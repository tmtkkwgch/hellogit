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

<%-- キーワード検索区分 --%>
<%
  String keyWordAnd    = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.KEY_WORD_KBN_AND);
  String keyWordOr     = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.KEY_WORD_KBN_OR);
%>
<%-- 検索対象 --%>
<%
  String targetTitle   = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SEARCH_TARGET_TITLE);
  String targetHonbun  = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SEARCH_TARGET_HONBUN);
%>
<%-- 定数値 --%>
<%
  int editConfOwn          = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_OWN;
  int editConfGroup        = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_GROUP;
  int dspPublic            = jp.groupsession.v2.cmn.GSConstSchedule.DSP_PUBLIC;
  int dspNotPublic         = jp.groupsession.v2.cmn.GSConstSchedule.DSP_NOT_PUBLIC;
  int dspYoteiari          = jp.groupsession.v2.cmn.GSConstSchedule.DSP_YOTEIARI;
  int dspBelongGroup       = jp.groupsession.v2.cmn.GSConstSchedule.DSP_BELONG_GROUP;
%>
<%-- 管理者区分 --%>
<%
  String adminUsr   = String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch100.5" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/webSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch100.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="calWindowClose();">
<html:form action="/schedule/sch100">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010CrangeKbn" />

<html:hidden property="sch100SvSltGroup" />
<html:hidden property="sch100SvSltUser" />
<html:hidden property="sch100SvSltStartYearFr" />
<html:hidden property="sch100SvSltStartMonthFr" />
<html:hidden property="sch100SvSltStartDayFr" />
<html:hidden property="sch100SvSltStartYearTo" />
<html:hidden property="sch100SvSltStartMonthTo" />
<html:hidden property="sch100SvSltStartDayTo" />
<html:hidden property="sch100SvSltEndYearFr" />
<html:hidden property="sch100SvSltEndMonthFr" />
<html:hidden property="sch100SvSltEndDayFr" />
<html:hidden property="sch100SvSltEndYearTo" />
<html:hidden property="sch100SvSltEndMonthTo" />
<html:hidden property="sch100SvSltEndDayTo" />
<html:hidden property="sch100SvKeyWordkbn" />
<html:hidden property="sch100SvKeyValue" />

<html:hidden property="sch100SvOrderKey1" />
<html:hidden property="sch100SvSortKey1" />
<html:hidden property="sch100SvOrderKey2" />
<html:hidden property="sch100SvSortKey2" />

