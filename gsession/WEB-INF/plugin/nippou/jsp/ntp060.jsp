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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>


<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03" onunload="calWindowClose();">



<html:form action="/nippou/ntp060">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp060NanSid" />
<html:hidden property="ntp060ProcMode" />
<html:hidden property="ntp060InitFlg" />
<html:hidden property="paramAnkenSid" />
<html:hidden property="dspMod" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="3" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" /> ]</td>
    <td width="100%" class="header_white_bg">
      <logic:equal name="ntp060Form" property="adminKbn" value="1">
        <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush2('ktool');">
      </logic:equal>
      <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush2('pset');">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

  </td>
  </tr>


  </table>

</td>
</tr>
</table>

<img src="../schedule/images/spacer.gif" width="1px" height="2px" border="0">

<table cellpadding="5px" width="100%">
<tr>
<td>

    <table cellpadding="0" cellspacing="0" width="99%" class="menu_table">

      <tr>

        <td valign="top" width="16%" class="menu_space_area_both">
          <table width="100%" height="100%" cellpadding="0" cellspacing="0">

            <tr class="menu_not_select_tr" class="" onClick="buttonPush('nippou');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_single.gif" alt="<gsmsg:write key="ntp.1" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.1" /></span>
              </td>
            </tr>

            <tr class="menu_select_tr">
              <td class="menu_select_icon">
                <img src="../nippou/images/menu_icon_anken.gif" alt="<gsmsg:write key="ntp.11" />" />
              </td>
              <td class="menu_select_text" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.11" /></span>
              </td>
              <td class="menu_select_text" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
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

            <logic:equal name="ntp060Form" property="adminKbn" value="1">
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

        <%-- 日報データ --%>
        <div id="nippouArea" class="areablock" style="vertical-align:top;padding-left:10px;">

                    <table width="100%">
                      <tr>

                        <td width="65%" valign="top">

                             <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">

                             <table width="100%">

                                            <tr><td align="center">

                                            </td></tr>

                                            <tr><td align="center">
                                               <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
                                            </td></tr>

                                            <tr>
                                              <td align="center" style="padding-bottom:10px;">

                                                <table width="100%">

                                                <tr>
                                                <td align="right" colspan="3">
                                                <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
                                                <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('export');">
                                                <input type="button" class="btn_add_n3" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.new.registration" />" onClick="return buttonSubmit('add','-1');">
                                                </td>
                                                </tr>

                                                <%--
                                                <tr>
                                                <td align="center" class="table_bg_A5B4E1" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />２</span></td>
                                                <td align="left" class="td_type20" nowrap>
                                                <!-- ソート２コンボ -->
                                                <html:select name="ntp060Form" property="ntp060SortKey2">
                                                <logic:notEmpty name="ntp060Form" property="ntp060SortList">
                                                <html:optionsCollection name="ntp060Form" property="ntp060SortList" label="value" label="label" />
                                                </logic:notEmpty>
                                                </html:select>
                                                <html:radio name="ntp060Form" property="ntp060OrderKey2" styleId="ntp060OrderKey21" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp060OrderKey21"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
                                                <html:radio name="ntp060Form" property="ntp060OrderKey2" styleId="ntp060OrderKey22" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp060OrderKey22"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
                                                </td>
                                                </tr>
                                                --%>

                                                </table>
                                              </td>
                                            </tr>

                                            <logic:messagesPresent message="false">
                                            <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
                                            <tr><td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td></tr>
                                            </logic:messagesPresent>


                                            <tr>
                                            <td align="right">
                                              <table width="100%" cellpadding="5" cellspacing="0">
                                                <td>
                                                  <!-- ソート１コンボ -->
                                                  <html:select name="ntp060Form" property="ntp060SortKey1" onchange="return buttonPush('search');">
                                                  <logic:notEmpty name="ntp060Form" property="ntp060SortList">
                                                  <html:optionsCollection name="ntp060Form" property="ntp060SortList" value="value" label="label" />
                                                  </logic:notEmpty>
                                                  </html:select>
                                                  <html:radio name="ntp060Form" property="ntp060OrderKey1" styleId="ntp060OrderKey11" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" onclick="return buttonPush('search');" /><span class="text_base7"><label for="ntp060OrderKey11"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
                                                  <html:radio name="ntp060Form" property="ntp060OrderKey1" styleId="ntp060OrderKey12" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" onclick="return buttonPush('search');" /><span class="text_base7"><label for="ntp060OrderKey12"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
                                                </td>
                                                <logic:notEmpty name="ntp060Form" property="ntp060AnkenList">
                                                <bean:size id="count1" name="ntp060Form" property="ntp060PageCmbList" scope="request" />
                                                <logic:greaterThan name="count1" value="1">
                                                <td align="right">
                                                  <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush('prevPage');">
                                                  <html:select property="ntp060PageTop" onchange="changePage(0);">
                                                    <html:optionsCollection name="ntp060Form" property="ntp060PageCmbList" value="value" label="label" />
                                                  </html:select>
                                                  <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush('nextPage');"></td>
                                                </logic:greaterThan>
                                                </logic:notEmpty>
                                                </tr>
                                              </table>
                                            </td>
                                            </tr>



                                            <logic:notEmpty name="ntp060Form" property="ntp060AnkenList">
                                            <logic:iterate id="ankenMdl" name="ntp060Form" property="ntp060AnkenList" indexId="idx">

                                            <bean:define id="mod" value="0" />
                                            <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
                                              <bean:define id="tblColor" value="smail_td1" />
                                            </logic:equal>
                                            <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
                                              <bean:define id="tblColor" value="smail_td2" />
                                            </logic:notEqual>


                                            <tr>
                                            <td>

                                            <table style="margin-top:5px;" cellpadding="0" cellspacing="0" width="100%">
                                              <tr>
                                              <td class="smail_td1 smail_tr" width="95%" align="left" valign="middle">

                                                  <table width="100%">
                                                    <tr>
                                                      <td class="<bean:write name="tblColor" /> td_<bean:write name="ankenMdl" property="nanSid" />" width="100%" align="left" colspan="2">
                                                        <table width="100%">
                                                          <tr>
                                                            <td width="70%" align="left">
                                                              <span class="text_bold anken_code_title"><bean:write name="ankenMdl" property="nanCode" /></span>
                                                            </td>
                                                            <td width="30%" align="right">
                                                              <span class="text_bold anken_edate_title"><bean:write name="ankenMdl" property="ntp060Edate" />&nbsp;更新&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                                            </td>
                                                          </tr>
                                                        </table>

                                                      </td>
                                                    </tr>

                                                    <tr>

                                                      <td valign="top" align="center" width="10%" class="<bean:write name="tblColor" /> td_<bean:write name="ankenMdl" property="nanSid" />">

                                                          <% String parcent_class= "parcent_bg"; %>
                                                          <logic:equal name="ankenMdl" property="nanMikomi" value="4">
                                                            <% parcent_class= "parcent_bg2"; %>
                                                          </logic:equal>

                                                          <div class="<%= parcent_class %>" onclick="return buttonSubmit('edit','<bean:write name="ankenMdl" property="nanSid" />')">
                                                            <div class="text_parcent">
                                                            <logic:equal name="ankenMdl" property="nanMikomi" value="0">10%</logic:equal>
                                                            <logic:equal name="ankenMdl" property="nanMikomi" value="1">30%</logic:equal>
                                                            <logic:equal name="ankenMdl" property="nanMikomi" value="2">50%</logic:equal>
                                                            <logic:equal name="ankenMdl" property="nanMikomi" value="3">70%</logic:equal>
                                                            <logic:equal name="ankenMdl" property="nanMikomi" value="4">100%</logic:equal>
                                                            </div>
                                                          </div>
                                                      </td>

                                                      <td width="90%" style="padding-right:30px;" id="tr_<bean:write name="ankenMdl" property="nanSid" />" valign="top" class="<bean:write name="tblColor" />">

                                                          <div style="float:left;">
                                                          <span id="<bean:write name="ankenMdl" property="nanSid" />" class="text_blue_120 text_bold anken_name_title anken_link_area<%= String.valueOf(idx.intValue() % 2) %>" onclick="return buttonSubmit('edit','<bean:write name="ankenMdl" property="nanSid" />')"><bean:write name="ankenMdl" property="nanName" /></span>
                                                          </div>
                                                          <div style="float:right;cursor:pointer;">
                                                          <img onClick="return ntpSearch(<bean:write name="ankenMdl" property="nanSid" />);" src="../nippou/images/icon_nippou_search.gif" border="0" alt="<gsmsg:write key="ntp.1" />">
                                                          </div>

                                                          <hr class="anken_hr">
                                                          <logic:notEmpty  name="ankenMdl" property="ntp060CompanyName">
                                                            <span class="text_company" style="font-size:12px;"><bean:write name="ankenMdl" property="ntp060CompanyName" />
                                                            <logic:notEmpty  name="ankenMdl" property="ntp060BaseName">
                                                              &nbsp;&nbsp;<bean:write name="ankenMdl" property="ntp060BaseName" />
                                                            </logic:notEmpty>
                                                            </span>
                                                            <br>
                                                          </logic:notEmpty>

                                                          <logic:notEmpty  name="ankenMdl" property="ntp060ShohinList">
                                                            <logic:iterate id="shMdl" name="ankenMdl" property="ntp060ShohinList">
                                                              <wbr><span class="text_shohin"><bean:write name="shMdl" property="nhnName" /></span>
                                                            </logic:iterate>
                                                            <br>
                                                          </logic:notEmpty>

                                                          <div id="tooltip_area"></div>
                                                          <span class="mitumori_kin_str_area"><span class="text_kingaku_title"><gsmsg:write key="ntp.53" />：</span><span class="text_kingaku"><bean:write name="ankenMdl" property="ntp060KinMitumori" /></span></span><span style="display:none;"><nobr><span style="font-weight:bold;font-size:12px;color:#333333;"><gsmsg:write key="ntp.55" />:<bean:write name="ankenMdl" property="ntp060MitumoriDate" /></nobr></span></span>&nbsp;&nbsp;&nbsp;&nbsp;
                                                          <span class="jutyu_kin_str_area"><span class="text_kingaku_title"><gsmsg:write key="ntp.54" />：</span><span class="text_kingaku"><bean:write name="ankenMdl" property="ntp060KinJutyu" /></span></span><span style="display:none;"><nobr><span style="font-weight:bold;font-size:12px;color:#333333;"><gsmsg:write key="ntp.56" />:<bean:write name="ankenMdl" property="ntp060JutyuDate" /></nobr></span></span>





                                                      </td>

                                                    </tr>

                                                  </table>
                                              </td>

                                              <td class="<bean:write name="tblColor"/> anken_link_area<%= String.valueOf(idx.intValue() % 2) %> anken_link_tag" id="<bean:write name="ankenMdl" property="nanSid" />" style="border: 1px solid rgb(204, 204, 204);" align="center" nowrap="nowrap" valign="middle" width="0%">
                                                  <div><a href="#"><img src="../common/images/icon_next.gif"></a></div>
                                              </td>

                                              </tr>

                                              </table>

                                              </td>
                                              </tr>
                                              </logic:iterate>
                                              </logic:notEmpty>


                                            <logic:notEmpty name="ntp060Form" property="ntp060AnkenList">
                                            <tr>
                                            <td align="right">
                                              <bean:size id="count1" name="ntp060Form" property="ntp060PageCmbList" scope="request" />
                                              <logic:greaterThan name="count1" value="1">
                                              <table width="100%" cellpadding="5" cellspacing="0">
                                                <td align="right">
                                                  <img src="../common/images/arrow_w2.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="13" height="13" onClick="buttonPush('prevPage');">
                                                  <html:select property="ntp060PageBottom" onchange="changePage(1);">
                                                    <html:optionsCollection name="ntp060Form" property="ntp060PageCmbList" value="value" label="label" />
                                                  </html:select>
                                                  <img src="../common/images/arrow_w.gif" alt="<gsmsg:write key="project.48" />" width="13" height="13" onClick="buttonPush('nextPage');"></td>
                                                </tr>
                                              </table>
                                              </logic:greaterThan>
                                            </td>
                                            </tr>
                                            </logic:notEmpty>

                             </table>

                        </td>



                        <td width="35%" valign="top" style="padding-left:10px;padding-right:10px;">

                              <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">


                              <%--
                              <table>
                                            <tr>
                                            <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
                                            </tr>

                                            <tr>
                                            <td align="left">

                                              <table width="100%" class="tl0" border="0" cellpadding="5">

                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.29" /></span></td>
                                              <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp060Form" property="ntp060NanCode" size="16" maxlength="8" /></td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.57" /></span></td>
                                              <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp060Form" property="ntp060NanName" size="50" maxlength="50" /></td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="address.7" /></span></td>
                                              <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp060Form" property="ntp060AcoCode" size="16" maxlength="8" /></td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.company.name" /></span></td>
                                              <td align="left" class="td_type20" width="35%"><html:text name="ntp060Form" property="ntp060AcoName" size="50" maxlength="50" /></td>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="address.9" /></span></td>
                                              <td align="left" class="td_type20" width="35%"><html:text name="ntp060Form" property="ntp060AcoNameKana" size="50" maxlength="50" /></td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.150" /></span></td>
                                              <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp060Form" property="ntp060AbaName" size="50" maxlength="50" /></td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.58" /></span></td>
                                              <td align="left" class="td_type20" width="85%" colspan="3"><html:text name="ntp060Form" property="ntp060ShohinName" size="50" maxlength="50" /></td>
                                              </tr>

                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.11" /><gsmsg:write key="bmk.15" /></span></td>
                                              <td align="left" class="td_type20" width="85%" colspan="3">

                                              <!-- 開始年コンボ -->
                                              <html:select name="ntp060Form" property="ntp060FrYear" styleId="selYear">
                                              <logic:notEmpty name="ntp060Form" property="ntp060YearList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060YearList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>

                                              <!-- 開始月コンボ -->
                                              <html:select name="ntp060Form" property="ntp060FrMonth" styleId="selMonth">
                                              <logic:notEmpty name="ntp060Form" property="ntp060MonthList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060MonthList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <!-- 開始日コンボ -->
                                              <html:select name="ntp060Form" property="ntp060FrDay" styleId="selDay">
                                              <logic:notEmpty name="ntp060Form" property="ntp060DayList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060DayList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <input type="button" value="Cal" onclick="wrtCalendar(this.form.selDay, this.form.selMonth, this.form.selYear)" class="calendar_btn">
                                              &nbsp;<span class="text_base7">～</span>&nbsp;
                                              <!-- 終了年コンボ -->
                                              <html:select name="ntp060Form" property="ntp060ToYear" styleId="seleYear">
                                              <logic:notEmpty name="ntp060Form" property="ntp060YearList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060YearList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <!-- 終了月コンボ -->
                                              <html:select name="ntp060Form" property="ntp060ToMonth" styleId="seleMonth">
                                              <logic:notEmpty name="ntp060Form" property="ntp060MonthList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060MonthList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <!-- 終了日コンボ -->
                                              <html:select name="ntp060Form" property="ntp060ToDay" styleId="seleDay">
                                              <logic:notEmpty name="ntp060Form" property="ntp060DayList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060DayList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <input type="button" value="Cal" onclick="wrtCalendar(this.form.seleDay, this.form.seleMonth, this.form.seleYear)" class="calendar_btn">
                                              </td>
                                              </tr>

                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.61" />／<gsmsg:write key="ntp.62" /></span></td>
                                              <td align="left" class="td_type20" width="35%">

                                              <!-- 業務コンボ -->
                                              <html:select name="ntp060Form" property="ntp060NgySid" styleClass="select01" onchange="return buttonPush('changeGyomu');" style="width: 150px;">
                                              <logic:notEmpty name="ntp060Form" property="ntp060GyomuList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060GyomuList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              &nbsp;
                                              <!-- プロセスコンボ -->
                                              <html:select name="ntp060Form" property="ntp060NgpSid" styleClass="select01" style="width: 150px;">
                                              <logic:notEmpty name="ntp060Form" property="ntp060ProcessList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060ProcessList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>

                                              </td>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.63" /></span></td>
                                              <td align="left" class="td_type20" width="35%">
                                              <html:multibox name="ntp060Form" property="ntp060Mikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_DAI) %>" />&nbsp;<span class="text_base7">大</span>&nbsp;
                                              <html:multibox name="ntp060Form" property="ntp060Mikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_CHU) %>" />&nbsp;<span class="text_base7">中</span>&nbsp;
                                              <html:multibox name="ntp060Form" property="ntp060Mikomi" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.MIKOMI_SYO) %>" />&nbsp;<span class="text_base7">小</span>
                                              </td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.53" /></span></td>
                                              <td align="left" class="td_type20" width="35%">
                                              <html:text name="ntp060Form" property="ntp060NanKinMitumori" size="13" maxlength="12" style="text-align:right" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
                                              <html:radio name="ntp060Form" property="ntp060NanKinMitumoriKbn" styleId="ntp060NanKinMitumoriKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp060NanKinMitumoriKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
                                              <html:radio name="ntp060Form" property="ntp060NanKinMitumoriKbn" styleId="ntp060NanKinMitumoriKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp060NanKinMitumoriKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
                                              </td>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.54" /></span></td>
                                              <td align="left" class="td_type20" width="35%">
                                              <html:text name="ntp060Form" property="ntp060NanKinJutyu" size="13" maxlength="12" style="text-align:right" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span>
                                              <html:radio name="ntp060Form" property="ntp060NanKinJutyuKbn" styleId="ntp060NanKinJutyuKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp060NanKinJutyuKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
                                              <html:radio name="ntp060Form" property="ntp060NanKinJutyuKbn" styleId="ntp060NanKinJutyuKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp060NanKinJutyuKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
                                              </td>
                                              </tr>
                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.64" /></span></td>
                                              <td align="left" class="td_type20" width="35%">
                                              <html:multibox name="ntp060Form" property="ntp060Syodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_CHU) %>" />&nbsp;<span class="text_base7"><gsmsg:write key="ntp.68" /></span>&nbsp;
                                              <html:multibox name="ntp060Form" property="ntp060Syodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_JYUCHU) %>" />&nbsp;<span class="text_base7"><gsmsg:write key="ntp.69" /></span>&nbsp;
                                              <html:multibox name="ntp060Form" property="ntp060Syodan" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.SYODAN_SICHU) %>" />&nbsp;<span class="text_base7"><gsmsg:write key="ntp.7" /></span>
                                              </td>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="ntp.65" /></span></td>
                                              <td align="left" class="td_type20" width="35%">
                                              <!-- 顧客源泉コンボ -->
                                              <html:select name="ntp060Form" property="ntp060NcnSid" styleClass="select01" style="width: 220px;">
                                              <logic:notEmpty name="ntp060Form" property="ntp060ContactList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060ContactList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              </td>
                                              </tr>

                                              <tr>
                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />１</span></td>
                                              <td align="left" class="td_type20" width="35%" nowrap>
                                              <!-- ソート１コンボ -->
                                              <html:select name="ntp060Form" property="ntp060SortKey1" styleClass="select01" style="width: 220px;">
                                              <logic:notEmpty name="ntp060Form" property="ntp060SortList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060SortList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <html:radio name="ntp060Form" property="ntp060OrderKey1" styleId="ntp060OrderKey11" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp060OrderKey11"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
                                              <html:radio name="ntp060Form" property="ntp060OrderKey1" styleId="ntp060OrderKey12" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp060OrderKey12"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
                                              </td>

                                              <td align="center" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb2"><gsmsg:write key="cmn.sortkey" />２</span></td>
                                              <td align="left" class="td_type20" width="35%" nowrap>
                                              <!-- ソート２コンボ -->
                                              <html:select name="ntp060Form" property="ntp060SortKey2" styleClass="select01" style="width: 220px;">
                                              <logic:notEmpty name="ntp060Form" property="ntp060SortList">
                                              <html:optionsCollection name="ntp060Form" property="ntp060SortList" label="value" label="label" />
                                              </logic:notEmpty>
                                              </html:select>
                                              <html:radio name="ntp060Form" property="ntp060OrderKey2" styleId="ntp060OrderKey21" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_ASC) %>" /><span class="text_base7"><label for="ntp060OrderKey21"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
                                              <html:radio name="ntp060Form" property="ntp060OrderKey2" styleId="ntp060OrderKey22" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.ORDER_KEY_DESC) %>" /><span class="text_base7"><label for="ntp060OrderKey22"><gsmsg:write key="cmn.order.desc" /></label></span>&nbsp;
                                              </td>
                                              </tr>
                                              </table>

                                            </td>
                                            </tr>
                                            <tr>
                                            <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
                                            </tr>
                                            <tr>
                                            <td align="center">
                                                <table cellpadding="0" cellspacing="0" width="100%">
                                                <tr>
                                                <td align="center" width="100%">
                                                <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="return buttonPush('search');">
                                                </td>
                                                <td align="right" width="0%">
                                                <logic:notEmpty name="ntp060Form" property="ntp060AnkenList">
                                                <input type="button" name="btn_usrimp" class="btn_csv_n2" value="エクスポート" onClick="buttonPush('export');">
                                                </logic:notEmpty>
                                                </td>
                                                </tr>
                                                </table>
                                            </td>
                                            </tr>
                                            <tr>
                                            <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
                                            </tr>


                             </table>
                             --%>
















                                      <table class="ankenSearchTable" width="100%">
                                         <tr>
                                           <td class="td_wt title_head_bg" style="padding:0px;" colspan="2" valign="middle" align="center">
                                             <table width="100%">
                                               <tr>
                                                 <td width="30%" align="right" style="padding:5px;">
                                                   <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />">
                                                 </td>
                                                 <td align="left">
                                                   <span class="timeline_menu">&nbsp;<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />&nbsp;&nbsp;</span>
                                                   <input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="return buttonPush('search');">
                                                 </td>
                                               </tr>
                                             </table>
                                           </td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.29" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060NanCode" maxlength="8" style="width:131px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.57" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060NanName" maxlength="50" style="width:215px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="address.7" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060AcoCode" maxlength="8" style="width:131px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="cmn.company.name" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060AcoName" maxlength="50" style="width:215px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="address.9" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060AcoNameKana" maxlength="100" style="width:215px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.150" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060AbaName" maxlength="50" style="width:215px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.59" /></span></td>
                                           <td class="td_wt">
                                             <html:select name="ntp060Form" property="ntp060CatSid" style="margin:0px;padding:0px">
                                             <logic:notEmpty name="ntp060Form" property="ntp060CategoryList">
                                             <html:optionsCollection name="ntp060Form" property="ntp060CategoryList" value="value" label="label" />
                                             </logic:notEmpty>
                                             </html:select>
                                           </td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.58" /></span></td><td class="td_wt"><html:text name="ntp060Form" property="ntp060ShohinName" maxlength="50" style="width:215px; margin:0 4px;" /></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.11" /><gsmsg:write key="bmk.15" /></span></td><td class="td_type20" colspan="3" align="left" width="85%">

                                                                                                                      <!-- 開始年コンボ -->
                                                                                                                      <html:select name="ntp060Form" property="ntp060FrYear" styleId="selYear" style="margin:0px;padding:0px">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060YearList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060YearList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>

                                                                                                                      <!-- 開始月コンボ -->
                                                                                                                      <html:select name="ntp060Form" property="ntp060FrMonth" styleId="selMonth" styleClass="combo_style">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060MonthList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060MonthList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                                                                                                      <!-- 開始日コンボ -->
                                                                                                                      <html:select name="ntp060Form" property="ntp060FrDay" styleId="selDay" styleClass="combo_style">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060DayList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060DayList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                                                                                                      <input type="button" value="Cal" onclick="wrtCalendar(this.form.selDay, this.form.selMonth, this.form.selYear)" class="calendar_btn">
                                                                                                                      <span class="text_base7"><br>～</span><br>
                                                                                                                      <!-- 終了年コンボ -->
                                                                                                                      <html:select name="ntp060Form" property="ntp060ToYear" styleId="seleYear" styleClass="combo_style">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060YearList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060YearList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                                                                                                      <!-- 終了月コンボ -->
                                                                                                                      <html:select name="ntp060Form" property="ntp060ToMonth" styleId="seleMonth" styleClass="combo_style">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060MonthList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060MonthList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                                                                                                      <!-- 終了日コンボ -->
                                                                                                                      <html:select name="ntp060Form" property="ntp060ToDay" styleId="seleDay" styleClass="combo_style">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060DayList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060DayList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                                                                                                      <input type="button" value="Cal" onclick="wrtCalendar(this.form.seleDay, this.form.seleMonth, this.form.seleYear)" class="calendar_btn">


                                            </td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.61" /></span></td><td class="td_wt">
                                                                                                                      <html:select name="ntp060Form" property="ntp060NgySid" styleClass="select01" onchange="return buttonPush('changeGyomu');" style="width: 150px;">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060GyomuList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060GyomuList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                           </td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.62" /></span></td><td class="td_wt">
                                                                                                                      <html:select name="ntp060Form" property="ntp060NgpSid" styleClass="select01" style="width: 150px;">
                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060ProcessList">
                                                                                                                      <html:optionsCollection name="ntp060Form" property="ntp060ProcessList" value="value" label="label" />
                                                                                                                      </logic:notEmpty>
                                                                                                                      </html:select>
                                           </td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.63" /></span></td><td class="td_wt">
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Mikomi" styleId="ntp060mikomido0" value="0" />&nbsp;<label for="ntp060mikomido0"><span class="text_base7">10%</span></label>&nbsp;
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Mikomi" styleId="ntp060mikomido1" value="1" />&nbsp;<label for="ntp060mikomido1"><span class="text_base7">30%</span></label>&nbsp;
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Mikomi" styleId="ntp060mikomido2" value="2" />&nbsp;<label for="ntp060mikomido2"><span class="text_base7">50%</span></label>&nbsp;<br>
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Mikomi" styleId="ntp060mikomido3" value="3" />&nbsp;<label for="ntp060mikomido3"><span class="text_base7">70%</span></label>&nbsp;
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Mikomi" styleId="ntp060mikomido4" value="4" />&nbsp;<label for="ntp060mikomido4"><span class="text_base7">100%</span></label>
                                           </td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.53" /></span></td><td class="td_wt">
                                                                                                                      <html:text name="ntp060Form" property="ntp060NanKinMitumori" maxlength="9" style="text-align:right; width:113px; margin-left:4px;" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span><br>
                                                                                                                      <html:radio name="ntp060Form" property="ntp060NanKinMitumoriKbn" styleId="ntp060NanKinMitumoriKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp060NanKinMitumoriKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
                                                                                                                      <html:radio name="ntp060Form" property="ntp060NanKinMitumoriKbn" styleId="ntp060NanKinMitumoriKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp060NanKinMitumoriKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.54" /></span></td><td class="td_wt">
                                                                                                                      <html:text name="ntp060Form" property="ntp060NanKinJutyu" maxlength="9" style="text-align:right; width:113px; margin-left:4px;" />&nbsp;<span class="text_base7"><gsmsg:write key="project.103" /></span><br>
                                                                                                                      <html:radio name="ntp060Form" property="ntp060NanKinJutyuKbn" styleId="ntp060NanKinJutyuKbn1" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_MORE) %>" /><span class="text_base7"><label for="ntp060NanKinJutyuKbn1"><gsmsg:write key="ntp.66" /></label></span>&nbsp;
                                                                                                                      <html:radio name="ntp060Form" property="ntp060NanKinJutyuKbn" styleId="ntp060NanKinJutyuKbn2" value="<%= String.valueOf(jp.groupsession.v2.ntp.ntp060.Ntp060Biz.PRICE_LESS) %>" /><span class="text_base7"><label for="ntp060NanKinJutyuKbn2"><gsmsg:write key="ntp.67" /></label></span>&nbsp;
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.64" /></span></td><td class="td_wt">
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Syodan" styleId="ntp060syodan0" value="0" />&nbsp;<label for="ntp060syodan0"><span class="text_base7"><gsmsg:write key="ntp.68" /></span></label>&nbsp;
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Syodan" styleId="ntp060syodan1" value="1" />&nbsp;<label for="ntp060syodan1"><span class="text_base7"><gsmsg:write key="ntp.69" /></span></label>&nbsp;
                                                                                                                      <html:multibox name="ntp060Form" property="ntp060Syodan" styleId="ntp060syodan2" value="2" />&nbsp;<label for="ntp060syodan2"><span class="text_base7"><gsmsg:write key="ntp.7" /></span></label></td>
                                         </tr>
                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center"><span class="text_anken_search"><gsmsg:write key="ntp.65" /></span></td><td class="td_wt">
