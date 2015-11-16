<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs060Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs070kn.1" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs060">

<input type="hidden" name="CMD" value="">
<html:hidden name="mbhBbs060Form" property="s_key" />
<html:hidden name="mbhBbs060Form" property="bbs010page1" />
<html:hidden name="mbhBbs060Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs060Form" property="searchDspID" />
<html:hidden name="mbhBbs060Form" property="bbs080page1" />
<html:hidden name="mbhBbs060Form" property="bbs080writeSid" />
<html:hidden name="mbhBbs060Form" property="bbs080orderKey" />

<html:hidden name="mbhBbs060Form" property="threadSid" />
<html:hidden name="mbhBbs060Form" property="bbs040cmdMode" />
<html:hidden name="mbhBbs060Form" property="bbs040forumName" />
<html:hidden name="mbhBbs060Form" property="bbs040title" />
<html:hidden name="mbhBbs060Form" property="bbs040value" />
<html:hidden name="mbhBbs060Form" property="bbs040limit" />
<html:hidden name="mbhBbs060Form" property="bbs040limitYear" />
<html:hidden name="mbhBbs060Form" property="bbs040limitMonth" />
<html:hidden name="mbhBbs060Form" property="bbs040limitDay" />
<html:hidden name="mbhBbs060Form" property="bbs040contributor" />

<html:hidden name="mbhBbs060Form" property="bbs060TmpFileId" />

<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.bbs070kn.1" /></b>

<hr>
■<gsmsg:write key="bbs.3" />
<br>
<bean:write name="mbhBbs060Form" property="bbs040forumName" />
<br>
■<gsmsg:write key="cmn.contributor" />
<br>
<bean:write name="mbhBbs060Form" property="bbs060knviewContributor" />
<br>
■<gsmsg:write key="cmn.title" />
<br>
<bean:write name="mbhBbs060Form" property="bbs040title" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<bean:write name="mbhBbs060Form" property="bbs060viewvalue" filter="false" />
<br>
■<gsmsg:write key="bbs.12" />
<br>
<logic:equal name="mbhBbs060Form" property="bbs040limit" value="0">
<gsmsg:write key="cmn.unlimited" />
</logic:equal>
<logic:notEqual name="mbhBbs060Form" property="bbs040limit" value="0">
<bean:define id="yr" name="mbhBbs060Form" property="bbs040limitYear" type="java.lang.Integer" />
<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr) %>" /><bean:write name="mbhBbs060Form" property="bbs040limitMonth"/><gsmsg:write key="cmn.month" /><bean:write name="mbhBbs060Form" property="bbs040limitDay"/><gsmsg:write key="cmn.day" />
</logic:notEqual>
<br>
<br>

<div align="center">
<input name="bbs060ok" value="<gsmsg:write key="cmn.final" />" type="submit">
<input name="bbs060back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>