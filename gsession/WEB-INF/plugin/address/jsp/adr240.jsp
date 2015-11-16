<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>[Group Session] <gsmsg:write key="cmn.select.company" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<logic:notEqual name="adr240Form" property="adr240ProAddErrFlg" value="1">
  <body class="body_03">
</logic:notEqual>

<logic:equal name="adr240Form" property="adr240ProAddErrFlg" value="1">
  <body class="body_03" onload="selectCompany();">
</logic:equal>

<html:form action="/address/adr240">
<input type="hidden" name="CMD" value="">

<html:hidden property="adr240Index" />
<html:hidden property="adr240Str" />
<html:hidden property="adr240page" />
<html:hidden property="adr240parentPageId" />
<html:hidden property="adr240CompanySid" />
<html:hidden property="adr240CompanyBaseSid" />
<html:hidden property="adr240CompanyCode" />
<html:hidden property="adr240CompanyName" />
<html:hidden property="adr240mode" />
<html:hidden property="adr240ProAddFlg" />
<html:hidden property="adr240ProAddErrFlg" />
<html:hidden property="adr240RowNumber" />
<html:hidden property="adr240PrsMode" />
<html:hidden property="adr240SearchMode" />

<html:hidden property="adr240svCode" />
<html:hidden property="adr240svCoName" />
<html:hidden property="adr240svCoNameKn" />
<html:hidden property="adr240svCoBaseName" />
<html:hidden property="adr240svAtiSid" />
<html:hidden property="adr240svTdfk" />
<html:hidden property="adr240svBiko" />

<input type="hidden" name="adr240tanto" value="0">

<table width="100%">
<tr>
<td width="100%" align="center">


  <table width="800" id="companyTable">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg">&nbsp;</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

<bean:define id="procMode" name="adr240Form" property="adr240SearchMode" />
<% int sMode = ((Integer) procMode).intValue(); %>

