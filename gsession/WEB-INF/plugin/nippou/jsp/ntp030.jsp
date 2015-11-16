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
<title>[GroupSession] <gsmsg:write key="ntp.1" /><gsmsg:write key="cmn.list" />(<gsmsg:write key="cmn.days2" />)</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar2.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.waypoints.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bottom-1.0.js'></script>
<script language="JavaScript" src='../common/js/jquery.tooltip.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../nippou/js/ntp030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/glayer.js"></script>
<script>
  $(function(){
      $('.label_area').inFieldLabels();
  });
</script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onunload="windowClose();calWindowClose();">
<html:form action="/nippou/ntp030">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<input type="hidden" name="dspMod" value="3" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp030SessionSid" />
<html:hidden property="ntp030SelectUsrSid" />
<html:hidden property="ntp030Offset" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp030LabelDate" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp010HistoryAnkenSid" />
<html:hidden property="ntp010HistoryCompSid" />
<html:hidden property="ntp010HistoryCompBaseSid" />
<html:hidden property="ntp010SessionUsrId" />

<%-- セッションユーザ情報 --%>
<bean:define id="susrInfMdl" name="ntp030Form" property="ntp030UsrInfMdl" />
<input type="hidden" name="sUsrBinSid" value="<bean:write name="susrInfMdl" property="binSid" />" />
<input type="hidden" name="sUsrPictKf" value="<bean:write name="susrInfMdl" property="usiPictKf" />" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<div id="pageTopArea"></div>

  <div class="time_line_fix_content" id="time_line_fix" style="width:100%">
    <table width="100%">
        <td width="16%"></td>
        <td width="84%">
          <table width="100%">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="69%">
                <table width="94%">
                 <tr>
                   <td class="td_time_line_fix">
                      <div align="center">
                        <span class="time_line_fix_top" id="scTop" >▲<gsmsg:write key="ntp.19" /></span>&nbsp;
                        <select id="select_fix_date" onchange="selConbChange();">
                        </select>

                      </div>
                   </td>
                 </tr>
                </table>
              </td>
              <td width="27%">&nbsp;</td>
            </tr>
          </table>
        </td>
    </table>
  </div>


