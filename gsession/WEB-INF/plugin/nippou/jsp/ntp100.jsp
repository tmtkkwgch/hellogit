<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%-- キーワード検索区分 --%>
<%
  String keyWordAnd    = String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.KEY_WORD_KBN_AND);
  String keyWordOr     = String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.KEY_WORD_KBN_OR);
%>
<%-- 検索対象 --%>
<%
  String targetTitle   = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SEARCH_TARGET_TITLE);
  String targetHonbun  = String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SEARCH_TARGET_HONBUN);
%>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> [<gsmsg:write key="cmn.search" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/webSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="setToUser();" onunload="windowClose();">
<html:form action="/nippou/ntp100">

<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/nippou/jsp/ntp060_hiddenParams.jsp" %>

<input type="hidden" name="listMod" value="5">
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />

<html:hidden property="ntp100SvAnkenSid" />
<html:hidden property="ntp100SvCompanySid" />
<html:hidden property="ntp100SvCompanyBaseSid" />

<html:hidden property="ntp100SvKtbunrui" />
<html:hidden property="ntp100SvKthouhou" />


<html:hidden property="ntp100SvSltGroup" />
<html:hidden property="ntp100SvSltUser" />
<html:hidden property="ntp100SvSltYearFr" />
<html:hidden property="ntp100SvSltMonthFr" />
<html:hidden property="ntp100SvSltDayFr" />
<html:hidden property="ntp100SvSltYearTo" />
<html:hidden property="ntp100SvSltMonthTo" />
<html:hidden property="ntp100SvSltDayTo" />
<html:hidden property="ntp100SvKeyWordkbn" />
<html:hidden property="ntp100SvKeyValue" />

<html:hidden property="ntp100SvOrderKey1" />
<html:hidden property="ntp100SvSortKey1" />
<html:hidden property="ntp100SvOrderKey2" />
<html:hidden property="ntp100SvSortKey2" />

<html:hidden property="ntp100SelectNtpAreaSid" />

<logic:notEmpty name="ntp100Form" property="ntp100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="ntp100Form" property="ntp100SvSearchTarget" scope="request">
    <input type="hidden" name="ntp100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ntp100Form" property="ntp100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="ntp100Form" property="ntp100SvBgcolor" scope="request">
    <input type="hidden" name="ntp100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ntp100Form" property="ntp100SvMikomido" scope="request">
  <logic:iterate id="svMikomido" name="ntp100Form" property="ntp100SvMikomido" scope="request">
    <input type="hidden" name="ntp100SvMikomido" value="<bean:write name="svMikomido"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="ntp100PageNum" />



<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.search" /> ]</td>
  <td width="100%" class="header_white_bg">

  <logic:equal name="ntp100Form" property="adminKbn" value="1">
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

            <logic:equal name="ntp100Form" property="adminKbn" value="1">
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


        <%-- div id="nippouArea" class="areablock" style="vertical-align:top;padding-left:10px;padding-right:10px;"--%>
        <table width="100%">
        <tr>
        <td width="100%" style="padding-left:15px;padding-right:5px;">


                       <table cellspacing="0" border="0" width="100%">

                        <tbody>

                        <logic:messagesPresent message="false">
                        <tr>
                        <td  colspan="7" width="100%" nowrap>
                          <table width="100%">
                          <tr>
                          <td align="left"><span class="TXT02"><html:errors/></span></td>
                          </tr>
                          </table>
                        </td>
                        </tr>
                        </logic:messagesPresent>

                        <tr>
                        <td align="left" width="20%">

                        </td>
                        <td align="center" nowrap="nowrap" width="40%">

                        </td>

                        <td align="center" nowrap="nowrap" style="padding-top:5px;padding-bottom:10px;" width="40%">

                          <input value="<gsmsg:write key="ntp.18" />" class="btn_time_line" onclick="buttonPush('day');" type="button">
                          <input value="<gsmsg:write key="cmn.weekly" />" class="btn_week" onclick="buttonPush('week');" type="button">
                          <input value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onclick="buttonPush('month');" type="button">
                          <input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_schedule_search">&nbsp;&nbsp;

                        </td>

                        <td width="0%" align="right" nowrap>
                          <input type="image" name="zweek" src="../nippou/images/arrow1_l.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:10px;visibility:hidden">
                          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="buttonPush('move_lm');" style="visibility:hidden">
                          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.thismonth3" />" onClick="buttonPush('today');" style="visibility:hidden">
                          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="buttonPush('move_rm');" style="visibility:hidden">
                          <input type="image" name="yweek" src="../nippou/images/arrow1_r.gif" alt="<gsmsg:write key="cmn.space" />" style="width:25px;height:10px;visibility:hidden">
                        </td>

                        <td width="0%" align="right" nowrap>
                          <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.ntp010DspDate, 'ntp020CalBtn')" style="visibility:hidden" class="calendar_btn2" id="ntp020CalBtn">
                        </td>

                        </tr>

                        <tr>
                        <td colspan="7" class="table_bg_7D91BD" align="center">
                          <table class="tl0" width="100%">
                          <tbody><tr>
                          <td align="left">
                          <span class="text_tl1"><font color="ffffff">&nbsp;</font></span>
                          </td>
                          </tr>
                          </tbody></table>
                        </td>
                        </tr>

                        <tr>
                        <td colspan="1" class="td_gray" align="center" nowrap="nowrap" width="14%"><span class="text_bb5"><gsmsg:write key="schedule.sch100.4" /></span></td>
                        <td colspan="6" class="td_type20" nowrap="nowrap" width="86%">
                         <html:select property="ntp100SltGroup" styleClass="select01" onchange="changeGroupCombo();">

                            <logic:notEmpty name="ntp100Form" property="ntp100GroupLabel" scope="request">
                            <logic:iterate id="gpBean" name="ntp100Form" property="ntp100GroupLabel" scope="request">

                            <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                            <logic:equal name="gpBean" property="styleClass" value="0">
                            <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                            </logic:equal>

                            <logic:notEqual name="gpBean" property="styleClass" value="0">
                            <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                            </logic:notEqual>

                            </logic:iterate>
                            </logic:notEmpty>

                         <!--html:optionsCollection name="ntp100Form" property="sch100GroupLabel" value="value" label="label" /-->
                         </html:select>
                         <input type="button" onclick="openNtp100Group(this.form.ntp100SltGroup, 'ntp100SltGroup', '1')" class="group_btn" value="&nbsp;&nbsp;" id="ntp100GroupBtn">
                         <html:select property="ntp100SltUser" styleClass="select01">
                          <html:optionsCollection name="ntp100Form" property="ntp100UserLabel" value="value" label="label" />
                         </html:select>
                        </td>
                        </tr>

                      <tr>
                      <td colspan="1" align="center" class="td_gray"><span class="text_bb5"><gsmsg:write key="ntp.163" /></span></td>
                      <td colspan="6" class="td_type20">
                        <html:select property="ntp100SltYearFr" styleId="selYearsf">
                        <html:optionsCollection name="ntp100Form" property="ntp100YearLabel" value="value" label="label" />
                        </html:select>
                        <html:select property="ntp100SltMonthFr" styleId="selMonthsf">
                        <html:optionsCollection name="ntp100Form" property="ntp100MonthLabel" value="value" label="label" />
                        </html:select>
                        <html:select property="ntp100SltDayFr" styleId="selDaysf">
                        <html:optionsCollection name="ntp100Form" property="ntp100DayLabel" value="value" label="label" />
                        </html:select>
                        <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDaysf, this.form.selMonthsf, this.form.selYearsf, 'ntp100FrCalBtn')" class="calendar_btn" id="ntp100FrCalBtn">
                        <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 1)">
                        <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 2)">
                        <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearsf')[0], $('#selMonthsf')[0], $('#selDaysf')[0], 3)">
                    ～
                        <html:select property="ntp100SltYearTo" styleId="selYearst">
                        <html:optionsCollection name="ntp100Form" property="ntp100YearLabel" value="value" label="label" />
                        </html:select>
                        <html:select property="ntp100SltMonthTo" styleId="selMonthst">
                        <html:optionsCollection name="ntp100Form" property="ntp100MonthLabel" value="value" label="label" />
                        </html:select>
                        <html:select property="ntp100SltDayTo" styleId="selDayst">
                        <html:optionsCollection name="ntp100Form" property="ntp100DayLabel" value="value" label="label" />
                        </html:select>
                        <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.selDayst, this.form.selMonthst, this.form.selYearst, 'ntp100ToCalBtn')" class="calendar_btn" id="ntp100ToCalBtn">
                        <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 1)">
                        <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 2)">
                        <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#selYearst')[0], $('#selMonthst')[0], $('#selDayst')[0], 3)">
                      </td>
                      </tr>

                       <tr>

                       <td class="table_bg_A5B4E1" nowrap="nowrap" align="center" colspan="1"><span class="text_bb5"><gsmsg:write key="ntp.11" /></span><span class="text_r2"></span></td>
                       <td colspan="6" class="td_wt">
                          <table width="100%">
                          <tr>
                          <td align="left">
                          <input class="btn_search_n1" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" onclick="return openAnkenWindow('ntp100','')" type="button"><br>
                            <img src="../schedule/images/spacer.gif" height="10px" border="0" width="1px">
                            <logic:notEmpty name="ntp100Form" property="ntp100NtpAnkenModel" scope="request">
                              <bean:define id="ankenMdl" name="ntp100Form" property="ntp100NtpAnkenModel" />
                              <div id="ntp100AnkenIdArea"><input name="ntp100AnkenSid" value="<bean:write name="ankenMdl" property="nanSid" />" type="hidden"></div>
                              <div id="ntp100AnkenCodeArea"><span class="text_anken_code"><gsmsg:write key="ntp.29" />：<span class="comp_code_name"><bean:write name="ankenMdl" property="nanCode" /></span></span></div>
                              <div id="ntp100AnkenNameArea"><span class="text_anken"><a id="<bean:write name="ankenMdl" property="nanSid" />" class="sc_link anken_click"><span class="anken_name"><bean:write name="ankenMdl" property="nanName" /></span></a></span><a href="javascript:void(0);" onclick="delAnken('ntp100','');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a></div>
                            </logic:notEmpty>

                            <logic:empty name="ntp100Form" property="ntp100NtpAnkenModel" scope="request">
                              <div id="ntp100AnkenIdArea"></div>
                              <div id="ntp100AnkenCodeArea"></div>
                              <div id="ntp100AnkenNameArea"></div>
                            </logic:empty>



                          </td>

                          <td class="table_bg_A5B4E1 border_both_none border_right_none" align="center" nowrap="nowrap"><span class="text_bb5"><gsmsg:write key="ntp.15" />・<gsmsg:write key="ntp.16" /></span></td>
                          <td align="left" class="td_wt border_both_none border_right_none">
                            <input class="btn_address_n2" value="<gsmsg:write key="addressbook" />" onclick="return openCompanyWindow2('ntp100','')" type="button"><br>
                            <img src="../schedule/images/spacer.gif" height="10px" border="0" width="1px">

                            <logic:notEmpty name="ntp100Form" property="ntp100AdrCompanyMdl" scope="request">
                              <bean:define id="companyMdl" name="ntp100Form" property="ntp100AdrCompanyMdl" />
                              <div id="ntp100CompanyIdArea"><input name="ntp100CompanySid" value="<bean:write name="companyMdl" property="acoSid" />" type="hidden"></div>

                              <logic:notEmpty name="ntp100Form" property="ntp100AdrCompanyBaseMdl" scope="request">
                                <bean:define id="companyBaseMdl" name="ntp100Form" property="ntp100AdrCompanyBaseMdl" />
                                <div id="ntp100CompanyBaseIdArea"><input name="ntp100CompanyBaseSid" value="<bean:write name="companyBaseMdl" property="abaSid" />" type="hidden"></div>
                              </logic:notEmpty>

                              <div id="ntp100CompanyCodeArea"><span class="text_anken_code"><gsmsg:write key="address.7" />：<span class="comp_code_name"><bean:write name="companyMdl" property="acoCode" /></span></span></div>

                              <logic:notEmpty name="ntp100Form" property="ntp100AdrCompanyBaseMdl" scope="request">
                                <div id="ntp100CompNameArea"><span class="text_company"><a id="<bean:write name="companyMdl" property="acoSid" />" class="sc_link comp_click"><span class="comp_name"><bean:write name="companyMdl" property="acoName" /> ： <bean:write name="companyBaseMdl" property="abaName" /></span></a></span><a href="javascript:void(0);" onclick="delCompany('ntp100','');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a></span></div>
                              </logic:notEmpty>

                              <logic:empty name="ntp100Form" property="ntp100AdrCompanyBaseMdl" scope="request">
                                <div id="ntp100CompanyBaseIdArea"></div>
                                <div id="ntp100CompNameArea"><span class="text_company"><a id="<bean:write name="companyMdl" property="acoSid" />" class="sc_link comp_click"><span class="comp_name"><bean:write name="companyMdl" property="acoName" /></span></a></span><a href="javascript:void(0);" onclick="delCompany('ntp100','');"><img src="../common/images/delete.gif" class="img_bottom" alt="" width="15"></a></span></div>
                              </logic:empty>

                              <div id="ntp100AddressIdArea"></div>
                              <div id="ntp100AddressNameArea"></div>
                            </logic:notEmpty>

                            <logic:empty name="ntp100Form" property="ntp100AdrCompanyMdl" scope="request">
                              <div id="ntp100CompanyIdArea"></div>
                              <div id="ntp100CompanyBaseIdArea"></div>
                              <div id="ntp100CompanyCodeArea"></div>
                              <div id="ntp100CompNameArea"></div>
                              <div id="ntp100AddressIdArea"></div>
                              <div id="ntp100AddressNameArea"></div>
                            </logic:empty>


                          </td>
                          </tr>
                          </table>
                        </td>



                        </tr>

                        <tr>
                        <td class="table_bg_A5B4E1" nowrap="nowrap" align="center"><span class="text_bb5"><gsmsg:write key="ntp.3" /></span><span class="text_r2"></span></td>
                        <td colspan="6" class="td_wt" align="left">

                          <logic:notEmpty name="ntp100Form" property="ntp100KtbunruiLavel">
                             <html:select property="ntp100Ktbunrui">
                                <html:optionsCollection name="ntp100Form" property="ntp100KtbunruiLavel" value="value" label="label" />
                             </html:select>
                           </logic:notEmpty>

                           <logic:notEmpty name="ntp100Form" property="ntp100KthouhouLavel">
                             <html:select property="ntp100Kthouhou">
                                <html:optionsCollection name="ntp100Form" property="ntp100KthouhouLavel" value="value" label="label" />
                             </html:select>
                           </logic:notEmpty>

                         </td>
                        </tr>

                        <tr>
                        <td class="table_bg_A5B4E1" nowrap="nowrap" align="center"><span class="text_bb5"><gsmsg:write key="ntp.32" /></span><span class="text_r2"></span></td>
                        <td colspan="6" class="td_wt" align="left">
                          <span class="text_base">

                            <html:multibox name="ntp100Form" property="ntp100Mikomido" styleId="ntp100Mikomido0" value="0" />&nbsp;<label for="ntp100Mikomido0"><span class="text_base7">10%</span></label>&nbsp;
                            <html:multibox name="ntp100Form" property="ntp100Mikomido" styleId="ntp100Mikomido1" value="1" />&nbsp;<label for="ntp100Mikomido1"><span class="text_base7">30%</span></label>&nbsp;
                            <html:multibox name="ntp100Form" property="ntp100Mikomido" styleId="ntp100Mikomido2" value="2" />&nbsp;<label for="ntp100Mikomido2"><span class="text_base7">50%</span></label>
                            <html:multibox name="ntp100Form" property="ntp100Mikomido" styleId="ntp100Mikomido3" value="3" />&nbsp;<label for="ntp100Mikomido3"><span class="text_base7">70%</span></label>&nbsp;
                            <html:multibox name="ntp100Form" property="ntp100Mikomido" styleId="ntp100Mikomido4" value="4" />&nbsp;<label for="ntp100Mikomido4"><span class="text_base7">100%</span></label>

                          </span>
                        </td>
                        </tr>


                        <tr>

                           <td align="center" class="td_gray border_both_none border_right_none" nowrap><span class="text_bb5"><gsmsg:write key="cmn.keyword" /></span></td>

                           <td colspan="6" class="td_wt">
                              <table width="100%">
                                <tr>

                                <td colspan="4" class="td_type20 border_both_none border_left_none" nowrap><span class="text_base2">
                                <html:text name="ntp100Form" maxlength="50" property="ntp010searchWord" style="width:335px;" />
                                <div class="text_base2">
                                <html:radio name="ntp100Form" property="ntp100KeyWordkbn" value="<%= keyWordAnd %>" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
                                <html:radio name="ntp100Form" property="ntp100KeyWordkbn" value="<%= keyWordOr %>" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
                                </div>
                                </span>
                                </td>

                                <th width="10%" class="td_gray text_bb2 border_both_none border_right_none" nowrap><gsmsg:write key="cmn.search2" /></th>
                                  <td width="40%" class="td_sub_detail border_both_none border_right_none">
                                    <html:multibox styleId="search_scope_01" name="ntp100Form" property="ntp100SearchTarget" value="<%= targetTitle %>" /><label for="search_scope_01"><gsmsg:write key="cmn.title" /></label>
                                    <html:multibox styleId="search_scope_02" name="ntp100Form" property="ntp100SearchTarget" value="<%= targetHonbun %>" /><label for="search_scope_02"><gsmsg:write key="cmn.content" /></label>
                                </td>
                                </tr>
                              </table>

                          </td>

                       </tr>

                        <tr>
                        <td colspan="1" class="td_gray" align="center" nowrap="nowrap"><span class="text_bb5"><gsmsg:write key="schedule.10" /></span></td>
                        <td colspan="6" class="td_type20" align="left" nowrap="nowrap">

                        <span class="sc_block_color_1"><html:multibox name="ntp100Form" property="ntp100Bgcolor" styleId="ntp100Bgcolor0" value="1" /></span>&nbsp;<label for="ntp100Bgcolor0"><span class="text_base7"></span></label>&nbsp;
                        <span class="sc_block_color_2"><html:multibox name="ntp100Form" property="ntp100Bgcolor" styleId="ntp100Bgcolor1" value="2" /></span>&nbsp;<label for="ntp100Bgcolor1"><span class="text_base7"></span></label>&nbsp;
                        <span class="sc_block_color_3"><html:multibox name="ntp100Form" property="ntp100Bgcolor" styleId="ntp100Bgcolor2" value="3" /></span>&nbsp;<label for="ntp100Bgcolor2"><span class="text_base7"></span></label>
                        <span class="sc_block_color_4"><html:multibox name="ntp100Form" property="ntp100Bgcolor" styleId="ntp100Bgcolor3" value="4" /></span>&nbsp;<label for="ntp100Bgcolor3"><span class="text_base7"></span></label>&nbsp;
                        <span class="sc_block_color_5"><html:multibox name="ntp100Form" property="ntp100Bgcolor" styleId="ntp100Bgcolor4" value="5" /></span>&nbsp;<label for="ntp100Bgcolor4"><span class="text_base7"></span></label>

                        </td>
                        </tr>


                        <tr>
                        <td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb5"><gsmsg:write key="cmn.sort.order" /></span></td>
                        <td colspan="6" class="td_type20" nowrap>
                        <span class="text_base2">

                        <span class="text_bb5"><gsmsg:write key="cmn.first.key" /></span>
                        <html:select property="ntp100SortKey1">
                          <html:optionsCollection name="ntp100Form" property="sortKeyList" value="value" label="label" />
                        </html:select>
                        <html:radio name="ntp100Form" property="ntp100OrderKey1" styleId="sort1_up" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
                        <html:radio name="ntp100Form" property="ntp100OrderKey1" styleId="sort1_dw" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="text_bb5"><gsmsg:write key="cmn.second.key" /></span>
                        <html:select property="ntp100SortKey2">
                          <html:optionsCollection name="ntp100Form" property="sortKeyList" value="value" label="label" />
                        </html:select>
                        <html:radio name="ntp100Form" property="ntp100OrderKey2" styleId="sort2_up" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
                        <html:radio name="ntp100Form" property="ntp100OrderKey2" styleId="sort2_dw" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

                        </span>
                        </td>
                        </tr>

                      <tr>
                      <td colspan="7" width="100%">
                        <table cellpadding="0" cellspacing="0" width="100%">
                        <tbody><tr>
                        <td align="center" width="100%">
                        <br>
                        <input name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onclick="buttonPush('ntp100search');" type="button">
                        </td>
                        </tr>
                        </tbody></table>
                      </td>
                      </tr>




                      <tr>
                      <td>
                        <img src="../common/images/spacer.gif" alt="" height="5px" border="0" width="1px">
                      </td>
                      </tr>


                      <logic:notEmpty name="ntp100Form" property="ntp100NippouList">


                        <tr>
                        <td colspan="1" align="center" class="td_gray" nowrap><span class="text_bb2"><gsmsg:write key="reserve.output.item" /></span></td>
                        <td colspan="6" class="td_type20" nowrap>
                          <table cellpadding="0" cellspacing="0" width="100%">
                          <tr>
                          <td>
                          <logic:equal name="ntp100Form" property="adminKbn" value="1">
                            <span style="white-space: nowrap"><html:multibox styleId="loginId" name="ntp100Form" property="ntp100CsvOutField" value="1" /><label for="loginId" class="text_base"><gsmsg:write key="cmn.user.id" /></label></span><wbr>
                          </logic:equal>
                          <span style="white-space: nowrap"><html:multibox styleId="ntpDate" name="ntp100Form" property="ntp100CsvOutField" value="2" /><label for="ntpDate" class="text_base"><gsmsg:write key="ntp.35" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="frTime" name="ntp100Form" property="ntp100CsvOutField" value="3" /><label for="frTime" class="text_base"><gsmsg:write key="cmn.starttime" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="toTime" name="ntp100Form" property="ntp100CsvOutField" value="4" /><label for="toTime" class="text_base"><gsmsg:write key="cmn.endtime" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="ankenCode" name="ntp100Form" property="ntp100CsvOutField" value="5" /><label for="ankenCode" class="text_base"><gsmsg:write key="ntp.29" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="cmpCode" name="ntp100Form" property="ntp100CsvOutField" value="6" /><label for="cmpCode" class="text_base"><gsmsg:write key="address.7" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="title" name="ntp100Form" property="ntp100CsvOutField" value="7" /><label for="title" class="text_base"><gsmsg:write key="cmn.title" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="titleColor" name="ntp100Form" property="ntp100CsvOutField" value="8" /><label for="titleColor" class="text_base">タイトル色</label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="kbunruicode" name="ntp100Form" property="ntp100CsvOutField" value="9" /><label for="kbunruicode" class="text_base"><gsmsg:write key="ntp.117" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="khouhoucode" name="ntp100Form" property="ntp100CsvOutField" value="10" /><label for="khouhoucode" class="text_base"><gsmsg:write key="ntp.118" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="value" name="ntp100Form" property="ntp100CsvOutField" value="11" /><label for="value" class="text_base"><gsmsg:write key="cmn.content" /></label></span><wbr>
                          <span style="white-space: nowrap"><html:multibox styleId="mikomido" name="ntp100Form" property="ntp100CsvOutField" value="12" /><label for="mikomido" class="text_base"><gsmsg:write key="ntp.32" /></label></span><wbr>
                          </td>
                          <td align="right" width="0%">
                          <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('ntp100export');">
                          </td>
                          </tr>
                          </table>
                        </td>
                        </tr>

                        <bean:size id="count1" name="ntp100Form" property="ntp100PageLabel" scope="request" />

                        <tr>
                        <td colspan="7" width="100%" align="right" valign="top" nowrap>

                          <logic:greaterThan name="count1" value="1">
                          <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" style="padding-bottom:5px;" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">
                          <logic:notEmpty name="ntp100Form" property="ntp100PageLabel">
                            <html:select property="ntp100Slt_page1" onchange="changePage1();" styleClass="text_i">
                              <html:optionsCollection name="ntp100Form" property="ntp100PageLabel" value="value" label="label" />
                            </html:select>
                          </logic:notEmpty>
                          <logic:empty name="ntp100Form" property="ntp100PageLabel">
                            <html:select property="ntp100Slt_page1" styleClass="text_i">
                             <option value="1" class="text_i">1 / 1</option>
                            </html:select>
                          </logic:empty>
                          <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" style="padding-bottom:5px;" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
                          </logic:greaterThan>
                        </td>
                        </tr>

                      </logic:notEmpty>

                      <logic:notEmpty name="ntp100Form" property="ntp100NippouList">

                        <tr>
                        <td class="td_type_tab" colspan="7">
                          <table class="tl0" cellpadding="5" cellspacing="0" width="100%">
                          <tbody><tr>
                      <!--


                          <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('1', '0')"><span class="text_tlw">名前</span></a></th>




                          <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('2', '1')"><span class="text_tlw">開始日時▲</span></a></th>







                          <th width="15%" class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('3', '0')"><span class="text_tlw">終了日時</span></a></th>




                          <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('4', '0')"><span class="text_tlw">タイトル/<gsmsg:write key="cmn.content" /></span></a></th>

                      -->



                          <th class="table_bg_7D91BD" width="15%"><a href="javascript:void(0)" onclick="clickSortTitle('1')"><span class="text_tlw"><gsmsg:write key="cmn.name4" /></span></a></th>




                          <th class="table_bg_7D91BD" width="15%"><a href="javascript:void(0)" onclick="clickSortTitle('2')"><span class="text_tlw"><gsmsg:write key="ntp.35" /></span></a></th>





                          <th class="table_bg_7D91BD" width="15%"><span class="text_tlw"><gsmsg:write key="ntp.11" /></span></th>




                          <th class="table_bg_7D91BD"><a href="javascript:void(0)" onclick="clickSortTitle('4')"><span class="text_tlw"><gsmsg:write key="cmn.title" />/<gsmsg:write key="cmn.content" /></span></a></th>


                          </tr>




                            <logic:iterate id="ntpMdl" name="ntp100Form" property="ntp100NippouList" indexId="idx">
                            <bean:define id="tdColor" value="" />

                            <% String[] tdColors = new String[] {"td_type1", "td_type29"}; %>
                            <% tdColor = tdColors[(idx.intValue() % 2)]; %>
                            <%
                               int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
                               int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
                               int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
                            %>

                            <tr id="NTP_AREA_<bean:write name="ntpMdl" property="ntpSid" />">
                            <td class="<%= tdColor %>" align="center" valign="middle" nowrap><bean:write name="ntpMdl" property="userName" /></td>
                            <td class="<%= tdColor %>" align="center" valign="middle" nowrap>
                              <bean:write name="ntpMdl" property="ntpDateStr" /><br>
                              <bean:write name="ntpMdl" property="fromTimeStr" />～<bean:write name="ntpMdl" property="toTiemStr" />
                            </td>
                            <td class="<%= tdColor %>" align="center" valign="middle" nowrap><bean:write name="ntpMdl" property="ankenName" /></td>
                            <td class="<%= tdColor %>" align="left" valign="middle">

                            <bean:define id="chKbn" name="ntpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

                            <% String chkClass = "ntp_chk2"; %>
                            <% if (chKbn == 1) { %>
                            <%    chkClass = ""; %>
                            <% } %>

                            <div onClick="editNippou('edit', <bean:write name="ntpMdl" property="ntpDspDateStr" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="ntpMdl" property="userSid" />, 0);" class="<%= chkClass %>" style="padding-top:3px;">

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

                            <%-- a href="#" onClick="editNippou('edit', <bean:write name="ntp100Form" property="ntp010DspDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="ntpMdl" property="userSid" />, <bean:write name="ntpMdl" property="userKbn" />);"--%>
                            <a href="#" title="<bean:write name="ntpMdl" property="title" />">

                            <!--タイトルカラー設定-->
                            <logic:equal name="ntpMdl" property="titleColor" value="1">
                            <span class="sc_link_1b">
                            </logic:equal>
                            <logic:equal name="ntpMdl" property="titleColor" value="2">
                            <span class="sc_link_2b">
                            </logic:equal>
                            <logic:equal name="ntpMdl" property="titleColor" value="3">
                            <span class="sc_link_3b">
                            </logic:equal>
                            <logic:equal name="ntpMdl" property="titleColor" value="4">
                            <span class="sc_link_4b">
                            </logic:equal>
                            <logic:equal name="ntpMdl" property="titleColor" value="5">
                            <span class="sc_link_5b">
                            </logic:equal>

                            <bean:write name="ntpMdl" property="title" /></span></a><BR>
                            <bean:write name="ntpMdl" property="detail" filter="false"/>

                            </div>
                            </td>
                            </tr>
                            </logic:iterate>
                       </logic:notEmpty>


                      <logic:notEmpty name="ntp100Form" property="ntp100NippouList">
                        <bean:size id="count2" name="ntp100Form" property="ntp100PageLabel" scope="request" />
                        <logic:greaterThan name="count2" value="1">
                        <tr>
                        <td colspan="7" width="100%" align="right" valign="top" nowrap style="padding-top:5px;">
                        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
                        <logic:notEmpty name="ntp100Form" property="ntp100PageLabel">
                        <html:select property="ntp100Slt_page2" onchange="changePage2();" styleClass="text_i">
                          <html:optionsCollection name="ntp100Form" property="ntp100PageLabel" value="value" label="label" />
                        </html:select>
                        </logic:notEmpty>
                        <logic:empty name="ntp100Form" property="ntp100PageLabel">
                        <html:select property="ntp100Slt_page2" styleClass="text_i">
                         <option value="1" class="text_i">1 / 1</option>
                        </html:select>
                        </logic:empty>
                        <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
                        </td>
                        </tr>
                        </logic:greaterThan>
                      </logic:notEmpty>



                        <tr>
                        <td class="td_type_tab" colspan="7">
                          <table class="tl5" width="100%">
                          <tbody><tr>
                          <td width="100%">&nbsp;</td>
                          <td align="right">
                          </td>
                          </tr>
                          </table>
                        </td>
                        </tr>


                      </table>




                    </td>
                  </tr>


                 </tbody>

                 </table>


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

</html:form>
</body>
</html:html>