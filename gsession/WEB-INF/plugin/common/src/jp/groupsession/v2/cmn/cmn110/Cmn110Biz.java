package jp.groupsession.v2.cmn.cmn110;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.dao.base.CmnFileConfDao;
import jp.groupsession.v2.cmn.model.FileEnhancingModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnFileConfModel;

/**
 * <br>[機  能] ファイル添付ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn110Biz {

    /** 拡張子(BMP) */
    private static final String EXTENSION_BMP = ".BMP";
    /** 拡張子(JPG) */
    private static final String EXTENSION_JPG = ".JPG";
    /** 拡張子(JPEG) */
    private static final String EXTENSION_JPEG = ".JPEG";
    /** 拡張子(GIF) */
    private static final String EXTENSION_GIF = ".GIF";
    /** 拡張子(PNG) */
    private static final String EXTENSION_PNG = ".PNG";

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param cmn110Model パラメータ情報
     * @param con Connection
     * @param tempDir テンポラリディレクトリ
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 添付ファイル操作時例外
     */
    public void setInitData(Cmn110ParamModel cmn110Model, Connection con, String tempDir)
        throws SQLException, IOToolsException {

        //添付ファイル最大容量取得
        CmnFileConfDao cfcDao = new CmnFileConfDao(con);
        if (NullDefault.getString(cmn110Model.getCmn110Mode(), "").equals(
                String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_FILEKANRI))) {
            int size = cfcDao.getFileKanriFileSize();
            cmn110Model.setStrMaxSize(String.valueOf(size));
        } else if (NullDefault.getString(cmn110Model.getCmn110Mode(), "").equals(
                String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_TANITU_USR031))) {
            CmnFileConfModel cfcMdl = cfcDao.select();
            cmn110Model.setStrMaxSize(cfcMdl.getFicPhotoSize());
        } else {
            CmnFileConfModel cfcMdl = cfcDao.select();
            cmn110Model.setStrMaxSize(String.valueOf(cfcMdl.getFicMaxSize()));
        }

        ArrayList<String> updList = new ArrayList<String>();
        if (NullDefault.getString(cmn110Model.getCmn110Mode(), "").equals(
                String.valueOf(GSConstCommon.FILE_UPLOAD_MODE_FILE))) {

            //テンポラリディレクトリにあるファイル名称を取得
            List<String> fileList = IOTools.getFileNames(tempDir);

            if (fileList != null && fileList.size() > 0) {

                for (int i = 0; i < fileList.size(); i++) {

                    //ファイル名を取得
                    String fileName = fileList.get(i);

                    if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                        continue;
                    }

                    //オブジェクトファイルを取得
                    ObjectFile objFile = new ObjectFile(tempDir, fileName);
                    Object fObj = objFile.load();
                    if (fObj == null) {
                        continue;
                    }

                    FileEnhancingModel fMdl = (FileEnhancingModel) fObj;
                    String objName = fMdl.getSplitObjName();

                    String fileStr =
                        fMdl.getFileName()
                        + cmn110Model.getSplitStr()
                        + String.valueOf(fMdl.getUpdateKbn())
                        + cmn110Model.getSplitStr()
                        + objName;

                    updList.add(fileStr);
                }
            }
        }

        String[] updStr;
        if (updList.isEmpty()) {
            updStr = new String[0];
        } else {
            updStr = updList.toArray(new String[updList.size()]);
        }
        cmn110Model.setFileList(updStr);
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param cmn110Model パラメータ情報
     * @param reqMdl リクエスト情報
     * @return テンポラリディレクトリパス
     */
    public static String getTempDir(String rootDir,
                                    Cmn110ParamModel cmn110Model,
                                    RequestModel reqMdl) {
        //セッションID
        String sessionId = reqMdl.getSession().getId();

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(rootDir);
        tempDir.append("/");
        tempDir.append(cmn110Model.getCmn110pluginId());
        tempDir.append("/");
        tempDir.append(sessionId);
        tempDir.append("/");
        if (cmn110Model.getCmn110TempDirPlus() != null) {
            tempDir.append(cmn110Model.getCmn110TempDirPlus());
            tempDir.append("/");
        }

        return tempDir.toString();
    }

    /**
     * <br>[機  能] 添付ファイル(本体)のパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @return 添付ファイル(本体)のパス
     */
    public static File getSaveFilePath(String tempDir, String dateStr, int fileNum) {

        return __getFilePath(tempDir, dateStr, fileNum, GSConstCommon.ENDSTR_SAVEFILE);
    }

    /**
     * <br>[機  能] オブジェクトファイルのパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @return 添付ファイル(本体)のパス
     */
    public static File getObjFilePath(String tempDir, String dateStr, int fileNum) {

        return __getFilePath(tempDir, dateStr, fileNum, GSConstCommon.ENDSTR_OBJFILE);
    }

    /**
     * <br>[機  能] 添付ファイルの連番を取得する
     * <br>[解  説] テンポラリディレクトリパス以下に存在する
     * <br>         名前が「日付文字列 + xxx + "file"」のファイルの数を返す
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param dateStr 日付文字列
     * @return テンポラリファイルの連番
     */
    public static int getFileNumber(String tempDir, String dateStr) {
        Cmn110FilenameFilter filter =
            new Cmn110FilenameFilter(dateStr, GSConstCommon.ENDSTR_SAVEFILE);

        File tempDirPath = new File(tempDir);
        File[] fileList = tempDirPath.listFiles(filter);
        if (fileList == null) {
            return 0;
        }

        int fileNum = 0;
        int tailLen = GSConstCommon.ENDSTR_SAVEFILE.length();
        for (File fileName : fileList) {
            String num = fileName.getName();
            num = num.substring(dateStr.length(), num.length() - tailLen);
            if (fileNum < Integer.parseInt(num)) {
                fileNum = Integer.parseInt(num);
            }
        }

        return fileNum;
    }

    /**
     * <br>[機  能] ファイルパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリファイル
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @param endStr 接尾文字列("file" or "obj")
     * @return ファイルパス
     */
    private static File __getFilePath(String tempDir, String dateStr, int fileNum, String endStr) {

        StringBuilder filePath = new StringBuilder("");
        filePath.append(tempDir);
        filePath.append(dateStr);
        filePath.append(StringUtil.toDecFormat(fileNum, "000"));
        filePath.append(endStr);

        return new File(filePath.toString());
    }
    /**
     * <br>[機  能] ファイルパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリファイル
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @param endStr 接尾文字列("file" or "obj")
     * @return ファイルパス
     */
    public static String getFilePath(String tempDir, String dateStr, int fileNum, String endStr) {

        StringBuilder filePath = new StringBuilder("");
        filePath.append(tempDir);
        filePath.append(dateStr);
        filePath.append(StringUtil.toDecFormat(fileNum, "000"));
        filePath.append(endStr);

        return filePath.toString();
    }
    /**
     * <br>[機  能] ファイル名から拡張子を取得し、
     *              特定の種類のファイルであるかを確認する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param fileName ファイル名
     * @return ret true:拡張子が正常 false:拡張子が不正
     */
    public static boolean isExtensionOk(String fileName) {

        boolean ret = false;

        //ファイル名がNULLまたは空の場合は処理しない
        if (fileName != null && !fileName.equals("")) {
            String strExt = StringUtil.getExtension(fileName);

            //拡張子が取得できた場合
            if (strExt != null) {

                //拡張子がBMPか
                if (strExt.toUpperCase().equals(EXTENSION_BMP)) {
                    return true;
                }
                //拡張子がJPGか
                if (strExt.toUpperCase().equals(EXTENSION_JPG)) {
                    return true;
                }
                //拡張子がJPEGか
                if (strExt.toUpperCase().equals(EXTENSION_JPEG)) {
                    return true;
                }
                //拡張子がGIFか
                if (strExt.toUpperCase().equals(EXTENSION_GIF)) {
                    return true;
                }
                //拡張子がPNGか
                if (strExt.toUpperCase().equals(EXTENSION_PNG)) {
                    return true;
                }
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] ファイル名から拡張子を取得し、
     *              特定の種類のファイルであるかを確認する
     * <br>[解  説] 画像ファイルの拡張子であればtrueを返します。BMPを除く。
     * <br>[備  考] JPG, JPEG, GIF, PNGの場合にtrueを返します。
     *
     * @param fileName ファイル名
     * @return ret true:拡張子が正常 false:拡張子が不正
     */
    public static boolean isExtensionForPhotoOk(String fileName) {

        boolean ret = false;

        //ファイル名がNULLまたは空の場合は処理しない
        if (fileName != null && !fileName.equals("")) {
            String strExt = StringUtil.getExtension(fileName);

            //拡張子が取得できた場合
            if (strExt != null) {

                //拡張子がJPGか
                if (strExt.toUpperCase().equals(EXTENSION_JPG)) {
                    return true;
                }
                //拡張子がJPEGか
                if (strExt.toUpperCase().equals(EXTENSION_JPEG)) {
                    return true;
                }
                //拡張子がGIFか
                if (strExt.toUpperCase().equals(EXTENSION_GIF)) {
                    return true;
                }
                //拡張子がPNGか
                if (strExt.toUpperCase().equals(EXTENSION_PNG)) {
                    return true;
                }
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] JSON形式で使用できる文字列へ変換する
     * <br>[解  説]
     * <br>[備  考] '\b','\t','\f','\r' についてはサーバ側のJavascriptにてエスケープを行うので
     * <br>         エスケープの対象とはしない。
     * @param text 文字列
     * @return 文字列
     * @throws UnsupportedEncodingException 文字コードの指定が不正
     */
    public static String escapeText(String text) throws UnsupportedEncodingException {
        String encodeText = NullDefault.getString(text, "");
        encodeText = StringUtilHtml.transToHTml(encodeText);
        encodeText = StringUtilHtml.replaceString(encodeText, "\\", "\\\\");
        encodeText = StringUtilHtml.replaceString(encodeText, "\r\n", "\n");
        encodeText = StringUtilHtml.replaceString(encodeText, "\"", "\\\"");
        encodeText = StringUtilHtml.replaceString(encodeText, "\n", "\\n");

        return encodeText;
    }

}