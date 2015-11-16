<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Content-Script-Type" content="text/javascript">
<META http-equiv="Content-Style-Type" content="text/css">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq940.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03" onload="enq940Init(<bean:write name='enq940Form' property='enq940MainDspKbn' />);">
<html:form action="/enquete/enq940">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq940Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq940Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq940Form" property="enq010status">
<logic:iterate id="svStatus" name="enq940Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq940Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq940Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq940Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq940Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>


<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq900.07"/> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key='cmn.admin.setting' />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key='cmn.setting' />" class="btn_setting_n1" onClick="buttonPush('enq940commit');">
          <input type="button" value="<gsmsg:write key='cmn.back.admin.setting' />" class="btn_back_n3" onClick="buttonPush('enq940back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <thead>
      <!-- メイン表示区分 -->
      <tr>
        <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.enq940.01"/></span>
        </td>
        <td align="left" class="td_wt" width="80%">
          <span class="text_base">
            <html:radio styleId="mainDspKbn0" name="enq940Form" property="enq940MainDspKbn" value="<%= String.valueOf(GSConstEnquete.CONF_MAIN_DSP_USE_LIMIT) %>" onclick="changeMainDspKbn(0);" />
            <label for="mainDspKbn0"><gsmsg:write key="cmn.set.the.admin"/></label>&nbsp;
            <html:radio styleId="mainDspKbn1" name="enq940Form" property="enq940MainDspKbn" value="<%= String.valueOf(GSConstEnquete.CONF_MAIN_DSP_USE_EACH) %>" onclick="changeMainDspKbn(1);" />
            <label for="mainDspKbn1"><gsmsg:write key="cmn.set.eachuser"/></label>&nbsp;
          </span>
        </td>
      </tr>
    </thead>

    <tbody id="mainDspArea">
      <!-- メイン表示フラグ -->
      <tr>
        <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap>
          <span class="text_bb1"><gsmsg:write key="enq.enq940.02"/></span>
        </td>
        <td align="left" class="td_wt" width="80%">
          <span class="text_base">
            <html:radio styleId="display_ok" name="enq940Form" property="enq940MainDsp" value="<%= String.valueOf(GSConstEnquete.CONF_MAIN_DSP_ON) %>" />
            <label for="display_ok"><gsmsg:write key="cmn.display.ok"/></label>&nbsp;
            <html:radio styleId="display_show" name="enq940Form" property="enq940MainDsp" value="<%= String.valueOf(GSConstEnquete.CONF_MAIN_DSP_OFF) %>" />
            <label for="display_show"><gsmsg:write key="cmn.dont.show"/></label>&nbsp;
          </span>
        </td>
      </tr>
    </tbody>
    </table>

    <img src="../common/images/spacer.gif" width="1" height="10" alt="<gsmsg:write key='cmn.spacer' />" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key='cmn.setting' />" class="btn_setting_n1" onClick="buttonPush('enq940commit');">
          <input type="button" value="<gsmsg:write key='cmn.back.admin.setting' />" class="btn_back_n3" onClick="buttonPush('enq940back');">
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