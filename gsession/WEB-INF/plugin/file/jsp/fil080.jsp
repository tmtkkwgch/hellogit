<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.fil.GSConstFile" %>

<%
    String maxLengthBiko        = String.valueOf(jp.groupsession.v2.fil.GSConstFile.MAX_LENGTH_FILE_BIKO);
    String maxLengthUpCmt       = String.valueOf(jp.groupsession.v2.fil.GSConstFile.MAX_LENGTH_FILE_UP_CMT);
    String VERSION_ALL_KBN_OFF  = String.valueOf(jp.groupsession.v2.fil.GSConstFile.VERSION_ALL_KBN_OFF);
    String VERSION_ALL_KBN_ON   = String.valueOf(jp.groupsession.v2.fil.GSConstFile.VERSION_ALL_KBN_ON);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.16" /> </title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil080.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<% boolean callWebmail = false; %>
<logic:equal name="fil080Form" property="fil080webmail" value="1">
  <link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
  <% callWebmail = true; %>
</logic:equal>

<body class="body_03" onload="fil080ShowOrHide();parentAccessShowOrHide(<bean:write name="fil080Form" property="fil080ParentAccessAll" />);showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthUpCmt %>, 'inputlength2');" onunload="windowClose();">

<html:form action="/file/fil080">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="backDspLow" />
<html:hidden property="admVerKbn" />
<html:hidden property="filSearchWd" />

<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />

<html:hidden property="fil070DirSid" />
<html:hidden property="fil070ParentDirSid" />
<html:hidden property="fil080Mode" />
<html:hidden property="fil080PluginId" />
<html:hidden property="fil080SvPluralKbn" />
<html:hidden property="fil080ParentAccessAll" />
<html:hidden property="fil080ParentZeroUser" />

<logic:equal name="fil080Form" property="fil080VerallKbn" value="<%= VERSION_ALL_KBN_ON %>">
<html:hidden property="fil080VerKbn" />
</logic:equal>

<html:hidden name="fil080Form" property="fil100SltCabinetSid" />
<html:hidden name="fil080Form" property="fil100ChkTrgFolder" />
<html:hidden name="fil080Form" property="fil100ChkTrgFile" />
<html:hidden name="fil080Form" property="fil100SearchMode" />
<html:hidden name="fil080Form" property="fil100ChkWdTrgName" />
<html:hidden name="fil080Form" property="fil100ChkWdTrgBiko" />
<html:hidden name="fil080Form" property="fil100ChkWdTrgText" />
<html:hidden name="fil080Form" property="fileSearchfromYear" />
<html:hidden name="fil080Form" property="fileSearchfromMonth" />
<html:hidden name="fil080Form" property="fileSearchfromDay" />
<html:hidden name="fil080Form" property="fileSearchtoYear" />
<html:hidden name="fil080Form" property="fileSearchtoMonth" />
<html:hidden name="fil080Form" property="fileSearchtoDay" />
<html:hidden name="fil080Form" property="fil100ChkOnOff" />

<html:hidden name="fil080Form" property="fil100SvSltCabinetSid" />
<html:hidden name="fil080Form" property="fil100SvChkTrgFolder" />
<html:hidden name="fil080Form" property="fil100SvChkTrgFile" />
<html:hidden name="fil080Form" property="fil100SvSearchMode" />
<html:hidden name="fil080Form" property="fil100SvChkWdTrgName" />
<html:hidden name="fil080Form" property="fil100SvChkWdTrgBiko" />
<html:hidden name="fil080Form" property="fil100SvChkWdTrgText" />
<html:hidden name="fil080Form" property="fil100SvChkWdKeyWord" />
<html:hidden name="fil080Form" property="fileSvSearchfromYear" />
<html:hidden name="fil080Form" property="fileSvSearchfromMonth" />
<html:hidden name="fil080Form" property="fileSvSearchfromDay" />
<html:hidden name="fil080Form" property="fileSvSearchtoYear" />
<html:hidden name="fil080Form" property="fileSvSearchtoMonth" />
<html:hidden name="fil080Form" property="fileSvSearchtoDay" />
<html:hidden name="fil080Form" property="fil100SvChkOnOff" />
<html:hidden name="fil080Form" property="fil100sortKey" />
<html:hidden name="fil080Form" property="fil100orderKey" />
<html:hidden name="fil080Form" property="fil100pageNum1" />
<html:hidden name="fil080Form" property="fil100pageNum2" />
<html:hidden name="fil080Form" property="fil240PageNum" />
<html:hidden name="fil080Form" property="backDspCall" />

