<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%-- CMD定数 --%>
<%  String okClick         = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_OK_CLICK;
    String template        = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_USE_TEMPLATE;
    String deleteClick     = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_DEL_CLICK;
    String backClick       = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_BACK_CLICK;
    String backRedraw      = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_BACK_REDRAW;
    String prjStatusModify = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_PRJ_STATUS_MODIFY_CLICK;
    String memberAdd       = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_MEMBER_ADD_CLICK;
    String memberRemove    = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_MEMBER_REMOVE_CLICK;
    String adminAdd        = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_ADMIN_ADD_CLICK;
    String adminRemove     = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_ADMIN_REMOVE_CLICK;
    String categoriModify  = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_CATEGORI_MODIFY_CLICK;
    String statusModify    = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_STATUS_MODIFY_CLICK;
    String copyClick       = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_COPY_CLICK;
    String memberEdit      = jp.groupsession.v2.prj.prj020.Prj020Action.CMD_MEMBER_EDIT;
%>

<%-- 定数値 --%>
<%
    String entryAdd         = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;
    String entryEdit        = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_EDIT;
    String dspId            = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ020;
    String kengenMem        = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_EDIT_KENGEN_MEM);
    String kengenAdm        = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_EDIT_KENGEN_ADM);
    String kengenAll        = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_EDIT_KENGEN_ALL);
    String mailKbnFree      = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_FREE);
    String mailKbnAdmin     = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_SEND_ADMIN);
    String enabled          = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_KOUKAI_ENABLED);
    String disabled         = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_KOUKAI_DISABLED);
    String mode_tmp         = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_SELECT);
    String maxLengthTarget  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_TARGET);
    String maxLengthContent = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_CONTENT);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
</head>

<body class="body_03" onunload="calWindowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthTarget %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthContent %>, 'inputlength2');scroll();">

<logic:notEqual name="prj020Form" property="prj020cmdMode" value="<%= entryEdit %>">
<div id="subPanel">
<div class="hd"><gsmsg:write key="project.prj020.1" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="pos" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>
</logic:notEqual>

<div id="FreezePane">

<html:form action="/project/prj020">
<input type="hidden" name="helpPrm" value="<bean:write name="prj020Form" property="prj020cmdMode" />">
<input type="hidden" name="winname" value="subbox">

<input type="hidden" name="CMD" id="CMD" value="">
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="selectingProject" />
<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />
<html:hidden property="prj010Init" />

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

<logic:notEmpty name="prj020Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj020Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj020Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj020Form" property="prj040svScMemberSid" scope="request">
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
<html:hidden property="selectingCategory" />
<html:hidden property="selectingStatus" />
<html:hidden property="selectingMember" />

<logic:notEmpty name="prj020Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj020Form" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj020scrId" />
<html:hidden property="prj020cmdMode" />
<html:hidden property="prj020prjSid" />
<html:hidden property="prj020prjMyKbn" />
<html:hidden property="copyProjectSid" value="" />

<logic:notEmpty name="prj020Form" property="prj020hdnMember" scope="request">
  <logic:iterate id="item" name="prj020Form" property="prj020hdnMember" scope="request">
    <input type="hidden" name="prj020hdnMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj020Form" property="prj020hdnAdmin" scope="request">
  <logic:iterate id="item" name="prj020Form" property="prj020hdnAdmin" scope="request">
    <input type="hidden" name="prj020hdnAdmin" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prjTmpMode" />
<html:hidden property="movedDspId" />
<html:hidden property="selectDir" />

<logic:notEmpty name="prj020Form" property="prj150CompanySid">
  <logic:iterate id="coId" name="prj020Form" property="prj150CompanySid">
    <input type="hidden" name="prj150CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj020Form" property="prj150CompanyBaseSid">
  <logic:iterate id="coId" name="prj020Form" property="prj150CompanyBaseSid">
    <input type="hidden" name="prj150CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj020Form" property="prj150AddressIdSv">
  <logic:iterate id="addressId" name="prj020Form" property="prj150AddressIdSv">
    <input type="hidden" name="prj150AddressIdSv" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="prj020UsrDel" value="">
<html:hidden property="prj020EditDspFlg" />

<html:hidden property="prj020AddMemAllDelFlg" />
<html:hidden property="prj020ScrollFlg" />
<html:hidden property="prj020IcoName" />
<html:hidden property="prj020IcoSaveName" />
<html:hidden property="prj020IcoSetFlg" />
<html:hidden property="prj020initFlg" />

