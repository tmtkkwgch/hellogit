<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>
<%@page import="jp.groupsession.v2.cmn.GSConstSchedule"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSch010Form"; %>
<%-- 定数値 --%>
<%
  int editConfOwn          = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_OWN;
  int editConfGroup        = jp.groupsession.v2.cmn.GSConstSchedule.EDIT_CONF_GROUP;
  int dspPublic            = jp.groupsession.v2.cmn.GSConstSchedule.DSP_PUBLIC;
  int dspNotPublic         = jp.groupsession.v2.cmn.GSConstSchedule.DSP_NOT_PUBLIC;
  int dspYoteiari          = jp.groupsession.v2.cmn.GSConstSchedule.DSP_YOTEIARI;
  int dspBelongGroup       = jp.groupsession.v2.cmn.GSConstSchedule.DSP_BELONG_GROUP;
%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="schedule.108" /><gsmsg:write key="schedule.19" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_sch020">

<b><%= emojiTokei.toString() %><gsmsg:write key="schedule.108" /><br><gsmsg:write key="schedule.19" /></b>
<hr>
  <logic:equal name="mbhSch010Form" property="registFlg" value="<%= String.valueOf(GSConstSchedule.SSP_AUTH_EDIT) %>">
   ●<a href="../mobile/mh_sch040.do<%= jsessionId.toString() %>?cmd=add&dspMod=1&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010SelectDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&sch040DispMod=1&changeDateFlg=<bean:write name="mbhSch010Form" property="changeDateFlg" />" accesskey="1"><gsmsg:write key="cmn.entry" /></a><br>
  </logic:equal>
<bean:write name="mbhSch010Form" property="sch010UserName" /><a href="../mobile/mh_sch050.do<%= jsessionId.toString() %>?sch050DispMod=usrChange&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />&changeDateFlg=<bean:write name="mbhSch010Form" property="changeDateFlg" />&sch050DispId=mh_sch010"><gsmsg:write key="mobile.10" /></a>

