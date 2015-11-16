<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="jp.groupsession.v2.cmn.GSConstSchedule"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%  String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<!DOCTYPE html>
<%-- 定数値 --%>
<%
  int editConfOwn          = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_OWN;
  int editConfGroup        = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_GROUP;
  int dspPublic            = jp.groupsession.v2.cmn.GSConstSchedule.DSP_PUBLIC;
  int dspNotPublic         = jp.groupsession.v2.cmn.GSConstSchedule.DSP_NOT_PUBLIC;
  int dspYoteiari          = jp.groupsession.v2.cmn.GSConstSchedule.DSP_YOTEIARI;
  int dspBelongGroup       = jp.groupsession.v2.cmn.GSConstSchedule.DSP_BELONG_GROUP;
  String sunday            = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_SUN);
  String monday            = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_MON);
  String tuesday           = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_TUE);
  String wednesday         = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_WED);
  String thursday          = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_THU);
  String friday            = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_FRI);
  String saturday          = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CHANGE_WEEK_SAT);
%>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" /> SP] <gsmsg:write key="schedule.108" /> <gsmsg:write key="mobile.18" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sch"; %>
<% thisForm = "mbhSch030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_sch030">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/sch_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="schedule.108" /><br><gsmsg:write key="mobile.18" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:equal name="mbhSch030Form" property="registFlg" value="<%= String.valueOf(GSConstSchedule.SSP_AUTH_EDIT) %>">
    <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=<bean:write name="mbhSch030Form" property="dspMod"  />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&changeDateFlg=<bean:write name="mbhSch030Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.entry" /></div></a>
  </logic:equal>
<a href="../mobile/sp_sch050.do?mobileType=1&sch050DispMod=usrChange&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch050DispId=sp_sch030&changeDateFlg=<bean:write name="mbhSch030Form" property="changeDateFlg" />" data-role="button" data-icon="refresh" data-inline="true" data-iconpos="right"><div class="font_small"><gsmsg:write key="mobile.10" /></div></a>
</div>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li data-role="list-divider" style="background:#ffffff;">
    <div align="center">
      <b><bean:write name="mbhSch030Form" property="sch030UsrName" /></b>
      <hr>
      <bean:write name="mbhSch030Form" property="sch030StrDate" />
    </div>
  </li>

  <!-- スケジュール表示  -->
  <logic:notEmpty name="mbhSch030Form" property="sch030DayMdlList">
  <bean:define id="mod" value="0" />
  <logic:iterate id="dayMdl" name="mbhSch030Form" property="sch030DayMdlList" indexId="idx">

    <bean:define id="u_schsid" name="dayMdl" property="schSid" type="java.lang.Integer" />
    <bean:define id="u_public" name="dayMdl" property="public"  type="java.lang.Integer" />
    <bean:define id="u_grpEdKbn" name="dayMdl" property="grpEdKbn"  type="java.lang.Integer" />
    <bean:define id="u_kjnEdKbn" name="dayMdl" property="kjnEdKbn"  type="java.lang.Integer" />

    <%
        int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
        int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
        int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
    %>

    <!--公開-->
    <%
    if ((publicType == dspPublic || publicType == dspBelongGroup) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
    %>


    <logic:notEmpty name="dayMdl" property="title">
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>



      <a href="../mobile/sp_sch040.do?mobileType=1&cmd=edit&sch010SchSid=<bean:write name="dayMdl" property="schSid" />&dspMod=<bean:write name="mbhSch030Form" property="dspMod" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=<bean:write name="mbhSch030Form" property="changeDateFlg" />">
      <div class="font_xsmall"><span style="color:#ff0000"><bean:write name="dayMdl" property="time" /></span></div>
          <!--タイトルカラー設定-->
          <logic:equal name="dayMdl" property="bgColor" value="0">
          <span style="color: #0000FF;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="1">
          <span style="color: #0000FF;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="2">
          <span style="color: #FF0000;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="3">
          <span style="color: #009900;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="4">
          <span style="color: #ff9900;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="5">
          <span style="color: #333333;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="6">
          <span style="color: #000080;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="7">
          <span style="color: #800000;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="8">
          <span style="color: #008080;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="9">
          <span style="color: #808080;">
          </logic:equal>
          <logic:equal name="dayMdl" property="bgColor" value="10">
          <span style="color: #008DCB;">
          </logic:equal>
          <bean:write name="dayMdl" property="title" />
          </span>
          </a>

    </li>
    </logic:notEmpty>

    <%
      } else {
    %>

    <!--非公開-->
      <logic:notEmpty name="dayMdl" property="title">
      <li>
      <logic:notEmpty name="dayMdl" property="time">
      <font size="-2"><bean:write name="dayMdl" property="time" /><br></font>
      </logic:notEmpty>
      <font size="-2"><bean:write name="dayMdl" property="title" /></font>
      </li>
      </logic:notEmpty>
    <%
     }
    %>

  </logic:iterate>
</logic:notEmpty>

</ul>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <a href="../mobile/sp_sch030.do?mobileType=1&CMD=move_ld&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=1" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
  <a href="../mobile/sp_sch030.do?mobileType=1&CMD=today&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=0" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
  <a href="../mobile/sp_sch030.do?mobileType=1&CMD=move_rd&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=1" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
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