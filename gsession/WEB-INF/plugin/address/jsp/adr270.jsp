<%@ page import="java.util.Calendar" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String close = String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr270.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.addressbook" /></title>
</head>

<body class="body_03">

<html:form action="/address/adr270">

<html:hidden property="adr270address1" />
<html:hidden property="adr270address2" />

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="90%">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1" onClick="window.close();">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.name" /></span></td>
    <td width="45%" valign="middle" align="left" class="td_type1">
      <bean:write name="adr270Form" property="adr270unameSei" />&nbsp;&nbsp;<bean:write name="adr270Form" property="adr270unameMei" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="user.119" /></span></td>
    <td valign="middle" align="left" class="td_type1">
      <bean:write name="adr270Form" property="adr270unameSeiKn" />&nbsp;&nbsp;<bean:write name="adr270Form" property="adr270unameMeiKn" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.number.display.settings" /></span></td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="address.7" /></span></td>
    <td align="left" class="td_type1"><bean:write name="adr270Form" property="adr270companyCode" /></td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.company.name" /></span></td>
    <td align="left" class="td_type1"><bean:write name="adr270Form" property="adr270companyName" /></td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="address.10" /></span></td>
    <td align="left" class="td_type1"><bean:write name="adr270Form" property="adr270companyBaseName" /></td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.affiliation" /></font></td>
    <td valign="middle" align="left" class="td_type1"><bean:write name="adr270Form" property="adr270syozoku" /></td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.post" /></span></td>
    <td valign="middle" align="left" class="td_type1"><bean:write name="adr270Form" property="adr270positionName" /></td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.mailaddress1" /></font></td>
    <td valign="middle" align="left" class="td_type1" colspan="1" nowrap>
      <bean:write name="adr270Form" property="adr270mail1" />
      <logic:notEmpty name="adr270Form" property="adr270mail1Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><bean:write name="adr270Form" property="adr270mail1Comment" /></logic:notEmpty>
    </td>

    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.mailaddress2" /></font> </td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270mail2" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270mail1Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><bean:write name="adr270Form" property="adr270mail2Comment" /></logic:notEmpty>
    </td>

    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.mailaddress3" /></font> </td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270mail3" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270mail1Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><bean:write name="adr270Form" property="adr270mail3Comment" /></logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel1" /></font></td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270tel1" />
      <logic:notEmpty name="adr270Form" property="adr270tel1Nai"><br><span class="text_base2"><gsmsg:write key="address.58" />：&nbsp;</span><bean:write name="adr270Form" property="adr270tel1Nai" /></logic:notEmpty>
      <logic:notEmpty name="adr270Form" property="adr270tel1Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><bean:write name="adr270Form" property="adr270tel1Comment" /></logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel2" /></font></td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270tel2" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270tel2Nai"><br><span class="text_base2"><gsmsg:write key="address.58" />：&nbsp;</span><bean:write name="adr270Form" property="adr270tel2Nai" /></logic:notEmpty>
      <logic:notEmpty name="adr270Form" property="adr270tel2Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><bean:write name="adr270Form" property="adr270tel2Comment" /></logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel3" /></font></td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270tel3" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270tel3Nai"><br><span class="text_base2"><gsmsg:write key="address.58" />：&nbsp;</span><bean:write name="adr270Form" property="adr270tel3Nai" /></logic:notEmpty>
      <logic:notEmpty name="adr270Form" property="adr270tel3Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><bean:write name="adr270Form" property="adr270tel3Comment" /></logic:notEmpty>
    </td>
    </tr>

    <!-- FAX -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ１</font></td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270fax1" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270fax1Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span></span><bean:write name="adr270Form" property="adr270fax1Comment" /></logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ２</font></td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270fax2" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270fax2Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span></span><bean:write name="adr270Form" property="adr270fax2Comment" /></logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ３</font></td>
    <td valign="middle" align="left" class="td_type1" nowrap>
      <bean:write name="adr270Form" property="adr270fax3" />&nbsp;
      <logic:notEmpty name="adr270Form" property="adr270fax3Comment"><br><span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span></span><bean:write name="adr270Form" property="adr270fax3Comment" /></logic:notEmpty>
    </td>
    </tr>

    <% String addressRow = "5"; %>
    <% String editRow = "3"; %>
    <logic:equal name="adr270Form" property="adr270searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
      <% addressRow = "4"; %>
      <% editRow = "2"; %>
    </logic:equal>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="<%= addressRow %>" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address" /></font></td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.postalcode" /></font></td>
    <td valign="middle" align="left" class="td_type1">
      <bean:write name="adr270Form" property="adr270ViewPostno" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.prefectures" /></font></td>
    <td valign="middle" align="left" class="td_type1">
      <bean:write name="adr270Form" property="adr270tdfkName" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address1" /></font></td>
    <td valign="middle" align="left" class="td_type1">
      <bean:write name="adr270Form" property="adr270address1" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address2" /></font></td>
    <td valign="middle" align="left" class="td_type1"><bean:write name="adr270Form" property="adr270address2" /></td>
    </tr>

    <% if (addressRow.equals("5")) { %>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="address.76" /></font> </td>
    <td valign="middle" align="left" class="td_type1">
      <a href="#" onClick="return addressSearch();"><span class="search_map text_blue_100"><gsmsg:write key="cmn.search.map" /></span></a>
    </td>
    </tr>
    <% } %>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td valign="middle" align="left" class="td_type1"><bean:write name="adr270Form" property="adr270biko" filter="false" /></td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.staff" /></span></td>
    <td valign="middle" align="left" class="td_type1">
      <logic:notEmpty name="adr270Form" property="selectTantoCombo">
        <% boolean tantoLineFlg = false; %>
        <logic:iterate id="tantoLabel" name="adr270Form" property="selectTantoCombo">
          <% if (tantoLineFlg) { %><br><% } else { tantoLineFlg = true; } %>
          <bean:write name="tantoLabel" property="label" />
        </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.label" /></span>
    </td>
    <td valign="middle" align="left" class="td_type1">
      <logic:notEmpty name="adr270Form" property="selectLabelList">
      <table cellpadding="0" cellspacing="0" class="tl0 spacer" width="100%">
      <% String[] labelClass = {"td_label1", "td_label2"}; %>
      <logic:iterate id="labelData" name="adr270Form" property="selectLabelList" indexId="idx">
      <tr class="<%= labelClass[idx.intValue() % 2] %>">
      <td width="100%"><bean:write name="labelData" property="albName" /></td>
      </tr>

      </logic:iterate>

      </table>
      </logic:notEmpty>

    </td>
    </tr>

    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </td>
  </tr>

  <tr>
  <td align="right" class="td_btn">
    <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1" onClick="window.close();">
  </td>
  </tr>

  </table>

</td>
</tr>
</table>

</html:form>
</body>
</html:html>