<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%-- 評価画像定義 --%>
<%
  java.util.HashMap imgMap = new java.util.HashMap();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
  String rank1 = gsMsg.getMessage(request, "bmk.11");
  String rank2 = gsMsg.getMessage(request, "bmk.10");
  String rank3 = gsMsg.getMessage(request, "bmk.09");
  String rank4 = gsMsg.getMessage(request, "bmk.08");
  String rank5 = gsMsg.getMessage(request, "bmk.07");

  imgMap.put("0", "&nbsp;");
  imgMap.put("1", "<img src=\"../bookmark/images/icon_star1.gif\" alt=\"" + rank1 + "\" class=\"img_bottom\">");
  imgMap.put("2", "<img src=\"../bookmark/images/icon_star2.gif\" alt=\"" + rank2 + "\" class=\"img_bottom\">");
  imgMap.put("3", "<img src=\"../bookmark/images/icon_star3.gif\" alt=\"" + rank3 + "\" class=\"img_bottom\">");
  imgMap.put("4", "<img src=\"../bookmark/images/icon_star4.gif\" alt=\"" + rank4 + "\" class=\"img_bottom\">");
  imgMap.put("5", "<img src=\"../bookmark/images/icon_star5.gif\" alt=\"" + rank5 + "\" class=\"img_bottom\">");

%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="bmk.bmk010.11" /></title>
<link rel=stylesheet href="../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<theme:css filename="theme.css"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmk010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmkcommon.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<%
  String open = gsMsg.getMessage(request, "cmn.open.more");
  String close = gsMsg.getMessage(request, "cmn.close.more");
 %>
<script>
function hide_comment(id){
    var d = $('#comment' + id)[0];
    var s = $('#disp_comment' + id)[0];

    if (d.style.height == "auto") {
        d.style.height = '1.5em';
        s.innerHTML = "<%= open %>";
    } else {
        d.style.height = 'auto';
        s.innerHTML = "<%= close %>";
    }
}
</script>

</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/bookmark/bmk010">

<input type="hidden" name="CMD" value="">

<html:hidden property="procMode" />
<html:hidden property="editBmuSid" />
<html:hidden property="editBmkSid" />
<html:hidden property="returnPage" value="bmk010" />

<html:hidden property="bmk010mode" />
<html:hidden property="bmk010orderKey" />
<html:hidden property="bmk010sortKey" />
<html:hidden property="bmk010searchLabel" />
<html:hidden property="bmk010page" />
<input type="hidden" name="helpPrm" value="<bean:write name="bmk010Form" property="bmk010mode" />">

<bean:define id="bmkMode" name="bmk010Form" property="bmk010mode" type="java.lang.Integer" />
<% if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) { %>
<html:hidden property="bmk010groupSid" />
<html:hidden property="bmk010userSid" />
<% } else if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) { %>
<html:hidden property="bmk010userSid" />
<% } %>

