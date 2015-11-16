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
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch081.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/schedule/sch081">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/schedule/jsp/sch080_hiddenParams.jsp" %>

<logic:notEmpty name="sch081Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch081Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch081Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch081Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch081Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch081Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch081Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch081Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch081Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch081Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sch.preferences" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('sch081kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('sch081back');"></td>
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
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="schedule.123" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="schedule.124" /></span><br><br>
      <html:radio name="sch081Form" property="sch081Crange" styleId="sch081Crange0" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CRANGE_SHARE_ALL) %>" /><span class="text_base7"><label for="sch081Crange0"><gsmsg:write key="schedule.125" /></label></span>&nbsp;
      <html:radio name="sch081Form" property="sch081Crange" styleId="sch081Crange1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.CRANGE_SHARE_GROUP) %>" /><span class="text_base7"><label for="sch081Crange1"><gsmsg:write key="schedule.src.2" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="reserve.123" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="schedule.126" /></span><br><br>
      <html:radio name="sch081Form" property="sch081HourDiv" styleId="sch081HourDiv0" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.HOUR_DIVISION5) %>" /><span class="text_base7"><label for="sch081HourDiv0"><gsmsg:write key="reserve.rsv220.3" /></label></span>&nbsp;
      <html:radio name="sch081Form" property="sch081HourDiv" styleId="sch081HourDiv1" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.DF_HOUR_DIVISION) %>" /><span class="text_base7"><label for="sch081HourDiv1"><gsmsg:write key="reserve.rsv220.4" /></label></span>&nbsp;
      <html:radio name="sch081Form" property="sch081HourDiv" styleId="sch081HourDiv2" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.HOUR_DIVISION15) %>" /><span class="text_base7"><label for="sch081HourDiv2"><gsmsg:write key="reserve.rsv220.5" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="schedule.128" /><br><gsmsg:write key="cmn.comment" /></span></td>
    <td class="td_type1">
    <span class="text_base"><gsmsg:write key="schedule.127" /></span><br><br>
      <table class="tl0" width="100%" cellpadding="5">
      <tr><td><span class="sc_block_color_1">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment1" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr><td><span class="sc_block_color_2">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment2" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr><td><span class="sc_block_color_3">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment3" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr><td><span class="sc_block_color_4">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment4" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr><td><span class="sc_block_color_5">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment5" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr>
        <td>
          <html:radio name="sch081Form" property="sch081colorKbn" styleId="sch081colorKbn0" value="0" /><span class="text_base7"><label for="sch081colorKbn0"><gsmsg:write key="schedule.168" /></label></span>
          <html:radio name="sch081Form" property="sch081colorKbn" styleId="sch081colorKbn1" value="1" /><span class="text_base7"><label for="sch081colorKbn1"><gsmsg:write key="schedule.169" /></label></span>
        </td>
      </tr>
      <tr class="add_title_color display_none"><td><span class="sc_block_color_6">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment6" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr class="add_title_color display_none"><td><span class="sc_block_color_7">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment7" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr class="add_title_color display_none"><td><span class="sc_block_color_8">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment8" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr class="add_title_color display_none"><td><span class="sc_block_color_9">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment9" styleClass="text_base" style="width:95px;" /></td></tr>
      <tr class="add_title_color display_none"><td><span class="sc_block_color_10">&nbsp;&nbsp;&nbsp;</span>&nbsp;<html:text name="sch081Form" maxlength="10" property="sch081ColorComment10" styleClass="text_base" style="width:95px;" /></td></tr>
      </table>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('sch081kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('sch081back');">
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