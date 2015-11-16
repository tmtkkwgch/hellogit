<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<% String maxLengthContent = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.MAX_LENGTH_VALUE); %>
<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.MAX_LENGTH_BIKO); %>
<html:html>
<head>
<logic:equal name="ntp040Form" property="cmd" value="add">
<title>[GroupSession] <gsmsg:write key="ntp.177" /></title>
</logic:equal>
<logic:equal name="ntp040Form" property="cmd" value="edit">
<title>[GroupSession] <gsmsg:write key="ntp.178" /></title>
</logic:equal>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearchText.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/reservepopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String selectionEvent = ""; %>
<% boolean selectionFlg = false; %>
<% String valueFocusEvent = ""; %>
<% String bikoFocusEvent = ""; %>

<logic:equal name="ntp040Form" property="searchPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
  <% selectionFlg = true; %>
</logic:equal>
<% String closeScript = "calWindowClose();windowClose();"; %>
<logic:equal name="ntp040Form" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/glayer.js"></script>
<script>
  $(function(){
      $('.label_area').inFieldLabels();
      $('.fix_content').exFixed(); // for IE6
  });
</script>
<% closeScript += "companyWindowClose();"; %>
</logic:equal>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="" onunload="<%= closeScript %>">

<html:form action="/nippou/ntp040">
<%@ include file="/WEB-INF/plugin/nippou/jsp/ntp060_hiddenParams.jsp" %>

<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="ntp040InitFlg" />
<html:hidden property="ntp040CopyFlg" />
<html:hidden property="ntp040ScrollFlg" />
<html:hidden property="ntp040BgcolorInit" />
<html:hidden property="ntp040DspMoveFlg" />
<html:hidden property="ntp040AnkenUse" />
<html:hidden property="ntp040CompanyUse" />
<html:hidden property="ntp040AnkenCompanyUse" />
<html:hidden property="ntp040KtBriHhuUse" />
<html:hidden property="ntp040MikomidoUse" />
<html:hidden property="ntp040TmpFileUse" />
<html:hidden property="ntp040NextActionUse" />
<html:hidden property="ntp040AdrHistoryPageNum" />
<html:hidden property="ntp040AnkenHistoryPageNum" />
<html:hidden property="ntp040DefaultValue" />
<html:hidden property="ntp040DefaultValue2" />
<html:hidden property="ntp040MikomidoFlg" />
<html:hidden property="ntp040InitYear" />
<html:hidden property="ntp040InitMonth" />
<html:hidden property="ntp040InitDay" />
<html:hidden property="ntp040schUrl" />
<html:hidden property="ntp040UsrName" />
<html:hidden property="ntp040UsrBinSid" />
<html:hidden property="ntp040UsrPctKbn" />
<html:hidden property="ntp040PrevNtpDate" />
<html:hidden property="ntp040TodayNtpDate" />
<html:hidden property="ntp040NextNtpDate" />
<html:hidden property="scheduleUseOk" />
<html:hidden property="projectUseOk" />
<html:hidden property="ntp010DspDate" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp010searchWord" />
<html:hidden property="ntp020SelectUsrSid" />
<html:hidden property="ntp030SelectUsrSid" />

<logic:notEmpty name="ntp040Form"  property="ntp040FileList">
<logic:iterate id="tempmdl" name="ntp040Form"  property="ntp040FileList">
<html:hidden name="tempmdl" property="binFileName" />
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="ntp040delCompanyId" value="">
<input type="hidden" name="ntp040delCompanyBaseId" value="">

