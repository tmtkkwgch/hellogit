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
  <title>[GroupSession]<gsmsg:write key="wml.wml100.02" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../webmail/js/wml100.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/webmail/wml100">

<input type="hidden" name="CMD" value="">

<html:hidden property="wmlCmdMode" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />
<html:hidden property="backScreen" />

<logic:notEmpty name="wml100Form" property="accountList" scope="request">
  <logic:iterate id="sort" name="wml100Form" property="accountList" scope="request">
    <input type="hidden" name="wml100sortLabel" value="<bean:write name="sort" property="acValue" />">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="wmlMailTemplateKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_NORMAL) %>">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml100.02" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="accountConf(0, 0);">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('psnTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr><td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td>
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('upFilterData');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush('downFilterData');">
      </td>
    </tr>
    </table>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="0%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="25%"><span class="text_tlw"><gsmsg:write key="wml.96" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="35%"><span class="text_tlw"><gsmsg:write key="cmn.mailaddress" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%"><span class="text_tlw"><gsmsg:write key="cmn.received.date" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="20%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="5%"><span class="text_tlw"><gsmsg:write key="cmn.edit" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="5%"><span class="text_tlw"><gsmsg:write key="cmn.template" /></span></th>
    </tr>

    <logic:iterate id="acuntData" name="wml100Form" property="accountList">
      <bean:define id="acValue" name="acuntData" property="acValue" />
      <tr>
      <td align="center" class="smail_td1" nowrap><html:radio property="wml100sortAccount" value="<%= String.valueOf(acValue) %>" /></td>
      <td align="left" class="smail_td1"><span class="text_base"><bean:write name="acuntData" property="accountName" /></span></td>
      <td align="left" class="smail_td1"><span class="text_base"><bean:write name="acuntData" property="accountAddress" /></span></td>
      <td align="left" class="smail_td1"><span class="text_base"><bean:write name="acuntData" property="receiveDate" /></span></td>
      <td align="left" class="smail_td1"><span class="text_base"><bean:write name="acuntData" property="accountBiko" /></span></td>
      <td align="left" class="smail_td1">
        <logic:equal name="acuntData" property="accountUseUser" value="true">
          <input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n3" onclick="return accountEdit(1, <bean:write name="acuntData" property="accountSid" />);">
        </logic:equal>
      </td>
      <td align="left" class="smail_td1">
      <input type="button" value="<gsmsg:write key="cmn.template" />" class="btn_account_subconf" onclick="return mailTemplate(<bean:write name="acuntData" property="accountSid" />);">
      </td>
      </tr>
    </logic:iterate>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">&nbsp;</td>
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