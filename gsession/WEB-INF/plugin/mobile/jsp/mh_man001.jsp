<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%  String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.main" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<b><gsmsg:write key="cmn.main" /></b>
<hr>

<logic:notEmpty name="<%= key %>" scope="session">
  <font size="-1">
  <gsmsg:write key="cmn.user" />:&nbsp;&nbsp;<bean:write name="<%= key %>" scope="session" property="usisei" />&nbsp;&nbsp;
  <bean:write name="<%= key %>" scope="session" property="usimei" />
  </font>
</logic:notEmpty>

<html:form action="/mobile/mh_cmn001">
<logic:notEmpty name="mbhMan001Form" property="infoMsgs">
  <hr>
  <logic:iterate id="msg" name="mbhMan001Form" property="infoMsgs" scope="request" indexId="idx">
  <%--
    <a href="..<bean:write name="msg" property="linkUrl" />"><span style="color:blue;"><bean:write name="msg" property="mess
    <html:link action="./mobile/mh_sml020"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>

  <logic:equal name="msg" property="pluginName" value="sml">
    <html:link action="./mobile/mh_sml020"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>
  </logic:equal>
  <logic:equal name="msg" property="pluginName" value="bbs">
    <html:link action="./mobile/mh_bbs010"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>
  </logic:equal>
  <logic:equal name="msg" property="pluginName" value="rng">
    <html:link action="./mobile/mh_rng020"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>
  </logic:equal>
  <logic:equal name="msg" property="pluginName" value="cir">
    <html:link action="./mobile/mh_cir020"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>
  </logic:equal>
  <logic:equal name="msg" property="pluginName" value="wml">
    <html:link action="./mobile/mh_wml010"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>
  </logic:equal>
  <logic:equal name="msg" property="pluginName" value="info">
    <html:link action="./mobile/mh_man002" paramId="imssid" paramName="msg" paramProperty="linkUrl"><span style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></html:link>
  </logic:equal>
    <br>

--%>
          <bean:define id="urlStr" name="msg" property="linkUrl" type="java.lang.String" />
          <% String urlString = urlStr; %>
          <% String reqUrl = urlString.replace(".do", ".do;jsessionid=" + session.getId()); %>

          <table>
            <tr>
              <td style="padding-top:3px;">
                <a href="..<%= reqUrl %>">
                  <b><span class="font_small" style="color:blue;"><bean:write name="msg" property="message" filter="false"/></span></b>
                </a>
              </td>
            </tr>
          </table>



  </logic:iterate>
</logic:notEmpty>

<hr>
<logic:equal name="mbhMan001Form" property="scheduleUseOk" value="0">
  <%= emojiTokei.toString() %><b><gsmsg:write key="schedule.108" /></b>
  <br>┣<html:link action="./mobile/mh_sch030" accesskey="0"><%= emojiNum0.toString() %><gsmsg:write key="mobile.18" /></html:link>
  <br>┣<html:link action="./mobile/mh_sch010" accesskey="1"><%= emojiNum1.toString() %><gsmsg:write key="schedule.19" /></html:link>
  <br>┗<html:link action="./mobile/mh_sch020" accesskey="2"><%= emojiNum2.toString() %><gsmsg:write key="mobile.19" /></html:link>
</logic:equal>
<logic:equal name="mbhMan001Form" property="smailUseOk" value="0">
  <br>
  <b>
  <html:link action="./mobile/mh_sml010" accesskey="3"><%= emojiNum3.toString() %><gsmsg:write key="cmn.shortmail" /></html:link>
  <logic:greaterThan name="mbhMan001Form" property="man001SmlCnt" value="0">
    <html:link action="./mobile/mh_sml020">(<bean:write name="mbhMan001Form" property="man001SmlCnt" />)</html:link>
  </logic:greaterThan>
  </b>
</logic:equal>

<logic:equal name="mbhMan001Form" property="rsvUseOk" value="0">
  <br>
  <b>
  <html:link action="./mobile/mh_rsv010" accesskey="4"><%= emojiNum4.toString() %><gsmsg:write key="cmn.reserve" /></html:link>
  </b>
</logic:equal>

<logic:equal name="mbhMan001Form" property="nippouUseOk" value="0">
  <br>
  <%= emojiTokei.toString() %><b><gsmsg:write key="ntp.1" /></b>
  <br>┣<html:link action="./mobile/mh_ntp030"><gsmsg:write key="mobile.18" /></html:link>
  <br>┣<html:link action="./mobile/mh_ntp010"><gsmsg:write key="schedule.19" /></html:link>
  <br>┗<html:link action="./mobile/mh_ntp020"><gsmsg:write key="mobile.19" /></html:link>
</logic:equal>

