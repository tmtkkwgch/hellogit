<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int mode0 = jp.groupsession.v2.rng.RngConst.RNG_MODE_JYUSIN; %>
<% int mode1 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SINSEI; %>
<% int mode2 = jp.groupsession.v2.rng.RngConst.RNG_MODE_KANRYO; %>
<% int mode3 = jp.groupsession.v2.rng.RngConst.RNG_MODE_SOUKOU; %>

<% int sort_title = jp.groupsession.v2.rng.RngConst.RNG_SORT_TITLE; %>
<% int sort_name = jp.groupsession.v2.rng.RngConst.RNG_SORT_NAME; %>
<% int sort_appl = jp.groupsession.v2.rng.RngConst.RNG_SORT_DATE; %>
<% int sort_jyusin = jp.groupsession.v2.rng.RngConst.RNG_SORT_JYUSIN; %>
<% int sort_kakunin = jp.groupsession.v2.rng.RngConst.RNG_SORT_KAKUNIN; %>
<% int sort_touroku = jp.groupsession.v2.rng.RngConst.RNG_SORT_TOUROKU; %>
<% int order_asc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_ASC; %>
<% int order_desc = jp.groupsession.v2.rng.RngConst.RNG_ORDER_DESC; %>
<% String rngstatus_settlet = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED); %>
<% String rngstatus_reject = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT); %>

<bean:define id="rngDelAuth"  name="rng010Form" property="rng010delAuth" type="java.lang.Integer" />
<% boolean rngDelOk = rngDelAuth.intValue() == jp.groupsession.v2.rng.RngConst.RAR_DEL_AUTH_UNRESTRICTED; %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.13" /></title>
</head>
<body class="body_03">

<html:form action="ringi/rng010">
<input type="hidden" name="CMD" value="moveSearch">
<input type="hidden" name="rngCmdMode" value="0">
<input type="hidden" name="rngApprMode" value="0">

<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010orderKey" />

<input type="hidden" name="helpPrm" value="<bean:write name="rng010Form" property="rngProcMode" />">

<bean:define id="procMode" name="rng010Form" property="rngProcMode" />
<% int sMode = ((Integer) procMode).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
<% if (sMode == mode0) { %><!-- 受信モード -->
        <td width="10%" class="header_white_bg_text">[ <gsmsg:write key="cmn.receive" /> ]</td>
<% } else if (sMode == mode1){ %><!-- 申請中モード -->
        <td width="10%" class="header_white_bg_text">[ <gsmsg:write key="rng.application.ongoing" /> ]</td>
<% } else if (sMode == mode2){ %><!-- 完了モード -->
        <td width="10%" class="header_white_bg_text">[ <gsmsg:write key="cmn.complete" /> ]</td>
<% } else if (sMode == mode3){ %><!-- 草稿モード -->
        <td width="10%" class="header_white_bg_text">[ <gsmsg:write key="cmn.draft" /> ]</td>
<% } else { %>
        <td width="10%" class="header_white_bg_text">[ <gsmsg:write key="cmn.receive" /> ]</td>
<% } %>
        <td width="90%" class="header_white_bg">
        <logic:equal name="rng010Form" property="rng010adminFlg" value="1">
        <input type="button" name="btn_ktool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('rng040');">
        </logic:equal>
        <input type="button" name="btn_ktool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('rng080');">
        </td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
        <% if ((sMode == mode2 && rngDelOk) || sMode == mode3) { %>
        <input type="button" name="btn_delete" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('delete')">
        <% } %>
        <input type="button" name="btn_shinsei" class="btn_add_n1" value="<gsmsg:write key="rng.rng010.02" />" onClick="buttonPush('rngEdit')">
        </td>
      </tr>
    </table>

  <logic:messagesPresent message="false">
  <table width="100%" class="tl0" border="0" cellpadding="0">
  <tr><td align="left"><html:errors/></td></tr>
  </table>
  </logic:messagesPresent>

  <img src="../common/images/spacer.gif" width="1" height="10" border="0">

  <table width="100%" class="tl0" cellpadding="0" cellspacing="0" border="0">
  <tr>
  <td　 width="100%" nowrap>

