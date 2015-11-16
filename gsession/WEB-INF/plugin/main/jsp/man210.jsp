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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man210.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.mobile.use.massconfig" /></title>
</head>

<body class="body_03" onload="initEnableDisable();">

<!--　BODY -->
<html:form action="/main/man210">
<input type="hidden" name="CMD" value="">

<logic:notEmpty name="man210Form" property="man210userSid" scope="request">
<logic:iterate id="users" name="man210Form" property="man210userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="man210userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.mobile.use.massconfig" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.target" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base">
      <html:radio styleId="man210Obj_0" name="man210Form" property="man210ObjKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_OK) %>" onclick="buttonPush('redraw');" /><label for="man210Obj_0"><gsmsg:write key="cmn.alluser" /></label>&nbsp;&nbsp;
      <html:radio styleId="man210Obj_1" name="man210Form" property="man210ObjKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_NG) %>" onclick="buttonPush('redraw');" /><label for="man210Obj_1"><gsmsg:write key="cmn.named.user" /></label>
      </span>

      <logic:equal name="man210Form" property="man210ObjKbn" value="1">
        <table width="0%" border="0">
        <tr>
        <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="35%" align="left">
        <input class="btn_group_n1" onclick="return openAllGroup(this.form.man210groupSid, 'man210groupSid', '-1', '0', 'changeGroup', 'man210userSid', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
          <html:select property="man210groupSid" styleClass="select01" onchange="buttonPush('changeGroup');">
            <html:optionsCollection name="man210Form" property="man210GpLabelList" value="value" label="label" />
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </html:select>
        </td>
        <td width="10%" align="left" valign="bottom">
            <input type="button" onclick="openGroupWindow(this.form.man210groupSid, 'man210groupSid', '0', 'changeGroup')" class="group_btn2" value="&nbsp;&nbsp;" id="man210GroupBtn">
        </td>
        </tr>

        <tr>
        <td>
          <html:select property="man210selectUserSid" size="6" multiple="true" styleClass="select01">
            <html:optionsCollection name="man210Form" property="man210MbLabelList" value="value" label="label" />
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </html:select>
        </td>
        <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMnb');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteMnb');">
          <br>
        </td>
        <td align="center">
          <html:select property="man210addUserSid" size="6" multiple="true" styleClass="select01">
            <html:optionsCollection name="man210Form" property="man210AdLabelList" value="value" label="label" />
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
          </html:select>
        </td>
        </tr>
        </table>
      </logic:equal>

      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.mobile.use" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base"><html:radio styleId="man210Use_0" name="man210Form" property="man210UseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_OK) %>" onclick="changeUseKbn();" /><label for="man210Use_0"><gsmsg:write key="cmn.accepted" /></label>&nbsp;&nbsp;<html:radio styleId="man210Use_1" name="man210Form" property="man210UseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_NG) %>" onclick="changeUseKbn();" /><label for="man210Use_1"><gsmsg:write key="cmn.not" /></label></span>
      <br><span class="sc_ttl_sun"><gsmsg:write key="main.man210.6" /></span>
      <br><span class="text_base"><html:checkbox name="man210Form" property="man210NumCont" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_CONTROL) %>" styleId="num_seigyo" onclick="changeNumCont();" /><label for="num_seigyo"><gsmsg:write key="cmn.login.control.identification.number" /></label></span>
      <br><span class="text_base" style="padding-left: 15px;"><html:checkbox name="man210Form" property="man210NumAutAdd" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_AUTO_REG_OK) %>" styleId="autoreg" onclick="" /><label for="autoreg"><gsmsg:write key="main.man210.3" /></label></span>
      <br>&nbsp;&nbsp;&nbsp;&nbsp;<span class="sc_ttl_sun"><gsmsg:write key="main.man210.4" /></span>
      <br>&nbsp;&nbsp;&nbsp;&nbsp;<span class="sc_ttl_sun"><gsmsg:write key="main.man210.5" /></span>
      </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">


    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
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