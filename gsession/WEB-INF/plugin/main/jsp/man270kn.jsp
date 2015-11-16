<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConstCommon" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="main.man270kn.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../main/js/man270.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03" onload="setDispConfirm();">

<html:form action="/main/man270kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="man270InitFlg" />
<html:hidden property="man270lockKbn" />
<html:hidden property="man270failCount" />
<html:hidden property="man270failTime" />
<html:hidden property="man270lockTime" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man270kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
　　　<input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('conf');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back_man270');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td width="35%" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man270.1" /></span>
    </td>
    <td width="65%" valign="middle" align="left" class="td_wt text_base">
      <logic:equal name="man270knForm" property="man270lockKbn" value="<%= String.valueOf(GSConstCommon.LOGIN_LOCKKBN_NOSET) %>">
      <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="man270knForm" property="man270lockKbn" value="<%= String.valueOf(GSConstCommon.LOGIN_LOCKKBN_LOCKOUT) %>">
      <gsmsg:write key="main.man270.2" />
      </logic:equal>
    </td>
    </tr>

    <tr id="confRow2">
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.number.failure" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt text_base">
      <bean:define id="failCnt" name="man270Form" property="man270failCount" type="java.lang.Integer" />
      <gsmsg:write key="main.man270.4" arg0="<%= String.valueOf(failCnt.intValue()) %>" />
    </td>
    </tr>

    <tr>
    <td id="confRow4" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.lockout.time" /></span>
    </td>
    <td id="confRow5" valign="middle" align="left" class="td_wt text_base">
      <bean:write name="man270Form" property="man270lockTime" />&nbsp;<gsmsg:write key="cmn.minute" />
    </td>
    </tr>

    <tr id="confRow3">
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.reset.number.failure" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt text_base">
      <bean:write name="man270Form" property="man270failTime" />&nbsp;<gsmsg:write key="main.man270.9" />
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <td>
  <tr>
    <table width="70%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
　　　<input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('conf');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back_man270');">
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