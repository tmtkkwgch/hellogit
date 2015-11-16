package jp.groupsession.v2.adr.adr170;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AddressDao;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrContactBinDao;
import jp.groupsession.v2.adr.dao.AdrContactDao;
import jp.groupsession.v2.adr.dao.AdrContactPrjDao;
import jp.groupsession.v2.adr.model.AdrAddressModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrContactModel;
import jp.groupsession.v2.adr.model.AdrContactPrjModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;


/**
 * <br>[機  能] アドレス帳 コンタクト履歴登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr170Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr170Biz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

//    /**
//     * <br>[機  能] デフォルトコンストラクタ
//     * <br>[解  説]
//     * <br>[備  考]
//     */
//    public Adr170Biz() {
//    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr170Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr170ParamModel
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param buMdl セッションユーザModel
     * @param pconfig プラグインコンフィグ
     * @throws IOException 添付ファイルの操作に失敗
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void getInitData(Connection con,
                              Adr170ParamModel paramMdl,
                              String appRoot,
                              String tempDir,
                              BaseUserModel buMdl,
                              PluginConfig pconfig)
    throws SQLException, IOToolsException, IOException {

        String targetComName = "";
        String targetUsrName = "";
        int acoSid = -1;
        AdrCompanyDao comDao = new AdrCompanyDao(con);

        //コンタクト履歴登録対象者情報取得
        AdrAddressDao adrDao = new AdrAddressDao(con);
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

        //初回は会社コンボの選択値を、対象ユーザの会社とする
        //(※会社に所属していない場合は-1(選択してください)
        if (paramMdl.getAdr170SelectedComComboSid() < -1) {
            paramMdl.setAdr170SelectedComComboSid(acoSid);
        }

        int editSid = paramMdl.getAdr160EditSid();
        UDate now = new UDate();

        //コンボセット
        __setCombo(paramMdl, con, buMdl);

        //添付ファイルセット
        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setAdr170FileLabelList(commonBiz.getTempFileLabelList(tempDir));

        //画面初期値セット
        String title = "";
        int type = paramMdl.getAdr170Mark();

        String yearFrom = String.valueOf(now.getYear());
        String monthFrom = String.valueOf(now.getMonth());
        String dayFrom = String.valueOf(now.getIntDay());
        String hourFrom = String.valueOf(now.getIntHour());
        String minuteFrom = String.valueOf(now.getIntMinute());
        String yearTo = String.valueOf(now.getYear());
        String monthTo = String.valueOf(now.getMonth());
        String dayTo = String.valueOf(now.getIntDay());
        String hourTo = String.valueOf(now.getIntHour());
        String minuteTo = String.valueOf(now.getIntMinute());
        String project = "-1";
        String biko = "";
        int adcSid = 0;

        //同時登録ユーザ
        String[] saveUser = paramMdl.getSaveUser();

        //編集の場合、コンタクト履歴情報取得
        if (paramMdl.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT) {
            AdrContactDao adcDao = new AdrContactDao(con);
            AdrContactModel adcMdl = adcDao.select(editSid);
            if (adcMdl != null) {

                UDate cttime = NullDefault.getUDate(adcMdl.getAdcCttime(), now);
                UDate cttimeto = NullDefault.getUDate(adcMdl.getAdcCttimeTo(), now);

                title = adcMdl.getAdcTitle();
                type = adcMdl.getAdcType();
                yearFrom = String.valueOf(cttime.getYear());
                monthFrom = String.valueOf(cttime.getMonth());
                dayFrom = String.valueOf(cttime.getIntDay());
                hourFrom = String.valueOf(cttime.getIntHour());
                minuteFrom = String.valueOf(cttime.getIntMinute());
                yearTo = String.valueOf(cttimeto.getYear());
                monthTo = String.valueOf(cttimeto.getMonth());
                dayTo = String.valueOf(cttimeto.getIntDay());
                hourTo = String.valueOf(cttimeto.getIntHour());
                minuteTo = String.valueOf(cttimeto.getIntMinute());
                project = String.valueOf(adcMdl.getPrjSid());
                biko = adcMdl.getAdcBiko();
                adcSid = adcMdl.getAdcSid();

                //同時登録データがある場合
                int adcGrpSid = adcMdl.getAdcGrpSid();

                if ((saveUser == null || saveUser.length == 0)
                        && adcGrpSid > 0) {

                    //同時登録したアドレス帳のSIDを取得
                    ArrayList<Integer> intGrpAdrSid =
                        adcDao.selectGrpAdrSid(adcGrpSid);

                    if (!intGrpAdrSid.isEmpty()) {

                        ArrayList<String> convGrpAdrSid = new ArrayList<String>();
                        int myAdrSid = adcMdl.getAdrSid();

                        for (int grpAdrSid : intGrpAdrSid) {
                            //自分自身は除外する
                            if (myAdrSid != grpAdrSid) {
                                convGrpAdrSid.add(String.valueOf(grpAdrSid));
                            }
                        }

                        if (!convGrpAdrSid.isEmpty()) {
                            saveUser =
                                (String[]) convGrpAdrSid.toArray(
                                        new String[convGrpAdrSid.size()]);
                        }
                    }
                }
            }
        }

        //選択済ユーザ
        ArrayList<AdrAddressModel> grpSelectUserList =
            adrDao.selectAdrList(saveUser, buMdl.getUsrsid());
        paramMdl.setAdr170SelectLeftUser(grpSelectUserList);

        ArrayList<String> convGrpAdrSid = new ArrayList<String>();
        for (AdrAddressModel adrMdl : grpSelectUserList) {
            convGrpAdrSid.add(String.valueOf(adrMdl.getAdrSid()));
        }

        paramMdl.setSaveUser(
                (String[]) convGrpAdrSid.toArray(
                        new String[convGrpAdrSid.size()]));

        //会社コンボ
        GsMessage gsMsg = new GsMessage(reqMdl_);
        ArrayList<LabelValueBean> comList = new ArrayList<LabelValueBean>();
        comList.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));
        comList.add(new LabelValueBean(gsMsg.getMessage("address.src.34"), "0"));

        ArrayList<AdrCompanyModel> dbComList = comDao.getSelectComList();

        for (AdrCompanyModel comMdl : dbComList) {
            comList.add(
                    new LabelValueBean(comMdl.getAcoName(),
                            String.valueOf(comMdl.getAcoSid())));
        }

        paramMdl.setAdr170ComLabelList(comList);

        //会社コンボ選択値
        int selectCom = paramMdl.getAdr170SelectedComComboSid();

        //未選択ユーザ
        int myAdrSid = paramMdl.getAdr010EditAdrSid();
        ArrayList<AdrAddressModel> grpUserList =
            adrDao.selectAcoUsrList(saveUser, myAdrSid, selectCom, buMdl.getUsrsid());
        paramMdl.setAdr170SelectRightUser(grpUserList);

        //画面値セット
        //タイトル
        paramMdl.setAdr170title(NullDefault.getString(paramMdl.getAdr170title(), title));

        //種別
        if (paramMdl.getAdr170Mark() == -1) {
            paramMdl.setAdr170Mark(type);
        }
        //コンタクト日付From
        paramMdl.setAdr170enterContactYear(NullDefault.getString(
                paramMdl.getAdr170enterContactYear(), yearFrom));
        paramMdl.setAdr170enterContactMonth(NullDefault.getString(
                paramMdl.getAdr170enterContactMonth(), monthFrom));
        paramMdl.setAdr170enterContactDay(NullDefault.getString(
                paramMdl.getAdr170enterContactDay(), dayFrom));
        paramMdl.setAdr170enterContactHour(NullDefault.getString(
                paramMdl.getAdr170enterContactHour(), hourFrom));
        paramMdl.setAdr170enterContactMinute(NullDefault.getString(
                paramMdl.getAdr170enterContactMinute(), minuteFrom));
        //コンタクト日付To
        paramMdl.setAdr170enterContactYearTo(NullDefault.getString(
                paramMdl.getAdr170enterContactYearTo(), yearTo));
        paramMdl.setAdr170enterContactMonthTo(NullDefault.getString(
                paramMdl.getAdr170enterContactMonthTo(), monthTo));
        paramMdl.setAdr170enterContactDayTo(NullDefault.getString(
                paramMdl.getAdr170enterContactDayTo(), dayTo));
        paramMdl.setAdr170enterContactHourTo(NullDefault.getString(
                paramMdl.getAdr170enterContactHourTo(), hourTo));
        paramMdl.setAdr170enterContactMinuteTo(NullDefault.getString(
                paramMdl.getAdr170enterContactMinuteTo(), minuteTo));
        //プロジェクト
        paramMdl.setAdr170enterContactProject(NullDefault.getString(
                paramMdl.getAdr170enterContactProject(), project));
        //備考
        paramMdl.setAdr170biko(NullDefault.getString(paramMdl.getAdr170biko(), biko));

        //プロジェクトプラグイン使用有無
        if (pconfig.getPlugin("project") != null) {
            log__.debug("プロジェクト使用");
            //プロジェクト情報を設定する。
            __setProjectData(paramMdl, con, adcSid);

            //プロジェクト表示リストを設定する。
            __setProjectDspList(paramMdl, con);

            paramMdl.setProjectPluginKbn(GSConst.PLUGIN_USE);

        } else {
            paramMdl.setProjectPluginKbn(GSConst.PLUGIN_NOT_USE);
            log__.debug("プロジェクト使用不可");
        }


    }

    /**
     * <br>[機  能] コンボに格納する値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170ParamModel
     * @param con コネクション
     * @param buMdl セッションユーザModel
     * @throws SQLException SQL実行例外
     */
    private void __setCombo(Adr170ParamModel paramMdl,
                             Connection con,
                             BaseUserModel buMdl) throws SQLException {

        UDate dspDate = new UDate();

        //コンタクト日時コンボ
        paramMdl.setAdr170yearLabelList(__getYearLabel(dspDate.getYear()));
        paramMdl.setAdr170monthLabelList(__getMonthLabel());
        paramMdl.setAdr170dayLabelList(__getDayLabel());
        paramMdl.setAdr170hourLabelList(__getHourLabel());
        paramMdl.setAdr170minuteLabelList(__getMinuteLabel());

    }

    /**
     * <br>表示開始日から年コンボを生成します
     * @param year 基準年
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    private ArrayList<LabelValueBean> __getYearLabel(int year) {
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        for (int i = year - 10; i < year + 10; i++) {
            labelList.add(
                    new LabelValueBean(gsMsg.getMessage(
                            "cmn.year",
                            new String[] {String.valueOf(i)}), String.valueOf(i)));
        }
        return labelList;
    }

    /**
     * <br>月コンボを生成します
     * @return ArrayList (in LabelValueBean)  月コンボ
     */
    private ArrayList<LabelValueBean> __getMonthLabel() {
        int month = 1;
        GsMessage gsMsg = new GsMessage(reqMdl_);
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(month
                            + gsMsg.getMessage("cmn.month"), String.valueOf(month)));
            month++;
        }
        return labelList;
    }

    /**
     * <br>日コンボを生成します
     * @return ArrayList (in LabelValueBean)  日コンボ
     */
    private ArrayList<LabelValueBean> __getDayLabel() {
        int day = 1;
        GsMessage gsMsg = new GsMessage(reqMdl_);
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day
                            + gsMsg.getMessage("cmn.day"), String.valueOf(day)));
            day++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 時間コンボを生成
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  時間コンボ
     */
    private ArrayList<LabelValueBean> __getHourLabel() {
        int hour = 0;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i <= 23; i++) {
            labelList.add(
                    new LabelValueBean(String.valueOf(hour), String.valueOf(hour)));
            hour++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 分コンボを生成
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  分コンボ
     */
    private ArrayList<LabelValueBean> __getMinuteLabel() {
        int min = 0;
        int minMem = 60 / 5;
        int minCount = 60 / minMem;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < minMem; i++) {
            labelList.add(
                    new LabelValueBean(StringUtil.toDecFormat(min, "00"), String.valueOf(min)));
            min = min + minCount;
        }
        return labelList;
    }

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
     * <br>[機  能] コンタクト履歴SIDからコンタクト履歴情報を取得し、削除確認メッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param albSid コンタクト履歴SID
     * @param msgRes MessageResources
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePosMsg(Connection con,
                                  int albSid,
                                  MessageResources msgRes)
    throws SQLException {

        String msg = "";

        //コンタクト履歴タイトル取得
        AdrContactDao adcDao = new AdrContactDao(con);
        AdrContactModel adcMdl = adcDao.select(albSid);
        String albName = "";
        if (adcMdl != null) {
            albName = NullDefault.getString(adcMdl.getAdcTitle(), "");
        }
        GsMessage gsMsg = new GsMessage(reqMdl_);
        msg = msgRes.getMessage("sakujo.kakunin.list", gsMsg.getMessage("address.6"),
                StringUtilHtml.transToHTmlPlusAmparsant(albName));

        return msg;
    }

    /**
     * <br>[機  能] コンタクト履歴を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr170ParamModel
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void deleteAlb(
            Connection con, Adr170ParamModel paramMdl, int userSid) throws SQLException {

        int editAlbSid = paramMdl.getAdr160EditSid();
        boolean commitFlg = false;

        try {

            AdrAddressDao adrDao = new AdrAddressDao(con);
            AdrContactDao adcDao = new AdrContactDao(con);
            AdrContactModel adcMdl = adcDao.select(editAlbSid);

            int adcGrpSid = -1;
            if (adcMdl != null) {

                adcGrpSid = adcMdl.getAdcGrpSid();

                //バイナリ情報,コンタクト履歴添付情報を削除
                deleteBin(con, editAlbSid, userSid, adcGrpSid);

                //コンタクト履歴情報を物理削除する
                if (adcGrpSid > 0) {
                    AdrContactDao contactDao = new AdrContactDao(con);
                    ArrayList<AdrContactModel> doziAdrList =
                        contactDao.selectGrpList(-1, adcGrpSid);

                    if (!doziAdrList.isEmpty()) {
                        for (AdrContactModel doziMdl : doziAdrList) {

                            //編集可能ならば
                            int adrSid = doziMdl.getAdrSid();
                            String[] saveUser = new String[1];
                            saveUser[0] = String.valueOf(adrSid);
                            ArrayList<AdrAddressModel> doziUser =
                                adrDao.selectAdrList(saveUser, userSid);

                            if (!doziUser.isEmpty()) {
                                adcDao.delete(doziMdl.getAdcSid());
                            }
                        }
                    }
                } else {
                    adcDao.delete(editAlbSid);
                }
                //コンタクト履歴プロジェクト情報を削除する。
                AdrContactPrjDao aprjDao = new AdrContactPrjDao(con);
                aprjDao.delete(adcMdl.getAdcSid());
            }

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
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
     * <br>[機  能] コンタクト履歴添付情報を元に添付ファイルを指定したテンポラリディレクトリに作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param adcSid コンタクト履歴SID
     * @param userSid ユーザSID
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param now 現在日時
     * @param domain ドメイン
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setTempFileData(Connection con,
                                int adcSid,
                                int userSid,
                                String appRoot,
                                String tempDir,
                                UDate now,
                                String domain)
    throws SQLException, IOException, IOToolsException, TempFileException {

        String dateStr = now.getDateString(); //現在日付の文字列(YYYYMMDD)
        AdrContactBinDao binDao = new AdrContactBinDao(con);
        CommonBiz cmnBiz = new CommonBiz();

        String[] binSids = binDao.getTmpFileList(adcSid, userSid);
        if (binSids == null || binSids.length < 1) {
            return;
        }
        List<CmnBinfModel> binList = cmnBiz.getBinInfo(con, binSids, domain);

        int fileNum = 1;
        for (CmnBinfModel binData : binList) {
            cmnBiz.saveTempFile(dateStr, binData, appRoot, tempDir, fileNum);
            fileNum++;
        }
    }

    /**
     * バイナリ情報,コンタクト履歴添付情報を削除
     * @param con コネクション
     * @param adcsid コンタクト履歴SID
     * @param userSid ユーザSID
     * @param adcGrpSid 同時登録グループSID
     * @throws SQLException SQL実行例外
     */
    public void deleteBin(Connection con, int adcsid, int userSid, int adcGrpSid)
        throws SQLException {

        //コンタクト履歴添付情報からバイナリSID一覧取得
        Adr170BinDao cbinDao = new Adr170BinDao(con);
        List<Long> binSidList = cbinDao.selectBinSidList(adcsid);

        //バイナリ情報を論理削除
        CmnBinfDao delbinDao = new CmnBinfDao(con);
        CmnBinfModel cbMdl = new CmnBinfModel();
        cbMdl.setBinJkbn(GSConst.JTKBN_DELETE);
        cbMdl.setBinUpuser(userSid);
        cbMdl.setBinUpdate(new UDate());
        delbinDao.updateJKbn(cbMdl, binSidList);

        //コンタクト履歴添付情報を物理削除
        cbinDao.deleteBin(adcsid);

        AdrAddressDao adrDao = new AdrAddressDao(con);

        if (adcGrpSid > 0) {

            AdrContactDao adcDao = new AdrContactDao(con);
            ArrayList<AdrContactModel> doziAdrList =
                adcDao.selectGrpList(adcsid, adcGrpSid);

            if (!doziAdrList.isEmpty()) {
                for (AdrContactModel doziMdl : doziAdrList) {

                    //編集可能ならば
                    int adrSid = doziMdl.getAdrSid();
                    String[] saveUser = new String[1];
                    saveUser[0] = String.valueOf(adrSid);
                    ArrayList<AdrAddressModel> doziUser =
                        adrDao.selectAdrList(saveUser, userSid);

                    if (!doziUser.isEmpty()) {
                        //コンタクト履歴添付情報からバイナリSID一覧取得
                        List<Long> doziBinSidList =
                            cbinDao.selectBinSidList(doziMdl.getAdcSid());

                        //バイナリ情報を論理削除
                        cbMdl.setBinJkbn(GSConst.JTKBN_DELETE);
                        cbMdl.setBinUpuser(userSid);
                        cbMdl.setBinUpdate(new UDate());
                        delbinDao.updateJKbn(cbMdl, doziBinSidList);

                        //コンタクト履歴添付情報を物理削除
                        cbinDao.deleteBin(doziMdl.getAdcSid());
                    }
                }
            }
        }
    }

    /**
     * <br>[機  能] プロジェクト表示リストを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    private void __setProjectDspList(
            Adr170ParamModel paramMdl, Connection con) throws SQLException {

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
     * <br>[機  能] DBよりプロジェクト情報を取得し、設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr170ParamModel
     * @param con コネクション
     * @param adcSid コンタクトSID
     * @throws SQLException SQL実行例外
     */
    private void __setProjectData(
            Adr170ParamModel paramMdl, Connection con, int adcSid) throws SQLException {

        if (paramMdl.getAdr160ProcMode() == GSConstAddress.PROCMODE_ADD
                || adcSid < 1
                || paramMdl.getProjectPluginKbn() > -1) {
            return;
        }
        AdrContactPrjDao aprjDao = new  AdrContactPrjDao(con);
        List<AdrContactPrjModel> prjList = aprjDao.select(adcSid);

        if (prjList != null && prjList.size() > 0) {
            String[] prjSids = new String[prjList.size()];
            int i = 0;
            for (AdrContactPrjModel model : prjList) {
                prjSids[i] = String.valueOf(model.getPrjSid());
                i++;
            }
            paramMdl.setAdr170ProjectSid(prjSids);
        }

    }
}