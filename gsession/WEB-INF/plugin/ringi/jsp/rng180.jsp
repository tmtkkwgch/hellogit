<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="rng.62" />　<gsmsg:write key="cmn.preferences" /></title>
</head>

<body class="body_03" onload="lmtEnableDisable();">
<html:form action="/ringi/rng180">

<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rng180initFlg" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.62" />　<gsmsg:write key="cmn.preferences" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backMenu');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5" style="margin-top: 10px;">

    <tr>
    <td>
      <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap>
        <span class="text_bb1"><gsmsg:write key="cmn.delete" /></span>
      </td>
      <!-- 削除 -->
      <td valign="middle" align="left" class="td_wt">
        <html:radio name="rng180Form" styleId="rng180delKbn_01" property="rng180delKbn" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_DEL_AUTH_ADM) %>" /><label for="rng180delKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.delete.only.admin" /></span></label>&nbsp;
        <html:radio name="rng180Form" styleId="rng180delKbn_02" property="rng180delKbn" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RAR_DEL_AUTH_UNRESTRICTED) %>" /><label for="rng180delKbn_02"><span class="text_base6_2"><gsmsg:write key="man.no.limit" /></span></label>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    </table>

    <table cellpadding="0" cellpadding="5" border="0" width="100%" style="margin-top: 10px;">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backMenu');">
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