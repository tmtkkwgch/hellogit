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
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />[<gsmsg:write key="cmn.weeks" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../reserve/js/rsv010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript">
<!--
  <logic:notEqual name="rsv010Form" property="rsv010Reload" value="0">
    var reloadinterval = <bean:write name="rsv010Form" property="rsv010Reload" />;
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
<html:form action="/reserve/rsv010">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvSelectedYoyakuSid" />
<html:hidden property="rsvSelectedDate" />
<html:hidden property="rsv010ClearTargetKey" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHiddenParam.jsp" %>

<logic:notEmpty name="rsv010Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv010Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv010Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv010Form" property="rsvSelectedIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table width="100%">
<tr>
<td width="100%" align="center">
  <table width="100%" class="tl0">
  <tr>
  <td colspan="8">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.weekly" /> ]</td>
    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
      <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
      <input type="button" name="btn_sisetu_settei" class="btn_base_rsv1" value="<gsmsg:write key="reserve.3" />" onclick="buttonPush('ikkatu_yoyaku');">
      <logic:equal name="rsv010Form" property="rsvGroupEditFlg" value="true">
        <input type="button" name="btn_sisetu_settei" class="btn_base_rsv2" value="<gsmsg:write key="reserve.settings" />" onclick="buttonPush('sisetu_settei');">
      </logic:equal>
      <logic:equal name="rsv010Form" property="rsvAdmFlg" value="true">
        <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onclick="buttonPush('kanrisya_settei');">
      </logic:equal>
      <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onclick="buttonPush('kozin_settei');">
    </td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td colspan="8" class="td_type0">
    <table width="100%" class="tl0" border="0">
    <tr>
    <td width="20%" align="left">
      <table border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td nowrap>
        <span class="text_bb1"><gsmsg:write key="cmn.group" />：</span>
        <html:select property="rsvSelectedGrpSid" styleClass="select01" onchange="changeGroupCombo();">
          <html:optionsCollection name="rsv010Form" property="rsvGrpLabelList" value="value" label="label" />
        </html:select>
      </td>
      </tr>
      </table>
    </td>
    <td width="40%" align="right">&nbsp;</td>
    <td width="40%" align="center" nowrap>
      <input type="button" value="<gsmsg:write key="cmn.days" />" class="btn_day" onclick="buttonPush('nikkan');">
      <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week">
      <input type="button" value="<gsmsg:write key="cmn.listof" />" class="btn_schedule_search" onclick="moveItiran('riyo_zyokyo_syokai');">
    </td>
    <td width="0%" align="right" nowrap>
      <input type="button" class="btn_arrow1_l" value="&nbsp;" onclick="buttonPush('zensyu_ido');">
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('zenzitu_ido');">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('kyo');">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('yokuzitu_ido')">
      <input type="button" class="btn_arrow1_r" value="&nbsp;" onclick="buttonPush('yokusyu_ido');">

    </td>
    <td width="0%" align="right" nowrap>
      <input type="button" value="Cal" onclick="resetCmd();wrtCalendarByBtn(this.form.rsvDspFrom, 'rsv010Btn')" class="calendar_btn2", id="rsv010Btn">
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

  <logic:empty name="rsv010Form" property="rsvIkkatuTorokuHiddenList">
    <table width="100%">
    <tr>
    <td width="100%" valign="top">
  </logic:empty>

  <logic:notEmpty name="rsv010Form" property="rsvIkkatuTorokuHiddenList">
    <table width="100%">
    <tr>
    <td width="85%" valign="top">
  </logic:notEmpty>

  <table width="100%" class="tl0" align="left">
  <tr>
  <th colspan="8" align="right" width="100%" class="table_bg_7D91BD">
    <table width="100%">
    <tr>
    <th width="0%" height="26" align="left" nowrap>
      <span class="text_tl1"><bean:write name="rsv010Form" property="rsvDispYm" /></span>
    </th>
    <td width="100%" height="26" align="right" nowrap>
      <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
      <html:text name="rsv010Form" property="rsv100KeyWord" styleClass="text_base" maxlength="50" onkeydown="return keyPress(event.keyCode);" style="width:155px;"/>
      <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onclick="buttonPush('gotosearch');">
    </td>
    </tr>
    </table>
  </tr>

  <tr>
  <th width="16%" class="td_type3" style="padding:5px 0px 5px 0px;"><span class="sc_ttl_def"><gsmsg:write key="cmn.facility.name" /></span></th>

  <logic:empty name="rsv010Form" property="rsv010SisetuList" scope="request">
    <bean:define id="daylyMoveFlg" value="0" />
  </logic:empty>
  <logic:notEmpty name="rsv010Form" property="rsv010SisetuList" scope="request">
    <bean:define id="daylyMoveFlg" value="1" />
  </logic:notEmpty>

    <logic:notEmpty name="rsv010Form" property="rsv010CalendarList" scope="request">
      <logic:iterate id="calBean" name="rsv010Form" property="rsv010CalendarList" scope="request">

        <logic:equal name="calBean" property="todayKbn" value="0">
          <th width="12%" nowrap class="td_cal_today">
        </logic:equal>
        <logic:equal name="calBean" property="todayKbn" value="1">
          <th width="12%" nowrap class="td_type3">
        </logic:equal>

        <logic:equal name="calBean" property="holidayKbn" value="1">
          <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
            <span class="sc_ttl_sun"><bean:write name="calBean" property="dspDayString" /></span>
          </a>
        </logic:equal>

       <logic:notEqual name="calBean" property="holidayKbn" value="1">
         <logic:equal name="calBean" property="weekKbn" value="1">
           <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
             <span class="sc_ttl_sun"><bean:write name="calBean" property="dspDayString" /></span>
           </a>
         </logic:equal>

         <logic:equal name="calBean" property="weekKbn" value="7">
           <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
             <span class="sc_ttl_sat"><bean:write name="calBean" property="dspDayString" /></span>
           </a>
         </logic:equal>

         <logic:notEqual name="calBean" property="weekKbn" value="1">
           <logic:notEqual name="calBean" property="weekKbn" value="7">
             <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
               <span class="sc_ttl_def"><bean:write name="calBean" property="dspDayString" /></span>
             </a>
           </logic:notEqual>
         </logic:notEqual>
       </logic:notEqual>

       </th>

     </logic:iterate>
   </logic:notEmpty>

  </tr>

  <logic:notEmpty name="rsv010Form" property="rsv010SisetuList" scope="request">
    <logic:iterate id="sisetu" name="rsv010Form" property="rsv010SisetuList" scope="request" indexId="cnt">

      <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
      <logic:equal name="ret" value="0">
        <logic:greaterThan name="cnt" value="0">
        <tr>
        <th width="16%" class="td_type3" style="padding:5px 0px 5px 0px;"><span class="sc_ttl_def"><gsmsg:write key="cmn.facility.name" /></span></th>

        <logic:empty name="rsv010Form" property="rsv010SisetuList" scope="request">
          <bean:define id="daylyMoveFlg" value="0" />
        </logic:empty>
        <logic:notEmpty name="rsv010Form" property="rsv010SisetuList" scope="request">
          <bean:define id="daylyMoveFlg" value="1" />
        </logic:notEmpty>

        <logic:notEmpty name="rsv010Form" property="rsv010CalendarList" scope="request">
          <logic:iterate id="calBean" name="rsv010Form" property="rsv010CalendarList" scope="request">

            <logic:equal name="calBean" property="todayKbn" value="0">
              <th width="12%" nowrap class="td_cal_today">
            </logic:equal>
            <logic:equal name="calBean" property="todayKbn" value="1">
              <th width="12%" nowrap class="td_type3">
            </logic:equal>

            <logic:equal name="calBean" property="holidayKbn" value="1">
              <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
                <span class="sc_ttl_sun"><bean:write name="calBean" property="dspDayString" /></span>
              </a>
            </logic:equal>

           <logic:notEqual name="calBean" property="holidayKbn" value="1">
             <logic:equal name="calBean" property="weekKbn" value="1">
               <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
                 <span class="sc_ttl_sun"><bean:write name="calBean" property="dspDayString" /></span>
               </a>
             </logic:equal>

             <logic:equal name="calBean" property="weekKbn" value="7">
               <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
                 <span class="sc_ttl_sat"><bean:write name="calBean" property="dspDayString" /></span>
               </a>
             </logic:equal>

             <logic:notEqual name="calBean" property="weekKbn" value="1">
               <logic:notEqual name="calBean" property="weekKbn" value="7">
                 <a href="#" onclick="moveDailySchedule('<bean:write name="calBean" property="dspDate" />');">
                   <span class="sc_ttl_def"><bean:write name="calBean" property="dspDayString" /></span>
                 </a>
               </logic:notEqual>
             </logic:notEqual>
           </logic:notEqual>

         </th>

         </logic:iterate>
       </logic:notEmpty>

      </tr>

      </logic:greaterThan>
      </logic:equal>

      <% jp.groupsession.v2.rsv.biz.RsvCommonBiz rsvCmnBiz = new jp.groupsession.v2.rsv.biz.RsvCommonBiz(); %>
      <tr align="left" valign="top">
      <td class="td_type1">
        <a href="#" onclick="openSisetuSyosai(<bean:write name="sisetu" property="rsdSid" />);"><span class="rsv_sc_link_2"><bean:write name="sisetu" property="rsdName" /></span></a><br>
          <logic:notEqual name="sisetu" property="sisetuImgBinSid" value="0">
          <logic:equal name="sisetu" property="rsdInfoSisetuImgDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
            <img src="../reserve/rsv010.do?CMD=getImageFileSisetu&rsvSelectedSisetuSid=<bean:write name="sisetu" property="rsdSid" />&rsv010BinSid=<bean:write name="sisetu" property="sisetuImgBinSid" />" name="sisetuImgName" alt="<gsmsg:write key="reserve.17" />" border="1" class="img_hoge">
          </logic:equal>
          </logic:notEqual>
          <div style="height:3px;">
            <span id="lt">
              <a href="#" name="deluserBtn" onClick="moveGekkanSchedule('<bean:write name="sisetu" property="rsdSid" />');">
              <img src="../common/images/btn_gekkan_bg.gif" width="35" height="18" alt="" border="0">
              <span class="rsv_link_gek"><gsmsg:write key="cmn.monthly" /></span>
              </a>
            </span>
          </div>
       <br>

      <logic:equal name="sisetu" property="rsdInfoSisetuIdDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.55" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdSisetuId" /></span><br>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoSisanKanriDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdSisanKanri">
        <span class="text_base_rsv2"><gsmsg:write key="cmn.asset.register.num" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdSisanKanri" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp1ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp1Value">
        <span class="text_base_rsv2"><bean:write name="sisetu" property="rsvPropHeaderName8" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdProp1Value" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp2ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp2Value">
        <span class="text_base_rsv2"><bean:write name="rsv010Form" property="rsv010PropHeaderName2" />:</span>
        <span class="text_base_rsv">
        <logic:equal name="sisetu" property="rsdProp2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="sisetu" property="rsdProp2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp3ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp3Value">
        <span class="text_base_rsv2">
        <bean:write name="rsv010Form" property="rsv010PropHeaderName3" />:</span>
        <span class="text_base_rsv">
        <logic:equal name="sisetu" property="rsdProp3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="sisetu" property="rsdProp3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp4ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp4Value">
        <span class="text_base_rsv2"><bean:write name="rsv010Form" property="rsv010PropHeaderName4" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdProp4Value" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp5ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp5Value">
        <span class="text_base_rsv2"><bean:write name="rsv010Form" property="rsv010PropHeaderName5" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdProp5Value" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp6ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp6Value">
        <span class="text_base_rsv2"><bean:write name="rsv010Form" property="rsv010PropHeaderName6" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdProp6Value" /><gsmsg:write key="cmn.days.after" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoProp7ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdProp7Value">
        <span class="text_base_rsv2">
        <bean:write name="rsv010Form" property="rsv010PropHeaderName7" />:</span>
        <span class="text_base_rsv">
        <logic:equal name="sisetu" property="rsdProp7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="sisetu" property="rsdProp7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdApprDspFlg" value="true">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.appr.set.title" />:</span>
        <span class="text_base_rsv2">
          <logic:equal name="sisetu" property="rsdApprKbnFlg" value="true"><gsmsg:write key="reserve.appr.set.kbn1" /></logic:equal>
          <logic:notEqual name="sisetu" property="rsdApprKbnFlg" value="true"><gsmsg:write key="reserve.appr.set.kbn2" /></logic:notEqual>
        </span><br>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaComDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdInfoPlaCom">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.location.comments" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdInfoPlaCom" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom1DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom1">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment1" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom1" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom2DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom2">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment2" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom2" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom3DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom3">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment3" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom3" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom4DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom4">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment4" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom4" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom5DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom5">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment5" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom5" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom6DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom6">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment6" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom6" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom7DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom7">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment7" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom7" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom8DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom8">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment8" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom8" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom9DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom9">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment9" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom9" /></span><br>
      </logic:notEmpty>
      </logic:equal>
      <logic:equal name="sisetu" property="rsdInfoPlaceImgCom10DspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdPlaceImgCom10">
        <span class="text_base_rsv2"><gsmsg:write key="reserve.place.comment10" />:</span><span class="text_base_rsv"><bean:write name="sisetu" property="rsdPlaceImgCom10" /></span><br>
      </logic:notEmpty>
      </logic:equal>

      <%-- 備考 --%>
      <logic:equal name="sisetu" property="rsdInfoBikoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
      <logic:notEmpty name="sisetu" property="rsdBiko">
        <div>
          <div class="text_base_rsv2 display_table_cell nowrap"><gsmsg:write key="cmn.memo" />:</div>
          <div class="text_base_rsv display_table_cell"><bean:write name="sisetu" property="rsdBiko" filter="false"/></div>
        </div>
      </logic:notEmpty>
      </logic:equal>

      </td>
      <logic:notEmpty name="sisetu" property="rsvWeekList">
        <logic:iterate id="week" name="sisetu" property="rsvWeekList">

          <logic:notEmpty name="week" property="yoyakuDayList">
            <logic:iterate id="day" name="week" property="yoyakuDayList">

              <logic:equal name="day" property="yrkYobi" value="1">
                <td align="left" valign="top" class="td_type9" id="<bean:write name="day" property="ikkatuKey" />">
              </logic:equal>
              <logic:equal name="day" property="yrkYobi" value="7">
                <td align="left" valign="top" class="td_type8" id="<bean:write name="day" property="ikkatuKey" />">
              </logic:equal>

              <logic:notEqual name="day" property="yrkYobi" value="1">
                <logic:notEqual name="day" property="yrkYobi" value="7">
                  <td align="left" valign="top" class="td_type1" id="<bean:write name="day" property="ikkatuKey" />">
                </logic:notEqual>
              </logic:notEqual>

              <logic:equal name="sisetu" property="racAuth" value="1">
              <span id="lt"><a href="#" onclick="moveSisetuAdd('<bean:write name="day" property="yrkDateStr" />', '<bean:write name="sisetu" property="rsdSid" />');"><img src="../reserve/images/scadd.gif" alt="<gsmsg:write key="reserve.19" />" width="16" height="16" border="0"></a>
              <logic:equal name="day" property="yrkYobi" value="1"><html:multibox name="rsv010Form" property="rsvIkkatuTorokuKey" onclick="backGroundSetting(this, '9');"><bean:write name="day" property="ikkatuKey" /></html:multibox></logic:equal>
              <logic:equal name="day" property="yrkYobi" value="7"><html:multibox name="rsv010Form" property="rsvIkkatuTorokuKey" onclick="backGroundSetting(this, '8');"><bean:write name="day" property="ikkatuKey" /></html:multibox></logic:equal>
              <logic:notEqual name="day" property="yrkYobi" value="1"><logic:notEqual name="day" property="yrkYobi" value="7"><html:multibox name="rsv010Form" property="rsvIkkatuTorokuKey" onclick="backGroundSetting(this, '1');"><bean:write name="day" property="ikkatuKey" /></html:multibox></logic:notEqual></logic:notEqual>
              </span>
              </logic:equal>

              <logic:notEmpty name="day" property="holName"><span id="rt"><font size="-2" color="#ff0000"><bean:write name="day" property="holName" /></font></span></logic:notEmpty>
              <br><br>

              <logic:notEmpty name="day" property="yoyakuList">
                <logic:iterate id="yrk" name="day" property="yoyakuList" indexId="idx">
                  <bean:define id="index" value="<%= String.valueOf(((Integer) idx).intValue()) %>" />

                  <logic:notEqual name="index" value="0"><br><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"><br></logic:notEqual>

                  <bean:define id="s_rsdSid" name="sisetu" property="rsdSid" type="java.lang.Integer" />
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
                  <a href="#" id="tooltips_rsv<%= String.valueOf(rsvTipCnt++) %>" onclick="return moveSisetuEdit('<bean:write name="yrk" property="rsySid" />');">
                  <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                  </logic:notEmpty>
                  <logic:empty name="yrk" property="rsyNaiyo">
                  <a href="#" id="tooltips_rsv<%= String.valueOf(rsvTipCnt++) %>" onclick="return moveSisetuEdit('<bean:write name="yrk" property="rsySid" />');">
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

            </logic:iterate>
          </logic:notEmpty>

         </tr>

        </logic:iterate>
      </logic:notEmpty>

    </logic:iterate>
  </logic:notEmpty>

  </table>

</td>

<logic:notEmpty name="rsv010Form" property="rsvIkkatuTorokuHiddenList">
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

      <logic:iterate id="day" name="rsv010Form" property="rsvIkkatuTorokuHiddenList" scope="request">
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
          <td><img src="../reserve/images/spacer.gif" width="1" height="3"></td>
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