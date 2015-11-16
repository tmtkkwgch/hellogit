<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>
<%@ page import="jp.groupsession.v2.enq.enq010.Enq010Const" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>">'></script>
<script type="text/javascript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq230.js?<%= GSConst.VERSION_PARAM %>"></script>

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
<html:form action="/enquete/enq230">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="editEnqSid" value="">
<input type="hidden" name="enqEditMode" value="">
<input type="hidden" name="enq210editMode" value="2">

<html:hidden property="cmd" />
<html:hidden property="enq230initFlg" />
<html:hidden property="enq230svType"/>
<html:hidden property="enq230svKeyword"/>
<html:hidden property="enq230svKeywordType"/>
<html:hidden property="enq230svAnony" />
<logic:notEmpty name="enq230Form" property="enq230svPriority">
<logic:iterate id="svPriority" name="enq230Form" property="enq230svPriority">
  <input type="hidden" name="enq230svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq230Form" property="enq230svStatus">
<logic:iterate id="svStatus" name="enq230Form" property="enq230svStatus">
  <input type="hidden" name="enq230svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>

<!-- 検索条件hidden -->
<html:hidden property="enq010folder" />
<html:hidden property="enq010subFolder" />
<html:hidden property="enq010initFlg" />
<html:hidden property="enq010searchDetailFlg" />
<html:hidden property="enq010type" />
<html:hidden property="enq010keyword" />
<html:hidden property="enq010keywordType" />
<html:hidden property="enq010keywordSimple" />
<html:hidden property="enq010sendGroup" />
<html:hidden property="enq010sendUser" />
<html:hidden property="enq010sendInput" />
<html:hidden property="enq010sendInputText" />
<html:hidden property="enq010makeDateKbn" />
<html:hidden property="enq010makeDateFromYear" />
<html:hidden property="enq010makeDateFromMonth" />
<html:hidden property="enq010makeDateFromDay" />
<html:hidden property="enq010makeDateToYear" />
<html:hidden property="enq010makeDateToMonth" />
<html:hidden property="enq010makeDateToDay" />
<html:hidden property="enq010resPubDateKbn" />
<html:hidden property="enq010resPubDateFromYear" />
<html:hidden property="enq010resPubDateFromMonth" />
<html:hidden property="enq010resPubDateFromDay" />
<html:hidden property="enq010resPubDateToYear" />
<html:hidden property="enq010resPubDateToMonth" />
<html:hidden property="enq010resPubDateToDay" />
<html:hidden property="enq010pubDateKbn" />
<html:hidden property="enq010pubDateFromYear" />
<html:hidden property="enq010pubDateFromMonth" />
<html:hidden property="enq010pubDateFromDay" />
<html:hidden property="enq010pubDateToYear" />
<html:hidden property="enq010pubDateToMonth" />
<html:hidden property="enq010pubDateToDay" />
<html:hidden property="enq010ansDateKbn" />
<html:hidden property="enq010ansDateFromYear" />
<html:hidden property="enq010ansDateFromMonth" />
<html:hidden property="enq010ansDateFromDay" />
<html:hidden property="enq010ansDateToYear" />
<html:hidden property="enq010ansDateToMonth" />
<html:hidden property="enq010ansDateToDay" />
<html:hidden property="enq010statusAnsOverSimple" />
<html:hidden property="enq010anony" />
<html:hidden property="enq010svType" />
<html:hidden property="enq010svKeyword" />
<html:hidden property="enq010svKeywordSimple" />
<html:hidden property="enq010svKeywordType" />
<html:hidden property="enq010svSendGroup" />
<html:hidden property="enq010svSendUser" />
<html:hidden property="enq010svSendInput" />
<html:hidden property="enq010svSendInputText" />
<html:hidden property="enq010svMakeDateKbn" />
<html:hidden property="enq010svMakeDateFromYear" />
<html:hidden property="enq010svMakeDateFromMonth" />
<html:hidden property="enq010svMakeDateFromDay" />
<html:hidden property="enq010svMakeDateToYear" />
<html:hidden property="enq010svMakeDateToMonth" />
<html:hidden property="enq010svMakeDateToDay" />
<html:hidden property="enq010svResPubDateKbn" />
<html:hidden property="enq010svResPubDateFromYear" />
<html:hidden property="enq010svResPubDateFromMonth" />
<html:hidden property="enq010svResPubDateFromDay" />
<html:hidden property="enq010svResPubDateToYear" />
<html:hidden property="enq010svResPubDateToMonth" />
<html:hidden property="enq010svResPubDateToDay" />
<html:hidden property="enq010svPubDateKbn" />
<html:hidden property="enq010svPubDateFromYear" />
<html:hidden property="enq010svPubDateFromMonth" />
<html:hidden property="enq010svPubDateFromDay" />
<html:hidden property="enq010svPubDateToYear" />
<html:hidden property="enq010svPubDateToMonth" />
<html:hidden property="enq010svPubDateToDay" />
<html:hidden property="enq010svAnsDateKbn" />
<html:hidden property="enq010svAnsDateFromYear" />
<html:hidden property="enq010svAnsDateFromMonth" />
<html:hidden property="enq010svAnsDateFromDay" />
<html:hidden property="enq010svAnsDateToYear" />
<html:hidden property="enq010svAnsDateToMonth" />
<html:hidden property="enq010svAnsDateToDay" />
<html:hidden property="enq010svAnony" />
<html:hidden property="enq010svStatusAnsOverSimple" />
<html:hidden property="enq010pageTop" />
<html:hidden property="enq010pageBottom" />
<html:hidden property="enq010sortKey" />
<html:hidden property="enq010order" />

