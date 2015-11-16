<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /><gsmsg:write key="cmn.list" />(<gsmsg:write key="cmn.weeks" />)</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../nippou/js/ntp010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="ntp010Form" property="ntp010Reload" value="0">
    var reloadinterval = <bean:write name="ntp010Form" property="ntp010Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<% long ntpTipCnt = 0; %>
<% String chkClass = "ntp_chk"; %>
<body class="body_03" onload="setToUser();" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/nippou/ntp010">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="1">

<input type="hidden" name="ntp010SelectUsrSid" value="-1">
<input type="hidden" name="ntp010SelectUsrKbn" value="0">

<html:hidden property="cmd" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />

<html:hidden property="ntp010HistoryAnkenSid" />
<html:hidden property="ntp010HistoryCompSid" />
<html:hidden property="ntp010HistoryCompBaseSid" />
<html:hidden property="ntp010SessionUsrId" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="cmn.weeks" /> ]</td>
  <td width="100%" class="header_white_bg">
  <%-- input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');" --%>
  <%-- input type="button" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush('anken');"--%>
  <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
  <logic:equal name="ntp010Form" property="adminKbn" value="1">
    <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('ktool');">
  </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pset');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  </tr>
  </table>


<img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0">

<table cellpadding="1px" width="100%">
<tr>
<td>

    <table cellpadding="0" cellspacing="0" class="menu_table">

      <tr>

        <td valign="top" width="16%" class="menu_space_area_both">
          <table width="100%" height="100%" cellpadding="0" cellspacing="0">

            <tr class="menu_select_tr">
              <td class="menu_select_icon_no_top">
                <img src="../nippou/images/menu_icon_single.gif" alt="<gsmsg:write key="ntp.1" />" />
              </td>
              <td class="menu_select_text_no_top" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.1" /></span>
              </td>
              <td class="menu_select_text_no_top" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush('anken');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_anken.gif" alt="<gsmsg:write key="ntp.11" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.11" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush('target');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_target.gif" alt="<gsmsg:write key="ntp.12" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.12" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush('bunseki');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_bunseki.gif" alt="<gsmsg:write key="ntp.13" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.13" /></span>
              </td>
            </tr>

            <logic:equal name="ntp010Form" property="adminKbn" value="1">
              <tr class="menu_not_select_tr" onClick="buttonPush('masta');">
                <td class="menu_not_select_icon">
                  <img src="../nippou/images/menu_icon_mente.gif" alt="<gsmsg:write key="ntp.14" />" />
                </td>
                <td class="menu_not_select_text" colspan="2" style="padding-top:7px;padding-right:5px;" valign="top" nowrap>
                  <span class="timeline_menu2"><gsmsg:write key="ntp.14" /></span>
                </td>
              </tr>
            </logic:equal>

            <tr class="menu_history_tr">
              <td style="padding:5px;" width="100%" align="center" colspan="3" class="menu_history_area">
                <br>
                <logic:notEmpty name="ntp010Form" property="ankenHistoryList">
                  <table width="100%">
                    <tr>
                      <td align="left" width="100%" class="table_bg_7D91BD text_tl5">
                        <span class="text_tlw"><img style="vertical-align:middle;padding-bottom:3px;" src="../nippou/images/history_adr_icon_mini.gif" /><gsmsg:write key="ntp.11" /><gsmsg:write key="ntp.17" /></span><br>
                      </td>
                    </tr>
                    <logic:iterate id="ankenMdl" name="ntp010Form" property="ankenHistoryList">
                      <tr>
                        <td width="100%" align="left" id="<bean:write name="ankenMdl" property="nanSid" />" class="ankenHistoryArea ankenAreaSel">
                          <span style="font-size:12px;"><bean:write name="ankenMdl" property="nanName" /></span>
                        </td>
                      </tr>
                    </logic:iterate>
                  </table>
                  <br>
                </logic:notEmpty>

                <logic:notEmpty name="ntp010Form" property="companyHistoryList">
                  <table width="100%">
                    <tr>
                      <td align="left" width="100%" class="table_bg_7D91BD text_tl5">
                        <span class="text_tlw"><img style="vertical-align:middle;padding-bottom:3px;" src="../nippou/images/history_cmp_icon_mini.gif" /><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /><gsmsg:write key="ntp.17" /></span><br>
                      </td>
                    </tr>
                    <logic:iterate id="companyMdl" name="ntp010Form" property="companyHistoryList">
                      <tr>
                        <td width="100%" align="left" id="<bean:write name="companyMdl" property="companySid" />" class="companyHistoryArea companyAreaSel">
                          <span id="<bean:write name="companyMdl" property="companyBaseSid" />" style="font-size:11px;"><bean:write name="companyMdl" property="companyName" /><wbr>
                            <logic:notEmpty name="companyMdl" property="companyBaseName">
                              <bean:write name="companyMdl" property="companyBaseName" />
                            </logic:notEmpty>
                          </span>

                        </td>
                      </tr>
                    </logic:iterate>
                  </table>
                  <br>
                </logic:notEmpty>

              </td>
            </tr>

            <tr>
              <td class="menu_space_area_left"></td>
              <td class="menu_space_area_right" colspan="2">&nbsp;</td>
            </tr>


          </table>
        </td>

        <td valign="top" width="84%">


               <%-- div id="nippouArea" class="areablock" style="vertical-align:top;padding-left:10px;padding-right:10px;"--%>
                <table width="100%">
                <tr>
                <td width="100%" style="padding-left:15px;padding-right:10px;">

                      <table cellpadding="5" width="100%" class="tl0">
                      <tr>
                      <td colspan="8" class="td_type0">

                        <table cellspacing="0" width="100%" border="0">

                        <tr>
                        <td width="20%" align="left">

                        </td>
                        <td width="40%" align="center" nowrap>

                        </td>

                        <td width="40%" style="padding-top:5px;" align="center" nowrap>
                        <bean:size id="topSize" name="ntp010Form" property="ntp010TopList" scope="request" />
                        <logic:equal name="topSize" value="2">
                        <logic:iterate id="weekBean" name="ntp010Form" property="ntp010TopList" scope="request" offset="1"/>
                        </logic:equal>
                        <logic:notEqual name="topSize" value="2">
                        <logic:iterate id="weekBean" name="ntp010Form" property="ntp010TopList" scope="request" offset="0"/>
                        </logic:notEqual>

                        <bean:define id="usrBean" name="weekBean" property="ntp010UsrMdl"/>
                          <input type="button" value="<gsmsg:write key="ntp.18" />" class="btn_time_line" onClick="moveDailyNippou('day', <bean:write name="ntp010Form" property="ntp010DspDate" />, <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
                          <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('reload');">
                          <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="moveMonthNippou('month', <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">
                          <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_schedule_search" onClick="moveListNippou('list', <bean:write name="usrBean" property="usrSid" />, <bean:write name="usrBean" property="usrKbn" />);">&nbsp;&nbsp;
                        </td>

                        <td width="0%" align="right" nowrap style="padding-top:3px;">
                          <input type="button" class="btn_arrow1_l" value="&nbsp;" onclick="buttonPush('move_lw');">
                          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_ld');">
                          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="buttonPush('today');">
                          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rd');">
                          <input type="button" class="btn_arrow1_r" value="&nbsp;" onclick="buttonPush('move_rw');">
                        </td>

                        <td width="0%" align="right" style="padding-top:4px;" nowrap>
                          <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.ntp010DspDate, 'ntp010CalBtn')" class="calendar_btn2" id="ntp010CalBtn">
                        </td>

                        </tr>
                        </table>
                        <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">
                      </td>

                      </tr>

                      <logic:messagesPresent message="false">
                      <tr>
                      <td colspan="8"><html:errors /></td>
                      </tr>
                      </logic:messagesPresent>

                      <tr>
                      <td colspan="8" class="table_bg_7D91BD" align="center">

                        <table width="100%" class="tl0">
                          <tr>
                          <td width="20%" align="left" class="text_tl3">
                            <span><font color="ffffff"><bean:write name="ntp010Form" property="ntp010StrDspDate" /></font></span>
                          </td>

                          <td width="40%" align="left" nowrap>
                            <span class="text_tlw"><gsmsg:write key="cmn.show.group" /></span>
                            <html:select property="ntp010DspGpSid" styleClass="select01" onchange="changeGroupCombo();">
                              <logic:notEmpty name="ntp010Form" property="ntp010GpLabelList" scope="request">
                              <logic:iterate id="gpBean" name="ntp010Form" property="ntp010GpLabelList" scope="request">

                              <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                              <logic:equal name="gpBean" property="styleClass" value="0">
                              <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                              </logic:equal>

                              <logic:notEqual name="gpBean" property="styleClass" value="0">
                              <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                              </logic:notEqual>

                              </logic:iterate>
                              </logic:notEmpty>
                            </html:select>

                          <input type="button" onclick="openGroupWindow(this.form.ntp010DspGpSid, 'ntp010DspGpSid', '1')" class="group_btn" value="&nbsp;&nbsp;" id="ntp010GroupBtn">
                          </td>

                          <td width="0%" align="right">
                          <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
                          <html:text property="ntp010searchWord" styleClass="text_base" maxlength="50" style="width:121px; margin-right:2px;" />
                          <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onClick="buttonPush('search');">
                          </td>
                          </tr>
                        </table>
                      </td>
                      </tr>


                      <!-- タイトル(氏名,日付) -->
                      <tr>
                      <th width="16%" class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
                      <logic:notEmpty name="ntp010Form" property="ntp010CalendarList" scope="request">
                      <logic:iterate id="calBean" name="ntp010Form" property="ntp010CalendarList" scope="request">

                        <bean:define id="tdColor" value="" />
                        <bean:define id="fontColorSun" value="" />
                        <bean:define id="fontColorSat" value="" />
                        <bean:define id="fontColorDef" value="" />
                        <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
                        <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
                        <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
                        <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

                        <logic:equal name="calBean" property="todayKbn" value="1">
                        <% tdColor = tdColors[1]; %>
                        <% fontColorSun = fontColorsSun[1]; %>
                        <% fontColorSat = fontColorsSat[1]; %>
                        <% fontColorDef = fontColorsDef[1]; %>
                        </logic:equal>
                        <logic:notEqual name="calBean" property="todayKbn" value="1">
                        <% tdColor = tdColors[0]; %>
                        <% fontColorSun = fontColorsSun[0]; %>
                        <% fontColorSat = fontColorsSat[0]; %>
                        <% fontColorDef = fontColorsDef[0]; %>
                        </logic:notEqual>

                      <logic:equal name="calBean" property="holidayKbn" value="1">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:notEqual name="calBean" property="holidayKbn" value="1">
                      <logic:equal name="calBean" property="weekKbn" value="1">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:equal name="calBean" property="weekKbn" value="7">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSat %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:notEqual name="calBean" property="weekKbn" value="1">
                      <logic:notEqual name="calBean" property="weekKbn" value="7">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorDef %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:notEqual>
                      </logic:notEqual>
                      </logic:notEqual>

                      </logic:iterate>
                      </logic:notEmpty>
                      </tr>



                      <!-- 本人 -->
                      <logic:notEmpty name="ntp010Form" property="ntp010TopList" scope="request">
                      <logic:iterate id="weekMdl" name="ntp010Form" property="ntp010TopList" scope="request">
                      <tr align="left" valign="top">
                      <bean:define id="usrMdl" name="weekMdl" property="ntp010UsrMdl"/>

                      <!-- 本人 -->
                      <logic:notEqual name="usrMdl" property="usrKbn" value="1">
                      <logic:equal name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                        <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
                        <td class="td_type1 break">
                        </logic:equal>
                        <logic:equal name="usrMdl" property="zaisekiKbn" value="2">
                        <td class="td_type_gaisyutu break">
                        </logic:equal>
                        <logic:equal name="usrMdl" property="zaisekiKbn" value="0">
                        <td class="td_type_kekkin break">
                        </logic:equal>
                      </logic:equal>
                      <logic:notEqual name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                        <td class="td_type1">
                      </logic:notEqual>

                        <span>
                        <a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="usrMdl" property="usrSid" />);"><bean:write name="usrMdl" property="usrName" /></a>
                        </span>
                        <span class="sc_link_5"><bean:write name="usrMdl" property="zaisekiMsg" /></span><br>
                        <span>
                          <a href="#" onClick="moveMonthNippou('month', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_gekkan_bg.gif" width="35" height="18" alt="" border="0">
                          <span class="sc_link_6"><gsmsg:write key="cmn.monthly" /></span>
                          </a>
                          <br>
                          <a href="#" onClick="moveListNippou('list', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_ichiran_bg.gif" width="35" height="18" alt="" border="0">
                          <span class="sc_link_6"><gsmsg:write key="cmn.list" /></span>
                          </a>
                          <br>
                          <logic:equal name="ntp010Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <logic:equal name="usrMdl" property="smlAble" value="1">
