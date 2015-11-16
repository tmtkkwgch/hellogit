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
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.fil230.4" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil230.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/file/fil230">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="backScreen" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="filSearchWd" />

<logic:notEmpty name="fil230Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil230Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil230Form" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->

<html:hidden property="backScreen" />
<logic:notEmpty name="fil230Form" property="treeFormLv1" scope="request">
  <logic:iterate id="lv1" name="fil230Form" property="treeFormLv1" scope="request">
    <input type="hidden" name="treeFormLv1" value="<bean:write name="lv1"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv2" scope="request">
  <logic:iterate id="lv2" name="fil230Form" property="treeFormLv2" scope="request">
    <input type="hidden" name="treeFormLv2" value="<bean:write name="lv2"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv3" scope="request">
  <logic:iterate id="lv3" name="fil230Form" property="treeFormLv3" scope="request">
    <input type="hidden" name="treeFormLv3" value="<bean:write name="lv3"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv4" scope="request">
  <logic:iterate id="lv4" name="fil230Form" property="treeFormLv4" scope="request">
    <input type="hidden" name="treeFormLv4" value="<bean:write name="lv4"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv5" scope="request">
  <logic:iterate id="lv5" name="fil230Form" property="treeFormLv5" scope="request">
    <input type="hidden" name="treeFormLv5" value="<bean:write name="lv5"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv6" scope="request">
  <logic:iterate id="lv6" name="fil230Form" property="treeFormLv6" scope="request">
    <input type="hidden" name="treeFormLv6" value="<bean:write name="lv6"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv7" scope="request">
  <logic:iterate id="lv7" name="fil230Form" property="treeFormLv7" scope="request">
    <input type="hidden" name="treeFormLv7" value="<bean:write name="lv7"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv8" scope="request">
  <logic:iterate id="lv8" name="fil230Form" property="treeFormLv8" scope="request">
    <input type="hidden" name="treeFormLv8" value="<bean:write name="lv8"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv9" scope="request">
  <logic:iterate id="lv9" name="fil230Form" property="treeFormLv9" scope="request">
    <input type="hidden" name="treeFormLv9" value="<bean:write name="lv9"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil230Form" property="treeFormLv10" scope="request">
  <logic:iterate id="lv10" name="fil230Form" property="treeFormLv10" scope="request">
    <input type="hidden" name="treeFormLv10" value="<bean:write name="lv10"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="selectDir" />
<html:hidden property="sepKey" />
<html:hidden property="fil230DeleteDirSid" />
<html:hidden property="fil230RootDirSid" />
<html:hidden property="fil230RootDirName" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="fil.fil230.4" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil230delete');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil230back');">
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
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.36" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%"><span class="text_base_prj"><div id="moveDirName"><bean:write name="fil230Form" property="fil230DeleteDir" /></div></span></td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.23" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">

      <logic:notEmpty name="fil230Form" property="fil230cabinetList">
      <html:select name="fil230Form" property="fil230SltCabinetSid" onchange="buttonPush('changeCabinet');">
      <html:optionsCollection name="fil230Form" property="fil230cabinetList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>

    </td>
    </tr>
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.fil230.5" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">

      <div class="dtree" style="padding-left:10px;width:97%;height:300px;overflow:scroll;">
      <logic:notEqual name="fil230Form" property="fil230SltCabinetSid" value="-1">

        <script type="text/javascript">
          var treeObj = new dTree('treeObj');
          treeObj.add(<bean:write name="fil230Form" property="fil230RootDirSid" />,-1,'<bean:write name="fil230Form" property="fil230RootDirName" />', 'javascript:(file230TreeClick(\'detailDir\', <bean:write name="fil230Form" property="fil230RootDirSid" />))');

          var sepKey = document.forms[0].sepKey.value;
          var selectDir = document.forms[0].fil230DeleteDirSid.value;
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
                      treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
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
                  treeObj.add(sp[0], sp[1], sp[2], 'javascript:(file230TreeClick(\'detailDir\', ' + sp[0] + '))');
              }
          }

          document.write(treeObj);
          if (openDir != '-1') {
              treeObj.openTo(openDir, true);
          }
        </script>

      </logic:notEqual>
      </div>
    </td>

    </td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.35" /></span>
    </td>
    <td align="left" class="td_sub_detail">

    <html:radio property="fil230DeleteOpt" value="<%= delOptFile %>" styleId="delOpt_00" /><span class="text_base7"><label for="delOpt_00"><gsmsg:write key="fil.99" /></label></span>&nbsp;
    <html:radio property="fil230DeleteOpt" value="<%= delOptFolderFile %>" styleId="delOpt_01" /><span class="text_base7"><label for="delOpt_01"><gsmsg:write key="fil.37" /></label></span>&nbsp;

    <br><span class="text_r1">※<gsmsg:write key="fil.fil230.6" /></span>
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
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil230delete');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil230back');">
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