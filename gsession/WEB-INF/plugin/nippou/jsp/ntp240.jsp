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

<title>[GroupSession] <gsmsg:write key="ntp.12" /><gsmsg:write key="cmn.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>"></script>
<!--[if IE]><script type="text/javascript" src="../common/js/graph_circle_1_0_2/excanvas/excanvas.js"></script><![endif]-->
<script language="JavaScript" src="../common/js/jplot/jquery.jqplot.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.barRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.highlighter.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp240.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/js/jplot/css/jquery.jqplot.min.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/nippou/ntp240">

<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="ntp.12" /><gsmsg:write key="cmn.setting" /> ]</td>
  <td width="100%" class="header_white_bg">

  <%-- input type="button" value="<gsmsg:write key="ntp.11" />検索" class="btn_search_n1" onClick="buttonPush2('anken');" --%>

    <logic:equal name="ntp240Form" property="adminKbn" value="1">
      <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush2('ktool');">
    </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush2('pset');">

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

            <tr class="menu_not_select_tr" class="" onClick="buttonPush2('nippou');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_single.gif" alt="<gsmsg:write key="ntp.1" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.1" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush2('anken');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_anken.gif" alt="<gsmsg:write key="ntp.11" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.11" /></span>
              </td>
            </tr>

            <tr class="menu_select_tr">
              <td class="menu_select_icon">
                <img src="../nippou/images/menu_icon_target.gif" alt="<gsmsg:write key="ntp.12" />" />
              </td>
              <td class="menu_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.12" /></span>
              </td>
              <td class="menu_select_text" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
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

            <logic:equal name="ntp240Form" property="adminKbn" value="1">
              <tr class="menu_not_select_tr" onClick="buttonPush('masta');">
                <td class="menu_not_select_icon">
                  <img src="../nippou/images/menu_icon_mente.gif" alt="<gsmsg:write key="ntp.14" />" />
                </td>
                <td class="menu_not_select_text" colspan="2" style="padding-top:7px;padding-right:5px;" valign="top" nowrap>
                  <span class="timeline_menu2"><gsmsg:write key="ntp.14" /></span>
                </td>
              </tr>
            </logic:equal>

            <tr>
              <td class="menu_space_area_left"></td>
              <td class="menu_space_area_right" colspan="2">&nbsp;</td>
            </tr>


          </table>
        </td>

        <td valign="top" width="84%">

          <%--  div style="width:96%;padding-left:30px;" --%>

                <table width="100%">
                <tr>
                <td width="100%" style="padding-left:15px;padding-right:10px;">

                <br>




                <logic:notEmpty name="ntp240Form" property="ntp240DspTargetList" scope="request">
                  <logic:iterate id="trgDspMdl" name="ntp240Form" property="ntp240DspTargetList" scope="request" indexId="idx">


                    <% if (idx.intValue() == 0) { %>

                      <table align="left">
                      <tr>

                        <td width="0%">
                            <html:select property="ntp240Year" styleId="selYearsf" onchange="changeCmb();">
                            <html:optionsCollection name="ntp240Form" property="ntp240YearLabel" value="value" label="label" />
                            </html:select>
                        </td>

                        <td width="0%" nowrap>
                            <html:select property="ntp240Month" styleId="selMonthsf" onchange="changeCmb();">
                            <html:optionsCollection name="ntp240Form" property="ntp240MonthLabel" value="value" label="label" />
                            </html:select>
                            <input class="btn_arrow_l" value="&nbsp;" onclick="prevMonth();" type="button">
                            <input class="btn_today" value="<gsmsg:write key="cmn.thismonth" />" onclick="thisMonth();" type="button">
                            <input class="btn_arrow_r" value="&nbsp;" onclick="nextMonth();" type="button">
                        </td>

                        <td align="left" nowrap width="0%">&nbsp;
                          <logic:notEmpty name="ntp240Form" property="ntp240GpLabelList" scope="request">
                            <html:select property="ntp240DspGpSid" styleClass="select01" onchange="changeGroupCombo();">
                              <logic:iterate id="gpBean" name="ntp240Form" property="ntp240GpLabelList" scope="request">

                                <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                                <logic:equal name="gpBean" property="styleClass" value="0">
                                <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                                </logic:equal>

                                <logic:notEqual name="gpBean" property="styleClass" value="0">
                                <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                                </logic:notEqual>

                              </logic:iterate>

                            </html:select>
                            <input type="button" onclick="openGroupWindow(this.form.ntp240DspGpSid, 'ntp240DspGpSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="ntp240GroupBtn">
                          </logic:notEmpty>
                          <logic:empty name="ntp240Form" property="ntp240GpLabelList" scope="request">
                            <b><bean:write name="ntp240Form" property="ntp240UserName" /></b>
                          </logic:empty>
                        </td>

                        <td align="left" nowrap width="0%">
                          <logic:notEmpty name="ntp240Form" property="ntp240UserLabel" scope="request">
                            <html:select property="ntp240SelectUsrSid" styleClass="select01" onchange="changeCmb();">
                              <html:optionsCollection name="ntp240Form" property="ntp240UserLabel" value="value" label="label" />
                            </html:select>
                          </logic:notEmpty>
                        </td>
                      </tr>
                      </table>

                    <% } else { %>
                      <span style="color:#333333;font-size:14px;font-weight:bold;">&nbsp;<bean:write name="trgDspMdl" property="year" />年<bean:write name="trgDspMdl" property="month" />月</span>
                    <% } %>

                    <br style="clear:both;">


                    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
                    <tbody>
                      <tr>
                      <th class="table_bg_7D91BD" align="center" nowrap width="30%"><span class="text_tlw"><gsmsg:write key="ntp.12" /></span></th>
                      <th class="table_bg_7D91BD" align="center" nowrap width="60%"><span class="text_tlw"><bean:write name="trgDspMdl" property="year" />年<bean:write name="trgDspMdl" property="month" />月&nbsp;<gsmsg:write key="ntp.139" /></span></th>
                      </tr>

                        <logic:notEmpty name="trgDspMdl" property="ntgList">
                          <logic:iterate id="trgUsrMdl" name="trgDspMdl" property="ntgList" indexId="idx2">

                            <bean:define id="tdColor" value="" />
                            <% String[] tdColors = new String[] {"td_type1", "td_type29"}; %>
                            <% tdColor = tdColors[(idx2.intValue() % 2)]; %>

                            <% String recordColor = ""; %>
                            <% String barGraphClass = ""; %>

                            <logic:equal name="trgUsrMdl" property="npgTargetKbn" value="0">
                            <% barGraphClass = "target_bar_graph"; %>
                            </logic:equal>

                            <logic:equal name="trgUsrMdl" property="npgTargetKbn" value="1">
                            <% barGraphClass = "target_bar_graph2"; %>
                            <% recordColor = "record_comp"; %>
                            </logic:equal>

                            <tr class="<%= tdColor %>" align="center">
                              <!-- 目標名 -->
                              <td align="left" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />">
                                <span style="font-size:16px;padding:10px;" class="text_link targetPopLink cursor_pointer"><bean:write name="trgUsrMdl" property="npgTargetName" /></span>
                              </td>
                              <!-- 達成度 -->
                              <td align="right" style="padding-right:10px;" nowrap>

                                <table width="100%" class="table_no_border">
                                <tr>
                                  <!-- 棒グラフ -->
                                  <td width="60%" style="padding-left:5px;padding-right:5px;">
                                    <table class="target_bar_table">
                                      <tr>
                                      <div style="position:relative;top:17px;line-height:0px;margin:0px:padding:0px;"><span  style="margin:0px:padding:0px;line-height:10px;" class="target_bar_graph_str" id="ratioStr_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />"><bean:write name="trgUsrMdl" property="npgTargetRatioStr" />%</span></div>
                                       <td>
                                        <div id="barTargetRatio_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" style="width:<bean:write name="trgUsrMdl" property="npgTargetRatio" />%;" class="<%= barGraphClass %>"></div>
                                        <div id="barTargetUnRatio_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" style="width:<bean:write name="trgUsrMdl" property="npgTargetUnRatio" />%;" class="target_bar_graph_none"></div>
                                       </td>
                                      </tr>
                                    </table>
                                  </td>

                                  <!-- 数値 -->
                                  <td width="40%" align="right" nowrap>

                                    <div style="display:block;" class="target_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />">
                                      <b>
                                        <span class="<%= recordColor %>" id="spanRecord_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />">
                                          <bean:write name="trgUsrMdl" property="npgRecord" />
                                        </span>
                                      </b>/
                                      <span id="spanTarget_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />">
                                        <bean:write name="trgUsrMdl" property="npgTarget" />
                                      </span>
                                      <bean:write name="trgUsrMdl" property="npgTargetUnit" />
                                      <input class="target_settei_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" value="変更" type="button">
                                    </div>
                                    <div style="display:none;" class="target_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />">
                                      <table class="table_border_none">
                                        <tr>
                                          <td nowrap>
                                            <input style="text-align:right; width:97px;" name="npgRecord" maxlength="15" value="<bean:write name="trgUsrMdl" property="npgRecord" />" id="trgRecord_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" class="text_base" type="text">/
                                            <input style="text-align:right; width:97px;" name="npgTarget" maxlength="15" value="<bean:write name="trgUsrMdl" property="npgTarget" />" id="trgTarget_<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" class="text_base text_ntp240_npgTarget" type="text">
                                            <bean:write name="trgUsrMdl" property="npgTargetUnit" />
                                          </td>
                                          <td>
                                            <table class="table_border_none">
                                              <tr>
                                                <td>
                                                  <input class="target_kakutei_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" value="確定" type="button">
                                                </td>
                                              </tr>
                                              <tr>
                                                <td>
                                                  <input class="target_cansel_btn" id="<bean:write name="trgDspMdl" property="year" />_<bean:write name="trgDspMdl" property="month" />_<bean:write name="ntp240Form" property="ntp240SelectUsrSid" />_<bean:write name="trgUsrMdl" property="ntgSid" />" value="キャンセル" type="button">
                                                </td>
                                              </tr>
                                            </table>
                                          </td>
                                        </tr>
                                      </table>
                                    </div>


                                  </td>

                                </tr>
                                </table>

                              </td>
                            </tr>

                          </logic:iterate>
                        </logic:notEmpty>

                    </tbody>
                    </table>

                    <br>

                  </logic:iterate>
                </logic:notEmpty>

                <logic:empty name="ntp240Form" property="ntp240DspTargetList" scope="request">
                      <table align="left">
                      <tr>

                        <td width="0%">
                            <html:select property="ntp240Year" styleId="selYearsf" onchange="changeCmb();">
                            <html:optionsCollection name="ntp240Form" property="ntp240YearLabel" value="value" label="label" />
                            </html:select>
                        </td>

                        <td width="0%">
                            <html:select property="ntp240Month" styleId="selMonthsf" onchange="changeCmb();">
                            <html:optionsCollection name="ntp240Form" property="ntp240MonthLabel" value="value" label="label" />
                            </html:select>
                        </td>


                        <td align="left" nowrap width="0%">&nbsp;
                          <logic:notEmpty name="ntp240Form" property="ntp240GpLabelList" scope="request">
                            <html:select property="ntp240DspGpSid" styleClass="select01" onchange="changeGroupCombo();">
                              <logic:iterate id="gpBean" name="ntp240Form" property="ntp240GpLabelList" scope="request">

                                <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                                <logic:equal name="gpBean" property="styleClass" value="0">
                                <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                                </logic:equal>

                                <logic:notEqual name="gpBean" property="styleClass" value="0">
                                <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                                </logic:notEqual>

                              </logic:iterate>

                            </html:select>
                            <input type="button" onclick="openGroupWindow(this.form.ntp240DspGpSid, 'ntp240DspGpSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="ntp240GroupBtn">
                          </logic:notEmpty>
                          <logic:empty name="ntp240Form" property="ntp240GpLabelList" scope="request">
                            <b><bean:write name="ntp240Form" property="ntp240UserName" /></b>
                          </logic:empty>
                        </td>

                        <td align="left" nowrap width="0%">
                          <logic:notEmpty name="ntp240Form" property="ntp240UserLabel" scope="request">
                            <html:select property="ntp240SelectUsrSid" styleClass="select01" onchange="changeCmb();">
                              <html:optionsCollection name="ntp240Form" property="ntp240UserLabel" value="value" label="label" />
                            </html:select>
                          </logic:notEmpty>
                        </td>
                      </tr>
                      </table>

                     <br style="clear:both;">
                     <span style="padding-top:220px;height:100%;width:100%;font-size:12px;font-weight:bold;text-align:center;"><gsmsg:write key="ntp.140" /></span>

                </logic:empty>

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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>



