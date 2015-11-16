<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%
      try{
%>

<%-- CMD定数 --%>
<%
    String okClick          = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_OK_CLICK;
    String deleteClick      = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_DEL_CLICK;
    String backClick        = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_BACK_CLICK;
    String memberAdd        = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_MEMBER_ADD_CLICK;
    String memberRemove     = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_MEMBER_REMOVE_CLICK;
    String attachRemove     = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_ATTACH_REMOVE_CLICK;
    String backRedraw       = jp.groupsession.v2.prj.prj050.Prj050Action.CMD_BACK_REDRAW;
    String entryAdd         = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;
    String entryEdit        = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_EDIT;
%>
<gsmsg:define id="textKbnAdmin" msgkey="project.src.55" />
<gsmsg:define id="textKbnParticipation" msgkey="project.src.56" />
<%

    String low              = String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_LOW);
    String middle           = String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_MIDDLE);
    String high             = String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_HIGH);

    String id_low           = "juyou" + low;
    String id_middle        = "juyou" + middle;
    String id_high          = "juyou" + high;

    String kbn_all          = String.valueOf(jp.groupsession.v2.prj.GSConstProject.PRJ_KBN_ADMIN);
    String kbn_sanka        = String.valueOf(jp.groupsession.v2.prj.GSConstProject.PRJ_KBN_PARTICIPATION);
    String kbn_all_str      = textKbnAdmin;
    String kbn_sanka_str    = textKbnParticipation;

    String pluginId         = jp.groupsession.v2.prj.GSConstProject.PLUGIN_ID_PROJECT;

    String maxLengthContent = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_CONTENT);
    String maxLengthReason  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_STATUS_REASON);

%>

<bean:define id="prjStsMdl" name="prj050Form" property="prjStatusMdl" />

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj050.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<logic:equal name="prj050Form" property="prj050elementKbn" value="1">
  <body class="body_03" onunload="calWindowClose();windowClose();" onload="showLengthId($('#inputstr2')[0], <%= maxLengthContent %>, 'inputlength2');showLengthId($('#inputstr3')[0], <%= maxLengthReason %>, 'inputlength3');">
</logic:equal>
<logic:equal name="prj050Form" property="prj050elementKbn" value="0">
  <body class="body_03" onunload="calWindowClose();windowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');">
</logic:equal>

<html:form action="/project/prj050">
<input type="hidden" name="helpPrm" value="<bean:write name="prj050Form" property="prj050cmdMode" />">

<input type="hidden" name="CMD" value="okClick">
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

<logic:notEmpty name="prj050Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj040svScMemberSid" scope="request">
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

<logic:notEmpty name="prj050Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj050elementKbn" />

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

<logic:notEmpty name="prj050Form" property="prj070scTantou" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070scTantou" scope="request">
    <input type="hidden" name="prj070scTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070scTourokusya" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070scTourokusya" scope="request">
    <input type="hidden" name="prj070scTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070svScTantou" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070svScTantou" scope="request">
    <input type="hidden" name="prj070svScTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070svScTourokusya" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070svScTourokusya" scope="request">
    <input type="hidden" name="prj070svScTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070svScJuuyou" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070svScJuuyou" scope="request">
    <input type="hidden" name="prj070svScJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070scJuuyou" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070scJuuyou" scope="request">
    <input type="hidden" name="prj070scJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070SearchTarget" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070SearchTarget" scope="request">
    <input type="hidden" name="prj070SearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050Form" property="prj070SvSearchTarget" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj070SvSearchTarget" scope="request">
    <input type="hidden" name="prj070SvSearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj060scrId" />
<html:hidden property="prj060prjSid" />
<html:hidden property="prj060todoSid" />
<html:hidden property="prj060comment" />
<html:hidden property="prj060status" />
<html:hidden property="prj060riyu" />

<html:hidden property="prj060SelectYearFr" />
<html:hidden property="prj060SelectMonthFr" />
<html:hidden property="prj060SelectDayFr" />
<html:hidden property="prj060SelectYearTo" />
<html:hidden property="prj060SelectMonthTo" />
<html:hidden property="prj060SelectDayTo" />
<html:hidden property="prj060ResultKosu" />

<html:hidden property="prj060scrId" />
<html:hidden property="prj060prjSid" />
<html:hidden property="prj060todoSid" />
<html:hidden property="prj060schUrl" />

<html:hidden property="prj050scrId" />
<html:hidden property="prj050cmdMode" />
<html:hidden property="prj050todoSid" />
<html:hidden property="prj050prjMyKbn" />
<html:hidden property="prj050dspKbn" />

