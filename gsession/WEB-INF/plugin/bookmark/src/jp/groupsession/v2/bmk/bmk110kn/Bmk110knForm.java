package jp.groupsession.v2.bmk.bmk110kn;

import java.util.List;

import jp.groupsession.v2.bmk.bmk110.Bmk110Form;

/**
 * <br>[機  能] 管理者設定 権限設定確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk110knForm extends Bmk110Form {
    /** 編集ユーザ一覧 */
    private List<String> bmk110knUserList__ = null;
    /** 編集グループ一覧 */
    private List<String> bmk110knGroupList__ = null;
    /**
     * <p>bmk110knGroupList を取得します。
     * @return bmk110knGroupList
     */
    public List<String> getBmk110knGroupList() {
        return bmk110knGroupList__;
    }
    /**
     * <p>bmk110knGroupList をセットします。
     * @param bmk110knGroupList bmk110knGroupList
     */
    public void setBmk110knGroupList(List<String> bmk110knGroupList) {
        bmk110knGroupList__ = bmk110knGroupList;
    }
    /**
     * <p>bmk110knUserList を取得します。
     * @return bmk110knUserList
     */
    public List<String> getBmk110knUserList() {
        return bmk110knUserList__;
    }
    /**
     * <p>bmk110knUserList をセットします。
     * @param bmk110knUserList bmk110knUserList
     */
    public void setBmk110knUserList(List<String> bmk110knUserList) {
        bmk110knUserList__ = bmk110knUserList;
    }
}
