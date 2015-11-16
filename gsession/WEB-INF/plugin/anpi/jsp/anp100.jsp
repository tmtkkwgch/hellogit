<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthTitle   = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SUBJECT); %>
<% String maxLengthSubject = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SUBJECT); %>
<% String maxLengthText1   = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_TEXT1); %>
<% String maxLengthText2   = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_TEXT2); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03" onload="showLengthId($('#inputstr1')[0],<%=maxLengthText1 %>,'inputlength1');showLengthId($('#inputstr2')[0],<%=maxLengthText2 %>,'inputlength2');">
<html:form action="/anpi/anp100">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anp090SelectSid" />

<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp100.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <logic:notEmpty name="anp100Form" property="anp090SelectSid" >
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('anp100delete');">
            </logic:notEmpty>
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp100excute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp100back');">
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
                <html:text name="anp100Form" property="anp100Title" maxlength="<%=maxLengthTitle %>" styleClass="text_base" styleId="title" style="width:385px;" />
            </td>
        </tr>

        <tr>
            <!-- 件名 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="cmn.subject"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
                <html:text name="anp100Form" property="anp100Subject" maxlength="<%=maxLengthSubject %>" styleClass="text_base" styleId="subject" style="width:385px;" />
            </td>
        </tr>

        <tr>
            <!-- 本文１ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.body1"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" width="100%">
                <% String onkey1 = "showLengthStr(value," + maxLengthText1 + ",'inputlength1');"; %>
                <html:textarea styleId="inputstr1" name="anp100Form" property="anp100Text1" style="width:676px;" rows="10" styleClass="text_base" onkeyup="<%= onkey1 %>" />
                <br>
                <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span>
                <span id="inputlength1" class="font_string_count">0</span>&nbsp;
                <span class="font_string_count_max">/&nbsp;<%=maxLengthText1 %>&nbsp;<gsmsg:write key="cmn.character" /></span>
            </td>
        </tr>

        <tr>
            <!-- 本文２ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.body2"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" width="100%">
                <% String onkey2 = "showLengthStr(value," + maxLengthText2 + ",'inputlength2');"; %>
                <html:textarea styleId="inputstr2" name="anp100Form" property="anp100Text2" style="width:676px;" rows="8" styleClass="text_base" onkeyup="<%= onkey2 %>" />
                <br>
                <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span>
                <span id="inputlength2" class="font_string_count">0</span>&nbsp;
                <span class="font_string_count_max">/&nbsp;<%=maxLengthText2 %>&nbsp;<gsmsg:write key="cmn.character" /></span>
            </td>
        </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <logic:notEmpty name="anp100Form" property="anp090SelectSid" >
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('anp100delete');">
            </logic:notEmpty>
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp100excute');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp100back');">
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