<% if (sMode == 0) { %>


    <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td class="tab_bg_image_1_on" nowrap>
    <div class="tab_text_area">
    <a href="javascript:change240Tab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_50) %>');"><gsmsg:write key="address.adr100.1" /></a>
    </div>
    </td>

    <td class="tab_space" nowrap>&nbsp;</td>
    <td class="tab_bg_image_1_off" nowrap>
    <div class="tab_text_area_right">
    <a href="javascript:change240Tab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_DETAIL) %>');"><gsmsg:write key="cmn.advanced.search" /></a>
    </div>
    </td>

    <td class="tab_space" nowrap>&nbsp;</td>
    <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap></td>
    <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>

<div id="select_index">

<%--
    <table>
    <tr class="select_tr">
      <td>
        <input class="btn_label_n1" value="ラベル選択" id="labelPopBtn" type="button">
        <div id="labelArea"><div>

          <logic:notEmpty name="adr240Form" property="selectedLabelList">
            <logic:iterate id="lblData" name="adr240Form" property="selectedLabelList">
                <input type="hidden" name="adr240searchLabel" id="lbl_<bean:write name="lblData" property="albSid" />" value="<bean:write name="lblData" property="albSid" />" />
            </logic:iterate>
          </logic:notEmpty>

        </div></div>
      </td>
    </tr>
    </table>
--%>


    <table width="0%" class="tl0" border="0" cellpadding="0">

    <tr class="select_tr">

      <logic:equal name="adr240Form" property="adr240Index" value="-1">
        <td class="back_select_index2"><span class="select_font_bbt_all"><gsmsg:write key="cmn.all" /></span></td>
      </logic:equal>
      <logic:notEqual name="adr240Form" property="adr240Index" value="-1">
        <td class="back_index2">
        <a href="javascript:void(0);" onClick="return selectLine('-1');">
        <span class="select_font_blue_all"><gsmsg:write key="cmn.all" /></span>
        </a>
        </td>
      </logic:notEqual>

    <logic:notEmpty name="adr240Form" property="adr240IndexList">
    <logic:iterate id="lineLabel" name="adr240Form" property="adr240IndexList">

      <logic:equal name="lineLabel" property="value" value="0">
        <td class="back_index">
        <a href="javascript:void(0);" onClick="return selectLine('<bean:write name="lineLabel" property="label" />');">
        <span class="select_font_blue"><bean:write name="lineLabel" property="label" /></span>
        </a>
        </td>
      </logic:equal>

      <logic:equal name="lineLabel" property="value" value="1">
        <td class="back_index"><span class="select_font_black"><bean:write name="lineLabel" property="label" /></span></td>
      </logic:equal>

      <logic:equal name="lineLabel" property="value" value="2">
        <td class="back_select_index"><span class="select_font_blue_bbt"><bean:write name="lineLabel" property="label" /></span></td>
      </logic:equal>

    </logic:iterate>
    </logic:notEmpty>


      <logic:equal name="adr240Form" property="adr240Index" value="99">
        <td style="width:90px!important;" class="back_select_index" nowrap><span class="select_font_bbt_company"><gsmsg:write key="address.adr240.1" /></span></td>
      </logic:equal>
      <logic:notEqual name="adr240Form" property="adr240Index" value="99">
        <td style="width:90px!important;" class="back_index" nowrap>
        <a href="javascript:void(0);" onClick="return selectLine('99');">
        <span class="select_font_blue_company"><gsmsg:write key="address.adr240.1" /></span>
        </a>
        </td>
      </logic:notEqual>

    </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="0" class="tl0" border="0" cellpadding="0">
    <tr class="select_detail_tr">

      <logic:equal name="adr240Form" property="adr240Str" value="-1">
        <td class="back_select_index_detail2"><span class="select_font_bbt_all"><gsmsg:write key="cmn.all" /></span></td>
      </logic:equal>
      <logic:notEqual name="adr240Form" property="adr240Str" value="-1">
        <td class="back_index_detail">
        <a href="javascript:void(0);" onClick="return selectStr('-1');"><span class="select_font_blue_all"><gsmsg:write key="cmn.all" /></span></a>
        </td>
      </logic:notEqual>

    <logic:notEmpty name="adr240Form" property="adr240StrList">
    <logic:iterate id="strLabel" name="adr240Form" property="adr240StrList">

      <logic:equal name="strLabel" property="value" value="0">
        <td class="back_index_detail">
        <a href="javascript:void(0);" onClick="return selectStr('<bean:write name="strLabel" property="label" />');">
        <span class="select_font_blue"><bean:write name="strLabel" property="label" /></span>
        </a>
        </td>
      </logic:equal>

      <logic:equal name="strLabel" property="value" value="1">
        <td class="back_index_detail"><span class="select_font_black"><bean:write name="strLabel" property="label" /></span></td>
      </logic:equal>

      <logic:equal name="strLabel" property="value" value="2">
        <td class="back_select_index_detail"><span class="select_font_blue_bbt"><bean:write name="strLabel" property="label" /></span></td>
      </logic:equal>

    </logic:iterate>
    </logic:notEmpty>
    </tr>
    </table>
</div>



<% } else if (sMode == 1) { %><!-- <gsmsg:write key="adr.advanced.search.mode" /> -->






    <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td class="tab_bg_image_1_off" nowrap>
    <div class="tab_text_area_right">
    <a href="javascript:change240Tab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_50) %>');"><gsmsg:write key="address.adr100.1" /></a>
    </div>
    </td>

    <td class="tab_space" nowrap>&nbsp;</td>
    <td class="tab_bg_image_1_on" nowrap>
    <div class="tab_text_area">
    <a href="javascript:change240Tab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_DETAIL) %>');"><gsmsg:write key="cmn.advanced.search" /></a>
    </div>
    </td>

    <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap></td>
    <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>

<div id="select_index">

    <table width="100%" class="tl0 add_tbl_base">

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.7" /></th>
    <td class="td_type1">
      <html:text property="adr240code" maxlength="20" style="width:275px;" />
    </td>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.11" /></th>
    <td width="40%" class="td_type1">
      <logic:notEmpty name="adr240Form" property="atiCmbList">
        <html:select name="adr240Form" property="adr240atiSid">
          <html:optionsCollection name="adr240Form" property="atiCmbList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="cmn.company.name" /></th>
    <td width="40%" class="td_type1">
      <html:text property="adr240coName" maxlength="50" style="width:275px;" />
    </td>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="cmn.prefectures" /></th>
    <td width="40%" class="td_type1">
        <logic:notEmpty name="adr240Form" property="tdfkCmbList">
          <html:select name="adr240Form" property="adr240tdfk">
            <html:optionsCollection name="adr240Form" property="tdfkCmbList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.9" /></th>
    <td class="td_type1"><html:text property="adr240coNameKn" maxlength="100" style="width:275px;" /></td>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="cmn.memo" /></th>
    <td class="td_type1"><html:text property="adr240biko" maxlength="1000" style="width:275px;" /></td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.10" /></th>
    <td class="td_type1" colspan="3"><html:text property="adr240coBaseName" maxlength="50" style="width:275px;" /></td>
    </tr>
    </table>

    <table cellpadding="5" width="100%">
    <tr>
    <td align="center" width="100%">
      <span><input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush('search');"></span>
    </td>
    </tr>
    </table>

</div>



<% }  %>

  <table>
  <logic:messagesPresent message="false">
  <tr>
  <td>
    <html:errors />
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>
  </logic:messagesPresent>
  </table>

    <logic:notEmpty name="adr240Form" property="adr240CompanyList">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <% String[] tdClass = {"td_type1", "td_type_usr"}; %>

    <logic:notEmpty  name="adr240Form" property="pageCmbList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <td align="right">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" onClick="return buttonPush('prevPage');" style="cursor:pointer;">
      <html:select name="adr240Form" property="adr240pageTop" styleClass="text_i" onchange="buttonPush('changePageTop');">
        <html:optionsCollection name="adr240Form" property="pageCmbList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="return buttonPush('nextPage');" style="cursor:pointer;">
    </td>
    </tr>
    </table>
    </logic:notEmpty>