<logic:notEmpty name="sch100Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch100Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch100Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch100Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sch100PageNum" />
<input type="hidden" name="listMod" value="5">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.sch100.5" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.list" /> ]</td>
  <td width="100%" class="header_white_bg">
  <logic:notEmpty name="sch100Form" property="sch100ScheduleList">
      <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
  </logic:notEmpty>
  <logic:equal name="sch100Form" property="adminKbn" value="1">
    <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
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
    <tr>
    <td width="75%" align="right" nowrap>
    <bean:define id="uMdl" name="sch100Form" property="sch100UsrMdl" />
     <input type="button" value="<gsmsg:write key="schedule.19" />" class="btn_week_kojin" onClick="buttonPush('kojin');">
     <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onClick="buttonPush('day');">
     <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('week');">
     <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="moveMonthSchedule('month', <bean:write name="uMdl" property="usrSid" />, <bean:write name="uMdl" property="usrKbn" />);">
     <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onClick="buttonPush('reload');">
     <!--input type="button" name="btn_kojn" class="btn_base1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('100_back', '<bean:write name="sch100Form" property="sch100BtnCmd" />');"-->
    </td>
    <td width="0%" align="right" nowrap>
      <input type="image" name="zweek" src="./images/arrow1_l.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden">
      <input type="image" name="zmonth" src="./images/arrow2_l.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden">
      <input type="image" name="tmonth" src="../schedule/images/moon_btn.gif" alt="<gsmsg:write key="cmn.space" />" style="width:50px;height:25px;visibility:hidden">
      <input type="image" name="yweek" src="./images/arrow2_r.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden">
      <input type="image" name="yweek" src="./images/arrow1_r.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:25px;visibility:hidden">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
  <td  colspan="7" width="100%" nowrap>
    <table width="100%">
    <tr>
    <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>
  </td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td colspan="7" class="table_bg_7D91BD" align="center">
    <table width="100%" class="tl0">
    <tr>
    <td align="left">
    <span class="text_tl1"><font color="ffffff">&nbsp;</font></span>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td colspan="1" align="center" class="td_gray" width="14%" nowrap><span class="text_bb2"><gsmsg:write key="schedule.sch100.4" /></span></td>
  <td colspan="6" class="td_type20" width="86%" nowrap>
   <html:select property="sch100SltGroup" styleClass="select01" onchange="changeGroupCombo();">

      <logic:notEmpty name="sch100Form" property="sch100GroupLabel" scope="request">
      <logic:iterate id="gpBean" name="sch100Form" property="sch100GroupLabel" scope="request">

      <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
      <logic:equal name="gpBean" property="styleClass" value="0">
      <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:equal>

      <logic:notEqual name="gpBean" property="styleClass" value="0">
      <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:notEqual>

      </logic:iterate>
      </logic:notEmpty>

   <!--html:optionsCollection name="sch100Form" property="sch100GroupLabel" value="value" label="label" /-->
   </html:select>
   <logic:equal name="sch100Form" property="sch010CrangeKbn" value="0">
     <input type="button" onclick="openSch100Group(this.form.sch100SltGroup, 'sch100SltGroup', '1')" class="group_btn" value="&nbsp;&nbsp;" id="sch100GroupBtn">
   </logic:equal>
   <html:select property="sch100SltUser" styleClass="select01">
    <html:optionsCollection name="sch100Form" property="sch100UserLabel" value="value" label="label" />
   </html:select>
  </td>
  </tr>

  <tr>
  <td colspan="1" align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="schedule.sch100.10" /></span></td>
  <td colspan="6" class="td_type20">
    <html:select property="sch100SltStartYearFr" styleId="selYearsf">
    <html:optionsCollection name="sch100Form" property="sch100StartYearFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltStartMonthFr" styleId="selMonthsf">
    <html:optionsCollection name="sch100Form" property="sch100StartMonthFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltStartDayFr" styleId="selDaysf">
    <html:optionsCollection name="sch100Form" property="sch100StartDayFrLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDaysf, this.form.selMonthsf, this.form.selYearsf, 'sch100StartFrCalBtn')" class="calendar_btn" id="sch100StartFrCalBtn">
    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 1)">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 2)">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 3)">
～
    <html:select property="sch100SltStartYearTo" styleId="selYearst">
    <html:optionsCollection name="sch100Form" property="sch100StartYearFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltStartMonthTo" styleId="selMonthst">
    <html:optionsCollection name="sch100Form" property="sch100StartMonthFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltStartDayTo" styleId="selDayst">
    <html:optionsCollection name="sch100Form" property="sch100StartDayFrLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayst, this.form.selMonthst, this.form.selYearst, 'sch100StartToCalBtn')" class="calendar_btn" id="sch100StartToCalBtn">
    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 1)">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 2)">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 3)">
  </td>
  </tr>

  <tr>
  <td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="schedule.sch100.15" /></span></td>
  <td colspan="6" class="td_type20">
    <html:select property="sch100SltEndYearFr" styleId="selYearef">
    <html:optionsCollection name="sch100Form" property="sch100StartYearFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltEndMonthFr" styleId="selMonthef">
    <html:optionsCollection name="sch100Form" property="sch100StartMonthFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltEndDayFr" styleId="selDayef">
    <html:optionsCollection name="sch100Form" property="sch100StartDayFrLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayef, this.form.selMonthef, this.form.selYearef, 'sch100EndFrCalBtn')" class="calendar_btn" id="sch100EndFrCalBtn">
    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearef')[0], $('#selMonthef')[0], $('#selDayef')[0], 1)">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearef')[0], $('#selMonthef')[0], $('#selDayef')[0], 2)">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearef')[0], $('#selMonthef')[0], $('#selDayef')[0], 3)">
