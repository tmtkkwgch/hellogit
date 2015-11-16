package jp.groupsession.v2.cir.cir010;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cir.cir180.Cir180Dao;
import jp.groupsession.v2.cir.dao.CirAccountDao;
import jp.groupsession.v2.cir.dao.CirInfDao;
import jp.groupsession.v2.cir.dao.CirUserDao;
import jp.groupsession.v2.cir.dao.CirViewDao;
import jp.groupsession.v2.cir.dao.CircularDao;
import jp.groupsession.v2.cir.model.AccountDataModel;
import jp.groupsession.v2.cir.model.CirAccountModel;
import jp.groupsession.v2.cir.model.CirInfModel;
import jp.groupsession.v2.cir.model.CirSearchModel;
import jp.groupsession.v2.cir.model.CirUserModel;
import jp.groupsession.v2.cir.model.CirViewModel;
import jp.groupsession.v2.cir.model.CircularDspModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 回覧板一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir010Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cir010Biz.class);

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ銃砲
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Cir010ParamModel paramMdl, Connection con,
                            int userSid, RequestModel reqMdl)
    throws SQLException {


        //アカウント情報を設定
        __setAccountInf(paramMdl, con, reqMdl);

        //回覧板個人設定を取得する
        CirCommonBiz ccBiz = new CirCommonBiz();
        int limit = ccBiz.getCountLimit(userSid, con);

        List < CircularDspModel > cList = null;

        //処理モードで処理を分岐
        String cmdMode = NullDefault.getString(paramMdl.getCir010cmdMode(), "");
        if (cmdMode.equals(GSConstCircular.MODE_JUSIN)) {
            //受信
            cList = __getJusinList(paramMdl, con, limit, paramMdl.getCirViewAccount());

        } else if (cmdMode.equals(GSConstCircular.MODE_SOUSIN)) {
            //送信済み
            cList = __getSousinList(paramMdl, con, limit, paramMdl.getCirViewAccount());

        } else if (cmdMode.equals(GSConstCircular.MODE_GOMI)) {
            //ゴミ箱
            cList = __getGomiList(paramMdl, con, limit, paramMdl.getCirViewAccount());
        }


        for (int i = 0; i < cList.size(); i++) {
            CircularDspModel clMdl = cList.get(i);
            String strAdate =
                UDateUtil.getSlashYYMD(clMdl.getCifAdate())
                + "  "
                + UDateUtil.getSeparateHMS(clMdl.getCifAdate());
            clMdl.setDspCifAdate(strAdate);
        }

        String[] delInfSid = paramMdl.getCir010delInfSid();
        ArrayList<String> saveList = new ArrayList<String>();

        if (delInfSid != null) {

            log__.debug("delInfSid.length = " + delInfSid.length);

            for (int i = 0; i < delInfSid.length; i++) {
                String cirSid = NullDefault.getString(delInfSid[i], "");

                boolean addFlg = true;
                for (int j = 0; j < cList.size(); j++) {
                    CircularDspModel clMdl = cList.get(j);
                    if (cirSid.equals(clMdl.getCifSid() + "-" + clMdl.getJsFlg())) {
                        addFlg = false;
                        break;
                    }
                }
                if (addFlg) {
                    saveList.add(String.valueOf(delInfSid[i]));
                    log__.debug("save SID = " + delInfSid[i]);
                }
            }
        }
        //saveリスト(現在ページ以外でチェックされている値)
        paramMdl.setCir010saveList(saveList);

        //一覧をフォームへセット
        paramMdl.setCir010CircularList(cList);

        //自動リロード時間を取得
        paramMdl.setCir010Reload(__getReloadTime(con, userSid));

        //管理者権限
        BaseUserModel baseMdl = reqMdl.getSmodel();
        CommonBiz cmnBiz = new CommonBiz();

        if (baseMdl != null) {
            boolean adminUser = cmnBiz.isPluginAdmin(con, baseMdl,
                    GSConstCircular.PLUGIN_ID_CIRCULAR);
            if (adminUser) {
                paramMdl.setAdminFlg(GSConst.USER_ADMIN);
            } else {
                paramMdl.setAdminFlg(GSConst.USER_NOT_ADMIN);
            }
        }
    }

    /**
     * <br>[機  能] 回覧情報(受信)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param limit 1ページの表示件数
     * @param cacSid アカウントSID
     * @return List in CircularListModel
     * @throws SQLException SQL実行例外
     */
    private List < CircularDspModel > __getJusinList(
        Cir010ParamModel paramMdl,
        Connection con,
        int limit,
        int cacSid) throws SQLException {

        int nowPage = paramMdl.getCir010pageNum1();
        int start = PageUtil.getRowNumber(nowPage, limit);

        //検索用Modelを作成
        CirSearchModel bean = new CirSearchModel();
        bean.setCacSid(cacSid);
        bean.setLimit(limit);
        bean.setOrderKey(paramMdl.getCir010orderKey());
        bean.setSortKey(paramMdl.getCir010sortKey());

        //件数カウント
        CircularDao cDao = new CircularDao(con);
        long maxCount = cDao.getJusinCount(bean);
        log__.debug("件数 = " + maxCount);

        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }
        bean.setStart(start);

        paramMdl.setCir010pageNum1(nowPage);
        paramMdl.setCir010pageNum2(nowPage);
        paramMdl.setCir010PageLabel(PageUtil.createPageOptions(maxCount, limit));

        if (maxCount < 1) {
            return new ArrayList<CircularDspModel>();
        }

        return cDao.getJusinList(bean);
    }

    /**
     * <br>[機  能] 回覧情報(送信済み)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param limit 1ページの表示件数
     * @param cacSid アカウントSID
     * @return List in CircularListModel
     * @throws SQLException SQL実行例外
     */
    private List < CircularDspModel > __getSousinList(
            Cir010ParamModel paramMdl,
        Connection con,
        int limit,
        int cacSid) throws SQLException {

        int nowPage = paramMdl.getCir010pageNum1();
        int start = PageUtil.getRowNumber(nowPage, limit);

        //検索用Modelを作成
        CirSearchModel bean = new CirSearchModel();
        bean.setCacSid(cacSid);
        bean.setLimit(limit);
        bean.setOrderKey(paramMdl.getCir010orderKey());
        bean.setSortKey(paramMdl.getCir010sortKey());

        //件数カウント
        CircularDao cDao = new CircularDao(con);
        long maxCount = cDao.getSousinCount(bean);
        log__.debug("件数 = " + maxCount);

        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }
        bean.setStart(start);

        paramMdl.setCir010pageNum1(nowPage);
        paramMdl.setCir010pageNum2(nowPage);
        paramMdl.setCir010PageLabel(PageUtil.createPageOptions(maxCount, limit));

        if (maxCount < 1) {
            return new ArrayList<CircularDspModel>();
        }

        return cDao.getSousinList(bean);
    }

    /**
     * <br>[機  能] 回覧情報(ゴミ箱)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param limit 1ページの表示件数
     * @param cacSid アカウントSID
     * @return List in CircularListModel
     * @throws SQLException SQL実行例外
     */
    private List < CircularDspModel > __getGomiList(
            Cir010ParamModel paramMdl,
        Connection con,
        int limit,
        int cacSid) throws SQLException {

        int nowPage = paramMdl.getCir010pageNum1();
        int start = PageUtil.getRowNumber(nowPage, limit);

        //検索用Modelを作成
        CirSearchModel bean = new CirSearchModel();
        bean.setCacSid(cacSid);
        bean.setLimit(limit);
        bean.setOrderKey(paramMdl.getCir010orderKey());
        bean.setSortKey(paramMdl.getCir010sortKey());

        //件数カウント
        CircularDao cDao = new CircularDao(con);
        long maxCount = cDao.getGomiCount(bean);
        log__.debug("件数 = " + maxCount);

        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }
        bean.setStart(start);

        paramMdl.setCir010pageNum1(nowPage);
        paramMdl.setCir010pageNum2(nowPage);
        paramMdl.setCir010PageLabel(PageUtil.createPageOptions(maxCount, limit));

        if (maxCount < 1) {
            return new ArrayList<CircularDspModel>();
        }

        return cDao.getGomiList(bean);
    }

    /**
     * <br>[機  能] 削除する回覧板のタイトルを取得する
     * <br>[解  説] 複数存在する場合は改行を挿入する
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cacSid アカウントSID
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return String 削除する回覧板名称
     * @throws SQLException SQL実行例外
     */
    public String getDeleteCirName(Cir010ParamModel paramMdl, int cacSid, Connection con,
                                RequestModel reqMdl)
    throws SQLException {

        List < CircularDspModel > cList = __getConfList(paramMdl, cacSid, con);
        int mikakkuCount = 0;

        //回覧板名称取得
        StringBuilder deleteCir = new StringBuilder();
        for (int i = 0; i < cList.size(); i++) {
            CircularDspModel ciMdl = cList.get(i);

            String cmdMode = NullDefault.getString(paramMdl.getCir010cmdMode(), "");
            if (cmdMode.equals(GSConstCircular.MODE_JUSIN)) {
                //受信
                if (ciMdl.getCvwConf() == GSConstCircular.CONF_UNOPEN) {
                    //未確認
                    mikakkuCount++;
                    deleteCir.append("<font color='#ff0000'><strong>");
                    deleteCir.append("・");
                    deleteCir.append(NullDefault.getString(
                            StringUtilHtml.transToHTmlPlusAmparsant(ciMdl.getCifTitle()), ""));
                    deleteCir.append("</strong></font>");

                } else {
                    //確認済み
                    deleteCir.append("・");
                    deleteCir.append(NullDefault.getString(
                            StringUtilHtml.transToHTmlPlusAmparsant(ciMdl.getCifTitle()), ""));
                }

            } else if (cmdMode.equals(GSConstCircular.MODE_SOUSIN)) {
                //送信済み
                deleteCir.append("・");
                deleteCir.append(NullDefault.getString(
                        StringUtilHtml.transToHTmlPlusAmparsant(ciMdl.getCifTitle()), ""));

            } else if (cmdMode.equals(GSConstCircular.MODE_GOMI)) {
                //ゴミ箱
                deleteCir.append("・");

                GsMessage gsMsg = new GsMessage(reqMdl);

                if (ciMdl.getJsFlg().equals(GSConstCircular.MODE_JUSIN)) {
                    String textJusin = gsMsg.getMessage("cmn.receive2");
                    deleteCir.append("[ " + textJusin + " ] ");
                } else if (ciMdl.getJsFlg().equals(GSConstCircular.MODE_SOUSIN)) {
                    String textSosin = gsMsg.getMessage("cmn.sent2");
                    deleteCir.append("[ " + textSosin + " ] ");
                }
                deleteCir.append(NullDefault.getString(
                        StringUtilHtml.transToHTmlPlusAmparsant(ciMdl.getCifTitle()), ""));
            }


            //改行を挿入
            if (i < cList.size() - 1) {
                deleteCir.append(GSConst.NEW_LINE_STR);
            }
        }

        paramMdl.setMikakkuCount(mikakkuCount);
        return deleteCir.toString();
    }

    /**
     * <br>[機  能] 選択した回覧板SID(複数)から回覧板情報を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cacSid アカウントSID
     * @param con コネクション
     * @return List in CircularDspModel
     * @throws SQLException SQL実行例外
     */
    private List<CircularDspModel>  __getConfList(
            Cir010ParamModel paramMdl,
            int cacSid,
            Connection con) throws SQLException {

        //回覧板SIDを取得
        String[] delInfSidBase = paramMdl.getCir010delInfSid();
        String[] delInfSid = new String[delInfSidBase.length];
        ArrayList<String> juList = new ArrayList<String>();
        ArrayList<String> soList = new ArrayList<String>();

        for (int i = 0; i < delInfSidBase.length; i++) {
            String[] splitSid = delInfSidBase[i].split("-");
            //回覧板SIDを配列にセット
            delInfSid[i] = splitSid[0];

            if (splitSid[1].equals(GSConstCircular.MODE_JUSIN)) {
                //受信リストに追加
                juList.add(splitSid[0]);

            } else if (splitSid[1].equals(GSConstCircular.MODE_SOUSIN)) {
                //送信リストに追加
                soList.add(splitSid[0]);
            }
        }

        //受信回覧板SID
        String[] juSid = juList.toArray(new String[juList.size()]);
        //送信回覧板SID
        String[] soSid = soList.toArray(new String[soList.size()]);

        CirSearchModel bean = new CirSearchModel();
        bean.setCacSid(cacSid);

        List < CircularDspModel > cList = null;

        //処理モードで処理を分岐
        //回覧板SID(複数)から回覧板情報を取得する
        String cmdMode = NullDefault.getString(paramMdl.getCir010cmdMode(), "");
        if (cmdMode.equals(GSConstCircular.MODE_JUSIN)) {
            //受信
            CircularDao ciDao = new CircularDao(con);
            cList = ciDao.getJusinConfList(bean, delInfSid);

        } else if (cmdMode.equals(GSConstCircular.MODE_SOUSIN)) {
            //送信済み
            CirInfDao ciDao = new CirInfDao(con);
            cList = ciDao.getCirListFromCirSid(delInfSid);

        } else if (cmdMode.equals(GSConstCircular.MODE_GOMI)) {
            //ゴミ箱
            CircularDao ciDao = new CircularDao(con);
            cList = ciDao.getGomiConfList(bean, juSid, soSid);
        }

        return cList;
    }

    /**
     * <br>[機  能] 選択された回覧板を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cacSid アカウントSID
     * @throws SQLException SQL実行例外
     */
    public void deleteCir(Cir010ParamModel paramMdl, Connection con, int cacSid)
    throws SQLException {

        //回覧板SIDを取得
        String[] delInfSidBase = paramMdl.getCir010delInfSid();
        String[] delInfSid = new String[delInfSidBase.length];
        ArrayList<String> juList = new ArrayList<String>();
        ArrayList<String> soList = new ArrayList<String>();

        for (int i = 0; i < delInfSidBase.length; i++) {
            String[] splitSid = delInfSidBase[i].split("-");
            //回覧板SIDを配列にセット
            delInfSid[i] = splitSid[0];

            if (splitSid[1].equals(GSConstCircular.MODE_JUSIN)) {
                //受信リストに追加
                juList.add(splitSid[0]);

            } else if (splitSid[1].equals(GSConstCircular.MODE_SOUSIN)) {
                //送信リストに追加
                soList.add(splitSid[0]);
            }
        }

        //受信回覧板SID
        String[] juSid = juList.toArray(new String[juList.size()]);
        //送信回覧板SID
        String[] soSid = soList.toArray(new String[soList.size()]);
        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            //処理モードで処理を分岐
            String cmdMode = NullDefault.getString(paramMdl.getCir010cmdMode(), "");
            if (cmdMode.equals(GSConstCircular.MODE_JUSIN)) {
                //受信
                CirViewModel bean = new CirViewModel();
                bean.setCvwDsp(GSConstCircular.DSPKBN_DSP_NG);
                bean.setCvwEuid(cacSid);
                bean.setCvwEdate(now);
                bean.setCacSid(cacSid);

                //選択された回覧板の状態区分を更新する(論理削除)
                CirViewDao cvDao = new CirViewDao(con);
                cvDao.updateDspFlg(bean, delInfSid);

            } else if (cmdMode.equals(GSConstCircular.MODE_SOUSIN)) {
                //送信済み
                CirInfModel bean = new CirInfModel();
                bean.setCifJkbn(GSConstCircular.DSPKBN_DSP_NG);
                bean.setCifEuid(cacSid);
                bean.setCifEdate(now);

                //選択された回覧板の状態区分を更新する(論理削除)
                CirInfDao cDao = new CirInfDao(con);
                cDao.updateJkbn(bean, delInfSid);

                //回覧先の状態区分を更新する(削除)
                CirViewDao cirViewDao = new CirViewDao(con);
                for (String soCifSid : soList) {
                    cirViewDao.deleteAllView(Integer.parseInt(soCifSid), cacSid, now);
                }

            } else if (cmdMode.equals(GSConstCircular.MODE_GOMI)) {
                //ゴミ箱
                CirViewModel juBean = new CirViewModel();
                juBean.setCvwDsp(GSConstCircular.DSPKBN_DSP_DEL);
                juBean.setCvwEuid(cacSid);
                juBean.setCvwEdate(now);
                juBean.setCacSid(cacSid);
                //選択された受信回覧板の状態区分を更新する(論理削除)
                CirViewDao cvDao = new CirViewDao(con);
                cvDao.updateDspFlg(juBean, juSid);

                CirInfModel soBean = new CirInfModel();
                soBean.setCifJkbn(GSConstCircular.DSPKBN_DSP_DEL);
                soBean.setCifEuid(cacSid);
                soBean.setCifEdate(now);
                //選択された送信回覧板の状態区分を更新する(論理削除)
                CirInfDao cDao = new CirInfDao(con);
                cDao.updateJkbn(soBean, soSid);
            }

            con.commit();
        } catch (SQLException e) {
            log__.warn("回覧板削除に失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        }
    }

    /**
     * <br>[機  能] 選択された回覧板を元に戻す
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cacSid アカウントSID
     * @throws SQLException SQL実行例外
     */
    public void comeBackCir(Cir010ParamModel paramMdl, Connection con, int cacSid)
    throws SQLException {

        //回覧板SIDを取得
        String[] delInfSidBase = paramMdl.getCir010delInfSid();
        String[] delInfSid = new String[delInfSidBase.length];
        ArrayList<String> juList = new ArrayList<String>();
        ArrayList<String> soList = new ArrayList<String>();

        for (int i = 0; i < delInfSidBase.length; i++) {
            String[] splitSid = delInfSidBase[i].split("-");
            //回覧板SIDを配列にセット
            delInfSid[i] = splitSid[0];

            if (splitSid[1].equals(GSConstCircular.MODE_JUSIN)) {
                //受信リストに追加
                juList.add(splitSid[0]);

            } else if (splitSid[1].equals(GSConstCircular.MODE_SOUSIN)) {
                //送信リストに追加
                soList.add(splitSid[0]);
            }
        }

        //受信回覧板SID
        String[] juSid = juList.toArray(new String[juList.size()]);
        //送信回覧板SID
        String[] soSid = soList.toArray(new String[soList.size()]);
        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            //ゴミ箱
            CirViewModel juBean = new CirViewModel();
            juBean.setCvwDsp(GSConstCircular.DSPKBN_DSP_OK);
            juBean.setCvwEuid(cacSid);
            juBean.setCvwEdate(now);
            juBean.setCacSid(cacSid);
            //選択された受信回覧板の状態区分を更新する(復帰)
            CirViewDao cvDao = new CirViewDao(con);
            cvDao.updateDspFlg(juBean, juSid);

            CirInfModel soBean = new CirInfModel();
            soBean.setCifJkbn(GSConstCircular.DSPKBN_DSP_OK);
            soBean.setCifEuid(cacSid);
            soBean.setCifEdate(now);
            //選択された送信回覧板の状態区分を更新する(復帰)
            CirInfDao cDao = new CirInfDao(con);
            cDao.updateJkbn(soBean, soSid);

            con.commit();
        } catch (SQLException e) {
            log__.warn("回覧板復帰に失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        }
    }

    /**
     * <br>[機  能] ゴミ箱のデータ件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param cacSid アカウントSID
     * @param con コネクション
     * @return cnt 件数
     * @throws SQLException SQL実行時例外
     */
    public int getGomibakoCnt(Cir010ParamModel paramMdl, int cacSid, Connection con)
        throws SQLException {

        //検索用Modelを作成
        CirSearchModel bean = new CirSearchModel();
        bean.setCacSid(cacSid);

        CircularDao cDao = new CircularDao(con);
        int cnt = cDao.getGomiCount(bean);

        return cnt;
    }

    /**
     * <br>[機  能] 全ての回覧板を削除する(ゴミ箱内)
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cacSid アカウントSID
     * @throws SQLException SQL実行例外
     */
    public void deleteAllCir(Cir010ParamModel paramMdl, Connection con, int cacSid)
    throws SQLException {

        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            CirViewModel juBean = new CirViewModel();
            juBean.setCvwDsp(GSConstCircular.DSPKBN_DSP_DEL);
            juBean.setCvwEuid(cacSid);
            juBean.setCvwEdate(now);
            juBean.setCacSid(cacSid);
            //受信回覧板の状態区分を更新する(論理削除)
            CirViewDao cvDao = new CirViewDao(con);
            cvDao.updateAllView(juBean);

            CirInfModel soBean = new CirInfModel();
            soBean.setCifJkbn(GSConstCircular.DSPKBN_DSP_DEL);
            soBean.setCifEuid(cacSid);
            soBean.setCifEdate(now);
            soBean.setCifAuid(cacSid);
            //送信回覧板の状態区分を更新する(論理削除)
            CirInfDao cDao = new CirInfDao(con);
            cDao.updateAllUserCir(soBean);

            con.commit();
        } catch (SQLException e) {
            log__.warn("回覧板削除に失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        }
    }

    /**
     * <br>[機  能] 自動リロード時間を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @return int 自動リロード時間
     * @throws SQLException SQL実行例外
     */
    private int __getReloadTime(Connection con, int userSid)
    throws SQLException {

        int reloadTime = GSConstCircular.AUTO_RELOAD_10MIN;

        CirUserDao dao = new CirUserDao(con);
        CirUserModel model = dao.getCirUserInfo(userSid);
        if (model != null) {
            reloadTime = model.getCurReload();
        }

        return reloadTime;
    }

    /**
     * アカウント情報を設定
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    private void __setAccountInf(
                                Cir010ParamModel paramMdl,
                                Connection con,
                                RequestModel reqMdl)
    throws SQLException {

        CirAccountDao cacDao = new CirAccountDao(con);
        CirAccountModel cacMdl = null;

        //アカウントを取得
        if (paramMdl.getCirViewAccount() <= 0) {
            //デフォルトのアカウントを取得
            cacMdl = cacDao.selectFromUsrSid(reqMdl.getSmodel().getUsrsid());
        } else {
            //選択されたアカウントを取得
            cacMdl = cacDao.select(paramMdl.getCirViewAccount());
        }

        if (cacMdl != null) {
            //アカウント
            paramMdl.setCirViewAccount(cacMdl.getCacSid());
            //アカウント名
            paramMdl.setCirViewAccountName(cacMdl.getCacName());
            //アカウントテーマ
            paramMdl.setCir010AccountTheme(cacMdl.getCacTheme());

        }

        //使用可能なアカウントの一覧を取得する
        Cir180Dao dao = new Cir180Dao(con);
        List<AccountDataModel> accountList = dao.getAccountList(reqMdl.getSmodel().getUsrsid());
        paramMdl.setCir010AccountList(accountList);
    }

}
