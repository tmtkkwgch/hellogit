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
<script language="JavaScript" src="../main/js/man121.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do'">
<html:form action="/main/man121">
<input type="hidden" name="CMD" value="conf">
<html:hidden property="man120pluginId" />
<html:hidden property="man280backId" />

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
    <td width="193px" align="left" valign="top" height="100%">

      <table height="100%" cellpadding="0" cellspacing="0" height="100%">
      <tr>
      <td width="193px" height="100%">

        <table class="tl3" width="193px" height="100%" cellpadding="5" cellspacing="0">

        <tr>
        <th class="table_bg_7D91BD" align="left" height="0%"><span class="text_tlw"><gsmsg:write key="man.menu" /></span>
        </th>
        </tr>
        <tr>
        <td height="100%" valign="top" class="td_type1" >
        <div class="plugin_menu">
          <a href="javaScript:void(0);" onclick="return buttonPush('plugin');">&nbsp;<span class="text_link"><gsmsg:write key="cmn.plugin" /></span></a>
        </div>
        <div class="plugin_menu">
          <a href="javaScript:void(0);">&nbsp;<span class="text_link"><gsmsg:write key="main.man120.1" /></span></a>
        </div>
        <div class="plugin_menu">
          <a href="javaScript:void(0);" onclick="return buttonPush('dspSettei');">&nbsp;<span class="text_link"><gsmsg:write key="main.man120.2" /></span></a>
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

    <table class="tl0" width="100%" cellpadding="0" cellspacing="0">

      <tr>
        <th align="left" width="100%" colspan="5" class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="main.man121.1" /></span></th>
      </tr>

      <tr>
        <th class="td_type3" width="20%"><span class="text_bb2"><gsmsg:write key="cmn.plugin" /></span></th>
        <th class="td_type3" width="40%"><span class="text_bb2"><gsmsg:write key="cmn.admin" /></span></th>
        <th class="td_type3" width="0%"><span class="text_bb2"><gsmsg:write key="cmn.howto.limit" /></span></th>
        <th class="td_type3" width="40%"><span class="text_bb2"><gsmsg:write key="cmn.target" /></span></th>
        <th class="td_type3" width="0%"><span class="text_bb2"></span></th>
      </tr>


      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>

      <% boolean admGroupFlg = false; %>
      <logic:notEmpty name="man121Form" property="man121dspList" >
      <logic:iterate id="dspModel" name="man121Form" property="man121dspList" indexId="idx">
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>

        <tr>
        <td nowrap align="left" valign="top" class="<%= tdColor %>">
        <logic:equal name="dspModel" property="pluginKbn" value="0">
          <img width="22px" src="../<bean:write name="dspModel" property="pluginId" />/images/menu_icon_single.gif" id="img<bean:write name="dspModel" property="pluginId" />" alt="" class="img_bottom img_border" onerror="defaultImg('img<bean:write name="dspModel" property="pluginId" />')">
        </logic:equal>
        <logic:notEqual name="dspModel" property="pluginKbn" value="0">
          <logic:equal name="dspModel" property="pluginBinSid" value="0">
            <img width="22" height="22" class="img_border img_bottom" src="../common/images/plugin_default.gif" name="pitctImage" alt="<gsmsg:write key="cmn.icon" />" border="1">
          </logic:equal>
          <logic:notEqual name="dspModel" property="pluginBinSid" value="0">
            <img width="22" height="22" class="img_border img_bottom" src="../main/man121.do?CMD=getImageFile&man120imgPluginId=<bean:write name="dspModel" property="pluginId" />" alt="<gsmsg:write key="cmn.icon" />">
          </logic:notEqual>
        </logic:notEqual>
        <bean:write name="dspModel" property="pluginName" />
        </td>



        <td nowrap align="left" valign="top" class="<%= tdColor %>">
          <table border="0" width="100%">
          <tr>
          <td style="border:0px;vertical-align:text-top;">

          <span class="text_base">
          <% admGroupFlg = false; %>
          <logic:notEmpty name="dspModel" property="admGroupNameList">
            <% admGroupFlg = true; %>
            <gsmsg:write key="cmn.group" />:
            <logic:iterate id="adminData" name="dspModel" property="admGroupNameList">
              <br>　　<bean:write name="adminData" property="label" />
            </logic:iterate>
          </logic:notEmpty>

          <logic:notEmpty name="dspModel" property="admUserNameList">
            <% if (admGroupFlg) { %><br><% } %>
            <gsmsg:write key="cmn.user" />:
            <logic:iterate id="adminData" name="dspModel" property="admUserNameList">
              <br>　　<bean:write name="adminData" property="label" />
            </logic:iterate>
          </logic:notEmpty>
          </span>
          </td>
          </tr>
          </table>
        </td>
        <td nowrap align="left" valign="top" class="<%= tdColor %>">
          <logic:equal name="dspModel" property="pctType" value="0"><gsmsg:write key="main.man121.3" /><br><gsmsg:write key="main.man121.4" /></logic:equal>
          <logic:equal name="dspModel" property="pctType" value="1"><gsmsg:write key="main.man121.5" /><br><gsmsg:write key="main.man121.6" /></logic:equal>
          <logic:equal name="dspModel" property="pctType" value="2"><gsmsg:write key="man.no.limit" /></logic:equal>
        </td>
        <td nowrap align="left" valign="top" class="<%= tdColor %>">
           <bean:write name="dspModel" property="taisyo" />
        </td>
        <td nowrap align="center" class="<%= tdColor %>">
          <input type="button" value="<gsmsg:write key="cmn.change" />" class="btn_edit_n3" onClick="editClick('<bean:write name="dspModel" property="pluginId" />');">
        </td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>

      </table>


      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">

      <tr>
      <td width="100%" align="left">
      <span class="text_base"><gsmsg:write key="main.man121.7" /></span>
      </td>
      <td width="0%">

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