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
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../enquete/js/enq970.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<style type="text/css">
.textfield { position: relative; margin: 0 0 10px 0;}
.textfield label { position: absolute; top: 4; left: 3;}
.textfield br {display: none;}
</style>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>

<body class="body_03">
<html:form action="/enquete/enq970">

<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />
<input type="hidden" name="enq970BackPage" value="1">

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<html:hidden property="enq970initFlg" />
<html:hidden property="enq970searchDetailFlg" />
<html:hidden property="enq970sortKey" />
<html:hidden property="enq970order" />
<html:hidden property="enq970svType" />
<html:hidden property="enq970svKeyword" />
<html:hidden property="enq970svKeywordType" />
<html:hidden property="enq970svSendGroup" />
<html:hidden property="enq970svSendUser" />
<html:hidden property="enq970svSendInput" />
<html:hidden property="enq970svSendInputText" />
<html:hidden property="enq970svMakeDateKbn" />
<html:hidden property="enq970svMakeDateFromYear" />
<html:hidden property="enq970svMakeDateFromMonth" />
<html:hidden property="enq970svMakeDateFromDay" />
<html:hidden property="enq970svMakeDateToYear" />
<html:hidden property="enq970svMakeDateToMonth" />
<html:hidden property="enq970svMakeDateToDay" />
<html:hidden property="enq970svPubDateKbn" />
<html:hidden property="enq970svPubDateFromYear" />
<html:hidden property="enq970svPubDateFromMonth" />
<html:hidden property="enq970svPubDateFromDay" />
<html:hidden property="enq970svPubDateToYear" />
<html:hidden property="enq970svPubDateToMonth" />
<html:hidden property="enq970svPubDateToDay" />
<html:hidden property="enq970svAnsDateKbn" />
<html:hidden property="enq970svAnsDateFromYear" />
<html:hidden property="enq970svAnsDateFromMonth" />
<html:hidden property="enq970svAnsDateFromDay" />
<html:hidden property="enq970svAnsDateToYear" />
<html:hidden property="enq970svAnsDateToMonth" />
<html:hidden property="enq970svAnsDateToDay" />
<html:hidden property="enq970svAnony" />

<logic:notEmpty name="enq970Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq970Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq970Form" property="enq010status">
<logic:iterate id="svStatus" name="enq970Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq970Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq970Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq970Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq970Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq970Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq970Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq970Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq970Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq970Form" property="enq970svPriority">
<logic:iterate id="svPriority" name="enq970Form" property="enq970svPriority">
  <input type="hidden" name="enq970svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq970Form" property="enq970svPriority">
