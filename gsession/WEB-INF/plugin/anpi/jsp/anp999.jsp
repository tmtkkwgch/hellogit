<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="./anpheader_mb000.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="anp.plugin"/></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
</head>

<body>

<html:form action="/anpi/anp999" target="_self">

<html:hidden property="CMD" value="" />
<html:hidden property="retryURL" />
<html:hidden property="directURL" />
<html:hidden property="tokenUrl" />
<html:hidden property="tokenFlg" />
<html:hidden property="urlOK" />
<html:hidden property="urlCancel" />
<html:hidden property="urlBack" />

<logic:notEmpty name="anp999Form" property="hiddenList">
  <logic:iterate id="item" name="anp999Form" property="hiddenList" scope="request">
  <input type="hidden" name="<bean:write name="item" property="key"/>" value="<bean:write name="item" property="value"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="./anpheader_mb001.jsp" %>


<br>
<logic:equal name="anp999Form" property="icon" value="<%= String.valueOf(jp.groupsession.v2.anp.anp999.Anp999Form.ICON_WARN) %>">
<gsmsg:write key="cmn.warning" />
</logic:equal>
<logic:equal name="anp999Form" property="icon" value="<%= String.valueOf(jp.groupsession.v2.anp.anp999.Anp999Form.ICON_INFO) %>">
<gsmsg:write key="cmn.information" />
</logic:equal>
<hr>
<br>
<bean:write name="anp999Form" property="message" filter="false"/>
<br>
<br>

<logic:equal name="anp999Form" property="type" value="<%= String.valueOf(jp.groupsession.v2.anp.anp999.Anp999Form.TYPE_OK) %>">
  <input type="submit" name="ok" value="<bean:write name="anp999Form" property="okBtnValue"/>" style="width: 90px; height: 30px">
</logic:equal>
<logic:equal name="anp999Form" property="type" value="<%= String.valueOf(jp.groupsession.v2.anp.anp999.Anp999Form.TYPE_OKCANCEL) %>">
  <input type="submit" name="ok" value="<bean:write name="anp999Form" property="okBtnValue"/>" style="width: 90px; height: 30px">
  <input type="submit" name="cancel" value="<gsmsg:write key="cmn.cancel" />" style="width: 90px; height: 30px">
</logic:equal>
<logic:equal name="anp999Form" property="type" value="<%= String.valueOf(jp.groupsession.v2.anp.anp999.Anp999Form.TYPE_OKBACK) %>">
  <input type="submit" name="ok" value="<bean:write name="anp999Form" property="okBtnValue"/>" style="width: 90px; height: 30px">
  <input type="submit" name="back" value="<gsmsg:write key="cmn.back" />" style="width: 90px; height: 30px">
</logic:equal>
<logic:equal name="anp999Form" property="type" value="<%= String.valueOf(jp.groupsession.v2.anp.anp999.Anp999Form.TYPE_ERROR_REPORT) %>">
  <input type="submit" name="ok" value="<bean:write name="anp999Form" property="okBtnValue"/>" style="width: 90px; height: 30px">
</logic:equal>

</html:form>

<br><br><br><br><br><br>

</body>
</html:html>