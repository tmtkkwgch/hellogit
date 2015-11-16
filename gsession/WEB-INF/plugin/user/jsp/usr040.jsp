<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
  String strOk = jp.groupsession.v2.usr.GSConstUser.EXPORT_USE_OK;
  String labelSetOk = jp.groupsession.v2.usr.GSConstUser.LABEL_SET_OK;
  String labelEditOk = jp.groupsession.v2.usr.GSConstUser.LABEL_EDIT_OK;
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="user.144" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<logic:equal name="usr040Form" property="usr040webmail" value="1">
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

<body class="body_03" onunload="windowClose();">

<div id="FreezePane">


<html:form action="/user/usr040">
<input type="hidden" name="helpPrm" value="<bean:write name="usr040Form" property="usr040cmdMode" />">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="pushSearch" value="">

<html:hidden property="usr040cmdMode" />

<html:hidden property="selectgsidSave" />

<html:hidden property="usr040KeywordSave" />
<html:hidden property="usr040KeyKbnShainnoSave" />
<html:hidden property="usr040KeyKbnNameSave" />
<html:hidden property="usr040KeyKbnNameKnSave" />
<html:hidden property="usr040KeyKbnMailSave" />
<html:hidden property="usr040SearchKanaSave" />
<html:hidden property="usr040agefromSave" />
<html:hidden property="usr040agetoSave" />
<html:hidden property="usr040yakushokuSave" />
<html:hidden property="usr040tdfkCdSave" />
<html:hidden property="usr040entranceYearFrSave" />
<html:hidden property="usr040entranceMonthFrSave" />
<html:hidden property="usr040entranceDayFrSave" />
<html:hidden property="usr040entranceYearToSave" />
<html:hidden property="usr040entranceMonthToSave" />
<html:hidden property="usr040entranceDayToSave" />
<html:hidden property="usr040seibetuSave" />
<html:hidden property="usr040orderKeySave" />
<html:hidden property="usr040sortKeySave" />
<html:hidden property="usr040orderKey2Save" />
<html:hidden property="usr040sortKey2Save" />

<logic:notEmpty name="usr040Form" property="usr040labSidSave">
<logic:iterate id="labSidArray" name="usr040Form" property="usr040labSidSave" indexId="idx">
<input type="hidden" name="usr040labSidSave" value="<bean:write name="labSidArray" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040CategorySetInitFlg" />

<logic:notEmpty name="usr040Form" property="usr040CategoryOpenFlg">
<logic:iterate id="openFlg" name="usr040Form" property="usr040CategoryOpenFlg">
  <bean:define id="flg" name="openFlg" type="java.lang.String" />
  <html:hidden property="usr040CategoryOpenFlg" value="<%= flg %>" />
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040SearchFlg" />
<html:hidden property="usr040saveFlg" />
<html:hidden property="usr040DetailExeFlg" />

<html:hidden property="cmd" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="usr040webmail" />
<html:hidden property="usr040webmailAddress" />
<html:hidden property="usr040webmailType" />
<html:hidden property="usr040SendMailMode" />
<html:hidden property="usr040UsrSid" />
<html:hidden property="usr040DelUsrSid" />
<html:hidden property="usr040AddressKbn" />

<% String[] atskListName = {"usr040AtskList", "usr040CcList", "usr040BccList"}; %>
<% String[] atskParamName = {"usr040Atsk", "usr040Cc", "usr040Bcc"}; %>
<% for (int atskIdx = 0; atskIdx < 3; atskIdx++) { %>
<logic:notEmpty name="usr040Form" property="<%= atskListName[atskIdx] %>" scope="request">
  <% String usiMailName = ""; %>
  <logic:iterate id="atskMdl" name="usr040Form" property="<%= atskListName[atskIdx] %>" scope="request">
    <% for (int mailNo = 1; mailNo <= 3; mailNo++) { %>
    <%     usiMailName = "usiMail" + mailNo; %>
    <logic:notEmpty name="atskMdl" property="<%= usiMailName %>">
      <input type="hidden" name="<%= atskParamName[atskIdx] %>" value="<bean:write name="atskMdl" property="mailPersonal" /> &lt;<bean:write name="atskMdl" property="<%= usiMailName %>" />&gt;">
    </logic:notEmpty>
    <%     usiMailName = ""; %>
    <% } %>
  </logic:iterate>
</logic:notEmpty>
<% } %>

