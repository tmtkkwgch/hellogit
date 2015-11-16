<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-jqText.tld" prefix="jquery" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.reserve" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>

<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.mobile.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jQuery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../mobile/sp/js/jquery.ui.datepicker.mobile.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>

<logic:equal name="mbhRsv030Form" property="mobileLang" value="0">
<script src="../mobile/sp/js/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
</logic:equal>

<bean:define id="sptm" name="mbhRsv030Form" property="spTheme"/>
<script type="text/javascript">
$(document).ready(function(){
  $("#date").datepicker();
  <logic:notEmpty name="mbhRsv030Form" property="rsv030SchGroupSid">
    <logic:notEqual name="mbhRsv030Form" property="rsv030SchGroupSid" value="-1">
    rsvSchGrpChange('<%= sptm %>');
    </logic:notEqual>
  </logic:notEmpty>
});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $("#date2").datepicker();
});
</script>

<% pluginName = "rsv"; %>
<% thisForm = "mbhRsv030Form"; %>
</head>

<% boolean editSchFlg = false; %>
<logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">
  <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
    <logic:equal name="mbhRsv030Form" property="rsv030ExistSchDateFlg" value="true">
    <logic:greaterThan name="mbhRsv030Form" property="rsv030ScdRsSid" value="0">
      <% editSchFlg = true; %>
    </logic:greaterThan>
    </logic:equal>
  </logic:equal>
</logic:equal>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_rsv030">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/rsv_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.reserve" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="acordParam" />
<html:hidden property="rsv030ProcMode" />
<html:hidden property="rsv030InitFlg" />
<html:hidden property="rsv030RsySid" />
<html:hidden property="rsv030RsdSid" />
<html:hidden property="rsv030SinkiDefaultDate" />
<html:hidden property="rsv030ScdRsSid" />
<html:hidden property="rsv030EditAuth" />
<html:hidden property="rsv100InitFlg" />
<html:hidden property="rsv100SearchFlg" />
<html:hidden property="rsv100SortKey" />
<html:hidden property="rsv100OrderKey" />
<html:hidden property="rsv100PageTop" />
<html:hidden property="rsv100PageBottom" />
<html:hidden property="rsv100selectedFromYear" />
<html:hidden property="rsv100selectedFromMonth" />
<html:hidden property="rsv100selectedFromDay" />
<html:hidden property="rsv100selectedToYear" />
<html:hidden property="rsv100selectedToMonth" />
<html:hidden property="rsv100selectedToDay" />

<html:hidden property="rsv100KeyWord" />
<html:hidden property="rsv100SearchCondition" />
<html:hidden property="rsv100TargetMok" />
<html:hidden property="rsv100TargetNiyo" />
<logic:notEmpty name="mbhRsv030Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="mbhRsv030Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>
<html:hidden property="rsv100SelectedKey1" />
<html:hidden property="rsv100SelectedKey2" />
<html:hidden property="rsv100SelectedKey1Sort" />
<html:hidden property="rsv100SelectedKey2Sort" />
<html:hidden property="rsv100svFromYear" />
<html:hidden property="rsv100svFromMonth" />
<html:hidden property="rsv100svFromDay" />
<html:hidden property="rsv100svToYear" />
<html:hidden property="rsv100svToMonth" />
<html:hidden property="rsv100svToDay" />
<html:hidden property="rsv100svGrp1" />
<html:hidden property="rsv100svGrp2" />
<html:hidden property="rsv100svKeyWord" />
<html:hidden property="rsv100svSearchCondition" />
<html:hidden property="rsv100svTargetMok" />
<html:hidden property="rsv100svTargetNiyo" />
<html:hidden property="rsv100svSelectedKey1" />
<html:hidden property="rsv100svSelectedKey2" />
<html:hidden property="rsv100svSelectedKey1Sort" />
<html:hidden property="rsv100svSelectedKey2Sort" />
<html:hidden property="rsv100SearchSvFlg" />
<html:hidden property="rsv030HeaderDspFlg" />
<html:hidden property="rsv030ExistSchDateFlg" />

