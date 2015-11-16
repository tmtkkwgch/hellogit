<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="tcd.tcd050.04" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/timecard/tcd050kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<logic:notEmpty name="tcd050knForm" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd050knForm" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="tcd050initFlg" />
<html:hidden property="tcd050BetweenSlt" />
<html:hidden property="tcd050SinsuSlt" />
<html:hidden property="tcd050LimitDaySlt" />
<html:hidden property="tcd050DspHolidayYear" />
<logic:notEmpty name="tcd050knForm" property="tcd050SelectWeek" scope="request">
<logic:iterate id="selectWeek" name="tcd050knForm" property="tcd050SelectWeek" scope="request">
  <input type="hidden" name="tcd050SelectWeek" value="<bean:write name="selectWeek" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="tcd050knForm" property="tcd050SelectHoliDay" scope="request">
<logic:iterate id="selectHol" name="tcd050knForm" property="tcd050SelectHoliDay" scope="request">
  <input type="hidden" name="tcd050SelectHoliDay" value="<bean:write name="selectHol" />">
</logic:iterate>
</logic:notEmpty>
<logic:empty name="tcd050knForm" property="tcd050SelectHoliDay" scope="request">
<input type="hidden" name="tcd050SelectHoliDay" value="">
</logic:empty>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td align="center">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.tcd050.07" />(<gsmsg:write key="cmn.check" />) ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('tcd050kn_submit');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('tcd050kn_back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>
    <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td align="left">
    <span class="text_r1"><gsmsg:write key="tcd.45" /></span>
    </td>
    </tr>
    </table>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.11" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
    <span class="text_base4">
    <bean:write name="tcd050knForm" property="tcd050BetweenSlt" /><gsmsg:write key="cmn.minute" />
    </span>
    <span class="text_base"><gsmsg:write key="tcd.tcd050.05" /></span>
    <span class="text_base"><gsmsg:write key="wml.215" /></span>
    <span class="text_base"><gsmsg:write key="tcd.tcd050.06" /></span>
    <span class="text_base4">
    <bean:write name="tcd050knForm" property="tcd050SinsuSlt" /><gsmsg:write key="tcd.tcd050kn.03" />
    </span>
    <br><br>
    <span class="text_base"><gsmsg:write key="tcd.54" /> </span><br>
    <span class="text_base"><gsmsg:write key="tcd.53" /> </span><br>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd050.02" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <span class="text_base4">
    <logic:equal name="tcd050knForm" property="tcd050LimitDaySlt" value="99">
    <gsmsg:write key="tcd.tcd050kn.01" />
    </logic:equal>
    <logic:notEqual name="tcd050knForm" property="tcd050LimitDaySlt" value="99">
    <bean:write name="tcd050knForm" property="tcd050LimitDaySlt" /><gsmsg:write key="cmn.day" />
    </logic:notEqual>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.37" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <span class="text_base"><gsmsg:write key="tcd.tcd050kn.07" /></span>
      <table class="tl_u2">
      <tr>
      <th class="td_type9"><span color="#ff0000"><gsmsg:write key="cmn.day" /></span></th>
      <th class="td_type1"><gsmsg:write key="cmn.Monday" /></th>
      <th class="td_type1"><gsmsg:write key="cmn.tuesday" /></th>
      <th class="td_type1"><gsmsg:write key="cmn.wednesday" /></th>
      <th class="td_type1"><gsmsg:write key="cmn.thursday" /></th>
      <th class="td_type1"><gsmsg:write key="cmn.friday" /></th>
      <th class="td_type8"><span color="#0000ff"><gsmsg:write key="cmn.saturday" /></span></th>
      </tr>
      <tr>
      <th class="td_type9">
      <logic:equal name="tcd050knForm" property="weekSun" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      <th class="td_type1">
      <logic:equal name="tcd050knForm" property="weekMon" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      <th class="td_type1">
      <logic:equal name="tcd050knForm" property="weekTue" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      <th class="td_type1">
      <logic:equal name="tcd050knForm" property="weekWed" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      <th class="td_type1">
      <logic:equal name="tcd050knForm" property="weekThu" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      <th class="td_type1">
      <logic:equal name="tcd050knForm" property="weekFri" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      <th class="td_type8">
      <logic:equal name="tcd050knForm" property="weekSat" value="1">
      <img src="../timecard/images/locked.gif" alt="<gsmsg:write key="tcd.tcd050kn.06" />" width="20" height="20">
      </logic:equal>
      </th>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd050kn.04" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base"><gsmsg:write key="tcd.tcd050kn.08" /> </span>
    <div align="right" style="width:80%; ">
    <bean:define id="yr" name="tcd050knForm" property="tcd050DspHolidayYear" type="java.lang.String" />
    <span class="text_base3"><gsmsg:write key="cmn.year" arg0="<%= yr %>" /></span>
    </div>
      <table cellpadding="5" cellspacing="0" class="tl_u2 table_td_border" width="80%">
      <tr class="table_bg_7D91BD">
      <th width="30%"><span class="text_tlw"><gsmsg:write key="cmn.date2" /></span></th>
      <th width="70%"><span class="text_tlw"><gsmsg:write key="cmn.holiday.name" /></span></th>
      </tr>
      <logic:notEmpty name="tcd050knForm" property="tcd050HolidayInfoList" scope="request">
      <% String[] trColors = new String[] {"smail_td1", "smail_td2"}; %>
      <bean:define id="trColor" value="" />
      <logic:iterate id="holMdl" name="tcd050knForm" property="tcd050HolidayInfoList" scope="request" indexId="idx">
      <% trColor = trColors[(idx.intValue() % 2)]; %>
      <tr class="<%= trColor %>">
      <th><bean:write name="holMdl" property="viewDate" /></th>
      <th><bean:write name="holMdl" property="holName" /></th>
      </tr>
      </logic:iterate>
      </logic:notEmpty>
      </table>
    </td>
    </tr>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('tcd050kn_submit');">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd050kn_back');">
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