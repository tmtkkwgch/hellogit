<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>


<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs020Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs020">
<html:hidden property="bbs010forumSid" />

<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.9" /></b>

<hr>
■<gsmsg:write key="bbs.3" />
<br>
<b><bean:write name="mbhBbs020Form" property="bbs020forumName" /></b>
<hr>
<logic:equal name="mbhBbs020Form" property="bbs020ShowThreBtn" value="1">
<input name="bbs020add" value="<gsmsg:write key="bbs.bbs060.1" />" type="submit">
</logic:equal>
<logic:notEmpty name="mbhBbs020Form" property="threadList">
<logic:iterate id="threadMdl" name="mbhBbs020Form" property="threadList" indexId="idx">

<% String titleColor = "#0000FF"; %>
<logic:equal name="threadMdl" property="readFlg" value="1">
  <% titleColor = "#990099"; %>
</logic:equal>

<br>
<hr>
■<a href="./mh_bbs030.do<%= jsessionId.toString() %>?bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />&threadSid=<bean:write name="threadMdl" property="btiSid" />"><b><span style="color:<%= titleColor %>;"><bean:write name="threadMdl" property="btiTitle" /></span></b></a>
<logic:equal name="threadMdl" property="newFlg" value="1">
  <blink><span style="color: red">new</span></blink>
</logic:equal>
<br>
■<gsmsg:write key="cmn.contributor" />：<bean:write name="threadMdl" property="userName" />
<br>
■<gsmsg:write key="bbs.5" />：<bean:write name="threadMdl" property="writeCnt" />
<br>
■<gsmsg:write key="bbs.11" />：<bean:write name="threadMdl" property="readedCnt" />&nbsp;/&nbsp;<bean:write name="mbhBbs020Form" property="forumMemberCount" />
<br>
■<gsmsg:write key="bbs.bbs060.3" />
<br>
<bean:write name="threadMdl" property="strWriteDate" />
</logic:iterate>
</logic:notEmpty>

<br>
<br>
<div align="center">
<logic:notEmpty name="mbhBbs020Form" property="threadList">
<bean:define id="maxPage" name="mbhBbs020Form" property="bbs020maxPage" />
<logic:greaterThan name="maxPage" value="1">
  <logic:notEqual name="mbhBbs020Form" property="bbs020page1" value="1">
  <logic:notEqual name="mbhBbs020Form" property="bbs020page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/mh_bbs020.do<%= jsessionId.toString() %>?CMD=prevPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs020Form" property="bbs020page1" />/<bean:write name="mbhBbs020Form" property="bbs020maxPage" />)<a href="../mobile/mh_bbs020.do<%= jsessionId.toString() %>?CMD=nextPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />"><gsmsg:write key="cmn.next" /></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs020Form" property="bbs020page1" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhBbs020Form" property="bbs020page1" />/<bean:write name="mbhBbs020Form" property="bbs020maxPage" />)<a href="../mobile/mh_bbs020.do<%= jsessionId.toString() %>?CMD=nextPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />"><gsmsg:write key="cmn.next" /></a>
  </logic:equal>
  <logic:equal name="mbhBbs020Form" property="bbs020page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/mh_bbs020.do<%= jsessionId.toString() %>?CMD=prevPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs020Form" property="bbs020page1" />/<bean:write name="mbhBbs020Form" property="bbs020maxPage" />)<gsmsg:write key="cmn.next" />
  </logic:equal>
</logic:greaterThan>
</logic:notEmpty>
<input name="bbs020back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>