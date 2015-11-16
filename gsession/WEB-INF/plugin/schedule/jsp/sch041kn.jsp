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
<title>[GroupSession] <gsmsg:write key="schedule.sch041kn.5" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch041.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">

<html:form action="/schedule/sch041kn">
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
<html:hidden property="sch100SelectUsrSid" />
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


<logic:notEmpty name="sch041knForm" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch041knForm" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch041knForm" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch041knForm" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch041knForm" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch041knForm" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch041knForm" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch041knForm" property="sch100CsvOutField" scope="request">
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
<html:hidden property="sch040GroupSid" />
<html:hidden property="sch040ReserveGroupSid" />
<html:hidden property="sch040Edit" />
<html:hidden property="sch040TimeKbn" />
<html:hidden property="sch040InitFlg" />

<html:hidden property="sch041ExtSid" />
<html:hidden property="sch041ExtKbn" />
<html:hidden property="sch041Week" />
<html:hidden property="sch041Day" />
<html:hidden property="sch041DayOfYearly" />
<html:hidden property="sch041MonthOfYearly" />
<html:hidden property="sch041TranKbn" />
<html:hidden property="sch041FrYear" />
<html:hidden property="sch041FrMonth" />
<html:hidden property="sch041FrDay" />
<html:hidden property="sch041ToYear" />
<html:hidden property="sch041ToMonth" />
<html:hidden property="sch041ToDay" />
<html:hidden property="sch041FrHour" />
<html:hidden property="sch041FrMin" />
<html:hidden property="sch041ToHour" />
<html:hidden property="sch041ToMin" />
<html:hidden property="sch041Bgcolor" />
<html:hidden property="sch041Title" />
<html:hidden property="sch041Value" />
<html:hidden property="sch041Biko" />
<html:hidden property="sch041Public" />
<html:hidden property="sch041Edit" />
<html:hidden property="sch041TimeKbn" />
<html:hidden property="sch041BatchRef" />
<html:hidden property="sch041GroupSid" />
<html:hidden property="sch041ReserveGroupSid" />

<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />

<html:hidden property="sch040contact" />
<html:hidden property="sch041contact" />
<logic:notEmpty name="sch041knForm" property="sch040CompanySid">
  <logic:iterate id="coId" name="sch041knForm" property="sch040CompanySid">
    <input type="hidden" name="sch040CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<logic:notEmpty name="sch041knForm" property="sch040CompanyBaseSid">
  <logic:iterate id="coId" name="sch041knForm" property="sch040CompanyBaseSid">
    <input type="hidden" name="sch040CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch040AddressId">
  <logic:iterate id="addressId" name="sch041knForm" property="sch040AddressId">
    <input type="hidden" name="sch040AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch041CompanySid">
  <logic:iterate id="coId" name="sch041knForm" property="sch041CompanySid">
    <input type="hidden" name="sch041CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch041CompanyBaseSid">
  <logic:iterate id="coId" name="sch041knForm" property="sch041CompanyBaseSid">
    <input type="hidden" name="sch041CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch041AddressId">
  <logic:iterate id="addressId" name="sch041knForm" property="sch041AddressId">
    <input type="hidden" name="sch041AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch041Dweek" scope="request">
<logic:iterate id="selectWeek" name="sch041knForm" property="sch041Dweek" scope="request">
  <input type="hidden" name="sch041Dweek" value="<bean:write name="selectWeek" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="sch041knForm" property="sch010SelectUsrKbn" /><bean:write name="sch041knForm" property="cmd" />">

