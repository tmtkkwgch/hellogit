<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String sosin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN); %>
<% String jusin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO); %>
<% String top = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN_FROM_TOP); %>
<% String gomi = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO); %>

<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

<% String fwkbnOk      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.FWKBN_OK); %>

<%-- マーク画像定義 --%>
<%
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String phone = gsMsg.getMessage(request, "cmn.phone");
        String important = gsMsg.getMessage(request, "sml.61");
        String smile = gsMsg.getMessage(request, "sml.11");
        String worry = gsMsg.getMessage(request, "sml.86");
        String angry = gsMsg.getMessage(request, "sml.83");
        String sad = gsMsg.getMessage(request, "sml.87");
        String beer = gsMsg.getMessage(request, "sml.15");
        String hart = gsMsg.getMessage(request, "sml.13");
        String tired = gsMsg.getMessage(request, "sml.88");

  java.util.HashMap imgMap = new java.util.HashMap();
  imgMap.put(markTel, "<img src=\"../smail/images/call.gif\" alt=\"" + phone + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markImp, "<img src=\"../smail/images/zyuu.gif\" alt=\"" + important + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markSmaily, "<img src=\"../smail/images/icon_face01.gif\" alt=\"" + smile + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markWorry, "<img src=\"../smail/images/icon_face02.gif\" alt=\"" + worry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markAngry, "<img src=\"../smail/images/icon_face03.gif\" alt=\"" + angry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markSadly, "<img src=\"../smail/images/icon_face04.gif\" alt=\"" + sad + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markBeer, "<img src=\"../smail/images/icon_beer.gif\" alt=\"" + beer + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markHart, "<img src=\"../smail/images/icon_hart.gif\" alt=\"" + hart + "\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markZasetsu, "<img src=\"../smail/images/icon_zasetsu.gif\" alt=\"" + tired + "\" border=\"0\" class=\"img_bottom\">");

  imgMap.put("none", "&nbsp;");
%>


<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml030.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="sml.sml030.08" /></title>
</head>

<body class="body_03">

<html:form action="/smail/sml030">
<input type="hidden" name="CMD" value="">
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml010SelectedMailKbn" />
<html:hidden property="sml030PrevNextFlg" />
<html:hidden property="tempDspFlg"/>


<logic:notEmpty name="sml030Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml030Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml030SelectedRowNum" />
<html:hidden property="sml030binSid" />
<html:hidden property="schWeekDate" />
<html:hidden property="schDailyDate" />
<html:hidden property="sml030HensinDspFlg" />

<logic:empty name="sml030Form" property="sml010SelectedMailKbn">
  <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= top %>">
    <input type="hidden" name="helpPrm" value="<%= jusin %>">
  </logic:equal>
  <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= top %>">
    <input type="hidden" name="helpPrm" value="<bean:write name="sml030Form" property="sml010ProcMode" />">
  </logic:notEqual>
</logic:empty>
<logic:notEmpty name="sml030Form" property="sml010SelectedMailKbn">
  <input type="hidden" name="helpPrm" value="<bean:write name="sml030Form" property="sml010SelectedMailKbn" />">
</logic:notEmpty>






<%-- sml090詳細検索パラメータ start --%>
<html:hidden property="sml090ProcModeSave" />
<html:hidden property="sml090BackParm" />

<html:hidden property="searchFlg" />

<html:hidden property="sml090page1" />
<html:hidden property="sml090page2" />
<html:hidden property="sml090SltGroup" />
<html:hidden property="sml090SltUser" />
<html:hidden property="sml090MailSyubetsu" />
<html:hidden property="sml090MailMark" />
<html:hidden property="sml090KeyWord" />
<html:hidden property="sml090KeyWordkbn" />
<logic:notEmpty name="sml030Form" property="sml090SearchTarget" scope="request">
  <logic:iterate id="target" name="sml030Form" property="sml090SearchTarget" scope="request">
    <input type="hidden" name="sml090SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SearchSortKey1" />
<html:hidden property="sml090SearchOrderKey1" />
<html:hidden property="sml090SearchSortKey2" />
<html:hidden property="sml090SearchOrderKey2" />

<%-- 検索SVパラメータ start -------------------------------------------------------------------------- --%>
<logic:notEmpty name="sml030Form" property="cmn120SvuserSid" scope="request">
  <logic:iterate id="svuser" name="sml030Form" property="cmn120SvuserSid" scope="request">
    <input type="hidden" name="cmn120SvuserSid" value="<bean:write name="svuser"/>">
  </logic:iterate>
