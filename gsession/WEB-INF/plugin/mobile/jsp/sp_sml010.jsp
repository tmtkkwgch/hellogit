<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shortmail" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "smlTop"; %>
<% thisForm = "mbhSml010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_sml010">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="smlViewAccountName" />
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/sml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shortmail" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div align="center">
  <logic:notEmpty name="mbhSml010Form" property="sml010AccountList">
  <html:select name="mbhSml010Form" property="smlViewAccount" styleClass="select01" onchange="changeCombo();">
    <logic:iterate id="accountMdl" name="mbhSml010Form" property="sml010AccountList">
      <bean:define id="accoutVal" name="accountMdl" property="accountSid" type="java.lang.Integer" />
      <html:option value="<%= String.valueOf(accoutVal) %>"><bean:write name="accountMdl" property="accountName" /></html:option>
    </logic:iterate>
  </html:select>
  </logic:notEmpty>
</div>

<div data-role="content">

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li>
    <a href="./sp_sml040.do?mobileType=1&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />" accesskey="0"><img src="../mobile/sp/imgages/mail_sakusei.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.create.new" /></a>
  </li>
  <li>
    <a href="./sp_sml020.do?mobileType=1&sml010ProcMode=0&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />" accesskey="0"><img src="../mobile/sp/imgages/mail_jushin.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.receive" />
    <logic:greaterThan name="mbhSml010Form" property="sml010MidokuCnt" value="0">(<bean:write name="mbhSml010Form" property="sml010MidokuCnt" />)</logic:greaterThan></a>
  </li>
  <li>
    <a href="./sp_sml020.do?mobileType=1&sml010ProcMode=1&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />" accesskey="0"><img src="../mobile/sp/imgages/mail_soshin.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.sent" /></a>
  </li>
  <li>
    <a href="./sp_sml020.do?mobileType=1&sml010ProcMode=2&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />" accesskey="0"><img src="../mobile/sp/imgages/mail_hokan.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.draft" />
    <logic:greaterThan name="mbhSml010Form" property="sml010SokoCnt" value="0">(<bean:write name="mbhSml010Form" property="sml010SokoCnt" />)</logic:greaterThan></a>
  </li>
  <li>
    <a href="./sp_sml020.do?mobileType=1&sml010ProcMode=4&smlViewAccount=<bean:write name="mbhSml010Form" property="smlViewAccount" />" accesskey="0"><img src="../mobile/sp/imgages/mail_gomi.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.trash" />
    <logic:greaterThan name="mbhSml010Form" property="sml010GomiMidokuCnt" value="0">(<bean:write name="mbhSml010Form" property="sml010GomiMidokuCnt" />)</logic:greaterThan></a>
  </li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>