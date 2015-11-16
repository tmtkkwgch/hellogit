<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-jqText.tld" prefix="jquery" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="cmn.entry" />･<gsmsg:write key="cmn.check" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>

<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.mobile.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jQuery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../mobile/sp/js/jquery.ui.datepicker.mobile.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>

<logic:equal name="mbhSch040Form" property="mobileLang" value="0">
<script src="../mobile/sp/js/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
</logic:equal>

<script type="text/javascript">
$(document).ready(function(){
  $("#date").datepicker();
});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $("#date2").datepicker();
});
</script>
<% pluginName = "sch"; %>
<% thisForm = "mbhSch040Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_sch040">
<input type="hidden" name="mobileType" value="1">
<html:hidden property="cmd" />
<input type="hidden" name="CMD" value="">
<html:hidden property="dspMod" />
<html:hidden property="sch010SchSid" />
<html:hidden property="listMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch040BatchRef" />
<html:hidden property="sch040ReserveGroupSid" />
<html:hidden property="sch040ResBatchRef" />
<html:hidden property="sch040contact" />
<html:hidden property="sch040ResBatchRef" />
<html:hidden property="sch040DispMod" />
<html:hidden property="sch040FrYear" />
<html:hidden property="sch040FrMonth" />
<html:hidden property="sch040FrDay" />
<html:hidden property="sch040ToYear" />
<html:hidden property="sch040ToMonth" />
<html:hidden property="sch040ToDay" />
<html:hidden property="sch040InitFlg" />
<html:hidden property="sch040EditDspMode" />

<bean:define id="sptm" name="mbhSch040Form" property="spTheme"/>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('sch040back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/sch_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="schedule.108" /><br><gsmsg:write key="cmn.entry" />･<gsmsg:write key="cmn.check" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhSch040Form" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="mbhSch040Form" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch040Form" property="svReserveUsers" scope="request">
<logic:iterate id="resBean" name="mbhSch040Form" property="svReserveUsers" scope="request">
<input type="hidden" name="svReserveUsers" value="<bean:write name="resBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch040Form" property="sch040CompanySid">
  <logic:iterate id="coId" name="mbhSch040Form" property="sch040CompanySid">
    <input type="hidden" name="sch040CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch040Form" property="sch040CompanyBaseSid">
  <logic:iterate id="coId" name="mbhSch040Form" property="sch040CompanyBaseSid">
    <input type="hidden" name="sch040CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch040Form" property="sch040AddressId">
  <logic:iterate id="addressId" name="mbhSch040Form" property="sch040AddressId">
    <input type="hidden" name="sch040AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>

<!-- 編集モード -->
<logic:notEqual name="mbhSch040Form" property="sch040DispMod" value="0">

<br>
<div class="title_1" align="center">
  <b>
    <div class="font_small">
      <logic:notEqual name="mbhSch040Form" property="sch010SelectUsrKbn" value="0">
        <img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0">
      </logic:notEqual>
      <bean:write name="mbhSch040Form" property="sch040UsrName" />
    </div>
  </b>
</div>

<br>

<!-- 通常スケジュール or 出欠確認依頼者-->
<logic:notEqual name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_ANSWER) %>">

  <logic:equal name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_REGIST) %>">
    <div class="font_small">■出欠確認</div>
    <br>
    <div align="center">
      <html:select property="sch040AttendKbn" onchange="changeCombo2('changeAttend');">
        <html:optionsCollection name="mbhSch040Form" property="sch040AttendKbnLavel" value="value" label="label" />
      </html:select>
    </div>

    <hr>

  <div id="ansList" data-role="collapsible" data-collapsed="true">
  <h2>
    <div align="center">回答一覧</div>
  </h2>

  <div width="100%" style="text-align:center;border:1px solid #a5a1a1;background:#ffffff;padding-right:1%;padding-left:1%;padding-top:10px;padding-bottom:10px;" class="header_both_shadow" align="center">
  <table cellspacing="0" cellpadding="0" style="border-collapse:collapse;border:1px solid #a5a1a1;" align="center" width="100%">
    <tr>
      <td class="ui-body ui-body-c" align="center" width="25%"><span class="font_xsmall" style="font-weight:bold;">回答日時</span></td>
      <td class="ui-body ui-body-c" align="center" width="50%"><span class="font_xsmall" style="font-weight:bold;">氏名</span></td>
      <td class="ui-body ui-body-c" align="center" width="25%"><span class="font_xsmall" style="font-weight:bold;">回答</span></td>
    </tr>

    <logic:notEmpty name="mbhSch040Form" property="sch040AttendAnsList">
    <logic:iterate id="ansMdl" name="mbhSch040Form" property="sch040AttendAnsList">
    <tr>
      <td class="ui-body ui-body-d" align="center">
      <span class="font_xsmall" style="font-weight:bold;">
        <logic:equal name="ansMdl" property="attendAnsKbn" value="0">
          －
        </logic:equal>
        <logic:notEqual name="ansMdl" property="attendAnsKbn" value="0">
          <bean:write name="ansMdl" property="attendAnsDate" />
        </logic:notEqual>
      </span>
      </td>
      <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="font-weight:bold;"><bean:write name="ansMdl" property="attendAnsUsrName" /></span></td>
      <logic:equal name="ansMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NONE) %>">
        <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="font-weight:bold;">未回答</span></td>
      </logic:equal>
      <logic:equal name="ansMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_YES) %>">
        <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="color:#008000;font-weight:bold;">出　席</span></td>
      </logic:equal>
      <logic:equal name="ansMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NO) %>">
        <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="color:red;font-weight:bold;">欠　席</span></td>
      </logic:equal>
    </tr>
    </logic:iterate>
    </logic:notEmpty>
  </table>
  </div>


  </div>

