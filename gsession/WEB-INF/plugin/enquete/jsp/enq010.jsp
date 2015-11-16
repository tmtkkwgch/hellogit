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
<script type="text/javascript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmn220.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/jquery.infieldlabel.min.js?<%= GSConst.VERSION_PARAM %>"></script>

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

<body class="body_03" onunload="callSmailWindowClose();">
<html:form action="/enquete/enq010">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="enqEditMode" value="">
<input type="hidden" name="editEnqSid" value="">
<input type="hidden" name="ansEnqSid" value="">
<input type="hidden" name="enq010smailEnquate" value="">
<input type="hidden" name="enq010ansedSendKbn" value="">
<input type="hidden" name="enq210initFlg" value="">
<input type="hidden" name="enq210templateId" value="">

<html:hidden property="enq010folder" />
<html:hidden property="enq010subFolder" />
<html:hidden property="enq010initFlg" />
<html:hidden property="enq010searchDetailFlg" />
<html:hidden property="enq010sortKey" />
<html:hidden property="enq010order" />
<html:hidden property="enq010svType" />
<html:hidden property="enq010svKeywordSimple" />
<html:hidden property="enq010svKeyword" />
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

<logic:notEmpty name="enq010Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq010Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq010Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq010Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq010Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq010Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>


<bean:define id="openFolder" name="enq010Form" property="enq010folder" type="java.lang.Integer" />
<bean:define id="openSubFolder" name="enq010Form" property="enq010subFolder" type="java.lang.Integer" />
<% boolean receiveFolder = openFolder.intValue() == Enq010Const.FOLDER_RECEIVE; %>
<% boolean sendFolder = openFolder.intValue() == Enq010Const.FOLDER_SEND; %>
<% boolean draftFolder = openFolder.intValue() == Enq010Const.FOLDER_DRAFT; %>
<% boolean templateFolder = openFolder.intValue() == Enq010Const.FOLDER_TEMPLATE; %>

<% if ((receiveFolder || sendFolder) && openSubFolder.intValue() > 0) { %>
<input type="hidden" name="enq010status" value="<%= openSubFolder %>">
<% } %>
<bean:define id="openDetailSearch" name="enq010Form" property="enq010searchDetailFlg" type="java.lang.Integer" />
<bean:define id="enqSortKey" name="enq010Form" property="enq010sortKey" type="java.lang.Integer" />
<bean:define id="enqOrder" name="enq010Form" property="enq010order" type="java.lang.Integer" />
<% String sortSign = ""; %>
<% String nextOrder = ""; %>
<% int titleSortKey = Enq010Const.SORTKEY_OPEN; %>

<html:hidden property="cmd" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value='<%= openFolder %><%= openSubFolder %><bean:write name="enq010Form" property="enq010searchDetailFlg"/>'>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
    <% if (openFolder.intValue() == Enq010Const.FOLDER_RECEIVE) { %>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="cmn.receive" /> ]</td>
    <% } else if (openFolder.intValue() == Enq010Const.FOLDER_SEND) { %>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.13" /> ]</td>
    <% } else if (openFolder.intValue() == Enq010Const.FOLDER_DRAFT) { %>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="cmn.draft" /> ]</td>
    <% } else if (openFolder.intValue() == Enq010Const.FOLDER_TEMPLATE) { %>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="cmn.template" /> ]</td>
    <% } %>
    <td width="100%" class="header_white_bg">
      <logic:equal name="enq010Form" property="enq010adminUser" value="true">
        <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key='cmn.admin.setting' />" onclick="buttonPush('enq010admConf');">
      </logic:equal>
      <input type="button" name="btn_pri" class="btn_setting_n1" value="<gsmsg:write key='cmn.preferences2' />" onclick="buttonPush('enq010priConf');">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>


<!-- BODY -->
<table align="center" cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center" class="wrap_table">

