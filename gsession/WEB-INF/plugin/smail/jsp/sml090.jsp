<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- 共通定数 --%>
<%
  String unopend = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED);
  String opend = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED);
  String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU);
  String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE);
%>

<%-- コマンドパラメータ --%>
<%
  String backToMsgList    = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_BACK;
  String changeGroup      = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_CHANGE_GROUP;
  String selectAtesaki    = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_TO_SELECT;
  String changePageCombo  = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_CHANGE_PCOMBO;
  String fomerPage        = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_FORMER_PAGE;
  String nextPage         = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_NEXT_PAGE;
  String search           = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_SEARCH;
  String clickTitle       = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_CLICK_TITLE;
  String clickTitleSoukou = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_CLICK_TITLE_SOUKOU;
  String changeSyubetsu   = jp.groupsession.v2.sml.sml090.Sml090Action.CMD_CHANGE_SYUBETSU;
%>

<%-- メール種別 --%>
<%
  String jusin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN);
  String sosin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN);
  String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO);
  String gomi = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO);

%>

<%-- キーワード検索区分 --%>
<%
  String keyWordAnd    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.KEY_WORD_KBN_AND);
  String keyWordOr     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.KEY_WORD_KBN_OR);
%>

<%-- 検索対象 --%>
<%
  String targetTitle   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SEARCH_TARGET_TITLE);
  String targetHonbun  = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SEARCH_TARGET_HONBUN);
%>

<%-- ソートオーダー --%>
<%
  String orderAsc  = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC);
  String orderDesc = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC);
%>

<%-- ソートキー --%>
<%
  String sortMark        =  String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_MARK);
  String sortTitle       =  String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_TITLE);
  String sortSoushinsya  =  String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_NAME);
  String sortDate        =  String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_DATE);
  String sortAtesaki     =  String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_ATESAKI);
%>

<%-- マーク --%>
<%
  String markAll       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ALL);

  String markNone      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE);
  String markTel       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL);
  String markImp       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP);
  String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);
  String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);
  String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);
  String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);
  String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);
  String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);
  String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU);
%>

<%-- マーク画像定義 --%>
<%
  java.util.HashMap imgMap = new java.util.HashMap();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String phone = gsMsg.getMessage(request, "cmn.phone");
        String important = gsMsg.getMessage(request, "sml.61");
        String smile = gsMsg.getMessage(request, "sml.11");
        String worry = gsMsg.getMessage(request, "sml.86");
        String angry = gsMsg.getMessage(request, "sml.83");
        String sad = gsMsg.getMessage(request, "sml.87");
        String beer = gsMsg.getMessage(request, "sml.15");
        String hart = gsMsg.getMessage(request, "sml.13");
        String tired = gsMsg.getMessage(request, "sml.88");

  imgMap.put(markTel, "<img src=\"../smail/images/call.gif\" alt=\"" + phone + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markImp, "<img src=\"../smail/images/zyuu.gif\" alt=\"" + important + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markSmaily, "<img src=\"../smail/images/icon_face01.gif\" alt=\"" + smile + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markWorry, "<img src=\"../smail/images/icon_face02.gif\" alt=\"" + worry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markAngry, "<img src=\"../smail/images/icon_face03.gif\" alt=\"" + angry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markSadly, "<img src=\"../smail/images/icon_face04.gif\" alt=\"" + sad + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markBeer, "<img src=\"../smail/images/icon_beer.gif\" alt=\"" + beer + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markHart, "<img src=\"../smail/images/icon_hart.gif\" alt=\"" + hart + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markZasetsu, "<img src=\"../smail/images/icon_zasetsu.gif\" alt=\"" + tired + "\" border=\"0\" class=\"img_bottom\">");

  imgMap.put("none", "&nbsp;");
%>

<%
  java.util.HashMap imgTextMap = new java.util.HashMap();
  imgTextMap.put(markTel, phone);
  imgTextMap.put(markImp, important);
  imgTextMap.put(markSmaily, smile);
  imgTextMap.put(markWorry, worry);
  imgTextMap.put(markAngry, angry);
  imgTextMap.put(markSadly, sad);
  imgTextMap.put(markBeer, beer);
  imgTextMap.put(markHart, hart);
  imgTextMap.put(markZasetsu, tired);

  imgTextMap.put("none", "&nbsp;");
%>


