<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConstWebmail" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml150kn.Wml150knForm" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml150.04" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml150.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03" onload="more(<bean:write name="wml150knForm" property="wml150elementKbn" />);">

<html:form action="/webmail/wml150kn">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="wmlViewAccount" />
<html:hidden property="wml150acntMakeKbn" />
<html:hidden property="wml150acntSendFormat" />
<html:hidden property="wml150acntLogRegist" />
<html:hidden property="wml150initFlg" />
<html:hidden property="wml150elementKbn" />
<html:hidden property="wml150permitKbn" />
<html:hidden property="wml150diskSize" />
<html:hidden property="wml150diskSizeLimit" />
<html:hidden property="wml150diskSizeComp" />
<html:hidden property="wml150warnDisk" />
<html:hidden property="wml150warnDiskThreshold" />
<html:hidden property="wml150delReceive" />
<html:hidden property="wml150autoResv" />
<html:hidden property="wml150AutoReceiveTime" />
<html:hidden property="wml150receiveServer" />
<html:hidden property="wml150receiveServerPort" />
<html:hidden property="wml150receiveServerSsl" />
<html:hidden property="wml150sendServer" />
<html:hidden property="wml150sendServerPort" />
<html:hidden property="wml150sendServerSsl" />
<html:hidden property="wml150sendMaxSizeKbn" />
<html:hidden property="wml150sendMaxSize" />
<html:hidden property="wml150FwLimit" />
<html:hidden property="wml150FwLimitText" />
<html:hidden property="wml150svFwLimitText" />
<html:hidden property="wml150FwLimitDelete" />

<html:hidden property="wml150checkAddress" />
<html:hidden property="wml150checkFile" />
<html:hidden property="wml150compressFile" />
<html:hidden property="wml150compressFileDef" />
<html:hidden property="wml150timeSent" />
<html:hidden property="wml150timeSentDef" />
<html:hidden property="wml150bcc" />
<html:hidden property="wml150bccThreshold" />
<html:hidden property="wml150settingServer" />
<html:hidden property="wml150proxyUser" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml150.04" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

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
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.101" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150acntMakeKbn" value="<%= String.valueOf(GSConstWebmail.KANRI_USER_NO) %>">
      <gsmsg:write key="wml.31" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150acntMakeKbn" value="<%= String.valueOf(GSConstWebmail.KANRI_USER_ONLY) %>">
      <gsmsg:write key="wml.70" />
      </logic:equal>
      </span>
    </td>
    </tr>

<logic:equal name="wml150knForm" property="wml150acntMakeKbn" value="<%= String.valueOf(GSConstWebmail.KANRI_USER_ONLY) %>">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.253" /></span></td>
    <td align="left" class="webmail_td1">
      <logic:equal name="wml150knForm" property="wml150settingServer" value="<%= String.valueOf(GSConstWebmail.WAD_SETTING_SERVER_YES) %>">
        <gsmsg:write key="cmn.permit" />
      </logic:equal>
      <logic:notEqual name="wml150knForm" property="wml150settingServer" value="<%= String.valueOf(GSConstWebmail.WAD_SETTING_SERVER_YES) %>">
        <gsmsg:write key="cmn.not.permit" />
      </logic:notEqual>
    </td>
    </tr>
</logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="cmn.format." /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150acntSendFormat" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_NOSET) %>">
      <gsmsg:write key="wml.31" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150acntSendFormat" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_TEXT) %>">
      <gsmsg:write key="wml.104" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150acntSendFormat" value="<%= String.valueOf(GSConstWebmail.ACNT_SENDFORMAT_HTML) %>">
      <gsmsg:write key="wml.109" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.23" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150acntLogRegist" value="<%= String.valueOf(GSConstWebmail.ACNT_LOG_REGIST_REGIST) %>">
      <gsmsg:write key="wml.12" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150acntLogRegist" value="<%= String.valueOf(GSConstWebmail.ACNT_LOG_REGIST_NOTREGIST) %>">
      <gsmsg:write key="cmn.dont.entry" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.81" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml150knForm" property="wml150receiveServer" />&nbsp;
      <logic:notEmpty name="wml150knForm" property="wml150receiveServerPort">
      <gsmsg:write key="cmn.port.number" />:<bean:write name="wml150knForm" property="wml150receiveServerPort" />
      </logic:notEmpty>
      <logic:equal name="wml150knForm" property="wml150receiveServerSsl" value="<%= String.valueOf(Wml150knForm.RECEIVE_SSL_USE) %>"><br><gsmsg:write key="wml.105" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.80" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml150knForm" property="wml150sendServer" />&nbsp;
      <logic:notEmpty name="wml150knForm" property="wml150sendServerPort">
      <gsmsg:write key="cmn.port.number" />:<bean:write name="wml150knForm" property="wml150sendServerPort" />
      </logic:notEmpty>
      <logic:equal name="wml150knForm" property="wml150sendServerSsl" value="<%= String.valueOf(Wml150knForm.SEND_SSL_USE) %>"><br><gsmsg:write key="wml.105" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.246" /></span></td>
    <td align="left" class="webmail_td1">
      <logic:equal name="wml150knForm" property="wml150sendMaxSizeKbn" value="1">
      <gsmsg:write key="wml.32" />&nbsp;<bean:write name="wml150knForm" property="wml150sendMaxSize" />MB
      </logic:equal>
      <logic:notEqual name="wml150knForm" property="wml150sendMaxSizeKbn" value="1">
      <gsmsg:write key="wml.31" />
      </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.13" /></span></td>
    <td align="left" class="webmail_td1">
      <logic:equal name="wml150knForm" property="wml150bcc" value="1">
        <gsmsg:write key="cmn.coerced" />
        &nbsp;&nbsp;<gsmsg:write key="cmn.threshold" />:&nbsp;<bean:write name="wml150Form" property="wml150bccThreshold" /><gsmsg:write key="cmn.number" />
      </logic:equal>
      <logic:notEqual name="wml150knForm" property="wml150bcc" value="1">
         <gsmsg:write key="man.no.limit" />
      </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.proxyuser" /></span></td>
    <td align="left" class="webmail_td1">
      <logic:equal name="wml150knForm" property="wml150proxyUser" value="<%= String.valueOf(GSConstWebmail.WAD_PROXY_USER_NO) %>">
        <gsmsg:write key="cmn.not.permit" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150proxyUser" value="<%= String.valueOf(GSConstWebmail.WAD_PROXY_USER_YES) %>">
        <gsmsg:write key="cmn.permit" />
      </logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.10" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:define id="fwLimitKbn" name="wml150knForm" property="wml150FwLimit" type="java.lang.Integer" />
      <% if (fwLimitKbn.intValue() == 1) { %>
        <gsmsg:write key="wml.transfer.limit.02" />
        <br>
        <logic:iterate id="fwLimitAddress" name="wml150knForm" property="wml150knFwLimitText">
          <br>&nbsp;&nbsp;<bean:write name="fwLimitAddress" />
        </logic:iterate>
      <% } else if (fwLimitKbn.intValue() == 2) { %>
        <gsmsg:write key="wml.transfer.limit.03" />
        <logic:equal name="wml150knForm" property="wml150FwLimitDelete" value="1">
          <div style="margin-top: 3px;">
           &nbsp;<gsmsg:write key="wml.transfer.limit.04" />
          </div>
        </logic:equal>
      <% } else { %>
        <gsmsg:write key="wml.transfer.limit.01" />
      <% } %>
    </td>
    </tr>

    </table>

    <table id="detail_settings" class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.permit.select" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150permitKbn" value="<%= String.valueOf(GSConstWebmail.PERMIT_ADMIN) %>">
      <gsmsg:write key="cmn.set.the.admin" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150permitKbn" value="<%= String.valueOf(GSConstWebmail.PERMIT_ACCOUNT) %>">
      <gsmsg:write key="cmn.set.eachaccount" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.87" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150diskSize" value="<%= String.valueOf(GSConstWebmail.WAC_DISK_UNLIMITED) %>">
      <gsmsg:write key="wml.31" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150diskSize" value="<%= String.valueOf(GSConstWebmail.WAC_DISK_LIMIT) %>">
      <gsmsg:write key="wml.32" />&nbsp;<bean:write name="wml150knForm" property="wml150diskSizeLimit" />MB
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150diskSizeComp" value="true">
      &nbsp;<gsmsg:write key="cmn.force" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.wml150.15" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150warnDisk" value="<%= String.valueOf(GSConstWebmail.WAD_WARN_DISK_NO) %>">
      <gsmsg:write key="cmn.notset" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150warnDisk" value="<%= String.valueOf(GSConstWebmail.WAD_WARN_DISK_YES) %>">
      <gsmsg:write key="cmn.warning2" />&nbsp;&nbsp;
      <gsmsg:write key="cmn.threshold" />&nbsp;<bean:write name="wml150knForm" property="wml150warnDiskThreshold" />%
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.36" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150delReceive" value="<%= String.valueOf(GSConstWebmail.WAC_DELRECEIVE_YES) %>">
      <gsmsg:write key="wml.60" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150delReceive" value="<%= String.valueOf(GSConstWebmail.WAC_DELRECEIVE_NO) %>">
      <gsmsg:write key="cmn.dont.delete" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.50" /></span></td>
    <td class="td_type1">
      <span class="text_base">
      <logic:equal name="wml150knForm" property="wml150autoResv" value="<%= String.valueOf(GSConstWebmail.MAIL_AUTO_RSV_ON) %>">
      <gsmsg:write key="wml.48" />
      </logic:equal>
      <logic:equal name="wml150knForm" property="wml150autoResv" value="<%= String.valueOf(GSConstWebmail.MAIL_AUTO_RSV_OFF) %>">
      <gsmsg:write key="wml.49" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <logic:equal name="wml150knForm" property="wml150autoResv" value="<%= String.valueOf(GSConstWebmail.MAIL_AUTO_RSV_ON) %>">
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.auto.receive.time" /></span></td>
    <td class="td_type1">
      <span class="text_base"><bean:write name="wml150knForm" property="wml150AutoReceiveTime" /><gsmsg:write key="cmn.minute" /></span>
    </td>
    </tr>
    </logic:equal>

      <tr id="checkAddress">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.238" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml150knForm" property="wml150checkAddress" value="1">
          <gsmsg:write key="cmn.check.2" />
        </logic:equal>
        <logic:notEqual name="wml150knForm" property="wml150checkAddress" value="1">
          <gsmsg:write key="cmn.notcheck" />
        </logic:notEqual>
      </td>
      </tr>

      <tr id="checkFile">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.239" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml150knForm" property="wml150checkFile" value="1">
          <gsmsg:write key="cmn.check.2" />
        </logic:equal>
        <logic:notEqual name="wml150knForm" property="wml150checkFile" value="1">
          <gsmsg:write key="cmn.notcheck" />
        </logic:notEqual>
      </td>
      </tr>

      <tr id="compressFile">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml150knForm" property="wml150compressFile" value="1">
          <gsmsg:write key="cmn.compress" />
        </logic:equal>
        <logic:equal name="wml150knForm" property="wml150compressFile" value="0">
           <gsmsg:write key="cmn.not.compress" />
        </logic:equal>
        <logic:equal name="wml150knForm" property="wml150compressFile" value="2">
           <gsmsg:write key="cmn.setting.from.screen" />
        </logic:equal>
      </td>
      </tr>

      <logic:equal name="wml150knForm" property="wml150compressFile" value="2">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.240" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
      <td align="left" class="webmail_td1">
          <logic:equal name="wml150knForm" property="wml150compressFileDef" value="1">
           <gsmsg:write key="cmn.compress" />
          </logic:equal>
          <logic:notEqual name="wml150knForm" property="wml150compressFileDef" value="1">
            <gsmsg:write key="cmn.not.compress" />
          </logic:notEqual>
      </td>
      </tr>
      </logic:equal>

      <tr id="timeSent">
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" /></span></td>
      <td align="left" class="webmail_td1">
        <logic:equal name="wml150knForm" property="wml150timeSent" value="1">
          <gsmsg:write key="cmn.effective" />
        </logic:equal>
        <logic:equal name="wml150knForm" property="wml150timeSent" value="0">
           <gsmsg:write key="cmn.invalid" />
        </logic:equal>
        <logic:equal name="wml150knForm" property="wml150timeSent" value="2">
           <gsmsg:write key="cmn.setting.from.screen" />
        </logic:equal>
      </td>
      </tr>

      <logic:equal name="wml150knForm" property="wml150timeSent" value="2">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.241" />&nbsp;<gsmsg:write key="ntp.10"/></span></td>
      <td align="left" class="webmail_td1">
            <logic:equal name="wml150knForm" property="wml150timeSentDef" value="1">
              <gsmsg:write key="wml.241" />
            </logic:equal>
            <logic:notEqual name="wml150knForm" property="wml150timeSentDef" value="1">
              <gsmsg:write key="wml.276" />
            </logic:notEqual>
      </td>
      </tr>
      </logic:equal>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.space" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
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