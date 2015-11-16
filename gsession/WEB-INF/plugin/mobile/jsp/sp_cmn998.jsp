<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.message" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% thisForm = "mbhCmn998Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_cmn998" target="_self">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="CMD" value="" />
<html:hidden property="tokenUrl" />
<html:hidden property="tokenFlg" />
<html:hidden property="wtarget" />
<html:hidden property="forwardOK" value="sp_gf_domain_logout"/>

<logic:notEmpty name="mbhCmn998Form" property="hiddenList">
  <logic:iterate id="item" name="mbhCmn998Form" property="hiddenList" scope="request">
    <input type="hidden" name="<bean:write name="item" property="key"/>" value="<bean:write name="item" property="value"/>">
  </logic:iterate>
</logic:notEmpty>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <h1><gsmsg:write key="cmn.warning" /></h1>
</div><!-- /header -->

<div data-role="content">

<bean:write name="mbhCmn998Form" property="message" filter="false"/>
<br>

<div align="center">
  <input type="submit" name="ok" value="<bean:write name="mbhCmn998Form" property="okBtnValue"/>">
</div>

</div><!-- /content -->

<div data-role="footer" data-theme="<%= usrTheme %>">
  <br>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_footer.jsp" %>
</div><!-- /footer -->

</html:form>

</div><!-- /page -->
<br><br><br><br><br><br>

</body>
</html:html>