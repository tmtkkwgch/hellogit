<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSch060Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="cmn.reserve" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>
<body>
<html:form action="/mobile/mh_sch060">


<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="sch010SchSid" />
<html:hidden property="listMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch040BatchRef" />
<html:hidden property="sch040ResBatchRef" />
<html:hidden property="sch040contact" />
<html:hidden property="sch040ResBatchRef" />
<html:hidden property="sch040DataFlg" />
<html:hidden property="reservePluginKbn" />
<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />
<html:hidden property="sch040InitFlg" />
<html:hidden property="sch040UsrName" />
<html:hidden property="sch040AddUsrJkbn" />
<html:hidden property="sch040AddDate" />
<html:hidden property="sch040Bgcolor" />
<html:hidden property="sch040Title" />
<html:hidden property="sch040Value" />
<html:hidden property="sch040Biko" />
<html:hidden property="sch040Public" />
<html:hidden property="sch040FrYear" />
<html:hidden property="sch040FrMonth" />
<html:hidden property="sch040FrDay" />
<html:hidden property="sch040FrHour" />
<html:hidden property="sch040FrMin" />
<html:hidden property="sch040ToYear" />
<html:hidden property="sch040ToMonth" />
<html:hidden property="sch040ToDay" />
<html:hidden property="sch040ToHour" />
<html:hidden property="sch040ToMin" />
<html:hidden property="sch040Edit" />
<html:hidden property="sch040DispMod" />

<html:hidden property="sch040AttendKbn" />
<html:hidden property="sch040AttendAnsKbn" />
<html:hidden property="sch040EditDspMode" />
<html:hidden property="sch040EditMailSendKbn" />
<html:hidden property="sch040AttendDelFlg" />

<logic:notEmpty name="mbhSch060Form" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="mbhSch060Form" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch060Form" property="svReserveUsers" scope="request">
<logic:iterate id="resBean" name="mbhSch060Form" property="svReserveUsers" scope="request">
<input type="hidden" name="svReserveUsers" value="<bean:write name="resBean" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch060Form" property="sch040CompanySid">
  <logic:iterate id="coId" name="mbhSch060Form" property="sch040CompanySid">
    <input type="hidden" name="sch040CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch060Form" property="sch040CompanyBaseSid">
  <logic:iterate id="coId" name="mbhSch060Form" property="sch040CompanyBaseSid">
    <input type="hidden" name="sch040CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSch060Form" property="sch040AddressId">
  <logic:iterate id="addressId" name="mbhSch060Form" property="sch040AddressId">
    <input type="hidden" name="sch040AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<b><%= emojiTokei.toString() %><gsmsg:write key="schedule.108" /><br><gsmsg:write key="cmn.reserve" /></b>
<hr>
<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>
<%= emojiPen2.toString() %><gsmsg:write key="cmn.reserve" />
<br>

<logic:equal name="mbhSch060Form" property="reservePluginKbn" value="0">

  <logic:notEmpty name="mbhSch060Form" property="sch040ReserveGroupLavel" scope="request">
  <html:select property="sch040ReserveGroupSid">
    <html:optionsCollection name="mbhSch060Form" property="sch040ReserveGroupLavel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>
  <input name="sch060Search" value="<gsmsg:write key="cmn.search" />" type="submit">
  <br>


   <!-- グループ -->
   <logic:notEmpty name="mbhSch060Form" property="sch040ReserveBelongLavel" scope="request">
    <logic:iterate id="resbelBean" name="mbhSch060Form" property="sch040ReserveBelongLavel" scope="request">
      <input name="<%= jp.groupsession.v3.mbh.sch040.MbhSch040Form.PARAM_RSV_SELECTADR %><bean:write name="resbelBean" property="rsdSid" scope="page"/>" value="<gsmsg:write key="cmn.select" />" type="submit">
      <bean:write name="resbelBean" property="rsdName" scope="page"/><br>
    </logic:iterate>
   </logic:notEmpty>

</logic:equal>


<br>
<div align="center">
<input name="sch060back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>