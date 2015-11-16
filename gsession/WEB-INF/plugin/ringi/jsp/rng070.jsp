<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int sort_title = jp.groupsession.v2.rng.RngConst.RNG_SORT_TITLE; %>
<% int sort_name = jp.groupsession.v2.rng.RngConst.RNG_SORT_NAME; %>
<% int sort_date = jp.groupsession.v2.rng.RngConst.RNG_SORT_DATE; %>
<% int sort_jyusin = jp.groupsession.v2.rng.RngConst.RNG_SORT_JYUSIN; %>
<% int sort_kakunin = jp.groupsession.v2.rng.RngConst.RNG_SORT_KAKUNIN; %>
<% int sort_touroku = jp.groupsession.v2.rng.RngConst.RNG_SORT_TOUROKU; %>
<% int sort_kekka = jp.groupsession.v2.rng.RngConst.RNG_SORT_KEKKA; %>
<% int st_soukou = jp.groupsession.v2.rng.RngConst.RNG_STATUS_DRAFT; %>
<% int st_sinsei = jp.groupsession.v2.rng.RngConst.RNG_STATUS_REQUEST; %>
<% int st_kessai = jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED; %>
<% int st_kyakka = jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT; %>
<% int order_asc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC; %>
<% int order_desc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC; %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng050.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String onloadEvent = ""; %>
<logic:equal name="rng070Form" property="rngWebSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
  <% onloadEvent = " onload=\"hideTooltip();\""; %>
</logic:equal>

<title>[Group Session] <gsmsg:write key="rng.19" /></title>
</head>

<body class="body_03" onunload="calWindowClose();"<%= onloadEvent %>>

<html:form action="ringi/rng070">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="rngSid" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngAdminGroupSid" />
<html:hidden property="rngAdminUserSid" />
<html:hidden property="rngAdminKeyword" />
<html:hidden property="rngAdminApplYearFr" />
<html:hidden property="rngAdminApplMonthFr" />
<html:hidden property="rngAdminApplDayFr" />
<html:hidden property="rngAdminApplYearTo" />
<html:hidden property="rngAdminApplMonthTo" />
<html:hidden property="rngAdminApplDayTo" />
<html:hidden property="rngAdminLastManageYearFr" />
<html:hidden property="rngAdminLastManageMonthFr" />
<html:hidden property="rngAdminLastManageDayFr" />
<html:hidden property="rngAdminLastManageYearTo" />
<html:hidden property="rngAdminLastManageMonthTo" />
<html:hidden property="rngAdminLastManageDayTo" />
<html:hidden property="rngAdminSortKey" />
<html:hidden property="rngAdminOrderKey" />
<html:hidden property="rngAdminSearchFlg" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<input type="hidden" name="rngApprMode" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_COMPLETE) %>">
<bean:define id="iorderKey" name="rng070Form" property="rngAdminSortKey" />
<bean:define id="isortKbn" name="rng070Form" property="rngAdminOrderKey" />
<% int iOrderKey = ((Integer) iorderKey).intValue(); %>
<% int iSortKbn = ((Integer) isortKbn).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- body -->
<table width="100%" class="tl0">
<tr>
<td colspan="3">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.19" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="rng.19" />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_delete" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('rng070del')">
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('rng040')">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="rng.19" />"></td>
      </tr>
    </table>



</td>
</tr>
<logic:messagesPresent message="false">
  <tr>
     <td align="left"><html:errors/></td>
  </tr>
</logic:messagesPresent>

