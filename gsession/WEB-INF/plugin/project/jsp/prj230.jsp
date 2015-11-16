<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%  String editTodoClick   = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_EDIT_CLICK;
    String deleteClick     = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_DEL_CLICK;
    String backClick       = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_BACK_CLICK;
    String commentClick    = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_COMMENT_CLICK;
    String commentDelClick = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_COMMENT_DELETE_CLICK;
    String updateClick     = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_STATUS_UPDATE_CLICK;
    String statusDelClick  = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_STATUS_DELETE_CLICK;
    String fileDl          = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_FILE_DL;
    String zissekiClick    = jp.groupsession.v2.prj.prj060.Prj060Action.CMD_ZISSEKI_CLICK;

    String keikokuAri  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEIKOKU_ARI);

    String prj060 = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ060;
    String mode_edit = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_EDIT;

    String jkbnToroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU);
%>

<%-- 定数値 --%>

<% String maxLengthReason   = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_STATUS_REASON);
   String maxLengthToDoCmt  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_TODO_CMT);
%>

<bean:define id="prjMdl" name="prj230Form" property="projectItem" />
<bean:define id="prjSid" name="prj230Form" property="prj060prjSid" />
<bean:define id="todoSid" name="prj230Form" property="prj060todoSid" />

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj230.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<logic:equal name="prj230Form" property="todoEdit" value="true">
<body class="body_03" onload="rateChange(-1);moveComment();showLengthId($('#inputstr')[0], <%= maxLengthToDoCmt %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthReason %>, 'inputlength2');" onunload="calWindowClose();">
</logic:equal>
<logic:notEqual name="prj230Form" property="todoEdit" value="true">
<body class="body_03" onload="moveComment();" onunload="calWindowClose();">
</logic:notEqual>

<html:form action="/project/prj230">

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


<html:hidden property="prj060prjSid" />
<html:hidden property="prj060todoSid" />

<logic:notEmpty name="prj230Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj030scrId" />
<html:hidden property="prj030prjSid" />
<html:hidden property="prj030sort" />
<html:hidden property="prj030order" />
<html:hidden property="prj030page1" />
<html:hidden property="prj030page2" />
<html:hidden property="prj030Init" />
<html:hidden property="prjmvComment" />
<html:hidden property="selectingDate" />
<html:hidden property="selectingStatus" />
<html:hidden property="selectingCategory" />
<html:hidden property="selectingMember" />

