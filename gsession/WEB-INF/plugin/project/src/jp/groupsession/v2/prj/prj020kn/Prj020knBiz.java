package jp.groupsession.v2.prj.prj020kn;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.ProjectUpdateBiz;
import jp.groupsession.v2.prj.dao.PrjAddressDao;
import jp.groupsession.v2.prj.dao.PrjCompanyDao;
import jp.groupsession.v2.prj.dao.PrjPrjdataDao;
import jp.groupsession.v2.prj.dao.ProjectUpdateDao;
import jp.groupsession.v2.prj.model.PrjAddressModel;
import jp.groupsession.v2.prj.model.PrjCompanyModel;
import jp.groupsession.v2.prj.model.PrjMembersModel;
import jp.groupsession.v2.prj.model.PrjPrjdataModel;
import jp.groupsession.v2.prj.model.PrjPrjstatusModel;
import jp.groupsession.v2.prj.model.ProjectStatusModel;
import jp.groupsession.v2.prj.prj020.Prj020Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] プロジェクト登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj020knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj020knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl リクエストモデル
     */
    public Prj020knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj020knParamModel
     * @param pconfig プラグイン設定情報
     * @param rootDir ルートディレクトリ
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public void setInitData(Prj020knParamModel paramMdl, PluginConfig pconfig,
                             String rootDir, int userSid)
    throws SQLException, IOToolsException {

        //プロジェクト状態
        ProjectStatusModel prjStatus = PrjCommonBiz.getProjectStatusModel(rootDir, reqMdl__);

        GsMessage gsMsg = new GsMessage(reqMdl__);

        PrjCommonBiz prjCmnBiz = new PrjCommonBiz(con__, gsMsg, reqMdl__);
        //予算
        paramMdl.setYosan(prjCmnBiz.getYosanStr(
                NullDefault.getLong(paramMdl.getPrj020yosan(), -1)));
        //公開・非公開
        paramMdl.setKoukai(prjCmnBiz.getKoukaiKbnName(paramMdl.getPrj020koukai()));
        //開始
        String startDate = "";
        if (!paramMdl.getPrj020startYear().equals("")
        && !paramMdl.getPrj020startMonth().equals("")
        && !paramMdl.getPrj020startDay().equals("")) {
            startDate = paramMdl.getPrj020startYear() + "/"
                      + StringUtil.toDecFormat(paramMdl.getPrj020startMonth(), "00") + "/"
                      + StringUtil.toDecFormat(paramMdl.getPrj020startDay(), "00");
        }
        paramMdl.setStartDate(startDate);
        //終了
        String endDate = "";
        if (!paramMdl.getPrj020endYear().equals("")
        && !paramMdl.getPrj020endMonth().equals("")
        && !paramMdl.getPrj020endDay().equals("")) {
            endDate = paramMdl.getPrj020endYear() + "/"
                    + StringUtil.toDecFormat(paramMdl.getPrj020endMonth(), "00") + "/"
                    + StringUtil.toDecFormat(paramMdl.getPrj020endDay(), "00");
        }
        paramMdl.setEndDate(endDate);

        //状態
        if (paramMdl.getPrj020prjMyKbn() == GSConstProject.KBN_MY_PRJ_DEF) {
            String status = "";
            List<PrjPrjstatusModel> prjStatusList = prjStatus.getPrjStatusList();
            for (PrjPrjstatusModel ppsMdl : prjStatusList) {
                if (ppsMdl.getPrsSid() == paramMdl.getPrj020status()) {
                    status = ppsMdl.getPrsRate() + "% (" + ppsMdl.getPrsName() + ")";
                    break;
                }
            }
            paramMdl.setStatus(status);
        }

        //目標・目的
        paramMdl.setMokuhyou(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj020mokuhyou()));
        //内容
        paramMdl.setNaiyou(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPrj020naiyou()));
        //所属メンバーラベル
        UserBiz userBiz = new UserBiz();
        paramMdl.setSyozokuMemberLabel(
                userBiz.getUserPrjLabelList(
                        con__, paramMdl.getPrj020hdnMember()));
        //管理者メンバーラベル
        paramMdl.setAdminMemberLabel(userBiz.getUserLabelList(con__, paramMdl.getPrj020hdnAdmin()));
        //TODOカテゴリ、TODO状態
        paramMdl.setPrjStatusMdl(prjStatus);
        //編集権限
        paramMdl.setKengen(prjCmnBiz.getTodoKengenName(paramMdl.getPrj020kengen()));
        //ショートメール通知先
        paramMdl.setSmailKbn(prjCmnBiz.getSmailKbnStr(paramMdl.getPrj020smailKbn()));

        //ショートメール使用有無
        paramMdl.setUseSmail(pconfig.getPlugin(GSConstProject.PLUGIN_ID_SMAIL) != null);

        //アドレスメンバー
        Prj020Biz prj020Biz = new Prj020Biz(reqMdl__, con__);
        int addIdNullHntFlg = 0;

        //アドレスIDがNullでないとき、フラグを立てる
        if (paramMdl.getPrj150AddressIdSv() != null
                && paramMdl.getPrj150AddressIdSv().length != 0) {
            addIdNullHntFlg = 1;
        }

        if (addIdNullHntFlg == 1) {
            prj020Biz.getCompanyDataSv(paramMdl, userSid);
        }
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj020knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param rootDir ルートディレクトリ
     * @param appRoot アプリケーションルートパス
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void doAddEdit(
        Prj020knParamModel paramMdl,
        MlCountMtController cntCon,
        int userSid,
        String rootDir,
        String appRoot) throws SQLException, IOToolsException, TempFileException {

        //プロジェクト状態
        ProjectStatusModel prjStatus = PrjCommonBiz.getProjectStatusModel(rootDir, reqMdl__);

        //テンポラリディレクトリパス
        CommonBiz cBiz = new CommonBiz();
        String filePath = cBiz.getTempDir(
                rootDir, GSConstProject.PLUGIN_ID_PROJECT, reqMdl__);

        //処理モード
        String cmdMode = paramMdl.getPrj020cmdMode();
        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録
            doInsert(paramMdl, cntCon, userSid, prjStatus, appRoot, filePath);

        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新
            doUpdate(paramMdl, userSid, prjStatus, appRoot, filePath, cntCon);
        }
    }

    /**
     * <br>[機  能] 更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj020knParamModel
     * @param userSid ログインユーザSID
     * @param prjStatus プロジェクト状態
     * @param appRoot アプリケーションパス
     * @param filePath ファイルパス
     * @param cntCon 採番コントローラ
     * @throws SQLException SQL実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void doUpdate(
            Prj020knParamModel paramMdl,
            int userSid,
            ProjectStatusModel prjStatus,
            String appRoot,
            String filePath,
            MlCountMtController cntCon)
    throws SQLException, TempFileException {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {

            con__.setAutoCommit(false);

            //プロジェクトSID
            int projectSid = paramMdl.getPrj020prjSid();

            //プロジェクト更新Model
            PrjPrjdataModel ppMdl =
                __getPrjUpdateMdl(projectSid, paramMdl, userSid, now, appRoot, filePath, cntCon);

            //プロジェクトメンバー更新Modelリスト
            List<PrjMembersModel> memberList =
                __getMemberUpdateList(projectSid, paramMdl, userSid, now);

            //プロジェクト情報を更新する
            ProjectUpdateBiz projectBiz = new ProjectUpdateBiz(con__);
            projectBiz.updateProject(ppMdl, prjStatus, memberList);

            addPrjAddDB(paramMdl, userSid, projectSid);
            addPrjComDB(paramMdl, userSid, projectSid);

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
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Prj020knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param prjStatus プロジェクト状態
     * @param appRoot アプリケーションパス
     * @param filePath ファイルパス
     * @throws SQLException SQL実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void doInsert(
        Prj020knParamModel paramMdl,
        MlCountMtController cntCon,
        int userSid,
        ProjectStatusModel prjStatus,
        String appRoot,
        String filePath) throws SQLException, TempFileException {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {

            con__.setAutoCommit(false);

            //プロジェクトSID採番
            int projectSid = (int) cntCon.getSaibanNumber(GSConstProject.SBNSID_PROJECT,
                                                           GSConstProject.SBNSID_SUB_PROJECT,
                                                           userSid);
            //プロジェクト更新Model
            PrjPrjdataModel ppMdl = __getPrjUpdateMdl(projectSid, paramMdl, userSid,
                                                      now, appRoot, filePath, cntCon);

            //プロジェクトメンバー更新Modelリスト
            List<PrjMembersModel> memberList =
                __getMemberUpdateList(projectSid, paramMdl, userSid, now);

            //プロジェクト情報を登録する
            ProjectUpdateDao projectDao = new ProjectUpdateDao(con__);
            projectDao.insertProject(ppMdl, prjStatus, memberList);

            addPrjAddDB(paramMdl, userSid, projectSid);
            addPrjComDB(paramMdl, userSid, projectSid);

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
     * <br>[機  能] 更新用のPrjMembersModelのリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param projectSid プロジェクトSID
     * @param paramMdl Prj020knParamModel
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return List in PrjMembersModel
     */
    private List<PrjMembersModel> __getMemberUpdateList(
        int projectSid,
        Prj020knParamModel paramMdl,
        int userSid,
        UDate now) {

        List<PrjMembersModel> memberList = new ArrayList<PrjMembersModel>();
        String[] members = paramMdl.getPrj020hdnMember();

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

        String[] admins = paramMdl.getPrj020hdnAdmin();
        PrjMembersModel memberMdl = null;

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
                memberMdl = new PrjMembersModel();
                memberMdl.setPrjSid(projectSid);
                memberMdl.setUsrSid(NullDefault.getInt(member, -1));
                memberMdl.setPrmEmployeeKbn(GSConstProject.KBN_PROJECT_MEMBER_INNER);
                memberMdl.setPrmAdminKbn(adminKbn);
                memberMdl.setPrmAuid(userSid);
                memberMdl.setPrmAdate(now);
                memberMdl.setPrmEuid(userSid);
                memberMdl.setPrmEdate(now);
                memberMdl.setPrmMemKey(spUsrKeyMap.get(member));

                memberList.add(memberMdl);
            }
        }
        return memberList;
    }

    /**
     * <br>[機  能] 更新用のPrjPrjdataModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param projectSid プロジェクトSID
     * @param paramMdl Prj020knParamModel
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param appRoot アプリケーションルートパス
     * @param tempDir Tempパス
     * @param cntCon 採番コントローラ
     * @return PrjPrjdataModel
     * @throws TempFileException 添付ファイルUtil内での例外
     * @throws SQLException SQL実行例外
     */
    private PrjPrjdataModel __getPrjUpdateMdl(
        int projectSid,
        Prj020knParamModel paramMdl,
        int userSid,
        UDate now,
        String appRoot,
        String tempDir,
        MlCountMtController cntCon
    ) throws TempFileException, SQLException {

        UDate startDate = PrjCommonBiz.createUDate(
                NullDefault.getInt(paramMdl.getPrj020startYear(), -1),
                NullDefault.getInt(paramMdl.getPrj020startMonth(), -1),
                NullDefault.getInt(paramMdl.getPrj020startDay(), -1));
        UDate andDate = PrjCommonBiz.createUDate(
                NullDefault.getInt(paramMdl.getPrj020endYear(), -1),
                NullDefault.getInt(paramMdl.getPrj020endMonth(), -1),
                NullDefault.getInt(paramMdl.getPrj020endDay(), -1));

        PrjPrjdataModel ppMdl = new PrjPrjdataModel();
        ppMdl.setPrjSid(projectSid);
        ppMdl.setPrjMyKbn(paramMdl.getPrj020prjMyKbn());
        ppMdl.setPrjId(paramMdl.getPrj020prjId());
        ppMdl.setPrjName(paramMdl.getPrj020prjName());
        ppMdl.setPrjNameShort(paramMdl.getPrj020prjNameS());
        ppMdl.setPrjYosan(NullDefault.getLong(paramMdl.getPrj020yosan(), GSConstCommon.NUM_INIT));
        ppMdl.setPrjKoukaiKbn(paramMdl.getPrj020koukai());
        ppMdl.setPrjDateStart(startDate);
        ppMdl.setPrjDateEnd(andDate);
        if (paramMdl.getPrj020prjMyKbn() == GSConstProject.KBN_MY_PRJ_MY) {
            ppMdl.setPrjStatusSid(GSConstProject.STATUS_MYPRJ);
        } else {
            ppMdl.setPrjStatusSid(paramMdl.getPrj020status());
        }
        ppMdl.setPrjTarget(paramMdl.getPrj020mokuhyou());
        ppMdl.setPrjContent(paramMdl.getPrj020naiyou());
        ppMdl.setPrjEdit(paramMdl.getPrj020kengen());
        ppMdl.setPrjMailKbn(paramMdl.getPrj020smailKbn());
        ppMdl.setPrjAuid(userSid);
        ppMdl.setPrjAdate(now);
        ppMdl.setPrjEuid(userSid);
        ppMdl.setPrjEdate(now);

        //アイコン情報を登録
        //バイナリー情報を更新する
        if (paramMdl.getPrj020cmdMode().equals(GSConstProject.CMD_MODE_EDIT)) {
            //編集時
            PrjPrjdataDao ppDao = new PrjPrjdataDao(con__);
            ppDao.deleteBinfProject(projectSid);
        }

        Long binSid = new Long(0);
        if (!NullDefault.getStringZeroLength(
                paramMdl.getPrj020IcoName(), "").equals("")
                && !NullDefault.getStringZeroLength(
                        paramMdl.getPrj020IcoSaveName(), "").equals("")) {
            CommonBiz cmnBiz = new CommonBiz();

            String filePath = tempDir
                             + GSConstProject.PLUGIN_ID_PROJECT + GSConstProject.TEMP_ICN_PRJ
                             + File.separator
                             + paramMdl.getPrj020IcoSaveName() + GSConstCommon.ENDSTR_SAVEFILE;

            binSid = cmnBiz.insertBinInfo(
                    con__, appRoot, cntCon, userSid, now, filePath, paramMdl.getPrj020IcoName());
        }

        ppMdl.setBinSid(binSid);
        return ppMdl;
    }

    /**
     * <br>[機  能] プロジェクトアドレス情報をDBに更新する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj020knParamModel
     * @param usrSid セッションユーザSID
     * @param newPrjSid 新規登録用プロジェクトSID
     * @throws SQLException SQL実行時例外
     */
    public void addPrjAddDB(Prj020knParamModel paramMdl, int usrSid, int newPrjSid)
        throws SQLException {

        UDate now = new UDate();
        PrjAddressDao addDao = new PrjAddressDao(con__);
        addDao.deletePrjAdd(paramMdl.getPrj030prjSid());

        String[] addressIdList = paramMdl.getPrj150AddressIdSv();

        if (addressIdList != null) {
            for (int i = 0; i < addressIdList.length; i++) {
                PrjAddressModel addressModel = new PrjAddressModel();
                String addSid = addressIdList[i];
                addressModel.setAdrSid(Integer.parseInt(addSid));
                addressModel.setPrjSid(newPrjSid);
                addressModel.setPraAuid(usrSid);
                addressModel.setPraAdate(now);
                addressModel.setPraEuid(usrSid);
                addressModel.setPraEdate(now);
                addressModel.setPraSort(0);
                addDao.insert(addressModel);
            }
        }
    }

    /**
     * <br>[機  能] プロジェクト会社情報をDBに更新する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Prj020knParamModel
     * @param usrSid セッションユーザSID
     * @param newPrjSid 新規登録用プロジェクトSID
     * @throws SQLException SQL実行時例外
     * @return 更新実行
     */
    public boolean addPrjComDB(Prj020knParamModel paramMdl, int usrSid, int newPrjSid)
        throws SQLException {

        UDate now = new UDate();
        PrjCompanyDao comDao = new PrjCompanyDao(con__);
        comDao.deletePrjCom(paramMdl.getPrj030prjSid());

        String[] companyIdList = paramMdl.getPrj150CompanySid();
        String[] companyBaseIdList
               = paramMdl.getPrj150CompanyBaseSid();

        if (companyIdList == null) {
            return false;
        }

        for (int i = 0; i < companyIdList.length; i++) {

            //会社SID、拠点SIDを取得
            int cntCom = companyIdList[i].toString().indexOf("d");
            int cntBaseCom = companyBaseIdList[i].toString().indexOf("d");
            String comSid = companyIdList[i].toString().substring(0, cntCom);
            String comBaseSid = companyBaseIdList[i].toString().substring(0, cntBaseCom);

            int comCnt = comDao.getprjComCount(paramMdl.getPrj030prjSid(),
                    Integer.parseInt(comSid),
                    Integer.parseInt(comBaseSid));

            if (comCnt != 0) {
                continue;
            }

            PrjCompanyModel companyModel = new PrjCompanyModel();

            companyModel.setAcoSid(Integer.parseInt(comSid));
            companyModel.setAbaSid(Integer.parseInt(comBaseSid));
            companyModel.setPrjSid(newPrjSid);
            companyModel.setPrcAuid(usrSid);
            companyModel.setPrcAdate(now);
            companyModel.setPrcEuid(usrSid);
            companyModel.setPrcEdate(now);

            if (comDao.update(companyModel) == 0) {
                comDao.insert(companyModel);
            }
        }
        return true;
    }
}