</logic:notEmpty>


<logic:notEmpty name="sml030Form" property="sml090userSid" scope="request">
  <logic:iterate id="user" name="sml030Form" property="sml090userSid" scope="request">
    <input type="hidden" name="sml090userSid" value="<bean:write name="user"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSltGroup" />
<html:hidden property="sml090SvSltUser" />

<logic:notEmpty name="sml030Form" property="sml090SvAtesaki" scope="request">
  <logic:iterate id="svAtesaki" name="sml030Form" property="sml090SvAtesaki" scope="request">
    <input type="hidden" name="sml090SvAtesaki" value="<bean:write name="svAtesaki"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvMailSyubetsu" />
<html:hidden property="sml090SvMailMark" />
<html:hidden property="sml090SvKeyWord" />
<html:hidden property="sml090SvKeyWordkbn" />

<logic:notEmpty name="sml030Form" property="sml090SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sml030Form" property="sml090SvSearchTarget" scope="request">
    <input type="hidden" name="sml090SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSearchOrderKey1" />
<html:hidden property="sml090SvSearchSortKey1" />
<html:hidden property="sml090SvSearchOrderKey2" />
<html:hidden property="sml090SvSearchSortKey2" />

<%-- 検索SVパラメータ end ---------------------------------------------------------------------------- --%>

<%-- sml090詳細検索パラメータ end   --%>




<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="5" cellspacing="0" border="0" width="80%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../smail/images/header_smail03_7_01.gif" border="0" alt="<gsmsg:write key="cmn.shortmail" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shortmail" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="sml.sml030.08" /> ]</td>
    <td width="100%" class="header_white_bg">

<%--
      <logic:equal name="sml030Form" property="sml030PrevBound" value="false">
        <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_base1_dis_previous" disabled="true">
      </logic:equal>
      <logic:equal name="sml030Form" property="sml030PrevBound" value="true">
        <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" onClick="buttonPush('prev');">
      </logic:equal>
      <logic:equal name="sml030Form" property="sml030NextBound" value="false">
        <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_base1_dis_next" disabled="true">
      </logic:equal>
      <logic:equal name="sml030Form" property="sml030NextBound" value="true">
        <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" onClick="buttonPush('next');">
      </logic:equal>