<logic:notEmpty name="prj230Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj070scrId" />
<html:hidden property="prj070scPrjSid" />
<html:hidden property="prj070scCategorySid" />
<html:hidden property="prj070scStatusFrom" />
<html:hidden property="prj070scStatusTo" />
<html:hidden property="prj070scKaisiYoteiYear" />
<html:hidden property="prj070scKaisiYoteiMonth" />
<html:hidden property="prj070scKaisiYoteiDay" />
<html:hidden property="prj070scKigenYear" />
<html:hidden property="prj070scKigenMonth" />
<html:hidden property="prj070scKigenDay" />
<html:hidden property="prj070scKaisiJissekiYear" />
<html:hidden property="prj070scKaisiJissekiMonth" />
<html:hidden property="prj070scKaisiJissekiDay" />
<html:hidden property="prj070scSyuryoJissekiYear" />
<html:hidden property="prj070scSyuryoJissekiMonth" />
<html:hidden property="prj070scSyuryoJissekiDay" />
<html:hidden property="prj070scTitle" />
<html:hidden property="prj070KeyWordkbn" />
<html:hidden property="prj070svScPrjSid" />
<html:hidden property="prj070svScCategorySid" />
<html:hidden property="prj070svScStatusFrom" />
<html:hidden property="prj070svScStatusTo" />
<html:hidden property="prj070svScKaisiYoteiYear" />
<html:hidden property="prj070svScKaisiYoteiMonth" />
<html:hidden property="prj070svScKaisiYoteiDay" />
<html:hidden property="prj070svScKigenYear" />
<html:hidden property="prj070svScKigenMonth" />
<html:hidden property="prj070svScKigenDay" />
<html:hidden property="prj070svScKaisiJissekiYear" />
<html:hidden property="prj070svScKaisiJissekiMonth" />
<html:hidden property="prj070svScKaisiJissekiDay" />
<html:hidden property="prj070svScSyuryoJissekiYear" />
<html:hidden property="prj070svScSyuryoJissekiMonth" />
<html:hidden property="prj070svScSyuryoJissekiDay" />
<html:hidden property="prj070svScTitle" />
<html:hidden property="prj070SvKeyWordkbn" />
<html:hidden property="prj070page1" />
<html:hidden property="prj070page2" />
<html:hidden property="prj070sort" />
<html:hidden property="prj070order" />
<html:hidden property="prj070searchFlg" />
<html:hidden property="prj070InitFlg" />
<gsmsg:define id="textNo" msgkey="cmn.no3" />
<gsmsg:define id="textAllMem" msgkey="project130" />
<gsmsg:define id="textCmnStaff" msgkey="cmn.staff" />
<gsmsg:define id="textProjectAdm" msgkey="project.src.32" />
<gsmsg:define id="textProjectLeaderAndTanto" msgkey="project.src.64" />
<logic:notEmpty name="prj230Form" property="prj070scTantou" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070scTantou" scope="request">
    <input type="hidden" name="prj070scTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070scTourokusya" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070scTourokusya" scope="request">
    <input type="hidden" name="prj070scTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070svScTantou" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070svScTantou" scope="request">
    <input type="hidden" name="prj070svScTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070svScTourokusya" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070svScTourokusya" scope="request">
    <input type="hidden" name="prj070svScTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070svScJuuyou" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070svScJuuyou" scope="request">
    <input type="hidden" name="prj070svScJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070scJuuyou" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070scJuuyou" scope="request">
    <input type="hidden" name="prj070scJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070SearchTarget" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070SearchTarget" scope="request">
    <input type="hidden" name="prj070SearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj230Form" property="prj070SvSearchTarget" scope="request">
  <logic:iterate id="item" name="prj230Form" property="prj070SvSearchTarget" scope="request">
    <input type="hidden" name="prj070SvSearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj060TodoTitle" />
<html:hidden property="prj060scrId" />
<html:hidden property="prj060prjSid" />
<html:hidden property="prj060todoSid" />
<html:hidden property="prj060schUrl" />

<html:hidden property="prj050scrId" value="<%= prj060 %>" />
<html:hidden property="prj050cmdMode" value="<%= mode_edit %>" />
<html:hidden property="prj050prjSid" value="<%= String.valueOf(prjSid) %>" />
<html:hidden property="prj050todoSid" value="<%= String.valueOf(todoSid) %>" />

<html:hidden property="commentSid" />
<html:hidden property="historySid" />
<html:hidden property="binSid" />
<html:hidden property="selectDir" />




