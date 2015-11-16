<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%  String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="main.man030.10" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% thisForm = "mbhCmn002Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_cmn002">
<html:hidden property="mobileType" value="1"/>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="delete" data-iconpos="notext"></a>
  <h1><b><gsmsg:write key="cmn.setting" /></b><br></h1>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhCmn002Form" property="themeList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider" style="background:#ffffff;">
      <div class="font_middle" align="center"><gsmsg:write key="main.man030.10" /></div>
    </li>
    <logic:iterate id="theme" name="mbhCmn002Form" property="themeList">
      <li>
        <a href="../mobile/sp_cmn002.do?mobileType=1&CMD=changeTheme&themeSid=<bean:write name="theme" property="mbtSid"/>&themeId=<bean:write name="theme" property="mbtId"/>">
          <logic:equal name="theme" property="mbtId" value="<%= usrTheme %>">
            <img src="../mobile/sp/imgages/icon_check.gif"  class="ui-li-icon ui-li-thumb" />
          </logic:equal>
          <logic:notEqual name="theme" property="mbtId" value="<%= usrTheme %>">
            <img src="../mobile/sp/imgages/icon_dummy.gif"  class="ui-li-icon ui-li-thumb" />
          </logic:notEqual>
          <bean:write name="theme" property="mbtName" />
        </a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

</div><!-- /content -->

<div data-role="footer" data-theme="<%= usrTheme %>">
  <br>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_footer.jsp" %>
</div><!-- /footer -->

</html:form>

</div><!-- /page -->

</body>

</html:html>