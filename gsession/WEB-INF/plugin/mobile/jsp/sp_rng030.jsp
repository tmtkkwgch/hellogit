<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<%@ page import="jp.groupsession.v2.rng.RngConst" %>
<%@ page import="jp.groupsession.v2.rng.rng030.Rng030Form" %>

<% String maxLengthContent = String.valueOf(RngConst.MAX_LENGTH_CONTENT); %>
<% String maxLengthCmt = String.valueOf(RngConst.MAX_LENGTH_COMMENT); %>
<% int cmdmode_view = Rng030Form.CMDMODE_VIEW; %>
<% int cmdmode_appr = Rng030Form.CMDMODE_APPR; %>
<% int cmdmode_confirm = Rng030Form.CMDMODE_CONFIRM; %>
<% int cmdmode_adminappr = Rng030Form.CMDMODE_ADMINAPPR; %>
<% String apprMode_appl = String.valueOf(RngConst.RNG_APPRMODE_APPL); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="sml.sml030.08" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rng"; %>
<% thisForm = "mbhRng030Form"; %>
</head>

<bean:define id="procMode" name="mbhRng030Form" property="rngProcMode" type="java.lang.Integer" />
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_rng030">

<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngApprMode" />
<html:hidden property="rng020pageTop" />
<html:hidden property="rng020pageBottom" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<input type="hidden" name="rng030fileId" value="">
<logic:notEqual name="mbhRng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
  <html:hidden property="rng030ViewTitle" />
</logic:notEqual>


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('rng030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/rng_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="rng.62" /><br><gsmsg:write key="sml.sml030.08" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.status" />：</div>
    <bean:define id="rngStatus" name="mbhRng030Form" property="rng030Status" />
    <% if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_REQUEST) { %>
    <gsmsg:write key="rng.48" />
    <% } else if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED) { %>
    <gsmsg:write key="rng.64" />
    <% } else if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT) { %>
    <gsmsg:write key="rng.65" />
    <% } else { %>
    &nbsp;
    <% } %>
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.title" />：</div><bean:write name="mbhRng030Form" property="rng030ViewTitle" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="rng.47" />：</div><bean:write name="mbhRng030Form" property="rng030apprUser" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="rng.37" />:</div>
    <bean:write name="mbhRng030Form" property="rng030makeDate" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.content" /></div>
    <bean:write name="mbhRng030Form" property="rng030ViewContent" filter="false" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="rng.24" /></div>
    <logic:iterate id="apprUser" name="mbhRng030Form" property="channelList" indexId="idx">
    <bean:define id="channelStatus" name="apprUser" property="rncStatus" />

      <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR) { %>
        <gsmsg:write key="rng.41" />&nbsp;&nbsp;&nbsp;
      <% } else if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_DENIAL) { %>
    <gsmsg:write key="rng.rng030.12" />&nbsp;&nbsp;&nbsp;
      <% } else {%>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <% } %>

      <logic:equal name="apprUser" property="delUser" value="true">
        <strike><bean:write name="apprUser" property="userName" /></strike>
      </logic:equal>
      <logic:equal name="apprUser" property="delUser" value="false">
        <bean:write name="apprUser" property="userName" />
      </logic:equal>

      <logic:equal name="apprUser" property="ringiUse" value="false">
        <logic:notEmpty name="apprUser" property="rncComment"><br></logic:notEmpty>
        <span style="color:red;"><gsmsg:write key="rng.rng030.01" /></span>
      </logic:equal>
      <br>
    </logic:iterate>
  </li>
  <logic:notEmpty name="mbhRng030Form" property="confirmChannelList">
  <li>
    <div class="font_small">■<gsmsg:write key="rng.35" /></div>
    <br>
    <logic:iterate id="confirmUser" name="mbhRng030Form" property="confirmChannelList" indexId="idx">
    <bean:define id="channelStatus" name="confirmUser" property="rncStatus" />

      <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_CONFIRMATION) { %>
    <gsmsg:write key="cmn.check" />&nbsp;&nbsp;
      <% } else {%>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <% } %>

      <logic:equal name="confirmUser" property="delUser" value="true">
        <strike><bean:write name="confirmUser" property="userName" /></strike>
      </logic:equal>
      <logic:equal name="confirmUser" property="delUser" value="false">
        <bean:write name="confirmUser" property="userName" />
      </logic:equal>


      <logic:equal name="confirmUser" property="ringiUse" value="false">
        <logic:notEmpty name="confirmUser" property="rncComment"><br></logic:notEmpty>
        <span style="color:red;"><gsmsg:write key="rng.rng030.01" /></span>
      </logic:equal>
    <br>
    </logic:iterate>
  </li>
  </logic:notEmpty>

  <logic:notEmpty name="mbhRng030Form" property="tmpFileList">
    <li>
      <div class="font_small" align="center"><gsmsg:write key="cmn.attached" /></div>
    </li>
      <logic:iterate id="tmpFile" name="mbhRng030Form" property="tmpFileList" indexId="idx" scope="request">
        <li>
          <a href="../mobile/sp_rng030.do?mobileType=1&CMD=fileDownload&rngSid=<bean:write name="mbhRng030Form" property="rngSid" />&rng030fileId=<bean:write name="tmpFile" property="binSid" />"><span class="color_blue"><bean:write name="tmpFile" property="binFileName" />&nbsp;<bean:write name="tmpFile" property="binFileSizeDsp" /></span></a>
        </li>
      </logic:iterate>
    </logic:notEmpty>
  </li>
</ul>

<div align="center">
  <logic:equal name="mbhRng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_appr) %>">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <input name="rng030approval" value="<gsmsg:write key="rng.41" />" type="submit" data-inline="true" data-icon="check" />
      <input name="rng030reject" value="<gsmsg:write key="rng.22" />" type="submit" data-inline="true" data-icon="delete" data-iconpos="right"/>
    </div>
    <logic:equal name="mbhRng030Form" property="rng030rftBtnFlg" value="1">
      <div class="font_small"><input name="rng030reflection" value="<gsmsg:write key="rng.rng030.08" />" type="submit" data-inline="true" data-icon="refresh" data-iconpos="right" /></div>
    </logic:equal>
  </logic:equal>
</div>

<logic:equal name="mbhRng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_confirm) %>">
  <div align="center">
    <input name="rng030confirmation" value="<gsmsg:write key="cmn.check" />" type="submit" data-inline="true" data-icon="check" />
  </div>
</logic:equal>

<logic:equal name="mbhRng030Form" property="rng030compBtnFlg" value="1">
  <div align="center">
    <input name="rng030complete" value="<gsmsg:write key="cmn.complete" />" type="submit" data-inline="true" data-icon="check" />
  </div>
</logic:equal>

<br>

<div align="center">
  <input name="rng030back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" />
</div>


</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>