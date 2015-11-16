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
<%@ page import="jp.groupsession.v2.enq.enq210.Enq210Form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src='../smail/js/smljquery.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq210.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript" src="../portal/js/tiny_mce/tiny_mce.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enqEditor.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<% boolean editFlg = false; %>
<logic:equal name="enq210Form" property="enqEditMode" value="<%= String.valueOf(GSConstEnquete.EDITMODE_EDIT) %>">
<% editFlg = true; %>
</logic:equal>

<% if (editFlg) { %>
<title>[GroupSession] <gsmsg:write key="enq.plugin" /> <gsmsg:write key="enq.enq210.02" /></title>
<% } else { %>
<title>[GroupSession] <gsmsg:write key="enq.plugin" /> <gsmsg:write key="enq.enq210.01" /></title>
<% } %>

</head>
<body class="body_03" onload="changeAttached(<bean:write name="enq210Form" property="enq210AttachKbn" />);changeSeqType();">
<html:form action="/enquete/enq210">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="cmd" value="">
<input type="hidden" name="SEQ" value="">
<input type="hidden" name="enq210DescText" value="">
<input type="hidden" name="enq210templateId" value="">
<input type="hidden" name="enq210editQueIndex" value="">
<input type="hidden" name="enq220initFlg" value="">
<input type="hidden" name="enq220editMode" value="">
<input type="hidden" name="enq110DspMode" value="1">

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>
<html:hidden property="enq210editMode" />
<html:hidden property="enq210scrollQuestonFlg" />
<html:hidden property="enq210queType" />
<html:hidden property="enq210AttachKbn" />
<html:hidden property="tempClickBtn" />

<!-- テンプレート画面の検索条件 -->
<html:hidden property="enq230initFlg" />
<html:hidden property="enq230type" />
<html:hidden property="enq230keyword" />
<html:hidden property="enq230keywordType" />
<html:hidden property="enq230anony" />
<html:hidden property="enq230pageTop" />
<html:hidden property="enq230pageBottom" />
<html:hidden property="enq230svType"/>
<html:hidden property="enq230svKeyword"/>
<html:hidden property="enq230svKeywordType"/>
<html:hidden property="enq230svAnony" />
<logic:notEmpty name="enq210Form" property="enq230selectEnqSid">
  <logic:iterate id="sv230SelectEnqSid" name="enq210Form" property="enq230selectEnqSid">
    <input type="hidden" name="enq230selectEnqSid" value="<bean:write name='sv230SelectEnqSid' />" >
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq230priority">
  <logic:iterate id="sv230Priority" name="enq210Form" property="enq230priority">
    <input type="hidden" name="enq230priority" value="<bean:write name='sv230Priority' />">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq230svPriority">
<logic:iterate id="svPriority" name="enq210Form" property="enq230svPriority">
  <input type="hidden" name="enq230svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq230svStatus">
<logic:iterate id="svStatus" name="enq210Form" property="enq230svStatus">
  <input type="hidden" name="enq230svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq210Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq210Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq010status">
<logic:iterate id="svStatus" name="enq210Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq210Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq210Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq210Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq210Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq210Form" property="enq210answerGroup">
<logic:iterate id="answerUser" name="enq210Form" property="enq210answerList">
  <input type="hidden" name="enq210answerList" value="<bean:write name="answerUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="enq210Seq" />
