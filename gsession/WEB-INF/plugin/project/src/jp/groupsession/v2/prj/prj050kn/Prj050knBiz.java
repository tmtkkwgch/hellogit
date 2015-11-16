package jp.groupsession.v2.prj.prj050kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.dao.PrjPrjdataDao;
import jp.groupsession.v2.prj.dao.PrjTodocategoryDao;
import jp.groupsession.v2.prj.dao.PrjTododataDao;
import jp.groupsession.v2.prj.dao.PrjTodostatusDao;
import jp.groupsession.v2.prj.dao.ProjectUpdateDao;
import jp.groupsession.v2.prj.model.PrjPrjdataModel;
import jp.groupsession.v2.prj.model.PrjSmailModel;
import jp.groupsession.v2.prj.model.PrjSmailParamModel;
import jp.groupsession.v2.prj.model.PrjStatusHistoryModel;
import jp.groupsession.v2.prj.model.PrjTodocategoryModel;
import jp.groupsession.v2.prj.model.PrjTododataModel;
import jp.groupsession.v2.prj.model.PrjTodomemberModel;
import jp.groupsession.v2.prj.model.PrjTodostatusModel;
import jp.groupsession.v2.prj.model.ProjectItemModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] TODO登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj050knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj050knBiz.class);

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl リクエストモデル
     */
    public Prj050knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param appRoot ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void setInitData(Prj050knParamModel paramMdl, String appRoot)
    throws SQLException, IOToolsException {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        PrjCommonBiz prjComBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
        //プロジェクトSID
        int projectSid = paramMdl.getPrj050prjSid();
        //TODOSID
        int todoSid = paramMdl.getPrj050todoSid();
        //処理モード
        String cmdMode = paramMdl.getPrj050cmdMode();

        //プロジェクト名称を取得
        PrjPrjdataDao ppdDao = new PrjPrjdataDao(con__);
        ProjectItemModel piMdl = ppdDao.getProjectInfo(projectSid);
        if (piMdl != null) {
            paramMdl.setProjectName(piMdl.getProjectName());
        }

        //詳細入力
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
                //更新モード
                //TODO情報を取得
                PrjTododataDao ptdDao = new PrjTododataDao(con__);
                piMdl = ptdDao.getTodoData(todoSid);
                piMdl.setStrKanriNo(
                        StringUtil.toDecFormat(piMdl.getKanriNo(), GSConstProject.KANRI_NO_FORMAT));
                paramMdl.setProjectItem(piMdl);
            }

            //カテゴリ名称を取得
            PrjTodocategoryDao ptcDao = new PrjTodocategoryDao(con__);
            PrjTodocategoryModel ptcMdl = ptcDao.select(projectSid, paramMdl.getPrj050cate());
            if (ptcMdl != null) {
                paramMdl.setCategoryName(ptcMdl.getPtcName());
            }

            //担当者名を取得
            UserBiz userBiz = new UserBiz();
            paramMdl.setAdminMemberLabel(userBiz.getUserLabelList(
                    con__, paramMdl.getPrj050hdnTanto()));

            //開始予定年月日
            String kaisiYotei = "";
            if (!paramMdl.getPrj050kaisiYoteiYear().equals("")
            && !paramMdl.getPrj050kaisiYoteiMonth().equals("")
            && !paramMdl.getPrj050kaisiYoteiDay().equals("")) {
                kaisiYotei = paramMdl.getPrj050kaisiYoteiYear() + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kaisiYoteiMonth(), "00") + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kaisiYoteiDay(), "00");
            }
            paramMdl.setKaisiYotei(kaisiYotei);
            //期限年月日
            String kigen = "";
            if (!paramMdl.getPrj050kigenYear().equals("")
            && !paramMdl.getPrj050kigenMonth().equals("")
            && !paramMdl.getPrj050kigenDay().equals("")) {
                kigen = paramMdl.getPrj050kigenYear() + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kigenMonth(), "00") + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kigenDay(), "00");
            }
            paramMdl.setKigen(kigen);
            //開始実績年月日
            String kaisiJisseki = "";
            if (!paramMdl.getPrj050kaisiJissekiYear().equals("")
            && !paramMdl.getPrj050kaisiJissekiMonth().equals("")
            && !paramMdl.getPrj050kaisiJissekiDay().equals("")) {
                kaisiJisseki = paramMdl.getPrj050kaisiJissekiYear() + "/"
                          + StringUtil.toDecFormat(
                                  paramMdl.getPrj050kaisiJissekiMonth(), "00") + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kaisiJissekiDay(), "00");
            }
            paramMdl.setKaisiJisseki(kaisiJisseki);
            //終了実績年月日
            String syuryoJisseki = "";
            if (!paramMdl.getPrj050syuryoJissekiYear().equals("")
            && !paramMdl.getPrj050syuryoJissekiMonth().equals("")
            && !paramMdl.getPrj050syuryoJissekiDay().equals("")) {
                syuryoJisseki = paramMdl.getPrj050syuryoJissekiYear() + "/"
                        + StringUtil.toDecFormat(paramMdl.getPrj050syuryoJissekiMonth(), "00") + "/"
                        + StringUtil.toDecFormat(paramMdl.getPrj050syuryoJissekiDay(), "00");
            }
            paramMdl.setSyuryoJisseki(syuryoJisseki);

            //警告開始
            paramMdl.setKeikokuName(prjComBiz.getKeikokuName(paramMdl.getPrj050keikoku()));
            //重要度
            paramMdl.setJuyoName(prjComBiz.getWeightName(paramMdl.getPrj050juyou()));
            //状態
            PrjTodostatusDao ptsDao = new PrjTodostatusDao(con__);
            PrjTodostatusModel ptsMdl = ptsDao.select(projectSid, paramMdl.getPrj050status());
            if (ptsMdl != null) {
                paramMdl.setStatusName(ptsMdl.getPtsName());
                paramMdl.setStatusRate(ptsMdl.getPtsRate());
            }

            //状態変更理由
            paramMdl.setStatusCmt(StringUtilHtml.transToHTmlPlusAmparsant(
                    paramMdl.getPrj050statusCmt()));
            //内容
            paramMdl.setNaiyo(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj050naiyo()));

            //添付ファイル一覧を設定
            CommonBiz cmnBiz = new CommonBiz();
            String tempDir = cmnBiz.getTempDir(appRoot, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);
            paramMdl.setFileLabel(cmnBiz.getTempFileLabelList(tempDir));

        //簡易入力
        } else {

            if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
                //更新モード
                //TODO情報を取得
                PrjTododataDao ptdDao = new PrjTododataDao(con__);
                piMdl = ptdDao.getTodoData(todoSid);
                piMdl.setStrKanriNo(
                        StringUtil.toDecFormat(piMdl.getKanriNo(), GSConstProject.KANRI_NO_FORMAT));
                paramMdl.setProjectItem(piMdl);
            }

            //期限年月日
            String kigen = "";
            if (!paramMdl.getPrj050kigenYearEasy().equals("")
            && !paramMdl.getPrj050kigenMonthEasy().equals("")
            && !paramMdl.getPrj050kigenDayEasy().equals("")) {
                kigen = paramMdl.getPrj050kigenYearEasy() + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kigenMonthEasy(), "00") + "/"
                          + StringUtil.toDecFormat(paramMdl.getPrj050kigenDayEasy(), "00");
            }
            paramMdl.setKigen(kigen);
            //重要度
            paramMdl.setJuyoName(prjComBiz.getWeightName(paramMdl.getPrj050juyouEasy()));
            //状態
            PrjTodostatusDao ptsDao = new PrjTodostatusDao(con__);
            PrjTodostatusModel ptsMdl = ptsDao.select(projectSid, paramMdl.getPrj050statusEasy());
            if (ptsMdl != null) {
                paramMdl.setStatusName(ptsMdl.getPtsName());
                paramMdl.setStatusRate(ptsMdl.getPtsRate());
            }

            //状態変更理由
            paramMdl.setStatusCmt(
                    StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj050statusCmtEasy()));
            //内容
            paramMdl.setNaiyo(StringUtilHtml.transToHTmlPlusAmparsant(
                    paramMdl.getPrj050naiyoEasy()));

        }
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param appRoot ルートディレクトリ
     * @param tempRoot テンポラリディレクトリ
     * @param pconfig プラグインコンフィグ
     * @return boolean 画面遷移時、対象データがあるか(削除、プロジェクトSID変更済でないか)
     *                  true=データが存在する、false=存在しない
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException 入出力時例外
     * @throws Exception その他例外
     */
    public boolean doAddEdit(Prj050knParamModel paramMdl,
                              MlCountMtController cntCon,
                              int userSid,
                              String appRoot,
                              String tempRoot,
                              PluginConfig pconfig)
        throws SQLException, IOToolsException, IOException, Exception {

        boolean dataexist = true;

        int projectSid = paramMdl.getPrj050prjSid();
        //簡易入力時のショートメール送信処理
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_EASY) {
            //ショートメール通知区分
            PrjPrjdataDao prjDao = new PrjPrjdataDao(con__);
            PrjPrjdataModel prjMdl = prjDao.getProjectData(projectSid);

            //ショートメール通知区分
            if (prjMdl.getPrjMailKbn() == GSConstProject.TODO_MAIL_FREE) {
                paramMdl.setPrj050MailSend(GSConstProject.NOT_SEND);
            } else if (prjMdl.getPrjMailKbn() == GSConstProject.TODO_MAIL_SEND_ADMIN) {
                paramMdl.setPrj050MailSend(GSConstProject.SEND_LEADER);
            }
        }

        //処理モード
        String cmdMode = paramMdl.getPrj050cmdMode();
        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録
            doInsert(paramMdl, cntCon, userSid, appRoot, tempRoot, pconfig);

        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新
            dataexist = doUpdate(paramMdl, cntCon, userSid, appRoot, tempRoot, pconfig);
        }
        return dataexist;
    }

    /**
     * <br>[機  能] 更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param appRoot ルートディレクトリ
     * @param tempRoot テンポラリディレクトリ
     * @param pconfig プラグインコンフィグ
     * @return boolean 画面遷移時、対象データがあるか(削除、プロジェクトSID変更済でないか)
     *                  true=データが存在する、false=存在しない
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws Exception その他例外
     */
    public boolean doUpdate(Prj050knParamModel paramMdl,
                              MlCountMtController cntCon,
                              int userSid,
                              String appRoot,
                              String tempRoot,
                              PluginConfig pconfig)
        throws SQLException, IOToolsException, IOException, Exception {

        boolean commitFlg = false;
        UDate now = new UDate();
        boolean dataexist = true;

        //プロジェクトSID
        int projectSid = paramMdl.getPrj050prjSid();
        //TODOSID
        int todoSid = paramMdl.getPrj050todoSid();
        //状態区分
        int status = paramMdl.getPrj050status();
        //状態変更理由
        String statusCmt = null;
        //テンポラリディレクトリ
        String tempDir = null;

        //詳細入力 状態変更理由
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            statusCmt = NullDefault.getString(paramMdl.getPrj050statusCmt(), "");

        //簡易入力 状態変更理由
        } else {
            statusCmt = "";
        }

        try {

            con__.setAutoCommit(false);

            //TODO情報を取得
            PrjTododataDao ptdDao = new PrjTododataDao(con__);
            ProjectItemModel piMdl = ptdDao.getTodoData(todoSid);

            ProjectUpdateDao projectDao = new ProjectUpdateDao(con__);

            //管理番号
            int kanriNo = piMdl.getKanriNo();
            if (projectSid != piMdl.getProjectSid()) {
                //プロジェクトSIDが変更された場合、管理番号を採番する
                kanriNo = (int) cntCon.getSaibanNumber(GSConstProject.SBNSID_PROJECT,
                          projectSid + GSConstProject.SBNSID_SUB_KANRI,
                          userSid);
                dataexist = false;
            }

            //変更履歴SID
            int hisSid = piMdl.getHisSid();
            PrjStatusHistoryModel pshMdl = null;
            if (status != piMdl.getStatus() || !statusCmt.equals("")) {
                //状態が変更された or 状態変更時コメントが入力された場合
                //変更履歴SIDを採番する
                hisSid = (int) cntCon.getSaibanNumber(GSConstProject.SBNSID_PROJECT,
                         todoSid + GSConstProject.SBNSID_SUB_HISTORY,
                         userSid);

                //更新用のPrjStatusHistoryModelを取得する
                pshMdl = __getPrjStatusHistoryModel(paramMdl, todoSid, hisSid, userSid, now);
            }

            //更新用のPrjTododataModelを取得する
            PrjTododataModel ptMdl =
                __getPrjTododataModel(paramMdl, todoSid, kanriNo, hisSid, userSid, now);

            //更新用のPrjTodomemberModelのリストを取得する
            List<PrjTodomemberModel> memberList =
                __getPrjTodomemberList(paramMdl, todoSid, userSid, now);

            //テンポラリディレクトリパスを取得
            CommonBiz cmnBiz = new CommonBiz();
            tempDir = cmnBiz.getTempDir(tempRoot, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);
            log__.debug("テンポラリディレクトリ = " + tempDir);

            //テンポラリディレクトリパスにある添付ファイルを全て登録
            List<String> binList =
                cmnBiz.insertBinInfo(con__, tempDir, appRoot, cntCon, userSid, now);

            //TODO情報を更新する
            projectDao.updateTodo(ptMdl, memberList, pshMdl, binList);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {

                con__.commit();

                if (paramMdl.getPrj050MailSend() != GSConstProject.NOT_SEND) {
                    GsMessage gsMsg = new GsMessage(reqMdl__);
                    PrjCommonBiz cmnBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
                    PrjSmailParamModel smlParamModel = new PrjSmailParamModel();
                    smlParamModel.setCmdMode(paramMdl.getPrj050cmdMode());
                    smlParamModel.setPrjSid(projectSid);
                    smlParamModel.setTodoSid(todoSid);
                    smlParamModel.setUsrSid(userSid);
                    smlParamModel.setTarget(paramMdl.getPrj050MailSend());
                    smlParamModel.setHistory(statusCmt);
                    smlParamModel.setAppRoot(appRoot);
                    smlParamModel.setTempDir(tempDir);

                    PrjSmailModel param = cmnBiz.getSmailParamMdl(smlParamModel);

                    cmnBiz.sendTodoEditMail(
                            con__, cntCon, param, appRoot, pconfig);
                }

            } else {
                JDBCUtil.rollback(con__);
            }
        }
        return dataexist;
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param appRoot ルートディレクトリ
     * @param tempRoot テンポラリディレクトリ
     * @param pconfig プラグインコンフィグ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws Exception その他例外
     */
    public void doInsert(Prj050knParamModel paramMdl,
                          MlCountMtController cntCon,
                          int userSid,
                          String appRoot,
                          String tempRoot,
                          PluginConfig pconfig)
        throws SQLException, IOToolsException, IOException, Exception {

        boolean commitFlg = false;
        UDate now = new UDate();

        //プロジェクトSID
        int projectSid = paramMdl.getPrj050prjSid();
        int todoSid = -1;
        String tempDir = null;
        String statusCmt = null;

        //詳細入力 状態変更理由
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            statusCmt = paramMdl.getPrj050statusCmt();

        //簡易入力 状態変更理由
        } else {
            statusCmt = paramMdl.getPrj050statusCmtEasy();
        }

        try {

            con__.setAutoCommit(false);

            //TODOSID採番
            todoSid = (int) cntCon.getSaibanNumber(GSConstProject.SBNSID_PROJECT,
                                                           GSConstProject.SBNSID_SUB_TODO,
                                                           userSid);
            //管理番号を採番
            ProjectUpdateDao projectDao = new ProjectUpdateDao(con__);
            int kanriNo = (int) cntCon.getSaibanNumber(GSConstProject.SBNSID_PROJECT,
                           projectSid + GSConstProject.SBNSID_SUB_KANRI,
                           userSid);
            //変更履歴SIDを採番
            int hisSid = (int) cntCon.getSaibanNumber(GSConstProject.SBNSID_PROJECT,
                          todoSid + GSConstProject.SBNSID_SUB_HISTORY,
                          userSid);

            //更新用のPrjTododataModelを取得する
            PrjTododataModel ptMdl =
                __getPrjTododataModel(paramMdl, todoSid, kanriNo, hisSid, userSid, now);

            //更新用のPrjTodomemberModelのリストを取得する
            List<PrjTodomemberModel> memberList =
                __getPrjTodomemberList(paramMdl, todoSid, userSid, now);

            //更新用のPrjStatusHistoryModelを取得する
            PrjStatusHistoryModel pshMdl =
                __getPrjStatusHistoryModel(paramMdl, todoSid, hisSid, userSid, now);

            //テンポラリディレクトリパスを取得
            CommonBiz cmnBiz = new CommonBiz();
            tempDir = cmnBiz.getTempDir(tempRoot, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);
            log__.debug("テンポラリディレクトリ = " + tempDir);

            //テンポラリディレクトリパスにある添付ファイルを全て登録
            List<String> binList =
                cmnBiz.insertBinInfo(con__, tempDir, appRoot, cntCon, userSid, now);

            //TODO情報を登録する
            projectDao.insertTodo(ptMdl, memberList, pshMdl, binList);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {

                con__.commit();

                if (paramMdl.getPrj050MailSend() != GSConstProject.NOT_SEND) {
                    GsMessage gsMsg = new GsMessage(reqMdl__);
                    PrjCommonBiz cmnBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
                    PrjSmailParamModel smlParamModel = new PrjSmailParamModel();
                    smlParamModel.setCmdMode(paramMdl.getPrj050cmdMode());
                    smlParamModel.setPrjSid(projectSid);
                    smlParamModel.setTodoSid(todoSid);
                    smlParamModel.setUsrSid(userSid);
                    smlParamModel.setTarget(paramMdl.getPrj050MailSend());
                    smlParamModel.setHistory(statusCmt);
                    smlParamModel.setAppRoot(appRoot);
                    smlParamModel.setTempDir(tempDir);
                    PrjSmailModel param = cmnBiz.getSmailParamMdl(smlParamModel);

                    cmnBiz.sendTodoEditMail(
                            con__, cntCon, param, appRoot, pconfig);
                }

            } else {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] 更新用のPrjStatusHistoryModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param todoSid TODOSID
     * @param hisSid 変更履歴SID
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return PrjStatusHistoryModel
     */
    private PrjStatusHistoryModel __getPrjStatusHistoryModel(
        Prj050knParamModel paramMdl,
        int todoSid,
        int hisSid,
        int userSid,
        UDate now) {

        //プロジェクトSID
        int projectSid = paramMdl.getPrj050prjSid();

        PrjStatusHistoryModel pshMdl = new PrjStatusHistoryModel();
        pshMdl.setPrjSid(projectSid);
        pshMdl.setPtdSid(todoSid);
        pshMdl.setPshSid(hisSid);

        //詳細入力
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            pshMdl.setPtsSid(paramMdl.getPrj050status());
            pshMdl.setPshReason(paramMdl.getPrj050statusCmt());
        //簡易入力
        } else {
            pshMdl.setPtsSid(paramMdl.getPrj050statusEasy());
            pshMdl.setPshReason(paramMdl.getPrj050statusCmtEasy());
        }
        pshMdl.setPshAuid(userSid);
        pshMdl.setPshAdate(now);
        pshMdl.setPshEuid(userSid);
        pshMdl.setPshEdate(now);
        return pshMdl;
    }

    /**
     * <br>[機  能] 更新用のPrjTodomemberModelのリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param todoSid TODOSID
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return List in PrjTodomemberModel
     */
    private List<PrjTodomemberModel> __getPrjTodomemberList(
        Prj050knParamModel paramMdl,
        int todoSid,
        int userSid,
        UDate now) {

        //プロジェクトSID
        int projectSid = paramMdl.getPrj050prjSid();

        List<PrjTodomemberModel> memberList = new ArrayList<PrjTodomemberModel>();
        String[] tantos = paramMdl.getPrj050hdnTanto();
        PrjTodomemberModel memberMdl = null;

        if (tantos != null) {
            for (String member : tantos) {
                memberMdl = new PrjTodomemberModel();
                memberMdl.setPrjSid(projectSid);
                memberMdl.setPtdSid(todoSid);
                memberMdl.setUsrSid(NullDefault.getInt(member, -1));
                memberMdl.setPtmEmployeeKbn(GSConstProject.KBN_PROJECT_MEMBER_INNER);
                memberMdl.setPtmAuid(userSid);
                memberMdl.setPtmAdate(now);
                memberMdl.setPtmEuid(userSid);
                memberMdl.setPtmEdate(now);
                memberList.add(memberMdl);
            }
        }
        return memberList;
    }

    /**
     * <br>[機  能] 更新用のPrjTododataModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050knParamModel
     * @param todoSid TODOSID
     * @param kanriNo 管理番号
     * @param hisSid 変更履歴SID
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return PrjTododataModel 更新用のPrjTododataModel
     */
    private PrjTododataModel __getPrjTododataModel(
        Prj050knParamModel paramMdl,
        int todoSid,
        int kanriNo,
        int hisSid,
        int userSid,
        UDate now) {

        //プロジェクトSID
        int projectSid = paramMdl.getPrj050prjSid();
        UDate kaisiYotei = new UDate();
        UDate kigen = new UDate();
        UDate kaisiJisseki = new UDate();
        UDate syuryoJisseki = new UDate();

        //詳細入力
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            //開始予定年月日
            kaisiYotei = PrjCommonBiz.createUDate(
                    NullDefault.getInt(paramMdl.getPrj050kaisiYoteiYear(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kaisiYoteiMonth(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kaisiYoteiDay(), -1));
            //期限年月日
            kigen = PrjCommonBiz.createUDate(
                    NullDefault.getInt(paramMdl.getPrj050kigenYear(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kigenMonth(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kigenDay(), -1));
            //開始実績年月日
            kaisiJisseki = PrjCommonBiz.createUDate(
                    NullDefault.getInt(paramMdl.getPrj050kaisiJissekiYear(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kaisiJissekiMonth(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kaisiJissekiDay(), -1));
            //終了実績年月日
            syuryoJisseki = PrjCommonBiz.createUDate(
                    NullDefault.getInt(paramMdl.getPrj050syuryoJissekiYear(), -1),
                    NullDefault.getInt(paramMdl.getPrj050syuryoJissekiMonth(), -1),
                    NullDefault.getInt(paramMdl.getPrj050syuryoJissekiDay(), -1));
        } else {
            //期限年月日
            kigen = PrjCommonBiz.createUDate(
                    NullDefault.getInt(paramMdl.getPrj050kigenYearEasy(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kigenMonthEasy(), -1),
                    NullDefault.getInt(paramMdl.getPrj050kigenDayEasy(), -1));
        }

        PrjTododataModel ptMdl = new PrjTododataModel();

        //詳細入力
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            ptMdl.setPrjSid(projectSid);
            ptMdl.setPtdSid(todoSid);
            ptMdl.setPtdNo(kanriNo);
            ptMdl.setPtdCategory(paramMdl.getPrj050cate());
            ptMdl.setPtdTitle(paramMdl.getPrj050title());
            ptMdl.setPtdDatePlan(kaisiYotei);
            ptMdl.setPrjDateLimit(kigen);
            ptMdl.setPtdDateStart(kaisiJisseki);
            ptMdl.setPtdDateEnd(syuryoJisseki);
            ptMdl.setPtdPlanKosu(NullDefault.getBigDecimal(paramMdl.getPrj050yoteiKosu(), null));
            ptMdl.setPtdResultsKosu(NullDefault.getBigDecimal(
                    paramMdl.getPrj050jissekiKosu(), null));
            ptMdl.setPtdAlarmKbn(paramMdl.getPrj050keikoku());
            ptMdl.setPtdImportance(paramMdl.getPrj050juyou());
            ptMdl.setPshSid(hisSid);
            ptMdl.setPtsSid(paramMdl.getPrj050status());
            ptMdl.setPtdContent(paramMdl.getPrj050naiyo());
            ptMdl.setPtdAuid(userSid);
            ptMdl.setPtdAdate(now);
            ptMdl.setPtdEuid(userSid);
            ptMdl.setPtdEdate(now);
            return ptMdl;

        //簡易入力
        } else {
            ptMdl.setPrjSid(projectSid);
            ptMdl.setPtdSid(todoSid);
            ptMdl.setPtdNo(kanriNo);
            ptMdl.setPtdCategory(paramMdl.getPrj050cate());
            ptMdl.setPtdTitle(paramMdl.getPrj050titleEasy());
            ptMdl.setPrjDateLimit(kigen);
            ptMdl.setPtdAlarmKbn(paramMdl.getPrj050keikoku());
            ptMdl.setPtdImportance(paramMdl.getPrj050juyouEasy());
            ptMdl.setPshSid(hisSid);
            ptMdl.setPtsSid(paramMdl.getPrj050statusEasy());
            ptMdl.setPtdContent(paramMdl.getPrj050naiyoEasy());
            ptMdl.setPtdAuid(userSid);
            ptMdl.setPtdAdate(now);
            ptMdl.setPtdEuid(userSid);
            ptMdl.setPtdEdate(now);
            return ptMdl;
        }
   }
}