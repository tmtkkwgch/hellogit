package jp.groupsession.v2.cmn.cmn001;

import jp.groupsession.v2.cmn.model.AbstractParamModel;

/**
 * <br>[機  能] ログイン画面のパラメータ、出力値を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn001ParamModel extends AbstractParamModel {
    /** ログイン制御フラグ ログインを許可する */
    public static final int LOGIN_CTRL_OK = 0;
    /** ログイン制御フラグ ログインを許可しない */
    public static final int LOGIN_CTRL_NG = 1;

    /** ログイン種別 通常 */
    public static final int LOGIN_TYPE_NORMAL = 0;
    /** ログイン種別 ログイン画面から */
    public static final int LOGIN_TYPE_SCREEN = 1;

    /** ユーザID */
    private String cmn001Userid__ = null;
    /** パスワード */
    private String cmn001Passwd__ = null;
    /** ログイン後BODYに表示するURL */
    private String url__ = null;
    /** ログイン制御フラグ 0=ログインを許可する 1=ログインを許可しない */
    private int cmn001LoginCtrl__ = LOGIN_CTRL_OK;
    /** バイナリSID */
    private Long cmn001BinSid__ = new Long(0);
    /** 企業情報 URL */
    private String cmn001Url__ = null;

    /** ログイン種別 */
    private int cmn001loginType__ = LOGIN_TYPE_NORMAL;
    /** 初回アクセス */
    private int cmn001initAccess__ = 0;

    /**
     * <p>cmn001BinSid を取得します。
     * @return cmn001BinSid
     */
    public Long getCmn001BinSid() {
        return cmn001BinSid__;
    }
    /**
     * <p>cmn001BinSid をセットします。
     * @param cmn001BinSid cmn001BinSid
     */
    public void setCmn001BinSid(Long cmn001BinSid) {
        cmn001BinSid__ = cmn001BinSid;
    }
    /**
     * @return cmn001Passwd を戻します。
     */
    public String getCmn001Passwd() {
        return cmn001Passwd__;
    }
    /**
     * @param cmn001Passwd 設定する cmn001Passwd。
     */
    public void setCmn001Passwd(String cmn001Passwd) {
        cmn001Passwd__ = cmn001Passwd;
    }
    /**
     * @return cmn001Userid を戻します。
     */
    public String getCmn001Userid() {
        return cmn001Userid__;
    }
    /**
     * @param cmn001Userid 設定する cmn001Userid。
     */
    public void setCmn001Userid(String cmn001Userid) {
        cmn001Userid__ = cmn001Userid;
    }
    /**
     * <p>cmn001LoginCtrl を取得します。
     * @return cmn001LoginCtrl
     */
    public int getCmn001LoginCtrl() {
        return cmn001LoginCtrl__;
    }
    /**
     * <p>cmn001LoginCtrl をセットします。
     * @param cmn001LoginCtrl cmn001LoginCtrl
     */
    public void setCmn001LoginCtrl(int cmn001LoginCtrl) {
        cmn001LoginCtrl__ = cmn001LoginCtrl;
    }

    /**
     * @return url を戻します。
     */
    public String getUrl() {
        return url__;
    }
    /**
     * @param url 設定する url。
     */
    public void setUrl(String url) {
        url__ = url;
    }
    /**
     * <p>cmn001Url を取得します。
     * @return cmn001Url
     */
    public String getCmn001Url() {
        return cmn001Url__;
    }
    /**
     * <p>cmn001Url をセットします。
     * @param cmn001Url cmn001Url
     */
    public void setCmn001Url(String cmn001Url) {
        cmn001Url__ = cmn001Url;
    }
    /**
     * <p>cmn001loginType を取得します。
     * @return cmn001loginType
     */
    public int getCmn001loginType() {
        return cmn001loginType__;
    }
    /**
     * <p>cmn001loginType をセットします。
     * @param cmn001loginType cmn001loginType
     */
    public void setCmn001loginType(int cmn001loginType) {
        cmn001loginType__ = cmn001loginType;
    }
    /**
     * <p>cmn001initAccess を取得します。
     * @return cmn001initAccess
     */
    public int getCmn001initAccess() {
        return cmn001initAccess__;
    }
    /**
     * <p>cmn001initAccess をセットします。
     * @param cmn001initAccess cmn001initAccess
     */
    public void setCmn001initAccess(int cmn001initAccess) {
        cmn001initAccess__ = cmn001initAccess;
    }
}