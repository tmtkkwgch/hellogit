package jp.groupsession.v2.prj.prj140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.ProjectUpdateBiz;
import jp.groupsession.v2.prj.dao.PrjMembersTmpDao;
import jp.groupsession.v2.prj.dao.PrjPrjdataTmpDao;
import jp.groupsession.v2.prj.dao.PrjPrjstatusTmpDao;
import jp.groupsession.v2.prj.dao.PrjTodocategoryTmpDao;
import jp.groupsession.v2.prj.dao.PrjTodostatusTmpDao;
import jp.groupsession.v2.prj.model.PrjMembersTmpModel;
import jp.groupsession.v2.prj.model.PrjPrjstatusTmpModel;
import jp.groupsession.v2.prj.model.PrjTodostatusTmpModel;
import jp.groupsession.v2.prj.model.ProjectItemModel;
import jp.groupsession.v2.prj.model.ProjectStatusTmpModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] プロジェクト管理 プロジェクトテンプレート登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj140Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj140Biz.class);

    /** ヘルプモード 個人テンプレート登録*/
    public static final String HELP_MODE_TMP_KOJIN_ADD = "0";
    /** ヘルプモード 個人テンプレート編集*/
    public static final String HELP_MODE_TMP_KOJIN_EDIT = "1";
    /** ヘルプモード 共有テンプレート登録*/
    public static final String HELP_MODE_TMP_KYOUYU_ADD = "2";
    /** ヘルプモード 共有テンプレート編集*/
    public static final String HELP_MODE_TMP_KYOUYU_EDIT = "3";

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;
    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl リクエストモデル
     */
    public Prj140Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void setInitData(Prj140ParamModel paramMdl,
                             int userSid,
                             String rootDir)
        throws SQLException, IOToolsException {

        //プロジェクトテンプレートSID取得
        int prtSid = paramMdl.getPrtSid();

        //プロジェクト状態
        ProjectStatusTmpModel prjStatusMdl = null;

        if (prtSid < 1) {

            log__.debug("テンプレート新規登録");

            //初期設定を行い、プロジェクト状態Modelを返す
            prjStatusMdl = __setAddInit(paramMdl, userSid);

            //ヘルプパラメータを設定する
            if (paramMdl.getPrjTmpMode() == GSConstProject.MODE_TMP_KOJIN) {
                paramMdl.setPrj140cmdMode(HELP_MODE_TMP_KOJIN_ADD);
            } else {
                paramMdl.setPrj140cmdMode(HELP_MODE_TMP_KYOUYU_ADD);
            }

        } else {

            log__.debug("テンプレート編集");

            //初期設定を行い、プロジェクト状態Modelを返す
            prjStatusMdl = __setEditInit(paramMdl, userSid, paramMdl.getPrtSid());

            //ヘルプパラメータを設定する
            if (paramMdl.getPrjTmpMode() == GSConstProject.MODE_TMP_KOJIN) {
                paramMdl.setPrj140cmdMode(HELP_MODE_TMP_KOJIN_EDIT);
            } else {
                paramMdl.setPrj140cmdMode(HELP_MODE_TMP_KYOUYU_EDIT);
            }
        }

        //プロジェクト状態をオブジェクトファイルに保存
        __saveFile(rootDir, prjStatusMdl);

        //グループにデフォルトグループを設定
        GroupBiz grpBiz = new GroupBiz();
        paramMdl.setPrj140group(grpBiz.getDefaultGroupSid(userSid, con__));
    }

    /**
     * <br>[機  能] 登録時の初期設定を行い、プロジェクト状態Modelを返す
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @return ProjectStatusModel
     */
    private ProjectStatusTmpModel __setAddInit(Prj140ParamModel paramMdl, int userSid) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //予定
        String textYotei = gsMsg.getMessage("project.prj010.8");
        //プロジェクト状態
        ProjectStatusTmpModel prjStatusMdl = new ProjectStatusTmpModel();
        //完了
        String textCmoplete = gsMsg.getMessage("cmn.complete");

        //プロジェクト状態に初期値をセットする
        paramMdl.setPrj140status(1);

        //プロジェクトメンバー・管理者に初期値をセットする
        String[] member = {String.valueOf(userSid) + GSConst.GSESSION2_ID};
        String[] admin = {String.valueOf(userSid)};

        //所属メンバー
        paramMdl.setPrj140hdnMember(member);
        //プロジェクト管理者
        paramMdl.setPrj140hdnAdmin(admin);

        //プロジェクトテンプレート状態
        List<PrjPrjstatusTmpModel> prjStatusTmpList = new ArrayList<PrjPrjstatusTmpModel>();
        PrjPrjstatusTmpModel pttMdl = new PrjPrjstatusTmpModel();
        pttMdl.setPttSid(GSConstProject.STATUS_0);
        pttMdl.setPttSort(1);
        pttMdl.setPttName(textYotei);
        pttMdl.setPttRate(GSConstProject.RATE_MIN);
        prjStatusTmpList.add(pttMdl);

        pttMdl = new PrjPrjstatusTmpModel();
        pttMdl.setPttSid(GSConstProject.STATUS_100);
        pttMdl.setPttSort(2);
        pttMdl.setPttName(textCmoplete);
        pttMdl.setPttRate(GSConstProject.RATE_MAX);
        prjStatusTmpList.add(pttMdl);

        //TODO状態
        List<PrjTodostatusTmpModel> todoStatusTmpList = new ArrayList<PrjTodostatusTmpModel>();
        PrjTodostatusTmpModel pstMdl = new PrjTodostatusTmpModel();
        pstMdl.setPstSid(1);
        pstMdl.setPstSort(1);
        pstMdl.setPstName(textYotei);
        pstMdl.setPstRate(GSConstProject.RATE_MIN);
        todoStatusTmpList.add(pstMdl);

        pstMdl = new PrjTodostatusTmpModel();
        pstMdl.setPstSid(2);
        pstMdl.setPstSort(2);
        pstMdl.setPstName(textCmoplete);
        pstMdl.setPstRate(GSConstProject.RATE_MAX);
        todoStatusTmpList.add(pstMdl);

        //プロジェクト状態を取得
        prjStatusMdl.setPrjStatusList(prjStatusTmpList);

        //TODO状態を取得
        prjStatusMdl.setTodoStatusList(todoStatusTmpList);

        //初期表示時は開始と終了にシステム年月日を設定
        UDate systemUd = new UDate();
        String year = String.valueOf(systemUd.getYear());
        String month = String.valueOf(systemUd.getMonth());
        String day = String.valueOf(systemUd.getIntDay());

        paramMdl.setPrj140startYear(year);
        paramMdl.setPrj140startMonth(month);
        paramMdl.setPrj140startDay(day);
        paramMdl.setPrj140endYear(year);
        paramMdl.setPrj140endMonth(month);
        paramMdl.setPrj140endDay(day);

        return prjStatusMdl;
    }

    /**
     * <br>[機  能] 編集時の初期設定を行い、プロジェクト状態Modelを返す
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @param prtSid プロジェクトテンプレートSID
     * @return ProjectStatusModel
     * @throws SQLException SQL実行例外
     */
    private ProjectStatusTmpModel __setEditInit(Prj140ParamModel paramMdl,
                                                 int userSid,
                                                 int prtSid)
        throws SQLException {

        //プロジェクト状態
        ProjectStatusTmpModel prjStatusMdl = new ProjectStatusTmpModel();

        //プロジェクト情報を取得する
        PrjPrjdataTmpDao ppDao = new PrjPrjdataTmpDao(con__);
        ProjectItemModel piMdl = ppDao.getProjectTmpInfo(prtSid);

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
            paramMdl.setPrj140yosan(String.valueOf(yosan));
        }
        //公開・非公開
        paramMdl.setPrj140koukai(piMdl.getKoukaiKbn());
        //開始年月日
        UDate start = piMdl.getStartDate();
        if (start != null) {
            paramMdl.setPrj140startYear(String.valueOf(start.getYear()));
            paramMdl.setPrj140startMonth(String.valueOf(start.getMonth()));
            paramMdl.setPrj140startDay(String.valueOf(start.getIntDay()));
        }
        //終了年月日
        UDate end = piMdl.getEndDate();
        if (end != null) {
            paramMdl.setPrj140endYear(String.valueOf(end.getYear()));
            paramMdl.setPrj140endMonth(String.valueOf(end.getMonth()));
            paramMdl.setPrj140endDay(String.valueOf(end.getIntDay()));
        }
        //状態
        paramMdl.setPrj140status(piMdl.getStatus());
        //目標・目的
        paramMdl.setPrj140mokuhyou(piMdl.getMokuhyou());
        //内容
        paramMdl.setPrj140naiyou(piMdl.getNaiyou());
        //編集権限
        paramMdl.setPrj140kengen(piMdl.getEditKengen());
        //ショートメール通知先
        paramMdl.setPrj140smailKbn(piMdl.getPrjMailKbn());

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
            member[index] = String.valueOf(pmMdl.getUsrSid())
                            + GSConst.GSESSION2_ID
                + NullDefault.getString(pmMdl.getPmtMemKey(), "");;
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

        //プロジェクト状態を取得
        PrjPrjstatusTmpDao ppsDao = new PrjPrjstatusTmpDao(con__);
        prjStatusMdl.setPrjStatusList(ppsDao.selectTmpProjects(prtSid));

        //TODOカテゴリを取得
        PrjTodocategoryTmpDao ptcDao = new PrjTodocategoryTmpDao(con__);
        prjStatusMdl.setTodoCateList(ptcDao.selectTmpProjects(prtSid));

        //TODO状態を取得
        PrjTodostatusTmpDao pts = new PrjTodostatusTmpDao(con__);
        prjStatusMdl.setTodoStatusList(pts.selectTmpProjects(prtSid));

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
                             ProjectStatusTmpModel prjStatusMdl) throws IOToolsException {

        CommonBiz cBiz = new CommonBiz();
        String filePath = cBiz.getTempDir(rootDir, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);

        log__.debug("ディレクトリ = " + filePath);
        log__.debug("ファイル名 = " + GSConstProject.SAVE_FILENAME);

        //ディレクトリ存在チェック(なければ作成)
        IOTools.isDirCheck(filePath, true);

        //ファイル保存
        ObjectFile objFile = new ObjectFile(filePath, GSConstProject.SAVE_FILENAME);
        objFile.save(prjStatusMdl);
    }

    /**
     * <br>[機  能] 画面に常に表示する情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl アクションフォーム
     * @param pconfig プラグイン設定情報
     * @param rootDir ルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void getDspData(Prj140ParamModel paramMdl, PluginConfig pconfig,
                            String rootDir)
        throws SQLException, IOToolsException {

        CommonBiz cmnBiz = new CommonBiz();

        //所属メンバー
        String[] member = paramMdl.getPrj140hdnMember();
        String[] convMember = __getUserSidList(paramMdl);

        //プロジェクト管理者
        String[] admin = paramMdl.getPrj140hdnAdmin();
        //管理者以外メンバー
        String[] notAdmin = cmnBiz.getDeleteMember(admin, convMember);
        //グループSID
        int groupSid = paramMdl.getPrj140group();

        //グループラベルを設定する。
        GsMessage gsMsg = new GsMessage(reqMdl__);
        GroupBiz grpBiz = new GroupBiz();
        List<LabelValueBean> groupLabel = grpBiz.getGroupCombLabelList(con__, true, gsMsg);
        groupLabel.remove(0);
        paramMdl.setGroupLabel(groupLabel);

        //所属メンバーラベル
        UserBiz userBiz = new UserBiz();
        paramMdl.setSyozokuMemberLabel(userBiz.getUserPrjLabelList(con__, member));

        //未所属ユーザラベル
        paramMdl.setUserLabel(userBiz.getNormalUserLabelList(
                                   con__, groupSid, convMember, false, gsMsg));

        //管理者メンバーラベル
        paramMdl.setAdminMemberLabel(userBiz.getUserLabelList(con__, admin));

        //管理者以外メンバー
        paramMdl.setMemberLabel(userBiz.getUserLabelList(con__, notAdmin));

        //年月日コンボの値をセット
        UDate now = new UDate();
        PrjCommonBiz prjComBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
        paramMdl.setYearLabel(prjComBiz.getYearList(now.getYear()));
        paramMdl.setMonthLabel(prjComBiz.getMonthList());
        paramMdl.setDayLabel(prjComBiz.getDayList());

        //プロジェクト状態
        paramMdl.setPrjStatusTmpMdl(PrjCommonBiz.getProjectStatusTmpModel(rootDir, reqMdl__));

        //ショートメール使用有無
        paramMdl.setUseSmail(pconfig.getPlugin(GSConstProject.PLUGIN_ID_SMAIL) != null);
    }

    /**
     * <br>[機  能] ユーザSID配列からユーザSID部分を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @return ret ユーザ情報Model
     * @throws SQLException SQL実行時例外
     */
    private String[] __getUserSidList(Prj140ParamModel paramMdl) throws SQLException {

        String[] hdnMember = paramMdl.getPrj140hdnMember();
        String[] spUsrSidList = null;

        if (hdnMember != null && hdnMember.length > 0) {

            int idx = 0;
            spUsrSidList = new String[hdnMember.length];

            for (String hdn : hdnMember) {

                String[] splitStr = hdn.split(GSConst.GSESSION2_ID);
                spUsrSidList[idx] = String.valueOf(splitStr[0]);

                idx += 1;
            }
        }

        return spUsrSidList;
    }

    /**
     * <br>[機  能] プロジェクトを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void deleteProject(Prj140ParamModel paramMdl, int userSid) throws SQLException {

        //プロジェクトテンプレートSID
        int prtSid = paramMdl.getPrtSid();
        boolean commitFlg = false;

        try {

            con__.setAutoCommit(false);

            //プロジェクト情報を削除する
            ProjectUpdateBiz projectBiz = new ProjectUpdateBiz(con__);
            projectBiz.deleteProjectTmp(prtSid);

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
}