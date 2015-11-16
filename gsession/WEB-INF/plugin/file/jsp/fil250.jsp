<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String delOptFile = String.valueOf(jp.groupsession.v2.fil.GSConstFile.DELETE_OPTION_FILE);
   String delOptFolderFile = String.valueOf(jp.groupsession.v2.fil.GSConstFile.DELETE_OPTION_FOLDER_FILE);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.124" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil250.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/file/fil250">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="backScreen" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="filSearchWd" />

<logic:notEmpty name="fil250Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil250Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil250Form" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->

<html:hidden property="backScreen" />
<logic:notEmpty name="fil250Form" property="treeFormLv1" scope="request">
  <logic:iterate id="lv1" name="fil250Form" property="treeFormLv1" scope="request">
    <input type="hidden" name="treeFormLv1" value="<bean:write name="lv1"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv2" scope="request">
  <logic:iterate id="lv2" name="fil250Form" property="treeFormLv2" scope="request">
    <input type="hidden" name="treeFormLv2" value="<bean:write name="lv2"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv3" scope="request">
  <logic:iterate id="lv3" name="fil250Form" property="treeFormLv3" scope="request">
    <input type="hidden" name="treeFormLv3" value="<bean:write name="lv3"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv4" scope="request">
  <logic:iterate id="lv4" name="fil250Form" property="treeFormLv4" scope="request">
    <input type="hidden" name="treeFormLv4" value="<bean:write name="lv4"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv5" scope="request">
  <logic:iterate id="lv5" name="fil250Form" property="treeFormLv5" scope="request">
    <input type="hidden" name="treeFormLv5" value="<bean:write name="lv5"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv6" scope="request">
  <logic:iterate id="lv6" name="fil250Form" property="treeFormLv6" scope="request">
    <input type="hidden" name="treeFormLv6" value="<bean:write name="lv6"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv7" scope="request">
  <logic:iterate id="lv7" name="fil250Form" property="treeFormLv7" scope="request">
    <input type="hidden" name="treeFormLv7" value="<bean:write name="lv7"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv8" scope="request">
  <logic:iterate id="lv8" name="fil250Form" property="treeFormLv8" scope="request">
    <input type="hidden" name="treeFormLv8" value="<bean:write name="lv8"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv9" scope="request">
  <logic:iterate id="lv9" name="fil250Form" property="treeFormLv9" scope="request">
    <input type="hidden" name="treeFormLv9" value="<bean:write name="lv9"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil250Form" property="treeFormLv10" scope="request">
  <logic:iterate id="lv10" name="fil250Form" property="treeFormLv10" scope="request">
    <input type="hidden" name="treeFormLv10" value="<bean:write name="lv10"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="selectDir" />
<html:hidden property="sepKey" />
<html:hidden property="fil250DirSid" />
<html:hidden property="fil250RootDirSid" />
<html:hidden property="fil250RootDirName" />

<logic:notEmpty name="fil250Form" property="fil250SvCallUser">
<logic:iterate id="calUser" name="fil250Form" property="fil250SvCallUser">
  <input type="hidden" name="fil250SvCallUser" value="<bean:write name="calUser" />">
</logic:iterate>
</logic:notEmpty>


<!-- HEADER -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="fil.124" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil250ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil250back');">
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

    <img src="../file/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.folder" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%"><span class="text_base_prj"><div id="moveDirName"><bean:write name="fil250Form" property="fil250DirPath" /></div></span></td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.23" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">

      <logic:notEmpty name="fil250Form" property="fil250cabinetList">
      <html:select name="fil250Form" property="fil250SltCabinetSid" onchange="buttonPush('changeCabinet');">
      <html:optionsCollection name="fil250Form" property="fil250cabinetList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>

    </td>
    </tr>
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.fil230.5" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">

      <div class="dtree" style="padding-left:10px;width:97%;height:300px;overflow:scroll;">

      <logic:notEmpty name="fil250Form" property="fil250SltCabinetSid">
      <logic:notEqual name="fil250Form" property="fil250SltCabinetSid" value="-1">

        <script type="text/javascript">
          var treeObj = new dTree('treeObj');
          treeObj.add(<bean:write name="fil250Form" property="fil250RootDirSid" />,-1,'<bean:write name="fil250Form" property="fil250RootDirName" />', 'javascript:(file250TreeClick(\'detailDir\', <bean:write name="fil250Form" property="fil250RootDirSid" />))');

          var sepKey = document.forms[0].sepKey.value;
          var selectDir = document.forms[0].fil250DirSid.value;
          var openDir = '-1';
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
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file250TreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }

          document.write(treeObj);
          if (openDir != '-1') {
              treeObj.openTo(openDir, true);
          }
        </script>

      </logic:notEqual>
      </logic:notEmpty>
      </div>
    </td>

    </td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.125" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">

      <table width="0%" border="0">
      <tr>

      <td align="left" colspan="3" class="text_base"><gsmsg:write key="fil.fil250.1" /></td>

      </tr>

      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="fil.125" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" align="left">
      <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.fil250callUserSltGroup, 'fil250callUserSltGroup', '<bean:write name="fil250Form" property="fil250callUserSltGroup" />', '0', 'changeGrp', 'fil250SvCallUser', '-1', '0')" type="button">
      <html:select name="fil250Form" property="fil250callUserSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil250Form" property="fil250callUserGroupCombo">
      <html:optionsCollection name="fil250Form" property="fil250callUserGroupCombo" value="value" label="label" />
      </logic:notEmpty>
      </html:select>

      <span class="text_base">
      <input type="button" onclick="openGroupWindow(this.form.fil250callUserSltGroup, 'fil250callUserSltGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="fil250GroupBtn">
      <input type="checkbox" name="fil250callUserAllSlt" value="1" id="select_admin" onclick="return allSelectUser();" />
      <label for="select_admin"><gsmsg:write key="cmn.select" /></label></span>

      </td>
      </tr>

      <tr>
      <td align="center">
      <html:select name="fil250Form" property="fil250callUser" size="13" multiple="true" styleClass="select01" style="width: 220px;">
      <logic:notEmpty name="fil250Form" property="fil250callUserCombo">
      <html:optionsCollection name="fil250Form" property="fil250callUserCombo" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil250addUser');"><br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil250delUser');">
      </td>

      <td valign="top">
      <html:select name="fil250Form" property="fil250callUserRight" size="13" multiple="true" styleClass="select01" style="width: 220px;">
      <logic:notEmpty name="fil250Form" property="fil250callUserRightCombo">
      <html:optionsCollection name="fil250Form" property="fil250callUserRightCombo" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      </table>

    </td>
    </tr>
    </table>

  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
      <input type="button" class="btn_ok1" value="OK" onclick="buttonPush('fil250ok');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil250back');">
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