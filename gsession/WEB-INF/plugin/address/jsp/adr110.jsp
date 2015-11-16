<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
    String maxLengthBiko = String.valueOf(jp.groupsession.v2.adr.GSConstAddress.MAX_LENGTH_ADR_BIKO);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="address.adr110.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adrComPopUp.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03" onunload="windowClose();" onload="showLengthId($('#inputstr1')[0], <%= maxLengthBiko %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthBiko %>, 'inputlength2');">

<html:form action="/address/adr110">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<logic:notEmpty name="adr110Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr110Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr110Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr110Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr110Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="CMD" value="">
<input type="hidden" name="helpPrm" value="<bean:write name="adr110Form" property="adr110ProcMode" />">
<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

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

<logic:notEmpty name="adr110Form" property="adr020tantoList">
<logic:iterate id="tantoList" name="adr110Form" property="adr020tantoList">
  <input type="hidden" name="adr020tantoList" value="<bean:write name="tantoList" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr110Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr110Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr110Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr110Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020tantoGroup" />

<logic:notEmpty name="adr110Form" property="adr020label">
<logic:iterate id="label" name="adr110Form" property="adr020label">
  <input type="hidden" name="adr020label" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitView" />

<logic:notEmpty name="adr110Form" property="adr020permitViewGroup">
<logic:iterate id="permitViewGroup" name="adr110Form" property="adr020permitViewGroup">
  <input type="hidden" name="adr020permitViewGroup" value="<bean:write name="permitViewGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110Form" property="adr020permitViewUser">
<logic:iterate id="permitViewUser" name="adr110Form" property="adr020permitViewUser">
  <input type="hidden" name="adr020permitViewUser" value="<bean:write name="permitViewUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020permitViewUserGroup" />
<html:hidden property="adr020permitEdit" />

<logic:notEmpty name="adr110Form" property="adr020permitEditGroup">
<logic:iterate id="permitEditGroup" name="adr110Form" property="adr020permitEditGroup">
  <input type="hidden" name="adr020permitEditGroup" value="<bean:write name="permitEditGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110Form" property="adr020permitEditUser">
<logic:iterate id="permitEditUser" name="adr110Form" property="adr020permitEditUser">
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


<logic:notEmpty name="adr110Form" property="adr110atiList">
<logic:iterate id="atiSid" name="adr110Form" property="adr110atiList">
<input type="hidden" name="adr110atiList" value="<bean:write name="atiSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr110Form" property="abaListToList">
<logic:iterate id="abaData" name="adr110Form" property="abaListToList" indexId="idx">
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

