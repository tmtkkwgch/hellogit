<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><gsmsg:write key="main.login.history" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<!--[if IE]><script type="text/javascript" src="../common/js/graph_circle_1_0_2/excanvas/excanvas.js"></script><![endif]-->
<script language="JavaScript" src="../common/js/jplot/jquery.jqplot.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.barRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="javascript" src="../common/js/jplot/jqplot.pieRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.highlighter.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.categoryAxisRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.canvasAxisTickRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../common/js/jplot/jqplot.canvasTextRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../common/js/jplot/jqplot.pointLabels.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.cursor.min.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery-ui-1.8.16.custom.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/i18n/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>'></script>

<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../main/js/man390.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/js/jplot/css/jquery.jqplot.min.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/js/jquery-ui-1.8.16/development-bundle/themes/base/jquery.ui.all.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/js/jquery-ui-1.8.16/ui/dialog/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<style type="text/css">
.textfield { position: relative; margin: 0 0 0 0;}
.textfield label { position: absolute; top: 4; left: 6; font-size:12px; color:#333333;}
.textfield br {display: none;}
</style>

</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/main/man390">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="man390DateWeeklyFrStr" />
<html:hidden property="man390DateWeeklyToStr" />
<html:hidden property="man390DateDailyFrStr" />
<html:hidden property="man390DateDailyToStr" />
<html:hidden property="man390NowPage"/>
<html:hidden property="man390GraphItem"/>
<html:hidden property="logCountBack"/>

<html:hidden property="jsonDateData"/>
<html:hidden property="jsonLoginRate"/>

<logic:notEqual name="man390Form" property="man390DateUnit" value="2">
  <html:hidden property="man390DateMonthlyFrYear"/>
  <html:hidden property="man390DateMonthlyFrMonth"/>
  <html:hidden property="man390DateMonthlyToYear"/>
  <html:hidden property="man390DateMonthlyToMonth"/>
</logic:notEqual>

<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="90%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.statistical.info" /> ]</td>
    <td width="10%" class="header_ktool_bg_text2"><input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('admTool');"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
    </tr>
    </table>


<img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0" alt="">

