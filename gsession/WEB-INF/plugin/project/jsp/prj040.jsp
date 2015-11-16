<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%  String prjAddClick      = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_PRJ_ADD_CLICK;
    String prjNameClick     = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_PRJ_NAME_CLICK;
    String backClick        = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_BACK_CLICK;
    String prjSelectMember  = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_SCT_MEM;
    String searchClick      = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_SEARCH;
    String export           = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_EXPORT;
    String prev             = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_PAGE_PREVEW;
    String next             = jp.groupsession.v2.prj.prj040.Prj040Action.CMD_PAGE_NEXT;
%>

<%  String prj040 = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ040;
    String mode_add = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;  %>
<gsmsg:define id="textProjectName" msgkey="project.40" />
<gsmsg:define id="textProjectYosan" msgkey="project.10" />
<gsmsg:define id="textProjectStatus" msgkey="cmn.status" />
<gsmsg:define id="textProjectStart" msgkey="main.src.man250.29" />
<gsmsg:define id="textProjectEnd" msgkey="main.src.man250.30" />

<%
  int[] sortKeyList01 = new int[] {
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_ID,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PROJECT_NAME,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_YOSAN,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_STATUS,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_START,
                       jp.groupsession.v2.prj.GSConstProject.SORT_PRJECT_END
                       };
  String[] title_width01 = new String[] { "10", "40", "10", "7", "10", "10"};
  String[] titleList01 = new String[] {
                        jp.groupsession.v2.prj.GSConstProject.SORT_STR_PRJECT_ID,
                        textProjectName,
                        textProjectYosan,
                        textProjectStatus,
                        textProjectStart,
                        textProjectEnd
                       };
%>

<%-- ソートオーダー --%>
<%  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
    int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;  %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
</head>

<body class="body_03" onunload="calWindowClose();">

<html:form action="/project/prj040">

<input type="hidden" name="CMD" id="CMD" value="">
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010Init" />
<html:hidden property="selectingProject" />
<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />

<html:hidden property="prj040searchFlg" />
<html:hidden property="prj040sort" />
<html:hidden property="prj040order" />
<html:hidden property="prj040svScPrjId" />
<html:hidden property="prj040svScStatusFrom" />
<html:hidden property="prj040svScStatusTo" />
<html:hidden property="prj040svScPrjName" />
<html:hidden property="prj040svScStartYearFrom" />
<html:hidden property="prj040svScStartMonthFrom" />
<html:hidden property="prj040svScStartDayFrom" />
<html:hidden property="prj040svScStartYearTo" />
<html:hidden property="prj040svScStartMonthTo" />
<html:hidden property="prj040svScStartDayTo" />
<html:hidden property="prj040svScEndYearFrom" />
<html:hidden property="prj040svScEndMonthFrom" />
<html:hidden property="prj040svScEndDayFrom" />
<html:hidden property="prj040svScEndYearTo" />
<html:hidden property="prj040svScEndMonthTo" />
<html:hidden property="prj040svScEndDayTo" />
<html:hidden property="prj040svScYosanFr" />
<html:hidden property="prj040svScYosanTo" />

<logic:notEmpty name="prj040Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj040Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj040Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj040Form" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj020scrId" value="<%= prj040 %>" />
<html:hidden property="prj020cmdMode" value="<%= mode_add %>" />

<html:hidden property="prj030scrId" value="<%= prj040 %>" />
<html:hidden property="prj030prjSid" value="" />