<logic:notEmpty name="bmk010Form" property="bmk010SelectedDelSid" scope="request">
<logic:iterate id="item" name="bmk010Form" property="bmk010SelectedDelSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td>

  <table cellpadding="0" cellspacing="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <bean:define id="bmkMode" name="bmk010Form" property="bmk010mode" type="java.lang.Integer" />
    <% if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) { %>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="bmk.30" /> ]</td>
    <% } else if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) { %>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="bmk.51" /> ]</td>
    <% } else if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) { %>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="bmk.34" /> ]</td>
    <% } %>

    <td width="100%" class="header_white_bg">
    <logic:equal name="bmk010Form" property="bmk010viewAdminBtn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.POW_YES) %>'>
    <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="return buttonPush('adminMenu');">
    </logic:equal>
    <input type="button" name="btn_kojn" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="return buttonPush('kojinMenu');"></td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="5" border="0" alt=""></td>
    </tr>

    <tr>

    <td valign="top" width="22%" nowrap>

      <div style="margin-top: 30px;"></div>


      <!-- ラベル一覧 -->
      <table class="tl0" width="100%" >
      <tbody>
      <tr>
      <th class="table_bg_7D91BD" style="border-right:0px;" align="center" width="33%">
      </th>
      <th class="table_bg_7D91BD" style="border-left:0px;border-right:0px;" align="center" width="33%">
      <span class="text_tlw"><gsmsg:write key="cmn.label" /></span>
      </th>
      <!-- ラベル設定ボタン -->
      <th class="table_bg_7D91BD" style="border-left:0px;" align="right" width="33%">
      <logic:equal name="bmk010Form" property="bmk010editPow" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.POW_YES) %>'>

      <input type="button" onClick="return buttonPush('labelList');" style="border:0px;color:#000066;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
      </logic:equal>
      </th>
      </tr>

      <logic:notEmpty name="bmk010Form" property="bmk010LabelList">

      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type1", "td_type25_5"}; %>

      <logic:iterate id="labelMdl" name="bmk010Form" property="bmk010LabelList" indexId="idx">
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>
      <tr>
      <td class="<%= tdColor %>" style="border-right:0px;" colspan="2">
        <a href="javascript:void(0);" onclick="return selectLabel('<bean:write name="labelMdl" property="blbSid" />');">
        <span class="text_link">
        <bean:write name="labelMdl" property="blbName" />&nbsp;(<bean:write name="labelMdl" property="bmkLabelCount" />)
        </span>
        </a>
      </td>
      <td class="<%= tdColor %>" align="right" valign="middle" style="border-left:0px;padding:0">
        <logic:notEqual name="bmk010Form" property="bmk010searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
        <logic:notEqual name="labelMdl" property="blbSid" value="-1">
        <table class="tl0" cellpadding="0" cellspacing="0" border="0" >
        <tr>
        <td width="0" valign="bottom" style="padding:5 0 0 0;">
        <a href="javascript:void(0);" onClick="webSearch('<bean:write name="labelMdl" property="blbNameWebSearchWord" />');"><img src="../common/images/search_web.gif" alt="" border="0"></a>
        </td>
        <td align="right" valign="middle" style="padding-right:2px;">
        <a href="javascript:void(0);" onClick="webSearch('<bean:write name="labelMdl" property="blbNameWebSearchWord" />');">
        <span class="text_search_web"><gsmsg:write key="cmn.search" /></span>
        </a>
        </td>
        </tr>
        </table>
        </logic:notEqual>
        </logic:notEqual>
      </td>
      </tr>

      </logic:iterate>
      </logic:notEmpty>

      </tbody>
      </table>


    <!-- 新着ブックマーク -->
    <logic:notEmpty name="bmk010Form" property="bmk010NewList">
    <br>

      <table class="tl0" width="100%">
      <tbody>
      <tr>
      <th class="table_bg_7D91BD" width="400" colspan="3">
        <table>
        <tr>
        <td width="99%"  align="center" nowrap>
        <span class="text_tlw" nowrap><gsmsg:write key="bmk.24" /></span>
        </td>
        <td width="1%" align="right">
        <logic:equal name="bmk010Form" property="bmk010editPow" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.POW_YES) %>'>
        <input type="button" class=btn_list_n3 value="<gsmsg:write key="cmn.list" />" onclick="buttonPush('newBookmark');">
        </logic:equal>
        </td>
        </tr>
        </table>
      </th>
      </tr>

      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type1", "td_type25_6"}; %>

      <logic:iterate id="newMdl" name="bmk010Form" property="bmk010NewList" indexId="idx">
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>
      <tr>

      <!-- 追加ボタンありのときのtd -->
      <logic:equal name="newMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" style="border-right:0px;" colspan="2">
      </logic:equal>
      <!-- 追加ボタンなしのときのtd -->
      <logic:notEqual name="newMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" colspan="3">
      </logic:notEqual>

      <!-- タイトル -->
      <a style="color:blue;" href="<bean:write name="newMdl" property="bmuUrl" />" target="_blank">
      <bean:write name="newMdl" property="bmuTitle" />
