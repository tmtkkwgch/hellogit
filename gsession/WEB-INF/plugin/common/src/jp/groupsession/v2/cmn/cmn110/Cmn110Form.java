package jp.groupsession.v2.cmn.cmn110;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.dao.base.CmnFileConfDao;
import jp.groupsession.v2.cmn.model.base.CmnFileConfModel;
import jp.groupsession.v2.struts.AbstractGsForm;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.upload.FormFile;

/**
 * <br>[機  能] ファイル添付ポップアップのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn110Form extends AbstractGsForm {

    /** Logging インスタンス */
//    private static Log log__ = LogFactory.getLog(Cmn110Form.class);

    /** アップロード種別 通常 */
    public static final int UPLOADTYPE_NORMAL = 0;
    /** アップロード種別 Drop */
    public static final int UPLOADTYPE_DROP = 1;

    /** 添付ファイル用のリストの名称 */
    private String cmn110parentListName__ = null;
    /** 機能ごとのID */
    private String cmn110pluginId__ = null;
    /** 添付ファイル名 */
    private String[] cmn110tempName__ = null;
    /** 添付ファイル名(本体) */
    private String[] cmn110tempSaveName__ = null;
    /** 添付ファイル */
    private List<FormFile> cmn110file__ = new ArrayList<FormFile>();
    /** 添付ファイルの指定数制限 0:制限無し、1:１件のみ */
    private int cmn110fileLimit__ = 0;
    /** 添付ファイルの保存先ディレクトリ※添付ディレクトリを分けたい場合に使用 */
    private String cmn110TempDirPlus__ = null;

    /** ファイル添付完了フラグ */
    private int cmn110Decision__ = 0;
    /** ファイルアップロード種別 */
    private int cmn110uploadType__ = UPLOADTYPE_NORMAL;

    /** 処理モード */
    private String cmn110Mode__;
    /** ファイル最大容量 (表示用) */
    private String strMaxSize__;

    /** ファイルサイズ */
    private static final int FILESIZE_NODATA = 0;
    /** ファイルリスト(ファイル管理で使用) */
    private String[] fileList__;
    /** スプリット文字列 */
    private String splitStr__ = GSConst.GSESSION2_ID + GSConst.GSESSION2_ID;

    //-------プロジェクトプラグインで使用
    /** プロジェクト使用フラグ 1:使用 */
    private  int cmn110PrjUseFlg__ = 0;
    /** プロジェクトSID */
    private  int cmn110PrjSid__ = 0;
    /** プロジェクト:CMDモード 0:新規登録 1:編集 */
    private  String cmn110PrjCmdMode__ = "0";

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con Connection
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイル操作時例外
     */
    public Cmn110ValidateResultModel validate110(Connection con, HttpServletRequest req)
        throws SQLException, IOToolsException {

        Cmn110ValidateResultModel resultMdl = new Cmn110ValidateResultModel();

        GsMessage gsMsg = new GsMessage();

        if (cmn110file__ == null || cmn110file__.size() <= 0) {
            //指定されたファイルが存在しない場合はエラーメッセージを表示
            resultMdl.addErrors(req, "error.input.notfound.file",
                                "cmn.cmn110.error.input.notfound.file",
                                1);
            return resultMdl;
        }

        //1ファイルのみ選択可能な画面に複数ファイルが指定された場合、エラーメッセージを表示
        if (cmn110file__.size() > 1) {
            String mode = NullDefault.getString(getCmn110Mode(), "");
            if (getCmn110fileLimit() == 1
            || mode.equals("4")
            || mode.equals("5")) {
                resultMdl.addErrors(req, "error.input.multiple.files",
                                    "cmn.cmn110.error.input.multiple.files",
                                    1);
                return resultMdl;
            }
        }

        int fileNo = 0;
        CmnFileConfDao cfcDao = new CmnFileConfDao(con);
        for (FormFile fileData : cmn110file__) {
            fileNo++;

            if (fileData == null || fileData.getFileSize() == FILESIZE_NODATA) {
                //指定されたファイルが存在しない場合はエラーメッセージを表示
                resultMdl.addErrors(req, "error.input.notfound.file",
                                    "cmn.cmn110.error.input.notfound.file",
                                    fileNo);
                continue;
            }

            if (fileData.getFileName().length() > GSConst.MAX_LENGTH_FILE) {
                //ファイル名
                String textFileName = gsMsg.getMessage(req, "cmn.file.name");

                //ファイル名桁数チェック
                resultMdl.addErrors(req, "error.input.length.text",
                                "cmn.cmn110.error.input.length.text",
                                fileNo,
                                new String[] {textFileName,
                                            Integer.toString(GSConst.MAX_LENGTH_FILE)});
                continue;
            }

            //ファイル名 使用文字チェック
            if (!GSValidateUtil.isGsJapaneaseString(fileData.getFileName())) {
                //ファイル名
                String textFileName = gsMsg.getMessage(req, "cmn.file.name");

                //利用不可能な文字を入力した場合
                String nstr =
                    GSValidateUtil.getNotGsJapaneaseString(
                            fileData.getFileName());

                resultMdl.addErrors(req, "error.input.njapan.text",
                                    "cmn.cmn110.error.input.njapan.text",
                                    fileNo,
                                    new String[] {textFileName, nstr});
            }

            if (NullDefault.getString(cmn110Mode__, "").equals(
                    String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_FILEKANRI))) {
                //ファイル管理モードの場合
                int confSize = cfcDao.getFileKanriFileSize();
                int maxSize = confSize * GSConstCommon.FILE_SIZE_1MB;
                if (fileData.getFileSize() > maxSize) {
                    //指定されたファイルの容量が最大値を超えていた場合はエラーメッセージを表示
                    resultMdl.addErrors(req, "error.input.capacity.over",
                            "cmn.cmn110.error.input.capacity.over",
                            fileNo,
                            new String[] {confSize + "MB"});
                    continue;
                }

            } else if (NullDefault.getString(cmn110Mode__, "").equals(
                    String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_TANITU_USR031))) {
                //単一画像ファイル登録ユーザ登録の場合

                //添付ファイル最大容量取得
                CmnFileConfModel cfcModel = cfcDao.select();
                BigDecimal maxSize = new BigDecimal(cfcModel.getFicPhotoSize());
                BigDecimal maxSizeMb =
                        maxSize.multiply(new BigDecimal(GSConstCommon.FILE_SIZE_1MB));
                BigDecimal fileSize = new BigDecimal(fileData.getFileSize());

                if (maxSizeMb.compareTo(fileSize) == -1) {
                    //「写真ファイル制限の最大値 < 指定されたファイルの容量」の場合はエラーメッセージを表示
                    resultMdl.addErrors(req, "error.input.capacity.over",
                            "cmn.cmn110.error.input.capacity.over",
                            fileNo,
                            new String[] {cfcModel.getFicPhotoSize() + "MB"});
                    continue;
                }

            } else {
                //添付ファイル最大容量取得
                CmnFileConfModel cfcMdl = cfcDao.select();
                    //通常の添付ファイルの場合
                int maxSize = cfcMdl.getFicMaxSize();
                int maxSizeMb = maxSize * GSConstCommon.FILE_SIZE_1MB;

                if (maxSizeMb < fileData.getFileSize()) {
                    //「添付ファイル制限の最大値 < 指定されたファイルの容量」の場合はエラーメッセージを表示
                    resultMdl.addErrors(req, "error.input.capacity.over",
                            "cmn.cmn110.error.input.capacity.over",
                            fileNo,
                            new String[] {maxSize + "MB"});
                    continue;
                }
            }
        }

        return resultMdl;
    }

    /**
     * <p>fileList を取得します。
     * @return fileList
     */
    public String[] getFileList() {
        return fileList__;
    }
    /**
     * <p>fileList をセットします。
     * @param fileList fileList
     */
    public void setFileList(String[] fileList) {
        fileList__ = fileList;
    }
    /**
     * <p>cmn110TempDirPlus を取得します。
     * @return cmn110TempDirPlus
     */
    public String getCmn110TempDirPlus() {
        return cmn110TempDirPlus__;
    }
    /**
     * <p>cmn110TempDirPlus をセットします。
     * @param cmn110TempDirPlus cmn110TempDirPlus
     */
    public void setCmn110TempDirPlus(String cmn110TempDirPlus) {
        cmn110TempDirPlus__ = cmn110TempDirPlus;
    }
    /**
     * @return cmn110Mode__ を戻します。
     */
    public String getCmn110Mode() {
        return cmn110Mode__;
    }
    /**
     * @param cmn110Mode 設定する cmn110Mode__。
     */
    public void setCmn110Mode(String cmn110Mode) {
        cmn110Mode__ = cmn110Mode;
    }
    /**
     * @return cmn110file を戻します。
     */
    public List<FormFile> getCmn110fileForm() {
        return cmn110file__;
    }
    /**
     * @param cmn110file 設定する cmn110file。
     */
    public void setCmn110fileForm(List<FormFile> cmn110file) {
        cmn110file__ = cmn110file;
    }
    /**
     * @return cmn110parentListName を戻します。
     */
    public String getCmn110parentListName() {
        return cmn110parentListName__;
    }
    /**
     * @param cmn110parentListName 設定する cmn110parentListName。
     */
    public void setCmn110parentListName(String cmn110parentListName) {
        cmn110parentListName__ = cmn110parentListName;
    }
    /**
     * @return cmn110pluginId を戻します。
     */
    public String getCmn110pluginId() {
        return cmn110pluginId__;
    }
    /**
     * @param cmn110pluginId 設定する cmn110pluginId。
     */
    public void setCmn110pluginId(String cmn110pluginId) {
        cmn110pluginId__ = cmn110pluginId;
    }
    /**
     * @return cmn110Decision を戻します。
     */
    public int getCmn110Decision() {
        return cmn110Decision__;
    }
    /**
     * @param cmn110Decision 設定する cmn110Decision。
     */
    public void setCmn110Decision(int cmn110Decision) {
        cmn110Decision__ = cmn110Decision;
    }
    /**
     * @return cmn110tempName を戻します。
     */
    public String[] getCmn110tempName() {
        return cmn110tempName__;
    }
    /**
     * @param cmn110tempName 設定する cmn110tempName。
     */
    public void setCmn110tempName(String[] cmn110tempName) {
        cmn110tempName__ = cmn110tempName;
    }
    /**
     * @return cmn110tempSaveName を戻します。
     */
    public String[] getCmn110tempSaveName() {
        return cmn110tempSaveName__;
    }
    /**
     * @param cmn110tempSaveName 設定する cmn110tempSaveName。
     */
    public void setCmn110tempSaveName(String[] cmn110tempSaveName) {
        cmn110tempSaveName__ = cmn110tempSaveName;
    }
    /**
     * @return cmn110fileLimit を戻します。
     */
    public int getCmn110fileLimit() {
        return cmn110fileLimit__;
    }
    /**
     * @param cmn110fileLimit 設定する cmn110fileLimit。
     */
    public void setCmn110fileLimit(int cmn110fileLimit) {
        cmn110fileLimit__ = cmn110fileLimit;
    }

    /**
     * <p>strMaxSize を取得します。
     * @return strMaxSize
     */
    public String getStrMaxSize() {
        return strMaxSize__;
    }

    /**
     * <p>strMaxSize をセットします。
     * @param strMaxSize strMaxSize
     */
    public void setStrMaxSize(String strMaxSize) {
        strMaxSize__ = strMaxSize;
    }
    /**
     * <p>splitStr を取得します。
     * @return splitStr
     */
    public String getSplitStr() {
        return splitStr__;
    }
    /**
     * <p>splitStr をセットします。
     * @param splitStr splitStr
     */
    public void setSplitStr(String splitStr) {
        splitStr__ = splitStr;
    }

    /**
     * <p>cmn110PrjCmdMode を取得します。
     * @return cmn110PrjCmdMode
     */
    public String getCmn110PrjCmdMode() {
        return cmn110PrjCmdMode__;
    }

    /**
     * <p>cmn110PrjCmdMode をセットします。
     * @param cmn110PrjCmdMode cmn110PrjCmdMode
     */
    public void setCmn110PrjCmdMode(String cmn110PrjCmdMode) {
        cmn110PrjCmdMode__ = cmn110PrjCmdMode;
    }

    /**
     * <p>cmn110PrjSid を取得します。
     * @return cmn110PrjSid
     */
    public int getCmn110PrjSid() {
        return cmn110PrjSid__;
    }

    /**
     * <p>cmn110PrjSid をセットします。
     * @param cmn110PrjSid cmn110PrjSid
     */
    public void setCmn110PrjSid(int cmn110PrjSid) {
        cmn110PrjSid__ = cmn110PrjSid;
    }

    /**
     * <p>cmn110PrjUseFlg を取得します。
     * @return cmn110PrjUseFlg
     */
    public int getCmn110PrjUseFlg() {
        return cmn110PrjUseFlg__;
    }

    /**
     * <p>cmn110PrjUseFlg をセットします。
     * @param cmn110PrjUseFlg cmn110PrjUseFlg
     */
    public void setCmn110PrjUseFlg(int cmn110PrjUseFlg) {
        cmn110PrjUseFlg__ = cmn110PrjUseFlg;
    }

    /**
     * <p>cmn110uploadType を取得します。
     * @return cmn110uploadType
     */
    public int getCmn110uploadType() {
        return cmn110uploadType__;
    }

    /**
     * <p>cmn110uploadType をセットします。
     * @param cmn110uploadType cmn110uploadType
     */
    public void setCmn110uploadType(int cmn110uploadType) {
        cmn110uploadType__ = cmn110uploadType;
    }

    /**
     * 添付ファイルを取得します。
     * @param index 要素番号
     * @return FormFile
     */
    public FormFile getCmn110file(int index) {
        while (cmn110file__.size() <= index) {
            return null;
        }

        return (FormFile) cmn110file__.get(index);
    }

    /**
     * 添付ファイルを追加します。
     * @param index 要素番号
     * @param cmn110file FormFile
     */
    public void setCmn110file(int index, FormFile cmn110file) {
        while (cmn110file__.size() <= index) {
            cmn110file__.add(null);
        }
        cmn110file__.remove(index);
        cmn110file__.add(index, cmn110file);
    }
    /**
     * @return cmn110file を戻します。
     */
    public FormFile[] getCmn110file() {
        if (cmn110file__ == null) {
            return null;
        }
        return cmn110file__.toArray(new FormFile[cmn110file__.size()]);
    }
    /**
     * @param cmn110file 設定する cmn110file。
     */
    public void setCmn110file(FormFile[] cmn110file) {
        if (cmn110file == null) {
            return;
        }
        for (int i = 0; i < cmn110file.length; i++) {
            setCmn110file(i, cmn110file[i]);
        }
    }

}