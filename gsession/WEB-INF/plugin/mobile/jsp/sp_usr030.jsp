<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shain.info" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "usr"; %>
<% thisForm = "mbhUsr030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_usr030">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
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

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('usr030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
  <h1><gsmsg:write key="mobile.27" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:equal name="mbhUsr030Form"  property="usr030SelectValue" value="mail">
  <div align="center">
    <gsmsg:write key="mobile.28" />
    <br>
    <div data-role="controlgroup" data-type="horizontal" align="center" class="font_small">
      <input name="usr030yes" value="<gsmsg:write key="mobile.13" />" type="submit" data-inline="true" data-icon="forward"><input name="usr030back" value="<gsmsg:write key="mobile.14" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right">
    </div>
  </div>
</logic:equal>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>