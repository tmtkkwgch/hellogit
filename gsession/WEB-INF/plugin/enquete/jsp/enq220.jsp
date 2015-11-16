<%@page import="jp.groupsession.v2.enq.enq210.Enq210Form"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.enq.GSConstEnquete" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script type="text/javascript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src='../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enq220.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../enquete/css/enquete.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script type="text/javascript" src="../portal/js/tiny_mce/tiny_mce.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../enquete/js/enqEditor.js?<%= GSConst.VERSION_PARAM %>"></script>

<title>[GroupSession] <gsmsg:write key="enq.plugin" /></title>
</head>
<body class="body_03" onload="changeAttached(<bean:write name="enq220Form" property="enq220AttachKbn" />); initImageView('enqImgName');">
<html:form action="/enquete/enq220">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="SEQ" value="">
<input type="hidden" name="enq210scrollQuestonFlg" value="1">
<input type="hidden" name="enq220QueDescText" value="">
<input type="hidden" name="enq220deleteRow" value="">

<html:hidden property="enq210queType" />
<html:hidden property="enq210queIndex" />
<html:hidden property="enq210editQueIndex" />
<html:hidden property="enq220editMode" />
<html:hidden property="enq220initFlg" />
<html:hidden property="enq220viewDetailFlg" />
<html:hidden property="enq220queId" />
<html:hidden property="enq220files" />
<html:hidden property="enq220AttachKbn" />
<html:hidden property="enq220scrollQuestonFlg" />
<html:hidden property="tempClickBtn" />


<%@ include file="/WEB-INF/plugin/enquete/jsp/enq210_hiddenParams.jsp" %>
<html:hidden property="enq210editMode" />

<logic:notEmpty name="enq220Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq220Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq010status">
<logic:iterate id="svStatus" name="enq220Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq220Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq220Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq220Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq220Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq220Form" property="enq210answerGroup">
<logic:iterate id="answerUser" name="enq220Form" property="enq210answerList">
  <input type="hidden" name="enq210answerList" value="<bean:write name="answerUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="enq220Form" property="enq230selectEnqSid">
  <logic:iterate id="sv230SelectEnqSid" name="enq220Form" property="enq230selectEnqSid">
    <input type="hidden" name="enq230selectEnqSid" value="<bean:write name='sv230SelectEnqSid' />" >
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq230priority">
  <logic:iterate id="sv230Priority" name="enq220Form" property="enq230priority">
    <input type="hidden" name="enq230priority" value='<bean:write name="sv230Priority" />'>
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq230svPriority">
<logic:iterate id="svPriority" name="enq220Form" property="enq230svPriority">
  <input type="hidden" name="enq230svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq220Form" property="enq230svStatus">
<logic:iterate id="svStatus" name="enq220Form" property="enq230svStatus">
  <input type="hidden" name="enq230svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value='<bean:write name="enq220Form" property="enq210queType" />'>

<bean:define id="enq210editMode" name="enq220Form" property="enq210editMode" type="java.lang.Integer" />
<% boolean enqTemplateFlg = enq210editMode.intValue() == Enq210Form.EDITMODE_TEMPLATE; %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="70%">
<tr>
<td width="100%" align="center" colspan="3">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../enquete/images/header_enquete_01.gif" border="0" alt="<gsmsg:write key="enq.plugin" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="enq.plugin" /></span></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap>
    <logic:equal name="enq220Form" property="enq220editMode" value="1">
    [ <gsmsg:write key="enq.enq220.04" /> ]
    </logic:equal>
    <logic:notEqual name="enq220Form" property="enq220editMode" value="1">
    [ <gsmsg:write key="enq.enq220.03" /> ]
    </logic:notEqual>
    </td>
    <td width="100%" class="header_white_bg"></td>
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
      <input type="button" value="O　K" class="btn_ok1" onclick="enq220Entry();">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq220back')">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.header' />"></td>
    </tr>
    </tbody></table>
  </td>
  </tr>
  </table>
</td>
</tr>


<!-- BODY -->
<logic:messagesPresent message="false">
<tr><td width="100%" style="margin-bottom: 10px 0px;" colspan="3"><html:errors /></td></tr>
</logic:messagesPresent>

