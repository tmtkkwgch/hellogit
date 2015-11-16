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
<title>[Group Session] <gsmsg:write key="wml.86" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <theme:css filename="theme.css"/>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../smail/js/sml350.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/smail/sml350">


<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="smlCmdMode" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountMode" />
<html:hidden property="smlAccountSid" />
<html:hidden property="smlFilterCmdMode" />
<html:hidden property="smlEditFilterId" />
<html:hidden property="dspCount" />

<logic:notEmpty name="sml350Form" property="filList" scope="request">
  <logic:iterate id="sort" name="sml350Form" property="filList" scope="request">
    <input type="hidden" name="sml350sortLabel" value="<bean:write name="sort" property="filValue" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="50%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.86" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addFilter();">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('sml350Back');">
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
    <td align="left" class="smail_td1" width="100%"><bean:write name="sml350Form" property="sml350accountName" /></td>
    </tr>
    </table>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td style="white-space:nowrap;">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('upFilterData');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush('downFilterData');">
      </td>
      <td align="left" width="100%"><span class="text_base3"><gsmsg:write key="wml.wml130.01" /></span></td>
    </tr>
    </table>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="0%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="100%"><span class="text_tlw"><gsmsg:write key="wml.84" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw"><gsmsg:write key="cmn.fixed" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw"><gsmsg:write key="cmn.delete" /></span></th>
    </tr>

    <logic:iterate id="filData" name="sml350Form" property="filList" indexId="idx">
      <bean:define id="filValue" name="filData" property="filValue" />
      <% String filterCheckId = "chkFilter" + String.valueOf(idx.intValue()); %>
      <tr>
      <td align="center" class="smail_td1" nowrap><html:radio property="sml350SortRadio" value="<%= String.valueOf(filValue) %>" styleId="<%= filterCheckId %>" /></td>
      <td align="left" class="smail_td1" onClick="sml350CheckFilter('<%= filterCheckId %>');">
        <bean:write name="filData" property="filterName" />
      </td>
      <td align="left" class="smail_td1"><input type="button" class="btn_edit_n3" value="<gsmsg:write key="cmn.fixed" />" name="btn_change" onClick="editFilter('<bean:write name="filData" property="filterSid" />');"></td>
      <td align="left" class="smail_td1"><input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" name="btn_delete" onClick="deleteFilter('<bean:write name="filData" property="filterSid" />');"></td>
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