<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/webSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml090.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="sml.sml090.06" /></title>
<script language="JavaScript">
<!--
function replaceSaveProcMode() {
    $('#sml010ProcMode')[0].value = $('#sml090ProcModeSave')[0].value
}

function replaceAtesakiParm() {
  var sml090users = document.getElementsByName('sml090userSid');
  var cmn120users = document.getElementsByName('cmn120userSid');
  sml090users = cmn120users;
  clearUserList();

}
-->
</script>
</head>

<body class="body_03" onload="dispStyle();">
<html:form action="/smail/sml090">
<input type="hidden" id="CMD" name="CMD" value="<%= search %>">
<input type="hidden" name="sml090BackParm" value="1" >

<html:hidden property="sml010ProcMode" styleId="sml010ProcMode" />
<html:hidden property="sml010SelectedSid" styleId="sml010SelectedSid" />
<html:hidden property="sml010SelectedMailKbn" styleId="sml010SelectedMailKbn" />

<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />

<%-- 詰め替え用保持値 --%>
<html:hidden property="sml090ProcModeSave" styleId="sml090ProcModeSave" />

<logic:notEmpty name="sml090Form" property="sml010SelectedDelSid" scope="request">
  <logic:iterate id="select" name="sml090Form" property="sml010SelectedDelSid" scope="request">
    <input type="hidden" name="sml010SelectedDelSid" value="<bean:write name="select"/>">
  </logic:iterate>
</logic:notEmpty>


<%-- 削除チェックの保持パラメータ --%>
<logic:notEmpty name="sml090Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml090Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>


<logic:notEmpty name="sml090Form" property="sml090SelectedDelSid" scope="request">
  <logic:iterate id="delSid" name="sml090Form" property="sml090SelectedDelSid" scope="request">
    <input type="hidden" name="sml090DelSid" value="<bean:write name="delSid"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="searchFlg" />
<%-- 検索SVパラメータ start -------------------------------------------------------------------------- --%>
<logic:notEmpty name="sml090Form" property="cmn120SvuserSid" scope="request">
  <logic:iterate id="svuser" name="sml090Form" property="cmn120SvuserSid" scope="request">
    <input type="hidden" name="cmn120SvuserSid" value="<bean:write name="svuser"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSltGroup" />
<html:hidden property="sml090SvSltUser" />

<logic:notEmpty name="sml090Form" property="sml090SvAtesaki" scope="request">
  <logic:iterate id="svAtesaki" name="sml090Form" property="sml090SvAtesaki" scope="request">
    <input type="hidden" name="sml090SvAtesaki" value="<bean:write name="svAtesaki"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvMailSyubetsu" />
<html:hidden property="sml090SvMailMark" />
<html:hidden property="sml090SvKeyWord" />
<html:hidden property="sml090SvKeyWordkbn" />

<logic:notEmpty name="sml090Form" property="sml090SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sml090Form" property="sml090SvSearchTarget" scope="request">
    <input type="hidden" name="sml090SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSearchOrderKey1" />
<html:hidden property="sml090SvSearchSortKey1" />
<html:hidden property="sml090SvSearchOrderKey2" />
<html:hidden property="sml090SvSearchSortKey2" />
<%-- 検索SVパラメータ end ---------------------------------------------------------------------------- --%>





<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../smail/images/header_smail03_7_01.gif" border="0" alt="<gsmsg:write key="cmn.shortmail" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shortmail" /></span></td>
        <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="sml.sml090.07" /> ]</td>

        <td width="100%" class="header_white_bg">&nbsp;</td>

        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="replaceSaveProcMode(); buttonPush('<%= backToMsgList %>');">
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">


<!-- ページコンテンツ start -->
<logic:messagesPresent message="false">
    <table width="100%">
    <tr><td colspan="4" align="left"><html:errors/></td></tr>
    </table>
