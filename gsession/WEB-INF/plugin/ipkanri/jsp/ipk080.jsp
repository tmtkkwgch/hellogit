<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
</head>
<body class="body_03">
<html:form action="/ipkanri/ipk080">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />" /></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ipk080Return');" class="btn_back_n1"></td>
    </td>
    </tr>
    </table>
  </td>
  </tr>


  <tr>
  <td>
    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="buttonPush2('adminSetting');"><span class="text_link"><gsmsg:write key="ipk.14" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="ipk.ipk080.1" /></li></div></dd>
    </dl>
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="buttonPush2('specMstSetting');"><span class="text_link"><gsmsg:write key="ipk.10" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="ipk.ipk080.2" /></div></dd>
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