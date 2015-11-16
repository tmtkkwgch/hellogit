<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
String orderAsc  = String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC);
String orderDesc = String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC);
%>

<%
String sortDate  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_DATE);
String sortPulugin  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_PLUGIN);
String sortLevel  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_LOG_LEVEL);
String sortUsrName  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_USR_NAME);
String sortPgName  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_PG_NAME);
String sortOpCode  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_OP_CODE);
String sortValue  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_VALUE);
String sortLogIp  = String.valueOf(jp.groupsession.v2.man.man250.Man250Biz.SORT_KEY_LOG_IP);

String logLevelError  = gsMsg.getMessage(request, "man.error");
String logLevelWarn  = gsMsg.getMessage(request, "cmn.warning");
String logLevelInfo  = gsMsg.getMessage(request, "main.man240.2");
String logLevelTrace  = gsMsg.getMessage(request, "main.man240.3");

%>

<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="main.man002.53" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man250.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

</head>

<body class="body_03">
<html:form action="/main/man250">

<input type="hidden" name="CMD" value="">

<html:hidden property="man250PageNum" />
<html:hidden property="man250SvSltGroup" />
<html:hidden property="man250SvSltUser" />
<html:hidden property="man250SvSltYearFr" />
<html:hidden property="man250SvSltMonthFr" />
<html:hidden property="man250SvSltDayFr" />
<html:hidden property="man250SvSltHourFr" />
<html:hidden property="man250SvSltMinFr" />
<html:hidden property="man250SvSltYearTo" />
<html:hidden property="man250SvSltMonthTo" />
<html:hidden property="man250SvSltDayTo" />
<html:hidden property="man250SvSltHourTo" />
<html:hidden property="man250SvSltMinTo" />
<html:hidden property="man250SvSltPlugin" />
<html:hidden property="man250SvSltLogError" />
<html:hidden property="man250SvSltLogWarn" />
<html:hidden property="man250SvSltLogInfo" />
<html:hidden property="man250SvSltLogTrace" />
<html:hidden property="man250SvOrderKey1" />
<html:hidden property="man250SvSortKey1" />
<html:hidden property="man250SvOrderKey2" />
<html:hidden property="man250SvSortKey2" />

