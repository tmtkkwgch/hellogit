<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.man.GSConstMain" %>
<% String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.sortable.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jtooltip_main.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>

<logic:notEmpty name="ptl010Form" property="ptl010scriptPathList">
<logic:iterate id="jsPath" name="ptl010Form" property="ptl010scriptPathList">
  <script language="JavaScript" src="<bean:write name="jsPath" />"></script>
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ptl010Form" property="ptl010stylePath">
<logic:iterate id="cssPath" name="ptl010Form" property="ptl010stylePath">
  <link rel=stylesheet href='<bean:write name="cssPath" />' type='text/css'>
</logic:iterate>
</logic:notEmpty>


<script type="text/javascript">
<!--

$(function() {

  <logic:notEmpty name="ptl010Form" property="ptl010allList">
  <logic:iterate id="screenAllMdl" name="ptl010Form" property="ptl010allList" indexId="idx">

    var screenId<bean:write name="idx" /> = '';
    var url<bean:write name="idx" /> = "";

    <logic:equal name="screenAllMdl" property="ptpType" value="1">
    screenId<bean:write name="idx" /> = '<bean:write name="screenAllMdl" property="pluginId" />' + '_' + '<bean:write name="screenAllMdl" property="screenId" />'
    url<bean:write name="idx" /> = '<bean:write name="screenAllMdl" property="screenUrl" />';
    if (url<bean:write name="idx" /> != "") {
        $.ajaxSetup({async:true});
        $.post(url<bean:write name="idx" />, function(data){
            if ($('#' + screenId<bean:write name="idx" />)[0] != null) {
<logic:equal name="screenAllMdl" property="loadScript" value="true">
                $('#' + screenId<bean:write name="idx" />).html(data);
</logic:equal>
<logic:notEqual name="screenAllMdl" property="loadScript" value="true">
                $('#' + screenId<bean:write name="idx" />)[0].innerHTML = data;
</logic:notEqual>
            }
        });
    }
    </logic:equal>
    <logic:equal name="screenAllMdl" property="ptpType" value="2">
    screenId<bean:write name="idx" /> = '<bean:write name="screenAllMdl" property="pluginId" />' + '_' + '<bean:write name="screenAllMdl" property="itemId" />'
    url<bean:write name="idx" /> = '<bean:write name="screenAllMdl" property="screenUrl" filter="false" />';
    if (url<bean:write name="idx" /> != "") {
        $.ajaxSetup({async:true});
        $.post(url<bean:write name="idx" />, function(data2){
            if ($('#' + screenId<bean:write name="idx" />)[0] != null) {
                $('#' + screenId<bean:write name="idx" />).html(data2);
            }
        });
    }
    </logic:equal>
  </logic:iterate>
  </logic:notEmpty>
});

  <logic:notEqual name="ptl010Form" property="ptl010Reload" value="0">
    var reloadinterval = <bean:write name="ptl010Form" property="ptl010Reload" />;
    setTimeout("buttonPush('reload')",reloadinterval);
  </logic:notEqual>
-->
</script>





<title>[Group Session] <gsmsg:write key="ptl.2" /></title>
</head>

<body class="body_03 ptl_body" onload=" initArea();">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../main/images/header_main_01.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.main" /></span></td>
    <td width="0%" class="header_white_bg_text" nowrap align="left">[<bean:write name="ptl010Form" property="dspPtlName" />]</td>
    <td width="100%" class="header_white_bg">
      <input type="button" name="btn_reload" class="btn_reload_n1" value="<gsmsg:write key="cmn.reload" />" onClick="buttonPush2('reload');">
      <bean:define id="kusr" name="<%= key %>" scope="session" />
      <logic:equal name="kusr" property="adminFlg" value="true">
        <input type="button" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onclick="buttonPush2('ktools');">
      </logic:equal>
      <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onclick="buttonPush2('pset');">
    </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>
<!-- tabの部分を全てdivで作成した場合-->

<div class="portal_tab_list" >
  <ul>
    <bean:define id="selectPtlSid" name="ptl010Form" property="dspPtlSid" />
    <% String sltPtlSid = (selectPtlSid).toString(); %>

    <logic:notEmpty name="ptl010Form" property="ptl010ptlList">
    <logic:iterate id="ptlMdl" name="ptl010Form" property="ptl010ptlList">

      <logic:equal name="ptlMdl" property="ptlSid" value="<%= sltPtlSid %>">

      <li class="now_forcus">
         <span><bean:write name="ptlMdl" property="ptlName" /></span>
      </li>
      </logic:equal>
      <logic:notEqual name="ptlMdl" property="ptlSid" value="<%= sltPtlSid %>">
      <li class="none_forcus">
        <a href="javascript:void(0);" onClick="return movePortal(<bean:write name="ptlMdl" property="ptlSid" />);"><bean:write name="ptlMdl" property="ptlName" /></a>
      </li>
      </logic:notEqual>

    </logic:iterate>
    </logic:notEmpty>
  </ul>
</div>