<tr>
<td nowrap>
  <table width="100%">

    <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="cmn.keyword" /><gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:text name="rng070Form" property="rngInputKeyword" style="width:393px;" maxlength="100" />
    </td>
    </tr>

    <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="cmn.group" />　<gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:select property="sltGroupSid" styleClass="select01" onchange="changeGroupCombo();">
        <html:optionsCollection name="rng070Form" property="rng070groupList" value="value" label="label" />
      </html:select>
      <input type="button" onclick="openGroupWindow(this.form.sltGroupSid, 'sltGroupSid', '0', '')" class="group_btn" value="&nbsp;&nbsp;" id="rng070GroupBtn">
    </td>
    </tr>

    <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="rng.47" />　<gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:select property="sltUserSid" styleClass="select01">
        <html:optionsCollection name="rng070Form" property="rng070userList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
      <td valign="top" nowrap width="5%">
        <span class="text_bb1"><gsmsg:write key="rng.application.date" />　<gsmsg:write key="wml.215" /></span>
      </td>
      <td width="95%" valign="top" align="left" nowrap>
        <html:select property="sltApplYearFr" styleId="applYearFr">
           <html:optionsCollection name="rng070Form" property="rng070YearList" value="value" label="label" />
        </html:select>
        <html:select property="sltApplMonthFr" styleId="applMonthFr">
           <html:optionsCollection name="rng070Form" property="rng070MonthList" value="value" label="label" />
        </html:select>
        <html:select property="sltApplDayFr" styleId="applDayFr">
           <html:optionsCollection name="rng070Form" property="rng070DayList" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" onclick="wrtCalendar(this.form.applDayFr, this.form.applMonthFr, this.form.applYearFr)" class="calendar_btn">

        &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

        <html:select property="sltApplYearTo" styleId="applYearTo">
        <html:optionsCollection name="rng070Form" property="rng070YearList" value="value" label="label" />
        </html:select>
        <html:select property="sltApplMonthTo" styleId="applMonthTo">
           <html:optionsCollection name="rng070Form" property="rng070MonthList" value="value" label="label" />
        </html:select>
        <html:select property="sltApplDayTo" styleId="applDayTo">
           <html:optionsCollection name="rng070Form" property="rng070DayList" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" onclick="wrtCalendar(this.form.applDayTo, this.form.applMonthTo, this.form.applYearTo)" class="calendar_btn">
      </td>
    </tr>

    <tr>
      <td valign="top" nowrap width="5%">
        <span class="text_bb1"><gsmsg:write key="rng.105" />　<gsmsg:write key="wml.215" /></span>
      </td>
      <td width="95%" valign="top" align="left" nowrap>
        <html:select property="sltLastManageYearFr" styleId="lastManageYearFr">
          <html:optionsCollection name="rng070Form" property="rng070YearList" value="value" label="label" />
        </html:select>
        <html:select property="sltLastManageMonthFr" styleId="lastManageMonthFr">
          <html:optionsCollection name="rng070Form" property="rng070MonthList" value="value" label="label" />
        </html:select>
        <html:select property="sltLastManageDayFr" styleId="lastManageDayFr">
          <html:optionsCollection name="rng070Form" property="rng070DayList" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" onclick="wrtCalendar(this.form.lastManageDayFr, this.form.lastManageMonthFr, this.form.lastManageYearFr)" class="calendar_btn">

        &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

        <html:select property="sltLastManageYearTo" styleId="lastManageYearTo">
          <html:optionsCollection name="rng070Form" property="rng070YearList" value="value" label="label" />
        </html:select>
        <html:select property="sltLastManageMonthTo" styleId="lastManageMonthTo">
          <html:optionsCollection name="rng070Form" property="rng070MonthList" value="value" label="label" />
        </html:select>
        <html:select property="sltLastManageDayTo" styleId="lastManageDayTo">
          <html:optionsCollection name="rng070Form" property="rng070DayList" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" onclick="wrtCalendar(this.form.lastManageDayTo, this.form.lastManageMonthTo, this.form.lastManageYearTo)" class="calendar_btn">
      </td>
    </tr>
    <tr><td colspan="2">&nbsp</td></tr>
    <tr>
    <td width="100%" valign="bottom" align="center" colspan="2"><input type="submit" name="btn_usrimp" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('search')"></td>
    </tr>
  </table>
</td>
</tr>


<tr>
<td class="td_type_tab" colspan="3">
  <table class="tl0" width="100%" cellpadding="5" cellspacing="0" id="selectionSearchArea">
  <tr>
    <td colspan="6" width="100%" align="right" valign="top">
      <img src="../common/images/keiro.gif" alt="<gsmsg:write key="rng.60" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.60" />&nbsp;
      <img src="../common/images/keiro_ok.gif" alt="<gsmsg:write key="rng.43" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.43" />&nbsp;
      <img src="../common/images/arrow_keiro.gif" alt="<gsmsg:write key="rng.48" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.48" />&nbsp;
      <img src="../common/images/keiro_ng.gif" alt="<gsmsg:write key="rng.22" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.22" />
    </td>
  </tr>