<html:hidden property="rsv030SisetuKbn" />
<html:hidden property="rsvPrintUseKbn" />

<bean:define id="rsvSisKbn" name="mbhRsv030Form" property="rsv030SisetuKbn" type="java.lang.Integer" />
<% int sisKbn = rsvSisKbn; %>

<logic:notEmpty name="mbhRsv030Form" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="mbhRsv030Form" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>

  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.facility.group" /></div>
      <bean:write name="mbhRsv030Form" property="rsv030GrpName" />
    </li>

    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.facility.name" /></div>
      <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030SisetuName" /></span>
    </li>

    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.registant" /></div>
      <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Torokusya" /></span>
    </li>
  </ul>

<!-- 印刷 -->
    <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
      <logic:equal name="mbhRsv030Form" property="rsvPrintUseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_PRINT_USE_YES) %>">
      <div class="font_small">■<gsmsg:write key="reserve.print" /></div>
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:equal name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="font_xsmall"><gsmsg:write key="reserve.print.yes" /></span></logic:equal>
        <logic:notEqual name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="font_xsmall"><gsmsg:write key="reserve.print.no" /></span></logic:notEqual>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:checkbox name="mbhRsv030Form" property="rsv030PrintKbn" value="1" styleId="print"/><label for="print" class="text_base"><span class="font_xsmall"><gsmsg:write key="reserve.print.yes" /></span></label>
        </logic:notEqual>
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <logic:equal name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="font_xsmall"><gsmsg:write key="reserve.print.yes" /></span></logic:equal>
          <logic:notEqual name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="font_xsmall"><gsmsg:write key="reserve.print.no" /></span></logic:notEqual>
        </logic:equal>
      </logic:equal>
      </logic:equal>
    </logic:equal>

    <div class="font_small">■<gsmsg:write key="reserve.72" /></div>


    <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Mokuteki" /></span>
    </logic:notEqual>

    <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
        <html:text name="mbhRsv030Form" property="rsv030Mokuteki" size="40" maxlength="50" styleClass="text_base_rsv" />
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Mokuteki" /></span>
      </logic:equal>
    </logic:equal>

    <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">

    <div class="font_small">■<gsmsg:write key="reserve.use.kbn" /></div>
    <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>"><gsmsg:write key="reserve.use.kbn.noset" /></logic:equal>
      <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>"><gsmsg:write key="reserve.use.kbn.meeting" /></logic:equal>
      <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>"><gsmsg:write key="reserve.use.kbn.training" /></logic:equal>
      <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>"><gsmsg:write key="reserve.use.kbn.other" /></logic:equal>
    </logic:notEqual>
    <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
      <html:select property="rsv030UseKbn">
        <html:optionsCollection name="mbhRsv030Form" property="rsv030UseKbnLavel" value="value" label="label" />
      </html:select>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
        <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_NOSET) %>"><gsmsg:write key="reserve.use.kbn.noset" /></logic:equal>
        <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KAIGI) %>"><gsmsg:write key="reserve.use.kbn.meeting" /></logic:equal>
        <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_KENSYU) %>"><gsmsg:write key="reserve.use.kbn.training" /></logic:equal>
        <logic:equal name="mbhRsv030Form" property="rsv030UseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSY_USE_KBN_OTHER) %>"><gsmsg:write key="reserve.use.kbn.other" /></logic:equal>
      </logic:equal>
    </logic:equal>

    </logic:equal>


    <% if (sisKbn ==jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA
            || sisKbn == jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) { %>

    <div class="font_small">■<gsmsg:write key="reserve.contact" /></div>
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Contact" /></span>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="mbhRsv030Form" property="rsv030Contact" size="20" maxlength="20" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Contact" /></span>
        </logic:equal>
      </logic:equal>

    <% } %>

    <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
      <div class="font_small">■<gsmsg:write key="reserve.guide" /></div>
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Guide" /></span>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="mbhRsv030Form" property="rsv030Guide" size="20" maxlength="20" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Guide" /></span>
        </logic:equal>
      </logic:equal>

      <div class="font_small">■<gsmsg:write key="reserve.park.num" /></div>
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030ParkNum" /></span>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="mbhRsv030Form" property="rsv030ParkNum" size="20" maxlength="20" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030ParkNum" /></span>
        </logic:equal>
      </logic:equal>
    </logic:equal>

    <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
    <!-- 行先 -->
      <div class="font_small">■<gsmsg:write key="reserve.dest" /></div>
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Dest" /></span>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:text name="mbhRsv030Form" property="rsv030Dest" size="20" maxlength="20" styleClass="text_base_rsv" />
        </logic:notEqual>
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Dest" /></span>
        </logic:equal>
      </logic:equal>
    </logic:equal>

    <div class="font_small">■<gsmsg:write key="cmn.start" /></div>


    <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <span class="text_base"><bean:write name="mbhRsv030Form" property="yoyakuFrString" /></span>
    </logic:notEqual>

    <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">

      <jquery:jqtext id="date" name="mbhRsv030Form" property="rsv030FrDate" readonly="true"/>

      <div data-role="navbar" align="center">
        <ul>
          <li>
              <html:select property="rsv030SelectedHourFr">
                <logic:notEmpty name="mbhRsv030Form" property="rsv030HourComboList">
                  <html:optionsCollection name="mbhRsv030Form" property="rsv030HourComboList" value="value" label="label" />
                </logic:notEmpty>
              </html:select>
              <gsmsg:write key="cmn.hour.input" />
          </li>
          <li>
              <html:select property="rsv030SelectedMinuteFr">
                <logic:notEmpty name="mbhRsv030Form" property="rsv030MinuteComboList">
                  <html:optionsCollection name="mbhRsv030Form" property="rsv030MinuteComboList" value="value" label="label" />
                </logic:notEmpty>
              </html:select><gsmsg:write key="cmn.minute.input" />
         </li>
        </ul>
      </div>

    </logic:equal>

    <div class="font_small">■<gsmsg:write key="cmn.end" /></div>

    <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <span class="text_base"><bean:write name="mbhRsv030Form" property="yoyakuToString" /></span>
    </logic:notEqual>

    <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <jquery:jqtext id="date2" name="mbhRsv030Form" property="rsv030ToDate" readonly="true"/>
      <div data-role="navbar" align="center">
        <ul>
          <li>
              <html:select property="rsv030SelectedHourTo">
                <logic:notEmpty name="mbhRsv030Form" property="rsv030HourComboList">
                  <html:optionsCollection name="mbhRsv030Form" property="rsv030HourComboList" value="value" label="label" />
                </logic:notEmpty>
              </html:select>
              <gsmsg:write key="cmn.hour.input" />
          </li>
          <li>
              <html:select property="rsv030SelectedMinuteTo">
                <logic:notEmpty name="mbhRsv030Form" property="rsv030MinuteComboList">
                  <html:optionsCollection name="mbhRsv030Form" property="rsv030MinuteComboList" value="value" label="label" />
                </logic:notEmpty>
              </html:select>
              <gsmsg:write key="cmn.minute.input" />
         </li>
        </ul>
      </div>
    </logic:equal>
    <br>

    <div class="font_small">■<gsmsg:write key="cmn.content" /></div>


      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Naiyo" filter="false" /></span>
      </logic:notEqual>

      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <textarea styleClass="text_base_rsv" name="rsv030Naiyo" cols="25" rows="6"" id="inputstr"><bean:write name="mbhRsv030Form" property="rsv030Naiyo" /></textarea>

        </logic:notEqual>

      </logic:equal>

      <div class="font_small">■<gsmsg:write key="cmn.edit.permissions" /></div>

      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <html:select property="rsv030RsyEdit">
          <html:optionsCollection name="mbhRsv030Form" property="rsv030EditLavel" value="value" label="label" />
        </html:select>
      </logic:equal>

      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:equal name="mbhRsv030Form" property="rsv030RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>"><gsmsg:write key="cmn.nolimit" /></logic:equal>
        <logic:equal name="mbhRsv030Form" property="rsv030RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>"><gsmsg:write key="cmn.only.principal.or.registant" /></logic:equal>
        <logic:equal name="mbhRsv030Form" property="rsv030RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>"><gsmsg:write key="cmn.only.affiliation.group.membership" /></logic:equal>
      </logic:notEqual>

      <br>

