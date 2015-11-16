<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.preferences2" /></title>
</head>

<body class="body_03">

<html:form action="/webmail/wml090">
<input type="hidden" name="CMD" value="init">
<html:hidden property="backScreen" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="60%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2"></td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('mailList');">
        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('accountList');"><span class="text_link"><gsmsg:write key="wml.100" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml090.04" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('labelList');"><span class="text_link"><gsmsg:write key="cmn.label.management" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml090.02" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('filterList');"><span class="text_link"><gsmsg:write key="wml.86" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml090.03" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('confSendList');"><span class="text_link">送信先リスト管理</span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.15" /></li></div></dd>
          </dl>
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