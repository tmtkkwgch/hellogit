<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String maxLengthCmt = String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.MAX_LENGTH_CMT);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="bmk.bmk030.06" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../bookmark/css/bmkEntry.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/freeze.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmk030.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthCmt %>, 'inputlength');">

<div id="FreezePane">

<html:form action="/bookmark/bmk030">

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<html:hidden property="bmk020url" />
<html:hidden property="bmk030modeName" />
<html:hidden property="bmk030InitFlg" />

<html:hidden property="bmk070ReturnPage" />
<html:hidden property="bmk070Page" />
<html:hidden property="bmk070PageTop" />
<html:hidden property="bmk070PageBottom" />
<html:hidden property="bmk070OrderKey" />
<html:hidden property="bmk070SortKey" />

<html:hidden property="bmk080Page" />
<html:hidden property="bmk080PageTop" />
<html:hidden property="bmk080PageBottom" />
<input type="hidden" name="helpPrm" value="<bean:write name="bmk030Form" property="procMode" />">

<bean:define id="pMode" name="bmk030Form" property="procMode" type="java.lang.Integer" />
<% if (pMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_MODE_EDIT) { %>
<html:hidden property="bmk030mode" />
<html:hidden property="bmk030groupSid" />
<% } %>

<bean:define id="bMode" name="bmk030Form" property="bmk030mode" type="java.lang.Integer" />
<% if (bMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) { %>
<html:hidden property="bmk030public" />
<html:hidden property="bmk030main" />
<html:hidden property="bmk030groupSid" />
<% } else if (bMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) { %>
<html:hidden property="bmk030main" />
<% } else if (bMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) { %>
<html:hidden property="bmk030groupSid" />
<% } %>

<logic:notEmpty name="bmk030Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk030Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="bmk150PageNum" />
<html:hidden property="bmk070ToBmk150DspFlg" />

<span id="bmk040labelArea">
</span>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<!-- freeze状態のとき画面の偏りを防ぐ処理(FireFoxの場合) -->
<td valign="top">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>

