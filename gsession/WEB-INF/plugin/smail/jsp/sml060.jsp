<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String markNone      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_NONE); %>
<% String markTel = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL); %>
<% String markImp = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP); %>
<% String markSmaily    = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SMAILY);  %>
<% String markWorry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_WORRY);   %>
<% String markAngry     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ANGRY);   %>
<% String markSadly     = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_SADRY);   %>
<% String markBeer      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_BEER);    %>
<% String markHart      = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_HART);    %>
<% String markZasetsu   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_ZASETSU); %>

<% String maxLengthText = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MAX_LENGTH_TEXT); %>

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
<script language="JavaScript" src="../smail/js/sml060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession]
<logic:equal name="sml060Form" property="selectedHinaSid" value="0"><gsmsg:write key="sml.sml060.03" /></logic:equal>
<logic:notEqual name="sml060Form" property="selectedHinaSid" value="0"><gsmsg:write key="sml.sml060.04" /></logic:notEqual>

<logic:equal name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>"><gsmsg:write key="sml.sml060.01" /></logic:equal>
<logic:equal name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>"><gsmsg:write key="sml.sml060.02" /></logic:equal>
</title>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthText %>, 'inputlength');">
<html:form action="/smail/sml060">
<input type="hidden" name="CMD" value="add_kn">
<html:hidden property="backScreen" />
<html:hidden property="selectedHinaSid" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml050HinaKbn" />
<html:hidden property="sml050InitFlg" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />

<logic:notEmpty name="sml060Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml060Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml050ProcMode" />
<html:hidden property="sml050Sort_key" />
<html:hidden property="sml050Order_key" />
<html:hidden property="sml050PageNum" />

<logic:notEqual name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>">

<logic:lessEqual name="sml060Form" property="selectedHinaSid" value="0">
  <input type="hidden" name="helpPrm" value="0">
</logic:lessEqual>
<logic:greaterThan name="sml060Form" property="selectedHinaSid" value="0">
  <input type="hidden" name="helpPrm" value="1">
</logic:greaterThan>

</logic:notEqual>

<logic:equal name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>">

<logic:lessEqual name="sml060Form" property="selectedHinaSid" value="0">
  <input type="hidden" name="helpPrm" value="2">
</logic:lessEqual>
<logic:greaterThan name="sml060Form" property="selectedHinaSid" value="0">
  <input type="hidden" name="helpPrm" value="3">
</logic:greaterThan>

