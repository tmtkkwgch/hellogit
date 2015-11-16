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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js" />
<script type="text/javascript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enquete.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css" />
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>
<body class="body_03" onload="initImageView('enqImgName');" onunload="windowClose();">
<html:form action="/enquete/enq110kn" >
<input type="hidden" name="CMD" value="">
<input type="hidden" name="enq110DownloadFlg" value="">
<input type="hidden" name="enq110BinSid" value="">
<input type="hidden" name="enq110PreTempDirKbn" value="">
<input type="hidden" name="enq110TempDir" value="">
<html:hidden property="cmd" />
<html:hidden property="ansEnqSid" />
<html:hidden property="enq110DspMode" />
<html:hidden property="enq110queDate" />
<html:hidden property="enq110Title" />
<html:hidden property="enq110knSvBackScreen" />

<!-- 回答入力画面でのパラメータ保持 -->
<logic:notEmpty name="enq110knForm" property="enq110QueListToList">
  <logic:iterate id="mainList" name="enq110knForm" property="enq110QueListToList" indexId="mIdx">
    <% String mIndex = String.valueOf(mIdx); %>
    <% String formName = "enq110QueList[" + mIndex + "]."; %>
    <% String[] quePrmName = {"emnSid", "eqmSeq", "eqmDspSec", "eqmQueKbn", "eqmRequire",
                              "eqmRngStrNum", "eqmRngEndNum", "eqmRngStrDat", "eqmRngEndDat", "eqmQueSec",
                              "", "eqsSeq", "eqsDspName", "eqmUnitNum"}; %>
    <% String[] ansPrmName = {"eqmAnsText", "eqmAnsTextarea", "eqmAnsNum", "eqmSelectAnsYear", "eqmSelectAnsMonth",
                              "eqmSelectAnsDay", "eqmSelOther", "eqmSelRbn", "eqmSelChk", "eqmSelectAnsDate"}; %>
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
    <html:hidden property="<%= formName + quePrmName[13] %>" />

    <html:hidden property="<%= formName + ansPrmName[0] %>" />
    <html:hidden property="<%= formName + ansPrmName[1] %>" />
    <html:hidden property="<%= formName + ansPrmName[2] %>" />
    <html:hidden property="<%= formName + ansPrmName[3] %>" />
    <html:hidden property="<%= formName + ansPrmName[4] %>" />
    <html:hidden property="<%= formName + ansPrmName[5] %>" />
    <html:hidden property="<%= formName + ansPrmName[6] %>" />
    <html:hidden property="<%= formName + ansPrmName[7] %>" />
    <logic:notEmpty name="mainList" property="eqmSelChk">
      <logic:iterate id="chkList" name="mainList" property="eqmSelChk" indexId="chkIdx">
        <input type="hidden" name="<%= formName + ansPrmName[8] %>" value='<bean:write name="chkList"/>'>
      </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="mainList" property="eqmSubList">
      <logic:iterate id="subList" name="mainList" property="eqmSubList" indexId="subIdx">
        <% String sFormName = "eqmSubList[" + subIdx + "]."; %>
        <input type="hidden" name="<%= formName + sFormName + quePrmName[11] %>" value='<bean:write name="subList" property="eqsSeq"/>'>
        <input type="hidden" name="<%= formName + sFormName + quePrmName[12] %>" value='<bean:write name="subList" property="eqsDspName"/>'>
      </logic:iterate>
    </logic:notEmpty>
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/enquete/jsp/enq210_hiddenParams.jsp" %>

<logic:notEmpty name="enq110knForm" property="enq010priority">
<logic:iterate id="svPriority" name="enq110knForm" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110knForm" property="enq010status">
<logic:iterate id="svStatus" name="enq110knForm" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110knForm" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq110knForm" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110knForm" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq110knForm" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110knForm" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq110knForm" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq110knForm" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq110knForm" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq110knForm" property="enq210answerList">
<logic:iterate id="answerUser" name="enq110knForm" property="enq210answerList">
  <input type="hidden" name="enq210answerList" value="<bean:write name="answerUser" />">
</logic:iterate>
</logic:notEmpty>

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<!-- HEADER -->
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value='<bean:write name="enq110knForm" property="enq110DspMode" />'>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center" colspan="2">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key='enq.plugin' />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
    <logic:equal name="enq110knForm" property="enq110DspMode" value="0">
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.enq110kn.01" /> ]</td>
    </logic:equal>
    <logic:notEqual name="enq110knForm" property="enq110DspMode" value="0">
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>[ <gsmsg:write key="enq.39" /> ]</td>
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
      <logic:equal name="enq110knForm" property="enq110DspMode" value="0">
      <input type="button" value="<gsmsg:write key='enq.23' />" class="btn_henshin_n1" onclick="buttonPush('enq110kncommit');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key='cmn.back2' />" class="btn_back_n1" onclick="buttonPush('enq110knback');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </tbody></table>

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <!-- 回答時の確認メッセージ -->
    <logic:equal name="enq110knForm" property="enq110DspMode" value="0">
    <div style="padding-top: 10px; padding-bottom:10px;"><gsmsg:write key="enq.72" /></div>
    </logic:equal>

  </td>
  </tr>
  </table>