<!--　BODY -->
<div id="ntpTooltipsDaily">
<table cellpadding="5" cellspacing="0" width="100%">
<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="ntp.18" /> ]</td>
  <td width="100%" class="header_white_bg">
  <%-- input type="button" value="再読込" class="btn_reload_n1" onClick="buttonPush('reload');" --%>
  <%-- input type="button" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush('anken');" --%>
  <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
  <logic:equal name="ntp030Form" property="adminKbn" value="1">
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

            <logic:equal name="ntp030Form" property="adminKbn" value="1">
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
                <logic:notEmpty name="ntp030Form" property="ankenHistoryList">
                  <table width="100%">
                    <tr>
                      <td align="left" width="100%" class="table_bg_7D91BD text_tl5">
                        <span class="text_tlw"><img style="vertical-align:middle;padding-bottom:3px;" src="../nippou/images/history_adr_icon_mini.gif" /><gsmsg:write key="ntp.11" /><gsmsg:write key="ntp.17" /></span><br>
                      </td>
                    </tr>
                    <logic:iterate id="ankenMdl" name="ntp030Form" property="ankenHistoryList">
                      <tr>
                        <td width="100%" align="left" id="<bean:write name="ankenMdl" property="nanSid" />" class="ankenHistoryArea ankenAreaSel">
                          <span style="font-size:12px;"><bean:write name="ankenMdl" property="nanName" /></span>
                        </td>
                      </tr>
                    </logic:iterate>
                  </table>
                  <br>
                </logic:notEmpty>

                <logic:notEmpty name="ntp030Form" property="companyHistoryList">
                  <table width="100%">
                    <tr>
                      <td align="left" width="100%" class="table_bg_7D91BD text_tl5">
                        <span class="text_tlw"><img style="vertical-align:middle;padding-bottom:3px;" src="../nippou/images/history_cmp_icon_mini.gif" /><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /><gsmsg:write key="ntp.17" /></span><br>
                      </td>
                    </tr>
                    <logic:iterate id="companyMdl" name="ntp030Form" property="companyHistoryList">
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
        <%--div id="nippouArea" class="areablock" style="padding-left:15px;vertical-align:top;"--%>
        <table width="100%">
        <tr>
        <td width="100%" style="padding-left:15px;padding-right:0px;">

          <table width="100%">


  <tr>
  <td colspan="" class="td_type0">

    <table width="100%" class="tl0" border="0">
    <tr>
    <td width="20%" align="left">
    </td>
    <td width="40%" align="center" nowrap>
    </td>
    <td width="39%" align="center" style="padding-top:5px;padding-left:3px;" nowrap>
      <input type="button" value="<gsmsg:write key="ntp.18" />" class="btn_time_line" onClick="buttonPush('reload');">
      <input type="button" value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onClick="buttonPush('week');">
      <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="moveMonthNippou('month', '', '');">
      <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_schedule_search" onClick="moveListNippou('list');">
    </td>
    <td width="1%" align="center" style="padding-top:5px;" nowrap></td>


    </tr>
    </table>



  </td>
  </tr>



            <tr>
             <td width="75%" align="center" style="padding-top:5px;padding-bottom:5px;padding-left:5px;padding-right:0px;vertical-align:top;">


                        <table width="95%">
                          <tr><td style="padding-top:10px"></td></tr>
                          <tr>

                          <td align="left">
                            <span class="text_tlw_black2"><gsmsg:write key="cmn.sort.order" />&nbsp;:</span>

                            <html:select property="ntp030Sort" onchange="changeGroupCombo();">
                              <logic:notEmpty name="ntp030Form" property="ntp030SortLabel" scope="request">
                                <logic:iterate id="labelBean" name="ntp030Form" property="ntp030SortLabel" scope="request">
                                <bean:define id="labelValue" name="labelBean" property="value" type="java.lang.String" />
                                 <html:option value="<%= labelValue %>"><bean:write name="labelBean" property="label" /></html:option>
                                </logic:iterate>
                              </logic:notEmpty>
                            </html:select>
                          </td>

                          <td align="right" style="padding-right:40px">
                            <input value="日報<gsmsg:write key="cmn.new.registration" />" onClick="return addNippou('add', <bean:write name="ntp030Form" property="ntp010SelectDate" />, <bean:write name="ntp030Form" property="ntp030SessionSid" />, 0);" class="btn_nippou_add" type="button">
                          </td>

                          </tr>
                        </table>


                        <table width="95%">

                        <logic:notEmpty name="ntp030Form" property="ntp030DataModelList">

                          <% String beforeLabel = ""; %>
                          <% String positionLabel = ""; %>
                          <% String beforePositionLabel = ""; %>
                          <logic:iterate id="dataMdl" name="ntp030Form" property="ntp030DataModelList"  indexId="idx">

                            <bean:define id="labelDate" name="dataMdl" property="ntp030NtpDate" type="java.lang.String" />
                            <% positionLabel = labelDate.substring(0,8); %>

                                  <tr><td width="100%">


                                        <table width="100%" style="border-collapse:collapse;"><tr>
                                        <td colspan="6">
                                        <table width="100%" style="border-collapse:collapse;"><tbody><tr><td>
                                        </td></tr><tr>
                                        <td colspan="6" height="25px"></td>
                                        </tr>

                                         <tr>

                                          <td class="td_wt5 title_area_1" valign="top" nowrap="nowrap" width="50px">
                                              <div style="padding-left:5px;padding:right:0px;">
                                                <bean:define id="ntpUsrInfMdl" name="dataMdl" property="ntp030UsrInfMdl" />
                                                <logic:equal name="ntpUsrInfMdl" property="binSid" value="0">
                                                  <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="45px" />
                                                </logic:equal>

                                                <logic:notEqual name="ntpUsrInfMdl" property="binSid" value="0">
                                                  <logic:equal name="ntpUsrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                    <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                                                  </logic:equal>
                                                  <logic:notEqual name="ntpUsrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                    <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="ntpUsrInfMdl" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="45px" />
                                                  </logic:notEqual>
                                                </logic:notEqual>
                                              </div>
                                          </td>
                                          <td class="td_wt5 title_area_2" width="100%" colspan="3" align="left">

                                            <div class="titleArea2 title_area">
                                               <span style="font-color:#eeeeee;font-size:12px;line-height:1em;"><bean:write name="dataMdl" property="ntp030NtpDate" /></span>&nbsp;
                                               <span style="font-color:#eeeeee;font-size:12px;line-height:1em;">





                                               <bean:write name="dataMdl" property="ntp030DspFrHour" /><gsmsg:write key="cmn.hour.input" />
                                               <bean:write name="dataMdl" property="ntp030DspFrMinute"/><gsmsg:write key="cmn.minute.input" />
                                               ～


                                               <bean:write name="dataMdl" property="ntp030DspToHour"/>
                                               <gsmsg:write key="cmn.hour.input" />
                                               <bean:write name="dataMdl" property="ntp030DspToMinute"/>
                                               <gsmsg:write key="cmn.minute.input" />


                                               </span><br>


                                               <span style="font-color:#eeeeee;font-size:13px;line-height:1em;"><bean:write name="ntpUsrInfMdl" property="usiSei" />&nbsp;&nbsp;<bean:write name="ntpUsrInfMdl" property="usiMei" /></span>
                                               <div style="padding-top:10px;padding-bottom:10px;">
                                               <span class="timeline_title" style="cursor:pointer;" onClick="return editNippou('edit', <bean:write name="dataMdl" property="ntp030NtpSid" />, <bean:write name="dataMdl" property="ntp030UsrSid" />);"><bean:write name="dataMdl" property="title" filter="false"/></span>
                                               </div>
                                              <logic:notEmpty name="dataMdl" property="ankenName">

                                              <a id="<bean:write name="dataMdl" property="ankenSid" />" class="sc_link anken_click">
                                                <logic:notEmpty name="dataMdl" property="ankenSid">
                                                  <logic:notEqual name="dataMdl" property="ankenSid" value="0">
                                                    <span class="text_anken">
                                                      <bean:write name="dataMdl" property="ankenName" />
                                                    </span>
                                                  </logic:notEqual>
                                                </logic:notEmpty>
                                              </a>
                                              </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                              </logic:notEmpty>


                                            <logic:notEmpty name="dataMdl" property="companyName">

                                              <span>
                                                <a id="<bean:write name="dataMdl" property="companySid" />" class="sc_link comp_click comp_name_link_<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                                  <span class="comp_name_<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                                    <span class="text_company"><bean:write name="dataMdl" property="companyName" />
                                                      <logic:notEmpty name="dataMdl" property="companySid">
                                                        <bean:write name="dataMdl" property="companyBaseName" />
                                                      </logic:notEmpty>
                                                    </span>
                                                  </span>
                                                </a>
                                              </span>
                                            </logic:notEmpty>

                                            </div>

                                          </td>
                                          </tr>

                                      <tr>
                                        <td class="td_wt4" colspan="4" style="padding-top:5px;padding-left:60px;background-color:#fcfcfc;" align="left">
                                          <div class="naiyouArea<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                            <span class="dsp_naiyou_<bean:write name="dataMdl" property="ntp030NtpSid" />"><bean:write name="dataMdl" property="ntp030DspValueStr" filter="false"/></span>
                                          </div>

                                         <div class="tempFileArea<bean:write name="dataMdl" property="ankenSid" /> dsp_tmp_file_area_<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                            <logic:notEmpty name="dataMdl" property="ntp030FileList">
                                              <logic:iterate id="tempMdl" name="dataMdl" property="ntp030FileList">
                                                <img src="../nippou/images/icon_attach.gif" alt="<gsmsg:write key="cmn.attached" />" /><a href="javascript:void(0);" onclick="return fileLinkClick(<bean:write name="dataMdl" property="ntp030NtpSid" />,<bean:write name="tempMdl" property="binSid"/>);"><span class="text_link_min"><bean:write name="tempMdl" property="binFileName"/><bean:write name="tempMdl" property="binFileSizeDsp" /></span></a><br>
                                              </logic:iterate>

                                              <div><img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0"></div>

                                            </logic:notEmpty>
                                          </div>

                                          <div><img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0"></div>

                                        </td>
                                      </tr>

                                      <%--
                                      <tr>
                                      <td class="table_bg_A5B4E1" nowrap="nowrap"><span class="text_bb1"><gsmsg:write key="ntp.3" /></span><span class="text_r2">※</span></td>
                                      <td colspan="3" class="td_wt" align="left">

                                        <div class="ktBunruiArea2">
                                         <span class="dsp_ktbunrui_2"><gsmsg:write key="ntp.3" />3</span>&nbsp;
                                         <span class="dsp_kthouhou_2"><gsmsg:write key="ntp.121" />5</span>
                                        </div>

                                       </td>
                                      </tr>


                                      <tr>
                                      <td class="table_bg_A5B4E1" nowrap="nowrap"><span class="text_bb1"><gsmsg:write key="ntp.32" /></span><span class="text_r2">※</span></td>
                                      <td class="td_wt" colspan="3" align="left">

                                        <div class="mikomidoArea2">
                                          <span class="text_base">
                                            <span class="dsp_mikomido_2">10</span>％
                                          </span>
                                        </div>

                                      </td>
                                      </tr>

                                      --%>


                                      <tr class="commentArea2">
                                      <td class="td_wt3" colspan="4" style="padding-left:60px;padding-bottom:5px;background-color:#fcfcfc;">


                                        <div style="float:left;"><span id="goodBtnArea_<bean:write name="dataMdl" property="ntp030NtpSid" />"><logic:equal name="dataMdl" property="ntp030GoodFlg" value="0"><input id="<bean:write name="dataMdl" property="ntp030NtpSid" />" style="border:0px;color:#000066;font-size:10px;font-weight:bold;width:60px;height:17px;" class="ntp_good_btn goodLink" value="<gsmsg:write key="ntp.22" />!" type="button"></logic:equal><logic:notEqual name="dataMdl" property="ntp030GoodFlg" value="0"><span class="text_already_good">いいね!しています</span></logic:notEqual></span></div>


                                        <div style="float:left;padding-left:4px;padding-top:2px;">
                                          <table>
                                            <tr>
                                              <td align="center" class="text_good" id="<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                                <span id="goodCntArea_<bean:write name="dataMdl" property="ntp030NtpSid" />"><bean:write name="dataMdl" property="ntp030GoodCnt" /></span>
                                              </td>
                                            </tr>
                                          </table>
                                        </div>



                                      </td>
                                      </tr>

                                      <logic:notEmpty name="dataMdl" property="ntp030CommentList">

                                          <tr class="ntp030DspComment_tr_<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                            <td id="ntp030DspComment_<bean:write name="dataMdl" property="ntp030NtpSid" />" class="td_wt4" colspan="4">

                                            <span class="commentDspArea<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                            <logic:iterate id="npcMdl" name="dataMdl" property="ntp030CommentList">
                                              <bean:define id="usrInfMdl" name="npcMdl" property="ntp030UsrInfMdl"/>
                                              <bean:define id="ntpCmtMdl" name="npcMdl" property="ntp030CommentMdl"/>

                                              <table class="commentDspAreaTable_<bean:write name="dataMdl" property="ntp030NtpSid" />_<bean:write name="ntpCmtMdl" property="npcSid" />">
                                                <tr>
                                                  <td valign="top" style="padding-left:60px;padding-top:12px;padding-bottom:20px;">

                                                    <logic:equal name="usrInfMdl" property="binSid" value="0">
                                                      <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="30px" />
                                                    </logic:equal>

                                                    <logic:notEqual name="usrInfMdl" property="binSid" value="0">
                                                      <logic:equal name="usrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                        <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                                                      </logic:equal>
                                                      <logic:notEqual name="usrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                        <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="usrInfMdl" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="30px" />
                                                      </logic:notEqual>
                                                    </logic:notEqual>
                                                  </td>
                                                  <td valign="top" style="padding-top:5px;padding-left:10px;" id="commentDspAreaTable_<bean:write name="dataMdl" property="ntp030NtpSid" />_<bean:write name="ntpCmtMdl" property="npcSid" />">
                                                    <span style="font-size:12px;color:#333333;"><b><bean:write name="usrInfMdl" property="usiSei"/>&nbsp;<bean:write name="usrInfMdl" property="usiMei"/></b></span>
                                                    &nbsp;<span style="font-size:12px;color:#333333;"><bean:write name="npcMdl" property="ntp030CommentDate" filter="false"/></span>
                                                    <logic:equal name="npcMdl" property="ntp030CommentDelFlg" value="1">
                                                    <span class="commentDel" id="<bean:write name="ntpCmtMdl" property="npcSid" />"><img src="../nippou/images/delete_icon2.gif" alt="<gsmsg:write key="cmn.delete" />" /></span></logic:equal><br><span style="font-size:13px;color:#333333;"><bean:write name="ntpCmtMdl" property="npcComment" filter="false"/></span>
                                                  </td>
                                                </tr>
                                              </table>



                                            </logic:iterate>
                                            </span>

                                          </td>
                                        </tr>


                                      </logic:notEmpty>


                                     <logic:empty name="dataMdl" property="ntp030CommentList">
                                          <tr class="ntp030DspComment_tr_<bean:write name="dataMdl" property="ntp030NtpSid" />">
                                            <td id="ntp030DspComment_<bean:write name="dataMdl" property="ntp030NtpSid" />" class="td_wt4" colspan="4">
                                            </td>
                                          </tr>
                                     </logic:empty>





                                      <tr id="commentAddArea_<bean:write name="dataMdl" property="ntp030NtpSid" />" class="commentArea2">
                                      <td class="td_wt8" style="padding-top:5px;" colspan="4">


                                        <table>
                                          <tbody><tr>
                                            <td style="padding-left:60px;">

                                              <logic:equal name="susrInfMdl" property="binSid" value="0">
                                                <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="30px" />
                                              </logic:equal>

                                              <logic:notEqual name="susrInfMdl" property="binSid" value="0">
                                                <logic:equal name="susrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                  <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                                                </logic:equal>
                                                <logic:notEqual name="susrInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                  <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="susrInfMdl" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="30px" />
                                                </logic:notEqual>
                                              </logic:notEqual>

                                            </td>
                                            <td style="padding-left:10px;padding-top:10px;" valign="middle">
                                              <div class="textfield">
                                                <label class="label_area" style="opacity: 1; font-size: 12px; color: rgb(163, 163, 163);" for="field_id<bean:write name="dataMdl" property="ntp030NtpSid" />"><gsmsg:write key="ntp.36" /></label>
                                                <textarea name="ntp030Comment_<bean:write name="dataMdl" property="ntp030NtpSid" />" cols="45" rows="2" class="commentTextArea" id="field_id<bean:write name="dataMdl" property="ntp030NtpSid" />"></textarea>
                                              </div>
                                            </td>
                                            <td id="2" valign="middle">
                                              &nbsp;<input id="<bean:write name="dataMdl" property="ntp030NtpSid" />" class="btn_base_toukou" name="commentBtn" value="投稿" type="button">
                                            </td>
                                          </tr>
                                        </tbody>
                                      </table>

                                      </td>
                                      </tr>

                                      </table>

                                       </td>
                                      </tr>
                                    </table>

                          </td>
                          <td valign="top" width="0%" class="timeline" nowrap >
                            <% if (!labelDate.equals(beforeLabel)) { %>
                              <span class="time_line_date_label dataDateArea" id="<%= labelDate %>"><bean:write name="dataMdl" property="ntp030LabelDate" /></span>
                            <% } %>
                            <% if (!positionLabel.equals(beforePositionLabel)) { %>
                              <span id="position_<%= positionLabel %>"></span>
                            <% } %>
                          </td>
                          </tr>

                          <bean:define id="beforeLabelDate" name="dataMdl" property="ntp030NtpDate" type="java.lang.String" />
                          <% beforeLabel= beforeLabelDate; %>
                          <% beforePositionLabel = beforeLabelDate.substring(0,8); %>
                          </logic:iterate>
                       </logic:notEmpty>


                    </table>

                    <table width="95%" id="pageBottomArea"></table>

                    <br>

                    <div id="pageBottom"></div>

             </td>



             <td width="20%" valign="top" align="center" style="padding-right:10px">

                                      <br>

                                      <table>

                                        <!-- グループコンボ -->
                                        <tr>
                                        <td class="td_type3" colspan="2" align="left" nowrap>
                                          <span class="text_base">表示グループ</span><br>
                                          <html:select property="ntp010DspGpSid" styleClass="select02" onchange="change030GroupCombo();">

                                            <logic:notEmpty name="ntp030Form" property="ntp010GpLabelList" scope="request">
                                            <logic:iterate id="gpBean" name="ntp030Form" property="ntp010GpLabelList" scope="request">

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
                                          </html:select><br>
                                          <logic:greaterThan name="ntp030Form" property="ntp030SelectUsrSid" value="0">
                                          <div style="padding:3px;"><span id="selGroupBtn" class="time_line_group_btn"><gsmsg:write key="ntp.2" /></span></div>
                                          </logic:greaterThan>
                                        </td>
                                        </tr>



                                        <tr>
                                        <td class="td_type1" colspan="2">
                                          <% String bgClassName = "user_select_area"; %>
                                          <logic:notEmpty name="ntp030Form" property="ntp030UsrLabelList" scope="request">
                                          <logic:iterate id="usrBean" name="ntp030Form" property="ntp030UsrLabelList" scope="request">

                                           <bean:define id="beanUsrSid" name="usrBean" property="value" type="java.lang.String" />
                                           <logic:equal name="ntp030Form" property="ntp030SelectUsrSid" value="<%= beanUsrSid %>">
                                             <% bgClassName = "user_select_area_hover"; %>
                                           </logic:equal>
                                           <logic:notEqual name="ntp030Form" property="ntp030SelectUsrSid" value="<%= beanUsrSid %>">
                                             <% bgClassName = "user_select_area"; %>
                                           </logic:notEqual>

                                            <!-- ユーザ情報 -->
                                            <table class="tl0" width="100%" style="border-collapse:collapse;">
                                              <tbody>
                                                <tr height="70px">
                                                  <td rowspan="2" width="55px" height="70px" class="<%= bgClassName %> usr_inf_area_left">
                                                    <!-- ユーザ画像 -->
                                                    <a href="javascript:void(0);" onclick="return openUserInfoWindow(<bean:write name="usrBean" property="value" />);">

                                                      <bean:define id="usrLvInfMdl" name="usrBean" property="usrInfMdl" />
                                                      <logic:equal name="usrLvInfMdl" property="binSid" value="0">
                                                        <img  class="comment_Img" src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" />
                                                      </logic:equal>

                                                      <logic:notEqual name="usrLvInfMdl" property="binSid" value="0">
                                                        <logic:equal name="usrLvInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                          <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                                                        </logic:equal>
                                                        <logic:notEqual name="usrLvInfMdl" property="usiPictKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                                                          <img class="comment_Img" src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="usrLvInfMdl" property="binSid" />" alt="<gsmsg:write key="cmn.photo" />" width="50px" />
                                                        </logic:notEqual>
                                                      </logic:notEqual>

                                                    </a>
                                                  </td>
                                                  <td id="<bean:write name="usrBean" property="value" />" class="ntp030usrLink left_space <%= bgClassName %> usr_inf_area_right" colspan="2" align="left" nowrap="nowrap" width="100%">
                                                    <!-- ユーザ名 -->
                                                    <span class="text_link2">
                                                      <a href="#">
                                                        <bean:write name="usrBean" property="label" />
                                                      </a>
                                                    </span>
                                                  </td>
                                                </tr>
                                              </tbody>
                                            </table>

                                          </logic:iterate>
                                          </logic:notEmpty>


                                        </td>
                                        </tr>
                                      </table>

           </td>
          </tr>
        </table>




        </td>
        </tr>
        </table>
        <%-- /div --%>




        <%-- 案件 --%>
        <div id="ankenArea" class="areanone">


        </div>


        <div id="bunsekiArea" class="areanone"><gsmsg:write key="ntp.13" />

        </div>


        <div id="menteArea" class="areanone"><gsmsg:write key="ntp.14" />


        </div>


        </td>

      </tr>

    </table>

