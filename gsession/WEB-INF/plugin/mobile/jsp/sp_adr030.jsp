<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="addressbook" /><gsmsg:write key="address.118" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "adr"; %>
<% thisForm = "mbhAdr030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_adr030">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<html:hidden property="adr030editAcoSid" />
<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010EditAdrSid" />
<html:hidden property="adr020BackId" />
<html:hidden property="adr030BackId" />
<bean:define id="maxPage" name="mbhAdr030Form" property="adr030MaxPage" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('adr030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/adr_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="addressbook" /> <br><b><gsmsg:write key="address.118" /></b>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
        <div align="center"><bean:write name="mbhAdr030Form" property="adr030coName" /></div>
  </li>
  <logic:notEmpty name="mbhAdr030Form" property="adr030url">
    <li>
      <a href="<bean:write name="mbhAdr030Form" property="adr030url" />">
      <div class="font_xsmall">■URL</div>
      <bean:write name="mbhAdr030Form" property="adr030url" /></a>
    </li>
  </logic:notEmpty>
</ul>

<logic:notEmpty name="mbhAdr030Form" property="adr030Label">
  <div class="font_small">■<gsmsg:write key="address.10" /></div>
  <div class="font_small" align="center">
    <html:select property="adr030selectLabel" onchange="changeCombo2('searchButton');">
      <html:optionsCollection name="mbhAdr030Form" property="adr030Label" value="value" label="label" />
    </html:select>
  </div>
</logic:notEmpty>

<logic:equal name="mbhAdr030Form" property="adr030SearchFlg" value="1">
<logic:notEmpty name="mbhAdr030Form" property="adr030BaseMdl">
<bean:define id="abaData" name="mbhAdr030Form" property="adr030BaseMdl" />
<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <logic:notEmpty name="abaData" property="adr110abaPostno1">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.postalcode" /></div>
      <bean:write name="abaData" property="adr110abaPostno1" />-<bean:write name="abaData" property="adr110abaPostno2" />
    </li>
  </logic:notEmpty>
  <logic:notEmpty name="abaData" property="adr110abaTdfkName">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.prefectures" /></div>
      <bean:write name="abaData" property="adr110abaTdfkName" />
    </li>
  </logic:notEmpty>
  <logic:notEmpty name="abaData" property="adr110abaAddress1">
    <li>
      <a href="http://maps.google.co.jp/maps?q=<bean:write name="mbhAdr030Form" property="adr030abaAddress1Url" />" />
      <div class="font_xsmall">■<gsmsg:write key="cmn.address" /></div>
      <bean:write name="abaData" property="adr110abaAddress1" /></a>
    </li>
  </logic:notEmpty>
</ul>
</logic:notEmpty>
</logic:equal>

<hr>

<div align="center">
  <logic:messagesPresent message="false">
    <html:errors />
  </logic:messagesPresent>
</div>

<logic:notEmpty name="mbhAdr030Form" property="adr030AdrInfList">
<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center"><gsmsg:write key="cmn.userlist" />
      <logic:greaterThan name="maxPage" value="1">
        (<bean:write name="mbhAdr030Form" property="adr030page" />/<bean:write name="mbhAdr030Form" property="adr030MaxPage" />)
      </logic:greaterThan>
    </div>
  </li>
  <logic:iterate id="adrInfo" name="mbhAdr030Form" property="adr030AdrInfList" indexId="idx">
  <logic:notEqual name="mbhAdr030Form" property="adr030SearchFlg" value="1">
    <li>
      <a href="../mobile/sp_adr020.do?mobileType=1&adr010EditAdrSid=<bean:write name="adrInfo" property="adrSid" />&adr020BackId=adr030&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />"><bean:write name="adrInfo" property="userName" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="adrInfo" property="companyBaseName" /></a>
    </li>
  </logic:notEqual>
  <logic:equal name="mbhAdr030Form" property="adr030SearchFlg" value="1">
    <li>
      <a href="../mobile/sp_adr020.do?mobileType=1&adr010EditAdrSid=<bean:write name="adrInfo" property="adrSid" />&adr020BackId=adr030&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />"><bean:write name="adrInfo" property="userName" /></a>
    </li>
  </logic:equal>
  </logic:iterate>
</ul>
</logic:notEmpty>


<div class="font_xsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEqual name="mbhAdr030Form" property="adr030MaxPage" value="0">
<logic:equal name="mbhAdr030Form" property="adr030page" value="1">
<a href="../mobile/sp_adr030.do?mobileType=1&CMD=nextPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><gsmsg:write key="cmn.next" /></a>
</logic:equal>
<logic:equal name="mbhAdr030Form" property="adr030page" value="<%= String.valueOf(maxPage) %>" >
<a href="../mobile/sp_adr030.do?mobileType=1&CMD=prevPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.previous" /></a>
</logic:equal>
<logic:notEqual name="mbhAdr030Form" property="adr030page" value="1">
<logic:notEqual name="mbhAdr030Form" property="adr030page" value="<%= String.valueOf(maxPage) %>" >
  <a href="../mobile/sp_adr030.do?mobileType=1&CMD=prevPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.previous" /></a><a href="../mobile/sp_adr030.do?mobileType=1&CMD=nextPage&adr030page=<bean:write name="mbhAdr030Form" property="adr030page" />&adr010cmdMode=<bean:write name="mbhAdr030Form" property="adr010cmdMode" />&adr030editAcoSid=<bean:write name="mbhAdr030Form" property="adr030editAcoSid" />&adr030SearchFlg=<bean:write name="mbhAdr030Form" property="adr030SearchFlg" />&adr030selectLabel=<bean:write name="mbhAdr030Form" property="adr030selectLabel" />&adr030BackId=<bean:write name="mbhAdr030Form" property="adr030BackId" />&adr010EditAdrSid=<bean:write name="mbhAdr030Form" property="adr010EditAdrSid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><gsmsg:write key="cmn.next" /></a>
</logic:notEqual>
</logic:notEqual>
</logic:notEqual>
</div>
</div>

<div align="center" class="font_small">
  <input name="adr030backButton" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>