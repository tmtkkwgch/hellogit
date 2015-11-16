<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.sml.sml250kn.Sml250knForm" %>
<%-- 定数値 --%>
<%
  String  acModeNormal    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ACCOUNTMODE_NORMAL);
  String  acModePsn       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ACCOUNTMODE_PSNLSETTING);
  String  acModeCommon    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ACCOUNTMODE_COMMON);
  String  cmdModeAdd      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.CMDMODE_ADD);
  String  cmdModeEdit     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.CMDMODE_EDIT);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml040kn.05" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../smail/js/sml250.js?<%= GSConst.VERSION_PARAM %>"></script>
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

<body class="body_03">

<html:form styleId="sml250kn" action="/smail/sml250kn">

<logic:notEqual name="sml250knForm" property="smlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="sml250knForm" property="smlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="0">
 </logic:equal>
</logic:notEqual>

<logic:equal name="sml250knForm" property="smlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="sml250knForm" property="smlCmdMode" value="<%= cmdModeAdd %>">
  <input type="hidden" name="helpPrm" value="1">
 </logic:equal>
</logic:equal>

<logic:notEqual name="sml250knForm" property="smlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="sml250knForm" property="smlCmdMode" value="<%= cmdModeEdit %>">
  <input type="hidden" name="helpPrm" value="2">
 </logic:equal>
</logic:notEqual>

<logic:equal name="sml250knForm" property="smlAccountMode" value="<%= acModeCommon %>">
 <logic:equal name="sml250knForm" property="smlCmdMode" value="<%= cmdModeEdit %>">
  <input type="hidden" name="helpPrm" value="3">
 </logic:equal>
</logic:equal>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="smlCmdMode" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountMode" />
<html:hidden property="smlAccountSid" />
<html:hidden property="sml240keyword" />
<html:hidden property="sml240group" />
<html:hidden property="sml240user" />

<html:hidden property="sml250initFlg" />
<html:hidden property="sml250AccountKbn" />
<html:hidden property="sml250DefActUsrSid" />
<html:hidden property="sml250elementKbn" />
<html:hidden property="sml250name" />
<html:hidden property="sml250biko" />

<html:hidden property="sml250JdelKbn" />
<html:hidden property="sml250JYear" />
<html:hidden property="sml250JMonth" />
<html:hidden property="sml250SdelKbn" />
<html:hidden property="sml250SYear" />
<html:hidden property="sml250SMonth" />
<html:hidden property="sml250WdelKbn" />
<html:hidden property="sml250WYear" />
<html:hidden property="sml250WMonth" />
<html:hidden property="sml250DdelKbn" />
<html:hidden property="sml250DYear" />
<html:hidden property="sml250DMonth" />
<html:hidden property="sml250autoDelKbn" />
<html:hidden property="sml250tensoSetKbn" />
<html:hidden property="sml250tensoKbn" />
<html:hidden property="sml250SelTab" />





<logic:notEmpty name="sml250knForm" property="sml250userKbnUser">
<logic:iterate id="permitId" name="sml250knForm" property="sml250userKbnUser">
  <input type="hidden" name="sml250userKbnUser" value="<bean:write name="permitId" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="sml250theme" />
<html:hidden property="sml250quotes" />
<html:hidden property="sml250sendType" />

<html:hidden property="sml100sortAccount" />
<html:hidden property="sml240keyword" />
<html:hidden property="sml240group" />
<html:hidden property="sml240user" />
<html:hidden property="sml240svKeyword" />
<html:hidden property="sml240svGroup" />
<html:hidden property="sml240svUser" />
<html:hidden property="sml240sortKey" />
<html:hidden property="sml240order" />
<html:hidden property="sml240searchFlg" />


<logic:notEmpty name="sml250knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml250knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml250ObjKbn" />
<html:hidden property="sml250PassKbn" />
<html:hidden property="sml250groupSid" />
<html:hidden property="sml250MailFw" />
<html:hidden property="sml250MailDf" />
<html:hidden property="sml250MailDfSelected" />
<html:hidden property="sml250SmailOp" />
<html:hidden property="sml250ZaisekiPlugin" />

