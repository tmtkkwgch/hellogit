package jp.groupsession.v2.wml.wml030;

import java.util.List;

import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.wml020.Wml020ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アカウントマネージャー画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml030ParamModel extends Wml020ParamModel {
    /** 検索キーワード */
    private String wml030keyword__ = null;
    /** グループ */
    private int wml030group__ = -1;
    /** ユーザ */
    private int wml030user__ = -1;
    /** ページ上段 */
    private int wml030pageTop__ = 0;
    /** ページ下段 */
    private int wml030pageBottom__ = 0;
    /** ページ表示フラグ */
    private boolean wml030pageDspFlg__ = false;

    /** 検索キーワード(検索条件保持) */
    private String wml030svKeyword__ = null;
    /** グループ(検索条件保持) */
    private int wml030svGroup__ = -1;
    /** ユーザ(検索条件保持) */
    private int wml030svUser__ = -1;

    /** ソートキー */
    private int wml030sortKey__ = GSConstWebmail.SKEY_ACCOUNTNAME;
    /** 並び順 */
    private int wml030order__ = GSConstWebmail.ORDER_ASC;

    /** 検索実行フラグ */
    private int wml030searchFlg__ = 0;

    /** 検索結果一覧 */
    private List<Wml030AccountModel> accountList__ = null;
    /** ページコンボ */
    private List<LabelValueBean> pageCombo__ = null;

    /** グループコンボ */
    private List<LabelValueBean> groupCombo__ = null;
    /** ユーザコンボ */
    private List<LabelValueBean> userCombo__ = null;

    /** 選択されたアカウント */
    private String[] wml030selectAcount__;
    /** メールテンプレート区分 */
    private int wmlMailTemplateKbn__ = 0;

    /**
     * <p>accountList を取得します。
     * @return accountList
     */
    public List<Wml030AccountModel> getAccountList() {
        return accountList__;
    }
    /**
     * <p>accountList をセットします。
     * @param accountList accountList
     */
    public void setAccountList(List<Wml030AccountModel> accountList) {
        accountList__ = accountList;
    }
    /**
     * <p>pageCombo を取得します。
     * @return pageCombo
     */
    public List<LabelValueBean> getPageCombo() {
        return pageCombo__;
    }
    /**
     * <p>pageCombo をセットします。
     * @param pageCombo pageCombo
     */
    public void setPageCombo(List<LabelValueBean> pageCombo) {
        pageCombo__ = pageCombo;
    }
    /**
     * <p>wml030group を取得します。
     * @return wml030group
     */
    public int getWml030group() {
        return wml030group__;
    }
    /**
     * <p>wml030group をセットします。
     * @param wml030group wml030group
     */
    public void setWml030group(int wml030group) {
        wml030group__ = wml030group;
    }
    /**
     * <p>wml030keyword を取得します。
     * @return wml030keyword
     */
    public String getWml030keyword() {
        return wml030keyword__;
    }
    /**
     * <p>wml030keyword をセットします。
     * @param wml030keyword wml030keyword
     */
    public void setWml030keyword(String wml030keyword) {
        wml030keyword__ = wml030keyword;
    }
    /**
     * <p>wml030order を取得します。
     * @return wml030order
     */
    public int getWml030order() {
        return wml030order__;
    }
    /**
     * <p>wml030order をセットします。
     * @param wml030order wml030order
     */
    public void setWml030order(int wml030order) {
        wml030order__ = wml030order;
    }
    /**
     * <p>wml030pageBottom を取得します。
     * @return wml030pageBottom
     */
    public int getWml030pageBottom() {
        return wml030pageBottom__;
    }
    /**
     * <p>wml030pageBottom をセットします。
     * @param wml030pageBottom wml030pageBottom
     */
    public void setWml030pageBottom(int wml030pageBottom) {
        wml030pageBottom__ = wml030pageBottom;
    }
    /**
     * <p>wml030pageTop を取得します。
     * @return wml030pageTop
     */
    public int getWml030pageTop() {
        return wml030pageTop__;
    }
    /**
     * <p>wml030pageTop をセットします。
     * @param wml030pageTop wml030pageTop
     */
    public void setWml030pageTop(int wml030pageTop) {
        wml030pageTop__ = wml030pageTop;
    }
    /**
     * <p>wml030sortKey を取得します。
     * @return wml030sortKey
     */
    public int getWml030sortKey() {
        return wml030sortKey__;
    }
    /**
     * <p>wml030sortKey をセットします。
     * @param wml030sortKey wml030sortKey
     */
    public void setWml030sortKey(int wml030sortKey) {
        wml030sortKey__ = wml030sortKey;
    }
    /**
     * <p>wml030user を取得します。
     * @return wml030user
     */
    public int getWml030user() {
        return wml030user__;
    }
    /**
     * <p>wml030user をセットします。
     * @param wml030user wml030user
     */
    public void setWml030user(int wml030user) {
        wml030user__ = wml030user;
    }
    /**
     * <p>wml030searchFlg を取得します。
     * @return wml030searchFlg
     */
    public int getWml030searchFlg() {
        return wml030searchFlg__;
    }
    /**
     * <p>wml030searchFlg をセットします。
     * @param wml030searchFlg wml030searchFlg
     */
    public void setWml030searchFlg(int wml030searchFlg) {
        wml030searchFlg__ = wml030searchFlg;
    }
    /**
     * <p>wml030svGroup を取得します。
     * @return wml030svGroup
     */
    public int getWml030svGroup() {
        return wml030svGroup__;
    }
    /**
     * <p>wml030svGroup をセットします。
     * @param wml030svGroup wml030svGroup
     */
    public void setWml030svGroup(int wml030svGroup) {
        wml030svGroup__ = wml030svGroup;
    }
    /**
     * <p>wml030svKeyword を取得します。
     * @return wml030svKeyword
     */
    public String getWml030svKeyword() {
        return wml030svKeyword__;
    }
    /**
     * <p>wml030svKeyword をセットします。
     * @param wml030svKeyword wml030svKeyword
     */
    public void setWml030svKeyword(String wml030svKeyword) {
        wml030svKeyword__ = wml030svKeyword;
    }
    /**
     * <p>wml030svUser を取得します。
     * @return wml030svUser
     */
    public int getWml030svUser() {
        return wml030svUser__;
    }
    /**
     * <p>wml030svUser をセットします。
     * @param wml030svUser wml030svUser
     */
    public void setWml030svUser(int wml030svUser) {
        wml030svUser__ = wml030svUser;
    }
    /**
     * <p>groupCombo を取得します。
     * @return groupCombo
     */
    public List<LabelValueBean> getGroupCombo() {
        return groupCombo__;
    }
    /**
     * <p>groupCombo をセットします。
     * @param groupCombo groupCombo
     */
    public void setGroupCombo(List<LabelValueBean> groupCombo) {
        groupCombo__ = groupCombo;
    }
    /**
     * <p>userCombo を取得します。
     * @return userCombo
     */
    public List<LabelValueBean> getUserCombo() {
        return userCombo__;
    }
    /**
     * <p>userCombo をセットします。
     * @param userCombo userCombo
     */
    public void setUserCombo(List<LabelValueBean> userCombo) {
        userCombo__ = userCombo;
    }

    /**
     * <p>wml030pageDspFlg を取得します。
     * @return wml030pageDspFlg
     */
    public boolean isWml030pageDspFlg() {
        return wml030pageDspFlg__;
    }

    /**
     * <p>wml030pageDspFlg をセットします。
     * @param wml030pageDspFlg wml030pageDspFlg
     */
    public void setWml030pageDspFlg(boolean wml030pageDspFlg) {
        wml030pageDspFlg__ = wml030pageDspFlg;
    }

    /**
     * <p>wml030selectAcount を取得します。
     * @return wml030selectAcount
     */
    public String[] getWml030selectAcount() {
        return wml030selectAcount__;
    }

    /**
     * <p>wml030selectAcount をセットします。
     * @param wml030selectAcount wml030selectAcount
     */
    public void setWml030selectAcount(String[] wml030selectAcount) {
        wml030selectAcount__ = wml030selectAcount;
    }
    /**
     * <p>wmlMailTemplateKbn を取得します。
     * @return wmlMailTemplateKbn
     */
    public int getWmlMailTemplateKbn() {
        return wmlMailTemplateKbn__;
    }
    /**
     * <p>wmlMailTemplateKbn をセットします。
     * @param wmlMailTemplateKbn wmlMailTemplateKbn
     */
    public void setWmlMailTemplateKbn(int wmlMailTemplateKbn) {
        wmlMailTemplateKbn__ = wmlMailTemplateKbn;
    }
}