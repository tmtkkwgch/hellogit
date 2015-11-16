<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<bean:define id="templateCmdMode" name="wml250knForm" property="wmlTemplateCmdMode" type="java.lang.Integer" />
<% boolean editFlg = (templateCmdMode == 1); %>

<html:html>
<head>
<% if (editFlg) { %>
  <title>[Group Session] <gsmsg:write key="wml.wml250kn.02" /></title>
<% } else { %>
  <title>[Group Session] <gsmsg:write key="wml.wml250kn.01" /></title>
<% } %>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml250kn.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml250kn">

<bean:define id="templateKbn" name="wml250knForm" property="wmlMailTemplateKbn" type="java.lang.Integer" />
<bean:define id="accountMode" name="wml250knForm" property="wmlAccountMode" type="java.lang.Integer" />
<%
  String helpParam = "0";
  if (templateKbn.intValue() != jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_COMMON) {
    if (accountMode.intValue() == jp.groupsession.v2.cmn.GSConstWebmail.WAC_TYPE_USER) {
      helpParam = "1";
    } else {
      helpParam = "2";
    }
  }
%>
<input type="hidden" name="helpPrm" value="<%= helpParam %>">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml030_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="wmlTemplateCmdMode" />
<html:hidden property="wmlEditTemplateId" />

<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wml240SortRadio" />
<html:hidden property="dspCount" />
<html:hidden property="wmlMailTemplateKbn" />

<html:hidden property="wml250initKbn" />
<html:hidden property="wml250TemplateName" />
<html:hidden property="wml250Title" />
<html:hidden property="wml250Content" />
<input type="hidden" name="wml250knFileId" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="60%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <bean:define id="accountMode" name="wml250knForm" property="wmlAccountMode" type="java.lang.Integer" />
      <% if (accountMode.intValue() == 2) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <% if (editFlg) { %>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml250kn.02" /> ]</td>
        <% } else { %>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml250kn.01" /> ]</td>
        <% } %>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      <% } else { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <% if (editFlg) { %>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml250kn.02" /> ]</td>
        <% } else { %>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml250kn.01" /> ]</td>
        <% } %>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <% } %>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="40%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="60%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr><td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="wml.102" /></span></td>
    <td align="left" class="td_wt" width="70%">
      <bean:define id="templateKbn" name="wml250knForm" property="wmlMailTemplateKbn" type="java.lang.Integer" />
      <% if (templateKbn.intValue() == jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_COMMON) { %>
        <gsmsg:write key="cmn.common" />
      <% } else { %>
        <bean:write name="wml250knForm" property="wml240accountName" />
      <% } %>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="30%" nowrap><span class="text_bb1"><gsmsg:write key="anp.anp100.02" /></span></td>
    <td align="left" class="td_wt" width="70%"><bean:write name="wml250knForm" property="wml250TemplateName" /></td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject" /></span></td>
    <td align="left" class="td_type20"><bean:write name="wml250knForm" property="wml250Title" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.body" /></span></td>
    <td align="left" class="td_type20"><bean:write name="wml250knForm" property="wml250knViewContent" filter="false" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td align="left" class="td_type20" valign="middle">

      <logic:empty name="wml250knForm" property="fileList">&nbsp;</logic:empty>

      <logic:notEmpty name="wml250knForm" property="fileList">
      <table cellpadding="0" cellpadding="0" border="0">

      <logic:iterate id="fileMdl" name="wml250knForm" property="fileList">
        <tr>
        <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
        <td class="menu_bun"><a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a></td>
        </tr>

      </logic:iterate>

      </table>
      </logic:notEmpty>

    </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.space" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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