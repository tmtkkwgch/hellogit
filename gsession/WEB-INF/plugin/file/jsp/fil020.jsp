<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String callOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CALL_OFF);
   String callOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CALL_ON);
   String shortcutOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.SHORTCUT_OFF);
   String shortcutOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.SHORTCUT_ON);
   String rekiKbnNew = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_NEW);
   String rekiKbnUpdate = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_UPDATE);
   String rekiKbnRepair = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_REPAIR);
   String rekiKbnDelete = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_DELETE);

%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="fil.50" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmnPic.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil020.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>



<!-- BODY -->
<body class="body_03" onload="initPicture('iconImage', 30);">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil020Form" property="fil020DspMode" />">
<html:form action="/file/fil020">
<input type="hidden" name="CMD" value="">

<html:hidden property="cmnMode" />
<html:hidden property="backDsp" />
<html:hidden property="fileSid" />
<html:hidden property="filSearchWd" />
<html:hidden property="fil010SelectCabinet" />
<input type="hidden" name="fil030SelectCabinet" value="">

<html:hidden property="fil020DspMode" />
<html:hidden property="fil020SortKey" />
<html:hidden property="fil020OrderKey" />
<html:hidden property="fil020SltDirSid" />
<html:hidden property="fil020SltDirVer" />
<html:hidden property="fil020binSid" />

<logic:notEmpty name="fil020Form" property="fil010SelectDelLink" scope="request">
<logic:iterate id="item" name="fil020Form" property="fil010SelectDelLink" scope="request">
  <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<!-- HEADER -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="80%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>

    <td width="0%">
      <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
    <td width="100%" class="header_white_bg_text">
        [ <gsmsg:write key="fil.50" /> ]
    </td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <logic:equal name="fil020Form" property="fil020WriteFlg" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="fil020CabinetEdit('fil020edit');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil020back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../project/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.23" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <span class="text_base_prj"><bean:write name="fil020Form" property="fil020CabinetName" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.3" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <logic:equal name="fil020Form" property="fil020DspCapaKbn" value="0">
    <span class="text_base_prj"><gsmsg:write key="cmn.noset" /></span>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020DspCapaKbn" value="0">
    <span class="text_base"><gsmsg:write key="fil.4" />：<b><bean:write name="fil020Form" property="fil020DspCapaSize" />MB&nbsp;</b></span>
    <logic:notEqual name="fil020Form" property="fil020DspCapaWarn" value="0">
    <br><span class="text_base"><gsmsg:write key="fil.fil030kn.1" />：<b><bean:write name="fil020Form" property="fil020DspCapaWarn" />%</b></span>
    </logic:notEqual>
    </logic:notEqual>
    </td>

    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.icon" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <logic:equal name="fil020Form" property="fil020binSid" value="0">
      <img src="../file/images/cabinet.gif" alt="">
      </logic:equal>
      <logic:notEqual name="fil020Form" property="fil020binSid" value="0">
      <img name="iconImage" src="../file/fil020.do?CMD=tempview&fil010SelectCabinet=<bean:write name="fil020Form" property="fil010SelectCabinet" />&fil020binSid=<bean:write name="fil020Form" property="fil020binSid" />" name="pictImage">
       </logic:notEqual>
    </td>
    </tr>

    <logic:equal name="fil020Form" property="admVerKbn" value="1">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.5" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <logic:equal name="fil020Form" property="fil020VerKbn" value="0">
    <span class="text_base_prj"><gsmsg:write key="fil.65" /></span>
    </logic:equal>

    <logic:notEqual name="fil020Form" property="fil020VerKbn" value="0">
    <bean:define id="ver" name="fil020Form" property="fil020VerKbn" type="java.lang.String" />
    <span class="text_base"><gsmsg:write key="fil.generations" arg0="<%= ver %>" />&nbsp;</span>
    </logic:notEqual>
    <logic:equal name="fil020Form" property="fil020VerAllKbn" value="1">
    <span class="text_base">：&nbsp;<gsmsg:write key="fil.15" /></span>
    </logic:equal>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><bean:write name="fil020Form" property="fil020DspBiko" filter="false" /></span>
    </td>
    </tr>
