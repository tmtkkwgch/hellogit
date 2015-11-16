<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="zsk.21" /></title>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk130">
<input type="hidden" name="CMD" value="">
<html:hidden name="zsk130Form" property="backScreen" />
<html:hidden name="zsk130Form" property="selectZifSid" />
<html:hidden name="zsk130Form" property="uioStatus" />
<html:hidden name="zsk130Form" property="uioStatusBiko" />
<html:hidden name="zsk130Form" property="sortKey" />
<html:hidden name="zsk130Form" property="orderKey" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.28" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk130kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk130back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.sort" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <span class="text_r1"><gsmsg:write key="zsk.zsk130.01" /></span><br>
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" /><gsmsg:write key="wml.215" /></span>

      <!-- キー1 -->
      <html:select property="zsk130SortKey1">
        <html:optionsCollection name="zsk130Form" property="zsk130SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="zsk130Form" property="zsk130SortOrder1" styleId="zsk130SortOrder10" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="zsk130SortOrder10"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="zsk130Form" property="zsk130SortOrder1" styleId="zsk130SortOrder11" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="zsk130SortOrder11"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" /><gsmsg:write key="wml.215" /></span>
      <!-- キー2 -->
      <html:select property="zsk130SortKey2">
        <html:optionsCollection name="zsk130Form" property="zsk130SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="zsk130Form" property="zsk130SortOrder2" styleId="zsk130SortOrder20" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="zsk130SortOrder20"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="zsk130Form" property="zsk130SortOrder2" styleId="zsk130SortOrder21" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="zsk130SortOrder21"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk130kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk130back');">
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