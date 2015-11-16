<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03"  onload="initEnableDisable();lmtEnableDisable();">
<html:form action="/smail/sml110">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.shortmail" /> <gsmsg:write key="sml.80" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('sml110kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('sml110back');"></td>
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

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="sml.80" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="sml.sml110.11" /></span><br><br>
      <html:radio name="sml110Form" property="sml110MailForward" styleId="sml110MailForward0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NG) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml110MailForward0"><gsmsg:write key="sml.78" /></label></span>&nbsp;
      <html:radio name="sml110Form" property="sml110MailForward" styleId="sml110MailForward1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>" onclick="changeEnableDisable();"/><span class="text_base7"><label for="sml110MailForward1"><gsmsg:write key="sml.79" /></label>&nbsp;</span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml110.06" /></span></td>
    <td class="td_type1" width="85%">
      <span class="text_base"><gsmsg:write key="sml.sml110.18" /></span><br><br>
      <table class="tl0" width="99%" cellpadding="5">
        <tr>
          <th width="0%" align="right" nowrap><span class="text_base"><gsmsg:write key="sml.sml110.07" /></span>&nbsp;</th>
          <td width="100%"><html:text name="sml110Form" style="width:335px;" maxlength="200" property="sml110SmtpUrl" styleClass="text_base" /></td>
        </tr>
        <tr>
          <th width="0%" align="right" nowrap><span class="text_base"><gsmsg:write key="sml.sml110.08" /></span>&nbsp;</th>
          <td width="100%"><html:text name="sml110Form" style="width:71px;" maxlength="5" property="sml110SmtpPort" styleClass="text_base" />&nbsp;&nbsp;<span class="text_base6"><gsmsg:write key="sml.sml110.05" /></span></td>
        </tr>
        <tr>
          <th width="0%" align="right" nowrap><span class="text_base"></th>
          <td width="100%"><span class="text_base"><html:checkbox name="sml110Form" property="sml110SslFlg" styleId="smlSsl" value="1" /><label for="smlSsl"><gsmsg:write key="wml.wml040.06" /></label></td>
        </tr>
        <tr>
          <th width="0%" align="right" nowrap><span class="text_base"><gsmsg:write key="sml.sml110.17" /></span>&nbsp;</th>
          <td width="100%"><html:text name="sml110Form" style="width:515px;" maxlength="256" property="sml110FromAddress" styleClass="text_base" /></td>
        </tr>
        <tr><td width="100%" colspan="2"></td></tr>
        <tr>
          <th width="0%" align="right" nowrap></th>
          <td class="td_type3" nowrap>
            <table class="tl0" width="100%" cellpadding="5">
              <tr><td width="100%" colspan="2"><span class="text_base"><gsmsg:write key="sml.sml110.12" /></span><br><span class="text_base6"><gsmsg:write key="sml.sml110.02" /></span></td></tr>
              <tr><th width="0%" align="right" nowrap><span class="text_base"><gsmsg:write key="sml.sml110.22" /></span>&nbsp;</th><td width="100%"><html:text name="sml110Form" style="width:275px;" maxlength="100" property="sml110SmtpUser" styleClass="text_base" /></td></tr>
              <tr><th width="0%" align="right" nowrap><span class="text_base"><gsmsg:write key="sml.sml110.21" /></span>&nbsp;</th><td width="100%"><html:password name="sml110Form" style="width:275px;" maxlength="100" property="sml110SmtpPass" styleClass="text_base" /></td></tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml110.20" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="sml.sml110.10" /></span><br>
      <span class="sc_ttl_sun"><gsmsg:write key="sml.sml110.04" /><br><gsmsg:write key="sml.sml110.01" /></span>
      <br><br>
      <html:radio name="sml110Form" property="sml110FwLmtKbn" styleId="sml110FwLmtKbn1" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_LIMIT) %>" onclick="lmtEnableDisable();"/><span class="text_base7"><label for="sml110FwLmtKbn1"><gsmsg:write key="cmn.do.limit" /></label></span>&nbsp;
      <html:radio name="sml110Form" property="sml110FwLmtKbn" styleId="sml110FwLmtKbn0" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_NO_LIMIT) %>" onclick="lmtEnableDisable();"/><span class="text_base7"><label for="sml110FwLmtKbn0"><gsmsg:write key="cmn.not.limit" /></label>&nbsp;</span>

