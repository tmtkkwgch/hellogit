<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting" />[<gsmsg:write key="cmn.reserve" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../reserve/js/rsv040.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv040">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv040Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv040Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv040Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv040Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.reserve" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('kihon_settei');"><span class="text_link"><gsmsg:write key="reserve.rsv040.1" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv040.2" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('batch_settei');"><span class="text_link"><gsmsg:write key="cmn.automatic.delete.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv040.4" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('data_sakuzyo');"><span class="text_link"><gsmsg:write key="cmn.manual.delete" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv040.6" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('yoyaku_import');"><span class="text_link"><gsmsg:write key="reserve.37" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv040.7" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('init_settei');"><span class="text_link"><gsmsg:write key="reserve.src.39" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv040.8" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('zikantai_settei');"><span class="text_link"><gsmsg:write key="reserve.168" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv040.9" /></div></dd>
      </dl>
      <logic:equal name="rsv040Form" property="canUseSmlKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">      
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smail_settei');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.159" /></div></dd>
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