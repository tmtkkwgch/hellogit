<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.rsv.GSConstReserve" %>
<%@ page import="jp.groupsession.v2.rsv.rsv310.Rsv310Form" %>

<% String maxLengthNaiyo = String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.MAX_LENGTH_NAIYO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />
  <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [ <gsmsg:write key="reserve.19" /> ]</logic:equal>
  <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
    <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">[ <gsmsg:write key="reserve.rsv110.1" /> ]</logic:notEqual>
    <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">[ <gsmsg:write key="reserve.rsv110.2" /> ]</logic:equal>
  </logic:equal>
  <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">[ <gsmsg:write key="reserve.rsv110.1" /> ]</logic:equal>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../reserve/js/rsv110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/rsvschedule.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/reservepopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/textarea_autoresize.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% boolean editSchFlg = false; %>
<logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
  <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
    <logic:equal name="rsv110Form" property="rsv110ExistSchDateFlg" value="true">
    <logic:greaterThan name="rsv110Form" property="rsv110ScdRsSid" value="0">
      <% editSchFlg = true; %>
    </logic:greaterThan>
    </logic:equal>
  </logic:equal>
</logic:equal>


<% String showScript = ""; %>
<logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
  <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
    <% showScript = "showLengthId($(\'#inputstr\')[0], " + maxLengthNaiyo + ", \'inputlength\');rsvSchChange();"; %>
  </logic:notEqual>
</logic:equal>

<body class="body_03" onunload="calWindowClose();windowClose();" onload="showOrHide();<%= showScript %><% if (editSchFlg) { %>rsvSchDisabled();<% } %>">

<html:form action="/reserve/rsv110">
<input type="hidden" name="CMD" value="sisetu_yoyaku_kakunin">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvPrintUseKbn" />
<html:hidden property="rsv110ProcMode" />
<html:hidden property="rsv110InitFlg" />
<html:hidden property="rsv110RsySid" />
<html:hidden property="rsv110RsdSid" />
<html:hidden property="rsv110SinkiDefaultDate" />
<html:hidden property="rsv110ScdRsSid" />
<html:hidden property="rsv110EditAuth" />
<html:hidden property="rsv110ApprBtnFlg" />
<html:hidden property="rsv110rejectDel"/>
<html:hidden property="rsv110SisetuKbn" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv110Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv110Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv111InitFlg" />
<html:hidden property="rsv111RsrRsid" />
<html:hidden property="rsv111RsrKbn" />
<html:hidden property="rsv111RsrDweek1" />
<html:hidden property="rsv111RsrDweek2" />
<html:hidden property="rsv111RsrDweek3" />
<html:hidden property="rsv111RsrDweek4" />
<html:hidden property="rsv111RsrDweek5" />
<html:hidden property="rsv111RsrDweek6" />
<html:hidden property="rsv111RsrDweek7" />
<html:hidden property="rsv111RsrWeek" />
<html:hidden property="rsv111RsrDay" />
<html:hidden property="rsv111RsrDayOfYearly" />
<html:hidden property="rsv111RsrMonthOfYearly" />
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
<html:hidden property="rsv111RsrMok" />
<html:hidden property="rsv111RsrBiko" />
<html:hidden property="rsv111RsrEdit" />
<html:hidden property="rsv111ScdReflection" />

<html:hidden property="rsv111Busyo" />
<html:hidden property="rsv111UseName" />
<html:hidden property="rsv111UseNum" />
<html:hidden property="rsv111UseKbn" />
<html:hidden property="rsv111Contact" />
<html:hidden property="rsv111Guide" />
<html:hidden property="rsv111ParkNum" />
<html:hidden property="rsv111PrintKbn"/>
<html:hidden property="rsv111Dest" />

<html:hidden property="rsv110HeaderDspFlg" />
<html:hidden property="rsv110ExistSchDateFlg" />


<logic:notEmpty name="rsv110Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv110Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv110Form" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="rsv110Form" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv111GroupSid" />
<logic:notEmpty name="rsv110Form" property="rsv111SvUsers" scope="request">
  <logic:iterate id="ulExBean" name="rsv110Form" property="rsv111SvUsers" scope="request">
    <input type="hidden" name="rsv111SvUsers" value="<bean:write name="ulExBean" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv111SchKbn" />