<logic:notEmpty name="prj050Form" property="prj050hdnTanto" scope="request">
  <logic:iterate id="item" name="prj050Form" property="prj050hdnTanto" scope="request">
    <input type="hidden" name="prj050hdnTanto" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="selectDir" />

<logic:equal name="prj050Form" property="prj050elementKbn" value="0">
  <html:hidden property="prj050PrjListKbn" />
  <html:hidden property="prj050cate" />
  <html:hidden property="prj050kaisiYoteiYear" />
  <html:hidden property="prj050kaisiYoteiMonth" />
  <html:hidden property="prj050kaisiYoteiDay" />
  <html:hidden property="prj050yoteiKosu" />
  <html:hidden property="prj050kaisiJissekiYear" />
  <html:hidden property="prj050kaisiJissekiMonth" />
  <html:hidden property="prj050kaisiJissekiDay" />
  <html:hidden property="prj050syuryoJissekiYear" />
  <html:hidden property="prj050syuryoJissekiMonth" />
  <html:hidden property="prj050syuryoJissekiDay" />
  <html:hidden property="prj050jissekiKosu" />
  <html:hidden property="prj050status" />
  <html:hidden property="prj050statusCmt" />
  <html:hidden property="prj050keikoku" />
  <html:hidden property="prj050MailSend" />
