<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
  <title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.rsv111kn.1" /> ]</title>
</logic:equal>
<logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true">
  <title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.rsv111kn.2" /> ]</title>
</logic:equal>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/rsv111.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="showOrHide();">
<html:form action="/reserve/rsv111kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvPrintUseKbn" />
<html:hidden property="rsv110SisetuKbn" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv111knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv111knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>
<html:hidden property="rsv110ProcMode" />
<html:hidden property="rsv110InitFlg" />
<html:hidden property="rsv110RsySid" />
<html:hidden property="rsv110RsdSid" />
<html:hidden property="rsv110SinkiDefaultDate" />
<html:hidden property="rsv110ScdRsSid" />
<html:hidden property="rsv110Mokuteki" />
<html:hidden property="rsv110SelectedYearFr" />
<html:hidden property="rsv110SelectedMonthFr" />
<html:hidden property="rsv110SelectedDayFr" />
<html:hidden property="rsv110SelectedHourFr" />
<html:hidden property="rsv110SelectedMinuteFr" />
<html:hidden property="rsv110SelectedYearTo" />
<html:hidden property="rsv110SelectedMonthTo" />
<html:hidden property="rsv110SelectedDayTo" />
<html:hidden property="rsv110SelectedHourTo" />
<html:hidden property="rsv110SelectedMinuteTo" />
<html:hidden property="rsv110Naiyo" />
<html:hidden property="rsv110RsyEdit" />
<html:hidden property="rsv110EditAuth" />
<html:hidden property="rsv110rejectDel" />
<html:hidden property="rsv110ApprBtnFlg" />
<html:hidden property="rsv111InitFlg" />
<html:hidden property="rsv111RsrRsid" />
<html:hidden property="rsv111RsrMok" />
<html:hidden property="rsv111RsrKbn" />
<html:hidden property="rsv111RsrWeek" />
<html:hidden property="rsv111RsrDay" />
<html:hidden property="rsv111RsrDayOfYearly" />
<html:hidden property="rsv111RsrMonthOfYearly" />
<html:hidden property="rsv111RsrDweek1" />
<html:hidden property="rsv111RsrDweek2" />
<html:hidden property="rsv111RsrDweek3" />
<html:hidden property="rsv111RsrDweek4" />
<html:hidden property="rsv111RsrDweek5" />
<html:hidden property="rsv111RsrDweek6" />
<html:hidden property="rsv111RsrDweek7" />
<html:hidden property="rsv111RsrTranKbn" />
<html:hidden property="rsv111RsrDateYearFr" />
<html:hidden property="rsv111RsrDateMonthFr" />
<html:hidden property="rsv111RsrDateDayFr" />
<html:hidden property="rsv111RsrDateYearTo" />
<html:hidden property="rsv111RsrDateMonthTo" />
<html:hidden property="rsv111RsrDateDayTo" />
<html:hidden property="rsv111RsrTimeHourFr" />
<html:hidden property="rsv111RsrTimeMinuteFr" />
<html:hidden property="rsv111RsrTimeHourTo" />
<html:hidden property="rsv111RsrTimeMinuteTo" />
<html:hidden property="rsv111RsrBiko" />
<html:hidden property="rsv111RsrEdit" />
<html:hidden property="rsv111ScdReflection" />
<html:hidden property="rsv111DeleteFlg" />
<html:hidden property="rsv111HeaderDspFlg" />
<html:hidden property="rsv111ExistSchDateFlg" />

<html:hidden property="rsv110Busyo" />
<html:hidden property="rsv110UseName" />
<html:hidden property="rsv110UseNum" />
<html:hidden property="rsv110UseKbn" />
<html:hidden property="rsv110Contact" />
<html:hidden property="rsv110Guide" />
<html:hidden property="rsv110ParkNum" />
<html:hidden property="rsv110PrintKbn"/>
<html:hidden property="rsv110Dest" />
<html:hidden property="rsv111Busyo" />
<html:hidden property="rsv111UseName" />
<html:hidden property="rsv111UseNum" />
<html:hidden property="rsv111UseKbn" />
<html:hidden property="rsv111Contact" />
<html:hidden property="rsv111Guide" />
<html:hidden property="rsv111ParkNum" />
<html:hidden property="rsv111PrintKbn"/>
<html:hidden property="rsv111Dest" />

<logic:notEmpty name="rsv111knForm" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv111knForm" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv110GroupSid" />
<logic:notEmpty name="rsv111knForm" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="rsv111knForm" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv111GroupSid" />
<logic:notEmpty name="rsv111knForm" property="rsv111SvUsers" scope="request">
  <logic:iterate id="ulExBean" name="rsv111knForm" property="rsv111SvUsers" scope="request">
    <input type="hidden" name="rsv111SvUsers" value="<bean:write name="ulExBean" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv110SchKbn" />
