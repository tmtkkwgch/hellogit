<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="jp.groupsession.v2.sml.sml250.Sml250Form" %>
<%-- 定数値 --%>
<%
  String  acModeNormal    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ACCOUNTMODE_NORMAL);
  String  acModePsn       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ACCOUNTMODE_PSNLSETTING);
  String  acModeCommon    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ACCOUNTMODE_COMMON);
  String  cmdModeAdd      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.CMDMODE_ADD);
  String  cmdModeEdit     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.CMDMODE_EDIT);
%>


<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml250.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="wml.wml040.05" /></title>
</head>

<body class="body_03" onunload="windowClose();">

<html:form styleId="sml250Form" action="/smail/sml250">

<logic:notEqual name="sml250Form" property="smlAccountMode" value="0">
<input type="hidden" name="helpPrm" value="" />
</logic:notEqual>

<logic:equal name="sml250Form" property="smlAccountMode" value="0">
<input type="hidden" name="helpPrm" value="3" />
</logic:equal>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="smlCmdMode" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountMode" />
<html:hidden property="smlAccountSid" />
<html:hidden property="sml010adminUser" />
<html:hidden property="sml250initFlg" />
<html:hidden property="sml250AccountKbn" />
<html:hidden property="sml250DefActUsrSid" />
<html:hidden property="sml250elementKbn" />
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
<html:hidden property="sml250autoDelKbn" />
<html:hidden property="sml250tensoKbn" />
<html:hidden property="sml250SelTab" />

<logic:notEmpty name="sml250Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml250Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml250Form" property="sml250userSid" scope="request">
<logic:iterate id="users" name="sml250Form" property="sml250userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="sml250userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

      <logic:notEqual name="sml250Form" property="sml250ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <html:hidden property="sml250HuriwakeKbn" />
      <html:hidden property="sml250Zmail1Selected" />
      <html:hidden property="sml250Zmail1" />
      <html:hidden property="sml250Zmail2Selected" />
      <html:hidden property="sml250Zmail2" />
      <html:hidden property="sml250Zmail3Selected" />
      <html:hidden property="sml250Zmail3" />
      </logic:notEqual>
<html:hidden property="sml250ZaisekiPlugin" />


<bean:define id="acctMode" name="sml250Form" property="smlAccountMode" type="java.lang.Integer" />
<bean:define id="wCmdMode" name="sml250Form" property="smlCmdMode" type="java.lang.Integer" />
<% int accountMode = acctMode.intValue(); %>
<% int cmdMode = wCmdMode.intValue(); %>

<%--
<logic:notEmpty name="sml250Form" property="sml250userKbnGroup">
<logic:iterate id="accountGroup" name="sml250Form" property="sml250userKbnGroup">
  <input type="hidden" name="sml250userKbnGroup" value="<bean:write name="accountGroup" />">
</logic:iterate>
</logic:notEmpty>
--%>

<logic:notEmpty name="sml250Form" property="sml250userKbnUser">
<logic:iterate id="accountUser" name="sml250Form" property="sml250userKbnUser">
  <input type="hidden" name="sml250userKbnUser" value="<bean:write name="accountUser" />">