<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
  <td width="0%" class="header_white_bg_text">[<bean:write name="prjMdl" property="projectName" /> <gsmsg:write key="project.prj060.1" /> ]</td>
  <td width="100%" class="header_white_bg">
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <%--
  <tr>
  <td width="70%" valign="bottom">&nbsp;</td>
  <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
  <td class="header_glay_bg" width="30%">
    <logic:equal name="prj230Form" property="todoEdit" value="true">
    <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');">
    <input type="button" value="<gsmsg:write key="cmn.fixed2" />" class="btn_edit_n1" onclick="buttonPush('<%= editTodoClick %>');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
  </td>
  <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
  </tr>
  --%>
  <tr>
  <td colspan="4" width="100%" align="left" class="text_bb4">&nbsp;
    <br>
    <logic:equal name="prjMdl" property="keikoku" value="<%= keikokuAri %>">
      <logic:equal name="prjMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
        <img src="../project/images/icon_todo_30.png" alt="TODO" class="prj_img_todo_normal">&nbsp;
      </logic:equal>
      <logic:notEqual name="prjMdl" property="rate" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.RATE_MAX) %>">
        <img src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />" class="prj_img_warn">&nbsp;
      </logic:notEqual>
    </logic:equal>
    <logic:notEqual name="prjMdl" property="keikoku" value="<%= keikokuAri %>">
      <img src="../project/images/icon_todo_30.png" alt="TODO" class="prj_img_todo_normal">&nbsp;
    </logic:notEqual>
    <bean:write name="prjMdl" property="todoTitle" filter="false" />
  </td>
  </tr>
  </table>

  <logic:messagesPresent message="false">
    <table width="100%">
    <tr><td align="left"><html:errors/></td></tr>
    </table>
  </logic:messagesPresent>

  <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
  <td width="65%" valign="top">

    <table width="100%" class="tl0 table_td_border" border="0" cellpadding="5">

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.5" /></span></td>
    <td align="left" class="td_sub_detail" width="35%"><span class="text_base_prj"><bean:write name="prjMdl" property="strKanriNo" /></span></td>
    <td class="table_bg_A5B4E1" width="12%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_sub_detail" width="28%">
    <logic:equal name="prj230Form" property="addUserStatus" value="<%= jkbnToroku %>">
    <span class="text_base_prj"><bean:write name="prj230Form" property="addUserName" /></span>
    </logic:equal>
    <logic:notEqual name="prj230Form" property="addUserStatus" value="<%= jkbnToroku %>">
    <del><span class="text_base_prj"><bean:write name="prj230Form" property="addUserName" /></span></del>
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj060.2" /></span></td>
    <td align="left" class="td_sub_detail">
      <span class="text_base_prj">
      <bean:write name="prjMdl" property="strStartDate" />

      <% String kikan = ""; %>
      <logic:notEmpty name="prjMdl" property="strStartDate">
      <% kikan = "～"; %>
      </logic:notEmpty>
      <logic:notEmpty name="prjMdl" property="strEndDate">
      <% kikan = "～"; %>
      </logic:notEmpty>
      <%= kikan %>

      <bean:write name="prjMdl" property="strEndDate" />
      </span>
    </td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj060.3" /></span></td>
    <td class="td_sub_detail" nowrap><span class="text_base_prj"><bean:write name="prjMdl" property="strYoteiKosu" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj060.4" /></span></td>
    <td align="left" class="td_sub_detail" nowrap>
      <span class="text_base_prj">

        <bean:write name="prjMdl" property="strStartJissekiDate" />

        <% kikan = ""; %>
        <logic:notEmpty name="prjMdl" property="strStartJissekiDate">
        <% kikan = "～"; %>
        </logic:notEmpty>
        <logic:notEmpty name="prjMdl" property="strEndJissekiDate">
        <% kikan = "～"; %>
        </logic:notEmpty>
        <%= kikan %>

        <bean:write name="prjMdl" property="strEndJissekiDate" />

      </span>
    </td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj060.5" /></span></td>
    <td class="td_sub_detail" valign="top" nowrap>
      <span class="text_base_prj">
        <bean:write name="prjMdl" property="strJissekiKosu" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.label" /></span></td>
    <td class="td_sub_detail" nowrap><span class="text_base_prj"><bean:write name="prjMdl" property="category" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.4" /></span></td>

    <%-- <td class="td_sub_detail" nowrap><span class="text_base_prj"><bean:write name="prjMdl" property="strJuyo" /></span></td> --%>
    <td class="td_sub_detail" nowrap>
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
    </tr>

    <logic:equal name="prjMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.113" /></span></td>
    <td class="td_sub_detail" colspan="3">
      <logic:notEmpty name="prj230Form" property="userList" scope="request">
      <logic:iterate id="memMdl" name="prj230Form" property="userList" scope="request">

        <logic:equal name="memMdl" property="status" value="<%= jkbnToroku %>">
        <span class="text_base_prj"><bean:write name="memMdl" property="sei" />&nbsp;<bean:write name="memMdl" property="mei" /></span>
        </logic:equal>
        <logic:notEqual name="memMdl" property="status" value="<%= jkbnToroku %>">
        <del><span class="text_base_prj"><bean:write name="memMdl" property="sei" />&nbsp;<bean:write name="memMdl" property="mei" /></span></del>
        </logic:notEqual>
        &nbsp;

      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_sub_detail" colspan="3"><span class="text_base_prj"><bean:write name="prjMdl" property="naiyou" filter="false" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td class="td_sub_detail" colspan="3" nowrap>
      <logic:notEmpty name="prj230Form" property="binfList" scope="request">
      <logic:iterate id="fileMdl" name="prj230Form" property="binfList" scope="request">
      <a href="javascript:void(0);" onClick="return fileDl('<%= fileDl %>', '<bean:write name="fileMdl" property="binSid" />');"><span class="text_link"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a><br>
      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>
    </table>

    <br>