<logic:iterate id="svStatus" name="enq970Form" property="enq970svStatus">
  <input type="hidden" name="enq970svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="cmd" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="0">


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="75%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq970.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.function.btn" />"></td>
        <td class="header_glay_bg" width="50%">
          <logic:notEmpty name="enq970Form" property="enq010EnqueteList">
            <input type="button" name="" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('enq970delEnquete');">
          </logic:notEmpty>
         <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onClick="enq970changeSearch();">
          <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('enq970back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.function.btn" />"></td>
      </tr>
    </table>

</td>
</tr>

<tr>
<td align="center" class="wrap_table" style="margin-top: 15px;">

  <table class="clear_table" width="100%">
  <tr>
    <td>

      <logic:messagesPresent message="false">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr><td width="100%" style="margin-bottom: 10px;"><html:errors /></td></tr>
      </table>
      </logic:messagesPresent>

      <!-- 検索条件 -->
      <table cellpadding="5" width="99%" class="tl0" id="enq970searchDetailArea" style="margin-top: 15px;">
      <tbody>

        <!-- 種類 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.type2" /></td>
          <td class="td_type20" nowrap>
          <span class="text_base2">
            <html:select property="enq970type" style="vertical-align:middle;">
              <logic:notEmpty name="enq970Form" property="enqTypeList">
              <html:optionsCollection name="enq970Form" property="enqTypeList" value="value" label="label" />
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
        <html:text name="enq970Form" property="enq970keyword" maxlength="100" size="40" />
        <html:radio name="enq970Form" property="enq970keywordType" value="0" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
        <html:radio name="enq970Form" property="enq970keywordType" value="1" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
        </span>
        </td>
        </tr>

        <!-- 発信者 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cir.2" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <span class="text_bb2"><gsmsg:write key="cmn.group" /></span>
        <html:select property="enq970sendGroup" onchange="buttonPush('init');">
          <logic:notEmpty name="enq970Form" property="enqSendGroupList">
          <html:optionsCollection name="enq970Form" property="enqSendGroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <span class="text_bb2"><gsmsg:write key="cmn.user" /></span>
        <html:select property="enq970sendUser">
          <logic:notEmpty name="enq970Form" property="enqSendUserList">
          <html:optionsCollection name="enq970Form" property="enqSendUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
         &nbsp;&nbsp;&nbsp;&nbsp;
        </span>
        <div class="textfield" style="margin-top: 12px;">
          <label for="field_id"><gsmsg:write key="cmn.search.item2"/></label>
          <html:text name="enq970Form" property="enq970sendInputText" maxlength="20" styleClass="text_base" styleId="field_id" style="width:273px;" />
        </div>
        </td>
        </tr>

        <!-- 作成日 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="man.creation.date" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:radio name="enq970Form" property="enq970makeDateKbn" value="0" styleId="enq970makeDateFromKbn0" onclick="enq970chkSrhDate(1);" /><label for="enq970makeDateFromKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq970Form" property="enq970makeDateKbn" value="1" styleId="enq970makeDateFromKbn1" onclick="enq970chkSrhDate(1);" /><label for="enq970makeDateFromKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq970makeDateArea">
        <html:select property="enq970makeDateFromYear" styleId="enq970makeDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970makeDateFromMonth" styleId="enq970makeDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970makeDateFromDay" styleId="enq970makeDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="makeDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq970makeDateFromDay, this.form.enq970makeDateFromMonth, this.form.enq970makeDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq970makeDateToYear" styleId="enq970makeDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970makeDateToMonth" styleId="enq970makeDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970makeDateToDay" styleId="enq970makeDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="makeDateToCalendarBtn" onclick="wrtCalendar(this.form.enq970makeDateToDay, this.form.enq970makeDateToMonth, this.form.enq970makeDateToYear);" class="calendar_btn">

        </span>
        </td>
        </tr>

<%--         <!-- 公開期間 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.open.period" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:radio name="enq970Form" property="enq970pubDateKbn" value="0" styleId="enq970pubDateKbn0" onclick="enq970chkSrhDate(2);" /><label for="enq970pubDateKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq970Form" property="enq970pubDateKbn" value="1" styleId="enq970pubDateKbn1" onclick="enq970chkSrhDate(2);" /><label for="enq970pubDateKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq970pubDateArea">
        <html:select property="enq970pubDateFromYear" styleId="enq970pubDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970pubDateFromMonth" styleId="enq970pubDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970pubDateFromDay" styleId="enq970pubDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="pubDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq970pubDateFromDay, this.form.enq970pubDateFromMonth, this.form.enq970pubDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq970pubDateToYear" styleId="enq970pubDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970pubDateToMonth" styleId="enq970pubDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970pubDateToDay" styleId="enq970pubDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="pubDateToCalendarBtn" onclick="wrtCalendar(this.form.enq970pubDateToDay, this.form.enq970pubDateToMonth, this.form.enq970pubDateToYear);" class="calendar_btn">
        </span>
        </td>
        </tr>
 --%>
        <!-- 回答期限 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
        <td class="td_type20" nowrap>

        <span class="text_base2">
        <html:radio name="enq970Form" property="enq970ansDateKbn" value="0" styleId="enq970ansDateKbn0" onclick="enq970chkSrhDate(3);" /><label for="enq970ansDateKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq970Form" property="enq970ansDateKbn" value="1" styleId="enq970ansDateKbn1" onclick="enq970chkSrhDate(3);" /><label for="enq970ansDateKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq970ansDateArea">
        <html:select property="enq970ansDateFromYear" styleId="enq970ansDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970ansDateFromMonth" styleId="enq970ansDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970ansDateFromDay" styleId="enq970ansDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="ansDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq970ansDateFromDay, this.form.enq970ansDateFromMonth, this.form.enq970ansDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq970ansDateToYear" styleId="enq970ansDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970ansDateToMonth" styleId="enq970ansDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970ansDateToDay" styleId="enq970ansDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="ansDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq970ansDateToDay, this.form.enq970ansDateToMonth, this.form.enq970ansDateToYear);" class="calendar_btn">
        </span>
        </td>
        </tr>
        <!-- 結果公開期間 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:radio name="enq970Form" property="enq970resPubDateKbn" value="0" styleId="enq970resPubDateKbn0" onclick="enq970chkSrhDate(4);" /><label for="enq970resPubDateKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq970Form" property="enq970resPubDateKbn" value="1" styleId="enq970resPubDateKbn1" onclick="enq970chkSrhDate(4);" /><label for="enq970resPubDateKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq970resPubDateArea">
        <html:select property="enq970resPubDateFromYear" styleId="enq970resPubDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970resPubDateFromMonth" styleId="enq970resPubDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970resPubDateFromDay" styleId="enq970resPubDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="resPubDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq970resPubDateFromDay, this.form.enq970resPubDateFromMonth, this.form.enq970resPubDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq970resPubDateToYear" styleId="enq970resPubDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970resPubDateToMonth" styleId="enq970resPubDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq970resPubDateToDay" styleId="enq970resPubDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq970Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="resPubDateToCalendarBtn" onclick="wrtCalendar(this.form.enq970resPubDateToDay, this.form.enq970resPubDateToMonth, this.form.enq970resPubDateToYear);" class="calendar_btn">
        </span>
        </td>
        </tr>

        <!-- 重要度 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="project.prj050.4" /></td>
          <td class="td_type20" nowrap>

            <html:multibox name="enq970Form" property="enq970priority" styleId="search_juuyou_01">
              <%= String.valueOf(GSConstEnquete.JUUYOU_0) %>
            </html:multibox>
            <label for="search_juuyou_01">
              <img src="../enquete/images/star_blue_16.png" class="star3" border="0" alt="<gsmsg:write key="project.58" />">
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt="<gsmsg:write key="project.58" />">
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt="<gsmsg:write key="project.58" />">
            </label>

            <html:multibox name="enq970Form" property="enq970priority" styleId="search_juuyou_02">
              <%= String.valueOf(GSConstEnquete.JUUYOU_1) %>
            </html:multibox>
            <label for="search_juuyou_02">
              <img src="../enquete/images/star_gold_16.png" class="star3" border="0" alt="<gsmsg:write key="project.59" />">
              <img src="../enquete/images/star_gold_16.png" class="star3" border="0" alt="<gsmsg:write key="project.59" />">
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt="<gsmsg:write key="project.59" />">
            </label>

            <html:multibox name="enq970Form" property="enq970priority" styleId="search_juuyou_03">
              <%= String.valueOf(GSConstEnquete.JUUYOU_2) %>
            </html:multibox>
            <label for="search_juuyou_03">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt="<gsmsg:write key="project.60" />">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt="<gsmsg:write key="project.60" />">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt="<gsmsg:write key="project.60" />">
            </label>
          </td>
        </tr>

        <!-- 状態 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="anp.state" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
            <html:multibox name="enq970Form" property="enq970status" styleId="search_joutai_send_01">
              <%= String.valueOf(Enq010Const.STATUS_NOTPUB) %>
            </html:multibox><label for="search_joutai_send_01"><gsmsg:write key="enq.15" /></label>
            <html:multibox name="enq970Form" property="enq970status" styleId="search_joutai_send_02">
              <%= String.valueOf(Enq010Const.STATUS_PUB) %>
            </html:multibox><label for="search_joutai_send_02"><gsmsg:write key="enq.77" /></label>
            <html:multibox name="enq970Form" property="enq970status" styleId="search_joutai_send_03">
              <%= String.valueOf(Enq010Const.STATUS_ANSEXIT) %>
            </html:multibox><label for="search_joutai_send_03"><gsmsg:write key="enq.16" /></label>
            <html:multibox name="enq970Form" property="enq970status" styleId="search_joutai_send_04">
              <%= String.valueOf(Enq010Const.STATUS_PUBEXIT) %>
            </html:multibox><label for="search_joutai_send_04"><gsmsg:write key="enq.17" /></label>
        </span>
        </td>
        </tr>

        <tr>
          <td colspan="2" style="text-align: center!important; padding-top: 10px; padding-bottom: 20px;"><input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onclick="buttonPush('enq970Search');"></td>
        </tr>

      </tbody>
      </table>

    <bean:define id="enqSortKey" name="enq970Form" property="enq970sortKey" type="java.lang.Integer" />
    <bean:define id="enqOrder" name="enq970Form" property="enq970order" type="java.lang.Integer" />
    <% String sortSign = ""; %>
    <% String nextOrder = ""; %>
    <% int titleSortKey = Enq010Const.SORTKEY_OPEN; %>

      <table width="100%" style="margin-top: 20px;">
      <tr>
        <td align="right">
        <logic:notEmpty name="enq970Form" property="pageList">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq970Form" property="enq970pageTop" styleClass="text_i" onchange="enq970changePage('0');">
            <html:optionsCollection name="enq970Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
        </logic:notEmpty>
        </td>
      </tr>
      </table>

      <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="">

      <table class="tl0" width="100%" cellpadding="3" cellspacing="0">
      <tbody>
        <tr>
          <th width="0%" class="detail_tbl" nowrap><input type="checkbox" name="enq970allCheck" value="1" onclick="chgCheckAll('enq970allCheck', 'enq970selectEnqSid');"></th>
          <th width="14%" class="detail_tbl" nowrap><span class="cel_enq_head"><gsmsg:write key="cmn.status" /></span></th>

          <%
            jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
            String[] minWidthPxList = {"48", "75", "90", "75", "75", "105"};
            String[] widthList = {"0", "13", "30", "13", "13", "17"};
            String[] titleList = {gsMsg.getMessage(request, "enq.24"), "&nbsp;" + gsMsg.getMessage(request, "enq.25"), gsMsg.getMessage(request, "cmn.title"), gsMsg.getMessage(request, "enq.53"), "&nbsp;" + gsMsg.getMessage(request, "enq.19"), gsMsg.getMessage(request, "enq.enq210.11")};
            int[] sortKeyList = {Enq010Const.SORTKEY_PRIORITY,  Enq010Const.SORTKEY_SENDER,  Enq010Const.SORTKEY_TITLE,  Enq010Const.SORTKEY_OPEN,  Enq010Const.SORTKEY_ANSLIMIT,  Enq010Const.SORTKEY_ANS_OPEN};
            for (int titleIdx = 0; titleIdx < titleList.length; titleIdx++) {
              if (enqSortKey.intValue() == sortKeyList[titleIdx]) {
                if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; }
              } else { nextOrder = "0"; sortSign = ""; }
          %>
          <th width="<%= widthList[titleIdx] %>%" class="detail_tbl">
          <a href="#" onClick="enq970ClickTitle(<%= String.valueOf(sortKeyList[titleIdx]) %>, <%= nextOrder %>);" class="cel_enq_head"><%= titleList[titleIdx] %><%= sortSign %></a>
          <% if (titleIdx == 1 && enqSortKey.intValue() == Enq010Const.SORTKEY_MAKEDATE) { if (enqOrder.intValue() == 1) { sortSign="▼"; nextOrder = "0"; } else { sortSign="▲"; nextOrder = "1"; }%><br><a href="#" onClick="enq970ClickTitle(<%= String.valueOf(Enq010Const.SORTKEY_MAKEDATE) %>, <%= nextOrder %>);" class="cel_enq_head"><gsmsg:write key="man.creation.date" /><%= sortSign %></a>
          <% } else if (titleIdx == 1) {  { nextOrder = "0"; sortSign = ""; } %><br><a href="#" onClick="enq970ClickTitle(<%= String.valueOf(Enq010Const.SORTKEY_MAKEDATE) %>, <%= nextOrder %>);" class="cel_enq_head"><gsmsg:write key="man.creation.date" /><%= sortSign %></a>
          <% } else if (titleIdx == 4) { %><br><span class="cel_enq_head"><gsmsg:write key="cmn.number.of.candidates" /></span><% } %>
          <p class="min_width_img"><img src="../common/images/spacer.gif" width="<%=minWidthPxList[titleIdx] %>" height="1px" border="0" alt="" ></p>
          </th>
          <% } %>

        </tr>
       <logic:notEmpty name="enq970Form" property="enq010EnqueteList">
        <logic:iterate id="enqData" name="enq970Form" property="enq010EnqueteList" indexId="idx">
        <tr>
          <td class="td_type1" align="center" valign="middle" nowrap="">
            <html:multibox name="enq970Form" property="enq970selectEnqSid">
              <bean:write name="enqData"  property="enqSid" />
            </html:multibox>
          </td>
          <td class="td_type1" align="center" valign="middle" >
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_YES) %>">
              <span class="text_base2"><gsmsg:write key="enq.77" /></span>
            </logic:equal>
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_END) %>">
              <span class="text_base2"><gsmsg:write key="enq.17" /></span>
            </logic:equal>
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_NO) %>">
              <span class="text_base2"><gsmsg:write key="enq.15" /></span>
            </logic:equal>
            <logic:equal name="enqData" property="publicKbn" value="<%= String.valueOf(Enq010Const.PUBLIC_ANSED) %>">
              <span class="text_base2"><gsmsg:write key="enq.16" /></span>
            </logic:equal>
          </td>
          <td class="td_type1" align="center" valign="middle" nowrap>
           <bean:define id="enqPriority" name="enqData" property="priority" type="java.lang.Integer" />
           <% if (enqPriority.intValue() == GSConstEnquete.JUUYOU_0) { %>
             <img src="../enquete/images/star_blue_16.png" class="star4" border="0" alt="<gsmsg:write key="project.58" />">
             <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt="<gsmsg:write key="project.58" />">
             <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt="<gsmsg:write key="project.58" />">
           <% } else if (enqPriority.intValue() == GSConstEnquete.JUUYOU_1) { %>
            <img src="../enquete/images/star_gold_16.png" class="star4" border="0" alt="<gsmsg:write key="project.59" />">
            <img src="../enquete/images/star_gold_16.png" class="star4" border="0" alt="<gsmsg:write key="project.59" />">
            <img src="../enquete/images/star_white_16.png" class="star4" border="0" alt="<gsmsg:write key="project.59" />">
           <% } else if (enqPriority.intValue() == GSConstEnquete.JUUYOU_2) { %>
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt="<gsmsg:write key="project.60" />">
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt="<gsmsg:write key="project.60" />">
            <img src="../enquete/images/star_red_16.png" class="star4" border="0" alt="<gsmsg:write key="project.60" />">
           <% } %>
          </td>
          <td class="td_type1" align="left" valign="middle" >
            <bean:define id="sdFlg" name="enqData" property="senderDelFlg" type="java.lang.Boolean" />
            <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
              <bean:write name="enqData" property="sender" />
            </span>
            <br>
            <span class="text_base2">
            <bean:write name="enqData" property="makeDate" />
            </span>
          </td>
          <td class="td_type1" align="left" valign="middle">
            <logic:notEmpty name="enqData" property="typeName">
              <span class="label_style"><bean:write name="enqData" property="typeName" /></span>
            </logic:notEmpty>
            <a href="#" onClick="enq970viewDetail(<bean:write name="enqData"  property="enqSid" />);" class="text_link_enq"><bean:write name="enqData" property="title" /></a>
          </td>
          <td class="td_type1" align="center" valign="middle">
          <span class="text_base2">
          <bean:write name="enqData" property="pubStartDateStr" filter='false'/>
          </span>
          </td>
          <td class="td_type1" align="center" valign="middle" >
            <span class="text_base2">
            <bean:write name="enqData" property="ansLimitDate" />
            <logic:equal name="enqData" property="enqPublic" value="<%= String.valueOf(Enq010Const.PUBLIC_YES) %>">
              <bean:define id="publicKbn" name="enqData" property="publicKbn" type="java.lang.Integer" />
              <br><bean:write name="enqData" property="subjects" /><gsmsg:write key="cmn.persons" />
            </logic:equal>
            </span>
          </td>
            <td class="td_type1" align="center" valign="top" >
              <span class="text_base2">
              <logic:equal name="enqData" property="ansOpen" value="<%= String.valueOf(GSConstEnquete.KOUKAI_ON) %>">
              <bean:write name="enqData" property="ansPubStartDate" />
              <logic:empty name="enqData" property="pubEndDateStr">
              &nbsp;～<br>
              <gsmsg:write key="main.man200.9" />
              </logic:empty>
              <logic:notEmpty name="enqData" property="pubEndDateStr">
              &nbsp;～<br>
              <bean:write name="enqData" property="pubEndDateStr" />
              </logic:notEmpty>
              </logic:equal>
              <logic:notEqual name="enqData" property="ansOpen" value="<%= String.valueOf(GSConstEnquete.KOUKAI_ON) %>">
              <gsmsg:write key="cmn.private" />
              </logic:notEqual>
              </span>
            </td>
        </tr>

        </logic:iterate>
        </logic:notEmpty>
        </tbody>
        </table>

      <img src="../common/images/spacer.gif" width="1px" height="5px" border="0" alt="">
      <table width="100%">
      <tr>
        <td align="left">&nbsp;</td>
        <td align="right">
        <logic:notEmpty name="enq970Form" property="pageList">
          <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onclick="buttonPush('prevPage');">
          <html:select name="enq970Form" property="enq970pageBottom" styleClass="text_i" onchange="enq970changePage('1');">
            <html:optionsCollection name="enq970Form" property="pageList" value="value" label="label" />
          </html:select>
          <img src="../common/images/arrow2_r.gif" name="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onclick="buttonPush('nextPage');">
        </logic:notEmpty>
        </td>
      </tr>
      </table>

    </td>
  </tr>

  </table>

</td>
</tr>
</table>

</td>
</tr>
</table>

<span id="tooltip_area"></span>

</html:form>

<div id="smlPop" title="" style="display:none">
  <div id="smlCreateArea" width="100%" height="100%"></div>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>