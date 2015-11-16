<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="schedule.117" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sch"; %>
<% thisForm = "mbhSch050Form"; %>
</head>
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_sch050">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<input type="hidden" name="id" value="">
<html:hidden property="sch050Cmd" />
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010SchSid" />
<html:hidden property="listMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch040BatchRef" />
<html:hidden property="sch040ReserveGroupSid" />
<html:hidden property="sch040ResBatchRef" />
<html:hidden property="sch040contact" />
<html:hidden property="sch040ResBatchRef" />
<html:hidden property="sch040DataFlg" />
<html:hidden property="reservePluginKbn" />
<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />
<html:hidden property="sch040UsrName" />
<html:hidden property="sch040AddUsrJkbn" />
<html:hidden property="sch040AddDate" />
<html:hidden property="sch040Bgcolor" />
<html:hidden property="sch040Title" />
<html:hidden property="sch040Value" />
<html:hidden property="sch040Biko" />
<html:hidden property="sch040Public" />
<html:hidden property="sch040FrYear" />
<html:hidden property="sch040FrMonth" />
<html:hidden property="sch040FrDay" />
<html:hidden property="sch040FrHour" />
<html:hidden property="sch040FrMin" />
<html:hidden property="sch040ToYear" />
<html:hidden property="sch040ToMonth" />
<html:hidden property="sch040ToDay" />
<html:hidden property="sch040ToHour" />
<html:hidden property="sch040ToMin" />
<html:hidden property="sch040Edit" />
<html:hidden property="sch040DispMod" />
<html:hidden property="sch050DispMod" />
<html:hidden property="sch050DispId" />
<html:hidden property="sch040FrDate" />
<html:hidden property="sch040ToDate" />
<html:hidden property="sch040InitFlg" />

<html:hidden property="sch040AttendKbn" />
<html:hidden property="sch040AttendAnsKbn" />
<html:hidden property="sch040EditDspMode" />
<html:hidden property="sch040EditMailSendKbn" />
<html:hidden property="sch040AttendDelFlg" />


<logic:notEmpty name="mbhSch050Form" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="mbhSch050Form" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch050Form" property="svReserveUsers" scope="request">
<logic:iterate id="resBean" name="mbhSch050Form" property="svReserveUsers" scope="request">
<input type="hidden" name="svReserveUsers" value="<bean:write name="resBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch050Form" property="sch040CompanySid">
  <logic:iterate id="coId" name="mbhSch050Form" property="sch040CompanySid">
    <input type="hidden" name="sch040CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch050Form" property="sch040CompanyBaseSid">
  <logic:iterate id="coId" name="mbhSch050Form" property="sch040CompanyBaseSid">
    <input type="hidden" name="sch040CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch050Form" property="sch040AddressId">
  <logic:iterate id="addressId" name="mbhSch050Form" property="sch040AddressId">
    <input type="hidden" name="sch040AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>



<logic:equal name="mbhSch050Form" property="sch050DispMod" value="usrChange">
  <div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
    <a href="#" onClick="buttonPush('sch050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
      <h1><gsmsg:write key="schedule.108" /><br><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.change" /></h1>
    <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
  </div><!-- /header -->
</logic:equal>
<logic:empty name="mbhSch050Form" property="sch050DispMod">
  <div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
    <a href="#" onClick="buttonPush('sch050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
      <h1><gsmsg:write key="schedule.108" /><br><gsmsg:write key="schedule.117" /></h1>
    <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
  </div><!-- /header -->
</logic:empty>

<div data-role="content">

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>

<%-- 同時登録ユーザ追加　--%>
<logic:empty name="mbhSch050Form" property="sch050DispMod">
<logic:notEmpty name="mbhSch050Form" property="sch040GroupLavel">
<div align="center">
  <html:select property="sch040GroupSid" onchange="changeCombo();">
    <html:optionsCollection name="mbhSch050Form" property="sch040GroupLavel" value="value" label="label" />
  </html:select>
</div>
</logic:notEmpty>

<logic:notEmpty name="mbhSch050Form" property="sch040BelongLavel">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <logic:notEmpty name="mbhSch050Form" property="sch050GroupName">
    <li data-role="list-divider">
      <div align="center">
        <bean:write name="mbhSch050Form" property="sch050GroupName" />
      </div>
    </li>
  </logic:notEmpty>
  <bean:define id="mod" value="0" />
  <logic:iterate id="userList" name="mbhSch050Form" property="sch040BelongLavel" indexId="idx">
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>
      <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.sch040.MbhSch040Form.PARAM_SELECTADR %><bean:write name="userList" property="usrSid" />');"><bean:write name="userList" property="usiSei" /> <bean:write name="userList" property="usiMei" /></a>
    </li>
  </logic:iterate>
</ul>

</logic:notEmpty>

</logic:empty>

<%-- ユーザ変更　--%>
<logic:equal name="mbhSch050Form" property="sch050DispMod" value="usrChange">
<logic:notEmpty name="mbhSch050Form" property="sch050GroupList">
<html:select name="mbhSch050Form" property="sch050GroupSid" styleClass="select01" onchange="changeCombo();">
  <logic:iterate id="gpBean" name="mbhSch050Form" property="sch050GroupList">
    <% boolean gpDisabled = false; %>
    <logic:equal name="gpBean" property="viewKbn" value="false">
    <% gpDisabled = true; %>
    </logic:equal>
    <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
    <html:option value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
  </logic:iterate>
</html:select>
</logic:notEmpty>

<br>
<logic:notEmpty name="mbhSch050Form" property="sch050UserList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:notEmpty name="mbhSch050Form" property="sch050GroupName">
      <li data-role="list-divider">
        <div align="center">
          <bean:write name="mbhSch050Form" property="sch050GroupName" />
       </div>
      </li>
    </logic:notEmpty>
    <bean:define id="mod" value="0" />
    <logic:iterate id="userList" name="mbhSch050Form" property="sch050UserList" indexId="idx">
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:notEqual>

      <li data-theme=<bean:write name="liColor" />>
        <a href="./<bean:write name="mbhSch050Form" property="sch050DispId" />.do?mobileType=1&CMD=addUser&sch010SelectUsrSid=<bean:write name="userList" property="value" />&sch010DspDate=<bean:write name="mbhSch050Form" property="sch010DspDate" />&sch010DspGpSid=<bean:write name="mbhSch050Form" property="sch050GroupSid" />&changeDateFlg=<bean:write name="mbhSch050Form" property="changeDateFlg" />"><bean:write name="userList" property="label" /></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>
</logic:equal>
<div align="center">
  <input name="sch050back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back"/>
</div>


</div><!-- /content -->


</html:form>

</div><!-- /page -->

</body>
</html:html>