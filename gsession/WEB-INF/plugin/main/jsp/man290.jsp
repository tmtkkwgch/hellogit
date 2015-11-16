<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthContent = String.valueOf(jp.groupsession.v2.man.GSConstMain.MAX_LENGTH_VALUE); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="main.man290.1" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man290.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String selectionEvent = ""; %>
<% boolean selectionFlg = false; %>
<% String valueFocusEvent = ""; %>
<logic:equal name="man290Form" property="webSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/selectionSearchText.js?<%= GSConst.VERSION_PARAM %>"></script>
  <% selectionFlg = true; %>
</logic:equal>

<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<% String closeScript = "calWindowClose();windowClose();"; %>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="<%= closeScript %>" onload="moreRe();setDisabled();changeWeekCombo();showLengthId($('#inputstr2')[0], <%= maxLengthContent %>, 'inputlength');">


<html:form action="/main/man290">

<html:hidden property="cmd" />
<html:hidden property="man320OrderKey" />
<html:hidden property="man320SortKey" />
<html:hidden property="man320FormAdminConfBtn" />
<html:hidden property="man320SltPage1" />
<html:hidden property="man320SltPage2" />
<html:hidden property="man320PageNum" />
<html:hidden property="man320SelectedSid" />
<html:hidden property="man290elementKbn" />
<html:hidden property="man290helpMode" />

<logic:notEmpty name="man290Form" property="man290memberSid">
<logic:iterate id="usid" name="man290Form" property="man290memberSid">
  <input type="hidden" name="man290memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="man290Form" property="man290helpMode" />">
