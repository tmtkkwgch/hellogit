<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhNtp050Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="ntp.1" /> ユーザ変更</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>
<body>
<html:form action="/mobile/mh_ntp050">


<html:hidden property="ntp050Cmd" />
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="ntp010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp050DispMod" />
<html:hidden property="ntp050DispId" />


<logic:equal name="mbhNtp050Form" property="ntp050DispMod" value="usrChange">
<b><%= emojiTokei.toString() %><gsmsg:write key="ntp.1" /><br><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.change" /></b>
</logic:equal>
<logic:empty name="mbhNtp050Form" property="ntp050DispMod">
<b><%= emojiTokei.toString() %><gsmsg:write key="ntp.1" /><br><gsmsg:write key="schedule.117" /></b>
</logic:empty>
<hr>
<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>
<%= emojiPen2.toString() %><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.group" />
<br>



<%-- ユーザ変更　--%>
<logic:equal name="mbhNtp050Form" property="ntp050DispMod" value="usrChange">
<logic:notEmpty name="mbhNtp050Form" property="ntp050GroupList">
<html:select name="mbhNtp050Form" property="ntp050GroupSid" styleClass="select01">
  <html:optionsCollection name="mbhNtp050Form" property="ntp050GroupList" value="value" label="label" />
</html:select>
</logic:notEmpty>

<input name="ntp050Search" value="<gsmsg:write key="cmn.search" />" type="submit">
<br>
<logic:notEmpty name="mbhNtp050Form" property="ntp050UserList">
<logic:iterate id="userList" name="mbhNtp050Form" property="ntp050UserList" indexId="idx">
<a href="./<bean:write name="mbhNtp050Form" property="ntp050DispId" />.do<%= jsessionId.toString() %>?CMD=addUser&ntp010SelectUsrSid=<bean:write name="userList" property="value" />&ntp010DspDate=<bean:write name="mbhNtp050Form" property="ntp010DspDate" />&ntp010DspGpSid=<bean:write name="mbhNtp050Form" property="ntp050GroupSid" />&changeDateFlg=<bean:write name="mbhNtp050Form" property="changeDateFlg" />"><bean:write name="userList" property="label" /></a><br>
</logic:iterate>
</logic:notEmpty>
</logic:equal>
<input name="ntp050back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>