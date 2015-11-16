<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyReserve.tld" prefix="dailyReserve" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />[<gsmsg:write key="cmn.days2" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../reserve/js/rsv020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript">
<!--
  <logic:notEqual name="rsv020Form" property="rsv020Reload" value="0">
    var reloadinterval = <bean:write name="rsv020Form" property="rsv020Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>

  function initPictureRsv() {
    var objImg = document.getElementsByName('sisetuImgName');
    if (objImg) {
      for (idx = 0; idx < objImg.length; idx++) {
        if (objImg[idx].width > 50) {
          objImg[idx].width = 50;
        }
      }
    }
  }
-->
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% long rsvTipCnt = 0; %>
<body class="body_03" onload="init();initPictureRsv();" onunload="windowClose();calWindowClose();">
<html:form action="/reserve/rsv020">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsv020FromHour" />
<html:hidden property="rsv020ToHour" />
<html:hidden property="rsv020ColSpan" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvSelectedYoyakuSid" />
<html:hidden property="rsvSelectedDate" />
<html:hidden property="rsv020ClearTargetKey" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHiddenParam.jsp" %>

<logic:notEmpty name="rsv020Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv020Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv020Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv020Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="fromHour" name="rsv020Form" property="rsv020FromHour" scope="request"/>
<bean:define id="toHour" name="rsv020Form" property="rsv020ToHour" scope="request"/>
<bean:define id="rsvDspFrom" name="rsv020Form" property="rsvDspFrom" scope="request"/>
<bean:define id="colSpan" name="rsv020Form" property="rsv020ColSpan" scope="request"/>
<bean:define id="hourDivCount" name="rsv020Form" property="rsv020HourDivCount" scope="request"/>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">
  <table width="100%" class="tl0">
  <tr>
  <td colspan="<bean:write name="colSpan" />">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.days" /> ]</td>
    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
      <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf_nik');">
      <input type="button" name="btn_sisetu_settei" class="btn_base_rsv1" value="<gsmsg:write key="reserve.3" />" onclick="buttonPush('ikkatu_yoyaku');">
      <logic:equal name="rsv020Form" property="rsvGroupEditFlg" value="true">
        <input type="button" name="btn_sisetu_settei" class="btn_base_rsv2" value="<gsmsg:write key="reserve.settings" />" onclick="buttonPush('sisetu_settei');">
      </logic:equal>

      <logic:notEqual name="rsv020Form" property="rsvGroupEditFlg" value="true">
        <img src="../common/images/spacer.gif" width="80" height="1" border="0" alt="">
      </logic:notEqual>

      <logic:equal name="rsv020Form" property="rsvAdmFlg" value="true">
        <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onclick="buttonPush('kanrisya_settei');">
      </logic:equal>

      <logic:notEqual name="rsv020Form" property="rsvAdmFlg" value="true">
        <logic:notEqual name="rsv020Form" property="rsvGroupEditFlg" value="true">
          <img src="../common/images/spacer.gif" width="80" height="1" border="0" alt="">
        </logic:notEqual>
      </logic:notEqual>

      <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onclick="buttonPush('kozin_settei');">
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td width="100%" colspan="<bean:write name="colSpan" />" class="td_type0">
    <table width="100%" class="tl0" border="0">
    <tr>
    <td width="20%" align="left" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.group" />：</span>
      <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeGroupCombo();">
        <html:optionsCollection name="rsv020Form" property="rsvGrpLabelList" value="value" label="label" />
      </html:select>
    </td>

    <td width="40%" align="center">&nbsp;</td>
    <td width="40%" align="center" nowrap>
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day">
      <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onclick="buttonPush('syukan');">
      <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onclick="moveItiran('riyo_zyokyo_syokai');">
    </td>
    <td width="0%" align="right" nowrap>
      <img src="../common/images/spacer.gif" width="25" height="1" border="0" alt="">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('zenzitu_ido');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('kyo');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('yokuzitu_ido');">
      <img src="../reserve/images/spacer.gif" width="25" height="1" border="0" alt="">
    </td>
    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="resetCmd();wrtCalendarByBtn(this.form.rsvDspFrom, 'rsv020');" class="calendar_btn2", id="rsv020">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
  <td><html:errors /></td>
  </tr>
  </logic:messagesPresent>

  </table>

  <logic:empty name="rsv020Form" property="rsvIkkatuTorokuHiddenList">
    <table width="100%">
    <tr>
    <td width="100%" valign="top">
  </logic:empty>

  <logic:notEmpty name="rsv020Form" property="rsvIkkatuTorokuHiddenList">
    <table width="100%">
    <tr>
    <td width="85%" valign="top">
  </logic:notEmpty>

  <% jp.groupsession.v2.rsv.biz.RsvCommonBiz rsvCmnBiz = new jp.groupsession.v2.rsv.biz.RsvCommonBiz(); %>
  <table width="100%" class="tl0" align="left" id="tooltips_rsv_daily">
  <tr>
  <th colspan="<bean:write name="colSpan" />" class="table_bg_7D91BD">
    <table width="100%">
    <tr>
    <th width="0%" align="left" nowrap><span class="text_tl1"><bean:write name="rsv020Form" property="rsvDispYm" /></font></span></th>
    <td width="100%" height="26" align="right" nowrap>
      <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
      <html:text name="rsv020Form" property="rsv100KeyWord" styleClass="text_base" maxlength="50" onkeydown="return keyPress(event.keyCode);" style="width:155px;"/>
      <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onclick="buttonPush('gotosearch');">
    </td>
    </tr>
    </table>
  </th>
  </tr>

  <tr>
  <th width="19%" class="td_type3" rowspan="2"><span class="sc_ttl_def"><gsmsg:write key="cmn.facility.name" /></span></th>
  <th width="3%" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.new" /></span></th>

  <logic:notEmpty name="rsv020Form" property="rsv020TimeChartList" scope="request">
    <logic:iterate id="strHour" name="rsv020Form" property="rsv020TimeChartList" scope="request" >
      <th colspan="<bean:write name='hourDivCount' />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
    </logic:iterate>
    </tr>

    <tr>
    <logic:iterate id="timeSpc" name="rsv020Form" property="rsv020TimeChartList" scope="request">