<html:hidden property="rsv111SchGroupSid" />

<logic:notEmpty name="rsv110Form" property="rsv110SchNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="rsv110Form" property="rsv110SchNotAccessGroupList">
    <input type="hidden" name="rsvSchNotAccessGroup" value="<bean:write name="notAccessGroup" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv110Form" property="rsv110SchNotAccessUserList" scope="request">
  <logic:iterate id="notAccessUser" name="rsv110Form" property="rsv110SchNotAccessUserList">
    <input type="hidden" name="rsvSchNotAccessUser" value="<bean:write name="notAccessUser" />">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="rsvSisKbn" name="rsv110Form" property="rsv110SisetuKbn" type="java.lang.Integer" />
<% int sisKbn = rsvSisKbn; %>

<input type="hidden" name="helpPrm" value="<bean:write name ="rsv110Form" property="rsv110SisetuKbn"/>_<bean:write name="rsv110Form" property="rsv110ProcMode" />" />

<logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
  <%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
</logic:notEqual>

<table width="100%">
<tr>
<td width="100%" align="center">

<logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
</logic:notEqual>
<logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
</logic:equal>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">
      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [ <gsmsg:write key="reserve.19" /> ]</logic:equal>
      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">[ <gsmsg:write key="reserve.rsv110.1" /> ]</logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">[ <gsmsg:write key="reserve.rsv110.2" /> ]</logic:equal>
      </logic:equal>
      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">[ <gsmsg:write key="reserve.rsv110.1" /> ]</logic:equal>
      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_COPY_ADD) %>"> [ <gsmsg:write key="reserve.19" /> ]</logic:equal>
    </td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <input type="button" value="<gsmsg:write key="cmn.for.repert" />" class="btn_base1" onclick="buttonPush('kurikaeshi');">
          <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
            <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush('copytouroku');">
          </logic:equal>
        </logic:notEqual>
      </logic:equal>
      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_EDIT) %>">
          <input type="button" value="<gsmsg:write key="cmn.pdf" />" class="btn_pdf_n1" onClick="buttonPush('pdf');">
      </logic:equal>
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td width="50%" class="header_glay_bg">
      <bean:define id="strRsv110ProcMode" name="rsv110Form" property="rsv110ProcMode" type="java.lang.String" />
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
      </logic:notEqual>
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= GSConstReserve.PROC_MODE_POPUP %>"><input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onclick="window.parent.callYokyakuWindowClose();"></logic:equal>
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= GSConstReserve.PROC_MODE_POPUP %>">

          <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('sisetu_yoyaku_kakunin');">
          <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_EDIT) %>"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('delete');"></logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
        </logic:notEqual>
      </logic:equal>
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>

    <% if (strRsv110ProcMode != null
    && (strRsv110ProcMode.equals(GSConstReserve.PROC_MODE_EDIT)
      || strRsv110ProcMode.equals(GSConstReserve.PROC_MODE_POPUP))) { %>
      <logic:equal name="rsv110Form" property="rsv110ApprBtnFlg" value="1">
      <tr>
      <td colspan="4" align="right" style="padding-top: 5px;">
        <input type="button" value="<gsmsg:write key="cmn.approval" />" id="syoninbtn" class="btn_syonin_n1">
        <input type="button" value="<gsmsg:write key="cmn.rejection" />" id="kyakkabtn" class="btn_kyakka_n1">
      </td>
      </tr>
      </logic:equal>
    <% } %>

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
      <table width="99%">
      <tr>
      <td align="left" width="100%" class="text_base2" nowrap><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110GrpName" /></span></td>
      <td align="right" width="0%" nowrap><input type="button" value="<gsmsg:write key="cmn.hide" />" class="btn_base1s" onClick="hideText();">&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110SisetuKbnName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110SisetuName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.asset.register.num" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110SisanKanri" /></span></td>
    </tr>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName4" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Prop4Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName5" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Prop5Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName1" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Prop1Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName2" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv110Form" property="rsv110Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv110Form" property="rsv110Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName3" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv110Form" property="rsv110Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv110Form" property="rsv110Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName7" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv110Form" property="rsv110Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv110Form" property="rsv110Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv110Form" property="rsv110PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv110Form" property="rsv110PropHeaderName6" /></span></td>
    <td align="left" class="td_type1">
      <logic:notEmpty name="rsv110Form" property="rsv110Prop6Value"><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Prop6Value" /><gsmsg:write key="cmn.days.after" /></logic:notEmpty></span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <bean:write name="rsv110Form" property="rsv110Biko" filter="false" /></span>
    </td>
    </tr>

    </table>
    </div>

    <div id="shortHeader">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table width="99%">
      <tr>
      <td align="left" width="100%" class="text_base2" nowrap><span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110SisetuName" /></span></td>
      <td align="right" width="0%" nowrap><input type="button" value="<gsmsg:write key="cmn.show" />" class="btn_base1s" onClick="showText();">&nbsp;</td>
      </tr>
      </table>
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
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Torokusya" /></span>
      </td>
      <td width="40%" align="left" nowrap>
      <span class="text_base"><bean:write name="rsv110Form" property="rsv110AddDate" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>