<hr>
  </logic:equal>

<div class="font_small">■<gsmsg:write key="cmn.start" /></div>
<br>
<jquery:jqtext id="date" name="mbhSch040Form" property="sch040FrDate" readonly="true"/>
<div data-role="navbar" align="center">
  <ul>
    <li>
      <html:select property="sch040FrHour">
        <html:optionsCollection name="mbhSch040Form" property="sch040HourLavel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
    </li>
    <li>
      <html:select property="sch040FrMin">
        <html:optionsCollection name="mbhSch040Form" property="sch040MinuteLavel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
   </li>
  </ul>
</div>
<br>
<div class="font_small">■<gsmsg:write key="main.src.man250.30" /></div>
<br>
<jquery:jqtext id="date2" name="mbhSch040Form" property="sch040ToDate" readonly="true"/>
<div data-role="navbar" align="center">
  <ul>
    <li>
      <html:select property="sch040ToHour" onchange="setToDay();">
        <html:optionsCollection name="mbhSch040Form" property="sch040HourLavel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.hour.input" />
    </li>
    <li>
      <html:select property="sch040ToMin" onchange="setToDay();">
        <html:optionsCollection name="mbhSch040Form" property="sch040MinuteLavel" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.minute.input" />
   </li>
  </ul>
</div>

<hr>

<div class="font_small">■<gsmsg:write key="cmn.title" /></div>
<br><html:text name="mbhSch040Form" size="27" maxlength="21" property="sch040Title" />
<br><div class="font_small">■<gsmsg:write key="schedule.10" /></div>
<br>

