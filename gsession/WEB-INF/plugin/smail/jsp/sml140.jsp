<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../smail/js/sml140.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.09" /></title>
</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/smail/sml140">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml140AccountSid" />
<html:hidden property="sml140AccountName" />

<logic:notEmpty name="sml140Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml140Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.09" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="submitStyleChange();buttonPush('syudo_del_kakunin');">
      <input type="button" value="<gsmsg:write key="cmn.back.preferences" />" class="btn_back_n3" onClick="buttonPush('backKjnTool');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <th class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /><gsmsg:write key="wml.102" /></span></th>
    <td align="left" class="td_wt" width="100%">
      <div>
        <html:radio name="sml140Form" property="sml140SelKbn" styleClass="accountSelKbn" styleId="sml140SelKbn_0"  value="0"/><span class="text_base"><label for="sml140SelKbn_0"><gsmsg:write key="wml.wml010.12" /></label></span>&nbsp;
        <html:radio name="sml140Form" property="sml140SelKbn" styleClass="accountSelKbn" styleId="sml140SelKbn_1"  value="1"/><span class="text_base"><label for="sml140SelKbn_1"><gsmsg:write key="cmn.all" /></label></span>&nbsp;
      </div>
      <div id="accountSelArea" class="account_name_area"><span id="selAccountNameArea"><bean:write name="sml140Form" property="sml140AccountName" /></span>　<input id="accountSelBtn" type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" ></div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.57" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml140Form" property="sml140JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml140JdelKbn_0" onclick="setDispState(this.form.sml140JdelKbn, this.form.sml140JYear, this.form.sml140JMonth)" /><label for="sml140JdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml140Form" property="sml140JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml140JdelKbn_1" onclick="setDispState(this.form.sml140JdelKbn, this.form.sml140JYear, this.form.sml140JMonth)" /><label for="sml140JdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml140Form" property="sml140YearLabelList">
          <html:select property="sml140JYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml140Form" property="sml140MonthLabelList">
          <html:select property="sml140JMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.59" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml140Form" property="sml140SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml140SdelKbn_0" onclick="setDispState(this.form.sml140SdelKbn, this.form.sml140SYear, this.form.sml140SMonth)" /><label for="sml140SdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml140Form" property="sml140SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml140SdelKbn_1" onclick="setDispState(this.form.sml140SdelKbn, this.form.sml140SYear, this.form.sml140SMonth)" /><label for="sml140SdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml140Form" property="sml140YearLabelList">
          <html:select property="sml140SYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml140Form" property="sml140MonthLabelList">
          <html:select property="sml140SMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.58" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml140Form" property="sml140WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml140WdelKbn_0" onclick="setDispState(this.form.sml140WdelKbn, this.form.sml140WYear, this.form.sml140WMonth)" /><label for="sml140WdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml140Form" property="sml140WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml140WdelKbn_1" onclick="setDispState(this.form.sml140WdelKbn, this.form.sml140WYear, this.form.sml140WMonth)" /><label for="sml140WdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml140Form" property="sml140YearLabelList">
          <html:select property="sml140WYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml140Form" property="sml140MonthLabelList">
          <html:select property="sml140WMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.56" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml140Form" property="sml140DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml140DdelKbn_0" onclick="setDispState(this.form.sml140DdelKbn, this.form.sml140DYear, this.form.sml140DMonth)" /><label for="sml140DdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml140Form" property="sml140DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml140DdelKbn_1" onclick="setDispState(this.form.sml140DdelKbn, this.form.sml140DYear, this.form.sml140DMonth)" /><label for="sml140DdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml140Form" property="sml140YearLabelList">
          <html:select property="sml140DYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml140Form" property="sml140MonthLabelList">
          <html:select property="sml140DMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml140Form" property="sml140MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="submitStyleChange();buttonPush('syudo_del_kakunin');">
      <input type="button" value="<gsmsg:write key="cmn.back.preferences" />" class="btn_back_n3" onClick="buttonPush('backKjnTool');">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>

<div id="accountSelPop" title="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" style="display:none">
  <input type="hidden" id="selAccountElm" value="sml140AccountSid" />
  <input type="hidden" id="selAccountSubmit" value="true" />
  <input type="hidden" id="sml240user" value="<bean:write name="sml140Form" property="smlViewUser" />" />

  <div style="height:460px;overflow-y:auto;">
  <table width="100%" height="100%">
    <tr>
      <td id="accountListArea" valign="top"></td>
    </tr>
  </table>
  </div>
</div>

</body>
</html:html>