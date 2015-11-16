<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleReadOnly.tld" prefix="dailySchedule" %>
<%@ taglib uri="/WEB-INF/ctag-dailyReserveReadOnly.tld" prefix="dailyReserve" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch120.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../schedule/js/sch120.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="sch120Form" property="sch120Reload" value="0">
    var reloadinterval = <bean:write name="sch120Form" property="sch120Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/schedule/sch120">
<input type="hidden" name="CMD" value="">
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />

<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010DspGpSid" />

<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch120MoveMode" />
<html:hidden property="sch120FromHour" />
<html:hidden property="sch120ResDspGpSid" />

<logic:notEmpty name="sch120Form" property="sv_users" scope="request">
<logic:iterate id="svuBean" name="sch120Form" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="svuBean" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch120Form" property="users_l" scope="request">
<logic:iterate id="ulBean" name="sch120Form" property="users_l" scope="request">
<input type="hidden" name="users_l" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch120Form" property="svReserveUsers" scope="request">
<logic:iterate id="svrBean" name="sch120Form" property="svReserveUsers" scope="request">
<input type="hidden" name="svReserveUsers" value="<bean:write name="svrBean" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch120Form" property="reserve_l" scope="request">
<logic:iterate id="rlBean" name="sch120Form" property="reserve_l" scope="request">
<input type="hidden" name="reserve_l" value="<bean:write name="rlBean" />">
</logic:iterate>
</logic:notEmpty>

<bean:define id="fromHour" name="sch120Form" property="sch120FromHour" scope="request"/>
<bean:define id="toHour" name="sch120Form" property="sch120ToHour" scope="request"/>
<bean:define id="dspDate" name="sch120Form" property="sch010DspDate" scope="request"/>
<bean:define id="totalCols" name="sch120Form" property="sch120TotalCols" scope="request"/>
<bean:define id="adminKbn" name="sch120Form" property="adminKbn" scope="request"/>
<bean:define id="memoriCount" name="sch120Form" property="sch120MemoriCount" scope="request"/>


<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="schedule.sch120.4" /> ]</td>
  <td width="100%" class="header_white_bg">
  <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
  <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  </tr>
  </table>


  <table cellpadding="5" width="100%" class="tl0">
  <tr>
  <td colspan="<%= totalCols.toString() %>" class="td_type0">

    <table width="100%" class="tl0" border="0">
    <tr>
    <td width="20%" align="left">

      <table border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td class="td_type1" nowrap><span class="text_base2"><gsmsg:write key="cmn.zaiseki2" /></span></td>
      <td>&nbsp;</td>
      <td class="td_type_gaisyutu" nowrap><span class="text_base2"><gsmsg:write key="cmn.absence2" /></span></td>
      <td>&nbsp;</td>
      <td class="td_type_kekkin" nowrap><span class="text_base2"><gsmsg:write key="cmn.other" /></span></td>
      </tr>
      </table>

    </td>
    <td width="40%" align="center" nowrap>

    </td>
    <td width="40%" align="center" nowrap>
    <bean:size id="topSize" name="sch120Form" property="sch010TopList" scope="request" />
    <logic:equal name="topSize" value="2">
    <logic:iterate id="weekBean" name="sch120Form" property="sch010TopList" scope="request" offset="1"/>
    </logic:equal>
    <logic:notEqual name="topSize" value="2">
    <logic:iterate id="weekBean" name="sch120Form" property="sch010TopList" scope="request" offset="0"/>
    </logic:notEqual>

    <bean:define id="usrBean" name="weekBean" property="sch010UsrMdl" />
    </td>
    <td width="0%" align="right" nowrap>
      <input type="image" name="zweek" src="../schedule/images/arrow1_l.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_ld');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('today');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rd');">
      <input type="image" name="yweek" src="../schedule/images/arrow1_r.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden">
    </td>

    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.sch010DspDate, 'sch120')" class="calendar_btn2", id="sch120">
    </td>

    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td colspan="<%= totalCols.toString() %>" class="table_bg_7D91BD">
        <table width="100%" class="tl0">
        <tr>
        <td align="left">
        <span class="text_tl1"><bean:write name="sch120Form" property="sch120StrDate" scope="request" /></span>
        </td>
        <td align="right">
        </td>
        </tr>
        </table>
  </td>
  </tr>


  <!-- タイトル行(氏名,新規,時間) -->
  <tr>
  <th width="150" class="td_type3" colspan="2" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>

  <logic:notEmpty name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch120Form" property="sch120TimeChartList" scope="request">
  <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
  </logic:iterate>
  </logic:notEmpty>
  </tr>

  <!-- 時間目盛 -->
  <tr>
  <logic:notEmpty name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch120Form" property="sch120TimeChartList" scope="request">

  <logic:equal name="memoriCount" value="4">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  <logic:equal name="memoriCount" value="6">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  <logic:equal name="memoriCount" value="12">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  </logic:iterate>
  </logic:notEmpty>
  </tr>



<!-- グループと本人 -->
<logic:notEmpty name="sch120Form" property="sch010TopList" scope="request">
<logic:iterate id="weekMdl" name="sch120Form" property="sch010TopList" scope="request">

  <tr align="left" valign="top">
    <dailySchedule:dailywrite name="weekMdl" top="1" from="<%= fromHour.toString() %>" to="<%= toHour.toString() %>" admin="<%= adminKbn.toString() %>" memCnt="<%= memoriCount.toString() %>"/>
  </tr>

