<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml040kn.Wml040knForm" %>
<%-- 定数値 --%>
<%
  String  acModeNormal    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.ACCOUNTMODE_NORMAL);
  String  acModePsn       = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.ACCOUNTMODE_PSNLSETTING);
  String  acModeCommon    = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.ACCOUNTMODE_COMMON);
  String  cmdModeAdd      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.CMDMODE_ADD);
  String  cmdModeEdit     = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.CMDMODE_EDIT);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml040kn.05" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../webmail/js/wml040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script>
function connectionEnd(){
  document.getElementById("connection").style.display="none";
  document.getElementById("connectionEnd").style.display="block";
}

function connectionTest(){
  document.getElementById("connection").style.display="block";
  document.getElementById("connectionEnd").style.display="none";
  setTimeout (connectionEnd, 2000);
}
</script>
</head>

<bean:define id="webmailAdmin" name="wml040knForm" property="wml040webmailAdmin" type=" java.lang.Boolean" />
<% boolean adminUserFlg = webmailAdmin.booleanValue(); %>

<body class="body_03" onload="more(<bean:write name="wml040knForm" property="wml040elementKbn" />);changeAutoRsvTime(<bean:write name="wml040knForm" property="wml040autoResv" />);">

<html:form action="/webmail/wml040kn">

<logic:notEqual name="wml040knForm" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040knForm" property="wmlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="0">
 </logic:equal>
</logic:notEqual>

<logic:equal name="wml040knForm" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040knForm" property="wmlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="1">
 </logic:equal>
</logic:equal>

<logic:notEqual name="wml040knForm" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040knForm" property="wmlCmdMode" value="<%= cmdModeEdit %>">
  <input type="hidden" name="helpPrm" value="2">
 </logic:equal>
</logic:notEqual>

<logic:equal name="wml040knForm" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040knForm" property="wmlCmdMode" value="<%= cmdModeEdit %>">
  <input type="hidden" name="helpPrm" value="3">
 </logic:equal>
</logic:equal>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden property="wmlCmdMode" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wml030keyword" />
<html:hidden property="wml030group" />
<html:hidden property="wml030user" />
<html:hidden property="wml030pageTop" />
<html:hidden property="wml030pageBottom" />

<html:hidden property="wml040initFlg" />
<html:hidden property="wml040elementKbn" />

<html:hidden property="wml040name" />
<html:hidden property="wml040mailAddress" />
<html:hidden property="wml040receiveServer" />
<html:hidden property="wml040receiveServerPort" />
<html:hidden property="wml040receiveServerSsl" />
<html:hidden property="wml040receiveServerType" />
<html:hidden property="wml040receiveServerUser" />
<html:hidden property="wml040receiveServerPassword" />
<html:hidden property="wml040receiveServerSsl" />
<html:hidden property="wml040sendServer" />
<html:hidden property="wml040sendServerPort" />
<html:hidden property="wml040sendServerSsl" />
<html:hidden property="wml040sendServerUser" />
<html:hidden property="wml040sendServerPassword" />
<html:hidden property="wml040sendServerSsl" />
<html:hidden property="wml040diskSize" />
<html:hidden property="wml040diskSizeLimit" />
<html:hidden property="wml040diskSizeComp" />
<html:hidden property="wml040diskSps" />
<html:hidden property="wml040biko" />
<html:hidden property="wml040userKbn" />
<html:hidden property="wml040organization" />
<html:hidden property="wml040sign" />
<html:hidden property="wml040signNo" />
<html:hidden property="wml040autoTo" />
<html:hidden property="wml040autoCc" />
<html:hidden property="wml040autoBcc" />
<html:hidden property="wml040delReceive" />
<html:hidden property="wml040reReceive" />
<html:hidden property="wml040apop" />
<html:hidden property="wml040smtpAuth" />
<html:hidden property="wml040popBSmtp" />
<html:hidden property="wml040encodeSend" />
<html:hidden property="wml040autoResv" />
<html:hidden property="wml040sendType" />
<html:hidden property="wml040receiveDsp" />
<html:hidden property="wml040signPoint" />
<html:hidden property="wml040AutoReceiveTime" />
<html:hidden property="wml040PermitKbn" />

<bean:define id="wmlPermitKbn" name="wml040knForm" property="wml040PermitKbn" type="java.lang.Integer" />
<% int permitKbn = wmlPermitKbn.intValue(); %>