</logic:messagesPresent>

    <table width="100%" class="tl0 search_tbl_base">
    <tr>
    <td width="100%" height="30px" colspan="4" class="table_bg_7D91BD_search">
      <img src="../circular/images/spacer.gif" width="1" height="20" align="left">
      <img src="../common/images/search_icon.gif" class="img_bottom" alt="<gsmsg:write key="cmn.advanced.search" />"><span class="text_tlw3"><gsmsg:write key="cmn.advanced.search" /></span>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="sml.sml090.10" /></th>
    <td width="40%" class="td_sub_detail">
      <html:radio name="sml090Form" property="sml090MailSyubetsu" styleId="radio_jushin" value="<%= jusin %>" onclick="buttonPush('changeSyubetsu');" /><label for="radio_jushin"><gsmsg:write key="cmn.receive" /></label>
      <html:radio name="sml090Form" property="sml090MailSyubetsu" styleId="radio_soushin" value="<%= sosin %>" onclick="buttonPush('changeSyubetsu');" /><label for="radio_soushin"><gsmsg:write key="cmn.sent" /></label>
      <html:radio name="sml090Form" property="sml090MailSyubetsu" styleId="radio_soukou" value="<%= soko %>" onclick="buttonPush('changeSyubetsu');" /><label for="radio_soukou"><gsmsg:write key="cmn.draft" /></label>
      <html:radio name="sml090Form" property="sml090MailSyubetsu" styleId="radio_gomi" value="<%= gomi %>" onclick="buttonPush('changeSyubetsu');" /><label for="radio_gomi"><gsmsg:write key="cmn.trash" /></label>
    </td>

    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.sender" /></th>
    <td width="40%" class="td_sub_detail">
      <table>
        <tr>
        <td class="text_bb2" nowrap><gsmsg:write key="cmn.group" /><gsmsg:write key="wml.215" /></td>
        <td>
          <html:select property="sml090SltGroup" styleId="sml090SltGroup" styleClass="select01" onchange="buttonPush('changeGroup');">
            <html:optionsCollection name="sml090Form" property="sml090GroupLabel" value="value" label="label" />
          </html:select>
        </td>
        </tr>
        <tr>
        <td class="text_bb2" nowrap><gsmsg:write key="cmn.user" /><gsmsg:write key="wml.215" /></td>
        <td>
          <html:select property="sml090SltUser" styleId="sml090SltUser" styleClass="select01">
            <html:optionsCollection name="sml090Form" property="sml090UserLabel" value="value" label="label" />
          </html:select>
        </td>
        </tr>
      </table>
    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.mark" /></th>
    <td width="40%" class="td_sub_detail">
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markAll %>" styleId="radio_all" /><label for="radio_all"><gsmsg:write key="cmn.without.specifying" /></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markNone %>" styleId="radio_none" /><label for="radio_none"><gsmsg:write key="cmn.no3" /></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markTel %>" styleId="radio_tell" /><label for="radio_tell"><%= (String) imgMap.get(markTel) %><%= (String) imgTextMap.get(markTel) %></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markImp %>" styleId="radio_important" /><label for="radio_important"><%= (String) imgMap.get(markImp) %><%= (String) imgTextMap.get(markImp) %></label><br>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markSmaily %>" styleId="radio_Smaily" /><label for="radio_Smaily"><%= (String) imgMap.get(markSmaily) %><%= (String) imgTextMap.get(markSmaily) %></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markWorry %>" styleId="radio_Worry" /><label for="radio_Worry"><%= (String) imgMap.get(markWorry) %><%= (String) imgTextMap.get(markWorry) %></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markAngry %>" styleId="radio_Angry" /><label for="radio_Angry"><%= (String) imgMap.get(markAngry) %><%= (String) imgTextMap.get(markAngry) %></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markSadly %>" styleId="radio_Sadly" /><label for="radio_Sadly"><%= (String) imgMap.get(markSadly) %><%= (String) imgTextMap.get(markSadly) %></label><br>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markBeer %>" styleId="radio_Beer" /><label for="radio_Beer"><%= (String) imgMap.get(markBeer) %><%= (String) imgTextMap.get(markBeer) %></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markHart %>" styleId="radio_Hart" /><label for="radio_Hart"><%= (String) imgMap.get(markHart) %><%= (String) imgTextMap.get(markHart) %></label>
      <html:radio name="sml090Form" property="sml090MailMark" value="<%= markZasetsu %>" styleId="radio_markZasetsu" /><label for="radio_markZasetsu"><%= (String) imgMap.get(markZasetsu) %><%= (String) imgTextMap.get(markZasetsu) %></label><br>
    </td>

    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.from" /></th>
    <td width="40%" class="td_sub_detail" valign="top">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.select" />" onClick="buttonPush('<%= selectAtesaki %>');" id="btnAtesakiSelect">&nbsp;&nbsp;
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.clear" />" onClick="clearUserList(); clearSaveUserList();" id="btnAtesakiClear">
      <span id="save-atesaki-part">
        <logic:notEmpty name="sml090Form" property="cmn120userSid" scope="request">
          <logic:iterate id="user" name="sml090Form" property="cmn120userSid" scope="request">
            <input type="hidden" name="sml090userSid" value="<bean:write name="user"/>">
          </logic:iterate>
        </logic:notEmpty>
      </span>

      <div id="atesaki-part">
        <%-- 表示用メンバ名＆hiddenタグ生成 clearUserList()にて空白に書き換えられる --%>
        <logic:notEmpty name="sml090Form" property="cmn120userSid" scope="request">
          <logic:iterate id="user" name="sml090Form" property="cmn120userSid" scope="request">
            <input type="hidden" name="cmn120userSid" value="<bean:write name="user"/>">
          </logic:iterate>
        </logic:notEmpty>


        <logic:notEmpty name="sml090Form" property="sml090AtesakiModel" scope="request">
          <bean:define id="detail" name="sml090Form" property="sml090AtesakiModel" type="jp.groupsession.v2.sml.model.SmailModel" />
          <logic:iterate id="atesaki" name="detail" property="atesakiList" indexId="idx" scope="page">
              <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /><logic:notEqual name="detail" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
              <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del><logic:notEqual name="detail" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"><br></logic:notEqual></logic:equal>
          &nbsp;
          </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="sml090Form" property="sml090AtesakiModel" scope="request">&nbsp;</logic:empty>
      </div>

    </td>
    </tr>

    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.keyword" /></th>
    <td class="td_sub_detail">
      <html:text name="sml090Form" property="sml090KeyWord" maxlength="100" size="50" />
      <div class="text_base2">
        <html:radio name="sml090Form" property="sml090KeyWordkbn" value="<%= keyWordAnd %>" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
        <html:radio name="sml090Form" property="sml090KeyWordkbn" value="<%= keyWordOr %>" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
      </div>
    </td>


    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.search2" /></th>
    <td width="40%" class="td_sub_detail">
      <html:multibox styleId="search_scope_01" name="sml090Form" property="sml090SearchTarget" value="<%= targetTitle %>" /><label for="search_scope_01"><gsmsg:write key="cmn.subject" /></label>
      <html:multibox styleId="search_scope_02" name="sml090Form" property="sml090SearchTarget" value="<%= targetHonbun %>" /><label for="search_scope_02"><gsmsg:write key="wml.210" /></label>
    </td>

    </tr>


    <tr>
    <th width="10%" class="td_gray text_bb2" nowrap><gsmsg:write key="cmn.sort.order" /></th>
    <td class="td_sub_detail" colspan="3">
    <span class="text_bb2"><gsmsg:write key="cmn.first.key" /></span>
      <html:select property="sml090SearchSortKey1">
        <html:optionsCollection name="sml090Form" property="sml090SortKeyLabelList" value="value" label="label" />
      </html:select>

      <html:radio name="sml090Form" property="sml090SearchOrderKey1" value="<%= orderAsc %>" styleId="sort1_up" /><label for="sort1_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="sml090Form" property="sml090SearchOrderKey1" value="<%= orderDesc %>" styleId="sort1_dw" /><label for="sort1_dw"><gsmsg:write key="cmn.order.desc" /></label>
      &nbsp;&nbsp;&nbsp;&nbsp;

    <span class="text_bb2"><gsmsg:write key="cmn.second.key" /></span>
      <html:select property="sml090SearchSortKey2">
        <html:optionsCollection name="sml090Form" property="sml090SortKeyLabelList" value="value" label="label" />
      </html:select>
      <html:radio name="sml090Form" property="sml090SearchOrderKey2" value="<%= orderAsc %>" styleId="sort2_up" /><label for="sort2_up"><gsmsg:write key="cmn.order.asc" /></label>
      <html:radio name="sml090Form" property="sml090SearchOrderKey2" value="<%= orderDesc %>" styleId="sort2_dw" /><label for="sort2_dw"><gsmsg:write key="cmn.order.desc" /></label>

    </td>
    </tr>

    </table>

    <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>

    <table width="100%">
    <tr>
    <td width="99%" align="center"><input type="button" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onclick="buttonPush('<%= search %>');"></td>
    <td width="1%" align="right"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('msg_delete');"></td>
    </tr>
    </table>

