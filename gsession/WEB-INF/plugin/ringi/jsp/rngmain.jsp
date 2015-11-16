<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/rngmain.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.13" /></title>
</head>

<body class="body_03">

<html:form action="ringi/rngmain">

<logic:notEmpty name="rngmainForm" property="rngDataList">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="rngCmdMode" value="0">
<input type="hidden" name="rngDspMode" value="1">
<input type="hidden" name="rngApprMode" value="0">

<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010orderKey" />

<bean:define id="procMode" name="rngmainForm" property="rngProcMode" />

<!-- body -->
<table class="tl0" width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td class="header_7D91BD_left" colspan="4" width="100%">
<img src="../ringi/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="rng.rngmain.03" />"><a href="<bean:write name="rngmainForm" property="rngTopUrl" />"><gsmsg:write key="rng.rngmain.03" /></a>
  </td>
  </tr>

  <tr class="text_base2">
    <th width="59%" class="td_type30"><gsmsg:write key="cmn.title" /></th>
    <th width="15%" class="td_type30"><gsmsg:write key="rng.47" /></th>
    <th width="13%" class="td_type30"><gsmsg:write key="rng.rngmain.02" /></th>
    <th width="13%" class="td_type30"><gsmsg:write key="rng.rngmain.01" /></th>
  </tr>

  <% String[] trclass = {"td_type1", "td_type25_2"}; %>

  <logic:iterate id="rngData" name="rngmainForm" property="rngDataList" indexId="idx" scope="request">

  <% String apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPR); %>
  <logic:equal name="rngData" property="rncType" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_RNCTYPE_APPL) %>">
    <% apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPL); %>
  </logic:equal>

<% String apprUserClass = "";%>
<logic:equal name="rngData" property="apprUserDelFlg" value="true">
<% apprUserClass = "text_appruser_del"; %>
</logic:equal>

  <tr class="text_base2">
    <td align="left" class="<%= trclass[idx.intValue() % 2] %>"><a href="#" onclick="clickRingi('detail', 0, <%= apprMode %>, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
    <td align="left" class="<%= trclass[idx.intValue() % 2] + " " + apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
    <td align="center" class="<%= trclass[idx.intValue() % 2] %>"><bean:write name="rngData" property="strRngAppldate" /></td>
    <td align="center" class="<%= trclass[idx.intValue() % 2] %>"><bean:write name="rngData" property="strRcvDate" /></td>
  </tr>

  </logic:iterate>

</table>

</logic:notEmpty>

</html:form>

</body>
</html:html>
