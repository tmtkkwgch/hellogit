<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>
<%@ page import="jp.groupsession.v2.enq.enq210.Enq210Form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enqDelCheck.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq210kn.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>



<% boolean editFlg = false; %>
<logic:equal name="enq210knForm" property="enqEditMode" value="<%= String.valueOf(GSConstEnquete.EDITMODE_EDIT) %>">
<% editFlg = true; %>
</logic:equal>
<% boolean backPage970 = false; %>
<logic:equal name="enq210knForm" property="enq970BackPage" value="1">
<% backPage970 = true; %>
</logic:equal>

<% if (backPage970) { %>
<title>[GroupSession] <gsmsg:write key="enq.enq970.01" /></title>
<% } else { %>
<%   if (editFlg) { %>
<title>[GroupSession] <gsmsg:write key="enq.plugin" /> <gsmsg:write key="enq.enq210kn.02" /></title>
<%   } else { %>
<title>[GroupSession] <gsmsg:write key="enq.plugin" /> <gsmsg:write key="enq.enq210kn.01" /></title>
<%   } %>
<% } %>

</head>
<body class="body_03">
<html:form styleId="enqForm" action="/enquete/enq210kn">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="cmd" value="">
<input type="hidden" name="SEQ" value="">

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq210_hiddenParams.jsp" %>
<html:hidden property="enq210editMode" />
<html:hidden property="enq210DescText" />
<html:hidden property="tempClickBtn" />
<html:hidden property="answerDataReset" />
<input type="hidden" name="enq210DelAnsFlg" value="false">

<logic:notEmpty name="enq210knForm" property="enq010priority">
<logic:iterate id="svPriority" name="enq210knForm" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq010status">
<logic:iterate id="svStatus" name="enq210knForm" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq210knForm" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq210knForm" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq210knForm" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq210knForm" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq210knForm" property="enq210answerGroup">
<logic:iterate id="answerUser" name="enq210knForm" property="enq210answerList">
  <input type="hidden" name="enq210answerList" value="<bean:write name="answerUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq210knForm" property="enq230selectEnqSid">
  <logic:iterate id="sv230SelectEnqSid" name="enq210knForm" property="enq230selectEnqSid">
    <input type="hidden" name="enq230selectEnqSid" value="<bean:write name='sv230SelectEnqSid' />" >
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq230priority">
  <logic:iterate id="sv230Priority" name="enq210knForm" property="enq230priority">
    <input type="hidden" name="enq230priority" value='<bean:write name="sv230Priority" />'>
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq230svPriority">
<logic:iterate id="svPriority" name="enq210knForm" property="enq230svPriority">
  <input type="hidden" name="enq230svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq230svStatus">
<logic:iterate id="svStatus" name="enq210knForm" property="enq230svStatus">
  <input type="hidden" name="enq230svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq970_hiddenParams.jsp" %>

