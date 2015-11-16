<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhAdr040Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="addressbook" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_adr040">

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

<div align="center">
<gsmsg:write key="cmn.information" />
</div>
<hr>
<logic:equal name="mbhAdr040Form"  property="adr040SelectValue" value="mail">
  <div align="center">
    <gsmsg:write key="mobile.8" />
    <br>
    <br><input name="adr040yes" value="<gsmsg:write key="mobile.13" />" type="submit"><input name="adr040back" value="<gsmsg:write key="mobile.14" />" type="submit">
  </div>
</logic:equal>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>