<div data-role="navbar" align="center">

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
    <logic:iterate id="msgStr" name="mbhSch040Form" property="sch040ColorMsgList" indexId="msgId" type="java.lang.String">
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

    <ul>
      <li style="background-color:#0000FF;">
        <span style="background-color:#0000FF;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="1" /></span>
        <label for="bg_color1" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg1 %></b></div></span>
      </li>
      <li style="background-color:#FF0000;">
        <span style="background-color:#FF0000;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="2" /></span>
        <label for="bg_color2" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg2 %></b></div></span>
      </li>
      <li style="background-color:#009900;">
        <span style="background-color:#009900;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="3" /></span>
        <label for="bg_color3" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg3 %></b></div></span>
      </li>
      <li style="background-color:#ff9900;">
        <span style="background-color:#ff9900;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="4" /></span>
        <label for="bg_color4" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg4 %></b></div></span>
      </li>
      <li style="background-color:#333333;">
        <span style="background-color:#333333;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="5" /></span>
        <label for="bg_color5" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg5 %></b></div></span>
    </li>
  </ul>

  <logic:equal name="mbhSch040Form" property="sch040colorKbn" value="1">
    <br>
     <ul>
      <li style="background-color:#000080;">
        <span style="background-color:#000080;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="6" /></span>
        <label for="bg_color6" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg6 %></b></div></span>
      </li>
      <li style="background-color:#800000;">
        <span style="background-color:#800000;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="7" /></span>
        <label for="bg_color7" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg7 %></b></div></span>
      </li>
      <li style="background-color:#008080;">
        <span style="background-color:#008080;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="8" /></span>
        <label for="bg_color8" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg8 %></b></div></span>
      </li>
      <li style="background-color:#808080;">
        <span style="background-color:#808080;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="9" /></span>
        <label for="bg_color9" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg9 %></b></div></span>
      </li>
      <li style="background-color:#008DCB;">
        <span style="background-color:#008DCB;"><html:radio name="mbhSch040Form" property="sch040Bgcolor" value="10" /></span>
        <label for="bg_color10" class="text_base">&nbsp;&nbsp;</label><br><span style="color:white;"><div class="font_small"><b><%= colorMsg10 %></b></div></span>
      </li>
    </ul>

  </logic:equal>

</div>
<hr>


<div class="font_small">■<gsmsg:write key="cmn.content" /></div>
<br><textarea name="sch040Value" cols="16" rows="3"><bean:write name="mbhSch040Form" property="sch040Value" /></textarea>
<br><div class="font_small">■<gsmsg:write key="cmn.memo" /></div>
<br><textarea name="sch040Biko" cols="16" rows="2"><bean:write name="mbhSch040Form" property="sch040Biko" /></textarea>

<hr>

