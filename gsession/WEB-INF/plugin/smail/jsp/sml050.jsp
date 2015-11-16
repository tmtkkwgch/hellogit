<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../smail/js/sml050.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession]
<logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>"><gsmsg:write key="sml.sml050.01" /></logic:equal>
<logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>"><gsmsg:write key="sml.sml050.02" /></logic:equal>
</title>
</head>

<body class="body_03">
<html:form action="/smail/sml050">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="selectedHinaSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml050HinaKbn" />
<html:hidden property="sml050InitFlg" />


<logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>">
  <input type="hidden" name="helpPrm" value="1">
</logic:equal>
<logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>

<logic:notEmpty name="sml050Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml050Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml050Sort_key" />
<html:hidden property="sml050Order_key" />
<html:hidden property="sml050PageNum" />


<input type="hidden" name="sml050HinaKbn" value="1" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../smail/images/header_smail03_7_01.gif" border="0" alt="<gsmsg:write key="cmn.shortmail" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shortmail" /></span></td>
    <td width="0%" class="header_white_bg_text">
    <logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_CMN) %>">[ <gsmsg:write key="sml.sml050.01" /> ]</logic:equal>
    <logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>">[ <gsmsg:write key="sml.sml050.02" /> ]</logic:equal>
    </td>

    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="buttonPush('hina_add');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backFromHina');"></td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <br>

    <logic:equal name="sml050Form" property="sml050HinaKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI) %>">


          <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
          <tr>
          <th class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /><gsmsg:write key="wml.102" /></span></th>
          <td align="left" class="td_wt" width="100%">
            <div id="accountSelArea"><span id="selAccountNameArea"><bean:write name="sml050Form" property="sml050AccountName" /></span>　<input id="accountSelBtn" type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="wml.102" /><gsmsg:write key="cmn.select" />" ></div>
          </td>
          </tr>
          </table>

      <br>
    </logic:equal>

    <table width="100%" border="0" cellpadding="0" cellspacing="3">
    <tr>
    <td colspan="2" align="right">

    <logic:notEmpty name="sml050Form" property="sml050PageLabel" scope="request">
    <bean:size id="count1" name="sml050Form" property="sml050PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
      <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">
      <logic:notEmpty name="sml050Form" property="sml050PageLabel">
        <html:select property="sml050Slt_page1" onchange="changePage1();" styleClass="text_i">
          <html:optionsCollection name="sml050Form" property="sml050PageLabel" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
      <logic:empty name="sml050Form" property="sml050PageLabel">
        <html:select property="sml050Slt_page1">
          <option value="1">1 / 1</option>
        </html:select>
      </logic:empty>
      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
    </logic:greaterThan>
    </logic:notEmpty>
    </td>
    </tr>
    </table>

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
    <tr class="td_type3">
      <logic:equal name="sml050Form" property="sml050Sort_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_NAME) %>">
        <logic:equal name="sml050Form" property="sml050Order_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>">
          <th width="40%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC) %>')"><span class="text_base3"><gsmsg:write key="sml.template.name" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
        </logic:equal>
        <logic:equal name="sml050Form" property="sml050Order_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC) %>">
          <th width="40%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>')"><span class="text_base3"><gsmsg:write key="sml.template.name" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="sml050Form" property="sml050Sort_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_NAME) %>">
        <th width="40%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_NAME) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>')"><span class="text_base3"><gsmsg:write key="sml.template.name" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="sml050Form" property="sml050Sort_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_TITLE) %>">
        <logic:equal name="sml050Form" property="sml050Order_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>">
          <th width="60%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC) %>')"><span class="text_base3"><gsmsg:write key="cmn.subject" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
        </logic:equal>
        <logic:equal name="sml050Form" property="sml050Order_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC) %>">
          <th width="60%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>')"><span class="text_base3"><gsmsg:write key="cmn.subject" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="sml050Form" property="sml050Sort_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_TITLE) %>">
        <th width="60%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_TITLE) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>')"><span class="text_base3"><gsmsg:write key="cmn.subject" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="sml050Form" property="sml050Sort_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_MARK) %>">
        <logic:equal name="sml050Form" property="sml050Order_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>">
          <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_MARK) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC) %>')"><span class="text_base3"><gsmsg:write key="cmn.mark" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
        </logic:equal>
        <logic:equal name="sml050Form" property="sml050Order_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC) %>">
          <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_MARK) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>')"><span class="text_base3"><gsmsg:write key="cmn.mark" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
        </logic:equal>
      </logic:equal>
      <logic:notEqual name="sml050Form" property="sml050Sort_key" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_MARK) %>">
        <th width="0%" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_SORT_KEY_MARK) %>', '<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC) %>')"><span class="text_base3"><gsmsg:write key="cmn.mark" /></span></a></th>
      </logic:notEqual>

      <th width="0%">&nbsp;</th>
      </tr>

      <logic:notEmpty name="sml050Form" property="sml050HinaList" scope="request">
        <bean:define id="mod" value="0" />
        <logic:iterate id="hina" name="sml050Form" property="sml050HinaList" indexId="idx" scope="request">
          <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
            <bean:define id="tblColor" value="smail_td1" />
          </logic:equal>
          <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
            <bean:define id="tblColor" value="smail_td2" />
          </logic:notEqual>

<%-- マーク定義  --%>
        <bean:define id="imgMark"><bean:write name="hina" property="shnMark" /></bean:define>
          <tr>
          <td class="<bean:write name="tblColor" />" align="left" valign="middle"><bean:write name="hina" property="shnHname" /></td>
          <td class="<bean:write name="tblColor" />" align="left" valign="middle"><bean:write name="hina" property="shnTitle" /></td>
          <td class="<bean:write name="tblColor" />" align="center" valign="middle">
            <%-- マーク --%><% java.lang.String key = "none";  if (imgMap.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap.get(key) %>
          </td>
          <td class="<bean:write name="tblColor" />" align="center" valign="middle"><input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n3" onClick="moveEdit('<bean:write name="hina" property="shnSid" />')"></td>
          </tr>
        </logic:iterate>
      </logic:notEmpty>
    </table>

    <table width="100%" border="0" cellpadding="5"  cellspacing="0">
    <tr>
    <td colspan="2" align="right">
    <logic:notEmpty name="sml050Form" property="sml050PageLabel" scope="request">
    <bean:size id="count2" name="sml050Form" property="sml050PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">
      <img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('arrorw_left');">
      <logic:notEmpty name="sml050Form" property="sml050PageLabel">
        <html:select property="sml050Slt_page2" onchange="changePage2();" styleClass="text_i">
          <html:optionsCollection name="sml050Form" property="sml050PageLabel" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
      <logic:empty name="sml050Form" property="sml050PageLabel">
        <html:select property="sml050Slt_page2">
          <option value="1">1 / 1</option>
        </html:select>
      </logic:empty>
      <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
    </logic:greaterThan>
    </logic:notEmpty>
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="buttonPush('hina_add');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backFromHina');">
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
  <input type="hidden" id="selAccountSubmit" value="true" />
  <input type="hidden" id="sml240user" value="<bean:write name="sml050Form" property="smlViewUser" />" />


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