</td>

</tr>

</table>





</td>
</tr>
</table>
</div>


<div id="dialogCommentDel" title="<gsmsg:write key="cmn.delete" /><gsmsg:write key="cmn.check" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.21" /></b>
    </p>
</div>

<div id="commentError" title="<gsmsg:write key="ntp.42" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.23" /></b><br><br>
    </p>
</div>

<div id="commentLengthError" title="<gsmsg:write key="ntp.42" />" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span><b><gsmsg:write key="ntp.24" /></b><br><br>
    </p>
</div>

<div id="goodError" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.25" /></b><br><br>
    </p>
</div>

<div id="goodDialog" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.26" /></b><br><br>
    </p>
</div>

<div id="goodDialogComp" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.27" /></b><br><br>
    </p>
</div>

<div id="goodZero" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="ntp.28" /></b><br><br>
    </p>
</div>

</html:form>
<iframe type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></iframe>

<script type="text/javascript">
$(function() {
  $('#ntpTooltipsDaily * a').tooltip();
});
</script>


<div id="goodUsrInfArea" class="goodAddUsrArea">
  <table style="width:100%;">
    <tr>
      <td style="width:100%;" align="right">
        <input value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" id="goodAddUsrClose" type="button">
      </td>
    </tr>
    <tr>
      <td style="width:100%;">
        <div id="goodUsrInfArea2" style="height:270px;overflow-y: scroll;">
        </div>
      </td>
    </tr>
  </table>
</div>


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>