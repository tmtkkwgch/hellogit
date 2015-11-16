<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>
<%@ page import="jp.groupsession.v2.enq.enq110.Enq110Const" %>
<%@ page import="jp.groupsession.v2.enq.enq210.Enq210Form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js" />
<script type="text/javascript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enqDelCheck.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq110.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css" />
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>
<body class="body_03" onload="initImageView('enqImgName');" >
<html:form styleId="enqForm" action="/enquete/enq110" >
<input type="hidden" name="CMD" value="">
<input type="hidden" name="enq110DownloadFlg" value="">
<input type="hidden" name="enq110BinSid" value="">
<input type="hidden" name="enq110PreTempDirKbn" value="">
<input type="hidden" name="enq110TempDir" value="">

<html:hidden property="cmd" />
<html:hidden property="ansEnqSid" />
<html:hidden property="enq210editMode" />
<html:hidden property="enq110DspMode" />
<html:hidden property="enq110queDate" />
<html:hidden property="enq210DescText" />
<input type="hidden" name="enq210DelAnsFlg" value="false">

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq210_hiddenParams.jsp" %>
<logic:notEmpty name="enq110Form" property="enq230selectEnqSid">
  <logic:iterate id="sv230SelectEnqSid" name="enq110Form" property="enq230selectEnqSid">
    <input type="hidden" name="enq230selectEnqSid" value="<bean:write name='sv230SelectEnqSid' />" >
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110Form" property="enq230priority">
  <logic:iterate id="sv230Priority" name="enq110Form" property="enq230priority">
    <input type="hidden" name="enq230priority" value="<bean:write name='sv230Priority' />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq110Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq110Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110Form" property="enq010status">
<logic:iterate id="svStatus" name="enq110Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq110Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq110Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq110Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq110Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq110Form" property="enq210answerList">
<logic:iterate id="answerUser" name="enq110Form" property="enq210answerList">
  <input type="hidden" name="enq210answerList" value="<bean:write name="answerUser" />">
</logic:iterate>
</logic:notEmpty>

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<html:hidden property="tempClickBtn" />
<html:hidden property="answerDataReset" />

<!-- HEADER -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<bean:define id="enq210editMode" name="enq110Form" property="enq210editMode" type="java.lang.Integer" />
<% int editMode = enq210editMode; %>
<% if (editMode == Enq210Form.EDITMODE_TEMPLATE) { %>
  <input type="hidden" name="helpPrm" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>">
<% } else { %>
  <input type="hidden" name="helpPrm" value='<bean:write name="enq110Form" property="enq110DspMode" />'>
<% } %>

<table align="center" cellpadding="0" cellspacing="5" border="0" width="70%">
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
    <logic:equal name="enq110Form" property="enq110DspMode" value="0">
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq110.01" /> ]</td>
    </logic:equal>
    <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="cmn.preview" /> ]</td>
    </logic:notEqual>
    <td width="100%" class="header_white_bg">
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tbody><tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="enq110Form" property="enq110DspMode" value="<%= String.valueOf(Enq110Const.DSP_MODE_ANSWER) %>">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('enq110answer');">
      </logic:equal>
      <logic:equal name="enq110Form" property="enq110DspMode" value="<%= String.valueOf(Enq110Const.DSP_MODE_PREVIEW) %>">
        <logic:equal name="enq110Form" property="enq210editMode" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>" >
          <input type="button" value="<gsmsg:write key='man.final' />" class="btn_base1" onclick="buttonPush('enq110commit');">
        </logic:equal>
        <logic:notEqual name="enq110Form" property="enq210editMode" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>" >
        <input type="button" value="<gsmsg:write key='enq.05' />" id="kakuteibtn" class="btn_add_n1">
        </logic:notEqual>
      </logic:equal>
      <input type="button" value="<gsmsg:write key='cmn.back2' />" class="btn_back_n1" onclick="buttonPush('enq110back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </tbody></table>

    <!-- エラーメッセージ -->
    <div style="text-align:left; padding-top:5px;">
    <html:errors/>
    </div>

  </td>
  </tr>
  </table>