<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />
<html:hidden property="ntp100SelectNtpAreaSid" />
<html:hidden property="ntp100AnkenSid" />
<html:hidden property="ntp100CompanySid" />
<html:hidden property="ntp100CompanyBaseSid" />
<html:hidden property="ntp100SvAnkenSid" />
<html:hidden property="ntp100SvCompanySid" />
<html:hidden property="ntp100SvCompanyBaseSid" />
<html:hidden property="ntp100Ktbunrui" />
<html:hidden property="ntp100Kthouhou" />
<html:hidden property="ntp100SvKtbunrui" />
<html:hidden property="ntp100SvKthouhou" />
<html:hidden property="ntp100SltGroup" />
<html:hidden property="ntp100SltUser" />
<html:hidden property="ntp100SvSltGroup" />
<html:hidden property="ntp100SvSltUser" />
<html:hidden property="ntp100SltYearFr" />
<html:hidden property="ntp100SltMonthFr" />
<html:hidden property="ntp100SltDayFr" />
<html:hidden property="ntp100SltYearTo" />
<html:hidden property="ntp100SltMonthTo" />
<html:hidden property="ntp100SltDayTo" />
<html:hidden property="ntp100SvSltYearFr" />
<html:hidden property="ntp100SvSltMonthFr" />
<html:hidden property="ntp100SvSltDayFr" />
<html:hidden property="ntp100SvSltYearTo" />
<html:hidden property="ntp100SvSltMonthTo" />
<html:hidden property="ntp100SvSltDayTo" />
<html:hidden property="ntp100KeyWordkbn" />
<html:hidden property="ntp100KeyValue" />
<html:hidden property="ntp100SvKeyWordkbn" />
<html:hidden property="ntp100SvKeyValue" />
<html:hidden property="ntp100PageNum" />
<html:hidden property="ntp100Slt_page1" />
<html:hidden property="ntp100Slt_page2" />
<html:hidden property="ntp100OrderKey1" />
<html:hidden property="ntp100SortKey1" />
<html:hidden property="ntp100OrderKey2" />
<html:hidden property="ntp100SortKey2" />
<html:hidden property="ntp100SvOrderKey1" />
<html:hidden property="ntp100SvSortKey1" />
<html:hidden property="ntp100SvOrderKey2" />
<html:hidden property="ntp100SvSortKey2" />
<html:hidden property="ntp100PageNum" />

<bean:write name="ntp040Form" property="ntp040100SvSearchTarget" filter="false"/>
<bean:write name="ntp040Form" property="ntp040100SearchTarget" filter="false" />
<bean:write name="ntp040Form" property="ntp040100SvBgcolor" filter="false" />
<bean:write name="ntp040Form" property="ntp040100Bgcolor" filter="false" />
<bean:write name="ntp040Form" property="ntp040100SvMikomido" filter="false" />
<bean:write name="ntp040Form" property="ntp040100Mikomido" filter="false" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="helpPrm" value="<bean:write name="ntp040Form" property="ntp010SelectUsrKbn" /><bean:write name="ntp040Form" property="cmd" />">

<!--　BODY -->
<bean:write name="ntp040Form" property="ntp040YearLavelStr" filter="false" />
<bean:write name="ntp040Form" property="ntp040MonthLavelStr" filter="false" />
<bean:write name="ntp040Form" property="ntp040DayLavelStr" filter="false" />
<bean:write name="ntp040Form" property="ntp040HourLavelStr" filter="false" />
<bean:write name="ntp040Form" property="ntp040MinuteLavelStr" filter="false" />
<bean:write name="ntp040Form" property="ntp040KtbunruiLavelStr" filter="false" />
<bean:write name="ntp040Form" property="ntp040KthouhouLavelStr" filter="false" />

<input type="hidden" id="frhourhide" value="<bean:write name="ntp040Form" property="ntp040FrHour" />">
<input type="hidden" id="frminhide" value="<bean:write name="ntp040Form" property="ntp040FrMin" />">
<input type="hidden" id="tohourhide" value="<bean:write name="ntp040Form" property="ntp040ToHour" />">
<input type="hidden" id="tominhide" value="<bean:write name="ntp040Form" property="ntp040ToMin" />">
<input type="hidden" id="ktbunruihide" value="<bean:write name="ntp040Form" property="ntp040Ktbunrui" />">
<input type="hidden" id="kthouhouhide" value="<bean:write name="ntp040Form" property="ntp040Kthouhou" />">

