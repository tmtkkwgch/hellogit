<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSml030Form"; %>
<% String jusin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO); %>
<% String gomi = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO); %>

<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shortmail" />(<gsmsg:write key="sml.sml030.08" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_sml030">

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
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />



<b><%= emojiMail.toString() %><gsmsg:write key="cmn.shortmail" /><br><gsmsg:write key="sml.sml030.08" /></b>

<hr>


<logic:notEmpty name="mbhSml030Form" property="sml030SmlList" scope="request">
<logic:iterate id="msg" name="mbhSml030Form" property="sml030SmlList" indexId="idx" scope="request">


  <bean:write name="msg" property="smsSdateStr" />
  <br>
  ■<gsmsg:write key="cmn.subject" />：
    <logic:notEqual name="msg" property="fwKbn" value="0">
      <img alt="Re" src="../mobile/images/img_forward.gif">
    </logic:notEqual>
    <logic:notEqual name="msg" property="returnKbn" value="0">
      <img alt="Re" src="../mobile/images/img_henshin.gif">
    </logic:notEqual>
  <bean:write name="msg" property="smsTitle" filter="false" />

  <logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= gomi %>">
    <logic:equal name="mbhSml030Form" property="sml020SelectedMailKbn" value="<%= jusin %>">
    <br>
    ■<gsmsg:write key="cmn.sender" />：<logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>

    </logic:equal>

    <logic:equal name="mbhSml030Form" property="sml020SelectedMailKbn" value="<%= sosin %>">
      <br>
      ■<gsmsg:write key="cmn.from" />
      <logic:notEmpty name="msg" property="atesakiList" scope="page">
        <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">
          <br>
          <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
          <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
        </logic:iterate>
      </logic:notEmpty>
    <logic:notEmpty name="msg" property="ccList" scope="page">
      <br>
      ■CC
      <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc3" scope="page">
        <br>
        <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"></logic:notEqual></span></logic:equal>
        <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"></logic:notEqual></span></logic:equal>
      </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="msg" property="bccList" scope="page">
      <br>
      ■BCC
      <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc3" scope="page">
        <br>
        <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"></logic:notEqual></span></logic:equal>
        <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"></logic:notEqual></span></logic:equal>
      </logic:iterate>
    </logic:notEmpty>
    </logic:equal>
  </logic:equal>
  <logic:notEqual name="mbhSml030Form" property="sml010ProcMode" value="<%= gomi %>">
    <logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= jusin %>">
    <br>
    ■<gsmsg:write key="cmn.sender" />：<logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>

    </logic:equal>

    <br>
    ■<gsmsg:write key="cmn.from" />
    <logic:notEmpty name="msg" property="atesakiList" scope="page">
    <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">
      <br>
      <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
      <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
    </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="msg" property="ccList" scope="page">
      <br>
      ■CC
      <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc3" scope="page">
        <br>
        <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"></logic:notEqual></span></logic:equal>
        <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"></logic:notEqual></span></logic:equal>
      </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="msg" property="bccList" scope="page">
      <br>
      ■BCC
      <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc3" scope="page">
        <br>
        <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"></logic:notEqual></span></logic:equal>
        <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"></logic:notEqual></span></logic:equal>
      </logic:iterate>
    </logic:notEmpty>
  </logic:notEqual>
  <br>
  ■<gsmsg:write key="cmn.mark" />
<br>
<logic:equal name="msg" property="smsMark" value="<%= markNone %>"><gsmsg:write key="cmn.no3" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markTel %>"><gsmsg:write key="cmn.phone" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markImp %>"><gsmsg:write key="sml.61" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markSmaily %>"><gsmsg:write key="sml.11" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markWorry %>"><gsmsg:write key="sml.86" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markAngry %>"><gsmsg:write key="sml.83" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markSadly %>"><gsmsg:write key="sml.87" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markBeer %>"><gsmsg:write key="sml.15" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markHart %>"><gsmsg:write key="sml.13" /></logic:equal>
<logic:equal name="msg" property="smsMark" value="<%= markZasetsu %>"><gsmsg:write key="sml.88" /></logic:equal>
<bean:define id="imgMark"><bean:write name="msg" property="smsMark" filter="false"/></bean:define>
<% java.lang.String key = "none";if(imgMap.containsKey(imgMark)){ key = imgMark; } %><%= (java.lang.String) imgMap.get(key) %>

  <br>
  ■<gsmsg:write key="cmn.content" />
  <br>
<span style="word-break:break-all; word-wrap:break-word;">
<bean:write name="msg" property="smsBody" filter="false"/>
</span>


<logic:notEmpty name="mbhSml030Form" property="sml030FileList" scope="request">
  <br>
  ■<gsmsg:write key="cmn.attach.file" />
  <br>
  <logic:iterate id="fileMdl" name="mbhSml030Form" property="sml030FileList" scope="request">
    <bean:write name="fileMdl" property="binFileName" />
    <br>
  </logic:iterate>
</logic:notEmpty>


</logic:iterate>
</logic:notEmpty>

<br>
<logic:notEqual name="mbhSml030Form" property="sml010ProcMode" value="<%= jusin %>">
  <div align="center">
    <logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= sosin %>">
      <input name="sml030Forwarding" value="<gsmsg:write key="cmn.forward" />" type="submit">
    </logic:equal>
  <input type="submit" name="sml030Back" value="<gsmsg:write key="cmn.back" />" />
  </div>
</logic:notEqual>
<logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= jusin %>">
  <div align="center">
<logic:equal name="mbhSml030Form" property="sml030HensinDspFlg" value="true">
    <input name="sml030Return" value="<gsmsg:write key="cmn.reply" />" type="submit">
    <input name="sml030ReturnAll" value="<gsmsg:write key="sml.67" />" type="submit">
    <input name="sml030Forwarding" value="<gsmsg:write key="cmn.forward" />" type="submit">
</logic:equal>
    <input name="sml030Back" value="<gsmsg:write key="cmn.back" />" type="submit">
  </div>
</logic:equal>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>