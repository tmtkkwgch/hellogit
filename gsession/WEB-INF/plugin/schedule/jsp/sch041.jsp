<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthContent = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.MAX_LENGTH_VALUE); %>
<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.MAX_LENGTH_BIKO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch041.1" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearchText.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch041.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/reservepopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String selectionEvent = ""; %>
<% boolean selectionFlg = false; %>
<% String valueFocusEvent = ""; %>
<% String bikoFocusEvent = ""; %>


<% String closeScript = "calWindowClose();windowClose();"; %>
<logic:equal name="sch041Form" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
<% closeScript += "companyWindowClose();"; %>
</logic:equal>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<logic:notEmpty name="sch041Form" property="sch040SelectUsrLabel" scope="request">
<logic:equal name="sch041Form" property="cmd" value="edit">
<body class="body_03" onunload="<%= closeScript %>" onload="setDisabled();setTimeStatus();changeWeekCombo();showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthBiko %>, 'inputlength2');<%= selectionEvent %>">
</logic:equal>
<logic:equal name="sch041Form" property="cmd" value="add">
<body class="body_03" onunload="<%= closeScript %>" onload="setDisabled();setTimeStatus();changeWeekCombo();showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthBiko %>, 'inputlength2');<%= selectionEvent %>">
</logic:equal>
</logic:notEmpty>
<logic:empty name="sch041Form" property="sch040SelectUsrLabel" scope="request">
<body class="body_03" onunload="<%= closeScript %>" onload="setDisabled();setTimeStatus();changeWeekCombo();showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthBiko %>, 'inputlength2');<%= selectionEvent %>">
</logic:empty>


<html:form action="/schedule/sch041">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch020SelectUsrSid" />
<html:hidden property="sch030FromHour" />
<html:hidden property="schWeekDate" />
<html:hidden property="schDailyDate" />

<html:hidden property="sch100PageNum" />
<html:hidden property="sch100Slt_page1" />
<html:hidden property="sch100Slt_page2" />
<html:hidden property="sch100OrderKey1" />
<html:hidden property="sch100SortKey1" />
<html:hidden property="sch100OrderKey2" />
<html:hidden property="sch100SortKey2" />
<html:hidden property="sch100SvOrderKey1" />
<html:hidden property="sch100SvSortKey1" />
<html:hidden property="sch100SvOrderKey2" />
<html:hidden property="sch100SvSortKey2" />
<html:hidden property="sch100SelectUsrSid" />
<html:hidden property="sch100SvSltGroup" />
<html:hidden property="sch100SvSltUser" />
<html:hidden property="sch100SvSltStartYearFr" />
<html:hidden property="sch100SvSltStartMonthFr" />
<html:hidden property="sch100SvSltStartDayFr" />
<html:hidden property="sch100SvSltStartYearTo" />
<html:hidden property="sch100SvSltStartMonthTo" />
<html:hidden property="sch100SvSltStartDayTo" />
<html:hidden property="sch100SvSltEndYearFr" />
<html:hidden property="sch100SvSltEndMonthFr" />
<html:hidden property="sch100SvSltEndDayFr" />
<html:hidden property="sch100SvSltEndYearTo" />
<html:hidden property="sch100SvSltEndMonthTo" />
<html:hidden property="sch100SvSltEndDayTo" />
<html:hidden property="sch100SvKeyWordkbn" />
<html:hidden property="sch100SvKeyValue" />
<html:hidden property="sch100SltGroup" />
<html:hidden property="sch100SltUser" />
<html:hidden property="sch100SltStartYearFr" />
<html:hidden property="sch100SltStartMonthFr" />
<html:hidden property="sch100SltStartDayFr" />
<html:hidden property="sch100SltStartYearTo" />
<html:hidden property="sch100SltStartMonthTo" />
<html:hidden property="sch100SltStartDayTo" />
<html:hidden property="sch100SltEndYearFr" />
<html:hidden property="sch100SltEndMonthFr" />
<html:hidden property="sch100SltEndDayFr" />
<html:hidden property="sch100SltEndYearTo" />
<html:hidden property="sch100SltEndMonthTo" />
<html:hidden property="sch100SltEndDayTo" />
<html:hidden property="sch100KeyWordkbn" />
<html:hidden property="sch010searchWord" />

<logic:notEmpty name="sch041Form" property="schNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="sch041Form" property="schNotAccessGroupList" scope="request">
    <input type="hidden" name="schNotAccessGroup" value="<bean:write name="notAccessGroup"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="schNotAccessUserList" scope="request">
  <logic:iterate id="notAccessUser" name="sch041Form" property="schNotAccessUserList" scope="request">
    <input type="hidden" name="schNotAccessUser" value="<bean:write name="notAccessUser"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch041Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch041Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch041Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch041Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sch040Bgcolor" />
