<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
  String project           = jp.groupsession.v2.cmn.GSConstCommon.PLUGIN_ID_PROJECT;
  String nippou            = jp.groupsession.v2.cmn.GSConstCommon.PLUGIN_ID_NIPPOU;
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch020.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../schedule/js/sch020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="sch020Form" property="sch020Reload" value="0">
    var reloadinterval = <bean:write name="sch020Form" property="sch020Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% long schTipCnt = 0; %>
<body class="body_03" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/schedule/sch020">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010CrangeKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />

<logic:notEmpty name="sch020Form" property="schNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="sch020Form" property="schNotAccessGroupList" scope="request">
    <input type="hidden" name="schNotAccessGroup" value="<bean:write name="notAccessGroup"/>">
  </logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.monthly" /> ]</td>
  <td width="100%" class="header_white_bg">
  <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
  <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
  <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
  <logic:equal name="sch020Form" property="adminKbn" value="1">
    <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('ktool');">
  </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pset');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  </tr>
  </table>

  <table cellpadding="5" width="100%" class="tl0">
  <tr>
  <td colspan="7" class="td_type0">

    <table width="100%" class="tl0" border="0">

    <logic:messagesPresent message="false">
    <tr>
    <td colspan="5">
      <span class="TXT02"><html:errors/></span>
    </td>
    </tr>
    </logic:messagesPresent>

    <tr>
    <td width="75%" align="right" nowrap>
      <input type="button" value="<gsmsg:write key="schedule.19" />" class="btn_week_kojin" onClick="buttonPush('kojin');">
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onClick="buttonPush('day');">
    <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('week');">
    <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="buttonPush('reload');">
    <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onClick="moveListSchedule('list', <bean:write name="sch020Form" property="sch010SelectUsrSid" />, <bean:write name="sch020Form" property="sch010SelectUsrKbn" />);">
    </td>
    <td width="0%" align="right" valign="top" nowrap>
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_lm');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.thismonth3" />" onClick="buttonPush('today');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rm');">
    </td>
    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="resetCmd();wrtCalendarByBtn(this.form.sch010DspDate, 'sch020Btn')" class="calendar_btn2", id="sch020Btn">
    </td>


    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td colspan="7" class="table_bg_7D91BD" align="center">
    <table width="100%" class="tl0">
      <tr>
      <td width="15%" align="left">
        <span class="text_tl1"><bean:write name="sch020Form" property="sch020StrDspDate" scope="request" /></font></span>
        &nbsp;&nbsp;
        <span class="text_tl1"><!--a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="sch020Form" property="sch010SelectUsrSid" />);"><font color="ffffff"><bean:write name="sch020Form" property="sch020StrUserName" scope="request" /></font></a-->
      </td>

      <td width="10%" align="left" nowrap>
      <span class="text_tlw"><gsmsg:write key="cmn.show.group" /></span>
      <html:select property="sch010DspGpSid" styleClass="select02" onchange="changeGroupCombo();">

        <logic:notEmpty name="sch020Form" property="sch010GpLabelList" scope="request">
        <logic:iterate id="gpBean" name="sch020Form" property="sch010GpLabelList" scope="request">

        <% boolean gpDisabled = false; %>
        <logic:equal name="gpBean" property="viewKbn" value="false">
        <% gpDisabled = true; %>
        </logic:equal>

        <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
        <logic:equal name="gpBean" property="styleClass" value="0">
        <html:option value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
        </logic:equal>

        <logic:notEqual name="gpBean" property="styleClass" value="0">
        <html:option styleClass="select03" value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
        </logic:notEqual>

        </logic:iterate>
        </logic:notEmpty>
        <logic:equal name="sch020Form" property="sch010CrangeKbn" value="0">
          <input type="button" onclick="openGroupWindow(this.form.sch010DspGpSid, 'sch010DspGpSid', '1', '', 0, '', 'schNotAccessGroup');" class="group_btn" value="&nbsp;&nbsp;" id="sch010GroupBtn">
        </logic:equal>
      </html:select>
      </td>

      <td width="10%" align="left" nowrap>
      <html:select property="sch020SelectUsrSid" styleClass="select02" onchange="changeUserCombo();">
        <html:optionsCollection name="sch020Form" property="sch020UsrLabelList" value="value" label="label" />
      </html:select>

      <logic:equal name="sch020Form" property="sch020SelectUsrSid" value="-1">
      <input type="hidden" name="sch010SelectUsrSid" value="<bean:write name="sch020Form" property="sch010DspGpSid" />">
      <input type="hidden" name="sch010SelectUsrKbn" value="1">
      </logic:equal>
      <logic:equal name="sch020Form" property="sch020SelectUsrSid" value="-2">
      <input type="hidden" name="sch010SelectUsrSid" value="<bean:write name="sch020Form" property="sch010DspGpSid" />">
      <input type="hidden" name="sch010SelectUsrKbn" value="1">
      </logic:equal>
      <logic:notEqual name="sch020Form" property="sch020SelectUsrSid" value="-1">
      <logic:notEqual name="sch020Form" property="sch020SelectUsrSid" value="-2">
      <input type="hidden" name="sch010SelectUsrSid" value="<bean:write name="sch020Form" property="sch020SelectUsrSid" />">
      <input type="hidden" name="sch010SelectUsrKbn" value="0">
      </logic:notEqual>
      </logic:notEqual>
      </td>

      <td width="0%" align="right">
      <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
      <html:text property="sch010searchWord" styleClass="text_base" maxlength="50" style="width:155px;" />
      <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onClick="buttonPush('search');">
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= sunday %>">
  <!-- 開始曜日：日曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  </logic:equal>

  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= monday %>">
  <!-- 開始曜日：月曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  </logic:equal>

  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= tuesday %>">
  <!-- 開始曜日：火曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  </logic:equal>

  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= wednesday %>">
  <!-- 開始曜日：水曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  </logic:equal>

  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= thursday %>">
  <!-- 開始曜日：木曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  </logic:equal>

  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= friday %>">
  <!-- 開始曜日：金曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  </logic:equal>

  <logic:equal name="sch020Form" property="sch020DspStartWeek" value="<%= saturday %>">
  <!-- 開始曜日：土曜日 -->
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
  <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
  </logic:equal>
  </tr>

