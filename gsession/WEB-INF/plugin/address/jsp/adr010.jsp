<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%@ page import="jp.groupsession.v2.adr.GSConstAddress" %>
<%@ page import="jp.groupsession.v2.adr.adr010.Adr010Const" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="cmn.addressbook" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/adrEntry.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adrcommon.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>

<logic:equal name="adr010Form" property="adr010webmail" value="1">
  <link rel=stylesheet href='../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
  <link rel="stylesheet" href="../common/css/jquery.scrolltable.css?<%= GSConst.VERSION_PARAM %>" />
  <script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/yui/yahoo-dom-event/yahoo-dom-event.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/yui/animation/animation-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/fader.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/webmailSubWindow.js?<%= GSConst.VERSION_PARAM %>"></script>

<style type="text/css">
.fakeContainer {
    margin: 0 0 0px;
    width:  100%;
    height: 200px;
    overflow: hidden;
}

.sData{
  overflow-x:hidden!important;
}
</style>

</logic:equal>
</head>

<bean:define id="cmdMode" name="adr010Form" property="adr010cmdMode" type="java.lang.Integer" />

<% if (cmdMode.intValue() == Adr010Const.CMDMODE_CONTACT) { %>
  <body class="body_03" onload="adr010DateKbn();" onunload="calWindowClose();">
<% } else { %>
  <body class="body_03" onunload="calWindowClose();">
<% } %>
<div id="FreezePane">

<html:form action="/address/adr010">
<html:hidden property="adr010webmail" />
<html:hidden property="adr010webmailAddress" />
<html:hidden property="adr010webmailType" />
<html:hidden property="adr010SendMailMode" />
<html:hidden property="adr010AdrSid" />
<html:hidden property="adr010DelAdrSid" />
<input type="hidden" name="adr010AdrType" value="0">

<% String[] atskListName = {"adr010AtskList", "adr010CcList", "adr010BccList"}; %>
<% String[] atskParamName = {"adr010Atsk", "adr010Cc", "adr010Bcc"}; %>
<% for (int atskIdx = 0; atskIdx < 3; atskIdx++) { %>
<logic:notEmpty name="adr010Form" property="<%= atskListName[atskIdx] %>" scope="request">
  <% String adrMailName = ""; %>
  <logic:iterate id="atskMdl" name="adr010Form" property="<%= atskListName[atskIdx] %>" scope="request">
    <% for (int mailNo = 1; mailNo <= 3; mailNo++) { %>
    <%     adrMailName = "adrMail" + mailNo; %>
    <logic:notEmpty name="atskMdl" property="<%= adrMailName %>">
    <logic:notEmpty name="atskMdl" property="adrSei">
      <input type="hidden" name="<%= atskParamName[atskIdx] %>" value="<bean:write name="atskMdl" property="adrMailPersonal" /> &lt;<bean:write name="atskMdl" property="<%= adrMailName %>" />&gt;">
    </logic:notEmpty>
    <logic:empty name="atskMdl" property="adrSei">
      <input type="hidden" name="<%= atskParamName[atskIdx] %>" value="<bean:write name="atskMdl" property="<%= adrMailName %>" />">
    </logic:empty>
    </logic:notEmpty>
    <%     adrMailName = ""; %>
    <% } %>
  </logic:iterate>
</logic:notEmpty>
<% } %>

<logic:notEmpty name="adr010Form" property="adr010SidsAtsk" scope="request">
  <logic:iterate id="sidsAtsk" name="adr010Form" property="adr010SidsAtsk" scope="request">
    <input type="hidden" name="adr010SidsAtsk" value="<bean:write name="sidsAtsk"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr010Form" property="adr010SidsCc" scope="request">
  <logic:iterate id="sidsCc" name="adr010Form" property="adr010SidsCc" scope="request">
    <input type="hidden" name="adr010SidsCc" value="<bean:write name="sidsCc"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr010Form" property="adr010SidsBcc" scope="request">
  <logic:iterate id="sidsBcc" name="adr010Form" property="adr010SidsBcc" scope="request">
    <input type="hidden" name="adr010SidsBcc" value="<bean:write name="sidsBcc"/>">
  </logic:iterate>
