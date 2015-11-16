<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String sinki = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NEW); %>
<% String hensin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_HENSIN); %>
<% String zenhen = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_ZENHENSIN); %>
<% String tenso = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_TENSO); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SOKO); %>
<% String nikkan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SC_NIKKAN); %>
<% String syukan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_SC_SYUKAN); %>
<% String ntpnikkan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NTP_NIKKAN); %>
<% String ntpsyukan = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_NTP_SYUKAN); %>
<% String zaiseki = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_ZAISEKI); %>
<% String main = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_MAIN); %>
<% String copy = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_CREATE_MODE_COPY); %>
<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<% String markNone = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE); %>
<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

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

<%
  java.util.HashMap imgTextMap = new java.util.HashMap();
  imgTextMap.put(markTel, phone);
  imgTextMap.put(markImp, important);
  imgTextMap.put(markSmaily, smile);
  imgTextMap.put(markWorry, worry);
  imgTextMap.put(markAngry, angry);
  imgTextMap.put(markSadly, sad);
  imgTextMap.put(markBeer, beer);
  imgTextMap.put(markHart, hart);
  imgTextMap.put(markZasetsu, tired);

  imgTextMap.put("none", "&nbsp;");
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= sinki %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= hensin %>"><gsmsg:define id="title" msgkey="cmn.reply" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= zenhen %>"><gsmsg:define id="title" msgkey="sml.67" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= tenso %>"><gsmsg:define id="title" msgkey="cmn.forward" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= soko %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= nikkan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= syukan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= ntpnikkan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= ntpsyukan %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= zaiseki %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= main %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>
<logic:equal name="sml020knForm" property="sml020ProcMode" value="<%= copy %>"><gsmsg:define id="title" msgkey="cmn.create.new" /></logic:equal>

<title>[Group Session] <gsmsg:write key="cmn.shortmail" /><bean:write name="title" /></title>
</head>

<body class="body_03" onunload="windowClose();">
<html:form action="/smail/sml020kn">
<input type="hidden" name="CMD" value="send">
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />

<html:hidden property="sml020ProcMode" />
<html:hidden property="sml020knBinSid"/>

<logic:notEmpty name="sml020knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml020knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml030SelectedRowNum" />
<html:hidden property="sch010DspDate" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch030FromHour" />
<html:hidden property="schWeekDate" />
<html:hidden property="schDailyDate" />
<html:hidden property="ntp010DspDate" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp030FromHour" />

<html:hidden property="sml020SelectHinaId" />
<html:hidden property="sml020Title" />
<html:hidden property="sml020Mark" />
<html:hidden property="sml020Body" />
<html:hidden property="sml020selectFiles" />
<html:hidden property="sml020AllUserReFlg" />

<logic:notEmpty name="sml020knForm" property="sml020userSid" scope="request">
  <logic:iterate id="smlUsrSid" name="sml020knForm" property="sml020userSid" scope="request">
    <input type="hidden" name="sml020userSid" value="<bean:write name="smlUsrSid"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020knForm" property="sml020userSidCc" scope="request">
  <logic:iterate id="smlUsrSidCc" name="sml020knForm" property="sml020userSidCc" scope="request">
    <input type="hidden" name="sml020userSidCc" value="<bean:write name="smlUsrSidCc"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020knForm" property="sml020userSidBcc" scope="request">
  <logic:iterate id="smlUsrSidBcc" name="sml020knForm" property="sml020userSidBcc" scope="request">
    <input type="hidden" name="sml020userSidBcc" value="<bean:write name="smlUsrSidBcc"/>">
  </logic:iterate>
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
<logic:notEmpty name="sml020knForm" property="sml090SearchTarget" scope="request">
  <logic:iterate id="target" name="sml020knForm" property="sml090SearchTarget" scope="request">
    <input type="hidden" name="sml090SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SearchSortKey1" />
<html:hidden property="sml090SearchOrderKey1" />
<html:hidden property="sml090SearchSortKey2" />
<html:hidden property="sml090SearchOrderKey2" />

<html:hidden property="sml020webmail" />

<%-- 検索SVパラメータ start -------------------------------------------------------------------------- --%>
<logic:notEmpty name="sml020knForm" property="cmn120SvuserSid" scope="request">
  <logic:iterate id="svuser" name="sml020knForm" property="cmn120SvuserSid" scope="request">
    <input type="hidden" name="cmn120SvuserSid" value="<bean:write name="svuser"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sml020knForm" property="sml090userSid" scope="request">
  <logic:iterate id="user" name="sml020knForm" property="sml090userSid" scope="request">
    <input type="hidden" name="sml090userSid" value="<bean:write name="user"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSltGroup" />
<html:hidden property="sml090SvSltUser" />

<logic:notEmpty name="sml020knForm" property="sml090SvAtesaki" scope="request">
  <logic:iterate id="svAtesaki" name="sml020knForm" property="sml090SvAtesaki" scope="request">
    <input type="hidden" name="sml090SvAtesaki" value="<bean:write name="svAtesaki"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvMailSyubetsu" />
