<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="jp.groupsession.v2.cmn.GSConstSchedule"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-jqselect.tld" prefix="jquery" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<%-- 定数値 --%>
<%
  int editConfOwn          = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_OWN;
  int editConfGroup        = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_GROUP;
  int dspPublic            = jp.groupsession.v2.cmn.GSConstSchedule.DSP_PUBLIC;
  int dspNotPublic         = jp.groupsession.v2.cmn.GSConstSchedule.DSP_NOT_PUBLIC;
  int dspYoteiari          = jp.groupsession.v2.cmn.GSConstSchedule.DSP_YOTEIARI;
  int dspBelongGroup       = jp.groupsession.v2.cmn.GSConstSchedule.DSP_BELONG_GROUP;
%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="mobile.19" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sch"; %>
<% thisForm = "mbhSch020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_sch020">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />


<html:hidden property="sch010DspDate" />

<bean:define id="fromHour" name="mbhSch020Form" property="sch020FromHour" scope="request"/>
<bean:define id="toHour" name="mbhSch020Form" property="sch020ToHour" scope="request"/>
<bean:define id="totalCols" name="mbhSch020Form" property="sch020TotalCols" scope="request"/>
<bean:define id="adminKbn" name="mbhSch020Form" property="adminKbn" scope="request"/>
<bean:define id="memoriCount" name="mbhSch020Form" property="sch020MemoriCount" scope="request"/>

<div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/sch_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="schedule.108" /><br><gsmsg:write key="mobile.19" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div align="center">

  <jquery:jqselect property="sch010DspGpSid" styleClass="select01" onchange="changeGroupCombo();">
  <logic:notEmpty name="mbhSch020Form" property="sch010GpLabelList" scope="request">
  <logic:iterate id="gpBean" name="mbhSch020Form" property="sch010GpLabelList" scope="request">
  <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
  <% boolean gpDisabled = false; %>
  <logic:equal name="gpBean" property="viewKbn" value="false">
  <% gpDisabled = true; %>
  </logic:equal>
  <logic:equal name="gpBean" property="styleClass" value="0">
  <html:option value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
  </logic:equal>

  <logic:notEqual name="gpBean" property="styleClass" value="0">
  <html:option styleClass="select03" value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
  </logic:notEqual>

  </logic:iterate>
  </logic:notEmpty>

  <!--html:optionsCollection name="mbhSch020Form" property="sch010GpLabelList" value="value" label="label" /-->
  </jquery:jqselect>
</div>

<br>
<%--
<input name="groupButton" value="<gsmsg:write key="cmn.select" />" type="submit">

<br>
<hr>
--%>

<!-- スケジュール表示  -->
<div class="title_1" align="center">
  <b><div class="font_small"><bean:write name="mbhSch020Form" property="sch020StrDate" /></div></b>
</div>

<bean:define id="schAccessGroupFlg" name="mbhSch020Form" property="sch020accessGroup" type="java.lang.Boolean" />
<bean:define id="myGroupFlg" name="mbhSch020Form" property="sch020MyGroupFlg" type="java.lang.Integer" />
<% boolean schMyGroupFlg = (myGroupFlg != null?myGroupFlg.intValue() == 0:false); %>
<!-- グループ、本人 -->
<!-- スケジュール情報 -->
<logic:notEmpty name="mbhSch020Form" property="sch010TopList" scope="request">

