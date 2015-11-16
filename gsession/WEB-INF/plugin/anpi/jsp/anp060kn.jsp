<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<!-- haisinflg 0:配信 2:再配信 4:参照 -->
<% Integer haisinflg = 0; %>
<% Integer senderCnt = 0; %>

<logic:equal name="anp060knForm" property="anp060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MSG_HAISIN_MODE_MISAISOU) %>">
    <% haisinflg = 2; %>
</logic:equal>
<logic:equal name="anp060knForm" property="anp060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MSG_HAISIN_MODE_ZENSAISOU) %>">
    <% haisinflg = 2; %>
</logic:equal>
<logic:equal name="anp060knForm" property="anp060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MSG_HAISIN_MODE_INFOCONF) %>">
    <% haisinflg = 4; %>
</logic:equal>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="anp.anp060.01"/></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../anpi/css/anpi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../anpi/js/anp060kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03" onload="setfocus();" onunload="windowClose();">
<html:form action="/anpi/anp060kn">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden property="anpiSid" />
<html:hidden property="anp010SelectGroupSid" />
<html:hidden property="anp010NowPage" />
<html:hidden property="anp010SortKeyIndex" />
<html:hidden property="anp010OrderKey" />
<html:hidden property="anp010KnrenFlg" />

<html:hidden property="anp060ProcMode" />
<html:hidden property="anp060main" />
<html:hidden property="anp060Subject" />
<html:hidden property="anp060Text1" />
<html:hidden property="anp060Text2" />
<html:hidden property="anp130SelectAphSid" />

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

<html:hidden property="anp060knNowPage" />
<html:hidden property="anp060knDspPage1" />
<html:hidden property="anp060knDspPage2" />
<html:hidden property="anp060knScrollFlg" />


