package jp.groupsession.v2.api.file.unlock;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] /file/unlockのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileUnlockForm extends AbstractApiForm {

    /** ディレクトリSID */
    private String fdrSid__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @param gsMsg GsMessage
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateFileLock(
            Connection con, RequestModel reqMdl, GsMessage gsMsg)
    throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        if (StringUtil.isNullZeroString(fdrSid__)) {
            String textDirSid = gsMsg.getMessage("fil.111");
            //未入力
            msg = new ActionMessage("error.input.required.text", textDirSid);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!ValidateUtil.isNumber(fdrSid__)) {
            String textDirSid = gsMsg.getMessage("fil.111");
            //数字チェック
            msg = new ActionMessage(
                    "error.input.number.hankaku", textDirSid);
                StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!__isExistFile(con)) {
            String textFile = gsMsg.getMessage("fil.92");
            //ファイル存在チェック
            msg = new ActionMessage(
                    "search.notfound.tdfkcode", textFile);
            StrutsUtil.addMessage(errors, msg, "fdrSid");

        } else if (!__isCanFileUnlockUser(con, reqMdl)) {
            //ファイルロック解除権限無し
            String textFile = gsMsg.getMessage("fil.48");
            msg = new ActionMessage("error.edit.power.user", textFile, textFile);
                StrutsUtil.addMessage(errors, msg, "fdrSid");
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
     * ユーザがキャビネット内のファイルロック解除が可能か判定します。
     * システム管理者、管理者権限を持つユーザは権限有り
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    private boolean __isCanFileUnlockUser(Connection con, RequestModel reqMdl)
    throws SQLException {

        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
        int fdrSid = NullDefault.getInt(fdrSid__, -1);
        int fcbSid = fileBiz.getCabinetSid(fdrSid, con);

        if (fileBiz.isCanFileUnlockUser(fcbSid, con)) {
            return true;
        }

        //ユーザがファイルロック解除可能かを判定
        return fileBiz.checkFileLock(fdrSid, reqMdl.getSmodel().getUsrsid(), con);
    }

    /**
     * <p>fdrSid を取得します。
     * @return fdrSid
     */
    public String getFdrSid() {
        return fdrSid__;
    }

    /**
     * <p>fdrSid をセットします。
     * @param fdrSid fdrSid
     */
    public void setFdrSid(String fdrSid) {
        fdrSid__ = fdrSid;
    }
}
