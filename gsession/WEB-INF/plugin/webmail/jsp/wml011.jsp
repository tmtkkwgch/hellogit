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
<title>[Group Session] <gsmsg:write key="wml.header.info" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

  <logic:notEmpty name="wml011Form" property="wml010theme">
    <bean:define id="themeName" name="wml011Form" property="wml010theme" type="java.lang.String" />
    <link rel="stylesheet" type="text/css" href="../webmail/css/theme/<%= themeName %>.css?<%= GSConst.VERSION_PARAM %>" />
  </logic:notEmpty>

</head>

<body class="body_03">

<html:form action="/webmail/wml011">

<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<div class="mail_header">
<gsmsg:write key="wml.header.info" />
</div>

<logic:notEmpty name="wml011Form" property="wml011MailHeaderData">
<div class="mail_body">
<logic:iterate id="headerData" name="wml011Form" property="wml011MailHeaderData">
  <bean:write name="headerData" property="wmhType" />: <bean:write name="headerData" property="wmhContent" /><br>
</logic:iterate>
</div>
</logic:notEmpty>
<br>
<div align="center">
  <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
</div>

</html:form>
</body>
</html:html>