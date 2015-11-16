<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
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
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng050.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String onloadEvent = ""; %>
<logic:equal name="rng050Form" property="rngWebSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
  <% onloadEvent = " onload=\"hideTooltip();\""; %>
</logic:equal>

<title>[Group Session] <gsmsg:write key="rng.49" /></title>
</head>

<body class="body_03" onunload="calWindowClose();"<%= onloadEvent %>>

<html:form action="ringi/rng050">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="rngSid" value="">
<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010orderKey" />

<html:hidden property="rngAdminKeyword" />
<html:hidden property="rngAdminGroupSid" />
<html:hidden property="rngAdminUserSid" />
<html:hidden property="rngAdminApplYearFr" />
<html:hidden property="rngAdminApplMonthFr" />
<html:hidden property="rngAdminApplDayFr" />
<html:hidden property="rngAdminApplYearTo" />
<html:hidden property="rngAdminApplMonthTo" />
<html:hidden property="rngAdminApplDayTo" />
<html:hidden property="rngAdminLastManageYearFr" />
<html:hidden property="rngAdminLastManageMonthFr" />
<html:hidden property="rngAdminLastManageDayFr" />
<html:hidden property="rngAdminLastManageYearTo" />
<html:hidden property="rngAdminLastManageMonthTo" />
<html:hidden property="rngAdminLastManageDayTo" />

<html:hidden property="rngAdminSearchFlg" />
<html:hidden property="rngAdminSortKey" />
<html:hidden property="rngAdminOrderKey" />

<html:hidden property="rngProcMode" />
<input type="hidden" name="rngApprMode" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_DISCUSSING) %>">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" class="tl0">
<tr>
<td colspan="3">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.49" /> ]</td>
      <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td width="50%"></td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="rng.49" />"></td>
      <td class="header_glay_bg" width="100%">
        <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('rng040')"></td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="rng.49" />"></td>
    </tr>
  </table>

</td>
</tr>

<logic:messagesPresent message="false">
<tr><td colspan="3" align="left"><html:errors/></td></tr>
</logic:messagesPresent>

