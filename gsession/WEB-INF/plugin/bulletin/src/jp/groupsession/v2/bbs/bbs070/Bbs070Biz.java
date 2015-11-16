package jp.groupsession.v2.bbs.bbs070;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 スレッド登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs070Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs070Biz.class);

    /**
     * <br>[機  能] テンポラリディレクトリのファイル削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void doDeleteFile(String tempDir) throws IOToolsException {

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、スレッド情報を設定する
     * <br>[備  考]
     * @param cmd CMDパラメータ
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    @SuppressWarnings("unchecked")
    public void setInitData(String cmd, RequestModel reqMdl, Bbs070ParamModel paramMdl,
                            Connection con, String appRoot, String tempDir)
    throws SQLException, IOException, IOToolsException, TempFileException {
        log__.debug("START");

        CommonBiz cmnBiz = new CommonBiz();
        BbsBiz bbsBiz = new BbsBiz();

        //フォーラム名を設定
        BulletinDspModel btDspMdl = bbsBiz.getForumData(con, paramMdl.getBbs010forumSid());
        paramMdl.setBbs070forumName(btDspMdl.getBfiName());
        //フォーラム掲示期間有無
        paramMdl.setBbs070limitDisable(btDspMdl.getBfiLimitOn());

        //投稿一覧からの遷移、かつ処理モード = 編集の場合はスレッド情報を取得する
        if (cmd.equals("editThread")
                && paramMdl.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {

            con.setAutoCommit(false);
            //スレッド情報を設定
            BulletinDspModel bbsMdl = bbsBiz.getThreadData(con, paramMdl.getThreadSid());
            paramMdl.setBbs070title(bbsMdl.getBtiTitle());
            paramMdl.setBbs070limit(bbsMdl.getBtiLimit());
            if (paramMdl.getBbs070limit() == GSConstBulletin.THREAD_LIMIT_YES) {
                UDate limitFrDate = bbsMdl.getBtiLimitFrDate();
                paramMdl.setBbs070limitFrYear(limitFrDate.getYear());
                paramMdl.setBbs070limitFrMonth(limitFrDate.getMonth());
                paramMdl.setBbs070limitFrDay(limitFrDate.getIntDay());

                UDate limitToDate = bbsMdl.getBtiLimitDate();
                paramMdl.setBbs070limitYear(limitToDate.getYear());
                paramMdl.setBbs070limitMonth(limitToDate.getMonth());
                paramMdl.setBbs070limitDay(limitToDate.getIntDay());
            }

            //例外条件(掲示期間 無効 + スレッド期限設定あり)
            if (paramMdl.getBbs070limitDisable() == GSConstBulletin.THREAD_DISABLE
                    && paramMdl.getBbs070limit() == GSConstBulletin.THREAD_LIMIT_YES) {
                paramMdl.setBbs070limitException(GSConstBulletin.THREAD_EXCEPTION);
            }

            //スレッドの投稿情報を設定
            BulletinDspModel bbsWriteMdl = bbsBiz.getWriteData(con, paramMdl.getBbs080writeSid());
            paramMdl.setBbs070value(bbsWriteMdl.getBwiValue());
            if (bbsWriteMdl.getGrpSid() > 0) {
                paramMdl.setBbs070contributor(bbsWriteMdl.getGrpSid());
            }

            //添付ファイルをテンポラリディレクトリへ移動する
            String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)
            if (bbsWriteMdl.getTmpFileList() != null && bbsWriteMdl.getTmpFileList().size() > 0) {
                List < CmnBinfModel > tmpFileList = bbsWriteMdl.getTmpFileList();
                String[] binSids = new String[tmpFileList.size()];

                //バイナリSIDの取得
                for (int i = 0; i < tmpFileList.size(); i++) {
                    binSids[i] = String.valueOf(tmpFileList.get(i).getBinSid());
                }

                //取得したバイナリSIDからバイナリ情報を取得
                List<CmnBinfModel> binList = cmnBiz.getBinInfo(con, binSids, reqMdl.getDomain());

                int fileNum = 1;
                for (CmnBinfModel binData : binList) {
                    cmnBiz.saveTempFile(dateStr, binData, appRoot, tempDir, fileNum);
                    fileNum++;
                }
            }
        }

        if (cmd.equals("addThre")
                && paramMdl.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_ADD) {

            UDate now = new UDate();
            paramMdl.setBbs070limitFrYear(now.getYear());
            paramMdl.setBbs070limitFrMonth(now.getMonth());
            paramMdl.setBbs070limitFrDay(now.getIntDay());

            //掲示期間の初期値設定（フォーラムの設定を参照する）
            if (btDspMdl.getBfiLimit() == GSConstBulletin.THREAD_LIMIT_YES) {

                paramMdl.setBbs070limit(GSConstBulletin.THREAD_LIMIT_YES);

                //日数を加算
                now.addDay(btDspMdl.getBfiLimitDate());
                paramMdl.setBbs070limitYear(now.getYear());
                paramMdl.setBbs070limitMonth(now.getMonth());
                paramMdl.setBbs070limitDay(now.getIntDay());
            } else {
                paramMdl.setBbs070limit(GSConstBulletin.THREAD_LIMIT_NO);
                paramMdl.setBbs070limitYear(now.getYear());
                paramMdl.setBbs070limitMonth(now.getMonth());
                paramMdl.setBbs070limitDay(now.getIntDay());
            }

            //スレッドテンプレートを適応する。
            if (btDspMdl.getBfiTemplateKbn() == GSConstBulletin.BBS_THRE_TEMPLATE_YES) {
                paramMdl.setBbs070value(NullDefault.getString(btDspMdl.getBfiTemplate(), ""));
            }
        }

        //添付ファイル一覧を設定
        List<LabelValueBean> sortList = cmnBiz.getTempFileLabelList(tempDir);
        Collections.sort(sortList);
        paramMdl.setBbs070FileLabelList(sortList);

        //年月日コンボを設定
        paramMdl.setBbs070FrYearList(__getYearLabelList(reqMdl));   //年
        paramMdl.setBbs070FrMonthList(__getMonthLabelList(reqMdl)); //月
        paramMdl.setBbs070FrDayList(__getDayLabelList(reqMdl));     //日
        paramMdl.setBbs070yearList(__getYearLabelList(reqMdl));   //年
        paramMdl.setBbs070monthList(__getMonthLabelList(reqMdl)); //月
        paramMdl.setBbs070dayList(__getDayLabelList(reqMdl));     //日

        //投稿者コンボを設定
        int registUser = reqMdl.getSmodel().getUsrsid();
        if (paramMdl.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            //編集の場合、新規登録時の登録ユーザを投稿者の基準とする
            BbsWriteInfDao writeDao = new BbsWriteInfDao(con);
            registUser = writeDao.getWriteAuid(paramMdl.getBbs080writeSid());
        }
        CmnUsrmInfDao usiDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel usiMdl = usiDao.selectUserNameAndJtkbn(registUser);

        int contributor = paramMdl.getBbs070contributor();
        if (contributor > 0) {
            GroupDao grpDao = new GroupDao(con);
            if (!grpDao.isBelong(registUser, contributor)) {
                //投稿者が投稿グループに所属していない場合には投稿者を編集不可にする。
                paramMdl.setBbs070contributorEditKbn(0);
                CmnGroupmDao cmnGrpDao = new CmnGroupmDao(con);
                CmnGroupmModel grpMdl = cmnGrpDao.selectGroup(contributor);
                if (grpMdl != null) {
                    paramMdl.setBbs070contributorJKbn(grpMdl.getGrpJkbn());
                    LabelValueBean label;
                    List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
                    label = new LabelValueBean(grpMdl.getGrpName(), String
                            .valueOf(contributor));
                    labelList.add(label);
                    paramMdl.setBbs070contributorList(labelList);
                }
            }
        } else if (usiMdl.getUsrJkbn() == GSConstUser.USER_JTKBN_DELETE) {
            //投稿者が削除済みユーザの場合には投稿者を編集不可にする。
            paramMdl.setBbs070contributorEditKbn(0);

            LabelValueBean label;
            List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
            String name = NullDefault.getString(usiMdl.getUsiSei(), "")
                    + "　" + NullDefault.getString(usiMdl.getUsiMei(), "");
            paramMdl.setBbs070contributorJKbn(usiMdl.getUsrJkbn());

            label = new LabelValueBean(name, String
                    .valueOf(contributor));
            labelList.add(label);
            paramMdl.setBbs070contributorList(labelList);
        }

        if (paramMdl.getBbs070contributorEditKbn() == 1) {
                //投稿者コンボを設定
            paramMdl.setBbs070contributorList(
                    cmnBiz.getMARegistrantCombo(con, registUser, reqMdl));
        }


        log__.debug("End");
    }

    /**
     * 年コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getYearLabelList(RequestModel reqMdl) {

        int sysYear = (new UDate()).getYear();
        int start = sysYear;
        int end = sysYear + 5;
        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                        gsMsg.getMessage("cmn.year", new String[] {String.valueOf(value)}),
                        String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * 月コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getMonthLabelList(RequestModel reqMdl) {

        int start = 1;
        int end = 12;
        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                            value + gsMsg.getMessage("cmn.month"), String.valueOf(value)));
        }
        return labelList;
    }
    /**
     * 日コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getDayLabelList(RequestModel reqMdl) {

        int start = 1;
        int end = 31;

        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                             value + gsMsg.getMessage("cmn.day"), String.valueOf(value)));
        }
        return labelList;
    }

}