<%--                           <a href="#" onClick="moveCreateMsg('msg', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_smail_bg.gif" width="35" height="18" alt="" border="0"> --%>
                          <a href="#" class="sml_send_link" id="<bean:write name="usrMdl" property="usrSid" />"><img src="../common/images/btn_smail_bg.gif" width="35" height="18" alt="" border="0">
                          <span class="sc_link_6"><gsmsg:write key="cmn.shortmail" /></span>
                          </a>
                          <br>
                          </logic:equal>
                          </logic:equal>
                          <!-- 在席 -->
                          <logic:equal name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
                          <input type="button" onClick="setFuzai(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#417984;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_fuzai" value="<gsmsg:write key="cmn.absence" />">
                          <input type="button" onClick="setSonota(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_sonota" value="<gsmsg:write key="cmn.other" />">
                          </logic:equal>
                          <logic:notEqual name="usrMdl" property="zaisekiKbn" value="1">
                          <!-- 不在、その他 -->
                          <input type="button" onClick="setZaiseki(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_zaiseki" value="<gsmsg:write key="cmn.zaiseki" />">
                          <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
                          </logic:notEqual>
                          </logic:equal>
                          <logic:notEqual name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
                          <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
                          </logic:notEqual>

                        </span>
                        </td>
                      </logic:notEqual>


                      <!-- 日報情報 -->
                      <logic:notEmpty name="weekMdl" property="ntp010NtpList">
                      <logic:iterate id="dayMdl" name="weekMdl" property="ntp010NtpList">

                      <logic:equal name="dayMdl" property="weekKbn" value="1">
                        <td align="left" valign="top" class="td_type9">
                      </logic:equal>
                      <logic:equal name="dayMdl" property="weekKbn" value="7">
                        <td align="left" valign="top" class="td_type8">
                      </logic:equal>

                      <logic:notEqual name="dayMdl" property="weekKbn" value="1">
                      <logic:notEqual name="dayMdl" property="weekKbn" value="7">
                        <td align="left" valign="top" class="td_type1">
                      </logic:notEqual>
                      </logic:notEqual>
                        <span id="lt"><a href="#" onClick="return addNippou('add', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);"><img src="../nippou/images/add_nippou.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0" ></a></span>

                        <logic:notEmpty name="dayMdl" property="holidayName">
                        <span id="rt"><font size="-2" class="time_line_height" color="#ff0000"><bean:write name="dayMdl" property="holidayName" /></font></span>
                        </logic:notEmpty>

                        <br>

                        <logic:notEmpty name="dayMdl" property="ntpDataList">
                        <logic:iterate id="ntpMdl" name="dayMdl" property="ntpDataList">



                          <bean:define id="chKbn" name="ntpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

                          <% chkClass = "ntp_chk"; %>
                          <% if (chKbn == 1) { %>
                          <%    chkClass = ""; %>
                          <% } %>

                          <div onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="dayMdl" property="usrSid" />, 0);" class="<%= chkClass %>" style="padding-top:5px;">

                          <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
                          <bean:define id="u_ntpsid" name="ntpMdl" property="ntpSid" type="java.lang.Integer" />
                          <bean:define id="u_date" name="dayMdl" property="ntpDate"  type="java.lang.String" />

                          <%
                            String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
                            String tipskey2 = ((Integer)pageContext.getAttribute("u_ntpsid",PageContext.PAGE_SCOPE)).toString();
                            String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
                            String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
                          %>


                          <!-- コメントアイコン表示  -->
                          <logic:notEqual name="ntpMdl" property="ntp_cmtCnt" value="0">
                              <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="0">
                                <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str2"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="1">
                                <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="2">
                                <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
                              </logic:equal>

                              <logic:equal name="ntpMdl" property="ntp_goodCnt" value="0">
                                <br style="clear:both;line-height:1px;">
                              </logic:equal>
                          </logic:notEqual>


                          <!-- いいねアイコン表示  -->
                          <logic:notEqual name="ntpMdl" property="ntp_goodCnt" value="0">
                              <logic:equal name="ntpMdl" property="ntp_goodkbn" value="0">
                                <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str2"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="ntpMdl" property="ntp_goodkbn" value="1">
                                <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="ntpMdl" property="ntp_goodkbn" value="2">
                                <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
                              </logic:equal>

                              <br style="clear:both;line-height:1px;">

                          </logic:notEqual>

                    <%--
                          <logic:empty name="ntpMdl" property="detail">
                          <a href="#" title="<bean:write name="ntpMdl" property="title" />" onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="dayMdl" property="usrSid" />, 0);" id="ntpsid<%= tipskey %>">
                          </logic:empty>
                          <logic:notEmpty name="ntpMdl" property="detail">
                          <a href="#" onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="dayMdl" property="usrSid" />, 0);" id="ntpsid<%= tipskey %>">
                          </logic:notEmpty>
                    --%>



                          <logic:empty name="ntpMdl" property="detail">
                            <a href="#" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"><span class="tooltips"><bean:write name="ntpMdl" property="title" /></span>
                          </logic:empty>

                          <logic:notEmpty name="ntpMdl" property="detail">
                          <bean:define id="ntdetailu" name="ntpMdl" property="detail" />

                          <%
                            String tmpText = (String)pageContext.getAttribute("ntdetailu",PageContext.PAGE_SCOPE);
                            String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTmlPlusAmparsant(tmpText);
                          %>

                            <a href="#" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"  id="ntpsid<%= tipskey %>"><span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                          </logic:notEmpty>




                          <!--タイトルカラー設定-->
                          <logic:equal name="ntpMdl" property="titleColor" value="0">
                          <span class="sc_link_1">
                          </logic:equal>
                          <logic:equal name="ntpMdl" property="titleColor" value="1">
                          <span class="sc_link_1">
                          </logic:equal>
                          <logic:equal name="ntpMdl" property="titleColor" value="2">
                          <span class="sc_link_2">
                          </logic:equal>
                          <logic:equal name="ntpMdl" property="titleColor" value="3">
                          <span class="sc_link_3">
                          </logic:equal>
                          <logic:equal name="ntpMdl" property="titleColor" value="4">
                          <span class="sc_link_4">
                          </logic:equal>
                          <logic:equal name="ntpMdl" property="titleColor" value="5">
                          <span class="sc_link_5">
                          </logic:equal>


                          <logic:notEmpty name="ntpMdl" property="time">
                            <font size="-2" class="time_line_height"><bean:write name="ntpMdl" property="time" /><br></font>
                          </logic:notEmpty>
                            <bean:write name="ntpMdl" property="title" />
                          </span>
                          </a>
