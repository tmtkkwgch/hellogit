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
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../main/js/man070.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man002.30" /></title>
</head>

<body class="body_03" onload="man070load();">

<html:form action="/main/man070">
<input type="hidden" name="CMD" value="kakunin">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.30" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="submit" class="btn_ok1" value="OK">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back_to_kanri_menu');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man002.30" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td align="left" class="td_wt2" width="100%">
      <span class="text_gray">
        <html:radio styleId="proxyuse" name="man070Form" property="man070pxyUseKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PROXY_SERVER_NOT_USE) %>" onclick="radioChkChange(0)" /><label for="proxyuse"><gsmsg:write key="main.man070kn.2" /></label>
        <html:radio styleId="proxynotuse" name="man070Form" property="man070pxyUseKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PROXY_SERVER_USE) %>" onclick="radioChkChange(1)" /><label for="proxynotuse"><gsmsg:write key="main.man070kn.3" /></label>
      </span>
      <br><br>
      <table width="100%" border="0" cellpadding="2">
      <tr>
      <td width="0%" nowrap><span class="text_gray"><gsmsg:write key="cmn.address.2" />：</span></td>
      <td width="100%"><html:text name="man070Form" property="man070address" style="width:213px" maxlength="200" /></td>
      <td align="left" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="main.man070.1" /></span>
      </td>
      <tr>
      <td nowrap><span class="text_gray"><gsmsg:write key="cmn.port.number" />：</span></td>
      <td><html:text name="man070Form" property="man070portnum" style="width:63px" maxlength="5" /></td>
      <td align="left" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="main.man070.3" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr id="proxyAuth">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man070.4" /></span>
    <span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <span class="text_gray">
        <html:checkbox styleId="userAuth" name="man070Form" property="man070Auth" value="<%= String.valueOf(jp.groupsession.v2.man.man070.Man070Form.MAN070_AUTH_USE) %>" onclick="chkUserAuth();" /><label for="userAuth"><gsmsg:write key="main.man070.5" /></label>
      </span>
      <br>
      <span class="text_r1"><gsmsg:write key="main.man070.10" /></span>
      <table width="100%" border="0" cellpadding="2" id="userAuthElement">
      <tr>
      <td width="0%" nowrap><span class="text_gray"><gsmsg:write key="cmn.user" />：</span></td>
      <td width="100%"><html:text name="man070Form" property="man070AuthUser" style="width:153px" maxlength="256" /></td>
      <td align="left" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man070.6" /></span>
      </td>
      <tr>
      <td nowrap><span class="text_gray"><gsmsg:write key="user.117" />：</span></td>
      <td><html:password name="man070Form" property="man070AuthPassword" style="width:153px" maxlength="256" /></td>
      <td align="left" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man070.7" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr id="proxyAuthElement">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man070.9" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <table width="100%" border="0" cellpadding="2" id="userAuthElement">
      <tr>
      <td width="100%">
      <span class="text_r1"><gsmsg:write key="sml.sml110.03" /></span>
      <br><span class="text_r1"><gsmsg:write key="main.man070.11" /></span>
      <html:textarea name="man070Form" property="man070NoProxyAddress" style="width:671px;" rows="6"></html:textarea>
      </span>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="submit" class="btn_ok1" value="OK">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back_to_kanri_menu');">
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