</logic:iterate>
</logic:notEmpty>

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
          <logic:equal name="sml250Form" property="smlCmdMode" value="1">
            <logic:equal name="sml250Form" property="sml250AccountKbn" value="1">
              <logic:equal name="sml250Form" property="sml250CanDelFlg" value="0">
                <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteAccount');">
              </logic:equal>
            </logic:equal>
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
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr id="normal_tab" align="left">
        <td id="normal" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="cmn.preferences" /></td>
        <logic:notEqual name="sml250Form" property="sml250autoDelKbn" value="0">
          <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250Form" property="smlAccountMode" value="2">
          <logic:equal name="sml250Form" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      </tr>
      <tr id="send_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><a href="#" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250Form" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250Form" property="smlAccountMode" value="2">
          <logic:equal name="sml250Form" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="auto_del_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250Form" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="cmn.autodelete" /></td>
        </logic:notEqual>
        <logic:equal name="sml250Form" property="smlAccountMode" value="2">
          <logic:equal name="sml250Form" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="other_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250Form" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250Form" property="smlAccountMode" value="2">
          <logic:equal name="sml250Form" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="sml.80" /></a></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="cmn.other" /></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
      <tr id="tenso_tab" class="display_none" align="left">
        <td id="normal" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#" style="color:#ffffff;"><gsmsg:write key="cmn.preferences" /></a></td>
        <logic:notEqual name="sml250Form" property="sml250autoDelKbn" value="0">
        <td id="auto_del" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.autodelete" /></a></td>
        </logic:notEqual>
        <logic:equal name="sml250Form" property="smlAccountMode" value="2">
          <logic:equal name="sml250Form" property="sml250tensoKbn" value="1">
            <td id="tenso" class="sml_account_tab now_forcus wml_tab_td2" align="center" nowrap><gsmsg:write key="sml.80" /></td>
          </logic:equal>
        </logic:equal>
        <td id="other" class="sml_account_tab none_forcus wml_tab_td2" align="center" nowrap><a href="#"  style="color:#ffffff;"><gsmsg:write key="cmn.other" /></a></td>
        <td class="wml_tab_td2" width="100%">&nbsp;</td>
      </tr>
    </table>

    <table id="normal_table" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">


      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="wml.96" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_wt" width="750">
          <logic:equal name="sml250Form" property="sml250AccountKbn" value="1">
            <html:text name="sml250Form" property="sml250name" styleClass="width:60%;" style="width:273px;" maxlength="100" />
          </logic:equal>
          <logic:equal name="sml250Form" property="sml250AccountKbn" value="0">
            <html:hidden property="sml250name" />
            <bean:write name="sml250Form" property="sml250name" />
          </logic:equal>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="webmail_td1">
          <html:textarea name="sml250Form" property="sml250biko" style="width:442px;" rows="10" styleClass="width:60%;" />
        </td>
      </tr>
<%--
      <logic:equal name="sml250Form" property="smlAccountMode" value="2">
--%>
      <logic:equal name="sml250Form" property="sml250acntUserFlg" value="true">
      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.employer" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="webmail_td1">
