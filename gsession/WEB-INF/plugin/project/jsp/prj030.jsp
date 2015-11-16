    <%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%  String prjEditClick  = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_PRJ_EDIT_CLICK;
    String back030       = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_BACK_CLICK;
    String todoAdd       = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_TODO_ADD;
    String todoEdit      = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_TODO_EDIT;
    String todoRef       = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_TODO_TITLE_CLICK;
    String todoSearch    = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_TODO_SEARCH;
    String cirSend       = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_CIR_SEND;
    String smlSend       = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_SML_SEND;
    String memberEdit    = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_MEMBER_EDIT;
    String smlNew        = jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NEW;
    String todoImport    = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_TODO_IMPORT;
    String detailDir     = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_DETAIL_DIR;
    String prev          = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_PAGE_PREVEW;
    String next          = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_PAGE_NEXT;
    String cmdDelTo      = jp.groupsession.v2.prj.prj010.Prj010Action.CMD_DEL_TODO;
    String editStatus    = jp.groupsession.v2.prj.prj030.Prj030Action.CMD_EDIT_STATUS;
%>
<gsmsg:define id="projectTitle" msgkey="cmn.project" />
<gsmsg:define id="title" msgkey="cmn.title" />
<gsmsg:define id="todoNum" msgkey="project.prj050.5" />
<gsmsg:define id="todoTanto" msgkey="cmn.staff" />
<gsmsg:define id="todoWeight" msgkey="project.prj050.4" />
<gsmsg:define id="todoStatus" msgkey="cmn.status" />
<gsmsg:define id="todoStartPlan" msgkey="project.100" />
<gsmsg:define id="todoLimitPlan" msgkey="project.src.66" />
<%
  int[] sortKeyList01 = new int[] {
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_NO,
                       -1,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_WEIGHT,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_STATUS,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_START_PLAN,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_LIMIT_PLAN
                       };
  String[] title_width01 = new String[] { "32", "10", "10", "10", "10", "10", "10"};
  String[] titleList01 = new String[] {
                        title,
                        todoNum,
                        todoTanto,
                        todoWeight,
                        todoStatus,
                        todoStartPlan,
                        todoLimitPlan
                       };
  String dspId = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ030;
%>

<logic:notEqual name="prj030Form" property="prjTodoEdit" value="true">
<% title_width01[0] = "33"; %>
</logic:notEqual>

<%  String prj030 = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ030;
    String search_ok = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEARCH_FLG_OK);
    String mode_add = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;
    String mode_edit = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_EDIT;  %>

<%-- ソートオーダー --%>
<%  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
    int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;  %>

<bean:define id="orderKey" name="prj030Form" property="prj030order" />
<bean:define id="sortKbn" name="prj030Form" property="prj030sort" />
<bean:define id="prjSid" name="prj030Form" property="prj030prjSid" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="project.107" /> <gsmsg:write key="project.prj030.1" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/ui/jquery-ui-1.8.16.custom.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/dtree.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<logic:equal name="prj030Form" property="useAddress" value="true">
<script language="JavaScript" src="../address/js/adrPopup.js?<%= GSConst.VERSION_PARAM %>"></script>
</logic:equal>

<theme:css filename="theme.css"/>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/project/prj030">

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
<html:hidden property="prj040scPrjId" />
<html:hidden property="prj040scStatusFrom" />
<html:hidden property="prj040scStatusTo" />
<html:hidden property="prj040scPrjName" />
<html:hidden property="prj040scStartYearFrom" />
<html:hidden property="prj040scStartMonthFrom" />
<html:hidden property="prj040scStartDayFrom" />
<html:hidden property="prj040scStartYearTo" />
<html:hidden property="prj040scStartMonthTo" />
<html:hidden property="prj040scStartDayTo" />
<html:hidden property="prj040scEndYearFrom" />
<html:hidden property="prj040scEndMonthFrom" />
<html:hidden property="prj040scEndDayFrom" />
<html:hidden property="prj040scEndYearTo" />
<html:hidden property="prj040scEndMonthTo" />
<html:hidden property="prj040scEndDayTo" />
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
<html:hidden property="prj040page1" />
<html:hidden property="prj040page2" />
<html:hidden property="prj040sort" />
<html:hidden property="prj040order" />
<html:hidden property="prj040scYosanFr" />
<html:hidden property="prj040scYosanTo" />
<html:hidden property="prj040svScYosanFr" />
<html:hidden property="prj040svScYosanTo" />

