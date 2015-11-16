<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String shortcutOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.SHORTCUT_OFF);
   String shortcutOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.SHORTCUT_ON);
   String rekiKbnNew = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_NEW);
   String rekiKbnUpdate = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_UPDATE);
   String rekiKbnRepair = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_REPAIR);
   String rekiKbnDelete = String.valueOf(jp.groupsession.v2.fil.GSConstFile.REKI_KBN_DELETE);

%>

<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.57" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil070.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03">
<html:form action="/file/fil070">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="backDspLow" />
<html:hidden property="filSearchWd" />

<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />

<html:hidden property="fil070DspMode" />
<html:hidden property="fil070SortKey" />
<html:hidden property="fil070OrderKey" />
<html:hidden property="fil070ShortcutKbn" />
<html:hidden property="fil070SltDirSid" />
<html:hidden property="fil070SltDirVer" />
<html:hidden property="fil070DirSid" />
<html:hidden property="fil070ParentDirSid" />

<html:hidden property="fileSid" />

<input type="hidden" name="fil080DirSid" value="">

<html:hidden name="fil070Form" property="fil100SltCabinetSid" />
<html:hidden name="fil070Form" property="fil100ChkTrgFolder" />
<html:hidden name="fil070Form" property="fil100ChkTrgFile" />
<html:hidden name="fil070Form" property="fil100SearchMode" />
<html:hidden name="fil070Form" property="fil100ChkWdTrgName" />
<html:hidden name="fil070Form" property="fil100ChkWdTrgBiko" />
<html:hidden name="fil070Form" property="fil100ChkWdTrgText" />
<html:hidden name="fil070Form" property="fileSearchfromYear" />
<html:hidden name="fil070Form" property="fileSearchfromMonth" />
<html:hidden name="fil070Form" property="fileSearchfromDay" />
<html:hidden name="fil070Form" property="fileSearchtoYear" />
<html:hidden name="fil070Form" property="fileSearchtoMonth" />
<html:hidden name="fil070Form" property="fileSearchtoDay" />
<html:hidden name="fil070Form" property="fil100ChkOnOff" />

