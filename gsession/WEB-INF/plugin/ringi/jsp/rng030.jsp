<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.rng.RngConst" %>
<%@ page import="jp.groupsession.v2.rng.rng030.Rng030Form" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthContent = String.valueOf(RngConst.MAX_LENGTH_CONTENT); %>
<% String maxLengthCmt = String.valueOf(RngConst.MAX_LENGTH_COMMENT); %>
<% int cmdmode_view = Rng030Form.CMDMODE_VIEW; %>
<% int cmdmode_appr = Rng030Form.CMDMODE_APPR; %>
<% int cmdmode_confirm = Rng030Form.CMDMODE_CONFIRM; %>
<% int cmdmode_adminappr = Rng030Form.CMDMODE_ADMINAPPR; %>
<% String apprMode_appl = String.valueOf(RngConst.RNG_APPRMODE_APPL); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/selection.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../ringi/js/rng030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/selectionSearch.js?<%= GSConst.VERSION_PARAM %>"></script>


<% String onloadEvent_appl = ""; %>
<% String onloadEvent_view = ""; %>
<% String onloadEvent_proc = ""; %>
<logic:equal name="rng030Form" property="rngWebSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">

  <% onloadEvent_appl = "showLengthId($(\'#inputstr\')[0], " + maxLengthContent + ", \'inputlength\');hideTooltip();"; %>
  <% onloadEvent_view = "hideTooltip();showLengthId($(\'#inputstr2\')[0], " + maxLengthCmt + ", \'inputlength2\');"; %>
  <% onloadEvent_proc = " onload=\"hideTooltip();\""; %>

</logic:equal>

<title>[Group Session] <gsmsg:write key="rng.62" /></title>
</head>
<logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
  <body class="body_03" onunload="windowClose();" onload="<%= onloadEvent_appl %>">
</logic:equal>
<logic:notEqual name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_view) %>">
  <body class="body_03" onunload="windowClose();" onload="<%= onloadEvent_view %>">
</logic:notEqual>

<bean:define id="procMode" name="rng030Form" property="rngProcMode" type="java.lang.Integer" />
<% if (procMode == RngConst.RNG_MODE_SINSEI || procMode == RngConst.RNG_MODE_KANRYO) { %>
  <body<%= onloadEvent_proc %>>
<% } %>

<html:form action="/ringi/rng030">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngApprMode" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010orderKey" />
<html:hidden property="rngAdminKeyword" />
<html:hidden property="rngAdminGroupSid" />
<html:hidden property="rngAdminUserSid" />
<html:hidden property="rngAdminApplYearFr" />
<html:hidden property="rngAdminApplMonthFr" />
<html:hidden property="rngAdminApplDayFr" />
<html:hidden property="rngAdminApplYearTo" />
<html:hidden property="rngAdminApplMonthTo" />
<html:hidden property="rngAdminApplDayTo" />
<html:hidden property="rngAdminLastManageYearFr" />
<html:hidden property="rngAdminLastManageMonthFr" />
<html:hidden property="rngAdminLastManageDayFr" />
<html:hidden property="rngAdminLastManageYearTo" />
<html:hidden property="rngAdminLastManageMonthTo" />
<html:hidden property="rngAdminLastManageDayTo" />
<html:hidden property="rngAdminSortKey" />
<html:hidden property="rngAdminOrderKey" />
<html:hidden property="rngAdminPageTop" />
<html:hidden property="rngAdminPageBottom" />
<html:hidden property="rngAdminSearchFlg" />
<html:hidden property="rngInputKeyword" />
<html:hidden property="sltGroupSid" />
<html:hidden property="sltUserSid" />
<html:hidden property="sltApplYearFr" />
<html:hidden property="sltApplMonthFr" />
<html:hidden property="sltApplDayFr" />
<html:hidden property="sltApplYearTo" />
<html:hidden property="sltApplMonthTo" />
<html:hidden property="sltApplDayTo" />
<html:hidden property="sltLastManageYearFr" />
<html:hidden property="sltLastManageMonthFr" />
<html:hidden property="sltLastManageDayFr" />
<html:hidden property="sltLastManageYearTo" />
<html:hidden property="sltLastManageMonthTo" />
<html:hidden property="sltLastManageDayTo" />
<html:hidden property="rngDspMode" />

