package jp.co.sjts.util.log.log4j;

import java.util.HashMap;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.io.IOTools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * <br>[機 能] Log4jの設定を行うユーティリティクラス
 * <br>[解 説]
 * <br>[備 考]
 * @author JTS
 */
public class Log4jConfig {
    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(Log4jConfig.class);

    /**
     * <p>指定した設定ファイルを読み込み、log4jのインスタンスを再設定します。
     * @param path log4j.xmlのパス
     */
    public static void reloadConfigFile(String path) {
        //設定ファイル
        DOMConfigurator.configure(path);
    }

    /**
     * <p>ログ出力先ディレクトリを変更する。
     * @param outPath 新たに設定するログ出力先(c:\log\wbs.log)
     * @param tempPath テンプレートファイルのパス(c:\log4_tmp.xml)
     * @param confPath log4jxmlの出力先(c:\log4j.xml)
     * @throws Exception ResourceNotFoundException,ParseErrorException
     */
    public static void setLogDir(String outPath, String tempPath,
            String confPath) throws Exception {
        //
        String outConfText = null;
        String confText = IOTools.readText(tempPath, Encoding.UTF_8);
        HashMap<Object, Object> pattern = new HashMap<Object, Object>();
        pattern.put("LOGFILE", outPath);
        try {
            outConfText = StringUtil.merge(confText, pattern);
            IOTools.writeText(confPath, Encoding.UTF_8, outConfText);
        } catch (ResourceNotFoundException e) {
            log__.error("", e);
            throw e;
        } catch (ParseErrorException e) {
            log__.error("", e);
            throw e;
        }
    }

    /**
     * <p>ログ出力先ディレクトリを変更する。
     * @param outPath 新たに設定するログ出力先(c:\log\wbs.log)
     * @param outErrPath 新たに設定するエラーログ出力先(c:\log\wbs.log)
     * @param tempPath テンプレートファイルのパス(c:\log4_tmp.xml)
     * @param confPath log4jxmlの出力先(c:\log4j.xml)
     * @throws Exception ResourceNotFoundException,ParseErrorException
     */
    public static void setLogDir(String outPath, String outErrPath, String tempPath,
            String confPath) throws Exception {
        //
        String outConfText = null;
        String confText = IOTools.readText(tempPath, Encoding.UTF_8);
        HashMap<Object, Object> pattern = new HashMap<Object, Object>();
        pattern.put("LOGFILE", outPath);
        pattern.put("LOGERRORFILE", outErrPath);
        try {
            outConfText = StringUtil.merge(confText, pattern);
            IOTools.writeText(confPath, Encoding.UTF_8, outConfText);
        } catch (ResourceNotFoundException e) {
            log__.error("", e);
            throw e;
        } catch (ParseErrorException e) {
            log__.error("", e);
            throw e;
        }
    }
}