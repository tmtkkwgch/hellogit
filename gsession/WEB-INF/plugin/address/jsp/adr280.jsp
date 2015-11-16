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
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.categorylist" /></title>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr280.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/address/adr280">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="adr280EditSid">
<input type="hidden" name="adr280ProcMode">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr280Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr280Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr280Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr280Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr280Form" property="catList" scope="request">
  <logic:iterate id="sort" name="adr280Form" property="catList" scope="request">
    <input type="hidden" name="adr280KeyList" value="<bean:write name="sort" property="alcValue" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr280Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr280Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr280Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr280Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.category.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonSubmit('addCategory', 'add' , '-1');">&nbsp;
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('adr280back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('adr280up');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('adr280down');">&nbsp;&nbsp;
    </td>
    </tr>
    </table>




    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
      <th align="center" class="table_bg_7D91BD" width="5%"><span class="text_tlw">&nbsp;</span></th>
      <th align="center" class="table_bg_7D91BD" width="28%"><span class="text_tlw"><gsmsg:write key="cmn.category.name" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="51%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="16%" colspan="2"><span class="text_tlw"><gsmsg:write key="cmn.edit" /></span></th>
    </tr>

    <logic:notEmpty name="adr280Form" property="catList">
    <bean:define id="tdColor" value=""/>
    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>
    <logic:iterate id="catMdl" name="adr280Form" property="catList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <bean:define id="alcValue" name="catMdl" property="alcValue" />

    <tr align="center" class="<%= tdColor %>">
    <td align="center" nowrap>
    <html:radio property="adr280SortRadio" value="<%= String.valueOf(alcValue) %>" />
    </td>
    <td align="left" nowrap><bean:write name="catMdl" property="alcName" /></td>
    <td align="left">
    <bean:write name="catMdl" property="alcBiko" filter="false" />
    </td>
    <td align="center">
    <logic:notEqual name="catMdl" property="alcSid" value="1">
    <input type="button" value="<gsmsg:write key="cmn.category" />" class="btn_base0" onclick="return buttonSubmit('categoryEdit', 'edit', '<bean:write name="catMdl" property="alcSid" />')"></td>
    </logic:notEqual>
    <td align="center">
    <input type="button" value="<gsmsg:write key="cmn.label" />" class="btn_base0" onclick="return buttonSubmit('adr280edit','', '<bean:write name="catMdl" property="alcSid" />');"></td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>


    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonSubmit('addCategory', 'add' , '-1');">&nbsp;
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('adr280back');">
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