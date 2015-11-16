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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.sortable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl070.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>

<logic:notEmpty name="ptl070Form" property="jsList">
  <logic:iterate id="scriptPath" name="ptl070Form" property="jsList">
      <script language="JavaScript" src="<bean:write name="scriptPath" />"></script>
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ptl070Form" property="cssList">
  <logic:iterate id="stylePath" name="ptl070Form" property="cssList">
      <link rel=stylesheet href='<bean:write name="stylePath" />' type='text/css'>
  </logic:iterate>
</logic:notEmpty>

<script type="text/javascript">
<!--

function init() {

  var url = '';
  var pars = '';
  var screenId = '';

  <logic:notEmpty name="ptl070Form" property="urlList">
  <logic:iterate id="screenMdl" name="ptl070Form" property="urlList">

    <logic:equal name="screenMdl" property="partsKbn" value="1">
    ptpItemid = '<bean:write name="screenMdl" property="pluginId" />' + '_' + '<bean:write name="screenMdl" property="id" />'
    url = '<bean:write name="screenMdl" property="screenUrl" />';
    if (url != "") {
      $('#' + ptpItemid).load(url);
    }
    </logic:equal>

    <logic:equal name="screenMdl" property="partsKbn" value="2">
    ptpItemid = '<bean:write name="screenMdl" property="ptpItemid" />'
    url = '<bean:write name="screenMdl" property="screenUrl" />';
    if (url != "") {
      $('#' + ptpItemid).load(url);
    }
    </logic:equal>

  </logic:iterate>
  </logic:notEmpty>

  window.scroll( 0, 0 );
}

-->
</script>




<title>[Group Session] <gsmsg:write key="ptl.6" /></title>
</head>

