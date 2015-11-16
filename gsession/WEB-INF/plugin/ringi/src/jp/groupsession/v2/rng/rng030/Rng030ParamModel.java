package jp.groupsession.v2.rng.rng030;

import java.util.List;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.model.RingiChannelDataModel;
import jp.groupsession.v2.rng.rng010.Rng010ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議内容確認画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng030ParamModel extends Rng010ParamModel {
    /** 処理モード 閲覧 */
    public static final int CMDMODE_VIEW = 0;
    /** 処理モード 承認 */
    public static final int CMDMODE_APPR = 1;
    /** 処理モード 確認 */
    public static final int CMDMODE_CONFIRM = 2;
    /** 処理モード 管理者承認 */
    public static final int CMDMODE_ADMINAPPR = 3;

    /** 処理モード */
    private int rng030CmdMode__ = CMDMODE_VIEW;
    /** 承認モード */
    private int rng030mode__ = RngConst.RNG_APPRMODE_APPR;
    /** 確認モード */
    private int rng030confirmMode__ = -1;
    /** 差し戻しボタン表示フラグ */
    private int rng030rftBtnFlg__ = 0;
    /** 確認ボタン表示フラグ */
    private int rng030cfmBtnFlg__ = 0;
    /** 完了ボタン表示フラグ */
    private int rng030compBtnFlg__ = 0;
    /** スキップボタン表示フラグ */
    private int rng030skipBtnFlg__ = 0;
    /** 添付ファイルID */
    private Long rng030fileId__ = new Long(0);
    /** コメント */
    private String rng030Comment__ = null;
    /** 確認時添付一覧 */
    private String[] rng030files__ = null;

    //表示項目
    /** 状態 */
    private int rng030Status__ = RngConst.RNG_STATUS_REQUEST;
    /** タイトル */
    private String rng030Title__ = null;
    /** タイトル(表示用) */
    private String rng030ViewTitle__ = null;
    /** 申請者 */
    private String rng030apprUser__ = null;
    /** 作成日 */
    private String rng030makeDate__ = null;
    /** 内容 */
    private String rng030Content__ = null;
    /** 内容(表示用) */
    private String rng030ViewContent__ = null;
    /** ファイルダウンロードフラグ */
    private int rng030filedownloadFlg__ = 0;
    /** 完了フラグ */
    private int rng030completeFlg__ = 0;

    /** 添付情報 */
    private List<CmnBinfModel> tmpFileList__ = null;
    /** 確認時添付一覧 */
    private List<LabelValueBean> rng030fileList__ = null;
    /** 経路情報一覧 */
    private List<RingiChannelDataModel> channelList__ = null;
    /** 経路情報一覧(確認) */
    private List<RingiChannelDataModel> confirmChannelList__ = null;
    /** 経路情報一覧件数 */
    private String channelListCount__ = "0";
    /** 経路情報一覧(確認)件数 */
    private String confirmChannelListCount__ = "0";

    //遷移元画面のパラメータ
    /** キーワード */
    private String rngAdminKeyword__;
    /** グループSID */
    private int rngAdminGroupSid__ = -1;
    /** ユーザSID */
    private int rngAdminUserSid__ = -1;
    /** キーワード(入力) */
    private String rngInputKeyword__;
    /** 申請日時 年 From */
    private int rngAdminApplYearFr__ = -1;
    /** 申請日時 月 From */
    private int rngAdminApplMonthFr__ = -1;
    /** 申請日時 日 From */
    private int rngAdminApplDayFr__ = -1;
    /** 申請日時 年 To */
    private int rngAdminApplYearTo__ = -1;
    /** 申請日時 月 To */
    private int rngAdminApplMonthTo__ = -1;
    /** 申請日時 日 To */
    private int rngAdminApplDayTo__ = -1;
    /** 最終処理日時 年 From */
    private int rngAdminLastManageYearFr__ = -1;
    /** 最終処理日時 月 From */
    private int rngAdminLastManageMonthFr__ = -1;
    /** 最終処理日時 日 From */
    private int rngAdminLastManageDayFr__ = -1;
    /** 最終処理日時 年 To */
    private int rngAdminLastManageYearTo__ = -1;
    /** 最終処理日時 月 To */
    private int rngAdminLastManageMonthTo__ = -1;
    /** 最終処理日時 日 To */
    private int rngAdminLastManageDayTo__ = -1;

    /** 選択グループSID */
    private int sltGroupSid__ = -1;
    /** 選択グループSID */
    private int sltUserSid__ = -1;

    /** 選択申請日時 年 From */
    private int sltApplYearFr__ = -1;
    /** 選択申請日時 月 From */
    private int sltApplMonthFr__ = -1;
    /** 選択申請日時 日 From */
    private int sltApplDayFr__ = -1;
    /** 選択申請日時 年 To */
    private int sltApplYearTo__ = -1;
    /** 選択申請日時 月 To */
    private int sltApplMonthTo__ = -1;
    /** 選択申請日時 日 To */
    private int sltApplDayTo__ = -1;
    /** 選択最終処理日時 年 From */
    private int sltLastManageYearFr__ = -1;
    /** 選択最終処理日時 月 From */
    private int sltLastManageMonthFr__ = -1;
    /** 選択最終処理日時 日 From */
    private int sltLastManageDayFr__ = -1;
    /** 選択最終処理日時 年 To */
    private int sltLastManageYearTo__ = -1;
    /** 選択最終処理日時 月 To */
    private int sltLastManageMonthTo__ = -1;
    /** 選択最終処理日時 日 To */
    private int sltLastManageDayTo__ = -1;

    /** ソートキー */
    private int rngAdminSortKey__ = -1;
    /** オーダーキー */
    private int rngAdminOrderKey__ = -1;
    /** ページ(上) */
    private int rngAdminPageTop__ = 1;
    /** ページ(下) */
    private int rngAdminPageBottom__ = 1;
    /** 検索実行フラグ */
    private int rngAdminSearchFlg__ = 0;

    //遷移元画面(検索画面)のパラメータ
    /** 種別 */
    private int rng130Type__ = RngConst.RNG_SORT_JYUSIN;
    /** キーワード 条件 */
    private int rng130keyKbn__ = 0;
    /** 検索対象 件名 */
    private int rng130searchSubject1__ = 0;
    /** 検索対象 内容 */
    private int rng130searchSubject2__ = 0;
    /** 第１ソートキー */
    private int sltSortKey1__ = -1;
    /** 第１オーダーキー */
    private int rng130orderKey1__ = RngConst.RNG_ORDER_ASC;
    /** 第２ソートキー */
    private int sltSortKey2__ = -1;
    /** 第２オーダーキー */
    private int rng130orderKey2__ = RngConst.RNG_ORDER_ASC;
    /** ページコンボ上段 */
    private int rng130pageTop__ = 1;
    /** ページコンボ下段 */
    private int rng130pageBottom__ = 1;

    /** キーワード(検索条件保持用) */
    private String svRngKeyword__ = null;
    /** 種別(検索条件保持用) */
    private int svRng130Type__ = RngConst.RNG_SORT_JYUSIN;
    /** 承認者 グループ(検索条件保持用) */
    private int svGroupSid__ = -1;
    /** 承認者 ユーザ(検索条件保持用) */
    private int svUserSid__ = -1;
    /** キーワード 条件(検索条件保持用) */
    private int svRng130keyKbn__ = 0;
    /** 検索対象 件名(検索条件保持用) */
    private int svRng130searchSubject1__ = 0;
    /** 検索対象 内容(検索条件保持用) */
    private int svRng130searchSubject2__ = 0;
    /** 第１ソートキー(検索条件保持用) */
    private int svSortKey1__ = -1;
    /** 第１オーダーキー(検索条件保持用) */
    private int svRng130orderKey1__ = RngConst.RNG_ORDER_ASC;
    /** 第２ソートキー(検索条件保持用) */
    private int svSortKey2__ = -1;
    /** 第２オーダーキー(検索条件保持用) */
    private int svRng130orderKey2__ = RngConst.RNG_ORDER_ASC;
    /** 選択申請日時 年 From(検索条件保持用) */
    private int svApplYearFr__ = -1;
    /** 選択申請日時 月 From(検索条件保持用) */
    private int svApplMonthFr__ = -1;
    /** 選択申請日時 日 From(検索条件保持用) */
    private int svApplDayFr__ = -1;
    /** 選択申請日時 年 To(検索条件保持用) */
    private int svApplYearTo__ = -1;
    /** 選択申請日時 月 To(検索条件保持用) */
    private int svApplMonthTo__ = -1;
    /** 選択申請日時 日 To(検索条件保持用) */
    private int svApplDayTo__ = -1;
    /** 選択最終処理日時 年 From(検索条件保持用) */
    private int svLastManageYearFr__ = -1;
    /** 選択最終処理日時 月 From(検索条件保持用) */
    private int svLastManageMonthFr__ = -1;
    /** 選択最終処理日時 日 From(検索条件保持用) */
    private int svLastManageDayFr__ = -1;
    /** 選択最終処理日時 年 To(検索条件保持用) */
    private int svLastManageYearTo__ = -1;
    /** 選択最終処理日時 月 To(検索条件保持用) */
    private int svLastManageMonthTo__ = -1;
    /** 選択最終処理日時 日 To(検索条件保持用) */
    private int svLastManageDayTo__ = -1;
    /** 検索フラグ */
    private int rng130searchFlg__ = 0;

    /** 複写して申請ボタン表示フラグ */
    private boolean rng030copyApplBtn__ = false;
    /** 複写して申請 */
    private boolean rng020copyApply__ = false;

    /** WEB検索プラグイン使用可否 0=使用 1=未使用 */
    private int rngWebSearchUse__ = GSConst.PLUGIN_USE;

    /**
     * <p>channelList を取得します。
     * @return channelList
     */
    public List<RingiChannelDataModel> getChannelList() {
        return channelList__;
    }
    /**
     * <p>channelList をセットします。
     * @param channelList channelList
     */
    public void setChannelList(List<RingiChannelDataModel> channelList) {
        channelList__ = channelList;
    }
    /**
     * <p>rng030confirmMode を取得します。
     * @return rng030confirmMode
     */
    public int getRng030confirmMode() {
        return rng030confirmMode__;
    }
    /**
     * <p>rng030confirmMode をセットします。
     * @param rng030confirmMode rng030confirmMode
     */
    public void setRng030confirmMode(int rng030confirmMode) {
        rng030confirmMode__ = rng030confirmMode;
    }
    /**
     * <p>rng030apprUser を取得します。
     * @return rng030apprUser
     */
    public String getRng030apprUser() {
        return rng030apprUser__;
    }
    /**
     * <p>rng030apprUser をセットします。
     * @param rng030apprUser rng030apprUser
     */
    public void setRng030apprUser(String rng030apprUser) {
        rng030apprUser__ = rng030apprUser;
    }
    /**
     * <p>rng030CmdMode を取得します。
     * @return rng030CmdMode
     */
    public int getRng030CmdMode() {
        return rng030CmdMode__;
    }
    /**
     * <p>rng030CmdMode をセットします。
     * @param rng030CmdMode rng030CmdMode
     */
    public void setRng030CmdMode(int rng030CmdMode) {
        rng030CmdMode__ = rng030CmdMode;
    }
    /**
     * <p>rng030Comment を取得します。
     * @return rng030Comment
     */
    public String getRng030Comment() {
        return rng030Comment__;
    }
    /**
     * <p>rng030Comment をセットします。
     * @param rng030Comment rng030Comment
     */
    public void setRng030Comment(String rng030Comment) {
        rng030Comment__ = rng030Comment;
    }
    /**
     * <p>rng030Content を取得します。
     * @return rng030Content
     */
    public String getRng030Content() {
        return rng030Content__;
    }
    /**
     * <p>rng030Content をセットします。
     * @param rng030Content rng030Content
     */
    public void setRng030Content(String rng030Content) {
        rng030Content__ = rng030Content;
    }
    /**
     * <p>rng030ViewContent を取得します。
     * @return rng030ViewContent
     */
    public String getRng030ViewContent() {
        return rng030ViewContent__;
    }
    /**
     * <p>rng030ViewContent をセットします。
     * @param rng030ViewContent rng030ViewContent
     */
    public void setRng030ViewContent(String rng030ViewContent) {
        rng030ViewContent__ = rng030ViewContent;
    }
    /**
     * <p>rng030filedownloadFlg を取得します。
     * @return rng030filedownloadFlg
     */
    public int getRng030filedownloadFlg() {
        return rng030filedownloadFlg__;
    }
    /**
     * <p>rng030filedownloadFlg をセットします。
     * @param rng030filedownloadFlg rng030filedownloadFlg
     */
    public void setRng030filedownloadFlg(int rng030filedownloadFlg) {
        rng030filedownloadFlg__ = rng030filedownloadFlg;
    }
    /**
     * <p>rng030fileId を取得します。
     * @return rng030fileId
     */
    public Long getRng030fileId() {
        return rng030fileId__;
    }
    /**
     * <p>rng030fileId をセットします。
     * @param rng030fileId rng030fileId
     */
    public void setRng030fileId(Long rng030fileId) {
        rng030fileId__ = rng030fileId;
    }
    /**
     * <p>rng030fileList を取得します。
     * @return rng030fileList
     */
    public List<LabelValueBean> getRng030fileList() {
        return rng030fileList__;
    }
    /**
     * <p>rng030fileList をセットします。
     * @param rng030fileList rng030fileList
     */
    public void setRng030fileList(List<LabelValueBean> rng030fileList) {
        rng030fileList__ = rng030fileList;
    }
    /**
     * <p>rng030files を取得します。
     * @return rng030files
     */
    public String[] getRng030files() {
        return rng030files__;
    }
    /**
     * <p>rng030files をセットします。
     * @param rng030files rng030files
     */
    public void setRng030files(String[] rng030files) {
        rng030files__ = rng030files;
    }
    /**
     * <p>rng030makeDate を取得します。
     * @return rng030makeDate
     */
    public String getRng030makeDate() {
        return rng030makeDate__;
    }
    /**
     * <p>rng030makeDate をセットします。
     * @param rng030makeDate rng030makeDate
     */
    public void setRng030makeDate(String rng030makeDate) {
        rng030makeDate__ = rng030makeDate;
    }
    /**
     * <p>rng030mode を取得します。
     * @return rng030mode
     */
    public int getRng030mode() {
        return rng030mode__;
    }
    /**
     * <p>rng030mode をセットします。
     * @param rng030mode rng030mode
     */
    public void setRng030mode(int rng030mode) {
        rng030mode__ = rng030mode;
    }
    /**
     * <p>rng030Status を取得します。
     * @return rng030Status
     */
    public int getRng030Status() {
        return rng030Status__;
    }
    /**
     * <p>rng030Status をセットします。
     * @param rng030Status rng030Status
     */
    public void setRng030Status(int rng030Status) {
        rng030Status__ = rng030Status;
    }
    /**
     * <p>rng030Title を取得します。
     * @return rng030Title
     */
    public String getRng030Title() {
        return rng030Title__;
    }
    /**
     * <p>rng030Title をセットします。
     * @param rng030Title rng030Title
     */
    public void setRng030Title(String rng030Title) {
        rng030Title__ = rng030Title;
    }
    /**
     * <p>rng030ViewTitle を取得します。
     * @return rng030ViewTitle
     */
    public String getRng030ViewTitle() {
        return rng030ViewTitle__;
    }
    /**
     * <p>rng030ViewTitle をセットします。
     * @param rng030ViewTitle rng030ViewTitle
     */
    public void setRng030ViewTitle(String rng030ViewTitle) {
        rng030ViewTitle__ = rng030ViewTitle;
    }
    /**
     * <p>tmpFileList を取得します。
     * @return tmpFileList
     */
    public List<CmnBinfModel> getTmpFileList() {
        return tmpFileList__;
    }
    /**
     * <p>tmpFileList をセットします。
     * @param tmpFileList tmpFileList
     */
    public void setTmpFileList(List<CmnBinfModel> tmpFileList) {
        tmpFileList__ = tmpFileList;
    }
    /**
     * <p>confirmChannelList を取得します。
     * @return confirmChannelList
     */
    public List<RingiChannelDataModel> getConfirmChannelList() {
        return confirmChannelList__;
    }
    /**
     * <p>confirmChannelList をセットします。
     * @param confirmChannelList confirmChannelList
     */
    public void setConfirmChannelList(List<RingiChannelDataModel> confirmChannelList) {
        confirmChannelList__ = confirmChannelList;
    }
    /**
     * <p>channelListCount を取得します。
     * @return channelListCount
     */
    public String getChannelListCount() {
        return channelListCount__;
    }
    /**
     * <p>channelListCount をセットします。
     * @param channelListCount channelListCount
     */
    public void setChannelListCount(String channelListCount) {
        channelListCount__ = channelListCount;
    }
    /**
     * <p>confirmChannelListCount を取得します。
     * @return confirmChannelListCount
     */
    public String getConfirmChannelListCount() {
        return confirmChannelListCount__;
    }
    /**
     * <p>confirmChannelListCount をセットします。
     * @param confirmChannelListCount confirmChannelListCount
     */
    public void setConfirmChannelListCount(String confirmChannelListCount) {
        confirmChannelListCount__ = confirmChannelListCount;
    }
    /**
     * <p>rng030cfmBtnFlg を取得します。
     * @return rng030cfmBtnFlg
     */
    public int getRng030cfmBtnFlg() {
        return rng030cfmBtnFlg__;
    }
    /**
     * <p>rng030cfmBtnFlg をセットします。
     * @param rng030cfmBtnFlg rng030cfmBtnFlg
     */
    public void setRng030cfmBtnFlg(int rng030cfmBtnFlg) {
        rng030cfmBtnFlg__ = rng030cfmBtnFlg;
    }
    /**
     * <p>rng030compBtnFlg を取得します。
     * @return rng030compBtnFlg
     */
    public int getRng030compBtnFlg() {
        return rng030compBtnFlg__;
    }
    /**
     * <p>rng030compBtnFlg をセットします。
     * @param rng030compBtnFlg rng030compBtnFlg
     */
    public void setRng030compBtnFlg(int rng030compBtnFlg) {
        rng030compBtnFlg__ = rng030compBtnFlg;
    }
    /**
     * <p>rng030rftBtnFlg を取得します。
     * @return rng030rftBtnFlg
     */
    public int getRng030rftBtnFlg() {
        return rng030rftBtnFlg__;
    }
    /**
     * <p>rng030rftBtnFlg をセットします。
     * @param rng030rftBtnFlg rng030rftBtnFlg
     */
    public void setRng030rftBtnFlg(int rng030rftBtnFlg) {
        rng030rftBtnFlg__ = rng030rftBtnFlg;
    }
    /**
     * <p>rng030skipBtnFlg を取得します。
     * @return rng030skipBtnFlg
     */
    public int getRng030skipBtnFlg() {
        return rng030skipBtnFlg__;
    }
    /**
     * <p>rng030skipBtnFlg をセットします。
     * @param rng030skipBtnFlg rng030skipBtnFlg
     */
    public void setRng030skipBtnFlg(int rng030skipBtnFlg) {
        rng030skipBtnFlg__ = rng030skipBtnFlg;
    }
    /**
     * <p>rngAdminKeyword を取得します。
     * @return rngAdminKeyword
     */
    public String getRngAdminKeyword() {
        return rngAdminKeyword__;
    }
    /**
     * <p>rngAdminKeyword をセットします。
     * @param rngAdminKeyword rngAdminKeyword
     */
    public void setRngAdminKeyword(String rngAdminKeyword) {
        rngAdminKeyword__ = rngAdminKeyword;
    }
    /**
     * <p>rngAdminOrderKey を取得します。
     * @return rngAdminOrderKey
     */
    public int getRngAdminOrderKey() {
        return rngAdminOrderKey__;
    }
    /**
     * <p>rngAdminOrderKey をセットします。
     * @param rngAdminOrderKey rngAdminOrderKey
     */
    public void setRngAdminOrderKey(int rngAdminOrderKey) {
        rngAdminOrderKey__ = rngAdminOrderKey;
    }
    /**
     * <p>rngAdminPageBottom を取得します。
     * @return rngAdminPageBottom
     */
    public int getRngAdminPageBottom() {
        return rngAdminPageBottom__;
    }
    /**
     * <p>rngAdminPageBottom をセットします。
     * @param rngAdminPageBottom rngAdminPageBottom
     */
    public void setRngAdminPageBottom(int rngAdminPageBottom) {
        rngAdminPageBottom__ = rngAdminPageBottom;
    }
    /**
     * <p>rngAdminPageTop を取得します。
     * @return rngAdminPageTop
     */
    public int getRngAdminPageTop() {
        return rngAdminPageTop__;
    }
    /**
     * <p>rngAdminPageTop をセットします。
     * @param rngAdminPageTop rngAdminPageTop
     */
    public void setRngAdminPageTop(int rngAdminPageTop) {
        rngAdminPageTop__ = rngAdminPageTop;
    }
    /**
     * <p>rngAdminSortKey を取得します。
     * @return rngAdminSortKey
     */
    public int getRngAdminSortKey() {
        return rngAdminSortKey__;
    }
    /**
     * <p>rngAdminSortKey をセットします。
     * @param rngAdminSortKey rngAdminSortKey
     */
    public void setRngAdminSortKey(int rngAdminSortKey) {
        rngAdminSortKey__ = rngAdminSortKey;
    }
    /**
     * <p>sltGroupSid を取得します。
     * @return sltGroupSid
     */
    public int getSltGroupSid() {
        return sltGroupSid__;
    }
    /**
     * <p>sltGroupSid をセットします。
     * @param sltGroupSid sltGroupSid
     */
    public void setSltGroupSid(int sltGroupSid) {
        sltGroupSid__ = sltGroupSid;
    }
    /**
     * <p>sltUserSid を取得します。
     * @return sltUserSid
     */
    public int getSltUserSid() {
        return sltUserSid__;
    }
    /**
     * <p>sltUserSid をセットします。
     * @param sltUserSid sltUserSid
     */
    public void setSltUserSid(int sltUserSid) {
        sltUserSid__ = sltUserSid;
    }
    /**
     * <p>rngAdminSearchFlg を取得します。
     * @return rngAdminSearchFlg
     */
    public int getRngAdminSearchFlg() {
        return rngAdminSearchFlg__;
    }
    /**
     * <p>rngAdminSearchFlg をセットします。
     * @param rngAdminSearchFlg rngAdminSearchFlg
     */
    public void setRngAdminSearchFlg(int rngAdminSearchFlg) {
        rngAdminSearchFlg__ = rngAdminSearchFlg;
    }
    /**
     * <p>rngAdminGroupSid を取得します。
     * @return rngAdminGroupSid
     */
    public int getRngAdminGroupSid() {
        return rngAdminGroupSid__;
    }
    /**
     * <p>rngAdminGroupSid をセットします。
     * @param rngAdminGroupSid rngAdminGroupSid
     */
    public void setRngAdminGroupSid(int rngAdminGroupSid) {
        rngAdminGroupSid__ = rngAdminGroupSid;
    }
    /**
     * <p>rngAdminUserSid を取得します。
     * @return rngAdminUserSid
     */
    public int getRngAdminUserSid() {
        return rngAdminUserSid__;
    }
    /**
     * <p>rngAdminUserSid をセットします。
     * @param rngAdminUserSid rngAdminUserSid
     */
    public void setRngAdminUserSid(int rngAdminUserSid) {
        rngAdminUserSid__ = rngAdminUserSid;
    }
    /**
     * <p>rngInputKeyword を取得します。
     * @return rngInputKeyword
     */
    public String getRngInputKeyword() {
        return rngInputKeyword__;
    }
    /**
     * <p>rngInputKeyword をセットします。
     * @param rngInputKeyword rngInputKeyword
     */
    public void setRngInputKeyword(String rngInputKeyword) {
        rngInputKeyword__ = rngInputKeyword;
    }
    /**
     * <p>rng030completeFlg を取得します。
     * @return rng030completeFlg
     */
    public int getRng030completeFlg() {
        return rng030completeFlg__;
    }
    /**
     * <p>rng030completeFlg をセットします。
     * @param rng030completeFlg rng030completeFlg
     */
    public void setRng030completeFlg(int rng030completeFlg) {
        rng030completeFlg__ = rng030completeFlg;
    }
    /**
     * <p>rngAdminApplDayFr を取得します。
     * @return rngAdminApplDayFr
     */
    public int getRngAdminApplDayFr() {
        return rngAdminApplDayFr__;
    }
    /**
     * <p>rngAdminApplDayFr をセットします。
     * @param rngAdminApplDayFr rngAdminApplDayFr
     */
    public void setRngAdminApplDayFr(int rngAdminApplDayFr) {
        rngAdminApplDayFr__ = rngAdminApplDayFr;
    }
    /**
     * <p>rngAdminApplDayTo を取得します。
     * @return rngAdminApplDayTo
     */
    public int getRngAdminApplDayTo() {
        return rngAdminApplDayTo__;
    }
    /**
     * <p>rngAdminApplDayTo をセットします。
     * @param rngAdminApplDayTo rngAdminApplDayTo
     */
    public void setRngAdminApplDayTo(int rngAdminApplDayTo) {
        rngAdminApplDayTo__ = rngAdminApplDayTo;
    }
    /**
     * <p>rngAdminApplMonthFr を取得します。
     * @return rngAdminApplMonthFr
     */
    public int getRngAdminApplMonthFr() {
        return rngAdminApplMonthFr__;
    }
    /**
     * <p>rngAdminApplMonthFr をセットします。
     * @param rngAdminApplMonthFr rngAdminApplMonthFr
     */
    public void setRngAdminApplMonthFr(int rngAdminApplMonthFr) {
        rngAdminApplMonthFr__ = rngAdminApplMonthFr;
    }
    /**
     * <p>rngAdminApplMonthTo を取得します。
     * @return rngAdminApplMonthTo
     */
    public int getRngAdminApplMonthTo() {
        return rngAdminApplMonthTo__;
    }
    /**
     * <p>rngAdminApplMonthTo をセットします。
     * @param rngAdminApplMonthTo rngAdminApplMonthTo
     */
    public void setRngAdminApplMonthTo(int rngAdminApplMonthTo) {
        rngAdminApplMonthTo__ = rngAdminApplMonthTo;
    }
    /**
     * <p>rngAdminApplYearFr を取得します。
     * @return rngAdminApplYearFr
     */
    public int getRngAdminApplYearFr() {
        return rngAdminApplYearFr__;
    }
    /**
     * <p>rngAdminApplYearFr をセットします。
     * @param rngAdminApplYearFr rngAdminApplYearFr
     */
    public void setRngAdminApplYearFr(int rngAdminApplYearFr) {
        rngAdminApplYearFr__ = rngAdminApplYearFr;
    }
    /**
     * <p>rngAdminApplYearTo を取得します。
     * @return rngAdminApplYearTo
     */
    public int getRngAdminApplYearTo() {
        return rngAdminApplYearTo__;
    }
    /**
     * <p>rngAdminApplYearTo をセットします。
     * @param rngAdminApplYearTo rngAdminApplYearTo
     */
    public void setRngAdminApplYearTo(int rngAdminApplYearTo) {
        rngAdminApplYearTo__ = rngAdminApplYearTo;
    }
    /**
     * <p>rngAdminLastManageDayFr を取得します。
     * @return rngAdminLastManageDayFr
     */
    public int getRngAdminLastManageDayFr() {
        return rngAdminLastManageDayFr__;
    }
    /**
     * <p>rngAdminLastManageDayFr をセットします。
     * @param rngAdminLastManageDayFr rngAdminLastManageDayFr
     */
    public void setRngAdminLastManageDayFr(int rngAdminLastManageDayFr) {
        rngAdminLastManageDayFr__ = rngAdminLastManageDayFr;
    }
    /**
     * <p>rngAdminLastManageDayTo を取得します。
     * @return rngAdminLastManageDayTo
     */
    public int getRngAdminLastManageDayTo() {
        return rngAdminLastManageDayTo__;
    }
    /**
     * <p>rngAdminLastManageDayTo をセットします。
     * @param rngAdminLastManageDayTo rngAdminLastManageDayTo
     */
    public void setRngAdminLastManageDayTo(int rngAdminLastManageDayTo) {
        rngAdminLastManageDayTo__ = rngAdminLastManageDayTo;
    }
    /**
     * <p>rngAdminLastManageMonthFr を取得します。
     * @return rngAdminLastManageMonthFr
     */
    public int getRngAdminLastManageMonthFr() {
        return rngAdminLastManageMonthFr__;
    }
    /**
     * <p>rngAdminLastManageMonthFr をセットします。
     * @param rngAdminLastManageMonthFr rngAdminLastManageMonthFr
     */
    public void setRngAdminLastManageMonthFr(int rngAdminLastManageMonthFr) {
        rngAdminLastManageMonthFr__ = rngAdminLastManageMonthFr;
    }
    /**
     * <p>rngAdminLastManageMonthTo を取得します。
     * @return rngAdminLastManageMonthTo
     */
    public int getRngAdminLastManageMonthTo() {
        return rngAdminLastManageMonthTo__;
    }
    /**
     * <p>rngAdminLastManageMonthTo をセットします。
     * @param rngAdminLastManageMonthTo rngAdminLastManageMonthTo
     */
    public void setRngAdminLastManageMonthTo(int rngAdminLastManageMonthTo) {
        rngAdminLastManageMonthTo__ = rngAdminLastManageMonthTo;
    }
    /**
     * <p>rngAdminLastManageYearFr を取得します。
     * @return rngAdminLastManageYearFr
     */
    public int getRngAdminLastManageYearFr() {
        return rngAdminLastManageYearFr__;
    }
    /**
     * <p>rngAdminLastManageYearFr をセットします。
     * @param rngAdminLastManageYearFr rngAdminLastManageYearFr
     */
    public void setRngAdminLastManageYearFr(int rngAdminLastManageYearFr) {
        rngAdminLastManageYearFr__ = rngAdminLastManageYearFr;
    }
    /**
     * <p>rngAdminLastManageYearTo を取得します。
     * @return rngAdminLastManageYearTo
     */
    public int getRngAdminLastManageYearTo() {
        return rngAdminLastManageYearTo__;
    }
    /**
     * <p>rngAdminLastManageYearTo をセットします。
     * @param rngAdminLastManageYearTo rngAdminLastManageYearTo
     */
    public void setRngAdminLastManageYearTo(int rngAdminLastManageYearTo) {
        rngAdminLastManageYearTo__ = rngAdminLastManageYearTo;
    }
    /**
     * <p>sltApplDayFr を取得します。
     * @return sltApplDayFr
     */
    public int getSltApplDayFr() {
        return sltApplDayFr__;
    }
    /**
     * <p>sltApplDayFr をセットします。
     * @param sltApplDayFr sltApplDayFr
     */
    public void setSltApplDayFr(int sltApplDayFr) {
        sltApplDayFr__ = sltApplDayFr;
    }
    /**
     * <p>sltApplDayTo を取得します。
     * @return sltApplDayTo
     */
    public int getSltApplDayTo() {
        return sltApplDayTo__;
    }
    /**
     * <p>sltApplDayTo をセットします。
     * @param sltApplDayTo sltApplDayTo
     */
    public void setSltApplDayTo(int sltApplDayTo) {
        sltApplDayTo__ = sltApplDayTo;
    }
    /**
     * <p>sltApplMonthFr を取得します。
     * @return sltApplMonthFr
     */
    public int getSltApplMonthFr() {
        return sltApplMonthFr__;
    }
    /**
     * <p>sltApplMonthFr をセットします。
     * @param sltApplMonthFr sltApplMonthFr
     */
    public void setSltApplMonthFr(int sltApplMonthFr) {
        sltApplMonthFr__ = sltApplMonthFr;
    }
    /**
     * <p>sltApplMonthTo を取得します。
     * @return sltApplMonthTo
     */
    public int getSltApplMonthTo() {
        return sltApplMonthTo__;
    }
    /**
     * <p>sltApplMonthTo をセットします。
     * @param sltApplMonthTo sltApplMonthTo
     */
    public void setSltApplMonthTo(int sltApplMonthTo) {
        sltApplMonthTo__ = sltApplMonthTo;
    }
    /**
     * <p>sltApplYearFr を取得します。
     * @return sltApplYearFr
     */
    public int getSltApplYearFr() {
        return sltApplYearFr__;
    }
    /**
     * <p>sltApplYearFr をセットします。
     * @param sltApplYearFr sltApplYearFr
     */
    public void setSltApplYearFr(int sltApplYearFr) {
        sltApplYearFr__ = sltApplYearFr;
    }
    /**
     * <p>sltApplYearTo を取得します。
     * @return sltApplYearTo
     */
    public int getSltApplYearTo() {
        return sltApplYearTo__;
    }
    /**
     * <p>sltApplYearTo をセットします。
     * @param sltApplYearTo sltApplYearTo
     */
    public void setSltApplYearTo(int sltApplYearTo) {
        sltApplYearTo__ = sltApplYearTo;
    }
    /**
     * <p>sltLastManageDayFr を取得します。
     * @return sltLastManageDayFr
     */
    public int getSltLastManageDayFr() {
        return sltLastManageDayFr__;
    }
    /**
     * <p>sltLastManageDayFr をセットします。
     * @param sltLastManageDayFr sltLastManageDayFr
     */
    public void setSltLastManageDayFr(int sltLastManageDayFr) {
        sltLastManageDayFr__ = sltLastManageDayFr;
    }
    /**
     * <p>sltLastManageDayTo を取得します。
     * @return sltLastManageDayTo
     */
    public int getSltLastManageDayTo() {
        return sltLastManageDayTo__;
    }
    /**
     * <p>sltLastManageDayTo をセットします。
     * @param sltLastManageDayTo sltLastManageDayTo
     */
    public void setSltLastManageDayTo(int sltLastManageDayTo) {
        sltLastManageDayTo__ = sltLastManageDayTo;
    }
    /**
     * <p>sltLastManageMonthFr を取得します。
     * @return sltLastManageMonthFr
     */
    public int getSltLastManageMonthFr() {
        return sltLastManageMonthFr__;
    }
    /**
     * <p>sltLastManageMonthFr をセットします。
     * @param sltLastManageMonthFr sltLastManageMonthFr
     */
    public void setSltLastManageMonthFr(int sltLastManageMonthFr) {
        sltLastManageMonthFr__ = sltLastManageMonthFr;
    }
    /**
     * <p>sltLastManageMonthTo を取得します。
     * @return sltLastManageMonthTo
     */
    public int getSltLastManageMonthTo() {
        return sltLastManageMonthTo__;
    }
    /**
     * <p>sltLastManageMonthTo をセットします。
     * @param sltLastManageMonthTo sltLastManageMonthTo
     */
    public void setSltLastManageMonthTo(int sltLastManageMonthTo) {
        sltLastManageMonthTo__ = sltLastManageMonthTo;
    }
    /**
     * <p>sltLastManageYearFr を取得します。
     * @return sltLastManageYearFr
     */
    public int getSltLastManageYearFr() {
        return sltLastManageYearFr__;
    }
    /**
     * <p>sltLastManageYearFr をセットします。
     * @param sltLastManageYearFr sltLastManageYearFr
     */
    public void setSltLastManageYearFr(int sltLastManageYearFr) {
        sltLastManageYearFr__ = sltLastManageYearFr;
    }
    /**
     * <p>sltLastManageYearTo を取得します。
     * @return sltLastManageYearTo
     */
    public int getSltLastManageYearTo() {
        return sltLastManageYearTo__;
    }
    /**
     * <p>sltLastManageYearTo をセットします。
     * @param sltLastManageYearTo sltLastManageYearTo
     */
    public void setSltLastManageYearTo(int sltLastManageYearTo) {
        sltLastManageYearTo__ = sltLastManageYearTo;
    }
    /**
     * <p>rng130keyKbn を取得します。
     * @return rng130keyKbn
     */
    public int getRng130keyKbn() {
        return rng130keyKbn__;
    }
    /**
     * <p>rng130keyKbn をセットします。
     * @param rng130keyKbn rng130keyKbn
     */
    public void setRng130keyKbn(int rng130keyKbn) {
        rng130keyKbn__ = rng130keyKbn;
    }
    /**
     * <p>rng130orderKey1 を取得します。
     * @return rng130orderKey1
     */
    public int getRng130orderKey1() {
        return rng130orderKey1__;
    }
    /**
     * <p>rng130orderKey1 をセットします。
     * @param rng130orderKey1 rng130orderKey1
     */
    public void setRng130orderKey1(int rng130orderKey1) {
        rng130orderKey1__ = rng130orderKey1;
    }
    /**
     * <p>rng130orderKey2 を取得します。
     * @return rng130orderKey2
     */
    public int getRng130orderKey2() {
        return rng130orderKey2__;
    }
    /**
     * <p>rng130orderKey2 をセットします。
     * @param rng130orderKey2 rng130orderKey2
     */
    public void setRng130orderKey2(int rng130orderKey2) {
        rng130orderKey2__ = rng130orderKey2;
    }
    /**
     * <p>rng130pageBottom を取得します。
     * @return rng130pageBottom
     */
    public int getRng130pageBottom() {
        return rng130pageBottom__;
    }
    /**
     * <p>rng130pageBottom をセットします。
     * @param rng130pageBottom rng130pageBottom
     */
    public void setRng130pageBottom(int rng130pageBottom) {
        rng130pageBottom__ = rng130pageBottom;
    }
    /**
     * <p>rng130pageTop を取得します。
     * @return rng130pageTop
     */
    public int getRng130pageTop() {
        return rng130pageTop__;
    }
    /**
     * <p>rng130pageTop をセットします。
     * @param rng130pageTop rng130pageTop
     */
    public void setRng130pageTop(int rng130pageTop) {
        rng130pageTop__ = rng130pageTop;
    }
    /**
     * <p>rng130searchSubject1 を取得します。
     * @return rng130searchSubject1
     */
    public int getRng130searchSubject1() {
        return rng130searchSubject1__;
    }
    /**
     * <p>rng130searchSubject1 をセットします。
     * @param rng130searchSubject1 rng130searchSubject1
     */
    public void setRng130searchSubject1(int rng130searchSubject1) {
        rng130searchSubject1__ = rng130searchSubject1;
    }
    /**
     * <p>rng130searchSubject2 を取得します。
     * @return rng130searchSubject2
     */
    public int getRng130searchSubject2() {
        return rng130searchSubject2__;
    }
    /**
     * <p>rng130searchSubject2 をセットします。
     * @param rng130searchSubject2 rng130searchSubject2
     */
    public void setRng130searchSubject2(int rng130searchSubject2) {
        rng130searchSubject2__ = rng130searchSubject2;
    }
    /**
     * <p>rng130Type を取得します。
     * @return rng130Type
     */
    public int getRng130Type() {
        return rng130Type__;
    }
    /**
     * <p>rng130Type をセットします。
     * @param rng130Type rng130Type
     */
    public void setRng130Type(int rng130Type) {
        rng130Type__ = rng130Type;
    }
    /**
     * <p>sltSortKey1 を取得します。
     * @return sltSortKey1
     */
    public int getSltSortKey1() {
        return sltSortKey1__;
    }
    /**
     * <p>sltSortKey1 をセットします。
     * @param sltSortKey1 sltSortKey1
     */
    public void setSltSortKey1(int sltSortKey1) {
        sltSortKey1__ = sltSortKey1;
    }
    /**
     * <p>sltSortKey2 を取得します。
     * @return sltSortKey2
     */
    public int getSltSortKey2() {
        return sltSortKey2__;
    }
    /**
     * <p>sltSortKey2 をセットします。
     * @param sltSortKey2 sltSortKey2
     */
    public void setSltSortKey2(int sltSortKey2) {
        sltSortKey2__ = sltSortKey2;
    }
    /**
     * <p>svApplDayFr を取得します。
     * @return svApplDayFr
     */
    public int getSvApplDayFr() {
        return svApplDayFr__;
    }
    /**
     * <p>svApplDayFr をセットします。
     * @param svApplDayFr svApplDayFr
     */
    public void setSvApplDayFr(int svApplDayFr) {
        svApplDayFr__ = svApplDayFr;
    }
    /**
     * <p>svApplDayTo を取得します。
     * @return svApplDayTo
     */
    public int getSvApplDayTo() {
        return svApplDayTo__;
    }
    /**
     * <p>svApplDayTo をセットします。
     * @param svApplDayTo svApplDayTo
     */
    public void setSvApplDayTo(int svApplDayTo) {
        svApplDayTo__ = svApplDayTo;
    }
    /**
     * <p>svApplMonthFr を取得します。
     * @return svApplMonthFr
     */
    public int getSvApplMonthFr() {
        return svApplMonthFr__;
    }
    /**
     * <p>svApplMonthFr をセットします。
     * @param svApplMonthFr svApplMonthFr
     */
    public void setSvApplMonthFr(int svApplMonthFr) {
        svApplMonthFr__ = svApplMonthFr;
    }
    /**
     * <p>svApplMonthTo を取得します。
     * @return svApplMonthTo
     */
    public int getSvApplMonthTo() {
        return svApplMonthTo__;
    }
    /**
     * <p>svApplMonthTo をセットします。
     * @param svApplMonthTo svApplMonthTo
     */
    public void setSvApplMonthTo(int svApplMonthTo) {
        svApplMonthTo__ = svApplMonthTo;
    }
    /**
     * <p>svApplYearFr を取得します。
     * @return svApplYearFr
     */
    public int getSvApplYearFr() {
        return svApplYearFr__;
    }
    /**
     * <p>svApplYearFr をセットします。
     * @param svApplYearFr svApplYearFr
     */
    public void setSvApplYearFr(int svApplYearFr) {
        svApplYearFr__ = svApplYearFr;
    }
    /**
     * <p>svApplYearTo を取得します。
     * @return svApplYearTo
     */
    public int getSvApplYearTo() {
        return svApplYearTo__;
    }
    /**
     * <p>svApplYearTo をセットします。
     * @param svApplYearTo svApplYearTo
     */
    public void setSvApplYearTo(int svApplYearTo) {
        svApplYearTo__ = svApplYearTo;
    }
    /**
     * <p>svGroupSid を取得します。
     * @return svGroupSid
     */
    public int getSvGroupSid() {
        return svGroupSid__;
    }
    /**
     * <p>svGroupSid をセットします。
     * @param svGroupSid svGroupSid
     */
    public void setSvGroupSid(int svGroupSid) {
        svGroupSid__ = svGroupSid;
    }
    /**
     * <p>svLastManageDayFr を取得します。
     * @return svLastManageDayFr
     */
    public int getSvLastManageDayFr() {
        return svLastManageDayFr__;
    }
    /**
     * <p>svLastManageDayFr をセットします。
     * @param svLastManageDayFr svLastManageDayFr
     */
    public void setSvLastManageDayFr(int svLastManageDayFr) {
        svLastManageDayFr__ = svLastManageDayFr;
    }
    /**
     * <p>svLastManageDayTo を取得します。
     * @return svLastManageDayTo
     */
    public int getSvLastManageDayTo() {
        return svLastManageDayTo__;
    }
    /**
     * <p>svLastManageDayTo をセットします。
     * @param svLastManageDayTo svLastManageDayTo
     */
    public void setSvLastManageDayTo(int svLastManageDayTo) {
        svLastManageDayTo__ = svLastManageDayTo;
    }
    /**
     * <p>svLastManageMonthFr を取得します。
     * @return svLastManageMonthFr
     */
    public int getSvLastManageMonthFr() {
        return svLastManageMonthFr__;
    }
    /**
     * <p>svLastManageMonthFr をセットします。
     * @param svLastManageMonthFr svLastManageMonthFr
     */
    public void setSvLastManageMonthFr(int svLastManageMonthFr) {
        svLastManageMonthFr__ = svLastManageMonthFr;
    }
    /**
     * <p>svLastManageMonthTo を取得します。
     * @return svLastManageMonthTo
     */
    public int getSvLastManageMonthTo() {
        return svLastManageMonthTo__;
    }
    /**
     * <p>svLastManageMonthTo をセットします。
     * @param svLastManageMonthTo svLastManageMonthTo
     */
    public void setSvLastManageMonthTo(int svLastManageMonthTo) {
        svLastManageMonthTo__ = svLastManageMonthTo;
    }
    /**
     * <p>svLastManageYearFr を取得します。
     * @return svLastManageYearFr
     */
    public int getSvLastManageYearFr() {
        return svLastManageYearFr__;
    }
    /**
     * <p>svLastManageYearFr をセットします。
     * @param svLastManageYearFr svLastManageYearFr
     */
    public void setSvLastManageYearFr(int svLastManageYearFr) {
        svLastManageYearFr__ = svLastManageYearFr;
    }
    /**
     * <p>svLastManageYearTo を取得します。
     * @return svLastManageYearTo
     */
    public int getSvLastManageYearTo() {
        return svLastManageYearTo__;
    }
    /**
     * <p>svLastManageYearTo をセットします。
     * @param svLastManageYearTo svLastManageYearTo
     */
    public void setSvLastManageYearTo(int svLastManageYearTo) {
        svLastManageYearTo__ = svLastManageYearTo;
    }
    /**
     * <p>svRng130keyKbn を取得します。
     * @return svRng130keyKbn
     */
    public int getSvRng130keyKbn() {
        return svRng130keyKbn__;
    }
    /**
     * <p>svRng130keyKbn をセットします。
     * @param svRng130keyKbn svRng130keyKbn
     */
    public void setSvRng130keyKbn(int svRng130keyKbn) {
        svRng130keyKbn__ = svRng130keyKbn;
    }
    /**
     * <p>svRng130orderKey1 を取得します。
     * @return svRng130orderKey1
     */
    public int getSvRng130orderKey1() {
        return svRng130orderKey1__;
    }
    /**
     * <p>svRng130orderKey1 をセットします。
     * @param svRng130orderKey1 svRng130orderKey1
     */
    public void setSvRng130orderKey1(int svRng130orderKey1) {
        svRng130orderKey1__ = svRng130orderKey1;
    }
    /**
     * <p>svRng130orderKey2 を取得します。
     * @return svRng130orderKey2
     */
    public int getSvRng130orderKey2() {
        return svRng130orderKey2__;
    }
    /**
     * <p>svRng130orderKey2 をセットします。
     * @param svRng130orderKey2 svRng130orderKey2
     */
    public void setSvRng130orderKey2(int svRng130orderKey2) {
        svRng130orderKey2__ = svRng130orderKey2;
    }
    /**
     * <p>svRng130searchSubject1 を取得します。
     * @return svRng130searchSubject1
     */
    public int getSvRng130searchSubject1() {
        return svRng130searchSubject1__;
    }
    /**
     * <p>svRng130searchSubject1 をセットします。
     * @param svRng130searchSubject1 svRng130searchSubject1
     */
    public void setSvRng130searchSubject1(int svRng130searchSubject1) {
        svRng130searchSubject1__ = svRng130searchSubject1;
    }
    /**
     * <p>svRng130searchSubject2 を取得します。
     * @return svRng130searchSubject2
     */
    public int getSvRng130searchSubject2() {
        return svRng130searchSubject2__;
    }
    /**
     * <p>svRng130searchSubject2 をセットします。
     * @param svRng130searchSubject2 svRng130searchSubject2
     */
    public void setSvRng130searchSubject2(int svRng130searchSubject2) {
        svRng130searchSubject2__ = svRng130searchSubject2;
    }
    /**
     * <p>svRng130Type を取得します。
     * @return svRng130Type
     */
    public int getSvRng130Type() {
        return svRng130Type__;
    }
    /**
     * <p>svRng130Type をセットします。
     * @param svRng130Type svRng130Type
     */
    public void setSvRng130Type(int svRng130Type) {
        svRng130Type__ = svRng130Type;
    }
    /**
     * <p>svRngKeyword を取得します。
     * @return svRngKeyword
     */
    public String getSvRngKeyword() {
        return svRngKeyword__;
    }
    /**
     * <p>svRngKeyword をセットします。
     * @param svRngKeyword svRngKeyword
     */
    public void setSvRngKeyword(String svRngKeyword) {
        svRngKeyword__ = svRngKeyword;
    }
    /**
     * <p>svSortKey1 を取得します。
     * @return svSortKey1
     */
    public int getSvSortKey1() {
        return svSortKey1__;
    }
    /**
     * <p>svSortKey1 をセットします。
     * @param svSortKey1 svSortKey1
     */
    public void setSvSortKey1(int svSortKey1) {
        svSortKey1__ = svSortKey1;
    }
    /**
     * <p>svSortKey2 を取得します。
     * @return svSortKey2
     */
    public int getSvSortKey2() {
        return svSortKey2__;
    }
    /**
     * <p>svSortKey2 をセットします。
     * @param svSortKey2 svSortKey2
     */
    public void setSvSortKey2(int svSortKey2) {
        svSortKey2__ = svSortKey2;
    }
    /**
     * <p>rng130searchFlg を取得します。
     * @return rng130searchFlg
     */
    public int getRng130searchFlg() {
        return rng130searchFlg__;
    }
    /**
     * <p>rng130searchFlg をセットします。
     * @param rng130searchFlg rng130searchFlg
     */
    public void setRng130searchFlg(int rng130searchFlg) {
        rng130searchFlg__ = rng130searchFlg;
    }
    /**
     * <p>svUserSid を取得します。
     * @return svUserSid
     */
    public int getSvUserSid() {
        return svUserSid__;
    }
    /**
     * <p>svUserSid をセットします。
     * @param svUserSid svUserSid
     */
    public void setSvUserSid(int svUserSid) {
        svUserSid__ = svUserSid;
    }
    /**
     * <p>rng020copyApply を取得します。
     * @return rng020copyApply
     */
    public boolean isRng020copyApply() {
        return rng020copyApply__;
    }
    /**
     * <p>rng020copyApply をセットします。
     * @param rng020copyApply rng020copyApply
     */
    public void setRng020copyApply(boolean rng020copyApply) {
        rng020copyApply__ = rng020copyApply;
    }
    /**
     * <p>rng030copyApplBtn を取得します。
     * @return rng030copyApplBtn
     */
    public boolean isRng030copyApplBtn() {
        return rng030copyApplBtn__;
    }
    /**
     * <p>rng030copyApplBtn をセットします。
     * @param rng030copyApplBtn rng030copyApplBtn
     */
    public void setRng030copyApplBtn(boolean rng030copyApplBtn) {
        rng030copyApplBtn__ = rng030copyApplBtn;
    }

    /**
     * <p>rngWebSearchUse を取得します。
     * @return rngWebSearchUse
     */
    public int getRngWebSearchUse() {
        return rngWebSearchUse__;
    }
    /**
     * <p>rngWebSearchUse をセットします。
     * @param rngWebSearchUse rngWebSearchUse
     */
    public void setRngWebSearchUse(int rngWebSearchUse) {
        rngWebSearchUse__ = rngWebSearchUse;
    }
}