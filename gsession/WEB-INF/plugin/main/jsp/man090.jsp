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
<title>[Group Session] <gsmsg:write key="main.man002.57" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../main/js/man090.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/main/man090">
<html:hidden property="logName" />
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.57" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('backMenu')">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <span class="text_base"><gsmsg:write key="main.man090.1" /></span>
    <table class="tl0 table_td_border" cellpadding="0" cellspacing="0" border="0" width="100%">

    <tr class="td_type24">
    <th width="65%"><gsmsg:write key="cmn.logfile" /></th>
    <th width="35%"><gsmsg:write key="cmn.update.day.hour" /></th>
    </tr>
    <% int length = 0; %>
    <logic:notEmpty name="man090Form" property="logList" scope="request">
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type3"}; %>
    <logic:iterate id="item" name="man090Form" property="logList" scope="request" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>
      <tr>
      <td class="<%= tdColor %>" align="left"><a href="javascript:void(0)" onclick="return onSubmitLog('logdownload', '<bean:write name="item" property="escHttpLogName" />')"><span class="text_link2"><bean:write name="item" property="httpLogName" /><bean:write name="item" property="httpLogSize" /></span></a></td>
      <td class="<%= tdColor %>" align="center"><span class="text_base"><bean:write name="item" property="logLastUpDate" /></span></td>
      </tr>
    <% length++; %>
    </logic:iterate>

    </logic:notEmpty>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
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