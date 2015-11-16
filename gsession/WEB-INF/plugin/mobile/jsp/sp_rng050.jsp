<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="mobile.21" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rng"; %>
<% thisForm = "mbhRng050Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_rng050">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="id" value="">
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rng040Title" />
<html:hidden property="rng040requestUser" />
<html:hidden property="rng040Content" />
<html:hidden property="rng040InitFlg" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<html:hidden property="rng020pageTop" />
<html:hidden property="rng050mod" />

<logic:notEmpty name="mbhRng050Form" property="rng040apprUser">
<logic:iterate id="apprUser" name="mbhRng050Form" property="rng040apprUser">
  <input type="hidden" name="rng040apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="mbhRng050Form" property="rng040confirmUser">
<logic:iterate id="confirmUser" name="mbhRng050Form" property="rng040confirmUser">
  <input type="hidden" name="rng040confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('rng050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/rng_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="rng.62" /><br>
    <logic:equal name="mbhRng050Form" property="rng050mod" value="0"><gsmsg:write key="mobile.21" /></logic:equal>
    <logic:notEqual name="mbhRng050Form" property="rng050mod" value="0"><gsmsg:write key="rng.35" /><gsmsg:write key="cmn.select" /></logic:notEqual>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div align="center">
  <div class="font_small">
    <html:select property="rng040group" onchange="changeCombo();">
      <html:optionsCollection name="mbhRng050Form" property="rng040groupList" value="value" label="label" />
    </html:select>
  </div>
</div>

<logic:notEmpty name="mbhRng050Form" property="rng040userList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider">
      <div align="center">
        <div class="font_small">
          <logic:equal name="mbhRng050Form" property="rng050mod" value="0"><gsmsg:write key="rng.rng020.02" /></logic:equal>
          <logic:notEqual name="mbhRng050Form" property="rng050mod" value="0"><gsmsg:write key="mobile.23" /></logic:notEqual>
        </div>
      </div>
    </li>
    <bean:define id="mod" value="0" />
    <logic:iterate id="userList" name="mbhRng050Form" property="rng040userList" indexId="idx">

<%--
      <bean:define id="uid" name="userList" property="value" type="java.lang.String"/>
      <html:radio name="mbhRng050Form" property="rng040AddedUsrId" value="<%= uid.toString() %>" /><bean:write name="userList" property="label" /><br>
--%>

      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:notEqual>
      <li data-theme=<bean:write name="liColor" />>
        <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.rng040.MbhRng040Form.PARAM_SELECTADR %><bean:write name="userList" property="value" />');"><bean:write name="userList" property="label" /></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div align="center">
  <div class="font_small">
    <input name="rng050Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back"/>
  </div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>