<table width="100%" class="tl_companyPopup">
<tr>
<th width="60%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="address.118" /></span></th>
<logic:equal name="adr240Form" property="adr240mode" value="0">
<th width="40%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.staff" /></span></th>
</logic:equal>

<tr>
<td valign="top">
    <table width="100%" class="tl0 tl_companyPopup" border="0" cellpadding="0">
    <logic:iterate id="companyModel" name="adr240Form" property="adr240CompanyList" indexId="idx">

    <tr>
      <bean:define id="coData" name="companyModel" type="jp.groupsession.v2.adr.adr240.model.Adr240Model" />
      <% String companyId = String.valueOf(coData.getAcoSid()) + ":" + String.valueOf(coData.getAbaSid()); %>
      <% String companyId2 = String.valueOf(coData.getAcoSid()) + "_" + String.valueOf(coData.getAbaSid()); %>
      <logic:equal name="adr240Form" property="adr240mode" value="0">

        <td width="5%" class="<%= tdClass[idx.intValue() % 2] %>" style="border-left:none;" align="center">

          <logic:notEqual name="adr240Form" property="adr240ProAddFlg" value="1">
            <input type="radio" name="adr240selectCompany" value="<%= companyId %>" onclick="selectCompany();">
          </logic:notEqual>

          <logic:equal name="adr240Form" property="adr240ProAddFlg" value="1">
            <html:radio name="adr240Form" property="adr240selectCompany" value="<%= companyId %>" onclick="selectCompany();" />
          </logic:equal>
          <input type="hidden" name="adr240selectCompanyCode<%= companyId2 %>" value="<bean:write name="companyModel" property="acoCode" />">
          <input type="hidden" name="adr240selectCompanyName<%= companyId %>" value="<bean:write name="companyModel" property="acoName" /> <bean:write name="companyModel" property="abaName" />">
        </td>

        <td class="<%= tdClass[idx.intValue() % 2] %>" style="border-right:none;cursor:pointer;" onClick="clickCompanyName(<%= String.valueOf(coData.getAcoSid()) %>, <%= String.valueOf(coData.getAbaSid()) %>);">
          <bean:write name="companyModel" property="acoName" /> <bean:write name="companyModel" property="abaName" />
        </td>
      </logic:equal>

      <logic:equal name="adr240Form" property="adr240mode" value="1">

      <logic:equal name="adr240Form" property="adr240selMode" value="1">
        <td class="comp_select_area" id="<%= companyId %>" style="border-right:none;cursor:pointer;" onClick="return setParent('<%= companyId %>');">
          <input type="hidden" name="adr240selectCompanyCode<%= companyId2 %>" value="<bean:write name="companyModel" property="acoCode" />">
          <input type="hidden" name="adr240selectCompanyName<%= companyId %>" value="<bean:write name="companyModel" property="acoName" /> <bean:write name="companyModel" property="abaName" />">
          <bean:write name="companyModel" property="acoName" /> <bean:write name="companyModel" property="abaName" />
        </td>
      </logic:equal>
      <logic:notEqual name="adr240Form" property="adr240selMode" value="1">
        <td class="comp_select_area" style="border-right:none;cursor:pointer;" onClick="clickCompanyName2('<%= companyId %>');">
          <input type="hidden" name="adr240selectCompanyCode<%= companyId2 %>" value="<bean:write name="companyModel" property="acoCode" />">
          <input type="hidden" name="adr240selectCompanyName<%= companyId %>" value="<bean:write name="companyModel" property="acoName" /> <bean:write name="companyModel" property="abaName" />">
          <bean:write name="companyModel" property="acoName" /> <bean:write name="companyModel" property="abaName" />
        </td>
      </logic:notEqual>


      </logic:equal>

    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>

