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
<title>[Group Session] <gsmsg:write key="cmn.filekanri" />  <gsmsg:write key="cmn.filelist" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<% java.util.List filTipList = new java.util.ArrayList(); %>

<body class="body_03">
<html:form action="/file/fil040">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="CMD" value="fil040search">
<input type="hidden" name="moveToDir" value="">
<input type="hidden" name="fil050DirSid" value="">
<input type="hidden" name="fil050ParentDirSid" value="">
<input type="hidden" name="fil070DirSid" value="">
<input type="hidden" name="fil070ParentDirSid" value="">
<input type="hidden" name="fil090DirSid" value="">
<input type="hidden" name="fil040SelectUnlock" value="">
<input type="hidden" name="fil040SelectUnlockVer" value="">

<input type="hidden" name="fileSid" value="">


<html:hidden property="selectDir" />
<html:hidden property="sepKey" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="backDsp" />
<html:hidden property="backDspLow" />

<html:hidden property="fil040SortKey" />
<html:hidden property="fil040OrderKey" />
<html:hidden property="fil090SelectPluralKbn" />

<logic:notEmpty name="fil040Form" property="fil010SelectDelLink">
<logic:iterate id="delSid" name="fil040Form" property="fil010SelectDelLink">
  <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="delSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil040Form" property="treeFormLv0">
<logic:iterate id="lv0" name="fil040Form" property="treeFormLv0">
  <input type="hidden" name="treeFormLv0" value="<bean:write name="lv0" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv1">
<logic:iterate id="lv1" name="fil040Form" property="treeFormLv1">
  <input type="hidden" name="treeFormLv1" value="<bean:write name="lv1" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv2">
<logic:iterate id="lv2" name="fil040Form" property="treeFormLv2">
  <input type="hidden" name="treeFormLv2" value="<bean:write name="lv2" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv3">
<logic:iterate id="lv3" name="fil040Form" property="treeFormLv3">
  <input type="hidden" name="treeFormLv3" value="<bean:write name="lv3" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv4">
<logic:iterate id="lv4" name="fil040Form" property="treeFormLv4">
  <input type="hidden" name="treeFormLv4" value="<bean:write name="lv4" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv5">
<logic:iterate id="lv5" name="fil040Form" property="treeFormLv5">
  <input type="hidden" name="treeFormLv5" value="<bean:write name="lv5" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv6">
<logic:iterate id="lv6" name="fil040Form" property="treeFormLv6">
  <input type="hidden" name="treeFormLv6" value="<bean:write name="lv6" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv7">
<logic:iterate id="lv7" name="fil040Form" property="treeFormLv7">
  <input type="hidden" name="treeFormLv7" value="<bean:write name="lv7" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv8">
<logic:iterate id="lv8" name="fil040Form" property="treeFormLv8">
  <input type="hidden" name="treeFormLv8" value="<bean:write name="lv8" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv9">
<logic:iterate id="lv9" name="fil040Form" property="treeFormLv9">
  <input type="hidden" name="treeFormLv9" value="<bean:write name="lv9" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil040Form" property="treeFormLv10">
<logic:iterate id="lv10" name="fil040Form" property="treeFormLv10">
  <input type="hidden" name="treeFormLv10" value="<bean:write name="lv10" />">
</logic:iterate>
</logic:notEmpty>

