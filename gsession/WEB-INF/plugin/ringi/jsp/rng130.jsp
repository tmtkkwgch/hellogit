<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../ringi/js/rng130.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<title>[Group Session] <gsmsg:write key="rng.rng130.09" /></title>
</head>

<body class="body_03" onload="controlInput();" onunload="windowClose();calWindowClose();">

<html:form action="ringi/rng130">

<input type="hidden" name="CMD" value="search">

<html:hidden property="rngSid" />
<input type="hidden" name="rngCmdMode" value="0">
<input type="hidden" name="rngApprMode" value="0">
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="svRngKeyword" />
<html:hidden property="svRng130Type" />
<html:hidden property="svGroupSid" />
<html:hidden property="svUserSid" />
<html:hidden property="svRng130keyKbn" />
<html:hidden property="svRng130searchSubject1" />
<html:hidden property="svRng130searchSubject2" />
<html:hidden property="svSortKey1" />
<html:hidden property="svRng130orderKey1" />
<html:hidden property="svSortKey2" />
<html:hidden property="svRng130orderKey2" />
<html:hidden property="svApplYearFr" />
<html:hidden property="svApplMonthFr" />
<html:hidden property="svApplDayFr" />
<html:hidden property="svApplYearTo" />
<html:hidden property="svApplMonthTo" />
<html:hidden property="svApplDayTo" />
<html:hidden property="svLastManageYearFr" />
<html:hidden property="svLastManageMonthFr" />
<html:hidden property="svLastManageDayFr" />
<html:hidden property="svLastManageYearTo" />
<html:hidden property="svLastManageMonthTo" />
<html:hidden property="svLastManageDayTo" />