<logic:notEmpty name="prj030Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj030Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj030Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj030Form" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj030scrId" />
<html:hidden property="prj030prjSid" />
<html:hidden property="prj030sort" />
<html:hidden property="prj030order" />
<html:hidden property="prj030Init" />
<input type="hidden" name="prjmvComment">

<html:hidden property="prj020scrId" value="<%= prj030 %>" />
<html:hidden property="prj020cmdMode" value="<%= mode_edit %>" />
<html:hidden property="prj020prjSid" value="<%= String.valueOf(prjSid) %>" />

<html:hidden property="prj070scrId" value="<%= prj030 %>" />
<html:hidden property="prj070scPrjSid" value="<%= String.valueOf(prjSid) %>" />
<html:hidden property="prj070svScPrjSid" value="<%= String.valueOf(prjSid) %>" />
<html:hidden property="prj070searchFlg" value="<%= search_ok %>" />

<html:hidden property="prj050scrId" value="<%= prj030 %>" />
<html:hidden property="prj050prjSid" value="<%= String.valueOf(prjSid) %>" />
<html:hidden property="prj050cmdMode" />
<html:hidden property="prj050todoSid" />

<html:hidden property="prj060scrId" value="<%= prj030 %>" />
<html:hidden property="prj060prjSid" value="<%= String.valueOf(prjSid) %>" />
<html:hidden property="prj060todoSid" />

<html:hidden property="movedDspId" />

<input type="hidden" name="sml020ProcMode" value="<%= smlNew %>">

<bean:define id="projectMdl" name="prj030Form" property="projectItem" />

<logic:notEmpty name="prj030Form" property="treeFormLv1" scope="request">
  <logic:iterate id="lv1" name="prj030Form" property="treeFormLv1" scope="request">
    <input type="hidden" name="treeFormLv1" value="<bean:write name="lv1"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj030Form" property="treeFormLv2" scope="request">
  <logic:iterate id="lv2" name="prj030Form" property="treeFormLv2" scope="request">
    <input type="hidden" name="treeFormLv2" value="<bean:write name="lv2"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj030Form" property="treeFormLv3" scope="request">
  <logic:iterate id="lv3" name="prj030Form" property="treeFormLv3" scope="request">
    <input type="hidden" name="treeFormLv3" value="<bean:write name="lv3"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj030Form" property="treeFormLv4" scope="request">
  <logic:iterate id="lv4" name="prj030Form" property="treeFormLv4" scope="request">
    <input type="hidden" name="treeFormLv4" value="<bean:write name="lv4"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj030Form" property="treeFormLv5" scope="request">
  <logic:iterate id="lv5" name="prj030Form" property="treeFormLv5" scope="request">
    <input type="hidden" name="treeFormLv5" value="<bean:write name="lv5"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj030Form" property="treeFormLv6" scope="request">
  <logic:iterate id="lv6" name="prj030Form" property="treeFormLv6" scope="request">
    <input type="hidden" name="treeFormLv6" value="<bean:write name="lv6"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="selectDir" />
