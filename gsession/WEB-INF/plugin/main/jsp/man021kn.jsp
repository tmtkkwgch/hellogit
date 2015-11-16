<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<gsmsg:define id="pageTitle" msgkey="main.man021kn.1" />
<gsmsg:define id="confirmBtnValue" msgkey="user.59" />
<gsmsg:define id="explanation" msgkey="main.man021kn.2" />
<logic:equal name="man021Form" property="man021CmdMode" value="2">
  <gsmsg:define id="pageTitle2" msgkey="main.man021kn.3" />
  <gsmsg:define id="confirmBtnValue2" msgkey="schedule.43" />
  <gsmsg:define id="explanation2" msgkey="main.man021kn.1" />" />

  <% pageTitle=pageTitle2; %>
  <% confirmBtnValue=confirmBtnValue2; %>
  <% explanation=explanation2; %>
</logic:equal>

<html:html>
<head>
<title>[Group Session] <%= confirmBtnValue %></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../main/js/man021kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/main/man021kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="man020DspYear" />
<html:hidden property="editHolDate" />
<html:hidden property="man021CmdMode" />
<html:hidden property="man021HolMonth" />
<html:hidden property="man021HolDay" />
<html:hidden property="man021HolName" />
<html:hidden property="man021knHolDate" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center">
  <table width="60%">
  <logic:messagesPresent message="false">
   <tr>
    <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td><span class="text_base4"><%= explanation %></span></td>
  </tr>
  <tr>
  <td align="center">
    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td align="left" class="td_type5" colspan="2">
    <span id="lt" class="text_tl1"><%= pageTitle %></span>
    <span id="rt">
     <input type="button" value="<%= confirmBtnValue %>" class="btn_base1" onClick="buttonPush(1);">
     <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_base1" onclick="buttonPush(2);">
    </span>
    </td>
    </tr>
    <tr>
    <th align="center" class="td_gray" width="30%"><gsmsg:write key="cmn.date2" /></th>
    <th align="center" class="td_gray" width="70%"><gsmsg:write key="cmn.holiday.name" /></th>
    </tr>
    <tr>
    <th align="center" class="td_type1" width="30%"><bean:write name="man021knForm" property="man021knHolDate" /></th>
    <td class="td_type1" width="70%"><bean:write name="man021knForm" property="man021HolName" /></td>
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