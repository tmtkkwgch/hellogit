<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<% String jusin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO); %>
<% String gomi = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO); %>

<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shortmail" />(<gsmsg:write key="sml.sml030.08" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sml"; %>
<% thisForm = "mbhSml030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_sml030">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccountName" />
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
  imgMap.put(markTel, "<img src=\"../smail/images/call.gif\" alt=\"" + phone + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markImp, "<img src=\"../smail/images/zyuu.gif\" alt=\"" + important + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markSmaily, "<img src=\"../smail/images/icon_face01.gif\" alt=\"" + smile + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markWorry, "<img src=\"../smail/images/icon_face02.gif\" alt=\"" + worry + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markAngry, "<img src=\"../smail/images/icon_face03.gif\" alt=\"" + angry + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markSadly, "<img src=\"../smail/images/icon_face04.gif\" alt=\"" + sad + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markBeer, "<img src=\"../smail/images/icon_beer.gif\" alt=\"" + beer + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markHart, "<img src=\"../smail/images/icon_hart.gif\" alt=\"" + hart + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");
  imgMap.put(markZasetsu, "<img src=\"../smail/images/icon_zasetsu.gif\" alt=\"" + tired + "\" border=\"0\" class=\"ui-li-icon ui-li-thumb\">");

  imgMap.put("none", "&nbsp;");
%>

<html:hidden property="mobileType" value="1"/>
<html:hidden property="smlViewAccount" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('sml030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/sml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shortmail" /><br><gsmsg:write key="sml.sml030.08" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">


<logic:notEmpty name="mbhSml030Form" property="sml030SmlList" scope="request">

<logic:iterate id="msg" name="mbhSml030Form" property="sml030SmlList" indexId="idx" scope="request">
<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <bean:write name="msg" property="smsSdateStr" />
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.subject" />
        <logic:notEqual name="msg" property="fwKbn" value="0">
      <img alt="Re" src="../mobile/images/img_forward.gif">
    </logic:notEqual>
    <logic:notEqual name="msg" property="returnKbn" value="0">
      <img alt="Re" src="../mobile/images/img_henshin.gif">
    </logic:notEqual>
    </div>
    <bean:write name="msg" property="smsTitle" filter="false" />
  </li>
  <logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= gomi %>">
    <logic:equal name="mbhSml030Form" property="sml020SelectedMailKbn" value="<%= jusin %>">
      <li>
        <div class="font_xsmall"><gsmsg:write key="cmn.sender" /></div>
        <logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>
      </li>
    </logic:equal>

    <li>

      <logic:equal name="msg" property="smsMark" value="<%= markNone %>"><span class="font_small">■<gsmsg:write key="cmn.mark" /></span><br><gsmsg:write key="cmn.no3" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markTel %>"><gsmsg:write key="cmn.phone" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markImp %>"><gsmsg:write key="sml.61" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markSmaily %>"><gsmsg:write key="sml.11" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markWorry %>"><gsmsg:write key="sml.86" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markAngry %>"><gsmsg:write key="sml.83" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markSadly %>"><gsmsg:write key="sml.87" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markBeer %>"><gsmsg:write key="sml.15" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markHart %>"><gsmsg:write key="sml.13" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markZasetsu %>"><gsmsg:write key="sml.88" /></logic:equal>
      <bean:define id="imgMark"><bean:write name="msg" property="smsMark" /></bean:define>
      <% java.lang.String key = "none";if(imgMap.containsKey(imgMark)){ key = imgMark; } %><%= (java.lang.String) imgMap.get(key) %>
    </li>

    <logic:equal name="mbhSml030Form" property="sml020SelectedMailKbn" value="<%= sosin %>">
      <li>
        <div class="font_xsmall">■<gsmsg:write key="cmn.from" /></div>
        <logic:notEmpty name="msg" property="atesakiList" scope="page">
        <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">
          <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
          <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
        </logic:iterate>
        </logic:notEmpty>
      </li>
      <logic:notEmpty name="msg" property="ccList" scope="page">
        <li>
          <div class="font_xsmall">■CC</div>
          <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc3" scope="page">
            <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
            <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
          </logic:iterate>
        </li>
      </logic:notEmpty>
      <logic:notEmpty name="msg" property="bccList" scope="page">
        <li>
          <div class="font_xsmall">■BCC</div>
          <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc3" scope="page">
            <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
            <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
          </logic:iterate>
        </li>
      </logic:notEmpty>
    </logic:equal>
  </logic:equal>
  <logic:notEqual name="mbhSml030Form" property="sml010ProcMode" value="<%= gomi %>">
    <logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= jusin %>">
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.sender" /></div>
      <logic:equal name="msg" property="usrJkbn" value="<%= toroku %>"><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></logic:equal><logic:equal name="msg" property="usrJkbn" value="<%= delete %>"><del><bean:write name="msg" property="usiSei" /><bean:write name="msg" property="usiMei" /></del></logic:equal>
    </li>
    </logic:equal>
    <li>

      <logic:equal name="msg" property="smsMark" value="<%= markNone %>"><span class="font_small">■<gsmsg:write key="cmn.mark" /></span><br><gsmsg:write key="cmn.no3" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markTel %>"><gsmsg:write key="cmn.phone" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markImp %>"><gsmsg:write key="sml.61" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markSmaily %>"><gsmsg:write key="sml.11" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markWorry %>"><gsmsg:write key="sml.86" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markAngry %>"><gsmsg:write key="sml.83" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markSadly %>"><gsmsg:write key="sml.87" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markBeer %>"><gsmsg:write key="sml.15" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markHart %>"><gsmsg:write key="sml.13" /></logic:equal>
      <logic:equal name="msg" property="smsMark" value="<%= markZasetsu %>"><gsmsg:write key="sml.88" /></logic:equal>
      <bean:define id="imgMark"><bean:write name="msg" property="smsMark" /></bean:define>
      <% java.lang.String key = "none";if(imgMap.containsKey(imgMark)){ key = imgMark; } %><%= (java.lang.String) imgMap.get(key) %>
    </li>
    <li>
      <div class="font_xsmall">■<gsmsg:write key="cmn.from" /></div>
      <logic:notEmpty name="msg" property="atesakiList" scope="page">
      <logic:iterate id="atesaki" name="msg" property="atesakiList" indexId="idx_at" scope="page">
        <logic:equal name="atesaki" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></span></logic:equal>
        <logic:equal name="atesaki" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="atesaki" property="usiSei" />&nbsp;&nbsp;<bean:write name="atesaki" property="usiMei" /></del></span></logic:equal>
        <br>
      </logic:iterate>
      </logic:notEmpty>
    </li>
    <logic:notEmpty name="msg" property="ccList" scope="page">
      <li>
        <div class="font_xsmall">■CC</div>
        <logic:iterate id="cc" name="msg" property="ccList" indexId="idx_cc3" scope="page">
          <logic:equal name="cc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
          <logic:equal name="cc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="cc" property="usiSei" />&nbsp;&nbsp;<bean:write name="cc" property="usiMei" /></del><logic:notEqual name="msg" property="ccListSize" value="<%= String.valueOf(idx_cc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
        </logic:iterate>
      </li>
    </logic:notEmpty>
    <logic:notEmpty name="msg" property="bccList" scope="page">
      <li>
        <div class="font_xsmall">■BCC</div>
        <logic:iterate id="bcc" name="msg" property="bccList" indexId="idx_bcc3" scope="page">
          <logic:equal name="bcc" property="usrJkbn" value="<%= toroku %>"><span class="text_base"><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
          <logic:equal name="bcc" property="usrJkbn" value="<%= delete %>"><span class="text_base"><del><bean:write name="bcc" property="usiSei" />&nbsp;&nbsp;<bean:write name="bcc" property="usiMei" /></del><logic:notEqual name="msg" property="bccListSize" value="<%= String.valueOf(idx_bcc3.intValue()) %>"><br></logic:notEqual></span></logic:equal>
        </logic:iterate>
      </li>
    </logic:notEmpty>
  </logic:notEqual>

<%--
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.content" /></div>
    <bean:write name="msg" property="smsBody" filter="false"/>
  </li>
--%>

  <logic:notEmpty name="mbhSml030Form" property="sml030FileList" scope="request">
    <li>
      <div class="font_xsmall" align="center"><gsmsg:write key="cmn.attach.file" /></div>
    </li>
    <logic:iterate id="fileMdl" name="mbhSml030Form" property="sml030FileList" scope="request">
      <li>
        <a href="../mobile/sp_sml030.do?mobileType=1&smlViewAccount=<bean:write name="mbhSml030Form" property="smlViewAccount" />&CMD=downLoad&sml020SelectedSid=<bean:write name="mbhSml030Form" property="sml020SelectedSid" />&sml030binSid=<bean:write name="fileMdl" property="binSid" />"><span class="color_blue"><bean:write name="fileMdl" property="binFileName" />&nbsp;<bean:write name="fileMdl" property="binFileSizeDsp" /></span></a>
      </li>
    </logic:iterate>
  </logic:notEmpty>
</ul>

  <div class="text_box">
    <b>
      <div class="font_xsmall" align="center"><gsmsg:write key="cmn.content" /></div>
      <hr>
      <bean:write name="msg" property="smsBody" filter="false"/>
    </b>
  </div>
</logic:iterate>

</logic:notEmpty>

<logic:notEqual name="mbhSml030Form" property="sml010ProcMode" value="<%= jusin %>">
  <div align="center">
    <logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= sosin %>">
      <input name="sml030Forwarding" value="<gsmsg:write key="cmn.forward" />" type="submit" data-inline="true" data-icon="forward"/>
      <br />
    </logic:equal>
    <input type="submit" name="sml030Back" value="<gsmsg:write key="cmn.back" />" data-inline="true" data-role="button" data-icon="back" />
  </div>
</logic:notEqual>
<logic:equal name="mbhSml030Form" property="sml010ProcMode" value="<%= jusin %>">
<logic:equal name="mbhSml030Form" property="sml030HensinDspFlg" value="true">
  <div data-role="controlgroup" data-type="horizontal" align="center">
    <input name="sml030Return" value="<gsmsg:write key="cmn.reply" />" type="submit" data-inline="true" data-icon="forward" />
    <input name="sml030ReturnAll" value="<gsmsg:write key="sml.67" />" type="submit" data-inline="true" data-icon="forward" data-iconpos="right"/>
    <input name="sml030Forwarding" value="<gsmsg:write key="cmn.forward" />" type="submit" data-inline="true" data-icon="forward" data-iconpos="right"/>
  </div>
</logic:equal>
  <div align="center">
    <input name="sml030Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
  </div>
</logic:equal>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>