<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> グループ<gsmsg:write key="cmn.days2" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp020Form"; %>

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
</head>

<% long ntpTipCnt = 0; %>
<% String chkClass = "ntp_chk"; %>
<body class="body_03" onload="setToUser();" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_ntp020">
<input type="hidden" name="mobileType" value="1">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="1">

<input type="hidden" name="ntp010SelectUsrSid" value="-1">
<input type="hidden" name="ntp010SelectUsrKbn" value="0">

<html:hidden property="cmd" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp020SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/ntp_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="ntp.1" /><br>グループ<gsmsg:write key="cmn.days2" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="content">


<br>
<html:select property="ntp010DspGpSid" styleClass="select01" onchange="changeGroupCombo();">
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

<% String dayColor = "#000000"; %>
<logic:equal name="mbhNtp020Form" property="ntp020StrDspDateKbn" value="7">
  <% dayColor = "blue"; %>
</logic:equal>
<logic:equal name="mbhNtp020Form" property="ntp020StrDspDateKbn" value="1">
  <% dayColor = "red"; %>
</logic:equal>


<div class="title_1" align="center">
  <b><div class="font_small"><span style="color:<%= dayColor %>;"><bean:write name="mbhNtp020Form" property="ntp020StrDspDate" /></span></div></b>
</div>

<!-- 本人 -->
<logic:notEmpty name="mbhNtp020Form" property="ntp020TopList" scope="request">
<logic:iterate id="weekMdl" name="mbhNtp020Form" property="ntp020TopList" scope="request">

<bean:define id="usrMdl" name="weekMdl" property="ntp010UsrMdl"/>




    <div data-role="collapsible" data-collapsed="false">
      <h2>
        <div align="center">
          <bean:write name="usrMdl" property="usrName" />
        </div>
      </h2>

      <div align="center" class="font_xsmall">
        <a href="../mobile/sp_ntp040.do?mobileType=1&cmd=add&dspMod=2&ntp010SelectUsrSid=<bean:write name="usrMdl" property="usrSid" />&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp020Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.entry" /></div></a>
      </div>



<!-- 日報情報 -->
<logic:notEmpty name="weekMdl" property="ntp010NtpList">
<logic:iterate id="dayMdl" name="weekMdl" property="ntp010NtpList">


<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">


<logic:notEmpty name="dayMdl" property="ntpDataList">
<logic:iterate id="ntpMdl" name="dayMdl" property="ntpDataList" indexId="idx2">

<logic:equal name="mod" value="<%= String.valueOf(idx2.intValue() % 2) %>">
  <bean:define id="liColor" value="c" />
</logic:equal>
<logic:notEqual name="mod" value="<%= String.valueOf(idx2.intValue() % 2) %>">
  <bean:define id="liColor" value="d" />
</logic:notEqual>

<li data-theme=<bean:write name="liColor" />>

<bean:define id="chKbn" name="ntpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>



<bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
<bean:define id="u_ntpsid" name="ntpMdl" property="ntpSid" type="java.lang.Integer" />
<bean:define id="u_date" name="dayMdl" property="ntpDate"  type="java.lang.String" />

<%
String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
String tipskey2 = ((Integer)pageContext.getAttribute("u_ntpsid",PageContext.PAGE_SCOPE)).toString();
String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
%>


<a href="../mobile/sp_ntp040.do?mobileType=1&cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />" id="ntpsid<%= tipskey %>">

<div  class="<%= chkClass %>" style="padding-top:3px;">


<logic:notEmpty name="ntpMdl" property="time">
<font size="-2" class="time_line_height" style="color:#ff0000;"><bean:write name="ntpMdl" property="time" /></font>
</logic:notEmpty>




<!-- コメントアイコン表示  -->
<logic:notEqual name="ntpMdl" property="ntp_cmtCnt" value="0">
<br>
<logic:equal name="ntpMdl" property="ntp_cmtkbn" value="0">
<div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str2"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></div>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_cmtkbn" value="1">
<div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></div>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_cmtkbn" value="2">
<div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></div>
</logic:equal>

</logic:notEqual>


<!-- いいねアイコン表示  -->
<logic:notEqual name="ntpMdl" property="ntp_goodCnt" value="0">
<logic:equal name="ntpMdl" property="ntp_cmtCnt" value="0">
<br>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_goodkbn" value="0">
<div id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str2"><bean:write name="ntpMdl" property="ntp_goodCnt" /></div>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_goodkbn" value="1">
<div id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></div>
</logic:equal>
<logic:equal name="ntpMdl" property="ntp_goodkbn" value="2">
<div id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str"><bean:write name="ntpMdl" property="ntp_goodCnt" /></div>
</logic:equal>



</logic:notEqual>

<br style="clear:both;">


<!--タイトルカラー設定-->
<logic:equal name="ntpMdl" property="titleColor" value="0">
<span style="color: #0000FF;">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="1">
<span style="color: #0000FF;">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="2">
<span style="color: #FF0000;">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="3">
<span style="color: #009900;">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="4">
<span style="color: #ff9900;">
</logic:equal>
<logic:equal name="ntpMdl" property="titleColor" value="5">
<span style="color: #333333;">
</logic:equal>


<bean:write name="ntpMdl" property="title" />
</span>

</div>

</a>



</li>

</logic:iterate>
</logic:notEmpty>

</ul>

</div>

</logic:iterate>
</logic:notEmpty>