<%--
      <logic:notEmpty name="newMdl" property="bmkTitleDspList">
      <logic:iterate id="titleDsp" name="newMdl" property="bmkTitleDspList" indexId="idx2">
      <% if (idx2 > 0) { %> <br> <% } %>
      <bean:write name="titleDsp" />
      </logic:iterate>
      </logic:notEmpty>
--%>
      </a>
      <!-- 人数 -->
      <bean:define id="perCnt1" name="newMdl" property="bmkPerCount" type="java.lang.Integer" />
      <a href="javascript:void(0);" onclick="return selPerCount('<bean:write name="newMdl" property="bmuSid" />');" style="font-size:70%;color:#ff0000;background-color:#ffcccc;">
      <gsmsg:write key="bmk.23" arg0="<%= String.valueOf(perCnt1.intValue()) %>" />
      </a>
      </td>
      <!-- 追加ボタン -->
      <logic:equal name="newMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" align="right" style="border-left:0px;padding:0px 2px 0px 0px;">
      <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:20px;padding-right:0px;padding-left:0px;padding-top:6px;font-weight:bold;" class="bmk_add_btn2" value="<gsmsg:write key="cmn.add" />" onclick="return buttonPushAdd('<bean:write name="newMdl" property="bmuSid" />');">
      </td>
      </logic:equal>

      </tr>
      </logic:iterate>

      </tbody>
      </table>
    </logic:notEmpty>

    <!-- ランキング -->
    <logic:notEmpty name="bmk010Form" property="bmk010RankingList">
    <br>

      <table class="tl0" width="100%">
      <tbody>
      <tr>
      <th class="table_bg_7D91BD" align="center" width="100%" colspan="2"><span class="text_tlw"><gsmsg:write key="bmk.bmk010.01" /></span></th>
      </tr>

      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type1", "td_type25_6"}; %>

      <logic:iterate id="rankMdl" name="bmk010Form" property="bmk010RankingList" indexId="idx">
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>
      <tr>

      <!-- 追加ボタンありのときのtd -->
      <logic:equal name="rankMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" style="border-right:0px;">
      </logic:equal>
      <!-- 追加ボタンなしのときのtd -->
      <logic:notEqual name="rankMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" colspan="2">
      </logic:notEqual>

      <!-- タイトル・人数 -->
      <a style="color:blue;" href="<bean:write name="rankMdl" property="bmuUrl" />" target="_blank">
      <bean:write name="rankMdl" property="bmuTitle" />
<%--
      <logic:notEmpty name="rankMdl" property="bmkTitleDspList">
      <logic:iterate id="titleDsp" name="rankMdl" property="bmkTitleDspList" indexId="idx2">
      <% if (idx2 > 0) { %> <br> <% } %>
      <bean:write name="titleDsp" />
      </logic:iterate>
      </logic:notEmpty>
