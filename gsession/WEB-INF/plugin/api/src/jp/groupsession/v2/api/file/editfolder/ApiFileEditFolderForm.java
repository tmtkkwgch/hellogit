package jp.groupsession.v2.api.file.editfolder;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.GSValidateFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] /file/addfolderのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileEditFolderForm extends AbstractApiForm {

    /** ディレクトリSID（編集するフォルダ） */
    private String fdrSid__ = null;
    /** フォルダ名 */
    private String fdrName__ = null;
    /** 備考 */
    private String fdrNote__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @param gsMsg GsMessage
     * @return エラー
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     */
    public ActionErrors validateFileUpload(Connection con, RequestModel reqMdl,
            GsMessage gsMsg)
    throws SQLException, IOToolsException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);

        if (StringUtil.isNullZeroString(fdrSid__)) {
            String textDirSid = gsMsg.getMessage("fil.111");
            //未入力
            msg = new ActionMessage(
                    "error.input.required.text", textDirSid);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!ValidateUtil.isNumber(fdrSid__)) {
            //数字チェック
            String textDirSid = gsMsg.getMessage("fil.111");
            msg = new ActionMessage(
                    "error.input.number.hankaku", textDirSid);
            StrutsUtil.addMessage(errors, msg, "fdrParentSid.error.input.number.hankaku");

        } else if (__checkDirData(con, errors, gsMsg)) {

            //キャビネットSIDを取得
            int parentFdrSid = NullDefault.getInt(fdrSid__, -1);
            int fcbSid = fileBiz.getCabinetSid(parentFdrSid, con);

            if (fcbSid < 1 || !fileBiz.isDirAccessAuthUser(fcbSid,
                    parentFdrSid,
                    reqMdl.getSmodel().getUsrsid(),
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                    con)) {
                //ディレクトリへの書込み権限なし
                msg = new ActionMessage("error.edit.power.user",
                        gsMsg.getMessage("cmn.edit.folder"), gsMsg.getMessage("fil.123"));
                StrutsUtil.addMessage(errors, msg, "fdrParentSid.error.edit.power.user");
            }

        }
        String textDirName = gsMsg.getMessage("fil.21");
        String textBiko = gsMsg.getMessage("cmn.memo");


        //フォルダ名
        GSValidateFile.validateTextBoxInput(errors,
                                         fdrName__,
                                         textDirName,
                                         GSConstFile.MAX_LENGTH_FOLDER_NAME,
                                         true);

        //備考
        GSValidateFile.validateTextarea(errors,
                                         fdrNote__,
                                         textBiko,
                                         GSConstFile.MAX_LENGTH_FOLDER_BIKO,
                                         false);

        return errors;
    }

    /**
     * <br>[機  能] フォルダの存在チェック、階層チェックを行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param errors ActionErrors
     * @param gsMsg GsMessage
     * @return true:正常 false:エラーあり
     * @throws SQLException SQL実行時例外
     */
    private boolean __checkDirData(Connection con, ActionErrors errors,
            GsMessage gsMsg)
    throws SQLException {

        ActionMessage msg = null;
        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel model = dao.getNewDirectory(NullDefault.getInt(fdrSid__, 0));
        String textDirSid = gsMsg.getMessage("fil.110");

        int errorCnt = errors.size();
        if (model == null || model.getFdrKbn() == GSConstFile.DIRECTORY_FILE) {
            //ディレクトリ存在チェック
            msg = new ActionMessage(
                    "search.notfound.tdfkcode", textDirSid);
            StrutsUtil.addMessage(errors, msg, "fdrParentSid.search.notfound.tdfkcode");

        } else if (model.getFdrLevel() > GSConstFile.DIRECTORY_LEVEL_10) {
            //ディレクトリ階層チェック
            msg = new ActionMessage(
                    "error.over.level.create.dir", GSConstFile.DIRECTORY_LEVEL_10);
            StrutsUtil.addMessage(errors, msg, "fdrParentSid.error.over.level.create.dir");
        }

        return errorCnt == errors.size();
    }

    /**
     * @return fdrSid
     */
    public String getFdrSid() {
        return fdrSid__;
    }

    /**
     * @param fdrSid セットする fdrSid
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
     * <p>fdrNote を取得します。
     * @return fdrNote
     */
    public String getFdrNote() {
        return fdrNote__;
    }

    /**
     * <p>fdrNote をセットします。
     * @param fdrNote fdrNote
     */
    public void setFdrNote(String fdrNote) {
        fdrNote__ = fdrNote;
    }


}
