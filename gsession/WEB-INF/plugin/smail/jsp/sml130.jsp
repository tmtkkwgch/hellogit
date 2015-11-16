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
<script language="JavaScript" src="../smail/js/sml130.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.07" /></title>
</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/smail/sml130">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />

<logic:notEmpty name="sml130Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml130Form" property="sml010DelSid" scope="request">
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
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="sml.07" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="submitStyleChange();buttonPush('ok');">
      <input type="button" value="<gsmsg:write key="cmn.back.preferences" />" class="btn_back_n3" onClick="buttonPush('backToList');">
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
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.50" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml130Form" property="sml130JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml130JdelKbn_0" onclick="setDispState(this.form.sml130JdelKbn, this.form.sml130JYear, this.form.sml130JMonth)" /><label for="sml130JdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml130Form" property="sml130JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml130JdelKbn_1" onclick="setDispState(this.form.sml130JdelKbn, this.form.sml130JYear, this.form.sml130JMonth)" /><label for="sml130JdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml130Form" property="sml130YearLabelList">
          <html:select property="sml130JYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml130Form" property="sml130MonthLabelList">
          <html:select property="sml130JMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.52" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml130Form" property="sml130SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml130SdelKbn_0" onclick="setDispState(this.form.sml130SdelKbn, this.form.sml130SYear, this.form.sml130SMonth)" /><label for="sml130SdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml130Form" property="sml130SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml130SdelKbn_1" onclick="setDispState(this.form.sml130SdelKbn, this.form.sml130SYear, this.form.sml130SMonth)" /><label for="sml130SdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml130Form" property="sml130YearLabelList">
          <html:select property="sml130SYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml130Form" property="sml130MonthLabelList">
          <html:select property="sml130SMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.51" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml130Form" property="sml130WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml130WdelKbn_0" onclick="setDispState(this.form.sml130WdelKbn, this.form.sml130WYear, this.form.sml130WMonth)" /><label for="sml130WdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml130Form" property="sml130WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml130WdelKbn_1" onclick="setDispState(this.form.sml130WdelKbn, this.form.sml130WYear, this.form.sml130WMonth)" /><label for="sml130WdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml130Form" property="sml130YearLabelList">
          <html:select property="sml130WYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml130Form" property="sml130MonthLabelList">
          <html:select property="sml130WMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.49" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml130Form" property="sml130DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml130DdelKbn_0" onclick="setDispState(this.form.sml130DdelKbn, this.form.sml130DYear, this.form.sml130DMonth)" /><label for="sml130DdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml130Form" property="sml130DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml130DdelKbn_1" onclick="setDispState(this.form.sml130DdelKbn, this.form.sml130DYear, this.form.sml130DMonth)" /><label for="sml130DdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml130Form" property="sml130YearLabelList">
          <html:select property="sml130DYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml130Form" property="sml130MonthLabelList">
          <html:select property="sml130DMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml130Form" property="sml130MonthLabelList" value="value" label="label" />
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
      <input type="button" value="OK" class="btn_ok1" onClick="submitStyleChange();buttonPush('ok');">
      <input type="button" value="<gsmsg:write key="cmn.back.preferences" />" class="btn_back_n3" onClick="buttonPush('backToList');">
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
</body>
</html:html>