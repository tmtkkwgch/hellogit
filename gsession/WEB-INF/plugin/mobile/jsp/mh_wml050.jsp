<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhWml050Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />]<gsmsg:write key="wml.wml010.25" /><gsmsg:write key="cmn.from" /><gsmsg:write key="cmn.search" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>
<body>
<html:form action="/mobile/mh_wml050">
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wmlLabel" />
<html:hidden property="wmlMailNum" />
<html:hidden property="wmlSendMode" />
<html:hidden property="wml020SearchFlg" />
<html:hidden property="wml020selectPage" />
<html:hidden property="wml020MaxPage" />
<html:hidden property="wml020svSearchFrom" />
<html:hidden property="wml020svSearchTo" />
<html:hidden property="wml020svSearchDateType" />
<html:hidden property="wml020svSearchDateYearFr" />
<html:hidden property="wml020svSearchDateMonthFr" />
<html:hidden property="wml020svSearchDateDayFr" />
<html:hidden property="wml020svSearchDateYearTo" />
<html:hidden property="wml020svSearchDateMonthTo" />
<html:hidden property="wml020svSearchDateDayTo" />
<html:hidden property="wml020svKeyword" />
<html:hidden property="wml020svSearchKeyword" />
<html:hidden property="wml020svSearchKeywordKbn" />
<html:hidden property="wml020svSearchReadKbn" />
<html:hidden property="wml020svSearchTempFile" />
<html:hidden property="wml020DetaileSrhFlg" />
<html:hidden property="wml040initFlg" />
<html:hidden property="wml040Account" />
<html:hidden property="wml040svAccount" />
<html:hidden property="wml040Subject" />
<html:hidden property="wml040To" />
<html:hidden property="wml040Cc" />
<html:hidden property="wml040Bcc" />
<html:hidden property="wml040Body" />
<html:hidden property="wml040mode" />
<html:hidden property="wml040adrMode" />

<bean:define id="mw050Form" name="mbhWml050Form" type="jp.groupsession.v3.mbh.wml050.MbhWml050Form" />

<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="sml.sml020.05" /></b>

<hr>

<%= emojiPen2.toString() %><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.group" />
<br>
<html:select property="wml050Group">
  <html:optionsCollection name="mbhWml050Form" property="grpCombo" value="value" label="label" />
</html:select>
<input name="wml050ChgGrp" value="<gsmsg:write key="cmn.change" />" type="submit">

<logic:notEmpty name="mbhWml050Form" property="userList">
<logic:iterate id="userData" name="mbhWml050Form" property="userList">
<br><input name="<%= jp.groupsession.v3.mbh.wml050.MbhWml050Form.PARAM_SELECTADR %><bean:write name="userData" property="userSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><%= emojiRsv.toString() %><bean:write name="userData" property="userName" />
</logic:iterate>
</logic:notEmpty>

<div align="center">
  <br><input name="wml050Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>