<!-- 印刷 -->
    <logic:equal name="rsv110Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
       <logic:equal name="rsv110Form" property="rsvPrintUseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_PRINT_USE_YES) %>">
       <tr>
       <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.print" /></span></td>
       <td align="left" class="td_type1" width="80%">
         <table class="tl0" width="99%">
         <tr>
         <td width="60%" nowrap>
         <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
           <logic:equal name="rsv110Form" property="rsv110PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.yes" /></span></logic:equal>
           <logic:notEqual name="rsv110Form" property="rsv110PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.no" /></span></logic:notEqual>
         </logic:notEqual>

         <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
           <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
             <html:checkbox name="rsv110Form" property="rsv110PrintKbn" value="1" styleId="print"/><label for="print" class="text_base_rsv"><gsmsg:write key="reserve.print.yes" /></label>
           </logic:notEqual>
           <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
             <logic:equal name="rsv110Form" property="rsv110PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.yes" /></span></logic:equal>
             <logic:notEqual name="rsv110Form" property="rsv110PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.no" /></span></logic:notEqual>
           </logic:equal>
         </logic:equal>
         </td>
         </tr>
         </table>
       </td>
       </tr>
       </logic:equal>
    </logic:equal>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.72" /></span><logic:equal name="rsv110Form" property="rsv110EditAuth" value="true"><logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></logic:notEqual></logic:equal></td>
    <td align="left" class="td_type1">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Mokuteki" /></span>
        <html:hidden name="rsv110Form" property="rsv110Mokuteki" />
      </logic:notEqual>

      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="rsv110Form" property="rsv110Mokuteki" maxlength="50" style="width:501px;" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Mokuteki" /></span>
          <html:hidden name="rsv110Form" property="rsv110Mokuteki" />
        </logic:equal>
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <logic:equal name="rsv110Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.use.kbn" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv">
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
         <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>"><gsmsg:write key="reserve.use.kbn.noset" /></logic:equal>
         <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>"><gsmsg:write key="reserve.use.kbn.meeting" /></logic:equal>
         <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>"><gsmsg:write key="reserve.use.kbn.training" /></logic:equal>
         <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>"><gsmsg:write key="reserve.use.kbn.other" /></logic:equal>
      </logic:notEqual>

      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:radio name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>" styleId="rsyUkbnNoset"><label for="rsyUkbnNoset"><gsmsg:write key="reserve.use.kbn.noset" /></label></html:radio>&nbsp;
          <html:radio name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>" styleId="rsyUkbnKaigi"><label for="rsyUkbnKaigi"><gsmsg:write key="reserve.use.kbn.meeting" /></label></html:radio>&nbsp;
          <html:radio name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>" styleId="rsyUkbnKensyu"><label for="rsyUkbnKensyu"><gsmsg:write key="reserve.use.kbn.training" /></label></html:radio>&nbsp;
          <html:radio name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>" styleId="rsyUkbnOther"><label for="rsyUkbnOther"><gsmsg:write key="reserve.use.kbn.other" /></label></html:radio>
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>"><gsmsg:write key="reserve.use.kbn.noset" /></logic:equal>
          <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>"><gsmsg:write key="reserve.use.kbn.meeting" /></logic:equal>
          <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>"><gsmsg:write key="reserve.use.kbn.training" /></logic:equal>
          <logic:equal name="rsv110Form" property="rsv110UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>"><gsmsg:write key="reserve.use.kbn.other" /></logic:equal>
        </logic:equal>
      </logic:equal>
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
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.contact" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Contact" /></span>
      </logic:notEqual>
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="rsv110Form" property="rsv110Contact"  maxlength="20" style="width:155px;" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Contact" /></span>
        </logic:equal>
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <% } %>

    <logic:equal name="rsv110Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.guide" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Guide"/></span>
      </logic:notEqual>
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="rsv110Form" property="rsv110Guide" style="width:335px;" maxlength="50" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Guide"/></span>
        </logic:equal>
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.park.num" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110ParkNum" /></span>
      </logic:notEqual>
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="rsv110Form" property="rsv110ParkNum" maxlength="5" styleClass="text_base_rsv" style="text-align:right;width:65px;" />
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110ParkNum" /></span>
        </logic:equal>
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </logic:equal>

    <logic:equal name="rsv110Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <!-- 行先 -->
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.dest" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Dest" /></span>
      </logic:notEqual>
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="rsv110Form" property="rsv110Dest" maxlength="50" styleClass="text_base_rsv" style="width:335px;"/>
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Dest" /></span>
        </logic:equal>
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td rowspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.period" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span><logic:equal name="rsv110Form" property="rsv110EditAuth" value="true"><logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></logic:notEqual></logic:equal></td>
    <td align="left" class="td_wt">

      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
      <span class="text_base"><bean:write name="rsv110Form" property="yoyakuFrString" /></span>
      </logic:equal>

      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
      <span class="text_base"><bean:write name="rsv110Form" property="yoyakuFrString" /></span>
      </logic:notEqual>

      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">

        <html:select property="rsv110SelectedYearFr" styleId="fromYear">
          <logic:notEmpty name="rsv110Form" property="rsv110YearComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110YearComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv110SelectedMonthFr" styleId="fromMonth">
          <logic:notEmpty name="rsv110Form" property="rsv110MonthComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110MonthComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv110SelectedDayFr" styleId="fromDay">
          <logic:notEmpty name="rsv110Form" property="rsv110DayComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110DayComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <input type="button" value="Cal" onclick="wrtCalendar(this.form.fromDay, this.form.fromMonth, this.form.fromYear)" class="calendar_btn">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 3)">

        &nbsp;
        <html:select property="rsv110SelectedHourFr">
          <logic:notEmpty name="rsv110Form" property="rsv110HourComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110HourComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.hour.input" />

        <html:select property="rsv110SelectedMinuteFr">
          <logic:notEmpty name="rsv110Form" property="rsv110MinuteComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110MinuteComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.minute.input" />

        </logic:notEqual>


        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">

          <html:select property="rsv110SelectedYearFr" styleId="fromYear" styleClass="display_none">
            <logic:notEmpty name="rsv110Form" property="rsv110YearComboList">
              <html:optionsCollection name="rsv110Form" property="rsv110YearComboList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

          <html:select property="rsv110SelectedMonthFr" styleId="fromMonth" styleClass="display_none">
            <logic:notEmpty name="rsv110Form" property="rsv110MonthComboList">
              <html:optionsCollection name="rsv110Form" property="rsv110MonthComboList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

          <html:select property="rsv110SelectedDayFr" styleId="fromDay" styleClass="display_none">
            <logic:notEmpty name="rsv110Form" property="rsv110DayComboList">
              <html:optionsCollection name="rsv110Form" property="rsv110DayComboList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

          <html:select property="rsv110SelectedHourFr" styleClass="display_none">
            <logic:notEmpty name="rsv110Form" property="rsv110HourComboList">
              <html:optionsCollection name="rsv110Form" property="rsv110HourComboList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

          <html:select property="rsv110SelectedMinuteFr" styleClass="display_none">
            <logic:notEmpty name="rsv110Form" property="rsv110MinuteComboList">
              <html:optionsCollection name="rsv110Form" property="rsv110MinuteComboList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

        </logic:equal>

      </logic:equal>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span><logic:equal name="rsv110Form" property="rsv110EditAuth" value="true"><logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></logic:notEqual></logic:equal></td>
    <td align="left" class="td_wt">

      <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
      <span class="text_base"><bean:write name="rsv110Form" property="yoyakuToString" /></span>
      </logic:equal>

      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
      <span class="text_base"><bean:write name="rsv110Form" property="yoyakuToString" /></span>
      </logic:notEqual>

      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">

        <html:select property="rsv110SelectedYearTo" styleId="toYear">
          <logic:notEmpty name="rsv110Form" property="rsv110YearComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110YearComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv110SelectedMonthTo" styleId="toMonth">
          <logic:notEmpty name="rsv110Form" property="rsv110MonthComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110MonthComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv110SelectedDayTo" styleId="toDay">
          <logic:notEmpty name="rsv110Form" property="rsv110DayComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110DayComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <input type="button" value="Cal" onclick="wrtCalendar(this.form.toDay, this.form.toMonth, this.form.toYear)" class="calendar_btn">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 3)">

        &nbsp;
        <html:select property="rsv110SelectedHourTo">
          <logic:notEmpty name="rsv110Form" property="rsv110HourComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110HourComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.hour.input" />

        <html:select property="rsv110SelectedMinuteTo">
          <logic:notEmpty name="rsv110Form" property="rsv110MinuteComboList">
            <html:optionsCollection name="rsv110Form" property="rsv110MinuteComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.minute.input" />

        </logic:notEqual>
      </logic:equal>

    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type1">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Naiyo" filter="false" /></span>
      </logic:notEqual>

      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <textarea styleClass="text_base_rsv" name="rsv110Naiyo" rows="5" onkeyup="showLengthStr(value, <%= maxLengthNaiyo %>, 'inputlength');" id="inputstr" style="width:472px;"><bean:write name="rsv110Form" property="rsv110Naiyo" /></textarea>
          <br>
          <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthNaiyo %>&nbsp;<gsmsg:write key="cmn.character" /></span>
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="rsv110Form" property="rsv110Naiyo" filter="false" /></span>
        </logic:equal>
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><logic:equal name="rsv110Form" property="rsv110EditAuth" value="true"><logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></logic:notEqual></logic:equal></td>
    <td align="left" class="td_type1">
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <span class="text_base_rsv">
          <logic:equal name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>"><gsmsg:write key="cmn.nolimit" /></logic:equal>
          <logic:equal name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>"><gsmsg:write key="cmn.only.principal.or.registant" /></logic:equal>
          <logic:equal name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>"><gsmsg:write key="cmn.only.affiliation.group.membership" /></logic:equal>
        </span>
      </logic:notEqual>

      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><gsmsg:write key="cmn.comments" /><gsmsg:write key="reserve.89" /><br>
          ※<gsmsg:write key="reserve.90" /><br>
          &nbsp;&nbsp;<gsmsg:write key="reserve.91" /></span>
          <br><br>
          <html:radio styleId="lvl1" name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>" /><label for="lvl1"><gsmsg:write key="cmn.nolimit" /></label>&nbsp;
          <html:radio styleId="lvl2" name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>" /><label for="lvl2"><gsmsg:write key="cmn.only.principal.or.registant" /></label>&nbsp;
          <html:radio styleId="lvl3" name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>" /><label for="lvl3"><gsmsg:write key="cmn.only.affiliation.group.membership" /></label>
        </logic:notEqual>
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv">
            <logic:equal name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>"><gsmsg:write key="cmn.nolimit" /></logic:equal>
            <logic:equal name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>"><gsmsg:write key="cmn.only.principal.or.registant" /></logic:equal>
            <logic:equal name="rsv110Form" property="rsv110RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>"><gsmsg:write key="cmn.only.affiliation.group.membership" /></logic:equal>
          </span>
        </logic:equal>
      </logic:equal>
    </td>
    </tr>

