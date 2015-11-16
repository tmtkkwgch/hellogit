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
<title>[GroupSession] <gsmsg:write key="main.man112.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/main/man112kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="man112updateFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man112kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.run" />" onClick="buttonPush('doImp');">
      <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('Man112kn_Back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
    <tr>
    <td><span class="text_r1"><gsmsg:write key="cmn.capture.file.sure" /></span></td>
    </tr>
    </table>

    <!-- <gsmsg:write key="cmn.capture.file" /> -->
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl2" align="center">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <span class="text_base"><bean:write name="man112knForm" property="man112knFileName" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man112kn.2" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <logic:notEmpty name="man112knForm" property="man112knImpList" scope="request">
        <logic:iterate id="posModel" name="man112knForm" property="man112knImpList" scope="request">
          <span class="text_base"><bean:write name="posModel" property="posName" /></span><br>
        </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.capture.item.count" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <logic:notEmpty name="man112knForm" property="man112knImpList" scope="request">
        <bean:size id="count" name="man112knForm" property="man112knImpList" scope="request" />
        <span class="text_base"><bean:write name="count" /><gsmsg:write key="cmn.number" /></span><br>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <logic:equal name="man112knForm" property="man112updateFlg" value="1">
    <span class="text_base"><gsmsg:write key="main.man112.5" /></span>
    </logic:equal>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.run" />" onClick="buttonPush('doImp');">
      <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('Man112kn_Back');">
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