<%--
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr valign="center">
    <td>
      <span class="text_base">URL:&nbsp;</span><input type="text" value="<bean:write name="prj230Form" property="prj060TodoUrl" />" size="100" class="text_todoUrl" readOnly="true" />
    </td>
    </tr>
    </table>

    <br>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl0 prj_tbl_base">
    <tr>
    <th align="left" class="prj_todo_comt"><a name="prj060CmtPnt"><img src="../project/images/todo_ref_comment.gif" class="img_bottom">&nbsp;<gsmsg:write key="cmn.comment" /></a></th>
    </tr>

    <tr>
    <td>
      <logic:notEmpty name="prj230Form" property="todoComList" scope="request">
      <logic:iterate id="comMdl" name="prj230Form" property="todoComList" scope="request">
      <div class="text_bg_index" width="100%">
      <logic:equal name="prj230Form" property="todoDelete" value="true">
      <a href="javascript:void(0)" onClick="return deleteCmt('<%= commentDelClick %>', '<bean:write name="comMdl" property="pcmSid" />');"><span class="text_right"><img src="../common/images/delete.gif" alt="<gsmsg:write key="cmn.delete" />" class="img_bottom"></span></a>
      </logic:equal>

      <img src="../project/images/todo_ref_comment_s.gif" class="img_bottom">&nbsp;<bean:write name="comMdl" property="strPcmAdate" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

      <logic:equal name="comMdl" property="status" value="<%= jkbnToroku %>">
      <bean:write name="comMdl" property="sei" />&nbsp;<bean:write name="comMdl" property="mei" />
      </logic:equal>
      <logic:notEqual name="comMdl" property="status" value="<%= jkbnToroku %>">
      <del><bean:write name="comMdl" property="sei" />&nbsp;<bean:write name="comMdl" property="mei" /></del>
      </logic:notEqual>
      </div>
      <div class="text_cmt"><bean:write name="comMdl" property="pcmComment" filter="false" /></div>
      <div class="btm-border-dot"><img src="../project/images/spacer.gif" width="1" height="5"></div>
      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <logic:equal name="prj230Form" property="todoEdit" value="true">
    <tr>
    <td align="center">
      <table class="prj_todo_comadd_bk" width="100%" cellpadding="5">
        <tr>
        <td width="100%">
          <table width="100%">
          <tr>
          <td valign="middle" nowrap><span class="font_string_count"><gsmsg:write key="cmn.comment" /></span></td>
          <td valign="middle" nowrap height="100">
          <textarea name="prj060comment" cols="50" rows="5" class="text_gray textarea_base" onkeyup="showLengthStr(value, <%= maxLengthToDoCmt %>, 'inputlength');" id="inputstr"><bean:write name="prj230Form" property="prj060comment" /></textarea>
          <br>
          <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthToDoCmt %>&nbsp;<gsmsg:write key="cmn.character" /></span>
          </td>
          <td valign="middle"><input type="button" value="<gsmsg:write key="project.122" />" class="btn_add_n1" onclick="buttonPush('<%= commentClick %>');"></td>
          </tr>

          <logic:equal name="prj230Form" property="useSmail" value="true">
          <logic:equal name="prjMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
            <tr>
            <td>&nbsp;</td>
            <td colspan="2" valign="bottom"><span class="font_string_count"><gsmsg:write key="shortmail.notification" /></span></td>
            </tr>
            <tr>

            <tr>
            <td>&nbsp;</td>
            <td colspan="2">
              <span class="text_base">
              <logic:equal name="prj230Form" property="prj060smailKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_FREE) %>">
                <html:radio property="prj060CommentMailSend" styleId="cmtSmail1" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.NOT_SEND) %>" /><label for="cmtSmail1" class="font_string_count"><%= textNo %></label>&nbsp;
                <html:radio property="prj060CommentMailSend" styleId="cmtSmail3" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_TANTO) %>" /><label for="cmtSmail3" class="font_string_count"><%= textCmnStaff %></label>&nbsp;
              </logic:equal>
              <html:radio property="prj060CommentMailSend" styleId="cmtSmail4" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER) %>" /><label for="cmtSmail4" class="font_string_count"><%= textProjectAdm %></label>&nbsp;
              <html:radio property="prj060CommentMailSend" styleId="cmtSmail5" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER_AND_TANTO) %>" /><label for="cmtSmail5" class="font_string_count"><%= textProjectLeaderAndTanto %></label>&nbsp;
              <html:radio property="prj060CommentMailSend" styleId="cmtSmail2" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_ALL_MEMBER) %>" /><label for="cmtSmail2" class="font_string_count"><%= textAllMem %></label>
            </span>
          </td>
          </tr>
          </logic:equal>
          </logic:equal>
          </table>
        </td>
        </tr>
      </table>
    </td>
    </tr>
    </logic:equal>

    </table>
