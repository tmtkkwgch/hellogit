<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
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
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<!--[if IE]><script type="text/javascript" src="../common/js/graph_circle_1_0_2/excanvas/excanvas.js"></script><![endif]-->
<script language="JavaScript" src="../common/js/jplot/jquery.jqplot.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.barRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="javascript" src="../common/js/jplot/jqplot.pieRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.highlighter.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.categoryAxisRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.canvasAxisTickRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>


<script language="JavaScript" src="../common/js/jplot/jqplot.canvasTextRenderer.min.js?<%= GSConst.VERSION_PARAM %>"></script>


<script language="JavaScript" src="../common/js/jplot/jqplot.pointLabels.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jplot/jqplot.cursor.min.js?<%= GSConst.VERSION_PARAM %>"></script>




<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery-ui-1.8.16.custom.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/i18n/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>'></script>



<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../nippou/js/ntp220.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.exfixed.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/js/jplot/css/jquery.jqplot.min.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/js/jquery-ui-1.8.16/development-bundle/themes/base/jquery.ui.all.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/js/jquery-ui-1.8.16/ui/dialog/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<style type="text/css">
.textfield { position: relative; margin: 0 0 0 0;}
.textfield label { position: absolute; top: 4; left: 6; font-size:12px; color:#333333;}
.textfield br {display: none;}
</style>

</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp220">

<input type="hidden" name="CMD" value="">
<html:hidden property="dspMod" />
<html:hidden property="ntp220mode" />
<html:hidden property="ntp220DateFrStr" />
<html:hidden property="ntp220DateToStr" />

<html:hidden property="ntp220State" />
<html:hidden property="ntp220AnkenState" />

<html:hidden property="ntp220NowSelParentId" />
<html:hidden property="ntp220NowSelChildId" />

<!--@BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="ntp.13" /> ]</td>
  <td width="100%" class="header_white_bg">

  <%-- input type="button" value="<gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="buttonPush2('anken');" --%>

  <logic:equal name="ntp220Form" property="adminKbn" value="1">
    <input type="button" name="btn_admin_tool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush2('ktool');">
  </logic:equal>
    <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush2('pset');">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
  </tr>
  </table>

<img src="../schedule/images/spacer.gif" width="1px" height="5px" border="0">


<table cellpadding="1px" width="100%">
<tr>
<td>

    <table cellpadding="0" cellspacing="0" class="menu_table">

      <tr>

        <td valign="top" width="16%" class="menu_space_area_both">
          <table width="100%" height="100%" cellpadding="0" cellspacing="0">

            <tr class="menu_not_select_tr" class="" onClick="buttonPush2('nippou');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_single.gif" alt="<gsmsg:write key="ntp.1" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.1" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush2('anken');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_anken.gif" alt="<gsmsg:write key="ntp.11" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.11" /></span>
              </td>
            </tr>

            <tr class="menu_not_select_tr" onClick="buttonPush('target');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_target.gif" alt="<gsmsg:write key="ntp.12" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" width="82%" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.12" /></span>
              </td>
            </tr>

            <tr class="menu_select_tr">
              <td class="menu_select_icon">
                <img src="../nippou/images/menu_icon_bunseki.gif" alt="<gsmsg:write key="ntp.13" />" />
              </td>
              <td class="menu_select_text" nowrap>
                <span class="timeline_menu"><gsmsg:write key="ntp.13" /></span>
              </td>
              <td class="menu_select_text" valign="middle" nowrap>
                <div class="menu_select_bg" style="position:relative;left:2px;height:40px">&nbsp;</div>
              </td>
            </tr>

            <logic:equal name="ntp220Form" property="adminKbn" value="1">
            <tr class="menu_not_select_tr" onClick="buttonPush('masta');">
              <td class="menu_not_select_icon">
                <img src="../nippou/images/menu_icon_mente.gif" alt="<gsmsg:write key="ntp.14" />" />
              </td>
              <td class="menu_not_select_text" colspan="2" style="padding-top:7px;padding-right:5px;" valign="top" nowrap>
                <span class="timeline_menu2"><gsmsg:write key="ntp.14" /></span>
              </td>
            </tr>
            </logic:equal>


            <tr>
              <td class="menu_space_area_left"></td>
              <td class="menu_space_area_right" colspan="2">&nbsp;</td>
            </tr>


          </table>
        </td>

        <td valign="top" width="84%">

          <logic:equal name="ntp220Form" property="ntp220mode" value="0">
          <%@ include file="/WEB-INF/plugin/nippou/jsp/ntp220_shukei.jsp" %>
          </logic:equal>
          <logic:equal name="ntp220Form" property="ntp220mode" value="1">
          <%@ include file="/WEB-INF/plugin/nippou/jsp/ntp220_kojin.jsp" %>
          </logic:equal>

        </td>

      </tr>

    </table>

</td>

</tr>

</table>

<span id="tooltip_area"></span>

<div id="searchBtnResultPop" style="height:550px;overflow-y:hidden;display:none;" title="<gsmsg:write key="bbs.bbs041.2" />">
  <p>
    <div id="searchBtnResultArea" style="height:450px;">
    </div>
  </p>
</div>

<input type="hidden" name="ntp220pageNum" value="1" />

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>