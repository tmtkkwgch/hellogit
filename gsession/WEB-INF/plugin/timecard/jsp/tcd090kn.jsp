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
<html:form action="/timecard/tcd090kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<logic:notEmpty name="tcd090knForm" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd090knForm" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="tcd090LockFlg" />
<html:hidden property="tcd090LockStrike" />
<html:hidden property="tcd090LockBiko" />
<html:hidden property="tcd090LockLate" />
<html:hidden property="tcd090LockHoliday" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.tcd090kn.01" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('tcd090kn_submit');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('tcd090kn_back');">
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
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="10%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.20" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="90%" >

    <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
    <% String allUser = gsMsg.getMessage(request, "cmn.alluser"); %>
    <% String kanriUser = gsMsg.getMessage(request, "tcd.tcd090kn.11"); %>
    <logic:equal name="tcd090knForm" property="tcd090LockStrike" value="0">
        <gsmsg:write key="tcd.tcd090kn.07" arg0="<%= allUser %>" />
    </logic:equal>
    <logic:equal name="tcd090knForm" property="tcd090LockStrike" value="1">
        <gsmsg:write key="tcd.tcd090kn.07" arg0="<%= kanriUser %>" />
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.29" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <logic:equal name="tcd090knForm" property="tcd090LockFlg" value="0">
        <gsmsg:write key="tcd.tcd090kn.08" arg0="<%= allUser %>" />
    </logic:equal>
    <logic:equal name="tcd090knForm" property="tcd090LockFlg" value="1">
        <gsmsg:write key="tcd.tcd090kn.08" arg0="<%= kanriUser %>" />
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.25" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <logic:equal name="tcd090knForm" property="tcd090LockBiko" value="0">
      <gsmsg:write key="tcd.tcd090kn.051" />
    </logic:equal>
    <logic:equal name="tcd090knForm" property="tcd090LockBiko" value="1">
      <gsmsg:write key="tcd.tcd090kn.05" />
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.17" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <logic:equal name="tcd090knForm" property="tcd090LockLate" value="0">
        <gsmsg:write key="tcd.tcd090kn.06" arg0="<%= allUser %>" />
    </logic:equal>
    <logic:equal name="tcd090knForm" property="tcd090LockLate" value="1">
        <gsmsg:write key="tcd.tcd090kn.06" arg0="<%= kanriUser %>" />
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.39" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <logic:equal name="tcd090knForm" property="tcd090LockHoliday" value="0">
        <gsmsg:write key="tcd.tcd090kn.09" arg0="<%= allUser %>" />
    </logic:equal>
    <logic:equal name="tcd090knForm" property="tcd090LockHoliday" value="1">
        <gsmsg:write key="tcd.tcd090kn.09" arg0="<%= kanriUser %>" />
    </logic:equal>
    </td>
    </tr>

    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('tcd090kn_submit');">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd090kn_back');">
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