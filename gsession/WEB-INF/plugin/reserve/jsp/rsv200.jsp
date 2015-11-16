<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.MAX_LENGTH_BIKO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.61" /> ]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/sisetuPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="initSettingBackGroundColor();showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="windowClose();">
<html:form action="/reserve/rsv200">
<input type="hidden" name="CMD" value="ikkatu_settei_kakunin">

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv080EditGrpSid" />
<html:hidden property="rsv080SortRadio" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv200Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv200Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv200Form" property="rsv200TargetSisetu" scope="request">
  <logic:iterate id="targetSisetu" name="rsv200Form" property="rsv200TargetSisetu" scope="request">
    <input type="hidden" name="rsv200TargetSisetu" value="<bean:write name="targetSisetu"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv200Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv200Form" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.61" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="submit" value="OK" class="btn_ok1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_settei');">
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
    <span class="text_base"><gsmsg:write key="reserve.rsv200.1" /></span>
  </td>
  </tr>
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop4" name="rsv200Form" property="rsv200Prop4Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop4');" />
      <label for="rsv200Prop4"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName4" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop4Inp"><html:text styleClass="text_base_rsv" name="rsv200Form" property="rsv200Prop4Value" maxlength="20" style="width:177px;"/></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop5" name="rsv200Form" property="rsv200Prop5Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop5');" />
      <label for="rsv200Prop5"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName5" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop5Inp"><html:text styleClass="text_base_rsv" name="rsv200Form" property="rsv200Prop5Value" maxlength="17" style="width:153px;"/></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop1" name="rsv200Form" property="rsv200Prop1Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop1');" />
      <label for="rsv200Prop1"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName1" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop1Inp"><html:text styleClass="text_base_rsv" name="rsv200Form" property="rsv200Prop1Value" style="width:123px;" maxlength="10" /></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop2" name="rsv200Form" property="rsv200Prop2Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop2');" />
      <label for="rsv200Prop2"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName2" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop2Inp">
      <html:radio styleId="2ka" name="rsv200Form" property="rsv200Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>" /><label for="2ka"><gsmsg:write key="cmn.accepted" /></label>&nbsp;
      <html:radio styleId="2huka" name="rsv200Form" property="rsv200Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>" /><label for="2huka"><gsmsg:write key="cmn.not" /></label>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop3" name="rsv200Form" property="rsv200Prop3Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop3');" />
      <label for="rsv200Prop3"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName3" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop3Inp">
      <html:radio styleId="3ka" name="rsv200Form" property="rsv200Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>" /><label for="3ka"><gsmsg:write key="cmn.accepted" /></label>&nbsp;
      <html:radio styleId="3huka" name="rsv200Form" property="rsv200Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>" /><label for="3huka"><gsmsg:write key="cmn.not" /></label>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop7" name="rsv200Form" property="rsv200Prop7Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop7');" />
      <label for="rsv200Prop7"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName7" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop7Inp">
      <html:radio styleId="7ka" name="rsv200Form" property="rsv200Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>" /><label for="7ka"><gsmsg:write key="cmn.accepted" /></label>&nbsp;
      <html:radio styleId="7huka" name="rsv200Form" property="rsv200Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>" /><label for="7huka"><gsmsg:write key="cmn.not" /></label>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200Form" property="rsv200PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Prop6" name="rsv200Form" property="rsv200Prop6Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Prop6');" />
      <label for="rsv200Prop6"><span class="text_bb1"><bean:write name="rsv200Form" property="rsv200PropHeaderName6" /></span></label></td>
    <td align="left" class="td_type1" id="rsv200Prop6Inp"><html:text styleClass="text_base_rsv" name="rsv200Form" property="rsv200Prop6Value" style="width:69px;" maxlength="4" />&nbsp;<gsmsg:write key="cmn.days.after" /></td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <html:checkbox styleId="rsv200Biko" name="rsv200Form" property="rsv200BikoCheck" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>" onclick="setBackGroundColor('rsv200Biko');" />
      <label for="rsv200Biko"><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></label>
    <td align="left" class="td_type1" id="rsv200BikoInp">
      <textarea styleClass="text_base_rsv" name="rsv200Biko" style="width:489px;" rows="6" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="rsv200Form" property="rsv200Biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

  <br>

  <table width="70%" class="tl0">
  <tr>
  <th align="left" width="0%" class="table_bg_7D91BD">
    <input type="checkbox" name="allCheck" onClick="changeChk();">
  <th align="center" width="100%" class="table_bg_7D91BD">
    <span class="text_tlw"><gsmsg:write key="reserve.121" /></span></th>
  </tr>

  <bean:define id="mod" value="0" />

  <logic:notEmpty name="rsv200Form" property="rsv200SisetuList" scope="request">
    <logic:iterate id="sisetu" name="rsv200Form" property="rsv200SisetuList" scope="request" indexId="idx">

    <tr>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type25_2" />
    </logic:notEqual>
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type1" />
    </logic:equal>
    <td align="left" class="<bean:write name="tblColor" />">
      <html:multibox name="rsv200Form" property="rsv200TargetSisetu"><bean:write name="sisetu" property="rsdSid" /></html:multibox>
    </td>
    <td align="left" class="<bean:write name="tblColor" />">
      <a href="javaScript:void(0);" onclick="openSisetuSyosai(<bean:write name="sisetu" property="rsdSid" />);"><bean:write name="sisetu" property="rsdName" /><a></td>
    </tr>

    </logic:iterate>
  </logic:notEmpty>

  <tr>
  <td colspan="2">
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="submit" value="OK" class="btn_ok1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_settei');">
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