</td>
</tr>


<!-- BODY -->
<tr>
  <!-- 基本情報 タイトル -->
  <td width="99%">
    <table width="100%" cellspacing="0" cellpadding="5">
    <tr>
      <td width="1%"><img src="../enquete/images/enquete_title.gif" alt="<gsmsg:write key='enq.plugin' />"></td>
      <td width="99%" class="text_bb4 enq_title" style="padding-left: 10px;"><bean:write name="enq110Form" property="enq110Title" /></td>
    </tr>
    </table>
  </td>
</tr>

<tr>
<td colspan="2" width="100%" align="center" class="wrap_table">

<table class="clear_table" width="100%" cellspacing="0" cellpading="5">
  <tr>

    <td class="content_area">

      <!-- 基本情報 -->
      <table cellpadding="5" cellspacing="0" width="100%" class="tl0" align="center">
      <tbody>

        <!-- 基本情報 重要度 -->
        <tr>
          <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.24" /></td>
          <td width="25%" class="td_type20" nowrap>
            <logic:equal name="enq110Form" property="enq110PriKbn" value="<%= String.valueOf(GSConstEnquete.JUUYOU_0) %>">
              <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key='enq.33' />">
              <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key='enq.33' />">
              <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key='enq.33' />">
            </logic:equal>
            <logic:equal name="enq110Form" property="enq110PriKbn" value="<%= String.valueOf(GSConstEnquete.JUUYOU_1) %>">
              <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key='enq.34' />">
              <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key='enq.34' />">
              <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key='enq.34' />">
            </logic:equal>
            <logic:equal name="enq110Form" property="enq110PriKbn" value="<%= String.valueOf(GSConstEnquete.JUUYOU_2) %>">
              <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key='enq.35' />">
              <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key='enq.35' />">
              <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key='enq.35' />">
            </logic:equal>
          </td>
          <!-- 基本情報 発信者 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.25" /></td>
          <td width="52%" class="td_type20" nowrap>
            <bean:define id="sdFlg" name="enq110Form" property="enq110SendNameDelFlg" type="java.lang.Boolean" />
            <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
              <logic:notEqual name="enq110Form" property="enq210editMode" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>" >
                <bean:write name="enq110Form" property="enq110SendName" />
              </logic:notEqual>
            </span>
          </td>
        </tr>

        <!-- 基本情報 アンケート内容 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.26" /></td>
          <td colspan="3" class="td_type20">
          <span class="text_base2">
            <logic:equal name="enq110Form" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
              <logic:equal name="enq110Form" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                  <img src='../enquete/enq110.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110Form" property="ansEnqSid" />&enq110BinSid=<bean:write name="enq110Form" property="enq110AttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                </logic:equal>
                <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                  <img src='../enquete/enq110.do?CMD=getPreTempFile&enq110BinSid=<bean:write name="enq110Form" property="enq110AttachId" />&enq110PreTempDirKbn=0' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                </logic:notEqual>
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='enq110Form' property='enq110AttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 0);">
                        <span class="text_link"><bean:write name="enq110Form" property="enq110AttachName" /><bean:write name="enq110Form" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>
            <logic:equal name="enq110Form" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
              <logic:equal name="enq110Form" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='enq110Form' property='enq110AttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 0);">
                        <span class="text_link"><bean:write name="enq110Form" property="enq110AttachName" /><bean:write name="enq110Form" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>

            <bean:write name="enq110Form" property="enq110Desc" filter="false" />

            <logic:equal name="enq110Form" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
              <logic:equal name="enq110Form" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                  <img src='../enquete/enq110.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110Form" property="ansEnqSid" />&enq110BinSid=<bean:write name="enq110Form" property="enq110AttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                </logic:equal>
                <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                  <img src='../enquete/enq110.do?CMD=getPreTempFile&enq110BinSid=<bean:write name="enq110Form" property="enq110AttachId" />&enq110PreTempDirKbn=0' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                </logic:notEqual>
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='enq110Form' property='enq110AttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 0);">
                        <span class="text_link"><bean:write name="enq110Form" property="enq110AttachName" /><bean:write name="enq110Form" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>
            <logic:equal name="enq110Form" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
              <logic:equal name="enq110Form" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='enq110Form' property='enq110AttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 0);">
                        <span class="text_link"><bean:write name="enq110Form" property="enq110AttachName" /><bean:write name="enq110Form" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>
          </span>
          </td>
        </tr>

        <!-- 基本情報 回答期限 -->
        <logic:notEqual name="enq110Form" property="enq210editMode" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>" >
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
          <td class="td_type20" nowrap>
            <span class="text_base2"><bean:write name="enq110Form" property="enq110ResEnd" /></span>
          </td>
          <!-- 基本情報 結果公開期限 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
          <td class="td_type20" nowrap>
        <bean:define id="ansOpen" name="enq110Form" property="enq110AnsOpen" type="java.lang.Integer" />
        <% if (ansOpen == GSConstEnquete.EMN_ANS_OPEN_PUBLIC) {%>
            <span class="text_base2"><bean:write name="enq110Form" property="enq110AnsPubStr" /></span>
            &nbsp;～&nbsp;
            <logic:empty name="enq110Form" property="enq110OpenEnd">
            <gsmsg:write key="main.man200.9" />
            </logic:empty>
            <logic:notEmpty name="enq110Form" property="enq110OpenEnd">
            <span class="text_base2"><bean:write name="enq110Form" property="enq110OpenEnd" /></span>
            </logic:notEmpty>
          </td>
         <% } else { %>
          <gsmsg:write key="cmn.private" />
         <% } %>
        </tr>
        </logic:notEqual>

        <!-- 基本情報 注意事項 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.27" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <bean:define id="anony" name="enq110Form" property="enq110Anony" type="java.lang.Integer" />
              <bean:define id="ansOpen" name="enq110Form" property="enq110AnsOpen" type="java.lang.Integer" />
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

      <img src="../common/images/spacer.gif" width="1" height="15" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      <!-- 回答部 -->
        <logic:notEmpty name="enq110Form" property="enq110QueList">
        <% String[] quePrmName = {"emnSid", "eqmSeq", "eqmDspSec", "eqmQueKbn", "eqmRequire",
                                  "eqmRngStrNum", "eqmRngEndNum", "eqmRngStrDat", "eqmRngEndDat", "eqmQueSec",
                                  "eqsSeq", "eqsDspName", "eqmOther", "eqmUnitNum"}; %>
        <% String[] ansPrmName = {"eqmAnsText", "eqmAnsTextarea", "eqmAnsNum", "eqmSelectAnsYear", "eqmSelectAnsMonth", "eqmSelectAnsDay", "eqmSelOther", "eqmSelRbn", "eqmSelChk"}; %>
        <% int qnoAuto = 1; %>
        <logic:iterate id="mainList" name="enq110Form" property="enq110QueList" indexId="mIdx" scope="request">

        <% String mIndex = String.valueOf(mIdx); %>
        <% String formName = "enq110QueList[" + mIndex + "]."; %>

        <!-- パラメータを保持 -->
        <html:hidden property="<%= formName + quePrmName[0] %>" />
        <html:hidden property="<%= formName + quePrmName[1] %>" />
        <html:hidden property="<%= formName + quePrmName[2] %>" />
        <html:hidden property="<%= formName + quePrmName[3] %>" />
        <html:hidden property="<%= formName + quePrmName[4] %>" />
        <html:hidden property="<%= formName + quePrmName[5] %>" />
        <html:hidden property="<%= formName + quePrmName[6] %>" />
        <html:hidden property="<%= formName + quePrmName[7] %>" />
        <html:hidden property="<%= formName + quePrmName[8] %>" />
        <html:hidden property="<%= formName + quePrmName[9] %>" />
        <html:hidden property="<%= formName + quePrmName[12] %>" />
        <html:hidden property="<%= formName + quePrmName[13] %>" />

        <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_COMMENT) %>" >
        <!-- コメント -->
        <table width="100%" class="tl0" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
          <td>
            <logic:equal name="mainList" property="eqmLineKbn" value="<%= String.valueOf(GSConstEnquete.COMMENT_LINE_TOP) %>">
            <div class="btm-border-dot">&nbsp;</div>
            </logic:equal>
            <logic:equal name="mainList" property="eqmLineKbn" value="<%= String.valueOf(GSConstEnquete.COMMENT_LINE_UPDOWN) %>">
            <div class="btm-border-dot">&nbsp;</div>
            </logic:equal>
            <div class="text_comment">
              <table width="100%" class="table_answer" cellpadding="5" cellspacing="0" border="0">
                <tr>
                  <td align="left" valign="top">
                    <span style="font-size: 90%;">
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                        <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110Form" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                        </logic:equal>
                        <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getPreTempFile&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />&enq110TempDir=<bean:write name="mainList" property="eqmAttachDir" />&enq110PreTempDirKbn=1' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                        </logic:notEqual>
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                              </logic:equal>
                              <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                              </logic:notEqual>
                                <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                              </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                              </logic:equal>
                              <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                              </logic:notEqual>
                                <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                              </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>

                    <bean:write name="mainList" property="eqmDesc" filter="false" />

                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                        <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110Form" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                        </logic:equal>
                        <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getPreTempFile&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />&enq110TempDir=<bean:write name="mainList" property="eqmAttachDir" />&enq110PreTempDirKbn=1' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                        </logic:notEqual>
                        <table cellpadding="0" border="0">
                            <tr>
                              <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                              <td class="td_temp_link" align="left" valign="middle">
                                <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                                <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                                </logic:equal>
                                <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                                <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                                </logic:notEqual>
                                  <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                                </a>
                              </td>
                            </tr>
                          </table>
                      </logic:equal>
                    </logic:equal>
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                              </logic:equal>
                              <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                              </logic:notEqual>
                                <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                              </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>
                    </span>
                  </td>
                </tr>
              </table>
            </div>
            <logic:equal name="mainList" property="eqmLineKbn" value="<%= String.valueOf(GSConstEnquete.COMMENT_LINE_BOTTOM) %>">
            <div class="btm-border-dot">&nbsp;</div>
            </logic:equal>
            <logic:equal name="mainList" property="eqmLineKbn" value="<%= String.valueOf(GSConstEnquete.COMMENT_LINE_UPDOWN) %>">
            <div class="btm-border-dot">&nbsp;</div>
            </logic:equal>
            <img src="../common/images/spacer.gif" width="1" height="2" border="0" alt="<gsmsg:write key='cmn.spacer' />">
          </td>
        </tr>
        </tbody>
        </table>
        </logic:equal>

        <logic:notEqual name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_COMMENT) %>" >
        <!-- コメント以外 -->
        <table width="100%" class="tl0 table_ans2" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
          <td class="td_type1_enq">
            <div class="text_bg_index" style="padding-left:0; padding-right:0;">
              <table width="100%" cellpadding="5" cellspacing="0" border="0">
                <tr>
                  <td width="1%" class="text_question" align="left" valign="top" nowrap><gsmsg:write key="enq.37" /></td>
                  <td width="60" class="text_question" align="left" valign="top">
                    <logic:equal name="enq110Form" property="enq210queSeqType" value="1">
                      <%= String.valueOf(qnoAuto) %><% qnoAuto++; %>
                    </logic:equal>
                    <logic:equal name="enq110Form" property="enq210queSeqType" value="0">
                      <bean:write name="mainList" property="eqmQueSec" />
                    </logic:equal>
                  </td>
                  <td class="text_question" align="left" valign="top"><bean:write name="mainList" property="eqmQuestion" /></td>
                </tr>
                <tr>
                  <td rowspan="2" colspan="2" class="text_question" align="left" valign="top">
                    <logic:equal name="mainList" property="<%= quePrmName[4] %>" value="<%= String.valueOf(GSConstEnquete.REQUIRE_ON) %>" >
                      <span style="color: red"><gsmsg:write key="enq.28" /></span>
                    </logic:equal>
                  </td>
                  <td align="left" class="text_question" valign="top">
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                        <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110Form" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        </logic:equal>
                        <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getPreTempFile&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />&enq110TempDir=<bean:write name="mainList" property="eqmAttachDir" />&enq110PreTempDirKbn=1' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        </logic:notEqual>
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                              </logic:equal>
                              <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                              </logic:notEqual>
                                <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                              </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                            <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                            <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                            </logic:equal>
                            <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                            <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                            </logic:notEqual>
                              <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                            </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>

                    <bean:write name="mainList" property="eqmDesc" filter="false" />

                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                        <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110Form" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        </logic:equal>
                        <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                          <img src='../enquete/enq110.do?CMD=getPreTempFile&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />&enq110TempDir=<bean:write name="mainList" property="eqmAttachDir" />&enq110PreTempDirKbn=1' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        </logic:notEqual>
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                              </logic:equal>
                              <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                              </logic:notEqual>
                                <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                              </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <logic:equal name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, 1);">
                              </logic:equal>
                              <logic:notEqual name="enq110Form" property="enq110DspMode" value="0">
                              <a href="javascript:void(0);" onclick="fileLinkClick(<bean:write name='mainList' property='eqmAttachId' />, <bean:write name="enq110Form" property="enq110DspMode" />, <bean:write name="mainList" property="eqmAttachDir" />, 1);">
                              </logic:notEqual>
                                <span class="text_link"><bean:write name="mainList" property="eqmAttachName" /><bean:write name="mainList" property="eqmAttachSize" /></span>
                              </a>
                            </td>
                          </tr>
                        </table>
                      </logic:equal>
                    </logic:equal>
                  </td>
                </tr>

                <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_INTEGER) %>" >
                <!-- 数値の最小値、最大値 -->
                <tr>
                  <td colspan="2" class="text_question" align="left" valign="top">
                    <logic:notEmpty name="mainList" property="eqmRngStrNum">
                      <logic:notEmpty name="mainList" property="eqmRngEndNum">
                        <gsmsg:write key="cmn.asterisk" /><bean:write name="mainList" property="<%= quePrmName[5] %>" />&nbsp;～&nbsp;<bean:write name="mainList" property="<%= quePrmName[6] %>" />&nbsp;<gsmsg:write key="enq.65" />
                      </logic:notEmpty>
                    </logic:notEmpty>
                  </td>
                </tr>
                </logic:equal>


                <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_DAY) %>" >
                <!-- 日付の最小値、最大値 -->
                <tr>
                  <td colspan="2" class="text_question" align="left" valign="top">
                    <logic:notEmpty name="mainList" property="eqmRngStrDat">
                      <logic:notEmpty name="mainList" property="eqmRngEndDat">
                        <gsmsg:write key="cmn.asterisk" /><bean:write name="mainList" property="<%= quePrmName[7] %>" />&nbsp;～&nbsp;<bean:write name="mainList" property="<%= quePrmName[8] %>" />&nbsp;<gsmsg:write key="enq.65" />
                      </logic:notEmpty>
                    </logic:notEmpty>
                  </td>
                </tr>
                </logic:equal>

              </table>
            </div>
            <div class="text_answer">
              <table width="90%" class="table_answer" cellpadding="5" cellspacing="0" border="0">
                <tr>
                  <td width="10%" align="center" valign="top" nowrap><gsmsg:write key="enq.52" /></td>
                  <td width="80%" align="left" valign="top">

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_SINGLE) %>" >
                    <!-- 単一 -->
                      <logic:notEmpty name="mainList" property="eqmSubList">
                        <logic:iterate id="subList" name="mainList" property="eqmSubList" indexId="sIdx" >
                          <% String sIndex = String.valueOf(sIdx); %>
                          <% String sFormName = "eqmSubList[" + sIndex + "]."; %>
                          <% String radio = "radio_" + mIndex + "_" + sIndex; %>
                          <% String eqsSeq = "eqsSeq"; %>

                          <html:hidden property="<%= formName + sFormName + quePrmName[10] %>" />
                          <html:hidden property="<%= formName + sFormName + quePrmName[11] %>" />

                          <logic:notEqual name="subList" property="eqsSeq" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>">
                            <bean:define id="rbnVal" name="subList" property="eqsSeq"/>
                            <% String val = String.valueOf(rbnVal); %>
                            <div class="enqSelLabel">
                              <label for="<%= radio %>" class="enqSelLabel"><span class="text_question2">
                                <html:radio property="<%= formName + ansPrmName[7] %>" value="<%= val %>" styleId="<%= radio %>" styleClass="enqSelLabel"/><bean:write name="subList" property="eqsDspName" />
                              </span></label>
                            </div>
                          </logic:notEqual>
                          <logic:equal name="subList" property="eqsSeq" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>">
                            <br><div class="enqSelLabel">
                              <span class="text_question2">
                                <logic:equal name="mainList" property="eqmOther" value="<%= String.valueOf(GSConstEnquete.OTHER_TEXT) %>">
                                  <html:radio property="<%= formName + ansPrmName[7] %>" value="-1" styleId="<%= radio %>" styleClass="enqSelLabel" /><label for="<%= radio %>" style="vertical-align:top;"><gsmsg:write key="cmn.other" /></label>  <html:text property="<%= formName + ansPrmName[6] %>"  styleClass="ans_text_other" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_TEXT) %>" />
                                </logic:equal>
                                <logic:equal name="mainList" property="eqmOther" value="<%= String.valueOf(GSConstEnquete.OTHER_TEXTAREA) %>">
                                  <html:radio property="<%= formName + ansPrmName[7] %>" value="-1" styleId="<%= radio %>" styleClass="enqSelLabel" /><label for="<%= radio %>" style="vertical-align:top;"><gsmsg:write key="cmn.other" /></label>  <html:textarea property="<%= formName + ansPrmName[6] %>" styleClass="ans_textarea_other" />
                                </logic:equal>
                              </span>
                            </div>
                          </logic:equal>

                        </logic:iterate>
                      </logic:notEmpty>
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_MULTIPLE) %>" >
                    <!-- 複数 -->
                      <logic:notEmpty name="mainList" property="eqmSubList">
                        <logic:iterate id="subList" name="mainList" property="eqmSubList" indexId="sIdx" >
                          <% String sIndex = String.valueOf(sIdx); %>
                          <% String sFormName = "eqmSubList[" + sIndex + "]."; %>
                          <% String check = "check_" + mIndex + "_" + sIndex; %>
                          <% String eqsSeq = "eqsSeq"; %>

                          <html:hidden property="<%= formName + sFormName + quePrmName[10] %>" />
                          <html:hidden property="<%= formName + sFormName + quePrmName[11] %>" />

                          <logic:notEqual name="subList" property="eqsSeq" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>">
                            <bean:define id="chkVal" name="subList" property="eqsSeq"/>
                            <% String val = String.valueOf(chkVal); %>
                            <div class="enqSelLabel">
                              <label for="<%= check %>"><span class="text_question2">
                              <html:multibox property="<%= formName + ansPrmName[8] %>" value="<%= val %>" styleId="<%= check %>"/><bean:write name="subList" property="eqsDspName" />
                              </span></label>
                            </div>
                          </logic:notEqual>
                          <logic:equal name="subList" property="eqsSeq" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>">
                            <br><div class="enqSelLabel">
                              <span class="text_question2">
                                <logic:equal name="mainList" property="eqmOther" value="<%= String.valueOf(GSConstEnquete.OTHER_TEXT) %>">
                                  <html:multibox property="<%= formName + ansPrmName[8] %>" value="-1" styleId="<%= check %>" styleClass="enqSelLabel" /><label for="<%= check %>" style="vertical-align:top;"><gsmsg:write key="cmn.other" /></label>  <html:text property="<%= formName + ansPrmName[6] %>" styleClass="ans_text_other" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_TEXT) %>" />
                                </logic:equal>
                                <logic:equal name="mainList" property="eqmOther" value="<%= String.valueOf(GSConstEnquete.OTHER_TEXTAREA) %>">
                                  <html:multibox property="<%= formName + ansPrmName[8] %>" value="-1" styleId="<%= check %>" styleClass="enqSelLabel" /><label for="<%= check %>" style="vertical-align:top;"><gsmsg:write key="cmn.other" /></label>  <html:textarea property="<%= formName + ansPrmName[6] %>" styleClass="ans_textarea_other" />
                                </logic:equal>
                              </span>
                            </div>
                          </logic:equal>

                        </logic:iterate>
                      </logic:notEmpty>
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXT) %>" >
                    <!-- テキスト -->
                      <html:text property="<%= formName + ansPrmName[0] %>" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_TEXT) %>" styleClass="ans_text" />
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXTAREA) %>" >
                    <!-- 複数行 -->
                      <html:textarea property="<%= formName + ansPrmName[1] %>" styleClass="text_base ans_textarea" />
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_INTEGER) %>" >
                    <!-- 数値 -->
                      <html:text property="<%= formName + ansPrmName[2] %>" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_INT) %>" styleClass="ans_text_num" /> <bean:write name="mainList" property="eqmUnitNum" />
                    </logic:equal>
                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_DAY) %>" >
                    <!-- 日付 -->
                      <% String selYear  = "selYear_"  + String.valueOf(mIdx); %>
                      <% String selMonth = "selMonth_" + String.valueOf(mIdx); %>
                      <% String selDay   = "selDay_"   + String.valueOf(mIdx); %>
                      <% String calBtn   = "calBtn_"   + String.valueOf(mIdx); %>
                      <html:select property="<%= formName + ansPrmName[3] %>" styleId='<%= selYear %>'>
                        <option value="-1"><gsmsg:write key='cmn.notset' /></option>
                        <html:optionsCollection name="enq110Form" property="enq110YearLabel" value="value" label="label" />
                      </html:select>
                      <html:select property="<%= formName + ansPrmName[4] %>" styleId='<%= selMonth %>'>
                        <option value="-1"><gsmsg:write key='cmn.notset' /></option>
                        <html:optionsCollection name="enq110Form" property="enq110MonthLabel" value="value" label="label" />
                      </html:select>
                      <html:select property="<%= formName + ansPrmName[5] %>" styleId='<%= selDay %>'>
                        <option value="-1"><gsmsg:write key='cmn.notset' /></option>
                        <html:optionsCollection name="enq110Form" property="enq110DayLabel" value="value" label="label" />
                      </html:select>
                    </logic:equal>
                  </td>
                </tr>
              </table>
            </div>
          </td>
        </tr>
        </tbody>
        </table>
        </logic:notEqual>

        </logic:iterate>
        </logic:notEmpty>
    </td>

  </tr>
  <tr>
    <td align="right">
      <logic:equal name="enq110Form" property="enq110DspMode" value="0">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('enq110answer');">
      </logic:equal>
      <logic:equal name="enq110Form" property="enq110DspMode" value="<%= String.valueOf(Enq110Const.DSP_MODE_PREVIEW) %>">
        <logic:equal name="enq110Form" property="enq210editMode" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>" >
          <input type="button" value="<gsmsg:write key='man.final' />" class="btn_base1" onclick="buttonPush('enq110commit');">
        </logic:equal>
        <logic:notEqual name="enq110Form" property="enq210editMode" value="<%= String.valueOf(Enq210Form.EDITMODE_TEMPLATE) %>" >
        <input type="button" value="<gsmsg:write key='enq.05' />" id="kakuteibtn" class="btn_add_n1">
        </logic:notEqual>
      </logic:equal>
      <input type="button" value="<gsmsg:write key='cmn.back2' />" class="btn_back_n1" onclick="buttonPush('enq110back');">
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
