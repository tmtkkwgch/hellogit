package jp.groupsession.v2.man.man410;

import java.util.List;

import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.model.AbstractParamModel;
import jp.groupsession.v2.man.GSConstMain;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 統計情報手動削除画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man410ParamModel extends AbstractParamModel {

    /** 遷移元 メイン個人設定:1 メイン管理者設定:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;
    /** 遷移元プラグイン */
    private String backPlugin__ = "";

    /** WEBメール 削除区分 */
    private int man410WmlKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** WEBメール 手動削除 年 */
    private int man410WmlYear__ = GSConstCommon.YEAR_ZERO;
    /** WEBメール 手動削除 月 */
    private int man410WmlMonth__ = GSConstCommon.MONTH_ZERO;

    /** ショートメール 削除区分 */
    private int man410SmlKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** ショートメール 手動削除 年 */
    private int man410SmlYear__ = GSConstCommon.YEAR_ZERO;
    /** ショートメール 手動削除 月 */
    private int man410SmlMonth__ = GSConstCommon.MONTH_ZERO;

    /** 回覧板 削除区分 */
    private int man410CirKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** 回覧板 手動削除 年 */
    private int man410CirYear__ = GSConstCommon.YEAR_ZERO;
    /** 回覧板 手動削除 月 */
    private int man410CirMonth__ = GSConstCommon.MONTH_ZERO;

    /** ファイル管理 削除区分 */
    private int man410FilKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** ファイル管理 手動削除 年 */
    private int man410FilYear__ = GSConstCommon.YEAR_ZERO;
    /** ファイル管理 手動削除 月 */
    private int man410FilMonth__ = GSConstCommon.MONTH_ZERO;

    /** 掲示板 削除区分 */
    private int man410BbsKbn__ = GSConstMain.LAD_DELKBN_NO;
    /** 掲示板 手動削除 年 */
    private int man410BbsYear__ = GSConstCommon.YEAR_ZERO;
    /** 掲示板 手動削除 月 */
    private int man410BbsMonth__ = GSConstCommon.MONTH_ZERO;

    /** 年コンボ */
    private List<LabelValueBean> yearLabelList__ = null;
    /** 月コンボ */
    private List<LabelValueBean> monthLabelList__ = null;

    /** 使用可能フラグ WEBメール */
    private boolean man410CtrlFlgWml__ = false;
    /** 使用可能フラグ ショートメール */
    private boolean man410CtrlFlgSml__ = false;
    /** 使用可能フラグ 回覧板 */
    private boolean man410CtrlFlgCir__ = false;
    /** 使用可能フラグ ファイル管理 */
    private boolean man410CtrlFlgFil__ = false;
    /** 使用可能フラグ 掲示板 */
    private boolean man410CtrlFlgBbs__ = false;

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
     * <p>man410WmlKbn を取得します。
     * @return man410WmlKbn
     */
    public int getMan410WmlKbn() {
        return man410WmlKbn__;
    }
    /**
     * <p>man410WmlKbn をセットします。
     * @param man410WmlKbn man410WmlKbn
     */
    public void setMan410WmlKbn(int man410WmlKbn) {
        man410WmlKbn__ = man410WmlKbn;
    }
    /**
     * <p>man410WmlYear を取得します。
     * @return man410WmlYear
     */
    public int getMan410WmlYear() {
        return man410WmlYear__;
    }
    /**
     * <p>man410WmlYear をセットします。
     * @param man410WmlYear man410WmlYear
     */
    public void setMan410WmlYear(int man410WmlYear) {
        man410WmlYear__ = man410WmlYear;
    }
    /**
     * <p>man410WmlMonth を取得します。
     * @return man410WmlMonth
     */
    public int getMan410WmlMonth() {
        return man410WmlMonth__;
    }
    /**
     * <p>man410WmlMonth をセットします。
     * @param man410WmlMonth man410WmlMonth
     */
    public void setMan410WmlMonth(int man410WmlMonth) {
        man410WmlMonth__ = man410WmlMonth;
    }
    /**
     * <p>man410SmlKbn を取得します。
     * @return man410SmlKbn
     */
    public int getMan410SmlKbn() {
        return man410SmlKbn__;
    }
    /**
     * <p>man410SmlKbn をセットします。
     * @param man410SmlKbn man410SmlKbn
     */
    public void setMan410SmlKbn(int man410SmlKbn) {
        man410SmlKbn__ = man410SmlKbn;
    }
    /**
     * <p>man410SmlYear を取得します。
     * @return man410SmlYear
     */
    public int getMan410SmlYear() {
        return man410SmlYear__;
    }
    /**
     * <p>man410SmlYear をセットします。
     * @param man410SmlYear man410SmlYear
     */
    public void setMan410SmlYear(int man410SmlYear) {
        man410SmlYear__ = man410SmlYear;
    }
    /**
     * <p>man410SmlMonth を取得します。
     * @return man410SmlMonth
     */
    public int getMan410SmlMonth() {
        return man410SmlMonth__;
    }
    /**
     * <p>man410SmlMonth をセットします。
     * @param man410SmlMonth man410SmlMonth
     */
    public void setMan410SmlMonth(int man410SmlMonth) {
        man410SmlMonth__ = man410SmlMonth;
    }
    /**
     * <p>man410CirKbn を取得します。
     * @return man410CirKbn
     */
    public int getMan410CirKbn() {
        return man410CirKbn__;
    }
    /**
     * <p>man410CirKbn をセットします。
     * @param man410CirKbn man410CirKbn
     */
    public void setMan410CirKbn(int man410CirKbn) {
        man410CirKbn__ = man410CirKbn;
    }
    /**
     * <p>man410CirYear を取得します。
     * @return man410CirYear
     */
    public int getMan410CirYear() {
        return man410CirYear__;
    }
    /**
     * <p>man410CirYear をセットします。
     * @param man410CirYear man410CirYear
     */
    public void setMan410CirYear(int man410CirYear) {
        man410CirYear__ = man410CirYear;
    }
    /**
     * <p>man410CirMonth を取得します。
     * @return man410CirMonth
     */
    public int getMan410CirMonth() {
        return man410CirMonth__;
    }
    /**
     * <p>man410CirMonth をセットします。
     * @param man410CirMonth man410CirMonth
     */
    public void setMan410CirMonth(int man410CirMonth) {
        man410CirMonth__ = man410CirMonth;
    }
    /**
     * <p>man410FilKbn を取得します。
     * @return man410FilKbn
     */
    public int getMan410FilKbn() {
        return man410FilKbn__;
    }
    /**
     * <p>man410FilKbn をセットします。
     * @param man410FilKbn man410FilKbn
     */
    public void setMan410FilKbn(int man410FilKbn) {
        man410FilKbn__ = man410FilKbn;
    }
    /**
     * <p>man410FilYear を取得します。
     * @return man410FilYear
     */
    public int getMan410FilYear() {
        return man410FilYear__;
    }
    /**
     * <p>man410FilYear をセットします。
     * @param man410FilYear man410FilYear
     */
    public void setMan410FilYear(int man410FilYear) {
        man410FilYear__ = man410FilYear;
    }
    /**
     * <p>man410FilMonth を取得します。
     * @return man410FilMonth
     */
    public int getMan410FilMonth() {
        return man410FilMonth__;
    }
    /**
     * <p>man410FilMonth をセットします。
     * @param man410FilMonth man410FilMonth
     */
    public void setMan410FilMonth(int man410FilMonth) {
        man410FilMonth__ = man410FilMonth;
    }
    /**
     * <p>man410BbsKbn を取得します。
     * @return man410BbsKbn
     */
    public int getMan410BbsKbn() {
        return man410BbsKbn__;
    }
    /**
     * <p>man410BbsKbn をセットします。
     * @param man410BbsKbn man410BbsKbn
     */
    public void setMan410BbsKbn(int man410BbsKbn) {
        man410BbsKbn__ = man410BbsKbn;
    }
    /**
     * <p>man410BbsYear を取得します。
     * @return man410BbsYear
     */
    public int getMan410BbsYear() {
        return man410BbsYear__;
    }
    /**
     * <p>man410BbsYear をセットします。
     * @param man410BbsYear man410BbsYear
     */
    public void setMan410BbsYear(int man410BbsYear) {
        man410BbsYear__ = man410BbsYear;
    }
    /**
     * <p>man410BbsMonth を取得します。
     * @return man410BbsMonth
     */
    public int getMan410BbsMonth() {
        return man410BbsMonth__;
    }
    /**
     * <p>man410BbsMonth をセットします。
     * @param man410BbsMonth man410BbsMonth
     */
    public void setMan410BbsMonth(int man410BbsMonth) {
        man410BbsMonth__ = man410BbsMonth;
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
     * <p>man410CtrlFlgWml を取得します。
     * @return man410CtrlFlgWml
     */
    public boolean isMan410CtrlFlgWml() {
        return man410CtrlFlgWml__;
    }
    /**
     * <p>man410CtrlFlgWml をセットします。
     * @param man410CtrlFlgWml man410CtrlFlgWml
     */
    public void setMan410CtrlFlgWml(boolean man410CtrlFlgWml) {
        man410CtrlFlgWml__ = man410CtrlFlgWml;
    }
    /**
     * <p>man410CtrlFlgSml を取得します。
     * @return man410CtrlFlgSml
     */
    public boolean isMan410CtrlFlgSml() {
        return man410CtrlFlgSml__;
    }
    /**
     * <p>man410CtrlFlgSml をセットします。
     * @param man410CtrlFlgSml man410CtrlFlgSml
     */
    public void setMan410CtrlFlgSml(boolean man410CtrlFlgSml) {
        man410CtrlFlgSml__ = man410CtrlFlgSml;
    }
    /**
     * <p>man410CtrlFlgCir を取得します。
     * @return man410CtrlFlgCir
     */
    public boolean isMan410CtrlFlgCir() {
        return man410CtrlFlgCir__;
    }
    /**
     * <p>man410CtrlFlgCir をセットします。
     * @param man410CtrlFlgCir man410CtrlFlgCir
     */
    public void setMan410CtrlFlgCir(boolean man410CtrlFlgCir) {
        man410CtrlFlgCir__ = man410CtrlFlgCir;
    }
    /**
     * <p>man410CtrlFlgFil を取得します。
     * @return man410CtrlFlgFil
     */
    public boolean isMan410CtrlFlgFil() {
        return man410CtrlFlgFil__;
    }
    /**
     * <p>man410CtrlFlgFil をセットします。
     * @param man410CtrlFlgFil man410CtrlFlgFil
     */
    public void setMan410CtrlFlgFil(boolean man410CtrlFlgFil) {
        man410CtrlFlgFil__ = man410CtrlFlgFil;
    }
    /**
     * <p>man410CtrlFlgBbs を取得します。
     * @return man410CtrlFlgBbs
     */
    public boolean isMan410CtrlFlgBbs() {
        return man410CtrlFlgBbs__;
    }
    /**
     * <p>man410CtrlFlgBbs をセットします。
     * @param man410CtrlFlgBbs man410CtrlFlgBbs
     */
    public void setMan410CtrlFlgBbs(boolean man410CtrlFlgBbs) {
        man410CtrlFlgBbs__ = man410CtrlFlgBbs;
    }
}
