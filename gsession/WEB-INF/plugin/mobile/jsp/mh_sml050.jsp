<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSml050Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />]<gsmsg:write key="cmn.shortmail" /><gsmsg:write key="sml.sml020.05" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>
<body>
<html:form method="post" action="/mobile/mh_sml050">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />
<html:hidden property="sml040Title" />
<html:hidden property="sml040Mark" />
<html:hidden property="sml040Body" />
<html:hidden property="sml040ProcMode" />
<html:hidden property="sml040InitFlg" />
<html:hidden property="sml050cmdMode"  />

<logic:notEmpty name="mbhSml050Form" property="sml040userSid">
<logic:iterate id="usid" name="mbhSml050Form" property="sml040userSid">
  <input type="hidden" name="sml040userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml050Form" property="sml040userSidCc">
<logic:iterate id="usidcc" name="mbhSml050Form" property="sml040userSidCc">
  <input type="hidden" name="sml040userSidCc" value="<bean:write name="usidcc" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml050Form" property="sml040userSidBcc">
<logic:iterate id="usidbcc" name="mbhSml050Form" property="sml040userSidBcc">
  <input type="hidden" name="sml040userSidBcc" value="<bean:write name="usidbcc" />">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMail.toString() %><gsmsg:write key="cmn.shortmail" /><br>
<logic:equal name="mbhSml050Form" property="sml050cmdMode" value="0"><gsmsg:write key="sml.sml020.05" /></logic:equal>
<logic:equal name="mbhSml050Form" property="sml050cmdMode" value="1"><gsmsg:write key="sml.sml020.05" /></logic:equal>
<logic:equal name="mbhSml050Form" property="sml050cmdMode" value="2"><gsmsg:write key="sml.sml020.05" /></logic:equal>
</b>
<hr>

<%= emojiPen2.toString() %><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.group" />
<br>
<logic:notEmpty name="mbhSml050Form" property="sml050GroupList">
<html:select name="mbhSml050Form" property="sml050GroupSid" styleClass="select01">
  <html:optionsCollection name="mbhSml050Form" property="sml050GroupList" value="value" label="label" />
</html:select>
</logic:notEmpty>

<input name="sml050Search" value="<gsmsg:write key="cmn.search" />" type="submit">
<br>

<logic:notEqual name="mbhSml050Form" property="sml050GroupSid" value="sac">
  <logic:notEmpty name="mbhSml050Form" property="sml050UserList">
    <logic:iterate id="userList" name="mbhSml050Form" property="sml050UserList" indexId="idx">
     <input name="<%= jp.groupsession.v3.mbh.sml050.MbhSml050Form.PARAM_SELECTADR %><bean:write name="userList" property="usrSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="userList" property="usiSei" />&nbsp;&nbsp;<bean:write name="userList" property="usiMei" /><br>
    </logic:iterate>
  </logic:notEmpty>
</logic:notEqual>

<logic:equal name="mbhSml050Form" property="sml050GroupSid" value="sac">
  <logic:notEmpty name="mbhSml050Form" property="sml050UserList">
    <logic:iterate id="userList" name="mbhSml050Form" property="sml050UserList" indexId="idx">
     <input name="<%= jp.groupsession.v3.mbh.sml050.MbhSml050Form.PARAM_SELECTADR %>sac<bean:write name="userList" property="sacSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="userList" property="sacName" /><br>
    </logic:iterate>
  </logic:notEmpty>
</logic:equal>

<div align="center">
  <br><input name="sml050Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>