<bean:define id="frhourval" name="ntp040Form" property="ntp040FrHour" type="java.lang.String"/>
<bean:define id="frminval" name="ntp040Form" property="ntp040FrMin" type="java.lang.String"/>
<bean:define id="tohourval" name="ntp040Form" property="ntp040ToHour" type="java.lang.String"/>
<bean:define id="tominval" name="ntp040Form" property="ntp040ToMin" type="java.lang.String"/>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="日報"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl">日報</span></td>
    <td width="100%" class="header_white_bg_text">
    <logic:equal name="ntp040Form" property="cmd" value="add">[ 日報登録 ]</td></logic:equal>
    <logic:equal name="ntp040Form" property="cmd" value="edit">[ 日報確認 ]</td></logic:equal>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

        <logic:equal name="ntp040Form" property="cmd" value="add">
        <input type="hidden" name="CMD" value="040_ok">
          <input type="button" id="" value="<gsmsg:write key="cmn.entry.2" />" class="btn_add_n1 ntpAddBtn">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('040_back', '<bean:write name="ntp040Form" property="ntp040BtnCmd" />');">
        </logic:equal>

        <logic:equal name="ntp040Form" property="cmd" value="edit">
          <input type="hidden" name="CMD" value="040_ok">
          <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="backButtonPush('040_back', '<bean:write name="ntp040Form" property="ntp040BtnCmd" />');">
        </logic:equal>

    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">

    <bean:define id="tdColor" value="td_wt" />
    <table width="100%" class="tl0" border="0" cellpadding="5">
      <logic:equal name="ntp040Form" property="ntp040ExTextDspFlg" value="true">
      <tr>
      <td align="left" width="100%" colspan="2" nowrap>
        <span class="text_base"><gsmsg:write key="schedule.149" /></span>
      </td>
      </tr>
    </logic:equal>

    <logic:equal name="ntp040Form" property="cmd" value="add">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="schedule.4" /></span></td>
      <td class="<%= tdColor %>" align="left" width="80%">
      <logic:notEqual name="ntp040Form" property="ntp010SelectUsrKbn" value="0">
      <span id="lt"><img src="../common/images/groupicon.gif" alt="<gsmsg:write key="cmn.group" />" border="0"></span>
      </logic:notEqual>
      <span class="text_base"><bean:write name="ntp040Form" property="ntp040UsrName" /></span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">報告日付</span><span class="text_r2">※</span></td>
      <td align="left" class="<%= tdColor %>" nowrap>
        <html:select property="ntp040FrYear" styleId="selYear">
          <html:optionsCollection name="ntp040Form" property="ntp040YearLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040FrMonth" styleId="selMonth">
          <html:optionsCollection name="ntp040Form" property="ntp040MonthLavel" value="value" label="label" />
        </html:select>

        <html:select property="ntp040FrDay" styleId="selDay">
          <html:optionsCollection name="ntp040Form" property="ntp040DayLavel" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDay, this.form.selMonth, this.form.ntp040FrYear, 'ntp040FrCalBtn')" class="calendar_btn" id="ntp040FrCalBtn">
        <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 1)">
        <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 2)">
        <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYear')[0], $('#selMonth')[0], $('#selDay')[0], 3)">
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
      <td align="left" class="<%= tdColor %>">
        <table class="tl0" width="100%">
        <tr>
        <td nowrap>
        <span class="text_base">
        <logic:notEqual name="ntp040Form" property="ntp040AddUsrJkbn" value="9">
        <bean:write name="ntp040Form" property="ntp040AddUsrName" />
        </logic:notEqual>
        <logic:equal name="ntp040Form" property="ntp040AddUsrJkbn" value="9">
        <del><bean:write name="ntp040Form" property="ntp040AddUsrName" /></del>
        </logic:equal>
        </span>
        </td>
        </tr>
        </table>
      </td>
      </tr>

   </logic:equal>
  </table>


  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <logic:notEmpty name="ntp040Form" property="ntp040DspTargetMdlList">
  <logic:iterate id="trgDspMdl" name="ntp040Form" property="ntp040DspTargetMdlList" indexId="trgListIdx">
  <logic:notEmpty name="trgDspMdl" property="ntgList">
    <input type="hidden" id="hideYear" value="<bean:write name="trgDspMdl" property="year" />" />
    <input type="hidden" id="hideMonth" value="<bean:write name="trgDspMdl" property="month" />" />
    <input type="hidden" id="hideUsrSid" value="<bean:write name="trgDspMdl" property="usrSid" />" />
    <input type="hidden" id="hideNttSid" value="<bean:write name="trgDspMdl" property="nttSid" />" />

    <span id="pos-1"></span>
    <div id="trgSetArea">
      <div id="trgDataSetArea">
        <logic:equal name="ntp040Form" property="cmd" value="add">
          <table class="tl0" cellpadding="5" border="0" width="100%">
            <tbody>
              <bean:define id="trgList" name="trgDspMdl" property="ntgList" />
              <bean:size id="listSize" name="trgList" />
              <tr>
                <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">
                  <logic:notEmpty name="ntgMdl" property="npgTargetName">
                    <td width="25%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><bean:write name="ntgMdl" property="npgTargetName" /></span></td>
                  </logic:notEmpty>
                  <logic:empty name="ntgMdl" property="npgTargetName">
                    <td width="25%"></td>
                  </logic:empty>
                </logic:iterate>
              </tr>

              <tr>
                <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">
                  <logic:notEmpty name="ntgMdl" property="npgTargetName">
                    <td width="25%" class="td_wt td_target" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" align="center">
                      <span class="text_base">
                        <input name="" style="text-align:right; width:108px;" maxlength="15" value="<bean:write name="ntgMdl" property="npgRecord" />" id="val_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" class="text_base" type="text">
                        <wbr>/<span id="valTrg_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />"><bean:write name="ntgMdl" property="npgTarget" /></span>
                      </span>
                      <wbr><bean:write name="ntgMdl" property="npgTargetUnit" /><br>
                      <input class="target_settei_btn resetTrgBtn" id="resetTrg_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="リセット" type="button">
                    </td>
                  </logic:notEmpty>
                  <logic:empty name="ntgMdl" property="npgTargetName">
                    <td width="25%"></td>
                  </logic:empty>
                </logic:iterate>
              </tr>
            </tbody>
          </table>
        </logic:equal>

        <logic:equal name="ntp040Form" property="cmd" value="edit">
          <table class="tl0" cellpadding="5" border="0" width="100%">
            <tbody>
              <bean:define id="trgList" name="trgDspMdl" property="ntgList" />
              <bean:size id="listSize" name="trgList" />
                    <tr>
                      <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">
                        <logic:notEmpty name="ntgMdl" property="npgTargetName">
                          <td width="25%" nowrap class="table_bg_A5B4E1" align="center"><span class="text_bb1"><bean:write name="ntgMdl" property="npgTargetName" /></span></td>
                        </logic:notEmpty>
                        <logic:empty name="ntgMdl" property="npgTargetName">
                          <td></td>
                        </logic:empty>
                      </logic:iterate>
                    </tr>

                    <tr>
                    <logic:iterate id="ntgMdl" name="trgDspMdl" property="ntgList" indexId="trgIdx">
                    <logic:notEmpty name="ntgMdl" property="npgTargetName">
                    <% String recordColor = ""; %>
                    <logic:equal name="ntgMdl" property="npgTargetKbn" value="1">
                    <% recordColor = "record_comp"; %>
                    </logic:equal>

                    <td nowrap class="td_wt td_target" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" align="center">
                      <table class="table_border_none">
                        <tr>
                          <td>
                            <span class="text_base">
                              <span class="recordArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">
                                <span class="<%= recordColor %>" id="recordAreaText_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">
                                  <bean:write name="ntgMdl" property="npgRecord" />
                                </span>
                              </span>
                              <span class="recordArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" style="display:none;">
                                <input name="" style="text-align:right; width:108px;" maxlength="15" value="<bean:write name="ntgMdl" property="npgRecord" />" id="trgRecord_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" class="text_base" type="text">
                              </span>
                              /<span id="valTrg_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />"><bean:write name="ntgMdl" property="npgTarget" /></span>
                            </span>
                            <bean:write name="ntgMdl" property="npgTargetUnit" />&nbsp;&nbsp;
                          </td>
                        </tr>
                        <tr>
                          <td align="center">
                            <logic:equal name="ntp040Form" property="ntp040TargetAdmKbn" value="0">
                              <div class="trgBtnArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" style="display:block;">
                                <input class="target_settei_btn changeTrgBtn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="変更" type="button">
                              </div>

                              <div style="display:none;" class="trgBtnArea_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />">
                                <table class="table_border_none">
                                  <tr>
                                    <td>
                                      <input class="target_kakutei_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="確定" type="button">
                                    </td>
                                    <td>
                                      <input class="target_cansel_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="trgDspMdl" property="usrSid" />_<bean:write name="ntgMdl" property="ntgSid" />" value="キャンセル" type="button">
                                    </td>
                                  </tr>
                                </table>
                              </div>
                            </logic:equal>
                          </td>
                        </tr>
                      </table>
                    </td>
                    </logic:notEmpty>

                    <logic:empty name="ntgMdl" property="npgTargetName">
                      <td></td>
                    </logic:empty>
                  </logic:iterate>
                  </tr>
            </tbody>
          </table>
        </logic:equal>
      </div>
    </div>
  </logic:notEmpty>

  <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">

  </logic:iterate>
  </logic:notEmpty>

  <table id="nippoutable" cellpadding="0" cellspacing="0" border="0" width="100%">
    <logic:equal name="ntp040Form" property="cmd" value="add">
      <%@ include file="/WEB-INF/plugin/nippou/jsp/ntp040_add.jsp" %>
    </logic:equal>
    <logic:equal name="ntp040Form" property="cmd" value="edit">
     <tr>
        <td class="table_bg_A5B4E1 text_bb1" align="center" nowrap>
          <table width="100%">
            <tr>
              <td width="50%" align="left">
                <b><span class="<bean:write name="ntp040Form" property="ntp040DspDateKbn" />"><bean:write name="ntp040Form" property="ntp040FrYear" />年<bean:write name="ntp040Form" property="ntp040FrMonth" />月<bean:write name="ntp040Form" property="ntp040FrDay" />日<span style="font-size:12px;">(<bean:write name="ntp040Form" property="ntp040DspDateKbnStr" />)</span></span></b>
              </td>
              <td width="50%" align="right">
                <input type="button" value="前日" id="<bean:write name="ntp040Form" property="ntp040PrevNtpSid" />" class="btn_previous_n1 ntpPrevBtn">&nbsp;
                <input type="button" value="今日" id="<bean:write name="ntp040Form" property="ntp040TodayNtpSid" />" class="btn_base1 ntpTodayBtn">&nbsp;
                <input type="button" value="翌日" id="<bean:write name="ntp040Form" property="ntp040NextNtpSid" />" class="btn_next_n1 ntpNextBtn">
              </td>
            </tr>
            </table>
        </td>
      </tr>
      <%@ include file="/WEB-INF/plugin/nippou/jsp/ntp040_edit.jsp" %>
    </logic:equal>
    <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">

    <table cellpadding="0" width="100%">
      <logic:equal name="ntp040Form" property="cmd" value="add">
       <tr>
         <td align="left"></td>
         <td align="center"><input value="行追加" class="btn_add_n1" id="trAddBtn" type="button"></td>
       </tr>
     </logic:equal>

     <logic:equal name="ntp040Form" property="cmd" value="edit">
      <logic:equal name="ntp040Form" property="authAddEditKbn" value="0">
       <tr>
         <td align="left"></td>
         <td align="center"><input value="行追加" class="btn_add_n1" id="trAddBtnInEdit" type="button"></td>
       </tr>
       </logic:equal>

     <tr>
       <td colspan="2"><img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px"></td>
     </tr>

     <tr>
        <td class="table_bg_A5B4E1 text_bb1" colspan="2" align="center" nowrap>
          <table width="100%">
            <tr>
              <td width="50%" align="left">
                <b><span class="<bean:write name="ntp040Form" property="ntp040DspDateKbn" />"><bean:write name="ntp040Form" property="ntp040FrYear" />年<bean:write name="ntp040Form" property="ntp040FrMonth" />月<bean:write name="ntp040Form" property="ntp040FrDay" />日<span style="font-size:12px;">(<bean:write name="ntp040Form" property="ntp040DspDateKbnStr" />)</span></span></b>
              </td>
              <td width="50%" align="right">
                <input type="button" value="前日" id="<bean:write name="ntp040Form" property="ntp040PrevNtpSid" />" class="btn_previous_n1 ntpPrevBtn">&nbsp;
                <input type="button" value="今日" id="<bean:write name="ntp040Form" property="ntp040TodayNtpSid" />" class="btn_base1 ntpTodayBtn">&nbsp;
                <input type="button" value="翌日" id="<bean:write name="ntp040Form" property="ntp040NextNtpSid" />" class="btn_next_n1 ntpNextBtn">
              </td>
            </tr>
            </table>
        </td>
     </tr>

     <tr>
       <td colspan="2"><img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px"></td>
     </tr>
     </logic:equal>

     <img src="../schedule/images/spacer.gif" width="1px" border="0" height="10px">
     <tr>
     <td align="left"></td>
     <td align="right">
       <logic:equal name="ntp040Form" property="cmd" value="add">
         <input type="button" id="" value="<gsmsg:write key="cmn.entry.2" />" class="btn_add_n1 ntpAddBtn">
         <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('040_back', '<bean:write name="ntp040Form" property="ntp040BtnCmd" />');">
       </logic:equal>
       <logic:equal name="ntp040Form" property="cmd" value="edit">
         <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="backButtonPush('040_back', '<bean:write name="ntp040Form" property="ntp040BtnCmd" />');">
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

