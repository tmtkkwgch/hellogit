package jp.groupsession.v2.adr.adr170;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr161.Adr161Form;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrContactDao;
import jp.groupsession.v2.adr.model.AdrAddressModel;
import jp.groupsession.v2.adr.model.AdrContactModel;
import jp.groupsession.v2.adr.util.AdrValidateUtil;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 コンタクト履歴登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr170Form extends Adr161Form {

    /** タイトル */
    private String adr170title__ = null;
    /** マーク */
    private int adr170Mark__ = -1;
    /** コンタクト日時From 年 (選択値) */
    private String adr170enterContactYear__ = null;
    /** コンタクト日時To 年 (選択値) */
    private String adr170enterContactYearTo__ = null;
    /** コンタクト日時From 月 (選択値) */
    private String adr170enterContactMonth__ = null;
    /** コンタクト日時To 月 (選択値) */
    private String adr170enterContactMonthTo__ = null;
    /** コンタクト日時From 日 (選択値) */
    private String adr170enterContactDay__ = null;
    /** コンタクト日時To 日 (選択値) */
    private String adr170enterContactDayTo__ = null;
    /** コンタクト日時From 時 (選択値) */
    private String adr170enterContactHour__ = null;
    /** コンタクト日時To 時 (選択値) */
    private String adr170enterContactHourTo__ = null;
    /** コンタクト日時From 分 (選択値) */
    private String adr170enterContactMinute__ = null;
    /** コンタクト日時To 分 (選択値) */
    private String adr170enterContactMinuteTo__ = null;
    /** プロジェクト (選択値) */
    private String adr170enterContactProject__ = null;
    /** 備考 */
    private String adr170biko__ = null;

    /** コンタクト日時 年コンボ */
    private List < LabelValueBean > adr170yearLabelList__ = null;
    /** コンタクト日時 月コンボ */
    private List < LabelValueBean > adr170monthLabelList__ = null;
    /** コンタクト日時 日コンボ */
    private List < LabelValueBean > adr170dayLabelList__ = null;
    /** コンタクト日時 時コンボ */
    private List < LabelValueBean > adr170hourLabelList__ = null;
    /** コンタクト日時 分コンボ */
    private List < LabelValueBean > adr170minuteLabelList__ = null;
    /** プロジェクト コンボ */
    private List < LabelValueBean > adr170projectLabelList__ = null;

    /** 添付ファイル(コンボで選択中) */
    private String[] adr170selectFiles__ = null;
    /** ファイルコンボ */
    private List<LabelValueBean> adr170FileLabelList__ = null;
    /** プラグインID */
    private String adr170pluginId__ = GSConstAddress.PLUGIN_ID_ADDRESS;

    //同時登録用
    /** 会社コンボ選択値 */
    private int adr170SelectedComComboSid__ = -2;
    /** 会社コンボリスト */
    private ArrayList<LabelValueBean> adr170ComLabelList__ = null;

    /** 同時登録ユーザセーブ */
    private String[] saveUser__ = null;

    /** 選択済ユーザ(左) */
    private String[] adr170SelectedLeft__ = null;
    /** セレクトエリア(左) */
    private ArrayList<AdrAddressModel> adr170SelectLeftUser__ = null;

    /** 選択済ユーザ(右) */
    private String[] adr170SelectedRight__ = null;
    /** セレクトエリア(右) */
    private ArrayList<AdrAddressModel> adr170SelectRightUser__ = null;

    /** コンタクト履歴 登録対象者情報 会社名 */
    private String adr170ContactUserComName__ = null;
    /** コンタクト履歴 登録対象者情報 氏名 */
    private String adr170ContactUserName__ = null;

    /** プロジェクトSID */
    private String[] adr170ProjectSid__ = null;
    /** プロジェクトリスト */
    List <LabelValueBean> adr170ProjectList__ = null;
    /** 削除対象のプロジェクトSID */
    private String adr170delProjectSid__ = null;
//    /** プロジェクトプラグイン使用有無 0=使用 1=未使用*/
//    private int projectPluginKbn__ = -1;

    /**
     * <p>adr170biko を取得します。
     * @return adr170biko
     */
    public String getAdr170biko() {
        return adr170biko__;
    }

    /**
     * <p>adr170biko をセットします。
     * @param adr170biko adr170biko
     */
    public void setAdr170biko(String adr170biko) {
        adr170biko__ = adr170biko;
    }

    /**
     * <p>adr170dayLabelList を取得します。
     * @return adr170dayLabelList
     */
    public List<LabelValueBean> getAdr170dayLabelList() {
        return adr170dayLabelList__;
    }

    /**
     * <p>adr170dayLabelList をセットします。
     * @param adr170dayLabelList adr170dayLabelList
     */
    public void setAdr170dayLabelList(List<LabelValueBean> adr170dayLabelList) {
        adr170dayLabelList__ = adr170dayLabelList;
    }

    /**
     * <p>adr170enterContactDay を取得します。
     * @return adr170enterContactDay
     */
    public String getAdr170enterContactDay() {
        return adr170enterContactDay__;
    }

    /**
     * <p>adr170enterContactDay をセットします。
     * @param adr170enterContactDay adr170enterContactDay
     */
    public void setAdr170enterContactDay(String adr170enterContactDay) {
        adr170enterContactDay__ = adr170enterContactDay;
    }

    /**
     * <p>adr170enterContactHour を取得します。
     * @return adr170enterContactHour
     */
    public String getAdr170enterContactHour() {
        return adr170enterContactHour__;
    }

    /**
     * <p>adr170enterContactHour をセットします。
     * @param adr170enterContactHour adr170enterContactHour
     */
    public void setAdr170enterContactHour(String adr170enterContactHour) {
        adr170enterContactHour__ = adr170enterContactHour;
    }

    /**
     * <p>adr170enterContactMinute を取得します。
     * @return adr170enterContactMinute
     */
    public String getAdr170enterContactMinute() {
        return adr170enterContactMinute__;
    }

    /**
     * <p>adr170enterContactMinute をセットします。
     * @param adr170enterContactMinute adr170enterContactMinute
     */
    public void setAdr170enterContactMinute(String adr170enterContactMinute) {
        adr170enterContactMinute__ = adr170enterContactMinute;
    }

    /**
     * <p>adr170enterContactMonth を取得します。
     * @return adr170enterContactMonth
     */
    public String getAdr170enterContactMonth() {
        return adr170enterContactMonth__;
    }

    /**
     * <p>adr170enterContactMonth をセットします。
     * @param adr170enterContactMonth adr170enterContactMonth
     */
    public void setAdr170enterContactMonth(String adr170enterContactMonth) {
        adr170enterContactMonth__ = adr170enterContactMonth;
    }

    /**
     * <p>adr170enterContactProject を取得します。
     * @return adr170enterContactProject
     */
    public String getAdr170enterContactProject() {
        return adr170enterContactProject__;
    }

    /**
     * <p>adr170enterContactProject をセットします。
     * @param adr170enterContactProject adr170enterContactProject
     */
    public void setAdr170enterContactProject(String adr170enterContactProject) {
        adr170enterContactProject__ = adr170enterContactProject;
    }

    /**
     * <p>adr170enterContactYear を取得します。
     * @return adr170enterContactYear
     */
    public String getAdr170enterContactYear() {
        return adr170enterContactYear__;
    }

    /**
     * <p>adr170enterContactYear をセットします。
     * @param adr170enterContactYear adr170enterContactYear
     */
    public void setAdr170enterContactYear(String adr170enterContactYear) {
        adr170enterContactYear__ = adr170enterContactYear;
    }

    /**
     * <p>adr170hourLabelList を取得します。
     * @return adr170hourLabelList
     */
    public List<LabelValueBean> getAdr170hourLabelList() {
        return adr170hourLabelList__;
    }

    /**
     * <p>adr170hourLabelList をセットします。
     * @param adr170hourLabelList adr170hourLabelList
     */
    public void setAdr170hourLabelList(List<LabelValueBean> adr170hourLabelList) {
        adr170hourLabelList__ = adr170hourLabelList;
    }

    /**
     * <p>adr170minuteLabelList を取得します。
     * @return adr170minuteLabelList
     */
    public List<LabelValueBean> getAdr170minuteLabelList() {
        return adr170minuteLabelList__;
    }

    /**
     * <p>adr170minuteLabelList をセットします。
     * @param adr170minuteLabelList adr170minuteLabelList
     */
    public void setAdr170minuteLabelList(List<LabelValueBean> adr170minuteLabelList) {
        adr170minuteLabelList__ = adr170minuteLabelList;
    }

    /**
     * <p>adr170monthLabelList を取得します。
     * @return adr170monthLabelList
     */
    public List<LabelValueBean> getAdr170monthLabelList() {
        return adr170monthLabelList__;
    }

    /**
     * <p>adr170monthLabelList をセットします。
     * @param adr170monthLabelList adr170monthLabelList
     */
    public void setAdr170monthLabelList(List<LabelValueBean> adr170monthLabelList) {
        adr170monthLabelList__ = adr170monthLabelList;
    }

    /**
     * <p>adr170projectLabelList を取得します。
     * @return adr170projectLabelList
     */
    public List<LabelValueBean> getAdr170projectLabelList() {
        return adr170projectLabelList__;
    }

    /**
     * <p>adr170projectLabelList をセットします。
     * @param adr170projectLabelList adr170projectLabelList
     */
    public void setAdr170projectLabelList(
            List<LabelValueBean> adr170projectLabelList) {
        adr170projectLabelList__ = adr170projectLabelList;
    }

    /**
     * <p>adr170title を取得します。
     * @return adr170title
     */
    public String getAdr170title() {
        return adr170title__;
    }

    /**
     * <p>adr170title をセットします。
     * @param adr170title adr170title
     */
    public void setAdr170title(String adr170title) {
        adr170title__ = adr170title;
    }

    /**
     * <p>adr170yearLabelList を取得します。
     * @return adr170yearLabelList
     */
    public List<LabelValueBean> getAdr170yearLabelList() {
        return adr170yearLabelList__;
    }

    /**
     * <p>adr170yearLabelList をセットします。
     * @param adr170yearLabelList adr170yearLabelList
     */
    public void setAdr170yearLabelList(List<LabelValueBean> adr170yearLabelList) {
        adr170yearLabelList__ = adr170yearLabelList;
    }

    /**
     * <p>adr170FileLabelList を取得します。
     * @return adr170FileLabelList
     */
    public List<LabelValueBean> getAdr170FileLabelList() {
        return adr170FileLabelList__;
    }

    /**
     * <p>adr170FileLabelList をセットします。
     * @param adr170FileLabelList adr170FileLabelList
     */
    public void setAdr170FileLabelList(List<LabelValueBean> adr170FileLabelList) {
        adr170FileLabelList__ = adr170FileLabelList;
    }

    /**
     * <p>adr170Mark を取得します。
     * @return adr170Mark
     */
    public int getAdr170Mark() {
        return adr170Mark__;
    }

    /**
     * <p>adr170Mark をセットします。
     * @param adr170Mark adr170Mark
     */
    public void setAdr170Mark(int adr170Mark) {
        adr170Mark__ = adr170Mark;
    }

    /**
     * <p>adr170pluginId を取得します。
     * @return adr170pluginId
     */
    public String getAdr170pluginId() {
        return adr170pluginId__;
    }

    /**
     * <p>adr170pluginId をセットします。
     * @param adr170pluginId adr170pluginId
     */
    public void setAdr170pluginId(String adr170pluginId) {
        adr170pluginId__ = adr170pluginId;
    }

    /**
     * <p>adr170selectFiles を取得します。
     * @return adr170selectFiles
     */
    public String[] getAdr170selectFiles() {
        return adr170selectFiles__;
    }

    /**
     * <p>adr170selectFiles をセットします。
     * @param adr170selectFiles adr170selectFiles
     */
    public void setAdr170selectFiles(String[] adr170selectFiles) {
        adr170selectFiles__ = adr170selectFiles;
    }

    /**
     * <p>adr170enterContactDayTo を取得します。
     * @return adr170enterContactDayTo
     */
    public String getAdr170enterContactDayTo() {
        return adr170enterContactDayTo__;
    }

    /**
     * <p>adr170enterContactDayTo をセットします。
     * @param adr170enterContactDayTo adr170enterContactDayTo
     */
    public void setAdr170enterContactDayTo(String adr170enterContactDayTo) {
        adr170enterContactDayTo__ = adr170enterContactDayTo;
    }

    /**
     * <p>adr170enterContactHourTo を取得します。
     * @return adr170enterContactHourTo
     */
    public String getAdr170enterContactHourTo() {
        return adr170enterContactHourTo__;
    }

    /**
     * <p>adr170enterContactHourTo をセットします。
     * @param adr170enterContactHourTo adr170enterContactHourTo
     */
    public void setAdr170enterContactHourTo(String adr170enterContactHourTo) {
        adr170enterContactHourTo__ = adr170enterContactHourTo;
    }

    /**
     * <p>adr170enterContactMinuteTo を取得します。
     * @return adr170enterContactMinuteTo
     */
    public String getAdr170enterContactMinuteTo() {
        return adr170enterContactMinuteTo__;
    }

    /**
     * <p>adr170enterContactMinuteTo をセットします。
     * @param adr170enterContactMinuteTo adr170enterContactMinuteTo
     */
    public void setAdr170enterContactMinuteTo(String adr170enterContactMinuteTo) {
        adr170enterContactMinuteTo__ = adr170enterContactMinuteTo;
    }

    /**
     * <p>adr170enterContactMonthTo を取得します。
     * @return adr170enterContactMonthTo
     */
    public String getAdr170enterContactMonthTo() {
        return adr170enterContactMonthTo__;
    }

    /**
     * <p>adr170enterContactMonthTo をセットします。
     * @param adr170enterContactMonthTo adr170enterContactMonthTo
     */
    public void setAdr170enterContactMonthTo(String adr170enterContactMonthTo) {
        adr170enterContactMonthTo__ = adr170enterContactMonthTo;
    }

    /**
     * <p>adr170enterContactYearTo を取得します。
     * @return adr170enterContactYearTo
     */
    public String getAdr170enterContactYearTo() {
        return adr170enterContactYearTo__;
    }

    /**
     * <p>adr170enterContactYearTo をセットします。
     * @param adr170enterContactYearTo adr170enterContactYearTo
     */
    public void setAdr170enterContactYearTo(String adr170enterContactYearTo) {
        adr170enterContactYearTo__ = adr170enterContactYearTo;
    }

    /**
     * <p>adr170ComLabelList を取得します。
     * @return adr170ComLabelList
     */
    public ArrayList<LabelValueBean> getAdr170ComLabelList() {
        return adr170ComLabelList__;
    }

    /**
     * <p>adr170ComLabelList をセットします。
     * @param adr170ComLabelList adr170ComLabelList
     */
    public void setAdr170ComLabelList(ArrayList<LabelValueBean> adr170ComLabelList) {
        adr170ComLabelList__ = adr170ComLabelList;
    }

    /**
     * <p>adr170SelectedComComboSid を取得します。
     * @return adr170SelectedComComboSid
     */
    public int getAdr170SelectedComComboSid() {
        return adr170SelectedComComboSid__;
    }

    /**
     * <p>adr170SelectedComComboSid をセットします。
     * @param adr170SelectedComComboSid adr170SelectedComComboSid
     */
    public void setAdr170SelectedComComboSid(int adr170SelectedComComboSid) {
        adr170SelectedComComboSid__ = adr170SelectedComComboSid;
    }

    /**
     * <p>saveUser を取得します。
     * @return saveUser
     */
    public String[] getSaveUser() {
        return saveUser__;
    }

    /**
     * <p>saveUser をセットします。
     * @param saveUser saveUser
     */
    public void setSaveUser(String[] saveUser) {
        saveUser__ = saveUser;
    }

    /**
     * <p>adr170SelectedLeft を取得します。
     * @return adr170SelectedLeft
     */
    public String[] getAdr170SelectedLeft() {
        return adr170SelectedLeft__;
    }

    /**
     * <p>adr170SelectedLeft をセットします。
     * @param adr170SelectedLeft adr170SelectedLeft
     */
    public void setAdr170SelectedLeft(String[] adr170SelectedLeft) {
        adr170SelectedLeft__ = adr170SelectedLeft;
    }
    /**
     * <p>adr170SelectedRight を取得します。
     * @return adr170SelectedRight
     */
    public String[] getAdr170SelectedRight() {
        return adr170SelectedRight__;
    }

    /**
     * <p>adr170SelectedRight をセットします。
     * @param adr170SelectedRight adr170SelectedRight
     */
    public void setAdr170SelectedRight(String[] adr170SelectedRight) {
        adr170SelectedRight__ = adr170SelectedRight;
    }
    /**
     * <p>adr170SelectLeftUser を取得します。
     * @return adr170SelectLeftUser
     */
    public ArrayList<AdrAddressModel> getAdr170SelectLeftUser() {
        return adr170SelectLeftUser__;
    }

    /**
     * <p>adr170SelectLeftUser をセットします。
     * @param adr170SelectLeftUser adr170SelectLeftUser
     */
    public void setAdr170SelectLeftUser(
            ArrayList<AdrAddressModel> adr170SelectLeftUser) {
        adr170SelectLeftUser__ = adr170SelectLeftUser;
    }

    /**
     * <p>adr170SelectRightUser を取得します。
     * @return adr170SelectRightUser
     */
    public ArrayList<AdrAddressModel> getAdr170SelectRightUser() {
        return adr170SelectRightUser__;
    }

    /**
     * <p>adr170SelectRightUser をセットします。
     * @param adr170SelectRightUser adr170SelectRightUser
     */
    public void setAdr170SelectRightUser(
            ArrayList<AdrAddressModel> adr170SelectRightUser) {
        adr170SelectRightUser__ = adr170SelectRightUser;
    }

    /**
     * <p>adr170ContactUserComName を取得します。
     * @return adr170ContactUserComName
     */
    public String getAdr170ContactUserComName() {
        return adr170ContactUserComName__;
    }

    /**
     * <p>adr170ContactUserComName をセットします。
     * @param adr170ContactUserComName adr170ContactUserComName
     */
    public void setAdr170ContactUserComName(String adr170ContactUserComName) {
        adr170ContactUserComName__ = adr170ContactUserComName;
    }

    /**
     * <p>adr170ContactUserName を取得します。
     * @return adr170ContactUserName
     */
    public String getAdr170ContactUserName() {
        return adr170ContactUserName__;
    }

    /**
     * <p>adr170ContactUserName をセットします。
     * @param adr170ContactUserName adr170ContactUserName
     */
    public void setAdr170ContactUserName(String adr170ContactUserName) {
        adr170ContactUserName__ = adr170ContactUserName;
    }

    /**
     * <br>[機  能] 共通メッセージフォームへのパラメータ設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param msgForm 共通メッセージフォーム
     */
    public void setHiddenParam170(Cmn999Form msgForm) {

        msgForm.addHiddenParam("adr170title", adr170title__);
        msgForm.addHiddenParam("adr170Mark", adr170Mark__);
        msgForm.addHiddenParam("adr170enterContactYear", adr170enterContactYear__);
        msgForm.addHiddenParam("adr170enterContactMonth", adr170enterContactMonth__);
        msgForm.addHiddenParam("adr170enterContactDay", adr170enterContactDay__);
        msgForm.addHiddenParam("adr170enterContactHour", adr170enterContactHour__);
        msgForm.addHiddenParam("adr170enterContactMinute", adr170enterContactMinute__);
        msgForm.addHiddenParam("adr170enterContactProject", adr170enterContactProject__);
        msgForm.addHiddenParam("adr170biko", adr170biko__);
//        msgForm.addHiddenParam("projectPluginKbn", projectPluginKbn__);
        msgForm.addHiddenParam("adr170ProjectSid", adr170ProjectSid__);
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param buMdl セッションユーザモデル
     * @param con コネクション
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateAdr170(BaseUserModel buMdl, Connection con, HttpServletRequest req)
        throws SQLException {

        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage();

        //タイトル名
        AdrValidateUtil.validateTextField(errors,
                                          adr170title__,
                                         "adr170title",
                                          gsMsg.getMessage(req, "cmn.title"),
                                          100,
                                          true);

        //種別
        if (adr170Mark__ == -1) {
            ActionMessage msg =
                new ActionMessage(
                        "error.select.required.text",
                         gsMsg.getMessage(req, "cmn.type"));
             errors.add("adr170Mark.error.select.required.text", msg);
        }
        int errCnt = errors.size();
        //コンタクト日時From
        AdrValidateUtil.validateDate(errors,
                                     gsMsg.getMessage(req, "address.114") + "From",
                                     adr170enterContactYear__,
                                     adr170enterContactMonth__,
                                     adr170enterContactDay__,
                                     req);
        //コンタクト日時To
        AdrValidateUtil.validateDate(errors,
                                     gsMsg.getMessage(req, "address.114") + "To",
                                     adr170enterContactYearTo__,
                                     adr170enterContactMonthTo__,
                                     adr170enterContactDayTo__,
                                     req);
        //コンタクト日時大小チェック
        if (errCnt == errors.size()) {
            __validateDataRange(errors, req);
        }
        //備考
        AdrValidateUtil.validateTextAreaField(errors,
                                              adr170biko__,
                                             "adr170biko",
                                              gsMsg.getMessage(req, "cmn.memo"),
                                              10000,
                                              false);

//        //同時登録ユーザのチェック
//        int cmdMode = getAdr160ProcMode();
//
//        //新規モード
//        if (cmdMode == GSConstAddress.PROCMODE_ADD) {
//
//            if (saveUser__ != null && saveUser__.length > 0) {
//                AdrAddressDao adrDao = new AdrAddressDao(con);
//                ArrayList<AdrAddressModel> grpSelectUserList =
//                    adrDao.selectAdrList(saveUser__, buMdl.getUsrsid());
//
//                if (saveUser__.length != grpSelectUserList.size()) {
//                    ActionMessage msg =
//                        new ActionMessage("error.adr.add.dozi.user");
//                    errors.add("error.adr.add.dozi.user", msg);
//                }
//            }
//
//        //編集モード
//        } else if (cmdMode == GSConstAddress.PROCMODE_EDIT) {
//
//            //編集後の同時登録データが編集可能か
//
//        }

        if (saveUser__ != null && saveUser__.length > 0) {
            AdrAddressDao adrDao = new AdrAddressDao(con);
            ArrayList<AdrAddressModel> grpSelectUserList =
                adrDao.selectAdrList(saveUser__, buMdl.getUsrsid());

            if (saveUser__.length != grpSelectUserList.size()) {
                ActionMessage msg =
                    new ActionMessage("error.adr.add.dozi.user");
                errors.add("error.adr.add.dozi.user", msg);
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param buMdl セッションユーザモデル
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateAdr170Delete(BaseUserModel buMdl, Connection con)
        throws SQLException {

        ActionErrors errors = new ActionErrors();

        AdrContactDao adcDao = new AdrContactDao(con);
        int editSid = getAdr160EditSid();
        AdrContactModel adcMdl = adcDao.select(editSid);

        String[] delUser = null;
        int adcGrpSid = -1;
        if (adcMdl != null) {
            adcGrpSid = adcMdl.getAdcGrpSid();
            if (adcGrpSid > 0) {

                //同時登録したアドレス帳のSIDを取得
                ArrayList<Integer> intGrpAdrSid =
                    adcDao.selectGrpAdrSid(adcGrpSid);

                if (!intGrpAdrSid.isEmpty()) {

                    ArrayList<String> convGrpAdrSid = new ArrayList<String>();
//                    int myAdrSid = adcMdl.getAdrSid();

                    for (int grpAdrSid : intGrpAdrSid) {
//                        //自分自身は除外する
//                        if (myAdrSid != grpAdrSid) {
                            convGrpAdrSid.add(String.valueOf(grpAdrSid));
//                        }
                    }

                    if (!convGrpAdrSid.isEmpty()) {
                        delUser =
                            (String[]) convGrpAdrSid.toArray(
                                    new String[convGrpAdrSid.size()]);
                    }
                }
            }
        }

        if (delUser != null && delUser.length > 0) {
            AdrAddressDao adrDao = new AdrAddressDao(con);
            ArrayList<AdrAddressModel> grpSelectUserList =
                adrDao.selectAdrList(delUser, buMdl.getUsrsid());

            if (delUser.length != grpSelectUserList.size()) {
                ActionMessage msg =
                    new ActionMessage("error.adr.add.dozi.user");
                errors.add("error.adr.add.dozi.user", msg);
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 年月日時分の大小チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param req リクエスト
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    private boolean __validateDataRange(ActionErrors errors, HttpServletRequest req) {
        GsMessage gsMsg = new GsMessage();
        UDate dateFrom = new UDate();
        dateFrom.setTimeStamp(adr170enterContactYear__
                            + StringUtil.toDecFormat(adr170enterContactMonth__, "00")
                            + StringUtil.toDecFormat(adr170enterContactDay__, "00")
                            + StringUtil.toDecFormat(adr170enterContactHour__, "00")
                            + StringUtil.toDecFormat(adr170enterContactMinute__, "00")
                            + "00");

        UDate dateTo = new UDate();
        dateTo.setTimeStamp(adr170enterContactYearTo__
                          + StringUtil.toDecFormat(adr170enterContactMonthTo__, "00")
                          + StringUtil.toDecFormat(adr170enterContactDayTo__, "00")
                          + StringUtil.toDecFormat(adr170enterContactHourTo__, "00")
                          + StringUtil.toDecFormat(adr170enterContactMinuteTo__, "00")
                          + "00");

        //大小チェック
        if (dateTo.compareDateYMDHM(dateFrom) == UDate.LARGE) {
            ActionMessage msg = new ActionMessage(
                    "error.input.comp.text",
                    gsMsg.getMessage(req, "address.114"),
                    gsMsg.getMessage(req, "cmn.start.lessthan.end"));
            errors.add("" + "error.input.comp.text", msg);
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * @return adr170ProjectList
     */
    public List<LabelValueBean> getAdr170ProjectList() {
        return adr170ProjectList__;
    }

    /**
     * @param adr170ProjectList 設定する adr170ProjectList
     */
    public void setAdr170ProjectList(List<LabelValueBean> adr170ProjectList) {
        adr170ProjectList__ = adr170ProjectList;
    }

    /**
     * @return adr170ProjectSid
     */
    public String[] getAdr170ProjectSid() {
        return adr170ProjectSid__;
    }

    /**
     * @param adr170ProjectSid 設定する adr170ProjectSid
     */
    public void setAdr170ProjectSid(String[] adr170ProjectSid) {
        adr170ProjectSid__ = adr170ProjectSid;
    }

//    /**
//     * @return projectPluginKbn
//     */
//    public int getProjectPluginKbn() {
//        return projectPluginKbn__;
//    }
//
//    /**
//     * @param projectPluginKbn 設定する projectPluginKbn
//     */
//    public void setProjectPluginKbn(int projectPluginKbn) {
//        projectPluginKbn__ = projectPluginKbn;
//    }

    /**
     * @return adr170delProjectSid
     */
    public String getAdr170delProjectSid() {
        return adr170delProjectSid__;
    }

    /**
     * @param adr170delProjectSid 設定する adr170delProjectSid
     */
    public void setAdr170delProjectSid(String adr170delProjectSid) {
        adr170delProjectSid__ = adr170delProjectSid;
    }
}