<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%  String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="mobile.27" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "info"; %>
<% thisForm = "mbhMan002Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/info_menu_icon_single2.gif" class="tl img_border"/>
  <h1><b><gsmsg:write key="mobile.27" /></b><br></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<html:form action="/mobile/sp_man002">
<html:hidden property="mobileType" value="1"/>

<input type="hidden" name="CMD" value="">
<html:hidden name="mbhMan002Form" property="man002binSid" />
<html:hidden name="mbhMan002Form" property="man002SelectedSid" />

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.message" /></div>
    <bean:write name="mbhMan002Form" property="man002Msg" />
  </li>


  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.content" /></div>
    <bean:write name="mbhMan002Form" property="man002Value" filter="false" />
  </li>

  <logic:notEmpty name="mbhMan002Form" property="man002KoukaiList">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="main.exposed" /></div>
        <logic:iterate id="memName" name="mbhMan002Form" property="man002KoukaiList">
          <bean:write name="memName" property="label" /><br>
        </logic:iterate>
    </li>
  </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.registant" /></div>
    <logic:equal name="mbhMan002Form" property="man002UsrJkbn" value="9">
      <del>
        <bean:write name="mbhMan002Form" property="man002NameSei" />&nbsp;<bean:write name="mbhMan002Form" property="man002NameMei" />
      </del>
    </logic:equal>
    <logic:notEqual name="mbhMan002Form" property="man002UsrJkbn" value="9">
      <bean:write name="mbhMan002Form" property="man002NameSei" />&nbsp;<bean:write name="mbhMan002Form" property="man002NameMei" />
    </logic:notEqual>
    </li>

</ul>

<logic:notEmpty name="mbhMan002Form" property="tmpFileList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider" style="background:#ffffff;">
      <div align="center" class="font_xsmall">
        <gsmsg:write key="cmn.attach.file" />
      </div>
    </li>
    <logic:iterate id="fileMdl" name="mbhMan002Form" property="tmpFileList">
      <li>
        <a href="../mobile/sp_man002.do?mobileType=1&CMD=fileDownload&man002imsSid=<bean:write name="mbhMan002Form" property="man002SelectedSid" />&man002binSid=<bean:write name="fileMdl" property="binSid" />" target="_blank"><span class="color_blue"><bean:write name="fileMdl" property="binFileName" />&nbsp;<bean:write name="fileMdl" property="binFileSizeDsp" /></span></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div align="center" class="font_small">
  <input name="man002backButton" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" />
</div>

</div><!-- /content -->

</html:form>

<div data-role="footer" data-theme="<%= usrTheme %>" align="center">
  <div data-role="navbar">
    <ul>
      <li>
        <a href="../mobile/sp_man001.do?mobileType=1" data-icon="home"><div class="font_xxsmall"><gsmsg:write key="cmn.main" /></div></a>
      </li>
      <li>
        <a href="../mobile/sp_cmn001.do?mobileType=1&CMD=logout" data-icon="back" data-iconpos="right"><div class="font_xxsmall"><gsmsg:write key="mobile.11" /></div></a>
      </li>
    </ul>
  </div>
</div>

<div data-role="footer" data-theme="<%= usrTheme %>" data-theme="<%= usrTheme %>" align="center">
  <br>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_footer.jsp" %>
</div><!-- /footer -->

</div><!-- /page -->

</body>
</html:html>