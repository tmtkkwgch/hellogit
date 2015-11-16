<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRng060Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="schedule.117" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>
<body>
<html:form action="/mobile/mh_rng060">

<b><%= emojiTokei.toString() %><gsmsg:write key="rng.62" /><br><gsmsg:write key="mobile.21" /></b>
<hr>

<%= emojiPen2.toString() %><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.group" />
<br>

<%-- 同時登録ユーザ追加　--%>

<%--

<logic:empty name="mbhRng060Form" property="rng050DispMod">
<logic:notEmpty name="mbhRng060Form" property="rng040GroupLavel">
<html:select name="mbhRng060Form" property="rng040GroupSid">
  <html:optionsCollection name="mbhRng060Form" property="rng040GroupLavel" value="value" label="label" />
</html:select>
</logic:notEmpty>
<input name="rng050Search" value="<gsmsg:write key="cmn.search" />" type="submit">
<br>
<logic:notEmpty name="mbhRng060Form" property="rng040BelongLavel">
<logic:iterate id="userList" name="mbhRng060Form" property="rng040BelongLavel" indexId="idx">
<bean:define id="uid" name="userList" property="usrSid" />
<html:radio name="mbhRng060Form" property="rng040AddedUsrId" value="<%= uid.toString() %>" /><bean:write name="userList" property="usiSei" /> <bean:write name="userList" property="usiMei" /><br>
</logic:iterate>
</logic:notEmpty>
<div align="center">
<br>
<input name="rng050add" value="<gsmsg:write key="cmn.add" />" type="submit">
</logic:empty>

--%>

<input name="rng050back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>