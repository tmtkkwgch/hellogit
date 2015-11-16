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
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />
  <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [ <gsmsg:write key="reserve.rsv090.1" /> ]</logic:equal>
  <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">[ <gsmsg:write key="reserve.rsv090.2" /> ]</logic:equal>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv090.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script type="text/javascript">
function initPictureRsv() {
    jQuery(".img_hoge").each(function(){
      if ($(".img_hoge").width() > 130) {
        $(".img_hoge").width(130);
      }
    });
}
</script>

</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');initPictureRsv();">
<html:form action="/reserve/rsv090">
<input type="hidden" name="CMD" value="sisetu_toroku_kakunin">

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv080EditGrpSid" />
<html:hidden property="rsv080SortRadio" />
<html:hidden property="rsv090InitFlg" />
<html:hidden property="rsv090ProcMode" />
<html:hidden property="rsv090EditGrpSid" />
<html:hidden property="rsv090EditSisetuSid" />
<html:hidden property="rsv090sisGrpApprFlg" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv090Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv090Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv090Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv090Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<bean:write name="rsv090Form" property="rsv090ProcMode" />">

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
    <td width="100%" class="header_white_bg_text">
      <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [ <gsmsg:write key="reserve.rsv090.1" /> ]</logic:equal>
      <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">[ <gsmsg:write key="reserve.rsv090.2" /> ]</logic:equal>
    </td>
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

      <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('sisetu_sakuzyo_kakunin');">
      </logic:equal>

      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_zyoho_settei');">
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

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span></td>
    <td align="left" class="td_type1" width="60%">
      <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>">
        <span class="text_base_rsv">
          <bean:write name="rsv090Form" property="rsv090GrpName" />
        </span>
      </logic:equal>
      <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <html:select property="rsv090SelectRsvGrp" styleClass="select01" onchange="changeGroupCombo('chGrpComb');">
          <html:optionsCollection name="rsv090Form" property="rsv090RsvGrpLabelList" value="value" label="label" />
        </html:select>
      </logic:equal>
    </td>
    <td class="td_type1" width="20%" align="center" rowspan="2" nowrap><gsmsg:write key="reserve.view.sisetu.dsp.kbn" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1" width="80%"><span class="text_base_rsv"><bean:write name="rsv090Form" property="rsv090SisetuKbnName" /></span></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.55" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090SisetuId" style="width:215px;" maxlength="15" /></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="sisetuIdDsp" name="rsv090Form" property="rsv090SisetuIdDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="sisetuIdDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="sisetuIdDspNot" name="rsv090Form" property="rsv090SisetuIdDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="sisetuIdDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type1" width="60%"><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090SisetuName" style="width:393px;" maxlength="100" /></td>
    <td align="left" class="td_type1" width="20%"></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.asset.register.num" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090SisanKanri" style="width:215px;" maxlength="20" /></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="sisanKanriDsp" name="rsv090Form" property="rsv090SisanKanriDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="sisanKanriDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="sisanKanriDspNot" name="rsv090Form" property="rsv090SisanKanriDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="sisanKanriDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName4" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090Prop4Value" maxlength="20" style="width:179px;"/></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop4ValueDsp" name="rsv090Form" property="rsv090Prop4ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop4ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop4ValueDspNot" name="rsv090Form" property="rsv090Prop4ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop4ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName5" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090Prop5Value" maxlength="17" style="width:155px;"/></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop5ValueDsp" name="rsv090Form" property="rsv090Prop5ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop5ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop5ValueDspNot" name="rsv090Form" property="rsv090Prop5ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop5ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName1" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090Prop1Value" style="width:125px;" maxlength="10" /></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop1ValueDsp" name="rsv090Form" property="rsv090Prop1ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop1ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop1ValueDspNot" name="rsv090Form" property="rsv090Prop1ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop1ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName2" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap>
      <span class="text_base"><html:radio styleId="2ka" name="rsv090Form" property="rsv090Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>" /><label for="2ka"><gsmsg:write key="cmn.accepted" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="2huka" name="rsv090Form" property="rsv090Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>" /><label for="2huka"><gsmsg:write key="cmn.not" /></label></span>
    </td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop2ValueDsp" name="rsv090Form" property="rsv090Prop2ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop2ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop2ValueDspNot" name="rsv090Form" property="rsv090Prop2ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop2ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName3" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap>
      <span class="text_base"><html:radio styleId="3ka" name="rsv090Form" property="rsv090Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>" /><label for="3ka"><gsmsg:write key="cmn.accepted" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="3huka" name="rsv090Form" property="rsv090Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>" /><label for="3huka"><gsmsg:write key="cmn.not" /></label></span>
    </td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop3ValueDsp" name="rsv090Form" property="rsv090Prop3ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop3ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop3ValueDspNot" name="rsv090Form" property="rsv090Prop3ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop3ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName7" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap>
      <span class="text_base"><html:radio styleId="7ka" name="rsv090Form" property="rsv090Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>" /><label for="7ka"><gsmsg:write key="cmn.accepted" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="7huka" name="rsv090Form" property="rsv090Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>" /><label for="7huka"><gsmsg:write key="cmn.not" /></label></span>
    </td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop7ValueDsp" name="rsv090Form" property="rsv090Prop7ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop7ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop7ValueDspNot" name="rsv090Form" property="rsv090Prop7ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop7ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:equal name="rsv090Form" property="rsv090apprKbnInput" value="true">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.appr.set.title" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap>
      <logic:equal name="rsv090Form" property="rsv090sisGrpAdmFlg" value="false">
        <span class="text_base"><gsmsg:write key="reserve.rsv090.4" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090Form" property="rsv090sisGrpAdmFlg" value="false">
        <logic:equal name="rsv090Form" property="rsv090sisGrpApprFlg" value="true">
          <span class="text_base"><gsmsg:write key="reserve.appr.set.kbn1" />
        </logic:equal>
        <logic:notEqual name="rsv090Form" property="rsv090sisGrpApprFlg" value="true">
          <span class="text_base"><html:radio styleId="apprKbn1" name="rsv090Form" property="rsv090apprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSD_APPR_KBN_NOSET) %>" /><label for="apprKbn1"><gsmsg:write key="reserve.appr.set.kbn2" /></label></span>&nbsp;
          <span class="text_base"><html:radio styleId="apprKbn2" name="rsv090Form" property="rsv090apprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSD_APPR_KBN_APPR) %>" /><label for="apprKbn2"><gsmsg:write key="reserve.appr.set.kbn1" /></label></span>
        </logic:notEqual>
      </logic:notEqual>
    </td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="apprKbnDsp" name="rsv090Form" property="rsv090apprKbnDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="apprKbnDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="apprKbnDspNot" name="rsv090Form" property="rsv090apprKbnDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="apprKbnDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:equal>

    <logic:notEmpty name="rsv090Form" property="rsv090PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><bean:write name="rsv090Form" property="rsv090PropHeaderName6" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090Prop6Value" style="width:71px;" maxlength="4" />&nbsp;<span class="text_base"><gsmsg:write key="cmn.days.after" /></span></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="prop6ValueDsp" name="rsv090Form" property="rsv090Prop6ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="prop6ValueDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="prop6ValueDspNot" name="rsv090Form" property="rsv090Prop6ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="prop6ValueDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    <td align="left" class="td_wt6" width="50%" nowrap>
      <textarea styleClass="text_base_rsv" name="rsv090Biko" style="width:541px;" rows="6" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="rsv090Form" property="rsv090Biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="bikoDsp" name="rsv090Form" property="rsv090BikoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="bikoDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="bikoDspNot" name="rsv090Form" property="rsv090BikoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="bikoDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.59" /></span>
    <td align="left" width="60%" class="td_type1">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td>
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.attached" />" onclick="buttonPush('sisetu_img_toroku');">
      </td>
      </tr>
      <logic:notEmpty name="rsv090Form" property="rsv090SisetuFileLabelList" scope="request">
        <tr>
        <td width="100%" colspan="2">
        <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
        </td>
        </tr>
        <tr>
        <td width="40%" nowrap class="td_type_rsv_pop" align="center">
          <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
        </td>
        <td width="60%" nowrap class="td_type_rsv_pop" align="center">
          <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
        </td>
        </tr>

        <logic:iterate id="fileMdl" name="rsv090Form" property="rsv090SisetuFileLabelList" scope="request">
        <tr>
        <td class="td_type1" nowrap align="center">
          <img src="../reserve/rsv090.do?CMD=getImageFileSisetu&rsv090BinSid=<bean:write name="fileMdl" property="value" />" alt="<gsmsg:write key="reserve.17" />" border="1" class="img_hoge">
        </td>
        <td class="td_type1" nowrap align="center">
          <span class="text_base2"><bean:write name="fileMdl" property="label" /></span>
        </td>
        </tr>
        </logic:iterate>
      </logic:notEmpty>
      </table>

    </td>
    <td align="left" valign="top" width="20%" class="td_type1">
    <logic:notEmpty name="rsv090Form" property="rsv090SisetuFileLabelList" scope="request">
      <html:select property="rsv090SisetuImgDefoValue" styleClass="select04">
        <html:optionsCollection name="rsv090Form" property="rsv090SisetuFileLabelList" value="value" label="label" />
      </html:select><br>
      <span class="text_base"><html:radio styleId="sisetuImgDefoDsp" name="rsv090Form" property="rsv090SisetuImgDefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="sisetuImgDefoDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="sisetuImgDefoDspNot" name="rsv090Form" property="rsv090SisetuImgDefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="sisetuImgDefoDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </logic:notEmpty>
    </td>
    </tr>

    <logic:notEmpty name="rsv090Form" property="place">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.location.comments" /></span></td>
    <td align="left" class="td_wt6" width="50%" nowrap><html:text styleClass="text_base_rsv" name="rsv090Form" property="rsv090PlaceComment" style="width:335px;" maxlength="50" /></td>
    <td align="left" class="td_wt2" width="40%" nowrap>
      <span class="text_base"><html:radio styleId="placeCommentDsp" name="rsv090Form" property="rsv090PlaceCommentDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" /><label for="placeCommentDsp"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <span class="text_base"><html:radio styleId="placeCommentDspNot" name="rsv090Form" property="rsv090PlaceCommentDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" /><label for="placeCommentDspNot"><gsmsg:write key="cmn.dont.show" /></label></span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.60" /></span>
    <td align="left" class="td_type1" colspan="2">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td>
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.attached" />" onclick="buttonPush('place_img_toroku');">
      </td>
      </tr>
      <logic:notEmpty name="rsv090Form" property="place">
         <% String commentDsp = "commentDsp"; %>
         <% String commentDspNot = "commentDspNot"; %>
         <% String commentDspVal = ""; %>
         <% String commentDspNotVal = ""; %>

         <% int count = 0; %>
         <tr>
         <td width="100%" colspan="3">
         <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
         </td>
         </tr>

         <tr>
         <td width="25%" nowrap class="td_type_rsv_pop" align="center">
           <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
         </td>
         <td width="15%" nowrap class="td_type_rsv_pop" align="center">
           <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
         </td>
         <td width="40%" nowrap class="td_type_rsv_pop" align="center">
           <span class="text_base_rsv3"><gsmsg:write key="cmn.comment" /></span>
         </td>
         </tr>
        <logic:iterate id="place" name="rsv090Form" property="place" scope="request">
         <% commentDspVal = commentDsp + count; %>
         <% commentDspNotVal = commentDspNot + count; %>
         <tr>
         <td class="td_type1" nowrap align="center">
          <img src="../reserve/rsv090.do?CMD=getImageFilePlace&rsv090BinSid=<bean:write name="place" property="rsv090PlaceFileValue" />" alt="<gsmsg:write key="reserve.66" />" border="1" class="img_hoge">
          <html:hidden name="place" property="rsv090PlaceFileValue" indexed="true" />
         </td>
         <td class="td_type1" nowrap align="center">
           <span class="text_base2"><bean:write name="place" property="rsv090PlaceFileLabel" /></span>
           <html:hidden name="place" property="rsv090PlaceFileLabel" indexed="true" />
         </td>
         <td class="td_type1" nowrap align="center">
           <html:text styleClass="text_base_rsv" name="place" property="rsv090PlaceFileComment" indexed="true" style="width:333px;" maxlength="50" />
         </td>
         </tr>
         <% count++; %>
        </logic:iterate>
      </logic:notEmpty>
      </table>
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
      <input type="submit" value="OK" class="btn_ok1">

      <logic:equal name="rsv090Form" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('sisetu_sakuzyo_kakunin');">
      </logic:equal>

      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_zyoho_settei');">
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