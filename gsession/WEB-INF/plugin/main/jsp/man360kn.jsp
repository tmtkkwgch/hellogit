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
<script language="JavaScript" src="../main/js/man360kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="main.man350kn.1" /></title>
</head>

<!-- body -->
<body class="body_03" onload="initChgArea();">
<html:form action="/main/man360kn">
<input type="hidden" name="CMD" value="init">
<html:hidden property="man360init" />
<html:hidden property="man360area1" />
<html:hidden property="man360area2" />
<html:hidden property="man360area3" />
<html:hidden property="man360area4" />
<html:hidden property="man360area5" />
<html:hidden property="man360layout" />

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
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="main.man350kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush2('man360knOk');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('man360knBack');">
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
      <logic:equal name="man360Form" property="man360layout" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.MANSCREEN_LAYOUT_DEFAULT) %>" ><gsmsg:write key="cmn.default" /></logic:equal>
      <logic:equal name="man360Form" property="man360layout" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.MANSCREEN_LAYOUT_CUSTOM) %>" ><gsmsg:write key="cmn.customize" /></logic:equal>
    </span>
  </td>
  </tr>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <div id="defaultAreaList">
  <table width="100%" cellspacing="0" cellpadding="0" class="portal_space">
    <tr>
    <!-- 左-->
    <td width="75%" valign="top">
      <div class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="430px" border="0">
      </div>
    </td>

    <td>
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 右 -->
    <td width="25%" height="450" valign="top">
      <div class="portal_area">
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
    <div class="portal_area">
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
      <div class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="280px" border="0">
      </div>
    </td>

    <td id="left_space">
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 中央 -->
    <td width="33%" valign="top" id="mainScreenListCenter">
      <div class="portal_area">
        <img src="../common/images/spacer.gif" width="1px" height="280px" border="0">
      </div>
    </td>

    <td id="right_space">
      <img src="../common/images/spacer.gif" width="10px" height="1px" border="0">
    </td>

    <!-- 右 -->
    <td width="33%" valign="top" id="mainScreenListRight">
      <div class="portal_area">
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
    <div class="portal_area">
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