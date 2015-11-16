<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.rsv.rsv310.Rsv310Form" %>


<% String maxLengthNaiyo = String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.MAX_LENGTH_NAIYO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.rsv111.1" /> ]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/rsv111.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../reserve/js/rsvschedule.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/reservepopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<% boolean editSchFlg = false; %>
<logic:equal name="rsv111Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
  <logic:equal name="rsv111Form" property="rsv110EditAuth" value="true">
    <logic:equal name="rsv111Form" property="rsv111ExistSchDateFlg" value="true">
    <logic:greaterThan name="rsv111Form" property="rsv110ScdRsSid" value="0">
      <% editSchFlg = true; %>
    </logic:greaterThan>
    </logic:equal>
  </logic:equal>
</logic:equal>


<body class="body_03" onload="showOrHide();setDisabled();changeWeekCombo();showLengthId($('#inputstr')[0], <%= maxLengthNaiyo %>, 'inputlength');rsvSchChange();<% if (editSchFlg) { %>rsvSchDisabled();<% } %>" onunload="calWindowClose();windowClose();">

<html:form action="/reserve/rsv111">
<input type="hidden" name="CMD" value="kurikaesi_toroku_kakunin">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsvPrintUseKbn" />
<html:hidden property="rsv110SisetuKbn" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv111Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv111Form" property="rsv100CsvOutField" scope="request">
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




<logic:notEmpty name="rsv111Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv111Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv110GroupSid" />
<logic:notEmpty name="rsv111Form" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="rsv111Form" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv110SchKbn" />
<html:hidden property="rsv110SchGroupSid" />

<logic:notEmpty name="rsv111Form" property="rsv111SvUsers" scope="request">
  <logic:iterate id="ulExBean" name="rsv111Form" property="rsv111SvUsers" scope="request">
    <input type="hidden" name="rsv111SvUsers" value="<bean:write name="ulExBean" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv111Form" property="rsv110SchNotAccessGroupList" scope="request">
  <logic:iterate id="notAccessGroup" name="rsv111Form" property="rsv110SchNotAccessGroupList">
    <input type="hidden" name="rsvSchNotAccessGroup" value="<bean:write name="notAccessGroup" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv111Form" property="rsv110SchNotAccessUserList" scope="request">
  <logic:iterate id="notAccessUser" name="rsv111Form" property="rsv110SchNotAccessUserList">
    <input type="hidden" name="rsvSchNotAccessUser" value="<bean:write name="notAccessUser" />">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="rsvSisKbn" name="rsv111Form" property="rsv110SisetuKbn" type="java.lang.Integer" />
<% int sisKbn = rsvSisKbn; %>