<logic:notEmpty name="usr040Form" property="usr040SidsAtsk" scope="request">
  <logic:iterate id="sidsAtsk" name="usr040Form" property="usr040SidsAtsk" scope="request">
    <input type="hidden" name="usr040SidsAtsk" value="<bean:write name="sidsAtsk"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr040Form" property="usr040SidsCc" scope="request">
  <logic:iterate id="sidsCc" name="usr040Form" property="usr040SidsCc" scope="request">
    <input type="hidden" name="usr040SidsCc" value="<bean:write name="sidsCc"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr040Form" property="usr040SidsBcc" scope="request">
  <logic:iterate id="sidsBcc" name="usr040Form" property="usr040SidsBcc" scope="request">
    <input type="hidden" name="usr040SidsBcc" value="<bean:write name="sidsBcc"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040DspFlg" />

<html:hidden property="usr040GrpSearchGIdSave" />
<html:hidden property="usr040GrpSearchGNameSave" />

<span id="usr040labelArea">
</span>

<logic:notEqual name="usr040Form" property="usr040webmail" value="1">
  <%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
</logic:notEqual>

<table cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>

<td width="100%" align="center">

  <table width="100%" cellpadding="0" border="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../user/images/header_user_01.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    <td width="0%" class="header_white_bg_text" align="left" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shain.info" /></span></td>
    <td width="0%" class="header_white_bg_text">
      <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NAME) %>">
        [ <gsmsg:write key="user.6" /> ]
      </logic:equal>
      <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP) %>">
        [ <gsmsg:write key="user.7" /> ]
      </logic:equal>
      <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_SHOUSAI) %>">
        [ <gsmsg:write key="cmn.advanced.search" /> ]
      </logic:equal>
    </td>
    <td width="100%" class="header_white_bg">
    <logic:notEqual name="usr040Form" property="usr040webmail" value="1">
      <logic:equal name="usr040Form" property="adminKbn" value="1">
      <input type="button" name="btn_admin" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('asetting');">
      </logic:equal>
      <input type="button" name="btn_kojn" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('psetting');"></td>
    </logic:notEqual>
    <logic:equal name="usr040Form" property="usr040webmail" value="1">
      <logic:equal name="usr040Form" property="usr040webmailType" value="1">
        <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailSendList(0, 'usr040SetMailMsg');">
      </logic:equal>
      <logic:notEqual name="usr040Form" property="usr040webmailType" value="1">
        <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailData(0, 'usr040SetMailMsg');">
      </logic:notEqual>
      <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td>
    </logic:equal>

    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0"  style="table-layout: fixed;">
    <tr>
    <td width="200px" valign="top" class="tl0">

      <logic:equal name="usr040Form" property="usr040webmail" value="1">
          <table width="100%" style="margin-top:0px;margin-right:0px;margin-bottom:15px;margin-left:0px;">
          <tr>
          <td align="center" width="50%" class="td_change_pop_sel">
            <span><gsmsg:write key="cmn.shain.info" /></span>
          </td>
          <td align="center" width="50%" class="td_change_pop_not_sel" onClick="changePopup();">
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
      <td class="sel_menu_area sel_menu_title" id="name" nowrap>
        <!-- 氏名 -->
        <span><gsmsg:write key="cmn.name" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="group" nowrap>
        <!-- グループ -->
        <span><gsmsg:write key="cmn.group" /></span>
      </td>
      </tr>

      <tr>
      <td class="sel_menu_area sel_menu_title" id="shousai" nowrap>
        <!-- 詳細検索 -->
        <span><gsmsg:write key="cmn.advanced.search" /></span>
      </td>
      </tr>

      </table>

      <%@ include file="/WEB-INF/plugin/user/jsp/usr040_labelList.jsp" %>

    </td>
    <td valign="top" style="padding-left:5px;width:auto;">

        <!-- エラーメッセージ -->
       <logic:messagesPresent message="false">
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
       <tr>
       <td align="left"><html:errors/></td>
       </tr>
       </table>
       </logic:messagesPresent>

       <table width="100%" cellpadding="0" cellspacing="0">
       <tr>
       <td width="100%" class="usr_search_area">

       <!-- 氏名 -->
       <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NAME) %>">
         <%@ include file="/WEB-INF/plugin/user/jsp/usr040_sub01.jsp" %>
       </logic:equal>

       <!-- グループ -->
       <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP) %>">
         <%@ include file="/WEB-INF/plugin/user/jsp/usr040_sub02.jsp" %>
       </logic:equal>

       <!-- 詳細検索 -->
       <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_SHOUSAI) %>">
         <%@ include file="/WEB-INF/plugin/user/jsp/usr040_sub03.jsp" %>
       </logic:equal>

       </td>
       </tr>

       <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_SHOUSAI) %>">
          <tr>
          <td style="padding-top:10px;">
             <table width="100%" cellpadding="0" cellspacing="0">
             <tr>
             <td colspan="2" align="center"><input type="button" name="btn_search" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('searchSyosai');"></td>
             </tr>
             </table>
         </td>
         </tr>
       </logic:equal>

       </table>

       <logic:notEqual name="usr040Form" property="usr040webmail" value="1">
         <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
       </logic:notEqual>

       <table width="100%" class="tl5">

       <logic:equal name="usr040Form" property="usr040webmail" value="1">
         <tr align="center" valign="center"><td id="usr040SetMailMsg" class="owAddressMsgArea_hide" width="100%">　</td></tr>
         <tr>
         <td width="0%" nowrap>
           <logic:equal name="usr040Form" property="usr040webmailType" value="1">
             <input type="button" name="addUsrAtesaki" value="<gsmsg:write key="anp.send.dest" />" class=btn_base1 onClick="checkUsr(0, 0, 'addUsrAtesaki');"/>
           </logic:equal>
           <logic:notEqual name="usr040Form" property="usr040webmailType" value="1">
             <input type="button" name="addUsrAtesaki" value="<gsmsg:write key="cmn.from" />" class=btn_base1s_1 onClick="checkUsr(0, 0, 'addUsrAtesaki');"/>
             <input type="button" name="addUsrCc" value="CC" class="btn_base1s_1" onClick="checkUsr(1, 0, 'addUsrCc');"/>
             <input type="button" name="addUsrBcc" value="BCC" class="btn_base1s_1" onClick="checkUsr(2, 0, 'addUsrBcc');"/>
           </logic:notEqual>
         </td>
         </tr>
       </logic:equal>

       <tr>
       <td align="left" width="100%" rowspan="2">
         <logic:equal name="usr040Form" property="usr040SearchFlg" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SEARCH_ZUMI) %>">
         <span class="text_search_title"><gsmsg:write key="cmn.search.criteria" /></span>
         <br>
         <% boolean writedSearch = false; %>
         <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
         <span class="text_search_key">
         <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NAME) %>">
           <logic:notEmpty name="usr040Form" property="usr040SearchKana">
           <% writedSearch = true; %>
          <bean:define id="usr040SrhKana" name="usr040Form" property="usr040SearchKana" type="java.lang.String" />
          <% String nameStr = gsMsg.getMessage(request, "cmn.name"); %>
          <gsmsg:write key="user.usr040.31" arg0="<%= nameStr %>" arg1="<%= usr040SrhKana %>" />
           <logic:notEmpty name="usr040Form" property="usr040DispKensakuJouken">(<bean:write name="usr040Form" property="usr040DispKensakuJouken" filter="false"/>)</logic:notEmpty>
           </logic:notEmpty>
         </logic:equal>
         <logic:equal name="usr040Form" property="usr040cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP) %>">
           <logic:notEmpty name="usr040Form" property="selectgpname">
           <% writedSearch = true; %>
           <bean:define id="selGpName" name="usr040Form" property="selectgpname" type="java.lang.String" />
           <gsmsg:write key="user.usr040.32" arg0="<%= selGpName %>" />
           <logic:notEmpty name="usr040Form" property="usr040DispKensakuJouken">(<bean:write name="usr040Form" property="usr040DispKensakuJouken" filter="false"/>)</logic:notEmpty>
           </logic:notEmpty>
         </logic:equal>

         <% if (!writedSearch) { %>
         <logic:notEmpty name="usr040Form" property="usr040DispKensakuJouken">
         <bean:define id="srhWord" name="usr040Form" property="usr040DispKensakuJouken" type="java.lang.String" />
         <gsmsg:write key="address.38" arg0="<%= srhWord %>" />
         </logic:notEmpty>
         <% } %>
         </span>
         </logic:equal>
       </td>

       <logic:notEqual name="usr040Form" property="usr040webmail" value="1">
       <td width="0%" nowrap>
         <logic:notEmpty name="usr040Form" property="usr040users">
         <logic:equal name="usr040Form" property="usrLabelSetKbn" value="<%= labelSetOk %>">
           <input type="button" name="btn_usrlabel" class="btn_label2" value="<gsmsg:write key="cmn.add.label" />" onClick="openlabel();btnOnly('labelSet');">&nbsp;
         </logic:equal>
         <logic:equal name="usr040Form" property="usr040CsvExportKbn" value="<%= strOk %>">
           <input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('usr040export');">
         </logic:equal>
         </logic:notEmpty>
       </td>
       </tr>
       </logic:notEqual>

       <logic:notEmpty name="usr040Form" property="usr040users">
         <bean:size id="count1" name="usr040Form" property="usr040PageLabel" scope="request" />
       <logic:greaterThan name="count1" value="1">
       <tr>
       <td align="right" width="0%">
         <table width="0%" cellpadding="0" cellspacing="0" border="0">
         <td align="right">
           <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onClick="buttonPush('arrorw_left');">&nbsp;</td>
         <td align="right">
           <html:select property="usr040pageNum1" onchange="changePage(1);" styleClass="text_i">
             <span class="text_base"><html:optionsCollection name="usr040Form" property="usr040PageLabel" value="value" label="label" /></span>
           </html:select>
         </td>
         <td align="right">
           &nbsp;<img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" border="0" onClick="buttonPush('arrorw_right');">
         </td>
         </tr>
         </table>
       </td>
       </tr>
       </logic:greaterThan>
       </logic:notEmpty>

       </table>

       <logic:notEqual name="usr040Form" property="usr040webmail" value="1">
         <%@ include file="/WEB-INF/plugin/user/jsp/usr040syain.jsp" %>
       </logic:notEqual>

       <logic:equal name="usr040Form" property="usr040webmail" value="1">
         <%@ include file="/WEB-INF/plugin/user/jsp/usr040webmail.jsp" %>
       </logic:equal>

       <logic:notEmpty name="usr040Form" property="usr040PageLabel">
          <bean:size id="count2" name="usr040Form" property="usr040PageLabel" scope="request" />
       <logic:greaterThan name="count2" value="1">
       <table width="100%" class="tl5">
       <tr>
       <td width="100%">&nbsp;</td>
       <td align="right">
         <table width="0%" cellpadding="0" cellspacing="0" border="0">
         <td align="right">
           <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0" onClick="buttonPush('arrorw_left');">&nbsp;
         </td>
         <td align="right">
           <html:select property="usr040pageNum2" onchange="changePage(2);" styleClass="text_i">
             <span class="text_base"><html:optionsCollection name="usr040Form" property="usr040PageLabel" value="value" label="label" /></span>
           </html:select>
         </td>
         <td align="right">
           &nbsp;<img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
         </td>
         </tr>
         </table>

       </td>
       </tr>
       </table>
       </logic:greaterThan>
       </logic:notEmpty>

     <logic:equal name="usr040Form" property="usr040webmail" value="1">
       <IMG SRC="../common/images/spacer.gif" width="1px" height="5px" border="0">
       <%@ include file="/WEB-INF/plugin/user/jsp/usr040webmailSend.jsp" %>
     </logic:equal>

    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<logic:equal name="usr040Form" property="usr040webmail" value="1">