<hr>
  <!-- グループ,本人 -->
  <logic:notEmpty name="mbhSch010Form" property="sch010TopList" scope="request">
  <logic:iterate id="weekMdl" name="mbhSch010Form" property="sch010TopList" scope="request">
  <bean:define id="usrMdl" name="weekMdl" property="sch010UsrMdl"/>


  <!-- 本人 -->
  <logic:notEqual name="usrMdl" property="usrKbn" value="1">
  <!-- スケジュール情報 -->
  <logic:notEmpty name="weekMdl" property="sch010SchList">
  <logic:iterate id="dayMdl" name="weekMdl" property="sch010SchList">

    <% String dayColor = "#000000"; %>
    <logic:equal name="dayMdl" property="weekKbn" value="7">
      <% dayColor = "blue"; %>
    </logic:equal>
    <logic:equal name="dayMdl" property="weekKbn" value="1">
      <% dayColor = "red"; %>
    </logic:equal>

    <span style="color:<%= dayColor %>;"><bean:write name="dayMdl" property="schDate" /></span>

    <logic:notEmpty name="dayMdl" property="schDataList">
    <logic:iterate id="schMdl" name="dayMdl" property="schDataList">

      <bean:define id="u_usrsid" name="dayMdl" property="usrSid" type="java.lang.Integer" />
      <bean:define id="u_schsid" name="schMdl" property="schSid" type="java.lang.Integer" />
      <bean:define id="u_date" name="dayMdl" property="schDate"  type="java.lang.String" />
      <bean:define id="u_public" name="schMdl" property="public"  type="java.lang.Integer" />
      <bean:define id="u_kjnEdKbn" name="schMdl" property="kjnEdKbn"  type="java.lang.Integer" />
      <bean:define id="u_grpEdKbn" name="schMdl" property="grpEdKbn"  type="java.lang.Integer" />

      <%
        String tipskey1 = ((Integer)pageContext.getAttribute("u_usrsid",PageContext.PAGE_SCOPE)).toString();
        String tipskey2 = ((Integer)pageContext.getAttribute("u_schsid",PageContext.PAGE_SCOPE)).toString();
        String tipskey3 = ((String)pageContext.getAttribute("u_date",PageContext.PAGE_SCOPE));
        String tipskey = tipskey1 + '_' + tipskey2 + '_' + tipskey3;
        int publicType = ((Integer)pageContext.getAttribute("u_public",PageContext.PAGE_SCOPE));
        int kjnEdKbn = ((Integer)pageContext.getAttribute("u_kjnEdKbn",PageContext.PAGE_SCOPE));
        int grpEdKbn = ((Integer)pageContext.getAttribute("u_grpEdKbn",PageContext.PAGE_SCOPE));
      %>

      <!--公開-->
      <%
      if ((publicType == dspPublic) || (kjnEdKbn == editConfOwn || grpEdKbn == editConfGroup)) {
      %>
      <br>
      <logic:notEmpty name="schMdl" property="title">

      <logic:notEmpty name="schMdl" property="time">
      <font size="-2" color="#ff0000"><%= emojiTokei.toString() %><bean:write name="schMdl" property="time" /></font><br>
      </logic:notEmpty>
           ┗<a href="../mobile/mh_sch040.do<%= jsessionId.toString() %>?cmd=edit&sch010SchSid=<bean:write name="schMdl" property="schSid" />&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&sch010SelectUsrKbn=0&dspMod=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />&changeDateFlg=<bean:write name="mbhSch010Form" property="changeDateFlg" />">
      <!--タイトルカラー設定-->
      <logic:equal name="schMdl" property="bgColor" value="0">
      <span style="color: #0000FF;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="1">
      <span style="color: #0000FF;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="2">
      <span style="color: #FF0000;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="3">
      <span style="color: #009900;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="4">
      <span style="color: #ff9900;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="5">
      <span style="color: #333333;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="6">
      <span style="color: #000080;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="7">
      <span style="color: #800000;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="8">
      <span style="color: #008080;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="9">
      <span style="color: #808080;">
      </logic:equal>
      <logic:equal name="schMdl" property="bgColor" value="10">
      <span style="color: #008DCB;">
      </logic:equal>
      <bean:write name="schMdl" property="title" />
      </span>
      </a>

      </logic:notEmpty>

      <%
       } else {
      %>

      <!--非公開-->

      <logic:notEmpty name="schMdl" property="title">
        <logic:notEmpty name="schMdl" property="time">
        <br>
        <font size="-2" color="#000000"><%= emojiTokei.toString() %><bean:write name="schMdl" property="time" /></font>
        </logic:notEmpty>
        <br>┗<font size="-2" color="#000000"><bean:write name="schMdl" property="title" /></font>
      </logic:notEmpty>

      <%
       }
      %>

    </logic:iterate>
    </logic:notEmpty>

  <br><br>

  </logic:iterate>
  </logic:notEmpty>

  </logic:notEqual>
  </logic:iterate>
  </logic:notEmpty>

<hr>
<div align="center">
<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>?CMD=move_lw&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />"><gsmsg:write key="mobile.24" /></a>/<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>?CMD=move_ld&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />"><gsmsg:write key="cmn.previous.day" /></a>/<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>?CMD=today&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=0&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />"><gsmsg:write key="cmn.today" /></a>/<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>?CMD=move_rd&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />"><gsmsg:write key="cmn.nextday" /></a>/<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>?CMD=move_rw&sch010SelectUsrSid=<bean:write name="mbhSch010Form" property="sch010SelectUsrSid" />&sch010DspDate=<bean:write name="mbhSch010Form" property="sch010DspDate" />&changeDateFlg=1&sch010DspGpSid=<bean:write name="mbhSch010Form" property="sch010DspGpSid" />"><gsmsg:write key="mobile.25" /></a>&nbsp;
</div>

<hr>
<a href="../mobile/mh_sch030.do<%= jsessionId.toString() %>"><gsmsg:write key="mobile.18" /></a>
<br>
<a href="../mobile/mh_sch010.do<%= jsessionId.toString() %>"><gsmsg:write key="schedule.19" /></a>
<br>
<a href="../mobile/mh_sch020.do<%= jsessionId.toString() %>"><gsmsg:write key="mobile.19" /></a>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>