<input type="hidden" name="helpPrm" value="<bean:write name ="rsv111Form" property="rsv110SisetuKbn"/>_<bean:write name="rsv111Form" property="rsv110ProcMode" />" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="99%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv111.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="99%">
    <tr>
    <td width="50%">
      <input type="button" value="<gsmsg:write key="cmn.general.regist" />" class="btn_base1" onclick="buttonPush('ippan');">
      <logic:equal name="rsv111Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush('copytouroku');">
      </logic:equal>
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td width="50%" class="header_glay_bg">
      <input type="submit" value="OK" class="btn_ok1">
      <logic:equal name="rsv111Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('delete');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
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

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="99%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table width="99%">
      <tr>
      <td align="left" width="100%" class="text_base2" nowrap><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110GrpName" /></span></td>
      <td align="right" width="0%" nowrap><input type="button" value="<gsmsg:write key="cmn.hide" />" class="btn_base1s" onClick="hideText();">&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110SisetuKbnName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110SisetuName" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.asset.register.num" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110SisanKanri" /></span></td>
    </tr>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName4">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName4" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110Prop4Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName5" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110Prop5Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName1">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName1" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110Prop1Value" /></span></td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName2">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName2" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111Form" property="rsv110Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv111Form" property="rsv110Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName3">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName3" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111Form" property="rsv110Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv111Form" property="rsv110Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName7">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName7" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv111Form" property="rsv110Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
        <logic:equal name="rsv111Form" property="rsv110Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="rsv111Form" property="rsv110PropHeaderName6">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="rsv111Form" property="rsv110PropHeaderName6" /></span></td>
    <td align="left" class="td_type1">
      <logic:notEmpty name="rsv111Form" property="rsv110Prop6Value"><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110Prop6Value" /><gsmsg:write key="cmn.days.after" /></logic:notEmpty></span>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <bean:write name="rsv111Form" property="rsv110Biko" filter="false" /></span>
    </td>
    </tr>
    </table>
    </div>


    <div id="shortHeader">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="99%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table width="100%">
      <tr>
      <td align="left" width="100%" class="text_base2" nowrap><span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110SisetuName" /></span></td>
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

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="99%">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="100%">
      <tr>
      <td width="60%" nowrap>
        <span class="text_base_rsv"><bean:write name="rsv111Form" property="rsv110Torokusya" /></span>
      </td>
      <td width="40%" align="left" nowrap>
      <span class="text_base"><bean:write name="rsv111Form" property="rsv110AddDate"/></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- 印刷 -->
    <logic:equal name="rsv111Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
        <logic:equal name="rsv111Form" property="rsvPrintUseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_PRINT_USE_YES) %>">
        <tr>
        <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">印刷</span></td>
        <td align="left" class="td_type1" width="80%">
          <table class="tl0" width="99%">
          <tr>
          <td width="60%" nowrap>
          <html:checkbox name="rsv111Form" property="rsv111PrintKbn" value="1" styleId="print"/><label for="print" class="text_base_rsv">印刷する</label>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </logic:equal>
    </logic:equal>


    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.72" /></span><logic:equal name="rsv111Form" property="rsv110EditAuth" value="true"><logic:notEqual name="rsv111Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></logic:notEqual></logic:equal></td>
    <td align="left" class="td_type1">
      <html:text name="rsv111Form" property="rsv111RsrMok" maxlength="50" styleClass="text_base_rsv" style="width:549px;" />
    </td>
    </tr>


    <logic:equal name="rsv111Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">利用区分</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <span class="text_base_rsv">
      <html:radio name="rsv111Form" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>" styleId="rsyUkbnNoset"><label for="rsyUkbnNoset">未設定</label></html:radio>&nbsp;
      <html:radio name="rsv111Form" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>" styleId="rsyUkbnKaigi"><label for="rsyUkbnKaigi">会議</label></html:radio>&nbsp;
      <html:radio name="rsv111Form" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>" styleId="rsyUkbnKensyu"><label for="rsyUkbnKensyu">研修</label></html:radio>&nbsp;
      <html:radio name="rsv111Form" property="rsv111UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>" styleId="rsyUkbnOther"><label for="rsyUkbnOther">その他</label></html:radio>
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
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">連絡先</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <html:text name="rsv111Form" property="rsv111Contact" maxlength="20" styleClass="text_base_rsv" style="width:155px;" />
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <% } %>

    <logic:equal name="rsv111Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">会議名案内</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <html:text name="rsv111Form" property="rsv111Guide" maxlength="50" styleClass="text_base_rsv" style="width:335px;" />
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">駐車場見込み台数</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <html:text name="rsv111Form" property="rsv111ParkNum" maxlength="5" styleClass="text_base_rsv" style="text-align:right;width:65px" />
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </logic:equal>

    <logic:equal name="rsv111Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <!-- 行先 -->
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">行先</span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
      <html:text name="rsv111Form" property="rsv111Dest" maxlength="50" styleClass="text_base_rsv" style="width:335px;"/>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.for.repert" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">
      <table width="80%">
      <tr>
      <td colspan="3" width="100%" nowrap>
        <html:radio name="rsv111Form" property="rsv111RsrKbn" styleId="everyday" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.KAKUTYO_KBN_EVERY_DAY) %>" onclick="setDisabled();"/>
        <span class="text_base_rsv"><label for="everyday"><gsmsg:write key="cmn.everyday" /></label></span>&nbsp;
      </td>
      </tr>

      <tr>
      <td colspan="1" width="10%" nowrap>
        <html:radio name="rsv111Form" property="rsv111RsrKbn" styleId="everyweek" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.KAKUTYO_KBN_EVERY_WEEK) %>" onclick="setDisabled();"/>
        <span class="text_base_rsv"><label for="everyweek"><gsmsg:write key="cmn.weekly2" /></label></span>&nbsp;</td>
      <td colspan="1" width="10%" align="center" nowrap>&nbsp;</td>
      <td colspan="1" rowspan="2" align="left" width="80%" nowrap>

        <table class="tl_u2">
        <tr>
        <th class="td_type9"><span color="#ff0000"><gsmsg:write key="cmn.sunday" /></span></th>
        <th class="td_type1"><gsmsg:write key="cmn.Monday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.tuesday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.wednesday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.thursday" /></th>
        <th class="td_type1"><gsmsg:write key="cmn.friday" /></th>
        <th class="td_type8"><span color="#0000ff"><gsmsg:write key="cmn.saturday" /></span></th>
        </tr>

        <tr>
        <th class="td_type9"><html:checkbox name="rsv111Form" property="rsv111RsrDweek1" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        <th class="td_type1"><html:checkbox name="rsv111Form" property="rsv111RsrDweek2" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        <th class="td_type1"><html:checkbox name="rsv111Form" property="rsv111RsrDweek3" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        <th class="td_type1"><html:checkbox name="rsv111Form" property="rsv111RsrDweek4" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        <th class="td_type1"><html:checkbox name="rsv111Form" property="rsv111RsrDweek5" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        <th class="td_type1"><html:checkbox name="rsv111Form" property="rsv111RsrDweek6" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        <th class="td_type8"><html:checkbox name="rsv111Form" property="rsv111RsrDweek7" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.COMBO_DEFAULT_ON_VALUE) %>" /></th>
        </tr>

        </table>
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>
        <html:radio name="rsv111Form" property="rsv111RsrKbn" styleId="everymonth" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.KAKUTYO_KBN_EVERY_MONTH) %>" onclick="setDisabled();"/>
        <span class="text_base_rsv"><label for="everymonth"><gsmsg:write key="cmn.monthly.2" /></label></span>&nbsp;</td>
      <td colspan="1" align="center" nowrap><span class="text_base_rsv"><gsmsg:write key="cmn.week" />：</span>
        <html:select property="rsv111RsrWeek" onchange="changeWeekCombo();">
          <html:optionsCollection name="rsv111Form" property="rsv111WeekList" value="value" label="label" />
        </html:select>&nbsp;&nbsp;
      </td>
      </tr>

      <tr>
      <td colspan="1" nowrap>&nbsp;</td>
      <td colspan="1" align="center" nowrap><span class="text_base_rsv"><gsmsg:write key="cmn.day" />：</span>
      <html:select property="rsv111RsrDay">
        <html:optionsCollection name="rsv111Form" property="rsv111ExDayList" value="value" label="label" />
      </html:select>&nbsp;&nbsp;
      </td>
      <td colspan="1" nowrap>&nbsp;</td>
      </tr>

      <tr>
      <td colspan="1" nowrap>
      <html:radio name="rsv111Form" property="rsv111RsrKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.KAKUTYO_KBN_EVERY_YEAR) %>" styleId="everyyear" onclick="setDisabled();"/>
      <span class="text_base2"><label for="everyyear"><gsmsg:write key="cmn.yearly" /></label></span>&nbsp;</td>
      <td colspan="1" align="center" nowrap>
         <html:select property="rsv111RsrMonthOfYearly" styleId="selMonthOfYearly">
             <html:optionsCollection name="rsv111Form" property="rsv110MonthComboList" value="value" label="label" />
         </html:select>
        <html:select property="rsv111RsrDayOfYearly" styleId="selDayOfYearly">
             <html:optionsCollection name="rsv111Form" property="rsv111ExDayOfYearlyList" value="value" label="label" />
        </html:select>
       </td>
      <td colspan="1" nowrap>&nbsp;</td>

      <tr>
      <td colspan="4" nowrap>&nbsp;</td>
      </tr>

      <tr>
      <td colspan="4" nowrap><span class="text_r1"><gsmsg:write key="reserve.rsv111.8" /></span><br>
        <span class="text_base_rsv">
        <html:radio name="rsv111Form" property="rsv111RsrTranKbn" styleId="tranKbn1" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.FURIKAE_NO) %>" />
        <label for="tranKbn1"><gsmsg:write key="reserve.rsv111.9" /></label><br>
        <html:radio name="rsv111Form" property="rsv111RsrTranKbn" styleId="tranKbn2" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.FURIKAE_MAE) %>" />
        <label for="tranKbn2"><gsmsg:write key="cmn.change.before.businessday" /></label><br>
        <html:radio name="rsv111Form" property="rsv111RsrTranKbn" styleId="tranKbn3" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.FURIKAE_ATO) %>" />
        <label for="tranKbn3"><gsmsg:write key="cmn.change.next.businessday" /></label>
        &nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.comments" /><gsmsg:write key="cmn.holiday.based.timecard" /></span>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td rowspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.period" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">

      <html:select property="rsv111RsrDateYearFr" styleId="fromYear">
        <logic:notEmpty name="rsv111Form" property="rsv110YearComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110YearComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

      <html:select property="rsv111RsrDateMonthFr" styleId="fromMonth">
        <logic:notEmpty name="rsv111Form" property="rsv110MonthComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110MonthComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

      <html:select property="rsv111RsrDateDayFr" styleId="fromDay">
        <logic:notEmpty name="rsv111Form" property="rsv110DayComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110DayComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

      <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.fromDay, this.form.fromMonth, this.form.fromYear, 'rsv111_1')" class="calendar_btn", id="rsv111_1">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#fromYear')[0], $('#fromMonth')[0], $('#fromDay')[0], 3)">

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">

      <html:select property="rsv111RsrDateYearTo" styleId="toYear">
        <logic:notEmpty name="rsv111Form" property="rsv110YearComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110YearComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

      <html:select property="rsv111RsrDateMonthTo" styleId="toMonth">
        <logic:notEmpty name="rsv111Form" property="rsv110MonthComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110MonthComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

      <html:select property="rsv111RsrDateDayTo" styleId="toDay">
        <logic:notEmpty name="rsv111Form" property="rsv110DayComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110DayComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>

      <input type="button" value="Cal" onclick="wrtCalendarByBtn(this.form.toDay, this.form.toMonth, this.form.toYear, 'rsv111_2')" class="calendar_btn", id="rsv111_2">

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#toYear')[0], $('#toMonth')[0], $('#toDay')[0], 3)">

    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.time" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">

      <html:select property="rsv111RsrTimeHourFr">
        <logic:notEmpty name="rsv111Form" property="rsv110HourComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110HourComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.hour.input" />

      <html:select property="rsv111RsrTimeMinuteFr">
        <logic:notEmpty name="rsv111Form" property="rsv110MinuteComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110MinuteComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

      &nbsp;～&nbsp;

      <html:select property="rsv111RsrTimeHourTo">
        <logic:notEmpty name="rsv111Form" property="rsv110HourComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110HourComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.hour.input" />

      <html:select property="rsv111RsrTimeMinuteTo">
        <logic:notEmpty name="rsv111Form" property="rsv110MinuteComboList">
          <html:optionsCollection name="rsv111Form" property="rsv110MinuteComboList" value="value" label="label" />
        </logic:notEmpty>
      </html:select>
      <gsmsg:write key="cmn.minute.input" />

    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type1">
      <textarea styleClass="text_base_rsv" name="rsv111RsrBiko" style="width:489px;" rows="6" onkeyup="showLengthStr(value, <%= maxLengthNaiyo %>, 'inputlength');" id="inputstr"><bean:write name="rsv111Form" property="rsv111RsrBiko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthNaiyo %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv"><gsmsg:write key="cmn.comments" /><gsmsg:write key="reserve.89" /><br>
      ※<gsmsg:write key="reserve.90" /><br>
      &nbsp;&nbsp;<gsmsg:write key="reserve.91" /></span>
      <br><br>
      <html:radio styleId="lvl1" name="rsv111Form" property="rsv111RsrEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>" /><label for="lvl1"><gsmsg:write key="cmn.nolimit" /></label>&nbsp;
      <html:radio styleId="lvl2" name="rsv111Form" property="rsv111RsrEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>" /><label for="lvl2"><gsmsg:write key="cmn.only.principal.or.registant" /></label>&nbsp;
      <html:radio styleId="lvl3" name="rsv111Form" property="rsv111RsrEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>" /><label for="lvl3"><gsmsg:write key="cmn.only.affiliation.group.membership" /></label>
    </td>
    </tr>

    <!-- 担当部署/使用者名/人数 -->
    <% if (sisKbn ==jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA
            || sisKbn == jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) { %>
    <% String headName=""; %>
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1">
    <logic:equal name="rsv111Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
    <% headName="担当者名"; %>
    </logic:equal>
    <logic:equal name="rsv111Form" property="rsv110SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <% headName="使用者名"; %>
    </logic:equal>
    担当部署/<%= headName %>/人数
    </span></td>
    <td align="left" class="td_type1" width="80%">
      <table class="tl0" width="99%">
      <tr>
      <td width="60%" nowrap>
         <span class="text_base_rsv">
         担当部署&nbsp;<html:text name="rsv111Form" property="rsv111Busyo"  maxlength="20" styleClass="text_base_rsv" style="width:153px;"/><br>
         <%= headName %>&nbsp;<html:text name="rsv111Form" property="rsv111UseName" maxlength="20" styleClass="text_base_rsv" style="width:153px;"/><br>
         他&nbsp;<html:text name="rsv111Form" property="rsv111UseNum" maxlength="5" styleClass="text_base_rsv" style="text-align: right;width:63px;" />人
         </span>
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
          <html:radio styleId="refOk" name="rsv111Form" property="rsv111ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>" onclick="rsvSchDisabled();" /><label for="refOk"><gsmsg:write key="reserve.86" /></label>&nbsp;
          <html:radio styleId="refNo" name="rsv111Form" property="rsv111ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>" onclick="rsvSchDisabled();" /><label for="refNo"><gsmsg:write key="reserve.87" /></label>
        </td>
        </tr>
    <% } %>


    <logic:equal name="rsv111Form" property="schedulePluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">

      <tr>
      <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.3" /></span></td>
      <td align="left" class="td_type1">

        <html:radio property="rsv111SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_USER) %>" styleId="rsvSchKbn0" onclick="rsvSchChange();" /><label for="rsvSchKbn0"><span class="text_base"><gsmsg:write key="cmn.user" /></span></label>
        <html:radio property="rsv111SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_GROUP) %>" styleId="rsvSchKbn1" onclick="rsvSchChange();" /><label for="rsvSchKbn1"><span class="text_base"><gsmsg:write key="cmn.group" /></span></label>
        <br>

        <span id="rsvSchGroup">
          <span class="text_r1">[<gsmsg:write key="reserve.167" />]</span><br>

          <html:select property="rsv111SchGroupSid" styleId="rsvSchGrpSid">
          <logic:notEmpty name="rsv111Form" property="rsv110SchGroupLabel" scope="request">

            <logic:iterate id="exSchGpBean" name="rsv111Form" property="rsv110SchGroupLabel" scope="request">
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

