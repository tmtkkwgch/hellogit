<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String holNone = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.HOL_KBN_UNSELECT); %>
<% String holKetu = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.HOL_KBN_KEKKIN); %>
<% String holKeit = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.HOL_KBN_KEITYO); %>
<% String holYuuk = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.HOL_KBN_YUUKYU); %>
<% String holDaik = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.HOL_KBN_DAIKYU); %>
<% String holSono = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.HOL_KBN_SONOTA); %>

<% String chkNone = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.CHK_KBN_UNSELECT); %>
<% String chikoku = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.CHK_KBN_SELECT); %>
<% String souNone = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SOU_KBN_UNSELECT); %>
<% String soutai = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SOU_KBN_SELECT); %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../timecard/css/timecard.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../timecard/js/tcd020.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="tcd.tcd020.07" /></title>
</head>


<body class="body_03" onload="init();">

<html:form action="/timecard/tcd020" onsubmit="return false" >
<input type="hidden" name="CMD" value="">
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<html:hidden property="editDay" />

<logic:notEmpty name="tcd020Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd020Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usrKbn" />
<html:hidden property="tcd020LockFlg" />
<html:hidden property="tcd020LockStrike" />
<html:hidden property="tcd020LockBiko" />
<html:hidden property="tcd020LockLate" />
<html:hidden property="tcd020LockHoliday" />
<html:hidden property="pluralFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../timecard/images/header_timecard_01.gif" border="0" alt="<gsmsg:write key="tcd.50" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="tcd.50" /></span></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.edit" /> ]</td>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="tcd.50" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
        <input type="button" value="<gsmsg:write key="cmn.change" />" class="btn_edit_n1" onClick="buttonPush('submit')">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back')">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.name4" /></span></th>
    <td align="left" class="td_type1" width="80%">
      <bean:write name="tcd020Form" property="tcd020Name"/>
    </td>
    </tr>
    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.date2" /></span></th>

    <td align="left" class="td_type1" width="80%">
    <b><html:hidden name="tcd020Form" property="tcd020Date" write="true" /></b>
    </td>
    </tr>

    <logic:equal name="tcd020Form" property="pluralFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.PLURAL_FLG_SINGLE) %>">
    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.21" /></span></th>

    <td align="left" class="td_type1" width="80%">

      <logic:equal name="tcd020Form" property="tcd020LockStrike" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>">
      <html:select name="tcd020Form" property="tcd020StrikeInHour">
      <html:optionsCollection name="tcd020Form" property="tcd020HourLabel" label="label" value="value" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <html:select name="tcd020Form" property="tcd020StrikeInMinute">
      <html:optionsCollection name="tcd020Form" property="tcd020StrikeMinuteLabel" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
      <input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base1s" onClick="clearValue('tcd020StrikeInHour', 'tcd020StrikeInMinute')">
