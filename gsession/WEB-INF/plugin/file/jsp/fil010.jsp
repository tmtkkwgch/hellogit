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
<title>[Group Session] <gsmsg:write key="cmn.filekanri" />  <gsmsg:write key="fil.49" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmnPic.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil010.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>
<body class="body_03" onload="initPicture('iconImage', 30);">
<html:form action="/file/fil010">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<input type="hidden" name="CMD" value="fil010search">
<input type="hidden" name="cmnMode" value="">
<input type="hidden" name="backDsp" value="">
<input type="hidden" name="backDspLow" value="">
<input type="hidden" name="backDspCall" value="">
<input type="hidden" name="fil010SelectCabinet" value="">
<input type="hidden" name="fil010SelectDirSid" value="">
<html:hidden name="fil010Form" property="fil010binSid" />
<input type="hidden" name="fil070DirSid" value="">
<html:hidden property="fileSid" />
  <table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
    <td width="100%" align="center">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="fil.49" /> ]</td>
        <td width="100%" class="header_white_bg">
        <logic:equal name="fil010Form" property="fil010DspAdminConfBtn" value="1">
          <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('fil010atools');">
        </logic:equal>
          <input type="button" name="btn_kojn" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('fil010ptools');">
        </td>
        <td width="0%" class="header_white_bg"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
      </table>

      <div style="margin-top: 5px;"></div>

      <!-- ページコンテンツ start -->
      <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <logic:notEqual name="fil010Form" property="shortcutCallListFlg" value="1">
          <td width="22%" valign="top">
        </logic:notEqual>
        <logic:equal name="fil010Form" property="shortcutCallListFlg" value="1">
          <td width="0%" valign="top">
        </logic:equal>
        <!--ショートカット-->
        <logic:notEmpty name="fil010Form" property="shortcutList" scope="request">
          <table class="file-table-right">
          <tr class="row-right-heading">
            <th colspan="2" width="100%">
              <table width="100%">
              <tr>
                <td width="100%"><span class="text_tlw2"><gsmsg:write key="fil.2" /></span></td>
                <td width="0%"><input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil010scDelete');"></td>
             </tr>
              </table>
            </th>
          </tr>
          <bean:define id="tdColor" value="" />
          <% String[] tdColors = new String[] {"td_type_file1", "td_type_file2"}; %>
          <logic:iterate id="shortBean" name="fil010Form" property="shortcutList" scope="request" indexId="idx">
          <% tdColor = tdColors[(idx.intValue() % 2)]; %>
          <tr class="<%= tdColor %>">
            <td width="0%"  align="center">
              <bean:define id="day" name="shortBean" property="directorySid" type="java.lang.Integer" />
              <html:multibox property="fil010SelectDelLink" value="<%= Integer.toString(day.intValue()) %>"/>
            </td>
            <td width="100%"  align="left">
            <logic:equal name="shortBean" property="directoryKbn" value="0">
              <img src="../file/images/folder_short_cut.gif" alt="<gsmsg:write key="cmn.folder" />">
              <a href="#" onclick="MoveToFolderList(<bean:write name="shortBean" property="cabinetSid" />, <bean:write name="shortBean" property="directorySid" />);">
            </logic:equal>
            <logic:equal name="shortBean" property="directoryKbn" value="1">
              <img src="../file/images/file_short_cut.gif" alt="<gsmsg:write key="cmn.file" />">
              <a href="#" onclick="fileDl('fileDownload', '<bean:write name="shortBean" property="binSid" />');">
            </logic:equal>
            <bean:write name="shortBean" property="directoryFullPathName" />
              </a>
            </td>
          </tr>
          </logic:iterate>
          </table>
        </logic:notEmpty>

          <table class="tl0" width="100%" cellpadding="0" cellspacing="0">
          <tr>
            <td width="100%" nowrap>&nbsp;</td>
          </tr>
          </table>
          <table class="file-table-right">
          <!-- 新着情報 -->
          <logic:notEmpty name="fil010Form" property="callList" scope="request">
          <bean:define id="tdColor" value="" />
          <% String[] tdColors = new String[] {"td_type_file1", "td_type_file2"}; %>
          <tr class="row-right-heading">
          <th colspan="2">
            <table width="100%">
            <tr>
            <td width="100%"><span class="text_tlw2"><gsmsg:write key="fil.1" /></span></td>
            <td width="0%"><input type="button" class=btn_list_n3 value="<gsmsg:write key="cmn.list" />" onclick="MoveToCallList();"></td>
            </tr>
            </table>
          </th>
          </tr>
          <logic:iterate id="callBean" name="fil010Form" property="callList" scope="request" indexId="idx">
          <% tdColor = tdColors[(idx.intValue() % 2)]; %>
          <tr class="<%= tdColor %>">
            <td width="0%" align="left">
            <logic:notEqual name="callBean" property="fcbMark" value="0">
              <img name="iconImage" width="30" src="../file/fil010.do?CMD=tempview&fil010SelectCabinet=<bean:write name="callBean" property="cabinetSid" />&fil010binSid=<bean:write name="callBean" property="fcbMark" />" name="pctImage<bean:write name="callBean" property="fcbMark" />" >
            </logic:notEqual>
            <logic:equal name="callBean" property="directoryKbn" value="0">
            <logic:equal name="callBean" property="fcbMark" value="0">
              <img src="../file/images/cate_icon01_anime.gif" border="0" alt="" style="vertical-align:bottom;">
            </logic:equal>
            </td>
            <td width="100%" align="left">
              <a href="#" onClick="MoveToFolderDetail(<bean:write name="callBean" property="cabinetSid" />,<bean:write name="callBean" property="directorySid" />);">
              <bean:write name="callBean" property="directoryFullPathName" />
            </logic:equal>
            <logic:notEqual name="callBean" property="directoryKbn" value="0">
            <logic:equal name="callBean" property="fcbMark" value="0">
              <img src="../file/images/cate_icon02_anime.gif" border="0" alt="" style="vertical-align:bottom;">
            </logic:equal>
            </td>
            <td width="100%" align="left">
              <a href="#" onClick="MoveToFileDetail(<bean:write name="callBean" property="cabinetSid" />,<bean:write name="callBean" property="directorySid" />);">
              <bean:write name="callBean" property="directoryFullPathName" />
            </logic:notEqual>
              </a>
            <br>
            <span class="text_base_file_up"><gsmsg:write key="cmn.update" />：<bean:write name="callBean" property="directoryUpdateStr" /></span>
            </td>
          </tr>
          </logic:iterate>
          </logic:notEmpty>
          </table>
        </td>
        <logic:notEqual name="fil010Form" property="shortcutCallListFlg" value="1">
          <td width="1%" valign="top" nowrap>&nbsp;</td>
          <td width="77%" valign="top">
        </logic:notEqual>
        <logic:equal name="fil010Form" property="shortcutCallListFlg" value="1">
          <td width="100%" valign="top">
        </logic:equal>
          <html:messages id="msg" message="false" >
            <span class="text_error"><bean:write name="msg" ignore="true" /></span>
          </html:messages>
          <p class="file-cabinet-control">
            <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
            <html:text name="fil010Form" maxlength="50" property="filSearchWd" styleClass="text_base" style="width:157px;" />
            <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onClick="MoveToSearch();">
            <logic:equal name="fil010Form" property="fil010DspCabinetAddBtn" value="1">
              <input type="button" value="<gsmsg:write key="fil.64" />" class="btn_folder_n1" onClick="CreateCabinet();">
            </logic:equal>
          </p>

          <logic:notEmpty name="fil010Form" property="cabinetList" scope="request">
          <table id="file-list-table">
          <tr class="row-heading">
            <th width="0">&nbsp;</th>
            <th width="75%"><gsmsg:write key="fil.23" /></th>
            <th width="20%"><gsmsg:write key="fil.fil010.1" />/<gsmsg:write key="fil.fil010.2" /></th>
            <th width="5%"><gsmsg:write key="cmn.detail" /></th>
          </tr>
          <bean:define id="tdColor" value="" />
          <% String[] tdColors = new String[] {"row-gray", "row-sky"}; %>
          <logic:iterate id="cabBean" name="fil010Form" property="cabinetList" scope="request" indexId="idx">
          <% tdColor = tdColors[(idx.intValue() % 2)]; %>
          <tr class="<%= tdColor %>">
            <td>
            <logic:equal name="cabBean" property="fcbMark" value="0">
              <img src="../file/images/cabinet.gif" alt="">
            </logic:equal>
            <logic:notEqual name="cabBean" property="fcbMark" value="0">
            <img name="iconImage" width="30" src="../file/fil010.do?CMD=tempview&fil010SelectCabinet=<bean:write name="cabBean" property="fcbSid" />&fil010binSid=<bean:write name="cabBean" property="fcbMark" />" name="pictImage<bean:write name="cabBean" property="fcbMark" />" >
            </logic:notEqual>
            </td>
            <td>
              <a href="#" onclick="MoveToRootFolderList(<bean:write name="cabBean" property="fcbSid" />, <bean:write name="cabBean" property="rootDirSid" />);"><bean:write name="cabBean" property="dspfcbName" filter="false" />
            <logic:notEqual name="cabBean" property="accessIconKbn" value="1">
              <img src="../file/images/icon_stop.gif" alt="">
            </logic:notEqual>

            <logic:equal name="cabBean" property="callIconKbn" value="1">
              <img src="../file/images/icon_call.gif" alt="">
            </logic:equal>
            <logic:notEqual name="cabBean" property="callIconKbn" value="1">
              &nbsp;
            </logic:notEqual>
              </a>

            <logic:notEmpty name="cabBean" property="dspBikoString">
              <p><bean:write name="cabBean" property="dspBikoString" filter="false"/></p>
            </logic:notEmpty>
            <logic:empty name="cabBean" property="dspBikoString">
              &nbsp;
            </logic:empty>
            </td>
            <td>
            <logic:notEqual name="cabBean" property="diskUsedWarning" value="1">
              <span class="text_title" ><bean:write name="cabBean" property="diskUsedString" filter="false" /></span>
            </logic:notEqual>
            <logic:equal name="cabBean" property="diskUsedWarning" value="1">
              <img style="float: left;margin:5px 5px 0 0;" src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />">
              <span class="text_warning"><bean:write name="cabBean" property="diskUsedString" filter="false" /></span>
            </logic:equal>
            </td>
            <td><input type="button" value="<gsmsg:write key="fil.18" />" class="btn_base1s" onclick="CabinetDetail(<bean:write name="cabBean" property="fcbSid" />);"></td>
          </tr>
          </logic:iterate>
          </table>

          <!-- 補足 -->
          <p>
            <img src="../file/images/icon_stop.gif" alt=""><span class="text_base"><gsmsg:write key="fil.fil010.3" /></span>
            <img src="../file/images/icon_call.gif" alt=""><span class="text_base"><gsmsg:write key="fil.fil010.4" /></span>
          </p>
          </logic:notEmpty>
          <logic:empty name="fil010Form" property="cabinetList" scope="request">
            <gsmsg:write key="fil.fil010.5" />
          </logic:empty>
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