<html:hidden property="sch040Title" />
<html:hidden property="sch040Value" />
<html:hidden property="sch040Biko" />
<html:hidden property="sch040Public" />
<html:hidden property="sch040FrYear" />
<html:hidden property="sch040FrMonth" />
<html:hidden property="sch040FrDay" />
<html:hidden property="sch040FrHour" />
<html:hidden property="sch040FrMin" />
<html:hidden property="sch040ToYear" />
<html:hidden property="sch040ToMonth" />
<html:hidden property="sch040ToDay" />
<html:hidden property="sch040ToHour" />
<html:hidden property="sch040ToMin" />
<html:hidden property="sch040Edit" />
<html:hidden property="sch040TimeKbn" />
<html:hidden property="sch040InitFlg" />
<html:hidden property="sch040ScrollFlg" />
<html:hidden property="sch040CrangeKbn" />
<html:hidden property="sch040GroupSid" />
<html:hidden property="sch041ExtSid" />
<html:hidden property="reservePluginKbn" />
<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />

<input type="hidden" name="sch041delCompanyId" value="">
<input type="hidden" name="sch041delCompanyBaseId" value="">
<html:hidden property="sch040contact" />

<logic:notEmpty name="sch041Form" property="sch040CompanySid">
  <logic:iterate id="coId" name="sch041Form" property="sch040CompanySid">
    <input type="hidden" name="sch040CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<logic:notEmpty name="sch041Form" property="sch040CompanyBaseSid">
  <logic:iterate id="coId" name="sch041Form" property="sch040CompanyBaseSid">
    <input type="hidden" name="sch040CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch040AddressId">
  <logic:iterate id="addressId" name="sch041Form" property="sch040AddressId">
    <input type="hidden" name="sch040AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<div id="sch041CompanyIdArea">
<logic:notEmpty name="sch041Form" property="sch041CompanySid">
  <logic:iterate id="coId" name="sch041Form" property="sch041CompanySid">
    <input type="hidden" name="sch041CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<div id="sch041CompanyBaseIdArea">
<logic:notEmpty name="sch041Form" property="sch041CompanyBaseSid">
  <logic:iterate id="coId" name="sch041Form" property="sch041CompanyBaseSid">
    <input type="hidden" name="sch041CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<div id="sch041AddressIdArea">
<logic:notEmpty name="sch041Form" property="sch041AddressId">
  <logic:iterate id="addressId" name="sch041Form" property="sch041AddressId">
    <input type="hidden" name="sch041AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="sch041Form" property="sch010SelectUsrKbn" /><bean:write name="sch041Form" property="cmd" />">

