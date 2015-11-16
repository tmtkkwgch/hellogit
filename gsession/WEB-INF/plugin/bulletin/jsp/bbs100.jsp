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
<title>[GroupSession] <gsmsg:write key="bbs.bbs100.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/bulletin/bbs100">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs100Form" property="bbs010forumSid" />
<html:hidden name="bbs100Form" property="s_key" />
<html:hidden name="bbs100Form" property="bbs010page1" />
<html:hidden name="bbs100Form" property="bbs100orderKey" />
<html:hidden name="bbs100Form" property="bbs100sortKey" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>

<td width="100%" align="center">

  <table width="100%" cellpadding="0" border="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../bulletin/images/header_bulletin_01.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.bulletin" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.bbs100.1" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backForumList');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl5">

    <logic:notEmpty name="bbs100Form" property="bbs100users">
    <bean:size id="count1" name="bbs100Form" property="bbs100PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <tr>
    <td align="right" width="0%">
      <table width="0%" cellpadding="0" cellspacing="0" border="0">
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">&nbsp;</td>
      <td align="right">
        <html:select property="bbs100pageNum1" onchange="changePage(1);" styleClass="text_i">
          <span class="text_base"><html:optionsCollection name="bbs100Form" property="bbs100PageLabel" value="value" label="label" /></span>
        </html:select>
      </td>
      <td align="right">
        &nbsp;<img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:greaterThan>
    </logic:notEmpty>

    </table>


    <table class="tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">
    <tr class="table_bg_7D91BD">
    <!-- 社員/職員番号 -->
    <th width="0%" nowrap>
      <logic:equal name="bbs100Form" property="bbs100sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
        <logic:equal name="bbs100Form" property="bbs100orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
          <a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a></th>
        </logic:equal>
        <logic:equal name="bbs100Form" property="bbs100orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
          <a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="bbs100Form" property="bbs100sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
          <a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span></a></th>
      </logic:notEqual>
    </th>

    <!-- 氏名 -->
    <th width="20%" nowrap>
      <logic:equal name="bbs100Form" property="bbs100sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
        <logic:equal name="bbs100Form" property="bbs100orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a></th>
        </logic:equal>
        <logic:equal name="bbs100Form" property="bbs100orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="bbs100Form" property="bbs100sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.name" /></span></a></th>
      </logic:notEqual>
    </th>

    <!-- 役職 -->
    <th width="0%" nowrap>
      <logic:equal name="bbs100Form" property="bbs100sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
        <logic:equal name="bbs100Form" property="bbs100orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.post" />▲</span></a></th>
        </logic:equal>
        <logic:equal name="bbs100Form" property="bbs100orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.post" />▼</span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="bbs100Form" property="bbs100sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.post" /></span></a></th>
      </logic:notEqual>
    </th>

    <!-- E-MAIL -->
    <th width="50%" nowrap>
      <span class="text_tlw">E-MAIL</span></th>
    </tr>

    <logic:notEmpty name="bbs100Form" property="bbs100users">
    <logic:iterate id="user" name="bbs100Form" property="bbs100users" scope="request" indexId="idx">
      <bean:define id="mod" value="0" />
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type25_2" />
      </logic:notEqual>


    <tr class="<bean:write name="tblColor" />">
    <!-- 社員/職員番号 -->
    <td nowrap><bean:write name="user" property="usiSyainNo" /></td>
    <!-- 氏名 -->
    <td nowrap><a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="user" property="usrSid" />);"><span class="normal_link"><bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" /></span></a></td>
    <!-- 役職 -->
    <td nowrap><bean:write name="user" property="usiYakusyoku" /></td>
    <!-- E-MAIL -->
    <td>
      <logic:notEmpty name="user" property="usiMail1"><a href="mailto:<bean:write name="user" property="usiMail1" />"><span class="normal_link"><bean:write name="user" property="usiMail1" /></span></a></logic:notEmpty>
      <logic:notEmpty name="user" property="usiMail2"><a href="mailto:<bean:write name="user" property="usiMail2" />"><span class="normal_link"><bean:write name="user" property="usiMail2" /></span></a></logic:notEmpty>
      <logic:notEmpty name="user" property="usiMail3"><a href="mailto:<bean:write name="user" property="usiMail3" />"><span class="normal_link"><bean:write name="user" property="usiMail3" /></span></a></logic:notEmpty>
    </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    </table>

    <logic:notEmpty name="bbs100Form" property="bbs100PageLabel">
       <bean:size id="count2" name="bbs100Form" property="bbs100PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" class="tl5">
    <tr>
    <td width="100%">&nbsp;</td>
    <td align="right">
      <table width="0%" cellpadding="0" cellspacing="0" border="0">
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">&nbsp;
      </td>
      <td align="right">
        <html:select property="bbs100pageNum2" onchange="changePage(2);" styleClass="text_i">
          <span class="text_base"><html:optionsCollection name="bbs100Form" property="bbs100PageLabel" value="value" label="label" /></span>
        </html:select>
      </td>
      <td align="right">
        &nbsp;<img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

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