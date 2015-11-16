<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhAdr020Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="addressbook" />(<gsmsg:write key="address.src.2" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_adr020">
<html:hidden name="mbhAdr020Form" property="adr010cmdMode" />
<html:hidden name="mbhAdr020Form" property="adr010SelectInd" />
<html:hidden name="mbhAdr020Form" property="adr010SearchInd" />
<html:hidden name="mbhAdr020Form" property="adr010page" />
<html:hidden name="mbhAdr020Form" property="adr010SearchTopFlg" />
<html:hidden name="mbhAdr020Form" property="adr010SearchKanaFlg" />
<html:hidden name="mbhAdr020Form" property="adr010selectLabel" />
<html:hidden name="mbhAdr020Form" property="adr020BackId" />
<html:hidden name="mbhAdr020Form" property="adr030editAcoSid" />

<b><%= emojiBook.toString() %><gsmsg:write key="addressbook" />-<gsmsg:write key="address.src.2" /></b>
<hr>

■<gsmsg:write key="cmn.name" />
<br>
<bean:write name="mbhAdr020Form" property="adr020unameSei" /> <bean:write name="mbhAdr020Form" property="adr020unameMei" />
<br>
<logic:notEmpty name="mbhAdr020Form" property="adr020companyName">
■<gsmsg:write key="cmn.company.name" />
<br>
<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr020AcoSid" />&adr030BackId=adr020&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />"><bean:write name="mbhAdr020Form" property="adr020companyName" /></a>
<br>
</logic:notEmpty>
<%--
<logic:notEmpty name="mbhAdr020Form" property="adr020companyBaseName">
■<gsmsg:write key="address.10" />
<br>
<bean:write name="mbhAdr020Form" property="adr020companyBaseName" />
<br>
</logic:notEmpty>
--%>
<logic:notEmpty name="mbhAdr020Form" property="adr020tel1">
■<gsmsg:write key="cmn.tel" />1
<br>
<a href=tel:<bean:write name="mbhAdr020Form" property="adr020tel1" />><bean:write name="mbhAdr020Form" property="adr020tel1" /></a>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhAdr020Form" property="adr020tel2">
■<gsmsg:write key="cmn.tel" />2
<br>
<a href=tel:<bean:write name="mbhAdr020Form" property="adr020tel2" />><bean:write name="mbhAdr020Form" property="adr020tel2" /></a>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhAdr020Form" property="adr020tel3">
■<gsmsg:write key="cmn.tel" />3
<br>
<a href=tel:<bean:write name="mbhAdr020Form" property="adr020tel3" />><bean:write name="mbhAdr020Form" property="adr020tel3" /></a>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhAdr020Form" property="adr020companyPost">
■<gsmsg:write key="cmn.address" />
<a href="http://maps.google.co.jp/maps?q=<bean:write name="mbhAdr020Form" property="adr020abaAddress1Url" />" /><gsmsg:write key="address.76" /></a>
<br>
<bean:write name="mbhAdr020Form" property="adr020address1" />
<br>
</logic:notEmpty>
<%--
<logic:notEmpty name="mbhAdr020Form" property="adr020companyURL">
■URL
<br>
<a href="<bean:write name="mbhAdr020Form" property="adr020companyURL" />"><bean:write name="mbhAdr020Form" property="adr020companyURL" /></a>
<br>
<br>
</logic:notEmpty>
--%>
<br>
<logic:notEmpty name="mbhAdr020Form" property="adr020mail1">
■<gsmsg:write key="cmn.mailaddress" />1
<br>
<logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
  <a href="../mobile/mh_adr040.do<%= jsessionId.toString() %>?adr040SelectValue=mail&adr020mail1=<bean:write name="mbhAdr020Form" property="adr020mail1" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr020Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr020Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr020Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr020Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr020Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr020Form" property="adr010selectLabel" />&adr020BackId=<bean:write name="mbhAdr020Form" property="adr020BackId" />&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr030editAcoSid" />&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />"><bean:write name="mbhAdr020Form" property="adr020mail1" /></a>
</logic:equal>
<logic:notEqual name="mbhAdr020Form" property="adr020webMailUse" value="0">
  <bean:write name="mbhAdr020Form" property="adr020mail1" />
</logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhAdr020Form" property="adr020mail2">
■<gsmsg:write key="cmn.mailaddress" />2
<br>
<logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
  <a href="../mobile/mh_adr040.do<%= jsessionId.toString() %>?adr040SelectValue=mail&adr020mail2=<bean:write name="mbhAdr020Form" property="adr020mail2" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr020Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr020Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr020Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr020Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr020Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr020Form" property="adr010selectLabel" />&adr020BackId=<bean:write name="mbhAdr020Form" property="adr020BackId" />&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr030editAcoSid" />&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />"><bean:write name="mbhAdr020Form" property="adr020mail2" /></a>
</logic:equal>
<logic:notEqual name="mbhAdr020Form" property="adr020webMailUse" value="0">
  <bean:write name="mbhAdr020Form" property="adr020mail2" />
</logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhAdr020Form" property="adr020mail3">
■<gsmsg:write key="cmn.mailaddress" />3
<br>
<logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
  <a href="../mobile/mh_adr040.do<%= jsessionId.toString() %>?adr040SelectValue=mail&adr020mail3=<bean:write name="mbhAdr020Form" property="adr020mail3" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr020Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr020Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr020Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr020Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr020Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr020Form" property="adr010selectLabel" />&adr020BackId=<bean:write name="mbhAdr020Form" property="adr020BackId" />&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr030editAcoSid" />&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />"><bean:write name="mbhAdr020Form" property="adr020mail3" /></a>
</logic:equal>
<logic:notEqual name="mbhAdr020Form" property="adr020webMailUse" value="0">
  <bean:write name="mbhAdr020Form" property="adr020mail3" />
</logic:notEqual>
<br>
</logic:notEmpty>

<br>
<div align="center">
<input name="adr020backButton" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>