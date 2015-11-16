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
<script language="JavaScript" src="../bookmark/js/bmkptl020.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<!-- BODY -->
<body class="body_03" onload="closeWindow();">

<html:form action="/bookmark/bmkptl020">

<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortalSid" />

<html:hidden property="bmkptl020GrpSid" />
<html:hidden property="bmkptl020selectFlg" />

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="90%">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../bookmark/images/header_link01.gif" border="0" alt=""></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bmk.51" /> ]</td>
    <td width="0%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ptl.1" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="10px" height="20px" border="0"><br>
  <span class="text_base"><gsmsg:write key="zsk.ptl020.2" /></span>
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="15%" class="td_type25_2" align="center" nowrap><span class="text_todo_head"><gsmsg:write key="ptl.21" /></span></td>
    <td class="td_type1">
      <logic:notEmpty name="bmkptl020Form" property="portletTypeCombo">
      <html:select property="ptl080PluginPortlet" onchange="buttonPush('bmkChangeCombo');">
        <html:optionsCollection property="portletTypeCombo" value="value" label="label" />
      </html:select>
      </logic:notEmpty>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>
  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th align="center" class="td_type24" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group" /></span></th>
    </tr>

    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>

    <logic:notEmpty name="bmkptl020Form" property="tree">
    <logic:iterate id="groupModel" name="bmkptl020Form" property="tree" indexId="idx">

    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr>
    <td align="left" class="<%= tdColor %>" width="100%" nowrap>
      <img src="../bookmark/images/menu_icon_single.gif" class="img_bottom" alt="">
      <a href="javascript:void(0);" onClick="return selectBmk('<bean:write name="groupModel" property="groupSid" />');"><span class="text_link"><bean:write name="groupModel" property="groupName" /></span></a>
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>

    </table>

  </td>
  </tr>

  </table>

</td>
</tr>
</table>

</body>
</html:form>

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:html>