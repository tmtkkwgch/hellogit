<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String jusin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_JUSIN); %>
<% String sosin = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOSIN); %>
<% String soko = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_SOKO); %>
<% String gomi = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.TAB_DSP_MODE_GOMIBAKO); %>
<% String mark = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_MARK); %>
<% String ttl = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_TITLE); %>
<% String kname = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_NAME); %>
<% String date = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MSG_SORT_KEY_DATE); %>
<% String asc = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_ASC); %>
<% String desc = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.ORDER_KEY_DESC); %>

<% String unopend = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_UNOPENED); %>
<% String opend = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.OPKBN_OPENED); %>
<% String toroku = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU); %>
<% String delete = String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE); %>

<% String hkbnPri = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.HINA_KBN_PRI); %>

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
  imgMap.put(markTel, "&lt;img src=&quot;../smail/images/call.gif&quot; alt=&quot;" + phone + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markImp, "&lt;img src=&quot;../smail/images/zyuu.gif&quot; alt=&quot;" + important + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markSmaily, "&lt;img src=&quot;../smail/images/icon_face01.gif&quot; alt=&quot;" + smile + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markWorry, "&lt;img src=&quot;../smail/images/icon_face02.gif&quot; alt=&quot;" + worry + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markAngry, "&lt;img src=&quot;../smail/images/icon_face03.gif&quot; alt=&quot;" + angry + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markSadly, "&lt;img src=&quot;../smail/images/icon_face04.gif&quot; alt=&quot;" + sad + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markBeer, "&lt;img src=&quot;../smail/images/icon_beer.gif&quot; alt=&quot;" + beer + "&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markHart, "&lt;img src=&quot;../smail/images/icon_hart.gif&quot; alt=&quot;" + hart +"&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put(markZasetsu, "&lt;img src=&quot;../smail/images/icon_zasetsu.gif&quot; alt=&quot;" + tired +"&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;");
  imgMap.put("none", "");


  java.util.HashMap imgMap2 = new java.util.HashMap();
  imgMap2.put(markTel, "<img src=\"../smail/images/call.gif\" alt=\"" + phone + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markImp, "<img src=\"../smail/images/zyuu.gif\" alt=\"" + important + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markSmaily, "<img src=\"../smail/images/icon_face01.gif\" alt=\"" + smile + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markWorry, "<img src=\"../smail/images/icon_face02.gif\" alt=\"" + worry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markAngry, "<img src=\"../smail/images/icon_face03.gif\" alt=\"" + angry + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markSadly, "<img src=\"../smail/images/icon_face04.gif\" alt=\"" + sad + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markBeer, "<img src=\"../smail/images/icon_beer.gif\" alt=\"" + beer + "\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markHart, "<img src=\"../smail/images/icon_hart.gif\" alt=\"" + hart +"\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put(markZasetsu, "<img src=\"../smail/images/icon_zasetsu.gif\" alt=\"" + tired +"\" border=\"0\" class=\"img_bottom\">");
  imgMap2.put("none", "");
%>


<input type="hidden" id="markKey_0" value="" />
<input type="hidden" id="markKey_<%= markTel %>" value="&lt;img src=&quot;../smail/images/call.gif&quot; alt=&quot;<%= phone %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markImp %>" value="&lt;img src=&quot;../smail/images/zyuu.gif&quot; alt=&quot;<%= important %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markSmaily %>" value="&lt;img src=&quot;../smail/images/icon_face01.gif&quot; alt=&quot;<%= smile %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markWorry %>" value="&lt;img src=&quot;../smail/images/icon_face02.gif&quot; alt=&quot;<%= worry %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markAngry %>" value="&lt;img src=&quot;../smail/images/icon_face03.gif&quot; alt=&quot;<%= angry %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markSadly %>" value="&lt;img src=&quot;../smail/images/icon_face04.gif&quot; alt=&quot;<%= sad %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markBeer %>" value="&lt;img src=&quot;../smail/images/icon_beer.gif&quot; alt=&quot;<%= beer %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markHart %>" value="&lt;img src=&quot;../smail/images/icon_hart.gif&quot; alt=&quot;<%= hart %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="markKey_<%= markZasetsu %>" value="&lt;img src=&quot;../smail/images/icon_zasetsu.gif&quot; alt=&quot;<%= tired %>&quot; border=&quot;0&quot; class=&quot;img_bottom&quot;&gt;" />
<input type="hidden" id="none" value="" />



<%
  String targetTitle   = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SEARCH_TARGET_TITLE);
  String targetHonbun  = String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SEARCH_TARGET_HONBUN);
%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../smail/js/smljquery.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml010.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/glayer.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src="../common/js/jquery.contextmenu.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../portal/js/tiny_mce/tiny_mce.js?ae<%= GSConst.VERSION_PARAM %>"></script>





<script type="text/javascript">
<!--
  //自動リロード
  <logic:notEqual name="sml010Form" property="sml010Reload" value="0">
    var reloadinterval = <bean:write name="sml010Form" property="sml010Reload" />;
    setInterval("reloadData()",reloadinterval);
  </logic:notEqual>
-->
</script>



<logic:notEqual name="sml010Form" property="sml010AccountTheme" value="0">
<link rel=stylesheet href="../common/css/theme<bean:write name="sml010Form" property="sml010AccountTheme" />/theme.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</logic:notEqual>
<logic:equal name="sml010Form" property="sml010AccountTheme" value="0">
<theme:css filename="theme.css"/>
</logic:equal>

<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[GroupSession] <gsmsg:write key="sml.19" /></title>
</head>

<body id="sml010body" class="body_03 yui-skin-sam" onload="showLengthId($('#inputstr')[0], <%= maxLengthText %>, 'inputlength');" onunload="windowClose();">

<html:form styleId="smlForm" action="/smail/sml010">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="SERCHFLG" value="">

<input type="hidden" name="PROCMODE" value="">
<input type="hidden" name="SELECTSID" value="">
<input type="hidden" name="SELECTKBN" value="">
<input type="hidden" name="ACCOUNT" value="">

<html:hidden property="smlAccountSid" />
<html:hidden property="sml010AccountSendMailType" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml010SelectedMailKbn" />
<html:hidden property="sml010EditSid" />
<html:hidden property="sml010scriptSelUsrSid" styleId="sml010scriptSelUsrSid" />
<html:hidden property="sml010scriptSelUsrName" styleId="sml010scriptSelUsrName" />
<html:hidden property="sml010scriptSelSacSid" styleId="sml010scriptSelSacSid" />
<html:hidden property="sml010usrSid" />
<html:hidden property="sml010scriptFlg"/>
<html:hidden property="sml010scriptKbn"/>
<html:hidden property="sml050HinaKbn" />
<html:hidden property="tempDspFlg"/>

<html:hidden property="sml010SelectLabelSid"/>
<html:hidden property="sml010addLabelType"/>
<html:hidden property="sml010addLabel"/>
<html:hidden property="sml010addLabelName"/>
<html:hidden property="sml010delLabel"/>

<div id="sml010banUserSidArea"></div>
<div id="sml010disableGroupSidArea"></div>

<html:hidden property="sml020webmail"/>
<html:hidden property="sml020webmailId"/>
<html:hidden property="sml020ProcMode" />

<div id="sml010LabelTabSelArea"></div>
<div id="sml020ProcModeArea"></div>
<div id="sml020SelectHinaIdArea"></div>


<logic:notEmpty name="sml010Form" property="sml010SelectedDelSid" scope="request">
  <logic:iterate id="select" name="sml010Form" property="sml010SelectedDelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="select"/>">
  </logic:iterate>
</logic:notEmpty>

