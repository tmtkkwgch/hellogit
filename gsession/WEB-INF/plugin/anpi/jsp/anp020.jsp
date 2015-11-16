<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthComment = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_REPLYCOMMENT); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="anp.anp020.01"/></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>

</head>
<body class="body_03" onload="showLengthId($('#inputstr1')[0],<%=maxLengthComment %>,'inputlength1');">

<html:form action="/anpi/anp020">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="anpiSid" />
<html:hidden property="userSid" />
<html:hidden property="rmode" />

<html:hidden property="anp010SelectGroupSid" />
<html:hidden property="anp010NowPage" />
<html:hidden property="anp010SortKeyIndex" />
<html:hidden property="anp010OrderKey" />
<html:hidden property="anp010KnrenFlg" />

<html:hidden property="anp020EmployeeNo" />
<html:hidden property="anp020Name" />
<html:hidden property="anp020Kana" />
<html:hidden property="anp020HaisinDate" />
<html:hidden property="anp020ReplyDate" />
<html:hidden property="anp020UrgentDspFlg" />
<html:hidden property="anp020UrgentMail" />
<html:hidden property="anp020UrgentTelNo" />
<html:hidden property="anp020PhotoFileSid" />
<html:hidden property="anp020PhotoDspFlg" />

<html:hidden property="anp010SearchKbn" />
<html:hidden property="anp010SearchSndKbn" />
<html:hidden property="anp010SearchAnsKbn" />
<html:hidden property="anp010SearchAnpKbn" />
<html:hidden property="anp010SearchPlcKbn" />
<html:hidden property="anp010SearchSyuKbn" />
<html:hidden property="anp010SvSearchSndKbn" />
<html:hidden property="anp010SvSearchAnsKbn" />
<html:hidden property="anp010SvSearchAnpKbn" />
<html:hidden property="anp010SvSearchPlcKbn" />
<html:hidden property="anp010SvSearchSyuKbn" />

