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
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>



<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>




<title>[Group Session] <gsmsg:write key="cmn.category.entry.kn" /></title>
</head>

<!-- body -->
<body class="body_03">
<html:form action="/portal/ptl120kn">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortletSid" />
<html:hidden property="ptlCmdMode" />

<html:hidden property="ptl090category" />
<html:hidden property="ptl090svCategory" />

<html:hidden property="ptl110sortPltCategory" />

<html:hidden property="ptl120init" />
<html:hidden property="ptlPtlCategorytSid" />
<html:hidden property="ptlPlcBack" />

<html:hidden property="ptl120name" />
<html:hidden property="ptl120biko" />

<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
      <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.ptl120kn.1" /> ]</td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_ok1" onClick="buttonPush('ptl120knOk');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ptl120knBack');">
    </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>
  <tr>
  <td>


  </td>
  </tr>

  <tr>
  <td>


    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category.name" /></span></td>
    <td align="left" class="td_type1" width="80%"><bean:write name="ptl120Form" property="ptl120name" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_type1" width="80%"><bean:write name="ptl120Form" property="ptl120biko" />
    </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
    <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_ok1" onClick="buttonPush('ptl120knOk');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ptl120knBack');">
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

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>