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
<title>[Group Session] <gsmsg:write key="address.adr210.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr210.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/address/adr210">
<input type="hidden" name="CMD" value="">
<html:hidden property="adr210ProcMode" />
<html:hidden property="adr210EditPosSid" />
<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr210Form" property="posList" scope="request">
  <logic:iterate id="sort" name="adr210Form" property="posList" scope="request">
    <input type="hidden" name="adr210KeyList" value="<bean:write name="sort" property="posValue" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr210Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr210Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr210Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr210Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr210Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr210Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
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
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr210.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="buttonSubmit('adr210add', '<%= add %>', '-1');">
      <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('adr210back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />"  onClick="return buttonPush('up');">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />"  onClick="return buttonPush('down');">
&nbsp;&nbsp;<span class="text_base"><gsmsg:write key="address.adr210.2" /></span>
    </td>
    </tr>
    </table>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="6%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="25%"><span class="text_tlw"><gsmsg:write key="cmn.job.title" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="69%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <logic:notEmpty name="adr210Form" property="posList" scope="request">
    <logic:iterate id="posMdl" name="adr210Form" property="posList" scope="request" indexId="idx">

    <bean:define id="mod" value="0" />
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type1" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type_usr" />
    </logic:notEqual>

    <bean:define id="posValue" name="posMdl" property="posValue" />

      <tr class="<bean:write name="tblColor" />">
      <td align="center" nowrap><html:radio property="adr210SortRadio" value="<%= String.valueOf(posValue) %>" /></td>
      <td align="left">
        <a href="javascript:void(0);" onclick="return buttonSubmit('posname', '<%= edit %>', '<bean:write name="posMdl" property="apsSid" />');"><span class="text_link"><bean:write name="posMdl" property="apsName" /></span></a>
      </td>
      <td align="left"><bean:write name="posMdl" property="apsBiko" filter="false" /></td>
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

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>