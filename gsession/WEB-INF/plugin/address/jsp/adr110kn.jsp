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
<title>[Group Session] <gsmsg:write key="address.adr110kn.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr110kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<html:form action="/address/adr110kn">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>
<logic:notEmpty name="adr110knForm" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr110knForm" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110knForm" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr110knForm" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110knForm" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr110knForm" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110knForm" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr110knForm" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110knForm" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr110knForm" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020webmail" />
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

<logic:notEmpty name="adr110knForm" property="adr020tantoList">
<logic:iterate id="tantoList" name="adr110knForm" property="adr020tantoList">
  <input type="hidden" name="adr020tantoList" value="<bean:write name="tantoList" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110knForm" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr110knForm" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110knForm" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr110knForm" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020tantoGroup" />

<logic:notEmpty name="adr110knForm" property="adr020label">
<logic:iterate id="label" name="adr110knForm" property="adr020label">
  <input type="hidden" name="adr020label" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitView" />

<logic:notEmpty name="adr110knForm" property="adr020permitViewGroup">
<logic:iterate id="permitViewGroup" name="adr110knForm" property="adr020permitViewGroup">
  <input type="hidden" name="adr020permitViewGroup" value="<bean:write name="permitViewGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110knForm" property="adr020permitViewUser">
<logic:iterate id="permitViewUser" name="adr110knForm" property="adr020permitViewUser">
  <input type="hidden" name="adr020permitViewUser" value="<bean:write name="permitViewUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitViewUserGroup" />
<html:hidden property="adr020permitEdit" />

<logic:notEmpty name="adr110knForm" property="adr020permitEditGroup">
<logic:iterate id="permitEditGroup" name="adr110knForm" property="adr020permitEditGroup">
  <input type="hidden" name="adr020permitEditGroup" value="<bean:write name="permitEditGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110knForm" property="adr020permitEditUser">
<logic:iterate id="permitEditUser" name="adr110knForm" property="adr020permitEditUser">
  <input type="hidden" name="adr020permitEditUser" value="<bean:write name="permitEditUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitEditUserGroup" />
<html:hidden property="adr100backFlg" />

<html:hidden property="adr110ProcMode" />
<html:hidden property="adr110editAcoSid" />
<html:hidden property="adr110initFlg" />
<html:hidden property="adr100searchFlg" />
<html:hidden property="adr100page" />
<html:hidden property="adr100pageTop" />
<html:hidden property="adr100pageBottom" />
<html:hidden property="adr100code" />
<html:hidden property="adr100coName" />
<html:hidden property="adr100coNameKn" />
<html:hidden property="adr100coBaseName" />
<html:hidden property="adr100atiSid" />
<html:hidden property="adr100tdfk" />
<html:hidden property="adr100biko" />
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

<html:hidden property="adr110coCode" />
<html:hidden property="adr110coName" />
<html:hidden property="adr110coNameKn" />

<logic:notEmpty name="adr110knForm" property="adr110atiList">
<logic:iterate id="atiSid" name="adr110knForm" property="adr110atiList">
<input type="hidden" name="adr110atiList" value="<bean:write name="atiSid" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr110url" />
<html:hidden property="adr110biko" />
<html:hidden property="adr110abaType" />
<html:hidden property="adr110abaName" />
<html:hidden property="adr110abaPostno1" />
<html:hidden property="adr110abaPostno2" />
<html:hidden property="adr110abaTdfk" />
<html:hidden property="adr110abaAddress1" />
<html:hidden property="adr110abaAddress2" />
<html:hidden property="adr110abaBiko" />

