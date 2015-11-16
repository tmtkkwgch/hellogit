package jp.groupsession.v2.prj.prj200kn;

import jp.groupsession.v2.prj.prj200.Prj200ParamModel;

/**
 * <br>[機  能] プロジェクト管理 個人設定 プロジェクトメイン初期値設定確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj200knParamModel extends Prj200ParamModel {

    /** 日付 表示 */
    private String prj200knDateStr__;
    /** 状態 表示 */
    private String prj200knStatusStr__;
    /** メンバー 表示 */
    private String prj200knMemberStr__;

    /**
     * <p>prj200knDateStr を取得します。
     * @return prj200knDateStr
     */
    public String getPrj200knDateStr() {
        return prj200knDateStr__;
    }
    /**
     * <p>prj200knDateStr をセットします。
     * @param prj200knDateStr prj200knDateStr
     */
    public void setPrj200knDateStr(String prj200knDateStr) {
        prj200knDateStr__ = prj200knDateStr;
    }
    /**
     * <p>prj200knStatusStr を取得します。
     * @return prj200knStatusStr
     */
    public String getPrj200knStatusStr() {
        return prj200knStatusStr__;
    }
    /**
     * <p>prj200knStatusStr をセットします。
     * @param prj200knStatusStr prj200knStatusStr
     */
    public void setPrj200knStatusStr(String prj200knStatusStr) {
        prj200knStatusStr__ = prj200knStatusStr;
    }
    /**
     * <p>prj200knMemberStr を取得します。
     * @return prj200knMemberStr
     */
    public String getPrj200knMemberStr() {
        return prj200knMemberStr__;
    }
    /**
     * <p>prj200knMemberStr をセットします。
     * @param prj200knMemberStr prj200knMemberStr
     */
    public void setPrj200knMemberStr(String prj200knMemberStr) {
        prj200knMemberStr__ = prj200knMemberStr;
    }
}