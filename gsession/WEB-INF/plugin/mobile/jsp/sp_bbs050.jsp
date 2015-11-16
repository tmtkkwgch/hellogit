<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="mobile.11" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs050Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<html:form action="/mobile/mh_bbs050">
<html:hidden property="mobileType" value="1"/>
<html:hidden name="mbhBbs050Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs050Form" property="bbs040title" />
<html:hidden name="mbhBbs050Form" property="bbs040value" />
<html:hidden name="mbhBbs050Form" property="bbs050cmdMode" />
<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="mobile.11" /></b>

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