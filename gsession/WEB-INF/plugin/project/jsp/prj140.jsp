<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%  String okClick         = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_OK_CLICK;
    String deleteClick     = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_DEL_CLICK;
    String backClick       = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_BACK_CLICK;
    String backRedraw      = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_BACK_REDRAW;
    String prjStatusModify = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_PRJ_STATUS_MODIFY_CLICK;
    String memberAdd       = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_MEMBER_ADD_CLICK;
    String memberRemove    = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_MEMBER_REMOVE_CLICK;
    String adminAdd        = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_ADMIN_ADD_CLICK;
    String adminRemove     = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_ADMIN_REMOVE_CLICK;
    String categoriModify  = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_CATEGORI_MODIFY_CLICK;
    String statusModify    = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_STATUS_MODIFY_CLICK;
    String memberEdit      = jp.groupsession.v2.prj.prj140.Prj140Action.CMD_MEMBER_EDIT;
%>

<%-- 定数値 --%>
<%  String mode_kojin      = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_KOJIN);
    String mode_kyoyu      = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_KYOYU);
    String mode_select     = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_SELECT);
    String kengenMem       = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_EDIT_KENGEN_MEM);
    String kengenAdm       = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_EDIT_KENGEN_ADM);
    String kengenAll       = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_EDIT_KENGEN_ALL);
    String mailKbnFree     = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_FREE);
    String mailKbnAdmin    = String.valueOf(jp.groupsession.v2.prj.GSConstProject.TODO_MAIL_SEND_ADMIN);
    String enabled         = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_KOUKAI_ENABLED);
    String disabled        = String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_KOUKAI_DISABLED);
    String dspId           = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ140;
    String maxLengthTarget = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_TARGET);
    String maxLengthContent = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MAX_LENGTH_CONTENT);
%>

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
<script language="JavaScript" src="../project/js/prj140.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03" onunload="calWindowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthTarget %>, 'inputlength');showLengthId($('#inputstr2')[0], <%= maxLengthContent %>, 'inputlength2');">

<html:form action="/project/prj140">
<input type="hidden" name="helpPrm" value="<bean:write name="prj140Form" property="prj140cmdMode" />">
<input type="hidden" name="CMD" id="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010Init" />
<html:hidden property="selectingProject" />
<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />
<html:hidden property="prjTmpMode" />
<html:hidden property="prtSid" />
<html:hidden property="movedDspId" />
<html:hidden property="prj140cmdMode" />

