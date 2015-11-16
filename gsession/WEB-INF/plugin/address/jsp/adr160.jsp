<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
  int add = jp.groupsession.v2.adr.GSConstAddress.PROCMODE_ADD;
  int edit = jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT;
%>

<% String markOther    = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_OTHER); %>
<% String markTel      = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_TEL); %>
<% String markMail     = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_MAIL); %>
<% String markWeb      = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_WEB);  %>
<% String markMeeting  = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_MEETING);   %>

<%-- <gsmsg:write key="adr.mark.image" /> --%>
<%
  java.util.HashMap imgMap = new java.util.HashMap();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
  String msgTel = gsMsg.getMessage(request, "cmn.phone");
  String msgMail = gsMsg.getMessage(request, "cmn.mail");
  String msgMeeting = gsMsg.getMessage(request, "address.28");
  String msgOther = gsMsg.getMessage(request, "cmn.other");

  imgMap.put(markTel, "<img src=\"../address/images/call.gif\" alt=" + "\"" + msgTel + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put(markMail, "<img src=\"../address/images/mail.gif\" alt=" + "\"" + msgMail + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put(markWeb, "<img src=\"../address/images/web.gif\" alt=\"Web\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markMeeting, "<img src=\"../address/images/uchiawase.gif\" alt=" + "\"" + msgMeeting + "\"" + " border=\"0\" class=\"img_bottom\">");

  imgMap.put("none", "&nbsp;");
%>

<%
  java.util.HashMap imgTextMap = new java.util.HashMap();
  imgTextMap.put(markTel, msgTel);
  imgTextMap.put(markMail, msgMail);
  imgTextMap.put(markWeb, "Web");
  imgTextMap.put(markMeeting, msgMeeting);
  imgTextMap.put(markOther, msgOther);

  imgTextMap.put("none", "&nbsp;");
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="address.adr160.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr160.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03">
<html:form action="/address/adr160">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">
<input type="hidden" name="adr160EditSid">
<input type="hidden" name="adr160ProcMode">

<html:hidden name="adr160Form" property="adr010EditAdrSid" />
<html:hidden name="adr160Form" property="adr020ProcMode" />
<html:hidden name="adr160Form" property="adr020BackId" />
<html:hidden name="adr160Form" property="sortKey" />
<html:hidden name="adr160Form" property="orderKey" />

<logic:notEmpty name="adr160Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr160Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr160Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr160Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr160Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr160Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>


<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
  <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr160.1" /> ]</td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
  </tr>
  </table>

  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td align="right">
  <%-- <gsmsg:write key="cmn.add" /> --%>
  <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('adr160add', '<%= add %>', '-1');">
  <%-- <gsmsg:write key="cmn.export" /> --%>
  <logic:notEqual name="adr160Form" property="adr160dataExist" value="0" >
    <logic:notEqual name="adr160Form" property="adr160exportPower" value="0" >
      <input type="button" name="btn_csv" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('csv');">
    </logic:notEqual>
  </logic:notEqual>
  <%-- <gsmsg:write key="cmn.back" /> --%>
  <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('adr160back');">
  </td>
  </tr>
  </table>

  <table width="100%" cellpadding="3" cellspacing="0">
  <tr>

  <%-- <gsmsg:write key="cmn.company.name" />＋<gsmsg:write key="cmn.name" /> --%>
  <th width="55%" align="left">
  <span class="text_base1">
  <bean:write name="adr160Form" property="adr160kaisya" />
  &nbsp;

  <a href="#" onClick="return editAddress('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>', '<bean:write name="adr160Form" property="adr010EditAdrSid" />')">
    <span class="text_link"><bean:write name="adr160Form" property="adr160simei" /></span>
  </a>

  </span>
  </th>

  <%-- <gsmsg:write key="cmn.pageing" /> --%>
  <td width="15%" valign="bottom">
    <table width="100%" cellpadding="0" cellspacing="0">
    <td colspan="2" width="100%" align="right" valign="top" class="table_padding" nowrap>
    <bean:size id="count1" name="adr160Form" property="adr160PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
    <html:select property="adr160pageNum1" styleClass="text_i" onchange="changePage(1);">
    <html:optionsCollection name="adr160Form" property="adr160PageLabel" value="value" label="label" />
    </html:select>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('next');">
    </logic:greaterThan>
    </td>
    </table>
  </td>

  <td width="30%">&nbsp;</td>
  </tr>

  <tr valign="top">
  <td width="70%" colspan="2" align="center">

    <%-- <gsmsg:write key="cmn.heading" /> --%>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

    <logic:equal name="adr160Form" property="adr160dataExist" value="0" >
    <tr>
    <td><span class="text_r2"><gsmsg:write key="address.adr160.2" /></span></td>
    </tr>
    </logic:equal>

    <logic:notEqual name="adr160Form" property="adr160dataExist" value="0" >
    <tr>

    <%-- <gsmsg:write key="address.114" /> --%>
    <th align="center" class="table_bg_7D91BD" width="19%">
    <logic:equal name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_DATE) %>">
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_DATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.date" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_DATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.date" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_DATE) %>">
      <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_DATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
      <span class="text_tlw"><gsmsg:write key="cmn.date" /></span></a></th>
    </logic:notEqual>

    <%-- <gsmsg:write key="cmn.type" /> --%>
    <th align="center" class="table_bg_7D91BD" width="14%">
    <logic:equal name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_SYUBETU) %>">
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_SYUBETU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.type" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_SYUBETU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.type" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_SYUBETU) %>">
      <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_SYUBETU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
      <span class="text_tlw"><gsmsg:write key="cmn.type" /></span></a></th>
    </logic:notEqual>

    <%-- <gsmsg:write key="cmn.title" /> --%>
    <th align="center" class="table_bg_7D91BD" width="45%">
    <logic:equal name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_TITLE) %>">
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.title" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.title" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_TITLE) %>">
      <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
      <span class="text_tlw"><gsmsg:write key="cmn.title" /></span></a></th>
    </logic:notEqual>

    <%-- <gsmsg:write key="cmn.registant" /> --%>
    <th align="center" class="table_bg_7D91BD" width="13%">
    <logic:equal name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_ADDUSER) %>">
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_ADDUSER) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.registant" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="adr160Form" property="orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_ADDUSER) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.registant" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="adr160Form" property="sortKey" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_ADDUSER) %>">
      <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.CONTACT_SORT_ADDUSER) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
      <span class="text_tlw"><gsmsg:write key="cmn.registant" /></span></a></th>
    </logic:notEqual>
    </tr>
    </logic:notEqual>

    <%-- <gsmsg:write key="address.6" /> --%>
    <logic:notEmpty name="adr160Form" property="adr160contactList">
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="conMdl" name="adr160Form" property="adr160contactList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr>

    <%-- <gsmsg:write key="cmn.date" /> --%>
    <td align="center" class="<%= tdColor %>">
    <bean:write name="conMdl" property="adcCttimeDsp" />