<%--           <html:select property="rsv111SchGroupSid" styleId="rsvSchGrpSid"> --%>
<%--           <logic:notEmpty name="rsv111Form" property="rsv110SchGroupLabel" scope="request"> --%>
<%--              <html:optionsCollection name="rsv111Form" property="rsv110SchGroupLabel" value="value" label="label" /> --%>
<%--           </logic:notEmpty> --%>
<%--           </html:select> --%>
          <input type="button" onclick="openGroupWindow_Disabled(this.form.rsv111SchGroupSid, 'rsv111SchGroupSid', '0', '', 1, '', 'rsvSchNotAccessGroup', 1)" class="group_btn2" value="&nbsp;&nbsp;" id="rsvSchGrpBtn1">
        </span>

        <table cellpadding="0" cellspacing="0" border="0" width="100%" id="rsvSchUser">
        <tr>
        <td width="100%" align="left" class="tbl_in_tbl">
          <span class="text_r1">[<gsmsg:write key="reserve.166" />]</span>

          <table width="0%" border="0">
          <tr>
          <td width="40%" align="center"></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" align="left">
            <logic:equal name="rsv111Form" property="rsv110SchCrangeKbn" value="0">
            <input id="rsvSchBtn" class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup_setDisable(this.form.rsv111GroupSid, 'rsv111GroupSid', '<bean:write name="rsv111Form" property="rsv111GroupSid" />', '1', '110_group', 'rsv111SvUsers', '-1', '0', 0, 0, 0, 'rsvSchNotAccessUser', null, 'rsvSchNotAccessGroup')" type="button">
            </logic:equal>
          </td>
          </tr>
          <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" align="left">

            <logic:notEmpty name="rsv111Form" property="rsv110GroupLabel" scope="request">
              <html:select style="width:200px" property="rsv111GroupSid" onchange="buttonPush('110_group');" styleId="rsvSchGrpLabel">
              <logic:notEmpty name="rsv111Form" property="rsv110GroupLabel" scope="request">
                <logic:iterate id="gpBean" name="rsv111Form" property="rsv110GroupLabel" scope="request">
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
              <logic:equal name="rsv111Form" property="rsv110SchCrangeKbn" value="0">
              <input type="button" onclick="openGroupWindow(this.form.rsv111GroupSid, 'rsv111GroupSid', '0', '110_group')" class="group_btn2" value="&nbsp;&nbsp;" id="rsvSchGrpBtn2">
              </logic:equal>
            </logic:notEmpty>

            <span class="text_base">
            <br><input type="checkbox" name="rsv111SelectUsersKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SELECT_ON) %>" id="select_user" onclick="return selectUsersList();" />
            <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>

          </td>
          </tr>

          <tr>
          <td align="center">
            <!-- 同時登録先 -->
            <select size="5" multiple name="users_r" class="select01" id="rsvSchUsers_r">
            <logic:notEmpty name="rsv111Form" property="rsv110SelectUsrLabel" scope="request">
              <logic:iterate id="urBean" name="rsv111Form" property="rsv110SelectUsrLabel" scope="request">
                <option value="<bean:write name="urBean" property="usrSid" scope="page"/>"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/></option>
              </logic:iterate>
            </logic:notEmpty>
            <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </select>
          </td>

          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('111_rightarrow');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('111_leftarrow');">
          </td>
          <td>
            <select size="5" multiple name="users_l" class="select01" id="rsvSchUsers_l">
            <logic:notEmpty name="rsv111Form" property="rsv110BelongLabel" scope="request">
              <logic:iterate id="urBean" name="rsv111Form" property="rsv110BelongLabel" scope="request">
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

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="schedule.18" /></span></td>
    <td align="left" class="td_type1">
      <table class="tl0" width="100%">
      <tr>
      <td width="50%" nowrap>      <span class="text_base">※<gsmsg:write key="schedule.35" /></span>
      </span>
      </td>
      <td width="50%" align="left" nowrap>
      <input type="button" value="<gsmsg:write key="schedule.17" />" class="btn_base1" onClick="openScheduleReserveWindowForReserve(<%= String.valueOf(Rsv310Form.POP_DSP_MODE_RSV111) %>);">
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="99%">
    <tr>
    <td widht="50%" align="left">
      <input type="button" value="<gsmsg:write key="cmn.general.regist" />" class="btn_base1" onclick="buttonPush('ippan');">
      <logic:equal name="rsv111Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
        <input type="button" value="<gsmsg:write key="cmn.register.copy" />" class="btn_base1" onClick="buttonPush('copytouroku');">
      </logic:equal>
    <td width="50%" align="right">
      <input type="submit" value="OK" class="btn_ok1">
      <logic:equal name="rsv111Form" property="rsv110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('delete');"></logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_menu');">
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