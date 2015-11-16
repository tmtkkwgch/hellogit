<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="rng.62" /><gsmsg:write key="cmn.list" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rng"; %>
<% thisForm = "mbhRng020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_rng020">
<input type="hidden" name="BCMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush2('rng020Back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/rng_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="rng.62" /><br>
    <logic:equal name="mbhRng020Form" property="rngProcMode" value="0">
    <gsmsg:write key="cmn.receive" /><gsmsg:write key="cmn.list" />
    </logic:equal>
    <logic:equal name="mbhRng020Form" property="rngProcMode" value="1">
    <gsmsg:write key="rng.48" /><gsmsg:write key="cmn.list" />
    </logic:equal>
    <logic:equal name="mbhRng020Form" property="rngProcMode" value="2">
    <gsmsg:write key="cmn.complete" /><gsmsg:write key="cmn.list" />
    </logic:equal>
    <logic:equal name="mbhRng020Form" property="rngProcMode" value="3">
    <gsmsg:write key="cmn.draft" /><gsmsg:write key="cmn.list" />
    </logic:equal>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">


</b>
<% int mode0 = jp.groupsession.v2.rng.RngConst.RNG_MODE_JYUSIN; %>
<% int mode1 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SINSEI; %>
<% int mode2 = jp.groupsession.v2.rng.RngConst.RNG_MODE_KANRYO; %>
<% int mode3 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SOUKOU; %>

<% int sort_title = jp.groupsession.v2.rng.RngConst.RNG_SORT_TITLE; %>
<% int sort_name = jp.groupsession.v2.rng.RngConst.RNG_SORT_NAME; %>
<% int sort_appl = jp.groupsession.v2.rng.RngConst.RNG_SORT_DATE; %>
<% int sort_jyusin = jp.groupsession.v2.rng.RngConst.RNG_SORT_JYUSIN; %>
<% int sort_kakunin = jp.groupsession.v2.rng.RngConst.RNG_SORT_KAKUNIN; %>
<% int sort_touroku = jp.groupsession.v2.rng.RngConst.RNG_SORT_TOUROKU; %>
<% int order_asc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC; %>
<% int order_desc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC; %>
<% String rngstatus_settlet = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED); %>
<% String rngstatus_reject = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT); %>

<input type="hidden" name="CMD" value="moveSearch">
<input type="hidden" name="rngCmdMode" value="0">
<input type="hidden" name="rngApprMode" value="0">
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />

<bean:define id="maxCnt" name="mbhRng020Form" property="rng020maxPage" />
<bean:define id="procMode" name="mbhRng020Form" property="rngProcMode" />
<% int sMode = ((Integer) procMode).intValue(); %>

