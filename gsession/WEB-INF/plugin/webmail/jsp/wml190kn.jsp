<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.wml.wml190kn.Wml190knForm" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.97" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/webmail/wml190kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden property="wmlCmdMode" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wml190initFlg" />
<html:hidden property="wml190sign" />
<html:hidden property="wml190name" />
<html:hidden property="wml190receiveServer" />
<html:hidden property="wml190receiveServerPort" />
<html:hidden property="wml190receiveServerSsl" />
<html:hidden property="wml190receiveServerType" />
<html:hidden property="wml190receiveServerUser" />
<html:hidden property="wml190receiveServerPassword" />
<html:hidden property="wml190sendServer" />
<html:hidden property="wml190sendServerPort" />
<html:hidden property="wml190sendServerSsl" />
<html:hidden property="wml190smtpAuth" />
<html:hidden property="wml190sendServerUser" />
<html:hidden property="wml190sendServerPassword" />
<html:hidden property="wml190autoTo" />
<html:hidden property="wml190autoCc" />
<html:hidden property="wml190autoBcc" />
<html:hidden property="wml190theme" />
<html:hidden property="wml190quotes" />

<logic:notEmpty name="wml190knForm" property="wml190proxyUser">
<logic:iterate id="proxyUser" name="wml190knForm" property="wml190proxyUser">
  <input type="hidden" name="wml190proxyUser" value="<bean:write name="proxyUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="wml100sortAccount" />


<bean:define id="acctMode" name="wml190knForm" property="wmlAccountMode" type="java.lang.Integer" />
<bean:define id="wCmdMode" name="wml190knForm" property="wmlCmdMode" type="java.lang.Integer" />
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
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.97" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="40%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="60%">
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
  </td>
  </tr>

  <tr>
  <td>

  <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
  <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="wml.96" /></span></td>
  <td align="left" class="webmail_td1" width="750">
  <bean:write name="wml190knForm" property="wml190name" />
  </td>
  </tr>

<logic:equal name="wml190knForm" property="wml190settingServer" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_SETTING_SERVER_YES) %>">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.81" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml190knForm" property="wml190receiveServer" />&nbsp;<gsmsg:write key="cmn.port.number" />:<bean:write name="wml190knForm" property="wml190receiveServerPort" />
      <logic:equal name="wml190knForm" property="wml190receiveServerSsl" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAC_RECEIVE_SSL_USE) %>"><br><gsmsg:write key="wml.105" /></logic:equal>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.43" /></span></td>
    <td align="left" class="webmail_td1">
    <bean:write name="wml190knForm" property="wml190receiveServerUser" />
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
      <bean:write name="wml190knForm" property="wml190sendServer" />&nbsp;<gsmsg:write key="cmn.port.number" />:<bean:write name="wml190knForm" property="wml190sendServerPort" />
      <logic:equal name="wml190knForm" property="wml190sendServerSsl" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAC_RECEIVE_SSL_USE) %>"><br><gsmsg:write key="wml.105" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.106" /></span></td>
    <td align="left" class="webmail_td1">
    <logic:equal name="wml190knForm" property="wml190smtpAuth" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAC_SMTPAUTH_YES) %>">
    <gsmsg:write key="wml.07" />
    </logic:equal>
    <logic:notEqual name="wml190knForm" property="wml190smtpAuth" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAC_SMTPAUTH_YES) %>">
    <gsmsg:write key="wml.08" />
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.78" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="wml190knForm" property="wml190sendServerUser" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.79" /></span></td>
    <td align="left" class="webmail_td1">
    <logic:notEmpty name="wml190knForm" property="wml190sendServerPassword">
      *****
    </logic:notEmpty>
    </td>
    </tr>
</logic:equal>

  <tr>
  <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.34" /></span></td>
  <td align="left" class="webmail_td1">
        <logic:notEmpty name="wml190knForm" property="wml190signList">
         <table class="tl0" width="99%" style="margin-top: 5px;">

          <logic:iterate id="signData" name="wml190knForm" property="wml190signList" indexId="signIdx" type="org.apache.struts.util.LabelValueBean">
          <% String signNo = signData.getValue(); String signClass = "wml_sign_td2"; if (signIdx.intValue() % 2 == 0) { signClass = "wml_sign_td1"; }%>
          <tr>
          <td width="98%" class="<%= signClass %>">
          &nbsp;<bean:write name="signData" property="label" /></td>
          </tr>
          </logic:iterate>
         </table>
        </logic:notEmpty>
  </td>
  </tr>

  <tr>
  <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.52" /></span></td>
  <td align="left" class="webmail_td1">
    <bean:write name="wml190knForm" property="wml190autoTo" />
  </td>
  </tr>

  <tr>
  <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.53" /></span></td>
  <td align="left" class="webmail_td1">
    <bean:write name="wml190knForm" property="wml190autoCc" />
  </td>
  </tr>

  <tr>
  <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.54" /></span></td>
  <td align="left" class="webmail_td1" width="70%">
    <bean:write name="wml190knForm" property="wml190autoBcc" />
  </td>
  </tr>

  <tr>
  <td width="250" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.theme" /></span></td>
  <td align="left" class="webmail_td1">
    <bean:write name="wml190knForm" property="wml190knTheme" />
  </td>
  </tr>
  <tr>
  <td width="250" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.quotes" /></span></td>
  <td align="left" class="webmail_td1">
    <bean:write name="wml190knForm" property="wml190knQuotes" />
  </td>
  </tr>

  <logic:equal name="wml190knForm" property="wml190proxyUserFlg" value="true">
  <tr>
  <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.proxyuser" /></span></td>
  <td align="left" class="webmail_td1">
    <logic:notEmpty name="wml190knForm" property="proxyUserSelectCombo">
      <ul>
        <logic:iterate id="proxyUserKbnLabel" name="wml190knForm" property="proxyUserSelectCombo">
        <li><bean:write name="proxyUserKbnLabel" property="label" /></li>
        </logic:iterate>
      </ul>
    </logic:notEmpty>
  </td>
  </tr>
  </logic:equal>

  </table>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
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