<html:hidden property="prj030SepKey" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
  <td width="0%" class="header_white_bg_text">[&nbsp;<gsmsg:write key="project.prj030.1" />&nbsp;]</td>
  <td align="left" width="100%" class="header_white_bg">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <table cellpadding="3" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="70%" class="text_bb3" valign="bottom">

    <table align="left" width="100%" border="0" cellpadding="3" cellspacing="0">
    <tr>
    <td width="0%">
      <logic:equal name="prj030Form" property="prj010PrjBinSid" value="0">
        <img src="../project/images/icon_30.png" name="pitctImage" alt="<gsmsg:write key="cmn.icon" />" border="1">
      </logic:equal>
      <logic:notEqual name="prj030Form" property="prj010PrjBinSid" value="0">
        <img src="../project/prj030.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prj030Form" property="prj010PrjSid" />&prj010PrjBinSid=<bean:write name="prj030Form" property="prj010PrjBinSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');">
      </logic:notEqual>
    </td>
    <td width="100%"><span class="text_bb4"><bean:write name="projectMdl" property="projectName" /></span></td>
    </tr>
    </table>

  </td>
  <td width="0%"></td>
  <td width="30%" align="right" valign="top" colspan="2">
    <logic:equal name="prj030Form" property="prjEdit" value="true">
    <input type="button" value="<gsmsg:write key="cmn.fixed2" />" class="btn_edit_n1" onclick="buttonPush('<%= prjEditClick %>');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('<%= back030 %>');">
  </td>
  </tr>
  </table>

  <!-- ページコンテンツ start -->
  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
    <td width="20%" valign="top" rowspan="2">
    <bean:define id="rate" name="projectMdl" property="rate" />
    <bean:define id="notEndRate" name="projectMdl" property="notEndRate" />

    <logic:equal name="projectMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <table align="left" width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td nowrap><span class="text_todo_head"></span></td>
    <logic:equal name="projectMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MIN) %>">
      <logic:equal name="projectMdl" property="notEndRate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
        <td nowrap width="<%= notEndRate %>%" height="25" class="prj_status_not_end_max" align="center"><span class="text_todo_head"><gsmsg:write key="project.27" /><bean:write name="rate" />%</span></td>
      </logic:equal>
    </logic:equal>

    <logic:equal name="projectMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
      <logic:equal name="projectMdl" property="notEndRate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MIN) %>">
        <td nowrap width="<%= rate %>%" height="25" class="prj_status_end" align="center"><span class="text_prj_sts"><gsmsg:write key="project.27" /><bean:write name="rate" />%</span></td>
      </logic:equal>
    </logic:equal>

    <logic:greaterThan name="projectMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MIN) %>">
      <logic:lessThan name="projectMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
        <td nowrap width="<%= rate %>%" height="25" class="prj_status_end" align="center"><span class="text_prj_sts"><gsmsg:write key="project.27" /><bean:write name="rate" />%</span></td>
        <td nowrap width="<%= notEndRate %>%" height="25" class="prj_status_not_end_range" align="center">&nbsp;</td>
      </logic:lessThan>
    </logic:greaterThan>

    </tr>
    </table>
    <br><br>
    </logic:equal>


    <logic:equal name="projectMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <!-- メインに表示する member -->
    <table class="tl0 mem_table" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="member_th" colspan="3">

      <bean:size id="count" name="prj030Form" property="userList" scope="request" />
      <bean:define id="memberCount" value="<%= String.valueOf(count) %>" />
      <table width="100%" class="tl0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="10%" align="center" nowrap>
        <input type="checkbox" name="allCheck" onClick="changeChk();">
        <span class="text_mem_head"><gsmsg:write key="cmn.member" />&nbsp;&nbsp;<%= memberCount %><gsmsg:write key="cmn.name3" /></span>&nbsp;
      </td>
      <td width="90%" align="right" nowrap>
        <logic:equal name="prj030Form" property="useCircular" value="true">
          <input type="button" class="btn_kairan" value="&nbsp;" onclick="return buttonPush('<%= cirSend %>');">
        </logic:equal>
        <logic:equal name="prj030Form" property="useSmail" value="true">
          <input type="button" class="btn_smail" value="&nbsp;" onclick="return buttonPush('<%= smlSend %>');">
        </logic:equal>&nbsp;
      </td>
      </tr>
      </table>
    </th>

    <logic:notEmpty name="prj030Form" property="userList" scope="request">
    <logic:iterate id="userMdl" name="prj030Form" property="userList" scope="request" indexId="idx">


    <logic:equal name="userMdl" property="zaisekiKbn" value="1">
    <tr class="mem_zaiseki">
    </logic:equal>
    <logic:equal name="userMdl" property="zaisekiKbn" value="2">
    <tr class="mem_gaisyutu">
    </logic:equal>
    <logic:equal name="userMdl" property="zaisekiKbn" value="0">
    <tr class="mem_fuzai">
    </logic:equal>

    <td width="0%" align="center" style="padding: 3px 3px 3px 3px;">
      <logic:equal name="userMdl" property="status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
      <html:multibox name="prj030Form" property="prj030sendMember">
      <bean:write name="userMdl" property="userSid" />
      </html:multibox>
      </logic:equal>
    </td>
    <td width="0%" style="padding: 3px 3px 3px 3px;">
      <logic:equal name="userMdl" property="adminKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_POWER_NORMAL) %>">
        <img src="../project/images/person_n1.gif" alt="<gsmsg:write key="cmn.member" />">
      </logic:equal>
      <logic:equal name="userMdl" property="adminKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_POWER_ADMIN) %>">
        <img src="../project/images/person_r1.gif" alt="<gsmsg:write key="cmn.admin" />">
      </logic:equal>
    </td>
    <td width="100%" align="left">
      <logic:equal name="userMdl" property="status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
      <span class="blue_link"><a href="javaScript:void(0);" onClick="return openUserInfoWindow('<bean:write name="userMdl" property="userSid" />');"><bean:write name="userMdl" property="sei" />&nbsp;<bean:write name="userMdl" property="mei" /></a></span>
      </logic:equal>
      <logic:notEqual name="userMdl" property="status" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
      <span class="blue_link"><del><bean:write name="userMdl" property="sei" />&nbsp;<bean:write name="userMdl" property="mei" /></del></span>
      </logic:notEqual>
    </td>
    </tr>
    </logic:iterate>

    <logic:notEmpty name="prj030Form" property="outMemberList" scope="request">
    <bean:size id="outMemCnt" name="prj030Form" property="outMemberList" scope="request" />
    <bean:define id="outMemberCount" value="<%= String.valueOf(outMemCnt) %>" />

    <tr>
    <th class="member_th" colspan="3">
      <table width="100%" class="tl0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="10%" align="center" nowrap>
        <span class="text_mem_head"><gsmsg:write key="project.28" />&nbsp;&nbsp;<%= outMemberCount %><gsmsg:write key="cmn.name3" /></span>&nbsp;
      </td>
      <td width="90%" align="right" nowrap>
      </td>
      </tr>
      </table>
    </th>
    </tr>

    <logic:iterate id="outMemMdl" name="prj030Form" property="outMemberList" scope="request" indexId="idx">
    <tr class="mem_zaiseki">
    <td width="0%" align="center" style="padding: 3px 3px 3px 3px;">
    </td>
    <td  width="0%" style="padding: 3px 3px 3px 3px;">
      <img src="../project/images/person_n1.gif" alt="<gsmsg:write key="cmn.member" />">
    </td>
    <td width="100%" align="left">
      <span class="blue_link"><a href="javascript:void(0);" onClick="openAddressInfoWindow('<bean:write name="outMemMdl" property="adrSid" />');"><bean:write name="outMemMdl" property="adrName" /></a></span>
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>

    <logic:equal name="prj030Form" property="prjEdit" value="true">
      <logic:greaterThan name="memberCount" value="0">
      <tr class="td_label_mem3">
      <td colspan="3" align="right">
        <a href="javascript:void(0);" onclick="return memberEdit('<%= memberEdit %>', '<%= dspId %>');"><span class="text_mem_set">[<gsmsg:write key="project.29" />]</span></a>
      </td>
      </tr>
      </logic:greaterThan>
    </logic:equal>

    </logic:notEmpty>
    </table>
    <br>
    </logic:equal>


    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="prj_mokuteki" nowrap><gsmsg:write key="project.30" /></th>
    </tr>
    <tr>
    <td class="prj_mokuteki_val">
    <span class="text_base">
    <logic:notEmpty name="projectMdl" property="strStartDate">
    &nbsp;&nbsp;<gsmsg:write key="cmn.period" />:
    </logic:notEmpty>
    <logic:empty name="projectMdl" property="strStartDate">
      <logic:notEmpty name="projectMdl" property="strEndDate">
      &nbsp;&nbsp;<gsmsg:write key="cmn.period" />:
      </logic:notEmpty>
    </logic:empty>

    <bean:write name="projectMdl" property="strStartDate" />

    <% String kikan = ""; %>
    <logic:notEmpty name="projectMdl" property="strStartDate">
    <% kikan = "～"; %>
    </logic:notEmpty>
    <logic:notEmpty name="projectMdl" property="strEndDate">
    <% kikan = "～"; %>
    </logic:notEmpty>
    <%= kikan %>
    <bean:write name="projectMdl" property="strEndDate" />

    <br>
    <logic:notEmpty name="projectMdl" property="strYosan">
    &nbsp;&nbsp;<gsmsg:write key="project.10" />:
    <bean:write name="projectMdl" property="strYosan" />
    </logic:notEmpty>
    </span>
    </td>
    </tr>
    </table>
    <br>

    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="prj_mokuteki" nowrap><gsmsg:write key="project.21" /></th>
    </tr>
    <logic:notEmpty name="projectMdl" property="mokuhyou">
    <tr>
    <td width="80%" class="prj_mokuteki_val"><bean:write name="projectMdl" property="mokuhyou" filter="false" /></th>
    </tr>
    </logic:notEmpty>
    </table>
    <br>

    <table class="tl0 prj_tbl_base4" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <th class="prj_mokuteki" nowrap><gsmsg:write key="cmn.content" /></th>
    </tr>
    <logic:notEmpty name="projectMdl" property="naiyou">
    <tr>
    <td width="80%" class="prj_mokuteki_val"><bean:write name="projectMdl" property="naiyou" filter="false" /></th>
    </tr>
    </logic:notEmpty>
    </table>
    <br>

  </td>

  <td width="80%" valign="top">

  <!-- エラーメッセージ -->
  <logic:messagesPresent message="false">
  <table width="100%">
  <tr><td colspan="4" align="left"><html:errors/></td></tr>
  </table>
  </logic:messagesPresent>

    <table width="100%" border="0" cellpadding="0">

    <tr>
    <td>

      <!-- TODO -->
      <table class="tl0 prj_tbl_base3" width="100%" cellpadding="5" cellspacing="0">
      <tr>
      <td class="prjlist_title_back">

        <table width="100%" cellpadding="0" cellspacing="0">

        <tr>
        <th align="left" nowrap><span class="text_todo_head">TODO</span></th>
        <td align="right" nowrap>
          <span class="text_todo_head"><gsmsg:write key="project.100" /></span>
          <logic:notEmpty name="prj030Form" property="targetDateLabel">
            <html:select property="selectingDate" onchange="buttonPush('<%= init %>');">
              <html:optionsCollection name="prj030Form" property="targetDateLabel" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <img src="../project/images/spacer.gif" width="1px" height="1px" border="0">

          <span class="text_todo_head"><gsmsg:write key="cmn.status" /></span>
          <logic:notEmpty name="prj030Form" property="targetStatusLabel">
            <html:select property="selectingStatus" onchange="buttonPush('<%= init %>');">
              <html:optionsCollection name="prj030Form" property="targetStatusLabel" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <img src="../project/images/spacer.gif" width="1px" height="1px" border="0">

          <span class="text_todo_head"><gsmsg:write key="cmn.label" /></span>
          <logic:notEmpty name="prj030Form" property="targetCategoryLabel">
            <html:select property="selectingCategory" onchange="buttonPush('<%= init %>');">
              <html:optionsCollection name="prj030Form" property="targetCategoryLabel" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <img src="../project/images/spacer.gif" width="1px" height="1px" border="0">

          <span class="text_todo_head"><gsmsg:write key="cmn.member" /></span>
          <logic:notEmpty name="prj030Form" property="targetMemberLabel">
            <html:select property="selectingMember" onchange="buttonPush('<%= init %>');">
              <html:optionsCollection name="prj030Form" property="targetMemberLabel" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <input type="button" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onclick="buttonPush('<%= todoSearch %>');">
        </td>
        </tr>
        </table>
      </td>
      </tr>

    </table>
    </td>
    </tr>

    <tr>
    <td>
    <table width="100%" cellpadding="0" cellspacing="0">
      <tr><td><img src="../common/images/spacer.gif" height="5"></td></tr>
      <tr>
      <td width="98%" align="right">

        <logic:equal name="prj030Form" property="prjTodoEdit" value="true">
          <logic:notEmpty name="prj030Form" property="projectList" scope="request">
          <a href="javascript:void(0)" onClick="return doOpenDialog('<bean:write name="prj030Form" property="prj030prjSid" />');"><span class="text_todo_head">▼<gsmsg:write key="project.date.change" /></span></a>
          &nbsp;&nbsp;<span class="text_todo_head"><gsmsg:write key="project.20" />:</span>&nbsp;
            <html:select property="prj030selectEditStatus" styleClass="text_i">
              <html:optionsCollection name="prj030Form" property="editStatusLabel" value="value" label="label" />
            </html:select>
            <input type="button" value="<gsmsg:write key="cmn.change" />" class="btn_base0" onclick="buttonPush('<%= editStatus %>');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </logic:notEmpty>
        </logic:equal>
        <!-- logic:equal name="prj030Form" property="prjTodoEdit" value="true" -->
          <input type="button" class="btn_csv_n2" value="<gsmsg:write key="cmn.import" />" onclick="buttonPush('<%= todoImport %>');">
        <!-- /logic:equal -->
        <!-- logic:equal name="prj030Form" property="prjTodoEdit" value="true" -->
          <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.new.registration" />" onclick="buttonPushAdd('<%= todoAdd %>', <%= mode_add %>);">
        <!-- /logic:equal -->

        <logic:equal name="prj030Form" property="prjTodoEdit" value="true">
          <logic:notEmpty name="prj030Form" property="projectList" scope="request">
          <input type="button" name="btn_tododel"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('<%= cmdDelTo %>');">
          </logic:notEmpty>
        </logic:equal>
      </td>

      <bean:size id="count1" name="prj030Form" property="pageLabel" scope="request" />
      <logic:greaterThan name="count1" value="1">
        <td width="2%" align="right" valign="middle" nowrap>
          &nbsp;
          <img src="../common/images/arrow2_l.gif" class="img_pageBtn" alt="<gsmsg:write key="cmn.previous.page" />" onClick="buttonPush('<%= prev %>');">
          <html:select property="prj030page1" onchange="changePage(1);" styleClass="text_i">
            <html:optionsCollection name="prj030Form" property="pageLabel" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" class="img_pageBtn" alt="<gsmsg:write key="cmn.next.page" />" onClick="buttonPush('<%= next %>');">&nbsp;
        </td>
      </logic:greaterThan>

      </tr>

      <tr>
      <td><img src="../project/images/spacer.gif" width="1px" height="5px" border="0"></td>
      </tr>
    </table>

    <table width="100%">

      <tr>
      <logic:equal name="prj030Form" property="prjTodoEdit" value="true">
      <td width="1%" class="prj_title td_type3" align="center"><input type="checkbox" name="prj030AllCheck" value="1" onClick="chgCheckAll('prj030AllCheck', 'prj030selectTodo');chgCheckAllChange('prj030AllCheck', 'prj030selectTodo');"></td>
      </logic:equal>

      <%
      for (int i = 0; i < sortKeyList01.length; i++) {
        if (i != 1) {
      %>
        <th width="<%= title_width01[i] %>%" class="prj_title td_type3">
      <%} else { %>
      &nbsp;/&nbsp;
      <%
        }
      if (iSortKbn == sortKeyList01[i]) {
          if (iOrderKey == order_desc) {
      %>
      <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_prjtodo_list_head"><%= titleList01[i] %>▼</span></a>
      <%
          } else {
      %>
      <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_desc %>);"><span class="text_prjtodo_list_head"><%= titleList01[i] %>▲</span></a>
      <%
          }
        } else {
      %>
      <%
          if (sortKeyList01[i] == -1) {
      %>
      <%-- 担当者名 --%>
      <span class="text_prjtodo_list_head"><%= titleList01[i] %></span>
      <%
          } else {
      %>
      <a href="javascript:void(0);" onClick="return onTitleLinkSubmit(<%= sortKeyList01[i] %>, <%= order_asc %>);"><span class="text_prjtodo_list_head"><%= titleList01[i] %></span></a>
      <%
          }
      %>
      <%
        }
      %>

      <% if (i >= 1) { %>
        </th>
      <%
        }
      }
      %>
      </tr>

      <logic:notEmpty name="prj030Form" property="projectList" scope="request">
      <logic:iterate id="prjMdl" name="prj030Form" property="projectList" scope="request" indexId="idx">
      <bean:define id="backclass" value="td_line_color" />
      <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
      <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

      <tr class="<%= back %>" id="<bean:write name="prjMdl" property="todoSid" />">
      <logic:equal name="prj030Form" property="prjTodoEdit" value="true">
      <td class="prj_td" align="center">
        <% if (Integer.valueOf(backpat) == 1) { %>
          <html:multibox name="prj030Form" property="prj030selectTodo" onclick="backGroundSetting(this, '1');">
            <bean:write name="prjMdl" property="todoSid" />
          </html:multibox>
        <% } else { %>
           <html:multibox name="prj030Form" property="prj030selectTodo" onclick="backGroundSetting(this, '2');">
            <bean:write name="prjMdl" property="todoSid" />
          </html:multibox>
        <% } %>
      </td>
      </logic:equal>

      <td class="prj_td">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        <td width="99%">
          <logic:equal name="prjMdl" property="keikoku" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEIKOKU_ARI) %>">
            <logic:notEqual name="prjMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
              <img src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />" class="prj_img_warn">&nbsp;
            </logic:notEqual>
          </logic:equal>
          <a href="javascript:void(0)" onClick="return viewTodo('<%= todoRef %>', '<bean:write name="prjMdl" property="todoSid" />');"><span class="text_blue"><bean:write name="prjMdl" property="todoTitle" filter="false" /></span></a>
          <logic:equal name="prjMdl" property="prjTodoEdit" value="true">
          <a href="javascript:void(0)" onClick="return editTodo('<%= todoEdit %>', '<%= mode_edit %>', '<bean:write name="prjMdl" property="projectSid" />', '<bean:write name="prjMdl" property="todoSid" />');"><img src="../project/images/prj_todo_add.gif" alt="<gsmsg:write key="project.56" />"></a>
          </logic:equal>
        </td>
        <td width="1%" align="right" valign="middle" nowrap>
          <logic:notEqual name="prjMdl" property="prjTodoCommentCnt" value="0">
            <a href="javascript:void(0)" onClick="return viewTodoComment('<%= todoRef %>', '<bean:write name="prjMdl" property="todoSid" />');">
            <img src="../project/images/todo_ref_comment_s.gif" alt="<gsmsg:write key="cmn.comment" />" class="prj_img_comment_s">
            <span class="text_base"><bean:write name="prjMdl" property="prjTodoCommentCnt" /></span>
            </a>
          </logic:notEqual>
        </td>
        </tr>
        <tr>
          <td colspan="2">
            <span class="text_base"><bean:write name="prjMdl" property="strKanriNo" /></span>&nbsp;&nbsp;
            <logic:notEqual name="prjMdl" property="categoryValue" value="-1">
              <span class="label_style"><bean:write name="prjMdl" property="category" /></span>
            </logic:notEqual>
          </td>
        </tr>
        </table>
      </td>

      <td class="prj_td" align="left">
      <logic:notEmpty name="prjMdl" property="todoTantoList">
        <logic:iterate id="tantoMdl" name="prjMdl" property="todoTantoList" indexId="tantoIdx">
        <% if (tantoIdx.intValue() > 0) { %><br><% } %>

        <logic:equal name="tantoMdl" property="delUser" value="false">
        <span class="text_base"><bean:write name="tantoMdl" property="userName" /></span>
        </logic:equal>
        <logic:notEqual name="tantoMdl" property="delUser" value="false">
        <span class="text_base"><del><bean:write name="tantoMdl" property="userName" /></del></span>
        </logic:notEqual>

        </logic:iterate>
      </logic:notEmpty>
      </td>

      <%-- <td class="prj_td" align="center"><span class="text_base"><bean:write name="prjMdl" property="strJuyo" /></span></td> --%>

      <td class="prj_td" align="center">
        <logic:equal name="prjMdl" property="juyo" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_LOW) %>">
          <img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />">
        </logic:equal>
        <logic:equal name="prjMdl" property="juyo" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_MIDDLE) %>">
          <img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />">
        </logic:equal>
        <logic:equal name="prjMdl" property="juyo" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_HIGH) %>">
          <img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />">
        </logic:equal>
      </td>

      <td class="prj_td" align="center" nowrap><span class="text_base"><bean:write name="prjMdl" property="rate" />%<br>(<bean:write name="prjMdl" property="statusName" />)</span></td>
      <td class="prj_td" align="center" nowrap><span class="text_base"><bean:write name="prjMdl" property="strStartDate" /></span></td>
      <td class="prj_td" align="center" nowrap><span class="text_base"><bean:write name="prjMdl" property="strEndDate" /></span></td>
      </tr>

      </logic:iterate>
      </logic:notEmpty>
      </table>

      <bean:size id="count1" name="prj030Form" property="pageLabel" scope="request" />
      <logic:greaterThan name="count1" value="1">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td><img src="../project/images/spacer.gif" width="1px" height="5px" border="0"></td>
      </tr>
      <tr>
      <td>
        <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
        <td align="right">
          <img src="../common/images/arrow2_l.gif" class="img_pageBtn" alt="<gsmsg:write key="cmn.previous.page" />" onClick="buttonPush('<%= prev %>');">
          <html:select property="prj030page2" onchange="changePage(2);" styleClass="text_i">
            <html:optionsCollection name="prj030Form" property="pageLabel" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" class="img_pageBtn" alt="<gsmsg:write key="cmn.next.page" />" onClick="buttonPush('<%= next %>');">&nbsp;
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>

      </logic:greaterThan>

    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

