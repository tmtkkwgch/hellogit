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
<title>[GroupSession] <gsmsg:write key="bbs.bbs140.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs140.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs140">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs140Form" property="bbs010forumSid" />
<html:hidden name="bbs140Form" property="threadSid" />
<html:hidden name="bbs140Form" property="bbs010page1" />
<html:hidden name="bbs140Form" property="bbs140orderKey" />
<html:hidden name="bbs140Form" property="bbs140sortKey" />

<!-- BODY -->
<table cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>

<td width="100%" align="center">

  <table width="100%" cellpadding="0" border="0" cellspacing="0">
  <tr>
  <td align="left">

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl5">

    <logic:notEmpty name="bbs140Form" property="bbs140users">
    <bean:size id="count1" name="bbs140Form" property="bbs140PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <tr>
    <td align="right" width="0%">
      <table width="0%" cellpadding="0" cellspacing="0" border="0">
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">&nbsp;</td>
      <td align="right">
        <html:select property="bbs140pageNum1" onchange="changePage(1);" styleClass="text_i">
          <span class="text_base"><html:optionsCollection name="bbs140Form" property="bbs140PageLabel" value="value" label="label" /></span>
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

    <!-- 氏名 -->
    <th width="40%" nowrap>
      <logic:equal name="bbs140Form" property="bbs140sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
        <logic:equal name="bbs140Form" property="bbs140orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.name" />▲</span></a></th>
        </logic:equal>
        <logic:equal name="bbs140Form" property="bbs140orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
          <span class="text_tlw"><gsmsg:write key="cmn.name" />▼</span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="bbs140Form" property="bbs140sortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_SORT_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="cmn.name" /></span></a></th>
      </logic:notEqual>
    </th>

    <!-- 閲覧状況 -->
    <th width="60%" nowrap>
      <logic:equal name="bbs140Form" property="bbs140sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_WATCH) %>">
        <logic:equal name="bbs140Form" property="bbs140orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_WATCH) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>');">
          <span class="text_tlw"><gsmsg:write key="bbs.bbsMain.1" />▲</span></a></th>
        </logic:equal>
        <logic:equal name="bbs140Form" property="bbs140orderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
          <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_WATCH) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
          <span class="text_tlw"><gsmsg:write key="bbs.bbsMain.1" />▼</span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="bbs140Form" property="bbs140sortKey" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_WATCH) %>">
        <a  href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.SORT_KEY_WATCH) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>');">
        <span class="text_tlw"><gsmsg:write key="bbs.bbsMain.1" /></span></a></th>
      </logic:notEqual>
    </th>

    <logic:notEmpty name="bbs140Form" property="bbs140users">
    <logic:iterate id="user" name="bbs140Form" property="bbs140users" scope="request" indexId="idx">
      <bean:define id="mod" value="0" />
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type_bbs_blue" />
      </logic:notEqual>

    <tr class="<bean:write name="tblColor" />">
    <!-- 氏名 -->
    <td nowrap><span class="normal_link"><bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" /></span></td>
    <!-- 閲覧状況 -->
    <logic:equal name="user" property="userJkbn" value="0">
      <td nowrap><span class="text_blue"><gsmsg:write key="cmn.read.yet" /></span></td>
    </logic:equal>
    <logic:equal name="user" property="userJkbn" value="1">
      <td nowrap><gsmsg:write key="cmn.read.already" /></td>
    </logic:equal>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    </table>

    <logic:notEmpty name="bbs140Form" property="bbs140PageLabel">
    <bean:size id="count2" name="bbs140Form" property="bbs140PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" class="tl5">
    <tr>
    <td width="100%">&nbsp;</td>
    <td align="right">
      <table width="0%" cellpadding="0" cellspacing="0" border="0">
      <td align="right">
        <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">&nbsp;
      </td>
      <td align="right">
        <html:select property="bbs140pageNum2" onchange="changePage(2);" styleClass="text_i">
          <span class="text_base"><html:optionsCollection name="bbs140Form" property="bbs140PageLabel" value="value" label="label" /></span>
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

  <tr>
  <td align="center">
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td align="center">
    <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1" onClick="window.close();">
  </td>
  </tr>

  </table>

</td>
</tr>
</table>

</html:form>

</body>
</html:html>