<html:hidden property="fil080webmail" />

<logic:notEmpty name="fil080Form" property="fil080SvAcFull">
<logic:iterate id="afid" name="fil080Form" property="fil080SvAcFull">
  <input type="hidden" name="fil080SvAcFull" value="<bean:write name="afid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil080Form" property="fil080SvAcRead">
<logic:iterate id="arid" name="fil080Form" property="fil080SvAcRead">
  <input type="hidden" name="fil080SvAcRead" value="<bean:write name="arid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEqual name="fil080Form" property="fil080webmail" value="1">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
</logic:notEqual>

<% if (callWebmail) { %>
<html:hidden property="selectDir" />
<html:hidden property="sepKey" />
<input type="hidden" name="moveToDir" value="">

<logic:notEmpty name="fil080Form" property="treeFormLv0">
<logic:iterate id="lv0" name="fil080Form" property="treeFormLv0">
  <input type="hidden" name="treeFormLv0" value="<bean:write name="lv0" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv1">
<logic:iterate id="lv1" name="fil080Form" property="treeFormLv1">
  <input type="hidden" name="treeFormLv1" value="<bean:write name="lv1" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv2">
<logic:iterate id="lv2" name="fil080Form" property="treeFormLv2">
  <input type="hidden" name="treeFormLv2" value="<bean:write name="lv2" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv3">
<logic:iterate id="lv3" name="fil080Form" property="treeFormLv3">
  <input type="hidden" name="treeFormLv3" value="<bean:write name="lv3" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv4">
<logic:iterate id="lv4" name="fil080Form" property="treeFormLv4">
  <input type="hidden" name="treeFormLv4" value="<bean:write name="lv4" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv5">
<logic:iterate id="lv5" name="fil080Form" property="treeFormLv5">
  <input type="hidden" name="treeFormLv5" value="<bean:write name="lv5" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv6">
<logic:iterate id="lv6" name="fil080Form" property="treeFormLv6">
  <input type="hidden" name="treeFormLv6" value="<bean:write name="lv6" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv7">
<logic:iterate id="lv7" name="fil080Form" property="treeFormLv7">
  <input type="hidden" name="treeFormLv7" value="<bean:write name="lv7" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv8">
<logic:iterate id="lv8" name="fil080Form" property="treeFormLv8">
  <input type="hidden" name="treeFormLv8" value="<bean:write name="lv8" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv9">
<logic:iterate id="lv9" name="fil080Form" property="treeFormLv9">
  <input type="hidden" name="treeFormLv9" value="<bean:write name="lv9" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="fil080Form" property="treeFormLv10">
<logic:iterate id="lv10" name="fil080Form" property="treeFormLv10">
  <input type="hidden" name="treeFormLv10" value="<bean:write name="lv10" />">
</logic:iterate>
</logic:notEmpty>
<% } %>

<input type="hidden" name="helpPrm" value="<bean:write name="fil080Form" property="fil080Mode" />">
<!-- BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>

    <td width="0%">
      <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
    <td width="100%" class="header_white_bg_text">
      <logic:equal name="fil080Form" property="fil080Mode" value="0">
    [ <gsmsg:write key="fil.16" /> ]
      </logic:equal>
      <logic:equal name="fil080Form" property="fil080Mode" value="1">
    [ <gsmsg:write key="cmn.edit.file" /> ]
      </logic:equal>
    </td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="fil080Form" property="fil080Mode" value="0">
      <input type="button" class="btn_file_n1" value="<gsmsg:write key="fil.16" />" onclick="buttonPush('fil080add');">
      </logic:equal>
      <logic:equal name="fil080Form" property="fil080Mode" value="1">
      <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttonPush('fil080add');">
      <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil080delete');">
      </logic:equal>

    <% if (callWebmail) { %>
      <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.parent.webmailEntrySubWindowClose();">
    <% } else { %>
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil080back');">
    <% } %>
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