</logic:notEmpty>


<span id="adr010labelArea">
</span>

<input type="hidden" name="helpPrm" value="<bean:write name="adr010Form" property="adr010cmdMode" />">
<logic:notEqual name="adr010Form" property="adr010webmail" value="1">
  <%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
</logic:notEqual>

<input type="hidden" name="CMD" value="">
<input type="hidden" name="selectLabel" value="">

<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010searchFlg" />
<html:hidden property="adr010InitDspContactFlg" />
<html:hidden property="adr010EditAdrSid" />
<html:hidden property="adr010orderKey" />
<html:hidden property="adr010sortKey" />
<html:hidden property="adr010page" />
<input type="hidden" name="adr020ProcMode" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_ADD) %>">

<% if (cmdMode.intValue() == Adr010Const.CMDMODE_COMPANY) { %>
<html:hidden property="adr010svCode" />
<html:hidden property="adr010svCoName" />
<html:hidden property="adr010svCoNameKn" />
<html:hidden property="adr010svCoBaseName" />
<html:hidden property="adr010svAtiSid" />
<html:hidden property="adr010svTdfk" />
<html:hidden property="adr010svBiko" />
<html:hidden property="adr010SearchComKana" />
<html:hidden property="adr010svSearchComKana" />

<% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_NAME) { %>
<html:hidden property="adr010SearchKana" />
<html:hidden property="adr010svSearchKana" />

<% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_TANTO) { %>
<html:hidden property="adr010svTantoGroup" />
<html:hidden property="adr010svTantoUser" />

<% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_PROJECT) { %>
<html:hidden property="selectingProjectSv" />
<html:hidden property="projectKbnSv" />
<html:hidden property="statusKbnSv" />

<% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_DETAILED) { %>
<html:hidden property="adr010svUnameSei" />
<html:hidden property="adr010svUnameMei" />
<html:hidden property="adr010svUnameSeiKn" />
<html:hidden property="adr010svUnameMeiKn" />
<html:hidden property="adr010svDetailCoName" />
<html:hidden property="adr010svSyozoku" />
<html:hidden property="adr010svPosition" />
<html:hidden property="adr010svMail" />
<html:hidden property="adr010svDetailTantoGroup" />
<html:hidden property="adr010svDetailTantoUser" />
<html:hidden property="adr010svDetailAtiSid" />

<% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_CONTACT) { %>
<html:hidden property="adr010svTantoGroupContact" />
<html:hidden property="adr010svTantoUserContact" />
<html:hidden property="adr010svUnameSeiContact" />
<html:hidden property="adr010svUnameMeiContact" />
<html:hidden property="adr010svCoNameContact" />
<html:hidden property="adr010svCoBaseNameContact" />
<html:hidden property="adr010svProjectContact" />
<html:hidden property="adr010SvTempFilekbnContact" />
<html:hidden property="adr010svSltYearFrContact" />
<html:hidden property="adr010svSltMonthFrContact" />
<html:hidden property="adr010svSltDayFrContact" />
<html:hidden property="adr010svSltYearToContact" />
<html:hidden property="adr010svSltMonthToContact" />
<html:hidden property="adr010svSltDayToContact" />
<html:hidden property="adr010svSyubetsuContact" />
<html:hidden property="adr010svSearchWordContact" />
<html:hidden property="adr010SvKeyWordkbnContact" />
<html:hidden property="adr010svdateNoKbn" />

<logic:notEmpty name="adr010Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr010Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<% } %>

<logic:notEmpty name="adr010Form" property="adr010svSearchLabel">
<logic:iterate id="labelId" name="adr010Form" property="adr010svSearchLabel">
<input type="hidden" name="adr010svSearchLabel" value="<bean:write name="labelId" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr010CategorySetInitFlg" />

<logic:notEmpty name="adr010Form" property="adr010CategoryOpenFlg">
<logic:iterate id="openFlg" name="adr010Form" property="adr010CategoryOpenFlg">
  <bean:define id="flg" name="openFlg" type="java.lang.String" />
  <html:hidden property="adr010CategoryOpenFlg" value="<%= flg %>" />
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="adr100backFlg" value="">
<input type="hidden" name="adr110ProcMode" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.PROCMODE_EDIT) %>">
<input type="hidden" name="adr110editAcoSid" value="">