<table cellpadding="1px" width="100%">
<tr>
<td>

    <!-- 各統計情報メニュー -->
    <table cellpadding="0" cellspacing="0" class="menu_table">

      <tr>
        <td valign="top" width="16%" class="menu_space_area_both">
          <table width="100%" height="100%" cellpadding="0" cellspacing="0">

            <%-- ログイン履歴 --%>
            <tr class="menu_select_tr">
              <td class="menu_select_icon_no_top">
                &nbsp;<img src="../main/images/menu_icon_login_history.gif" alt="<gsmsg:write key="main.login.history" />" />
              </td>
              <td class="menu_select_text_no_top" nowrap>
                <span class="timeline_menu"><gsmsg:write key="main.login.history" /></span>
              </td>
              <td class="menu_select_text_no_top" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
              </td>
            </tr>

            <%-- WEBメール --%>
            <logic:equal name="man390Form" property="man390CtrlFlgWml" value="true">
            <tr class="menu_not_select_tr" onClick="buttonPush('webmail');">
              <td class="menu_not_select_icon">
                &nbsp;<img src="../webmail/images/menu_icon_single.gif" alt="<gsmsg:write key="wml.wml010.25" />" />
              </td>
              <td class="menu_not_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="wml.wml010.25" /></span>
              </td>
            </tr>
            </logic:equal>

            <%-- ショートメール --%>
            <logic:equal name="man390Form" property="man390CtrlFlgSml" value="true">
            <tr class="menu_not_select_tr" onClick="buttonPush('smail');">
              <td class="menu_not_select_icon">
              &nbsp;<img src="../smail/images/menu_icon_single.gif" alt="<gsmsg:write key="cmn.shortmail" />" />
              </td>
              <td class="menu_not_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="cmn.shortmail" /></span>
              </td>
            </tr>
            </logic:equal>

            <%-- 回覧板 --%>
            <logic:equal name="man390Form" property="man390CtrlFlgCir" value="true">
            <tr class="menu_not_select_tr" onClick="buttonPush('circular');">
              <td class="menu_not_select_icon">
                &nbsp;<img src="../circular/images/menu_icon_single.gif" alt="<gsmsg:write key="cir.5" />" />
              </td>
              <td class="menu_not_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="cir.5" /></span>
              </td>
            </tr>
            </logic:equal>

            <%-- ファイル管理 --%>
            <logic:equal name="man390Form" property="man390CtrlFlgFil" value="true">
            <tr class="menu_not_select_tr" onClick="buttonPush('file');">
              <td class="menu_not_select_icon">
                &nbsp;<img src="../file/images/menu_icon_single.gif" alt="<gsmsg:write key="cmn.filekanri" />" />
              </td>
              <td class="menu_not_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="cmn.filekanri" /></span>
              </td>
            </tr>
            </logic:equal>

            <%-- 掲示板 --%>
            <logic:equal name="man390Form" property="man390CtrlFlgBbs" value="true">
            <tr class="menu_not_select_tr" onClick="buttonPush('bulletin');">
              <td class="menu_not_select_icon">
              &nbsp;<img src="../bulletin/images/menu_icon_single.gif" alt="<gsmsg:write key="cmn.bulletin" />" />
              </td>
              <td class="menu_not_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="cmn.bulletin" /></span>
              </td>
            </tr>
            </logic:equal>

            <tr>
              <td class="menu_space_area_left"></td>
              <td class="menu_space_area_right">&nbsp;</td>
            </tr>


          </table>
        </td>

        <td valign="top" width="84%">
          <div style="padding-top:5px;padding-right:5px;padding-bottom:0px;padding-left:5px;">

            <table width="100%" style="margin-top:5px;">
              <tr>
                <td valign="top" width="100%">
                  <table style="padding:0px;margin:0px 0px 5px 0px;width:100%;">
                    <tr>

                      <%-- 月単位表示年月指定 --%>
                      <logic:equal name="man390Form" property="man390DateUnit" value="2">
                      <td valign="middle" style="padding:0px;margin:0px;">
                        <span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" /><gsmsg:write key="wml.215" /></span>
                        <html:select name="man390Form" property="man390DateMonthlyFrYear" onchange="changeYearMonthCombo('from');">
                          <html:optionsCollection name="man390Form" property="man390DspYearLabel" value="value" label="label" />
                        </html:select>
                        <html:select name="man390Form" property="man390DateMonthlyFrMonth" onchange="changeYearMonthCombo('from');">
                          <html:optionsCollection name="man390Form" property="man390DspMonthLabel" value="value" label="label" />
                        </html:select>
                        <span class="text_base"><gsmsg:write key="tcd.153" /></span>
                        <html:select name="man390Form" property="man390DateMonthlyToYear" onchange="changeYearMonthCombo('to');">
                          <html:optionsCollection name="man390Form" property="man390DspYearLabel" value="value" label="label" />
                        </html:select>
                        <html:select name="man390Form" property="man390DateMonthlyToMonth" onchange="changeYearMonthCombo('to');">
                          <html:optionsCollection name="man390Form" property="man390DspMonthLabel" value="value" label="label" />
                        </html:select>
                      </td>
                      </logic:equal>

                      <%-- 週単位表示 --%>
                      <logic:equal name="man390Form" property="man390DateUnit" value="1">
                      <td valign="middle" style="padding:0px;margin:0px;">
                        <p id="jquery-ui-datepicker-wrap"><span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" /><gsmsg:write key="wml.215" /></span>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="man390Form" property="man390DateWeeklyFrStr" />" type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from"/>
                          <label for="jquery-ui-datepicker-from"><span class="text_base"><gsmsg:write key="tcd.153" /></span></label>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="man390Form" property="man390DateWeeklyToStr" />" type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to"/>
                        </p>
                      </td>
                      </logic:equal>

                      <%-- 日付範囲指定 --%>
                      <logic:equal name="man390Form" property="man390DateUnit" value="0">
                      <td valign="middle" style="padding:0px;margin:0px;">
                        <p id="jquery-ui-datepicker-wrap"><span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" /><gsmsg:write key="wml.215" /></span>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="man390Form" property="man390DateDailyFrStr" />" type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from"/>
                          <label for="jquery-ui-datepicker-from"><span class="text_base"><gsmsg:write key="tcd.153" /></span></label>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="man390Form" property="man390DateDailyToStr" />" type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to"/>
                        </p>
                      </td>
                      </logic:equal>

                      <%-- 表示件数 --%>
                      <td valign="middle" style="padding:0px 5px;margin:0px;width:145px;">
                        <div  class="category_sel_area">
                          <span style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.number.display" /><gsmsg:write key="wml.215" /></span>
                          <html:select style="width:50px;" name="man390Form" property="man390DspNum" styleClass="select01" onchange="changeDspNumCombo();">
                          <logic:notEmpty name="man390Form" property="man390DspNumLabel">
                          <html:optionsCollection name="man390Form" property="man390DspNumLabel" value="value" label="label" />
                          </logic:notEmpty>
                          </html:select>
                        </div>
                      </td>

                      <%-- 月週日切り替えラジオ--%>
                      <td style="padding:0px 10px 0px 5px; width:170px;">
                      <div class="category_sel_area">
                        <html:radio name="man390Form" property="man390DateUnit" value="2" styleId="radio_month" onclick="buttonPush('dateUnitChange');"/>
                        <label for="radio_month" style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.monthly.3" /></label>
                        <html:radio name="man390Form" property="man390DateUnit" value="1" styleId="radio_week" onclick="buttonPush('dateUnitChange');"/>
                        <label for="radio_week" style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.weekly3" /></label>
                        <html:radio name="man390Form" property="man390DateUnit" value="0" styleId="radio_day" onclick="buttonPush('dateUnitChange');"/>
                        <label for="radio_day" style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.daily" /></label>
                      </div>
                      </td>

                    </tr>
                  </table>
                </td>
              </tr>

              <%-- コンテンツ--%>
              <tr>
                <td style="padding:0px;margin:0px;" width="100%">
                  <table  width="100%" style="padding:0px;margin:0px;">
                    <tr height="290px">

                      <%-- メニュー--%>