--%>
      </a>

      <bean:define id="perCnt2" name="rankMdl" property="bmkPerCount" type="java.lang.Integer" />
      <a href="javascript:void(0);" onclick="return selPerCount('<bean:write name="rankMdl" property="bmuSid" />');" style="font-size:70%;color:#ff0000;background-color:#ffcccc;">
      <gsmsg:write key="bmk.23" arg0="<%= String.valueOf(perCnt2.intValue()) %>" />
      </a>
      </td>
      <!-- 追加ボタン -->
      <logic:equal name="rankMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" align="right" style="border-left:0px;padding:0px 2px 0px 0px;">
      <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:20px;padding-right:0px;padding-left:0px;padding-top:6px;font-weight:bold;" class="bmk_add_btn2" value="<gsmsg:write key="cmn.add" />" onclick="return buttonPushAdd('<bean:write name="rankMdl" property="bmuSid" />');">
      </td>
      </logic:equal>

      </tr>
      </logic:iterate>

      </tbody>
      </table>
    </logic:notEmpty>

    </td>
    <td width="1%" valign="top" nowrap>&nbsp;</td>
    <td valign="top" width="77%">
    <!-- メッセージ -->
    <logic:messagesPresent message="false">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
      <tr>
      <td align="left"><html:errors/><br></td>
      </tr>
      </table>
    </logic:messagesPresent>

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>


      <td nowrap>
      <!-- グループコンボ -->
      <logic:notEqual name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>'>
      <logic:notEmpty name="bmk010Form" property="bmk010groupCmbList">
      <html:select name="bmk010Form" property="bmk010groupSid" onchange="buttonPush('grpChange');" styleClass="select01">
      <html:optionsCollection name="bmk010Form" property="bmk010groupCmbList" value="value" label="label" />
      </html:select>
      <input type="button" onclick="openGroupWindow(this.form.bmk010groupSid, 'bmk010groupSid', '0')" class="group_btn" value="&nbsp;&nbsp;" id="bmk010GroupBtn">
      </logic:notEmpty>
      </logic:notEqual>
      <!-- ユーザコンボ -->
      <logic:equal name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
      <logic:notEmpty name="bmk010Form" property="bmk010userCmbList">
      <html:select name="bmk010Form" property="bmk010userSid" onchange="buttonPush('usrChange');">
      <html:optionsCollection name="bmk010Form" property="bmk010userCmbList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>
      </logic:equal>
      <!-- グループ編集権限ボタン -->
      <logic:equal name="bmk010Form" property="bmk010viewGroupBtn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.POW_YES) %>'>
      <input type="button" name="btn_group" class="btn_base1" value="<gsmsg:write key="cmn.setting.permissions" />" onClick="return buttonPush('groupMenu');">
      &nbsp;
      </logic:equal>
      </td>
      <td align="right" nowrap>
      <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.new.registration" />" onClick="return buttonPushAdd(-1);">
      <input type="button" name="btn_del" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush('delete');">
      <input type="button" name="btn_lank" class="btn_rank_n1" value="<gsmsg:write key="cmn.ranking" />" onClick="return buttonPush('rankingList');">
      </td>

      </tr>

      <tr>
        <td width="0%" class="tab_head_end" colspan="2"><img src="../common/images/damy.gif" width="10" height="4" border="0" alt=""></td>
      </tr>

      <tr>

      <td width="100%" style="font-size:0%;" colspan="2" nowrap>
      <!-- 個人タブ -->
      <logic:equal name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
               <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>');"><gsmsg:write key="bmk.bmk010.07" /></a>
            </div>
        </td>

    	<td class="tab_space" nowrap>&nbsp;</td>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>');"><gsmsg:write key="cmn.group" /></a>
            </div>
        </td>

    	<td class="tab_space" nowrap>&nbsp;</td>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>');"><gsmsg:write key="bmk.bmk010.09" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
            <!-- ページング -->
        <div align="right">
              <logic:notEmpty name="bmk010Form" property="bmk010pageCmbList">
              <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="img_bottom" border="0" height="20" width="20" onClick="buttonPush('prevPage');">
              <html:select name="bmk010Form" property="bmk010pageTop" styleClass="text_i" onchange="changePage('bmk010pageTop');">
                  <html:optionsCollection name="bmk010Form" property="bmk010pageCmbList" value="value" label="label" />
              </html:select>
              <img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif"class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('nextPage');">
              </logic:notEmpty>
        </div>
          </td>
        </tr>
        </table>
      </logic:equal>
      <!-- グループタブ -->
      <logic:equal name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>'>
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>');"><gsmsg:write key="bmk.bmk010.07" /></a>
            </div>
        </td>
    	<td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>');"><gsmsg:write key="cmn.group" /></a>
            </div>
        </td>
    	<td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>');"><gsmsg:write key="bmk.bmk010.09" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
            <!-- ページング -->
        <div align="right">
              <logic:notEmpty name="bmk010Form" property="bmk010pageCmbList">
              <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('prevPage');">
              <html:select name="bmk010Form" property="bmk010pageTop" styleClass="text_i" onchange="changePage('bmk010pageTop');">
                  <html:optionsCollection name="bmk010Form" property="bmk010pageCmbList" value="value" label="label" />
              </html:select>
              <img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('nextPage');">
              </logic:notEmpty>
        </div>
          </td>
        </tr>
        </table>
      </logic:equal>
      <!-- 共有タブ -->
      <logic:equal name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>'>
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>');"><gsmsg:write key="bmk.bmk010.07" /></a>
            </div>
        </td>
    	<td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>');"><gsmsg:write key="cmn.group" /></a>
            </div>
        </td>
    	<td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeBmkKbn('<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>');"><gsmsg:write key="bmk.bmk010.09" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>
            <!-- ページング -->
        <div align="right">
              <logic:notEmpty name="bmk010Form" property="bmk010pageCmbList">
              <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('prevPage');">
              <html:select name="bmk010Form" property="bmk010pageTop" styleClass="text_i" onchange="changePage('bmk010pageTop');">
                  <html:optionsCollection name="bmk010Form" property="bmk010pageCmbList" value="value" label="label" />
              </html:select>
              <img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('nextPage');">
              </logic:notEmpty>
        </div>
          </td>
        </tr>
        </table>
      </logic:equal>
      </td>

      <!-- タブ右端 -->
      <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
      </table>

      <div class="sort_navi" style="height: 20px;background-color: #eeeeee;">
      <!-- 条件 -->
      <logic:equal name="bmk010Form" property="bmk010searchLabel" value='<%= String.valueOf(jp.groupsession.v2.bmk.bmk010.Bmk010Biz.INIT_VALUE) %>'>
      <span><gsmsg:write key="cmn.showing.all" /></span>
      </logic:equal>
      <logic:notEqual name="bmk010Form" property="bmk010searchLabel" value='<%= String.valueOf(jp.groupsession.v2.bmk.bmk010.Bmk010Biz.INIT_VALUE) %>'>
      <span><gsmsg:write key="cmn.label" /><gsmsg:write key="wml.215" /><bean:write name="bmk010Form" property="bmk010searchLabelName" />&nbsp;</span>
      <div style="float:left;">
      ／
      <a href="javascript:void(0);" onClick="return selectLabel(<%= jp.groupsession.v2.bmk.bmk010.Bmk010Biz.INIT_VALUE %>);"><gsmsg:write key="bmk.bmk010.06" /></a></div>
      </logic:notEqual>

      <!-- ソート順 -->
      <bean:define id="sortKey" name="bmk010Form" property="bmk010sortKey" type="java.lang.Integer" />
      <bean:define id="orderKey" name="bmk010Form" property="bmk010orderKey" type="java.lang.Integer" />

      <!-- 登録順 -->
      <% if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC) { %>
      <strong>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:black;font-size:13px;">
      <gsmsg:write key="tcd.tcd040.23" /><gsmsg:write key="bmk.17" />
      </a>
      </strong>
      <% } else if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC) { %>
      <strong>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC %>);" style="color:black;font-size:13px;">
      <gsmsg:write key="tcd.tcd040.22" /><gsmsg:write key="bmk.17" />
      </a>
      </strong>
      <% } else { %>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_ADATE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);">
      <gsmsg:write key="bmk.17" />
      </a>
      <% } %>
      ／
      <!-- 評価順 -->
      <% if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC) { %>
      <strong>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:black;font-size:13px;">
      <gsmsg:write key="tcd.tcd040.23" /><gsmsg:write key="bmk.06" />
      </a>
      </strong>
      <% } else if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC) { %>
      <strong>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC %>);" style="color:black;font-size:13px;">
      <gsmsg:write key="tcd.tcd040.22" /><gsmsg:write key="bmk.06" />
      </a>
      </strong>
      <% } else { %>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_SCORE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);">
      <gsmsg:write key="bmk.06" />
      </a>
      <% } %>
      ／
      <!-- タイトル順 -->
      <% if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_TITLE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC) { %>
      <strong>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_TITLE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);" style="color:black;font-size:13px;">
      <gsmsg:write key="tcd.tcd040.23" /><gsmsg:write key="bmk.bmk010.12" />
      </a>
      </strong>
      <% } else if (sortKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_TITLE && orderKey.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC) { %>
      <strong>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_TITLE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_DESC %>);" style="color:black;font-size:13px;">
      <gsmsg:write key="tcd.tcd040.22" /><gsmsg:write key="bmk.bmk010.12" />
      </a>
      </strong>
      <% } else { %>
      <a href="javascript:void(0);" onClick="return sortChange(<%= jp.groupsession.v2.bmk.GSConstBookmark.SORTKEY_TITLE %>, <%= jp.groupsession.v2.bmk.GSConstBookmark.ORDERKEY_ASC %>);">
      <gsmsg:write key="bmk.bmk010.12" />
      </a>
      <% } %>

      </div>

      <!-- ブックマーク一覧 -->
      <logic:notEmpty name="bmk010Form" property="bmk010BookmarkList">
      <logic:iterate id="bmkMdl" name="bmk010Form" property="bmk010BookmarkList" indexId="idx">
      <table class="bookmarklist" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <th>
        <table class="bookmarkurl" cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>

        <!-- 評価 -->
        <td align="center" width="0%">
        <bean:define id="imgScore"><bean:write name="bmkMdl" property="bmkScore" /></bean:define>
        <% java.lang.String key = "0";  if (imgMap.containsKey(imgScore)) { key = imgScore; } %> <%= (java.lang.String) imgMap.get(key) %>
        </td>
        <!-- タイトル -->
        <td width="100%" style="border-right:0px;">
        <a href="<bean:write name="bmkMdl" property="bmuUrl" />" target="_blank">
        <bean:write name="bmkMdl" property="bmkTitle" />
