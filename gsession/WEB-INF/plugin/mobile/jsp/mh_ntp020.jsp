<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% thisForm = "mbhNtp020Form"; %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> グループ日間</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<% long ntpTipCnt = 0; %>
<% String chkClass = "ntp_chk"; %>
<body class="body_03" onload="setToUser();" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/mobile/mh_ntp020">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="1">

<input type="hidden" name="ntp010SelectUsrSid" value="-1">
<input type="hidden" name="ntp010SelectUsrKbn" value="0">

<html:hidden property="cmd" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp020SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />

<b><%= emojiTokei.toString() %><gsmsg:write key="ntp.1" /><br>グループ日間</b>
<hr>

●<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=add&dspMod=2&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp020Form" property="changeDateFlg" />" accesskey="1"><gsmsg:write key="cmn.entry" /></a>
<br>
<html:select property="ntp010DspGpSid" styleClass="">
  <logic:notEmpty name="mbhNtp020Form" property="ntp020GpLabelList" scope="request">
  <logic:iterate id="gpBean" name="mbhNtp020Form" property="ntp020GpLabelList" scope="request">

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

<input name="groupButton" value="<gsmsg:write key="cmn.select" />" type="submit">

<hr>

<% String dayColor = "#000000"; %>
<logic:equal name="mbhNtp020Form" property="ntp020StrDspDateKbn" value="7">
  <% dayColor = "blue"; %>
</logic:equal>
<logic:equal name="mbhNtp020Form" property="ntp020StrDspDateKbn" value="1">
  <% dayColor = "red"; %>
</logic:equal>

<span style="color:<%= dayColor %>;"><b><bean:write name="mbhNtp020Form" property="ntp020StrDspDate" /></b></span>


<br>

<!-- 本人 -->
<logic:notEmpty name="mbhNtp020Form" property="ntp020TopList" scope="request">
<logic:iterate id="weekMdl" name="mbhNtp020Form" property="ntp020TopList" scope="request">

<bean:define id="usrMdl" name="weekMdl" property="ntp010UsrMdl"/>
<br>[<bean:write name="usrMdl" property="usrName" />]


<!-- 日報情報 -->
<logic:notEmpty name="weekMdl" property="ntp010NtpList">
<logic:iterate id="dayMdl" name="weekMdl" property="ntp010NtpList">




<br>

<logic:notEmpty name="dayMdl" property="ntpDataList">
<logic:iterate id="ntpMdl" name="dayMdl" property="ntpDataList">



<bean:define id="chKbn" name="ntpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>

<div onClick="editNippou('edit', <bean:write name="dayMdl" property="ntpDate" />, <bean:write name="ntpMdl" property="ntpSid" />, <bean:write name="dayMdl" property="usrSid" />, 0);" class="<%= chkClass %>" style="padding-top:3px;">

<bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
<bean:define id="u_ntpsid" name="ntpMdl" property="ntpSid" type="java.lang.Integer" />
<bean:define id="u_date" name="dayMdl" property="ntpDate"  type="java.lang.String" />

<%
String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
String tipskey2 = ((Integer)pageContext.getAttribute("u_ntpsid",PageContext.PAGE_SCOPE)).toString();
String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
%>


<!-- コメントアイコン表示  -->
<logic:notEqual name="ntpMdl" property="ntp_cmtCnt" value="0">
<logic:equal name="ntpMdl" property="ntp_cmtkbn" value="0">
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="comment_icon_str2"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_cmtkbn" value="1">
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_cmtkbn" value="2">
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
</logic:equal>

<logic:equal name="ntpMdl" property="ntp_goodCnt" value="0">
<br style="clear:both;line-height:1px;">
</logic:equal>
</logic:notEqual>


<!-- いいねアイコン表示  -->
<logic:notEqual name="ntpMdl" property="ntp_goodCnt" value="0">
<logic:equal name="ntpMdl" property="ntp_goodkbn" value="0">
<span id="lt" style="height:16px;width:19px" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="good_icon_str2"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_goodkbn" value="1">
<span id="lt" style="height:16px;width:19px" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_goodkbn" value="2">
<span id="lt" style="height:16px;width:19px" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
</logic:equal>

<br style="clear:both;line-height:1px;">

</logic:notEqual>

<logic:notEmpty name="ntpMdl" property="time">
<font size="-2" class="time_line_height" style="color:#ff0000;"><bean:write name="ntpMdl" property="time" /><br></font>┗
</logic:notEmpty>

<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"  id="ntpsid<%= tipskey %>">


<!--タイトルカラー設定-->
<logic:equal name="ntpMdl" property="titleColor" value="0">
<span class="sc_link_1">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="1">
<span class="sc_link_1">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="2">
<span class="sc_link_2">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="3">
<span class="sc_link_3">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="4">
<span class="sc_link_4">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="5">
<span class="sc_link_5">
</logic:equal>

