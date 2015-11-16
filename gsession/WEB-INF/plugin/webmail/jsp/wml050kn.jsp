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
<title>[Group Session] <gsmsg:write key="wml.wml050kn.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/webmail/wml050kn">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<html:hidden property="backScreen" />
<html:hidden name="wml050knForm" property="wmlCmdMode" />
<html:hidden name="wml050knForm" property="wmlViewAccount" />
<html:hidden name="wml050knForm" property="wmlAccountMode" />
<html:hidden name="wml050knForm" property="wmlAccountSid" />
<html:hidden name="wml050knForm" property="wml050dustKbn" />
<html:hidden name="wml050knForm" property="wml050dustYear" />
<html:hidden name="wml050knForm" property="wml050dustMonth" />
<html:hidden name="wml050knForm" property="wml050dustDay" />
<html:hidden name="wml050knForm" property="wml050sendKbn" />
<html:hidden name="wml050knForm" property="wml050sendYear" />
<html:hidden name="wml050knForm" property="wml050sendMonth" />
<html:hidden name="wml050knForm" property="wml050sendDay" />
<html:hidden name="wml050knForm" property="wml050draftKbn" />
<html:hidden name="wml050knForm" property="wml050draftYear" />
<html:hidden name="wml050knForm" property="wml050draftMonth" />
<html:hidden name="wml050knForm" property="wml050draftDay" />
<html:hidden name="wml050knForm" property="wml050resvKbn" />
<html:hidden name="wml050knForm" property="wml050resvYear" />
<html:hidden name="wml050knForm" property="wml050resvMonth" />
<html:hidden name="wml050knForm" property="wml050resvDay" />
<html:hidden name="wml050knForm" property="wml050keepKbn" />
<html:hidden name="wml050knForm" property="wml050keepYear" />
<html:hidden name="wml050knForm" property="wml050keepMonth" />
<html:hidden name="wml050knForm" property="wml050keepDay" />

<html:hidden name="wml050knForm" property="wml050initFlg" />

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml050kn.01" /> ]</td>
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

  </td></tr>
  <tr><td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table summary="" id="wml_settings">

        <tr>
        <th><gsmsg:write key="cmn.trash" /></th>
        <td>
          <bean:write name="wml050knForm" property="dustDelSetUp" />
          <logic:equal name="wml050knForm" property="wml050dustKbn" value="2">
            <bean:define id="dyear" name="wml050knForm" property="dustDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= dyear %>" />
            <bean:define id="dmonth" name="wml050knForm" property="dustDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= dmonth %>" />
            <bean:write name="wml050knForm" property="dustDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="wml.19" /></th>
        <td>
          <bean:write name="wml050knForm" property="sendDelSetUp" />
          <logic:equal name="wml050knForm" property="wml050sendKbn" value="2">
            <bean:define id="syear" name="wml050knForm" property="sendDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= syear %>" />
            <bean:define id="smonth" name="wml050knForm" property="sendDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= smonth %>" />
            <bean:write name="wml050knForm" property="sendDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="cmn.draft" /></th>
        <td>
          <bean:write name="wml050knForm" property="draftDelSetUp" />
          <logic:equal name="wml050knForm" property="wml050draftKbn" value="2">
            <bean:define id="ddyear" name="wml050knForm" property="draftDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%=ddyear %>" />
            <bean:define id="ddmonth" name="wml050knForm" property="draftDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= ddmonth %>" />
            <bean:write name="wml050knForm" property="draftDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="wml.37" /></th>
        <td>
          <bean:write name="wml050knForm" property="resvDelSetUp" />
          <logic:equal name="wml050knForm" property="wml050resvKbn" value="2">
            <bean:define id="ryear" name="wml050knForm" property="resvDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%=ryear %>" />
            <bean:define id="rmonth" name="wml050knForm" property="resvDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= rmonth %>" />
            <bean:write name="wml050knForm" property="resvDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="cmn.strage" /></th>
        <td>
          <bean:write name="wml050knForm" property="keepDelSetUp" />
          <logic:equal name="wml050knForm" property="wml050keepKbn" value="2">
            <bean:define id="kyear" name="wml050knForm" property="keepDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= kyear %>" />
            <bean:define id="kmonth" name="wml050knForm" property="keepDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= kmonth %>" />
            <bean:write name="wml050knForm" property="keepDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
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