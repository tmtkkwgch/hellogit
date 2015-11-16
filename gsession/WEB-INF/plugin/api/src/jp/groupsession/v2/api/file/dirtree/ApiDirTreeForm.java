package jp.groupsession.v2.api.file.dirtree;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSConstApi;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] /file/dirtreeのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiDirTreeForm extends AbstractApiForm {

    /** キャビネットSID */
    private String fcbSid__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param gsMsg GsMessage
     * @param umodel ユーザ情報モデル
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateDirTree(Connection con, GsMessage gsMsg,
                                        BaseUserModel umodel)
    throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        FilCommonBiz fileBiz = new FilCommonBiz();

        if (StringUtil.isNullZeroString(fcbSid__)) {
            //未入力
            msg = new ActionMessage("error.input.required.text",
                    gsMsg.getMessage(GSConstApi.TEXT_CABINET_SID));
            StrutsUtil.addMessage(errors, msg, "fcbSid");

        } else if (!ValidateUtil.isNumber(fcbSid__)) {
            //数字チェック
            msg = new ActionMessage("error.input.number.hankaku",
                    gsMsg.getMessage(GSConstApi.TEXT_CABINET_SID));
                StrutsUtil.addMessage(errors, msg, "fcbSid");

        } else if (!fileBiz.isAccessAuthUser(Integer.parseInt(fcbSid__), con, umodel)) {
            //キャビネットへのアクセス権限なし
            msg = new ActionMessage("error.not.view.cabinet");
                StrutsUtil.addMessage(errors, msg, "fcbSid");
        }

         return errors;
    }

    /**
     * @return fcbSid
     */
    public String getFcbSid() {
        return fcbSid__;
    }

    /**
     * @param fcbSid 設定する fcbSid
     */
    public void setFcbSid(String fcbSid) {
        this.fcbSid__ = fcbSid;
    }

}
