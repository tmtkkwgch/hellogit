<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.cmn.GSConstWebmail" %>
<%@ page import="jp.groupsession.v2.wml.wml040.Wml040Form" %>
<%-- 定数値 --%>
<%
  String  acModeNormal    = String.valueOf(GSConstWebmail.ACCOUNTMODE_NORMAL);
  String  acModePsn       = String.valueOf(GSConstWebmail.ACCOUNTMODE_PSNLSETTING);
  String  acModeCommon    = String.valueOf(GSConstWebmail.ACCOUNTMODE_COMMON);
  String  cmdModeAdd      = String.valueOf(GSConstWebmail.CMDMODE_ADD);
  String  cmdModeEdit     = String.valueOf(GSConstWebmail.CMDMODE_EDIT);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml040.05" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../webmail/js/wml040.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml041.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<bean:define id="webmailAdmin" name="wml040Form" property="wml040webmailAdmin" type=" java.lang.Boolean" />
<% boolean adminUserFlg = webmailAdmin.booleanValue(); %>

<bean:define id="wmlPermitKbn" name="wml040Form" property="wml040PermitKbn" type="java.lang.Integer" />
<% int permitKbn = wmlPermitKbn.intValue(); %>
<% if (permitKbn != 0) { %>
  <body class="body_03" onunload="windowClose();wml041Close();" onload="changeInputDiskSize(<bean:write name="wml040Form" property="wml040diskSize" />);change(<bean:write name="wml040Form" property="wml040userKbn" />, <bean:write name="wml040Form" property="wmlAccountMode" />);more(<bean:write name="wml040Form" property="wml040elementKbn" />);initDiskArea(<%= adminUserFlg %>);changeSendServerAuth(<bean:write name="wml040Form" property="wml040smtpAuth" />);changeAutoRsvTime(<bean:write name="wml040Form" property="wml040autoResv" />);changeCompressFile(<bean:write name="wml040Form" property="wml040compressFile" />);changeTimesent(<bean:write name="wml040Form" property="wml040timeSent" />);">
<% } else { %>
  <body class="body_03" onunload="windowClose();wml041Close();" onload="change(<bean:write name="wml040Form" property="wml040userKbn" />, <bean:write name="wml040Form" property="wmlAccountMode" />);more(<bean:write name="wml040Form" property="wml040elementKbn" />);initDiskArea(<%= adminUserFlg %>);changeSendServerAuth(<bean:write name="wml040Form" property="wml040smtpAuth" />);changeCompressFile(<bean:write name="wml040Form" property="wml040compressFile" />);changeTimesent(<bean:write name="wml040Form" property="wml040timeSent" />);">
<% } %>

<html:form action="/webmail/wml040">
<logic:notEqual name="wml040Form" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040Form" property="wmlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="0">
 </logic:equal>
</logic:notEqual>

<logic:equal name="wml040Form" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040Form" property="wmlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="1">
 </logic:equal>
</logic:equal>

<logic:notEqual name="wml040Form" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040Form" property="wmlCmdMode" value="<%= cmdModeEdit %>">
  <input type="hidden" name="helpPrm" value="2">
 </logic:equal>
</logic:notEqual>

<logic:equal name="wml040Form" property="wmlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="wml040Form" property="wmlCmdMode" value="<%= cmdModeEdit %>">
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
<html:hidden property="wml010adminUser" />
<html:hidden property="wml040initFlg" />
<html:hidden property="wml040elementKbn" />
<html:hidden property="wml040PermitKbn" />
<html:hidden property="wml040diskSizeComp" />
<html:hidden property="wml100sortAccount" />
<html:hidden property="wml030keyword" />
<html:hidden property="wml030group" />
<html:hidden property="wml030user" />
<html:hidden property="wml030pageTop" />
<html:hidden property="wml030pageBottom" />
<html:hidden property="wml030svKeyword" />
<html:hidden property="wml030svGroup" />
<html:hidden property="wml030svUser" />
<html:hidden property="wml030sortKey" />
<html:hidden property="wml030order" />
<html:hidden property="wml030searchFlg" />
<html:hidden property="wml040PermitKbn" />

