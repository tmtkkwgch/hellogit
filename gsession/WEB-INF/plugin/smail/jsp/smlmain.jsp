<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<% String jtop = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN_FROM_TOP); %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%-- マーク --%>
<%
  String markAll       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ALL);

  String markNone      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE);
  String markTel       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL);
  String markImp       = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP);
  String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);
  String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);
  String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);
  String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);
  String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);
  String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);
  String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU);
%>

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
<title>[GroupSession] <gsmsg:write key="sml.smlmain.02" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>

<body class="body_03">
<html:form action="/smail/smlmain">
<input type="hidden" name="CMD" value="">

<logic:notEmpty name="smlmainForm" property="sml010SmlList" scope="request">

    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left" class="header_7D91BD_left" colspan="4">
      <img src="../smail/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.shortmail" />"><a href="<bean:write name="smlmainForm" property="smlTopUrl" />"><gsmsg:write key="cmn.shortmail" /></a>
    </td>
    </tr>

    <tr class="text_base2">
    <th class="td_type30" width="16%" scope="col" nowrap><gsmsg:write key="cmn.sender" /></th>
    <th class="td_type30" width="43%" scope="col" nowrap><gsmsg:write key="cmn.subject" /></th>
    <th class="td_type30" width="15%" scope="col" nowrap><gsmsg:write key="cmn.from" /></th>
    <th class="td_type30" width="26%" scope="col" nowrap><gsmsg:write key="cmn.date" /></th>
    </tr>

      <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>
      <logic:iterate id="msg" name="smlmainForm" property="sml010SmlList" indexId="idx" scope="request">
        <% String tdClass = tdClassList[idx.intValue() % 2]; %>

        <%-- マーク定義  --%>
        <bean:define id="imgMark"><bean:write name="msg" property="smsMark" /></bean:define>
        <tr>
        <logic:equal name="msg" property="smjOpkbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED) %>">
          <bean:define id="titleColor" value="text_link" />
        </logic:equal>
        <logic:equal name="msg" property="smjOpkbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED) %>">
          <bean:define id="titleColor" value="text_p" />
        </logic:equal>

        <bean:define id="sacMdl" name="msg" property="smlAccountData" />

        <td class="<%= tdClass %>" align="left" valign="middle">
          <span class="sc_ttl_def">
            <logic:equal name="msg" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
              <bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" />
            </logic:equal>
            <logic:equal name="msg" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
              <del><bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" /></del>
            </logic:equal>
          </span>
        </td>

        <td class="<%= tdClass %>" align="left" valign="middle">
          <%-- マーク --%><% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %>
          <a href="../smail/sml010.do?sml010scriptFlg=1&sml010scriptKbn=1&sml010SelectedSid=<bean:write name="msg" property="smlSid" />&smlViewAccount=<bean:write name="sacMdl" property="accountSid" />"><span class="<bean:write name="titleColor" />"><bean:write name="msg" property="smsTitle" /></span></a>
        </td>

        <td class="<%= tdClass %>" align="left" valign="middle">
          <bean:write name="sacMdl" property="accountName" />
        </td>

        <td class="<%= tdClass %>" align="center" valign="middle"><span class="sc_ttl_def"><bean:write name="msg" property="strSdate" /></span></td>
        </tr>

      </logic:iterate>

    </table>

</logic:notEmpty>

</html:form>


</body>
</html:html>