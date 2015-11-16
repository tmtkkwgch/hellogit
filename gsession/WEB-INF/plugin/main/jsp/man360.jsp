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
<script language="JavaScript" src="../main/js/man360.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

<title>[Group Session] <gsmsg:write key="main.man350.1" /></title>
</head>

<!-- body -->
<body class="body_03" onload="initChgArea();">

<html:form action="/main/man360">
<input type="hidden" name="CMD" value="init">

<html:hidden property="man360init" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">

  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="main.man350.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush2('man360edit');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('man360back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <logic:messagesPresent message="false">
  <table width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </table>
  </logic:messagesPresent>

  <table width="100%" class="tl0" border="0" cellpadding="5">
  <tr>
  <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="ptl.5" /></span></td>
  <td align="left" class="td_wt" width="85%">
    <span class="text_base">
      <html:radio styleId="layout_0" name="man360Form" property="man360layout" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.MANSCREEN_LAYOUT_DEFAULT) %>" onclick="chgDefaultArea();" /><label for="layout_0"><gsmsg:write key="cmn.default" /></label>&nbsp;
      <html:radio styleId="layout_1" name="man360Form" property="man360layout" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.MANSCREEN_LAYOUT_CUSTOM) %>" onclick="chgDefaultArea();" /><label for="layout_1"><gsmsg:write key="cmn.customize" /></label>
    </span>
  </td>
  </tr>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <table width="100%" cellspacing="0" cellpadding="0" id="checkarea">
  <tr>
  <td>
  <html:checkbox styleId="topId" name="man360Form" property="man360area3" value="1" onclick="chgArea('topId', 'Top');" /><label for="topId"><gsmsg:write key="ptl.ptl060.2" /></label>
  <html:checkbox styleId="leftId" name="man360Form" property="man360area1" value="1" onclick="chgArea('leftId', 'Left');" /><label for="leftId"><gsmsg:write key="cmn.left" /></label>
  <html:checkbox styleId="centerId" name="man360Form" property="man360area5" value="1" onclick="chgArea('centerId', 'Center');" /><label for="centerId"><gsmsg:write key="ptl.ptl060.3" /></label>
  <html:checkbox styleId="rightId" name="man360Form" property="man360area2" value="1" onclick="chgArea('rightId', 'Right');" /><label for="rightId"><gsmsg:write key="cmn.right" /></label>
  <html:checkbox styleId="bottomId" name="man360Form" property="man360area4" value="1" onclick="chgArea('bottomId', 'Bottom');" /><label for="bottomId"><gsmsg:write key="ptl.ptl060.4" /></label>
  </td>
  </tr>
  </table>

  <div id="defaultAreaList">
  <table width="100%" cellspacing="0" cellpadding="0" class="portal_space">
    <tr>
    <!-- 左-->
    <td width="75%" valign="top">
      <div width="100%" class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="430px" border="0">
      </div>
    </td>

    <td>
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 右 -->
    <td width="25%" valign="top">
      <div width="100%" class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="430px" border="0">
      </div>
    </td>
    </tr>
    </table>
  </div>


  <div id="areaList">
  <table width="100%" cellspacing="0" cellpadding="0" class="portal_space">

  <!-- 上 -->
  <tr>
  <td width="100%" height="100px" id="mainScreenListTop">
    <div width="100%" class="portal_area">
    <img src="../common/images/spacer.gif" width="1px" height="90px" border="0">
    </div>
  </td>
  </tr>
  <tr>
  <td height="5" align="center" id="portal_space_Top">
    <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%">
    <tr>
    <!-- 左-->
    <td width="33%" valign="top" id="mainScreenListLeft">
      <div width="100%"class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="280px" border="0">
      </div>
    </td>

    <td id="left_space">
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 中央 -->
    <td width="33%" valign="top" id="mainScreenListCenter">
      <div width="100%" class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="280px" border="0">
      </div>
    </td>

    <td id="right_space">
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 右 -->
    <td width="33%" valign="top" id="mainScreenListRight">
      <div width="100%" class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="280px" border="0">
      </div>
    </td>
    </tr>
    </table>

  </td>
  </tr>

  <!-- 下 -->
  <tr>
  <td height="5" align="center" id="portal_space_Bottom">
    <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">
  </td>
  </tr>

  <tr>
  <td width="100%" height="100" align="center" valign="top" id="mainScreenListBottom">
    <div width="100%" class="portal_area">
    <img src="../common/images/spacer.gif" width="1px" height="90px" border="0">
    </div>
  </td>
  </tr>

  </table>
  </div>

</td>
</tr>
</table>

</html:form>

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>

</html:html>