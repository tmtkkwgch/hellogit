<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../timecard/js/tcd010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../timecard/css/timecard.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="tcd.tcd010.17" /></title>
</head>

<body class="body_03">

<html:form action="/timecard/tcd010">
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="editDay" />
<html:hidden property="dakokuStrSetFlg" />
<html:hidden property="dakokuEndSetFlg" />
<input type="hidden" name="CMD" value="init">

<logic:equal name="tcd010Form" property="usrKbn" value="0">
<html:hidden property="sltGroupSid" />
<html:hidden property="usrSid" />
</logic:equal>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%" border="0">
<tr>
<td width="100%" align="center" colspan="2">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../timecard/images/header_timecard_01.gif" border="0" alt="<gsmsg:write key="tcd.50" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="tcd.50" /></span></td>
  <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="tcd.tcd010.18" /> ]</td>
  <td width="100%" class="header_white_bg">
  <logic:notEqual name="tcd010Form" property="usrKbn" value="0">
    <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('admtool');">
  </logic:notEqual>
    <input type="button" name="btn_pri_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pritool');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="tcd.50" />"></td>
  </tr>
  </table>

</td>
</tr>

<tr>
<td width="30%">&nbsp;</td>
<td width="70%" align="left">

  <html:messages id="msg" message="false" >
  <span class="text_error">
  <bean:write name="msg" ignore="true" />
  </span>
  </html:messages>

  <table width="100%" class="tl0" border="0">

  <logic:notEqual name="tcd010Form" property="usrKbn" value="0">
  <tr>
  <td colspan="2" width="100%" align="left" nowrap>
    <span class="text_tlw_black"><gsmsg:write key="cmn.show.group" /></span>
    <html:select property="sltGroupSid" styleClass="select01" onchange="changeGroupCombo();">
      <html:optionsCollection name="tcd010Form" property="tcd010GpLabelList" value="value" label="label" />
    </html:select>
    <input type="button" onclick="openGroupWindow(this.form.sltGroupSid, 'sltGroupSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="tcd010GroupBtn">
    <html:select property="usrSid" styleClass="select01" onchange="changeUserCombo();">
      <html:optionsCollection name="tcd010Form" property="tcd010UsrLabelList" value="value" label="label" />
    </html:select>
  </td>
  </tr>
  </logic:notEqual>

  <logic:notEmpty name="tcd010Form" property="tcd010UsrLabelList">
  <tr>
  <td align="left" nowrap>
    <logic:equal name="tcd010Form" property="tcd010LockFlg" value="0">
    <logic:equal name="tcd010Form" property="tcd010FailFlg" value="0">
    <input type="button" class="btn_edits" onClick="multiEditButton();" value="<gsmsg:write key="cmn.multiple.edit" />">
    <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('del');">
    </logic:equal>
    </logic:equal>
    <logic:equal name="tcd010Form" property="tcd010FailFlg" value="0">

    <logic:equal name="tcd010Form" property="kinmuOut" value="0">
    <input type="button" value="<gsmsg:write key="tcd.tcd010.12" />" class="btn_excel_n2" onClick="buttonPush('xls');">
    </logic:equal>
    <logic:equal name="tcd010Form" property="kinmuOut" value="1">
    <input type="button" value="<gsmsg:write key="tcd.tcd010.12" />" class="btn_pdf_n2" onClick="buttonPush('pdf');">
    </logic:equal>

    <input type="button" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onClick="buttonPush('csv');">
    </logic:equal>
  </td>


  <td align="right" nowrap>

    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_last');">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.thismonth3" />" onClick="buttonPush('move_now');">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_next');">


  </td>
  <td valign="top">
  <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.tcdDspFrom, 'sch200Btn')" class="calendar_btn2", id="sch200Btn">
  </td>
  </tr>
  </logic:notEmpty>
  </table>

</td>
</tr>