<% if (sMode == mode0) { %><!-- 受信モード -->

        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);"><gsmsg:write key="cmn.receive3" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.application.ongoing" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.rng010.06" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);"><gsmsg:write key="cmn.draft2" /></a>
            </div>
        </td>

           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
           <div align="right">
              <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
              <html:text name="rng010Form" property="rngKeyword" style="width:183px;" maxlength="100" />
              <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
              &nbsp;
              <logic:notEmpty name="rng010Form" property="pageList">
                <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" width="20" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" border="0"></a>
               <html:select property="rng010pageTop" onchange="selectPage(0);" styleClass="text_i">
                  <html:optionsCollection name="rng010Form" property="pageList" value="value" label="label" />
            </html:select>
            <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" width="20" height="20" src="../common/images/arrow2_r.gif" class="img_bottom" border="0"></a></td>
              </logic:notEmpty>
        </div>
         </td>
        <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
        </tr>
        </table>

<% } else if (sMode == mode1){ %><!-- 申請中モード -->
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);"><gsmsg:write key="cmn.receive3" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.application.ongoing" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.rng010.06" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);"><gsmsg:write key="cmn.draft2" /></a>
            </div>
        </td>

           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
           <div align="right">
              <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
              <html:text name="rng010Form" property="rngKeyword" style="width:183px;" maxlength="100" />
              <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
              &nbsp;
              <logic:notEmpty name="rng010Form" property="pageList">
                <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
               <html:select property="rng010pageTop" onchange="selectPage(0);" styleClass="text_i">
                  <html:optionsCollection name="rng010Form" property="pageList" value="value" label="label" />
            </html:select>
            <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
              </logic:notEmpty>
        </div>
         </td>
        <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
        </tr>
        </table>
<% } else if (sMode == mode2){ %><!-- 完了モード -->
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);"><gsmsg:write key="cmn.receive3" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.application.ongoing" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.rng010.06" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);"><gsmsg:write key="cmn.draft2" /></a>
            </div>
        </td>

           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
           <div align="right">
              <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
              <html:text name="rng010Form" property="rngKeyword" style="width:183px;" maxlength="100" />
              <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
              &nbsp;
              <logic:notEmpty name="rng010Form" property="pageList">
                <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
               <html:select property="rng010pageTop" onchange="selectPage(0);" styleClass="text_i">
                  <html:optionsCollection name="rng010Form" property="pageList" value="value" label="label" />
            </html:select>
            <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
              </logic:notEmpty>
        </div>
         </td>
        <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
        </tr>
        </table>
<% } else if (sMode == mode3){ %><!-- 草稿モード -->
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);"><gsmsg:write key="cmn.receive3" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.application.ongoing" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);"><gsmsg:write key="rng.rng010.06" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);"><gsmsg:write key="cmn.draft2" /></a>
            </div>
        </td>

           <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
           <div align="right">
              <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
              <html:text name="rng010Form" property="rngKeyword" style="width:183px;" maxlength="100" />
              <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base0">
              &nbsp;
              <logic:notEmpty name="rng010Form" property="pageList">
                <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
               <html:select property="rng010pageTop" onchange="selectPage(0);" styleClass="text_i">
                  <html:optionsCollection name="rng010Form" property="pageList" value="value" label="label" />
            </html:select>
            <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
              </logic:notEmpty>
        </div>
         </td>
        <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
        </tr>
        </table>
<% } else { %>
    <td><gsmsg:write key="rng.rng010.04" /></td>
<% } %>

  </td>
  </tr>
  </table>