<logic:equal name="wml040knForm" property="wml040userKbn" value="1">
  <html:hidden property="wml040userKbnUserGroup" />
</logic:equal>

<logic:notEmpty name="wml040knForm" property="wml040userKbnGroup">
<logic:iterate id="permitId" name="wml040knForm" property="wml040userKbnGroup">
  <input type="hidden" name="wml040userKbnGroup" value="<bean:write name="permitId" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="wml040knForm" property="wml040userKbnUser">
<logic:iterate id="permitId" name="wml040knForm" property="wml040userKbnUser">
  <input type="hidden" name="wml040userKbnUser" value="<bean:write name="permitId" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="wml040knForm" property="wml040proxyUser">
<logic:iterate id="proxyUser" name="wml040knForm" property="wml040proxyUser">
  <input type="hidden" name="wml040proxyUser" value="<bean:write name="proxyUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="wml040theme" />
<html:hidden property="wml040checkAddress" />
<html:hidden property="wml040checkFile" />
<html:hidden property="wml040compressFile" />
<html:hidden property="wml040compressFileDef" />
<html:hidden property="wml040timeSent" />
<html:hidden property="wml040timeSentDef" />
<html:hidden property="wml040quotes" />

<html:hidden property="wml100sortAccount" />
<html:hidden property="wml030keyword" />
<html:hidden property="wml030group" />
<html:hidden property="wml030user" />
<html:hidden property="wml030svKeyword" />
<html:hidden property="wml030svGroup" />
<html:hidden property="wml030svUser" />
<html:hidden property="wml030sortKey" />
<html:hidden property="wml030order" />
<html:hidden property="wml030searchFlg" />