<logic:notEmpty name="prj140Form" property="prj140hdnMember" scope="request">
  <logic:iterate id="item" name="prj140Form" property="prj140hdnMember" scope="request">
    <input type="hidden" name="prj140hdnMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj140Form" property="prj140hdnAdmin" scope="request">
  <logic:iterate id="item" name="prj140Form" property="prj140hdnAdmin" scope="request">
    <input type="hidden" name="prj140hdnAdmin" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj140Form" property="prj150AddressIdSv" scope="request">
  <logic:iterate id="addressId" name="prj140Form" property="prj150AddressIdSv" scope="request">
    <input type="hidden" name="prj150AddressIdSv" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj140Form" property="prj150CompanySid" scope="request">
  <logic:iterate id="coId" name="prj140Form" property="prj150CompanySid" scope="request">
    <input type="hidden" name="prj150CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj140Form" property="prj150CompanyBaseSid" scope="request">
  <logic:iterate id="coId" name="prj140Form" property="prj150CompanyBaseSid" scope="request">
    <input type="hidden" name="prj150CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<bean:define id="prjStsMdl" name="prj140Form" property="prjStatusTmpMdl" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="82%" class="tl0">
  <tr>
  <td align="center">

    <logic:equal name="prj140Form" property="prjTmpMode" value="<%= mode_kyoyu %>">
      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>

      <logic:lessEqual name="prj140Form" property="prtSid" value="0">
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.entry.shared.template" /> ]</td>
      </logic:lessEqual>

      <logic:greaterThan name="prj140Form" property="prtSid" value="0">
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="project.prj140.2" /> ]</td>
      </logic:greaterThan>

      <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <logic:equal name="prj140Form" property="prjTmpMode" value="<%= mode_kojin %>">
      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>

      <logic:lessEqual name="prj140Form" property="prtSid" value="0">
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.entry.personal.template" /> ]</td>
      </logic:lessEqual>

      <logic:greaterThan name="prj140Form" property="prtSid" value="0">
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="project.prj140.4" /> ]</td>
      </logic:greaterThan>

      <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <logic:equal name="prj140Form" property="prjTmpMode" value="<%= mode_select %>">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
      <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.prj140.5" /> ]</td>
      <td width="100%" class="header_white_bg"></td>
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">&nbsp;</td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <table align="right">
      <tr>
      <td><input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');"></td>

        <logic:greaterThan name="prj140Form" property="prtSid" value="0">
          <td><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');"></td>
        </logic:greaterThan>

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
    <tr><td width="100%" align="left"><html:errors/></td></tr>
    </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="project.prj140.6" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <html:text property="prj140prtTmpName" style="width:633px;" maxlength="50" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="project.31" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <html:text property="prj140prtId" style="width:291px;" maxlength="20" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.40" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj140prtName" style="width:633px;" maxlength="70" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.41" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj140prtNameS" style="width:291px;" maxlength="20" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.10" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:text property="prj140yosan" style="width:93px;" maxlength="9" />&nbsp;<span class="text_base2"><gsmsg:write key="project.103" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.19" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj140koukai" styleId="prj140koukai_0" value="<%= enabled %>" /><span class="text_base2"><label for="prj140koukai_0" class="text_base2"><gsmsg:write key="cmn.public" /></label></span>&nbsp;
      <html:radio property="prj140koukai" styleId="prj140koukai_1" value="<%= disabled %>" /><span class="text_base2"><label for="prj140koukai_1" class="text_base2"><gsmsg:write key="cmn.private" /></label></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj140startYear" styleId="prj140startYear">
         <html:optionsCollection name="prj140Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj140startMonth" styleId="prj140startMonth">
         <html:optionsCollection name="prj140Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj140startDay" styleId="prj140startDay">
         <html:optionsCollection name="prj140Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateFrBtn" value="Cal" onclick="wrtCalendarByBtn($('#prj140startDay')[0], $('#prj140startMonth')[0], $('#prj140startYear')[0], 'prj140_1');" class="calendar_btn", id="prj140_1">
      &nbsp;
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj140startYear')[0], $('#prj140startMonth')[0], $('#prj140startDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj140startYear')[0], $('#prj140startMonth')[0], $('#prj140startDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj140startYear')[0], $('#prj140startMonth')[0], $('#prj140startDay')[0], 3)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj140startDay', 'prj140startMonth', 'prj140startYear')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:select property="prj140endYear" styleId="prj140endYear">
         <html:optionsCollection name="prj140Form" property="yearLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj140endMonth" styleId="prj140endMonth">
         <html:optionsCollection name="prj140Form" property="monthLabel" value="value" label="label" />
      </html:select>
      <html:select property="prj140endDay" styleId="prj140endDay">
         <html:optionsCollection name="prj140Form" property="dayLabel" value="value" label="label" />
      </html:select>
      <input type="button" name="dateToBtn" value="Cal" onclick="wrtCalendarByBtn($('#prj140endDay')[0], $('#prj140endMonth')[0], $('#prj140endYear')[0], 'prj140_2');" class="calendar_btn", id="prj140_2">
      &nbsp;
      <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#prj140endYear')[0], $('#prj140endMonth')[0], $('#prj140endDay')[0], 1)">
      <input type="button" class="btn_today" value="<gsmsg:write key="cmn.today" />" onClick="return moveDay($('#prj140endYear')[0], $('#prj140endMonth')[0], $('#prj140endDay')[0], 2)">
      <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#prj140endYear')[0], $('#prj140endMonth')[0], $('#prj140endDay')[0], 3)">

      &nbsp;<input type="button" value="<gsmsg:write key="cmn.clear" />" class="btn_base0" onclick="return clearDate('prj140endDay', 'prj140endMonth', 'prj140endYear')">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span></td>
    <td align="left" class="td_sub_detail">
      <input type="button" class="btn_base0" value="<gsmsg:write key="project.77" />" onclick="buttonPush('<%= prjStatusModify %>');">
      <br>
      <logic:notEmpty name="prjStsMdl" property="prjStatusList">
      <logic:iterate id="prjStatus" name="prjStsMdl" property="prjStatusList">
      <bean:define id="idbase" value="prj140status" />
      <bean:define id="pttSid" name="prjStatus" property="pttSid" />
      <bean:define id="idname" value="<%= String.valueOf(idbase) + String.valueOf(pttSid) %>" />

      <html:radio property="prj140status" styleId="<%= idname %>" value="<%= String.valueOf(pttSid) %>" /><span class="text_base2"><label for="<%= idname %>" class="text_base2"><bean:write name="prjStatus" property="pttRate" />%(<bean:write name="prjStatus" property="pttName" />)</label></span>

      </logic:iterate>
      </logic:notEmpty>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.21" /></span></td>
    <td align="left" class="td_sub_detail">
      <textarea name="prj140mokuhyou" style="width:643px;" rows="5" class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthTarget %>, 'inputlength');" id="inputstr"><bean:write name="prj140Form" property="prj140mokuhyou" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthTarget %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="td_sub_detail">
      <textarea name="prj140naiyou" style="width:643px;" rows="5" class="text_gray" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength2');" id="inputstr2"><bean:write name="prj140Form" property="prj140naiyou" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

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
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.prj140group, 'prj140group', '<bean:write name="prj140Form" property="prj140group" />', '0', 'backRedraw', 'prj140hdnMember', '-1', '0', '0', '1')" type="button">
        </td>
        <td width="10%" align="left"></td>
      </tr>
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <html:select property="prj140group" styleClass="select01" onchange="buttonPush('backRedraw');">
           <html:optionsCollection name="prj140Form" property="groupLabel" value="value" label="label" />
        </html:select>
      </td>
      <td width="10%" align="left">
        <input type="button" onclick="openGroupWindow(this.form.prj140group, 'prj140group', '0', 'backRedraw')" class="group_btn2" value="&nbsp;&nbsp;" id="prj140GroupBtn">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="prj140syozokuMember" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj140Form" property="syozokuMemberLabel" value="value" label="label" />
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('<%= memberAdd %>');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('<%= memberRemove %>');">
      </td>
      <td>
        <html:select property="prj140user" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj140Form" property="userLabel" value="value" label="label" />
        </html:select>
      </td>
      </tr>
      <tr>
      <td colspan="3" align="left"><input type="button" value="<gsmsg:write key="project.1" />" class="btn_base1w" onclick="memberEdit('<%= memberEdit %>', '<%= dspId %>');"></td>
      </tr>
      </table>

    </td>
    </tr>

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
        <html:select property="prj140adminMember" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj140Form" property="adminMemberLabel" value="value" label="label" />
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('<%= adminAdd %>');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('<%= adminRemove %>');">
      </td>
      <td>
        <html:select property="prj140prjMember" multiple="true" size="5" styleClass="select01">
           <html:optionsCollection name="prj140Form" property="memberLabel" value="value" label="label" />
        </html:select>
      </td>
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
        <br><bean:write name="todoCate" property="pctName" />
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
        <br><bean:write name="todoStatus" property="pstRate" />%(<bean:write name="todoStatus" property="pstName" />)
        </logic:iterate>
      </logic:notEmpty>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj140kengen" styleId="edit0" value="<%= kengenMem %>" /><span class="text_base2"><label for="edit0" class="text_base2"><gsmsg:write key="project.prj020.8" /></label></span>
      <html:radio property="prj140kengen" styleId="edit1" value="<%= kengenAdm %>" /><span class="text_base2"><label for="edit1" class="text_base2"><gsmsg:write key="project.13" /></label></span>
      <html:radio property="prj140kengen" styleId="edit2" value="<%= kengenAll %>" /><span class="text_base2"><label for="edit2" class="text_base2"><gsmsg:write key="cmn.no.setting.permission" /></label></span>
    </td>
    </tr>

    <logic:equal name="prj140Form" property="useSmail" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="project.2" /></span></td>
    <td align="left" class="td_sub_detail">
      <html:radio property="prj140smailKbn" styleId="mailKbn0" value="<%= mailKbnFree %>" /><span class="text_base2"><label for="mailKbn0" class="text_base2"><gsmsg:write key="project.16" /></label></span>
      <html:radio property="prj140smailKbn" styleId="mailKbn1" value="<%= mailKbnAdmin %>" /><span class="text_base2"><label for="mailKbn1" class="text_base2"><gsmsg:write key="project.17" /></label></span>
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
      <td><input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');"></td>

        <logic:greaterThan name="prj140Form" property="prtSid" value="0">
          <td><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onclick="buttonPush('<%= deleteClick %>');"></td>
        </logic:greaterThan>

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