<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.add" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "cir"; %>
<% thisForm = "mbhCir060Form"; %>
</head>
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_cir060">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<input type="hidden" name="id" value="">
<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<html:hidden property="cir020pageNum2" />
<html:hidden property="cir050pluginId" />
<html:hidden property="cir050memoPeriod" />
<html:hidden property="cir050title" />
<html:hidden property="cir050value" />
<html:hidden property="cir050show" />
<html:hidden property="cir050memoKbn" />
<html:hidden property="cir050limitDay" />
<html:hidden property="cir050InitFlg" />

<html:hidden property="cir060dspId" />
<html:hidden property="cir020selectInfSid" />

<logic:notEmpty name="mbhCir060Form" property="cir050userSid" scope="request">
<logic:iterate id="users" name="mbhCir060Form" property="cir050userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cir050userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir060Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir060Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('cir060back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/cir_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cir.5" /><br>
    <gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.add" />
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhCir060Form" property="cir060GroupList">
<html:select name="mbhCir060Form" property="cir060GroupSid" styleClass="select01" onchange="changeCombo();">
  <html:optionsCollection name="mbhCir060Form" property="cir060GroupList" value="value" label="label" />
</html:select>
</logic:notEmpty>


<logic:notEmpty name="mbhCir060Form" property="cir060GroupList">
<logic:notEqual name="mbhCir060Form" property="cir060GroupSid" value="cac">
  <logic:notEmpty name="mbhCir060Form" property="cir060UserList">
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <logic:iterate id="userList" name="mbhCir060Form" property="cir060UserList" indexId="idx">
      <li>
        <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.cir060.MbhCir060Form.PARAM_SELECTADR %><bean:write name="userList" property="usrSid" />');"><bean:write name="userList" property="usiSei" />&nbsp;&nbsp;<bean:write name="userList" property="usiMei" /></a>
      </li>
      </logic:iterate>
    </ul>
  </logic:notEmpty>
</logic:notEqual>

<logic:equal name="mbhCir060Form" property="cir060GroupSid" value="cac">
  <logic:notEmpty name="mbhCir060Form" property="cir060UserList">
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <logic:iterate id="userList" name="mbhCir060Form" property="cir060UserList" indexId="idx">
      <li>
        <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.cir060.MbhCir060Form.PARAM_SELECTADR %>cac<bean:write name="userList" property="cacSid" />');"><bean:write name="userList" property="cacName" /></a>
      </li>
      </logic:iterate>
    </ul>
  </logic:notEmpty>
</logic:equal>
</logic:notEmpty>

<div align="center">
  <input name="cir060Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back">
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>