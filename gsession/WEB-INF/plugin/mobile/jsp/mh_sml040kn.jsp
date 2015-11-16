<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhSml040knForm"; %>
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

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shortmail" />(<gsmsg:write key="cmn.check" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form method="post" action="/mobile/mh_sml040kn">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />

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
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />
<html:hidden property="sml040ProcMode" />
<html:hidden property="sml040Title" />
<html:hidden property="sml040Mark" />
<html:hidden property="sml040Body" />

<b><%= emojiMail.toString() %><gsmsg:write key="cmn.shortmail" /><br><gsmsg:write key="cmn.check" /></b>

<hr>
<logic:messagesPresent message="false">
<span style="color: red"><html:errors/></span>
</logic:messagesPresent>

■<gsmsg:write key="cmn.sender" />
<br>
<bean:write name="mbhSml040knForm"  property="smlViewAccountName" />
<br>
■<gsmsg:write key="cmn.from" />
<br>
<logic:notEmpty name="mbhSml040knForm" property="sml040SelectUserList">
  <logic:iterate id="usrLabel" name="mbhSml040knForm" property="sml040SelectUserList" indexId="idx">
    <bean:write name="usrLabel" property="label" /><br>
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml040knForm" property="sml040CcSelectUserList">
■CC
<br>
<logic:iterate id="usrCcLabel" name="mbhSml040knForm" property="sml040CcSelectUserList" indexId="idx">
  <bean:write name="usrCcLabel" property="label" /><br>
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml040knForm" property="sml040BccSelectUserList">
■BCC
<br>
<logic:iterate id="usrBccLabel" name="mbhSml040knForm" property="sml040BccSelectUserList" indexId="idx">
  <bean:write name="usrBccLabel" property="label" /><br>
</logic:iterate>
</logic:notEmpty>

■<gsmsg:write key="cmn.subject" />
<br>
<bean:write name="mbhSml040knForm" property="sml040Title" />
<br>
■<gsmsg:write key="cmn.mark" />
<br>
<logic:equal name="mbhSml040knForm"  property="sml040Mark" value="<%= markNone %>"><gsmsg:write key="cmn.no3" /></logic:equal>
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

<br>
■<gsmsg:write key="cmn.body" />
<br>
<bean:write name="mbhSml040knForm" property="sml040knBody" filter="false"/>
<br>
<div align="center">
  <br><input name="sml040knSend" value="<gsmsg:write key="cmn.sent" />" type="submit"><input name="sml040knBack" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>