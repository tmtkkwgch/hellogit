<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%-- CMD定数 --%>
<%
  String searchClick         = jp.groupsession.v2.fil.fil100.Fil100Action.CMD_SEARCH;
%>

<%-- 定数 --%>
<%
  String keyWordAnd          =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.KEY_WORD_KBN_AND);
  String keyWordOr           =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.KEY_WORD_KBN_OR);
  String targetFolder        =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.GET_TARGET_FOLDER);
  String targetFile          =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.GET_TARGET_FILE);
  String targetName          =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.KEYWORD_TARGET_NAME);
  String targetBiko          =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.KEYWORD_TARGET_BIKO);
  String targetText          =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.KEYWORD_TARGET_TEXT);
  String dirFolder           =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.DIRECTORY_FOLDER);
  String dirFile             =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.DIRECTORY_FILE);
  String callOn              =  String.valueOf(jp.groupsession.v2.fil.GSConstFile.CALL_ON);
%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.60" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/webSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil100.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<logic:equal name="fil100Form" property="fil100WarnDspFlg" value="1"><body class="body_03" onunload="calWindowClose();" onload="filedateNoKbn();showWarnDialog('<bean:write name="fil100Form" property="fil100ResultCount" />');"></logic:equal>
<logic:notEqual name="fil100Form" property="fil100WarnDspFlg" value="1"><body class="body_03" onunload="calWindowClose();" onload="filedateNoKbn();"></logic:notEqual>

<html:form action="/file/fil100">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden name="fil100Form" property="searchFlg" />
<html:hidden name="fil100Form" property="fil100SvSltCabinetSid" />
<html:hidden name="fil100Form" property="fil100SvChkTrgFolder" />
<html:hidden name="fil100Form" property="fil100SvChkTrgFile" />
<html:hidden name="fil100Form" property="fil100SvSearchMode" />
<html:hidden name="fil100Form" property="fil100SvChkWdTrgName" />
<html:hidden name="fil100Form" property="fil100SvChkWdTrgBiko" />
<html:hidden name="fil100Form" property="fil100SvChkWdTrgText" />
<html:hidden name="fil100Form" property="fil100SvChkWdKeyWord" />
<html:hidden name="fil100Form" property="fileSvSearchfromYear" />
<html:hidden name="fil100Form" property="fileSvSearchfromMonth" />
<html:hidden name="fil100Form" property="fileSvSearchfromDay" />
<html:hidden name="fil100Form" property="fileSvSearchtoYear" />
<html:hidden name="fil100Form" property="fileSvSearchtoMonth" />
<html:hidden name="fil100Form" property="fileSvSearchtoDay" />
<html:hidden name="fil100Form" property="fil100SvChkOnOff" />
<html:hidden name="fil100Form" property="fil100sortKey" />
<html:hidden name="fil100Form" property="fil100orderKey" />
<html:hidden name="fil100Form" property="binSid" />
<html:hidden name="fil100Form" property="fil100ResultCount" />

<html:hidden property="backDsp" />
<html:hidden property="backDspLow" />

<html:hidden property="fil040SortKey" />
<html:hidden property="fil040OrderKey" />

<html:hidden property="fil010SelectDirSid" />
<html:hidden property="fil010SelectCabinet" />

<input type="hidden" name="fil050DirSid" value="">
<input type="hidden" name="fil070DirSid" value="">
<input type="hidden" name="fil050ParentDirSid" value="">
<input type="hidden" name="fil070ParentDirSid" value="">
<input type="hidden" name="fil100WarnOk" value="">


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%">
    <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
  <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="fil.60" /> ]</td>
  <td width="100%" class="header_white_bg">
  </td>
  <td width="0%" class="header_white_bg">
    <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>
</td>
</tr>

<tr>
<td>
  <table cellpadding="0" cellspacing="5" border="0" width="100%">
  <tr>
  <td width="100%" align="right">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil100back');">
  </td>
  </tr>
  </table>
</td>
</tr>

  <logic:messagesPresent message="false">
  <tr>
  <td>
    <table width="100%">
    <tr><td align="left"><html:errors/></td></tr>
    </table>
  </td>
  </tr>
  </logic:messagesPresent>

