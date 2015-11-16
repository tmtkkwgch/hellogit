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
<html:form action="/anpi/anp100kn">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anp090SelectSid" />

<html:hidden property="anp100Title" />
<html:hidden property="anp100Subject" />
<html:hidden property="anp100Text1" />
<html:hidden property="anp100Text2" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp100kn.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('anp100knexcute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp100knback');">
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
            <!-- テンプレート名 -->
            <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp100.02"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td width="80%" valign="middle" align="left" class="td_wt table_pad" >
                <span class="text_base"><bean:write name="anp100knForm" property="anp100Title" /></span>
            </td>
        </tr>

        <tr>
            <!-- 件名 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="cmn.subject"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
                <span class="text_base"><bean:write name="anp100knForm" property="anp100Subject" /></span>
            </td>
        </tr>

        <tr>
            <!-- 本文１ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.body1"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
                <span class="text_base"><bean:write name="anp100knForm" property="anp100knDspText1" filter="false" /></span>
            </td>
        </tr>

        <tr>
            <!-- 本文２ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.body2"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
                <span class="text_base"><bean:write name="anp100knForm" property="anp100knDspText2" filter="false" /></span>
            </td>
        </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('anp100knexcute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp100knback');">
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