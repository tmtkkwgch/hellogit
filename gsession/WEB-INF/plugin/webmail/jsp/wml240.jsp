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
<title>[Group Session] <gsmsg:write key="anp.anp070.02" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml240.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml240">

<bean:define id="templateKbn" name="wml240Form" property="wmlMailTemplateKbn" type="java.lang.Integer" />
<bean:define id="accountMode" name="wml240Form" property="wmlAccountMode" type="java.lang.Integer" />
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

<html:hidden name="wml240Form" property="wmlViewAccount" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="wmlTemplateCmdMode" />
<html:hidden property="wmlEditTemplateId" />
<html:hidden property="dspCount" />
<html:hidden property="wmlMailTemplateKbn" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="50%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <bean:define id="accountMode" name="wml240Form" property="wmlAccountMode" type="java.lang.Integer" />
      <% if (accountMode.intValue() == 2) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.anp070.02" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      <% } else { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.anp070.02" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <% } %>
      </tr>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addTemplate();">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('wml240Back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr>
    <td>
    <logic:messagesPresent message="false">
      <tr>
      <td>
      <table width="100%">
        <tr><td align="left"><html:errors/></td></tr>
      </table>
      </td>
      </tr>
    </logic:messagesPresent>
    </td>
  </tr>
  <tr><td>

    <br>
    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD text_tlw" width="0%" nowrap><gsmsg:write key="wml.28" /></th>
    <td align="left" class="smail_td1" width="100%">
      <bean:define id="templateKbn" name="wml240Form" property="wmlMailTemplateKbn" type="java.lang.Integer" />
      <% if (templateKbn.intValue() == jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_COMMON) { %>
        <gsmsg:write key="cmn.common" />
      <% } else { %>
        <bean:write name="wml240Form" property="wml240accountName" />
      <% } %>
    </td>
    </tr>
    </table>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td style="white-space:nowrap;">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('upTemplateData');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush('downTemplateData');">
      </td>
    </tr>
    </table>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="0%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="100%"><span class="text_tlw"><gsmsg:write key="anp.anp100.02" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw"><gsmsg:write key="cmn.fixed" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw"><gsmsg:write key="cmn.delete" /></span></th>
    </tr>

    <logic:iterate id="templateData" name="wml240Form" property="templateList" indexId="idx">
      <bean:define id="templateId" name="templateData" property="wtpSid" />
      <% String templateCheckId = "chkTemplate" + String.valueOf(idx.intValue()); %>
      <tr>
      <td align="center" class="smail_td1" nowrap><html:radio property="wml240SortRadio" value="<%= String.valueOf(templateId) %>" styleId="<%= templateCheckId %>" /></td>
      <td align="left" class="smail_td1" onClick="wml240CheckTemplate('<%= templateCheckId %>');">
        <bean:write name="templateData" property="wtpName" />
      </td>
      <td align="left" class="smail_td1"><input type="button" class="btn_edit_n3" value="<gsmsg:write key="cmn.fixed" />" name="btn_change" onClick="editTemplate('<bean:write name="templateData" property="wtpSid" />');"></td>
      <td align="left" class="smail_td1"><input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" name="btn_delete" onClick="deleteTemplate('<bean:write name="templateData" property="wtpSid" />');"></td>
    </tr>
    </logic:iterate>

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