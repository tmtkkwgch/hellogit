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
<title>[Group Session] <gsmsg:write key="address.118" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr250.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<html:form action="/address/adr250">

<input type="hidden" name="CMD" value="">
<html:hidden property="adr250AcoSid" />

<table width="530px" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="80%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.118" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">&nbsp;</td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>
  <tr>
  <td width="50%" valign="top">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <!--  <gsmsg:write key="address.7" /> -->
    <tr>
    <td width="30%" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.7" /></span>
    </td>
    <td width="70%" valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr250Form" property="adr250coCode" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.company.name" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr250Form" property="adr250coName" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="cmn.company.name" /><gsmsg:write key="cmn.kana.2" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.company.name" />(<gsmsg:write key="cmn.kana" />)</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr250Form" property="adr250coNameKn" /></span>
    </td>
    </tr>

    <!--  <gsmsg:write key="address.103" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.103" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:notEmpty name="adr250Form" property="adr250ViewAtiList">
      <span class="text_base">
      <logic:iterate id="atiName" name="adr250Form" property="adr250ViewAtiList">
      <bean:write name="atiName" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- URL -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1">URL</span>
    </td>
    <td valign="middle" align="left" class="td_wt"><a href="<bean:write name="adr250Form" property="adr250url" />" target="__blank"><span class="text_p"><bean:write name="adr250Form" property="adr250url" /></span></a></td>
    </tr>

    <!--  <gsmsg:write key="cmn.memo" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <span class="text_base"><bean:write name="adr250Form" property="adr250ViewBiko" filter="false" /></span>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <logic:notEmpty name="adr250Form" property="abaList">
  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl_u2">

    <tr>
    <td colspan="2"><IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0"></td>
    </tr>
    <logic:iterate id="abaData" name="adr250Form" property="abaList">
    <tr>
    <th colspan="4" class="address_info_bg" align="left">
    <img src="../address/images/parson_icon.gif" alt="<gsmsg:write key="address.106" />" class="img_bottom">
    <span class="adr_text_bb2">&nbsp;&nbsp;
    <logic:equal name="abaData" property="adr250abaTypeNameDetail" value="address.122">
      <gsmsg:write key="address.122" />
    </logic:equal>
    <logic:equal name="abaData" property="adr250abaTypeNameDetail" value="address.123">
      <gsmsg:write key="address.123" />
    </logic:equal>
    <logic:equal name="abaData" property="adr250abaTypeNameDetail" value="address.124">
      <gsmsg:write key="address.124" />
    </logic:equal>
    &nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="abaData" property="adr250abaName" /></span>
    </th>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" width="20%" nowrap><gsmsg:write key="cmn.postalcode" /></th>
    <td class="td_wt" width="30%">
    <logic:notEmpty name="abaData" property="adr250abaPostno1">
    <span class="text_base">〒<bean:write name="abaData" property="adr250abaPostno1" />-<bean:write name="abaData" property="adr250abaPostno2" /></span>
    </logic:notEmpty>
    </td>
    <th class="table_bg_A5B4E1" align="left" width="20%" nowrap><gsmsg:write key="cmn.prefectures" /></th>
    <td class="td_wt" width="30%"><span class="text_base"><bean:write name="abaData" property="adr250abaTdfkName" /></span></td>
    </td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" nowrap><gsmsg:write key="cmn.address1" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr250abaAddress1" /></span></td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" nowrap><gsmsg:write key="cmn.address2" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr250abaAddress2" /></span></td>
    </tr>

    <tr>
    <th class="table_bg_A5B4E1" align="left" nowrap><gsmsg:write key="cmn.memo" /></th>
    <td class="td_wt" colspan="3"><span class="text_base"><bean:write name="abaData" property="adr250abaViewBiko" filter="false" /></span></td>
    </tr>

    </logic:iterate>

    </table>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="center" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
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

</body>
</html:html>