</td>

<logic:equal name="adr240Form" property="adr240mode" value="0">
<td valign="top">
<div id="tantoArea">
</div>
</td>
</logic:equal>
</tr>
</table>

    <logic:notEmpty  name="adr240Form" property="pageCmbList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <td align="right">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('prevPage');" style="cursor:pointer;">
      <html:select name="adr240Form" property="adr240pageBottom" styleClass="text_i" onchange="buttonPush('changePageBottom');">
        <html:optionsCollection name="adr240Form" property="pageCmbList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('nextPage');" style="cursor:pointer;">
    </td>
    </tr>
    </table>
    </logic:notEmpty>


    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="0">
    <tr>
      <td align="center">
        <logic:equal name="adr240Form" property="adr240mode" value="0">
          <logic:equal name="adr240Form" property="adr240PrsMode" value="0">
            <input type="button" value="<gsmsg:write key="cmn.select" />" class="btn_base1" onClick="return parentReload();">
          </logic:equal>
          <logic:notEqual name="adr240Form" property="adr240PrsMode" value="0">
            <input type="button" value="<gsmsg:write key="cmn.select" />" class="btn_base1" onClick="return setParent();">
          </logic:notEqual>
          &nbsp;&nbsp;
        </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="return window.close();">
      </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>
</td>
</tr>
</table>









<%--
<div id="labelPop" title="ラベル選択" style="display:none;">
  <p>

<div style="border:solid 1px;border-color:#9a9a9a;height:300px;overflow-y: scroll">
    <table class="table_td_border2" width="100%" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="table_bg_7D91BD">

        <!-- チェックボックス -->
        <th width="7%" align="center" nowrap="nowrap">
         <span class="text_tlw">

        </th>

        <!-- 目標名 -->
        <th width="83%" nowrap="nowrap">
          <span class="text_tlw">ラベル</span>
        </th>


      </tr>
      </thead>


      <tbody>
        <logic:notEmpty name="adr240Form" property="selectLabelList">
          <logic:iterate id="labelData" name="adr240Form" property="selectLabelList">
            <bean:define id="albsid" name="labelData" property="albSid" />
            <tr class="td_type1" style="font-size:90%;cursor:pointer;" id="tr_<%= albsid.toString() %>" onclick="clickLabelName(3, <%= albsid.toString() %>);">

              <!-- チェックボックス -->
              <td width="7%">
                <input type="checkbox" name="poplabel" value="<%= albsid.toString() %>" onclick="clickMulti();">
              </td>

              <td width="83%" id="td_name_<%= albsid.toString() %>" nowrap="nowrap">
                <bean:write name="labelData" property="albName" />
              </td>

            </tr>
          </logic:iterate>
        </logic:notEmpty>

      </tbody>
    </table>
    </div>

  </p>
</div>
--%>
</html:form>
</body>
</html:html>