package jp.groupsession.v2.sml;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.StatisticsBiz;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.dao.base.SmlLogCountAdelDao;
import jp.groupsession.v2.man.model.base.SmlLogCountAdelModel;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmailDao;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlAdelDao;
import jp.groupsession.v2.sml.dao.SmlBinDao;
import jp.groupsession.v2.sml.dao.SmlJmeisDao;
import jp.groupsession.v2.sml.dao.SmlLogCountSumDao;
import jp.groupsession.v2.sml.dao.SmlSmeisDao;
import jp.groupsession.v2.sml.dao.SmlWmeisDao;
import jp.groupsession.v2.sml.model.SmlAccountModel;
import jp.groupsession.v2.sml.model.SmlAdelModel;
import jp.groupsession.v2.sml.model.SmlAdminModel;
import jp.groupsession.v2.sml.model.SmlLogCountSumModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] ショートメールについての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class SmlBatchListenerImpl implements IBatchListener {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(SmlBatchListenerImpl.class);

    /**
     * <p>日次バッチ起動時に実行される
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doDayBatch(Connection con, IBatchModel param) throws Exception {

        log__.debug("ショートメールバッチ処理開始");

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

           /**********************************************************
            *
            * 自動削除設定に従いデータを削除する
            *
            **********************************************************/

            SmlJmeisDao jmsDao = new SmlJmeisDao(con);
            SmlSmeisDao smsDao = new SmlSmeisDao(con);
            SmlWmeisDao wmsDao = new SmlWmeisDao(con);

            //ショートメール自動削除
            SmlAdelDao delDao = new SmlAdelDao(con);
            SmlAdelModel delMdl = delDao.select(0);
            SmlCommonBiz smlBiz = new SmlCommonBiz();
            SmlAdminModel admMdl = smlBiz.getSmailAdminConf(-1, con);

            //「管理者が設定する」で設定済み
            if (delMdl != null
                    && admMdl.getSmaAutoDelKbn() == GSConstSmail.SML_DEL_KBN_ADM_SETTING) {

                if (delMdl.getSadDdelKbn() == GSConstSmail.SML_AUTO_DEL_LIMIT) {
                    smlBiz.deleteJmeis(con, delMdl, 2);
                    smlBiz.deleteSmeis(con, delMdl, 2);
                    smlBiz.deleteWmeis(con, delMdl, 2);
                }

                if (delMdl.getSadJdelKbn() == GSConstSmail.SML_AUTO_DEL_LIMIT) {
                    smlBiz.deleteJmeis(con, delMdl, 1);
                }

                if (delMdl.getSadSdelKbn() == GSConstSmail.SML_AUTO_DEL_LIMIT) {
                    smlBiz.deleteSmeis(con, delMdl, 1);
                }

                if (delMdl.getSadWdelKbn() == GSConstSmail.SML_AUTO_DEL_LIMIT) {
                    smlBiz.deleteWmeis(con, delMdl, 1);
                }

            //「各ユーザが設定する」で設定済み
            } else if (delMdl != null
                    && admMdl.getSmaAutoDelKbn() == GSConstSmail.SML_DEL_KBN_USER_SETTING) {

                //[自動削除する]に設定されている個人設定データを区分毎に取得
                ArrayList<SmlAdelModel> jdelList = delDao.selectAutoDelUserData(1);
                ArrayList<SmlAdelModel> sdelList = delDao.selectAutoDelUserData(2);
                ArrayList<SmlAdelModel> wdelList = delDao.selectAutoDelUserData(3);
                ArrayList<SmlAdelModel> ddelList = delDao.selectAutoDelUserData(4);

                //ゴミ箱タブデータ削除
                if (!ddelList.isEmpty()) {
                    jmsDao.delete(ddelList, 2);
                    smsDao.delete(ddelList, 2);
                    wmsDao.delete(ddelList, 2);
                }

                //受信タブデータ削除
                if (!jdelList.isEmpty()) {
                    jmsDao.delete(jdelList, 1);
                }

                //送信タブデータ削除
                if (!sdelList.isEmpty()) {
                    smsDao.delete(sdelList, 1);
                }

                //草稿タブデータ削除
                if (!wdelList.isEmpty()) {
                    wmsDao.delete(wdelList, 1);
                }
            }

           /**********************************************************
            *
            * 各テーブルの削除状況を確認し、関連のあるデータが全て
            * 論理削除されている場合は物理削除する
            *
            **********************************************************/
            //削除対象のメールSIDを取得
            SmailDao smlDao = new SmailDao(con);
            List<String> mailSidList = smlDao.getAllDeleteMailSidList();

            //ラベルを削除
            smlDao.deleteJushinLabel(mailSidList);
            smlDao.deleteSoshinLabel(mailSidList);

            //ショートメール明細(受信)を物理削除
            SmlJmeisDao jmeisDao = new SmlJmeisDao(con);
            jmeisDao.deleteJMail(mailSidList);

            //ショートメール明細(送信)を物理削除
            SmlSmeisDao smeisDao = new SmlSmeisDao(con);
            smeisDao.deleteSMail(mailSidList);

            //ショートメールに送付されているバイナリSID一覧取得
            SmlBinDao sbinDao = new SmlBinDao(con);
            List<Long> binSidList = sbinDao.selectBinSidList(mailSidList);

            //バイナリ情報を論理削除
            CmnBinfDao binDao = new CmnBinfDao(con);
            CmnBinfModel cbMdl = new CmnBinfModel();
            cbMdl.setBinJkbn(GSConst.JTKBN_DELETE);
            cbMdl.setBinUpuser(GSConstUser.SID_ADMIN);
            cbMdl.setBinUpdate(new UDate());
            binDao.updateJKbn(cbMdl, binSidList);

            //ショートメールバイナリファイル送付情報を物理削除
            sbinDao.deleteSmlBin(mailSidList);

            //ディスク使用量を更新
            SmlAccountDao sacDao = new SmlAccountDao(con);
            List<SmlAccountModel> sacList = new ArrayList<SmlAccountModel>();
            sacList = sacDao.getAccountDataList();
            if (sacList != null && !sacList.isEmpty()) {
                for (SmlAccountModel sacMdl : sacList) {
                    smlBiz.updateAccountDiskSize(con, sacMdl.getSacSid());
                }
            }

            //未登録の統計情報_集計結果を登録
            SmlLogCountSumDao logSumDao = new SmlLogCountSumDao(con);
            List<SmlLogCountSumModel> logSumList = logSumDao.getNonRegisteredList();
            if (logSumList != null && !logSumList.isEmpty()) {
                for (SmlLogCountSumModel logSumMdl : logSumList) {
                    if (logSumDao.update(logSumMdl) == 0) {
                        logSumDao.insert(logSumMdl);
                    }
                }
            }

            //ショートメール 統計情報集計データ削除
            log__.debug("ショートメール 統計情報集計データ削除処理開始");
            SmlLogCountAdelModel smlLogCntAdelMdl = null;
            SmlLogCountAdelDao smlLogCntAdelDao = new SmlLogCountAdelDao(con);
            List<SmlLogCountAdelModel> adelList = smlLogCntAdelDao.select();
            if (adelList != null && !adelList.isEmpty()) {
                smlLogCntAdelMdl = adelList.get(0);
            }
            if (smlLogCntAdelMdl != null
            && smlLogCntAdelMdl.getSldDelKbn() == GSConstMain.LAD_DELKBN_AUTO) {
                int smlLogAdelYear = smlLogCntAdelMdl.getSldDelYear();
                int smlLogAdelMonth = smlLogCntAdelMdl.getSldDelMonth();

                //削除する境界の日付を設定する
                UDate smlLogDelUd = new UDate();
                log__.debug("現在日 = " + smlLogDelUd.getDateString());
                log__.debug("削除条件 経過年 = " + smlLogAdelYear);
                log__.debug("削除条件 経過月 = " + smlLogAdelMonth);
                smlLogDelUd.addYear(smlLogAdelYear * -1);
                smlLogDelUd.addMonth(smlLogAdelMonth * -1);
                smlLogDelUd.setMaxHhMmSs();
                log__.debug("削除境界線(この日以前のデータを削除) = " + smlLogDelUd.getTimeStamp());

                StatisticsBiz statsBiz = new StatisticsBiz();
                //集計データを削除
                int smlLogDelCount = statsBiz.deleteLogCount(
                        con, "SML_LOG_COUNT", "SLC_DATE", smlLogAdelYear, smlLogAdelMonth);
                log__.debug("ショートメール 統計情報集計データ" + smlLogDelCount + "件を削除");
            }

            commitFlg = true;

            log__.debug("ショートメールバッチ処理終了");

        } catch (SQLException e) {
            log__.error("ショートメールバッチ処理失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <p>1時間間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doOneHourBatch(Connection con, IBatchModel param) throws Exception {
        boolean commit = false;
        try {
            //統計情報の集計を行う
            SmlLogCountSumDao logSumDao = new SmlLogCountSumDao(con);
            UDate date = new UDate();
            //日付変更直後のみ前日の集計も行う
            if (date.getIntHour() == 0) {
                UDate prev = date.cloneUDate();
                prev.addDay(-1);
                List<SmlLogCountSumModel> prevSumList = logSumDao.getSumLogCount(prev);
                if (prevSumList != null && !prevSumList.isEmpty()) {
                    for (SmlLogCountSumModel logSumMdl : prevSumList) {
                        if (logSumDao.update(logSumMdl) == 0) {
                            logSumDao.insert(logSumMdl);
                        }
                    }
                }
            }
            List<SmlLogCountSumModel> logSumList = logSumDao.getSumLogCount(date);
            if (logSumList != null && !logSumList.isEmpty()) {
                for (SmlLogCountSumModel logSumMdl : logSumList) {
                    if (logSumDao.update(logSumMdl) == 0) {
                        logSumDao.insert(logSumMdl);
                    }
                }
            }
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("ショートメール 統計情報の集計に失敗", e);
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
    }

    /**
     * <p>5分間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void do5mBatch(Connection con, IBatchModel param) throws Exception {
    }
}