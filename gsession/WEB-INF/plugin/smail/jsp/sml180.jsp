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
<script language="JavaScript" src="../smail/js/sml180.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.20" /></title>
</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/smail/sml180">
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

<logic:notEmpty name="sml180Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml180Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml180Form" property="sml180userSid" scope="request">
<logic:iterate id="users" name="sml180Form" property="sml180userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="sml180userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

      <logic:notEqual name="sml180Form" property="sml180ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <html:hidden property="sml180HuriwakeKbn" />
      <html:hidden property="sml180Zmail1Selected" />
      <html:hidden property="sml180Zmail1" />
      <html:hidden property="sml180Zmail2Selected" />
      <html:hidden property="sml180Zmail2" />
      <html:hidden property="sml180Zmail3Selected" />
      <html:hidden property="sml180Zmail3" />
      </logic:notEqual>
<html:hidden property="sml180ZaisekiPlugin" />

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.sml180.01" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ok');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backToList');">
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


<!-- 対象 -->
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.target" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1" width="80%">

      <table width="100%" border="0">
      <tr>
      <td width="100%">
        <span class="text_base">
        <html:radio styleId="sml180Obj_0" name="sml180Form" property="sml180ObjKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.sml180.Sml180Biz.TAISYO_ALL) %>" onclick="buttonPush('redraw');" /><label for="sml180Obj_0"><gsmsg:write key="cmn.alluser" /></label>&nbsp;&nbsp;
        <html:radio styleId="sml180Obj_1" name="sml180Form" property="sml180ObjKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.sml180.Sml180Biz.TAISYO_SELECT) %>" onclick="buttonPush('redraw');" /><label for="sml180Obj_1"><gsmsg:write key="cmn.named.user" /></label>
        </span>
      </td>
      <td width="0%" nowrap>
      <html:checkbox name="sml180Form" property="sml180PassKbn" value="1" styleId="sml180PassKbn" /><span class="text_base2"><label for="sml180PassKbn"><gsmsg:write key="sml.sml180.07" /></label></span>
      </td>
      </tr>
      </table>

    <span class="text_base">
    </span>

    <logic:equal name="sml180Form" property="sml180ObjKbn" value="1">
      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.user" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <input class="btn_group_n1" onclick="return openAllGroup(this.form.sml180groupSid, 'sml180groupSid', '-1', '0', 'changeGroup', 'sml180userSid', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
        <html:select property="sml180groupSid" styleClass="select01" onchange="buttonPush('changeGroup');">
          <html:optionsCollection name="sml180Form" property="sml180GpLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.sml180groupSid, 'sml180groupSid', '0', 'changeGroup')" class="group_btn2" value="&nbsp;&nbsp;" id="sml180GroupBtn">
      </td>
      </tr>

      <tr>
      <td>
        <html:select property="sml180selectUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="sml180Form" property="sml180MbLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMnb');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteMnb');"><br>
      </td>
      <td align="center">
        <html:select property="sml180addUserSid" size="6" multiple="true" styleClass="select01">
          <html:optionsCollection name="sml180Form" property="sml180AdLabelList" value="value" label="label" />
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>
    </logic:equal>
    </td>
    </tr>

<!-- メール転送設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.80" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
      <span class="text_base"><gsmsg:write key="sml.05" /><br>
      <html:radio name="sml180Form" property="sml180MailFw" styleId="sml180MailFw0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml180MailFw0"><gsmsg:write key="sml.78" /></label></span>&nbsp;
      <html:radio name="sml180Form" property="sml180MailFw" styleId="sml180MailFw1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml180MailFw1"><gsmsg:write key="sml.79" /></label>&nbsp;</span><br>
      </span>
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.81" /></span>&nbsp;</th>
      </tr>
      <tr>
      <td align="left" width="100%">
        <html:select name="sml180Form" property="sml180MailDfSelected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml180Form" property="sml180MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml180Form" size="50" maxlength="50" property="sml180MailDf" styleClass="text_base" /><br>
      <span class="text_base"><gsmsg:write key="sml.77" /></span>
      <html:radio name="sml180Form" property="sml180SmailOp" styleId="sml180SmailOp0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED) %>"/>
      <span class="text_base"><label for="sml180SmailOp0"><gsmsg:write key="sml.29" /></label></span>
      <html:radio name="sml180Form" property="sml180SmailOp" styleId="sml180SmailOp1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED) %>"/>
      <span class="text_base"><label for="sml180SmailOp1"><gsmsg:write key="cmn.mark.read" /></label></span>
      </td>
      </tr>

      <logic:equal name="sml180Form" property="sml180ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <tr>
      <td align="left" width="100%">
      <hr style="border-color:#cccccc;">
      <span class="text_base"><gsmsg:write key="sml.sml180.03" /><br><gsmsg:write key="sml.sml180.05" /><br>
      <html:radio name="sml180Form" property="sml180HuriwakeKbn" styleId="sml180HuriwakeKbn0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml180HuriwakeKbn0"><gsmsg:write key="sml.42" /></label></span>&nbsp;
      <html:radio name="sml180Form" property="sml180HuriwakeKbn" styleId="sml180HuriwakeKbn1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml180HuriwakeKbn1"><gsmsg:write key="sml.43" /></label></span>&nbsp;
      <html:radio name="sml180Form" property="sml180HuriwakeKbn" styleId="sml180HuriwakeKbn2" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_FUZAI_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml180HuriwakeKbn2"><gsmsg:write key="sml.168" /></label></span>&nbsp;
      </span>
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.44" /></span>&nbsp;</th>
      </tr>

      <tr>
      <td align="left" width="100%">
        <html:select name="sml180Form" property="sml180Zmail1Selected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml180Form" property="sml180MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml180Form" size="50" maxlength="50" property="sml180Zmail1" styleClass="text_base" />
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.89" /></span>&nbsp;</th>
      </tr>

      <tr>
      <td align="left" width="100%">
        <html:select name="sml180Form" property="sml180Zmail2Selected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml180Form" property="sml180MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml180Form" size="50" maxlength="50" property="sml180Zmail2" styleClass="text_base" />
      </td>
      </tr>

      <tr>
      <th width="100%" align="left" nowrap><span class="text_base"><gsmsg:write key="sml.12" /></span>&nbsp;</th>
      </tr>

      <tr>
      <td align="left" width="100%">
        <html:select name="sml180Form" property="sml180Zmail3Selected" onchange="changeEnableDisable();">
          <html:optionsCollection name="sml180Form" property="sml180MailList" value="value" label="label" />
        </html:select>
      <html:text name="sml180Form" size="50" maxlength="50" property="sml180Zmail3" styleClass="text_base" />
      </td>
      </tr>
      </logic:equal>
      </table>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ok');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backToList');">
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
</body>
</html:html>