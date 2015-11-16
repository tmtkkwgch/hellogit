package jp.groupsession.v2.fil.fil040;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.fil030.Fil030ParamModel;
import jp.groupsession.v2.fil.fil090.Fil090Biz;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] フォルダ情報一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil040ParamModel extends Fil030ParamModel {


    /** 全て選択・解除用 */
    private String fil040SelectDelAll__ = null;
    /** 削除用チェックボックス */
    private String[] fil040SelectDel__;
    /** 移動先ディレクトリ */
    private String moveToDir__ = null;
    /** Unlock用ディレクトリSID */
    private String fil040SelectUnlock__ = null;
    /** Unlock用ディレクトリバージョン */
    private String fil040SelectUnlockVer__ = null;
    /** ソートキー */
    private int fil040SortKey__ = GSConstFile.SORT_NAME;
    /** オーダーキー */
    private int fil040OrderKey__ = GSConst.ORDER_KEY_ASC;

    //表示用
    /** キャビネット名称 */
    private String fil040CabinetName__ = null;
    /** ディレクトリパス情報(タイトル表示用) */
    private ArrayList<FileDirectoryModel> fil040DirectoryPathList__ = null;

    /** カレントディレクトリ情報一覧 */
    private ArrayList<FileDirectoryDspModel> fil040DirectoryList__ = null;
    /** ロック解除権限有無 */
    private String fil040UnLockAuth__ = GSConstFile.UNLOCK_AUTH_OFF;
    /** 追加/削除ボタン有無 */
    private String fil040DspAddBtn__ = GSConstFile.DSP_KBN_OFF;
    /** フォルダ追加ボタン有無 */
    private String fil040DspFolderAddBtn__ = GSConstFile.DSP_KBN_OFF;
    /** URL文字列 */
    private String fil040UrlString__;

    /** キャビネットコンボ選択値 */
    private String fil040SelectCabinet__ = null;
    /** キャビネットコンボ */
    private List<LabelValueBean> fil040CabinetList__ = null;

    /** 一括移動区分 */
    private int fil090SelectPluralKbn__ = Fil090Biz.MOVE_PLURAL_NO;

    /** 全て選択・解除用有無 */
    private String fil040DspSelectDelAll__ = "0";
    /**
     * <p>fil040DspFolderAddBtn を取得します。
     * @return fil040DspFolderAddBtn
     */
    public String getFil040DspFolderAddBtn() {
        return fil040DspFolderAddBtn__;
    }
    /**
     * <p>fil040DspFolderAddBtn をセットします。
     * @param fil040DspFolderAddBtn fil040DspFolderAddBtn
     */
    public void setFil040DspFolderAddBtn(String fil040DspFolderAddBtn) {
        fil040DspFolderAddBtn__ = fil040DspFolderAddBtn;
    }
    /**
     * @return fil040DspAddBtn
     */
    public String getFil040DspAddBtn() {
        return fil040DspAddBtn__;
    }
    /**
     * @param fil040DspAddBtn 設定する fil040DspAddBtn
     */
    public void setFil040DspAddBtn(String fil040DspAddBtn) {
        fil040DspAddBtn__ = fil040DspAddBtn;
    }
    /**
     * @return moveToDir
     */
    public String getMoveToDir() {
        return moveToDir__;
    }
    /**
     * @param moveToDir 設定する moveToDir
     */
    public void setMoveToDir(String moveToDir) {
        moveToDir__ = moveToDir;
    }
    /**
     * @return fil040CabinetName
     */
    public String getFil040CabinetName() {
        return fil040CabinetName__;
    }
    /**
     * @param fil040CabinetName 設定する fil040CabinetName
     */
    public void setFil040CabinetName(String fil040CabinetName) {
        fil040CabinetName__ = fil040CabinetName;
    }
    /**
     * @return fil040DirectoryPathList
     */
    public ArrayList<FileDirectoryModel> getFil040DirectoryPathList() {
        return fil040DirectoryPathList__;
    }
    /**
     * @param fil040DirectoryPathList 設定する fil040DirectoryPathList
     */
    public void setFil040DirectoryPathList(
            ArrayList<FileDirectoryModel> fil040DirectoryPathList) {
        fil040DirectoryPathList__ = fil040DirectoryPathList;
    }
    /**
     * @return fil040OrderKey
     */
    public int getFil040OrderKey() {
        return fil040OrderKey__;
    }
    /**
     * @param fil040OrderKey 設定する fil040OrderKey
     */
    public void setFil040OrderKey(int fil040OrderKey) {
        fil040OrderKey__ = fil040OrderKey;
    }
    /**
     * @return fil040SortKey
     */
    public int getFil040SortKey() {
        return fil040SortKey__;
    }
    /**
     * @param fil040SortKey 設定する fil040SortKey
     */
    public void setFil040SortKey(int fil040SortKey) {
        fil040SortKey__ = fil040SortKey;
    }
    /**
     * @return fil040UrlString
     */
    public String getFil040UrlString() {
        return fil040UrlString__;
    }
    /**
     * @param fil040UrlString 設定する fil040UrlString
     */
    public void setFil040UrlString(String fil040UrlString) {
        fil040UrlString__ = fil040UrlString;
    }
    /**
     * @return fil040DirectoryList
     */
    public ArrayList<FileDirectoryDspModel> getFil040DirectoryList() {
        return fil040DirectoryList__;
    }
    /**
     * @param fil040DirectoryList 設定する fil040DirectoryList
     */
    public void setFil040DirectoryList(
            ArrayList<FileDirectoryDspModel> fil040DirectoryList) {
        fil040DirectoryList__ = fil040DirectoryList;
    }
    /**
     * @return fil040UnLockAuth
     */
    public String getFil040UnLockAuth() {
        return fil040UnLockAuth__;
    }
    /**
     * @param fil040UnLockAuth 設定する fil040UnLockAuth
     */
    public void setFil040UnLockAuth(String fil040UnLockAuth) {
        fil040UnLockAuth__ = fil040UnLockAuth;
    }
    /**
     * @return fil040SelectDel
     */
    public String[] getFil040SelectDel() {
        return fil040SelectDel__;
    }
    /**
     * @param fil040SelectDel 設定する fil040SelectDel
     */
    public void setFil040SelectDel(String[] fil040SelectDel) {
        fil040SelectDel__ = fil040SelectDel;
    }
    /**
     * <p>fil040SelectDelAll を取得します。
     * @return fil040SelectDelAll
     */
    public String getFil040SelectDelAll() {
        return fil040SelectDelAll__;
    }
    /**
     * <p>fil040SelectDelAll をセットします。
     * @param fil040SelectDelAll fil040SelectDelAll
     */
    public void setFil040SelectDelAll(String fil040SelectDelAll) {
        fil040SelectDelAll__ = fil040SelectDelAll;
    }

    /**
     * <p>fil040SelectUnlock を取得します。
     * @return fil040SelectUnlock
     */
    public String getFil040SelectUnlock() {
        return fil040SelectUnlock__;
    }
    /**
     * <p>fil040SelectUnlock をセットします。
     * @param fil040SelectUnlock fil040SelectUnlock
     */
    public void setFil040SelectUnlock(String fil040SelectUnlock) {
        fil040SelectUnlock__ = fil040SelectUnlock;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説] 削除ボタンクリック時にチェックする
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, RequestModel reqMdl)
    throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        int errorSize = errors.size();
        FilCommonBiz biz = new FilCommonBiz(con, reqMdl);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textDelTarget = gsMsg.getMessage("cmn.remove.directory");

        //未選択チェック
        if (fil040SelectDel__ == null || fil040SelectDel__.length < 1) {
            msg = new ActionMessage(
                    "error.select.required.text",
                    textDelTarget);
            StrutsUtil.addMessage(errors, msg, "fil040SelectDel");
        } else {
            //削除可能かをチェック
            FileDirectoryDao dao = new FileDirectoryDao(con);
            for (String dirSid : fil040SelectDel__) {
                List<FileDirectoryModel> list = dao.getChildDirectory(Integer.parseInt(dirSid));
                if (list.size() > 0) {
                    msg = new ActionMessage(
                            "error.directory.not.empty.name",
                            biz.getDirctoryName(Integer.parseInt(dirSid), con));
                    StrutsUtil.addMessage(errors, msg, "fil040SelectDel" + dirSid);
                }
            }

        }

        if (errorSize < errors.size()) {
            return errors;
        }

        //管理者設定ロック区分 = 無効 の場合、ファイルロックチェックを行わない
        FilCommonBiz filCmnBiz = new FilCommonBiz(con, reqMdl);
        if (filCmnBiz.getLockKbnAdmin(con) == GSConstFile.LOCK_KBN_ON) {
            //ファイルロックチェック
            FileFileBinDao fileDao = new FileFileBinDao(con);
            for (String dirSid : fil040SelectDel__) {
                FileFileBinModel fileModel = fileDao.getNewFile(Integer.parseInt(dirSid));
                if (fileModel != null) {
                    if (fileModel.getFflLockKbn() == GSConstFile.LOCK_KBN_ON) {
                        String dirName = StringUtilHtml.transToHTmlPlusAmparsant(
                                biz.getDirctoryName(Integer.parseInt(dirSid), con));
                        msg = new ActionMessage("error.file.lock.name", dirName);
                        StrutsUtil.addMessage(errors, msg, "error.file.lock.name." + dirSid);
                    }
                }
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説] 一括移動ボタンクリック時にチェック
     * <br>[備  考]
     * @param con コネクション
     * @param req リクエスト
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheckMove(Connection con, HttpServletRequest req)
    throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String textDelTarget = gsMsg.getMessage(req, "fil.120");

        //未選択チェック
        if (fil040SelectDel__ == null || fil040SelectDel__.length < 1) {
            msg = new ActionMessage("error.select.required.text", textDelTarget);
            StrutsUtil.addMessage(errors, msg, "fil040SelectDel");

        }

        return errors;
    }

    /**
     * <br>[機  能] ファイルロック可能チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors fileLockCheck(Connection con, RequestModel reqMdl)
    throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        FilCommonBiz biz = new FilCommonBiz(con, reqMdl);

        GsMessage gsMsg = new GsMessage();
        String textLockTarget = gsMsg.getMessage("fil.fil040.9");

        //未選択チェック
        if (fil040SelectDel__ == null || fil040SelectDel__.length < 1) {
            msg = new ActionMessage(
                    "error.select.required.text",
                    textLockTarget);
            StrutsUtil.addMessage(errors, msg, "fil040SelectDel");

        } else {

            //フォルダチェック
            FileDirectoryDao dirDao = new FileDirectoryDao(con);
            List<FileDirectoryModel> dirList = dirDao.getNewDirectoryList(fil040SelectDel__);
            if (dirList != null && dirList.size() > 0) {
                for (FileDirectoryModel model : dirList) {
                    if (model.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                        msg = new ActionMessage(
                                "error.folder.lock",
                                StringUtilHtml.transToHTmlPlusAmparsant(model.getFdrName()));
                        StrutsUtil.addMessage(
                                errors, msg, "error.folder.lock." + model.getFdrSid());
                    }
                }
            }

            //ファイルロックチェック
            FileFileBinDao fileDao = new FileFileBinDao(con);
            for (String dirSid : fil040SelectDel__) {
                FileFileBinModel fileModel = fileDao.getNewFile(Integer.parseInt(dirSid));
                if (fileModel != null) {
                    if (fileModel.getFflLockKbn() == GSConstFile.LOCK_KBN_ON) {
                        String dirName = StringUtilHtml.transToHTmlPlusAmparsant(
                                biz.getDirctoryName(Integer.parseInt(dirSid), con));
                        msg = new ActionMessage("error.file.lock.edit", dirName);
                        StrutsUtil.addMessage(errors, msg, "error.file.lock.edit." + dirSid);
                    }
                }
            }

        }

        return errors;
    }

    /**
     * <br>[機  能] ファイルロック解除可能チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     * @param buModel BaseUserModel
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors fileUnlockCheck(
            Connection con, RequestModel reqMdl, BaseUserModel buModel)
    throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        FilCommonBiz biz = new FilCommonBiz(con , reqMdl);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textLockTarget = gsMsg.getMessage("fil.fil040.10");

        //セッションユーザSID
        int usrSid = buModel.getUsrsid();
        //キャビネットSID
        int fcbSid = NullDefault.getInt(getFil010SelectCabinet(), -1);
        //キャビネット管理者権限
        boolean cabinetAdminFlg = biz.isCabinetAdminUser(fcbSid, usrSid, con);

        //未選択チェック
        if (fil040SelectDel__ == null || fil040SelectDel__.length < 1) {
            msg = new ActionMessage(
                    "error.select.required.text",
                    textLockTarget);
            StrutsUtil.addMessage(errors, msg, "fil040SelectDel");

        } else {

            //フォルダチェック
            FileDirectoryDao dirDao = new FileDirectoryDao(con);
            List<FileDirectoryModel> dirList = dirDao.getNewDirectoryList(fil040SelectDel__);
            if (dirList != null && dirList.size() > 0) {
                for (FileDirectoryModel model : dirList) {
                    if (model.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                        msg = new ActionMessage(
                                "error.folder.unlock",
                                StringUtilHtml.transToHTmlPlusAmparsant(model.getFdrName()));
                        StrutsUtil.addMessage(
                                errors, msg, "error.folder.unlock." + model.getFdrSid());
                    }
                }
            }

            //管理者権限
            CommonBiz  commonBiz = new CommonBiz();
            boolean adminFlg = commonBiz.isPluginAdmin(con, buModel, GSConstFile.PLUGIN_ID_FILE);

            //ファイルロック解除可能チェック
            if (!adminFlg && !cabinetAdminFlg) {
                FileFileBinDao fileDao = new FileFileBinDao(con);
                for (String fdrSid : fil040SelectDel__) {
                    FileFileBinModel fileModel = fileDao.getNewFile(Integer.parseInt(fdrSid));
                    if (fileModel != null) {
                        if (fileModel.getFflLockKbn() == GSConstFile.LOCK_KBN_ON
                                && fileModel.getFflLockUser() != usrSid) {
                            String dirName = StringUtilHtml.transToHTmlPlusAmparsant(
                                    biz.getDirctoryName(Integer.parseInt(fdrSid), con));
                            msg = new ActionMessage("error.file.unlock.edit", dirName);
                            StrutsUtil.addMessage(errors, msg, "error.file.unlock.edit." + fdrSid);
                        }
                    }
                }
            }
        }

        return errors;
    }

    /**
     * <p>fil040SelectUnlockVer を取得します。
     * @return fil040SelectUnlockVer
     */
    public String getFil040SelectUnlockVer() {
        return fil040SelectUnlockVer__;
    }
    /**
     * <p>fil040SelectUnlockVer をセットします。
     * @param fil040SelectUnlockVer fil040SelectUnlockVer
     */
    public void setFil040SelectUnlockVer(String fil040SelectUnlockVer) {
        fil040SelectUnlockVer__ = fil040SelectUnlockVer;
    }
    /**
     * <p>fil040SelectCabinet を取得します。
     * @return fil040SelectCabinet
     */
    public String getFil040SelectCabinet() {
        return fil040SelectCabinet__;
    }
    /**
     * <p>fil040SelectCabinet をセットします。
     * @param fil040SelectCabinet fil040SelectCabinet
     */
    public void setFil040SelectCabinet(String fil040SelectCabinet) {
        fil040SelectCabinet__ = fil040SelectCabinet;
    }
    /**
     * <p>fil040CabinetList を取得します。
     * @return fil040CabinetList
     */
    public List<LabelValueBean> getFil040CabinetList() {
        return fil040CabinetList__;
    }
    /**
     * <p>fil040CabinetList をセットします。
     * @param fil040CabinetList fil040CabinetList
     */
    public void setFil040CabinetList(List<LabelValueBean> fil040CabinetList) {
        fil040CabinetList__ = fil040CabinetList;
    }
    /**
     * <p>fil090SelectPluralKbn を取得します。
     * @return fil090SelectPluralKbn
     */
    public int getFil090SelectPluralKbn() {
        return fil090SelectPluralKbn__;
    }

    /**
     * <p>fil090SelectPluralKbn をセットします。
     * @param fil090SelectPluralKbn fil090SelectPluralKbn
     */
    public void setFil090SelectPluralKbn(int fil090SelectPluralKbn) {
        fil090SelectPluralKbn__ = fil090SelectPluralKbn;
    }
    /**
     * <p>fil040DspSelectDelAll を取得します。
     * @return fil040DspSelectDelAll
     */
    public String getFil040DspSelectDelAll() {
        return fil040DspSelectDelAll__;
    }
    /**
     * <p>fil040DspSelectDelAll をセットします。
     * @param fil040DspSelectDelAll fil040DspSelectDelAll
     */
    public void setFil040DspSelectDelAll(String fil040DspSelectDelAll) {
        this.fil040DspSelectDelAll__ = fil040DspSelectDelAll;
    }
}