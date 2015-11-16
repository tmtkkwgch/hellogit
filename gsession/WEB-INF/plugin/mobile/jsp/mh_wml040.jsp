<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhWml040Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="wml.wml010.25" />(<gsmsg:write key="cmn.create.new" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_wml040">
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
<html:hidden property="wml040svAccount" />

<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="cmn.create.new" /></b>

<hr>
●<gsmsg:write key="wml.102" />
<br>
<html:select property="wml040Account">
  <html:optionsCollection name="mbhWml040Form" property="accountCombo" value="value" label="label" />
</html:select>
<gsmsg:define id="cmnChange" msgkey="cmn.change" />
<html:submit property="submit" value="<%= cmnChange %>" />
<hr>

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>

■<gsmsg:write key="cmn.from" />
<logic:equal name="mbhWml040Form" property="addressUseOk" value="0">
  <input name="wml040selectAdTo" value="<gsmsg:write key="addressbook" />" type="submit">
</logic:equal>
<logic:equal name="mbhWml040Form" property="userUseOk" value="0">
  <input name="wml040selectTo" value="<gsmsg:write key="cmn.shain.info" />" type="submit">
</logic:equal>
<br><html:text name="mbhWml040Form" property="wml040To" size="27" />
<br>
■CC
<logic:equal name="mbhWml040Form" property="addressUseOk" value="0">
  <input name="wml040selectAdCc" value="<gsmsg:write key="addressbook" />" type="submit">
</logic:equal>
<logic:equal name="mbhWml040Form" property="userUseOk" value="0">
  <input name="wml040selectCc" value="<gsmsg:write key="cmn.shain.info" />" type="submit">
</logic:equal>
<br><html:text name="mbhWml040Form" property="wml040Cc" size="27" />
<br>
■BCC
<logic:equal name="mbhWml040Form" property="addressUseOk" value="0">
  <input name="wml040selectAdBcc" value="<gsmsg:write key="addressbook" />" type="submit">
</logic:equal>
<logic:equal name="mbhWml040Form" property="userUseOk" value="0">
  <input name="wml040selectBcc" value="<gsmsg:write key="cmn.shain.info" />" type="submit">
</logic:equal>
<br><html:text name="mbhWml040Form" property="wml040Bcc" size="27" />
<br>
■<gsmsg:write key="cmn.subject" />
<br>
<html:text name="mbhWml040Form" property="wml040Subject" size="27" />
<br>
■<gsmsg:write key="cmn.body" />
<br><html:textarea name="mbhWml040Form" property="wml040Body" cols="20" rows="4"/>

<div align="center">
  <br><input name="wml040Send" value="<gsmsg:write key="cmn.sent" />" type="submit"><input name="wml040Draft" value="<gsmsg:write key="cmn.save.draft" />" type="submit"><input name="wml040Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>