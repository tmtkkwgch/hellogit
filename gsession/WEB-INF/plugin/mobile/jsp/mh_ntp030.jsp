<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% thisForm = "mbhNtp030Form"; %>
<% String chkClass = "ntp_chk"; %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" />(個人日間)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body class="body_03" onunload="windowClose();calWindowClose();">
<html:form action="/mobile/mh_ntp030">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<input type="hidden" name="dspMod" value="3" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp030SessionSid" />
<html:hidden property="ntp030SelectUsrSid" />
<html:hidden property="ntp030Offset" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp030LabelDate" />
<html:hidden property="ntp010DspDate" />



<b><%= emojiTokei.toString() %><gsmsg:write key="ntp.1" /><br><gsmsg:write key="mobile.18" /></b>
<hr>
<logic:equal name="mbhNtp030Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">
●<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=add&dspMod=3&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp030Form" property="changeDateFlg" />"><gsmsg:write key="cmn.entry" /></a>
</logic:equal>
<br><bean:write name="mbhNtp030Form" property="ntp030UsrName" /><a href="../mobile/mh_ntp050.do<%= jsessionId.toString() %>?ntp050DispMod=usrChange&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp050DispId=mh_ntp030"><gsmsg:write key="mobile.10" /></a>
<hr>





<% String dayColor = "#000000"; %>
<logic:equal name="mbhNtp030Form" property="ntp030StrDateKbn" value="7">
  <% dayColor = "blue"; %>
</logic:equal>
<logic:equal name="mbhNtp030Form" property="ntp030StrDateKbn" value="1">
  <% dayColor = "red"; %>
</logic:equal>

<span style="color:<%= dayColor %>;"><bean:write name="mbhNtp030Form" property="ntp030StrDate" /></span>


<br>

<logic:notEmpty name="mbhNtp030Form" property="ntp030DataModelList">

<logic:iterate id="dataMdl" name="mbhNtp030Form" property="ntp030DataModelList"  indexId="idx">

<bean:define id="chKbn" name="dataMdl" property="ntp030ChkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>

<div class="<%= chkClass %>" style="padding-top:3px;">


<bean:define id="labelDate" name="dataMdl" property="ntp030NtpDate" type="java.lang.String" />

<font size="-2" class="time_line_height" style="color:#ff0000;">
<bean:write name="dataMdl" property="ntp030DspFrHour" />:<bean:write name="dataMdl" property="ntp030DspFrMinute"/>
-
<bean:write name="dataMdl" property="ntp030DspToHour"/>:<bean:write name="dataMdl" property="ntp030DspToMinute"/>
</font>


<br>


<!-- コメントアイコン表示  -->
<%--
<logic:notEqual name="ntpMdl" property="ntp_cmtCnt" value="0">
  <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="0">
    <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str2"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="1">
    <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="2">
    <span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="#" class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></a></span>
  </logic:equal>

  <logic:equal name="ntpMdl" property="ntp_goodCnt" value="0">
    <br style="clear:both;line-height:1px;">
  </logic:equal>
</logic:notEqual>
--%>
<logic:notEmpty name="dataMdl" property="ntp030CommentList">
<bean:size id="cmtCnt" name="dataMdl" property="ntp030CommentList" />
<span id="lt" style="height:16px;width:19px" class="comment_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=3&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dataMdl" property="ntp030UsrSid" />&ntp010NipSid=<bean:write name="dataMdl" property="ntp030NtpSid" />" class="comment_icon_str2"><%= cmtCnt %></a></span>
</logic:notEmpty>

<!-- いいねアイコン表示  -->
<logic:notEqual name="dataMdl" property="ntp030GoodCnt" value="0">
<%--
  <logic:equal name="ntpMdl" property="ntp_goodkbn" value="0">
--%>
    <span id="lt" style="height:16px;width:19px;" class="good_bg"><a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=3&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dataMdl" property="ntp030UsrSid" />&ntp010NipSid=<bean:write name="dataMdl" property="ntp030NtpSid" />" class="good_icon_str2"><bean:write name="dataMdl" property="ntp030GoodCnt" /></a></span>
<%--
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_goodkbn" value="1">
    <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
  </logic:equal>
  <logic:equal name="ntpMdl" property="ntp_goodkbn" value="2">
    <span id="lt" style="height:16px;width:19px" class="good_bg"><a href="#" class="good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></a></span>
  </logic:equal>
--%>
<br style="clear:both;line-height:1px;">
</logic:notEqual>


┗
<a href="../mobile/mh_ntp040.do<%= jsessionId.toString() %>?cmd=kakunin&dspMod=3&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dataMdl" property="ntp030UsrSid" />&ntp010NipSid=<bean:write name="dataMdl" property="ntp030NtpSid" />" id="" title="<bean:write name="dataMdl" property="title" filter="false"/>" >
<!--タイトルカラー設定-->
<logic:equal name="dataMdl" property="bgcolor" value="0">
<span style="color: #0000FF;">
</logic:equal>
<logic:equal name="dataMdl" property="bgcolor" value="1">
<span style="color: #0000FF;">
</logic:equal>
<logic:equal name="dataMdl" property="bgcolor" value="2">
<span style="color: #FF0000;">
</logic:equal>
<logic:equal name="dataMdl" property="bgcolor" value="3">
<span style="color: #009900;">
</logic:equal>
<logic:equal name="dataMdl" property="bgcolor" value="4">
<span style="color: #ff9900;">
</logic:equal>
<logic:equal name="dataMdl" property="bgcolor" value="5">
<span style="color: #333333;">
</logic:equal>

<bean:write name="dataMdl" property="title" filter="false"/>
</span>
</a>


</div>


</logic:iterate>
</logic:notEmpty>

<div align="center">
  <a href="../mobile/mh_ntp030.do<%= jsessionId.toString() %>?CMD=move_ld&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp030Form" property="ntp010DspGpSid" />&changeDateFlg=1"><gsmsg:write key="cmn.previous.day" /></a>/
  <a href="../mobile/mh_ntp030.do<%= jsessionId.toString() %>?CMD=today&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp030Form" property="ntp010DspGpSid" />&changeDateFlg=0"><gsmsg:write key="cmn.today" /></a>/
  <a href="../mobile/mh_ntp030.do<%= jsessionId.toString() %>?CMD=move_rd&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp030Form" property="ntp010DspGpSid" />&changeDateFlg=1"><gsmsg:write key="cmn.nextday" /></a>&nbsp;
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