<tr>
<td>
  <!-- ページコンテンツ start -->
  <table width="100%" class="tl0 prj_tbl_base">
  <tr>
    <td width="100%" height="30px" colspan="4" class="table_bg_7D91BD_search">
      <img src="../circular/images/spacer.gif" width="1" height="20" align="left">
      <img src="../common/images/search_icon.gif" class="img_bottom" alt="<gsmsg:write key="cmn.advanced.search" />"><span class="text_tlw3"><gsmsg:write key="cmn.advanced.search" /></span>
    </td>
  </tr>

  <tr>
  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="fil.23" /></th>
  <td width="40%" class="td_sub_detail">
  <logic:notEmpty name="fil100Form" property="cabinetLabel">
    <html:select property="fil100SltCabinetSid" styleClass="select01">
      <html:optionsCollection name="fil100Form" property="cabinetLabel" value="value" label="label" />
    </html:select>
  </logic:notEmpty>
  </td>

  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.target" /></th>
  <td width="40%" class="td_sub_detail">
  <span class="text_base2">
    <html:checkbox styleId="search_scope_01" name="fil100Form" property="fil100ChkTrgFolder" value="<%= targetFolder %>" /><label for="search_scope_01"><gsmsg:write key="cmn.folder" /></label>
    <html:checkbox styleId="search_scope_02" name="fil100Form" property="fil100ChkTrgFile" value="<%= targetFile %>" /><label for="search_scope_02"><gsmsg:write key="cmn.file" /></label>
  </span>
  </td>
  </tr>

  <tr>
  <th class="td_gray text_bb2"><gsmsg:write key="cmn.keyword" /></th>
  <td class="td_sub_detail">
    <html:text name="fil100Form" property="filSearchWd" styleClass="text_base" maxlength="100" style="width:283px;" /><br>
    <span class="text_base2">
      <html:radio property="fil100SearchMode" value="<%= keyWordAnd %>" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all" />(AND)</label>&nbsp;<html:radio property="fil100SearchMode" value="<%= keyWordOr %>" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.containing.either" />(OR)</label>
    </span>
  </td>

  <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.search2" /></th>
  <td width="40%" class="td_sub_detail">
  <span class="text_base2">
    <html:checkbox styleId="search_scope_03" name="fil100Form" property="fil100ChkWdTrgName" value="<%= targetName %>" /><label for="search_scope_03"><gsmsg:write key="fil.fil100.1" /></label>

    <logic:equal name="fil100Form" property="fileSearchFlg" value="1">
      <html:checkbox styleId="search_scope_05" name="fil100Form" property="fil100ChkWdTrgText" value="<%= targetText %>" /><label for="search_scope_05"><gsmsg:write key="fil.fil100.2" /></label>
    </logic:equal>

    <html:checkbox styleId="search_scope_04" name="fil100Form" property="fil100ChkWdTrgBiko" value="<%= targetBiko %>" /><label for="search_scope_04"><gsmsg:write key="cmn.memo" /></label>
  </span>
  </td>
  </tr>

  <tr>
  <td align="center" class="td_gray"><span class="text_bb2"><gsmsg:write key="cmn.update.day.hour" /></span></td>
  <td class="td_type20" colspan="3">
  <span class="text_base2"><html:checkbox name="fil100Form" styleId="fileSearchdateNoKbn" property="fil100ChkOnOff" value="1" onclick="filedateNoKbn();" /><label for="fileSearchdateNoKbn"><gsmsg:write key="cmn.without.specifying" /></label></span>
  <logic:notEmpty name="fil100Form" property="yearLabel">
  <html:select property="fileSearchfromYear" styleId="srchFrYear" size="1">
    <html:optionsCollection name="fil100Form" property="yearLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>

  <logic:notEmpty name="fil100Form" property="monthLabel">
  <html:select property="fileSearchfromMonth" styleId="srchFrMonth" size="1">
    <html:optionsCollection name="fil100Form" property="monthLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>

  <logic:notEmpty name="fil100Form" property="dayLabel">
  <html:select property="fileSearchfromDay" styleId="srchFrDay" size="1">
    <html:optionsCollection name="fil100Form" property="dayLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>

  <input type="button" value="Cal" name="fromCalendarBtn" onclick="wrtCalendar(this.form.srchFrDay, this.form.srchFrMonth, this.form.srchFrYear);" class="calendar_btn">
        ～
  <logic:notEmpty name="fil100Form" property="yearLabel">
  <html:select property="fileSearchtoYear" styleId="srchToYear" size="1">
    <html:optionsCollection name="fil100Form" property="yearLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>

  <logic:notEmpty name="fil100Form" property="monthLabel">
  <html:select property="fileSearchtoMonth" styleId="srchToMonth" size="1">
    <html:optionsCollection name="fil100Form" property="monthLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>

  <logic:notEmpty name="fil100Form" property="dayLabel">
  <html:select property="fileSearchtoDay" styleId="srchToDay" size="1">
    <html:optionsCollection name="fil100Form" property="dayLabel" value="value" label="label" />
  </html:select>
  </logic:notEmpty>

  <input type="button" value="Cal" name="toCalendarBtn" onclick="wrtCalendar(this.form.srchToDay, this.form.srchToMonth, this.form.srchToYear);" class="calendar_btn">

  </td>
  </tr>

  </table>
