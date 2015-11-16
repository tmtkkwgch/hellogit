package jp.groupsession.v2.rng.rng060;

import java.util.ArrayList;

import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.rng.rng020.Rng020ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 内容テンプレート選択画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng060ParamModel extends Rng020ParamModel {

    /** テンプレート処理モード */
    private int rngTplCmdMode__ = RngConst.RNG_CMDMODE_ADD;
    /** 選択したテンプレートSID */
    private int rngSelectTplSid__ = -1;
    /** チェックされている並び順(共有) */
    private String rng060SortRadio__ = null;
    /** チェックされている並び順(個人) */
    private String rng060SortRadioPrivate__ = null;
    /** テンプレート種別 */
    private int rng060TemplateMode__ = RngConst.RNG_TEMPLATE_SHARE;
    /** 選択カテゴリ */
    private int rng060SelectCat__ = -1;
    /** 選択カテゴリ(個人) */
    private int rng060SelectCatUsr__ = -1;


    /** JSP表示用モデル 共有 */
    ArrayList<RngTemplateModel> rng060tplListShare__ = null;
    /** JSP表示用モデル 個人 */
    ArrayList<RngTemplateModel> rng060tplListPrivate__ = null;

    /** カテゴリコンボボックス(共有) */
    ArrayList<LabelValueBean> rng060CategoryList__ = null;
    /** カテゴリコンボボックス(個人) */
    ArrayList<LabelValueBean> rng060CategoryListPrivate__ = null;
    /**
     * <p>rngTplCmdMode を取得します。
     * @return rngTplCmdMode
     */
    public int getRngTplCmdMode() {
        return rngTplCmdMode__;
    }
    /**
     * <p>rngTplCmdMode をセットします。
     * @param rngTplCmdMode rngTplCmdMode
     */
    public void setRngTplCmdMode(int rngTplCmdMode) {
        rngTplCmdMode__ = rngTplCmdMode;
    }
    /**
     * <p>rngSelectTplSid を取得します。
     * @return rngSelectTplSid
     */
    public int getRngSelectTplSid() {
        return rngSelectTplSid__;
    }
    /**
     * <p>rngSelectTplSid をセットします。
     * @param rngSelectTplSid rngSelectTplSid
     */
    public void setRngSelectTplSid(int rngSelectTplSid) {
        rngSelectTplSid__ = rngSelectTplSid;
    }
    /**
     * <p>rng060SortRadio を取得します。
     * @return rng060SortRadio
     */
    public String getRng060SortRadio() {
        return rng060SortRadio__;
    }
    /**
     * <p>rng060SortRadio をセットします。
     * @param rng060SortRadio rng060SortRadio
     */
    public void setRng060SortRadio(String rng060SortRadio) {
        rng060SortRadio__ = rng060SortRadio;
    }
    /**
     * <p>rng060SortRadioPrivate を取得します。
     * @return rng060SortRadioPrivate
     */
    public String getRng060SortRadioPrivate() {
        return rng060SortRadioPrivate__;
    }
    /**
     * <p>rng060SortRadioPrivate をセットします。
     * @param rng060SortRadioPrivate rng060SortRadioPrivate
     */
    public void setRng060SortRadioPrivate(String rng060SortRadioPrivate) {
        rng060SortRadioPrivate__ = rng060SortRadioPrivate;
    }
    /**
     * <p>rng060TemplateMode を取得します。
     * @return rng060TemplateMode
     */
    public int getRng060TemplateMode() {
        return rng060TemplateMode__;
    }
    /**
     * <p>rng060TemplateMode をセットします。
     * @param rng060TemplateMode rng060TemplateMode
     */
    public void setRng060TemplateMode(int rng060TemplateMode) {
        rng060TemplateMode__ = rng060TemplateMode;
    }
    /**
     * <p>rng060SelectCat を取得します。
     * @return rng060SelectCat
     */
    public int getRng060SelectCat() {
        return rng060SelectCat__;
    }
    /**
     * <p>rng060SelectCat をセットします。
     * @param rng060SelectCat rng060SelectCat
     */
    public void setRng060SelectCat(int rng060SelectCat) {
        rng060SelectCat__ = rng060SelectCat;
    }
    /**
     * <p>rng060SelectCatUsr を取得します。
     * @return rng060SelectCatUsr
     */
    public int getRng060SelectCatUsr() {
        return rng060SelectCatUsr__;
    }
    /**
     * <p>rng060SelectCatUsr をセットします。
     * @param rng060SelectCatUsr rng060SelectCatUsr
     */
    public void setRng060SelectCatUsr(int rng060SelectCatUsr) {
        rng060SelectCatUsr__ = rng060SelectCatUsr;
    }
    /**
     * <p>rng060tplListShare を取得します。
     * @return rng060tplListShare
     */
    public ArrayList<RngTemplateModel> getRng060tplListShare() {
        return rng060tplListShare__;
    }
    /**
     * <p>rng060tplListShare をセットします。
     * @param rng060tplListShare rng060tplListShare
     */
    public void setRng060tplListShare(ArrayList<RngTemplateModel> rng060tplListShare) {
        rng060tplListShare__ = rng060tplListShare;
    }
    /**
     * <p>rng060tplListPrivate を取得します。
     * @return rng060tplListPrivate
     */
    public ArrayList<RngTemplateModel> getRng060tplListPrivate() {
        return rng060tplListPrivate__;
    }
    /**
     * <p>rng060tplListPrivate をセットします。
     * @param rng060tplListPrivate rng060tplListPrivate
     */
    public void setRng060tplListPrivate(
            ArrayList<RngTemplateModel> rng060tplListPrivate) {
        rng060tplListPrivate__ = rng060tplListPrivate;
    }
    /**
     * <p>rng060CategoryList を取得します。
     * @return rng060CategoryList
     */
    public ArrayList<LabelValueBean> getRng060CategoryList() {
        return rng060CategoryList__;
    }
    /**
     * <p>rng060CategoryList をセットします。
     * @param rng060CategoryList rng060CategoryList
     */
    public void setRng060CategoryList(ArrayList<LabelValueBean> rng060CategoryList) {
        rng060CategoryList__ = rng060CategoryList;
    }
    /**
     * <p>rng060CategoryListPrivate を取得します。
     * @return rng060CategoryListPrivate
     */
    public ArrayList<LabelValueBean> getRng060CategoryListPrivate() {
        return rng060CategoryListPrivate__;
    }
    /**
     * <p>rng060CategoryListPrivate をセットします。
     * @param rng060CategoryListPrivate rng060CategoryListPrivate
     */
    public void setRng060CategoryListPrivate(
            ArrayList<LabelValueBean> rng060CategoryListPrivate) {
        rng060CategoryListPrivate__ = rng060CategoryListPrivate;
    }
}