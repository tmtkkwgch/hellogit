<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>


<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%  String back          = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_BACK;
    String kensuEdit     = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_KENSU_EDIT;
    String tempKojin     = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_TMP_KOJIN;
    String dashBoard     = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_DASH_BOARD;
    String projectMain   = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_PRJ_MAIN;
    String projectSch    = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_PRJ_SCH;
    String todoSet       = jp.groupsession.v2.prj.prj080.Prj080Action.CMD_TODO_SET;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">

<html:form action="/project/prj080">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010Init" />
<html:hidden property="selectingProject" />
<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />
<html:hidden property="prjTmpMode" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.project" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('<%= back %>');">
        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="return buttonPush('<%= kensuEdit %>');"><span class="text_link"><gsmsg:write key="cmn.number.display.settings" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="project.prj080.1" /></li></div></dd>
          </dl>
        </td>
      </tr>
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="return buttonPush('<%= tempKojin %>');"><span class="text_link"><gsmsg:write key="project.prj080.2" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="project.prj080.3" /></li></div></dd>
          </dl>
        </td>
      </tr>
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="return buttonPush('<%= dashBoard %>');"><span class="text_link"><gsmsg:write key="project.11" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="project.prj080.5" /></li></div></dd>
          </dl>
        </td>
      </tr>

      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="return buttonPush('<%= todoSet %>');"><span class="text_link"><gsmsg:write key="project.prj080.9" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="project.prj080.10" /></li></div></dd>
          </dl>
        </td>
      </tr>

      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="return buttonPush('<%= projectMain %>');"><span class="text_link"><gsmsg:write key="project.12" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="project.prj080.7" /></li></div></dd>
          </dl>
        </td>
      </tr>
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="return buttonPush('<%= projectSch %>');"><span class="text_link"><gsmsg:write key="schedule.108" /><gsmsg:write key="cmn.display.settings" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="project.prj080.8" /></li></div></dd>
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>