<%--                        <td width="180px" valign="top" id="sel_menu_wrapper" class="sel_menu_wrapper">
                        <div style="margin:0px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;">
                          <table width="180px" style="margin-top:0px;margin-right:0px;margin-bottom:3px;margin-left:0px;">
                            <tr>
                              <td class="sel_menu_area" id="man_graph_user" onclick="changeDspItem('man_graph_user');">
                                <div class="sel_menu_title"><span><gsmsg:write key="main.man390.1" /></span></div>
                              </td>
                            </tr>
                            <tr>
                              <td class="sel_menu_area" id="man_graph_num" onclick="changeDspItem('man_graph_num');">
                                <div class="sel_menu_title"><span><gsmsg:write key="main.man390.3" /></span></div>
                               </td>
                            </tr>
                          </table>
                        </div>
                      </td>
 --%>
                      <%-- メニュー格納用縦線 --%>
<!--                       <td width="3px" id="menu_length_area" class="menu_length_border" style="padding:0px;margin:0px;">&nbsp;</td> -->
<!--                       <td style="border-right: 1px dotted #b7b7b7;">&nbsp;</td> -->

                      <td valign="top" style="width:100%;padding:0px;margin:0px;">
                        <table style="padding:0px;margin:0px;" width="99%">

                          <%-- グラフ --%>
                          <tr>
                          <td style="padding-left:5px;padding-bottom:10px;">
                          <div id="manCntGraph" style="height:260px;"></div>
                          </td>
                          </tr>


                          <!-- ページング -->
                          <tr>
                          <td width="100%" align="right" nowrap>
                          <bean:size id="pageCount" name="man390Form" property="man390PageLabel" scope="request" />
                          <logic:greaterThan name="pageCount" value="1">
                            <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="text_i" width="20" border="0" onClick="buttonPush('pageLast');">
                            <logic:notEmpty name="man390Form" property="man390PageLabel" scope="request">
                            <html:select property="man390DspPage1" onchange="changePage(this);" styleClass="text_i">
                            <html:optionsCollection name="man390Form" property="man390PageLabel" value="value" label="label" />
                            </html:select>
                            </logic:notEmpty>
                            <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('pageNext');">
                          </logic:greaterThan>
                          </td>
                          </tr>

                          <%-- 集計一覧 --%>
                          <tr id="anken_detail_area" style="border-left: 1px dotted #b7b7b7;">
                            <td width="100%">


                              <table width="100%">

                                <tr>

                                  <td valign="top">
                                    <table width="100%" class="anken_val_table" id="anken_val_table">

                                      <tr class="anken_val_title" id="anken_val_title">
                                        <logic:equal name="man390Form" property="man390DateUnit" value="0">
                                          <td width="40%"><b><gsmsg:write key="cmn.date3" /></b></td>
                                          <td width="20%"><b><gsmsg:write key="main.man390.3" /></b></td>
                                          <td width="20%"><b><gsmsg:write key="main.man390.1" /></b></td>
                                          <td width="20%"><b><gsmsg:write key="main.man390.2" /></b></td>
                                        </logic:equal>
                                        <logic:notEqual name="man390Form" property="man390DateUnit" value="0">
                                          <td width="40%"><b><gsmsg:write key="cmn.date3" /></b></td>
                                          <td width="30%"><b><gsmsg:write key="main.man390.3" /></b></td>
                                          <td width="30%"><b><gsmsg:write key="main.man390.2" /></b></td>
                                        </logic:notEqual>
                                      </tr>
                                      <logic:notEmpty name="man390Form" property="man390LogCountList">
                                      <logic:iterate name="man390Form" property="man390LogCountList" id="dayData">
                                      <tr class="anken_val">
                                      <td align="center"><bean:write name="dayData" property="dspDate" /></td>
                                      <td align="right"><bean:write name="dayData" property="dspLoginNum" /></td>
                                      <logic:equal name="man390Form" property="man390DateUnit" value="0">
                                        <td align="right"><bean:write name="dayData" property="dspLoginUserNum" /></td>
                                      </logic:equal>
                                      <td align="right"><bean:write name="dayData" property="dspLoginRate" /></td>
                                      </tr>
                                      </logic:iterate>
