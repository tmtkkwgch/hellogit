<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhWml010Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_wml010">
<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /></b>

<logic:empty name="mbhWml010Form" property="accountCombo">
<br>
<br>
<gsmsg:write key="mobile.26" />
</logic:empty>

<logic:notEmpty name="mbhWml010Form" property="accountCombo">
<bean:define id="wacSid" name="mbhWml010Form" property="wmlAccount" type="java.lang.Integer" />
<% String accountSid = String.valueOf(wacSid.intValue()); %>

<hr>
●<gsmsg:write key="wml.102" />
<br>
<html:select property="wmlAccount">
  <html:optionsCollection name="mbhWml010Form" property="accountCombo" value="value" label="label" />
</html:select>
<input name="wml010changeButton" value="<gsmsg:write key="cmn.change" />" type="submit">

<logic:notEmpty name="mbhWml010Form" property="directoryList">

<hr>
●<a href="./mh_wml040.do<%= jsessionId.toString() %>?wmlAccount=<%= accountSid %>"><gsmsg:write key="cmn.create.new" /></a>
<br>
<logic:iterate id="dirData" name="mbhWml010Form" property="directoryList">
<bean:define id="noReadCnt" name="dirData" property="noReadCount" type="java.lang.Long" />
<% boolean noRead = noReadCnt.longValue() > 0; %>
■<% if (noRead) { %><b><% } %><a href="./mh_wml020.do<%= jsessionId.toString() %>?wmlAccount=<%= accountSid %>&wmlDirectory=<bean:write name="dirData" property="id" />"><bean:write name="dirData" property="name" /><% if (noRead) { %><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %></a><% if (noRead) { %></b><% } %>
<br>
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhWml010Form" property="labelList">
<br>
■<gsmsg:write key="cmn.label" />
<bean:define id="labelCnt" name="mbhWml010Form" property="labelListCount" type="java.lang.Integer" />
<logic:iterate id="labelData" name="mbhWml010Form" property="labelList" indexId="labelIdx">
<bean:define id="noReadCnt" name="labelData" property="noReadCount" type="java.lang.Long" />
<% boolean noRead = noReadCnt.longValue() > 0; %>
<br><%= (labelIdx.intValue() + 1 == labelCnt.intValue())?"┗":"┣" %><% if (noRead) { %><b><% } %><a href="./mh_wml020.do<%= jsessionId.toString() %>?wmlAccount=<%= accountSid %>&wmlLabel=<bean:write name="labelData" property="id" />"><bean:write name="labelData" property="name" /><% if (noRead) { %><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %></a><% if (noRead) { %></b><% } %>
</logic:iterate>
</logic:notEmpty>

</logic:notEmpty>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>