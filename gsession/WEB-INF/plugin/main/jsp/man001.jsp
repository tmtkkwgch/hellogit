<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.man.GSConstMain" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<% String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>

<html:html>
<head>
<title><gsmsg:write key="main.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.sortable.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../main/js/man001.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptlmain.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jtooltip_main.js?<%= GSConst.VERSION_PARAM %>'></script>
<%--
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/scriptaculous/scriptaculous.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
--%>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>



<logic:notEmpty name="man001Form" property="screenInfoList">
<logic:iterate id="screenMdl" name="man001Form" property="screenInfoList">

<logic:notEmpty name="screenMdl" property="scriptPath">
<script language="JavaScript" src="<bean:write name="screenMdl" property="scriptPath" />?<%= GSConst.VERSION_PARAM %>"></script>
</logic:notEmpty>

<logic:notEmpty name="screenMdl" property="stylePath">
<link rel=stylesheet href='<bean:write name="screenMdl" property="stylePath" />?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</logic:notEmpty>

</logic:iterate>
</logic:notEmpty>


<logic:notEmpty name="man001Form" property="screenInfoList">

<script type="text/javascript">
<!--

$(function() {

  <logic:iterate id="screenMdl" name="man001Form" property="screenInfoList" indexId="idx">
    var screenId<bean:write name="idx" /> = '';
    var url<bean:write name="idx" /> = "";

    screenId<bean:write name="idx" /> = '<bean:write name="screenMdl" property="pluginId" />' + '_' + '<bean:write name="screenMdl" property="id" />';
    url<bean:write name="idx" /> = '<bean:write name="screenMdl" property="screenUrl" />';

    if (url<bean:write name="idx" /> != '') {
        $.ajaxSetup({async:true});
        $.post(url<bean:write name="idx" />, function(data){
            if ($('#' + screenId<bean:write name="idx" />)[0] != null) {
<logic:equal name="screenMdl" property="loadScript" value="true">
                $('#' + screenId<bean:write name="idx" />).html(data);
</logic:equal>
<logic:notEqual name="screenMdl" property="loadScript" value="true">
                $('#' + screenId<bean:write name="idx" />)[0].innerHTML = data;
</logic:notEqual>
            }
        });
    }

  </logic:iterate>
});

  <logic:notEqual name="man001Form" property="man001Reload" value="0">
    var reloadinterval = <bean:write name="man001Form" property="man001Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>

</logic:notEmpty>

</head>