<div id="dialog-form" title="<gsmsg:write key="project.date.change" />" style="display:none; height: 100px!important;">
    <fieldset>

        <div class="dialog_div" style="float:left;">
         <input type="radio" name="plusMinus" id="radioPl" value="plus" checked><label for="radioPl"><b>＋</b></label><br>
         <input type="radio" name="plusMinus" id="radioMi" value="minus"><label for="radioMi"><b>－</b></label>
        </div>
        <div class="dialog_div" style="float:right; width:80%; vertical-align:
         display: inline-block; _display: inline; margin-top: 0.7em; text-align: left;">
        <b><gsmsg:write key="project.move.days" />:</b>
        <select name="prj010SelMonth" id="selMonth">
          <option value="0" selected="selected"><gsmsg:write key="cmn.months" arg0="0"/></option>
          <option value="1"><gsmsg:write key="cmn.months" arg0="1"/></option>
          <option value="2"><gsmsg:write key="cmn.months" arg0="2"/></option>
          <option value="3"><gsmsg:write key="cmn.months" arg0="3"/></option>
          <option value="4"><gsmsg:write key="cmn.months" arg0="4"/></option>
          <option value="5"><gsmsg:write key="cmn.months" arg0="5"/></option>
          <option value="6"><gsmsg:write key="cmn.months" arg0="6"/></option>
          <option value="7"><gsmsg:write key="cmn.months" arg0="7"/></option>
          <option value="8"><gsmsg:write key="cmn.months" arg0="8"/></option>
          <option value="9"><gsmsg:write key="cmn.months" arg0="9"/></option>
          <option value="10"><gsmsg:write key="cmn.months" arg0="10"/></option>
          <option value="11"><gsmsg:write key="cmn.months" arg0="11"/></option>
          <option value="12"><gsmsg:write key="cmn.months" arg0="12"/></option>
        </select>
        <select name="prj010SelDay" id="selDay">
          <option value="1">0<gsmsg:write key="cmn.day" /></option>
          <option value="1" selected="selected">1<gsmsg:write key="cmn.day" /></option>
          <option value="2">2<gsmsg:write key="cmn.day" /></option>
          <option value="3">3<gsmsg:write key="cmn.day" /></option>
          <option value="4">4<gsmsg:write key="cmn.day" /></option>
          <option value="5">5<gsmsg:write key="cmn.day" /></option>
          <option value="6">6<gsmsg:write key="cmn.day" /></option>
          <option value="7">7<gsmsg:write key="cmn.day" /></option>
          <option value="8">8<gsmsg:write key="cmn.day" /></option>
          <option value="9">9<gsmsg:write key="cmn.day" /></option>
          <option value="10">10<gsmsg:write key="cmn.day" /></option>
          <option value="11">11<gsmsg:write key="cmn.day" /></option>
          <option value="12">12<gsmsg:write key="cmn.day" /></option>
          <option value="13">13<gsmsg:write key="cmn.day" /></option>
          <option value="14">14<gsmsg:write key="cmn.day" /></option>
          <option value="15">15<gsmsg:write key="cmn.day" /></option>
          <option value="16">16<gsmsg:write key="cmn.day" /></option>
          <option value="17">17<gsmsg:write key="cmn.day" /></option>
          <option value="18">18<gsmsg:write key="cmn.day" /></option>
          <option value="19">19<gsmsg:write key="cmn.day" /></option>
          <option value="20">20<gsmsg:write key="cmn.day" /></option>
          <option value="21">21<gsmsg:write key="cmn.day" /></option>
          <option value="22">22<gsmsg:write key="cmn.day" /></option>
          <option value="23">23<gsmsg:write key="cmn.day" /></option>
          <option value="24">24<gsmsg:write key="cmn.day" /></option>
          <option value="25">25<gsmsg:write key="cmn.day" /></option>
          <option value="26">26<gsmsg:write key="cmn.day" /></option>
          <option value="27">27<gsmsg:write key="cmn.day" /></option>
          <option value="28">28<gsmsg:write key="cmn.day" /></option>
          <option value="29">29<gsmsg:write key="cmn.day" /></option>
          <option value="30">30<gsmsg:write key="cmn.day" /></option>
          <option value="31">31<gsmsg:write key="cmn.day" /></option>
        </select>

        </div>

        <br>
        <br clear="all">
        <hr>
        <input type="checkbox" name="holSetCheck" value="<gsmsg:write key="main.holiday.setting" />" id="holSet" checked><label for="holSet"><b><gsmsg:write key="project.lef.holiday" /></b></label>


    </fieldset>
</div>

<div id="dialog-error" title="<gsmsg:write key="cmn.warning" />" style="display:none">
 <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
   <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.select.3" arg0="TODO" />
 </p>
</div>

</html:form>

</body>
</html:html>