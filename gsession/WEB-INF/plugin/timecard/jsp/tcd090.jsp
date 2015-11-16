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
<title>[GroupSession] <gsmsg:write key="tcd.06" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/timecard/tcd090">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<logic:notEmpty name="tcd090Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd090Form" property="selectDay" scope="request">
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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.06" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd090_submit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd090_back');"></td>
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
      <span class="text_bb1"><gsmsg:write key="tcd.20" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd090.02" /></span><br>
      <html:radio styleId="tcd090LockStrike_0" name="tcd090Form" property="tcd090LockStrike" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockStrike_0"><gsmsg:write key="tcd.tcd090.03" /></label></span>&nbsp;
      <html:radio styleId="tcd090LockStrike_1" name="tcd090Form" property="tcd090LockStrike" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockStrike_1"><gsmsg:write key="tcd.tcd090.07" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.29" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd090.04" /></span><br>
      <html:radio styleId="tcd090LockFlg_0" name="tcd090Form" property="tcd090LockFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockFlg_0"><gsmsg:write key="tcd.tcd090.03" /></label></span>&nbsp;
      <html:radio styleId="tcd090LockFlg_1" name="tcd090Form" property="tcd090LockFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockFlg_1"><gsmsg:write key="tcd.tcd090.07" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.25" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <html:checkbox styleId="tcd090LockBiko" name="tcd090Form" property="tcd090LockBiko" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.BIKO_NECESSARY_FLG) %>"  />
      <span class="text_base"><label for="tcd090LockBiko"><gsmsg:write key="tcd.tcd090.01" /></label></span>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.17" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd090.05" /></span><br>
      <html:radio styleId="tcd090LockLate_0" name="tcd090Form" property="tcd090LockLate" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockLate_0"><gsmsg:write key="tcd.tcd090.03" /></label></span>&nbsp;
      <html:radio styleId="tcd090LockLate_1" name="tcd090Form" property="tcd090LockLate" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockLate_1"><gsmsg:write key="tcd.tcd090.07" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="tcd.39" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><gsmsg:write key="tcd.tcd090.06" /></span><br>
      <html:radio styleId="tcd090LockHoliday_0" name="tcd090Form" property="tcd090LockHoliday" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockHoliday_0"><gsmsg:write key="tcd.tcd090.03" /></label></span>&nbsp;
      <html:radio styleId="tcd090LockHoliday_1" name="tcd090Form" property="tcd090LockHoliday" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>" /><span class="text_base"><label for="tcd090LockHoliday_1"><gsmsg:write key="tcd.tcd090.07" /></label>&nbsp;</span>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('tcd090_submit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd090_back');">
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