<logic:equal name="sml250knForm" property="sml250ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
<html:hidden property="sml250HuriwakeKbn" />
<html:hidden property="sml250Zmail1Selected" />
<html:hidden property="sml250Zmail1" />
<html:hidden property="sml250Zmail2Selected" />
<html:hidden property="sml250Zmail2" />
<html:hidden property="sml250Zmail3Selected" />
<html:hidden property="sml250Zmail3" />
</logic:equal>

<logic:notEmpty name="sml250knForm" property="sml250userSid" scope="request">
<logic:iterate id="users" name="sml250knForm" property="sml250userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="sml250userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<bean:define id="acctMode" name="sml250knForm" property="smlAccountMode" type="java.lang.Integer" />
<bean:define id="sCmdMode" name="sml250knForm" property="smlCmdMode" type="java.lang.Integer" />
<% int accountMode = acctMode.intValue(); %>
<% int cmdMode = sCmdMode.intValue(); %>

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
      <tr id="normal_tab" align="left">
        <td id="normal" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="cmn.preferences" /></td>
        <logic:notEqual name="sml250knForm" property="sml250autoDelKbn" value="0">
          <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250knForm" property="smlAccountMode" value="2">
          <logic:equal name="sml250knForm" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      </tr>
      <tr id="auto_del_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(0);" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250knForm" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="cmn.autodelete" /></td>
        </logic:notEqual>
        <logic:equal name="sml250knForm" property="smlAccountMode" value="2">
          <logic:equal name="sml250knForm" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(0);" style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="other_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(0);" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250knForm" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250knForm" property="smlAccountMode" value="2">
          <logic:equal name="sml250knForm" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="cmn.other" /></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="tenso_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(0);" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250knForm" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250knForm" property="smlAccountMode" value="2">
          <logic:equal name="sml250knForm" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="sml.80" /></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" onclick="more(2);"  style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
    </table>

    <table id="normal_table" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="35%" nowrap><span class="text_bb1"><gsmsg:write key="wml.96" /></span></td>
    <td align="left" class="webmail_td1" width="65%">
    <bean:write name="sml250knForm" property="sml250name" />
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="webmail_td1">
      <bean:write name="sml250knForm" property="sml250knBiko" filter="false" />
    </td>
    </tr>

<%--
    <logic:equal name="sml250knForm" property="smlAccountMode" value="2">
--%>
    <logic:equal name="sml250knForm" property="sml250acntUserFlg" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.employer" /></span></td>
    <td align="left" class="webmail_td1">
    <%--
    <logic:equal name="sml250knForm" property="sml250userKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.sml250kn.Sml250knForm.USERKBN_GROUP) %>">
      <p><gsmsg:write key="wml.94" /></p>
      <logic:notEmpty name="sml250knForm" property="userKbnGroupSelectCombo">
        <ul>
          <logic:iterate id="userKbnLabel" name="sml250knForm" property="userKbnGroupSelectCombo">
          <li><bean:write name="userKbnLabel" property="label" /></li>
          </logic:iterate>
        </ul>
      </logic:notEmpty>
    </logic:equal>

      <p><gsmsg:write key="wml.77" /></p>
   --%>
      <logic:notEmpty name="sml250knForm" property="userKbnUserSelectCombo">
        <ul>
          <logic:iterate id="userKbnLabel" name="sml250knForm" property="userKbnUserSelectCombo">
          <li><bean:write name="userKbnLabel" property="label" /></li>
          </logic:iterate>
        </ul>
      </logic:notEmpty>

    </td>
    </tr>
    </logic:equal>

    </table>

    <table id="tenso_table" width="100%" class="tl0 display_none" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml100.03" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <logic:equal name="sml250knForm" property="sml250tensoSetKbn" value="0">
        <span class="text_base"><gsmsg:write key="cmn.noset" /></span>
      </logic:equal>
      <logic:notEqual name="sml250knForm" property="sml250tensoSetKbn" value="0">
        <span class="text_base"><gsmsg:write key="cmn.setting.do" /></span>
      </logic:notEqual>
    </td>
    </tr>
