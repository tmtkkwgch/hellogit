<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp080kn">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anp080BackScreen" />
<html:hidden property="anp080BaseUrl" />
<html:hidden property="anp080SendMail" />
<html:hidden property="anp080SendHost" />
<html:hidden property="anp080SendPort" />
<html:hidden property="anp080SendSsl" />
<html:hidden property="anp080SmtpAuth" />
<html:hidden property="anp080SendUser" />
<html:hidden property="anp080SendPass" />

<html:hidden property="anp080UrlSetKbn" />
<html:hidden property="anp080SvBaseUrlAuto" />

<logic:notEmpty name="anp080knForm" property="anp080AdmUserList" scope="request">
    <logic:iterate id="ulBean" name="anp080knForm" property="anp080AdmUserList" scope="request">
    <input type="hidden" name="anp080AdmUserList" value="<bean:write name="ulBean" />">
    </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp080kn.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="anp.anp080kn.02"/>" class="btn_base1" onClick="buttonPush('anp080knconTest');">
            <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('anp080knexcute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp080knback');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
            <!-- 返信基本URL -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.02"/><span class="text_r2">※</span></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <span class="text_base">
                <logic:equal name="anp080knForm" property="anp080UrlSetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.URL_SETTING_AUTO) %>">
                <bean:write name="anp080knForm" property="anp080SvBaseUrlAuto" />
                </logic:equal>
                <logic:equal name="anp080knForm" property="anp080UrlSetKbn" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.URL_SETTING_MANUAL) %>">
                <bean:write name="anp080knForm" property="anp080BaseUrl" />
                </logic:equal>
                </span>
            </td>
        </tr>

        <tr>
            <!-- 送信メールアドレス -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.06"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <span class="text_base"><bean:write name="anp080knForm" property="anp080SendMail" /></span>
            </td>
        </tr>

        <tr>
            <!-- メール送信サーバ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.smtp.server"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td align="left" class="td_wt">
                <span class="text_base">
                <bean:write name="anp080knForm" property="anp080SendHost" />&nbsp;
                <gsmsg:write key="cmn.port.number" />:<bean:write name="anp080knForm" property="anp080SendPort" />
                <logic:equal name="anp080knForm" property="anp080SendSsl" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEND_SSL_USE) %>">
                <br><gsmsg:write key="anp.anp080kn.03"/>
                </logic:equal>
                </span>
            </td>
        </tr>

        <tr>
            <!-- SMTP認証ON/OFF -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.08"/></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
            </td>
            <td align="left" class="td_wt">
                <span class="text_base">
                <logic:equal name="anp080knForm" property="anp080SmtpAuth" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SMTP_AUTH_YES) %>" ><gsmsg:write key="anp.anp080.09"/></logic:equal>
                <logic:equal name="anp080knForm" property="anp080SmtpAuth" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SMTP_AUTH_NOT) %>" ><gsmsg:write key="anp.anp080.10"/></logic:equal>
                </span>
            </td>
        </tr>

        <tr>
            <!-- メール送信サーバ ユーザ名 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.11"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <span class="text_base"><bean:write name="anp080knForm" property="anp080SendUser" /></span>
            </td>
        </tr>

        <tr>
            <!-- メール送信サーバ パスワード -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.12"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <span class="text_base"><bean:write name="anp080knForm" property="anp080knDspSendPass" /></span>
            </td>
        </tr>

        <tr>
            <!-- 安否確認管理者 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp080.13"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
                <logic:notEmpty name="anp080knForm" property="anp080AdmUserLabel">
                  <span class="text_base">
                  <logic:iterate id="urBean" name="anp080knForm" property="anp080AdmUserLabel" indexId="idx">
                     <bean:write name="urBean" property="label"/><br>
                  </logic:iterate>
                  </span>
                </logic:notEmpty>
            </td>
        </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="<gsmsg:write key="anp.anp080kn.02"/>" class="btn_base1" onClick="buttonPush('anp080knconTest');">
            <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('anp080knexcute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp080knback');">
        </td>
        </tr>
    </table>

    </td>
    </tr>
    </table>

</td>
</tr>
</table>


</html:form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>