<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-bean.tld" prefix="bean2" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.sch040kn.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch040kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/schedule/sch040kn">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="helpPrm" value="<bean:write name="sch040knForm" property="sch010SelectUsrKbn" />">
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
<html:hidden property="sch040Edit" />
<html:hidden property="sch040TimeKbn" />

<html:hidden property="schWeekDate" />
<html:hidden property="schDailyDate" />
<html:hidden property="sch100PageNum" />
<html:hidden property="sch100Slt_page1" />
<html:hidden property="sch100Slt_page2" />
<html:hidden property="sch100OrderKey1" />
<html:hidden property="sch100SortKey1" />
<html:hidden property="sch100OrderKey2" />
<html:hidden property="sch100SortKey2" />
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
<html:hidden property="sch100SvOrderKey1" />
<html:hidden property="sch100SvSortKey1" />
<html:hidden property="sch100SvOrderKey2" />
<html:hidden property="sch100SvSortKey2" />

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

<logic:notEmpty name="sch040knForm" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch040knForm" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch040knForm" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch040knForm" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch040knForm" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="sch040knForm" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch040knForm" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch040knForm" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch040knForm" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch040knForm" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch040knForm" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch040knForm" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
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
    <td width="100%" class="header_white_bg_text">

    <!-- タイトル -->
    <logic:equal name="sch040knForm" property="cmd" value="edit">[ <gsmsg:write key="schedule.sch040kn.1" /> ]</td></logic:equal>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

        <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('040kn_back', 'edit');">

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
    <logic:notEqual name="sch040knForm" property="sch010SelectUsrKbn" value="0">
    <% tdColor = tdColors[1]; %>
    </logic:notEqual>
    <logic:equal name="sch040knForm" property="sch010SelectUsrKbn" value="0">
    <% tdColor = tdColors[0]; %>
    </logic:equal>

    <logic:equal name="sch040knForm" property="sch010SelectUsrKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.USER_KBN_USER) %>">
    <logic:notEqual name="sch040knForm" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_NORMAL) %>">
    <div style="padding-bottom:10px;">
    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">出欠確認</span></td>
    <td align="left" class="<%= tdColor %>">
    <logic:notEqual name="sch040knForm" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_ANSWER) %>">
      <div style="padding-top:10px;padding-bottom:10px;">
      <logic:equal name="sch040knForm" property="sch040AttendKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_KBN_NO) %>" ><span class="text_base2">確認しない</span></logic:equal>
      <logic:equal name="sch040knForm" property="sch040AttendKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_KBN_YES) %>" ><span class="text_base2">確認する</label></logic:equal>
      </div>
    </logic:notEqual>

    <logic:equal name="sch040knForm" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_ANSWER) %>">
    <div style="padding-top:10px;padding-bottom:10px;">
    <logic:equal name="sch040knForm" property="sch040AttendAnsKbn" value="0" ><span class="text_base2">未回答</span></logic:equal>
    <logic:equal name="sch040knForm" property="sch040AttendAnsKbn" value="1" ><span class="text_base2">出席</span></logic:equal>
    <logic:equal name="sch040knForm" property="sch040AttendAnsKbn" value="2" ><span class="text_base2">欠席</span></logic:equal>
    </div>
    </logic:equal>
    <div>
    <table class="tl0 table_td_border" width="50%" cellpadding="0" cellspacing="0">
      <tr class="table_bg_7D91BD">
      <th width="40%" class="table_bg_7D91BD" nowrap>回答日時</th>
      <th width="30%" class="table_bg_7D91BD" nowrap>氏名</th>
      <th width="30%" class="table_bg_7D91BD" nowrap>回答内容</th>
      </tr>
      <logic:notEmpty name="sch040knForm" property="sch040AttendAnsList">
      <logic:iterate id="attendMdl" name="sch040knForm" property="sch040AttendAnsList" indexId="idx">
        <% String[] typeClass = new String[] {"td_type1", "td_type_usr"}; %>
        <% String tdType = typeClass[(idx.intValue() % 2)]; %>
        <tr class="<%= tdType %>">
        <td align="center" nowrap>
        <logic:equal name="attendMdl" property="attendAnsKbn" value="0">
        －
        </logic:equal>
        <logic:notEqual name="attendMdl" property="attendAnsKbn" value="0">
          <bean:write name="attendMdl" property="attendAnsDate" />
        </logic:notEqual>
        </td>
        <td align="left" nowrap>
          <bean:write name="attendMdl" property="attendAnsUsrName" />
        </td>
        <td align="center" nowrap>
          <logic:equal name="attendMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NONE) %>">未回答</logic:equal>
          <logic:equal name="attendMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_YES) %>"><span class="attend_text_yes">出席</span></logic:equal>
          <logic:equal name="attendMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NO) %>"><span class="attend_text_no">欠席</span></logic:equal>
        </td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>
    </table>
    <logic:equal name="sch040knForm" property="sch040AttendLinkFlg" value="1">
    <span class="all_disp_txt" id="all_disp">全て表示する</span>
    </logic:equal>
    </div>
    </td>
    </tr>
    </table>
    </div>
    </logic:notEqual>
    </logic:equal>

    <table width="100%" class="tl0" border="0" cellpadding="5" id="selectionSearchArea">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="schedule.4" /></span></td>
    <td class="<%= tdColor %>" align="left" width="80%">
    <logic:notEqual name="sch040knForm" property="sch010SelectUsrKbn" value="0">
    <span id="lt"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0"></span>
    </logic:notEqual>
    <span class="text_base"><bean:write name="sch040knForm" property="sch040UsrName" /></span>
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.16" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
    <span class="text_base">
    <bean:write name="sch040knForm" property="sch040knFromDate" />
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.27" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean:write name="sch040knForm" property="sch040knToDate" />
    </span>
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean:write name="sch040knForm" property="sch040Title" />
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.10" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="1">
    <span class="sc_block_color_1">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="2">
    <span class="sc_block_color_2">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="3">
    <span class="sc_block_color_3">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="4">
    <span class="sc_block_color_4">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="5">
    <span class="sc_block_color_5">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="6">
    <span class="sc_block_color_6">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="7">
    <span class="sc_block_color_7">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="8">
    <span class="sc_block_color_8">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="9">
    <span class="sc_block_color_9">&nbsp;&nbsp;</span>
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Bgcolor" value="10">
    <span class="sc_block_color_10">&nbsp;&nbsp;</span>
    </logic:equal>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean2:writeText name="sch040knForm" property="sch040Value" crlf="false" />
    </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean2:writeText name="sch040knForm" property="sch040Biko" crlf="false" />
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.21" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <logic:equal name="sch040knForm" property="sch040Public" value="0">
    <gsmsg:write key="cmn.public" />
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Public" value="1">
    <gsmsg:write key="cmn.private" />
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Public" value="2">
    <gsmsg:write key="schedule.5" />
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Public" value="3">
    <gsmsg:write key="schedule.28" />
    </logic:equal>
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.sch040kn.2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <logic:equal name="sch040knForm" property="sch040Edit" value="0">
    <gsmsg:write key="cmn.notset" />
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Edit" value="1">
    <gsmsg:write key="schedule.sch040kn.3" />
    </logic:equal>
    <logic:equal name="sch040knForm" property="sch040Edit" value="2">
    <gsmsg:write key="cmn.affiliation.group" />
    </logic:equal>
    </span>
    </td>
    </tr>