<logic:notEmpty name="sml090Form" property="sml090SearchResultList" >

    <%-- ページコンボ上段 --%>
    <bean:size id="count1" name="sml090Form" property="smlPageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= fomerPage %>');">
        <html:select property="sml090page1" styleId="sml090page1" onchange="changePage(0, 'changePageCombo');" styleClass="text_i">
          <html:optionsCollection name="sml090Form" property="smlPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= nextPage %>');">
      </td>
      </tr>
    </table>
    </logic:greaterThan>
    <logic:lessThan name="count1" value="2">
    <br class="clear">
    </logic:lessThan>

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

      <bean:define id="mailType" name="sml090Form" property="sml090MailSyubetsu" type="String"/>
      <% String strMailType = mailType; %>

      <bean:define id="procMode" name="sml090Form" property="sml090SvMailSyubetsu" type="String"/>
      <% String sMode = procMode; %>
      <% String mode0 = jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN; %>
      <% String mode1 = jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN; %>
      <% String mode2 = jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO; %>
      <% String mode3 = jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO; %>

<%-- タイトル --%>
      <tr class="table_bg_7D91BD_search">
      <th width="0%"><input type="checkbox" name="allCheck" onClick="changeChk();"></th>
<% if (mode0.equals(sMode)) { %>
        <%-- 受信 --%>
        <th width="10%"><a href="#" onClick="clickSortTitle(<%= sortMark %>);"><span class="text_tlw"><gsmsg:write key="cmn.mark" /></span></a></th>
        <th width="45%"><a href="#" onClick="clickSortTitle(<%= sortTitle %>);"><span class="text_tlw"><gsmsg:write key="cmn.subject" /></span></a></th>
        <th width="25%"><a href="#" onClick="clickSortTitle(<%= sortSoushinsya %>);"><span class="text_tlw"><gsmsg:write key="cmn.sender" /></span></a></th>
        <!--th width="20%"><span class="text_bb2"><gsmsg:write key="cmn.from" /></span></th-->
        <th width="20%"><a href="#" onClick="clickSortTitle(<%= sortDate %>);"><span class="text_tlw"><gsmsg:write key="cmn.date" /></span></a></th>
<% } else if (mode1.equals(sMode)) { %>
        <%-- 送信 --%>
        <th width="10%"><a href="#" onClick="clickSortTitle(<%= sortMark %>);"><span class="text_tlw"><gsmsg:write key="cmn.mark" /></span></a></th>
        <th width="45%"><a href="#" onClick="clickSortTitle(<%= sortTitle %>);"><span class="text_tlw"><gsmsg:write key="cmn.subject" /></span></a></th>
        <!--th width="20%"><span class="text_bb2"><gsmsg:write key="cmn.sender" /></span></th-->
        <th width="25%"><a href="#" onClick="clickSortTitle(<%= sortAtesaki %>);"><span class="text_tlw"><gsmsg:write key="cmn.from" /></span></a></th>
        <th width="20%"><a href="#" onClick="clickSortTitle(<%= sortDate %>);"><span class="text_tlw"><gsmsg:write key="cmn.date" /></span></a></th>
<% } else if (mode2.equals(sMode)) { %>
        <%-- 草稿 --%>
        <th width="10%"><a href="#" onClick="clickSortTitle(<%= sortMark %>);"><span class="text_tlw"><gsmsg:write key="cmn.mark" /></span></a></th>
        <th width="45%"><a href="#" onClick="clickSortTitle(<%= sortTitle %>);"><span class="text_tlw"><gsmsg:write key="cmn.subject" /></span></a></th>
        <!--th width="20%"><span class="text_bb2"><gsmsg:write key="cmn.sender" /></span></th-->
        <th width="25%"><span class="text_tlw"><!-- a href="#" onClick="clickSortTitle(<%= sortAtesaki %>);" --><gsmsg:write key="cmn.from" /><!-- /a --></span></th>
        <th width="20%"><a href="#" onClick="clickSortTitle(<%= sortDate %>);"><span class="text_tlw"><gsmsg:write key="cmn.date" /></span></a></th>
<% } else if (mode3.equals(sMode)) { %>
        <%-- ゴミ箱 --%>
        <th width="5%"><a href="#" onClick="clickSortTitle(<%= sortMark %>);"><span class="text_tlw"><gsmsg:write key="cmn.mark" /></span></a></th>
        <th width="40%"><a href="#" onClick="clickSortTitle(<%= sortTitle %>);"><span class="text_tlw"><gsmsg:write key="cmn.subject" /></span></a></th>
        <th width="20%"><span class="text_tlw"><gsmsg:write key="cmn.sender" /></span></th>
        <th width="20%"><span class="text_tlw"><gsmsg:write key="cmn.from" /></span></th>
        <th width="15%"><a href="#" onClick="clickSortTitle(<%= sortDate %>);"><span class="text_tlw"><gsmsg:write key="cmn.date" /></span></a></th>
<% } %>
      </tr>

  <logic:iterate id="resultBean" name="sml090Form" property="sml090SearchResultList" indexId="idx" scope="request">

    <%-- 背景カラー設定 --%>
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_line_color1", "td_line_color2"}; %>
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>
    <bean:define id="imgMark"><bean:write name="resultBean" property="smsMark" /></bean:define>

        <%-- 未読・既読管理 --%>
        <logic:equal name="sml090Form" property="sml090SvMailSyubetsu" value="<%= jusin %>">
          <logic:equal name="resultBean" property="smjOpkbn" value="<%= unopend %>">
            <bean:define id="titleColor" value="text_link" />
          </logic:equal>
          <logic:equal name="resultBean" property="smjOpkbn" value="<%= opend %>">
            <bean:define id="titleColor" value="text_p" />
          </logic:equal>
        </logic:equal>
        <logic:notEqual name="sml090Form" property="sml090SvMailSyubetsu" value="<%= jusin %>">
          <bean:define id="titleColor" value="text_p" />
        </logic:notEqual>

        <logic:equal name="sml090Form" property="sml090SvMailSyubetsu" value="<%= jusin %>">
          <logic:equal name="resultBean" property="smjOpkbn" value="<%= unopend %>">
            <bean:define id="nameColor" value="sc_ttl_sat" />
          </logic:equal>
          <logic:equal name="resultBean" property="smjOpkbn" value="<%= opend %>">
            <bean:define id="nameColor" value="text_p" />
          </logic:equal>
        </logic:equal>
        <logic:notEqual name="sml090Form" property="sml090SvMailSyubetsu" value="<%= jusin %>">
          <bean:define id="nameColor" value="text_p" />
        </logic:notEqual>

      <tr class="<%= tdColor %>">
        <td align="center" valign="middle" width="0%">
