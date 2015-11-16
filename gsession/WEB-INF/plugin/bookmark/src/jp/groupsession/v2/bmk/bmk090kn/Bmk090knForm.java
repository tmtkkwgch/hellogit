package jp.groupsession.v2.bmk.bmk090kn;

import java.util.List;

import jp.groupsession.v2.bmk.bmk090.Bmk090Form;

/**
 * <br>[機  能] 権限設定(グループブックマーク)確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk090knForm extends Bmk090Form {
    /** 編集ユーザ一覧 */
    private List<String> bmk090knUserList__ = null;
    /** 編集グループ一覧 */
    private List<String> bmk090knGroupList__ = null;
    /**
     * <p>bmk090knGroupList を取得します。
     * @return bmk090knGroupList
     */
    public List<String> getBmk090knGroupList() {
        return bmk090knGroupList__;
    }
    /**
     * <p>bmk090knGroupList をセットします。
     * @param bmk090knGroupList bmk090knGroupList
     */
    public void setBmk090knGroupList(List<String> bmk090knGroupList) {
        bmk090knGroupList__ = bmk090knGroupList;
    }
    /**
     * <p>bmk090knUserList を取得します。
     * @return bmk090knUserList
     */
    public List<String> getBmk090knUserList() {
        return bmk090knUserList__;
    }
    /**
     * <p>bmk090knUserList をセットします。
     * @param bmk090knUserList bmk090knUserList
     */
    public void setBmk090knUserList(List<String> bmk090knUserList) {
        bmk090knUserList__ = bmk090knUserList;
    }
}