<%--
        <logic:notEmpty name="bmkMdl" property="bmkTitleDspList">
        <logic:iterate id="titleDsp" name="bmkMdl" property="bmkTitleDspList" indexId="idx2">
        <% if (idx2 > 0) { %> <br> <% } %>
        <bean:write name="titleDsp" />
        </logic:iterate>
        </logic:notEmpty>
--%>
        </a>
        <!-- 登録人数 -->
        <a href="javascript:void(0);" onclick="return selPerCount('<bean:write name="bmkMdl" property="bmuSid" />');" class="regist-count">
        <bean:define id="bmkPerCountStr" name="bmkMdl" property="bmkPerCount" type="java.lang.Integer" />
        <gsmsg:write key="bmk.22" arg0="<%= String.valueOf(bmkPerCountStr.intValue()) %>" />
        </a>
        <br>
        <!-- ＵＲＬ -->
        <small>
        <logic:notEmpty name="bmkMdl" property="bmuUrlDspList">
        <logic:iterate id="urlDsp" name="bmkMdl" property="bmuUrlDspList">
        <bean:write name="urlDsp" /><br>
        </logic:iterate>
        </logic:notEmpty>
        </small>
        </td>
        <!-- 追加ボタン -->
        <logic:equal name="bmkMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
        <td align="center" valign="top" width="0%" style="border-left:0px;border-right:0px;">
        <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:26px;padding-right:0px;padding-left:0px;padding-top:12px;font-weight:bold;" class="bmk_add_btn" value="<gsmsg:write key="cmn.add" />" onClick="return buttonPushAdd(<bean:write name="bmkMdl" property="bmuSid" />);">
        </td>
        </logic:equal>
        <!-- 変更ボタン -->
        <logic:equal name="bmk010Form" property="bmk010editPow" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.POW_YES) %>'>
        <td align="center" valign="top" width="0%" style="border-left:0px;border-right:0px;">
        <input type="button" name="add" style="border:0px;width:26px;height:26px;" class="bmk_edit_btn" value="" onClick="return buttonPushEdit(<bean:write name="bmkMdl" property="bmkSid" />);">
        </td>
        </logic:equal>
        <!-- 削除チェックボックス -->
        <logic:equal name="bmk010Form" property="bmk010editPow" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.POW_YES) %>'>
        <td align="center" valign="top" width="0%">
        <html:multibox name="bmk010Form" property="bmk010delInfSid">
        <bean:write name="bmkMdl" property="bmkSid" />
        </html:multibox>
        </td>
        </logic:equal>

        </tr>
        </table>
      </th>
      </tr>

      <tr>
      <td>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <!-- コメント -->
        <td width="85%">
        <!-- 改行のとき -->
        <logic:equal name="bmkMdl" property="bmkCmtBrKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.bmk010.dao.Bmk010Dao.BMK_CMT_BR_YES) %>'>
        <p id="comment<bean:write name='idx' />" class="bookmark_comment">
        </logic:equal>
        <!-- 改行なしのとき -->
        <logic:equal name="bmkMdl" property="bmkCmtBrKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.bmk010.dao.Bmk010Dao.BMK_CMT_BR_NO) %>'>
        <p id="comment<bean:write name='idx' />">
        </logic:equal>
        <!-- コメント≠ブランクのとき -->
        <logic:notEmpty name="bmkMdl" property="bmkCmt">
        <p class="bookmark_tag"><bean:write name="bmkMdl" property="bmkCmt" filter="false" /></p>
        </logic:notEmpty>
        <!-- コメント＝ブランクのとき -->
        <logic:empty name="bmkMdl" property="bmkCmt">
        &nbsp;
        </logic:empty>
        </p>
        </td>
        <!-- 表示／隠す -->
        <td align="right" width="15%" style="vertical-align:bottom;">
        <logic:equal name="bmkMdl" property="bmkCmtBrKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.bmk010.dao.Bmk010Dao.BMK_CMT_BR_YES) %>'>
        <span style="font-size:70%;" >
        <a href="javaScript:void(0);" onclick="hide_comment(<bean:write name='idx' />);" id="disp_comment<bean:write name='idx' />">
         <gsmsg:write key="bmk.bmk010.03" />
        </a>
        </span>
        </logic:equal>
        </td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td>
      <!-- ラベル -->
      <p class="bookmark_tag"><gsmsg:write key="cmn.label" /><gsmsg:write key="wml.215" />
      <logic:notEmpty name="bmkMdl" property="bmkLabelList">
      <logic:iterate id="labelMdl" name="bmkMdl" property="bmkLabelList">
      <a href="javascript:void(0);" onclick="return selectLabel('<bean:write name="labelMdl" property="blbSid" />');">
      <bean:write name="labelMdl" property="blbName" />
      </a>
      </logic:iterate>
      </logic:notEmpty>

      <!-- 登録日 -->
      <br>
      <span class="date"><gsmsg:write key="bmk.15" /><gsmsg:write key="wml.215" /><bean:write name="bmkMdl" property="bmkAdateDsp" /></span>

      <!-- 公開区分 -->
      <logic:equal name="bmkMdl" property="bmkPublic" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_NO) %>'>
      <logic:equal name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
      <span class="secret"><gsmsg:write key="cmn.private" /></span>
      </logic:equal>
      <logic:equal name="bmk010Form" property="bmk010mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>'>
      <span class="secret"><gsmsg:write key="bmk.25" /></span>
      </logic:equal>
      </logic:equal>
      </p>
      </td>
      </tr>

      </table>
      </logic:iterate>
      </logic:notEmpty>

      <!-- ページング -->
      <logic:notEmpty name="bmk010Form" property="bmk010pageCmbList">
      <table width="100%">
      <tr>
      <td width="100%" align="right" valign="top">
      <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('prevPage');">
      <html:select name="bmk010Form" property="bmk010pageBottom" styleClass="text_i" onchange="changePage('bmk010pageBottom');">
      <html:optionsCollection name="bmk010Form" property="bmk010pageCmbList" value="value" label="label" />
      </html:select>
      <img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('nextPage');">
      </td>
      </tr>
      </table>
      </logic:notEmpty>

    </td>

    </tr>
    </table>

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