<bean:define id="orderKey" name="prj040Form" property="prj040order" />
<bean:define id="sortKbn" name="prj040Form" property="prj040sort" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.prj040.1" /> ]</td>
  <td width="100%" class="header_white_bg">
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td align="right">
    <logic:equal name="prj040Form" property="prjAdd" value="true">
    <input type="button" name="btn_prjadd" class="btn_add_n3" value="<gsmsg:write key="project.121" />" onclick="buttonPush('<%= prjAddClick %>');">
    </logic:equal>
    <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('<%= backClick %>');">
  </td>
  </tr>
  </table>

  <IMG SRC="./images/spacer.gif" width="1px" height="10px" border="0">

  <logic:messagesPresent message="false">
    <table width="100%">
    <tr><td colspan="4" align="left"><html:errors/></td></tr>
    </table>
  </logic:messagesPresent>

  <!-- ページコンテンツ start -->
  <table width="100%" class="tl0 prj_tbl_base">
  <tr>
  <td colspan="4" class="table_bg_7D91BD" align="left">
    <img src="../common/images/search.gif" class="img_bottom" alt="<gsmsg:write key="cmn.advanced.search" />"><span class="text_tl1"><gsmsg:write key="cmn.advanced.search" /></span>
  </td>
  </tr>

  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="project.31" /></th>
  <td width="40%" class="td_sub_detail">
    <html:text property="prj040scPrjId" styleClass="text_base" style="width:131px;" size="20" />
  </td>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.status" /></th>
  <td width="40%" class="td_sub_detail">
    <html:text property="prj040scStatusFrom" styleClass="text_base" maxlength="3" style="width:46px;" />%～
    <html:text property="prj040scStatusTo" styleClass="text_base" maxlength="3" style="width:46px;" />%
  </td>
  </tr>

  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="project.40" /></th>
  <td class="td_sub_detail"><html:text property="prj040scPrjName" styleClass="text_base" maxlength="50" style="width:281px;" /></td>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.squad" /></th>
  <td class="td_sub_detail">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.select2" />" onclick="buttonPush('<%= prjSelectMember.toString() %>');">&nbsp;&nbsp;
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.clear" />" onClick="clearUserList();"><br>
    <span id="selectMember">
    <logic:notEmpty name="prj040Form" property="memberList" scope="request">
    <logic:iterate id="memMdl" name="prj040Form" property="memberList" scope="request">
    <bean:write name="memMdl" property="usiSei" />&nbsp;<bean:write name="memMdl" property="usiMei" />&nbsp;&nbsp;
    </logic:iterate>
    </logic:notEmpty>
    </span>
  </td>
  </tr>

  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="project.10" /></th>
  <td class="td_sub_detail" colspan="3">
    <html:text property="prj040scYosanFr" style="width:153px;" maxlength="14" />&nbsp;<span class="text_base2"><gsmsg:write key="project.103" /></span>&nbsp;～&nbsp;<html:text property="prj040scYosanTo" style="width:153px;" maxlength="14" />&nbsp;<span class="text_base2"><gsmsg:write key="project.103" /></span>
  </td>
  </tr>

  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.start" /></th>
  <td class="td_sub_detail" colspan="3">
    <html:select property="prj040scStartYearFrom" styleId="selYearFr">
      <html:optionsCollection name="prj040Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scStartMonthFrom" styleId="selMonthFr">
      <html:optionsCollection name="prj040Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scStartDayFrom" styleId="selDayFr">
      <html:optionsCollection name="prj040Form" property="dayLabel" value="value" label="label" />
    </html:select>

    <input type="button" value="Cal" onclick="wrtCalendar($('#selDayFr')[0], $('#selMonthFr')[0], $('#selYearFr')[0])" class="calendar_btn">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('selDayFr', 'selMonthFr', 'selYearFr')">
    &nbsp;～&nbsp;

    <html:select property="prj040scStartYearTo" styleId="selYearTo">
      <html:optionsCollection name="prj040Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scStartMonthTo" styleId="selMonthTo">
      <html:optionsCollection name="prj040Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scStartDayTo" styleId="selDayTo">
      <html:optionsCollection name="prj040Form" property="dayLabel" value="value" label="label" />
    </html:select>

    <input type="button" value="Cal" onclick="wrtCalendar($('#selDayTo')[0], $('#selMonthTo')[0], $('#selYearTo')[0])" class="calendar_btn">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('selDayTo', 'selMonthTo', 'selYearTo')">
  </td>
  </tr>

  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.end" /></th>
  <td class="td_sub_detail" colspan="3">
    <html:select property="prj040scEndYearFrom" styleId="selYearFr2">
      <html:optionsCollection name="prj040Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scEndMonthFrom" styleId="selMonthFr2">
      <html:optionsCollection name="prj040Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scEndDayFrom" styleId="selDayFr2">
      <html:optionsCollection name="prj040Form" property="dayLabel" value="value" label="label" />
    </html:select>

    <input type="button" value="Cal" onclick="wrtCalendar($('#selDayFr2')[0], $('#selMonthFr2')[0], $('#selYearFr2')[0])" class="calendar_btn">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('selDayFr2', 'selMonthFr2', 'selYearFr2')">
    &nbsp;～&nbsp;

    <html:select property="prj040scEndYearTo" styleId="selYearTo2">
      <html:optionsCollection name="prj040Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scEndMonthTo" styleId="selMonthTo2">
      <html:optionsCollection name="prj040Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj040scEndDayTo" styleId="selDayTo2">
      <html:optionsCollection name="prj040Form" property="dayLabel" value="value" label="label" />
    </html:select>

    <input type="button" value="Cal" onclick="wrtCalendar($('#selDayTo2')[0], $('#selMonthTo2')[0], $('#selYearTo2')[0])" class="calendar_btn">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('selDayTo2', 'selMonthTo2', 'selYearTo2')">
  </td>
  </tr>
  </table>

  <div><img src="../common/images/spacer.gif" width="1" height="10"></div>
  <div align="right" class="seach_function_l"><input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onclick="buttonPush('<%= searchClick %>');"></div>

  <div align="right" class="seach_function_r">
