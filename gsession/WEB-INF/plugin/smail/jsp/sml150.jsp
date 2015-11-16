<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../smail/js/sml150.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.07" /></title>
</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/smail/sml150">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />

<logic:notEmpty name="sml150Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml150Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.07" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="submitStyleChange();buttonPush('ok');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backToKtool');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">




<br>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td width="0%" colspan="2" nowrap><span class="text_r1">※<gsmsg:write key="wml.wml020.02" /></span></td>
    </tr>
    <%--
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.53" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
        <html:radio name="sml150Form" property="sml150DelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_DEL_KBN_ADM_SETTING) %>" styleId="sml150DelKbn_0" onclick="changeDelKbn();" /><label for="sml150DelKbn_0"><gsmsg:write key="cmn.set.the.admin" /></label>&nbsp;
        <html:radio name="sml150Form" property="sml150DelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_DEL_KBN_USER_SETTING) %>" styleId="sml150DelKbn_1" onclick="changeDelKbn();" /><label for="sml150DelKbn_1"><gsmsg:write key="cmn.set.eachuser" /></label>&nbsp;
      </span>
    </td>
    </tr>
    --%>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.50" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml150Form" property="sml150JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml150JdelKbn_0" onclick="setDispState(this.form.sml150JdelKbn, this.form.sml150JYear, this.form.sml150JMonth)" /><label for="sml150JdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml150Form" property="sml150JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml150JdelKbn_1" onclick="setDispState(this.form.sml150JdelKbn, this.form.sml150JYear, this.form.sml150JMonth)" /><label for="sml150JdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml150Form" property="sml150YearLabelList">
          <html:select property="sml150JYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml150Form" property="sml150MonthLabelList">
          <html:select property="sml150JMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.52" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml150Form" property="sml150SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml150SdelKbn_0" onclick="setDispState(this.form.sml150SdelKbn, this.form.sml150SYear, this.form.sml150SMonth)" /><label for="sml150SdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml150Form" property="sml150SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml150SdelKbn_1" onclick="setDispState(this.form.sml150SdelKbn, this.form.sml150SYear, this.form.sml150SMonth)" /><label for="sml150SdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml150Form" property="sml150YearLabelList">
          <html:select property="sml150SYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml150Form" property="sml150MonthLabelList">
          <html:select property="sml150SMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.51" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml150Form" property="sml150WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml150WdelKbn_0" onclick="setDispState(this.form.sml150WdelKbn, this.form.sml150WYear, this.form.sml150WMonth)" /><label for="sml150WdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml150Form" property="sml150WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml150WdelKbn_1" onclick="setDispState(this.form.sml150WdelKbn, this.form.sml150WYear, this.form.sml150WMonth)" /><label for="sml150WdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml150Form" property="sml150YearLabelList">
          <html:select property="sml150WYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml150Form" property="sml150MonthLabelList">
          <html:select property="sml150WMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.49" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <span class="text_base">

        <html:radio name="sml150Form" property="sml150DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>" styleId="sml150DdelKbn_0" onclick="setDispState(this.form.sml150DdelKbn, this.form.sml150DYear, this.form.sml150DMonth)" /><label for="sml150DdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
        <html:radio name="sml150Form" property="sml150DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>" styleId="sml150DdelKbn_1" onclick="setDispState(this.form.sml150DdelKbn, this.form.sml150DYear, this.form.sml150DMonth)" /><label for="sml150DdelKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

        <gsmsg:write key="cmn.after.data.head" />

        <logic:notEmpty name="sml150Form" property="sml150YearLabelList">
          <html:select property="sml150DYear" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150YearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="sml150Form" property="sml150MonthLabelList">
          <html:select property="sml150DMonth" styleClass="select01" style="width: 100px;">
            <html:optionsCollection name="sml150Form" property="sml150MonthLabelList" value="value" label="label" />
          </html:select>
           <gsmsg:write key="cmn.after.data" />
        </logic:notEmpty>

      </span>

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="submitStyleChange();buttonPush('ok');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backToKtool');">
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