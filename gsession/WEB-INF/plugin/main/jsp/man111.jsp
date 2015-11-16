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
<title>[Group Session] <gsmsg:write key="cmn.entry.position" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../main/js/man111.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/readOnly.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/freeze.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="freezeScreenParent('', false);parentReadOnly();parentReload(<bean:write name="man111Form" property="closeFlg" />);">
<html:form action="/main/man111">

<input type="hidden" name="CMD" value="ok">
<html:hidden property="man111InitFlg" />

<table cellpadding="4" cellspacing="0" border="0" width="100%">
<tr>
<td>

  <logic:messagesPresent message="false">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
  <tr>
  <td align="left"><html:errors/></td>
  </tr>
  </table>
  </logic:messagesPresent>

  <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
  <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="user.src.50" /></span></td>
  <td align="left" class="td_wt" width="80%"><html:text style="width:275px;" maxlength="15" property="man110posCode" styleClass="text_base" /></td>
  </tr>
  <tr>
  <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.job.title" /></span></td>
  <td align="left" class="td_wt" width="80%"><html:text style="width:275px;" maxlength="30" property="man110posName" styleClass="text_base" /></td>
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
      <input type="submit" value="<gsmsg:write key="cmn.add" />" class="btn_base1">
      <input type="button" value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onclick="posclose(false);">
    </td>
    </tr>
  </table>

</td>
</tr>
</table>

</html:form>

</body>
</html:html>