&nbsp;<gsmsg:write key="tcd.153" />&nbsp;
      <html:select name="tcd020Form" property="tcd020StrikeOutHour" >
      <html:optionsCollection name="tcd020Form" property="tcd020HourLabel" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <html:select name="tcd020Form" property="tcd020StrikeOutMinute">
      <html:optionsCollection name="tcd020Form" property="tcd020StrikeMinuteLabel" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
      <input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base1s" onClick="clearValue('tcd020StrikeOutHour', 'tcd020StrikeOutMinute')">
      </logic:equal>

      <logic:equal name="tcd020Form" property="tcd020LockStrike" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>">
      <bean:write name="tcd020Form" property="tcd020StrikeTimeStr" />
      </logic:equal>

    </td>
    </tr>
    </logic:equal>

    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.28" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></th>

    <td align="left" class="td_type1" width="80%">

      <logic:equal name="tcd020Form" property="tcd020LockFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>">
      <html:select name="tcd020Form" property="tcd020InHour">
      <html:optionsCollection name="tcd020Form" property="tcd020HourLabel" label="label" value="value" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <html:select name="tcd020Form" property="tcd020InMinute">
      <html:optionsCollection name="tcd020Form" property="tcd020MinuteLabel" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
      <input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base1s" onClick="clearValue('tcd020InHour', 'tcd020InMinute')">
      </logic:equal>

      <logic:equal name="tcd020Form" property="tcd020LockFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>">
      <bean:write name="tcd020Form" property="tcd020InTimeStr" />
      </logic:equal>

    </td>
    </tr>


    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.24" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></th>
    <td align="left" class="td_type1" width="80%">

      <logic:equal name="tcd020Form" property="tcd020LockFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>">
      <html:select name="tcd020Form" property="tcd020OutHour" >
      <html:optionsCollection name="tcd020Form" property="tcd020HourLabel" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
      <html:select name="tcd020Form" property="tcd020OutMinute">
      <html:optionsCollection name="tcd020Form" property="tcd020MinuteLabel" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
      <input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base1s" onClick="clearValue('tcd020OutHour', 'tcd020OutMinute')">
      </logic:equal>

      <logic:equal name="tcd020Form" property="tcd020LockFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>">
      <bean:write name="tcd020Form" property="tcd020OutTimeStr" />
      </logic:equal>

    </td>
    </tr>

    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%">
    <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></th>
    <td align="left" class="td_wt" width="80%">
    <html:text name="tcd020Form" property="tcd020Biko" styleClass="text_base" style="width:635px;" maxlength="100" />
    </td>
    </tr>

    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd020.03" /></span></th>
    <td align="left" class="td_type1" width="80%">

    <logic:equal name="tcd020Form" property="tcd020LockLate" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>">
    <span class="text_bb1"><gsmsg:write key="tcd.tcd020.02" /><gsmsg:write key="wml.215" /></span>
    <logic:equal name="tcd020Form" property="tcd020ChkKbn" value="<%= chkNone %>"><gsmsg:write key="cmn.without.specifying" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020ChkKbn" value="<%= chikoku %>"><gsmsg:write key="tcd.18" /></logic:equal>
    <br>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd020.04" /><gsmsg:write key="wml.215" /></span>
    <logic:equal name="tcd020Form" property="tcd020SouKbn" value="<%= souNone %>"><gsmsg:write key="cmn.without.specifying" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020SouKbn" value="<%= soutai %>"><gsmsg:write key="tcd.22" /></logic:equal>
    </logic:equal>

    <logic:equal name="tcd020Form" property="tcd020LockLate" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>">
    <html:radio styleId="tcd020ChkKbn_0" name="tcd020Form" property="tcd020ChkKbn" value="<%= chkNone %>"/><label for="tcd020ChkKbn_0"><gsmsg:write key="cmn.without.specifying" /></label>
    <html:radio styleId="tcd020ChkKbn_1" name="tcd020Form" property="tcd020ChkKbn" value="<%= chikoku %>"/><label for="tcd020ChkKbn_1"><gsmsg:write key="tcd.18" /></label>
    <br>
    <html:radio styleId="tcd020SouKbn_0" name="tcd020Form" property="tcd020SouKbn" value="<%= souNone %>"/><label for="tcd020SouKbn_0"><gsmsg:write key="cmn.without.specifying" /></label>
    <html:radio styleId="tcd020SouKbn_1" name="tcd020Form" property="tcd020SouKbn" value="<%= soutai %>"/><label for="tcd020SouKbn_1"><gsmsg:write key="tcd.22" /></label>
    </logic:equal>

    </td>
    </tr>

    <tr>
    <th align="left" class="table_bg_A5B4E1" width="20%" nowrap>
    <span class="text_bb1"><gsmsg:write key="tcd.40" /></span></th>

    <td align="left" class="td_type1" width="80%">

    <logic:equal name="tcd020Form" property="tcd020LockHoliday" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.UNLOCK_FLG) %>">
    <span class="text_r1"><gsmsg:write key="tcd.tcd020.08" /></span><br><br>
    <html:radio styleId="tcd020HolKbn_0" name="tcd020Form" property="tcd020HolKbn" value="<%= holNone %>" onclick="holRadioChange()"/><label for="tcd020HolKbn_0"><gsmsg:write key="cmn.without.specifying" /></label>
    <html:radio styleId="tcd020HolKbn_1" name="tcd020Form" property="tcd020HolKbn" value="<%= holKetu %>" onclick="holRadioChange()"/><label for="tcd020HolKbn_1"><gsmsg:write key="tcd.34" /></label>
    <html:radio styleId="tcd020HolKbn_2" name="tcd020Form" property="tcd020HolKbn" value="<%= holKeit %>" onclick="holRadioChange()"/><label for="tcd020HolKbn_2"><gsmsg:write key="tcd.35" /></label>
    <html:radio styleId="tcd020HolKbn_3" name="tcd020Form" property="tcd020HolKbn" value="<%= holYuuk %>" onclick="holRadioChange()"/><label for="tcd020HolKbn_3"><gsmsg:write key="tcd.03" /></label>
    <html:radio styleId="tcd020HolKbn_4" name="tcd020Form" property="tcd020HolKbn" value="<%= holDaik %>" onclick="holRadioChange()"/><label for="tcd020HolKbn_4"><gsmsg:write key="tcd.19" /></label>
    <html:radio styleId="tcd020HolKbn_5" name="tcd020Form" property="tcd020HolKbn" value="<%= holSono %>" onclick="holRadioChange()"/><label for="tcd020HolKbn_5"><gsmsg:write key="cmn.other" /></label>
    <html:text name="tcd020Form" property="tcd020HolValue" styleClass="text_base" style="width:101px;" maxlength="10" /><br>
        <gsmsg:write key="tcd.tcd020.06" /><gsmsg:write key="wml.215" /><html:text name="tcd020Form" property="tcd020HolDays" styleClass="text_base" style="width:56px;" maxlength="5" />
    </logic:equal>

    <logic:equal name="tcd020Form" property="tcd020LockHoliday" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.LOCK_FLG) %>">
    <span class="text_bb1"><gsmsg:write key="tcd.40" /><gsmsg:write key="wml.215" /></span>
    <logic:equal name="tcd020Form" property="tcd020HolKbn" value="0"><gsmsg:write key="cmn.without.specifying" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020HolKbn" value="1"><gsmsg:write key="tcd.34" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020HolKbn" value="2"><gsmsg:write key="tcd.35" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020HolKbn" value="3"><gsmsg:write key="tcd.03" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020HolKbn" value="4"><gsmsg:write key="tcd.19" /></logic:equal>
    <logic:equal name="tcd020Form" property="tcd020HolKbn" value="5"><gsmsg:write key="cmn.other" />&nbsp;&nbsp;
    <bean:write name="tcd020Form" property="tcd020HolValue" />
    </logic:equal>
    <logic:notEqual name="tcd020Form" property="tcd020HolKbn" value="0">
    <br>
    <span class="text_bb1"><gsmsg:write key="tcd.tcd020.06" /><gsmsg:write key="wml.215" /></span>
    <bean:write name="tcd020Form" property="tcd020HolDays" />
    </logic:notEqual>

    </logic:equal>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.change" />" class="btn_edit_n1" onClick="buttonPush('submit')">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back')">
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