<td>

  <table cellpadding="0" cellspacing="0" width="100%" align="center">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bmk.bmk030.05" /> ]</td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="OK" class="btn_ok1" onClick="return buttonPush('bmk030pushOk');">
    <logic:equal name="bmk030Form" property="procMode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_MODE_EDIT) %>'>
    <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1"  onClick="return buttonPush('bmk030pushDelete');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('bmk030pushBack');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <!-- メッセージ -->
  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/><br></td>
    </tr>
    </table>
  </logic:messagesPresent>

    <table class="tl0 table_td_border" width="100%"  border="0" cellspacing="0" cellpadding="5">

    <!-- 登録先 -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="bmk.16" /></th>

    <!-- 登録モードのとき -->
    <logic:equal name="bmk030Form" property="procMode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_MODE_TOUROKU) %>'>
    <td colspan="2">
    <!-- 個人ブックマークラジオ -->
    <html:radio name="bmk030Form" styleId="bmk030mode_00" property="bmk030mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>" onclick="buttonPush('init');" />
    <label for="bmk030mode_00"><gsmsg:write key="bmk.30" /></label>
    <br>
    <!-- グループブックマークラジオ -->
    <html:radio name="bmk030Form" styleId="bmk030mode_01" property="bmk030mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>" onclick="buttonPush('init');" />
    <label for="bmk030mode_01"><gsmsg:write key="bmk.51" /></label>&nbsp;
    <!-- グループコンボ -->
    <% boolean bmkFlg = true; %>
    <logic:equal name="bmk030Form" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>'>
    <% bmkFlg = false; %>
    </logic:equal>
    <logic:notEmpty name="bmk030Form" property="bmk030groupCmbList">
    <html:select name="bmk030Form" property="bmk030groupSid" disabled="<%= bmkFlg %>" onchange="buttonPush('init');">
    <html:optionsCollection name="bmk030Form" property="bmk030groupCmbList" value="value" label="label" />
    </html:select>
    </logic:notEmpty>
    <br>
    <!-- 共有ブックマークラジオ -->
    <html:radio name="bmk030Form" styleId="bmk030mode_02" property="bmk030mode" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>" onclick="buttonPush('init');" />
    <label for="bmk030mode_02"><gsmsg:write key="bmk.34" /></label>
    </td>
    </logic:equal>

    <!-- 編集モードのとき -->
    <logic:equal name="bmk030Form" property="procMode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_MODE_EDIT) %>'>
    <td colspan="2">
    <bean:write name="bmk030Form" property="bmk030modeName" />
    </td>
    </logic:equal>

    </tr>

    <!-- ＵＲＬ -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left">URL</th>
    <td colspan="2">
    <logic:notEmpty name="bmk030Form" property="bmk030UrlDsp">
    <logic:iterate id="urlDsp" name="bmk030Form" property="bmk030UrlDsp" indexId="idx2">
    <% if (idx2 > 0) { %> <br> <% } %>
    <bean:write name="urlDsp" />
    </logic:iterate>
    </logic:notEmpty>
    </td>
    </tr>

    <!-- タイトル -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.title" /><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></th>
    <td colspan="2">
    <html:text maxlength="150" property="bmk030title" style="width:575px;"/>
    </td>
    </tr>

    <!-- ラベル -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left" rowspan="2"><gsmsg:write key="cmn.label" /></th>
    <td width="0%" style="border-right:0px;border-bottom:0px;">
    <html:text maxlength="1000" property="bmk030label" style="width:575px;"/>
    </td>
    <!-- ラベル選択ボタン -->
    <td align="left" valign="top" width="100%" style="border-left:0px;border-bottom:0px;">
    <input type="button" value="<gsmsg:write key="cmn.select" />" class="btn_base1" onClick="openlabel(<bean:write name="bmk030Form" property="procMode" />);" style="width: 40px;" tabindex="47">
    </td>
    </tr>

    <!-- ラベルの説明 -->
    <tr>
    <td cellpadding="0" cellspacing="0" valign="top" colspan="2" style="border-top:0px;">
    <span class="small" style="line-height: 1.5;">
    <gsmsg:write key="bmk.bmk030.02" /><br>
    <span class="text_bb1"><gsmsg:write key="bmk.bmk030.04" /></span>
    </span>
    </td>
    </tr>

    <!-- コメント -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.comment" /></th>
    <td colspan="2">
    <textarea wrap="soft" name="bmk030cmt" style="width:575px;" rows="3" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthCmt %>, 'inputlength');" id="inputstr"><bean:write name="bmk030Form" property="bmk030cmt" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthCmt %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>


    <!-- 評価 -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="bmk.bmk030kn.01" /></th>
    <td colspan="2">
    <logic:notEmpty name="bmk030Form" property="bmk030scoreCmbList">
    <html:select name="bmk030Form" property="bmk030score">
    <html:optionsCollection name="bmk030Form" property="bmk030scoreCmbList" value="value" label="label" />
    </html:select>
    </logic:notEmpty>
    </td>
    </tr>

    <!-- 公開区分 -->
    <logic:notEqual name="bmk030Form" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>'>
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.public" /></th>
    <td colspan="2">
    <html:radio name="bmk030Form" styleId="bmk030public_01" property="bmk030public" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_YES) %>" />
    <label for="bmk030public_01"><gsmsg:write key="cmn.public" /></label>
    &nbsp;
    <html:radio name="bmk030Form" styleId="bmk030public_00" property="bmk030public" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_NO) %>" />
    <logic:equal name="bmk030Form" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
    <label for="bmk030public_00"><gsmsg:write key="cmn.private" /></label>
    </logic:equal>
    <logic:equal name="bmk030Form" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>'>
    <label for="bmk030public_00"><gsmsg:write key="bmk.25" /></label>
    </logic:equal>
    </td>
    </tr>
    </logic:notEqual>

    <!-- メイン表示区分 -->
    <logic:equal name="bmk030Form" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.main.view" /></th>
    <td colspan="2">
    <html:radio name="bmk030Form" styleId="bmk030main_01" property="bmk030main" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_YES) %>" />
    <label for="bmk030main_01"><gsmsg:write key="cmn.show" /></label>
    &nbsp;
    <html:radio name="bmk030Form" styleId="bmk030main_00" property="bmk030main" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_NO) %>" />
    <label for="bmk030main_00"><gsmsg:write key="cmn.hide" /></label>
    </td>
    </tr>
    </logic:equal>

    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" value="OK" class="btn_ok1" onClick="return buttonPush('bmk030pushOk');">
    <logic:equal name="bmk030Form" property="procMode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_MODE_EDIT) %>'>
    <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1"  onClick="return buttonPush('bmk030pushDelete');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('bmk030pushBack');">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>

<!-- freeze状態のとき画面の偏りを防ぐ処理(FireFoxの場合) -->
<td valign="top">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>

</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</div>

<div id="labelPanel">
<div class="hd"><gsmsg:write key="cmn.select.a.label" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="lab" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>

</body>
</html:html>