<html:multibox name="sml090Form" property="sml090DelSid"><bean:write name="resultBean" property="mailKey" /></html:multibox>
        </td>

        <td align="center"><%-- マーク --%><% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %></td>

        <td align="left">
               <logic:notEqual name="resultBean" property="binCnt" value="0"><img alt="<gsmsg:write key="cmn.attach.file" />" src="../smail/images/temp_file.gif" class="img_bottom"></logic:notEqual>
              <logic:equal name="resultBean" property="binCnt" value="0"><img alt="" src="../smail/images/space_box.gif" class="img_bottom"></logic:equal>


          <logic:equal name="sml090Form" property="sml090SvMailSyubetsu" value="<%= gomi %>">
            <span class="<bean:write name="titleColor" />">
              <a href="#" onClick="return moveDetail('<bean:write name="resultBean" property="smlSid" />', '<bean:write name="resultBean" property="mailKbn" />', '<%= clickTitle %>')"><span class="<bean:write name="titleColor" />">
              <logic:equal name="resultBean" property="mailKbn" value="<%= jusin %>">[ <gsmsg:write key="cmn.receive2" /> ]</logic:equal>
              <logic:equal name="resultBean" property="mailKbn" value="<%= sosin %>">[ <gsmsg:write key="cmn.sent2" /> ]</logic:equal>
              <logic:equal name="resultBean" property="mailKbn" value="<%= soko %>">[ <gsmsg:write key="cmn.draft3" /> ]</logic:equal>
              <bean:write name="resultBean" property="smsTitle" /></span>
              </a>
            </span>
          </logic:equal>

          <logic:equal name="sml090Form" property="sml090SvMailSyubetsu" value="<%= soko %>">
            <a href="#" onClick="replaceAtesakiParm(); return moveMessage('<bean:write name="resultBean" property="smlSid" />', '<%= clickTitleSoukou %>')"><span class="<bean:write name="titleColor" />"><bean:write name="resultBean" property="smsTitle" /></span></a>
          </logic:equal>

          <logic:notEqual name="sml090Form" property="sml090SvMailSyubetsu" value="<%= soko %>"><logic:notEqual name="sml090Form" property="sml090SvMailSyubetsu" value="<%= gomi %>">
            <a href="#" onClick="replaceAtesakiParm(); moveDetail('<bean:write name="resultBean" property="smlSid" />', '-1', '<%= clickTitle %>')"><span class="<bean:write name="titleColor" />"><bean:write name="resultBean" property="smsTitle" /></span></a>
          </logic:notEqual></logic:notEqual>
        </td>
