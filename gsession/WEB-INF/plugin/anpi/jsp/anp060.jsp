<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthSubject = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_SUBJECT); %>
<% String maxLengthText1   = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_TEXT1); %>
<% String maxLengthText2   = String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MAXLENGTH_TEXT2); %>
<% String maxLengthMail    = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_MAIL); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="anp.anp060.01"/></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/jquery.selection-min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp060.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03" onload="setfocus();showLengthId($('#inputstr1')[0],<%=maxLengthText1 %>,'inputlength1');showLengthId($('#inputstr2')[0],<%=maxLengthText2 %>,'inputlength2');">
<html:form action="/anpi/anp060">

<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anpiSid" />
<html:hidden property="anp010SelectGroupSid" />
<html:hidden property="anp010NowPage" />
<html:hidden property="anp010SortKeyIndex" />
<html:hidden property="anp010OrderKey" />

<html:hidden property="anp060ProcMode" />
<html:hidden property="anp060ScrollFlg" />
<html:hidden property="anp060CopyAnpiSid" />

<logic:notEmpty name="anp060Form" property="anp060SenderList" scope="request">
    <logic:iterate id="ulBean" name="anp060Form" property="anp060SenderList" scope="request">
    <input type="hidden" name="anp060SenderList" value="<bean:write name="ulBean" />">
    </logic:iterate>
</logic:notEmpty>

<html:hidden property="anp130SelectAphSid" />
<html:hidden property="anp130allCheck" />
<html:hidden property="anp130NowPage" />
<html:hidden property="anp130DspPage1" />
<html:hidden property="anp130DspPage2" />
<html:hidden property="anp140NowPage" />
<html:hidden property="anp140DspPage1" />
<html:hidden property="anp140DspPage2" />
<html:hidden property="anp140ScrollFlg" />