<logic:notEmpty name="enq210knForm" property="enq970selectEnqSid">
  <logic:iterate id="svEnq970selectEnqSid" name="enq210knForm" property="enq970selectEnqSid">
    <input type="hidden" name="enq970selectEnqSid" value='<bean:write name="svEnq970selectEnqSid" />'>
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq970priority">
  <logic:iterate id="svEnq970priority" name="enq210knForm" property="enq970priority">
    <input type="hidden" name="enq970priority" value='<bean:write name="svEnq970priority" />'>
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq970status">
  <logic:iterate id="svEnq970status" name="enq210knForm" property="enq970status">
    <input type="hidden" name="enq970status" value='<bean:write name="svEnq970status" />'>
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq970svPriority">
  <logic:iterate id="svEnq970svPriority" name="enq210knForm" property="enq970svPriority">
    <input type="hidden" name="enq970svPriority" value='<bean:write name="svEnq970svPriority" />'>
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq210knForm" property="enq970svStatus">
  <logic:iterate id="svEnq970svStatus" name="enq210knForm" property="enq970svStatus">
    <input type="hidden" name="enq970svStatus" value='<bean:write name="svEnq970svStatus" />'>
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<bean:define id="enq210editMode" name="enq210knForm" property="enq210editMode" type="java.lang.Integer" />
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

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center">

  <bean:define id="enq210editMode" name="enq210knForm" property="enq210editMode" type="java.lang.Integer" />
  <% boolean enqTemplateFlg = enq210editMode.intValue() == jp.groupsession.v2.enq.enq210.Enq210Form.EDITMODE_TEMPLATE; %>
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>

  <% if (!backPage970) { %>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" /> "></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /> </span></td>
    <% if (editFlg) { %>
      <% if (enqTemplateFlg) { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210kn.04" /> ]</td>
      <% } else { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210kn.02" /> ]</td>
      <% } %>
    <% } else { %>
      <% if (enqTemplateFlg) { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210kn.03" /> ]</td>
      <% } else { %>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq210kn.01" /> ]</td>
      <% } %>
    <% } %>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
  <% } else { %>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.enq970.01" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <% } %>
  </tr>
  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tbody><tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    <td class="header_glay_bg" width="50%">
      <logic:notEqual name="enq210knForm" property="enq970BackPage" value="1">
<%--       <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onclick="buttonPush('decision');"> --%>
      <input type="button" value="<gsmsg:write key="man.final" />" id ="kakuteibtn" class="btn_base1">
      </logic:notEqual>
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq210knback');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
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

<table class="clear_table" width="100%">
  <tr>

    <td class="content_area">

      <table cellpadding="5" width="100%" class="tl0">
      <tbody>

        <!-- 基本情報 重要度 -->
        <tr>
          <td width="10%" colspan="2" width="" align="center" class="td_gray text_header" nowrap><gsmsg:write key="project.prj050.4" /></td>
          <td width="90%" colspan="3" class="td_type20" align="left" valign="bottom" nowrap>
            <table width="400px" cellpadding="0" cellspacing="0" border="0">
              <tr>
                <td width="40px" align="left" valign="middle" class="text_base2" nowrap>
                  <logic:equal name="enq210knForm" property="enq210Juuyou" value="0">
                  <gsmsg:write key="project.58" />
                  </logic:equal>
                  <logic:equal name="enq210knForm" property="enq210Juuyou" value="1">
                  <gsmsg:write key="project.59" />
                  </logic:equal>
                  <logic:equal name="enq210knForm" property="enq210Juuyou" value="2">
                  <gsmsg:write key="project.60" />
                  </logic:equal>
                </td>
                <td width="200px" align="left" valign="middle" nowrap>
                  <logic:equal name="enq210knForm" property="enq210Juuyou" value="0">
                  <div id="star_1" class="star" >
                    <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                    <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                    <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                  </div>
                  </logic:equal>
                  <logic:equal name="enq210knForm" property="enq210Juuyou" value="1">
                  <div id="star_2" class="star">
                    <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                    <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                    <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                  </div>
                  </logic:equal>
                  <logic:equal name="enq210knForm" property="enq210Juuyou" value="2">
                  <div id="star_3" class="star">
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                  </div>
                  </logic:equal>
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
              <bean:write name="enq210knForm" property="enq210knViewSyurui" />&nbsp;
            </span>
          </td>
          <!-- 基本情報 発信者 -->
          <% if (!enqTemplateFlg) { %>
          <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cir.2" /></td>
          <td width="50%" class="td_type20">
            <bean:define id="sdFlg" name="enq210knForm" property="enq210knSenderDelFlg" type="java.lang.Boolean" />
            <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
            <bean:write name="enq210knForm" property="enq210knSenderName" />
            </span>
          </td>
          <% } %>
        </tr>

        <!-- 基本情報 タイトル -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.title" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <bean:write name="enq210knForm" property="enq210Title" />
            </span>
          </td>
        </tr>

        <!-- 基本情報 説明文 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header td_1" nowrap><gsmsg:write key="cmn.explanation" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <bean:write name="enq210knForm" property="enq210Desc" filter="false" />&nbsp;
            </span>
          </td>
        </tr>

        <bean:define id="attachKbn" name="enq210knForm" property="enq210AttachKbn" type="java.lang.Integer" />
        <tr>
          <td width="4%" class="td_gray td_1"></td>
          <td width="9%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.attached" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
            <% if (attachKbn.intValue() == 1 || attachKbn.intValue() == 2) { %><bean:write name="enq210knForm" property="enq210fileName" />
            <% } %>&nbsp;
          </span>
          </td>
        </tr>

        <% if (attachKbn.intValue() == 1 || attachKbn.intValue() == 2) { %>
        <tr>
          <td class="td_gray td_1"></td>
          <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.position" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <logic:equal name="enq210knForm" property="enq210AttachPos" value="0"><gsmsg:write key="cmn.up2" /></logic:equal>
              <logic:equal name="enq210knForm" property="enq210AttachPos" value="1"><gsmsg:write key="cmn.down2" /></logic:equal>
            </span>
          </td>
        </tr>
        <% } %>

        <!-- 基本情報 公開開始日 -->
        <% if (!enqTemplateFlg) { %>
          <tr>
          <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.53" /></td>
          <td colspan="3" class="td_type20">
            <bean:define id="frYear" name="enq210knForm" property="enq210FrYear" type="java.lang.Integer" />
            <bean:define id="frMonth" name="enq210knForm" property="enq210FrMonth" type="java.lang.Integer" />
            <bean:define id="frDay" name="enq210knForm" property="enq210FrDay" type="java.lang.Integer" />
            <gsmsg:write key="cmn.date4" arg0="<%= String.valueOf(frYear) %>" arg1="<%= String.valueOf(frMonth) %>" arg2="<%= String.valueOf(frDay) %>"/>

          </td>
          </tr>

          <!-- 基本情報 回答期限 -->
          <tr>
          <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
          <td colspan="3" class="td_type20">
            <bean:define id="ansYear" name="enq210knForm" property="enq210AnsYear" type="java.lang.Integer" />
            <bean:define id="ansMonth" name="enq210knForm" property="enq210AnsMonth" type="java.lang.Integer" />
            <bean:define id="ansDay" name="enq210knForm" property="enq210AnsDay" type="java.lang.Integer" />
            <gsmsg:write key="cmn.date4" arg0="<%= String.valueOf(ansYear) %>" arg1="<%= String.valueOf(ansMonth) %>" arg2="<%= String.valueOf(ansDay) %>"/>
          </td>
          </tr>
        <% } %>

        <!-- 基本情報 結果公開 -->
        <tr>
        <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.07" /></td>
        <td colspan="3" class="td_type20">
          <logic:equal name="enq210knForm" property="enq210AnsOpen" value="1">
          <span class="text_base2"><gsmsg:write key="cmn.publish" /></span>
        <% if (!enqTemplateFlg) { %>
          <br><br>
          <gsmsg:write key="cmn.start" />:&nbsp;
          <bean:define id="ansPubFrYear" name="enq210knForm" property="enq210AnsPubFrYear" type="java.lang.Integer" />
          <bean:define id="ansPubFrMonth" name="enq210knForm" property="enq210AnsPubFrMonth" type="java.lang.Integer" />
          <bean:define id="ansPubFrDay" name="enq210knForm" property="enq210AnsPubFrDay" type="java.lang.Integer" />
          <gsmsg:write key="cmn.date4" arg0="<%= String.valueOf(ansPubFrYear) %>" arg1="<%= String.valueOf(ansPubFrMonth) %>" arg2="<%= String.valueOf(ansPubFrDay) %>"/>
          <br>
          <gsmsg:write key="main.src.man250.30" />:&nbsp;
          <logic:equal name="enq210knForm" property="enq210ToKbn" value="1">
            <gsmsg:write key="main.man200.9" />
          </logic:equal>
          <logic:notEqual name="enq210knForm" property="enq210ToKbn" value="1">
            <bean:define id="toYear" name="enq210knForm" property="enq210ToYear" type="java.lang.Integer" />
            <bean:define id="toMonth" name="enq210knForm" property="enq210ToMonth" type="java.lang.Integer" />
            <bean:define id="toDay" name="enq210knForm" property="enq210ToDay" type="java.lang.Integer" />
            <gsmsg:write key="cmn.date4" arg0="<%= String.valueOf(toYear) %>" arg1="<%= String.valueOf(toMonth) %>" arg2="<%= String.valueOf(toDay) %>"/>
          </logic:notEqual>
        <% } %>
          </logic:equal>
          <logic:notEqual name="enq210knForm" property="enq210AnsOpen" value="1">
          <span class="text_base2"><gsmsg:write key="cmn.not.publish" /></span>
          </logic:notEqual>
        </td>
        </tr>

        <% if (!enqTemplateFlg) { %>
        <logic:notEqual name="enq210knForm" property="enq970BackPage" value="1">
        <logic:equal name="enq210knForm" property="enq210pluginSmailUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_USE) %>">
        <!-- ショートメール通知 -->
        <tr>
        <td colspan="2" align="center" class="td_gray text_header" style="padding: 0px 5px;"nowrap><gsmsg:write key="shortmail.notification" /></td>
        <td colspan="3" class="td_type20">
          <logic:equal name="enq210knForm" property="enq210smailInfo" value="<%= String.valueOf(Enq210Form.SML_INFO_SEND) %>">
            <span class="text_base2"><gsmsg:write key="cmn.notify" /></span>
          </logic:equal>
        </td>
        </tr>
        </logic:equal>
        </logic:notEqual>
        <% } %>

        <!-- 基本情報 匿名 -->
        <tr>
        <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.anonymity" /></td>
        <td colspan="3" class="td_type20" align="left" valign="middle" nowrap>
          <logic:equal name="enq210knForm" property="enq210Anony" value="1">
          <span class="text_base2"><gsmsg:write key="enq.06" /></span>
          </logic:equal>
        </td>
        </tr>

        <!-- 基本情報 対象者 -->
        <tr>
        <td colspan="2" width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.10" /></td>
        <td colspan="3" class="td_type20" align="left" valign="middle">
          <logic:notEmpty name="enq210knForm" property="selectAnswerCombo">
          <logic:iterate id="answerUser" name="enq210knForm" property="selectAnswerCombo">
            <span class="text_base"><bean:write name="answerUser" property="label" /></span><br>
          </logic:iterate>
          </logic:notEmpty>
          <logic:empty name="enq210knForm" property="selectAnswerCombo">&nbsp;</logic:empty>

          <logic:notEmpty name="enq210knForm" property="enq210knDelAnswerList">
          <logic:iterate id="delAnswer" name="enq210knForm" property="enq210knDelAnswerList">
            <br><span class="text_base text_deluser_enq"><bean:write name="delAnswer" /></span>
          </logic:iterate>
          </logic:notEmpty>
        </td>
        </tr>

      </tbody>
      </table>

      <img src="../common/images/spacer.gif" width="1px" height="15px" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      <!-- 設問情報 タイトル -->
      <table width="100%">
      <tbody>
      <tr>
      <td width="20%" class="text_info_title" align="left" valign="bottom"><gsmsg:write key="enq.04" /></td>
      <td width="80%" align="left" class="text_base2">
    <% if (!backPage970) { %>
      <span style="font-weight:bold;"><gsmsg:write key="enq.09" /></span>
      <span class="text_base2">
        <logic:equal name="enq210knForm" property="enq210queSeqType" value="1"><gsmsg:write key="cmn.auto" /></logic:equal>
        <logic:notEqual name="enq210knForm" property="enq210queSeqType" value="1"><gsmsg:write key="cmn.manual" /></logic:notEqual>
    <% } else { %>&nbsp;<% } %>
      </span>
      </td>
      </tr>
      </tbody>
      </table>

      <img src="../common/images/spacer.gif" width="1px" height="0px" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      <!-- 設問情報 -->
      <table class="tl0" width="100%" cellpadding="5" cellspacing="0">
      <thead>
        <tr>
          <th width="20%" class="table_bg_7D91BD"><span class="text_tlw_10pt"><gsmsg:write key="enq.09" /></span></th>
          <th width="80%" class="table_bg_7D91BD"><span class="text_tlw_10pt"><gsmsg:write key="enq.12" /></span></th>
        </tr>
      </thead>

      <tbody id="enqTbl">
        <logic:notEmpty name="enq210knForm" property="ebaList">
        <logic:iterate id="ebaData" name="enq210knForm" property="ebaList" indexId="lineIdx">
        <tr>
        <td class="td_type1 text_mod_10pt" align="left" valign="top">
          <logic:equal name="enq210knForm" property="enq210queSeqType" value="1">
            <logic:greaterThan name="ebaData" property="enq210AutoQueNo" value="0">
            <bean:write name="ebaData" property="enq210AutoQueNo" />
            </logic:greaterThan>
          </logic:equal>
          <logic:notEqual name="enq210knForm" property="enq210queSeqType" value="1">
          <bean:write name="ebaData" property="enq210QueNo" />
          </logic:notEqual>
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
        </tr>
        </logic:iterate>
        </logic:notEmpty>
      </tbody>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      </td>
      </tr>


    <tr>
    <td>
    <img src="../common/images/spacer.gif" width="1px" height="30px" border="0" alt="<gsmsg:write key='cmn.spacer' />">
    </td>
    </tr>

    <tr>
    <td width="50%" align="right">
      <logic:notEqual name="enq210knForm" property="enq970BackPage" value="1">
<%--       <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onclick="buttonPush('decision');"> --%>
      <input type="button" value="<gsmsg:write key="man.final" />" id="kakuteibtn" class="btn_base1">
      </logic:notEqual>
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq210knback');">
    </td>
    </tr>

  </table>

</td>
</tr>
</table>

<!-- 回答済みかつ設問情報が変更されていない場合ダイアログ -->
<div id="enqAnswerReset" title="" style="display:none;">
  <p>
    <div>
       <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
       <b><gsmsg:write key="enq.enq210kn.05" /></b><br><br>
    </div>
  </p>
</div>

<!-- 回答済みかつ設問情報が変更されていた場合ダイアログ -->
<div id="enqAnswerDelete" title="" style="display:none;">
  <p>
    <div>
       <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>
       <b><gsmsg:write key="enq.enq210kn.06" /></b><br><br>
    </div>
  </p>
</div>

<!-- 処理中 -->
<div id="loading_pop" title="" style="display:none">
  <table width="100%" height="100%">
    <tr>
      <td class="loading_area" valign="middle" width="110%">
        <img src="../smail/images/ajax-loader.gif" />
      </td>
    </tr>
  </table>
</div>


</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>