<!-- body -->
<body class="body_03 ptl_body" onload="init();initArea();">
<html:form action="/portal/ptl070">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortalSid" />
<html:hidden property="ptl070areaTop" />
<html:hidden property="ptl070areaBottom" />
<html:hidden property="ptl070areaLeft" />
<html:hidden property="ptl070areaCenter" />
<html:hidden property="ptl070areaRight" />
</html:form>


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>
<td width="100%" align="center">
  <!-- 各プラグイン情報レイアウト -->

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%" class="header_white_bg2" valign="top"><img src="../main/images/header_main_01.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.main" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="ptl.ptl070.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    </td>

    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt="">
    </td>
    </tr>
    </table>
  </td>
  </tr>
  </table>

  <table width="100%" cellspacing="0" cellpadding="0">
  <tr>
  <td valign="top" class="plt_top">

  <table width="100%" cellspacing="0" cellpadding="0">
  <!-- 上 -->
  <tr>
  <td width="100%" id="mainScreenListTop">
  <logic:notEmpty name="ptl070Form" property="ptl070topList">

    <logic:iterate id="topModel" name="ptl070Form" property="ptl070topList" indexId="idxtop">

      <logic:equal name="topModel" property="partsKbn" value="3">
        <div id="infomation"><%@ include file="/WEB-INF/plugin/portal/jsp/ptl070_information.jsp" %></div>
      </logic:equal>

      <logic:equal name="topModel" property="partsKbn" value="2">
      <!-- プラグインポートレット -->
        <div class="portlet"><span id="<bean:write name="topModel" property="ptpItemid" />"><bean:write name="topModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
      </logic:equal>

      <logic:equal name="topModel" property="partsKbn" value="1">
      <!-- プラグイン -->
        <div class="portlet"><span id="<bean:write name='topModel' property='pluginId' />_<bean:write name='topModel' property='id' />"><bean:write name="topModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
      </logic:equal>

      <logic:equal name="topModel" property="partsKbn" value="0">
      <!-- ポートレット -->
      <bean:define id="border" name="topModel" property="ptlBorderKbn" />
        <% String t_borderKbn = (border).toString(); %>
        <% String t_className = "";%>
        <% if (t_borderKbn.equals("0")) { %>
        <%     t_className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="topModel" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     t_className = "portlet_nonborder portlet_style"; %>
        <% } %>
          <table class="<%= t_className %>" id="<bean:write name='topModel' property='ptpItemid' />" width="100%">
          <tr><td>
            <bean:write name="topModel" property="ptlContent" filter="false"/>
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

      <td width="33%" valign="top" id="mainScreenListLeft" class="plt_left">
      <logic:notEmpty name="ptl070Form" property="ptl070leftList">

      <logic:iterate id="leftModel" name="ptl070Form" property="ptl070leftList" indexId="idxleft">

        <logic:equal name="leftModel" property="partsKbn" value="3">
          <div id="infomation"><%@ include file="/WEB-INF/plugin/portal/jsp/ptl070_information.jsp" %></div>
        </logic:equal>

        <logic:equal name="leftModel" property="partsKbn" value="2">
        <!-- プラグインポートレット -->
          <div class="portlet"><span id="<bean:write name="leftModel" property="ptpItemid" />"><bean:write name="leftModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
        </logic:equal>

        <logic:equal name="leftModel" property="partsKbn" value="1">
        <!-- プラグイン -->
          <div class="portlet"><span id="<bean:write name='leftModel' property='pluginId' />_<bean:write name='leftModel' property='id' />"><bean:write name="leftModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
        </logic:equal>

        <logic:equal name="leftModel" property="partsKbn" value="0">
        <!-- ポートレット -->
      <bean:define id="border" name="leftModel" property="ptlBorderKbn" />
        <% String l_borderKbn = (border).toString(); %>
        <% String l_className = "";%>
        <% if (l_borderKbn.equals("0")) { %>
        <%     l_className = "portlet_border portlet_style"; %>
          <table width="100%" class="ptl_title_tbl">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="leftModel" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     l_className = "portlet_nonborder portlet_style"; %>
        <% } %>
          <table class="<%= l_className %>" id="<bean:write name='leftModel' property='ptpItemid' />" width="100%">
          <tr><td>
            <bean:write name="leftModel" property="ptlContent" filter="false"/>
          </td></tr>
          </table>
        </logic:equal>

      </logic:iterate>
    </logic:notEmpty>

    </td>

    <!-- 中央 -->
    <td width="33%" valign="top" id="mainScreenListCenter" class="plt_center">
    <logic:notEmpty name="ptl070Form" property="ptl070centerList">

    <logic:iterate id="centerModel" name="ptl070Form" property="ptl070centerList" indexId="idxcenter">

        <logic:equal name="centerModel" property="partsKbn" value="3">
          <div id="infomation"><%@ include file="/WEB-INF/plugin/portal/jsp/ptl070_information.jsp" %></div>
        </logic:equal>

        <logic:equal name="centerModel" property="partsKbn" value="2">
        <!-- プラグインポートレット -->
          <div class="portlet"><span id="<bean:write name="centerModel" property="ptpItemid" />"><bean:write name="centerModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
        </logic:equal>

        <logic:equal name="centerModel" property="partsKbn" value="1">
        <!-- プラグイン -->
          <div class="portlet"><span id="<bean:write name='centerModel' property='pluginId' />_<bean:write name='centerModel' property='id' />"><bean:write name="centerModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
        </logic:equal>

        <logic:equal name="centerModel" property="partsKbn" value="0">
        <!-- ポートレット -->
        <bean:define id="border" name="centerModel" property="ptlBorderKbn" />
        <% String c_borderKbn = (border).toString(); %>
        <% String c_className = "";%>
        <% if (c_borderKbn.equals("0")) { %>
        <%     c_className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="centerModel" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     c_className = "portlet_nonborder portlet_style"; %>
        <% } %>
          <table class="<%= c_className %>" id="<bean:write name='centerModel' property='ptpItemid' />" width="100%">
          <tr><td>
            <bean:write name="centerModel" property="ptlContent" filter="false"/>
          </td></tr>
          </table>
        </logic:equal>

    </logic:iterate>
    </logic:notEmpty>

    </td>

    <!-- 右 -->

    <td width="33%" valign="top" id="mainScreenListRight" class="plt_right">
    <logic:notEmpty name="ptl070Form" property="ptl070rightList">
      <logic:iterate id="rightModel" name="ptl070Form" property="ptl070rightList" indexId="idxright">

        <logic:equal name="rightModel" property="partsKbn" value="3">
          <div id="infomation"><%@ include file="/WEB-INF/plugin/portal/jsp/ptl070_information.jsp" %></div>
        </logic:equal>

        <logic:equal name="rightModel" property="partsKbn" value="2">
        <!-- プラグインポートレット -->
          <div class="portlet"><span id="<bean:write name="rightModel" property="ptpItemid" />"><bean:write name="rightModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
        </logic:equal>

        <logic:equal name="rightModel" property="partsKbn" value="1">
        <!-- プラグイン -->
          <div class="portlet"><span id="<bean:write name='rightModel' property='pluginId' />_<bean:write name='rightModel' property='id' />"><bean:write name="rightModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
        </logic:equal>

        <logic:equal name="rightModel" property="partsKbn" value="0">
        <!-- ポートレット -->
      <bean:define id="r_border" name="rightModel" property="ptlBorderKbn" />
        <% String r_borderKbn = (r_border).toString(); %>
        <% String r_className = "";%>
        <% if (r_borderKbn.equals("0")) { %>
        <%     r_className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="rightModel" property="ptlTitle" /></td>
          </tr>
          </table>
        <% } else { %>
        <%     r_className = "portlet_nonborder portlet_style"; %>
        <% } %>
          <table class="<%= r_className %>" id="<bean:write name='rightModel' property='ptpItemid' />" width="100%">
          <tr><td>
            <bean:write name="rightModel" property="ptlContent" filter="false"/>
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


  <logic:notEmpty name="ptl070Form" property="ptl070bottomList">
  <tr>
  <td width="100%" align="center" valign="top" id="mainScreenListBottom">
    <logic:iterate id="bottomModel" name="ptl070Form" property="ptl070bottomList" indexId="idxbottom">

    <logic:equal name="bottomModel" property="partsKbn" value="3">
      <div id="infomation"><%@ include file="/WEB-INF/plugin/portal/jsp/ptl070_information.jsp" %></div>
    </logic:equal>

    <logic:equal name="bottomModel" property="partsKbn" value="2">
    <!-- プラグインポートレット -->
      <div class="portlet"><span id="<bean:write name="bottomModel" property="ptpItemid" />"><bean:write name="bottomModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
    </logic:equal>

    <logic:equal name="bottomModel" property="partsKbn" value="1">
    <!-- プラグイン -->
      <div class="portlet"><span id="<bean:write name='bottomModel' property='pluginId' />_<bean:write name='bottomModel' property='id' />"><bean:write name="bottomModel" property="pluginName" /><gsmsg:write key="cmn.reload" />...</span></div>
    </logic:equal>

    <logic:equal name="bottomModel" property="partsKbn" value="0">
    <!-- ポートレット -->
      <bean:define id="b_border" name="bottomModel" property="ptlBorderKbn" />
      <% String b_borderKbn = (b_border).toString(); %>
      <% String b_className = "";%>
      <% if (b_borderKbn.equals("0")) { %>
      <%     b_className = "portlet_border portlet_style"; %>
          <table width="100%">
          <tr>
            <td class="header_7D91BD_left plt_title"><bean:write name="bottomModel" property="ptlTitle" /></td>
          </tr>
          </table>
      <% } else { %>
      <%     b_className = "portlet_nonborder portlet_style"; %>
      <% } %>
          <table class="<%= b_className %>" id="<bean:write name='bottomModel' property='ptpItemid' />" width="100%">
          <tr><td>
            <bean:write name="bottomModel" property="ptlContent" filter="false"/>
          </td></tr>
          </table>
    </logic:equal>

    </logic:iterate>
  </td>
  </tr>
  </logic:notEmpty>

  </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>


<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>

</html:html>