<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<title><gsmsg:write key="main.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man001.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<!-- BODY -->
<!-- <gsmsg:write key="cmn.title" /> -->
<table align="center" cellpadding="0" cellspacing="5" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../main/images/header_main_01.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    <td width="0%" class="header_white_bg_text"></td>
    <td width="100%" class="header_white_bg">
      <bean:define id="kusr" name="<%= key %>" scope="session" />
      <logic:equal name="kusr" property="adminFlg" value="true">
        <input type="button" value="<gsmsg:write key="cmn.admin.setting" />" class="btn_setting_admin_n1" onClick="buttonPush('ktools');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.preferences2" />" class="btn_setting_n1" onClick="buttonPush('pset')">
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  </td>
  </tr>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