<logic:notEmpty name="man250Form" property="man250SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="man250Form" property="man250SvSearchTarget" scope="request">
    <input type="hidden" name="man250SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
  <html:hidden property="man250SvKeyWord" />
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.53" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:notEmpty name="man250Form" property="man250DspList">
      <input type="button" name="btn_del" class="btn_dell_n1" value="<gsmsg:write key="man.purge" />" onClick="buttonPush('man250delete');">
      </logic:notEmpty>
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0 tbl_base">

    <logic:messagesPresent message="false">
    <tr>
    <td  colspan="4" width="100%" nowrap>
      <table width="100%">
      <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:messagesPresent>

    <tr>
    <td colspan="4" class="table_bg_7D91BD" align="center">
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
    <td align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.loglevel" /></span></td>
    <td colspan="3"align="left" class="td_type20" nowrap>
      <html:checkbox styleId="search_scope_01" property="man250SltLogError" value="1" /><label for="search_scope_01"><img alt="<gsmsg:write key="man.error" />" class="img_bottom" src="../main/images/icon_log_error.gif" border="0"><gsmsg:write key="man.error" />&nbsp;</label>
      <html:checkbox styleId="search_scope_02" property="man250SltLogWarn" value="1" /><label for="search_scope_02"><img alt="<gsmsg:write key="cmn.warning" />" class="img_bottom" src="../main/images/icon_log_warn.gif" border="0"><gsmsg:write key="cmn.warning" />&nbsp;</label>
      <html:checkbox styleId="search_scope_03" property="man250SltLogInfo" value="1" /><label for="search_scope_03"><img alt="<gsmsg:write key="main.man240.2" />" class="img_bottom" src="../main/images/icon_log_info.gif" border="0"><gsmsg:write key="main.man240.2" />&nbsp;</label>
      <html:checkbox styleId="search_scope_04" property="man250SltLogTrace" value="1" /><label for="search_scope_04"><img alt="<gsmsg:write key="main.man240.3" />" class="img_bottom" src="../main/images/icon_log_trace.gif" border="0"><gsmsg:write key="main.man240.3" /></label>
    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="main.man250.2" /></span></td>
    <td colspan="3" class="td_type20">

    <logic:notEmpty name="man250Form" property="yearLabel">
      <html:select property="man250SltYearFr" styleId="fromYear">
        <html:optionsCollection name="man250Form" property="yearLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <logic:notEmpty name="man250Form" property="monthLabel">
      <html:select property="man250SltMonthFr" styleId="fromMonth">
        <html:optionsCollection name="man250Form" property="monthLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <logic:notEmpty name="man250Form" property="dayLabel">
      <html:select property="man250SltDayFr" styleId="fromDay">
        <html:optionsCollection name="man250Form" property="dayLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <input type="button" value="Cal" name="frCalendarBtn" onclick="wrtCalendar(this.form.fromDay, this.form.fromMonth, this.form.fromYear)" class="calendar_btn">

    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="moveDay(1, 1)">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="moveDay(1, 2)">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="moveDay(1, 3)">&nbsp;

    <logic:notEmpty name="man250Form" property="hourLabel">
      <html:select property="man250SltHourFr">
        <html:optionsCollection name="man250Form" property="hourLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.hour.input" />

    <logic:notEmpty name="man250Form" property="minLabel">
      <html:select property="man250SltMinFr">
        <html:optionsCollection name="man250Form" property="minLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="main.man250.3" /></span></td>
    <td colspan="3" class="td_type20">
    <logic:notEmpty name="man250Form" property="yearLabel">
      <html:select property="man250SltYearTo" styleId="toYear">
        <html:optionsCollection name="man250Form" property="yearLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <logic:notEmpty name="man250Form" property="monthLabel">
      <html:select property="man250SltMonthTo" styleId="toMonth">
        <html:optionsCollection name="man250Form" property="monthLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <logic:notEmpty name="man250Form" property="dayLabel">
      <html:select property="man250SltDayTo" styleId="toDay">
        <html:optionsCollection name="man250Form" property="dayLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <input type="button" value="Cal" name="frCalendarBtn" onclick="wrtCalendar(this.form.toDay, this.form.toMonth, this.form.toYear)" class="calendar_btn">

    <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="moveDay(2, 1)">
    <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="moveDay(2, 2)">
    <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="moveDay(2, 3)">&nbsp;

    <logic:notEmpty name="man250Form" property="hourLabel">
      <html:select property="man250SltHourTo">
        <html:optionsCollection name="man250Form" property="hourLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.hour.input" />

    <logic:notEmpty name="man250Form" property="minLabel">
      <html:select property="man250SltMinTo">
        <html:optionsCollection name="man250Form" property="minLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.plugin" /></span></td>
    <td colspan="3" class="td_type20" nowrap>

    <logic:notEmpty name="man250Form" property="plgLabel">
      <html:select property="man250SltPlugin" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="man250Form" property="plgLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray" width="14%" nowrap><span class="text_bb2"><gsmsg:write key="man.run.user" /></span></td>
    <td  colspan="3" class="td_type20" width="86%" nowrap>
    <logic:notEmpty name="man250Form" property="grpLabel">
      <html:select property="man250SltGroup" styleClass="select01" onchange="changeGroupCombo();">
        <html:optionsCollection name="man250Form" property="grpLabel" value="value" label="label" />
      </html:select>
      <input type="button" onclick="openGroupWindowForMan250(this.form.man250SltGroup, 'man250SltGroup', '0', 'research')" class="group_btn" value="&nbsp;&nbsp;" id="man250GroupBtn">
    </logic:notEmpty>

    <logic:notEmpty name="man250Form" property="usrLabel">
      <html:select property="man250SltUser" styleClass="select01">
        <html:optionsCollection name="man250Form" property="usrLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td  align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.keyword" /></span></td>
    <td  class="td_type20" nowrap><span class="text_base2">
    <html:text name="man250Form" maxlength="50" property="man250KeyWord" style="width:333px;" />
    <div class="text_base2">
    <html:radio name="man250Form" property="man250KeyWordKbn" value="0" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
    <html:radio name="man250Form" property="man250KeyWordKbn" value="1" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
    </div>
    </span>
    </td>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.search2" /></th>
    <td width="40%" class="td_sub_detail">
      <html:multibox styleId="search_target_01" name="man250Form" property="man250SearchTarget" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.SEARCH_TARGET_FUNC) %>" /><label for="search_target_01"><gsmsg:write key="main.scr.feature.name" /></label>&nbsp;
      <html:multibox styleId="search_target_02" name="man250Form" property="man250SearchTarget" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.SEARCH_TARGET_OPERATION) %>" /><label for="search_target_02"><gsmsg:write key="cmn.operations" /></label>&nbsp;
      <html:multibox styleId="search_target_03" name="man250Form" property="man250SearchTarget" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.SEARCH_TARGET_CONTENT) %>" /><label for="search_target_03"><gsmsg:write key="cmn.content" /></label>&nbsp;
      <html:multibox styleId="search_target_04" name="man250Form" property="man250SearchTarget" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.SEARCH_TARGET_IPADDRESS) %>" /><label for="search_target_04"><gsmsg:write key="cmn.ipaddress" /></label>
    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sort.order" /></span></td>
    <td colspan="3" class="td_type20" nowrap>
      <span class="text_base2">

      <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
      <html:select property="man250SortKey1" styleClass="select01">
        <html:optionsCollection name="man250Form" property="sortLabel" value="value" label="label" />
      </html:select>
      <html:radio name="man250Form" property="man250OrderKey1" styleId="sort1_up" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="man250Form" property="man250OrderKey1" styleId="sort1_dw" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
      <html:select property="man250SortKey2" styleClass="select01">
        <html:optionsCollection name="man250Form" property="sortLabel" value="value" label="label" />
      </html:select>
      <html:radio name="man250Form" property="man250OrderKey2" styleId="sort2_up" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="man250Form" property="man250OrderKey2" styleId="sort2_dw" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

      </span>
    </td>
    </tr>
    </table>

    <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="center" width="100%">
    <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base1" onClick="buttonPush('man250search');">
    </td>

    <logic:notEmpty name="man250Form" property="man250DspList">
    <td align="right" width="0%">
    <input type="button" name="btn_exp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('man250export');">
    </td>
    </logic:notEmpty>
    </tr>
    </table>


    <logic:notEmpty name="man250Form" property="man250DspList">

    <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>

    <bean:size id="count1" name="man250Form" property="man250PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right" valign="top" nowrap>
    <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
    <logic:notEmpty name="man250Form" property="man250PageLabel">
      <html:select property="man250SltPage1" onchange="changePage1();" styleClass="text_i">
        <html:optionsCollection name="man250Form" property="man250PageLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
    </td>
    </tr>
    </table>
    </logic:greaterThan>

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

      <tr class="td_type3">
        <th width="0%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortLevel %>);"><span class="text_bb2"><gsmsg:write key="cmn.loglevel" /></span></a></th>
        <th width="0%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortDate %>);"><span class="text_bb2"><gsmsg:write key="man.run.time" /></span></a></th>
        <th width="0%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortPulugin %>);"><span class="text_bb2"><gsmsg:write key="cmn.plugin" /></span></a></th>
        <th width="0%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortUsrName %>);"><span class="text_bb2"><gsmsg:write key="man.run.user" /></span></a></th>
        <th width="15%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortPgName %>);"><span class="text_bb2"><gsmsg:write key="main.scr.feature.name" /></span></a></th>
        <th width="0%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortOpCode %>);"><span class="text_bb2"><gsmsg:write key="cmn.operations" /></span></a></th>
        <th width="85%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortValue %>);"><span class="text_bb2"><gsmsg:write key="cmn.content" /></span></a></th>
        <th width="0%"><a href="javascript:void(0)" onClick="clickSortTitle(<%= sortLogIp %>);"><span class="text_bb2"><gsmsg:write key="cmn.ipaddress" /></span></a></th>
      </tr>

      <logic:iterate id="dspMdl" name="man250Form" property="man250DspList" indexId="idx">
      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>

      <tr>
        <td class="<%= tdColor %>" nowrap align="left">
        <logic:equal name="dspMdl" property="logLevel" value="<%= logLevelError %>"><img alt="<gsmsg:write key="man.error" />" class="img_bottom" src="../main/images/icon_log_error.gif" border="0"></logic:equal>
        <logic:equal name="dspMdl" property="logLevel" value="<%= logLevelWarn %>"><img alt="<gsmsg:write key="cmn.warning" />" class="img_bottom" src="../main/images/icon_log_warn.gif" border="0"></logic:equal>
        <logic:equal name="dspMdl" property="logLevel" value="<%= logLevelInfo %>"><img alt="<gsmsg:write key="main.man240.2" />" class="img_bottom" src="../main/images/icon_log_info.gif" border="0"></logic:equal>
        <logic:equal name="dspMdl" property="logLevel" value="<%= logLevelTrace %>"><img alt="<gsmsg:write key="main.man240.3" />" class="img_bottom" src="../main/images/icon_log_trace.gif" border="0"></logic:equal>
        <bean:write name="dspMdl" property="logLevel" />
        </td>
        <td class="<%= tdColor %>" nowrap align="center"><bean:write name="dspMdl" property="logDate" /></td>
        <td class="<%= tdColor %>" nowrap align="left"><bean:write name="dspMdl" property="pluginName" /></td>
        <td class="<%= tdColor %>" nowrap align="left"><bean:write name="dspMdl" property="usrNameSei" />&nbsp;<bean:write name="dspMdl" property="usrNameMei" /></td>
        <td class="<%= tdColor %>" align="left"><bean:write name="dspMdl" property="pgName" filter="false" /></td>
        <td class="<%= tdColor %>" nowrap align="left"><bean:write name="dspMdl" property="opCode"  /></td>
        <td class="<%= tdColor %>" align="left"><bean:write name="dspMdl" property="value" filter="false"  /></td>
        <td class="<%= tdColor %>" nowrap align="left"><bean:write name="dspMdl" property="logIp"  /></td>
      </tr>

      </logic:iterate>

    </table>


    <bean:size id="count2" name="man250Form" property="man250PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right" valign="top" nowrap>
    <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
    <logic:notEmpty name="man250Form" property="man250PageLabel">
      <html:select property="man250SltPage2" onchange="changePage2();" styleClass="text_i">
      <html:optionsCollection name="man250Form" property="man250PageLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
    </td>
    </tr>
    </table>
    </logic:greaterThan>
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