<html:hidden property="enq210initFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<bean:define id="enq210editMode" name="enq210Form" property="enq210editMode" type="java.lang.Integer" />
<% int editMode = enq210editMode.intValue(); %>
<% if (editMode == Enq210Form.EDITMODE_NORMAL) { %>
  <% if (!editFlg) { %>
    <input type="hidden" name="helpPrm" value="0">
  <% } else { %>
    <input type="hidden" name="helpPrm" value="1">
  <% } %>
<% } else if (editMode == Enq210Form.EDITMODE_TEMPLATE) { %>
  <% if (!editFlg) { %>
    <input type="hidden" name="helpPrm" value="2">
  <% } else { %>
    <input type="hidden" name="helpPrm" value="3">
  <% } %>
<% } else { %>
  <input type="hidden" name="helpPrm" value="4">
<% } %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="80%">
<tr>
<td width="100%" align="center">

  <% boolean enqTemplateFlg = enq210editMode.intValue() == Enq210Form.EDITMODE_TEMPLATE; %>
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" /> "></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /> </span></td>
    <% if (editFlg) { %>
      <% if (enqTemplateFlg) { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210.10" /> ]</td>
      <% } else { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210.02" /> ]</td>
      <% } %>
    <% } else { %>
      <% if (enqTemplateFlg) { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210.09" /> ]</td>
      <% } else { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210.01" /> ]</td>
      <% } %>
    <% } %>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt='<gsmsg:write key="cmn.header" />'></td>
    </tr>
  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tbody><tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=<gsmsg:write key="cmn.header" />></td>
    <td class="header_glay_bg" width="50%">
      <bean:define id="enq210folder" name="enq210Form" property="enq010folder" type="java.lang.Integer" />
      <% if (editFlg) { %>
        <input type="button" value="<gsmsg:write key="cmn.register.copy.new"/>" class="btn_base1_3" onClick="buttonPush('enq210copyNew');">
      <% } %>
      <%
        if ((enq210editMode.intValue() == Enq210Form.EDITMODE_NORMAL && !editFlg)
        || enq210editMode.intValue() == Enq210Form.EDITMODE_DRAFT) {
      %>
      <input type="button" value="<gsmsg:write key="cmn.save.draft" />" class="btn_base1" onclick="enq210Draft();">
      <% } %>
      <input type="button" value="<gsmsg:write key="ptl.6" />" class="btn_preview_n1" onclick="buttonPush('enq210preview');">
      <% if (enqTemplateFlg) { %>
      <input type="button" class="btn_ok1" value="OK" onclick="enq210Entry();">
      <% } %>
      <% if (!enqTemplateFlg) { %>
      <input type="button" value="<gsmsg:write key="enq.05" />" class="btn_add_n1" onclick="enq210Entry();">
      <% } %>
      <% if (editFlg) { %>
        <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onclick="buttonPush('enq210delete');">
      <% } %>
      <% if (!enqTemplateFlg) { %>
      <input type="button" value="<gsmsg:write key="cmn.template" />" class="btn_base1" onclick="enq210DspTemplate();">
      <% } %>
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq210back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=<gsmsg:write key="cmn.header" />></td>
  </tr>
  </tbody></table>
</td>
</tr>

<!-- BODY -->
<tr>
<td width="100%" align="center" class="wrap_table">

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%" style="margin-bottom: 10px;"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

