<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%-- CMD定数 --%>
<%  String tabProjectClick = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_TAB_PROJECT;
    String tabTodoClick    = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_TAB_TODO;
    String prjAdd          = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PRJ_ADD;
    String prjMain         = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PRJ_TITLE_CLICK;
    String prjSearch       = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PRJ_SEARCH;
    String todoAdd         = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_TODO_ADD;
    String todoEdit         = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_TODO_EDIT;
    String todoRef         = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_TODO_TITLE_CLICK;
    String todoSearch      = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_TODO_SEARCH;
    String adminConf       = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_ADMIN_CONFIG;
    String personalConf    = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PERSONAL_CONFIG;
    String prev            = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PAGE_PREVEW;
    String next            = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PAGE_NEXT;
    String init            = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_PAGE_INIT;  %>


<%-- 処理(タブ)モード --%>
<%  String modeProject = jp.groupsession.v2.prj.GSConstProject.MODE_PROJECT;
    String modeTodo    = jp.groupsession.v2.prj.GSConstProject.MODE_TODO;  %>

<%-- ソートオーダー --%>
<%  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
    int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;  %>

<%  String prj010 = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ010;
    String search_ok = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEARCH_FLG_OK);
    String mode_add = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;
    String mode_edit = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_EDIT;  %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../project/css/project.css" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery-ui-1.8.16.custom.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../project/js/prj010.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<theme:css filename="theme.css"/>
<style type="text/css">

  #chart {
    margin:auto;
    width: 150px;
    height: 150px;
  }

  #legend {
    text-align:left;
    list-style: none;
    padding: 5px;
    margin-left: 5px;
    background-color: #ffffff;
  }

  #legend li {
    height: 1.2em;
    padding-left: 15px;
    margin-bottom: 4px;
    list-style: none;
    font-family: Verdana,Arial,sans-serif;
    color: #333333;
    font-size: 90%;
  }

  .categoryName {
    display: block;
    padding-left: 4px;
    background-color: #ffffff;
  }

  .kanryo {
    background-color: #0c5bc7;
  }

  .sinkotyu {
    background-color: #d6d4fe;
  }

  .mikanryo {
    background-color: #828282;
  }

</style>

</head>

<html:form action="/project/prj010">
<input type="hidden" name="helpPrm" value="<bean:write name="prj010Form" property="prj010cmdMode" />">
<input type="hidden" name="CMD" id="CMD" value="">
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010Init" />
<html:hidden property="prj010IeFlg" />
<html:hidden property="todoKanryoCnt" />
<html:hidden property="todoMikanryoCnt" />
<html:hidden property="todoSinkotyuCnt" />
<html:hidden property="prj070scrId" value="<%= prj010 %>" />
<html:hidden property="prj070searchFlg" value="<%= search_ok %>" />
<html:hidden property="prj040searchFlg" value="<%= search_ok %>" />
<html:hidden property="prj050scrId" value="<%= prj010 %>" />
<html:hidden property="prj050cmdMode" />
<html:hidden property="prj050prjSid" />
<html:hidden property="prj050todoSid" />
<html:hidden property="prj020scrId" value="<%= prj010 %>" />
<html:hidden property="prj020cmdMode" value="<%= mode_add %>" />
<html:hidden property="prj030scrId" value="<%= prj010 %>" />
<html:hidden property="prj030prjSid" value="" />
<html:hidden property="prj060scrId" value="<%= prj010 %>" />
<html:hidden property="prj060prjSid" />
<html:hidden property="prj060todoSid" />
<html:hidden property="prjTmpMode" />
<input type="hidden" name="prjmvComment">

<bean:define id="orderKey" name="prj010Form" property="prj010order" />
<bean:define id="sortKbn" name="prj010Form" property="prj010sort" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>


