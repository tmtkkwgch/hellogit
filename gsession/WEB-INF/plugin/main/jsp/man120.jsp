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
<title>[GroupSession] <gsmsg:write key="main.man002.19" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man120.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do'">
<html:form action="/main/man120">
<input type="hidden" name="CMD" value="conf">
<html:hidden property="man120pluginId" />

<logic:notEmpty name="man120Form" property="man120notViewMenuList" scope="request">
<logic:iterate id="ulnotBean" name="man120Form" property="man120notViewMenuList" scope="request">
<input type="hidden" name="man120notViewMenuList" value="<bean:write name="ulnotBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="man120Form" property="man120viewMenuLabel">
<logic:iterate id="viewMenu" name="man120Form" property="man120viewMenuLabel">
<input type="hidden" name="man120viewMenuList" value="<bean:write name="viewMenu" property="pluginId" />">
</logic:iterate>
</logic:notEmpty>



<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="100%" cellpadding="5" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.19" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="addUplgClick();">
          <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backAdminMenu');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false">
    <html:errors />
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>


    <table cellpadding="0" cellspacing="0" align="center" width="100%">
    <tr>
    <td width="193px" align="left" valign="top" height="80%">

      <table height="100%" cellpadding="0" cellspacing="0" height="100%">
      <tr>
      <td width="193px" height="100%">

        <table class="tl3" width="193px" height="100%" cellpadding="5" cellspacing="0">

        <tr>
        <th class="table_bg_7D91BD" align="left"><span class="text_tlw"><gsmsg:write key="man.menu" /></span>
        </th>
        </tr>
        <tr>
        <td height="20%" valign="top" class="td_type1">
        <div class="plugin_menu">
          <a href="javaScript:void(0);">&nbsp;<span class="text_link"><gsmsg:write key="cmn.plugin" /></span></a>
        </div>
        <div class="plugin_menu">
          <a href="javaScript:void(0);" onclick="return buttonPush('seigenSettei');">&nbsp;<span class="text_link"><gsmsg:write key="main.man120.1" /></span></a>
        </div>
        <div class="plugin_menu">
          <a href="javaScript:void(0);" onclick="return buttonPush('dspSettei');">&nbsp;<span class="text_link"><gsmsg:write key="main.man120.2" /></span></a>
        </div>
        <br><br>

        </td>
        </tr>

        <tr>
        <th class="table_bg_7D91BD" align="left"><span class="text_tlw"><gsmsg:write key="main.man120.3" /></span>
        </th>
        </tr>
        <tr>
        <td height="80%" valign="top" class="td_type1">
          <table class="tl0" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
          <td valign="top" nowrap>
            <table class="tl0" border="0" cellpadding="0" cellspacing="0">
            <logic:notEmpty name="man120Form" property="man120notViewMenuLabel" >
            <logic:iterate id="notUsePlugin" name="man120Form" property="man120notViewMenuLabel" >
              <tr class="plugin_list">
              <td nowrap>
                <logic:equal name="notUsePlugin" property="pluginKbn" value="0">
                  <img width="22" height="22" src="../<bean:write name="notUsePlugin" property="pluginId" />/images/menu_icon_single.gif" id="img<bean:write name="notUsePlugin" property="pluginId" />" alt="" class="img_bottom img_border" onerror="defaultImg('img<bean:write name="notUsePlugin" property="pluginId" />')">
                </logic:equal>
                <logic:notEqual name="notUsePlugin" property="pluginKbn" value="0">
                  <logic:equal name="notUsePlugin" property="pluginBinSid" value="0">
                    <img width="22" height="22" src="../common/images/plugin_default.gif" name="pitctImage" alt="<gsmsg:write key="cmn.icon" />" class="img_border">
                </logic:equal>
                  <logic:notEqual name="notUsePlugin" property="pluginBinSid" value="0">
                    <img width="22" height="22" src="../main/man120.do?CMD=getImageFile&man120imgPluginId=<bean:write name="notUsePlugin" property="pluginId" />" alt="<gsmsg:write key="cmn.icon" /> class="img_border"">
                  </logic:notEqual>
                </logic:notEqual>
                &nbsp;
              </td>
              <td nowrap valign="middle">
                <span class="text_base2">
                <bean:write name="notUsePlugin" property="pluginName" />&nbsp;[<a href="javaScript:void(0);" onclick="return addUseKbn('<bean:write name="notUsePlugin" property="pluginId" />');"><gsmsg:write key="cmn.use" /></a>]
                </span>
              </td>
              </tr>
            </logic:iterate>
            </logic:notEmpty>
            </table>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>


    </td>
    <td>&nbsp;&nbsp;</td>
    <logic:equal name="man120Form" property="menuEditOk" value="1">
    <td valign="top">
    <br><br><br><br><br><br>
      <img src="../common/images/arrow2_u.gif" width="25" height="25" alt="<gsmsg:write key="cmn.up" />" border="0" onClick="buttonPush('up');">
      <br><br>
      <img src="../common/images/arrow2_d.gif" width="25" height="25" alt="<gsmsg:write key="cmn.down" />" border="0" onClick="buttonPush('down');">
    </td>
    <td>&nbsp;&nbsp;</td>
    </logic:equal>

    <td valign="top" width="100%">

      <table width="100%" cellpadding="0" cellspacing="0" class="tl0">
      <tr>
      <td width="100%" class="table_bg_7D91BD">
        <span class="text_tlw"><gsmsg:write key="main.man120.5" /></span>
      </td>
      </tr>
      </table>

      <logic:notEmpty name="man120Form" property="man120viewMenuLabel">
      <table width="100%" cellpadding="0" cellspacing="0" class="tl0">

      <logic:iterate id="viewMenuValue" name="man120Form" property="man120viewMenuLabel" indexId="idx">
      <bean:define id="pluginid" name="viewMenuValue" property="pluginId" />
      <tr>
      <td class="td_type1" width="100%">
        <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
        <tr border="1">

        <td width="0%" align="center">
          <logic:equal name="man120Form" property="menuEditOk" value="1">
            <html:radio property="man120sort" value="<%= String.valueOf(pluginid) %>" styleId="<%= String.valueOf(pluginid) %>"/>
          </logic:equal>
        </td>
        <td class="img_space" align="center">
          <label for="<%= String.valueOf(pluginid) %>">
            <logic:equal name="viewMenuValue" property="pluginKbn" value="0">
              <img width="25" height="25" src="../<bean:write name="viewMenuValue" property="pluginId" />/images/menu_icon_single.gif" id="img<bean:write name="viewMenuValue" property="pluginId" />" alt="" class="img_bottom img_border" onerror="defaultImg('img<bean:write name="viewMenuValue" property="pluginId" />')">
            </logic:equal>
            <logic:notEqual name="viewMenuValue" property="pluginKbn" value="0">
              <logic:equal name="viewMenuValue" property="pluginBinSid" value="0">
                <img width="25" height="25" src="../common/images/plugin_default.gif" name="pitctImage" alt="<gsmsg:write key="cmn.icon" />"  class="img_border">
              </logic:equal>
              <logic:notEqual name="viewMenuValue" property="pluginBinSid" value="0">
                <img width="25" height="25" src="../main/man120.do?CMD=getImageFile&man120imgPluginId=<bean:write name="viewMenuValue" property="pluginId" />" alt="<gsmsg:write key="cmn.icon" />" class="img_border">
              </logic:notEqual>
            </logic:notEqual>
          </label>
        </td>
        <td width="200px" nowrap>
          <label for="<%= String.valueOf(pluginid) %>"><span class="text_bb3"><bean:write name="viewMenuValue" property="pluginName" /></span></label>
        </td>
        <td width="100%" nowrap valign="middle">
          <logic:notEqual name="viewMenuValue" property="pluginId" value="main">
          <span class="text_base">
          <input type="radio" name="man120useKbn<bean:write name="viewMenuValue" property="pluginId" />" value="0" checked="checked"><gsmsg:write key="cmn.use" />
          <input type="radio" name="man120useKbn<bean:write name="viewMenuValue" property="pluginId" />" value="1" onclick="delUseKbn('<bean:write name="viewMenuValue" property="pluginId" />');" id="useKbn<bean:write name="viewMenuValue" property="pluginId" />"><label for="useKbn<bean:write name="viewMenuValue" property="pluginId" />"><gsmsg:write key="cmn.unused" /></label>
          </span>
          </logic:notEqual>
        </td>
        <td nowrap align="center" width="0%">
          <logic:notEqual name="viewMenuValue" property="pluginId" value="main">
            <logic:equal name="viewMenuValue" property="pluginKbn" value="1">
              <input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n3" onClick="editUplgClick('<bean:write name="viewMenuValue" property="pluginId" />');">
            </logic:equal>
            <input type="button" value="<gsmsg:write key="main.man120.6" />" class="btn_edit_n2" onClick="editClick('<bean:write name="viewMenuValue" property="pluginId" />');">
          </logic:notEqual>
        </td>
        </tr>
        </table>
        <logic:equal name="viewMenuValue" property="pluginId" value="mobile">
          <span class="text_base6">&nbsp;<gsmsg:write key="main.man120.7" /></span>
        </logic:equal>
        <logic:equal name="viewMenuValue" property="pluginId" value="api">
          <span class="text_base6">&nbsp;<gsmsg:write key="main.man120.8" /></span>
        </logic:equal>
      </td>
      </tr>

      <tr>
      <td><IMG SRC="../common/images/spacer.gif" width="1px" height="6px" border="0"></td>
      </tr>

      </logic:iterate>

      <tr id="man120pluginUseConfig">
      <td colspan="2">

        <table width="100%" cellpadding="5" cellspacing="0">
        <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backAdminMenu');">
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>
      </logic:notEmpty>

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