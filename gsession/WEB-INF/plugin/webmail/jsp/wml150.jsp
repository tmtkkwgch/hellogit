<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConstWebmail" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml150.Wml150Form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml020.07" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml150.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03" onload="wml150Init(<bean:write name="wml150Form" property="wml150acntMakeKbn" />, <bean:write name="wml150Form" property="wml150elementKbn" />, <bean:write name="wml150Form" property="wml150diskSize" />, <bean:write name="wml150Form" property="wml150autoResv" />, <bean:write name="wml150Form" property="wml150sendMaxSizeKbn" />, <bean:write name="wml150Form" property="wml150FwLimit" />, <bean:write name="wml150Form" property="wml150FwLimitCheckFlg" />, <bean:write name="wml150Form" property="wml150bcc" />, <bean:write name="wml150Form" property="wml150warnDisk" />);changeCompressFile(<bean:write name="wml150Form" property="wml150compressFile" />);changeTimesent(<bean:write name="wml150Form" property="wml150timeSent" />);">

<html:form action="/webmail/wml150">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="wmlViewAccount" />
<html:hidden property="wml150initFlg" />
<html:hidden property="wml150elementKbn" />
<html:hidden property="wml150svFwLimitText" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml020.07" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_ok1" value="<gsmsg:write key="cmn.ok" />" onClick="buttonPush('confirm');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('admTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:messagesPresent message="false">
    <tr>
    <td>
    <table width="100%">
      <tr><td align="left"><html:errors/></td></tr>
    </table>
    </td>
    </tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <br>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr id="normal" align="left">
        <td class="now_forcus wml_tab_td2" nowrap><gsmsg:write key="cmn.preferences" /></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(1);"><gsmsg:write key="cmn.detail.setting" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="detail" align="left">
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(0);"><gsmsg:write key="cmn.preferences" /></a></td>
        <td class="now_forcus wml_tab_td2" nowrap><gsmsg:write key="cmn.detail.setting" /></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
    </table>

    <table id="normal_settings" class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.101" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="wml.wml150.03" /></span><br><br>
      <html:radio name="wml150Form" property="wml150acntMakeKbn" value="<%= String.valueOf(GSConstWebmail.KANRI_USER_NO) %>" styleId="acntMake1" onclick="changeAcntMakeKbn(0);" /><label for="acntMake1"><gsmsg:write key="wml.31" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150acntMakeKbn" value="<%= String.valueOf(GSConstWebmail.KANRI_USER_ONLY) %>" styleId="acntMake2" onclick="changeAcntMakeKbn(1);" /><label for="acntMake2"><gsmsg:write key="wml.70" /></label>
    </td>
    </tr>

    <tr id="settingServerArea">
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.253" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="wml.wml150.17" /></span><br><br>
      <html:radio name="wml150Form" property="wml150settingServer" value="<%= String.valueOf(GSConstWebmail.WAD_SETTING_SERVER_NO) %>" styleId="settingServer1" /><label for="settingServer1"><gsmsg:write key="cmn.not.permit" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150settingServer" value="<%= String.valueOf(GSConstWebmail.WAD_SETTING_SERVER_YES) %>" styleId="settingServer2" /><label for="settingServer2"><gsmsg:write key="cmn.permit" /></label>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="cmn.format." /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="wml.wml150.01" /></span><br><br>
      <html:radio name="wml150Form" property="wml150acntSendFormat" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_NOSET) %>" styleId="acntSendFormat1" /><label for="acntSendFormat1"><gsmsg:write key="wml.31" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150acntSendFormat" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_TEXT) %>" styleId="acntSendFormat2" /><label for="acntSendFormat2"><gsmsg:write key="wml.104" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150acntSendFormat" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_HTML) %>" styleId="acntSendFormat3" /><label for="acntSendFormat3"><gsmsg:write key="wml.109" /></label>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.23" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="wml.wml150.02" /></span><br><br>
      <html:radio name="wml150Form" property="wml150acntLogRegist" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_NOSET) %>" styleId="acntLogRegist1" /><label for="acntLogRegist1"><gsmsg:write key="wml.12" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150acntLogRegist" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_TEXT) %>" styleId="acntLogRegist2" /><label for="acntLogRegist2"><gsmsg:write key="cmn.dont.entry" /></label>
    </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.154" /></span></td>
      <td align="left" class="webmail_td1">
        <html:text name="wml150Form" property="wml150receiveServer" style="width:273px;" maxlength="100" />
        <gsmsg:write key="cmn.port.number" />
        <html:text name="wml150Form" property="wml150receiveServerPort" style="width:69px;" maxlength="5" />
        <br>
        <html:checkbox name="wml150Form" property="wml150receiveServerSsl" styleId="rcvSsl" value="<%= String.valueOf(Wml150Form.RECEIVE_SSL_USE) %>" /><label for="rcvSsl"><gsmsg:write key="wml.wml040.06" /></label>
        <br><span class="wml_description_text_dsp"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.80" /></span></td>
      <td align="left" class="webmail_td1">
        <html:text name="wml150Form" property="wml150sendServer" style="width:273px;" maxlength="100" />
        <gsmsg:write key="cmn.port.number" />
        <html:text name="wml150Form" property="wml150sendServerPort" style="width:69px;" maxlength="5" />
        <br>
        <html:checkbox name="wml150Form" property="wml150sendServerSsl" styleId="sendSsl" value="<%= String.valueOf(Wml150Form.SEND_SSL_USE) %>" /><label for="sendSsl"><gsmsg:write key="wml.wml040.06" /></label>
        <br><span class="wml_description_text_dsp"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.246" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150sendMaxSizeKbn" styleId="sendMaxSize1" value="0" onclick="changeSendMaxSize(0);" /><label for="sendMaxSize1"><gsmsg:write key="wml.31" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150sendMaxSizeKbn" styleId="sendMaxSize2" value="1" onclick="changeSendMaxSize(1);" /><label for="sendMaxSize2"><gsmsg:write key="wml.32" /></label>
        <span id="sendMaxSize">
        &nbsp;<html:text name="wml150Form" property="wml150sendMaxSize" style="width:75px;" maxlength="6" />MB&nbsp;
        <br><span class="text_description"><gsmsg:write key="cmn.comments" /> <gsmsg:write key="wml.wml150.05" /></span>
        <br><span class="text_description"><gsmsg:write key="cmn.comments" /> <gsmsg:write key="wml.wml150.06" /></span>
        </span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.13" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150bcc" styleId="bcc1" value="0" onclick="wml150changeBcc(0);" /><label for="bcc1"><gsmsg:write key="man.no.limit" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150bcc" styleId="bcc2" value="1" onclick="wml150changeBcc(1);" /><label for="bcc2"><gsmsg:write key="cmn.coerced" /></label>
        <span id="bccThresholdArea">
          &nbsp;&nbsp;<gsmsg:write key="cmn.threshold" />:&nbsp;
          <html:select name="wml150Form" property="wml150bccThreshold">
            <html:optionsCollection  name="wml150Form" property="bccThresholdList" value="value" label="label" />
          </html:select>
          <gsmsg:write key="cmn.number" />
          <br>
          <span class="wml_description_text_dsp">
          <gsmsg:write key="cmn.comments" /><gsmsg:write key="wml.wml150.14" />
          </span>
        </span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.proxyuser" /></span></td>
      <td align="left" class="webmail_td1">
      <html:radio name="wml150Form" property="wml150proxyUser" value="<%= String.valueOf(GSConstWebmail.WAD_PROXY_USER_NO) %>" styleId="proxyUser1" /><label for="proxyUser1"><gsmsg:write key="cmn.not.permit" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150proxyUser" value="<%= String.valueOf(GSConstWebmail.WAD_PROXY_USER_YES) %>" styleId="proxyUser2" /><label for="proxyUser2"><gsmsg:write key="cmn.permit" /></label>
      </td>
    </tr>

    <tr id="fwLimitLine">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.10" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td align="left" class="webmail_td1">
        <span class="text_base"><gsmsg:write key="wml.wml150.07" /></span>
        <br><br>
        <html:radio name="wml150Form" property="wml150FwLimit" styleId="wml150FwLimit0" value="0" onclick="changeFwLimit(0);"/><label for="wml150FwLimit0"><gsmsg:write key="wml.transfer.limit.01" /></label>&nbsp;
        <html:radio name="wml150Form" property="wml150FwLimit" styleId="wml150FwLimit1" value="1" onclick="changeFwLimit(1);"/><label for="wml150FwLimit1"><gsmsg:write key="wml.transfer.limit.02" /></label>&nbsp;
        <html:radio name="wml150Form" property="wml150FwLimit" styleId="wml150FwLimit2" value="2" onclick="changeFwLimit(2);"/><label for="wml150FwLimit2"><gsmsg:write key="wml.transfer.limit.03" /></label>&nbsp;

        <div id="fwLimitDeleteArea" style="margin-top: 3px;">
          &nbsp;<html:checkbox styleId="fwLimitDelete" name="wml150Form" property="wml150FwLimitDelete" value="1" /><label for="fwLimitDelete"><gsmsg:write key="wml.transfer.limit.04" /></label>
        </div>

        <div id="fwLimitArea">
          <span class="text_base6"><gsmsg:write key="wml.wml150.08" /></span>
          <br><span class="sc_ttl_sun"><gsmsg:write key="sml.sml110.03" /></span>
          <br>
          <html:textarea name="wml150Form" property="wml150FwLimitText" style="width:579px;" rows="6"></html:textarea>

          <table class="tl0" width="100%" style="margin-top:5px;">
          <tr>
          <td width="100%"  align="center"><input type="button" name="check" onclick="buttonPush('filterCheck');" value="<gsmsg:write key="wml.wml150.11" />" class="btn_base0"></td>
          </tr>

          <logic:notEmpty name="wml150Form" property="fwCheckList">
          <tr>
          <td>
            <br>
            <span class="text_base2"><gsmsg:write key="wml.wml150.12" />
            <table width="99%" class="tl0">
            <tr>
            <td class="table_bg_A5B4E1" align="center" width="30%"><span class="text_bb1"><gsmsg:write key="wml.96" /></span></td>
            <td class="table_bg_A5B4E1" align="center" width="35%"><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
            <td class="table_bg_A5B4E1" align="center" width="20%"><span class="text_bb1"><gsmsg:write key="wml.201" /></span></td>
            <td class="table_bg_A5B4E1" align="center" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.user" /></span></td>
            </tr>

            <% String tdColor = null; %>
            <% String[] tdColors = new String[] {"td_type1", "td_type8"}; %>
            <logic:iterate id="filterData" name="wml150Form" property="fwCheckList" indexId="idx">
              <% tdColor = tdColors[(idx.intValue() % 2)]; %>
              <tr>
              <td class="<%= tdColor %>"><bean:write name="filterData" property="wacName" /></td>
              <td class="<%= tdColor %>"><bean:write name="filterData" property="filterName" /></td>
              <td class="<%= tdColor %>"><bean:write name="filterData" property="fwAddress" /></td>
              <td class="<%= tdColor %>"><bean:write name="filterData" property="userNameSei" />&nbsp;<bean:write name="filterData" property="userNameMei" /></td>
              </tr>
            </logic:iterate>

            </table>
          </td>
          </tr>
          </logic:notEmpty>

          </table>
        </div>
      </td>
    </tr>

    </table>

    <table id="detail_settings" class="tl0" width="100%" cellpadding="5">
    <tr>
    <td width="25%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.permit.select" /></span></td>
    <td align="left" class="webmail_td1">
      <html:radio name="wml150Form" property="wml150permitKbn" styleId="wml150permitKbn1" value="0" onclick="lmtEnableDisable();" /><label for="wml150permitKbn1"><gsmsg:write key="cmn.set.the.admin" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150permitKbn" styleId="wml150permitKbn2" value="1" onclick="lmtEnableDisable();" /><label for="wml150permitKbn2"><gsmsg:write key="cmn.set.eachaccount" /></label>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.87" /></span></td>
    <td align="left" class="webmail_td1">
      <html:radio name="wml150Form" property="wml150diskSize" styleId="disk1" value="0" onclick="changeInputDiskSize(0);" /><label for="disk1"><gsmsg:write key="wml.31" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150diskSize" styleId="disk2" value="1" onclick="changeInputDiskSize(1);" /><label for="disk2"><gsmsg:write key="wml.32" /></label>
      <span id="inputDiskSize">&nbsp;<html:text name="wml150Form" property="wml150diskSizeLimit" style="width:75px;" maxlength="6" />MB&nbsp;</span>
      <span id="inputDiskSize2"><html:checkbox name="wml150Form" property="wml150diskSizeComp" styleId="sizeComp" onclick="changeDefLabel();"/><label for="sizeComp"><gsmsg:write key="cmn.force" /></label></span>
      <br><span id="lmtinput1"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
    </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.15" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150warnDisk" styleId="warnDisk1" value="0" onclick="wml150changeWarnDisk(0);" /><label for="warnDisk1"><gsmsg:write key="cmn.notset" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150warnDisk" styleId="warnDisk2" value="1" onclick="wml150changeWarnDisk(1);" /><label for="warnDisk2"><gsmsg:write key="cmn.warning2" /></label>
        <span id="warnDiskThresholdArea">
          &nbsp;&nbsp;<gsmsg:write key="cmn.threshold" />:&nbsp;
          <html:select name="wml150Form" property="wml150warnDiskThreshold">
            <html:optionsCollection  name="wml150Form" property="warnDiskThresholdList" value="value" label="label" />
          </html:select>
          %
          <br>
          <span class="wml_description_text_dsp">
          <gsmsg:write key="cmn.comments" /><gsmsg:write key="wml.wml150.16" />
          </span>
        </span>
      </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.36" /></span></td>
    <td align="left" class="webmail_td1">
      <html:radio name="wml150Form" property="wml150delReceive" styleId="delReceive1" value="1" /><label for="delReceive1"><gsmsg:write key="wml.60" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150delReceive" styleId="delReceive2" value="0" /><label for="delReceive2"><gsmsg:write key="cmn.dont.delete" /></label>
      <br><span id="lmtinput2"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.50" /></span></td>
    <td align="left" class="webmail_td1">
      <html:radio name="wml150Form" property="wml150autoResv" styleId="autoResv1" value="1" onclick="changeAutoRsvTime(1);" /><label for="autoResv1"><gsmsg:write key="wml.48" /></label>
      &nbsp;<html:radio name="wml150Form" property="wml150autoResv" styleId="autoResv2" value="0" onclick="changeAutoRsvTime(0);" /><label for="autoResv2"><gsmsg:write key="wml.49" /></label>
      <br><span id="lmtinput3"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
    </td>
    </tr>

    <tr id="autoRsvTime">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.auto.receive.time" /></span></td>
      <td align="left" class="webmail_td1">
        <html:select property="wml150AutoReceiveTime">
          <html:optionsCollection name="wml150Form" property="wml150AutoRsvKeyLabel" value="value" label="label" />
        </html:select>
        <br><span id="lmtinput4"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.238" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150checkAddress" styleId="checkAddress1" value="1" /><label for="checkAddress1"><gsmsg:write key="cmn.check.2" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150checkAddress" styleId="checkAddress2" value="0" /><label for="checkAddress2"><gsmsg:write key="cmn.notcheck" /></label>
        <br><span id="lmtinput5"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.239" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150checkFile" styleId="checkFile1" value="1" /><label for="checkFile1"><gsmsg:write key="cmn.check.2" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150checkFile" styleId="checkFile2" value="0" /><label for="checkFile2"><gsmsg:write key="cmn.notcheck" /></label>
        <br><span id="lmtinput6"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150compressFile" styleId="compressFile1" value="1" onclick="changeCompressFile(1);" /><label for="compressFile1"><gsmsg:write key="cmn.compress" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150compressFile" styleId="compressFile2" value="0" onclick="changeCompressFile(0);" /><label for="compressFile2"><gsmsg:write key="cmn.not.compress" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150compressFile" styleId="compressFile3" value="2" onclick="changeCompressFile(2);" /><label for="compressFile3"><gsmsg:write key="cmn.setting.from.screen" /></label>
        <br><span id="lmtinput7"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr id="compressDefArea">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
      <td align="left" class="webmail_td1">
          <html:radio name="wml150Form" property="wml150compressFileDef" styleId="compressFileDef1" value="1" /><label for="compressFileDef1"><gsmsg:write key="cmn.compress" /></label>
          &nbsp;<html:radio name="wml150Form" property="wml150compressFileDef" styleId="compressFileDef2" value="0" /><label for="compressFileDef2"><gsmsg:write key="cmn.not.compress" /></label>
        <br><span id="lmtinput8"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" /></span></td>
      <td align="left" class="webmail_td1">
        <html:radio name="wml150Form" property="wml150timeSent" styleId="timeSent1" value="1" onclick="changeTimesent(1);" /><label for="timeSent1"><gsmsg:write key="cmn.effective" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150timeSent" styleId="timeSent2" value="0" onclick="changeTimesent(0);" /><label for="timeSent2"><gsmsg:write key="cmn.invalid" /></label>
        &nbsp;<html:radio name="wml150Form" property="wml150timeSent" styleId="timeSent3" value="2" onclick="changeTimesent(2);" /><label for="timeSent3"><gsmsg:write key="cmn.setting.from.screen" /></label>
        <br><span id="lmtinput9"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>

    <tr id="timeSentDefArea">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
      <td align="left" class="webmail_td1">
          <html:radio name="wml150Form" property="wml150timeSentDef" styleId="timeSentDef1" value="1" /><label for="timeSentDef1"><gsmsg:write key="wml.241" /></label>
          &nbsp;<html:radio name="wml150Form" property="wml150timeSentDef" styleId="timeSentDef2" value="0" /><label for="timeSentDef2"><gsmsg:write key="wml.276" /></label>
        <br><span id="lmtinput10"><gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.view.account.defaultset" /></span>
      </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.space" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" name="btn_add" class="btn_ok1" value="<gsmsg:write key="cmn.ok" />" onClick="buttonPush('confirm');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('admTool');">
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