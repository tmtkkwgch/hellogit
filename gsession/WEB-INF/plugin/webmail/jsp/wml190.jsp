<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>

<%@ page import="jp.groupsession.v2.wml.wml190.Wml190Form" %>
<%@ page import="jp.groupsession.v2.wml.wml190kn.Wml190knForm" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.98" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../webmail/js/wml041.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml190.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="changeSendServerAuth(<bean:write name="wml190Form" property="wml190smtpAuth" />);" onunload="wml041Close();">

<html:form action="/webmail/wml190">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden property="wmlCmdMode" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wml190name" />
<html:hidden property="wml190initFlg" />

<logic:notEmpty name="wml190Form" property="wml190proxyUser">
<logic:iterate id="proxyUser" name="wml190Form" property="wml190proxyUser">
  <input type="hidden" name="wml190proxyUser" value="<bean:write name="proxyUser" />">
</logic:iterate>
</logic:notEmpty>

<bean:define id="acctMode" name="wml190Form" property="wmlAccountMode" type="java.lang.Integer" />
<bean:define id="wCmdMode" name="wml190Form" property="wmlCmdMode" type="java.lang.Integer" />
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
          <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.98" /> ]</td>
          <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
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

    <table id="wml_settings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="wml.96" /></span></td>
        <td align="left" class="webmail_td1" width="750">
          <bean:write name="wml190Form" property="wml190name" />
        </td>
      </tr>

<logic:equal name="wml190Form" property="wml190settingServer" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAD_SETTING_SERVER_YES) %>">
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.81" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <html:text name="wml190Form" property="wml190receiveServer" styleClass="width:60%;" size="40" maxlength="100" />
          <gsmsg:write key="cmn.port.number" />
          <html:text name="wml190Form" property="wml190receiveServerPort" size="6" maxlength="5" />
          <br>
          <html:checkbox name="wml190Form" property="wml190receiveServerSsl" styleId="rcvSsl" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAC_RECEIVE_SSL_USE) %>" /><label for="rcvSsl"><gsmsg:write key="wml.wml040.06" /></label>
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.43" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt">
          <html:text name="wml190Form" property="wml190receiveServerUser" styleClass="width:60%;" size="40" maxlength="100" />
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.44" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt">
          <html:password name="wml190Form" property="wml190receiveServerPassword" styleClass="width:60%;" size="40" maxlength="100" />
        </td>
      </tr>
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.80" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
          <html:text name="wml190Form" property="wml190sendServer" styleClass="width:60%;" size="40" maxlength="100" />
          <gsmsg:write key="cmn.port.number" />
          <html:text name="wml190Form" property="wml190sendServerPort" size="6" maxlength="5" />
          <br>
          <html:checkbox name="wml190Form" property="wml190sendServerSsl" styleId="sendSsl" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAC_SEND_SSL_USE) %>" /><label for="sendSsl"><gsmsg:write key="wml.wml040.06" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.106" /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="wml190Form" property="wml190smtpAuth" styleId="smtpAuth1" value="1" onclick="changeSendServerAuth(1);" /><label for="smtpAuth1"><gsmsg:write key="wml.07" /></label>
          &nbsp;<html:radio name="wml190Form" property="wml190smtpAuth" styleId="smtpAuth2" value="0" onclick="changeSendServerAuth(0);" /><label for="smtpAuth2"><gsmsg:write key="wml.08" /></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.78" /></span></td>
        <td align="left" class="td_wt">
          <html:text name="wml190Form" property="wml190sendServerUser" styleClass="width:60%;" size="40" maxlength="100" styleId="wml190sendServerUser" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.79" /></span></td>
        <td align="left" class="td_wt">
          <html:password name="wml190Form" property="wml190sendServerPassword" styleClass="width:60%;" size="40" maxlength="100" styleId="wml190sendServerPassword" />
        </td>
      </tr>