</td>
</tr>

<!-- BODY -->
<tr>
  <!-- 基本情報 タイトル -->
  <td width="99%">
    <table width="100%" cellspacing="0">
    <tr>
      <td width="1%"><img src="../enquete/images/enquete_title.gif" alt="<gsmsg:write key='enq.plugin' />"/></td>
      <td width="99%" class="text_bb4 enq_title" style="padding-left: 10px;"><bean:write name="enq110knForm" property="enq110Title" /></td>
    </tr>
    </table>
  </td>
</tr>

<tr>
<td colspan="2" width="100%" align="center" class="wrap_table">

<table class="clear_table" width="100%">
  <tr>

    <td class="content_area">

      <!-- 基本情報 -->
      <table cellpadding="5" width="100%" class="tl0">
      <tbody>

        <!-- 基本情報 重要度 -->
        <tr>
          <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.24" /></td>
          <td width="25%" class="td_type20" nowrap>
            <logic:equal name="enq110knForm" property="enq110PriKbn" value="<%= String.valueOf(GSConstEnquete.JUUYOU_0) %>">
              <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key='enq.33' />">
              <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key='enq.33' />">
              <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key='enq.33' />">
            </logic:equal>
            <logic:equal name="enq110knForm" property="enq110PriKbn" value="<%= String.valueOf(GSConstEnquete.JUUYOU_1) %>">
              <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key='enq.34' />">
              <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key='enq.34' />">
              <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key='enq.34' />">
            </logic:equal>
            <logic:equal name="enq110knForm" property="enq110PriKbn" value="<%= String.valueOf(GSConstEnquete.JUUYOU_2) %>">
              <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key='enq.35' />">
              <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key='enq.35' />">
              <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key='enq.35' />">
            </logic:equal>
          </td>
          <!-- 基本情報 発信者 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.25" /></td>
          <td colspan="3" width="52%" class="td_type20" nowrap>
            <bean:define id="sdFlg" name="enq110knForm" property="enq110SendNameDelFlg" type="java.lang.Boolean" />
            <bean:define id="sdUserKbn" name="enq110knForm" property="enq110SendKbn" type="java.lang.Integer" />
            <% boolean senderLinkFlg = (!sdFlg && sdUserKbn.intValue() == jp.groupsession.v2.enq.enq110.Enq110Const.SENDER_KBN_USER); %>
            <span class="text_base2<% if (sdFlg) { %> text_deluser_enq<% } %>">
              <% if (senderLinkFlg) { %><a href="javaScript:void(0);" onClick="openUserInfoWindow(<bean:write name="enq110knForm" property="enq110SendSid" />);" class="answer"><% } %>
              <bean:write name="enq110knForm" property="enq110SendName" />
              <% if (senderLinkFlg) { %></a><% } %>
            </span>
          </td>
        </tr>

        <!-- 基本情報 アンケート内容 -->
        <tr>
          <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.26" /></td>
          <td colspan="3" class="td_type20">
          <span class="text_base2">
            <logic:equal name="enq110knForm" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
              <logic:equal name="enq110knForm" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                <img src='../enquete/enq110kn.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110knForm" property="ansEnqSid" />&enq110BinSid=<bean:write name="enq110knForm" property="enq110AttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='enq110knForm' property='enq110AttachId' />);">
                        <span class="text_link"><bean:write name="enq110knForm" property="enq110AttachName" /><bean:write name="enq110knForm" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>
            <logic:equal name="enq110knForm" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
              <logic:equal name="enq110knForm" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='enq110knForm' property='enq110AttachId' />);">
                        <span class="text_link"><bean:write name="enq110knForm" property="enq110AttachName" /><bean:write name="enq110knForm" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>

            <bean:write name="enq110knForm" property="enq110Desc" filter="false" />

            <logic:equal name="enq110knForm" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
              <logic:equal name="enq110knForm" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                <img src='../enquete/enq110kn.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110knForm" property="ansEnqSid" />&enq110BinSid=<bean:write name="enq110knForm" property="enq110AttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge"><br>
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='enq110knForm' property='enq110AttachId' />);">
                        <span class="text_link"><bean:write name="enq110knForm" property="enq110AttachName" /><bean:write name="enq110knForm" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>
            <logic:equal name="enq110knForm" property="enq110AttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_FILE) %>" >
              <logic:equal name="enq110knForm" property="enq110AttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_BOTTOM) %>" >
                <table cellpadding="0" border="0">
                  <tr>
                    <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                    <td class="td_temp_link" align="left" valign="middle">
                      <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='enq110knForm' property='enq110AttachId' />);">
                        <span class="text_link"><bean:write name="enq110knForm" property="enq110AttachName" /><bean:write name="enq110knForm" property="enq110AttachSize" /></span>
                      </a><br>
                    </td>
                  </tr>
                </table>
              </logic:equal>
            </logic:equal>
          </span>
          </td>
        </tr>

        <tr>
          <!-- 基本情報 回答期限 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
          <td class="td_type20" nowrap>
            <span class="text_base2"><bean:write name="enq110knForm" property="enq110ResEnd" /></span>
          </td>
          <!-- 基本情報 結果公開期限 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
          <td class="td_type20" nowrap>
        <bean:define id="ansOpen" name="enq110knForm" property="enq110AnsOpen" type="java.lang.Integer" />
        <% if (ansOpen == GSConstEnquete.EMN_ANS_OPEN_PUBLIC) {%>
            <span class="text_base2"><bean:write name="enq110knForm" property="enq110AnsPubStr" /></span>
            &nbsp;～&nbsp;
            <logic:empty name="enq110knForm" property="enq110OpenEnd">
            <gsmsg:write key="main.man200.9" />
            </logic:empty>
            <logic:notEmpty name="enq110knForm" property="enq110OpenEnd">
            <span class="text_base2"><bean:write name="enq110knForm" property="enq110OpenEnd" /></span>
            </logic:notEmpty>
         <% } else { %>
          <gsmsg:write key="cmn.private" />
         <% } %>
          </td>
        </tr>

        <!-- 基本情報 注意事項 -->
        <tr>
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.27" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <bean:define id="anony" name="enq110knForm" property="enq110Anony" type="java.lang.Integer" />
              <bean:define id="ansOpen" name="enq110knForm" property="enq110AnsOpen" type="java.lang.Integer" />
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
        <logic:notEmpty name="enq110knForm" property="enq110knQueList">
        <% String[] quePrmName = {"emnSid", "eqmSeq", "eqmDspSec", "eqmQueKbn", "eqmRequire",
                                  "eqmRngStrNum", "eqmRngEndNum", "eqmRngStrDat", "eqmRngEndDat", "eqmQueSec"}; %>
        <% String[] ansPrmName = {"eqmAnsText", "eqmAnsTextarea", "eqmAnsNum", "eqmSelectAnsYear", "eqmSelectAnsMonth", "eqmSelectAnsDay", "eqmSelOther", "eqmSelRbn", "eqmSelChk"}; %>
        <logic:iterate id="mainList" name="enq110knForm" property="enq110knQueList" indexId="mIdx" scope="request">

        <% String mIndex = String.valueOf(mIdx); %>
        <% String knformName = "enq110knQueList[" + mIndex + "]."; %>

        <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_COMMENT) %>" >
        <!-- コメント -->
        <table width="100%" class="tl0" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
          <td colspan="4" style="padding:0;">
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
                        <img src='../enquete/enq110kn.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110knForm" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
                        <img src='../enquete/enq110kn.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110knForm" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
          <td colspan="4" class="td_type1_enq" style="padding:0;">
            <div class="text_bg_index" style="padding-left:0; padding-right:0;">
              <table width="100%" cellpadding="5" cellspacing="0" border="0">
                <tr>
                  <td width="1%" class="text_question" align="left" valign="top" nowrap><gsmsg:write key="enq.37" /></td>
                  <td width="60" class="text_question" align="left" valign="top"><bean:write name="mainList" property="eqmQueSec" /></td>
                  <td class="text_question" align="left" valign="top"><bean:write name="mainList" property="eqmQuestion" /></td>
                </tr>
                <tr>
                  <td rowspan="2" colspan="2" class="text_question" align="left" valign="top" nowrap>
                    <logic:equal name="mainList" property="<%= quePrmName[4] %>" value="<%= String.valueOf(GSConstEnquete.REQUIRE_ON) %>" >
                      <span style="color: red"><gsmsg:write key="enq.28" /></span>
                    </logic:equal>
                  </td>
                  <td align="left" class="text_question" valign="top">
                    <logic:equal name="mainList" property="eqmAttachKbn" value="<%= String.valueOf(GSConstEnquete.TEMP_IMAGE) %>" >
                      <logic:equal name="mainList" property="eqmAttachPos" value="<%= String.valueOf(GSConstEnquete.TEMP_POS_TOP) %>" >
                        <img src='../enquete/enq110kn.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110knForm" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
                        <img src='../enquete/enq110kn.do?CMD=getImageFile&ansEnqSid=<bean:write name="enq110knForm" property="ansEnqSid" />&enq110BinSid=<bean:write name="mainList" property="eqmAttachId" />' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
                              <a href="javascript:void(0);" onclick="fileLinkClickBin(<bean:write name='mainList' property='eqmAttachId' />);">
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
              <table width="80%" class="table_answer" cellpadding="5" cellspacing="0" border="0">
                <tr>
                  <td width="60" align="center" valign="top"><gsmsg:write key="enq.52" /></td>
                  <td align="left" valign="top">

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_SINGLE) %>" >
                    <!-- 単一 -->
                      <logic:notEqual name="mainList" property="eqmSelRbn" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>">
                        <bean:write name="mainList" property="eqmSelRbnName" />
                      </logic:notEqual>
                      <logic:equal name="mainList" property="eqmSelRbn" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>">
                        <gsmsg:write key="cmn.other" />[<bean:write name="mainList" property="eqmSelOther" filter="false"/>]
                      </logic:equal>
                      <logic:empty name="mainList" property="eqmSelRbn">
                        <gsmsg:write key="enq.38" />
                      </logic:empty>

                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_MULTIPLE) %>" >
                    <!-- 複数 -->
                      <logic:notEmpty name="mainList" property="eqmSelChkName">
                        <logic:iterate id="subList" name="mainList" property="eqmSelChkName">
                          <logic:notEqual name="subList" property="value" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>" >
                            <bean:write name="subList" property="label"/><br>
                          </logic:notEqual>
                          <logic:equal name="subList" property="value" value="<%= String.valueOf(GSConstEnquete.CHOICE_KBN_OTHER) %>" >
                            <gsmsg:write key="cmn.other" />[<bean:write name="mainList" property="eqmSelOther" filter="false"/>]
                          </logic:equal>
                        </logic:iterate>
                      </logic:notEmpty>
                      <logic:empty name="mainList" property="eqmSelChkName">
                        <logic:empty name="mainList" property="eqmSelOther">
                          <gsmsg:write key="enq.38" />
                        </logic:empty>
                      </logic:empty>
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXT) %>" >
                    <!-- テキスト -->
                      <bean:write name="mainList" property="<%= ansPrmName[0] %>" />
                      <logic:empty name="mainList" property="<%= ansPrmName[0] %>" >
                        <gsmsg:write key="enq.38" />
                      </logic:empty>
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXTAREA) %>" >
                    <!-- 複数行 -->
                      <bean:write name="mainList" property="<%= ansPrmName[1] %>" filter="false" />
                      <logic:empty name="mainList" property="<%= ansPrmName[1] %>" >
                        <gsmsg:write key="enq.38" />
                      </logic:empty>
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_INTEGER) %>" >
                    <!-- 数値 -->
                      <logic:notEmpty name="mainList" property="<%= ansPrmName[2] %>">
                        <bean:write name="mainList" property="<%= ansPrmName[2] %>" />&nbsp;<bean:write name="mainList" property="eqmUnitNum" />
                      </logic:notEmpty>
                      <logic:empty name="mainList" property="<%= ansPrmName[2] %>" >
                        <gsmsg:write key="enq.38" />
                      </logic:empty>
                    </logic:equal>

                    <logic:equal name="mainList" property="eqmQueKbn" value="<%= String.valueOf(GSConstEnquete.SYURUI_DAY) %>" >
                    <!-- 日付 -->
                      <logic:notEqual name="mainList" property="<%= ansPrmName[3] %>" value="-1">
                        <!--<bean:write name="mainList" property="<%= ansPrmName[3] %>" /><gsmsg:write key="cmn.year2" /><bean:write name="mainList" property="<%= ansPrmName[4] %>" /><gsmsg:write key="cmn.month" /><bean:write name="mainList" property="<%= ansPrmName[5] %>" /><gsmsg:write key="cmn.day" />-->
                        <bean:write name="mainList" property="eqmSelectAnsDate" />
                      </logic:notEqual>
                      <logic:equal name="mainList" property="<%= ansPrmName[3] %>" value="-1">
                        <gsmsg:write key="enq.38" />
                      </logic:equal>
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

      </tbody>
      </table>

  </tr>
  <tr>
    <td align="right">
      <logic:equal name="enq110knForm" property="enq110DspMode" value="0">
      <input type="button" value="<gsmsg:write key='enq.23' />" class="btn_henshin_n1" onclick="buttonPush('enq110kncommit');">
      </logic:equal>
      <input type="button" value="<gsmsg:write key='cmn.back2' />" class="btn_back_n1" onclick="buttonPush('enq110knback');">
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
