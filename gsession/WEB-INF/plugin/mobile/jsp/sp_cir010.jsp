<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "cirTop"; %>
<% thisForm = "mbhCir010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_cir010">

<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/cir_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cir.5" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div align="center">
  <logic:notEmpty name="mbhCir010Form" property="cir010AccountList">
  <html:select name="mbhCir010Form" property="cirViewAccount" styleClass="select01" onchange="changeCombo();">
    <logic:iterate id="accountMdl" name="mbhCir010Form" property="cir010AccountList">
      <bean:define id="accoutVal" name="accountMdl" property="accountSid" type="java.lang.Integer" />
      <html:option value="<%= String.valueOf(accoutVal) %>"><bean:write name="accountMdl" property="accountName" /></html:option>
    </logic:iterate>
  </html:select>
  </logic:notEmpty>
</div>

<div data-role="content">

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li>
    <a href="./sp_cir050.do?mobileType=1&cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><img src="../mobile/sp/imgages/mail_sakusei.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.create.new" /></a>
  </li>
  <li>
    <a href="./sp_cir020.do?mobileType=1&cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><gsmsg:write key="cmn.receive" /><img src="../mobile/sp/imgages/mail_jushin.gif" class="ui-li-icon ui-li-thumb"><logic:greaterThan name="mbhCir010Form" property="mikakkuCount" value="0">(<bean:write name="mbhCir010Form" property="mikakkuCount" />)</logic:greaterThan></a>
  </li>
  <li>
    <a href="./sp_cir020.do?mobileType=1&cir020cmdMode=1&cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><img src="../mobile/sp/imgages/mail_soshin.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.sent" /></a>
  </li>
  <li>
    <a href="./sp_cir020.do?mobileType=1&cir020cmdMode=3&cirViewAccount=<bean:write name="mbhCir010Form" property="cirViewAccount" />"><img src="../mobile/sp/imgages/mail_gomi.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.trash" /></a>
  </li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>