<logic:iterate id="weekMdl" name="mbhSch020Form" property="sch010TopList" scope="request" indexId="idx">
<bean:define id="usrMdl" name="weekMdl" property="sch010UsrMdl"/>

  <logic:notEmpty name="weekMdl" property="sch010SchList">
  <logic:iterate id="dayMdl" name="weekMdl" property="sch010SchList">

    <logic:empty name="dayMdl" property="schDataList">
      <div align="center">
        <p class="btn_fake"><b><bean:write name="usrMdl" property="usrName"/></b></p>
      </div>
      <div align="center" class="font_xsmall">
        <% if (idx == 0) { %>
          <% if (schMyGroupFlg) { %>
            <% if (schAccessGroupFlg.booleanValue()) { %>
            <logic:equal name="mbhSch020Form" property="registFlg" value="<%= String.valueOf(GSConstSchedule.SSP_AUTH_EDIT) %>">
            <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />&sch040DispMod=1&changeDateFlg=<bean:write name="mbhSch020Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.entry" /></div></a>
            </logic:equal>
            <% } %>
          <% } else { %>
            <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="usrMdl" property="usrSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />&changeDateFlg=<bean:write name="mbhSch020Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.individual" /><gsmsg:write key="cmn.entry" /></div></a>
          <% } %>
        <% } else {%>
          <logic:equal name="mbhSch020Form" property="sch020MyGroupFlg" value="0">
            <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="usrMdl" property="usrSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />&changeDateFlg=<bean:write name="mbhSch020Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.individual" /><gsmsg:write key="cmn.entry" /></div></a>
          </logic:equal>
        <% } %>
      </div>
    </logic:empty>

    <logic:notEmpty name="dayMdl" property="schDataList">

    <div data-role="collapsible" data-collapsed="false">
      <h2>
        <div align="center">
          <bean:write name="usrMdl" property="usrName"/>
        </div>
      </h2>

      <div align="center" class="font_xsmall">
        <% if (idx == 0) { %>
          <% if (schMyGroupFlg) { %>
            <% if (schAccessGroupFlg.booleanValue()) { %>
            <logic:equal name="mbhSch020Form" property="registFlg" value="<%= String.valueOf(GSConstSchedule.SSP_AUTH_EDIT) %>">
            <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />&sch040DispMod=1" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.entry" /></div></a>
            </logic:equal>
            <% } else if (!schAccessGroupFlg.booleanValue()) { %>
              <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.individual" /><gsmsg:write key="cmn.entry" /></div></a>
            <% } %>
          <% } else { %>
            <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.individual" /><gsmsg:write key="cmn.entry" /></div></a>
          <% } %>
        <% } else {%>
          <% if (schMyGroupFlg) { %>
            <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=2&sch010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.individual" /><gsmsg:write key="cmn.entry" /></div></a>
          <% } %>
        <% } %>
      </div>

    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

      <bean:define id="mod" value="0" />

      <logic:iterate id="schMdl" name="dayMdl" property="schDataList" indexId="idx2">

        <logic:equal name="mod" value="<%= String.valueOf(idx2.intValue() % 2) %>">
          <bean:define id="liColor" value="c" />
        </logic:equal>
        <logic:notEqual name="mod" value="<%= String.valueOf(idx2.intValue() % 2) %>">
          <bean:define id="liColor" value="d" />
        </logic:notEqual>

        <li data-theme=<bean:write name="liColor" />>

          <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
          <bean:define id="u_schsid" name="schMdl" property="schSid" type="java.lang.Integer" />
          <bean:define id="u_date" name="dayMdl" property="schDate"  type="java.lang.String" />
          <bean:define id="u_public" name="schMdl" property="public"  type="java.lang.Integer" />
          <bean:define id="u_kjnEdKbn" name="schMdl" property="kjnEdKbn"  type="java.lang.Integer" />
          <bean:define id="u_grpEdKbn" name="schMdl" property="grpEdKbn"  type="java.lang.Integer" />

          <%
            String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
            String tipskey2 = ((Integer)pageContext.getAttribute("u_schsid",PageContext.PAGE_SCOPE)).toString();
            String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
            String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
            int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
            int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
            int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
          %>

          <!--公開-->
          <%
          if ((publicType == dspPublic) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
          %>

          <a href="../mobile/sp_sch040.do?mobileType=1&cmd=edit&sch010SchSid=<bean:write name="schMdl" property="schSid" />&sch010SelectUsrSid=<bean:write name="usrMdl" property="usrSid"/>&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=<bean:write name="usrMdl" property="usrKbn" />&dspMod=2&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />">
          <logic:notEmpty name="schMdl" property="time">
          <div class="font_xsmall"><span style="color:#ff0000"><bean:write name="schMdl" property="time" /></span></div>
          </logic:notEmpty>
          <!--タイトルカラー設定-->
          <logic:equal name="schMdl" property="bgColor" value="0">
          <span style="color: #0000FF;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="1">
          <span style="color: #0000FF;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="2">
          <span style="color: #FF0000;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="3">
          <span style="color: #009900;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="4">
          <span style="color: #ff9900;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="5">
          <span style="color: #333333;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="6">
          <span style="color: #000080;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="7">
          <span style="color: #800000;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="8">
          <span style="color: #008080;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="9">
          <span style="color: #808080;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="10">
          <span style="color: #008DCB;">
          </logic:equal>
          <bean:write name="schMdl" property="title" />
          </span>
          </a>

          <%
           } else {
          %>

          <!--非公開-->
          <span class="sc_nolink">
            <logic:notEmpty name="schMdl" property="time">
            <div class="font_xsmall"><span style="color:#ff0000"><bean:write name="schMdl" property="time" /></span></div>
            </logic:notEmpty>
            <bean:write name="schMdl" property="title" />
          </span>

          <%
           }
          %>

        </li>

      </logic:iterate>

    </ul>
    </div>
    </logic:notEmpty>
  </logic:iterate>
  </logic:notEmpty>

</logic:iterate>
</logic:notEmpty>