<%--
    <br>
    <bean:write name="conMdl" property="adcCttimeToDsp" />
--%>
    </td>

    <%-- <gsmsg:write key="cmn.type" /> --%>
    <td align="center" class="<%= tdColor %>">
    <bean:define id="imgMark"><bean:write name="conMdl" property="adcType" /></bean:define>
    <%-- <gsmsg:write key="cmn.mark" /> --%><% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %>
    <%-- <gsmsg:write key="cmn.text" /> --%><% java.lang.String txtkey = "none";  if (imgTextMap.containsKey(imgMark)) { txtkey = imgMark; } %> <%= (java.lang.String) imgTextMap.get(txtkey) %>
    </td>

    <%-- <gsmsg:write key="cmn.title" /> --%>
    <td align="left" class="<%= tdColor %>">
    <a href="#" onclick="return buttonSubmit('adr161', '<%= edit %>', '<bean:write name="conMdl" property="adcSid" />');">
      <span class="text_link"><bean:write name="conMdl" property="adcTitle" /></span></a>
    </td>

    <%-- <gsmsg:write key="cmn.registant" /> --%>
    <td align="left" class="<%= tdColor %>">
    <bean:write name="conMdl" property="adcAdduserDspSei" />
    <bean:write name="conMdl" property="adcAdduserDspMei" />
    </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>
    </table>

    <%-- <gsmsg:write key="cmn.pageing" /> --%>
    <table width="100%" cellpadding="5" cellspacing="0">
    <td colspan="2" width="100%" align="right" valign="top" class="table_padding" nowrap>
    <bean:size id="count1" name="adr160Form" property="adr160PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
    <html:select property="adr160pageNum2" onchange="changePage(2);" styleClass="text_i">
    <html:optionsCollection name="adr160Form" property="adr160PageLabel" value="value" label="label" />
    </html:select>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('next');">
    </logic:greaterThan>
    </td>
    </table>

  </td>
  <td width="30%" align="center">

    <%-- <gsmsg:write key="address.src.2" /> --%>
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td width="17%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.name" /></span>
    </td>
    <td width="83%" valign="middle" align="left" class="td_wt">
      <a href="#" onClick="return editAddress('<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>', '<bean:write name="adr160Form" property="adr010EditAdrSid" />')">
        <span class="text_base"><span class="text_link"><bean:write name="adr160Form" property="adr160simei" /></span></span>
      </a>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="user.119" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160simeikana" />
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="address.139" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160kaisya" /><br>
    <bean:write name="adr160Form" property="adr160kaisyakyoten" />
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.affiliation" /></font> </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160syozoku" />
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.post" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160yakusyoku" />
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.mailaddress1" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
      <a href="mailto:<bean:write name="adr160Form" property="adr160MailAddress1" />"><bean:write name="adr160Form" property="adr160MailAddress1" /></a>
    <logic:notEmpty name="adr160Form" property="adr160MailComment1"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160MailComment1" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.mailaddress2" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
      <a href="mailto:<bean:write name="adr160Form" property="adr160MailAddress2" />"><bean:write name="adr160Form" property="adr160MailAddress2" /></a>
    <logic:notEmpty name="adr160Form" property="adr160MailComment2"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160MailComment2" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.mailaddress3" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
      <a href="mailto:<bean:write name="adr160Form" property="adr160MailAddress3" />"><bean:write name="adr160Form" property="adr160MailAddress3" /></a>
    <logic:notEmpty name="adr160Form" property="adr160MailComment3"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160MailComment3" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.tel1" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160Tel1" />
    <logic:notEmpty name="adr160Form" property="adr160TelNaisen1"><br>
    <gsmsg:write key="address.58" />：<bean:write name="adr160Form" property="adr160TelNaisen1" />
    </logic:notEmpty>
    <logic:notEmpty name="adr160Form" property="adr160TelComment1"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160TelComment1" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.tel2" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160Tel2" />
    <logic:notEmpty name="adr160Form" property="adr160TelNaisen2"><br>
    <gsmsg:write key="address.58" />：<bean:write name="adr160Form" property="adr160TelNaisen2" />
    </logic:notEmpty>
    <logic:notEmpty name="adr160Form" property="adr160TelComment2"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160TelComment2" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.tel3" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160Tel3" />
    <logic:notEmpty name="adr160Form" property="adr160TelNaisen3"><br>
    <gsmsg:write key="address.58" />：<bean:write name="adr160Form" property="adr160TelNaisen3" />
    </logic:notEmpty>
    <logic:notEmpty name="adr160Form" property="adr160TelComment3"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160TelComment3" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <!-- FAX1 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1">ＦＡＸ１</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160Fax1" />
    <logic:notEmpty name="adr160Form" property="adr160FaxComment1"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160FaxComment1" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <!-- FAX2 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1">ＦＡＸ２</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160Fax2" />
    <logic:notEmpty name="adr160Form" property="adr160FaxComment2"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160FaxComment2" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <!-- FAX3 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1">ＦＡＸ３</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
    <bean:write name="adr160Form" property="adr160Fax3" />
    <logic:notEmpty name="adr160Form" property="adr160FaxComment3"><br>
    <gsmsg:write key="cmn.comment" />：<bean:write name="adr160Form" property="adr160FaxComment3" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.address" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base">
        <logic:notEmpty name="adr160Form" property="adr160PostNo">
        〒<bean:write name="adr160Form" property="adr160PostNo" />
        <logic:notEmpty name="adr160Form" property="adr160TdfName"><br></logic:notEmpty>
        <logic:empty name="adr160Form" property="adr160TdfName"><logic:notEmpty name="adr160Form" property="adr160Address1"><br></logic:notEmpty></logic:empty>
        <logic:empty name="adr160Form" property="adr160TdfName"><logic:empty name="adr160Form" property="adr160Address1"><logic:notEmpty name="adr160Form" property="adr160Address2"><br></logic:notEmpty></logic:empty></logic:empty>
        </logic:notEmpty>

        <logic:notEmpty name="adr160Form" property="adr160TdfName"><bean:write name="adr160Form" property="adr160TdfName" />&nbsp;</logic:notEmpty>
        <logic:notEmpty name="adr160Form" property="adr160Address1"><bean:write name="adr160Form" property="adr160Address1" />&nbsp;</logic:notEmpty>
        <logic:notEmpty name="adr160Form" property="adr160Address2"><bean:write name="adr160Form" property="adr160Address2" />&nbsp;</logic:notEmpty>
      </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base">
        <bean:write name="adr160Form" property="adr160Biko" filter="false" />
      </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.staff" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base">
      <logic:notEmpty name="adr160Form" property="adr160TantoUserName">
        <logic:iterate id="tanto" name="adr160Form" property="adr160TantoUserName" indexId="idx">
          <bean:write name="tanto" property="usisei" />&nbsp;<bean:write name="tanto" property="usimei" /><br>
        </logic:iterate>
      </logic:notEmpty>
      </span>
    </td>
    </tr>
    </table>

    <%-- <gsmsg:write key="cmn.label" /> --%>
    <logic:notEqual name="adr160Form" property="adr160labelExist" value="0" >
    <table cellpadding="0" width="100%" class="tl0 spacer">
    <tr>
    <td valign="middle" align="center" class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.label" /></span>
    </td>
    </tr>

    <logic:notEmpty name="adr160Form" property="adr160labelList">
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_label1", "td_label2"}; %>
    <logic:iterate id="labelMdl" name="adr160Form" property="adr160labelList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr align="center" class="<%= tdColor %>">
    <td align="left">
    <bean:write name="labelMdl" property="albName" />
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>
    </logic:notEqual>

  </td>
  </tr>
  </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>