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
<% pluginName = "bbsTop"; %>
<% thisForm = "mbhBbs010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs010">
<html:hidden property="mobileType" value="1"/>
<bean:define id="maxPage" name="mbhBbs010Form" property="bbs010maxCnt" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="mobile.29" /><gsmsg:write key="cmn.list" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li data-role="list-divider">
    <div align="center">
      <b><gsmsg:write key="bbs.3" /></b><logic:greaterThan name="maxPage" value="1"><div class="font_small">(<bean:write name="mbhBbs010Form" property="bbs010page1" />/<bean:write name="mbhBbs010Form" property="bbs010maxCnt" />)</div></logic:greaterThan>
    </div>
  </li>

  <bean:define id="mod" value="0" />
  <logic:iterate id="forumMdl" name="mbhBbs010Form" property="forumList" indexId="idx">
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>
    <% String titleColor = "#0000FF"; %>
    <logic:equal name="forumMdl" property="readFlg" value="1">
      <% titleColor = "#990099"; %>
    </logic:equal>
    <li data-theme=<bean:write name="liColor" />>
      <a href="../mobile/sp_bbs020.do?mobileType=1&bbs010forumSid=<bean:write name="forumMdl" property="bfiSid" />"><span style="color:<%= titleColor %>;"><bean:write name="forumMdl" property="bfiName" /><span>
        <logic:equal name="forumMdl" property="newFlg" value="1">
          <blink><span style="color:red;">new</span></blink>
        </logic:equal>
      </a>
    </li>
  </logic:iterate>
</ul>
<div align="center">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEmpty name="mbhBbs010Form" property="forumList">
  <logic:greaterThan name="maxPage" value="1">
  <logic:notEqual name="mbhBbs010Form" property="bbs010page1" value="1">
  <logic:notEqual name="mbhBbs010Form" property="bbs010page1" value="<%= maxPage.toString() %>">
      <a href="../mobile/sp_bbs010.do?mobileType=1&CMD=prevPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_small"><gsmsg:write key="cmn.previous" /></div></a><a href="../mobile/sp_bbs010.do?mobileType=1&CMD=nextPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"　 data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_small"><gsmsg:write key="cmn.next" /></div></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs010Form" property="bbs010page1" value="1">
    <a href="../mobile/sp_bbs010.do?mobileType=1&CMD=nextPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"　data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_small"><gsmsg:write key="cmn.next" /></div></a>
  </logic:equal>
  <logic:equal name="mbhBbs010Form" property="bbs010page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/sp_bbs010.do?mobileType=1&CMD=prevPage&bbs010page1=<bean:write name="mbhBbs010Form" property="bbs010page1" />"　data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_small"><gsmsg:write key="cmn.previous" /></div></a>
  </logic:equal>
  </logic:greaterThan>
</logic:notEmpty>
</div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->
</body>
</html:html>