<%
int count = 0;
int rowCnt = 0;
%>
  <logic:notEmpty name="sch020Form" property="sch020ScheduleList" scope="request">
    <logic:iterate id="monthMdl" name="sch020Form" property="sch020ScheduleList" scope="request">
    <bean:define id="periodSchList" name="monthMdl" property="sch020PeriodSchList" type="java.util.ArrayList" />
    <bean:define id="usrMdl" name="monthMdl" property="sch020UsrMdl"/>
    <logic:notEmpty name="monthMdl" property="sch020SchList">
    <logic:iterate id="dayMdl" name="monthMdl" property="sch020SchList">

    <!--１週間-->
    <!--背景-->
    <logic:equal name="dayMdl" property="weekKbn" value="1">

    <%
      if (count >= 7) {
    %>
      <tr>
    <%
      }
    %>

      <logic:equal name="dayMdl" property="todayKbn" value="1">
      <td valign="top" class="td_type2" height="60">
      </logic:equal>
      <logic:notEqual name="dayMdl" property="todayKbn" value="1">
      <td valign="top" class="td_type9" height="60">
      </logic:notEqual>
    </logic:equal>

    <logic:equal name="dayMdl" property="weekKbn" value="7">
    <logic:notEqual name="dayMdl" property="todayKbn" value="1">
      <td valign="top" class="td_type8" height="60">
    </logic:notEqual>
    <logic:equal name="dayMdl" property="todayKbn" value="1">
      <td valign="top" class="td_type2" height="60">
    </logic:equal>
    </logic:equal>

    <logic:notEqual name="dayMdl" property="weekKbn" value="1">
    <logic:notEqual name="dayMdl" property="weekKbn" value="7">
    <logic:notEqual name="dayMdl" property="todayKbn" value="1">
      <td valign="top" class="td_type1" height="60">
    </logic:notEqual>
    <logic:equal name="dayMdl" property="todayKbn" value="1">
      <td valign="top" class="td_type2" height="60">
    </logic:equal>
    </logic:notEqual>
    </logic:notEqual>

    <span id="lt" class="text_tl1">
    <logic:equal name="sch020Form" property="sch020RegistFlg" value="true">
    <a href="#" onClick="addSchedule('add', <bean:write name="dayMdl" property="schDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);"><img src="../schedule/images/scadd.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0"></a>
    </logic:equal>
    </span>


    <logic:notEqual name="dayMdl" property="holidayKbn" value="1">
    <span id="rt">
    <!--日付-->
    <a href="#" onClick="moveDailySchedule('day', <bean:write name="dayMdl" property="schDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);">
    <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
    <logic:equal name="dayMdl" property="todayKbn" value="1">
    <span class="sc_thismonth_today">
    </logic:equal>

    <logic:notEqual name="dayMdl" property="todayKbn" value="1">
    <span class="text_base3">
    </logic:notEqual>

    </logic:equal>
    <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
    <span class="text_base5">
    </logic:notEqual>

    <bean:write name="dayMdl" property="dspDay" />

    <%
      count++;
    %>

    </span>
    </a>
    </span>
    </logic:notEqual>

    <logic:equal name="dayMdl" property="holidayKbn" value="1">
    <span id="rt">
    <!--日付-->
    <a href="#" onClick="moveDailySchedule('day', <bean:write name="dayMdl" property="schDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);">
    <logic:equal name="dayMdl" property="todayKbn" value="1">
    <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
    <span class="sc_thismonth_holiday_today">
    </logic:equal>
    <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
    <span class="sc_month_holiday_today">
    </logic:notEqual>
    </logic:equal>

    <logic:notEqual name="dayMdl" property="todayKbn" value="1">
    <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
    <span class="text_base4">
    </logic:equal>
    <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
    <span class="text_base6">
    </logic:notEqual>
    </logic:notEqual>

    <bean:write name="dayMdl" property="dspDay" />

    <%
      count++;
    %>

    </span></a>
    </span>
    </logic:equal>

    <!--休日名称-->
    <logic:notEmpty name="dayMdl" property="holidayName">
    <br>
    <span id="rt"><font size="-2" color="#ff0000">
    <bean:write name="dayMdl" property="holidayName" />
    </font></span>
    </logic:notEmpty>
    <logic:empty name="dayMdl" property="holidayName">
    <br>
    </logic:empty>

    <logic:notEmpty name="dayMdl" property="schDataList">
    <logic:iterate id="schMdl" name="dayMdl" property="schDataList">
    <logic:notEqual name="schMdl" property="timeKbn" value="1">

      <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
      <bean:define id="u_schsid" name="schMdl" property="schSid" type="java.lang.Integer" />
      <bean:define id="u_date" name="dayMdl" property="schDate"  type="java.lang.String" />
      <bean:define id="u_public" name="schMdl" property="public"  type="java.lang.Integer" />
      <bean:define id="u_grpEdKbn" name="schMdl" property="grpEdKbn"  type="java.lang.Integer" />
      <bean:define id="u_kjnEdKbn" name="schMdl" property="kjnEdKbn"  type="java.lang.Integer" />

      <%
        int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
        int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
        int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
      %>

      <!--公開-->
      <%
      if ((publicType == dspPublic || publicType == dspBelongGroup) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
      %>
     <br>
      <logic:empty name="schMdl" property="valueStr">
      <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="schMdl" property="userSid" />, <bean:write name="schMdl" property="userKbn" />);">
      <span class="tooltips"><bean:write name="schMdl" property="title" /></span>
      </logic:empty>
      <logic:notEmpty name="schMdl" property="valueStr">
      <bean:define id="scnaiyou" name="schMdl" property="valueStr" />
      <%
        String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
      %>
      <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="schMdl" property="userSid" />, <bean:write name="schMdl" property="userKbn" />);">
      <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
      </logic:notEmpty>
      <logic:equal name="schMdl" property="userKbn" value="1">
      <span class="sc_link_g">G</span>
      </logic:equal>
      <!--タイトルカラー設定-->
      <logic:equal name="schMdl" property="bgColor" value="0">
      <span class="sc_link_1">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="1">
      <span class="sc_link_1">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="2">
      <span class="sc_link_2">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="3">
      <span class="sc_link_3">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="4">
      <span class="sc_link_4">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="5">
      <span class="sc_link_5">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="6">
      <span class="sc_link_6">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="7">
      <span class="sc_link_7">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="8">
      <span class="sc_link_8">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="9">
      <span class="sc_link_9">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="10">
      <span class="sc_link_10">
      </logic:equal>

      <logic:notEmpty name="schMdl" property="userName">
        <span class="sc_link_mem"><bean:write name="schMdl" property="userName" /><br></span>
      </logic:notEmpty>

        <logic:notEmpty name="schMdl" property="time">
        <font size="-2"><bean:write name="schMdl" property="time" /><br></font>
        </logic:notEmpty>
      <bean:write name="schMdl" property="title" />

    </span>
    </a>

      <%
       } else {
      %>

      <!--非公開-->
      <br>
      <span class="sc_nolink">
        <logic:notEmpty name="schMdl" property="userName">
          <span class="sc_link_mem"><bean:write name="schMdl" property="userName" /><br></span>
        </logic:notEmpty>
        <logic:notEmpty name="schMdl" property="time">
        <font size="-2"><bean:write name="schMdl" property="time" /><br></font>
        </logic:notEmpty>
      <bean:write name="schMdl" property="title" />
    </span>
      <%
       }
      %>

    </logic:notEqual>
    </logic:iterate>
    </logic:notEmpty>
    </td>

    <%
      if (count >= 7) {
    %>
      </tr>


      <!--期間スケジュール-->
      <logic:iterate id="periodList" name="periodSchList" indexId="idx">
      <% if (Integer.valueOf(idx) == rowCnt) { %>
        <logic:notEmpty name="periodList" property="sch020NoTimeSchList">
        <bean:define id="prList" name="periodList" property="sch020NoTimeSchList" type="java.util.ArrayList"/>
        <% int size = prList.size(); %>
        <logic:iterate id="prdList" name="periodList" property="sch020NoTimeSchList" indexId="idx2">

          <logic:notEmpty name="prdList">
            <% if ((Integer.valueOf(idx2) + 1) == (Integer.valueOf(size))) { %>
            <tr class="td_sch_type5">
            <% } else { %>
            <tr class="td_type33">
            <% } %>

              <bean:define id="mPrList" name="prdList" type="java.util.ArrayList"/>
              <% int size2 = mPrList.size(); %>

              <logic:iterate id="uPeriodMdl" name="prdList" indexId="mPidx">

                <logic:notEmpty name="uPeriodMdl" property="periodMdl">
                  <bean:define id="pMdl" name="uPeriodMdl" property="periodMdl"/>

                     <td class="td_type1 td_type_kikan" colspan="<bean:write name="pMdl" property="schPeriodCnt" />">

                      <bean:define id="p_schsid" name="uPeriodMdl" property="schSid" type="java.lang.Integer" />
                      <bean:define id="p_public" name="uPeriodMdl" property="public"  type="java.lang.Integer" />
                      <bean:define id="p_grEdKbn" name="uPeriodMdl" property="grpEdKbn"  type="java.lang.Integer" />
                      <bean:define id="p_kjEdKbn" name="uPeriodMdl" property="kjnEdKbn"  type="java.lang.Integer" />

                      <%
                        int p_pblicType = ((Integer)pageContext.getAttribute("p_public",PageContext.PAGE_SCOPE));
                        int p_grpEdKbn = ((Integer)pageContext.getAttribute("p_grEdKbn",PageContext.PAGE_SCOPE));
                        int p_kjnEdKbn = ((Integer)pageContext.getAttribute("p_kjEdKbn",PageContext.PAGE_SCOPE));
                      %>

                      <!--公開-->
                      <%
                      if ((p_pblicType == dspPublic || p_pblicType == dspBelongGroup) || (p_kjEdKbn == editConfOwn || p_grEdKbn == editConfGroup)) {
                      %>

                      <!--スケジュールデータ-->
                      <logic:empty name="uPeriodMdl" property="schAppendUrl">
                        <logic:empty name="uPeriodMdl" property="valueStr">
                        <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, <bean:write name="uPeriodMdl" property="userSid" />, <bean:write name="uPeriodMdl" property="userKbn" />);">
                        <span class="tooltips"><bean:write name="uPeriodMdl" property="title" /></span>
                        </logic:empty>
                        <logic:notEmpty name="uPeriodMdl" property="valueStr">
                        <bean:define id="scnaiyou" name="uPeriodMdl" property="valueStr" />
                        <%
                          String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                          String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                        %>
                        <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, <bean:write name="uPeriodMdl" property="userSid" />, <bean:write name="uPeriodMdl" property="userKbn" />);">
                        <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                        </logic:notEmpty>
                      </logic:empty>

                      <!--他プラグインデータ-->
                      <logic:notEmpty name="uPeriodMdl" property="schAppendUrl">
                        <logic:empty name="uPeriodMdl" property="valueStr">
                        <a id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" href="<bean:write name="uPeriodMdl" property="schAppendUrl" />">
                        <% boolean schFilter = true; %>
                        <logic:equal name="uPeriodMdl" property="userKbn" value="<%= project %>">
                          <% schFilter = false; %>
                        </logic:equal>
                        <span class="tooltips"><bean:write name="uPeriodMdl" property="title" filter="<%= schFilter %>" /></span>
                        </logic:empty>
                        <logic:notEmpty name="uPeriodMdl" property="valueStr">
                        <bean:define id="scnaiyou" name="uPeriodMdl" property="valueStr" />
                        <%
                          String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                          String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                        %>
                        <a href="<bean:write name="uPeriodMdl" property="schAppendUrl" />" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>">
                        <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                        </logic:notEmpty>
                      </logic:notEmpty>

                      <% boolean schFilter = true; %>
                      <logic:equal name="uPeriodMdl" property="userKbn" value="1">
                      <span class="sc_link_g">G</span>
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="userKbn" value="<%= project %>">
                        <span class="sc_link_g">TODO</span>
                        <% schFilter = false; %>
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="userKbn" value="<%= nippou %>">
                        <span class="sc_link_g">アクション</span>
                      </logic:equal>
                      <!--タイトルカラー設定-->
                      <logic:equal name="uPeriodMdl" property="bgColor" value="0">
                      <span class="sc_link_1">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="1">
                      <span class="sc_link_1">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="2">
                      <span class="sc_link_2">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="3">
                      <span class="sc_link_3">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="4">
                      <span class="sc_link_4">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="5">
                      <span class="sc_link_5">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="6">
                      <span class="sc_link_6">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="7">
                      <span class="sc_link_7">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="8">
                      <span class="sc_link_8">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="9">
                      <span class="sc_link_9">
                      </logic:equal>
                      <logic:equal name="uPeriodMdl" property="bgColor" value="10">
                      <span class="sc_link_10">
                      </logic:equal>

                      <logic:notEmpty name="uPeriodMdl" property="userName">
                        <span class="sc_link_mem"><bean:write name="uPeriodMdl" property="userName" /><br></span>
                      </logic:notEmpty>

                        <logic:notEmpty name="uPeriodMdl" property="time">
                        <font size="-2"><bean:write name="uPeriodMdl" property="time" /><br></font>
                        </logic:notEmpty>
                      <bean:write name="uPeriodMdl" property="title" filter="<%= schFilter %>"  />

                    </span>
                    </a>

                      <%
                       } else {
                      %>

                      <!--非公開-->
                      <span class="sc_nolink">
                        <logic:notEmpty name="uPeriodMdl" property="time">
                        <font size="-2">
                        <bean:write name="uPeriodMdl" property="time" /><br></font>
                        </logic:notEmpty>
                        <logic:notEmpty name="uPeriodMdl" property="userName">
                          <span class="sc_link_mem"><bean:write name="uPeriodMdl" property="userName" /><br></span>
                        </logic:notEmpty>
                        <bean:write name="uPeriodMdl" property="title" />
                      </span>

                      <% } %>

                    </td>
                </logic:notEmpty>

                <logic:empty name="uPeriodMdl" property="periodMdl">

                  <% String td_class = "td_type_kikan2"; %>
                  <% if ((Integer.valueOf(mPidx) + 1) == (Integer.valueOf(size2))) { %>
                  <% td_class ="td_type_kikan3"; %>
                  <% } else if (Integer.valueOf(mPidx) == 0){ %>
                  <% td_class ="td_type_kikan4"; %>
                  <% } %>

                  <% if ((Integer.valueOf(idx2) + 1) == (Integer.valueOf(size))) { %>
                  <% td_class = td_class  + " td_type_kikan5"; %>
                  <% } %>

                  <td class="<%= td_class %>"></td>

                </logic:empty>

              </logic:iterate>
            </tr>
          </logic:notEmpty>

        </logic:iterate>

        </logic:notEmpty>
      <% } %>
      </logic:iterate>

    <%
        count = 0;
        rowCnt++;
      }
    %>

    </logic:iterate>
    </logic:notEmpty>

    </logic:iterate>
  </logic:notEmpty>
  </table>

</td>
</tr>
</table>
</html:form>

<script type="text/javascript">
<% for (long schIdCnt = 0; schIdCnt < schTipCnt; schIdCnt++) { %>
<% } %>
</script>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>