<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSml010Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shortmail" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form method="post" action="/mobile/mh_sml010">
<html:hidden property="smlViewAccountName" />
<b><%= emojiMail.toString() %><gsmsg:write key="cmn.shortmail" /></b>

<br>

<logic:notEmpty name="mbhSml010Form" property="sml010AccountList">
<html:select name="mbhSml010Form" property="smlViewAccount" styleClass="select01">
  <logic:iterate id="accountMdl" name="mbhSml010Form" property="sml010AccountList">
    <bean:define id="accoutVal" name="accountMdl" property="accountSid" type="java.lang.Integer" />
    <html:option value="<%= String.valueOf(accoutVal) %>"><bean:write name="accountMdl" property="accountName" /></html:option>
  </logic:iterate>
</html:select>
</logic:notEmpty>
<input name="sml010changeAccount" value="<gsmsg:write key="cmn.select" />" type="submit">


<hr>
●<a href="./mh_sml040.do<%= jsessionId.toString() %>?smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />"><gsmsg:write key="cmn.create.new" /></a>
<br>
<logic:greaterThan name="mbhSml010Form" property="sml010MidokuCnt" value="0"><b></logic:greaterThan>
■<a href="./mh_sml020.do<%= jsessionId.toString() %>?sml010ProcMode=0&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />"><gsmsg:write key="cmn.receive" />
<logic:greaterThan name="mbhSml010Form" property="sml010MidokuCnt" value="0">(<bean:write name="mbhSml010Form" property="sml010MidokuCnt" />)</logic:greaterThan></a>
<logic:greaterThan name="mbhSml010Form" property="sml010MidokuCnt" value="0"></b></logic:greaterThan>
<br>
■<a href="./mh_sml020.do<%= jsessionId.toString() %>?sml010ProcMode=1&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />"><gsmsg:write key="cmn.sent" /></a>
<br>
■<a href="./mh_sml020.do<%= jsessionId.toString() %>?sml010ProcMode=2&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />"><gsmsg:write key="cmn.draft" />
<logic:greaterThan name="mbhSml010Form" property="sml010SokoCnt" value="0">(<bean:write name="mbhSml010Form" property="sml010SokoCnt" />)</logic:greaterThan></a>
<br>
■<a href="./mh_sml020.do<%= jsessionId.toString() %>?sml010ProcMode=4&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />"><gsmsg:write key="cmn.trash" />
<logic:greaterThan name="mbhSml010Form" property="sml010GomiMidokuCnt" value="0">(<bean:write name="mbhSml010Form" property="sml010GomiMidokuCnt" />)</logic:greaterThan></a>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>