<html:hidden property="rng130searchFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="rng.rng130.10" /> ]</td>

        <td width="100%" class="header_white_bg">&nbsp;</td>

        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backRngList');">
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table width="100%">
    <tr><td><html:errors/></td></tr>
    </table>
    </logic:messagesPresent>

    <% String sort_title = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SORT_TITLE); %>
    <% String sort_name = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SORT_NAME); %>
    <% String sort_date = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SORT_DATE); %>
    <% String sort_jyusin = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SORT_JYUSIN); %>
    <% String sort_touroku = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SORT_TOUROKU); %>
    <% String sort_kakunin = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SORT_KAKUNIN); %>

    <table width="100%" class="tl0 search_tbl_base">

    <tr>
    <td width="100%" height="30px" colspan="4" class="table_bg_7D91BD_search">
       <img src="../circular/images/spacer.gif" width="1" height="20" align="left">
       <img src="../common/images/search_icon.gif" class="img_bottom" alt="<gsmsg:write key="cmn.advanced.search" />"><span class="text_tlw3"><gsmsg:write key="cmn.advanced.search" /></span>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.type" /></th>
    <td width="90%" class="td_sub_detail" colspan="3">
      <html:radio name="rng130Form" property="rng130Type" styleId="type1" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_MODE_JYUSIN) %>" onclick="changeProcType();" /><label for="type1"><gsmsg:write key="cmn.receive" /></label>
      <html:radio name="rng130Form" property="rng130Type" styleId="type2" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_MODE_SINSEI) %>" onclick="changeProcType();" /><label for="type2"><gsmsg:write key="rng.48" /></label>
      <html:radio name="rng130Form" property="rng130Type" styleId="type3" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_MODE_KANRYO) %>" onclick="changeProcType();" /><label for="type3"><gsmsg:write key="cmn.complete" /></label>
      <html:radio name="rng130Form" property="rng130Type" styleId="type4" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_MODE_SOUKOU) %>" onclick="changeProcType();" /><label for="type4"><gsmsg:write key="cmn.draft" /></label>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.keyword" /></th>
    <td class="td_sub_detail">
      <html:text name="rng130Form" property="rngKeyword" maxlength="100" style="width:333px;" />
      <div class="text_base2">
        <html:radio name="rng130Form" property="rng130keyKbn" value="<%= String.valueOf( jp.groupsession.v2.rng.RngConst.RNG_SEARCHTYPE_AND) %>" styleId="rng130keyKbn_01" /><label for="rng130keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;<html:radio name="rng130Form" property="rng130keyKbn" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_SEARCHTYPE_OR) %>" styleId="rng130keyKbn_02" /><label for="rng130keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
      </div>
    </td>

    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.search2" /></th>
    <td width="40%" class="td_sub_detail">
      <html:checkbox name="rng130Form" property="rng130searchSubject1" value="1" styleId="search_scope_01" /><label for="search_scope_01"><gsmsg:write key="cmn.subject" /></label>
      <html:checkbox name="rng130Form" property="rng130searchSubject2" value="1" styleId="search_scope_02" /><label for="search_scope_02"><gsmsg:write key="cmn.content" /></label>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="rng.application.date" /></th>
    <td width="90%" class="td_sub_detail" colspan="3">
      <html:select property="sltApplYearFr" styleId="applYearFr">
         <html:optionsCollection name="rng130Form" property="rng130YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplMonthFr" styleId="applMonthFr">
         <html:optionsCollection name="rng130Form" property="rng130MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplDayFr" styleId="applDayFr">
         <html:optionsCollection name="rng130Form" property="rng130DayList" value="value" label="label" />
      </html:select>
      <input type="button" name="applDateFrBtn" value="Cal" onclick="wrtCalendar(this.form.applDayFr, this.form.applMonthFr, this.form.applYearFr)" class="calendar_btn">

      &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

      <html:select property="sltApplYearTo" styleId="applYearTo">
      <html:optionsCollection name="rng130Form" property="rng130YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplMonthTo" styleId="applMonthTo">
         <html:optionsCollection name="rng130Form" property="rng130MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplDayTo" styleId="applDayTo">
         <html:optionsCollection name="rng130Form" property="rng130DayList" value="value" label="label" />
      </html:select>
      <input type="button" name="applDateToBtn" value="Cal" onclick="wrtCalendar(this.form.applDayTo, this.form.applMonthTo, this.form.applYearTo)" class="calendar_btn">
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="rng.36" /></th>
    <td width="90%" class="td_sub_detail" colspan="3">
      <html:select property="sltLastManageYearFr" styleId="lastManageYearFr">
        <html:optionsCollection name="rng130Form" property="rng130YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageMonthFr" styleId="lastManageMonthFr">
        <html:optionsCollection name="rng130Form" property="rng130MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageDayFr" styleId="lastManageDayFr">
        <html:optionsCollection name="rng130Form" property="rng130DayList" value="value" label="label" />
      </html:select>
      <input type="button" name="lastManageDateFrBtn" value="Cal" onclick="wrtCalendar(this.form.lastManageDayFr, this.form.lastManageMonthFr, this.form.lastManageYearFr)" class="calendar_btn">

      &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

      <html:select property="sltLastManageYearTo" styleId="lastManageYearTo">
        <html:optionsCollection name="rng130Form" property="rng130YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageMonthTo" styleId="lastManageMonthTo">
        <html:optionsCollection name="rng130Form" property="rng130MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageDayTo" styleId="lastManageDayTo">
        <html:optionsCollection name="rng130Form" property="rng130DayList" value="value" label="label" />
      </html:select>
      <input type="button" name="lastManageDateToBtn" value="Cal" onclick="wrtCalendar(this.form.lastManageDayTo, this.form.lastManageMonthTo, this.form.lastManageYearTo)" class="calendar_btn">
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.sort.order" /></th>
    <td class="td_sub_detail" colspan="3">
    <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
      <html:select property="sltSortKey1" styleClass="select01">
        <html:optionsCollection name="rng130Form" property="sortKeyList" value="value" label="label" />
      </html:select>
      <html:radio name="rng130Form" property="rng130orderKey1" styleId="sort1_up" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC) %>" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="rng130Form" property="rng130orderKey1" styleId="sort1_dw" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC) %>" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
      &nbsp;&nbsp;&nbsp;&nbsp;
    <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
      <html:select property="sltSortKey2" styleClass="select01">
        <html:optionsCollection name="rng130Form" property="sortKeyList" value="value" label="label" />
      </html:select>
      <html:radio name="rng130Form" property="rng130orderKey2" styleId="sort2_up" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC) %>" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="rng130Form" property="rng130orderKey2" styleId="sort2_dw" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC) %>" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

    </td>
    </tr>

    </table>

    <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>
    <div align="center"><input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1"></div>
    <br class="clear">

    <logic:notEmpty name="rng130Form" property="pageList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">
        <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select property="rng130pageTop" onchange="selectPage(0);" styleClass="text_i">
          <html:optionsCollection name="rng130Form" property="pageList" value="value" label="label" />
        </html:select>
        <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
      </td>
    </tr>
    </table>
    </logic:notEmpty>

    <logic:notEmpty name="rng130Form" property="rng130rngDataList">
    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

    <bean:define id="rngType" name="rng130Form" property="rng130Type" type="Integer"/>
    <% int intRngType = rngType.intValue(); %>

    <bean:define id="procMode" name="rng130Form" property="svRng130Type" type="Integer"/>
    <% int sMode = procMode.intValue(); %>
    <% int mode0 = jp.groupsession.v2.rng.RngConst.RNG_MODE_JYUSIN; %>
    <% int mode1 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SINSEI; %>
    <% int mode2 = jp.groupsession.v2.rng.RngConst.RNG_MODE_KANRYO; %>
    <% int mode3 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SOUKOU; %>

    <% String rngstatus_settlet = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED); %>
    <% String rngstatus_reject = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT); %>
    <tr class="table_bg_7D91BD_search">
