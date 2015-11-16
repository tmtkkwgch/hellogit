<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhUsr030Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shain.info" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_usr030">
<html:hidden property="usr020usid" />
<html:hidden property="usr010cmdMode" />
<html:hidden property="usr010pageNum1" />
<html:hidden property="usr010selectedLabel" />
<html:hidden property="usr010SelectInd" />
<html:hidden property="usr010SearchInd" />
<html:hidden property="usr010SearchTopFlg" />
<html:hidden property="usr010SearchKanaFlg" />
<html:hidden property="wml040To" />
<html:hidden property="wml040mode" value="2"/>

<div align="center">
<gsmsg:write key="cmn.information" />
</div>
<hr>
<logic:equal name="mbhUsr030Form"  property="usr030SelectValue" value="mail">
  <div align="center">
    <gsmsg:write key="mobile.8" />
    <br>
    <br><input name="usr030yes" value="<gsmsg:write key="mobile.13" />" type="submit"><input name="usr030back" value="<gsmsg:write key="mobile.14" />" type="submit">
  </div>
</logic:equal>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>