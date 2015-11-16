<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [<gsmsg:write key="reserve.rsv080.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv080.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv080">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv080EditGrpSid" />
<html:hidden property="rsv080EditSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv080Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv080Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv080Form" property="rsv080KeyList" scope="request">
  <logic:iterate id="sort" name="rsv080Form" property="rsv080KeyList" scope="request">
    <input type="hidden" name="rsv080KeyList" value="<bean:write name="sort"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv080Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv080Form" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv080.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('sisetu_add');">
      <input type="button" name="btn_delete" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('sisetu_sakuzyo');">
      <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_sisetu_group_settei');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td></tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
    <td width="10%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span></td>
    <td class="td_type1"><bean:write name="rsv080Form" property="rsv080RsgName" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td class="td_type1"><bean:write name="rsv080Form" property="rsv080RskName" /></td>
    <tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>&nbsp;</td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" class="btn_upper" onclick="buttonPush('ue_e');">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" class="btn_downer" onclick="buttonPush('sita_e');">&nbsp;&nbsp;
    </td>
    <td align="right">
      <input type="button" class="btn_base0" value="<gsmsg:write key="reserve.61" />" class="btn_upper" onclick="buttonPush('move_ikkatu');">
      <input type="button" class="btn_csv_n2" value="<gsmsg:write key="reserve.62" />" class="btn_upper" onclick="buttonPush('move_import');">
    </td>
    </tr>
    </table>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" align="center" width="2%">&nbsp;</th>
    <th class="table_bg_7D91BD" align="center" width="45%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.facility.name" /></span></th>
    <th class="table_bg_7D91BD" align="center" width="10%" nowrap><span class="text_tlw"><gsmsg:write key="reserve.55" /></span></th>
    <th class="table_bg_7D91BD" align="center" width="15%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.asset.register.num" /></span></th>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName4">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName4" /></span></th>
    </logic:notEmpty>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName5">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName5" /></span></th>
    </logic:notEmpty>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName1">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName1" /></span></th>
    </logic:notEmpty>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName2">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName2" /></span></th>
    </logic:notEmpty>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName3">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName3" /></span></th>
    </logic:notEmpty>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName7">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName7" /></span></th>
    </logic:notEmpty>

    <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName6">
      <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><bean:write name="rsv080Form" property="rsv080PropHeaderName6" /></span></th>
    </logic:notEmpty>
    <th class="table_bg_7D91BD" align="center" nowrap><span class="text_tlw"><gsmsg:write key="reserve.appr.set.title" /></span></th>

    <th class="table_bg_7D91BD" align="center" width="2%">&nbsp;</th>
    </tr>

    <bean:define id="mod" value="0" />
    <logic:notEmpty name="rsv080Form" property="rsv080SisetuList" scope="request">
      <logic:iterate id="sisetu" name="rsv080Form" property="rsv080SisetuList" scope="request" indexId="idx">
        <bean:define id="index" value="<%= String.valueOf(((Integer) idx).intValue()) %>" />

        <tr>
        <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>
        <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>

        <td class="<bean:write name="tblColor" />" nowrap>
          <bean:define id="sKey" name="sisetu" property="radioKey" />
          <html:radio name="rsv080Form" property="rsv080SortRadio" value="<%= String.valueOf(sKey) %>" />
        </td>
        <td align="left" class="<bean:write name="tblColor" />" >
          <a href="#" onclick="moveSisetuEdit('<bean:write name="sisetu" property="rsdSid" />')"><span class="text_link"><bean:write name="sisetu" property="rsdName" /></span></a></td>

        <td align="left" class="<bean:write name="tblColor" />" ><bean:write name="sisetu" property="rsdId" /></td>

        <td align="left" class="<bean:write name="tblColor" />"><bean:write name="sisetu" property="rsdSnum" /></td>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName4">
          <td align="center" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp4">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp4"><bean:write name="sisetu" property="rsdProp4" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName5">
          <td align="center" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp5">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp5"><bean:write name="sisetu" property="rsdProp5" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName1">
          <td align="right" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp1">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp1"><bean:write name="sisetu" property="rsdProp1" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName2">
          <td align="center" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp2">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp2"><bean:write name="sisetu" property="rsdProp2" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName3">
          <td align="center" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp3">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp3"><bean:write name="sisetu" property="rsdProp3" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName7">
          <td align="center" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp7">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp7"><bean:write name="sisetu" property="rsdProp7" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <logic:notEmpty name="rsv080Form" property="rsv080PropHeaderName6">
          <td align="right" class="<bean:write name="tblColor" />" nowrap>
            <logic:empty name="sisetu" property="rsdProp6">&nbsp;</logic:empty>
            <logic:notEmpty name="sisetu" property="rsdProp6"><bean:write name="sisetu" property="rsdProp6" /></logic:notEmpty>
          </td>
        </logic:notEmpty>

        <td align="center" class="<bean:write name="tblColor" />" nowrap>
            <logic:equal name="sisetu" property="rsdApprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSD_APPR_KBN_NOSET) %>">
              <gsmsg:write key="reserve.appr.set.kbn2" />
            </logic:equal>
            <logic:notEqual name="sisetu" property="rsdApprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSD_APPR_KBN_NOSET) %>">
              <gsmsg:write key="reserve.appr.set.kbn1" />
            </logic:notEqual>
        </td>

        <td align="left" class="<bean:write name="tblColor" />" >
          <input type="button" onclick="moveSisetuEdit('<bean:write name="sisetu" property="rsdSid" />')" value="<gsmsg:write key="cmn.fixed" />" class="btn_base0"></td>

        </tr>

      </logic:iterate>
    </logic:notEmpty>

    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('sisetu_add');">
      <input type="button" name="btn_delete" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('sisetu_sakuzyo');">
      <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_sisetu_group_settei');">
    </td>
    </tr>
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