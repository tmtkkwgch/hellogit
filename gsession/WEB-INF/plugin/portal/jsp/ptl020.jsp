<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="ptl.13" /></title>
</head>

<body class="body_03">

<html:form action="/portal/ptl020">
<input type="hidden" name="CMD" value="init">
<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backList');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush2('ptlManager');"><span class="text_link"><gsmsg:write key="ptl.2" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ptl.ptl020.1" /></li></div></dd>
      </dl>
    </td>
    </tr>

    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush2('pletManager');"><span class="text_link"><gsmsg:write key="ptl.9" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ptl.ptl020.2" /></li></div></dd>
      </dl>
    </td>
    </tr>

    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush2('pletInitValue');"><span class="text_link"><gsmsg:write key="cmn.default.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ptl.ptl020.3" /></li></div></dd>
      </dl>
    </td>
    </tr>

    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush2('powManager');"><span class="text_link"><gsmsg:write key="cmn.setting.permissions" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ptl.ptl020.4" /></li></div></dd>
      </dl>
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