<%--
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">

        <logic:empty name="fil020Form" property="fil020FileLabelList" scope="request">&nbsp;</logic:empty>

        <logic:notEmpty name="fil020Form" property="fil020FileLabelList" scope="request">
        <table cellpadding="0" cellpadding="0" border="0">

        <logic:iterate id="fileMdl" name="fil020Form" property="fil020FileLabelList" scope="request">
          <tr>
          <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun"><a href="#" onClick="fileDl('fileDownload', '<bean:write name="fileMdl" property="binSid" />');"><span class="text_link"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a></td>
          </tr>
        </logic:iterate>

        </table>
        </logic:notEmpty>
    </td>
    </tr>
--%>
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.2" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <logic:equal name="fil020Form" property="fil020ShortCutKbn" value="0">
    <span class="text_base"><gsmsg:write key="fil.105" /></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn_scut_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('fil020short');">
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020ShortCutKbn" value="0">
    <span class="text_base"><gsmsg:write key="fil.106" /></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil020short');">
    </logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.1" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <logic:equal name="fil020Form" property="fil020CallKbn" value="0">
    <span class="text_base"><gsmsg:write key="cmn.invalid" /></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn_call_n1" value="<gsmsg:write key="fil.1" />" onclick="buttonPush('fil020call');">
    </logic:equal>
    <logic:equal name="fil020Form" property="fil020CallKbn" value="1">
    <span class="text_base"><gsmsg:write key="cmn.effective" /></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn_dell_n1" value="<gsmsg:write key="fil.20" />" onclick="buttonPush('fil020call');">
    </logic:equal>
    &nbsp;&nbsp;&nbsp;
    <html:checkbox name="fil020Form" property="fil020CallLevelKbn" value="1" styleId="ikkatuCheck" /><label for="ikkatuCheck"><gsmsg:write key="fil.7" /></label>

    </td>
    </tr>

    </table>

    <img src="../project/images/spacer.gif" width="1px" height="10px" border="0"><br>

    <table width="100%"  border="0" cellpadding="0">
    </table>


    <logic:equal name="fil020Form" property="fil020DspMode" value="0">
    <!--更新履歴-->
    <table width="100%" class="tl0 prj_tbl_base_2" border="0" cellpadding="0">
    <tr>
    <td width="10%" align="center" class="td_type_filetab1" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.fil020.2" /></span></td>
    <td width="10%" align="center" class="td_type_filetab2" onclick="fil020TabChange('fil020tabChange', '1');" nowrap> <span class="text_white_s"><gsmsg:write key="fil.fil020.3" /></span></td>
    <td width="60%" class="td_type_border_bottom"></td>

    <td width="20%" align="right" class="td_type_border_bottom">
    <bean:size id="count1" name="fil020Form" property="fil020PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil020Slt_page1" onchange="fil020changePage(1);" styleClass="text_i">
          <html:optionsCollection name="fil020Form" property="fil020PageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
      </td>
    </tr>
    </table>
    </logic:greaterThan>
    </td>

    </tr>
    <tr>
    <td colspan="4" class="td_type_filetab1_u"><img src="../project/images/spacer.gif" width="1px" height="5px" border="0"></td>
    </tr>
    </table>

    <logic:empty name="fil020Form" property="historyList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td width="100%" class="td_type_file">
    <span class="text_base"><gsmsg:write key="fil.fil020.4" /></span>
    </td>
    </table>
    </logic:empty>

    <logic:notEmpty name="fil020Form" property="historyList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">

    <tr>
    <th width="0%" class="td_type_file" nowrap>
    <logic:equal name="fil020Form" property="fil020SortKey" value="1">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(1, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="1">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" /></span></a>
    </logic:notEqual>
    </th>

    <th width="0%" class="td_type_file" nowrap>
    <logic:equal name="fil020Form" property="fil020SortKey" value="2">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(2, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="2">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" /></span></a>
    </logic:notEqual>
    </th>

    <th width="50%" class="td_type_file" nowrap>
    <logic:equal name="fil020Form" property="fil020SortKey" value="3">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.9" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(3, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.9" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="3">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.9" /></span></a>
    </logic:notEqual>
    </th>

    <th width="0%" class="td_type_file" nowrap>
    <logic:equal name="fil020Form" property="fil020SortKey" value="4">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(4, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.operations" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(4, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.operations" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="4">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(4, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.operations" /></span></a>
    </logic:notEqual>
    </th>

    <th width="50%" class="td_type_file" nowrap>
      <span class="text_prjtodo_list_head"><gsmsg:write key="fil.11" /></span>
    </th>

    <logic:equal name="fil020Form" property="fil020FileWriteFlg" value="1">
    <th width="0%" class="td_type_file" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.12" /></span></th>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020FileWriteFlg" value="1">
    <logic:equal name="fil020Form" property="fil020RepairDspFlg" value="1">
    <th width="0%" class="td_type_file" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.12" /></span></th>
    </logic:equal>
    </logic:notEqual>

    </tr>

    <% String[] td_colors = new String[] {"td_type_file1", "td_type_file2"}; %>
    <logic:iterate id="historyMdl" name="fil020Form" property="historyList" indexId="idx">

    <tr class="<%= td_colors[idx.intValue() % 2] %>">
    <td class="prj_td" align="left" nowrap><bean:write name="historyMdl" property="ffrEdate" /></td>
    <td class="prj_td" align="left" nowrap>
    <logic:equal name="historyMdl" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <s><span class="text_base"><bean:write name="historyMdl" property="usrSeiMei" /></span></s>
    </logic:equal>
    <logic:notEqual name="historyMdl" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <span class="text_base"><bean:write name="historyMdl" property="usrSeiMei" /></span>
    </logic:notEqual>
    </td>
    <td class="prj_td" align="left">

    <logic:notEqual name="historyMdl" property="binSid" value="0">
      <a href="#" onclick="fileDl('fileDownloadReki', '<bean:write name="historyMdl" property="binSid" />');">
      <span class="text_link2"><bean:write name="historyMdl" property="ffrName" /></span>
      </a>
    </logic:notEqual>

    <logic:equal name="historyMdl" property="binSid" value="0">
      <span class="text_blue"><bean:write name="historyMdl" property="ffrName" /></span>
    </logic:equal>


    </td>
    <td class="prj_td" align="center" nowrap>
      <span class="text_base">
      <logic:equal name="historyMdl" property="ffrKbn" value="<%= rekiKbnNew %>"><gsmsg:write key="cmn.new" /></logic:equal>
      <logic:equal name="historyMdl" property="ffrKbn" value="<%= rekiKbnUpdate %>"><gsmsg:write key="cmn.update" /></logic:equal>
      <logic:equal name="historyMdl" property="ffrKbn" value="<%= rekiKbnRepair %>"><gsmsg:write key="fil.12" /></logic:equal>
      <logic:equal name="historyMdl" property="ffrKbn" value="<%= rekiKbnDelete %>"><gsmsg:write key="cmn.delete" /></logic:equal>
      </span>
    </td>

    <td class="prj_td" align="left">
      <span class="text_base"><bean:write name="historyMdl" property="ffrUpCmt" filter="false" /></span>
    </td>

    <logic:equal name="fil020Form" property="fil020FileWriteFlg" value="1">
    <td class="prj_td" align="center">
      <logic:equal name="historyMdl" property="repairBtnDspFlg" value="true">
      <input type="button" value="<gsmsg:write key="fil.fil070.2" />" class="btn_base1s" onClick="fil020RepairClick(<bean:write name="historyMdl" property="fdrSid" />, <bean:write name="historyMdl" property="ffrVersion" />);">
      </logic:equal>
    </td>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020FileWriteFlg" value="1">
    <logic:equal name="fil020Form" property="fil020RepairDspFlg" value="1">
    <td class="prj_td" align="center">
      <logic:equal name="historyMdl" property="repairBtnDspFlg" value="true">
      <input type="button" value="<gsmsg:write key="fil.fil070.2" />" class="btn_base1s" onClick="fil020RepairClick(<bean:write name="historyMdl" property="fdrSid" />, <bean:write name="historyMdl" property="ffrVersion" />);">
      </logic:equal>
    </td>
    </logic:equal>
    </logic:notEqual>
    </tr>
    </logic:iterate>

    </table>

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td align="right">
    <bean:size id="count2" name="fil020Form" property="fil020PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil020Slt_page2" onchange="fil020changePage(2);" styleClass="text_i">
          <html:optionsCollection name="fil020Form" property="fil020PageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
    </logic:greaterThan>
      </td>
      </tr>
      </table>


    </logic:notEmpty>

    </logic:equal>

    <logic:equal name="fil020Form" property="fil020DspMode" value="1">
    <!--アクセス制限-->
    <table width="100%" class="tl0 prj_tbl_base_2" border="0" cellpadding="0">
    <tr>
    <td width="10%" align="center" class="td_type_filetab2" onclick="fil020TabChange('fil020tabChange', '0');" nowrap><span class="text_white_s"><gsmsg:write key="fil.fil020.2" /></span></td>
    <td width="10%" align="center" class="td_type_filetab1" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.fil020.3" /></span></td>
    <td width="60%" class="td_type_border_bottom"></td>
    <td width="20%" align="right" class="td_type_border_bottom">
    <bean:size id="count1" name="fil020Form" property="fil020PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil020Slt_page1" onchange="fil020changePage(1);" styleClass="text_i">
          <html:optionsCollection name="fil020Form" property="fil020PageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
      </td>
      </tr>
      </table>
    </logic:greaterThan>
    </td>


    </tr>
    <tr>
    <td colspan="4" class="td_type_filetab1_u"><img src="../project/images/spacer.gif" width="1px" height="5px" border="0"></td>
    </tr>
    </table>

    <logic:empty name="fil020Form" property="accessList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td width="100%" class="td_type_file">
    <span class="text_base"><gsmsg:write key="fil.fil020.5" /></span>
    </td>
    </table>
    </logic:empty>

    <logic:notEmpty name="fil020Form" property="accessList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">

    <tr>
    <th width="20%" class="td_type_file">
    <logic:equal name="fil020Form" property="fil020SortKey" value="2">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(2, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="2">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
    </logic:notEqual>
    </th>

    <th width="30%" class="td_type_file">
    <logic:equal name="fil020Form" property="fil020SortKey" value="1">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(1, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="1">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" /></span></a>
    </logic:notEqual>
    </th>

    <th width="20%" class="td_type_file">
    <logic:equal name="fil020Form" property="fil020SortKey" value="3">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.post" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(3, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.post" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="3">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.post" /></span></a>
    </logic:notEqual>
    </th>

    <th width="15%" class="td_type_file">
    <logic:equal name="fil020Form" property="fil020SortKey" value="4">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(4, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.admin" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(4, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.admin" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="4">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(4, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.admin" /></span></a>
    </logic:notEqual>
    </th>

    <th width="15%" class="td_type_file">
    <logic:equal name="fil020Form" property="fil020SortKey" value="5">
      <logic:equal name="fil020Form" property="fil020OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil020TitleClick(5, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.edit.permissions" />▲</span></a></logic:equal>
      <logic:equal name="fil020Form" property="fil020OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil020TitleClick(5, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.edit.permissions" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil020Form" property="fil020SortKey" value="5">
      <a href="javascript:void(0);" onClick="return fil020TitleClick(5, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.edit.permissions" /></span></a>
    </logic:notEqual>
    </th>

    </tr>

    <% String[] td_colors = new String[] {"td_type_file1", "td_type_file2"}; %>
    <logic:iterate id="accessMdl" name="fil020Form" property="accessList" indexId="idx">

    <tr class="<%= td_colors[idx.intValue() % 2] %>">
    <td class="prj_td" align="left"><bean:write name="accessMdl" property="usiSyainNo" /></td>
    <td class="prj_td" align="left"><span class="text_base"><bean:write name="accessMdl" property="usiSei" />&nbsp;<bean:write name="accessMdl" property="usiMei" /></span></td>
    <td class="prj_td" align="left"><span class="text_base"><bean:write name="accessMdl" property="usiYakusyoku" /></span></td>
    <td class="prj_td" align="center">
    <logic:equal name="accessMdl" property="cabinetAdminKbn" value="1">
    <img src="../common/images/check.gif" alt="">
    </logic:equal>
    </td>
    <td class="prj_td" align="center">
    <logic:equal name="accessMdl" property="cabinetAccessKbn" value="0">
    <img src="../file/images/icon_stop.gif" alt="">
    </logic:equal>
    <logic:equal name="accessMdl" property="cabinetAccessKbn" value="1">
    &nbsp;
    </logic:equal>
    </td>
    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>



  </td>
  </tr>



  <tr>
  <td align="right" width="0%">

      <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td align="left">
      <img src="../common/images/check.gif" alt=""><span class="text_base">：<gsmsg:write key="cmn.admin" /></span>
      <img src="../file/images/icon_stop.gif" alt=""><span class="text_base">：<gsmsg:write key="fil.fil020.6" /></span>
      </td>
      <td align="right">
    <bean:size id="count2" name="fil020Form" property="fil020PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil020Slt_page2" onchange="fil020changePage(2);" styleClass="text_i">
          <html:optionsCollection name="fil020Form" property="fil020PageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
    </logic:greaterThan>
      </td>
      </tr>
      </table>

  </td>
  </tr>

  </logic:equal>
  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
  <logic:equal name="fil020Form" property="fil020WriteFlg" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="fil020CabinetEdit('fil020edit');">
  </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil020back');">
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>