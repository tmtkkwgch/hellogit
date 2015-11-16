<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir010Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form method="post" action="/mobile/mh_cir010">


<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /></b>

<br>

<logic:notEmpty name="mbhCir010Form" property="cir010AccountList">
<html:select name="mbhCir010Form" property="cirViewAccount" styleClass="select01">
  <logic:iterate id="accountMdl" name="mbhCir010Form" property="cir010AccountList">
    <bean:define id="accoutVal" name="accountMdl" property="accountSid" type="java.lang.Integer" />
    <html:option value="<%= String.valueOf(accoutVal) %>"><bean:write name="accountMdl" property="accountName" /></html:option>
  </logic:iterate>
</html:select>
</logic:notEmpty>
<input name="cir010changeAccount" value="<gsmsg:write key="cmn.select" />" type="submit">

<hr>
●<a href="./mh_cir050.do<%= jsessionId.toString() %>?cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><gsmsg:write key="cmn.create.new" /></a>
<br>
<logic:greaterThan name="mbhCir010Form" property="mikakkuCount" value="0"><b></logic:greaterThan>
■<a href="./mh_cir020.do<%= jsessionId.toString() %>?cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><gsmsg:write key="cmn.receive" />
<logic:greaterThan name="mbhCir010Form" property="mikakkuCount" value="0">(<bean:write name="mbhCir010Form" property="mikakkuCount" />)</logic:greaterThan></a>
<logic:greaterThan name="mbhCir010Form" property="mikakkuCount" value="0"></b></logic:greaterThan>
<br>
■<a href="./mh_cir020.do<%= jsessionId.toString() %>?cir020cmdMode=1&cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><gsmsg:write key="cmn.sent" /></a>
<br>
■<a href="./mh_cir020.do<%= jsessionId.toString() %>?cir020cmdMode=3&cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><gsmsg:write key="cmn.trash" /></a>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>