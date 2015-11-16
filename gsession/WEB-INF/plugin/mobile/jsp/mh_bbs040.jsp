<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs040Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs060.1" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs040">
<html:hidden name="mbhBbs040Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs040Form" property="bbs040limit" />
<html:hidden name="mbhBbs040Form" property="bbs040limitYear" />
<html:hidden name="mbhBbs040Form" property="bbs040limitMonth" />
<html:hidden name="mbhBbs040Form" property="bbs040limitDay" />

<logic:messagesPresent message="false">
<html:errors/><br>
</logic:messagesPresent>

<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.bbs060.1" /></b>

<hr>
■<gsmsg:write key="bbs.3" />

<br>
<bean:write name="mbhBbs040Form" property="bbs040forumName" />
<br>
■<gsmsg:write key="cmn.contributor" />
<br>
<html:select property="bbs040contributor">
  <html:optionsCollection name="mbhBbs040Form" property="bbs040contributorList" value="value" label="label" />
</html:select>
<br>
■<gsmsg:write key="cmn.title" />
<br>
<html:text name="mbhBbs040Form" property="bbs040title" size="27" maxlength="70" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<textarea name="bbs040value" cols="16" rows="3"><bean:write name="mbhBbs040Form" property="bbs040value" /></textarea>
<br>
■<gsmsg:write key="bbs.12" />
<logic:equal name="mbhBbs040Form" property="bbs040limit" value="0">
  <input name="bbs040limitbtn" value="<gsmsg:write key="bbs.bbs070.4" />" type="submit">
  <br>
    <gsmsg:write key="main.man200.9" />
</logic:equal>

<logic:notEqual name="mbhBbs040Form" property="bbs040limit" value="0">
<input name="bbs040noLimit" value="<gsmsg:write key="mobile.16" />" type="submit">
<br>
<bean:define id="yr" name="mbhBbs040Form" property="bbs040limitYear" type="java.lang.Integer" />
<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr) %>" /><bean:write name="mbhBbs040Form" property="bbs040limitMonth" /><gsmsg:write key="cmn.month" /><bean:write name="mbhBbs040Form" property="bbs040limitDay" /><gsmsg:write key="cmn.day" /><input name="bbs040change" value="<gsmsg:write key="cmn.change" />" type="submit">
</logic:notEqual>

<br>
<br>

<div align="center">
  <input name="bbs040ok" value="OK" type="submit">
  <input name="bbs040back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>