<%--
                          <!-- 追加：確認者数表示  -->
                          <logic:notEqual name="ntpMdl" property="nkk_allcnt" value="0">
                            <font size="-2">(<bean:write name="ntpMdl" property="nkk_chkcnt" />/<bean:write name="ntpMdl" property="nkk_allcnt" />)</font>
                          </logic:notEqual>
--%>
                    <%--
                          <!-- ツールチップ -->
                          <div id="sctext<%= tipskey %>" style="display:none;padding: 5px;margin: 5px;background-color: #cccccc;border: solid 1px #666666;font-size: 12px;white-space:nowrap;">
                            <bean:define id="ntpnaiyou" name="ntpMdl" property="detail" />
                            <%
                              String tmpText = (String)pageContext.getAttribute("ntpnaiyou",PageContext.PAGE_SCOPE);
                              String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                              out.println("内容:");
                              out.println(tmpText2);
                            %>
                          </div>
                          <logic:notEmpty name="ntpMdl" property="detail">
                          <script type="text/javascript">
                            var ntp_tooltip<%= tipskey %> = new Tooltip('ntpsid<%= tipskey %>', 'sctext<%= tipskey %>');
                          </script>
                          </logic:notEmpty>
                          <!-- ツールチップ ここまで -->
                    --%>
                        </div>
                        </logic:iterate>
                        </logic:notEmpty>

                      </td>

                      </logic:iterate>
                      </logic:notEmpty>
                      </tr>

                      <!-- グループと本人の間のボーダー -->
                      <logic:equal name="usrMdl" property="usrKbn" value="1">
                      <tr><td colspan="8" class="table_sch_bg_type1">
                          <img src="../nippou/images/damy.gif" width="1" height="3" alt="<gsmsg:write key="cmn.space" />">
                      </td></tr>
                      </logic:equal>

                      </logic:iterate>
                      </logic:notEmpty>


                      <!-- グループメンバー -->
                      <tr>
                      <td colspan="8" class="table_bg_7D91BD" align="left">
                         <table width="100%" class="tl0">
                          <tr>
                          <td align="left" class="text_tl4">
                            <span class="text_tlwn">&nbsp;<gsmsg:write key="schedule.74" /></span>
                          </td>
                          <td align="right">

                          </td>
                          </tr>
                        </table>
                      </td>
                      </tr>

                      <!-- タイトル(氏名,日付) -->
                      <tr>
                      <th width="16%" class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
                      <logic:notEmpty name="ntp010Form" property="ntp010CalendarList" scope="request">
                      <logic:iterate id="calBean" name="ntp010Form" property="ntp010CalendarList" scope="request">

                        <bean:define id="tdColor" value="" />
                        <bean:define id="fontColorSun" value="" />
                        <bean:define id="fontColorSat" value="" />
                        <bean:define id="fontColorDef" value="" />
                        <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
                        <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
                        <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
                        <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

                        <logic:equal name="calBean" property="todayKbn" value="1">
                        <% tdColor = tdColors[1]; %>
                        <% fontColorSun = fontColorsSun[1]; %>
                        <% fontColorSat = fontColorsSat[1]; %>
                        <% fontColorDef = fontColorsDef[1]; %>
                        </logic:equal>
                        <logic:notEqual name="calBean" property="todayKbn" value="1">
                        <% tdColor = tdColors[0]; %>
                        <% fontColorSun = fontColorsSun[0]; %>
                        <% fontColorSat = fontColorsSat[0]; %>
                        <% fontColorDef = fontColorsDef[0]; %>
                        </logic:notEqual>

                      <logic:equal name="calBean" property="holidayKbn" value="1">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:notEqual name="calBean" property="holidayKbn" value="1">
                      <logic:equal name="calBean" property="weekKbn" value="1">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:equal name="calBean" property="weekKbn" value="7">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSat %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:notEqual name="calBean" property="weekKbn" value="1">
                      <logic:notEqual name="calBean" property="weekKbn" value="7">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorDef %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:notEqual>
                      </logic:notEqual>
                      </logic:notEqual>

                      </logic:iterate>
                      </logic:notEmpty>
                      </tr>


                      <!-- グループメンバー日報表示 -->
                      <!-- グループメンバー -->
                      <logic:notEmpty name="ntp010Form" property="ntp010BottomList" scope="request">
                      <logic:iterate id="gpWeekMdl" name="ntp010Form" property="ntp010BottomList" scope="request" indexId="cnt">

                      <bean:define id="ret" value="<%= String.valueOf(cnt.intValue() % 5) %>" />
                      <logic:notEqual name="cnt" value="0" scope="page">
                      <logic:equal name="ret" value="0">
                      <!-- 5行毎にタイトル(氏名,日付) -->
                      <tr>
                      <th width="16%" class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.name" /></span></th>
                      <logic:notEmpty name="ntp010Form" property="ntp010CalendarList" scope="request">
                      <logic:iterate id="calBean" name="ntp010Form" property="ntp010CalendarList" scope="request">

                        <bean:define id="tdColor" value="" />
                        <bean:define id="fontColorSun" value="" />
                        <bean:define id="fontColorSat" value="" />
                        <bean:define id="fontColorDef" value="" />
                        <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
                        <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
                        <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
                        <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

                        <logic:equal name="calBean" property="todayKbn" value="1">
                        <% tdColor = tdColors[1]; %>
                        <% fontColorSun = fontColorsSun[1]; %>
                        <% fontColorSat = fontColorsSat[1]; %>
                        <% fontColorDef = fontColorsDef[1]; %>
                        </logic:equal>
                        <logic:notEqual name="calBean" property="todayKbn" value="1">
                        <% tdColor = tdColors[0]; %>
                        <% fontColorSun = fontColorsSun[0]; %>
                        <% fontColorSat = fontColorsSat[0]; %>
                        <% fontColorDef = fontColorsDef[0]; %>
                        </logic:notEqual>

                      <logic:equal name="calBean" property="holidayKbn" value="1">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:notEqual name="calBean" property="holidayKbn" value="1">
                      <logic:equal name="calBean" property="weekKbn" value="1">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSun %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:equal name="calBean" property="weekKbn" value="7">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorSat %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:equal>

                      <logic:notEqual name="calBean" property="weekKbn" value="1">
                      <logic:notEqual name="calBean" property="weekKbn" value="7">
                      <th width="12%" nowrap class="<%= tdColor %>">
                      <span class="<%= fontColorDef %>"><bean:write name="calBean" property="dspDayString" /></span></th>
                      </logic:notEqual>
                      </logic:notEqual>
                      </logic:notEqual>


                      </logic:iterate>
                      </logic:notEmpty>
                      </tr>
                      </logic:equal>
                      </logic:notEqual>

                      <tr align="left" valign="top">
                      <!-- ユーザ欄 -->
                      <bean:define id="usrMdl" name="gpWeekMdl" property="ntp010UsrMdl"/>

                      <!-- ユーザ氏名 -->
                      <logic:equal name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                      <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
                      <td class="td_type1 break">
                      </logic:equal>
                      <logic:equal name="usrMdl" property="zaisekiKbn" value="2">
                      <td class="td_type_gaisyutu break">
                      </logic:equal>
                      <logic:equal name="usrMdl" property="zaisekiKbn" value="0">
                      <td class="td_type_kekkin break">
                      </logic:equal>
                      </logic:equal>
                      <logic:notEqual name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                      <td class="td_type1">
                      </logic:notEqual>

                        <span>
                        <a href="javaScript:void(0);" id="USR_AREA_<bean:write name="usrMdl" property="usrSid" />" onClick="openUserInfoWindow(<bean:write name="usrMdl" property="usrSid" />);"><bean:write name="usrMdl" property="usrName" /></a>
                        </span>
                        <span class="sc_link_5"><bean:write name="usrMdl" property="zaisekiMsg" /></span><br>

                        <span>
                          <a href="#" onClick="moveMonthNippou('month', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_gekkan_bg.gif" width="35" height="18" alt="" border="0">
                          <span class="sc_link_6"><gsmsg:write key="cmn.monthly" /></span>
                          </a>
                          <br>
                          <a href="#" onClick="moveListNippou('list', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_ichiran_bg.gif" width="35" height="18" alt="" border="0">
                          <span class="sc_link_6"><gsmsg:write key="cmn.list" /></span>
                          </a>
                          <br>
                          <logic:equal name="ntp010Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <logic:equal name="usrMdl" property="smlAble" value="1">
                          <a href="#" class="sml_send_link" id="<bean:write name="usrMdl" property="usrSid" />"><img src="../common/images/btn_smail_bg.gif" width="35" height="18" alt="" border="0">