<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../main/images/header_info_01.gif" border="0" alt="<gsmsg:write key="man.information.management" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.information" /></span></td>
    <logic:equal name="man290Form" property="cmd" value="add">
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="main.man290.1" /> ]</td>
    </logic:equal>
    <logic:equal name="man290Form" property="cmd" value="edit">
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="man.information.edit" /> ]</td>
    </logic:equal>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="man.information.management" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"><a href="#" onClick="more()"><span class="text_link" id="more">＞＞<gsmsg:write key="man.advanced.display" /></span></a>
    <logic:equal name="man290Form" property="cmd" value="edit">
      <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush2('290_copy', 'add');">
    </logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

        <logic:equal name="man290Form" property="cmd" value="add">
          <input type="hidden" name="CMD" value="290_ok">
          <input type="submit" value="<gsmsg:write key="cmn.entry.2" />" class="btn_add_n1">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('290_back');">
        </logic:equal>

        <logic:equal name="man290Form" property="cmd" value="edit">
          <input type="hidden" name="CMD" value="290_ok">
          <input type="submit" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1">
          <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('290_del');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('290_back');">
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
    <% tdColor = tdColors[0]; %>

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr id="moreSettings" style="display:none;">
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="main.man290.2" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
      <table width="87%">

      <tr>
      <td colspan="3" width="100%" nowrap>
      <html:radio name="man290Form" property="man290ExtKbn" value="1" styleId="man290ExtKbn0" onclick="setDisabled();"/>
      <span class="text_base2"><label for="man290ExtKbn0"><gsmsg:write key="cmn.everyday" /></label></span>&nbsp;
      </td>
      </tr>

      <tr>
      <td colspan="1" width="10%" nowrap>
      <html:radio name="man290Form" property="man290ExtKbn" value="2" styleId="man290ExtKbn1" onclick="setDisabled();"/>
      <span class="text_base2"><label for="man290ExtKbn1"><gsmsg:write key="cmn.weekly2" /></label></span></td>
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
        <th class="td_type9"><html:multibox property="man290Dweek" value="1"/></th>
        <th class="td_type1"><html:multibox property="man290Dweek" value="2"/></th>
        <th class="td_type1"><html:multibox property="man290Dweek" value="3"/></th>
        <th class="td_type1"><html:multibox property="man290Dweek" value="4"/></th>
        <th class="td_type1"><html:multibox property="man290Dweek" value="5"/></th>
        <th class="td_type1"><html:multibox property="man290Dweek" value="6"/></th>
        <th class="td_type8"><html:multibox property="man290Dweek" value="7"/></th>
        </tr>

        </table>
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>
      <html:radio name="man290Form" property="man290ExtKbn" value="3" styleId="man290ExtKbn2" onclick="setDisabled();"/>
      <span class="text_base2"><label for="man290ExtKbn2"><gsmsg:write key="cmn.monthly.2" /></label></span>&nbsp;</td>
      <td colspan="1" align="center" nowrap><span class="text_base2"><gsmsg:write key="cmn.week" />：</span>
      <html:select property="man290Week" onchange="changeWeekCombo();">
        <html:optionsCollection name="man290Form" property="man290WeekLabel" value="value" label="label" />
      </html:select>
      &nbsp;
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>&nbsp;</td>
      <td colspan="1" align="center" nowrap><span class="text_base2"><gsmsg:write key="cmn.day" />：</span>
      <html:select property="man290Day">
        <html:optionsCollection name="man290Form" property="man290ExDayLabel" value="value" label="label" />
      </html:select>
      &nbsp;
      </td>
      <td colspan="1" nowrap>
      </td>
      </tr>

      <tr>
      <td>
      <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">
      </td></tr>

      <tr>
      <td colspan="3" width="100%" nowrap><span class="text_r1"><gsmsg:write key="main.man290.3" /></span></td>
      </tr>
      <tr>
      <td colspan="3" width="100%" nowrap>
      <html:radio name="man290Form" property="man290HolKbn" value="0" styleId="man290HolidayKbn0"/>
      <span class="text_base2"><label for="man290HolidayKbn0"><gsmsg:write key="cmn.displayed.as" /></label></span>
      </td>
      </tr>
      <tr>
      <td colspan="3" width="100%" nowrap>
      <html:radio name="man290Form" property="man290HolKbn" value="1" styleId="man290HolidayKbn1"/>
      <span class="text_base2"><label for="man290HolidayKbn1"><gsmsg:write key="cmn.dont.show" /></label></span>
      </td>
      </tr>
      <tr>
      <td colspan="3" width="100%" nowrap>
      <html:radio name="man290Form" property="man290HolKbn" value="2" styleId="man290HolidayKbn2"/>
      <span class="text_base2"><label for="man290HolidayKbn2"><gsmsg:write key="main.man290.6" /></label></span>
      </td>
      </tr>
      <tr>
      <td colspan="3" width="100%" nowrap>
      <html:radio name="man290Form" property="man290HolKbn" value="3" styleId="man290HolidayKbn3"/>
      <span class="text_base2"><label for="man290HolidayKbn3"><gsmsg:write key="main.man290.7" /></label></span>
      </td>
      </tr>


      </table>
    </td>
    </tr>

    <tr>
    <td rowspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.period" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
       <html:select property="man290FrYear" styleId="selYear">
        <html:optionsCollection name="man290Form" property="man290YearLabel" value="value" label="label" />
     </html:select>
     <html:select property="man290FrMonth" styleId="selMonth">
        <html:optionsCollection name="man290Form" property="man290MonthLabel" value="value" label="label" />
     </html:select>
     <html:select property="man290FrDay" styleId="selDay">
        <html:optionsCollection name="man290Form" property="man290DayLabel" value="value" label="label" />
     </html:select>
       <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDay, this.form.selMonth, this.form.man290FrYear, 'man290FrCalBtn')" class="calendar_btn" id="man290FrCalBtn">

       <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 1)">
       <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 2)">
       <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 3)">

     <html:select property="man290FrHour">
        <html:optionsCollection name="man290Form" property="man290HourLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.hour.input" />
     <html:select property="man290FrMin">
        <html:optionsCollection name="man290Form" property="man290MinuteLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.minute.input" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
     <html:select property="man290ToYear" styleId="seleYear">
        <html:optionsCollection name="man290Form" property="man290YearLabel" value="value" label="label" />
     </html:select>
     <html:select property="man290ToMonth" styleId="seleMonth">
        <html:optionsCollection name="man290Form" property="man290MonthLabel" value="value" label="label" />
     </html:select>
     <html:select property="man290ToDay" styleId="seleDay">
        <html:optionsCollection name="man290Form" property="man290DayLabel" value="value" label="label" />
     </html:select>
     <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.seleDay, this.form.seleMonth, this.form.seleYear, 'man290ToCalBtn')" class="calendar_btn" id="man290ToCalBtn">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#seleYear')[0], $('#seleMonth')[0], $('#seleDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#seleYear')[0], $('#seleMonth')[0], $('#seleDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#seleYear')[0], $('#seleMonth')[0], $('#seleDay')[0], 3)">

     <html:select property="man290ToHour">
        <html:optionsCollection name="man290Form" property="man290HourLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.hour.input" />
     <html:select property="man290ToMin">
        <html:optionsCollection name="man290Form" property="man290MinuteLabel" value="value" label="label" />
     </html:select>
     <gsmsg:write key="cmn.minute.input" />
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.message" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="<%= tdColor %>" >
    <html:text name="man290Form" style="width:575px;" maxlength="150" property="man290Msg" styleClass="text_base" styleId="selectionSearchArea" />
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="<%= tdColor %>"><textarea name="man290Value" style="width:455px;" rows="10" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength');" id="inputstr2" <%= valueFocusEvent %>><bean:write name="man290Form" property="man290Value" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>


    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="main.exposed" /></span></td>
    <td align="left" class="td_type20">

      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="main.exposed" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="30%" align="left">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.man290groupSid, 'man290groupSid', '<bean:write name="man290Form" property="man290groupSid" />', '0', '290_group', 'man290memberSid', '-1', '1')" type="button">
        <html:select property="man290groupSid" styleClass="select01" onchange="selectGroup('290_group');">
          <logic:notEmpty name="man290Form" property="man290GroupList">
          <html:optionsCollection name="man290Form" property="man290GroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="15%" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.man290groupSid, 'man290groupSid', '0', '290_group')" class="group_btn2" value="&nbsp;&nbsp;" id="man290GroupBtn">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="man290SelectLeftUser" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man290Form" property="man290LeftUserList">
          <html:optionsCollection name="man290Form" property="man290LeftUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('290_leftarrow');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('290_rightarrow');">
      </td>
      <td>
        <html:select property="man290SelectRightUser" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man290Form" property="man290RightUserList">
          <html:optionsCollection name="man290Form" property="man290RightUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td align="left" class="td_type20">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delTempFile');">
      &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('man290files', 'main', '0', '0');">
      <br>
      <html:select property="man290files" styleClass="select01" multiple="true">
        <html:optionsCollection name="man290Form" property="man290FileLabelList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    </table>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
  <tr>
  <td align="left">
    </td>
    <td align="right">

        <logic:equal name="man290Form" property="cmd" value="add">
          <input type="submit" value="<gsmsg:write key="cmn.entry.2" />" class="btn_add_n1">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('290_back');">
        </logic:equal>

        <logic:equal name="man290Form" property="cmd" value="edit">
          <input type="submit" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1">
          <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('290_del');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('290_back');">
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