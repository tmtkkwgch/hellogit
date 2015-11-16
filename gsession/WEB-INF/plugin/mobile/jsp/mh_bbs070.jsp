<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs070Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs090.1" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs070">
<input type="hidden" name="CMD" value="">
<html:hidden name="mbhBbs070Form" property="bbs010page1" />
<html:hidden name="mbhBbs070Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs070Form" property="bbs020page1" />
<html:hidden name="mbhBbs070Form" property="searchDspID" />
<html:hidden name="mbhBbs070Form" property="threadSid" />
<html:hidden name="mbhBbs070Form" property="bbs030page1" />
<html:hidden name="mbhBbs070Form" property="bbs030writeSid" />
<html:hidden name="mbhBbs070Form" property="bbs030orderKey" />

<logic:messagesPresent message="false">
<html:errors/>
</logic:messagesPresent>

<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.bbs090.1" /></b>

<hr>
■<gsmsg:write key="bbs.3" />
<br>
<bean:write name="mbhBbs070Form" property="bbs070forumName" />
<br>
■<gsmsg:write key="cmn.contributor" />
<br>
<html:select property="bbs070contributor">
  <html:optionsCollection name="mbhBbs070Form" property="bbs070contributorList" value="value" label="label" />
</html:select>
<br>
■<gsmsg:write key="cmn.title" />
<br>
<bean:write name="mbhBbs070Form" property="bbs070threTitle" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<textarea name="bbs070value" cols="16" rows="3"><bean:write name="mbhBbs070Form" property="bbs070value" /></textarea>
<br>
<br>

<div align="center">
<input name="bbs070ok" value="OK" type="submit">
<input name="bbs070back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>