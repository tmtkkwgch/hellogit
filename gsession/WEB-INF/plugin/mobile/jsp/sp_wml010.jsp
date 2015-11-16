<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "wmlTop"; %>
<% thisForm = "mbhWml010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml010">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:empty name="mbhWml010Form" property="accountCombo">
<br>
<br>
<gsmsg:write key="mobile.26" />
</logic:empty>

<logic:notEmpty name="mbhWml010Form" property="accountCombo">
<bean:define id="wacSid" name="mbhWml010Form" property="wmlAccount" type="java.lang.Integer" />
<% String accountSid = String.valueOf(wacSid.intValue()); %>

<div align="center">
  <div class="font_small">
    <html:select property="wmlAccount" onchange="changeCombo();">
      <html:optionsCollection name="mbhWml010Form" property="accountCombo" value="value" label="label" />
    </html:select>
  </div>
</div>

<br>
<gsmsg:define id="cmnReceive" msgkey="cmn.receive" />
<gsmsg:define id="wml19" msgkey="wml.19" />
<gsmsg:define id="cmnSendPlan" msgkey="wml.211" />
<gsmsg:define id="cmnDraft" msgkey="cmn.draft" />
<gsmsg:define id="cmnTrash" msgkey="cmn.trash" />
<gsmsg:define id="cmnStrage" msgkey="cmn.strage" />
<% String strDirectoryId =""; %>
<logic:notEmpty name="mbhWml010Form" property="directoryList">
  <ul data-role="listview" data-theme="d" data-dividertheme="c">
    <li>
      <a href="./sp_wml040.do?mobileType=1&wmlAccount=<%= accountSid %>" accesskey="0"><img src="../mobile/sp/imgages/mail_sakusei.gif" class="ui-li-icon ui-li-thumb"><gsmsg:write key="cmn.create.new" /></a>
    </li>
    <logic:iterate id="dirData" name="mbhWml010Form" property="directoryList">
      <li>
        <bean:define id="noReadCnt" name="dirData" property="noReadCount" type="java.lang.Long" />
        <bean:define id="directoryId" name="dirData" property="id" type="java.lang.Long" />

        <% boolean noRead = noReadCnt.longValue() > 0; %>
        <% if (noRead) { %><% } %><a href="./sp_wml020.do?mobileType=1&wmlAccount=<%= accountSid %>&wmlDirectory=<bean:write name="dirData" property="id" />" accesskey="0"><bean:write name="dirData" property="name" />
        <logic:equal name="dirData" property="name" value="<%= cmnReceive %>">
          <% strDirectoryId = String.valueOf(directoryId.longValue()); %>
          <img src="../mobile/sp/imgages/mail_jushin.gif" class="ui-li-icon ui-li-thumb">
        </logic:equal>
        <logic:equal name="dirData" property="name" value="<%= wml19 %>">
          <img src="../mobile/sp/imgages/mail_soshin.gif" class="ui-li-icon ui-li-thumb">
        </logic:equal>
        <logic:equal name="dirData" property="name" value="<%= cmnSendPlan %>">
          <img src="../mobile/sp/imgages/mail_sendplan.gif" class="ui-li-icon ui-li-thumb">
        </logic:equal>
        <logic:equal name="dirData" property="name" value="<%= cmnDraft %>">
          <img src="../mobile/sp/imgages/mail_hokan.gif" class="ui-li-icon ui-li-thumb">
        </logic:equal>
        <logic:equal name="dirData" property="name" value="<%= cmnTrash %>">
          <img src="../mobile/sp/imgages/mail_gomi.gif" class="ui-li-icon ui-li-thumb">
        </logic:equal>
        <logic:equal name="dirData" property="name" value="<%= cmnStrage %>">
          <img src="../mobile/sp/imgages/mail_hokan.gif" class="ui-li-icon ui-li-thumb">
        </logic:equal>
        <% if (noRead) { %><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %></a><% if (noRead) { %><% } %>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<br>

<logic:notEmpty name="mbhWml010Form" property="labelList">

<div data-role="collapsible" data-collapsed="false">
  <h2>
    <div align="center">
      <div class="font_small"><gsmsg:write key="cmn.label" /></div>
    </div>
  </h2>

  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <bean:define id="labelCnt" name="mbhWml010Form" property="labelListCount" type="java.lang.Integer" />
    <bean:define id="mod" value="0" />
    <logic:iterate id="labelData" name="mbhWml010Form" property="labelList" indexId="labelIdx">

      <logic:equal name="mod" value="<%= String.valueOf(labelIdx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(labelIdx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:notEqual>

      <li data-theme=<bean:write name="liColor" />>
        <bean:define id="noReadCnt" name="labelData" property="noReadCount" type="java.lang.Long" />
        <% boolean noRead = noReadCnt.longValue() > 0; %>
        <a href="./sp_wml020.do?mobileType=1&wmlAccount=<%= accountSid %>&wmlLabel=<bean:write name="labelData" property="id" />&wmlDirectory=<%= strDirectoryId %>" accesskey="0"><% if (noRead) { %><b><% } %><bean:write name="labelData" property="name" /><% if (noRead) { %><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %><% if (noRead) { %></b><% } %></a>
      </li>
    </logic:iterate>
  </ul>

</div>

</logic:notEmpty>

</logic:notEmpty>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>