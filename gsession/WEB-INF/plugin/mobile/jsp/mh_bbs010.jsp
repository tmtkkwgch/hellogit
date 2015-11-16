<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs010Form"; %>
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

<html:form action="/mobile/mh_bbs010">


<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.1" /></b>

<hr>
■<gsmsg:write key="bbs.3" />
<ul>
<logic:iterate id="forumMdl" name="mbhBbs010Form" property="forumList" indexId="idx">
  <% String titleColor = "#0000FF"; %>
  <logic:equal name="forumMdl" property="readFlg" value="1">
    <% titleColor = "#990099"; %>
  </logic:equal>
<li>
<a href="../mobile/mh_bbs020.do<%= jsessionId.toString() %>?bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />"><span style="color:<%= titleColor %>;"><bean:write name="forumMdl" property="bfiName" /><span></a>
<logic:equal name="forumMdl" property="newFlg" value="1">
<blink><span style="color:red;">new</span></blink>
</logic:equal>
</li>
</logic:iterate>
</ul>
<div align="center">
<logic:notEmpty name="mbhBbs010Form" property="forumList">
  <bean:define id="maxPage" name="mbhBbs010Form" property="bbs010maxCnt" />
  <logic:greaterThan name="maxPage" value="1">
  <logic:notEqual name="mbhBbs010Form" property="bbs010page1" value="1">
  <logic:notEqual name="mbhBbs010Form" property="bbs010page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/mh_bbs010.do<%= jsessionId.toString() %>?CMD=prevPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs010Form" property="bbs010page1" />/<bean:write name="mbhBbs010Form" property="bbs010maxCnt" />)<a href="../mobile/mh_bbs010.do<%= jsessionId.toString() %>?CMD=nextPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"><gsmsg:write key="cmn.next" /></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs010Form" property="bbs010page1" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhBbs010Form" property="bbs010page1" />/<bean:write name="mbhBbs010Form" property="bbs010maxCnt" />)<a href="../mobile/mh_bbs010.do<%= jsessionId.toString() %>?CMD=nextPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"><gsmsg:write key="cmn.next" /></a>
  </logic:equal>
  <logic:equal name="mbhBbs010Form" property="bbs010page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/mh_bbs010.do<%= jsessionId.toString() %>?CMD=prevPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs010Form" property="bbs010page1" />/<bean:write name="mbhBbs010Form" property="bbs010maxCnt" />)<gsmsg:write key="cmn.next" />
  </logic:equal>
  </logic:greaterThan>
</logic:notEmpty>
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>