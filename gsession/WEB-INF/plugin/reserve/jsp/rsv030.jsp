<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />[<gsmsg:write key="cmn.monthly" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../reserve/js/rsv030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript">
<!--
  <logic:notEqual name="rsv030Form" property="rsv030Reload" value="0">
    var reloadinterval = <bean:write name="rsv030Form" property="rsv030Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% long rsvTipCnt = 0; %>
<body class="body_03" onload="init();" onunload="windowClose();calWindowClose();">
<html:form action="/reserve/rsv030">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedYoyakuSid" />
<html:hidden property="rsvSelectedDate" />
<html:hidden property="rsv030ClearTargetKey" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv030Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv030Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv030Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv030Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table width="100%">
<tr>
<td width="100%" align="center">
  <table width="100%" class="tl0">
  <tr>
  <td colspan="7">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
      <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.between.mon" /> ]</td>
      <td width="100%" class="header_white_bg">
        <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
        <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf_gek');">
        <input type="button" name="btn_sisetu_settei" class="btn_base_rsv1" value="<gsmsg:write key="reserve.3" />" onclick="buttonPush('ikkatu_yoyaku');">
        <logic:equal name="rsv030Form" property="rsvGroupEditFlg" value="true">
          <input type="button" name="btn_sisetu_settei" class="btn_base_rsv2" value="<gsmsg:write key="reserve.settings" />" onclick="buttonPush('sisetu_settei');">
        </logic:equal>
        <logic:equal name="rsv030Form" property="rsvAdmFlg" value="true">
          <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onclick="buttonPush('kanrisya_settei');">
        </logic:equal>
        <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onclick="buttonPush('kozin_settei');">
      <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
      </table>
    </td>
    </tr>
    <tr>
    <td colspan="7" class="td_type0">
      <table width="100%" class="tl0" border="0">
      <tr>
      <td width="7%" align="left" nowrap>
        <span class="text_bb1"><gsmsg:write key="cmn.group" />：</span><br><img src="../common/images/spacer.gif" width="1" height="5" border="0" alt=""><br>
        <span class="text_bb1"><gsmsg:write key="cmn.facility" />：</span>
      </td>
      <td width="13%" align="left">
        <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeGroupCombo();">
          <html:optionsCollection name="rsv030Form" property="rsvGrpLabelList" value="value" label="label" />
        </html:select><br><img src="../common/images/spacer.gif" width="1" height="5" border="0" alt=""><br>
        <html:select property="rsvSelectedSisetuSid" styleClass="select01" onchange="changeGroupCombo();">
          <html:optionsCollection name="rsv030Form" property="rsvSisetuLabelList" value="value" label="label" />
        </html:select>
      </td>
      <td width="50%" align="right">&nbsp;</td>
      <td width="38%" align="center"  nowrap>
        <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onclick="buttonPush('nikkan');">
        <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onclick="buttonPush('syukan');">
        <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onclick="buttonPush('riyo_zyokyo_syokai');">
      </td>

      <td width="2%" align="right" nowrap>
        <img src="../common/images/spacer.gif" width="25" height="1" border="0" alt="">
        <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('zengetu_ido');">
        <input type="button" class="btn_today" value="<gsmsg:write key="cmn.thismonth3" />" onClick="buttonPush('kongetu');">
        <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('yokutuki_ido');">
        <img src="../common/images/spacer.gif" width="25" height="1" border="0" alt="">
      </td>
      <td width="0%" align="right" nowrap>
        <input type="button" value="Cal" onclick="resetCmd();wrtCalendar(this.form.rsvDspFrom);" class="calendar_btn2">
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

    <logic:empty name="rsv030Form" property="rsvIkkatuTorokuHiddenList">
      <table width="100%">
      <tr>
      <td width="100%" valign="top">
    </logic:empty>

    <logic:notEmpty name="rsv030Form" property="rsvIkkatuTorokuHiddenList">
      <table width="100%">
      <tr>
      <td width="85%" valign="top">
    </logic:notEmpty>

    <table width="100%" class="tl0" align="left">
    <tr>
    <th colspan="7" class="table_bg_7D91BD">
      <table width="100%">
      <tr>
      <th width="0%" align="left" nowrap>
        <span class="text_tl1"><font color="ffffff"><bean:write name="rsv030Form" property="rsvDispYm" /></font></span></th>
      <td width="100%" height="26" align="right" nowrap>
        <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
        <html:text name="rsv030Form" property="rsv100KeyWord" styleClass="text_base" maxlength="50" onkeydown="return keyPress(event.keyCode);"/>
        <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onclick="buttonPush('gotosearch');">
      </td>
      </tr>
      </table>
    </th>
    </tr>

    <% jp.groupsession.v2.rsv.biz.RsvCommonBiz rsvCmnBiz = new jp.groupsession.v2.rsv.biz.RsvCommonBiz(); %>
    <tr>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
    <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>

    <logic:notEmpty name="rsv030Form" property="rsv030MonthList" scope="request">
      <logic:iterate id="monthMdl" name="rsv030Form" property="rsv030MonthList" scope="request">

        <logic:notEmpty name="monthMdl" property="yoyakuDayList">
          <logic:iterate id="day" name="monthMdl" property="yoyakuDayList">

            <logic:equal name="day" property="yrkYobi" value="1">
              <tr>
              <td align="left" valign="top" class="td_type9" id="<bean:write name="day" property="ikkatuKey" />" height="60">
            </logic:equal>
            <logic:equal name="day" property="yrkYobi" value="7">
              <td align="left" valign="top" class="td_type8" id="<bean:write name="day" property="ikkatuKey" />" height="60">
            </logic:equal>
            <logic:notEqual name="day" property="yrkYobi" value="1">
              <logic:notEqual name="day" property="yrkYobi" value="7">
                <td align="left" valign="top" class="td_type1" id="<bean:write name="day" property="ikkatuKey" />" height="60">
              </logic:notEqual>
            </logic:notEqual>

            <span id="lt" class="text_tl1">
            <logic:equal name="rsv030Form" property="rsv030AccessAuth" value="1">
              <logic:greaterThan name="rsv030Form" property="rsvSelectedSisetuSid" value="0">
                <a href="#" onclick="moveSisetuAdd('<bean:write name="day" property="yrkDateStr" />', '<bean:write name="rsv030Form" property="rsvSelectedSisetuSid" />');"><img src="../reserve/images/scadd.gif" alt="<gsmsg:write key="reserve.19" />" width="16" height="16" border="0"></a>
              </logic:greaterThan>
              <logic:lessEqual name="rsv030Form" property="rsvSelectedSisetuSid" value="0">
                <a href="#"><image src="../reserve/images/scadd.gif" alt="<gsmsg:write key="reserve.19" />" width="16" height="16" border="0"></a>
              </logic:lessEqual>
              <logic:equal name="day" property="yrkYobi" value="1"><html:multibox name="rsv030Form" property="rsvIkkatuTorokuKey" onclick="backGroundSetting(this, '9');"><bean:write name="day" property="ikkatuKey" /></html:multibox></logic:equal>
              <logic:equal name="day" property="yrkYobi" value="7"><html:multibox name="rsv030Form" property="rsvIkkatuTorokuKey" onclick="backGroundSetting(this, '8');"><bean:write name="day" property="ikkatuKey" /></html:multibox></logic:equal>
              <logic:notEqual name="day" property="yrkYobi" value="1"><logic:notEqual name="day" property="yrkYobi" value="7"><html:multibox name="rsv030Form" property="rsvIkkatuTorokuKey" onclick="backGroundSetting(this, '1');"><bean:write name="day" property="ikkatuKey" /></html:multibox></logic:notEqual></logic:notEqual>
            </logic:equal>
            </span>

            <span id="rt">

              <logic:greaterThan name="rsv030Form" property="rsvSelectedSisetuSid" value="0">
                <a href="#" onclick="moveDailySchedule('<bean:write name="day" property="yrkDateStr" />');">
              </logic:greaterThan>
              <logic:lessEqual name="rsv030Form" property="rsvSelectedSisetuSid" value="0">
                <a href="#">
              </logic:lessEqual>

                <logic:equal name="day" property="yrkMonthKbn" value="1">
                  <logic:empty name="day" property="holName">
                    <logic:equal name="day" property="todayKbn" value="0">
                      <span class="text_base3_today">
                    </logic:equal>
                    <logic:notEqual name="day" property="todayKbn" value="0">
                      <span class="text_base3">
                    </logic:notEqual>
                  </logic:empty>

                  <logic:notEmpty name="day" property="holName">
                    <logic:equal name="day" property="todayKbn" value="0">
                      <span class="text_base4_today">
                    </logic:equal>
                    <logic:notEqual name="day" property="todayKbn" value="0">
                      <span class="text_base4">
                    </logic:notEqual>
                  </logic:notEmpty>
                </logic:equal>

                <logic:notEqual name="day" property="yrkMonthKbn" value="1">
                  <logic:empty name="day" property="holName">
                    <logic:equal name="day" property="todayKbn" value="0">
                      <span class="text_base2_today">
                    </logic:equal>
                    <logic:notEqual name="day" property="todayKbn" value="0">
                      <span class="text_base2">
                    </logic:notEqual>
                  </logic:empty>
                  <logic:notEmpty name="day" property="holName">
                    <logic:equal name="day" property="todayKbn" value="0">
                      <span class="text_base6_today">
                    </logic:equal>
                    <logic:notEqual name="day" property="todayKbn" value="0">
                      <span class="text_base6">
                    </logic:notEqual>
                  </logic:notEmpty>
                </logic:notEqual>
                <bean:write name="day" property="yrkDateDay" />
                </span>
              </a>
            </span>

            <br>
            <logic:notEmpty name="day" property="holName">
              <span id="rt"><font size="-2" color="#ff0000"><bean:write name="day" property="holName" /></font></span>
            </logic:notEmpty>
            <br>

            <logic:notEmpty name="day" property="yoyakuList">
              <logic:iterate id="yrk" name="day" property="yoyakuList" indexId="idx">
                <bean:define id="index" value="<%= String.valueOf(((Integer) idx).intValue()) %>" />
                <logic:notEqual name="index" value="0"><br><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"><br></logic:notEqual>

                  <bean:define id="s_rsdSid" name="rsv030Form" property="rsvSelectedSisetuSid" type="java.lang.Integer" />
                  <bean:define id="s_rsySid" name="yrk" property="rsySid" type="java.lang.Integer" />
                  <bean:define id="s_date" name="day" property="yrkDateStr"  type="java.lang.String" />

                  <bean:define id="rsvSisApprStatus" name="yrk" property="rsyApprStatus" type="java.lang.Integer" />
                  <bean:define id="rsvSisApprKbn" name="yrk" property="rsyApprKbn" type="java.lang.Integer" />
                  <% String[] mokApprStatus = rsvCmnBiz.getMokApprStatus(request.getLocale(), rsvSisApprStatus.intValue(), rsvSisApprKbn.intValue()); %>

                  <logic:notEmpty name="yrk" property="rsyNaiyo">
                  <bean:define id="scnaiyou" name="yrk" property="rsyNaiyo" />
                  <%
                    String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                    String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                  %>
                  <a href="#" id="tooltips_rsv<%= String.valueOf(rsvTipCnt++) %>" onclick="moveSisetuEdit('<bean:write name="yrk" property="rsySid" />');">
                  <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                  </logic:notEmpty>
                  <logic:empty name="yrk" property="rsyNaiyo">
                  <a href="#" id="tooltips_rsv<%= String.valueOf(rsvTipCnt++) %>" onclick="moveSisetuEdit('<bean:write name="yrk" property="rsySid" />');">
                  <span class="tooltips"><%= mokApprStatus[2] %><bean:write name="yrk" property="rsyMok" /></span>
                  </logic:empty>

                    <logic:notEmpty name="yrk" property="yrkRiyoDateStr"><span class="<%= mokApprStatus[0] %>"><bean:write name="yrk" property="yrkRiyoDateStr" /></span><br></logic:notEmpty>
                    <span class="<%= mokApprStatus[1] %>">
                      <logic:notEmpty name="yrk" property="rsyMok"><%= mokApprStatus[2] %><bean:write name="yrk" property="rsyMok" /></logic:notEmpty>
                      <logic:notEmpty name="yrk" property="rsyMok">
                        <logic:notEmpty name="yrk" property="yrkName">&nbsp;/&nbsp;</logic:notEmpty>
                      </logic:notEmpty>
                      <logic:notEmpty name="yrk" property="yrkName"><bean:write name="yrk" property="yrkName" /></logic:notEmpty>
                    </span>
                  </a>

              </logic:iterate>
            </logic:notEmpty>

            </td>
            <logic:equal name="day" property="yrkYobi" value="7">
              </tr>
            </logic:equal>

          </logic:iterate>
        </logic:notEmpty>

      </logic:iterate>
    </logic:notEmpty>

    </td>
    </tr>
    </table>

    </td>

    <logic:notEmpty name="rsv030Form" property="rsvIkkatuTorokuHiddenList">
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

          <logic:iterate id="day" name="rsv030Form" property="rsvIkkatuTorokuHiddenList" scope="request">
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
     </tr>
    </logic:notEmpty>

    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>
<%--
<script type="text/javascript">
<% for (long rsvIdCnt = 0; rsvIdCnt < rsvTipCnt; rsvIdCnt++) { %>
$(function() {
  $('#tooltips_rsv<%= String.valueOf(rsvIdCnt) %>').tooltip();
});
<% } %>
</script>
--%>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>