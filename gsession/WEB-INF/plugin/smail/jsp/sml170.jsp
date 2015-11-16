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
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../smail/js/sml170.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.20" /></title>
</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/smail/sml170">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="smlAccountSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml170AccountSid" />
<html:hidden property="sml170InitFlg" />
<html:hidden property="sml170AccountName" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.mail" /> <gsmsg:write key="sml.80" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" id="settingBtn" class="btn_setting_n1">
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
    <th class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /><gsmsg:write key="wml.102" /></span></th>
    <td align="left" class="td_wt" width="100%">
      <div>
        <html:radio name="sml170Form" property="sml170SelKbn" styleClass="accountSelKbn" styleId="sml170SelKbn_0"  value="0"/><span class="text_base"><label for="sml170SelKbn_0"><gsmsg:write key="wml.wml010.12" /></label></span>&nbsp;
        <html:radio name="sml170Form" property="sml170SelKbn" styleClass="accountSelKbn" styleId="sml170SelKbn_1"  value="1"/><span class="text_base"><label for="sml170SelKbn_1"><gsmsg:write key="cmn.all" /></label></span>&nbsp;
      </div>
      <div id="accountSelArea" class="account_name_area"><span id="selAccountNameArea"><bean:write name="sml170Form" property="sml170AccountName" /></span>　<input id="accountSelBtn" type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" ></div>
    </td>
    </tr>

    <!-- メール転送設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.80" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
      <span class="text_base"><gsmsg:write key="sml.05" /><br>
      <html:radio name="sml170Form" property="sml170MailFw" styleId="sml170MailFw0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml170MailFw0"><gsmsg:write key="sml.78" /></label></span>&nbsp;
      <html:radio name="sml170Form" property="sml170MailFw" styleId="sml170MailFw1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml170MailFw1"><gsmsg:write key="sml.79" /></label>&nbsp;</span><br>
      </span>
      </td>
      </tr>


      <tr>
      <td align="left" width="100%">
      <span class="text_base"><gsmsg:write key="sml.77" /></span>
      <html:radio name="sml170Form" property="sml170SmailOp" styleId="sml170SmailOp0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED) %>"/>
      <span class="text_base"><label for="sml170SmailOp0"><gsmsg:write key="sml.29" /></label></span>
      <html:radio name="sml170Form" property="sml170SmailOp" styleId="sml170SmailOp1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED) %>"/>
      <span class="text_base"><label for="sml170SmailOp1"><gsmsg:write key="cmn.mark.read" /></label></span>
      </td>
      </tr>

      <logic:equal name="sml170Form" property="sml170ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <tr>
      <td align="left" width="100%">
      <hr>
      <span class="text_base"><gsmsg:write key="sml.sml180.03" /><br>
      <html:radio name="sml170Form" property="sml170HuriwakeKbn" styleId="sml170HuriwakeKbn0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml170HuriwakeKbn0"><gsmsg:write key="sml.42" /></label></span>&nbsp;
      <html:radio name="sml170Form" property="sml170HuriwakeKbn" styleId="sml170HuriwakeKbn1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml170HuriwakeKbn1"><gsmsg:write key="sml.43" /></label></span>&nbsp;
      <html:radio name="sml170Form" property="sml170HuriwakeKbn" styleId="sml170HuriwakeKbn2" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_FUZAI_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml170HuriwakeKbn2"><gsmsg:write key="sml.168" /></label></span>&nbsp;
      </span>
      </td>
      </tr>

      <tr class="send_default">
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.81" /></span>&nbsp;</th>
      </tr>

      <tr class="send_default">
      <td align="left" width="100%">
        <html:select name="sml170Form" property="sml170MailDfSelected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml170Form" property="sml170MailList" value="value" label="label" />
        </html:select>
        <html:text name="sml170Form" style="width:335px;" maxlength="50" property="sml170MailDf" styleClass="text_base" /><br>
      </td>
      </tr>

      <tr class="send_kobetu">
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.44" /></span>&nbsp;</th>
      </tr>

      <tr class="send_kobetu">
      <td align="left" width="100%">
        <html:select name="sml170Form" property="sml170Zmail1Selected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml170Form" property="sml170MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml170Form" size="50" maxlength="50" property="sml170Zmail1" styleClass="text_base" />
      </td>
      </tr>

      <tr class="send_kobetu send_fuzai">
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.89" /></span>&nbsp;</th>
      </tr>

      <tr class="send_kobetu send_fuzai">
      <td align="left" width="100%">
        <html:select name="sml170Form" property="sml170Zmail2Selected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml170Form" property="sml170MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml170Form" size="50" maxlength="50" property="sml170Zmail2" styleClass="text_base" />
      </td>
      </tr>

      <tr class="send_kobetu">
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.12" /></span>&nbsp;</th>
      </tr>

      <tr class="send_kobetu">
      <td align="left" width="100%">
        <html:select name="sml170Form" property="sml170Zmail3Selected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml170Form" property="sml170MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml170Form" size="50" maxlength="50" property="sml170Zmail3" styleClass="text_base" />
      </td>
      </tr>
      </logic:equal>

      <logic:notEqual name="sml170Form" property="sml170ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <tr class="send_default">
      <td align="left" width="100%">
        <html:select name="sml170Form" property="sml170MailDfSelected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml170Form" property="sml170MailList" value="value" label="label" />
        </html:select>
        <html:text name="sml170Form" size="50" maxlength="50" property="sml170MailDf" styleClass="text_base" /><br>
      </td>
      </tr>
      <html:hidden property="sml170HuriwakeKbn"/>
      <html:hidden property="sml170Zmail1Selected" />
      <html:hidden property="sml170Zmail1" />
      <html:hidden property="sml170Zmail2Selected" />
      <html:hidden property="sml170Zmail2" />
      <html:hidden property="sml170Zmail3Selected" />
      <html:hidden property="sml170Zmail3" />
      </logic:notEqual>
      </table>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" id="settingBtn">
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

<div id="accountSelPop" title="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" style="display:none">
  <input type="hidden" id="selAccountElm" value="sml170AccountSid" />
  <input type="hidden" id="selAccountSubmit" value="true" />
  <input type="hidden" id="resetParam" value="sml170InitFlg" />
  <input type="hidden" id="sml240user" value="<bean:write name="sml170Form" property="smlViewUser" />" />

  <div style="height:460px;overflow-y:auto;">
  <table width="100%" height="100%">
    <tr>
      <td id="accountListArea" valign="top"></td>
    </tr>
  </table>
  </div>
</div>


<div id="setKakuninPop" title="" style="display:none">
  <table width="100%" height="100%">
    <tr>
      <td rowspan="2" valign="top">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td style="padding-left:15px;padding-top:10px;">
        <span class="text_base1"><gsmsg:write key="sml.170" /></span>
      </td>
    </tr>
   <tr>
      <td  style="padding-left:15px;padding-top:10px;" colspan="2" valign="top">
        <div id="accountKakuninListArea" style="width:300px;height:100px;overflow-y:scroll;"></div>
      </td>
    </tr>
  </table>
</div>

</body>
</html:html>