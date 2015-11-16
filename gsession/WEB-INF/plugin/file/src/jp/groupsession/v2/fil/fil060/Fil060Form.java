package jp.groupsession.v2.fil.fil060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.GSValidateFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.fil050.Fil050Form;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileParentAccessDspModel;
import jp.groupsession.v2.fil.util.FilValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] フォルダ登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil060Form extends Fil050Form {

    /** 登録変更モード */
    private int fil060CmdMode__ = 0;
    /** プラグインID */
    private String fil060PluginId__ = null;
    /** フォルダ名 */
    private String fil060DirName__ = null;
    /** 備考 */
    private String fil060Biko__ = null;
    /** 添付ファイル(コンボで選択中) */
    private String[] fil060TempFiles__ = null;
    /** ファイルコンボ */
    private List<LabelValueBean> fil060FileLabelList__ = null;
    /** 更新者ID */
    private String fil060EditId__ = null;
    /** グループ一覧 */
    private List<LabelValueBean> fil060groupList__ = null;

    /** 親アクセス権限全表示*/
    private String fil060ParentAccessAll__ = "0";
    /** アクセス制御　有無*/
    private String fil060AccessKbn__ = "0";
    /** 含まれるサブフォルダ・ファイルにも適応*/
    private String file060AdaptIncFile__ = "0";

    /** アクセス候補　追加・変更・削除グループ*/
    private String fil060AcEditSltGroup__ = null;
    /** アクセス制限 追加・変更・削除候補選択 checkbox*/
    private String fil060AcEditAllSlt__ = GSConstFile.DSP_KBN_OFF;
    /** アクセス制限 追加・変更・削除 グループリスト */
    private ArrayList< LabelValueBean > fil060AcEditGroupLavel__ = null;

    /** フルアクセスユーザ */
    private String[] fil060AcFull__ = null;
    /** アクセス制限 フルアクセスユーザ・グループリスト */
    private ArrayList<LabelValueBean> fil060AcFullLavel__ = null;

    /** ユーザリスト（追加・変更・削除 候補）*/
    private String[] fil060AcEditRight__ = null;
    /** アクセス制限 追加・変更・削除 候補リスト */
    private ArrayList< LabelValueBean > fil060AcEditRightLavel__ = null;

    /** アクセス候補　閲覧グループ*/
    private String fil060AcReadSltGroup__ = null;
    /** アクセス制限 閲覧候補選択 checkbox*/
    private String fil060AcReadAllSlt__ = GSConstFile.DSP_KBN_OFF;
    /** アクセス制限 閲覧 グループリスト */
    private ArrayList< LabelValueBean > fil060AcReadGroupLavel__ = null;

    /** 閲覧アクセスユーザ */
    private String[] fil060AcRead__ = null;
    /** アクセス制限 閲覧ユーザ・グループリスト */
    private ArrayList< LabelValueBean > fil060AcReadLavel__ = null;

    /** ユーザリスト（閲覧 候補）*/
    private String[] fil060AcReadRight__ = null;
    /** アクセス制限 閲覧 候補リスト */
    private ArrayList< LabelValueBean > fil060AcReadRightLavel__ = null;

    /** セーブ フルアクセスユーザ */
    private String[] fil060SvAcFull__ = null;
    /** セーブ 閲覧アクセスユーザ */
    private String[] fil060SvAcRead__ = null;

    /** 親ディレクトリ　追加・変更・削除ユーザリスト */
    private ArrayList<FileParentAccessDspModel> fil060ParentEditList__ = null;
    /** 親ディレクトリ　閲覧ユーザリスト */
    private ArrayList<FileParentAccessDspModel> fil060ParentReadList__ = null;

    /** 親ディレクトリ　アクセス権限全件表示フラグ */
    private int fil060ParentAccessAllDspFlg__ = 0;
    /** 親ディレクトリ　ゼロユーザフラグ */
    private String fil060ParentZeroUser__ = "0";
    /**
     * <br>[機  能] 入力チェックを行う（フォルダ登録時）
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors fil060validateCheck(
            Connection con, HttpServletRequest req) throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //ゼロユーザチェック
        if (fil060ParentZeroUser__.equals("1")) {
            GsMessage gsMsg = new GsMessage(req);
            msg = new ActionMessage(
                    "error.input.format.file",
                    gsMsg.getMessage("cmn.folder"),
                    gsMsg.getMessage("fil.102"));
            StrutsUtil.addMessage(errors, msg, "error.input.format.file");
            return errors;
        }
        //ディレクトリ階層チェック
        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel mdl = dao.getNewDirectory(
                NullDefault.getInt(getFil050ParentDirSid(), -1));
        if (mdl != null) {
            if (mdl.getFdrLevel() >= GSConstFile.DIRECTORY_LEVEL_10) {
                msg = new ActionMessage(
                        "error.over.level.create.dir", GSConstFile.DIRECTORY_LEVEL_10);
                StrutsUtil.addMessage(errors, msg, "error.over.level.create.dir");
                return errors;
            }
        }

        GsMessage gsMsg = new GsMessage(req);
        String textFolderName = gsMsg.getMessage("fil.21");
        String textBiko = gsMsg.getMessage("cmn.memo");

        //フォルダ名
        GSValidateFile.validateTextBoxInput(errors,
                                         fil060DirName__,
                                         textFolderName,
                                         GSConstFile.MAX_LENGTH_FOLDER_NAME,
                                         true);

        //アクセス権限 制限する場合は必須
        if (fil060AccessKbn__.equals(String.valueOf(GSConstFile.ACCESS_KBN_ON))) {
            //フルアクセスユーザを選択チェック
            if ((fil060SvAcFull__ == null || fil060SvAcFull__.length < 1)
             && (fil060SvAcRead__ == null || fil060SvAcRead__.length < 1)) {
                String textAc = gsMsg.getMessage("fil.102");
                msg =
                    new ActionMessage(
                        "error.select.required.text", textAc);
                StrutsUtil.addMessage(errors, msg, "fil060SvAcFull");
            }
            List<String> svAcList = new ArrayList<String>();
            if (fil060SvAcFull__ != null) {
                svAcList.addAll(Arrays.asList(fil060SvAcFull__));
            }
            if (fil060SvAcRead__ != null) {
                svAcList.addAll(Arrays.asList(fil060SvAcRead__));
            }
            String[] svAc = svAcList.toArray(new String[svAcList.size()]);

            //選択ユーザ権限チェック(追加・変更・削除・閲覧)
            FilValidateUtil.validateSltDaccess(errors,
                                               con,
                                               Integer.parseInt(GSConstFile.ACCESS_KBN_READ),
                                               svAc,
                                               NullDefault.getInt(getFil050ParentDirSid(), -1),
                                               gsMsg);

        }

        //備考
        GSValidateFile.validateTextarea(errors,
                                         fil060Biko__,
                                         textBiko,
                                         GSConstFile.MAX_LENGTH_FOLDER_BIKO,
                                         false);
        return errors;
    }

    /**
     * <br>[機  能] 入力チェックを行う(削除ボタンクリック時)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行例外
     */
    public ActionErrors checkDelFolder(Connection con) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        String fieldFix = "fil060DirName.";

        int dirSid = NullDefault.getInt(getFil050DirSid(), -1);
        //削除対象ディレクトリの下の階層の有無チェック
        FileDirectoryDao dao = new FileDirectoryDao(con);
        List<FileDirectoryModel> childList = dao.getChildDirectory(dirSid);

        if (!(childList == null || childList.size() < 1)) {
            msg = new ActionMessage("error.directory.not.empty");
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.spase.cl.only");
        }

        return errors;
    }

    /**
     * <p>fil060Biko を取得します。
     * @return fil060Biko
     */
    public String getFil060Biko() {
        return fil060Biko__;
    }
    /**
     * <p>fil060Biko をセットします。
     * @param fil060Biko fil060Biko
     */
    public void setFil060Biko(String fil060Biko) {
        fil060Biko__ = fil060Biko;
    }
    /**
     * <p>fil060DirName を取得します。
     * @return fil060DirName
     */
    public String getFil060DirName() {
        return fil060DirName__;
    }
    /**
     * <p>fil060DirName をセットします。
     * @param fil060DirName fil060DirName
     */
    public void setFil060DirName(String fil060DirName) {
        fil060DirName__ = fil060DirName;
    }
    /**
     * <p>fil060FileLabelList を取得します。
     * @return fil060FileLabelList
     */
    public List<LabelValueBean> getFil060FileLabelList() {
        return fil060FileLabelList__;
    }
    /**
     * <p>fil060FileLabelList をセットします。
     * @param fil060FileLabelList fil060FileLabelList
     */
    public void setFil060FileLabelList(List<LabelValueBean> fil060FileLabelList) {
        fil060FileLabelList__ = fil060FileLabelList;
    }
    /**
     * <p>fil060TempFiles を取得します。
     * @return fil060TempFiles
     */
    public String[] getFil060TempFiles() {
        return fil060TempFiles__;
    }
    /**
     * <p>fil060TempFiles をセットします。
     * @param fil060TempFiles fil060TempFiles
     */
    public void setFil060TempFiles(String[] fil060TempFiles) {
        fil060TempFiles__ = fil060TempFiles;
    }
    /**
     * <p>fil060CmdMode を取得します。
     * @return fil060CmdMode
     */
    public int getFil060CmdMode() {
        return fil060CmdMode__;
    }
    /**
     * <p>fil060CmdMode をセットします。
     * @param fil060CmdMode fil060CmdMode
     */
    public void setFil060CmdMode(int fil060CmdMode) {
        fil060CmdMode__ = fil060CmdMode;
    }
    /**
     * <p>fil060PluginId を取得します。
     * @return fil060PluginId
     */
    public String getFil060PluginId() {
        return fil060PluginId__;
    }
    /**
     * <p>fil060PluginId をセットします。
     * @param fil060PluginId fil060PluginId
     */
    public void setFil060PluginId(String fil060PluginId) {
        fil060PluginId__ = fil060PluginId;
    }

    /**
     * <p>fil060EditId を取得します。
     * @return fil060EditId
     */
    public String getFil060EditId() {
        return fil060EditId__;
    }

    /**
     * <p>fil060EditId をセットします。
     * @param fil060EditId fil060EditId
     */
    public void setFil060EditId(String fil060EditId) {
        fil060EditId__ = fil060EditId;
    }

    /**
     * <p>fil060groupList を取得します。
     * @return fil060groupList
     */
    public List<LabelValueBean> getFil060groupList() {
        return fil060groupList__;
    }

    /**
     * <p>fil060groupList をセットします。
     * @param fil060groupList fil060groupList
     */
    public void setFil060groupList(List<LabelValueBean> fil060groupList) {
        fil060groupList__ = fil060groupList;
    }

    /**
     * <p>fil060ParentAccessAll を取得します。
     * @return fil060ParentAccessAll
     */
    public String getFil060ParentAccessAll() {
        return fil060ParentAccessAll__;
    }

    /**
     * <p>fil060ParentAccessAll をセットします。
     * @param fil060ParentAccessAll fil060ParentAccessAll
     */
    public void setFil060ParentAccessAll(String fil060ParentAccessAll) {
        this.fil060ParentAccessAll__ = fil060ParentAccessAll;
    }

    /**
     * <p>fil060AccessKbn を取得します。
     * @return fil060AccessKbn
     */
    public String getFil060AccessKbn() {
        return fil060AccessKbn__;
    }

    /**
     * <p>fil060AccessKbn をセットします。
     * @param fil060AccessKbn fil060AccessKbn
     */
    public void setFil060AccessKbn(String fil060AccessKbn) {
        this.fil060AccessKbn__ = fil060AccessKbn;
    }

    /**
     * <p>file060AdaptIncFile を取得します。
     * @return file060AdaptIncFile
     */
    public String getFile060AdaptIncFile() {
        return file060AdaptIncFile__;
    }

    /**
     * <p>file060AdaptIncFile をセットします。
     * @param file060AdaptIncFile file060AdaptIncFile
     */
    public void setFile060AdaptIncFile(String file060AdaptIncFile) {
        this.file060AdaptIncFile__ = file060AdaptIncFile;
    }

    /**
     * <p>fil060AcEditSltGroup を取得します。
     * @return fil060AcEditSltGroup
     */
    public String getFil060AcEditSltGroup() {
        return fil060AcEditSltGroup__;
    }

    /**
     * <p>fil060AcEditSltGroup をセットします。
     * @param fil060AcEditSltGroup fil060AcEditSltGroup
     */
    public void setFil060AcEditSltGroup(String fil060AcEditSltGroup) {
        this.fil060AcEditSltGroup__ = fil060AcEditSltGroup;
    }

    /**
     * <p>fil060AcEditAllSlt を取得します。
     * @return fil060AcEditAllSlt
     */
    public String getFil060AcEditAllSlt() {
        return fil060AcEditAllSlt__;
    }

    /**
     * <p>fil060AcEditAllSlt をセットします。
     * @param fil060AcEditAllSlt fil060AcEditAllSlt
     */
    public void setFil060AcEditAllSlt(String fil060AcEditAllSlt) {
        this.fil060AcEditAllSlt__ = fil060AcEditAllSlt;
    }

    /**
     * <p>fil060AcEditGroupLavel を取得します。
     * @return fil060AcEditGroupLavel
     */
    public ArrayList< LabelValueBean > getFil060AcEditGroupLavel() {
        return fil060AcEditGroupLavel__;
    }

    /**
     * <p>fil060AcEditGroupLavel をセットします。
     * @param fil060AcEditGroupLavel fil060AcEditGroupLavel
     */
    public void setFil060AcEditGroupLavel(ArrayList< LabelValueBean > fil060AcEditGroupLavel) {
        this.fil060AcEditGroupLavel__ = fil060AcEditGroupLavel;
    }

    /**
     * <p>fil060AcFull を取得します。
     * @return fil060AcFull
     */
    public String[] getFil060AcFull() {
        return fil060AcFull__;
    }

    /**
     * <p>fil060AcFull をセットします。
     * @param fil060AcFull fil060AcFull
     */
    public void setFil060AcFull(String[] fil060AcFull) {
        this.fil060AcFull__ = fil060AcFull;
    }

    /**
     * <p>fil060AcFullLavel を取得します。
     * @return fil060AcFullLavel
     */
    public ArrayList<LabelValueBean> getFil060AcFullLavel() {
        return fil060AcFullLavel__;
    }

    /**
     * <p>fil060AcFullLavel をセットします。
     * @param fil060AcFullLavel fil060AcFullLavel
     */
    public void setFil060AcFullLavel(ArrayList<LabelValueBean> fil060AcFullLavel) {
        this.fil060AcFullLavel__ = fil060AcFullLavel;
    }

    /**
     * <p>fil060AcEditRight を取得します。
     * @return fil060AcEditRight
     */
    public String[] getFil060AcEditRight() {
        return fil060AcEditRight__;
    }

    /**
     * <p>fil060AcEditRight をセットします。
     * @param fil060AcEditRight fil060AcEditRight
     */
    public void setFil060AcEditRight(String[] fil060AcEditRight) {
        this.fil060AcEditRight__ = fil060AcEditRight;
    }

    /**
     * <p>fil060AcEditRightLavel を取得します。
     * @return fil060AcEditRightLavel
     */
    public ArrayList< LabelValueBean > getFil060AcEditRightLavel() {
        return fil060AcEditRightLavel__;
    }

    /**
     * <p>fil060AcEditRightLavel をセットします。
     * @param fil060AcEditRightLavel fil060AcEditRightLavel
     */
    public void setFil060AcEditRightLavel(ArrayList< LabelValueBean > fil060AcEditRightLavel) {
        this.fil060AcEditRightLavel__ = fil060AcEditRightLavel;
    }

    /**
     * <p>fil060AcReadSltGroup を取得します。
     * @return fil060AcReadSltGroup
     */
    public String getFil060AcReadSltGroup() {
        return fil060AcReadSltGroup__;
    }

    /**
     * <p>fil060AcReadSltGroup をセットします。
     * @param fil060AcReadSltGroup fil060AcReadSltGroup
     */
    public void setFil060AcReadSltGroup(String fil060AcReadSltGroup) {
        this.fil060AcReadSltGroup__ = fil060AcReadSltGroup;
    }

    /**
     * <p>fil060AcReadAllSlt を取得します。
     * @return fil060AcReadAllSlt
     */
    public String getFil060AcReadAllSlt() {
        return fil060AcReadAllSlt__;
    }

    /**
     * <p>fil060AcReadAllSlt をセットします。
     * @param fil060AcReadAllSlt fil060AcReadAllSlt
     */
    public void setFil060AcReadAllSlt(String fil060AcReadAllSlt) {
        this.fil060AcReadAllSlt__ = fil060AcReadAllSlt;
    }

    /**
     * <p>fil060AcReadGroupLavel を取得します。
     * @return fil060AcReadGroupLavel
     */
    public ArrayList< LabelValueBean > getFil060AcReadGroupLavel() {
        return fil060AcReadGroupLavel__;
    }

    /**
     * <p>fil060AcReadGroupLavel をセットします。
     * @param fil060AcReadGroupLavel fil060AcReadGroupLavel
     */
    public void setFil060AcReadGroupLavel(ArrayList< LabelValueBean > fil060AcReadGroupLavel) {
        this.fil060AcReadGroupLavel__ = fil060AcReadGroupLavel;
    }

    /**
     * <p>fil060AcRead を取得します。
     * @return fil060AcRead
     */
    public String[] getFil060AcRead() {
        return fil060AcRead__;
    }

    /**
     * <p>fil060AcRead をセットします。
     * @param fil060AcRead fil060AcRead
     */
    public void setFil060AcRead(String[] fil060AcRead) {
        this.fil060AcRead__ = fil060AcRead;
    }

    /**
     * <p>fil060AcReadLavel を取得します。
     * @return fil060AcReadLavel
     */
    public ArrayList< LabelValueBean > getFil060AcReadLavel() {
        return fil060AcReadLavel__;
    }

    /**
     * <p>fil060AcReadLavel をセットします。
     * @param fil060AcReadLavel セットする fil060AcReadLavel
     */
    public void setFil060AcReadLavel(ArrayList< LabelValueBean > fil060AcReadLavel) {
        this.fil060AcReadLavel__ = fil060AcReadLavel;
    }

    /**
     * <p>fil060AcReadRight を取得します。
     * @return fil060AcReadRight
     */
    public String[] getFil060AcReadRight() {
        return fil060AcReadRight__;
    }

    /**
     * <p>fil060AcReadRight をセットします。
     * @param fil060AcReadRight fil060AcReadRight
     */
    public void setFil060AcReadRight(String[] fil060AcReadRight) {
        this.fil060AcReadRight__ = fil060AcReadRight;
    }

    /**
     * <p>fil060AcReadRightLavel を取得します。
     * @return fil060AcReadRightLavel
     */
    public ArrayList< LabelValueBean > getFil060AcReadRightLavel() {
        return fil060AcReadRightLavel__;
    }

    /**
     * <p>fil060AcReadRightLavel をセットします。
     * @param fil060AcReadRightLavel fil060AcReadRightLavel
     */
    public void setFil060AcReadRightLavel(ArrayList< LabelValueBean > fil060AcReadRightLavel) {
        this.fil060AcReadRightLavel__ = fil060AcReadRightLavel;
    }

    /**
     * <p>fil060SvAcFull を取得します。
     * @return fil060SvAcFull
     */
    public String[] getFil060SvAcFull() {
        return fil060SvAcFull__;
    }

    /**
     * <p>fil060SvAcFull をセットします。
     * @param fil060SvAcFull fil060SvAcFull
     */
    public void setFil060SvAcFull(String[] fil060SvAcFull) {
        this.fil060SvAcFull__ = fil060SvAcFull;
    }

    /**
     * <p>fil060SvAcRead を取得します。
     * @return fil060SvAcRead
     */
    public String[] getFil060SvAcRead() {
        return fil060SvAcRead__;
    }

    /**
     * <p>fil060SvAcRead をセットします。
     * @param fil060SvAcRead fil060SvAcRead
     */
    public void setFil060SvAcRead(String[] fil060SvAcRead) {
        this.fil060SvAcRead__ = fil060SvAcRead;
    }

    /**
     * <p>fil060ParentEditList を取得します。
     * @return fil060ParentEditList
     */
    public ArrayList<FileParentAccessDspModel> getFil060ParentEditList() {
        return fil060ParentEditList__;
    }

    /**
     * <p>fil060ParentEditList をセットします。
     * @param fil060ParentEditList fil060ParentEditList
     */
    public void setFil060ParentEditList(ArrayList<FileParentAccessDspModel> fil060ParentEditList) {
        this.fil060ParentEditList__ = fil060ParentEditList;
    }

    /**
     * <p>fil060ParentReadList を取得します。
     * @return fil060ParentReadList
     */
    public ArrayList<FileParentAccessDspModel> getFil060ParentReadList() {
        return fil060ParentReadList__;
    }

    /**
     * <p>fil060ParentReadList をセットします。
     * @param fil060ParentReadList fil060ParentReadList
     */
    public void setFil060ParentReadList(ArrayList<FileParentAccessDspModel> fil060ParentReadList) {
        this.fil060ParentReadList__ = fil060ParentReadList;
    }

    /**
     * <p>fil060ParentAccessAllDspFlg を取得します。
     * @return fil060ParentAccessAllDspFlg
     */
    public int getFil060ParentAccessAllDspFlg() {
        return fil060ParentAccessAllDspFlg__;
    }

    /**
     * <p>fil060ParentAccessAllDspFlg をセットします。
     * @param fil060ParentAccessAllDspFlg fil060ParentAccessAllDspFlg
     */
    public void setFil060ParentAccessAllDspFlg(int fil060ParentAccessAllDspFlg) {
        this.fil060ParentAccessAllDspFlg__ = fil060ParentAccessAllDspFlg;
    }

    /**
     * <p>fil060ParentZeroUser を取得します。
     * @return fil060ParentZeroUser
     */
    public String getFil060ParentZeroUser() {
        return fil060ParentZeroUser__;
    }

    /**
     * <p>fil060ParentZeroUser をセットします。
     * @param fil060ParentZeroUser fil060ParentZeroUser
     */
    public void setFil060ParentZeroUser(String fil060ParentZeroUser) {
        this.fil060ParentZeroUser__ = fil060ParentZeroUser;
    }
}