</logic:equal>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
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
    <td width="100%" class="header_white_bg_text">
      <logic:equal name="sml060Form" property="selectedHinaSid" value="0">[ <gsmsg:write key="sml.sml060.03" /></logic:equal>
      <logic:notEqual name="sml060Form" property="selectedHinaSid" value="0">[ <gsmsg:write key="sml.sml060.04" /></logic:notEqual>
<logic:equal name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>"><gsmsg:write key="sml.sml060.01" /> ]</logic:equal>
<logic:equal name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>"><gsmsg:write key="sml.sml060.02" /> ]</logic:equal>

    </td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="submit" value="OK" class="btn_ok1" onClick="return onControlSubmit();">
      <logic:greaterThan name="sml060Form" property="selectedHinaSid" value="0">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
      </logic:greaterThan>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_from_hina_add');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" alt="" height="10px" width="10px">
    <html:errors />
    <img src="../common/images/spacer.gif" alt="" height="10px" width="10px">

    <table width="100%" class="tl0" border="0" cellpadding="5">


    <logic:equal name="sml060Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>">

      <tr>
      <th class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /><gsmsg:write key="wml.102" /></span></th>
      <td align="left" class="td_wt" width="100%">
        <logic:equal name="sml060Form" property="selectedHinaSid" value="0">
          <div id="accountSelArea"><span id="selAccountNameArea"><bean:write name="sml060Form" property="sml060AccountName" /></span>　<input id="accountSelBtn" type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" ></div>
        </logic:equal>
        <logic:notEqual name="sml060Form" property="selectedHinaSid" value="0">
          <span id="selAccountNameArea"><bean:write name="sml060Form" property="sml060AccountName" /></span>
        </logic:notEqual>
      </td>
      </tr>

    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="sml.template.name" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" width="90%" class="td_wt">
      <html:text styleClass="text_base" style="width:335px;" maxlength="50" name="sml060Form" property="sml060HinaName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.subject" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">
      <html:text styleClass="text_base" style="width:335px;" maxlength="50" name="sml060Form" property="sml060HinaTitle" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.mark" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">
      <span class="text_base">
        <html:radio styleId="sml060HinaMark_0" name="sml060Form" property="sml060HinaMark" value="<%= markNone %>" /><label for="sml060HinaMark_0"><gsmsg:write key="cmn.no3" /></label>&nbsp;
        <html:radio styleId="sml060HinaMark_1" name="sml060Form" property="sml060HinaMark" value="<%= markTel %>" />&nbsp;<label for="sml060HinaMark_1"><%= (String) imgMap.get(markTel) %><%= (String) imgTextMap.get(markTel) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_2" name="sml060Form" property="sml060HinaMark" value="<%= markImp %>" />&nbsp;<label for="sml060HinaMark_2"><%= (String) imgMap.get(markImp) %><%= (String) imgTextMap.get(markImp) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_3" name="sml060Form" property="sml060HinaMark" value="<%= markSmaily %>" />&nbsp;<label for="sml060HinaMark_3"><%= (String) imgMap.get(markSmaily) %><%= (String) imgTextMap.get(markSmaily) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_4" name="sml060Form" property="sml060HinaMark" value="<%= markWorry %>" />&nbsp;<label for="sml060HinaMark_4"><%= (String) imgMap.get(markWorry) %><%= (String) imgTextMap.get(markWorry) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_5" name="sml060Form" property="sml060HinaMark" value="<%= markAngry %>" />&nbsp;<label for="sml060HinaMark_5"><%= (String) imgMap.get(markAngry) %><%= (String) imgTextMap.get(markAngry) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_6" name="sml060Form" property="sml060HinaMark" value="<%= markSadly %>" />&nbsp;<label for="sml060HinaMark_6"><%= (String) imgMap.get(markSadly) %><%= (String) imgTextMap.get(markSadly) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_7" name="sml060Form" property="sml060HinaMark" value="<%= markBeer %>" />&nbsp;<label for="sml060HinaMark_7"><%= (String) imgMap.get(markBeer) %><%= (String) imgTextMap.get(markBeer) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_8" name="sml060Form" property="sml060HinaMark" value="<%= markHart %>" />&nbsp;<label for="sml060HinaMark_8"><%= (String) imgMap.get(markHart) %><%= (String) imgTextMap.get(markHart) %></label>&nbsp;
        <html:radio styleId="sml060HinaMark_9" name="sml060Form" property="sml060HinaMark" value="<%= markZasetsu %>" />&nbsp;<label for="sml060HinaMark_9"><%= (String) imgMap.get(markZasetsu) %><%= (String) imgTextMap.get(markZasetsu) %></label>&nbsp;

      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.210" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">
      <textarea name="sml060HinaBody" style="width:716px;" rows="20" wrap="soft" onkeyup="showLengthStr(value, <%= maxLengthText %>, 'inputlength');" id="inputstr"><bean:write name="sml060Form" property="sml060HinaBody" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthText %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" alt="" height="10px" width="10px">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="submit" value="OK" class="btn_ok1" onClick="return onControlSubmit();">
      <logic:greaterThan name="sml060Form" property="selectedHinaSid" value="0">
        <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
      </logic:greaterThan>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_from_hina_add');">
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


<div id="accountSelPop" title="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" style="display:none">
  <input type="hidden" id="selAccountElm" value="smlAccountSid" />
  <input type="hidden" id="selAccountSubmit" value="false" />
  <input type="hidden" id="sml240user" value="<bean:write name="sml060Form" property="smlViewUser" />" />
  <div style="height:460px;overflow-y:auto;">
  <table width="100%" height="100%">
    <tr>
      <td id="accountListArea" valign="top"></td>
    </tr>
  </table>
  </div>
</div>


<div id="setKakuninPop" title="" style="display:none">
  <table width="100%" height="100%">
    <tr>
      <td rowspan="2" valign="top">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td style="padding-left:15px;padding-top:10px;">
        <span class="text_base1"><gsmsg:write key="sml.170" /></span>
      </td>
    </tr>
   <tr>
      <td  style="padding-left:15px;padding-top:10px;" colspan="2" valign="top">
        <div id="accountKakuninListArea" style="width:300px;height:100px;overflow-y:scroll;"></div>
      </td>
    </tr>
  </table>
</div>


</body>
</html:html>