<logic:notEmpty name="enq230Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq230Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq230Form" property="enq010status">
<logic:iterate id="svStatus" name="enq230Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq230Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq230Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq230Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq230Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq230Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq230Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq230Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq230Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<bean:define id="openFolder" name="enq230Form" property="enq010folder" type="java.lang.Integer" />
<bean:define id="openSubFolder" name="enq230Form" property="enq010subFolder" type="java.lang.Integer" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center" cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td width="0%"><img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" />"></td>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="cmn.shared.template" /> ]</td>
      <td width="100%" class="header_white_bg"></td>
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tbody>
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.header' />" ></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value='<gsmsg:write key="cmn.add" />' class="btn_add_n1" onclick="buttonPush('enq230add');">
          <input type="button" value='<gsmsg:write key="cmn.delete" />' class="btn_dell_n1" onclick="buttonPush('enq230delete');">
          <input type="button" value='<gsmsg:write key="cmn.back2" />' class="btn_back_n1" onclick="buttonPush('enq230back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
      </tr>
    </tbody>
    </table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

  </td>
  </tr>
  </table>
</td>
</tr>

<!-- BODY -->
<tr>
<td width="100%" align="center" class="wrap_table">

<table class="clear_table" width="100%">
  <tr>

    <td class="content_area">

      <!-- 検索条件 -->
      <table cellpadding="5" width="100%" class="tl0" id="enq010searchDetailArea">
      <tbody>

        <!-- 種類 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.type2" /></td>
          <td class="td_type20" nowrap>
          <span class="text_base2">
            <html:select property="enq230type" style="vertical-align:middle;">
              <logic:notEmpty name="enq230Form" property="enq230TypeList">
              <html:optionsCollection name="enq230Form" property="enq230TypeList" value="value" label="label" />
              </logic:notEmpty>
            </html:select>
          </span>
          </td>
        </tr>

        <!-- キーワード -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.keyword" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:text name="enq230Form" property="enq230keyword" maxlength="100" size="40" />
        <html:radio name="enq230Form" property="enq230keywordType" value="0" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
        <html:radio name="enq230Form" property="enq230keywordType" value="1" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
        </span>
        </td>
        </tr>

        <!-- 重要度 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.24" /></td>
          <td class="td_type20" nowrap>

            <html:multibox name="enq230Form" property="enq230priority" styleId="search_juuyou_01">
              <%= String.valueOf(GSConstEnquete.JUUYOU_0) %>
            </html:multibox>
            <label for="search_juuyou_01">
              <img src="../enquete/images/star_blue_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.33" />'>
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.33" />'>
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.33" />'>
            </label>

            <html:multibox name="enq230Form" property="enq230priority" styleId="search_juuyou_02">
              <%= String.valueOf(GSConstEnquete.JUUYOU_1) %>
            </html:multibox>
            <label for="search_juuyou_02">
              <img src="../enquete/images/star_gold_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.34" />'>
              <img src="../enquete/images/star_gold_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.34" />'>
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.34" />'>
            </label>

            <html:multibox name="enq230Form" property="enq230priority" styleId="search_juuyou_03">
              <%= String.valueOf(GSConstEnquete.JUUYOU_2) %>
            </html:multibox>
            <label for="search_juuyou_03">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.35" />'>
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.35" />'>
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt='<gsmsg:write key="enq.35" />'>
            </label>
          </td>
        </tr>

        <!-- 匿名 -->
        <tr>
        <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.anonymity" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
          <html:radio name="enq230Form" property="enq230anony" value="0" styleId="search_anony_00" /><label for="search_anony_00"><gsmsg:write key="cmn.all" /></label>
          <html:radio name="enq230Form" property="enq230anony" value="1" styleId="search_anony_01" /><label for="search_anony_01"><gsmsg:write key="enq.62" /></label>
          <html:radio name="enq230Form" property="enq230anony" value="2" styleId="search_anony_02" /><label for="search_anony_02"><gsmsg:write key="enq.63" /></label>
        </span>
        </td>
        </tr>

        <tr>
          <td colspan="2" style="text-align: center!important; padding-top: 10px; padding-bottom: 20px;"><input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onclick="buttonPush('enq230search');"></td>
        </tr>

      </tbody>
      </table>

      <!-- ページング -->
      <logic:notEmpty name="enq230Form" property="enq230pageList">
      <table width="100%" cellpadding="5" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td width="100%" align="right" valign="bottom" nowrap>
            <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('arrow_left');">
            <html:select name="enq230Form" property="enq230pageTop" styleClass="text_i" onchange="changePage(0);">
              <html:optionsCollection name="enq230Form" property="enq230pageList" value="value" label="label" />
            </html:select>
            <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('arrow_right');">
          </td>
        </tr>
      </tcody>
      </table>
      </logic:notEmpty>

      <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt='<gsmsg:write key="cmn.spacer"/>'>

      <!-- 一覧 -->
      <table class="tl0 table_td_border2" width="100%" cellpadding="5" cellspacing="0">
      <tbody>
        <tr>
          <th width="3%" class="detail_tbl" align="center" nowrap><input type="checkbox" name="enq230allCheck" value="1" onclick="chgCheckAll('enq230allCheck', 'enq230selectEnqSid');"></th>
          <th width="10%" class="detail_tbl" nowrap><span class="cel_enq_head"><gsmsg:write key="enq.24" /></span></th>
          <th width="60%" class="detail_tbl" nowrap><span class="cel_enq_head"><gsmsg:write key="cmn.title" /></span></th>
          <th width="15%" class="detail_tbl" nowrap><span class="cel_enq_head"><gsmsg:write key="cmn.anonymity" /></span></th>
          <th width="7%" class="detail_tbl" nowrap><span class="cel_enq_head">&nbsp;</span></th>
        </tr>

        <logic:notEmpty name="enq230Form" property="enq230EnqueteList">
        <logic:iterate id="enqData" name="enq230Form" property="enq230EnqueteList" indexId="idx">

        <bean:define id="mod" value="0"/>
        <logic:equal name="mod" value="<%= String.valueOf(idx % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod" value="<%= String.valueOf(idx % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:notEqual>
        <tr>
          <td class="td_type1 text_mod_10pt <bean:write name="tblColor" />" align="center" valign="middle" nowrap>
            <html:multibox name="enq230Form" property="enq230selectEnqSid">
              <bean:write name="enqData"  property="enqSid" />
            </html:multibox>
          </td>
          <td class='<bean:write name="tblColor" /> text_mod_10pt' align="center" valign="middle" nowrap>
           <bean:define id="enqPriority" name="enqData" property="priority" type="java.lang.Integer" />
           <% if (enqPriority.intValue() == GSConstEnquete.JUUYOU_0) { %>
             <img src="../enquete/images/star_blue_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.33" />'>
             <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.33" />'>
             <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.33" />'>
           <% } else if (enqPriority.intValue() == GSConstEnquete.JUUYOU_1) { %>
            <img src="../enquete/images/star_gold_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.34" />'>
            <img src="../enquete/images/star_gold_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.34" />'>
            <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.34" />'>
           <% } else if (enqPriority.intValue() == GSConstEnquete.JUUYOU_2) { %>
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.35" />'>
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.35" />'>
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt='<gsmsg:write key="enq.35" />'>
           <% } %>
          </td>
          <td class='<bean:write name="tblColor" /> text_mod_10pt' align="left" valign="middle">
            <logic:notEmpty name="enqData" property="typeName">
              <span class="label_style"><bean:write name="enqData" property="typeName" /></span>
            </logic:notEmpty>
            <span class="text_base2"><bean:write name="enqData" property="title" /></span>
          </td>
          <td class='<bean:write name="tblColor" /> text_mod_10pt' align="left" valign="middle" nowrap>
            <logic:equal name="enqData" property="annoy" value="<%= String.valueOf(GSConstEnquete.ANONYMUS_ON) %>">
              <span class="text_base2"><gsmsg:write key="enq.06" /></span>
            </logic:equal>
          </td>
          <td class='<bean:write name="tblColor" />' align="center" valign="middle">
            <input type="button" onclick="editEnquete(<bean:write name="enqData" property="enqSid" />);" class="btn_edit_sub" value="<gsmsg:write key="cmn.edit" />">
          </td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>

      </tbody>
      </table>

    </td>
    </tr>


  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt='<gsmsg:write key="cmn.spacer"/>'>
  </td>
  </tr>

  <tr>
  <td colspan="7" width="100%" align="right" valign="top" nowrap>
    <logic:notEmpty name="enq230Form" property="enq230pageList">
      <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('arrow_left');">
      <html:select name="enq230Form" property="enq230pageBottom" styleClass="text_i" onchange="changePage(1);">
        <html:optionsCollection name="enq230Form" property="enq230pageList" value="value" label="label" />
      </html:select>
      <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('arrow_right');">
    </logic:notEmpty>
  </td>
  </tr>

</td>
</tr>

<tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="15px" border="0" alt='<gsmsg:write key="cmn.spacer"/>'>
  </td>
</tr>
<tr>
  <td align="right">
    <input type="button" value='<gsmsg:write key="cmn.add" />' class="btn_add_n1" onclick="buttonPush('enq230add');">
    <input type="button" value='<gsmsg:write key="cmn.delete" />' class="btn_dell_n1" onclick="buttonPush('enq230delete');">
    <input type="button" value='<gsmsg:write key="cmn.back2" />' class="btn_back_n1" onclick="buttonPush('enq230back');">
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