package jp.groupsession.v2.anp.anp999;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.anp.AbstractAnpiMobileAction;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RedirectingActionForward;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 安否確認モバイル版のメッセージ画面のAction
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp999Action extends AbstractAnpiMobileAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp999Action.class);

    /**
     * <p>パラメータチェックを行うかどうかの判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @return true:チェックする,false:チェックしない
     */
    @Override
    public boolean isCheckParam() {
        return false;
    }

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                     org.apache.struts.action.ActionForm,
     *                     javax.servlet.http.HttpServletRequest,
     *                     javax.servlet.http.HttpServletResponse,
     *                     java.sql.Connection)
     */
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        ActionForward forward = null;
        Anp999Form uform = (Anp999Form) form;

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        String okBtn = NullDefault.getString(req.getParameter("ok"), "");
        String cancelBtn = NullDefault.getString(req.getParameter("cancel"), "");
        String backBtn = NullDefault.getString(req.getParameter("back"), "");
        log__.debug("cmd = " + cmd);

        RequestModel reqMdl = getRequestModel(req);
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(reqMdl);
        String opCode = gsMsg.getMessage("address.adr110.2");

        //システムエラー画面
        if (cmd.equals("SYSETEM_ERROR")) {
            log__.debug("システムエラーメッセージ");
            __setExceptionDispParam(map, uform, req, getServlet().getServletContext());
            //ログ出力
            cmnBiz.outPutSystemLog(map, reqMdl, gsMsg, con,
                    opCode, GSConstLog.LEVEL_ERROR,
                    gsMsg.getMessage("cmn.sys.error"));
            forward = map.getInputForward();

        //２重投稿エラー画面
        } else if (cmd.equals("SUBMIT_ERROR")
               || (uform.getTokenFlg() && !isTokenValid(req, true))) {
            log__.debug("２重投稿エラーメッセージ");
            __setSubmitErrorDispParam(map, uform, req);
            //ログ出力
            cmnBiz.outPutSystemLog(map, reqMdl, gsMsg, con,
                    opCode, GSConstLog.LEVEL_WARN,
                    gsMsg.getMessage("cmn.double.error"));
            forward = map.getInputForward();

        //権限不足エラー
        } else if (cmd.equals("POWER_ERROR")) {
            log__.debug("権限不足エラー");
            __setPowerErrorDispParam(map, uform, req);
            //ログ出力
            cmnBiz.outPutSystemLog(map, reqMdl, gsMsg, con,
                    opCode, GSConstLog.LEVEL_WARN,
                    gsMsg.getMessage("cmn.access.kengen"));
            forward = map.getInputForward();

        } else if (!StringUtil.isNullZeroString(okBtn)) {
            log__.debug("OKボタンクリック");
            log__.debug(uform.getUrlOK());
            if (cmnBiz.isCheckRedirectUrl(uform.getUrlOK())) {
                forward = new ActionForward(uform.getUrlOK());
            } else {
                forward = map.getInputForward();
            }

        } else if (!StringUtil.isNullZeroString(cancelBtn)) {
            log__.debug("キャンセルボタンクリック");
            log__.debug(uform.getUrlCancel());
            if (cmnBiz.isCheckRedirectUrl(uform.getUrlCancel())) {
                forward = new ActionForward(uform.getUrlCancel());
            } else {
                forward = map.getInputForward();
            }

        } else if (!StringUtil.isNullZeroString(backBtn)) {
            log__.debug("戻るボタンクリック");
            log__.debug(uform.getUrlBack());
            if (cmnBiz.isCheckRedirectUrl(uform.getUrlBack())) {
                forward = new ActionForward(uform.getUrlBack());
            } else {
                forward = map.getInputForward();
            }

        //リダイレクト
        } else if (!StringUtil.isNullZeroString(uform.getDirectURL())
                    && !cmd.equals("INFO")) {
            log__.debug("リダイレクト");
            //リダイレクト先が格納されているならそのURLへ遷移
            log__.debug("遷移先 = " + uform.getDirectURL());
            String url = uform.getDirectURL().toLowerCase();
            if (url.startsWith("http")) {
                //外部リンク
                forward = new RedirectingActionForward(url);
            } else {
                //内部リンク(パラメータが付加されていた場合、小文字変換は不都合)
                if (cmnBiz.isCheckRedirectUrl(uform.getDirectURL())) {
                    forward = new ActionForward(uform.getDirectURL());
                } else {
                    forward = map.getInputForward();
                }
            }
        } else {
            log__.debug("デフォルト");
            forward = map.getInputForward();
        }

        return forward;
    }

    /**
     * [機  能] システムエラー画面の設定処理を行う<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param context コンテキスト
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     * @throws InvocationTargetException チェック済み例外
     * @throws IllegalAccessException フィールドの設定に失敗
     */
    private void __setExceptionDispParam(
        ActionMapping map,
        Anp999Form form,
        HttpServletRequest req,
        ServletContext context
        ) throws UnsupportedEncodingException, IllegalAccessException, InvocationTargetException {

        //システムエラー画面パラメータの設定
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        MessageResources msgRes = getResources(req);
        form.setType(Anp999Form.TYPE_ERROR_REPORT);
        form.setIcon(Anp999Form.ICON_WARN); //警告アイコン
        form.setMessage(msgRes.getMessage("error.unknown.server"));
        __setErrorUrl(map, form);

        //エラーログを画面表示用に作成・セット
        Object oerror = req.getAttribute(GSConst.ERROR_KEY);
        if (oerror != null) {
            Throwable terror = (Throwable) oerror;
            StackTraceElement[] stels = terror.getStackTrace();
            StringBuilder ebuf = new StringBuilder();

            ebuf.append(terror.toString());
            ebuf.append("\r\n");
            for (StackTraceElement stel : stels) {
                ebuf.append("    at " + stel.toString());
                ebuf.append("\r\n");
            }
            ebuf.append("\r\n");
            Object oerrorPara = req.getAttribute(GSConst.ERROR_PARA_KEY);
            if (oerrorPara != null && oerrorPara instanceof String) {
                ebuf.append(gsMsg.getMessage("cmn.cmn999.10"));
                ebuf.append("\r\n");
                ebuf.append((String) oerrorPara);
                ebuf.append("\r\n");
            }

            form.setErrorLogOnly(ebuf.toString());

            try {
                // 現在のシステムプロパティを取得します
                Properties props = System.getProperties();
                StringBuilder ibuf = new StringBuilder();

                //OS
                String osName = props.getProperty("os.name");
                ibuf.append("OS： " + osName);

                //アーキテクチャー
                String osArch = props.getProperty("os.arch");
                ibuf.append(" " + osArch);

                //バージョン
                String osVersion = props.getProperty("os.version");
                ibuf.append(" " + osVersion);
                ibuf.append("\r\n");

                //サーブレットコンテナバージョン
                String servletInfo = context.getServerInfo();
                ibuf.append(gsMsg.getMessage("cmn.cmn999.9") + "： " + servletInfo);
                ibuf.append("\r\n");

                //javaバージョン
                String jversion = props.getProperty("java.version");
                ibuf.append("Java： " + jversion);

                //javaベンダー
                String jvendor = props.getProperty("java.vendor");
                ibuf.append(" " + jvendor);
                ibuf.append("\r\n");

                //メモリ情報
                String memoryInfo = __getMemoryInfo(getRequestModel(req));
                ibuf.append(memoryInfo);
                ibuf.append("\r\n");

                form.setDetailInfo(ibuf.toString());
                ebuf.append("\r\n");
                ebuf.append("\r\n");
                ebuf.append(ibuf.toString());
                ebuf.append("\r\n");
                ebuf.append("GroupSession Version "
                            + "http://groupsession.jp/wbs/report/rpt010.do");
                form.setErrorLog(ebuf.toString());

            } catch (Exception e) {

            }

            String urlReport = "http://groupsession.jp/wbs/report/rpt010.do";
            form.setUrlReport(urlReport);
        }
    }

    /**
     * [機  能] ２重投稿エラー画面の設定処理を行う<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @throws InvocationTargetException チェック済み例外
     * @throws IllegalAccessException フィールドの設定に失敗
     */
    private void __setSubmitErrorDispParam(
        ActionMapping map,
        Anp999Form form,
        HttpServletRequest req) throws IllegalAccessException, InvocationTargetException {

        //２重投稿エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        form.setType(Anp999Form.TYPE_OK);
        form.setIcon(Anp999Form.ICON_WARN);
        form.setMessage(msgRes.getMessage("error.access.double.submit"));
        __setErrorUrl(map, form);
    }

    /**
     * [機  能] 権限不足エラー画面の設定処理を行う<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     */
    private void __setPowerErrorDispParam(
        ActionMapping map,
        Anp999Form form,
        HttpServletRequest req) {

        //権限エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        form.setType(Anp999Form.TYPE_OK);
        form.setIcon(Anp999Form.ICON_WARN);
        form.setMessage(msgRes.getMessage("error.access.power.user"));
        __setErrorUrl(map, form);
    }

    /**
     * [機  能] 使用量、使用を試みる最大メモリ容量の情報を返します。<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param reqMdl リクエストモデル
     * @return mBuf 仮想マシンのメモリ情報
     */
    private String __getMemoryInfo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        DecimalFormat format1 = new DecimalFormat("#,###KB");
        DecimalFormat format2 = new DecimalFormat("##.#");
        long free = Runtime.getRuntime().freeMemory() / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024;
        long used = max - free;
        double ratio = (used * 100 / (double) max);
        StringBuilder mBuf = new StringBuilder();
        mBuf.append(gsMsg.getMessage("cmn.cmn999.11") + "：" + format1.format(used));
        mBuf.append(" (");
        mBuf.append(format2.format(ratio));
        mBuf.append(" %)");
        mBuf.append("\r\n");
        mBuf.append(gsMsg.getMessage("cmn.cmn999.12") + "：" + format1.format(max));
        return mBuf.toString();
    }

    /**
     * <br>[機  能] エラー時OKボタン遷移URL設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     */
    private void __setErrorUrl(ActionMapping map, Anp999Form form) {

        if (!StringUtil.isNullZeroString(form.getRetryURL())) {
            //遷移先がないため、リトライを促すようにする
            form.setUrlOK(form.getRetryURL());
        }
    }
}