<%
  String markOther    = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_OTHER);
  String markTel      = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_TEL);
  String markMail     = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_MAIL);
  String markWeb      = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_WEB);
  String markMeeting  = String.valueOf(jp.groupsession.v2.cmn.GSConst.CONTYP_MEETING);

  java.util.HashMap imgMap = new java.util.HashMap();
  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
  String msgTel = gsMsg.getMessage(request, "cmn.phone");
  String msgMail = gsMsg.getMessage(request, "cmn.mail");
  String msgMeeting = gsMsg.getMessage(request, "address.28");
  String msgOther = gsMsg.getMessage(request, "cmn.other");

  imgMap.put(markTel, "<img src=\"../address/images/call.gif\" alt=" + "\"" + msgTel + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put(markMail, "<img src=\"../address/images/mail.gif\" alt=" + "\"" + msgMail + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put(markWeb, "<img src=\"../address/images/web.gif\" alt=\"Web\" border=\"0\" class=\"img_bottom\">");
  imgMap.put(markMeeting, "<img src=\"../address/images/uchiawase.gif\" alt=" + "\"" + msgMeeting + "\"" + " border=\"0\" class=\"img_bottom\">");
  imgMap.put("none", "&nbsp;");

  java.util.HashMap imgTextMap = new java.util.HashMap();
  imgTextMap.put(markTel, msgTel);
  imgTextMap.put(markMail, msgMail);
  imgTextMap.put(markWeb, "Web");
  imgTextMap.put(markMeeting, msgMeeting);
  imgTextMap.put(markOther, msgOther);

  imgTextMap.put("none", "&nbsp;");
%>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="left">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
  <td width="0%" class="header_white_bg_text">
  <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
    [ <gsmsg:write key="cmn.addressbook" /> ]
  </logic:notEqual>
  <logic:equal name="adr010Form" property="adr010webmail" value="1">
    [ <gsmsg:write key="address.31" /> ]
  </logic:equal>
  </td>
  <td width="100%" class="header_white_bg">
  <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
    <logic:equal name="adr010Form" property="adr010viewAdminBtn" value="1">
    <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('adminMenu');">
    </logic:equal>
    <input type="button" name="btn_kojn" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('personalMenu');">
  </logic:notEqual>
  <logic:equal name="adr010Form" property="adr010webmail" value="1">
    <logic:equal name="adr010Form" property="adr010webmailType" value="1">
      <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailSendList(1, 'adr010SetMailMsg');">
    </logic:equal>
    <logic:notEqual name="adr010Form" property="adr010webmailType" value="1">
      <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailData(1, 'adr010SetMailMsg');">
    </logic:notEqual>
    <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td>
  </logic:equal>
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
  </tr>
  </table>

  <table cellpadding="5" cellspacing="0" width="100%">
  <tr>
  <td align="right">
  <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
    <input type="button" value="<gsmsg:write key="cmn.new.registration" />" class="btn_add_address" onClick="buttonPush('addAdrData');">
    <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('import');">
    <logic:equal name="adr010Form" property="adr010viewYksBtn" value="1">
    <input type="button" value="<gsmsg:write key="address.adr010.1" />" class="btn_yks_n1" onClick="buttonPush('setupYakusyoku');">
    </logic:equal>
    <logic:equal name="adr010Form" property="adr010viewGyosyuBtn" value="1">
    <input type="button" value="<gsmsg:write key="address.adr010.2" />" class="btn_category" onClick="buttonPush('setupIndustry');">
    </logic:equal>
    <logic:equal name="adr010Form" property="adr010viewCompanyBtn" value="1">
    <input type="button" value="<gsmsg:write key="address.118" />" class="btn_company" onClick="buttonPush('setupCompany');">
    </logic:equal>
  </logic:notEqual>
  </td>
  </tr>
  </table>

  <table width="100%" class="tl0"  style="table-layout: fixed;">
  <tr>
  <td width="200px" valign="top" class="tl0">
      <logic:equal name="adr010Form" property="adr010webmail" value="1">

          <table width="100%" style="margin-top:0px;margin-right:0px;margin-bottom:15px;margin-left:0px;">
          <tr>
          <td align="center" width="50%" class="td_change_pop_not_sel" onClick="changePopup();">
            <span><gsmsg:write key="cmn.shain.info" /></span>
          </td>
          <td align="center" width="50%" class="td_change_pop_sel">
            <span><gsmsg:write key="cmn.addressbook" /></span>
          </td>
          </tr>
          </table>
      </logic:equal>


      <table width="100%" style="margin-top:0px;margin-right:0px;margin-bottom:3px;margin-left:0px;">

      <tr>
      <td class="detail_tbl" nowrap>
          <span class="sel_search_header"><gsmsg:write key="cmn.search.menu" /></span>
      </td>
      </tr>
      <tr>

      <td class="sel_menu_area sel_menu_title" id="0" nowrap>
        <!-- 会社 -->
        <span><gsmsg:write key="address.139" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="1" nowrap>
        <!-- 氏名 -->
        <span><gsmsg:write key="cmn.name" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="2" nowrap>
        <!-- 担当者 -->
        <span><gsmsg:write key="cmn.staff" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="3" nowrap>
        <!-- プロジェクト -->
        <span><gsmsg:write key="cmn.project" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="4" nowrap>
        <!-- 詳細検索 -->
        <span><gsmsg:write key="cmn.advanced.search" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="5" nowrap>
        <!-- コンタクト履歴 -->
        <span><gsmsg:write key="address.6" /></span>
      </td>
      </tr>

      </table>

      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_labelList.jsp" %>

  </td>

  <td valign="top" style="padding-left:5px;width:auto;">
  <logic:messagesPresent message="false">
  <IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr><td width="100%"><html:errors /></td></tr>
  </table>
  </logic:messagesPresent>

  <% if (cmdMode.intValue() == Adr010Const.CMDMODE_COMPANY) { %>
      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_company.jsp" %>
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_NAME) { %>
      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_name.jsp" %>
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_TANTO) { %>
      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_tanto.jsp" %>
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_PROJECT) { %>
      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_project.jsp" %>
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_DETAILED) { %>
      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_detailed.jsp" %>
  <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_CONTACT) { %>
      <%@ include file="/WEB-INF/plugin/address/jsp/adr010_contact.jsp" %>
  <% } %>

  <logic:notEmpty name="adr010Form" property="detailList">

  <table width="100%" class="tl5">

  <logic:equal name="adr010Form" property="adr010webmail" value="1">
    <tr align="center" valign="center"><td id="adr010SetMailMsg" class="owAddressMsgArea_hide" width="100%">　</td></tr>
    <tr>
    <td width="0%" nowrap>
      <logic:equal name="adr010Form" property="adr010webmailType" value="1">
        <input type="button" name="addUsrAtesaki" value="<gsmsg:write key="anp.send.dest" />" class="btn_base1" onclick="return addAddress(0, 0, 'addUsrAtesaki');"/>
      </logic:equal>
      <logic:notEqual name="adr010Form" property="adr010webmailType" value="1">
        <input type="button" name="addUsrAtesaki" value="<gsmsg:write key="cmn.from" />" class=btn_base1s_1 onclick="return addAddress(0, 0, 'addUsrAtesaki');"/>
        <input type="button" name="addUsrCc" value="CC" class="btn_base1s_1" onclick="return addAddress(1, 0, 'addUsrCc');"/>
        <input type="button" name="addUsrBcc" value="BCC" class="btn_base1s_1" onclick="return addAddress(2, 0, 'addUsrBcc');"/>
      </logic:notEqual>
    </td>
    </tr>
  </logic:equal>
  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="3px" border="0">
  </td>
  </tr>
  <tr>
  <td align="left" width="100%" rowspan="2">
    <span class="text_search_title"><gsmsg:write key="cmn.search.criteria" /></span><br>
    <% if (cmdMode.intValue() == Adr010Const.CMDMODE_NAME || cmdMode.intValue() == Adr010Const.CMDMODE_COMPANY) { %>

      <% boolean writedSearch = false; %>
      <% if (cmdMode.intValue() == Adr010Const.CMDMODE_COMPANY) { %>
        <logic:notEmpty name="adr010Form" property="adr010svSearchComKana">
        <span class="text_search_key">
        <bean:define id="comKana" name="adr010Form" property="adr010svSearchComKana" type="java.lang.String" />
        <% String comName = gsMsg.getMessage(request, "cmn.company.name"); %>
        <gsmsg:write key="user.usr040.31" arg0="<%= comName %>" arg1="<%= comKana %>" />
        <logic:notEmpty name="adr010Form" property="adr010searchLabelString">(<bean:write name="adr010Form" property="adr010searchLabelString" filter="false"/>)</logic:notEmpty>
        </span>
        <% writedSearch = true; %>
        </logic:notEmpty>

        <logic:notEmpty name="adr010Form" property="adr010searchParamString">
            <bean:define id="searchPrmOth" name="adr010Form" property="adr010searchParamString" type="java.lang.String" />
            <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= searchPrmOth %>" />
            <logic:notEmpty name="adr010Form" property="adr010searchLabelString">(<bean:write name="adr010Form" property="adr010searchLabelString" filter="false"/>)</logic:notEmpty>
            </span>
        <% writedSearch = true; %>
        </logic:notEmpty>
          <% if (!writedSearch) { %>
              <logic:empty  name="adr010Form" property="adr010searchLabelString">
                  <% String allSearch = gsMsg.getMessage(request, "address.adr010.7"); %>
                  <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= allSearch %>" /></span>
              </logic:empty>
              <logic:notEmpty  name="adr010Form" property="adr010searchLabelString">
                <bean:define id="srhLabel" name="adr010Form" property="adr010searchLabelString" type="java.lang.String" />
                <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= srhLabel %>" /></span>
              </logic:notEmpty>
          <% } %>

      <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_NAME) { %>
        <logic:notEmpty name="adr010Form" property="adr010svSearchKana">
        <span class="text_search_key">
        <bean:define id="adr010SrhKana" name="adr010Form" property="adr010svSearchKana" type="java.lang.String" />
        <% String nameStr = gsMsg.getMessage(request, "cmn.name"); %>
        <gsmsg:write key="user.usr040.31" arg0="<%= nameStr %>" arg1="<%= adr010SrhKana %>" />
        <logic:notEmpty name="adr010Form" property="adr010searchLabelString">(<bean:write name="adr010Form" property="adr010searchLabelString" filter="false"/>)</logic:notEmpty>
        </span>
        <% writedSearch = true; %>
        </logic:notEmpty>
          <% if (!writedSearch) { %>
              <logic:notEmpty  name="adr010Form" property="adr010searchLabelString">
                <bean:define id="adr010NameSrhLabel" name="adr010Form" property="adr010searchLabelString" type="java.lang.String" />
                <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= adr010NameSrhLabel %>" /></span>
              </logic:notEmpty>
          <% } %>
      <% } %>


    <% } else if (cmdMode.intValue() == Adr010Const.CMDMODE_PROJECT) { %>
      <logic:empty name="adr010Form" property="adr010searchParamString">
      <% String allProjects = gsMsg.getMessage(request, "cmn.allprojects"); %>
      <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= allProjects %>" /></span>
      </logic:empty>
      <logic:notEmpty name="adr010Form" property="adr010searchParamString">
      <bean:define id="adr010PrjString" name="adr010Form" property="adr010searchParamString" type="java.lang.String" />
      <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= adr010PrjString %>" /></span>
      </logic:notEmpty>

    <% } else { %>
      <logic:empty name="adr010Form" property="adr010searchParamString">
        <% String allSearchOth = gsMsg.getMessage(request, "address.adr010.7"); %>
        <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= allSearchOth %>" /></span>
      </logic:empty>
      <logic:notEmpty name="adr010Form" property="adr010searchParamString">
      <bean:define id="searchPrmOth" name="adr010Form" property="adr010searchParamString" type="java.lang.String" />
      <span class="text_search_key"><gsmsg:write key="address.38" arg0="<%= searchPrmOth %>" /></span>
      </logic:notEmpty>
    <% } %>
  </td>

  <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
  <td width="0%" nowrap>
    <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('adrDelete');">
    <input type="button" value="<gsmsg:write key="cmn.add.label" />" class="btn_label2" onClick="openlabel();">
    <% if (cmdMode.intValue() != Adr010Const.CMDMODE_CONTACT) { %>
    <logic:equal name="adr010Form" property="adr010viewExportBtn" value="1">
    <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('export');">
    </logic:equal>
    <% } else {%>
    <logic:equal name="adr010Form" property="adr010viewExportBtn" value="1">
    <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('exportContact');">
    </logic:equal>
    <% }%>
  </td>
  </logic:notEqual>
  </tr>

  <logic:notEmpty name="adr010Form" property="pageCmbList">
  <tr>
  <td align="right" width="0%">
    <table width="0%" cellpadding="0" cellspacing="0" border="0">
    <td align="right"><img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('prevPage');" style="cursor:pointer;">&nbsp;</td>
    <td align="right">
      <html:select name="adr010Form" property="adr010pageTop" styleClass="text_i" onchange="changePage('adr010pageTop');">
        <html:optionsCollection name="adr010Form" property="pageCmbList" value="value" label="label" />
      </html:select>
    </td>
    <td align="right">&nbsp;<img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('nextPage');" style="cursor:pointer;"></td>
    </tr>
    </table>
  </td>
  </tr>
  </logic:notEmpty>

  </table>

  <table width="100%" class="tl5">
  <tr>
  <td width="100%">
  <logic:notEqual name="adr010Form" property="adr010webmail" value="1">
    <%@ include file="/WEB-INF/plugin/address/jsp/adr010address.jsp" %>
  </logic:notEqual>

  <logic:equal name="adr010Form" property="adr010webmail" value="1">
    <%@ include file="/WEB-INF/plugin/address/jsp/adr010webmail.jsp" %>
  </logic:equal>
  </td>
  </tr>
  </table>

  <logic:notEmpty name="adr010Form" property="pageCmbList">
  <table width="100%" class="tl5">
  <tr>
  <td width="100%">&nbsp;</td>
  <td align="right">

    <table width="0%" cellpadding="0" cellspacing="0" border="0">
    <td align="right"><img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('prevPage');" style="cursor:pointer;">&nbsp;</td>
    <td align="right">
      <html:select name="adr010Form" property="adr010pageBottom" styleClass="text_i" onchange="changePage('adr010pageBottom');">
        <html:optionsCollection name="adr010Form" property="pageCmbList" value="value" label="label" />
      </html:select>
    </td>
    <td align="right">&nbsp;<img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" onClick="buttonPush('nextPage');" style="cursor:pointer;"></td>
    </tr>
    </table>

  </td>
  </tr>
  </table>
  </logic:notEmpty>
  </logic:notEmpty>

<logic:equal name="adr010Form" property="adr010webmail" value="1">
  <%@ include file="/WEB-INF/plugin/address/jsp/adr010webmailSend.jsp" %>
</logic:equal>

</td>
  </tr>
  </table>

</td>
</tr>
</table>

<logic:equal name="adr010Form" property="adr010webmail" value="1">
<script language="JavaScript" src="../common/js/jquery.scrollTable.js"></script>
<script type="text/javascript">
(function() {
    <logic:notEmpty name="adr010Form" property="detailList">
      var mySt = new superTable("demoTable", {
          fixedCols : 0,
          headerRows : 1
      });
    </logic:notEmpty>

})();
</script>
</logic:equal>

</html:form>

<logic:equal name="adr010Form" property="adr010webmail" value="1">
  <div width="100%" align="right">
    <logic:equal name="adr010Form" property="adr010webmailType" value="1">
      <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailSendList(1, 'adr010SetMailMsg');">
    </logic:equal>
    <logic:notEqual name="adr010Form" property="adr010webmailType" value="1">
      <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailData(1, 'adr010SetMailMsg');">
    </logic:notEqual>
    <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td>
  </div>
</logic:equal>

<logic:notEqual name="adr010Form" property="adr010webmail" value="1">
  <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</logic:notEqual>
</div>

<div id="labelPanel">
<div class="hd"><gsmsg:write key="cmn.select.a.label" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="lab" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>

</body>
</html:html>