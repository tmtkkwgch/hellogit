<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>
<%@ page import="jp.groupsession.v2.enq.enq320.Enq320Const" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../enquete/js/enq320.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>
<body class="body_03">
<html:form action="/enquete/enq320">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="enq110answer" value="">

<html:hidden property="ansEnqSid" />
<html:hidden property="enq310selectQue" />
<html:hidden property="enq310selectQueSub" />
<html:hidden property="enq320svGroup" />
<html:hidden property="enq320svUser" />
<html:hidden property="enq320svStsAns" />
<html:hidden property="enq320svStsNon" />
<html:hidden property="enq320sortKey" />
<html:hidden property="enq320order" />
<html:hidden property="enq320scrollQuestonFlg" />
<html:hidden property="enq320viewMode" />

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq320Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq320Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq320Form" property="enq010status">
<logic:iterate id="svStatus" name="enq320Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq320Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq320Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq320Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq320Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq320Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq320Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq320Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq320Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value='<bean:write name="enq320Form" property="enq310anony" />'>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center" colspan="2">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.50" /> ]</td>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tbody><tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.spacer' />"></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq320back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </tbody></table>
  </td>
  </tr>
  </table>
</td>
</tr>

<!-- BODY -->
<tr>
  <td width="99%">
    <table width="100%">
      <tr>
        <td width="1%"><img src="../enquete/images/enquete_title.gif" /></td>
        <td width="99%" class="text_bb4 enq_title" style="padding-left: 10px;"><bean:write name="enq320Form" property="enq310enqTitle" /></td>
      </tr>
    </table>
  </td>
</tr>

<tr>
<td width="100%" align="center" class="wrap_table" colspan="2">

