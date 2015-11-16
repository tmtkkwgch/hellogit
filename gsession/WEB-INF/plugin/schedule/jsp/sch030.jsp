<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailySchedule.tld" prefix="dailySchedule" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch030.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../schedule/js/sch030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="sch030Form" property="sch030Reload" value="0">
    var reloadinterval = <bean:write name="sch030Form" property="sch030Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/schedule/sch030">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="sch010SelectUsrSid" value="-1">
<input type="hidden" name="sch010SelectUsrKbn" value="0">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010CrangeKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch030FromHour" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<bean:define id="fromHour" name="sch030Form" property="sch030FromHour" scope="request"/>
<bean:define id="toHour" name="sch030Form" property="sch030ToHour" scope="request"/>
<bean:define id="totalCols" name="sch030Form" property="sch030TotalCols" scope="request"/>
<bean:define id="adminKbn" name="sch030Form" property="adminKbn" scope="request"/>
<bean:define id="memoriCount" name="sch030Form" property="sch030MemoriCount" scope="request"/>

<logic:notEmpty name="sch030Form" property="schNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="sch030Form" property="schNotAccessGroupList" scope="request">
    <input type="hidden" name="schNotAccessGroup" value="<bean:write name="notAccessGroup"/>">
  </logic:iterate>
</logic:notEmpty>


<!--　BODY -->
<div id="schTooltipsDaily">
<table cellpadding="5" cellspacing="0" width="100%">
<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.days2" /> ]</td>
  <td width="100%" class="header_white_bg">
  <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
  <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
  <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
  <logic:equal name="sch030Form" property="adminKbn" value="1">
    <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('ktool');">
  </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pset');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
  </tr>
  </table>


  <table cellpadding="5" width="100%" class="tl0">
  <tr>
  <td colspan="<%= totalCols.toString() %>" class="td_type0">

    <table width="100%" class="tl0" border="0">
    <tr>
    <td width="75%" align="right" nowrap>
    <bean:size id="topSize" name="sch030Form" property="sch010TopList" scope="request" />
    <logic:equal name="topSize" value="2">
    <logic:iterate id="weekBean" name="sch030Form" property="sch010TopList" scope="request" offset="1"/>
    </logic:equal>
    <logic:notEqual name="topSize" value="2">
    <logic:iterate id="weekBean" name="sch030Form" property="sch010TopList" scope="request" offset="0"/>
    </logic:notEqual>

    <bean:define id="usrBean" name="weekBean" property="sch010UsrMdl" />
      <input type="button" value="<gsmsg:write key="schedule.19" />" class="btn_week_kojin" onClick="moveKojinSchedule('kojin', <bean:write name="sch030Form" property="sch010DspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onClick="buttonPush('reload');">
    <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('week');">
    <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="moveMonthSchedule('month', <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
    <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onClick="moveListSchedule('list', <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
    </td>
    <td width="0%" align="right" valign="top" nowrap>
      <input type="image" name="zweek" src="../schedule/images/arrow1_l.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden" class="img_top">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_ld');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('today');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rd');">
      <input type="image" name="yweek" src="../schedule/images/arrow1_r.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden" class="img_top">
    </td>

    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="resetCmd();wrtCalendarByBtn(this.form.sch010DspDate, 'sch030CalBtn')" class="calendar_btn2" id="sch030CalBtn">
    </td>

    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td colspan="<%= totalCols.toString() %>" class="table_bg_7D91BD">
        <table width="100%" class="tl0">
        <tr>
        <td width="25%" align="left">
        <span class="text_tl1"><bean:write name="sch030Form" property="sch030StrDate" scope="request" /></span>
        </td>


        <td width="50%" align="left" nowrap>

        <span class="text_tlw"><gsmsg:write key="cmn.show.group" /></span>
        <html:select property="sch010DspGpSid" styleClass="select01" onchange="changeGroupCombo();">

        <logic:notEmpty name="sch030Form" property="sch010GpLabelList" scope="request">
        <logic:iterate id="gpBean" name="sch030Form" property="sch010GpLabelList" scope="request">

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
        <logic:equal name="sch030Form" property="sch010CrangeKbn" value="0">
          <input type="button" onclick="openGroupWindow(this.form.sch010DspGpSid, 'sch010DspGpSid', '1', '', 0, '', 'schNotAccessGroup');" class="group_btn" value="&nbsp;&nbsp;" id="sch010GroupBtn">
        </logic:equal>
        <!--html:optionsCollection name="sch030Form" property="sch010GpLabelList" value="value" label="label" /-->
        </html:select>

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


  <!-- タイトル行(氏名,新規,時間) -->
  <tr>
  <th width="120" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <th width="30" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.new" /></span></th>

  <logic:notEmpty name="sch030Form" property="sch030TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch030Form" property="sch030TimeChartList" scope="request">
  <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
  </logic:iterate>
  </logic:notEmpty>
  </tr>

  <!-- 時間目盛 -->
  <tr>
  <logic:notEmpty name="sch030Form" property="sch030TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch030Form" property="sch030TimeChartList" scope="request">

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
<logic:notEmpty name="sch030Form" property="sch010TopList" scope="request">
<logic:iterate id="weekMdl" name="sch030Form" property="sch010TopList" scope="request">

  <tr align="left" valign="top">
    <dailySchedule:dailywrite name="weekMdl" top="1" from="<%= fromHour.toString() %>" to="<%= toHour.toString() %>" admin="<%= adminKbn.toString() %>" memCnt="<%= memoriCount.toString() %>"/>
  </tr>