<%
    int hdCount = ((Integer) hourDivCount).intValue();
    for(int j=0;j<hdCount;j++){
        out.println("<td class='user_tab_tl'><img src='../reserve/images/space10.gif' width='5'></td>");
    }
%>

    </logic:iterate>
    </tr>
  </logic:notEmpty>

  <logic:notEmpty name="rsv020Form" property="rsv020DaylyList" scope="request">
    <logic:iterate id="sisetu" name="rsv020Form" property="rsv020DaylyList" scope="request" indexId="cnt">
      <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
      <logic:equal name="ret" value="0">
        <logic:greaterThan name="cnt" value="0">

        <tr>
        <th width="19%" class="td_type3" rowspan="2"><span class="sc_ttl_def"><gsmsg:write key="cmn.facility.name" /></span></th>
        <th width="3%" class="td_type3" rowspan="2" nowrap><span class="sc_ttl_def"><gsmsg:write key="cmn.new" /></span></th>

        <logic:notEmpty name="rsv020Form" property="rsv020TimeChartList" scope="request">
          <logic:iterate id="strHour" name="rsv020Form" property="rsv020TimeChartList" scope="request" >
            <th colspan="<bean:write name='hourDivCount' />" nowrap class="td_type3"><span class="sc_ttl_def"><bean:write name="strHour" scope="page" /></span></th>
          </logic:iterate>
          </tr>
          <tr>
          <logic:iterate id="timeSpc" name="rsv020Form" property="rsv020TimeChartList" scope="request">
<%
    int hdCount = ((Integer) hourDivCount).intValue();
    for(int j=0;j<hdCount;j++){
        out.println("<td class='user_tab_tl'><img src='../reserve/images/space10.gif' width='5'></td>");
    }
%>
          </logic:iterate>
          </tr>
        </logic:notEmpty>
        </logic:greaterThan>
      </logic:equal>

      <tr align="left" valign="top">

        <dailyReserve:dailywrite name="sisetu" from="<%= fromHour.toString() %>" to="<%= toHour.toString() %>" dspDate="<%= rsvDspFrom.toString() %>" count="<%= hourDivCount.toString() %>" />

      </tr>
    </logic:iterate>
  </logic:notEmpty>

  </table>

  </td>

  <logic:notEmpty name="rsv020Form" property="rsvIkkatuTorokuHiddenList">
  <td width="15%" valign="top">
    <table width="100%" class="tl0" align="left">
    <tr>
    <th align="right" width="100%" class="table_bg_7D91BD">
      <table width="100%">
      <tr>
      <th width="100%" height="26" align="left" nowrap>
        <span class="text_tl1"><font color="ffffff"><gsmsg:write key="reserve.20" /></font></span>&nbsp;&nbsp;<input type="button" value="<gsmsg:write key="reserve.21" />" class="btn_hdb" onclick="buttonPush('allClear');">
      </th>
      </tr>
      </table>
    </tr>
    <tr>
    <td width="100%" class="search_tbl_base">

      <table width="100%" align="left" valign="top">

      <logic:iterate id="day" name="rsv020Form" property="rsvIkkatuTorokuHiddenList" scope="request">
        <tr>
        <td class="text_hid_day"><bean:write name="day" property="hidDayStr" /></td>
        </tr>

        <logic:iterate id="grp" name="day" property="grpList">
        <tr>
        <td class="text_hid_grp">■<bean:write name="grp" property="rsgName" /></td>
        </tr>

          <logic:iterate id="sisetu" name="grp" property="sisetuList">
          <tr>
          <td width="100%">
            <table class="tl0">
            <tr>
            <td width="80%" class="text_hid_sis">
              <a href="#" onclick="openSisetuSyosai(<bean:write name="sisetu" property="rsdSid" />);"><bean:write name="sisetu" property="rsdName" /></a>
            </td>
            <td width="20%"><input type="button" value="<gsmsg:write key="reserve.22" />" class="btn_hds" onclick="clearSisetu('<bean:write name="sisetu" property="rsvIkkatuTorokuKey" />');"></td>
            </tr>
            </table>
          </td>
          </tr>
          </logic:iterate>

          <tr>
          <td><img src="../common/images/spacer.gif" width="1" height="3"></td>
          </tr>
        </logic:iterate>
      </logic:iterate>

      </table>
    </td>
    </tr>
    </table>
   </td>
   </logic:notEmpty>

   </tr>
  </table>
</td>
</tr>

</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>