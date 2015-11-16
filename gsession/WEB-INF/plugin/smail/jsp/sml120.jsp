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
<title>[GroupSession] <gsmsg:write key="cmn.preferences.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">
<html:form action="/smail/sml120">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountMode" />
<html:hidden property="smlAccountSid" />

<logic:notEmpty name="sml120Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml120Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.shortmail" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToMailList');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">



    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailAccount');"><span class="text_link"><gsmsg:write key="wml.100" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="wml.wml020.09" /></li></div></dd>
      </dl>
    </td>
    </tr>


<%--
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailLabel');"><span class="text_link"><gsmsg:write key="cmn.label.management" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="wml.wml090.02" /></li></div></dd>
      </dl>
    </td>
    </tr>


    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailFilter');"><span class="text_link"><gsmsg:write key="wml.86" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="wml.wml090.03" /></li></div></dd>
      </dl>
    </td>
    </tr>
--%>




    <logic:equal name="sml120Form" property="sml120DispAdminConf" value="0">
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailKsetting');"><span class="text_link"><gsmsg:write key="cmn.display.settings" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="sml.sml120.07" /></li></div></dd>
      </dl>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailMainsetting');"><span class="text_link"><gsmsg:write key="cmn.setting.main.view" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="sml.sml120.06" /></li></div></dd>
      </dl>
    </td>
    </tr>


    <logic:equal name="sml120Form" property="sml120MailFwAdminConf" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAIL_FORWARD_OK) %>">
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailForwardsetting');"><span class="text_link"><gsmsg:write key="sml.20" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="sml.sml120.03" /></li></div></dd>
      </dl>
    </td>
    </tr>
    </logic:equal>

<%--
    <logic:equal name="sml120Form" property="sml120MailDelAdminConf" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_DEL_KBN_USER_SETTING) %>">
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailAutoDelete');"><span class="text_link"><gsmsg:write key="sml.06" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="sml.sml120.01" /></li></div></dd>
      </dl>
    </td>
    </tr>
    </logic:equal>
--%>

    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailManualDelete');"><span class="text_link"><gsmsg:write key="sml.09" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="sml.sml120.02" /></li></div></dd>
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


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</html:form>
</body>
</html:html>