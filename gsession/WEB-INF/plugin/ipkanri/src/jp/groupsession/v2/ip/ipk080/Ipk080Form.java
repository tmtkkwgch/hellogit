package jp.groupsession.v2.ip.ipk080;

import jp.groupsession.v2.ip.ipk010.Ipk010Form;

/**
 * <br>[機  能] IP管理 管理者メニュー画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Ipk080Form extends Ipk010Form {
    /** ページ遷移コマンド */
    private String cmd__;

    /**
     * <p>cmd を取得します。
     * @return cmd
     */
    public String getCmd() {
        return cmd__;
    }

    /**
     * <p>cmd をセットします。
     * @param cmd cmd
     */
    public void setCmd(String cmd) {
        cmd__ = cmd;
    }
}