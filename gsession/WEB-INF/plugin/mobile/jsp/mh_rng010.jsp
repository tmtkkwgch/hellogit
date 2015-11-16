<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRng010Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="rng.62" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_rng010">


<b><%= emojiRingi.toString() %><gsmsg:write key="rng.62" /></b>

<hr>
●<a href="./mh_rng040.do<%= jsessionId.toString() %>"><gsmsg:write key="rng.rng010.02" /></a>
<br>
■<b><a href="./mh_rng020.do<%= jsessionId.toString() %>?rngProcMode=0"><gsmsg:write key="cmn.receive" />
<logic:notEqual name="mbhRng010Form" property="bbs010ReceptCnt" value="0">
(<bean:write name="mbhRng010Form" property="bbs010ReceptCnt"/>)
</logic:notEqual>
</a></b>
<br>
■<a href="./mh_rng020.do<%= jsessionId.toString() %>?rngProcMode=1&rng020sortKey=2"><gsmsg:write key="rng.application.ongoing" /></a>
<br>
■<a href="./mh_rng020.do<%= jsessionId.toString() %>?rngProcMode=2&rng020sortKey=4&rng020orderKey=1"><gsmsg:write key="cmn.complete" /></a>
<br>
■<a href="./mh_rng020.do<%= jsessionId.toString() %>?rngProcMode=3&rng020sortKey=5&rng020orderKey=1"><gsmsg:write key="cmn.draft" /></a>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>