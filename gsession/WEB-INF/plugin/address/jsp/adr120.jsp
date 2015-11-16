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
<title>[Group Session] <gsmsg:write key="address.adr120.1" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/address/adr120">

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr120Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr120Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr120Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr120Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr120Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr120Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr120Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr120Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr120Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr120Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

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

<logic:notEmpty name="adr120Form" property="adr120atiSid">
<logic:iterate id="atiSid" name="adr120Form" property="adr120atiSid">
  <input type="hidden" name="adr120atiSid" value="<bean:write name="atiSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr120.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('importCompanyConfirm');">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backCompanyList');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <!-- <gsmsg:write key="cmn.capture.file" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('adr120file', '<%= jp.groupsession.v2.adr.GSConstAddress.PLUGIN_ID_ADDRESS %>', '1', '0');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('deleteFile');"><br>
      <html:select property="adr120file" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="adr120Form" property="adr120fileCombo" value="value" label="label" />
      </html:select>
      <br>
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% String csvFileMsg = gsMsg.getMessage(request, "address.adr120.2"); %>
      <span class="text_base">*<gsmsg:write key="cmn.plz.specify" arg0="<%= csvFileMsg %>" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="address.103" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
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
        <html:select property="adr120selectAtiSid" styleClass="select01" multiple="true" size="5">
          <logic:notEmpty name="adr120Form" property="adr120selectAtiSidCombo">
          <html:optionsCollection name="adr120Form" property="adr120selectAtiSidCombo" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td valign="middle" align="center">
        <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addGyosyu');">
        <br><br>
        <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteGyosyu');">
      </td>
      <td align="center" class="td_body01" valign="top">
        <html:select property="adr120NoSelectAtiSid" styleClass="select01" multiple="true" size="5">
          <logic:notEmpty name="adr120Form" property="adr120NoSelectAtiSidCombo">
          <html:optionsCollection name="adr120Form" property="adr120NoSelectAtiSidCombo" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <!--  <gsmsg:write key="address.103" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <html:checkbox name="adr120Form" property="adr120updateFlg" value="1" styleId="editCompany_1" /><label for="editCompany_1" class="text_base"><gsmsg:write key="address.109" /></label>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('importCompanyConfirm');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backCompanyList');">
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
</body>
</html:html>