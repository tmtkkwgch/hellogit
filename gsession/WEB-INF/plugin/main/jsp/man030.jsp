<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<% String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.preferences2" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">

<html:form action="/main/man030">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <bean:define id="kusr" name="<%= key %>" scope="session" />
      <logic:notEqual name="kusr" property="usrsid" value="0">
        <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('back');">
      </logic:notEqual>
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td valign="top" width="49%">

      <logic:notEmpty name="man030Form" property="pluginMenuList">
      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="cmn.plugin" />"><gsmsg:write key="cmn.plugin" /></dt>
      <dd>
      <logic:iterate id="plugin" name="man030Form" property="pluginMenuList">

        <li class="text">
          <img src="<bean:write name='plugin' property='icon' />" border="0" alt="">
          <a href="<bean:write name='plugin' property='url' />"><span class="text_link"><bean:write name='plugin' property='name' /></span></a>
          <bean:define id="pluginName" name='plugin' property='name' type="java.lang.String" />
          <br><span class="text_indent_22">・・・<gsmsg:write key="man.personal.settings" arg0="<%= pluginName %>" /></span>
        </li>

      </logic:iterate>

      </dd>
      </dl>
      </logic:notEmpty>
    </td>

    <td width="2%">&nbsp;</td>

    <td width="49%" valign="top">

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="cmn.preferences" />"><gsmsg:write key="cmn.preferences" /></dt>
      <dd>

        <logic:equal name="man030Form" property="changePassword" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.CHANGEPASSWORD_PARMIT) %>">
        <logic:equal name="man030Form" property="manPasswordKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_USER) %>">
        <li class="text">
          <img src="../main/images/icon_passward.gif" border="0" alt="<gsmsg:write key="cmn.change.password" />">
          <a href="#" onClick="return buttonPush('passwordEdit');"><span class="text_link"><gsmsg:write key="cmn.change.password" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="man.change.password.configure" /></span>
        </li>
        </logic:equal>
        </logic:equal>

        <logic:equal name="man030Form" property="mainPconfEditKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PCONF_EDIT_USER) %>">
        <li class="text">
          <img src="../main/images/icon_syain.gif" border="0" alt="<gsmsg:write key="cmn.modify.personalinfo" />">
          <a href="#" onClick="return buttonPush('userEdit');"><span class="text_link"><gsmsg:write key="cmn.modify.personalinfo" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man030.4" /></span>
        </li>
        </logic:equal>

        <logic:equal name="man030Form" property="pluginSetting" value="0">
        <li class="text">
          <img src="../main/images/icon_menu.gif" border="0" alt="<gsmsg:write key="main.man030.5" />">
          <a href="#" onClick="return buttonPush('menuEdit');"><span class="text_link"><gsmsg:write key="main.man030.5" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man030.6" /></span>
        </li>
        </logic:equal>

        <li class="text">
          <img src="../main/images/icon_main.gif" border="0" alt="<gsmsg:write key="cmn.setting.main.view2" />">
          <a href="#" onClick="return buttonPush('maindsp');"><span class="text_link"><gsmsg:write key="cmn.setting.main.view2" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man030.7" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_group.gif" border="0" alt="<gsmsg:write key="main.man030.8" />">
          <a href="#" onClick="return buttonPush('mygroup');"><span class="text_link"><gsmsg:write key="main.man030.8" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="man.set.destination.maigurupu" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_theme.gif" border="0" alt="<gsmsg:write key="main.man030.10" />">
          <a href="#" onClick="return buttonPush('theme');"><span class="text_link"><gsmsg:write key="main.man030.10" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man030.11" /></span>
        </li>

        <logic:equal name="man030Form" property="portalUseOk" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
        <li class="text">
          <img src="../portal/images/menu_icon_single.gif" class="img_border" alt="<gsmsg:write key="ptl.7" />">
          <a href="#" onClick="return buttonPush('portal');"><span class="text_link"><gsmsg:write key="ptl.7" /></span></a>
          <logic:equal name="man030Form" property="ptlSortPow" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.EDIT_KBN_PUBLIC) %>">
            <logic:equal name="man030Form" property="ptlDefPow" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.EDIT_KBN_PUBLIC) %>">
              <br><span class="text_indent_22">・・・<gsmsg:write key="ptl.22" /></span>
            </logic:equal>
            <logic:equal name="man030Form" property="ptlDefPow" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.EDIT_KBN_ADM) %>">
              <br><span class="text_indent_22">・・・<gsmsg:write key="ptl.ptl140.2" /></span>
            </logic:equal>
          </logic:equal>
          <logic:equal name="man030Form" property="ptlSortPow" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.EDIT_KBN_ADM) %>">
            <logic:equal name="man030Form" property="ptlDefPow" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.EDIT_KBN_PUBLIC) %>">
              <br><span class="text_indent_22">・・・<gsmsg:write key="ptl.ptl020.3" /></span>
            </logic:equal>
          </logic:equal>
        </li>
        </logic:equal>

        <logic:equal name="man030Form" property="mainLayoutKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.MANSCREEN_LAYOUTKBN_USER) %>">
        <li class="text">
          <img src="../portal/images/menu_icon_single.gif" class="img_border" alt="<gsmsg:write key="main.layout.setting" />">
          <a href="#" onClick="return buttonPush('mainLayout');"><span class="text_link"><gsmsg:write key="main.layout.setting" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.73" /></span>
        </li>
        </logic:equal>

<%--
        <li class="text">
          <img src="../common/images/icon_language.gif" border="0" alt="<gsmsg:write key="main.man030.13" />">
          <a href="#" onClick="return buttonPush('languageEdit');"><span class="text_link"><gsmsg:write key="main.man030.13" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man030.12" /></span>
        </li>
--%>
      </dd>
      </dl>

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