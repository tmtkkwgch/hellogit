<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml280kn.Wml280knForm" %>
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
<title>[Group Session] <gsmsg:write key="wml.wml010.35" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<gsjsmsg:js filename="gsjsmsg.js"/>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/yui/yahoo-dom-event/yahoo-dom-event.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/yui/animation/animation-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/fader.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/editorSubWindow.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/webmailSubWindow.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml290.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<logic:empty name="wml290Form" property="wml290destAddressList">
  <body class="body_03">
</logic:empty>
<logic:notEmpty name="wml290Form" property="wml290destAddressList">
  <body class="body_03" onload="setWml290WebmailData();">
</logic:notEmpty>

<html:form action="/webmail/wml290">

<input type="hidden" name="CMD" value="">

<html:hidden property="wmlCmdMode" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wmlEditDestList" />
<html:hidden property="wml270keyword" />
<html:hidden property="wml270pageTop" />
<html:hidden property="wml270pageBottom" />
<html:hidden property="wml270pageDspFlg" />
<html:hidden property="wml270svKeyword" />
<html:hidden property="wml270sortKey" />
<html:hidden property="wml270order" />
<html:hidden property="wml270searchFlg" />
<html:hidden property="wml280initFlg" />
<html:hidden property="wml280name" />
<html:hidden property="wml280biko" />

<html:hidden property="wml290initFlg" />
<html:hidden property="wml290webmailAddress" />

<logic:notEmpty name="wml290Form" property="wml280destUser">
<logic:iterate id="destUser" name="wml290Form" property="wml280destUser">
  <input type="hidden" name="wml280destUser" value="<bean:write name="destUser" />">
</logic:iterate>
</logic:notEmpty>
</span>

<logic:notEmpty name="wml290Form" property="wml280destAddress">
<logic:iterate id="destAddress" name="wml290Form" property="wml280destAddress">
  <input type="hidden" name="wml280destAddress" value="<bean:write name="destAddress" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="wml290Form" property="wml290destAddressList">
  <bean:define id="webmailAddressName" name="wml290Form" property="wml290webmailAddress" type="java.lang.String" />
  <%
    String destAddressName = "wml290Atsk";
    if (webmailAddressName.equals("wml010sendAddressCc")) {
        destAddressName = "wml290Cc";
    } else if (webmailAddressName.equals("wml010sendAddressBcc")) {
        destAddressName = "wml290Bcc";
    }
  %>

  <logic:iterate id="destAddress" name="wml290Form" property="wml290destAddressList">
    <input type="hidden" name="<%= destAddressName %>" value="<bean:write name="destAddress" />">
  </logic:iterate>
</logic:notEmpty>


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="95%">
  <tr>
  <td>


    <table cellpadding="0" cellspacing="0" border="0" width="99%">
      <tr>
        <td width="0%"><img src="../webmail/images/header_webmail.gif" border="0" alt="<gsmsg:write key="wml.wml010.35" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="wml.wml010.35" /></span></td>
        <td width="100%" class="header_white_bg mailSearch">&nbsp;</td>
      <td width="0%" class="header_white_bg">
        <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="buttonPush('decision');">
        <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td>
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="wml.wml010.35" />"></td>
      </tr>
    </table>
  </td>
  </tr>

  <logic:messagesPresent message="false">
    <tr>
    <td>
    <table width="100%" style="margin-top: 5px;">
      <tr><td align="left"><html:errors/></td></tr>
    </table>
    </td>
    </tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <span id="wml290SetMailMsg"></span>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table id="wml_settings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="250" valign="top" style="background-color: #eeeeee;">
        <logic:notEmpty name="wml290Form" property="wml290destlistCombo">
        <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

        <logic:iterate id="destlistData" name="wml290Form" property="wml290destlistCombo">
          <tr>
          <td align="left" class="webmail_td1">
            <a href="#" onClick="wml290SelectDestlist(<bean:write name="destlistData" property="value" />)"><bean:write name="destlistData" property="label" /></a>
          </td>
          </tr>
        </logic:iterate>

        </table>
        </logic:notEmpty>
      </td>

      <td width="750" valign="top">

        <logic:notEmpty name="wml290Form" property="destUserList">
        <gsmsg:write key="wml.263" />: <bean:write name="wml290Form" property="wml280name" />

        <table class="tl0" boreder="0" width="99%">
          <tr>
            <td class="table_bg_A5B4E1" align="center" width="1%"><input type="checkbox" name="wml030AllCheck" value="1" onClick="chgCheckAll('wml030AllCheck', 'wml280destUserSelect');chgCheckAllChange('wml030AllCheck', 'wml030selectAcount');"></td>
            <td class="table_bg_A5B4E1" align="center" width="45%"><span class="text_bb1"><gsmsg:write key="cmn.name" />/<gsmsg:write key="cmn.post" /></span></td>
            <td class="table_bg_A5B4E1" align="center" width="45%"><span class="text_bb1"><gsmsg:write key="cmn.company.name" />/<gsmsg:write key="address.10" /></span></td>
            <td class="table_bg_A5B4E1" align="center" width="54%"><span class="text_bb1"><gsmsg:write key="cmn.mailaddress" /></span></td>
          </tr>

          <logic:iterate id="destUserData" name="wml290Form" property="destUserList">
          <tr>
            <td class="table_bg_A5B4E1" align="center">
              <html:multibox name="wml290Form" property="wml280destUserSelect">
                <bean:write name="destUserData" property="destId" />
              </html:multibox>
            </td>
            <td class="td_type1">
              <span class="text_base">
                  <logic:notEmpty name="destUserData" property="yakusyoku">
                  <bean:write name="destUserData" property="yakusyoku" /><br>
                  </logic:notEmpty>
                  <bean:write name="destUserData" property="name" />

              </span>
            </td>

            <td class="td_type1">
              <span class="text_base">
                  &nbsp;<bean:write name="destUserData" property="acoName" />
                  <logic:notEmpty name="destUserData" property="abaName">
                  <br>&nbsp;&nbsp;&nbsp;<bean:write name="destUserData" property="abaName" />
                  </logic:notEmpty>
                </span>
            </td>
            <td class="td_type1">
              <span class="text_base"><bean:write name="destUserData" property="mailAddress" /></span>
            </td>
          </tr>
          </logic:iterate>
        </table>
        </logic:notEmpty>
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
          <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td>
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

</body>
</html:html>