<% if (mode0.equals(sMode) || mode3.equals(sMode)) { %>
        <td align="left">
          <span class="<bean:write name="nameColor" />">
            <logic:empty name="resultBean" property="usiSei" ><logic:empty name="resultBean" property="usiMei" >-</logic:empty></logic:empty>
            <logic:equal name="resultBean" property="usrJkbn" value="<%= toroku %>"><bean:write name="resultBean" property="usiSei" />&nbsp;&nbsp;<bean:write name="resultBean" property="usiMei" /></logic:equal>
            <logic:equal name="resultBean" property="usrJkbn" value="<%= delete %>"><del><bean:write name="resultBean" property="usiSei" />&nbsp;&nbsp;<bean:write name="resultBean" property="usiMei" /></del></logic:equal>
          </span>
        </td>
<% } %>

<% if (mode1.equals(sMode) || mode2.equals(sMode) || mode3.equals(sMode)) { %>
        <td align="left">
          <span class="<bean:write name="nameColor" />">
            <logic:empty name="resultBean" property="atesakiList" >-</logic:empty>
            <logic:notEmpty name="resultBean" property="atesakiList" >
              <logic:iterate id="atesakiModel" name="resultBean" property="atesakiList" indexId="idx_at2">
                <logic:equal name="atesakiModel" property="usrJkbn" value="<%= toroku %>"><logic:notEqual name="resultBean" property="listSize" value="<%= String.valueOf(idx_at2.intValue()) %>">;&nbsp;</logic:notEqual><bean:write name="atesakiModel" property="usiSei" />　<bean:write name="atesakiModel" property="usiMei" /></logic:equal>
                <logic:equal name="atesakiModel" property="usrJkbn" value="<%= delete %>"><logic:notEqual name="resultBean" property="listSize" value="<%= String.valueOf(idx_at2.intValue()) %>">;&nbsp;</logic:notEqual><del><bean:write name="atesakiModel" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiModel" property="usiMei" /></del></logic:equal>
              </logic:iterate>
            </logic:notEmpty>
          </span>
        </td>
<% } %>
        <td nowrap align="center"><span class="<bean:write name="nameColor" />"><bean:write name="resultBean" property="strSdate" /></span></td>
      </tr>

  </logic:iterate>
    </table>

    <logic:notEqual name="sml090Form" property="sml090searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
    <logic:notEmpty name="sml090Form" property="sml090SvKeyWord">
    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td align="left">
      <bean:define id="sml090SrhWord" name="sml090Form" property="sml090WebSearchWord" type="java.lang.String" />
      <a href="javascript:void(0);" class="" onmouseover="overSearch();" onmouseout="outSearch();" onClick="webSearch('<bean:write name="sml090Form" property="sml090WebSearchWord" />');">
      <span id="webSearchArea" class="text_normal">
      <gsmsg:write key="cmn.websearch" arg0="<%= sml090SrhWord %>" />
      </span>
      </a>
      </td>
    </tr>
    </table>
    </logic:notEmpty>
    </logic:notEqual>

    <%-- ページコンボ下段 --%>
    <bean:size id="count1" name="sml090Form" property="smlPageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= fomerPage %>');">
        <html:select property="sml090page2" styleId="sml090page2" onchange="changePage(1, 'changePageCombo');" styleClass="text_i">
          <html:optionsCollection name="sml090Form" property="smlPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('<%= nextPage %>');">
      </td>
      </tr>
    </table>
    </logic:greaterThan>


</logic:notEmpty>



  </td>
  </tr>
  </table>

</td>
</tr>
</table>
<!-- ページコンテンツ end -->

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>