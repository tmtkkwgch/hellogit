<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<gsmsg:define id="projectTitle" msgkey="cmn.project" />
<gsmsg:define id="title" msgkey="cmn.title" />
<gsmsg:define id="todoNum" msgkey="project.prj050.5" />
<gsmsg:define id="todoTanto" msgkey="cmn.staff" />
<gsmsg:define id="todoWeight" msgkey="project.prj050.4" />
<gsmsg:define id="todoStatus" msgkey="cmn.status" />
<gsmsg:define id="todoStartPlan" msgkey="project.100" />
<gsmsg:define id="todoLimitPlan" msgkey="project.src.66" />
<%-- CMD定数 --%>
<%  String todoAddClick      = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_TODO_ADD_CLICK;
    String todoNameClick     = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_TODO_REF_CLICK;
    String prjSelectMember   = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_SCT_MEM;
    String prjSelectAddUser  = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_SCT_ADD_USR;
    String searchClick       = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_SEARCH;
    String export            = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_EXPORT;
    String prev              = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_PAGE_PREVEW;
    String next              = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_PAGE_NEXT;
    String backClick         = jp.groupsession.v2.prj.prj070.Prj070Action.CMD_BACK_CLICK;

    String low               = String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_LOW);
    String middle            = String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_MIDDLE);
    String high              = String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_HIGH);

    String id_low            = "juyou" + low;
    String id_middle         = "juyou" + middle;
    String id_high           = "juyou" + high;

  int[] sortKeyList01 = new int[] {
                       jp.groupsession.v2.prj.GSConstProject.SORT_PROJECT_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_NO,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_TITLE,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_WEIGHT,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_STATUS,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_START_PLAN,
                       jp.groupsession.v2.prj.GSConstProject.SORT_TODO_LIMIT_PLAN
                       };
  String[] title_width01 = new String[] { "20", "5", "32", "5", "6", "9", "9"};

  String[] titleList01 = new String[] {
                        projectTitle,
                        todoNum,
                        title,
                        todoWeight,
                        todoStatus,
                        todoStartPlan,
                        todoLimitPlan
                       };

  String prj070 = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ070;
  String mode_add = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;
%>

<%-- ソートオーダー --%>
<%  int order_desc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC;
    int order_asc = jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC;  %>

<bean:define id="orderKey" name="prj070Form" property="prj070order" />
<bean:define id="sortKbn" name="prj070Form" property="prj070sort" />
<% int iOrderKey = ((Integer) orderKey).intValue(); %>
<% int iSortKbn = ((Integer) sortKbn).intValue(); %>

<%-- キーワード検索区分 --%>
<%
  String keyWordAnd    = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEY_WORD_KBN_AND);
  String keyWordOr     = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEY_WORD_KBN_OR);
%>

<%-- 検索対象 --%>
<%
  String targetTitle   = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEARCH_TARGET_TITLE);
  String targetNaiyou  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEARCH_TARGET_NAIYOU);
%>
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
<script language="JavaScript" src="../project/js/prj070.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/project/prj070">

<input type="hidden" name="CMD" id="CMD" value="">
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010Init" />
<html:hidden property="prj010order" />
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

<logic:notEmpty name="prj070Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj070Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj040svScMemberSid" scope="request">
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
<html:hidden property="selectingDate" />
<html:hidden property="selectingStatus" />
<html:hidden property="selectingCategory" />
<html:hidden property="selectingMember" />

<logic:notEmpty name="prj070Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj070scrId" />
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
<html:hidden property="prj070sort" />
<html:hidden property="prj070order" />
<html:hidden property="prj070searchFlg" />
<html:hidden property="prj070InitFlg" />

<logic:notEmpty name="prj070Form" property="prj070scTantou" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj070scTantou" scope="request">
    <input type="hidden" name="prj070scTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj070Form" property="prj070scTourokusya" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj070scTourokusya" scope="request">
    <input type="hidden" name="prj070scTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj070Form" property="prj070svScTantou" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj070svScTantou" scope="request">
    <input type="hidden" name="prj070svScTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj070Form" property="prj070svScTourokusya" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj070svScTourokusya" scope="request">
    <input type="hidden" name="prj070svScTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj070Form" property="prj070svScJuuyou" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj070svScJuuyou" scope="request">
    <input type="hidden" name="prj070svScJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj070Form" property="prj070SvSearchTarget" scope="request">
  <logic:iterate id="item" name="prj070Form" property="prj070SvSearchTarget" scope="request">
    <input type="hidden" name="prj070SvSearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj050scrId" value="<%= prj070 %>" />
