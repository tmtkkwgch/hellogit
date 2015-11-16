<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [<gsmsg:write key="reserve.62" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv180.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="windowClose();">
<html:form action="/reserve/rsv180">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsv180Selectgroup" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv080EditGrpSid" />
<html:hidden property="rsv080EditSisetuSid" />
<html:hidden property="rsv080SortRadio" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv180Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv180Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv180Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv180Form" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.62" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_zyoho');">
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
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
    <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span></td>
    <td width="80%" class="td_type1"><bean:write name="rsv180Form" property="rsv080RsgName" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td class="td_type1"><bean:write name="rsv180Form" property="rsv080RskName" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.110" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%" nowrap>
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('rsv180SelectFiles', '<%= jp.groupsession.v2.rsv.GSConstReserve.PLUGIN_ID_RESERVE %>', '1');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');"><br>

      <html:select property="rsv180SelectFiles" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="rsv180Form" property="rsv180FileLabelList" value="value" label="label" />
      </html:select>

      &nbsp;
      <span class="text_base">
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% String csvFileMsg = ""; %>
        <logic:equal name="rsv180Form" property="rsv180RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv180.do?CMD=rsv180_sample&sample=1&kbn=1\">【" + gsMsg.getMessage(request, "reserve.112") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv180Form" property="rsv180RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_BUPPIN) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv180.do?CMD=rsv180_sample&sample=1&kbn=2\">【" + gsMsg.getMessage(request, "reserve.113") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv180Form" property="rsv180RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv180.do?CMD=rsv180_sample&sample=1&kbn=3\">【" + gsMsg.getMessage(request, "reserve.114") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv180Form" property="rsv180RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_BOOK) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv180.do?CMD=rsv180_sample&sample=1&kbn=4\">【" + gsMsg.getMessage(request, "reserve.115") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
        <logic:equal name="rsv180Form" property="rsv180RskSid" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_OTHER) %>">
          <% csvFileMsg = "<a href=\"../reserve/rsv180.do?CMD=rsv180_sample&sample=1&kbn=5\">【" + gsMsg.getMessage(request, "cmn.other") + "】" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
          *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
        </logic:equal>
      </span>
      <br>
    </td>
    </tr>
  </td>
  </tr>
  </table>


  <table cellpadding="0" cellspacing="0" width="100%">
  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onclick="buttonPush('import');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_zyoho');">
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