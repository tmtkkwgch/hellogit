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
<title>[Group Session] <gsmsg:write key="wml.wml080.03" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../webmail/js/wml080.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml080">

<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden name="wml080Form" property="wmlViewAccount" />

<div class="mail_header">
<gsmsg:write key="cmn.subject" />: <bean:write name="wml080Form" property="wml080Title" /><br>
<gsmsg:write key="cmn.sender" />: <bean:write name="wml080Form" property="wml080From" /><br>
<gsmsg:write key="cmn.send.date" />: <bean:write name="wml080Form" property="wml080Sdate" /> <bean:write name="wml080Form" property="wml080Stime" /><br>
</div>
<logic:notEmpty name="wml080Form" property="tempFileList">
<div class="mail_header_tempfile">
<table width="100%" border="0" padding="3" style="background-color:#ffffff!important;font-size:80%;">
<logic:iterate id="fileData" name="wml080Form" property="tempFileList">
 <tr><td>
 <span class="tempfile">
 <a href="#" onClick="fileDownload(<bean:write name="wml080Form" property="wml080mailNum" />,<bean:write name="fileData" property="binSid" />);"><bean:write name="fileData" property="fileName" /></a>
 </span>
 </td></tr>
</logic:iterate>
</table>
</div>
</logic:notEmpty>
<div class="mail_body">
<logic:notEqual name="wml080Form" property="wml080BodyFlg" value="1">
  <span class="sc_ttl_sun"><bean:write name="wml080Form" property="wml080Body" /></span>
</logic:notEqual>
<logic:equal name="wml080Form" property="wml080BodyFlg" value="1">
  <bean:write name="wml080Form" property="wml080Body" filter="false" />
</logic:equal>
</div>
<br>
<div align="center">
  <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
</div>

</html:form>
</body>
</html:html>