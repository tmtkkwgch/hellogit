package jp.groupsession.v2.bmk.bmk140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.bmk120.Bmk120ParamModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン画面表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk140ParamModel extends Bmk120ParamModel {
    /** 個人ブックマークメイン表示区分 */
    private int bmk140MyKbn__ = -1;
    /** 新着ブックマークメイン表示区分 */
    private int bmk140NewKbn__ = -1;
    /** 新着ブックマーク表示日数の選択値 */
    public static final String[] NEWCNTVALUE
        = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //入力項目
    /** 表示ブックマーク(選択) */
    private String[] bmk140SelectViewBmk__ = null;
    /** 非表示ブックマーク(選択) */
    private String[] bmk140SelectNotViewBmk__ = null;
    /** 新着ブックマーク表示日数 */
    private int bmk140newCnt__ = 0;

    //表示項目
    /** 表示ブックマークコンボ */
    private List<LabelValueBean> bmk140ViewBmkLabel__ = null;
    /** 非表示ブックマークコンボ */
    private List<LabelValueBean> bmk140NotViewBmkLabel__ = null;
    /** 表示ブックマーク(パラメータ保持用) */
    private String[] bmk140ViewBmkList__ = null;
    /** 新着ブックマーク表示日数 */
    private List < LabelValueBean > bmk140newCntLabel__ = null;

    /**
     * コンストラクタ
     */
    public Bmk140ParamModel() {
    }

    /**
     * <p>bmk140newCnt を取得します。
     * @return bmk140newCnt
     */
    public int getBmk140newCnt() {
        return bmk140newCnt__;
    }
    /**
     * <p>bmk140newCnt をセットします。
     * @param bmk140newCnt bmk140newCnt
     */
    public void setBmk140newCnt(int bmk140newCnt) {
        bmk140newCnt__ = bmk140newCnt;
    }
    /**
     * <p>bmk140newCntLabel を取得します。
     * @return bmk140newCntLabel
     */
    public List<LabelValueBean> getBmk140newCntLabel() {
        return bmk140newCntLabel__;
    }
    /**
     * <p>bmk140newCntLabel をセットします。
     * @param bmk140newCntLabel bmk140newCntLabel
     */
    public void setBmk140newCntLabel(List<LabelValueBean> bmk140newCntLabel) {
        bmk140newCntLabel__ = bmk140newCntLabel;
    }
    /**
     * <p>bmk140MyKbn を取得します。
     * @return bmk140MyKbn
     */
    public int getBmk140MyKbn() {
        return bmk140MyKbn__;
    }
    /**
     * <p>bmk140MyKbn をセットします。
     * @param bmk140MyKbn bmk140MyKbn
     */
    public void setBmk140MyKbn(int bmk140MyKbn) {
        bmk140MyKbn__ = bmk140MyKbn;
    }
    /**
     * <p>bmk140NewKbn を取得します。
     * @return bmk140NewKbn
     */
    public int getBmk140NewKbn() {
        return bmk140NewKbn__;
    }
    /**
     * <p>bmk140NewKbn をセットします。
     * @param bmk140NewKbn bmk140NewKbn
     */
    public void setBmk140NewKbn(int bmk140NewKbn) {
        bmk140NewKbn__ = bmk140NewKbn;
    }
    /**
     * <p>bmk140NotViewBmkLabel を取得します。
     * @return bmk140NotViewBmkLabel
     */
    public List<LabelValueBean> getBmk140NotViewBmkLabel() {
        return bmk140NotViewBmkLabel__;
    }
    /**
     * <p>bmk140NotViewBmkLabel をセットします。
     * @param bmk140NotViewBmkLabel bmk140NotViewBmkLabel
     */
    public void setBmk140NotViewBmkLabel(List<LabelValueBean> bmk140NotViewBmkLabel) {
        bmk140NotViewBmkLabel__ = bmk140NotViewBmkLabel;
    }
    /**
     * <p>bmk140SelectNotViewBmk を取得します。
     * @return bmk140SelectNotViewBmk
     */
    public String[] getBmk140SelectNotViewBmk() {
        return bmk140SelectNotViewBmk__;
    }
    /**
     * <p>bmk140SelectNotViewBmk をセットします。
     * @param bmk140SelectNotViewBmk bmk140SelectNotViewBmk
     */
    public void setBmk140SelectNotViewBmk(String[] bmk140SelectNotViewBmk) {
        bmk140SelectNotViewBmk__ = bmk140SelectNotViewBmk;
    }
    /**
     * <p>bmk140SelectViewBmk を取得します。
     * @return bmk140SelectViewBmk
     */
    public String[] getBmk140SelectViewBmk() {
        return bmk140SelectViewBmk__;
    }
    /**
     * <p>bmk140SelectViewBmk をセットします。
     * @param bmk140SelectViewBmk bmk140SelectViewBmk
     */
    public void setBmk140SelectViewBmk(String[] bmk140SelectViewBmk) {
        bmk140SelectViewBmk__ = bmk140SelectViewBmk;
    }
    /**
     * <p>bmk140ViewBmkLabel を取得します。
     * @return bmk140ViewBmkLabel
     */
    public List<LabelValueBean> getBmk140ViewBmkLabel() {
        return bmk140ViewBmkLabel__;
    }
    /**
     * <p>bmk140ViewBmkLabel をセットします。
     * @param bmk140ViewBmkLabel bmk140ViewBmkLabel
     */
    public void setBmk140ViewBmkLabel(List<LabelValueBean> bmk140ViewBmkLabel) {
        bmk140ViewBmkLabel__ = bmk140ViewBmkLabel;
    }
    /**
     * <p>bmk140ViewBmkList を取得します。
     * @return bmk140ViewBmkList
     */
    public String[] getBmk140ViewBmkList() {
        return bmk140ViewBmkList__;
    }
    /**
     * <p>bmk140ViewBmkList をセットします。
     * @param bmk140ViewBmkList bmk140ViewBmkList
     */
    public void setBmk140ViewBmkList(String[] bmk140ViewBmkList) {
        bmk140ViewBmkList__ = bmk140ViewBmkList;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, HttpServletRequest req)
    throws SQLException {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage();
        String msg2 = gsMsg.getMessage(req, "bmk.58");

        if (bmk140MyKbn__ == GSConstBookmark.DSP_YES) {
            //メイン表示区分：表示
            if (bmk140ViewBmkList__ == null || bmk140ViewBmkList__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msg2);
                StrutsUtil.addMessage(
                        errors, msg, "bmk140ViewBmkList." + msgKey);
            }
        }
        return errors;
    }

    /**
     * <br>[機  能] 新着ブックマーク表示日数コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public void setNewCntLabel(RequestModel reqMdl) {
        bmk140newCntLabel__ = new ArrayList < LabelValueBean >();
        GsMessage gsMsg = new GsMessage(reqMdl);
        for (String label : NEWCNTVALUE) {
            bmk140newCntLabel__.add(
                    new LabelValueBean(gsMsg.getMessage("cmn.less.than.day", new String[] {label}),
                                        label));
        }
    }
}
