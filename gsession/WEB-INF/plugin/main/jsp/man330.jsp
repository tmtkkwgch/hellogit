<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String modeExport = String.valueOf(jp.groupsession.v2.man.GSConstMain.MODE_EXPORT); %>
<% String modeImport = String.valueOf(jp.groupsession.v2.man.GSConstMain.MODE_IMPORT); %>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man330.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="main.memberships.conf" /></title>
</head>
<body class="body_03" onunload="windowClose();">
<!--　BODY -->

<html:form action="/main/man330">

<input type="hidden" name="CMD" value="">
<html:hidden property="man330cmdMode" />

<input type="hidden" name="helpPrm" value="<bean:write name="man330Form" property="man330cmdMode" />">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

    <table width="70%" cellpadding="0" cellspacing="0">
    <tr>
    <td align="left">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
      <td width="100%" class="header_ktool_bg_text2">
        <logic:equal name="man330Form" property="man330cmdMode" value="<%= modeExport %>">
          [<gsmsg:write key="main.memberships.conf" /> <gsmsg:write key="cmn.export" />]
        </logic:equal>
        <logic:notEqual name="man330Form" property="man330cmdMode" value="<%= modeExport %>">
          [<gsmsg:write key="main.memberships.conf" /> <gsmsg:write key="cmn.import" />]
        </logic:notEqual>
      </td>
      </tr>
      </table>

      <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="right" nowrap>
        <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('ktool');">
      </td>
      </tr>
      </table>

      <logic:messagesPresent message="false">
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr><td width="100%"><html:errors /></td></tr>
      </table>
      </logic:messagesPresent>

      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" nowrap>

      <logic:equal name="man330Form" property="man330cmdMode" value="<%= modeExport %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
              <a href="javascript:changeTab('export');"><gsmsg:write key="cmn.export" /></a>
            </div>
          </td>
          <td class="tab_space" nowrap>&nbsp;</td>
          <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
              <a href="javascript:changeTab('import');"><gsmsg:write key="cmn.import" /></a>
            </div>
          </td>

          <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
      </logic:equal>

      <logic:equal name="man330Form" property="man330cmdMode" value="<%= modeImport %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
          <div class="tab_text_area_right">
            <a href="javascript:changeTab('export');"><gsmsg:write key="cmn.export" /></a>
          </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
          <div class="tab_text_area">
            <a href="javascript:changeTab('import');"><gsmsg:write key="cmn.import" /></a>
          </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
      </logic:equal>
      </td>

      <td class="smail_tab_top_bg" width="100%" align="right" valign="top" nowrap></td>
      <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
      </table>

      <logic:equal name="man330Form" property="man330cmdMode" value="<%= modeExport %>">
        <%@ include file="/WEB-INF/plugin/main/jsp/man330_sub01.jsp" %>
      </logic:equal>
      <logic:equal name="man330Form" property="man330cmdMode" value="<%= modeImport %>">
        <%@ include file="/WEB-INF/plugin/main/jsp/man330_sub02.jsp" %>
      </logic:equal>
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