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
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../zaiseki/js/zsk140.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="zsk.74" /></title>
</head>

<body class="body_03" onload="sortEnableDisable();">
<html:form action="/zaiseki/zsk140">
<input type="hidden" name="CMD" value="">
<html:hidden name="zsk140Form" property="backScreen" />
<html:hidden name="zsk140Form" property="selectZifSid" />
<html:hidden name="zsk140Form" property="uioStatus" />
<html:hidden name="zsk140Form" property="uioStatusBiko" />
<html:hidden name="zsk140Form" property="sortKey" />
<html:hidden name="zsk140Form" property="orderKey" />

<html:hidden name="zsk140Form" property="zsk140initKbn" />

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.74" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk140kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk140back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.sort" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td  valign="middle" align="left" class="td_wt" width="80%">
      <html:radio name="zsk140Form" styleId="zsk140SortKbn_01" property="zsk140SortKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.ADM_SORTKBN_ADM) %>" onclick="sortEnableDisable();" /><label for="zsk140SortKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="zsk140Form" styleId="zsk140SortKbn_02" property="zsk140SortKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.ADM_SORTKBN_PRI) %>" onclick="sortEnableDisable();" /><label for="zsk140SortKbn_02"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      <br>
      <span id="lmtText" class="text_r1">※<gsmsg:write key="cmn.view.user.defaultset" /><br></span>
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" />：</span>

      <!-- キー1 -->
      <html:select property="zsk140SortKey1">
        <html:optionsCollection name="zsk140Form" property="zsk140SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="zsk140Form" property="zsk140SortOrder1" styleId="zsk140SortOrder10" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="zsk140SortOrder10"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="zsk140Form" property="zsk140SortOrder1" styleId="zsk140SortOrder11" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="zsk140SortOrder11"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" /><gsmsg:write key="wml.215" /></span>
      <!-- キー2 -->
      <html:select property="zsk140SortKey2">
        <html:optionsCollection name="zsk140Form" property="zsk140SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="zsk140Form" property="zsk140SortOrder2" styleId="zsk140SortOrder20" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="zsk140SortOrder20"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="zsk140Form" property="zsk140SortOrder2" styleId="zsk140SortOrder21" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="zsk140SortOrder21"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('zsk140kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('zsk140back');">
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