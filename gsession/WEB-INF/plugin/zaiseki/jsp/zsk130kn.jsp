<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="cmn.preferences2" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/zaiseki/zsk130kn">
<input type="hidden" name="CMD">
<html:hidden name="zsk130knForm" property="backScreen" />
<html:hidden name="zsk130knForm" property="selectZifSid" />
<html:hidden name="zsk130knForm" property="uioStatus" />
<html:hidden name="zsk130knForm" property="uioStatusBiko" />
<html:hidden name="zsk130knForm" property="sortKey" />
<html:hidden name="zsk130knForm" property="orderKey" />
<html:hidden name="zsk130knForm" property="zsk130SortKey1" />
<html:hidden name="zsk130knForm" property="zsk130SortKey2" />
<html:hidden name="zsk130knForm" property="zsk130SortOrder1" />
<html:hidden name="zsk130knForm" property="zsk130SortOrder2" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.zsk130kn.01" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('updateZsk130');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backZsk130');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td width="20%" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.sort" /></span>
    </td>
    <td width="80%" align="left" class="td_wt">
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" /><gsmsg:write key="wml.215" /></span>

      <!-- キー1 -->
      <span class="text_base2">
      <bean:write name="zsk130knForm" property="zsk130knSortKey1Name" />&nbsp;
      <logic:equal name="zsk130knForm" property="zsk130SortOrder1" value="0"><gsmsg:write key="cmn.order.asc" /></logic:equal>
      <logic:equal name="zsk130knForm" property="zsk130SortOrder1" value="1"><gsmsg:write key="cmn.order.desc" /></logic:equal>
      </span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" /><gsmsg:write key="wml.215" /></span>
      <!-- キー2 -->
      <span class="text_base2">
      <bean:write name="zsk130knForm" property="zsk130knSortKey2Name" />&nbsp;
      <logic:equal name="zsk130knForm" property="zsk130SortOrder2" value="0"><gsmsg:write key="cmn.order.asc" /></logic:equal>
      <logic:equal name="zsk130knForm" property="zsk130SortOrder2" value="1"><gsmsg:write key="cmn.order.desc" /></logic:equal>
      </span>

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('updateZsk130');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backZsk130');">
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