</logic:equal>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.34" /></span></td>
        <td align="left" class="webmail_td1">

        <logic:empty name="wml190Form" property="wml190signList">
          <input value="<gsmsg:write key="cmn.add" />" class="btn_add_n4" id="signAddBtn" type="button" onClick="openSignWindow(0, 0);">
        </logic:empty>

        <logic:notEmpty name="wml190Form" property="wml190signList">
          <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('upSign');">
          <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush('downSign');">
          &nbsp;&nbsp;&nbsp;&nbsp;<input value="<gsmsg:write key="cmn.add" />" class="btn_add_n4" id="signAddBtn" type="button" onClick="openSignWindow(0, 0);">
          &nbsp;<input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('deleteSign');">

         <table class="tl0" width="99%" style="margin-top: 5px;">

          <logic:iterate id="signData" name="wml190Form" property="wml190signList" indexId="signIdx" type="org.apache.struts.util.LabelValueBean">
          <% String signNo = signData.getValue(); String signClass = "wml_sign_td2"; if (signIdx.intValue() % 2 == 0) { signClass = "wml_sign_td1"; } %>
          <tr>
          <td width="99%" class="<%= signClass %>"><html:radio name="wml190Form" property="wml190sign" value="<%= signNo %>"/>
          &nbsp;<a href="#" onClick="openSignWindowEdit(<%= signNo %>);"><bean:write name="signData" property="label" /></a></td>
          </tr>
          </logic:iterate>

         </table>
        </logic:notEmpty>

        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.52" /></span></td>
        <td align="left" class="td_wt" width="70%">
          <html:text name="wml190Form" property="wml190autoTo" styleClass="width:60%;" size="40" maxlength="256" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.53" /></span></td>
        <td align="left" class="td_wt" width="70%">
          <html:text name="wml190Form" property="wml190autoCc" styleClass="width:60%;" size="40" maxlength="256" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.54" /></span></td>
        <td align="left" class="td_wt" width="70%">
          <html:text name="wml190Form" property="wml190autoBcc" styleClass="width:60%;" size="40" maxlength="256" />
        </td>
      </tr>

      <tr>
        <td width="250"  class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.theme" /></span></td>
        <td  align="left" class="webmail_td1">
          <html:select name="wml190Form" property="wml190theme">
            <logic:notEmpty name="wml190Form" property="wml190themeList">
              <html:optionsCollection name="wml190Form" property="wml190themeList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
        </td>
      </tr>
      <tr>
        <td width="250"  class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.quotes" /></span></td>
        <td  align="left" class="webmail_td1">
          <html:select name="wml190Form" property="wml190quotes">
            <logic:notEmpty name="wml190Form" property="wml190quotesList">
              <html:optionsCollection name="wml190Form" property="wml190quotesList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
        </td>
      </tr>


      <logic:equal name="wml190Form" property="wml190proxyUserFlg" value="true">
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
                <html:select name="wml190Form" property="wml190proxyUserSelect" size="11" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml190Form" property="proxyUserSelectCombo">
                  <html:optionsCollection name="wml190Form" property="proxyUserSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
              <td valign="middle">
                <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="return buttonPush('addProxyUser');">
                <img src="../common/images/spacer.gif" width="20" height="30">
                <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="return buttonPush('deleteProxyUser');">
              </td>
              <td class="td_type1">
                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.wml190proxyUserGroup, 'wml190proxyUserGroup', '<bean:write name="wml190Form" property="wml190proxyUserGroup" />', '0', 'init', 'wml190proxyUser', '-1', '0')" type="button">
                <html:select name="wml190Form" property="wml190proxyUserGroup" styleClass="webmail_select01" onchange="buttonPush('init');">
                  <html:optionsCollection name="wml190Form" property="proxyUserGroupCombo" value="value" label="label" />
                </html:select>
                <input type="button" onclick="openGroupWindow(this.form.wml190proxyUserGroup, 'wml190proxyUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="wml190proxyGroupBtn">
                <html:select name="wml190Form" property="wml190proxyUserNoSelect" size="9" styleClass="webmail_select01" multiple="true">
                  <logic:notEmpty name="wml190Form" property="proxyUserNoSelectCombo">
                  <html:optionsCollection name="wml190Form" property="proxyUserNoSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </logic:equal>

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