<%--
          <p>
          <html:radio name="sml250Form" property="sml250userKbn" styleId="usrKbn1" value="0" onclick="tensoCheck(0 ,2, -1);" /><label for="usrKbn1"><gsmsg:write key="wml.94" /></label>
          &nbsp;<html:radio name="sml250Form" property="sml250userKbn" styleId="usrKbn2" value="1" onclick="tensoCheck(1, 2, -1);" /><label for="usrKbn2"><gsmsg:write key="wml.77" /></label>
          </p>

          <table id="permissionGroup">

            <tr>
              <td width="50%">
                <span class="text_r1">
                  <logic:equal name="sml250Form" property="sml250AccountKbn" value="0">
                    <gsmsg:write key="wml.wml040.01" />:<bean:write name="sml250Form" property="sml250name" />
                  </logic:equal>
                </span>
              </td>
              <td width="0%"></td>
              <td width="50%"></td>
            </tr>

            <tr>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.02" /></span></td>
              <td width="0%"></td>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.04" /></span></td>
            </tr>
            <tr>
              <td class="td_type1">

                <html:select name="sml250Form" property="sml250userKbnGroupSelect" size="7" styleClass="smail_select01" multiple="true">
                  <logic:notEmpty name="sml250Form" property="userKbnGroupSelectCombo">
                  <html:optionsCollection name="sml250Form" property="userKbnGroupSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
              <td valign="middle">
                <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="return buttonPush('addGroup');">
                <img src="../common/images/spacer.gif" width="20" height="30">
                <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="deleteSelGroup();">
              </td>
              <td class="td_type1">
                <html:select name="sml250Form" property="sml250userKbnGroupNoSelect" size="7" styleClass="smail_select01" multiple="true">
                  <logic:notEmpty name="sml250Form" property="userKbnGroupNoSelectCombo">
                  <html:optionsCollection name="sml250Form" property="userKbnGroupNoSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
          </table>
          --%>

          <table id="permissionUser">
            <tr>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.01" /></span></td>
              <td width="0%"></td>
              <td class="table_bg_A5B4E1" align="center" width="50%"><span class="text_bb1"><gsmsg:write key="wml.wml040.03" /></span></td>
            </tr>
            <tr>
              <td class="td_type1">

                <html:select name="sml250Form" property="sml250userKbnUserSelect" size="11" styleClass="smail_select01" multiple="true">
                  <logic:notEmpty name="sml250Form" property="userKbnUserSelectCombo">
                  <html:optionsCollection name="sml250Form" property="userKbnUserSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
              <td valign="middle">
                <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="return buttonPush('addUser');">
                <img src="../common/images/spacer.gif" width="20" height="30">
                <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="return deleteSelUser();">
              </td>
              <td class="td_type1">
                <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.sml250userKbnUserGroup, 'sml250userKbnUserGroup', '<bean:write name="sml250Form" property="sml250userKbnUserGroup" />', '0', 'init', 'sml250userKbnUser', '-1', '0')" type="button">
                <html:select name="sml250Form" property="sml250userKbnUserGroup" styleClass="smail_select01" onchange="buttonPush('init');">
                  <html:optionsCollection name="sml250Form" property="userKbnGroupCombo" value="value" label="label" />
                </html:select>
                <input type="button" onclick="openGroupWindow(this.form.sml250userKbnUserGroup, 'sml250userKbnUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="sml250GroupBtn">
                <html:select name="sml250Form" property="sml250userKbnUserNoSelect" size="9" styleClass="smail_select01" multiple="true">
                  <logic:notEmpty name="sml250Form" property="userKbnUserNoSelectCombo">
                  <html:optionsCollection name="sml250Form" property="userKbnUserNoSelectCombo" value="value" label="label" />
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      </logic:equal>

    </table>

    <logic:equal name="sml250Form" property="smlAccountMode" value="2">
    <logic:equal name="sml250Form" property="sml250tensoKbn" value="1">
    <table id="tenso_table" width="100%" class="tl0 display_none" border="0" cellpadding="5">

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml100.03" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
        <html:radio styleId="sml250tensoSetKbn_0" name="sml250Form" property="sml250tensoSetKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NO_SET) %>" onclick="changeTensoArea();" /><label for="sml250tensoSetKbn_0"><gsmsg:write key="cmn.noset" /></label>
        <html:radio styleId="sml250tensoSetKbn_1" name="sml250Form" property="sml250tensoSetKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_SET) %>" onclick="changeTensoArea();" /><label for="sml250tensoSetKbn_1"><gsmsg:write key="cmn.setting.do" /></label>&nbsp;&nbsp;
      </span>
    </td>
    </tr>

    <!-- 対象 -->
    <tr class="sml_tenso_set display_none">
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.target" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1" width="80%">

      <table width="100%" border="0">
      <tr>
      <td width="100%">
        <span class="text_base">
          <html:radio styleId="sml250Obj_0" name="sml250Form" property="sml250ObjKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.sml250.Sml250Biz.TAISYO_ALL) %>" onclick="changeTensoUsrKbn();" /><label for="sml250Obj_0"><gsmsg:write key="cmn.alluser" /></label>&nbsp;&nbsp;
          <html:radio styleId="sml250Obj_1" name="sml250Form" property="sml250ObjKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.sml250.Sml250Biz.TAISYO_SELECT) %>" onclick="changeTensoUsrKbn();" /><label for="sml250Obj_1"><gsmsg:write key="cmn.named.user" /></label>
        </span>
      </td>
      <td width="0%" nowrap>
      <html:checkbox name="sml250Form" property="sml250PassKbn" value="1" styleId="sml180PassKbn" /><span class="text_base2"><label for="sml180PassKbn"><gsmsg:write key="sml.sml180.07" /></label></span>
      </td>
      </tr>
      </table>

    <span class="text_base">
    </span>

    <logic:equal name="sml250Form" property="sml250ObjKbn" value="1">
      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.user" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
