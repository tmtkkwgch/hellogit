<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhBbs030Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.32" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_bbs030">
<html:hidden property="bbs010forumSid" />
<html:hidden property="threadSid" />

<b><%= emojiMemo.toString() %><gsmsg:write key="cmn.bulletin" /> - <gsmsg:write key="bbs.32" /></b>

<hr>
■<gsmsg:write key="bbs.3" />
<br>
<a href="../mobile/mh_bbs020.do<%= jsessionId.toString() %>?bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />"><span class="text_bbs1"><bean:write name="mbhBbs030Form" property="bbs030forumName" /></span></a>
<br>
■<gsmsg:write key="cmn.title" />
<br>
<b><bean:write name="mbhBbs030Form" property="bbs030threadTitle" /></b>
<hr>

<div align="center">
  <logic:notEmpty name="mbhBbs030Form" property="bbsPageLabel">
  <bean:define id="maxPage" name="mbhBbs030Form" property="bbs030maxPage" />
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="1">
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)<a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />"><gsmsg:write key="cmn.next" /></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)<a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />"><gsmsg:write key="cmn.next" /></a>
  </logic:equal>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" /><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)<gsmsg:write key="cmn.next" />
  </logic:equal>
  </logic:notEmpty>
  <logic:equal name="mbhBbs030Form" property="bbs030ShowThreBtn" value="1">
  <logic:equal name="mbhBbs030Form" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
    <input name="bbs030reply" value="<gsmsg:write key="cmn.reply" />" type="submit">
  </logic:equal>
  </logic:equal>
  <input name="bbs030back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

<br>
<logic:notEmpty name="mbhBbs030Form" property="writeList">
<logic:iterate id="writeMdl" name="mbhBbs030Form" property="writeList" indexId="idx">
  <% if (idx != 0) { %>
  <hr>
  <% } %>
  <b>■<gsmsg:write key="cmn.contributor" />：
  <logic:equal name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <del><bean:write name="writeMdl" property="userName" /></del>
  </logic:equal>
  <logic:notEqual name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <bean:write name="writeMdl" property="userName" />
  </logic:notEqual>
  </b>
  <br>
  <b>■<gsmsg:write key="cmn.posted" /></b>
  <br>
  <b>
    <logic:notEqual name="writeMdl" property="writeUpdateFlg" value="1">
      <bean:write name="writeMdl" property="strBwiAdate" />
    </logic:notEqual>
    <logic:equal name="writeMdl" property="writeUpdateFlg" value="1">
      <bean:write name="writeMdl" property="strBwiEdate" />
    </logic:equal>
  </b>
  <br>
  <b>■<gsmsg:write key="cmn.content" /></b>
    <logic:equal name="writeMdl" property="newFlg" value="1">
      <blink><span style="color:red">new</span></blink>
    </logic:equal>
  <br>
  <bean:write name="writeMdl" property="bwiValueView" filter="false" />
  <br>
</logic:iterate>
</logic:notEmpty>

<br>
<div align="center">
  <logic:notEmpty name="mbhBbs030Form" property="bbsPageLabel">
  <bean:define id="maxPage" name="mbhBbs030Form" property="bbs030maxPage" />
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="1">
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)<a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />"><gsmsg:write key="cmn.next" /></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)<a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />"><gsmsg:write key="cmn.next" /></a>
  </logic:equal>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/mh_bbs030.do<%= jsessionId.toString() %>?CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" /><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)<gsmsg:write key="cmn.next" />
  </logic:equal>
  </logic:notEmpty>
  <logic:equal name="mbhBbs030Form" property="bbs030ShowThreBtn" value="1">
  <logic:equal name="mbhBbs030Form" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
    <input name="bbs030reply" value="<gsmsg:write key="cmn.reply" />" type="submit">
  </logic:equal>
  </logic:equal>
  <input name="bbs030back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>