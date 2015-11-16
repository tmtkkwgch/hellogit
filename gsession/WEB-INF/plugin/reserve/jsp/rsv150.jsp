<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int dspNG = jp.groupsession.v2.rsv.GSConstReserve.KOJN_SETTEI_DSP_NO; %>
<% int dspOK = jp.groupsession.v2.rsv.GSConstReserve.KOJN_SETTEI_DSP_OK; %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.preferences2" />[<gsmsg:write key="cmn.display.settings" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../reserve/js/rsv150.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv150">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv150initDspFlg" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv150Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv150Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv150Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv150Form" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.display.settings" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="./images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('hyozi_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.preferences" />" onclick="buttonPush('back_to_kojn_menu');">
    </td>
    <td width="0%"><img src="./images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
<logic:messagesPresent message="false">
      <tr>
         <td align="left"><html:errors/></td>
      </tr>
</logic:messagesPresent>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.initial.display" /></span></a></td>
    <td class="td_type1">
      <html:radio name="rsv150Form" styleId="sch096Dsp_02" property="rsv150DefDsp" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.DSP_DAY) %>" /><label for="sch096Dsp_02"><span class="text_base"><gsmsg:write key="cmn.days2" /></span></label>&nbsp;
      <html:radio name="rsv150Form" styleId="sch096Dsp_01" property="rsv150DefDsp" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.DSP_WEEK) %>" /><label for="sch096Dsp_01"><span class="text_base"><gsmsg:write key="cmn.weeks" /></span></label>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.99" /></span></a></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="reserve.rsv150.1" /></span><br><br>
      <html:select property="rsv150SelectedGrpSid" styleClass="select01">
        <html:optionsCollection name="rsv150Form" property="rsv150sisetuLabelList" value="value" label="label" />
      </html:select>
    </td>
    </tr>
    <tr>
    <td width="20%" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.100" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></a></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="cmn.comments" /><gsmsg:write key="reserve.rsv150.2" /></span><br><br>
      <html:checkbox name="rsv150Form" property="rsv150DispItem1" value="<%= String.valueOf(dspOK) %>" styleId="rsv150DispItem1" />&nbsp;<label for="rsv150DispItem1"><gsmsg:write key="reserve.72" /></label><br>
      <html:checkbox name="rsv150Form" property="rsv150DispItem2" value="<%= String.valueOf(dspOK) %>" styleId="rsv150DispItem2" />&nbsp;<label for="rsv150DispItem2"><gsmsg:write key="reserve.137" /></label>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.auto.reload.time" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></a></td>
    <td class="td_type1">
      <html:select name="rsv150Form" property="rsv150ReloadTime">
      <html:optionsCollection name="rsv150Form" property="rsv150TimeLabelList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.102" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></a></td>
    <td class="td_type1">
      <html:radio name="rsv150Form" property="rsv150ImgDspKbn" styleId="rsv150ImgDspKbn0" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><span class="text_base"><label for="rsv150ImgDspKbn0"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <html:radio name="rsv150Form" property="rsv150ImgDspKbn" styleId="rsv150ImgDspKbn1" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><span class="text_base"><label for="rsv150ImgDspKbn1"><gsmsg:write key="cmn.dont.show" /></label>&nbsp;</span>
    </td>
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
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('hyozi_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.preferences" />" onclick="buttonPush('back_to_kojn_menu');">
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