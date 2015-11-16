package jp.groupsession.v2.cmn.cmn170;

import java.util.List;

import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.struts.AbstractGsForm;

import org.apache.struts.action.ActionErrors;

/**
 * <br>[機  能] メイン 個人設定 テーマ設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn170Form extends AbstractGsForm {

    /** 画面項目表示有無 */
    private int cmn170Dsp1__ = GSConstCommon.NUM_INIT;

    /** テーマ画像パス保存リスト */
    private List<Cmn170Model> themeList__;

    /**
     * <p>cmn170Dsp1 を取得します。
     * @return cmn170Dsp1
     */
    public int getCmn170Dsp1() {
        return cmn170Dsp1__;
    }

    /**
     * <p>cmn170Dsp1 をセットします。
     * @param cmn170Dsp1 cmn170Dsp1
     */
    public void setCmn170Dsp1(int cmn170Dsp1) {
        cmn170Dsp1__ = cmn170Dsp1;
    }

    /**
     * <p>themeList を取得します。
     * @return themeList
     */
    public List<Cmn170Model> getThemeList() {
        return themeList__;
    }
    /**
     * <p>themeList をセットします。
     * @param themeList themeList
     */
    public void setThemeList(List <Cmn170Model> themeList) {
        themeList__ = themeList;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @return エラー
     */
    public ActionErrors validateCheck() {
        ActionErrors errors = new ActionErrors();
        return errors;
    }
}
