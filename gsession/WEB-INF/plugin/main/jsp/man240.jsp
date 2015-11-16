<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
  String okClick         = jp.groupsession.v2.man.man240.Man240Action.CMD_OK;
  String backClick       = jp.groupsession.v2.man.man240.Man240Action.CMD_BACK;
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="main.man002.51" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../main/js/man240.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man240">
<input type="hidden" name="CMD" value="">

<bean:define id="pluginId" value="" />
<bean:define id="pluginName" value="" />

<logic:notEmpty name="man240Form" property="man240LogConfList">
<logic:iterate id="man240LogConf" name="man240Form" property="man240LogConfList" indexId="indx">

  <% pluginId = "man240LogConf[" + String.valueOf(indx.intValue()) + "].lgcPlugin"; %>
  <% pluginName = "man240LogConf[" + String.valueOf(indx.intValue()) + "].pluginName"; %>
  <input type="hidden" name="<%= pluginId %>" value="<bean:write name='man240Form' property='<%= pluginId %>' />" >
  <input type="hidden" name="<%= pluginName %>" value="<bean:write name='man240Form' property='<%= pluginName %>' />" >
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
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.51" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">

    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td><span class="text_base"><gsmsg:write key="main.man240.1" /></span></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <logic:notEmpty name="man240Form" property="man240LogConfList">
    <logic:iterate name="man240Form" property="man240LogConfList" id="man240LogConf" indexId="idx" >

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><bean:write name="man240LogConf" property="pluginName" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt2" width="100%">


      <html:checkbox name="man240LogConf" property="lgcLevelError" indexed="true" value="1" />
      <a href="javascript:void(0);" onclick="logLevelCheck('man240LogConf', 'lgcLevelError', '<%= String.valueOf(idx.intValue()) %>');" ><span class="text_normal"><img alt="<gsmsg:write key="man.error" />" class="img_bottom" src="../main/images/icon_log_error.gif" border="0"><gsmsg:write key="man.error" /></span></a>
      &nbsp;&nbsp;
      <html:checkbox name="man240LogConf" property="lgcLevelWarn" indexed="true" value="1"/>
      <a href="javascript:void(0);" onclick="logLevelCheck('man240LogConf', 'lgcLevelWarn', '<%= String.valueOf(idx.intValue()) %>');"><span class="text_normal"><img alt="<gsmsg:write key="cmn.warning" />" class="img_bottom" src="../main/images/icon_log_warn.gif" border="0"><gsmsg:write key="cmn.warning" /></span></a>
      &nbsp;&nbsp;
      <html:checkbox name="man240LogConf" property="lgcLevelInfo" indexed="true" value="1"/>
      <a href="javascript:void(0);" onclick="logLevelCheck('man240LogConf', 'lgcLevelInfo', '<%= String.valueOf(idx.intValue()) %>');"><span class="text_normal"><img alt="<gsmsg:write key="main.man240.2" />" class="img_bottom" src="../main/images/icon_log_info.gif" border="0"><gsmsg:write key="main.man240.2" /></span></a>
      &nbsp;&nbsp;
      <html:checkbox name="man240LogConf" property="lgcLevelTrace" indexed="true" value="1"/>
      <a href="javascript:void(0);" onclick="logLevelCheck('man240LogConf', 'lgcLevelTrace', '<%= String.valueOf(idx.intValue()) %>');"><span class="text_normal"><img alt="<gsmsg:write key="main.man240.3" />" class="img_bottom" src="../main/images/icon_log_trace.gif" border="0"><gsmsg:write key="main.man240.3" /></span></a>

    </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
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