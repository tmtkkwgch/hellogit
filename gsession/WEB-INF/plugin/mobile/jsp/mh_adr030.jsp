<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhAdr030Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="addressbook" /><gsmsg:write key="address.118" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_adr030">
<html:hidden property="adr030editAcoSid" />
<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010EditAdrSid" />
<html:hidden property="adr020BackId" />
<html:hidden property="adr030BackId" />

<b><%= emojiBook.toString() %><gsmsg:write key="addressbook" /> - <gsmsg:write key="address.118" /></b>
<hr>

■<gsmsg:write key="cmn.company.name" />
<br>
<bean:write name="mbhAdr030Form" property="adr030coName" />
<br>
<logic:notEmpty name="mbhAdr030Form" property="adr030url">
■URL
<a href="<bean:write name="mbhAdr030Form" property="adr030url" />"><bean:write name="mbhAdr030Form" property="adr030url" /></a>
<br>
</logic:notEmpty>

<logic:notEmpty name="mbhAdr030Form" property="adr030Label">
■<gsmsg:write key="address.10" />
<br>
<html:select property="adr030selectLabel">
<html:optionsCollection name="mbhAdr030Form" property="adr030Label" value="value" label="label" />
</html:select>

<input name="searchButton" value="<gsmsg:write key="cmn.search" />" type="submit">
</logic:notEmpty>

<logic:equal name="mbhAdr030Form" property="adr030SearchFlg" value="1">
<logic:notEmpty name="mbhAdr030Form" property="adr030BaseMdl">
<bean:define id="abaData" name="mbhAdr030Form" property="adr030BaseMdl" />
<br>
<logic:notEmpty name="abaData" property="adr110abaPostno1">
■<gsmsg:write key="cmn.postalcode" />
<br>
<bean:write name="abaData" property="adr110abaPostno1" />-<bean:write name="abaData" property="adr110abaPostno2" />
<br>
</logic:notEmpty>
<logic:notEmpty name="abaData" property="adr110abaTdfkName">
■<gsmsg:write key="cmn.prefectures" />
<br>
<bean:write name="abaData" property="adr110abaTdfkName" />
<br>
</logic:notEmpty>
<logic:notEmpty name="abaData" property="adr110abaAddress1">
■<gsmsg:write key="cmn.address" />
<a href="http://maps.google.co.jp/maps?q=<bean:write name="mbhAdr030Form" property="adr030abaAddress1Url" />" /><gsmsg:write key="address.76" /></a>
<br>
<bean:write name="abaData" property="adr110abaAddress1" />
</logic:notEmpty>

</logic:notEmpty>
</logic:equal>

<hr>
<div align="center">
<gsmsg:write key="cmn.userlist" />
</div>
<hr>
<logic:messagesPresent message="false">
<html:errors />
</logic:messagesPresent>
<logic:notEmpty name="mbhAdr030Form" property="adr030AdrInfList">
<logic:iterate id="adrInfo" name="mbhAdr030Form" property="adr030AdrInfList" indexId="idx">
<logic:notEqual name="mbhAdr030Form" property="adr030SearchFlg" value="1">
<a href="../mobile/mh_adr020.do<%= jsessionId.toString() %>?adr010EditAdrSid=<bean:write name="adrInfo" property="adrSid" />&adr020BackId=adr030&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />"><bean:write name="adrInfo" property="userName" /></a> <bean:write name="adrInfo" property="companyBaseName" />
</logic:notEqual>
<logic:equal name="mbhAdr030Form" property="adr030SearchFlg" value="1">
<a href="../mobile/mh_adr020.do<%= jsessionId.toString() %>?adr010EditAdrSid=<bean:write name="adrInfo" property="adrSid" />&adr020BackId=adr030&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />"><bean:write name="adrInfo" property="userName" /></a>
</logic:equal>
<br>
</logic:iterate>
</logic:notEmpty>
<br>
<br>
<logic:notEqual name="mbhAdr030Form" property="adr030MaxPage" value="0">
<div align="center">
<bean:define id="maxPage" name="mbhAdr030Form" property="adr030MaxPage" />
<logic:equal name="mbhAdr030Form" property="adr030page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhAdr030Form" property="adr030page" />/<bean:write name="mbhAdr030Form" property="adr030MaxPage" />)<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?CMD=nextPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />"><gsmsg:write key="cmn.next" /></a>
</logic:equal>
<logic:equal name="mbhAdr030Form" property="adr030page" value="<%= String.valueOf(maxPage) %>" >
<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?CMD=prevPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhAdr030Form" property="adr030page" />/<bean:write name="mbhAdr030Form" property="adr030MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhAdr030Form" property="adr030page" value="1">
<logic:notEqual name="mbhAdr030Form" property="adr030page" value="<%= String.valueOf(maxPage) %>" >
  <a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?CMD=prevPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhAdr030Form" property="adr030page" />/<bean:write name="mbhAdr030Form" property="adr030MaxPage" />)<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?CMD=nextPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />"><gsmsg:write key="cmn.next" /></a>
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEqual>
<div align="center">
<br>
<input name="adr030backButton" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>