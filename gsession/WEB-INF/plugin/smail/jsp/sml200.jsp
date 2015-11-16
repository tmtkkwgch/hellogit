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
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../smail/js/sml200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession]
<logic:equal name="sml200Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>"><gsmsg:write key="sml.sml050.01" /></logic:equal>
<logic:equal name="sml200Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>"><gsmsg:write key="sml.sml050.02" /></logic:equal>
</title>
</head>

<body class="body_03">
<html:form action="/smail/sml200">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlAccountSid" />
<html:hidden property="selectedHinaSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml050HinaKbn" />
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

  <table cellpadding="0" cellspacing="0" border="0" width="50%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl">個人設定</span></td>
        <td width="100%" class="header_ktool_bg_text2">[ ラベルの管理 ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_add_n1" value="追加" onClick="buttonPush('labelAdd');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="戻る" onClick="buttonPush('backFromLabel');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr>
    <td>

    </td>
  </tr>
  <tr><td>

    <br>
    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD text_tlw" width="0%" nowrap>設定するアカウント</th>
    <td align="left" class="smail_td1" width="100%">山田  太郎
      <input id="accountSelBtn" type="button" name="btn_add" class="btn_base1" value="アカウント選択" >
    </td>
    </tr>
    </table>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td style="white-space:nowrap;">
        <input type="button" class="btn_base0" value="上へ" name="btn_upper" onClick="buttonPush('upLabelData');">
        <input type="button" class="btn_base0" value="下へ" name="btn_downer" onClick="buttonPush('downLabelData');">
      </td>
    </tr>
    </table>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="0%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="100%"><span class="text_tlw">ラベル名</span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw">修正</span></th>
    <th align="center" class="table_bg_7D91BD" width="0%"><span class="text_tlw">削除</span></th>
    </tr>





      <tr>
      <td align="center" class="smail_td1" nowrap><input type="radio" name="wml110SortRadio" value="2:1:0" checked="checked" id="chkLabel0"></td>
      <td align="left" class="smail_td1" onClick="wml110CheckLabel('chkLabel0');">
        ラベルの1
      </td>
      <td align="left" class="smail_td1"><input type="button" class="btn_edit_n3" value="修正" name="btn_change" onClick="buttonPush('labelAdd');"></td>
      <td align="left" class="smail_td1"><input type="button" class="btn_dell_n3" value="削除" name="btn_delete" onClick="deleteLabel('2');"></td>
    </tr>



      <tr>
      <td align="center" class="smail_td1" nowrap><input type="radio" name="wml110SortRadio" value="3:2:1" id="chkLabel1"></td>
      <td align="left" class="smail_td1" onClick="wml110CheckLabel('chkLabel1');">
        ラベルの2
      </td>
      <td align="left" class="smail_td1"><input type="button" class="btn_edit_n3" value="修正" name="btn_change" onClick="buttonPush('labelAdd');"></td>
      <td align="left" class="smail_td1"><input type="button" class="btn_dell_n3" value="削除" name="btn_delete" onClick="deleteLabel('3');"></td>
    </tr>



    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">&nbsp;</td>
      </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>





<div id="accountSelPop" title="アカウント選択" style="display:none;">
  <p>


      <table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" align="center">

        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td>


        </td>
        </tr>

        <tr>
        <td>

          <p class="type_p">
          <input type="text" name="wml030keyword" maxlength="50" size="25" value="">
          <input type="button" onclick="buttonPush('search');" class="btn_base0" value="検索">
          &nbsp;
          <small>グループ</small>
          <select name="wml030group" onchange="buttonPush('init');"><option value="-1" selected="selected">選択してください。</option>
      <option value="0">システム管理グループ</option>
      <option value="1">取締役</option>
      <option value="2">社外取締役</option>
      <option value="3">秘書室</option>
      <option value="19">法務部</option>
      <option value="10">営業部</option>
      <option value="14">　営業１課</option>
      <option value="15">　営業２課</option>
      <option value="44">札幌本社</option>
      <option value="45">テストグループ456</option>
      <option value="46">テストグループ</option>
      <option value="17">人事部</option>
      <option value="32">　人事評価</option>
      <option value="34">　　採用</option>
      <option value="33">　人事異動</option>
      <option value="7">管理部</option>
      <option value="9">　品質管理</option>
      <option value="23">　品質監査</option>
      <option value="18">経理部</option>
      <option value="28">　会計簿記記帳</option>
      <option value="30">　　管理会計資料作成</option>
      <option value="29">　税務申告</option>
      <option value="31">　決算</option>
      <option value="16">企画部</option>
      <option value="25">　経営企画</option>
      <option value="26">　業務支援</option>
      <option value="27">　事業分析</option>
      <option value="4">　広報室</option>
      <option value="6">理財部</option>
      <option value="24">　有価証券管理</option>
      <option value="21">　資材調達室</option>
      <option value="47">営業部</option>
      <option value="8">生産部</option>
      <option value="20">　生産企画室</option>
      <option value="22">　生産技術管理</option>
      <option value="5">総務部</option>
      <option value="39">　総務課</option>
      <option value="42">　業務効率推進課</option>
      <option value="43">　福利衛生課</option>
      <option value="40">総務部 札幌本部</option>
      <option value="41">総務部 東京本部</option>
      <option value="11">システム開発部</option>
      <option value="12">　第１開発グループ</option>
      <option value="35">　　第１ソフトウェア開発グループ</option>
      <option value="36">　　第２ソフトウェア開発グループ</option>
      <option value="13">　第２開発グループ</option>
      <option value="37">　技術支援グループ</option>
      <option value="38">運用サポート</option></select>
          <input type="button" onclick="openGroupWindow(this.form.wml030group, 'wml030group', '0', 'init')" class="group_btn" value="&nbsp;&nbsp;" id="wml030GroupBtn">

          <small>ユーザ</small>
          <select name="wml030user"><option value="-1" selected="selected">指定無し</option></select>
          </p>


          <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
          <tr>

          <th align="center" class="table_bg_7D91BD" width="20%">
          <a href="#" onClick="return sort(0, 1);"><span class="text_tlw">▲アカウント名</span></a>
          </th>
          <th align="center" class="table_bg_7D91BD" width="10%">
          <a href="#" onClick="return sort(3, 0);"><span class="text_tlw">ディスク使用量</span></a>
          </th>
          <th align="center" class="table_bg_7D91BD" width="10%">
          <a href="#" onClick="return sort(4, 0);"><span class="text_tlw">受信日時</span></a>
          </th>

          <th align="center" class="table_bg_7D91BD" width="15%"><span class="text_tlw">備考</span></th>
          </tr>



          <tr class="td_line_color1" id="1">


          <td align="left" class="prj_td">
            <a href="#" onClick="buttonPush('accountDetail');"><span class="text_link">山田 太郎</span></a>
          </td>
          <td align="right" class="prj_td"><span class="text_base">0.5MB</span></td>
          <td align="left" class="prj_td"><span class="text_base">2013/07/19 13:33:22</span></td>
          <td align="left" class="prj_td"><span class="text_base"></span></td>
          </tr>








          <tr class="td_line_color2" id="13">


          <td align="left" class="prj_td">
            <a href="#" onClick="buttonPush('accountDetail');"><span class="text_link">サンプルアカウント</span></a>
          </td>
          <td align="right" class="prj_td"><span class="text_base">0.0MB</span></td>
          <td align="left" class="prj_td"><span class="text_base"></span></td>
          <td align="left" class="prj_td"><span class="text_base">サンプルです。</span></td>
          </tr>








          <tr class="td_line_color1" id="4">


          <td align="left" class="prj_td">
            <a href="#" onClick="buttonPush('accountDetail');"><span class="text_link">並木 希望</span></a>
          </td>
          <td align="right" class="prj_td"><span class="text_base">0.5MB</span></td>
          <td align="left" class="prj_td"><span class="text_base">2013/05/09 10:00:12</span></td>
          <td align="left" class="prj_td"><span class="text_base"></span></td>
          </tr>








          <tr class="td_line_color2" id="5">


          <td align="left" class="prj_td">
            <a href="#" onClick="buttonPush('accountDetail');"><span class="text_link">小柳 千紘</span></a>
          </td>
          <td align="right" class="prj_td"><span class="text_base">0.5MB</span></td>
          <td align="left" class="prj_td"><span class="text_base">2013/05/09 10:00:12</span></td>
          <td align="left" class="prj_td"><span class="text_base"></span></td>
          </tr>








          <tr class="td_line_color1" id="2">


          <td align="left" class="prj_td">
            <a href="#" onClick="buttonPush('accountDetail');"><span class="text_link">畠中 隆男</span></a>
          </td>
          <td align="right" class="prj_td"><span class="text_base">0.5MB</span></td>
          <td align="left" class="prj_td"><span class="text_base">2013/05/09 10:00:12</span></td>
          <td align="left" class="prj_td"><span class="text_base"></span></td>
          </tr>








          <tr class="td_line_color2" id="3">


          <td align="left" class="prj_td">
            <a href="#" onClick="buttonPush('accountDetail');"><span class="text_link">石橋 鈴音</span></a>
          </td>
          <td align="right" class="prj_td"><span class="text_base">0.5MB</span></td>
          <td align="left" class="prj_td"><span class="text_base">2013/05/09 10:00:12</span></td>
          <td align="left" class="prj_td"><span class="text_base"></span></td>
          </tr>


          </table>

        </td>
        </tr>

        </table>

      </td>
      </tr>
      </table>



  </p>
</div>







</html:form>
</body>
</html:html>