<% if (permitKbn == 0) { %>
  <html:hidden property="wml040AutoReceiveTime" />
  <html:hidden property="wml040delReceive" />
  <html:hidden property="wml040autoResv" />
<% } %>

<bean:define id="acctMode" name="wml040Form" property="wmlAccountMode" type="java.lang.Integer" />
<bean:define id="wCmdMode" name="wml040Form" property="wmlCmdMode" type="java.lang.Integer" />
<% int accountMode = acctMode.intValue(); %>
<% int cmdMode = wCmdMode.intValue(); %>

<logic:notEmpty name="wml040Form" property="wml040userKbnGroup">
<logic:iterate id="accountGroup" name="wml040Form" property="wml040userKbnGroup">
  <input type="hidden" name="wml040userKbnGroup" value="<bean:write name="accountGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="wml040Form" property="wml040userKbnUser">
<logic:iterate id="accountUser" name="wml040Form" property="wml040userKbnUser">
  <input type="hidden" name="wml040userKbnUser" value="<bean:write name="accountUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="wml040Form" property="wml040proxyUser">
<logic:iterate id="proxyUser" name="wml040Form" property="wml040proxyUser">
  <input type="hidden" name="wml040proxyUser" value="<bean:write name="proxyUser" />">
</logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="75%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <% if (accountMode == 2 && cmdMode == 0) { %>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml040.05" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } else if (accountMode == 2 && cmdMode == 1) { %>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.98" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } else if (accountMode != 2 && cmdMode == 0) { %>
          <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml040.05" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } else if (accountMode != 2 && cmdMode == 1) { %>
          <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.98" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
        <% } %>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
          <logic:equal name="wml040Form" property="wmlCmdMode" value="1">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteAccount');">
          </logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('beforePage');">
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
    <span id="errorArea"></span>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

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
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="wml.96" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt" width="750">
          <html:text name="wml040Form" property="wml040name" style="width:273px;" maxlength="<%= String.valueOf(GSConstWebmail.MAXLEN_ACCOUNT) %>" />
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.mailaddress" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt">
          <html:text name="wml040Form" property="wml040mailAddress" style="width:273px;" maxlength="256" />
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.81" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <html:text name="wml040Form" property="wml040receiveServer" style="width:273px;" maxlength="100" />
          <gsmsg:write key="cmn.port.number" />
          <html:text name="wml040Form" property="wml040receiveServerPort" style="width:69px;" maxlength="5" />
          <br>
          <html:checkbox name="wml040Form" property="wml040receiveServerSsl" styleId="rcvSsl" value="<%= String.valueOf(Wml040Form.RECEIVE_SSL_USE) %>" /><label for="rcvSsl"><gsmsg:write key="wml.wml040.06" /></label>
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.43" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt">
          <html:text name="wml040Form" property="wml040receiveServerUser" style="width:273px;" maxlength="100" />
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.44" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt">
          <html:password name="wml040Form" property="wml040receiveServerPassword" style="width:273px;" maxlength="100" />
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.80" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <html:text name="wml040Form" property="wml040sendServer" style="width:273px;" maxlength="100" />
          <gsmsg:write key="cmn.port.number" />
          <html:text name="wml040Form" property="wml040sendServerPort" style="width:69px;" maxlength="5" />
          <br>
          <html:checkbox name="wml040Form" property="wml040sendServerSsl" styleId="sendSsl" value="<%= String.valueOf(Wml040Form.SEND_SSL_USE) %>" /><label for="sendSsl"><gsmsg:write key="wml.wml040.06" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.106" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040smtpAuth" styleId="smtpAuth1" value="1" onclick="changeSendServerAuth(1);" /><label for="smtpAuth1"><gsmsg:write key="wml.07" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040smtpAuth" styleId="smtpAuth2" value="0" onclick="changeSendServerAuth(0);" /><label for="smtpAuth2"><gsmsg:write key="wml.08" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.78" /></span></td>
        <td align="left" class="td_wt">
          <html:text name="wml040Form" property="wml040sendServerUser" style="width:273px;" maxlength="100" styleId="wml040sendServerUser" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.79" /></span></td>
        <td align="left" class="td_wt">
          <html:password name="wml040Form" property="wml040sendServerPassword" style="width:273px;" maxlength="100" styleId="wml040sendServerPassword" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.87" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <span id="diskSizeInputArea">
            <html:radio name="wml040Form" property="wml040diskSize" styleId="disk1" value="0" onclick="changeInputDiskSize(0);" /><label for="disk1"><gsmsg:write key="wml.31" /></label>
            &nbsp;<html:radio name="wml040Form" property="wml040diskSize" styleId="disk2" value="1" onclick="changeInputDiskSize(1);" /><label for="disk2"><gsmsg:write key="wml.32" /></label>
            <span id="inputDiskSize">&nbsp;<html:text name="wml040Form" property="wml040diskSizeLimit" style="width:75px;" maxlength="6" />MB</span>
          </span>
          <span id="diskSizeViewArea">
            <html:hidden property="wml040diskSize" />
            <html:hidden property="wml040diskSizeLimit" />

            <bean:define id="diskSizeComp" name="wml040Form" property="wml040diskSizeComp" type="java.lang.Integer" />
            <bean:define id="diskSps" name="wml040Form" property="wml040diskSps" type="java.lang.Integer" />
          <% if ((permitKbn == 0 || diskSizeComp == 1) && (adminUserFlg || (!adminUserFlg && diskSps == 0))) { %>
            <logic:equal name="wml040Form" property="wml040admDisk" value="0">
              <gsmsg:write key="wml.31" />
            </logic:equal>
            <logic:notEqual name="wml040Form" property="wml040admDisk" value="0">
              <span id="inputDiskSize"><gsmsg:write key="wml.32" />&nbsp;<bean:write name="wml040Form" property="wml040admDiskSize" />MB</span>
            </logic:notEqual>
          <% } else { %>
            <logic:equal name="wml040Form" property="wml040diskSize" value="0">
              <gsmsg:write key="wml.31" />
            </logic:equal>
            <logic:notEqual name="wml040Form" property="wml040diskSize" value="0">
            <span id="inputDiskSize"><gsmsg:write key="wml.32" />&nbsp;<bean:write name="wml040Form" property="wml040diskSizeLimit" />MB</span>
            </logic:notEqual>
          <% } %>
          </span>
          <% if (adminUserFlg && (permitKbn == 0 || diskSizeComp == 1)) { %>
          <span style="width: 99%; margin-left: 30px;" align="left">
            <html:checkbox styleId="diskSps" name="wml040Form" property="wml040diskSps" value="1" onclick="changeDiskSps();" /><label for="diskSps"><gsmsg:write key="cmn.spsetting" /></label>
          </span>
          <% } %>
          <% if (!adminUserFlg) { %>
            <html:hidden name="wml040Form" property="wml040diskSps" />
          <% } %>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="webmail_td1">
          <html:textarea name="wml040Form" property="wml040biko" rows="10" style="width:369px;" />
        </td>
      </tr>

      <logic:equal name="wml040Form" property="wmlAccountMode" value="2">
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.employer" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <p>
          <html:radio name="wml040Form" property="wml040userKbn" styleId="usrKbn1" value="0" onclick="change(0, 2);" /><label for="usrKbn1"><gsmsg:write key="wml.94" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040userKbn" styleId="usrKbn2" value="1" onclick="change(1, 2);" /><label for="usrKbn2"><gsmsg:write key="wml.77" /></label>
          </p>
          <table id="permissionGroup">
            <tr>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.02" /></span></td>
              <td width="0%"></td>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.04" /></span></td>
            </tr>
            <tr>
              <td class="td_type1">
                <html:select name="wml040Form" property="wml040userKbnGroupSelect" size="7" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml040Form" property="userKbnGroupSelectCombo">
                  <html:optionsCollection name="wml040Form" property="userKbnGroupSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
              <td valign="middle">
                <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="return buttonPush('addGroup');">
                <img src="../common/images/spacer.gif" width="20" height="30">
                <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="return buttonPush('deleteGroup');">
              </td>
              <td class="td_type1">
                <html:select name="wml040Form" property="wml040userKbnGroupNoSelect" size="7" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml040Form" property="userKbnGroupNoSelectCombo">
                  <html:optionsCollection name="wml040Form" property="userKbnGroupNoSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
          </table>
          <table id="permissionUser" style="display:none;">
            <tr>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.01" /></span></td>
              <td width="0%"></td>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.03" /></span></td>
            </tr>
            <tr>
              <td class="td_type1">
                <html:select name="wml040Form" property="wml040userKbnUserSelect" size="11" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml040Form" property="userKbnUserSelectCombo">
                  <html:optionsCollection name="wml040Form" property="userKbnUserSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
              <td valign="middle">
                <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="return buttonPush('addUser');">
                <img src="../common/images/spacer.gif" width="20" height="30">
                <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="return buttonPush('deleteUser');">
              </td>
              <td class="td_type1">
                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.wml040userKbnUserGroup, 'wml040userKbnUserGroup', '<bean:write name="wml040Form" property="wml040userKbnUserGroup" />', '0', 'init', 'wml040userKbnUser', '-1', '0')" type="button">
                <html:select name="wml040Form" property="wml040userKbnUserGroup" styleClass="webmail_select01" onchange="buttonPush('init');">
                  <html:optionsCollection name="wml040Form" property="userKbnGroupCombo" value="value" label="label" />
                </html:select>
                <input type="button" onclick="openGroupWindow(this.form.wml040userKbnUserGroup, 'wml040userKbnUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="wml040GroupBtn">
                <html:select name="wml040Form" property="wml040userKbnUserNoSelect" size="9" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml040Form" property="userKbnUserNoSelectCombo">
                  <html:optionsCollection name="wml040Form" property="userKbnUserNoSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </logic:equal>

      <logic:equal name="wml040Form" property="wml040proxyUserFlg" value="true">
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.proxyuser" /></span></td>
        <td align="left" class="webmail_td1">
          <table>
            <tr>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="cmn.proxyuser" /></span></td>
              <td width="0%"></td>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.03" /></span></td>
            </tr>
            <tr>
              <td class="td_type1">
                <html:select name="wml040Form" property="wml040proxyUserSelect" size="11" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml040Form" property="proxyUserSelectCombo">
                  <html:optionsCollection name="wml040Form" property="proxyUserSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
              <td valign="middle">
                <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="return buttonPush('addProxyUser');">
                <img src="../common/images/spacer.gif" width="20" height="30">
                <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="return buttonPush('deleteProxyUser');">
              </td>
              <td class="td_type1">
                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.wml040proxyUserGroup, 'wml040proxyUserGroup', '<bean:write name="wml040Form" property="wml040proxyUserGroup" />', '0', 'init', 'wml040proxyUser', '-1', '0')" type="button">
                <html:select name="wml040Form" property="wml040proxyUserGroup" styleClass="webmail_select01" onchange="buttonPush('init');">
                  <html:optionsCollection name="wml040Form" property="userKbnGroupCombo" value="value" label="label" />
                </html:select>
                <input type="button" onclick="openGroupWindow(this.form.wml040proxyUserGroup, 'wml040proxyUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="wml040proxyGroupBtn">
                <html:select name="wml040Form" property="wml040proxyUserNoSelect" size="9" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml040Form" property="proxyUserNoSelectCombo">
                  <html:optionsCollection name="wml040Form" property="proxyUserNoSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </logic:equal>

    </table>

    <table id="signSettings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="wml.25" /></span></td>
        <td align="left" class="td_wt" width="750">
          <html:text name="wml040Form" property="wml040organization" style="width:273px;" maxlength="<%= String.valueOf(GSConstWebmail.MAXLEN_ACCOUNT_ORGANIZE) %>" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.34" /></span></td>
        <td align="left" class="webmail_td1">

        <logic:empty name="wml040Form" property="wml040signList">
          <input value="<gsmsg:write key="cmn.add" />" class="btn_add_n4" id="signAddBtn" type="button" onClick="openSignWindow(0, 0);">
        </logic:empty>

        <logic:notEmpty name="wml040Form" property="wml040signList">
          <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('upSign');">
          <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush('downSign');">
          &nbsp;&nbsp;&nbsp;&nbsp;<input value="<gsmsg:write key="cmn.add" />" class="btn_add_n4" id="signAddBtn" type="button" onClick="openSignWindow(0, 0);">
          &nbsp;<input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('deleteSign');">

         <div id="right_label">
         <table class="tl0" width="99%" style="margin-top: 5px;">

          <logic:iterate id="signData" name="wml040Form" property="wml040signList" indexId="signIdx" type="org.apache.struts.util.LabelValueBean">
          <% String signNo = signData.getValue(); String signClass = "wml_sign_td2"; if (signIdx.intValue() % 2 == 0) { signClass = "wml_sign_td1"; }%>
          <tr>
          <td width="99%" class="<%= signClass %>"><html:radio name="wml040Form" property="wml040signNo" value="<%= signNo %>"/>
          &nbsp;<a href="#" onClick="openSignWindowEdit(<%= signNo %>);"><bean:write name="signData" property="label" /></a></td>
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
          <html:radio name="wml040Form" property="wml040signPoint" styleId="signPlace" value="0" /><label for="signPlace"><gsmsg:write key="wml.sign.top" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040signPoint" styleId="signPlace2" value="1" /><label for="signPlace2"><gsmsg:write key="wml.sign.bottom" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.receive.sign" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040receiveDsp" styleId="receiveDsp" value="1" /><label for="receiveDsp"><gsmsg:write key="cmn.display.ok" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040receiveDsp" styleId="receiveDsp2" value="0" /><label for="receiveDsp2"><gsmsg:write key="cmn.dont.show" /></label>
        </td>
      </tr>
      </table>
      <table id="moreSettings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.52" /></span></td>
        <td align="left" class="td_wt" width="70%">
          <html:text name="wml040Form" property="wml040autoTo" style="width:273px;" maxlength="256" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.53" /></span></td>
        <td align="left" class="td_wt" width="70%">
          <html:text name="wml040Form" property="wml040autoCc" style="width:273px;" maxlength="256" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.54" /></span></td>
        <td align="left" class="td_wt" width="70%">
          <html:text name="wml040Form" property="wml040autoBcc" style="width:273px;" maxlength="256" />
        </td>
      </tr>

      <% if (permitKbn == 1) { %>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.36" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040delReceive" styleId="delReceive1" value="1" /><label for="delReceive1"><gsmsg:write key="wml.60" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040delReceive" styleId="delReceive2" value="0" /><label for="delReceive2"><gsmsg:write key="cmn.dont.delete" /></label>
        </td>
      </tr>
      <% } %>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.39" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040reReceive" styleId="reReceive1" value="1" /><label for="reReceive1"><gsmsg:write key="wml.41" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040reReceive" styleId="reReceive2" value="0" /><label for="reReceive2"><gsmsg:write key="wml.42" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.111" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040apop" styleId="apop1" value="1" /><label for="apop1"><gsmsg:write key="wml.112" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040apop" styleId="apop2" value="0" /><label for="apop2"><gsmsg:write key="wml.113" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.17" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040popBSmtp" styleId="popBSmtp1" value="1" /><label for="popBSmtp1"><gsmsg:write key="wml.07" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040popBSmtp" styleId="popBSmtp2" value="0" /><label for="popBSmtp2"><gsmsg:write key="wml.08" /></label>
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml040kn.01" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040encodeSend" styleId="encodeSend1" value="0" /><label for="encodeSend1"><gsmsg:write key="wml.108" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040encodeSend" styleId="encodeSend2" value="1" /><label for="encodeSend2"><gsmsg:write key="wml.103" /></label>
        </td>
      </tr>

      <% if (permitKbn == 1) { %>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.50" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040autoResv" styleId="autoResv1" value="1" onclick="changeAutoRsvTime(1);" /><label for="autoResv1"><gsmsg:write key="wml.48" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040autoResv" styleId="autoResv2" value="0" onclick="changeAutoRsvTime(0);" /><label for="autoResv2"><gsmsg:write key="wml.49" /></label>
        </td>
      </tr>

      <tr id="autoRsvTime">
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.auto.receive.time" /></span></td>
        <td align="left" class="webmail_td1">
          <html:select property="wml040AutoReceiveTime">
            <html:optionsCollection name="wml040Form" property="wml040AutoRsvKeyLabel" value="value" label="label" />
          </html:select>
        </td>
      </tr>
      <% } %>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.format." /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040sendType" styleId="sendType1" value="0" /><label for="sendType1"><gsmsg:write key="cmn.standard" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040sendType" styleId="sendType2" value="1" /><label for="sendType2"><gsmsg:write key="wml.110" /></label>
        </td>
      </tr>

      <% if (permitKbn == 1) { %>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.238" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040checkAddress" styleId="checkAddress1" value="1" /><label for="checkAddress1"><gsmsg:write key="cmn.check.2" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040checkAddress" styleId="checkAddress2" value="0" /><label for="checkAddress2"><gsmsg:write key="cmn.notcheck" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.239" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040checkFile" styleId="checkFile1" value="1" /><label for="checkFile1"><gsmsg:write key="cmn.check.2" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040checkFile" styleId="checkFile2" value="0" /><label for="checkFile2"><gsmsg:write key="cmn.notcheck" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040compressFile" styleId="compressFile1" value="1" onclick="changeCompressFile(1);" /><label for="compressFile1"><gsmsg:write key="cmn.compress" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040compressFile" styleId="compressFile2" value="0" onclick="changeCompressFile(0);" /><label for="compressFile2"><gsmsg:write key="cmn.not.compress" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040compressFile" styleId="compressFile3" value="2" onclick="changeCompressFile(2);" /><label for="compressFile3"><gsmsg:write key="cmn.setting.from.screen" /></label>
        </td>
      </tr>

      <tr id="compressDefArea">
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
        <td align="left" class="webmail_td1">
            <html:radio name="wml040Form" property="wml040compressFileDef" styleId="compressFileDef1" value="1" /><label for="compressFileDef1"><gsmsg:write key="cmn.compress" /></label>
            &nbsp;<html:radio name="wml040Form" property="wml040compressFileDef" styleId="compressFileDef2" value="0" /><label for="compressFileDef2"><gsmsg:write key="cmn.not.compress" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml040Form" property="wml040timeSent" styleId="timeSent1" value="1" onclick="changeTimesent(1);" /><label for="timeSent1"><gsmsg:write key="cmn.effective" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040timeSent" styleId="timeSent2" value="0" onclick="changeTimesent(0);" /><label for="timeSent2"><gsmsg:write key="cmn.invalid" /></label>
          &nbsp;<html:radio name="wml040Form" property="wml040timeSent" styleId="timeSent3" value="2" onclick="changeTimesent(2);" /><label for="timeSent3"><gsmsg:write key="cmn.setting.from.screen" /></label>
        </td>
      </tr>

      <tr id="timeSentDefArea">
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
        <td align="left" class="webmail_td1">
            <html:radio name="wml040Form" property="wml040timeSentDef" styleId="timeSentDef1" value="1" /><label for="timeSentDef1"><gsmsg:write key="wml.241" /></label>
            &nbsp;<html:radio name="wml040Form" property="wml040timeSentDef" styleId="timeSentDef2" value="0" /><label for="timeSentDef2"><gsmsg:write key="wml.276" /></label>
        </td>
      </tr>
      <% } %>

    </table>

    <table id="otherSettings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="250"  class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.theme" /></span></td>
        <td  align="left" class="webmail_td1">
          <html:select name="wml040Form" property="wml040theme">
            <logic:notEmpty name="wml040Form" property="wml040themeList">
              <html:optionsCollection name="wml040Form" property="wml040themeList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
        </td>
      </tr>
      <tr>
        <td width="250"  class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.quotes" /></span></td>
        <td  align="left" class="webmail_td1">
          <html:select name="wml040Form" property="wml040quotes">
            <logic:notEmpty name="wml040Form" property="wml040quotesList">
              <html:optionsCollection name="wml040Form" property="wml040quotesList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
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
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
          <logic:equal name="wml040Form" property="wmlCmdMode" value="1">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteAccount');">
          </logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('beforePage');">
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