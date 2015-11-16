<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> 個人週間</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp010Form"; %>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
</head>

<% long ntpTipCnt = 0; %>
<% String chkClass = "ntp_chk"; %>
<body class="body_03" onload="setToUser();" onunload="windowClose();calWindowClose();" onkeydown="return keyPress(event.keyCode);">

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">



<html:form action="/mobile/sp_ntp010">
<html:hidden property="mobileType" value="1"/>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <img src="../mobile/sp/imgages/ntp_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="ntp.1" /><br>個人週間</h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<input type="hidden" name="mobileType" value="1">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="1">

<input type="hidden" name="ntp010SelectUsrSid" value="-1">
<input type="hidden" name="ntp010SelectUsrKbn" value="0">

<html:hidden property="cmd" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>


<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:equal name="mbhNtp010Form" property="authAddEditKbn" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTH_ADD_EDIT) %>">
  <a href="../mobile/sp_ntp040.do?mobileType=1&cmd=add&dspMod=1&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrKbn=0&ntp040DispMod=1&changeDateFlg=<bean:write name="mbhNtp010Form" property="changeDateFlg" />" data-inline="true" data-role="button" data-icon="plus"><div class="font_small"><gsmsg:write key="cmn.entry" /></div></a>
  </logic:equal>
  <a href="../mobile/sp_ntp050.do?mobileType=1&ntp050DispMod=usrChange&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />&changeDateFlg=<bean:write name="mbhNtp010Form" property="changeDateFlg" />&ntp050DispId=sp_ntp010"  data-role="button" data-icon="refresh" data-inline="true" data-iconpos="right"><div class="font_small"><gsmsg:write key="mobile.10" /></div></a>
</div>


<div class="title_1" align="center">
  <b><div class="font_small"><bean:write name="mbhNtp010Form" property="ntp010UserName" /></div></b>
</div>


<!-- 本人 -->
<logic:notEmpty name="mbhNtp010Form" property="ntp010TopList" scope="request">
<logic:iterate id="weekMdl" name="mbhNtp010Form" property="ntp010TopList" scope="request">


<!-- 日報情報 -->
<logic:notEmpty name="weekMdl" property="ntp010NtpList">
<logic:iterate id="dayMdl" name="weekMdl" property="ntp010NtpList">

  <logic:empty name="dayMdl" property="ntpDataList">
    <div align="center">
      <p class="btn_fake"><b><bean:write name="dayMdl" property="ntpDate" /></b></p>
    </div>
  </logic:empty>

  <logic:notEmpty name="dayMdl" property="ntpDataList">

   <% String dayColor = "#ffffff"; %>
   <logic:equal name="dayMdl" property="weekKbn" value="7">
     <% dayColor = "#d1d3ff"; %>
   </logic:equal>
   <logic:equal name="dayMdl" property="weekKbn" value="1">
     <% dayColor = "#ff9090"; %>
   </logic:equal>

    <div data-role="collapsible" data-collapsed=false>
      <h2>
        <div align="center">
          <span style="color:<%= dayColor %>;"><bean:write name="dayMdl" property="ntpDate" /></span>
        </div>
      </h2>
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">


  <logic:notEmpty name="dayMdl" property="ntpDataList">
  <logic:iterate id="ntpMdl" name="dayMdl" property="ntpDataList" indexId="idx2">

          <logic:equal name="mod2" value="<%= String.valueOf(idx2.intValue() % 2) %>">
          <bean:define id="liColor" value="c" />
        </logic:equal>
        <logic:notEqual name="mod2" value="<%= String.valueOf(idx2.intValue() % 2) %>">
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

    <logic:empty name="ntpMdl" property="detail">
  <a href="../mobile/sp_ntp040.do?cmd=kakunin&mobileType=1&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />">
  </logic:empty>

  <logic:notEmpty name="ntpMdl" property="detail">
  <bean:define id="ntdetailu" name="ntpMdl" property="detail" />
  <%
  String tmpText = (String)pageContext.getAttribute("ntdetailu",PageContext.PAGE_SCOPE);
  String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
  %>
  <a href="../mobile/sp_ntp040.do?cmd=kakunin&mobileType=1&dspMod=1&CMD=edit&ntp010SelectDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="dayMdl" property="usrSid" />&ntp010NipSid=<bean:write name="ntpMdl" property="ntpSid" />"  id="ntpsid<%= tipskey %>">
  </logic:notEmpty>

  <div  class="<%= chkClass %>" style="padding-top:3px;">

  <logic:notEmpty name="ntpMdl" property="time">
  <font size="-2" class="time_line_height" style="color:#ff0000;"><bean:write name="ntpMdl" property="time" /></font>
  </logic:notEmpty>

  <!-- コメントアイコン表示  -->
  <logic:notEqual name="ntpMdl" property="ntp_cmtCnt" value="0">
    <br>
    <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="0">
      <div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg"><span class="comment_icon_str2"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></span></div>
    </logic:equal>
    <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="1">
      <div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg"><span class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></span></div>
    </logic:equal>
    <logic:equal name="ntpMdl" property="ntp_cmtkbn" value="2">
      <div id="lt" style="height:16px;width:19px;float:left;" class="comment_bg"><span class="comment_icon_str"><bean:write name="ntpMdl" property="ntp_cmtCnt" /></span></div>
    </logic:equal>

    <logic:equal name="ntpMdl" property="ntp_goodCnt" value="0">

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
  </logic:notEmpty>

  </logic:iterate>
  </logic:notEmpty>




</logic:iterate>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
<a href="../mobile/sp_ntp010.do?mobileType=1&CMD=move_ld&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.previous.day" /></div></a>
<a href="../mobile/sp_ntp010.do?mobileType=1&CMD=today&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=0&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.today" /></div></a>
<a href="../mobile/sp_ntp010.do?mobileType=1&CMD=move_rd&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="cmn.nextday" /></div></a>
</div>

<div data-role="controlgroup" data-type="horizontal" align="center">
<a href="../mobile/sp_ntp010.do?mobileType=1&CMD=move_lw&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="mobile.24" /></div></a>
<a href="../mobile/sp_ntp010.do?mobileType=1&CMD=move_rw&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&ntp010SelectUsrSid=<bean:write name="mbhNtp010Form" property="ntp010SelectUsrSid" />&ntp010DspDate=<bean:write name="mbhNtp010Form" property="ntp010DspDate" />&changeDateFlg=1&ntp010DspGpSid=<bean:write name="mbhNtp010Form" property="ntp010DspGpSid" />" data-inline="true" data-role="button"><div class="font_small"><gsmsg:write key="mobile.25" /></div></a>
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