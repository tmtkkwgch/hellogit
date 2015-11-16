<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthMail = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_MAIL); %>
<% String maxLengthTel = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_TEL); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.preferences.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp050">
<!-- BODY -->
<input type="hidden" name="CMD">

<html:hidden property="backScreen" />
<html:hidden property="anp050UserSid" />

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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.setting.contact"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
	        <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp050excute');">
	        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp050back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

	<!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
            <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="cmn.mailaddress"/><span class="text_r2"></span></span>
            </td>
            <td width="80%" valign="middle" align="left" class="td_wt table_pad" >
              <html:text name="anp050Form" property="anp050Mail" maxlength="<%= maxLengthMail %>" styleClass="text_base" style="width:397px;" />
            </td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
            <span class="text_bb1"><gsmsg:write key="cmn.tel"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" width="100%">
              <html:text name="anp050Form" property="anp050TelNo" maxlength="<%= maxLengthTel %>" styleClass="text_base" style="width:217px;" />
            </td>
        </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td align="right">
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp050excute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp050back');">
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