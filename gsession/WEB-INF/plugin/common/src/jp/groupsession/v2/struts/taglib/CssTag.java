package jp.groupsession.v2.struts.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.taglib.bean.WriteTag;

/**
 * <br>[機  能] linkタグを作成する
 * <br>[解  説] 読み込むCSSファイルを設定する
 * <br>[備  考]
 *
 * @author JTS
 */
public class CssTag extends WriteTag {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(CssTag.class);

    /** デフォルトCtmSid */
    public static final int DEFAULT_CTM_SID = 1;
    /** デフォルトテーマパス */
    public static final String DEFAULT_THEME_PATH = "common/css/theme1";
    /** pageContext */
    private PageContext pageContext__;
    /** テーマパス */
    private String themePath__ = null;
    /** ファイル名 */
    private String fileName__ = null;
    /** テーマフラグ */
    private Boolean themeFlg__ = false;

    /**
     * @param fileName 設定する fileName。
     */
    public void setFilename(String fileName) {
        fileName__ = fileName;
    }
    /**
     * @return fileName を戻します。
     */
    public String getFilename() {
        return fileName__;
    }

    /**
     * @return pageContext を戻します。
     */
    public PageContext getPageContext() {
        return pageContext__;
    }
    /**
     * @param pageContext 設定する pageContext。
     */
    public void setPageContext(PageContext pageContext) {
        pageContext__ = pageContext;
    }
    /**
     * <p>タグが読み込まれた時
     * @throws JspException 実行例外
     * @return SKIP_BODY
     */
    public int doStartTag() throws JspException {

        BaseUserModel smodel = new BaseUserModel();

        JspWriter out = pageContext__.getOut();

        if (pageContext__.getSession() == null) {
            try {
                out.print("<link rel=stylesheet href='../" + DEFAULT_THEME_PATH + "/"
                 + fileName__ + "?" + GSConst.VERSION_PARAM + "' type='text/css'>");
            } catch (Exception e) {
//                    throw new JspException(e.getMessage());
                log__.debug("Exception1", e);
            }
        } else if (pageContext__.getSession().getAttribute(GSConst.SESSION_KEY) == null) {
            try {
                out.print("<link rel=stylesheet href='../" + DEFAULT_THEME_PATH + "/"
                 + fileName__ + "?" + GSConst.VERSION_PARAM + "' type='text/css'>");
            } catch (Exception e) {
//                    throw new JspException(e.getMessage());
                log__.debug("Exception2", e);
            }
        } else {

            try {
                smodel = (BaseUserModel) pageContext__.getSession()
                        .getAttribute(GSConst.SESSION_KEY);
                themePath__ = smodel.getCtmPath();
                if (themePath__ == null) {
                    themePath__ = DEFAULT_THEME_PATH;
                }

                out.print("<link rel=stylesheet href='../" + themePath__ + "/"
                        + fileName__ + "?" + GSConst.VERSION_PARAM + "'"
                        + " type='text/css'>");

                themeFlg__ = true;

            } catch (Exception e) {
                  //throw new JspException(e.getMessage());
                log__.debug("Exception3", e);
            } finally {
                if (themeFlg__ == false) {
                    try {
                        out.print("<link rel=stylesheet href='../" + DEFAULT_THEME_PATH + "/"
                         + fileName__ + "?" + GSConst.VERSION_PARAM + "' type='text/css'>");
                    } catch (Exception e) {
    //                    throw new JspException(e.getMessage());
                        log__.debug("Exception4", e);
                    }
                }
            }
        }
        return SKIP_BODY;
    }

    /**
     * <p>タグが読み終わった時
     * @throws JspException 実行例外
     * @return EVAL_PAGE
     */
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