<!-- グループメンバー -->
<!-- スケジュール情報 -->
<logic:notEmpty name="mbhSch020Form" property="sch010BottomList" scope="request">
<logic:iterate id="weekMdl" name="mbhSch020Form" property="sch010BottomList" scope="request">
<bean:define id="usrMdl" name="weekMdl" property="sch010UsrMdl"/>

  <logic:notEmpty name="weekMdl" property="sch010SchList">
  <logic:iterate id="dayMdl" name="weekMdl" property="sch010SchList">

    <logic:empty name="dayMdl" property="schDataList">
      <div align="center">
        <p class="btn_fake"><b><bean:write name="usrMdl" property="usrName"/></b></p>
      </div>
    </logic:empty>

    <logic:notEmpty name="dayMdl" property="schDataList">

    <div data-role="collapsible" data-collapsed="false">
      <h2>
        <div align="center">
          <bean:write name="usrMdl" property="usrName"/>
        </div>
      </h2>
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

      <bean:define id="mod2" value="0" />
      <logic:iterate id="schMdl" name="dayMdl" property="schDataList" indexId="idx3">

       <logic:equal name="mod2" value="<%= String.valueOf(idx3.intValue() % 2) %>">
          <bean:define id="liColor" value="c" />
        </logic:equal>
        <logic:notEqual name="mod2" value="<%= String.valueOf(idx3.intValue() % 2) %>">
          <bean:define id="liColor" value="d" />
        </logic:notEqual>

        <li data-theme=<bean:write name="liColor" />>

          <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
          <bean:define id="u_schsid" name="schMdl" property="schSid" type="java.lang.Integer" />
          <bean:define id="u_date" name="dayMdl" property="schDate"  type="java.lang.String" />
          <bean:define id="u_public" name="schMdl" property="public"  type="java.lang.Integer" />
          <bean:define id="u_kjnEdKbn" name="schMdl" property="kjnEdKbn"  type="java.lang.Integer" />
          <bean:define id="u_grpEdKbn" name="schMdl" property="grpEdKbn"  type="java.lang.Integer" />

          <%
            String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
            String tipskey2 = ((Integer)pageContext.getAttribute("u_schsid",PageContext.PAGE_SCOPE)).toString();
            String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
            String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
            int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
            int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
            int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
          %>
          <!--公開-->
          <%
          if ((publicType == dspPublic) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
          %>
          <logic:notEmpty name="schMdl" property="time">
          <div class="font_xsmall"><span style="color:#ff0000"><bean:write name="schMdl" property="time" /></span></div>
          </logic:notEmpty>
          <a href="../mobile/sp_sch040.do?mobileType=1&cmd=edit&sch010SchSid=<bean:write name="schMdl" property="schSid" />&sch010SelectUsrSid=<bean:write name="usrMdl" property="usrSid"/>&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&dspMod=2&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />">
          <!--タイトルカラー設定-->
          <logic:equal name="schMdl" property="bgColor" value="0">
          <span style="color: #0000FF;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="1">
          <span style="color: #0000FF;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="2">
          <span style="color: #FF0000;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="3">
          <span style="color: #009900;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="4">
          <span style="color: #ff9900;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="5">
          <span style="color: #333333;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="6">
          <span style="color: #000080;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="7">
          <span style="color: #800000;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="8">
          <span style="color: #008080;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="9">
          <span style="color: #808080;">
          </logic:equal>
          <logic:equal name="schMdl" property="bgColor" value="10">
          <span style="color: #008DCB;">
          </logic:equal>
          <bean:write name="schMdl" property="title" />
          </span>
          </a>

          <%
           } else {
          %>

          <!--非公開-->
          <span class="sc_nolink">
            <logic:notEmpty name="schMdl" property="time">
            <div class="font_xsmall"><span style="color:#000000"><bean:write name="schMdl" property="time" /></span></div>
            </logic:notEmpty>
            <span style="color:#000000"><bean:write name="schMdl" property="title" /></span>
          </span>

          <%
           }
          %>

        </li>

      </logic:iterate>
    </ul>
    </div>
    </logic:notEmpty>

  </logic:iterate>
  </logic:notEmpty>

</logic:iterate>
</logic:notEmpty>

<br>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <a href="../mobile/sp_sch020.do?mobileType=1&CMD=move_ld&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
  <a href="../mobile/sp_sch020.do?mobileType=1&CMD=today&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
  <a href="../mobile/sp_sch020.do?mobileType=1&CMD=move_rd&sch010DspDate=<bean:write name="mbhSch020Form" property="sch010DspDate" />&sch010DspGpSid=<bean:write name="mbhSch020Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
</div>

<br>

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li><a href="../mobile/sp_sch030.do?mobileType=1"><gsmsg:write key="mobile.18" /></a></li>
  <li><a href="../mobile/sp_sch010.do?mobileType=1"><gsmsg:write key="schedule.19" /></a></li>
  <li><a href="../mobile/sp_sch020.do?mobileType=1"><gsmsg:write key="mobile.19" /></a></li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->
</body>
</html:html>