<!-- 担当部署/使用者名/人数 -->
    <% if (sisKbn ==jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA
            || sisKbn == jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) { %>
    <% String headName="";
           jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(request);
           String msgTanto = gsMsg.getMessage("reserve.use.name.1");
           String msgUser = gsMsg.getMessage("reserve.use.name.2");
    %>

    <logic:equal name="rsv110Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <% headName= msgTanto; %>
    </logic:equal>
    <logic:equal name="rsv110Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <% headName= msgUser; %>
    </logic:equal>

    <tr>
       <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">
         <gsmsg:write key="reserve.busyo" />/<%= headName %>/<gsmsg:write key="reserve.use.num" />
       </span>
    </td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
        <td width="60%" nowrap>
          <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
                 <span class="text_base_rsv">
                 <gsmsg:write key="reserve.busyo" />&nbsp;<bean:write name="rsv110Form" property="rsv110Busyo" /><br>
                 <%= headName %>&nbsp;<bean:write name="rsv110Form" property="rsv110UseName" /><br>
                 他&nbsp;<bean:write name="rsv110Form" property="rsv110UseNum" />人
                 </span>
          </logic:notEqual>

          <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
             <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
             <span class="text_base_rsv">
               <gsmsg:write key="reserve.busyo" />&nbsp;<html:text name="rsv110Form" property="rsv110Busyo" maxlength="20" styleClass="text_base_rsv" style="width:153px;" /><br>
               <%= headName %>&nbsp;<html:text name="rsv110Form" property="rsv110UseName" maxlength="20" styleClass="text_base_rsv" style="width:153px;" /><br>
               他&nbsp;<html:text name="rsv110Form" property="rsv110UseNum" maxlength="5" styleClass="text_base_rsv" style="text-align: right;width:63px;" />人
             </span>
             </logic:notEqual>
             <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               <span class="text_base_rsv">
               <gsmsg:write key="reserve.busyo" />&nbsp;<bean:write name="rsv110Form" property="rsv110Busyo" /><br>
               <%= headName %>&nbsp;<bean:write name="rsv110Form" property="rsv110UseName" /><br>
               他&nbsp;<bean:write name="rsv110Form" property="rsv110UseNum" />人
               </span>
             </logic:equal>
          </logic:equal>
        </td>
      </tr>
      </table>
    </td>

    </tr>
    <% } %>

    <% if (editSchFlg) { %>
        <tr>
        <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.85" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
        <td align="left" class="td_type1">
          <html:radio styleId="refOk" name="rsv110Form" property="rsv110ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>" onclick="rsvSchDisabled();" /><label for="refOk"><gsmsg:write key="reserve.86" /></label>&nbsp;
          <html:radio styleId="refNo" name="rsv110Form" property="rsv110ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>" onclick="rsvSchDisabled();" /><label for="refNo"><gsmsg:write key="reserve.87" /></label>
        </td>
        </tr>
    <% } %>

    <logic:equal name="rsv110Form" property="schedulePluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
    <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
    <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">


      <tr>
      <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.3" /></span></td>
      <td align="left" class="td_type1">

        <html:radio property="rsv110SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_USER) %>" styleId="rsvSchKbn0" onclick="rsvSchChange();" /><label for="rsvSchKbn0"><span class="text_base"><gsmsg:write key="cmn.user" /></span></label>
        <html:radio property="rsv110SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_GROUP) %>" styleId="rsvSchKbn1" onclick="rsvSchChange();" /><label for="rsvSchKbn1"><span class="text_base"><gsmsg:write key="cmn.group" /></span></label>
        <br>

        <span id="rsvSchGroup">
          <span class="text_r1">[<gsmsg:write key="reserve.167" />]</span><br>

          <html:select property="rsv110SchGroupSid" styleId="rsvSchGrpSid">
          <logic:notEmpty name="rsv110Form" property="rsv110SchGroupLabel" scope="request">
            <logic:iterate id="exSchGpBean" name="rsv110Form" property="rsv110SchGroupLabel" scope="request">
              <% boolean schGpDisabled = false; %>
              <logic:equal name="exSchGpBean" property="viewKbn" value="false">
                <% schGpDisabled = true; %>
              </logic:equal>
              <bean:define id="gpValue" name="exSchGpBean" property="value" type="java.lang.String" />
              <logic:equal name="exSchGpBean" property="styleClass" value="0">
                <html:option value="<%= gpValue %>" disabled="<%= schGpDisabled %>"><bean:write name="exSchGpBean" property="label" /></html:option>
              </logic:equal>
              <logic:notEqual name="exSchGpBean" property="styleClass" value="0">
                <html:option value="<%= gpValue %>" disabled="<%= schGpDisabled %>"><bean:write name="exSchGpBean" property="label" /></html:option>
              </logic:notEqual>

            </logic:iterate>
          </logic:notEmpty>
          </html:select>
