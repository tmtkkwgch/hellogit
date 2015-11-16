<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% String maxLengthContent = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_CONTENT); %>
<% thisForm = "mbhRng040knForm"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="rng.rng020kn.01" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
</head>

<body>

<html:form action="/mobile/mh_rng040kn">

<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<html:hidden property="rng020pageTop" />
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rng040Title" />
<html:hidden property="rng040requestUser" />
<html:hidden property="rng040Content" />

<logic:notEmpty name="mbhRng040knForm" property="rng040apprUser">
<logic:iterate id="apprUser" name="mbhRng040knForm" property="rng040apprUser">
  <input type="hidden" name="rng040apprUser" value="<bean:write name='apprUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040knForm" property="rng040confirmUser">
<logic:iterate id="confirmUser" name="mbhRng040knForm" property="rng040confirmUser">
  <input type="hidden" name="rng040confirmUser" value="<bean:write name='confirmUser' />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="rng040BinSid" />

<b><%= emojiMail.toString() %><gsmsg:write key="rng.62" /><br><gsmsg:write key="rng.rng020kn.01" /></b>

<hr>

■<gsmsg:write key="cmn.title" />
<br>
<bean:write name="mbhRng040knForm" property="rng040Title" />
<br>
■<gsmsg:write key="rng.47" />
<br>
<bean:write name="mbhRng040knForm" property="rng040requestUser" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<bean:write name="mbhRng040knForm" property="rng040knContent" filter="false" />
<br>
■<gsmsg:write key="rng.24" />
<br>
<logic:notEmpty name="mbhRng040knForm" property="channelList">
<logic:iterate id="channelModel" name="mbhRng040knForm" property="channelList" indexId="idx">
<bean:write name="channelModel" property="userName" />
<logic:notEmpty name="channelModel" property="yakusyoku">
(<bean:write name="channelModel" property="yakusyoku" />)
</logic:notEmpty>
<br>
</logic:iterate>
</logic:notEmpty>
<br>
<logic:notEmpty name="mbhRng040knForm" property="confirmChannelList">
■<gsmsg:write key="cmn.check" />
<br>
<logic:iterate id="confirmChannelModel" name="mbhRng040knForm" property="confirmChannelList" indexId="indx">
<bean:write name="confirmChannelModel" property="userName" />
<logic:notEmpty name="confirmChannelModel" property="yakusyoku">
(<bean:write name="confirmChannelModel" property="yakusyoku" />)
</logic:notEmpty>
<br>
</logic:iterate>
</logic:notEmpty>
<br>
<div align="center">
  <input name="rng040knSinsei" value="<gsmsg:write key="rng.46" />" type="submit">
  <input name="rng040knBack" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>