<div id="targetPop" title="<gsmsg:write key="ntp.139" />" style="display:none;">
  <p>

    <table style="border-collapse:collapse;" width="100%">
      <tr>
        <td align="left" style="color:333333;font-size:16px;" width="30%"><b>&nbsp;<span id="popTrgYear"></span><gsmsg:write key="ntp.168" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="popTrgUsr"></span></b></td>
        <td align="center" style="color:333333;font-size:16px;" width="40%"><b><span id="popTrgTarget"></span></b></td>
        <td style="padding-top:3px;padding-right:10px;" align="right" nowrap width="0%">
          <input class="btn_arrow_l cursor_pointer" value="&nbsp;" id="popPrevBtn" type="button">
          <input class="btn_today cursor_pointer" value="<gsmsg:write key="ntp.169" />" id="popThisYearBtn" type="button">
          <input class="btn_arrow_r cursor_pointer" value="&nbsp;" id="popNextBtn" type="button">

          <input type="hidden" id="popHideYear" value="" />
          <input type="hidden" id="popHideMonth" value="" />
          <input type="hidden" id="popHideUsrSid" value="" />
          <input type="hidden" id="popHideNtgSid" value="" />
         </td>
      </tr>
    </table>

    <span id="loadingArea"></span>

    <span id="yearTargetArea" style="width:780px;">
      <span id="yearTargetDataArea" style="overflow-x:scroll;"></span>
    </span>

    <table style="border-collapse:collapse;" width="100%">
      <tr>
        <td align="center"><u><span style="color:#333333;font-size:14px;"><gsmsg:write key="ntp.141" />：</span><b style="color:333333;font-size:16px;"><span id="popTrgRatio"></span></b>%</u></td>
      </tr>
      <tr>
        <td align="left">
          <input type="button" class="graph-btn-active" id="changeLineGraph" value="<gsmsg:write key="ntp.142" />"/><input type="button" class="graph-btn-unactive" id="changeBarGraph" value="<gsmsg:write key="ntp.143" />"/>
        </td>
      </tr>
      <tr>
      <tr>
        <td align="center" class="graph_top">
          <span id="line_title" class="graph_title"><gsmsg:write key="ntp.142" />(%)/<gsmsg:write key="cmn.month" /></span>
          <span id="bar_title" class="graph_title"><gsmsg:write key="ntp.143" />(<span id="bar_unit"></span>)/<gsmsg:write key="cmn.month" /></span>
        </td>
      </tr>
      <tr>
        <td  class="graph_bottom" align="center" id="graph_area">
          <div id="linechart" style="z-index:200;width:650px;height:200px;"></div>
          <div id="barchart" style="z-index:200;width:650px;height:200px;"></div>
        </td>
     </tr>

    </table>



  </p>
</div>

<div id="dialogEditOk" title="<gsmsg:write key="ntp.144" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.145" /></b>
    </p>
</div>

<div id="dialog_error" class="error_dialog" title="" style="display:none">
    <p>
      <table>
        <tr>
          <td class="ui-icon ui-icon-alert" valign="middle"></td>
          <td id="error_msg" valign="middle" style="padding-left:20px;" class="TXT02"></td>
        </tr>
      </table>
    </p>
</div>



</html:form>
</body>
</html:html>