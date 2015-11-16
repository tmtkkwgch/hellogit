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
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="ptl.13" /></title>
</head>

<body class="body_03">

<html:form action="/portal/ptl130">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptl130init" />

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="cmn.setting.permissions" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('ptl130kakunin');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('confMenu');">
    </td>
    </tr>
    </table>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="13%" nowrap><span class="text_bb1"><gsmsg:write key="ptl.15" /></span></td>
    <td align="left" class="td_type20" width="87%">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="ptl130Form" styleId="ptl130editKbn_01" property="ptl130editKbn" value="0" /><label for="ptl130editKbn_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="ptl130Form" styleId="ptl130editKbn_02" property="ptl130editKbn" value="1" /><label for="ptl130editKbn_02"><span class="text_base6"><gsmsg:write key="cmn.nolimit" /></span></label>
      <div class="text_base7"><gsmsg:write key="ptl.ptl130.1" /></div>
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('ptl130kakunin');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('confMenu');">
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