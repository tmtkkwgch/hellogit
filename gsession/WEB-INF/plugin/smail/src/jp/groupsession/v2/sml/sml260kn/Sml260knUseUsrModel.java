package jp.groupsession.v2.sml.sml260kn;

import java.util.List;

import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;

/**
 * <br>[機  能] ショートメール アカウントの使用者に関する情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml260knUseUsrModel {

    /** アカウント名 */
    private String accountName__ = null;
    /** ユーザ名 */
    private List<CmnUsrmInfModel> userNameList__ = null;

    /**
     * <p>accountName を取得します。
     * @return accountName
     */
    public String getAccountName() {
        return accountName__;
    }

    /**
     * <p>accountName をセットします。
     * @param accountName accountName
     */
    public void setAccountName(String accountName) {
        accountName__ = accountName;
    }

    /**
     * <p>userNameList を取得します。
     * @return userNameList
     */
    public List<CmnUsrmInfModel> getUserNameList() {
        return userNameList__;
    }

    /**
     * <p>userNameList をセットします。
     * @param userNameList userNameList
     */
    public void setUserNameList(List<CmnUsrmInfModel> userNameList) {
        userNameList__ = userNameList;
    }
}