<!--       <div id="lmtinput" class="sml_description_text_notdsp"> -->
      <div id="lmtinput">
      <table class="tl0" width="100%">
      <tr>
      <td width="50%"><span class="text_base"><gsmsg:write key="sml.sml110.19" /></span></td>
      <td width="50%"><span class="text_base6"><gsmsg:write key="sml.sml110.03" /></span></td>
      </tr>
      <tr>
      <td align="left" colspan="2">
<html:textarea name="sml110Form" property="sml110FwlmtTextArea" style="width:671px;" rows="6"></html:textarea>
      </td>
      </tr>
      <tr>
      <td align="left" colspan="2">
        <img src="../common/images/spacer.gif" width="1px" height="5px" border="0">

        <table class="tl0" width="100%">
        <tr>
        <td width="100%"  align="center"><input type="button" name="check" onclick="buttonPush('sml110lmtCheck');" value="<gsmsg:write key="sml.sml110.23" />" class="btn_base0"></td>
        </tr>

        <logic:notEmpty name="sml110Form" property="sml110FwCheckList">
        <tr>
        <td width="100%" align="left"><span class="text_base2"><gsmsg:write key="sml.sml110.14" /></span></td>
        </tr>
        </logic:notEmpty>

        <logic:equal name="sml110Form" property="sml110CheckFlg" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.FW_CHECK_ON) %>">
        <tr>
        <td width="100%" align="left"><span class="text_base6"><gsmsg:write key="sml.sml110.24" /></span></td>
        </tr>
        </logic:equal>

        <tr>
        <td width="100%">
          <logic:notEmpty name="sml110Form" property="sml110FwCheckList">
          <table class="tl0" width="100%">
          <tr>
          <td class="table_bg_A5B4E1" width="30%" align="center"><span class="text_bb1"><gsmsg:write key="cmn.user.name" /></span></td>
          <td class="table_bg_A5B4E1" width="70%" align="center"><span class="text_bb1"><gsmsg:write key="sml.sml110.25" /></span></td>
          </tr>

          <bean:define id="tdColor" value="" />
          <% String[] tdColors = new String[] {"td_type1", "td_type8"}; %>

          <logic:iterate id="lmtCheckList" name="sml110Form" property="sml110FwCheckList" indexId="idx">
          <% tdColor = tdColors[(idx.intValue() % 2)]; %>
          <tr>
          <td class="<%= tdColor %>" width="30%"><bean:write name="lmtCheckList" property="usrNameSei" />&nbsp;<bean:write name="lmtCheckList" property="usrNameMei" /></td>
          <td class="<%= tdColor %>" width="70%">
          <logic:equal name="lmtCheckList" property="fwKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.FW_CHECK_MODE_DF) %>">
            <bean:write name="lmtCheckList" property="fwAddDf" />
          </logic:equal>
          <logic:equal name="lmtCheckList" property="fwKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.FW_CHECK_MODE_ZAISEKI) %>">
            <bean:write name="lmtCheckList" property="fwAdd1" />
          </logic:equal>
          <logic:equal name="lmtCheckList" property="fwKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.FW_CHECK_MODE_HUZAI) %>">
            <bean:write name="lmtCheckList" property="fwAdd2" />
          </logic:equal>
          <logic:equal name="lmtCheckList" property="fwKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.FW_CHECK_MODE_OTHER) %>">
            <bean:write name="lmtCheckList" property="fwAdd3" />
          </logic:equal>

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
      </table>

      </div>

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('sml110kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('sml110back');">
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