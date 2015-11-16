package jp.co.sjts.util;

import java.io.StringReader;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * <br>[機  能] HTMLの予約語に対する変換処理をおこないます。
 * <br>         また「改行からBRタグ」「BRタグから改行」等の変更を行います。
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class StringUtilHtml {

    /** wbrタグを追加する基準となる文字列 */
    public static final String[] AFTER_WBR_STR = {
                                    " ", ".", ",", "!", "?", ":", ";", "/",
                                    "+", "-", "=", "~", "(", ")", "[", "]",
                                    "　", "。", "、",  "！", "？", "：", "；", "／",
                                    "＋", "－", "＝", "～", "（", "）", "「", "」", "［", "］"
                                                    };
    /** wbrタグを追加する際に使用する正規表現 */
    public static Pattern pattern_AFTER_WBR_STR = null;

    static {
        StringBuilder sb = new StringBuilder("[");
        for (String str : AFTER_WBR_STR) {
            sb.append("\\");
            sb.append(str);
        }
        sb.append("]+");

        pattern_AFTER_WBR_STR = Pattern.compile(sb.toString(), Pattern.CASE_INSENSITIVE);
        sb.toString();
    }

    /**
     * <br>[機  能] form から受け取ったメッセージをhtmlで正常に
     * <br>         表示できる文字列に変換します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param  str     変換元の文字列
     * @return         変換済みの文字列
     */
    public static String transToHTml(String str) {
        if (str == null) {
                return null;
        }

        StringBuilder retSB = new StringBuilder();
        StringCharacterIterator stit = new StringCharacterIterator(str);

        for (
            char c = stit.first();
            c != CharacterIterator.DONE;
            c = stit.next()
            ) {

            switch(c) {
            case ' ':
                retSB.append("&nbsp;");
                break;
            case '<':
                retSB.append("&lt;");
                break;
            case '>':
                retSB.append("&gt;");
                break;
            case '"':
                retSB.append("&quot;");
                break;
            case '\n':
                retSB.append("<BR>");
                break;
            case '\r':
                if (stit.next() != '\n') {
                    stit.previous();
                }
                retSB.append("<BR>");
                break;
            default :
                retSB.append(c);
                break;
            }
        }
        return retSB.toString();
    }

    /**
     * <br>[機  能] form から受け取ったメッセージをhtmlで正常に
     * <br>         表示できる文字列に変換します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param  str     変換元の文字列
     * @return         変換済みの文字列
     */
    public static String transToHTmlPlusAmparsant(String str) {
        if (str == null) {
                return null;
        }

        StringBuilder retSB = new StringBuilder();
        StringCharacterIterator stit = new StringCharacterIterator(str);

        for (
            char c = stit.first();
            c != CharacterIterator.DONE;
            c = stit.next()
            ) {

            switch(c) {
            case '&':
                retSB.append("&amp;");
                break;
            case ' ':
                retSB.append("&nbsp;");
                break;
            case '<':
                retSB.append("&lt;");
                break;
            case '>':
                retSB.append("&gt;");
                break;
            case '"':
                retSB.append("&quot;");
                break;
            case '\n':
                retSB.append("<BR>");
                break;
            case '\r':
                if (stit.next() != '\n') {
                    stit.previous();
                }
                retSB.append("<BR>");
                break;
            default :
                retSB.append(c);
                break;
            }
        }
        return retSB.toString();
    }

    /**
     * <br>[機  能] form から受け取ったメッセージをhtmlで正常に
     * <br>         表示できる文字列に変換します。
     * <br>         また、URLをリンクに置き換えます
     * <br>[解  説]
     * <br>[備  考]
     * @param str 変換元の文字列
     * @return    変換済みの文字列
     */
    public static String transToHTmlPlusAmparsantAndLink(String str) {
        if (!StringUtil.isNullZeroString(str)) {
            Pattern pattern = Pattern.compile(StringUtil.URL_PATTERN,
                                            Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(str);
            String replaceStr = "";
            String bufReq = str.toString();

            while (matcher.find()) {
                String url = matcher.group();

                int urlIndex = bufReq.indexOf(url) + url.length();
                String urlStr = transToHTmlPlusAmparsant(bufReq.substring(0, bufReq.indexOf(url)));
                String linkStr = "<A HREF=\"" + url + "\"";
                linkStr += " target=\"_blank\"";

                int cornIndex = url.indexOf("://") + 3;
                String urlBf = url.substring(0, cornIndex);
                String urlAf = url.substring(cornIndex);
                String viewUrl = urlAf.replace("/", "/<wbr>");
                viewUrl = viewUrl.replace("%", "%<wbr>");
                linkStr += ">" + urlBf + viewUrl + "</A>";

                replaceStr += urlStr + linkStr;
                bufReq = bufReq.substring(urlIndex);
            }

            if (!StringUtil.isNullZeroString(bufReq)) {
                replaceStr += transToHTmlPlusAmparsant(bufReq);
            }

            if (replaceStr.length() > 0) {
                str = replaceStr;
            }
        }

        return str;
    }

    /**
     * <br>[機  能] form から受け取ったメッセージをhtmlで正常に
     * <br>         表示できる文字列に変換します。(TEXT AREA専用)
     * <br>[解  説]
     * <br>[備  考] transToHTmlとの違いは改行コードを処理しない点です。
     *
     * @param  str     変換元の文字列
     * @return         変換済みの文字列
     */
    public static String transToHTmlForTextArea(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder retSB = new StringBuilder();
        StringCharacterIterator stit = new StringCharacterIterator(str);

        for (
            char c = stit.first();
            c != CharacterIterator.DONE;
            c = stit.next()
            ) {

            switch(c) {
            case '<':
                retSB.append("&lt;");
                break;
            case '>':
                retSB.append("&gt;");
                break;
            case '"':
                retSB.append("&quot;");
                break;
            case '&':
                retSB.append("&amp;");
                break;
            default :
                retSB.append(c);
                break;
            }
        }
        return retSB.toString();
    }

    /**
     * <br>[機  能] HTMLへエスケープされた文字を元のテキストへ変換します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param msg 元に戻す文字列
     * @return 変換後の文字列
     */
    public static String transToText(String msg) {
        msg = replaceString(msg, "&lt;", "<");
        msg = replaceString(msg, "&gt;", ">");
        msg = replaceString(msg, "&quot;", "\"");
        msg = replaceString(msg, "<BR>", "\r\n");
        msg = replaceString(msg, "<br>", "\r\n");
        msg = replaceString(msg, "<br />", "\r\n");
        msg = replaceString(msg, "&amp;", "&");
        msg = replaceString(msg, "&nbsp;", " ");
        return msg;
    }

    /**
     * <br>[機  能] HTMLへエスケープされた文字を元のテキストへ変換します。
     * <br>[解  説] &lt;BR&gt;タグを改行へ変換した上でタグを取り除きます。
     * <br>またHTML予約語&amp\;、&nbsp\;も元の文字列（&,スペース）へ変換します。
     * <br>[備  考]
     *
     * @param msg 元に戻す文字列
     * @return 変換後の文字列
     */
    public static String transToTextAndDeleteTag(String msg) {
        //BR 改行変換
        msg = transBRtoCRLF(msg);
        //HTML予約語変換
        msg = replaceString(msg, "&gt;", ">");
        msg = replaceString(msg, "&quot;", "\"");
        msg = replaceString(msg, "&amp;", "&");
        msg = replaceString(msg, "&nbsp;", " ");
        //
        msg = delHTMLTag(msg);
        return msg;
    }

    /**
     * <br>[機  能] 引数でわたされた文字列中にリターンコードがある時
     *              リターンコードを"<BR>"へ変換した文字列を返す。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param str 変換元の文字列
     * @return 変換済みの文字列
     */
    public static String returntoBR(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder retSB = new StringBuilder();
        StringCharacterIterator stit = new StringCharacterIterator(str);

        for (
            char c = stit.first();
            c != CharacterIterator.DONE;
            c = stit.next()) {
            switch(c) {

            case '\n':
                retSB.append("<BR>");
                break;
            case '\r':
                if (stit.next() != '\n') {
                        stit.previous();
                }
                retSB.append("<BR>");
                break;
            default :
                retSB.append(c);
                break;
            }
        }
        return retSB.toString();
    }

    /**
     * <br>[機  能] 引数でわたされた文字列中に"<BR>"がある時
     *              <BR>をcrlfへ変換した文字列を返す。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param msg 変換元の文字列
     * @return            変換済みの文字列
     */
    public static String transBRtoCRLF(String msg) {
        msg = replaceString(msg, "<BR>", "\r\n");
        msg = replaceString(msg, "<br>", "\r\n");
        msg = replaceString(msg, "<br />", "\r\n");
        return msg;
    }

    /**
     * <br>[機  能] 指定した文字列を指定された文字列に置き換えます。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param msg 元の文字列
     * @param oldStr 置換対象の文字列
     * @param newStr おきかえる文字列
     * @return 置換後の文字列
     */
    public static String replaceString(
        String msg,
        String oldStr,
        String newStr) {

        int start = 0;
        int end = 0;
        StringBuilder retSB = new StringBuilder();

        if ((msg == null) || (oldStr == null) || (newStr == null)) {
            return null;
        }

        int oldLength = oldStr.length();

        while (true) {
            end = msg.indexOf(oldStr, start);
            if (end == -1) { //置換対象の文字列がない場合はそのまま返す
                retSB.append(msg.substring(start));
                break;
            } else {
                retSB.append(msg.substring(start, end));
                retSB.append(newStr);
                start = end + oldLength;
            }
        }
        return retSB.toString();
    }

    /**
     * <br>[機  能] 指定した文字列にwbrタグを追加する
     * <br>[解  説] 指定した文字長 or 規定の文字列の後ろ
     * <br>[備  考] 文字列のescapeも同時に行う
     * @param str 変換元の文字列
     * @param wbrLen 1行の最大文字長 この文字長を超えた場合、wbrタグを追加する
     * @return 変換後の文字列
     */
    public static String transToHTmlWithWbr(String str, int wbrLen) {
        if (!StringUtil.isNullZeroString(str)) {
            Matcher matcher = pattern_AFTER_WBR_STR.matcher(str);
            StringBuilder replaceStr = new StringBuilder("");

            while (matcher.find()) {
                String matchStr = matcher.group();
                int matchIndex = str.indexOf(matchStr) + 1;

                matchStr = str.substring(0, matchIndex);
                str = str.substring(matchIndex, str.length());
                while (matchStr.length() > wbrLen) {
                    replaceStr.append(transToHTml(matchStr.substring(0, wbrLen)));
                    replaceStr.append("<wbr/>");
                    matchStr = matchStr.substring(wbrLen, matchStr.length());
                }

                replaceStr.append(transToHTml(matchStr));
                replaceStr.append("<wbr/>");
            }

            if (str.length() > 0) {
                while (str.length() > wbrLen) {
                    replaceStr.append(transToHTml(str.substring(0, wbrLen)));
                    replaceStr.append("<wbr/>");
                    str = str.substring(wbrLen, str.length());
                }
                replaceStr.append(transToHTml(str));
            }
            str = replaceStr.toString();
            replaceStr = null;
            matcher = null;
        }

        return str;
    }

    /**
     * <br>[機  能] 文字列内にHTMLのタグが存在した場合、タグを削除した文字列を返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param tagStr htmlタグを含む文字列
     * @return タグを含まない文字列(タグを削除した)
     */
    public static String deleteHtmlTag(String tagStr) {
        String ret = null;
        try {
            DOMParser parser = new DOMParser();
            StringReader sr = new StringReader(tagStr);
            parser.parse(new InputSource(sr));
            Node node = parser.getDocument();
            //
            ret = __deleteHtmlTagNode(node, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * <br>[機  能] Nodeについて再起処理を行い、CDATA_SECTION_NODE, TEXT_NODEのテキスト情報のみをバッファに格納する。
     * <br>[解  説]
     * <br>[備  考]
     * @param node XMLのNode
     * @param buf テキスト情報を格納するバッファ。nullの場合は作成する。
     * @return Nodeより取得したテキスト
     */
    private static String __deleteHtmlTagNode(Node node, StringBuilder buf) {
        if (buf == null) {
            buf = new StringBuilder();
        }

        if (node == null) {
            return buf.toString();
        }
        short type = node.getNodeType();
        if (Node.CDATA_SECTION_NODE == type || Node.TEXT_NODE == type) {
            String text = node.getNodeValue();
            if (text != null || text.trim().length() != 0) {
                buf.append(text.trim());
            }
        }

        NodeList nlist = node.getChildNodes();
        for (int i = 0; i < nlist.getLength(); i++) {
            Node cnode = nlist.item(i);
            __deleteHtmlTagNode(cnode, buf);
        }
        return buf.toString();
    }

    /**
     * <br>[機  能] HTMLの文字列をテキスト情報のみの文字列にする。
     * <br>[解  説]
     * <br>[備  考]
     * @param htmlStr 置き換え文字列
     * @return テキスト
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        return htmlStr.trim();
    }
}
