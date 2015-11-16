<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />
  <logic:equal name="rsv090knForm" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [ <gsmsg:write key="reserve.rsv090kn.1" /> ]</logic:equal>
  <logic:equal name="rsv090knForm" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">[ <gsmsg:write key="reserve.rsv090kn.2" /> ]</logic:equal>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv090kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
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

<body class="body_03" onload="initPictureRsv();">
<html:form action="/reserve/rsv090kn">
<input type="hidden" name="CMD" value="">

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
<html:hidden property="rsv090SisetuId" />
<html:hidden property="rsv090SisetuName" />
<html:hidden property="rsv090SisanKanri" />
<html:hidden property="rsv090Prop1Value" />
<html:hidden property="rsv090Prop2Value" />
<html:hidden property="rsv090Prop3Value" />
<html:hidden property="rsv090Prop4Value" />
<html:hidden property="rsv090Prop5Value" />
<html:hidden property="rsv090Prop6Value" />
<html:hidden property="rsv090Prop7Value" />
<html:hidden property="rsv090Biko" />
<html:hidden property="rsv090apprKbn" />
<html:hidden property="rsv090SisetuImgDefoValue" />
<html:hidden property="rsv090PlaceComment" />

<html:hidden property="rsv090SisetuIdDspKbn" />
<html:hidden property="rsv090SisanKanriDspKbn" />
<html:hidden property="rsv090Prop1ValueDspKbn" />
<html:hidden property="rsv090Prop2ValueDspKbn" />
<html:hidden property="rsv090Prop3ValueDspKbn" />
<html:hidden property="rsv090Prop4ValueDspKbn" />
<html:hidden property="rsv090Prop5ValueDspKbn" />
<html:hidden property="rsv090Prop6ValueDspKbn" />
<html:hidden property="rsv090Prop7ValueDspKbn" />
<html:hidden property="rsv090BikoDspKbn" />
<html:hidden property="rsv090PlaceCommentDspKbn" />
<html:hidden property="rsv090SisetuImgDefoDspKbn" />
<html:hidden property="rsv090apprKbnDspKbn" />
<html:hidden property="rsv090sisGrpApprFlg" />


