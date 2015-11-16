<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhWml030Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="wml.wml010.25" />(<gsmsg:write key="sml.sml030.08" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_wml030">
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wmlLabel" />
<html:hidden property="wmlMailNum" />
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

<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="sml.sml030.08" /></b>

<hr>

<logic:equal name="mbhWml030Form" property="forward" value="true">
  <img alt="Fw" src="../mobile/images/img_forward.gif">
</logic:equal>

<logic:equal name="mbhWml030Form" property="reply" value="true">
  <img alt="Re" src="../mobile/images/img_henshin.gif">
</logic:equal>

<bean:write name="mbhWml030Form" property="wml030Date" />
<br>
■<gsmsg:write key="wml.from" />：<bean:write name="mbhWml030Form" property="wml030From" />
<br>
■<gsmsg:write key="cmn.subject" />：<bean:write name="mbhWml030Form" property="wml030Subject" />
<br>
■<gsmsg:write key="cmn.from" />：<bean:write name="mbhWml030Form" property="wml030To" />

<logic:notEmpty name="mbhWml030Form" property="wml030Cc">
<br>
■CC：<bean:write name="mbhWml030Form" property="wml030Cc" />
</logic:notEmpty>

<logic:notEmpty name="mbhWml030Form" property="wml030Bcc">
<br>
■BCC：<bean:write name="mbhWml030Form" property="wml030Bcc" />
</logic:notEmpty>
<logic:notEmpty name="mbhWml030Form" property="wml030TempFileList">
<br>
■<gsmsg:write key="cmn.attached" />
<br>
<logic:iterate id="tempMdl" name="mbhWml030Form" property="wml030TempFileList" indexId="idx">
<bean:write name="tempMdl" property="fileName" />
<br>
</logic:iterate>
</logic:notEmpty>
<br>
■<gsmsg:write key="cmn.content" />
<br>
<span style="word-break:break-all; word-wrap:break-word;">
<bean:write name="mbhWml030Form" property="wml030Body" filter="false" />
</span>

<br>
<gsmsg:define id="cmnStrage" msgkey="cmn.strage" />
<div align="center">
  <input name="wml030Reply" value="<gsmsg:write key="cmn.reply" />" type="submit">
  <input name="wml030ReplyAll" value="<gsmsg:write key="sml.67" />" type="submit">
  <input name="wml030Forwarding" value="<gsmsg:write key="cmn.forward" />" type="submit">
  <input name="wml030DustMail" value="<gsmsg:write key="cmn.delete" />" type="submit"/>
  <logic:notEqual name="mbhWml030Form" property="wml020Title" value="<%= cmnStrage %>">
    <input name="wml030KeepMail" value="<gsmsg:write key="cmn.strage" />" type="submit" />
  </logic:notEqual>
  <input name="wml030Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>