</logic:iterate>
</logic:notEmpty>

<br>

<!-- グループメンバー -->
<logic:notEmpty name="mbhNtp020Form" property="ntp020BottomList" scope="request">
<logic:iterate id="gpWeekMdl" name="mbhNtp020Form" property="ntp020BottomList" scope="request" indexId="cnt">
<bean:define id="usrMdl" name="gpWeekMdl" property="ntp010UsrMdl"/>

    <div data-role="collapsible" data-collapsed="false">
      <h2>
        <div align="center">
          <bean:write name="usrMdl" property="usrName" />
        </div>
      </h2>

      <logic:equal name="mbhNtp020Form" property="adminKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">
      <div align="center" class="font_xsmall">
        <a href="../mobile/sp_ntp040.do?mobileType=1&cmd=add&dspMod=2&ntp010SelectUsrSid=<bean:write name="usrMdl" property="usrSid" />&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp020Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.entry" /></div></a>
      </div>
      </logic:equal>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

<!-- 日報情報 -->
<logic:notEmpty name="gpWeekMdl" property="ntp010NtpList">
<logic:iterate id="gpDayMdl" name="gpWeekMdl" property="ntp010NtpList">



<logic:notEmpty name="gpDayMdl" property="ntpDataList">

<logic:iterate id="gpNtpMdl" name="gpDayMdl" property="ntpDataList" indexId="idx3">

<logic:equal name="mod" value="<%= String.valueOf(idx3.intValue() % 2) %>">
  <bean:define id="liColor" value="c" />
</logic:equal>
<logic:notEqual name="mod" value="<%= String.valueOf(idx3.intValue() % 2) %>">
  <bean:define id="liColor" value="d" />
</logic:notEqual>

<li data-theme=<bean:write name="liColor" />>


<a href="../mobile/sp_ntp040.do?mobileType=1&cmd=kakunin&dspMod=2&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="gpDayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="gpNtpMdl" property="ntpSid" />" >

<bean:define id="chkKbn" name="gpNtpMdl" property="ntp_chkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chkKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>

<div  class="<%= chkClass %>" style="padding-top:3px;">


<logic:notEmpty name="gpNtpMdl" property="time">
<font size="-2" class="time_line_height" style="color:#ff0000;"><bean:write name="gpNtpMdl" property="time" /></font><br>
</logic:notEmpty>




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
<span id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str2"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="1">
<span id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_cmtkbn" value="2">
<span id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str"><bean:write name="gpNtpMdl" property="ntp_cmtCnt" /></span>
</logic:equal>

<logic:equal name="gpNtpMdl" property="ntp_goodCnt" value="0">
<br style="clear:both;line-height:1px;">
</logic:equal>
</logic:notEqual>


<!-- いいねアイコン表示  -->
<logic:notEqual name="gpNtpMdl" property="ntp_goodCnt" value="0">
<logic:equal name="gpNtpMdl" property="ntp_cmtCnt" value="0">

</logic:equal>

<logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="0">
<span id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str2"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="1">
<span id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></span>
</logic:equal>
<logic:equal name="gpNtpMdl" property="ntp_goodkbn" value="2">
<span id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str"><bean:write name="gpNtpMdl" property="ntp_goodCnt" /></span>
</logic:equal>

<br style="clear:both;line-height:1px;">

</logic:notEqual>

<!--タイトルカラー設定-->
<logic:equal name="gpNtpMdl" property="titleColor" value="0">
<span style="color: #0000FF;">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="1">
<span style="color: #0000FF;">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="2">
<span style="color: #FF0000;">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="3">
<span style="color: #009900;">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="4">
<span style="color: #ff9900;">
</logic:equal>
<logic:equal name="gpNtpMdl" property="titleColor" value="5">
<span style="color: #333333;">
</logic:equal>

<bean:write name="gpNtpMdl" property="title" />
</span>

</div>

</a>

</li>

</logic:iterate>
</logic:notEmpty>



</logic:iterate>
</logic:notEmpty>

</ul>
</div>

</logic:iterate>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <a href="../mobile/sp_ntp020.do?mobileType=1&CMD=move_ld&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp020Form" property="ntp010DspGpSid" />&changeDateFlg=1"  data-inline="true" data-role="button"><gsmsg:write key="cmn.previous.day" /></a>
  <a href="../mobile/sp_ntp020.do?mobileType=1&CMD=today&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp020Form" property="ntp010DspGpSid" />&changeDateFlg=0"  data-inline="true" data-role="button"><gsmsg:write key="cmn.today" /></a>
  <a href="../mobile/sp_ntp020.do?mobileType=1&CMD=move_rd&ntp010DspDate=<bean:write name="mbhNtp020Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp020Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp020Form" property="ntp010DspGpSid" />&changeDateFlg=1"  data-inline="true" data-role="button"><gsmsg:write key="cmn.nextday" /></a>
</div>

<br>

<ul data-role="listview" data-theme="d" data-dividertheme="c">
  <li><a href="../mobile/sp_ntp030.do?mobileType=1"><gsmsg:write key="mobile.18" /></a></li>
  <li><a href="../mobile/sp_ntp010.do?mobileType=1"><gsmsg:write key="schedule.19" /></a></li>
  <li><a href="../mobile/sp_ntp020.do?mobileType=1"><gsmsg:write key="mobile.19" /></a></li>
</ul>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->
</body>
</html:html>