</logic:iterate>
</logic:notEmpty>


  <!-- グループメンバー -->
  <!-- タイトル行(氏名,新規,時間) -->
  <tr>
  <th width="150" class="td_type3" colspan="2" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <!-- 時間目盛 -->
  <logic:notEmpty name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch120Form" property="sch120TimeChartList" scope="request">
  <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
  </logic:iterate>
  </logic:notEmpty>
  </tr>
  <!-- 分目盛 -->
  <tr>
  <logic:notEmpty name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch120Form" property="sch120TimeChartList" scope="request">

  <logic:equal name="memoriCount" value="4">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  <logic:equal name="memoriCount" value="6">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  <logic:equal name="memoriCount" value="12">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  </logic:iterate>
  </logic:notEmpty>
  </tr>


  <!-- グループメンバー -->
<logic:notEmpty name="sch120Form" property="sch010BottomList" scope="request">
<logic:iterate id="gpWeekMdl" name="sch120Form" property="sch010BottomList" scope="request" indexId="cnt">

  <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
  <logic:notEqual name="cnt" value="0" scope="page">
  <logic:equal name="ret" value="0">
  <!-- タイトル行(氏名,新規,時間) -->
  <tr>
  <th width="150" class="td_type3" colspan="2" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <!-- 時間目盛 -->
  <logic:notEmpty name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch120Form" property="sch120TimeChartList" scope="request">
  <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
  </logic:iterate>
  </logic:notEmpty>
  </tr>
  <!-- 分目盛 -->
  <tr>
  <logic:notEmpty name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch120Form" property="sch120TimeChartList" scope="request">
  <logic:equal name="memoriCount" value="4">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  <logic:equal name="memoriCount" value="6">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>

  <logic:equal name="memoriCount" value="12">
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
  </logic:equal>
  </logic:iterate>
  </logic:notEmpty>
  </tr>
  </logic:equal>
  </logic:notEqual>

  <tr align="left" valign="top">
    <dailySchedule:dailywrite name="gpWeekMdl" top="0" from="<%= fromHour.toString() %>" to="<%= toHour.toString() %>" admin="<%= adminKbn.toString() %>" memCnt="<%= memoriCount.toString() %>" />
  </tr>

</logic:iterate>
</logic:notEmpty>

<!--施設予約状況-->
<logic:notEmpty name="sch120Form" property="rsv020TimeChartList" scope="request">
<logic:notEmpty name="sch120Form" property="rsv020DaylyList" scope="request">
  <tr>
  <th width="150" class="td_type3" colspan="2" rowspan="2"><span class="sc_ttl_def"><gsmsg:write key="cmn.facility.name" /></span></th>

    <logic:iterate id="strHour" name="sch120Form" property="rsv020TimeChartList" scope="request" >
      <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
    </logic:iterate>
    </tr>

    <tr>
    <logic:iterate id="timeSpc" name="sch120Form" property="rsv020TimeChartList" scope="request">
      <logic:equal name="memoriCount" value="4">
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      </logic:equal>

      <logic:equal name="memoriCount" value="6">
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      </logic:equal>

      <logic:equal name="memoriCount" value="12">
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
      </logic:equal>
    </logic:iterate>
    </tr>
  </logic:notEmpty>

  <logic:notEmpty name="sch120Form" property="rsv020DaylyList" scope="request">
    <logic:iterate id="sisetu" name="sch120Form" property="rsv020DaylyList" scope="request" indexId="cnt">
      <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
      <logic:equal name="ret" value="0">
        <logic:greaterThan name="cnt" value="0">

        <tr>
        <th width="150" class="td_type3" colspan="2" rowspan="2"><span class="sc_ttl_def"><gsmsg:write key="cmn.facility.name" /></span></th>

        <logic:notEmpty name="sch120Form" property="rsv020TimeChartList" scope="request">
          <logic:iterate id="strHour" name="sch120Form" property="rsv020TimeChartList" scope="request" >
            <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
          </logic:iterate>
          </tr>

          <tr>
          <logic:iterate id="timeSpc" name="sch120Form" property="rsv020TimeChartList" scope="request">
            <logic:equal name="memoriCount" value="4">
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            </logic:equal>

            <logic:equal name="memoriCount" value="6">
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            </logic:equal>

            <logic:equal name="memoriCount" value="12">
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            <td class="user_tab_tl"><img src="../schedule/images/space10.gif" width="5"></td>
            </logic:equal>
          </logic:iterate>
          </tr>
        </logic:notEmpty>
        </logic:greaterThan>
      </logic:equal>

      <tr align="left" valign="top">
        <dailyReserve:dailywrite name="sisetu" from="<%= fromHour.toString() %>" to="<%= toHour.toString() %>" dspDate="<%= dspDate.toString() %>" count="<%= memoriCount.toString() %>" />
      </tr>
    </logic:iterate>
  </logic:notEmpty>
  </logic:notEmpty>

  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="100%">&nbsp;
  </td>
  </tr>
  <tr>
  <td width="100%" align="right">
  <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
  <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  </tr>
  </table>

</td>
</tr>



</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>