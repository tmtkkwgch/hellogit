package jp.co.sjts.util.archive;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 *
 * <br>[機 能] zip圧縮、解凍を行うクラス
 * <br>[解 説]
 * <br>[備 考]
 *
 * @author JTS
 */
public class ZipUtil {

    /** ファイル作成時のバッファーサイズ(4MB) */
    public static final int BUFFERSIZE_CREATE = 4 * 1024 * 1024;
    /**
     * [機 能] ファイルをZIP形式で圧縮する。<br>
     * [解 説] <br>
     * [備 考] <br>
     * @param encoding ファイルシステムのエンコーディング
     * @param zipFilePath ZIP出力ファイル名
     * @param srcFilePath 入力ファイル名
     * @throws IOException IOエラー
     */
    public static void zipFile(String encoding, String srcFilePath,
            String zipFilePath) throws IOException {
        File file = new File(srcFilePath);

        if (file.isDirectory()) {
            throw new IOException("パスにディレクトリが指定されています。");
        }
        String[] targetFiles = {srcFilePath};
        __createZip(encoding, zipFilePath, targetFiles);
    }

    /**
     * [機 能] ファイルをZIP形式で圧縮する。<br>
     * [解 説] <br>
     * [備 考] Deflater<br>
     *
     * @param zipFilePath ZIP出力ファイル名
     * @param srcFilePath 入力ファイル名
     * @throws IOException IOエラー
     */
    public static void zipDeflaterFile(String srcFilePath, String zipFilePath)
    throws IOException {

        File file = new File(srcFilePath);

        if (file.isDirectory()) {
            throw new IOException("パスにディレクトリが指定されています。");
        }

        FileInputStream fis = null;
        DeflaterOutputStream dos = null;

        int length;
        byte[] buffer = new byte[BUFFERSIZE_CREATE];

        try {
            fis = new FileInputStream(file);
            dos = new DeflaterOutputStream(
                    new BufferedOutputStream(new FileOutputStream(zipFilePath)));

            while ((length = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, length);
            }

            dos.finish();

        } finally {
            if (fis != null) {
                fis.close();
            }
            if (dos != null) {
                dos.close();
            }
        }
    }

    /**
     * [機 能] 単一のディレクトリをZIPする。<br>
     * [解 説] <br>
     * [備 考] <br>
     * @param encoding ファイルシステムのエンコーディング
     * @param srcDirPath 入力ディレクトリ名
     * @param zipFilePath ZIP出力ファイル名
     * @throws IOException IOエラー
     */
    public static void zipDir(String encoding, String srcDirPath,
            String zipFilePath) throws IOException {
        File dir = new File(srcDirPath);

        if (dir.isFile()) {
            throw new IOException("パスにファイルが指定されています。");
        }
        String[] targetFiles = {srcDirPath};
        __createZip(encoding, zipFilePath, targetFiles);
    }

    /**
     * [機 能] 複数のディレクトリをZIPする。<br>
     * [解 説] <br>
     * [備 考] <br>
     * @param encoding ファイルシステムのエンコーディング
     * @param srcDirPathList 入力ディレクトリ名の一覧
     * @param zipFilePath ZIP出力ファイル名
     * @throws IOException IOエラー
     */
    public static void zipDir(String encoding, String[] srcDirPathList,
                                String zipFilePath) throws IOException {

        for (String srcDirPath : srcDirPathList) {
            File dir = new File(srcDirPath);
            if (dir.isFile()) {
                throw new IOException("Target Path is File : [" + srcDirPath + "]");
            }
        }
        String[] targetFiles = srcDirPathList;
        __createZip(encoding, zipFilePath, targetFiles);
    }

    /**
     * [機 能] targetFilesをZIP圧縮してzipFileに出力する。<br>
     * [解 説] <br>
     * [備 考] <br>
     * @param encoding ファイルシステムのエンコーディング
     * @param zipFilePath ZIP出力ファイル名
     * @param targetFiles 入力ファイル名(ディレクトリ)配列
     * @throws IOException 入出力例外
     */
    private static void __createZip(String encoding, String zipFilePath,
            String[] targetFiles) throws IOException {

        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFilePath)));
            out.setEncoding(encoding);
            for (int i = 0; i < targetFiles.length; i++) {
                File file = new File(targetFiles[i]);
                int deleteLength = file.getPath().length() - file.getName().length();
                __createZip(encoding, out, file, deleteLength);
            }
        } finally {
            out.close();
        }
    }

    /**
     * [機 能] targetFileをoutのZIPエントリへ出力する。<br>
     * [解 説] <br>
     * [備 考] <br>
     * @param encoding ファイルシステムのエンコーディング
     * @param out ZIP出力先
     * @param targetFile 入力ファイル名(ディレクトリ)
     * @param deleteLength Zipエントリから削除するパスの長さ
     * @throws IOException 入出力例外
     */
    private static void __createZip(String encoding, ZipOutputStream out,
            File targetFile, int deleteLength) throws IOException {

        if (targetFile.isDirectory()) {
            File[] files = targetFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                __createZip(encoding, out, files[i], deleteLength);
            }
        } else {
            ZipEntry target = new ZipEntry(__getEntryPath(targetFile, deleteLength));
            out.putNextEntry(target);
            byte[] buf = new byte[BUFFERSIZE_CREATE];
            int count;
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(
                        new FileInputStream(targetFile));
                while ((count = in.read(buf)) != -1) {
                    out.write(buf, 0, count);
                }
            } finally {
                in.close();
                out.closeEntry();
            }
        }
    }

    /**
     * [機 能] ZIPエントリパスを返す。<br>
     * [解 説] <br>
     * [備 考] <br>
     * @param file ZIPエントリ対象ファイル
     * @param deleteLength Zipエントリから削除するパスの長さ
     * @return ZIPエントリのパス
     */
    private static String __getEntryPath(File file, int deleteLength) {
        return file.getPath().replaceAll("\\\\", "/").substring(deleteLength);
    }
}