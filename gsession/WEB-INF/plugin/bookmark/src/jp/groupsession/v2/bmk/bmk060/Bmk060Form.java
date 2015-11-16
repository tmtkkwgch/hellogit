package jp.groupsession.v2.bmk.bmk060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.bmk050.Bmk050Form;
import jp.groupsession.v2.bmk.dao.BmkLabelDao;
import jp.groupsession.v2.bmk.dao.BmkLabelDataDao;
import jp.groupsession.v2.bmk.model.BmkLabelDataModel;
import jp.groupsession.v2.bmk.model.BmkLabelModel;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ラベル登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Bmk060Form extends Bmk050Form {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk060Form.class);
    /** ラベル名 */
    private String bmk060LblName__ = null;

    /** ラベル統合区分 */
    private int bmk060LblKbn__ = GSConstBookmark.LABEL_TOGO_NO;
    //入力項目
    /** 統合ラベル(選択) */
    private String[] bmk060SelectLeftLabel__ = null;
    /** 選択ラベル(選択) */
    private String[] bmk060SelectRightLabel__ = null;

    //表示項目
    /** 統合ラベルコンボ */
    private List<LabelValueBean> bmk060LeftLabelList__ = null;
    /** 選択ラベルコンボ */
    private List<LabelValueBean> bmk060RightLabelList__ = null;
    /** 統合ラベル(パラメータ保持用) */
    private String[] bmk060LabelList__ = null;

    /**
     * <p>bmk060LblName を取得します。
     * @return bmk060LblName
     */
    public String getBmk060LblName() {
        return bmk060LblName__;
    }

    /**
     * <p>bmk060LblName をセットします。
     * @param bmk060LblName bmk060LblName
     */
    public void setBmk060LblName(String bmk060LblName) {
        bmk060LblName__ = bmk060LblName;
    }

    /**
     * <p>bmk060LabelList を取得します。
     * @return bmk060LabelList
     */
    public String[] getBmk060LabelList() {
        return bmk060LabelList__;
    }

    /**
     * <p>bmk060LabelList をセットします。
     * @param bmk060LabelList bmk060LabelList
     */
    public void setBmk060LabelList(String[] bmk060LabelList) {
        bmk060LabelList__ = bmk060LabelList;
    }

    /**
     * <p>bmk060LblKbn を取得します。
     * @return bmk060LblKbn
     */
    public int getBmk060LblKbn() {
        return bmk060LblKbn__;
    }

    /**
     * <p>bmk060LblKbn をセットします。
     * @param bmk060LblKbn bmk060LblKbn
     */
    public void setBmk060LblKbn(int bmk060LblKbn) {
        bmk060LblKbn__ = bmk060LblKbn;
    }

    /**
     * <p>bmk060LeftLabelList を取得します。
     * @return bmk060LeftLabelList
     */
    public List<LabelValueBean> getBmk060LeftLabelList() {
        return bmk060LeftLabelList__;
    }

    /**
     * <p>bmk060LeftLabelList をセットします。
     * @param bmk060LeftLabelList bmk060LeftLabelList
     */
    public void setBmk060LeftLabelList(List<LabelValueBean> bmk060LeftLabelList) {
        bmk060LeftLabelList__ = bmk060LeftLabelList;
    }

    /**
     * <p>bmk060RightLabelList を取得します。
     * @return bmk060RightLabelList
     */
    public List<LabelValueBean> getBmk060RightLabelList() {
        return bmk060RightLabelList__;
    }

    /**
     * <p>bmk060RightLabelList をセットします。
     * @param bmk060RightLabelList bmk060RightLabelList
     */
    public void setBmk060RightLabelList(List<LabelValueBean> bmk060RightLabelList) {
        bmk060RightLabelList__ = bmk060RightLabelList;
    }

    /**
     * <p>bmk060SelectLeftLabel を取得します。
     * @return bmk060SelectLeftLabel
     */
    public String[] getBmk060SelectLeftLabel() {
        return bmk060SelectLeftLabel__;
    }

    /**
     * <p>bmk060SelectLeftLabel をセットします。
     * @param bmk060SelectLeftLabel bmk060SelectLeftLabel
     */
    public void setBmk060SelectLeftLabel(String[] bmk060SelectLeftLabel) {
        bmk060SelectLeftLabel__ = bmk060SelectLeftLabel;
    }

    /**
     * <p>bmk060SelectRightLabel を取得します。
     * @return bmk060SelectRightLabel
     */
    public String[] getBmk060SelectRightLabel() {
        return bmk060SelectRightLabel__;
    }

    /**
     * <p>bmk060SelectRightLabel をセットします。
     * @param bmk060SelectRightLabel bmk060SelectRightLabel
     */
    public void setBmk060SelectRightLabel(String[] bmk060SelectRightLabel) {
        bmk060SelectRightLabel__ = bmk060SelectRightLabel;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, int usrSid, HttpServletRequest req)
    throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String msg2 = "";

        if (bmk060LblKbn__ == GSConstBookmark.LABEL_TOGO_YES) {
            msg2 = gsMsg.getMessage(req, "bmk.67");
            //ラベル統合区分：する
            //未選択チェック
            if (bmk060LabelList__ == null || bmk060LabelList__.length == 0) {
                msg = new ActionMessage("error.select.required.text", msg2);
                StrutsUtil.addMessage(
                          errors, msg, "bmk060LabelList__");
            }
        }
        String editLabelName = null;
        if (this.getBmk050ProcMode() == GSConstBookmark.LABEL_MODE_EDIT) {
            //処理モード：編集モード
            BmkLabelDao dao = new BmkLabelDao(con);
            BmkLabelModel mdlEdit = dao.select(this.getBmk050LblSid());
            editLabelName = mdlEdit.getBlbName();
        }

        log__.debug("入力ラベル名：" + bmk060LblName__);

        //指定ラベル情報取得
        BmkLabelDataDao dao = new BmkLabelDataDao(con);
        ArrayList<BmkLabelDataModel> modelList
            = dao.select(this.getBmk010mode(), usrSid, this.getBmk010groupSid());

        String msg3 = "";
        String msg4 = "";
        //ラベル重複チェック
        for (BmkLabelDataModel mdl : modelList) {
            log__.debug("リストラベル名：" + mdl.getBlbName());

            if (bmk060LblName__.equals(mdl.getBlbName())
            && (editLabelName == null || !bmk060LblName__.equals(editLabelName))) {

                String eprefix = "bookmark";
                msg3 = gsMsg.getMessage(req, "cmn.label");
                String fieldfix = msg3 + ".";
                msg4 = gsMsg.getMessage(req, "cmn.label.name");
                msg = new ActionMessage("error.select.dup.list", msg4);
                StrutsUtil.addMessage(errors, msg, eprefix + fieldfix + "bmk060LblName__");
                break;
            }
        }

        String labelName = gsMsg.getMessage(req, "cmn.label.name");
        if (StringUtil.isNullZeroString(bmk060LblName__)) {
            //未入力はOK
            msg = new ActionMessage("error.input.required.text", labelName);
            StrutsUtil.addMessage(
                      errors, msg, "bmk060LblName__");
        } else if (ValidateUtil.isSpace(bmk060LblName__)) {
            //スペースのみチェック
            msg = new ActionMessage("error.input.spase.only", labelName);
            StrutsUtil.addMessage(errors, msg, "bmk060LblName__");
        } else if (ValidateUtil.isSpaceStart(bmk060LblName__)) {
            //先頭スペースチェック
            msg = new ActionMessage("error.input.spase.start", labelName);
            StrutsUtil.addMessage(errors, msg, "bmk060LblName__");
        } else if (ValidateUtil.isTab(bmk060LblName__)) {
            //タブ文字が含まれている
            msg = new ActionMessage("error.input.tab.text", labelName);
            StrutsUtil.addMessage(errors, msg, "bmk060LblName__");
        } else if (bmk060LblName__.length() > 20) {
            //MAX桁チェック
            msg = new ActionMessage(
                    "error.input.length.text", labelName, 20);
            StrutsUtil.addMessage(errors, msg, "bmk060LblName__");
        } else if (bmk060LblName__.indexOf(" ") > 0) {
            //半角スペースチェック
            msg = new ActionMessage(
                    "error.cantinput.space", labelName);
            StrutsUtil.addMessage(errors, msg, "bmk060LblName__");
        } else if (!GSValidateUtil.isGsJapaneaseString(bmk060LblName__)) {
            //JIS第2水準チェック
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(bmk060LblName__);
            msg = new ActionMessage("error.input.njapan.text", labelName, nstr);
            StrutsUtil.addMessage(errors, msg, "bmk060LblName__");
        }
        return errors;
    }
}
