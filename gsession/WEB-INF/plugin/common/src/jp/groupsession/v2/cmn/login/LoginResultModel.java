package jp.groupsession.v2.cmn.login;

import jp.groupsession.v2.cmn.model.AbstractModel;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;

/**
 * <br>[機  能] ログイン処理の結果に関する情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class LoginResultModel extends AbstractModel {

    /** ログイン処理の結果 true:成功 false:失敗 */
    private boolean result__ = false;
    /** エラー */
    private ActionErrors errors__ = null;
    /** アクションフォワード */
    private ActionForward forward__ = null;
    /** LDAP false:利用しない true:利用する */
    private boolean ldapFlg__ = false;
    /**
     * <p>errors を取得します。
     * @return errors
     */
    public ActionErrors getErrors() {
        return errors__;
    }
    /**
     * <p>errors をセットします。
     * @param errors errors
     */
    public void setErrors(ActionErrors errors) {
        errors__ = errors;
    }
    /**
     * <p>result を取得します。
     * @return result
     */
    public boolean isResult() {
        return result__;
    }
    /**
     * <p>result をセットします。
     * @param result result
     */
    public void setResult(boolean result) {
        result__ = result;
    }
    /**
     * <p>forward を取得します。
     * @return forward
     */
    public ActionForward getForward() {
        return forward__;
    }
    /**
     * <p>forward をセットします。
     * @param forward forward
     */
    public void setForward(ActionForward forward) {
        forward__ = forward;
    }
    /**
     * <p>ldapFlg を取得します。
     * @return ldapFlg
     */
    public boolean isLdapFlg() {
        return ldapFlg__;
    }
    /**
     * <p>ldapFlg をセットします。
     * @param ldapFlg ldapFlg
     */
    public void setLdapFlg(boolean ldapFlg) {
        ldapFlg__ = ldapFlg;
    }

}