--%>
  </td>

  </tr>

  <tr>
  <td width="35%" align="center" valign="top">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
      <table width="100%" class="tl0" border="0" cellpadding="5">
      <tr>
      <td class="prj_todo_history_bk" width="20%" nowrap>
        <table>
        <tr>
        <td><img src="../project/images/history_icon.gif"></td>
        <td><span class="text_bb1"><gsmsg:write key="project.prj060.6" /></span></td>
        </tr>
        </table>
      </td>
      </tr>
      <tr>
      <td class="td_type1">
        <gsmsg:write key="project.prj060.7" />　：<span class="text_bb2"><bean:write name="prjMdl" property="rate" />%(<bean:write name="prjMdl" property="statusName" />)</span>
        <br>
        <div class="text_cmt3">
        <logic:equal name="prj230Form" property="todoEdit" value="true">
        <bean:define id="prjStsMdl" name="prj230Form" property="prjStatusMdl" />

        <logic:notEmpty name="prjStsMdl" property="todoStatusList">
        <logic:iterate id="prjStatus" name="prjStsMdl" property="todoStatusList">
        <bean:define id="idbase" value="prj060status" />
        <bean:define id="ptsSid" name="prjStatus" property="ptsSid" />
        <bean:define id="idname" value="<%= String.valueOf(idbase) + String.valueOf(ptsSid) %>" />
        <bean:define id="ptsRate" name="prjStatus" property="ptsRate" />

        <html:radio property="prj060status" styleId="<%= idname %>" value="<%= String.valueOf(ptsSid) %>" onclick='<%= "rateChange(" + ptsSid + ")" %>' /><span class="text_base2"><label for="<%= idname %>" class="text_base2"><bean:write name="prjStatus" property="ptsRate" />%(<bean:write name="prjStatus" property="ptsName" />)</label></span>

        </logic:iterate>
        </logic:notEmpty>
        </div>
        <logic:equal name="prj230Form" property="todoEdit" value="true">
          <div id="zisseki_fr" class="prj_pad_tb1">
          <div class="text_base_prj"><gsmsg:write key="project.prj060.8" /></div>

          <span class="text_base_prj"><gsmsg:write key="cmn.start" />：</span>

          <html:select property="prj060SelectYearFr" styleId="prj060SelectYearFr">
            <html:optionsCollection name="prj230Form" property="yearLabel" value="value" label="label" />
          </html:select>

          <html:select property="prj060SelectMonthFr" styleId="prj060SelectMonthFr">
            <html:optionsCollection name="prj230Form" property="monthLabel" value="value" label="label" />
          </html:select>

          <html:select property="prj060SelectDayFr" styleId="prj060SelectDayFr">
            <html:optionsCollection name="prj230Form" property="dayLabel" value="value" label="label" />
          </html:select>
          <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj060SelectDayFr')[0], $('#prj060SelectMonthFr')[0], $('#prj060SelectYearFr')[0]);" class="calendar_btn">
          <input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj060SelectDayFr', 'prj060SelectMonthFr', 'prj060SelectYearFr')">

          <br>　　　
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj060SelectYearFr')[0], $('#prj060SelectMonthFr')[0], $('#prj060SelectDayFr')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj060SelectYearFr')[0], $('#prj060SelectMonthFr')[0], $('#prj060SelectDayFr')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj060SelectYearFr')[0], $('#prj060SelectMonthFr')[0], $('#prj060SelectDayFr')[0], 3)">

        </div>

        <div id="zisseki_to" class="prj_pad_tb1">

          <span class="text_base_prj"><gsmsg:write key="cmn.end" />：</span>

          <html:select property="prj060SelectYearTo" styleId="prj060SelectYearTo">
            <html:optionsCollection name="prj230Form" property="yearLabel" value="value" label="label" />
          </html:select>

          <html:select property="prj060SelectMonthTo" styleId="prj060SelectMonthTo">
            <html:optionsCollection name="prj230Form" property="monthLabel" value="value" label="label" />
          </html:select>

          <html:select property="prj060SelectDayTo" styleId="prj060SelectDayTo">
            <html:optionsCollection name="prj230Form" property="dayLabel" value="value" label="label" />
          </html:select>
          <input type="button" name="dateToBtn" value="Cal" onclick="wrtCalendar($('#prj060SelectDayTo')[0], $('#prj060SelectMonthTo')[0], $('#prj060SelectYearTo')[0]);" class="calendar_btn">
          <input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj060SelectDayTo', 'prj060SelectMonthTo', 'prj060SelectYearTo')">

          <br>　　　
          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj060SelectYearTo')[0], $('#prj060SelectMonthTo')[0], $('#prj060SelectDayTo')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj060SelectYearTo')[0], $('#prj060SelectMonthTo')[0], $('#prj060SelectDayTo')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj060SelectYearTo')[0], $('#prj060SelectMonthTo')[0], $('#prj060SelectDayTo')[0], 3)">

        </div>

        <div id="kosu" class="prj_pad_tb1">
          <span class="text_base_prj"><gsmsg:write key="project.prj060.5" />：</span>
          <html:text name="prj230Form" property="prj060ResultKosu" style="width:63px;" maxlength="5" />
        </div>

        </logic:equal>

        <table cellpadding="0" cellspacing="3" border="0" width="100%">
        <tr valign="bottom">
        <td><span class="text_base"><gsmsg:write key="project.36" /></span></td>
        </tr>
        <tr>
        <td><textarea name="prj060riyu" style="width:311px;" rows="3" class="text_gray textarea_base" onkeyup="showLengthStr(value, <%= maxLengthReason %>, 'inputlength2');" id="inputstr2"><bean:write name="prj230Form" property="prj060riyu" /></textarea>
        <br>
        <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthReason %>&nbsp;<gsmsg:write key="cmn.character" /></span>
        </td>
        </tr>

        <logic:equal name="prj230Form" property="useSmail" value="true">
        <logic:equal name="prjMdl" property="prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
        <tr>
        <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
        </tr>
        <tr valign="bottom">
        <td><span class="text_base"><gsmsg:write key="shortmail.notification" /></span></td>
        </tr>
        <tr>
        <tr valign="middle">
        <td>
          <span class="text_base">
            <logic:equal name="prj230Form" property="prj060smailKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_FREE) %>">
              <html:radio property="prj060MailSend" styleId="smail1" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.NOT_SEND) %>" /><label for="smail1"><%= textNo %></label>&nbsp;
              <html:radio property="prj060MailSend" styleId="smail3" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_TANTO) %>" /><label for="smail3"><%= textCmnStaff %></label>&nbsp;
            </logic:equal>
            <html:radio property="prj060MailSend" styleId="smail4" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER) %>" /><label for="smail4"><%= textProjectAdm %></label>&nbsp;
            <html:radio property="prj060MailSend" styleId="smail5" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER_AND_TANTO) %>" /><label for="smail5"><%= textProjectLeaderAndTanto %></label>&nbsp;
            <html:radio property="prj060MailSend" styleId="smail2" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_ALL_MEMBER) %>" /><label for="smail2"><%= textAllMem %></label>
          </span>
        </td>
        </tr>
        <tr>
        <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
        </tr>
        </logic:equal>
        </logic:equal>

        <tr>
        <td align="center"><input type="button" value="<gsmsg:write key="cmn.update" />" class="btn_edit_n1 changeStatus"></td>
        </tr>
        </table>
        </logic:equal>

      </td>
      </tr>
      </table>
      <br>

    </td>
    </tr>


    <tr>
    <td>
