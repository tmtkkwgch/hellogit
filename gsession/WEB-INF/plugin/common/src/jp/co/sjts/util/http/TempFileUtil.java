package jp.co.sjts.util.http;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.FileNameUtil;
import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.ITempFileUtil;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.WmlTempfileModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

/**
 * <br>[機  能] 添付ファイルに関するユーティリティクラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class TempFileUtil {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(TempFileUtil.class);
    /** ファイルアップロード時のバッファーサイズ */
    public static final int BUFFERSIZE_UPLOAD = 1024;
    /** ファイルダウンロード時のバッファーサイズ */
    public static final int BUFFERSIZE_DOWNLOAD = 1024;

    /**
     * [機  能] アップロードされたファイルを引数で指定した出力先にコピーを行う<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param formFile アップロードされたファイル
     * @param outDir 出力先ディレクトリ
     * @param outFileName 出力先ファイル名
     * @throws Exception システムエラー
     */
    public static void upload(FormFile formFile, String outDir, String outFileName)
        throws Exception {

        try {
            File makeUserDir = new File(outDir);
            if (!makeUserDir.exists()) {
                makeUserDir.mkdirs();
            }

            InputStream inStream = formFile.getInputStream();

            File fileup = new File(outDir + outFileName);

            BufferedOutputStream outStream = null;
            try {
                outStream =
                    new BufferedOutputStream(new FileOutputStream(fileup));

                long mapLen = formFile.getFileSize();
                int bufSize = BUFFERSIZE_UPLOAD;
                if (bufSize > mapLen) {
                    bufSize = new Long(mapLen).intValue();
                }

                byte[] bytes = new byte[bufSize];
                int s = 0;
                long readSize = 0;
                while ((s = inStream.read(bytes)) != -1) {
                    outStream.write(bytes, 0, s);
                    //正確なバイトサイズで書き込むための処理
                    readSize = readSize + bytes.length;
                    int zanSize = new Long(mapLen - readSize).intValue();
                    if (zanSize < bufSize) {
                        bytes = new byte[zanSize];
                    }
                    if (zanSize <= 0) {
                        break;
                    }
                }
            } finally {
                try {
                    if (inStream != null) {
                        inStream.close();
                    }
                    if (outStream != null) {
                        outStream.close();
                    }
                } catch (IOException e) {
                }
            }

        } finally {
            //ファイルオブジェクト削除
            formFile.destroy();
        }
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFilePath ファイルデータパス
     * @param fileName ダウンロードファイル名
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadInline(
            HttpServletRequest req,
            HttpServletResponse res, String downFilePath, String fileName, String encoding)
            throws Exception {

        File file = new File(downFilePath);
        __download(req, res, file, fileName, false, encoding, false);
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param cbMdl 添付ファイルモデル
     * @param appRootPath アプリケーションルートパス
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadInline(
            HttpServletRequest req,
            HttpServletResponse res, CmnBinfModel cbMdl, String appRootPath, String encoding)
            throws Exception {

        ITempFileUtil tempUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        File file = tempUtil.getDownloadFile(cbMdl, appRootPath);
        __download(req, res, file, cbMdl.getBinFileName(), false, encoding, false);
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param wtfMdl 添付ファイルモデル
     * @param appRootPath アプリケーションルートパス
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadInlineForWebmail(
            HttpServletRequest req,
            HttpServletResponse res, WmlTempfileModel wtfMdl, String appRootPath, String encoding)
            throws Exception {

        ITempFileUtil tempUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        File file = tempUtil.getDownloadFileForWebmail(wtfMdl, appRootPath);

        __download(req, res, file, wtfMdl.getWtfFileName(), false, encoding, false);
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param cbMdl 添付ファイルモデル
     * @param appRootPath アプリケーションルートパス
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @param setCharset true:Charsetにencodingを設定、false:ブラウザにより決定
     * @throws Exception システムエラー
     */
    public static void downloadInline(
            HttpServletRequest req, HttpServletResponse res,
            CmnBinfModel cbMdl, String appRootPath, String encoding, boolean setCharset)
            throws Exception {

        ITempFileUtil tempUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        File file = tempUtil.getDownloadFile(cbMdl, appRootPath);

        __download(req, res, file, cbMdl.getBinFileName(), false, encoding, setCharset);
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param wtfMdl 添付ファイルモデル
     * @param appRootPath アプリケーションルートパス
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @param setCharset true:Charsetにencodingを設定、false:ブラウザにより決定
     * @throws Exception システムエラー
     */
    public static void downloadInlineForWebmail(
            HttpServletRequest req, HttpServletResponse res,
            WmlTempfileModel wtfMdl, String appRootPath, String encoding, boolean setCharset)
            throws Exception {

        ITempFileUtil tempUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        File file = tempUtil.getDownloadFileForWebmail(wtfMdl, appRootPath);

        __download(req, res, file, wtfMdl.getWtfFileName(), false, encoding, setCharset);
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFilePath ファイルデータパス
     * @param fileName ダウンロードファイル名
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @param setCharset true:Charsetにencodingを設定、false:ブラウザにより決定
     * @throws Exception システムエラー
     */
    public static void downloadInline(
            HttpServletRequest req,
            HttpServletResponse res, String downFilePath, String fileName, String encoding,
            boolean setCharset)
            throws Exception {

        File file = new File(downFilePath);
        __download(req, res, file, fileName, false, encoding, setCharset);
    }

    /**
     * [機  能] inlineダウンロード処理<br>
     * [解  説] <br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFile ファイルデータ
     * @param fileName ダウンロードファイル名
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadInline(HttpServletRequest req,
            HttpServletResponse res, File downFile, String fileName, String encoding)
            throws Exception {

        __download(req, res, downFile, fileName, false, encoding, false);
    }

    /**
     * [機  能] atachmentダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFilePath ファイルデータパス
     * @param fileName ダウンロードファイル名
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadAtachment(HttpServletRequest req,
            HttpServletResponse res, String downFilePath, String fileName, String encoding)
            throws Exception {

        File file = new File(downFilePath);
        __download(req, res, file, fileName, true, encoding, false);
    }

    /**
     * [機  能] atachmentダウンロード処理<br>
     * [解  説]<br>ファイル登録先を振り分ける
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param cbMdl 添付ファイルモデル
     * @param appRootPath アプリケーションルートパス
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadAtachment(HttpServletRequest req,
            HttpServletResponse res, CmnBinfModel cbMdl, String appRootPath,  String encoding)
            throws Exception {

        ITempFileUtil tempUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        File file = tempUtil.getDownloadFile(cbMdl, appRootPath);

        __download(req, res, file, cbMdl.getBinFileName(), true, encoding, false);
    }

    /**
     * [機  能] atachmentダウンロード処理<br>
     * [解  説]<br>ファイル登録先を振り分ける
     * [備  考]<br>ウェブメールで使用する。
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param wtfMdl 添付ファイルモデル
     * @param appRootPath アプリケーションルートパス
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadAtachmentForWebmail(HttpServletRequest req,
            HttpServletResponse res, WmlTempfileModel wtfMdl, String appRootPath,  String encoding)
            throws Exception {

        ITempFileUtil tempUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        File file = tempUtil.getDownloadFileForWebmail(wtfMdl, appRootPath);

        __download(req, res, file, wtfMdl.getWtfFileName(), true, encoding, false);
    }

    /**
     * [機  能] atachmentダウンロード処理<br>
     * [解  説]<br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFilePath ファイルデータパス
     * @param fileName ダウンロードファイル名
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @param setCharset true:Charsetにencodingを設定、false:ブラウザにより決定
     * @throws Exception システムエラー
     */
    public static void downloadAtachment(HttpServletRequest req,
            HttpServletResponse res, String downFilePath, String fileName,
            String encoding, boolean setCharset)
            throws Exception {

        File file = new File(downFilePath);
        __download(req, res, file, fileName, true, encoding, setCharset);
    }

    /**
     * [機  能] atachmentダウンロード処理<br>
     * [解  説] <br>
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFile ファイルデータ
     * @param fileName ダウンロードファイル名
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @throws Exception システムエラー
     */
    public static void downloadAtachment(HttpServletRequest req,
            HttpServletResponse res, File downFile, String fileName, String encoding)
            throws Exception {
        __download(req, res, downFile, fileName, true, encoding, false);
    }

    /**
     * [機  能] ダウンロード処理<br>
     * [解  説]<br>パラメータによりinline or attachment を制御する
     * [備  考]<br>
     * @param req HttpServletRequest
     * @param res レスポンス
     * @param downFile ファイルデータ
     * @param fileName ダウンロードファイル名
     * @param tempFlg true:attachment(添付ファイルとしてダウンロード), false:inline(画面内に描画する場合)
     * @param encoding ファイル名の出力文字コード(IE以外のブラウザで適用。IEの場合は強制的にShift_JIS)
     * @param setCharset true:Charsetにencodingを設定、false:ブラウザにより決定
     * @throws Exception システムエラー
     */
    private static void __download(HttpServletRequest req,
            HttpServletResponse res, File downFile, String fileName,
            boolean tempFlg, String encoding, boolean setCharset) throws Exception {

        // ファイル読み込み用バッファ
        byte[] buffer = new byte[BUFFERSIZE_DOWNLOAD];
        if (!downFile.exists()) {
            //ダウンロードするファイルが存在しない場合
            log__.error("File is not exist !!" + downFile.getAbsolutePath());
            throw new FileNotFoundException("ファイルが存在しません。"
                    + downFile.getAbsolutePath());
        }

        String dispfile = null;
        //ブラウザ判定
        boolean ieFlg = BrowserUtil.isIe(req);
        boolean edgeFlg = BrowserUtil.isEdge(req);
        boolean safariFlg = BrowserUtil.isSafari(req);

        //ファイル名にタブ文字が含まれる場合、スペースに変換する
        String dlFileName = NullDefault.getString(fileName, "").toString();
        dlFileName = FileNameUtil.getTempFileNameTabRemoveNoExt(dlFileName);

        if (ieFlg || edgeFlg) {
            //IE
            log__.debug("IEとしてダウンロード");
            dispfile = new String(dlFileName.getBytes(Encoding.WINDOWS_31J), Encoding.ISO8859_1);
        } else if (safariFlg) {
            log__.debug("Safariとしてダウンロード");
            dispfile = new String(dlFileName.getBytes(Encoding.UTF_8), Encoding.ISO8859_1);
        } else {
            //FireFox・他
            log__.debug("Firefox・その他としてダウンロード");
            dispfile = MimeUtility.encodeWord(dlFileName, Encoding.UTF_8, "B");
        }

        // 拡張子からcontentTypeを獲得 (元のファイル名で判定)
        String contentType = ContentType.getContentType(dlFileName);
        // contentTypeを出力
        if (ieFlg && !setCharset) {
            //ブラウザが解釈できる文字コードの文字列をセット(WINDOWS_31JではなくSHIFT_JIS)
            res.setContentType(contentType + "; charset=" + Encoding.SHIFT_JIS);
        } else {
            //
            String charset = Encoding.getHtmlCharset(encoding);
            res.setContentType(contentType + "; charset=" + charset);
        }

        // ファイル名の送信
        if (tempFlg) {
            res.setHeader(
                "Content-disposition",
                "attachment; filename=\"" + dispfile + "\"");
        } else {
            res.setHeader(
                "Content-disposition",
                "inline; filename=\"" + dispfile + "\"");
        }

        // ファイルサイズ
        res.setHeader(
                "Content-length",
                Long.toString(downFile.length()));

        // ファイル内容の出力
        ServletOutputStream out = null;
        FileInputStream fin = null;
        try {
            out = res.getOutputStream();
            fin = new FileInputStream(downFile);
            int size;
            while ((size = fin.read(buffer)) != -1) {
                out.write(buffer, 0, size);
                out.flush();
            }
            out.flush();
        } catch (java.net.SocketException e) {
            log__.error("既にソケットがクローズされている場合にスロー", e);
            throw e;
        } catch (Exception e) {
            if (!e.toString().startsWith("ClientAbortException")) {
                log__.error("ファイルの取得に失敗 = " + downFile, e);
                throw e;
            }
        } finally {
            //ストリームのクローズ
            try {
                if (fin != null) {
                     fin.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e2) {
            }
        }

        return;
    }

    /**
     * <br>[機  能] ファイルダウンロード時に最適な文字コードを返す。
     * <br>[解  説] ブラウザの種類により文字コードを判定
     * <br>         IEの場合はWindows31J。
     * <br>         IE以外の場合は画面の表示で使用している文字コードを返す
     * <br>[備  考]
     * @param req HttpServletRequest
     * @return String 文字コード
     */
    public static String getFileDownloadEncoding(HttpServletRequest req) {
        if (BrowserUtil.isIe(req)) {
            //IEの場合
            return Encoding.WINDOWS_31J;
        }
        return req.getCharacterEncoding();
    }
}