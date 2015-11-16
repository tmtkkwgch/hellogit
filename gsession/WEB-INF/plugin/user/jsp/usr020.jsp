<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-groupTree.tld" prefix="groupTree" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.grouplist" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/group2.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/tree.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<bean:define id="type" name="usr020Form" property="listType" scope="request"/>
<bean:define id="level" name="usr020Form" property="selectLevel" scope="request"/>
<bean:define id="root" name="usr020Form" property="dspRoot" scope="request"/>

<body class="body_03" onload="checkedGroup();">
<html:form action="/user/usr020">

<html:hidden property="parentName" />

<!-- BODY 通常時 -->
<table>
<tr>
<td width="500">

<groupTree:gtreewrite name="usr020Form" property="groupList" root="<%= root.toString() %>" level="<%= level.toString() %>" type="<%= type.toString() %>"/>
</td>
</tr>

</table>

<div id="trAfter"><br><br>
<br><br>
<br>
</div>
</html:form>
</body>

</html:html>