<logic:notEmpty name="anp060Form" property="anp130DelSidList" scope="request">
  <logic:iterate id="del" name="anp060Form" property="anp130DelSidList" scope="request">
    <input type="hidden" name="anp130DelSidList" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

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
        <td width="0%" class="header_white_bg_text"><gsmsg:write key="anp.plugin"/> [ <gsmsg:write key="anp.anp060.02"/> ]</td>
        <td width="100%" class="header_white_bg"></td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="安否確認"></td>
        </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="anp.send2"/>" class="btn_haisin" onClick="buttonPush('anp060haisin');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp060back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellspacing="0" border="0" width="100%" class="tl_u2" id="knren_top">
        <!-- 訓練モード バー -->
        <tr>
          <td valign="middle" class="table_bg_FFC1C1" align="center">
            <span class="text_r2"><gsmsg:write key="anp.knmode"/></span>
          </td>
        </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
            <!-- 訓練フラグ -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.knmode"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
                <html:checkbox name="anp060Form" property="anp010KnrenFlg" value="1" styleId="knrenFlg" onclick="setKnrenMode();" /><label for="knrenFlg" class="text_base"><gsmsg:write key="anp.anp060.03"/></label>
            </td>
        </tr>

        <!-- メイン表示 -->
        <tr>
          <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="cmn.main.view" /></span>
          </td>
          <td valign="middle" align="left" class="td_wt table_pad" >
            <html:radio name="anp060Form" property="anp060main" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.APH_VIEW_MAIN_ALL) %>" styleId="anpMain0" /><label for="anpMain0" class="text_base"><gsmsg:write key="cmn.alluser"/></label>
            &nbsp;
            <html:radio name="anp060Form" property="anp060main" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.APH_VIEW_MAIN_SENDTO) %>" styleId="anpMain1" /><label for="anpMain1" class="text_base"><gsmsg:write key="anp.anp060.11"/></label>
          </td>
        </tr>

        <tr>
            <!-- 定型メッセージ選択 -->
            <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp060.04"/></span>
            </td>
            <td width="80%" valign="middle" align="left" class="td_wt" width="100%">
	            <html:select property="anp060SelectMtempSid" styleId="mtempSid" >
                <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                <logic:notEmpty name="anp060Form" property="anp060MtempLabel">
                <html:optionsCollection name="anp060Form" property="anp060MtempLabel" value="value" label="label" />
                </logic:notEmpty>
                </html:select>
	            <input type="button" value="選択" class="btn_base1ss" onClick="buttonPush('anp060selectTemp');">
            </td>
        </tr>

        <tr>
            <!-- 件名 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="cmn.subject"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
                <span id="lmtinput" class="text_base">【 <gsmsg:write key="anp.knmode"/> 】</span>
                <html:text name="anp060Form" property="anp060Subject" maxlength="<%=maxLengthSubject %>" styleClass="text_base" styleId="subject" style="width:217px;" />
            </td>
        </tr>

        <tr>
            <!-- 本文 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1">本文<span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" width="100%">
                <span id="lmtinput2" class="text_base">
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━<br>

                ※<gsmsg:write key="anp.knmode"/><br>
                <gsmsg:write key="anp.anp060.05"/><br>
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━<br><br></span>
                <% String onkey1 = "showLengthStr(value," + maxLengthText1 + ",'inputlength1');"; %>
                <html:textarea styleId="inputstr1" name="anp060Form" property="anp060Text1" style="width:676px;" rows="10" styleClass="text_base" onkeyup="<%= onkey1 %>" />
                <br>
                <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span>
                <span id="inputlength1" class="font_string_count">0</span>&nbsp;
                <span class="font_string_count_max">/&nbsp;<%=maxLengthText1 %>&nbsp;<gsmsg:write key="cmn.character" /></span>
                <span class="text_base">
                <br><br><bean:write name="anp060Form" property="anp060MessageBody" filter="false" /><br><br>
                </span>
                <% String onkey2 = "showLengthStr(value," + maxLengthText2 + ",'inputlength2');"; %>
                <html:textarea styleId="inputstr2" name="anp060Form" property="anp060Text2" style="width:676px;" rows="8" styleClass="text_base" onkeyup="<%= onkey2 %>" />
                <br>
                <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span>
                <span id="inputlength2" class="font_string_count">0</span>&nbsp;
                <span class="font_string_count_max">/&nbsp;<%=maxLengthText2 %>&nbsp;<gsmsg:write key="cmn.character" /></span>
            </td>
        </tr>

        <tr>
            <!-- 送信先 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.send.dest"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
            </td>
            <td valign="middle" align="left" class="td_wt" width="100%">
            <a id="label_sender" name="label_sender"></a>
            <table width="0%" border="0">
              <tr>
              <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.sender"/></span></td>
              <td width="20%" align="center">&nbsp;</td>

              <td width="40%" align="left">
              <logic:notEqual name="anp060Form" property="anp060SelectGroupSid" value="-9">
              <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.anp060SelectGroupSid, 'anp060SelectGroupSid', '<bean:write name="anp060Form" property="anp060SelectGroupSid" />', '1', 'anp060group', 'anp060SenderList', '-1', '1')" type="button"><br>
              </logic:notEqual>
                <!-- グループコンボボックス -->
                <logic:notEmpty name="anp060Form" property="anp060GroupLabel" scope="request">
                  <html:select property="anp060SelectGroupSid" onchange="buttonPush('anp060group');" styleClass="select05">

                  <logic:notEmpty name="anp060Form" property="anp060GroupLabel" scope="request">
                  <logic:iterate id="gpBean" name="anp060Form" property="anp060GroupLabel" scope="request">

                  <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                  <logic:equal name="gpBean" property="styleClass" value="0">
                  <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:equal>

                  <logic:notEqual name="gpBean" property="styleClass" value="0">
                  <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:notEqual>

                  </logic:iterate>
                  </logic:notEmpty>

                  </html:select>
                  <span class="text_base8">
                  <input type="button" onclick="openGroupWindow(this.form.anp060SelectGroupSid, 'anp060SelectGroupSid', '1', 'anp060group')" class="group_btn2" value="&nbsp;" id="groupBtn">
                  <input type="checkbox" value="1" id="select_user" onclick="selectUsersList(this, 'anp060SelectBelongSid');" />
                  <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>
                </logic:notEmpty>
              </td>
              </tr>

              <tr>
              <!-- 送信者ユーザリスト -->
              <td align="center">
                <select size="10" multiple name="anp060SelectSenderSid" class="select01">
                  <logic:notEmpty name="anp060Form" property="anp060SenderLabel" scope="request">
                  <logic:iterate id="laveValueBean" name="anp060Form" property="anp060SenderLabel" scope="request">
                     <option value="<bean:write name="laveValueBean" property="value" />"><bean:write name="laveValueBean" property="label"/></option>
                  </logic:iterate>
                  </logic:notEmpty>
                  <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                </select>
              </td>

              <td align="center">
                <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('anp060senderAdd');"><br><br>
                <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('anp060senderDel');">
              </td>

              <!-- グループ所属ユーザリスト -->
              <td valign="top" rowspan="3">
                <select size="10" multiple name="anp060SelectBelongSid" class="select01">
                  <logic:notEmpty name="anp060Form" property="anp060BelongLabel" scope="request">
                  <logic:iterate id="laveValueBean" name="anp060Form" property="anp060BelongLabel" scope="request">
                    <option value="<bean:write name="laveValueBean" property="value"/>"><bean:write name="laveValueBean" property="label"/></option>
                  </logic:iterate>
                  </logic:notEmpty>
                  <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                </select>
              </td>
              </tr>
            </table>
            </td>
        </tr>

        <tr>
            <!-- テスト送信 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="anp.anp060.06"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
            <html:text name="anp060Form" property="anp060TestAdr" maxlength="<%=maxLengthMail %>" styleClass="text_base" style="width:385px;" />
            <input type="button" value="送信" class="btn_base1ss" onClick="buttonPush('anp060sendTest');">
            </td>
        </tr>

        <tr>
            <!-- 配信者 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="anp.sender"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
            <span class="text_base"><bean:write name="anp060Form" property="anp060RegistName" /></span>
            </td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="<gsmsg:write key="anp.send2"/>" class="btn_haisin" onClick="buttonPush('anp060haisin');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp060back');">
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