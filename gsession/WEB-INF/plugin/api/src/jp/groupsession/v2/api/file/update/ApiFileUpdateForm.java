package jp.groupsession.v2.api.file.update;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.file.delete.ApiFileDeleteBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.GSValidateFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 * <br>[機  能] /file/updateのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileUpdateForm extends AbstractApiForm {

    /** ディレクトリSID */
    private String fdrSid__ = null;
    /** ファイル */
    private FormFile uploadFile__ = null;

    /** キャビネットSID(入力チェック用) */
    private int fcbSid__ = 0;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param umodel ユーザ情報モデル
     * @param gsMsg GsMessage
     * @param reqMdl RequestModel
     * @return エラー
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     */
    public ActionErrors validateFileUpdate(Connection con, BaseUserModel umodel,
            GsMessage gsMsg, RequestModel reqMdl)
    throws SQLException, IOToolsException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        ApiFileDeleteBiz fileDelBiz = new ApiFileDeleteBiz();


        if (StringUtil.isNullZeroString(fdrSid__)) {
            //未入力
            String textDirSid = gsMsg.getMessage("fil.111");
            msg = new ActionMessage("error.input.required.text", textDirSid);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!ValidateUtil.isNumber(fdrSid__)) {
            //数字チェック
            String textDirSid = gsMsg.getMessage("fil.111");
            msg = new ActionMessage(
                    "error.input.number.hankaku", textDirSid);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!__isExistFile(con)) {
            //ファイル存在チェック
            String textFile = gsMsg.getMessage("fil.92");
            msg = new ActionMessage(
                    "search.notfound.tdfkcode", textFile);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!__validateWrite(con, reqMdl)) {
            //キャビネットへの書込み権限なし
            String textEdit = gsMsg.getMessage("cmn.edit");
            String textCabinet = gsMsg.getMessage("fil.51");

            msg = new ActionMessage("error.edit.power.user",
                    textCabinet, textEdit);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (
                fileDelBiz.checkFileLock(
                        con, umodel.getUsrsid(), NullDefault.getInt(fdrSid__, 0))) {
            //ファイルロック
            msg = new ActionMessage("error.file.lock");
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else {
            //ファイルサイズ
            BigDecimal fileSize = new BigDecimal(uploadFile__.getFileSize());

            //ファイルサイズのチェック
            boolean errorFlg = GSValidateFile.validateFileSizeOver(
                                                errors, con, fileSize, reqMdl);

            if (!errorFlg) {
                //キャビネット容量チェック
                GSValidateFile.validateUsedDiskSizeOverValue(errors, fcbSid__, con, fileSize);

            }

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
        fcbSid__ = fileBiz.getCabinetSid(fdrSid, con);
        if (fcbSid__ <= 0) {
            return false;
        }

        return fileBiz.isDirAccessAuthUser(fcbSid__,
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
     * @return uploadFile
     */
    public FormFile getUploadFile() {
        return uploadFile__;
    }

    /**
     * @param uploadFile 設定する uploadFile
     */
    public void setUploadFile(FormFile uploadFile) {
        uploadFile__ = uploadFile;
    }

}