<logic:notEmpty name="tcd010Form" property="tcd010UsrLabelList">
<tr>
<!--勤怠集計-->
<td class="t_top">

  <table width="100%" class="tl0 table_td_border" border="0" cellpadding="5">
  <tr>
  <td align="center" class="table_bg_7D91BD" colspan="6" nowrap><span class="text_tl1"><gsmsg:write key="tcd.tcd010.13" /></span></td>
  </tr>

  <tr>
  <th colspan="2" rowspan="2" class="td_type3" nowrap>&nbsp;</th>
  <th align="center" class="td_type3" colspan="2" nowrap><bean:write name="tcd010Form" property="lastMonthString" /><gsmsg:write key="cmn.month" /></th>
  <th align="center" class="td_type3" colspan="2" nowrap><bean:write name="tcd010Form" property="thisMonthString" /><gsmsg:write key="cmn.month" /></th>
  </tr>

  <tr>
  <th class="td_type3">&nbsp;&nbsp;<gsmsg:write key="cmn.performance" />&nbsp;&nbsp;</th>
  <th class="td_type3"><gsmsg:write key="tcd.tcd010.15" /></th>
  <th class="td_type3">&nbsp;&nbsp;<gsmsg:write key="cmn.performance" />&nbsp;&nbsp;</th>
  <th class="td_type3"><gsmsg:write key="tcd.tcd010.15" /></th>
  </tr>

  <bean:define id="lastMdl" name="tcd010Form" property="lastMonthMdl"/>
  <bean:define id="thisMdl" name="tcd010Form" property="thisMonthMdl"/>
  <bean:define id="totalYearMdl" name="tcd010Form" property="totalYearMdl"/>
  <tr>
  <th rowspan="2" align="center" class="td_type3" width="15%" nowrap><gsmsg:write key="tcd.tcd010.16" /></th>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.working.day" /></th>
  <td align="right" width="10%"><bean:write name="lastMdl" property="kadoDaysStr" /></td>
  <td align="right" width="10%"><bean:write name="lastMdl" property="kadoBaseDaysStr" /></td>
  <td align="right" width="10%"><bean:write name="thisMdl" property="kadoDaysStr" /></td>
  <td align="right" width="10%"><bean:write name="thisMdl" property="kadoBaseDaysStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.running.time" /></th>
  <td align="right"><bean:write name="lastMdl" property="kadoHoursStr" /></td>
  <td align="right"><bean:write name="lastMdl" property="kadoBaseHoursStr" /></td>
  <td align="right"><bean:write name="thisMdl" property="kadoHoursStr" /></td>
  <td align="right"><bean:write name="thisMdl" property="kadoBaseHoursStr" /></td>
  </tr>

  <tr>
  <th rowspan="2" align="center" class="td_type3" width="15%" nowrap><gsmsg:write key="tcd.tcd010.09" /></th>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.working.day" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="zangyoDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="zangyoDaysStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.running.time" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="zangyoHoursStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="zangyoHoursStr" /></td>
  </tr>

  <tr>
  <th rowspan="2" align="center" class="td_type3" width="15%" nowrap><gsmsg:write key="tcd.tcd010.06" /></th>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.working.day" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="sinyaDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="sinyaDaysStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.running.time" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="sinyaHoursStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="sinyaHoursStr" /></td>
  </tr>

  <tr>
  <th rowspan="2" align="center" class="td_type3" width="15%" nowrap><gsmsg:write key="tcd.tcd010.14" /></th>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.working.day" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="kyusyutuDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="kyusyutuDaysStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" nowrap><gsmsg:write key="tcd.running.time" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="kyusyutuHoursStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="kyusyutuHoursStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="tcd.18" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="chikokuTimesStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="chikokuTimesStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="tcd.22" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="soutaiTimesStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="soutaiTimesStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="tcd.34" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="kekkinDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="kekkinDaysStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="tcd.35" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="keityoDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="keityoDaysStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="tcd.03" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="yuukyuDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="yuukyuDaysStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="tcd.19" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="daikyuDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="daikyuDaysStr" /></td>
  </tr>

  <tr>
  <th colspan="2" align="center" class="td_type3" nowrap><gsmsg:write key="cmn.other" /></th>
  <td align="right" colspan="2"><bean:write name="lastMdl" property="sonotaDaysStr" /></td>
  <td align="right" colspan="2"><bean:write name="thisMdl" property="sonotaDaysStr" /></td>
  </tr>
  </table>

  <br>

  <!--月別集計-->

  <table width="100%" class="tl0 table_td_border" border="0" cellpadding="5">
  <tr>
  <td align="center" class="table_bg_7D91BD" colspan="7" nowrap><span class="text_tl1"><gsmsg:write key="tcd.tcd010.11" /></span></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" width="22%" nowrap></th>
  <th align="center" class="td_type3" colspan="2" width="25%"><gsmsg:write key="tcd.tcd010.16" /></th>
  <th align="center" class="td_type3" colspan="2" width="25%"><gsmsg:write key="tcd.tcd010.09" /></th>
  <th align="center" class="td_type3" colspan="2" width="25%"><gsmsg:write key="tcd.tcd010.05" /></th>
  </tr>

  <logic:notEmpty name="tcd010Form" property="monthTtlList" scope="request">
  <logic:iterate id="monthTtlMdl" name="tcd010Form" property="monthTtlList" scope="request" indexId="cnt">

  <tr>
  <th align="center" class="td_type3" width="22%" nowrap><bean:write name="monthTtlMdl" property="kadoMonth" /><gsmsg:write key="cmn.month" /></th>
  <td align="right" colspan="2" width="25%"><bean:write name="monthTtlMdl" property="kadoHoursStr" /></td>
  <td align="right" colspan="2" width="25%"><bean:write name="monthTtlMdl" property="zangyoHours" /></td>
  <td align="right" colspan="2" width="25%"><bean:write name="monthTtlMdl" property="sinyaHours" /></td>
  </tr>

  </logic:iterate>
  </logic:notEmpty>

  </table>

  <br>

  <!--年度集計-->

  <table width="100%" class="tl0 table_td_border3" border="0" cellpadding="5">


  <tr>
  <th align="right" class="double_header_7D91BD_left2" colspan="2" width="20%" nowrap><span class="text_tl1">&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="tcd.tcd010.03" />&nbsp;</span></th>
  <th align="left" class="double_header_7D91BD_right2"  nowrap><span class="text_tcd_header">
  <bean:define id="yr" name="tcd010Form" property="nendYear" type="java.lang.String" />
  <bean:define id="yr2" name="tcd010Form" property="endYear" type="java.lang.String" />
  <gsmsg:write key="cmn.year" arg0="<%= yr %>" />&nbsp;<bean:write name="tcd010Form" property="kishuMonth" /><gsmsg:write key="cmn.month" /><gsmsg:write key="tcd.153" /><gsmsg:write key="cmn.year" arg0="<%= yr2 %>" />&nbsp;<bean:write name="tcd010Form" property="kimatuMonth" /><gsmsg:write key="cmn.month" /></span>
  </th>
  </tr>

  <tr>
  <th align="center" class="td_type3" width="23%" nowrap>&nbsp;&nbsp;&nbsp;<gsmsg:write key="tcd.18" />&nbsp;&nbsp;&nbsp;</th>
  <td align="right" class="td_type16_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  <td align="right" class="td_type16_right"><bean:write name="totalYearMdl" property="chikokuTimesStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" width="70%" nowrap>&nbsp;&nbsp;&nbsp;<gsmsg:write key="tcd.22" />&nbsp;&nbsp;&nbsp;</th>
  <td align="right" class="td_type16_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  <td align="right" class="td_type16_right""><bean:write name="totalYearMdl" property="soutaiTimesStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" width="70%" nowrap>&nbsp;&nbsp;&nbsp;<gsmsg:write key="tcd.34" />&nbsp;&nbsp;&nbsp;</th>
  <td align="right" class="td_type16_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  <td align="right" class="td_type16_right"><bean:write name="totalYearMdl" property="kekkinDaysStr" /></td>
  </tr>

  <tr>
  <th align="center" class="td_type3" width="70%" nowrap>&nbsp;&nbsp;&nbsp;<gsmsg:write key="tcd.03" />&nbsp;&nbsp;&nbsp;</th>
  <td align="right" class="td_type16_left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  <td align="right" class="td_type16_right""><bean:write name="totalYearMdl" property="yuukyuDaysStr" /></td>
  </tr>



  </table>