<html:hidden property="sml090SvMailMark" />
<html:hidden property="sml090SvKeyWord" />
<html:hidden property="sml090SvKeyWordkbn" />

<logic:notEmpty name="sml020knForm" property="sml090SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sml020knForm" property="sml090SvSearchTarget" scope="request">
    <input type="hidden" name="sml090SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml090SvSearchOrderKey1" />
<html:hidden property="sml090SvSearchSortKey1" />
<html:hidden property="sml090SvSearchOrderKey2" />
<html:hidden property="sml090SvSearchSortKey2" />

<%-- 検索SVパラメータ end ---------------------------------------------------------------------------- --%>

<%-- sml090詳細検索パラメータ end   --%>


<logic:notEqual name="sml020knForm" property="sml020webmail" value="1">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
</logic:notEqual>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="80%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../smail/images/header_smail03_7_01.gif" border="0" alt="<gsmsg:write key="cmn.shortmail" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shortmail" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.new.create.edit" /> ]</td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="submit" value="<gsmsg:write key="cmn.sent" />" class="btn_base1" onClick="return onControlSmlSend();">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="smlButtonPush('backFromSmailCreateKn');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">
    <logic:messagesPresent message="false">
    <br><html:errors /><br>
    <img src="../common/images/spacer.gif" style="width:1px; height:1px;" border="0" alt="">
    </logic:messagesPresent>
  </td>
  </tr>
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" nowrap width="10%"><span class="text_bb1"><gsmsg:write key="cmn.from" /></span></td>
    <td align="left" class="td_wt" width="90%">
      <table cellspacing="0" cellpadding="0" border="0">
      <tr>
      <td width="90%">
        <logic:notEmpty name="sml020knForm" property="sml020Atesaki" scope="request">
          <bean:define id="detail" name="sml020knForm" property="sml020Atesaki" type="jp.groupsession.v2.sml.model.SmailModel" />

          <logic:iterate id="atesaki" name="detail" property="atesakiList" indexId="idx" scope="page">

           <span class="photoList">
            <logic:equal name="atesaki" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
              <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
            </logic:equal>
            <logic:equal name="atesaki" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
              <logic:equal name="atesaki" property="binFileSid" value="0">
                <img src="../user/images/photo.gif" name="userImage" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" class="img_bottom">
              </logic:equal>
              <logic:notEqual name="atesaki" property="binFileSid" value="0">
                <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="atesaki" property="binFileSid" />" name="userImage<bean:write name="atesaki" property="usrSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" onload="initImageView50('userImage<bean:write name="atesaki" property="usrSid" />');" class="img_bottom">
              </logic:notEqual>
            </logic:equal>
            <div class="text_base" align="center">
            <span style="white-space: nowrap">
              <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /><logic:notEqual name="detail" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
              <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del><logic:notEqual name="detail" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
            </span>
            </div>
            </span>
          </logic:iterate>

        </logic:notEmpty>
        <logic:empty name="sml020knForm" property="sml020Atesaki" scope="request">&nbsp;</logic:empty>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <logic:notEmpty name="sml020knForm" property="sml020AtesakiCc" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" nowrap width="10%"><span class="text_bb1">CC</span></td>
    <td align="left" class="td_wt" width="90%">
      <table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr>
      <td width="90%">
          <bean:define id="detailCc" name="sml020knForm" property="sml020AtesakiCc" type="jp.groupsession.v2.sml.model.SmailModel" />
          <logic:iterate id="atesakiCc" name="detailCc" property="atesakiList" indexId="idx" scope="page">
           <span class="photoList">
            <logic:equal name="atesakiCc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
              <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
            </logic:equal>
            <logic:equal name="atesakiCc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
              <logic:equal name="atesakiCc" property="binFileSid" value="0">
                <img src="../user/images/photo.gif" name="userImageCc" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" class="img_bottom">
              </logic:equal>
              <logic:notEqual name="atesakiCc" property="binFileSid" value="0">
                <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="atesakiCc" property="binFileSid" />" name="userImageCc<bean:write name="atesakiCc" property="usrSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" onload="initImageView50('userImageCc<bean:write name="atesakiCc" property="usrSid" />');" class="img_bottom">
              </logic:notEqual>
            </logic:equal>
            <div class="text_base" align="center">
            <span style="white-space: nowrap">
              <logic:equal name="atesakiCc" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesakiCc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiCc" property="usiMei" /><logic:notEqual name="detailCc" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
              <logic:equal name="atesakiCc" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesakiCc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiCc" property="usiMei" /></del><logic:notEqual name="detailCc" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
            </span>
            </div>
            </span>

          </logic:iterate>
        <logic:empty name="sml020knForm" property="sml020AtesakiCc" scope="request">&nbsp;</logic:empty>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:notEmpty>

    <logic:notEmpty name="sml020knForm" property="sml020AtesakiBcc" scope="request">
    <tr>
    <td class="table_bg_A5B4E1" nowrap width="10%"><span class="text_bb1">BCC</span></td>
    <td align="left" class="td_wt" width="90%">
      <table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr>
      <td width="90%">
          <bean:define id="detailBcc" name="sml020knForm" property="sml020AtesakiBcc" type="jp.groupsession.v2.sml.model.SmailModel" />
          <logic:iterate id="atesakiBcc" name="detailBcc" property="atesakiList" indexId="idx" scope="page">
           <span class="photoList">
            <logic:equal name="atesakiBcc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>">
              <div align="center" class="photo_hikokai2"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div>
            </logic:equal>
            <logic:equal name="atesakiBcc" property="photoFileDsp" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>">
              <logic:equal name="atesakiBcc" property="binFileSid" value="0">
                <img src="../user/images/photo.gif" name="userImageBcc" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" class="img_bottom">
              </logic:equal>
              <logic:notEqual name="atesakiBcc" property="binFileSid" value="0">
                <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name="atesakiBcc" property="binFileSid" />" name="userImageBcc<bean:write name="atesakiBcc" property="usrSid" />" alt="<gsmsg:write key="cmn.photo" />" border="1" width="50px" onload="initImageView50('userImageBcc<bean:write name="atesakiBcc" property="usrSid" />');" class="img_bottom">
              </logic:notEqual>
            </logic:equal>
            <div class="text_base" align="center">
            <span style="white-space: nowrap">
              <logic:equal name="atesakiBcc" property="usrJkbn" value="<%= toroku %>"><bean:write name="atesakiBcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiBcc" property="usiMei" /><logic:notEqual name="detailBcc" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
              <logic:equal name="atesakiBcc" property="usrJkbn" value="<%= delete %>"><del><bean:write name="atesakiBcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesakiBcc" property="usiMei" /></del><logic:notEqual name="detailBcc" property="listSize" value="<%= String.valueOf(idx.intValue()) %>"></logic:notEqual></logic:equal>
            </span>
            </div>
            </span>
          </logic:iterate>
        <logic:empty name="sml020knForm" property="sml020AtesakiBcc" scope="request">&nbsp;</logic:empty>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject" /></span></td>
    <td align="left" class="td_wt">
    <span class="text_base">
      <bean:write name="sml020knForm" property="sml020Title" />
    </span>
    </td>
    </tr>

    <%-- マーク定義  --%>
    <bean:define id="imgMark"><bean:write name="sml020knForm" property="sml020Mark" /></bean:define>
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
        <logic:empty name="sml020knForm" property="sml020FileLabelList" scope="request">&nbsp;</logic:empty>

        <logic:notEmpty name="sml020knForm" property="sml020FileLabelList" scope="request">
        <table cellpadding="0" cellpadding="0" border="0">

        <logic:iterate id="fileMdl" name="sml020knForm" property="sml020FileLabelList" scope="request">
          <tr>
          <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun"><a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a></td>
          </tr>

        </logic:iterate>

        </table>
        </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td colspan="1" class="table_bg_A5B4E1" align="left" nowrap><span class="text_bb1"><gsmsg:write key="wml.210" /></span></td>
    <td colspan="1" align="left" class="td_wt">
      <table align="left">
      <tr>
      <td align="left">
      <span class="smailTextarea_base_nest"><bean:write name="sml020knForm" property="sml020knSmsBody" filter="false"/></span>
      </td>
      </table>
    </td>
    </tr>

    </table>

  </td>
  </tr>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
