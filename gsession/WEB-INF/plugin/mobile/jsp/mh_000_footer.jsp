<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<hr>
<div id="mh_fmenu">
<font size="-1"><img src="../mobile/images/menu_access.gif"/><html:link action="./mobile/mh_man001.do" accesskey="*"><gsmsg:write key="cmn.main" /></html:link></font>
<logic:equal name="<%= thisForm %>" property="scheduleUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_sch030.do" accesskey="0"><%= emojiNum0.toString() %><gsmsg:write key="schedule.108" /></html:link></font>
  <br>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="smailUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_sml010.do" accesskey="1"><%= emojiNum1.toString() %><gsmsg:write key="cmn.shortmail" /></html:link></font>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="rsvUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_rsv010.do" accesskey="2"><%= emojiNum2.toString() %><gsmsg:write key="cmn.reserve" /></html:link></font>
  <br>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="nippouUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_ntp010.do" accesskey="3"><%= emojiNum3.toString() %>日報</html:link></font>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="webMailUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_wml010.do" accesskey="4"><%= emojiNum4.toString() %><gsmsg:write key="wml.wml010.25" /></html:link></font>
  <br>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="addressUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_adr010.do" accesskey="5"><%= emojiNum5.toString() %><gsmsg:write key="addressbook" /></html:link></font>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="userUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_usr010.do" accesskey="6"><%= emojiNum6.toString() %><gsmsg:write key="cmn.shain.info" /></html:link></font>
  <br>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="bullutinUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_bbs010.do" accesskey="7"><%= emojiNum7.toString() %><gsmsg:write key="cmn.bulletin" /></html:link></font>
</logic:equal>
<logic:equal name="<%= thisForm %>" property="ringiUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_rng010.do" accesskey="8"><%= emojiNum8.toString() %><gsmsg:write key="rng.62" /></html:link></font>
</logic:equal>

<br>

<logic:equal name="<%= thisForm %>" property="circularUseOk" value="0">
  <font size="-1"><html:link action="./mobile/mh_cir010.do" accesskey="9"><%= emojiNum9.toString() %><gsmsg:write key="cir.5" /></html:link></font>
</logic:equal>


</div>

<div id="mh_footer">
<hr>
<div align="center">
<a href="./mh_cmn001.do<%= jsessionId.toString() %>?CMD=logout" accesskey="0"><font size="-2"><gsmsg:write key="mobile.11" /></font></a>
</div>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_footer.jsp" %>
</div>