<html:hidden property="prj050cmdMode" value="<%= mode_add %>" />

<html:hidden property="prj060scrId" value="<%= prj070 %>" />
<html:hidden property="prj060prjSid" value="" />
<html:hidden property="prj060todoSid" value="" />

<html:hidden property="selectDir" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.123" /> ]</td>
  <td width="100%" class="header_white_bg"></td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>

  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td align="right">
    <input type="button" name="btn_prjadd" class="btn_add_n2" value="<gsmsg:write key="project.prj070.1" />" onclick="buttonPush('<%= todoAddClick %>');">
    <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('<%= backClick %>');">
  </td>
  </tr>
  </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

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
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.project" /></th>
  <td width="40%" class="td_sub_detail">
    <html:select property="prj070scPrjSid" styleClass="select01" onchange="changeProject();">
      <html:optionsCollection name="prj070Form" property="projectLabel" value="value" label="label" />
    </html:select>
  </td>
  <!-- 管理番号入力欄 -->
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="project.prj050.5" /></th>
  <td class="td_sub_detail" colspan="1">
    <html:text property="prj070scKanriNumber" styleClass="text_base" maxlength="8" style="width:81px;" />
  </td>
  </tr>

  <tr>
  <th class="td_gray text_bb2"><gsmsg:write key="project.prj050.4" /></th>
  <td class="td_sub_detail">
    <html:multibox name="prj070Form" property="prj070scJuuyou" styleId="<%= id_low %>">
      <%= low %>
    </html:multibox><label for="<%= id_low %>" onclick="return clickLabel(this);"><gsmsg:write key="project.58" />&nbsp;<img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />" class="prj_img_star"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" class="prj_img_star"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" class="prj_img_star"></label>&nbsp;
    <html:multibox name="prj070Form" property="prj070scJuuyou" styleId="<%= id_middle %>">
      <%= middle %>
    </html:multibox><label for="<%= id_middle %>" onclick="return clickLabel(this);"><gsmsg:write key="project.59" />&nbsp;<img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" class="prj_img_star"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" class="prj_img_star"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />" class="prj_img_star"></label>&nbsp;
    <html:multibox name="prj070Form" property="prj070scJuuyou" styleId="<%= id_high %>">
      <%= high %>
    </html:multibox><label for="<%= id_high %>" onclick="return clickLabel(this);"><gsmsg:write key="project.60" />&nbsp;<img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" class="prj_img_star"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" class="prj_img_star"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" class="prj_img_star"></label>
  </td>

  <% String tantoRow = ""; %>
  <logic:notEmpty name="prj070Form" property="categoryLabel">
  <% tantoRow = " rowspan=\"2\""; %>
  </logic:notEmpty>

  <th width="10%" class="td_gray text_bb2" nowrap<%= tantoRow %>><gsmsg:write key="project.113" /></th>
  <td width="40%" class="td_sub_detail"<%= tantoRow %>>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.select2" />" onclick="buttonPush('<%= prjSelectMember %>');">&nbsp;&nbsp;
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.clear" />" onClick="clearUserList();"><br>
    <span id="selectMember">
    <logic:notEmpty name="prj070Form" property="memberList" scope="request">
    <logic:iterate id="memMdl" name="prj070Form" property="memberList" scope="request">
    <bean:write name="memMdl" property="usiSei" />&nbsp;<bean:write name="memMdl" property="usiMei" />&nbsp;&nbsp;
    </logic:iterate>
    </logic:notEmpty>
    </span>
  </td>
  </tr>

  <logic:notEmpty name="prj070Form" property="categoryLabel">
  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.label" /></th>
  <td width="40%" class="td_sub_detail">
    <html:select property="prj070scCategorySid" styleClass="select01">
      <html:optionsCollection name="prj070Form" property="categoryLabel" value="value" label="label" />
    </html:select>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="project.prj070.2" /></th>
  <td class="td_sub_detail" nowrap>
    <html:select property="prj070scKaisiYoteiYear" styleId="KaisiYoteiYear">
      <html:optionsCollection name="prj070Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scKaisiYoteiMonth" styleId="KaisiYoteiMonth">
      <html:optionsCollection name="prj070Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scKaisiYoteiDay" styleId="KaisiYoteiDay">
      <html:optionsCollection name="prj070Form" property="dayLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendar($('#KaisiYoteiDay')[0], $('#KaisiYoteiMonth')[0], $('#KaisiYoteiYear')[0])" class="calendar_btn">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('KaisiYoteiDay', 'KaisiYoteiMonth', 'KaisiYoteiYear')">
  </td>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="project.prj070.4" /></th>
  <td class="td_sub_detail" nowrap>
    <html:select property="prj070scKigenYear" styleId="KigenYear">
      <html:optionsCollection name="prj070Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scKigenMonth" styleId="KigenMonth">
      <html:optionsCollection name="prj070Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scKigenDay" styleId="KigenDay">
      <html:optionsCollection name="prj070Form" property="dayLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn($('#KigenDay')[0], $('#KigenMonth')[0], $('#KigenYear')[0], 'prj070_2')" class="calendar_btn", id="prj070_2">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('KigenDay', 'KigenMonth', 'KigenYear')">
  </td>
  </tr>

  <tr>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="project.prj070.5" /></th>
  <td class="td_sub_detail" nowrap>
    <html:select property="prj070scKaisiJissekiYear" styleId="KaisiJissekiYear">
      <html:optionsCollection name="prj070Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scKaisiJissekiMonth" styleId="KaisiJissekiMonth">
      <html:optionsCollection name="prj070Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scKaisiJissekiDay" styleId="KaisiJissekiDay">
      <html:optionsCollection name="prj070Form" property="dayLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn($('#KaisiJissekiDay')[0], $('#KaisiJissekiMonth')[0], $('#KaisiJissekiYear')[0], 'prj070_1')" class="calendar_btn", id="prj070_1">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('KaisiJissekiDay', 'KaisiJissekiMonth', 'KaisiJissekiYear')">
  </td>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="project.prj070.6" /></th>
  <td class="td_sub_detail" nowrap>
    <html:select property="prj070scSyuryoJissekiYear" styleId="SyuryoJissekiYear">
      <html:optionsCollection name="prj070Form" property="yearLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scSyuryoJissekiMonth" styleId="SyuryoJissekiMonth">
      <html:optionsCollection name="prj070Form" property="monthLabel" value="value" label="label" />
    </html:select>
    <html:select property="prj070scSyuryoJissekiDay" styleId="SyuryoJissekiDay">
      <html:optionsCollection name="prj070Form" property="dayLabel" value="value" label="label" />
    </html:select>
    <input type="button" value="Cal" onclick="wrtCalendarByBtn($('#SyuryoJissekiDay')[0], $('#SyuryoJissekiMonth')[0], $('#SyuryoJissekiYear')[0], 'prj070_3')" class="calendar_btn", id="prj070_3">
    <input type="button" value="<gsmsg:write key="cmn.without.specifying" />" class="btn_base0" onclick="clearDate('SyuryoJissekiDay', 'SyuryoJissekiMonth', 'SyuryoJissekiYear')">
  </td>
  </tr>

  <tr>
  <th class="td_gray text_bb2"><gsmsg:write key="cmn.keyword" /></th>
  <td class="td_sub_detail">
    <html:text property="prj070scTitle" styleClass="text_base" maxlength="100" style="width:281px;" />
    <div class="text_base2">
    <html:radio name="prj070Form" property="prj070KeyWordkbn" value="<%= keyWordAnd %>" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
    <html:radio name="prj070Form" property="prj070KeyWordkbn" value="<%= keyWordOr %>" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
    </div>
  </td>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.search2" /></th>
    <td class="td_sub_detail">
      <html:multibox styleId="search_scope_01" name="prj070Form" property="prj070SearchTarget" value="<%= targetTitle %>" /><label for="search_scope_01"><gsmsg:write key="cmn.title" /></label>
      <html:multibox styleId="search_scope_02" name="prj070Form" property="prj070SearchTarget" value="<%= targetNaiyou %>" /><label for="search_scope_02"><gsmsg:write key="cmn.content" /></label>
  </td>
  </tr>

  <tr>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.registant" /></th>
  <td class="td_sub_detail" colspan="1">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.select2" />" onclick="buttonPush('<%= prjSelectAddUser %>');">&nbsp;&nbsp;
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.clear" />" onClick="clearAddUserList();"><br>
    <span id="selectAddUser">
    <logic:notEmpty name="prj070Form" property="addUserList" scope="request">
    <logic:iterate id="memMdl" name="prj070Form" property="addUserList" scope="request">
    <bean:write name="memMdl" property="usiSei" />&nbsp;<bean:write name="memMdl" property="usiMei" />&nbsp;&nbsp;
    </logic:iterate>
    </logic:notEmpty>
    </span>
  </td>
  <th class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.status" /></th>
  <td class="td_sub_detail" nowrap>
    <html:text property="prj070scStatusFrom" styleClass="text_base" maxlength="3" style="width:46px;" />%<gsmsg:write key="tcd.153" />
    <html:text property="prj070scStatusTo" styleClass="text_base" maxlength="3" style="width:46px;" />%
  </td>
  </tr>
  </table>

  <div><img src="../common/images/spacer.gif" width="1" height="10"></div>
  <div align="right" class="seach_function_l"><input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onclick="buttonPush('<%= searchClick %>');"></div>

  <div align="right" class="seach_function_r">
