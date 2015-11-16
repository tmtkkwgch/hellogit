<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.adr.GSConstAddress" %>

<%
    String maxLengthBiko = String.valueOf(GSConstAddress.MAX_LENGTH_ADR_BIKO);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="address.adr020.1" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/adrEntry.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<% String closeScript = "companyWindowClose();"; %>
</head>

<body class="body_03" onload="viewchange();editchange();showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="<%= closeScript %>">

<div id="FreezePane">

<html:form action="/address/adr020">

<input type="hidden" name="helpPrm" value="<bean:write name="adr020Form" property="adr020ProcMode" />">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="winname" value="subbox">
<logic:notEmpty name="adr020Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr020Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr020Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr020Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr020Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr020Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr020init" />
<html:hidden property="adr020ProcMode" />
<html:hidden property="adr020BackId" />
<html:hidden property="adr160pageNum1" />
<html:hidden property="adr160pageNum2" />
<html:hidden property="sortKey" />
<html:hidden property="orderKey" />
<html:hidden property="adrPosition" />

<html:hidden property="adrCopyFlg" />
<html:hidden property="adr020webmail" />

<html:hidden property="adr020selectCompany" />
<html:hidden property="adr020selectCompanyBase" />
<input type="hidden" name="adr020deleteLabel" value="">
<input type="hidden" name="adr100backFlg" value="1">
<input type="hidden" name="adr110editAcoSid" value="">
<input type="hidden" name="adr110ProcMode" value="0">

<logic:notEmpty name="adr020Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr020Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr020Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr020Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<span id="adr020labelArea">
<logic:notEmpty name="adr020Form" property="adr020label">
<logic:iterate id="label" name="adr020Form" property="adr020label">
  <input type="hidden" name="adr020label" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>
</span>

<logic:notEmpty name="adr020Form" property="adr020tantoList">
<logic:iterate id="tanto" name="adr020Form" property="adr020tantoList">
  <input type="hidden" name="adr020tantoList" value="<bean:write name="tanto" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr020Form" property="adr020permitViewGroup">
<logic:iterate id="viewGroup" name="adr020Form" property="adr020permitViewGroup">
  <input type="hidden" name="adr020permitViewGroup" value="<bean:write name="viewGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr020Form" property="adr020permitViewUser">
<logic:iterate id="viewUser" name="adr020Form" property="adr020permitViewUser">
  <input type="hidden" name="adr020permitViewUser" value="<bean:write name="viewUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr020Form" property="adr020permitEditGroup">
<logic:iterate id="editGroup" name="adr020Form" property="adr020permitEditGroup">
  <input type="hidden" name="adr020permitEditGroup" value="<bean:write name="editGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr020Form" property="adr020permitEditUser">
<logic:iterate id="editUser" name="adr020Form" property="adr020permitEditUser">
  <input type="hidden" name="adr020permitEditUser" value="<bean:write name="editUser" />">
</logic:iterate>
</logic:notEmpty>

<div id="adr020CompanyIdArea">
<logic:notEmpty name="adr020Form" property="adr020CompanySid">
  <logic:iterate id="coId" name="adr020Form" property="adr020CompanySid">
    <input type="hidden" name="adr020CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<div id="adr020CompanyBaseIdArea">
<logic:notEmpty name="adr020Form" property="adr020CompanyBaseSid">
  <logic:iterate id="coId" name="adr020Form" property="adr020CompanyBaseSid">
    <input type="hidden" name="adr020CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<logic:notEmpty name="adr020Form" property="projectCmbList">
<logic:iterate id="project" name="adr020Form" property="projectCmbList">
  <input type="hidden" name="projectCmbList" value="<bean:write name="project" />">
</logic:iterate>
</logic:notEmpty>