<logic:notEmpty name="ntp060Form" property="ntp060ContactList">
<logic:iterate id="ncnMdl" name="ntp060Form" property="ntp060ContactList" indexId="ncnidx">
<bean:define  id="ncnVal" name="ncnMdl" property="value" />
<% String idFor = "ntp060NcnSid" + String.valueOf(ncnidx); %>
<html:radio name="ntp060Form" property="ntp060NcnSid" styleId="<%= idFor %>"  value="<%= String.valueOf(ncnVal) %>" />
<span class="text_base7"><label for="<%= idFor %>">
<bean:write  name="ncnMdl" property="label" />
</label>
</span>&nbsp;
<% if (ncnidx != 0 && ncnidx % 2 == 0) { %><br><% } %>
</logic:iterate>
</logic:notEmpty>
                                           </td>
                                         </tr>


                                         <tr>
                                           <td class="table_bg_A5B4E1" align="center" nowrap="nowrap"><span class="text_anken_search"><gsmsg:write key="ntp.71" /></span></td><td class="td_wt">

                                                                                                                      <logic:notEmpty name="ntp060Form" property="ntp060StateList">
                                                                                                                      <logic:iterate id="stateMdl" name="ntp060Form" property="ntp060StateList" indexId="stateidx">
                                                                                                                      <bean:define  id="ntp060StateVal" name="stateMdl" property="value" />
                                                                                                                      <% String idForState = "ntp060State" + String.valueOf(stateidx); %>
                                                                                                                      <html:radio name="ntp060Form" property="ntp060State" styleId="<%= idForState %>"  value="<%= String.valueOf(ntp060StateVal) %>" /><span class="text_base7"><label for="<%= idForState %>"><bean:write  name="stateMdl" property="label" /></label></span>&nbsp;</wbr>
                                                                                                                      </logic:iterate>
                                                                                                                      </logic:notEmpty>
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

</table>






























</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>