<!-- 対象 -->
    <tr class="sml_tenso_set display_none">
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /></span></td>
    <td align="left" class="td_wt" width="80%">

    <logic:equal name="sml250knForm" property="sml250ObjKbn" value="0">
    <span class="text_r1"><gsmsg:write key="sml.sml180kn.14" /></span>
    <br>
    <span class="text_base"><gsmsg:write key="wml.231" /><gsmsg:write key="cmn.alluser" /></span>
    <br>
    <span class="text_base">（<bean:write name="sml250knForm" property="sml250knUsrCnt" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
    </logic:equal>

    <logic:equal name="sml250knForm" property="sml250ObjKbn" value="1">
    <logic:notEmpty name="sml250knForm" property="sml250knUsrOkLabelList">
    <span class="text_r1"><gsmsg:write key="sml.sml180kn.14" /></span>
    <br>
      <logic:iterate id="okUser" name="sml250knForm" property="sml250knUsrOkLabelList">
        <span class="text_base"><bean:write name="okUser" /></span><br>
      </logic:iterate>
      <span class="text_base">(<bean:write name="sml250knForm" property="sml250knUsrCnt" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
    </logic:notEmpty>
    </logic:equal>

    <logic:notEmpty name="sml250knForm" property="sml250knUsrNgLabelList">
    <br>
    <br>
    <span class="text_r1"><gsmsg:write key="sml.sml180kn.17" /></span>
    <br>
      <logic:iterate id="ngUser" name="sml250knForm" property="sml250knUsrNgLabelList">
      <span class="text_base"><bean:write name="ngUser" /></span><br>
      </logic:iterate>
      <span class="text_base"><gsmsg:write key="sml.sml180kn.06" /><bean:write name="sml250knForm" property="sml250knUsrCntNg" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
    </logic:notEmpty>
    </td>
    </tr>

<!-- メール転送設定 -->
    <tr class="sml_tenso_set display_none">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.80" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <logic:equal name="sml250knForm" property="sml250MailFw" value="0">
      <tr>
      <td align="left" width="100%">
        <span class="text_base"><gsmsg:write key="sml.sml180kn.04" /></span>
      </td>
      </tr>
      </logic:equal>

      <logic:equal name="sml250knForm" property="sml250MailFw" value="1">
      <tr>
      <td align="left" width="100%">
        <span class="text_base"><gsmsg:write key="sml.sml180kn.05" /></span><br>

        <span class="text_base">
        <logic:equal name="sml250knForm" property="sml250SmailOp" value="0"><gsmsg:write key="sml.sml180kn.02" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250SmailOp" value="1"><gsmsg:write key="sml.sml180kn.03" /></logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="sml.81" /><gsmsg:write key="wml.215" /></span>
        <span class="text_base">
        <logic:equal name="sml250knForm" property="sml250MailDfSelected" value="0"><bean:write name="sml250knForm" property="sml250MailDf" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250MailDfSelected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250MailDfSelected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250MailDfSelected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>


      <logic:equal name="sml250knForm" property="sml250ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <logic:equal name="sml250knForm" property="sml250HuriwakeKbn" value="1">
      <tr>
      <td width="100%" align="left" nowrap>
      <hr style="border-color:#cccccc;">
      <span class="text_base3"><gsmsg:write key="sml.sml180kn.15" /></span>
        <span class="text_base">
        <logic:equal name="sml250knForm" property="sml250Zmail1Selected" value="0"><bean:write name="sml250knForm" property="sml250Zmail1" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail1Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail1Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail1Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="sml.sml180kn.16" /></span>
        <span class="text_base">
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="0"><bean:write name="sml250knForm" property="sml250Zmail2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="cmn.other" /><gsmsg:write key="wml.215" /></span>
        <span class="text_base">
        <logic:equal name="sml250knForm" property="sml250Zmail3Selected" value="0"><bean:write name="sml250knForm" property="sml250Zmail3" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail3Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail3Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail3Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>
      </logic:equal>


      <logic:equal name="sml250knForm" property="sml250HuriwakeKbn" value="2">

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="sml.sml180kn.16" /></span>
        <span class="text_base">
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="0"><bean:write name="sml250knForm" property="sml250Zmail2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml250knForm" property="sml250Zmail2Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>

      </logic:equal>


      </logic:equal>
      </logic:equal>
      </table>
    </td>
    </tr>
    </table>



    <table id="auto_del_table" class="tl0 display_none" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.50" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml250knForm" property="sml250JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml250knForm" property="sml250JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="jyear" name="sml250knForm" property="sml250JYear" type="java.lang.String" />
        <bean:define id="jmonth" name="sml250knForm" property="sml250JMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= jyear %>" /> <gsmsg:write key="cmn.months" arg0="<%= jmonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.52" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml250knForm" property="sml250SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml250knForm" property="sml250SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="syear" name="sml250knForm" property="sml250SYear" type="java.lang.String" />
        <bean:define id="smonth" name="sml250knForm" property="sml250SMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= syear %>" /> <gsmsg:write key="cmn.months" arg0="<%= smonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.51" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml250knForm" property="sml250WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml250knForm" property="sml250WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="wyear" name="sml250knForm" property="sml250WYear" type="java.lang.String" />
        <bean:define id="wmonth" name="sml250knForm" property="sml250WMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= wyear %>" /> <gsmsg:write key="cmn.months" arg0="<%= wmonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.49" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml250knForm" property="sml250DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml250knForm" property="sml250DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="dyear" name="sml250knForm" property="sml250DYear" type="java.lang.String" />
        <bean:define id="dmonth" name="sml250knForm" property="sml250DMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= dyear %>" /> <gsmsg:write key="cmn.months" arg0="<%= dmonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>
    </table>

    <table id="other_table" class="tl0 display_none" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr><%-- 自動TO --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.52" /></span></td>
        <td align="left" class="webmail_td1">
            <logic:notEmpty  name="sml250knForm" property="sml250AutoDestToLabelList">
            <logic:iterate id="user" name="sml250knForm" property="sml250AutoDestToLabelList">
                    <input type="hidden" name="sml250AutoDestToUsrSid" value="<bean:write name="user" property="value" />">
                    <bean:write name="user" property="label" /></br>
            </logic:iterate>
            </logic:notEmpty>
        </td>
      </tr>
      <tr><%-- 自動Cc --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.53" /></span></td>
        <td align="left" class="webmail_td1">
            <logic:notEmpty  name="sml250knForm" property="sml250AutoDestCcLabelList">
            <logic:iterate id="user" name="sml250knForm" property="sml250AutoDestCcLabelList">
                    <input type="hidden" name="sml250AutoDestCcUsrSid" value="<bean:write name="user" property="value" />">
                    <bean:write name="user" property="label" /></br>
            </logic:iterate>
            </logic:notEmpty>
        </td>
      </tr>
      <tr><%-- 自動Bcc --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.54" /></span></td>
        <td align="left" class="webmail_td1">
            <logic:notEmpty  name="sml250knForm" property="sml250AutoDestBccLabelList">
            <logic:iterate id="user" name="sml250knForm" property="sml250AutoDestBccLabelList">
                    <input type="hidden" name="sml250AutoDestBccUsrSid" value="<bean:write name="user" property="value" />">
                    <bean:write name="user" property="label" /></br>
            </logic:iterate>
            </logic:notEmpty>
        </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.format." /></span></td>
      <td align="left" class="webmail_td1">
      <logic:equal name="sml250knForm" property="sml250sendType" value="0">
      <gsmsg:write key="cmn.standard" />
      </logic:equal>
      <logic:notEqual name="sml250knForm" property="sml250sendType" value="0">
      <gsmsg:write key="wml.110" />
      </logic:notEqual>
      </td>
      </tr>

      <tr>
      <td width="250" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.theme" /></span></td>
      <td align="left" class="webmail_td1">

            <logic:equal name="sml250knForm" property="sml250theme" value="0">
              <bean:write name="sml250knForm" property="sml250knTheme" />
            </logic:equal>
            <logic:equal name="sml250knForm" property="sml250theme" value="1">
              <span class="theme_class_1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
            </logic:equal>
            <logic:equal name="sml250knForm" property="sml250theme" value="2">
              <span class="theme_class_2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
            </logic:equal>
            <logic:equal name="sml250knForm" property="sml250theme" value="3">
              <span class="theme_class_3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
            </logic:equal>
            <logic:equal name="sml250knForm" property="sml250theme" value="4">
              <span class="theme_class_4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
            </logic:equal>
            <logic:equal name="sml250knForm" property="sml250theme" value="5">
              <span class="theme_class_5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
            </logic:equal>
            <logic:equal name="sml250knForm" property="sml250theme" value="6">
             <span class="theme_class_6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
            </logic:equal>


      </td>
      </tr>
      <tr>
      <td width="250" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.quotes" /></span></td>
      <td align="left" class="webmail_td1">
        <bean:write name="sml250knForm" property="sml250knQuotes" />
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