～
    <html:select property="sch100SltEndYearTo" styleId="selYearet">
    <html:optionsCollection name="sch100Form" property="sch100StartYearFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltEndMonthTo" styleId="selMonthet">
    <html:optionsCollection name="sch100Form" property="sch100StartMonthFrLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch100SltEndDayTo" styleId="selDayet">
    <html:optionsCollection name="sch100Form" property="sch100StartDayFrLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayet, this.form.selMonthet, this.form.selYearet, 'sch100EndTCalBtn')" class="calendar_btn" id="sch100EndTCalBtn">
    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearet')[0], $('#selMonthet')[0], $('#selDayet')[0], 1)">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearet')[0], $('#selMonthet')[0], $('#selDayet')[0], 2)">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearet')[0], $('#selMonthet')[0], $('#selDayet')[0], 3)">
  </td>
  </tr>

  <tr>
  <td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.keyword" /></span></td>
  <td colspan="4" class="td_type20" nowrap><span class="text_base2">
  <html:text name="sch100Form" maxlength="50" property="sch010searchWord" style="width:333px;" />
  <div class="text_base2">
  <html:radio name="sch100Form" property="sch100KeyWordkbn" value="<%= keyWordAnd %>" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
  <html:radio name="sch100Form" property="sch100KeyWordkbn" value="<%= keyWordOr %>" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
  </div>
  </span>
  </td>

  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.search2" /></th>
    <td width="40%" class="td_sub_detail">
      <html:multibox styleId="search_scope_01" name="sch100Form" property="sch100SearchTarget" value="<%= targetTitle %>" /><label for="search_scope_01"><gsmsg:write key="cmn.subject" /></label>
      <html:multibox styleId="search_scope_02" name="sch100Form" property="sch100SearchTarget" value="<%= targetHonbun %>" /><label for="search_scope_02"><gsmsg:write key="cmn.body" /></label>
  </td>
  </tr>

  <tr>
  <td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="schedule.10" /></span></td>
  <td colspan="6" align="left" class="td_type20" nowrap>

  <bean:define id="colorMsg1" value=""/>
  <bean:define id="colorMsg2" value=""/>
  <bean:define id="colorMsg3" value=""/>
  <bean:define id="colorMsg4" value=""/>
  <bean:define id="colorMsg5" value=""/>
  <bean:define id="colorMsg6" value=""/>
  <bean:define id="colorMsg7" value=""/>
  <bean:define id="colorMsg8" value=""/>
  <bean:define id="colorMsg9" value=""/>
  <bean:define id="colorMsg10" value=""/>
  <logic:iterate id="msgStr" name="sch100Form" property="sch100ColorMsgList" indexId="msgId" type="java.lang.String">
  <logic:equal name="msgId" value="0">
  <% colorMsg1 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="1">
  <% colorMsg2 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="2">
  <% colorMsg3 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="3">
  <% colorMsg4 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="4">
  <% colorMsg5 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="5">
  <% colorMsg6 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="6">
  <% colorMsg7 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="7">
  <% colorMsg8 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="8">
  <% colorMsg9 = msgStr; %>
  </logic:equal>
  <logic:equal name="msgId" value="9">
  <% colorMsg10 = msgStr; %>
  </logic:equal>
  </logic:iterate>

  <span class="sc_block_color_1"><html:multibox name="sch100Form" styleId="bg_color1" property="sch100Bgcolor" value="1" /></span>
  <label for="bg_color1" class="text_base"><%= colorMsg1 %></label>
  <span class="sc_block_color_2"><html:multibox name="sch100Form" styleId="bg_color2" property="sch100Bgcolor" value="2" /></span>
  <label for="bg_color2" class="text_base"><%= colorMsg2 %></label>
  <span class="sc_block_color_3"><html:multibox name="sch100Form" styleId="bg_color3" property="sch100Bgcolor" value="3" /></span>
  <label for="bg_color3" class="text_base"><%= colorMsg3 %></label>
  <span class="sc_block_color_4"><html:multibox name="sch100Form" styleId="bg_color4" property="sch100Bgcolor" value="4" /></span>
  <label for="bg_color4" class="text_base"><%= colorMsg4 %></label>
  <span class="sc_block_color_5"><html:multibox name="sch100Form" styleId="bg_color5" property="sch100Bgcolor" value="5" /></span>
  <label for="bg_color5" class="text_base"><%= colorMsg5 %></label>

  <logic:equal name="sch100Form" property="sch100colorKbn" value="1">
    <div><img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px"></div>
    <span class="sc_block_color_6"><html:multibox name="sch100Form" styleId="bg_color6" property="sch100Bgcolor" value="6" /></span>
    <label for="bg_color6" class="text_base"><%= colorMsg6 %></label>
    <span class="sc_block_color_7"><html:multibox name="sch100Form" styleId="bg_color7" property="sch100Bgcolor" value="7" /></span>
    <label for="bg_color7" class="text_base"><%= colorMsg7 %></label>
    <span class="sc_block_color_8"><html:multibox name="sch100Form" styleId="bg_color8" property="sch100Bgcolor" value="8" /></span>
    <label for="bg_color8" class="text_base"><%= colorMsg8 %></label>
    <span class="sc_block_color_9"><html:multibox name="sch100Form" styleId="bg_color9" property="sch100Bgcolor" value="9" /></span>
    <label for="bg_color9" class="text_base"><%= colorMsg9 %></label>
    <span class="sc_block_color_10"><html:multibox name="sch100Form" styleId="bg_color10" property="sch100Bgcolor" value="10" /></span>
    <label for="bg_color10" class="text_base"><%= colorMsg10 %></label>
  </logic:equal>

  </td>
  </tr>

  <tr>
  <td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sort.order" /></span></td>
  <td colspan="6" class="td_type20" nowrap>
  <span class="text_base2">

  <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
  <html:select property="sch100SortKey1" styleClass="select01">
    <html:optionsCollection name="sch100Form" property="sortKeyList" value="value" label="label" />
  </html:select>
  <html:radio name="sch100Form" property="sch100OrderKey1" styleId="sort1_up" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
  <html:radio name="sch100Form" property="sch100OrderKey1" styleId="sort1_dw" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
  <html:select property="sch100SortKey2" styleClass="select01">
    <html:optionsCollection name="sch100Form" property="sortKeyList" value="value" label="label" />
  </html:select>
  <html:radio name="sch100Form" property="sch100OrderKey2" styleId="sort2_up" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
  <html:radio name="sch100Form" property="sch100OrderKey2" styleId="sort2_dw" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

  </span>
  </td>
  </tr>