<!-- 出欠確認 -->
    <logic:equal name="sch040knForm" property="sch010SelectUsrKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.USER_KBN_USER) %>">
    <logic:equal name="sch040knForm" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_NORMAL) %>">
       <tr>
       <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">出欠確認</span></td>
       <td align="left" class="<%= tdColor %>"><span class="text_base">確認しない</span></td>
       </tr>
    </logic:equal>
    </logic:equal>

<!-- 同時登録 -->
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.117" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <table class="tl0" width="100%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base">
      <logic:notEmpty name="sch040knForm" property="sch040AddedUsrLabel">
      <logic:iterate id="aurBean" name="sch040knForm" property="sch040AddedUsrLabel" scope="request">
      <span class="text_base"><bean:write name="aurBean" property="usiSei" scope="page"/>　<bean:write name="aurBean" property="usiMei" scope="page"/></span>
      <br>
      </logic:iterate>
      </logic:notEmpty>
      </span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="<%= tdColor %>">
      <table class="tl0" width="100%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base">
      <logic:notEqual name="sch040knForm" property="sch040AddUsrJkbn" value="9">
      <bean:write name="sch040knForm" property="sch040AddUsrName" />
      </logic:notEqual>
      <logic:equal name="sch040knForm" property="sch040AddUsrJkbn" value="9">
      <del>
      <bean:write name="sch040knForm" property="sch040AddUsrName" />
      </del>
      </logic:equal>
      </span>
      </td>
      <td width="40%" align="left" nowrap>
      <span class="text_base"><bean:write name="sch040knForm" property="sch040AddDate" filter="false" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>


    </table>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('040kn_back', 'edit');">
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
<span id="tooltip_search" class="tooltip_search"></span>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>