<!-- 担当部署/使用者名/人数 -->
    <% if (sisKbn ==jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA
            || sisKbn == jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) { %>
    <% String headName="";
           jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(request);
           String msgTanto = gsMsg.getMessage("reserve.use.name.1");
           String msgUser = gsMsg.getMessage("reserve.use.name.2");
           %>

           <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">
           <% headName= msgTanto; %>
           </logic:equal>
           <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
           <% headName= msgUser; %>
           </logic:equal>

           <!-- 部署 -->
           <div class="font_small">■<gsmsg:write key="reserve.busyo" /></div>
           <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Busyo" /></span>
           </logic:notEqual>
           <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               <html:text name="mbhRsv030Form" property="rsv030Busyo" size="20" maxlength="20" styleClass="text_base_rsv" />
             </logic:notEqual>
             <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Busyo" /></span>
             </logic:equal>
           </logic:equal>

           <div class="font_small">■<%= headName %></div>
           <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030UseName" /></span>
           </logic:notEqual>
           <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               <html:text name="mbhRsv030Form" property="rsv030UseName" size="20" maxlength="20" styleClass="text_base_rsv" />
             </logic:notEqual>
             <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030UseName" /></span>
             </logic:equal>
           </logic:equal>

           <div class="font_small">■<gsmsg:write key="reserve.use.num" /></div>
           <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <span class="text_base_rsv">他&nbsp;<bean:write name="mbhRsv030Form" property="rsv030UseNum" />人</span>
           </logic:notEqual>
           <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               他&nbsp;<html:text name="mbhRsv030Form" property="rsv030UseNum" size="5" maxlength="5" styleClass="text_base_rsv" style="width:50%; display:inline;"/>人
             </logic:notEqual>
             <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               <span class="text_base_rsv">他&nbsp;<bean:write name="mbhRsv030Form" property="rsv030UseNum" />人</span>
             </logic:equal>
           </logic:equal>


    <% } %>

  <br>

    <logic:equal name="mbhRsv030Form" property="schedulePluginKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
    <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
    <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">

  <br>

    <logic:equal name="mbhRsv030Form" property="acordParam" value="true">
      <div data-role="collapsible" data-collapsed="true">
    </logic:equal>
    <logic:equal name="mbhRsv030Form" property="acordParam" value="false">
      <div data-role="collapsible" data-collapsed="false">
    </logic:equal>

      <h2>
        <div width="100%" align="center" class="font_small" onClick="chacord()"><gsmsg:write key="schedule.3" /></div>
      </h2>


      <% if (editSchFlg) { %>

         <% String grpColor = radioFont; %>
         <% String nameColor = radioFont; %>
         <logic:equal name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>">
           <% grpColor = radioFontActive; %>
         </logic:equal>
         <logic:equal name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>">
           <% nameColor = radioFontActive; %>
         </logic:equal>

         <fieldset data-type="horizontal" align="center">
          <html:radio styleId="refOk" name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>" onchange="chRsvYes('<%= sptm %>')" />
          <label for="refOk" class="<%= radioClass %>" id="rbr0"><span class="font_xsmall" id="rsvNo" style="color:<%= grpColor %>"><gsmsg:write key="reserve.86" /></span></label>
          <html:radio styleId="refNo" name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>" onchange="chRsvNo('<%= sptm %>')" />
          <label for="refNo" class="<%= radioClass %>" id="rbr1"><span class="font_xsmall" id="rsvYes" style="color:<%= nameColor %>"><gsmsg:write key="reserve.87" /></span></label>
         </fieldset>
      <% } %>


      <div id="schUsrText" align="center" style="display:block;"><font size="-2">[<gsmsg:write key="reserve.166" />]</font></div>
      <div id="schGrpText" align="center" style="display:none;"><font size="-2">[<gsmsg:write key="reserve.167" />]</font></div>
      <fieldset data-role="controlgroup" data-type="horizontal" align="center">
        <html:radio property="rsv030SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_USER) %>" styleId="rsvSchKbn0" onchange="rsvSchUsrChange('<%= sptm %>');"/>
        <label for="rsvSchKbn0" id="rsvSchUsrLb" class="<%= radioClass %>"><span id="rsvSchUsrBtn" class="font_xsmall" style="color:<%= radioFontActive %>"><gsmsg:write key="cmn.user" /></span></label>
        <html:radio property="rsv030SchKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_SCHKBN_GROUP) %>" styleId="rsvSchKbn1" onchange="rsvSchGrpChange('<%= sptm %>');"/>
        <label for="rsvSchKbn1" id="rsvSchGrpLb" class="<%= radioClass %>"><span id="rsvSchGrpBtn" class="font_xsmall" style="color:<%= radioFont %>"><gsmsg:write key="cmn.group" /></span></label>
      </fieldset>

      <div id="schUsrAddArea" align="center" class="font_xsmall" style="display:block">
        <input name="rsv030usrAdd" value="<gsmsg:write key="cmn.user.add" />" type="submit" data-inline="true" data-icon="plus"/>

        <logic:notEmpty name="mbhRsv030Form" property="rsv030SelectUsrLabel" scope="request">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030SelectUsrLabel" scope="request">
          <logic:iterate id="urBean" name="mbhRsv030Form" property="rsv030SelectUsrLabel" scope="request">
          <div align="center" class="font_xsmall"><input name="<bean:write name="urBean" property="usrSid" />" value="<bean:write name="urBean" property="usiSei" />&nbsp;<bean:write name="urBean" property="usiMei" />" type="submit" data-icon="delete"></div>
          </logic:iterate>
          </logic:notEmpty>
        </logic:notEmpty>

      </div>

      <div id="schGrpAddArea" data-role="controlgroup" data-type="horizontal" align="center" class="font_small" style="display:none">
        <div align="center" class="font_small">
          <html:select property="rsv030SchGroupSid" styleId="rsvSchGrpSid">
            <logic:notEmpty name="mbhRsv030Form" property="rsv030SchGroupLabel" scope="request">
                <logic:iterate id="exSchGpBean" name="mbhRsv030Form" property="rsv030SchGroupLabel" scope="request">
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
        </div>
      </div>

      </div>

    </logic:equal>
    </logic:notEqual>
    </logic:equal>

    <br>


    <div data-role="controlgroup" data-type="horizontal" align="center" class="font_small">
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <input name="rsv030back" type="submit" value="<gsmsg:write key="cmn.back" />" />
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><input name="rsv030delete" type="submit" value="<gsmsg:write key="cmn.close" />"></logic:equal>
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <input name="rsv030add" type="submit" value="ＯＫ" data-inline="true" data-icon="check">
          <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>"><input name="rsv030delete" type="submit" value="<gsmsg:write key="cmn.delete" />"></logic:equal>
          <input name="rsv030back" type="submit" value="<gsmsg:write key="cmn.back" />" data-icon="back" data-inline="true" data-iconpos="right" />
        </logic:notEqual>
      </logic:equal>
     </div>

<br>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>
</html:form>
</div><!-- /page -->

</body>
</html:html>