</logic:equal>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="82%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>

    <logic:equal name="prj050Form" property="prj050cmdMode" value="<%= entryAdd %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.32" /> ]</td>
    </logic:equal>
    <logic:equal name="prj050Form" property="prj050cmdMode" value="<%= entryEdit %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.56" /> ]</td>
    </logic:equal>

    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <logic:equal name="prj050Form" property="prj050elementKbn" value="0">
      <td width="50%">
        <div style="padding-top:5px;">
          <table>
          <tr>
          <td class="td_todo_add_not_sel" onClick="buttonPush('toDetail');">
            <gsmsg:write key="project.prj050.1" />
          </td>
          <td class="td_todo_add_sel">
            <gsmsg:write key="project.prj050.2" />
          </td>
          </tr>
          </table>
        </div>
      </td>
    </logic:equal>
    <logic:equal name="prj050Form" property="prj050elementKbn" value="1">
      <td width="50%">
        <div style="padding-top:5px;">
          <table>
          <tr>
          <td class="td_todo_add_sel">
            <gsmsg:write key="project.prj050.1" />
          </td>
          <td  class="td_todo_add_not_sel" onClick="buttonPush('toEasy');">
            <gsmsg:write key="project.prj050.2" />
          </td>
          </tr>
          </table>
        </div>
      </td>

    </logic:equal>

    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      <logic:equal name="prj050Form" property="prj050cmdMode" value="<%= entryEdit %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table width="100%">
    <tr><td colspan="4" align="left"><html:errors/></td></tr>
    </table>
    </logic:messagesPresent>

    <logic:equal name="prj050Form" property="prj050elementKbn" value="0">
    <html:hidden property="prj050statusCmtEasy" />
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" /><span class="text_r2">※</span></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050prjSid" styleClass="select01" onchange="buttonPush('changeProject');">
        <html:optionsCollection name="prj050Form" property="projectLabel" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj050titleEasy" style="width:633px;" maxlength="100" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.111" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050kigenYearEasy" styleId="prj050kigenYearEasy">
         <html:optionsCollection name="prj050Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kigenMonthEasy" styleId="prj050kigenMonthEasy">
         <html:optionsCollection name="prj050Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kigenDayEasy" styleId="prj050kigenDayEasy">
         <html:optionsCollection name="prj050Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj050kigenDayEasy')[0], $('#prj050kigenMonthEasy')[0], $('#prj050kigenYearEasy')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj050kigenYearEasy')[0], $('#prj050kigenMonthEasy')[0], $('#prj050kigenDayEasy')[0], 1, 2)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj050kigenYearEasy')[0], $('#prj050kigenMonthEasy')[0], $('#prj050kigenDayEasy')[0], 2, 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj050kigenYearEasy')[0], $('#prj050kigenMonthEasy')[0], $('#prj050kigenDayEasy')[0], 3, 2)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj050kigenDayEasy', 'prj050kigenMonthEasy', 'prj050kigenYearEasy')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.4" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj050juyouEasy" styleId="<%= id_low %>" value="<%= low %>" /><span class="text_base2"><label for="<%= id_low %>" class="text_base2" onclick="return clickLabel(this);"><gsmsg:write key="project.58" />&nbsp;<img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:bottom;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:bottom;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:bottom;"></label></span>&nbsp;
      <html:radio property="prj050juyouEasy" styleId="<%= id_middle %>" value="<%= middle %>" /><span class="text_base2"><label for="<%= id_middle %>" class="text_base2" onclick="return clickLabel(this);"><gsmsg:write key="project.59" />&nbsp;<img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:bottom;"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:bottom;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:bottom;"></label></span>&nbsp;
      <html:radio property="prj050juyouEasy" styleId="<%= id_high %>" value="<%= high %>" /><span class="text_base2"><label for="<%= id_high %>" class="text_base2" onclick="return clickLabel(this);"><gsmsg:write key="project.60" />&nbsp;<img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:bottom;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:bottom;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:bottom;"></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="td_sub_detail"><textarea name="prj050naiyoEasy" style="width:643px;" rows="5" class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength');" id="inputstr"><bean:write name="prj050Form" property="prj050naiyoEasy" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    </table>
    </logic:equal>






    <logic:equal name="prj050Form" property="prj050elementKbn" value="1">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" /><span class="text_r2">※</span></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050prjSid" styleClass="select01" onchange="buttonPush('changeProject');">
        <html:optionsCollection name="prj050Form" property="projectLabel" value="value" label="label" />
      </html:select>

      <logic:equal name="prj050Form" property="admin" value="true">
        &nbsp;

        <html:radio property="prj050PrjListKbn" styleId="kbn_all" value="<%= kbn_all %>" onclick="return buttonPush('changeProject');" /><span class="text_base2"><label for="kbn_all" class="text_base2"><%= kbn_all_str %></label></span>&nbsp;
        <html:radio property="prj050PrjListKbn" styleId="kbn_sanka" value="<%= kbn_sanka %>" onclick="return buttonPush('changeProject');" /><span class="text_base2"><label for="kbn_sanka" class="text_base2"><%= kbn_sanka_str %></label></span>

      </logic:equal>

    </td>
    </tr>

    <logic:equal name="prj050Form" property="prj050cmdMode" value="<%= entryEdit %>">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.5" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <logic:notEmpty name="prj050Form" property="projectItem">
      <bean:define id="prjMdl" name="prj050Form" property="projectItem" />
      <span class="text_base_prj"><bean:write name="prjMdl" property="strKanriNo" /></span>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td class="td_sub_detail" align="left">
      <logic:notEmpty name="prj050Form" property="projectItem">
      <bean:define id="prjMdl" name="prj050Form" property="projectItem" />
      <span class="text_base_prj"><bean:write name="prjMdl" property="addUserSei" />&nbsp;<bean:write name="prjMdl" property="addUserMei" /></span>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj050title" style="width:633px;" maxlength="100" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.label" /></span></td>
    <td align="left" class="td_sub_detail">

      <logic:notEmpty name="prj050Form" property="prj050CategoryList">
        <logic:iterate id="category" name="prj050Form" property="prj050CategoryList" indexId="idx">

          <% if (idx.intValue() > 0 && idx.intValue() % 5 == 0) { %><br><% } %>
          <bean:define id="categorySid" name="category" property="ptcCategorySid" />
          <bean:define id="categoryName" name="category" property="ptcName" />
          <bean:define id="idname" value="<%= String.valueOf(categorySid) %>" />

          <html:radio property="prj050cate" styleId="<%= idname %>" value="<%= String.valueOf(categorySid) %>" /><span class="text_base2"><label for="<%= idname %>" class="text_base2"><bean:write name="categoryName" /></label></span>
          <% if (idx.intValue() % 5 != 4) { %>&nbsp;&nbsp;<% } %>

        </logic:iterate>
      </logic:notEmpty>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" rowspan="3" nowrap><span class="text_bb1"><gsmsg:write key="project.prj010.8" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050kaisiYoteiYear" styleId="prj050kaisiYoteiYear" onchange="setToDayYotei();">
         <html:optionsCollection name="prj050Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kaisiYoteiMonth" styleId="prj050kaisiYoteiMonth" onchange="setToDayYotei();">
         <html:optionsCollection name="prj050Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kaisiYoteiDay" styleId="prj050kaisiYoteiDay" onchange="setToDayYotei();">
         <html:optionsCollection name="prj050Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj050kaisiYoteiDay')[0], $('#prj050kaisiYoteiMonth')[0], $('#prj050kaisiYoteiYear')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj050kaisiYoteiYear')[0], $('#prj050kaisiYoteiMonth')[0], $('#prj050kaisiYoteiDay')[0], 1, 0)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj050kaisiYoteiYear')[0], $('#prj050kaisiYoteiMonth')[0], $('#prj050kaisiYoteiDay')[0], 2, 0)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj050kaisiYoteiYear')[0], $('#prj050kaisiYoteiMonth')[0], $('#prj050kaisiYoteiDay')[0], 3, 0)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj050kaisiYoteiDay', 'prj050kaisiYoteiMonth', 'prj050kaisiYoteiYear')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050kigenYear" styleId="prj050kigenYear" onchange="setToDayYotei();">
         <html:optionsCollection name="prj050Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kigenMonth" styleId="prj050kigenMonth" onchange="setToDayYotei();">
         <html:optionsCollection name="prj050Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kigenDay" styleId="prj050kigenDay" onchange="setToDayYotei();">
         <html:optionsCollection name="prj050Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj050kigenDay')[0], $('#prj050kigenMonth')[0], $('#prj050kigenYear')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj050kigenYear')[0], $('#prj050kigenMonth')[0], $('#prj050kigenDay')[0], 1, 0)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj050kigenYear')[0], $('#prj050kigenMonth')[0], $('#prj050kigenDay')[0], 2, 0)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj050kigenYear')[0], $('#prj050kigenMonth')[0], $('#prj050kigenDay')[0], 3, 0)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj050kigenDay', 'prj050kigenMonth', 'prj050kigenYear')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.33" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj050yoteiKosu" style="width:63px;" maxlength="5" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" rowspan="3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.performance" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050kaisiJissekiYear" styleId="prj050kaisiJissekiYear" onchange="setToDayJisseki();">
         <html:optionsCollection name="prj050Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kaisiJissekiMonth" styleId="prj050kaisiJissekiMonth" onchange="setToDayJisseki();">
         <html:optionsCollection name="prj050Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050kaisiJissekiDay" styleId="prj050kaisiJissekiDay" onchange="setToDayJisseki();">
         <html:optionsCollection name="prj050Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj050kaisiJissekiDay')[0], $('#prj050kaisiJissekiMonth')[0], $('#prj050kaisiJissekiYear')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="moveDay($('#prj050kaisiJissekiYear')[0], $('#prj050kaisiJissekiMonth')[0], $('#prj050kaisiJissekiDay')[0], 1, 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="moveDay($('#prj050kaisiJissekiYear')[0], $('#prj050kaisiJissekiMonth')[0], $('#prj050kaisiJissekiDay')[0], 2, 1)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="moveDay($('#prj050kaisiJissekiYear')[0], $('#prj050kaisiJissekiMonth')[0], $('#prj050kaisiJissekiDay')[0], 3, 1)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="clearDate('prj050kaisiJissekiDay', 'prj050kaisiJissekiMonth', 'prj050kaisiJissekiYear')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050syuryoJissekiYear" styleId="prj050syuryoJissekiYear" onchange="setToDayJisseki();">
         <html:optionsCollection name="prj050Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050syuryoJissekiMonth" styleId="prj050syuryoJissekiMonth" onchange="setToDayJisseki();">
         <html:optionsCollection name="prj050Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj050syuryoJissekiDay" styleId="prj050syuryoJissekiDay" onchange="setToDayJisseki();">
         <html:optionsCollection name="prj050Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj050syuryoJissekiDay')[0], $('#prj050syuryoJissekiMonth')[0], $('#prj050syuryoJissekiYear')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="moveDay($('#prj050syuryoJissekiYear')[0], $('#prj050syuryoJissekiMonth')[0], $('#prj050syuryoJissekiDay')[0], 1, 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="moveDay($('#prj050syuryoJissekiYear')[0], $('#prj050syuryoJissekiMonth')[0], $('#prj050syuryoJissekiDay')[0], 2, 1)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="moveDay($('#prj050syuryoJissekiYear')[0], $('#prj050syuryoJissekiMonth')[0], $('#prj050syuryoJissekiDay')[0], 3, 1)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="clearDate('prj050syuryoJissekiDay', 'prj050syuryoJissekiMonth', 'prj050syuryoJissekiYear')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.33" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj050jissekiKosu" style="width:63px;" maxlength="5" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="td_sub_detail"><textarea name="prj050naiyo" style="width:643px;" rows="5" class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength2');" id="inputstr2"><bean:write name="prj050Form" property="prj050naiyo" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <logic:equal name="prj050Form" property="prj050prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.113" /></span></td>
    <td class="td_sub_detail" align="left">

      <table width="0%" border="0">
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="project.113" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="prj050tanto" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj050Form" property="adminMemberLabel" value="value" label="label" />
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('<%= memberAdd %>');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('<%= memberRemove %>');">
      </td>
      <td>
        <html:select property="prj050member" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj050Form" property="memberLabel" value="value" label="label" />
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.4" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj050juyou" styleId="<%= id_low %>" value="<%= low %>" /><span class="text_base2"><label for="<%= id_low %>" class="text_base2" onclick="return clickLabel(this);"><gsmsg:write key="project.58" />&nbsp;<img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:bottom;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:bottom;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:bottom;"></label></span>&nbsp;
      <html:radio property="prj050juyou" styleId="<%= id_middle %>" value="<%= middle %>" /><span class="text_base2"><label for="<%= id_middle %>" class="text_base2" onclick="return clickLabel(this);"><gsmsg:write key="project.59" />&nbsp;<img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:bottom;"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:bottom;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:bottom;"></label></span>&nbsp;
      <html:radio property="prj050juyou" styleId="<%= id_high %>" value="<%= high %>" /><span class="text_base2"><label for="<%= id_high %>" class="text_base2" onclick="return clickLabel(this);"><gsmsg:write key="project.60" />&nbsp;<img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:bottom;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:bottom;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:bottom;"></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.35" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj050keikoku">
        <html:optionsCollection name="prj050Form" property="keikokuLabel" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">

      <logic:notEmpty name="prjStsMdl" property="todoStatusList">
      <logic:iterate id="prjStatus" name="prjStsMdl" property="todoStatusList">
      <bean:define id="idbase" value="prj050status" />
      <bean:define id="ptsSid" name="prjStatus" property="ptsSid" />
      <bean:define id="idname" value="<%= String.valueOf(idbase) + String.valueOf(ptsSid) %>" />

      <html:radio property="prj050status" styleId="<%= idname %>" value="<%= String.valueOf(ptsSid) %>" /><span class="text_base2"><label for="<%= idname %>" class="text_base2"><bean:write name="prjStatus" property="ptsRate" />%(<bean:write name="prjStatus" property="ptsName" />)</label></span>

      </logic:iterate>
      </logic:notEmpty>

      <br>
      <br><span class="text_base_prj"><gsmsg:write key="project.36" />：</span><br>
      <textarea name="prj050statusCmt" style="width:411px;" rows="3"  class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthReason %>, 'inputlength3');" id="inputstr3"><bean:write name="prj050Form" property="prj050statusCmt" /></textarea><br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength3" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthReason %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
<gsmsg:define id="textNo" msgkey="cmn.no3" />
<gsmsg:define id="textAllMem" msgkey="project130" />
<gsmsg:define id="textCmnStaff" msgkey="cmn.staff" />
<gsmsg:define id="textProjectAdm" msgkey="project.src.32" />
<gsmsg:define id="textProjectLeaderAndTanto" msgkey="project.src.64" />

    <logic:equal name="prj050Form" property="useSmail" value="true">
    <logic:equal name="prj050Form" property="prj050prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="shortmail.notification" /></span></td>
    <td align="left" class="td_sub_detail">
      <span class="text_base2">
        <logic:equal name="prj050Form" property="prj050smailKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_FREE) %>">
          <html:radio property="prj050MailSend" styleId="smail1" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.NOT_SEND) %>" /><label for="smail1"><%= textNo %></label>&nbsp;
          <html:radio property="prj050MailSend" styleId="smail3" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_TANTO) %>" /><label for="smail3"><%= textCmnStaff %></label>&nbsp;
        </logic:equal>
        <html:radio property="prj050MailSend" styleId="smail4" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER) %>" /><label for="smail4"><%= textProjectAdm %></label>&nbsp;
        <html:radio property="prj050MailSend" styleId="smail5" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER_AND_TANTO) %>" /><label for="smail5"><%= textProjectLeaderAndTanto %></label>&nbsp;
        <html:radio property="prj050MailSend" styleId="smail2" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_ALL_MEMBER) %>" /><label for="smail2"><%= textAllMem %></label>
      </span>
    </td>
    </tr>
    </logic:equal>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.110" /></span></td>
    <td align="left" class="td_sub_detail">

      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('prj050tenpu', '<%= pluginId %>', '0', '0');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('<%= attachRemove %>');"><br>

      <html:select property="prj050tenpu" styleClass="select01" multiple="true">
        <html:optionsCollection name="prj050Form" property="fileLabel" value="value" label="label" />
      </html:select>
    </td>
    </tr>
    </table>
    </logic:equal>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      <logic:equal name="prj050Form" property="prj050cmdMode" value="<%= entryEdit %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
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

</html:form>

</body>
</html:html>

<%
        } catch (Exception e) {
            e.printStackTrace();
        }
%>