<% if (sMode == mode0) { %>
    <th width="59%"><a href="#" onClick="clickSortTitle(<%= sort_title %>);"><span class="text_tlw"><gsmsg:write key="cmn.title" /></span></a></th>
    <% if (intRngType != mode3) { %>
    <th width="15%"><a href="#" onClick="clickSortTitle(<%= sort_name %>);"><span class="text_tlw"><gsmsg:write key="rng.47" /></span></a></th>
    <th width="13%"><a href="#" onClick="clickSortTitle(<%= sort_date %>);"><span class="text_tlw"><gsmsg:write key="rng.application.date" /></span></a></th>
    <% } else { %>
    <th width="15%"><span class="text_tlw"><gsmsg:write key="rng.47" /></th>
    <th width="13%"><span class="text_tlw"><gsmsg:write key="rng.application.date" /></th>
    <% } %>
    <% if (intRngType == mode0) { %>
    <th width="13%"><a href="#" onClick="clickSortTitle(<%= sort_jyusin %>);"><span class="text_tlw"><gsmsg:write key="cmn.received.date" /></span></a></th>
    <% } else { %>
    <th width="13%"><span class="text_tlw"><gsmsg:write key="cmn.received.date" /></a></th>
    <% } %>
<% } else if (sMode == mode1) { %>
    <th width="59%"><a href="#" onClick="clickSortTitle(<%= sort_title %>);"><span class="text_tlw"><gsmsg:write key="cmn.title" /></span></a></th>
    <% if (intRngType != mode3) { %>
    <th width="15%"><a href="#" onClick="clickSortTitle(<%= sort_name %>);"><span class="text_tlw"><gsmsg:write key="rng.47" /></span></a></th>
    <th width="13%"><a href="#" onClick="clickSortTitle(<%= sort_date %>);"><span class="text_tlw"><gsmsg:write key="rng.application.date" /></span></a></th>
    <% } else { %>
    <th width="15%"><span class="text_tlw"><gsmsg:write key="rng.47" /></th>
    <th width="13%"><span class="text_tlw"><gsmsg:write key="rng.application.date" /></th>
    <% } %>
    <% if (intRngType == mode1 || intRngType == mode2) { %>
    <th width="13%"><a href="#" onClick="clickSortTitle(<%= sort_kakunin %>);"><span class="text_tlw"><gsmsg:write key="rng.105" /></span></a></th>
    <% } else { %>
    <th width="13%"><span class="text_tlw"><gsmsg:write key="rng.105" /></span></th>
    <% } %>
<% } else if (sMode == mode2) { %>
    <th width="59%" colspan="2"><a href="#" onClick="clickSortTitle(<%= sort_title %>);"><span class="text_tlw"><gsmsg:write key="cmn.title" /></span></a></th>
    <% if (intRngType != mode3) { %>
    <th width="15%"><a href="#" onClick="clickSortTitle(<%= sort_name %>);"><span class="text_tlw"><gsmsg:write key="rng.47" /></span></a></th>
    <th width="13%"><a href="#" onClick="clickSortTitle(<%= sort_date %>);"><span class="text_tlw"><gsmsg:write key="rng.application.date" /></span></a></th>
    <% } else { %>
    <th width="15%"><span class="text_tlw"><gsmsg:write key="rng.47" /></th>
    <th width="13%"><span class="text_tlw"><gsmsg:write key="rng.application.date" /></th>
    <% } %>
    <% if (intRngType == mode1 || intRngType == mode2) { %>
    <th width="13%"><a href="#" onClick="clickSortTitle(<%= sort_kakunin %>);"><span class="text_tlw"><gsmsg:write key="rng.105" /></span></a></th>
    <% } else { %>
    <th width="13%"><span class="text_tlw"><gsmsg:write key="rng.105" /></span></th>
    <% } %>
<% } else if (sMode == mode3) { %>
    <th width="83%"><a href="#" onClick="clickSortTitle(<%= sort_title %>);"><span class="text_tlw"><gsmsg:write key="cmn.title" /></span></a></th>
    <% if (intRngType == mode3) { %>
    <th width="17%"><a href="#" onClick="clickSortTitle(<%= sort_touroku %>);"><span class="text_tlw"><gsmsg:write key="rng.37" /></span></a></th>
    <% } else { %>
    <th width="17%"><span class="text_tlw"><gsmsg:write key="rng.37" /></span></th>
    <% } %>
<% } %>
    </tr>

    <% String[] trclass = new String[] {"td_line_color1", "td_line_color2"}; %>

    <logic:iterate id="rngData" name="rng130Form" property="rng130rngDataList" indexId="idx" scope="request">
    <tr class="<%= trclass[idx.intValue() % 2] %>">

