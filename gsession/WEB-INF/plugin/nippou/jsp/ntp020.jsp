<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /><gsmsg:write key="cmn.list" />(<gsmsg:write key="cmn.monthly" />)</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../nippou/js/ntp020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jtooltip.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="ntp020Form" property="ntp020Reload" value="0">
    var reloadinterval = <bean:write name="ntp020Form" property="ntp020Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>


<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% long ntpTipCnt = 0; %>
<body class="body_03" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/nippou/ntp020">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="ntp010DspDate" />


<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />

<html:hidden property="ntp010HistoryAnkenSid" />
<html:hidden property="ntp010HistoryCompSid" />
<html:hidden property="ntp010HistoryCompBaseSid" />
<html:hidden property="ntp010SessionUsrId" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.monthly" /> ]</td>
  <td width="100%" class="header_white_bg">
  <%-- input type="button" value="再読込" class="btn_reload_n1" onClick="buttonPush('reload');"--%>
  <%-- input type="button" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush('anken');"--%>
  <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
  <logic:equal name="ntp020Form" property="adminKbn" value="1">
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

            <logic:equal name="ntp020Form" property="adminKbn" value="1">
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
                <logic:notEmpty name="ntp020Form" property="ankenHistoryList">
                  <table width="100%">
                    <tr>
                      <td align="left" width="100%" class="table_bg_7D91BD text_tl5">
                        <span class="text_tlw"><img style="vertical-align:middle;padding-bottom:3px;" src="../nippou/images/history_adr_icon_mini.gif" /><gsmsg:write key="ntp.11" /><gsmsg:write key="ntp.17" /></span><br>
                      </td>
                    </tr>
                    <logic:iterate id="ankenMdl" name="ntp020Form" property="ankenHistoryList">
                      <tr>
                        <td width="100%" align="left" id="<bean:write name="ankenMdl" property="nanSid" />" class="ankenHistoryArea ankenAreaSel">
                          <span style="font-size:12px;"><bean:write name="ankenMdl" property="nanName" /></span>
                        </td>
                      </tr>
                    </logic:iterate>
                  </table>
                  <br>
                </logic:notEmpty>

                <logic:notEmpty name="ntp020Form" property="companyHistoryList">
                  <table width="100%">
                    <tr>
                      <td align="left" width="100%" class="table_bg_7D91BD text_tl5">
                        <span class="text_tlw"><img style="vertical-align:middle;padding-bottom:3px;" src="../nippou/images/history_cmp_icon_mini.gif" /><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /><gsmsg:write key="ntp.17" /></span><br>
                      </td>
                    </tr>
                    <logic:iterate id="companyMdl" name="ntp020Form" property="companyHistoryList">
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

        <%-- 日報データ --%>
        <%-- div id="nippouArea" class="areablock" style="vertical-align:top;padding-left:10px;padding-right:10px;"--%>
                <table width="100%">
                <tr>
                <td width="100%" style="padding-left:15px;padding-right:10px;">

                          <table cellpadding="5" width="100%" class="tl0">
                          <tr>
                          <td colspan="7" class="td_type0">

                            <table width="100%" class="tl0" border="0">
                            <tr>

                            <td width="30%" align="left" nowrap>

                            </td>
                            <td width="30%" align="left" nowrap>

                            </td>

                            <td width="40%" align="center" style="padding-top:5px;padding-bottom:5px;padding-bottom:10px;" nowrap>
                              <input type="button" value="<gsmsg:write key="ntp.18" />" class="btn_time_line" onClick="buttonPush('day');">
                              <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('week');">
                              <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="buttonPush('reload');">
                              <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_schedule_search"  onClick="buttonPush('list');">&nbsp;&nbsp;
                            </td>
                            <td width="0%" align="right" nowrap style="padding-top:0px;padding-bottom:8px;">
                              <input type="image" name="zweek" src="../nippou/images/arrow1_l.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:10px;visibility:hidden">
                              <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_lm');">
                              <input type="button" class="btn_today" value="<gsmsg:write key="cmn.thismonth3" />" onClick="buttonPush('today');">
                              <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rm');">
                              <input type="image" name="yweek" src="../nippou/images/arrow1_r.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:10px;visibility:hidden">
                            </td>

                            <td width="0%" align="right" style="padding-top:0px;padding-bottom:6px;" nowrap>
                              <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.ntp010DspDate, 'ntp020CalBtn')" class="calendar_btn2" id="ntp020CalBtn">
                            </td>

                            </tr>
                            </table>

                          </td>
                          </tr>

                          <logic:messagesPresent message="false">
                          <tr>
                          <td colspan="7"><html:errors /></td>
                          </tr>
                          </logic:messagesPresent>

                          <tr>
                          <td colspan="7" class="table_bg_7D91BD" align="center">
                            <table width="100%" class="tl0">
                              <tr>
                              <td width="15%" align="left" class="text_tl3">
                                <span><font color="ffffff"><bean:write name="ntp020Form" property="ntp020StrDspDate" scope="request" /></font></span>
                                &nbsp;&nbsp;
                                <span class="text_tl1"><!--a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="ntp020Form" property="ntp010SelectUsrSid" />);"><font color="ffffff"><bean:write name="ntp020Form" property="ntp020StrUserName" scope="request" /></font></a-->
                              </td>

                              <td width="10%" align="left" nowrap>
                                <span class="text_tlw"><gsmsg:write key="cmn.show.group" /></span>
                                <html:select property="ntp010DspGpSid" styleClass="select02" onchange="changeGroupCombo();">

                                  <logic:notEmpty name="ntp020Form" property="ntp010GpLabelList" scope="request">
                                  <logic:iterate id="gpBean" name="ntp020Form" property="ntp010GpLabelList" scope="request">

                                  <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                                  <logic:equal name="gpBean" property="styleClass" value="0">
                                  <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                                  </logic:equal>

                                  <logic:notEqual name="gpBean" property="styleClass" value="0">
                                  <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                                  </logic:notEqual>

                                  </logic:iterate>
                                  </logic:notEmpty>
                                  <input type="button" onclick="openGroupWindow(this.form.ntp010DspGpSid, 'ntp010DspGpSid', '1')" class="group_btn" value="&nbsp;&nbsp;" id="ntp010GroupBtn">
                                </html:select>
                              </td>


                              <td width="10%" align="left" nowrap>
                                <html:select property="ntp020SelectUsrSid" styleClass="select02" onchange="changeUserCombo();">
                                  <html:optionsCollection name="ntp020Form" property="ntp020UsrLabelList" value="value" label="label" />
                                </html:select>

                                <logic:equal name="ntp020Form" property="ntp020SelectUsrSid" value="-1">
                                <input type="hidden" name="ntp010SelectUsrSid" value="<bean:write name="ntp020Form" property="ntp010DspGpSid" />">
                                <input type="hidden" name="ntp010SelectUsrKbn" value="1">
                                </logic:equal>
                                <logic:equal name="ntp020Form" property="ntp020SelectUsrSid" value="-2">
                                <input type="hidden" name="ntp010SelectUsrSid" value="<bean:write name="ntp020Form" property="ntp010DspGpSid" />">
                                <input type="hidden" name="ntp010SelectUsrKbn" value="1">
                                </logic:equal>
                                <logic:notEqual name="ntp020Form" property="ntp020SelectUsrSid" value="-1">
                                <logic:notEqual name="ntp020Form" property="ntp020SelectUsrSid" value="-2">
                                <input type="hidden" name="ntp010SelectUsrSid" value="<bean:write name="ntp020Form" property="ntp020SelectUsrSid" />">
                                <input type="hidden" name="ntp010SelectUsrKbn" value="0">
                                </logic:notEqual>
                                </logic:notEqual>
                              </td>


                              <%--
                              <td width="0%" align="right">
                              <logic:equal name="ntp020Form" property="ntp020IkkatuBttn" value="1">
                                <input type="button" value="一括<gsmsg:write key="cmn.check" />" class="btn_base_ikkatu" onClick="buttonPush('ikkatu');">
                              </logic:equal>
                              <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
                              <html:text property="ntp010searchWord" styleClass="text_base" maxlength="50"/>
                              <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_base0" onClick="buttonPush('search');">
                              </td>
                              --%>
                              </tr>
                            </table>

                          </td>
                          </tr>
                          <tr>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sun"><gsmsg:write key="cmn.sunday" /></span></th>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.Monday" /></span></th>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.tuesday" /></span></th>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.wednesday" /></span></th>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.thursday" /></span></th>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_def"><gsmsg:write key="cmn.friday" /></span></th>
                          <th width="14%" nowrap class="td_type3"><span class="sc_ttl_sat"><gsmsg:write key="cmn.saturday" /></span></th>
                          </tr>


                          <logic:notEmpty name="ntp020Form" property="ntp020NippouList" scope="request">
                            <logic:iterate id="monthMdl" name="ntp020Form" property="ntp020NippouList" scope="request">
                            <bean:define id="usrMdl" name="monthMdl" property="ntp020UsrMdl"/>

                            <logic:notEmpty name="monthMdl" property="ntp020NtpList">
                            <logic:iterate id="dayMdl" name="monthMdl" property="ntp020NtpList">

                            <!--１週間-->
                            <!--背景-->
                            <logic:equal name="dayMdl" property="weekKbn" value="1">
                            <tr>
                            <td valign="top" class="td_type9" height="60">
                            </logic:equal>
                            <logic:equal name="dayMdl" property="weekKbn" value="7">
                            <td valign="top" class="td_type8" height="60">
                            </logic:equal>

                            <logic:notEqual name="dayMdl" property="weekKbn" value="1">
                            <logic:notEqual name="dayMdl" property="weekKbn" value="7">
                            <td valign="top" class="td_type1" height="60">
                            </logic:notEqual>
                            </logic:notEqual>

                            <logic:equal name="ntp020Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">
                            <span id="lt" class="text_tl1">
                            <a href="#" onClick="addNippou('add', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);"><img src="../nippou/images/add_nippou.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0"></a>
                            </span>
                            </logic:equal>

                            <!-- 追加：一括確認チェック欄表示  -->
                            <logic:equal name="dayMdl" property="ikkatuKbn" value="0">
                            <span id="lt" class="text_tl3">
                            <html:multibox name="ntp020Form" property="rsvIkkatuKey"><bean:write name="dayMdl" property="ikkatuChk" /></html:multibox><gsmsg:write key="cmn.check" />
                            </span>
                            </logic:equal>


                            <logic:notEqual name="dayMdl" property="holidayKbn" value="1">
                            <span id="rt">
                            <!--日付-->

                            <logic:equal name="dayMdl" property="todayKbn" value="1">
                            <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="sc_thismonth_today">
                            </logic:equal>
                            <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="sc_month_today">
                            </logic:notEqual>
                            </logic:equal>

                            <logic:notEqual name="dayMdl" property="todayKbn" value="1">
                            <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="text_base3">
                            </logic:equal>
                            <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="text_base5">
                            </logic:notEqual>
                            </logic:notEqual>



                            <bean:write name="dayMdl" property="dspDay" />
                            </span>

                            </span>
                            </logic:notEqual>

                            <logic:equal name="dayMdl" property="holidayKbn" value="1">
                            <span id="rt">
                            <!--日付-->
                            <a href="#" onClick="moveDailyNippou('day', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="dayMdl" property="usrSid" />, <bean:write name="dayMdl" property="usrKbn" />);">
                            <logic:equal name="dayMdl" property="todayKbn" value="1">
                            <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="sc_thismonth_holiday_today">
                            </logic:equal>
                            <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="sc_month_holiday_today">
                            </logic:notEqual>
                            </logic:equal>

                            <logic:notEqual name="dayMdl" property="todayKbn" value="1">
                            <logic:equal name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="text_base4">
                            </logic:equal>
                            <logic:notEqual name="dayMdl" property="thisMonthKbn" value="1">
                            <span class="text_base6">
                            </logic:notEqual>
                            </logic:notEqual>

                            <bean:write name="dayMdl" property="dspDay" />
                            </span></a>
                            </span>
                            </logic:equal>

                            <!--休日名称-->
                            <logic:notEmpty name="dayMdl" property="holidayName">
                            <br>
                            <span id="rt"><font size="-2" class="time_line_height" color="#ff0000">
                            <bean:write name="dayMdl" property="holidayName" />
                            </font></span>
                            </logic:notEmpty>
                            <logic:empty name="dayMdl" property="holidayName">
                            <br>
                            </logic:empty>
                            <img src="../schedule/images/spacer.gif" width="1px" height="1px" border="0">
                            <logic:notEmpty name="dayMdl" property="ntpDataList">
                            <logic:iterate id="ntpMdl" name="dayMdl" property="ntpDataList">

                            <bean:define id="chkKbn" name="ntpMdl" property="ntp_chkKbn" type="java.lang.Integer" />
                            <% String chkClass = "ntp_chk"; %>
                            <% if (chkKbn == 1) { %>
                            <%    chkClass = ""; %>
                            <% } %>
                            <div class="<%= chkClass %>" onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="dayMdl" property="usrSid" />, 0);" style="padding-top:5px;">


                              <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
                              <bean:define id="u_ntpsid" name="ntpMdl" property="ntpSid" type="java.lang.Integer" />
                              <bean:define id="u_date" name="dayMdl" property="ntpDate"  type="java.lang.String" />

                              <%
                                String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
                                String tipskey2 = ((Integer)pageContext.getAttribute("u_ntpsid",PageContext.PAGE_SCOPE)).toString();
                                String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
                                String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
                              %>


                              <!--公開-->
                              <%--
                              <logic:equal name="ntpMdl" property="public" value="0">
                              --%>



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
                              <a href="#" title="<bean:write name="ntpMdl" property="title" />" onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="ntpMdl" property="userSid" />, 0);" id="ntpsid<bean:write name="ntpMdl" property="ntpSid" />_<%= tipskey %>">
                              </logic:empty>
                              <logic:notEmpty name="ntpMdl" property="detail">
                              <a href="#" onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="ntpMdl" property="userSid" />, 0);" id="ntpsid<bean:write name="ntpMdl" property="ntpSid" />_<%= tipskey %>">
                              </logic:notEmpty>
                        --%>


                              <logic:empty name="ntpMdl" property="detail">
                              <a href="#" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"><span class="tooltips"><bean:write name="ntpMdl" property="title" /></span>
                              </logic:empty>
                              <logic:notEmpty name="ntpMdl" property="detail">
                              <bean:define id="ntpnaiyou" name="ntpMdl" property="detail" />
                              <%
                                String tmpText = (String)pageContext.getAttribute("ntpnaiyou",PageContext.PAGE_SCOPE);
                                String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTmlPlusAmparsant(tmpText);
                              %>
                              <a href="#" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"><span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                              </logic:notEmpty>

                              <logic:equal name="ntpMdl" property="userKbn" value="1">
                              <span class="sc_link_g">G</span>
                              </logic:equal>
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

                                <logic:notEmpty name="ntpMdl" property="userName">
                                <span class="sc_link_mem"><bean:write name="ntpMdl" property="userName" /><br></span>
                                </logic:notEmpty>

                                <logic:notEmpty name="ntpMdl" property="time">
                                <font size="-2" class="time_line_height"><bean:write name="ntpMdl" property="time" /><br></font>
                                </logic:notEmpty>
                                <bean:write name="ntpMdl" property="title" />

                              </span>
                              </a>
