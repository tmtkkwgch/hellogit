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

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">
<html:form action="/main/man122">
<input type="hidden" name="CMD" value="conf">
<html:hidden property="man120pluginId" />
<html:hidden property="menuEditOk" />

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
        <th height="0%" class="table_bg_7D91BD" align="left"><span class="text_tlw"><gsmsg:write key="man.menu" /></span>
        </th>
        </tr>
        <tr>
        <td height="100%" valign="top" class="td_type1">
        <div class="plugin_menu">
          <a href="javaScript:void(0);" onclick="return buttonPush('plugin');">&nbsp;<span class="text_link"><gsmsg:write key="cmn.plugin" /></span></a>
        </div>
        <div class="plugin_menu">
          <a href="javaScript:void(0);" onclick="return buttonPush('seigenSettei');">&nbsp;<span class="text_link"><gsmsg:write key="main.man120.1" /></span></a>
        </div>
        <div class="plugin_menu">
          <a href="javaScript:void(0);">&nbsp;<span class="text_link"><gsmsg:write key="main.man120.2" /></span></a>
        </div>
        <br><br>

        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>


    </td>
    <td>&nbsp;&nbsp;</td>
    <td valign="top" width="100%">

      <table width="100%" cellpadding="0" cellspacing="0" class="tl0">
      <tr>
      <td class="" width="100%">


        <table width="100%" class="tl0" border="0" cellpadding="3">
        <tr>
        <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="main.man122.1" /></span></td>
        <td align="left" class="td_wt2" width="80%">

          <table width="100%" cellpadding="5" cellspacing="" class="tl0">
          <tr>
          <td width="0%">

          </td>
          <td width="100%">


            <html:radio name="man122Form" styleId="limit" property="menuEditOk" value="1" onclick="buttonPush('man122edit');"/>
            <label for="limit">
              <logic:equal name="man122Form" property="menuEditOk" value="1">
              <span class="text_bb1"><gsmsg:write key="main.man122.2" /></span>
              </logic:equal>
              <logic:equal name="man122Form" property="menuEditOk" value="0">
              <span class="text_base2"><gsmsg:write key="main.man122.2" /></span>
              </logic:equal>
            </label>


            <br>


            <html:radio name="man122Form" styleId="permit" property="menuEditOk" value="0" onclick="buttonPush('man122edit');"/></span>
            <label for="permit">
              <logic:equal name="man122Form" property="menuEditOk" value="0">
              <span class="text_bb1"><gsmsg:write key="main.man122.5" /></span>
              </logic:equal>
              <logic:equal name="man122Form" property="menuEditOk" value="1">
              <span class="text_base2"><gsmsg:write key="main.man122.5" /></span>
              </logic:equal>
            </label>
          </td>
          </tr>


          <tr>
          <td colspan="3" width="100%">
          <br><span class="text_base2" style="margin-left :20px;"><gsmsg:write key="main.man122.7" /></span>
          </td>
          </tr>

          </table>

        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>



      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">

      <tr id="man120pluginUseConfig">
      <td colspan="2">

        <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
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