<html:hidden property="rsv110SchGroupSid" />
<html:hidden property="rsv111SchKbn" />
<html:hidden property="rsv111SchGroupSid" />

<bean:define id="rsvSisKbn" name="rsv111knForm" property="rsv110SisetuKbn" type="java.lang.Integer" />
<% int sisKbn = rsvSisKbn; %>

<input type="hidden" name="helpPrm" value="<bean:write name ="rsv111knForm" property="rsv110SisetuKbn"/>_<bean:write name="rsv111knForm" property="rsv110ProcMode" />" />

<logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true">
  <input type="hidden" name="helpPrm" value="del">
</logic:equal>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
      [ <gsmsg:write key="reserve.rsv111kn.1" /> ]
      </logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true">
      [ <gsmsg:write key="reserve.rsv111kn.2" /> ]
      </logic:equal>
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
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('kurikaesi_delete_kakutei');">
      </logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('kurikaesi_toroku_kakutei');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_kurikaeshi_inp');">
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

    <div id="longHeader">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table width="100%">
      <tr>
      <td align="left" width="100%" class="text_base2" nowrap><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110GrpName" /></span></td>
      <td align="right" width="0%" nowrap><input type="button" value="<gsmsg:write key="cmn.hide" />" class="btn_base1s" onClick="hideText();">&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110SisetuKbnName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110SisetuName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.asset.register.num" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110SisanKanri" /></span></td>
    </tr>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName4" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110Prop4Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName5" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110Prop5Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName1" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110Prop1Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName2" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111knForm" property="rsv110Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv111knForm" property="rsv110Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName3" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111knForm" property="rsv110Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv111knForm" property="rsv110Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName7" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111knForm" property="rsv110Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv111knForm" property="rsv110Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111knForm" property="rsv110PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111knForm" property="rsv110PropHeaderName6" /></span></td>
    <td align="left" class="td_type1">
      <logic:notEmpty name="rsv111knForm" property="rsv110Prop6Value"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110Prop6Value" /><gsmsg:write key="cmn.days.after" /></logic:notEmpty></span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <bean:write name="rsv111knForm" property="rsv110Biko" filter="false" /></span>
    </td>
    </tr>
    </table>
    </div>

    <div id="shortHeader">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table width="100%">
      <tr>
      <td align="left" width="100%" class="text_base2" nowrap><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110SisetuName" /></span></td>
      <td align="right" width="0%" nowrap><input type="button" value="<gsmsg:write key="cmn.show" />" class="btn_base1s" onClick="showText();">&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>
    </table>

    </div>

  </td>
  </tr>
  <tr>
  <td><br>
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

    <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_type1" width="80%"><span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv110Torokusya" /></span></td>
    </tr>

    <logic:equal name="rsv111knForm" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
        <logic:equal name="rsv111knForm" property="rsvPrintUseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_PRINT_USE_YES) %>">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">印刷</span></td>
        <td align="left" class="td_type1"><span class="text_base_rsv">
        <logic:equal name="rsv111knForm" property="rsv111PrintKbn" value="1">印刷する</logic:equal>
        <logic:notEqual name="rsv111knForm" property="rsv111PrintKbn" value="1">印刷しない</logic:notEqual>
        </span></td>
        </tr>
        </logic:equal>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.72" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv111RsrMok" /></span>
    </td>
    </tr>

    <logic:equal name="rsv111knForm" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">利用区分</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv">
      <logic:equal name="rsv111knForm" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>">未設定</label></logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>">会議</label></logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>">研修</label></logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>">その他</label></logic:equal>
      </span>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:equal>


    <% if (sisKbn ==jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA
            || sisKbn == jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) { %>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">連絡先</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv111Contact"/></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <% } %>

    <logic:equal name="rsv111knForm" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">会議名案内</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv111Guide"/></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">駐車場見込み台数</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv111ParkNum"/></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </logic:equal>
    <logic:equal name="rsv111knForm" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <!-- 行先 -->
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">行先</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv111Dest"/></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:equal>
    </logic:equal>


    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap>
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true"><span class="text_bb1"><gsmsg:write key="reserve.rsv111kn.3" /></span></logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false"><span class="text_bb1"><gsmsg:write key="reserve.rsv111kn.4" /></span></logic:equal>
    </td>
    <td align="left" class="td_type1" width="80%">
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td colspan="3">
        <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true"><span class="text_r1"><gsmsg:write key="reserve.rsv111kn.5" /></span></logic:equal>
        <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false"><span class="text_r1"><gsmsg:write key="reserve.rsv111kn.6" /></span></logic:equal>
      </td>
      </tr>
      <tr>
      <td width="0%" align="left" valign="top">

        <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>">
          <logic:iterate id="day" name="rsv111knForm" property="targetDay" scope="request" indexId="idx">
            <br>
            <span class="text_base"><bean:write name="day" /></span>
          </logic:iterate>
          <br><br>
        </logic:equal>

        <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
          <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false"><span class="text_base_rsv"><br>【<gsmsg:write key="reserve.rsv111kn.7" />】</span></logic:equal>
          <logic:iterate id="day" name="rsv111knForm" property="oldDay" scope="request" indexId="idx"><br><span class="text_base"><bean:write name="day" /></span></logic:iterate><br><br>
        </logic:equal>
      </td>

      <td width="0%" align="left" valign="middle" nowrap>
        <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>">&nbsp;</logic:equal>
        <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
          <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
            <img src="../reserve/images/spacer.gif" width="7px" height="1px" border="0" alt="">
            <img src="../reserve/images/arrow_east.gif" border="0" alt="">
            <img src="../reserve/images/spacer.gif" width="7px" height="1px" border="0" alt="">
          </logic:equal>
        </logic:equal>
      </td>

      <td width="100%" align="left" valign="top">
        <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>">&nbsp;</logic:equal>
        <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>"><logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false"><span class="text_base_rsv"><br>【<gsmsg:write key="reserve.rsv111kn.8" />】</span><logic:iterate id="newDay" name="rsv111knForm" property="targetDay" scope="request" indexId="idx"><br><span class="text_base"><bean:write name="newDay" /></span></logic:iterate><br><br></logic:equal></logic:equal>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.period" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <bean:write name="rsv111knForm" property="yoyakuFrString" />&nbsp;～&nbsp;<bean:write name="rsv111knForm" property="yoyakuToString" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.time" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <bean:write name="rsv111knForm" property="yoyakuTimeFrString" />&nbsp;～&nbsp;<bean:write name="rsv111knForm" property="yoyakuTimeToString" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv"><bean:write name="rsv111knForm" property="rsv111knRsrBiko" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111knForm" property="rsv111RsrEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>"><gsmsg:write key="cmn.nolimit" /></logic:equal>
        <logic:equal name="rsv111knForm" property="rsv111RsrEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>"><gsmsg:write key="cmn.only.principal.or.registant" /></logic:equal>
        <logic:equal name="rsv111knForm" property="rsv111RsrEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>"><gsmsg:write key="cmn.only.affiliation.group.membership" /></logic:equal>
      </span>
    </td>
    </tr>

    <% if (sisKbn ==jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA
            || sisKbn == jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) { %>
   <% String headName=""; %>

    <logic:equal name="rsv111knForm" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <% headName="担当者名"; %>
    </logic:equal>
    <logic:equal name="rsv111knForm" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <% headName="使用者名"; %>
    </logic:equal>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">担当部署/<%= headName %>/人数</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
         <span class="text_base_rsv">
         <logic:notEmpty name="rsv111knForm" property="rsv111Busyo">
            担当部署：&nbsp;<bean:write name="rsv111knForm" property="rsv111Busyo"/>
         </logic:notEmpty>
         <logic:notEmpty name="rsv111knForm" property="rsv111UseName">
           <br><%= headName %>：&nbsp;<bean:write name="rsv111knForm" property="rsv111UseName"/>
         </logic:notEmpty>
         <logic:notEmpty name="rsv111knForm" property="rsv111UseNum">
            <br>他&nbsp;&nbsp;<bean:write name="rsv111knForm" property="rsv111UseNum"/>人
         </logic:notEmpty>
         </span>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <% } %>

    </logic:equal>

    <logic:equal name="rsv111knForm" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
      <logic:equal name="rsv111knForm" property="rsv110EditAuth" value="true">
        <logic:equal name="rsv111Form" property="rsv111ExistSchDateFlg" value="true">
        <logic:greaterThan name="rsv111knForm" property="rsv110ScdRsSid" value="0">
        <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.85" /></span></td>
        <td align="left" class="td_type1">
          <span class="text_base_rsv">
            <logic:equal name="rsv111knForm" property="rsv111ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>" ><gsmsg:write key="reserve.86" /></logic:equal>
            <logic:equal name="rsv111knForm" property="rsv111ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>" ><gsmsg:write key="reserve.87" /></logic:equal>
          </span>
        </td>
        </tr>
        </logic:greaterThan>
        </logic:equal>
      </logic:equal>
    </logic:equal>

    <logic:notEmpty name="rsv111knForm" property="rsvSchUserNameArray" scope="request">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.3" /></span></td>
      <td align="left" class="td_type1">

        <logic:iterate id="usrName" name="rsv111knForm" property="rsvSchUserNameArray" scope="request">
          <span class="text_base_rsv">
            <bean:write name="usrName" /><br>
          </span>
        </logic:iterate>

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
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="true">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('kurikaesi_delete_kakutei');">
      </logic:equal>
      <logic:equal name="rsv111knForm" property="rsv111DeleteFlg" value="false">
        <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('kurikaesi_toroku_kakutei');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_kurikaeshi_inp');">
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