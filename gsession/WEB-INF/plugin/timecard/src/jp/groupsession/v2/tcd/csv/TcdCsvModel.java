package jp.groupsession.v2.tcd.csv;

import jp.groupsession.v2.tcd.tcd010.Tcd010Model;

/**
 * <br>[機  能] タイムカード一覧CSV用の１日分のデータを格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TcdCsvModel extends Tcd010Model {

    /** ユーザ氏名*/
    private String userName__;

    /**
     * <p>userName を取得します。
     * @return userName
     */
    public String getUserName() {
        return userName__;
    }

    /**
     * <p>userName をセットします。
     * @param userName userName
     */
    public void setUserName(String userName) {
        userName__ = userName;
    }

}