<%--
                              <!-- 追加：確認者数表示  -->
                              <logic:notEqual name="ntpMdl" property="nkk_allcnt" value="0">
                                <font size="-2" class="time_line_height">(<bean:write name="ntpMdl" property="nkk_chkcnt" />/<bean:write name="ntpMdl" property="nkk_allcnt" />)</font>
                              </logic:notEqual>
--%>
                        <%--
                              <!-- ツールチップ -->
                              <div id="sctext<bean:write name="ntpMdl" property="ntpSid" />_<%= tipskey %>" style="display:none;padding: 5px;margin: 5px;background-color: #cccccc;border: solid 1px #666666;font-size: 12px;white-space:nowrap;">
                                <bean:define id="scnaiyou" name="ntpMdl" property="detail" />
                                <%
                                  String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                                  String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                                  out.println("内容:");
                                  out.println(tmpText2);
                                %>
                              </div>
                              <logic:notEmpty name="ntpMdl" property="detail">
                              <script type="text/javascript">
                                var ntp_tooltip<bean:write name="ntpMdl" property="ntpSid" />_<%= tipskey %> = new Tooltip('ntpsid<bean:write name="ntpMdl" property="ntpSid" />_<%= tipskey %>', 'sctext<bean:write name="ntpMdl" property="ntpSid" />_<%= tipskey %>')
                              </script>
                              </logic:notEmpty>
                              <!-- ツールチップ ここまで -->
                        --%>
                        <%--
                              </logic:equal>

                              <!--非公開-->
                              <logic:notEqual name="ntpMdl" property="public" value="0">
                              <span class="sc_nolink">
                                <logic:notEmpty name="ntpMdl" property="time">
                                <font size="-2"><bean:write name="ntpMdl" property="time" /><br></font>
                                </logic:notEmpty>
                                <bean:write name="ntpMdl" property="title" />
                              </span>
                              </logic:notEqual>
                         --%>
                            </div>
                            </logic:iterate>
                            </logic:notEmpty>
                            </td>

                            <logic:equal name="dayMdl" property="weekKbn" value="7">
                            </tr>
                            </logic:equal>

                            </logic:iterate>
                            </logic:notEmpty>


                            </logic:iterate>
                          </logic:notEmpty>
                          </table>
                </td>
                </tr>
                </table>
          <%-- /div --%><br>

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