<% String apprUserClass = "";%>
<logic:equal name="rngData" property="apprUserDelFlg" value="true">
<% apprUserClass = "text_appruser_del"; %>
</logic:equal>

<% if (sMode == mode0) { %>
      <% String apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPR); %>
      <logic:equal name="rngData" property="rncType" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_RNCTYPE_APPL) %>">
        <% apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPL); %>
      </logic:equal>
      <td align="left"><a href="#" onclick="clickJyusinRingi('rngDetail', 0, <%= apprMode %>, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td align="left" class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
      <td align="center"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td align="center"><bean:write name="rngData" property="strRcvDate" /></td>
<% } else if (sMode == mode1) { %>
      <td align="left"><a href="#" onclick="clickRingi('rngDetail', 0, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td align="left" class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
      <td align="center"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td align="center"><bean:write name="rngData" property="strLastManageDate" /></td>
<% } else if (sMode == mode2) { %>
      <td align="left" width="50%"><a href="#" onclick="clickRingi('rngDetail', 0, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <% String rngStatus = "&nbsp;"; %>
      <logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_settlet %>">
<%      jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String kessai = gsMsg.getMessage(request, "rng.64");
        String kyakka = gsMsg.getMessage(request, "rng.65");
%>
        <% rngStatus = kessai; %>
      </logic:equal>
      <logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_reject %>">
<%      jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String kessai = gsMsg.getMessage(request, "rng.64");
        String kyakka = gsMsg.getMessage(request, "rng.65");
%>
        <% rngStatus = kyakka; %>
      </logic:equal>
      <td align="center" nowrap width="8%"><%= rngStatus %></td>
      <td align="left" class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
      <td align="center"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td align="center"><bean:write name="rngData" property="strLastManageDate" /></td>
<% } else if (sMode == mode3) { %>
      <td align="left" width="86%"><a href="#" onclick="clickRingi('rngEdit', 1, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td align="center"><bean:write name="rngData" property="strMakeDate" /></td>
<% } %>

    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>

    <logic:notEmpty name="rng130Form" property="pageList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">
        <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select property="rng130pageBottom" onchange="selectPage(1);" styleClass="text_i">
          <html:optionsCollection name="rng130Form" property="pageList" value="value" label="label" />
        </html:select>
        <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
      </td>
    </tr>
    </table>
    </logic:notEmpty>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>