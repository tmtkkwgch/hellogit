<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.select.label" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../address/js/adr260.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/readOnly.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/freeze.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="freezeScreenParent('', false);parentReadOnly();init(<bean:write name="adr260Form" property="adr260initFlg" />);">
<html:form action="/address/adr260">

<input type="hidden" name="CMD" value="">
<html:hidden property="adr260initFlg" />

<html:hidden property="adr260parentLabelName" />

<logic:notEmpty name="adr260Form" property="adr260selectLabel">
<logic:iterate id="label" name="adr260Form" property="adr260selectLabel">
  <input type="hidden" name="adr260selectLabel" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<table cellpadding="4" cellspacing="0" border="0" width="100%">
<tr>
<td>
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="setParentLabel();">
      <input type="button" value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onclick="labelSelectClose();">
    </td>
    </tr>
  </table>
</td>
</tr>

<logic:messagesPresent message="false">
<tr>
<td align="left"><html:errors/></td>
</tr>
</logic:messagesPresent>

<tr>
  <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span></td>
    <td align="left" class="td_wt" width="80%">
      <html:select property="selectCategory" onchange="buttonPush('init');" >
       <html:optionsCollection name="adr260Form" property="categoryList" value="value" label="label"/>
      </html:select>
   </td>
  </tr>
  </table>
</tr>


<logic:notEmpty name="adr260Form" property="labelList">
<% String[] labelClass = {"td_label1", "td_label2"}; %>
<tr>
<td>
<br>
  <table cellpadding="0" cellspacing="0" class="tl0" width="100%">
  <logic:iterate id="labelData" name="adr260Form" property="labelList" indexId="idx">
  <tr class="<%= labelClass[idx.intValue() % 2] %>">
  <td width="0%">
  <html:multibox name="adr260Form" property="adr260selectLabel">
    <bean:write name="labelData" property="albSid" />
  </html:multibox>
  </td>
  <td width="100%"><bean:write name="labelData" property="albName" /></td>
  </tr>
  </logic:iterate>

  </table>

</td>
</tr>

</logic:notEmpty>
</table>

</html:form>

</body>
</html:html>