<div data-role="collapsible" data-collapsed="true">
  <h2>
    <div align="center">詳細<gsmsg:write key="cmn.setting" /></div>
  </h2>

  <div class="font_small">■<gsmsg:write key="cmn.edit.permissions" /></div>
  <br>
  <div align="center">
  <logic:equal name="mbhSch040Form" property="sch040AttendKbn" value="0">
    <html:select property="sch040Edit">
      <html:optionsCollection name="mbhSch040Form" property="sch040EditLavel" value="value" label="label" />
    </html:select>
  </logic:equal>
  <logic:notEqual name="mbhSch040Form" property="sch040AttendKbn" value="0">
    <gsmsg:write key="cmn.only.principal.or.registant" />
  </logic:notEqual>
  </div>

  <div class="font_small">■<gsmsg:write key="cmn.public" /></div>
  <br>
  <div align="center">
    <html:select property="sch040Public">
      <html:optionsCollection name="mbhSch040Form" property="sch040PublicLavel" value="value" label="label" />
    </html:select>
  </div>

  <logic:equal name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_NORMAL) %>">
  <div class="font_small">■出欠確認</div>
  <br>
  <div align="center">
    <html:select property="sch040AttendKbn" onchange="changeCombo2('changeAttend');">
      <html:optionsCollection name="mbhSch040Form" property="sch040AttendKbnLavel" value="value" label="label" />
    </html:select>
  </div>
  </logic:equal>

  <hr>

  <logic:equal name="mbhSch040Form" property="sch010SelectUsrKbn" value="0">
  <br><div class="font_small">■<gsmsg:write key="schedule.117" /></div>
  <logic:notEmpty name="mbhSch040Form" property="sch040SelectUsrLavel">
  <div data-role="controlgroup" align="center">
    <logic:iterate id="urBean" name="mbhSch040Form" property="sch040SelectUsrLavel">
      <input name="<bean:write name="urBean" property="usrSid" />" value="<bean:write name="urBean" property="usiSei" />&nbsp;<bean:write name="urBean" property="usiMei" />" type="submit" data-icon="delete" data-iconpos="left" />
    </logic:iterate>
  </div>
  </logic:notEmpty>
  <div align="center">
    <div class="font_small">
      <input name="sch040usrAdd" value="<gsmsg:write key="cmn.add" />" type="submit" data-inline="true" data-role="button" data-icon="star" data-iconpos="right" />
    </div>
  </div>
  </logic:equal>


  <logic:equal name="mbhSch040Form" property="reservePluginKbn" value="0">
  <br><div class="font_small">■<gsmsg:write key="cmn.reserve" /></div>

  <br>
    <!-- 同時登録施設 -->

    <logic:equal name="mbhSch040Form" property="cmd" value="edit">

      <% String grpColor = radioFont; %>
      <% String nameColor = radioFont; %>

      <logic:equal name="mbhSch040Form" property="sch040ResBatchRef" value="1">
        <% grpColor = radioFontActive; %>
      </logic:equal>
      <logic:equal name="mbhSch040Form" property="sch040ResBatchRef" value="0">
        <% nameColor = radioFontActive; %>
      </logic:equal>

      <fieldset data-type="horizontal" align="center">
        <html:radio name="mbhSch040Form" property="sch040ResBatchRef" styleId="sch040ResBatchRef0" value="1" onchange="chRsvYes('<%= sptm %>')"/>
        <label for="sch040ResBatchRef0" class="<%= radioClass %>" id="rbr0"><span class="font_middle" id="rsvNo" style="color:<%= grpColor %>"><gsmsg:write key="schedule.25" /></span></label>
        <html:radio name="mbhSch040Form" property="sch040ResBatchRef" styleId="sch040ResBatchRef1" value="0" onchange="chRsvNo('<%= sptm %>')" />
        <label for="sch040ResBatchRef1" class="<%= radioClass %>" id="rbr1"><span class="font_middle" id="rsvYes" style="color:<%= nameColor %>"><gsmsg:write key="schedule.24" /></span></label>
      </fieldset>

    </logic:equal>

    <logic:notEmpty name="mbhSch040Form" property="sch040ReserveSelectLavel" scope="request">
      <div data-role="controlgroup" align="center">
        <logic:iterate id="ressBean" name="mbhSch040Form" property="sch040ReserveSelectLavel" scope="request">
          <input name="<%= jp.groupsession.v3.mbh.sch040.MbhSch040Form.PARAM_RSV_SELECTADR %><bean:write name="ressBean" property="rsdSid" scope="page"/>" value="<bean:write name="ressBean" property="rsdName" scope="page"/>" type="submit" data-icon="delete" data-iconpos="left" />
        </logic:iterate>
       </div>
     </logic:notEmpty>

    <logic:notEqual name="mbhSch040Form" property="sch040CantReadRsvCount" value="0">
    <br>
    <span class="text_r1"><gsmsg:write key="schedule.150" />:<bean:write name="mbhSch040Form" property="sch040CantReadRsvCount" /></span>
    </logic:notEqual>
  </logic:equal>

  <div align="center">
    <div class="font_small">
      <input name="sch040rsvAdd" value="<gsmsg:write key="cmn.add" />" type="submit" data-inline="true" data-role="button" data-icon="star" data-iconpos="right" />
    </div>
  </div>

</div>

<br><div class="font_small">■<gsmsg:write key="cmn.registant" /></div>
<div class="title_1" align="center">
  <b>
    <div class="font_small">
          <logic:notEqual name="mbhSch040Form" property="sch040AddUsrJkbn" value="9">
                <bean:write name="mbhSch040Form" property="sch040AddUsrName" />
          </logic:notEqual>
          <logic:equal name="mbhSch040Form" property="sch040AddUsrJkbn" value="9">
                <del>
                      <bean:write name="mbhSch040Form" property="sch040AddUsrName" />
                </del>
          </logic:equal>
          </div>
  </b>
</div>
</logic:notEqual>

<!-- 通常スケジュール or 出欠確認回答者-->
<logic:equal name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_ANSWER) %>">

  <div class="font_small">■出欠確認</div>
  <br>
  <div align="center">
    <html:select name="mbhSch040Form" property="sch040AttendAnsKbn">
      <html:optionsCollection name="mbhSch040Form" property="sch040AttendAnsKbnLavel" value="value" label="label" />
    </html:select>
  </div>