<tr>
<td colspan="7" width="100%">
  <table cellpadding="0" cellspacing="0" width="100%">
  <tr>
  <td align="center" width="100%">
  <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('sch100search');">
  </td>
  </tr>
  </table>
</td>
</tr>

<logic:notEmpty name="sch100Form" property="sch100ScheduleList">
<tr>
<td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="reserve.output.item" /></span></td>
<td colspan="6" class="td_type20" nowrap>
  <table cellpadding="0" cellspacing="0" width="100%">
  <tr>
  <td>
  <logic:equal name="sch100Form" property="adminKbn" value="<%= adminUsr %>">
    <span style="white-space: nowrap"><html:multibox styleId="loginId" name="sch100Form" property="sch100CsvOutField" value="1" /><label for="loginId" class="text_base"><gsmsg:write key="cmn.user.id" /></label></span><wbr>
    <span style="white-space: nowrap"><html:multibox styleId="groupId" name="sch100Form" property="sch100CsvOutField" value="2" /><label for="groupId" class="text_base"><gsmsg:write key="cmn.group.id" /></label></span><wbr>
  </logic:equal>
  <span style="white-space: nowrap"><html:multibox styleId="uname" name="sch100Form" property="sch100CsvOutField" value="3" /><label for="uname" class="text_base"><gsmsg:write key="cmn.name" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="frDate" name="sch100Form" property="sch100CsvOutField" value="4" /><label for="frDate" class="text_base"><gsmsg:write key="cmn.startdate" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="frTime" name="sch100Form" property="sch100CsvOutField" value="5" /><label for="frTime" class="text_base"><gsmsg:write key="cmn.starttime" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="toDate" name="sch100Form" property="sch100CsvOutField" value="6" /><label for="toDate" class="text_base"><gsmsg:write key="cmn.end.date" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="toTime" name="sch100Form" property="sch100CsvOutField" value="7" /><label for="toTime" class="text_base"><gsmsg:write key="cmn.endtime" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="title" name="sch100Form" property="sch100CsvOutField" value="8" /><label for="title" class="text_base"><gsmsg:write key="cmn.title" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="titleColor" name="sch100Form" property="sch100CsvOutField" value="9" /><label for="titleColor" class="text_base"><gsmsg:write key="schedule.10" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="value" name="sch100Form" property="sch100CsvOutField" value="10" /><label for="value" class="text_base"><gsmsg:write key="cmn.content" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="biko" name="sch100Form" property="sch100CsvOutField" value="11" /><label for="biko" class="text_base"><gsmsg:write key="cmn.memo" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="editPerm" name="sch100Form" property="sch100CsvOutField" value="12" /><label for="editPerm" class="text_base"><gsmsg:write key="cmn.edit.permissions" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="open" name="sch100Form" property="sch100CsvOutField" value="13" /><label for="open" class="text_base"><gsmsg:write key="cmn.public.kbn" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="timeSeg" name="sch100Form" property="sch100CsvOutField" value="14" /><label for="timeSeg" class="text_base"><gsmsg:write key="schedule.timed.segments" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="adName" name="sch100Form" property="sch100CsvOutField" value="15" /><label for="adName" class="text_base"><gsmsg:write key="schedule.132" /></label></span><wbr>
  <span style="white-space: nowrap"><html:multibox styleId="edName" name="sch100Form" property="sch100CsvOutField" value="16" /><label for="edName" class="text_base"><gsmsg:write key="schedule.133" /></label></span><wbr>
  </td>
  <td align="right" width="0%">
  <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('sch100export');">
  </td>
  </tr>
  </table>
