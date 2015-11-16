<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhWml040knForm"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="wml.wml010.25" />(<gsmsg:write key="cmn.check" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_wml040kn">
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

<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="cmn.check" /></b>

<hr>

■<gsmsg:write key="cmn.from" />
<br>
<bean:write name="mbhWml040knForm" property="wml040To" />
<br>
<logic:notEmpty name="mbhWml040knForm" property="wml040Cc">
■CC
<br>
<bean:write name="mbhWml040knForm" property="wml040Cc" />
<br>
</logic:notEmpty>
<logic:notEmpty name="mbhWml040knForm" property="wml040Bcc">
■BCC
<br>
<bean:write name="mbhWml040knForm" property="wml040Bcc" />
<br>
</logic:notEmpty>
■<gsmsg:write key="cmn.subject" />
<br>
<bean:write name="mbhWml040knForm" property="wml040Subject" />
<br>
■<gsmsg:write key="cmn.body" />
<br>
<bean:write name="mbhWml040knForm" property="wml040knViewBody" filter="false" />

<div align="center">
  <logic:equal name="mbhWml040knForm" property="wml040mode" value="1">
  <br><input name="wml040knDraft" value="OK" type="submit"><input name="wml040knBack" value="<gsmsg:write key="cmn.back" />" type="submit">
  </logic:equal>
  <logic:notEqual name="mbhWml040knForm" property="wml040mode" value="1">
  <br><input name="wml040knSend" value="<gsmsg:write key="cmn.sent" />" type="submit"><input name="wml040knBack" value="<gsmsg:write key="cmn.back" />" type="submit">
  </logic:notEqual>
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>