<body class="body_03">
<div id="project_todoList">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.prj010.2" /> ]</td>
  <td width="100%" class="header_white_bg">
    <logic:equal name="prj010Form" property="admin" value="true">
    <input type="button" name="btn_prjadd" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('<%= adminConf %>');">
    </logic:equal>
    <input type="button" name="btn_prjadd" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('<%= personalConf %>');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <br>

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td width="22%" style="text-align:center;" valign="top" nowrap>

    <table cellpadding="0" class="tl0" width="100%">
    <tr>
    <td class="prjlist_title_back" nowrap>
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td align="left" width="0%"><img src="../project/images/menu_icon_single.gif" alt="<gsmsg:write key="cmn.project" />" class="img_bottom img_border img_menu_icon_single" style="margin-left: 3px;"></img></td>
      <td align="left" nowrap width="100%"><span class="prjlist_title_text"><gsmsg:write key="cmn.join.project" /></span></td>
      <td align="right" width="0%" nowrap><input type="button" class=btn_list_n3 value="<gsmsg:write key="cmn.list" />" onclick="buttonPush('<%= prjSearch %>');"></td>
      </tr>
      </table>
    </td>
    </tr>

    <logic:notEmpty name="prj010Form" property="allProjectList" scope="request">
      <logic:iterate id="prjMdl" name="prj010Form" property="allProjectList" scope="request" indexId="idx">
        <bean:define id="backclass" value="td_prj_list" />
        <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
        <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
        <tr class="<%= String.valueOf(back) %>">
        <td>
        <logic:equal name="prjMdl" property="prjBinSid" value="0">
          <img src="../project/images/prj_icon.gif" name="pitctImage" width="20" height="20" alt="<gsmsg:write key="cmn.icon" />" border="1" class="prj_img_ico">
        </logic:equal>
        <logic:notEqual name="prjMdl" property="prjBinSid" value="0">
          <img src="../project/prj010.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prjMdl" property="projectSid" />&prj010PrjBinSid=<bean:write name="prjMdl" property="prjBinSid" />" name="pitctImage" width="20" height="20" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');" class="prj_img_ico">
        </logic:notEqual>
          <a href="javascript:void(0)" onClick="return viewPoject('<%= prjMain %>', '<bean:write name="prjMdl" property="projectSid" />');"><bean:write name="prjMdl" property="projectName" /></a></td>
        </tr>
      </logic:iterate>
    </logic:notEmpty>
    </table>

    <logic:equal name="prj010Form" property="prj010cmdMode" value="<%= modeTodo %>">

      <bean:define id="kanryo" name="prj010Form" property="todoKanryoCnt" />
      <bean:define id="mikanryo" name="prj010Form" property="todoMikanryoCnt" />
      <bean:define id="sinkotyu" name="prj010Form" property="todoSinkotyuCnt" />

      <%
        int kanryoCnt = ((Integer) kanryo).intValue();
        int mikanryoCnt = ((Integer) mikanryo).intValue();
        int sinkotyuCnt = ((Integer) sinkotyu).intValue();
        int allCnt = kanryoCnt + mikanryoCnt + sinkotyuCnt;

        if (allCnt > 0) {
      %>
      <logic:notEqual name="prj010Form" property="prj010IeFlg" value="0">
        <script type="text/javascript" src="../common/js/graph_circle_1_0_2/excanvas/excanvas.js?<%= GSConst.VERSION_PARAM %>"></script>
      </logic:notEqual>
      <script type="text/javascript" src="../common/js/graph_circle_1_0_2/graph/circle.js?<%= GSConst.VERSION_PARAM %>"></script>


      <script type="text/javascript">
      window.onload = function() {
          var cg = new html5jp.graph.circle("sample");
          if( ! cg ) { return; }
          var items = [
            ["<gsmsg:write key="cmn.complete" />", <bean:write name="kanryo" />, "#0c5bc7"],
            ["<gsmsg:write key="rng.application.ongoing" />", <bean:write name="sinkotyu" />, "#d6d4fe"],
            ["<gsmsg:write key="project.prj010.8" />", <bean:write name="mikanryo" />, "#828282"]
          ];
          var params = {
            backgroundColor: "#ffffff",
            shadow: false,
            caption: true,
            legend: false,
            captionNum: false,
            startAngle: -45
          };
          cg.draw(items, params);
      };
      </script>

      <br>

      <table cellpadding="0" width="100%" class="tl0">
      <tr>
      <td class="prjlist_title_back" nowrap>
        <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
        <td align="left"><span class="prjlist_title_text"><gsmsg:write key="project.prj010.5" /></span></td>
        </tr>
        </table>
      </td>
      </tr>
      <tr>
      <td class="prj_graph" align="center">
        <div><canvas width="250" height="200" id="sample"></canvas></div>
      </td>
      </tr>
      <tr>
      <td class="prj_graph">

        <ul id="legend">
          <li class="kanryo"><span class="categoryName">&nbsp;<gsmsg:write key="cmn.complete" />（<bean:write name="kanryo" /><gsmsg:write key="cmn.number" />）</span></li>
          <li class="sinkotyu"><span class="categoryName">&nbsp;<gsmsg:write key="rng.application.ongoing" />（<bean:write name="sinkotyu" /><gsmsg:write key="cmn.number" />）</span></li>
          <li class="mikanryo"><span class="categoryName">&nbsp;<gsmsg:write key="project.prj010.8" />（<bean:write name="mikanryo" /><gsmsg:write key="cmn.number" />）</span></li>
        </ul>

      </td>
      </tr>
      </table>

      <% } %>

    </logic:equal>

  </td>
  <td width="1%" valign="top" nowrap>&nbsp;</td>
  <td width="77%" valign="top" nowrap>

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td width="77%" valign="top">

      <table cellpadding="0" cellspacing="0" border="0" width="100%">

      <logic:messagesPresent message="false">
      <tr><td align="left"><html:errors/></td></tr>
      </logic:messagesPresent>

      <tr>
      <td width="100%">

      <logic:equal name="prj010Form" property="prj010cmdMode" value="<%= modeTodo %>">
    <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
    <tr>

        <td align="left" class="tab_bg_image_1_on" width="100px" nowrap>
          <div class="tab_text_area">
            <a href="javascript:changeTab('<%= tabTodoClick %>');">TODO</a>
          </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td align="left" class="tab_bg_image_1_off" width="107px" nowrap>
          <div class="tab_text_area_right">
            <a href="javascript:changeTab('<%= tabProjectClick %>');"><gsmsg:write key="cmn.project" /></a>
          </div>
        </td>
        <td class="tab_bg_underbar" width="100%" nowrap>&nbsp;</td>
        </tr>
        </table>
      </logic:equal>

      <logic:equal name="prj010Form" property="prj010cmdMode" value="<%= modeProject %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td align="left" class="tab_bg_image_1_off" width="100px" nowrap>
          <div class="tab_text_area_right">
            <a href="javascript:changeTab('<%= tabTodoClick %>');">TODO</a>
          </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td align="left" class="tab_bg_image_1_on" width="107px" nowrap>
          <div class="tab_text_area">
            <a href="javascript:changeTab('<%= tabProjectClick %>');"><gsmsg:write key="cmn.project" /></a>
          </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
          <!-- ページング -->
          <div align="right">
            <bean:size id="count1" name="prj010Form" property="pageLabel" scope="request" />
            <logic:greaterThan name="count1" value="1">
            <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_pageBtn" onClick="buttonPush('prev');">
            <html:select property="prj010page1" onchange="changePage(1);" styleClass="text_i">
              <html:optionsCollection name="prj010Form" property="pageLabel" value="value" label="label" />
            </html:select>
            <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_pageBtn" onClick="buttonPush('next');">
            </logic:greaterThan>
        </div>
          </td>
        </tr>
        </table>
      </logic:equal>
      </td>

      <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
      </table>

      <table class="tl0 prj_tbl_base_2" width="100%" cellpadding="0" cellspacing="0">
        <logic:equal name="prj010Form" property="prj010cmdMode" value="<%= modeTodo %>">
          <%@ include file="/WEB-INF/plugin/project/jsp/prj010_sub01.jsp" %>
        </logic:equal>
        <logic:equal name="prj010Form" property="prj010cmdMode" value="<%= modeProject %>">
          <%@ include file="/WEB-INF/plugin/project/jsp/prj010_sub02.jsp" %>
        </logic:equal>
      </table>

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
      <td width="100%" align="right" valign="bottom">
        <bean:size id="count1" name="prj010Form" property="pageLabel" scope="request" />
        <logic:greaterThan name="count1" value="1">
          <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_pageBtn" onClick="buttonPush('prev');">
          <html:select property="prj010page2" onchange="changePage(2);" styleClass="text_i">
            <html:optionsCollection name="prj010Form" property="pageLabel" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_pageBtn" onClick="buttonPush('next');">
        </logic:greaterThan>
      </td>
      <td width="0%"><img src="../common/images/damy.gif" width="10" height="34" border="0" alt=""></td>
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>

<map name="imagemap">
  <area shape="rect" coords="0,3,97,26" href="javascript:changeTab('<%= tabTodoClick %>');" alt="TODO">
  <area shape="rect" coords="104,3,197,26" href="javascript:changeTab('<%= tabProjectClick %>');" alt="<gsmsg:write key="cmn.project" />">
</map>
</div>

</body>
</html:html>