--%>
      <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" onClick="buttonPush('prev');">
      <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" onClick="buttonPush('next');">

      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="replaceAtesakiParm();">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:notEmpty name="sml030Form" property="sml030SmlList" scope="request">

        <input type="button" value="<gsmsg:write key="sml.167" />" class="btn_pdf_n2" onClick="buttonPush('pdf');">
        <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
        <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= sosin %>">
            <input type="button" value="<gsmsg:write key="cmn.register.copy.new" />" class="btn_base1w" onClick="buttonPush('copy');">
        </logic:equal>
        <logic:equal name="sml030Form" property="sml030HensinDspFlg" value="true">
          <input type="button" value="<gsmsg:write key="cmn.reply" />" class="btn_henshin_n2" onClick="buttonPush('hensin');">
          <input type="button" value="<gsmsg:write key="sml.67" />" class="btn_zenhenshin" onClick="buttonPush('zenhensin');">
        </logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.forward" />" class="btn_tensou" onClick="buttonPush('tenso');">
        </logic:notEqual>
        <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
          <input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('revived');">
        </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
        <logic:equal name="sml030Form" property="sml030DeleteAllOk" value="true">
          <input type="button" value="<gsmsg:write key="cmn.delete.all" />" class="btn_base1" onClick="buttonPush('deleteAll');">
        </logic:equal>
        </td>
      </logic:notEmpty>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <br><html:errors /><br>
    <img src="../common/images/spacer.gif" style="width:1px; height:1px;" border="0" alt="">
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">

      <logic:notEmpty name="sml030Form" property="sml030SmlList" scope="request">
        <logic:iterate id="msg" name="sml030Form" property="sml030SmlList" indexId="idx" scope="request">
        <tr>

        <logic:equal name="sml030Form" property="sml030SosinFlg" value="false">
        <td  class="img_top" >
          <table width="100%" class="tl0 smail_tr" cellpadding="0">

          <tr>
          <td width="100%" nowrap  class="img_top">

            <!-- 送信先ユーザ写真 -->
            <logic:equal name="msg" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
              <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
            </logic:equal>

            <logic:equal name="msg" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
              <logic:equal name="msg" property="binFileSid" value="0">
                <img src="../user/images/photo.gif" name="userImage<bean:write name="msg" property="smlSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="130px" onload="initImageView130('userImage<bean:write name="msg" property="smlSid" />');">
              </logic:equal>
              <logic:notEqual name="msg" property="binFileSid" value="0">
                <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="msg" property="binFileSid" />" name="userImage<bean:write name="msg" property="smlSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="130px" onload="initImageView130('userImage<bean:write name="msg" property="smlSid" />');">
              </logic:notEqual>
            </logic:equal>
            <logic:equal name="msg" property="photoFileDsp" value="2">
              <img src="../smail/images/icon_sml.jpg" border="0" alt="<gsmsg:write key="cmn.shortmail" />" class="img_bottom">
            </logic:equal>
          </td>
          </tr>
          </table>
        </td>
        </logic:equal>

        <td width="100%">
          <table width="100%" class="tl0" border="0" cellpadding="5">

          <logic:equal name="sml030Form" property="sml030SosinFlg" value="true">
            <tr>
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
            <td align="left" class="td_wt table_pad">
              <logic:notEmpty name="msg" property="atesakiList" scope="page">
                <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at3" scope="page">
                <span class="photoList">

                <!-- 宛先ユーザ写真 -->
                <logic:equal name="atesaki" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                  <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                </logic:equal>
                <logic:equal name="atesaki" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
                <logic:equal name="atesaki" property="binFileSid" value="0">
                  <img src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" class="img_bottom">
                </logic:equal>
                <logic:notEqual name="atesaki" property="binFileSid" value="0">
                  <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="atesaki" property="binFileSid" />" name="userImage<bean:write name="atesaki" property="usrSid" />" alt="<gsmsg:write key="cmn.photo" />" border="0" width="50px" onload="initImageView50('userImage<bean:write name="atesaki" property="usrSid" />');" class="img_bottom">
                </logic:notEqual>
                </logic:equal>

                <div class="text_base" align="center">
                <span style="white-space: nowrap">
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /><logic:notEqual name="msg" property="listSize" value=""></logic:notEqual></logic:equal>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del><logic:notEqual name="msg" property="listSize" value=""></logic:notEqual></logic:equal>
                </span>
                </div>
                </span>
                </logic:iterate>
              </logic:notEmpty>
            </td>
            </tr>

            <logic:notEmpty name="msg" property="ccList" scope="page">
            <tr>
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">CC</span></td>
            <td align="left" class="td_wt">
              <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc3" scope="page">

                <span class="photoList">
                <logic:equal name="cc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                  <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                </logic:equal>
                <logic:equal name="cc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
                <logic:equal name="cc" property="binFileSid" value="0">
                  <img src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" class="img_bottom">
                </logic:equal>
                <logic:notEqual name="cc" property="binFileSid" value="0">
                  <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="cc" property="binFileSid" />" name="userImage<bean:write name="cc" property="usrSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" onload="initImageView50('userImage<bean:write name="cc" property="usrSid" />');" class="img_bottom">
                </logic:notEqual>
                </logic:equal>
                <div class="text_base" align="center">
                <span style="white-space: nowrap">
                <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                </span>
                </div>
                </span>
              </logic:iterate>
            </td>
            </tr>
            </logic:notEmpty>

            <logic:notEmpty name="msg" property="bccList" scope="page">
            <tr>
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">BCC</span></td>
            <td align="left" class="td_wt">
              <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc3" scope="page">

                <span class="photoList">
                <logic:equal name="bcc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
                  <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
                </logic:equal>
                <logic:equal name="bcc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
                <logic:equal name="bcc" property="binFileSid" value="0">
                  <img src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" class="img_bottom">
                </logic:equal>
                <logic:notEqual name="bcc" property="binFileSid" value="0">
                  <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="bcc" property="binFileSid" />" name="userImage<bean:write name="bcc" property="usrSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" onload="initImageView50('userImage<bean:write name="bcc" property="usrSid" />');" class="img_bottom">
                </logic:notEqual>
                </logic:equal>
                <div class="text_base" align="center">
                <span style="white-space: nowrap">
                  <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                </span>
                </div>
                </span>
              </logic:iterate>
            </td>
            </tr>
            </logic:notEmpty>

            <tr>
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject" /></span></td>
            <td align="left" class="td_wt">
              <span class=text_base><bean:write name="msg" property="smsTitle" /></span>
            </td>
            </tr>

          </logic:equal>

          <logic:equal name="sml030Form" property="sml030SosinFlg" value="false">
          <tr>
          <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject" /></span></td>
          <td align="left" class="td_wt" width="90%">
            <logic:notEqual name="msg" property="fwKbn" value="0">
              <img alt="Fw" src="../smail/images/img_forward.gif" class="img_bottom">
            </logic:notEqual>
            <logic:notEqual name="msg" property="returnKbn" value="0">
              <img alt="Re" src="../smail/images/img_henshin.gif" class="img_bottom">
            </logic:notEqual>
            <span class=text_sml_title><bean:write name="msg" property="smsTitle" /></span>
          </td>
          </tr>
          </logic:equal>

          <tr>
          <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.sender" /></span></td>
          <td align="left" class="td_wt" width="90%">

          <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
            <logic:equal name="msg" property="mailKbn" value="<%= soko %>">&nbsp;</logic:equal>
            <logic:notEqual name="msg" property="mailKbn" value="<%= soko %>">
              <logic:equal name="msg" property="usrJkbn" value="<%= toroku %>">
                <span class="text_base"><bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" /></span>
              </logic:equal>
              <logic:equal name="msg" property="usrJkbn" value="<%= delete %>">
                <span class="text_base"><del><bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" /></del></span>
              </logic:equal>
            </logic:notEqual>
          </logic:equal>

          <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
            <logic:equal name="msg" property="usrJkbn" value="<%= toroku %>">
              <span class="text_base"><bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" /></span>
            </logic:equal>
            <logic:equal name="msg" property="usrJkbn" value="<%= delete %>">
              <span class="text_base"><del><bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" /></del></span>
            </logic:equal>
          </logic:notEqual>
          </td>
          </tr>

          <tr>
          <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.date" /></span></td>
          <td align="left" class="td_wt" width="100%">
            <span class="text_base">
              <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
                <logic:equal name="msg" property="mailKbn" value="<%= soko %>">&nbsp;</logic:equal>
                <logic:notEqual name="msg" property="mailKbn" value="<%= soko %>"><bean:write name="msg" property="smsSdateStr" /></logic:notEqual>
              </logic:equal>

              <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
                <bean:write name="msg" property="smsSdateStr" />
              </logic:notEqual>
            </span>
          </td>
          </tr>

          <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= sosin %>">

            <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
              <logic:notEqual name="msg" property="mailKbn" value="<%= sosin %>">
                <tr>
                <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
                <td align="left" class="td_wt">
                <logic:notEmpty name="msg" property="atesakiList" scope="page">
                  <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /><logic:notEqual name="msg" property="listSize" value="<%= String.valueOf(idx_at.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del><logic:notEqual name="msg" property="listSize" value="<%= String.valueOf(idx_at.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  </logic:iterate>
                </logic:notEmpty>
                </td>
                </tr>

                <logic:notEmpty name="msg" property="ccList" scope="page">
                <tr>
                <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">CC</span></td>
                <td align="left" class="td_wt">
                  <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc" scope="page">
                    <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                    <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  </logic:iterate>
                </td>
                </tr>
                </logic:notEmpty>

                <logic:notEmpty name="msg" property="bccList" scope="page">
                <tr>
                <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">BCC</span></td>
                <td align="left" class="td_wt">
                  <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc" scope="page">
                    <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                    <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  </logic:iterate>
                </td>
                </tr>
                </logic:notEmpty>

              </logic:notEqual>
            </logic:equal>

            <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
              <td align="left" class="td_wt">
                <logic:notEmpty name="msg" property="atesakiList" scope="page">
                  <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at2" scope="page">
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /><logic:notEqual name="msg" property="listSize" value="<%= String.valueOf(idx_at2.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del><logic:notEqual name="msg" property="listSize" value="<%= String.valueOf(idx_at2.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  </logic:iterate>
                </logic:notEmpty>
              </td>
              </tr>

              <logic:notEmpty name="msg" property="ccList" scope="page">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">CC</span></td>
              <td align="left" class="td_wt">
                  <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc2" scope="page">
                    <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc2.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                    <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc2.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  </logic:iterate>
              </td>
              </tr>
              </logic:notEmpty>

              <logic:notEmpty name="msg" property="bccList" scope="page">
              <tr>
              <td class="table_bg_A5B4E1" nowrap><span class="text_bb1">BCC</span></td>
              <td align="left" class="td_wt">
                  <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc2" scope="page">
                    <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc2.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                    <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc2.intValue()) %>"><br></logic:notEqual></span></logic:equal>
                  </logic:iterate>
              </td>
              </tr>
              </logic:notEmpty>

            </logic:notEqual>


          </logic:notEqual>

<%-- マーク定義  --%>
        <bean:define id="imgMark"><bean:write name="msg" property="smsMark" /></bean:define>
          <tr>
          <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.mark" /></span></td>
          <td align="left" class="td_wt">
            <span class="text_base">
              <%-- マーク --%><% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %>
            </span>
          </td>
          </tr>

          <tr>
          <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
          <td align="left" class="td_wt">
            <logic:notEmpty name="sml030Form" property="sml030FileList" scope="request">
              <logic:iterate id="fileMdl" name="sml030Form" property="sml030FileList" scope="request">
              <logic:equal name="sml030Form" property="tempDspFlg" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_IMAGE_TEMP_DSP) %>">
                <logic:notEmpty name="fileMdl" property="binFileExtension">
                   <bean:define id="fext" name="fileMdl" property="binFileExtension"  type="java.lang.String" />
                   <%
                    String dext = ((String)pageContext.getAttribute("fext",PageContext.PAGE_SCOPE));
                    if (dext != null) {
                        dext = dext.toLowerCase();
                        if (jp.groupsession.v2.cmn.biz.CommonBiz.isViewFile(dext)) {
                    %>
                   <img src="../smail/sml030.do?CMD=tempview&sml010SelectedSid=<bean:write name="sml030Form" property="sml010SelectedSid" />&sml030binSid=<bean:write name="fileMdl" property="binSid" />" name="pictImage<bean:write name="fileMdl" property="binSid" />" onload="initImageView('pictImage<bean:write name="fileMdl" property="binSid" />');">
                   <br>
                    <%
                        }
                    }
                    %>
                </logic:notEmpty>
              </logic:equal>
                <a href="javascript:void(0);" onClick="return fileLinkClick('downLoad',<bean:write name="fileMdl" property="binSid" />);"><span class="text_link_min"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a>
                <br><br>
              </logic:iterate>
            </logic:notEmpty>
          </td>
          </tr>

