<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

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

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shortmail" />(<gsmsg:write key="cmn.check" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sml"; %>
<% thisForm = "mbhSml040knForm"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_sml040kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccountName" />
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('sml040knBack');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/sml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shortmail" /><br><gsmsg:write key="cmn.check" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhSml040knForm" property="sml040userSid">
<logic:iterate id="usid" name="mbhSml040knForm" property="sml040userSid">
  <input type="hidden" name="sml040userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="mbhSml040knForm" property="sml040userSidCc">
<logic:iterate id="usidcc" name="mbhSml040knForm" property="sml040userSidCc">
  <input type="hidden" name="sml040userSidCc" value="<bean:write name="usidcc" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="mbhSml040knForm" property="sml040userSidBcc">
<logic:iterate id="usidbcc" name="mbhSml040knForm" property="sml040userSidBcc">
  <input type="hidden" name="sml040userSidBcc" value="<bean:write name="usidbcc" />">
</logic:iterate>
</logic:notEmpty>
<html:hidden property="smlViewAccount" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />
<html:hidden property="sml040ProcMode" />
<html:hidden property="sml040Title" />
<html:hidden property="sml040Mark" />
<html:hidden property="sml040Body" />
<html:hidden property="sml040InitFlg" />

<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li>
    <div class="font_small">■<gsmsg:write key="cmn.sender" /></div>
    <bean:write name="mbhSml040knForm"  property="smlViewAccountName" />
  </li>

  <li>
    <div class="font_small">■<gsmsg:write key="cmn.from" /></div>
      <logic:notEmpty name="mbhSml040knForm" property="sml040SelectUserList">
      <logic:iterate id="usrLabel" name="mbhSml040knForm" property="sml040SelectUserList" indexId="idx">
      <bean:write name="usrLabel" property="label" /><br>
      </logic:iterate>
      </logic:notEmpty>
  </li>

  <logic:notEmpty name="mbhSml040knForm" property="sml040CcSelectUserList">
    <li>
    <div class="font_small">■CC</div>
      <logic:iterate id="usrCcLabel" name="mbhSml040knForm" property="sml040CcSelectUserList" indexId="idx">
        <bean:write name="usrCcLabel" property="label" /><br>
      </logic:iterate>
    </li>
  </logic:notEmpty>

  <logic:notEmpty name="mbhSml040knForm" property="sml040BccSelectUserList">
    <li>
    <div class="font_small">■BCC</div>
      <logic:iterate id="usrBccLabel" name="mbhSml040knForm" property="sml040BccSelectUserList" indexId="idx">
        <bean:write name="usrBccLabel" property="label" /><br>
      </logic:iterate>
    </li>
  </logic:notEmpty>

  <li>
    <div class="font_small">■<gsmsg:write key="cmn.subject" /></div>
    <bean:write name="mbhSml040knForm" property="sml040Title" />
  </li>
  <li>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markNone %>"><span class="font_small">■<gsmsg:write key="cmn.mark" /></span><br><gsmsg:write key="cmn.no3" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markTel %>"><gsmsg:write key="cmn.phone" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markImp %>"><gsmsg:write key="sml.61" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markSmaily %>"><gsmsg:write key="sml.11" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markWorry %>"><gsmsg:write key="sml.86" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markAngry %>"><gsmsg:write key="sml.83" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markSadly %>"><gsmsg:write key="sml.87" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markBeer %>"><gsmsg:write key="sml.15" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markHart %>"><gsmsg:write key="sml.13" /></logic:equal>
    <logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markZasetsu %>"><gsmsg:write key="sml.88" /></logic:equal>
    <bean:define id="imgMark"><bean:write name="mbhSml040knForm" property="sml040Mark" /></bean:define>
    <% java.lang.String key = "none";if(imgMap.containsKey(imgMark)){ key = imgMark; } %><%= (java.lang.String) imgMap.get(key) %>
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.body" /></div>
    <bean:write name="mbhSml040knForm" property="sml040knBody" filter="false"/>
  </li>
</ul>

<logic:notEmpty name="mbhSml040knForm" property="sml040FileLabelList">
  <div class="font_small">■<gsmsg:write key="cmn.attach.file" /></div>
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:iterate id="file" name="mbhSml040knForm" property="sml040FileLabelList" indexId="idx" scope="request">
      <li><bean:write name="file" property="label" /></li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <br><input name="sml040knSend" value="<gsmsg:write key="cmn.sent" />" type="submit" data-inline="true" data-role="button" data-icon="forward" /><input name="sml040knBack" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-iconpos="right" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>