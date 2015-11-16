<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs020">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="bbs010forumSid" />
<input type="hidden" name="CMD" value="">
<bean:define id="maxPage" name="mbhBbs020Form" property="bbs020maxPage" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <a href="#" onClick="buttonPush('bbs020back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
  <img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="bbs.9" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div align="center">
  <logic:equal name="mbhBbs020Form" property="bbs020ShowThreBtn" value="1">
  <input name="bbs020add" value="<gsmsg:write key="bbs.bbs060.1" />" type="submit" data-inline="true" data-role="button" data-icon="plus" data-iconpos="right" />
  </logic:equal>
</div>
<logic:notEmpty name="mbhBbs020Form" property="threadList">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <b><bean:write name="mbhBbs020Form" property="bbs020forumName" /></b><logic:greaterThan name="maxPage" value="1"><div class="font_small">(<bean:write name="mbhBbs020Form" property="bbs020page1" />/<bean:write name="mbhBbs020Form" property="bbs020maxPage" />)</div></logic:greaterThan>
    </div>
  </li>
  <bean:define id="mod" value="0" />
  <logic:iterate id="threadMdl" name="mbhBbs020Form" property="threadList" indexId="idx">
  <% String titleColor = "#0000FF"; %>
  <logic:equal name="threadMdl" property="readFlg" value="1">
    <% titleColor = "#990099"; %>
  </logic:equal>
  <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
    <bean:define id="liColor" value="c" />
  </logic:equal>
  <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
    <bean:define id="liColor" value="d" />
  </logic:notEqual>
    <li data-theme=<bean:write name="liColor" />>
      <a href="./sp_bbs030.do?mobileType=1&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />&threadSid=<bean:write name="threadMdl" property="btiSid" />"><b><span style="color:<%= titleColor %>;"><bean:write name="threadMdl" property="btiTitle" /></span></b>
      <logic:equal name="threadMdl" property="newFlg" value="1">
        <blink><span style="color: red">new</span></blink>
      </logic:equal>
      <div class="font_small">
      <br>
      ■<gsmsg:write key="cmn.contributor" />：<bean:write name="threadMdl" property="userName" />
      <br>
      ■<gsmsg:write key="bbs.5" />：<bean:write name="threadMdl" property="writeCnt" />
      <br>
      ■<gsmsg:write key="bbs.11" />：<bean:write name="threadMdl" property="readedCnt" />&nbsp;/&nbsp;<bean:write name="mbhBbs020Form" property="forumMemberCount" />
      <br>
      ■<gsmsg:write key="bbs.bbs060.3" />：<bean:write name="threadMdl" property="strWriteDate" />
      </div>
      </a>
    </li>
  </logic:iterate>
</ul>
</logic:notEmpty>

<div align="center">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEmpty name="mbhBbs020Form" property="threadList">
<logic:greaterThan name="maxPage" value="1">
  <logic:notEqual name="mbhBbs020Form" property="bbs020page1" value="1">
  <logic:notEqual name="mbhBbs020Form" property="bbs020page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/sp_bbs020.do?mobileType=1&CMD=prevPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a><a href="../mobile/sp_bbs020.do?mobileType=1&CMD=nextPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid"/>" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs020Form" property="bbs020page1" value="1">
    <a href="../mobile/sp_bbs020.do?mobileType=1&CMD=nextPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid"/>" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:equal>
  <logic:equal name="mbhBbs020Form" property="bbs020page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/sp_bbs020.do?mobileType=1&CMD=prevPage&bbs020page1=<bean:write name="mbhBbs020Form" property="bbs020page1" />&bbs010forumSid=<bean:write name="mbhBbs020Form" property="bbs010forumSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a>
  </logic:equal>
</logic:greaterThan>
</logic:notEmpty>
</div>
<div align="center">
  <input name="bbs020back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>

</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->

</body>
</html:html>