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
<title>[GroupSession] <gsmsg:write key="main.man030.5" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">
<html:form action="/common/cmn140">
<input type="hidden" name="CMD" value="">

<logic:notEmpty name="cmn140Form" property="cmn140viewMenuLabel">
<logic:iterate id="viewMenu" name="cmn140Form" property="cmn140viewMenuLabel">
<input type="hidden" name="cmn140viewMenuList" value="<bean:write name="viewMenu" property="value" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man030.5" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('backPsMenu');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false">
    <html:errors />
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl3">
      <tr>
      <td class="td_type19" colspan="4"><span class="text_tlwn"><gsmsg:write key="cmn.cmn140.1" /></span></td>
      </tr>

      <logic:equal name="cmn140Form" property="cmn140EditKbn" value="0">

      <tr>
      <td colspan="4">&nbsp;</td>
      </tr>

      <tr>
      <td width="40%" valign="top">
        <table border="0" width="100%" class="menu_list_margin">
        <tr>
        <td class="td_type14" align="center"><gsmsg:write key="cmn.cmn140.2" /></td>
        </tr>
        <tr>
        <td align="center">
          <html:select name="cmn140Form" property="cmn140selectNotViewMenu" size="12" style="width:100%;">
          <html:optionsCollection name="cmn140Form" property="cmn140notViewMenuLabel" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        </table>
      </td>

      <td width="10%" align="center" valign="middle">
        <img src="../common/images/arrow2_r.gif" width="25" height="25" alt="<gsmsg:write key="cmn.add" />" border="0" onClick="buttonPush('add');"><br><br>
        <img src="../common/images/arrow2_l.gif" width="25" height="25" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="buttonPush('delete');">
      </td>

      <td width="40%" valign="top">
        <table border="0" width="100%">
        <tr>
        <td class="td_type14" align="center"><gsmsg:write key="cmn.cmn140.3" /></td>
        </tr>
        <tr>
        <td align="center">
          <html:select name="cmn140Form" property="cmn140selectViewMenu" size="12" style="width:100%;">
          <html:optionsCollection name="cmn140Form" property="cmn140viewMenuLabel" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        </table>
      </td>

      <td width="10%" align="center">
        <img src="../common/images/arrow2_u.gif" width="25" height="25" alt="<gsmsg:write key="cmn.up" />" border="0" onClick="buttonPush('up');"><br><br>
        <img src="../common/images/arrow2_d.gif" width="25" height="25" alt="<gsmsg:write key="cmn.down" />" border="0" onClick="buttonPush('down');">
      </td>
      </tr>

      </logic:equal>
      <logic:notEqual name="cmn140Form" property="cmn140EditKbn" value="0">
      <tr>
      <td colspan="4"><span class="text_kakunin"><gsmsg:write key="cmn.cmn140.4" /></span></td>
      </tr>
      </logic:notEqual>

      <tr>
      <td colspan="4">&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td>
      <table cellpadding="0" border="0" width="100%" style="margin-top:5px">
      <tr>
      <td align="right">
        <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('backPsMenu');">
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