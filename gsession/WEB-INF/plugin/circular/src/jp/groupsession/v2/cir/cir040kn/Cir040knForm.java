package jp.groupsession.v2.cir.cir040kn;

import java.util.ArrayList;

import jp.groupsession.v2.cir.cir040.Cir040Form;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;

/**
 * <br>[機  能] 回覧板 新規作成確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir040knForm extends Cir040Form {
    /** 添付ファイルリスト */
    private ArrayList<CmnBinfModel> cir040FileList__ = null;
    /** 添付ファイルのバイナリSID(ダウンロード時) */
    private String cir040knBinSid__ = null;
    /** 表示用内容*/
    private String cir040knBody__ = null;
    /**
     * <p>cir040FileList を取得します。
     * @return cir040FileList
     */
    public ArrayList<CmnBinfModel> getCir040FileList() {
        return cir040FileList__;
    }
    /**
     * <p>cir040FileList をセットします。
     * @param cir040FileList cir040FileList
     */
    public void setCir040FileList(ArrayList<CmnBinfModel> cir040FileList) {
        cir040FileList__ = cir040FileList;
    }
    /**
     * <p>cir040knBinSid を取得します。
     * @return cir040knBinSid
     */
    public String getCir040knBinSid() {
        return cir040knBinSid__;
    }
    /**
     * <p>cir040knBinSid をセットします。
     * @param cir040knBinSid cir040knBinSid
     */
    public void setCir040knBinSid(String cir040knBinSid) {
        cir040knBinSid__ = cir040knBinSid;
    }
    /**
     * <p>cir040knBody を取得します。
     * @return cir040knBody
     */
    public String getCir040knBody() {
        return cir040knBody__;
    }
    /**
     * <p>cir040knBody をセットします。
     * @param cir040knBody cir040knBody
     */
    public void setCir040knBody(String cir040knBody) {
        cir040knBody__ = cir040knBody;
    }
}