<%--                           <a href="#" onClick="moveCreateMsg('msg', <bean:write name="usrMdl" property="usrSid" />, <bean:write name="usrMdl" property="usrKbn" />);"><img src="../common/images/btn_smail_bg.gif" width="35" height="18" alt="" border="0"> --%>
                          <span class="sc_link_6"><gsmsg:write key="cmn.shortmail" /></span>
                          </a>
                          <br>
                          </logic:equal>
                          </logic:equal>
                          <!-- 在席 -->
                          <logic:equal name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <logic:equal name="usrMdl" property="zaisekiKbn" value="1">
                          <input type="button" onClick="setFuzai(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#417984;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_fuzai" value="<gsmsg:write key="cmn.absence" />">
                          <input type="button" onClick="setSonota(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_sonota" value="<gsmsg:write key="cmn.other" />">
                          </logic:equal>
                          <logic:notEqual name="usrMdl" property="zaisekiKbn" value="1">
                          <!-- 不在、その他 -->
                          <input type="button" onClick="setZaiseki(<bean:write name="usrMdl" property="usrSid" />);" style="border:0px;color:#47a370;font-size:10px;font-weight:bold;width:55px;height:17px;" class="btn_zaiseki" value="<gsmsg:write key="cmn.zaiseki" />">
                          <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
                          </logic:notEqual>
                          </logic:equal>
                          <logic:notEqual name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
                          <img src="../common/images/damy.gif" width="35" height="18" alt="<gsmsg:write key="cmn.space" />" >
                          </logic:notEqual>

                        </span>
                      </td>

                    <!-- 日報情報 -->
                      <logic:notEmpty name="gpWeekMdl" property="ntp010NtpList">
                      <logic:iterate id="gpDayMdl" name="gpWeekMdl" property="ntp010NtpList">

                      <logic:equal name="gpDayMdl" property="weekKbn" value="1">
                        <td align="left" valign="top" class="td_type9">
                      </logic:equal>
                      <logic:equal name="gpDayMdl" property="weekKbn" value="7">
                        <td align="left" valign="top" class="td_type8">
                      </logic:equal>

                      <logic:notEqual name="gpDayMdl" property="weekKbn" value="1">
                      <logic:notEqual name="gpDayMdl" property="weekKbn" value="7">
                        <td align="left" valign="top" class="td_type1">
                      </logic:notEqual>
                      </logic:notEqual>


                        <logic:equal name="ntp010Form" property="adminKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">
                        <a href="#" onClick="return addNippou('add', <bean:write name="gpDayMdl" property="ntpDate" />, <bean:write name="gpDayMdl" property="usrSid" />, <bean:write name="gpDayMdl" property="usrKbn" />);"><img src="../nippou/images/add_nippou.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0"></a>
                        </logic:equal>
                        <br>

                        <logic:notEmpty name="gpDayMdl" property="ntpDataList">

                        <logic:iterate id="gpNtpMdl" name="gpDayMdl" property="ntpDataList">

                          <bean:define id="chkKbn" name="gpNtpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

                          <% chkClass = "ntp_chk"; %>
                          <% if (chkKbn == 1) { %>
                          <%    chkClass = ""; %>
                          <% } %>

                          <div onClick="editNippou('edit', <bean:write name="gpDayMdl" property="ntpDate" />, <bean:write name="gpNtpMdl" property="ntpSid" />, <bean:write name="usrMdl" property="usrSid" />, 0);" class="<%= chkClass %>" style="padding-top:5px;">

                          <bean:define id="u_usrsid" name="gpDayMdl" property="usrSid" type="java.lang.Integer" />
                          <bean:define id="u_nipsid" name="gpNtpMdl" property="ntpSid" type="java.lang.Integer" />
                          <bean:define id="u_date" name="gpDayMdl" property="ntpDate"  type="java.lang.String" />

                          <%
                            String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
                            String tipskey2 = ((Integer)pageContext.getAttribute("u_nipsid",PageContext.PAGE_SCOPE)).toString();
                            String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
                            String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
                          %>


                          <!-- コメントアイコン表示  -->
                          <logic:notEqual name="gpNtpMdl" property="ntp_cmtCnt" value="0">
                              <logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="0">
                                <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str2"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="1">
                                <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="2">
                                <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></a></span>
                              </logic:equal>

                              <logic:equal name="gpNtpMdl" property="ntp_goodCnt" value="0">
                                <br style="clear:both;line-height:1px;">
                              </logic:equal>
                          </logic:notEqual>


                          <!-- いいねアイコン表示  -->
                          <logic:notEqual name="gpNtpMdl" property="ntp_goodCnt" value="0">
                              <logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="0">
                                <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str2"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="1">
                                <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></a></span>
                              </logic:equal>
                              <logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="2">
                                <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></a></span>
                              </logic:equal>

                              <br style="clear:both;line-height:1px;">

                          </logic:notEqual>

                    <%--
                          <logic:empty name="gpNtpMdl" property="detail">
                          <a href="#" title="<bean:write name="gpNtpMdl" property="title" />" onClick="editNippou('edit', <bean:write name="gpDayMdl" property="ntpDate" />, <bean:write name="gpNtpMdl" property="ntpSid" />, <bean:write name="usrMdl" property="usrSid" />, 0);" id="ntpsid<%= tipskey %>">
                          </logic:empty>
                          <logic:notEmpty name="gpNtpMdl" property="detail">
                          <a href="#" onClick="editNippou('edit', <bean:write name="gpDayMdl" property="ntpDate" />, <bean:write name="gpNtpMdl" property="ntpSid" />, <bean:write name="usrMdl" property="usrSid" />, 0);" id="ntpsid<%= tipskey %>">
                          </logic:notEmpty>
                    --%>


                          <logic:empty name="gpNtpMdl" property="detail">
                          <a href="#" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"><span class="tooltips"><bean:write name="gpNtpMdl" property="title" /></span>
                          </logic:empty>
                          <logic:notEmpty name="gpNtpMdl" property="detail">
                          <bean:define id="ntpdetail2" name="gpNtpMdl" property="detail" />
                          <%
                            String tmpText3 = (String)pageContext.getAttribute("ntpdetail2",PageContext.PAGE_SCOPE);
                            String tmpText4 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText3);
                          %>
                          <a href="#" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"><span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText4 %></span>

                          </logic:notEmpty>




                          <!--タイトルカラー設定-->
                          <logic:equal name="gpNtpMdl" property="titleColor" value="0">
                          <span class="sc_link_1">
                          </logic:equal>
                          <logic:equal name="gpNtpMdl" property="titleColor" value="1">
                          <span class="sc_link_1">
                          </logic:equal>
                          <logic:equal name="gpNtpMdl" property="titleColor" value="2">
                          <span class="sc_link_2">
                          </logic:equal>
                          <logic:equal name="gpNtpMdl" property="titleColor" value="3">
                          <span class="sc_link_3">
                          </logic:equal>
                          <logic:equal name="gpNtpMdl" property="titleColor" value="4">
                          <span class="sc_link_4">
                          </logic:equal>
                          <logic:equal name="gpNtpMdl" property="titleColor" value="5">
                          <span class="sc_link_5">
                          </logic:equal>

                            <logic:notEmpty name="gpNtpMdl" property="time">
                            <font size="-2" class="time_line_height"><bean:write name="gpNtpMdl" property="time" /><br></font>
                            </logic:notEmpty>
                            <bean:write name="gpNtpMdl" property="title" />
                          </span>
                          </a>