<%--
      <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
      <tr>
      <td class="prj_todo_history_bk" nowrap>
        <table>
        <tr>
        <td><img src="../project/images/history_icon.gif"></td>
        <td><span class="text_bb1"><gsmsg:write key="project.37" /></span></td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td class="td_sub_detail">

        <logic:notEmpty name="prj230Form" property="todoHisList">
        <table class="td_type_prj" width="100%">

        <logic:iterate id="prjHis" name="prj230Form" property="todoHisList">
        <tr>
        <td rowspan="2" nowrap><img src="../project/images/point_arrow.gif" class="img_bottom"></td>
        <td rowspan="2"><span class="text_navy"><bean:write name="prjHis" property="rate" />%(<bean:write name="prjHis" property="statusName" />)</span></td>
        <td nowrap><span class="text_navy"><bean:write name="prjHis" property="strAddDate" /></span></td>
        <td rowspan="2" nowrap>

        <logic:equal name="prj230Form" property="todoDelete" value="true">
        <a href="javascript:void(0)" onClick="return deleteHistory('<%= statusDelClick %>', '<bean:write name="prjHis" property="hisSid" />');"><img src="../common/images/delete.gif" alt="<gsmsg:write key="cmn.delete" />" class="img_bottom"></a>
        </logic:equal>

        </td>
        </tr>

        <tr>
        <td nowrap>
          <span class="text_navy">
          <logic:equal name="prjHis" property="userStatus" value="<%= jkbnToroku %>">
          <bean:write name="prjHis" property="sei" />&nbsp;<bean:write name="prjHis" property="mei" />
          </logic:equal>
          <logic:notEqual name="prjHis" property="userStatus" value="<%= jkbnToroku %>">
          <del><bean:write name="prjHis" property="sei" />&nbsp;<bean:write name="prjHis" property="mei" /></del>
          </logic:notEqual>
          </span>
        </td>
        </tr>

        <tr><td colspan="5"><div class="text_cmt2"><bean:write name="prjHis" property="reason" filter="false" /></div></td></tr>
        </logic:iterate>

        </table>
        </logic:notEmpty>

      </td>
      </tr>
      </table>
--%>
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

<%--
  <!-- ページコンテンツ end -->
  <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

  <table cellpadding="0" width="100%">
  <tr>
  <td align="right">
    <logic:equal name="prj230Form" property="todoEdit" value="true">
    <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');">
    <input type="button" value="<gsmsg:write key="cmn.fixed2" />" class="btn_edit_n1" onclick="buttonPush('<%= editTodoClick %>');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
  </td>
  </tr>
  </table>
--%>
</td>
</tr>
</table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="center">
    <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="return window.close();">
    </td>
    </tr>
    </table>


<div id="dialogChangeStatus" title="状態変更確認" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>状態を変更してもよろしいですか？</b>
    </p>
</div>


</html:form>

</body>
</html:html>
