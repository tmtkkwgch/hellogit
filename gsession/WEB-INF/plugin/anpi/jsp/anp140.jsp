<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp140.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03" onload="setfocus();" onunload="windowClose()">
<html:form action="/anpi/anp140">
<!-- BODY -->
<input type="hidden" name="CMD">

<html:hidden property="backScreen" />
<html:hidden property="anp130SelectAphSid" />
<html:hidden property="anp130allCheck" />
<html:hidden property="anp130NowPage" />
<html:hidden property="anp130DspPage1" />
<html:hidden property="anp130DspPage2" />
<html:hidden property="anp140NowPage" />
<html:hidden property="anp140DspPage1" />
<html:hidden property="anp140DspPage2" />

<html:hidden property="anp140SortKeyIndex" />
<html:hidden property="anp140OrderKey" />

<html:hidden property="anp140ScrollFlg" />

<logic:notEmpty name="anp140Form" property="anp130DelSidList" scope="request">
  <logic:iterate id="del" name="anp140Form" property="anp130DelSidList" scope="request">
    <input type="hidden" name="anp130DelSidList" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> <gsmsg:write key="anp.anp140.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="cmn.register.copy.new"/>" class="btn_base1_3" onClick="buttonPush('anp140copyNew');">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('anp140delete');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp140back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- 訓練モード バー -->
    <logic:equal name="anp140Form" property="anp010KnrenFlg" value="1">
      <table cellspacing="0" border="0" width="100%" class="tl_u2" id="knren_top">
        <tr>
          <td valign="middle" class="table_bg_FFC1C1" align="center">
            <span class="text_r2"><gsmsg:write key="anp.knmode"/></span>
          </td>
        </tr>
      </table>
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:equal>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
            <td valign="middle" width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="anp.date.send"/></span></td>
            <td valign="middle" width="80%" class="td_wt" ><span class="text_base"><bean:write name="anp140Form" property="anp140HaisinDate"/></span></td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="anp.sender"/></span></td>
            <td valign="middle" align="left" class="td_wt" ><span class="text_base"><bean:write name="anp140Form" property="anp140Name"/></span></td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject"/></span></td>
            <td valign="middle" align="left" class="td_wt" >
                <span class="text_base"><bean:write name="anp140Form" property="anp140Subject"/></span>
            </td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.body"/></span></td>
            <td valign="middle" align="left" class="td_wt" width="100%">
            <span class="text_base">
            <bean:write name="anp140Form" property="anp140Body" filter="false"/>
            </span>
            </td>
        </tr>

        <tr>
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="anp.anp140.02"/></span></td>
            <td valign="middle" align="left" class="td_wt" >

                <a id="label_sender" name="label_sender"></a>
                <table cellspacing="5" width="500" border="0">
                    <tr>
                    <td width="60%">
                      <table class="tl0 table_td_border" width="100%" cellpadding="5" cellspacing="0">
                         <tr class="td_type1">
                             <th class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.date.send"/></th>
                             <td colspan="2" nowrap><bean:write name="anp140Form" property="anp140HaisinDate"/></td>
                         </tr>
                         <tr class="td_type1">
                             <th class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.date.end"/></th>
                             <td colspan="2" nowrap><bean:write name="anp140Form" property="anp140EndDate"/></td>
                         </tr>
                         <tr class="td_type1">
                             <th class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.anp140.03"/></th>
                             <td colspan="2" nowrap><bean:write name="anp140Form" property="anp140ReplyState"/></td>
                         </tr>
                         <tr class="td_type1">
                             <th width="0%" class="table_bg_A5B4E1" rowspan="3" nowrap><gsmsg:write key="anp.state"/></th>
                             <td width="0%" class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.jokyo.good"/></td>
                             <td width="20%" align="right" nowrap><bean:write name="anp140Form" property="anp140Buji"/></td>
                         </tr>
                         <tr class="td_type1">
                             <td class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.jokyo.keisyo"/></td>
                             <td align="right" nowrap><bean:write name="anp140Form" property="anp140Keisyo"/></td>
                         </tr>
                         <tr class="td_type1">
                             <td class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.jokyo.jusyo"/></td>
                             <td align="right" nowrap><bean:write name="anp140Form" property="anp140Jyusyo"/></td>
                         </tr>
                         <tr class="td_type1">
                             <th class="table_bg_A5B4E1" rowspan="2" nowrap><gsmsg:write key="anp.syusya"/></th>
                             <td class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.syusya.ok2"/></td>
                             <td align="right" nowrap><bean:write name="anp140Form" property="anp140SyusyaOk"/></td>
                         </tr>
                         <tr class="td_type1">
                             <td class="table_bg_A5B4E1" nowrap><gsmsg:write key="anp.syusya.no"/></td>
                             <td align="right" nowrap><bean:write name="anp140Form" property="anp140SyusyaNo"/></td>
                         </tr>
                     </table>
                    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
                    </td>
                    <td align="right" valign="bottom" >
                      <!-- ページコンボ -->
                      <bean:size id="pageCount" name="anp140Form" property="anp140PageLabel" scope="request" />
                      <logic:greaterThan name="pageCount" value="1">
                      <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp140pageLast');">
                      <html:select property="anp140DspPage1" onchange="changePage(this);" styleClass="text_i">
                        <html:optionsCollection name="anp140Form" property="anp140PageLabel" value="value" label="label"/>
                      </html:select>
                      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp140pageNext');">
                      </logic:greaterThan>
                      </td>
                    </tr>
                </table>

                <table width="500" border="0" cellpadding="0" cellspacing="0" class="tl0">


              <!-- ヘッダ部分 -->
              <tr class="table_bg_7D91BD">
              <bean:define id="sortKeyIndex" name="anp140Form" property="anp140SortKeyIndex" />
              <bean:define id="orderKey" name="anp140Form" property="anp140OrderKey" />
              <gsmsg:define id="h_send" msgkey="anp.send.dest"/>
              <gsmsg:define id="h_mail" msgkey="cmn.mailaddress"/>
              <gsmsg:define id="h_ans" msgkey="anp.date.ans"/>
              <gsmsg:define id="h_state" msgkey="anp.state"/>

              <% int iSortKeyIndex = ((Integer) sortKeyIndex).intValue();   %>
              <% int iOrderKey = ((Integer) orderKey).intValue();     %>
              <% String[] colTitle = new String[] {h_send, h_mail, h_ans, h_state}; %>
              <% String[] colWidth = new String[] {"20%", "35%", "20%", "15%"}; %>
              <% Integer[] colOrder = new Integer[] {1, 1, 1, 1}; %>
              <% for (int i = 0; i < colTitle.length; i++) { %>
                  <%   String title = colTitle[i];                    %>
                  <%   Integer order = -1;                            %>
                  <%   if (iSortKeyIndex == i) {                      %>
                  <%     if (iOrderKey == GSConst.ORDER_KEY_ASC) {    %>
                  <%       title = title + "▲";                      %>
                  <%     } else {                                     %>
                  <%       title = title + "▼";                      %>
                  <%     }                                            %>
                  <%     order = iOrderKey;                           %>
                  <%   }                                              %>
                  <th width="<%= colWidth[i] %>" class="td_type14" nowrap>
                  <% if (colOrder[i] == 1) { %><a href="#" onClick="return sortList(<%= i %>, <%= order %>);"><% } %>
                  <span class="text_tlw"><%= title %></span><% if (colOrder[i] == 1) { %></a><% } %></th>
              <% } %>
              </tr>

                  <logic:notEmpty name="anp140Form" property="anp140JyokyoList">
                  <logic:iterate id="Jyookyo" name="anp140Form" property="anp140JyokyoList" indexId="idx">
                     <tr>
                     <!-- 送信者 -->
                     <td class="td_type1" nowrap><span class="text_base2">
                     <logic:equal name="Jyookyo" property="apsType" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEND_TYPE_GROUP) %>">
                       <a href="javascript:void(0)" onClick="openGrpUsrRirekiWindow(<bean:write name="anp140Form" property="anp130SelectAphSid"/>,<bean:write name="Jyookyo" property="grpSid"/>);"><bean:write name="Jyookyo" property="grpNameTo"/></a>
                     </logic:equal>
                     <logic:notEqual name="Jyookyo" property="apsType" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEND_TYPE_GROUP) %>">
                       <logic:equal name="Jyookyo" property="jyotaiKbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                       <bean:write name="Jyookyo" property="nameTo"/>
                       </logic:equal>
                       <logic:notEqual name="Jyookyo" property="jyotaiKbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                       <del><bean:write name="Jyookyo" property="nameTo"/></del>
                       </logic:notEqual>
                     </logic:notEqual>
                     </span></td>
                     <!-- 連絡先 -->
                     <logic:equal name="Jyookyo" property="apsType" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEND_TYPE_GROUP) %>">
                     <td class="td_type1" nowrap align="center"><span class="text_base2">-</span></td>
                     </logic:equal>
                     <logic:notEqual name="Jyookyo" property="apsType" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.SEND_TYPE_GROUP) %>">
                     <td class="td_type1" nowrap align="left"><span class="text_base2"><bean:write name="Jyookyo" property="mailAddress"/></span></td>
                     </logic:notEqual>
                     <!-- 返信日時 -->
                     <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="Jyookyo" property="hensinDate"/></span></td>
                     <!-- 状態 -->
                     <td class="td_type1" nowrap align="center">
                     <logic:equal name="Jyookyo" property="anpJyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>">-</logic:equal>
                     <logic:equal name="Jyookyo" property="anpJyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>"><span class="text_jokyoGood"><gsmsg:write key="anp.jokyo.good"/></span></logic:equal>
                     <logic:equal name="Jyookyo" property="anpJyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>"><span class="text_jokyoKeisyo"><gsmsg:write key="anp.jokyo.keisyo"/></span></logic:equal>
                     <logic:equal name="Jyookyo" property="anpJyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>"><span class="text_jokyoJyusyo"><gsmsg:write key="anp.jokyo.jusyo"/></span></logic:equal>
                     </td>
                     </tr>
                  </logic:iterate>
                  </logic:notEmpty>

                 </table>

                 <table cellspacing="5" width="500" border="0">
                    <tr>
                    <td align="right">
                      <!-- ページコンボ -->
                      <bean:size id="pageCount" name="anp140Form" property="anp140PageLabel" scope="request" />
                      <logic:greaterThan name="pageCount" value="1">
                      <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp140pageLast');">
                      <html:select property="anp140DspPage2" onchange="changePage(this);" styleClass="text_i">
                        <html:optionsCollection name="anp140Form" property="anp140PageLabel" value="value" label="label"/>
                      </html:select>
                      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp140pageNext');">
                      </logic:greaterThan>
                    </td>
                    </tr>
                </table>
            </td>
        </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="<gsmsg:write key="cmn.register.copy.new"/>" class="btn_base1_3" onClick="buttonPush('anp140copyNew');">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('anp140delete');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp140back');">
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