</td>


<!--一覧表-->
<td class="t_top">

  <table cellpadding="5" cellspacing="0" width="100%" class="tl0 table_td_border">
  <tr>
  <td colspan="8" class="table_bg_7D91BD" align="left">
  <bean:define id="yr2" name="tcd010Form" property="year" type="java.lang.String" />
  <span class="text_tl1"><gsmsg:write key="cmn.year" arg0="<%= yr2 %>" /><bean:write name="tcd010Form" property="month" /><gsmsg:write key="cmn.month" /></span>
  </td>
  </tr>

  <% String[] tdCols = new String[] {"1", "2"}; %>
  <% String tddayCols = ""; %>
  <% String tdbikoCols = ""; %>

  <% tdbikoCols = tdCols[0]; %>

  <logic:equal name="tcd010Form" property="tcd010FailFlg" value="0">
  <% tddayCols = tdCols[0]; %>
  </logic:equal>
  <logic:equal name="tcd010Form" property="tcd010FailFlg" value="1">
  <% tddayCols = tdCols[1]; %>
  </logic:equal>


  <bean:define id="dayCols" value="<%= tddayCols %>" />
  <bean:define id="bikoCols" value="<%= tdbikoCols %>" />

  <tr>
  <logic:equal name="dayCols" value="1" scope="page">
  <th width="0%" class="td_type3" colspan="1" nowrap><input type="checkbox" name="allChk" onClick="changeChk();"></th>
  <th width="0%" class="td_type3" colspan="1" nowrap><gsmsg:write key="cmn.date2" /></th>
  </logic:equal>
  <logic:equal name="dayCols" value="2" scope="page">
  <th width="0%" class="td_type3" colspan="2" nowrap><gsmsg:write key="cmn.date2" /></th>
  </logic:equal>

  <th width="1%" class="td_type3" nowrap><gsmsg:write key="tcd.21" /><br><gsmsg:write key="tcd.tcd010.08" /></th>
  <th width="20%" class="td_type3" nowrap><gsmsg:write key="tcd.28" /></th>
  <th width="20%" class="td_type3" nowrap><gsmsg:write key="tcd.24" /></th>
  <th width="50%" class="td_type3" colspan="3" nowrap><gsmsg:write key="cmn.memo" /></th>
  </tr>

  <logic:notEmpty name="tcd010Form" property="tcd010List" scope="request">
  <logic:iterate id="tdMdl" name="tcd010Form" property="tcd010List" scope="request" indexId="cnt">

  <bean:define id="tdColor" value="" />
  <bean:define id="week" name="tdMdl" property="tcd010Week" type="java.lang.Integer" />
  <% String[] tdColors = new String[] {"td_type9", "td_type1", "td_type1", "td_type1", "td_type1", "td_type1", "td_type8"}; %>
  <% tdColor = tdColors[(week.intValue()-1)]; %>
  <tr>

  <logic:equal name="dayCols" value="1" scope="page">
  <td class="<%= tdColor %>" width="0%" align="center">
  <bean:define id="day" name="tdMdl" property="tcd010Day" type="java.lang.Integer" />
    <html:multibox property="selectDay" value="<%= Integer.toString(day.intValue()) %>"/>
  </td>
  </logic:equal>

