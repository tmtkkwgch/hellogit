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
  String project           = jp.groupsession.v2.cmn.GSConstCommon.PLUGIN_ID_PROJECT;
  String nippou            = jp.groupsession.v2.cmn.GSConstCommon.PLUGIN_ID_NIPPOU;
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch010.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../schedule/js/sch010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="sch010Form" property="sch010Reload" value="0">
    var reloadinterval = <bean:write name="sch010Form" property="sch010Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<script type="text/javascript">
function getSelVal(className){

    return false;
}
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% long schTipCnt = 0; %>
<body class="body_03" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/schedule/sch010">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="1">

<input type="hidden" name="sch010SelectUsrSid" value="-1">
<input type="hidden" name="sch010SelectUsrKbn" value="0">

<html:hidden property="cmd" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010CrangeKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />

<logic:notEmpty name="sch010Form" property="schNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="sch010Form" property="schNotAccessGroupList" scope="request">
    <input type="hidden" name="schNotAccessGroup" value="<bean:write name="notAccessGroup"/>">
  </logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
  <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.weeks" /> ]</td>
  <td width="100%" class="header_white_bg">
  <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
  <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
  <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
  <logic:equal name="sch010Form" property="adminKbn" value="1">
    <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('ktool');">
  </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pset');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  </tr>
  </table>

  <table cellpadding="5" width="100%" class="tl0">
  <tr>
  <td colspan="8" class="td_type0">
    <table cellspacing="0" width="100%" border="0">

    <tr>
    <td width="75%" align="right" nowrap>

    <bean:size id="topSize" name="sch010Form" property="sch010TopList" scope="request" />
    <logic:equal name="topSize" value="2">
    <logic:iterate id="weekBean" name="sch010Form" property="sch010TopList" scope="request" offset="1"/>
    </logic:equal>
    <logic:notEqual name="topSize" value="2">
    <logic:iterate id="weekBean" name="sch010Form" property="sch010TopList" scope="request" offset="0"/>
    </logic:notEqual>

    <bean:define id="usrBean" name="weekBean" property="sch010UsrMdl"/>
      <input type="button" value="<gsmsg:write key="schedule.19" />" class="btn_week_kojin" onClick="moveKojinSchedule('kojin', <bean:write name="sch010Form" property="sch010DspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onClick="moveDailySchedule('day', <bean:write name="sch010Form" property="sch010DspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
      <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('reload');">
      <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="moveMonthSchedule('month', <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
      <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onClick="moveListSchedule('list', <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
    </td>

    <td width="0%" align="right" valign="top" nowrap>
      <input type="button" class="btn_arrow1_l" value="&nbsp;" onclick="buttonPush('move_lw');">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_ld');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('today');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rd');">
      <input type="button" class="btn_arrow1_r" value="&nbsp;" onclick="buttonPush('move_rw');">
    </td>

    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="resetCmd();sch010OpenCalendar();wrtCalendarByBtn(this.form.sch010DspDate, 'sch010CalBtn')" class="calendar_btn2" id="sch010CalBtn">
    </td>

    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td colspan="8" class="table_bg_7D91BD" align="center">
    <table width="100%" class="tl0">
      <tr>
      <td width="15%" align="left">
        <span class="text_tl1"><bean:write name="sch010Form" property="sch010StrDspDate" /></span>
      </td>

      <td width="60%" align="left" nowrap>
        <span class="text_tlw"><gsmsg:write key="cmn.show.group" /></span>

      <html:select property="sch010DspGpSid" styleClass="select01" onchange="changeGroupCombo();getSelVal('select01');">
        <logic:notEmpty name="sch010Form" property="sch010GpLabelList" scope="request">
        <logic:iterate id="gpBean" name="sch010Form" property="sch010GpLabelList" scope="request">

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


      </html:select>
      <logic:equal name="sch010Form" property="sch010CrangeKbn" value="0">
        <input type="button" onclick="openGroupWindow(this.form.sch010DspGpSid, 'sch010DspGpSid', '1', '', 0, '', 'schNotAccessGroup');" class="group_btn" value="&nbsp;&nbsp;" id="sch010GroupBtn">
      </logic:equal>
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

  <% String grpHeight = "105"; %>
  <logic:notEqual name="sch010Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <% grpHeight = "85"; %>
  </logic:notEqual>


  <!-- タイトル(氏名,日付) -->
  <tr>
  <th width="16%" class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <logic:notEmpty name="sch010Form" property="sch010CalendarList" scope="request">
  <logic:iterate id="calBean" name="sch010Form" property="sch010CalendarList" scope="request">

    <bean:define id="tdColor" value="" />
    <bean:define id="fontColorSun" value="" />
    <bean:define id="fontColorSat" value="" />
    <bean:define id="fontColorDef" value="" />
    <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
    <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
    <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
    <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

    <logic:equal name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[1]; %>
    <% fontColorSun = fontColorsSun[1]; %>
    <% fontColorSat = fontColorsSat[1]; %>
    <% fontColorDef = fontColorsDef[1]; %>
    </logic:equal>
    <logic:notEqual name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[0]; %>
    <% fontColorSun = fontColorsSun[0]; %>
    <% fontColorSat = fontColorsSat[0]; %>
    <% fontColorDef = fontColorsDef[0]; %>
    </logic:notEqual>

  <logic:equal name="calBean" property="holidayKbn" value="1">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:notEqual name="calBean" property="holidayKbn" value="1">
  <logic:equal name="calBean" property="weekKbn" value="1">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:equal name="calBean" property="weekKbn" value="7">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSat %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:notEqual name="calBean" property="weekKbn" value="1">
  <logic:notEqual name="calBean" property="weekKbn" value="7">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorDef %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:notEqual>
  </logic:notEqual>
  </logic:notEqual>

  </logic:iterate>
  </logic:notEmpty>
  </tr>



  <!-- グループ,本人 -->
  <logic:notEmpty name="sch010Form" property="sch010TopList" scope="request">
  <logic:iterate id="weekMdl" name="sch010Form" property="sch010TopList" scope="request">
  <tr align="left" valign="top">
  <bean:define id="usrMdl" name="weekMdl" property="sch010UsrMdl"/>

  <!-- グループ -->
  <logic:equal name="usrMdl" property="usrKbn" value="1">
    <td rowspan="<bean:write name="weekMdl" property="sch010PeriodRow" />" class="td_type1" >
    <span id="lt"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0"></span>
    <span id="lt" class="text_bb1"><bean:write name="usrMdl" property="usrName" /></span><br>
    <span>
      <a href="#" onClick="moveMonthSchedule('month', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_gekkan_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.monthly" /></span>
      </a><br>
      <a href="#" onClick="moveListSchedule('list', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_ichiran_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.list" /></span>
      </a>
    </span>
    </td>
  </logic:equal>

  <!-- 本人 -->
  <logic:notEqual name="usrMdl" property="usrKbn" value="1">
  <logic:equal name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
    <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
    <td rowspan="<bean:write name="weekMdl" property="sch010PeriodRow" />" class="td_type1 break">
    </logic:equal>
    <logic:equal name="usrMdl" property="zaisekiKbn" value="2">
    <td rowspan="<bean:write name="weekMdl" property="sch010PeriodRow" />" class="td_type_gaisyutu  break">
    </logic:equal>
    <logic:equal name="usrMdl" property="zaisekiKbn" value="0">
    <td rowspan="<bean:write name="weekMdl" property="sch010PeriodRow" />" class="td_type_kekkin  break">
    </logic:equal>
  </logic:equal>
  <logic:notEqual name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
    <td rowspan="<bean:write name="weekMdl" property="sch010PeriodRow" />" class="td_type1  break">
  </logic:notEqual>

    <span>
    <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="usrMdl" property="usrSid" />);"><bean:write name="usrMdl" property="usrName" /></a>
    </span>
    <span class="sc_link_5"><bean:write name="usrMdl" property="zaisekiMsg" /></span><br>
    <span>
      <a href="#" onClick="moveMyMonthSchedule('month', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />, '<bean:write name="sch010Form" property="sysDfGroupSid" />');"><img src="../common/images/btn_gekkan_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.monthly" /></span>
      </a><br>
      <a href="#" onClick="moveListSchedule('list', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_ichiran_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.list" /></span>
      </a><br>
      <logic:equal name="sch010Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <logic:equal name="usrMdl" property="smlAble" value="1">
        <a href="#" class="sml_send_link" id="<bean:write name="usrMdl" property="usrSid" />"><img src="../common/images/btn_smail_bg.gif" width="35" height="18" alt="" border="0">
      <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.shortmail" /></span>
      </a><br>
      </logic:equal>
      </logic:equal>
      <!-- 在席 -->
      <logic:equal name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
      <input type="button" onClick="setFuzai(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#417984;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_fuzai" value="<gsmsg:write key="cmn.absence" />">
      <input type="button" onClick="setSonota(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_sonota" value="<gsmsg:write key="cmn.other" />">
      </logic:equal>
      <logic:notEqual name="usrMdl" property="zaisekiKbn" value="1">
      <!-- 不在、その他 -->

      <input type="button" onClick="setZaiseki(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_zaiseki" value="<gsmsg:write key="cmn.zaiseki" />">
      <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
      </logic:notEqual>
      </logic:equal>
      <logic:notEqual name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
      <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
      </logic:notEqual>
    </span>
    </td>
  </logic:notEqual>

  <!-- スケジュール情報 -->
  <logic:notEmpty name="weekMdl" property="sch010SchList">
  <logic:iterate id="dayMdl" name="weekMdl" property="sch010SchList">

  <logic:equal name="dayMdl" property="weekKbn" value="1">
  <logic:notEqual name="dayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_type9">
  </logic:notEqual>
  <logic:equal name="dayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_cal_today">
  </logic:equal>
  </logic:equal>
  <logic:equal name="dayMdl" property="weekKbn" value="7">

  <logic:notEqual name="dayMdl" property="todayKbn" value="1">
  <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_type8">
  </logic:notEqual>
  <logic:equal name="dayMdl" property="todayKbn" value="1">
  <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_cal_today">
  </logic:equal>
  </logic:equal>

  <logic:notEqual name="dayMdl" property="weekKbn" value="1">
  <logic:notEqual name="dayMdl" property="weekKbn" value="7">
    <logic:equal name="dayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_cal_today">
    </logic:equal>
    <logic:notEqual name="dayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_type1">
    </logic:notEqual>

  </logic:notEqual>
  </logic:notEqual>
    <logic:equal name="usrMdl" property="schRegistFlg" value="true">
      <span id="lt"><a href="#" onClick="return addSchedule('add', <bean:write name="dayMdl" property="schDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);"><img src="../schedule/images/scadd.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0" ></a></span>
    </logic:equal>

    <logic:notEmpty name="dayMdl" property="holidayName">
    <span id="rt"><font size="-2" color="#ff0000"><bean:write name="dayMdl" property="holidayName" /></font></span>
    </logic:notEmpty>

    <logic:notEmpty name="dayMdl" property="schDataList">
    <logic:iterate id="schMdl" name="dayMdl" property="schDataList">

      <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
      <bean:define id="u_schsid" name="schMdl" property="schSid" type="java.lang.Integer" />
      <bean:define id="u_date" name="dayMdl" property="schDate"  type="java.lang.String" />
      <bean:define id="u_public" name="schMdl" property="public"  type="java.lang.Integer" />
      <bean:define id="u_kjnEdKbn" name="schMdl" property="kjnEdKbn"  type="java.lang.Integer" />
      <bean:define id="u_grpEdKbn" name="schMdl" property="grpEdKbn"  type="java.lang.Integer" />

      <%
        int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
        int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
        int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
      %>
      <br>
      <!--公開-->
      <%
      if ((publicType == dspPublic) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
      %>

      <logic:empty name="schMdl" property="valueStr">
        <logic:equal name="schMdl" property="userKbn" value="0">
          <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="schMdl" property="userKbn" />);">
        </logic:equal>
        <logic:notEqual name="schMdl" property="userKbn" value="0">
          <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="schMdl" property="userSid" />, <bean:write name="schMdl" property="userKbn" />);">
        </logic:notEqual>
        <span class="tooltips"><bean:write name="schMdl" property="title" /></span>
      </logic:empty>
      <logic:notEmpty name="schMdl" property="valueStr">
      <bean:define id="scnaiyou" name="schMdl" property="valueStr" />
      <%
        String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
      %>
      <logic:equal name="schMdl" property="userKbn" value="0">
        <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="schMdl" property="userKbn" />);">
      </logic:equal>
      <logic:notEqual name="schMdl" property="userKbn" value="0">
        <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="schMdl" property="userSid" />, <bean:write name="schMdl" property="userKbn" />);">
      </logic:notEqual>
      <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
      </logic:notEmpty>

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
      <logic:equal name="dayMdl" property="usrKbn" value="0">
        <logic:equal name="schMdl" property="userKbn" value="1">
          <span class="sc_link_g">G</span>
        </logic:equal>
      </logic:equal>
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
      <span class="sc_nolink">
        <logic:equal name="dayMdl" property="usrKbn" value="0">
          <logic:equal name="schMdl" property="userKbn" value="1">
            <span class="sc_link_g">G</span>
          </logic:equal>
        </logic:equal>
        <logic:notEmpty name="schMdl" property="time">
        <font size="-2"><bean:write name="schMdl" property="time" /><br></font>
        </logic:notEmpty>
        <bean:write name="schMdl" property="title" />
      </span>

      <%
       }
      %>

    </logic:iterate>
    </logic:notEmpty>
  </td>

  </logic:iterate>

  </logic:notEmpty>
  </tr>

  <!--期間スケジュール-->
  <logic:notEmpty name="weekMdl" property="sch010NoTimeSchList">
    <logic:iterate id="periodList" name="weekMdl" property="sch010NoTimeSchList">
      <logic:notEmpty name="periodList">
        <tr class="td_type33">

          <bean:define id="prList" name="periodList" type="java.util.ArrayList"/>
          <% int size = prList.size(); %>

          <logic:iterate id="uPeriodMdl" name="periodList" indexId="pIdx">

            <logic:notEmpty name="uPeriodMdl" property="periodMdl">
              <bean:define id="pMdl" name="uPeriodMdl" property="periodMdl"/>

              <td class="td_type1 td_type_kikan" colspan="<bean:write name="pMdl" property="schPeriodCnt" />">

                  <bean:define id="p_schsid" name="uPeriodMdl" property="schSid" type="java.lang.Integer" />
                  <bean:define id="p_public" name="uPeriodMdl" property="public"  type="java.lang.Integer" />
                  <bean:define id="p_kjnEdKbn" name="uPeriodMdl" property="kjnEdKbn"  type="java.lang.Integer" />
                  <bean:define id="p_grpEdKbn" name="uPeriodMdl" property="grpEdKbn"  type="java.lang.Integer" />
                  <%
                    int publicType = ((Integer)pageContext.getAttribute("p_public",PageContext.PAGE_SCOPE));
                    int kjnEdKbn = ((Integer)pageContext.getAttribute("p_kjnEdKbn",PageContext.PAGE_SCOPE));
                    int grpEdKbn = ((Integer)pageContext.getAttribute("p_grpEdKbn",PageContext.PAGE_SCOPE));
                  %>

                  <!--公開-->
                  <%
                  if ((publicType == dspPublic) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
                  %>

                  <logic:empty name="uPeriodMdl" property="schAppendUrl">
                    <logic:empty name="uPeriodMdl" property="valueStr">
                      <logic:equal name="uPeriodMdl" property="userKbn" value="0">
                        <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="uPeriodMdl" property="userKbn" />);">
                      </logic:equal>
                      <logic:notEqual name="uPeriodMdl" property="userKbn" value="0">
                        <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, <bean:write name="uPeriodMdl" property="userSid" />, <bean:write name="uPeriodMdl" property="userKbn" />);">
                      </logic:notEqual>
                      <span class="tooltips"><bean:write name="uPeriodMdl" property="title" /></span>
                    </logic:empty>
                    <logic:notEmpty name="uPeriodMdl" property="valueStr">
                    <bean:define id="scnaiyou" name="uPeriodMdl" property="valueStr" />
                    <%
                      String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                      String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                    %>
                    <logic:equal name="uPeriodMdl" property="userKbn" value="0">
                      <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="uPeriodMdl" property="userKbn" />);">
                    </logic:equal>
                    <logic:notEqual name="uPeriodMdl" property="userKbn" value="0">
                      <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, <bean:write name="uPeriodMdl" property="userSid" />, <bean:write name="uPeriodMdl" property="userKbn" />);">
                    </logic:notEqual>
                    <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                    </logic:notEmpty>
                  </logic:empty>

                  <logic:notEmpty name="uPeriodMdl" property="schAppendUrl">
                    <logic:empty name="uPeriodMdl" property="valueStr">
                    <a href="<bean:write name="uPeriodMdl" property="schAppendUrl" />" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>">
                    <% boolean schFilter = true; %>
                    <logic:equal name="uPeriodMdl" property="userKbn" value="<%= project %>">
                      <% schFilter = false; %>
                    </logic:equal>
                    <span class="tooltips"><bean:write name="uPeriodMdl" property="title" filter="<%= schFilter %>"/></span>
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
                  <% boolean schFilter = true; %>
                  <logic:equal name="dayMdl" property="usrKbn" value="0">
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
                  </logic:equal>
                    <logic:notEmpty name="uPeriodMdl" property="time">
                    <font size="-2"><bean:write name="uPeriodMdl" property="time" /><br></font>
                    </logic:notEmpty>
                    <bean:write name="uPeriodMdl" property="title" filter="<%= schFilter %>" />
                  </span>
                  </a>

                  <%
                   } else {
                  %>

                  <!--非公開-->
                  <span class="sc_nolink">
                    <logic:equal name="dayMdl" property="usrKbn" value="0">
                      <logic:equal name="uPeriodMdl" property="userKbn" value="1">
                        <span class="sc_link_g">G</span>
                      </logic:equal>
                    </logic:equal>
                    <logic:notEmpty name="uPeriodMdl" property="time">
                    <font size="-2"><bean:write name="uPeriodMdl" property="time" /><br></font>
                    </logic:notEmpty>
                    <bean:write name="uPeriodMdl" property="title" />
                  </span>

                  <%
                   }
                  %>
              </td>
            </logic:notEmpty>
            <logic:empty name="uPeriodMdl" property="periodMdl">

              <% if ((Integer.valueOf(pIdx) + 1) == (Integer.valueOf(size))) { %>
              <td class="td_type_kikan3"></td>
              <% } else { %>
              <td class="td_type_kikan2"></td>
              <% } %>

            </logic:empty>
          </logic:iterate>
        </tr>
      </logic:notEmpty>
    </logic:iterate>
  </logic:notEmpty>


  <!-- グループと本人の間のボーダー -->
  <logic:equal name="usrMdl" property="usrKbn" value="1">
  <tr><td colspan="8" class="table_sch_bg_type1"><img src="../schedule/images/damy.gif" width="1" height="3" alt="<gsmsg:write key="cmn.space" />"></td></tr>
  </logic:equal>

  </logic:iterate>
  </logic:notEmpty>


  <!-- グループメンバー -->
  <tr>
  <td colspan="8" class="table_bg_7D91BD" align="left">
    <span class="text_tlwn">&nbsp;<gsmsg:write key="schedule.74" /></span>

  </td>
  </tr>

  <!-- タイトル(氏名,日付) -->
  <tr>
  <th width="16%" class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <logic:notEmpty name="sch010Form" property="sch010CalendarList" scope="request">
  <logic:iterate id="calBean" name="sch010Form" property="sch010CalendarList" scope="request">

    <bean:define id="tdColor" value="" />
    <bean:define id="fontColorSun" value="" />
    <bean:define id="fontColorSat" value="" />
    <bean:define id="fontColorDef" value="" />
    <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
    <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
    <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
    <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

    <logic:equal name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[1]; %>
    <% fontColorSun = fontColorsSun[1]; %>
    <% fontColorSat = fontColorsSat[1]; %>
    <% fontColorDef = fontColorsDef[1]; %>
    </logic:equal>
    <logic:notEqual name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[0]; %>
    <% fontColorSun = fontColorsSun[0]; %>
    <% fontColorSat = fontColorsSat[0]; %>
    <% fontColorDef = fontColorsDef[0]; %>
    </logic:notEqual>

  <logic:equal name="calBean" property="holidayKbn" value="1">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:notEqual name="calBean" property="holidayKbn" value="1">
  <logic:equal name="calBean" property="weekKbn" value="1">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:equal name="calBean" property="weekKbn" value="7">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSat %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:notEqual name="calBean" property="weekKbn" value="1">
  <logic:notEqual name="calBean" property="weekKbn" value="7">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorDef %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:notEqual>
  </logic:notEqual>
  </logic:notEqual>

  </logic:iterate>
  </logic:notEmpty>
  </tr>


  <!-- グループメンバースケジュール表示 -->
  <!-- グループメンバー -->
  <logic:notEmpty name="sch010Form" property="sch010BottomList" scope="request">
  <logic:iterate id="gpWeekMdl" name="sch010Form" property="sch010BottomList" scope="request" indexId="cnt">

  <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
  <logic:notEqual name="cnt" value="0" scope="page">
  <logic:equal name="ret" value="0">
  <!-- 5行毎にタイトル(氏名,日付) -->
  <tr>
  <th width="16%" class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <logic:notEmpty name="sch010Form" property="sch010CalendarList" scope="request">
  <logic:iterate id="calBean" name="sch010Form" property="sch010CalendarList" scope="request">

    <bean:define id="tdColor" value="" />
    <bean:define id="fontColorSun" value="" />
    <bean:define id="fontColorSat" value="" />
    <bean:define id="fontColorDef" value="" />
    <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
    <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
    <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
    <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

    <logic:equal name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[1]; %>
    <% fontColorSun = fontColorsSun[1]; %>
    <% fontColorSat = fontColorsSat[1]; %>
    <% fontColorDef = fontColorsDef[1]; %>
    </logic:equal>
    <logic:notEqual name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[0]; %>
    <% fontColorSun = fontColorsSun[0]; %>
    <% fontColorSat = fontColorsSat[0]; %>
    <% fontColorDef = fontColorsDef[0]; %>
    </logic:notEqual>

  <logic:equal name="calBean" property="holidayKbn" value="1">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:notEqual name="calBean" property="holidayKbn" value="1">
  <logic:equal name="calBean" property="weekKbn" value="1">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:equal name="calBean" property="weekKbn" value="7">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorSat %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:equal>

  <logic:notEqual name="calBean" property="weekKbn" value="1">
  <logic:notEqual name="calBean" property="weekKbn" value="7">
  <th width="12%" nowrap class="<%= tdColor %>"><a href="#" onClick="moveDailySchedule('day', <bean:write name="calBean" property="dspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
  <span class="<%= fontColorDef %>"><bean:write name="calBean" property="dspDayString" /></span></a></th>
  </logic:notEqual>
  </logic:notEqual>
  </logic:notEqual>


  </logic:iterate>
  </logic:notEmpty>
  </tr>
  </logic:equal>
  </logic:notEqual>

  <tr align="left" valign="top">
  <!-- ユーザ欄 -->
  <bean:define id="usrMdl" name="gpWeekMdl" property="sch010UsrMdl"/>

  <!-- ユーザ氏名 -->
  <logic:equal name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
  <td rowspan="<bean:write name="gpWeekMdl" property="sch010PeriodRow" />" class="td_type1  break">
  </logic:equal>
  <logic:equal name="usrMdl" property="zaisekiKbn" value="2">
  <td rowspan="<bean:write name="gpWeekMdl" property="sch010PeriodRow" />" class="td_type_gaisyutu  break">
  </logic:equal>
  <logic:equal name="usrMdl" property="zaisekiKbn" value="0">
  <td rowspan="<bean:write name="gpWeekMdl" property="sch010PeriodRow" />" class="td_type_kekkin  break">
  </logic:equal>
  </logic:equal>
  <logic:notEqual name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <td rowspan="<bean:write name="gpWeekMdl" property="sch010PeriodRow" />" class="td_type1  break">
  </logic:notEqual>

    <span>
    <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="usrMdl" property="usrSid" />);"><bean:write name="usrMdl" property="usrName" /></a>
    </span>
    <span class="sc_link_5"><bean:write name="usrMdl" property="zaisekiMsg" /></span><br>

    <span>
      <a href="#" onClick="moveMonthSchedule('month', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_gekkan_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.monthly" /></span>
      </a><br>
      <a href="#" onClick="moveListSchedule('list', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_ichiran_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.list" /></span>
      </a><br>
      <logic:equal name="sch010Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <logic:equal name="usrMdl" property="smlAble" value="1">
      <a href="#" class="sml_send_link" id="<bean:write name="usrMdl" property="usrSid" />"><img src="../common/images/btn_smail_bg.gif" width="35" height="18" alt="" border="0">
        <span class="sc_link_1_vargin_top"><gsmsg:write key="cmn.shortmail" /></span>
      </a><br>
      </logic:equal>
      </logic:equal>
      <!-- 在席 -->
      <logic:equal name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
      <input type="button" onClick="setFuzai(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#417984;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_fuzai" value="<gsmsg:write key="cmn.absence" />">
      <input type="button" onClick="setSonota(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_sonota" value="<gsmsg:write key="cmn.other" />">
      </logic:equal>
      <logic:notEqual name="usrMdl" property="zaisekiKbn" value="1">
      <!-- 不在、その他 -->
      <input type="button" onClick="setZaiseki(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_zaiseki" value="<gsmsg:write key="cmn.zaiseki" />">
      <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
      </logic:notEqual>
      </logic:equal>
      <logic:notEqual name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
      <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
      </logic:notEqual>
    </span>
  </td>

