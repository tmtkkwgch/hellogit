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
<% thisForm = "mbhCmn999Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_cmn999" target="_self">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="CMD" value="" />
<html:hidden property="directURL" />
<html:hidden property="tokenUrl" />
<html:hidden property="tokenFlg" />
<html:hidden property="wtarget" />
<html:hidden property="forwardOK" />
<html:hidden property="forwardCancel" />
<html:hidden property="forwardBack" />
<html:hidden property="forwardClose" />
<logic:notEmpty name="mbhCmn999Form" property="hiddenList">
  <logic:iterate id="item" name="mbhCmn999Form" property="hiddenList" scope="request">
  <input type="hidden" name="<bean:write name="item" property="key"/>" value="<bean:write name="item" property="value"/>">
  </logic:iterate>
</logic:notEmpty>


<logic:equal name="mbhCmn999Form" property="icon" value="0">
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <h1><gsmsg:write key="cmn.warning" /></h1>
</div><!-- /header -->
</logic:equal>
<logic:equal name="mbhCmn999Form" property="icon" value="1">
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <h1><gsmsg:write key="mobile.27" /></h1>
</div><!-- /header -->
</logic:equal>

<div data-role="content">

<bean:write name="mbhCmn999Form" property="message" filter="false"/>
<br>
<logic:equal name="mbhCmn999Form" property="type_popup" value="0">
<logic:equal name="mbhCmn999Form" property="type" value="0">
  <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>">
</logic:equal>
<logic:equal name="mbhCmn999Form" property="type" value="1">
  <div data-role="controlgroup" data-type="horizontal" align="center">
    <input type="submit" name="ok" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="mbhCmn999Form" property="okBtnValue"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"  data-inline="true">
    <input type="submit" name="cansel" value="<gsmsg:write key="cmn.cancel" />"  data-inline="true">
  </div>
</logic:equal>
<logic:equal name="mbhCmn999Form" property="type" value="2">
  <div data-role="controlgroup" data-type="horizontal" align="center">
    <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>" data-inline="true">
    <input type="submit" name="back" value="<gsmsg:write key="cmn.back" />" data-inline="true">
  </div>
</logic:equal>
</logic:equal>

<logic:equal name="mbhCmn999Form" property="type" value="3">
  <div align="center">
    <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>">
  </div>
</logic:equal>

<logic:equal name="mbhCmn999Form" property="type_popup" value="1">
  <input type="submit" name="close" value="<gsmsg:write key="cmn.close" />" >
</logic:equal>

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