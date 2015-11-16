package jp.co.sjts.util.struts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.log.log4j.Log4jConfig;
import jp.groupsession.v2.cmn.GSConst;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;

/**
 * <br>[機  能] ActionServletの拡張クラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class BaseServlet extends ActionServlet {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(BaseServlet.class);

    /**
     * <br>[機 能] 初期化処理
     * <br>[解 説]
     * <br>[備 考]
     * @exception ServletException 初期設定の取得に失敗した場合にスロー
     */
    public void init() throws ServletException {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
            log("INIT ERROR: " + e.getMessage());
            throw e;
        }
        initLogConfiguration();
        log__ = LogFactory.getLog(BaseServlet.class);
    }

    /**
     * <br>[機  能] ログ設定の再読込処理を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @exception ServletException 初期設定の取得に失敗した場合にスロー
     */
    public void initLogConfiguration() throws ServletException {
        //WEBアプリケーションのパス
        String prefix =  getServletContext().getRealPath("/");
        prefix = IOTools.setEndPathChar(prefix);

        //ログ出力先(ALL)
        String logOutPath = IOTools.replaceSlashFileSep(prefix + "WEB-INF/log/"
                + GSConst.LOGFILE_NAME + ".log");
        //ログ出力先(ERROR)
        String logErrOutPath = IOTools.replaceSlashFileSep(prefix + "WEB-INF/log/"
                + GSConst.LOGFILE_NAME_ERROR + ".log");
        //ログテンプレートファイルのパス
        String logTmpFile = IOTools.replaceSlashFileSep(prefix
                + "/WEB-INF/conf/log4j_tmp.xml");
        //log4j.xmlのパス(このファイルを読み込む)
        String logConfFile = IOTools.replaceSlashFileSep(prefix
                + "/WEB-INF/conf/log4j.xml");
        try {
            Log4jConfig.setLogDir(logOutPath, logErrOutPath, logTmpFile, logConfFile);
            Log4jConfig.reloadConfigFile(logConfFile);
        } catch (Exception e) {
            log__.error("log4jの設定に失敗", e);
            throw new ServletException(e);
        }
    }

    /**
     * <br>[機  能] プロセスメソッドをオーバーライドし、セッションに会社ドメインを保存
     * <br>[解  説]
     * <br>[備  考]
     * @param request リクエスト
     * @param response レスポンス
     */
    protected void process(HttpServletRequest request,
                            HttpServletResponse response) {
        try {
            GSHttpServletRequestWrapper wrapper
            = new GSHttpServletRequestWrapper(request);
            super.process(wrapper, response);
            wrapper.setDomain(request);
        } catch (Throwable e) {
            log__.error(e);
            log__.error("プロセスメソッドの実行に失敗");
        }

    }
}