<%--
        <html:select property="sml250groupSid" styleClass="select01" onchange="buttonPush('changeGroup');">
          <html:optionsCollection name="sml250Form" property="sml250GpLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
--%>
      </td>
      <td width="10%" align="left" valign="bottom">

      </td>
      </tr>

      <tr>
      <td>
        <html:select property="sml250selectUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="sml250Form" property="sml250MbLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMnb');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteMnb');"><br>
      </td>
      <td align="center">
        <html:select property="sml250addUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="sml250Form" property="sml250AdLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>
    </logic:equal>
    </td>
    </tr>

    <!-- メール転送設定 -->
    <tr class="sml_tenso_set display_none">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.80" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
      <span class="text_base"><gsmsg:write key="sml.05" /><br>
      <html:radio name="sml250Form" property="sml250MailFw" styleId="sml250MailFw0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisableTenso();"/><span class="text_base7"><label for="sml250MailFw0"><gsmsg:write key="sml.78" /></label></span>&nbsp;
      <html:radio name="sml250Form" property="sml250MailFw" styleId="sml250MailFw1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisableTenso();"/><span class="text_base7"><label for="sml250MailFw1"><gsmsg:write key="sml.79" /></label>&nbsp;</span><br>
      </span>
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.81" /></span>&nbsp;</th>
      </tr>
      <tr>
      <td align="left" width="100%">
        <html:select name="sml250Form" property="sml250MailDfSelected" onchange="changeEnableDisableTenso();">
          <html:optionsCollection name="sml250Form" property="sml250MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml250Form" style="width:335px;" maxlength="50" property="sml250MailDf" styleClass="text_base" /><br>
      <span class="text_base"><gsmsg:write key="sml.77" /></span>
      <html:radio name="sml250Form" property="sml250SmailOp" styleId="sml180SmailOp0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED) %>"/>
      <span class="text_base"><label for="sml180SmailOp0"><gsmsg:write key="sml.29" /></label></span>
      <html:radio name="sml250Form" property="sml250SmailOp" styleId="sml180SmailOp1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED) %>"/>
      <span class="text_base"><label for="sml180SmailOp1"><gsmsg:write key="cmn.mark.read" /></label></span>
      </td>
      </tr>

      <logic:equal name="sml250Form" property="sml250ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <tr>
      <td align="left" width="100%">
      <hr style="border-color:#cccccc;">
      <span class="text_base"><gsmsg:write key="sml.sml180.03" /><br><gsmsg:write key="sml.sml180.05" /><br>
      <html:radio name="sml250Form" property="sml250HuriwakeKbn" styleId="sml250HuriwakeKbn0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisableTenso();"/><span class="text_base7"><label for="sml250HuriwakeKbn0"><gsmsg:write key="sml.42" /></label></span>&nbsp;
      <html:radio name="sml250Form" property="sml250HuriwakeKbn" styleId="sml250HuriwakeKbn1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisableTenso();"/><span class="text_base7"><label for="sml250HuriwakeKbn1"><gsmsg:write key="sml.43" /></label></span>&nbsp;
      <html:radio name="sml250Form" property="sml250HuriwakeKbn" styleId="sml250HuriwakeKbn2" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_FUZAI_OK) %>" onclick="changeEnableDisableTenso();"/><span class="text_base7"><label for="sml250HuriwakeKbn2"><gsmsg:write key="sml.168" /></label></span>&nbsp;
      </span>
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.44" /></span>&nbsp;</th>
      </tr>

      <tr>
      <td align="left" width="100%">
        <html:select name="sml250Form" property="sml250Zmail1Selected" onchange="changeEnableDisableTenso();">
          <html:optionsCollection name="sml250Form" property="sml250MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml250Form" style="width:335px;" maxlength="50" property="sml250Zmail1" styleClass="text_base" />
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.89" /></span>&nbsp;</th>
      </tr>

      <tr>
      <td align="left" width="100%">
        <html:select name="sml250Form" property="sml250Zmail2Selected" onchange="changeEnableDisableTenso();">
          <html:optionsCollection name="sml250Form" property="sml250MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml250Form" style="width:335px;" maxlength="50" property="sml250Zmail2" styleClass="text_base" />
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.12" /></span>&nbsp;</th>
      </tr>

      <tr>
      <td align="left" width="100%">
        <html:select name="sml250Form" property="sml250Zmail3Selected" onchange="changeEnableDisableTenso();">
          <html:optionsCollection name="sml250Form" property="sml250MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml250Form" style="width:335px;" maxlength="50" property="sml250Zmail3" styleClass="text_base" />
      </td>
      </tr>
      </logic:equal>
      </table>
    </td>
    </tr>

    </table>
    </logic:equal>
    </logic:equal>


    <logic:notEqual name="sml250Form" property="sml250autoDelKbn" value="0">
    <table id="auto_del_table"  class="display_none" width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.50" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml250Form" property="sml250JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml250JdelKbn_0" onclick="setDispState(this.form.sml250JdelKbn, this.form.sml250JYear, this.form.sml250JMonth)" /><label for="sml250JdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml250Form" property="sml250JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml250JdelKbn_1" onclick="setDispState(this.form.sml250JdelKbn, this.form.sml250JYear, this.form.sml250JMonth)" /><label for="sml250JdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml250Form" property="sml250YearLabelList">
          <html:select property="sml250JYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml250Form" property="sml250MonthLabelList">
          <html:select property="sml250JMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250MonthLabelList" value="value" label="label" />
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

        <html:radio name="sml250Form" property="sml250SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml250SdelKbn_0" onclick="setDispState(this.form.sml250SdelKbn, this.form.sml250SYear, this.form.sml250SMonth)" /><label for="sml250SdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml250Form" property="sml250SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml250SdelKbn_1" onclick="setDispState(this.form.sml250SdelKbn, this.form.sml250SYear, this.form.sml250SMonth)" /><label for="sml250SdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml250Form" property="sml250YearLabelList">
          <html:select property="sml250SYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml250Form" property="sml250MonthLabelList">
          <html:select property="sml250SMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250MonthLabelList" value="value" label="label" />
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

        <html:radio name="sml250Form" property="sml250WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml250WdelKbn_0" onclick="setDispState(this.form.sml250WdelKbn, this.form.sml250WYear, this.form.sml250WMonth)" /><label for="sml250WdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml250Form" property="sml250WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml250WdelKbn_1" onclick="setDispState(this.form.sml250WdelKbn, this.form.sml250WYear, this.form.sml250WMonth)" /><label for="sml250WdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml250Form" property="sml250YearLabelList">
          <html:select property="sml250WYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml250Form" property="sml250MonthLabelList">
          <html:select property="sml250WMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250MonthLabelList" value="value" label="label" />
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

        <html:radio name="sml250Form" property="sml250DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml250DdelKbn_0" onclick="setDispState(this.form.sml250DdelKbn, this.form.sml250DYear, this.form.sml250DMonth)" /><label for="sml250DdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml250Form" property="sml250DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml250DdelKbn_1" onclick="setDispState(this.form.sml250DdelKbn, this.form.sml250DYear, this.form.sml250DMonth)" /><label for="sml250DdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml250Form" property="sml250YearLabelList">
          <html:select property="sml250DYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml250Form" property="sml250MonthLabelList">
          <html:select property="sml250DMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml250Form" property="sml250MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    </table>
    </logic:notEqual>



    <table id="other_table"  class="display_none" width="100%" class="tl0" border="0" cellpadding="5">
      <tr><%-- 自動TO --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.52" /></span></td>
        <td align="left" class="webmail_td1">
          <table class="clear_table">
              <tr>
                <td style="width:35px">
                   <div class="mail_menu_btn mail_send_sel_btn" id="atesakiSelBtn"  style="margin-right: 5px">
                     <input type="hidden" id="atesakiSelBtnVal" value="<gsmsg:write key="wml.52" />" />
                     <input type="hidden" id="atesakiSelBtnKbn" value="0" />
                     <div class="head_menu_btn_left"></div>
                     <div class="head_menu_btn_bg"><div class="address_add_btn"></div></div>
                     <div class="head_menu_btn_right"></div>
                   </div>
                </td>
                <td >
                  <bean:size name="sml250Form" property="sml250AutoDestToLabelList" id="toSize" />
                  <logic:greaterThan name="toSize" value="3">
                  <div id="atesaki_area" style="width: 370px" class="atesaki_scroll_area atesaki_scroll_on atesaki_scroll_area_height" >
                  </logic:greaterThan>
                  <logic:lessEqual name="toSize" value="3" >
                  <div id="atesaki_area" style="width: 370px" class="atesaki_scroll_area">
                  </logic:lessEqual>
                    <div id="atesaki_to_area" class="atesaki_add_area">
                    <logic:notEmpty  name="sml250Form" property="sml250AutoDestToLabelList">
                    <logic:iterate id="user" name="sml250Form" property="sml250AutoDestToLabelList">
                        <div class="atesaki_to_user" id="0">
                            <input type="hidden" name="sml250AutoDestToUsrSid" value="<bean:write name="user" property="value" />">
                            <bean:write name="user" property="label" />&nbsp;&nbsp;[<span class="add_usr_del">削除</span>]
                        </div>
                    </logic:iterate>
                    </logic:notEmpty>
                    </div>
                  </div>
                  <div id="alldsp_to_area" class="atesaki_alldsp_area">
                      <logic:greaterThan name="toSize" value="3">
                        <span id="all_dsp_to_link" class="all_disp_txt"><gsmsg:write key="cmn.all" /><gsmsg:write key="cmn.show" /></span>
                      </logic:greaterThan>
                      <logic:lessEqual name="toSize" value="3">
                        <span id="all_dsp_to_link" class="all_disp_txt"></span>
                      </logic:lessEqual>
                  </div>
                </td>
              </tr>
            </table>
        </td>
      </tr>
      <tr><%-- 自動Cc --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.53" /></span></td>
        <td align="left" class="webmail_td1">
          <table class="clear_table">
              <tr>
                <td style="width:35px">
                  <div class="mail_menu_btn mail_send_sel_btn" id="ccSelBtn"  style="margin-right: 5px">
                    <input type="hidden" id="ccSelBtnVal" value="<gsmsg:write key="wml.53" />" />
                    <input type="hidden" id="ccSelBtnKbn" value="1" />
                    <div class="head_menu_btn_left"></div>
                    <div class="head_menu_btn_bg"><div class="address_add_btn"></div></div>
                    <div class="head_menu_btn_right"></div>
                  </div>
                </td>
                <td>
                  <bean:size name="sml250Form" property="sml250AutoDestCcLabelList" id="ccSize" />
                  <logic:greaterThan name="ccSize" value="3">
                  <div id="cc_area" style="width: 370px" class="atesaki_scroll_area atesaki_scroll_on atesaki_scroll_area_height" >
                  </logic:greaterThan>
                  <logic:lessEqual name="ccSize" value="3" >
                  <div id="cc_area" style="width: 370px" class="atesaki_scroll_area" >
                  </logic:lessEqual>
                       <div id="atesaki_cc_area" class="atesaki_add_area">
                        <logic:notEmpty  name="sml250Form" property="sml250AutoDestCcLabelList">
                        <logic:iterate id="user" name="sml250Form" property="sml250AutoDestCcLabelList">
                            <div class="atesaki_cc_user" id="1">
                                <input type="hidden" name="sml250AutoDestCcUsrSid" value="<bean:write name="user" property="value" />">
                                <bean:write name="user" property="label" />&nbsp;&nbsp;[<span class="add_usr_del">削除</span>]
                            </div>
                        </logic:iterate>
                        </logic:notEmpty>
                       </div>
                   </div>
                   <div id="alldsp_cc_area" class="atesaki_alldsp_area">
                  <logic:greaterThan name="ccSize" value="3">
                        <span id="all_dsp_cc_link" class="all_disp_txt"><gsmsg:write key="cmn.all" /><gsmsg:write key="cmn.show" /></span>
                  </logic:greaterThan>
                  <logic:lessEqual name="ccSize" value="3">
                        <span id="all_dsp_cc_link" class="all_disp_txt"></span>
                  </logic:lessEqual>
                   </div>
                </td>
              </tr>
          </table>
        </td>
      </tr>
      <tr><%-- 自動Bcc --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.54" /></span></td>
        <td align="left" class="webmail_td1">
            <table class="clear_table">
              <tr>
                <td style="width:35px">
                  <div class="mail_menu_btn mail_send_sel_btn" id="bccSelBtn" style="margin-right: 5px">
                    <input type="hidden" id="bccSelBtnVal" value="<gsmsg:write key="wml.54" />" />
                    <input type="hidden" id="bccSelBtnKbn" value="2" />
                    <div class="head_menu_btn_left"></div>
                    <div class="head_menu_btn_bg"><div class="address_add_btn"></div></div>
                    <div class="head_menu_btn_right"></div>
                  </div>
                </td>
                <td>
                  <bean:size name="sml250Form" property="sml250AutoDestBccLabelList" id="bccSize" />
                  <logic:greaterThan name="bccSize" value="3">
                  <div id="bcc_area" style="width: 370px" class="atesaki_scroll_area atesaki_scroll_on atesaki_scroll_area_height" >
                  </logic:greaterThan>
                  <logic:lessEqual name="bccSize" value="3">
                  <div id="bcc_area" style="width: 370px" class="atesaki_scroll_area" >
                  </logic:lessEqual>
                       <div id="atesaki_bcc_area" class="atesaki_add_area">
                        <logic:notEmpty  name="sml250Form" property="sml250AutoDestBccLabelList">
                        <logic:iterate id="user" name="sml250Form" property="sml250AutoDestBccLabelList">
                            <div class="atesaki_bcc_user" id="2">
                                <input type="hidden" name="sml250AutoDestBccUsrSid" value="<bean:write name="user" property="value" />">
                                <bean:write name="user" property="label" />&nbsp;&nbsp;[<span class="add_usr_del">削除</span>]
                            </div>
                        </logic:iterate>
                        </logic:notEmpty>
                       </div>
                   </div>
                   <div id="alldsp_bcc_area" class="atesaki_alldsp_area">
                  <logic:greaterThan name="bccSize" value="3">
                        <span id="all_dsp_bcc_link" class="all_disp_txt"><gsmsg:write key="cmn.all" /><gsmsg:write key="cmn.show" /></span>
                  </logic:greaterThan>
                  <logic:lessEqual name="bccSize" value="3">
                        <span id="all_dsp_bcc_link" class="all_disp_txt"></span>
                  </logic:lessEqual>
                   </div>
                </td>
              </tr>
            </table>
        </td>
      </tr>
      <tr><%-- 送信メール形式 --%>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.format." /></span></td>
        <td align="left" class="webmail_td1">
          <html:radio name="sml250Form" property="sml250sendType" styleId="sendType1" value="0" /><label for="sendType1"><gsmsg:write key="cmn.standard" /></label>
          &nbsp;<html:radio name="sml250Form" property="sml250sendType" styleId="sendType2" value="1" /><label for="sendType2"><gsmsg:write key="wml.110" /></label>
        </td>
      </tr>
      <tr><%-- テーマ --%>
        <td width="250"  class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.theme" /></span></td>
        <td  align="left" class="webmail_td1">
        <% String themeClass = "theme_class_0"; %>

        <logic:notEmpty name="sml250Form" property="sml250themeList">
          <logic:iterate id="themeMdl" name="sml250Form" property="sml250themeList" indexId="themeIdx" >

            <logic:equal name="themeMdl" property="value" value="1">
              <% themeClass = "theme_class_1"; %>
            </logic:equal>
            <logic:equal name="themeMdl" property="value" value="2">
              <% themeClass = "theme_class_2"; %>
            </logic:equal>
            <logic:equal name="themeMdl" property="value" value="3">
              <% themeClass = "theme_class_3"; %>
            </logic:equal>
            <logic:equal name="themeMdl" property="value" value="4">
              <% themeClass = "theme_class_4"; %>
            </logic:equal>
            <logic:equal name="themeMdl" property="value" value="5">
              <% themeClass = "theme_class_5"; %>
            </logic:equal>
            <logic:equal name="themeMdl" property="value" value="6">
              <% themeClass = "theme_class_6"; %>
            </logic:equal>

            <bean:define id="themeVal" name="themeMdl" property="value" type="java.lang.String"/>
            <html:radio name="sml250Form" property="sml250theme" styleId="<%= String.valueOf(themeIdx) %>" value="<%= themeVal %>" />
            <label for="<%= String.valueOf(themeIdx) %>">

              <logic:equal name="themeMdl" property="value" value="0">
                <span style="cursor:pointer;"><bean:write name="themeMdl" property="label" /></span>&nbsp;&nbsp;
              </logic:equal>

              <logic:notEqual name="themeMdl" property="value" value="0">
                <span class="<%= themeClass %>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;
              </logic:notEqual>

            </label>

          </logic:iterate>
        </logic:notEmpty>

        </td>
      </tr>
      <tr>
        <td width="250"  class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.quotes" /></span></td>
        <td  align="left" class="webmail_td1">
          <html:select name="sml250Form" property="sml250quotes">
            <logic:notEmpty name="sml250Form" property="sml250quotesList">
              <html:optionsCollection name="sml250Form" property="sml250quotesList" value="value" label="label" />
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
          <logic:equal name="sml250Form" property="smlCmdMode" value="1">
            <logic:equal name="sml250Form" property="sml250AccountKbn" value="1">
              <logic:equal name="sml250Form" property="sml250CanDelFlg" value="0">
                <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('deleteAccount');">
              </logic:equal>
            </logic:equal>
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

<div id="messagePop" title="" style="display:none">
  <p>
    <div style="float:left;"><span class="ui-icon ui-icon-info"></span></div><div style="float:left;padding-left:15px;padding-top:10px;"><b id="messageArea"></b></div>
  </p>
</div>

<div id="clearTensoPop" title="" style="display:none">
  <p>
    <div style="float:left;"><span class="ui-icon ui-icon-info"></span></div><div style="float:left;padding-left:15px;padding-top:10px;"><b><gsmsg:write key="sml.169" /></b></div>
  </p>
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

<div id="atesakiSelPop" title="ユーザ選択" style="display:none;">

  <table width="100%" height="100%">
    <tr>
      <td id="atesakiSelArea" width="100%"></td>
    </tr>
  </table>

</div>



</body>
</html:html>