<hr>

  <div data-role="collapsible" data-collapsed="true">
  <h2>
    <div align="center">回答一覧</div>
  </h2>

  <div width="100%" style="text-align:center;border:1px solid #a5a1a1;background:#ffffff;padding-right:1%;padding-left:1%;padding-top:10px;padding-bottom:10px;" class="header_both_shadow" align="center">
  <table cellspacing="0" cellpadding="0" style="border-collapse:collapse;border:1px solid #a5a1a1;" align="center" width="100%">
    <tr>
      <td class="ui-body ui-body-c" align="center" width="25%"><span class="font_xsmall" style="font-weight:bold;">回答日時</span></td>
      <td class="ui-body ui-body-c" align="center" width="50%"><span class="font_xsmall" style="font-weight:bold;">氏名</span></td>
      <td class="ui-body ui-body-c" align="center" width="25%"><span class="font_xsmall" style="font-weight:bold;">回答</span></td>
    </tr>

    <html:hidden property="sch040AttendKbn" />
    <logic:notEmpty name="mbhSch040Form" property="sch040AttendAnsList">
    <logic:iterate id="ansMdl" name="mbhSch040Form" property="sch040AttendAnsList">
    <tr>
      <td class="ui-body ui-body-d" align="center">
      <span class="font_xsmall" style="font-weight:bold;">
        <logic:equal name="ansMdl" property="attendAnsKbn" value="0">
          －
        </logic:equal>
        <logic:notEqual name="ansMdl" property="attendAnsKbn" value="0">
          <bean:write name="ansMdl" property="attendAnsDate" />
        </logic:notEqual>
      </span>
      </td>
      <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="font-weight:bold;"><bean:write name="ansMdl" property="attendAnsUsrName" /></span></td>
      <logic:equal name="ansMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NONE) %>">
        <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="font-weight:bold;">未回答</span></td>
      </logic:equal>
      <logic:equal name="ansMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_YES) %>">
        <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="color:#008000;font-weight:bold;">出　席</span></td>
      </logic:equal>
      <logic:equal name="ansMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NO) %>">
        <td class="ui-body ui-body-d" align="center"><span class="font_xsmall" style="color:red;font-weight:bold;">欠　席</span></td>
      </logic:equal>
    </tr>
    </logic:iterate>
    </logic:notEmpty>
  </table>
  </div>


  </div>

<hr>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.start" /></div>
    <bean:define id="yrf" name="mbhSch040Form" property="sch040FrYear" type="java.lang.String" />
    <bean:write name="mbhSch040Form" property="sch040FrYear" /><gsmsg:write key="cmn.year" arg0="<%= yrf %>"/>
    <bean:write name="mbhSch040Form" property="sch040FrMonth" /><gsmsg:write key="cmn.month" />
    <bean:write name="mbhSch040Form" property="sch040FrDay" /><gsmsg:write key="cmn.day" />
    <html:hidden property="sch040FrYear"/>
    <html:hidden property="sch040FrMonth"/>
    <html:hidden property="sch040FrDay"/>
    <logic:notEqual name="mbhSch040Form" property="sch040FrHour" value="-1">
      <br>
      <bean:write name="mbhSch040Form" property="sch040FrHour" /><gsmsg:write key="cmn.hour" />
      <html:hidden property="sch040FrHour"/>
      <logic:notEqual name="mbhSch040Form" property="sch040FrMin" value="0">
        <bean:write name="mbhSch040Form" property="sch040FrMin" /><gsmsg:write key="cmn.minute" />
        <html:hidden property="sch040FrMin"/>
      </logic:notEqual>
    </logic:notEqual>
  </li>

    <li>
    <div class="font_xsmall">■<gsmsg:write key="main.src.man250.30" /></div>
    <bean:define id="yrt" name="mbhSch040Form" property="sch040ToYear" type="java.lang.String" />
    <bean:write name="mbhSch040Form" property="sch040ToYear" /><gsmsg:write key="cmn.year" arg0="<%= yrt %>" />
    <bean:write name="mbhSch040Form" property="sch040ToMonth" /><gsmsg:write key="cmn.month" />
    <bean:write name="mbhSch040Form" property="sch040ToDay" /><gsmsg:write key="cmn.day" />
    <html:hidden property="sch040ToYear"/>
    <html:hidden property="sch040ToMonth"/>
    <html:hidden property="sch040ToDay"/>
    <logic:notEqual name="mbhSch040Form" property="sch040ToHour" value="-1">
      <br>
      <bean:write name="mbhSch040Form" property="sch040ToHour" /><gsmsg:write key="cmn.hour" />
      <html:hidden property="sch040ToHour"/>
      <logic:notEqual name="mbhSch040Form" property="sch040ToMin" value="0">
        <bean:write name="mbhSch040Form" property="sch040ToMin" /><gsmsg:write key="cmn.minute" />
        <html:hidden property="sch040ToMin"/>
      </logic:notEqual>
    </logic:notEqual>
  </li>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.title" /></div>
    <bean:write name="mbhSch040Form" property="sch040Title" />
    <html:hidden property="sch040Title"/>
  </li>

  <logic:notEmpty name="mbhSch040Form" property="sch040BgcolorText">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="schedule.10" /></div>
      <bean:write name="mbhSch040Form" property="sch040BgcolorText" />
      <html:hidden property="sch040BgcolorText"/>
    </li>
  </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.content" /></div>
    <bean:write name="mbhSch040Form" property="sch040Value" />
    <html:hidden property="sch040Value"/>
  </li>


  <logic:notEmpty name="mbhSch040Form" property="sch040Biko">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.memo" /></div>
      <bean:write name="mbhSch040Form" property="sch040Biko" />
      <html:hidden property="sch040Biko"/>
    </li>
  </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.edit.permissions" /></div>
    <bean:write name="mbhSch040Form" property="sch040EditText" />