<bean:write name="ntpMdl" property="title" />
</span>
</a>

</div>
</logic:iterate>
</logic:notEmpty>



</logic:iterate>
</logic:notEmpty>

</logic:iterate>
</logic:notEmpty>

<br>

<!-- グループメンバー -->
<logic:notEmpty name="mbhNtp020Form" property="ntp020BottomList" scope="request">
<logic:iterate id="gpWeekMdl" name="mbhNtp020Form" property="ntp020BottomList" scope="request" indexId="cnt">
<bean:define id="usrMdl" name="gpWeekMdl" property="ntp010UsrMdl"/>
[<bean:write name="usrMdl" property="usrName" />]

<!-- 日報情報 -->
<logic:notEmpty name="gpWeekMdl" property="ntp010NtpList">
<logic:iterate id="gpDayMdl" name="gpWeekMdl" property="ntp010NtpList">



<br>

<logic:notEmpty name="gpDayMdl" property="ntpDataList">

<logic:iterate id="gpNtpMdl" name="gpDayMdl" property="ntpDataList">

<bean:define id="chkKbn" name="gpNtpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chkKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>

<div onClick="editNippou('edit', <bean:write name="gpDayMdl" property="ntpDate" />, <bean:write name="gpNtpMdl" property="ntpSid" />, <bean:write name="usrMdl" property="usrSid" />, 0);" class="<%= chkClass %>" style="padding-top:3px;">

<bean:define id="u_usrsid" name="gpDayMdl" property="usrSid" type="java.lang.Integer" />
<bean:define id="u_nipsid" name="gpNtpMdl" property="ntpSid" type="java.lang.Integer" />
<bean:define id="u_date" name="gpDayMdl" property="ntpDate"  type="java.lang.String" />

<%
String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
String tipskey2 = ((Integer)pageContext.getAttribute("u_nipsid",PageContext.PAGE_SCOPE)).toString();
String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
%>


<!-- コメントアイコン表示  -->
<logic:notEqual name="gpNtpMdl" property="ntp_cmtCnt" value="0">
<logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="0">
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str2"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></a></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="1">
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></a></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="2">
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></a></span>
</logic:equal>

<logic:equal name="gpNtpMdl" property="ntp_goodCnt" value="0">
<br style="clear:both;line-height:1px;">
</logic:equal>
</logic:notEqual>


<!-- いいねアイコン表示  -->
<logic:notEqual name="gpNtpMdl" property="ntp_goodCnt" value="0">
<logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="0">
<span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str2"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></a></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="1">
<span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></a></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="2">
<span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></a></span>
</logic:equal>

<br style="clear:both;line-height:1px;">

</logic:notEqual>


<logic:notEmpty name="gpNtpMdl" property="time">
<font size="-2" class="time_line_height" style="color:#ff0000;"><bean:write name="gpNtpMdl" property="time" /><br></font>┗
</logic:notEmpty>

<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="gpDayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="gpNtpMdl" property="ntpSid" />" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>">



<!--タイトルカラー設定-->
<logic:equal name="gpNtpMdl" property="titleColor" value="0">
<span class="sc_link_1">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="1">
<span class="sc_link_1">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="2">
<span class="sc_link_2">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="3">
<span class="sc_link_3">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="4">
<span class="sc_link_4">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="5">
<span class="sc_link_5">
</logic:equal>

<bean:write name="gpNtpMdl" property="title" />
</span>
</a>

</div>
</logic:iterate>
</logic:notEmpty>


</logic:iterate>
</logic:notEmpty>

<br>

</logic:iterate>
</logic:notEmpty>



<div align="center">
  <a href="../mobile/mh_ntp020.do<%= jsessionId.toString() %>?CMD=move_ld&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp020Form" property="ntp010DspGpSid" />&changeDateFlg=1"><gsmsg:write key="cmn.previous.day" /></a>/
  <a href="../mobile/mh_ntp020.do<%= jsessionId.toString() %>?CMD=today&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp020Form" property="ntp010DspGpSid" />&changeDateFlg=0"><gsmsg:write key="cmn.today" /></a>/
  <a href="../mobile/mh_ntp020.do<%= jsessionId.toString() %>?CMD=move_rd&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp020Form" property="ntp010DspGpSid" />&changeDateFlg=1"><gsmsg:write key="cmn.nextday" /></a>&nbsp;
</div>

<hr>
<a href="../mobile/mh_ntp030.do<%= jsessionId.toString() %>"><gsmsg:write key="mobile.18" /></a>
<br>
<a href="../mobile/mh_ntp010.do<%= jsessionId.toString() %>"><gsmsg:write key="schedule.19" /></a>
<br>
<a href="../mobile/mh_ntp020.do<%= jsessionId.toString() %>"><gsmsg:write key="mobile.19" /></a>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>