<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
  String setteiClick         = jp.groupsession.v2.man.man240kn.Man240knAction.CMD_SETTEI_CLICK;
  String backClick           = jp.groupsession.v2.man.man240kn.Man240knAction.CMD_BACK_CLICK;
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="main.man240kn.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man240kn">
<input type="hidden" name="CMD" value="">

<bean:define id="pluginId" value="" />
<bean:define id="pluginName" value="" />
<bean:define id="checkError" value="" />
<bean:define id="checkWarn" value="" />
<bean:define id="checkInfo" value="" />
<bean:define id="checkTrace" value="" />


<logic:notEmpty name="man240knForm" property="man240LogConfList">
<logic:iterate id="man240LogConf" name="man240knForm" property="man240LogConfList" indexId="indx">

<% pluginId = "man240LogConf[" + String.valueOf(indx.intValue()) + "].lgcPlugin"; %>
<% pluginName = "man240LogConf[" + String.valueOf(indx.intValue()) + "].pluginName"; %>
<% checkError = "man240LogConf[" + String.valueOf(indx.intValue()) + "].lgcLevelError"; %>
<% checkWarn = "man240LogConf[" + String.valueOf(indx.intValue()) + "].lgcLevelWarn"; %>
<% checkInfo = "man240LogConf[" + String.valueOf(indx.intValue()) + "].lgcLevelInfo"; %>
<% checkTrace = "man240LogConf[" + String.valueOf(indx.intValue()) + "].lgcLevelTrace"; %>

<input type="hidden" name="<%= pluginId %>" value="<bean:write name='man240knForm' property='<%= pluginId %>' />" >
<input type="hidden" name="<%= pluginName %>" value="<bean:write name='man240knForm' property='<%= pluginName %>' />" >

<logic:notEmpty name="man240knForm" property="<%= checkError %>">
  <bean:define id="checkErrorValue" name="man240knForm" property="<%= checkError %>" type="java.lang.String" />
  <input type="hidden" name="<%= checkError %>" value="<%= checkErrorValue %>" >
</logic:notEmpty>
<logic:notEmpty name="man240knForm" property="<%= checkWarn %>">
  <bean:define id="checkWarnValue" name="man240knForm" property="<%= checkWarn %>" type="java.lang.String" />
  <input type="hidden" name="<%= checkWarn %>" value="<%= checkWarnValue %>" >
</logic:notEmpty>
<logic:notEmpty name="man240knForm" property="<%= checkInfo %>">
  <bean:define id="checkInfoValue" name="man240knForm" property="<%= checkInfo %>" type="java.lang.String" />
  <input type="hidden" name="<%= checkInfo %>" value="<%= checkInfoValue %>" >
</logic:notEmpty>
<logic:notEmpty name="man240knForm" property="<%= checkTrace %>">
  <bean:define id="checkTraceValue" name="man240knForm" property="<%= checkTrace %>" type="java.lang.String" />
  <input type="hidden" name="<%= checkTrace %>" value="<%= checkTraceValue %>" >
</logic:notEmpty>

</logic:iterate>
</logic:notEmpty>




<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td>


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man240kn.1" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('<%= setteiClick %>');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">

    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">



    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>


    <table width="100%" class="tl0" border="0" cellpadding="5">

    <logic:notEmpty name="man240Form" property="man240LogConfList">
    <logic:iterate name="man240Form" property="man240LogConfList" id="man240LogConf" indexId="idx" >

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><bean:write name="man240LogConf" property="pluginName" /></span></td>
    <td align="left" class="td_wt2" width="100%">

      <logic:equal name="man240LogConf" property="lgcLevelError" value="1">・<img alt="<gsmsg:write key="man.error" />" class="img_bottom" src="../main/images/icon_log_error.gif" border="0"><gsmsg:write key="man.error" /></logic:equal>
      <logic:equal name="man240LogConf" property="lgcLevelWarn" value="1">・<img alt="<gsmsg:write key="cmn.warning" />" class="img_bottom" src="../main/images/icon_log_warn.gif" border="0"><gsmsg:write key="cmn.warning" /></logic:equal>
      <logic:equal name="man240LogConf" property="lgcLevelInfo" value="1">・<img alt="<gsmsg:write key="main.man240.2" />" class="img_bottom" src="../main/images/icon_log_info.gif" border="0"><gsmsg:write key="main.man240.2" /></logic:equal>
      <logic:equal name="man240LogConf" property="lgcLevelTrace" value="1">・<img alt="<gsmsg:write key="main.man240.3" />" class="img_bottom" src="../main/images/icon_log_trace.gif" border="0"><gsmsg:write key="main.man240.3" /></logic:equal>
    </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('<%= setteiClick %>');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
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