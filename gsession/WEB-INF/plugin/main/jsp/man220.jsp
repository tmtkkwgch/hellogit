<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="main.grp.usr.sort.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/main/man220">

<input type="hidden" name="CMD" value="">
<html:hidden property="man220initFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.grp.usr.sort.setting" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backKtool');">
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

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man220.2" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <span class="text_r1"><gsmsg:write key="main.man220.3" /></span><br>
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" />：</span>

      <html:select property="man220UserSortKey1">
        <html:optionsCollection name="man220Form" property="userSortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="man220Form" property="man220UserSortOrder1" styleId="userOrder10" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="userOrder10"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="man220Form" property="man220UserSortOrder1" styleId="userOrder11" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="userOrder11"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" />：</span>
      <html:select property="man220UserSortKey2">
        <option value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USERCMB_SKEY_NOSET) %>">　</option>
        <html:optionsCollection name="man220Form" property="userSortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="man220Form" property="man220UserSortOrder2" styleId="userOrder20" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="userOrder20"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="man220Form" property="man220UserSortOrder2" styleId="userOrder21" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="userOrder21"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>

    </td>
    </tr>

    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="main.man220.5" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <span class="text_r1"><gsmsg:write key="main.man220.6" /></span><br>
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" />：</span>

      <html:select property="man220GroupSortKey1">
        <html:optionsCollection name="man220Form" property="groupSortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="man220Form" property="man220GroupSortOrder1" styleId="groupOrder10" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="groupOrder10"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="man220Form" property="man220GroupSortOrder1" styleId="groupOrder11" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="groupOrder11"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" />：</span>
      <html:select property="man220GroupSortKey2">
        <option value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.GROUPCMB_SKEY_NOSET) %>">　</option>
        <html:optionsCollection name="man220Form" property="groupSortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="man220Form" property="man220GroupSortOrder2" styleId="groupOrder20" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="groupOrder20"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="man220Form" property="man220GroupSortOrder2" styleId="groupOrder21" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="groupOrder21"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>


      <br><br>
      <span class="text_base"><html:checkbox styleId="groupSortKbn" name="man220Form" property="man220GroupSortKbn" value="<%= String.valueOf(jp.groupsession.v2.man.man220.Man220Form.GRPSORTKBN_SELECT) %>" /><label for="groupSortKbn"><gsmsg:write key="man.reorder.hierarchy.group" /></label>

    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backKtool');"></td>
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