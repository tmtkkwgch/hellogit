<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRng020Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="rng.62" /><gsmsg:write key="cmn.list" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_rng020">

<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />

<b><%= emojiRingi.toString() %><gsmsg:write key="rng.62" /><br>
<logic:equal name="mbhRng020Form" property="rngProcMode" value="0">
<gsmsg:write key="cmn.receive" /><gsmsg:write key="cmn.list" />
</logic:equal>
<logic:equal name="mbhRng020Form" property="rngProcMode" value="1">
<gsmsg:write key="rng.48" /><gsmsg:write key="cmn.list" />
</logic:equal>
<logic:equal name="mbhRng020Form" property="rngProcMode" value="2">
<gsmsg:write key="cmn.complete" /><gsmsg:write key="cmn.list" />
</logic:equal>
<logic:equal name="mbhRng020Form" property="rngProcMode" value="3">
<gsmsg:write key="cmn.draft" /><gsmsg:write key="cmn.list" />
</logic:equal>
</b>
<% int mode0 = jp.groupsession.v2.rng.RngConst.RNG_MODE_JYUSIN; %>
<% int mode1 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SINSEI; %>
<% int mode2 = jp.groupsession.v2.rng.RngConst.RNG_MODE_KANRYO; %>
<% int mode3 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SOUKOU; %>

<% int sort_title = jp.groupsession.v2.rng.RngConst.RNG_SORT_TITLE; %>
<% int sort_name = jp.groupsession.v2.rng.RngConst.RNG_SORT_NAME; %>
<% int sort_appl = jp.groupsession.v2.rng.RngConst.RNG_SORT_DATE; %>
<% int sort_jyusin = jp.groupsession.v2.rng.RngConst.RNG_SORT_JYUSIN; %>
<% int sort_kakunin = jp.groupsession.v2.rng.RngConst.RNG_SORT_KAKUNIN; %>
<% int sort_touroku = jp.groupsession.v2.rng.RngConst.RNG_SORT_TOUROKU; %>
<% int order_asc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC; %>
<% int order_desc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC; %>
<% String rngstatus_settlet = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED); %>
<% String rngstatus_reject = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT); %>

<input type="hidden" name="CMD" value="moveSearch">
<input type="hidden" name="rngCmdMode" value="0">
<input type="hidden" name="rngApprMode" value="0">
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />

<bean:define id="procMode" name="mbhRng020Form" property="rngProcMode" />
<% int sMode = ((Integer) procMode).intValue(); %>

<logic:notEmpty name="mbhRng020Form" property="rngDataList">
<logic:iterate id="rngData" name="mbhRng020Form" property="rngDataList" indexId="idx" scope="request">

<% if (sMode == mode0) { %>
<% String apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPR); %>
<logic:equal name="rngData" property="rncType" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_RNCTYPE_APPL) %>">
  <% apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPL); %>
</logic:equal>
<hr>
■<gsmsg:write key="cmn.title" />：<a href="./mh_rng030.do<%= jsessionId.toString() %>?rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />"><b><bean:write name="rngData" property="rngTitle" /></b></a>
<br>
■<gsmsg:write key="rng.47" />：<bean:write name="rngData" property="apprUser" />
<br>
■<gsmsg:write key="rng.application.date" />
<br>
<bean:write name="rngData" property="strRngAppldate" />
<br>
■<gsmsg:write key="cmn.received.date" />
<br>
<bean:write name="rngData" property="strRcvDate" />

<% } else if (sMode == mode1) { %>
<hr>
■<gsmsg:write key="cmn.title" />：<a href="./mh_rng030.do<%= jsessionId.toString() %>?rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />"><bean:write name="rngData" property="rngTitle" /></a>
<br>
■<gsmsg:write key="rng.47" />：<bean:write name="rngData" property="apprUser" />
<br>
■<gsmsg:write key="rng.application.date" />
<br>
<bean:write name="rngData" property="strRngAppldate" />
<br>
■<gsmsg:write key="rng.105" />
<br>
<bean:write name="rngData" property="strLastManageDate" />

<% } else if (sMode == mode2) { %>
<% String rngStatus = "&nbsp;"; %>
<gsmsg:define id="textKessai" msgkey="rng.64" />
<gsmsg:define id="textKyakka" msgkey="rng.65" />
<logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_settlet %>">
  <% rngStatus = textKessai; %>
</logic:equal>
<logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_reject %>">
  <% rngStatus = textKyakka; %>
</logic:equal>
<hr>
■<gsmsg:write key="cmn.title" />：<a href="./mh_rng030.do<%= jsessionId.toString() %>?rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />"><bean:write name="rngData" property="rngTitle" /></a>
<br>
(<%= rngStatus %>)
<br>
■<gsmsg:write key="rng.47" />：<bean:write name="rngData" property="apprUser" />
<br>
■<gsmsg:write key="rng.application.date" />
<br>
<bean:write name="rngData" property="strRngAppldate" />
<br>
■<gsmsg:write key="rng.105" />
<br>
<bean:write name="rngData" property="strLastManageDate" />

<% } else if (sMode == mode3) { %>
<hr>
■<gsmsg:write key="cmn.title" />：<a href="./mh_rng040.do<%= jsessionId.toString() %>?rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rngCmdMode=1&CMD=rngEdit&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />"><bean:write name="rngData" property="rngTitle" /></a>
<br>
■<gsmsg:write key="rng.37" />
<br>
<bean:write name="rngData" property="strMakeDate" />

<% } %>

</logic:iterate>
</logic:notEmpty>

<br>
<br>
<div align="center">
<logic:notEmpty name="mbhRng020Form" property="pageList">
  <bean:define id="maxCnt" name="mbhRng020Form" property="rng020maxPage" />
  <logic:greaterThan name="maxCnt" value="1">
  <logic:equal name="mbhRng020Form" property="rng020pageTop" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhRng020Form" property="rng020pageTop" />/<bean:write name="mbhRng020Form" property="rng020maxPage" />)<a href="./mh_rng020.do<%= jsessionId.toString() %>?CMD=nextPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />"><gsmsg:write key="cmn.next" /></a>
  </logic:equal>
  <logic:notEqual name="mbhRng020Form" property="rng020pageTop" value="1">
  <logic:notEqual name="mbhRng020Form" property="rng020pageTop" value="<%= maxCnt.toString() %>">
    <a href="./mh_rng020.do<%= jsessionId.toString() %>?CMD=prevPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhRng020Form" property="rng020pageTop" />/<bean:write name="mbhRng020Form" property="rng020maxPage" />)<a href="./mh_rng020.do<%= jsessionId.toString() %>?CMD=nextPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />"><gsmsg:write key="cmn.next" /></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhRng020Form" property="rng020pageTop" value="<%= maxCnt.toString() %>">
    <a href="./mh_rng020.do<%= jsessionId.toString() %>?CMD=prevPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhRng020Form" property="rng020pageTop" />/<bean:write name="mbhRng020Form" property="rng020maxPage" />)<gsmsg:write key="cmn.next" />
  </logic:equal>
  </logic:greaterThan>
</logic:notEmpty>
&nbsp;&nbsp;<input name="rng020back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>