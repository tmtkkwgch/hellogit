<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="rng.62" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rngTop"; %>
<% thisForm = "mbhRng010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_rng010">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/rng_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="rng.62" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li>
    <a href="./sp_rng040.do?mobileType=1"><img src="../mobile/sp/imgages/mail_sakusei.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="rng.rng010.02" /></a>
  </li>
  <li>
    <a href="./sp_rng020.do?mobileType=1&rngProcMode=0"><img src="../mobile/sp/imgages/mail_jushin.gif" class="ui-li-icon" ui-li-thumb><gsmsg:write key="cmn.receive" />
    <logic:notEqual name="mbhRng010Form" property="bbs010ReceptCnt" value="0">
    (<bean:write name="mbhRng010Form" property="bbs010ReceptCnt"/>)
    </logic:notEqual>
    </a>
  </li>
  <li>
    <a href="./sp_rng020.do?mobileType=1&rngProcMode=1&rng020sortKey=2"><img src="../mobile/sp/imgages/mail_soshin.gif" class="ui-li-icon" ui-li-thumb><gsmsg:write key="rng.application.ongoing" /></a>
  </li>
  <li>
    <a href="./sp_rng020.do?mobileType=1&rngProcMode=2&rng020sortKey=4&rng020orderKey=1"><img src="../mobile/sp/imgages/mail_hokan.gif" class="ui-li-icon" ui-li-thumb><gsmsg:write key="cmn.complete" /></a>
  </li>
  <li>
    <a href="./sp_rng020.do?mobileType=1&rngProcMode=3&rng020sortKey=5&rng020orderKey=1"><img src="../mobile/sp/imgages/mail_hokan.gif" class="ui-li-icon" ui-li-thumb><gsmsg:write key="cmn.draft" /></a>
  </li>
</ul>
</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>