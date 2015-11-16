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
<title>[Group Session] <gsmsg:write key="address.102" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adrcommon.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr100.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03">

<html:form action="/address/adr100">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>
<logic:notEmpty name="adr100Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr100Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr100Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr100Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr100Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr100Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr100Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr100Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr100Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr100Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020init" />
<html:hidden property="adr020ProcMode" />
<html:hidden property="adr020unameSei" />
<html:hidden property="adr020unameMei" />
<html:hidden property="adr020unameSeiKn" />
<html:hidden property="adr020unameMeiKn" />
<html:hidden property="adr020selectCompany" />
<html:hidden property="adr020selectCompanyBase" />
<html:hidden property="adr020syozoku" />
<html:hidden property="adr020position" />
<html:hidden property="adr020mail1" />
<html:hidden property="adr020mail1Comment" />
<html:hidden property="adr020mail2" />
<html:hidden property="adr020mail2Comment" />
<html:hidden property="adr020mail3" />
<html:hidden property="adr020mail3Comment" />
<html:hidden property="adr020tel1" />
<html:hidden property="adr020tel1Nai" />
<html:hidden property="adr020tel1Comment" />
<html:hidden property="adr020tel2" />
<html:hidden property="adr020tel2Nai" />
<html:hidden property="adr020tel2Comment" />
<html:hidden property="adr020tel3" />
<html:hidden property="adr020tel3Nai" />
<html:hidden property="adr020tel3Comment" />
<html:hidden property="adr020fax1" />
<html:hidden property="adr020fax1Comment" />
<html:hidden property="adr020fax2" />
<html:hidden property="adr020fax2Comment" />
<html:hidden property="adr020fax3" />
<html:hidden property="adr020fax3Comment" />
<html:hidden property="adr020postno1" />
<html:hidden property="adr020postno2" />
<html:hidden property="adr020tdfk" />
<html:hidden property="adr020address1" />
<html:hidden property="adr020address2" />
<html:hidden property="adr020biko" />

<logic:notEmpty name="adr100Form" property="adr020tantoList">
<logic:iterate id="tantoList" name="adr100Form" property="adr020tantoList">
  <input type="hidden" name="adr020tantoList" value="<bean:write name="tantoList" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr100Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr100Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr100Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr100Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020tantoGroup" />

<logic:notEmpty name="adr100Form" property="adr020label">
<logic:iterate id="label" name="adr100Form" property="adr020label">
  <input type="hidden" name="adr020label" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitView" />

<logic:notEmpty name="adr100Form" property="adr020permitViewGroup">
<logic:iterate id="permitViewGroup" name="adr100Form" property="adr020permitViewGroup">
  <input type="hidden" name="adr020permitViewGroup" value="<bean:write name="permitViewGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr100Form" property="adr020permitViewUser">
<logic:iterate id="permitViewUser" name="adr100Form" property="adr020permitViewUser">
  <input type="hidden" name="adr020permitViewUser" value="<bean:write name="permitViewUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitViewUserGroup" />
<html:hidden property="adr020permitEdit" />

<logic:notEmpty name="adr100Form" property="adr020permitEditGroup">
<logic:iterate id="permitEditGroup" name="adr100Form" property="adr020permitEditGroup">
  <input type="hidden" name="adr020permitEditGroup" value="<bean:write name="permitEditGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr100Form" property="adr020permitEditUser">
<logic:iterate id="permitEditUser" name="adr100Form" property="adr020permitEditUser">
  <input type="hidden" name="adr020permitEditUser" value="<bean:write name="permitEditUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitEditUserGroup" />
<html:hidden property="adr100backFlg" />

<input type="hidden" name="adr110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_ADD) %>">
<input type="hidden" name="adr110editAcoSid" value="">
<html:hidden property="adr100searchFlg" />
<html:hidden property="adr100page" />
<html:hidden property="adr100svCode" />
<html:hidden property="adr100svCoName" />
<html:hidden property="adr100svCoNameKn" />
<html:hidden property="adr100svCoBaseName" />
<html:hidden property="adr100svAtiSid" />
<html:hidden property="adr100svTdfk" />
<html:hidden property="adr100svBiko" />

