<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="addressbook" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "adr"; %>
<% thisForm = "mbhAdr040Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_adr040">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010SelectInd" />
<html:hidden property="adr010SearchInd" />
<html:hidden property="adr010page" />
<html:hidden property="adr010SearchTopFlg" />
<html:hidden property="adr010SearchKanaFlg" />
<html:hidden property="adr010selectLabel" />
<html:hidden property="adr020BackId" />
<html:hidden property="adr030editAcoSid" />
<html:hidden property="adr010EditAdrSid" />
<html:hidden property="wml040To" />
<html:hidden property="wml040mode" value="2"/>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <h1><gsmsg:write key="mobile.27" /></h1>
</div><!-- /header -->

<div data-role="content">

<logic:equal name="mbhAdr040Form"  property="adr040SelectValue" value="mail">
  <div align="center">
    <gsmsg:write key="mobile.28" />
    <br>
    <div data-role="controlgroup" data-type="horizontal" align="center" class="font_small">
      <input name="adr040yes" value="<gsmsg:write key="mobile.13" />" type="submit" data-inline="true" data-icon="forward"><input name="adr040back" value="<gsmsg:write key="mobile.14" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right">
    </div>
  </div>
</logic:equal>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>