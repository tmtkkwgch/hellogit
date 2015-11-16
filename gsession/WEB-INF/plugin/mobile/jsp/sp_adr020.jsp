<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="addressbook" />(<gsmsg:write key="address.src.2" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "adr"; %>
<% thisForm = "mbhAdr020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_adr020">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden name="mbhAdr020Form" property="adr010cmdMode" />
<html:hidden name="mbhAdr020Form" property="adr010SelectInd" />
<html:hidden name="mbhAdr020Form" property="adr010SearchInd" />
<html:hidden name="mbhAdr020Form" property="adr010page" />
<html:hidden name="mbhAdr020Form" property="adr010SearchTopFlg" />
<html:hidden name="mbhAdr020Form" property="adr010SearchKanaFlg" />
<html:hidden name="mbhAdr020Form" property="adr010selectLabel" />
<html:hidden name="mbhAdr020Form" property="adr020BackId" />
<html:hidden name="mbhAdr020Form" property="adr030editAcoSid" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('adr020back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/adr_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="addressbook" /> <br><b><gsmsg:write key="address.src.2" /></b>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider">
        <div align="center"><bean:write name="mbhAdr020Form" property="adr020unameSei" /> <bean:write name="mbhAdr020Form" property="adr020unameMei" /></div>
    </li>
    <logic:notEmpty name="mbhAdr020Form" property="adr020companyName">
      <li>
        <a href="../mobile/sp_adr030.do?mobileType=1&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr020AcoSid" />&adr030BackId=adr020&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />">
          <div class="font_xsmall">■<gsmsg:write key="cmn.company.name" /></div>
          <bean:write name="mbhAdr020Form" property="adr020companyName" />
        </a>
      </li>
    </logic:notEmpty>
    <%--
    <li>
      <logic:notEmpty name="mbhAdr020Form" property="adr020companyBaseName">
        <div class="font_xsmall">■<gsmsg:write key="address.10" /></div>
        <bean:write name="mbhAdr020Form" property="adr020companyBaseName" />
      </logic:notEmpty>
    </li>
    --%>
    <logic:notEmpty name="mbhAdr020Form" property="adr020tel1">
      <li>
        <a href=tel:<bean:write name="mbhAdr020Form" property="adr020tel1" />>
          <div class="font_xsmall">■<gsmsg:write key="cmn.tel" />1</div>
          <span style="color:blue"><bean:write name="mbhAdr020Form" property="adr020tel1" /></span>
        </a>
      </li>
    </logic:notEmpty>
    <logic:notEmpty name="mbhAdr020Form" property="adr020tel2">
      <li>
        <a href=tel:<bean:write name="mbhAdr020Form" property="adr020tel2" />>
          <div class="font_xsmall">■<gsmsg:write key="cmn.tel" />2</div>
          <span style="color:blue"><bean:write name="mbhAdr020Form" property="adr020tel2" /></span>
        </a>
      </li>
    </logic:notEmpty>
    <logic:notEmpty name="mbhAdr020Form" property="adr020tel3">
      <li>
        <a href=tel:<bean:write name="mbhAdr020Form" property="adr020tel3" />>
          <div class="font_xsmall">■<gsmsg:write key="cmn.tel" />3</div>
          <span style="color:blue"><bean:write name="mbhAdr020Form" property="adr020tel3" /></span>
        </a>
      </li>
    </logic:notEmpty>
    <logic:notEmpty name="mbhAdr020Form" property="adr020address1">
      <li>
        <a href="http://maps.google.co.jp/maps?q=<bean:write name="mbhAdr020Form" property="adr020address1" />" target="_blank" />
          <div class="font_xsmall">■<gsmsg:write key="cmn.address" /></div>
          <bean:write name="mbhAdr020Form" property="adr020address1" />
        </a>
      </li>
    </logic:notEmpty>
    <%--
    <logic:notEmpty name="mbhAdr020Form" property="adr020companyURL">
      <li>
        <div class="font_xsmall">■URL</div>
        <a href="<bean:write name="mbhAdr020Form" property="adr020companyURL" />"><bean:write name="mbhAdr020Form" property="adr020companyURL" /></a>
      </li>
    </logic:notEmpty>
    --%>
    <logic:notEmpty name="mbhAdr020Form" property="adr020mail1">
      <li>
          <logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <a href="../mobile/sp_adr040.do?mobileType=1&adr040SelectValue=mail&adr020mail1=<bean:write name="mbhAdr020Form" property="adr020mail1" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr020Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr020Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr020Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr020Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr020Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr020Form" property="adr010selectLabel" />&adr020BackId=<bean:write name="mbhAdr020Form" property="adr020BackId" />&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr030editAcoSid" />&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />">
        </logic:equal>
        <div class="font_xsmall">■<gsmsg:write key="cmn.mailaddress" />1</div>
        <logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <span style="color:blue"><bean:write name="mbhAdr020Form" property="adr020mail1" /></span></a>
        </logic:equal>
        <logic:notEqual name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <bean:write name="mbhAdr020Form" property="adr020mail1" />
        </logic:notEqual>
      </li>
    </logic:notEmpty>
    <logic:notEmpty name="mbhAdr020Form" property="adr020mail2">
      <li>
          <logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <a href="../mobile/sp_adr040.do?mobileType=1&adr040SelectValue=mail&adr020mail2=<bean:write name="mbhAdr020Form" property="adr020mail2" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr020Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr020Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr020Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr020Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr020Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr020Form" property="adr010selectLabel" />&adr020BackId=<bean:write name="mbhAdr020Form" property="adr020BackId" />&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr030editAcoSid" />&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />">
        </logic:equal>
        <div class="font_xsmall">■<gsmsg:write key="cmn.mailaddress" />2</div>
        <logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <span style="color:blue"><bean:write name="mbhAdr020Form" property="adr020mail2" /></span></a>
        </logic:equal>
        <logic:notEqual name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <bean:write name="mbhAdr020Form" property="adr020mail2" />
        </logic:notEqual>
      </li>
    </logic:notEmpty>
    <logic:notEmpty name="mbhAdr020Form" property="adr020mail3">
      <li>
          <logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <a href="../mobile/sp_adr040.do?mobileType=1&adr040SelectValue=mail&adr020mail3=<bean:write name="mbhAdr020Form" property="adr020mail3" />&adr010cmdMode=<bean:write name="mbhAdr020Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr020Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr020Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr020Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr020Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr020Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr020Form" property="adr010selectLabel" />&adr020BackId=<bean:write name="mbhAdr020Form" property="adr020BackId" />&adr030editAcoSid=<bean:write name="mbhAdr020Form" property="adr030editAcoSid" />&adr010EditAdrSid=<bean:write name="mbhAdr020Form" property="adr010EditAdrSid" />">
        </logic:equal>
        <div class="font_xsmall">■<gsmsg:write key="cmn.mailaddress" />3</div>
        <logic:equal name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <span style="color:blue"><bean:write name="mbhAdr020Form" property="adr020mail3" /></span></a>
        </logic:equal>
        <logic:notEqual name="mbhAdr020Form" property="adr020webMailUse" value="0">
          <bean:write name="mbhAdr020Form" property="adr020mail3" />
        </logic:notEqual>
      </li>
    </logic:notEmpty>
  </ul>
<div align="center" class="font_small">
  <input name="adr020backButton" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>