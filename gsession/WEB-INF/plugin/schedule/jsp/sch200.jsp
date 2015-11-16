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
<title>[GroupSession] <gsmsg:write key="schedule.sch200.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../schedule/jquery/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/fullcalendar/fullcalendar.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../schedule/js/sch200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="sch200Form" property="sch200Reload" value="0">
    var reloadinterval = <bean:write name="sch200Form" property="sch200Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<script type='text/javascript'>
  $(document).ready(function() {
  $.post('../schedule/sch200.do', {"CMD":"getSchData", "sch010DspDate":"<bean:write name="sch200Form" property="sch010DspDate" />", "sch200FrDate":"<bean:write name="sch200Form" property="sch200FrDate" />", "sch200ToDate":"<bean:write name="sch200Form" property="sch200ToDate" />", "sch100SelectUsrSid":"<bean:write name="sch200Form" property="sch100SelectUsrSid" />", "sch010SelectUsrKbn":"<bean:write name="sch200Form" property="sch010SelectUsrKbn" />"}, function(data){
      var jsonObject = eval('(' + data + ')');
      $('#calendar').fullCalendar(jsonObject);
      $('#calendar').fullCalendar( 'gotoDate', <bean:write name="sch200Form" property="sch200Year" />, <bean:write name="sch200Form" property="sch200Month" />, <bean:write name="sch200Form" property="sch200Day" /> );
      $('#calendar').fullCalendar('option', 'aspectRatio', 2.15);
    });
  });
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/fullcalendar/fullcalendar.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/fullcalendar/fullcalendar.print.css?<%= GSConst.VERSION_PARAM %>' media='print' type='text/css'>

</head>

<body class="body_03" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/schedule/sch200">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010CrangeKbn" />
<html:hidden property="sch200DayDelta" />
<html:hidden property="sch200MinuteDelta" />
<html:hidden property="sch200EventPosition" />
<html:hidden property="sch200BatchRef" />
<html:hidden property="sch200ResBatchRef" />
<html:hidden property="sch200Cancel" />
<html:hidden property="sch200FrDate" />
<html:hidden property="sch200ToDate" />

<html:hidden property="sch040FrHour" value="" />
<html:hidden property="sch040FrMin" value="" />
<html:hidden property="sch040ToYear" value="" />
<html:hidden property="sch040ToMonth" value="" />
<html:hidden property="sch040ToDay" value="" />
<html:hidden property="sch040ToHour" value="" />
<html:hidden property="sch040ToMin" value="" />
<html:hidden property="sch040TimeKbn" value="0" />

