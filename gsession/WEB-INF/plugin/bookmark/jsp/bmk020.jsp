<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="bmk.bmk020.02" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/bookmark/bmk020">

<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<html:hidden property="bmk070ReturnPage" />
<html:hidden property="bmk070Page" />
<html:hidden property="bmk070PageTop" />
<html:hidden property="bmk070PageBottom" />
<html:hidden property="bmk070OrderKey" />
<html:hidden property="bmk070SortKey" />
<html:hidden property="bmk030InitFlg" />

<html:hidden property="bmk080Page" />
<html:hidden property="bmk080PageTop" />
<html:hidden property="bmk080PageBottom" />

<logic:notEmpty name="bmk020Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk020Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="bmk150PageNum" />

<!--　BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bmk.bmk020.01" /> ]</td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="OK" class="btn_ok1" onClick="return buttonPush('bmk020pushOk');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('bmk020pushBack');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <!-- メッセージ -->
  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/><br></td>
    </tr>
    </table>
  </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1">URL</span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="90%">
    <html:text maxlength="1000" property="bmk020url" style="width:635px;"/>
    </tr>
    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" value="OK" class="btn_ok1" onClick="return buttonPush('bmk020pushOk');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('bmk020pushBack');">
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