<%--           <html:select property="rsv110SchGroupSid" styleId="rsvSchGrpSid"> --%>
<%--           <logic:notEmpty name="rsv110Form" property="rsv110SchGroupLabel" scope="request"> --%>
<%--              <html:optionsCollection name="rsv110Form" property="rsv110SchGroupLabel" value="value" label="label" /> --%>
<%--           </logic:notEmpty> --%>
<%--           </html:select> --%>
          <input type="button" onclick="openGroupWindow_Disabled(this.form.rsv110SchGroupSid, 'rsv110SchGroupSid', '0', '', 1, '', 'rsvSchNotAccessGroup', 1)" class="group_btn2" value="&nbsp;&nbsp;" id="rsvSchGrpBtn1">
        </span>

        <table cellpadding="0" cellspacing="0" border="0" width="99%" id="rsvSchUser">
        <tr>
        <td width="99%" align="left" class="tbl_in_tbl">
          <span class="text_r1">[<gsmsg:write key="reserve.166" />]</span>

          <table width="0%" border="0">
          <tr>
          <td width="40%" align="center"></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" align="left">
            <logic:equal name="rsv110Form" property="rsv110SchCrangeKbn" value="0">
            <input id="rsvSchBtn" class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup_setDisable(this.form.rsv110GroupSid, 'rsv110GroupSid', '<bean:write name="rsv110Form" property="rsv110GroupSid" />', '1', '110_group', 'sv_users', '-1', '0', 0, 0, 0, 'rsvSchNotAccessUser', null, 'rsvSchNotAccessGroup')" type="button">
            </logic:equal>
          </td>
          </tr>
          <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" align="left">

            <logic:notEmpty name="rsv110Form" property="rsv110GroupLabel" scope="request">
              <html:select style="width:220px" property="rsv110GroupSid" onchange="buttonPush('110_group');" styleId="rsvSchGrpLabel">
              <logic:notEmpty name="rsv110Form" property="rsv110GroupLabel" scope="request">
                <logic:iterate id="gpBean" name="rsv110Form" property="rsv110GroupLabel" scope="request">
                  <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                  <logic:equal name="gpBean" property="styleClass" value="0">
                    <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:equal>
                  <logic:notEqual name="gpBean" property="styleClass" value="0">
                    <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:notEqual>
                </logic:iterate>
              </logic:notEmpty>
              </html:select>
              <logic:equal name="rsv110Form" property="rsv110SchCrangeKbn" value="0">
              <input type="button" onclick="openGroupWindow(this.form.rsv110GroupSid, 'rsv110GroupSid', '1', '110_group')" class="group_btn2" value="&nbsp;&nbsp;" id="rsvSchGrpBtn2">
              </logic:equal>
            </logic:notEmpty>
            <br>
            <span class="text_base">
            <input type="checkbox" name="rsv110SelectUsersKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SELECT_ON) %>" id="select_user" onclick="return selectUsersList();" />
            <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>

          </td>
          </tr>

          <tr>
          <td align="center">
            <!-- 同時登録先 -->
            <select size="5" multiple name="users_r" class="select01" id="rsvSchUsers_r">
            <logic:notEmpty name="rsv110Form" property="rsv110SelectUsrLabel" scope="request">
              <logic:iterate id="urBean" name="rsv110Form" property="rsv110SelectUsrLabel" scope="request">
                <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
              </logic:iterate>
            </logic:notEmpty>
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select>
          </td>

          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('110_rightarrow');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('110_leftarrow');">
          </td>
          <td>
            <select size="5" multiple name="users_l" class="select01" id="rsvSchUsers_l">
            <logic:notEmpty name="rsv110Form" property="rsv110BelongLabel" scope="request">
              <logic:iterate id="urBean" name="rsv110Form" property="rsv110BelongLabel" scope="request">
                <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
              </logic:iterate>
            </logic:notEmpty>
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select>
          </td>
          </tr>
          </table>

        </td>
        </tr>
        </table>
      </td>
      </tr>

    </logic:equal>
    </logic:notEqual>
    </logic:equal>

    <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
       <tr>
       <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="schedule.18" /></span></td>
       <td align="left" class="td_type1">
         <table class="tl0" width="100%">
         <tr>
         <td width="50%" nowrap><span class="text_base">※<gsmsg:write key="schedule.35" /></span>
         </span>
         </td>
         <td width="50%" align="left" nowrap>

           <input type="button" value="<gsmsg:write key="schedule.17" />" class="btn_base1" onClick="openScheduleReserveWindowForReserve(<%= String.valueOf(Rsv310Form.POP_DSP_MODE_RSV110) %>);">

         </td>
         </tr>
         </table>
       </td>
       </tr>
    </logic:equal>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td widht="50%" align="left">
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_POPUP) %>">
          <input type="button" value="<gsmsg:write key="cmn.for.repert" />" class="btn_base1" onclick="buttonPush('kurikaeshi');">
          <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_EDIT) %>">
            <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush('copytouroku');">
          </logic:equal>
        </logic:notEqual>
      </logic:equal>
    <td width="50%" align="right">
      <logic:notEqual name="rsv110Form" property="rsv110EditAuth" value="true">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
      </logic:notEqual>
      <logic:equal name="rsv110Form" property="rsv110EditAuth" value="true">
        <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_POPUP) %>"><input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onclick="window.parent.callYokyakuWindowClose();"></logic:equal>
        <logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_POPUP) %>">
          <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('sisetu_yoyaku_kakunin');">
          <logic:equal name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(GSConstReserve.PROC_MODE_EDIT) %>"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('delete');"></logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
        </logic:notEqual>
      </logic:equal>
    </td>
    </tr>

    <% if (strRsv110ProcMode != null
    && (strRsv110ProcMode.equals(GSConstReserve.PROC_MODE_EDIT)
      || strRsv110ProcMode.equals(GSConstReserve.PROC_MODE_POPUP))) { %>
      <logic:equal name="rsv110Form" property="rsv110ApprBtnFlg" value="1">
      <tr>
      <td colspan="2" align="right" style="padding-top: 5px;">
        <input type="button" value="<gsmsg:write key="cmn.approval" />" id="syoninbtn" class="btn_syonin_n1">
        <input type="button" value="<gsmsg:write key="cmn.rejection" />" id="kyakkabtn" class="btn_kyakka_n1">
      </td>
      </tr>
      </logic:equal>
    <% } %>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<% String reserveMokuteki = ""; %>