<html:hidden property="rngKeyword" />
<html:hidden property="rng130Type" />
<html:hidden property="rng130keyKbn" />
<html:hidden property="rng130searchSubject1" />
<html:hidden property="rng130searchSubject2" />
<html:hidden property="sltSortKey1" />
<html:hidden property="rng130orderKey1" />
<html:hidden property="sltSortKey2" />
<html:hidden property="rng130orderKey2" />
<html:hidden property="rng130pageTop" />
<html:hidden property="rng130pageBottom" />

<html:hidden property="svRngKeyword" />
<html:hidden property="svRng130Type" />
<html:hidden property="svGroupSid" />
<html:hidden property="svUserSid" />
<html:hidden property="svRng130keyKbn" />
<html:hidden property="svRng130searchSubject1" />
<html:hidden property="svRng130searchSubject2" />
<html:hidden property="svSortKey1" />
<html:hidden property="svRng130orderKey1" />
<html:hidden property="svSortKey2" />
<html:hidden property="svRng130orderKey2" />
<html:hidden property="svApplYearFr" />
<html:hidden property="svApplMonthFr" />
<html:hidden property="svApplDayFr" />
<html:hidden property="svApplYearTo" />
<html:hidden property="svApplMonthTo" />
<html:hidden property="svApplDayTo" />
<html:hidden property="svLastManageYearFr" />
<html:hidden property="svLastManageMonthFr" />
<html:hidden property="svLastManageDayFr" />
<html:hidden property="svLastManageYearTo" />
<html:hidden property="svLastManageMonthTo" />
<html:hidden property="svLastManageDayTo" />

<html:hidden property="rng130searchFlg" />

<html:hidden property="rng030fileId" />


<html:hidden property="rng020copyApply" />

<logic:notEqual name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
  <html:hidden property="rng030ViewTitle" />