<div id="scriptSelUsrArea">
<logic:notEmpty name="sml010Form" property="sml010scriptUsrList" scope="request">
  <logic:iterate id="scriptSelUsrMdl" name="sml010Form" property="sml010scriptUsrList" scope="request">
    <input type="hidden" class="scriptSelUsrParams" value="<bean:write name="scriptSelUsrMdl" property="usrSid" />:<bean:write name="scriptSelUsrMdl" property="sacName" />" />
  </logic:iterate>
</logic:notEmpty>
</div>

<%-- 検索画面で使用 --%>


<input type="hidden" name="helpPrm" value="<bean:write name="sml010Form" property="sml010ProcMode" />">

<% boolean callWebmail = true; %>
<logic:notEqual name="sml010Form" property="sml020webmail" value="1">
<% callWebmail = false; %>
</logic:notEqual>

<% boolean callScript = false; %>
<logic:notEmpty name="sml010Form" property="sml010scriptSelUsrSid">
<logic:greaterThan name="sml010Form" property="sml010scriptSelUsrSid" value="0">
<% callScript = true; %>
</logic:greaterThan>
</logic:notEmpty>

<% if (!callWebmail && !callScript) { %>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<% } %>

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
    <td width="0%" class="header_white_bg_text"></td>
    <td width="100%" class="header_white_bg">
<% if (callWebmail) { %>
      <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.parent.webmailEntrySubWindowClose();">
<% } else if (callScript) { %>
      <input type="button" name="btn_close" class="btn_close_n1" value="<gsmsg:write key="cmn.close" />" onClick="window.parent.callSmailWindowClose();">
<% } else { %>
    <input type="button" value="<gsmsg:write key="cmn.reload" />" id="btn_reload" class="btn_reload_n1">
    <input type="button" name="btn_account" class="btn_base1" value="<gsmsg:write key="wml.100" />" onClick="return buttonPush('accountConf');">
    <logic:equal name="sml010Form" property="adminFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">
      <input type="button" name="btn_prjadd" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="return buttonPush('admConf');">
    </logic:equal>
      <input type="button" name="btn_prjadd" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="return buttonPush('kojinEdit');">
<% } %>
    </td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>
  </table>
</td>
</tr>
</table>


<div>
  <logic:messagesPresent message="false">
    <html:errors/><br>
  </logic:messagesPresent>
</div>
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center" class="wrap_table">

