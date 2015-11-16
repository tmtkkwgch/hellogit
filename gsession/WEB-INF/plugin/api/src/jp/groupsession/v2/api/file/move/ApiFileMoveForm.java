package jp.groupsession.v2.api.file.move;

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
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.fil090.Fil090Biz;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] /file/moveのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileMoveForm extends AbstractApiForm {

    /** ディレクトリSID */
    private String fdrSid__ = null;
    /** ファイル名 */
    private String fdrParentSid__ = null;

    /** キャビネットSID(入力チェック用) */
    private int fcbSid__ = 0;
    /** ファイル区分(入力チェック用) */
    private int fdrKbn__ = 0;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param gsMsg GsMessage
     * @param umodel ユーザ情報モデル
     * @param reqMdl RequestModel
     * @return エラー
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     */
    public ActionErrors validateFileMove(Connection con, GsMessage gsMsg,
                                        BaseUserModel umodel, RequestModel reqMdl)
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

        } else if (StringUtil.isNullZeroString(fdrParentSid__)) {
            //未入力
            msg = new ActionMessage(
                    "error.input.required.text", gsMsg.getMessage("fil.75"));
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (!ValidateUtil.isNumber(fdrParentSid__)) {
            //数字チェック
            msg = new ActionMessage(
                "error.input.number.hankaku", gsMsg.getMessage("fil.75"));
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (!__isExistFile(con)) {
            //ファイル存在チェック
            msg = new ActionMessage(
                    "search.notfound.tdfkcode",
                    gsMsg.getMessage(GSConstApi.TEXT_SELECT_FOLDER));
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (fdrKbn__ == GSConstFile.DIRECTORY_FILE
                && fileDelBiz.checkFileLock(
                        con, umodel.getUsrsid(), NullDefault.getInt(fdrSid__, 0))) {
                //ファイルロック
                msg = new ActionMessage("error.file.lock");
                StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!__validateSameCabinet(con)) {
            //移動先が違うキャビネットの場合
            msg = new ActionMessage("error.select.required.text", gsMsg.getMessage("fil.75"));
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (!__isFolder(con)) {
            //移動先フォルダ存在チェック
            msg = new ActionMessage("error.select.required.text", gsMsg.getMessage("fil.75"));
            StrutsUtil.addMessage(errors, msg, "fdrParentSid");

        } else if (!__validateWriteTo(con, reqMdl)) {
            //キャビネットへの書込み権限なし
            msg = new ActionMessage("error.cant.edit.filekanri.file");
            StrutsUtil.addMessage(errors, msg, "fdrSid.error.cant.edit.filekanri.file");

        } else if (isOverLevel(con, reqMdl)) {
            //移動後ディレクトリ11階層以上
            msg = new ActionMessage("error.over.level.dir", GSConstFile.MAX_LEVEL);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        }

        return errors;
    }
    /**
     * <br>[機  能] ユーザが移動先ディレクトリへの書込み権限を持っているか判定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @return true:権限有り false:権限無し
     * @throws NumberFormatException
     * @throws SQLException SQL実行時例外
     */
    private boolean __validateWriteTo(Connection con, RequestModel reqMdl) throws SQLException {
        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
        int fdrSid = NullDefault.getInt(fdrParentSid__, -1);

        return fileBiz.isDirAccessAuthUser(fcbSid__,
                fdrSid,
                reqMdl.getSmodel().getUsrsid(),
                Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                con);
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
     * <br>[機  能] 同じキャビネットへ移動するかを判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:同じキャビネット false:エラー
     * @throws SQLException SQL実行時例外
     */
    private boolean __validateSameCabinet(Connection con)
    throws SQLException {

        FilCommonBiz fileBiz = new FilCommonBiz();
        int cabinetSid = fileBiz.getCabinetSid(NullDefault.getInt(fdrParentSid__, -1), con);
        if (cabinetSid < 1) {
            return false;
        }
        if (cabinetSid == fcbSid__) {
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] フォルダが存在するか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:フォルダ false:エラー
     * @throws SQLException SQL実行時例外
     */
    private boolean __isFolder(Connection con) throws SQLException {

        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel model = dao.getNewDirectory(NullDefault.getInt(fdrParentSid__, 0));
        if (model == null) {
            return false;
        }
        if (model.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
            return true;
        }
        return false;
    }

    /**
     * <br>[機  能] ファイルが存在するか判定する。
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

        fdrKbn__ = model.getFdrKbn();
        return true;
    }

    /**
     * <br>[機  能] ディレクトリ移動後に11階層以上になるか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     * @return true:11階層以上　false:10階層以下
     * @throws SQLException SQL実行例外
     */
    public boolean isOverLevel(Connection con, RequestModel reqMdl) throws SQLException {
        boolean ret = false;

        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        Fil090Biz biz = new Fil090Biz(con, reqMdl);
        int dirLevel = 0;
        int sleDirLevel = 0;

        //移動するディレクトリの下階層数を取得する。
        dirLevel = biz.getMaxLevel(NullDefault.getInt(fdrSid__, -1));

        //移動先ディレクトリの階層数を取得する。
        FileDirectoryModel dirModel = dirDao.getNewDirectory(
                NullDefault.getInt(getFdrParentSid(), -1));
        if (dirModel != null) {
            sleDirLevel = dirModel.getFdrLevel();
        }

        if ((sleDirLevel + dirLevel) > GSConstFile.MAX_LEVEL) {
            ret = true;
        }
        return ret;
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

    /**
     * <p>fdrParentSid を取得します。
     * @return fdrParentSid
     */
    public String getFdrParentSid() {
        return fdrParentSid__;
    }

    /**
     * <p>fdrParentSid をセットします。
     * @param fdrParentSid fdrParentSid
     */
    public void setFdrParentSid(String fdrParentSid) {
        fdrParentSid__ = fdrParentSid;
    }
}
