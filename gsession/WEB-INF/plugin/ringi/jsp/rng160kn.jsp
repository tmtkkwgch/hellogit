<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%
  String delTypeNo = String.valueOf(jp.groupsession.v2.rng.RngConst.RAD_KBN_NO);
  String delTypeDelete = String.valueOf(jp.groupsession.v2.rng.RngConst.RAD_KBN_DELETE);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="reserve.rsv120kn.1" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/ringi/rng160kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden name="rng160knForm" property="rng160pendingKbn" />
<html:hidden name="rng160knForm" property="rng160pendingYear" />
<html:hidden name="rng160knForm" property="rng160pendingMonth" />
<html:hidden name="rng160knForm" property="rng160pendingDay" />
<html:hidden name="rng160knForm" property="rng160completeKbn" />
<html:hidden name="rng160knForm" property="rng160completeYear" />
<html:hidden name="rng160knForm" property="rng160completeMonth" />
<html:hidden name="rng160knForm" property="rng160completeDay" />
<html:hidden name="rng160knForm" property="rng160draftKbn" />
<html:hidden name="rng160knForm" property="rng160draftYear" />
<html:hidden name="rng160knForm" property="rng160draftMonth" />
<html:hidden name="rng160knForm" property="rng160draftDay" />

<html:hidden name="rng160knForm" property="rng160initFlg" />

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="reserve.rsv120kn.1" /> ]</td>
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

    <table width="100%" class="tl0" border="0" cellpadding="5" style="margin-top: 10px">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="rng.48" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
          <logic:equal name="rng160knForm" property="rng160pendingKbn" value="<%= delTypeNo %>" ><gsmsg:write key="cmn.noset" /></logic:equal>
          <logic:equal name="rng160knForm" property="rng160pendingKbn" value="<%= delTypeDelete %>" >

            <bean:define id="year1" name="rng160knForm" property="rng160pendingYear" type="java.lang.Integer" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(year1.intValue()) %>" />
            <bean:define id="month1" name="rng160knForm" property="rng160pendingMonth" type="java.lang.Integer" />
            <gsmsg:write key="cmn.months" arg0="<%= String.valueOf(month1.intValue()) %>" />
            <bean:write name="rng160knForm" property="rng160pendingDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.complete" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
          <logic:equal name="rng160knForm" property="rng160completeKbn" value="<%= delTypeNo %>" ><gsmsg:write key="cmn.noset" /></logic:equal>
          <logic:equal name="rng160knForm" property="rng160completeKbn" value="<%= delTypeDelete %>" >

            <bean:define id="year2" name="rng160knForm" property="rng160completeYear" type="java.lang.Integer" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(year2.intValue()) %>" />
            <bean:define id="month2" name="rng160knForm" property="rng160completeMonth" type="java.lang.Integer" />
            <gsmsg:write key="cmn.months" arg0="<%= String.valueOf(month2.intValue()) %>" />
            <bean:write name="rng160knForm" property="rng160completeDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.draft" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
          <logic:equal name="rng160knForm" property="rng160draftKbn" value="<%= delTypeNo %>" ><gsmsg:write key="cmn.noset" /></logic:equal>
          <logic:equal name="rng160knForm" property="rng160draftKbn" value="<%= delTypeDelete %>" >

            <bean:define id="year3" name="rng160knForm" property="rng160draftYear" type="java.lang.Integer" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(year3.intValue()) %>" />
            <bean:define id="month3" name="rng160knForm" property="rng160draftMonth" type="java.lang.Integer" />
            <gsmsg:write key="cmn.months" arg0="<%= String.valueOf(month3.intValue()) %>" />
            <bean:write name="rng160knForm" property="rng160draftDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </span>
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