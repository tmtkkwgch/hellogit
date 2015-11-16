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
<script language="JavaScript" src="../smail/js/sml160.js?<%= GSConst.VERSION_PARAM %>"></script>
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
<html:form action="/smail/sml160">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml160AccountSid" />
<html:hidden property="sml160AccountName" />

<logic:notEmpty name="sml160Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml160Form" property="sml010DelSid" scope="request">
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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
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
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backKtool');">
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
        <html:radio name="sml160Form" property="sml160SelKbn" styleClass="accountSelKbn" styleId="sml160SelKbn_0"  value="0"/><span class="text_base"><label for="sml160SelKbn_0"><gsmsg:write key="wml.wml010.12" /></label></span>&nbsp;
        <html:radio name="sml160Form" property="sml160SelKbn" styleClass="accountSelKbn" styleId="sml160SelKbn_1"  value="1"/><span class="text_base"><label for="sml160SelKbn_1"><gsmsg:write key="cmn.all" /></label></span>&nbsp;
      </div>
      <div id="accountSelArea" class="account_name_area"><span id="selAccountNameArea"><bean:write name="sml160Form" property="sml160AccountName" /></span>　<input id="accountSelBtn" type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" ></div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.57" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml160Form" property="sml160JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml160JdelKbn_0" onclick="setDispState(this.form.sml160JdelKbn, this.form.sml160JYear, this.form.sml160JMonth)" /><label for="sml160JdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml160Form" property="sml160JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml160JdelKbn_1" onclick="setDispState(this.form.sml160JdelKbn, this.form.sml160JYear, this.form.sml160JMonth)" /><label for="sml160JdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml160Form" property="sml160YearLabelList">
          <html:select property="sml160JYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml160Form" property="sml160MonthLabelList">
          <html:select property="sml160JMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160MonthLabelList" value="value" label="label" />
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

        <html:radio name="sml160Form" property="sml160SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml160SdelKbn_0" onclick="setDispState(this.form.sml160SdelKbn, this.form.sml160SYear, this.form.sml160SMonth)" /><label for="sml160SdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml160Form" property="sml160SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml160SdelKbn_1" onclick="setDispState(this.form.sml160SdelKbn, this.form.sml160SYear, this.form.sml160SMonth)" /><label for="sml160SdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml160Form" property="sml160YearLabelList">
          <html:select property="sml160SYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml160Form" property="sml160MonthLabelList">
          <html:select property="sml160SMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160MonthLabelList" value="value" label="label" />
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

        <html:radio name="sml160Form" property="sml160WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml160WdelKbn_0" onclick="setDispState(this.form.sml160WdelKbn, this.form.sml160WYear, this.form.sml160WMonth)" /><label for="sml160WdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml160Form" property="sml160WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml160WdelKbn_1" onclick="setDispState(this.form.sml160WdelKbn, this.form.sml160WYear, this.form.sml160WMonth)" /><label for="sml160WdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml160Form" property="sml160YearLabelList">
          <html:select property="sml160WYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml160Form" property="sml160MonthLabelList">
          <html:select property="sml160WMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160MonthLabelList" value="value" label="label" />
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

        <html:radio name="sml160Form" property="sml160DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml160DdelKbn_0" onclick="setDispState(this.form.sml160DdelKbn, this.form.sml160DYear, this.form.sml160DMonth)" /><label for="sml160DdelKbn_0"><gsmsg:write key="cmn.dont.delete" /></label>&nbsp;
        <html:radio name="sml160Form" property="sml160DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml160DdelKbn_1" onclick="setDispState(this.form.sml160DdelKbn, this.form.sml160DYear, this.form.sml160DMonth)" /><label for="sml160DdelKbn_1"><gsmsg:write key="cmn.delete.manual" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml160Form" property="sml160YearLabelList">
          <html:select property="sml160DYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml160Form" property="sml160MonthLabelList">
          <html:select property="sml160DMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml160Form" property="sml160MonthLabelList" value="value" label="label" />
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
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backKtool');">
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
  <input type="hidden" id="selAccountElm" value="sml160AccountSid" />
  <input type="hidden" id="selAccountSubmit" value="true" />
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