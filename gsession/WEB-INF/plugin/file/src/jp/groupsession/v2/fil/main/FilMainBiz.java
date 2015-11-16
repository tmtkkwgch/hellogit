package jp.groupsession.v2.fil.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.dao.FileUconfDao;
import jp.groupsession.v2.fil.fil010.FileLinkSimpleModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileUconfModel;

/**
 * ファイル管理メイン画面で使用するビジネスロジッククラス
 * @author JTS
 */
public class FilMainBiz {

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * デフォルトコンストラクタ
     * @param reqMdl RequestModel
     */
    public FilMainBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramModel FilMainParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(FilMainParamModel paramModel, Connection con) throws SQLException {

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid();

        FileUconfDao uconfDao = new FileUconfDao(con);
        FileUconfModel model = uconfDao.select(sessionUsrSid);


        if (model == null || model.getFucMainOkini() == GSConstFile.MAIN_OKINI_DSP_ON) {
            //ショートカット一覧を取得
            paramModel.setShortcutList(getShortcutInfoList(sessionUsrSid, con));
        } else {
            paramModel.setShortcutList(null);
        }

        if (model == null || model.getFucMainCall() > 0) {
            //更新通知情報を取得
            paramModel.setCallList(getCallInfoList(sessionUsrSid, con));
        } else {
            paramModel.setCallList(null);
        }

    }

    /**
     * ショートカット一覧を取得します
     * @param userSid ユーザSID
     * @param con コネクション
     * @return ショートカット一覧
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<FileLinkSimpleModel> getShortcutInfoList(int userSid, Connection con)
    throws SQLException {
        ArrayList<FileLinkSimpleModel> ret = new ArrayList<FileLinkSimpleModel>();
        FileShortcutConfDao dao = new FileShortcutConfDao(con);
        //ショートカット情報を取得
        ArrayList<FileDirectoryModel> shortCutList = dao.getShortCutInfo(userSid);
        FilCommonBiz filCmnBiz = new FilCommonBiz(con, reqMdl__);
        FileLinkSimpleModel dspModel = null;
        String path = "";
        for (FileDirectoryModel bean : shortCutList) {
            path = filCmnBiz.getDirctoryPath(bean.getFdrSid(), con);
            dspModel = new FileLinkSimpleModel();
            dspModel.setDirectoryFullPathName(path);
            dspModel.setDirectoryKbn(bean.getFdrKbn());
            dspModel.setDirectoryName(bean.getFdrName());
            dspModel.setCabinetSid(bean.getFcbSid());
            dspModel.setDirectorySid(bean.getFdrSid());
            dspModel.setBinSid(bean.getBinSid());
            dspModel.setDirectoryUpdateStr(
                    UDateUtil.getSlashYYMD(bean.getFdrEdate())
                    + " "
                    + UDateUtil.getSeparateHMS(bean.getFdrEdate()));
            ret.add(dspModel);
        }
        return ret;
    }

    /**
     * 更新通知一覧を取得します
     * @param userSid ユーザSID
     * @param con コネクション
     * @return 更新通知一覧
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<FileLinkSimpleModel> getCallInfoList(int userSid, Connection con)
    throws SQLException {
        ArrayList<FileLinkSimpleModel> ret = new ArrayList<FileLinkSimpleModel>();
        FileCallDataDao dao = new FileCallDataDao(con);

        //個人設定を取得
        FileUconfDao uconfDao = new FileUconfDao(con);
        FileUconfModel uconfModel = uconfDao.select(userSid);
        int limit = 0;
        if (uconfModel == null) {
            limit = GSConstFile.CALL_DSP_CNT;
        } else {
            limit = uconfModel.getFucMainCall();
        }
        int offset = 1;

        //更新通知情報を取得
        ArrayList<FileDirectoryModel> updateList = dao.getUpdateCallData(userSid, offset, limit);
        FilCommonBiz filCmnBiz = new FilCommonBiz(con, reqMdl__);
        FileLinkSimpleModel dspModel = null;
        String path = "";
        for (FileDirectoryModel bean : updateList) {
            path = filCmnBiz.getDirctoryPath(bean.getFdrSid(), con);
            dspModel = new FileLinkSimpleModel();
            dspModel.setDirectoryFullPathName(path);
            dspModel.setDirectoryKbn(bean.getFdrKbn());
            dspModel.setDirectoryName(bean.getFdrName());
            dspModel.setCabinetSid(bean.getFcbSid());
            dspModel.setDirectorySid(bean.getFdrSid());
            dspModel.setDirectoryUpdateStr(
                    UDateUtil.getSlashYYMD(bean.getFdrEdate())
                    + " "
                    + UDateUtil.getSeparateHMS(bean.getFdrEdate()));
            dspModel.setFcbMark(bean.getFcbMark());
            ret.add(dspModel);
        }
        return ret;
    }
}