</logic:notEqual>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="90%" cellpadding="0" cellspacing="0">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="sml.sml030.08" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <logic:equal name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_appr) %>">

            <input type="button" value="<gsmsg:write key="rng.41" />" class="btn_syonin_n1" onClick="buttonPush('approval')">
            <input type="button" value="<gsmsg:write key="rng.22" />" class="btn_kyakka_n1" onClick="buttonPush('reject')">
            <logic:equal name="rng030Form" property="rng030rftBtnFlg" value="1">
            <input type="button" value="<gsmsg:write key="rng.rng030.08" />" class="btn_sashimodoshi_n1" onClick="buttonPush('reflection')">

            </logic:equal>
          </logic:equal>

          <logic:equal name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_confirm) %>">
            <input type="button" value="<gsmsg:write key="cmn.check" />" class="btn_base1" onClick="buttonPush('confirmation')">
          </logic:equal>

          <logic:equal name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_adminappr) %>">
            <input type="button" value="<gsmsg:write key="rng.rng030.06" />" class="btn_base1" onClick="buttonPush('compelcomplete')">
            <input type="button" value="<gsmsg:write key="rng.rng030.07" />" class="btn_dell_n1" onClick="buttonPush('compeldelete')">

            <logic:equal name="rng030Form" property="rng030skipBtnFlg" value="1">
            <input type="button" value="<gsmsg:write key="rng.rng030.03" />" class="btn_base1" onClick="buttonPush('skip')">
            </logic:equal>

          </logic:equal>

          <logic:equal name="rng030Form" property="rng030compBtnFlg" value="1">
            <input type="button" value="<gsmsg:write key="cmn.complete" />" class="btn_base1" onClick="buttonPush('complete')">
          </logic:equal>


          <logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
            <input type="button" value="<gsmsg:write key="rng.rng030.09" />" class="btn_base1" onClick="buttonPush('applicate')">
          </logic:equal>

          <logic:equal name="rng030Form" property="rng030copyApplBtn" value="true">
            <input type="button" value="<gsmsg:write key="rng.rng030.13" />" class="btn_base1w" onClick="copyApply();">
          </logic:equal>
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backList')">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table width="100%" class="tl0" border="0" cellpadding="0">
    <tr><td align="left"><html:errors/></td></tr>
    </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5" id="selectionSearchArea">
    <tr>
    <td><img src="../common/images/damy.gif" width="1" height="5"></td>
    </tr>


    <bean:define id="rngStatus" name="rng030Form" property="rng030Status" />
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span></td>
    <% if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_REQUEST) { %>
    <td colspan="6" align="left" class="td_wt" width="100%"><gsmsg:write key="rng.48" /></td>
    <% } else if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_SETTLED) { %>
    <td colspan="6" align="left" class="td_wt" width="100%"><gsmsg:write key="rng.64" /></td>
    <% } else if (((Integer) rngStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_STATUS_REJECT) { %>
    <td colspan="6" align="left" class="td_wt" width="100%"><gsmsg:write key="rng.65" /></td>
    <% } else { %>
    <td colspan="6" align="left" class="td_wt" width="100%">&nbsp;</td>
    <% } %>
    </tr>

    <% String comments = ""; %>
    <logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <gsmsg:define id="strComments" msgkey="cmn.comments" />
      <% comments = "<span class=\"text_r2\">" + strComments + "</span>"; %>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.title" /><%= comments %></span>
    </td>
    <td colspan="6" align="left" class="td_wt" width="100%">
    <logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <html:text name="rng030Form" property="rng030Title" style="width:633px;" maxlength="100" />
    </logic:equal>
    <logic:notEqual name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <bean:write name="rng030Form" property="rng030ViewTitle" />
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.47" /></span></td>
    <td colspan="6" align="left" class="td_wt" width="100%"><bean:write name="rng030Form" property="rng030apprUser" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.37" /></span></td>
    <td colspan="6" align="left" class="td_wt" width="100%"><bean:write name="rng030Form" property="rng030makeDate" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span><%= comments %></td>
    <td colspan="6" align="left" class="td_wt" width="100%">
    <logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <textarea name="rng030Content" style="width:541px;" rows="10" onkeyup="showLengthStr(value, <%= maxLengthContent %>, 'inputlength');" id="inputstr"><bean:write name="rng030Form" property="rng030Content" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </logic:equal>
    <logic:notEqual name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <bean:write name="rng030Form" property="rng030ViewContent" filter="false" />
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td colspan="6" align="left" class="td_wt">

    <logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delTemp');">
      &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('rng030files', 'ringi', '0', '0');">
      <br>
      <html:select property="rng030files" styleClass="select01" size="5" multiple="true">
        <html:optionsCollection name="rng030Form" property="rng030fileList" value="value" label="label" />
      </html:select>
    </logic:equal>

    <logic:notEqual name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
      <logic:empty name="rng030Form" property="tmpFileList">&nbsp;</logic:empty>
      <logic:notEmpty name="rng030Form" property="tmpFileList">
      <logic:iterate id="tmpFile" name="rng030Form" property="tmpFileList" indexId="idx" scope="request">
      <a href="javascript:void(0);" onClick="return fileNameClick(<bean:write name="tmpFile" property="binSid" />);"><span class="text_link"><u><bean:write name="tmpFile" property="binFileName" /><bean:write name="tmpFile" property="binFileSizeDsp" /></u></span></a><br>
      </logic:iterate>
      </logic:notEmpty>
    </logic:notEqual>

    </td>
    </tr>

    <logic:notEqual name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_view) %>">

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.comment" /></span></td>
    <td colspan="6" align="left" class="td_wt"><textarea name="rng030Comment" style="width:541px;" rows="3" onkeyup="showLengthStr(value, <%= maxLengthCmt %>, 'inputlength2');" id="inputstr2"><bean:write name="rng030Form" property="rng030Comment" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength2" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthCmt %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <bean:define id="cmdMode" name="rng030Form" property="rng030CmdMode" />
    <% int iCmdMode = ((Integer) cmdMode).intValue(); %>
    <% if (iCmdMode == cmdmode_appr || iCmdMode == cmdmode_confirm) { %>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.rng030.05" /></span></td>
    <td valign="middle" align="left" class="td_wt" colspan="6">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delTemp');">
      &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('rng030files', 'ringi', '0', '0');">
      <br>
      <html:select property="rng030files" styleClass="select01" size="5" multiple="true">
        <html:optionsCollection name="rng030Form" property="rng030fileList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <% } %>

    </logic:notEqual>

    <tr>
    <td align="center" colspan="7" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="rng.27" /></span></td>
    </tr>

    <tr>
    <td colspan="3" align="center" class="td_type3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.user.name" /></span></td>
    <td align="center" class="td_type3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.comment" /></span></td>
    <td align="center" class="td_type3" nowrap><span class="text_bb1"><gsmsg:write key="rng.rng030.05" /></span></td>
    <td align="center" class="td_type3" nowrap><span class="text_bb1"><gsmsg:write key="rng.rng030.04" /></span></td>
    <td align="center" class="td_type3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span></td>
    </tr>

    <tr>
      <td align="center" width="5%" class="td_wt" rowspan="<bean:write name="rng030Form" property="channelListCount" />"><img src="../common/images/arrow_south.gif" alt="<gsmsg:write key="rng.26" />"></td>

    <logic:iterate id="apprUser" name="rng030Form" property="channelList" indexId="idx">
    <bean:define id="channelStatus" name="apprUser" property="rncStatus" />
    <% if (idx.intValue() > 1) { %>
    <tr>
    <% } %>
      <% String td_class = "td_wt"; %>
      <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_CONFIRM) { %>
      <% td_class = "td_confirm"; %>
      <% } %>

      <td class="<%= td_class %>" width="8%" nowrap><bean:write name="apprUser" property="yakusyoku" /></td>
      <td class="<%= td_class %>" width="20%" nowrap>
      <logic:equal name="apprUser" property="delUser" value="true">
        <strike><bean:write name="apprUser" property="userName" /></strike>
      </logic:equal>
      <logic:equal name="apprUser" property="delUser" value="false">
        <bean:write name="apprUser" property="userName" />
      </logic:equal>
      </span>
      </td>
      <td class="<%= td_class %>" width="32%">
      <bean:write name="apprUser" property="rncComment" filter="false" />
      <logic:equal name="apprUser" property="ringiUse" value="false">
        <logic:notEmpty name="apprUser" property="rncComment"><br></logic:notEmpty>
        <span class="text_error"><gsmsg:write key="rng.rng030.01" /></span>
      </logic:equal>
      </td>
      <td align="left" class="<%= td_class %>" width="20%">
        <logic:empty name="apprUser" property="tmpFileList">&nbsp;</logic:empty>
        <logic:notEmpty name="apprUser" property="tmpFileList">
        <logic:iterate id="tmpFile" name="apprUser" property="tmpFileList">
        <a href="javascript:void(0);" onClick="return fileNameClick(<bean:write name="tmpFile" property="binSid" />);"><span class="text_link"><u><bean:write name="tmpFile" property="binFileName" /><bean:write name="tmpFile" property="binFileSizeDsp" /></u></span></a><br>
        </logic:iterate>
        </logic:notEmpty>
      </td>
      <td align="center" class="<%= td_class %>" width="10%"><bean:write name="apprUser" property="strRncChkDate" /></td>
      <td align="center" class="<%= td_class %>" align="center" width="5%" nowrap>
      <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_APPR) { %>
      <img src="../ringi/images/mitomeru_stamp.gif" alt="<gsmsg:write key="rng.41" />">
      <% } else if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_DENIAL) { %>
      <img src="../ringi/images/hinin_stamp.gif" alt="<gsmsg:write key="rng.rng030.12" />">
      <% } %>
      </td>
    </tr>
    </logic:iterate>

    <logic:notEmpty name="rng030Form" property="confirmChannelList">
    <tr>
      <td class="td_wt" nowrap align="center" width="5%" rowspan="<bean:write name="rng030Form" property="confirmChannelListCount" />"><gsmsg:write key="cmn.check" /></td>

    <logic:iterate id="confirmUser" name="rng030Form" property="confirmChannelList" indexId="idx">
    <bean:define id="channelStatus" name="confirmUser" property="rncStatus" />
    <% if (idx.intValue() > 1) { %>
    <tr>
    <% } %>
      <td class="td_wt" width="8%" nowrap><bean:write name="confirmUser" property="yakusyoku" /></td>
      <td class="td_wt" width="20%" nowrap>
      <logic:equal name="confirmUser" property="delUser" value="true">
        <strike><bean:write name="confirmUser" property="userName" /></strike>
      </logic:equal>
      <logic:equal name="confirmUser" property="delUser" value="false">
        <bean:write name="confirmUser" property="userName" />
      </logic:equal>
      </td>
      <td class="td_wt" width="32%">
      <bean:write name="confirmUser" property="rncComment" filter="false" />
      <logic:equal name="confirmUser" property="ringiUse" value="false">
        <logic:notEmpty name="confirmUser" property="rncComment"><br></logic:notEmpty>
        <span class="text_error"><gsmsg:write key="rng.rng030.01" /></span>
      </logic:equal>
      </td>
      <td class="td_wt" width="20%">
        <logic:empty name="confirmUser" property="tmpFileList">&nbsp;</logic:empty>
        <logic:notEmpty name="confirmUser" property="tmpFileList">
        <logic:iterate id="tmpFile" name="confirmUser" property="tmpFileList">
        <a href="javascript:void(0);" onClick="return fileNameClick(<bean:write name="tmpFile" property="binSid" />);"><span class="text_link"><u><bean:write name="tmpFile" property="binFileName" /><bean:write name="tmpFile" property="binFileSizeDsp" /></u></span></a><br>
        </logic:iterate>
        </logic:notEmpty>
      </td>
      <td align="center" class="td_wt" width="10%"><bean:write name="confirmUser" property="strRncChkDate" /></td>
      <td align="center" class="td_wt" width="5%" nowrap>
      <% if (((Integer) channelStatus).intValue() == jp.groupsession.v2.rng.RngConst.RNG_RNCSTATUS_CONFIRMATION) { %>
      <img src="../ringi/images/mitomeru_stamp.gif" alt="<gsmsg:write key="cmn.check" />">
      <% } %>
      </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

  </table>


    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <logic:equal name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_appr) %>">






        <input type="button" value="<gsmsg:write key="rng.41" />" class="btn_syonin_n1" onClick="buttonPush('approval')">
        <input type="button" value="<gsmsg:write key="rng.22" />" class="btn_kyakka_n1" onClick="buttonPush('reject')">
        <logic:equal name="rng030Form" property="rng030rftBtnFlg" value="1">
        <input type="button" value="<gsmsg:write key="rng.rng030.08" />" class="btn_sashimodoshi_n1" onClick="buttonPush('reflection')">
        </logic:equal>









      </logic:equal>

      <logic:equal name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_confirm) %>">
        <input type="button" value="<gsmsg:write key="cmn.check" />" class="btn_base1" onClick="buttonPush('confirmation')">
      </logic:equal>

      <logic:equal name="rng030Form" property="rng030CmdMode" value="<%= String.valueOf(cmdmode_adminappr) %>">
        <input type="button" value="<gsmsg:write key="rng.rng030.06" />" class="btn_base1" onClick="buttonPush('compelcomplete')">
        <input type="button" value="<gsmsg:write key="rng.rng030.07" />" class="btn_dell_n1" onClick="buttonPush('compeldelete')">

        <logic:equal name="rng030Form" property="rng030skipBtnFlg" value="1">
        <input type="button" value="<gsmsg:write key="rng.rng030.03" />" class="btn_base1" onClick="buttonPush('skip')">
        </logic:equal>

      </logic:equal>

      <logic:equal name="rng030Form" property="rng030compBtnFlg" value="1">
        <input type="button" value="<gsmsg:write key="cmn.complete" />" class="btn_base1" onClick="buttonPush('complete')">
      </logic:equal>

      <logic:equal name="rng030Form" property="rngApprMode" value="<%= apprMode_appl %>">
        <input type="button" value="<gsmsg:write key="rng.rng030.09" />" class="btn_base1" onClick="buttonPush('applicate')">
      </logic:equal>
      <logic:equal name="rng030Form" property="rng030copyApplBtn" value="true">
        <input type="button" value="<gsmsg:write key="rng.rng030.13" />" class="btn_base1w" onClick="copyApply();">
      </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backList')">
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

<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>

</html:form>
<logic:equal name="rng030Form" property="rngWebSearchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
<span id="tooltip_search" class="tooltip_search"></span>
</logic:equal>
</body>
</html:html>
