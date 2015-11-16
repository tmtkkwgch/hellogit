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
<title>[GroupSession] <gsmsg:write key="man.restricteduse.plugin.kn" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../main/js/man280kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="initPluginUseConfirm();">

<html:form action="/main/man280kn">

<input type="hidden" name="CMD" value="">
<html:hidden name="man280knForm" property="man120pluginId" />

<html:hidden name="man280knForm" property="man280initFlg" />
<html:hidden name="man280knForm" property="man280useKbn" />
<html:hidden name="man280knForm" property="man280limitType" />
<html:hidden name="man280Form" property="man280backId" />

<logic:notEmpty name="man280knForm" property="man280memberSid">
<logic:iterate id="usid" name="man280knForm" property="man280memberSid">
  <input type="hidden" name="man280memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="man280knForm" property="man280AdminSid">
<logic:iterate id="usid" name="man280knForm" property="man280AdminSid">
  <input type="hidden" name="man280AdminSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man280kn.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    </logic:messagesPresent>

    <h1>
    <logic:equal name="man280knForm" property="man280pluginKbn" value="0">
      <img width="25px" src="../<bean:write name="man280knForm" property="man120pluginId" />/images/menu_icon_single.gif" id="img<bean:write name="man280knForm" property="man120pluginId" />" alt="" class="img_bottom img_border" onerror="defaultImg('img<bean:write name="man280knForm" property="man120pluginId" />')">
    </logic:equal>
    <logic:notEqual name="man280knForm" property="man280pluginKbn" value="0">
      <logic:equal name="man280knForm" property="man280BinSid" value="0">
        <img width="25" height="25" src="../common/images/plugin_default.gif" name="pitctImage" alt="<gsmsg:write key="cmn.icon" />" class="img_bottom img_border">
      </logic:equal>
      <logic:notEqual name="man280knForm" property="man280BinSid" value="0">
        <img width="25" height="25" src="../main/man280.do?CMD=getImageFile&man120imgPluginId=<bean:write name="man280knForm" property="man120pluginId" />" alt="<gsmsg:write key="cmn.icon" />" class="img_bottom img_border">
      </logic:notEqual>
    </logic:notEqual>
    &nbsp;<span valign="middle"><bean:write name="man280knForm" property="man280pluginName" /></span>
    </h1>

    <logic:notEqual name="man280Form" property="man120pluginId" value="main">
    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man280.2" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
    <td align="left" class="td_type20" width="80%">

    <% boolean admGroupFlg = false; %>
    <logic:notEmpty name="man280knForm" property="man280knAdmGroupNameList">
      <% admGroupFlg = true; %>
      <gsmsg:write key="cmn.group" />:
      <logic:iterate id="adminData" name="man280knForm" property="man280knAdmGroupNameList">
        <br>　　<bean:write name="adminData" property="label" />
      </logic:iterate>
    </logic:notEmpty>

    <logic:notEmpty name="man280knForm" property="man280knAdmUserNameList">
      <% if (admGroupFlg) { %><br><br><% } %>
      <gsmsg:write key="cmn.user" />:
      <logic:iterate id="adminData" name="man280knForm" property="man280knAdmUserNameList">
        <br>　　<bean:write name="adminData" property="label" />
      </logic:iterate>
    </logic:notEmpty>

    </td>
    </tr>
    </table>
    <br><br>
    </logic:notEqual>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="100%" nowrap><span class="text_bb1"><gsmsg:write key="main.plugin.usage.restrictions" /></span></td>

    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="main.plugin.usage.restrictions" /></span></td>
    <td align="left" class="td_type20" width="80%">
      <logic:equal name="man280knForm" property="man280useKbn" value="0"><gsmsg:write key="main.man280.4" /></logic:equal>
      <logic:equal name="man280knForm" property="man280useKbn" value="1"><gsmsg:write key="main.man280.5" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td id="pluginUseMember" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.howto.limit" /></span></td>
    <td id="pluginUseMember2" align="left" class="td_type20">
    <logic:equal name="man280knForm" property="man280limitType" value="0">
    <gsmsg:write key="main.man280.7" />
    </logic:equal>
    <logic:equal name="man280knForm" property="man280limitType" value="1">
    <gsmsg:write key="main.man280.8" />
    </logic:equal>
    <br><br>

    <% boolean groupFlg = false; %>
    <logic:notEmpty name="man280knForm" property="man280knMemGroupNameList">
      <% groupFlg = true; %>
      <gsmsg:write key="cmn.group" />:
      <logic:iterate id="memberData" name="man280knForm" property="man280knMemGroupNameList">
        <br>　　<bean:write name="memberData" property="label" />
      </logic:iterate>
    </logic:notEmpty>

    <logic:notEmpty name="man280knForm" property="man280knMemUserNameList">
      <% if (groupFlg) { %><br><br><% } %>
      <gsmsg:write key="cmn.user" />:
      <logic:iterate id="memberData" name="man280knForm" property="man280knMemUserNameList">
        <br>　　<bean:write name="memberData" property="label" />
      </logic:iterate>
    </logic:notEmpty>

    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
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