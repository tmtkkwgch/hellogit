<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhUsr020Form"; %>
<% String close = String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE); %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shain.info" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_usr020">
<html:hidden property="usr010cmdMode" />
<html:hidden property="usr010pageNum1" />
<html:hidden property="selectgsid" />
<html:hidden property="usr010selectedLabel" />
<html:hidden property="usr010SelectInd" />
<html:hidden property="usr010SearchInd" />
<html:hidden property="usr010SearchTopFlg" />
<html:hidden property="usr010SearchKanaFlg" />

<b><%= emojiBook.toString() %><gsmsg:write key="cmn.shain.info" /></b>
<hr>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiSyainNo">
■<gsmsg:write key="cmn.employee.staff.number" />
<br>
<bean:write name="mbhUsr020Form" property="usr020usiSyainNo" />
<br>
<br>
</logic:notEmpty>
■<gsmsg:write key="cmn.name" />
<br>
<bean:write name="mbhUsr020Form" property="usr020usiSei" />&nbsp;&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMei" />
<br>
■<gsmsg:write key="cmn.name" />（<gsmsg:write key="cmn.kana" />）
<br>
<bean:write name="mbhUsr020Form" property="usr020usiSeiKn" />&nbsp;&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMeiKn" />
<br>
<br>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiSyozoku">
■<gsmsg:write key="cmn.affiliation" />
<br>
<bean:write name="mbhUsr020Form" property="usr020usiSyozoku" />
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiYakusyoku">
■<gsmsg:write key="cmn.post" />
<br>
<bean:write name="mbhUsr020Form" property="usr020usiYakusyoku" />
<br>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiBdate">
■<gsmsg:write key="cmn.birthday" />
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiBdateKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiBdateKf" value="<%= close %>"><bean:write name="mbhUsr020Form" property="usr020usiBdate" />
<logic:notEqual name="mbhUsr020Form" property="usr020usiAge" value="-1">
(<bean:write name="mbhUsr020Form" property="usr020usiAge" />歳)
</logic:notEqual>
</logic:notEqual>
<br>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail1">
■<gsmsg:write key="cmn.mailaddress" />1
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiMail1Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiMail1Kf" value="<%= close %>">
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail1">
<logic:equal name="mbhUsr020Form" property="usr020webMailUse" value="0">
  <a href="../mobile/mh_usr030.do<%= jsessionId.toString() %>?usr020usid=<bean:write name="mbhUsr020Form" property="usr020usid" />&usr010cmdMode=<bean:write name="mbhUsr020Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr020Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr020Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr020Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr020Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr020Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr020Form" property="usr010SearchKanaFlg" />&usr030SelectValue=mail&usr020usiMail1=<bean:write name="mbhUsr020Form" property="usr020usiMail1" />"><bean:write name="mbhUsr020Form" property="usr020usiMail1" /></a>
</logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020webMailUse" value="0">
  <bean:write name="mbhUsr020Form" property="usr020usiMail1" />
</logic:notEqual>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMailCmt1"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMailCmt1" /></logic:notEmpty>
</logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail2">
■<gsmsg:write key="cmn.mailaddress" />2
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiMail2Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiMail2Kf" value="<%= close %>">
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiMail2">
  <logic:equal name="mbhUsr020Form" property="usr020webMailUse" value="0">
    <a href="../mobile/mh_usr030.do<%= jsessionId.toString() %>?usr020usid=<bean:write name="mbhUsr020Form" property="usr020usid" />&usr010cmdMode=<bean:write name="mbhUsr020Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr020Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr020Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr020Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr020Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr020Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr020Form" property="usr010SearchKanaFlg" />&usr030SelectValue=mail&usr020usiMail1=<bean:write name="mbhUsr020Form" property="usr020usiMail2" />"><bean:write name="mbhUsr020Form" property="usr020usiMail2" /></a>
  </logic:equal>
  <logic:notEqual name="mbhUsr020Form" property="usr020webMailUse" value="0">
    <bean:write name="mbhUsr020Form" property="usr020usiMail2" />
  </logic:notEqual>
  </logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiMailCmt2"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMailCmt2" /></logic:notEmpty>
</logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail3">
■<gsmsg:write key="cmn.mailaddress" />3
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiMail3Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiMail3Kf" value="<%= close %>">
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiMail3">
  <logic:equal name="mbhUsr020Form" property="usr020webMailUse" value="0">
    <a href="../mobile/mh_usr030.do<%= jsessionId.toString() %>?usr020usid=<bean:write name="mbhUsr020Form" property="usr020usid" />&usr010cmdMode=<bean:write name="mbhUsr020Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr020Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr020Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr020Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr020Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr020Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr020Form" property="usr010SearchKanaFlg" />&usr030SelectValue=mail&usr020usiMail1=<bean:write name="mbhUsr020Form" property="usr020usiMail3" />"><bean:write name="mbhUsr020Form" property="usr020usiMail3" /></a>
  </logic:equal>
  <logic:notEqual name="mbhUsr020Form" property="usr020webMailUse" value="0">
    <bean:write name="mbhUsr020Form" property="usr020usiMail3" />
  </logic:notEqual>
  </logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiMailCmt3"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMailCmt3" /></logic:notEmpty>
</logic:notEqual>
<br>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiZip">
■<gsmsg:write key="cmn.postalcode" />
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiZipKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiZipKf" value="<%= close %>"><bean:write name="mbhUsr020Form" property="usr020usiZip" /></logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020TdfkName">
■<gsmsg:write key="cmn.prefectures" />
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiTdfkKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiTdfkKf" value="<%= close %>"><bean:write name="mbhUsr020Form" property="usr020TdfkName" /></logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiAddr1">
■<gsmsg:write key="cmn.address" />
<logic:equal name="mbhUsr020Form" property="usr020usiAddr1Kf" value="<%= close %>"><br><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiAddr1Kf" value="<%= close %>">
<a href="http://maps.google.co.jp/maps?q=<bean:write name="mbhUsr020Form" property="usr020usiAddr1Url" />" /><gsmsg:write key="address.76" /></a><br>
<bean:write name="mbhUsr020Form" property="usr020usiAddr1" />
</logic:notEqual>
<br>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiTel1">
■<gsmsg:write key="cmn.tel" />1
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiTel1Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiTel1Kf" value="<%= close %>">
  <a href=tel:<bean:write name="mbhUsr020Form" property="usr020usiTel1" />><bean:write name="mbhUsr020Form" property="usr020usiTel1" /></a><br>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelNai1"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelNai1" />&nbsp;&nbsp;<br></logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelCmt1"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelCmt1" /></logic:notEmpty>
</logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiTel2">
■<gsmsg:write key="cmn.tel" />2
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiTel2Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiTel2Kf" value="<%= close %>">
  <a href=tel:<bean:write name="mbhUsr020Form" property="usr020usiTel2" />><bean:write name="mbhUsr020Form" property="usr020usiTel2" /></a><br>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelNai2"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelNai2" />&nbsp;&nbsp;<br></logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelCmt2"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelCmt2" /></logic:notEmpty>
</logic:notEqual>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiTel3">
■<gsmsg:write key="cmn.tel" />3
<br>
<logic:equal name="mbhUsr020Form" property="usr020usiTel3Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
<logic:notEqual name="mbhUsr020Form" property="usr020usiTel3Kf" value="<%= close %>">
  <a href=tel:<bean:write name="mbhUsr020Form" property="usr020usiTel3" />><bean:write name="mbhUsr020Form" property="usr020usiTel3" /></a><br>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelNai3"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelNai3" />&nbsp;&nbsp;<br></logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelCmt3"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelCmt3" /></logic:notEmpty>
</logic:notEqual>
<br>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="labelList">
■<gsmsg:write key="cmn.label" />
<br>
<logic:iterate id="labelData" name="mbhUsr020Form" property="labelList" indexId="idx">
<bean:write name="labelData" property="labName" />
<br>
</logic:iterate>
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020grpNmlist">
■<gsmsg:write key="cmn.affiliation" />
<br>
<logic:iterate id="group" name="mbhUsr020Form" property="usr020grpNmlist" indexId="idx" scope="request">
  <bean:write name="group" property="groupName" />
  <logic:equal name="group" property="grpKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>"><gsmsg:write key="cmn.admin" /></logic:equal>
  <logic:notEqual name="group" property="grpKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">&nbsp;</logic:notEqual>
  <br>
</logic:iterate>
<br>
</logic:notEmpty>

<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>

<div align="center">
<input name="usr020back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>