<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="cmn.change.password" /></title>
</head>

<body class="body_03">
<html:form action="/user/usr050">
<input type="hidden" name="CMD" value="ok">
<html:hidden property="usr050CoeKbn" />
<html:hidden property="usr050Digit" />
<html:hidden property="usr050UidPswdKbn" />
<html:hidden property="usr050OldPswdKbn" />
<html:hidden property="usr050Mode" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <bean:define id="kusr" name="<%= key %>" scope="session" />
    <logic:notEqual name="kusr" property="usrsid" value="0">
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    </logic:notEqual>
    <logic:equal name="kusr" property="usrsid" value="0">
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    </logic:equal>

    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.change.password" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('backToMenu');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:equal name="usr050Form" property="usr050Mode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.PSWD_MODE_UPDATE)  %>"><div class="attent1"><gsmsg:write key="user.17" /></div></logic:equal>
    <logic:equal name="usr050Form" property="usr050Mode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.PSWD_MODE_LIMIT)  %>"><div class="attent1"><gsmsg:write key="user.usr050.2" /><br><gsmsg:write key="user.17" /></div></logic:equal>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <!-- パスワード -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.src.28" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
      <html:password name="usr050Form" property="usr050OldPassWord" style="width:333px" maxlength="256" />
    </td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.src.26" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="5">
      <html:password name="usr050Form" property="usr050NewPassWord" style="width:333px" maxlength="256" /><br>
      <img src="../common/images/spacer.gif" width="1px" height="3px" border="0"><br>
      <html:password name="usr050Form" property="usr050NewPassWordKn" style="width:333px" maxlength="256" />&nbsp;<span class="text_base"><gsmsg:write key="user.19" /></span><br>
      <bean:define id="digitStr" name="usr050Form" property="usr050Digit" type="java.lang.Integer" />
      <span class="text_base">*<gsmsg:write key="user.usr031.10" arg0="<%= String.valueOf(digitStr.intValue()) %>" /></span><br>
      <logic:equal name="usr050Form" property="usr050CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_EN)  %>"><span class="text_base">*<gsmsg:write key="user.usr031.12" /></span><br></logic:equal>
      <logic:equal name="usr050Form" property="usr050CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_ENS)  %>"><span class="text_base">*<gsmsg:write key="user.usr031.19" /></span><br></logic:equal>
      <span class="text_base">*<gsmsg:write key="user.usr031.11" /></span><br>
      <logic:equal name="usr050Form" property="usr050UidPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_UIDPSWDKBN_ON)  %>"><br><span class="text_base">*<gsmsg:write key="user.usr031.13" /></span></logic:equal>
      <logic:equal name="usr050Form" property="usr050OldPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_OLDPSWDKBN_ON)  %>"><br><span class="text_base">*<gsmsg:write key="user.usr050.11" /></span></logic:equal>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('backToMenu');">
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