<logic:notEmpty name="mbhRng020Form" property="rngDataList">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li data-role="list-divider">
    <div align="center">
      <b>
        <logic:equal name="mbhRng020Form" property="rngProcMode" value="0">
        <gsmsg:write key="cmn.receive" /><gsmsg:write key="cmn.list" />
        </logic:equal>
        <logic:equal name="mbhRng020Form" property="rngProcMode" value="1">
        <gsmsg:write key="rng.48" /><gsmsg:write key="cmn.list" />
        </logic:equal>
        <logic:equal name="mbhRng020Form" property="rngProcMode" value="2">
        <gsmsg:write key="cmn.complete" /><gsmsg:write key="cmn.list" />
        </logic:equal>
        <logic:equal name="mbhRng020Form" property="rngProcMode" value="3">
        <gsmsg:write key="cmn.draft" /><gsmsg:write key="cmn.list" />
        </logic:equal>
      </b>
      <logic:greaterThan name="maxCnt" value="1"><div class="font_small">(<bean:write name="mbhRng020Form" property="rng020pageTop" />/<bean:write name="mbhRng020Form" property="rng020maxPage" />)</div></logic:greaterThan>
    </div>
  </li>


  <bean:define id="mod" value="0" />
  <logic:iterate id="rngData" name="mbhRng020Form" property="rngDataList" indexId="idx" scope="request">

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>

      <% if (sMode == mode0) { %>
        <% String apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPR); %>
        <logic:equal name="rngData" property="rncType" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_RNCTYPE_APPL) %>">
          <% apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPL); %>
        </logic:equal>
        <a href="./sp_rng030.do?mobileType=1&rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />">
        <div class="font_small">■<gsmsg:write key="cmn.title" />：</div><b><bean:write name="rngData" property="rngTitle" /></b>
        <br>
        <div class="font_small">
          ■<gsmsg:write key="rng.47" />：<bean:write name="rngData" property="apprUser" />
          <br>
          ■<gsmsg:write key="rng.application.date" />:<bean:write name="rngData" property="strRngAppldate" />
          <br>
          ■<gsmsg:write key="cmn.received.date" />:<bean:write name="rngData" property="strRcvDate" />
        </div>
          </a>
      <% } else if (sMode == mode1) { %>
        <a href="./sp_rng030.do?mobileType=1&rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />">
        <div class="font_small">■<gsmsg:write key="cmn.title" />：</div><bean:write name="rngData" property="rngTitle" />
        <br>
        <div class="font_small">
          ■<gsmsg:write key="rng.47" />：<bean:write name="rngData" property="apprUser" />
          <br>
          ■<gsmsg:write key="rng.application.date" />:<bean:write name="rngData" property="strRngAppldate" />
          <logic:notEmpty name="rngData" property="strLastManageDate">
            <br>
            ■<gsmsg:write key="rng.105" />:<bean:write name="rngData" property="strLastManageDate" />
          </logic:notEmpty>
        </div>
          </a>
      <% } else if (sMode == mode2) { %>

        <% String rngStatus = "&nbsp;"; %>
        <gsmsg:define id="textKessai" msgkey="rng.64" />
        <gsmsg:define id="textKyakka" msgkey="rng.65" />
        <logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_settlet %>">
          <% rngStatus = textKessai; %>
        </logic:equal>
        <logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_reject %>">
          <% rngStatus = textKyakka; %>
        </logic:equal>
        <a href="./sp_rng030.do?mobileType=1&rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />">
        <span class="font_small">●</span><%= rngStatus %>
        <div class="font_small">■<gsmsg:write key="cmn.title" />：</div><bean:write name="rngData" property="rngTitle" />
        <br>
        <div class="font_small">
          ■<gsmsg:write key="rng.47" />：<bean:write name="rngData" property="apprUser" />
          <br>
          ■<gsmsg:write key="rng.application.date" />:<bean:write name="rngData" property="strRngAppldate" />
          <br>
          ■<gsmsg:write key="rng.105" />:<bean:write name="rngData" property="strLastManageDate" />
        </div>
        </a>
      <% } else if (sMode == mode3) { %>
        <a href="./sp_rng040.do?mobileType=1&rngSid=<bean:write name="rngData" property="rngSid" />&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rngCmdMode=1&CMD=rngEdit&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />">
        <div class="font_small">■<gsmsg:write key="cmn.title" />：</div><bean:write name="rngData" property="rngTitle" />
        <br>
        <div class="font_small">
          ■<gsmsg:write key="rng.37" />:<bean:write name="rngData" property="strMakeDate" />
        </div>
        </a>
      <% } %>

    </li>
  </logic:iterate>
</ul>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEmpty name="mbhRng020Form" property="pageList">
  <logic:greaterThan name="maxCnt" value="1">
  <logic:equal name="mbhRng020Form" property="rng020pageTop" value="1">
    <a href="./sp_rng020.do?mobileType=1&CMD=nextPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:equal>
  <logic:notEqual name="mbhRng020Form" property="rng020pageTop" value="1">
  <logic:notEqual name="mbhRng020Form" property="rng020pageTop" value="<%= maxCnt.toString() %>">
    <a href="./sp_rng020.do?mobileType=1&CMD=prevPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a><a href="./sp_rng020.do?mobileType=1&CMD=nextPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhRng020Form" property="rng020pageTop" value="<%= maxCnt.toString() %>">
    <a href="./sp_rng020.do?mobileType=1&CMD=prevPage&rngProcMode=<bean:write name="mbhRng020Form" property="rngProcMode" />&rng020pageTop=<bean:write name="mbhRng020Form" property="rng020pageTop" />&rng020sortKey=<bean:write name="mbhRng020Form" property="rng020sortKey" />&rng020orderKey=<bean:write name="mbhRng020Form" property="rng020orderKey" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a>
  </logic:equal>
  </logic:greaterThan>
</logic:notEmpty>
</div>

<div align="center">
  <input name="rng020back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>