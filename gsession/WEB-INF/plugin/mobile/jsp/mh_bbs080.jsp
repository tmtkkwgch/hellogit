<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs080Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs090kn.1" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs080">
<input type="hidden" name="CMD" value="">
<html:hidden name="mbhBbs080Form" property="bbs010page1" />
<html:hidden name="mbhBbs080Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs080Form" property="threadSid" />
<html:hidden name="mbhBbs080Form" property="bbs030page1" />
<html:hidden name="mbhBbs080Form" property="bbs030writeSid" />
<html:hidden name="mbhBbs080Form" property="bbs030orderKey" />
<html:hidden name="mbhBbs080Form" property="bbs070cmdMode" />
<html:hidden name="mbhBbs080Form" property="bbs070value" />
<html:hidden name="mbhBbs080Form" property="bbs070threTitle" />
<html:hidden name="mbhBbs080Form" property="bbs070contributor" />

<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.bbs090kn.1" /></b>

<hr>
■<gsmsg:write key="bbs.3" />
<br>
<bean:write name="mbhBbs080Form" property="bbs070forumName" />
<br>
■<gsmsg:write key="cmn.contributor" />
<br>
<bean:write name="mbhBbs080Form" property="bbs080knviewContributor" />
<br>
■<gsmsg:write key="cmn.title" />
<br>
<bean:write name="mbhBbs080Form" property="bbs070threTitle" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<bean:write name="mbhBbs080Form" property="bbs080viewvalue" filter="false" />

<br>
<br>

<div align="center">
<input name="bbs080ok" value="<gsmsg:write key="cmn.final" />" type="submit">
<input name="bbs080back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>