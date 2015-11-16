package jp.groupsession.v2.cir.cir160;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.GSValidateCircular;
import jp.groupsession.v2.cir.cir150.Cir150Form;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] 回覧板 アカウント登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir160Form extends Cir150Form {

    /** グループSID: グループ一覧 */
    public static final int GRP_SID_GRPLIST = -9;
    /** 使用者 グループを指定 */
    public static final int USERKBN_GROUP = 0;
    /** 使用者 ユーザを指定 */
    public static final int USERKBN_USER = 1;


    /** 選択タブ  */
    private int cir160SelTab__ = GSConstCircular.SEL_TAB_NORMAL;

    /** アカウント区分  */
    private int cir160AccountKbn__ = GSConstCircular.ACNT_MAKE_ACNT;

    /** アカウント名 */
    private String cir160name__ = null;

    /** デフォルトアカウント ユーザSID  */
    private int cir160DefActUsrSid__ = 0;

    /** 削除可能フラグ  */
    private int cir160CanDelFlg__ = GSConstCircular.ACCOUNT_DELETE_OK;

    /** 備考 */
    private String cir160biko__ = null;

    /** 使用者 ユーザ */
    private String[] cir160userKbnUser__ = null;

    /** 初期表示フラグ */
    private int cir160initFlg__ = 0;

    /** 編集グループコンボ */
    private List<LabelValueBean> userKbnGroupCombo__ = null;

    /** 使用者 設定フラグ */
    private boolean cir160acntUserFlg__ = false;
    /** 使用者 ユーザ(選択用) */
    private String[] cir160userKbnUserSelect__  = null;
    /** 使用者 ユーザ(未選択 選択用) */
    private String[] cir160userKbnUserNoSelect__ = null;


    /** 使用者 ユーザ グループ */
    private String cir160userKbnUserGroup__ = String.valueOf(GRP_SID_GRPLIST);
    /** 使用者 ユーザ(選択用) コンボ */
    private List<LabelValueBean> userKbnUserSelectCombo__  = null;
    /** 使用者 ユーザ(未選択 選択用) コンボ */
    private List<LabelValueBean> userKbnUserNoSelectCombo__  = null;

    //--------- 初期値設定
    /** メモ欄修正区分 */
    private int cir160memoKbn__ = 0;
    /** メモ欄修正期限(期間指定) */
    private int cir160memoPeriod__ = 0;
    /**回覧先 公開／非公開 */
    private int cir160show__ = 0;

    //--------- アカウントマネージャー画面のパラメータ
    /** 検索キーワード */
    private String cir150keyword__ = null;
    /** グループ */
    private int cir150group__ = -1;
    /** ユーザ */
    private int cir150user__ = -1;
    /** ページ上段 */
    private int cir150pageTop__ = 0;
    /** ページ下段 */
    private int cir150pageBottom__ = 0;

    /** 検索キーワード(検索条件保持) */
    private String cir150svKeyword__ = null;
    /** グループ(検索条件保持) */
    private int cir150svGroup__ = -1;
    /** ユーザ(検索条件保持) */
    private int cir150svUser__ = -1;

    /** ソートキー */
    private int cir150sortKey__ = GSConstCircular.SKEY_ACCOUNTNAME;
    /** 並び順 */
    private int cir150order__ = GSConstCircular.ORDER_ASC;

    /** 検索実行フラグ */
    private int cir150searchFlg__ = 0;

    /** 表示判定 */
    private int cir160elementKbn__ = 0;


    /** 自動削除区分 */
    private int cir160autoDelKbn__ = GSConstCircular.AUTO_DEL_ADM;
    /**初期値設定区分 */
    private int cir160cirInitKbn__ = GSConstCircular.CIR_INIEDIT_STYPE_ADM;

    /** 受信タブ 処理区分 */
    private String cir160JdelKbn__ = null;
    /** 送信タブ 処理区分 */
    private String cir160SdelKbn__ = null;
    /** ゴミ箱タブ 処理区分 */
    private String cir160DdelKbn__ = null;

    /** 年リスト */
    private ArrayList<LabelValueBean> cir160YearLabelList__ = null;
    /** 月リスト */
    private ArrayList<LabelValueBean> cir160MonthLabelList__ = null;
    /** 受信タブ 年選択 */
    private String cir160JYear__ = null;
    /** 受信タブ 月選択 */
    private String cir160JMonth__ = null;
    /** 送信タブ 年選択 */
    private String cir160SYear__ = null;
    /** 送信タブ 月選択 */
    private String cir160SMonth__ = null;
    /** ゴミ箱タブ 年選択 */
    private String cir160DYear__ = null;
    /** ゴミ箱タブ 月選択 */
    private String cir160DMonth__ = null;


    /** テーマ */
    private int cir160theme__ = 0;
    /** テーマ 一覧 */
    private List<LabelValueBean> cir160themeList__ = null;

    /** ショートメール通知 */
    private int cir160smlNtf__ = GSConstCircular.SMAIL_TSUUCHI;
    /** ショートメール通知区分 */
    private int cir160SmlNtfKbn__ = GSConstCircular.CAF_SML_NTF_USER;

    //---------
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param con コネクション
     * @throws Exception  実行例外
     * @return エラー
     */
    public ActionErrors validateCheck250(HttpServletRequest req,
                                      Connection con) throws Exception {
        ActionErrors errors = new ActionErrors();

        //アカウント名入力チェック
        GSValidateCircular.validateTextBoxInput(errors, cir160name__,
                "cir160name",
                getInterMessage(req, GSConstCircular.TEXT_ACCOUNT),
                GSConstCircular.MAXLEN_ACCOUNT, true);


        //備考入力チェック
        GSValidateCircular.validateTextarea(errors, cir160biko__,
                "cir160biko",
                getInterMessage(req, GSConstCircular.TEXT_BIKO),
                GSConstCircular.MAXLEN_ACCOUNT_BIKO,
                false);

        Cir160ParamModel paramMdl = new Cir160ParamModel();
        paramMdl.setParam(this);
        Cir160Biz biz = new Cir160Biz();
        boolean acntUserFlg = biz.getAcntUserFlg(con, paramMdl, getCirAccountSid(), null);

//        if (isCir010adminUser()) {
        if (acntUserFlg) {

//            //使用者 グループ 設定チェック
//            if (cir160userKbn__ == GSConstCircular.USRKBN_GROUP) {
//                if (cir160userKbnGroup__ == null || cir160userKbnGroup__.length == 0) {
//                    String fieldfix = "group.";
//                        String msgKey = "error.select.required.text";
//                        ActionMessage msg = new ActionMessage(msgKey,
//                                getInterMessage(req, "cmn.employer"));
//                        StrutsUtil.addMessage(
//                                errors, msg, fieldfix + msgKey);
//
//                }
//
//            //使用者 ユーザ 設定チェック
//            } else if (cir160userKbn__ == GSConstCircular.USRKBN_USER) {
                if (cir160userKbnUser__ == null || cir160userKbnUser__.length == 0) {
                    String fieldfix = "user.";
                    String msgKey = "error.select.required.text";
                    ActionMessage msg = new ActionMessage(msgKey,
                                                        getInterMessage(req, "cmn.employer"));
                    StrutsUtil.addMessage(
                            errors, msg, fieldfix + msgKey);
                }
//            }
        }

        return errors;
    }

    /**
     * <p>cir160biko を取得します。
     * @return cir160biko
     */
    public String getCir160biko() {
        return cir160biko__;
    }
    /**
     * <p>cir160biko をセットします。
     * @param cir160biko cir160biko
     */
    public void setCir160biko(String cir160biko) {
        cir160biko__ = cir160biko;
    }

    /**
     * <p>cir160name を取得します。
     * @return cir160name
     */
    public String getCir160name() {
        return cir160name__;
    }
    /**
     * <p>cir160name をセットします。
     * @param cir160name cir160name
     */
    public void setCir160name(String cir160name) {
        cir160name__ = cir160name;
    }

//    /**
//     * <p>cir160userKbn を取得します。
//     * @return cir160userKbn
//     */
//    public int getCir160userKbn() {
//        return cir160userKbn__;
//    }
//    /**
//     * <p>cir160userKbn をセットします。
//     * @param cir160userKbn cir160userKbn
//     */
//    public void setCir160userKbn(int cir160userKbn) {
//        cir160userKbn__ = cir160userKbn;
//    }
//    /**
//     * <p>cir160userKbnGroup を取得します。
//     * @return cir160userKbnGroup
//     */
//    public String[] getCir160userKbnGroup() {
//        return cir160userKbnGroup__;
//    }
//    /**
//     * <p>cir160userKbnGroup をセットします。
//     * @param cir160userKbnGroup cir160userKbnGroup
//     */
//    public void setCir160userKbnGroup(String[] cir160userKbnGroup) {
//        cir160userKbnGroup__ = cir160userKbnGroup;
//    }
    /**
     * <p>cir160userKbnUser を取得します。
     * @return cir160userKbnUser
     */
    public String[] getCir160userKbnUser() {
        return cir160userKbnUser__;
    }
    /**
     * <p>cir160userKbnUser をセットします。
     * @param cir160userKbnUser cir160userKbnUser
     */
    public void setCir160userKbnUser(String[] cir160userKbnUser) {
        cir160userKbnUser__ = cir160userKbnUser;
    }

    /**
     * <p>userKbnGroupCombo を取得します。
     * @return userKbnGroupCombo
     */
    public List<LabelValueBean> getUserKbnGroupCombo() {
        return userKbnGroupCombo__;
    }
    /**
     * <p>userKbnGroupCombo をセットします。
     * @param userKbnGroupCombo userKbnGroupCombo
     */
    public void setUserKbnGroupCombo(List<LabelValueBean> userKbnGroupCombo) {
        userKbnGroupCombo__ = userKbnGroupCombo;
    }
//    /**
//     * <p>cir160userKbnGroupNoSelect を取得します。
//     * @return cir160userKbnGroupNoSelect
//     */
//    public String[] getCir160userKbnGroupNoSelect() {
//        return cir160userKbnGroupNoSelect__;
//    }
//    /**
//     * <p>cir160userKbnGroupNoSelect をセットします。
//     * @param cir160userKbnGroupNoSelect cir160userKbnGroupNoSelect
//     */
//    public void setCir160userKbnGroupNoSelect(String[] cir160userKbnGroupNoSelect) {
//        cir160userKbnGroupNoSelect__ = cir160userKbnGroupNoSelect;
//    }
//    /**
//     * <p>cir160userKbnGroupSelect を取得します。
//     * @return cir160userKbnGroupSelect
//     */
//    public String[] getCir160userKbnGroupSelect() {
//        return cir160userKbnGroupSelect__;
//    }
//    /**
//     * <p>cir160userKbnGroupSelect をセットします。
//     * @param cir160userKbnGroupSelect cir160userKbnGroupSelect
//     */
//    public void setCir160userKbnGroupSelect(String[] cir160userKbnGroupSelect) {
//        cir160userKbnGroupSelect__ = cir160userKbnGroupSelect;
//    }
    /**
     * <p>cir160userKbnUserNoSelect を取得します。
     * @return cir160userKbnUserNoSelect
     */
    public String[] getCir160userKbnUserNoSelect() {
        return cir160userKbnUserNoSelect__;
    }
    /**
     * <p>cir160userKbnUserNoSelect をセットします。
     * @param cir160userKbnUserNoSelect cir160userKbnUserNoSelect
     */
    public void setCir160userKbnUserNoSelect(String[] cir160userKbnUserNoSelect) {
        cir160userKbnUserNoSelect__ = cir160userKbnUserNoSelect;
    }
    /**
     * <p>cir160userKbnUserSelect を取得します。
     * @return cir160userKbnUserSelect
     */
    public String[] getCir160userKbnUserSelect() {
        return cir160userKbnUserSelect__;
    }
    /**
     * <p>cir160userKbnUserSelect をセットします。
     * @param cir160userKbnUserSelect cir160userKbnUserSelect
     */
    public void setCir160userKbnUserSelect(String[] cir160userKbnUserSelect) {
        cir160userKbnUserSelect__ = cir160userKbnUserSelect;
    }

    /**
     * <p>cir160initFlg を取得します。
     * @return cir160initFlg
     */
    public int getCir160initFlg() {
        return cir160initFlg__;
    }
    /**
     * <p>cir160initFlg をセットします。
     * @param cir160initFlg cir160initFlg
     */
    public void setCir160initFlg(int cir160initFlg) {
        cir160initFlg__ = cir160initFlg;
    }
//    /**
//     * <p>userKbnGroupNoSelectCombo を取得します。
//     * @return userKbnGroupNoSelectCombo
//     */
//    public List<LabelValueBean> getUserKbnGroupNoSelectCombo() {
//        return userKbnGroupNoSelectCombo__;
//    }
//    /**
//     * <p>userKbnGroupNoSelectCombo をセットします。
//     * @param userKbnGroupNoSelectCombo userKbnGroupNoSelectCombo
//     */
//    public void setUserKbnGroupNoSelectCombo(
//            List<LabelValueBean> userKbnGroupNoSelectCombo) {
//        userKbnGroupNoSelectCombo__ = userKbnGroupNoSelectCombo;
//    }
//    /**
//     * <p>userKbnGroupSelectCombo を取得します。
//     * @return userKbnGroupSelectCombo
//     */
//    public List<LabelValueBean> getUserKbnGroupSelectCombo() {
//        return userKbnGroupSelectCombo__;
//    }
//    /**
//     * <p>userKbnGroupSelectCombo をセットします。
//     * @param userKbnGroupSelectCombo userKbnGroupSelectCombo
//     */
//    public void setUserKbnGroupSelectCombo(
//            List<LabelValueBean> userKbnGroupSelectCombo) {
//        userKbnGroupSelectCombo__ = userKbnGroupSelectCombo;
//    }
    /**
     * <p>userKbnUserNoSelectCombo を取得します。
     * @return userKbnUserNoSelectCombo
     */
    public List<LabelValueBean> getUserKbnUserNoSelectCombo() {
        return userKbnUserNoSelectCombo__;
    }
    /**
     * <p>userKbnUserNoSelectCombo をセットします。
     * @param userKbnUserNoSelectCombo userKbnUserNoSelectCombo
     */
    public void setUserKbnUserNoSelectCombo(
            List<LabelValueBean> userKbnUserNoSelectCombo) {
        userKbnUserNoSelectCombo__ = userKbnUserNoSelectCombo;
    }
    /**
     * <p>userKbnUserSelectCombo を取得します。
     * @return userKbnUserSelectCombo
     */
    public List<LabelValueBean> getUserKbnUserSelectCombo() {
        return userKbnUserSelectCombo__;
    }
    /**
     * <p>userKbnUserSelectCombo をセットします。
     * @param userKbnUserSelectCombo userKbnUserSelectCombo
     */
    public void setUserKbnUserSelectCombo(
            List<LabelValueBean> userKbnUserSelectCombo) {
        userKbnUserSelectCombo__ = userKbnUserSelectCombo;
    }
    /**
     * <p>cir160userKbnUserGroup を取得します。
     * @return cir160userKbnUserGroup
     */
    public String getCir160userKbnUserGroup() {
        return cir160userKbnUserGroup__;
    }
    /**
     * <p>cir160userKbnUserGroup をセットします。
     * @param cir160userKbnUserGroup cir160userKbnUserGroup
     */
    public void setCir160userKbnUserGroup(String cir160userKbnUserGroup) {
        cir160userKbnUserGroup__ = cir160userKbnUserGroup;
    }
    /**
     * <p>wml030group を取得します。
     * @return cir150group
     */
    public int getSml030group() {
        return cir150group__;
    }
    /**
     * <p>wml030group をセットします。
     * @param cir150group cir150group
     */
    public void setSml030group(int cir150group) {
        cir150group__ = cir150group;
    }
    /**
     * <p>wml030keyword を取得します。
     * @return cir150keyword
     */
    public String getSml030keyword() {
        return cir150keyword__;
    }
    /**
     * <p>wml030keyword をセットします。
     * @param cir150keyword cir150keyword
     */
    public void setSml030keyword(String cir150keyword) {
        cir150keyword__ = cir150keyword;
    }
    /**
     * <p>wml030order を取得します。
     * @return cir150order
     */
    public int getSml030order() {
        return cir150order__;
    }
    /**
     * <p>wml030order をセットします。
     * @param cir150order cir150order
     */
    public void setSml030order(int cir150order) {
        cir150order__ = cir150order;
    }
    /**
     * <p>wml030pageBottom を取得します。
     * @return cir150pageBottom
     */
    public int getSml030pageBottom() {
        return cir150pageBottom__;
    }
    /**
     * <p>wml030pageBottom をセットします。
     * @param cir150pageBottom cir150pageBottom
     */
    public void setSml030pageBottom(int cir150pageBottom) {
        cir150pageBottom__ = cir150pageBottom;
    }
    /**
     * <p>wml030pageTop を取得します。
     * @return cir150pageTop
     */
    public int getSml030pageTop() {
        return cir150pageTop__;
    }
    /**
     * <p>wml030pageTop をセットします。
     * @param cir150pageTop cir150pageTop
     */
    public void setSml030pageTop(int cir150pageTop) {
        cir150pageTop__ = cir150pageTop;
    }
    /**
     * <p>wml030searchFlg を取得します。
     * @return cir150searchFlg
     */
    public int getSml030searchFlg() {
        return cir150searchFlg__;
    }
    /**
     * <p>wml030searchFlg をセットします。
     * @param cir150searchFlg cir150searchFlg
     */
    public void setSml030searchFlg(int cir150searchFlg) {
        cir150searchFlg__ = cir150searchFlg;
    }
    /**
     * <p>wml030sortKey を取得します。
     * @return cir150sortKey
     */
    public int getSml030sortKey() {
        return cir150sortKey__;
    }
    /**
     * <p>wml030sortKey をセットします。
     * @param cir150sortKey cir150sortKey
     */
    public void setSml030sortKey(int cir150sortKey) {
        cir150sortKey__ = cir150sortKey;
    }
    /**
     * <p>wml030svGroup を取得します。
     * @return cir150svGroup
     */
    public int getSml030svGroup() {
        return cir150svGroup__;
    }
    /**
     * <p>wml030svGroup をセットします。
     * @param cir150svGroup cir150svGroup
     */
    public void setSml030svGroup(int cir150svGroup) {
        cir150svGroup__ = cir150svGroup;
    }
    /**
     * <p>wml030svKeyword を取得します。
     * @return cir150svKeyword
     */
    public String getSml030svKeyword() {
        return cir150svKeyword__;
    }
    /**
     * <p>wml030svKeyword をセットします。
     * @param cir150svKeyword cir150svKeyword
     */
    public void setSml030svKeyword(String cir150svKeyword) {
        cir150svKeyword__ = cir150svKeyword;
    }
    /**
     * <p>wml030svUser を取得します。
     * @return cir150svUser
     */
    public int getSml030svUser() {
        return cir150svUser__;
    }
    /**
     * <p>wml030svUser をセットします。
     * @param cir150svUser cir150svUser
     */
    public void setSml030svUser(int cir150svUser) {
        cir150svUser__ = cir150svUser;
    }
    /**
     * <p>wml030user を取得します。
     * @return cir150user
     */
    public int getSml030user() {
        return cir150user__;
    }
    /**
     * <p>wml030user をセットします。
     * @param cir150user cir150user
     */
    public void setSml030user(int cir150user) {
        cir150user__ = cir150user;
    }

    /**
     * <br>[機  能] 共通メッセージ画面遷移時に保持するパラメータを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param msgForm 共通メッセージ画面Form
     */
    public void setHiddenParam(Cmn999Form msgForm) {

        super.setHiddenParam(msgForm);
        msgForm.addHiddenParam("cir010adminUser", String.valueOf(isCir010adminUser()));
        msgForm.addHiddenParam("cir160name", getCir160name());

        msgForm.addHiddenParam("cir160biko", getCir160biko());
//        msgForm.addHiddenParam("cir160userKbn", getCir160userKbn());
//        msgForm.addHiddenParam("cir160userKbnGroup", getCir160userKbnGroup());
        msgForm.addHiddenParam("cir160userKbnUser", getCir160userKbnUser());



        msgForm.addHiddenParam("cir160initFlg", getCir160initFlg());
//        msgForm.addHiddenParam("cir160userKbnGroupSelect", getCir160userKbnGroupSelect());
//        msgForm.addHiddenParam("cir160userKbnGroupNoSelect", getCir160userKbnGroupNoSelect());
        msgForm.addHiddenParam("cir160userKbnUserSelect", getCir160userKbnUserSelect());
        msgForm.addHiddenParam("cir160userKbnUserNoSelect", getCir160userKbnUserNoSelect());
        msgForm.addHiddenParam("cir160userKbnUserGroup", getCir160userKbnUserGroup());
        msgForm.addHiddenParam("cir160elementKbn", getCir160elementKbn());

        msgForm.addHiddenParam("backScreen", getBackScreen());

        if (isCir010adminUser()) {
            msgForm.addHiddenParam("cir150keyword", getCir150keyword());
            msgForm.addHiddenParam("cir150group", getCir150group());
            msgForm.addHiddenParam("cir150user", getCir150user());
            msgForm.addHiddenParam("cir150pageTop", getCir150pageTop());
            msgForm.addHiddenParam("cir150pageBottom", getCir150pageBottom());
            msgForm.addHiddenParam("cir150svKeyword", getCir150svKeyword());
            msgForm.addHiddenParam("cir150svGroup", getCir150svGroup());
            msgForm.addHiddenParam("cir150svUser", getCir150svUser());
            msgForm.addHiddenParam("cir150sortKey", getCir150sortKey());
            msgForm.addHiddenParam("cir150order", getCir150order());
            msgForm.addHiddenParam("cir150searchFlg", getCir150searchFlg());

        }
    }

    /**
     * <p>cir160elementKbn を取得します。
     * @return cir160elementKbn
     */
    public int getCir160elementKbn() {
        return cir160elementKbn__;
    }

    /**
     * <p>cir160elementKbn をセットします。
     * @param cir160elementKbn cir160elementKbn
     */
    public void setCir160elementKbn(int cir160elementKbn) {
        cir160elementKbn__ = cir160elementKbn;
    }

    /**
     * <p>cir160AccountKbn を取得します。
     * @return cir160AccountKbn
     */
    public int getCir160AccountKbn() {
        return cir160AccountKbn__;
    }

    /**
     * <p>cir160AccountKbn をセットします。
     * @param cir160AccountKbn cir160AccountKbn
     */
    public void setCir160AccountKbn(int cir160AccountKbn) {
        cir160AccountKbn__ = cir160AccountKbn;
    }

    /**
     * <p>cir160DefActUsrSid を取得します。
     * @return cir160DefActUsrSid
     */
    public int getCir160DefActUsrSid() {
        return cir160DefActUsrSid__;
    }

    /**
     * <p>cir160DefActUsrSid をセットします。
     * @param cir160DefActUsrSid cir160DefActUsrSid
     */
    public void setCir160DefActUsrSid(int cir160DefActUsrSid) {
        cir160DefActUsrSid__ = cir160DefActUsrSid;
    }

    /**
     * <p>cir160JdelKbn を取得します。
     * @return cir160JdelKbn
     */
    public String getCir160JdelKbn() {
        return cir160JdelKbn__;
    }

    /**
     * <p>cir160JdelKbn をセットします。
     * @param cir160JdelKbn cir160JdelKbn
     */
    public void setCir160JdelKbn(String cir160JdelKbn) {
        cir160JdelKbn__ = cir160JdelKbn;
    }

    /**
     * <p>cir160SdelKbn を取得します。
     * @return cir160SdelKbn
     */
    public String getCir160SdelKbn() {
        return cir160SdelKbn__;
    }

    /**
     * <p>cir160SdelKbn をセットします。
     * @param cir160SdelKbn cir160SdelKbn
     */
    public void setCir160SdelKbn(String cir160SdelKbn) {
        cir160SdelKbn__ = cir160SdelKbn;
    }

    /**
     * <p>cir160DdelKbn を取得します。
     * @return cir160DdelKbn
     */
    public String getCir160DdelKbn() {
        return cir160DdelKbn__;
    }

    /**
     * <p>cir160DdelKbn をセットします。
     * @param cir160DdelKbn cir160DdelKbn
     */
    public void setCir160DdelKbn(String cir160DdelKbn) {
        cir160DdelKbn__ = cir160DdelKbn;
    }

    /**
     * <p>cir160YearLabelList を取得します。
     * @return cir160YearLabelList
     */
    public ArrayList<LabelValueBean> getCir160YearLabelList() {
        return cir160YearLabelList__;
    }

    /**
     * <p>cir160YearLabelList をセットします。
     * @param cir160YearLabelList cir160YearLabelList
     */
    public void setCir160YearLabelList(ArrayList<LabelValueBean> cir160YearLabelList) {
        cir160YearLabelList__ = cir160YearLabelList;
    }

    /**
     * <p>cir160MonthLabelList を取得します。
     * @return cir160MonthLabelList
     */
    public ArrayList<LabelValueBean> getCir160MonthLabelList() {
        return cir160MonthLabelList__;
    }

    /**
     * <p>cir160MonthLabelList をセットします。
     * @param cir160MonthLabelList cir160MonthLabelList
     */
    public void setCir160MonthLabelList(
            ArrayList<LabelValueBean> cir160MonthLabelList) {
        cir160MonthLabelList__ = cir160MonthLabelList;
    }

    /**
     * <p>cir160JYear を取得します。
     * @return cir160JYear
     */
    public String getCir160JYear() {
        return cir160JYear__;
    }

    /**
     * <p>cir160JYear をセットします。
     * @param cir160jYear cir160JYear
     */
    public void setCir160JYear(String cir160jYear) {
        cir160JYear__ = cir160jYear;
    }

    /**
     * <p>cir160JMonth を取得します。
     * @return cir160JMonth
     */
    public String getCir160JMonth() {
        return cir160JMonth__;
    }

    /**
     * <p>cir160JMonth をセットします。
     * @param cir160jMonth cir160JMonth
     */
    public void setCir160JMonth(String cir160jMonth) {
        cir160JMonth__ = cir160jMonth;
    }

    /**
     * <p>cir160SYear を取得します。
     * @return cir160SYear
     */
    public String getCir160SYear() {
        return cir160SYear__;
    }

    /**
     * <p>cir160SYear をセットします。
     * @param cir160sYear cir160SYear
     */
    public void setCir160SYear(String cir160sYear) {
        cir160SYear__ = cir160sYear;
    }

    /**
     * <p>cir160SMonth を取得します。
     * @return cir160SMonth
     */
    public String getCir160SMonth() {
        return cir160SMonth__;
    }

    /**
     * <p>cir160SMonth をセットします。
     * @param cir160sMonth cir160SMonth
     */
    public void setCir160SMonth(String cir160sMonth) {
        cir160SMonth__ = cir160sMonth;
    }

    /**
     * <p>cir160DYear を取得します。
     * @return cir160DYear
     */
    public String getCir160DYear() {
        return cir160DYear__;
    }

    /**
     * <p>cir160DYear をセットします。
     * @param cir160dYear cir160DYear
     */
    public void setCir160DYear(String cir160dYear) {
        cir160DYear__ = cir160dYear;
    }

    /**
     * <p>cir160DMonth を取得します。
     * @return cir160DMonth
     */
    public String getCir160DMonth() {
        return cir160DMonth__;
    }

    /**
     * <p>cir160DMonth をセットします。
     * @param cir160dMonth cir160DMonth
     */
    public void setCir160DMonth(String cir160dMonth) {
        cir160DMonth__ = cir160dMonth;
    }

    /**
     * <p>cir160autoDelKbn を取得します。
     * @return cir160autoDelKbn
     */
    public int getCir160autoDelKbn() {
        return cir160autoDelKbn__;
    }

    /**
     * <p>cir160autoDelKbn をセットします。
     * @param cir160autoDelKbn cir160autoDelKbn
     */
    public void setCir160autoDelKbn(int cir160autoDelKbn) {
        cir160autoDelKbn__ = cir160autoDelKbn;
    }

    /**
     * <p>cir160SelTab を取得します。
     * @return cir160SelTab
     */
    public int getCir160SelTab() {
        return cir160SelTab__;
    }

    /**
     * <p>cir160SelTab をセットします。
     * @param cir160SelTab cir160SelTab
     */
    public void setCir160SelTab(int cir160SelTab) {
        cir160SelTab__ = cir160SelTab;
    }

    /**
     * <p>cir160CanDelFlg を取得します。
     * @return cir160CanDelFlg
     */
    public int getCir160CanDelFlg() {
        return cir160CanDelFlg__;
    }


    /**
     * <p>cir160CanDelFlg をセットします。
     * @param cir160CanDelFlg cir160CanDelFlg
     */
    public void setCir160CanDelFlg(int cir160CanDelFlg) {
        cir160CanDelFlg__ = cir160CanDelFlg;
    }

    /**
     * <p>cir160theme を取得します。
     * @return cir160theme
     */
    public int getCir160theme() {
        return cir160theme__;
    }

    /**
     * <p>cir160theme をセットします。
     * @param cir160theme cir160theme
     */
    public void setCir160theme(int cir160theme) {
        cir160theme__ = cir160theme;
    }

    /**
     * <p>cir160themeList を取得します。
     * @return cir160themeList
     */
    public List<LabelValueBean> getCir160themeList() {
        return cir160themeList__;
    }

    /**
     * <p>cir160themeList をセットします。
     * @param cir160themeList cir160themeList
     */
    public void setCir160themeList(List<LabelValueBean> cir160themeList) {
        cir160themeList__ = cir160themeList;
    }

    /**
     * <p>cir160cirInitKbn を取得します。
     * @return cir160cirInitKbn
     */
    public int getCir160cirInitKbn() {
        return cir160cirInitKbn__;
    }

    /**
     * <p>cir160cirInitKbn をセットします。
     * @param cir160cirInitKbn cir160cirInitKbn
     */
    public void setCir160cirInitKbn(int cir160cirInitKbn) {
        cir160cirInitKbn__ = cir160cirInitKbn;
    }

    /**
     * <p>cir160memoKbn を取得します。
     * @return cir160memoKbn
     */
    public int getCir160memoKbn() {
        return cir160memoKbn__;
    }

    /**
     * <p>cir160memoKbn をセットします。
     * @param cir160memoKbn cir160memoKbn
     */
    public void setCir160memoKbn(int cir160memoKbn) {
        cir160memoKbn__ = cir160memoKbn;
    }

    /**
     * <p>cir160memoPeriod を取得します。
     * @return cir160memoPeriod
     */
    public int getCir160memoPeriod() {
        return cir160memoPeriod__;
    }

    /**
     * <p>cir160memoPeriod をセットします。
     * @param cir160memoPeriod cir160memoPeriod
     */
    public void setCir160memoPeriod(int cir160memoPeriod) {
        cir160memoPeriod__ = cir160memoPeriod;
    }

    /**
     * <p>cir160show を取得します。
     * @return cir160show
     */
    public int getCir160show() {
        return cir160show__;
    }

    /**
     * <p>cir160show をセットします。
     * @param cir160show cir160show
     */
    public void setCir160show(int cir160show) {
        cir160show__ = cir160show;
    }

    /**
     * <p>cir160smlNtf を取得します。
     * @return cir160smlNtf
     */
    public int getCir160smlNtf() {
        return cir160smlNtf__;
    }

    /**
     * <p>cir160smlNtf をセットします。
     * @param cir160smlNtf cir160smlNtf
     */
    public void setCir160smlNtf(int cir160smlNtf) {
        cir160smlNtf__ = cir160smlNtf;
    }

    /**
     * <p>cir160SmlNtfKbn を取得します。
     * @return cir160SmlNtfKbn
     */
    public int getCir160SmlNtfKbn() {
        return cir160SmlNtfKbn__;
    }

    /**
     * <p>cir160SmlNtfKbn をセットします。
     * @param cir160SmlNtfKbn cir160SmlNtfKbn
     */
    public void setCir160SmlNtfKbn(int cir160SmlNtfKbn) {
        cir160SmlNtfKbn__ = cir160SmlNtfKbn;
    }

    /**
     * <p>cir160acntUserFlg を取得します。
     * @return cir160acntUserFlg
     */
    public boolean isCir160acntUserFlg() {
        return cir160acntUserFlg__;
    }

    /**
     * <p>cir160acntUserFlg をセットします。
     * @param cir160acntUserFlg cir160acntUserFlg
     */
    public void setCir160acntUserFlg(boolean cir160acntUserFlg) {
        cir160acntUserFlg__ = cir160acntUserFlg;
    }

}
