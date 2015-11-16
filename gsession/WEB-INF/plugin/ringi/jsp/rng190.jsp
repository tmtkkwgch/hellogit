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
<title>[GroupSession] <gsmsg:write key="rng.62" /> <gsmsg:write key="cmn.sml.notification.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../ringi/js/rng190.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/ringi/rng190">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.62" /> <gsmsg:write key="cmn.sml.notification.setting" /> ]</td>
      <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('rng190ok');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng190back');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table width="100%" class="tl0" border="0" cellpadding="5" style="margin-top: 10px">
      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap rowspan="2">
        <span class="text_bb1"><gsmsg:write key="cmn.sml.notification.setting" /></span>
      </td>
      <td valign="middle" align="left" class="td_wt">
          <html:radio name="rng190Form" styleId="smlNtf_01" property="rng190SmlNtf" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_SML_NTF_ADMIN) %>" /><label for="smlNtf_01"><span class="text_base6"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
          <html:radio name="rng190Form" styleId="smlNtf_02" property="rng190SmlNtf" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_SML_NTF_USER) %>" /><label for="smlNtf_02"><span class="text_base6"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      </td>
      </tr>

      <tr>
      <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="smlNoticeKbnArea">
          <html:radio name="rng190Form" styleId="smlNtfKbn_02" property="rng190SmlNtfKbn" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_SML_NTF_KBN_NO) %>" /><label for="smlNtfKbn_02"><span class="text_base6"><gsmsg:write key="cmn.dont.notify" /></span></label>
          <html:radio name="rng190Form" styleId="smlNtfKbn_01" property="rng190SmlNtfKbn" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_SML_NTF_KBN_YES) %>" /><label for="smlNtfKbn_01"><span class="text_base6"><gsmsg:write key="cmn.notify" /></span></label>
      </td>
      </tr>

    </table>

    <table cellpadding="0" cellspacing="0" width="100%" style="margin-top: 10px">
      <tr>
      <td align="right">
        <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('rng190ok');">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng190back');">
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