<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.preferences2" />[<gsmsg:write key="cmn.reserve" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../reserve/js/rsv140.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">
<html:form action="/reserve/rsv140">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv140Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv140Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv140Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv140Form" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2"><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
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
      <dt><a href="#" onclick="buttonPush('syokiti_settei');"><span class="text_link"><gsmsg:write key="cmn.default.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv140.2" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('hyozi_settei');"><span class="text_link"><gsmsg:write key="cmn.display.settings" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv140.3" /></div></dd>
      </dl>

      <logic:equal name="rsv140Form" property="rsv140canEditDtm" value="true">
      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('zikantai_settei');"><span class="text_link"><gsmsg:write key="cmn.show.timezone.days.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv140.4" /></div></dd>
      </dl>
      </logic:equal>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('kensu_settei');"><span class="text_link"><gsmsg:write key="reserve.98" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv140.5" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('main_settei');"><span class="text_link"><gsmsg:write key="cmn.setting.main.view2" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="reserve.rsv140.7" /></div></dd>
      </dl>

      <logic:equal name="rsv140Form" property="rsv140SmailSendKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RAC_SMAIL_SEND_KBN_USER) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onclick="buttonPush('smail_settei');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="fil.fil110.2" /></div></dd>
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