<logic:notEmpty name="prj040Form" property="projectList" scope="request">
  <input type="button" name="export" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onclick="buttonPush('<%= export %>');">
</logic:notEmpty>
  </div>

  <br class="clear">
  <br>
<logic:notEmpty name="prj040Form" property="projectList" scope="request">

  <table width="100%" cellpadding="5" cellspacing="0">
  <tr>
  <td align="right">
    <bean:size id="count1" name="prj040Form" property="pageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= prev %>');">
    <html:select property="prj040page1" onchange="changePage(1);" styleClass="text_i">
      <html:optionsCollection name="prj040Form" property="pageLabel" value="value" label="label" />
    </html:select>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= next %>');">
    </logic:greaterThan>
  </td>
  </tr>
  </table>

  <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
  <tr class="table_bg_7D91BD_search">
<%
    for (int i = 0; i < sortKeyList01.length; i++) {

      if (i == 3) {
%>
    <th width="10%"><span class="text_tlw"><gsmsg:write key="cmn.admin" /></span></th>
<%
      }


      if (iSortKbn == sortKeyList01[i]) {
        if (iOrderKey == order_desc) {
%>
        <th width="<%= title_width01[i] %>%"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_tlw"><%= titleList01[i] %>▼</span></a></th>
<%
        } else {
%>
        <th width="<%= title_width01[i] %>%"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_desc %>);"><span class="text_tlw"><%= titleList01[i] %>▲</span></a></th>
<%
        }
      } else {
%>
        <th width="<%= title_width01[i] %>%"><a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_tlw"><%= titleList01[i] %></span></a></th>
<%
      }
    }
%>
  </tr>

  <logic:notEmpty name="prj040Form" property="projectList" scope="request">
  <logic:iterate id="prjMdl" name="prj040Form" property="projectList" scope="request" indexId="idx">
  <bean:define id="backclass" value="td_line_color" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

  <tr class="<%= String.valueOf(back) %>">
  <td class="prj_td"><span class="text_blue"><bean:write name="prjMdl" property="projectId" /></span></td>
  <td class="prj_td" align="left">
    <logic:equal name="prjMdl" property="prjBinSid" value="0">
      <img src="../project/images/prj_icon.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" class="prj_img_ico">
    </logic:equal>
    <logic:notEqual name="prjMdl" property="prjBinSid" value="0">
      <img src="../project/prj040.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prjMdl" property="projectSid" />&prj010PrjBinSid=<bean:write name="prjMdl" property="prjBinSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');" class="prj_img_ico">
    </logic:notEqual>
    <a href="javascript:void(0)" onClick="return viewPoject('<%= prjNameClick %>', '<bean:write name="prjMdl" property="projectSid" />');"><span class="text_blue"><bean:write name="prjMdl" property="projectName" /></span></a></td>
  <td class="prj_td" align="right"><span class="text_blue"><bean:write name="prjMdl" property="strYosan" /></span></td>
  <td class="prj_td" align="center">
    <span class="text_blue">
    <logic:notEmpty name="prjMdl" property="memberList">
    <logic:iterate id="userMdl" name="prjMdl" property="memberList">
      <logic:equal name="userMdl" property="status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
      <bean:write name="userMdl" property="sei" />&nbsp;<bean:write name="userMdl" property="mei" />
      </logic:equal>
      <logic:notEqual name="userMdl" property="status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
      <del><bean:write name="userMdl" property="sei" />&nbsp;<bean:write name="userMdl" property="mei" /></del>
      </logic:notEqual>
      <br>
    </logic:iterate>
    </logic:notEmpty>
    </span>
  </td>
  <td class="prj_td" align="center">
    <logic:equal name="prjMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
      <span class="text_blue"><bean:write name="prjMdl" property="rate" />%<br>(<bean:write name="prjMdl" property="statusName" />)</span>
    </logic:equal>
  </td>
  <td class="prj_td" nowrap align="center"><span class="text_blue"><bean:write name="prjMdl" property="strStartDate" /></span></td>
  <td class="prj_td" nowrap align="center"><span class="text_blue"><bean:write name="prjMdl" property="strEndDate" /></span></td>
  </tr>

  </logic:iterate>
  </logic:notEmpty>

  </table>

  <table width="100%" cellpadding="5" cellspacing="0">
  <tr>
  <td align="right">
    <logic:greaterThan name="count1" value="1">
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= prev %>');">
    <html:select property="prj040page2" onchange="changePage(2);" styleClass="text_i">
      <html:optionsCollection name="prj040Form" property="pageLabel" value="value" label="label" />
    </html:select>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= next %>');">
    </logic:greaterThan>
  </td>
  </tr>
  </table>

</logic:notEmpty>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>

</body>
</html:html>