<!--休日以外-->
  <logic:equal name="tdMdl" property="holKbn" value="0">
  <logic:equal name="tdMdl" property="tcd010Week" value="1">
  <th class="<%= tdColor %>" width="0%" align="center" colspan="<%= dayCols %>">
  <span class="sc_ttl_sun"><bean:write name="tdMdl" property="tcd010Day" /><gsmsg:write key="cmn.day" />(<bean:write name="tdMdl" property="tcd010WeekStr" />) </span></th>
  </logic:equal>
  <logic:equal name="tdMdl" property="tcd010Week" value="7">
  <th class="<%= tdColor %>" width="0%" align="center" colspan="<%= dayCols %>">
  <span class="sc_ttl_sat"><bean:write name="tdMdl" property="tcd010Day" /><gsmsg:write key="cmn.day" />(<bean:write name="tdMdl" property="tcd010WeekStr" />) </span></th>
  </logic:equal>
  <logic:notEqual name="tdMdl" property="tcd010Week" value="1">
  <logic:notEqual name="tdMdl" property="tcd010Week" value="7">
  <th class="<%= tdColor %>" width="0%" align="center" colspan="<%= dayCols %>">
  <span class="sc_ttl_def"><bean:write name="tdMdl" property="tcd010Day" /><gsmsg:write key="cmn.day" />(<bean:write name="tdMdl" property="tcd010WeekStr" />) </span></th>
  </logic:notEqual>
  </logic:notEqual>
  </logic:equal>

