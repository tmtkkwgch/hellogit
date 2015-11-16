package jp.groupsession.v2.prj.prj050;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.PrjIUserGroupListenerImpl;
import jp.groupsession.v2.prj.ProjectUpdateBiz;
import jp.groupsession.v2.prj.dao.PrjMembersDao;
import jp.groupsession.v2.prj.dao.PrjPrjdataDao;
import jp.groupsession.v2.prj.dao.PrjTodoBinDao;
import jp.groupsession.v2.prj.dao.PrjTododataDao;
import jp.groupsession.v2.prj.dao.PrjTodomemberDao;
import jp.groupsession.v2.prj.dao.PrjTodostatusDao;
import jp.groupsession.v2.prj.dao.ProjectSearchDao;
import jp.groupsession.v2.prj.model.PrjPrjdataModel;
import jp.groupsession.v2.prj.model.PrjTodomemberModel;
import jp.groupsession.v2.prj.model.PrjUserConfModel;
import jp.groupsession.v2.prj.model.ProjectItemModel;
import jp.groupsession.v2.prj.model.ProjectStatusModel;
import jp.groupsession.v2.prj.model.UserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] TODO登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj050Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj050Biz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;

    /**
     * <p>コンストラクタ
     * @param reqMdl リクエストモデル
     */
    public Prj050Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl リクエストモデル
     */
    public Prj050Biz(
            Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param buMdl セッションユーザModel
     * @param appRoot アプリケーションのルートパス
     * @param tempRoot テンポラリディレクトリ
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @param domain ドメイン
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitData(
        Prj050ParamModel paramMdl,
        BaseUserModel buMdl,
        String appRoot,
        String tempRoot,
        int userSid,
        RequestModel reqMdl,
        String domain)
        throws SQLException, IOToolsException, IOException, TempFileException {

        //処理モード
        String cmdMode = paramMdl.getPrj050cmdMode();
        if (cmdMode == null) {
            cmdMode = GSConstProject.CMD_MODE_ADD;
            paramMdl.setPrj050cmdMode(cmdMode);
        }

        //個人設定を取得
        PrjCommonBiz prjBiz = new PrjCommonBiz(con__, reqMdl__);
        PrjUserConfModel prjUserMdl = null;
        prjUserMdl = prjBiz.getPrjUserConfModel(con__, reqMdl__.getSmodel().getUsrsid());
        //画面の表示区分を取得
        if (paramMdl.getPrj050dspKbn() == GSConstProject.DSP_FIRST) {
            paramMdl.setPrj050elementKbn(prjUserMdl.getPucTodoDsp());
        }

        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録モード
            __setNewInit(paramMdl, buMdl, reqMdl);

        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新モード
            //更新時の初期設定を行う
            __setEditInit(paramMdl, appRoot, tempRoot, buMdl, domain);

            //更新モード時初期表示
            if (paramMdl.getPrj050dspKbn() == GSConstProject.DSP_FIRST) {

                //タイトル
                paramMdl.setPrj050titleEasy(paramMdl.getPrj050title());
                //予定終了年
                paramMdl.setPrj050kigenYearEasy(paramMdl.getPrj050kigenYear());
                //予定終了月
                paramMdl.setPrj050kigenMonthEasy(paramMdl.getPrj050kigenMonth());
                //予定終了日
                paramMdl.setPrj050kigenDayEasy(paramMdl.getPrj050kigenDay());
                //重要度
                paramMdl.setPrj050juyouEasy(paramMdl.getPrj050juyou());
                //内容
                paramMdl.setPrj050naiyoEasy(paramMdl.getPrj050naiyo());
            }
        }
        paramMdl.setPrj050dspKbn(GSConstProject.DSP_ALREADY);
    }

    /**
     * <br>[機  能] 新規時の初期設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param buMdl セッションユーザModel
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    private void __setNewInit(Prj050ParamModel paramMdl, BaseUserModel buMdl, RequestModel reqMdl)
    throws SQLException {

        int projectSid = -1;

        //プロジェクト未選択の場合、初期値をセット
        if (paramMdl.getPrj050prjSid() < 1) {

            String prj050scrId =
                NullDefault.getString(paramMdl.getPrj050scrId(), "");

            //メイン画面 or ダッシュボードから遷移
            if (prj050scrId.equals(GSConstProject.SCR_ID_PRJ010)
                    || prj050scrId.equals(GSConstProject.SCR_ID_MAIN)) {

                //マイプロジェクトをデフォルトとする
                GsMessage gsMsg = new GsMessage(reqMdl__);
                ProjectSearchDao dao = new ProjectSearchDao(con__, gsMsg);
                projectSid = dao.getMyPrjSid(buMdl.getUsrsid());

                if (projectSid == -1) {

                    log__.debug("***マイプロジェクト無し → 作成");
                    //マイプロジェクトが登録されていない場合、作成する。
                    PrjIUserGroupListenerImpl uglistener = new PrjIUserGroupListenerImpl();
                    uglistener.addUser(null, con__, buMdl.getUsrsid(), GSConst.USER_ADMIN, reqMdl);
                    con__.commit();
                }

            } else {
                //簡易、詳細でTODO作成区分を判別する
                int param = 0;
                if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_EASY) {
                    param = GSConstProject.PRJ_KBN_PARTICIPATION;
                } else if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
                    param = paramMdl.getPrj050PrjListKbn();
                }

                GsMessage gsMsg = new GsMessage(reqMdl__);
                PrjCommonBiz pcBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
                List<LabelValueBean> projectLabel =
                    pcBiz.getCanCreateTodoProjectLabel(buMdl, param);
                projectSid =
                    NullDefault.getInt(
                            projectLabel.get(0).getValue(), GSConstCommon.NUM_INIT);
            }

            paramMdl.setPrj050prjSid(projectSid);
        }

        //マイプロジェクトの場合、担当者に自分を設定する
        setMyPrjKbn(paramMdl, buMdl);
        GsMessage gsMsg = new GsMessage(reqMdl__);
        paramMdl.setPrj050statusCmt(gsMsg.getMessage("cmn.new.registration"));
    }

    /**
     * <br>[機  能] 更新時の初期設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param appRoot アプリケーションのルートパス
     * @param tempRoot テンポラリディレクトリ
     * @param buMdl セッションユーザModel
     * @param domain ドメイン
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __setEditInit(
        Prj050ParamModel paramMdl,
        String appRoot,
        String tempRoot,
        BaseUserModel buMdl,
        String domain) throws SQLException, IOToolsException, IOException, TempFileException {

        if (paramMdl.getPrj050dspKbn() == GSConstProject.DSP_FIRST) {
            int todoSid = paramMdl.getPrj050todoSid();

            //TODO情報を取得
            PrjTododataDao ptdDao = new PrjTododataDao(con__);
            ProjectItemModel piMdl = ptdDao.getTodoData(todoSid);

            //プロジェクトSID
            paramMdl.setPrj050prjSid(piMdl.getProjectSid());
            //カテゴリ
            paramMdl.setPrj050cate(piMdl.getCategorySid());
            //TODOタイトル
            paramMdl.setPrj050title(piMdl.getTodoTitle());
            //予定開始年月日
            UDate start = piMdl.getStartDate();
            if (start != null) {
                paramMdl.setPrj050kaisiYoteiYear(String.valueOf(start.getYear()));
                paramMdl.setPrj050kaisiYoteiMonth(String.valueOf(start.getMonth()));
                paramMdl.setPrj050kaisiYoteiDay(String.valueOf(start.getIntDay()));
            }
            //予定終了年月日
            UDate end = piMdl.getEndDate();
            if (end != null) {
                paramMdl.setPrj050kigenYear(String.valueOf(end.getYear()));
                paramMdl.setPrj050kigenMonth(String.valueOf(end.getMonth()));
                paramMdl.setPrj050kigenDay(String.valueOf(end.getIntDay()));
            }
            //実績開始年月日
            UDate jissekiStart = piMdl.getStartJissekiDate();
            if (jissekiStart != null) {
                paramMdl.setPrj050kaisiJissekiYear(String.valueOf(jissekiStart.getYear()));
                paramMdl.setPrj050kaisiJissekiMonth(String.valueOf(jissekiStart.getMonth()));
                paramMdl.setPrj050kaisiJissekiDay(String.valueOf(jissekiStart.getIntDay()));
            }
            //実績終了年月日
            UDate jissekiEnd = piMdl.getEndJissekiDate();
            if (jissekiEnd != null) {
                paramMdl.setPrj050syuryoJissekiYear(String.valueOf(jissekiEnd.getYear()));
                paramMdl.setPrj050syuryoJissekiMonth(String.valueOf(jissekiEnd.getMonth()));
                paramMdl.setPrj050syuryoJissekiDay(String.valueOf(jissekiEnd.getIntDay()));
            }
            //予定工数
            BigDecimal yoteiKosu = piMdl.getYoteiKosu();
            if (yoteiKosu != null) {
                paramMdl.setPrj050yoteiKosu(String.valueOf(yoteiKosu));
            }
            //実績工数
            BigDecimal jissekiKosu = piMdl.getJissekiKosu();
            if (jissekiKosu != null) {
                paramMdl.setPrj050jissekiKosu(String.valueOf(jissekiKosu));
            }
            //警告開始
            paramMdl.setPrj050keikoku(piMdl.getKeikoku());
            //重要度
            paramMdl.setPrj050juyou(piMdl.getJuyo());
            //状態
            paramMdl.setPrj050status(piMdl.getStatus());
            //内容
            paramMdl.setPrj050naiyo(piMdl.getNaiyou());

            //担当者情報を取得する
            PrjTodomemberDao ptmDao = new PrjTodomemberDao(con__);
            CmnUsrmDao usrmDao = new CmnUsrmDao(con__);
            List<PrjTodomemberModel> ptmList = ptmDao.getTantoBaseList(todoSid);
            List<String> tantoList = new ArrayList<String>();

            int cnt = 0;
            for (PrjTodomemberModel ptmMdl : ptmList) {

                //削除ユーザチェック
                List<String> userSidList = new ArrayList<String>();
                userSidList.add(String.valueOf(ptmMdl.getUsrSid()));
                cnt = usrmDao.getCountDeleteUser(userSidList);

                if (cnt == 0) {
                    tantoList.add(String.valueOf(ptmMdl.getUsrSid()));
                }
            }
            String[] tanto = (String[]) tantoList.toArray(new String[tantoList.size()]);
            paramMdl.setPrj050hdnTanto(tanto);

          //添付ファイルをテンポラリディレクトリへ設定する
            __setTempFile(paramMdl, appRoot, tempRoot, domain);
        }
        //マイプロジェクトの場合、担当者に自分を設定する
        setMyPrjKbn(paramMdl, buMdl);
    }

    /**
     * <br>[機  能] マイプロジェクト区分・担当者の設定を行うを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param buMdl セッションユーザModel
     * @throws SQLException SQL実行例外
     */
    public void setMyPrjKbn(Prj050ParamModel paramMdl, BaseUserModel buMdl) throws SQLException {

        //簡易、詳細でTODO作成区分を判別する
        int param = 0;
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_EASY) {
            param = GSConstProject.PRJ_KBN_PARTICIPATION;
        } else if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            param = paramMdl.getPrj050PrjListKbn();
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);
        ProjectSearchDao psDao = new ProjectSearchDao(con__, gsMsg);
        List<ProjectItemModel> prjlist =
            psDao.getCanCreateTodoProjectList(buMdl, param);

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        boolean projectExistFlg = false;
        int projectSid = paramMdl.getPrj050prjSid();

        for (ProjectItemModel piMdl : prjlist) {

            labelList.add(new LabelValueBean(
                    piMdl.getProjectName(), String.valueOf(piMdl.getProjectSid())));

            if (projectSid == piMdl.getProjectSid()) {
                projectExistFlg = true;
                break;
            }
        }

        //プロジェクト未選択の場合、初期値をセット(コンボの一番上の値)
        if (paramMdl.getPrj050prjSid() < 1
                || !projectExistFlg) {
            paramMdl.setPrj050prjSid(
                    NullDefault.getInt(labelList.get(0).getValue(), GSConstCommon.NUM_INIT));
        }

        //プロジェクト情報を取得
        PrjPrjdataDao prjDao = new PrjPrjdataDao(con__);
        PrjPrjdataModel prjMdl = prjDao.getProjectData(paramMdl.getPrj050prjSid());

        if (prjMdl != null) {
            int prjMyKbn = prjMdl.getPrjMyKbn();
            //プロジェクトがマイプロジェクトの場合は担当に自分をセット
            if (prjMyKbn == GSConstProject.KBN_MY_PRJ_MY) {
                //担当者に初期値をセットする
                String[] tanto = {String.valueOf(buMdl.getUsrsid())};
                paramMdl.setPrj050hdnTanto(tanto);
            }
            paramMdl.setPrj050prjMyKbn(prjMyKbn);
        }
    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param appRoot アプリケーションのルートパス
     * @param tempRoot テンポラリディレクトリ
     * @param domain ドメイン
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __setTempFile(
        Prj050ParamModel paramMdl,
        String appRoot,
        String tempRoot,
        String domain)
    throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        int projectSid = paramMdl.getPrj050prjSid();
        int todoSid = paramMdl.getPrj050todoSid();
        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)
        String tempDir = cmnBiz.getTempDir(
                tempRoot, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);

        //添付ファイル情報を取得する
        PrjTodoBinDao ptbDao = new PrjTodoBinDao(con__);
        String[] binSids = ptbDao.getBinSids(projectSid, todoSid);
        if (binSids == null || binSids.length < 1) {
            return;
        }
        List<CmnBinfModel> cmList = cmnBiz.getBinInfo(con__, binSids, domain);

        int fileNum = 1;
        for (CmnBinfModel cbMdl : cmList) {
            if (cbMdl.getBinJkbn() == GSConst.JTKBN_DELETE) {
                continue;
            }

            //添付ファイルをテンポラリディレクトリにコピーする。
            cmnBiz.saveTempFile(dateStr, cbMdl, appRoot, tempDir, fileNum);

            fileNum++;
        }
    }

    /**
     * <br>[機  能] 画面に常に表示する情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param buMdl セッションユーザModel
     * @param tempRoot テンポラリディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void getDspData(
        Prj050ParamModel paramMdl,
        BaseUserModel buMdl,
        String tempRoot) throws SQLException, IOToolsException {

        //管理者区分
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con__, buMdl, GSConstProject.PLUGIN_ID_PROJECT);
        paramMdl.setAdmin(adminUser);

        //処理モード
        String cmdMode = paramMdl.getPrj050cmdMode();
        int todoSid = paramMdl.getPrj050todoSid();

        if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新モード
            //TODO情報を取得
            PrjTododataDao ptdDao = new PrjTododataDao(con__);
            ProjectItemModel piMdl = ptdDao.getTodoData(todoSid);
            piMdl.setStrKanriNo(
                    StringUtil.toDecFormat(piMdl.getKanriNo(), GSConstProject.KANRI_NO_FORMAT));
            paramMdl.setProjectItem(piMdl);
        }
        //簡易、詳細でTODO作成区分を判別する
        int param = 0;
        if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_EASY) {
            param = GSConstProject.PRJ_KBN_PARTICIPATION;
        } else if (paramMdl.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            param = paramMdl.getPrj050PrjListKbn();
        }

        //プロジェクトコンボをセットする
        GsMessage gsMsg = new GsMessage(reqMdl__);
        ProjectSearchDao psDao = new ProjectSearchDao(con__, gsMsg);
        List<ProjectItemModel> prjlist =
            psDao.getCanCreateTodoProjectList(buMdl, param);

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        boolean projectExistFlg = false;
        int projectSid = paramMdl.getPrj050prjSid();

        for (ProjectItemModel piMdl : prjlist) {
            labelList.add(new LabelValueBean(
                    piMdl.getProjectName(), String.valueOf(piMdl.getProjectSid())));

            if (projectSid == piMdl.getProjectSid()) {
                projectExistFlg = true;
            }
        }

        paramMdl.setProjectLabel(labelList);

        //プロジェクト未選択の場合、初期値をセット(コンボの一番上の値)
        if (paramMdl.getPrj050prjSid() < 1
                || !projectExistFlg) {
            paramMdl.setPrj050prjSid(
                    NullDefault.getInt(labelList.get(0).getValue(), GSConstCommon.NUM_INIT));
        }

        //TODOカテゴリリストをセットする
        PrjCommonBiz pcBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
        paramMdl.setPrj050CategoryList(pcBiz.getTodoCategoryList(projectSid));

        //担当者
        String[] tanto = paramMdl.getPrj050hdnTanto();

        //担当者コンボをセットする
        UserBiz userBiz = new UserBiz();
        paramMdl.setAdminMemberLabel(userBiz.getUserLabelList(con__, tanto));

        //メンバーコンボをセットする
        PrjMembersDao pmDao = new PrjMembersDao(con__);
        List<UserModel> usrAllList = pmDao.getMemberList(projectSid, tanto);
        List<UserModel> userList = new ArrayList<UserModel>();
        List<LabelValueBean> userLabel = new ArrayList<LabelValueBean>();
        CmnUsrmDao usrmDao = new CmnUsrmDao(con__);

        int cnt = 0;
        for (UserModel usrMdl : usrAllList) {

            //削除ユーザチェック
            List<String> userSidList = new ArrayList<String>();
            userSidList.add(String.valueOf(usrMdl.getUserSid()));
            cnt = usrmDao.getCountDeleteUser(userSidList);

            if (cnt == 0) {
                userList.add(usrMdl);
            }
        }

        for (UserModel userMdl : userList) {
            userLabel.add(new LabelValueBean(userMdl.getSei() + " " + userMdl.getMei(),
                                             String.valueOf(userMdl.getUserSid())));
        }
        paramMdl.setMemberLabel(userLabel);

        //年月日コンボの値をセット
        UDate now = new UDate();
        PrjCommonBiz prjComBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
        paramMdl.setYearLabel(prjComBiz.getYearList(now.getYear()));
        paramMdl.setMonthLabel(prjComBiz.getMonthList());
        paramMdl.setDayLabel(prjComBiz.getDayList());
        //警告開始コンボの値をセット
        paramMdl.setKeikokuLabel(prjComBiz.getKeikokuLabel());

        //TODO状態を取得
        PrjTodostatusDao pts = new PrjTodostatusDao(con__);
        ProjectStatusModel prjStatusMdl = new ProjectStatusModel();
        prjStatusMdl.setTodoStatusList(pts.selectProjects(projectSid));
        paramMdl.setPrjStatusMdl(prjStatusMdl);

        //添付ファイル一覧を設定
        String tempDir = cmnBiz.getTempDir(
                tempRoot, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);
        paramMdl.setFileLabel(cmnBiz.getTempFileLabelList(tempDir));

        //ショートメール通知区分
        PrjPrjdataDao prjDao = new PrjPrjdataDao(con__);
        PrjPrjdataModel prjMdl = prjDao.getProjectData(projectSid);

        int send = paramMdl.getPrj050MailSend();

        //ショートメール通知区分
        paramMdl.setPrj050smailKbn(prjMdl.getPrjMailKbn());
        if (send == -1) {
            if (prjMdl.getPrjMailKbn() == GSConstProject.TODO_MAIL_FREE) {
                paramMdl.setPrj050MailSend(GSConstProject.NOT_SEND);
            } else if (prjMdl.getPrjMailKbn() == GSConstProject.TODO_MAIL_SEND_ADMIN) {
                paramMdl.setPrj050MailSend(GSConstProject.SEND_LEADER);
            }
        }

        if (prjMdl != null) {
            int prjMyKbn = prjMdl.getPrjMyKbn();
            paramMdl.setPrj050prjMyKbn(prjMyKbn);
        }
    }

    /**
     * <br>[機  能] TODOを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj050ParamModel
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void deleteTodo(Prj050ParamModel paramMdl, int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            //TODO情報を削除する
            int todoSid = paramMdl.getPrj050todoSid();
            String[] todoSids = {String.valueOf(todoSid)};

            ProjectUpdateBiz projectBiz = new ProjectUpdateBiz(con__);
            projectBiz.deleteTodo(todoSids, userSid);

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
