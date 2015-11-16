package jp.groupsession.v2.api.file.lock;

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
 * <br>[機  能] /file/lockのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileLockForm extends AbstractApiForm {

    /** ディレクトリSID */
    private String fdrSid__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @param gsMsg GsMessage リクエスト
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

        } else if (!__isWriteAuthUser(reqMdl, con)) {
            //キャビネットへの書込み権限なし
            msg = new ActionMessage("error.cant.lock.filekanri.file");
            StrutsUtil.addMessage(errors, msg, "fdrSid.error.cant.lock.filekanri.file");
        }
        return errors;
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
        if (model.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
            return false;
        }

        return true;
    }

    /**
     * ユーザがディレクトリへの書込み権限を持っているか判定します。
     * 書込み権限があればtrue
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    private boolean __isWriteAuthUser(RequestModel reqMdl, Connection con)
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
