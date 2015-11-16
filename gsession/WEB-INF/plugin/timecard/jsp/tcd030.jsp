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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="tcd.50" /> <gsmsg:write key="cmn.admin.setting" />[<gsmsg:write key="tcd.48" />]</title>
</head>
<body class="body_03">
<html:form action="/timecard/tcd030" >

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />

<logic:notEmpty name="tcd030Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd030Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2"><gsmsg:write key="tcd.104" /></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td align="left">
    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back')">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td align="left">
    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="return buttonPush('mng');"><span class="text_link"><gsmsg:write key="tcd.49" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="tcd.tcd030.04" /></li></div></dd>
    </dl>
    <logic:equal name="tcd030Form" property="menuLevel" value="2">
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="return buttonPush('base_conf');"><span class="text_link"><gsmsg:write key="cmn.preferences" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="tcd.tcd030.02" /></div></dd>
    </dl>
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="return buttonPush('timezone');"><span class="text_link"><gsmsg:write key="tcd.tcd030.01" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="tcd.tcd030.03" /></li></div></dd>
    </dl>
    </logic:equal>
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="return buttonPush('editAuth');"><span class="text_link"><gsmsg:write key="tcd.06" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="tcd.tcd030.05" /></li></div></dd>
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