<logic:notEmpty name="rsv110Form" property="rsv110MokutekiEsc">
<bean:define id="reserveMokutekistr" name="rsv110Form" property="rsv110MokutekiEsc" type="java.lang.String" />
<% reserveMokuteki = reserveMokutekistr; %>
</logic:notEmpty>


<div id="rsvApproval" title="<gsmsg:write key='reserve.rsv110.info.2'/>" style="display:none;">
  <p>
    <div>
       <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
       <b><gsmsg:write key='reserve.rsv110.info.msg.2' arg0="<%= reserveMokuteki %>" /></b><br><br>
    </div>
  </p>
</div>

<div id="rsvcheck" title="<gsmsg:write key='reserve.rsv110.info'/>" style="display:none;">
  <p>
    <div>
       <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
       <b><gsmsg:write key='reserve.rsv110.info.msg' arg0="<%= reserveMokuteki %>" /></b><br><br>
       <input type="checkbox" id="rejectDel" value="1" /><label for="rejectDel" class="text_base"><span class="dialog_checkbox"><gsmsg:write key="reserve.rsv110.appr.note1" /></span></label>&nbsp;
    </div>
  </p>
</div>


<logic:notEqual name="rsv110Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
  <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</logic:notEqual>

</html:form>
</body>
</html:html>