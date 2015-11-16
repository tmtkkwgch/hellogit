<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

<%-- マーク画像定義 --%>
<%
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

  java.util.HashMap imgMap = new java.util.HashMap();
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

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../smail/js/sml050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession]
<logic:equal name="sml230Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>"><gsmsg:write key="sml.sml050.01" /></logic:equal>
<logic:equal name="sml230Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>"><gsmsg:write key="sml.sml050.02" /></logic:equal>
</title>
</head>

<body class="body_03">
<html:form action="/smail/sml230">
<html:hidden property="smlAccountSid" />
<input type="hidden" name="CMD" value="">


<!-- HEADER -->

  <div align="right">
  <!--a href="../help/hlp001.do?pluginid=webmail&amp;pgid=wml110" target="_blank"><img src="../common/images/q.gif" alt="ヘルプ" border="0"></a></div -->
  <input type="button" class="q_bg" value="ヘルプ" onclick="return openHelp();">
  </div>

<script language="JavaScript">
<!--
function openHelp(){

  var urlStr = '../help/hlp001.do?pluginid=webmail&pgid=wml110';
  helpPrm = document.getElementsByName("helpPrm");

  if (helpPrm.length > 0) {
    for (i = 0; i < helpPrm.length; i++) {
      urlStr = urlStr + helpPrm[i].value;
    }
  }

  window.open(urlStr, 'help', '');
  return false;
}

// -->
</script>



<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl">個人設定</span></td>
        <td width="100%" class="header_ktool_bg_text2">[ フィルタ登録 ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_ok1" value="ＯＫ" onClick="buttonPush('confirm');">
          <input type="button" name="btn_filterSearch" class="btn_filterSearch" value="フィルターテスト" onClick="buttonPush('filterSearch');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="戻る" onClick="buttonPush('backToList');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>



  <tr>
  <td>
    <br>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">アカウント</span></td>
    <td align="left" class="td_wt" width="75%">
      <label for="filKbn1">山田 太郎</label>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">フィルター名</span></td>
    <td align="left" class="td_wt" width="70%">
      <input type="text" name="wml140FilterName" maxlength="100" value="" style="width:100%;">
    </td>
    </tr>
    </table>

    <p>受信メッセージが次の場合&nbsp;<input type="radio" name="wml140filterType" value="0" checked="checked" id="filtype1"><label for="filtype1">全ての条件に一致</label>
                                &nbsp;<input type="radio" name="wml140filterType" value="1" id="filtype2"><label for="filtype2">いずれかの条件に一致</label></p>








    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">送信者</span></td>
    <td align="left" class="td_wt text_base2" width="75%" nowrap>
      <input type="checkbox" name="wml140condition1" value="1" onclick="changeFilterInput();">&nbsp;&nbsp;
送信者が
<select name="wml140conditionExs1" size="1">
<option value="0">山田 太郎</option>
<option value="1">アカウント1</option>
<option value="1">アカウント2</option>
</select>
の場合
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">条件1</span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <input type="checkbox" name="wml140condition1" value="1" onclick="changeFilterInput();">&nbsp;&nbsp;


        <select name="wml140conditionType1" size="1"><option value="0">件名</option>
<option value="3">本文</option></select>



        <select name="wml140conditionExs1" size="1"><option value="0">に次を含む</option>
<option value="1">に次を含まない</option></select>


      <input type="text" name="wml140conditionText1" maxlength="256" size="60" value="">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">条件2</span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <input type="checkbox" name="wml140condition2" value="2" onclick="changeFilterInput();">&nbsp;&nbsp;


        <select name="wml140conditionType2" size="1"><option value="0">件名</option>
<option value="3">本文</option></select>



        <select name="wml140conditionExs2" size="1"><option value="0">に次を含む</option>
<option value="1">に次を含まない</option></select>


      <input type="text" name="wml140conditionText2" maxlength="256" size="60" value="">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">条件3</span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <input type="checkbox" name="wml140condition3" value="3" onclick="changeFilterInput();">&nbsp;&nbsp;


        <select name="wml140conditionType3" size="1"><option value="0">件名</option>
<option value="3">本文</option></select>



        <select name="wml140conditionExs3" size="1"><option value="0">に次を含む</option>
<option value="1">に次を含まない</option></select>


      <input type="text" name="wml140conditionText3" maxlength="256" size="60" value="">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">条件4</span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <input type="checkbox" name="wml140condition4" value="4" onclick="changeFilterInput();">&nbsp;&nbsp;


        <select name="wml140conditionType4" size="1"><option value="0">件名</option>
<option value="3">本文</option></select>



        <select name="wml140conditionExs4" size="1"><option value="0">に次を含む</option>
<option value="1">に次を含まない</option></select>


      <input type="text" name="wml140conditionText4" maxlength="256" size="60" value="">
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1">条件5</span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <input type="checkbox" name="wml140condition5" value="5" onclick="changeFilterInput();">&nbsp;&nbsp;


        <select name="wml140conditionType5" size="1"><option value="0">件名</option>
<option value="3">本文</option></select>



        <select name="wml140conditionExs5" size="1"><option value="0">に次を含む</option>
<option value="1">に次を含まない</option></select>


      <input type="text" name="wml140conditionText5" maxlength="256" size="60" value="">
    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">添付ファイル</span></td>
    <td align="left" class="td_wt text_base2">
       <input type="checkbox" name="wml140tempFile" value="1">添付ファイルがある場合
    </td>
    </tr>
    </table>

    <p>次の動作を実行する</p>


    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left" class="table_bg_A5B4E1"><input type="checkbox" name="wml140actionLabel" value="1"></td>
    <td align="left" class="td_wt" width="100%">
    ラベルを付ける

      <select name="wml140actionLabelValue" size="1"><option value="-1">選択してください。</option>
<option value="2">ラベルの1</option>
<option value="3">ラベルの2</option></select>

    </td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1"><input type="checkbox" name="wml140actionRead" value="1"></td>
    <td align="left" class="td_wt" width="100%">既読にする</td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1"><input type="checkbox" name="wml140actionDust" value="1"></td>
    <td align="left" class="td_wt" width="100%">ゴミ箱に移動する</td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1"><input type="checkbox" name="wml140actionDust" value="1"></td>
    <td align="left" class="td_wt" width="100%">転送しない</td>
    </tr>
    </table>

  </td>
  </tr>



  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="スペース">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" name="btn_add" class="btn_ok1" value="ＯＫ" onClick="buttonPush('confirm');">
          <input type="button" name="btn_filterSearch" class="btn_filterSearch" value="フィルターテスト" onClick="buttonPush('filterSearch');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="戻る" onClick="buttonPush('backToList');">
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
</body>
</html:html>