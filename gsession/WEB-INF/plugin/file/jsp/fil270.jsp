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
<title>[Group Session] <gsmsg:write key="cmn.shortmail" /></title>
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
<script language="JavaScript" src='../file/js/fil270.js?<%= GSConst.VERSION_PARAM %>'></script>
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

<html:form action="/file/fil270">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="fil270DateWeeklyFrStr" />
<html:hidden property="fil270DateWeeklyToStr" />
<html:hidden property="fil270DateDailyFrStr" />
<html:hidden property="fil270DateDailyToStr" />
<html:hidden property="fil270NowPage"/>
<html:hidden property="fil270GraphItem"/>
<html:hidden property="fil270GsAdminFlg"/>
<html:hidden property="fil270CtrlFlgWml"/>
<html:hidden property="fil270CtrlFlgSml"/>
<html:hidden property="fil270CtrlFlgCir"/>
<html:hidden property="fil270CtrlFlgBbs"/>
<html:hidden property="logCountBack"/>

<html:hidden property="jsonDateData"/>
<html:hidden property="jsonDownloadData"/>
<html:hidden property="jsonUploadData"/>

<logic:notEqual name="fil270Form" property="fil270DateUnit" value="2">
  <html:hidden property="fil270DateMonthlyFrYear"/>
  <html:hidden property="fil270DateMonthlyFrMonth"/>
  <html:hidden property="fil270DateMonthlyToYear"/>
  <html:hidden property="fil270DateMonthlyToMonth"/>
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
            <logic:equal name="fil270Form" property="fil270GsAdminFlg" value="true">
            <tr class="menu_not_select_tr" onClick="buttonPush('statsMain');">
              <td class="menu_not_select_icon">
                &nbsp;<img src="../main/images/menu_icon_login_history.gif" alt="<gsmsg:write key="main.login.history" />" />
              </td>
              <td class="menu_not_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="main.login.history" /></span>
              </td>
            </tr>
            </logic:equal>

            <%-- WEBメール --%>
            <logic:equal name="fil270Form" property="fil270CtrlFlgWml" value="true">
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
            <logic:equal name="fil270Form" property="fil270CtrlFlgSml" value="true">
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
            <logic:equal name="fil270Form" property="fil270CtrlFlgCir" value="true">
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
            <tr class="menu_select_tr">
              <td class="menu_select_icon">
                &nbsp;<img src="../file/images/menu_icon_single.gif" alt="<gsmsg:write key="cmn.filekanri" />" />
              </td>
              <td class="menu_select_text" nowrap>
                <span class="timeline_menu"><gsmsg:write key="cmn.filekanri" /></span>
              </td>
              <td class="menu_select_text" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
              </td>
            </tr>

            <%-- 掲示板 --%>
            <logic:equal name="fil270Form" property="fil270CtrlFlgBbs" value="true">
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

              <%-- 日付指定 --%>
              <tr>
                <td valign="top" width="100%">
                  <table style="padding:0px;margin:0px 0px 5px 0px;width:100%;">
                    <tr>

                      <%-- 月単位表示年月指定 --%>
                      <logic:equal name="fil270Form" property="fil270DateUnit" value="2">
                      <td valign="middle" style="padding:0px;margin:0px;">
                        <p><span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" /><gsmsg:write key="wml.215" /></span>
                          <html:select name="fil270Form" property="fil270DateMonthlyFrYear" onchange="changeYearMonthCombo('from');">
                            <html:optionsCollection name="fil270Form" property="fil270DspYearLabel" value="value" label="label" />
                          </html:select>
                          <html:select name="fil270Form" property="fil270DateMonthlyFrMonth" onchange="changeYearMonthCombo('from');">
                            <html:optionsCollection name="fil270Form" property="fil270DspMonthLabel" value="value" label="label" />
                          </html:select>
                          <span class="text_base"><gsmsg:write key="tcd.153" /></span>
                          <html:select name="fil270Form" property="fil270DateMonthlyToYear" onchange="changeYearMonthCombo('to');">
                            <html:optionsCollection name="fil270Form" property="fil270DspYearLabel" value="value" label="label" />
                          </html:select>
                          <html:select name="fil270Form" property="fil270DateMonthlyToMonth" onchange="changeYearMonthCombo('to');">
                            <html:optionsCollection name="fil270Form" property="fil270DspMonthLabel" value="value" label="label" />
                          </html:select>
                        </p>
                      </td>
                      </logic:equal>

                      <%-- 週単位表示 --%>
                      <logic:equal name="fil270Form" property="fil270DateUnit" value="1">
                      <td valign="middle" style="padding:0px;margin:0px;">
                        <p id="jquery-ui-datepicker-wrap"><span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" /><gsmsg:write key="wml.215" /></span>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="fil270Form" property="fil270DateWeeklyFrStr" />" type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from"/>
                          <label for="jquery-ui-datepicker-from"><span class="text_base"><gsmsg:write key="tcd.153" /></span></label>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="fil270Form" property="fil270DateWeeklyToStr" />" type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to"/>
                        </p>
                      </td>
                      </logic:equal>

                      <%-- 日付範囲指定 --%>
                      <logic:equal name="fil270Form" property="fil270DateUnit" value="0">
                      <td valign="middle" style="padding:0px;margin:0px;">
                        <p id="jquery-ui-datepicker-wrap"><span style="font-size:12px;font-weight:bold;padding-left:12px;" class="text_base"><gsmsg:write key="ntp.132" /><gsmsg:write key="wml.215" /></span>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="fil270Form" property="fil270DateDailyFrStr" />" type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from"/>
                          <label for="jquery-ui-datepicker-from"><span class="text_base"><gsmsg:write key="tcd.153" /></span></label>
                          <input readonly="readonly" style="cursor:pointer; width:125px;" value="<bean:write name="fil270Form" property="fil270DateDailyToStr" />" type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to"/>
                        </p>
                      </td>
                      </logic:equal>

                      <%-- 表示件数 --%>
                      <td valign="middle" style="padding:0px 5px;margin:0px;width:145px;">
                        <div  class="category_sel_area">
                        <span style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.number.display" /><gsmsg:write key="wml.215" /></span>

                        <html:select style="width:50px;" name="fil270Form" property="fil270DspNum" styleClass="select01" onchange="changeDspNumCombo();">
                        <logic:notEmpty name="fil270Form" property="fil270DspNumLabel">
                        <html:optionsCollection name="fil270Form" property="fil270DspNumLabel" value="value" label="label" />
                        </logic:notEmpty>
                        </html:select>

                        </div>
                      </td>

                      <%-- 月週日切り替えラジオ--%>
                      <td style="padding:0px 10px 0px 5px;width:170px;">
                      <div class="category_sel_area">
                        <html:radio name="fil270Form" property="fil270DateUnit" value="2" styleId="radio_month" onclick="buttonPush('dateUnitChange');"/>
                        <label for="radio_month" style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.monthly.3" /></label>
                        <html:radio name="fil270Form" property="fil270DateUnit" value="1" styleId="radio_week" onclick="buttonPush('dateUnitChange');"/>
                        <label for="radio_week" style="font-size:12px;font-weight:bold;" class="text_base"><gsmsg:write key="cmn.weekly3" /></label>
                        <html:radio name="fil270Form" property="fil270DateUnit" value="0" styleId="radio_day" onclick="buttonPush('dateUnitChange');"/>
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
                    <tr height="300px">
                      <%-- メニュー--%>
                      <td width="180px" valign="top" id="sel_menu_wrapper" class="sel_menu_wrapper" style="border-top:1px solid #c5c5c5;border-left:1px solid #c5c5c5;border-bottom:1px solid #c5c5c5;">
                        <div style="margin:0px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;">
                          <table width="180px" style="margin-top:0px;margin-right:0px;margin-bottom:3px;margin-left:0px;">
                           <tr>
                              <td class="sel_menu_area" id="fil_graph_download" onclick="changeDspItem('fil_graph_download');">
                                <div class="sel_menu_title"><span><gsmsg:write key="fil.fil270.1" /></span></div>
                              </td>
                            </tr>
                            <tr>
                              <td class="sel_menu_area" id="fil_graph_upload" onclick="changeDspItem('fil_graph_upload');">
                                <div class="sel_menu_title"><span><gsmsg:write key="fil.fil270.2" /></span></div>
                              </td>
                            </tr>
                          </table>
                        </div>
                      </td>

                      <%-- メニュー格納用縦線 --%>
                      <td width="3px" id="menu_length_area" class="menu_length_border" style="padding:0px;margin:0px;border-top-width:0px;border-left:1px solid #c5c5c5;border-right: 1px dotted #b7b7b7;">&nbsp;</td>


                      <%-- グラフ --%>
                      <td valign="top" style="width:100%;padding:0px;margin:0px;">

                        <table style="padding:0px;margin:0px;" width="99%">

                          <%-- グラフ --%>
                          <tr>
                          <td style="padding-left:5px;padding-bottom:10px;">
                          <div id="filCntGraph" style="height:260px;"></div>
                          </td>
                          </tr>

                          <!-- ページング -->
                          <tr>
                          <td width="100%" align="right" nowrap>
                          <bean:size id="pageCount" name="fil270Form" property="fil270PageLabel" scope="request" />
                          <logic:greaterThan name="pageCount" value="1">
                            <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="text_i" width="20" border="0" onClick="buttonPush('pageLast');">
                            <logic:notEmpty name="fil270Form" property="fil270PageLabel" scope="request">
                            <html:select property="fil270DspPage1" onchange="changePage(this);" styleClass="text_i">
                            <html:optionsCollection name="fil270Form" property="fil270PageLabel" value="value" label="label" />
                            </html:select>
                            </logic:notEmpty>
                            <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('pageNext');">
                          </logic:greaterThan>
                          </td>
                          </tr>

                          <%-- 集計一覧 --%>
                          <tr id="anken_detail_area">
                            <td width="100%">


                              <table width="100%">

                                <tr>

                                  <td valign="top">
                                    <table width="100%" class="anken_val_table" id="anken_val_table">

                                      <tr class="anken_val_title" id="anken_val_title">
                                        <td width="40%"><b><gsmsg:write key="cmn.date3" /></b></td>
                                        <td width="30%"><b><gsmsg:write key="fil.fil270.1" /></b></td>
                                        <td width="30%"><b><gsmsg:write key="fil.fil270.2" /></b></td>
                                      </tr>
                                      <logic:notEmpty name="fil270Form" property="fil270LogCountList">
                                      <logic:iterate name="fil270Form" property="fil270LogCountList" id="dayData">
                                      <tr class="anken_val">
                                      <td align="center"><bean:write name="dayData" property="dspDate" /></td>
                                      <td align="right"><bean:write name="dayData" property="strDownloadNum" /></td>
                                      <td align="right"><bean:write name="dayData" property="strUploadNum" /></td>
                                      </tr>
                                      </logic:iterate>

                                      <tr class="anken_val">
                                      <td align="center"><b><gsmsg:write key="cmn.average" /></b></td>
                                      <td align="right"><b><bean:write name="fil270Form" property="fil270AveDownloadNum" /></b></td>
                                      <td align="right"><b><bean:write name="fil270Form" property="fil270AveUploadNum" /></b></td>
                                      </tr>
                                      <tr class="anken_val">
                                      <td align="center"><b><gsmsg:write key="cmn.sum" /></b></td>
                                      <td align="right"><b><bean:write name="fil270Form" property="fil270SumDownloadNum" /></b></td>
                                      <td align="right"><b><bean:write name="fil270Form" property="fil270SumUploadNum" /></b></td>
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
                          <bean:size id="pageCount2" name="fil270Form" property="fil270PageLabel" scope="request" />
                          <logic:greaterThan name="pageCount2" value="1">
                            <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="text_i" width="20" border="0" onClick="buttonPush('pageLast');">
                            <logic:notEmpty name="fil270Form" property="fil270PageLabel" scope="request">
                            <html:select property="fil270DspPage2" onchange="changePage(this);" styleClass="text_i">
                              <html:optionsCollection name="fil270Form" property="fil270PageLabel" value="value" label="label" />
                            </html:select>
                            </logic:notEmpty>
                            <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('pageNext');">
                          </logic:greaterThan>
                          </td>
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

<!-- <input type="hidden" name="ntp220pageNum" value="1" /> -->

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>