<!-- スケジュール情報 -->
  <logic:notEmpty name="gpWeekMdl" property="sch010SchList">
  <logic:iterate id="gpDayMdl" name="gpWeekMdl" property="sch010SchList">

  <logic:equal name="gpDayMdl" property="weekKbn" value="1">
  <logic:equal name="gpDayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_cal_today">
  </logic:equal>
  <logic:notEqual name="gpDayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_type9">
  </logic:notEqual>
  </logic:equal>

  <logic:equal name="gpDayMdl" property="weekKbn" value="7">
  <logic:equal name="gpDayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_cal_today">
  </logic:equal>
  <logic:notEqual name="gpDayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_type8">
  </logic:notEqual>
  </logic:equal>

  <logic:notEqual name="gpDayMdl" property="weekKbn" value="1">
  <logic:notEqual name="gpDayMdl" property="weekKbn" value="7">

  <logic:equal name="gpDayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_cal_today">
  </logic:equal>

  <logic:notEqual name="gpDayMdl" property="todayKbn" value="1">
    <td style="height:<%= grpHeight %>px" align="left" valign="top" class="td_type1">
  </logic:notEqual>

  </logic:notEqual>
  </logic:notEqual>

    <logic:equal name="usrMdl" property="schRegistFlg" value="true">
    <a href="#" onClick="return addSchedule('add', <bean:write name="gpDayMdl" property="schDate" />, <bean:write name="gpDayMdl" property="usrSid" />, <bean:write name="gpDayMdl" property="usrKbn" />);"><img src="../schedule/images/scadd.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0"></a><br>
    </logic:equal>

    <logic:notEmpty name="gpDayMdl" property="schDataList">
    <logic:iterate id="gpSchMdl" name="gpDayMdl" property="schDataList">

      <bean:define id="u_usrsid" name="gpDayMdl" property="usrSid" type="java.lang.Integer" />
      <bean:define id="u_schsid" name="gpSchMdl" property="schSid" type="java.lang.Integer" />
      <bean:define id="u_date" name="gpDayMdl" property="schDate"  type="java.lang.String" />

      <bean:define id="type1" name="gpSchMdl" property="public"  type="java.lang.Integer" />
      <bean:define id="type2" name="gpSchMdl" property="kjnEdKbn"  type="java.lang.Integer" />
      <bean:define id="type3" name="gpSchMdl" property="grpEdKbn"  type="java.lang.Integer" />

      <%
        int publicType = ((Integer)pageContext.getAttribute("type1",PageContext.PAGE_SCOPE));
        int kjnEdKbn = ((Integer)pageContext.getAttribute("type2",PageContext.PAGE_SCOPE));
        int grpEdKbn = ((Integer)pageContext.getAttribute("type3",PageContext.PAGE_SCOPE));
      %>

      <!--公開-->
      <%
      if ((publicType == dspPublic || publicType == dspBelongGroup) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
      %>

      <logic:empty name="gpSchMdl" property="valueStr">

      <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="gpDayMdl" property="schDate" />, <bean:write name="gpSchMdl" property="schSid" />, <bean:write name="gpDayMdl" property="usrSid" />, <bean:write name="gpDayMdl" property="usrKbn" />);">
      <span class="tooltips"><bean:write name="gpSchMdl" property="title" /></span>
      </logic:empty>
      <logic:notEmpty name="gpSchMdl" property="valueStr">
      <bean:define id="scnaiyou" name="gpSchMdl" property="valueStr" />
      <%
        String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
      %>
      <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="gpDayMdl" property="schDate" />, <bean:write name="gpSchMdl" property="schSid" />, <bean:write name="gpDayMdl" property="usrSid" />, <bean:write name="gpDayMdl" property="usrKbn" />);">
      <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
      </logic:notEmpty>

      <!--タイトルカラー設定-->
      <logic:equal name="gpSchMdl" property="bgColor" value="0">
      <span class="sc_link_1">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="1">
      <span class="sc_link_1">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="2">
      <span class="sc_link_2">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="3">
      <span class="sc_link_3">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="4">
      <span class="sc_link_4">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="5">
      <span class="sc_link_5">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="6">
      <span class="sc_link_6">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="7">
      <span class="sc_link_7">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="8">
      <span class="sc_link_8">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="9">
      <span class="sc_link_9">
      </logic:equal>
      <logic:equal name="gpSchMdl" property="bgColor" value="10">
      <span class="sc_link_10">
      </logic:equal>


        <logic:notEmpty name="gpSchMdl" property="time">
        <font size="-2"><bean:write name="gpSchMdl" property="time" /><br></font>
        </logic:notEmpty>
        <bean:write name="gpSchMdl" property="title" /><br>
      </span>
      </a>

      <%
      } else {
      %>

      <!--非公開-->
      <span class="sc_nolink">
        <logic:notEmpty name="gpSchMdl" property="time">
        <font size="-2"><bean:write name="gpSchMdl" property="time" /><br></font>
        </logic:notEmpty>
        <logic:notEmpty name="gpSchMdl" property="title">
        <bean:write name="gpSchMdl" property="title" />
        <br>
        </logic:notEmpty>
      </span>
      <%
      }
      %>
    </logic:iterate>
    </logic:notEmpty>
  </td>

  </logic:iterate>
  </logic:notEmpty>
  </tr>


  <!--期間スケジュール-->
  <logic:notEmpty name="gpWeekMdl" property="sch010NoTimeSchList">

   <bean:define id="memPrList" name="gpWeekMdl" property="sch010NoTimeSchList" type="java.util.ArrayList"/>
   <% int rowSize = memPrList.size(); %>

    <logic:iterate id="periodList" name="gpWeekMdl" property="sch010NoTimeSchList" indexId="rowPidx">
      <logic:notEmpty name="periodList">

        <% if ((Integer.valueOf(rowPidx) + 1) == (Integer.valueOf(rowSize))) { %>
        <tr class="td_sch_type7">
        <% } else { %>
        <tr class="td_sch_type6">
        <% } %>

          <bean:define id="prList" name="periodList" type="java.util.ArrayList"/>
          <% int size = prList.size(); %>

          <logic:iterate id="gpPeriodMdl" name="periodList" indexId="memPidx">
            <logic:notEmpty name="gpPeriodMdl" property="periodMdl">
              <bean:define id="pMdl" name="gpPeriodMdl" property="periodMdl"/>

              <td class="td_type1 td_type_kikan" colspan="<bean:write name="pMdl" property="schPeriodCnt" />">


                  <bean:define id="p_schsid" name="gpPeriodMdl" property="schSid" type="java.lang.Integer" />
                  <bean:define id="p_public" name="gpPeriodMdl" property="public"  type="java.lang.Integer" />
                  <bean:define id="p_kjnEdKbn" name="gpPeriodMdl" property="kjnEdKbn"  type="java.lang.Integer" />
                  <bean:define id="p_grpEdKbn" name="gpPeriodMdl" property="grpEdKbn"  type="java.lang.Integer" />
                  <%
                    int publicType = ((Integer)pageContext.getAttribute("p_public",PageContext.PAGE_SCOPE));
                    int kjnEdKbn = ((Integer)pageContext.getAttribute("p_kjnEdKbn",PageContext.PAGE_SCOPE));
                    int grpEdKbn = ((Integer)pageContext.getAttribute("p_grpEdKbn",PageContext.PAGE_SCOPE));
                  %>

                  <!--公開-->
                  <%
                  if ((publicType == dspPublic || publicType == dspBelongGroup) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
                  %>

                  <logic:empty name="gpPeriodMdl" property="schAppendUrl">
                    <logic:empty name="gpPeriodMdl" property="valueStr">
                    <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="gpDayMdl" property="schDate" />, <bean:write name="gpPeriodMdl" property="schSid" />, <bean:write name="gpDayMdl" property="usrSid" />, <bean:write name="gpDayMdl" property="usrKbn" />);">
                    <span class="tooltips"><bean:write name="gpPeriodMdl" property="title" /></span>
                    </logic:empty>
                    <logic:notEmpty name="gpPeriodMdl" property="valueStr">
                    <bean:define id="scnaiyou" name="gpPeriodMdl" property="valueStr" />
                    <%
                      String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                      String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                    %>
                    <a href="#" id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" onClick="editSchedule('edit', <bean:write name="gpDayMdl" property="schDate" />, <bean:write name="gpPeriodMdl" property="schSid" />, <bean:write name="gpDayMdl" property="usrSid" />, <bean:write name="gpDayMdl" property="usrKbn" />);">
                    <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                    </logic:notEmpty>
                  </logic:empty>

                  <logic:notEmpty name="gpPeriodMdl" property="schAppendUrl">
                    <logic:empty name="gpPeriodMdl" property="valueStr">
                    <a id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" href="<bean:write name="gpPeriodMdl" property="schAppendUrl" />">
                    <% boolean schFilter = true; %>
                    <logic:equal name="gpPeriodMdl" property="userKbn" value="<%= project %>">
                      <% schFilter = false; %>
                    </logic:equal>
                    <span class="tooltips"><bean:write name="gpPeriodMdl" property="title" filter="<%= schFilter %>" /></span>
                    </logic:empty>
                    <logic:notEmpty name="gpPeriodMdl" property="valueStr">
                    <bean:define id="scnaiyou" name="gpPeriodMdl" property="valueStr" />
                    <%
                      String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                      String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                    %>
                    <a id="tooltips_sch<%= String.valueOf(schTipCnt++) %>" href="<bean:write name="gpPeriodMdl" property="schAppendUrl" />">
                    <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                    </logic:notEmpty>
                  </logic:notEmpty>

                  <!--タイトルカラー設定-->
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="0">
                  <span class="sc_link_1">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="1">
                  <span class="sc_link_1">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="2">
                  <span class="sc_link_2">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="3">
                  <span class="sc_link_3">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="4">
                  <span class="sc_link_4">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="5">
                  <span class="sc_link_5">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="6">
                  <span class="sc_link_6">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="7">
                  <span class="sc_link_7">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="8">
                  <span class="sc_link_8">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="9">
                  <span class="sc_link_9">
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="bgColor" value="10">
                  <span class="sc_link_10">
                  </logic:equal>
                  <% boolean schFilter = true; %>
                  <logic:equal name="gpPeriodMdl" property="userKbn" value="<%= project %>">
                    <span class="sc_link_g">TODO</span>
                    <% schFilter = false; %>
                  </logic:equal>
                  <logic:equal name="gpPeriodMdl" property="userKbn" value="<%= nippou %>">
                    <span class="sc_link_g">アクション</span>
                  </logic:equal>

                    <logic:notEmpty name="gpPeriodMdl" property="time">
                    <font size="-2"><bean:write name="gpPeriodMdl" property="time" /><br></font>
                    </logic:notEmpty>
                    <bean:write name="gpPeriodMdl" property="title" filter="<%= schFilter %>" /><br>
                  </span>
                  </a>

                  <%
                  } else {
                  %>

                  <!--非公開-->
                  <span class="sc_nolink">
                    <logic:notEmpty name="gpPeriodMdl" property="time">
                    <font size="-2"><bean:write name="gpPeriodMdl" property="time" /><br></font>
                    </logic:notEmpty>
                    <logic:notEmpty name="gpPeriodMdl" property="title">
                    <bean:write name="gpPeriodMdl" property="title" />
                    <br>
                    </logic:notEmpty>
                  </span>
                  <%
                  }
                  %>
              </td>
            </logic:notEmpty>
            <logic:empty name="gpPeriodMdl" property="periodMdl">

              <% String td_class = "td_type_kikan2"; %>
              <% if ((Integer.valueOf(memPidx) + 1) == (Integer.valueOf(size))) { %>
              <% td_class ="td_type_kikan3"; %>
              <% } else if (Integer.valueOf(memPidx) == 0){ %>
              <% td_class ="td_type_kikan4"; %>
              <% } %>

              <% if ((Integer.valueOf(rowPidx) + 1) == (Integer.valueOf(rowSize))) { %>
              <% td_class = td_class  + " td_type_kikan5"; %>
              <% } %>

              <td class="<%= td_class %>"></td>

            </logic:empty>
          </logic:iterate>
        </tr>
      </logic:notEmpty>
    </logic:iterate>
  </logic:notEmpty>


  </logic:iterate>
  </logic:notEmpty>
  </table>

  <br>
  <div align="left">
  <table width="0%">
      <tr>
      <logic:equal name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
        <td class="td_type1" nowrap><span class="text_base2"><gsmsg:write key="cmn.zaiseki2" /></span></td>
        <td>&nbsp;</td>
        <td class="td_type_gaisyutu" nowrap><span class="text_base2"><gsmsg:write key="cmn.absence2" /></span></td>
        <td>&nbsp;</td>
        <td class="td_type_kekkin" nowrap><span class="text_base2"><gsmsg:write key="cmn.other" /></span></td>
      </logic:equal>
      <logic:notEqual name="sch010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <td></td>
      </logic:notEqual>
      </tr>
  </table>
  </div>

</td>
</tr>
</table>
</html:form>

<div id="smlPop" title="" style="display:none">
  <div id="smlCreateArea" width="100%" height="100%"></div>
</div>

<script type="text/javascript">
<% for (long schIdCnt = 0; schIdCnt < schTipCnt; schIdCnt++) { }%>
</script>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>