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
<title>[Group Session] <gsmsg:write key="wml.wml180kn.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml180kn.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="wml180knChangeDelKbn();">

<html:form action="/webmail/wml180kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden name="wml180knForm" property="wmlViewAccount" />

<html:hidden name="wml180knForm" property="wml180delKbn" />
<html:hidden name="wml180knForm" property="wml180delYear" />
<html:hidden name="wml180knForm" property="wml180delMonth" />
<html:hidden name="wml180knForm" property="wml180delDay" />
<html:hidden name="wml180knForm" property="wml180delYearFr" />
<html:hidden name="wml180knForm" property="wml180delMonthFr" />
<html:hidden name="wml180knForm" property="wml180delDayFr" />
<html:hidden name="wml180knForm" property="wml180delYearTo" />
<html:hidden name="wml180knForm" property="wml180delMonthTo" />
<html:hidden name="wml180knForm" property="wml180delDayTo" />

<table summary="" align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table summary="" cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml180kn.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr><td><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td></tr>

  <logic:messagesPresent message="false">
    <tr><td><html:errors/></td></tr>
    <tr><td><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <table summary="" id="wml_settings">
      <tr>
      <th><gsmsg:write key="wml.59" /></th>
      <td id="dateElement">
        <bean:define id="yr" name="wml180knForm" property="manuDelSetUpYear" type="java.lang.String" />
        <gsmsg:write key="cmn.year" arg0="<%= yr %>" />
        <bean:define id="month" name="wml180knForm" property="manuDelSetUpMonth" type="java.lang.String" />
        <gsmsg:write key="cmn.months" arg0="<%= month %>" />
        <bean:write name="wml180knForm" property="manuDelSetUpDay" /><gsmsg:write key="cmn.day" />
        <gsmsg:write key="wml.73" />
      </td>
      <td id="dateAreaElement">
        <bean:define id="yr2" name="wml180knForm" property="wml180delYearFr" type="java.lang.Integer" />
        <gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr2) %>" />
        <bean:write name="wml180knForm" property="wml180delMonthFr" /><gsmsg:write key="cmn.month" />
        <bean:write name="wml180knForm" property="wml180delDayFr" /><gsmsg:write key="cmn.day" />
        &nbsp;<gsmsg:write key="tcd.153" />&nbsp;
        <bean:define id="yr3" name="wml180knForm" property="wml180delYearTo" type="java.lang.Integer" />
        <gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr3) %>" />
        <bean:write name="wml180knForm" property="wml180delMonthTo" /><gsmsg:write key="cmn.month" />
        <bean:write name="wml180knForm" property="wml180delDayTo" /><gsmsg:write key="cmn.day" />
      </td>
      <td id="dateAreaElement2"><gsmsg:write key="wml.05" /></td>
      <td id="allDelElement"><gsmsg:write key="cmn.delete.all" /></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">
    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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