<table class="clear_table" width="100%">
  <tr>

    <%-- 左メニュー  --%>
    <td class="left_menu_area">

      <div id="sml_account_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_sel"><gsmsg:write key="wml.102" /></span>
      </div>

      <div id="sml_account_child_area" class="left_menu_content_area menu_head_account_area">

        <input type="hidden" id="smlViewAccountSv" value="<bean:write name="sml010Form" property="smlViewAccount" />" />

        <div style="padding-top:3px;">
          <html:select property="smlViewAccount" styleId="account_comb_box" styleClass="text_base account_select">
            <logic:notEmpty name="sml010Form" property="sml010AccountList">
              <logic:iterate id="accountMdl" name="sml010Form" property="sml010AccountList">
                <bean:define id="accoutVal" name="accountMdl" property="accountSid" type="java.lang.Integer" />
                <html:option value="<%= String.valueOf(accoutVal) %>"><bean:write name="accountMdl" property="accountName" /></html:option>
              </logic:iterate>
            </logic:notEmpty>
          </html:select>
        </div>

        <div class="account_sel_bottom">
          <logic:notEmpty name="sml010Form" property="sml010AccountList">
            <bean:define id="viewAccoutVal" name="sml010Form" property="smlViewAccount" type="java.lang.Integer" />
            <logic:iterate id="accountMdl" name="sml010Form" property="sml010AccountList">
              <logic:notEqual name="accountMdl" property="accountSid" value="<%= String.valueOf(viewAccoutVal) %>">
                <div class="account_sel_txt" id="<bean:write name="accountMdl" property="accountSid" />"><bean:write name="accountMdl" property="accountName" /></div>
              </logic:notEqual>
            </logic:iterate>
          </logic:notEmpty>
        </div>

      </div>

      <div id="sml_mailbox_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_sel">メールボックス</span>
      </div>

      <div id="sml_mailbox_child_area" class="left_menu_child_content_area">

        <div class="child_spacer"></div>


        <div class="content_div">
          <div class="mail_left_line_top folder_clear_float"></div>
          <div class="mail_jushin folder_float"></div>
          <div id="menu_jushin_txt" class="mail_box_txt folder_float" onclick="javascript:changeModeDir(<%= String.valueOf(jusin) %>);">受信</div><div id="midoku_txt" class="midoku_txt"></div>
        </div>

        <div class="content_div">
          <div class="mail_left_line folder_clear_float"></div>
          <div class="mail_soshin" style="float:left;"></div>
          <div class="mail_box_txt" style="float:left;" onclick="javascript:changeModeDir(<%= String.valueOf(sosin) %>);">送信</div>
        </div>

        <div class="content_div">
          <div class="mail_left_line folder_clear_float"></div>
          <div class="mail_folder folder_float"></div>
          <div class="mail_box_txt folder_float" onclick="javascript:changeModeDir(<%= String.valueOf(soko) %>);">草稿</div><div id="soko_txt" class="midoku_txt"></div>
        </div>

        <% String leftLineClass = "mail_left_line_bottom";%>

        <logic:notEmpty name="sml010Form" property="sml010LabelList">
          <% leftLineClass =  "mail_left_line";%>
        </logic:notEmpty>

        <div class="content_div">
          <div id="gomibako_bottom_div" class="<%= leftLineClass %> folder_clear_float"></div>
          <div class="mail_dust folder_float"></div>
          <div class="mail_box_txt folder_float" onclick="javascript:changeModeDir(<%= String.valueOf(gomi) %>);">ゴミ箱</div><div id="gomi_txt" class="midoku_txt"></div><div id="kara_area"></div>
        </div>

        <%-- ラベル  --%>
        <div id="labelArea" class="clear_div"></div>

        <div class="child_spacer" style="clear:both;" ></div>

        <div class="disk_use"><gsmsg:write key="wml.88" />:<wbr><span id="disk_use"><bean:write name="sml010Form" property="sml010AccountDisk" /></span>MB</div>

        <div class='contextMenu' id='context_menu1'>
          <ul>
            <li id='all_read'><gsmsg:write key="cmn.all" /><gsmsg:write key="cmn.mark.read" /></li>
            <li id='all_no_read'><gsmsg:write key="cmn.all" /><gsmsg:write key="wml.js.10" /></li>
          </ul>
        </div>

       <div class='contextMenu' id='context_menu2'>
          <ul>
            <li id='mail_read'><gsmsg:write key="cmn.mark.read" /></li>
          </ul>
        </div>

        <div class='contextMenu' id='context_menu3'>
          <ul>
            <li id='mail_no_read'><gsmsg:write key="wml.js.10" /></li>
          </ul>
        </div>

      </div>

      <div id="sml_shain_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_sel"><gsmsg:write key="cmn.shain.info" /></span>
      </div>

      <div id="sml_shain_child_area" class="left_menu_child_content_area">

       <div class="shain_top_back">
         <html:select name="sml010Form" property="sml010groupSid" styleId="sml010ChangeGrp" styleClass="text_base" style="width:160px;">
            <logic:notEmpty name="sml010Form" property="sml010groupList">
              <logic:iterate id="gpBean" name="sml010Form" property="sml010groupList" scope="request">
              <bean:define id="gpValue" name="gpBean" property="value" type="java.lang.String" />
                  <logic:equal name="gpBean" property="styleClass" value="0">
                    <html:option value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:equal>
                  <logic:equal name="gpBean" property="styleClass" value="1">
                    <html:option styleClass="select03" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:equal>
                  <logic:equal name="gpBean" property="styleClass" value="2">
                    <html:option styleClass="select06" value="<%= gpValue %>"><bean:write name="gpBean" property="label" /></html:option>
                  </logic:equal>
              </logic:iterate>
            </logic:notEmpty>
          </html:select>
          <input type="button" onclick="openGroupWindow(this.form.sml010groupSid, 'sml010groupSid', '1', 'changeGrp', '1', 'fakeGrpButton', 'sml010disableGroupSid')" class="group_btn2" value="&nbsp;&nbsp;" id="sml010GroupBtn">
          <input type="button" class="display_none" id="fakeGrpButton" name="fakeGrpButton" />
          <div class="add_atesaki_pad">

            <div class="mail_btn mail_atesaki_add_btn" id="0"><gsmsg:write key="cmn.from" /></div>
            <div class="mail_btn_space"></div>
            <div class="mail_btn mail_atesaki_add_btn" id="1">CC</div>
            <div class="mail_btn_space"></div>
            <div class="mail_btn mail_atesaki_add_btn" id="2">BCC</div>

          </div>
          <div class="syain_checkbox_area"><input class="syain_checkbox" type="checkbox" name="usrAllCheck" onClick="return changeChkAdd();" id="all" /> <label for="all"><span class="syain_sel_txt"><gsmsg:write key="sml.66" /></span></label></div>
        </div>

        <div id="selGrpUsrArea" style="padding-left:1px;">
          <logic:notEmpty name="sml010Form" property="sml010userList" >
          <logic:iterate id="usrList" name="sml010Form" property="sml010userList" >
            <div class="syain_checkbox_area">


            <logic:notEmpty name="usrList" property="sacSid">
              <input class="syain_checkbox" type="checkbox" name="sml010usrSids" value="sac<bean:write name="usrList" property="sacSid" />" />
              <a class="syain_sel_check_txt syain_sel_txt" id="sac<bean:write name="usrList" property="sacSid" />"><bean:write name="usrList" property="sacName" /></a>
            </logic:notEmpty>

            <logic:empty name="usrList" property="sacSid">
              <input class="syain_checkbox" type="checkbox" name="sml010usrSids" value="<bean:write name="usrList" property="usrSid" />" />
              <a class="syain_sel_check_txt syain_sel_txt" id="<bean:write name="usrList" property="usrSid" />"><bean:write name="usrList" property="usiSei" />&nbsp;<bean:write name="usrList" property="usiMei" /></a>
            </logic:empty>

            </div>
          </logic:iterate>
          </logic:notEmpty>
        </div>

      </div>


      <logic:notEmpty name="sml010Form" property="sml010HinaList">

      <div id="sml_hina_kyotu_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_not_sel"><gsmsg:write key="sml.sml020.03" /></span>
      </div>

      <div id="sml_hina_kyotu_child_area" class="left_menu_child_content_area display_none">
        <logic:notEmpty name="sml010Form" property="sml010HinaList">

        <div class="hina_txt_div">

        <table class="clear_table">
        <bean:define id="mod" value="0" />
        <logic:iterate id="hinaModel" name="sml010Form" property="sml010HinaList" indexId="hinaidx">
          <tr>
          <td width="0%">
            <%-- マーク  --%>
            <bean:define id="imgMark"><bean:write name="hinaModel" property="shnMark" /></bean:define>
            <% java.lang.String key = "none";  if (imgMap2.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap2.get(key) %>
          </td>
          <td width="100%">


            <logic:notEmpty name="hinaModel" property="shnBody">
            <bean:define id="hinabody" name="hinaModel" property="shnBody" />
            </logic:notEmpty>

            <p>
            <a class="hina_sel_txt" href="#" id="<bean:write name="hinaModel" property="shnSid" />">
              <bean:write name="hinaModel" property="shnHnameDsp" />
              <span class="tooltips display_none"><gsmsg:write key="cmn.subject" />：<bean:write name="hinaModel" property="shnTitle" />
                <gsmsg:write key="cmn.content" />：<bean:write name="hinaModel" property="shnBody" filter="false"/>
              </span>
            </a>
            </p>

          </td>
          </tr>

        </logic:iterate>
        </table>
        </div>
        </logic:notEmpty>
      </div>

      </logic:notEmpty>



      <div id="sml_hina_kojin_area" class="left_menu_content_area menu_head_area">
        <span class="menu_head_txt menu_head_not_sel">
           <gsmsg:write key="sml.sml020.04" />
           <span class="hinagata_add_wrap"><span id="hinagata_add" onClick="buttonPush('hina_edit');"><gsmsg:write key="cmn.add" /></span></span>
        </span>
      </div>

      <logic:notEmpty name="sml010Form" property="sml010HinaListKjn">

      <div id="sml_hina_kojin_child_area" class="left_menu_child_content_area left_menu_botton display_none">
        <div class="hina_txt_div">
        <logic:notEmpty name="sml010Form" property="sml010HinaListKjn">
        <table class="clear_table">
        <bean:define id="mod" value="0" />
        <logic:iterate id="hinaModelKjn" name="sml010Form" property="sml010HinaListKjn" indexId="hinaidxkjn">
          <tr>
          <td width="0%">

            <%-- マーク  --%>
            <bean:define id="imgMark"><bean:write name="hinaModelKjn" property="shnMark" /></bean:define>
            <% java.lang.String key = "none";  if (imgMap2.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap2.get(key) %>
          </td>
          <td width="100%">

            <logic:notEmpty name="hinaModelKjn" property="shnBody">
            <bean:define id="hinabody" name="hinaModelKjn" property="shnBody" />
            </logic:notEmpty>
            <p>
            <a class="hina_sel_txt" id="<bean:write name="hinaModelKjn" property="shnSid" />"  href="#">
              <bean:write name="hinaModelKjn" property="shnHnameDsp" />
              <span class="tooltips display_none"><gsmsg:write key="cmn.subject" />：<bean:write name="hinaModelKjn" property="shnTitle" />
                <gsmsg:write key="cmn.content" />：<bean:write name="hinaModelKjn" property="shnBody" filter="false"/>
              </span>
            </a>
            </p>

          </td>
          </tr>

        </logic:iterate>
        </table>
        </logic:notEmpty>

        </div>






      </div>

      </logic:notEmpty>

      <logic:empty name="sml010Form" property="sml010HinaListKjn">
        <div class="left_menu_content_area menu_head_area_none"></div>
      </logic:empty>

    </td>

    <%-- 右コンテンツ  --%>
    <td class="content_area">

        <%-- 検索  --%>

        <%-- 検索SVパラメータ start -------------------------------------------------------------------------- --%>
        <html:hidden property="sml090SvSltGroup" />
        <html:hidden property="sml090SvSltUser" />


        <div id="svAtesakiArea"></div>

        <html:hidden property="sml090SvMailSyubetsu" />
        <html:hidden property="sml090SvMailMark" />
        <html:hidden property="sml090SvKeyWord" />
        <html:hidden property="sml090SvKeyWordkbn" />

        <div id="svTargetArea"></div>

        <html:hidden property="sml090SvSearchOrderKey1" />
        <html:hidden property="sml090SvSearchSortKey1" />
        <html:hidden property="sml090SvSearchOrderKey2" />
        <html:hidden property="sml090SvSearchSortKey2" />


        <input type="hidden" name="sml090page1" value="1" />
        <input type="hidden" name="sml090page2" value="1" />


        <%-- 検索SVパラメータ end ---------------------------------------------------------------------------- --%>

        <table class="clear_table display_none" id="search_area_table" width="100%" cellpadding="0" cellspacing="0">
        <%-- table class="clear_table" id="search_area_table" width="100%" cellpadding="0" cellspacing="0"  --%>
        <tr>

          <td>

            <div style="border:1px solid #bbbbbb;">

            <table width="100%" class="search_table">


            <tr>
            <td colspan="4" class="border_left_none border_right_none search_table_top">
              <div class="search_area_del_btn"></div>
            </td>
            </tr>

            <tr>
            <th width="10%" class="search_table_border_right border_left_none" nowrap>メール種別</th>
            <td width="40%" class="border_left_none">
              <input type="radio" name="sml090MailSyubetsu" value="0" checked="checked" id="radio_jushin"><label for="radio_jushin">受信</label>
              <input type="radio" name="sml090MailSyubetsu" value="1" id="radio_soushin"><label for="radio_soushin">送信</label>
              <input type="radio" name="sml090MailSyubetsu" value="2" id="radio_soukou"><label for="radio_soukou">草稿</label>
              <input type="radio" name="sml090MailSyubetsu" value="4" id="radio_gomi"><label for="radio_gomi">ゴミ箱</label>
            </td>

            <th width="10%" class="search_table_border_right search_toggle_area" nowrap>送信者</th>
            <td width="40%" class="border_left_none border_right_none search_toggle_area">
              <table>
                <tr>
                <td style="border:0px;" class="" nowrap><gsmsg:write key="cmn.group" /><gsmsg:write key="wml.215" /></td>
                <td style="border:0px;" class="border_right_none" nowrap>
                  <html:select property="sml090SltGroup" styleId="sml090SltGroup" styleClass="select01">

                    <logic:notEmpty name="sml010Form" property="sml090GroupLabel">
                      <logic:iterate id="labelMdl" name="sml010Form" property="sml090GroupLabel">
                        <bean:define id="labelVal" name="labelMdl" property="value" type="java.lang.String" />

                        <logic:notEqual name="labelMdl" property="value" value="sac">
                          <html:option value="<%= labelVal %>"><bean:write name="labelMdl" property="label" /></html:option>
                        </logic:notEqual>

                        <logic:equal name="labelMdl" property="value" value="sac">
                          <html:option styleClass="select06" value="<%= labelVal %>"><bean:write name="labelMdl" property="label" /></html:option>
                        </logic:equal>

                      </logic:iterate>
                    </logic:notEmpty>

                  </html:select>
                  <span class="search_grp_sel_btn">
                    <input type="button" onclick="openGroupWindow(this.form.sml090SltGroup, 'sml090SltGroup', '0', 'changeGrp', '1', 'fakeSearchGrpButton', 'sml010disableGroupSid')" class="group_btn2" value="&nbsp;&nbsp;" id="sml090GroupBtn">
                    <input type="button" class="display_none" id="fakeSearchGrpButton" name="fakeSearchGrpButton" />
                  </span>
                  <span class="search_grp_sel_btn_hide display_none">
                    <input type="button" class="group_btn_hide" value="&nbsp;&nbsp;">
                  </span>
                </td>
                </tr>
                <tr>
                <td  style="border:0px;" class="" nowrap><gsmsg:write key="cmn.user" /><gsmsg:write key="wml.215" /></td>
                <td style="border:0px;">
                  <html:select property="sml090SltUser" styleId="sml090SltUser" styleClass="select01">
                    <html:optionsCollection name="sml010Form" property="sml090UserLabel" value="value" label="label" />
                  </html:select>
                </td>
                </tr>
              </table>
            </td>
            </tr>

            <tr class="search_toggle_area">
            <th width="10%" class="search_table_border_right border_left_none" nowrap>マーク</th>
            <td width="40%" class="border_left_none">
              <input type="radio" name="sml090MailMark" value="-1" checked="checked" id="radio_all"><label for="radio_all">指定無し</label>
              <input type="radio" name="sml090MailMark" value="0" id="radio_none"><label for="radio_none">無し</label>
              <input type="radio" name="sml090MailMark" value="4" id="radio_tell"><label for="radio_tell"><img src="../smail/images/call.gif" alt="電話" border="0" class="img_bottom">電話</label>
              <input type="radio" name="sml090MailMark" value="8" id="radio_important"><label for="radio_important"><img src="../smail/images/zyuu.gif" alt="重要" border="0" class="img_bottom">重要</label><br>
              <input type="radio" name="sml090MailMark" value="101" id="radio_Smaily"><label for="radio_Smaily"><img src="../smail/images/icon_face01.gif" alt="スマイル" border="0" class="img_bottom">スマイル</label>
              <input type="radio" name="sml090MailMark" value="102" id="radio_Worry"><label for="radio_Worry"><img src="../smail/images/icon_face02.gif" alt="悩み" border="0" class="img_bottom">悩み</label>
              <input type="radio" name="sml090MailMark" value="103" id="radio_Angry"><label for="radio_Angry"><img src="../smail/images/icon_face03.gif" alt="怒り" border="0" class="img_bottom">怒り</label>
              <input type="radio" name="sml090MailMark" value="104" id="radio_Sadly"><label for="radio_Sadly"><img src="../smail/images/icon_face04.gif" alt="悲しみ" border="0" class="img_bottom">悲しみ</label><br>
              <input type="radio" name="sml090MailMark" value="201" id="radio_Beer"><label for="radio_Beer"><img src="../smail/images/icon_beer.gif" alt="ビール" border="0" class="img_bottom">ビール</label>
              <input type="radio" name="sml090MailMark" value="202" id="radio_Hart"><label for="radio_Hart"><img src="../smail/images/icon_hart.gif" alt="ハート" border="0" class="img_bottom">ハート</label>
              <input type="radio" name="sml090MailMark" value="203" id="radio_markZasetsu"><label for="radio_markZasetsu"><img src="../smail/images/icon_zasetsu.gif" alt="疲れ" border="0" class="img_bottom">疲れ</label><br>
            </td>

            <th width="10%" class="search_table_border_right" nowrap>宛先</th>
            <td width="40%" class="border_left_none border_right_none" valign="top">
              <input type="button" class="btn_base0 mail_send_sel_btn" value="選択" id="btnSearchAtesakiSelect">&nbsp;&nbsp;
              <input type="hidden" id="btnSearchAtesakiSelectVal" value="<gsmsg:write key="cmn.from" />" />
              <input type="hidden" id="btnSearchAtesakiSelectKbn" value="3" />
              <input type="button" class="btn_base0" value="クリア" id="btnSearchAtesakiClear">
              <div class="atesaki_search_area" id="atesaki_search_area">

              </div>

            </td>
            </tr>

            <tr>
            <th width="10%" class="search_table_border_right border_left_none" nowrap>キーワード</th>
            <td class="border_left_none">
              <input type="text" name="sml090KeyWord" maxlength="100" style="width:265px;" value="">
              <div class="text_base2">
                <input type="radio" name="sml090KeyWordkbn" value="0" checked="checked" id="keyKbn_01"><label for="keyKbn_01">全てを含む(AND)</label>&nbsp;
                <input type="radio" name="sml090KeyWordkbn" value="1" id="keyKbn_02"><label for="keyKbn_02">いずれかを含む(OR)</label>
              </div>
            </td>


            <th width="10%" class="search_table_border_right search_toggle_area" nowrap>検索対象</th>
            <td width="40%" class="border_left_none border_right_none search_toggle_area">
              <input type="checkbox" name="sml090SearchTarget" value="1" checked="checked" id="search_scope_01"><label for="search_scope_01">件名</label>
              <input type="checkbox" name="sml090SearchTarget" value="2" checked="checked" id="search_scope_02"><label for="search_scope_02">本文</label>
            </td>

            </tr>


            <tr class="search_toggle_area">
            <th width="10%" class="search_table_border_right border_left_none" nowrap>ソート順</th>
            <td class="border_left_none border_right_none" colspan="3">
            <span class="text_bb2">第1キー</span>
              <html:select property="sml090SearchSortKey1">
                <html:optionsCollection name="sml010Form" property="sml090SortKeyLabelList" value="value" label="label" />
              </html:select>

              <input type="radio" name="sml090SearchOrderKey1" value="0" id="sort1_up"><label for="sort1_up">昇順</label>
              <input type="radio" name="sml090SearchOrderKey1" value="1" checked="checked" id="sort1_dw"><label for="sort1_dw">降順</label>
              &nbsp;&nbsp;&nbsp;&nbsp;

            <span class="text_bb2">第2キー</span>
              <html:select property="sml090SearchSortKey2">
                <html:optionsCollection name="sml010Form" property="sml090SortKeyLabelList" value="value" label="label" />
              </html:select>
              <input type="radio" name="sml090SearchOrderKey2" value="0" checked="checked" id="sort2_up"><label for="sort2_up">昇順</label>
              <input type="radio" name="sml090SearchOrderKey2" value="1" id="sort2_dw"><label for="sort2_dw">降順</label>

            </td>
            </tr>

            <tr>
            <td class="border_left_none border_right_none border_bottom_none" align="center" colspan="4">
               <table>
               <tr>
               <td align="center" class="border_top_none border_left_none border_right_none border_bottom_none">
<!--               <div style="padding-left:45%" class="mail_menu_btn" id="head_menu_search_btn2"> -->
               <div class="mail_menu_btn" id="head_menu_search_btn2">
                   <div class="head_menu_btn_left"></div>
                   <div class="head_menu_btn_bg"><div class="head_menu_search_btn2"><gsmsg:write key="cmn.search" /></div></div>
                   <div class="head_menu_btn_right"></div>
                 </div>
               </td>
               </tr>
               </table>
            </td>
            </tr>

            </table>

            </div>

            <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>

          </td>

        </tr>
        </table>


        <table class="clear_table" width="100%" cellpadding="0" cellspacing="0">
        <tr>
<%--
          <td class="mail_area_head_space" style="width:5px;"></td>
--%>
          <td id="mail_list_tab" class="mail_area_head">

              <logic:equal name="sml010Form" property="sml010ProcMode" value="<%= jusin %>">
               <gsmsg:write key="cmn.receive3" />
              </logic:equal>

              <logic:equal name="sml010Form" property="sml010ProcMode" value="<%= sosin %>">
                <gsmsg:write key="cmn.sent3" />
              </logic:equal>

              <logic:equal name="sml010Form" property="sml010ProcMode" value="<%= soko %>">
                <gsmsg:write key="cmn.draft2" />
              </logic:equal>

              <logic:equal name="sml010Form" property="sml010ProcMode" value="<%= gomi %>">
                <gsmsg:write key="cmn.trash" />
              </logic:equal>
          </td>


          <td class="mail_area_head_space2" style="padding-bottom:1px;">

            <div id="sml_search_btn" class="head_menu_search_btn sml_search_btn sml_search_box_area"><gsmsg:write key="cmn.search" /></div>

            <div id="sml_search_text" class="sml_search_box_area" style="padding-left:2px;padding-right:2px;float:right;"><input id="search_text_val" class="sml_search_text" type="text" value="" maxlength="100" style="width:123px;font-size:80%!important;" /></div>
            <span style="clear:borh;"></span>
          </td>

        </tr>
        </table>

        <table id="mail_list_head_menu_area" class="clear_table mail_list_area" width="100%" cellpadding="0" cellspacing="0">

        <tr>
          <td id="mail_list_head_menu_area_tr" class="mail_list_head_menu" colspan="6">


            <logic:equal name="sml010Form" property="sml010ProcMode" value="<%= gomi %>">
              <div class="head_menu_spacer head_menu_main gomibako_area"></div>
              <div class="mail_menu_btn gomibako_area" id="head_menu_empty_trash_btn">
              <div class="head_menu_btn_left"></div>
              <div class="head_menu_btn_bg"><div class="head_menu_empty_trash_btn"><gsmsg:write key="cmn.empty.trash" /></div></div>
              <div class="head_menu_btn_right"></div>
              </div>
            </logic:equal>

            <div class="head_menu_spacer head_menu_main"></div>
            <span id="takekawa"></span>

<%--
            <div class="mail_menu_btn head_menu_add_btn head_menu_main" onClick="buttonPush('msg_create');"><gsmsg:write key="cmn.create.new" /></div>
--%>

            <div class="mail_menu_btn">
              <div class="head_menu_btn_left"></div>
              <div class="head_menu_btn_bg"><div class="head_menu_add_btn"><gsmsg:write key="cmn.create.new" /></div></div>
              <div class="head_menu_btn_right"></div>
            </div>

<%--
            <div class="head_menu_spacer head_menu_main"></div>

            <div class="mail_menu_btn" onClick="buttonPush('hina_edit');">
              <div class="head_menu_btn_left"></div>
              <div class="head_menu_btn_bg"><div class="head_menu_hina_btn"><gsmsg:write key="sml.sml010.03" /></div></div>
              <div class="head_menu_btn_right"></div>
            </div>
--%>
            <div class="head_menu_spacer head_menu_main"></div>

            <div class="mail_menu_btn" id="head_menu_list_pdf_btn">
              <div class="head_menu_btn_left"></div>
              <div class="head_menu_btn_bg"><div class="head_menu_pdf_btn"><gsmsg:write key="cmn.pdf" /></div></div>
              <div class="head_menu_btn_right"></div>
            </div>

            <div class="mail_menu_btn" id="head_menu_list_eml_btn">
              <div class="head_menu_btn_bg"><div class="head_menu_eml_btn">eml出力</div></div>
              <div class="head_menu_btn_right"></div>
            </div>

           <logic:notEqual name="sml010Form" property="sml010ProcMode" value="<%= gomi %>">

           <div class="head_menu_spacer head_menu_main"></div>

           <div class="mail_menu_btn" id="head_menu_list_label_add_btn">
              <div class="head_menu_btn_left"></div>
              <div class="head_menu_btn_bg"><div class="head_menu_label_add_btn"><gsmsg:write key="cmn.add.label2" /></div></div>
              <div class="head_menu_btn_right"></div>
            </div>

            <div class="mail_menu_btn" id="head_menu_list_label_del_btn">
              <div class="head_menu_btn_bg"><div class="head_menu_label_del_btn"><gsmsg:write key="wml.js.108" /></div></div>
              <div class="head_menu_btn_right"></div>
            </div>

            </logic:notEqual>

            <logic:equal name="sml010Form" property="sml010ProcMode" value="<%= gomi %>">
              <div class="head_menu_spacer head_menu_main gomibako_area"></div>
              <div class="mail_menu_btn gomibako_area" id="head_menu_return_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_return_btn"><gsmsg:write key="cmn.undo" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

            </logic:equal>


            <logic:notEqual name="sml010Form" property="sml010ProcMode" value="<%= gomi %>">
              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_list_kidoku_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_kidoku_btn"><gsmsg:write key="cmn.read.already" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="mail_menu_btn" id="head_menu_list_midoku_btn">
                <div class="head_menu_btn_bg"><div class="head_menu_midoku_btn"><gsmsg:write key="cmn.read.yet" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>
            </logic:notEqual>


           <div class="head_menu_spacer head_menu_main"></div>

           <div class="mail_menu_btn" id="head_menu_list_del_btn">
              <div class="head_menu_btn_left"></div>
              <div class="head_menu_btn_bg"><div class="head_menu_del_btn"><gsmsg:write key="cmn.delete" /></div></div>
              <div class="head_menu_btn_right"></div>
            </div>

            <div class="head_menu_spacer"></div>

            <div id="sml_page_top_area" align="right" style="float:right;">
            </div>

          </td>
        </tr>

        </table>


        <!-- リストBODY  -->
        <table width="100%" id="mail_list_draw_table" class="mail_list_area" cellpadding="0" cellspacing="0" style="margin-top: 1px;" ></table>

<%--
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="0%"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>

        <td width="50%" align="right" valign="bottom">
          <bean:define id ="autoDelKbn" name="sml010Form" property="sml010autoDelKbn" type="java.lang.Integer" />
          <% if (autoDelKbn.intValue() == jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) { %>
            <bean:define id="autoDelYear" name="sml010Form" property="sml010autoDelYear" type="java.lang.Integer" />
            <bean:define id="autoDelMonth" name="sml010Form" property="sml010autoDelMonth" type="java.lang.Integer" />

            <% String dateString = ""; %>
            <%
               jp.groupsession.v2.struts.msg.GsMessage gsMsg2 = new jp.groupsession.v2.struts.msg.GsMessage();
               String year = gsMsg2.getMessage(request, "cmn.year", String.valueOf(autoDelYear));
               String months = gsMsg2.getMessage(request, "cmn.months", String.valueOf(autoDelMonth));
                                                                          %>
            <% if (autoDelYear.intValue() > 0) { dateString += year; } %>
            <% if (autoDelMonth.intValue() > 0) { dateString += months; } %>

            <% if (dateString.length() > 0) { %><span class="text_base7"><gsmsg:write key="cmn.comments" /><%= dateString %><gsmsg:write key="sml.sml010.05" /></span><% } %>
          <% } %>
        </td>

        </tr>

        </table>

--%>

        <!-- 検索結果リストHEAD  -->
        <table id="mail_search_list_head_menu_area" class="clear_table mail_search_list_area display_none" width="100%" cellpadding="0" cellspacing="0">

          <tr>
            <td id="mail_search_list_head_menu_area_tr" class="mail_list_head_menu" colspan="6">

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_add_btn"><gsmsg:write key="cmn.create.new" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

<%--
              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" onClick="buttonPush('hina_edit');">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_hina_btn"><gsmsg:write key="sml.sml010.03" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>
--%>
              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_search_list_pdf_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_pdf_btn"><gsmsg:write key="cmn.pdf" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="mail_menu_btn" id="head_menu_search_list_eml_btn">
                <div class="head_menu_btn_bg"><div class="head_menu_eml_btn">eml出力</div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_search_list_label_add_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_label_add_btn"><gsmsg:write key="cmn.add.label2" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="mail_menu_btn" id="head_menu_search_list_label_del_btn">
                <div class="head_menu_btn_bg"><div class="head_menu_label_del_btn"><gsmsg:write key="wml.js.108" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_search_list_kidoku_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_kidoku_btn"><gsmsg:write key="cmn.read.already" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="mail_menu_btn" id="head_menu_search_list_midoku_btn">
                <div class="head_menu_btn_bg"><div class="head_menu_midoku_btn"><gsmsg:write key="cmn.read.yet" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_search_list_del_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_del_btn"><gsmsg:write key="cmn.delete" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer"></div>

              <div id="sml_search_page_top_area" align="right" style="float:right;">
              </div>

            </td>
          </tr>

        </table>
        <!-- 検索結果リストBODY  -->
        <table width="100%" id="mail_search_list_draw_table" class="mail_search_list_area" cellpadding="0" cellspacing="0" style="margin-top: 1px;" ></table>

        <%-- 新規作成 --%>
        <table class="clear_table mail_create_area display_none" width="100%" cellpadding="0" cellspacing="0">

          <tr>
            <td class="mail_list_head_menu" style="padding-left:15px;">

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_send_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_send_btn">O K</div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_soko_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_soko_btn"><gsmsg:write key="cmn.save.draft" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn display_none" id="head_menu_del_soko_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_del_btn"><gsmsg:write key="cmn.delete" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div id="head_menu_del_soko_btn_spacer" class="head_menu_spacer head_menu_main display_none"></div>

              <div class="mail_menu_btn" id="head_menu_hinagata_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_hinagata_btn"><gsmsg:write key="sml.sml010.03" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>


              <div class="head_menu_spacer head_menu_main"></div>

            </td>
          </tr>

          <%-- 宛先 --%>
          <tr style="clear:both;">

          <td class="mail_create_sel_send">

            <table class="clear_table">
              <tr>
                <td class="mail_create_sel_left">

                  <div class="mail_create_sel_send_title"><gsmsg:write key="cmn.from" /><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></div>
                  <div class="head_menu_spacer2 head_menu_main"></div>
                  <div class="mail_menu_btn mail_send_sel_btn" id="atesakiSelBtn">
                    <input type="hidden" id="atesakiSelBtnVal" value="<gsmsg:write key="cmn.from" />" />
                    <input type="hidden" id="atesakiSelBtnKbn" value="0" />
                    <div class="head_menu_btn_left"></div>
                    <div class="head_menu_btn_bg"><div class="address_add_btn"></div></div>
                    <div class="head_menu_btn_right"></div>
                  </div>

                </td>

                <td>
                  <div id="atesaki_area" class="atesaki_scroll_area">
                    <div id="atesaki_to_area" class="atesaki_add_area"></div>
                  </div>
                  <div id="alldsp_to_area" class="atesaki_alldsp_area">
                    <span id="all_dsp_to_link" class="all_disp_txt"></span>
                  </div>
                </td>

              </tr>
            </table>

          </td>

          </tr>

          <%-- CC --%>
          <tr style="clear:both;">

          <td class="mail_create_sel_send">

            <table class="clear_table">
              <tr>
                <td class="mail_create_sel_left">

                  <div class="mail_create_sel_send_title" style="padding-right:18px;">CC</div>
                  <div class="head_menu_spacer2 head_menu_main"></div>
                  <div class="mail_menu_btn mail_send_sel_btn" id="ccSelBtn">
                    <input type="hidden" id="ccSelBtnVal" value="CC" />
                    <input type="hidden" id="ccSelBtnKbn" value="1" />
                    <div class="head_menu_btn_left"></div>
                    <div class="head_menu_btn_bg"><div class="address_add_btn"></div></div>
                    <div class="head_menu_btn_right"></div>
                  </div>

                </td>

                <td>
                   <div id="cc_area" class="atesaki_scroll_area atesaki_scroll_on">
                       <div id="atesaki_cc_area" class="atesaki_add_area"></div>
                   </div>
                   <div id="alldsp_cc_area" class="atesaki_alldsp_area">
                        <span id="all_dsp_cc_link" class="all_disp_txt"></span>
                   </div>
                </td>

              </tr>
            </table>

          </tr>

          <%-- BCC --%>
          <tr style="clear:both;">

          <td class="mail_create_sel_send">

            <table class="clear_table">
              <tr>
                <td class="mail_create_sel_left">

                  <div class="mail_create_sel_send_title" style="padding-right:10px;">BCC</div>
                  <div class="head_menu_spacer2 head_menu_main"></div>
                  <div class="mail_menu_btn mail_send_sel_btn" id="bccSelBtn">
                    <input type="hidden" id="bccSelBtnVal" value="BCC" />
                    <input type="hidden" id="bccSelBtnKbn" value="2" />
                    <div class="head_menu_btn_left"></div>
                    <div class="head_menu_btn_bg"><div class="address_add_btn"></div></div>
                    <div class="head_menu_btn_right"></div>
                  </div>

                </td>

                <td>
                   <div id="bcc_area" class="atesaki_scroll_area">
                       <div id="atesaki_bcc_area" class="atesaki_add_area"></div>
                   </div>
                   <div id="alldsp_bcc_area" class="atesaki_alldsp_area">
                        <span id="all_dsp_bcc_link" class="all_disp_txt"></span>
                   </div>
                </td>

              </tr>
            </table>

          </tr>

          <%-- 宛先 タイトル --%>
          <tr style="clear:both;">

          <td class="mail_create_sel_send">

            <div class="mail_create_sel_send_title"><gsmsg:write key="cmn.subject" /><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></div>
            <div class="head_menu_spacer2 head_menu_main"></div>
            <div class="clear_div" style="float:left;">
              <input type="text" name="sml020Title" maxlength="70" style="width:581px;" value="" class="text_base">
            </div>

          </td>

          </tr>

          <%-- マーク --%>
          <tr style="clear:both;">

          <td class="mail_create_sel_send">

            <div class="mail_create_bottom_content_mark" style="padding-left:10px;">
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="0" checked="checked" id="sml020Mark_0"><label for="sml020Mark_0">無し</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="4" id="sml020Mark_1">&nbsp;<label for="sml020Mark_1"><img src="../smail/images/call.gif" alt="電話" border="0" class="img_bottom">電話</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="8" id="sml020Mark_2">&nbsp;<label for="sml020Mark_2"><img src="../smail/images/zyuu.gif" alt="重要" border="0" class="img_bottom">重要</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="101" id="sml020Mark_3">&nbsp;<label for="sml020Mark_3"><img src="../smail/images/icon_face01.gif" alt="スマイル" border="0" class="img_bottom">スマイル</label>&nbsp;</nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="102" id="sml020Mark_4">&nbsp;<label for="sml020Mark_4"><img src="../smail/images/icon_face02.gif" alt="悩み" border="0" class="img_bottom">悩み</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="103" id="sml020Mark_5">&nbsp;<label for="sml020Mark_5"><img src="../smail/images/icon_face03.gif" alt="怒り" border="0" class="img_bottom">怒り</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="104" id="sml020Mark_6">&nbsp;<label for="sml020Mark_6"><img src="../smail/images/icon_face04.gif" alt="悲しみ" border="0" class="img_bottom">悲しみ</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="201" id="sml020Mark_7">&nbsp;<label for="sml020Mark_7"><img src="../smail/images/icon_beer.gif" alt="ビール" border="0" class="img_bottom">ビール</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="202" id="sml020Mark_8">&nbsp;<label for="sml020Mark_8"><img src="../smail/images/icon_hart.gif" alt="ハート" border="0" class="img_bottom">ハート</label></nobr></div>
                <div class="mark_class"><nobr><input type="radio" name="sml020Mark" value="203" id="sml020Mark_9">&nbsp;<label for="sml020Mark_9"><img src="../smail/images/icon_zasetsu.gif" alt="疲れ" border="0" class="img_bottom">疲れ</label></nobr></div>
            </div>

          </td>

          </tr>

          <%-- 添付ファイル --%>
          <tr style="clear:both;">

            <td class="mail_create_sel_send mail_create_sel_send_bottom">

              <table border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td style="padding-left:20px;padding-top:5px;padding-bottom:5px;" width="0%" align="left" valign="top" nowrap>
                  <select id="sml020selectFiles" name="sml020selectFiles" class="select01" multiple size="3" style="height:50px;"></select>
                </td>

                <td width="0%" align="left" valign="top" nowrap><img src="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>
                <td width="100%" align="left" valign="middle">
                  <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn">&nbsp;
                  <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellTmpBtn" onClick="smlButtonPush('delete');">
                </td>
              </tr>
              </table>

            </td>

          </tr>

          <%-- 添付ファイル ・マーク・テキスト形式 --%>
          <tr style="clear:both;">

          <td class="mail_create_bottom_sel" width="100%">


            <table class="clear_table" width="100%">
              <tr>

                <td class="mail_create_bottom_sel_space"></td>

                <td class="mail_create_bottom_sel_space2"></td>

                <td id="text_html" class="mail_create_bottom_sel_text_form"><gsmsg:write key="wml.109" />に変更</td>

                <td id="text_text" class="mail_create_bottom_sel_text_form display_none"><gsmsg:write key="wml.js.12" />に変更</td>

                <input type="hidden" name="sml020MailType" value="0" />

              </tr>
            </table>


          </td>

          </tr>

        </table>




        <%-- 新規作成BODY --%>
        <table width="100%" class="mail_create_area display_none" cellpadding="0" cellspacing="0" style="clear:both;">

          <tr>
            <td style="padding-left:20px;padding-right:20px;padding-top:5px;padding-bottom:5px;" class="mail_create_sel_send_textarea">
            <div class="mail_create_sel_send_title_body"><gsmsg:write key="cmn.body" /><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></div>
              <div id="html_input_area" class="html_text_input display_none">
                <textarea id="html_input" class="html_text_input"></textarea>
                <input type="hidden" name="sml020BodyHtml" value="" />
              </div>
              <div id="text_input_area"><textarea id="text_input" class="html_text_input sml_textarea" name="sml020Body" rows="20" wrap="soft" onkeyup="showLengthStr(value, 2000, 'inputlength');" id="inputstr"></textarea></div>

              <div id="text_count_area" align="right" style="padding-right:5px;">
              <span class="font_string_count">現在の文字数:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;2000&nbsp;文字</span>
              </div>

            </td>
          </tr>

        </table>


        <%-- 新規作成 確認 --%>
        <table class="clear_table mail_create_kakunin_area display_none" width="100%" cellpadding="0" cellspacing="0">

          <tr>
            <td class="mail_list_head_menu" style="padding-left:15px;">

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_send_kakunin_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_send_kakunin_btn"><gsmsg:write key="cmn.sent" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn" id="head_menu_back_kakunin_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_back_btn"><gsmsg:write key="cmn.back" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

            </td>
          </tr>

          <%-- 宛先 --%>
          <tr id="atesaki_to_kakunin_area" style="clear:both;"></tr>

          <%-- CC --%>
          <tr id="atesaki_cc_kakunin_area" style="clear:both;"></tr>

          <%-- BCC --%>
          <tr id="atesaki_bcc_kakunin_area" style="clear:both;"></tr>

          <%-- 宛先 タイトル --%>
          <tr id="mail_create_title_kakunin_area" style="clear:both;"></tr>

          <%-- マーク --%>
          <tr id="mail_create_mark_kakunin_area" style="clear:both;"></tr>

          <%-- 添付ファイル --%>
          <tr id="mail_create_tmp_kakunin_area" style="clear:both;"></tr>

        </table>




        <%-- 新規作成BODY 確認--%>
        <table width="100%" class="mail_create_kakunin_area display_none" cellpadding="0" cellspacing="0" style="clear:both;">

          <tr id="mail_create_body_kakunin_area">
          </tr>

        </table>




        <%-- メール確認 --%>
        <table class="clear_table mail_kakunin_area display_none" width="100%" cellpadding="0" cellspacing="0">

          <tr id="mail_kakunin_head_menu">
          <%--
            <td class="mail_list_head_menu">

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_copy_btn">複写して新規作成</div></div>
                <div class="head_menu_btn_right"></div>
              </div>


              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_replay_btn"><gsmsg:write key="cmn.reply" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_all_replay_btn"><gsmsg:write key="sml.67" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_forward_btn"><gsmsg:write key="cmn.forward" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer head_menu_main"></div>

              <div class="mail_menu_btn">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_del_btn"><gsmsg:write key="cmn.delete" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer mail_menu_btn_right"></div>

              <div id="head_menu_next" class="mail_menu_btn mail_menu_btn_right">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_next_btn"><gsmsg:write key="cmn.next" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

              <div class="head_menu_spacer mail_menu_btn_right"></div>

              <div id="head_menu_prev" class="mail_menu_btn mail_menu_btn_right">
                <div class="head_menu_btn_left"></div>
                <div class="head_menu_btn_bg"><div class="head_menu_prev_btn"><gsmsg:write key="cmn.previous" /></div></div>
                <div class="head_menu_btn_right"></div>
              </div>

            </td>
          --%>
          </tr>


          <%-- 宛先 --%>
          <tr id="mail_kakunin_body_tr" style="clear:both;"></tr>

          <tr>
            <td style="padding-top:10px;padding-right:20px;padding-bottom:10px;padding-left:20px;" class="mail_check_body mail_check_body_txt"></td>
          </tr>

          <tr id="mail_kakunin_open_tr"></tr>

          <tr>
            <td class="mail_check_body_bottom">
              <a class="mail_check_body_bottom_pdf" href="javascript:void(0);" onClick="buttonPush('pdf')"><span class="mail_check_body_bottom_txt"><gsmsg:write key="wml.237" /></span></a>
              <a class="mail_check_body_bottom_eml" href="javascript:void(0);" onClick="buttonPush('eml')"><span class="mail_check_body_bottom_txt">eml形式でエクスポート</span></a>
            </td>
          </tr>

        </table>

    </td>

  </tr>
</table>


</td>
</tr>
</table>

<div id="atesakiSelPop" title="ユーザ選択" style="display:none;">

  <table width="100%" height="100%">
    <tr>
      <td id="atesakiSelArea" width="100%"></td>
    </tr>
  </table>

</div>


<div id="sendMailPop" title="" style="display:none">
  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%">
        <b id="sendMailPopMsg" style="font-size:14px;"></b>
      </td>
    </tr>
  </table>
</div>

<div id="messagePop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%">
        <b id="messageArea" style="font-size:14px;"></b>
      </td>
    </tr>
  </table>

</div>

<div id="delKakuninPop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info">
      </td>
      <td width="85%">
        <b id="" style="font-size:14px;">作成中のメールを削除してもよろしいですか？</b>
      </td>
    </tr>
  </table>


</div>

<div id="delMailMsgPop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%" valign="middle">
        <div style="vertical-align:middle;overflow:auto;width:500px;height:200px;">
          <table><tr><td id="delMailMsgArea" style="vertical-align:middle;width:500px;height:200px;font-weight:bold;font-size:14px;"></td></tr></table>
        </div>
      </td>
    </tr>
  </table>

</div>

<div id="revivedMailPop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%">
        <b id="revivedMailMsgArea" style="font-size:14px;"></b>
      </td>
    </tr>
  </table>

</div>


<div id="hinaOverWritePop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%">
        <b style="font-size:14px;">作成中のメールを上書きしてもよろしいですか？</b>
      </td>
    </tr>
  </table>

</div>

<div id="contextAllReadPop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%">
        <b id="allReadMsgArea" style="font-size:14px;"></b>
      </td>
    </tr>
  </table>

</div>

<div id="contextAllNoReadPop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td width="15%">
        <span class="ui-icon ui-icon-info"></span>
      </td>
      <td width="85%">
        <b style="font-size:14px;">すべての受信メールを未読にしてもよろしいですか？</b>
      </td>
    </tr>
  </table>

</div>



<div id="labelAddPop" title="<gsmsg:write key="wml.wml010.16" />" style="display:none">

  <table id="labelAddContentArea" width="100%" height="100%"></table>

</div>

<div id="labelDelPop" title="<gsmsg:write key="wml.js.108" />" style="display:none">

  <table id="labelDelContentArea" width="100%" height="100%"></table>

</div>

<div id="hinagata_pop" title="" style="display:none">

  <div style="width:100%;height:320px;overflow:auto;">

  <table width="100%" height="100%">
    <tr>
      <td valign="top" align="center" width="100%">

        <table width="100%" class="hinagata_head_table">
          <tr>
            <td id="hina_kyotu_tab" nowrap class="hinagata_pop_head"><gsmsg:write key="cmn.common" /></td>
            <td width="5px" class="hinagata_pop_head_other"><img src="../common/images/spacer.gif" width="5px" height="1px"></td>
            <td id="hina_kojin_tab" nowrap class="hinagata_pop_head hinagata_pop_head_no_sel"><gsmsg:write key="cmn.individual" /></td>
            <td width="100%" class="hinagata_pop_head_other"></td>
          </tr>
        </table>

        <table id="popHinaKyotu" class="tl0 table_td_border">
        <logic:notEmpty name="sml010Form" property="sml010HinaList">
        <logic:iterate id="hinaMdl" name="sml010Form" property="sml010HinaList" indexId="hinaidx">
          <tr class="hina_sel_txt2 td_line_color hinagataSelTr" id="<bean:write name="hinaMdl" property="shnSid" />">
          <td width="0%" class="border_right_none border_top_none">
            <%-- マーク  --%>
            <bean:define id="imgMark"><bean:write name="hinaMdl" property="shnMark" /></bean:define>
            <% java.lang.String key = "none";  if (imgMap2.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap2.get(key) %>
          </td>
          <td width="100%" class="border_left_none border_top_none">


            <logic:notEmpty name="hinaMdl" property="shnBody">
            <bean:define id="hinabdy" name="hinaMdl" property="shnBody" />
            </logic:notEmpty>

            <p>
            <span class="hina_sel_txt3">
              <bean:write name="hinaMdl" property="shnHnameDsp" />
              <span class="tooltips display_none"><gsmsg:write key="cmn.subject" />：<bean:write name="hinaMdl" property="shnTitle" />
                <gsmsg:write key="cmn.content" />：<bean:write name="hinaMdl" property="shnBody" filter="false"/>
              </span>
            </span>
            </p>

          </td>
          </tr>

        </logic:iterate>
        </logic:notEmpty>
        </table>


        <table id="popHinaKojin" class="tl0 table_td_border display_none">
        <logic:notEmpty name="sml010Form" property="sml010HinaListKjn">
        <logic:iterate id="hinaMdlKjn" name="sml010Form" property="sml010HinaListKjn" indexId="hinaidxkjn">
          <tr class="hina_sel_txt2 td_line_color hinagataSelTr" id="<bean:write name="hinaMdlKjn" property="shnSid" />">
          <td width="0%" class="border_right_none border_top_none">

            <%-- マーク  --%>
            <bean:define id="imgMark"><bean:write name="hinaMdlKjn" property="shnMark" /></bean:define>
            <% java.lang.String key = "none";  if (imgMap2.containsKey(imgMark)) { key = imgMark; } %> <%= (java.lang.String) imgMap2.get(key) %>
          </td>
          <td width="100%" class="border_left_none border_top_none">

            <logic:notEmpty name="hinaMdlKjn" property="shnBody">
            <bean:define id="hinabody" name="hinaMdlKjn" property="shnBody" />
            </logic:notEmpty>
            <p>
            <span class="hina_sel_txt3">
              <bean:write name="hinaMdlKjn" property="shnHnameDsp" />
              <span class="tooltips display_none"><gsmsg:write key="cmn.subject" />：<bean:write name="hinaMdlKjn" property="shnTitle" />
                <gsmsg:write key="cmn.content" />：<bean:write name="hinaMdlKjn" property="shnBody" filter="false"/>
              </span>
            </span>
            </p>

          </td>
          </tr>

        </logic:iterate>
        </logic:notEmpty>
        </table>


      </td>
    </tr>
  </table>

  </div>

</div>

<div id="loading_pop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr>
      <td class="loading_area" valign="middle" width="110%">
        <img src="../smail/images/ajax-loader.gif" />
      </td>
    </tr>
  </table>

</div>

<span id="tooltip_area"></span>

<logic:notEqual name="sml010Form" property="sml020webmail" value="1">
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</logic:notEqual>

</html:form>

<form id="tempSendForm">
</form>

<iframe id="sml010Export" src="" height="0" style="visibility: hidden" />

</body>
</html:html>