<html:hidden property="adr100SortKey" />
<html:hidden property="adr100OrderKey" />
<html:hidden property="adr100SearchKana" />
<html:hidden property="adr100mode" />
<bean:define id="procMode" name="adr100Form" property="adr100mode" />
<% int sMode = ((Integer) procMode).intValue(); %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="90%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.102" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <logic:notEqual name="adr100Form" property="adr100backFlg" value="1">
      <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addCompany('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_ADD) %>');">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('importCompany');">
      <logic:notEmpty name="adr100Form" property="companyList">
      <input type="button" name="btn_dell" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('deleteCompanies');">
      </logic:notEmpty>
      <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAddressList');">
      </logic:notEqual>
      <logic:equal name="adr100Form" property="adr100backFlg" value="1">
      <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInputAddress');">
      </logic:equal>
    </td>
    </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
  <td>
    <html:errors />
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td>


<% if (sMode == 0) { %>

    <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td class="tab_bg_image_1_on" nowrap>
    <div class="tab_text_area">
    <a href="javascript:changeTab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_50) %>');"><gsmsg:write key="address.adr100.1" /></a>
    </div>
    </td>

    <td class="tab_space" nowrap>&nbsp;</td>
    <td class="tab_bg_image_1_off" nowrap>
    <div class="tab_text_area_right">
    <a href="javascript:changeTab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_DETAIL) %>');"><gsmsg:write key="cmn.advanced.search" /></a>
    </div>
    </td>

    <td class="tab_space" nowrap>&nbsp;</td>
    <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap></td>
    <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td class="tbl_gray2">

    <table width="100%" cellpadding="3" cellspacing="0">
    <tr valign="top">
    <td width="100%" align="center">

      <% java.util.List rowList = new java.util.ArrayList(); %>
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.a"), gsMsg.getMessage(request, "cmn.kana.ka"), gsMsg.getMessage(request, "cmn.kana.sa"), gsMsg.getMessage(request, "cmn.kana.ta"), gsMsg.getMessage(request, "cmn.kana.na"), gsMsg.getMessage(request, "cmn.kana.ha"), gsMsg.getMessage(request, "cmn.kana.ma"), gsMsg.getMessage(request, "cmn.kana.ya"), gsMsg.getMessage(request, "cmn.kana.ra"), gsMsg.getMessage(request, "cmn.kana.wa")}); %>
      <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.i"), gsMsg.getMessage(request, "cmn.kana.ki"), gsMsg.getMessage(request, "cmn.kana.shi"), gsMsg.getMessage(request, "cmn.kana.chi"), gsMsg.getMessage(request, "cmn.kana.ni"), gsMsg.getMessage(request, "cmn.kana.hi"), gsMsg.getMessage(request, "cmn.kana.mi"), "", gsMsg.getMessage(request, "cmn.kana.ri"), gsMsg.getMessage(request, "cmn.kana.wo")}); %>
      <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.u"), gsMsg.getMessage(request, "cmn.kana.ku"), gsMsg.getMessage(request, "cmn.kana.su"), gsMsg.getMessage(request, "cmn.kana.tsu"), gsMsg.getMessage(request, "cmn.kana.nu"), gsMsg.getMessage(request, "cmn.kana.fu"), gsMsg.getMessage(request, "cmn.kana.mu"), gsMsg.getMessage(request, "cmn.kana.yu"), gsMsg.getMessage(request, "cmn.kana.ru"), gsMsg.getMessage(request, "cmn.kana.n")}); %>
      <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.e"), gsMsg.getMessage(request, "cmn.kana.ke"), gsMsg.getMessage(request, "cmn.kana.se"), gsMsg.getMessage(request, "cmn.kana.te"), gsMsg.getMessage(request, "cmn.kana.ne"), gsMsg.getMessage(request, "cmn.kana.he"), gsMsg.getMessage(request, "cmn.kana.me"), "", gsMsg.getMessage(request, "cmn.kana.re"), ""}); %>
      <% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.o"), gsMsg.getMessage(request, "cmn.kana.ko"), gsMsg.getMessage(request, "cmn.kana.so"), gsMsg.getMessage(request, "cmn.kana.to"), gsMsg.getMessage(request, "cmn.kana.no"), gsMsg.getMessage(request, "cmn.kana.ho"), gsMsg.getMessage(request, "cmn.kana.mo"), gsMsg.getMessage(request, "cmn.kana.yo"), gsMsg.getMessage(request, "cmn.kana.ro"), ""}); %>
      <bean:define id="existKanaList" name="adr100Form" property="adr100comNameKanaList" type="java.util.List" />

      <table cellpadding="0" cellspacing="0" width="100%" class="user_search">

      <% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
      <%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
      <tr align="center">
      <%     for (int kanaIndex = 0; kanaIndex < kanaArray.length; kanaIndex++) { %>
      <%         String kana = kanaArray[kanaIndex]; %>
      <%         if (existKanaList.contains(kana)) { %>
      <td><a href="#" onClick="return searchToKana('<%= kana %>');"><span class="user_sakuin_text"><%= kana %></span></a></td>
      <%         } else { %>
      <td><span class="user_base_text2"><%= kana %></span></td>
      <%         } %>
      <%     } %>
      </tr>
      <% } %>

      </table>

    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="tab_bottom_left"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    <td class="tab_bottom_mid" width="100%" align="right" valign="bottom">
    <td width="0%" class="tab_bottom_right"><img src="../common/images/damy.gif" width="10" height="34" border="0" alt=""></td>
    </tr>
    </table>