<%--     <html:hidden property="sch040EditText"/> --%>
<html:hidden property="sch040Edit"/>

  </li>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.public" /></div>
    <bean:write name="mbhSch040Form" property="sch041PublicText" />
<%--     <html:hidden property="sch041PublicText"/> --%>
<html:hidden property="sch040Public"/>

  </li>

  <li>
    <div class="font_xsmall">■出欠確認</div>
    <bean:write name="mbhSch040Form" property="sch041AttendKbnText" />
    <html:hidden property="sch041PublicText"/>
  </li>

  <logic:equal name="mbhSch040Form" property="sch010SelectUsrKbn" value="0">
    <logic:notEmpty name="mbhSch040Form" property="sch040SelectUsrLavel">
      <li>
        <div class="font_xsmall">■<gsmsg:write key="schedule.117" /></div>
        <logic:iterate id="urBean" name="mbhSch040Form" property="sch040SelectUsrLavel">
        <bean:write name="urBean" property="usiSei" />&nbsp;<bean:write name="urBean" property="usiMei" />
        <br>
        </logic:iterate>
      </li>
    </logic:notEmpty>
  </logic:equal>


  <logic:notEmpty name="mbhSch040Form" property="sch040ReserveSelectLavel" scope="request">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.reserve" /></div>
      <logic:iterate id="ressBean" name="mbhSch040Form" property="sch040ReserveSelectLavel" scope="request">
        <bean:write name="ressBean" property="rsdName" scope="page"/><br>
      </logic:iterate>
    </li>
 </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.registant" /></div>
    <logic:notEqual name="mbhSch040Form" property="sch040AddUsrJkbn" value="9">
    <bean:write name="mbhSch040Form" property="sch040AddUsrName" />
    </logic:notEqual>
    <logic:equal name="mbhSch040Form" property="sch040AddUsrJkbn" value="9">
    <del>
    <bean:write name="mbhSch040Form" property="sch040AddUsrName" />
    </del>
    </logic:equal>
  </li>

  </ul>

</logic:equal>


<logic:notEqual name="mbhSch040Form" property="cmd" value="edit">
  <div data-role="controlgroup" data-type="horizontal" align="center">
    <br><input name="sch040add" value="<gsmsg:write key="cmn.entry" />" type="submit" data-inline="true" data-role="button" data-icon="plus"/><input name="sch040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-inline="true" data-iconpos="right"/>
  </div>