<logic:notEmpty name="sch200Form" property="schNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="sch200Form" property="schNotAccessGroupList" scope="request">
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
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="schedule.19" /> ]</td>
  <td width="100%" class="header_white_bg">

  <logic:equal name="sch200Form" property="adminKbn" value="1">
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
    <td colspan="3">
      <span class="TXT02"><html:errors/></span>
    </td>
    </tr>
    </logic:messagesPresent>

    <tr>
    <td width="75%" align="right" nowrap>
      <input type="button" value="<gsmsg:write key="schedule.19" />" class="btn_week_kojin" onClick="buttonPush('reload');">
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onClick="buttonPush('day');">
      <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('week');">
      <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="moveMonthSchedule('month', <bean:write name="sch200Form" property="sch010SelectUsrSid" />, <bean:write name="sch200Form" property="sch010SelectUsrKbn" />);">
      <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onClick="moveListSchedule('list', <bean:write name="sch200Form" property="sch010SelectUsrSid" />, <bean:write name="sch200Form" property="sch010SelectUsrKbn" />);">
    </td>

    <td width="0%" align="right" valign="top" nowrap>
      <input type="button" class="btn_arrow1_l" value="&nbsp;" onclick="buttonPush('move_lw');">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_ld');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('today');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rd');">
      <input type="button" class="btn_arrow1_r" value="&nbsp;" onclick="buttonPush('move_rw');">
    </td>

    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.sch010DspDate, 'sch200Btn')" class="calendar_btn2", id="sch200Btn">
    </td>
    </tr>
    </table>

  </td>
  </tr>

  <!-- カレンダーヘッダー -->
  <tr>
  <td class="table_bg_7D91BD" align="center">
    <table width="100%" class="tl0">
    <tr>
    <!--　日付 -->
    <td width="15%" align="left">
      <span class="text_tl1"><bean:write name="sch200Form" property="sch200StrDspDate" /></span>
    </td>

    <!--　グループコンボ -->
    <td width="10%" align="left" nowrap>
      <span class="text_tlw"><gsmsg:write key="cmn.show.group" /></span>
      <html:select property="sch010DspGpSid" styleClass="select02" onchange="changeGroupCombo('chgroup');">

        <logic:notEmpty name="sch200Form" property="sch010GpLabelList" scope="request">
        <logic:iterate id="gpBean" name="sch200Form" property="sch010GpLabelList" scope="request">

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
        <logic:equal name="sch200Form" property="sch010CrangeKbn" value="0">
          <input type="button" onclick="openGroupWindow(this.form.sch010DspGpSid, 'sch010DspGpSid', '1', 'chgroup', 0, '', 'schNotAccessGroup');" class="group_btn" value="&nbsp;&nbsp;" id="sch010GroupBtn">
        </logic:equal>
      </html:select>
      </td>

      <td width="10%" align-"left" nowrap>
      <logic:notEmpty name="sch200Form" property="sch200UsrLabelList">
      <html:select property="sch100SelectUsrSid" styleClass="select02" onchange="changeUserCombo();">
        <html:optionsCollection name="sch200Form" property="sch200UsrLabelList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>

      <logic:equal name="sch200Form" property="sch100SelectUsrSid" value="-1">
      <input type="hidden" name="sch010SelectUsrSid" value="<bean:write name="sch200Form" property="sch010DspGpSid" />">
      <input type="hidden" name="sch010SelectUsrKbn" value="1">
      </logic:equal>
      <logic:equal name="sch200Form" property="sch100SelectUsrSid" value="-2">
      <input type="hidden" name="sch010SelectUsrSid" value="<bean:write name="sch200Form" property="sch010DspGpSid" />">
      <input type="hidden" name="sch010SelectUsrKbn" value="1">
      </logic:equal>
      <logic:notEqual name="sch200Form" property="sch100SelectUsrSid" value="-1">
      <logic:notEqual name="sch200Form" property="sch100SelectUsrSid" value="-2">
      <input type="hidden" name="sch010SelectUsrSid" value="<bean:write name="sch200Form" property="sch100SelectUsrSid" />">
      <input type="hidden" name="sch010SelectUsrKbn" value="0">
      </logic:notEqual>
      </logic:notEqual>
    </td>

    <!--　検索 -->
    <td width="0%" align="right">
      <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
      <html:text property="sch010searchWord" styleClass="text_base" maxlength="50" style="width:155px;" />
      <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onClick="buttonPush('search');">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>
  <div id='calendar'></div>
</td>
</tr>
</table>


<div id="dialog" title="<gsmsg:write key="schedule.sch200.23" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
      <gsmsg:write key="schedule.sch200.13" />&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="schedule.sch200.16" />
 </p>
</div>

<div id="dialog2" title="<gsmsg:write key="schedule.sch200.24" />" style="display:none">
 <p><span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
     <gsmsg:write key="schedule.135" />
 </p>
</div>

<div id="dialog3" title="<gsmsg:write key="schedule.sch200.25" />" style="display:none">
 <p><span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
     <gsmsg:write key="schedule.134" />
 </p>
</div>

<div id="dialog4" title="<gsmsg:write key="schedule.sch200.26" />" style="display:none">
 <p><span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
    <gsmsg:write key="schedule.sch200.17" />
 </p>
</div>

<div id="dialog5" title="<gsmsg:write key="schedule.148" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
  <gsmsg:write key="schedule.sch200.19" />
 </p>
</div>

<div id="dialog6" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
  <gsmsg:write key="schedule.sch200.21" />
 </p>
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>