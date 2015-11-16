package jp.groupsession.v2.cmn.cmn220;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;

/**
 * <br>[機  能] グループ一覧ポップアップ用 ユーザ一覧表示用検索結果モデル
 * <br>[解  説] ユーザ情報＋所属グループ
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn220UsrDspModel extends CmnUsrmInfModel {

    /** 所属グループ一覧 */
    private ArrayList<GroupModel> belongGrpList__ = null;

    /**
     * <p>belongGrpList を取得します。
     * @return belongGrpList
     */
    public ArrayList<GroupModel> getBelongGrpList() {
        return belongGrpList__;
    }

    /**
     * <p>belongGrpList をセットします。
     * @param belongGrpList belongGrpList
     */
    public void setBelongGrpList(ArrayList<GroupModel> belongGrpList) {
        belongGrpList__ = belongGrpList;
    }

}