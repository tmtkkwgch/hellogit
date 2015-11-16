<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.message" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_cmn999" target="_self">

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
<br>
<logic:equal name="mbhCmn999Form" property="icon" value="0">
<gsmsg:write key="cmn.warning" />
</logic:equal>
<logic:equal name="mbhCmn999Form" property="icon" value="1">
<gsmsg:write key="cmn.information" />
</logic:equal>
<hr>
<br>
<bean:write name="mbhCmn999Form" property="message" filter="false"/>
<br>
<logic:equal name="mbhCmn999Form" property="type_popup" value="0">
<logic:equal name="mbhCmn999Form" property="type" value="0">
  <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>">
</logic:equal>
<logic:equal name="mbhCmn999Form" property="type" value="1">
  <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>">
  <input type="submit" name="cansel" value="<gsmsg:write key="cmn.cancel" />">
</logic:equal>
<logic:equal name="mbhCmn999Form" property="type" value="2">
  <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>" >
  <input type="submit" name="back" value="<gsmsg:write key="cmn.back" />">
</logic:equal>
</logic:equal>

<logic:equal name="mbhCmn999Form" property="type" value="3">
  <input type="submit" name="ok" value="<bean:write name="mbhCmn999Form" property="okBtnValue"/>" >
</logic:equal>

<logic:equal name="mbhCmn999Form" property="type_popup" value="1">
  <input type="submit" name="close" value="<gsmsg:write key="cmn.close" />" >
</logic:equal>
</html:form>

<br><br><br><br><br><br>

</body>
</html:html>