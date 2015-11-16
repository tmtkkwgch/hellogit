package jp.groupsession.v2.man.man400;

import java.util.List;

import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.model.AbstractParamModel;
import jp.groupsession.v2.man.GSConstMain;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 統計情報自動削除画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man400ParamModel extends AbstractParamModel {

    /** 遷移元 メイン個人設定:1 メイン管理者設定:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;
    /** 遷移元プラグイン */
    private String backPlugin__ = "";

    /** 初期表示区分 */
    private String man400InitFlg__ = String.valueOf(GSConstMain.DSP_FIRST);

    /** 削除区分 WEBメール */
    private int man400WmlKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** WEBメール 自動削除 年 */
    private int man400WmlYear__ = GSConstCommon.YEAR_ZERO;
    /** WEBメール 自動削除 月 */
    private int man400WmlMonth__ = GSConstCommon.MONTH_ZERO;

    /** 削除区分 ショートメール */
    private int man400SmlKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** ショートメール 自動削除 年 */
    private int man400SmlYear__ = GSConstCommon.YEAR_ZERO;
    /** ショートメール 自動削除 月 */
    private int man400SmlMonth__ = GSConstCommon.MONTH_ZERO;

    /** 削除区分 回覧板 */
    private int man400CirKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** 回覧板 自動削除 年 */
    private int man400CirYear__ = GSConstCommon.YEAR_ZERO;
    /** 回覧板 自動削除 月 */
    private int man400CirMonth__ = GSConstCommon.MONTH_ZERO;

    /** 削除区分 ファイル管理 */
    private int man400FilKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** ファイル管理 自動削除 年 */
    private int man400FilYear__ = GSConstCommon.YEAR_ZERO;
    /** ファイル管理 自動削除 月 */
    private int man400FilMonth__ = GSConstCommon.MONTH_ZERO;

    /** 削除区分 掲示板 */
    private int man400BbsKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** 掲示板 自動削除 年 */
    private int man400BbsYear__ = GSConstCommon.YEAR_ZERO;
    /** 掲示板 自動削除 月 */
    private int man400BbsMonth__ = GSConstCommon.MONTH_ZERO;

    /** 年コンボ */
    private List<LabelValueBean> yearLabelList__ = null;
    /** 月コンボ */
    private List<LabelValueBean> monthLabelList__ = null;

    /** 使用可能フラグ WEBメール */
    private boolean man400CtrlFlgWml__ = false;
    /** 使用可能フラグ ショートメール */
    private boolean man400CtrlFlgSml__ = false;
    /** 使用可能フラグ 回覧板 */
    private boolean man400CtrlFlgCir__ = false;
    /** 使用可能フラグ ファイル管理 */
    private boolean man400CtrlFlgFil__ = false;
    /** 使用可能フラグ 掲示板 */
    private boolean man400CtrlFlgBbs__ = false;

    /**
     * <p>backScreen を取得します。
     * @return backScreen
     */
    public int getBackScreen() {
        return backScreen__;
    }
    /**
     * <p>backScreen をセットします。
     * @param backScreen backScreen
     */
    public void setBackScreen(int backScreen) {
        backScreen__ = backScreen;
    }
    /**
     * <p>backPlugin を取得します。
     * @return backPlugin
     */
    public String getBackPlugin() {
        return backPlugin__;
    }
    /**
     * <p>backPlugin をセットします。
     * @param backPlugin backPlugin
     */
    public void setBackPlugin(String backPlugin) {
        backPlugin__ = backPlugin;
    }
    /**
     * <p>man400InitFlg を取得します。
     * @return man400InitFlg
     */
    public String getMan400InitFlg() {
        return man400InitFlg__;
    }
    /**
     * <p>man400InitFlg をセットします。
     * @param man400InitFlg man400InitFlg
     */
    public void setMan400InitFlg(String man400InitFlg) {
        man400InitFlg__ = man400InitFlg;
    }
    /**
     * <p>man400WmlKbn を取得します。
     * @return man400WmlKbn
     */
    public int getMan400WmlKbn() {
        return man400WmlKbn__;
    }
    /**
     * <p>man400WmlKbn をセットします。
     * @param man400WmlKbn man400WmlKbn
     */
    public void setMan400WmlKbn(int man400WmlKbn) {
        man400WmlKbn__ = man400WmlKbn;
    }
    /**
     * <p>man400WmlYear を取得します。
     * @return man400WmlYear
     */
    public int getMan400WmlYear() {
        return man400WmlYear__;
    }
    /**
     * <p>man400WmlYear をセットします。
     * @param man400WmlYear man400WmlYear
     */
    public void setMan400WmlYear(int man400WmlYear) {
        man400WmlYear__ = man400WmlYear;
    }
    /**
     * <p>man400WmlMonth を取得します。
     * @return man400WmlMonth
     */
    public int getMan400WmlMonth() {
        return man400WmlMonth__;
    }
    /**
     * <p>man400WmlMonth をセットします。
     * @param man400WmlMonth man400WmlMonth
     */
    public void setMan400WmlMonth(int man400WmlMonth) {
        man400WmlMonth__ = man400WmlMonth;
    }
    /**
     * <p>man400SmlKbn を取得します。
     * @return man400SmlKbn
     */
    public int getMan400SmlKbn() {
        return man400SmlKbn__;
    }
    /**
     * <p>man400SmlKbn をセットします。
     * @param man400SmlKbn man400SmlKbn
     */
    public void setMan400SmlKbn(int man400SmlKbn) {
        man400SmlKbn__ = man400SmlKbn;
    }
    /**
     * <p>man400SmlYear を取得します。
     * @return man400SmlYear
     */
    public int getMan400SmlYear() {
        return man400SmlYear__;
    }
    /**
     * <p>man400SmlYear をセットします。
     * @param man400SmlYear man400SmlYear
     */
    public void setMan400SmlYear(int man400SmlYear) {
        man400SmlYear__ = man400SmlYear;
    }
    /**
     * <p>man400SmlMonth を取得します。
     * @return man400SmlMonth
     */
    public int getMan400SmlMonth() {
        return man400SmlMonth__;
    }
    /**
     * <p>man400SmlMonth をセットします。
     * @param man400SmlMonth man400SmlMonth
     */
    public void setMan400SmlMonth(int man400SmlMonth) {
        man400SmlMonth__ = man400SmlMonth;
    }
    /**
     * <p>man400CirKbn を取得します。
     * @return man400CirKbn
     */
    public int getMan400CirKbn() {
        return man400CirKbn__;
    }
    /**
     * <p>man400CirKbn をセットします。
     * @param man400CirKbn man400CirKbn
     */
    public void setMan400CirKbn(int man400CirKbn) {
        man400CirKbn__ = man400CirKbn;
    }
    /**
     * <p>man400CirYear を取得します。
     * @return man400CirYear
     */
    public int getMan400CirYear() {
        return man400CirYear__;
    }
    /**
     * <p>man400CirYear をセットします。
     * @param man400CirYear man400CirYear
     */
    public void setMan400CirYear(int man400CirYear) {
        man400CirYear__ = man400CirYear;
    }
    /**
     * <p>man400CirMonth を取得します。
     * @return man400CirMonth
     */
    public int getMan400CirMonth() {
        return man400CirMonth__;
    }
    /**
     * <p>man400CirMonth をセットします。
     * @param man400CirMonth man400CirMonth
     */
    public void setMan400CirMonth(int man400CirMonth) {
        man400CirMonth__ = man400CirMonth;
    }
    /**
     * <p>man400FilKbn を取得します。
     * @return man400FilKbn
     */
    public int getMan400FilKbn() {
        return man400FilKbn__;
    }
    /**
     * <p>man400FilKbn をセットします。
     * @param man400FilKbn man400FilKbn
     */
    public void setMan400FilKbn(int man400FilKbn) {
        man400FilKbn__ = man400FilKbn;
    }
    /**
     * <p>man400FilYear を取得します。
     * @return man400FilYear
     */
    public int getMan400FilYear() {
        return man400FilYear__;
    }
    /**
     * <p>man400FilYear をセットします。
     * @param man400FilYear man400FilYear
     */
    public void setMan400FilYear(int man400FilYear) {
        man400FilYear__ = man400FilYear;
    }
    /**
     * <p>man400FilMonth を取得します。
     * @return man400FilMonth
     */
    public int getMan400FilMonth() {
        return man400FilMonth__;
    }
    /**
     * <p>man400FilMonth をセットします。
     * @param man400FilMonth man400FilMonth
     */
    public void setMan400FilMonth(int man400FilMonth) {
        man400FilMonth__ = man400FilMonth;
    }
    /**
     * <p>man400BbsKbn を取得します。
     * @return man400BbsKbn
     */
    public int getMan400BbsKbn() {
        return man400BbsKbn__;
    }
    /**
     * <p>man400BbsKbn をセットします。
     * @param man400BbsKbn man400BbsKbn
     */
    public void setMan400BbsKbn(int man400BbsKbn) {
        man400BbsKbn__ = man400BbsKbn;
    }
    /**
     * <p>man400BbsYear を取得します。
     * @return man400BbsYear
     */
    public int getMan400BbsYear() {
        return man400BbsYear__;
    }
    /**
     * <p>man400BbsYear をセットします。
     * @param man400BbsYear man400BbsYear
     */
    public void setMan400BbsYear(int man400BbsYear) {
        man400BbsYear__ = man400BbsYear;
    }
    /**
     * <p>man400BbsMonth を取得します。
     * @return man400BbsMonth
     */
    public int getMan400BbsMonth() {
        return man400BbsMonth__;
    }
    /**
     * <p>man400BbsMonth をセットします。
     * @param man400BbsMonth man400BbsMonth
     */
    public void setMan400BbsMonth(int man400BbsMonth) {
        man400BbsMonth__ = man400BbsMonth;
    }
    /**
     * <p>yearLabelList を取得します。
     * @return yearLabelList
     */
    public List<LabelValueBean> getYearLabelList() {
        return yearLabelList__;
    }
    /**
     * <p>yearLabelList をセットします。
     * @param yearLabelList yearLabelList
     */
    public void setYearLabelList(List<LabelValueBean> yearLabelList) {
        yearLabelList__ = yearLabelList;
    }
    /**
     * <p>monthLabelList を取得します。
     * @return monthLabelList
     */
    public List<LabelValueBean> getMonthLabelList() {
        return monthLabelList__;
    }
    /**
     * <p>monthLabelList をセットします。
     * @param monthLabelList monthLabelList
     */
    public void setMonthLabelList(List<LabelValueBean> monthLabelList) {
        monthLabelList__ = monthLabelList;
    }
    /**
     * <p>man400CtrlFlgWml を取得します。
     * @return man400CtrlFlgWml
     */
    public boolean isMan400CtrlFlgWml() {
        return man400CtrlFlgWml__;
    }
    /**
     * <p>man400CtrlFlgWml をセットします。
     * @param man400CtrlFlgWml man400CtrlFlgWml
     */
    public void setMan400CtrlFlgWml(boolean man400CtrlFlgWml) {
        man400CtrlFlgWml__ = man400CtrlFlgWml;
    }
    /**
     * <p>man400CtrlFlgSml を取得します。
     * @return man400CtrlFlgSml
     */
    public boolean isMan400CtrlFlgSml() {
        return man400CtrlFlgSml__;
    }
    /**
     * <p>man400CtrlFlgSml をセットします。
     * @param man400CtrlFlgSml man400CtrlFlgSml
     */
    public void setMan400CtrlFlgSml(boolean man400CtrlFlgSml) {
        man400CtrlFlgSml__ = man400CtrlFlgSml;
    }
    /**
     * <p>man400CtrlFlgCir を取得します。
     * @return man400CtrlFlgCir
     */
    public boolean isMan400CtrlFlgCir() {
        return man400CtrlFlgCir__;
    }
    /**
     * <p>man400CtrlFlgCir をセットします。
     * @param man400CtrlFlgCir man400CtrlFlgCir
     */
    public void setMan400CtrlFlgCir(boolean man400CtrlFlgCir) {
        man400CtrlFlgCir__ = man400CtrlFlgCir;
    }
    /**
     * <p>man400CtrlFlgFil を取得します。
     * @return man400CtrlFlgFil
     */
    public boolean isMan400CtrlFlgFil() {
        return man400CtrlFlgFil__;
    }
    /**
     * <p>man400CtrlFlgFil をセットします。
     * @param man400CtrlFlgFil man400CtrlFlgFil
     */
    public void setMan400CtrlFlgFil(boolean man400CtrlFlgFil) {
        man400CtrlFlgFil__ = man400CtrlFlgFil;
    }
    /**
     * <p>man400CtrlFlgBbs を取得します。
     * @return man400CtrlFlgBbs
     */
    public boolean isMan400CtrlFlgBbs() {
        return man400CtrlFlgBbs__;
    }
    /**
     * <p>man400CtrlFlgBbs をセットします。
     * @param man400CtrlFlgBbs man400CtrlFlgBbs
     */
    public void setMan400CtrlFlgBbs(boolean man400CtrlFlgBbs) {
        man400CtrlFlgBbs__ = man400CtrlFlgBbs;
    }
}
