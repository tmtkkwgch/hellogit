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
<title>[Group Session] <gsmsg:write key="cmn.manual.delete.kn" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/webmail/wml060kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden name="wml060knForm" property="wmlViewAccount" />
<html:hidden name="wml060knForm" property="wml060delKbn1" />
<html:hidden name="wml060knForm" property="wml060delYear1" />
<html:hidden name="wml060knForm" property="wml060delMonth1" />
<html:hidden name="wml060knForm" property="wml060delDay1" />
<html:hidden name="wml060knForm" property="wml060delKbn2" />
<html:hidden name="wml060knForm" property="wml060delYear2" />
<html:hidden name="wml060knForm" property="wml060delMonth2" />
<html:hidden name="wml060knForm" property="wml060delDay2" />
<html:hidden name="wml060knForm" property="wml060delKbn3" />
<html:hidden name="wml060knForm" property="wml060delYear3" />
<html:hidden name="wml060knForm" property="wml060delMonth3" />
<html:hidden name="wml060knForm" property="wml060delDay3" />
<html:hidden name="wml060knForm" property="wml060delKbn4" />
<html:hidden name="wml060knForm" property="wml060delYear4" />
<html:hidden name="wml060knForm" property="wml060delMonth4" />
<html:hidden name="wml060knForm" property="wml060delDay4" />
<html:hidden name="wml060knForm" property="wml060delKbn5" />
<html:hidden name="wml060knForm" property="wml060delYear5" />
<html:hidden name="wml060knForm" property="wml060delMonth5" />
<html:hidden name="wml060knForm" property="wml060delDay5" />

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.manual.delete.kn" /> ]</td>
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
          <logic:equal name="wml060knForm" property="wml060delKbn1" value="0">
            <bean:write name="wml060knForm" property="manuDustDelSetUp" />
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn1" value="1">
            <span class="del"><bean:write name="wml060knForm" property="manuDustDelSetUp" /></span>
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn1" value="1">
            <bean:define id="dyear" name="wml060knForm" property="manuDustDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= dyear %>" />
            <bean:define id="dmonth" name="wml060knForm" property="manuDustDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= dmonth %>" />
            <bean:write name="wml060knForm" property="manuDustDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="wml.19" /></th>
        <td>
          <logic:equal name="wml060knForm" property="wml060delKbn2" value="0">
            <bean:write name="wml060knForm" property="manuSendDelSetUp" />
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn2" value="1">
            <span class="del"><bean:write name="wml060knForm" property="manuSendDelSetUp" /></span>
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn2" value="1">
            <bean:define id="syear" name="wml060knForm" property="manuSendDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= syear %>" />
            <bean:define id="smonth" name="wml060knForm" property="manuSendDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= smonth %>" />
            <bean:write name="wml060knForm" property="manuSendDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="cmn.draft" /></th>
        <td>
          <logic:equal name="wml060knForm" property="wml060delKbn3" value="0">
            <bean:write name="wml060knForm" property="manuDraftDelSetUp" />
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn3" value="1">
            <span class="del"><bean:write name="wml060knForm" property="manuDraftDelSetUp" /></span>
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn3" value="1">
            <bean:define id="ddyear" name="wml060knForm" property="manuDraftDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= ddyear %>" />
            <bean:define id="ddmonth" name="wml060knForm" property="manuDraftDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= ddmonth %>" />
            <bean:write name="wml060knForm" property="manuDraftDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="wml.37" /></th>
        <td>
          <logic:equal name="wml060knForm" property="wml060delKbn4" value="0">
            <bean:write name="wml060knForm" property="manuResvDelSetUp" />
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn4" value="1">
            <span class="del"><bean:write name="wml060knForm" property="manuResvDelSetUp" /></span>
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn4" value="1">
            <bean:define id="ryear" name="wml060knForm" property="manuResvDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= ryear %>" />
            <bean:define id="rmonth" name="wml060knForm" property="manuResvDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= rmonth %>" />
            <bean:write name="wml060knForm" property="manuResvDelSetUpDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </td>
        </tr>

        <tr>
        <th><gsmsg:write key="cmn.strage" /></th>
        <td>
          <logic:equal name="wml060knForm" property="wml060delKbn5" value="0">
            <bean:write name="wml060knForm" property="manuKeepDelSetUp" />
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn5" value="1">
            <span class="del"><bean:write name="wml060knForm" property="manuKeepDelSetUp" /></span>
          </logic:equal>
          <logic:equal name="wml060knForm" property="wml060delKbn5" value="1">
            <bean:define id="kyear" name="wml060knForm" property="manuKeepDelSetUpYear" type="java.lang.String" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= kyear %>" />
            <bean:define id="kmonth" name="wml060knForm" property="manuKeepDelSetUpMonth" type="java.lang.String" />
            <gsmsg:write key="cmn.months" arg0="<%= kmonth %>" />
            <bean:write name="wml060knForm" property="manuKeepDelSetUpDay" /><gsmsg:write key="cmn.day" />
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