<%--
                          <!-- 追加：確認者数表示  -->
                          <logic:notEqual name="gpNtpMdl" property="nkk_allcnt" value="0">
                            <font size="-2">(<bean:write name="gpNtpMdl" property="nkk_chkcnt" />/<bean:write name="gpNtpMdl" property="nkk_allcnt" />)</font>
                          </logic:notEqual>
--%>
                    <%--
                          <!-- ツールチップ -->
                          <div id="sctext<%= tipskey %>" style="display:none;padding: 5px;margin: 5px;background-color: #cccccc;border: solid 1px #666666;font-size: 12px; white-space:nowrap;">
                            <bean:define id="ntpnaiyou" name="gpNtpMdl" property="detail" />
                            <%
                              String tmpText = (String)pageContext.getAttribute("ntpnaiyou",PageContext.PAGE_SCOPE);
                              String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                              out.println("内容:");
                              out.println(tmpText2);
                            %>
                          </div>
                          <logic:notEmpty name="gpNtpMdl" property="detail">
                          <script type="text/javascript">
                            var ntp_tooltip<%= tipskey %> = new Tooltip('ntpsid<%= tipskey %>', 'sctext<%= tipskey %>');
                          </script>
                          </logic:notEmpty>
                          <!-- ツールチップ ここまで -->
                    --%>


