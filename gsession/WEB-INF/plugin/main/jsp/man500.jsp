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
<title>[GroupSession] <gsmsg:write key="man.personal.edit" />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../schedule/js/sch086.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man500">

<input type="hidden" name="CMD" value="">
<html:hidden property="man500init" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[<gsmsg:write key="main.man500.2" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('man500Ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('man500Back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man500.3" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <html:radio name="man500Form" styleId="manEditKbn_01" property="man500EditKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PCONF_EDIT_ADM) %>" /><label for="manEditKbn_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="man500Form" styleId="manEditKbn_02" property="man500EditKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PCONF_EDIT_USER) %>" /><label for="manEditKbn_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
    </td>
    </tr>

    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man500.4" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
    <logic:equal name="man500Form" property="manPasswordLimitFlg" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_ON) %>">
      <html:hidden property="manPasswordKbn" />
      <html:radio name="man500Form" styleId="manPasswordKbn_01" property="man500PasswordKbn" disabled="true" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_ADM) %>" /><label for="manPasswordKbn_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="man500Form" styleId="manPasswordKbn_02" property="man500PasswordKbn" disabled="true" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_USER) %>" /><label for="manPasswordKbn_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      <span class="text_r1"><br><gsmsg:write key="cmn.comments" /><gsmsg:write key="main.man500.6"/></span>
    </logic:equal>
    <logic:notEqual name="man500Form" property="manPasswordLimitFlg" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_ON) %>">
      <html:radio name="man500Form" styleId="manPasswordKbn_01" property="man500PasswordKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_ADM) %>" /><label for="manPasswordKbn_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="man500Form" styleId="manPasswordKbn_02" property="man500PasswordKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_USER) %>" /><label for="manPasswordKbn_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
    </logic:notEqual>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td>
      <table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top:10px;">
      <tr>
      <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('man500Ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('man500Back');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  </table>
</table>

  <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
  </html:form>
  </body>
  </html:html>