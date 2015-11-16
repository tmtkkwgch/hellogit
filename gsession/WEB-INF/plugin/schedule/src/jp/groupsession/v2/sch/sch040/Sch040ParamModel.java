package jp.groupsession.v2.sch.sch040;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.RelationBetweenScdAndRsvChkBiz;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv110.Rsv110Biz;
import jp.groupsession.v2.rsv.rsv210.Rsv210Model;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch040.model.Sch040AttendModel;
import jp.groupsession.v2.sch.sch040.model.Sch040CompanyModel;
import jp.groupsession.v2.sch.sch100.Sch100ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch040ParamModel extends Sch100ParamModel {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch040ParamModel.class);
    /** スケジュールデータ有無フラグ true=有り false:無し*/
    private boolean sch040DataFlg__ = false;
    /** スケジュールデータ閲覧フラグ true=閲覧可 false:閲覧不可能 */
    private boolean sch040ViewFlg__ = false;

    /** 施設予約プラグイン使用有無 0=使用 1=未使用*/
    private int reservePluginKbn__;
    /** アドレス帳プラグイン使用有無 0=使用 1=未使用*/
    private int addressPluginKbn__;
    /** WEB検索プラグイン使用有無 0=使用 1=未使用*/
    private int searchPluginKbn__;

    /** スケジュール共有範囲  0=共有範囲制限なし 1=所属グループのみ*/
    private int sch040CrangeKbn__;

    /** スケジュール対象ユーザ名称 */
    private String sch040UsrName__ = null;
    /** スケジュール登録者名称 */
    private String sch040AddUsrName__ = null;
    /** スケジュール登録者削除区分 */
    private String sch040AddUsrJkbn__ = null;
    /** スケジュール登録日時 */
    private String sch040AddDate__ = null;
    /** 背景色 */
    private String sch040Bgcolor__ = null;
    /** スケジュールタイトル */
    private String sch040Title__ = null;
    /** スケジュール内容 */
    private String sch040Value__ = null;
    /** スケジュール備考 */
    private String sch040Biko__ = null;
    /** 公開区分 */
    private String sch040Public__ = null;
    /** 開始年 */
    private String sch040FrYear__ = null;
    /** 開始月 */
    private String sch040FrMonth__ = null;
    /** 開始日 */
    private String sch040FrDay__ = null;
    /** 開始時 */
    private String sch040FrHour__ = null;
    /** 開始分 */
    private String sch040FrMin__ = null;
    /** 終了年 */
    private String sch040ToYear__ = null;
    /** 終了月 */
    private String sch040ToMonth__ = null;
    /** 終了日 */
    private String sch040ToDay__ = null;
    /** 終了時 */
    private String sch040ToHour__ = null;
    /** 終了分 */
    private String sch040ToMin__ = null;

    /** 同時登録グループSID */
    private String sch040GroupSid__ = null;
    /** セーブユーザーリスト */
    private String[] sv_users__ = null;
    /** ユーザーリスト（同時登録）*/
    private String[] users_r__ = null;

    /** 年リスト */
    private ArrayList <LabelValueBean> sch040YearLabel__ = null;
    /** 月リスト */
    private ArrayList <LabelValueBean> sch040MonthLabel__ = null;
    /** 日リスト */
    private ArrayList <LabelValueBean> sch040DayLabel__ = null;
    /** 時リスト */
    private ArrayList <LabelValueBean> sch040HourLabel__ = null;
    /** 分リスト */
    private ArrayList <LabelValueBean> sch040MinuteLabel__ = null;
    /** 同時登録グループリスト */
    private List<SchLabelValueModel> sch040GroupLabel__ = null;
    /** 同時登録グループ所属ユーザリスト */
    private ArrayList <CmnUsrmInfModel> sch040BelongLabel__ = null;
    /** 同時登録ユーザリスト */
    private ArrayList <CmnUsrmInfModel> sch040SelectUsrLabel__ = null;
    /** 既登録の同時登録ユーザリスト */
    private ArrayList <CmnUsrmInfModel> sch040AddedUsrLabel__ = null;
    /** カラーコメントリスト */
    private ArrayList <String> sch040ColorMsgList__ = null;
    /** タイトル色区分 */
    private int sch040colorKbn__ = GSConstSchedule.SAD_MSG_NO_ADD;

    /** 繰り返しで登録スケジュール表示フラグ */
    private boolean sch040ExTextDspFlg__ = false;

    //メイン画面パラメータ
    /** 週間スケジュール 表示日付 */
    private String schWeekDate__;
    /** 日間スケジュール 表示日付 */
    private String schDailyDate__;

    /** 同時登録スケジュールへ反映有無 */
    private String sch040BatchRef__ = String.valueOf(GSConstSchedule.SAME_EDIT_ON);

    /** 編集権限設定 0=未設定 1=本人のみ 2=所属グループ */
    private String sch040Edit__ = null;

    /** 時間指定有無 0=有り 1=無し */
    private String sch040TimeKbn__ = String.valueOf(GSConstSchedule.TIME_EXIST);

    //拡張登録画面パラメータ
    /** 拡張区分 */
    private String sch041ExtSid__ = null;
    /** 拡張区分 */
    private String sch041ExtKbn__ = null;
    /** 曜日multibox */
    private String[] sch041Dweek__ = null;
    /** 週 */
    private String sch041Week__ = null;
    /** 日 */
    private String sch041Day__ = null;
    /** 毎年 日 */
    private String sch041DayOfYearly__ = null;
    /** 毎年 月 */
    private String sch041MonthOfYearly__ = null;

    /** 振替設定 */
    private String sch041TranKbn__ = null;
    /** 開始年 */
    private String sch041FrYear__ = null;
    /** 開始月 */
    private String sch041FrMonth__ = null;
    /** 開始日 */
    private String sch041FrDay__ = null;
    /** 終了年 */
    private String sch041ToYear__ = null;
    /** 終了月 */
    private String sch041ToMonth__ = null;
    /** 終了日 */
    private String sch041ToDay__ = null;

    /** 開始時 */
    private String sch041FrHour__ = null;
    /** 開始分 */
    private String sch041FrMin__ = null;
    /** 終了時 */
    private String sch041ToHour__ = null;
    /** 終了分 */
    private String sch041ToMin__ = null;
    /** 背景色 */
    private String sch041Bgcolor__ = null;
    /** スケジュールタイトル */
    private String sch041Title__ = null;
    /** スケジュール内容 */
    private String sch041Value__ = null;
    /** スケジュール備考 */
    private String sch041Biko__ = null;
    /** 公開区分 */
    private String sch041Public__ = null;
    /** 編集権限設定 0=未設定 1=本人のみ 2=所属グループ */
    private String sch041Edit__ = null;
    /** 同時登録スケジュールへ反映有無 */
    private String sch041BatchRef__ = "1";

    /** 時間指定有無 0=有り 1=無し */
    private String sch041TimeKbn__ = String.valueOf(GSConstSchedule.TIME_EXIST);

    /** 同時登録グループSID */
    private String sch041GroupSid__ = null;
    /** 拡張同時登録ユーザーリスト */
    private String[] sch041SvUsers__ = null;
    /** ユーザーリスト（同時登録）*/
    private String[] sch041users_r__ = null;

    //施設予約
    /** 同時登録施設予約へ反映有無 */
    private String sch040ResBatchRef__ = "1";
    /** 同時登録施設グループSID */
    private String sch040ReserveGroupSid__ = null;
    /** セーブ施設リスト */
    private String[] svReserveUsers__ = null;
    /** 施設リスト（同時登録）*/
    private String[] reserve_r__ = null;

    /** 同時登録施設グループSID */
    private String sch041ReserveGroupSid__ = null;
    /** 拡張同時登録施設リスト */
    private String[] sch041SvReserve__ = null;
    /** 施設リスト（同時登録）*/
    private String[] sch041Reserve_r__ = null;

    /** 同時登録施設グループリスト */
    private List<LabelValueBean> sch040ReserveGroupLabel__ = null;
    /** 同時登録施設グループ所属施設リスト */
    private ArrayList <RsvSisDataModel> sch040ReserveBelongLabel__ = null;
    /** 同時登録施設リスト */
    private ArrayList <RsvSisDataModel> sch040ReserveSelectLabel__ = null;

    /** 同時登録されたアクセス権限のない施設予約数 */
    private int sch040CantReadRsvCount__ = 0;

    /** 初期表示フラグ 0=初期 1=初期済み */
    private String sch040InitFlg__ = String.valueOf(GSConstSchedule.INIT_FLG);

    /** 初期表示フラグ 0=初期 1=初期済み */
    private String sch040ScrollFlg__ = "0";

    /** 複写フラグ 0=通常 1=複写 */
    private String sch040CopyFlg__ = GSConstSchedule.NOT_COPY_FLG;

    //会社情報
    /** 会社SID */
    private String[] sch040CompanySid__ = null;
    /** 会社拠点SID */
    private String[] sch040CompanyBaseSid__ = null;
    /** 担当者(アドレス情報) */
    private String[] sch040AddressId__ = null;
    /** コンタクト履歴に反映 */
    private int sch040contact__ = 0;
    /** 会社SID */
    private String[] sch041CompanySid__ = null;
    /** 会社拠点SID */
    private String[] sch041CompanyBaseSid__ = null;
    /** 担当者(アドレス情報) */
    private String[] sch041AddressId__ = null;
    /** コンタクト履歴に反映 */
    private int sch041contact__ = 0;

    /** 削除対象の会社ID */
    private String sch040delCompanyId__ = null;
    /** 削除対象の会社拠点ID */
    private String sch040delCompanyBaseId__ = null;

    /** 会社情報一覧 */
    private List<Sch040CompanyModel> sch040CompanyList__ = null;
    /** 会社情報一覧 */
    private List<Sch040CompanyModel> sch041CompanyList__ = null;

    /** 出欠確認区分 */
    private String sch040AttendKbn__ = null;
    /** 出欠回答区分 */
    private String sch040AttendAnsKbn__ = null;
    /** スケジュール編集画面 表示モード（0：通常スケジュール  1:出欠依頼者  2:出欠回答者） */
    private String sch040EditDspMode__ = null;
    /** 出欠確認 再通知メール送信区分 0:送信しない  1:送信する*/
    private int sch040EditMailSendKbn__ = 0;
    /** 出欠確認回答一覧 */
    private List<Sch040AttendModel> sch040AttendAnsList__ = null;
    /** 出欠確認回答一覧 全て表示リンクフラグ 0:非表示 1:表示 */
    private int sch040AttendLinkFlg__ = 0;
    /** 出欠回答者 表示用 From時間 */
    private String sch040DspFromDate__ = null;
    /** 出欠回答者 表示用 To時間 */
    private String sch040DspToDate__ = null;
    /** 出欠回答者 表示用 内容 */
    private String sch040DspValue__ = null;
    /** 出欠回答者 表示用 備考 */
    private String sch040DspBiko__ = null;
    /** 出欠確認依頼者スケジュール 存在フラグ  0:存在 1:削除 */
    private int sch040AttendDelFlg__ = 0;

    /** ボタン用の処理モード */
    private String sch040BtnCmd__ = "";

    /**
     * <p>sch040TimeKbn を取得します。
     * @return sch040TimeKbn
     */
    public String getSch040TimeKbn() {
        return sch040TimeKbn__;
    }

    /**
     * <p>sch040TimeKbn をセットします。
     * @param sch040TimeKbn sch040TimeKbn
     */
    public void setSch040TimeKbn(String sch040TimeKbn) {
        sch040TimeKbn__ = sch040TimeKbn;
    }

    /**
     * <p>sch041TimeKbn を取得します。
     * @return sch041TimeKbn
     */
    public String getSch041TimeKbn() {
        return sch041TimeKbn__;
    }

    /**
     * <p>sch041TimeKbn をセットします。
     * @param sch041TimeKbn sch041TimeKbn
     */
    public void setSch041TimeKbn(String sch041TimeKbn) {
        sch041TimeKbn__ = sch041TimeKbn;
    }

    /**
     * <p>sch040ColorMsgList を取得します。
     * @return sch040ColorMsgList
     */
    public ArrayList<String> getSch040ColorMsgList() {
        return sch040ColorMsgList__;
    }

    /**
     * <p>sch040ColorMsgList をセットします。
     * @param sch040ColorMsgList sch040ColorMsgList
     */
    public void setSch040ColorMsgList(ArrayList<String> sch040ColorMsgList) {
        sch040ColorMsgList__ = sch040ColorMsgList;
    }

    /**
     * <p>sch040DataFlg を取得します。
     * @return sch040DataFlg
     */
    public boolean isSch040DataFlg() {
        return sch040DataFlg__;
    }

    /**
     * <p>sch040DataFlg をセットします。
     * @param sch040DataFlg sch040DataFlg
     */
    public void setSch040DataFlg(boolean sch040DataFlg) {
        sch040DataFlg__ = sch040DataFlg;
    }

    /**
     * <p>sch041ExtSid を取得します。
     * @return sch041ExtSid
     */
    public String getSch041ExtSid() {
        return sch041ExtSid__;
    }

    /**
     * <p>sch041ExtSid をセットします。
     * @param sch041ExtSid sch041ExtSid
     */
    public void setSch041ExtSid(String sch041ExtSid) {
        sch041ExtSid__ = sch041ExtSid;
    }

    /**
     * <p>reservePluginKbn を取得します。
     * @return reservePluginKbn
     */
    public int getReservePluginKbn() {
        return reservePluginKbn__;
    }

    /**
     * <p>reservePluginKbn をセットします。
     * @param reservePluginKbn reservePluginKbn
     */
    public void setReservePluginKbn(int reservePluginKbn) {
        reservePluginKbn__ = reservePluginKbn;
    }

    /**
     * <p>addressPluginKbn を取得します。
     * @return addressPluginKbn
     */
    public int getAddressPluginKbn() {
        return addressPluginKbn__;
    }

    /**
     * <p>addressPluginKbn をセットします。
     * @param addressPluginKbn addressPluginKbn
     */
    public void setAddressPluginKbn(int addressPluginKbn) {
        addressPluginKbn__ = addressPluginKbn;
    }

    /**
     * <p>searchPluginKbn を取得します。
     * @return searchPluginKbn
     */
    public int getSearchPluginKbn() {
        return searchPluginKbn__;
    }

    /**
     * <p>searchPluginKbn をセットします。
     * @param searchPluginKbn searchPluginKbn
     */
    public void setSearchPluginKbn(int searchPluginKbn) {
        searchPluginKbn__ = searchPluginKbn;
    }

    /**
     * <p>sch040ResBatchRef を取得します。
     * @return sch040ResBatchRef
     */
    public String getSch040ResBatchRef() {
        return sch040ResBatchRef__;
    }

    /**
     * <p>sch040ResBatchRef をセットします。
     * @param sch040ResBatchRef sch040ResBatchRef
     */
    public void setSch040ResBatchRef(String sch040ResBatchRef) {
        sch040ResBatchRef__ = sch040ResBatchRef;
    }

    /**
     * <p>reserve_r を取得します。
     * @return reserve_r
     */
    public String[] getReserve_r() {
        return reserve_r__;
    }

    /**
     * <p>reserve_r をセットします。
     * @param reserver reserve_r
     */
    public void setReserve_r(String[] reserver) {
        reserve_r__ = reserver;
    }

    /**
     * <p>sch040ReserveBelongLabel を取得します。
     * @return sch040ReserveBelongLabel
     */
    public ArrayList<RsvSisDataModel> getSch040ReserveBelongLabel() {
        return sch040ReserveBelongLabel__;
    }

    /**
     * <p>sch040ReserveBelongLabel をセットします。
     * @param sch040ReserveBelongLabel sch040ReserveBelongLabel
     */
    public void setSch040ReserveBelongLabel(
            ArrayList<RsvSisDataModel> sch040ReserveBelongLabel) {
        sch040ReserveBelongLabel__ = sch040ReserveBelongLabel;
    }

    /**
     * <p>sch040ReserveGroupLabel を取得します。
     * @return sch040ReserveGroupLabel
     */
    public List<LabelValueBean> getSch040ReserveGroupLabel() {
        return sch040ReserveGroupLabel__;
    }

    /**
     * <p>sch040ReserveGroupLabel をセットします。
     * @param sch040ReserveGroupLabel sch040ReserveGroupLabel
     */
    public void setSch040ReserveGroupLabel(
            List<LabelValueBean> sch040ReserveGroupLabel) {
        sch040ReserveGroupLabel__ = sch040ReserveGroupLabel;
    }

    /**
     * <p>sch040ReserveGroupSid を取得します。
     * @return sch040ReserveGroupSid
     */
    public String getSch040ReserveGroupSid() {
        return sch040ReserveGroupSid__;
    }

    /**
     * <p>sch040ReserveGroupSid をセットします。
     * @param sch040ReserveGroupSid sch040ReserveGroupSid
     */
    public void setSch040ReserveGroupSid(String sch040ReserveGroupSid) {
        sch040ReserveGroupSid__ = sch040ReserveGroupSid;
    }

    /**
     * <p>sch040ReserveSelectLabel を取得します。
     * @return sch040ReserveSelectLabel
     */
    public ArrayList<RsvSisDataModel> getSch040ReserveSelectLabel() {
        return sch040ReserveSelectLabel__;
    }

    /**
     * <p>sch040ReserveSelectLabel をセットします。
     * @param sch040ReserveSelectLabel sch040ReserveSelectLabel
     */
    public void setSch040ReserveSelectLabel(
            ArrayList<RsvSisDataModel> sch040ReserveSelectLabel) {
        sch040ReserveSelectLabel__ = sch040ReserveSelectLabel;
    }

    /**
     * <p>sch041Reserve_r を取得します。
     * @return sch041Reserve_r
     */
    public String[] getSch041Reserve_r() {
        return sch041Reserve_r__;
    }

    /**
     * <p>sch041Reserve_r をセットします。
     * @param sch041Reserver sch041Reserve_r
     */
    public void setSch041Reserve_r(String[] sch041Reserver) {
        sch041Reserve_r__ = sch041Reserver;
    }

    /**
     * <p>sch041ReserveGroupSid を取得します。
     * @return sch041ReserveGroupSid
     */
    public String getSch041ReserveGroupSid() {
        return sch041ReserveGroupSid__;
    }

    /**
     * <p>sch041ReserveGroupSid をセットします。
     * @param sch041ReserveGroupSid sch041ReserveGroupSid
     */
    public void setSch041ReserveGroupSid(String sch041ReserveGroupSid) {
        sch041ReserveGroupSid__ = sch041ReserveGroupSid;
    }

    /**
     * <p>sch041SvReserve を取得します。
     * @return sch041SvReserve
     */
    public String[] getSch041SvReserve() {
        return sch041SvReserve__;
    }

    /**
     * <p>sch041SvReserve をセットします。
     * @param sch041SvReserve sch041SvReserve
     */
    public void setSch041SvReserve(String[] sch041SvReserve) {
        sch041SvReserve__ = sch041SvReserve;
    }

    /**
     * <p>svReserveUsers を取得します。
     * @return svReserveUsers
     */
    public String[] getSvReserveUsers() {
        return svReserveUsers__;
    }

    /**
     * <p>svReserveUsers をセットします。
     * @param svReserveUsers svReserveUsers
     */
    public void setSvReserveUsers(String[] svReserveUsers) {
        svReserveUsers__ = svReserveUsers;
    }

    /**
     * <p>sch041GroupSid を取得します。
     * @return sch041GroupSid
     */
    public String getSch041GroupSid() {
        return sch041GroupSid__;
    }

    /**
     * <p>sch041GroupSid をセットします。
     * @param sch041GroupSid sch041GroupSid
     */
    public void setSch041GroupSid(String sch041GroupSid) {
        sch041GroupSid__ = sch041GroupSid;
    }

    /**
     * <p>sch041users_r を取得します。
     * @return sch041users_r
     */
    public String[] getSch041users_r() {
        return sch041users_r__;
    }

    /**
     * <p>sch041users_r をセットします。
     * @param sch041usersr sch041usersr
     */
    public void setSch041users_r(String[] sch041usersr) {
        sch041users_r__ = sch041usersr;
    }

    /**
     * <p>sch041Edit を取得します。
     * @return sch041Edit
     */
    public String getSch041Edit() {
        return sch041Edit__;
    }


    /**
     * <p>sch041Edit をセットします。
     * @param sch041Edit sch041Edit
     */
    public void setSch041Edit(String sch041Edit) {
        sch041Edit__ = sch041Edit;
    }


    /**
     * <p>sch040AddedUsrLabel を取得します。
     * @return sch040AddedUsrLabel
     */
    public ArrayList<CmnUsrmInfModel> getSch040AddedUsrLabel() {
        return sch040AddedUsrLabel__;
    }


    /**
     * <p>sch040AddedUsrLabel をセットします。
     * @param sch040AddedUsrLabel sch040AddedUsrLabel
     */
    public void setSch040AddedUsrLabel(
            ArrayList<CmnUsrmInfModel> sch040AddedUsrLabel) {
        sch040AddedUsrLabel__ = sch040AddedUsrLabel;
    }


    /**
     * <p>sch040Edit を取得します。
     * @return sch040Edit
     */
    public String getSch040Edit() {
        return sch040Edit__;
    }


    /**
     * <p>sch040Edit をセットします。
     * @param sch040Edit sch040Edit
     */
    public void setSch040Edit(String sch040Edit) {
        sch040Edit__ = sch040Edit;
    }


    /**
     * <p>sch041BatchRef を取得します。
     * @return sch041BatchRef
     */
    public String getSch041BatchRef() {
        return sch041BatchRef__;
    }


    /**
     * <p>sch041BatchRef をセットします。
     * @param sch041BatchRef sch041BatchRef
     */
    public void setSch041BatchRef(String sch041BatchRef) {
        sch041BatchRef__ = sch041BatchRef;
    }


    /**
     * <p>sch041Bgcolor を取得します。
     * @return sch041Bgcolor
     */
    public String getSch041Bgcolor() {
        return sch041Bgcolor__;
    }


    /**
     * <p>sch041Bgcolor をセットします。
     * @param sch041Bgcolor sch041Bgcolor
     */
    public void setSch041Bgcolor(String sch041Bgcolor) {
        sch041Bgcolor__ = sch041Bgcolor;
    }


    /**
     * <p>sch041Biko を取得します。
     * @return sch041Biko
     */
    public String getSch041Biko() {
        return sch041Biko__;
    }


    /**
     * <p>sch041Biko をセットします。
     * @param sch041Biko sch041Biko
     */
    public void setSch041Biko(String sch041Biko) {
        sch041Biko__ = sch041Biko;
    }


    /**
     * <p>sch041Day を取得します。
     * @return sch041Day
     */
    public String getSch041Day() {
        return sch041Day__;
    }


    /**
     * <p>sch041Day をセットします。
     * @param sch041Day sch041Day
     */
    public void setSch041Day(String sch041Day) {
        sch041Day__ = sch041Day;
    }


    /**
     * <p>sch041Dweek を取得します。
     * @return sch041Dweek
     */
    public String[] getSch041Dweek() {
        return sch041Dweek__;
    }

    /**
     * <p>sch041Dweek をセットします。
     * @param sch041Dweek sch041Dweek
     */
    public void setSch041Dweek(String[] sch041Dweek) {
        sch041Dweek__ = sch041Dweek;
    }

    /**
     * <p>sch041ExtKbn を取得します。
     * @return sch041ExtKbn
     */
    public String getSch041ExtKbn() {
        return sch041ExtKbn__;
    }


    /**
     * <p>sch041ExtKbn をセットします。
     * @param sch041ExtKbn sch041ExtKbn
     */
    public void setSch041ExtKbn(String sch041ExtKbn) {
        sch041ExtKbn__ = sch041ExtKbn;
    }


    /**
     * <p>sch041FrDay を取得します。
     * @return sch041FrDay
     */
    public String getSch041FrDay() {
        return sch041FrDay__;
    }


    /**
     * <p>sch041FrDay をセットします。
     * @param sch041FrDay sch041FrDay
     */
    public void setSch041FrDay(String sch041FrDay) {
        sch041FrDay__ = sch041FrDay;
    }


    /**
     * <p>sch041FrHour を取得します。
     * @return sch041FrHour
     */
    public String getSch041FrHour() {
        return sch041FrHour__;
    }


    /**
     * <p>sch041FrHour をセットします。
     * @param sch041FrHour sch041FrHour
     */
    public void setSch041FrHour(String sch041FrHour) {
        sch041FrHour__ = sch041FrHour;
    }


    /**
     * <p>sch041FrMin を取得します。
     * @return sch041FrMin
     */
    public String getSch041FrMin() {
        return sch041FrMin__;
    }


    /**
     * <p>sch041FrMin をセットします。
     * @param sch041FrMin sch041FrMin
     */
    public void setSch041FrMin(String sch041FrMin) {
        sch041FrMin__ = sch041FrMin;
    }


    /**
     * <p>sch041FrMonth を取得します。
     * @return sch041FrMonth
     */
    public String getSch041FrMonth() {
        return sch041FrMonth__;
    }


    /**
     * <p>sch041FrMonth をセットします。
     * @param sch041FrMonth sch041FrMonth
     */
    public void setSch041FrMonth(String sch041FrMonth) {
        sch041FrMonth__ = sch041FrMonth;
    }


    /**
     * <p>sch041FrYear を取得します。
     * @return sch041FrYear
     */
    public String getSch041FrYear() {
        return sch041FrYear__;
    }


    /**
     * <p>sch041FrYear をセットします。
     * @param sch041FrYear sch041FrYear
     */
    public void setSch041FrYear(String sch041FrYear) {
        sch041FrYear__ = sch041FrYear;
    }


    /**
     * <p>sch041Public を取得します。
     * @return sch041Public
     */
    public String getSch041Public() {
        return sch041Public__;
    }


    /**
     * <p>sch041Public をセットします。
     * @param sch041Public sch041Public
     */
    public void setSch041Public(String sch041Public) {
        sch041Public__ = sch041Public;
    }


    /**
     * <p>sch041SvUsers を取得します。
     * @return sch041SvUsers
     */
    public String[] getSch041SvUsers() {
        return sch041SvUsers__;
    }


    /**
     * <p>sch041SvUsers をセットします。
     * @param sch041SvUsers sch041SvUsers
     */
    public void setSch041SvUsers(String[] sch041SvUsers) {
        sch041SvUsers__ = sch041SvUsers;
    }


    /**
     * <p>sch041Title を取得します。
     * @return sch041Title
     */
    public String getSch041Title() {
        return sch041Title__;
    }


    /**
     * <p>sch041Title をセットします。
     * @param sch041Title sch041Title
     */
    public void setSch041Title(String sch041Title) {
        sch041Title__ = sch041Title;
    }


    /**
     * <p>sch041ToDay を取得します。
     * @return sch041ToDay
     */
    public String getSch041ToDay() {
        return sch041ToDay__;
    }


    /**
     * <p>sch041ToDay をセットします。
     * @param sch041ToDay sch041ToDay
     */
    public void setSch041ToDay(String sch041ToDay) {
        sch041ToDay__ = sch041ToDay;
    }


    /**
     * <p>sch041ToHour を取得します。
     * @return sch041ToHour
     */
    public String getSch041ToHour() {
        return sch041ToHour__;
    }


    /**
     * <p>sch041ToHour をセットします。
     * @param sch041ToHour sch041ToHour
     */
    public void setSch041ToHour(String sch041ToHour) {
        sch041ToHour__ = sch041ToHour;
    }


    /**
     * <p>sch041ToMin を取得します。
     * @return sch041ToMin
     */
    public String getSch041ToMin() {
        return sch041ToMin__;
    }


    /**
     * <p>sch041ToMin をセットします。
     * @param sch041ToMin sch041ToMin
     */
    public void setSch041ToMin(String sch041ToMin) {
        sch041ToMin__ = sch041ToMin;
    }


    /**
     * <p>sch041ToMonth を取得します。
     * @return sch041ToMonth
     */
    public String getSch041ToMonth() {
        return sch041ToMonth__;
    }


    /**
     * <p>sch041ToMonth をセットします。
     * @param sch041ToMonth sch041ToMonth
     */
    public void setSch041ToMonth(String sch041ToMonth) {
        sch041ToMonth__ = sch041ToMonth;
    }


    /**
     * <p>sch041ToYear を取得します。
     * @return sch041ToYear
     */
    public String getSch041ToYear() {
        return sch041ToYear__;
    }


    /**
     * <p>sch041ToYear をセットします。
     * @param sch041ToYear sch041ToYear
     */
    public void setSch041ToYear(String sch041ToYear) {
        sch041ToYear__ = sch041ToYear;
    }


    /**
     * <p>sch041TranKbn を取得します。
     * @return sch041TranKbn
     */
    public String getSch041TranKbn() {
        return sch041TranKbn__;
    }


    /**
     * <p>sch041TranKbn をセットします。
     * @param sch041TranKbn sch041TranKbn
     */
    public void setSch041TranKbn(String sch041TranKbn) {
        sch041TranKbn__ = sch041TranKbn;
    }


    /**
     * <p>sch041Value を取得します。
     * @return sch041Value
     */
    public String getSch041Value() {
        return sch041Value__;
    }


    /**
     * <p>sch041Value をセットします。
     * @param sch041Value sch041Value
     */
    public void setSch041Value(String sch041Value) {
        sch041Value__ = sch041Value;
    }


    /**
     * <p>sch041Week を取得します。
     * @return sch041Week
     */
    public String getSch041Week() {
        return sch041Week__;
    }


    /**
     * <p>sch041Week をセットします。
     * @param sch041Week sch041Week
     */
    public void setSch041Week(String sch041Week) {
        sch041Week__ = sch041Week;
    }


    /**
     * <p>sch040BatchRef を取得します。
     * @return sch040BatchRef
     */
    public String getSch040BatchRef() {
        return sch040BatchRef__;
    }


    /**
     * <p>sch040BatchRef をセットします。
     * @param sch040BatchRef sch040BatchRef
     */
    public void setSch040BatchRef(String sch040BatchRef) {
        sch040BatchRef__ = sch040BatchRef;
    }


    /**
     * @return schDailyDate を戻します。
     */
    public String getSchDailyDate() {
        return schDailyDate__;
    }


    /**
     * @param schDailyDate 設定する schDailyDate。
     */
    public void setSchDailyDate(String schDailyDate) {
        schDailyDate__ = schDailyDate;
    }


    /**
     * @return schWeekDate を戻します。
     */
    public String getSchWeekDate() {
        return schWeekDate__;
    }


    /**
     * @param schWeekDate 設定する schWeekDate。
     */
    public void setSchWeekDate(String schWeekDate) {
        schWeekDate__ = schWeekDate;
    }


    /**
     * @return sv_users を戻します。
     */
    public String[] getSv_users() {
        return sv_users__;
    }


    /**
     * @return sch040AddUsrJkbn を戻します。
     */
    public String getSch040AddUsrJkbn() {
        return sch040AddUsrJkbn__;
    }


    /**
     * @param sch040AddUsrJkbn 設定する sch040AddUsrJkbn。
     */
    public void setSch040AddUsrJkbn(String sch040AddUsrJkbn) {
        sch040AddUsrJkbn__ = sch040AddUsrJkbn;
    }


    /**
     * @param svUsers 設定する sv_users。
     */
    public void setSv_users(String[] svUsers) {
        sv_users__ = svUsers;
    }


    /**
     * @return users_r を戻します。
     */
    public String[] getUsers_r() {
        return users_r__;
    }


    /**
     * @param usersr 設定する users_r。
     */
    public void setUsers_r(String[] usersr) {
        users_r__ = usersr;
    }


    /**
     * @return sch040FrHour を戻します。
     */
    public String getSch040FrHour() {
        return sch040FrHour__;
    }


    /**
     * @param sch040FrHour 設定する sch040FrHour。
     */
    public void setSch040FrHour(String sch040FrHour) {
        sch040FrHour__ = sch040FrHour;
    }


    /**
     * @return sch040FrMin を戻します。
     */
    public String getSch040FrMin() {
        return sch040FrMin__;
    }


    /**
     * @param sch040FrMin 設定する sch040FrMin。
     */
    public void setSch040FrMin(String sch040FrMin) {
        sch040FrMin__ = sch040FrMin;
    }


    /**
     * @return sch040ToHour を戻します。
     */
    public String getSch040ToHour() {
        return sch040ToHour__;
    }


    /**
     * @param sch040ToHour 設定する sch040ToHour。
     */
    public void setSch040ToHour(String sch040ToHour) {
        sch040ToHour__ = sch040ToHour;
    }


    /**
     * @return sch040ToMin を戻します。
     */
    public String getSch040ToMin() {
        return sch040ToMin__;
    }


    /**
     * @param sch040ToMin 設定する sch040ToMin。
     */
    public void setSch040ToMin(String sch040ToMin) {
        sch040ToMin__ = sch040ToMin;
    }

    /**
     * @return sch040GroupSid を戻します。
     */
    public String getSch040GroupSid() {
        return sch040GroupSid__;
    }


    /**
     * @param sch040GroupSid 設定する sch040GroupSid。
     */
    public void setSch040GroupSid(String sch040GroupSid) {
        sch040GroupSid__ = sch040GroupSid;
    }


    /**
     * @return sch040AddUsrName を戻します。
     */
    public String getSch040AddUsrName() {
        return sch040AddUsrName__;
    }


    /**
     * @param sch040AddUsrName 設定する sch040AddUsrName。
     */
    public void setSch040AddUsrName(String sch040AddUsrName) {
        sch040AddUsrName__ = sch040AddUsrName;
    }


    /**
     * @return sch040BelongLabel を戻します。
     */
    public ArrayList < CmnUsrmInfModel > getSch040BelongLabel() {
        return sch040BelongLabel__;
    }


    /**
     * @param sch040BelongLabel 設定する sch040BelongLabel。
     */
    public void setSch040BelongLabel(ArrayList < CmnUsrmInfModel > sch040BelongLabel) {
        sch040BelongLabel__ = sch040BelongLabel;
    }


    /**
     * @return sch040Bgcolor を戻します。
     */
    public String getSch040Bgcolor() {
        return sch040Bgcolor__;
    }


    /**
     * @param sch040Bgcolor 設定する sch040Bgcolor。
     */
    public void setSch040Bgcolor(String sch040Bgcolor) {
        sch040Bgcolor__ = sch040Bgcolor;
    }


    /**
     * @return sch040Biko を戻します。
     */
    public String getSch040Biko() {
        return sch040Biko__;
    }


    /**
     * @param sch040Biko 設定する sch040Biko。
     */
    public void setSch040Biko(String sch040Biko) {
        sch040Biko__ = sch040Biko;
    }


    /**
     * @return sch040DayLabel を戻します。
     */
    public ArrayList<LabelValueBean> getSch040DayLabel() {
        return sch040DayLabel__;
    }


    /**
     * @param sch040DayLabel 設定する sch040DayLabel。
     */
    public void setSch040DayLabel(ArrayList < LabelValueBean > sch040DayLabel) {
        sch040DayLabel__ = sch040DayLabel;
    }


    /**
     * @return sch040FrDay を戻します。
     */
    public String getSch040FrDay() {
        return sch040FrDay__;
    }


    /**
     * @param sch040FrDay 設定する sch040FrDay。
     */
    public void setSch040FrDay(String sch040FrDay) {
        sch040FrDay__ = sch040FrDay;
    }


    /**
     * @return sch040FrMonth を戻します。
     */
    public String getSch040FrMonth() {
        return sch040FrMonth__;
    }


    /**
     * @param sch040FrMonth 設定する sch040FrMonth。
     */
    public void setSch040FrMonth(String sch040FrMonth) {
        sch040FrMonth__ = sch040FrMonth;
    }


    /**
     * @return sch040FrYear を戻します。
     */
    public String getSch040FrYear() {
        return sch040FrYear__;
    }


    /**
     * @param sch040FrYear 設定する sch040FrYear。
     */
    public void setSch040FrYear(String sch040FrYear) {
        sch040FrYear__ = sch040FrYear;
    }


    /**
     * @return sch040GroupLabel を戻します。
     */
    public List<SchLabelValueModel> getSch040GroupLabel() {
        return sch040GroupLabel__;
    }


    /**
     * @param sch040GroupLabel 設定する sch040GroupLabel。
     */
    public void setSch040GroupLabel(List<SchLabelValueModel> sch040GroupLabel) {
        sch040GroupLabel__ = sch040GroupLabel;
    }


    /**
     * @return sch040MonthLabel を戻します。
     */
    public ArrayList < LabelValueBean > getSch040MonthLabel() {
        return sch040MonthLabel__;
    }


    /**
     * @param sch040MonthLabel 設定する sch040MonthLabel。
     */
    public void setSch040MonthLabel(ArrayList < LabelValueBean > sch040MonthLabel) {
        sch040MonthLabel__ = sch040MonthLabel;
    }


    /**
     * @return sch040Public を戻します。
     */
    public String getSch040Public() {
        return sch040Public__;
    }


    /**
     * @param sch040Public 設定する sch040Public。
     */
    public void setSch040Public(String sch040Public) {
        sch040Public__ = sch040Public;
    }


    /**
     * @return sch040SelectUsrLabel を戻します。
     */
    public ArrayList < CmnUsrmInfModel > getSch040SelectUsrLabel() {
        return sch040SelectUsrLabel__;
    }


    /**
     * @param sch040SelectUsrLabel 設定する sch040SelectUsrLabel。
     */
    public void setSch040SelectUsrLabel(ArrayList < CmnUsrmInfModel > sch040SelectUsrLabel) {
        sch040SelectUsrLabel__ = sch040SelectUsrLabel;
    }


    /**
     * @return sch040Title を戻します。
     */
    public String getSch040Title() {
        return sch040Title__;
    }


    /**
     * @param sch040Title 設定する sch040Title。
     */
    public void setSch040Title(String sch040Title) {
        sch040Title__ = sch040Title;
    }


    /**
     * @return sch040ToDay を戻します。
     */
    public String getSch040ToDay() {
        return sch040ToDay__;
    }


    /**
     * @param sch040ToDay 設定する sch040ToDay。
     */
    public void setSch040ToDay(String sch040ToDay) {
        sch040ToDay__ = sch040ToDay;
    }


    /**
     * @return sch040ToMonth を戻します。
     */
    public String getSch040ToMonth() {
        return sch040ToMonth__;
    }


    /**
     * @param sch040ToMonth 設定する sch040ToMonth。
     */
    public void setSch040ToMonth(String sch040ToMonth) {
        sch040ToMonth__ = sch040ToMonth;
    }


    /**
     * @return sch040ToYear を戻します。
     */
    public String getSch040ToYear() {
        return sch040ToYear__;
    }


    /**
     * @param sch040ToYear 設定する sch040ToYear。
     */
    public void setSch040ToYear(String sch040ToYear) {
        sch040ToYear__ = sch040ToYear;
    }


    /**
     * @return sch040UsrName を戻します。
     */
    public String getSch040UsrName() {
        return sch040UsrName__;
    }


    /**
     * @param sch040UsrName 設定する sch040UsrName。
     */
    public void setSch040UsrName(String sch040UsrName) {
        sch040UsrName__ = sch040UsrName;
    }


    /**
     * @return sch040Value を戻します。
     */
    public String getSch040Value() {
        return sch040Value__;
    }


    /**
     * @param sch040Value 設定する sch040Value。
     */
    public void setSch040Value(String sch040Value) {
        sch040Value__ = sch040Value;
    }


    /**
     * @return sch040YearLabel を戻します。
     */
    public ArrayList < LabelValueBean > getSch040YearLabel() {
        return sch040YearLabel__;
    }


    /**
     * @param sch040YearLabel 設定する sch040YearLabel。
     */
    public void setSch040YearLabel(ArrayList < LabelValueBean > sch040YearLabel) {
        sch040YearLabel__ = sch040YearLabel;
    }


    /**
     * @return sch040HourLabel を戻します。
     */
    public ArrayList < LabelValueBean > getSch040HourLabel() {
        return sch040HourLabel__;
    }


    /**
     * @param sch040HourLabel 設定する sch040HourLabel。
     */
    public void setSch040HourLabel(ArrayList < LabelValueBean > sch040HourLabel) {
        sch040HourLabel__ = sch040HourLabel;
    }


    /**
     * @return sch040MinuteLabel を戻します。
     */
    public ArrayList < LabelValueBean > getSch040MinuteLabel() {
        return sch040MinuteLabel__;
    }


    /**
     * @param sch040MinuteLabel 設定する sch040MinuteLabel。
     */
    public void setSch040MinuteLabel(ArrayList < LabelValueBean > sch040MinuteLabel) {
        sch040MinuteLabel__ = sch040MinuteLabel;
    }


    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(
            RequestModel reqMdl,
            Connection con,
            int sessionUsrSid) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl);

        //開始年月日チェックフラグ(true=入力OK、false=NG)
        boolean fromOk = false;

        int iSYear = -1;
        if (!StringUtil.isNullZeroStringSpace(sch040FrYear__)) {
            iSYear = Integer.parseInt(sch040FrYear__);
        }
        int iSMonth = Integer.parseInt(sch040FrMonth__);
        int iSDay = Integer.parseInt(sch040FrDay__);

        UDate frDate = new UDate();
        frDate.setDate(iSYear, iSMonth, iSDay);
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        if (frDate.getYear() != iSYear
        || frDate.getMonth() != iSMonth
        || frDate.getIntDay() != iSDay) {
            msg = new ActionMessage("error.input.notfound.date",
                    gsMsg.getMessage("schedule.sch100.10"));
            errors.add("error.input.notfound.date", msg);
            log__.debug("error:1");
        } else {
            fromOk = true;
        }

        //終了年月日チェックフラグ(true=入力OK、false=NG)
        boolean toOk = false;

        int iEYear = -1;
        if (!StringUtil.isNullZeroStringSpace(sch040ToYear__)) {
            iEYear = Integer.parseInt(sch040ToYear__);
        }
        int iEMonth = Integer.parseInt(sch040ToMonth__);
        int iEDay = Integer.parseInt(sch040ToDay__);

        UDate toDate = new UDate();
        toDate.setDate(iEYear, iEMonth, iEDay);
        toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        if (toDate.getYear() != iEYear
        || toDate.getMonth() != iEMonth
        || toDate.getIntDay() != iEDay) {
            msg = new ActionMessage("error.input.notfound.date",
                    gsMsg.getMessage("schedule.sch100.15"));
            errors.add("error.input.notfound.date", msg);
            log__.debug("error:2");
        } else {
            toOk = true;
        }

        if (fromOk && toOk && sch040TimeKbn__.equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {

            if (sch040FrHour__.equals("-1")
             || sch040FrMin__.equals("-1")
             || sch040ToHour__.equals("-1")
             || sch040ToMin__.equals("-1")
                    ) {
                        //時分
                        String textHourMinute = gsMsg.getMessage("schedule.src.36");
                        msg = new ActionMessage("error.input.required.text", textHourMinute);
                        errors.add("" + "error.input.required.text", msg);
                        fromOk = false;

                    }
        }

        //個別チェックOKの場合
        if (fromOk && toOk) {

            if (sch040TimeKbn__.equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
                if (sch040FrHour__.equals("-1") && sch040FrMin__.equals("-1")) {
                    frDate.setHour(GSConstSchedule.DAY_START_HOUR);
                    frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
                } else {
                    frDate.setHour(Integer.parseInt(sch040FrHour__));
                    frDate.setMinute(Integer.parseInt(sch040FrMin__));
                }
                if (sch040ToHour__.equals("-1") && sch040ToMin__.equals("-1")) {
                    toDate.setHour(GSConstSchedule.DAY_END_HOUR);
                    toDate.setMinute(GSConstSchedule.DAY_SYSMAX_MINUTES);
                } else {
                    toDate.setHour(Integer.parseInt(sch040ToHour__));
                    toDate.setMinute(Integer.parseInt(sch040ToMin__));
                }
            } else {
                frDate.setZeroHhMmSs();
                toDate.setMaxHhMmSs();
            }

            //from～to大小チェック
            if (frDate.compare(frDate, toDate) != UDate.LARGE) {
                //開始 < 終了
                String textStartLessEnd = gsMsg.getMessage("cmn.start.lessthan.end");
                //開始・終了
                String textStartEnd = gsMsg.getMessage("cmn.start.end");
                msg = new ActionMessage("error.input.comp.text", textStartEnd, textStartLessEnd);
                errors.add("" + "error.input.comp.text", msg);
                log__.debug("error:5");
            }

        }

        //タイトルのチェック
        if (__checkNoInput(errors, sch040Title__, "sch040Title",
                gsMsg.getMessage("cmn.title"))) {
            if (__checkRange(
                    errors,
                    sch040Title__,
                    "sch040Title",
                    gsMsg.getMessage("cmn.title"),
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(sch040Title__)) {
                    //タイトル
                    String textTitle = gsMsg.getMessage("cmn.title");
                    msg = new ActionMessage("error.input.spase.start",
                            textTitle);
                    StrutsUtil.addMessage(errors, msg, "sch040Title");
                } else {
                    __checkJisString(
                            errors,
                            sch040Title__,
                            "sch040Title",
                            gsMsg.getMessage("cmn.title"));
                }
            }

        }
        boolean valueError = false;
        //内容のチェック
        if (__checkRangeTextarea(
                errors,
                sch040Value__,
                "sch040Value",
                gsMsg.getMessage("cmn.content"),
                GSConstSchedule.MAX_LENGTH_VALUE)) {
            if (!StringUtil.isNullZeroString(sch040Value__)) {
                //スペースのみチェック
                if (!valueError && ValidateUtil.isSpaceOrKaigyou(sch040Value__)) {
                    msg = new ActionMessage("error.input.spase.cl.only",
                            gsMsg.getMessage("cmn.content"));
                    StrutsUtil.addMessage(errors, msg, "sch040Value");
                    valueError = true;
                }

                if (!valueError) {
                    //JIS
                    __checkJisString(
                            errors,
                            sch040Value__,
                            "sch040Value",
                            gsMsg.getMessage("cmn.content"));
                }
            }
        }

        //公開フラグ

        boolean bikoError = false;
        //備考のチェック
        if (__checkRangeTextarea(
                errors,
                sch040Biko__,
                "sch040Biko",
                gsMsg.getMessage("cmn.memo"),
                GSConstSchedule.MAX_LENGTH_BIKO)) {
            if (!StringUtil.isNullZeroString(sch040Biko__)) {
                //スペースのみチェック
                if (!bikoError && ValidateUtil.isSpaceOrKaigyou(sch040Biko__)) {
                    msg = new ActionMessage("error.input.spase.cl.only",
                            gsMsg.getMessage("cmn.memo"));
                    StrutsUtil.addMessage(errors, msg, "sch040Biko");
                    bikoError = true;
                }

                if (!bikoError) {
                    //JIS
                    __checkJisString(
                            errors,
                            sch040Biko__,
                            "sch040Biko",
                            gsMsg.getMessage("cmn.memo"));
                }
            }
        }

        //同時登録スケジュールの編集権限チェック
        errors = validateSchPowerCheck(reqMdl, errors, con);

        if (errors.isEmpty()) {
            //スケジュール重複登録チェック
            errors = validateSchRepeatCheck(reqMdl, errors, con, sessionUsrSid);
        }

        //同時登録施設予約の編集権限チェック
        errors = validateResPowerCheck(reqMdl, errors, con);

        //施設予約エラーチェック

        Sch040Biz biz = new Sch040Biz(con, reqMdl);

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con);
        boolean errorFlg = false;
        String[] rsdSids = null;

        if (sch040TimeKbn__.equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            rsdSids = getSvReserveUsers();
        }

        Rsv210Model dataMdl = null;
        if (rsdSids != null) {

            Rsv110Biz rsv110biz = new Rsv110Biz(reqMdl, con);
            for (String rsdSid : rsdSids) {
                dataMdl = rsv110biz.getGroupData(Integer.parseInt(rsdSid));
                if (dataMdl != null) {

                    //予約可能期限チェック(期限が設定されていればチェックする)
                    String kigen = dataMdl.getRsdProp6();
                    if (!StringUtil.isNullZeroString(kigen)) {

                        //施設グループ管理者の場合は予約可能期限チェックをパスする
                        RsvCommonBiz rsvBiz = new RsvCommonBiz();
                        if (!rsvBiz.isGroupAdmin(
                                con, Integer.parseInt(rsdSid), sessionUsrSid)) {

                            UDate now = new UDate();
                            UDate udKigen = now.cloneUDate();
                            udKigen.addDay(Integer.parseInt(kigen));

                            String kigenYmd = udKigen.getDateString();
                            String chkYmd = toDate.getDateString();

                            if (Integer.parseInt(chkYmd) > Integer.parseInt(kigenYmd)) {
                                //開始・終了
                                String textDayAfter = gsMsg.getMessage("cmn.days.after");
                                String kigenStr =
                                        "※"
                                                + dataMdl.getRsdProp6()
                                                + textDayAfter;

                                msg = new ActionMessage("error.kigen.over2.sisetu", kigenStr);
                                StrutsUtil.addMessage(errors, msg, "sisetu");
                                errorFlg = true;
                            }
                        }
                    }
                }

                //重複のチェック(重複登録 = 不可の場合にチェック)
                String tyohuku = dataMdl.getRsdProp7();
                if (!errorFlg
                        && !StringUtil.isNullZeroString(tyohuku)
                        && Integer.parseInt(tyohuku) == GSConstReserve.PROP_KBN_HUKA) {

                    List<RsvSisYrkModel> ngList = new ArrayList<RsvSisYrkModel>();
                    //施設予約重複チェック
                    //新規モード
                    if (getCmd().equals(GSConstSchedule.CMD_ADD)) {

                        ngList = yrkDao.getYrkNgList(-1, Integer.parseInt(rsdSid), frDate, toDate);

                        //編集モード
                    } else if (getCmd().equals(GSConstSchedule.CMD_EDIT)) {
                        SchCommonBiz cBiz = new SchCommonBiz(reqMdl);
                        SchAdmConfModel adminConf = cBiz.getAdmConfModel(con);
                        ScheduleSearchModel scMdl = biz.getSchData(
                                Integer.parseInt(getSch010SchSid()),
                                adminConf,
                                con);

                        ArrayList<RsvSisYrkModel> yrkList = null;
                        if (scMdl.getScdRsSid() != -1) {
                            yrkList = yrkDao.getScheduleRserveData(
                                    scMdl.getScdRsSid()
                                    );
                        }
                        RsvSisYrkModel yrkMdl = null;

                        yrkMdl = getReserveData(yrkList, Integer.parseInt(rsdSid));

                        if (yrkMdl == null) {
                            ngList = yrkDao.getYrkNgList(
                                    -1, Integer.parseInt(rsdSid), frDate, toDate);

                        } else {
                            ngList = yrkDao.getYrkNgList(
                                    yrkMdl.getRsySid(), yrkMdl.getRsdSid(), frDate, toDate);
                        }

                    }

                    //重複チェック
                    if (ngList != null && ngList.size() > 0) {

                        String textSchedule = gsMsg.getMessage("cmn.reserve");
                        msg = new ActionMessage("error.input.dup", textSchedule);
                        StrutsUtil.addMessage(errors, msg, "rsv110YrkEr");

                        for (RsvSisYrkModel yrkModel : ngList) {

                            String schTime = UDateUtil.getYymdJ(yrkModel.getRsyFrDate(), reqMdl);
                            schTime += UDateUtil.getSeparateHMJ(yrkModel.getRsyFrDate(), reqMdl);
                            schTime += "～";
                            schTime += UDateUtil.getYymdJ(yrkModel.getRsyToDate(), reqMdl);
                            schTime += UDateUtil.getSeparateHMJ(yrkModel.getRsyToDate(), reqMdl);


                            msg = new ActionMessage("error.input.dup.sch",
                                    schTime,
                                    StringUtilHtml.transToHTmlPlusAmparsant(yrkModel.getRsdName()),
                                    StringUtilHtml.transToHTmlPlusAmparsant(yrkModel.getRsyMok()));

                            StrutsUtil.addMessage(errors, msg,
                                    "rsv110YrkErr" + String.valueOf(yrkModel.getRsySid()));
                        }
                    }
                }
                if (errorFlg) {
                    break;
                }
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validatePowerCheck(
            RequestModel reqMdl,
            Connection con) throws SQLException {
        ActionErrors errors = new ActionErrors();
        //同時登録スケジュールの編集権限チェック
        errors = validateSchPowerCheck(reqMdl, errors, con);
        //施設予約エラーチェック
        errors = validateResPowerCheck(reqMdl, errors, con);
        return errors;
    }
    /**
     * <br>[機  能] 同時登録スケジュールの編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param errors アクションエラー
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateSchPowerCheck(
            RequestModel reqMdl,
            ActionErrors errors,
            Connection con) throws SQLException {

        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl);
        //変更
        String textChange = gsMsg.getMessage("cmn.change");
        int schSid = NullDefault.getInt(getSch010SchSid(), -1);
        //同時登録スケジュールの編集権限チェック
        if (getCmd().equals(GSConstSchedule.CMD_EDIT)
                && getSch040BatchRef().equals(String.valueOf(GSConstSchedule.SAME_EDIT_ON))) {
            //
            Sch010Biz biz = new Sch010Biz(reqMdl);
            if (!biz.isAllEditOk(schSid, reqMdl, con)) {
                //同時登録スケジュールに対する編集
                String textSimultaneousEdit = gsMsg.getMessage("schedule.src.33");
                msg = new ActionMessage("error.edit.power.user", textSimultaneousEdit, textChange);
                StrutsUtil.addMessage(errors, msg, "adduser");
            }
        }
        return errors;
    }

    /**
     * <br>[機  能] 同時登録スケジュールの重複登録チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param errors アクションエラー
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateSchRepeatCheck(
            RequestModel reqMdl,
            ActionErrors errors,
            Connection con,
            int sessionUsrSid
            ) throws SQLException {

        ActionMessage msg = null;

        Sch040Biz biz = new Sch040Biz(con, reqMdl);
        SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);
        GsMessage gsMsg = new GsMessage(reqMdl);

        //重複登録 NGスケジュール一覧を取得する。
        int scdUsrKbn = GSConstSchedule.USER_KBN_USER;
        String selectUsrKbn = NullDefault.getString(getSch010SelectUsrKbn(), "");
        if (selectUsrKbn.equals(String.valueOf(GSConstSchedule.USER_KBN_GROUP))) {
            scdUsrKbn = GSConstSchedule.USER_KBN_GROUP;
        }
        List<SchDataModel> rptSchList
                 = biz.getSchWarningList(
                         this, sessionUsrSid, con, GSConstSchedule.SCH_REPEAT_KBN_NG,
                         scdUsrKbn);

        if (rptSchList != null && rptSchList.size() > 0) {
            int i = 1;

            String textSchedule = gsMsg.getMessage("schedule.108");
            msg = new ActionMessage("error.input.dup", textSchedule);
            StrutsUtil.addMessage(errors, msg, "error.input.dup");
            String title = "";
            for (SchDataModel ngMdl : rptSchList) {

                //公開区分で判定してタイトルを取得
                title = schBiz.getDspTitle(ngMdl, sessionUsrSid);

                String schTime = UDateUtil.getYymdJ(ngMdl.getScdFrDate(), reqMdl);
                schTime += UDateUtil.getSeparateHMJ(ngMdl.getScdFrDate(), reqMdl);
                schTime += "～";
                schTime += UDateUtil.getYymdJ(ngMdl.getScdToDate(), reqMdl);
                schTime += UDateUtil.getSeparateHMJ(ngMdl.getScdToDate(), reqMdl);

                msg = new ActionMessage("error.input.dup.sch",
                        schTime,
                        StringUtilHtml.transToHTmlPlusAmparsant(title),
                        StringUtilHtml.transToHTmlPlusAmparsant(ngMdl.getScdUserName()));
                StrutsUtil.addMessage(errors, msg, "error.input.dup.sch" + i);
                i++;
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 同時登録施設予約の編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param errors アクションエラー
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateResPowerCheck(
            RequestModel reqMdl,
            ActionErrors errors,
            Connection con) throws SQLException {

        ActionMessage msg = null;
        int schSid = NullDefault.getInt(getSch010SchSid(), -1);
        GsMessage gsMsg = new GsMessage(reqMdl);

        //アクセス権限チェック
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        CommonBiz cmnBiz = new CommonBiz();
        Sch040Biz biz = new Sch040Biz(con, reqMdl);


        //編集権限のない施設数を取得する。
        if (sch040ResBatchRef__.equals("1")) {
            boolean rsvAdmin = cmnBiz.isPluginAdmin(
                    con, usModel, GSConstSchedule.PLUGIN_ID_RESERVE);
            int count = biz.getCanNotEditRsvCount(this, usModel.getUsrsid(), con, rsvAdmin);

            if (count > 0) {
                //施設予約アクセス権限なし
                msg = new ActionMessage("error.myself.auth");
                StrutsUtil.addMessage(errors, msg, "error.myself.auth");
                return errors;
            }

        }


        //変更
        String textChange = gsMsg.getMessage("cmn.change");
        //同時登録施設予約の編集権限チェック
        if (getCmd().equals(GSConstSchedule.CMD_EDIT) && getSch040ResBatchRef().equals("1")) {

            RelationBetweenScdAndRsvChkBiz rsvChkBiz
                = new RelationBetweenScdAndRsvChkBiz(reqMdl, con);
            int errorCd = rsvChkBiz.isRsvEdit(
                    schSid,
                    RelationBetweenScdAndRsvChkBiz.CHK_KBN_TANITU);
            log__.debug("施設予約の編集権限チェック:エラーコード==>" + errorCd);
            if (errorCd == RelationBetweenScdAndRsvChkBiz.ERR_CD_SCD_CANNOT_EDIT) {
                //施設予約に対する編集
                String textRsvEdit = gsMsg.getMessage("schedule.src.32");
                msg = new ActionMessage("error.edit.power.user", textRsvEdit, textChange);
                StrutsUtil.addMessage(errors, msg, "addres");
            }
        }
        return errors;
    }
    /**
     * 指定した施設SIDを配列から検索します。
     * <br>[機  能]配列に存在する場合はRsvSisYrkModelを返します。
     * <br>[解  説]配列に存在しない場合はnullを返します。
     * <br>[備  考]
     * @param yrkList 本スケジュールろ同時登録された施設予約情報リスト
     * @param rsdSid 検索される施設SID
     * @return RsvSisYrkModel
     */
    public RsvSisYrkModel getReserveData(ArrayList<RsvSisYrkModel> yrkList, int rsdSid) {
        RsvSisYrkModel ret = null;
        if (yrkList == null) {
            return ret;
        }

        for (RsvSisYrkModel yrkMdl : yrkList) {
            if (yrkMdl.getRsdSid() == rsdSid) {
                return yrkMdl;
            }
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定された項目の未入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkNoInput(ActionErrors errors,
                                    String value,
                                    String element,
                                    String elementName) {
        boolean result = true;
        ActionMessage msg = null;

        if (StringUtil.isNullZeroString(value)) {
            msg = new ActionMessage("error.input.required.text", elementName);
            errors.add(element + "error.input.required.text", msg);
            result = false;
            log__.debug("error:6");
        } else {
            //スペースのみの入力かチェック
            if (ValidateUtil.isSpace(value)) {
                msg = new ActionMessage("error.input.spase.only", elementName);
                errors.add(element + "error.input.spase.only", msg);
                result = false;
            }

        }

        return result;
    }

    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRange(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.text", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.text", msg);
            result = false;
            log__.debug("error:7");
        }
        return result;
    }

    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRangeTextarea(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.textarea", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.textarea", msg);
            result = false;
            log__.debug("error:7");
        }
        return result;
    }

    /**
     * <br>[機  能] 指定された項目がJIS第2水準文字かチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkJisString(ActionErrors errors,
                                String value,
                                String element,
                                String elementName) {
        boolean result = true;
        ActionMessage msg = null;
        //JIS第2水準文字か
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text", elementName, nstr);
            errors.add(element + "error.input.njapan.text", msg);
            result = false;
        }
        return result;
    }

    /**
     * <p>sch040AddDate を取得します。
     * @return sch040AddDate
     */
    public String getSch040AddDate() {
        return sch040AddDate__;
    }

    /**
     * <p>sch040AddDate をセットします。
     * @param sch040AddDate sch040AddDate
     */
    public void setSch040AddDate(String sch040AddDate) {
        sch040AddDate__ = sch040AddDate;
    }

    /**
     * <p>sch040InitFlg を取得します。
     * @return sch040InitFlg
     */
    public String getSch040InitFlg() {
        return sch040InitFlg__;
    }

    /**
     * <p>sch040InitFlg をセットします。
     * @param sch040InitFlg sch040InitFlg
     */
    public void setSch040InitFlg(String sch040InitFlg) {
        sch040InitFlg__ = sch040InitFlg;
    }

    /**
     * <p>sch040AddressId を取得します。
     * @return sch040AddressId
     */
    public String[] getSch040AddressId() {
        return sch040AddressId__;
    }

    /**
     * <p>sch040AddressId をセットします。
     * @param sch040AddressId sch040AddressId
     */
    public void setSch040AddressId(String[] sch040AddressId) {
        sch040AddressId__ = sch040AddressId;
    }

    /**
     * <p>sch040CompanyBaseSid を取得します。
     * @return sch040CompanyBaseSid
     */
    public String[] getSch040CompanyBaseSid() {
        return sch040CompanyBaseSid__;
    }

    /**
     * <p>sch040CompanyBaseSid をセットします。
     * @param sch040CompanyBaseSid sch040CompanyBaseSid
     */
    public void setSch040CompanyBaseSid(String[] sch040CompanyBaseSid) {
        sch040CompanyBaseSid__ = sch040CompanyBaseSid;
    }

    /**
     * <p>sch040CompanyList を取得します。
     * @return sch040CompanyList
     */
    public List<Sch040CompanyModel> getSch040CompanyList() {
        return sch040CompanyList__;
    }

    /**
     * <p>sch040CompanyList をセットします。
     * @param sch040CompanyList sch040CompanyList
     */
    public void setSch040CompanyList(List<Sch040CompanyModel> sch040CompanyList) {
        sch040CompanyList__ = sch040CompanyList;
    }

    /**
     * <p>sch040CompanySid を取得します。
     * @return sch040CompanySid
     */
    public String[] getSch040CompanySid() {
        return sch040CompanySid__;
    }

    /**
     * <p>sch040CompanySid をセットします。
     * @param sch040CompanySid sch040CompanySid
     */
    public void setSch040CompanySid(String[] sch040CompanySid) {
        sch040CompanySid__ = sch040CompanySid;
    }

    /**
     * <p>sch041AddressId を取得します。
     * @return sch041AddressId
     */
    public String[] getSch041AddressId() {
        return sch041AddressId__;
    }

    /**
     * <p>sch040contact を取得します。
     * @return sch040contact
     */
    public int getSch040contact() {
        return sch040contact__;
    }

    /**
     * <p>sch040contact をセットします。
     * @param sch040contact sch040contact
     */
    public void setSch040contact(int sch040contact) {
        sch040contact__ = sch040contact;
    }

    /**
     * <p>sch041AddressId をセットします。
     * @param sch041AddressId sch041AddressId
     */
    public void setSch041AddressId(String[] sch041AddressId) {
        sch041AddressId__ = sch041AddressId;
    }

    /**
     * <p>sch041CompanyBaseSid を取得します。
     * @return sch041CompanyBaseSid
     */
    public String[] getSch041CompanyBaseSid() {
        return sch041CompanyBaseSid__;
    }

    /**
     * <p>sch041CompanyBaseSid をセットします。
     * @param sch041CompanyBaseSid sch041CompanyBaseSid
     */
    public void setSch041CompanyBaseSid(String[] sch041CompanyBaseSid) {
        sch041CompanyBaseSid__ = sch041CompanyBaseSid;
    }

    /**
     * <p>sch041CompanyList を取得します。
     * @return sch041CompanyList
     */
    public List<Sch040CompanyModel> getSch041CompanyList() {
        return sch041CompanyList__;
    }

    /**
     * <p>sch041CompanyList をセットします。
     * @param sch041CompanyList sch041CompanyList
     */
    public void setSch041CompanyList(List<Sch040CompanyModel> sch041CompanyList) {
        sch041CompanyList__ = sch041CompanyList;
    }

    /**
     * <p>sch041CompanySid を取得します。
     * @return sch041CompanySid
     */
    public String[] getSch041CompanySid() {
        return sch041CompanySid__;
    }

    /**
     * <p>sch041CompanySid をセットします。
     * @param sch041CompanySid sch041CompanySid
     */
    public void setSch041CompanySid(String[] sch041CompanySid) {
        sch041CompanySid__ = sch041CompanySid;
    }

    /**
     * <p>sch041contact を取得します。
     * @return sch041contact
     */
    public int getSch041contact() {
        return sch041contact__;
    }

    /**
     * <p>sch041contact をセットします。
     * @param sch041contact sch041contact
     */
    public void setSch041contact(int sch041contact) {
        sch041contact__ = sch041contact;
    }

    /**
     * <p>sch040delCompanyBaseId を取得します。
     * @return sch040delCompanyBaseId
     */
    public String getSch040delCompanyBaseId() {
        return sch040delCompanyBaseId__;
    }

    /**
     * <p>sch040delCompanyBaseId をセットします。
     * @param sch040delCompanyBaseId sch040delCompanyBaseId
     */
    public void setSch040delCompanyBaseId(String sch040delCompanyBaseId) {
        sch040delCompanyBaseId__ = sch040delCompanyBaseId;
    }

    /**
     * <p>sch040delCompanyId を取得します。
     * @return sch040delCompanyId
     */
    public String getSch040delCompanyId() {
        return sch040delCompanyId__;
    }

    /**
     * <p>sch040delCompanyId をセットします。
     * @param sch040delCompanyId sch040delCompanyId
     */
    public void setSch040delCompanyId(String sch040delCompanyId) {
        sch040delCompanyId__ = sch040delCompanyId;
    }
    /**
     * @return sch040ScrollFlg
     */
    public String getSch040ScrollFlg() {
        return sch040ScrollFlg__;
    }

    /**
     * @param sch040ScrollFlg セットする sch040ScrollFlg
     */
    public void setSch040ScrollFlg(String sch040ScrollFlg) {
        sch040ScrollFlg__ = sch040ScrollFlg;
    }

    /**
     * <p>sch040ExTextDspFlg を取得します。
     * @return sch040ExTextDspFlg
     */
    public boolean isSch040ExTextDspFlg() {
        return sch040ExTextDspFlg__;
    }

    /**
     * <p>sch040ExTextDspFlg をセットします。
     * @param sch040ExTextDspFlg sch040ExTextDspFlg
     */
    public void setSch040ExTextDspFlg(boolean sch040ExTextDspFlg) {
        sch040ExTextDspFlg__ = sch040ExTextDspFlg;
    }

    /**
     * <p>sch040CopyFlg を取得します。
     * @return sch040CopyFlg
     */
    public String getSch040CopyFlg() {
        return sch040CopyFlg__;
    }

    /**
     * <p>sch040CopyFlg をセットします。
     * @param sch040CopyFlg sch040CopyFlg
     */
    public void setSch040CopyFlg(String sch040CopyFlg) {
        sch040CopyFlg__ = sch040CopyFlg;
    }

    /**
     * <p>sch040CantReadRsvCount を取得します。
     * @return sch040CantReadRsvCount
     */
    public int getSch040CantReadRsvCount() {
        return sch040CantReadRsvCount__;
    }

    /**
     * <p>sch040CantReadRsvCount をセットします。
     * @param sch040CantReadRsvCount sch040CantReadRsvCount
     */
    public void setSch040CantReadRsvCount(int sch040CantReadRsvCount) {
        sch040CantReadRsvCount__ = sch040CantReadRsvCount;
    }

    /**
     * <p>sch040CrangeKbn を取得します。
     * @return sch040CrangeKbn
     */
    public int getSch040CrangeKbn() {
        return sch040CrangeKbn__;
    }

    /**
     * <p>sch040CrangeKbn をセットします。
     * @param sch040CrangeKbn sch040CrangeKbn
     */
    public void setSch040CrangeKbn(int sch040CrangeKbn) {
        sch040CrangeKbn__ = sch040CrangeKbn;
    }

    /**
     * <p>sch040colorKbn を取得します。
     * @return sch040colorKbn
     */
    public int getSch040colorKbn() {
        return sch040colorKbn__;
    }

    /**
     * <p>sch040colorKbn をセットします。
     * @param sch040colorKbn sch040colorKbn
     */
    public void setSch040colorKbn(int sch040colorKbn) {
        sch040colorKbn__ = sch040colorKbn;
    }

    /**
     * <p>sch040AttendKbn を取得します。
     * @return sch040AttendKbn
     */
    public String getSch040AttendKbn() {
        return sch040AttendKbn__;
    }

    /**
     * <p>sch040AttendKbn をセットします。
     * @param sch040AttendKbn sch040AttendKbn
     */
    public void setSch040AttendKbn(String sch040AttendKbn) {
        sch040AttendKbn__ = sch040AttendKbn;
    }

    /**
     * <p>sch040AttendAnsKbn を取得します。
     * @return sch040AttendAnsKbn
     */
    public String getSch040AttendAnsKbn() {
        return sch040AttendAnsKbn__;
    }

    /**
     * <p>sch040AttendAnsKbn をセットします。
     * @param sch040AttendAnsKbn sch040AttendAnsKbn
     */
    public void setSch040AttendAnsKbn(String sch040AttendAnsKbn) {
        sch040AttendAnsKbn__ = sch040AttendAnsKbn;
    }

    /**
     * <p>sch040EditDspMode を取得します。
     * @return sch040EditDspMode
     */
    public String getSch040EditDspMode() {
        return sch040EditDspMode__;
    }

    /**
     * <p>sch040EditDspMode をセットします。
     * @param sch040EditDspMode sch040EditDspMode
     */
    public void setSch040EditDspMode(String sch040EditDspMode) {
        sch040EditDspMode__ = sch040EditDspMode;
    }

    /**
     * <p>sch040DspFromDate を取得します。
     * @return sch040DspFromDate
     */
    public String getSch040DspFromDate() {
        return sch040DspFromDate__;
    }

    /**
     * <p>sch040DspFromDate をセットします。
     * @param sch040DspFromDate sch040DspFromDate
     */
    public void setSch040DspFromDate(String sch040DspFromDate) {
        sch040DspFromDate__ = sch040DspFromDate;
    }

    /**
     * <p>sch040DspToDate を取得します。
     * @return sch040DspToDate
     */
    public String getSch040DspToDate() {
        return sch040DspToDate__;
    }

    /**
     * <p>sch040DspToDate をセットします。
     * @param sch040DspToDate sch040DspToDate
     */
    public void setSch040DspToDate(String sch040DspToDate) {
        sch040DspToDate__ = sch040DspToDate;
    }

    /**
     * <p>sch040DspValue を取得します。
     * @return sch040DspValue
     */
    public String getSch040DspValue() {
        return sch040DspValue__;
    }

    /**
     * <p>sch040DspValue をセットします。
     * @param sch040DspValue sch040DspValue
     */
    public void setSch040DspValue(String sch040DspValue) {
        sch040DspValue__ = sch040DspValue;
    }

    /**
     * <p>sch040DspBiko を取得します。
     * @return sch040DspBiko
     */
    public String getSch040DspBiko() {
        return sch040DspBiko__;
    }

    /**
     * <p>sch040DspBiko をセットします。
     * @param sch040DspBiko sch040DspBiko
     */
    public void setSch040DspBiko(String sch040DspBiko) {
        sch040DspBiko__ = sch040DspBiko;
    }

    /**
     * <p>sch040EditMailSendKbn を取得します。
     * @return sch040EditMailSendKbn
     */
    public int getSch040EditMailSendKbn() {
        return sch040EditMailSendKbn__;
    }

    /**
     * <p>sch040EditMailSendKbn をセットします。
     * @param sch040EditMailSendKbn sch040EditMailSendKbn
     */
    public void setSch040EditMailSendKbn(int sch040EditMailSendKbn) {
        sch040EditMailSendKbn__ = sch040EditMailSendKbn;
    }

    /**
     * <p>sch040AttendAnsList を取得します。
     * @return sch040AttendAnsList
     */
    public List<Sch040AttendModel> getSch040AttendAnsList() {
        return sch040AttendAnsList__;
    }

    /**
     * <p>sch040AttendAnsList をセットします。
     * @param sch040AttendAnsList sch040AttendAnsList
     */
    public void setSch040AttendAnsList(List<Sch040AttendModel> sch040AttendAnsList) {
        sch040AttendAnsList__ = sch040AttendAnsList;
    }

    /**
     * <p>sch040AttendLinkFlg を取得します。
     * @return sch040AttendLinkFlg
     */
    public int getSch040AttendLinkFlg() {
        return sch040AttendLinkFlg__;
    }

    /**
     * <p>sch040AttendLinkFlg をセットします。
     * @param sch040AttendLinkFlg sch040AttendLinkFlg
     */
    public void setSch040AttendLinkFlg(int sch040AttendLinkFlg) {
        sch040AttendLinkFlg__ = sch040AttendLinkFlg;
    }

    /**
     * <p>sch041DayOfYearly を取得します。
     * @return sch041DayOfYearly
     */
    public String getSch041DayOfYearly() {
        return sch041DayOfYearly__;
    }

    /**
     * <p>sch041DayOfYearly をセットします。
     * @param sch041DayOfYearly sch041DayOfYearly
     */
    public void setSch041DayOfYearly(String sch041DayOfYearly) {
        sch041DayOfYearly__ = sch041DayOfYearly;
    }

    /**
     * <p>sch041MonthOfYearly を取得します。
     * @return sch041MonthOfYearly
     */
    public String getSch041MonthOfYearly() {
        return sch041MonthOfYearly__;
    }

    /**
     * <p>sch041MonthOfYearly をセットします。
     * @param sch041MonthOfYearly sch041MonthOfYearly
     */
    public void setSch041MonthOfYearly(String sch041MonthOfYearly) {
        sch041MonthOfYearly__ = sch041MonthOfYearly;
    }

    /**
     * <p>sch040BtnCmd を取得します。
     * @return sch040BtnCmd
     */
    public String getSch040BtnCmd() {
        return sch040BtnCmd__;
    }

    /**
     * <p>sch040BtnCmd をセットします。
     * @param sch040BtnCmd sch040BtnCmd
     */
    public void setSch040BtnCmd(String sch040BtnCmd) {
        this.sch040BtnCmd__ = sch040BtnCmd;
    }

    /**
     * <p>sch040AttendDelFlg を取得します。
     * @return sch040AttendDelFlg
     */
    public int getSch040AttendDelFlg() {
        return sch040AttendDelFlg__;
    }

    /**
     * <p>sch040AttendDelFlg をセットします。
     * @param sch040AttendDelFlg sch040AttendDelFlg
     */
    public void setSch040AttendDelFlg(int sch040AttendDelFlg) {
        sch040AttendDelFlg__ = sch040AttendDelFlg;
    }

    /**
     * <p>sch040ViewFlg を取得します。
     * @return sch040ViewFlg
     */
    public boolean isSch040ViewFlg() {
        return sch040ViewFlg__;
    }

    /**
     * <p>sch040ViewFlg をセットします。
     * @param sch040ViewFlg sch040ViewFlg
     */
    public void setSch040ViewFlg(boolean sch040ViewFlg) {
        sch040ViewFlg__ = sch040ViewFlg;
    }
}