<%--
                                      <tr class="anken_val">
                                      <td align="center"><b><gsmsg:write key="cmn.average" /></b></td>
                                      <td align="right"><b><bean:write name="man390Form" property="man390AveLoginNum" /></b></td>
                                      <td align="right"><b><bean:write name="man390Form" property="man390AveLoginData" /></b></td>
                                      <td align="right"><b><bean:write name="man390Form" property="man390AveLoginRate" /></b></td>
                                      </tr>
 --%>
                                      <tr class="anken_val">
                                      <td align="center"><b><gsmsg:write key="cmn.sum" /></b></td>
                                      <td align="right"><b><bean:write name="man390Form" property="man390SumLoginNum" /></b></td>
                                      <logic:equal name="man390Form" property="man390DateUnit" value="0">
                                        <td align="right"><b><bean:write name="man390Form" property="man390SumLoginData" /></b></td>
                                      </logic:equal>
                                      <td align="right"></td>
                                      </tr>
                                      </logic:notEmpty>


                                    </table>

                                  </td>

                                  <td width="0">
                                  </td>

                                </tr>

                              </table>
                            </td>
                          </tr>

                          <!-- ページング -->
                          <tr>
                          <td width="100%" align="right" style="padding-top:4px;" nowrap>
                          <bean:size id="pageCount2" name="man390Form" property="man390PageLabel" scope="request" />
                          <logic:greaterThan name="pageCount2" value="1">
                            <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="text_i" width="20" border="0" onClick="buttonPush('pageLast');">
                            <logic:notEmpty name="man390Form" property="man390PageLabel" scope="request">
                            <html:select property="man390DspPage2" onchange="changePage(this);" styleClass="text_i">
                            <html:optionsCollection name="man390Form" property="man390PageLabel" value="value" label="label" />
                            </html:select>
                            </logic:notEmpty>
                            <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('pageNext');">
                          </logic:greaterThan>
                          </td>
                          </tr>
                          <logic:equal name="man390Form" property="man390DateUnit" value="0">
                          <tr><td><span style="font-size:12px;"><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man390.4" /></span></td></tr>
                          <tr><td><span style="font-size:12px;"><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man390.5" /></span></td></tr>
                          </logic:equal>
                          <logic:notEqual name="man390Form" property="man390DateUnit" value="0">
                          <tr><td><span style="font-size:12px;"><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man390.6" /></span></td></tr>
                          <tr><td><span style="font-size:12px;"><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man390.7" /></span></td></tr>
                          </logic:notEqual>
                          </tr>

                        </table>

                      </td>
                    </tr>
                  </table>
                  <img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0" alt="">

                </td>
              </tr>

            </table>
          </div>

        </td>

      </tr>

    </table>

</td>

</tr>

</table>

<span id="tooltip_area"></span>

<div id="searchBtnResultPop" style="height:550px;overflow-y:hidden;display:none;" title="<gsmsg:write key="bbs.bbs041.2" />">
  <p>
    <div id="searchBtnResultArea" style="height:450px;">
    </div>
  </p>
</div>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>