<% } else if (sMode == 1) { %><!-- <gsmsg:write key="adr.advanced.search.mode" /> -->

    <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td class="tab_bg_image_1_off" nowrap>
    <div class="tab_text_area_right">
    <a href="javascript:changeTab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_50) %>');"><gsmsg:write key="address.adr100.1" /></a>
    </div>
    </td>

    <td class="tab_space" nowrap>&nbsp;</td>
    <td class="tab_bg_image_1_on" nowrap>
    <div class="tab_text_area">
    <a href="javascript:changeTab('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.SEARCH_COMPANY_MODE_DETAIL) %>');"><gsmsg:write key="cmn.advanced.search" /></a>
    </div>
    </td>

    <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap></td>
    <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    </tr>
    </table>

    <table width="100%" class="tl0 add_tbl_base">

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.7" /></th>
    <td class="td_type1">
      <html:text property="adr100code" maxlength="20" style="width:275px;" />
    </td>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.11" /></th>
    <td width="40%" class="td_type1">
      <html:select name="adr100Form" property="adr100atiSid">
        <html:optionsCollection name="adr100Form" property="atiCmbList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="cmn.company.name" /></th>
    <td width="40%" class="td_type1">
      <html:text property="adr100coName" maxlength="50" style="width:275px;" />
    </td>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="cmn.prefectures" /></th>
    <td width="40%" class="td_type1">
      <html:select name="adr100Form" property="adr100tdfk">
        <html:optionsCollection name="adr100Form" property="tdfkCmbList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.9" /></th>
    <td class="td_type1"><html:text property="adr100coNameKn" maxlength="100" style="width:275px;" /></td>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="cmn.memo" /></th>
    <td class="td_type1"><html:text property="adr100biko" maxlength="1000" style="width:275px;" /></td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2 srh_ttl_pad" align="left" nowrap><gsmsg:write key="address.10" /></th>
    <td class="td_type1" colspan="3"><html:text property="adr100coBaseName" maxlength="50" style="width:275px;" /></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="tab_bottom_left"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
    <td class="tab_bottom_mid" width="100%" align="right" valign="bottom">
    <td width="0%" class="tab_bottom_right"><img src="../common/images/damy.gif" width="10" height="34" border="0" alt=""></td>
    </tr>
    </table>


  </td>
  </tr>

  <tr>
  <td width="100%">
    <table cellpadding="5" width="100%">
    <tr>
    <td align="center" width="100%">
      <span align="center"><input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush('search');"></span>
    </td>
    </tr>
    </table>
  </td>
  </tr>

<% }  %>

  <logic:notEmpty name="adr100Form" property="companyList">
  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td align ="right">
      <logic:notEmpty name="adr100Form" property="companyList">
        <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('exportCompany');">
      </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td align="right">
    <logic:notEmpty  name="adr100Form" property="pageCmbList">
      &nbsp;&nbsp;<img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" width="20" class="img_bottom" height="20" onClick="buttonPush('prevPage');" style="cursor:pointer;">
      <html:select name="adr100Form" property="adr100pageTop" styleClass="text_i" onchange="changePage('adr100pageTop');">
        <html:optionsCollection name="adr100Form" property="pageCmbList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" width="20" class="img_bottom" height="20" onClick="buttonPush('nextPage');" style="cursor:pointer;">
    </logic:notEmpty>
    </td>
    </tr>
    </table>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>

      <th class="table_bg_7D91BD" width="3%"><input type="checkbox" name="all_Check" onclick="changeChk();"></input></th>
    <!-- <gsmsg:write key="address.7" /> -->
    <logic:equal name="adr100Form" property="adr100SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>">

      <logic:equal name="adr100Form" property="adr100OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th align="center" class="table_bg_7D91BD" width="12%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="address.7" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="adr100Form" property="adr100OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th align="center" class="table_bg_7D91BD" width="12%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="address.7" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="adr100Form" property="adr100SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>">
      <th align="center" class="table_bg_7D91BD" width="12%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_CODE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="address.7" /></span></a></th>
    </logic:notEqual>

    <!-- <gsmsg:write key="cmn.company.name" /> -->
    <logic:equal name="adr100Form" property="adr100SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>">

      <logic:equal name="adr100Form" property="adr100OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th align="center" class="table_bg_7D91BD" width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.company.name" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="adr100Form" property="adr100OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th align="center" class="table_bg_7D91BD" width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.company.name" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="adr100Form" property="adr100SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>">
      <th align="center" class="table_bg_7D91BD" width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.company.name" /></span></a></th>
    </logic:notEqual>

    <!-- <gsmsg:write key="address.11" /> -->
    <th align="center" class="table_bg_7D91BD" width="20%"><span class="text_tlw"><gsmsg:write key="address.11" /></span></th>

    <!-- <gsmsg:write key="cmn.memo" /> -->
    <logic:equal name="adr100Form" property="adr100SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_BIKO) %>">

      <logic:equal name="adr100Form" property="adr100OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th align="center" class="table_bg_7D91BD" width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_BIKO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.memo" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="adr100Form" property="adr100OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th align="center" class="table_bg_7D91BD" width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_BIKO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.memo" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="adr100Form" property="adr100SortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_BIKO) %>">
      <th align="center" class="table_bg_7D91BD" width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.COMPANY_SORT_BIKO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></a></th>
    </logic:notEqual>

    </tr>

    <% String[] trClass = {"td_type1", "td_type_usr"}; %>
    <logic:iterate id="companyData" name="adr100Form" property="companyList" indexId="idx">
    <tr class="<%= trClass[idx.intValue() % 2] %>">

    <td align="center"><html:multibox name="adr100Form" property="adr100SelectCom"><bean:write name="companyData" property="acoSid" /></html:multibox></td>
    <td align="left"><bean:write name="companyData" property="companyCode" /></td>

    <logic:equal name="adr100Form" property="adr100backFlg" value="1">
    <td align="left" nowrap><a href="#" onClick="return selectCompany('<bean:write name="companyData" property="acoSid" />');"><span class="text_link"><bean:write name="companyData" property="companyName" /></span></a></td>
    </logic:equal>
    <logic:notEqual name="adr100Form" property="adr100backFlg" value="1">
    <td align="left" nowrap><a href="#" onClick="return editCompany('<bean:write name="companyData" property="acoSid" />', '<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>');"><span class="text_link"><bean:write name="companyData" property="companyName" /></span></a></td>
    </logic:notEqual>
    <td align="left"><bean:write name="companyData" property="industryName" /></td>
    <td align="left"><bean:write name="companyData" property="companyBiko" /></td>
    </tr>
    </logic:iterate>

    </table>

    <logic:notEmpty  name="adr100Form" property="pageCmbList">
    <table width="100%" cellpadding="5" cellspacing="0">
    <td align="right">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('prevPage');" style="cursor:pointer;">
      <html:select name="adr100Form" property="adr100pageBottom" styleClass="text_i" onchange="changePage('adr100pageBottom');">
        <html:optionsCollection name="adr100Form" property="pageCmbList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('nextPage');" style="cursor:pointer;">
    </td>
    </tr>
    </table>
    </logic:notEmpty>

  </td>
  </tr>
  </logic:notEmpty>

  </table>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>