<%--
                          <!--非公開-->
                          <logic:notEqual name="gpNtpMdl" property="public" value="0">
                          <span class="sc_nolink">
                            <logic:notEmpty name="gpNtpMdl" property="time">
                            <font size="-2"><bean:write name="gpNtpMdl" property="time" /><br></font>
                            </logic:notEmpty>
                            <bean:write name="gpNtpMdl" property="title" />
                          </span>
                          </logic:notEqual>
--%>
                          </div>
                        </logic:iterate>
                        </logic:notEmpty>
                      </td>

                      </logic:iterate>
                      </logic:notEmpty>
                      </tr>

                      </logic:iterate>
                      </logic:notEmpty>

                    </table>
                    <img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0">
                    <div align="left">
                      <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                        <logic:equal name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                          <td class="td_type1" nowrap><span class="text_base2"><gsmsg:write key="cmn.zaiseki2" /></span></td>
                          <td>&nbsp;</td>
                          <td class="td_type_gaisyutu" nowrap><span class="text_base2"><gsmsg:write key="cmn.absence2" /></span></td>
                          <td>&nbsp;</td>
                          <td class="td_type_kekkin" nowrap><span class="text_base2"><gsmsg:write key="cmn.other" /></span></td>
                        </logic:equal>
                        <logic:notEqual name="ntp010Form" property="zaisekiUseOk" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.PLUGIN_USE) %>">
                        <td></td>
                        </logic:notEqual>
                        </tr>
                      </table>
                    </div>
                    <br>

                </td>
                </tr>
                </table>
          <%-- /div --%>

        </td>

      </tr>

    </table>

</td>

</tr>

</table>


</html:form>

<div id="smlPop" title="" style="display:none">
  <div id="smlCreateArea" width="100%" height="100%"></div>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>