<html:form action="/portal/ptl010">
<input type="hidden" name="CMD" value="">
<html:hidden property="ptlPortalSid" />
<html:hidden property="ptl010areaTop" />
<html:hidden property="ptl010areaBottom" />
<html:hidden property="ptl010areaLeft" />
<html:hidden property="ptl010areaCenter" />
<html:hidden property="ptl010areaRight" />
<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>
</html:form>

  <!-- 各プラグイン情報レイアウト -->

  <table width="100%" cellspacing="0" cellpadding="0">
  <tr>
  <td valign="top" class="plt_top">


  <table width="100%" cellspacing="0" cellpadding="0">

  <!-- 上 -->
  <tr>
  <td width="99%" id="mainScreenListTop" class="portal_area_main">

  <logic:notEmpty name="ptl010Form" property="ptl010topList">
    <logic:iterate id="topMdl" name="ptl010Form" property="ptl010topList">

      <logic:equal name="topMdl" property="ptpType" value="3">
      <div><%@ include file="/WEB-INF/plugin/portal/jsp/ptl010_information.jsp" %></div>
      </logic:equal>

      <logic:equal name="topMdl" property="ptpType" value="2">
        <div class="portlet" id="<bean:write name='topMdl' property='itemId' />"><span id="<bean:write name='topMdl' property='pluginId' />_<bean:write name='topMdl' property='itemId' />"><bean:write name="topMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="topMdl" property="ptpType" value="1">
        <div class="portlet" id="<bean:write name='topMdl' property='itemId' />"><span id="<bean:write name='topMdl' property='pluginId' />_<bean:write name='topMdl' property='screenId' />"><bean:write name="topMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="topMdl" property="ptpType" value="0">
        <!-- ポートレット -->
        <bean:define id="border" name="topMdl" property="ptlBorderKbn" />
        <% String borderKbn = (border).toString(); %>
        <% String className = "";%>
        <% if (borderKbn.equals("0")) { %>
        <%     className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title" ><bean:write name="topMdl" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     className = "portlet_nonborder portlet_style"; %>
        <% } %>
        <table class="<%= className %>" id="<bean:write name='topMdl' property='itemId' />" width="100%">
        <tr><td>
          <bean:write name="topMdl" property="ptlContent" filter="false"/>
        </td></tr>
        </table>
      </logic:equal>
    </logic:iterate>
  </logic:notEmpty>
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%">
    <tr>
    <!-- 左-->
    <td width="33%" valign="top" id="mainScreenListLeft" class="portal_area_main plt_left">

    <logic:notEmpty name="ptl010Form" property="ptl010leftList">
    <logic:iterate id="leftMdl" name="ptl010Form" property="ptl010leftList">

      <logic:equal name="leftMdl" property="ptpType" value="3">
      <div><%@ include file="/WEB-INF/plugin/portal/jsp/ptl010_information.jsp" %></div>
      </logic:equal>

      <logic:equal name="leftMdl" property="ptpType" value="2">
        <div class="portlet" id="<bean:write name='leftMdl' property='itemId' />">
          <span id="<bean:write name='leftMdl' property='pluginId' />_<bean:write name='leftMdl' property='itemId' />"><bean:write name="leftMdl" property="pluginName" /><gsmsg:write key="main.10" /></span>
        </div>
      </logic:equal>

      <logic:equal name="leftMdl" property="ptpType" value="1">
        <div class="portlet" id="<bean:write name='leftMdl' property='itemId' />"><span id="<bean:write name='leftMdl' property='pluginId' />_<bean:write name='leftMdl' property='screenId' />"><bean:write name="leftMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="leftMdl" property="ptpType" value="0">
        <!-- ポートレット -->
        <bean:define id="border" name="leftMdl" property="ptlBorderKbn" />
        <% String borderKbn = (border).toString(); %>
        <% String className = "";%>
        <% if (borderKbn.equals("0")) { %>
        <%     className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="leftMdl" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     className = "portlet_nonborder portlet_style"; %>
        <% } %>
        <table class="<%= className %>" id="<bean:write name='leftMdl' property='itemId' />" width="100%">
        <tr><td>
          <bean:write name="leftMdl" property="ptlContent" filter="false"/>
        </td></tr>
        </table>
      </logic:equal>
    </logic:iterate>
    </logic:notEmpty>

    </td>

    <!-- 中央 -->
    <td width="33%" valign="top" id="mainScreenListCenter" class="portal_area_main plt_center">

    <logic:notEmpty name="ptl010Form" property="ptl010centerList">
    <logic:iterate id="centerMdl" name="ptl010Form" property="ptl010centerList">

      <logic:equal name="centerMdl" property="ptpType" value="3">
      <div><%@ include file="/WEB-INF/plugin/portal/jsp/ptl010_information.jsp" %></div>
      </logic:equal>

      <logic:equal name="centerMdl" property="ptpType" value="2">
        <div class="portlet" id="<bean:write name='centerMdl' property='itemId' />"><span id="<bean:write name='centerMdl' property='pluginId' />_<bean:write name='centerMdl' property='itemId' />"><bean:write name="centerMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="centerMdl" property="ptpType" value="1">
        <div class="portlet" id="<bean:write name='centerMdl' property='itemId' />"><span id="<bean:write name='centerMdl' property='pluginId' />_<bean:write name='centerMdl' property='screenId' />"><bean:write name="centerMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="centerMdl" property="ptpType" value="0">
        <!-- ポートレット -->
        <bean:define id="border" name="centerMdl" property="ptlBorderKbn" />
        <% String borderKbn = (border).toString(); %>
        <% String className = "";%>
        <% if (borderKbn.equals("0")) { %>
        <%     className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title" ><bean:write name="centerMdl" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     className = "portlet_nonborder portlet_style"; %>
        <% } %>
        <table class="<%= className %>" id="<bean:write name='centerMdl' property='itemId' />" width="100%">
        <tr><td>
          <bean:write name="centerMdl" property="ptlContent" filter="false"/>
        </td></tr>
        </table>
      </logic:equal>
    </logic:iterate>
    </logic:notEmpty>

    </td>

    <!-- 右 -->
    <td width="33%" valign="top" id="mainScreenListRight" class="portal_area_main plt_right">

    <logic:notEmpty name="ptl010Form" property="ptl010rightList">
    <logic:iterate id="rightMdl" name="ptl010Form" property="ptl010rightList">

      <logic:equal name="rightMdl" property="ptpType" value="3">
      <div><%@ include file="/WEB-INF/plugin/portal/jsp/ptl010_information.jsp" %></div>
      </logic:equal>

      <logic:equal name="rightMdl" property="ptpType" value="2">
        <div class="portlet" id="<bean:write name='rightMdl' property='itemId' />"><span id="<bean:write name='rightMdl' property='pluginId' />_<bean:write name='rightMdl' property='itemId' />"><bean:write name="rightMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="rightMdl" property="ptpType" value="1">
        <div class="portlet" id="<bean:write name='rightMdl' property='itemId' />"><span id="<bean:write name='rightMdl' property='pluginId' />_<bean:write name='rightMdl' property='screenId' />"><bean:write name="rightMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="rightMdl" property="ptpType" value="0">
        <!-- ポートレット -->
        <bean:define id="border" name="rightMdl" property="ptlBorderKbn" />
        <% String borderKbn = (border).toString(); %>
        <% String className = "";%>
        <% if (borderKbn.equals("0")) { %>
        <%     className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="rightMdl" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     className = "portlet_nonborder portlet_style"; %>
        <% } %>
        <table class="<%= className %>" id="<bean:write name='rightMdl' property='itemId' />" width="100%">
        <tr><td>
          <bean:write name="rightMdl" property="ptlContent" filter="false"/>
        </td></tr>
        </table>
      </logic:equal>
    </logic:iterate>
    </logic:notEmpty>

    </td>
    </tr>
    </table>

  </td>
  </tr>

  <!-- 下 -->

  <tr>
  <td width="100%" align="center" valign="top" id="mainScreenListBottom" class="portal_area_main portal_area_bottom">

    <logic:notEmpty name="ptl010Form" property="ptl010bottomList">
    <logic:iterate id="bottomMdl" name="ptl010Form" property="ptl010bottomList">

      <logic:equal name="bottomMdl" property="ptpType" value="3">
      <div><%@ include file="/WEB-INF/plugin/portal/jsp/ptl010_information.jsp" %></div>
      </logic:equal>

      <logic:equal name="bottomMdl" property="ptpType" value="2">
        <div class="portlet" id="<bean:write name='bottomMdl' property='itemId' />"><span id="<bean:write name='bottomMdl' property='pluginId' />_<bean:write name='bottomMdl' property='itemId' />"><bean:write name="bottomMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="bottomMdl" property="ptpType" value="1">
        <div class="portlet" id="<bean:write name='bottomMdl' property='itemId' />"><span id="<bean:write name='bottomMdl' property='pluginId' />_<bean:write name='bottomMdl' property='screenId' />"><bean:write name="bottomMdl" property="pluginName" /><gsmsg:write key="main.10" /></span></div>
      </logic:equal>

      <logic:equal name="bottomMdl" property="ptpType" value="0">
        <!-- ポートレット -->
        <bean:define id="border" name="bottomMdl" property="ptlBorderKbn" />
        <% String borderKbn = (border).toString(); %>
        <% String className = "";%>
        <% if (borderKbn.equals("0")) { %>
        <%     className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="bottomMdl" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     className = "portlet_nonborder portlet_style"; %>
        <% } %>
        <table class="<%= className %>" id="<bean:write name='bottomMdl' property='itemId' />" width="100%">
        <tr><td>
          <bean:write name="bottomMdl" property="ptlContent" filter="false"/>
        </td></tr>
        </table>
      </logic:equal>
    </logic:iterate>
    </logic:notEmpty>

  </td>
  </tr>
  </table>

  </td>
  </tr>
  </table>

  <logic:equal name="ptl010Form" property="ptl010ptlAdminFlg" value="true">
  <div align="right">
    <input type="button" value="<gsmsg:write key="ptl.7" />" class="btn_portal_n1" onClick="movePortalSetting();">
  </div>
  </logic:equal>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>