<logic:notEmpty name="anp060knForm" property="anp060SenderList" scope="request">
    <logic:iterate id="ulBean" name="anp060knForm" property="anp060SenderList" scope="request">
    <input type="hidden" name="anp060SenderList" value="<bean:write name="ulBean" />">
    <% senderCnt++; %>
    </logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<%= haisinflg %>" />

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
        <td width="0%"><img src="../anpi/images/header_anpi_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_white_bg_text"><gsmsg:write key="anp.plugin"/> [ <gsmsg:write key="anp.anp060kn.01"/> ]</td>
        <td width="100%" class="header_white_bg"></td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="安否確認"></td>
        </tr>
    </table>

    <!-- 配信ボタン類 -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <% if (haisinflg == 0) { %>
                <input type="button" value="配  信" class="btn_haisin" onClick="buttonPush('anp060knhaisin');">
            <% } %>
            <% if (haisinflg == 2 && senderCnt > 0) { %>
                <input type="button" value="再配信" class="btn_resend" onClick="buttonPush('anp060knhaisin');">
            <% } %>
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp060knback');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <!-- メッセージ -->
    <div style="text-align:left;">
        <% if (haisinflg == 2 && senderCnt == 0) { %>
            <span class="text_r1"><gsmsg:write key="anp.anp060kn.02"/></span>
        <% } %>

        <bean:define id="settingCnt" name="anp060knForm" property="anp060knSetConCount" />
        <% if (((Integer) settingCnt).intValue() < senderCnt) { %>
           <span class="text_r1"><gsmsg:write key="anp.anp060kn.03"/></span>
        <% } %>
    </div>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:equal name="anp060knForm" property="anp010KnrenFlg" value="1">
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
    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <% if (haisinflg == 2) { %>
            <tr>
                <!-- オプション -->
                <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                    <span class="text_bb1"><gsmsg:write key="anp.anp060kn.04"/><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></span>
                </td>
                <td width="80%" valign="middle" align="left" class="td_wt table_pad" >
                   <span class="text_base">
                   <html:radio styleId="reflg1" name="anp060knForm" property="anp060knProcMode" onclick="buttonPush('anp060knreload');" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MSG_HAISIN_MODE_MISAISOU) %>" />
                        <label for="reflg1"><gsmsg:write key="anp.anp060kn.05"/></label>&nbsp;
                   <html:radio styleId="reflg2" name="anp060knForm" property="anp060knProcMode" onclick="buttonPush('anp060knreload');" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MSG_HAISIN_MODE_ZENSAISOU) %>" />
                        <label for="reflg2"><gsmsg:write key="anp.anp060kn.06"/></label>
                   <br>&nbsp;<gsmsg:write key="anp.anp060kn.07"/>
                   </span>
                </td>
            </tr>
        <%} %>

        <tr>
            <!-- 訓練モード -->
            <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.knmode"/></span>
            </td>
            <td width="80%" valign="middle" align="left" class="td_wt table_pad" >
                <span class="text_base">
                <logic:equal name="anp060knForm" property="anp010KnrenFlg" value="1">
                    <gsmsg:write key="anp.knmode"/>
                </logic:equal>
                <logic:notEqual name="anp060knForm" property="anp010KnrenFlg" value="1">
                    <gsmsg:write key="anp.hmode"/>
                </logic:notEqual>
                </span>
            </td>
        </tr>

        <!-- メイン表示 -->
        <tr>
          <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="cmn.main.view" /></span>
          </td>
          <td width="80%" valign="middle" align="left" class="td_wt table_pad" >
          <span class="text_base">
            <logic:equal name="anp060knForm" property="anp060main" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.APH_VIEW_MAIN_SENDTO) %>" >
              <gsmsg:write key="anp.anp060.11" />
            </logic:equal>

            <logic:notEqual name="anp060knForm" property="anp060main" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.APH_VIEW_MAIN_SENDTO) %>" >
              <gsmsg:write key="cmn.alluser" />
            </logic:notEqual>
          </span>
          </td>
        </tr>

        <tr>
            <!-- 件名 -->
            <td width="20%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
                <span class="text_bb1"><gsmsg:write key="cmn.subject"/></span>
            </td>
            <td width="80%" valign="middle" align="left" class="td_wt table_pad" >
                <span class="text_base">
                <bean:write name="anp060knForm" property="anp060knDispSubject" /></span>
            </td>
        </tr>

        <tr>
            <!-- 本文 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="cmn.body"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" width="100%">
                <span class="text_base">
                <bean:write name="anp060knForm" property="anp060knDispMessageBody" filter="false" /></span>
            </td>
        </tr>

        <logic:notEqual name="anp060knForm" property="anp060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.anp.GSConstAnpi.MSG_HAISIN_MODE_INFOCONF) %>">
        <tr>
            <!-- 送信先 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
                <span class="text_bb1"><gsmsg:write key="anp.send.dest"/>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" width="100%">
                <logic:notEmpty name="anp060knForm" property="anp060knSenderList">

                  <a id="label_sender" name="label_sender"></a>
                  <table width="150px">
                      <tr>
                      <td align="left" valign="bottom" >
                      <!-- ページコンボ -->
                      <bean:size id="pageCount" name="anp060knForm" property="anp060knPageLabel" scope="request" />
                      <logic:greaterThan name="pageCount" value="1">
                      <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp060knpageLast');">
                      <html:select property="anp060knDspPage1" onchange="changePage(this);" styleClass="text_i">
                        <html:optionsCollection name="anp060knForm" property="anp060knPageLabel" value="value" label="label"/>
                      </html:select>
                      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp060knpageNext');">
                      </logic:greaterThan>
                      </td>
                      </tr>
                  </table>
                  <table cellpadding="0" cellspacing="0" border="0" class="tl0"  width="98%">

                  <logic:iterate id="urBean" name="anp060knForm" property="anp060knSenderList" indexId="idx">
                     <tr>
                     <td width="0%" nowrap><span class="text_base">
                     <logic:equal name="urBean" property="grpFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.anp060kn.Anp060knForm.GROUP_SELECT_YES) %>">
                        <a href="javascript:void(0)" onClick="openGrpUsrMailWindow(<bean:write name="urBean" property="sid" />);"><bean:write name="urBean" property="name" /></a>
                     </logic:equal>
                     <logic:notEqual name="urBean" property="grpFlg" value="<%= String.valueOf(jp.groupsession.v2.anp.anp060kn.Anp060knForm.GROUP_SELECT_YES) %>">
                        <bean:write name="urBean" property="name" />
                     </logic:notEqual>
                     &nbsp;&nbsp;</span></td>
                     <td width="0%"><logic:notEqual name="urBean" property="mailadr" value="<%= String.valueOf(jp.groupsession.v2.anp.anp060kn.Anp060knForm.MAIL_SET_YES) %>">
                            <img src="../anpi/images/nomail.gif" border="0" alt="!"></logic:notEqual>
                     </td>
                     <% if (idx == 0 && ((Integer) settingCnt).intValue() < senderCnt) { %>
                        <td width="100%" rowspan="<%= senderCnt %>" align="right" valign="bottom">
                            <span class="text_base_mini"><img src="../anpi/images/nomail.gif" border="0" alt="!" style="vertical-align : middle;">：<gsmsg:write key="anp.anp060kn.08"/></span>
                        </td>
                     <% } %>
                     </tr>
                  </logic:iterate>
                  </table>


                  <table width="150px">
                      <tr>
                      <td align="left" valign="bottom" >
                      <!-- ページコンボ -->
                      <bean:size id="pageCount" name="anp060knForm" property="anp060knPageLabel" scope="request" />
                      <logic:greaterThan name="pageCount" value="1">
                      <img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp060knpageLast');">
                      <html:select property="anp060knDspPage2" onchange="changePage(this);" styleClass="text_i">
                        <html:optionsCollection name="anp060knForm" property="anp060knPageLabel" value="value" label="label"/>
                      </html:select>
                      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="text_i" width="20" height="20" border="0" onClick="buttonPush('anp060knpageNext');">
                      </logic:greaterThan>
                      </td>
                      </tr>
                  </table>
                </logic:notEmpty>
            </td>
        </tr>

        <tr>
            <!-- 配信者 -->
            <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
            <span class="text_bb1"><gsmsg:write key="anp.sender"/></span>
            </td>
            <td valign="middle" align="left" class="td_wt table_pad" >
            <span class="text_base"><bean:write name="anp060knForm" property="anp060RegistName" /></span>
            </td>
        </tr>
        </logic:notEqual>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- 配信ボタン類 -->
    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <% if (haisinflg == 0) { %>
                <input type="button" value="配  信" class="btn_haisin" onClick="buttonPush('anp060knhaisin');">
            <% } %>
            <% if (haisinflg == 2 && senderCnt > 0) { %>
                <input type="button" value="再配信" class="btn_resend" onClick="buttonPush('anp060knhaisin');">
            <% } %>
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp060knback');">
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