<logic:notEmpty name="rng070Form" property="rng070dataList">
<bean:size id="count1" name="rng070Form" property="rngAdminPageList" scope="request" />
<logic:greaterThan name="count1" value="1">
  <tr>
    <td colspan="6" width="100%" align="right" valign="top">
      <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onClick="buttonPush('pageleft');">
      <logic:notEmpty name="rng070Form" property="rngAdminPageList">
        <html:select property="rngAdminPageTop" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="rng070Form" property="rngAdminPageList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
      <logic:empty name="rng070Form" property="rngAdminPageList">
        <html:select property="rngAdminPageTop" styleClass="text_i">
          <option value="1" class="text_i">1 / 1</option>
        </html:select>
      </logic:empty>
      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');">
    </td>
  </tr>
</logic:greaterThan>
</logic:notEmpty>
  <tr>
    <th width="1%" class="table_bg_7D91BD">&nbsp;</th>
    <!-- 結果 -->
    <% if (iOrderKey == sort_kekka) { %>
      <% if (iSortKbn == order_desc) { %>
      <th width="4%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_kekka %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="cmn.results" /><gsmsg:write key="tcd.tcd040.23" /></a></th>
      <% } else { %>
      <th width="4%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_kekka %>, <%= order_desc %>);" class="text_tlw"><gsmsg:write key="cmn.results" /><gsmsg:write key="tcd.tcd040.22" /></a></th>
      <% } %>
    <% } else { %>
      <th width="4%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_kekka %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="cmn.results" /></a></th>
    <% } %>
    <!-- 件名 -->
    <% if (iOrderKey == sort_title) { %>
      <% if (iSortKbn == order_desc) { %>
      <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_title %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="cmn.subject" /><gsmsg:write key="tcd.tcd040.23" /></a></th>
      <% } else { %>
      <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_title %>, <%= order_desc %>);" class="text_tlw"><gsmsg:write key="cmn.subject" /><gsmsg:write key="tcd.tcd040.22" /></a></th>
      <% } %>
    <% } else { %>
      <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_title %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="cmn.subject" /></a></th>
    <% } %>
    <!-- 申請日時 -->
    <% if (iOrderKey == sort_date) { %>
      <% if (iSortKbn == order_desc) { %>
      <th width="10%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_date %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="rng.application.date" /><gsmsg:write key="tcd.tcd040.23" /></a></th>
      <% } else { %>
      <th width="10%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_date %>, <%= order_desc %>);" class="text_tlw"><gsmsg:write key="rng.application.date" /><gsmsg:write key="tcd.tcd040.22" /></a></th>
      <% } %>
    <% } else { %>
      <th width="10%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_date %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="rng.application.date" /></a></th>
    <% } %>
    <!-- 最終処理日時 -->
    <% if (iOrderKey == sort_kakunin) { %>
      <% if (iSortKbn == order_desc) { %>
      <th width="10%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_kakunin %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="rng.105" /><gsmsg:write key="tcd.tcd040.23" /></a></th>
      <% } else { %>
      <th width="10%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_kakunin %>, <%= order_desc %>);" class="text_tlw"><gsmsg:write key="rng.105" /><gsmsg:write key="tcd.tcd040.22" /></a></th>
      <% } %>
    <% } else { %>
      <th width="10%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_kakunin %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="rng.105" /></a></th>
    <% } %>
    <!-- 経路状況 -->
    <% if (iOrderKey == sort_name) { %>
      <% if (iSortKbn == order_desc) { %>
      <th width="50%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_name %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="rng.channel.status" /><gsmsg:write key="tcd.tcd040.23" /></a></th>
      <% } else { %>
      <th width="50%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_name %>, <%= order_desc %>);" class="text_tlw"><gsmsg:write key="rng.channel.status" /><gsmsg:write key="tcd.tcd040.22" /></a></th>
      <% } %>
    <% } else { %>
      <th width="50%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return sorton(<%= sort_name %>, <%= order_asc %>);" class="text_tlw"><gsmsg:write key="rng.channel.status" /></a></th>
    <% } %>
  </tr>

<bean:define id="mod" value="0" />
<logic:notEmpty name="rng070Form" property="rng070dataList">
<logic:iterate id="rngkn" name="rng070Form" property="rng070dataList" indexId="idx" scope="request">
<logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
  <bean:define id="tblColor" value="td_type1" />
</logic:equal>
<logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
  <bean:define id="tblColor" value="td_type25_2" />
</logic:notEqual>
<bean:define id="rngstatus" name="rngkn" property="rngStatus" />
<% int status = ((Integer) rngstatus).intValue();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String error = gsMsg.getMessage(request, "wml.js.68");
        String soukou = gsMsg.getMessage(request, "cmn.draft");
        String sinsei = gsMsg.getMessage(request, "rng.48");
        String kessai = gsMsg.getMessage(request, "rng.29");
        String kyakka = gsMsg.getMessage(request, "rng.22");
