<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" />(個人日間)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp030Form"; %>
<% String chkClass = "ntp_chk"; %>

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../nippou/js/ntp.js?<%= GSConst.VERSION_PARAM %>'></script>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
</head>

<body class="body_03">
<html:form action="/mobile/sp_ntp030">

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">
<input type="hidden" name="mobileType" value="1">
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

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/ntp_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="ntp.1" /><br><gsmsg:write key="mobile.18" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->


<div data-role="content">


<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:equal name="mbhNtp030Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">
  <a href="../mobile/sp_ntp040.do?mobileType=1&cmd=add&dspMod=3&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp030Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><gsmsg:write key="cmn.entry" /></a>
  </logic:equal>
  <a href="../mobile/sp_ntp050.do?mobileType=1&ntp050DispMod=usrChange&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp050DispId=sp_ntp030" data-role="button" data-icon="refresh" data-inline="true" data-iconpos="right"><gsmsg:write key="mobile.10" /></a>
</div>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">



  <li data-role="list-divider" style="background:#ffffff;">
    <div align="center">
      <b><bean:write name="mbhNtp030Form" property="ntp030UsrName" /></b>
      <hr>
      <% String dayColor = "#000000"; %>
      <logic:equal name="mbhNtp030Form" property="ntp030StrDateKbn" value="7">
        <% dayColor = "blue"; %>
      </logic:equal>
      <logic:equal name="mbhNtp030Form" property="ntp030StrDateKbn" value="1">
        <% dayColor = "red"; %>
      </logic:equal>
      <span style="color:<%= dayColor %>;"><bean:write name="mbhNtp030Form" property="ntp030StrDate" /></span>
    </div>
  </li>


<logic:notEmpty name="mbhNtp030Form" property="ntp030DataModelList">

<logic:iterate id="dataMdl" name="mbhNtp030Form" property="ntp030DataModelList"  indexId="idx">

<bean:define id="chKbn" name="dataMdl" property="ntp030ChkKbn" type="java.lang.Integer" />

<% chkClass = "ntp_chk"; %>
<% if (chKbn == 1) { %>
<%    chkClass = ""; %>
<% } %>

    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

    <li data-theme=<bean:write name="liColor" />>

<a href="../mobile/sp_ntp040.do?mobileType=1&cmd=kakunin&dspMod=3&CMD=edit&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dataMdl" property="ntp030UsrSid" />&ntp010NipSid=<bean:write name="dataMdl" property="ntp030NtpSid" />">

<div class="<%= chkClass %>" style="padding-top:3px;">

<bean:define id="labelDate" name="dataMdl" property="ntp030NtpDate" type="java.lang.String" />

<font size="-2" class="time_line_height" style="color:#ff0000;">
<bean:write name="dataMdl" property="ntp030DspFrHour" />:<bean:write name="dataMdl" property="ntp030DspFrMinute"/>
-
<bean:write name="dataMdl" property="ntp030DspToHour"/>:<bean:write name="dataMdl" property="ntp030DspToMinute"/>
</font>

<!-- コメントアイコン表示  -->
<logic:notEmpty name="dataMdl" property="ntp030CommentList">
<br>
<bean:size id="cmtCnt" name="dataMdl" property="ntp030CommentList" />
<div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg comment_icon_str2"><%= cmtCnt %></div>
</logic:notEmpty>

<!-- いいねアイコン表示  -->
<logic:notEqual name="dataMdl" property="ntp030GoodCnt" value="0">

<logic:empty name="dataMdl" property="ntp030CommentList">
<br>
</logic:empty>
<div id="lt" style="height:16px;width:19px;float:left;" class="good_bg good_icon_str2"><bean:write name="dataMdl" property="ntp030GoodCnt" /></div>
</logic:notEqual>

<br style="clear:both;">


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

<bean:write name="dataMdl" property="title" filter="false" />


</span>

</div>

</a>

</li>

</logic:iterate>
</logic:notEmpty>

</ul>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <a href="../mobile/sp_ntp030.do?mobileType=1&CMD=move_ld&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp030Form" property="ntp010DspGpSid" />&changeDateFlg=1"  data-inline="true" data-role="button"><gsmsg:write key="cmn.previous.day" /></a>
  <a href="../mobile/sp_ntp030.do?mobileType=1&CMD=today&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp030Form" property="ntp010DspGpSid" />&changeDateFlg=0"  data-inline="true" data-role="button"><gsmsg:write key="cmn.today" /></a>
  <a href="../mobile/sp_ntp030.do?mobileType=1&CMD=move_rd&ntp010DspDate=<bean:write name="mbhNtp030Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp030Form" property="ntp010SelectUsrSid" />&ntp010DspGpSid=<bean:write name="mbhNtp030Form" property="ntp010DspGpSid" />&changeDateFlg=1"  data-inline="true" data-role="button"><gsmsg:write key="cmn.nextday" /></a>
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