<div id="footdiv"></div>
<jsp:include page="ntp040_popupdivs.jsp" />

<div id="schDataPop" title="スケジュールデータ" style="display:none;">
  <p>
    <div>
      <b><span class="<bean:write name="ntp040Form" property="ntp040DspDateKbn" />"><bean:write name="ntp040Form" property="ntp040FrYear" />年<bean:write name="ntp040Form" property="ntp040FrMonth" />月<bean:write name="ntp040Form" property="ntp040FrDay" />日<span style="font-size:12px;">(<bean:write name="ntp040Form" property="ntp040DspDateKbnStr" />)</span></span></b>
    </div>
    <div style="border:solid 1px;border-color:#9a9a9a;height:300px;overflow-y: scroll">
    <table class="table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">
        <th width="7%" align="center" nowrap="nowrap"></th>
        <th width="10%" nowrap="nowrap"><span class="text_tlw">時間</span></th>
        <th width="53%" nowrap="nowrap"><span class="text_tlw">タイトル</span></th>
        <th width="20%" nowrap="nowrap"><span class="text_tlw"></span></th>
      </tr>
      </thead>
      <tbody id="schDataTrArea"></tbody>
    </table>
    </div>
  </p>
</div>
<div id="mikomidoPop" title="見込み度基準" style="display:none;">
  <p>
    <div style="border:solid 1px;border-color:#9a9a9a;height:300px;overflow-y: scroll">
      <table class="table_td_border2" width="100%" cellpadding="0" cellspacing="0">
        <logic:notEmpty name="ntp040Form" property="ntp040MikomidoMsgList">
          <logic:iterate id="mmdMdl" name="ntp040Form" property="ntp040MikomidoMsgList">
            <tr>
              <td class="table_bg_A5B4E1" width="20%" align="center">
                <span style="font-size:16px;font-weight:bold;color:#333333;"><bean:write name="mmdMdl" property="nmmName" /></span>
              </td>
              <td width="80%">
                <span style="font-size:14px;font-weight:bold;color:#333333;"><bean:write name="mmdMdl" property="nmmMsg" filter="false" /></span>
              </td>
            </tr>
          </logic:iterate>
        </logic:notEmpty>
      </table>
    </div>
  </p>
