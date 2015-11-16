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
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<bean:define id="cabId" name="filptl010Form" property="dspFcbSid" type="java.lang.Integer" />
<% String filFormId = "cabinet" + String.valueOf(cabId.intValue()); %>

<bean:define id="fcbSid" name="filptl010Form" property="dspFcbSid" type="java.lang.Integer" />
<% String cabSid = String.valueOf(fcbSid.intValue()); %>

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03">
<logic:notEqual name="filptl010Form" property="dspFcbSid" value="0">
<html:form action="/file/filptl010" styleId="<%= filFormId %>">

<input type="hidden" name="CMD" value="">
<html:hidden property="dspFcbSid" />
<html:hidden property="selectDir" />
<html:hidden property="sepKey" />
<input type="hidden" name="backDsp" value="">
<input type="hidden" name="fil010SelectCabinet" value="">
<input type="hidden" name="fil010SelectDirSid" value="">


<logic:notEmpty name="filptl010Form" property="treeFormLv0">
<logic:iterate id="lv0" name="filptl010Form" property="treeFormLv0">
  <input type="hidden" name="tree<%= cabSid %>FormLv0" value="<bean:write name="lv0" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv1">
<logic:iterate id="lv1" name="filptl010Form" property="treeFormLv1">
  <input type="hidden" name="tree<%= cabSid %>FormLv1" value="<bean:write name="lv1" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv2">
<logic:iterate id="lv2" name="filptl010Form" property="treeFormLv2">
  <input type="hidden" name="tree<%= cabSid %>FormLv2" value="<bean:write name="lv2" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv3">
<logic:iterate id="lv3" name="filptl010Form" property="treeFormLv3">
  <input type="hidden" name="tree<%= cabSid %>FormLv3" value="<bean:write name="lv3" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv4">
<logic:iterate id="lv4" name="filptl010Form" property="treeFormLv4">
  <input type="hidden" name="tree<%= cabSid %>FormLv4" value="<bean:write name="lv4" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv5">
<logic:iterate id="lv5" name="filptl010Form" property="treeFormLv5">
  <input type="hidden" name="tree<%= cabSid %>FormLv5" value="<bean:write name="lv5" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv6">
<logic:iterate id="lv6" name="filptl010Form" property="treeFormLv6">
  <input type="hidden" name="tree<%= cabSid %>FormLv6" value="<bean:write name="lv6" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv7">
<logic:iterate id="lv7" name="filptl010Form" property="treeFormLv7">
  <input type="hidden" name="tree<%= cabSid %>FormLv7" value="<bean:write name="lv7" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv8">
<logic:iterate id="lv8" name="filptl010Form" property="treeFormLv8">
  <input type="hidden" name="tree<%= cabSid %>FormLv8" value="<bean:write name="lv8" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv9">
<logic:iterate id="lv9" name="filptl010Form" property="treeFormLv9">
  <input type="hidden" name="tree<%= cabSid %>FormLv9" value="<bean:write name="lv9" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="filptl010Form" property="treeFormLv10">
<logic:iterate id="lv10" name="filptl010Form" property="treeFormLv10">
  <input type="hidden" name="tree<%= cabSid %>FormLv10" value="<bean:write name="lv10" />">
</logic:iterate>
</logic:notEmpty>

    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="prj_mokuteki" width="0%" nowrap>
      <img src="../file/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="fil.1" />">
    </th>
    <th class="prj_mokuteki" width="100%" nowrap>
      <bean:write name="filptl010Form" property="filPtl010FcbName" />
    </th>
    </tr>

    <tr>
    <td class="f_tree" colspan="2" style="padding-left:7px;">

      <div class="dtree" style="width:100%;height:400px;overflow:scroll;">
        <a href="javascript: treeObj<%= cabSid %>.openAll();"><img src="../file/images/icon_tgl_plus.gif" style="vertical-align:bottom;" border="0" alt="<gsmsg:write key="cmn.open.all" />"><gsmsg:write key="cmn.open.all" /></a> |
        <a href="javascript: treeObj<%= cabSid %>.closeAll();"><img src="../file/images/icon_tgl_minus.gif" style="vertical-align:bottom;" border="0" alt="<gsmsg:write key="cmn.close.all" />"><gsmsg:write key="cmn.close.all" /></a><br>

        <img src="../file/images/spacer.gif" width="30px" height="1px" border="0">


        <div id="folderTree<%= cabSid %>"></div>

        <script type="text/javascript">

          var treeObj<%= cabSid %> = new dTree('treeObj<%= cabSid %>');
          var sepKey = document.getElementById("cabinet<%= cabSid %>").sepKey.value;
          var selectDir = document.getElementById("cabinet<%= cabSid %>").selectDir.value;
          var openDir = '-1';

          var arrayLv0 = document.getElementsByName('tree<%= cabSid %>FormLv0');
          if (arrayLv0 != null && arrayLv0.length > 0) {
              for (i = 0; i < arrayLv0.length; i++) {
                  var sp = arrayLv0[i].value.split(sepKey);
                  if (sp.length == 3) {
                      treeObj<%= cabSid %>.add(sp[0], -1, sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
                  }
              }
          }

          var arrayLv1 = document.getElementsByName('tree<%= cabSid %>FormLv1');
          if (arrayLv1 != null && arrayLv1.length > 0) {
              for (i = 0; i < arrayLv1.length; i++) {
                  var sp = arrayLv1[i].value.split(sepKey);
                  if (sp.length == 3) {
                      if (typeof selectDir != 'undefined') {
                          if (selectDir != '-1' && selectDir == sp[0]) {
                              openDir = sp[1];
                          }
                      }
                      treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
                  }
              }
          }
          var arrayLv2 = document.getElementsByName("tree<%= cabSid %>FormLv2");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }
          var arrayLv3 = document.getElementsByName("tree<%= cabSid %>FormLv3");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }
          var arrayLv4 = document.getElementsByName("tree<%= cabSid %>FormLv4");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }
          var arrayLv5 = document.getElementsByName("tree<%= cabSid %>FormLv5");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }
          var arrayLv6 = document.getElementsByName("tree<%= cabSid %>FormLv6");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }

          var arrayLv7 = document.getElementsByName("tree<%= cabSid %>FormLv7");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }

          var arrayLv8 = document.getElementsByName("tree<%= cabSid %>FormLv8");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }

          var arrayLv9 = document.getElementsByName("tree<%= cabSid %>FormLv9");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }

          var arrayLv10 = document.getElementsByName("tree<%= cabSid %>FormLv10");
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
                  treeObj<%= cabSid %>.add(sp[0], sp[1], sp[2], 'javascript:(filptlTreeClick(' + sp[0] + ', <%= filFormId %>))');
              }
          }

          document.getElementById("folderTree<%= cabSid %>").innerHTML = treeObj<%= cabSid %>;

          if (openDir != '-1') {
              treeObj<%= cabSid %>.openTo(openDir, true);
          }
        </script>

      </div>

    </td>
    </tr>
    </table>




</html:form>
</logic:notEqual>

</body>
</html:html>