<table class="clear_table" width="100%">
  <tr>

    <!-- フォルダ（左） -->
    <td class="left_menu_area">

      <!-- アンケートフォルダ -->
      <div id="enq_folder_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_sel"><gsmsg:write key="enq.43" /></span>
      </div>

      <% String enq010leftMenuClass = "left_menu_child_content_area"; %>
      <bean:define id="enq010crtUser" name="enq010Form" property="enq010crtUser" type="java.lang.Boolean" />
      <% boolean enqCrtUser = enq010crtUser.booleanValue(); %>
      <% if (!enqCrtUser) { enq010leftMenuClass = "left_menu_child_content_area_once"; } %>
      <div id="enq_folder_child_area" class="<%= enq010leftMenuClass %>" style="height:225px;">
        <div class="child_spacer"></div>

        <div class="content_div">
          <div class="mail_left_line_top folder_clear_float"></div>
          <div class="mail_jushin folder_float"></div>
          <div id="menu_jushin_txt" class="mail_box_txt folder_float" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_RECEIVE) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_NONE) %>);"><gsmsg:write key="cmn.receive" /></div>
          <div id="midoku_txt" class="midoku_txt"></div>

          <div class="clear_div">
            <div class="mail_pertical_line folder_clear_float"></div>
            <div class="mail_left_line folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_RECEIVE) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_UNANS) %>);">
              <gsmsg:write key="anp.ans.notyet" />
              <logic:greaterThan name="enq010Form" property="enq010UnansCount" value="0">
                <span class="text_bb1">(<bean:write name="enq010Form" property="enq010UnansCount" />)</span>
              </logic:greaterThan>
            </div>
            <input type="hidden" name="left_menu_label_name" value="1">
          </div>
          <div class="clear_div">
            <div class="mail_pertical_line folder_clear_float"></div>
            <div class="mail_left_line_bottom folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_RECEIVE) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_REPLIED) %>);"><gsmsg:write key="enq.14" /></div>
            <input type="hidden" name="left_menu_label_name" value="2">
          </div>
        </div>

        <div class="content_div">
          <div class="mail_left_line_bottom folder_clear_float"></div>
          <div class="mail_soshin" style="float:left;"></div>
          <div class="mail_box_txt" style="float:left;" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_SEND) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_NONE) %>);"><gsmsg:write key="enq.13" /></div>

          <div class="clear_div">
            <div class="mail_folder_null folder_clear_float"></div>
            <div class="mail_left_line folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_SEND) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_NOT_PUBLIC) %>);">
            <gsmsg:write key="enq.15" />
              <logic:greaterThan name="enq010Form" property="enq010notPublicCount" value="0">
                <span class="text_bb1">(<bean:write name="enq010Form" property="enq010notPublicCount" />)</span>
              </logic:greaterThan>
            </div>
            <input type="hidden" name="left_menu_label_name" value="<gsmsg:write key="enq.15" />">
          </div>
          <div class="clear_div">
            <div class="mail_folder_null folder_clear_float"></div>
            <div class="mail_left_line folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_SEND) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_PUBLIC) %>);">
            <gsmsg:write key="enq.77" />
              <logic:greaterThan name="enq010Form" property="enq010publicCount" value="0">
                <span class="text_bb1">(<bean:write name="enq010Form" property="enq010publicCount" />)</span>
              </logic:greaterThan>
            </div>
            <input type="hidden" name="left_menu_label_name" value="<gsmsg:write key="enq.77" />">
          </div>
          <div class="clear_div">
            <div class="mail_folder_null folder_clear_float"></div>
            <div class="mail_left_line folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_SEND) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_COMP_ANS) %>);"><gsmsg:write key="enq.16" /></div>
            <input type="hidden" name="left_menu_label_name" value="<gsmsg:write key="enq.16" />">
          </div>
          <div class="clear_div">
            <div class="mail_folder_null folder_clear_float"></div>
            <div class="mail_left_line folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_SEND) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_COMP_PUB) %>);"><gsmsg:write key="enq.17" /></div>
            <input type="hidden" name="left_menu_label_name" value="<gsmsg:write key="enq.17" />">
          </div>

          <div class="clear_div">
            <div class="mail_folder_null folder_clear_float"></div>
            <div class="mail_left_line_bottom mail_folder folder_float"></div>
            <div class="mail_box_txt mail_box_lable_txt folder_float changeLabelDir" id="1" onclick="changeFolder(<%= String.valueOf(Enq010Const.FOLDER_DRAFT) %>, <%= String.valueOf(Enq010Const.SUBFOLDER_NONE) %>);">
              <div class="mail_folder folder_float"></div>
              <gsmsg:write key="cmn.draft" />
              <logic:greaterThan name="enq010Form" property="enq010draftCount" value="0">
                <span class="text_bb1">(<bean:write name="enq010Form" property="enq010draftCount" />)</span>
              </logic:greaterThan>
            </div>
          </div>

        </div>

      </div>

      <% if (enqCrtUser) { %>
      <!-- テンプレート -->
      <div id="enq_template_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_not_sel"><gsmsg:write key="cmn.shared.template" />
          <span class="template_add_wrap"><span id="template_add" onclick="buttonPush('editTemplate');"><gsmsg:write key="cmn.setting" /></span></span>
        </span>
      </div>
      <logic:notEmpty name="enq010Form" property="enq010TemplateList">
        <div id="enq_template_child_area" class="left_menu_child_content_area left_menu_botton" style="display: none;">
          <div class="template_txt_div">
            <table class="clear_table">
            <tbody>
              <logic:notEmpty name="enq010Form" property="enq010TemplateList">
                <logic:iterate id="template" name="enq010Form" property="enq010TemplateList">
                  <tr>
                    <td width="100%">
                      <a class="template_sel_txt" id='<bean:write name="template" property="emnSid" />' href="#" onclick='editTemplate(<bean:write name="template" property="emnSid" />);'><div class="dspTempName"><bean:write name="template" property="viewEmnTitle" filter="false"/></div>
                      <span class="tooltips display_none"><gsmsg:write key="cmn.type2" />：<bean:write name="template" property="etpName" />
                        <gsmsg:write key="cmn.title" />：<bean:write name="template" property="emnTitle" />
                      </span>
                      </a>
                    </td>
                  </tr>
                </logic:iterate>
              </logic:notEmpty>
            </tbody>
            </table>
          </div>
        </div>
      </logic:notEmpty>

      <logic:empty name="enq010Form" property="enq010TemplateList">
        <div class="left_menu_content_area menu_head_area_none"></div>
      </logic:empty>

      <% } %>

    </td>


    <!-- 右 -->
    <td class="content_area">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="30%" class="text_bb8" align="left" style="margin-top:50px;" nowrap>
        &nbsp;
        <% if (openFolder.intValue() == Enq010Const.FOLDER_RECEIVE) { %>
          <gsmsg:write key="cmn.receive" />
          <% if (openSubFolder.intValue() == Enq010Const.SUBFOLDER_UNANS) { %>
          &nbsp;<gsmsg:write key="anp.ans.notyet" />
          <% } else if (openSubFolder.intValue() == Enq010Const.SUBFOLDER_REPLIED) { %>
          &nbsp;<gsmsg:write key="enq.14" />
          <% } %>
        <% } else if (openFolder.intValue() == Enq010Const.FOLDER_SEND) { %>
          <gsmsg:write key="enq.13" />
          <% if (openSubFolder.intValue() == Enq010Const.SUBFOLDER_NOT_PUBLIC) { %>
          &nbsp;<gsmsg:write key="enq.15" />
          <% } else if (openSubFolder.intValue() == Enq010Const.SUBFOLDER_PUBLIC) { %>
          &nbsp;<gsmsg:write key="enq.77" />
          <% } else if (openSubFolder.intValue() == Enq010Const.SUBFOLDER_COMP_ANS) { %>
          &nbsp;<gsmsg:write key="enq.16" />
          <% } else if (openSubFolder.intValue() == Enq010Const.SUBFOLDER_COMP_PUB) { %>
          &nbsp;<gsmsg:write key="enq.17" />
          <% } %>
        <% } else if (openFolder.intValue() == Enq010Const.FOLDER_DRAFT) { %>
          <gsmsg:write key="cmn.draft" />
        <% } else if (openFolder.intValue() == Enq010Const.FOLDER_TEMPLATE) { %>
          <gsmsg:write key="cmn.template" />
        <% } %>
      </td>
      <td width="70%" style="margin-bottom: 10px; text-align: right;" nowrap>
        <% if (openDetailSearch.intValue() == Enq010Const.SEARCH_DETAIL_OFF) { %>
        <span id="simpleSearch" class="text_base2" >
        <% } else { %>
        <span id="simpleSearch" class="text_base2" style="display: none;" >
        <% } %>
            <% if (openFolder.intValue() == Enq010Const.FOLDER_RECEIVE) { %>
            <span class="text_base2" ><html:multibox name="enq010Form" property="enq010statusAnsOverSimple" styleId="search_simple_joutai_ansover" onclick="chkAnsOverSimple();">
              <%= String.valueOf(Enq010Const.SEARCH_ANSFLGOK_ONLY) %>
            </html:multibox><label for="search_simple_joutai_ansover"><gsmsg:write key="enq.enq010.06" /></label></span>
            <% } %>
            <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
            <html:text name="enq010Form" property="enq010keywordSimple" maxlength="100" style="width:185px;" />
            <input type="submit" name="btn_prjadd" class="btn_base1s" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('enq010SearchSimple');">
        </span>
         <input type="button" name="btn_prjadd" class="btn_search_n1" value="<gsmsg:write key="cmn.advanced.search" />" onClick="enq010changeSearch();">
        <% if (enqCrtUser) { %>
          <input type="button" name="" class="btn_add_n1" value="<gsmsg:write key="cmn.add2" />" onclick="addEnquete();">
        <% } %>
        <% if (!receiveFolder) { %>
        <logic:notEmpty name="enq010Form" property="enq010EnqueteList">
          <% if (enqCrtUser) { %>
            <input type="button" name="" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('enq010delEnquete');">
          <% } %>
        </logic:notEmpty>
        <% } %>
      </td>
      </tr>
      </table>

      <logic:messagesPresent message="false">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr><td width="100%" style="margin-bottom: 10px;"><html:errors /></td></tr>
      </table>
      </logic:messagesPresent>

      <!-- 検索条件 -->
      <% if (openDetailSearch != Enq010Const.SEARCH_DETAIL_OFF) { %>
      <table cellpadding="5" width="99%" class="tl0" id="enq010searchDetailArea" >
      <% } else { %>
      <table cellpadding="5" width="99%" class="tl0" id="enq010searchDetailArea" style="display: none;">
      <% } %>
      <tbody>

        <!-- 種類 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.type2" /></td>
          <td class="td_type20" nowrap>
          <span class="text_base2">
            <html:select property="enq010type" style="vertical-align:middle;">
              <logic:notEmpty name="enq010Form" property="enqTypeList">
              <html:optionsCollection name="enq010Form" property="enqTypeList" value="value" label="label" />
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
        <html:text name="enq010Form" property="enq010keyword" maxlength="100" style="width:321px;" />
        <html:radio name="enq010Form" property="enq010keywordType" value="0" styleId="keyKbn_01" /><label for="keyKbn_01"><gsmsg:write key="cmn.contains.all.and" /></label>&nbsp;
        <html:radio name="enq010Form" property="enq010keywordType" value="1" styleId="keyKbn_02" /><label for="keyKbn_02"><gsmsg:write key="cmn.orcondition" /></label>
        </span>
        </td>
        </tr>

        <% if (!templateFolder) { %>
        <!-- 発信者 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cir.2" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <span class="text_bb2"><gsmsg:write key="cmn.group" /></span>
        <html:select property="enq010sendGroup" onchange="buttonPush('init');">
          <logic:notEmpty name="enq010Form" property="enqSendGroupList">
          <html:optionsCollection name="enq010Form" property="enqSendGroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <span class="text_bb2"><gsmsg:write key="cmn.user" /></span>
        <html:select property="enq010sendUser">
          <logic:notEmpty name="enq010Form" property="enqSendUserList">
          <html:optionsCollection name="enq010Form" property="enqSendUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
         &nbsp;&nbsp;&nbsp;&nbsp;
        </span>
        <div class="textfield" style="margin-top: 12px;">
          <label for="enq010sendInputText"><gsmsg:write key="cmn.search.item2"/></label>
          <html:text name="enq010Form" property="enq010sendInputText" maxlength="20" styleClass="text_base" styleId="enq010sendInputText" style="width:273px;" />
        </div>
        </td>
        </tr>
        <% } %>

        <% if (sendFolder || draftFolder) { %>
        <!-- 作成日 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="man.creation.date" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:radio name="enq010Form" property="enq010makeDateKbn" value="0" styleId="enq010makeDateFromKbn0" onclick="enq010chkSrhDate(1);" /><label for="enq010makeDateFromKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq010Form" property="enq010makeDateKbn" value="1" styleId="enq010makeDateFromKbn1" onclick="enq010chkSrhDate(1);" /><label for="enq010makeDateFromKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq010makeDateArea">
        <html:select property="enq010makeDateFromYear" styleId="enq010makeDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010makeDateFromMonth" styleId="enq010makeDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010makeDateFromDay" styleId="enq010makeDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="makeDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq010makeDateFromDay, this.form.enq010makeDateFromMonth, this.form.enq010makeDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq010makeDateToYear" styleId="enq010makeDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010makeDateToMonth" styleId="enq010makeDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010makeDateToDay" styleId="enq010makeDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="makeDateToCalendarBtn" onclick="wrtCalendar(this.form.enq010makeDateToDay, this.form.enq010makeDateToMonth, this.form.enq010makeDateToYear);" class="calendar_btn">

        </span>
        </td>
        </tr>
        <% } %>

        <% if (!templateFolder) { %>

<%--         <!-- 公開期間 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.open.period" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:radio name="enq010Form" property="enq010pubDateKbn" value="0" styleId="enq010pubDateKbn0" onclick="enq010chkSrhDate(2);" /><label for="enq010pubDateKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq010Form" property="enq010pubDateKbn" value="1" styleId="enq010pubDateKbn1" onclick="enq010chkSrhDate(2);" /><label for="enq010pubDateKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq010pubDateArea">
        <html:select property="enq010pubDateFromYear" styleId="enq010pubDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010pubDateFromMonth" styleId="enq010pubDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010pubDateFromDay" styleId="enq010pubDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="pubDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq010pubDateFromDay, this.form.enq010pubDateFromMonth, this.form.enq010pubDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq010pubDateToYear" styleId="enq010pubDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010pubDateToMonth" styleId="enq010pubDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010pubDateToDay" styleId="enq010pubDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="pubDateToCalendarBtn" onclick="wrtCalendar(this.form.enq010pubDateToDay, this.form.enq010pubDateToMonth, this.form.enq010pubDateToYear);" class="calendar_btn">
        </span>
        </td>
        </tr>
 --%>
        <!-- 回答期限 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
        <td class="td_type20" nowrap>

        <span class="text_base2">
        <html:radio name="enq010Form" property="enq010ansDateKbn" value="0" styleId="enq010ansDateKbn0" onclick="enq010chkSrhDate(3);" /><label for="enq010ansDateKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq010Form" property="enq010ansDateKbn" value="1" styleId="enq010ansDateKbn1" onclick="enq010chkSrhDate(3);" /><label for="enq010ansDateKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq010ansDateArea">
        <html:select property="enq010ansDateFromYear" styleId="enq010ansDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010ansDateFromMonth" styleId="enq010ansDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010ansDateFromDay" styleId="enq010ansDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="ansDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq010ansDateFromDay, this.form.enq010ansDateFromMonth, this.form.enq010ansDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq010ansDateToYear" styleId="enq010ansDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010ansDateToMonth" styleId="enq010ansDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010ansDateToDay" styleId="enq010ansDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="ansDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq010ansDateToDay, this.form.enq010ansDateToMonth, this.form.enq010ansDateToYear);" class="calendar_btn">
        </span>
        </td>
        </tr>
        <% if (!templateFolder) { %>
        <!-- 結果公開期間 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
        <html:radio name="enq010Form" property="enq010resPubDateKbn" value="0" styleId="enq010resPubDateKbn0" onclick="enq010chkSrhDate(4);" /><label for="enq010resPubDateKbn0"><gsmsg:write key="cmn.not.specified" /></label>
        <html:radio name="enq010Form" property="enq010resPubDateKbn" value="1" styleId="enq010resPubDateKbn1" onclick="enq010chkSrhDate(4);" /><label for="enq010resPubDateKbn1"><gsmsg:write key="wml.wml010.12" /></label>
        </span>

        <span id="enq010resPubDateArea">
        <html:select property="enq010resPubDateFromYear" styleId="enq010resPubDateFromYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010resPubDateFromMonth" styleId="enq010resPubDateFromMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010resPubDateFromDay" styleId="enq010resPubDateFromDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="resPubDateFromCalendarBtn" onclick="wrtCalendar(this.form.enq010resPubDateFromDay, this.form.enq010resPubDateFromMonth, this.form.enq010resPubDateFromYear);" class="calendar_btn">
            ～

        <html:select property="enq010resPubDateToYear" styleId="enq010resPubDateToYear" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="yearCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010resPubDateToMonth" styleId="enq010resPubDateToMonth" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="monthCombo" value="value" label="label" />
        </html:select>&nbsp;
        <html:select property="enq010resPubDateToDay" styleId="enq010resPubDateToDay" style="vertical-align:middle;">
        <html:optionsCollection name="enq010Form" property="dayCombo" value="value" label="label" />
        </html:select>
        <input type="button" value="Cal" name="resPubDateToCalendarBtn" onclick="wrtCalendar(this.form.enq010resPubDateToDay, this.form.enq010resPubDateToMonth, this.form.enq010resPubDateToYear);" class="calendar_btn">
        </span>
        </td>
        </tr>
        <% } %>
        <% } %>

        <!-- 重要度 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="project.prj050.4" /></td>
          <td class="td_type20" nowrap>

            <html:multibox name="enq010Form" property="enq010priority" styleId="search_juuyou_01">
              <%= String.valueOf(GSConstEnquete.JUUYOU_0) %>
            </html:multibox>
            <label for="search_juuyou_01">
              <img src="../enquete/images/star_blue_16.png" class="star3" border="0" alt="<gsmsg:write key="project.58" />">
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt="<gsmsg:write key="project.58" />">
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt="<gsmsg:write key="project.58" />">
            </label>

            <html:multibox name="enq010Form" property="enq010priority" styleId="search_juuyou_02">
              <%= String.valueOf(GSConstEnquete.JUUYOU_1) %>
            </html:multibox>
            <label for="search_juuyou_02">
              <img src="../enquete/images/star_gold_16.png" class="star3" border="0" alt="<gsmsg:write key="project.59" />">
              <img src="../enquete/images/star_gold_16.png" class="star3" border="0" alt="<gsmsg:write key="project.59" />">
              <img src="../enquete/images/star_white_16.png" class="star3" border="0" alt="<gsmsg:write key="project.59" />">
            </label>

            <html:multibox name="enq010Form" property="enq010priority" styleId="search_juuyou_03">
              <%= String.valueOf(GSConstEnquete.JUUYOU_2) %>
            </html:multibox>
            <label for="search_juuyou_03">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt="<gsmsg:write key="project.60" />">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt="<gsmsg:write key="project.60" />">
              <img src="../enquete/images/star_red_16.png" class="star3" border="0" alt="<gsmsg:write key="project.60" />">
            </label>
          </td>
        </tr>

        <% if ((receiveFolder || sendFolder) && openSubFolder.intValue() <= 0) { %>
        <!-- 状態 -->
        <tr>
        <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="anp.state" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
          <% if (receiveFolder) { %>
            <html:multibox name="enq010Form" property="enq010status" styleId="search_joutai_receive_01">
              <%= String.valueOf(Enq010Const.STATUS_NOTANS) %>
            </html:multibox><label for="search_joutai_receive_01"><gsmsg:write key="anp.ans.notyet" /></label>
            <html:multibox name="enq010Form" property="enq010status" styleId="search_joutai_receive_02">
              <%= String.valueOf(Enq010Const.STATUS_ANS) %>
            </html:multibox><label for="search_joutai_receive_02"><gsmsg:write key="enq.14" /></label>
          <% } else if (sendFolder) { %>
            <html:multibox name="enq010Form" property="enq010status" styleId="search_joutai_send_01">
              <%= String.valueOf(Enq010Const.STATUS_NOTPUB) %>
            </html:multibox><label for="search_joutai_send_01"><gsmsg:write key="enq.15" /></label>
            <html:multibox name="enq010Form" property="enq010status" styleId="search_joutai_send_02">
              <%= String.valueOf(Enq010Const.STATUS_PUB) %>
            </html:multibox><label for="search_joutai_send_02"><gsmsg:write key="enq.77" /></label>
            <html:multibox name="enq010Form" property="enq010status" styleId="search_joutai_send_03">
              <%= String.valueOf(Enq010Const.STATUS_ANSEXIT) %>
            </html:multibox><label for="search_joutai_send_03"><gsmsg:write key="enq.16" /></label>
            <html:multibox name="enq010Form" property="enq010status" styleId="search_joutai_send_04">
              <%= String.valueOf(Enq010Const.STATUS_PUBEXIT) %>
            </html:multibox><label for="search_joutai_send_04"><gsmsg:write key="enq.17" /></label>
          <% } %>
        </span>
        </td>
        </tr>
        <% } %>
        <% if (receiveFolder) { %>
        <!-- 回答 可/不可-->
            <tr>
            <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq010.09" /></td>
            <td class="td_type20" nowrap><span class="text_base2">
                <html:multibox name="enq010Form" property="enq010statusAnsOver" styleId="search_joutai_receive_03">
                  <%= String.valueOf(Enq010Const.PUBLIC_ANSFLG_OK) %>
                </html:multibox><label for="search_joutai_receive_03"><gsmsg:write key="enq.enq010.07" /></label>
                <html:multibox name="enq010Form" property="enq010statusAnsOver" styleId="search_joutai_receive_03">
                  <%= String.valueOf(Enq010Const.PUBLIC_ANSFLG_NG) %>
                </html:multibox><label for="search_joutai_receive_03"><gsmsg:write key="enq.enq010.08" /></label>
            </span></td>
            </tr>
        <% } %>

        <% if (templateFolder) { %>
        <!-- 匿名 -->
        <tr>
        <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.anonymity" /></td>
        <td class="td_type20" nowrap>
        <span class="text_base2">
          <html:radio name="enq010Form" property="enq010anony" value="0" styleId="search_anony_00" /><label for="search_anony_00"><gsmsg:write key="cmn.all" /></label>
          <html:radio name="enq010Form" property="enq010anony" value="1" styleId="search_anony_01" /><label for="search_anony_01"><gsmsg:write key="cmn.except.anonymity" /></label>
          <html:radio name="enq010Form" property="enq010anony" value="2" styleId="search_anony_02" /><label for="search_anony_02"><gsmsg:write key="cmn.anonymity" /></label>
        </span>
        </td>
        </tr>
        <% } %>

        <tr>
          <td colspan="2" style="text-align: center!important; padding-top: 10px; padding-bottom: 20px;"><input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onclick="buttonPush('enq010Search');"></td>
        </tr>

      </tbody>
      </table>

  <% if (openFolder.intValue() == Enq010Const.FOLDER_RECEIVE) { %>
      <%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_receive.jsp" %>
  <% } else if (openFolder.intValue() == Enq010Const.FOLDER_SEND) { %>
      <%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_send.jsp" %>
  <% } else if (openFolder.intValue() == Enq010Const.FOLDER_DRAFT) { %>
      <%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_draft.jsp" %>
   <% } %>

    </td>
  </tr>

</table>

</td>
</tr>
</table>

<span id="tooltip_area"></span>

</html:form>

<div id="dialogSmlNotice" title='<gsmsg:write key="shortmail.notification" />' style="display:none;">
  <p>
    <div>
       <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
       <b><gsmsg:write key="enq.enq010.03" /></b><br><br>
       <input type="radio" name="enq010ansedSendKbn" id="sendKbn0" value="0" checked><label for="sendKbn0" class="text_base2 dialog_checkbox"><b><gsmsg:write key="enq.enq010.04" /></b></label><br>
       <input type="radio" name="enq010ansedSendKbn" id="sendKbn1" value="1" ><label for="sendKbn1" class="text_base2 dialog_checkbox"><b><gsmsg:write key="enq.enq010.05" /></b></label>
    </div>
  </p>
</div>


<div id="smlPop" title="" style="display:none">
  <div id="smlCreateArea" width="100%" height="100%"></div>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>