<% boolean callWebmail = true; %>
<logic:notEqual name="adr020Form" property="adr020webmail" value="1">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<% callWebmail = false; %>
</logic:notEqual>

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="90%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr020.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="adr020Form" property="adr020ProcMode" value="<%= String.valueOf(GSConstAddress.PROCMODE_EDIT) %>">
      <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.register.copy" />" onClick="buttonCopy('<%= String.valueOf(GSConstAddress.PROCMODE_ADD) %>');" tabindex="1">
      </logic:equal>
      <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('registConfirm');" tabindex="1">
      <logic:equal name="adr020Form" property="adr020ProcMode" value="<%= String.valueOf(GSConstAddress.PROCMODE_EDIT) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');" tabindex="2">
      </logic:equal>

      <% if (callWebmail) { %>
        <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.parent.webmailEntrySubWindowClose();" tabindex="3">
      <% } else { %>
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAdrList');" tabindex="3">
      <% } %>
    </td>
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
    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.name" /></span><span class="text_r2">※</span>
    </td>
    <td width="45%" valign="middle" align="left" class="td_wt">
      <span class="text_base2"><gsmsg:write key="cmn.lastname" />&nbsp;</span><html:text property="adr020unameSei" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_SEI) %>" tabindex="5" style="width:155px;" />
      <span class="text_base2"><gsmsg:write key="cmn.name3" />&nbsp;</span><html:text property="adr020unameMei" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_MEI) %>" tabindex="6" style="width:155px;" />
    </td>

    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" rowspan="5" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.46" /></span>
    </td>
    <td width="30%" valign="middle" align="left" class="td_wt" rowspan="5">

      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.staff" /></span></td>
      <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.user" /></span></td>
      </tr>

      <tr>
      <td align="center" class="td_body01" valign="top">
        <html:select name="adr020Form" property="adr020selectTantoList" size="11" styleClass="add_select01" multiple="true" tabindex="42">
        <logic:notEmpty name="adr020Form" property="selectTantoCombo">
          <html:optionsCollection name="adr020Form" property="selectTantoCombo" value="value" label="label" />
        </logic:notEmpty>
        <option>&nbsp;</option>
        </html:select>
      </td>
      <td valign="middle" align="center">
        <a href="#" onClick="buttonPush('addTanto');"><img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" tabindex="43"></a>
        <br><br><br>
        <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteTanto');" tabindex="44">
      </td>
      <td align="center" class="td_body01" valign="top">
      <table>
      <tr>
      <td colspan="2">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.adr020tantoGroup, 'adr020tantoGroup', '<bean:write name="adr020Form" property="adr020tantoGroup" />', '0', 'init', 'adr020tantoList', '-1', '0')" type="button">
      </td>
      </tr>
      <tr>
      <td>
        <html:select name="adr020Form" property="adr020tantoGroup" styleClass="add_select01" onchange="buttonPush('init');" tabindex="45">
          <html:optionsCollection name="adr020Form" property="groupCmbList" value="value" label="label" />
        </html:select>
      </td>
      <td>
        <input type="button" onclick="openGroupWindow(this.form.adr020tantoGroup, 'adr020tantoGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="adr020GroupBtn">
      </td>
      </tr>
      <tr>
      <td colspan="2">
        <html:select name="adr020Form" property="adr020NoSelectTantoList" size="8" styleClass="add_select01" multiple="true" tabindex="46">
        <logic:notEmpty name="adr020Form" property="noSelectTantoCombo">
          <html:optionsCollection name="adr020Form" property="noSelectTantoCombo" value="value" label="label" />
        </logic:notEmpty>
        <option value="-1">&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.119" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base2"><gsmsg:write key="cmn.lastname" />&nbsp;</span><html:text property="adr020unameSeiKn" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_SEI_KN) %>" tabindex="7" style="width:155px;" />
      <span class="text_base2"><gsmsg:write key="cmn.name3" />&nbsp;</span><html:text property="adr020unameMeiKn" maxlength="<%= String.valueOf(GSConstAddress.MAX_LENGTH_NAME_MEI_KN) %>" tabindex="8" style="width:155px;" /><br>
      <span class="text_base">*<gsmsg:write key="cmn.enter.kana.zenkaku" /></span><br>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.select.company" /></span>
    </td>
    <td valign="bottom" align="left" class="td_wt">
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="100%" colspan="3" nowrap><span class="text_base"><gsmsg:write key="address.7" /> ： <bean:write name="adr020Form" property="adr020companyCode" /></span></td>
      </tr>

      <tr>
      <td width="0%" nowrap>
      <logic:notEmpty name="adr020Form" property="adr020companyName">
      <a href="#" onClick="return editCompany();" tabindex="9"><span class="sc_link"><bean:write name="adr020Form" property="adr020companyName" />&nbsp;&nbsp;&nbsp;<bean:write name="adr020Form" property="adr020companyBaseName" /></span></a>
      </logic:notEmpty>
      </td>
      <td width="10%" align="right" nowrap>&nbsp;<input type="button" name="btn_search" class="btn_base0" value="<gsmsg:write key="cmn.search" />" onclick="return openCompanyWindow('adr020')" tabindex="10"></td>
      <td width="90%" nowrap>
      <logic:equal name="adr020Form" property="adr020addCompanyBtnFlg" value="1">
      &nbsp;<a href="#" onClick="buttonPush('addCompany');"><span valign="bottom"><img src="../common/images/plus.gif" alt="<gsmsg:write key="address.adr020.5" />" border="0" tabindex="11"></span></a>
      </logic:equal>

      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.affiliation" /></font> </td>
    <td valign="middle" align="left" class="td_wt"><html:text property="adr020syozoku" maxlength="60" tabindex="12" style="width:395px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.post" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="1">

      <table cellpadding="0" cellspacing="0">
      <tr>
      <td>
        <html:select name="adr020Form" property="adr020position" tabindex="13">
          <html:optionsCollection name="adr020Form" property="positionCmbList" value="value" label="label" />
        </html:select>
        &nbsp;
      </td>
      <td><a href="#" onClick="return openpos();"><img src="../common/images/plus.gif" alt="<gsmsg:write key="address.adr020.6" />" border="0" tabindex="14"></a></td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.mailaddress1" /></font></td>
    <td valign="middle" align="left" class="td_wt" colspan="1" nowrap>
      <html:text property="adr020mail1" maxlength="50" tabindex="15" style="width:275px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="adr020mail1Comment" maxlength="10" tabindex="16" style="width:95px;" />
    </td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="6" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.label" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" rowspan="6">

      <table cellpadding="0" cellspacing="0">
      <tr>
      <td><input type="button" value="<gsmsg:write key="cmn.select.label" />" class="btn_base1" onClick="openlabel();" tabindex="47">&nbsp;</td>
      <logic:equal name="adr020Form" property="adr020addLabelBtnFlg" value="1">
      <td><a href="#" onClick="openlabeladd();"><img src="../common/images/plus.gif" alt="<gsmsg:write key="cmn.add.label2" />" border="0" tabindex="48"></a></td>
      </logic:equal>
      </tr>
      </table>

      <logic:notEmpty name="adr020Form" property="selectLabelList">
      <table cellpadding="0" cellspacing="0" class="tl0 spacer" width="100%">

      <% String[] labelClass = {"td_label1", "td_label2"}; %>
      <logic:iterate id="labelData" name="adr020Form" property="selectLabelList" indexId="idx">

      <tr class="<%= labelClass[idx.intValue() % 2] %>">
      <td width="100%"><img src="../common/images/delete.gif" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="deleteLabel('<bean:write name="labelData" property="albSid" />');">&nbsp;<bean:write name="labelData" property="albName" /></td>
      </tr>

      </logic:iterate>

      </table>
      </logic:notEmpty>

    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.mailaddress2" /></font> </td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020mail2" maxlength="50" tabindex="17" style="width:275px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="adr020mail2Comment" maxlength="10" tabindex="18" style="width:95px;" />
    </td>

    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.mailaddress3" /></font> </td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020mail3" maxlength="50" tabindex="19" style="width:275px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="adr020mail3Comment" maxlength="10" tabindex="20" style="width:95px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel1" /></font></td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020tel1" maxlength="20" tabindex="21" style="width:143px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="address.58" />：&nbsp;</span><html:text property="adr020tel1Nai" maxlength="10" tabindex="22" style="width:77px;" />
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="adr020tel1Comment" maxlength="10" tabindex="23" style="width:113px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel2" /></font></td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020tel2" maxlength="20" tabindex="24" style="width:143px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="address.58" />：&nbsp;</span><html:text property="adr020tel2Nai" maxlength="10" tabindex="25" style="width:77px;" />
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="adr020tel2Comment" maxlength="10" tabindex="26" style="width:113px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel3" /></font></td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020tel3" maxlength="20" tabindex="27" style="width:143px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="address.58" />：&nbsp;</span><html:text property="adr020tel3Nai" maxlength="10" tabindex="28" style="width:77px;" />
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="adr020tel3Comment" maxlength="10" tabindex="29" style="width:113px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ１</font></td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020fax1" maxlength="20" tabindex="30" style="width:143px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span></span><html:text property="adr020fax1Comment" maxlength="10" tabindex="31" style="width:215px;" />
    </td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="6" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.61" /></span><span class="text_r2">※</span>
    </td>
    <td valign="top" align="left" class="td_wt" rowspan="6" nowrap>
      <html:radio property="adr020permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_OWN) %>" styleId="view0" onclick="viewchange();" tabindex="49" /><label for="view0"><span class="text_base"><gsmsg:write key="address.62" /></span></label>
      <html:radio property="adr020permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_GROUP) %>" styleId="view1" onclick="viewchange();" tabindex="50" /><label for="view1"><span class="text_base"><gsmsg:write key="group.designation" /></span></label>
      <html:radio property="adr020permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_USER) %>" styleId="view2" onclick="viewchange();" tabindex="51" /><label for="view2"><span class="text_base"><gsmsg:write key="cmn.user.specified" /></span></label>
      <html:radio property="adr020permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_NORESTRICTION) %>" styleId="view3" onclick="viewchange();" tabindex="52" /><label for="view3"><span class="text_base"><gsmsg:write key="cmn.no.setting.permission" /></span></label>

      <div id="viewgroup" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.66" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.group" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr020Form" property="adr020selectPermitViewGroup" size="7" styleClass="add_select01" multiple="true" tabindex="53">
          <logic:notEmpty name="adr020Form" property="selectPermitViewGroup">
            <html:optionsCollection name="adr020Form" property="selectPermitViewGroup" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addViewGroup');" tabindex="54">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteViewGroup');" tabindex="55">
        </td>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr020Form" property="adr020NoSelectPermitViewGroup" size="6" styleClass="add_select01" multiple="true" tabindex="56">
          <logic:notEmpty name="adr020Form" property="noSelectPermitViewGroup">
            <html:optionsCollection name="adr020Form" property="noSelectPermitViewGroup" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;</option>
          </html:select>
        </td>
        </tr>
        </table>
      </div>

      <div id="viewuser" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.68" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.user" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr020Form" property="adr020selectPermitViewUser" size="9" styleClass="add_select01" multiple="true" tabindex="57">
          <logic:notEmpty name="adr020Form" property="selectPermitViewUser">
            <html:optionsCollection name="adr020Form" property="selectPermitViewUser" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addViewUser');" tabindex="58">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteViewUser');" tabindex="59">
        </td>
        <td align="center" class="td_body01" valign="top">
          <table>
          <tr>
          <td colspan="2">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.adr020permitViewUserGroup, 'adr020permitViewUserGroup', '<bean:write name="adr020Form" property="adr020permitViewUserGroup" />', '0', 'init', 'adr020permitViewUser', '-1', '0')" type="button"><br>
          </td>
          </tr>
          <tr>
          <td>
          <html:select name="adr020Form" property="adr020permitViewUserGroup" styleClass="add_select01" onchange="buttonPush('init');" tabindex="60">
            <html:optionsCollection name="adr020Form" property="groupCmbList" value="value" label="label" />
          </html:select>
          </td>
          <td>
            <input type="button" onclick="openGroupWindow(this.form.adr020permitViewUserGroup, 'adr020permitViewUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="adr020GroupBtn2">
          </td>
          </tr>
          <tr>
          <td colspan="2">
          <html:select name="adr020Form" property="adr020NoSelectPermitViewUser" size="6" styleClass="add_select01" multiple="true" tabindex="61">
          <logic:notEmpty name="adr020Form" property="noSelectPermitViewUser">
            <html:optionsCollection name="adr020Form" property="noSelectPermitViewUser" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;</option>
          </html:select>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </table>
      </div>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ２</font></td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020fax2" maxlength="20" tabindex="32" style="width:143px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span></span><html:text property="adr020fax2Comment" maxlength="10" tabindex="33" style="width:215px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ３</font></td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:text property="adr020fax3" maxlength="20" tabindex="34" style="width:143px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span></span><html:text property="adr020fax3Comment" maxlength="10" tabindex="35" style="width:215px;" />
    </td>
    </tr>

    <% String addressRow = "4"; %>
    <% String editRow = "3"; %>
    <logic:equal name="adr020Form" property="adr020searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
      <% addressRow = "4"; %>
      <% editRow = "2"; %>
    </logic:equal>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="<%= addressRow %>" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address" /></font> </td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.postalcode" /></font></td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr020postno1" maxlength="3" tabindex="36" style="width:59px;" />
      <font class="text_base">-</font>
      <html:text property="adr020postno2" maxlength="4" tabindex="37" style="width:71px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.prefectures" /></font> </td>
    <td valign="middle" align="left" class="td_wt">
      <html:select name="adr020Form" property="adr020tdfk" tabindex="38">
        <html:optionsCollection name="adr020Form" property="tdfkCmbList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address1" /></font> </td>
    <td valign="middle" align="left" class="td_wt">
      <html:text property="adr020address1" maxlength="100" tabindex="39" style="width:425px;" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address2" /></font></td>
    <td valign="middle" align="left" class="td_wt"><html:text property="adr020address2" maxlength="100" tabindex="40" style="width:425px;" /></td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="<%= editRow %>" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2">※</span>
    </td>
    <td valign="top" align="left" class="td_wt" rowspan="<%= editRow %>" nowrap>

      <div id="editselect">
      <html:radio property="adr020permitEdit" value="<%= String.valueOf(GSConstAddress.EDITPERMIT_OWN) %>" styleId="edit0" onclick="editchange();" tabindex="62" /><label for="edit0"><span class="text_base"><gsmsg:write key="address.62" /></span></label>
      <html:radio property="adr020permitEdit" value="<%= String.valueOf(GSConstAddress.EDITPERMIT_GROUP) %>" styleId="edit1" onclick="editchange();" tabindex="63" /><label for="edit1"><span class="text_base"><gsmsg:write key="group.designation" /></span></label>
      <html:radio property="adr020permitEdit" value="<%= String.valueOf(GSConstAddress.EDITPERMIT_USER) %>" styleId="edit2" onclick="editchange();" tabindex="64" /><label for="edit2"><span class="text_base"><gsmsg:write key="cmn.user.specified" /></span></label>
      <html:radio property="adr020permitEdit" value="<%= String.valueOf(GSConstAddress.EDITPERMIT_NORESTRICTION) %>" styleId="edit3" onclick="editchange();" tabindex="65" /><label for="edit3"><span class="text_base"><gsmsg:write key="cmn.no.setting.permission" /></span></label>
      </div>

      <div id="editselectstr">
      </div>

      <div id="editgroup" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.editgroup" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.group" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr020Form" property="adr020selectPermitEditGroup" size="8" styleClass="add_select01" multiple="true" tabindex="66">
          <logic:notEmpty name="adr020Form" property="selectPermitEditGroup">
            <html:optionsCollection name="adr020Form" property="selectPermitEditGroup" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addEditGroup');" tabindex="67">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteEditGroup');" tabindex="68">
        </td>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr020Form" property="adr020NoSelectPermitEditGroup" size="8" styleClass="add_select01" multiple="true" tabindex="69">
          <logic:notEmpty name="adr020Form" property="noSelectPermitEditGroup">
            <html:optionsCollection name="adr020Form" property="noSelectPermitEditGroup" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;</option>
          </html:select>
        </td>
        </tr>
        </table>
      </div>

      <div id="edituser" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.edituser" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.user" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr020Form" property="adr020selectPermitEditUser" size="10" styleClass="add_select01" multiple="true" tabindex="70">
          <logic:notEmpty name="adr020Form" property="selectPermitEditUser">
            <html:optionsCollection name="adr020Form" property="selectPermitEditUser" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addEditUser');" tabindex="71">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteEditUser');" tabindex="72">
        </td>
        <td align="center" class="td_body01" valign="top">
          <table>
          <tr>
          <td colspan="2">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.adr020permitEditUserGroup, 'adr020permitEditUserGroup', '<bean:write name="adr020Form" property="adr020permitEditUserGroup" />', '0', 'init', 'adr020permitEditUser', '-1', '0')" type="button"><br>
          </td>
          </tr>
          <tr>
          <td>
          <html:select name="adr020Form" property="adr020permitEditUserGroup" styleClass="add_select01" onchange="buttonPush('init');" tabindex="73">
            <html:optionsCollection name="adr020Form" property="groupCmbList" value="value" label="label" />
          </html:select>
          </td>
          <td>
            <input type="button" onclick="openGroupWindow(this.form.adr020permitEditUserGroup, 'adr020permitEditUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="adr020GroupBtn3">
          </td>
          </tr>
          <tr>
          <td colspan="2">
          <html:select name="adr020Form" property="adr020NoSelectPermitEditUser" size="7" styleClass="add_select01" multiple="true" tabindex="74">
          <logic:notEmpty name="adr020Form" property="noSelectPermitEditUser">
            <html:optionsCollection name="adr020Form" property="noSelectPermitEditUser" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;</option>
          </html:select>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </table>
      </div>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <textarea name="adr020biko" style="width:394px" rows="8" styleClass="text_gray" tabindex="41" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="adr020Form" property="adr020biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <logic:equal name="adr020Form" property="adr020ProcMode" value="<%= String.valueOf(GSConstAddress.PROCMODE_EDIT) %>">
      <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.register.copy" />" onClick="buttonCopy('<%= String.valueOf(GSConstAddress.PROCMODE_ADD) %>');" tabindex="1">
      </logic:equal>
      <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('registConfirm');" tabindex="75">
      <logic:equal name="adr020Form" property="adr020ProcMode" value="<%= String.valueOf(GSConstAddress.PROCMODE_EDIT) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');" tabindex="76">
      </logic:equal>


      <% if (callWebmail) { %>
        <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.parent.webmailEntrySubWindowClose();" tabindex="77">
      <% } else { %>
        <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAdrList');" tabindex="77">
      <% } %>
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

</div>

<div id="subPanel">
<div class="hd"><gsmsg:write key="cmn.plz.register.post" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="pos" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>

<div id="labelAddPanel">
<div class="hd"><gsmsg:write key="address.adr020.9" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="labadd" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>

<div id="labelPanel">
<div class="hd"><gsmsg:write key="cmn.select.a.label" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="lab" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>

</body>
</html:html>