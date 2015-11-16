package jp.groupsession.v2.api.file.delete;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.fil040.Fil040Biz;
import jp.groupsession.v2.fil.model.FileFileBinModel;

/**
 * <br>[機  能] ファイルの削除を行うWEBAPIビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileDeleteBiz {

    /**
     * <p>Set Connection
     */
    public ApiFileDeleteBiz() {
    }

    /**
     * <br>[機  能] ファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @param fdrSid 削除対象SID
     * @param usrSid ユーザSID
     * @return fileName ファイル名
     * @throws SQLException SQL実行時例外
     */
    public String deleteFile(
            RequestModel reqMdl,
            Connection con,
            int fdrSid,
            int usrSid)
    throws SQLException {

        Fil040Biz fil040biz = new Fil040Biz(reqMdl);

        String[] delSids = new String[1];
        delSids[0] = String.valueOf(fdrSid);


        FilCommonBiz filBiz = new FilCommonBiz();
        int fcbSid = filBiz.getCabinetSid(fdrSid, con);

        List<String> retList =  fil040biz.deleteDirectory(delSids, fcbSid, fdrSid, con);
        String ret = "";
        if (retList.size() > 0) {
            ret = retList.get(0);
        }
        return ret;
    }

    /**
     * <br>[機  能] ファイルがロックされているか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param fdrSid ディレクトリSID
     * @throws SQLException 実行例外
     * @return rockFlg ロックフラグ true:ロックされている false:ロックされていない
     */
    public boolean checkFileLock(
        Connection con,
        int usrSid,
        int fdrSid) throws SQLException {

        boolean lockFlg = true;
        FilCommonBiz filBiz = new FilCommonBiz();

        //管理者設定ロック区分を取得する。
        int admLockKbn = filBiz.getLockKbnAdmin(con);
        if (admLockKbn == GSConstFile.LOCK_KBN_OFF) {
            //ロック機能が無効の場合
            return false;
        }

        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        FileFileBinModel fileBinModel = fileBinDao.getNewFile(fdrSid);

        if (fileBinModel == null) {
            //ディレクトリ情報が存在しない場合
            return lockFlg;
        }

        //ログインユーザ以外がロックしている場合
        if (fileBinModel.getFflLockKbn() == GSConstFile.LOCK_KBN_ON
                && fileBinModel.getFflLockUser() != usrSid) {
            //編集ユーザがログインユーザと異なった場合
            return lockFlg;
        }

        return false;
    }
}