<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv090knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv090knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv090knForm" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv090knForm" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<bean:write name="rsv090knForm" property="rsv090ProcMode" />">

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
      <logic:equal name="rsv090knForm" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [ <gsmsg:write key="reserve.rsv090kn.1" /> ]</logic:equal>
      <logic:equal name="rsv090knForm" property="rsv090ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">[ <gsmsg:write key="reserve.rsv090kn.2" /> ]</logic:equal>
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
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('sisetu_settei_kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_settei_input');">
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
    <td align="left" class="td_type1" width="60%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090GrpName" /></span></td>
    <td class="td_type1" width="20%" align="center" rowspan="2" nowrap><gsmsg:write key="reserve.view.sisetu.dsp.kbn" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1" width="80%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090SisetuKbnName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.55" /></span></td>
    <td align="left" class="td_wt6" width="50%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090SisetuId" /></span></td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090SisetuIdDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090SisetuIdDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1" width="60%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090SisetuName" /></span></td>
    <td align="left" class="td_type1" width="20%"></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.asset.register.num" /></span></td>
    <td align="left" class="td_wt6" width="50%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090SisanKanri" /></span></td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090SisanKanriDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090SisanKanriDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName4" /></span></td>
    <td align="left" class="td_wt6" width="50%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090Prop4Value" /></span></td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop4ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop4ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName5" /></span></td>
    <td align="left" class="td_wt6" width="50%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090Prop5Value" /></span></td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop5ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop5ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName1" /></span></td>
    <td align="left" class="td_wt6" width="50%"><span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090Prop1Value" /></span></td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop1ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop1ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName2" /></span></td>
    <td align="left" class="td_wt6" width="50%">
      <span class="text_base_rsv">
        <logic:equal name="rsv090knForm" property="rsv090Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv090knForm" property="rsv090Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop2ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop2ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName3" /></span></td>
    <td align="left" class="td_wt6" width="50%">
      <span class="text_base_rsv">
        <logic:equal name="rsv090knForm" property="rsv090Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv090knForm" property="rsv090Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop3ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop3ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName7" /></span></td>
    <td align="left" class="td_wt6" width="50%">
      <span class="text_base_rsv">
        <logic:equal name="rsv090knForm" property="rsv090Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv090knForm" property="rsv090Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop7ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop7ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:equal name="rsv090Form" property="rsv090apprKbnInput" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.appr.set.title" /></span></td>
    <td align="left" class="td_wt6" width="50%">
      <logic:equal name="rsv090knForm" property="rsv090sisGrpAdmFlg" value="false">
        <span class="text_base"><gsmsg:write key="reserve.rsv090.4" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090sisGrpAdmFlg" value="false">
        <logic:equal name="rsv090knForm" property="rsv090sisGrpApprFlg" value="true">
          <span class="text_base"><gsmsg:write key="reserve.appr.set.kbn1" /></span>
        </logic:equal>
        <logic:notEqual name="rsv090knForm" property="rsv090sisGrpApprFlg" value="true">
          <span class="text_base_rsv">
            <logic:equal name="rsv090knForm" property="rsv090apprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSD_APPR_KBN_NOSET) %>"><gsmsg:write key="reserve.appr.set.kbn2" /></logic:equal>
            <logic:equal name="rsv090knForm" property="rsv090apprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSD_APPR_KBN_APPR) %>"><gsmsg:write key="reserve.appr.set.kbn1" /></logic:equal>
          </span>
        </logic:notEqual>
      </logic:notEqual>
    </td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090apprKbnDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090apprKbnDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:equal>

    <logic:notEmpty name="rsv090knForm" property="rsv090PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv090knForm" property="rsv090PropHeaderName6" /></span></td>
    <td align="left" class="td_wt6" width="50%">
      <span class="text_base_rsv">
      <logic:notEmpty name="rsv090knForm" property="rsv090Prop6Value">
        <bean:write name="rsv090knForm" property="rsv090Prop6Value" /><gsmsg:write key="cmn.days.after" />
      </logic:notEmpty>
      </span>
    </td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090Prop6ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090Prop6ValueDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    <td align="left" class="td_wt6" width="50%">
      <span class="text_base_rsv"><bean:write name="rsv090knForm" property="rsv090knBiko" filter="false" /></span>
    </td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090BikoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090BikoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>

    <logic:notEmpty name="rsv090knForm" property="rsv090knSisetuFileLabelList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.rsv090kn.3" /></span>
    <td align="left" class="td_type1" width="60%">
    <logic:equal name="rsv090knForm" property="rsv090knSetImgFlg" value="1">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="40%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
      </td>
      <td width="40%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
      </td>
      </tr>
      <tr>
      <td class="td_type1" nowrap align="center">
        <img src="../reserve/rsv090.do?CMD=getImageFileSisetu&rsv090BinSid=<bean:write name="rsv090knForm" property="rsv090SisetuImgDefoValue" />" alt="<gsmsg:write key="reserve.rsv090kn.4" />" border="1" class="img_hoge">
      </td>
      <td class="td_type1" nowrap align="center">
        <span class="text_base2"><bean:write name="rsv090knForm" property="rsv090knDefoDspImgName" /></span></label>
      </td>
      </tr>
    </table>
    </logic:equal>
    </td>

    <td align="left" width="20%" class="td_type1">
      <logic:equal name="rsv090knForm" property="rsv090SisetuImgDefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090SisetuImgDefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090knSisetuFileLabelList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.59" /></span>
    <td align="left" class="td_type1" width="60%">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="40%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
      </td>
      <td width="60%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
      </td>
      </tr>
      <logic:iterate id="fileMdl" name="rsv090knForm" property="rsv090knSisetuFileLabelList" scope="request">
      <tr>
      <td class="td_type1" nowrap align="center">
        <img src="../reserve/rsv090.do?CMD=getImageFileSisetu&rsv090BinSid=<bean:write name="fileMdl" property="value" />" alt="<gsmsg:write key="reserve.17" />" border="1" class="img_hoge">
      </td>
      <td class="td_type1" nowrap align="center">
        <span class="text_base_rsv"><bean:write name="fileMdl" property="label" /></span>
      </td>
      </tr>
      </logic:iterate>
      </tr>
    </table>
    </td>
    <td align="left" width="20%" class="td_type1"></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv090knForm" property="rsv090knPlaceFileLabelList" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.location.comments" /></span></td>
    <td align="left" class="td_wt6" width="50%"><span class="text_base"><bean:write name="rsv090knForm" property="rsv090PlaceComment" /></span></td>
    <td align="left" class="td_wt2" width="30%">
      <logic:equal name="rsv090knForm" property="rsv090PlaceCommentDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_r1"><gsmsg:write key="cmn.display.ok" /></span>
      </logic:equal>
      <logic:notEqual name="rsv090knForm" property="rsv090PlaceCommentDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>">
        <span class="text_base"><gsmsg:write key="cmn.dont.show" /></span>
      </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.60" /></span>
    <td align="left" class="td_type1" colspan="2">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="25%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.image" /></span>
      </td>
      <td width="15%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="fil.9" /></span>
      </td>
      <td width="60%" nowrap class="td_type_rsv_pop" align="center">
        <span class="text_base_rsv3"><gsmsg:write key="cmn.comment" /></span>
      </td>
      </tr>
      <logic:iterate id="fileMdl2" name="rsv090knForm" property="rsv090knPlaceFileLabelList" scope="request" indexId="idx">
      <tr>
      <td class="td_type1" nowrap align="center">
        <img src="../reserve/rsv090.do?CMD=getImageFilePlace&rsv090BinSid=<bean:write name="fileMdl2" property="value" />" alt="<gsmsg:write key="reserve.66" />" border="1" class="img_hoge">
      </td>
      <td class="td_type1" nowrap align="center">
        <span class="text_base_rsv"><bean:write name="fileMdl2" property="label" /></span>
      </td>
      <% if (idx.intValue() == 0) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment1" />
      </td>
      <% } else if (idx.intValue() == 1) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment2" />
      </td>
      <% } else if (idx.intValue() == 2) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment3" />
      </td>
      <% } else if (idx.intValue() == 3) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment4" />
      </td>
      <% } else if (idx.intValue() == 4) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment5" />
      </td>
      <% } else if (idx.intValue() == 5) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment6" />
      </td>
      <% } else if (idx.intValue() == 6) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment7" />
      </td>
      <% } else if (idx.intValue() == 7) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment8" />
      </td>
      <% } else if (idx.intValue() == 8) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment9" />
      </td>
      <% } else if (idx.intValue() == 9) { %>
      <td class="td_type1" nowrap align="center">
        <bean:write name="rsv090knForm" property="rsv090knPlaceFileComment10" />
      </td>
      <% } %>
      </tr>
      </logic:iterate>
      </tr>
    </table>
    </td>
    </tr>
    </logic:notEmpty>
  </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('sisetu_settei_kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_settei_input');">
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