<bean:define id="sortKey" name="rng010Form" property="rng010sortKey" />
<bean:define id="orderKey" name="rng010Form" property="rng010orderKey" />
<% int iSortKey = ((Integer) sortKey).intValue();                                      %>
<% int iOrderKey = ((Integer) orderKey).intValue();                                    %>
<% int[] sortKeyList = null;                                                           %>
<% String[] title_width = null;                                                        %>
<% String[] titleList = null;                                                          %>
<% int[] colspanList = null;                                                           %>
<% String[] textTypeList = null;                                                       %>
<% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();  %>
<% String msgTitle = gsMsg.getMessage(request, "cmn.title");                              %>
<% String user = gsMsg.getMessage(request, "rng.47");                                  %>
<% String date = gsMsg.getMessage(request, "rng.application.date");                    %>
<% String recDate = gsMsg.getMessage(request, "cmn.received.date");                    %>
<% String lastDate = gsMsg.getMessage(request, "rng.105");                             %>
<% String creDate = gsMsg.getMessage(request, "rng.37");                               %>
<% if (sMode == mode0) {                                                               %>
<%     sortKeyList = new int[] { sort_title, sort_name, sort_appl, sort_jyusin };      %>
<%     title_width = new String[] { "59%", "15%", "13%", "13%"};                       %>
<%     titleList = new String[] {msgTitle, user, date, recDate};                          %>
<%     colspanList = new int[] {1, 1, 1, 1};                                           %>
<% } else if (sMode == mode1) {                                                        %>
<%     sortKeyList = new int[] { sort_title, sort_name, sort_appl, sort_kakunin };     %>
<%     title_width = new String[] { "59%", "15%", "13%", "13%"};                       %>
<%     titleList = new String[] {msgTitle, user, date, lastDate};                         %>
<%     colspanList = new int[] {1, 1, 1, 1};                                           %>
<% } else if (sMode == mode2) {                                                        %>
<%     sortKeyList = new int[] { sort_title, sort_name, sort_appl, sort_kakunin };     %>
<%     title_width = new String[] { "54%", "15%", "13%", "13%"};                       %>
<%     titleList = new String[] {msgTitle, user, date, lastDate};                         %>
<%     colspanList = new int[] {2, 1, 1, 1};                                           %>
<% } else if (sMode == mode3) {                                                        %>
<%     sortKeyList = new int[] { sort_title, sort_touroku };                           %>
<%     title_width = new String[] { "82%", "13%"};                                     %>
<%     titleList = new String[] {msgTitle, creDate};                                      %>
<%     colspanList = new int[] {1, 1};                                                 %>
<% }                                                                                   %>

  <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
    <tr class="smail_th">
    <% if ((sMode == mode2 && rngDelOk) || sMode == mode3) { %>
    <td width="5%" class="td_rng_header">
    <logic:empty name="rng010Form" property="rngDataList">&nbsp;</logic:empty>
    <logic:notEmpty name="rng010Form" property="rngDataList">
    <input type="checkbox" name="allChk" onClick="changeChk();">
    </logic:notEmpty>
    </td>
    <% } %>
    <% String down = gsMsg.getMessage(request, "tcd.tcd040.23");    %>
    <% String up = gsMsg.getMessage(request, "tcd.tcd040.22");      %>
    <% for (int i = 0; i < sortKeyList.length; i++) {   %>
    <%   String title = titleList[i];                   %>
    <%   String skey = String.valueOf(sortKeyList[i]);  %>
    <%   String order = String.valueOf(order_asc);      %>
    <%   String colspan = (colspanList[i] > 1)?" colspan=\"" + colspanList[i] + "\"":""; %>
    <%   if (iSortKey == sortKeyList[i]) {              %>
    <%     if (iOrderKey == order_desc) {               %>
    <%       title = title + down;                      %>
    <%     } else {                                     %>
    <%       title = title + up;                      %>
    <%       order = String.valueOf(order_desc);        %>
    <%     }                                            %>
    <%   }                                              %>
      <td width="<%= title_width[i] %>"<%= colspan %> class="td_rng_header"><a href="javascript:void(0);" onClick="return clickTitle(<%= skey %>, <%= order %>);"><span class="text_base3"><%= title %></span></a></th>
    <% } %>
    </tr>

    <logic:notEmpty name="rng010Form" property="rngDataList">
    <% String[] trclass = {"smail_td1", "smail_td2"}; %>

    <logic:iterate id="rngData" name="rng010Form" property="rngDataList" indexId="idx" scope="request">
    <tr class="<%= trclass[idx.intValue() % 2] %>">

<% String apprUserClass = "";%>
<logic:equal name="rngData" property="apprUserDelFlg" value="true">
<% apprUserClass = "text_appruser_del"; %>
</logic:equal>

<% if (sMode == mode0) { %>
      <% String apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPR); %>
      <logic:equal name="rngData" property="rncType" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_RNCTYPE_APPL) %>">
        <% apprMode = String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_APPRMODE_APPL); %>
      </logic:equal>
      <td align="left"><a href="#" onclick="clickJyusinRingi('rng030', 0, <%= apprMode %>, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td align="left" class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
      <td align="center"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td align="center"><bean:write name="rngData" property="strRcvDate" /></td>
