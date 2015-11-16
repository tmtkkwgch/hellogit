<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [<gsmsg:write key="reserve.rsv050.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv050">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050EditGrpSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv050Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv050Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv050Form" property="rsv050KeyList" scope="request">
  <logic:iterate id="sort" name="rsv050Form" property="rsv050KeyList" scope="request">
    <input type="hidden" name="rsv050KeyList" value="<bean:write name="sort"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv050Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv050Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv050.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="rsv050Form" property="rsvAdmFlg" value="true">
        <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onclick="buttonPush('sisetu_group_all_export');">
        <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.import" />" class="btn_upper"onClick="buttonPush('sisetu_group_all_tuika');">
        <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('sisetu_group_tuika');">
      </logic:equal>
      <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_menu');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td colspan="2">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onclick="buttonPush('ue_e');">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onclick="buttonPush('sita_e');">
    </td>
    </tr>
    <tr>
    <th class="table_bg_7D91BD" width="2%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="93%"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></th>
    <th class="table_bg_7D91BD" width="5%" nowrap>&nbsp;</th>
    <th class="table_bg_7D91BD" width="5%" nowrap>&nbsp;</th>
    </tr>

    <bean:define id="mod" value="0" />
    <logic:notEmpty name="rsv050Form" property="rsv050GroupList" scope="request">
      <logic:iterate id="grp" name="rsv050Form" property="rsv050GroupList" scope="request" indexId="idx">
        <bean:define id="index" value="<%= String.valueOf(((Integer) idx).intValue()) %>" />

        <tr>
        <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>
        <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>

        <td class="<bean:write name="tblColor" />" nowrap>

          <bean:define id="sKey" name="grp" property="radioKey" />
          <html:radio name="rsv050Form" property="rsv050SortRadio" value="<%= String.valueOf(sKey) %>" /></td>

        <td align="left" class="<bean:write name="tblColor" />">
          <a href="#" onclick="moveSisetuGroup('<bean:write name="grp" property="rsgSid" />')"><span class="text_link"><bean:write name="grp" property="rsgName" /></span></a>
        </td>
        <td align="center" class="<bean:write name="tblColor" />"><input onclick="moveSisetuGroup('<bean:write name="grp" property="rsgSid" />')" value="<gsmsg:write key="cmn.fixed" />" class="btn_base0" type="button"></td>
        <td align="center" class="<bean:write name="tblColor" />"><input type="button" value="<gsmsg:write key="reserve.settings" />" class="btn_base0" onclick="moveSisetu('<bean:write name="grp" property="rsgSid" />')"></td>
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>