<input type="hidden" name="adr110deleteCompanyBaseIndex" value="">
<input type="hidden" name="adr111editCompanyBaseIndex" value="">
<html:hidden name="adr110Form" property="adr110BackId" />

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="80%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr110.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="okPush('confirmCompany');">
      <logic:equal name="adr110Form" property="adr110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteCompany');">
      </logic:equal>
      <logic:equal name="adr110Form" property="adr100backFlg" value="1">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInputAddress');">
      </logic:equal>
      <logic:notEqual name="adr110Form" property="adr100backFlg" value="1">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backCompanyList');">
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

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <!--  <gsmsg:write key="address.7" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.7" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110coCode" maxlength="20" style="width:275px;" />&nbsp;<input type="button" class="btn_base1s" name="comBtn1" id="comBtn1" value="<gsmsg:write key="address.adr110.2" />" onclick="return openComWindow();">
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.company.name" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110coName" maxlength="50" style="width:455px;" />
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.company.name" /><gsmsg:write key="cmn.kana.2" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" />(<gsmsg:write key="cmn.kana" />)</span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110coNameKn" maxlength="100" style="width:455px;" />
      <span class="text_base">*<gsmsg:write key="cmn.enter.kana.zenkaku" /></span><br>
    </td>
    </tr>

    <!--  <gsmsg:write key="address.103" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.103" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">

      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.104" /></span></td>
      <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.105" /></span></td>
      </tr>

      <tr>
      <td align="center" class="td_body01" valign="top">
        <html:select name="adr110Form" property="adr110selectAtiList" size="3" styleClass="select01" multiple="true">
        <logic:notEmpty name="adr110Form" property="selectAtiCombo">
          <html:optionsCollection name="adr110Form" property="selectAtiCombo" value="value" label="label" />
        </logic:notEmpty>
        <option>&nbsp;</option>
        </html:select>
      </td>
      <td valign="middle" align="center">
        <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addIndustry');" style="padding-bottom:5px;">
        <br>
        <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteIndustry');">
      </td>
      <td align="center" class="td_body01" valign="top">
        <html:select name="adr110Form" property="adr110NoSelectAtiList" size="3" styleClass="select01" multiple="true">
        <logic:notEmpty name="adr110Form" property="noSelectAtiCombo">
          <html:optionsCollection name="adr110Form" property="noSelectAtiCombo" value="value" label="label" />
        </logic:notEmpty>
        <option value="-1">&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <!-- URL -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1">URL</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110url" maxlength="100" style="width:635px;" />
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.memo" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <textarea name="adr110biko" style="width:488px" rows="5" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr1" /><bean:write name="adr110Form" property="adr110biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="20px" border="0">
  </td>
  </tr>

  <logic:notEmpty name="adr110Form" property="abaListToList">
  <logic:iterate id="abaData" name="adr110Form" property="abaListToList">
  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="20px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0" class="tl_u2">
    <tr>
    <td colspan="4" class="address_info_bg" align="left" valign="middle"><span class="text_left"><img src="../address/images/parson_icon.gif" alt="<gsmsg:write key="address.106" />" class="img_bottom"></span>
      <span class="text_right">
        <input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n3" onClick="editCompanyBase('<bean:write name="abaData" property="adr110abaIndex" />');">
          &nbsp;
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n3" onClick="deleteCompanyBase('<bean:write name="abaData" property="adr110abaIndex" />');"></span>
        <span class="adr_text_bb1">&nbsp;
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
    </td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" width="20%"><gsmsg:write key="cmn.postalcode" /></th>
    <td class="td_wt" width="30%">
    <logic:notEmpty name="abaData" property="adr110abaPostno1">
    <span class="text_base">〒<bean:write name="abaData" property="adr110abaPostno1" />-<bean:write name="abaData" property="adr110abaPostno2" /></span>
    </logic:notEmpty>
    </td>
    <th class="table_bg_A5B4E1" align="left" width="20%"><gsmsg:write key="cmn.prefectures" /></th>
    <td class="td_wt" width="30%"><span class="text_base"><bean:write name="abaData" property="adr110abaTdfkName" /></span></td>
    </td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left"><gsmsg:write key="cmn.address1" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr110abaAddress1" /></span></td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left"><gsmsg:write key="cmn.address2" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr110abaAddress2" /></span></td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left"><gsmsg:write key="cmn.memo" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr110abaViewBiko" filter="false" /></span></td>
    </tr>
    </table>
  </td>
  </tr>
  </logic:iterate>
  </logic:notEmpty>

  <tr>
  <td>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="40px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td valign="bottom" align="left" colspan="2" nowrap>
      <span class="text_bb2"><gsmsg:write key="address.adr110.3" /></span>
    </td>
    <td align="right">
      <input type="button" name="addBtn" class="btn_add_n4" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('addCompanyBase');">
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.10" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">

      <html:select name="adr110Form" property="adr110abaType">
        <html:optionsCollection name="adr110Form" property="abaTypeList" value="value" label="label" />
      </html:select>
      <html:text property="adr110abaName" maxlength="50" style="width:455px;" />
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.postalcode" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="4" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address" /></font> </td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.postalcode" /></font></td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110abaPostno1" maxlength="3" style="width:53px;" />
      <font class="text_base">-</font>
      <html:text property="adr110abaPostno2" maxlength="4" style="width:59px;" />
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.prefectures" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.prefectures" /></font> </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:notEmpty  name="adr110Form" property="tdfkCmbList">
      <html:select name="adr110Form" property="adr110abaTdfk">
        <html:optionsCollection name="adr110Form" property="tdfkCmbList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.address1" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address1" /></font> </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110abaAddress1" maxlength="100" style="width:575px;" />
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.address2" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address2" /></font> </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr110abaAddress2" maxlength="100" style="width:575px;" />
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.memo" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <textarea name="adr110abaBiko" style="width:488px" rows="5" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength2');" id="inputstr2" /><bean:write name="adr110Form" property="adr110abaBiko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_ok1" value="OK" onClick="okPush('confirmCompany');">
      <logic:equal name="adr110Form" property="adr110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteCompany');">
      </logic:equal>
      <logic:equal name="adr110Form" property="adr100backFlg" value="1">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInputAddress');">
      </logic:equal>
      <logic:notEqual name="adr110Form" property="adr100backFlg" value="1">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backCompanyList');">
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