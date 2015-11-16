<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhRsv050Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.reserve" /><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.add" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_rsv050">
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
<html:hidden property="rsv030SelectedYearFr" />
<html:hidden property="rsv030SelectedMonthFr" />
<html:hidden property="rsv030SelectedDayFr" />
<html:hidden property="rsv030SelectedHourFr" />
<html:hidden property="rsv030SelectedMinuteFr" />
<html:hidden property="rsv030SelectedYearTo" />
<html:hidden property="rsv030SelectedMonthTo" />
<html:hidden property="rsv030SelectedDayTo" />
<html:hidden property="rsv030SelectedHourTo" />
<html:hidden property="rsv030SelectedMinuteTo" />
<html:hidden property="rsv030RsyEdit" />
<html:hidden property="rsv030Mokuteki" />
<html:hidden property="rsv030Naiyo" />

<html:hidden property="rsv030SisetuKbn" />
<html:hidden property="rsvPrintUseKbn" />
<html:hidden property="rsv030Busyo" />
<html:hidden property="rsv030UseName" />
<html:hidden property="rsv030UseNum" />
<html:hidden property="rsv030UseKbn" />
<html:hidden property="rsv030Contact" />
<html:hidden property="rsv030Guide" />
<html:hidden property="rsv030ParkNum" />
<html:hidden property="rsv030PrintKbn" />
<html:hidden property="rsv030Dest" />

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
<logic:notEmpty name="mbhRsv050Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="mbhRsv050Form" property="rsv100CsvOutField" scope="request">
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



<logic:notEmpty name="mbhRsv050Form" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="mbhRsv050Form" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>


<b><%= emojiTokei.toString() %><gsmsg:write key="cmn.reserve" /><br><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.add" /></b>


<hr>

<%= emojiPen.toString() %><span class="text_bb1"><gsmsg:write key="cmn.group" /></span>
<br>


<logic:notEmpty name="mbhRsv050Form" property="rsv030GroupLabel" scope="request">
  <html:select property="rsv030GroupSid" onchange="buttonPush('030_group');" styleId="rsvSchGrpLabel">
  <logic:notEmpty name="mbhRsv050Form" property="rsv030GroupLabel" scope="request">
    <logic:iterate id="gpBean" name="mbhRsv050Form" property="rsv030GroupLabel" scope="request">
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
</logic:notEmpty>

<input name="sch050Search" value="検索" type="submit">
<br><br>


<logic:notEmpty name="mbhRsv050Form" property="rsv030BelongLabel" scope="request">
  <logic:iterate id="urBean" name="mbhRsv050Form" property="rsv030BelongLabel" scope="request">
    <input name="<%= jp.groupsession.v3.mbh.rsv030.MbhRsv030Form.PARAM_SELECTADR %><bean:write name="urBean" property="usrSid" scope="page"/>" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/><br>
  </logic:iterate>
</logic:notEmpty>

<br>

<input name="rsv050back" value="戻る" type="submit">
</div>

</html:form>

</body>
</html:html>