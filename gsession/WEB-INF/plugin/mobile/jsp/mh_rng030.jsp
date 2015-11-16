<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRng030Form"; %>
<%@ page import="jp.groupsession.v2.rng.RngConst" %>
<%@ page import="jp.groupsession.v2.rng.rng030.Rng030Form" %>

<% String maxLengthContent = String.valueOf(RngConst.MAX_LENGTH_CONTENT); %>
<% String maxLengthCmt = String.valueOf(RngConst.MAX_LENGTH_COMMENT); %>
<% int cmdmode_view = Rng030Form.CMDMODE_VIEW; %>
<% int cmdmode_appr = Rng030Form.CMDMODE_APPR; %>
<% int cmdmode_confirm = Rng030Form.CMDMODE_CONFIRM; %>
<% int cmdmode_adminappr = Rng030Form.CMDMODE_ADMINAPPR; %>
<% String apprMode_appl = String.valueOf(RngConst.RNG_APPRMODE_APPL); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="sml.sml030.08" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<bean:define id="procMode" name="mbhRng030Form" property="rngProcMode" type="java.lang.Integer" />
<body>

<html:form action="/mobile/mh_rng030">


<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngApprMode" />
<html:hidden property="rng020pageTop" />
<html:hidden property="rng020pageBottom" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />


<input type="hidden" name="rng030fileId" value="">


<logic:notEqual name="mbhRng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
  <html:hidden property="rng030ViewTitle" />
</logic:notEqual>



<b><%= emojiRingi.toString() %><gsmsg:write key="rng.62" /><br><gsmsg:write key="sml.sml030.08" /></b>

<hr>

■<gsmsg:write key="cmn.status" />：
<bean:define id="rngStatus" name="mbhRng030Form" property="rng030Status" />
<% if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_REQUEST) { %>
<gsmsg:write key="rng.48" />
<% } else if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED) { %>
<gsmsg:write key="rng.64" />
<% } else if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT) { %>
<gsmsg:write key="rng.65" />
<% } else { %>
&nbsp;
<% } %>
<br>
■<gsmsg:write key="cmn.title" />：<bean:write name="mbhRng030Form" property="rng030ViewTitle" />
<br>
■<gsmsg:write key="rng.47" />：<bean:write name="mbhRng030Form" property="rng030apprUser" />
<br>
■<gsmsg:write key="rng.37" />
<br>
<bean:write name="mbhRng030Form" property="rng030makeDate" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<bean:write name="mbhRng030Form" property="rng030ViewContent" filter="false" />
<br>
■<gsmsg:write key="rng.24" />
<br>
<logic:iterate id="apprUser" name="mbhRng030Form" property="channelList" indexId="idx">
<bean:define id="channelStatus" name="apprUser" property="rncStatus" />

  <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR) { %>
    <gsmsg:write key="rng.41" />&nbsp;&nbsp;
  <% } else if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_DENIAL) { %>
<gsmsg:write key="rng.rng030.12" />&nbsp;&nbsp;
  <% } else {%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <% } %>

  <logic:equal name="apprUser" property="delUser" value="true">
    <strike><bean:write name="apprUser" property="userName" /></strike>
  </logic:equal>
  <logic:equal name="apprUser" property="delUser" value="false">
    <bean:write name="apprUser" property="userName" />
  </logic:equal>

  <logic:equal name="apprUser" property="ringiUse" value="false">
    <logic:notEmpty name="apprUser" property="rncComment"><br></logic:notEmpty>
    <span style="color:red;"><gsmsg:write key="rng.rng030.01" /></span>
  </logic:equal>
  <br>
</logic:iterate>
<br>
<logic:notEmpty name="mbhRng030Form" property="confirmChannelList">
■<gsmsg:write key="rng.35" />
<br>
<logic:iterate id="confirmUser" name="mbhRng030Form" property="confirmChannelList" indexId="idx">
<bean:define id="channelStatus" name="confirmUser" property="rncStatus" />

  <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_CONFIRMATION) { %>
<gsmsg:write key="cmn.check" />&nbsp;&nbsp;
  <% } else {%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <% } %>

  <logic:equal name="confirmUser" property="delUser" value="true">
    <strike><bean:write name="confirmUser" property="userName" /></strike>
  </logic:equal>
  <logic:equal name="confirmUser" property="delUser" value="false">
    <bean:write name="confirmUser" property="userName" />
  </logic:equal>


  <logic:equal name="confirmUser" property="ringiUse" value="false">
    <logic:notEmpty name="confirmUser" property="rncComment"><br></logic:notEmpty>
    <span style="color:red;"><gsmsg:write key="rng.rng030.01" /></span>
  </logic:equal>
<br>
</logic:iterate>
</logic:notEmpty>
<br>

<div align="center">
  <logic:equal name="mbhRng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_appr) %>">
  <input name="rng030approval" value="<gsmsg:write key="rng.41" />" type="submit">
  <input name="rng030reject" value="<gsmsg:write key="rng.22" />" type="submit">
  <logic:equal name="mbhRng030Form" property="rng030rftBtnFlg" value="1">
  <input name="rng030reflection" value="<gsmsg:write key="rng.rng030.08" />" type="submit">
  </logic:equal>
  </logic:equal>

  <logic:equal name="mbhRng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_confirm) %>">
    <input name="rng030confirmation" value="<gsmsg:write key="cmn.check" />" type="submit">
  </logic:equal>

  <logic:equal name="mbhRng030Form" property="rng030compBtnFlg" value="1">
    <input name="rng030complete" value="<gsmsg:write key="cmn.complete" />" type="submit">
  </logic:equal>

  <input name="rng030back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>