</td>
</tr>

<tr>
<td>
  <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="">
</td>
</tr>
</logic:notEmpty>

  <logic:notEmpty name="sch100Form" property="sch100ScheduleList">

  <bean:size id="count1" name="sch100Form" property="sch100PageLabel" scope="request" />
  <logic:greaterThan name="count1" value="1">
  <tr>
  <td colspan="7" width="100%" align="right" valign="top" nowrap>
    <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">
    <logic:notEmpty name="sch100Form" property="sch100PageLabel">
      <html:select property="sch100Slt_page1" onchange="changePage1();" styleClass="text_i">
        <html:optionsCollection name="sch100Form" property="sch100PageLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <logic:empty name="sch100Form" property="sch100PageLabel">
      <html:select property="sch100Slt_page1" styleClass="text_i">
       <option value="1" class="text_i">1 / 1</option>
      </html:select>
    </logic:empty>
    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
  </td>
  </tr>
  </logic:greaterThan>

  <tr>
  <td class="td_type_tab" colspan="7">
    <table class="tl0" width="100%" cellpadding="5" cellspacing="0">
    <tr>
<!--
    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" />▲</span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" />▼</span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.11" />▲</span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th width="15%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.11" />▼</span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>">
    <th width="15%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.11" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.16" />▲</span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.16" />▼</span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.16" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.7" />▲</span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.7" />▼</span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>">
    <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.7" /></span></a></th>
    </logic:notEqual>
