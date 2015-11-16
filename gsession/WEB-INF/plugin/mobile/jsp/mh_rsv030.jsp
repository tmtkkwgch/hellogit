<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRsv030Form"; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.reserve" /><gsmsg:write key="cmn.entry" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
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

<body class="body_03">

<html:form action="/mobile/mh_rsv030">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvGrpFlg" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv010SisetuId" />
<html:hidden property="rsv010DspMode" />
<html:hidden property="rsv020SisetuId" />
<html:hidden property="rsv020DspMode" />
<html:hidden property="rsv030ProcMode" />
<html:hidden property="rsv030InitFlg" />
<html:hidden property="rsv030RsySid" />
<html:hidden property="rsv030RsdSid" />
<html:hidden property="rsv030SinkiDefaultDate" />
<html:hidden property="rsv030ScdRsSid" />
<html:hidden property="rsv030EditAuth" />
<html:hidden property="rsv030SchKbn" />
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

<b><%= emojiTokei.toString() %><gsmsg:write key="cmn.reserve" /><br><gsmsg:write key="cmn.entry" /></b>
<hr>

<logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>


    ■<span class="text_bb1"><gsmsg:write key="cmn.facility.group" /></span><br>
    <bean:write name="mbhRsv030Form" property="rsv030GrpName" /><br>

    ■<span class="text_bb1"><gsmsg:write key="cmn.facility.name" /></span><br>
    <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030SisetuName" /></span><br>

    ■<span class="text_bb1"><gsmsg:write key="cmn.registant" /></span><br>
    <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Torokusya" /></span><br>

