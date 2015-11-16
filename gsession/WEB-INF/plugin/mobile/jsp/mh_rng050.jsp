<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRng050Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="mobile.21" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
</head>

<body>

<html:form action="/mobile/mh_rng050">

<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rng040Title" />
<html:hidden property="rng040requestUser" />
<html:hidden property="rng040Content" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<html:hidden property="rng020pageTop" />

<logic:notEmpty name="mbhRng050Form" property="rng040apprUser">
<logic:iterate id="apprUser" name="mbhRng050Form" property="rng040apprUser">
  <input type="hidden" name="rng040apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="mbhRng050Form" property="rng040confirmUser">
<logic:iterate id="confirmUser" name="mbhRng050Form" property="rng040confirmUser">
  <input type="hidden" name="rng040confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMail.toString() %><gsmsg:write key="rng.62" /><br><gsmsg:write key="mobile.21" /></b>

<hr>

■<gsmsg:write key="rng.24" /><gsmsg:write key="cmn.template" />
<br>
<html:select property="rng040rctSid" styleClass="select01">
  <html:optionsCollection name="mbhRng050Form" property="rng040rctList" value="value" label="label" />
</html:select>
<br>
<input name="rng050Template" value="<gsmsg:write key="rng.rng020.02" />" type="submit">
<br>
<br>
■<gsmsg:write key="cmn.userlist" />
<br>
<html:select property="rng040group">
  <html:optionsCollection name="mbhRng050Form" property="rng040groupList" value="value" label="label" />
</html:select>
<input name="rng050GrpChang" value="<gsmsg:write key="cmn.change" />" type="submit">
<br>
<logic:notEmpty name="mbhRng050Form" property="rng040userList">
<logic:iterate id="userList" name="mbhRng050Form" property="rng040userList" indexId="idx">
<bean:define id="uid" name="userList" property="value" type="java.lang.String"/>
<html:radio name="mbhRng050Form" property="rng040AddedUsrId" value="<%= uid.toString() %>" /><bean:write name="userList" property="label" /><br>
</logic:iterate>
</logic:notEmpty>


<div align="center">
  <input name="rng050Keiro" value="<gsmsg:write key="rng.rng020.02" />" type="submit">
  <input name="rng050Kakunin" value="<gsmsg:write key="mobile.23" />" type="submit">
  <br>
  <br>
  <input name="rng050Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>