<script language="JavaScript">
<!--
function replaceAtesakiParm() {
  $('atesaki-part').innerHTML = '<logic:notEmpty name="sml020knForm" property="sml090userSid" scope="request"><logic:iterate id="user090" name="sml020knForm" property="sml090userSid" scope="request"><input type="hidden" name="cmn120userSid" value="<bean:write name="user090"/>"></logic:iterate></logic:notEmpty>';
  smlButtonPush('backFromSmailCreateKn');
}

//-->
</script>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../smail/images/header_smail03_7_b04_2.gif" border="0" alt=""></td>
    <td width="100%" class="header_white_bg_text" align="right" valign="middle" nowrap></td>
    <td width="0%" class="header_white_bg_text">
      <input type="submit" value="<gsmsg:write key="cmn.sent" />" class="btn_base1" onClick="return onControlSmlSend();">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="smlButtonPush('backFromSmailCreateKn');">
    </td>
    <td width="0%"><img src="../smail/images/header_smail03_7_b04.gif" border="0" alt=""></td>
    </tr>
    </table>

<div id="atesaki-part">
<logic:notEmpty name="sml020knForm" property="cmn120userSid" scope="request">
  <logic:iterate id="user" name="sml020knForm" property="cmn120userSid" scope="request">
    <input type="hidden" name="cmn120userSid" value="<bean:write name="user"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

    </td>
    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</html:form>
<iframe type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></iframe>
</body>
</html:html>