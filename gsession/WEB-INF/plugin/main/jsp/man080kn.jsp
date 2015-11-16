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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man080kn.1" /></title>
</head>
<body class="body_03">
<!--　BODY -->

<% String interval_noset = String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_NOSET); %>
<% String interval_daily = String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_DAILY); %>
<% String interval_weekly = String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_WEEKLY); %>
<% String interval_monthly = String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_MONTHLY); %>

<html:form action="/main/man080kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="man080Interval" />
<html:hidden property="man080dow" />
<html:hidden property="man080weekmonth" />
<html:hidden property="man080monthdow" />
<html:hidden property="man080generation" />
<html:hidden property="man080zipOutputKbn" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man080kn.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('entry');">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man080.1" /></span></td>
    <td align="left" class="td_wt2" width="100%">

    <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
    <% String[] dowList = {gsMsg.getMessage(request, "cmn.every.sunday"), gsMsg.getMessage(request, "cmn.every.monday"), gsMsg.getMessage(request, "cmn.every.tuesday"), gsMsg.getMessage(request, "cmn.every.wednesday"), gsMsg.getMessage(request, "cmn.every.thursday"), gsMsg.getMessage(request, "cmn.every.friday"), gsMsg.getMessage(request, "cmn.every.saturday")}; %>

    <logic:equal name="man080knForm" property="man080Interval" value="<%= interval_noset %>">
    <gsmsg:write key="main.man080kn.2" />
    </logic:equal>

    <logic:equal name="man080knForm" property="man080Interval" value="<%= interval_daily %>">
    <gsmsg:write key="main.man080kn.3" />
    </logic:equal>

    <logic:equal name="man080knForm" property="man080Interval" value="<%= interval_weekly %>">
      <bean:define id="dow" name="man080knForm" property="man080dow" />
      <gsmsg:write key="main.man080kn.4" arg0="<%= dowList[((Integer) dow).intValue() - 1] %>" />
    </logic:equal>

    <logic:equal name="man080knForm" property="man080Interval" value="<%= interval_monthly %>">
    <% String[] weekMonthList = {gsMsg.getMessage(request, "cmn.no.1"), gsMsg.getMessage(request, "cmn.no.2"), gsMsg.getMessage(request, "cmn.no.3"), gsMsg.getMessage(request, "cmn.no.4"), gsMsg.getMessage(request, "cmn.no.5")}; %>
    <bean:define id="dow" name="man080knForm" property="man080monthdow" />
    <bean:define id="weekmonth" name="man080knForm" property="man080weekmonth" />
    <gsmsg:write key="cmn.monthly.2" /> <%= weekMonthList[((Integer) weekmonth).intValue() - 1] %> <%= dowList[((Integer) dow).intValue() - 1] %><gsmsg:write key="main.man080kn.4" />
    </logic:equal>
    </td>
    </tr>

    <bean:define id="generation" name="man080knForm" property="man080generation" />
    <% String[] generationList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; %>

    <logic:notEqual name="man080knForm" property="man080Interval" value="<%= interval_noset %>">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="man.number.generations" /></span></td>
    <td align="left" class="td_wt2" width="100%">
    <gsmsg:write key="main.man080kn.20" arg0="<%= generationList[((Integer) generation).intValue() - 1] %>" />
    </td>
    </tr>
    </logic:notEqual>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.output" /></span></td>
    <td align="left" class="td_wt2" width="100%">
    <logic:equal name="man080knForm" property="man080zipOutputKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.ZIP_BACKUP_FLG_OFF) %>">
      <gsmsg:write key="main.not.compress" />
    </logic:equal>
    <logic:equal name="man080knForm" property="man080zipOutputKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.ZIP_BACKUP_FLG_ON) %>">
      <gsmsg:write key="main.zip.format.output" />
    </logic:equal>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('entry');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
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