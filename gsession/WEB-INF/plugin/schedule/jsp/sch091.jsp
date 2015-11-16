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
<title>[GroupSession] <gsmsg:write key="cmn.preferences.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch060.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/schedule/sch091">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch010searchWord" />
<html:hidden property="sch020SelectUsrSid" />
<html:hidden property="sch030FromHour" />

<html:hidden property="sch100PageNum" />
<html:hidden property="sch100Slt_page1" />
<html:hidden property="sch100Slt_page2" />
<html:hidden property="sch100OrderKey1" />
<html:hidden property="sch100SortKey1" />
<html:hidden property="sch100OrderKey2" />
<html:hidden property="sch100SortKey2" />

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
<logic:notEmpty name="sch091Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch091Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch091Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch091Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch091Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch091Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch091Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch091Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch091Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch091Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:equal name="sch091Form" property="sch091EditFlg" value="false">
  <html:hidden property="sch091Edit" />
</logic:equal>
<html:hidden property="sch091initFlg" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch091.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('sch091kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('sch091back');"></td>
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
      <span class="text_bb1"><gsmsg:write key="cmn.time" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_bb1"><gsmsg:write key="cmn.starttime" />：</span>
      <!-- 時 -->
      <html:select property="sch091DefFrH">
        <html:optionsCollection name="sch091Form" property="sch091HourLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <!-- 分 -->
      <html:select property="sch091DefFrM">
        <html:optionsCollection name="sch091Form" property="sch091MinuteLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    <br>
      <span class="text_bb1"><gsmsg:write key="cmn.endtime" />：</span>
      <!-- 時 -->
      <html:select property="sch091DefToH">
        <html:optionsCollection name="sch091Form" property="sch091HourLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <!-- 分 -->
      <html:select property="sch091DefToM">
        <html:optionsCollection name="sch091Form" property="sch091MinuteLabel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="schedule.10" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">


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
    <logic:iterate id="msgStr" name="sch091Form" property="sch091ColorMsgList" indexId="msgId" type="java.lang.String">
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

    <span class="sc_block_color_1"><html:radio name="sch091Form" property="sch091Fcolor" value="1" /></span><span class="text_base"><%= colorMsg1 %></span>
    <span class="sc_block_color_2"><html:radio name="sch091Form" property="sch091Fcolor" value="2" /></span><span class="text_base"><%= colorMsg2 %></span>
    <span class="sc_block_color_3"><html:radio name="sch091Form" property="sch091Fcolor" value="3" /></span><span class="text_base"><%= colorMsg3 %></span>
    <span class="sc_block_color_4"><html:radio name="sch091Form" property="sch091Fcolor" value="4" /></span><span class="text_base"><%= colorMsg4 %></span>
    <span class="sc_block_color_5"><html:radio name="sch091Form" property="sch091Fcolor" value="5" /></span><span class="text_base"><%= colorMsg5 %></span>

    <logic:equal name="sch091Form" property="sch091colorKbn" value="1">
      <div><img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px"></div>
      <span class="sc_block_color_6"><html:radio name="sch091Form" property="sch091Fcolor" value="6" /></span><span class="text_base"><%= colorMsg6 %></span>
      <span class="sc_block_color_7"><html:radio name="sch091Form" property="sch091Fcolor" value="7" /></span><span class="text_base"><%= colorMsg7 %></span>
      <span class="sc_block_color_8"><html:radio name="sch091Form" property="sch091Fcolor" value="8" /></span><span class="text_base"><%= colorMsg8 %></span>
      <span class="sc_block_color_9"><html:radio name="sch091Form" property="sch091Fcolor" value="9" /></span><span class="text_base"><%= colorMsg9 %></span>
      <span class="sc_block_color_10"><html:radio name="sch091Form" property="sch091Fcolor" value="10" /></span><span class="text_base"><%= colorMsg10 %></span>
    </logic:equal>

    </td>
    </tr>

    <!-- 編集権限 -->
    <logic:equal name="sch091Form" property="sch091EditFlg" value="true">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">
      <html:radio name="sch091Form" property="sch091Edit" styleId="sch091Edit0" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_NONE) %>" /><span class="text_base"><label for="sch091Edit0"><gsmsg:write key="cmn.nolimit" /></label></span>&nbsp;
      <html:radio name="sch091Form" property="sch091Edit" styleId="sch091Edit1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_OWN) %>" /><span class="text_base"><label for="sch091Edit1"><gsmsg:write key="cmn.only.principal.or.registant" /></label>&nbsp;</span>
      <html:radio name="sch091Form" property="sch091Edit" styleId="sch091Edit2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_GROUP) %>" /><span class="text_base"><label for="sch091Edit2"><gsmsg:write key="cmn.only.affiliation.group.membership" /></label>&nbsp;</span>
    </td>
    </tr>
    </logic:equal>

    <!-- 公開区分 -->
    <logic:equal name="sch091Form" property="sch091PublicFlg" value="true">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.public" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">
      <html:radio name="sch091Form" property="sch091PubFlg" styleId="sch091PubFlg0" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.DSP_PUBLIC) %>" /><span class="text_base"><label for="sch091PubFlg0"><gsmsg:write key="cmn.public" /></label></span>&nbsp;
      <html:radio name="sch091Form" property="sch091PubFlg" styleId="sch091PubFlg1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.DSP_NOT_PUBLIC) %>" /><span class="text_base"><label for="sch091PubFlg1"><gsmsg:write key="cmn.private" /></label>&nbsp;</span>
      <html:radio name="sch091Form" property="sch091PubFlg" styleId="sch091PubFlg2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.DSP_YOTEIARI) %>" /><span class="text_base"><label for="sch091PubFlg2"><gsmsg:write key="schedule.5" /></label>&nbsp;</span>
      <html:radio name="sch091Form" property="sch091PubFlg" styleId="sch091PubFlg3" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.DSP_BELONG_GROUP) %>" /><span class="text_base2"><label for="sch091PubFlg3"><gsmsg:write key="schedule.28" /></label></span>
    </td>
    </tr>
    </logic:equal>

    <!-- 同時修正 -->
    <logic:equal name="sch091Form" property="sch091SameFlg" value="true">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="schedule.32" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt">
      <html:radio name="sch091Form" property="sch091Same" styleId="sch091Same0" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SAME_EDIT_ON) %>" /><span class="text_base"><label for="sch091Same0"><gsmsg:write key="schedule.34" /></label></span>&nbsp;
      <html:radio name="sch091Form" property="sch091Same" styleId="sch091Same1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SAME_EDIT_OFF) %>" /><span class="text_base"><label for="sch091Same1"><gsmsg:write key="schedule.33" /></label></span>&nbsp;
    </td>
    </tr>
    </logic:equal>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('sch091kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('sch091back');">
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