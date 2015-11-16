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
<title>[GroupSession] <gsmsg:write key="tcd.146" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/timecard/tcd070">
<logic:notEmpty name="tcd070Form" property="editTimezoneSid">
  <input type="hidden" name="helpPrm" value="1">
</logic:notEmpty>
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<logic:notEmpty name="tcd070Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd070Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>
<html:hidden property="addTimezoneKbn" />
<html:hidden property="editTimezoneSid" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.146" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:notEmpty name="tcd070Form" property="editTimezoneSid">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('tcd070delete');">
      </logic:notEmpty>
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd070ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd070back');"></td>
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
      <span class="text_bb1"><gsmsg:write key="tcd.tcd070.03" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <html:select property="tcd070ZoneKbn">
        <html:optionsCollection name="tcd070Form" property="tcd070ZoneLabel" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.tcd070.01" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_bb1"><gsmsg:write key="cmn.start" /><gsmsg:write key="wml.215" /></span>
      <!-- 時 -->
      <html:select property="tcd070FrH">
        <html:optionsCollection name="tcd070Form" property="tcd070HourLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <!-- 分 -->
      <html:select property="tcd070FrM">
        <html:optionsCollection name="tcd070Form" property="tcd070MinuteLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
      <span class="text_bb1"><gsmsg:write key="cmn.end" /><gsmsg:write key="wml.215" /></span>
      <!-- 時 -->
      <html:select property="tcd070ToH">
        <html:optionsCollection name="tcd070Form" property="tcd070HourLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <!-- 分 -->
      <html:select property="tcd070ToM">
        <html:optionsCollection name="tcd070Form" property="tcd070MinuteLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <logic:notEmpty name="tcd070Form" property="editTimezoneSid">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('tcd070delete');">
      </logic:notEmpty>
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd070ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd070back');">
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