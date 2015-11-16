<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<% int cmdmode_add = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_ADD; %>
<% int cmdmode_edit = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT; %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng100.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.rng100.01" /></title>
</head>

<body class="body_03">

<html:form action="ringi/rng100">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />
<html:hidden property="rctSid" />
<html:hidden property="rngRctCmdMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.rng100.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="rng.rng100.01" />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addTemplate(<%= cmdmode_add %>);">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backMenu');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="rng.rng100.01" />"></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td><span class="text_base"><gsmsg:write key="rng.rng100.02" /></span></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th align="center" class="td_type24" width="100%"><span class="text_tlw4"><gsmsg:write key="rng.25" /></span></th>
    </tr>

    <logic:notEmpty name="rng100Form" property="rng100keiroTemplateList">
    <% String[] tdColors = {"td_type1", "td_type25_2"}; %>

    <logic:iterate id="keiroTempData" name="rng100Form" property="rng100keiroTemplateList" indexId="idx">
    <tr>
    <td align="left" class="<%= tdColors[idx.intValue() % 2] %>" width="100%">
      <a href="#" onClick="return selectChannelTemplate('<bean:write name="keiroTempData" property="rctSid" />', '<%= cmdmode_edit %>')"><span class="text_link"><bean:write name="keiroTempData" property="rctName" /></span></a>
    </td>
    </tr>
    </logic:iterate>

    </logic:notEmpty>
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