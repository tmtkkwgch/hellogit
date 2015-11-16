<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String edit = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT);%>
<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.MAX_LENGTH_ADR3_BIKO);%>

<% String markOther    = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_OTHER); %>
<% String markTel      = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_TEL); %>
<% String markMail     = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_MAIL); %>
<% String markWeb      = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_WEB);  %>
<% String markMeeting  = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_MEETING);   %>

<%-- <gsmsg:write key="adr.mark.image" /> --%>
<%
  java.util.HashMap imgMap = new java.util.HashMap();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
  String msgTel = gsMsg.getMessage(request, "cmn.phone");
  String msgMail = gsMsg.getMessage(request, "cmn.mail");
  String msgMeeting = gsMsg.getMessage(request, "address.28");
  String msgOther = gsMsg.getMessage(request, "cmn.other");

  imgMap.put(markTel, "<img src=\"../address/images/call.gif\" alt=" + "\"" + msgTel + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put(markMail, "<img src=\"../address/images/mail.gif\" alt=" + "\"" + msgMail + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put(markWeb, "<img src=\"../address/images/web.gif\" alt=\"Web\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markMeeting, "<img src=\"../address/images/uchiawase.gif\" alt=" + "\"" + msgMeeting + "\"" + " border=\"0\" class=\"img_bottom\">");

  imgMap.put("none", "&nbsp;");
%>

<%
  java.util.HashMap imgTextMap = new java.util.HashMap();
  imgTextMap.put(markTel, msgTel);
  imgTextMap.put(markMail, msgMail);
  imgTextMap.put(markWeb, "Web");
  imgTextMap.put(markMeeting, msgMeeting);

  imgTextMap.put("none", "&nbsp;");
%>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="address.112" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr170.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String closeScript = "calWindowClose();windowClose();"; %>
<logic:equal name="adr170Form" property="projectPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
<script language="JavaScript" src="../project/js/prj210.js?<%= GSConst.VERSION_PARAM %>"></script>
<% closeScript += "projectWindowClose();"; %>
</logic:equal>

</head>

<body class="body_03" onunload="<%= closeScript %>" onload="showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');">

<html:form action="/address/adr170">

<input type="hidden" name="CMD" value="ok">
<input type="hidden" name="helpPrm" value="<bean:write name="adr170Form" property="adr160ProcMode" />">

<html:hidden property="seniFlg" />

<html:hidden property="adr160ProcMode" />
<html:hidden property="adr160EditSid" />
<html:hidden property="adr160pageNum1" />
<html:hidden property="adr160pageNum2" />
<html:hidden property="sortKey" />
<html:hidden property="orderKey" />
<html:hidden property="adr170delProjectSid" />
<html:hidden property="projectPluginKbn" />

<logic:notEmpty name="adr170Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr170Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr170Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr170Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr170Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr170Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr170Form" property="saveUser" scope="request">
  <logic:iterate id="usrSid" name="adr170Form" property="saveUser" scope="request">
    <input type="hidden" name="saveUser" value="<bean:write name="usrSid" />">
  </logic:iterate>
</logic:notEmpty>

<div id="adr170ProjectIdArea">
<logic:notEmpty name="adr170Form" property="adr170ProjectSid">
  <logic:iterate id="pjSid" name="adr170Form" property="adr170ProjectSid">
    <input type="hidden" name="adr170ProjectSid" value="<bean:write name="pjSid"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="80%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.112" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <!-- OK -->
    <input type="submit" value="OK" class="btn_ok1">
    <!-- <gsmsg:write key="cmn.delete" /> -->
    <logic:equal name="adr170Form" property="adr160ProcMode" value="<%= edit %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
    </logic:equal>
    <!-- <gsmsg:write key="cmn.back" /> -->
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('list_back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/><br></td>
    </tr>
    </table>
  </logic:messagesPresent>

  </td>
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <!-- <gsmsg:write key="cmn.target" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.target" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base">
        <bean:write name="adr170Form" property="adr170ContactUserComName" />
        <logic:notEmpty name="adr170Form" property="adr170ContactUserComName">&nbsp;&nbsp;</logic:notEmpty>
        <bean:write name="adr170Form" property="adr170ContactUserName" />
      </span>
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.title" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <html:text maxlength="100" property="adr170title" styleClass="text_base" style="width:637px;" />
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.type" /> -->
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.type" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_wt">
      <span class="text_base">
        <html:radio styleId="adr170Mark_1" name="adr170Form" property="adr170Mark" value="<%= markTel %>" />&nbsp;<label for="adr170Mark_1"><%= (String) imgMap.get(markTel) %>&nbsp;<%= (String) imgTextMap.get(markTel) %></label>&nbsp;
        <html:radio styleId="adr170Mark_2" name="adr170Form" property="adr170Mark" value="<%= markMail %>" />&nbsp;<label for="adr170Mark_2"><%= (String) imgMap.get(markMail) %>&nbsp;<%= (String) imgTextMap.get(markMail) %></label>&nbsp;
        <html:radio styleId="adr170Mark_3" name="adr170Form" property="adr170Mark" value="<%= markWeb %>" />&nbsp;<label for="adr170Mark_3"><%= (String) imgMap.get(markWeb) %>&nbsp;<%= (String) imgTextMap.get(markWeb) %></label>&nbsp;
        <html:radio styleId="adr170Mark_4" name="adr170Form" property="adr170Mark" value="<%= markMeeting %>" />&nbsp;<label for="adr170Mark_4"><%= (String) imgMap.get(markMeeting) %>&nbsp;<%= (String) imgTextMap.get(markMeeting) %></label>&nbsp;
        <html:radio styleId="adr170Mark_0" name="adr170Form" property="adr170Mark" value="<%= markOther %>" /><label for="adr170Mark_0">&nbsp;<gsmsg:write key="cmn.other" /></label>&nbsp;
      </span>
    </td>
    </tr>

    <!--  <gsmsg:write key="address.114" /> -->
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="address.114" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_wt">

    <div>
    <span class="text_base"><gsmsg:write key="cmn.start" />：</span>
    <!-- <gsmsg:write key="cmn.year" />(From) -->
    <logic:notEmpty name="adr170Form" property="adr170yearLabelList">
      <html:select property="adr170enterContactYear" styleId="contactYear">
      <html:optionsCollection name="adr170Form" property="adr170yearLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <!-- <gsmsg:write key="cmn.months" />(From) -->
    <logic:notEmpty name="adr170Form" property="adr170monthLabelList">
      <html:select property="adr170enterContactMonth" styleId="contactMonth">
      <html:optionsCollection name="adr170Form" property="adr170monthLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <!-- <gsmsg:write key="cmn.day" />(From) -->
    <logic:notEmpty name="adr170Form" property="adr170dayLabelList">
      <html:select property="adr170enterContactDay" styleId="contactDay">
      <html:optionsCollection name="adr170Form" property="adr170dayLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#contactDay')[0], $('#contactMonth')[0], $('#contactYear')[0]);" class="calendar_btn">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#contactYear')[0], $('#contactMonth')[0], $('#contactDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#contactYear')[0], $('#contactMonth')[0], $('#contactDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#contactYear')[0], $('#contactMonth')[0], $('#contactDay')[0], 3)">&nbsp;

    <logic:notEmpty name="adr170Form" property="adr170hourLabelList">
      <html:select property="adr170enterContactHour">
      <html:optionsCollection name="adr170Form" property="adr170hourLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.hour.input" />
    <logic:notEmpty name="adr170Form" property="adr170minuteLabelList">
      <html:select property="adr170enterContactMinute">
      <html:optionsCollection name="adr170Form" property="adr170minuteLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.minute.input" />
    </div>

    <div>
    <img src="../common/images/spacer.gif" width="1" height="5" border="0" alt=""><br>
    <!-- <gsmsg:write key="cmn.year" />(To) -->
    <span class="text_base"><gsmsg:write key="cmn.end" />：</span>
    <logic:notEmpty name="adr170Form" property="adr170yearLabelList">
      <html:select property="adr170enterContactYearTo" styleId="contactYearTo">
      <html:optionsCollection name="adr170Form" property="adr170yearLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <!-- <gsmsg:write key="cmn.months" />(To) -->
    <logic:notEmpty name="adr170Form" property="adr170monthLabelList">
      <html:select property="adr170enterContactMonthTo" styleId="contactMonthTo">
      <html:optionsCollection name="adr170Form" property="adr170monthLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <!-- <gsmsg:write key="cmn.day" />(To) -->
    <logic:notEmpty name="adr170Form" property="adr170dayLabelList">
      <html:select property="adr170enterContactDayTo" styleId="contactDayTo">
      <html:optionsCollection name="adr170Form" property="adr170dayLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

      <input type="button" name="dateToBtn" value="Cal" onclick="wrtCalendar($('#contactDayTo')[0], $('#contactMonthTo')[0], $('#contactYearTo')[0]);" class="calendar_btn">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#contactYearTo')[0], $('#contactMonthTo')[0], $('#contactDayTo')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#contactYearTo')[0], $('#contactMonthTo')[0], $('#contactDayTo')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#contactYearTo')[0], $('#contactMonthTo')[0], $('#contactDayTo')[0], 3)">&nbsp;

    <logic:notEmpty name="adr170Form" property="adr170hourLabelList">
      <html:select property="adr170enterContactHourTo">
      <html:optionsCollection name="adr170Form" property="adr170hourLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.hour.input" />
    <logic:notEmpty name="adr170Form" property="adr170minuteLabelList">
      <html:select property="adr170enterContactMinuteTo">
      <html:optionsCollection name="adr170Form" property="adr170minuteLabelList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <gsmsg:write key="cmn.minute.input" />
    </div>

    </td>
    </tr>

    <logic:equal name="adr170Form" property="projectPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
    <!-- <gsmsg:write key="cmn.project" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" /></span></td>
    <td valign="middle" align="left" class="td_wt">

      <logic:empty name="adr170Form" property="adr170ProjectList">
      <input type="button" class="btn_project_n1" value="<gsmsg:write key="cmn.project" />" onclick="return openProjectWindow('adr170')" />
      </logic:empty>

      <logic:notEmpty name="adr170Form" property="adr170ProjectList">

      <table width="100%" cellpadding="0" cellspacing="0">
      <td width="0%">

        <table width="100%" cellpadding="0" cellspacing="0">

        <logic:iterate id="prjData" name="adr170Form" property="adr170ProjectList">
        <tr>
        <td width="5%" style="vertical-valign:middle;" align="center" nowrap>
          <a href="#" onClick="deleteProject(<bean:write name="prjData" property="value" />);"><img src="../common/images/delete.gif" class="img_bottom" width="15" alt="<gsmsg:write key="address.adr170.6" />"></a>&nbsp;
        </td>

        <td width="95%" style="text-align:left!important;text-valign:center" nowrap>
          <span class="text_base"><bean:write name="prjData" property="label" /></span>&nbsp;&nbsp;
        </td>
        </tr>
        </logic:iterate>

        </table>
      </td>
      <td width="100%" style="text-align:left!important;text-valign:center">
        <input type="button" class="btn_project_n1" value="<gsmsg:write key="cmn.project" />" onclick="return openProjectWindow('adr170')" />
      </td>
      </table>

      </logic:notEmpty>

    </td>
    </tr>
    </logic:equal>

    <!-- <gsmsg:write key="cmn.memo" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td valign="middle" align="left" class="td_wt"><textarea name="adr170biko" style="width:566px" rows="10" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="adr170Form" property="adr170biko" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <!-- <gsmsg:write key="schedule.117" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="schedule.117" /></span></td>
    <td valign="middle" align="left" class="td_wt">

      <table width="0%" border="0" nowrap>
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="schedule.117" /></span></td>
      <td width="2%" align="center">&nbsp;</td>
      <td width="16%" align="center">&nbsp;</td>
      <td width="2%" align="center">&nbsp;</td>
      <td width="40%" align="center">

        <!-- <gsmsg:write key="adr.company.combo" /> -->
        <html:select property="adr170SelectedComComboSid" styleClass="select01" onchange="changeGroupCombo('chngGrpCmb');">
          <html:optionsCollection name="adr170Form" property="adr170ComLabelList" value="value" label="label" />
        </html:select>

      </td>
      </tr>

      <tr>
      <td align="center">
        <select size="5" multiple name ="adr170SelectedLeft" class="select01">
          <logic:notEmpty name="adr170Form" property="adr170SelectLeftUser" scope="request">
            <logic:iterate id="selectUsr" name="adr170Form" property="adr170SelectLeftUser" scope="request">
              <option value="<bean:write name="selectUsr" property="adrSid" />"><bean:write name="selectUsr" property="adrSei" />&nbsp;&nbsp;<bean:write name="selectUsr" property="adrMei" /></option>
            </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>

      </td>
      <td>&nbsp;</td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onclick="buttonPush('user_add');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('user_delete');">
      </td>
      <td>&nbsp;</td>

      <td align="center">
        <select size="5" multiple name ="adr170SelectedRight" class="select01">
          <logic:notEmpty name="adr170Form" property="adr170SelectRightUser" scope="request">
            <logic:iterate id="grpUsr" name="adr170Form" property="adr170SelectRightUser" scope="request">
              <option value="<bean:write name="grpUsr" property="adrSid" />"><bean:write name="grpUsr" property="adrSei" />&nbsp;&nbsp;<bean:write name="grpUsr" property="adrMei" /></option>
            </logic:iterate>
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.attached" /> -->
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td align="left" class="td_wt">
    <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('adr170selectFiles', '<bean:write name="adr170Form" property="adr170pluginId" />', '0', '0');">
    &nbsp;
    <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('deleteFile');"">
    <br>
    <html:select property="adr170selectFiles" styleClass="select01" multiple="true" size="5">
    <html:optionsCollection name="adr170Form" property="adr170FileLabelList" value="value" label="label" />
    </html:select>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
    <input type="submit" value="OK" class="btn_ok1">
    <logic:equal name="adr170Form" property="adr160ProcMode" value="<%= edit %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('list_back');">
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