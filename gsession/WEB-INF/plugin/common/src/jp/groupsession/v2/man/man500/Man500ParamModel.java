package jp.groupsession.v2.man.man500;

import jp.groupsession.v2.man.AbstractMainParamModel;
import jp.groupsession.v2.man.GSConstMain;

/**
 * <br>[機  能] メイン 管理者設定 個人情報編集権限設定画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man500ParamModel extends AbstractMainParamModel {
    /** 初期表示フラグ */
    private int man500init__ = 0;
    /** 個人情報編集区分  */
    private int man500EditKbn__ = GSConstMain.PCONF_EDIT_USER;
    /** パスワード編集区分 */
    private int man500PasswordKbn__ = GSConstMain.PASSWORD_EDIT_USER;
    /**
     * <p>man500init を取得します。
     * @return man500init
     */
    public int getMan500init() {
        return man500init__;
    }
    /**
     * <p>man500init をセットします。
     * @param man500init man500init
     */
    public void setMan500init(int man500init) {
        man500init__ = man500init;
    }
    /**
     * <p>man500EditKbn を取得します。
     * @return man500EditKbn
     */
    public int getMan500EditKbn() {
        return man500EditKbn__;
    }
    /**
     * <p>man500EditKbn をセットします。
     * @param man500EditKbn man500EditKbn
     */
    public void setMan500EditKbn(int man500EditKbn) {
        man500EditKbn__ = man500EditKbn;
    }
    /**
     * <p>man500PasswordKbn を取得します。
     * @return man500PasswordKbn
     */
    public int getMan500PasswordKbn() {
        return man500PasswordKbn__;
    }
    /**
     * <p>man500PasswordKbn をセットします。
     * @param man500PasswordKbn man500PasswordKbn
     */
    public void setMan500PasswordKbn(int man500PasswordKbn) {
        man500PasswordKbn__ = man500PasswordKbn;
    }
}
