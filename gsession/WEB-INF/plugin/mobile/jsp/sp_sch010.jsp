<%@page import="jp.groupsession.v2.cmn.GSConstSchedule"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="schedule.19" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sch"; %>
<% thisForm = "mbhSch010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_sch020">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/sch_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="schedule.108" /><br><gsmsg:write key="schedule.19" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:equal name="mbhSch010Form" property="registFlg" value="<%= String.valueOf(GSConstSchedule.SSP_AUTH_EDIT) %>">
  <a href="../mobile/sp_sch040.do?mobileType=1&cmd=add&dspMod=1&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&changeDateFlg=<bean:write name="mbhSch010Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.entry" /></div></a>
  </logic:equal>
  <a href="../mobile/sp_sch050.do?mobileType=1&sch050DispMod=usrChange&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />&sch050DispId=sp_sch010&changeDateFlg=<bean:write name="mbhSch010Form" property="changeDateFlg" />" data-role="button" data-icon="refresh" data-inline="true" data-iconpos="right"><div class="font_small"><gsmsg:write key="mobile.10" /></div></a>
</div>

<div class="title_1" align="center">
  <b><div class="font_small"><bean:write name="mbhSch010Form" property="sch010UserName" /></div></b>
</div>

  <!-- グループ,本人 -->
  <logic:notEmpty name="mbhSch010Form" property="sch010TopList" scope="request">
  <logic:iterate id="weekMdl" name="mbhSch010Form" property="sch010TopList" scope="request">
  <bean:define id="usrMdl" name="weekMdl" property="sch010UsrMdl"/>


  <!-- 本人 -->
  <logic:notEqual name="usrMdl" property="usrKbn" value="1">
  <!-- スケジュール情報 -->
  <logic:notEmpty name="weekMdl" property="sch010SchList">

  <logic:iterate id="dayMdl" name="weekMdl" property="sch010SchList" indexId="idx">
  <bean:define id="mod" value="<%= String.valueOf(idx.intValue()) %>" />

   <% String dayColor = "#ffffff"; %>
   <logic:equal name="dayMdl" property="weekKbn" value="7">
     <% dayColor = "#d1d3ff"; %>
   </logic:equal>
   <logic:equal name="dayMdl" property="weekKbn" value="1">
     <% dayColor = "#ff9090"; %>
   </logic:equal>

  <logic:equal name="mod" value="0">
    <bean:define id="collapsed" value="false" />
  </logic:equal>
  <logic:notEqual name="mod" value="0">
    <bean:define id="collapsed" value="true" />
  </logic:notEqual>

  <logic:empty name="dayMdl" property="schDataList">
    <div align="center">
      <p class="btn_fake"><b><bean:write name="dayMdl" property="schDate" /></b></p>
    </div>
  </logic:empty>

  <logic:notEmpty name="dayMdl" property="schDataList">
  <div data-role="collapsible" data-collapsed=false>
      <h2>
        <div align="center">
          <span style="color:<%= dayColor %>;"><bean:write name="dayMdl" property="schDate" /></span>
        </div>
      </h2>
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

      <bean:define id="mod2" value="0" />
      <logic:iterate id="schMdl" name="dayMdl" property="schDataList" indexId="idx2">

      <logic:notEmpty name="schMdl" property="title">

        <logic:equal name="mod2" value="<%= String.valueOf(idx2.intValue() % 2) %>">
          <bean:define id="liColor" value="c" />
        </logic:equal>
        <logic:notEqual name="mod2" value="<%= String.valueOf(idx2.intValue() % 2) %>">
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
          <a href="../mobile/sp_sch040.do?mobileType=1&cmd=edit&sch010SchSid=<bean:write name="schMdl" property="schSid" />&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&dspMod=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />&changeDateFlg=<bean:write name="mbhSch010Form" property="changeDateFlg" />">
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
          <logic:notEmpty name="schMdl" property="title">
            <logic:notEmpty name="schMdl" property="time">
            <div class="font_xsmall"><span style="color:#000000"><bean:write name="schMdl" property="time" /></span></div>
            </logic:notEmpty>
            <span style="color:#000000"><bean:write name="schMdl" property="title" /></span>
          </logic:notEmpty>
          </span>

          <%
           }
          %>

      </li>
    </logic:notEmpty>
    </logic:iterate>

  </ul>

  </div>
  </logic:notEmpty>
  </logic:iterate>
  </logic:notEmpty>

  </logic:notEqual>
  </logic:iterate>
  </logic:notEmpty>

<br>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <a href="../mobile/sp_sch010.do?mobileType=1&CMD=move_ld&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
  <a href="../mobile/sp_sch010.do?mobileType=1&CMD=today&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=0&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
  <a href="../mobile/sp_sch010.do?mobileType=1&CMD=move_rd&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
</div>
<div data-role="controlgroup" data-type="horizontal" align="center">
  <a href="../mobile/sp_sch010.do?mobileType=1&CMD=move_lw&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="mobile.24" /></div></a>
  <a href="../mobile/sp_sch010.do?mobileType=1&CMD=move_rw&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="mobile.25" /></div></a>
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