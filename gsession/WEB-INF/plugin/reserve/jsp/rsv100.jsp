<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int sort_name = jp.groupsession.v2.rsv.GSConstReserve.RSV_SORT_NAME; %>
<% int sort_from = jp.groupsession.v2.rsv.GSConstReserve.RSV_SORT_FROM; %>
<% int sort_to = jp.groupsession.v2.rsv.GSConstReserve.RSV_SORT_TO; %>
<% int sort_content = jp.groupsession.v2.rsv.GSConstReserve.RSV_SORT_CONTENT; %>
<% int sort_sisetu = jp.groupsession.v2.rsv.GSConstReserve.RSV_SORT_SISETU; %>
<% int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC; %>
<% int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC; %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />[<gsmsg:write key="reserve.rsv100.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../reserve/js/rsv100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/reservepopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onkeydown="return keyPress(event.keyCode);" onload="rsv100ChgDateKbn();" onunload="calWindowClose();windowClose();">
<html:form action="/reserve/rsv100">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsv100svDateKbn" />
<html:hidden property="rsv100svFromYear" />
<html:hidden property="rsv100svFromMonth" />
<html:hidden property="rsv100svFromDay" />
<html:hidden property="rsv100svToYear" />
<html:hidden property="rsv100svToMonth" />
<html:hidden property="rsv100svToDay" />
<html:hidden property="rsv100svGrp1" />
<html:hidden property="rsv100svGrp2" />
<html:hidden property="rsv100InitFlg" />
<html:hidden property="rsv100SearchFlg" />
<html:hidden property="rsv100SortKey" />
<html:hidden property="rsv100OrderKey" />

<html:hidden property="rsv100svKeyWord" />
<html:hidden property="rsv100svSearchCondition" />
<html:hidden property="rsv100svTargetMok" />
<html:hidden property="rsv100svTargetNiyo" />
<html:hidden property="rsv100svApprStatus" />
<html:hidden property="rsv100svSelectedKey1" />
<html:hidden property="rsv100svSelectedKey2" />
<html:hidden property="rsv100svSelectedKey1Sort" />
<html:hidden property="rsv100svSelectedKey2Sort" />
<html:hidden property="rsv100SearchSvFlg" />
<html:hidden property="rsv100svSelectSisKbn" />

<logic:notEmpty name="rsv100Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv100Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv100Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv100Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="iorderKey" name="rsv100Form" property="rsv100OrderKey" />
<bean:define id="isortKbn" name="rsv100Form" property="rsv100SortKey" />
<% int iOrderKey = ((Integer) iorderKey).intValue(); %>
<% int iSortKbn = ((Integer) isortKbn).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">
  <table width="100%" class="tl0">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="reserve.rsv100.1" /> ]</td>
    <td width="100%" class="header_white_bg">
      <logic:notEmpty name="rsv100Form" property="rsv100resultList">
        <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
      </logic:notEmpty>
      <logic:equal name="rsv100Form" property="rsvGroupEditFlg" value="true">
        <input type="button" name="btn_sisetu_settei" class="btn_base_rsv2" value="<gsmsg:write key="reserve.settings" />" onclick="buttonPush('sisetu_settei');">
      </logic:equal>
      <logic:equal name="rsv100Form" property="rsvAdmFlg" value="true">
        <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onclick="buttonPush('kanrisya_settei');">
      </logic:equal>
      <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onclick="buttonPush('kozin_settei');">
    </td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td colspan="8" class="td_type0">
    <table width="100%" class="tl0" border="0">
    <tr>
    <td width="20%" align="left">
      <table border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td nowrap><img src="../common/images/spacer.gif" width="250px" height="28px" border="0"></td>
      </tr>
      </table>
    </td>
    <td width="40%" align="right">&nbsp;</td>
    <td width="40%" align="center" nowrap>
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onclick="buttonPush('nikkan');">
      <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onclick="buttonPush('syukan');">
      <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search">
    </td>
    <td width="0%" align="right" nowrap><img src="../common/images/spacer.gif" width="183px" height="28px" border="0"></td>
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
  <td width="100%">
    <table class="tl0" width="100%" cellpadding="5" cellspacing="0">
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
    <td align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="cmn.period" /></span></td>
    <td class="td_type20" colspan="3" nowrap valigh="middle">
      <span class="text_base2"><html:checkbox name="rsv100Form" styleId="srhDateKbn" property="rsv100dateKbn" value="1" onclick="rsv100ChgDateKbn();" /><label for="srhDateKbn"><gsmsg:write key="cmn.without.specifying" /></label></span>
      &nbsp;
      <html:select property="rsv100selectedFromYear" styleId="fromYear">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListYear" value="value" label="label" />
      </html:select>
      <html:select property="rsv100selectedFromMonth" styleId="fromMonth">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListMonth" value="value" label="label" />
      </html:select>
      <html:select property="rsv100selectedFromDay" styleId="fromDay">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListDay" value="value" label="label" />
      </html:select>
      <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.fromDay, this.form.fromMonth, this.form.fromYear, 'rsv100_1')" class="calendar_btn", id="rsv100_1" name="rsv100FromCalBtn">

      <span id="rsv100DateFromBtnAreaOn">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 3)">
      </span>
      <span id="rsv100DateFromBtnAreaOff">
      <img class="bottom" src="../reserve/images/arrow2_l.gif" border="0" alt="<gsmsg:write key="cmn.previous.day" />">
      <img class="bottom" src="../reserve/images/today_btn.gif" border="0" alt="<gsmsg:write key="cmn.today" />">
      <img class="bottom" src="../reserve/images/arrow2_r.gif" border="0" alt="<gsmsg:write key="cmn.nextday" />">
      </span>
      ～
      <html:select property="rsv100selectedToYear" styleId="toYear">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListYear" value="value" label="label" />
      </html:select>
      <html:select property="rsv100selectedToMonth" styleId="toMonth">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListMonth" value="value" label="label" />
      </html:select>
      <html:select property="rsv100selectedToDay" styleId="toDay">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListDay" value="value" label="label" />
      </html:select>
      <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.toDay, this.form.toMonth, this.form.toYear, 'rsv100_2')" class="calendar_btn", id="rsv100_2" name="rsv100ToCalBtn">
      <span id="rsv100DateToBtnAreaOn">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 3)">
      </span>
      <span id="rsv100DateToBtnAreaOff">
      <img class="bottom" src="../reserve/images/arrow2_l.gif" border="0" alt="<gsmsg:write key="cmn.previous.day" />">
      <img class="bottom" src="../reserve/images/today_btn.gif" border="0" alt="<gsmsg:write key="cmn.today" />">
      <img class="bottom" src="../reserve/images/arrow2_r.gif" border="0" alt="<gsmsg:write key="cmn.nextday" />">
      </span>
    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray" width="10%"><span class="text_bb2"><gsmsg:write key="cmn.keyword" /></span></td>
    <td class="td_type20" width="45%">
      <html:text name="rsv100Form" property="rsv100KeyWord" maxlength="50" style="width:333px;" />
      <div class="text_base_rsv">
        <html:radio styleId="lvl1" name="rsv100Form" property="rsv100SearchCondition" value="0" /><label for="lvl1"><gsmsg:write key="cmn.contains.all" />(AND)</label>&nbsp;
        <html:radio styleId="lvl2" name="rsv100Form" property="rsv100SearchCondition" value="1" /><label for="lvl2"><gsmsg:write key="cmn.containing.either" />(OR)</label>
      </div>
    </td>

    <td align="center" class="td_gray" width="10%"><span class="text_bb2"><gsmsg:write key="cmn.search2" /></span></td>
    <td class="td_type20" width="35%">
      <div class="text_base_rsv">
        <html:checkbox styleId="mok" name="rsv100Form" property="rsv100TargetMok" value="1" /><label for="mok"><gsmsg:write key="reserve.72" /></label>&nbsp;
        <html:checkbox styleId="niyo" name="rsv100Form" property="rsv100TargetNiyo" value="1" /><label for="niyo"><gsmsg:write key="cmn.content" /></label>
      </div>
    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="cmn.group" /></span></td>
    <td class="td_type20">
      <table>
      <tr>
      <td class="text_bb5"><gsmsg:write key="cmn.group" />&nbsp;</td>
      <td>
      <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeGroupCombo()">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListGrp1" value="value" label="label" />
      </html:select>
      </td>
      </tr>
      <tr>
      <td class="text_bb5"><gsmsg:write key="cmn.facility" /></td>
      <td>
      <html:select property="rsvSelectedSisetuSid" styleClass="select01">
        <html:optionsCollection name="rsv100Form" property="rsv100LabelListGrp2" value="value" label="label" />
      </html:select>
      </td>
      </tr>
      </table>
    </td>

    <td align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="cmn.appr.status" /></span></td>
    <td class="td_type20">
      <div class="text_base_rsv">
        <html:radio styleId="apprStatus1" name="rsv100Form" property="rsv100apprStatus" value="0" /><label for="apprStatus1"><gsmsg:write key="reserve.rsv100.appr.status1" /></label>
        &nbsp;<html:radio styleId="apprStatus2" name="rsv100Form" property="rsv100apprStatus" value="1" /><label for="apprStatus2"><gsmsg:write key="reserve.rsv100.appr.status2" /></label>
        &nbsp;<html:radio styleId="apprStatus3" name="rsv100Form" property="rsv100apprStatus" value="2" /><label for="apprStatus3"><gsmsg:write key="reserve.rsv100.appr.status3" /></label>
        <br><html:radio styleId="apprStatus4" name="rsv100Form" property="rsv100apprStatus" value="3" /><label for="apprStatus4"><gsmsg:write key="reserve.rsv100.appr.status4" /></label>
      </div>
    </td>
    </tr>

    <tr>
    <td align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="cmn.sort.order" /></span></td>
    <td class="td_type20" colspan="3">
      <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
      <html:select property="rsv100SelectedKey1" styleClass="select01">
        <html:optionsCollection name="rsv100Form" property="rsv100KeyList" value="value" label="label" />
      </html:select>
      <span class="text_base_rsv">
        <html:radio styleId="sort1and" name="rsv100Form" property="rsv100SelectedKey1Sort" value="0" /><label for="sort1and"><gsmsg:write key="cmn.order.asc" /></label>&nbsp;
        <html:radio styleId="sort1or" name="rsv100Form" property="rsv100SelectedKey1Sort" value="1" /><label for="sort1or"><gsmsg:write key="cmn.order.desc" /></label>
      </span>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
      <html:select property="rsv100SelectedKey2" styleClass="select01">
        <html:optionsCollection name="rsv100Form" property="rsv100KeyList" value="value" label="label" />
      </html:select>
      <span class="text_base_rsv">
        <html:radio styleId="sort2and" name="rsv100Form" property="rsv100SelectedKey2Sort" value="0" /><label for="sort2and"><gsmsg:write key="cmn.order.asc" /></label>&nbsp;
        <html:radio styleId="sort2or" name="rsv100Form" property="rsv100SelectedKey2Sort" value="1" /><label for="sort2or"><gsmsg:write key="cmn.order.desc" /></label>
      </span>
    </td>
    </tr>

    <tr>
    <td colspan="4" align="center">
      <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="center" width="100%">
        <input type="button" name="btn_usrimp" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onclick="buttonPush('kensaku');">
      </td>
      </tr>
      </table>
    </td>
    </tr>


    <% String headName="";
           jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(request);
           String msgTanto = gsMsg.getMessage("reserve.use.name.1");
           String msgUser = gsMsg.getMessage("reserve.use.name.2");
    %>

    <logic:equal name="rsv100Form" property="rsv100svSelectSisKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <% headName= msgTanto; %>
    </logic:equal>
    <logic:equal name="rsv100Form" property="rsv100svSelectSisKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <% headName= msgUser; %>
    </logic:equal>

    <bean:define id="searchFlg" name="rsv100Form" property="rsv100SearchFlg" />
    <logic:equal name="searchFlg" value="true">
    <tr>
    <td align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="reserve.output.item" /></span></td>
    <td class="td_type20" colspan="4">
      <table width="100%">
      <tr>
      <td>
      <div class="text_base_rsv">
        <span style="white-space: nowrap"><html:multibox styleId="roomId" name="rsv100Form" property="rsv100CsvOutField" value="1" /><label for="roomId"><gsmsg:write key="reserve.55" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="room" name="rsv100Form" property="rsv100CsvOutField" value="2" /><label for="room"><gsmsg:write key="cmn.facility" /></label></span><wbr>
        <logic:equal name="rsv100Form" property="rsvAdmFlg" value="true">
          <span style="white-space: nowrap"><html:multibox styleId="userId" name="rsv100Form" property="rsv100CsvOutField" value="3" /><label for="userId"><gsmsg:write key="cmn.user.id" /></label></span><wbr>
        </logic:equal>
        <span style="white-space: nowrap"><html:multibox styleId="user" name="rsv100Form" property="rsv100CsvOutField" value="4" /><label for="user"><gsmsg:write key="reserve.73" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="useMok" name="rsv100Form" property="rsv100CsvOutField" value="5" /><label for="useMok"><gsmsg:write key="reserve.72" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="dateFr" name="rsv100Form" property="rsv100CsvOutField" value="6" /><label for="dateFr"><gsmsg:write key="reserve.rsv100.14" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="timeFr" name="rsv100Form" property="rsv100CsvOutField" value="7" /><label for="timeFr"><gsmsg:write key="cmn.starttime" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="dateTo" name="rsv100Form" property="rsv100CsvOutField" value="8" /><label for="dateTo"><gsmsg:write key="reserve.rsv100.15" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="timeTo" name="rsv100Form" property="rsv100CsvOutField" value="9" /><label for="timeTo"><gsmsg:write key="cmn.endtime" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="biko" name="rsv100Form" property="rsv100CsvOutField" value="10" /><label for="biko"><gsmsg:write key="cmn.content" /></label></span><wbr>
        <span style="white-space: nowrap"><html:multibox styleId="edit" name="rsv100Form" property="rsv100CsvOutField" value="11" /><label for="edit"><gsmsg:write key="cmn.edit.permissions" /></label></span><wbr>

        <span class="csvOutFieldHeya" style="white-space: nowrap"><html:multibox styleId="usekbn" name="rsv100Form" property="rsv100CsvOutField" value="12" /><label for="usekbn"><gsmsg:write key="reserve.use.kbn" /></label></span><wbr>
        <span class="csvOutFieldHeyaCar" style="white-space: nowrap"><html:multibox styleId="contact" name="rsv100Form" property="rsv100CsvOutField" value="13" /><label for="contact"><gsmsg:write key="reserve.contact" /></label></span><wbr>
        <span class="csvOutFieldHeya" style="white-space: nowrap"><html:multibox styleId="guide" name="rsv100Form" property="rsv100CsvOutField" value="14" /><label for="guide"><gsmsg:write key="reserve.guide" /></label></span><wbr>
        <span class="csvOutFieldHeya" style="white-space: nowrap"><html:multibox styleId="parknum" name="rsv100Form" property="rsv100CsvOutField" value="15" /><label for="parknum"><gsmsg:write key="reserve.park.num" /></label></span><wbr>
        <span class="csvOutFieldCar" style="white-space: nowrap"><html:multibox styleId="dest" name="rsv100Form" property="rsv100CsvOutField" value="16" /><label for="dest"><gsmsg:write key="reserve.dest" /></label></span><wbr>
        <span class="csvOutFieldHeyaCar" style="white-space: nowrap"><html:multibox styleId="busyo" name="rsv100Form" property="rsv100CsvOutField" value="17" /><label for="busyo"><gsmsg:write key="reserve.busyo" /></label></span><wbr>
        <span class="csvOutFieldHeyaCar" style="white-space: nowrap"><html:multibox styleId="usename" name="rsv100Form" property="rsv100CsvOutField" value="18" /><label for="usename"><%= headName %></label></span><wbr>
        <span class="csvOutFieldHeyaCar" style="white-space: nowrap"><html:multibox styleId="usenum" name="rsv100Form" property="rsv100CsvOutField" value="19" /><label for="usenum"><gsmsg:write key="reserve.use.num" /></label></span><wbr>


      </div>
      </td>
      <td>
        <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onclick="buttonPush('export');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:equal>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="">
  </td>
  </tr>

  <logic:notEmpty name="rsv100Form" property="rsv100resultList">
    <bean:size id="count1" name="rsv100Form" property="rsv100PageList" scope="request" />
    <logic:greaterThan name="count1" value="1">
      <tr>
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('pageleft');">
        <logic:notEmpty name="rsv100Form" property="rsv100PageList">
          <html:select property="rsv100PageTop" onchange="changePage(0);" styleClass="text_i">
            <html:optionsCollection name="rsv100Form" property="rsv100PageList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
        <logic:empty name="rsv100Form" property="rsv100PageList">
          <html:select property="rsv100PageTop" styleClass="text_i">
            <option value="1" class="text_i">1 / 1</option>
          </html:select>
        </logic:empty>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');">
      </td>
      </tr>
    </logic:greaterThan>
  </logic:notEmpty>

  <logic:notEmpty name="rsv100Form" property="rsv100resultList">
  <% jp.groupsession.v2.rsv.biz.RsvCommonBiz rsvCmnBiz = new jp.groupsession.v2.rsv.biz.RsvCommonBiz(); %>
  <tr>
  <td class="td_type_tab">
    <table class="tl0" width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <th width="14%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return selectChange(1);" class="text_tlw"><gsmsg:write key="reserve.73" /></a></th>
    <th width="14%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return selectChange(2);" class="text_tlw"><gsmsg:write key="cmn.facility" /></a></th>
    <th width="16%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return selectChange(3);" class="text_tlw"><gsmsg:write key="reserve.rsv100.14" /></a></th>
    <th width="16%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return selectChange(4);" class="text_tlw"><gsmsg:write key="reserve.rsv100.15" /></a></th>
    <th width="40%" class="table_bg_7D91BD"><a href="javascript:void(0);" onClick="return selectChange(5);" class="text_tlw"><gsmsg:write key="reserve.72" /></a></th>
    </tr>

    <bean:define id="mod" value="0" />
      <logic:iterate id="list" name="rsv100Form" property="rsv100resultList" indexId="idx" scope="request">
        <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>
        <tr>
        <td class="<bean:write name="tblColor" />" align="left" valign="middle" nowrap><bean:write name="list" property="rsySeiMei"/></td>
        <td class="<bean:write name="tblColor" />" align="left" valign="middle"><bean:write name="list" property="rsySisetu"/></td>
        <td class="<bean:write name="tblColor" />" align="center" valign="middle"><bean:write name="list" property="rsyFrom"/></td>
        <td class="<bean:write name="tblColor" />" align="center" valign="middle"><bean:write name="list" property="rsyTo"/></td>
        <td class="<bean:write name="tblColor" />" align="left" valign="middle">
          <bean:define id="rsvSisApprStatus" name="list" property="rsyApprStatus" type="java.lang.Integer" />
          <bean:define id="rsvSisApprKbn" name="list" property="rsyApprKbn" type="java.lang.Integer" />
          <% String[] mokApprStatus = rsvCmnBiz.getMokApprStatus(request.getLocale(), rsvSisApprStatus.intValue(), rsvSisApprKbn.intValue()); %>
          <a href="#" class="<%= mokApprStatus[3] %> rsv_yoyaku_link" id="<bean:write name="list" property="rsySisetuSid"/>"><%= mokApprStatus[2] %><bean:write name="list" property="rsyContent"/></a>
        </td>
        </tr>
      </logic:iterate>
    </logic:notEmpty>
    </table>
  </td>
  </tr>

  <logic:notEmpty name="rsv100Form" property="rsv100resultList">
    <bean:size id="count1" name="rsv100Form" property="rsv100PageList" scope="request" />
    <logic:greaterThan name="count1" value="1">
      <tr>
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('pageleft');">
        <logic:notEmpty name="rsv100Form" property="rsv100PageList">
          <html:select property="rsv100PageBottom" onchange="changePage(1);" styleClass="text_i">
            <html:optionsCollection name="rsv100Form" property="rsv100PageList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
        <logic:empty name="rsv100Form" property="rsv100PageList">
          <html:select property="rsv100PageBottom" styleClass="text_i">
            <option value="1" class="text_i">1 / 1</option>
          </html:select>
        </logic:empty>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');">
      </td>
      </tr>
    </logic:greaterThan>
  </logic:notEmpty>
  </table>

</td>
</tr>
</table>


<div id="reservePop" title="" style="display:none">
  <div id="rsvCreateArea" width="100%" height="100%"></div>
</div>


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>