<logic:notEmpty name="prj070Form" property="projectList" scope="request">
  <input type="button" name="export" value="<gsmsg:write key="cmn.export" />" class="btn_csv_n2" onclick="buttonPush('<%= export %>');">
</logic:notEmpty>
  </div>

  <br class="clear">
  <br>

<logic:notEmpty name="prj070Form" property="projectList" scope="request">

  <table width="100%" cellpadding="5" cellspacing="0">
  <tr>
  <td align="right">
    <bean:size id="count1" name="prj070Form" property="pageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= prev %>');">
    <html:select property="prj070page1" onchange="changePage(1);" styleClass="text_i">
      <html:optionsCollection name="prj070Form" property="pageLabel" value="value" label="label" />
    </html:select>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= next %>');">
    </logic:greaterThan>
  </td>
  </tr>
  </table>

  <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
  <tr class="table_bg_7D91BD_search">
  <th width="4%">&nbsp;</th>
<%
    for (int i = 0; i < sortKeyList01.length; i++) {
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

  <logic:notEmpty name="prj070Form" property="projectList" scope="request">
  <logic:iterate id="prjMdl" name="prj070Form" property="projectList" scope="request" indexId="idx">
  <bean:define id="backclass" value="td_line_color" />
  <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
  <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />

  <tr class="<%= String.valueOf(back) %>">
  <td class="prj_td" align="center">
    <logic:equal name="prjMdl" property="keikoku" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KEIKOKU_ARI) %>">
    <img src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />">
    </logic:equal>
  </td>
  <td class="prj_td" align="left">
    <logic:equal name="prjMdl" property="prjBinSid" value="0">
      <img src="../project/images/prj_icon.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" class="prj_img_ico">
    </logic:equal>
    <logic:notEqual name="prjMdl" property="prjBinSid" value="0">
      <img src="../project/prj070.do?CMD=getImageFile&prj010PrjSid=<bean:write name="prjMdl" property="projectSid" />&prj010PrjBinSid=<bean:write name="prjMdl" property="prjBinSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');" class="prj_img_ico">
    </logic:notEqual>
    <span class="text_blue"><bean:write name="prjMdl" property="projectName" /></span>
  </td>
  <td class="prj_td" align="center"><span class="text_blue"><bean:write name="prjMdl" property="strKanriNo" /></span></td>
  <td class="prj_td"><a href="javascript:void(0)" onClick="return viewTodo('<%= todoNameClick %>', '<bean:write name="prjMdl" property="projectSid" />', '<bean:write name="prjMdl" property="todoSid" />');"><span class="text_blue"><bean:write name="prjMdl" property="todoTitle" filter="false" /></span></a></td>

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

  <td class="prj_td" align="center"><span class="text_blue"><bean:write name="prjMdl" property="rate" />%<br>(<bean:write name="prjMdl" property="statusName" />)</span></td>
  <td class="prj_td" align="center" nowrap><span class="text_blue"><bean:write name="prjMdl" property="strStartDate" /></span></td>
  <td class="prj_td" align="center" nowrap><span class="text_blue"><bean:write name="prjMdl" property="strEndDate" /></span></td>
  </tr>

  </logic:iterate>
  </logic:notEmpty>
  </table>

  <table width="100%" cellpadding="5" cellspacing="0">
  <tr>
  <td align="right">
    <logic:greaterThan name="count1" value="1">
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= prev %>');">
    <html:select property="prj070page2" onchange="changePage(2);" styleClass="text_i">
      <html:optionsCollection name="prj070Form" property="pageLabel" value="value" label="label" />
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