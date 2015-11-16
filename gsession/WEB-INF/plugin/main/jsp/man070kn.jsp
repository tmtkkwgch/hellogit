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
<script language="JavaScript" src="../main/js/man070kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man070kn.1" /></title>
</head>

<body class="body_03">

<html:form action="/main/man070kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="man070pxyUseKbn" />
<html:hidden property="man070address" />
<html:hidden property="man070portnum" />
<html:hidden property="man070Auth" />
<html:hidden property="man070AuthUser" />
<html:hidden property="man070AuthPassword" />
<html:hidden property="man070NoProxyAddress" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man070kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.setting" />" onClick="buttonPush('settei');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('input');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man002.30" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <span class="text_gray">
        <logic:equal name="man070knForm" property="man070pxyUseKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PROXY_SERVER_NOT_USE) %>">
          &nbsp;<gsmsg:write key="main.man070kn.2" />
        </logic:equal>

        <logic:equal name="man070knForm" property="man070pxyUseKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PROXY_SERVER_USE) %>">
          &nbsp;<gsmsg:write key="main.man070kn.3" /><br><br>
          <table width="100%" border="0" cellpadding="2">
          <tr>
          <td width="0%" nowrap><span class="text_gray"><gsmsg:write key="cmn.address.2" />：</span></td>
          <td width="100%"><bean:write name="man070knForm" property="man070address" /></td>
          <tr>
          <td nowrap><span class="text_gray"><gsmsg:write key="cmn.port.number" />：</span></td>
          <td><bean:write name="man070knForm" property="man070portnum" /></td>
          </tr>
          </table>
        </logic:equal>
      </span>
    </td>
    </tr>

    <logic:equal name="man070knForm" property="man070pxyUseKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PROXY_SERVER_USE) %>">

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man070.4" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <logic:equal name="man070Form" property="man070Auth" value="<%= String.valueOf(jp.groupsession.v2.man.man070.Man070Form.MAN070_AUTH_USE) %>">
      <gsmsg:write key="main.man070.5" /><br><br>
      <span class="text_gray"><gsmsg:write key="cmn.user" />：</span><bean:write name="man070Form" property="man070AuthUser" /><br>
      <span class="text_gray"><gsmsg:write key="user.117" />：*****</span>
      </logic:equal>
      <logic:notEqual name="man070Form" property="man070Auth" value="<%= String.valueOf(jp.groupsession.v2.man.man070.Man070Form.MAN070_AUTH_USE) %>">
      <gsmsg:write key="main.man070kn.4" />
      </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man070kn.2" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <logic:notEmpty name="man070knForm" property="man070knViewNoProxyAddress">
        <span class="text_gray">
        <logic:iterate id="address" name="man070knForm" property="man070knViewNoProxyAddress">
        <bean:write name="address" /><br>
        </logic:iterate>
        </span>
      </logic:notEmpty>
    </td>
    </tr>

    </logic:equal>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.setting" />" onClick="buttonPush('settei');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('input');">
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