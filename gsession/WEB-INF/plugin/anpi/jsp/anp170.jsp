<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="anp.plugin"/></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../anpi/js/anp170.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>

<body class="body_03" onload="" onunload="">

<html:form action="/anpi/anp170">

<input type="hidden" name="CMD" value="">
<html:hidden property="anp170AnpSid" />
<html:hidden property="anp170GrpSid" />


<html:hidden property="anp170NowPage" />
<html:hidden property="anp170DspPage1" />
<html:hidden property="anp170DspPage2" />

<html:hidden property="anp170SortKeyIndex" />
<html:hidden property="anp170OrderKey" />

<!--　BODY -->

<table width="100%" cellpadding="5" cellspacing="0">

<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../anpi/images/header_anpi_01.gif" border="0" alt="<gsmsg:write key="anp.plugin"/>"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="anp.plugin"/></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="anp.anp140.01"/> <gsmsg:write key="anp.anp140.02"/> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="anp.plugin"/>"></td>
    </tr>
    </table>


  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
  <tr><td><img src="../common/images/spacer.gif" style="width:10px; height:10px;" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td align="center">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td align="left" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group"/></span></td>
    <td align="left" class="td_type20" width="85%"><bean:write name="anp170Form" property="anp170GrpName"/></td>
    </tr>

        <tr height="400px">
            <!-- 送信先 -->
            <td valign="top" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.anp140.02"/></span>
            </td>
            <td valign="top" align="left" class="td_wt table_pad" width="100%">
                <table cellspacing="5" width="400" border="0">
                    <tr>
                    <td align="right">
                      <!-- ページコンボ -->
                      <bean:size id="pageCount" name="anp170Form" property="anp170PageLabel" scope="request" />
                      <logic:greaterThan name="pageCount" value="1">
                      <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp170pageLast');">
                      <html:select property="anp170DspPage1" onchange="changePage(this);" styleClass="text_i">
                        <html:optionsCollection name="anp170Form" property="anp170PageLabel" value="value" label="label"/>
                      </html:select>
                      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp170pageNext');">
                      </logic:greaterThan>
                      </td>
                    </tr>
                </table>

                <table width="400px" border="0" cellpadding="0" cellspacing="0" class="tl0">

                <!-- ヘッダ部分 -->
                <tr class="table_bg_7D91BD">
                <bean:define id="sortKeyIndex" name="anp170Form" property="anp170SortKeyIndex" />
                <bean:define id="orderKey" name="anp170Form" property="anp170OrderKey" />
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

                <logic:notEmpty name="anp170Form" property="anp170JyokyoList">
                <logic:iterate id="Jyookyo" name="anp170Form" property="anp170JyokyoList" indexId="idx">
                   <tr>
                   <!-- 送信者 -->
                   <td class="td_type1" nowrap><span class="text_base2">
                     <logic:equal name="Jyookyo" property="anp170JyotaiKbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                     <bean:write name="Jyookyo" property="anp170NameTo"/>
                     </logic:equal>
                     <logic:notEqual name="Jyookyo" property="anp170JyotaiKbn" value="<%= String.valueOf(GSConst.JTKBN_TOROKU) %>">
                     <del><bean:write name="Jyookyo" property="anp170NameTo"/></del>
                     </logic:notEqual>
                   </span></td>
                   <!-- 連絡先 -->
                   <td class="td_type1" nowrap align="left"><span class="text_base2"><bean:write name="Jyookyo" property="anp170MailAddress"/></span></td>
                   <!-- 返信日時 -->
                   <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="Jyookyo" property="anp170HensinDate"/></span></td>
                   <!-- 状態 -->
                   <td class="td_type1" nowrap align="center">
                   <logic:equal name="Jyookyo" property="anp170Jyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_UNSET) %>">-</logic:equal>
                   <logic:equal name="Jyookyo" property="anp170Jyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_GOOD) %>"><span class="text_jokyoGood"><gsmsg:write key="anp.jokyo.good"/></span></logic:equal>
                   <logic:equal name="Jyookyo" property="anp170Jyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_KEISYO) %>"><span class="text_jokyoKeisyo"><gsmsg:write key="anp.jokyo.keisyo"/></span></logic:equal>
                   <logic:equal name="Jyookyo" property="anp170Jyokyo" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.JOKYO_FLG_JUSYO) %>"><span class="text_jokyoJyusyo"><gsmsg:write key="anp.jokyo.jusyo"/></span></logic:equal>
                   </td>
                   </tr>
                </logic:iterate>
                </logic:notEmpty>

                </table>

                 <table cellspacing="5" width="400" border="0">
                   <tr>
                   <td align="right">
                     <!-- ページコンボ -->
                     <bean:size id="pageCount" name="anp170Form" property="anp170PageLabel" scope="request" />
                     <logic:greaterThan name="pageCount" value="1">
                     <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp170pageLast');">
                     <html:select property="anp170DspPage2" onchange="changePage(this);" styleClass="text_i">
                       <html:optionsCollection name="anp170Form" property="anp170PageLabel" value="value" label="label"/>
                     </html:select>
                     <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp170pageNext');">
                     </logic:greaterThan>
                   </td>
                   </tr>
                 </table>
            </td>
        </tr>


    </table>

  </td>
  </tr>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="center" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
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


</body>
</html:html>