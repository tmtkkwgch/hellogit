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
<title>[Group Session] <gsmsg:write key="cmn.admin.setting" /></title>
</head>

<body class="body_03">

<html:form action="/webmail/wml020">
<input type="hidden" name="CMD" value="init">
<html:hidden property="wmlViewAccount" />
<input type="hidden" name="wmlAccountMode" value="2">
<html:hidden property="backScreen" />

<input type="hidden" name="wmlMailTemplateKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_COMMON) %>">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table summary="" width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table summary="" width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2"></td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table summary="" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('mailList');">
        </td>
      </tr>
    </table>

    <table summary="" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('accountManager');"><img src="../main/images/icon_syain.gif" border="0" alt="<gsmsg:write key="wml.wml020.08" />"><span class="text_link"><gsmsg:write key="wml.wml020.08" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.09" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('confAccount');"><img src="../main/images/icon_syain.gif" border="0" alt="<gsmsg:write key="wml.wml020.07" />"><span class="text_link"><gsmsg:write key="wml.wml020.07" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.10" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('confMailTemplate');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="anp.anp070.02" />"><span class="text_link"><gsmsg:write key="anp.anp070.02" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.13" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('confSendList');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="wml.wml020.14" />"><span class="text_link">送信先リスト管理</span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.15" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('autoDelete');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="cmn.automatic.delete.setting" />"><span class="text_link"><gsmsg:write key="cmn.automatic.delete.setting" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.02" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('manualDelete');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="cmn.manual.delete" />"><span class="text_link"><gsmsg:write key="cmn.manual.delete" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.01" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('timesentManager');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="wml.259" />"><span class="text_link"><gsmsg:write key="wml.259" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.12" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('mailLog');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="wml.wml070.03" />"><span class="text_link"><gsmsg:write key="wml.wml070.03" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.06" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('autoDeleteHistory');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="wml.22" />"><span class="text_link"><gsmsg:write key="wml.22" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.05" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('manualDeleteHistory');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="wml.21" />"><span class="text_link"><gsmsg:write key="wml.21" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.04" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('wmlLogCount');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="cmn.statistical.info" />"><span class="text_link"><gsmsg:write key="cmn.statistical.info" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="wml.wml020.16" /></li></div></dd>
          </dl>

          <logic:equal name="wml020Form" property="wml020GsAdminFlg" value="true">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('wmlLogAutoDelete');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="cmn.statistics.automatic.delete.setting" />"><span class="text_link"><gsmsg:write key="cmn.statistics.automatic.delete.setting" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="cmn.statistics.automatic.delete.setting.comment" /></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('wmlLogManualDelete');"><img src="../main/images/icon_batch.gif" border="0" alt="<gsmsg:write key="cmn.statistics.manual.delete" />"><span class="text_link"><gsmsg:write key="cmn.statistics.manual.delete" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="cmn.statistics.manual.delete.comment" /></li></div></dd>
          </dl>
          </logic:equal>

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