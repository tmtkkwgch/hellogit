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
<title>[GroupSession] <gsmsg:write key="main.holiday.setting" /> [<gsmsg:write key="main.man028kn.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man028kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="man028updateFlg" />



<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">

<tr>
<td width="100%" align="center">
  <table width="70%" class="tl0">

  <tr>
  <td align="left">

<!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.holiday.setting" /> <gsmsg:write key="main.man029kn.2" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('doImport2');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_holImp');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td><span class="text_r1"><gsmsg:write key="cmn.capture.file.sure" /></span></td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.capture.file.name" /></span></td>
    <td class="td_type1" width="80%">
    <a href="../main/man028kn.do?CMD=downLoad">
    <span class="text_link_min"><bean:write name="man028knForm" property="man028knFileName" /></span>
    </a>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.capture.item.count" /></span></td>
    <td class="td_type1"><bean:write name="man028knForm" property="impDataCnt" /><gsmsg:write key="cmn.number" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span></td>
    <td class="td_type1">
    <logic:equal name="man028knForm" property="man028updateFlg" value="1">
    <gsmsg:write key="main.holiday.samedate.overrite" />
    </logic:equal>
    </td>
    </tr>
    </table>
  </td>
  </tr>


  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('doImport2');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_holImp');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>
  </td>
  </tr>

</table>
</td>
</tr>

</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>