%>
<% String kekka = error; %>
<% if (status == st_soukou) { %>
<%   kekka = soukou; %>
<% } else if (status == st_sinsei) { %>
<%   kekka = sinsei; %>
<% } else if (status == st_kessai) { %>
<%   kekka = kessai; %>
<% } else if (status == st_kyakka) { %>
<%   kekka = kyakka; %>
<% } else { %>
<% } %>
  <tr>
  <td class="<bean:write name="tblColor" />" align="center" valign="middle">
    <html:multibox name="rng070Form" property="rng070dellist">
      <bean:write name="rngkn" property="rngSid" />
    </html:multibox>
  </td>
  <td class="<bean:write name="tblColor" />" align="center" valign="middle"><%= kekka %></td>
  <td class="<bean:write name="tblColor" />" align="left" valign="middle"><a href="#" onclick="clickRingi('rng030', <bean:write name="rngkn" property="rngSid" />);"><bean:write name="rngkn" property="rngTitle" /></a></td>
  <td class="<bean:write name="tblColor" />" align="center" valign="middle"><bean:write name="rngkn" property="strRngAppldate" /></td>
  <td class="<bean:write name="tblColor" />" align="center" valign="middle"><bean:write name="rngkn" property="strLastManageDate" /></td>
  <td class="<bean:write name="tblColor" />" align="left" valign="middle">

<% String apprUserClass = "";%>
<logic:equal name="rngkn" property="apprUserDelFlg" value="true">
<% apprUserClass = "text_appruser_del"; %>
</logic:equal>

  <span class="<%= apprUserClass %>"><bean:write name="rngkn" property="apprUser" /></span>
  <logic:notEmpty name="rngkn" property="channelList">
  <% int beforeStatus = jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR; %>

  <logic:iterate id="channelData" name="rngkn" property="channelList" indexId="channelIdx">
  <bean:define id="rncStatus" name="channelData" property="rncStatus" />
  <% int intRncStatus = ((Integer) rncStatus).intValue(); %>
  <% if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_CONFIRM) { %>
  <img src="../common/images/arrow_keiro.gif" alt="<gsmsg:write key="rng.48" />">
  <% } else if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR) { %>
  <img src="../common/images/keiro_ok.gif" alt="<gsmsg:write key="rng.43" />">
  <% } else if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_NOSET) { %>
  <img src="../common/images/keiro.gif" alt="<gsmsg:write key="rng.60" />">
  <% } else if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_DENIAL) { %>
  <img src="../common/images/keiro_ng.gif" alt="<gsmsg:write key="rng.22" />">
  <% } %>

  <logic:equal name="channelData" property="delUser" value="true">
    <strike><bean:write name="channelData" property="userName" /></strike>
  </logic:equal>
  <logic:equal name="channelData" property="delUser" value="false">
    <bean:write name="channelData" property="userName" />
  </logic:equal>

  <% beforeStatus = intRncStatus; %>
  </logic:iterate>

  </logic:notEmpty>


  </td>
  </tr>

</logic:iterate>
</logic:notEmpty>
  </table>
</td>
</tr>
<logic:notEmpty name="rng070Form" property="rng070dataList">
<bean:size id="count1" name="rng070Form" property="rngAdminPageList" scope="request" />
<logic:greaterThan name="count1" value="1">
  <tr>
    <td colspan="6" width="100%" align="right" valign="top">
      <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" src="../common/images/arrow2_l.gif" border="0" onClick="buttonPush('pageleft');">
      <logic:notEmpty name="rng070Form" property="rngAdminPageList">
        <html:select property="rngAdminPageBottom" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="rng070Form" property="rngAdminPageList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
      <logic:empty name="rng070Form" property="rngAdminPageList">
        <html:select property="rngAdminPageBottom" styleClass="text_i">
          <option value="1" class="text_i">1 / 1</option>
        </html:select>
      </logic:empty>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');">
    </td>
  </tr>
</logic:greaterThan>
</logic:notEmpty>
<tr>
<td class="td_type_tab" colspan="3">
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
<logic:equal name="rng070Form" property="rngWebSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
<span id="tooltip_search" class="tooltip_search"></span>
</logic:equal>
</body>
</html:html>