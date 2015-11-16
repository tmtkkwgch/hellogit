package jp.groupsession.v2.rng.rng140;

import java.sql.Connection;
import java.util.ArrayList;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngValidate;
import jp.groupsession.v2.rng.rng060.Rng060Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 稟議 テンプレートカテゴリ登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng140Form extends Rng060Form {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng140Form.class);
    //入力項目
    /** カテゴリ名 */
    private String rng140CatName__ = "";

    /** カテゴリSID */
    private int rng140CatSid__ = 0;
    /** 状態区分 0=登録  1=編集 */
    private int rng140ProcMode__ = 0;
    /** 遷移元フラグ */
    private int rng140SeniFlg__ = 0;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateRng140(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("cmn.category.name");

        //カテゴリ名
        errors = RngValidate.validateCmnFieldText(errors,
                                                  msg,
                                                  rng140CatName__,
                                                  "rng140CatName__",
                                                  20,
                                                  true);
        return errors;
    }

    /**
     * <br>[機  能] 削除チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @return エラー
     * @throws Exception 例外発生
     */
    public ActionErrors deleteCheck(Connection con,
                                    int userSid,
                                    RequestModel reqMdl) throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        String fieldfix;

        Rng140ParamModel paramMdl = new Rng140ParamModel();
        paramMdl.setParam(this);
        Rng140Biz biz = new Rng140Biz(reqMdl);
        ArrayList<String> list = biz.getDeleteTmpList(paramMdl, con, userSid);
        paramMdl.setFormData(this);

        //カテゴリにテンプレートなし
        if (list.isEmpty()) {
            return errors;
        }

        log__.debug("エラー有り");
        log__.debug("rng140CatSid == " + rng140CatSid__);
        fieldfix = "tmpBelongCat" + ".";
        String msgKey = "error.ringi.delete.template.category";
        msg = new ActionMessage(msgKey);
        errors.add(fieldfix, msg);
        return errors;
    }

    /**
     * @return rng140CatName
     */
    public String getRng140CatName() {
        return rng140CatName__;
    }

    /**
     * @param rng140CatName セットする rng140CatName
     */
    public void setRng140CatName(String rng140CatName) {
        rng140CatName__ = rng140CatName;
    }

    /**
     * @return rng140CatSid
     */
    public int getRng140CatSid() {
        return rng140CatSid__;
    }

    /**
     * @param rng140CatSid セットする rng140CatSid
     */
    public void setRng140CatSid(int rng140CatSid) {
        rng140CatSid__ = rng140CatSid;
    }

    /**
     * @return rng140ProcMode
     */
    public int getRng140ProcMode() {
        return rng140ProcMode__;
    }

    /**
     * @param rng140ProcMode 設定する rng140ProcMode
     */
    public void setRng140ProcMode(int rng140ProcMode) {
        rng140ProcMode__ = rng140ProcMode;
    }

    /**
     * @return rng140SeniFlg
     */
    public int getRng140SeniFlg() {
        return rng140SeniFlg__;
    }

    /**
     * @param rng140SeniFlg 設定する rng140SeniFlg
     */
    public void setRng140SeniFlg(int rng140SeniFlg) {
        rng140SeniFlg__ = rng140SeniFlg;
    }
}
