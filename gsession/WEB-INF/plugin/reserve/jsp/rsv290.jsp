<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting" />[<gsmsg:write key="reserve.168" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../reserve/js/rsv290.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/reserve/rsv290">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv290Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv290Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv290Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv290Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv290initDspFlg" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="reserve.168" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="./images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onclick="buttonPush('zikantai_settei_kakunin');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onclick="buttonPush('backAdminMenu');">
    </td>
    <td width="0%"><img src="./images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

<logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="left"><html:errors/></td>
      </tr>
    </table>
</logic:messagesPresent>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td width="10%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.show.timezone.days.setting" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <html:radio name="rsv290Form" styleId="rsv290DateKbn_01" property="rsv290DateKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RAC_DTMKBN_ADM) %>" onclick="changeEnableDisable();" /><label for="rsv290DateKbn_01"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="rsv290Form" styleId="rsv290DateKbn_02" property="rsv290DateKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RAC_DTMKBN_USER) %>" onclick="changeEnableDisable();" /><label for="rsv290DateKbn_02"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      <br>
      <span class="text_description_dsp"><gsmsg:write key="cmn.comments" /><gsmsg:write key="reserve.rsv160.1" /></span><br>
      <span id="lmtText">※<gsmsg:write key="cmn.view.user.defaultset" /><br></span>
      <span class="text_bb1"><gsmsg:write key="cmn.starttime" />：</span>
      <html:select property="rsv290SelectedFromSid">
        <html:optionsCollection name="rsv290Form" property="rsv290HourLabelList" value="value" label="label" />
      </html:select>
      <br>
      <span class="text_bb1"><gsmsg:write key="cmn.endtime" />：</span>
      <html:select property="rsv290SelectedToSid">
        <html:optionsCollection name="rsv290Form" property="rsv290HourLabelList" value="value" label="label" />
      </html:select>

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onclick="buttonPush('zikantai_settei_kakunin');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onclick="buttonPush('backAdminMenu');">
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