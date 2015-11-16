<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="ntp.1" /> ユーザ変更</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp050Form"; %>
</head>
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_ntp050">

<input type="hidden" name="mobileType" value="1">
<html:hidden property="ntp050Cmd" />
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="ntp010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp050DispMod" />
<html:hidden property="ntp050DispId" />



<logic:equal name="mbhNtp050Form" property="ntp050DispMod" value="usrChange">
  <div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
    <a href="#" onClick="buttonPush('sch050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
      <h1><gsmsg:write key="ntp.1" /><br><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.change" /></h1>
    <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
  </div><!-- /header -->
</logic:equal>
<logic:empty name="mbhNtp050Form" property="ntp050DispMod">
  <div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
    <a href="#" onClick="buttonPush('sch050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
      <h1><gsmsg:write key="ntp.1" /><br><gsmsg:write key="schedule.117" /></h1>
    <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
  </div><!-- /header -->
</logic:empty>



<div data-role="content">

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>


<%-- ユーザ変更　--%>
<logic:equal name="mbhNtp050Form" property="ntp050DispMod" value="usrChange">
<logic:notEmpty name="mbhNtp050Form" property="ntp050GroupList">
<div align="center">
<html:select name="mbhNtp050Form" property="ntp050GroupSid" styleClass="select01" onchange="changeCombo();">
  <html:optionsCollection name="mbhNtp050Form" property="ntp050GroupList" value="value" label="label" />
</html:select>
</div>
</logic:notEmpty>


<logic:notEmpty name="mbhNtp050Form" property="ntp050UserList">
<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
<logic:iterate id="userList" name="mbhNtp050Form" property="ntp050UserList" indexId="idx">
<li>
<a href="./<bean:write name="mbhNtp050Form" property="ntp050DispId" />.do?mobileType=1&CMD=addUser&ntp010SelectUsrSid=<bean:write name="userList" property="value" />&ntp010DspDate=<bean:write name="mbhNtp050Form" property="ntp010DspDate" />&ntp010DspGpSid=<bean:write name="mbhNtp050Form" property="ntp050GroupSid" />&changeDateFlg=<bean:write name="mbhNtp050Form" property="changeDateFlg" />"><bean:write name="userList" property="label" /></a><br>
</li>
</logic:iterate>
</ul>
</logic:notEmpty>
</logic:equal>
<div align="center">
<input name="ntp050back" value="<gsmsg:write key="cmn.back" />" type="submit"  data-inline="true" data-role="button" data-icon="back" />
</div>
</div>
</div><!-- /content -->


</html:form>

</div><!-- /page -->

</body>
</html:html>