<bean:define id="prjStsMdl" name="prj020Form" property="prjStatusMdl" />

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
    <logic:equal name="prj020Form" property="prj020cmdMode" value="<%= entryAdd %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.131" /> ]</td>
    </logic:equal>
    <logic:equal name="prj020Form" property="prj020cmdMode" value="<%= entryEdit %>">
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.132" /> ]</td>
    </logic:equal>

    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">&nbsp;</td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

      <table align="right">
      <tr>
      <logic:equal name="prj020Form" property="prj020cmdMode" value="<%= entryAdd %>">
        <td><input type="button" value="<gsmsg:write key="cmn.use.template" />" class="btn_base1w" onclick="useTemplate('<%= template %>', '<%= mode_tmp %>');"></td>
        <td><input type="button" value="<gsmsg:write key="project.18" />" class="btn_copy_n1" onclick="return openpos();"></td>
      </logic:equal>
      <td><input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');"></td>
      <logic:equal name="prj020Form" property="prj020cmdMode" value="<%= entryEdit %>">
        <logic:equal name="prj020Form" property="prj020prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
          <td><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');"></td>
        </logic:equal>
      </logic:equal>
      <td><input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');"></td>
      </tr>
      </table>

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

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="project.31" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <html:text property="prj020prjId" style="width:291px;" maxlength="20" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.40" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj020prjName" style="width:633px;" maxlength="70" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.41" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj020prjNameS" style="width:544px;" maxlength="20" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.10" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj020yosan" style="width:153px;" maxlength="14" />&nbsp;<span class="text_base2"><gsmsg:write key="project.103" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.19" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj020koukai" styleId="prj020KoukaiKbn_0" value="<%= enabled %>" /><span class="text_base2"><label for="prj020KoukaiKbn_0" class="text_base2"><gsmsg:write key="cmn.public" /></label></span>&nbsp;
      <html:radio property="prj020koukai" styleId="prj020KoukaiKbn_1" value="<%= disabled %>" /><span class="text_base2"><label for="prj020KoukaiKbn_1" class="text_base2"><gsmsg:write key="cmn.private" /></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj020startYear" styleId="prj020YearStart">
         <html:optionsCollection name="prj020Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj020startMonth" styleId="prj020MonthStart">
         <html:optionsCollection name="prj020Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj020startDay" styleId="prj020DayStart">
         <html:optionsCollection name="prj020Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendar($('#prj020DayStart')[0], $('#prj020MonthStart')[0], $('#prj020YearStart')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj020YearStart')[0], $('#prj020MonthStart')[0], $('#prj020DayStart')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj020YearStart')[0], $('#prj020MonthStart')[0], $('#prj020DayStart')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj020YearStart')[0], $('#prj020MonthStart')[0], $('#prj020DayStart')[0], 3)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj020DayStart', 'prj020MonthStart', 'prj020YearStart')">
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj020endYear" styleId="prj020YearEnd">
         <html:optionsCollection name="prj020Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj020endMonth" styleId="prj020MonthEnd">
         <html:optionsCollection name="prj020Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj020endDay" styleId="prj020DayEnd">
         <html:optionsCollection name="prj020Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateToBtn" value="Cal" onclick="wrtCalendar($('#prj020DayEnd')[0], $('#prj020MonthEnd')[0], $('#prj020YearEnd')[0]);" class="calendar_btn">
      &nbsp;

      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj020YearEnd')[0], $('#prj020MonthEnd')[0], $('#prj020DayEnd')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj020YearEnd')[0], $('#prj020MonthEnd')[0], $('#prj020DayEnd')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj020YearEnd')[0], $('#prj020MonthEnd')[0], $('#prj020DayEnd')[0], 3)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj020DayEnd', 'prj020MonthEnd', 'prj020YearEnd')">
    </td>
    </tr>

    <logic:equal name="prj020Form" property="prj020prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <input type="button" class="btn_base0" value="<gsmsg:write key="project.77" />" onclick="buttonPush('<%= prjStatusModify %>');">
      <br>
      <logic:notEmpty name="prjStsMdl" property="prjStatusList">
      <logic:iterate id="prjStatus" name="prjStsMdl" property="prjStatusList">
      <bean:define id="idbase" value="prj020status" />
      <bean:define id="prsSid" name="prjStatus" property="prsSid" />
      <bean:define id="idname" value="<%= String.valueOf(idbase) + String.valueOf(prsSid) %>" />

      <html:radio property="prj020status" styleId="<%= idname %>" value="<%= String.valueOf(prsSid) %>" /><span class="text_base2"><label for="<%= idname %>" class="text_base2"><bean:write name="prjStatus" property="prsRate" />%(<bean:write name="prjStatus" property="prsName" />)</label></span>

      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.21" /></span></td>
    <td align="left" class="td_sub_detail">
      <textarea name="prj020mokuhyou" style="width:643px;" rows="5" class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthTarget %>, 'inputlength');" id="inputstr"><bean:write name="prj020Form" property="prj020mokuhyou" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthTarget %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="td_sub_detail">
      <textarea name="prj020naiyou" style="width:643px;" rows="5" class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength2');" id="inputstr2"><bean:write name="prj020Form" property="prj020naiyou" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    <a id="add_user" name="add_user"></a>
    </td>
    </tr>


    <logic:equal name="prj020Form" property="prj020prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.squad" /></span>
    </td>
    <td align="left" class="td_sub_detail">

      <table width="0%" border="0">
      <tr>
      <td width="35%" align="center"></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.prj020group, 'prj020group', '<bean:write name="prj020Form" property="prj020group" />', '0', 'backRedraw', 'prj020hdnMember', '-1', '0', '0', '1')" type="button">
      </td>
      <td width="10%" align="left"></td>
      </tr>
      <tr>
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <html:select property="prj020group" styleClass="select01" onchange="buttonPush('backRedraw');">
          <html:optionsCollection name="prj020Form" property="groupLabel" value="value" label="label" />
        </html:select>
      </td>
      <td width="10%" align="left">
        <input type="button" onclick="openGroupWindow(this.form.prj020group, 'prj020group', '0', 'backRedraw')" class="group_btn2" value="&nbsp;&nbsp;" id="prj020GroupBtn">
      </td>

      </tr>

      <tr>
      <td align="center">
        <html:select property="prj020syozokuMember" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj020Form" property="syozokuMemberLabel" value="value" label="label" />
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('<%= memberAdd %>');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('<%= memberRemove %>');">
      </td>
      <td valign="top">
        <html:select property="prj020user" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj020Form" property="userLabel" value="value" label="label" />
        </html:select>
      </td>
      </tr>
      <tr>
      <td colspan="3" align="left"><input type="button" value="<gsmsg:write key="project.1" />" class="btn_base1w" onclick="memberEdit('<%= memberEdit %>', '<%= dspId %>');"></td>
      </tr>
      </table>

    </td>
    </tr>

    <logic:equal name="prj020Form" property="prj020cmdMode" value="0">
    <logic:notEmpty name="prj020Form" property="prj150AddressIdSv" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj020.4" /></span></td>
    <td align="left" class="td_sub_detail">
    <logic:notEmpty name="prj020Form" property="prj020AddDataList">
      <logic:iterate name="prj020Form" property="prj020AddDataList" scope="request" id="companyData">
        <a href="#" onClick="deleteCompany(<bean:write name="companyData" property="adrSid" />);"><img src="../common/images/delete.gif" class="img_bottom" width="15" alt="<gsmsg:write key="cmn.delete.company" />"></a>
        <span class="text_base2"><bean:write name="companyData" property="adrName" /></span><br>
      </logic:iterate>
    </logic:notEmpty>
    </td>
    </tr>
    </logic:notEmpty>
    </logic:equal>

    <logic:notEqual name="prj020Form" property="prj020cmdMode" value="0">
    <logic:notEmpty name="prj020Form" property="prj150AddressIdSv" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.prj020.4" /></span></td>
    <td align="left" class="td_sub_detail">
    <logic:notEmpty name="prj020Form" property="prj020AddDataList">
      <logic:iterate name="prj020Form" property="prj020AddDataList" scope="request" id="companyData">
        <a href="#" onClick="deleteCompany(<bean:write name="companyData" property="adrSid" />);"><img src="../common/images/delete.gif" class="img_bottom" width="15" alt="<gsmsg:write key="cmn.delete.company" />"></a>
        <span class="text_base2"><bean:write name="companyData" property="adrName" /></span><br>
      </logic:iterate>
    </logic:notEmpty>
    </td>
    </tr>
    </logic:notEmpty>
    </logic:notEqual>

    <tr>
    <td class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="project.src.32" /></span>
    </td>
    <td align="left" class="td_sub_detail">

      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
      <td width="10%" align="center">&nbsp;</td>
      </tr>

      <tr>
      <td align="center">
         <!-- 管理者 -->
        <html:select property="prj020adminMember" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj020Form" property="adminMemberLabel" value="value" label="label" />
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('<%= adminAdd %>');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('<%= adminRemove %>');">
      </td>
      <td>
        <html:select property="prj020prjMember" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj020Form" property="memberLabel" value="value" label="label" />
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </logic:equal>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.icon" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <table width="100%" border="0">
      <tr>
      <td width="0%">

        <logic:equal name="prj020Form" property="prj020IcoName" value="">
          <img src="../project/images/prj_icon.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1"><br>
        </logic:equal>
        <logic:notEqual name="prj020Form" property="prj020IcoName" value="">
          <img src="../project/prj020.do?CMD=getImageFile&prj020cmdMode=<bean:write name="prj020Form" property="prj020cmdMode" />&prj020prjSid=<bean:write name="prj020Form" property="prj020prjSid" />" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');">
        </logic:notEqual>
      </td>
      </tr>
      <tr>
      <td><span class="text_base"><gsmsg:write key="cmn.icon.size" /></span><br>
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('prj020tempdeleteMark');">
        &nbsp;
        <!-- 新規登録 -->
        <logic:equal name="prj020Form" property="prj020cmdMode" value="0">
          <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlusPrj('prj020SelectTempFilesMark', '<bean:write name="prj020Form" property="prj020pluginId" />', '1', '1', 'projectIco', 0, 0);">
        </logic:equal>
        <!-- 編集 -->
        <logic:notEqual name="prj020Form" property="prj020cmdMode" value="0">
          <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlusPrj('prj020SelectTempFilesMark', '<bean:write name="prj020Form" property="prj020pluginId" />', '1', '1', 'projectIco', 1, <bean:write name="prj020Form" property="prj020prjSid" />);">
        </logic:notEqual>
      </td>
      <td>&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

    <br>
    <div align="left" class="text_bb1"><gsmsg:write key="project.14" /></div>

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.label" /></span></td>
    <td align="left" class="td_sub_detail" width="80%">
      <input type="button" class="btn_base0" value="<gsmsg:write key="project.77" />" onclick="buttonPush('<%= categoriModify %>');">
      <br>
      <span class="text_base2">
      <logic:notEmpty name="prjStsMdl" property="todoCateList">
      <logic:iterate id="todoCate" name="prjStsMdl" property="todoCateList">
      <br><bean:write name="todoCate" property="ptcName" />
      </logic:iterate>
      </logic:notEmpty>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span></td>
    <td align="left" class="td_sub_detail">
      <input type="button" class="btn_base0" value="<gsmsg:write key="project.77" />" onclick="buttonPush('<%= statusModify %>');">
      <br>
      <span class="text_base2">
      <logic:notEmpty name="prjStsMdl" property="todoStatusList">
      <logic:iterate id="todoStatus" name="prjStsMdl" property="todoStatusList">
      <br><bean:write name="todoStatus" property="ptsRate" />%(<bean:write name="todoStatus" property="ptsName" />)
      </logic:iterate>
      </logic:notEmpty>
      </span>
    </td>
    </tr>


    <logic:equal name="prj020Form" property="prj020prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj020kengen" styleId="edit0" value="<%= kengenMem %>" /><span class="text_base2"><label for="edit0" class="text_base2"><gsmsg:write key="project.prj020.8" /></label></span>
      <html:radio property="prj020kengen" styleId="edit1" value="<%= kengenAdm %>" /><span class="text_base2"><label for="edit1" class="text_base2"><gsmsg:write key="project.13" /></label></span>
      <html:radio property="prj020kengen" styleId="edit2" value="<%= kengenAll %>" /><span class="text_base2"><label for="edit2" class="text_base2"><gsmsg:write key="cmn.no.setting.permission" /></label></span>
    </td>
    </tr>
    </logic:equal>


    <logic:equal name="prj020Form" property="useSmail" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.2" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj020smailKbn" styleId="mailKbn0" value="<%= mailKbnFree %>" /><span class="text_base2"><label for="mailKbn0" class="text_base2"><gsmsg:write key="project.16" /></label></span>
      <html:radio property="prj020smailKbn" styleId="mailKbn1" value="<%= mailKbnAdmin %>" /><span class="text_base2"><label for="mailKbn1" class="text_base2"><gsmsg:write key="project.17" /></label></span>
    </td>
    </tr>
    </logic:equal>

    </table>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
    <tr>
    <td align="right">

      <table align="right">
      <tr>
      <logic:equal name="prj020Form" property="prj020cmdMode" value="<%= entryAdd %>">
        <td><input type="button" value="<gsmsg:write key="cmn.use.template" />" class="btn_base1w" onclick="useTemplate('<%= template %>', '<%= mode_tmp %>');"></td>
        <td><input type="button" value="<gsmsg:write key="project.18" />" class="btn_copy_n1" onclick="return openpos();"></td>
      </logic:equal>
      <td><input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');"></td>
      <logic:equal name="prj020Form" property="prj020cmdMode" value="<%= entryEdit %>">
        <logic:equal name="prj020Form" property="prj020prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
          <td><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');"></td>
        </logic:equal>
      </logic:equal>
      <td><input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');"></td>
      </tr>
      </table>

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

</div>

</body>
</html:html>