<logic:equal name="enq210Form" property="enq210Alert" value="1">
  <% if ((editFlg && !enqTemplateFlg)
          && (editFlg && (enq210editMode.intValue() != Enq210Form.EDITMODE_DRAFT))
          && (editFlg && (enq210editMode.intValue() != Enq210Form.EDITMODE_TEMPLATE))) { %>
  <tr><td class="text_error"><gsmsg:write key="cmn.warning"/>:<gsmsg:write key="enq.66" /></td></tr>
  <% } %>
  </logic:equal>

  <tr>
    <td class="content_area">

      <table cellpadding="5" width="100%" class="tl0">
      <tbody>

        <!-- 基本情報 重要度 -->
        <tr>
          <td colspan="2" width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="project.prj050.4" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td colspan="3" width="87%" class="td_type20" align="left" valign="bottom">
            <table width="400px" cellpadding="0" cellspacing="0" border="0">
              <tr>
                <td width="170px" align="left" valign="middle" class="text_base2" nowrap>
                  <html:radio name="enq210Form" property="enq210Juuyou" value="0" styleId="search_juuyou_0" onclick="changeJuuyou2(0);" />
                  <label for="search_juuyou_0"><gsmsg:write key="project.58" /></label>
                  &nbsp;
                  <html:radio name="enq210Form" property="enq210Juuyou" value="1" styleId="search_juuyou_1" onclick="changeJuuyou2(1);" />
                  <label for="search_juuyou_1"><gsmsg:write key="project.59" /></label>
                  &nbsp;
                  <html:radio name="enq210Form" property="enq210Juuyou" value="2" styleId="search_juuyou_2" onclick="changeJuuyou2(2);" />
                  <label for="search_juuyou_2"><gsmsg:write key="project.60" /></label>
                  &nbsp;
                </td>
                <td width="200px" align="left" valign="middle" nowrap>
                  <div id="star_1" class="star" >
                    <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                    <label for="search_juuyou_1"><img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />"></label>
                    <label for="search_juuyou_2"><img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />"></label>
                  </div>
                  <div id="star_2" class="star" style="display:none">
                    <label for="search_juuyou_0"><img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />"></label>
                    <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                    <label for="search_juuyou_2"><img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />"></label>
                  </div>
                  <div id="star_3" class="star" style="display:none">
                    <label for="search_juuyou_0"><img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />"></label>
                    <label for="search_juuyou_1"><img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />"></label>
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                  </div>
                </td>
              </tr>
            </table>
            </span>
          </td>
        </tr>

        <tr>
          <!-- 基本情報 種類 -->
          <% String strColVal = "1"; %>
          <% String strWidth = "27%"; %>
          <% if (enqTemplateFlg) { strColVal = "3"; strWidth="87%"; } %>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.type2" /></td>
          <td colspan="<%= strColVal %>" width="<%= strWidth %>" class="td_type20">
            <span class="text_base2">
            <html:select property="enq210Syurui" style="vertical-align:middle;">
              <logic:notEmpty name="enq210Form" property="enqTypeList">
              <html:optionsCollection name="enq210Form" property="enqTypeList" value="value" label="label" />
              </logic:notEmpty>
            </html:select>
            </span>
          </td>
          <!-- 基本情報 発信者 -->
          <% if (!enqTemplateFlg) { %>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cir.2" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td class="td_type20">
            <span class="text_base2">
            <html:select property="enq210Send" style="vertical-align:middle;">
              <logic:notEmpty name="enq210Form" property="enqSenderList">
              <html:optionsCollection name="enq210Form" property="enqSenderList" value="value" label="label" />
              </logic:notEmpty>
            </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
          </td>
          <% } %>
        </tr>

        <!-- 基本情報 タイトル -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.title" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <html:text name="enq210Form" property="enq210Title" maxlength="100" style="width:421px;" />
            </span>
          </td>
        </tr>

        <!-- 基本情報 説明文 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header td_1" nowrap><gsmsg:write key="cmn.explanation" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <input type="hidden" name="enqEditorSize" value="270">
              <table width="99%">
              <tr>
                <td>
                  <span class="text_base2">
                    <div class="setsumon" style="display:blank;">
                      <html:textarea name="enq210Form" property="enq210Desc" rows="5" cols="40" styleClass="text_base" styleId="enqDescArea" />
                    </div>
                  </span>
                </td>
              </tr>
            </table>
            </span>
          </td>
        </tr>

        <tr>
          <td width="4%" class="td_gray td_1"></td>
          <td width="9%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.attached" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
            <bean:write name="enq210Form" property="enq210fileName" /> &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="enq210opnTemp('<bean:write name="enq210Form" property="enq210fileDir" />');">
            &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('enq210delTemp');">
            </span>
          </td>
        </tr>

        <tr id="enq210attachPosition">
          <td class="td_gray td_1"></td>
          <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.position" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <html:radio name="enq210Form" property="enq210AttachPos" value="0" styleId="iti_0" /><label for="iti_0"><gsmsg:write key="cmn.up2" /></label>
              <html:radio name="enq210Form" property="enq210AttachPos" value="1" styleId="iti_1" /><label for="iti_1"><gsmsg:write key="cmn.down2" /></label>
            </span>
          </td>
        </tr>

        <tr id="enq210attachUrl">
          <td class="td_gray td_1"></td>
          <td align="center" class="td_gray text_header" nowrap>URL</td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <html:text name="enq210Form" property="enq210Url" size="80" maxlength="100" />
            </span>
          </td>
        </tr>

        <% if (!enqTemplateFlg) { %>
        <!-- 基本情報 公開開始日 -->
        <tr>
          <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.53" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td colspan="3" class="td_type20">

            <html:select property="enq210FrYear" styleId="enq210FrYear" style="vertical-align:middle;" onchange="enq210checkSmail();">
            <html:optionsCollection name="enq210Form" property="enq210YearLabel" value="value" label="label" />
            </html:select>&nbsp;
            <html:select property="enq210FrMonth" styleId="enq210FrMonth" style="vertical-align:middle;" onchange="enq210checkSmail();">
            <html:optionsCollection name="enq210Form" property="enq210MonthLabel" value="value" label="label" />
            </html:select>&nbsp;
            <html:select property="enq210FrDay" styleId="enq210FrDay" style="vertical-align:middle;" onchange="enq210checkSmail();">
            <html:optionsCollection name="enq210Form" property="enq210DayLabel" value="value" label="label" />
            </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="Cal" name="fromCalendarBtn" onclick="enq210WrtCalendar(this.form.enq210FrDay, this.form.enq210FrMonth, this.form.enq210FrYear);" class="calendar_btn">

            <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return enq210moveDay($('#enq210FrYear')[0], $('#enq210FrMonth')[0], $('#enq210FrDay')[0], 1)">
            <input type="button" class="btn_today" value="<gsmsg:write key='cmn.today' />" onClick="return enq210moveDay($('#enq210FrYear')[0], $('#enq210FrMonth')[0], $('#enq210FrDay')[0], 2)">
            <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return enq210moveDay($('#enq210FrYear')[0], $('#enq210FrMonth')[0], $('#enq210FrDay')[0], 3)">
          </td>
        </tr>

          <!-- 基本情報 回答期限 -->
        <tr>
          <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td colspan="3" class="td_type20">
          <html:select property="enq210AnsYear" styleId="enq210AnsYear" style="vertical-align:middle;" onchange="enq210checkSmail();">
          <html:optionsCollection name="enq210Form" property="enq210YearLabel" value="value" label="label" />
          </html:select>&nbsp;
          <html:select property="enq210AnsMonth" styleId="enq210AnsMonth" style="vertical-align:middle;" onchange="enq210checkSmail();">
          <html:optionsCollection name="enq210Form" property="enq210MonthLabel" value="value" label="label" />
          </html:select>&nbsp;
          <html:select property="enq210AnsDay" styleId="enq210AnsDay" style="vertical-align:middle;" onchange="enq210checkSmail();">
          <html:optionsCollection name="enq210Form" property="enq210DayLabel" value="value" label="label" />
          </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="Cal" name="fromCalendarBtn" onclick="wrtCalendar(this.form.enq210AnsDay, this.form.enq210AnsMonth, this.form.enq210AnsYear);" class="calendar_btn">

          <input type="button" class="btn_arrow_l" value="&nbsp;" onclick="return enq210moveDay($('#enq210AnsYear')[0], $('#enq210AnsMonth')[0], $('#enq210AnsDay')[0], 1)">
          <input type="button" class="btn_today" value="<gsmsg:write key='cmn.today' />" onClick="return enq210moveDay($('#enq210AnsYear')[0], $('#enq210AnsMonth')[0], $('#enq210AnsDay')[0], 2)">
          <input type="button" class="btn_arrow_r" value="&nbsp;" onclick="return enq210moveDay($('#enq210AnsYear')[0], $('#enq210AnsMonth')[0], $('#enq210AnsDay')[0], 3)">
          </td>
        </tr>
        <%} %>

        <!-- 基本情報 結果公開 -->
        <tr>
        <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.07" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
        <td colspan="3" class="td_type20">
        <div class="text_base2" style="margin-bottom:20px">
        <html:checkbox name="enq210Form" property="enq210AnsOpen" value="1" styleId="koukai" onclick="return checkEnq210AnsOpen();"/><label for="koukai"><gsmsg:write key="cmn.publish" /></label>
        <div style="margin-top: 5px;">&nbsp;<gsmsg:write key="cmn.asterisk"/><gsmsg:write key="enq.enq210.14"/></div>
        </div>
        <% if (!enqTemplateFlg) { %>
          <gsmsg:write key="cmn.start" />:&nbsp;
          <html:select property="enq210AnsPubFrYear" styleId="enq210AnsPubFrYear" style="vertical-align:middle;">
          <html:optionsCollection name="enq210Form" property="enq210YearLabel" value="value" label="label" />
          </html:select>&nbsp;
          <html:select property="enq210AnsPubFrMonth" styleId="enq210AnsPubFrMonth" style="vertical-align:middle;">
          <html:optionsCollection name="enq210Form" property="enq210MonthLabel" value="value" label="label" />
          </html:select>&nbsp;
          <html:select property="enq210AnsPubFrDay" styleId="enq210AnsPubFrDay" style="vertical-align:middle;">
          <html:optionsCollection name="enq210Form" property="enq210DayLabel" value="value" label="label" />
          </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="Cal" name="enq210AnsPubFrCal" onclick="wrtCalendar(this.form.enq210AnsPubFrDay, this.form.enq210AnsPubFrMonth, this.form.enq210AnsPubFrYear);" class="calendar_btn">

          <input type="button" class="btn_arrow_l" name="enq210AnsPubFrForward" value="&nbsp;" onclick="return moveDay($('#enq210AnsPubFrYear')[0], $('#enq210AnsPubFrMonth')[0], $('#enq210AnsPubFrDay')[0], 1)">
          <input type="button" class="btn_today" name="enq210AnsPubFrToday" value="<gsmsg:write key='cmn.today' />" onClick="return moveDay($('#enq210AnsPubFrYear')[0], $('#enq210AnsPubFrMonth')[0], $('#enq210AnsPubFrDay')[0], 2)">
          <input type="button" class="btn_arrow_r" name="enq210AnsPubFrNext" value="&nbsp;" onclick="return moveDay($('#enq210AnsPubFrYear')[0], $('#enq210AnsPubFrMonth')[0], $('#enq210AnsPubFrDay')[0], 3)">

          <br>

          <div style="margin-top:5px">
          <gsmsg:write key="main.src.man250.30" />:&nbsp;
          <html:select property="enq210ToYear" styleId="enq210ToYear" style="vertical-align:middle;">
          <html:optionsCollection name="enq210Form" property="enq210YearLabel" value="value" label="label" />
          </html:select>&nbsp;
          <html:select property="enq210ToMonth" styleId="enq210ToMonth" style="vertical-align:middle;">
          <html:optionsCollection name="enq210Form" property="enq210MonthLabel" value="value" label="label" />
          </html:select>&nbsp;
          <html:select property="enq210ToDay" styleId="enq210ToDay" style="vertical-align:middle;">
          <html:optionsCollection name="enq210Form" property="enq210DayLabel" value="value" label="label" />
          </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" name="enq210ToDateCal" value="Cal" onclick="wrtCalendar(this.form.enq210ToDay, this.form.enq210ToMonth, this.form.enq210ToYear);" class="calendar_btn">

          <input type="button" name="enq210ToDateForward" class="btn_arrow_l" value="&nbsp;" onclick="return moveDay($('#enq210ToYear')[0], $('#enq210ToMonth')[0], $('#enq210ToDay')[0], 1)">
          <input type="button" name="enq210ToDateToday" class="btn_today" value="<gsmsg:write key='cmn.today' />" onClick="return moveDay($('#enq210ToYear')[0], $('#enq210ToMonth')[0], $('#enq210ToDay')[0], 2)">
          <input type="button" name="enq210ToDateNext" class="btn_arrow_r" value="&nbsp;" onclick="return moveDay($('#enq210ToYear')[0], $('#enq210ToMonth')[0], $('#enq210ToDay')[0], 3)">
            &nbsp;&nbsp;&nbsp;<html:checkbox name="enq210Form" property="enq210ToKbn" value="1" styleId="enq210ToKbn" onclick="return enq210ToDateKbn();" /><label for="enq210ToKbn"><gsmsg:write key="enq.enq210.18" /></label>
          </div>

        <% } %>
        </td>
        </tr>
        <% if (!enqTemplateFlg) { %>
        <logic:equal name="enq210Form" property="enq210pluginSmailUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
        <!-- ショートメール通知 -->
        <tr>
        <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="shortmail.notification" /></td>
        <td colspan="3" class="td_type20">
        <span class="text_base2">
           <html:checkbox name="enq210Form" property="enq210smailInfo" value="<%= String.valueOf(Enq210Form.SML_INFO_SEND) %>" styleId="smailInfo" /><label for="smailInfo"><gsmsg:write key="cmn.notify" /></label>
           <div style="margin-top: 5px;">&nbsp;<gsmsg:write key="cmn.asterisk"/><gsmsg:write key="enq.enq210.16"/></div>
        </span>
        </td>
        </tr>
        </logic:equal>
        <% } %>

        <!-- 匿名 -->
        <tr>
        <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.anonymity" /></td>
        <td colspan="3" class="td_type20">
        <span class="text_base2">
           <html:checkbox name="enq210Form" property="enq210Anony" value="1" styleId="anony" /><label for="anony"><gsmsg:write key="enq.06" /></label>
           <div style="margin-top: 5px;">&nbsp;<gsmsg:write key="cmn.asterisk"/><gsmsg:write key="enq.enq210.15"/></div>
        </span>
        </td>
        </tr>

        <!-- 基本情報 対象者 -->
        <tr>
        <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.10" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
        <td colspan="3" class="td_type20" align="left" valign="middle">

          <table width="99%" border="0">
          <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="enq.10" /></span></td>
          <td width="10%" align="center">&nbsp;</td>
          <td width="40%" align="left" nowrap>
            <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.enq210answerGroup, 'enq210answerGroup', '<bean:write name="enq210Form" property="enq210answerGroup" />', '1', 'init', 'enq210answerList', '-1', '1')" type="button">
            <br>
            <html:select name="enq210Form" property="enq210answerGroup" styleClass="select01" onchange="buttonPush('init');" style="width: 170px;">
              <html:optionsCollection name="enq210Form" property="selectAnswerGroup" value="value" label="label" />
            </html:select>
            <input type="button" onclick="openGroupWindow(this.form.enq210answerGroup, 'enq210answerGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="enq210answerGroupBtn">
            <input type="checkbox" name="enq210selectAnswersKbn" value="1" id="select_answer" onclick="return enq210selectAnswersList();" />
            <label for="select_answer" class="text_base2"><gsmsg:write key="cmn.select" /></label></span>
          </td>
          </tr>

          <tr>
          <td align="center">
            <html:select name="enq210Form" property="enq210selectAnswerList" size="11" styleClass="select01" multiple="true" style="width: 300px;">
            <logic:notEmpty name="enq210Form" property="selectAnswerCombo">
              <html:optionsCollection name="enq210Form" property="selectAnswerCombo" value="value" label="label" />
            </logic:notEmpty>
            <option>&nbsp;</option>
            </html:select>
          </td>
          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add" />" name="adduserBtn" onClick="buttonPush('enq210addAnswer');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('enq210delAnswer');">
          </td>
          <td>
            <html:select name="enq210Form" property="enq210NoSelectAnswerList" size="10" styleClass="select01" multiple="true" style="width: 300px;">
            <logic:notEmpty name="enq210Form" property="noSelectAnswerCombo">
              <html:optionsCollection name="enq210Form" property="noSelectAnswerCombo" value="value" label="label" />
            </logic:notEmpty>
            <option value="-1">&nbsp;</option>
            </html:select>
          </td>
          </tr>
          </table>
        </td>
        </tr>

      </tbody>
      </table>


      <img src="../common/images/spacer.gif" width="1" height="15" border="0" alt=<gsmsg:write key="cmn.spacer" />>


      <!-- 設問情報 タイトル -->
      <div class="text_info_title"><gsmsg:write key="enq.04" /></div>

      <table>
        <tr>
          <td width="15%" style="padding-top: 10px; vertical-align:top;">
            <!-- 追加ボタン -->
            <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
              <tbody>
                <th width="8%" class="detail_tbl"><span class="cel_enq_head" nowrap><gsmsg:write key="enq.67" /></span></th>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(1);"><gsmsg:write key="enq.enq210.03" /></a></td></tr>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(2);"><gsmsg:write key="enq.enq210.04" /></a></td></tr>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(3);"><gsmsg:write key="enq.enq210.05" /></a></td></tr>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(4);"><gsmsg:write key="enq.enq210.05" /><br><gsmsg:write key="enq.68" /></a></td></tr>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(5);"><gsmsg:write key="enq.enq210.07" /></a></td></tr>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(6);"><gsmsg:write key="enq.enq210.08" /></a></td></tr>
                <tr><td class="td_type1 text_base2" align="left" valign="middle" nowrap><a href="javascript:void(0)" class="text_link_enq" onclick="addQuestion(0);"><gsmsg:write key="cmn.comment" /></a></td></tr>
              </tbody>
            </table>
          </td>

          <td width="15">&nbsp;</td>

          <td style="vertical-align: top; padding-top: 10px;" width="84%">
            <!-- 設問情報 -->
            <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
            <tbody>
              <tr>
                <td align="left" width="140px" style="white-space:nowrap;">
                <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onclick="sortQuestion('enq210upQuestion');">
                <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="sortQuestion('enq210downQuestion');">
                </td>
                <td aligin="left" class="text_base2">
                <span style="font-weight:bold;"><gsmsg:write key="enq.09" /></span>
                <span class="text_base2">
                <html:radio name="enq210Form" property="enq210queSeqType" value="1" styleId="jidou" onclick="changeSeqType();" /><label for="jidou"><gsmsg:write key="cmn.auto" /></label>
                <html:radio name="enq210Form" property="enq210queSeqType" value="0" styleId="syudou" onclick="changeSeqType();" /><label for="syudou"><gsmsg:write key="cmn.manual" /></label>
                </span>
                </td>
              </tr>
            </tbody>
            </table>

            <table class="tl0" width="100%" cellpadding="5" cellspacing="0" id="enq210question">
            <thead>
              <tr>
                <th width="5%" class="table_bg_7D91BD"><span class="text_tlw_10pt"></span></th>
                <th width="15%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.09" /></span></th>
                <th width="70%" class="table_bg_7D91BD" nowrap><span class="text_tlw_10pt"><gsmsg:write key="enq.12" /></span></th>
                <th width="10%" class="table_bg_7D91BD" colspan="2">&nbsp;</th>
              </tr>
            </thead>

            <tbody id="enqTbl">
              <logic:notEmpty name="enq210Form" property="ebaList">
              <input type="hidden" name="ebaListSize" value="<bean:write name="enq210Form" property="ebaListSize" />">
              <logic:iterate id="ebaData" name="enq210Form" property="ebaList" indexId="lineIdx">
              <bean:define id="intQueIndex" name="ebaData" property="enq210queIndex" type="java.lang.Integer" />
              <% String queIndex = String.valueOf(intQueIndex.intValue()); %>
              <% String radioNo = "radioNo_" + queIndex; %>
              <tr>
              <td class="td_type1 text_mod_10pt" align="center" valign="middle">
                <table width="100%" cellpaddin="0" cellspacing="0" border="0">
                  <tr>
                    <td align="center" valign="middle">
                      <html:radio name="enq210Form" property="enq210queIndex" styleId="<%= radioNo %>" value="<%= queIndex %>" />
                    </td>
                  </tr>
                </table>
              </td>
              <td class="td_type1 text_mod_10pt" align="left" valign="top">
                <span id="enq210qnoMan_<%= queIndex %>"><bean:write name="ebaData" property="enq210QueNo" /></span>
                <span id="enq210qnoAuto_<%= queIndex %>">
                <logic:greaterThan name="ebaData" property="enq210AutoQueNo" value="0">
                <bean:write name="ebaData" property="enq210AutoQueNo" />
                </logic:greaterThan>
                </span>
                <logic:equal name="ebaData" property="enq210Require" value="1">
                  <br><span class="text_base2"><gsmsg:write key="cmn.required" /></span>
                </logic:equal>
              </td>
              <td class="td_type1 text_mod_10pt" align="left" valign="top">
                <span class="text_base2">
                  <gsmsg:write key="cmn.type2" />：<bean:write name="ebaData" property="enq210SyuruiName" /><br>
                  <logic:notEmpty name="ebaData" property="enq210Question">
                  <gsmsg:write key="enq.12" />：<bean:write name="ebaData" property="enq210Question" /><br>
                  </logic:notEmpty>
                  <logic:notEmpty name="ebaData" property="enq210QueDesc">
                  <gsmsg:write key="ptl.8" />：<bean:write name="ebaData" property="enq210QueDesc" filter="false" /><br>
                  </logic:notEmpty>
                  <logic:equal name="ebaData" property="enq210QueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_SINGLE) %>" >
                  <gsmsg:write key="enq.enq210.12"/>：
                  <logic:notEmpty name="ebaData" property="queSubList">
                  <logic:iterate id="queChoice" name="ebaData" property="queSubList">
                  [<bean:write name="queChoice" property="enqDspName" />]
                  </logic:iterate>
                  </logic:notEmpty>
                  <br>
                  </logic:equal>
                  <logic:equal name="ebaData" property="enq210QueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_MULTIPLE) %>" >
                  <gsmsg:write key="enq.enq210.12"/>：
                  <logic:notEmpty name="ebaData" property="queSubList">
                  <logic:iterate id="queChoice" name="ebaData" property="queSubList">
                  [<bean:write name="queChoice" property="enqDspName" />]
                  </logic:iterate>
                  </logic:notEmpty>
                  <br>
                  </logic:equal>
                  <logic:notEmpty name="ebaData" property="enq210AttachName">
                  <gsmsg:write key="cmn.attached" />：<bean:write name="ebaData" property="enq210AttachName" /><br>
                  </logic:notEmpty>
                  <logic:notEmpty name="ebaData" property="enq210initTxt">
                  <gsmsg:write key="ntp.10" />：
                  <logic:equal name="ebaData" property="enq210QueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXTAREA) %>">
                  <br><bean:write name="ebaData" property="enq210viewInitTxt" filter="false"/>
                  </logic:equal>
                  <logic:notEqual name="ebaData" property="enq210QueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXTAREA) %>">
                    <bean:write name="ebaData" property="enq210initTxt" />
                  </logic:notEqual>
                  <br>
                  </logic:notEmpty>
                  <logic:notEmpty name="ebaData" property="enq210rangeTxtFr">
                  <logic:notEmpty name="ebaData" property="enq210rangeTxtTo">
                  <gsmsg:write key="cmn.input.range" />：
                  <bean:write name="ebaData" property="enq210rangeTxtFr" />～
                  <bean:write name="ebaData" property="enq210rangeTxtTo" /><br>
                  </logic:notEmpty>
                  </logic:notEmpty>
                  <logic:notEmpty name="ebaData" property="enq210unitNum">
                  <gsmsg:write key="ntp.102" />：<bean:write name="ebaData" property="enq210unitNum" /><br>
                  </logic:notEmpty>
                  <logic:equal name="ebaData" property="enq210QueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_DAY) %>" >
                  <logic:notEmpty name="ebaData" property="enq210initDspDate">
                  <gsmsg:write key="ntp.10" />：<bean:write name="ebaData" property="enq210initDspDate" /><br>
                  </logic:notEmpty>
                  <logic:notEmpty name="ebaData" property="enq210rangeTxtFrDsp">
                  <logic:notEmpty name="ebaData" property="enq210rangeTxtToDsp">
                  <gsmsg:write key="cmn.input.range" />：
                  <bean:write name="ebaData" property="enq210rangeTxtFrDsp" />～
                  <bean:write name="ebaData" property="enq210rangeTxtToDsp" /><br>
                  </logic:notEmpty>
                  </logic:notEmpty>
                  </logic:equal>
                </span>
              </td>
              <td class="td_type1 text_mod_10pt" align="center" valign="middle">
                <input type="button" class="btn_edit_n3" value="<gsmsg:write key="cmn.edit" />" name="btn_change" onClick="editQuestion(<%= queIndex %>);"></td>
              <td class="td_type1 text_mod_10pt" align="center" valign="middle">
                <input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" name="btn_delete" onClick="deleteQuestion(<%= queIndex %>);"></td>
              </tr>
              </logic:iterate>
              </logic:notEmpty>
            </tbody>
          </table>

        </td>
      </tr>
    </table>

    </td>
    </tr>

    <tr>
    <td>
    <img src="../common/images/spacer.gif" width="1px" height="30px" border="0" alt=<gsmsg:write key="cmn.spacer" />>
    </td>
    </tr>

    <tr>
    <td width="50%" align="right">
      <% if (editFlg) { %>
        <input type="button" value="<gsmsg:write key="cmn.register.copy.new"/>" class="btn_base1_3" onClick="buttonPush('enq210copyNew');">
      <% } %>
      <%
        if ((enq210editMode.intValue() == Enq210Form.EDITMODE_NORMAL && !editFlg)
        || enq210editMode.intValue() == Enq210Form.EDITMODE_DRAFT) {
      %>
      <input type="button" value="<gsmsg:write key="cmn.save.draft" />" class="btn_base1" onclick="enq210Draft();">
      <% } %>
      <input type="button" value="<gsmsg:write key="ptl.6" />" class="btn_preview_n1" onclick="buttonPush('enq210preview');">
      <% if (enq210editMode.intValue() == Enq210Form.EDITMODE_TEMPLATE) { %>
      <input type="button" class="btn_ok1" value="OK" onclick="enq210Entry();">
      <% } %>
      <% if (enq210editMode.intValue() != Enq210Form.EDITMODE_TEMPLATE) { %>
      <input type="button" value="<gsmsg:write key="enq.05" />" class="btn_add_n1" onclick="enq210Entry();">
      <% } %>
      <% if (editFlg) { %>
        <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onclick="buttonPush('enq210delete');">
      <% } %>
      <% if (enq210editMode.intValue() != Enq210Form.EDITMODE_TEMPLATE) { %>
      <input type="button" value="<gsmsg:write key="cmn.template" />" class="btn_base1" onclick="enq210DspTemplate();">
      <% } %>
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq210back');">
    </td>
    </tr>

  </table>

</td>
</tr>
</table>

</html:form>


<div id="enq210_template" title="" style="display:none">

  <div style="width:465px;height:320px;overflow:auto;">

  <table width="100%" height="100%">
    <tr>
    <td valign="top" align="center" width="95%">
        <table class="tl0 table_td_border" width="95%">
        <logic:notEmpty name="enq210Form" property="enq210TemplatelList">
        <logic:iterate id="templateData" name="enq210Form" property="enq210TemplatelList" indexId="templateIdx">
          <tr>
          <td width="100%" align="left" class="<% if (templateIdx % 2 == 0) { %>td_type1<% } else { %>td_type29<% } %>">
          <a href="#" onclick="enq210selectTemplate(<bean:write name="templateData" property="value" />)" class="text_link2"><bean:write name="templateData" property="label" /></a>
          </td>
          </tr>
        </logic:iterate>
        </logic:notEmpty>
        </table>
    </td>
    </tr>
  </table>
  </div>
</div>


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>