<logic:equal name="mbhMan001Form" property="webMailUseOk" value="0">
  <logic:notEmpty name="mbhMan001Form" property="man001WmlAccountList">
  <br><%= emojiMail.toString() %><b><gsmsg:write key="wml.wml010.25" /></b>
  <bean:define id="accountCnt" name="mbhMan001Form" property="man001WmlAccountCount" type="java.lang.Integer" />
  <logic:iterate id="accountData" name="mbhMan001Form" property="man001WmlAccountList" indexId="accountIdx">
  <bean:define id="noReadCnt" name="accountData" property="notReadCount" type="java.lang.Long" />
  <% boolean noRead = noReadCnt.longValue() > 0; %>

  <br><%= (accountIdx.intValue() + 1 == accountCnt.intValue())?"┗":"┣" %><% if (noRead) { %><b><% } %><a href="./mh_wml010.do;jsessionid=<%= session.getId() %>?wmlAccount=<bean:write name="accountData" property="wacSid" />" accessKey="<%= String.valueOf(accountIdx.intValue() + 6) %>"><bean:write name="accountData" property="wacName" /></a><% if (noRead) { %><a href="./mh_wml020.do<%= jsessionId.toString() %>?wmlAccount=<bean:write name="accountData" property="wacSid" />&wmlDirectory=<bean:write name="accountData" property="receiveWdrSid" />"><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %></a><% if (noRead) { %></b><% } %>
  <%--
  <br><%= (accountIdx.intValue() + 1 == accountCnt.intValue())?"┗":"┣" %><% if (noRead) { %><b><% } %><html:link action="./mobile/mh_wml010"><bean:write name="accountData" property="wacName" /></a><% if (noRead) { %><a href="./mh_wml020.do<%= jsessionId.toString() %>?wmlAccount=<bean:write name="accountData" property="wacSid" />&wmlDirectory=<bean:write name="accountData" property="receiveWdrSid" />"><%= "(" + String.valueOf(noReadCnt.longValue()) + ")" %><% } %></html:link><% if (noRead) { %></b><% } %>
    --%>
  </logic:iterate>
  </logic:notEmpty>
</logic:equal>
<logic:equal name="mbhMan001Form" property="addressUseOk" value="0">
  <br><b><html:link action="./mobile/mh_adr010" accesskey="5"><%= emojiNum5.toString() %><gsmsg:write key="addressbook" /></html:link></b>
</logic:equal>

<logic:equal name="mbhMan001Form" property="userUseOk" value="0">
  <br><b><html:link action="./mobile/mh_usr010" accesskey="6"><%= emojiNum6.toString() %><gsmsg:write key="cmn.shain.info" /></html:link>
</logic:equal>
<logic:equal name="mbhMan001Form" property="bullutinUseOk" value="0">
  <br>
  <b>
  <html:link action="./mobile/mh_bbs010" accesskey="7"><%= emojiNum7.toString() %><gsmsg:write key="cmn.bulletin" /></html:link>
  <logic:greaterThan name="mbhMan001Form" property="man001BbsCnt" value="0">
    <html:link action="./mobile/mh_bbs010">(<bean:write name="mbhMan001Form" property="man001BbsCnt" />)</html:link>
  </logic:greaterThan>
  </b>
</logic:equal>
<logic:equal name="mbhMan001Form" property="ringiUseOk" value="0">
  <br>
  <b>
  <html:link action="./mobile/mh_rng010" accesskey="8"><%= emojiNum8.toString() %><gsmsg:write key="rng.62" /></html:link>
  <logic:greaterThan name="mbhMan001Form" property="man001RngCnt" value="0">
    <html:link action="./mobile/mh_rng020">(<bean:write name="mbhMan001Form" property="man001RngCnt" />)</html:link>
  </logic:greaterThan>
  </b>
</logic:equal>

<logic:equal name="mbhMan001Form" property="circularUseOk" value="0">
  <br>
  <b>
  <html:link action="./mobile/mh_cir010" accesskey="9"><%= emojiNum9.toString() %><gsmsg:write key="cir.5" /></html:link>
  <logic:greaterThan name="mbhMan001Form" property="man001CirCnt" value="0">
    <html:link action="./mobile/mh_cir020">(<bean:write name="mbhMan001Form" property="man001CirCnt" />)</html:link>
  </logic:greaterThan>
  </b>
</logic:equal>

<div id="mh_footer">
<hr>
<div align="center">
<a href="./mh_cmn001.do<%= jsessionId.toString() %>?CMD=logout" accesskey="0"><font size="-2"><gsmsg:write key="mobile.11" /></font></a>
</div>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_footer.jsp" %>
</div>

</html:form>

</body>
</html:html>