<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/filMain.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/file/filMain">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="backDsp" value="">
<input type="hidden" name="backDspLow" value="">
<input type="hidden" name="backDspCall" value="">
<input type="hidden" name="backMainFlg" value="">
<input type="hidden" name="fileSid" value="">
<input type="hidden" name="fil010SelectCabinet" value="">
<input type="hidden" name="fil010SelectDirSid" value="">
<input type="hidden" name="fil050DirSid" value="">
<input type="hidden" name="fil070DirSid" value="">
<logic:notEmpty name="filMainForm" property="shortcutList">
<!-- ショートカット -->
  <table width="100%" class="tl3" cellspacing="0" cellpadding="0">
  <tr>
  <td class="header_7D91BD_right" style="border-top: solid 1px #333333;border-left: solid 1px #333333;">
  <img src="../file/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="fil.2" />"> <a href="<bean:write name="filMainForm" property="filTopUrl" />"><gsmsg:write key="cmn.filekanri" />-<gsmsg:write key="fil.2" /></a></td>
  </td>
  </tr>

  <bean:define id="tdColor" value="" />
  <% String[] tdColors = new String[] {"td_type1_10pt", "td_type25_2_10pt"}; %>

  <logic:iterate id="shortBean" name="filMainForm" property="shortcutList" scope="request" indexId="idx">
  <% tdColor = tdColors[(idx.intValue() % 2)]; %>

  <tr>
  <td width="100%" align="left" class="<%= tdColor %>">

  <logic:equal name="shortBean" property="directoryKbn" value="0">
    <img src="../file/images/folder_short_cut.gif" alt="<gsmsg:write key="cmn.folder" />">
    <a href="javascript:void(0);" onclick="return MoveToFolderList(<bean:write name="shortBean" property="cabinetSid" />, <bean:write name="shortBean" property="directorySid" />);">
  </logic:equal>

  <logic:equal name="shortBean" property="directoryKbn" value="1">
    <img src="../file/images/file_short_cut.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:void(0);" onclick="return fileDow('fileKanriFileDownload', '<bean:write name="shortBean" property="binSid" />');">
  </logic:equal>

  <bean:write name="shortBean" property="directoryFullPathName" /></a>
  </td>
  </tr>
  </logic:iterate>
  </table>

</logic:notEmpty>

<logic:notEmpty name="filMainForm" property="shortcutList">
<logic:notEmpty name="filMainForm" property="callList">
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
</logic:notEmpty>
</logic:notEmpty>

<logic:notEmpty name="filMainForm" property="callList">
<!-- 更新通知 -->
  <table width="100%" class="tl3" cellspacing="0" cellpadding="0">
  <tr>
  <td width="100%" colspan="2" class="header_7D91BD_right" style="border-top: solid 1px #333333;border-left: solid 1px #333333;">
    <table width="100%" class="tl0" cellspacing="0" cellpadding="0" >
    <tr>
    <td width="100%" style="border-top: solid 0px #333333;border-left: solid 0px #333333;">
    <img src="../file/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="fil.1" />"> <a href="<bean:write name="filMainForm" property="filTopUrl" />"><gsmsg:write key="cmn.filekanri" />-<gsmsg:write key="fil.1" /></a></td>
    </td>
    <td width="0%" align="right" class="">
    <input type="button" onClick="MoveToPconf();" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <bean:define id="tdColor2" value="" />
  <% String[] tdColorsCall = new String[] {"td_type1_10pt", "td_type25_2_10pt"}; %>

  <logic:iterate id="callBean" name="filMainForm" property="callList" scope="request" indexId="idx">
  <% tdColor2 = tdColorsCall[(idx.intValue() % 2)]; %>

  <tr class="<%= tdColor2 %>">
  <td width="0%" align="left" class="td_padding">

  <logic:notEqual name="callBean" property="fcbMark" value="0">
    <img name="iconImage" width="30" src="../file/fil010.do?CMD=tempview&fil010SelectCabinet=<bean:write name="callBean" property="cabinetSid" />&fil010binSid=<bean:write name="callBean" property="fcbMark" />" name="pctImage<bean:write name="callBean" property="fcbMark" />" >
  </logic:notEqual>


  <logic:equal name="callBean" property="directoryKbn" value="0">
    <logic:equal name="callBean" property="fcbMark" value="0">
      <img src="../file/images/cate_icon01_anime.gif" border="0" alt="" style="vertical-align:bottom;">
    </logic:equal>

    </td>

    <td width="100%" align="left">
    <a href="javascript:void(0);" onClick="return MoveToFolderDetail(<bean:write name="callBean" property="cabinetSid" />,<bean:write name="callBean" property="directorySid" />);">
    <bean:write name="callBean" property="directoryFullPathName" />
  </logic:equal>

  <logic:notEqual name="callBean" property="directoryKbn" value="0">
    <logic:equal name="callBean" property="fcbMark" value="0">
      <img src="../file/images/cate_icon02_anime.gif" border="0" alt="" style="vertical-align:bottom;">
    </logic:equal>
    </td>

    <td width="100%" align="left">
    <a href="javascript:void(0);" onClick="return MoveToFileDetail(<bean:write name="callBean" property="cabinetSid" />,<bean:write name="callBean" property="directorySid" />);">
    <bean:write name="callBean" property="directoryFullPathName" />
  </logic:notEqual>

  </a>
  <br>
  <span class="text_base"><gsmsg:write key="cmn.update.day.hour" />：<bean:write name="callBean" property="directoryUpdateStr" /></span>
  </td>
  </tr>
  </logic:iterate>

  <tr class="<%= tdColor2 %>">
    <td width="100%" align="right" class="main_link_td_padding" colspan="2">
      <span class="main_link"><a href="javascript:void(0);" onClick="MoveToCallList();"><gsmsg:write key="cmn.list" /></a></span>
    </td>
  </tr>

  </table>

</logic:notEmpty>

</html:form>
</body>
</html:html>