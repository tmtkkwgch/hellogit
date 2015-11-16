<%@page import="jp.groupsession.v2.rsv.GSConstReserve"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.preferences2" />[<gsmsg:write key="cmn.sml.notification.setting" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../reserve/js/rsv320.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv320">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv320Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv320Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.sml.notification.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onclick="buttonPush('edit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_kanri_menu');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

<!-- ショートメール通知機能 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap rowspan="2">
      <span class="text_bb1"><gsmsg:write key="shortmail.notification" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="rsv320Form" styleId="smailSendKbn_01" property="rsv320SmailSendKbn" value="<%= String.valueOf(GSConstReserve.RAC_SMAIL_SEND_KBN_ADMIN) %>"/><label for="smailSendKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="rsv320Form" styleId="smailSendKbn_02" property="rsv320SmailSendKbn" value="<%= String.valueOf(GSConstReserve.RAC_SMAIL_SEND_KBN_USER) %>"/><label for="smailSendKbn_02"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></label>&nbsp;
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="smlNoticeKbnArea">
      <html:radio name="rsv320Form" styleId="smailSend_01" property="rsv320SmailSend" value="<%= String.valueOf(GSConstReserve.RAC_SMAIL_SEND_NO) %>" /><label for="smailSend_01"><span class="text_base6_2"><gsmsg:write key="cmn.dont.notify" /></span></label>&nbsp;&nbsp;
      <html:radio name="rsv320Form" styleId="smailSend_02" property="rsv320SmailSend" value="<%= String.valueOf(GSConstReserve.RAC_SMAIL_SEND_YES) %>" /><label for="smailSend_02"><span class="text_base6_2"><gsmsg:write key="cmn.notify" /></span></label>&nbsp;&nbsp;
    </td>
    </tr>


    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onclick="buttonPush('edit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_kanri_menu');">
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