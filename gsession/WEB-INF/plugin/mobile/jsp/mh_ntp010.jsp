<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% thisForm = "mbhNtp010Form"; %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> 個人週間</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
</head>

<% long ntpTipCnt = 0; %>
<% String chkClass = "ntp_chk"; %>
<body class="body_03" onload="setToUser();" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">
<html:form action="/mobile/mh_ntp010">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="1">

<input type="hidden" name="ntp010SelectUsrSid" value="-1">
<input type="hidden" name="ntp010SelectUsrKbn" value="0">

<html:hidden property="cmd" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>

<b><%= emojiTokei.toString() %><gsmsg:write key="ntp.1" /><br>個人週間</b>
<hr>
<logic:equal name="mbhNtp010Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">
●<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=add&dspMod=1&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp010Form" property="changeDateFlg" />" accesskey="1"><gsmsg:write key="cmn.entry" /></a>
</logic:equal>
<br><bean:write name="mbhNtp010Form" property="ntp010UserName" /><a href="../mobile/mh_ntp050.do<%= jsessionId.toString() %>?ntp050DispMod=usrChange&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />&changeDateFlg=<bean:write name="mbhNtp010Form" property="changeDateFlg" />&ntp050DispId=mh_ntp010"><gsmsg:write key="mobile.10" /></a>

<hr>

<!-- 本人 -->
<logic:notEmpty name="mbhNtp010Form" property="ntp010TopList" scope="request">
<logic:iterate id="weekMdl" name="mbhNtp010Form" property="ntp010TopList" scope="request">


<!-- 日報情報 -->
<logic:notEmpty name="weekMdl" property="ntp010NtpList">
<logic:iterate id="dayMdl" name="weekMdl" property="ntp010NtpList">

<logic:equal name="dayMdl" property="weekKbn" value="1">
<td align="left" valign="top" class="td_type9">
</logic:equal>
<logic:equal name="dayMdl" property="weekKbn" value="7">
<td align="left" valign="top" class="td_type8">
</logic:equal>

<logic:notEqual name="dayMdl" property="weekKbn" value="1">
<logic:notEqual name="dayMdl" property="weekKbn" value="7">
<td align="left" valign="top" class="td_type1">
</logic:notEqual>
</logic:notEqual>

<% String dayColor = "#000000"; %>
<logic:equal name="dayMdl" property="weekKbn" value="7">
  <% dayColor = "blue"; %>
</logic:equal>
<logic:equal name="dayMdl" property="weekKbn" value="1">
  <% dayColor = "red"; %>
</logic:equal>

<span style="color:<%= dayColor %>;"><bean:write name="dayMdl" property="ntpDate" /></span>

<br>

<logic:notEmpty name="dayMdl" property="ntpDataList">
<logic:iterate id="ntpMdl" name="dayMdl" property="ntpDataList">



<bean:define id="chKbn" name="ntpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>

<div  class="<%= chkClass %>" style="padding-top:3px;">

<bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
<bean:define id="u_ntpsid" name="ntpMdl" property="ntpSid" type="java.lang.Integer" />
<bean:define id="u_date" name="dayMdl" property="ntpDate"  type="java.lang.String" />

<%
String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
String tipskey2 = ((Integer)pageContext.getAttribute("u_ntpsid",PageContext.PAGE_SCOPE)).toString();
String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
%>


<logic:notEmpty name="ntpMdl" property="time">
<font size="-2" class="time_line_height" style="color:#ff0000;"><bean:write name="ntpMdl" property="time" /><br></font>
</logic:notEmpty>

<!-- コメントアイコン表示  -->
<logic:notEqual name="ntpMdl" property="ntp_cmtCnt" value="0">
  <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="0">
    <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="comment_icon_str2"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="1">
    <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="2">
    <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
  </logic:equal>

  <logic:equal name="ntpMdl" property="ntp_goodCnt" value="0">
    <br style="clear:both;line-height:1px;">
  </logic:equal>
</logic:notEqual>


<!-- いいねアイコン表示  -->
<logic:notEqual name="ntpMdl" property="ntp_goodCnt" value="0">
  <logic:equal name="ntpMdl" property="ntp_goodkbn" value="0">
    <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="good_icon_str2"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_goodkbn" value="1">
    <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_goodkbn" value="2">
    <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
  </logic:equal>

  <br style="clear:both;line-height:1px;">

</logic:notEqual>

<logic:empty name="ntpMdl" property="detail">
┗<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" id="" title="<bean:write name="ntpMdl" property="title" />" >
</logic:empty>


<logic:notEmpty name="ntpMdl" property="detail">
<bean:define id="ntdetailu" name="ntpMdl" property="detail" />
<%
String tmpText = (String)pageContext.getAttribute("ntdetailu",PageContext.PAGE_SCOPE);
String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
%>
┗<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=1&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />" id="tooltips_ntp<%= String.valueOf(ntpTipCnt++) %>"  id="ntpsid<%= tipskey %>" title="<gsmsg:write key="cmn.content" />:<%= tmpText2 %>">
</logic:notEmpty>




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

<br>

</logic:iterate>
</logic:notEmpty>




</logic:iterate>
</logic:notEmpty>

<hr>
<div align="center">
<a href="../mobile/mh_ntp010.do<%= jsessionId.toString() %>?CMD=move_lw&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />"><gsmsg:write key="mobile.24" /></a>/<a href="../mobile/mh_ntp010.do<%= jsessionId.toString() %>?CMD=move_ld&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />"><gsmsg:write key="cmn.previous.day" /></a>/<a href="../mobile/mh_ntp010.do<%= jsessionId.toString() %>?CMD=today&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=0&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />"><gsmsg:write key="cmn.today" /></a>/<a href="../mobile/mh_ntp010.do<%= jsessionId.toString() %>?CMD=move_rd&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />"><gsmsg:write key="cmn.nextday" /></a>/<a href="../mobile/mh_ntp010.do<%= jsessionId.toString() %>?CMD=move_rw&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />"><gsmsg:write key="mobile.25" /></a>&nbsp;
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