<!--　BODY -->
<logic:notEmpty name="sch041Form" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="sch041Form" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch041SvUsers" scope="request">
<logic:iterate id="eulBean" name="sch041Form" property="sch041SvUsers" scope="request">
<input type="hidden" name="sch041SvUsers" value="<bean:write name="eulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch041SvReserve" scope="request">
<logic:iterate id="exresBean" name="sch041Form" property="sch041SvReserve" scope="request">
<input type="hidden" name="sch041SvReserve" value="<bean:write name="exresBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="svReserveUsers" scope="request">
<logic:iterate id="resBean" name="sch041Form" property="svReserveUsers" scope="request">
<input type="hidden" name="svReserveUsers" value="<bean:write name="resBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch041Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch041Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch041Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="schedule.sch041.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
    <input type="button" value="<gsmsg:write key="cmn.general.regist" />" class="btn_base1" onClick="buttonPush('041_default', '<bean:write name="sch041Form" property="sch040BtnCmd" />');">
    <logic:equal name="sch041Form" property="cmd" value="edit">
      <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush('041_copy', 'add');">
    </logic:equal>

    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

        <logic:equal name="sch041Form" property="cmd" value="add">
        <input type="hidden" name="CMD" value="041_ok">
          <input type="submit" value="<gsmsg:write key="cmn.entry.2" />" class="btn_add_n1">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041_back', '<bean:write name="sch041Form" property="sch040BtnCmd" />');">
        </logic:equal>

        <logic:equal name="sch041Form" property="cmd" value="edit">
        <input type="hidden" name="CMD" value="041_ok">
          <input type="submit" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1">
          <logic:notEqual name="sch041Form" property="sch041ExtSid" value="0">
          <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('041_del', 'del');">
          </logic:notEqual>
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041_back', '<bean:write name="sch041Form" property="sch040BtnCmd" />');">
        </logic:equal>

    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_wt", "td_sch_type2"}; %>
    <logic:notEqual name="sch041Form" property="sch010SelectUsrKbn" value="0">
    <% tdColor = tdColors[1]; %>
    </logic:notEqual>
    <logic:equal name="sch041Form" property="sch010SelectUsrKbn" value="0">
    <% tdColor = tdColors[0]; %>
    </logic:equal>



    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="schedule.4" /></span></td>
    <td class="<%= tdColor %>" align="left" width="80%">
    <logic:notEqual name="sch041Form" property="sch010SelectUsrKbn" value="0">
    <span id="lt"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0"></span>
    </logic:notEqual>
    <span class="text_base"><bean:write name="sch041Form" property="sch040UsrName" /></span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.advanced.settings" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
      <table width="87%">

      <tr>
      <td colspan="3" width="100%" nowrap>
      <html:radio name="sch041Form" property="sch041ExtKbn" value="1" styleId="sch041ExtKbn0" onclick="setDisabled();"/>
      <span class="text_base2"><label for="sch041ExtKbn0"><gsmsg:write key="cmn.everyday" /></label></span>&nbsp;
      </td>
      </tr>

      <tr>
      <td colspan="1" width="10%" nowrap>
      <html:radio name="sch041Form" property="sch041ExtKbn" value="2" styleId="sch041ExtKbn1" onclick="setDisabled();"/>
      <span class="text_base2"><label for="sch041ExtKbn1"><gsmsg:write key="cmn.weekly2" /></label></span></td>
      <td colspan="1" width="10%" align="center" nowrap>&nbsp;</td>
      <td colspan="1" rowspan="2" align="left" width="80%" nowrap>

        <table class="tl_u2">

        <tr>
        <th class="td_type9"><span color="#ff0000"><gsmsg:write key="cmn.sunday" /></span></th>
        <th class="td_type1"><gsmsg:write key="cmn.Monday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.tuesday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.wednesday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.thursday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.friday" /></th>
        <th class="td_type8"><span color="#0000ff"><gsmsg:write key="cmn.saturday" /></span></th>
        </tr>

        <tr>
        <th class="td_type9"><html:multibox property="sch041Dweek" value="1"/></th>
        <th class="td_type1"><html:multibox property="sch041Dweek" value="2"/></th>
        <th class="td_type1"><html:multibox property="sch041Dweek" value="3"/></th>
        <th class="td_type1"><html:multibox property="sch041Dweek" value="4"/></th>
        <th class="td_type1"><html:multibox property="sch041Dweek" value="5"/></th>
        <th class="td_type1"><html:multibox property="sch041Dweek" value="6"/></th>
        <th class="td_type8"><html:multibox property="sch041Dweek" value="7"/></th>
        </tr>

        </table>
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>
      <html:radio name="sch041Form" property="sch041ExtKbn" value="3" styleId="sch041ExtKbn2" onclick="setDisabled();"/>
      <span class="text_base2"><label for="sch041ExtKbn2"><gsmsg:write key="cmn.monthly.2" /></label></span>&nbsp;</td>
      <td colspan="1" align="center" nowrap><span class="text_base2"><gsmsg:write key="cmn.week" />：</span>
      <html:select property="sch041Week" onchange="changeWeekCombo();">
        <html:optionsCollection name="sch041Form" property="sch041WeekLabel" value="value" label="label" />
      </html:select>
      &nbsp;
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>&nbsp;</td>
      <td colspan="1" align="center" nowrap><span class="text_base2"><gsmsg:write key="cmn.day" />：</span>
      <html:select property="sch041Day">
        <html:optionsCollection name="sch041Form" property="sch041ExDayLabel" value="value" label="label" />
      </html:select>
      &nbsp;
      </td>
      <td colspan="1" nowrap>
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>
      <html:radio name="sch041Form" property="sch041ExtKbn" value="4" styleId="sch041ExtKbn3" onclick="setDisabled();"/>
      <span class="text_base2"><label for="sch041ExtKbn3"><gsmsg:write key="cmn.yearly" /></label></span>&nbsp;</td>
      <td colspan="1" align="center" nowrap>
        <html:select property="sch041MonthOfYearly" styleId="selMonthOfYearly">
           <html:optionsCollection name="sch041Form" property="sch040MonthLabel" value="value" label="label" />
        </html:select>
        <html:select property="sch041DayOfYearly" styleId="selDayOfYearly">
           <html:optionsCollection name="sch041Form" property="sch041ExDayOfYearlyLabel" value="value" label="label" />
        </html:select>
      </td>
      </tr>

      <tr>
      <td colspan="4"><span class="text_r1"><gsmsg:write key="schedule.sch041.2" /></span><br>
      <html:radio name="sch041Form" property="sch041TranKbn" styleId="sch041TranKbn9" value="9"/><span class="text_base"><label for="sch041TranKbn9"><gsmsg:write key="cmn.dont.entry" /></label></span><br>
      <html:radio name="sch041Form" property="sch041TranKbn" styleId="sch041TranKbn1" value="0"/><span class="text_base"><label for="sch041TranKbn1"><gsmsg:write key="schedule.144" /></label></span><br>
      <html:radio name="sch041Form" property="sch041TranKbn" styleId="sch041TranKbn2" value="1"/><span class="text_base"><label for="sch041TranKbn2"><gsmsg:write key="cmn.change.before.businessday" /></label></span><br>
      <html:radio name="sch041Form" property="sch041TranKbn" styleId="sch041TranKbn3" value="2"/><span class="text_base"><label for="sch041TranKbn3"><gsmsg:write key="cmn.change.next.businessday" /></label></span>
      <span class="text_base">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;※<gsmsg:write key="cmn.holiday.based.timecard" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td rowspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch041.8" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
       <html:select property="sch041FrYear" styleId="selYear">
        <html:optionsCollection name="sch041Form" property="sch040YearLabel" value="value" label="label" />
     </html:select>
     <html:select property="sch041FrMonth" styleId="selMonth">
        <html:optionsCollection name="sch041Form" property="sch040MonthLabel" value="value" label="label" />
     </html:select>
     <html:select property="sch041FrDay" styleId="selDay">
        <html:optionsCollection name="sch041Form" property="sch040DayLabel" value="value" label="label" />
     </html:select>
       <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDay, this.form.selMonth, this.form.sch041FrYear, 'sch041FrCalBtn')" class="calendar_btn" id="sch041FrCalBtn">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 3)">

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="main.src.man250.30" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
     <html:select property="sch041ToYear" styleId="seleYear">
        <html:optionsCollection name="sch041Form" property="sch040YearLabel" value="value" label="label" />
     </html:select>
     <html:select property="sch041ToMonth" styleId="seleMonth">
        <html:optionsCollection name="sch041Form" property="sch040MonthLabel" value="value" label="label" />
     </html:select>
     <html:select property="sch041ToDay" styleId="seleDay">
        <html:optionsCollection name="sch041Form" property="sch040DayLabel" value="value" label="label" />
     </html:select>
     <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.seleDay, this.form.seleMonth, this.form.seleYear, 'sch041ToCalBtn')" class="calendar_btn" id="sch041ToCalBtn">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#seleYear')[0], $('#seleMonth')[0], $('#seleDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#seleYear')[0], $('#seleMonth')[0], $('#seleDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#seleYear')[0], $('#seleMonth')[0], $('#seleDay')[0], 3)">

    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.time" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>">
     <html:select property="sch041FrHour">
        <html:optionsCollection name="sch041Form" property="sch040HourLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.hour.input" />
     <html:select property="sch041FrMin">
        <html:optionsCollection name="sch041Form" property="sch040MinuteLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.minute.input" />