<logic:notEmpty name="adr110knForm" property="abaListToList">
<logic:iterate id="abaData" name="adr110knForm" property="abaListToList" indexId="idx">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaIndex" value="<bean:write name="abaData" property="adr110abaIndex" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaSidDetail" value="<bean:write name="abaData" property="adr110abaSidDetail" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaTypeDetail" value="<bean:write name="abaData" property="adr110abaTypeDetail" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaName" value="<bean:write name="abaData" property="adr110abaName" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaPostno1" value="<bean:write name="abaData" property="adr110abaPostno1" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaPostno2" value="<bean:write name="abaData" property="adr110abaPostno2" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaTdfk" value="<bean:write name="abaData" property="adr110abaTdfk" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaAddress1" value="<bean:write name="abaData" property="adr110abaAddress1" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaAddress2" value="<bean:write name="abaData" property="adr110abaAddress2" />">
<input type="hidden" name="abaList[<%= String.valueOf(idx.intValue()) %>].adr110abaBiko" value="<bean:write name="abaData" property="adr110abaBiko" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden name="adr110knForm" property="adr110ProcMode" />
<html:hidden name="adr110knForm" property="adr110editAcoSid" />
<html:hidden name="adr110knForm" property="adr110BackId" />

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="80%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left" colspan="3">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr110kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

      <logic:equal name="adr110knForm" property="adr100backFlg" value="3">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decisionCompany');">
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="backInput('backInputCompany');">
      </logic:equal>

      <logic:notEqual name="adr110knForm" property="adr100backFlg" value="3">

      <logic:notEqual name="adr110knForm" property="adr100backFlg" value="2">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decisionCompany');">
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInputCompany');">
      </logic:notEqual>

      <logic:equal name="adr110knForm" property="adr100backFlg" value="2">
        <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.fixed" />" onClick="return editCompany('<bean:write name="adr110knForm" property="adr110editAcoSid" />', '<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>');">
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAdrList');">
      </logic:equal>

      </logic:notEqual>

      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>

    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

  </td>
  </tr>
  <tr>
  <logic:equal name="adr110knForm" property="adr100backFlg" value="2"><td width="50%" valign="top"></logic:equal>
  <logic:notEqual name="adr110knForm" property="adr100backFlg" value="2"><td width="50%" valign="top" colspan="3"></logic:notEqual>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <!--  <gsmsg:write key="address.7" /> -->
    <tr>
    <td width="30%" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.7" /></span>
    </td>
    <td width="70%" valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr110knForm" property="adr110coCode" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.company.name" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr110knForm" property="adr110coName" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.company.name" /><gsmsg:write key="cmn.kana.2" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" />(<gsmsg:write key="cmn.kana" />)</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr110knForm" property="adr110coNameKn" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="address.103" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.103" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:notEmpty name="adr110knForm" property="adr110knViewAtiList">
      <span class="text_base">
      <logic:iterate id="atiName" name="adr110knForm" property="adr110knViewAtiList">
      <bean:write name="atiName" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- URL -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1">URL</span>
    </td>
    <td valign="middle" align="left" class="td_wt"><a href="<bean:write name="adr110knForm" property="adr110url" />" target="__blank"><span class="text_p"><bean:write name="adr110knForm" property="adr110url" /></span></a></td>
    </tr>

    <!--  <gsmsg:write key="cmn.memo" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr110knForm" property="adr110knViewBiko" filter="false" /></span>
    </td>
    </tr>
    </table>
  </td>

  <logic:equal name="adr110knForm" property="adr100backFlg" value="2">
  <td width="0%"><IMG SRC="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>

  <td width="50%" valign="top">
    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th class="address_info_bg" width="100%" nowrap colspan="3" align="left">
    <img src="../address/images/parson_icon.gif" alt="<gsmsg:write key="address.106" />" class="img_bottom">
    <span class="text_bb1"><gsmsg:write key="address.adr110kn.3" />&nbsp;&nbsp;（<bean:write name="adr110knForm" property="adr110knAdrCount" /><gsmsg:write key="cmn.number" />）</span></th>
    </tr>

    <logic:notEmpty name="adr110knForm" property="adr110knAdrInfList" >
    <tr>
    <th class="address_info_bg" width="40%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.name" /></span></th>
    <th align="center" class="address_info_bg" width="40%"><span class="text_bb1"><gsmsg:write key="address.10" /></span></th>
    <th align="center" class="address_info_bg" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.post" /></span></th>
    </tr>

    <% String[] tdColors = {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="adrInfo" name="adr110knForm" property="adr110knAdrInfList" indexId="idx">

    <tr align="center">
    <td align="left" class="<%= tdColors[idx.intValue() % 2] %>" nowrap><span class="text_link">
      <a href="#" onClick="return editAddress('<bean:write name="adrInfo" property="adrSid" />');">
      <bean:write name="adrInfo" property="userName" /></a></span>
    </td>
    <td align="left" class="<%= tdColors[idx.intValue() % 2] %>"><bean:write name="adrInfo" property="companyBaseName" /></td>
    <td align="left" class="<%= tdColors[idx.intValue() % 2] %>"><bean:write name="adrInfo" property="positionName" /></td>
    </tr>
    </logic:iterate>

    </logic:notEmpty>
    </table>

  </td>
  </logic:equal>

  </tr>

  <logic:notEmpty name="adr110knForm" property="abaListToList">
  <tr>

  <logic:equal name="adr110knForm" property="adr100backFlg" value="2"><td colspan="1"></logic:equal>
  <logic:notEqual name="adr110knForm" property="adr100backFlg" value="2"><td colspan="3"></logic:notEqual>

    <table width="100%" cellpadding="5" cellspacing="0" class="tl_u2">

    <tr>
    <td colspan="2"><IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0"></td>
    </tr>
    <logic:iterate id="abaData" name="adr110knForm" property="abaListToList">
    <tr>
    <th colspan="4" class="address_info_bg" align="left">
    <img src="../address/images/parson_icon.gif" alt="<gsmsg:write key="address.106" />" class="img_bottom">
    <span class="adr_text_bb2">&nbsp;&nbsp;
    <logic:equal name="abaData" property="adr110abaTypeNameDetail" value="address.122">
      <gsmsg:write key="address.122" />
    </logic:equal>
    <logic:equal name="abaData" property="adr110abaTypeNameDetail" value="address.123">
      <gsmsg:write key="address.123" />
    </logic:equal>
    <logic:equal name="abaData" property="adr110abaTypeNameDetail" value="address.124">
      <gsmsg:write key="address.124" />
    </logic:equal>
    &nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="abaData" property="adr110abaName" /></span>
    </th>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" width="20%" nowrap><gsmsg:write key="cmn.postalcode" /></th>
    <td class="td_wt" width="30%">
    <logic:notEmpty name="abaData" property="adr110abaPostno1">
    <span class="text_base">〒<bean:write name="abaData" property="adr110abaPostno1" />-<bean:write name="abaData" property="adr110abaPostno2" /></span>
    </logic:notEmpty>
    </td>
    <th class="table_bg_A5B4E1" align="left" width="20%" nowrap><gsmsg:write key="cmn.prefectures" /></th>
    <td class="td_wt" width="30%"><span class="text_base"><bean:write name="abaData" property="adr110abaTdfkName" /></span></td>
    </td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" nowrap><gsmsg:write key="cmn.address1" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr110abaAddress1" /></span></td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" nowrap><gsmsg:write key="cmn.address2" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr110abaAddress2" /></span></td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" nowrap><gsmsg:write key="cmn.memo" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr110abaViewBiko" filter="false" /></span></td>
    </tr>
    <tr>
    <td colspan="2"><IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0"></td>
    </tr>

    </logic:iterate>

    </table>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td colspan="3">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">

      <logic:equal name="adr110knForm" property="adr100backFlg" value="3">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decisionCompany');">
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="backInput('backInputCompany');">
      </logic:equal>

      <logic:notEqual name="adr110knForm" property="adr100backFlg" value="3">

      <logic:notEqual name="adr110knForm" property="adr100backFlg" value="2">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decisionCompany');">
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInputCompany');">
      </logic:notEqual>

      <logic:equal name="adr110knForm" property="adr100backFlg" value="2">
        <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.fixed" />" onClick="return editCompany('<bean:write name="adr110knForm" property="adr110editAcoSid" />', '<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>');">
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAdrList');">
      </logic:equal>

      </logic:notEqual>

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