</div>
<div id="prjDataPop" title="プロジェクトデータ" style="display:none;">
  <p>
    <div>
      <b><span class="<bean:write name="ntp040Form" property="ntp040DspDateKbn" />"><bean:write name="ntp040Form" property="ntp040FrYear" />年<bean:write name="ntp040Form" property="ntp040FrMonth" />月<bean:write name="ntp040Form" property="ntp040FrDay" />日<span style="font-size:12px;">(<bean:write name="ntp040Form" property="ntp040DspDateKbnStr" />)</span></span></b>
    </div>
    <div style="border:solid 1px;border-color:#9a9a9a;height:300px;overflow-y: scroll">
    <table class="table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">
        <th width="7%" align="center" nowrap="nowrap"></th>
        <th width="10%" nowrap="nowrap"><span class="text_tlw">開始</span></th>
        <th width="53%" nowrap="nowrap"><span class="text_tlw">タイトル</span></th>
        <th width="20%" nowrap="nowrap"><span class="text_tlw"></span></th>
      </tr>
      </thead>
      <tbody id="prjDataTrArea"></tbody>
    </table>
    </div>
  </p>
</div>

<logic:equal name="ntp040Form" property="cmd" value="edit">
<div style="height:270px;">&nbsp;</div>
</logic:equal>

</html:form>
<iframe type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></iframe>

<% if (selectionFlg) { %>
<span id="tooltip_search" class="tooltip_search"></span>
<span id="damy"></span>
<% } %>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>