<tr>
 <td width="100%">
   <table cellpadding="4" width="100%" class="tl0" id="enq220detailArea1">
      <tr>
      <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.title"/></td>
      <td width="87%" class="td_type20" colspan="3">
      <table>
      <tr>
      <td width="99%"><span class="text_base2"><bean:write name="enq220Form" property="enq210Title" /></span></td>
      <td colspan="4" width="1%">
      <input type="button" value="<gsmsg:write key='cmn.detail' />" class="btn_base1s_3" onClick="enq220changeBasicDetail(1);" id="enq210detailBtn0">
      </td>
      </tr>
      </table>
      </td>
      </tr>
   </table>
   <table cellpadding="4" width="100%" class="tl0" id="enq220detailArea">
       <tr>
         <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.title"/></td>
         <td width="87%" class="td_type20" colspan="3">
           <table>
             <tr>
               <td width="99%"><span class="text_base2"><bean:write name="enq220Form" property="enq210Title" /></span></td>
               <td colspan="4" width="1%">
               <input type="button" value="<gsmsg:write key='cmn.close' />" class="btn_base1s_3" onClick="enq220changeBasicDetail(0);" id="enq210detailBtn1">
               </td>
             </tr>
            </table>
          </td>
        </tr>

        <tr>
          <!-- 基本情報 重要度 -->
          <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.24" /></td>
          <td width="27%" class="td_type20" nowrap>
            <span class="text_base2">
                  <logic:equal name="enq220Form" property="enq210Juuyou" value="0">
                    <img src="../enquete/images/star_blue_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                    <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                    <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.58" />">
                  </logic:equal>
                  <logic:equal name="enq220Form" property="enq210Juuyou" value="1">
                    <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                    <img src="../enquete/images/star_gold_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                    <img src="../enquete/images/star_white_16.png" class="star" border="0" alt="<gsmsg:write key="project.59" />">
                  </logic:equal>
                  <logic:equal name="enq220Form" property="enq210Juuyou" value="2">
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                    <img src="../enquete/images/star_red_16.png" class="star" border="0" alt="<gsmsg:write key="project.60" />">
                  </logic:equal>
            </span>
          </td>
          <!-- 基本情報 発信者 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cir.2" /></td>
          <td width="50%" class="td_type20">
            <span class="text_base2"><bean:write name="enq220Form" property="enq220ViewSender" /></span>
          </td>
        </tr>

        <!-- 基本情報 アンケート内容 -->
        <tr>
          <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.18" /></td>
          <td colspan="3" class="td_type20">
            <span class="text_base2">
              <!-- 説明 -->
              <% boolean tempfileFlg = false; %>
              <logic:notEmpty name="enq220Form" property="enq210fileName"><% tempfileFlg = true; %></logic:notEmpty>
              <bean:define id="attachKbn" name="enq220Form" property="enq210AttachKbn" type="java.lang.Integer" />
              <bean:define id="attachPos" name="enq220Form" property="enq210AttachPos" type="java.lang.Integer" />
              <table cellpadding="5" width="90%" class="tl0">
              <tbody>
                <tr>
                  <td>
                    <span style="font-size: 80%;">
                    <% if (attachKbn != 0 && attachPos == 0) { %>
                    <%   if (attachKbn == 1 && tempfileFlg) { %>
                       <img src='../enquete/enq220.do?CMD=enq220getImageFile' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                       <table cellpadding="0" border="0">
                        <tr>
                          <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                          <td class="td_temp_link" align="left" valign="middle">
                            <a href="#" onClick="buttonPush('enq220download')" class="text_link"><bean:write name="enq220Form" property="enq210fileName" /></a><br>
                          </td>
                        </tr>
                      </table>
                    <%   } else if (attachKbn == 2 && tempfileFlg) { %>
                      <logic:notEmpty name="enq220Form" property="enq210fileName">
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <a href="#" onClick="buttonPush('enq220download')" class="text_link"><bean:write name="enq220Form" property="enq210fileName" /></a><br>
                            </td>
                          </tr>
                        </table>
                      </logic:notEmpty>
                    <% } %>
                    <% } %>
                   <bean:write name="enq220Form" property="enq220ViewDesc" filter="false" />
                    <% if (attachKbn != 0 && attachPos == 1) { %>
                    <%   if (attachKbn == 1 && tempfileFlg) { %>
                      <img src='../enquete/enq220.do?CMD=enq220getImageFile' name="enqImgName" alt="<gsmsg:write key='cmn.image' />" border="0" class="img_hoge">
                      <table cellpadding="0" border="0">
                        <tr>
                          <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                          <td class="td_temp_link" align="left" valign="middle">
                            <a href="#" onClick="buttonPush('enq220download')" class="text_link"><bean:write name="enq220Form" property="enq210fileName" /></a><br>
                          </td>
                        </tr>
                      </table>
                    <%   } else if (attachKbn == 2 && tempfileFlg) { %>
                      <logic:notEmpty name="enq220Form" property="enq210fileName">
                        <table cellpadding="0" border="0">
                          <tr>
                            <td align="left" valign="middle"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key='cmn.file' />"></td>
                            <td class="td_temp_link" align="left" valign="middle">
                              <a href="#" onClick="buttonPush('enq220download')" class="text_link"><bean:write name="enq220Form" property="enq210fileName" /></a><br>
                            </td>
                          </tr>
                        </table>
                      </logic:notEmpty>
                      <% } %>
                    <% } %>
                    </span>
                  </td>
                </tr>
              </tbody>
              </table>
            </span>
          </td>
        </tr>
        <bean:define id="ansOpen" name="enq220Form" property="enq210AnsOpen" type="java.lang.Integer" />
      <% if (!enqTemplateFlg) { %>
        <!-- 基本情報 回答期限 -->
        <tr>
        <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.19" /></td>
        <td class="td_type20" nowrap>
          <bean:write name="enq220Form" property="enq220ViewAnsDate" />
        </td>
        <!-- 基本情報 結果公開期限 -->
        <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.enq210.11" /></td>
        <td class="td_type20" nowrap>
        <% if (ansOpen ==  GSConstEnquete.KOUKAI_ON) {%>
          <bean:write name="enq220Form" property="enq220ViewAnsPubDateFrom" />
          &nbsp;～&nbsp;
          <logic:notEqual  name="enq220Form" property="enq210ToKbn" value="1">
          <bean:write name="enq220Form" property="enq220ViewPubDateTo" />
          </logic:notEqual>
          <logic:equal  name="enq220Form" property="enq210ToKbn" value="1">
          <gsmsg:write key="main.man200.9" />
          </logic:equal>
        <% } else { %>
          <gsmsg:write key="cmn.private" />
        <% } %>
        </td>
        </tr>
      <% } %>

        <!-- 基本情報 注意事項 -->
        <tr>
        <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.hints" /></td>
        <td colspan="3" class="td_type20">
        <span class="text_base2">
          <bean:define id="anony" name="enq220Form" property="enq210Anony" type="java.lang.Integer" />
          <% if (anony == GSConstEnquete.ANONYMUS_ON && ansOpen == GSConstEnquete.KOUKAI_ON) { %>
            <gsmsg:write key="enq.69" />
          <% } else if (anony == GSConstEnquete.ANONYMUS_ON) { %>
            <gsmsg:write key="enq.31" />
          <% } else if (ansOpen == GSConstEnquete.KOUKAI_ON) { %>
            <gsmsg:write key="enq.32" />
          <% } else { %>
            &nbsp;
          <% } %>
        </span>
        </td>
        </tr>

      </tbody>
      </table>

      <img src="../common/images/spacer.gif" width="1" height="15" border="0" alt="<gsmsg:write key='cmn.spacer' />">

      <!-- 設問情報 -->
      <div class="text_info_title"><gsmsg:write key="enq.04" /></div>

      <table cellpadding="5" width="100%" class="tl0">
      <tbody>

        <!-- 設問情報 設問種類 -->
        <tr>
          <td colspan="2" width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.64" /></td>
          <td colspan="3" width="87%" class="td_type20" nowrap>
            <span class="text_base2"><bean:write name="enq220Form" property="enq220SyuruiName" /></span>
          </td>
        </tr>

        <logic:notEqual name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_COMMENT) %>" >
        <tr>
          <!-- 設問番号 -->
          <logic:equal name="enq220Form" property="enq210queSeqType" value="1">
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.09" /></td>
          </logic:equal>
          <logic:notEqual name="enq220Form" property="enq210queSeqType" value="1">
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.09" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          </logic:notEqual>

          <td width="27%" class="td_type20" nowrap>
            <span class="text_base2">
            <logic:equal name="enq220Form" property="enq210queSeqType" value="1">
              <html:hidden name="enq220Form" property="enq220QueNo" />
              <bean:define id="queIndex" name="enq220Form" property="enq210queIndex" type="java.lang.Integer" />
              <bean:write name="enq220Form" property="enq220autoQueNo" />
            </logic:equal>
            <logic:notEqual name="enq220Form" property="enq210queSeqType" value="1">
              <html:text name="enq220Form" property="enq220QueNo" styleClass="que_no" maxlength="10" />
            </logic:notEqual>
            </span>
          </td>
          <!-- 必須 -->
          <td width="10%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.required" /></td>
          <td width="50%" class="td_type20" nowrap>
            <span class="text_base2"><html:checkbox name="enq220Form" property="enq220Require" styleId="hissu" value="1" /><label for="hissu"><gsmsg:write key="cmn.required" /></label></span>
          </td>
        </tr>
        </logic:notEqual>

        <logic:notEqual name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_COMMENT) %>" >
        <!-- 設問情報 設問 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.12" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2"><html:text name="enq220Form" property="enq220Question" styleClass="que_question" maxlength="100" /></span>
          </td>
        </tr>
        </logic:notEqual>

        <!-- 設問情報 説明文 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header td_1" nowrap><gsmsg:write key="cmn.explanation" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <input type="hidden" name="enqEditorSize" value="180">
            <table width="99%">
              <tr>
                <td>
                  <span class="text_base2">
                    <div class="setsumon" style="display:blank;">
                      <html:textarea name="enq220Form" property="enq220QueDesc" cols="40" rows="5" styleClass="text_base" styleId="enqDescArea" />
                    </div>
                  </span>
                </td>
              </tr>
            </table>
          </td>
        </tr>

        <% String strCss = ""; %>
        <logic:empty name="enq220Form" property="enq220fileName">
          <% strCss = "td_2"; %>
        </logic:empty>
        <logic:notEmpty name="enq220Form" property="enq220fileName">
          <% strCss = "td_1"; %>
        </logic:notEmpty>

        <tr>
          <td width="4%" class="td_gray <%= strCss %>"></td>
          <td width="9%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.attached" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <bean:write name="enq220Form" property="enq220fileName" /> &nbsp;&nbsp;&nbsp;&nbsp;<html:hidden name="enq220Form" property="enq220fileName" />
            <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" id="tempbtn" name="attacheBtn" onClick="opnTemp('enq220files', '<bean:write name="enq220Form" property="enq220fileDir" />', '1', '0');">
            &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" id="sakujobtn" name="dellBtn" onClick="buttonPush('enq220delTemp');">
            </span>
          </td>
        </tr>

        <tr id="enq220attachPosition">
          <td class="td_gray td_2"></td>
          <td align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.position" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220AttachPos" value="0" styleId="iti_0" /><label for="iti_0"><gsmsg:write key="cmn.up2" /></label>
              <html:radio name="enq220Form" property="enq220AttachPos" value="1" styleId="iti_1" /><label for="iti_1"><gsmsg:write key="cmn.down2" /></label>
            </span>
          </td>
        </tr>

        <logic:equal name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_COMMENT) %>" >
        <!-- コメント -->
        <!-- 横線位置 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.horizon.line.position" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220LinePos" value="<%= String.valueOf(GSConstEnquete.EQM_LINE_KBN_NONE) %>" styleId="linePos_0" /><label for="linePos_0"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220LinePos" value="<%= String.valueOf(GSConstEnquete.EQM_LINE_KBN_TOP) %>" styleId="linePos_1" /><label for="linePos_1"><gsmsg:write key="cmn.up2" /></label>
              <html:radio name="enq220Form" property="enq220LinePos" value="<%= String.valueOf(GSConstEnquete.EQM_LINE_KBN_BOTTOM) %>" styleId="linePos_2" /><label for="linePos_2"><gsmsg:write key="cmn.down2" /></label>
              <html:radio name="enq220Form" property="enq220LinePos" value="<%= String.valueOf(GSConstEnquete.EQM_LINE_KBN_UPDOWN) %>" styleId="linePos_3" /><label for="linePos_3"><gsmsg:write key="cmn.updown" /></label>
            </span>
          </td>
        </tr>
        </logic:equal>

        <logic:equal name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_TEXT) %>" >
        <!-- テキスト入力（1行・複数行） -->
        <!-- 初期値 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header"><gsmsg:write key="ntp.10" /></td>
          <td colspan="3" class="td_type20" valign="top">
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220DefKbn" value="<%= String.valueOf(GSConstEnquete.INIT_OFF) %>" styleId="init_0" onclick="changeInitArea(0);" /><label for="init_0"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220DefKbn" value="<%= String.valueOf(GSConstEnquete.INIT_ON) %>" styleId="init_1" onclick="changeInitArea(0);" /><label for="init_1"><gsmsg:write key="cmn.exist" /></label>
              <span id="enq220TxtInitArea">
                <logic:equal name="enq220Form" property="enq210queType" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXTAREA) %>" >
                  <br>
                  <html:textarea name="enq220Form" property="enq220DefTxt" styleClass="text_base que_textarea_init" />
                </logic:equal>
                <logic:notEqual name="enq220Form" property="enq210queType" value="<%= String.valueOf(GSConstEnquete.SYURUI_TEXTAREA) %>" >
                  &nbsp;<html:text name="enq220Form" property="enq220DefTxt" styleClass="que_text_init" maxlength="100" />
                </logic:notEqual>
              </span>
            </span>
          </td>
        </tr>
        </logic:equal>

        <logic:equal name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_INTEGER) %>" >
        <!-- 数値入力 -->
        <!-- 初期値 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="ntp.10" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220DefKbn" value="<%= String.valueOf(GSConstEnquete.INIT_OFF) %>" styleId="init_0" onclick="changeInitArea(1);" /><label for="init_0"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220DefKbn" value="<%= String.valueOf(GSConstEnquete.INIT_ON) %>" styleId="init_1" onclick="changeInitArea(1);" /><label for="init_1"><gsmsg:write key="cmn.exist" /></label>
              <span id="enq220IntInitArea" style="display:none;">
                <html:text name="enq220Form" property="enq220DefNum" styleClass="que_text_int" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_INT) %>" />
              </span>
            </span>
          </td>
        </tr>
        <!-- 入力範囲 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.input.range" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220RngKbn" value="<%= String.valueOf(GSConstEnquete.RNG_OFF) %>" styleId="rng_0" onclick="changeRangeArea(0);" /><label for="rng_0"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220RngKbn" value="<%= String.valueOf(GSConstEnquete.RNG_ON) %>" styleId="rng_1" onclick="changeRangeArea(0);" /><label for="rng_1"><gsmsg:write key="cmn.exist" /></label>
              <span id="enq220IntRangeArea">
                <html:text name="enq220Form" property="enq220RngStrNum" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_INT) %>" styleClass="que_text_int" />
                　～　
                <html:text name="enq220Form" property="enq220RngEndNum" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EAS_ANS_INT) %>" styleClass="que_text_int" />
              </span>
            </span>
          </td>
        </tr>
        <!-- 単位 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="ntp.102" /><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:text name="enq220Form" property="enq220UnitNum" maxlength="10" styleClass="que_text_int" />
            </span>
          </td>
        </tr>
        </logic:equal>

        <logic:equal name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_DAY) %>" >
        <!-- 日付入力 -->
        <!-- 初期値 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="ntp.10" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220DefKbn" value="<%= String.valueOf(GSConstEnquete.INIT_OFF) %>" styleId="init_0" onclick="changeInitArea(2);" /><label for="init_0"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220DefKbn" value="<%= String.valueOf(GSConstEnquete.INIT_ON) %>" styleId="init_1" onclick="changeInitArea(2);" /><label for="init_1"><gsmsg:write key="cmn.exist" /></label>
              <span id="enq220DateInitArea">
                <html:select property="enq220DefDateYear" styleId="selDefYear">
                  <html:optionsCollection name="enq220Form" property="enq220YearLabel" value="value" label="label" />
                </html:select>
                <html:select property="enq220DefDateMonth" styleId="selDefMonth">
                  <html:optionsCollection name="enq220Form" property="enq220MonthLabel" value="value" label="label" />
                </html:select>
                <html:select property="enq220DefDateDay" styleId="selDefDay">
                  <html:optionsCollection name="enq220Form" property="enq220DayLabel" value="value" label="label" />
                </html:select>
                <input type="button" value="Cal" name="initCalendarBtn" onclick="wrtCalendar(this.form.selDefDay, this.form.selDefMonth, this.form.selDefYear);" class="calendar_btn" id="enq220DefCalBtn">
              </span>
            </span>
          </td>
        </tr>
        <!-- 入力範囲 -->
        <tr>
          <td colspan="2" align="center" class="td_gray text_header" nowrap><gsmsg:write key="cmn.input.range" /></td>
          <td colspan="3" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220RngKbn" value="<%= String.valueOf(GSConstEnquete.RNG_OFF) %>" styleId="rng_0" onclick="changeRangeArea(1);" /><label for="rng_0"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220RngKbn" value="<%= String.valueOf(GSConstEnquete.RNG_ON) %>" styleId="rng_1" onclick="changeRangeArea(1);" /><label for="rng_1"><gsmsg:write key="cmn.exist" /></label>
              <span id="enq220DateRangeArea">
                <html:select property="enq220RngStrDateYear" styleId="selRngStrYear">
                  <html:optionsCollection name="enq220Form" property="enq220YearLabel" value="value" label="label" />
                </html:select>
                <html:select property="enq220RngStrDateMonth" styleId="selRngStrMonth">
                  <html:optionsCollection name="enq220Form" property="enq220MonthLabel" value="value" label="label" />
                </html:select>
                <html:select property="enq220RngStrDateDay" styleId="selRngStrDay">
                  <html:optionsCollection name="enq220Form" property="enq220DayLabel" value="value" label="label" />
                </html:select>
                <input type="button" value="Cal" name="rngStrCalendarBtn" onclick="wrtCalendar(this.form.selRngStrDay, this.form.selRngStrMonth, this.form.selRngStrYear);" class="calendar_btn" id="enq220RngStrCalBtn">
                　～　
                <html:select property="enq220RngEndDateYear" styleId="selRngEndYear">
                  <html:optionsCollection name="enq220Form" property="enq220YearLabel" value="value" label="label" />
                </html:select>
                <html:select property="enq220RngEndDateMonth" styleId="selRngEndMonth">
                  <html:optionsCollection name="enq220Form" property="enq220MonthLabel" value="value" label="label" />
                </html:select>
                <html:select property="enq220RngEndDateDay" styleId="selRngEndDay">
                  <html:optionsCollection name="enq220Form" property="enq220DayLabel" value="value" label="label" />
                </html:select>
                <input type="button" value="Cal" name="rngEndCalendarBtn" onclick="wrtCalendar(this.form.selRngEndDay, this.form.selRngEndMonth, this.form.selRngEndYear);" class="calendar_btn" id="enq220RngEndCalBtn">
              </span>
            </span>
          </td>
        </tr>
        </logic:equal>

      </tbody>
      </table>

      <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="<gsmsg:write key='cmn.spacer' />">
      <logic:equal name="enq220Form" property="enq220DspMode" value="<%= String.valueOf(GSConstEnquete.DSP_MODE_CHOICE) %>" >
      <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tbody>
        <tr>
          <td align="left" style="white-space:nowrap;">
          <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onclick="enq220UpRow(enq220selectRow);">
          <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="enq220DownRow(enq220selectRow);">
          </td>
        </tr>
      </tbody>
      </table>
      <img src="../common/images/spacer.gif" width="1" height="5" border="0" alt="<gsmsg:write key='cmn.spacer' />">
      <!-- 選択項目 -->
      <table class="tl0" width="100%" cellpadding="5" cellspacing="0" id="enq220question">
      <thead>
        <tr>
          <th width="5%" class="table_bg_7D91BD"><span class="text_tlw_10pt"></span></th>
          <th width="48%" class="table_bg_7D91BD"><span class="text_tlw_10pt"><gsmsg:write key="cmn.display.name" /></span><span class="text_r2"><gsmsg:write key="cmn.asterisk" /></span></th>
          <th width="37%" class="table_bg_7D91BD"><span class="text_tlw_10pt"><gsmsg:write key="ntp.10" /></span></th>
          <th width="10%" class="table_bg_7D91BD"><span class="text_tlw_10pt">&nbsp;</span></th>
        </tr>
      </thead>
      <tbody id="choTbl">
        <logic:notEmpty name="enq220Form" property="subList">
        <% int subFormCount = 0; %>
        <% String[] subParamName = {"lineNo", "enqDspSec", "enqDspName", "enqDefFlg", "eqsRadioOff_", "eqsRadioOn_", "enqIndex" }; %>
        <logic:iterate id="subData" name="enq220Form" property="subList" indexId="lineIdx">
        <% String lineNo = String.valueOf(lineIdx.intValue()); %>
        <% String lineFrmName = "subList[" + lineNo + "]."; %>
        <% String trNo = "choRow_" + lineNo ; %>
        <% String paramName = lineFrmName + subParamName[0]; %>
        <% String radioNo = "radioNo_" + lineNo; %>
        <tr id="<%= trNo %>">
          <input type="hidden" name="rowIdx" value="<%= lineNo %>">
          <input type="hidden" name="<%= lineFrmName + subParamName[6] %>" value="<%= lineNo %>">
          <html:hidden property="<%= lineFrmName + subParamName[1] %>" styleClass="enqDspSec" />
          <td class="td_type1 text_mod_10pt" align="center" valign="middle" nowrap>
            <label for="<%= radioNo %>" width="100%" height="100%">
            <html:radio name="enq220Form" property="enq220selectRow" styleId="<%= radioNo %>" value="<%= lineNo %>" />
          </td>
          <td class="td_type1 text_mod_10pt" align="left" valign="middle" nowrap>
            <html:text property="<%= lineFrmName + subParamName[2] %>" styleClass="que_dsp_name" maxlength="<%= String.valueOf(GSConstEnquete.MAX_LEN_EQS_DSP_NAME) %>" />
          </td>
          <td class="td_type1 text_mod_10pt" align="left" valign="middle" nowrap>
            <% String enq220checkScript = "enq220ChechCheckBox(" + lineNo + ")"; %>
            <html:checkbox property="<%= lineFrmName + subParamName[3] %>" styleId="<%= subParamName[5] + lineNo %>" value="<%= String.valueOf(GSConstEnquete.CHOICE_INIT_ON) %>" onclick="<%= enq220checkScript %>" /><label for="<%= subParamName[5] + lineNo %>" /><gsmsg:write key="cmn.select" /></label>
          </td>
          <td class="td_type1 text_mod_10pt" width="5%" align="center" valign="middle">
            <input type="button" class="btn_dell_n3" value="<gsmsg:write key="cmn.delete" />" name="btn_delete" onClick="enq220delRow(<%= lineNo %>);"></td>
          </td>
        </tr>
        <% subFormCount++; %>
        </logic:iterate>
        <input type="hidden" name="enq230subFormListCount" value="<%= String.valueOf(subFormCount) %>">
        </logic:notEmpty>
      </tbody>

      <tfoot>
        <tr>
          <td class="td_type1 text_mod_10pt" align="center" valign="middle" nowrap>
          </td>
          <td class="td_type1 text_mod_10pt" align="center" valign="middle" nowrap>
          </td>
          <td class="td_type1 text_mod_10pt" align="left" valign="middle" nowrap>
          </td>
          <td class="td_type1 text_mod_10pt" align="center" valign="middle">
            <input type="button" name="btn_add" class="btn_add_n4" value="<gsmsg:write key="cmn.add" />" onClick="addRow();">
          </td>
        </tr>
      </tfoot>

      </table>

      <table cellpadding="5" width="100%" class="tl0" style="margin-top:10px;">
      <tbody>
        <tr>
          <td width="13%" align="center" class="td_gray text_header" nowrap><gsmsg:write key="enq.40" /></td>
          <td width="87%" class="td_type20" nowrap>
            <span class="text_base2">
              <html:radio name="enq220Form" property="enq220Other" value="<%= String.valueOf(GSConstEnquete.OTHER_OFF) %>" styleId="other_01" /><label for="other_01"><gsmsg:write key="cmn.no3" /></label>
              <html:radio name="enq220Form" property="enq220Other" value="<%= String.valueOf(GSConstEnquete.OTHER_TEXT) %>" styleId="other_02" /><label for="other_02"><gsmsg:write key="cmn.exist2" /></label>
              <html:radio name="enq220Form" property="enq220Other" value="<%= String.valueOf(GSConstEnquete.OTHER_TEXTAREA) %>" styleId="other_03" /><label for="other_03"><gsmsg:write key="cmn.exist3" /></label>
              <div style="margin-top: 5px;">&nbsp;<gsmsg:write key="cmn.asterisk"/><gsmsg:write key="enq.enq220.05"/></div>
            </span>
          </td>
        </tr>
      </tbody>
      </table>

      </logic:equal>

    </td>
  </tr>


  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="1" height="5" border="0" alt="<gsmsg:write key='cmn.spacer' />">
  </td>
  </tr>

  <tr>
    <td width="50%" align="right">
      <input type="button" value="O　K" class="btn_ok1" onclick="enq220Entry();">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('enq220back');">
    </td>
  </tr>

</td>
</tr>
</table>

</td>
</tr>

<br>
<br>


</html:form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>