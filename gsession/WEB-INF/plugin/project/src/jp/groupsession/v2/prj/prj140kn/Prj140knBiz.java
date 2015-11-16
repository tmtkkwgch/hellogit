package jp.groupsession.v2.prj.prj140kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.ProjectUpdateBiz;
import jp.groupsession.v2.prj.dao.PrjMembersTmpDao;
import jp.groupsession.v2.prj.dao.PrjPrjdataTmpDao;
import jp.groupsession.v2.prj.dao.PrjPrjstatusTmpDao;
import jp.groupsession.v2.prj.dao.PrjTodocategoryTmpDao;
import jp.groupsession.v2.prj.dao.PrjTodostatusTmpDao;
import jp.groupsession.v2.prj.dao.ProjectUpdateDao;
import jp.groupsession.v2.prj.model.PrjMembersTmpModel;
import jp.groupsession.v2.prj.model.PrjPrjdataTmpModel;
import jp.groupsession.v2.prj.model.PrjPrjstatusModel;
import jp.groupsession.v2.prj.model.PrjPrjstatusTmpModel;
import jp.groupsession.v2.prj.model.PrjTodocategoryModel;
import jp.groupsession.v2.prj.model.PrjTodocategoryTmpModel;
import jp.groupsession.v2.prj.model.PrjTodostatusModel;
import jp.groupsession.v2.prj.model.PrjTodostatusTmpModel;
import jp.groupsession.v2.prj.model.ProjectItemModel;
import jp.groupsession.v2.prj.model.ProjectStatusModel;
import jp.groupsession.v2.prj.model.ProjectStatusTmpModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] プロジェクト管理 プロジェクトテンプレート登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj140knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj140knBiz.class);

    /** ヘルプモード テンプレート選択*/
    public static final String HELP_MODE_TMP_SELECT = "4";

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl リクエストモデル
     */
    public Prj140knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param pconfig プラグイン設定情報
     * @param userSid セッションユーザSID
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void setInitData(Prj140knParamModel paramMdl, PluginConfig pconfig,
                             int userSid, String rootDir)
        throws SQLException, IOToolsException {

        int tmpMode = paramMdl.getPrjTmpMode();

        //テンプレート選択モード
        if (tmpMode == GSConstProject.MODE_TMP_SELECT) {
            __setSelectData(paramMdl, rootDir);

            //ヘルプパラメータを設定する
            paramMdl.setPrj140cmdMode(HELP_MODE_TMP_SELECT);
        } else {
            __setInputData(paramMdl, rootDir);
        }

        //ショートメール使用有無
        paramMdl.setUseSmail(pconfig.getPlugin(GSConstProject.PLUGIN_ID_SMAIL) != null);
    }

    /**
     * <br>[機  能] 選択テンプレート内容を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private void __setSelectData(Prj140knParamModel paramMdl, String rootDir)
        throws SQLException, IOToolsException {

        //プロジェクトテンプレートSID取得
        int prtSid = paramMdl.getPrtSid();

        //プロジェクトテンプレート状態
        ProjectStatusTmpModel prjStatusMdl = new ProjectStatusTmpModel();

        //プロジェクト状態_テンプレートを取得
        PrjPrjstatusTmpDao ppsDao = new PrjPrjstatusTmpDao(con__);
        prjStatusMdl.setPrjStatusList(ppsDao.selectTmpProjects(prtSid));

        //TODOカテゴリ_テンプレートを取得
        PrjTodocategoryTmpDao ptcDao = new PrjTodocategoryTmpDao(con__);
        prjStatusMdl.setTodoCateList(ptcDao.selectTmpProjects(prtSid));

        //TODO状態_テンプレートを取得
        PrjTodostatusTmpDao pts = new PrjTodostatusTmpDao(con__);
        prjStatusMdl.setTodoStatusList(pts.selectTmpProjects(prtSid));

        paramMdl.setPrjStatusTmpMdl(prjStatusMdl);

        //プロジェクトテンプレート情報を取得する
        GsMessage gsMsg = new GsMessage(reqMdl__);
        PrjPrjdataTmpDao ppDao = new PrjPrjdataTmpDao(con__);
        ProjectItemModel piMdl = ppDao.getProjectTmpInfo(prtSid);
        PrjCommonBiz prjCmnBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);

        //テンプレート名称
        paramMdl.setPrj140prtTmpName(NullDefault.getString(piMdl.getPrtTmpName(), ""));
        //プロジェクトID
        paramMdl.setPrj140prtId(piMdl.getProjectId());
        //プロジェクト名称
        paramMdl.setPrj140prtName(piMdl.getProjectName());
        //プロジェクト略称
        paramMdl.setPrj140prtNameS(piMdl.getProjectRyakuName());
        //予算
        long yosan = piMdl.getYosan();
        if (yosan > GSConstCommon.NUM_INIT) {
            //予算
            paramMdl.setPrj140yosan(String.valueOf(yosan));
            paramMdl.setYosan(
                    prjCmnBiz.getYosanStr(
                            NullDefault.getLong(paramMdl.getPrj140yosan(), -1)));
        }
        //公開・非公開
        paramMdl.setPrj140koukai(piMdl.getKoukaiKbn());
        paramMdl.setKoukai(
                prjCmnBiz.getKoukaiKbnName(
                        paramMdl.getPrj140koukai()));

        //開始年月日
        UDate start = piMdl.getStartDate();
        if (start != null) {
            paramMdl.setPrj140startYear(String.valueOf(start.getYear()));
            paramMdl.setPrj140startMonth(String.valueOf(start.getMonth()));
            paramMdl.setPrj140startDay(String.valueOf(start.getIntDay()));

            //開始
            String startDate =
                paramMdl.getPrj140startYear()
                + "/"
                + StringUtil.toDecFormat(paramMdl.getPrj140startMonth(), "00")
                + "/"
                + StringUtil.toDecFormat(paramMdl.getPrj140startDay(), "00");

            paramMdl.setStartDate(startDate);
        }

        //終了年月日
        UDate end = piMdl.getEndDate();
        if (end != null) {
            paramMdl.setPrj140endYear(String.valueOf(end.getYear()));
            paramMdl.setPrj140endMonth(String.valueOf(end.getMonth()));
            paramMdl.setPrj140endDay(String.valueOf(end.getIntDay()));

            //終了
            String endDate =
                paramMdl.getPrj140endYear()
                + "/"
                + StringUtil.toDecFormat(paramMdl.getPrj140endMonth(), "00")
                + "/"
                + StringUtil.toDecFormat(paramMdl.getPrj140endDay(), "00");
            paramMdl.setEndDate(endDate);
        }
        //状態
        paramMdl.setPrj140status(piMdl.getStatus());
        String status = "";
        List<PrjPrjstatusTmpModel> prjStatusList = prjStatusMdl.getPrjStatusList();
        for (PrjPrjstatusTmpModel ppsMdl : prjStatusList) {
            if (ppsMdl.getPttSid() == paramMdl.getPrj140status()) {
                status = ppsMdl.getPttRate() + "% (" + ppsMdl.getPttName() + ")";
                break;
            }
        }
        paramMdl.setStatus(status);

        //目標・目的
        paramMdl.setPrj140mokuhyou(piMdl.getMokuhyou());
        paramMdl.setMokuhyou(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj140mokuhyou()));

        //内容
        paramMdl.setPrj140naiyou(piMdl.getNaiyou());
        paramMdl.setNaiyou(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj140naiyou()));

        //編集権限
        paramMdl.setPrj140kengen(piMdl.getEditKengen());
        paramMdl.setKengen(prjCmnBiz.getTodoKengenName(paramMdl.getPrj140kengen()));

        //編集権限
        paramMdl.setPrj140smailKbn(piMdl.getPrjMailKbn());
        paramMdl.setSmailKbn(prjCmnBiz.getSmailKbnStr(paramMdl.getPrj140smailKbn()));

        //所属メンバーを取得
        PrjMembersTmpDao pmDao = new PrjMembersTmpDao(con__);
        List<PrjMembersTmpModel> pmList = pmDao.getPrjMembersTmp(prtSid);

        List<PrjMembersTmpModel> adminList = new ArrayList<PrjMembersTmpModel>();
        String[] member = new String[pmList.size()];

        int index = 0;
        for (PrjMembersTmpModel pmMdl : pmList) {
            if (pmMdl.getPmtAdminKbn() == GSConstProject.KBN_POWER_ADMIN) {
                adminList.add(pmMdl);
            }
            member[index] =
                String.valueOf(pmMdl.getUsrSid()
                        + GSConst.GSESSION2_ID
                        + NullDefault.getString(pmMdl.getPmtMemKey(), ""));
            index++;
        }

        String[] admin = new String[adminList.size()];
        index = 0;
        for (PrjMembersTmpModel pmMdl : adminList) {
            admin[index] = String.valueOf(pmMdl.getUsrSid());
            index++;
        }
        //所属メンバー
        paramMdl.setPrj140hdnMember(member);
        //プロジェクト管理者
        paramMdl.setPrj140hdnAdmin(admin);
        //所属メンバーラベル
        UserBiz userBiz = new UserBiz();
        paramMdl.setSyozokuMemberLabel(
                userBiz.getUserPrjLabelList(
                        con__, paramMdl.getPrj140hdnMember()));
        //管理者メンバーラベル
        paramMdl.setAdminMemberLabel(userBiz.getUserLabelList(con__, paramMdl.getPrj140hdnAdmin()));
    }

//    /**
//     * <br>[機  能] プロジェクト登録画面へのコピー値をセット
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param paramMdl Prj140knParamModel
//     */
//    private void __setPrjAddDspParam(Prj140knParamModel paramMdl) {
//
//        //マイプロジェクト区分
//        paramMdl.setPrj020prjMyKbn(GSConstProject.KBN_MY_PRJ_DEF);
//        //プロジェクトID
//        paramMdl.setPrj020prjId(NullDefault.getString(paramMdl.getPrj140prtId(), ""));
//        //プロジェクト名称
//        paramMdl.setPrj020prjName(NullDefault.getString(paramMdl.getPrj140prtName(), ""));
//        //プロジェクト略称
//        paramMdl.setPrj020prjNameS(NullDefault.getString(paramMdl.getPrj140prtNameS(), ""));
//        //予算
//        paramMdl.setPrj020yosan(NullDefault.getString(paramMdl.getPrj140yosan(), ""));
//        //公開・非公開
//        paramMdl.setPrj020koukai(paramMdl.getPrj140koukai());
//
//        //開始年月日
//        paramMdl.setPrj020startYear(NullDefault.getString(paramMdl.getPrj140startYear(), ""));
//        paramMdl.setPrj020startMonth(NullDefault.getString(paramMdl.getPrj140startMonth(), ""));
//        paramMdl.setPrj020startDay(NullDefault.getString(paramMdl.getPrj140startDay(), ""));
//
//        //終了年月日
//        paramMdl.setPrj020endYear(NullDefault.getString(paramMdl.getPrj140endYear(), ""));
//        paramMdl.setPrj020endMonth(NullDefault.getString(paramMdl.getPrj140endMonth(), ""));
//        paramMdl.setPrj020endDay(NullDefault.getString(paramMdl.getPrj140endDay(), ""));
//
//        //状態
//        paramMdl.setPrj020status(paramMdl.getPrj140status());
//        //目標・目的
//        paramMdl.setPrj020mokuhyou(NullDefault.getString(paramMdl.getPrj140mokuhyou(), ""));
//        //内容
//        paramMdl.setPrj020naiyou(NullDefault.getString(paramMdl.getPrj140naiyou(), ""));
//        //編集権限
//        paramMdl.setPrj020kengen(paramMdl.getPrj140kengen());
//
//        //所属メンバー
//        paramMdl.setPrj020hdnMember(paramMdl.getPrj140hdnMember());
//        //プロジェクト管理者
//        paramMdl.setPrj020hdnAdmin(paramMdl.getPrj140hdnAdmin());
//    }

    /**
     * <br>[機  能] 入力内容確認データをセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private void __setInputData(Prj140knParamModel paramMdl, String rootDir)
        throws SQLException, IOToolsException {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        PrjCommonBiz prjCmnBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
        //プロジェクト状態
        ProjectStatusTmpModel prjStatus =
            PrjCommonBiz.getProjectStatusTmpModel(rootDir, reqMdl__);

        //予算
        paramMdl.setYosan(
                prjCmnBiz.getYosanStr(
                        NullDefault.getLong(paramMdl.getPrj140yosan(), -1)));

        //公開・非公開
        paramMdl.setKoukai(
                prjCmnBiz.getKoukaiKbnName(
                        paramMdl.getPrj140koukai()));

        //開始
        String startDate = "";
        if (!paramMdl.getPrj140startYear().equals("")
            && !paramMdl.getPrj140startMonth().equals("")
            && !paramMdl.getPrj140startDay().equals("")) {

            startDate = paramMdl.getPrj140startYear() + "/"
                      + StringUtil.toDecFormat(paramMdl.getPrj140startMonth(), "00") + "/"
                      + StringUtil.toDecFormat(paramMdl.getPrj140startDay(), "00");
        }
        paramMdl.setStartDate(startDate);

        //終了
        String endDate = "";
        if (!paramMdl.getPrj140endYear().equals("")
            && !paramMdl.getPrj140endMonth().equals("")
            && !paramMdl.getPrj140endDay().equals("")) {

            endDate = paramMdl.getPrj140endYear() + "/"
                    + StringUtil.toDecFormat(paramMdl.getPrj140endMonth(), "00") + "/"
                    + StringUtil.toDecFormat(paramMdl.getPrj140endDay(), "00");
        }
        paramMdl.setEndDate(endDate);

        //状態
        String status = "";
        List<PrjPrjstatusTmpModel> prjStatusList = prjStatus.getPrjStatusList();
        for (PrjPrjstatusTmpModel ppsMdl : prjStatusList) {
            if (ppsMdl.getPttSid() == paramMdl.getPrj140status()) {
                status = ppsMdl.getPttRate() + "% (" + ppsMdl.getPttName() + ")";
                break;
            }
        }
        paramMdl.setStatus(status);

        //目標・目的
        paramMdl.setMokuhyou(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj140mokuhyou()));
        //内容
        paramMdl.setNaiyou(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj140naiyou()));
        //所属メンバーラベル
        UserBiz userBiz = new UserBiz();
        paramMdl.setSyozokuMemberLabel(
                userBiz.getUserPrjLabelList(
                        con__, paramMdl.getPrj140hdnMember()));
        //管理者メンバーラベル
        paramMdl.setAdminMemberLabel(userBiz.getUserLabelList(con__, paramMdl.getPrj140hdnAdmin()));
        //TODOカテゴリ、TODO状態
        paramMdl.setPrjStatusTmpMdl(prjStatus);
        //編集権限
        paramMdl.setKengen(prjCmnBiz.getTodoKengenName(paramMdl.getPrj140kengen()));
        //ショートメール通知先
        paramMdl.setSmailKbn(prjCmnBiz.getSmailKbnStr(paramMdl.getPrj140smailKbn()));
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void doAddEdit(Prj140knParamModel paramMdl,
                           MlCountMtController cntCon,
                           int userSid,
                           String rootDir)
        throws SQLException, IOToolsException {

        //プロジェクト状態
        ProjectStatusTmpModel prjStatus =
            PrjCommonBiz.getProjectStatusTmpModel(rootDir, reqMdl__);

        if (paramMdl.getPrtSid() < 1) {

            //登録
            __doInsert(paramMdl, cntCon, userSid, prjStatus);

        } else {

            //更新
            __doUpdate(paramMdl, userSid, prjStatus);
        }
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param prjStatus プロジェクト状態
     * @throws SQLException SQL実行例外
     */
    private void __doInsert(Prj140knParamModel paramMdl,
                             MlCountMtController cntCon,
                             int userSid,
                             ProjectStatusTmpModel prjStatus) throws SQLException {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {

            con__.setAutoCommit(false);

            //プロジェクトテンプレートSID採番
            int prtSid =
                (int) cntCon.getSaibanNumber(
                        GSConstProject.SBNSID_PROJECT,
                        GSConstProject.SBNSID_SUB_PROJECT_TMP,
                        userSid);

            //プロジェクトテンプレート更新Model
            PrjPrjdataTmpModel ppMdl = __getPrjUpdateMdl(prtSid, paramMdl, userSid, now);

            //プロジェクトテンプレートメンバー更新Modelリスト
            List<PrjMembersTmpModel> memberList =
                __getMemberUpdateList(prtSid, paramMdl, userSid, now);

            //プロジェクトテンプレート情報を登録する
            ProjectUpdateDao projectDao = new ProjectUpdateDao(con__);
            projectDao.insertProjectTmp(ppMdl, prjStatus, memberList);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con__.commit();
            } else {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] 更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param userSid ログインユーザSID
     * @param prjStatus プロジェクト状態
     * @throws SQLException SQL実行例外
     */
    private void __doUpdate(
            Prj140knParamModel paramMdl, int userSid, ProjectStatusTmpModel prjStatus)
        throws SQLException {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {

            con__.setAutoCommit(false);

            //プロジェクトテンプレートSID
            int prtSid = paramMdl.getPrtSid();

            //プロジェクトテンプレート更新Model
            PrjPrjdataTmpModel ppMdl = __getPrjUpdateMdl(prtSid, paramMdl, userSid, now);

            //プロジェクトテンプレートメンバー更新Modelリスト
            List<PrjMembersTmpModel> memberList =
                __getMemberUpdateList(prtSid, paramMdl, userSid, now);

            //プロジェクト情報を更新する
            ProjectUpdateBiz projectBiz = new ProjectUpdateBiz(con__);
            projectBiz.updateProjectTmp(ppMdl, prjStatus, memberList);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con__.commit();
            } else {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] 更新用のPrjPrjdataTmpModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param prtSid プロジェクトテンプレートSID
     * @param paramMdl Prj140knParamModel
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return PrjPrjdataModel
     */
    private PrjPrjdataTmpModel __getPrjUpdateMdl(int prtSid,
                                                  Prj140knParamModel paramMdl,
                                                  int userSid,
                                                  UDate now) {

        UDate startDate = PrjCommonBiz.createUDate(
                NullDefault.getInt(paramMdl.getPrj140startYear(), -1),
                NullDefault.getInt(paramMdl.getPrj140startMonth(), -1),
                NullDefault.getInt(paramMdl.getPrj140startDay(), -1));

        UDate andDate = PrjCommonBiz.createUDate(
                NullDefault.getInt(paramMdl.getPrj140endYear(), -1),
                NullDefault.getInt(paramMdl.getPrj140endMonth(), -1),
                NullDefault.getInt(paramMdl.getPrj140endDay(), -1));

        PrjPrjdataTmpModel ppMdl = new PrjPrjdataTmpModel();
        ppMdl.setPrtSid(prtSid);

        int prtKbn = -1;
        int prtUsrSid = 0;
        if (paramMdl.getPrjTmpMode() == GSConstProject.MODE_TMP_KOJIN) {
            prtKbn = GSConstProject.PRT_KBN_KOJIN;
            prtUsrSid = userSid;
        } else if (paramMdl.getPrjTmpMode() == GSConstProject.MODE_TMP_KYOYU) {
            prtKbn = GSConstProject.PRT_KBN_KYOYU;
        }
        ppMdl.setPrtKbn(prtKbn);
        ppMdl.setPrtTmpName(NullDefault.getString(paramMdl.getPrj140prtTmpName(), ""));
        ppMdl.setPrtUsrSid(prtUsrSid);
        ppMdl.setPrtId(NullDefault.getString(paramMdl.getPrj140prtId(), ""));
        ppMdl.setPrtName(NullDefault.getString(paramMdl.getPrj140prtName(), ""));
        ppMdl.setPrtNameShort(NullDefault.getString(paramMdl.getPrj140prtNameS(), ""));
        ppMdl.setPrtYosan(NullDefault.getInt(paramMdl.getPrj140yosan(), GSConstCommon.NUM_INIT));
        ppMdl.setPrtKoukaiKbn(paramMdl.getPrj140koukai());
        ppMdl.setPrtDateStart(startDate);
        ppMdl.setPrtDateEnd(andDate);
        ppMdl.setPrtStatusSid(paramMdl.getPrj140status());
        ppMdl.setPrtTarget(NullDefault.getString(paramMdl.getPrj140mokuhyou(), ""));
        ppMdl.setPrtContent(NullDefault.getString(paramMdl.getPrj140naiyou(), ""));
        ppMdl.setPrtEdit(paramMdl.getPrj140kengen());
        ppMdl.setPrtMailKbn(paramMdl.getPrj140smailKbn());
        ppMdl.setPrtAuid(userSid);
        ppMdl.setPrtAdate(now);
        ppMdl.setPrtEuid(userSid);
        ppMdl.setPrtEdate(now);
        return ppMdl;
    }

    /**
     * <br>[機  能] 更新用のPrjMembersTmpModelのリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param prtSid プロジェクトテンプレートSID
     * @param paramMdl Prj140knParamModel
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return List in PrjMembersModel
     */
    private List<PrjMembersTmpModel> __getMemberUpdateList(int prtSid,
                                                            Prj140knParamModel paramMdl,
                                                            int userSid,
                                                            UDate now) {

        List<PrjMembersTmpModel> memberList = new ArrayList<PrjMembersTmpModel>();
        String[] members = paramMdl.getPrj140hdnMember();
        String[] spUsrSidList = null;
        HashMap<String, String> spUsrKeyMap = null;

        if (members != null && members.length > 0) {

            int idx = 0;
            spUsrSidList = new String[members.length];
            spUsrKeyMap = new HashMap<String, String>();

            for (String hdn : members) {

                String[] splitStr = hdn.split(GSConst.GSESSION2_ID);
                spUsrSidList[idx] = String.valueOf(splitStr[0]);

                if (splitStr.length > 1) {
                    spUsrKeyMap.put(spUsrSidList[idx], splitStr[1]);
                } else {
                    spUsrKeyMap.put(spUsrSidList[idx], "");
                }
                idx += 1;
            }
        }

        String[] admins = paramMdl.getPrj140hdnAdmin();
        PrjMembersTmpModel memberMdl = null;

        if (spUsrSidList != null) {
            for (String member : spUsrSidList) {
                int adminKbn = GSConstProject.KBN_POWER_NORMAL;
                if (admins != null) {
                    for (String admin : admins) {
                        if (member.equals(admin)) {
                            adminKbn = GSConstProject.KBN_POWER_ADMIN;
                            break;
                        }
                    }
                }
                memberMdl = new PrjMembersTmpModel();
                memberMdl.setPrtSid(prtSid);
                memberMdl.setUsrSid(NullDefault.getInt(member, -1));
                memberMdl.setPmtEmployeeKbn(GSConstProject.KBN_PROJECT_MEMBER_INNER);
                memberMdl.setPmtAdminKbn(adminKbn);
                memberMdl.setPmtAuid(userSid);
                memberMdl.setPmtAdate(now);
                memberMdl.setPmtEuid(userSid);
                memberMdl.setPmtEdate(now);
                memberMdl.setPmtMemKey(spUsrKeyMap.get(member));
                memberList.add(memberMdl);
            }
        }
        return memberList;
    }

    /**
     * <br>[機  能] 表示しているテンプレートを適用する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @return ProjectStatusModel
     * @throws SQLException SQL実行例外
     */
    private ProjectStatusModel __getSelectData(Prj140knParamModel paramMdl) throws SQLException {

        int prtSid = paramMdl.getPrtSid();

        //プロジェクト状態
        ProjectStatusModel prjStatusMdl = new ProjectStatusModel();

        //プロジェクト状態_テンプレートを取得
        PrjPrjstatusTmpDao pptDao = new PrjPrjstatusTmpDao(con__);
        List<PrjPrjstatusTmpModel> prjStsTmpList = pptDao.selectTmpProjects(prtSid);

        //プロジェクト状態を設定
        List<PrjPrjstatusModel> prjStsList = new ArrayList<PrjPrjstatusModel>();
        for (PrjPrjstatusTmpModel pptMdl : prjStsTmpList) {
            PrjPrjstatusModel bean = new PrjPrjstatusModel();
            bean.setPrjSid(0);
            bean.setPrsSid(pptMdl.getPttSid());
            bean.setPrsSort(pptMdl.getPttSort());
            bean.setPrsName(NullDefault.getString(pptMdl.getPttName(), ""));
            bean.setPrsRate(pptMdl.getPttRate());
            bean.setPrsAuid(pptMdl.getPttAuid());
            bean.setPrsAdate(NullDefault.getUDate(pptMdl.getPttAdate(), null));
            bean.setPrsEuid(pptMdl.getPttEuid());
            bean.setPrsEdate(NullDefault.getUDate(pptMdl.getPttEdate(), null));
            prjStsList.add(bean);
        }
        prjStatusMdl.setPrjStatusList(prjStsList);

        //TODOカテゴリ_テンプレートを取得
        PrjTodocategoryTmpDao pctDao = new PrjTodocategoryTmpDao(con__);
        List<PrjTodocategoryTmpModel> prjTodoCateList = pctDao.selectTmpProjects(prtSid);

        //TODOカテゴリを設定
        List<PrjTodocategoryModel> prjCateList = new ArrayList<PrjTodocategoryModel>();
        for (PrjTodocategoryTmpModel pctMdl : prjTodoCateList) {
            PrjTodocategoryModel bean = new PrjTodocategoryModel();
            bean.setPrjSid(0);
            bean.setPtcCategorySid(pctMdl.getPctCategorySid());
            bean.setPtcSort(pctMdl.getPctSort());
            bean.setPtcName(NullDefault.getString(pctMdl.getPctName(), ""));
            bean.setPtcAuid(pctMdl.getPctAuid());
            bean.setPtcAdate(NullDefault.getUDate(pctMdl.getPctAdate(), null));
            bean.setPtcEuid(pctMdl.getPctEuid());
            bean.setPtcEdate(NullDefault.getUDate(pctMdl.getPctEdate(), null));
            prjCateList.add(bean);
        }
        prjStatusMdl.setTodoCateList(prjCateList);

        //TODO状態_テンプレートを取得
        PrjTodostatusTmpDao prtDao = new PrjTodostatusTmpDao(con__);
        List<PrjTodostatusTmpModel> prjTodoStsList = prtDao.selectTmpProjects(prtSid);

        //TODOテンプレートを設定
        List<PrjTodostatusModel> todoStsList = new ArrayList<PrjTodostatusModel>();
        for (PrjTodostatusTmpModel pstMdl : prjTodoStsList) {
            PrjTodostatusModel bean = new PrjTodostatusModel();
            bean.setPrjSid(0);
            bean.setPtsSid(pstMdl.getPstSid());
            bean.setPtsName(NullDefault.getString(pstMdl.getPstName(), ""));
            bean.setPtsRate(pstMdl.getPstRate());
            bean.setPtsSort(pstMdl.getPstSort());
            bean.setPtsAuid(pstMdl.getPstAuid());
            bean.setPtsAdate(NullDefault.getUDate(pstMdl.getPstAdate(), null));
            bean.setPtsEuid(pstMdl.getPstEuid());
            bean.setPtsEdate(NullDefault.getUDate(pstMdl.getPstAdate(), null));
            todoStsList.add(bean);
        }
        prjStatusMdl.setTodoStatusList(todoStsList);

        return prjStatusMdl;
    }

    /**
     * <br>[機  能] プロジェクト状態をオブジェクトファイルに保存
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param prjStatusMdl プロジェクト状態
     * @throws IOToolsException IOエラー
     */
    private void __saveFile(String rootDir,
                              ProjectStatusModel prjStatusMdl)
        throws IOToolsException {

        CommonBiz cBiz = new CommonBiz();
        String filePath = cBiz.getTempDir(
                rootDir, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);

        log__.debug("ディレクトリ = " + filePath);
        log__.debug("ファイル名 = " + GSConstProject.SAVE_FILENAME);

        //ディレクトリ存在チェック(なければ作成)
        IOTools.isDirCheck(filePath, true);
        //ファイル保存
        ObjectFile objFile = new ObjectFile(filePath, GSConstProject.SAVE_FILENAME);
        objFile.save(prjStatusMdl);
    }

    /**
     * <br>[機  能] 選択されたテンプレートを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj140knParamModel
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void setTemplateData(Prj140knParamModel paramMdl,
                                 String rootDir)
        throws SQLException, IOToolsException {

        ProjectStatusModel prjStatusMdl = __getSelectData(paramMdl);

        //プロジェクト状態をオブジェクトファイルに保存
        __saveFile(rootDir, prjStatusMdl);

//        //プロジェクト登録画面へのコピー値をセット
//        __setPrjAddDspParam(paramMdl);
    }
}