<!--　BODY -->
<logic:notEmpty name="sch041knForm" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="sch041knForm" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch041SvUsers" scope="request">
<logic:iterate id="eulBean" name="sch041knForm" property="sch041SvUsers" scope="request">
<input type="hidden" name="sch041SvUsers" value="<bean:write name="eulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="sch041SvReserve" scope="request">
<logic:iterate id="ersBean" name="sch041knForm" property="sch041SvReserve" scope="request">
<input type="hidden" name="sch041SvReserve" value="<bean:write name="ersBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch041knForm" property="svReserveUsers" scope="request">
<logic:iterate id="rsBean" name="sch041knForm" property="svReserveUsers" scope="request">
<input type="hidden" name="svReserveUsers" value="<bean:write name="rsBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:equal name="sch041knForm" property="sch010SelectUsrKbn" value="1">
<html:hidden property="sch040Public" />
</logic:equal>

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
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

        <logic:equal name="sch041knForm" property="cmd" value="add">
        <input type="hidden" name="CMD" value="041kn_ok">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('041kn_commit', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041kn_back', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
        </logic:equal>

        <logic:equal name="sch041knForm" property="cmd" value="edit">
        <input type="hidden" name="CMD" value="041kn_ok">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('041kn_commit', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041kn_back', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
        </logic:equal>

        <logic:equal name="sch041knForm" property="cmd" value="del">
          <input type="hidden" name="CMD" value="041kn_ok">
          <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('041kn_del_ok', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041kn_back', 'edit');">
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
    <bean:define id="tdInColor" value="" />
    <% String[] tdColors = new String[] {"td_wt", "td_sch_type2"}; %>
    <% String[] tdInColors = new String[] {"td_sch_type3", "td_sch_type4"}; %>
    <logic:notEqual name="sch041knForm" property="sch010SelectUsrKbn" value="0">
    <% tdColor = tdColors[1]; %>
    <% tdInColor = tdInColors[1]; %>
    </logic:notEqual>
    <logic:equal name="sch041knForm" property="sch010SelectUsrKbn" value="0">
    <% tdColor = tdColors[0]; %>
    <% tdInColor = tdInColors[0]; %>
    </logic:equal>


    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="schedule.4" /></span></td>
    <td class="<%= tdColor %>" align="left" width="80%">
    <logic:notEqual name="sch041knForm" property="sch010SelectUsrKbn" value="0">
    <span id="lt"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0"></span>
    </logic:notEqual>
    <span class="text_base"><bean:write name="sch041knForm" property="sch040UsrName" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.setting" /><gsmsg:write key="cmn.date2" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
      <table>

      <logic:notEqual name="sch041knForm" property="cmd" value="del">
      <tr><td colspan="3"><span class="text_r1"><gsmsg:write key="schedule.138" /></span></td></tr>
      </logic:notEqual>
      <logic:equal name="sch041knForm" property="cmd" value="del">
      <tr><td colspan="3"><span class="text_r1"><gsmsg:write key="schedule.139" /></span></td></tr>
      </logic:equal>
      <tr>

      <logic:equal name="sch041knForm" property="cmd" value="add">
      <td colspan="3" class="<%= tdInColor %>" width="100%" align="left" valign="top" nowrap>
      <logic:notEmpty name="sch041knForm" property="sch041knAftDateList" scope="request">
      <logic:iterate id="aftDate" name="sch041knForm" property="sch041knAftDateList" scope="request">
        <span class="text_base2"><bean:write name="aftDate" scope="page"/></span><br>
      </logic:iterate>
      </logic:notEmpty>
      </td>
      </logic:equal>

      <logic:equal name="sch041knForm" property="cmd" value="edit">
      <logic:notEmpty name="sch041knForm" property="sch041knBefDateList" scope="request">
      <td class="<%= tdInColor %>" width="0%" valign="top" align="left" nowrap>
      【<gsmsg:write key="reserve.rsv111kn.7" />】<br>
      <logic:iterate id="befDate" name="sch041knForm" property="sch041knBefDateList" scope="request">
        <span class="text_base"><bean:write name="befDate" scope="page"/></span><br>
      </logic:iterate>
      </td>
      </logic:notEmpty>

      <td class="<%= tdInColor %>" width="0%" align="center"><img src="../schedule/images/arrow_east.gif" border="0"></td>

      <td class="<%= tdInColor %>" width="100%" align="left" valign="top" nowrap>
      <logic:notEmpty name="sch041knForm" property="sch041knAftDateList" scope="request">
      【<gsmsg:write key="reserve.rsv111kn.8" />】<br>
      <logic:iterate id="aftDate" name="sch041knForm" property="sch041knAftDateList" scope="request">
        <span class="text_base"><bean:write name="aftDate" scope="page"/></span><br>
      </logic:iterate>
      </logic:notEmpty>
      </td>
      </logic:equal>

      <logic:equal name="sch041knForm" property="cmd" value="del">
      <logic:notEmpty name="sch041knForm" property="sch041knBefDateList" scope="request">
      <td class="<%= tdInColor %>" width="0%" valign="top" align="left" nowrap>
      <logic:iterate id="befDate" name="sch041knForm" property="sch041knBefDateList" scope="request">
        <span class="text_base"><bean:write name="befDate" scope="page"/></span><br>
      </logic:iterate>
      </td>
      </logic:notEmpty>
      </logic:equal>
      </tr>

      <logic:notEqual name="sch041knForm" property="cmd" value="del">
      <tr>
      <td colspan="3" class="<%= tdInColor %>">
      <logic:equal name="sch041knForm" property="sch041TranKbn" value="9">
      <span class="text_r1"><gsmsg:write key="schedule.sch041kn.1" /></span>
      </logic:equal>
      <logic:equal name="sch041knForm" property="sch041TranKbn" value="0">
      <span class="text_r1"><gsmsg:write key="schedule.sch041kn.2" /></span>
      </logic:equal>
      <logic:equal name="sch041knForm" property="sch041TranKbn" value="1">
      <span class="text_r1"><gsmsg:write key="schedule.sch041kn.3" /></span>
      </logic:equal>
      <logic:equal name="sch041knForm" property="sch041TranKbn" value="2">
      <span class="text_r1"><gsmsg:write key="schedule.sch041kn.4" /></span>
      </logic:equal>
      </td>
      </tr>
      </logic:notEqual>

      </table>
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch041.8" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
    <span class="text_base">
    <logic:notEqual name="sch041knForm" property="cmd" value="del">
    <bean:write name="sch041knForm" property="sch041knFromDate" />
    ～
    <bean:write name="sch041knForm" property="sch041knToDate" />
    </logic:notEqual>
    <logic:equal name="sch041knForm" property="cmd" value="del">
    <bean:write name="sch041knForm" property="sch041knDelFrDate" />
    ～
    <bean:write name="sch041knForm" property="sch041knDelToDate" />
    </logic:equal>
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.time" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">

    <logic:notEqual name="sch041knForm" property="cmd" value="del">
    <logic:equal name="sch041knForm" property="sch041TimeKbn" value="0">
    <bean:write name="sch041knForm" property="sch041knFromTime" />
    ～
    <bean:write name="sch041knForm" property="sch041knToTime" />
    </logic:equal>

    </logic:notEqual>

    <logic:equal name="sch041knForm" property="cmd" value="del">
    <logic:equal name="sch041knForm" property="sch041TimeKbn" value="0">
    <bean:write name="sch041knForm" property="sch041knDelFrTime" />
    ～
    <bean:write name="sch041knForm" property="sch041knDelToTime" />
    </logic:equal>
    </logic:equal>

    </span>
    </td>
    </tr>

    <logic:equal name="sch041knForm" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="schedule.14" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <table class="tl0" width="100%">
      <tr>
      <td>
        <logic:notEmpty name="sch041knForm" property="sch041CompanyList">
        <table width="100%" class="tl0 text_base">
        <logic:iterate id="companyData" name="sch041knForm" property="sch041CompanyList">
          <tr><td colspan="2">&nbsp</td></tr>
          <tr>
          <td><span class="text_company"><bean:write name="companyData" property="companyName" /></span></td>
          </tr>
          <tr>
          <td>
          <logic:notEmpty name="companyData" property="addressDataList">
          <logic:iterate id="addressData" name="companyData" property="addressDataList">
            &nbsp;&nbsp;<span class="text_tantou"><bean:write name="addressData" property="adrName" /></span><br>
          </logic:iterate>
          </logic:notEmpty>
          </td>
          </tr>
        </logic:iterate>
        </table>
        </logic:notEmpty>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td align="left" class="<%= tdColor %>">

    <logic:notEqual name="sch041knForm" property="cmd" value="del">

    <logic:equal name="sch041knForm" property="sch041Bgcolor" value="1">
    <span class="sc_title_1">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Bgcolor" value="2">
    <span class="sc_title_2">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Bgcolor" value="3">
    <span class="sc_title_3">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Bgcolor" value="4">
    <span class="sc_title_4">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Bgcolor" value="5">
    <span class="sc_title_5">
    </logic:equal>
    <bean:write name="sch041knForm" property="sch041Title" />

    </logic:notEqual>

    <logic:equal name="sch041knForm" property="cmd" value="del">

    <logic:equal name="sch041knForm" property="sch041knDelBgcolor" value="1">
    <span class="sc_title_1">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelBgcolor" value="2">
    <span class="sc_title_2">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelBgcolor" value="3">
    <span class="sc_title_3">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelBgcolor" value="4">
    <span class="sc_title_4">
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelBgcolor" value="5">
    <span class="sc_title_5">
    </logic:equal>
    <bean:write name="sch041knForm" property="sch041knDelTitle" />
    </logic:equal>

    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <logic:notEqual name="sch041knForm" property="cmd" value="del">
    <bean:write name="sch041knForm" property="sch041knDspValue" filter="false" />
    </logic:notEqual>
    <logic:equal name="sch041knForm" property="cmd" value="del">
    <bean:write name="sch041knForm" property="sch041knDelValue" filter="false" />
    </logic:equal>
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <logic:notEqual name="sch041knForm" property="cmd" value="del">
    <bean:write name="sch041knForm" property="sch041knDspBiko" filter="false"/>
    </logic:notEqual>
    <logic:equal name="sch041knForm" property="cmd" value="del">
    <bean:write name="sch041knForm" property="sch041knDelBiko" filter="false"/>
    </logic:equal>
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span></td>
    <td align="left" class="<%= tdColor %>">

    <logic:notEqual name="sch041knForm" property="cmd" value="del">
    <logic:equal name="sch041knForm" property="sch041Edit" value="0">
    <span class="text_base"><gsmsg:write key="cmn.nolimit" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Edit" value="1">
    <span class="text_base"><gsmsg:write key="cmn.only.principal.or.registant" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Edit" value="2">
    <span class="text_base"><gsmsg:write key="cmn.only.affiliation.group.membership" /></span>
    </logic:equal>
    </logic:notEqual>
    <logic:equal name="sch041knForm" property="cmd" value="del">
    <logic:equal name="sch041knForm" property="sch041knDelEdit" value="0">
    <span class="text_base"><gsmsg:write key="cmn.nolimit" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelEdit" value="1">
    <span class="text_base"><gsmsg:write key="cmn.only.principal.or.registant" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelEdit" value="2">
    <span class="text_base"><gsmsg:write key="cmn.only.affiliation.group.membership" /></span>
    </logic:equal>
    </logic:equal>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.21" /></span></td>
    <td align="left" class="<%= tdColor %>">

    <logic:notEqual name="sch041knForm" property="cmd" value="del">
    <logic:equal name="sch041knForm" property="sch041Public" value="0">
    <span class="text_base"><gsmsg:write key="cmn.public" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Public" value="1">
    <span class="text_base"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Public" value="2">
    <span class="text_base"><gsmsg:write key="schedule.5" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Public" value="3">
    <span class="text_base"> <gsmsg:write key="schedule.28" /></span>
    </logic:equal>
    </logic:notEqual>
    <logic:equal name="sch041knForm" property="cmd" value="del">
    <logic:equal name="sch041knForm" property="sch041knDelPublic" value="0">
    <span class="text_base"><gsmsg:write key="cmn.public" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelPublic" value="1">
    <span class="text_base"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041knDelPublic" value="2">
    <span class="text_base"><gsmsg:write key="schedule.5" /></span>
    </logic:equal>
    <logic:equal name="sch041knForm" property="sch041Public" value="3">
    <span class="text_base"> <gsmsg:write key="schedule.28" /></span>
    </logic:equal>
    </logic:equal>

    </td>
    </tr>

    <!-- グループスケジュール 非表示部分 START -->
    <logic:notEqual name="sch041knForm" property="sch010SelectUsrKbn" value="1">
    <logic:notEmpty name="sch041knForm" property="sch041knSelectUsrList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="schedule.117" /></span></td>
    <td align="left" class="<%= tdColor %>">

      <logic:notEqual name="sch041knForm" property="cmd" value="del">
      <span class="text_r1"><gsmsg:write key="schedule.140" /></span>
      </logic:notEqual>
      <logic:equal name="sch041knForm" property="cmd" value="del">
      <span class="text_r1"><gsmsg:write key="schedule.141" /></span>
      </logic:equal>
      <logic:iterate id="selectUsrBean" name="sch041knForm" property="sch041knSelectUsrList" scope="request">
      <br><span class="text_base"><bean:write name="selectUsrBean" property="usiSei" scope="page"/>　<bean:write name="selectUsrBean" property="usiMei" scope="page"/></span>
      </logic:iterate><br>
    </td>
    </tr>
    </logic:notEmpty>

    <!-- グループスケジュール 非表示部分 END -->
    </logic:notEqual>

    <logic:notEmpty name="sch041knForm" property="sch041knReserveList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.reserve" /></span></td>
    <td align="left" class="<%= tdColor %>">

      <logic:notEqual name="sch041knForm" property="cmd" value="del">
      <span class="text_r1"><gsmsg:write key="schedule.142" /></span>
      </logic:notEqual>
      <logic:equal name="sch041knForm" property="cmd" value="del">
      <span class="text_r1"><gsmsg:write key="schedule.143" /></span>
      </logic:equal>

      <logic:iterate id="selectResBean" name="sch041knForm" property="sch041knReserveList" scope="request">
      <br><span class="text_base"><bean:write name="selectResBean" property="rsdName" scope="page"/></span>
      </logic:iterate><br>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <span class="text_base">
      <logic:notEqual name="sch041knForm" property="sch040AddUsrJkbn" value="9">
      <bean:write name="sch041knForm" property="sch040AddUsrName" />
      </logic:notEqual>
      <logic:equal name="sch041knForm" property="sch040AddUsrJkbn" value="9">
      <del>
      <bean:write name="sch041knForm" property="sch040AddUsrName" />
      </del>
      </logic:equal>
      </span>
    </td>
    </tr>

    </table>


    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" width="100%">
    <tr>
    <td align="left">
    </td>
    <td align="right">

    <logic:equal name="sch041knForm" property="cmd" value="add">
    <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('041kn_commit', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041kn_back', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
    </logic:equal>

    <logic:equal name="sch041knForm" property="cmd" value="edit">
    <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush('041kn_commit', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041kn_back', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
    </logic:equal>

    <logic:equal name="sch041knForm" property="cmd" value="del">
    <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('041kn_del_ok', '<bean:write name="sch041knForm" property="sch040BtnCmd" />');">
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('041kn_back', 'edit');">
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>