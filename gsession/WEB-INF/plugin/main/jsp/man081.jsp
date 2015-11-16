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
<script language="JavaScript" src="../main/js/man081.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.manual.backup" /></title>
</head>
<body class="body_03">
<!--　BODY -->

<html:form action="/main/man081">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="man081backupFile" value="">

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.manual.backup" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="man.backup.configuration.manual" />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="main.man081.1" />" class="btn_base1_3" onClick="buttonPush('confirm');">
          <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backadmconf');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="man.backup.configuration.manual" />"></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="5" border="0" width="100%">
      <tr>
      <td width="100%" align="right"><input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');"></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <th align="center" class="table_bg_7D91BD" width="54%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.backupfile" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="23%" nowrap><span class="text_tlw"><gsmsg:write key="man.creation.date" /></th>
      <th align="center" class="table_bg_7D91BD" width="15%" nowrap><span class="text_tlw"><gsmsg:write key="main.man080.7" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="7%" nowrap></th>
    </tr>

    <logic:notEmpty name="man081Form" property="fileDataList">
    <% String[] tdClass = {"td_type1", "td_type29"}; %>

    <logic:iterate id="fileData" name="man081Form" property="fileDataList" indexId="idx">

    <tr>
    <td align="left" class="<%= tdClass[idx.intValue() % 2] %>">
      <a href="javascript:void(0)" onclick="return buttonPushWithFileName('download', '<bean:write name="fileData" property="hashFileName" />')"><span class="text_link"><bean:write name="fileData" property="fileName" /></span></a>
    </td>
    <td align="center" class="<%= tdClass[idx.intValue() % 2] %>"><bean:write name="fileData" property="strMakeDate" /></td>
    <td align="right" class="<%= tdClass[idx.intValue() % 2] %>"><bean:write name="fileData" property="fileSize" /></td>
    <td align="right" class="<%= tdClass[idx.intValue() % 2] %>"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n3" onClick="buttonPushWithFileName('delete', '<bean:write name="fileData" property="hashFileName" />');"></td>
    </tr>

    </logic:iterate>

    </logic:notEmpty>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="main.man081.1" />" class="btn_base1_3" onClick="buttonPush('confirm');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backadmconf');">
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