<% } else if (sMode == mode1) { %>
      <td align="left"><a href="#" onclick="clickRingi('rng030', 0, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td align="left" class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
      <td align="center"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td align="center"><bean:write name="rngData" property="strLastManageDate" /></td>
<% } else if (sMode == mode2) { %>

      <% if (rngDelOk) { %>
      <td align="center" width="1%">
      <logic:notEqual name="rngData" property="delFlg" value="1">&nbsp;</logic:notEqual>
      <logic:equal name="rngData" property="delFlg" value="1">
      <html:multibox name="rng010Form" property="rng010DelSidList">
         <bean:write name="rngData" property="rngSid" />
      </html:multibox>
      </logic:equal>
      </td>
      <% } %>

      <td align="left" width="50%"><a href="#" onclick="clickRingi('rng030', 0, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <% String rngStatus = "&nbsp;"; %>
      <% String kessai = gsMsg.getMessage(request, "rng.64"); %>
      <% String kyakka = gsMsg.getMessage(request, "rng.65"); %>
      <logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_settlet %>">
        <% rngStatus = kessai; %>
      </logic:equal>
      <logic:equal name="rngData" property="rngStatus" value="<%= rngstatus_reject %>">
        <% rngStatus = kyakka; %>
      </logic:equal>
      <td align="center" nowrap width="8%"><%= rngStatus %></td>
      <td align="left" class="<%= apprUserClass %>"><bean:write name="rngData" property="apprUser" /></td>
      <td align="center"><bean:write name="rngData" property="strRngAppldate" /></td>
      <td align="center"><bean:write name="rngData" property="strLastManageDate" /></td>
<% } else if (sMode == mode3) { %>
      <td align="center" width="1%">
      <html:multibox name="rng010Form" property="rng010DelSidList">
         <bean:write name="rngData" property="rngSid" />
      </html:multibox>
      </td>
      <td align="left" width="86%"><a href="#" onclick="clickRingi('rngEdit', 1, <bean:write name="rngData" property="rngSid" />);"><span class="text_link"><bean:write name="rngData" property="rngTitle" /></span></a></td>
      <td align="center"><bean:write name="rngData" property="strMakeDate" /></td>
<% } %>

    </tr>
    </logic:iterate>
    </logic:notEmpty>

  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td width="0%" class="tab_bottom_left"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
      <td class="tab_bottom_mid" width="100%" align="right" valign="bottom">
        <logic:notEmpty name="rng010Form" property="pageList">
        <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select property="rng010pageBottom" onchange="selectPage(1);" styleClass="text_i">
          <html:optionsCollection name="rng010Form" property="pageList" value="value" label="label" />
        </html:select>
        <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
        </logic:notEmpty>
      <td width="0%" class="tab_bottom_right"><img src="../common/images/spacer.gif" width="10" height="34" border="0" alt=""></td>
    </tr>
  </table>

</td>
</tr>
</table>

<tr>
<td class="td_type_tab" colspan="3">
  <table width="100%" class="tl5">
  <tr>
  <td width="100%">&nbsp;</td>
  <td align="right">
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

<map name="imagemap0">
  <area shape="rect" coords="4,3,97,26"    href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);" alt="<gsmsg:write key="cmn.receive" />">
  <area shape="rect" coords="104,3,197,26" href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="rng.application.ongoing" />">
  <area shape="rect" coords="204,3,297,26" href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.complete" />">
  <area shape="rect" coords="304,3,397,26" href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.draft" />">
</map>
<map name="imagemap1">
  <area shape="rect" coords="4,3,97,26"    href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);" alt="<gsmsg:write key="cmn.receive" />">
  <area shape="rect" coords="104,3,197,26" href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="rng.application.ongoing" />">
  <area shape="rect" coords="204,3,297,26" href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.complete" />">
  <area shape="rect" coords="304,3,397,26" href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.draft" />">
</map>
<map name="imagemap2">
  <area shape="rect" coords="4,3,97,26"    href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);" alt="<gsmsg:write key="cmn.receive" />">
  <area shape="rect" coords="104,3,197,26" href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="rng.application.ongoing" />">
  <area shape="rect" coords="204,3,297,26" href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.complete" />">
  <area shape="rect" coords="304,3,397,26" href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.draft" />">
</map>
<map name="imagemap3">
  <area shape="rect" coords="4,3,97,26"    href="#" onClick="changeMode(<%= mode0 %>, <%= sort_jyusin %>, <%= order_asc %>);" alt="<gsmsg:write key="cmn.receive" />">
  <area shape="rect" coords="104,3,197,26" href="#" onClick="changeMode(<%= mode1 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="rng.application.ongoing" />">
  <area shape="rect" coords="204,3,297,26" href="#" onClick="changeMode(<%= mode2 %>, <%= sort_kakunin %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.complete" />">
  <area shape="rect" coords="304,3,397,26" href="#" onClick="changeMode(<%= mode3 %>, <%= sort_touroku %>, <%= order_desc %>);" alt="<gsmsg:write key="cmn.draft" />">
</map>

</html:form>
</body>
</html:html>
