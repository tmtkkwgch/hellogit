<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.admin.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/file/fil200">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="backDsp" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="filSearchWd" />

<logic:notEmpty name="fil200Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil200Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil200Form" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil200Form" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('fil200back');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush('fil200baseConf');"><span class="text_link"><gsmsg:write key="cmn.preferences" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="fil.fil200.1" /></li></div></dd>
      </dl>
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush('fil200cabinetConf');"><span class="text_link"><gsmsg:write key="fil.62" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="fil.fil200.2" /></li></div></dd>
      </dl>
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush('fil200deleteFile');"><span class="text_link"><gsmsg:write key="fil.27" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="fil.fil200.3" /></div></dd>
      </dl>
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="buttonPush('fil200call');"><span class="text_link"><gsmsg:write key="fil.124" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="fil.fil200.4" /></div></dd>
      </dl>
      <logic:equal name="fil200Form" property="canUseSml" value="<%=String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('fil200smlConf');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.159" /></div></dd>
      </dl>
      </logic:equal>
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('fil200logCnt');"><span class="text_link"><gsmsg:write key="cmn.statistical.info" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="fil.fil200.5" /></div></dd>
      </dl>
      <logic:equal name="fil200Form" property="fil200GsAdminFlg" value="true">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('filLogAutoDelete');"><span class="text_link"><gsmsg:write key="cmn.statistics.automatic.delete.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="cmn.statistics.automatic.delete.setting.comment" /></li></div></dd>
      </dl>
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('filLogManualDelete');"><span class="text_link"><gsmsg:write key="cmn.statistics.manual.delete" /></span></a></dt>
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