<% if (callWebmail) { %>
  <!-- ページコンテンツ start -->
  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td width="20%" valign="top">

    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="prj_mokuteki" nowrap><gsmsg:write key="cmn.filekanri" /></th>
    </tr>

    <logic:notEmpty name="fil080Form" property="fil040CabinetList">
    <tr>
    <td class="td_line_color2 cabinet_combo" align="center">
      <html:select name="fil080Form" property="fil040SelectCabinet" onchange="buttonPush('fil080changeCabinet');" style="width:245px;">
        <html:optionsCollection name="fil080Form" property="fil040CabinetList" value="value" label="label" />
      </html:select>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="f_tree prj_tbl_base4_fontsize">

      <div class="dtree" style="padding-left:7px;width:250px;height:400px;overflow:scroll;">
        <a href="javascript: treeObj.openAll();"><img src="../file/images/icon_tgl_plus.gif" style="vertical-align:bottom;" border="0" alt="<gsmsg:write key="cmn.open.all" />"><gsmsg:write key="cmn.open.all" /></a> |
        <a href="javascript: treeObj.closeAll();"><img src="../file/images/icon_tgl_minus.gif" style="vertical-align:bottom;" border="0" alt="<gsmsg:write key="cmn.close.all" />"><gsmsg:write key="cmn.close.all" /></a><br>

        <img src="../file/images/spacer.gif" width="400px" height="1px" border="0">

        <script type="text/javascript">
          var treeObj = new dTree('treeObj');
          var sepKey = document.forms[0].sepKey.value;
          var selectDir = document.forms[0].selectDir.value;
          var openDir = '-1';
          var indexNum = 0;
          var noLinkDirAry = [];

          var arrayLv0 = document.getElementsByName("treeFormLv0");
          if (arrayLv0 != null && arrayLv0.length > 0) {
              for (i = 0; i < arrayLv0.length; i++) {
                  var sp = arrayLv0[i].value.split(sepKey);
                  if (sp.length == 4) {
                      if (sp[3] > 0) {
                          treeObj.add(sp[0], -1, sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                      } else {
                          var nm = "<span class='noLinkDir'>" + sp[2] + "</span>"
                          treeObj.add(sp[0], -1, nm, '');
                      }
                  }
              }
          }

          var arrayLv1 = document.getElementsByName("treeFormLv1");
          if (arrayLv1 != null && arrayLv1.length > 0) {
              for (i = 0; i < arrayLv1.length; i++) {
                  var sp = arrayLv1[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                      if (sp[3] > 0) {
                          treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                      } else {
                          treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                          noLinkDirAry.push(indexNum);
                      }
                  }
              }
          }
          var arrayLv2 = document.getElementsByName("treeFormLv2");
          if (arrayLv2 != null && arrayLv2.length > 0) {
              for (i = 0; i < arrayLv2.length; i++) {
                  var sp = arrayLv2[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
                  }
              }
          }
          var arrayLv3 = document.getElementsByName("treeFormLv3");
          if (arrayLv3 != null && arrayLv3.length > 0) {
              for (i = 0; i < arrayLv3.length; i++) {
                  var sp = arrayLv3[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
                  }
              }
          }
          var arrayLv4 = document.getElementsByName("treeFormLv4");
          if (arrayLv4 != null && arrayLv4.length > 0) {
              for (i = 0; i < arrayLv4.length; i++) {
                  var sp = arrayLv4[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
              }
            }
          }
          var arrayLv5 = document.getElementsByName("treeFormLv5");
          if (arrayLv5 != null && arrayLv5.length > 0) {
              for (i = 0; i < arrayLv5.length; i++) {
                  var sp = arrayLv5[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
              }
            }
          }
          var arrayLv6 = document.getElementsByName("treeFormLv6");
          if (arrayLv6 != null && arrayLv6.length > 0) {
              for (i = 0; i < arrayLv6.length; i++) {
                  var sp = arrayLv6[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
                  }
              }
          }

          var arrayLv7 = document.getElementsByName("treeFormLv7");
          if (arrayLv7 != null && arrayLv7.length > 0) {
              for (i = 0; i < arrayLv7.length; i++) {
                  var sp = arrayLv7[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
              }
            }
          }

          var arrayLv8 = document.getElementsByName("treeFormLv8");
          if (arrayLv8 != null && arrayLv8.length > 0) {
              for (i = 0; i < arrayLv8.length; i++) {
                  var sp = arrayLv8[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
                  }
              }
          }

          var arrayLv9 = document.getElementsByName("treeFormLv9");
          if (arrayLv9 != null && arrayLv9.length > 0) {
              for (i = 0; i < arrayLv9.length; i++) {
                  var sp = arrayLv9[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
                  }
              }
          }

          var arrayLv10 = document.getElementsByName("treeFormLv10");
          if (arrayLv10 != null && arrayLv10.length > 0) {
              for (i = 0; i < arrayLv10.length; i++) {
                  var sp = arrayLv10[i].value.split(sepKey);
                  if (sp.length == 4) {
                      indexNum = indexNum + 1;
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                  }
                  if (sp[3] > 0) {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(fileTreeClick(\'changeDir\', ' + sp[0] + '))');
                  } else {
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:void(0)');
                      noLinkDirAry.push(indexNum);
                  }
              }
          }
          document.write(treeObj);

          if (openDir != '-1') {
              treeObj.openTo(openDir, true);
          }
          for (var i = 0; i < noLinkDirAry.length; i++) {
              $('#streeObj' + noLinkDirAry[i]).addClass('noLinkDir');
              $('#streeObj' + noLinkDirAry[i]).removeAttr('onclick');
          }
        </script>

      </div>

    </td>
    </tr>
    </table>

  </td>

  <td width="80%" valign="top">
<% } %>


    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.update.user" /></span>
    </td>
    <td align="left" class="td_sub_detail">
      <logic:notEmpty name="fil080Form" property="fil080groupList">
        <html:select property="fil080EditId" styleClass="select01">
          <html:optionsCollection name="fil080Form" property="fil080groupList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
    </td>
    </tr>

    <logic:equal name="fil080Form" property="fil080Mode" value="0">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.fil080.2" /></span>
    </td>
    <td align="left" class="td_sub_detail">
      <html:radio name="fil080Form" styleId="view1" onclick="buttonPush('changeMode');" property="fil080PluralKbn" value="0" /><label for="view1"><span class="text_base"><gsmsg:write key="fil.fil080.3" /></span></label>&nbsp;
      <html:radio name="fil080Form" styleId="view2" onclick="buttonPush('changeMode');" property="fil080PluralKbn" value="1" /><label for="view2"><span class="text_base"><gsmsg:write key="fil.fil080.4" /></span></label>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.21" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <logic:equal name="fil080Form" property="fil070ParentDirSid" value="-1">
        <span class="text_base_prj"><gsmsg:write key="wml.146" /></span>
    </logic:equal>
    <logic:notEqual name="fil080Form" property="fil070ParentDirSid" value="-1">
        <img src="../file/images/icon_folder.gif" border="0" alt="" style="vertical-align:middle;">
        <span class="text_base_prj"><bean:write name="fil080Form" property="fil080DirPath" /></span>
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.file" /></span></td>

    <td class="td_sub_detail" align="left" width="80%">

      <logic:equal name="fil080Form" property="fil080PluralKbn" value="0">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>
        <html:select property="fil080TempFiles" size="5" styleClass="select01" multiple="multiple" >
          <logicl:notEmpty name="fil080Form" property="fil080FileLabelList">
          <html:optionsCollection name="fil080Form" property="fil080FileLabelList" value="value" label="label" />
          </logicl:notEmpty>
        </html:select>
      </td>
      <td width="0%" align="left" valign="top" nowrap><img src="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>
      <td width="100%" align="left" valign="middle">
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('fil080TempFiles', '<bean:write name="fil080Form" property="fil080PluginId" />', '1', '3');">
      <br><br><input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('deleteTemp');">
      </td>
      </tr>
      </table>
      </logic:equal>

      <logic:equal name="fil080Form" property="fil080PluralKbn" value="1">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>
        <html:select property="fil080TempFiles" size="10" styleClass="select01" multiple="multiple" >
          <logicl:notEmpty name="fil080Form" property="fil080FileLabelList">
          <html:optionsCollection name="fil080Form" property="fil080FileLabelList" value="value" label="label" />
          </logicl:notEmpty>
        </html:select>
      </td>
      <td width="0%" align="left" valign="top" nowrap><img src="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>
      <td width="100%" align="left" valign="middle">
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('fil080TempFiles', '<bean:write name="fil080Form" property="fil080PluginId" />', '0', '3');">
      <br><br><input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('deleteTemp');">
      </td>
      </tr>
      </table>
      </logic:equal>

    </td>
    </tr>

    <!-- ユーザ制限 -->
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.126" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table width="100%" border="0">
      <logic:equal name="fil080Form" property="fil080ParentAccessAllDspFlg" value="1">
      <tr>
      <td align="left">
      <span class="hide0"><input type="button" value="<gsmsg:write key="cmn.all" /><gsmsg:write key="api.cmn.view" />" class="btn_base1s_2" onClick="return parentAccessShowOrHide(1);"></span>
      <span class="show0"><input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1s_2" onClick="return parentAccessShowOrHide(0);"></span>
      </td>
      <td align="center">&nbsp;</td>
      <td align="center">&nbsp;</td>
      </tr>
      </logic:equal>
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      </tr>
      <tr>
      <td width="40%" align="left" valign="top" class="td_sub_detail">
      <logic:notEmpty name="fil080Form" property="fil080ParentEditList">
      <logic:iterate id="editMdl" name="fil080Form" property="fil080ParentEditList" indexId="idx" length="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="editMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="editMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      <div class="hide0">
      <logic:equal name="fil080Form" property="fil080ParentAccessAllDspFlg" value="1">
      <bean:size name="fil080Form" property="fil080ParentEditList" id="editSize" />
      <logic:greaterThan name="editSize" value="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <span class="text_base">…</span>
      </logic:greaterThan>
      </logic:equal>
      </div>
      <div class="show0">
      <logic:notEmpty name="fil080Form" property="fil080ParentEditList">
      <logic:iterate id="editMdl" name="fil080Form" property="fil080ParentEditList" indexId="idx" offset="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="editMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="editMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      </div>
      </td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left" valign="top" class="td_sub_detail">
      <logic:notEmpty name="fil080Form" property="fil080ParentReadList">
      <logic:iterate id="readMdl" name="fil080Form" property="fil080ParentReadList" indexId="idx" length="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="readMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="readMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      <div class="hide0">
      <logic:equal name="fil080Form" property="fil080ParentAccessAllDspFlg" value="1">
      <bean:size name="fil080Form" property="fil080ParentReadList" id="readSize" />
      <logic:greaterThan name="readSize" value="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <span class="text_base">…</span>
      </logic:greaterThan>
      </logic:equal></div>
      <div class="show0">
      <logic:notEmpty name="fil080Form" property="fil080ParentReadList">
      <logic:iterate id="readMdl" name="fil080Form" property="fil080ParentReadList" indexId="idx" offset="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="readMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="readMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      </div>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.130" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
    <html:radio name="fil080Form" property="fil080AccessKbn" styleId="okini0" value="0" onclick="fil080ShowOrHide();"/>
    <span class="text_base7"><label for="okini0"><gsmsg:write key="cmn.not.limit" /></label></span>&nbsp;
    <html:radio name="fil080Form" property="fil080AccessKbn" styleId="okini1" value="1" onclick="fil080ShowOrHide();"/>
    <span class="text_base7"><label for="okini1"><gsmsg:write key="cmn.do.limit" /></label>&nbsp;</span>
    </td>
    </tr>
    <tr id="hide0"></tr>
    <tr id="show0">
    <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.102" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table width="100%" border="0">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" align="left">
      <html:select name="fil080Form" property="fil080AcEditSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil080Form" property="fil080AcEditGroupLavel">
      <html:optionsCollection name="fil080Form" property="fil080AcEditGroupLavel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      <span class="text_base">
      <input type="checkbox" name="fil080AcEditAllSlt" value="1" id="select_edit_user" onclick="return selectAccessEditList();" />
      <label for="select_edit_user"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center" valign="top">
      <html:select name="fil080Form" property="fil080AcFull" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil080Form" property="fil080AcFullLavel">
      <html:optionsCollection name="fil080Form" property="fil080AcFullLavel" value="value" label="label" />
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </logic:notEmpty>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil080fulladd');">
      <br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil080fulldel');">
      </td>

      <td valign="top">
      <html:select name="fil080Form" property="fil080AcEditRight" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil080Form" property="fil080AcEditRightLavel">
      <html:optionsCollection name="fil080Form" property="fil080AcEditRightLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left">
      <html:select name="fil080Form" property="fil080AcReadSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil080Form" property="fil080AcReadGroupLavel">
      <html:optionsCollection name="fil080Form" property="fil080AcReadGroupLavel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      <span class="text_base">
      <input type="checkbox" name="fil080AcReadAllSlt" value="1" id="select_read_user" onclick="return selectAccessReadList();" />
      <label for="select_read_user"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center" valign="top">
      <html:select name="fil080Form" property="fil080AcRead" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil080Form" property="fil080AcReadLavel">
      <html:optionsCollection name="fil080Form" property="fil080AcReadLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil080readadd');"><br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil080readdel');">
      </td>
      <td valign="top">
      <html:select name="fil080Form" property="fil080AcReadRight" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil080Form" property="fil080AcReadRightLavel">
      <html:optionsCollection name="fil080Form" property="fil080AcReadRightLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      </table>
    </td>
    </tr>
    <logic:equal name="fil080Form" property="admVerKbn" value="1">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.5" /></span>
    </td>
    <td align="left" class="td_sub_detail">

    <logic:equal name="fil080Form" property="fil080VerallKbn" value="<%= VERSION_ALL_KBN_OFF %>">

      <logic:notEmpty name="fil080Form" property="fil080VerKbnLabelList">
      <html:select property="fil080VerKbn">
      <html:optionsCollection name="fil080Form" property="fil080VerKbnLabelList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>

    </logic:equal>

    <logic:equal name="fil080Form" property="fil080VerallKbn" value="<%= VERSION_ALL_KBN_ON %>">

      <logic:notEqual name="fil080Form" property="fil080VerKbn" value="0">
      <bean:define id="ver" name="fil080Form" property="fil080VerKbn" type="java.lang.String" />
      <span class="text_base_prj"><gsmsg:write key="fil.generations" arg0="<%= ver %>" /></span>
      </logic:notEqual>

      <logic:equal name="fil080Form" property="fil080VerKbn" value="0">
      <span class="text_base_prj"><gsmsg:write key="fil.22" /></span>
      </logic:equal>

    </logic:equal>

    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <textarea name="fil080Biko" style="width:541px;" rows="5" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="fil080Form" property="fil080Biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.11" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <textarea name="fil080UpCmt" style="width:541px;" rows="3" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthUpCmt %>, 'inputlength2');" id="inputstr2"><bean:write name="fil080Form" property="fil080UpCmt" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthUpCmt %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    </table>

<% if (callWebmail) { %>
  </td>
  </tr>
  </table>
<% } %>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
    <logic:equal name="fil080Form" property="fil080Mode" value="0">
    <input type="button" class="btn_file_n1" value="<gsmsg:write key="fil.16" />" onclick="buttonPush('fil080add');">
    </logic:equal>
    <logic:equal name="fil080Form" property="fil080Mode" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttonPush('fil080add');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil080delete');">
    </logic:equal>


    <% if (callWebmail) { %>
      <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.parent.webmailEntrySubWindowClose();">
    <% } else { %>
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil080back');">
    <% } %>
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>


<logic:notEqual name="fil080Form" property="fil080webmail" value="1">
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</logic:notEqual>

</body>
</html:html>