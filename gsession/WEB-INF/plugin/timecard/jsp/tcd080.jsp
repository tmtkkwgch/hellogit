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
<title>[GroupSession] <gsmsg:write key="tcd.tcd080.13" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch060.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/timecard/tcd080">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<logic:notEmpty name="tcd080Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd080Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.preferences" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd080ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd080back');"></td>
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
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.time" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_bb1"><gsmsg:write key="cmn.starttime" /><gsmsg:write key="wml.215" /></span>
      <!-- 時 -->
      <html:select property="tcd080DefFrH">
        <html:optionsCollection name="tcd080Form" property="tcd080HourLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <!-- 分 -->
      <html:select property="tcd080DefFrM">
        <html:optionsCollection name="tcd080Form" property="tcd080MinuteLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    <br>
      <span class="text_bb1"><gsmsg:write key="cmn.endtime" /><gsmsg:write key="wml.215" /></span>
      <!-- 時 -->
      <html:select property="tcd080DefToH">
        <html:optionsCollection name="tcd080Form" property="tcd080HourLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <!-- 分 -->
      <html:select property="tcd080DefToM">
        <html:optionsCollection name="tcd080Form" property="tcd080MinuteLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.tcd080.11" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd080.06" /></span><br>
      <html:radio styleId="tcd080mainDsp_0" name="tcd080Form" property="tcd080mainDsp" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_DSP) %>" /><span class="text_base"><label for="tcd080mainDsp_0"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <html:radio styleId="tcd080mainDsp_1" name="tcd080Form" property="tcd080mainDsp" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>" /><span class="text_base"><label for="tcd080mainDsp_1"><gsmsg:write key="cmn.dont.show" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.zaiseki.management" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd080.12" /></span><br>
      <html:radio styleId="tcd080zaisekiSts_0" name="tcd080Form" property="tcd080zaisekiSts" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ZAISEKI_OFF) %>" /><span class="text_base"><label for="tcd080zaisekiSts_0"><gsmsg:write key="tcd.tcd080.02" /></label></span>&nbsp;
      <html:radio styleId="tcd080zaisekiSts_1" name="tcd080Form" property="tcd080zaisekiSts" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ZAISEKI_ON) %>" /><span class="text_base"><label for="tcd080zaisekiSts_1"><gsmsg:write key="tcd.tcd080.01" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.tcd080.08" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd080.09" /></span><br>
      <html:radio styleId="tcd080kinmuOutput_0" name="tcd080Form" property="tcd080kinmuOutput" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.KINMU_EXCEL) %>" /><span class="text_base"><label for="tcd080kinmuOutput_0"><gsmsg:write key="tcd.tcd080.15" /></label></span>&nbsp;
      <html:radio styleId="tcd080kinmuOutput_1" name="tcd080Form" property="tcd080kinmuOutput" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.KINMU_PDF) %>" /><span class="text_base"><label for="tcd080kinmuOutput_1"><gsmsg:write key="tcd.tcd080.14" /></label>&nbsp;</span>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd080ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd080back');">
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
</body>
</html:html>