<!-- header -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- content -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="0%"><img src="../anpi/images/header_anpi_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_white_bg_text"><gsmsg:write key="anp.plugin"/> [ <gsmsg:write key="anp.anp020.02"/> ]</td>
        <td width="100%" class="header_white_bg"></td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="anp.plugin"/>"></td>
        </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp020excute');">
            <logic:notEqual name="anp020Form" property="rmode" value="<%= jp.groupsession.v2.anp.GSConstAnpi.REMOTE_MODE %>">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp020back');">
            </logic:notEqual>
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:equal name="anp020Form" property="anp010KnrenFlg" value="1">
      <table cellspacing="0" border="0" width="100%" class="tl_u2" id="knren_top">
        <!-- 訓練モード バー -->
        <tr>
          <td valign="middle" class="table_bg_FFC1C1" align="center">
            <span class="text_r2"><gsmsg:write key="anp.knmode"/></span>
          </td>
        </tr>
      </table>
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:equal>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
     <tr>
     <!-- ユーザ画像 -->
     <td width="130px" class="img_top" >
     <span class="photoList">
        <logic:equal name="anp020Form" property="anp020PhotoDspFlg" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
          <div align="center" class="photo_hikokai"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
        </logic:equal>
        <logic:equal name="anp020Form" property="anp020PhotoDspFlg" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
        <logic:equal name="anp020Form" property="anp020PhotoFileSid" value="0">
          <img src="../user/images/photo.gif" name="pitctImage" width="130" alt="<gsmsg:write key="cmn.photo" />" border="1">
        </logic:equal>
        <logic:notEqual name="anp020Form" property="anp020PhotoFileSid" value="0">
          <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="anp020Form" property="anp020PhotoFileSid" />" name="pictImage" alt="<gsmsg:write key="cmn.photo" />" width="130" border="1">
        </logic:notEqual>
        </logic:equal>
     </span>
     </td>

     <td width="10px"></td>

    <td width="0%">
    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
        <tr>
            <!-- 社員/職員番号 -->
            <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.employee.staff.number"/></span></td>
            <td width="80%" align="left" class="td_type1"><bean:write name="anp020Form" property="anp020EmployeeNo" /></td>
        </tr>

        <!-- 氏名 -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="cmn.name"/></span></td>
        <td align="left" class="td_type1"><bean:write name="anp020Form" property="anp020Name" /></td>
        </tr>

        <!-- 氏名(カナ) -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="user.119"/></span></td>
        <td align="left" class="td_type1"><bean:write name="anp020Form" property="anp020Kana" /></td>
        </tr>

        <!-- 送信日時 -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="anp.date.send"/></span></td>
        <td align="left" class="td_type1"><bean:write name="anp020Form" property="anp020HaisinDate" /></td>
        </tr>

        <!-- 回答日時 -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="anp.date.ans"/></span></td>
        <td align="left" class="td_type1"><bean:write name="anp020Form" property="anp020ReplyDate" /></td>
        </tr>

        <!-- 安否状況 -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="anp.jokyo"/></span></td>
        <td align="left" class="td_type1">
             <html:radio styleId="jokyoFlg1" name="anp020Form" property="anp020JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>" />
                   <label for="jokyoFlg1"><gsmsg:write key="cmn.notset"/></label>&nbsp;
             <html:radio styleId="jokyoFlg2" name="anp020Form" property="anp020JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>" />
                   <label for="jokyoFlg2"><gsmsg:write key="anp.jokyo.good"/></label>&nbsp;
             <html:radio styleId="jokyoFlg3" name="anp020Form" property="anp020JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>" />
                   <label for="jokyoFlg3"><gsmsg:write key="anp.jokyo.keisyo"/></label>&nbsp;
             <html:radio styleId="jokyoFlg4" name="anp020Form" property="anp020JokyoFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>" />
                   <label for="jokyoFlg4"><gsmsg:write key="anp.jokyo.jusyo"/></label>&nbsp;
        </td>
        </tr>

        <!-- 現在地 -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="anp.place"/></span></td>
        <td align="left" class="td_type1">
             <html:radio styleId="placeFlg1" name="anp020Form" property="anp020PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_UNSET) %>" />
                   <label for="placeFlg1"><gsmsg:write key="cmn.notset"/></label>&nbsp;
             <html:radio styleId="placeFlg2" name="anp020Form" property="anp020PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_HOUSE) %>" />
                   <label for="placeFlg2"><gsmsg:write key="anp.place.house"/></label>&nbsp;
             <html:radio styleId="placeFlg3" name="anp020Form" property="anp020PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OFFICE) %>" />
                   <label for="placeFlg3"><gsmsg:write key="anp.place.office"/></label>&nbsp;
             <html:radio styleId="placeFlg4" name="anp020Form" property="anp020PlaceFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.PLACE_FLG_OUT) %>" />
                   <label for="placeFlg4"><gsmsg:write key="anp.place.out"/></label>&nbsp;
        </td>
        </tr>

        <!-- 出社状況 -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="anp.syusya.state"/></span></td>
        <td align="left" class="td_type1">
             <html:radio styleId="syusyaFlg1" name="anp020Form" property="anp020SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_UNSET) %>" />
                   <label for="syusyaFlg1"><gsmsg:write key="cmn.notset"/></label>&nbsp;
             <html:radio styleId="syusyaFlg2" name="anp020Form" property="anp020SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_NO) %>" />
                   <label for="syusyaFlg2"><gsmsg:write key="anp.syusya.no"/></label>&nbsp;
             <html:radio styleId="syusyaFlg3" name="anp020Form" property="anp020SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OK) %>" />
                   <label for="syusyaFlg3"><gsmsg:write key="anp.syusya.ok"/></label>&nbsp;
             <html:radio styleId="syusyaFlg4" name="anp020Form" property="anp020SyusyaFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SYUSYA_FLG_OKD) %>" />
                   <label for="syusyaFlg4"><gsmsg:write key="anp.syusya.okd"/></label>&nbsp;
        </td>
        </tr>

        <!-- コメント -->
        <tr>
        <td class="table_bg_A5B4E1" ><span class="text_bb1"><gsmsg:write key="anp.comment"/></span></td>
        <td align="left" class="td_type1">
           <% String onkey1 = "showLengthStr(value," + maxLengthComment + ",'inputlength1');"; %>
           <html:textarea styleId="inputstr1" name="anp020Form" property="anp020Comment" style="width:318px;" rows="3" styleClass="text_base" onkeyup="<%=onkey1 %>"  />
           <br><span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span>
           <span id="inputlength1" class="font_string_count">0</span>&nbsp;
           <span class="font_string_count_max">/&nbsp;<%= maxLengthComment %>&nbsp;<gsmsg:write key="cmn.character" /></span>
        </td>
        </tr>

        <!-- 緊急連絡先 -->
        <logic:equal name="anp020Form" property="anp020UrgentDspFlg" value="1">
        <tr>
        <td class="table_bg_A5B4E1" nowrap ><span class="text_bb1"><gsmsg:write key="anp.kinkyu"/></span></td>
        <td align="left" class="td_type1">
            <b><gsmsg:write key="cmn.mailaddress"/>：</b><bean:write name="anp020Form" property="anp020UrgentMail" /><br>
            <b><gsmsg:write key="cmn.tel"/>：</b><bean:write name="anp020Form" property="anp020UrgentTelNo" /><br><br>
            <span class="text_base"><span class="text_blue"><gsmsg:write key="anp.anp020.03"/></span></span>
        </td>
        </tr>
        </logic:equal>
    </table>
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('anp020excute');">
            <logic:notEqual name="anp020Form" property="rmode" value="<%= jp.groupsession.v2.anp.GSConstAnpi.REMOTE_MODE %>">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp020back');">
            </logic:notEqual>
        </td>
        </tr>
    </table>

    </td>
    </tr>
    </table>

</td>
</tr>
</table>
<!-- content END -->

</html:form>
<!-- footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>