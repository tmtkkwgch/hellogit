<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
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
    padding: 1px;
    margin-left: 5px;
    margin-bottom: 0px!important;
    background-color: #ffffff;
  }

  #legend li {
    height: 1.2em;
    padding-left: 15px;
    margin-bottom: 3px;
    list-style: none;
    font-family: Verdana,Arial,sans-serif;
    color: #333333;
    font-size: 90%;
  }

  .categoryName {
    display: block;
    padding-left: 4px;
    background-color: #ffffff;
    height: 1.2em;
    vertical-align: middle;
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

<html:form action="/project/prjptl011">
<input type="hidden" name="prjPtl010PrjSid" value="">

<body class="body_03">
<table align="center" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td width="22%" style="text-align:center; margin:0px; padding:0px;" valign="top" nowrap>

      <bean:define id="kanryo" name="prjptl011Form" property="todoKanryoCnt" />
      <bean:define id="mikanryo" name="prjptl011Form" property="todoMikanryoCnt" />
      <bean:define id="sinkotyu" name="prjptl011Form" property="todoSinkotyuCnt" />

      <script type="text/javascript" src="../common/js/graph_circle_1_0_2/excanvas/excanvas.js?<%= GSConst.VERSION_PARAM %>"></script>

      <script type="text/javascript" src="../common/js/graph_circle_1_0_2/graph/circle.js?<%= GSConst.VERSION_PARAM %>"></script>
      <script type="text/javascript">
      $(function(){
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
      });
      </script>
      <table cellpadding="0" width="100%" class="tl0">
      <tr>
        <td class="header_7D91BD_left">
          <img src="../project/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.project" />">
          <a href="../project/prj030.do?prj030scrId=main&prj030prjSid=<bean:write name="prjptl011Form" property="prjPtl010PrjSid" />" target="_parent"><span class="text_base3"><gsmsg:write key="project.prj010.5" /> [ <bean:write name="prjptl011Form" property="prjPtl010prjName" /> ]</span></a>
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
  </td>
  </tr>
  </table>
</td>
</tr>
</table>
</body>
</html:form>
</html:html>