<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int batchOff = jp.groupsession.v2.rsv.GSConstReserve.RSV_RADIO_OFF; %>
<% int batchOn = jp.groupsession.v2.rsv.GSConstReserve.RSV_RADIO_ON; %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting" />[<gsmsg:write key="cmn.reserve" /> <gsmsg:write key="cmn.automatic.delete.setting" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../reserve/js/rsv120.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="changeEnableDisable()">
<html:form action="/reserve/rsv120">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv120initDspFlg" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv120Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv120Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv120Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv120Form" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.reserve" /> <gsmsg:write key="cmn.automatic.delete.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="./images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('batch_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onclick="buttonPush('back_to_kanri_menu');">
    </td>
    <td width="0%"><img src="./images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.autodelete" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="reserve.rsv120.1" /></span>
      <logic:notEmpty name="rsv120Form" property="rsv120BatchTime">
      <br>
      <bean:define id="rsv120BachTime" name="rsv120Form" property="rsv120BatchTime" type="java.lang.String" />
      <span class="text_r1"><gsmsg:write key="cmn.automatic.performed.time" arg0="<%= rsv120BachTime %>" /></span>
      </logic:notEmpty>
      <br><br>
      <html:radio name="rsv120Form" property="rsv120batchKbn" value="<%= String.valueOf(batchOff) %>" styleId="rsv120batchKbn0" onclick="changeEnableDisable()" /><label for="rsv120batchKbn0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
      <html:radio name="rsv120Form" property="rsv120batchKbn" value="<%= String.valueOf(batchOn) %>" styleId="rsv120batchKbn1" onclick="changeEnableDisable()" /><label for="rsv120batchKbn1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

      <gsmsg:write key="cmn.after.data.head" />

      <html:select property="rsv120year" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="rsv120Form" property="rsv120yearLabelList" value="value" label="label" />
      </html:select>
      <html:select property="rsv120month" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="rsv120Form" property="rsv120monthLabelList" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.after.data" />
    </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('batch_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onclick="buttonPush('back_to_kanri_menu');">
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