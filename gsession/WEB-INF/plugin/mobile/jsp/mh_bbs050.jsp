<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs050Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="mobile.9" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs050">
<html:hidden name="mbhBbs050Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs050Form" property="bbs040title" />
<html:hidden name="mbhBbs050Form" property="bbs040value" />
<html:hidden name="mbhBbs050Form" property="bbs050cmdMode" />
<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="mobile.9" /></b>

<hr>

<html:select name="mbhBbs050Form" property="bbs040limitYear">
  <html:optionsCollection name="mbhBbs050Form" property="bbs040yearList" value="value" label="label" />
</html:select>
<html:select name="mbhBbs050Form" property="bbs040limitMonth">
  <html:optionsCollection name="mbhBbs050Form" property="bbs040monthList" value="value" label="label" />
</html:select>
<html:select name="mbhBbs050Form" property="bbs040limitDay">
  <html:optionsCollection name="mbhBbs050Form" property="bbs040dayList" value="value" label="label" />
</html:select>

<br>
<br>

<div align="center">
<input name="bbs050add" value="<gsmsg:write key="cmn.setting" />" type="submit">
<logic:equal name="mbhBbs050Form" property="bbs050cmdMode" value="0">
<input name="bbs050back" value="<gsmsg:write key="cmn.back" />" type="submit">
</logic:equal>
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>