</td>
</tr>

<tr>
<td>
  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td>
  <div><img src="../common/images/spacer.gif" width="1" height="10"></div>
  <div align="right" class="seach_function_l"><input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onclick="buttonPush('<%= searchClick %>');"></div>
  </td>
  </tr>
  </table>
</td>
</tr>

<tr>
<td>
  <!-- ページコンテンツ start -->

  <logic:equal name="fil100Form" property="fil100pageDspFlg" value="true">
    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
      <td height="45px" valign="bottom" align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('fil100PagePreview');">
        <html:select property="fil100pageNum1" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="fil100Form" property="pageList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('fil100PageNext');">
      </td>
      </tr>
    </table>
  </logic:equal>
</td>
</tr>

<tr>
<td>
  <logic:notEmpty name="fil100Form" property="resultList">
  <!-- 一覧 -->
  <bean:define id="sortKey" name="fil100Form" property="fil100sortKey" />
  <bean:define id="orderKey" name="fil100Form" property="fil100orderKey" />

  <gsmsg:define id="namae" msgkey="cmn.name4" />
  <gsmsg:define id="saizu" msgkey="cmn.size" />
  <gsmsg:define id="tuuti" msgkey="fil.1" />
  <gsmsg:define id="nitiji" msgkey="cmn.update.day.hour" />
  <gsmsg:define id="kousinsya" msgkey="cmn.update.user" />


  <% int iSortKey = ((Integer) sortKey).intValue();   %>
  <% int iOrderKey = ((Integer) orderKey).intValue(); %>
  <% int[] sortKeyList = new int[] { jp.groupsession.v2.fil.GSConstFile.SORT_NAME, jp.groupsession.v2.fil.GSConstFile.SORT_SIZE, jp.groupsession.v2.fil.GSConstFile.SORT_CALL, jp.groupsession.v2.fil.GSConstFile.SORT_EDATE, jp.groupsession.v2.fil.GSConstFile.SORT_EUSR}; %>
  <% String[] title_width = new String[] {"45%", "10%", "10%", "15%", "15%"};          %>
  <% String[] titleList = new String[] {namae, saizu, tuuti, nitiji, kousinsya}; %>

  <table class="tl0 prj_tbl_base3" width="100%" cellpadding="0" cellspacing="0">
    <tr>
    <% int order_asc = jp.groupsession.v2.fil.GSConstFile.ORDER_KEY_ASC; %>
    <% int order_desc = jp.groupsession.v2.fil.GSConstFile.ORDER_KEY_DESC; %>
    <% for (int i = 0; i < sortKeyList.length; i++) {   %>
    <%   String title = titleList[i];                   %>
    <%   String skey = String.valueOf(sortKeyList[i]);  %>
    <%   String order = String.valueOf(order_asc);      %>
    <%   if (iSortKey == sortKeyList[i]) {              %>
    <%     if (iOrderKey == order_desc) {               %>
    <%       title = title + "▼";                      %>
    <%     } else {                                     %>
    <%       title = title + "▲";                      %>
    <%       order = String.valueOf(order_desc);        %>
    <%     }                                            %>
    <%   }                                              %>
    <th width="<%= title_width[i] %>" class="table_bg_7D91BD"><a href="#" onClick="return sort(<%= skey %>, <%= order %>);"><span class="text_tlw"><%= title %></span></a></th>
    <% } %>

    <th width="5%" class="table_bg_7D91BD"><span class="text_tlw"></span></th>
    </tr>

  <% String[] detailClass = {"td_type_file1", "td_type_file2"}; %>


  <logic:iterate id="detailMdl" name="fil100Form" property="resultList" indexId="idx">

  <tr class="<%= detailClass[idx.intValue() % 2] %>">

  <!-- ディレクトリ名称 -->
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFolder %>">
    <td class="prj_td" align="left">
      <img src="../file/images/icon_folder.gif" border="0" alt="" style="vertical-align:bottom;">&nbsp;
      <span class="text_blue"><a href="#" onclick="MoveToFolderDetail('<bean:write name="detailMdl" property="fdrSid" />','<bean:write name="detailMdl" property="fcbSid" />','<bean:write name="detailMdl" property="fdrParentSid" />');"><bean:write name="detailMdl" property="fdrName" /></a></span>
    </td>
  </logic:equal>
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFile %>">
    <td class="prj_td" align="left">
      <img src="../file/images/page.gif" border="0" alt="" style="vertical-align:bottom;">&nbsp;
      <span class="text_blue"><a href="#" onclick="downLoad('fileNameClick', '<bean:write name="detailMdl" property="binSid" />');"><bean:write name="detailMdl" property="fdrName" /></a></span>
     </td>
  </logic:equal>

  <!-- サイズ -->
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFolder %>">
    <td class="prj_td" align="right"></td>
  </logic:equal>
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFile %>">
    <td class="prj_td" align="right"><span class="text_base"><bean:write name="detailMdl" property="fflFileSize" /></span></td>
  </logic:equal>

  <!-- 更新通知 -->
  <logic:equal name="detailMdl" property="callOn" value="-1">
    <td class="prj_td" align="center"></td>
  </logic:equal>

  <logic:equal name="detailMdl" property="callOn" value="<%= callOn %>">
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFolder %>">
    <td class="prj_td" align="center">&nbsp;<img src="../file/images/icon_call.gif" border="0" alt="" style="vertical-align:bottom;">&nbsp;</td>
  </logic:equal>
  </logic:equal>

  <!-- 更新日時 -->
  <td class="prj_td" align="left"><span class="text_base"><bean:write name="detailMdl" property="fdrEdate" /></span></td>

  <!-- 更新者 -->
  <td class="prj_td" align="left">
  <logic:equal name="detailMdl" property="usrJKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <s><span class="text_base"><bean:write name="detailMdl" property="usrSei" />&nbsp;<bean:write name="detailMdl" property="usrMei" /></span></s>
  </logic:equal>
  <logic:notEqual name="detailMdl" property="usrJKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
    <span class="text_base"><bean:write name="detailMdl" property="usrSei" />&nbsp;<bean:write name="detailMdl" property="usrMei" /></span>
  </logic:notEqual>
  </td>

  <!-- 詳細ボタン-->
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFolder %>">
    <td class="prj_td" align="center"><input type="button" class="btn_base1" value="<gsmsg:write key="cmn.detail" />" onclick="MoveToFolderDetail('<bean:write name="detailMdl" property="fdrSid" />','<bean:write name="detailMdl" property="fcbSid" />','<bean:write name="detailMdl" property="fdrParentSid" />');"></td>
  </logic:equal>
  <logic:equal name="detailMdl" property="fdrKbn" value="<%= dirFile %>">
    <td class="prj_td" align="center"><input type="button" class="btn_base1" value="<gsmsg:write key="cmn.detail" />" onclick="MoveToFileDetail('<bean:write name="detailMdl" property="fdrSid" />','<bean:write name="detailMdl" property="fcbSid" />','<bean:write name="detailMdl" property="fdrParentSid" />');"></td>
  </logic:equal>
  </tr>

  </logic:iterate>

  </table>

  </tr>
  </table>


  <logic:notEqual name="fil100Form" property="fil100searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
  <logic:notEmpty name="fil100Form" property="filSearchWd">
  <table width="100%" cellpadding="5" cellspacing="0">
  <tr>
    <td align="left">
    <bean:define id="searchKeyword" name="fil100Form" property="fil100HtmlSearchWord" type="java.lang.String" />
    <a href="javascript:void(0);" class="" onmouseover="overSearch();" onmouseout="outSearch();" onClick="webSearch('<bean:write name="fil100Form" property="fil100WebSearchWord" />');">
      <span id="webSearchArea" class="text_normal">
      <gsmsg:write key="cmn.websearch" arg0="<%= searchKeyword %>" />
      </span>
    </a>
    </td>
  </tr>
  </table>
  </logic:notEmpty>
  </logic:notEqual>

  </logic:notEmpty>
</td>
</tr>

<tr>
<td>

  <logic:equal name="fil100Form" property="fil100pageDspFlg" value="true">
  <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td height="45px" valign="bottom" align="right">
      <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('fil100PagePreview');">
      <html:select property="fil100pageNum2" onchange="changePage(2);" styleClass="text_i">
        <html:optionsCollection name="fil100Form" property="pageList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('fil100PageNext');">
    </td>
    </tr>
  </table>
  </logic:equal>

</td>
</tr>

</table>

<div id="delMailMsgPop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%" valign="middle">
        <div style="vertical-align:middle;overflow:auto;width:500px;height:200px;">
          <table><tr><td id="delMailMsgArea" style="vertical-align:middle;width:500px;height:200px;font-weight:bold;font-size:14px;"></td></tr></table>
        </div>
      </td>
    </tr>
  </table>

</div>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>