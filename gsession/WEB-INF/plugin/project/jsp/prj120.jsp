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
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/freeze.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/readOnly.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj120.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03" onload="freezeScreenParent('', false);parentReadOnly();">

<html:form action="/project/prj120">
<input type="hidden" name="CMD" value="">

<table cellpadding="4" cellspacing="0" border="0" width="100%">
<tr>
<td>
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onclick="posclose();">
    </td>
    </tr>
  </table>
</td>
</tr>

<tr>
<td>

  <table cellpadding="0" cellspacing="0" class="tl0 table_td_border" width="100%">
  <tr class="table_bg_7D91BD_search">
  <th width="35%"><span class="text_tlw"><gsmsg:write key="project.31" /></span></th>
  <th width="65%"><span class="text_tlw"><gsmsg:write key="project.40" /></span></th>
  </tr>

  <logic:notEmpty name="prj120Form" property="projectList" scope="request">
  <logic:iterate id="prjMdl" name="prj120Form" property="projectList" scope="request" indexId="idx">
  <bean:define id="backclass" value="td_prj_list" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
  <tr class="<%= String.valueOf(back) %>">
  <td>
  <img src="../project/images/prj_icon.gif" name="pitctImage" width="20" height="20" alt="<gsmsg:write key="cmn.project" />" border="1" class="prj_img_ico">&nbsp;
  <bean:write name="prjMdl" property="projectId" /></td>
  <td>
    <img src="../project/images/prj_icon.gif" name="pitctImage" width="20" height="20" alt="<gsmsg:write key="cmn.project" />" border="1" class="prj_img_ico">&nbsp;
    <span class="blue_link">
    <a href="javascript:void(0);" onClick="return parentReload('<bean:write name="prjMdl" property="projectSid" />');"><bean:write name="prjMdl" property="projectName" /></a>
    </span>
  </td>
  </tr>
  </logic:iterate>
  </logic:notEmpty>

  </table>

</td>
</tr>
</table>

</html:form>
</body>
</html:html>