～
     <html:select property="sch041ToHour">
        <html:optionsCollection name="sch041Form" property="sch040HourLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.hour.input" />
     <html:select property="sch041ToMin">
        <html:optionsCollection name="sch041Form" property="sch040MinuteLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.minute.input" />
     <span class="text_base"><html:checkbox name="sch041Form" property="sch041TimeKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.TIME_NOT_EXIST) %>" styleId="num_seigyo" onclick="return changeTimeStatus();" /><label for="num_seigyo"><gsmsg:write key="schedule.7" /></label></span>
    </td>
    </tr>

    <logic:equal name="sch041Form" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">

    <tr>
    <td colspan="2" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="schedule.14" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <logic:empty name="sch041Form" property="sch041CompanyList">
        <input type="button" class="btn_address_n1" value="<gsmsg:write key="addressbook" />" onclick="return openCompanyWindow('sch041')" />
      </logic:empty>

      <logic:notEmpty name="sch041Form" property="sch041CompanyList">
      <table class="tl0" width="100%">
      <tr>
      <td>
        <logic:notEmpty name="sch041Form" property="sch041AddressId">
        <span class="text_base">
        <html:checkbox name="sch041Form" property="sch041contact" value="1" styleId="contactCheck" /><label for="contactCheck"><gsmsg:write key="schedule.23" /></label>
        </span>
        </logic:notEmpty>
      </td>
      <td style="text-align:right; padding-left:10px; padding-left:10px">
        <input type="button" class="btn_address_n1" value="<gsmsg:write key="addressbook" />" onclick="return openCompanyWindow('sch041')" />
      </td>
      </tr>

      <tr>
      <td colspan="2">
        <table width="100%" class="tl0 text_base">
        <logic:iterate id="companyData" name="sch041Form" property="sch041CompanyList">
          <tr><td colspan="3">&nbsp</td></tr>
          <tr>
          <td width="0%" style="vertical-valign:middle;">
          <a href="#" onClick="deleteCompany(<bean:write name="companyData" property="companySid" />, <bean:write name="companyData" property="companyBaseSid" />);"><img src="../common/images/delete.gif" class="img_bottom" width="15" height="15" alt="<gsmsg:write key="cmn.delete.company" />"></a>
          </td>
          <td width="70%" style="text-align:left!important;text-valign:center">
            &nbsp;<span class="text_company">
            <logic:equal name="companyData" property="companySid" value="0">
            <bean:write name="companyData" property="companyName" />
            </logic:equal>
            <logic:notEqual name="companyData" property="companySid" value="0">
            <a href="javascript:void(0);" onclick="return openSubWindow('../address/adr250.do?adr250AcoSid=<bean:write name="companyData" property="companySid" />')" class="text_blue_100"><bean:write name="companyData" property="companyName" /></a>
            </logic:notEqual>
            </span>
          </td>
          <td width="30%" nowrap>
          <logic:equal name="sch041Form" property="searchPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
            <logic:notEqual name="companyData" property="companySid" value="0">
            &nbsp;<a href="javascript:void(0);" onClick="webSearch('<bean:write name="companyData" property="companyNameSearch" />');" style="padding-top:2px;"><span class="search_normal text_blue_100"><gsmsg:write key="schedule.15" /></span></a>
            <logic:notEmpty name="companyData" property="companyAddress">
            &nbsp;<a href="javascript:void(0);" onClick="webSearch('<bean:write name="companyData" property="companyAddress" />');" style="padding-top:2px;"><span class="search_map text_blue_100"><gsmsg:write key="cmn.search.map" /></span></a>
            </logic:notEmpty>
            </logic:notEqual>
          </logic:equal>
          </td>
          </tr>
          <tr>
          <td>&nbsp;</td>
          <td colspan="2">
          <logic:notEmpty name="companyData" property="addressDataList">
          <logic:iterate id="addressData" name="companyData" property="addressDataList">
            <span class="text_tantou"><bean:write name="addressData" property="adrName" /></span><br>
          </logic:iterate>
          </logic:notEmpty>
          </td>
          </tr>
        </logic:iterate>
        </table>
      </td>
      </tr>
      </table>
      </logic:notEmpty>
    </td>
    </tr>

    </logic:equal>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>">
    <% if (selectionFlg) { %>
    <html:text name="sch041Form" maxlength="50" property="sch041Title" styleClass="text_base"  styleId="selectionSearchArea" style="width:335px;" />
    <% } else { %>
    <html:text name="sch041Form" maxlength="50" property="sch041Title" styleClass="text_base"  styleId="selectionSearchArea" style="width:335px;" />
    <% } %>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.10" /></span></td>
    <td align="left" class="<%= tdColor %>">

    <bean:define id="colorMsg1" value=""/>
    <bean:define id="colorMsg2" value=""/>
    <bean:define id="colorMsg3" value=""/>
    <bean:define id="colorMsg4" value=""/>
    <bean:define id="colorMsg5" value=""/>
    <bean:define id="colorMsg6" value=""/>
    <bean:define id="colorMsg7" value=""/>
    <bean:define id="colorMsg8" value=""/>
    <bean:define id="colorMsg9" value=""/>
    <bean:define id="colorMsg10" value=""/>
    <logic:iterate id="msgStr" name="sch041Form" property="sch040ColorMsgList" indexId="msgId" type="java.lang.String">
    <logic:equal name="msgId" value="0">
    <% colorMsg1 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="1">
    <% colorMsg2 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="2">
    <% colorMsg3 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="3">
    <% colorMsg4 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="4">
    <% colorMsg5 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="5">
    <% colorMsg6 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="6">
    <% colorMsg7 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="7">
    <% colorMsg8 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="8">
    <% colorMsg9 = msgStr; %>
    </logic:equal>
    <logic:equal name="msgId" value="9">
    <% colorMsg10 = msgStr; %>
    </logic:equal>
    </logic:iterate>

    <span class="sc_block_color_1"><html:radio name="sch041Form" property="sch041Bgcolor" value="1" styleId="bg_color1" /></span>
    <label for="bg_color1" class="text_base"><%= colorMsg1 %></label>
    <span class="sc_block_color_2"><html:radio name="sch041Form" property="sch041Bgcolor" value="2" styleId="bg_color2" /></span>
    <label for="bg_color2" class="text_base"><%= colorMsg2 %></label>
    <span class="sc_block_color_3"><html:radio name="sch041Form" property="sch041Bgcolor" value="3" styleId="bg_color3" /></span>
    <label for="bg_color3" class="text_base"><%= colorMsg3 %></label>
    <span class="sc_block_color_4"><html:radio name="sch041Form" property="sch041Bgcolor" value="4" styleId="bg_color4" /></span>
    <label for="bg_color4" class="text_base"><%= colorMsg4 %></label>
    <span class="sc_block_color_5"><html:radio name="sch041Form" property="sch041Bgcolor" value="5" styleId="bg_color5" /></span>
    <label for="bg_color5" class="text_base"><%= colorMsg5 %></label>

    <logic:equal name="sch041Form" property="sch041colorKbn" value="1">
      <div><img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px"></div>
      <span class="sc_block_color_6"><html:radio name="sch041Form" property="sch041Bgcolor" value="6" styleId="bg_color6" /></span>
      <label for="bg_color6" class="text_base"><%= colorMsg6 %></label>
      <span class="sc_block_color_7"><html:radio name="sch041Form" property="sch041Bgcolor" value="7" styleId="bg_color7" /></span>
      <label for="bg_color7" class="text_base"><%= colorMsg7 %></label>
      <span class="sc_block_color_8"><html:radio name="sch041Form" property="sch041Bgcolor" value="8" styleId="bg_color8" /></span>
      <label for="bg_color8" class="text_base"><%= colorMsg8 %></label>
      <span class="sc_block_color_9"><html:radio name="sch041Form" property="sch041Bgcolor" value="9" styleId="bg_color9" /></span>
      <label for="bg_color9" class="text_base"><%= colorMsg9 %></label>
      <span class="sc_block_color_10"><html:radio name="sch041Form" property="sch041Bgcolor" value="10" styleId="bg_color10" /></span>
      <label for="bg_color10" class="text_base"><%= colorMsg10 %></label>
    </logic:equal>

  </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="<%= tdColor %>"><textarea name="sch041Value" style="width:455px;" rows="10" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength');" id="inputstr" <%= valueFocusEvent %>><bean:write name="sch041Form" property="sch041Value" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.2" /></span></td>
    <td align="left" class="<%= tdColor %>"><textarea name="sch041Biko" style="width:455px;" rows="5" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength2');" id="inputstr2" <%= bikoFocusEvent %>><bean:write name="sch041Form" property="sch041Biko" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>">
      <html:radio name="sch041Form" property="sch041Edit" styleId="sch040Edit0" value="0" /><span class="text_base2"><label for="sch040Edit0"><gsmsg:write key="cmn.nolimit" /></label></span>&nbsp;
      <html:radio name="sch041Form" property="sch041Edit" styleId="sch040Edit1" value="1" /><span class="text_base2"><label for="sch040Edit1"><gsmsg:write key="cmn.only.principal.or.registant" /></label></span>
      <html:radio name="sch041Form" property="sch041Edit" styleId="sch040Edit2" value="2" /><span class="text_base2"><label for="sch040Edit2"><gsmsg:write key="cmn.only.affiliation.group.membership" /></label></span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.21" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>">
      <html:radio name="sch041Form" property="sch041Public" styleId="sch040Public0" value="0" /><span class="text_base2"><label for="sch040Public0"><gsmsg:write key="cmn.public" /></label></span>&nbsp;
      <html:radio name="sch041Form" property="sch041Public" styleId="sch040Public1" value="1" /><span class="text_base2"><label for="sch040Public1"><gsmsg:write key="cmn.private" /></label></span>
      <logic:equal name="sch041Form" property="sch010SelectUsrKbn" value="0">
        <html:radio name="sch041Form" property="sch041Public" styleId="sch040Public2" value="2" /><span class="text_base2"><label for="sch040Public2"><gsmsg:write key="schedule.5" /></label></span>
        <html:radio name="sch041Form" property="sch041Public" styleId="sch040Public3" value="3" /><span class="text_base2"><label for="sch040Public3"><gsmsg:write key="schedule.28" /></label></span>
      </logic:equal>
      <a id="add_user" name="add_user"></a>
    </td>
    </tr>

  <!-- グループスケジュール 非表示部分 START -->
    <logic:notEqual name="sch041Form" property="sch010SelectUsrKbn" value="1">
    <logic:notEmpty name="sch041Form" property="sch040AddedUsrLabel" scope="request">
    <logic:equal name="sch041Form" property="cmd" value="edit">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="schedule.32" /></span><span class="text_r2">※</span></td>
    <td align="left" class="<%= tdColor %>">
      <span class="text_r1"><gsmsg:write key="schedule.12" /></span>
      <logic:iterate id="aurBean" name="sch041Form" property="sch040AddedUsrLabel" scope="request">
      <br><span class="text_base"><bean:write name="aurBean" property="usiSei" scope="page"/>　<bean:write name="aurBean" property="usiMei" scope="page"/></span>
      </logic:iterate><br>
      <html:radio name="sch041Form" property="sch040BatchRef" styleId="sch040BatchRef0" value="1" onclick="setDisabled();"/><span class="text_base2"><label for="sch040BatchRef0"><gsmsg:write key="schedule.34" /></label></span>
      <html:radio name="sch041Form" property="sch040BatchRef" styleId="sch040BatchRef1" value="0" onclick="setDisabled();"/><span class="text_base2"><label for="sch040BatchRef1"><gsmsg:write key="schedule.33" /></label></span>&nbsp;
    </td>
    </tr>
    </logic:equal>
    </logic:notEmpty>

    <logic:empty name="sch041Form" property="sch040AddedUsrLabel" scope="request">
    <html:hidden property="sch040BatchRef" value="1"/>
    </logic:empty>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="schedule.117" /><br><br></span>
    </td>
    <td align="left" class="<%= tdColor %>">
      <table width="0%" border="0">
      <tr>
      <td width="40%">
      <span class="text_r1">[<gsmsg:write key="schedule.29" />]</span>
      </td>
      <td width="20%">&nbsp;</td>
      <td width="40%">
        <logic:equal name="sch041Form" property="sch040CrangeKbn" value="0">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup_setDisable(this.form.sch041GroupSid, 'sch041GroupSid', '<bean:write name="sch041Form" property="sch041GroupSid" />', '1', '041_group', 'sch041SvUsers', '<bean:write name="sch041Form" property="sch010SelectUsrSid" />', '0', 0, 0, 0, 'schNotAccessUser', null, 'schNotAccessGroup')" type="button">
        </logic:equal>
      </td>
      </tr>
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left">
        <logic:notEmpty name="sch041Form" property="sch040GroupLabel" scope="request">
        <html:select property="sch041GroupSid" onchange="changeGroupCombo('041_group');">

      <logic:notEmpty name="sch041Form" property="sch040GroupLabel" scope="request">
      <logic:iterate id="gpBean" name="sch041Form" property="sch040GroupLabel" scope="request">

      <% boolean gpDisabled = false; %>
      <logic:equal name="gpBean" property="viewKbn" value="false">
      <% gpDisabled = true; %>
      </logic:equal>

      <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
      <logic:equal name="gpBean" property="styleClass" value="0">
      <html:option value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:equal>

      <logic:notEqual name="gpBean" property="styleClass" value="0">
      <html:option styleClass="select03" value="<%= gpValue %>" disabled="<%= gpDisabled %>"><bean:write name="gpBean" property="label" /></html:option>
      </logic:notEqual>

      </logic:iterate>
      </logic:notEmpty>
      <logic:equal name="sch041Form" property="sch040CrangeKbn" value="0">
        <input type="button" onclick="openGroupWindow(this.form.sch041GroupSid, 'sch041GroupSid', '1', '041_group', 0, '', 'schNotAccessGroup');" class="group_btn2" value="&nbsp;&nbsp;" id="sch041GroupBtn">
      </logic:equal>
        </html:select>
        </logic:notEmpty>
        <span class="text_base8">
        <input type="checkbox" name="sch041SelectUsersKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SELECT_ON) %>" id="select_users" onclick="return selectUsersList();" />
        <label for="select_users"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center">
        <!-- 同時登録先 -->
        <select size="5" multiple name="sch041users_r" class="select01">
        <logic:notEmpty name="sch041Form" property="sch040SelectUsrLabel" scope="request">
        <logic:iterate id="urBean" name="sch041Form" property="sch040SelectUsrLabel" scope="request">
           <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
          </logic:iterate>
         </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>

      <td align="center">

        <input type="button" class="btn_base1add" name="adduserBtn" value="<gsmsg:write key="cmn.add"/>" onClick="moveUser('041_rightarrow');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="moveUser('041_leftarrow');">

      </td>
      <td>
         <!-- グループ -->
         <select size="5" multiple name="sch041users_l" class="select01">
         <logic:notEmpty name="sch041Form" property="sch040BelongLabel" scope="request">
          <logic:iterate id="urBean" name="sch041Form" property="sch040BelongLabel" scope="request">
            <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
          </logic:iterate>
         </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>
      </tr>

      </table>
    </td>
    </tr>
  <!-- グループスケジュール 非表示部分 END -->
    </logic:notEqual>

    <logic:equal name="sch041Form" property="sch010SelectUsrKbn" value="1">
    <html:hidden property="sch040ReserveGroupSid" value="-1"/>
    </logic:equal>

    <!-- 施設予約使用　有無判定 -->
    <logic:equal name="sch041Form" property="reservePluginKbn" value="0">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.reserve" /><br><br></span>
    </td>
    <td align="left" class="<%= tdColor %>">
      <span class="text_r1">[<gsmsg:write key="schedule.26" />]</span>

      <table width="0%" border="0">
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="schedule.6" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left">
        <logic:notEmpty name="sch041Form" property="sch040ReserveGroupLabel" scope="request">
        <html:select property="sch041ReserveGroupSid" onchange="changeGroupCombo('041_resgroup');">
          <html:optionsCollection name="sch041Form" property="sch040ReserveGroupLabel" value="value" label="label" />
        </html:select>
        </logic:notEmpty>
        <span class="text_base8">
        <input type="checkbox" name="sch041SelectResKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SELECT_ON) %>" id="select_res" onclick="return selectResList();" />
        <label for="select_res"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center">
         <!-- 同時登録施設 -->
        <select size="5" multiple name="sch041Reserve_r" class="select01">
        <logic:notEmpty name="sch041Form" property="sch040ReserveSelectLabel" scope="request">
        <logic:iterate id="ressBean" name="sch041Form" property="sch040ReserveSelectLabel" scope="request">
           <option value="<bean:write name="ressBean" property="rsdSid" scope="page"/>"><bean:write name="ressBean" property="rsdName" scope="page"/></option>
          </logic:iterate>
         </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>

        <logic:notEqual name="sch041Form" property="sch040CantReadRsvCount" value="0">
        <br>
        <span class="text_r1"><gsmsg:write key="schedule.150" />:<bean:write name="sch041Form" property="sch040CantReadRsvCount" /></span>
        </logic:notEqual>
      </td>

      <td align="center">

        <input type="button" class="btn_base1add" name="addresBtn" value="<gsmsg:write key="cmn.add"/>" onClick="moveUser('041_res_rightarrow');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="delresBtn" onClick="moveUser('041_res_leftarrow');">

      </td>
      <td>
         <!-- グループ -->
         <select size="5" multiple name="sch041Reserve_l" class="select01">
         <logic:notEmpty name="sch041Form" property="sch040ReserveBelongLabel" scope="request">
          <logic:iterate id="resbelBean" name="sch041Form" property="sch040ReserveBelongLabel" scope="request">
            <option value="<bean:write name="resbelBean" property="rsdSid" scope="page"/>"><bean:write name="resbelBean" property="rsdName" scope="page"/></option>
          </logic:iterate>
         </logic:notEmpty>
        <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>

      </tr>

      </table>

    </td>
    </tr>

    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="schedule.18" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <table class="tl0" width="100%">
      <tr>
      <td width="50%" nowrap>      <span class="text_base">※<gsmsg:write key="schedule.35" /></span>
      </span>
      </td>
      <td width="50%" align="left" nowrap>
      <input type="button" value="<gsmsg:write key="schedule.17" />" class="btn_base1" onClick="openScheduleReserveWindowEx('<bean:write name="sch041Form" property="sch041GroupSid" />', '<bean:write name="sch041Form" property="sch041ReserveGroupSid" />','<bean:write name="sch041Form" property="sch010SelectDate" />');">
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <logic:notEqual name="sch041Form" property="reservePluginKbn" value="0">
    <html:hidden property="sch041ReserveGroupSid" />
    </logic:notEqual>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <table class="tl0" width="100%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base">
      <logic:notEqual name="sch041Form" property="sch040AddUsrJkbn" value="9">
      <bean:write name="sch041Form" property="sch040AddUsrName" />
      </logic:notEqual>
      <logic:equal name="sch041Form" property="sch040AddUsrJkbn" value="9">
      <del>
      <bean:write name="sch041Form" property="sch040AddUsrName" />
      </del>
      </logic:equal>
      </span>
      </td>
      <td width="40%" align="left" nowrap>
      <span class="text_base"><bean:write name="sch041Form" property="sch040AddDate"/></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
  <tr>
  <td align="left">
  <input type="button" value="<gsmsg:write key="cmn.general.regist" />" class="btn_base1" onClick="buttonPush('041_default', '<bean:write name="sch041Form" property="sch040BtnCmd" />');">
    <logic:equal name="sch041Form" property="cmd" value="edit">
      <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush('041_copy', 'add');">
    </logic:equal>
    </td>
    <td align="right">

        <logic:equal name="sch041Form" property="cmd" value="add">
          <input type="submit" value="<gsmsg:write key="cmn.entry.2" />" class="btn_add_n1">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041_back', '<bean:write name="sch041Form" property="sch040BtnCmd" />');">
        </logic:equal>

        <logic:equal name="sch041Form" property="cmd" value="edit">
          <input type="submit" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1">
          <logic:notEqual name="sch041Form" property="sch041ExtSid" value="0">
          <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('041_del', 'del');">
          </logic:notEqual>
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041_back', '<bean:write name="sch041Form" property="sch040BtnCmd" />');">
        </logic:equal>

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

<% if (selectionFlg) { %>
<span id="tooltip_search" class="tooltip_search"></span>
<span id="damy"></span>
<% } %>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>