<html:hidden name="fil070Form" property="fil100SvSltCabinetSid" />
<html:hidden name="fil070Form" property="fil100SvChkTrgFolder" />
<html:hidden name="fil070Form" property="fil100SvChkTrgFile" />
<html:hidden name="fil070Form" property="fil100SvSearchMode" />
<html:hidden name="fil070Form" property="fil100SvChkWdTrgName" />
<html:hidden name="fil070Form" property="fil100SvChkWdTrgBiko" />
<html:hidden name="fil070Form" property="fil100SvChkWdTrgText" />
<html:hidden name="fil070Form" property="fil100SvChkWdKeyWord" />
<html:hidden name="fil070Form" property="fileSvSearchfromYear" />
<html:hidden name="fil070Form" property="fileSvSearchfromMonth" />
<html:hidden name="fil070Form" property="fileSvSearchfromDay" />
<html:hidden name="fil070Form" property="fileSvSearchtoYear" />
<html:hidden name="fil070Form" property="fileSvSearchtoMonth" />
<html:hidden name="fil070Form" property="fileSvSearchtoDay" />
<html:hidden name="fil070Form" property="fil100SvChkOnOff" />
<html:hidden name="fil070Form" property="fil100sortKey" />
<html:hidden name="fil070Form" property="fil100orderKey" />
<html:hidden name="fil070Form" property="fil100pageNum1" />
<html:hidden name="fil070Form" property="fil100pageNum2" />
<html:hidden name="fil070Form" property="fil240PageNum" />
<html:hidden name="fil070Form" property="backDspCall" />
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil070Form" property="fil070DspMode" />">
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
        [ <gsmsg:write key="fil.57" /> ]
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
    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="1">
    <logic:equal name="fil070Form" property="fil070FileLockKbn" value="0">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="MoveToFileEdit('<bean:write name="fil070Form" property="fil070DirSid" />');">
    </logic:equal>
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil070back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="1">
    <logic:equal name="fil070Form" property="fil070FileLockKbn" value="1">
    <table width="100%">
    <tr>
    <td align="left">
      <bean:define id="lockUser" name="fil070Form" property="fil070FileLockUser" type="java.lang.String" />
      <img src="../file/images/edit_locked.gif" alt=""><span class="text_r1"><gsmsg:write key="fil.fil070.3" arg0="<%= lockUser %>" /></span>
    </td>
    </tr>
    </table>
    </logic:equal>
    </logic:equal>

    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="0">
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:equal>

    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="1">
    <logic:equal name="fil070Form" property="fil070FileLockKbn" value="0">
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:equal>
    </logic:equal>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.folder" /></span>
    </td>
    <td align="left" class="td_sub_detail">
    <img src="../file/images/icon_folder.gif" border="0" alt="" style="vertical-align:middle;">
    <span class="text_base_prj"><bean:write name="fil070Form" property="fil070FolderPath" /></span>
    &nbsp;<input type="button" value="<gsmsg:write key="fil.fil050.1" />" class="btn_base1s" onClick="buttonPush('fil070dsp');">
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>

    <td class="td_sub_detail" align="left" width="80%">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>

      <logic:notEmpty name="fil070Form" property="fil070FileLabelList">
      <logic:iterate id="file" name="fil070Form" property="fil070FileLabelList">

        <a href="#" onclick="fileDl('fileDownload', '<bean:write name="file" property="value" />');">
        <span class="text_link2"><bean:write name="file" property="label" /></span>
        </a>
        <br>

      </logic:iterate>
      </logic:notEmpty>

      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.size" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><bean:write name="fil070Form" property="fil070FileSize" /></span>
    </td>
    </tr>

    <logic:equal name="fil070Form" property="admVerKbn" value="1">
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.6" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><bean:write name="fil070Form" property="fil070VerKbn" /></span>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><bean:write name="fil070Form" property="fil070Biko" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.2" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <logic:equal name="fil070Form" property="fil070ShortcutKbn" value="<%= shortcutOff %>">
      <span class="text_base"><gsmsg:write key="fil.105" /></span>&nbsp;&nbsp;&nbsp;
      <input type="button" class="btn_scut_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('shortcutOn');">
      </logic:equal>

      <logic:equal name="fil070Form" property="fil070ShortcutKbn" value="<%= shortcutOn %>">
      <span class="text_base"><gsmsg:write key="fil.106" /></span>&nbsp;&nbsp;&nbsp;
      <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('shortcutOff');">
      </logic:equal>
    </td>
    </tr>

    </table>

    <img src="../project/images/spacer.gif" width="1px" height="10px" border="0"><br>

    <logic:equal name="fil070Form" property="fil070DspMode" value="0">
    <!--更新履歴-->
    <table width="100%" class="tl0 prj_tbl_base_2" border="0" cellpadding="0">
    <tr>
    <td width="10%" align="center" class="td_type_filetab1" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.fil020.2" /></span></td>
    <td width="10%" align="center" class="td_type_filetab2" onclick="fil070TabChange('fil070tabChange', '1');" nowrap> <span class="text_white_s"><gsmsg:write key="fil.fil020.3" /></span></td>
    <td width="60%" class="td_type_border_bottom"></td>

    <td width="20%" align="right" class="td_type_border_bottom">
    <bean:size id="count1" name="fil070Form" property="fil070PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil070PageNum1" onchange="fil070changePage(1);" styleClass="text_i">
          <html:optionsCollection name="fil070Form" property="fil070PageLabel" value="value" label="label" />
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
    <logic:empty name="fil070Form" property="fil070RekiList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td width="100%" class="td_type_file">
    <span class="text_base"><gsmsg:write key="fil.fil020.4" /></span>
    </td>
    </table>
    </logic:empty>
    <logic:notEmpty name="fil070Form" property="fil070RekiList">

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <th width="0%" class="td_type_file" nowrap>
    <logic:equal name="fil070Form" property="fil070SortKey" value="0">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(0, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(0, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="0">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(0, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.day.hour" /></span></a>
    </logic:notEqual>
    </th>

    <th width="0%" class="td_type_file" nowrap>
    <logic:equal name="fil070Form" property="fil070SortKey" value="1">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(1, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="1">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.update.user" /></span></a>
    </logic:notEqual>
    </th>

    <th width="50%" class="td_type_file" nowrap>
    <logic:equal name="fil070Form" property="fil070SortKey" value="2">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.9" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(2, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.9" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="2">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.9" /></span></a>
    </logic:notEqual>
    </th>

    <th width="0%" class="td_type_file" nowrap>
    <logic:equal name="fil070Form" property="fil070SortKey" value="3">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.operations" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(3, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.operations" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="3">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.operations" /></span></a>
    </logic:notEqual>
    </th>

    <th width="50%" class="td_type_file" nowrap>
      <span class="text_prjtodo_list_head"><gsmsg:write key="fil.11" /></span>
    </th>

    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="1">
    <th width="0%" class="td_type_file" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.12" /></span></th>
    </logic:equal>

    </tr>

    <% String[] td_colors = new String[] {"td_type_file1", "td_type_file2"}; %>

    <logic:iterate id="fil070Model" name="fil070Form" property="fil070RekiList" indexId="idx">

    <tr class="<%= td_colors[idx.intValue() % 2] %>">
    <td class="prj_td" align="left" nowrap><bean:write name="fil070Model" property="ffrEdate" /></td>
    <td class="prj_td" align="left" nowrap>
    <logic:equal name="fil070Model" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <s><span class="text_base"><bean:write name="fil070Model" property="usrSeiMei" /></span></s>
    </logic:equal>
    <logic:notEqual name="fil070Model" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <span class="text_base"><bean:write name="fil070Model" property="usrSeiMei" /></span>
    </logic:notEqual>

    </td>
    <td class="prj_td" align="left">

      <logic:notEqual name="fil070Model" property="binSid" value="0">
      <a href="#" onclick="fileDl('fileDownload', '<bean:write name="fil070Model" property="binSid" />');">
      <span class="text_link2"><bean:write name="fil070Model" property="ffrName" /></span>
      </a>
      </logic:notEqual>

      <logic:equal name="fil070Model" property="binSid" value="0">
      <span class="text_blue"><bean:write name="fil070Model" property="ffrName" /></span>
      </logic:equal>

    </td>
    <td class="prj_td" align="center" nowrap>
      <span class="text_base">
      <logic:equal name="fil070Model" property="ffrKbn" value="<%= rekiKbnNew %>"><gsmsg:write key="cmn.new" /></logic:equal>
      <logic:equal name="fil070Model" property="ffrKbn" value="<%= rekiKbnUpdate %>"><gsmsg:write key="cmn.update" /></logic:equal>
      <logic:equal name="fil070Model" property="ffrKbn" value="<%= rekiKbnRepair %>"><gsmsg:write key="fil.12" /></logic:equal>
      <logic:equal name="fil070Model" property="ffrKbn" value="<%= rekiKbnDelete %>"><gsmsg:write key="cmn.delete" /></logic:equal>
      </span>
    </td>

    <td class="prj_td" align="left"><span class="text_base"><bean:write name="fil070Model" property="ffrUpCmt" filter="false"/></span></td>

    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="1">
    <td class="prj_td" align="center">
      <logic:notEqual name="fil070Model" property="binSid" value="0">
      <logic:equal name="fil070Model" property="newVersionFlg" value="false">
      <input type="button" value="<gsmsg:write key="fil.fil070.2" />" class="btn_base1s" onClick="fil070RepairClick(<bean:write name="fil070Model" property="fdrSid" />, <bean:write name="fil070Model" property="ffrVersion" />);">
      </logic:equal>
      </logic:notEqual>
    </td>
    </logic:equal>

    </tr>

    </logic:iterate>

    </table>

    <bean:size id="count2" name="fil070Form" property="fil070PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil070PageNum2" onchange="fil070changePage(2);" styleClass="text_i">
          <html:optionsCollection name="fil070Form" property="fil070PageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
      </td>
    </tr>
    </table>
    </logic:greaterThan>

    </logic:notEmpty>
    </logic:equal>
    <logic:equal name="fil070Form" property="fil070DspMode" value="1">
    <!--アクセス制限-->
    <table width="100%" class="tl0 prj_tbl_base_2" border="0" cellpadding="0">
    <tr>
    <td width="10%" align="center" class="td_type_filetab2" onclick="fil070TabChange('fil070tabChange', '0');" nowrap><span class="text_white_s"><gsmsg:write key="fil.fil020.2" /></span></td>
    <td width="10%" align="center" class="td_type_filetab1" nowrap><span class="text_prjtodo_list_head"><gsmsg:write key="fil.fil020.3" /></span></td>
    <td width="60%" class="td_type_border_bottom"></td>

    <td width="20%" align="right" class="td_type_border_bottom">
    <bean:size id="count1" name="fil070Form" property="fil070PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil070PageNum1" onchange="fil070changePage(1);" styleClass="text_i">
          <html:optionsCollection name="fil070Form" property="fil070PageLabel" value="value" label="label" />
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
    <logic:empty name="fil070Form" property="fil070AccessList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td width="100%" class="td_type_file">
    <span class="text_base"><gsmsg:write key="fil.fil020.5" /></span>
    </td>
    </table>
    </logic:empty>
    <logic:notEmpty name="fil070Form" property="fil070AccessList">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>

    <th width="20%" class="td_type_file">
    <logic:equal name="fil070Form" property="fil070SortKey" value="2">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.employee.staff.number" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(2, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.employee.staff.number" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="2">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(2, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.employee.staff.number" /></span></a>
    </logic:notEqual>
    </th>

    <th width="35%" class="td_type_file">
    <logic:equal name="fil070Form" property="fil070SortKey" value="1">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(1, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="1">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(1, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name" /></span></a>
    </logic:notEqual>
    </th>

    <th width="25%" class="td_type_file">
    <logic:equal name="fil070Form" property="fil070SortKey" value="3">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.post" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(3, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.post" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="3">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(3, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.post" /></span></a>
    </logic:notEqual>
    </th>

    <th width="20%" class="td_type_file">
    <logic:equal name="fil070Form" property="fil070SortKey" value="5">
      <logic:equal name="fil070Form" property="fil070OrderKey" value="0"><a href="javascript:void(0);" onClick="return fil070TitleClick(5, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.edit.permissions" />▲</span></a></logic:equal>
      <logic:equal name="fil070Form" property="fil070OrderKey" value="1"><a href="javascript:void(0);" onClick="return fil070TitleClick(5, 0);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.edit.permissions" />▼</span></a></logic:equal>
    </logic:equal>
    <logic:notEqual name="fil070Form" property="fil070SortKey" value="5">
      <a href="javascript:void(0);" onClick="return fil070TitleClick(5, 1);"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.edit.permissions" /></span></a>
    </logic:notEqual>
    </th>

    </tr>

    <% String[] td_colors = new String[] {"td_type_file1", "td_type_file2"}; %>
    <logic:iterate id="accessMdl" name="fil070Form" property="fil070AccessList" indexId="idx">
    <tr class="<%= td_colors[idx.intValue() % 2] %>">
    <td class="prj_td" align="left"><bean:write name="accessMdl" property="usiSyainNo" /></td>
    <td class="prj_td" align="left"><span class="text_base"><bean:write name="accessMdl" property="usiSei" />&nbsp;<bean:write name="accessMdl" property="usiMei" /></span></td>
    <td class="prj_td" align="left"><span class="text_base"><bean:write name="accessMdl" property="usiYakusyoku" /></span></td>
    <td class="prj_td" align="center">
    <logic:equal name="accessMdl" property="accessKbn" value="0">
    <img src="../file/images/icon_stop.gif" alt="">
    </logic:equal>
    <logic:equal name="accessMdl" property="accessKbn" value="1">
    &nbsp;
    </logic:equal>
    </td>
    </tr>
    </logic:iterate>

    </table>

    </logic:notEmpty>
    <table width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <td align="left">
    <img src="../file/images/icon_stop.gif" alt=""><span class="text_base">：<gsmsg:write key="fil.fil020.6" /></span>
    </td>
    <td align="right">
    <bean:size id="count2" name="fil070Form" property="fil070PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prev');">
        <html:select property="fil070PageNum2" onchange="fil070changePage(2);" styleClass="text_i">
          <html:optionsCollection name="fil070Form" property="fil070PageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
    </logic:greaterThan>
    </td>
    </tr>
    </table>

    </logic:equal>

  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="30px" border="0"></td>
  </tr>

  <logic:notEmpty name="fil070Form" property="fileUrl">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" width="100%">
      <tr valign="center">
      <td>
      <span class="text_base"><font size="-1">URL:</font></span><input type="text" value="<bean:write name="fil070Form" property="fileUrl" />" class="text_fileUrl" readOnly="true" style="width:575px;" />
      </td>
      </tr>
    </table>
  </td>
  </tr>
  </logic:notEmpty>

  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
    <logic:equal name="fil070Form" property="fil070EditAuthKbn" value="1">
    <logic:equal name="fil070Form" property="fil070FileLockKbn" value="0">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="MoveToFileEdit('<bean:write name="fil070Form" property="fil070DirSid" />');">
    </logic:equal>
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil070back');">
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