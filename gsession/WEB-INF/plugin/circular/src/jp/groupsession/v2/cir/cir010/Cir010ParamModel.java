package jp.groupsession.v2.cir.cir010;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.model.AccountDataModel;
import jp.groupsession.v2.cir.model.CircularDspModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.AbstractParamModel;
import jp.groupsession.v2.man.GSConstMain;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 回覧板一覧画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir010ParamModel extends AbstractParamModel {

    /** 表示アカウント */
    private int cirViewAccount__ = 0;
    /** 表示アカウント 名*/
    private String cirViewAccountName__;
    /** アカウント管理モード */
    private int cirAccountMode__ = 0;
    /** アカウントSID */
    private int cirAccountSid__ = 0;
    /** 編集モード */
    private int cirCmdMode__ = GSConstCircular.CMDMODE_ADD;

    /** アカウント一覧 */
    private List<AccountDataModel> cir010AccountList__;
    /** アカウントテーマ */
    private int cir010AccountTheme__ = GSConstCircular.CAC_THEME_NOSET;

    //入力項目
    /** ページ1 */
    private int cir010pageNum1__;
    /** ページ2 */
    private int cir010pageNum2__;
    /** 削除回覧板SID */
    private String[] cir010delInfSid__;
    /** 検索キーワード */
    private String cir010searchWord__;

    //表示項目
    /** 回覧板リスト */
    private List<CircularDspModel> cir010CircularList__ = null;
    /** ページラベル */
    ArrayList < LabelValueBean > cir010PageLabel__;

    //非表示項目
    /** 処理モード */
    private String cir010cmdMode__ = GSConstCircular.MODE_JUSIN;
    /** オーダーキー */
    private int cir010orderKey__ = GSConst.ORDER_KEY_DESC;
    /** ソートキー */
    private int cir010sortKey__ = GSConstCircular.SORT_DATE;
    /** saveリスト(現在ページ以外でチェックされている値) */
    ArrayList < String > cir010saveList__;
    /** 選択した回覧板SID */
    private String cir010selectInfSid__ = null;
    /** 選択した回覧板の送信受信区分 */
    private String cir010sojuKbn__ = GSConstCircular.MODE_JUSIN;
    /** 自動リロード時間 */
    private int cir010Reload__ = GSConstCircular.AUTO_RELOAD_10MIN;

    //その他パラメータ
    /** 未確認カウント */
    private int mikakkuCount__;
    /** 回覧板 登録モード(新規作成 or 複写して新規作成) */
    private int cirEntryMode__ = GSConstCircular.CIR_ENTRYMODE_NEW;
    /** 編集する回覧板SID */
    private String cirEditInfSid__ = null;

    //管理者権限
    /** 管理者権限有無 */
    private int adminFlg__ = GSConst.USER_NOT_ADMIN;

    /** 遷移元 メイン個人メニュー:2 メイン管理者メニュー:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;

    /** 管理者ユーザフラグ */
    private boolean cir010adminUser__ = false;

    /**
     * <p>adminFlg を取得します。
     * @return adminFlg
     */
    public int getAdminFlg() {
        return adminFlg__;
    }

    /**
     * <p>adminFlg をセットします。
     * @param adminFlg adminFlg
     */
    public void setAdminFlg(int adminFlg) {
        adminFlg__ = adminFlg;
    }
    /**
     * <p>cir010CircularList を取得します。
     * @return cir010CircularList
     */
    public List<CircularDspModel> getCir010CircularList() {
        return cir010CircularList__;
    }
    /**
     * <p>cir010CircularList をセットします。
     * @param cir010CircularList cir010CircularList
     */
    public void setCir010CircularList(List<CircularDspModel> cir010CircularList) {
        cir010CircularList__ = cir010CircularList;
    }
    /**
     * <p>cir010cmdMode を取得します。
     * @return cir010cmdMode
     */
    public String getCir010cmdMode() {
        return cir010cmdMode__;
    }
    /**
     * <p>cir010cmdMode をセットします。
     * @param cir010cmdMode cir010cmdMode
     */
    public void setCir010cmdMode(String cir010cmdMode) {
        cir010cmdMode__ = cir010cmdMode;
    }
    /**
     * <p>cir010orderKey を取得します。
     * @return cir010orderKey
     */
    public int getCir010orderKey() {
        return cir010orderKey__;
    }
    /**
     * <p>cir010orderKey をセットします。
     * @param cir010orderKey cir010orderKey
     */
    public void setCir010orderKey(int cir010orderKey) {
        cir010orderKey__ = cir010orderKey;
    }
    /**
     * <p>cir010PageLabel を取得します。
     * @return cir010PageLabel
     */
    public ArrayList < LabelValueBean > getCir010PageLabel() {
        return cir010PageLabel__;
    }
    /**
     * <p>cir010PageLabel をセットします。
     * @param cir010PageLabel cir010PageLabel
     */
    public void setCir010PageLabel(ArrayList < LabelValueBean > cir010PageLabel) {
        cir010PageLabel__ = cir010PageLabel;
    }
    /**
     * <p>cir010sortKey を取得します。
     * @return cir010sortKey
     */
    public int getCir010sortKey() {
        return cir010sortKey__;
    }
    /**
     * <p>cir010sortKey をセットします。
     * @param cir010sortKey cir010sortKey
     */
    public void setCir010sortKey(int cir010sortKey) {
        cir010sortKey__ = cir010sortKey;
    }
    /**
     * <p>cir010delInfSid を取得します。
     * @return cir010delInfSid
     */
    public String[] getCir010delInfSid() {
        return cir010delInfSid__;
    }
    /**
     * <p>cir010delInfSid をセットします。
     * @param cir010delInfSid cir010delInfSid
     */
    public void setCir010delInfSid(String[] cir010delInfSid) {
        cir010delInfSid__ = cir010delInfSid;
    }
    /**
     * <p>cir010saveList を取得します。
     * @return cir010saveList
     */
    public ArrayList < String > getCir010saveList() {
        return cir010saveList__;
    }
    /**
     * <p>cir010saveList をセットします。
     * @param cir010saveList cir010saveList
     */
    public void setCir010saveList(ArrayList < String > cir010saveList) {
        cir010saveList__ = cir010saveList;
    }

    /**
     * <p>cir010pageNum1 を取得します。
     * @return cir010pageNum1
     */
    public int getCir010pageNum1() {
        return cir010pageNum1__;
    }

    /**
     * <p>cir010pageNum1 をセットします。
     * @param cir010pageNum1 cir010pageNum1
     */
    public void setCir010pageNum1(int cir010pageNum1) {
        cir010pageNum1__ = cir010pageNum1;
    }

    /**
     * <p>cir010pageNum2 を取得します。
     * @return cir010pageNum2
     */
    public int getCir010pageNum2() {
        return cir010pageNum2__;
    }

    /**
     * <p>cir010pageNum2 をセットします。
     * @param cir010pageNum2 cir010pageNum2
     */
    public void setCir010pageNum2(int cir010pageNum2) {
        cir010pageNum2__ = cir010pageNum2;
    }

    /**
     * <p>cir010selectInfSid を取得します。
     * @return cir010selectInfSid
     */
    public String getCir010selectInfSid() {
        return cir010selectInfSid__;
    }

    /**
     * <p>cir010selectInfSid をセットします。
     * @param cir010selectInfSid cir010selectInfSid
     */
    public void setCir010selectInfSid(String cir010selectInfSid) {
        cir010selectInfSid__ = cir010selectInfSid;
    }

    /**
     * <p>mikakkuCount を取得します。
     * @return mikakkuCount
     */
    public int getMikakkuCount() {
        return mikakkuCount__;
    }

    /**
     * <p>mikakkuCount をセットします。
     * @param mikakkuCount mikakkuCount
     */
    public void setMikakkuCount(int mikakkuCount) {
        mikakkuCount__ = mikakkuCount;
    }

    /**
     * <p>cir010sojuKbn を取得します。
     * @return cir010sojuKbn
     */
    public String getCir010sojuKbn() {
        return cir010sojuKbn__;
    }

    /**
     * <p>cir010sojuKbn をセットします。
     * @param cir010sojuKbn cir010sojuKbn
     */
    public void setCir010sojuKbn(String cir010sojuKbn) {
        cir010sojuKbn__ = cir010sojuKbn;
    }

    /**
     * <p>cir010searchWord を取得します。
     * @return cir010searchWord
     */
    public String getCir010searchWord() {
        return cir010searchWord__;
    }

    /**
     * <p>cir010searchWord をセットします。
     * @param cir010searchWord cir010searchWord
     */
    public void setCir010searchWord(String cir010searchWord) {
        cir010searchWord__ = cir010searchWord;
    }

    /**
     * <p>cir010Reload を取得します。
     * @return cir010Reload
     */
    public int getCir010Reload() {
        return cir010Reload__;
    }

    /**
     * <p>cir010Reload をセットします。
     * @param cir010Reload cir010Reload
     */
    public void setCir010Reload(int cir010Reload) {
        cir010Reload__ = cir010Reload;
    }

    /**
     * <p>backScreen を取得します。
     * @return backScreen
     */
    public int getBackScreen() {
        return backScreen__;
    }

    /**
     * <p>backScreen をセットします。
     * @param backScreen backScreen
     */
    public void setBackScreen(int backScreen) {
        backScreen__ = backScreen;
    }

    /**
     * <p>cirEntryMode を取得します。
     * @return cirEntryMode
     */
    public int getCirEntryMode() {
        return cirEntryMode__;
    }

    /**
     * <p>cirEntryMode をセットします。
     * @param cirEntryMode cirEntryMode
     */
    public void setCirEntryMode(int cirEntryMode) {
        cirEntryMode__ = cirEntryMode;
    }

    /**
     * <p>cirViewAccount を取得します。
     * @return cirViewAccount
     */
    public int getCirViewAccount() {
        return cirViewAccount__;
    }

    /**
     * <p>cirViewAccount をセットします。
     * @param cirViewAccount cirViewAccount
     */
    public void setCirViewAccount(int cirViewAccount) {
        cirViewAccount__ = cirViewAccount;
    }

    /**
     * <p>cirAccountMode を取得します。
     * @return cirAccountMode
     */
    public int getCirAccountMode() {
        return cirAccountMode__;
    }

    /**
     * <p>cirAccountMode をセットします。
     * @param cirAccountMode cirAccountMode
     */
    public void setCirAccountMode(int cirAccountMode) {
        cirAccountMode__ = cirAccountMode;
    }

    /**
     * <p>cirAccountSid を取得します。
     * @return cirAccountSid
     */
    public int getCirAccountSid() {
        return cirAccountSid__;
    }

    /**
     * <p>cirAccountSid をセットします。
     * @param cirAccountSid cirAccountSid
     */
    public void setCirAccountSid(int cirAccountSid) {
        cirAccountSid__ = cirAccountSid;
    }

    /**
     * <p>cirCmdMode を取得します。
     * @return cirCmdMode
     */
    public int getCirCmdMode() {
        return cirCmdMode__;
    }

    /**
     * <p>cirCmdMode をセットします。
     * @param cirCmdMode cirCmdMode
     */
    public void setCirCmdMode(int cirCmdMode) {
        cirCmdMode__ = cirCmdMode;
    }

    /**
     * <p>cir010adminUser を取得します。
     * @return cir010adminUser
     */
    public boolean isCir010adminUser() {
        return cir010adminUser__;
    }

    /**
     * <p>cir010adminUser をセットします。
     * @param cir010adminUser cir010adminUser
     */
    public void setCir010adminUser(boolean cir010adminUser) {
        cir010adminUser__ = cir010adminUser;
    }

    /**
     * <p>cir010AccountList を取得します。
     * @return cir010AccountList
     */
    public List<AccountDataModel> getCir010AccountList() {
        return cir010AccountList__;
    }

    /**
     * <p>cir010AccountList をセットします。
     * @param cir010AccountList cir010AccountList
     */
    public void setCir010AccountList(List<AccountDataModel> cir010AccountList) {
        cir010AccountList__ = cir010AccountList;
    }

    /**
     * <p>cir010AccountTheme を取得します。
     * @return cir010AccountTheme
     */
    public int getCir010AccountTheme() {
        return cir010AccountTheme__;
    }

    /**
     * <p>cir010AccountTheme をセットします。
     * @param cir010AccountTheme cir010AccountTheme
     */
    public void setCir010AccountTheme(int cir010AccountTheme) {
        cir010AccountTheme__ = cir010AccountTheme;
    }

    /**
     * <p>cirViewAccountName を取得します。
     * @return cirViewAccountName
     */
    public String getCirViewAccountName() {
        return cirViewAccountName__;
    }

    /**
     * <p>cirViewAccountName をセットします。
     * @param cirViewAccountName cirViewAccountName
     */
    public void setCirViewAccountName(String cirViewAccountName) {
        cirViewAccountName__ = cirViewAccountName;
    }

    /**
     * <p>cirEditInfSid を取得します。
     * @return cirEditInfSid
     */
    public String getCirEditInfSid() {
        return cirEditInfSid__;
    }

    /**
     * <p>cirEditInfSid をセットします。
     * @param cirEditInfSid cirEditInfSid
     */
    public void setCirEditInfSid(String cirEditInfSid) {
        cirEditInfSid__ = cirEditInfSid;
    }
}