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
<title>[GroupSession] <gsmsg:write key="main.man150kn.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man150kn">
<input type="hidden" name="CMD" value="">
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man150kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('import_ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backToImport');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- <gsmsg:write key="cmn.capture.file" /> -->
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man150kn.2" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt2" width="100%" colspan="1">
      <span class="text_base4"><gsmsg:write key="main.man150kn.3" /></span>
      <br>
      <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="70%">
      <tr>
      <th align="center" class="td_type15" width="100%"><span class="text_tlw">&nbsp;</span></th>
      </tr>
      <tr>
      <td align="left" class="td_type1" width="100%">

        <table width="100%" border="0" style="padding-left:5px;padding-top:15px;padding-right:25px;padding-bottom:15px;">
        <tr>
        <td width="30%" align="right" nowrap>
          <span class="text_bb1"><gsmsg:write key="main.man002.3" /></span>
        </td>
        <td width="70%" style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <logic:empty name="man150knForm" property="man150LicenseId">&nbsp;</logic:empty>
          <logic:notEmpty name="man150knForm" property="man150LicenseId">
            <span class="text_gray"><bean:write name="man150knForm" property="man150LicenseId" /></span>
          </logic:notEmpty>
        </td>
        </tr>
        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        <tr>
        <td align="right">
          <span class="text_bb1"><gsmsg:write key="main.man002.4" /></span>
        </td>
        <td style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <logic:empty name="man150knForm" property="man150LicenseCom">&nbsp;</logic:empty>
          <logic:notEmpty name="man150knForm" property="man150LicenseCom">
            <span class="text_gray"><bean:write name="man150knForm" property="man150LicenseCom" /></span>
          </logic:notEmpty>
        </td>
        </tr>

        <logic:notEmpty name="man150knForm" property="man150PluginList">
        <logic:iterate id="plugin" name="man150knForm" property="man150PluginList" scope="request" indexId="cnt">
        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        <tr>
        <td align="right">
          <span class="text_bb1"><bean:write name="plugin" property="pluginName" />：</span>
        </td>
        <td style="border:1px;border-bottom-style:solid;border-color:#cccccc;"><span class="text_gray"><gsmsg:write key="cmn.period2" />&nbsp;<bean:write name="plugin" property="licenseLimit" /></span></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>

        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        </table>
      </td>
      </tr>
      </table>

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