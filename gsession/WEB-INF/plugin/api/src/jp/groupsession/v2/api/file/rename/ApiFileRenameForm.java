package jp.groupsession.v2.api.file.rename;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSConstApi;
import jp.groupsession.v2.api.file.delete.ApiFileDeleteBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.util.FilValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] /file/renameのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileRenameForm extends AbstractApiForm {

    /** ディレクトリSID */
    private String fdrSid__ = null;
    /** ファイル名 */
    private String fdrName__ = null;

    /** キャビネットSID(入力チェック用) */
    private int fcbSid__ = 0;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param gsMsg GsMessage
     * @param reqMdl リクエストモデル
     * @return エラー
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     */
    public ActionErrors validateFileRename(Connection con, GsMessage gsMsg,
                                            RequestModel reqMdl)
    throws SQLException, IOToolsException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        ApiFileDeleteBiz fileDelBiz = new ApiFileDeleteBiz();


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

        } else if (!__validateWrite(con, reqMdl)) {
            //キャビネットへの書込み権限なし
            msg = new ActionMessage("error.cant.edit.filekanri.file");
            StrutsUtil.addMessage(errors, msg, "fdrSid.error.cant.edit.filekanri.file");

        } else if (
                fileDelBiz.checkFileLock(
                        con, reqMdl.getSmodel().getUsrsid(), NullDefault.getInt(fdrSid__, 0))) {
            //ファイルロック
            msg = new ActionMessage("error.file.lock");
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (fdrName__ == null || fdrName__.equals("")) {
            //ファイル名チェック
            FilValidateUtil.validateTextField(errors, fdrName__, "fdrName",
                    gsMsg.getMessage("fil.9"),
                    GSConstFile.MAX_LENGTH_NAME, true);
        }

        return errors;
    }

    /**
     * <br>[機  能] ユーザがディレクトリへの書込み権限を持っているか判定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    private boolean __validateWrite(Connection con, RequestModel reqMdl)
    throws SQLException {

        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
        int fdrSid = NullDefault.getInt(fdrSid__, -1);
        int fcbSid = fileBiz.getCabinetSid(fdrSid, con);
        if (fcbSid <= 0) {
            return false;
        }

        return fileBiz.isDirAccessAuthUser(fcbSid,
                fdrSid,
                reqMdl.getSmodel().getUsrsid(),
                Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                con);
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

    /**
     * <p>fdrName を取得します。
     * @return fdrName
     */
    public String getFdrName() {
        return fdrName__;
    }

    /**
     * <p>fdrName をセットします。
     * @param fdrName fdrName
     */
    public void setFdrName(String fdrName) {
        fdrName__ = fdrName;
    }

    /**
     * <p>fcbSid を取得します。
     * @return fcbSid
     */
    public int getFcbSid() {
        return fcbSid__;
    }

    /**
     * <p>fcbSid をセットします。
     * @param fcbSid fcbSid
     */
    public void setFcbSid(int fcbSid) {
        fcbSid__ = fcbSid;
    }


}