<style>
a:hover {text-decoration:none;}
a.tooltip span {display:none;}
a.tooltip:hover span{display:inline; position:absolute; padding: 5px;margin: 5px;background-color: #cccccc;border: solid 1px #666666;font-size: 12px;white-space:nowrap;}
</style>

<logic:empty name="man001Form" property="screenInfoList">
<body class="body_03" onunload="windowClose();">
</logic:empty>
<logic:notEmpty name="man001Form" property="screenInfoList">
<body class="body_03" onload="initArea();" onunload="windowClose();">
</logic:notEmpty>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="header_white_bg2"><img src="../main/images/header_main_01.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.main" /></span></td>

    <% boolean smailUseFlg = false; %>
    <logic:equal name="man001Form" property="smailUseOk" value="<%= String.valueOf(GSConstMain.PLUGIN_USE) %>"><% smailUseFlg = true; %></logic:equal>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle">
    <% if (smailUseFlg) { %>
      <a href="../smail/sml010.do?sml010scriptFlg=1&sml010scriptKbn=2"><img alt="<gsmsg:write key="cmn.send.shortmail" />" src="../smail/images/icon_ms.gif" border="0"></a></td>
    <% } else { %></td><% } %>

    <td width="10%" class="header_white_bg_text3" align="left" valign="middle">
    <% if (smailUseFlg) { %>
      <a href="../smail/sml010.do?sml010scriptFlg=1&sml010scriptKbn=2" style="font-size: 80%; color: #000066;"><gsmsg:write key="cmn.send.shortmail" /></a>
    <% } %>
    </td>

    <% boolean projectUseFlg = false; %>
    <logic:equal name="man001Form" property="projectUseOk" value="<%= String.valueOf(GSConstMain.PLUGIN_USE) %>"><% projectUseFlg = true; %></logic:equal>
    <td width="0%" class="header_white_bg_text3" align="right" valign="middle">
    <% if (projectUseFlg) { %>
      <a href="../project/prj050.do?CMD=addTodo"><img alt="<gsmsg:write key="main.6" />" src="../project/images/icon_add_todo.gif" border="0"></a></td>
    <% } else { %></td><% } %>

    <td width="8%" class="header_white_bg_text3" align="left" valign="middle">
    <% if (projectUseFlg) { %>
      <a href="../project/prj050.do?CMD=addTodo" style="font-size: 80%; color: #000066;"><gsmsg:write key="main.6" /></a>
    <% } %>
    </td>

    <td width="52%" class="header_white_bg_text2">&nbsp;</td>

    <td width="20%" class="header_white_bg">
      <input type="button" name="btn_reload" class="btn_reload_n1" value="<gsmsg:write key="cmn.reload" />" onClick="buttonPush2('reload');">
      <logic:equal name="man001Form" property="adminKbn" value="<%= String.valueOf(GSConst.USER_ADMIN) %>">
        <input type="button" value="<gsmsg:write key="cmn.admin.setting" />" class="btn_setting_admin_n1" onClick="buttonPush2('ktools');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.preferences2" />" class="btn_setting_n1" onClick="buttonPush2('pset')">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    </tr>

<logic:empty name="man001Form" property="portalList">
    <tr><td colspan="9"><IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0"></td></tr>
</logic:empty>

    <logic:messagesPresent message="false">
    <tr>
    <td colspan="9" align="left"><br><html:errors/></td>
    </tr>
    </logic:messagesPresent>

    </table>


  </td>
  </tr>
  </table>

<logic:notEmpty name="man001Form" property="portalList">
<div style="width:100%;padding-right:2px;">
<div style="padding-right:2px;padding-left:2px;">
<div class="portal_tab_list">
  <ul>
  <logic:iterate id="ptlMdl" name="man001Form" property="portalList">
    <logic:equal name="ptlMdl" property="ptlSid" value="0">
      <li class="now_forcus"><span><bean:write name="ptlMdl" property="ptlName" /></span></li>
    </logic:equal>
    <logic:notEqual name="ptlMdl" property="ptlSid" value="0">
      <li class="none_forcus"><a href="javascript:void(0)" onclick="return pushPortalTab('portal', <bean:write name="ptlMdl" property="ptlSid" />)"><bean:write name="ptlMdl" property="ptlName" /></a></li>
    </logic:notEqual>
  </logic:iterate>
  </ul>
</div>
</div>
</div>
</logic:notEmpty>

<html:form action="/main/man001">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="ptlMainSid" />
<input type="hidden" name="ptlBackPage" value="">

<html:hidden property="man001mainStatus" />

<html:hidden property="man001layoutDefFlg" />
<html:hidden property="man001areaLeft" />
<html:hidden property="man001areaRight" />
<html:hidden property="man001areaTop" />
<html:hidden property="man001areaBottom" />
<html:hidden property="man001areaCenter" />

</html:form>

  <table width="100%" cellspacing="0" cellpadding="0">
  <tr>
  <td valign="top" class="plt_top">

  <logic:equal name="man001Form" property="man001layoutDefFlg" value="true">

  <table width="100%" cellspacing="0" cellpadding="0">
  <tr>

  <!-- 左 -->
  <td valign="top" id="mainScreenListLeft" class="column plt_left">
    <logic:notEmpty name="man001Form" property="screenInfoListLeft">
    <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListLeft">
      <bean:define id="pluginId" name="screenMdl" property="pluginId" />
      <bean:define id="id" name="screenMdl" property="id" />
      <% String positionId = id.toString(); %>
      <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span><% } %></div>
    </logic:iterate>
    </logic:notEmpty>
  </td>

  <!-- 右 -->
  <td valign="top" id="mainScreenListRight" class="column2 plt_right">

    <logic:notEmpty name="man001Form" property="screenInfoListRight">
    <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListRight">

      <bean:define id="pluginId" name="screenMdl" property="pluginId" />
      <bean:define id="id" name="screenMdl" property="id" type="java.lang.String" />
      <% String positionId = id.toString(); %>
      <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" /></span><% } %></div>
    </logic:iterate>
    </logic:notEmpty>
  </td>
  </tr>
  </table>
  </logic:equal>



  <logic:notEqual name="man001Form" property="man001layoutDefFlg" value="true">
  <table width="100%" cellspacing="0" cellpadding="0">
  <!-- 上 -->
  <logic:equal name="man001Form" property="man001areaTop" value="0">
  <tr>
  <td width="99%" id="mainScreenListTop" class="column3 portal_area_main">

    <logic:notEmpty name="man001Form" property="screenInfoListTop">
    <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListTop">

      <bean:define id="pluginId" name="screenMdl" property="pluginId" />
      <bean:define id="id" name="screenMdl" property="id" type="java.lang.String" />
      <% String positionId = id.toString(); %>
      <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" /></span><% } %></div>
    </logic:iterate>
    </logic:notEmpty>
    <logic:empty name="man001Form" property="screenInfoListTop">&nbsp;</logic:empty>
  </td>
  </tr>
  </logic:equal>

  <tr>
  <td>
    <table width="100%">
    <tr>
    <!-- 左-->
    <logic:equal name="man001Form" property="man001areaLeft" value="0">
    <td width="33%" valign="top" id="mainScreenListLeft" class="column3 portal_area_main plt_left">

      <logic:notEmpty name="man001Form" property="screenInfoListLeft">
      <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListLeft">
        <bean:define id="pluginId" name="screenMdl" property="pluginId" />
        <bean:define id="id" name="screenMdl" property="id" type="java.lang.String" />
        <% String positionId = id.toString(); %>
        <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" /></span><% } %></div>
      </logic:iterate>
      </logic:notEmpty>
    </td>
    </logic:equal>

    <!-- 中 -->
    <logic:equal name="man001Form" property="man001areaCenter" value="0">
    <td width="33%" valign="top" id="mainScreenListCenter" class="column3 portal_area_main plt_center">

      <logic:notEmpty name="man001Form" property="screenInfoListCenter">
      <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListCenter">
        <bean:define id="pluginId" name="screenMdl" property="pluginId" />
        <bean:define id="id" name="screenMdl" property="id" type="java.lang.String" />
        <% String positionId = id.toString(); %>
        <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" /></span><% } %></div>
      </logic:iterate>
      </logic:notEmpty>

    </td>
    </logic:equal>

    <!-- 右 -->
    <logic:equal name="man001Form" property="man001areaRight" value="0">
    <td width="33%" valign="top" id="mainScreenListRight" class="column3 portal_area_main plt_right">

      <logic:notEmpty name="man001Form" property="screenInfoListRight">
      <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListRight">
        <bean:define id="pluginId" name="screenMdl" property="pluginId" />
        <bean:define id="id" name="screenMdl" property="id" type="java.lang.String" />
        <% String positionId = id.toString(); %>
        <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" /></span><% } %></div>
      </logic:iterate>
      </logic:notEmpty>

    </td>
    </logic:equal>
    <logic:equal name="man001Form" property="man001areaRight" value="1">
    <td></td>
    </logic:equal>

    </tr>
    </table>

  </td>
  </tr>

  <!-- 下 -->

  <logic:equal name="man001Form" property="man001areaBottom" value="0">
  <tr>
  <td width="100%" align="center" valign="top" id="mainScreenListBottom" class="column3 portal_area_main">
    <logic:notEmpty name="man001Form" property="screenInfoListBottom">
    <logic:iterate id="screenMdl" name="man001Form" property="screenInfoListBottom">
      <bean:define id="pluginId" name="screenMdl" property="pluginId" />
      <bean:define id="id" name="screenMdl" property="id" type="java.lang.String" />
      <% String positionId = id.toString(); %>
      <div class="portlet" id="<%= positionId %>"><% if (id.equals(GSConstMain.MAINSCREENID_INFORMATION)) { %><%@ include file="/WEB-INF/plugin/main/jsp/man001_information.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_DAYTIME)) { %><%@ include file="/WEB-INF/plugin/common/jsp/daytime.jsp" %><% } else if (id.equals(GSConstMain.MAINSCREENID_LASTLOGIN)) { %><%@ include file="/WEB-INF/plugin/common/jsp/lastlogin.jsp" %><% } else { %><% String screenId = pluginId + "_" + id; %><span id="<%= screenId %>"><bean:write name="screenMdl" property="pluginName" /><gsmsg:write key="cmn.reload" /></span><% } %></div>
    </logic:iterate>
    </logic:notEmpty>
    <logic:empty name="man001Form" property="screenInfoListBottom">&nbsp;</logic:empty>
  </td>
  </tr>
  </logic:equal>

  </table>
  </logic:notEqual>

  </td>
  </tr>
  </table>

  <logic:equal name="man001Form" property="man001ptlAdminFlg" value="true">
  <div align="right">
    <input type="button" value="<gsmsg:write key="ptl.7" />" class="btn_portal_n1" onClick="movePortalSetting();">
  </div>
  </logic:equal>

</td>
</tr>
</table>


<div align="right">
<div class="dispSortSet">
  <form name="lockForm" method="post" action="#">
  <span class="text_base"><gsmsg:write key="cmn.display.position" />：</span>
        <input type="radio" name="lockFlg" value="1" checked="checked" id="lockFlg_01" onclick="destroySortable();"><label for="lockFlg_01"><span class="text_base"><gsmsg:write key="cmn.fixed3" /></span></label>&nbsp;
        <input type="radio" name="lockFlg" value="0" id="lockFlg_02" onclick="createSortable();"><label for="lockFlg_02"><span class="text_base"><gsmsg:write key="main.man001.3" />&nbsp;(<gsmsg:write key="main.man001.4" />)</span></label>
  </form>
</div>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>