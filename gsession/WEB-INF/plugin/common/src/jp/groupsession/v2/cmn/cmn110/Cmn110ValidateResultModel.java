package jp.groupsession.v2.cmn.cmn110;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.model.AbstractModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ファイル添付ポップアップの入力チェック結果を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn110ValidateResultModel extends AbstractModel {
    /** ActionErrors */
    private ActionErrors errors__ = null;
    /** エラーメッセージ一覧 */
    private List<String> errMessageList__ = null;
    /**
     * <p>コンストラクタ
     */
    public Cmn110ValidateResultModel() {
        errors__ = new ActionErrors();
        errMessageList__ = new ArrayList<String>();
    }
    /**
     * <p>errMessageList を取得します。
     * @return errMessageList
     */
    public List<String> getErrMessageList() {
        return errMessageList__;
    }
    /**
     * <p>errMessageList をセットします。
     * @param errMessageList errMessageList
     */
    public void setErrMessageList(List<String> errMessageList) {
        errMessageList__ = errMessageList;
    }
    /**
     * <p>errors を取得します。
     * @return errors
     */
    public ActionErrors getErrors() {
        return errors__;
    }
    /**
     * <p>errors をセットします。
     * @param errors errors
     */
    public void setErrors(ActionErrors errors) {
        errors__ = errors;
    }

    /**
     * <br>[機  能] 入力エラーの追加を行なう
     * <br>[解  説]
     * <br>[備  考]
     * @param key キー
     * @param msg ActionMessage
     */
    public void addError(String key, ActionMessage msg) {
        errors__.add(key, msg);
    }
    /**
     * <br>[機  能] エラーメッセージの追加を行なう
     * <br>[解  説]
     * <br>[備  考]
     * @param message エラーメッセージ
     */
    public void addErrMessage(String message) {
        errMessageList__.add(message);
    }

    /**
     * <br>[機  能] 入力エラー、エラーメッセージの追加を行なう
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param key キー
     * @param msgKey エラーメッセージキー
     * @param fileNo ファイル番号
     */
    public void addErrors(HttpServletRequest req, String key, String msgKey, int fileNo) {
        addErrors(req, key, msgKey, fileNo, null);
    }

    /**
     * <br>[機  能] 入力エラー、エラーメッセージの追加を行なう
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param key キー
     * @param msgKey エラーメッセージキー
     * @param fileNo ファイル番号
     * @param msgParams メッセージパラメータ
     */
    public void addErrors(HttpServletRequest req, String key, String msgKey, int fileNo,
                        String[] msgParams) {

        ActionMessage msg = null;
        String errMessage = null;
        GsMessage gsMsg = new GsMessage();

        if (msgParams != null && msgParams.length > 0) {
            msg = new ActionMessage(key, msgParams);
            errMessage = gsMsg.getMessage(req, msgKey, msgParams);
        } else {
            msg = new ActionMessage(key);
            errMessage = gsMsg.getMessage(req, msgKey);
        }

        addError("file" + fileNo + "." + key, msg);
        addErrMessage(errMessage);
    }
}