<table class="clear_table" width="100%">
  <tr>

    <td class="content_area">

    <table cellpadding="5" width="100%" class="tl0">
    <tbody>

    <!-- 基本情報 重要度 -->
    <tr>
    <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="project.prj050.4" /></td>
    <td width="25%" class="td_type20" nowrap>
    <span class="text_base2">
    <span class="text_base2">
       <logic:equal name="enq320Form" property="enq310priority" value="0">
         <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
       </logic:equal>
       <logic:equal name="enq320Form" property="enq310priority" value="1">
         <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
         <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
         <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
       </logic:equal>
       <logic:equal name="enq320Form" property="enq310priority" value="2">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
         <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
       </logic:equal>
    </span>
    </span>
    </td>
    <!-- 基本情報 発信者 -->
    <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.25" /></td>
    <td colspan="3" width="52%" class="td_type20" nowrap>
      <bean:define id="sdFlg" name="enq320Form" property="enq310senderDelFlg" type="java.lang.Boolean" />
      <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
        <bean:write name="enq320Form" property="enq310sender" />
      </span>
    </td>
    </tr>

    <!-- 基本情報 アンケート内容 -->
    <tr>
    <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.18" /></td>
    <td colspan="3" class="td_type20">
    <span class="text_base2">
      <bean:write name="enq320Form" property="enq310enqContent" filter="false" />&nbsp;
    </span>
    </td>
    </tr>

    <tr>
    <!-- 基本情報 回答期限 -->
      <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
      <td class="td_type20" nowrap>
        <bean:write name="enq320Form" property="enq310ansLimitDate" />
      </td>
      <!-- 基本情報 結果公開期限 -->
      <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
      <td class="td_type20" nowrap>
      <bean:define id="ansOpen" name="enq320Form" property="enq310ansOpen" type="java.lang.Integer" />
      <% if (ansOpen == GSConstEnquete.EMN_ANS_OPEN_PUBLIC) {%>
        <bean:write name="enq320Form" property="enq310ansPubFrDate" />
        <logic:empty name="enq320Form" property="enq310pubLimitDate">
              ～&nbsp;<gsmsg:write key="main.man200.9" />
        </logic:empty>
        <logic:notEmpty name="enq320Form" property="enq310pubLimitDate">
        &nbsp;～
        <br>
        <bean:write name="enq320Form" property="enq310pubLimitDate" />
        </logic:notEmpty>
      <% } else { %>
        <gsmsg:write key="cmn.private" />
      <% } %>
      </td>
    </tr>
    <tr>
      <!-- 対象人数 -->
      <td align="center" valign="middle" class="td_gray text_header" nowrap><gsmsg:write key="cmn.number.of.candidates" /></td>
      <td align="left" valign="top" class="td_type20" nowrap>
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tbody>
          <tr>
            <td width="80px" class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="cmn.whole" /></td>
            <td width="100px" class="text_base2" align="left" valign="top" nowrap>
              <bean:write name="enq320Form" property="enq310answerCountAll" /><gsmsg:write key="cmn.persons" />
            </td>
          </tr>
          <tr>
            <td class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="enq.44" /></td>
            <td class="text_base2" align="left" valign="top" nowrap>
              <bean:write name="enq320Form" property="enq310answerCountAr" /><gsmsg:write key="cmn.persons" />　<span style="font-size: 80%">[<bean:write name="enq320Form" property="enq310answerCountArPer" />%]</span>
            </td>
          </tr>
          <tr>
            <td class="text_header2" align="left" valign="top" nowrap><gsmsg:write key="enq.45" /></td>
            <td class="text_base2" align="left" valign="top" nowrap>
              <bean:write name="enq320Form" property="enq310answerCountUn" /><gsmsg:write key="cmn.persons" />　<span style="font-size: 80%">[<bean:write name="enq320Form" property="enq310answerCountUnPer" />%]</span>
            </td>
          </tr>
        </tbody>
        </table>
      </td>
      <!-- 基本情報 注意事項 -->
      <td class="td_gray text_header" align="center" valign="middle" nowrap><gsmsg:write key="cmn.hints" /></td>
      <td class="td_type20" align="left" valign="middle">
        <span class="text_base2">
          <bean:define id="anony" name="enq320Form" property="enq310anony" type="java.lang.Integer" />
          <bean:define id="ansOpen" name="enq320Form" property="enq310ansOpen" type="java.lang.Integer" />
          <% if (anony == GSConstEnquete.ANONYMUS_ON && ansOpen == GSConstEnquete.KOUKAI_ON) { %>
            <gsmsg:write key="enq.69" />
          <% } else if (anony == GSConstEnquete.ANONYMUS_ON) { %>
            <gsmsg:write key="enq.31" />
          <% } else if (ansOpen == GSConstEnquete.KOUKAI_ON) { %>
            <gsmsg:write key="enq.32" />
          <% } %>
        </span>
      </td>
    </tr>

  </tbody>
  </table>

  <!-- 回答情報 -->
  <div class="text_info_title" style="margin-top: 20px;"><gsmsg:write key="enq.46" /></div>

  <!-- 検索条件 -->
  <table width="100%" cellpadding="0" cellspacing="0" class="">
  <tbody>
    <tr>
      <td width="100%" class="enq_search_area tbl_search">

        <table cellpadding="5" width="100%" class="tl0 ">
        <tbody>
          <logic:equal name="enq320Form" property="enq310anony" value="<%= String.valueOf(GSConstEnquete.EMN_ANONNY_NON) %>" >
          <tr>
            <td width="12%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.target" /></td>
            <td width="88%" align="left" class="td_type20" nowrap>
              <span style="font-size: 80%;"><gsmsg:write key="cmn.group" /></span>
              <html:select property="enq320group" onchange="buttonPush('init');">
                <html:optionsCollection name="enq320Form" property="groupCombo" value="value" label="label" />
              </html:select>
              &nbsp;&nbsp;
              <span style="font-size: 80%;"><gsmsg:write key="cmn.user" /></span>
              <html:select property="enq320user">
                <html:optionsCollection name="enq320Form" property="userCombo" value="value" label="label" />
             </html:select>
            </td>
          </tr>
          </logic:equal>
          <tr>
            <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.status" /></td>
            <td class="td_type20" nowrap>
                <span class="text_base2">
                  <html:checkbox name="enq320Form" property="enq320stsAns" value="1" styleId="answered" /><label for="answered"><gsmsg:write key="enq.22" /></label>
                  <html:checkbox name="enq320Form" property="enq320stsNon" value="1" styleId="unanswered" /><label for="unanswered"><gsmsg:write key="enq.21" /></label>
                </span>
              </td>
          </tr>
          <tr>
            <td colspan="2" align="center" width="100%" nowrap>
              <img src="../common/images/spacer.gif" width="1" height="25" border="0" alt="<gsmsg:write key='cmn.spacer' />">
              <input type="button" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onclick="buttonPush('enq320search');">
            </td>
          </tr>
        </tbody>
        </table>

      </td>
    </tr>
  </tbody>
  </table>

  <img src="../common/images/spacer.gif" width="1px" height="20px" border="0" alt="">

  <!-- 結果一覧 -->
  <table class="tl0 clear_table" width="100%" cellpadding="5" cellspacing="0" id="enq320ansListArea">
  <tbody>
    <tr>
      <td colspan="4" width="100%" align="right" class="td_type1" style="border: 0px;">
        <logic:notEmpty name="enq320Form" property="pageList">
        <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq320Form" property="enq320pageTop" styleClass="text_i" onchange="enq320changePage(0);">
            <html:optionsCollection name="enq320Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
        </logic:notEmpty>
      </td>
    </tr>

    <bean:define id="enqSortKey" name="enq320Form" property="enq320sortKey" type="java.lang.Integer" />
    <bean:define id="enqOrder" name="enq320Form" property="enq320order" type="java.lang.Integer" />
    <% String sortSign = ""; %>
    <% String nextOrder = ""; %>
    <% int titleSortKey = 0; %>
    <tr>
      <th width="15%" class="table_bg_7D91BD" nowrap>
        <% titleSortKey = Enq320Const.SORTKEY_JOUTAI; %>
        <% if (enqSortKey.intValue() == titleSortKey) { %>
        <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
        <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="cmn.status" /><%= sortSign %></a>
      </th>
      <th width="30%" class="table_bg_7D91BD" nowrap>
        <% titleSortKey = Enq320Const.SORTKEY_GROUP; %>
        <logic:equal name="enq320Form" property="enq310anony" value="<%= String.valueOf(GSConstEnquete.ANONYMUS_ON) %>" >
          <span class="text_tlw_10pt"><gsmsg:write key="cmn.group" />
        </logic:equal>
        <logic:notEqual name="enq320Form" property="enq310anony" value="<%= String.valueOf(GSConstEnquete.ANONYMUS_ON) %>" >
        <% if (enqSortKey.intValue() == titleSortKey) { %>
        <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
        <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="cmn.group" /><%= sortSign %></a>
        </logic:notEqual>
      </th>
      <th width="30%" class="table_bg_7D91BD" nowrap>
        <% titleSortKey = Enq320Const.SORTKEY_USER; %>
        <logic:equal name="enq320Form" property="enq310anony" value="<%= String.valueOf(GSConstEnquete.ANONYMUS_ON) %>" >
          <span class="text_tlw_10pt"><gsmsg:write key="cmn.user" /></span>
        </logic:equal>
        <logic:notEqual name="enq320Form" property="enq310anony" value="<%= String.valueOf(GSConstEnquete.ANONYMUS_ON) %>" >
        <% if (enqSortKey.intValue() == titleSortKey) { %>
        <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
        <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="cmn.user" /><%= sortSign %></a>
        </logic:notEqual>
      </th>
      <th width="25%" class="table_bg_7D91BD" nowrap>
        <% titleSortKey = Enq320Const.SORTKEY_ANSDATE; %>
        <% if (enqSortKey.intValue() == titleSortKey) { %>
        <%   if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; } %>
        <% } else { nextOrder = "0"; sortSign = ""; } %>
          <a href="#" onClick="enqClickTitle(<%= String.valueOf(titleSortKey) %>, <%= nextOrder %>);" class="text_tlw_10pt"><gsmsg:write key="enq.51" /><%= sortSign %></a>
      </th>
    </tr>

    <logic:notEmpty name="enq320Form" property="ansList">
    <logic:iterate id="ansData" name="enq320Form" property="ansList">
    <tr>
      <!-- 状態 -->
      <td class="td_type1" align="left" valign="middle">
        <logic:equal name="ansData" property="status" value="1"><gsmsg:write key="enq.22" /></logic:equal>
        <logic:equal name="ansData" property="status" value="0"><gsmsg:write key="enq.21" /></logic:equal>
      </td>
      <!-- グループ -->
      <td class="td_type1" align="left" valign="top">
        <bean:write name="ansData" property="group" />
      </td>
      <!-- ユーザー -->
      <td class="td_type1" align="left" valign="top">
        <bean:define id="ansDataAnony" name="ansData" property="anony" type="java.lang.Integer" />
        <bean:define id="ansDataStatus" name="ansData" property="status" type="java.lang.Integer" />

        <bean:define id="sdFlg" name="ansData" property="userDelFlg" type="java.lang.Boolean" />
        <% if (sdFlg) { %><span class="text_deluser_enq"><% } %>
        <% if (ansDataAnony.intValue() != GSConstEnquete.EMN_ANONNY_ANONNY && ansDataStatus.intValue() == 1) {%>
        <a href="#" onclick="enq320viewAnswer(<bean:write name="ansData" property="userSid" />);" class="text_link_enq"><bean:write name="ansData" property="user" /></a>
        <% } else { %>
        <bean:write name="ansData" property="user" />
        <% } %>
        <% if (sdFlg) { %></span><% } %>
      </td>
      <!-- 回答日 -->
      <td class="td_type1" align="left" valign="top">
        <logic:notEmpty name="ansData" property="ansDate">
        <bean:write name="ansData" property="ansDate" />
        </logic:notEmpty>
      </td>
    </tr>
    </logic:iterate>
    </logic:notEmpty>

    <logic:notEmpty name="enq320Form" property="pageList">
    <tr>
      <td colspan="4" width="100%" align="right" class="td_type1" style="padding-bottom: 15px; border: 0px;">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq320Form" property="enq320pageBottom" styleClass="text_i" onchange="enq320changePage(1);">
            <html:optionsCollection name="enq320Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
      </td>
    </tr>
    </logic:notEmpty>

    </tbody></table>


  </tr>
  <tr>
    <td width="50%" align="right" style="padding-top:10px;">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq320back');">
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