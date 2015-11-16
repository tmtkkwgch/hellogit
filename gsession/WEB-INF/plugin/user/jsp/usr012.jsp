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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr012.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="user.usr012.1" /></title>
</head>

<body class="body_03">
<html:form action="/user/usr012">
<input type="hidden" name="usr010grpSid" value="<bean:write name='usr012Form' property='usr010grpSid'/>">
<input type="hidden" name="CMD" value="">
<html:hidden property="usr012SortKey" />
<html:hidden property="usr012OrderKey" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="user.usr012.2" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="user.22" />" onClick="buttonPush('back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="tl0 table_td_border2">
    <tr class="table_bg_7D91BD">

    <!-- ログインID -->
    <logic:equal name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_ID) %>">
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_ID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw">ID▲</span></a></th>
      </logic:equal>
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_ID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw">ID▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_ID) %>">
      <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_ID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw">ID</span></a></th>
    </logic:notEqual>

    <!-- 名前 -->
    <logic:equal name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="50%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="50%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
      <th width="50%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.name4" /></span></a></th>
    </logic:notEqual>

    <!-- 社員・職員番号 -->
    <logic:equal name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>">
      <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_SNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span></a></th>
    </logic:notEqual>

    <!-- 役職 -->
    <logic:equal name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="50%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.post" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="usr012Form" property="usr012OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="50%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.post" />▼</span></a></th>
      </logic:equal>
    </logic:equal>
    <logic:notEqual name="usr012Form" property="usr012SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>">
      <th width="50%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_YKSK) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.post" /></span></a></th>
    </logic:notEqual>
    </tr>

    <bean:define id="mod" value="0" />
    <logic:iterate id="group" name="usr012Form" property="usr012ModelList" indexId="idx" scope="request">
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type_usr" />
      </logic:notEqual>
      <tr>
        <td class="<bean:write name="tblColor" />" nowrap>
        <bean:write name="group" property="usrlgid" />
        </td>
        <td class="<bean:write name="tblColor" />" nowrap>
        <bean:write name="group" property="namesei" />
        <bean:write name="group" property="namemei" />
        </td>
        <td class="<bean:write name="tblColor" />" nowrap>
        <bean:write name="group" property="syainno" />
        </td>
        <td class="<bean:write name="tblColor" />" nowrap>
        <bean:write name="group" property="yakusyoku" />
        </td>
      </tr>
    </logic:iterate>

  </table>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <table width="100%">
  <tr>
  <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_back_n3" value="<gsmsg:write key="user.22" />" onClick="buttonPush('back');">
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