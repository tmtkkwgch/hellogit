package jp.groupsession.v2.adr.adr170kn;

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
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr170.Adr170BinDao;
import jp.groupsession.v2.adr.adr170.Adr170Biz;
import jp.groupsession.v2.adr.dao.AddressDao;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrContactDao;
import jp.groupsession.v2.adr.dao.AdrContactPrjDao;
import jp.groupsession.v2.adr.model.AdrAddressModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrContactBinModel;
import jp.groupsession.v2.adr.model.AdrContactModel;
import jp.groupsession.v2.adr.model.AdrContactPrjModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 コンタクト履歴登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr170knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr170knBiz.class);
    /** コンタクト履歴SID */
    private int adcsid__ = 0;
    /** コンタクト履歴SID(同時登録) */
    private ArrayList<Integer> doziAdcSid__ = new ArrayList<Integer>();
    /** コネクション */
    private Connection con__ = null;
    /** リクエスト */
    protected RequestModel reqMdl_ = null;


    /**
     * <p>doziAdcSid を取得します。
     * @return doziAdcSid
     */
    public ArrayList<Integer> getDoziAdcSid() {
        return doziAdcSid__;
    }

    /**
     * <p>doziAdcSid をセットします。
     * @param doziAdcSid doziAdcSid
     */
    public void setDoziAdcSid(ArrayList<Integer> doziAdcSid) {
        doziAdcSid__ = doziAdcSid;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     */
    public Adr170knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Adr170knParamModel
     * @param con コネクション
     * @param buMdl セッションユーザModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Adr170knParamModel paramMdl, Connection con, BaseUserModel buMdl)
        throws SQLException {

        String year = NullDefault.getString(paramMdl.getAdr170enterContactYear(), "0000");
        String month = NullDefault.getString(paramMdl.getAdr170enterContactMonth(), "00");
        String day = NullDefault.getString(paramMdl.getAdr170enterContactDay(), "00");
        String hour = NullDefault.getString(paramMdl.getAdr170enterContactHour(), "00");
        String minute = NullDefault.getString(paramMdl.getAdr170enterContactMinute(), "00");
        String yearTo = NullDefault.getString(paramMdl.getAdr170enterContactYearTo(), "0000");
        String monthTo = NullDefault.getString(paramMdl.getAdr170enterContactMonthTo(), "00");
        String dayTo = NullDefault.getString(paramMdl.getAdr170enterContactDayTo(), "00");
        String hourTo = NullDefault.getString(paramMdl.getAdr170enterContactHourTo(), "00");
        String minuteTo = NullDefault.getString(paramMdl.getAdr170enterContactMinuteTo(), "00");

        //コンタクト日時From(表示用)セット
        paramMdl.setAdr170cttimeDsp(year
                              + "/"
                              + StringUtil.toDecFormat(month, "00")
                              + "/"
                              + StringUtil.toDecFormat(day, "00")
                              + " "
                              + StringUtil.toDecFormat(hour, "00")
                              + ":"
                              + StringUtil.toDecFormat(minute, "00"));
        //コンタクト日時To(表示用)セット
        paramMdl.setAdr170cttimeToDsp(yearTo
                              + "/"
                              + StringUtil.toDecFormat(monthTo, "00")
                              + "/"
                              + StringUtil.toDecFormat(dayTo, "00")
                              + " "
                              + StringUtil.toDecFormat(hourTo, "00")
                              + ":"
                              + StringUtil.toDecFormat(minuteTo, "00"));
//        //プロジェクト(表示用)セット
//        if (paramMdl.getAdr170enterContactProject().equals("-1")) {
//            paramMdl.setAdr170projectDsp("");
//        } else {
//            PrjPrjdataDao prjDao = new PrjPrjdataDao(con);
//            PrjPrjdataModel prjModel = prjDao.getProjectData(
//                    Integer.parseInt(paramMdl.getAdr170enterContactProject()));
//            paramMdl.setAdr170projectDsp(prjModel.getPrjName());
//        }
        //備考(表示用)セット
        paramMdl.setAdr170bikoDsp(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getAdr170biko()), ""));

        //同時登録ユーザ
        String[] saveUser = paramMdl.getSaveUser();

        AdrAddressDao adrDao = new AdrAddressDao(con);
        ArrayList<AdrAddressModel> doziUserList =
            adrDao.selectAdrList(saveUser, buMdl.getUsrsid());
        paramMdl.setAdr170knUser(doziUserList);

        String targetComName = "";
        String targetUsrName = "";
        int acoSid = -1;
        AdrCompanyDao comDao = new AdrCompanyDao(con);

        //コンタクト履歴登録対象者情報取得
        AdrAddressModel addressMdl = adrDao.select(paramMdl.getAdr010EditAdrSid());
        if (addressMdl != null) {

            targetUsrName =
                NullDefault.getString(addressMdl.getAdrSei(), "")
                + "  "
                + NullDefault.getString(addressMdl.getAdrMei(), "");

            acoSid = addressMdl.getAcoSid();
            if (acoSid > 0) {
                //会社情報を取得
                AdrCompanyModel companyModel = comDao.select(acoSid);
                if (companyModel != null) {
                    targetComName = companyModel.getAcoName();
                }
            }
        }
        paramMdl.setAdr170ContactUserComName(targetComName);
        paramMdl.setAdr170ContactUserName(targetUsrName);

        //表示用プロジェクトリストを設定する。
        __setProjectDspList(paramMdl, con);
    }

    /**
     * <br>[機  能] 添付ファイル情報をセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Adr170knParamModel
     * @param tempDir テンポラリディレクトリ
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setTempFiles(Adr170knParamModel paramMdl, String tempDir)
        throws IOToolsException {

        // 画面に表示するファイルのリストを作成、セット
        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setAdr170FileLabelList(commonBiz.getTempFileLabelList(tempDir));
    }

    /**
     * <br>[機  能] 稟議情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig プラグイン情報
     * @throws Exception 実行例外
     */
    public void entryData(Adr170knParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir,
                                PluginConfig pluginConfig)
    throws Exception {
        log__.debug("START");

        int oldAdcGrpSid = -1;
        if (paramMdl.getAdr160ProcMode() == GSConstAddress.PROCMODE_ADD) {
            //コンタクト履歴情報 登録
            doInsert(paramMdl, cntCon, userSid);
        } else if (paramMdl.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT) {
            //コンタクト履歴情報 更新
            oldAdcGrpSid = doUpdate(paramMdl, cntCon, userSid);
        }

        //バイナリー情報,コンタクト履歴添付情報 登録
        doInsertBin(paramMdl, tempDir, appRootPath, cntCon, userSid, oldAdcGrpSid);

        log__.debug("End");
    }

    /**
     * <br>[機  能] コンタクト履歴情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Adr170knParamModel paramMdl,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;
        int adcGrpSid = -1;

        try {

            con__.setAutoCommit(false);

            //コンタクト履歴SID採番
            int adcSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                       GSConst.SBNSID_SUB_CONTACT,
                                                       userSid);

            AdrAddressDao adrDao = new AdrAddressDao(con__);
            ArrayList<AdrAddressModel> doziUser =
                adrDao.selectAdrList(paramMdl.getSaveUser(), userSid);

            if (!doziUser.isEmpty()) {
                //コンタクト履歴グループSID採番
                adcGrpSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                         GSConst.SBNSID_SUB_CONTACT_GRP,
                                                         userSid);
            }

            //登録用Model作成
            AdrContactModel adcMdl = __getUpdateModel(adcSid, adcGrpSid, paramMdl, userSid);

            //insert
            AdrContactDao adcDao = new AdrContactDao(con__);
            adcDao.insert(adcMdl);

            if (!doziUser.isEmpty()) {

                ArrayList<Integer> doziAdcSidList = new ArrayList<Integer>();

                for (AdrAddressModel adrMdl : doziUser) {

                    //コンタクト履歴SID採番
                    int doziAdcSid =
                        (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                     GSConst.SBNSID_SUB_CONTACT,
                                                     userSid);

                    adcMdl.setAdcSid(doziAdcSid);
                    adcMdl.setAdrSid(adrMdl.getAdrSid());
                    adcDao.insert(adcMdl);

                    doziAdcSidList.add(doziAdcSid);
                }
                setDoziAdcSid(doziAdcSidList);
            }

            //プロジェクト情報を登録する。
            __insertProject(paramMdl, userSid, adcSid);

            //コンタクト履歴SIDセット
            adcsid__ = adcSid;

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
     * <br>[機  能] コンタクト履歴情報の更新,バイナリー情報の削除,コンタクト履歴添付情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Adr170knParamModel
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     * @return oldAdcGrpSid 既存データ同時登録SID
     */
    public int doUpdate(Adr170knParamModel paramMdl, MlCountMtController cntCon, int userSid)
    throws SQLException {

        boolean commitFlg = false;
        int oldAdcGrpSid = -1;

        try {

            con__.setAutoCommit(false);

            //コンタクト履歴SID
            int editAdcSid = paramMdl.getAdr160EditSid();

            AdrContactDao adcDao = new AdrContactDao(con__);
            AdrContactModel oldAdcMdl = adcDao.select(editAdcSid);
            adcDao.delete(editAdcSid);

            if (oldAdcMdl != null) {
                oldAdcGrpSid = oldAdcMdl.getAdcGrpSid();
            }

            //コンタクト履歴SID採番
            int adcSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                       GSConst.SBNSID_SUB_CONTACT,
                                                       userSid);

            AdrAddressDao adrDao = new AdrAddressDao(con__);
            ArrayList<AdrAddressModel> doziUser =
                adrDao.selectAdrList(paramMdl.getSaveUser(), userSid);

            int adcGrpSid = -1;
            if (!doziUser.isEmpty()) {
                //コンタクト履歴グループSID採番
                adcGrpSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                         GSConst.SBNSID_SUB_CONTACT_GRP,
                                                         userSid);
            }

            //登録用Model作成
            AdrContactModel adcMdl = __getUpdateModel(adcSid, adcGrpSid, paramMdl, userSid);

            //insert
            adcDao.insert(adcMdl);

            if (!doziUser.isEmpty()) {

                ArrayList<Integer> doziAdcSidList = new ArrayList<Integer>();

                for (AdrAddressModel adrMdl : doziUser) {

                    //コンタクト履歴SID採番
                    int doziAdcSid =
                        (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                     GSConst.SBNSID_SUB_CONTACT,
                                                     userSid);

                    adcMdl.setAdcSid(doziAdcSid);
                    adcMdl.setAdrSid(adrMdl.getAdrSid());
                    adcDao.insert(adcMdl);

                    doziAdcSidList.add(doziAdcSid);
                }
                setDoziAdcSid(doziAdcSidList);
            }

            //コンタクト履歴プロジェクト情報を更新する。
            __updateProject(paramMdl, userSid, adcSid, editAdcSid);

            //コンタクト履歴SIDセット
            adcsid__ = adcSid;

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
        return oldAdcGrpSid;
    }

    /**
     * <br>[機  能] コンタクト履歴情報の登録・更新用Modelを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param adcSid コンタクト履歴SID
     * @param adcGrpSid コンタクト履歴グループSID
     * @param paramMdl Adr170knParamModel
     * @param userSid ログインユーザSID
     * @return AdrContactModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private AdrContactModel __getUpdateModel(int adcSid,
                                              int adcGrpSid,
                                              Adr170knParamModel paramMdl,
                                              int userSid) throws SQLException {

        UDate now = new UDate();

        AdrContactModel mdl = new AdrContactModel();
        //コンタクト履歴SID
        mdl.setAdcSid(adcSid);
        //コンタクト履歴グループSID
        mdl.setAdcGrpSid(adcGrpSid);
        //アドレス帳SID
        mdl.setAdrSid(paramMdl.getAdr010EditAdrSid());
        //タイトル
        mdl.setAdcTitle(NullDefault.getString(paramMdl.getAdr170title(), ""));
        //コンタクト履歴種別
        mdl.setAdcType(paramMdl.getAdr170Mark());
        //コンタクト日時From
        mdl.setAdcCttime(__getCttime(paramMdl));
        //コンタクト日時To
        mdl.setAdcCttimeTo(__getCttimeTo(paramMdl));
//        //プロジェクトSID
//        if (paramMdl.getAdr170enterContactProject().equals("-1")) {
//            mdl.setPrjSid(0);
//        } else {
//            mdl.setPrjSid(Integer.parseInt(paramMdl.getAdr170enterContactProject()));
//        }
        //備考
        mdl.setAdcBiko(NullDefault.getString(paramMdl.getAdr170biko(), ""));

        mdl.setAdcAuid(userSid);
        mdl.setAdcAdate(now);
        mdl.setAdcEuid(userSid);
        mdl.setAdcEdate(now);

        return mdl;
    }

    /**
     * <br>[機  能] コンタクト日時Fromを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @return UDate コンタクト日時
     */
    private UDate __getCttime(Adr170knParamModel paramMdl) {

        UDate ctdate = new UDate();
        ctdate.setTimeStamp(paramMdl.getAdr170enterContactYear()
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactMonth(), "00")
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactDay(), "00")
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactHour(), "00")
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactMinute(), "00")
                          + "00");
        return ctdate;
    }

    /**
     * <br>[機  能] コンタクト日時Toを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @return UDate コンタクト日時
     */
    private UDate __getCttimeTo(Adr170knParamModel paramMdl) {

        UDate ctdate = new UDate();
        ctdate.setTimeStamp(paramMdl.getAdr170enterContactYearTo()
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactMonthTo(), "00")
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactDayTo(), "00")
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactHourTo(), "00")
                          + StringUtil.toDecFormat(paramMdl.getAdr170enterContactMinuteTo(), "00")
                          + "00");
        return ctdate;
    }

    /**
     * <br>[機  能] 添付ファイルの登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @param tempDir テンポラリディレクトリ
     * @param appRootPath アプリケーションルート
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param oldGrpSid 既存データ同時登録SID
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void doInsertBin(
        Adr170knParamModel paramMdl,
        String tempDir,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        int oldGrpSid) throws SQLException, IOToolsException, IOException, TempFileException {

        boolean commitFlg = false;

        try {

            con__.setAutoCommit(false);
            UDate now = new UDate();

            //修正モードのときコンタクト履歴、バイナリ情報、コンタクト履歴添付情報を削除
            if (paramMdl.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT) {
                Adr170Biz biz = new Adr170Biz(reqMdl_);
                biz.deleteBin(con__, paramMdl.getAdr160EditSid(), userSid, oldGrpSid);

                if (oldGrpSid > 0) {
                    AdrContactDao contactDao = new AdrContactDao(con__);
                    ArrayList<AdrContactModel> doziAdrList =
                        contactDao.selectGrpList(paramMdl.getAdr160EditSid(), oldGrpSid);
                    AdrAddressDao adrDao = new AdrAddressDao(con__);

                    if (!doziAdrList.isEmpty()) {
                        for (AdrContactModel doziMdl : doziAdrList) {

                            //編集可能ならば
                            int adrSid = doziMdl.getAdrSid();
                            String[] saveUser = new String[1];
                            saveUser[0] = String.valueOf(adrSid);
                            ArrayList<AdrAddressModel> doziUser =
                                adrDao.selectAdrList(saveUser, userSid);

                            if (!doziUser.isEmpty()) {
                                contactDao.delete(doziMdl.getAdcSid());
                            }
                        }
                    }
                }
            }

            //バイナリー情報を登録
            CommonBiz biz = new CommonBiz();
            List<String> binList =
                biz.insertBinInfo(con__, tempDir, appRootPath, cntCon, userSid, now);

            //コンタクト履歴添付情報を登録
            Adr170BinDao binDao = new Adr170BinDao(con__);
            AdrContactBinModel sparam = new AdrContactBinModel();
            sparam.setAdcSid(adcsid__);
            sparam.setAcbAuid(userSid);
            sparam.setAcbAdate(now);
            sparam.setAcbEuid(userSid);
            sparam.setAcbEdate(now);
            binDao.insertAdrBin(sparam, binList);

            ArrayList<Integer> doziAdcsid = getDoziAdcSid();
            if (!getDoziAdcSid().isEmpty()) {
                for (int doziAdcSid : doziAdcsid) {
                    //バイナリー情報を登録
                    List<String> doziBinList =
                        biz.insertSameBinInfo(
                                con__, tempDir, appRootPath, cntCon, userSid, now);

                    //コンタクト履歴添付情報を登録
                    AdrContactBinModel doziSparam = new AdrContactBinModel();
                    doziSparam.setAdcSid(doziAdcSid);
                    doziSparam.setAcbAuid(userSid);
                    doziSparam.setAcbAdate(now);
                    doziSparam.setAcbEuid(userSid);
                    doziSparam.setAcbEdate(now);
                    binDao.insertAdrBin(doziSparam, doziBinList);
                }
            }

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
     * <br>[機  能] プロジェクト表示リストを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    private void __setProjectDspList(
            Adr170knParamModel paramMdl, Connection con) throws SQLException {

        String[] prjSids = paramMdl.getAdr170ProjectSid();
        if (prjSids == null || prjSids.length < 1) {
            paramMdl.setAdr170ProjectList(null);
            return;
        }
        AddressDao adrDao = new AddressDao(con);
        List<LabelValueBean> prjList = adrDao.getProjectData(prjSids);

        paramMdl.setAdr170ProjectList(prjList);

    }

    /**
     * <br>[機  能] コンタクト履歴プロジェクト情報を登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @param userSid ユーザSID
     * @param adcSid コンタクト履歴SID
     * @throws SQLException SQL実行例外
     */
    private void __insertProject(
            Adr170knParamModel paramMdl, int userSid, int adcSid) throws SQLException {

        String[] prjSids = paramMdl.getAdr170ProjectSid();
        if (prjSids == null || prjSids.length < 1) {
            return;
        }

        AdrContactPrjDao aprjDao = new AdrContactPrjDao(con__);
        AdrContactPrjModel aprjModel = new AdrContactPrjModel();
        UDate now = new UDate();
        aprjModel.setAdcAuid(userSid);
        aprjModel.setAdcAdate(now);
        aprjModel.setAdcEuid(userSid);
        aprjModel.setAdcEdate(now);
        aprjModel.setAdcSid(adcSid);

        for (String projectSid : prjSids) {
            aprjModel.setPrjSid(Integer.parseInt(projectSid));
            aprjDao.insert(aprjModel);
        }
    }

    /**
     * <br>[機  能] コンタクト履歴プロジェクト情報を更新する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170knParamModel
     * @param userSid ユーザSID
     * @param adcSid 新コンタクト履歴SID
     * @param editAdcSid コンタクト履歴SID
     * @throws SQLException SQL実行例外
     */
    private void __updateProject(
            Adr170knParamModel paramMdl, int userSid, int adcSid, int editAdcSid)
    throws SQLException {

        AdrContactPrjDao aprjDao = new AdrContactPrjDao(con__);

        //コンタクト履歴プロジェクト情報を削除する
        aprjDao.delete(editAdcSid);

        //コンタクト履歴プロジェクト情報を登録する
        __insertProject(paramMdl, userSid, adcSid);

    }

}