</logic:notEqual>
<logic:equal name="mbhSch040Form" property="cmd" value="edit">
  <div data-role="controlgroup" data-type="horizontal" align="center">
    <br>
    <logic:equal name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_REGIST) %>">
      <input type="button" name="sch040add" value="<gsmsg:write key="cmn.change" />"  data-inline="true" data-icon="plus" onClick="schAttendPopup();">
    </logic:equal>
    <logic:notEqual name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_REGIST) %>">
      <input name="sch040add" value="<gsmsg:write key="cmn.change" />" type="submit" data-inline="true" data-role="button" data-icon="plus"/>
    </logic:notEqual>
    <logic:notEqual name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_ANSWER) %>">
      <input name="sch040del" value="<gsmsg:write key="cmn.delete" />" type="submit" data-inline="true" data-icon="delete"/>
    </logic:notEqual>
    <logic:equal name="mbhSch040Form" property="sch040EditDspMode" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.EDIT_DSP_MODE_ANSWER) %>">
    <logic:equal name="mbhSch040Form" property="sch040AttendDelFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_SCH_DEL_YES) %>">
    <input name="sch040del" value="<gsmsg:write key="cmn.delete" />" type="submit" data-inline="true" data-icon="delete"/>
    </logic:equal>
    </logic:equal>
  </div>
  <div align="center">
    <input name="sch040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back"/>
  </div>
</logic:equal>

</logic:notEqual>


<!-- 確認モード -->
<logic:equal name="mbhSch040Form" property="sch040DispMod" value="0">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <logic:notEqual name="mbhSch040Form" property="sch010SelectUsrKbn" value="0">
      <img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0">
      </logic:notEqual>
      <bean:write name="mbhSch040Form" property="sch040UsrName" />
    </div>
  </li>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.start" /></div>
    <bean:define id="yrf" name="mbhSch040Form" property="sch040FrYear" type="java.lang.String" />
    <bean:write name="mbhSch040Form" property="sch040FrYear" /><gsmsg:write key="cmn.year" arg0="<%= yrf %>"/>
    <bean:write name="mbhSch040Form" property="sch040FrMonth" /><gsmsg:write key="cmn.month" />
    <bean:write name="mbhSch040Form" property="sch040FrDay" /><gsmsg:write key="cmn.day" />
    <logic:notEqual name="mbhSch040Form" property="sch040FrHour" value="-1">
      <br>
      <bean:write name="mbhSch040Form" property="sch040FrHour" /><gsmsg:write key="cmn.hour" />
      <logic:notEqual name="mbhSch040Form" property="sch040FrMin" value="0">
        <bean:write name="mbhSch040Form" property="sch040FrMin" /><gsmsg:write key="cmn.minute" />
      </logic:notEqual>
    </logic:notEqual>
  </li>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="main.src.man250.30" /></div>
    <bean:define id="yrt" name="mbhSch040Form" property="sch040ToYear" type="java.lang.String" />
    <bean:write name="mbhSch040Form" property="sch040ToYear" /><gsmsg:write key="cmn.year" arg0="<%= yrt %>" />
    <bean:write name="mbhSch040Form" property="sch040ToMonth" /><gsmsg:write key="cmn.month" />
    <bean:write name="mbhSch040Form" property="sch040ToDay" /><gsmsg:write key="cmn.day" />
    <logic:notEqual name="mbhSch040Form" property="sch040ToHour" value="-1">
      <br>
      <bean:write name="mbhSch040Form" property="sch040ToHour" /><gsmsg:write key="cmn.hour" />
      <logic:notEqual name="mbhSch040Form" property="sch040ToMin" value="0">
        <bean:write name="mbhSch040Form" property="sch040ToMin" /><gsmsg:write key="cmn.minute" />
      </logic:notEqual>
    </logic:notEqual>
  </li>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.title" /></div>
    <bean:write name="mbhSch040Form" property="sch040Title" />
  </li>

  <logic:notEmpty name="mbhSch040Form" property="sch040BgcolorText">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="schedule.10" /></div>
      <bean:write name="mbhSch040Form" property="sch040BgcolorText" />
    </li>
  </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.content" /></div>
    <bean:write name="mbhSch040Form" property="sch040Value" />
  </li>


  <logic:notEmpty name="mbhSch040Form" property="sch040Biko">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.memo" /></div>
      <bean:write name="mbhSch040Form" property="sch040Biko" />
    </li>
  </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.edit.permissions" /></div>
    <bean:write name="mbhSch040Form" property="sch040EditText" />
  </li>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.public" /></div>
    <bean:write name="mbhSch040Form" property="sch041PublicText" />
  </li>

  <li>
    <div class="font_xsmall">■出欠確認</div>
    <bean:write name="mbhSch040Form" property="sch041AttendKbnText" />
  </li>

  <logic:equal name="mbhSch040Form" property="sch010SelectUsrKbn" value="0">
    <logic:notEmpty name="mbhSch040Form" property="sch040SelectUsrLavel">
      <li>
        <div class="font_xsmall">■<gsmsg:write key="schedule.117" /></div>
        <logic:iterate id="urBean" name="mbhSch040Form" property="sch040SelectUsrLavel">
        <bean:write name="urBean" property="usiSei" />&nbsp;<bean:write name="urBean" property="usiMei" />
        <br>
        </logic:iterate>
      </li>
    </logic:notEmpty>
  </logic:equal>


  <logic:notEmpty name="mbhSch040Form" property="sch040ReserveSelectLavel" scope="request">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.reserve" /></div>
      <logic:iterate id="ressBean" name="mbhSch040Form" property="sch040ReserveSelectLavel" scope="request">
        <bean:write name="ressBean" property="rsdName" scope="page"/><br>
      </logic:iterate>
    </li>
 </logic:notEmpty>

  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.registant" /></div>
    <logic:notEqual name="mbhSch040Form" property="sch040AddUsrJkbn" value="9">
    <bean:write name="mbhSch040Form" property="sch040AddUsrName" />
    </logic:notEqual>
    <logic:equal name="mbhSch040Form" property="sch040AddUsrJkbn" value="9">
    <del>
    <bean:write name="mbhSch040Form" property="sch040AddUsrName" />
    </del>
    </logic:equal>
  </li>