<!--休日-->
  <logic:notEqual name="tdMdl" property="holKbn" value="0">
  <th class="<%= tdColor %>" width="0%" align="center" colspan="<%= dayCols %>">
  <span class="sc_ttl_sun"><bean:write name="tdMdl" property="tcd010Day" /><gsmsg:write key="cmn.day" />(<bean:write name="tdMdl" property="tcd010WeekStr" />) </span></th>
  </logic:notEqual>

  <td class="<%= tdColor %> text_top" width="10%" align="center" nowrap><span class="td_strikeTime"><bean:write name="tdMdl" property="tcd010StrikeShigyouStr"/>
    <br>
    <logic:empty name="tdMdl" property="tcd010StrikeSyugyouStr">&nbsp;</logic:empty>
    <logic:notEmpty name="tdMdl" property="tcd010StrikeSyugyouStr"><bean:write name="tdMdl" property="tcd010StrikeSyugyouStr" /></logic:notEmpty>
    </span>
  </td>
  <td class="<%= tdColor %>" width="20%" align="center">
    <bean:write name="tdMdl" property="tcd010ShigyouStr" />
    <logic:equal name="tcd010Form" property="myselfFlg" value="true">
    <logic:equal name="tdMdl" property="dakokuBtnStrKbn" value="1">
      <input type="button" value="<gsmsg:write key="tcd.tcdmain.06" />" class="btn_base0" onClick="editStrDakokuButton('<bean:write name="tdMdl" property="tcd010Day" />');">
    </logic:equal>
    </logic:equal>
  </td>
  <td class="<%= tdColor %>" width="20%" align="center">
    <bean:write name="tdMdl" property="tcd010SyugyouStr" />
    <logic:equal name="tcd010Form" property="myselfFlg" value="true">
    <logic:equal name="tdMdl" property="dakokuBtnEndKbn" value="1">
      <input type="button" value="<gsmsg:write key="tcd.tcdmain.05" />" class="btn_base0" onClick="editEndDakokuButton('<bean:write name="tdMdl" property="tcd010Day" />');">
    </logic:equal>
    </logic:equal>
  </td>
  <td class="<%= tdColor %>" width="5%" align="left" nowrap>
  <span class="text_base"><bean:write name="tdMdl" property="tcd010Kyujitsu" /></span>
  </td>

  <td class="<%= tdColor %>" width="45%" align="left" colspan="<%= bikoCols %>">
  <logic:notEmpty name="tdMdl" property="holName">
  <span class="text_baser"><bean:write name="tdMdl" property="holName" /></span>
  </logic:notEmpty>
  <span class="text_gray"><bean:write name="tdMdl" property="tcd010Bikou" /></span>
  </td>

  <td class="<%= tdColor %>" width="0%" align="center">
  <logic:equal name="tcd010Form" property="tcd010FailFlg" value="0">
  <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="editButton('<bean:write name="tdMdl" property="tcd010Day" />');">
  </logic:equal>

  <logic:notEqual name="tcd010Form" property="tcd010FailFlg" value="0">
  <logic:equal name="tdMdl" property="failFlg" value="1">
  <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="editButton('<bean:write name="tdMdl" property="tcd010Day" />');">
  </logic:equal>
  </logic:notEqual>

  </td>

  </tr>

  </logic:iterate>
  </logic:notEmpty>
  </table>

</td>
</tr>

<tr>
<td width="30%">&nbsp;</td>
<td width="70%" align="center">
  <table width="100%" class="tl0" border="0">
  <tr>
  <td align="left" nowrap>
  <logic:equal name="tcd010Form" property="tcd010LockFlg" value="0">
  <logic:equal name="tcd010Form" property="tcd010FailFlg" value="0">
  <input type="button" class="btn_edits" onClick="multiEditButton();" value="<gsmsg:write key="cmn.multiple.edit" />">
  <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('del');">
  </logic:equal>
  </logic:equal>
  <logic:equal name="tcd010Form" property="tcd010FailFlg" value="0">
      <logic:equal name="tcd010Form" property="kinmuOut" value="0">
    <input type="button" value="<gsmsg:write key="tcd.tcd010.12" />" class="btn_excel_n2" onClick="buttonPush('xls');">
    </logic:equal>
    <logic:equal name="tcd010Form" property="kinmuOut" value="1">
    <input type="button" value="<gsmsg:write key="tcd.tcd010.12" />" class="btn_pdf_n2" onClick="buttonPush('pdf');">
    </logic:equal>
  <input type="button" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onClick="buttonPush('csv');">
  </logic:equal>
  </td>
  <td align="right" nowrap>

  <input type="button" class="btn_arrow_l" value="&nbsp;" onClick="buttonPush('move_last');">
  <input type="button" class="btn_today" value="<gsmsg:write key="cmn.thismonth3" />" onClick="buttonPush('move_now');">
  <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_next');">

  </td>
  <td valign="top">
  <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.tcdDspFrom, 'sch200Btn')" class="calendar_btn2", id="sch200Btn">
  </td>
  </tr>
  </table>
</td>
</tr>
</logic:notEmpty>

</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>