<tr>
<td nowrap>
  <table width="100%">

  <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="cmn.keyword" /><gsmsg:write key="wml.215" /></span>
    </td>

    <td width="95%" valign="top" align="left" nowrap>
      <html:text name="rng050Form" property="rngInputKeyword" style="width:393px;" maxlength="100" />
    </td>
  </tr>

  <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="cmn.group" />　<gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:select property="sltGroupSid" styleClass="select01" onchange="changeGroupCombo();">
        <html:optionsCollection name="rng050Form" property="rng050groupList" value="value" label="label" />
      </html:select>
    <input type="button" onclick="openGroupWindow(this.form.sltGroupSid, 'sltGroupSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="rng050GroupBtn">
    </td>
  </tr>

  <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="rng.47" />　<gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:select property="sltUserSid" styleClass="select01">
        <html:optionsCollection name="rng050Form" property="rng050userList" value="value" label="label" />
      </html:select>
    </td>
  </tr>

  <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="rng.application.date" />　<gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:select property="sltApplYearFr" styleId="applYearFr">
         <html:optionsCollection name="rng050Form" property="rng050YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplMonthFr" styleId="applMonthFr">
         <html:optionsCollection name="rng050Form" property="rng050MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplDayFr" styleId="applDayFr">
         <html:optionsCollection name="rng050Form" property="rng050DayList" value="value" label="label" />
      </html:select>
      <input type="button" value="Cal" onclick="wrtCalendar(this.form.applDayFr, this.form.applMonthFr, this.form.applYearFr)" class="calendar_btn">

      &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

      <html:select property="sltApplYearTo" styleId="applYearTo">
      <html:optionsCollection name="rng050Form" property="rng050YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplMonthTo" styleId="applMonthTo">
         <html:optionsCollection name="rng050Form" property="rng050MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltApplDayTo" styleId="applDayTo">
         <html:optionsCollection name="rng050Form" property="rng050DayList" value="value" label="label" />
      </html:select>
      <input type="button" value="Cal" onclick="wrtCalendar(this.form.applDayTo, this.form.applMonthTo, this.form.applYearTo)" class="calendar_btn">
    </td>
  </tr>

  <tr>
    <td valign="top" nowrap width="5%">
      <span class="text_bb1"><gsmsg:write key="rng.105" />　<gsmsg:write key="wml.215" /></span>
    </td>
    <td width="95%" valign="top" align="left" nowrap>
      <html:select property="sltLastManageYearFr" styleId="lastManageYearFr">
        <html:optionsCollection name="rng050Form" property="rng050YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageMonthFr" styleId="lastManageMonthFr">
        <html:optionsCollection name="rng050Form" property="rng050MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageDayFr" styleId="lastManageDayFr">
        <html:optionsCollection name="rng050Form" property="rng050DayList" value="value" label="label" />
      </html:select>
      <input type="button" value="Cal" onclick="wrtCalendar(this.form.lastManageDayFr, this.form.lastManageMonthFr, this.form.lastManageYearFr)" class="calendar_btn">

      &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

      <html:select property="sltLastManageYearTo" styleId="lastManageYearTo">
        <html:optionsCollection name="rng050Form" property="rng050YearList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageMonthTo" styleId="lastManageMonthTo">
        <html:optionsCollection name="rng050Form" property="rng050MonthList" value="value" label="label" />
      </html:select>
      <html:select property="sltLastManageDayTo" styleId="lastManageDayTo">
        <html:optionsCollection name="rng050Form" property="rng050DayList" value="value" label="label" />
      </html:select>
      <input type="button" value="Cal" onclick="wrtCalendar(this.form.lastManageDayTo, this.form.lastManageMonthTo, this.form.lastManageYearTo)" class="calendar_btn">
    </td>
  </tr>
  <tr><td colspan="2">&nbsp</td></tr>
  <tr>
  <td width="100%" valign="bottom" align="center" colspan="2"><input type="submit" name="btn_usrimp" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('search')"></td>
  </tr>

  <bean:define id="sortKey" name="rng050Form" property="rngAdminSortKey" />
  <bean:define id="orderKey" name="rng050Form" property="rngAdminOrderKey" />
  <% int iSortKey = ((Integer) sortKey).intValue();                                       %>
  <% int iOrderKey = ((Integer) orderKey).intValue();                                     %>
  <% int[] sortKeyList = new int[] { jp.groupsession.v2.rng.RngConst.RNG_SORT_TITLE, jp.groupsession.v2.rng.RngConst.RNG_SORT_DATE, jp.groupsession.v2.rng.RngConst.RNG_SORT_KAKUNIN, jp.groupsession.v2.rng.RngConst.RNG_SORT_NAME }; %>
  <% String[] title_width = new String[] { "20%", "10%", "10%", "60%"};                   %>
  <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();  %>
  <% String subject = gsMsg.getMessage(request, "cmn.subject");  %>
  <% String date = gsMsg.getMessage(request, "rng.application.date");  %>
  <% String lastDate = gsMsg.getMessage(request, "rng.105");  %>
  <% String keiro = gsMsg.getMessage(request, "rng.channel.status");  %>
  <% String[] titleList = new String[] {subject, date, lastDate, keiro};  %>

  <tr>
  <td class="td_type_tab" colspan="3">
    <table class="tl0" width="100%" cellpadding="5" cellspacing="0" id="selectionSearchArea">
      <tr>
        <td colspan="4" width="100%" align="right" valign="top">
        <img src="../common/images/keiro.gif" alt="<gsmsg:write key="rng.60" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.60" />&nbsp;
        <img src="../common/images/keiro_ok.gif" alt="<gsmsg:write key="rng.43" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.43" />&nbsp;
        <img src="../common/images/arrow_keiro.gif" alt="<gsmsg:write key="rng.48" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.48" />&nbsp;
        <img src="../common/images/keiro_ng.gif" alt="<gsmsg:write key="rng.22" />"><gsmsg:write key="wml.215" /><gsmsg:write key="rng.22" />
        </td>
      </tr>

      <logic:notEmpty name="rng050Form" property="pageList">
      <tr>
      <td colspan="4" width="100%" align="right" valign="bottom" nowrap>
        <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select property="rngAdminPageTop" onchange="selectPage(0);" styleClass="text_i">
          <html:optionsCollection name="rng050Form" property="pageList" value="value" label="label" />
        </html:select>
        <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a>
      </td>
      </tr>
      </logic:notEmpty>

      <tr class="smail_th">
      <% String down = gsMsg.getMessage(request, "tcd.tcd040.23");  %>
      <% String up = gsMsg.getMessage(request, "tcd.tcd040.22");  %>
      <% int order_asc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC; %>
      <% int order_desc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC; %>
      <% for (int i = 0; i < sortKeyList.length; i++) {   %>
      <%   String title = titleList[i];                   %>
      <%   String skey = String.valueOf(sortKeyList[i]);  %>
      <%   String order = String.valueOf(order_asc);      %>
      <%   if (iSortKey == sortKeyList[i]) {              %>
      <%     if (iOrderKey == order_desc) {               %>
      <%       title = title + down;                      %>
      <%     } else {                                     %>
      <%       title = title + up;                      %>
      <%       order = String.valueOf(order_desc);        %>
      <%     }                                            %>
      <%   }                                              %>
      <th width="<%= title_width[i] %>" class="table_bg_7D91BD"><a href="#" onClick="return sorton(<%= skey %>, <%= order %>);"><span class="text_tlw"><%= title %></span></a></th>
      <% } %>
      </tr>

      <logic:notEmpty name="rng050Form" property="rng050rngDataList">
      <% String[] tdclass = {"td_type1", "td_type25_2"}; %>

      <logic:iterate id="rngData" name="rng050Form" property="rng050rngDataList" indexId="idx" scope="request">

      <tr>
      <td class="<%= tdclass[idx.intValue() % 2] %>" align="left" valign="middle" nowrap><a href="#" onclick="clickRingi('rng030', <bean:write name="rngData" property="rngSid" />);"><span class="normal_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td class="<%= tdclass[idx.intValue() % 2] %>" align="center" valign="middle"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td class="<%= tdclass[idx.intValue() % 2] %>" align="center" valign="middle"><bean:write name="rngData" property="strLastManageDate" /></td>
      <td class="<%= tdclass[idx.intValue() % 2] %>" align="left" valign="middle">