<%-- 開封状況 --%>
        <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= sosin %>">
          <tr>
          <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.210" /></span></td>
          <td align="left" class="td_wt table_pad">
          <span class="smailTextarea_base_nest"><bean:write name="msg" property="smsBody" filter="false"/></span>
          </td>
          </tr>
            <tr>
            <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml030.02" /></span></td>
            <td align="left" class="td_wt table_pad">
              <table width="400" border="0" cellpadding="0" cellspacing="0">
              <tr>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="cmn.from" /></span></td>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.03" /></span></td>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.01" /></span></td>
              </tr>
              <logic:notEmpty name="msg" property="atesakiList" scope="page">
                <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at3" scope="page">
                  <tr>
                  <td class="td_type1" nowrap>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base2"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base2"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
                  </td>
                  <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="atesaki" property="smlOpdateStr" /></span></td>
                  <td class="td_type1" nowrap>
                    <logic:equal name="atesaki" property="smjFwkbn" value="<%= fwkbnOk %>"><span class="text_base2"><gsmsg:write key="sml.sml030.07" /></span></logic:equal>
                  </td>
                  </tr>
                </logic:iterate>
              </logic:notEmpty>
              <logic:notEmpty name="msg" property="ccList" scope="page">
              <tr>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="cmn.from" />(CC)</span></td>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.03" /></span></td>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.01" /></span></td>
              </tr>
                <logic:iterate id="atesaki" name="msg" property="ccList" indexId="idx_at3" scope="page">
                  <tr>
                  <td class="td_type1" nowrap>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base2"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base2"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
                  </td>
                  <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="atesaki" property="smlOpdateStr" /></span></td>
                  <td class="td_type1" nowrap>
                    <logic:equal name="atesaki" property="smjFwkbn" value="<%= fwkbnOk %>"><span class="text_base2"><gsmsg:write key="sml.sml030.07" /></span></logic:equal>
                  </td>
                  </tr>
                </logic:iterate>
              </logic:notEmpty>
              <logic:notEmpty name="msg" property="bccList" scope="page">
              <tr>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="cmn.from" />(BCC)</span></td>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.03" /></span></td>
              <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.01" /></span></td>
              </tr>
                <logic:iterate id="atesaki" name="msg" property="bccList" indexId="idx_at3" scope="page">
                  <tr>
                  <td class="td_type1" nowrap>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base2"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
                    <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base2"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
                  </td>
                  <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="atesaki" property="smlOpdateStr" /></span></td>
                  <td class="td_type1" nowrap>
                    <logic:equal name="atesaki" property="smjFwkbn" value="<%= fwkbnOk %>"><span class="text_base2"><gsmsg:write key="sml.sml030.07" /></span></logic:equal>
                  </td>
                  </tr>
                </logic:iterate>
              </logic:notEmpty>
              </table>
            </td>
            </tr>

        </logic:equal>
        <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
        <logic:equal name="msg" property="mailKbn" value="<%= sosin %>">
          <tr>
          <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.210" /></span></td>
          <td align="left" class="td_wt table_pad">
          <span class="smailTextarea_base_nest"><bean:write name="msg" property="smsBody" filter="false"/></span>
          </td>
          </tr>
          <tr>
          <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml030.02" /></span></td>
          <td align="left" class="td_wt table_pad">
            <table width="400" border="0" cellpadding="0" cellspacing="0">
            <tr>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="cmn.from" /></span></td>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.03" /></span></td>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.01" /></span></td>
            </tr>
            <logic:notEmpty name="msg" property="atesakiList" scope="page">
              <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at3" scope="page">
                <tr>
                <td class="td_type1" nowrap>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base2"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base2"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
                </td>
                <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="atesaki" property="smlOpdateStr" /></span></td>
                <td class="td_type1" nowrap>
                  <logic:equal name="atesaki" property="smjFwkbn" value="<%= fwkbnOk %>"><span class="text_base2"><gsmsg:write key="sml.sml030.07" /></span></logic:equal>
                </td>
                </tr>
              </logic:iterate>
            </logic:notEmpty>
            <logic:notEmpty name="msg" property="ccList" scope="page">
            <tr>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="cmn.from" />(CC)</span></td>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.03" /></span></td>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.01" /></span></td>
            </tr>
            <logic:notEmpty name="msg" property="ccList" scope="page">
              <logic:iterate id="atesaki" name="msg" property="ccList" indexId="idx_at3" scope="page">
                <tr>
                <td class="td_type1" nowrap>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base2"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base2"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
                </td>
                <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="atesaki" property="smlOpdateStr" /></span></td>
                <td class="td_type1" nowrap>
                  <logic:equal name="atesaki" property="smjFwkbn" value="<%= fwkbnOk %>"><span class="text_base2"><gsmsg:write key="sml.sml030.07" /></span></logic:equal>
                </td>
                </tr>
              </logic:iterate>
            </logic:notEmpty>
            <tr>
            </logic:notEmpty>
            <logic:notEmpty name="msg" property="bccList" scope="page">
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="cmn.from" />(BCC)</span></td>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.03" /></span></td>
            <td width="50%" nowrap class="td_type14" align="center"><span class="text_tlw"><gsmsg:write key="sml.sml030.01" /></span></td>
            </tr>
              <logic:iterate id="atesaki" name="msg" property="bccList" indexId="idx_at3" scope="page">
                <tr>
                <td class="td_type1" nowrap>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base2"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
                  <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base2"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
                </td>
                <td class="td_type1" nowrap align="center"><span class="text_base2"><bean:write name="atesaki" property="smlOpdateStr" /></span></td>
                <td class="td_type1" nowrap>
                  <logic:equal name="atesaki" property="smjFwkbn" value="<%= fwkbnOk %>"><span class="text_base2"><gsmsg:write key="sml.sml030.07" /></span></logic:equal>
                </td>
                </tr>
              </logic:iterate>
            </logic:notEmpty>
            </table>
          </td>
          </tr>
        </logic:equal>
        </logic:equal>



          </table>
        </td>
        </tr>

          <logic:equal name="sml030Form" property="sml030SosinFlg" value="false">
          <tr>
          <td colspan="1"></td>

          <td colspan="1" >
          <table width="100%" class="tl0" border="0" cellpadding="0">
          <tr>
          <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.210" /></span></td>
          </tr>
          <tr>
          <td colspan="2" align="left" class="td_wt table_pad">
          <span class="smailTextarea_base_nest"><bean:write name="msg" property="smsBody" filter="false"/></span>
          </td>
          </tr>
          </table>
          </td>
          </tr>
          </logic:equal>



        </tr>
        </logic:iterate>
      </logic:notEmpty>
      </table>

    <img src="../common/images/spacer.gif" alt="" height="10px" width="10px">

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <logic:notEmpty name="sml030Form" property="sml030SmlList" scope="request">
        <input type="button" value="<gsmsg:write key="sml.167" />" class="btn_pdf_n2" onClick="buttonPush('pdf');">
        <logic:notEqual name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
        <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= sosin %>">
            <input type="button" value="<gsmsg:write key="cmn.register.copy.new" />" class="btn_base1w" onClick="buttonPush('copy');">
        </logic:equal>
        <logic:equal name="sml030Form" property="sml030HensinDspFlg" value="true">
          <input type="button" value="<gsmsg:write key="cmn.reply" />" class="btn_henshin_n2" onClick="buttonPush('hensin');">
          <input type="button" value="<gsmsg:write key="sml.67" />" class="btn_zenhenshin" onClick="buttonPush('zenhensin');">
        </logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.forward" />" class="btn_tensou" onClick="buttonPush('tenso');">
        </logic:notEqual>
        <logic:equal name="sml030Form" property="sml010ProcMode" value="<%= gomi %>">
          <input type="button" value="<gsmsg:write key="cmn.undo" />" class="btn_modosu_n1" onClick="buttonPush('revived');">
        </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
        <logic:equal name="sml030Form" property="sml030DeleteAllOk" value="true">
          <input type="button" value="<gsmsg:write key="cmn.delete.all" />" class="btn_base1" onClick="buttonPush('deleteAll');">
        </logic:equal>
        </td>
      </logic:notEmpty>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../smail/images/header_smail03_7_b04_2.gif" border="0" alt=""></td>
    <td width="100%" class="header_white_bg_text">&nbsp;</td>
    <td width="0%" class="header_white_bg_text" style="text-aligh:right!important">

    <%--
      <logic:equal name="sml030Form" property="sml030PrevBound" value="false">
        <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_base1_dis_previous" disabled="true">
      </logic:equal>
      <logic:equal name="sml030Form" property="sml030PrevBound" value="true">
        <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" onClick="buttonPush('prev');">
      </logic:equal>
      <logic:equal name="sml030Form" property="sml030NextBound" value="false">
        <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_base1_dis_next" disabled="true">
      </logic:equal>
      <logic:equal name="sml030Form" property="sml030NextBound" value="true">
        <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" onClick="buttonPush('next');">
      </logic:equal>
    --%>
      <input type="button" value="<gsmsg:write key="cmn.previous" />" class="btn_previous_n1" onClick="buttonPush('prev');">
      <input type="button" value="<gsmsg:write key="cmn.next" />" class="btn_next_n1" onClick="buttonPush('next');">

      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="replaceAtesakiParm();">
    </td>
    <td width="0%"><img src="../smail/images/header_smail03_7_b04.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<script language="JavaScript">
<!--
function replaceAtesakiParm() {
  $('#atesaki-part')[0].innerHTML = '<logic:notEmpty name="sml030Form" property="sml090userSid" scope="request"><logic:iterate id="user090" name="sml030Form" property="sml090userSid" scope="request"><input type="hidden" name="cmn120userSid" value="<bean:write name="user090"/>"></logic:iterate></logic:notEmpty>';
  buttonPush('backToMsgList');
}

//-->
</script>

<div id="atesaki-part">
<logic:notEmpty name="sml030Form" property="cmn120userSid" scope="request">
  <logic:iterate id="user" name="sml030Form" property="cmn120userSid" scope="request">
    <input type="hidden" name="cmn120userSid" value="<bean:write name="user"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>


<div id="noMailData" title="" style="display:none">
    <p>
      <span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 20px 0;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><gsmsg:write key="mobile.39" /></b>
    </p>
</div>


</html:form>
<iframe type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></iframe>
</body>
</html:html>