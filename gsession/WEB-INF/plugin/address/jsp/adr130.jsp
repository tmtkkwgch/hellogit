<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
  int add = jp.groupsession.v2.adr.GSConstAddress.PROCMODE_ADD;
  int edit = jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.labellist" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr130.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/address/adr130">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="adr130EditAlbSid">
<input type="hidden" name="adr130ProcMode">

<html:hidden property="adr280EditSid" />
<html:hidden property="adr280ProcMode" />

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr130Form" property="adr130LabelList" scope="request">
  <logic:iterate id="sort" name="adr130Form" property="adr130LabelList" scope="request">
    <input type="hidden" name="adr130KeyList" value="<bean:write name="sort" property="albValue" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr130Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr130Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr130Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr130Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr130Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr130Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<%-- BODY --%>
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.labellist" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('adr130add', '<%= add %>', '-1');">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr130back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
    <td width="10%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span></td>
    <td class="td_type1"><bean:write name="adr130Form" property="adr130CatName" /></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('adr130up');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('adr130down');">&nbsp;&nbsp;<span class="text_base"><gsmsg:write key="cmn.edit.label.click.name" /></span>
    </td>
    </tr>
    </table>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th class="table_bg_7D91BD" width="6%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="25%"><span class="text_tlw"><gsmsg:write key="cmn.label.name" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="69%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <logic:notEmpty name="adr130Form" property="adr130LabelList">
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>
    <logic:iterate id="labelMdl" name="adr130Form" property="adr130LabelList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <bean:define id="albValue" name="labelMdl" property="albValue" />

    <tr align="center" class="<%= tdColor %>">
    <!-- <gsmsg:write key="adr.radio.button" /> -->
    <td align="center" nowrap>
    <html:radio property="adr130SortRadio" value="<%= String.valueOf(albValue) %>" />
    </td>
    <td align="left">
    <a href="javascript:void(0);" onclick="return buttonSubmit('adr130edit', '<%= edit %>', '<bean:write name="labelMdl" property="albSid" />');">
    <span class="text_link"><bean:write name="labelMdl" property="albName" /></span>
    </a>
    </td>
    <!-- <gsmsg:write key="cmn.memo" /> -->
    <td align="left">
    <bean:write name="labelMdl" property="albBiko" filter="false" />
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>


    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
    <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('adr130add', '<%= add %>', '-1');">
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr130back');">
    </td>
    </tr>
    </table>


  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>