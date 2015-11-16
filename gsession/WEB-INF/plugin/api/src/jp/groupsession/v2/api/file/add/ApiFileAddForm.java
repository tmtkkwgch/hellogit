package jp.groupsession.v2.api.file.add;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
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
 * <br>[機  能] /file/addのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileAddForm extends AbstractApiForm {

    /** 親ディレクトリSID */
    private String fdrParentSid__ = null;
    /** ファイル */
    private FormFile uploadFile__ = null;

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
    public ActionErrors validateFileUpload(
            Connection con, BaseUserModel umodel, GsMessage gsMsg,
            RequestModel reqMdl)
    throws SQLException, IOToolsException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
        if (StringUtil.isNullZeroString(fdrParentSid__)) {
            //未入力
            String textParDir = gsMsg.getMessage("fil.109");
            msg = new ActionMessage(
                    "error.input.required.text", textParDir);
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (!ValidateUtil.isNumber(fdrParentSid__)) {
            //数字チェック
            String textParDir = gsMsg.getMessage("fil.109");
            msg = new ActionMessage(
                    "error.input.number.hankaku", textParDir);
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (!__isExistDir(con)) {
            //ディレクトリ存在チェック
            String textSelectedFile = gsMsg.getMessage("fil.109");
            msg = new ActionMessage(
                    "search.notfound.tdfkcode", textSelectedFile);
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else {

            int errorSize = errors.size();
            //キャビネットSIDを取得
            int parentFdrSid = NullDefault.getInt(fdrParentSid__, -1);
            int fcbSid = fileBiz.getCabinetSid(parentFdrSid, con);

            //ファイルサイズ
            BigDecimal fileSize = new BigDecimal(uploadFile__.getFileSize());

            //ファイルサイズのチェック
            GSValidateFile.validateFileSizeOver(errors, con, fileSize, reqMdl);

            if (errors.size() == errorSize) {
                //キャビネット添付ファイルサイズチェック
                GSValidateFile.validateUsedDiskSizeOverValue(errors, fcbSid, con, fileSize);
            }
            if (errors.size() == errorSize) {
                if (fcbSid < 1 || !fileBiz.isDirAccessAuthUser(fcbSid,
                        parentFdrSid,
                        umodel.getUsrsid(),
                        Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                        con)) {
                    //キャビネットへの書込み権限なし
                    String textEditCabinet = gsMsg.getMessage("cmn.edit.folder");
                    String textAdd = gsMsg.getMessage("cmn.add");
                    msg = new ActionMessage("error.edit.power.user",
                            textEditCabinet, textAdd);
                    StrutsUtil.addMessage(errors, msg, "fdrParentSid");
                }
            }

        }

        return errors;
    }

    /**
     * <br>[機  能] フォルダ存在するか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:存在する false:存在しない
     * @throws SQLException SQL実行時例外
     */
    private boolean __isExistDir(Connection con) throws SQLException {

        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel model = dao.getNewDirectory(NullDefault.getInt(fdrParentSid__, 0));
        if (model == null) {
            return false;
        }
        if (model.getFdrKbn() == GSConstFile.DIRECTORY_FILE) {
            return false;
        }

        return true;
    }

    /**
     * @return fdrParentSid
     */
    public String getFdrParentSid() {
        return fdrParentSid__;
    }

    /**
     * @param fdrParentSid 設定する fdrParentSid
     */
    public void setFdrParentSid(String fdrParentSid) {
        fdrParentSid__ = fdrParentSid;
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
