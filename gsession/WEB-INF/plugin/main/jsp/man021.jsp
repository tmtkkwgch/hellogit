<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<gsmsg:define id="pageTitle" msgkey="main.man021.1" type="java.lang.String" />
<gsmsg:define id="confirmBtnValue" msgkey="user.59" type="java.lang.String" />
<logic:equal name="man021Form" property="man021CmdMode" value="2">
  <gsmsg:define id="pageTitle2" msgkey="main.man021.2" type="java.lang.String" />
  <gsmsg:define id="confirmBtnValue2" msgkey="schedule.43" type="java.lang.String" />
  <% pageTitle = pageTitle2.toString(); %>
  <% confirmBtnValue = confirmBtnValue2.toString(); %>
</logic:equal>


<html:html>
<head>
<title>[Group Session] <%= pageTitle %></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../main/js/man021.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/main/man021">
<input type="hidden" name="CMD" value="confirm">
<html:hidden property="man020DspYear" />
<html:hidden property="editHolDate" />
<html:hidden property="man021CmdMode" />
<html:hidden property="man021HolMonthOld" />
<html:hidden property="man021HolDayOld" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">

<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.screen.header" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man021.3" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="man021Form" property="man021CmdMode" value="1">
      <input type="submit" value="<%= confirmBtnValue %>" class="btn_add_n1">
      </logic:equal>
      <logic:equal name="man021Form" property="man021CmdMode" value="2">
      <input type="submit" value="<%= confirmBtnValue %>" class="btn_edit_n1">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush(2);">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- <gsmsg:write key="cmn.error.message" /> -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table width="100%" class="tl0 table_td_border" border="0" cellpadding="5">
    <tr class="table_bg_A5B4E1">
    <th align="center" width="30%"><gsmsg:write key="cmn.date2" /><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></th>
    <th align="center" width="70%"><gsmsg:write key="cmn.holiday.name" /><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></th>
    </tr>

    <tr>
    <th align="center" class="td_wt" width="20%">
    <html:select name="man021Form" property="man021HolMonth">
      <html:optionsCollection name="man021Form" property="man021MonthLabel" value="value" label="label" />
    </html:select>
    <html:select name="man021Form" property="man021HolDay">
      <html:optionsCollection name="man021Form" property="man021DayLabel" value="value" label="label" />
    </html:select>
    </th>
    <td class="td_wt" width="70%">
    <html:text name="man021Form" style="width:393px;" maxlength="20" property="man021HolName" />
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