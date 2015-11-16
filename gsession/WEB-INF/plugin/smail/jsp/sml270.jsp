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
  <script language="JavaScript" src="../smail/js/sml270.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">

<html:form action="/smail/sml270">

<input type="hidden" name="CMD" value="">

<html:hidden property="smlCmdMode" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountMode" />
<html:hidden property="smlAccountSid" />
<html:hidden property="backScreen" />

<logic:notEmpty name="sml270Form" property="accountList" scope="request">
  <logic:iterate id="sort" name="sml270Form" property="accountList" scope="request">
    <input type="hidden" name="sml270sortLabel" value="<bean:write name="sort" property="acValue" />">
  </logic:iterate>
</logic:notEmpty>

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
          <logic:equal name="sml270Form" property="sml270MakeAcntHnt" value="0">
            <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="accountConf(0, 0);">
          </logic:equal>
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
    <th align="center" class="table_bg_7D91BD" width="65%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="10%"><span class="text_tlw"><gsmsg:write key="cmn.edit" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw"></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw"></span></th>
    </tr>

    <logic:iterate id="acuntData" name="sml270Form" property="accountList">
      <bean:define id="acValue" name="acuntData" property="acValue" />
      <tr>
      <td align="center" class="smail_td1" nowrap><html:radio property="sml270sortAccount" value="<%= String.valueOf(acValue) %>" /></td>
      <td align="left" class="smail_td1"><span class="text_base"><bean:write name="acuntData" property="accountName" /></span></td>
      <td align="left" class="smail_td1"><span class="text_base"><bean:write name="acuntData" property="accountBiko" filter="false" /></span></td>
      <td align="left" class="smail_td1"><input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n3" onclick="return accountEdit(1, <bean:write name="acuntData" property="accountSid" />);"></td>

      <td align="left" class="smail_td1">
       <input type="button" onclick="confLabel(<bean:write name="acuntData" property="accountSid" />);" class="btn_base0" value="<gsmsg:write key="cmn.label" />">
      </td>
      <td align="left" class="smail_td1">
       <input type="button" onclick="confFilter(<bean:write name="acuntData" property="accountSid" />);" class="btn_base0" value="<gsmsg:write key="wml.248" />">
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