<script language="JavaScript" src="../common/js/jquery.scrollTable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
(function() {
    <logic:notEmpty name="usr040Form" property="usr040users">
      var mySt = new superTable("demoTable", {
          fixedCols : 0,
          headerRows : 1
      });
    </logic:notEmpty>

})();
</script>
</logic:equal>

</html:form>

<logic:equal name="usr040Form" property="usr040webmail" value="1">
  <div width="100%" align="right">
    <logic:equal name="usr040Form" property="usr040webmailType" value="1">
      <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailSendList(0, 'usr040SetMailMsg');">
    </logic:equal>
    <logic:notEqual name="usr040Form" property="usr040webmailType" value="1">
      <input type="button" name="btn_apply" class="btn_base1" value="<gsmsg:write key="cmn.apply" />" onClick="setWebmailData(0, 'usr040SetMailMsg');">
    </logic:notEqual>
    <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.close();"></td>
  </div>
</logic:equal>

<logic:notEqual name="usr040Form" property="usr040webmail" value="1">
  <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</logic:notEqual>
</div>

<div id="labelPanel">
<div class="hd"><gsmsg:write key="cmn.select.a.label" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="lab" style="margin:0; padding:0; width:100%; height:400px" frameborder="no"></iframe></div>
</div>

</body>
</html:html>