</ul>

<div data-role="controlgroup" data-type="horizontal" align="center" class="font_small">
  <br>
  <logic:equal name="mbhSch040Form" property="schEditKbn" value="0">
    <input type="submit" name="sch040edit" value="<gsmsg:write key="cmn.edit" />"  data-inline="true" data-icon="arrow-l"/>
  </logic:equal>
  <input name="sch040back" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-inline="true" data-iconpos="right"/>
</div>
</logic:equal>
<br>

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li><a href="../mobile/sp_sch030.do"><gsmsg:write key="mobile.18" /></a></li>
  <li><a href="../mobile/sp_sch010.do"><gsmsg:write key="schedule.19" /></a></li>
  <li><a href="../mobile/sp_sch020.do"><gsmsg:write key="mobile.19" /></a></li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

<div id="mailAttend" style="position:absolute;bottom:0;display:none;z-index:10;display:none;width:100%;height:100%;background:rgba(255, 255, 255, 0.7);">
  <div id="schAttendPopup">
    <div style="text-align:right;">
    <a href="#" onclick="schAttendPopup();" data-role="button" data-icon="delete" data-iconpos="notext">Close</a>
    </div>
    <div align="center">
    <div style="width:90%;" class="title_1" align="center">
      <div class="font_small">
         <b>スケジュールを更新します。<br>よろしいですか？</b>
      </div>
      <br>
      <div>
         <input type="checkbox" name="sch040EditMailSendKbn" id="mailSendKbn" value="1" /><label for="mailSendKbn" class="text_base"><span class="font_xsmall">出欠確認<br>再通知メールを送信する</span></label>
      </div>
    </div>

    <div>
    <input name="sch040add" value="はい" type="submit" data-inline="true" data-role="button" data-icon="plus" style="font-size: 10px;"/>
    <input value="キャンセル" type="button" data-inline="true" data-role="button" data-icon="delete" onclick="schAttendPopup();"; />
  </div>

</div>
  </div>
</div>


</html:form>
</div><!-- /page -->

</body>
</html:html>
