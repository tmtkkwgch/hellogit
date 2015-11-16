<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@page import="jp.groupsession.v2.cmn.GSConstSchedule"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSch030Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="mobile.18" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>
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
<html:form action="/mobile/mh_sch030">


<b><%= emojiTokei.toString() %><gsmsg:write key="schedule.108" /><br><gsmsg:write key="mobile.18" /></b>
<hr>
  <logic:equal name="mbhSch030Form" property="registFlg" value="<%= String.valueOf(GSConstSchedule.SSP_AUTH_EDIT) %>">
    ●<a href="../mobile/mh_sch040.do<%= jsessionId.toString() %>?cmd=add&dspMod=<bean:write name="mbhSch030Form" property="dspMod" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&changeDateFlg=<bean:write name="mbhSch030Form" property="changeDateFlg" />"><gsmsg:write key="cmn.entry" /></a>
    <br>
  </logic:equal>
  <bean:write name="mbhSch030Form" property="sch030UsrName" /><a href="../mobile/mh_sch050.do<%= jsessionId.toString() %>?sch050DispMod=usrChange&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch050DispId=mh_sch030"><gsmsg:write key="mobile.10" /></a>
<hr>
<br><bean:write name="mbhSch030Form" property="sch030StrDate" />
<!-- スケジュール表示  -->
<logic:notEmpty name="mbhSch030Form" property="sch030DayMdlList">
<logic:iterate id="dayMdl" name="mbhSch030Form" property="sch030DayMdlList">

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
<logic:notEmpty name="dayMdl" property="time">
<br><font size="-2" color="#ff0000"><%= emojiTokei.toString() %><bean:write name="dayMdl" property="time" /></font>
</logic:notEmpty>
<br>┗<a href="../mobile/mh_sch040.do<%= jsessionId.toString() %>?cmd=edit&sch010SchSid=<bean:write name="dayMdl" property="schSid" />&dspMod=<bean:write name="mbhSch030Form" property="dspMod" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />">
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
</logic:notEmpty>

<%
  } else {
%>

<!--非公開-->
  <logic:notEmpty name="dayMdl" property="title">
  <br>
  <logic:notEmpty name="dayMdl" property="time">
  <font size="-2"><bean:write name="dayMdl" property="time" /><br></font>
  </logic:notEmpty>
  <font size="-2">┗<bean:write name="dayMdl" property="title" /></font>
  <br>
  </logic:notEmpty>
<%
 }
%>

</logic:iterate>
</logic:notEmpty>


<br>

<hr>
<div align="center">
  <a href="../mobile/mh_sch030.do<%= jsessionId.toString() %>?CMD=move_ld&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=1"><gsmsg:write key="cmn.previous.day" /></a>/
  <a href="../mobile/mh_sch030.do<%= jsessionId.toString() %>?CMD=today&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=0"><gsmsg:write key="cmn.today" /></a>/
  <a href="../mobile/mh_sch030.do<%= jsessionId.toString() %>?CMD=move_rd&sch010DspDate=<bean:write name="mbhSch030Form" property="sch010DspDate" />&sch010SelectUsrSid=<bean:write name="mbhSch030Form" property="sch010SelectUsrSid" />&sch010DspGpSid=<bean:write name="mbhSch030Form" property="sch010DspGpSid" />&changeDateFlg=1"><gsmsg:write key="cmn.nextday" /></a>&nbsp;
</div>

<hr>
<a href="../mobile/mh_sch030.do<%= jsessionId.toString() %>"><gsmsg:write key="mobile.18" /></a>
<br>
<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>"><gsmsg:write key="schedule.19" /></a>
<br>
<a href="../mobile/mh_sch020.do<%= jsessionId.toString() %>"><gsmsg:write key="mobile.19" /></a>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>