<!-- 印刷 -->
    <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_CAR) %>">
      <logic:equal name="mbhRsv030Form" property="rsvPrintUseKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_PRINT_USE_YES) %>">

    ■<span class="text_bb1"><gsmsg:write key="reserve.print" /></span>
        <br>
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:equal name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.yes" /></span></logic:equal>
        <logic:notEqual name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.no" /></span></logic:notEqual>
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <html:checkbox name="mbhRsv030Form" property="rsv030PrintKbn" value="1" styleId="print"/><label for="print" class="text_base"><span class="text_base_rsv"><gsmsg:write key="reserve.print.yes" /></span></label>
        </logic:notEqual>
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <logic:equal name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.yes" /></span></logic:equal>
          <logic:notEqual name="mbhRsv030Form" property="rsv030PrintKbn" value="1"><span class="text_base_rsv"><gsmsg:write key="reserve.print.no" /></span></logic:notEqual>
        </logic:equal>
      </logic:equal>
      </logic:equal>
    </logic:equal>
    <br>

    ■<span class="text_bb1"><gsmsg:write key="reserve.72" /></span><logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true"><logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></logic:notEqual></logic:equal>


    <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <br><span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Mokuteki" /></span>
    </logic:notEqual>

    <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
        <br><html:text name="mbhRsv030Form" property="rsv030Mokuteki" size="40" maxlength="50" styleClass="text_base_rsv" />
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
        <br><span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Mokuteki" /></span>
      </logic:equal>
    </logic:equal>

    <logic:equal name="mbhRsv030Form" property="rsv030SisetuKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSK_KBN_HEYA) %>">

    <div class="text_bb1">■<gsmsg:write key="reserve.use.kbn" /></div>
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
<!-- 連絡先 -->
    <br>
    ■<span class="text_bb1"><gsmsg:write key="reserve.contact" /></span><br>

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
      <div class="text_bb1">■<gsmsg:write key="reserve.guide" /></div>
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

      <div class="text_bb1">■<gsmsg:write key="reserve.park.num" /></div>
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
      <div class="text_bb1">■<gsmsg:write key="reserve.dest" /></div>
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


    <br>

    ■<span class="text_bb1"><gsmsg:write key="cmn.start" /></span><br>

      <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
      <span class="text_base"><bean:write name="mbhRsv030Form" property="yoyakuFrString" /></span>
      </logic:equal>

      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <span class="text_base"><bean:write name="mbhRsv030Form" property="yoyakuFrString" /></span>
      </logic:notEqual>

      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">

        <html:select property="rsv030SelectedYearFr" styleId="fromYear">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030YearComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030YearComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv030SelectedMonthFr" styleId="fromMonth">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030MonthComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030MonthComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv030SelectedDayFr" styleId="fromDay">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030DayComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030DayComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

       <br>

        <html:select property="rsv030SelectedHourFr">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030HourComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030HourComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.hour.input" />

        <html:select property="rsv030SelectedMinuteFr">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030MinuteComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030MinuteComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.minute.input" />

        </logic:notEqual>
      </logic:equal>


      <br>


      ■<span class="text_bb1"><gsmsg:write key="cmn.end" /></span><br>


      <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
      <span class="text_base"><bean:write name="mbhRsv030Form" property="yoyakuToString" /></span>
      </logic:equal>

      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
      <span class="text_base"><bean:write name="mbhRsv030Form" property="yoyakuToString" /></span>
      </logic:notEqual>

      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">

        <html:select property="rsv030SelectedYearTo" styleId="toYear">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030YearComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030YearComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv030SelectedMonthTo" styleId="toMonth">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030MonthComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030MonthComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <html:select property="rsv030SelectedDayTo" styleId="toDay">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030DayComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030DayComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>

        <br>

        <html:select property="rsv030SelectedHourTo">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030HourComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030HourComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.hour.input" />

        <html:select property="rsv030SelectedMinuteTo">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030MinuteComboList">
            <html:optionsCollection name="mbhRsv030Form" property="rsv030MinuteComboList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        <gsmsg:write key="cmn.minute.input" />

        </logic:notEqual>
      </logic:equal>

      <br>

      ■<span class="text_bb1"><gsmsg:write key="cmn.content" /></span><br>


      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <span class="text_base_rsv"><bean:write name="mbhRsv030Form" property="rsv030Naiyo" filter="false" /></span>
      </logic:notEqual>

      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <textarea styleClass="text_base_rsv" name="rsv030Naiyo" cols="25" rows="6"" id="inputstr"><bean:write name="mbhRsv030Form" property="rsv030Naiyo" /></textarea>

        </logic:notEqual>

      </logic:equal>

      <br>

      ■<span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><br>
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
           <div class="text_bb1">■<gsmsg:write key="reserve.busyo" /></div>
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

           <div class="text_bb1">■<%= headName %></div>
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

           <div class="text_bb1">■<gsmsg:write key="reserve.use.num" /></div>
           <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <span class="text_base_rsv">他&nbsp;<bean:write name="mbhRsv030Form" property="rsv030UseNum" />人</span>
           </logic:notEqual>
           <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
             <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
               他&nbsp;<html:text name="mbhRsv030Form" property="rsv030UseNum" size="5" maxlength="5" styleClass="text_base_rsv" />人
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

      ■<span class="text_bb1"><gsmsg:write key="schedule.3" /></span>

      <logic:equal name="mbhRsv030Form" property="rsv030SchKbn" value="0">
        <input name="rsv030SchGroup" value="<gsmsg:write key="cmn.group" />で登録" type="submit"><br>
        <% if (editSchFlg) { %>
          <font size="-1"><html:radio styleId="refOk" name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>" onclick="rsvSchDisabled();" /><label for="refOk"><gsmsg:write key="reserve.86" /></label></font><br>
          <font size="-1"><html:radio styleId="refNo" name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>" onclick="rsvSchDisabled();" /><label for="refNo"><gsmsg:write key="reserve.87" /></label></font><br>
       <% } %>
        <font size="-2">[<gsmsg:write key="reserve.166" />]</font><br>

        <input name="rsv030usrAdd" value="ﾕｰｻﾞ追加　" type="submit" /><br>
        <!-- 同時登録先 -->

        <logic:notEmpty name="mbhRsv030Form" property="rsv030SelectUsrLabel" scope="request">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030SelectUsrLabel" scope="request">
          <logic:iterate id="urBean" name="mbhRsv030Form" property="rsv030SelectUsrLabel" scope="request">
          <input name="<bean:write name="urBean" property="usrSid" />" value="<gsmsg:write key="cmn.delete" />" type="submit"><bean:write name="urBean" property="usiSei" />&nbsp;<bean:write name="urBean" property="usiMei" />
          <br>
          </logic:iterate>
          </logic:notEmpty>
        </logic:notEmpty>

      </logic:equal>

      <logic:equal name="mbhRsv030Form" property="rsv030SchKbn" value="1">
        <input name="rsv030SchUser" value="<gsmsg:write key="cmn.user" />で登録" type="submit"><br>
        <% if (editSchFlg) { %>
          <font size="-1"><html:radio styleId="refOk" name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_OK) %>" onclick="rsvSchDisabled();" /><label for="refOk"><gsmsg:write key="reserve.86" /></label></font><br>
          <font size="-1"><html:radio styleId="refNo" name="mbhRsv030Form" property="rsv030ScdReflection" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SCD_REFLECTION_NO) %>" onclick="rsvSchDisabled();" /><label for="refNo"><gsmsg:write key="reserve.87" /></label></font><br>
        <% } %>
        <font size="-2">[<gsmsg:write key="reserve.167" />]</font><br>
        <html:select property="rsv030SchGroupSid" styleId="rsvSchGrpSid">
          <logic:notEmpty name="mbhRsv030Form" property="rsv030SchGroupLabel" scope="request">
            <logic:iterate id="exSchGpBean" name="mbhRsv030Form" property="rsv030SchGroupLabel" scope="request">
              <bean:define id="gpValue" name="exSchGpBean" property="value" type="java.lang.String" />
              <% boolean schGpDisabled = false; %>
              <logic:notEqual name="exSchGpBean" property="viewKbn" value="false">
                <html:option value="<%= gpValue %>" disabled="<%= schGpDisabled %>"><bean:write name="exSchGpBean" property="label" /></html:option>
              </logic:notEqual>

            </logic:iterate>

          </logic:notEmpty>
        </html:select>
      </logic:equal>


    </logic:equal>
    </logic:notEqual>
    </logic:equal>


    <br>
    <br>


    <div align="center">
      <logic:notEqual name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <input name="rsv030back" type="submit" value="<gsmsg:write key="cmn.back" />" />
      </logic:notEqual>
      <logic:equal name="mbhRsv030Form" property="rsv030EditAuth" value="true">
        <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>"><input name="rsv030delete" type="submit" value="<gsmsg:write key="cmn.close" />"></logic:equal>
        <logic:notEqual name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_POPUP) %>">
          <input name="rsv030add" type="submit" value="OK">
          <logic:equal name="mbhRsv030Form" property="rsv030ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>"><input name="rsv030delete" type="submit" value="<gsmsg:write key="cmn.delete" />"></logic:equal>
          <input name="rsv030back" type="submit" value="<gsmsg:write key="cmn.back" />" />
        </logic:notEqual>
      </logic:equal>
     </div>

<hr>

  <a href="../mobile/mh_rsv010.do<%= jsessionId.toString() %>"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.weeks" /></a>
  <br>
  <a href="../mobile/mh_rsv020.do<%= jsessionId.toString() %>"><gsmsg:write key="cmn.group" /><gsmsg:write key="cmn.days2" /></a>
  <br>
  <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsv040DspMode=2"><gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.weeks" /></a>
  <br>
  <a href="../mobile/mh_rsv040.do<%= jsessionId.toString() %>?rsvGrpFlg=1&rsv040DspMode=1"><gsmsg:write key="cmn.facility" /><gsmsg:write key="cmn.days2" /></a>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>