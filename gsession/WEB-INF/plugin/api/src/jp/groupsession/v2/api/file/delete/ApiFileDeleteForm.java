package jp.groupsession.v2.api.file.delete;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSConstApi;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] /file/deleteのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileDeleteForm extends AbstractApiForm {

    /** ディレクトリSID（ファイル） */
    private String fdrSid__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param gsMsg GsMessage
     * @param reqMdl リクエストモデル
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateFileUpload(Connection con, GsMessage gsMsg,
                                            RequestModel reqMdl)
    throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
        ApiFileDeleteBiz biz = new ApiFileDeleteBiz();


        if (StringUtil.isNullZeroString(fdrSid__)) {
            //未入力
            msg = new ActionMessage("error.input.required.text",
                    gsMsg.getMessage(GSConstApi.TEXT_DIRECTORY_SID));
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!ValidateUtil.isNumber(fdrSid__)) {
            //数字チェック
            msg = new ActionMessage(
                    "error.input.number.hankaku",
                    gsMsg.getMessage(GSConstApi.TEXT_DIRECTORY_SID));
                StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!__isExistFile(con)) {
            //ファイル存在チェック
            msg = new ActionMessage(
                    "search.notfound.tdfkcode",
                    gsMsg.getMessage(GSConstApi.TEXT_SELECT_FILE));
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (biz.checkFileLock(
                con, reqMdl.getSmodel().getUsrsid(),
                NullDefault.getInt(fdrSid__, 0))) {
            //ファイルロック
            msg = new ActionMessage(
                    "error.file.lock.name",
                    gsMsg.getMessage(GSConstApi.TEXT_SELECT_FILE));
                StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else {
            int parentFdrSid = NullDefault.getInt(fdrSid__, -1);
            int cabinetSid = fileBiz.getCabinetSid(parentFdrSid, con);
            if (cabinetSid < 1 || !fileBiz.isDirAccessAuthUser(cabinetSid,
                    parentFdrSid,
                    reqMdl.getSmodel().getUsrsid(),
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                    con)) {
                //キャビネットへの書込み権限なし
                msg = new ActionMessage("error.cant.delete.filekanri.file");
                StrutsUtil.addMessage(errors, msg, "fdrSid.error.cant.delete.filekanri.file");
            }
        }
        return errors;
    }

    /**
     * <br>[機  能] ファイル存在するか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:存在する false:存在しない
     * @throws SQLException SQL実行時例外
     */
    private boolean __isExistFile(Connection con) throws SQLException {

        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel model = dao.getNewDirectory(NullDefault.getInt(fdrSid__, 0));
        if (model == null) {
            return false;
        }
        if (model.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
            return false;
        }

        return true;
    }

    /**
     * @return fdrSid
     */
    public String getFdrSid() {
        return fdrSid__;
    }

    /**
     * @param fdrSid 設定する fdrSid
     */
    public void setFdrSid(String fdrSid) {
        fdrSid__ = fdrSid;
    }

}
