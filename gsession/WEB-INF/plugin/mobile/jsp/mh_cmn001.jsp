<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-mobileHtmlForm.tld" prefix="mblform" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% String gsMobileUrl = jp.groupsession.v3.mbh.GSConstMobileHtml.GS_MOBILE_HOMEPAGE_URL; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.17" />] <gsmsg:write key="mobile.17" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<b><gsmsg:write key="mobile.1" /></b>
<hr>


<mblform:form action="/mobile/mh_cmn001" utn="utn">

<input type="hidden" name="CMD" value="login">

<span style="color: red"><html:errors/></span>

<logic:equal name="mbhCmn001Form" property="licenseFlg" value="0">
<logic:notEqual name="mbhCmn001Form" property="errorFlg" value="1">
<br><gsmsg:write key="cmn.user.id" />
<br><input type="text" name="cmn001Userid" maxlength="20" size="20" tabindex="1">
<br><gsmsg:write key="user.117" />
<br><input type="password" name="cmn001Passwd" maxlength="20" size="20" tabindex="2">
<br><input type="submit" value="<gsmsg:write key="mobile.17" />" class="login_btn" tabindex="3">
</logic:notEqual>
</logic:equal>

<logic:notEqual name="mbhCmn001Form" property="licenseFlg" value="0">
  <div align="center">
    <b><gsmsg:write key="mobile.30" /><br><gsmsg:write key="mobile.31" /></b>
    <br>
    <a rel="external" href="../common/cmn001.do" target="_blank"><gsmsg:write key="mobile.7" /></a>
    <a rel="external" href="<%= gsMobileUrl %>" target="_blank"><gsmsg:write key="mobile.4" /></a>
  </div>
</logic:notEqual>

</mblform:form>

<div id="mh_footer">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_footer.jsp" %>
</div>
</body>
</html:html>