</logic:iterate>
</logic:notEmpty>


  <!-- グループメンバー -->
  <!-- タイトル行(氏名,新規,時間) -->
  <tr>
  <th width="120" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <th width="30" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.new" /></span></th>
  <!-- 時間目盛 -->
  <logic:notEmpty name="sch030Form" property="sch030TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch030Form" property="sch030TimeChartList" scope="request">
  <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
  </logic:iterate>
  </logic:notEmpty>
  </tr>
  <!-- 分目盛 -->
  <tr>
  <logic:notEmpty name="sch030Form" property="sch030TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch030Form" property="sch030TimeChartList" scope="request">

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
<logic:notEmpty name="sch030Form" property="sch010BottomList" scope="request">
<logic:iterate id="gpWeekMdl" name="sch030Form" property="sch010BottomList" scope="request" indexId="cnt">

  <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
  <logic:notEqual name="cnt" value="0" scope="page">
  <logic:equal name="ret" value="0">
  <!-- タイトル行(氏名,新規,時間) -->
  <tr>
  <th width="120" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
  <th width="30" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.new" /></span></th>
  <!-- 時間目盛 -->
  <logic:notEmpty name="sch030Form" property="sch030TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch030Form" property="sch030TimeChartList" scope="request">
  <th colspan="<bean:write name="memoriCount" scope="page" />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
  </logic:iterate>
  </logic:notEmpty>
  </tr>
  <!-- 分目盛 -->
  <tr>
  <logic:notEmpty name="sch030Form" property="sch030TimeChartList" scope="request">
  <logic:iterate id="strHour" name="sch030Form" property="sch030TimeChartList" scope="request">
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

  </td>
  </tr>
  </table>
  <br>
  <div align="left">
  <table width ="0%" align="left">
    <tr>
      <td class="td_type1" align="left" nowrap><span class="text_base2"><gsmsg:write key="cmn.zaiseki2" /></span></td>
      <td>&nbsp;</td>
      <td class="td_type_gaisyutu" align="left" nowrap><span class="text_base2"><gsmsg:write key="cmn.absence2" /></span></td>
      <td>&nbsp;</td>
      <td class="td_type_kekkin" align="left" nowrap><span class="text_base2"><gsmsg:write key="cmn.other" /></span></td>
    </tr>
  </table>
  </div>

</td>
</tr>
</table>
</div>
</html:form>

<div id="smlPop" title="" style="display:none">
  <div id="smlCreateArea" width="100%" height="100%"></div>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>