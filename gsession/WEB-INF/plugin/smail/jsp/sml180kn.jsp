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
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.sml180kn.12" /></title>
</head>

<body class="body_03">
<html:form action="/smail/sml180kn">
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

<logic:notEmpty name="sml180knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml180knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml180ObjKbn" />
<html:hidden property="sml180PassKbn" />
<html:hidden property="sml180groupSid" />
<html:hidden property="sml180MailFw" />
<html:hidden property="sml180MailDf" />
<html:hidden property="sml180MailDfSelected" />
<html:hidden property="sml180SmailOp" />
<html:hidden property="sml180ZaisekiPlugin" />

<logic:equal name="sml180knForm" property="sml180ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
<html:hidden property="sml180HuriwakeKbn" />
<html:hidden property="sml180Zmail1Selected" />
<html:hidden property="sml180Zmail1" />
<html:hidden property="sml180Zmail2Selected" />
<html:hidden property="sml180Zmail2" />
<html:hidden property="sml180Zmail3Selected" />
<html:hidden property="sml180Zmail3" />
</logic:equal>

<logic:notEmpty name="sml180knForm" property="sml180userSid" scope="request">
<logic:iterate id="users" name="sml180knForm" property="sml180userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="sml180userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.sml180kn.12" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('setting');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back');">
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
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /></span></td>
    <td align="left" class="td_wt" width="80%">

    <logic:equal name="sml180knForm" property="sml180ObjKbn" value="0">
    <span class="text_r1"><gsmsg:write key="sml.sml180kn.14" /></span>
    <br>
    <span class="text_base"><gsmsg:write key="wml.231" /><gsmsg:write key="cmn.alluser" /></span>
    <br>
    <span class="text_base">（<bean:write name="sml180knForm" property="sml180knUsrCnt" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
    </logic:equal>

    <logic:equal name="sml180knForm" property="sml180ObjKbn" value="1">
    <logic:notEmpty name="sml180knForm" property="sml180knUsrOkLabelList">
    <span class="text_r1"><gsmsg:write key="sml.sml180kn.14" /></span>
    <br>
      <logic:iterate id="okUser" name="sml180knForm" property="sml180knUsrOkLabelList">
        <span class="text_base"><bean:write name="okUser" /></span><br>
      </logic:iterate>
      <span class="text_base">(<bean:write name="sml180knForm" property="sml180knUsrCnt" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
    </logic:notEmpty>
    </logic:equal>

    <logic:notEmpty name="sml180knForm" property="sml180knUsrNgLabelList">
    <br>
    <br>
    <span class="text_r1"><gsmsg:write key="sml.sml180kn.17" /></span>
    <br>
      <logic:iterate id="ngUser" name="sml180knForm" property="sml180knUsrNgLabelList">
      <span class="text_base"><bean:write name="ngUser" /></span><br>
      </logic:iterate>
      <span class="text_base"><gsmsg:write key="sml.sml180kn.06" /><bean:write name="sml180knForm" property="sml180knUsrCntNg" />&nbsp;<gsmsg:write key="cmn.user" />)</span>
    </logic:notEmpty>
    </td>
    </tr>

<!-- メール転送設定 -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.80" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <logic:equal name="sml180knForm" property="sml180MailFw" value="0">
      <tr>
      <td align="left" width="100%">
        <span class="text_base"><gsmsg:write key="sml.sml180kn.04" /></span>
      </td>
      </tr>
      </logic:equal>

      <logic:equal name="sml180knForm" property="sml180MailFw" value="1">
      <tr>
      <td align="left" width="100%">
        <span class="text_base"><gsmsg:write key="sml.sml180kn.05" /></span><br>

        <span class="text_base">
        <logic:equal name="sml180knForm" property="sml180SmailOp" value="0"><gsmsg:write key="sml.sml180kn.02" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180SmailOp" value="1"><gsmsg:write key="sml.sml180kn.03" /></logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="sml.81" /><gsmsg:write key="wml.215" /></span>
        <span class="text_base">
        <logic:equal name="sml180knForm" property="sml180MailDfSelected" value="0"><bean:write name="sml180knForm" property="sml180MailDf" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180MailDfSelected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180MailDfSelected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180MailDfSelected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>


      <logic:equal name="sml180knForm" property="sml180ZaisekiPlugin" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.PLUGIN_USE) %>">
      <logic:equal name="sml180knForm" property="sml180HuriwakeKbn" value="1">
      <tr>
      <td width="100%" align="left" nowrap>
      <hr style="border-color:#cccccc;">
      <span class="text_base3"><gsmsg:write key="sml.sml180kn.15" /></span>
        <span class="text_base">
        <logic:equal name="sml180knForm" property="sml180Zmail1Selected" value="0"><bean:write name="sml180knForm" property="sml180Zmail1" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail1Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail1Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail1Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="sml.sml180kn.16" /></span>
        <span class="text_base">
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="0"><bean:write name="sml180knForm" property="sml180Zmail2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="cmn.other" /><gsmsg:write key="wml.215" /></span>
        <span class="text_base">
        <logic:equal name="sml180knForm" property="sml180Zmail3Selected" value="0"><bean:write name="sml180knForm" property="sml180Zmail3" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail3Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail3Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail3Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
        </span>
      </td>
      </tr>
      </logic:equal>


      <logic:equal name="sml180knForm" property="sml180HuriwakeKbn" value="2">

      <tr>
      <td width="100%" align="left" nowrap><span class="text_base3"><gsmsg:write key="sml.sml180kn.16" /></span>
        <span class="text_base">
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="0"><bean:write name="sml180knForm" property="sml180Zmail2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="1"><gsmsg:write key="cmn.mailaddress1" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="2"><gsmsg:write key="cmn.mailaddress2" /></logic:equal>
        <logic:equal name="sml180knForm" property="sml180Zmail2Selected" value="3"><gsmsg:write key="cmn.mailaddress3" /></logic:equal>
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

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('setting');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back');">
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