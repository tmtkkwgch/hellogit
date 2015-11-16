package jp.groupsession.v2.fil.fil080;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.GSValidateFile;
import jp.groupsession.v2.fil.fil070.Fil070ParamModel;
import jp.groupsession.v2.fil.model.FileParentAccessDspModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ファイル登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil080ParamModel extends Fil070ParamModel {

    /** 複数登録区分 単一登録 */
    public static final String PLURALKBN_SINGLE = "0";
    /** 複数登録区分 複数一括登録 */
    public static final String PLURALKBN_MULTI = "1";

    /** プラグインID */
    private String fil080PluginId__ = null;
    /** フォルダパス */
    private String fil080DirPath__ = null;
    /** 添付ファイル(コンボで選択中) */
    private String[] fil080TempFiles__ = null;
    /** ファイルコンボ */
    private List<LabelValueBean> fil080FileLabelList__ = null;
    /** 処理モード */
    private int fil080Mode__ = GSConstFile.MODE_ADD;
    /** バージョン管理区分  */
    private String fil080VerKbn__ = null;
    /** バージョンコンボ */
    private List<LabelValueBean> fil080VerKbnLabelList__ = null;
    /** バージョン統一 */
    private String fil080VerallKbn__ = null;

    /** 更新者ID */
    private String fil080EditId__ = null;
    /** グループ一覧 */
    private List<LabelValueBean> fil080groupList__ = null;

    /** 備考 */
    private String fil080Biko__ = null;
    /** 更新コメント */
    private String fil080UpCmt__ = null;
    /** ロック　ユーザ名 */
    private String fil080LockUsrName__ = null;
    /** 複数登録区分 */
    private String fil080PluralKbn__ = PLURALKBN_SINGLE;
    /** 複数登録区分保持用 */
    private String fil080SvPluralKbn__ = PLURALKBN_MULTI;

    /** WEBメール 連携判定 */
    private int fil080webmail__ = 0;
    /** WEBメール 対象メール */
    private long fil080webmailId__ = 0;

    /** 親アクセス権限全表示*/
    private String fil080ParentAccessAll__ = "0";
    /** アクセス制御　有無*/
    private String fil080AccessKbn__ = "1";

    /** アクセス候補　追加・変更・削除グループ*/
    private String fil080AcEditSltGroup__ = null;
    /** アクセス制限 追加・変更・削除候補選択 checkbox*/
    private String fil080AcEditAllSlt__ = GSConstFile.DSP_KBN_OFF;
    /** アクセス制限 追加・変更・削除 グループリスト */
    private ArrayList< LabelValueBean > fil080AcEditGroupLavel__ = null;

    /** フルアクセスユーザ */
    private String[] fil080AcFull__ = null;
    /** アクセス制限 フルアクセスユーザ・グループリスト */
    private ArrayList<LabelValueBean> fil080AcFullLavel__ = null;

    /** ユーザリスト（追加・変更・削除 候補）*/
    private String[] fil080AcEditRight__ = null;
    /** アクセス制限 追加・変更・削除 候補リスト */
    private ArrayList< LabelValueBean > fil080AcEditRightLavel__ = null;

    /** アクセス候補　閲覧グループ*/
    private String fil080AcReadSltGroup__ = null;
    /** アクセス制限 閲覧候補選択 checkbox*/
    private String fil080AcReadAllSlt__ = GSConstFile.DSP_KBN_OFF;
    /** アクセス制限 閲覧 グループリスト */
    private ArrayList< LabelValueBean > fil080AcReadGroupLavel__ = null;

    /** 閲覧アクセスユーザ */
    private String[] fil080AcRead__ = null;
    /** アクセス制限 閲覧ユーザ・グループリスト */
    private ArrayList< LabelValueBean > fil080AcReadLavel__ = null;

    /** ユーザリスト（閲覧 候補）*/
    private String[] fil080AcReadRight__ = null;
    /** アクセス制限 閲覧 候補リスト */
    private ArrayList< LabelValueBean > fil080AcReadRightLavel__ = null;

    /** セーブ フルアクセスユーザ */
    private String[] fil080SvAcFull__ = null;
    /** セーブ 閲覧アクセスユーザ */
    private String[] fil080SvAcRead__ = null;

    /** 親ディレクトリ　追加・変更・削除ユーザリスト */
    private ArrayList<FileParentAccessDspModel> fil080ParentEditList__ = null;
    /** 親ディレクトリ　閲覧ユーザリスト */
    private ArrayList<FileParentAccessDspModel> fil080ParentReadList__ = null;

    /** 親ディレクトリ　アクセス権限全件表示フラグ */
    private int fil080ParentAccessAllDspFlg__ = 0;
    /** 親ディレクトリ　ゼロユーザフラグ */
    private String fil080ParentZeroUser__ = "0";

    /**
     * <br>[機  能] 入力チェックを行う（フォルダ登録時）
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param con コネクション
     * @param reqMdl RequestModel
     * @return エラー
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイル操作例外
     */
    public ActionErrors fil080validateCheck(String tempDir, Connection con, RequestModel reqMdl)
    throws SQLException, IOToolsException {

        ActionErrors errors = new ActionErrors();
        int errorSize = errors.size();

        ActionMessage msg = null;
        FilCommonBiz filBiz = new FilCommonBiz(con, reqMdl);

        List<String> fileList = IOTools.getFileNames(tempDir);

        //添付ファイル無し
        if (fileList == null || fileList.size() < 1) {

            GsMessage gsMsg = new GsMessage(reqMdl);
            String textFile = gsMsg.getMessage("cmn.file");

            msg = new ActionMessage("error.select.required.text", textFile);
            StrutsUtil.addMessage(errors, msg, "error.select.required.text");
        }

        if (errors.size() == errorSize) {
            //キャビネットSIDを取得
            int fcbSid = filBiz.getCabinetSid(NullDefault.getInt(getFil070ParentDirSid(), -1), con);
            //添付ファイルサイズチェック
            GSValidateFile.validateUsedDiskSizeOver(errors, fcbSid, con, tempDir, reqMdl);
        }
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textBiko = gsMsg.getMessage("cmn.memo");
        String textUpCmt = gsMsg.getMessage("fil.11");
        //備考
        GSValidateFile.validateTextarea(errors,
                                         fil080Biko__,
                                         textBiko,
                                         GSConstFile.MAX_LENGTH_FILE_BIKO,
                                         false);

        //更新コメント
        GSValidateFile.validateTextarea(errors,
                                         fil080UpCmt__,
                                         textUpCmt,
                                         GSConstFile.MAX_LENGTH_FILE_UP_CMT,
                                         false);

        return errors;
    }

    /**
     * <p>fil080SvPluralKbn を取得します。
     * @return fil080SvPluralKbn
     */
    public String getFil080SvPluralKbn() {
        return fil080SvPluralKbn__;
    }

    /**
     * <p>fil080SvPluralKbn をセットします。
     * @param fil080SvPluralKbn fil080SvPluralKbn
     */
    public void setFil080SvPluralKbn(String fil080SvPluralKbn) {
        fil080SvPluralKbn__ = fil080SvPluralKbn;
    }

    /**
     * <p>fil080PluralKbn を取得します。
     * @return fil080PluralKbn
     */
    public String getFil080PluralKbn() {
        return fil080PluralKbn__;
    }

    /**
     * <p>fil080PluralKbn をセットします。
     * @param fil080PluralKbn fil080PluralKbn
     */
    public void setFil080PluralKbn(String fil080PluralKbn) {
        fil080PluralKbn__ = fil080PluralKbn;
    }

    /**
     * <p>fil080Biko を取得します。
     * @return fil080Biko
     */
    public String getFil080Biko() {
        return fil080Biko__;
    }
    /**
     * <p>fil080Biko をセットします。
     * @param fil080Biko fil080Biko
     */
    public void setFil080Biko(String fil080Biko) {
        fil080Biko__ = fil080Biko;
    }
    /**
     * <p>fil080DirPath を取得します。
     * @return fil080DirPath
     */
    public String getFil080DirPath() {
        return fil080DirPath__;
    }
    /**
     * <p>fil080DirPath をセットします。
     * @param fil080DirPath fil080DirPath
     */
    public void setFil080DirPath(String fil080DirPath) {
        fil080DirPath__ = fil080DirPath;
    }
    /**
     * <p>fil080Mode を取得します。
     * @return fil080Mode
     */
    public int getFil080Mode() {
        return fil080Mode__;
    }
    /**
     * <p>fil080Mode をセットします。
     * @param fil080Mode fil080Mode
     */
    public void setFil080Mode(int fil080Mode) {
        fil080Mode__ = fil080Mode;
    }
    /**
     * <p>fil080PluginId を取得します。
     * @return fil080PluginId
     */
    public String getFil080PluginId() {
        return fil080PluginId__;
    }
    /**
     * <p>fil080PluginId をセットします。
     * @param fil080PluginId fil080PluginId
     */
    public void setFil080PluginId(String fil080PluginId) {
        fil080PluginId__ = fil080PluginId;
    }
    /**
     * <p>fil080VerKbn を取得します。
     * @return fil080VerKbn
     */
    public String getFil080VerKbn() {
        return fil080VerKbn__;
    }
    /**
     * <p>fil080VerKbn をセットします。
     * @param fil080VerKbn fil080VerKbn
     */
    public void setFil080VerKbn(String fil080VerKbn) {
        fil080VerKbn__ = fil080VerKbn;
    }
    /**
     * <p>fil080LockUsrName を取得します。
     * @return fil080LockUsrName
     */
    public String getFil080LockUsrName() {
        return fil080LockUsrName__;
    }
    /**
     * <p>fil080LockUsrName をセットします。
     * @param fil080LockUsrName fil080LockUsrName
     */
    public void setFil080LockUsrName(String fil080LockUsrName) {
        fil080LockUsrName__ = fil080LockUsrName;
    }
    /**
     * <p>fil080FileLabelList を取得します。
     * @return fil080FileLabelList
     */
    public List<LabelValueBean> getFil080FileLabelList() {
        return fil080FileLabelList__;
    }
    /**
     * <p>fil080FileLabelList をセットします。
     * @param fil080FileLabelList fil080FileLabelList
     */
    public void setFil080FileLabelList(List<LabelValueBean> fil080FileLabelList) {
        fil080FileLabelList__ = fil080FileLabelList;
    }

    /**
     * <p>fil080VerKbnLabelList を取得します。
     * @return fil080VerKbnLabelList
     */
    public List<LabelValueBean> getFil080VerKbnLabelList() {
        return fil080VerKbnLabelList__;
    }

    /**
     * <p>fil080VerKbnLabelList をセットします。
     * @param fil080VerKbnLabelList fil080VerKbnLabelList
     */
    public void setFil080VerKbnLabelList(List<LabelValueBean> fil080VerKbnLabelList) {
        fil080VerKbnLabelList__ = fil080VerKbnLabelList;
    }

    /**
     * <p>fil080TempFiles を取得します。
     * @return fil080TempFiles
     */
    public String[] getFil080TempFiles() {
        return fil080TempFiles__;
    }

    /**
     * <p>fil080TempFiles をセットします。
     * @param fil080TempFiles fil080TempFiles
     */
    public void setFil080TempFiles(String[] fil080TempFiles) {
        fil080TempFiles__ = fil080TempFiles;
    }

    /**
     * <p>fil080VerallKbn を取得します。
     * @return fil080VerallKbn
     */
    public String getFil080VerallKbn() {
        return fil080VerallKbn__;
    }

    /**
     * <p>fil080VerallKbn をセットします。
     * @param fil080VerallKbn fil080VerallKbn
     */
    public void setFil080VerallKbn(String fil080VerallKbn) {
        fil080VerallKbn__ = fil080VerallKbn;
    }

    /**
     * <p>fil080UpCmt を取得します。
     * @return fil080UpCmt
     */
    public String getFil080UpCmt() {
        return fil080UpCmt__;
    }

    /**
     * <p>fil080UpCmt をセットします。
     * @param fil080UpCmt fil080UpCmt
     */
    public void setFil080UpCmt(String fil080UpCmt) {
        fil080UpCmt__ = fil080UpCmt;
    }

    /**
     * <p>fil080webmail を取得します。
     * @return fil080webmail
     */
    public int getFil080webmail() {
        return fil080webmail__;
    }

    /**
     * <p>fil080webmail をセットします。
     * @param fil080webmail fil080webmail
     */
    public void setFil080webmail(int fil080webmail) {
        fil080webmail__ = fil080webmail;
    }

    /**
     * <p>fil080webmailId を取得します。
     * @return fil080webmailId
     */
    public long getFil080webmailId() {
        return fil080webmailId__;
    }

    /**
     * <p>fil080webmailId をセットします。
     * @param fil080webmailId fil080webmailId
     */
    public void setFil080webmailId(long fil080webmailId) {
        fil080webmailId__ = fil080webmailId;
    }

    /**
     * <p>fil080EditId を取得します。
     * @return fil080EditId
     */
    public String getFil080EditId() {
        return fil080EditId__;
    }

    /**
     * <p>fil080EditId をセットします。
     * @param fil080EditId fil080EditId
     */
    public void setFil080EditId(String fil080EditId) {
        fil080EditId__ = fil080EditId;
    }

    /**
     * <p>fil080groupList を取得します。
     * @return fil080groupList
     */
    public List<LabelValueBean> getFil080groupList() {
        return fil080groupList__;
    }

    /**
     * <p>fil080groupList をセットします。
     * @param fil080groupList fil080groupList
     */
    public void setFil080groupList(List<LabelValueBean> fil080groupList) {
        fil080groupList__ = fil080groupList;
    }

    /**
     * <p>fil080ParentAccessAll を取得します。
     * @return fil080ParentAccessAll
     */
    public String getFil080ParentAccessAll() {
        return fil080ParentAccessAll__;
    }

    /**
     * <p>fil080ParentAccessAll をセットします。
     * @param fil080ParentAccessAll fil080ParentAccessAll
     */
    public void setFil080ParentAccessAll(String fil080ParentAccessAll) {
        this.fil080ParentAccessAll__ = fil080ParentAccessAll;
    }

    /**
     * <p>fil080AccessKbn を取得します。
     * @return fil080AccessKbn
     */
    public String getFil080AccessKbn() {
        return fil080AccessKbn__;
    }

    /**
     * <p>fil080AccessKbn をセットします。
     * @param fil080AccessKbn fil080AccessKbn
     */
    public void setFil080AccessKbn(String fil080AccessKbn) {
        this.fil080AccessKbn__ = fil080AccessKbn;
    }

    /**
     * <p>fil080AcEditSltGroup を取得します。
     * @return fil080AcEditSltGroup
     */
    public String getFil080AcEditSltGroup() {
        return fil080AcEditSltGroup__;
    }

    /**
     * <p>fil080AcEditSltGroup をセットします。
     * @param fil080AcEditSltGroup fil080AcEditSltGroup
     */
    public void setFil080AcEditSltGroup(String fil080AcEditSltGroup) {
        this.fil080AcEditSltGroup__ = fil080AcEditSltGroup;
    }

    /**
     * <p>fil080AcEditAllSlt を取得します。
     * @return fil080AcEditAllSlt
     */
    public String getFil080AcEditAllSlt() {
        return fil080AcEditAllSlt__;
    }

    /**
     * <p>fil080AcEditAllSlt をセットします。
     * @param fil080AcEditAllSlt fil080AcEditAllSlt
     */
    public void setFil080AcEditAllSlt(String fil080AcEditAllSlt) {
        this.fil080AcEditAllSlt__ = fil080AcEditAllSlt;
    }

    /**
     * <p>fil080AcEditGroupLavel を取得します。
     * @return fil080AcEditGroupLavel
     */
    public ArrayList< LabelValueBean > getFil080AcEditGroupLavel() {
        return fil080AcEditGroupLavel__;
    }

    /**
     * <p>fil080AcEditGroupLavel をセットします。
     * @param fil080AcEditGroupLavel fil080AcEditGroupLavel
     */
    public void setFil080AcEditGroupLavel(ArrayList< LabelValueBean > fil080AcEditGroupLavel) {
        this.fil080AcEditGroupLavel__ = fil080AcEditGroupLavel;
    }

    /**
     * <p>fil080AcFull を取得します。
     * @return fil080AcFull
     */
    public String[] getFil080AcFull() {
        return fil080AcFull__;
    }

    /**
     * <p>fil080AcFull をセットします。
     * @param fil080AcFull fil080AcFull
     */
    public void setFil080AcFull(String[] fil080AcFull) {
        this.fil080AcFull__ = fil080AcFull;
    }

    /**
     * <p>fil080AcFullLavel を取得します。
     * @return fil080AcFullLavel
     */
    public ArrayList<LabelValueBean> getFil080AcFullLavel() {
        return fil080AcFullLavel__;
    }

    /**
     * <p>fil080AcFullLavel をセットします。
     * @param fil080AcFullLavel fil080AcFullLavel
     */
    public void setFil080AcFullLavel(ArrayList<LabelValueBean> fil080AcFullLavel) {
        this.fil080AcFullLavel__ = fil080AcFullLavel;
    }

    /**
     * <p>fil080AcEditRight を取得します。
     * @return fil080AcEditRight
     */
    public String[] getFil080AcEditRight() {
        return fil080AcEditRight__;
    }

    /**
     * <p>fil080AcEditRight をセットします。
     * @param fil080AcEditRight fil080AcEditRight
     */
    public void setFil080AcEditRight(String[] fil080AcEditRight) {
        this.fil080AcEditRight__ = fil080AcEditRight;
    }

    /**
     * <p>fil080AcEditRightLavel を取得します。
     * @return fil080AcEditRightLavel
     */
    public ArrayList< LabelValueBean > getFil080AcEditRightLavel() {
        return fil080AcEditRightLavel__;
    }

    /**
     * <p>fil080AcEditRightLavel をセットします。
     * @param fil080AcEditRightLavel fil080AcEditRightLavel
     */
    public void setFil080AcEditRightLavel(ArrayList< LabelValueBean > fil080AcEditRightLavel) {
        this.fil080AcEditRightLavel__ = fil080AcEditRightLavel;
    }

    /**
     * <p>fil080AcReadSltGroup を取得します。
     * @return fil080AcReadSltGroup
     */
    public String getFil080AcReadSltGroup() {
        return fil080AcReadSltGroup__;
    }

    /**
     * <p>fil080AcReadSltGroup をセットします。
     * @param fil080AcReadSltGroup fil080AcReadSltGroup
     */
    public void setFil080AcReadSltGroup(String fil080AcReadSltGroup) {
        this.fil080AcReadSltGroup__ = fil080AcReadSltGroup;
    }

    /**
     * <p>fil080AcReadAllSlt を取得します。
     * @return fil080AcReadAllSlt
     */
    public String getFil080AcReadAllSlt() {
        return fil080AcReadAllSlt__;
    }

    /**
     * <p>fil080AcReadAllSlt をセットします。
     * @param fil080AcReadAllSlt fil080AcReadAllSlt
     */
    public void setFil080AcReadAllSlt(String fil080AcReadAllSlt) {
        this.fil080AcReadAllSlt__ = fil080AcReadAllSlt;
    }

    /**
     * <p>fil080AcReadGroupLavel を取得します。
     * @return fil080AcReadGroupLavel
     */
    public ArrayList< LabelValueBean > getFil080AcReadGroupLavel() {
        return fil080AcReadGroupLavel__;
    }

    /**
     * <p>fil080AcReadGroupLavel をセットします。
     * @param fil080AcReadGroupLavel fil080AcReadGroupLavel
     */
    public void setFil080AcReadGroupLavel(ArrayList< LabelValueBean > fil080AcReadGroupLavel) {
        this.fil080AcReadGroupLavel__ = fil080AcReadGroupLavel;
    }

    /**
     * <p>fil080AcRead を取得します。
     * @return fil080AcRead
     */
    public String[] getFil080AcRead() {
        return fil080AcRead__;
    }

    /**
     * <p>fil080AcRead をセットします。
     * @param fil080AcRead fil080AcRead
     */
    public void setFil080AcRead(String[] fil080AcRead) {
        this.fil080AcRead__ = fil080AcRead;
    }

    /**
     * <p>fil080AcReadLavel を取得します。
     * @return fil080AcReadLavel
     */
    public ArrayList< LabelValueBean > getFil080AcReadLavel() {
        return fil080AcReadLavel__;
    }

    /**
     * <p>fil080AcReadLavel をセットします。
     * @param fil080AcReadLavel セットする fil080AcReadLavel
     */
    public void setFil080AcReadLavel(ArrayList< LabelValueBean > fil080AcReadLavel) {
        this.fil080AcReadLavel__ = fil080AcReadLavel;
    }

    /**
     * <p>fil080AcReadRight を取得します。
     * @return fil080AcReadRight
     */
    public String[] getFil080AcReadRight() {
        return fil080AcReadRight__;
    }

    /**
     * <p>fil080AcReadRight をセットします。
     * @param fil080AcReadRight fil080AcReadRight
     */
    public void setFil080AcReadRight(String[] fil080AcReadRight) {
        this.fil080AcReadRight__ = fil080AcReadRight;
    }

    /**
     * <p>fil080AcReadRightLavel を取得します。
     * @return fil080AcReadRightLavel
     */
    public ArrayList< LabelValueBean > getFil080AcReadRightLavel() {
        return fil080AcReadRightLavel__;
    }

    /**
     * <p>fil080AcReadRightLavel をセットします。
     * @param fil080AcReadRightLavel fil080AcReadRightLavel
     */
    public void setFil080AcReadRightLavel(ArrayList< LabelValueBean > fil080AcReadRightLavel) {
        this.fil080AcReadRightLavel__ = fil080AcReadRightLavel;
    }

    /**
     * <p>fil080SvAcFull を取得します。
     * @return fil080SvAcFull
     */
    public String[] getFil080SvAcFull() {
        return fil080SvAcFull__;
    }

    /**
     * <p>fil080SvAcFull をセットします。
     * @param fil080SvAcFull fil080SvAcFull
     */
    public void setFil080SvAcFull(String[] fil080SvAcFull) {
        this.fil080SvAcFull__ = fil080SvAcFull;
    }

    /**
     * <p>fil080SvAcRead を取得します。
     * @return fil080SvAcRead
     */
    public String[] getFil080SvAcRead() {
        return fil080SvAcRead__;
    }

    /**
     * <p>fil080SvAcRead をセットします。
     * @param fil080SvAcRead fil080SvAcRead
     */
    public void setFil080SvAcRead(String[] fil080SvAcRead) {
        this.fil080SvAcRead__ = fil080SvAcRead;
    }

    /**
     * <p>fil080ParentEditList を取得します。
     * @return fil080ParentEditList
     */
    public ArrayList<FileParentAccessDspModel> getFil080ParentEditList() {
        return fil080ParentEditList__;
    }

    /**
     * <p>fil080ParentEditList をセットします。
     * @param fil080ParentEditList fil080ParentEditList
     */
    public void setFil080ParentEditList(ArrayList<FileParentAccessDspModel> fil080ParentEditList) {
        this.fil080ParentEditList__ = fil080ParentEditList;
    }

    /**
     * <p>fil080ParentReadList を取得します。
     * @return fil080ParentReadList
     */
    public ArrayList<FileParentAccessDspModel> getFil080ParentReadList() {
        return fil080ParentReadList__;
    }

    /**
     * <p>fil080ParentReadList をセットします。
     * @param fil080ParentReadList fil080ParentReadList
     */
    public void setFil080ParentReadList(ArrayList<FileParentAccessDspModel> fil080ParentReadList) {
        this.fil080ParentReadList__ = fil080ParentReadList;
    }

    /**
     * <p>fil080ParentAccessAllDspFlg を取得します。
     * @return fil080ParentAccessAllDspFlg
     */
    public int getFil080ParentAccessAllDspFlg() {
        return fil080ParentAccessAllDspFlg__;
    }

    /**
     * <p>fil080ParentAccessAllDspFlg をセットします。
     * @param fil080ParentAccessAllDspFlg fil080ParentAccessAllDspFlg
     */
    public void setFil080ParentAccessAllDspFlg(int fil080ParentAccessAllDspFlg) {
        this.fil080ParentAccessAllDspFlg__ = fil080ParentAccessAllDspFlg;
    }

    /**
     * <p>fil080ParentZeroUser を取得します。
     * @return fil080ParentZeroUser
     */
    public String getFil080ParentZeroUser() {
        return fil080ParentZeroUser__;
    }

    /**
     * <p>fil080ParentZeroUser をセットします。
     * @param fil080ParentZeroUser fil080ParentZeroUser
     */
    public void setFil080ParentZeroUser(String fil080ParentZeroUser) {
        this.fil080ParentZeroUser__ = fil080ParentZeroUser;
    }

}