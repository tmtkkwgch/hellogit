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
<html:form action="/timecard/tcd050">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />

<html:hidden property="tcd050initFlg" />

<logic:notEmpty name="tcd050Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd050Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="tcd050DspHolidayYear" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.tcd050.07" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd050_submit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd050_back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.11" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
    <html:select property="tcd050BetweenSlt">
      <html:optionsCollection name="tcd050Form" property="tcd050BetweenLabel" value="value" label="label" />
    </html:select>
    <span class="text_base"><gsmsg:write key="tcd.tcd050.05" /></span>
    <span class="text_base"><gsmsg:write key="wml.215" /></span>
    <span class="text_base"><gsmsg:write key="tcd.tcd050.06" /></span>
    <html:select property="tcd050SinsuSlt">
      <html:optionsCollection name="tcd050Form" property="tcd050SinsuLabel" value="value" label="label" />
    </html:select>
    <br><br>
    <span class="text_base"><gsmsg:write key="tcd.54" /> </span><br>
    <span class="text_base"><gsmsg:write key="tcd.53" /> </span><br>
    <span class="text_r1"><gsmsg:write key="tcd.tcd050.10" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd050.02" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <html:select property="tcd050LimitDaySlt">
      <html:optionsCollection name="tcd050Form" property="tcd050LimitDayLabel" value="value" label="label" />
    </html:select>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.37" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <span class="text_r1"><gsmsg:write key="tcd.tcd050.09" /></span>
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
      <th class="td_type9"><html:multibox property="tcd050SelectWeek" value="1"/></th>
      <th class="td_type1"><html:multibox property="tcd050SelectWeek" value="2"/></th>
      <th class="td_type1"><html:multibox property="tcd050SelectWeek" value="3"/></th>
      <th class="td_type1"><html:multibox property="tcd050SelectWeek" value="4"/></th>
      <th class="td_type1"><html:multibox property="tcd050SelectWeek" value="5"/></th>
      <th class="td_type1"><html:multibox property="tcd050SelectWeek" value="6"/></th>
      <th class="td_type8"><html:multibox property="tcd050SelectWeek" value="7"/></th>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd050kn.04" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_r1"><gsmsg:write key="tcd.tcd050.08" /></span>
    <div align="right" style="width:80%; ">
    <input type="image" name="zday" src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.changes.previous.year" />" style="width:20px;height:20px;vertical-align:bottom;" onClick="buttonPush('moveLeft');">
    <bean:define id="yr" name="tcd050Form" property="tcd050DspHolidayYear" type="java.lang.String" />
    <span class="text_base3"><gsmsg:write key="cmn.year" arg0="<%= yr %>" /></span>
    <input type="image" name="yday" src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.change.nextyear" />" style="width:20px;height:20px;vertical-align:bottom;" onClick="buttonPush('moveRight');">
    </div>
      <table cellpadding="5" cellspacing="0" class="tl_u2 table_td_border" width="80%">
      <tr class="table_bg_7D91BD">
      <th width="0%">&nbsp;</th>
      <th width="30%"><span class="text_tlw"><gsmsg:write key="cmn.date2" /></span></th>
      <th width="70%"><span class="text_tlw"><gsmsg:write key="cmn.holiday.name" /></span></th>
      </tr>

      <logic:notEmpty name="tcd050Form" property="tcd050HolidayInfoList" scope="request">
      <% String[] trColors = new String[] {"smail_td1", "smail_td2"}; %>
      <bean:define id="trColor" value="" />
      <logic:iterate id="holMdl" name="tcd050Form" property="tcd050HolidayInfoList" scope="request" indexId="idx">
      <% trColor = trColors[(idx.intValue() % 2)]; %>

      <tr class="<%= trColor %>">
      <th>
      <html:multibox name="tcd050Form" property="tcd050SelectHoliDay">
       <bean:write name="holMdl" property="strDate" />
      </html:multibox>
      </th>
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

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd050_submit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd050_back');">
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