<% String apprUserClass = "";%>
<logic:equal name="rngData" property="apprUserDelFlg" value="true">
<% apprUserClass = "text_appruser_del"; %>
</logic:equal>
      <span class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></span>
      <logic:notEmpty name="rngData" property="channelList">
      <% int beforeStatus = jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR; %>

      <logic:iterate id="channelData" name="rngData" property="channelList" indexId="channelIdx">
      <bean:define id="rncStatus" name="channelData" property="rncStatus" />
      <% int intRncStatus = ((Integer) rncStatus).intValue(); %>
      <% if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_CONFIRM) { %>
      <img src="../common/images/arrow_keiro.gif" alt="<gsmsg:write key="rng.48" />">
      <% } else if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR) { %>
      <img src="../common/images/keiro_ok.gif" alt="<gsmsg:write key="rng.43" />">
      <% } else if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_NOSET) { %>
      <img src="../common/images/keiro.gif" alt="<gsmsg:write key="rng.60" />">
      <% } else if (intRncStatus == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_DENIAL) { %>
      <img src="../common/images/keiro_ng.gif" alt="<gsmsg:write key="rng.22" />">
      <% } %>
      <% beforeStatus = intRncStatus; %>

      <logic:equal name="channelData" property="delUser" value="true">
        <strike><bean:write name="channelData" property="userName" /></strike>
      </logic:equal>
      <logic:equal name="channelData" property="delUser" value="false">
        <bean:write name="channelData" property="userName" />
      </logic:equal>

      </logic:iterate>

      </logic:notEmpty>
      </td>
      </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>

  </td>
  </tr>

  <logic:notEmpty name="rng050Form" property="pageList">
  <tr>
  <td width="100%" align="right" valign="bottom" colspan="3">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select property="rngAdminPageBottom" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="rng050Form" property="pageList" value="value" label="label" />
    </html:select>
    <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td class="td_type_tab" colspan="3">
    <table width="100%" class="tl5">
      <tr>
      <td width="100%">&nbsp;</td>
      <td align="right">
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
<logic:equal name="rng050Form" property="rngWebSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
<span id="tooltip_search" class="tooltip_search"></span>
</logic:equal>
</body>
</html:html>