package jp.groupsession.v2.rsv.rsv210;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.RelationBetweenScdAndRsvChkBiz;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.other.ExtendedLabelValueModel;
import jp.groupsession.v2.rsv.rsv030.Rsv030ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設予約一括登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv210ParamModel extends Rsv030ParamModel {

    /** 初期表示フラグ */
    private boolean rsv210InitFlg__ = true;
    /** 登録者 */
    private String rsv210Torokusya__ = null;
    /** 利用目的 */
    private String rsv210Mokuteki__ = null;
    /** 内容 */
    private String rsv210Naiyo__ = null;
    /** 時コンボFr選択値 */
    private int rsv210SelectedHourFr__ = -1;
    /** 時コンボTo選択値 */
    private int rsv210SelectedHourTo__ = -1;
    /** 時コンボリスト */
    private ArrayList<LabelValueBean> rsv210HourComboList__ = null;
    /** 分コンボFr選択値 */
    private int rsv210SelectedMinuteFr__ = -1;
    /** 分コンボTo選択値 */
    private int rsv210SelectedMinuteTo__ = -1;
    /** 分コンボリスト */
    private ArrayList<LabelValueBean> rsv210MinuteComboList__ = null;
    /** 編集権限 */
    private int rsv210RsyEdit__ = GSConstReserve.EDIT_AUTH_NONE;

    /** 一括予約開始日付 */
    private String rsv210YrkFrom__ = null;

    //----------- スケジュール同時登録 ----------------------------
    /** スケジュールプラグイン使用有無 0=使用 1=未使用*/
    private int schedulePluginKbn__;

    /** スケジュール登録区分 0:ユーザ 1:グループ */
    private int rsv210SchKbn__ = GSConstReserve.RSV_SCHKBN_USER;
    /** 同時登録スケジュールグループSID */
    private String rsv210SchGroupSid__ = "-1";
    /** 同時登録スケジュールグループリスト */
    private List<ExtendedLabelValueModel> rsv210SchGroupLabel__ = null;
    /** スケジュール共有範囲  0=共有範囲制限なし 1=所属グループのみ*/
    private int rsv210SchCrangeKbn__;
    /** スケジュールの閲覧を許可しないグループの一覧 */
    private List<Integer> rsv210SchNotAccessGroupList__;
    /** スケジュールの閲覧を許可しないユーザの一覧 */
    private List<Integer> rsv210SchNotAccessUserList__;

    /** セーブユーザーリスト */
    private String[] sv_users__ = null;
    /** ユーザーリスト（同時登録）*/
    private String[] users_r__ = null;
    /** 同時登録グループSID */
    private String rsv210GroupSid__ = null;
    /** 同時登録グループリスト */
    private List<ExtendedLabelValueModel> rsv210GroupLabel__ = null;
    /** 同時登録グループ所属ユーザリスト */
    private ArrayList<CmnUsrmInfModel> rsv210BelongLabel__ = null;
    /** 同時登録ユーザリスト */
    private ArrayList<CmnUsrmInfModel> rsv210SelectUsrLabel__ = null;
    /** 既登録の同時登録ユーザリスト */
    private ArrayList<CmnUsrmInfModel> rsv210AddedUsrLabel__ = null;
    /** スケジュール作成ユーザ名称 */
    private ArrayList<BaseUserModel> userNameArray__ = null;
    //-----------------------------------------------------------------

    /**
     * <p>rsv210RsyEdit__ を取得します。
     * @return rsv210RsyEdit
     */
    public int getRsv210RsyEdit() {
        return rsv210RsyEdit__;
    }
    /**
     * <p>rsv210RsyEdit__ をセットします。
     * @param rsv210RsyEdit rsv210RsyEdit__
     */
    public void setRsv210RsyEdit(int rsv210RsyEdit) {
        rsv210RsyEdit__ = rsv210RsyEdit;
    }
    /**
     * <p>rsv210InitFlg__ を取得します。
     * @return rsv210InitFlg
     */
    public boolean isRsv210InitFlg() {
        return rsv210InitFlg__;
    }
    /**
     * <p>rsv210InitFlg__ をセットします。
     * @param rsv210InitFlg rsv210InitFlg__
     */
    public void setRsv210InitFlg(boolean rsv210InitFlg) {
        rsv210InitFlg__ = rsv210InitFlg;
    }
    /**
     * <p>rsv210HourComboList__ を取得します。
     * @return rsv210HourComboList
     */
    public ArrayList<LabelValueBean> getRsv210HourComboList() {
        return rsv210HourComboList__;
    }
    /**
     * <p>rsv210HourComboList__ をセットします。
     * @param rsv210HourComboList rsv210HourComboList__
     */
    public void setRsv210HourComboList(ArrayList<LabelValueBean> rsv210HourComboList) {
        rsv210HourComboList__ = rsv210HourComboList;
    }
    /**
     * <p>rsv210MinuteComboList__ を取得します。
     * @return rsv210MinuteComboList
     */
    public ArrayList<LabelValueBean> getRsv210MinuteComboList() {
        return rsv210MinuteComboList__;
    }
    /**
     * <p>rsv210MinuteComboList__ をセットします。
     * @param rsv210MinuteComboList rsv210MinuteComboList__
     */
    public void setRsv210MinuteComboList(
            ArrayList<LabelValueBean> rsv210MinuteComboList) {
        rsv210MinuteComboList__ = rsv210MinuteComboList;
    }
    /**
     * <p>rsv210Mokuteki__ を取得します。
     * @return rsv210Mokuteki
     */
    public String getRsv210Mokuteki() {
        return rsv210Mokuteki__;
    }
    /**
     * <p>rsv210Mokuteki__ をセットします。
     * @param rsv210Mokuteki rsv210Mokuteki__
     */
    public void setRsv210Mokuteki(String rsv210Mokuteki) {
        rsv210Mokuteki__ = rsv210Mokuteki;
    }
    /**
     * <p>rsv210Naiyo__ を取得します。
     * @return rsv210Naiyo
     */
    public String getRsv210Naiyo() {
        return rsv210Naiyo__;
    }
    /**
     * <p>rsv210Naiyo__ をセットします。
     * @param rsv210Naiyo rsv210Naiyo__
     */
    public void setRsv210Naiyo(String rsv210Naiyo) {
        rsv210Naiyo__ = rsv210Naiyo;
    }
    /**
     * <p>rsv210SelectedHourFr__ を取得します。
     * @return rsv210SelectedHourFr
     */
    public int getRsv210SelectedHourFr() {
        return rsv210SelectedHourFr__;
    }
    /**
     * <p>rsv210SelectedHourFr__ をセットします。
     * @param rsv210SelectedHourFr rsv210SelectedHourFr__
     */
    public void setRsv210SelectedHourFr(int rsv210SelectedHourFr) {
        rsv210SelectedHourFr__ = rsv210SelectedHourFr;
    }
    /**
     * <p>rsv210SelectedHourTo__ を取得します。
     * @return rsv210SelectedHourTo
     */
    public int getRsv210SelectedHourTo() {
        return rsv210SelectedHourTo__;
    }
    /**
     * <p>rsv210SelectedHourTo__ をセットします。
     * @param rsv210SelectedHourTo rsv210SelectedHourTo__
     */
    public void setRsv210SelectedHourTo(int rsv210SelectedHourTo) {
        rsv210SelectedHourTo__ = rsv210SelectedHourTo;
    }
    /**
     * <p>rsv210SelectedMinuteFr__ を取得します。
     * @return rsv210SelectedMinuteFr
     */
    public int getRsv210SelectedMinuteFr() {
        return rsv210SelectedMinuteFr__;
    }
    /**
     * <p>rsv210SelectedMinuteFr__ をセットします。
     * @param rsv210SelectedMinuteFr rsv210SelectedMinuteFr__
     */
    public void setRsv210SelectedMinuteFr(int rsv210SelectedMinuteFr) {
        rsv210SelectedMinuteFr__ = rsv210SelectedMinuteFr;
    }
    /**
     * <p>rsv210SelectedMinuteTo__ を取得します。
     * @return rsv210SelectedMinuteTo
     */
    public int getRsv210SelectedMinuteTo() {
        return rsv210SelectedMinuteTo__;
    }
    /**
     * <p>rsv210SelectedMinuteTo__ をセットします。
     * @param rsv210SelectedMinuteTo rsv210SelectedMinuteTo__
     */
    public void setRsv210SelectedMinuteTo(int rsv210SelectedMinuteTo) {
        rsv210SelectedMinuteTo__ = rsv210SelectedMinuteTo;
    }
    /**
     * <p>rsv210Torokusya__ を取得します。
     * @return rsv210Torokusya
     */
    public String getRsv210Torokusya() {
        return rsv210Torokusya__;
    }
    /**
     * <p>rsv210Torokusya__ をセットします。
     * @param rsv210Torokusya rsv210Torokusya__
     */
    public void setRsv210Torokusya(String rsv210Torokusya) {
        rsv210Torokusya__ = rsv210Torokusya;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param sessionUsrSid セッションユーザSID
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, RequestModel reqMdl, int sessionUsrSid)
        throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        boolean errorFlg = false;
        GsMessage gsMsg = new GsMessage(reqMdl);
        //利用目的 未入力チェック
        if (StringUtil.isNullZeroString(rsv210Mokuteki__)) {
            msg =
                new ActionMessage("error.input.required.text",
                        gsMsg.getMessage("reserve.72"));
            StrutsUtil.addMessage(errors, msg, "rsv210Mokuteki");
            errorFlg = true;
        //利用目的 桁数チェック
        } else if (rsv210Mokuteki__.length() > GSConstReserve.MAX_LENGTH_MOKUTEKI) {
            msg =
                new ActionMessage("error.input.length.text",
                        gsMsg.getMessage("reserve.72"),
                                String.valueOf(GSConstReserve.MAX_LENGTH_MOKUTEKI));
            StrutsUtil.addMessage(errors, msg, "rsv210Mokuteki");
            errorFlg = true;
        //利用目的 スペースのみチェック
        } else if (ValidateUtil.isSpace(rsv210Mokuteki__)) {
            msg = new ActionMessage("error.input.spase.only",
                    gsMsg.getMessage("reserve.72"));
            StrutsUtil.addMessage(errors, msg, "rsv210Mokuteki");
            errorFlg = true;
        //利用目的 先頭スペースチェック
        } else if (ValidateUtil.isSpaceStart(rsv210Mokuteki__)) {
            msg = new ActionMessage("error.input.spase.start",
                    gsMsg.getMessage("reserve.72"));
            StrutsUtil.addMessage(errors, msg, "rsv210Mokuteki");
            errorFlg = true;
        //利用目的 JIS第2水準チェック
        } else if (!GSValidateUtil.isGsJapaneaseString(rsv210Mokuteki__)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(rsv210Mokuteki__);
            msg =
                new ActionMessage("error.input.njapan.text",
                        gsMsg.getMessage("reserve.72"),
                        nstr);
            StrutsUtil.addMessage(errors, msg, "rsv210Mokuteki");
            errorFlg = true;
        }

        //時間大小チェック
        String timeFrom =
            StringUtil.toDecFormat(rsv210SelectedHourFr__, "00")
            + StringUtil.toDecFormat(rsv210SelectedMinuteFr__, "00");

        String timeTo =
            StringUtil.toDecFormat(rsv210SelectedHourTo__, "00")
            + StringUtil.toDecFormat(rsv210SelectedMinuteTo__, "00");

        boolean timeError = Integer.parseInt(timeFrom) >= Integer.parseInt(timeTo);
        if (timeError) {
            msg = new ActionMessage("error.input.comp.text",
                    gsMsg.getMessage("reserve.159"),
                    gsMsg.getMessage("cmn.start.lessthan.end"));
            StrutsUtil.addMessage(errors, msg, "rsvtime");
            errorFlg = true;
        }

        //内容
        if (!StringUtil.isNullZeroString(rsv210Naiyo__)) {
            //内容 桁数チェック
            if (rsv210Naiyo__.length() > GSConstReserve.MAX_LENGTH_NAIYO) {
                msg = new ActionMessage("error.input.length.textarea",
                        gsMsg.getMessage("cmn.content"),
                            String.valueOf(GSConstReserve.MAX_LENGTH_NAIYO));
                StrutsUtil.addMessage(errors, msg, "rsv210Naiyo");
                errorFlg = true;
            }
            //内容 スペース・改行のみチェック
            if (ValidateUtil.isSpaceOrKaigyou(rsv210Naiyo__)) {
                msg = new ActionMessage("error.input.spase.cl.only",
                        gsMsg.getMessage("cmn.content"));
                StrutsUtil.addMessage(errors, msg, "rsv210Naiyo");
                errorFlg = true;
            }
            //内容 JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseStringTextArea(rsv210Naiyo__)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(rsv210Naiyo__);
                msg = new ActionMessage("error.input.njapan.text",
                        gsMsg.getMessage("cmn.content"),
                        nstr);
                StrutsUtil.addMessage(errors, msg, "rsv210Naiyo");
                errorFlg = true;
            }
        }

        //予約チェック
        if (!errorFlg) {
            ArrayList<String> hiddArray = __getIkkatuKey();

            RsvSisDataDao dataDao = new RsvSisDataDao(con);
            HashMap<Integer, Rsv210Model> map =
                dataDao.getIkkatuTorokuGroupMap(hiddArray);

            for (String key : hiddArray) {

                errorFlg = false;

                //施設の情報を取得
                String sisetuSid = key.substring(key.indexOf("-") + 1);

                Rsv210Model mapMdl = map.get(new Integer(sisetuSid));
                if (mapMdl == null) {
                    continue;
                }

                //予約可能期限チェック(期限が設定されていればチェックする)
                String kigen = mapMdl.getRsdProp6();
                if (!StringUtil.isNullZeroString(kigen)) {

                    //施設グループ管理者の場合は予約可能期限チェックをパスする
                    RsvCommonBiz rsvBiz = new RsvCommonBiz();
                    if (!rsvBiz.isGroupAdmin(con, new Integer(sisetuSid),
                            reqMdl.getSmodel().getUsrsid())) {
                        UDate now = new UDate();
                        UDate udKigen = now.cloneUDate();
                        udKigen.addDay(Integer.parseInt(kigen));

                        String kigenYmd = udKigen.getDateString();
                        String keyYmd = key.substring(0, key.indexOf("-"));

                        if (Integer.parseInt(keyYmd) > Integer.parseInt(kigenYmd)) {

                            UDate errDay = new UDate();
                            errDay.setDate(keyYmd);
                            String errDayStr =
                                    gsMsg.getMessage("cmn.year", new String[] {errDay.getStrYear()})
                                    + errDay.getStrMonth()
                                    + gsMsg.getMessage("cmn.month")
                                    + errDay.getStrDay()
                                    + gsMsg.getMessage("cmn.day") + "（"
                                    + UDateUtil.getStrWeek(errDay.getWeek(), reqMdl)
                                    + "） "
                                    + mapMdl.getRsdName();

                            String kigenStr =
                                    gsMsg.getMessage("cmn.comments")
                                    + mapMdl.getRsdProp6()
                                    + gsMsg.getMessage("cmn.days.after");

                            msg = new ActionMessage("error.kigen.over.sisetu",
                                    errDayStr, kigenStr);
                            StrutsUtil.addMessage(errors, msg, "sisetu" + key);
                            errorFlg = true;
                        }
                    }
                }

                //重複のチェック(重複登録 = 不可の場合にチェック)
                String tyohuku = mapMdl.getRsdProp7();
                if (!errorFlg
                        && !StringUtil.isNullZeroString(tyohuku)
                        && Integer.parseInt(tyohuku) == GSConstReserve.PROP_KBN_HUKA) {

                    RsvSisYrkDao yrkDao = new RsvSisYrkDao(con);

                    UDate[] chkDate = __getYrkDate(key);
                    UDate udYrk = chkDate[0];
                    UDate chkFrDate = chkDate[1];
                    UDate chkToDate = chkDate[2];

                    //登録予定の時間帯に既に予約がある場合はエラー
                    if (!yrkDao.isYrkOk(-1, Integer.parseInt(sisetuSid), chkFrDate, chkToDate)) {

                        String errDayStr =
                                gsMsg.getMessage("cmn.year", new String[] {udYrk.getStrYear()})
                                + udYrk.getStrMonth()
                                + gsMsg.getMessage("cmn.month")
                                + udYrk.getStrDay()
                                + gsMsg.getMessage("cmn.day") + "（"
                                + UDateUtil.getStrWeek(udYrk.getWeek(), reqMdl)
                                + "） "
                                + mapMdl.getRsdName();

                        msg = new ActionMessage("error.yrk.exist.reserve", errDayStr);
                        StrutsUtil.addMessage(errors, msg, "tyohuku" + key);
                    }
                }
            }
        }

        //スケジュール重複チェック
        if (!timeError) {
            String[] users = getSv_users();
            if (rsv210SchKbn__ == GSConstReserve.RSV_SCHKBN_USER
                && users != null && users.length > 0) {

                ArrayList<String> hiddArray = __getIkkatuKey();
                List<UDate[]> dateList = new ArrayList<UDate[]>();
                for (String key : hiddArray) {
                    UDate[] chkDate = __getYrkDate(key);
                    dateList.add(new UDate[] {chkDate[1], chkDate[2]});
                }

                RelationBetweenScdAndRsvChkBiz schChkBiz =
                    new RelationBetweenScdAndRsvChkBiz(reqMdl, con);
                schChkBiz.validateDateForSchedule(
                        errors,
                        dateList,
                        users,
                        0,
                        false,
                        sessionUsrSid,
                        "rsv210Date");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 一括登録のキーを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return 一括登録のキー
     */
    private ArrayList<String> __getIkkatuKey() {
        ArrayList<String> hiddArray = new ArrayList<String>();
        String[] hiddStr = getRsvIkkatuTorokuKey();
        if (hiddStr != null && hiddStr.length > 0) {
            for (String key : hiddStr) {
                hiddArray.add(key);
            }
        }

        Collections.sort(hiddArray);

        return hiddArray;
    }

    /**
     * <br>[機  能] 施設予約日、施設予約開始日時、終了日時を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param key 一括登録のキー
     * @return 施設予約日、施設予約開始日時、終了日時
     */
    private UDate[] __getYrkDate(String key) {

        String ymdStr = key.substring(0, key.indexOf("-"));
        UDate udYrk = new UDate();
        udYrk.setDate(ymdStr);

        //予約開始
        UDate chkFrDate = new UDate();
        chkFrDate.setDate(
                udYrk.getYear(),
                udYrk.getMonth(),
                udYrk.getIntDay());
        chkFrDate.setHour(rsv210SelectedHourFr__);
        chkFrDate.setMinute(rsv210SelectedMinuteFr__);
        chkFrDate.setSecond(GSConstReserve.DAY_START_SECOND);
        chkFrDate.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

        //予約終了
        UDate chkToDate = new UDate();
        chkToDate.setDate(
                udYrk.getYear(),
                udYrk.getMonth(),
                udYrk.getIntDay());
        chkToDate.setHour(rsv210SelectedHourTo__);
        chkToDate.setMinute(rsv210SelectedMinuteTo__);
        chkToDate.setSecond(GSConstReserve.DAY_START_SECOND);
        chkToDate.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

        return new UDate[] {udYrk, chkFrDate, chkToDate};
    }

    /**
     * <p>rsv210AddedUsrLabel を取得します。
     * @return rsv210AddedUsrLabel
     */
    public ArrayList<CmnUsrmInfModel> getRsv210AddedUsrLabel() {
        return rsv210AddedUsrLabel__;
    }
    /**
     * <p>rsv210AddedUsrLabel をセットします。
     * @param rsv210AddedUsrLabel rsv210AddedUsrLabel
     */
    public void setRsv210AddedUsrLabel(
            ArrayList<CmnUsrmInfModel> rsv210AddedUsrLabel) {
        rsv210AddedUsrLabel__ = rsv210AddedUsrLabel;
    }
    /**
     * <p>rsv210BelongLabel を取得します。
     * @return rsv210BelongLabel
     */
    public ArrayList<CmnUsrmInfModel> getRsv210BelongLabel() {
        return rsv210BelongLabel__;
    }
    /**
     * <p>rsv210BelongLabel をセットします。
     * @param rsv210BelongLabel rsv210BelongLabel
     */
    public void setRsv210BelongLabel(ArrayList<CmnUsrmInfModel> rsv210BelongLabel) {
        rsv210BelongLabel__ = rsv210BelongLabel;
    }
    /**
     * <p>rsv210GroupLabel を取得します。
     * @return rsv210GroupLabel
     */
    public List<ExtendedLabelValueModel> getRsv210GroupLabel() {
        return rsv210GroupLabel__;
    }
    /**
     * <p>rsv210GroupLabel をセットします。
     * @param rsv210GroupLabel rsv210GroupLabel
     */
    public void setRsv210GroupLabel(List<ExtendedLabelValueModel> rsv210GroupLabel) {
        rsv210GroupLabel__ = rsv210GroupLabel;
    }
    /**
     * <p>rsv210GroupSid を取得します。
     * @return rsv210GroupSid
     */
    public String getRsv210GroupSid() {
        return rsv210GroupSid__;
    }
    /**
     * <p>rsv210GroupSid をセットします。
     * @param rsv210GroupSid rsv210GroupSid
     */
    public void setRsv210GroupSid(String rsv210GroupSid) {
        rsv210GroupSid__ = rsv210GroupSid;
    }
    /**
     * <p>rsv210SelectUsrLabel を取得します。
     * @return rsv210SelectUsrLabel
     */
    public ArrayList<CmnUsrmInfModel> getRsv210SelectUsrLabel() {
        return rsv210SelectUsrLabel__;
    }
    /**
     * <p>rsv210SelectUsrLabel をセットします。
     * @param rsv210SelectUsrLabel rsv210SelectUsrLabel
     */
    public void setRsv210SelectUsrLabel(
            ArrayList<CmnUsrmInfModel> rsv210SelectUsrLabel) {
        rsv210SelectUsrLabel__ = rsv210SelectUsrLabel;
    }
    /**
     * <p>schedulePluginKbn を取得します。
     * @return schedulePluginKbn
     */
    public int getSchedulePluginKbn() {
        return schedulePluginKbn__;
    }
    /**
     * <p>schedulePluginKbn をセットします。
     * @param schedulePluginKbn schedulePluginKbn
     */
    public void setSchedulePluginKbn(int schedulePluginKbn) {
        schedulePluginKbn__ = schedulePluginKbn;
    }
    /**
     * <p>sv_users を取得します。
     * @return sv_users
     */
    public String[] getSv_users() {
        return sv_users__;
    }
    /**
     * <p>sv_users をセットします。
     * @param svUsers sv_users
     */
    public void setSv_users(String[] svUsers) {
        sv_users__ = svUsers;
    }
    /**
     * <p>userNameArray を取得します。
     * @return userNameArray
     */
    public ArrayList<BaseUserModel> getUserNameArray() {
        return userNameArray__;
    }
    /**
     * <p>userNameArray をセットします。
     * @param userNameArray userNameArray
     */
    public void setUserNameArray(ArrayList<BaseUserModel> userNameArray) {
        userNameArray__ = userNameArray;
    }
    /**
     * <p>users_r を取得します。
     * @return users_r
     */
    public String[] getUsers_r() {
        return users_r__;
    }
    /**
     * <p>users_r をセットします。
     * @param users users_r
     */
    public void setUsers_r(String[] users) {
        users_r__ = users;
    }
    /**
     * <p>rsv210SchCrangeKbn を取得します。
     * @return rsv210SchCrangeKbn
     */
    public int getRsv210SchCrangeKbn() {
        return rsv210SchCrangeKbn__;
    }
    /**
     * <p>rsv210SchCrangeKbn をセットします。
     * @param rsv210SchCrangeKbn rsv210SchCrangeKbn
     */
    public void setRsv210SchCrangeKbn(int rsv210SchCrangeKbn) {
        rsv210SchCrangeKbn__ = rsv210SchCrangeKbn;
    }
    /**
     * <p>rsv210SchNotAccessGroupList を取得します。
     * @return rsv210SchNotAccessGroupList
     */
    public List<Integer> getRsv210SchNotAccessGroupList() {
        return rsv210SchNotAccessGroupList__;
    }
    /**
     * <p>rsv210SchNotAccessGroupList をセットします。
     * @param rsv210SchNotAccessGroupList rsv210SchNotAccessGroupList
     */
    public void setRsv210SchNotAccessGroupList(
            List<Integer> rsv210SchNotAccessGroupList) {
        rsv210SchNotAccessGroupList__ = rsv210SchNotAccessGroupList;
    }
    /**
     * <p>rsv210SchNotAccessUserList を取得します。
     * @return rsv210SchNotAccessUserList
     */
    public List<Integer> getRsv210SchNotAccessUserList() {
        return rsv210SchNotAccessUserList__;
    }
    /**
     * <p>rsv210SchNotAccessUserList をセットします。
     * @param rsv210SchNotAccessUserList rsv210SchNotAccessUserList
     */
    public void setRsv210SchNotAccessUserList(
            List<Integer> rsv210SchNotAccessUserList) {
        rsv210SchNotAccessUserList__ = rsv210SchNotAccessUserList;
    }
    /**
     * <p>rsv210SchGroupLabel を取得します。
     * @return rsv210SchGroupLabel
     */
    public List<ExtendedLabelValueModel> getRsv210SchGroupLabel() {
        return rsv210SchGroupLabel__;
    }
    /**
     * <p>rsv210SchGroupLabel をセットします。
     * @param rsv210SchGroupLabel rsv210SchGroupLabel
     */
    public void setRsv210SchGroupLabel(List<ExtendedLabelValueModel> rsv210SchGroupLabel) {
        rsv210SchGroupLabel__ = rsv210SchGroupLabel;
    }
    /**
     * <p>rsv210SchGroupSid を取得します。
     * @return rsv210SchGroupSid
     */
    public String getRsv210SchGroupSid() {
        return rsv210SchGroupSid__;
    }
    /**
     * <p>rsv210SchGroupSid をセットします。
     * @param rsv210SchGroupSid rsv210SchGroupSid
     */
    public void setRsv210SchGroupSid(String rsv210SchGroupSid) {
        rsv210SchGroupSid__ = rsv210SchGroupSid;
    }
    /**
     * <p>rsv210SchKbn を取得します。
     * @return rsv210SchKbn
     */
    public int getRsv210SchKbn() {
        return rsv210SchKbn__;
    }
    /**
     * <p>rsv210SchKbn をセットします。
     * @param rsv210SchKbn rsv210SchKbn
     */
    public void setRsv210SchKbn(int rsv210SchKbn) {
        rsv210SchKbn__ = rsv210SchKbn;
    }
    /**
     * <p>rsv210YrkFrom を取得します。
     * @return rsv210YrkFrom
     */
    public String getRsv210YrkFrom() {
        return rsv210YrkFrom__;
    }
    /**
     * <p>rsv210YrkFrom をセットします。
     * @param rsv210YrkFrom rsv210YrkFrom
     */
    public void setRsv210YrkFrom(String rsv210YrkFrom) {
        rsv210YrkFrom__ = rsv210YrkFrom;
    }
}