<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- 手動削除区分 --%>
<%
  String manuDelNo        = String.valueOf(jp.groupsession.v2.rng.RngConst.MANU_DEL_NO);
  String manuDelOk        = String.valueOf(jp.groupsession.v2.rng.RngConst.MANU_DEL_OK);
%>

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

<html:form action="/ringi/rng170kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden name="rng170knForm" property="rng170pendingKbn" />
<html:hidden name="rng170knForm" property="rng170pendingYear" />
<html:hidden name="rng170knForm" property="rng170pendingMonth" />
<html:hidden name="rng170knForm" property="rng170pendingDay" />
<html:hidden name="rng170knForm" property="rng170completeKbn" />
<html:hidden name="rng170knForm" property="rng170completeYear" />
<html:hidden name="rng170knForm" property="rng170completeMonth" />
<html:hidden name="rng170knForm" property="rng170completeDay" />
<html:hidden name="rng170knForm" property="rng170draftKbn" />
<html:hidden name="rng170knForm" property="rng170draftYear" />
<html:hidden name="rng170knForm" property="rng170draftMonth" />
<html:hidden name="rng170knForm" property="rng170draftDay" />


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

    <table width="100%" class="tl0" border="0" cellpadding="5" style="margin-top: 10px">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="rng.48" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
          <logic:equal name="rng170knForm" property="rng170pendingKbn" value="<%= manuDelNo %>" ><gsmsg:write key="cmn.dont.delete" /></logic:equal>
          <logic:equal name="rng170knForm" property="rng170pendingKbn" value="<%= manuDelOk %>" >

            <bean:define id="year1" name="rng170knForm" property="rng170pendingYear" type="java.lang.Integer" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(year1.intValue()) %>" />
            <bean:define id="month1" name="rng170knForm" property="rng170pendingMonth" type="java.lang.Integer" />
            <gsmsg:write key="cmn.months" arg0="<%= String.valueOf(month1.intValue()) %>" />
            <bean:write name="rng170knForm" property="rng170pendingDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.complete" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
          <logic:equal name="rng170knForm" property="rng170completeKbn" value="<%= manuDelNo %>" ><gsmsg:write key="cmn.dont.delete" /></logic:equal>
          <logic:equal name="rng170knForm" property="rng170completeKbn" value="<%= manuDelOk %>" >

            <bean:define id="year2" name="rng170knForm" property="rng170completeYear" type="java.lang.Integer" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(year2.intValue()) %>" />
            <bean:define id="month2" name="rng170knForm" property="rng170completeMonth" type="java.lang.Integer" />
            <gsmsg:write key="cmn.months" arg0="<%= String.valueOf(month2.intValue()) %>" />
            <bean:write name="rng170knForm" property="rng170completeDay" /><gsmsg:write key="cmn.day" />
            <gsmsg:write key="wml.73" />
          </logic:equal>
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.draft" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
          <logic:equal name="rng170knForm" property="rng170draftKbn" value="<%= manuDelNo %>" ><gsmsg:write key="cmn.dont.delete" /></logic:equal>
          <logic:equal name="rng170knForm" property="rng170draftKbn" value="<%= manuDelOk %>" >

            <bean:define id="year3" name="rng170knForm" property="rng170draftYear" type="java.lang.Integer" />
            &nbsp;<gsmsg:write key="cmn.year" arg0="<%= String.valueOf(year3.intValue()) %>" />
            <bean:define id="month3" name="rng170knForm" property="rng170draftMonth" type="java.lang.Integer" />
            <gsmsg:write key="cmn.months" arg0="<%= String.valueOf(month3.intValue()) %>" />
            <bean:write name="rng170knForm" property="rng170draftDay" /><gsmsg:write key="cmn.day" />
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