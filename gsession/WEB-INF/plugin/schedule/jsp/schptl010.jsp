<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.schmain.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<!--  script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>  -->

<script type="text/javascript">
  $('#tooltips_sch<bean:write name="schptl010Form" property="schDspGrpSid" /> * a').tooltip();
</script>

<bean:define id="grpSid" name="schptl010Form" property="schDspGrpSid" type="java.lang.Integer" />
<% String schFormId = "schptl010Form" + String.valueOf(grpSid.intValue()); %>

</head>

<body class="body_03">
<html:form action="/schedule/schptl010" styleId="<%= schFormId %>">
<logic:equal name="schptl010Form" property="schPtlDspFlg" value="0">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="dspMod" value="4">
<html:hidden property="schWeekDate" />
<html:hidden property="schSelectDate" />
<html:hidden property="schSelectUsrSid" />
<html:hidden property="schSelectSchSid" />
<html:hidden property="schDspGrpSid" />
<html:hidden property="schPtl010ItemId" />

<div id="tooltips_sch<bean:write name="schptl010Form" property="schDspGrpSid" />">
    <!--スケジュール-->
    <bean:define id="schWeekMdl" name="schptl010Form" property="schWeekMdl"/>
    <table cellpadding="0" cellspacing="0" width="100%" class="tl0">
    <tr>
    <td align="left" class="header_7D91BD_left" colspan="7">
      <table cellpadding="0" cellspacing="0" width="100%" class="tl0">
      <tr>
      <td width="0%" align="left" nowrap><img src="../schedule/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="schedule.108" />"><a href="javascript:void(0);"  onClick="return moveWeekScheduleFromPtl('week', '<%= schFormId %>');"><bean:write name="schptl010Form" property="schPtl010GrpName" /></a></td>
      <td width="100%" align="right" valign="bottom">
        <table cellpadding="0" cellspacing="0">
        <tr>
        <td  valign="top">
          <input type="button" value="<gsmsg:write key="cmn.between.mon" />" class="btn_month" onClick="return moveMonthScheduleFromPtl('month', '<%= schFormId %>');">&nbsp;
        </td>
        <td>
          <input type="button" class="btn_arrow1_l" value="&nbsp;" onclick="updateSchedulePtl('schw_move_lw', '<%= schFormId %>');">
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="updateSchedulePtl('schw_move_ld', '<%= schFormId %>');">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="updateSchedulePtl('schw_today', '<%= schFormId %>');">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="updateSchedulePtl('schw_move_rd', '<%= schFormId %>');">
          <input type="button" class="btn_arrow1_r" value="&nbsp;" onclick="updateSchedulePtl('schw_move_rw', '<%= schFormId %>');">&nbsp;
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- 日付表示 -->
    <tr>
    <logic:notEmpty name="schWeekMdl" property="schWeekCalendarList" scope="page">
    <logic:iterate id="calBean" name="schWeekMdl" property="schWeekCalendarList" scope="page">

    <bean:define id="tdColor" value="" />
    <bean:define id="fontColorSun" value="" />
    <bean:define id="fontColorSat" value="" />
    <bean:define id="fontColorDef" value="" />
    <% String[] tdColors = new String[] {"td_calnot_today", "td_cal_today"}; %>
    <% String[] fontColorsSun = new String[] {"sc_ttl_sun", "sc_ttl_sun"}; %>
    <% String[] fontColorsSat = new String[] {"sc_ttl_sat", "sc_ttl_sat"}; %>
    <% String[] fontColorsDef = new String[] {"sc_ttl_def", "sc_ttl_def_today"}; %>

    <logic:equal name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[1]; %>
    <% fontColorSun = fontColorsSun[1]; %>
    <% fontColorSat = fontColorsSat[1]; %>
    <% fontColorDef = fontColorsDef[1]; %>
    </logic:equal>
    <logic:notEqual name="calBean" property="todayKbn" value="1">
    <% tdColor = tdColors[0]; %>
    <% fontColorSun = fontColorsSun[0]; %>
    <% fontColorSat = fontColorsSat[0]; %>
    <% fontColorDef = fontColorsDef[0]; %>
    </logic:notEqual>

    <th width="12%" nowrap class="<%= tdColor %>">
    <a href="javascript:void(0);" title="<bean:write name="calBean" property="dspDayString" />" onClick="return moveDailyScheduleFromPtl('day', <bean:write name="calBean" property="dspDate" />, '<%= schFormId %>');">
    <logic:equal name="calBean" property="holidayKbn" value="1">
    <span class="<%= fontColorSun %>">
    </logic:equal>
    <logic:notEqual name="calBean" property="holidayKbn" value="1">
    <logic:equal name="calBean" property="weekKbn" value="1">
    <span class="<%= fontColorSun %>">
    </logic:equal>
    <logic:equal name="calBean" property="weekKbn" value="7">
    <span class="<%= fontColorSat %>">
    </logic:equal>
    <logic:notEqual name="calBean" property="weekKbn" value="1">
    <logic:notEqual name="calBean" property="weekKbn" value="7">
    <span class="<%= fontColorDef %>">
    </logic:notEqual>
    </logic:notEqual>
    </logic:notEqual>

    <bean:write name="calBean" property="dspDayString" /></span></a></th>
    </logic:iterate>
    </logic:notEmpty>
    </tr>

    <logic:iterate id="weekBean" name="schWeekMdl" property="schWeekTopList" scope="page" indexId="schIdx1" offset="0">
    <logic:equal name="schIdx1" value="0">
       <!-- スケジュール情報(グループ予定) -->
       <bean:define id="grpWeekBean" name="weekBean"/>
    </logic:equal>
    <logic:equal name="schIdx1" value="1">
       <!-- スケジュール情報(個人予定) -->
       <bean:define id="usrWeekBean" name="weekBean"/>
    </logic:equal>
    </logic:iterate>

    <tr align="left" valign="top">
    <logic:notEmpty name="grpWeekBean" property="sch010SchList">
    <logic:iterate id="dayMdl" name="grpWeekBean" property="sch010SchList" indexId="schIdx2">
    <bean:define id="offsetIdx" value="" />
    <% offsetIdx = schIdx2.toString(); %>
    <logic:iterate id="gdayMdl" name="grpWeekBean" property="sch010SchList" offset="<%= offsetIdx %>" length="1">

    <logic:equal name="gdayMdl" property="weekKbn" value="1">
    <logic:notEqual name="gdayMdl" property="todayKbn" value="1">
      <td align="left" valign="top" class="td_type9">
    </logic:notEqual>
    <logic:equal name="gdayMdl" property="todayKbn" value="1">
      <td align="left" valign="top" class="td_cal_today">
    </logic:equal>
    </logic:equal>

    <logic:equal name="gdayMdl" property="weekKbn" value="7">
    <logic:notEqual name="gdayMdl" property="todayKbn" value="1">
      <td align="left" valign="top" class="td_type8">
    </logic:notEqual>
    <logic:equal name="gdayMdl" property="todayKbn" value="1">
      <td align="left" valign="top" class="td_cal_today">
    </logic:equal>
    </logic:equal>

    <logic:notEqual name="gdayMdl" property="weekKbn" value="1">
    <logic:notEqual name="gdayMdl" property="weekKbn" value="7">

    <logic:notEqual name="gdayMdl" property="todayKbn" value="1">
      <td align="left" valign="top" class="td_type1">
    </logic:notEqual>
    <logic:equal name="gdayMdl" property="todayKbn" value="1">
      <td align="left" valign="top" class="td_cal_today">
    </logic:equal>

    </logic:notEqual>
    </logic:notEqual>

    <logic:equal name="schIdx2" value="0">
    <span id="lt"><img src="../schedule/images/damy.gif" width="0" height="50" alt="<gsmsg:write key="cmn.space" />"></span>
    </logic:equal>

    <logic:equal name="schptl010Form" property="schRegistFlg" value="true">
    <!-- スケジュール追加ボタン -->
    <span id="lt"><a href="javascript:void(0);" onClick="return addSchedulePtl('schw_add', <bean:write name="gdayMdl" property="schDate" />, '<%= schFormId %>');"><img src="../schedule/images/scadd.gif" alt="<gsmsg:write key="cmn.add" />" width="16" height="16" border="0" ></a></span>
    </logic:equal>

      <logic:notEmpty name="gdayMdl" property="holidayName">
      <span id="rt"><font size="-2" color="#ff0000"><bean:write name="gdayMdl" property="holidayName" /></font></span>
      </logic:notEmpty>

      <logic:notEmpty name="gdayMdl" property="schDataList">
      <logic:iterate id="gschMdl" name="gdayMdl" property="schDataList">

        <bean:define id="u_usrsid" name="gdayMdl" property="usrSid" type="java.lang.Integer" />
        <bean:define id="u_schsid" name="gschMdl" property="schSid" type="java.lang.Integer" />
        <bean:define id="u_date" name="gdayMdl" property="schDate"  type="java.lang.String" />

        <br>

        <logic:empty name="gschMdl" property="valueStr">
        <a href="javascript:void(0);" title="" onClick="return editSchedulePtl('schw_edit', <bean:write name="gdayMdl" property="schDate" />, <bean:write name="gschMdl" property="schSid" />, '<%= schFormId %>');">
        <span class="tooltips"><bean:write name="gschMdl" property="title" /></span>
        </logic:empty>

        <logic:notEmpty name="gschMdl" property="valueStr">
        <bean:define id="gscnaiyou" name="gschMdl" property="valueStr" />
        <%
          String tmpText = (String)pageContext.getAttribute("gscnaiyou",PageContext.PAGE_SCOPE);
          String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
        %>
        <a href="javascript:void(0);" onClick="return editSchedulePtl('schw_edit', <bean:write name="gdayMdl" property="schDate" />, <bean:write name="gschMdl" property="schSid" />, '<%= schFormId %>');" title="">
        <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
        </logic:notEmpty>


        <logic:notEmpty name="gschMdl" property="title">
        <span class="sc_link_g">G</span>
        <!--タイトルカラー設定-->
        <logic:equal name="gschMdl" property="bgColor" value="0">
        <span class="sc_link_1">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="1">
        <span class="sc_link_1">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="2">
        <span class="sc_link_2">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="3">
        <span class="sc_link_3">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="4">
        <span class="sc_link_4">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="5">
        <span class="sc_link_5">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="6">
        <span class="sc_link_6">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="7">
        <span class="sc_link_7">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="8">
        <span class="sc_link_8">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="9">
        <span class="sc_link_9">
        </logic:equal>
        <logic:equal name="gschMdl" property="bgColor" value="10">
        <span class="sc_link_10">
        </logic:equal>

        <logic:notEmpty name="gschMdl" property="time">
        <font size="-2"><bean:write name="gschMdl" property="time" /><br></font>
        </logic:notEmpty>
        <bean:write name="gschMdl" property="title" />
        </span>
        </a>
        </logic:notEmpty>

      </logic:iterate>
      </logic:notEmpty>
      </td>

    </logic:iterate>
    </logic:iterate>
    </logic:notEmpty>
    </tr>



    <!--時間指定無しスケジュール-->
    <logic:notEmpty name="grpWeekBean" property="sch010NoTimeSchList">
      <bean:define id="noTimeList" name="grpWeekBean" property="sch010NoTimeSchList" type="java.util.ArrayList"/>
      <% int rowSize = noTimeList.size(); %>
      <logic:iterate id="periodList" name="grpWeekBean" property="sch010NoTimeSchList" indexId="rowIdx">
        <logic:notEmpty name="periodList">

          <% if ((Integer.valueOf(rowIdx) + 1) == (Integer.valueOf(rowSize))) { %>
          <tr class="td_sch_type7">
          <% } else { %>
          <tr class="td_sch_type6">
          <% } %>


            <bean:define id="prList" name="periodList" type="java.util.ArrayList"/>
            <% int size = prList.size(); %>

            <logic:iterate id="uPeriodMdl" name="periodList" indexId="pIdx">

              <logic:notEmpty name="uPeriodMdl" property="periodMdl">
                <bean:define id="pMdl" name="uPeriodMdl" property="periodMdl"/>


                <td class="td_type1 td_type_kikan" colspan="<bean:write name="pMdl" property="schPeriodCnt" />">

                    <bean:define id="p_schsid" name="uPeriodMdl" property="schSid" type="java.lang.Integer" />
                    <bean:define id="p_public" name="uPeriodMdl" property="public"  type="java.lang.Integer" />
                    <bean:define id="p_kjnEdKbn" name="uPeriodMdl" property="kjnEdKbn"  type="java.lang.Integer" />
                    <bean:define id="p_grpEdKbn" name="uPeriodMdl" property="grpEdKbn"  type="java.lang.Integer" />
                    <%
                      int publicType = ((Integer)pageContext.getAttribute("p_public",PageContext.PAGE_SCOPE));
                      int kjnEdKbn = ((Integer)pageContext.getAttribute("p_kjnEdKbn",PageContext.PAGE_SCOPE));
                      int grpEdKbn = ((Integer)pageContext.getAttribute("p_grpEdKbn",PageContext.PAGE_SCOPE));
                    %>

                    <!--公開-->


                    <logic:empty name="uPeriodMdl" property="schAppendUrl">
                      <logic:empty name="uPeriodMdl" property="valueStr">
                      <a href="javascript:void(0);" title="" onClick="return editSchedulePtl('schw_edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, '<%= schFormId %>');">
                      <span class="tooltips"><bean:write name="uPeriodMdl" property="title" /></span>
                      </logic:empty>
                      <logic:notEmpty name="uPeriodMdl" property="valueStr">
                      <bean:define id="scnaiyou" name="uPeriodMdl" property="valueStr" />
                      <%
                        String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                      %>
                      <a href="javascript:void(0);" onClick="return editSchedulePtl('schw_edit', <bean:write name="dayMdl" property="schDate" />, <bean:write name="uPeriodMdl" property="schSid" />, '<%= schFormId %>');" title="">
                      <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                      </logic:notEmpty>
                    </logic:empty>

                    <logic:notEmpty name="uPeriodMdl" property="schAppendUrl">
                      <logic:empty name="uPeriodMdl" property="valueStr">
                      <a href="<bean:write name="uPeriodMdl" property="schAppendUrl" />" title="">
                      <span class="tooltips"><bean:write name="uPeriodMdl" property="title" /></span>
                      </logic:empty>
                      <logic:notEmpty name="uPeriodMdl" property="valueStr">
                      <bean:define id="scnaiyou" name="uPeriodMdl" property="valueStr" />
                      <%
                        String tmpText = (String)pageContext.getAttribute("scnaiyou",PageContext.PAGE_SCOPE);
                        String tmpText2 = jp.co.sjts.util.StringUtilHtml.transToHTml(tmpText);
                      %>
                      <a href="<bean:write name="uPeriodMdl" property="schAppendUrl" />" title="">
                      <span class="tooltips"><gsmsg:write key="cmn.content" />:<%= tmpText2 %></span>
                      </logic:notEmpty>
                    </logic:notEmpty>

                    <!--タイトルカラー設定-->
                    <logic:equal name="uPeriodMdl" property="bgColor" value="0">
                    <span class="sc_link_1">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="1">
                    <span class="sc_link_1">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="2">
                    <span class="sc_link_2">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="3">
                    <span class="sc_link_3">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="4">
                    <span class="sc_link_4">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="5">
                    <span class="sc_link_5">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="6">
                    <span class="sc_link_6">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="7">
                    <span class="sc_link_7">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="8">
                    <span class="sc_link_8">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="9">
                    <span class="sc_link_9">
                    </logic:equal>
                    <logic:equal name="uPeriodMdl" property="bgColor" value="10">
                    <span class="sc_link_10">
                    </logic:equal>
                      <span class="sc_link_g">G</span>
                      <logic:notEmpty name="uPeriodMdl" property="time">
                      <font size="-2"><bean:write name="uPeriodMdl" property="time" /><br></font>
                      </logic:notEmpty>
                      <bean:write name="uPeriodMdl" property="title" filter="false"/>
                    </span>
                    </a>

                </td>
              </logic:notEmpty>
              <logic:empty name="uPeriodMdl" property="periodMdl">


                <% String td_class = "td_type_kikan2"; %>
                <% if ((Integer.valueOf(pIdx) + 1) == (Integer.valueOf(size))) { %>
                <% td_class ="td_type_kikan3"; %>
                <% } else if (Integer.valueOf(pIdx) == 0){ %>
                <% td_class ="td_type_kikan4"; %>
                <% } %>

                <% if ((Integer.valueOf(rowIdx) + 1) == (Integer.valueOf(rowSize))) { %>
                <% td_class = td_class  + " td_type_kikan5"; %>
                <% } %>

                <td class="<%= td_class %>"></td>

              </logic:empty>
            </logic:iterate>
          </tr>
        </logic:notEmpty>
      </logic:iterate>
    </logic:notEmpty>

    </table>
</div>

</logic:equal>
</html:form>

</body>
</html:html>