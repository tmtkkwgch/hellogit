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
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/bulletin/bbs110">

<input type="hidden" name="CMD" value="">
<html:hidden name="bbs110Form" property="backScreen" />
<html:hidden name="bbs110Form" property="s_key" />
<html:hidden name="bbs110Form" property="bbs010page1" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.bulletin" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backBBSList');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('confForum');"><span class="text_link"><gsmsg:write key="cmn.bulletin" />-<gsmsg:write key="bbs.14" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="bbs.bbs110.1" /></li></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('delConf');"><span class="text_link"><gsmsg:write key="cmn.automatic.delete.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="bbs.bbs110.2" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('manualDelConf');"><span class="text_link"><gsmsg:write key="cmn.manual.delete" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="bbs.bbs110.3" /></div></dd>
      </dl>
      <logic:equal name="bbs110Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smlSetting');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.159" /></div></dd>
      </dl>
      </logic:equal>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('bbsLogCount');"><span class="text_link"><gsmsg:write key="cmn.statistical.info" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="bbs.bbs110.4" /></div></dd>
      </dl>

      <logic:equal name="bbs110Form" property="bbs110GsAdminFlg" value="true">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('bbsLogAutoDelete');"><span class="text_link"><gsmsg:write key="cmn.statistics.automatic.delete.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="cmn.statistics.automatic.delete.setting.comment" /></li></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('bbsLogManualDelete');"><span class="text_link"><gsmsg:write key="cmn.statistics.manual.delete" /></span></a></dt>
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


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</html:form>
</body>
</html:html>