<!-- HEADER -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%">
    <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="fil.53" />： <bean:write name="fil040Form" property="fil040CabinetName"/> ]</td>
  <td width="100%" class="header_white_bg">

    <logic:equal name="fil040Form" property="fil010DspAdminConfBtn" value="1">
    <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="MoveToAconf();">
    </logic:equal>

    <input type="button" name="btn_kojn" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="MoveToPconf();">
  </td>

  <td width="0%" class="header_white_bg">
    <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td align="right">

  <logic:equal name="fil040Form" property="fil040DspAddBtn" value="1">
    <logic:equal name="fil040Form" property="admLockKbn" value="1">
    <input type="button" class="btn_lock_n1" value="<gsmsg:write key="fil.121" />" onclick="FileRockOn();">
    <input type="button" class="btn_unlock_n1" value="<gsmsg:write key="fil.122" />" onclick="FileRockOff();">
    </logic:equal>

    <logic:equal name="fil040Form" property="fil040DspFolderAddBtn" value="1">
    <input type="button" class="btn_folder_n1" value="<gsmsg:write key="cmn.create.folder" />" onClick="CreateFolder();">
    </logic:equal>

    <input type="button" class="btn_file_n1" value="<gsmsg:write key="fil.16" />" onClick="CreateFile();">
    <input type="button" class="btn_move_n1" value="<gsmsg:write key="cmn.move" />" onclick="MovePlural();">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="DeleteDirectory();">
  </logic:equal>
  <logic:notEqual name="fil040Form" property="fil040DspAddBtn" value="1">
    <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
      <logic:equal name="fil040Form" property="admLockKbn" value="1">
        <input type="button" class="btn_lock_n1" value="<gsmsg:write key="fil.121" />" onclick="FileRockOn();">
        <input type="button" class="btn_unlock_n1" value="<gsmsg:write key="fil.122" />" onclick="FileRockOff();">
      </logic:equal>
    <input type="button" class="btn_move_n1" value="<gsmsg:write key="cmn.move" />" onclick="MovePlural();">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="DeleteDirectory();">
  </logic:equal>
  </logic:notEqual>

  <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil040back');">
  </td>
  </tr>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <logic:messagesPresent message="false">
  <table width="100%">
  <tr>
  <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </table>
  </logic:messagesPresent>

  <!-- ページコンテンツ start -->
  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td width="20%" valign="top">

    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="prj_mokuteki" nowrap><gsmsg:write key="cmn.filekanri" /></th>
    </tr>

    <logic:notEmpty name="fil040Form" property="fil040CabinetList">
    <tr>
    <td class="td_line_color2 cabinet_combo" align="center">
      <html:select name="fil040Form" property="fil040SelectCabinet" onchange="buttonPush('fil040changeCabinet');" style="width:245px;">
        <html:optionsCollection name="fil040Form" property="fil040CabinetList" value="value" label="label" />
      </html:select>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="f_tree prj_tbl_base4_fontsize">

      <div class="dtree" style="padding-left:7px;width:250px;height:500px;overflow:scroll;">
        <a href="javascript: treeObj.openAll();"><img src="../file/images/icon_tgl_plus.gif" style="vertical-align:bottom;" border="0" alt="<gsmsg:write key="cmn.open.all" />"><gsmsg:write key="cmn.open.all" /></a> |
        <a href="javascript: treeObj.closeAll();"><img src="../file/images/icon_tgl_minus.gif" style="vertical-align:bottom;" border="0" alt="<gsmsg:write key="cmn.close.all" />"><gsmsg:write key="cmn.close.all" /></a><br>

        <img src="../file/images/spacer.gif" width="400px" height="1px" border="0">

        <script type="text/javascript">

          var treeObj = new dTree('treeObj');
          var sepKey = document.forms[0].sepKey.value;
          var selectDir = document.forms[0].selectDir.value;
          var openDir = '-1';

          var arrayLv0 = document.getElementsByName("treeFormLv0");
          if (arrayLv0 != null && arrayLv0.length > 0) {
              for (i = 0; i < arrayLv0.length; i++) {
                  var sp = arrayLv0[i].value.split(sepKey);
                  if (sp.length == 3) {
                      treeObj.add(sp[0], -1, sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
                  }
              }
          }

          var arrayLv1 = document.getElementsByName("treeFormLv1");
          if (arrayLv1 != null && arrayLv1.length > 0) {
              for (i = 0; i < arrayLv1.length; i++) {
                  var sp = arrayLv1[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
                  }
              }
          }
          var arrayLv2 = document.getElementsByName("treeFormLv2");
          if (arrayLv2 != null && arrayLv2.length > 0) {
              for (i = 0; i < arrayLv2.length; i++) {
                  var sp = arrayLv2[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }
          var arrayLv3 = document.getElementsByName("treeFormLv3");
          if (arrayLv3 != null && arrayLv3.length > 0) {
              for (i = 0; i < arrayLv3.length; i++) {
                  var sp = arrayLv3[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }
          var arrayLv4 = document.getElementsByName("treeFormLv4");
          if (arrayLv4 != null && arrayLv4.length > 0) {
              for (i = 0; i < arrayLv4.length; i++) {
                  var sp = arrayLv4[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }
          var arrayLv5 = document.getElementsByName("treeFormLv5");
          if (arrayLv5 != null && arrayLv5.length > 0) {
              for (i = 0; i < arrayLv5.length; i++) {
                  var sp = arrayLv5[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }
          var arrayLv6 = document.getElementsByName("treeFormLv6");
          if (arrayLv6 != null && arrayLv6.length > 0) {
              for (i = 0; i < arrayLv6.length; i++) {
                  var sp = arrayLv6[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }

          var arrayLv7 = document.getElementsByName("treeFormLv7");
          if (arrayLv7 != null && arrayLv7.length > 0) {
              for (i = 0; i < arrayLv7.length; i++) {
                  var sp = arrayLv7[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }

          var arrayLv8 = document.getElementsByName("treeFormLv8");
          if (arrayLv8 != null && arrayLv8.length > 0) {
              for (i = 0; i < arrayLv8.length; i++) {
                  var sp = arrayLv8[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }

          var arrayLv9 = document.getElementsByName("treeFormLv9");
          if (arrayLv9 != null && arrayLv9.length > 0) {
              for (i = 0; i < arrayLv9.length; i++) {
                  var sp = arrayLv9[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }

          var arrayLv10 = document.getElementsByName("treeFormLv10");
          if (arrayLv10 != null && arrayLv10.length > 0) {
              for (i = 0; i < arrayLv10.length; i++) {
                  var sp = arrayLv10[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }
          document.write(treeObj);

          if (openDir != '-1') {
              treeObj.openTo(openDir, true);
          }
        </script>

      </div>

    </td>
    </tr>
    </table>

  </td>

  <td width="80%" valign="top">

    <!-- 一覧 -->
    <table class="tl0 prj_tbl_base3" width="100%" cellpadding="0" cellspacing="0">

    <tr>
    <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
    <td class="todo_th2" colspan="8">
    </logic:equal>
    <logic:notEqual name="fil040Form" property="fil040DspSelectDelAll" value="1">
      <td class="todo_th2" colspan="6">
    </logic:notEqual>

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <logic:equal name="fil040Form" property="fil040DspAddBtn" value="1">
      <th width="0%"><img src="../file/images/cabinet.gif" alt="<gsmsg:write key="fil.fil040.3" />" style="margin-right:5px;"></th>
      </logic:equal>
      <logic:notEqual name="fil040Form" property="fil040DspAddBtn" value="1">
      <th width="0%"><img src="../file/images/cabinet_stop.gif" alt="<gsmsg:write key="fil.fil040.4" />" style="margin-right:5px;"></th>
      </logic:notEqual>
      <th width="0%" align="left" nowrap>
      <logic:notEmpty name="fil040Form" property="fil040DirectoryPathList" scope="request">
      <logic:iterate id="pathBean" name="fil040Form" property="fil040DirectoryPathList" scope="request" indexId="idx">
      <a href="javascript:(fileLinkClick('detailDir', <bean:write name="pathBean" property="fdrSid"/>))"><span class="text_blue"><bean:write name="pathBean" property="fdrName"/>/</span></a>
      </logic:iterate>
      </logic:notEmpty>
      </th>
      <td width="100%" align="right" nowrap>
      <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
      <html:text name="fil040Form" maxlength="50" property="filSearchWd" styleClass="text_base" style="width:157px;" />

      <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onClick="MoveToSearch();">

      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
    <th width="3%" class="td_type_file">
    <html:checkbox name="fil040Form" property="fil040SelectDelAll" value="1" onclick="return changeChk();" />
    </th>
    </logic:equal>
    <th width="32%" class="td_type_file">
    <logic:equal name="fil040Form" property="fil040SortKey" value="1">
      <logic:equal name="fil040Form" property="fil040OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil040TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name4" />▲</span></a></logic:equal>
      <logic:equal name="fil040Form" property="fil040OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil040TitleClick(1, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name4" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil040Form" property="fil040SortKey" value="1">
      <a href="javascript:void(0);" onClick="return fil040TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name4" /></span></a>
    </logic:notEqual>
    </th>
    <th width="10%" class="td_type_file">
    <logic:equal name="fil040Form" property="fil040SortKey" value="2">
      <logic:equal name="fil040Form" property="fil040OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil040TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.size" />▲</span></a></logic:equal>
      <logic:equal name="fil040Form" property="fil040OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil040TitleClick(2, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.size" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil040Form" property="fil040SortKey" value="2">
      <a href="javascript:void(0);" onClick="return fil040TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.size" /></span></a>
    </logic:notEqual>
    </th>
    <th width="10%" class="td_type_file">
    <logic:equal name="fil040Form" property="fil040SortKey" value="3">
      <logic:equal name="fil040Form" property="fil040OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil040TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.1" />▲</span></a></logic:equal>
      <logic:equal name="fil040Form" property="fil040OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil040TitleClick(3, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.1" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil040Form" property="fil040SortKey" value="3">
      <a href="javascript:void(0);" onClick="return fil040TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.1" /></span></a>
    </logic:notEqual>
    </th>
    <th width="15%" class="td_type_file">
    <logic:equal name="fil040Form" property="fil040SortKey" value="4">
      <logic:equal name="fil040Form" property="fil040OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil040TitleClick(4, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" />▲</span></a></logic:equal>
      <logic:equal name="fil040Form" property="fil040OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil040TitleClick(4, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil040Form" property="fil040SortKey" value="4">
      <a href="javascript:void(0);" onClick="return fil040TitleClick(4, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" /></span></a>
    </logic:notEqual>
    </th>
    <th width="15%" class="td_type_file">
    <logic:equal name="fil040Form" property="fil040SortKey" value="5">
      <logic:equal name="fil040Form" property="fil040OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil040TitleClick(5, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" />▲</span></a></logic:equal>
      <logic:equal name="fil040Form" property="fil040OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil040TitleClick(5, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil040Form" property="fil040SortKey" value="5">
      <a href="javascript:void(0);" onClick="return fil040TitleClick(5, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" /></span></a>
    </logic:notEqual>
    </th>
    <th width="5%" class="td_type_file"><a href="javascript:void(0);"><span class="text_prjtodo_list_head"></span></a></th>

    <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
    <th width="5%" class="td_type_file"><a href="javascript:void(0);"><span class="text_prjtodo_list_head"></span></a></th>
    </logic:equal>
    </tr>

    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type_file1", "td_type_file2"}; %>
    <logic:notEmpty name="fil040Form" property="fil040DirectoryList" scope="request">
    <logic:iterate id="dirBean" name="fil040Form" property="fil040DirectoryList" scope="request" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

        <tr class="<%= tdColor %>">
        <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
        <td class="prj_td" align="center">
        <logic:equal name="dirBean" property="accessKbn" value="1">
        <html:multibox name="fil040Form" property="fil040SelectDel">
        <bean:write name="dirBean" property="fdrSid" />
        </html:multibox>
        </logic:equal>
        </td>
        </logic:equal>
        <logic:equal name="dirBean" property="fdrKbn" value="0">
        <logic:notEmpty name="dirBean" property="fdrBiko">
        <bean:define id="biko" name="dirBean" property="fdrBiko" />
        <%
        String tmpText = (String)pageContext.getAttribute("biko",PageContext.PAGE_SCOPE);
        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
        %>
        <td class="prj_td" align="left">
            <a href="javascript:(fileLinkClick('detailDir', <bean:write name="dirBean" property="fdrSid" />))" id="fdrsid<bean:write name="dirBean" property="fdrSid" />">
            <span class="tooltips"><gsmsg:write key="cmn.memo" />: <%= tmpText2 %></span>
            <img src="../file/images/icon_folder.gif" alt="">
            &nbsp;
            <span class="text_blue"><bean:write name="dirBean" property="fdrName" /></span>
            </a>
<% filTipList.add("fdrsid" + String.valueOf(((jp.groupsession.v2.fil.model.FileDirectoryModel) dirBean).getFdrSid())); %>
        </td>
        </logic:notEmpty>
        <logic:empty name="dirBean" property="fdrBiko">
        <td class="prj_td" align="left">
            <a href="javascript:(fileLinkClick('detailDir', <bean:write name="dirBean" property="fdrSid" />))" id="fdrsid<bean:write name="dirBean" property="fdrSid" />">
            <img src="../file/images/icon_folder.gif" alt="">
            &nbsp;
            <span class="text_blue"><bean:write name="dirBean" property="fdrName" /></span>
            </a>
        </td>
        </logic:empty>

        <td class="prj_td" align="right"><span class="text_base">&nbsp;</span></td>
        <td class="prj_td" align="center">
        <logic:equal name="dirBean" property="callKbn" value="1">
        &nbsp;<img src="../file/images/icon_call.gif" border="0" alt="" style="vertical-align:bottom;">&nbsp;
        </logic:equal>
        <logic:notEqual name="dirBean" property="callKbn" value="1">
        &nbsp;
        </logic:notEqual>
        </td>
        <td class="prj_td" align="center"><span class="text_base"><bean:write name="dirBean" property="edateString" /></span></td>
        <td class="prj_td" align="left">
        <logic:equal name="dirBean" property="upUsrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
          <s><span class="text_base"><bean:write name="dirBean" property="upUsrName" /></span></s>
        </logic:equal>
        <logic:notEqual name="dirBean" property="upUsrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
         <span class="text_base"><bean:write name="dirBean" property="upUsrName" /></span>
        </logic:notEqual>
        </td>
        <td class="prj_td" align="center"><input type="button" value="<gsmsg:write key="fil.18" />" class="btn_base1s" onClick="MoveToFolderDetail(<bean:write name="dirBean" property="fdrSid" />,<bean:write name="dirBean" property="fdrParentSid" />);"></td>
        <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
        <td class="prj_td" align="center">
          <logic:equal name="dirBean" property="accessKbn" value="1">
            <input type="button" value="<gsmsg:write key="fil.fil040.7" />" class="btn_base1s" onClick="MoveToFileMove(<bean:write name="dirBean" property="fdrSid" />);">
          </logic:equal>
        </td>
        </logic:equal>
        </logic:equal>

        <logic:notEqual name="dirBean" property="fdrKbn" value="0">
        <logic:notEmpty name="dirBean" property="fdrBiko">
        <bean:define id="biko" name="dirBean" property="fdrBiko" />
        <%
        String tmpText = (String)pageContext.getAttribute("biko",PageContext.PAGE_SCOPE);
        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
        %>
        <td class="prj_td" align="left">
            <a href="javascript:void(0);" onClick="return fileDl('fileDownload', <bean:write name="dirBean" property="fileBinSid" />);" id="fdrsid<bean:write name="dirBean" property="fdrSid" />">
            <span class="tooltips"><gsmsg:write key="cmn.memo" />: <%= tmpText2 %></span>
            <img src="../file/images/page.gif" border="0" alt="" style="vertical-align:bottom;">
            <span class="text_blue"><bean:write name="dirBean" property="fdrName" /></span></a>
            <logic:equal name="dirBean" property="lockKbn" value="1">
            <logic:equal name="fil040Form" property="admLockKbn" value="1">
            <br>
            <img src="../file/images/edit_locked.gif" alt=""><span class="text_r1"><gsmsg:write key="fil.fil040.5" />(<bean:write name="dirBean" property="lockUsrName" />)</span>
              <logic:equal name="fil040Form" property="fil040UnLockAuth" value="1">
                <logic:notEmpty name="dirBean" property="fdrBiko">
                  <a href="#" onClick="UnLock(<bean:write name="dirBean" property="fdrSid" />,<bean:write name="dirBean" property="fdrVersion" />)"><span class="sc_link"><img src="../file/images/key_unlock.gif" title="<gsmsg:write key="cmn.memo" />:<bean:write name="dirBean" property="fdrBiko" />" alt=""><gsmsg:write key="fil.fil040.6" /></span></a>
                </logic:notEmpty>
                <logic:empty name="dirBean" property="fdrBiko">
                  <a href="#" onClick="UnLock(<bean:write name="dirBean" property="fdrSid" />,<bean:write name="dirBean" property="fdrVersion" />)"><span class="sc_link"><img src="../file/images/key_unlock.gif" alt=""><gsmsg:write key="fil.fil040.6" /></span></a>
                </logic:empty>
              </logic:equal>
              <logic:notEmpty name="dirBean" property="lockDate">
              <br><span class="text_base"><bean:write name="dirBean" property="lockDate" />～</span>
              </logic:notEmpty>
            </logic:equal>
            </logic:equal>

        </td>
        <% filTipList.add("fdrsid" + String.valueOf(((jp.groupsession.v2.fil.model.FileDirectoryModel) dirBean).getFdrSid())); %>
        </logic:notEmpty>
        <logic:empty name="dirBean" property="fdrBiko">
        <td class="prj_td" align="left">
            <a href="javascript:void(0);" onClick="return fileDl('fileDownload', <bean:write name="dirBean" property="fileBinSid" />);" id="fdrsid<bean:write name="dirBean" property="fdrSid" />">
            <img src="../file/images/page.gif" border="0" alt="" style="vertical-align:bottom;">
            <span class="text_blue"><bean:write name="dirBean" property="fdrName" /></span></a>
            <logic:equal name="dirBean" property="lockKbn" value="1">
            <logic:equal name="fil040Form" property="admLockKbn" value="1">
            <br>
            <img src="../file/images/edit_locked.gif" alt=""><span class="text_r1"><gsmsg:write key="fil.fil040.5" />(<bean:write name="dirBean" property="lockUsrName" />)</span>
              <logic:equal name="fil040Form" property="fil040UnLockAuth" value="1">
                <logic:notEmpty name="dirBean" property="fdrBiko">
                  <a href="#" onClick="UnLock(<bean:write name="dirBean" property="fdrSid" />,<bean:write name="dirBean" property="fdrVersion" />)"><span class="sc_link"><img src="../file/images/key_unlock.gif" title="<gsmsg:write key="cmn.memo" />:<bean:write name="dirBean" property="fdrBiko" />" alt=""><gsmsg:write key="fil.fil040.6" /></span></a>
                </logic:notEmpty>
                <logic:empty name="dirBean" property="fdrBiko">
                  <a href="#" onClick="UnLock(<bean:write name="dirBean" property="fdrSid" />,<bean:write name="dirBean" property="fdrVersion" />)"><span class="sc_link"><img src="../file/images/key_unlock.gif" alt=""><gsmsg:write key="fil.fil040.6" /></span></a>
                </logic:empty>
              </logic:equal>
              <logic:notEmpty name="dirBean" property="lockDate">
              <br><span class="text_base"><bean:write name="dirBean" property="lockDate" />～</span>
              </logic:notEmpty>
            </logic:equal>
            </logic:equal>

        </td>
        </logic:empty>
        <td class="prj_td" align="right"><span class="text_base"><bean:write name="dirBean" property="fileSize" /></span></td>
        <td class="prj_td" align="center">
        &nbsp;
        </td>
        <td class="prj_td" align="center"><span class="text_base"><bean:write name="dirBean" property="edateString" /></span></td>
        <td class="prj_td" align="left">
        <logic:equal name="dirBean" property="upUsrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
          <s><span class="text_base"><bean:write name="dirBean" property="upUsrName" /></span></s>
        </logic:equal>
        <logic:notEqual name="dirBean" property="upUsrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
         <span class="text_base"><bean:write name="dirBean" property="upUsrName" /></span>
        </logic:notEqual>
        </td>
        <td class="prj_td" align="center">
        <input type="button" value="<gsmsg:write key="fil.18" />" class="btn_base1s" onClick="MoveToFileDetail(<bean:write name="dirBean" property="fdrSid" />);">
        </td>

        <logic:equal name="fil040Form" property="fil040DspSelectDelAll" value="1">
        <td class="prj_td" align="center">
        <logic:equal name="dirBean" property="accessKbn" value="1">
          <input type="button" value="<gsmsg:write key="fil.fil040.7" />" class="btn_base1s" onClick="MoveToFileMove(<bean:write name="dirBean" property="fdrSid" />);">
        </logic:equal>
        </td>
        </logic:equal>
        </logic:notEqual>


        </tr>


    </logic:iterate>
    </logic:notEmpty>
    </table>

    <table width="100%" cellpadding="0" cellspacing="5">
    <tr>
    <td width="0%" align="left" nowrap>
    <img src="../file/images/icon_call.gif" border="0" alt="" style="vertical-align:bottom;">&nbsp;<span class="text_base">：<gsmsg:write key="fil.fil040.8" /></span>
    </td>
    </tr>
    </table>

    <table width="100%" cellpadding="0" cellspacing="0">
      <tr valign="center">
      <td>
      <span class="text_base"><font size="-1">URL:</font></span><input type="text" value="<bean:write name="fil040Form" property="fil040UrlString" />" style="width:575px" class="text_theadurl" readOnly="true" />
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
<!--
<script type="text/javascript">
<% for (int filCnt = 0; filCnt < filTipList.size(); filCnt++) { %>
$(function() {
  $('#<%= (java.lang.String) filTipList.get(filCnt) %>').tooltip();
});
<% } %>
</script>
 -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>