-->

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" /></span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_NAME) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.11" /></span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th width="15%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.11" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>">
    <th width="15%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_FRDATE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.11" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.16" /></span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.16" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>">
    <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TODATE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.16" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>">
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_ASC) %>">
    <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.7" /></span></a></th>
    </logic:equal>
    <logic:equal name="sch100Form" property="sch100OrderKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ORDER_KEY_DESC) %>">
    <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.7" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="sch100Form" property="sch100SortKey1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>">
    <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SORT_KEY_TITLE) %>')"><span class="text_tlw"><gsmsg:write key="schedule.sch100.7" /></span></a></th>
    </logic:notEqual>

    </tr>

    <logic:iterate id="schMdl" name="sch100Form" property="sch100ScheduleList" indexId="idx">
    <bean:define id="tdColor" value="" />
    <bean:define id="u_public" name="schMdl" property="public"  type="java.lang.Integer" />
    <bean:define id="u_grpEdKbn" name="schMdl" property="grpEdKbn"  type="java.lang.Integer" />
    <bean:define id="u_kjnEdKbn" name="schMdl" property="kjnEdKbn"  type="java.lang.Integer" />
    <% String[] tdColors = new String[] {"td_type1", "td_type29"}; %>
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>
    <%
       int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
       int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
       int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
    %>

    <tr>
    <td class="<%= tdColor %>" align="left" valign="middle" nowrap><bean:write name="schMdl" property="userName" /></td>
    <td class="<%= tdColor %>" align="center" valign="middle" nowrap><bean:write name="schMdl" property="fromDateStr" /></td>
    <td class="<%= tdColor %>" align="center" valign="middle" nowrap><bean:write name="schMdl" property="toDateStr" /></td>
    <td class="<%= tdColor %>" align="left" valign="middle">
    <div>

    <%
    if ((publicType == dspPublic || publicType == dspBelongGroup) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
    %>

    <a href="#" onClick="editSchedule('edit', <bean:write name="sch100Form" property="sch010DspDate" />, <bean:write name="schMdl" property="schSid" />, <bean:write name="schMdl" property="userSid" />, <bean:write name="schMdl" property="userKbn" />);">

    <!--タイトルカラー設定-->
    <logic:equal name="schMdl" property="bgColor" value="1">
    <span class="sc_link_1b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="2">
    <span class="sc_link_2b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="3">
    <span class="sc_link_3b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="4">
    <span class="sc_link_4b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="5">
    <span class="sc_link_5b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="6">
    <span class="sc_link_6b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="7">
    <span class="sc_link_7b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="8">
    <span class="sc_link_8b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="9">
    <span class="sc_link_9b">
    </logic:equal>
    <logic:equal name="schMdl" property="bgColor" value="10">
    <span class="sc_link_10b">
    </logic:equal>

    <bean:write name="schMdl" property="title" /></span></a><BR>
    <bean:write name="schMdl" property="valueStr" filter="false"/>

    <%
     } else {
    %>

    <span class="sc_link_5b"><bean:write name="schMdl" property="title" /></span>

    <%
     }
    %>
    </div>
    </td>
    </tr>
    </logic:iterate>

    </table>
  </td>
  </tr>

  <logic:notEqual name="sch100Form" property="sch100searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
  <logic:notEmpty name="sch100Form" property="sch100WebSearchWord">
  <tr>
  <td colspan="7" align="left" nowrap>

    <bean:define id="searchKeyword" name="sch100Form" property="sch100HtmlSearchWord" type="java.lang.String" />
    <a href="javascript:void(0);" class="" onmouseover="overSearch();" onmouseout="outSearch();" onClick="webSearch('<bean:write name="sch100Form" property="sch100WebSearchWord" />');">
      <span id="webSearchArea" class="text_normal">
      <gsmsg:write key="cmn.websearch" arg0="<%= searchKeyword %>" />
      </span>
    </a>

  </td>
  </tr>
  </logic:notEmpty>
  </logic:notEqual>

  <bean:size id="count2" name="sch100Form" property="sch100PageLabel" scope="request" />
  <logic:greaterThan name="count2" value="1">
  <tr>
  <td colspan="7" width="100%" align="right" valign="top" nowrap>
  <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
  <logic:notEmpty name="sch100Form" property="sch100PageLabel">
  <html:select property="sch100Slt_page2" onchange="changePage2();" styleClass="text_i">
    <html:optionsCollection name="sch100Form" property="sch100PageLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>
  <logic:empty name="sch100Form" property="sch100PageLabel">
  <html:select property="sch100Slt_page2" styleClass="text_i">
   <option value="1" class="text_i">1 / 1</option>
  </html:select>
  </logic:empty>
  <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
  </td>
  </tr>
  </logic:greaterThan>
  </logic:notEmpty>


  <tr>
  <td class="td_type_tab" colspan="7">
    <table width="100%" class="tl5">
    <tr>
    <td width="100%">&nbsp;</td>
    <td align="right">
    </td>
    </tr>
    </table>
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