<bean:define id="acctMode" name="wml040knForm" property="wmlAccountMode" type="java.lang.Integer" />
<bean:define id="wCmdMode" name="wml040knForm" property="wmlCmdMode" type="java.lang.Integer" />
<% int accountMode = acctMode.intValue(); %>
<% int cmdMode = wCmdMode.intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <% if (accountMode == 2 && cmdMode == 0) { %>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml040kn.05" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } else if (accountMode == 2 && cmdMode == 1) { %>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.97" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } else if (accountMode != 2 && cmdMode == 0) { %>
          <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml040kn.05" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } else if (accountMode != 2 && cmdMode == 1) { %>
          <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.97" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } %>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="40%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="60%">
          <input type="button" value="<gsmsg:write key="wml.wml040kn.04" />" class="btn_base1" onClick="buttonPush('connectTest');">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
    <div id="connection" style="text-align:center;padding:10px;display:none;"><img src="../webmail/js/assets/progress.gif" alt=""><gsmsg:write key="wml.wml040kn.02" /></div>
    <div id="connectionEnd" style="text-align:center;padding:10px;display:none;font-weight:bold;"><gsmsg:write key="wml.wml040kn.03" /></div>
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

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr id="normal" align="left">
        <td class="now_forcus wml_tab_td2" nowrap><gsmsg:write key="cmn.preferences" /></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(1);"><gsmsg:write key="wml.sign.setting" /></a></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(2);"><gsmsg:write key="wml.receive.setting" /></a></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(3);"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="sign" align="left">
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(0);"><gsmsg:write key="cmn.preferences" /></a></td>
        <td class="now_forcus wml_tab_td2" nowrap><gsmsg:write key="wml.sign.setting" /></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(2);"><gsmsg:write key="wml.receive.setting" /></a></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(3);"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="receive" align="left">
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(0);"><gsmsg:write key="cmn.preferences" /></a></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(1);"><gsmsg:write key="wml.sign.setting" /></a></td>
        <td class="now_forcus wml_tab_td2" nowrap><gsmsg:write key="wml.receive.setting" /></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(3);"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="other" align="left">
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(0);"><gsmsg:write key="cmn.preferences" /></a></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(1);"><gsmsg:write key="wml.sign.setting" /></a></td>
        <td class="none_forcus wml_tab_td2" nowrap><a href="#" onclick="more(2);"><gsmsg:write key="wml.receive.setting" /></a></td>
        <td class="now_forcus wml_tab_td2" nowrap><gsmsg:write key="cmn.other" /></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
    </table>

    <table id="wml_settings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="35%" nowrap><span class="text_bb1"><gsmsg:write key="wml.96" /></span></td>
    <td align="left" class="webmail_td1" width="65%">
    <bean:write name="wml040knForm" property="wml040name" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.mailaddress" /></span></td>
    <td align="left" class="webmail_td1">
    <bean:write name="wml040knForm" property="wml040mailAddress" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.81" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml040knForm" property="wml040receiveServer" />&nbsp;<gsmsg:write key="cmn.port.number" />:<bean:write name="wml040knForm" property="wml040receiveServerPort" />
      <logic:equal name="wml040knForm" property="wml040receiveServerSsl" value="<%= String.valueOf(Wml040knForm.RECEIVE_SSL_USE) %>"><br><gsmsg:write key="wml.105" /></logic:equal>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.43" /></span></td>
    <td align="left" class="webmail_td1">
    <bean:write name="wml040knForm" property="wml040receiveServerUser" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.44" /></span></td>
    <td align="left" class="webmail_td1">
      *****
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.80" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml040knForm" property="wml040sendServer" />&nbsp;<gsmsg:write key="cmn.port.number" />:<bean:write name="wml040knForm" property="wml040sendServerPort" />
      <logic:equal name="wml040knForm" property="wml040sendServerSsl" value="<%= String.valueOf(Wml040knForm.SEND_SSL_USE) %>"><br><gsmsg:write key="wml.105" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.106" /></span></td>
    <td align="left" class="webmail_td1">
    <logic:equal name="wml040knForm" property="wml040smtpAuth" value="1">
    <gsmsg:write key="wml.07" />
    </logic:equal>
    <logic:notEqual name="wml040knForm" property="wml040smtpAuth" value="1">
    <gsmsg:write key="wml.08" />
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.78" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml040knForm" property="wml040sendServerUser" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.79" /></span></td>
    <td align="left" class="webmail_td1">
    <logic:notEmpty name="wml040knForm" property="wml040sendServerPassword">
      *****
    </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.87" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:define id="diskSizeComp" name="wml040knForm" property="wml040diskSizeComp" type="java.lang.Integer" />
      <bean:define id="diskSps" name="wml040knForm" property="wml040diskSps" type="java.lang.Integer" />
      <% if ((permitKbn == 0 || diskSizeComp == 1) && diskSps == 0) { %>
        <logic:equal name="wml040knForm" property="wml040admDisk" value="0">
          <gsmsg:write key="wml.31" />
        </logic:equal>
        <logic:notEqual name="wml040knForm" property="wml040admDisk" value="0">
          <span id="inputDiskSize"><gsmsg:write key="wml.32" />&nbsp;<bean:write name="wml040knForm" property="wml040admDiskSize" />MB</span>
        </logic:notEqual>

      <% } else { %>
        <logic:equal name="wml040knForm" property="wml040diskSize" value="1">
        <gsmsg:write key="wml.32" />&nbsp;<bean:write name="wml040knForm" property="wml040diskSizeLimit" />MB
        </logic:equal>
        <logic:notEqual name="wml040knForm" property="wml040diskSize" value="1">
        <gsmsg:write key="wml.31" />
        </logic:notEqual>
      <% } %>

      <% if (adminUserFlg && (permitKbn == 0 || diskSizeComp == 1) && diskSps == 1) { %>
        <span style="width: 99%; margin-left: 30px;" align="left">
          <gsmsg:write key="cmn.spsetting" />
        </span>
      <% } %>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml040knForm" property="wml040knBiko" filter="false" />
    </td>
    </tr>

    <logic:equal name="wml040knForm" property="wmlAccountMode" value="2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.employer" /></span></td>
    <td align="left" class="webmail_td1">
    <logic:equal name="wml040knForm" property="wml040userKbn" value="<%= String.valueOf(jp.groupsession.v2.wml.wml040kn.Wml040knForm.USERKBN_GROUP) %>">
      <p><gsmsg:write key="wml.94" /></p>
      <logic:notEmpty name="wml040knForm" property="userKbnGroupSelectCombo">
        <ul>
          <logic:iterate id="userKbnLabel" name="wml040knForm" property="userKbnGroupSelectCombo">
          <li><bean:write name="userKbnLabel" property="label" /></li>
          </logic:iterate>
        </ul>
      </logic:notEmpty>
    </logic:equal>
    <logic:notEqual name="wml040knForm" property="wml040userKbn" value="<%= String.valueOf(jp.groupsession.v2.wml.wml040kn.Wml040knForm.USERKBN_GROUP) %>">
      <p><gsmsg:write key="wml.77" /></p>
      <logic:notEmpty name="wml040knForm" property="userKbnUserSelectCombo">
        <ul>
          <logic:iterate id="userKbnLabel" name="wml040knForm" property="userKbnUserSelectCombo">
          <li><bean:write name="userKbnLabel" property="label" /></li>
          </logic:iterate>
        </ul>
      </logic:notEmpty>
    </logic:notEqual>
    </td>
    </tr>
    </logic:equal>

    <logic:equal name="wml040knForm" property="wml040proxyUserFlg" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.proxyuser" /></span></td>
    <td align="left" class="webmail_td1">
      <logic:notEmpty name="wml040knForm" property="proxyUserSelectCombo">
        <ul>
          <logic:iterate id="proxyUserKbnLabel" name="wml040knForm" property="proxyUserSelectCombo">
          <li><bean:write name="proxyUserKbnLabel" property="label" /></li>
          </logic:iterate>
        </ul>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    </table>

    <table id="signSettings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td class="table_bg_A5B4E1" width="35%" nowrap><span class="text_bb1"><gsmsg:write key="wml.25" /></span></td>
      <td align="left" class="webmail_td1" width="65%">
        <bean:write name="wml040knForm" property="wml040organization" />
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.34" /></span></td>
      <td align="left" class="webmail_td1">

        <logic:notEmpty name="wml040knForm" property="wml040signList">
         <div id="right_label">
         <table class="tl0" width="99%" style="margin-top: 5px;">

          <logic:iterate id="signData" name="wml040knForm" property="wml040signList" indexId="signIdx" type="org.apache.struts.util.LabelValueBean">
          <% String signNo = signData.getValue(); String signClass = "wml_sign_td2"; if (signIdx.intValue() % 2 == 0) { signClass = "wml_sign_td1"; }%>
          <tr>
          <td width="99%" class="<%= signClass %>">
          &nbsp;<bean:write name="signData" property="label" /></td>
          </tr>
          </logic:iterate>

         </table>
         </div>
        </logic:notEmpty>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.sign.point" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:notEqual name="wml040knForm" property="wml040signPoint" value="1">
      <gsmsg:write key="wml.sign.top" />
      </logic:notEqual>
      <logic:equal name="wml040knForm" property="wml040signPoint" value="1">
      <gsmsg:write key="wml.sign.bottom" />
      </logic:equal>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.receive.sign" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040receiveDsp" value="1">
      <gsmsg:write key="cmn.display.ok" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040receiveDsp" value="1">
      <gsmsg:write key="cmn.dont.show" />
      </logic:notEqual>
      </td>
      </tr>
    </table>

    <table id="moreSettings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.52" /></span></td>
      <td align="left" class="webmail_td1">
        <bean:write name="wml040knForm" property="wml040autoTo" />
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.53" /></span></td>
      <td align="left" class="webmail_td1">
        <bean:write name="wml040knForm" property="wml040autoCc" />
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.54" /></span></td>
      <td align="left" class="webmail_td1" width="70%">
        <bean:write name="wml040knForm" property="wml040autoBcc" />
      </td>
      </tr>

    <% if (permitKbn == 1) { %>
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.36" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040delReceive" value="1">
      <gsmsg:write key="wml.60" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040delReceive" value="1">
      <gsmsg:write key="cmn.dont.delete" />
      </logic:notEqual>
      </td>
      </tr>
    <% } %>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.39" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040reReceive" value="1">
      <gsmsg:write key="wml.41" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040reReceive" value="1">
      <gsmsg:write key="wml.42" />
      </logic:notEqual>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.111" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040apop" value="1">
      <gsmsg:write key="wml.112" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040apop" value="1">
      <gsmsg:write key="wml.113" />
      </logic:notEqual>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.17" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040popBSmtp" value="1">
      <gsmsg:write key="wml.07" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040popBSmtp" value="1">
      <gsmsg:write key="wml.08" />
      </logic:notEqual>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml040kn.01" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040encodeSend" value="0">
      <gsmsg:write key="wml.108" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040encodeSend" value="0">
      <gsmsg:write key="wml.103" />
      </logic:notEqual>
      </td>
      </tr>

    <% if (permitKbn == 1) { %>
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.50" /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040autoResv" value="1">
      <gsmsg:write key="wml.48" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040autoResv" value="1">
      <gsmsg:write key="wml.49" />
      </logic:notEqual>
      </td>
      </tr>

      <tr id="autoRsvTime">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.auto.receive.time" /></span></td>
      <td align="left" class="webmail_td1">
        <bean:write name="wml040knForm" property="wml040AutoReceiveTime" /><gsmsg:write key="cmn.minute" />
      </td>
      </tr>
    <% } %>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.format." /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="wml040knForm" property="wml040sendType" value="0">
      <gsmsg:write key="cmn.standard" />
      </logic:equal>
      <logic:notEqual name="wml040knForm" property="wml040sendType" value="0">
      <gsmsg:write key="wml.110" />
      </logic:notEqual>
      </td>
      </tr>

    <% if (permitKbn == 1) { %>
      <tr id="checkAddress">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.238" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml040knForm" property="wml040checkAddress" value="1">
          <gsmsg:write key="cmn.check.2" />
        </logic:equal>
        <logic:notEqual name="wml040knForm" property="wml040checkAddress" value="1">
          <gsmsg:write key="cmn.notcheck" />
        </logic:notEqual>
      </td>
      </tr>

      <tr id="checkFile">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.239" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml040knForm" property="wml040checkFile" value="1">
          <gsmsg:write key="cmn.check.2" />
        </logic:equal>
        <logic:notEqual name="wml040knForm" property="wml040checkFile" value="1">
          <gsmsg:write key="cmn.notcheck" />
        </logic:notEqual>
      </td>
      </tr>

      <tr id="compressFile">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml040knForm" property="wml040compressFile" value="1">
          <gsmsg:write key="cmn.compress" />
        </logic:equal>
        <logic:equal name="wml040knForm" property="wml040compressFile" value="0">
           <gsmsg:write key="cmn.not.compress" />
        </logic:equal>
        <logic:equal name="wml040knForm" property="wml040compressFile" value="2">
           <gsmsg:write key="cmn.setting.from.screen" />
        </logic:equal>
      </td>
      </tr>

      <logic:equal name="wml040knForm" property="wml040compressFile" value="2">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
      <td align="left" class="webmail_td1">
          <logic:equal name="wml040knForm" property="wml040compressFileDef" value="1">
           <gsmsg:write key="cmn.compress" />
          </logic:equal>
          <logic:notEqual name="wml040knForm" property="wml040compressFileDef" value="1">
            <gsmsg:write key="cmn.not.compress" />
          </logic:notEqual>
      </td>
      </tr>
      </logic:equal>

      <tr id="timeSent">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml040knForm" property="wml040timeSent" value="1">
          <gsmsg:write key="cmn.effective" />
        </logic:equal>
        <logic:equal name="wml040knForm" property="wml040timeSent" value="0">
           <gsmsg:write key="cmn.invalid" />
        </logic:equal>
        <logic:equal name="wml040knForm" property="wml040timeSent" value="2">
           <gsmsg:write key="cmn.setting.from.screen" />
        </logic:equal>
      </td>
      </tr>

      <logic:equal name="wml040knForm" property="wml040timeSent" value="2">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
      <td align="left" class="webmail_td1">
            <logic:equal name="wml040knForm" property="wml040timeSentDef" value="1">
              <gsmsg:write key="wml.241" />
            </logic:equal>
            <logic:notEqual name="wml040knForm" property="wml040timeSentDef" value="1">
              <gsmsg:write key="wml.276" />
            </logic:notEqual>
      </td>
      </tr>
      </logic:equal>

    <% } %>

    </table>

    <table id="otherSettings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="250" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.theme" /></span></td>
      <td align="left" class="webmail_td1">
        <bean:write name="wml040knForm" property="wml040knTheme" />
      </td>
      </tr>
      <tr>
      <td width="250" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.quotes" /></span></td>
      <td align="left" class="webmail_td1">
        <bean:write name="wml040knForm" property="wml040knQuotes" />
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="wml.wml040kn.04" />" class="btn_base1" onClick="buttonPush('connectTest');">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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