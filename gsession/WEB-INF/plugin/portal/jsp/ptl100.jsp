<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int tCmdAdd = jp.groupsession.v2.man.GSConstPortal.CMD_MODE_ADD; %>
<% int tCmdEdit = jp.groupsession.v2.man.GSConstPortal.CMD_MODE_EDIT; %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../portal/js/tiny_mce/tiny_mce.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../portal/js/ptl100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl160.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="ptl.ptl100.3" /></title>
</head>

<!-- body -->
<body class="body_03" onload="showLengthId($('#inputstr')[0], 1000, 'inputlength');" onunload="closePortletImagePopup();">
<html:form action="/portal/ptl100">
<input type="hidden" name="CMD" value="init">
<input type="hidden" name="helpPrm" value="<bean:write name="ptl100Form" property="ptlCmdMode" /><bean:write name="ptl100Form" property="ptl100contentType" />">
<html:hidden property="ptl090category" />
<html:hidden property="ptl090svCategory" />
<html:hidden property="ptl090sortPortlet" />
<html:hidden property="ptlCmdMode" />
<html:hidden property="ptlPortletSid" />
<html:hidden property="ptl100init" />
<html:hidden property="ptl100contentPlusImage" />

<input type="hidden" name="pltPortletImageSid" value="0">

<logic:equal name="ptl100Form" property="ptl100contentType" value="0">
  <html:hidden property="ptl100contentUrl" />
  <html:hidden property="ptl100contentHtml" />
</logic:equal>
<logic:equal name="ptl100Form" property="ptl100contentType" value="2">
  <html:hidden property="ptl100contentUrl" />
  <html:hidden property="ptl100content" />
</logic:equal>
<logic:equal name="ptl100Form" property="ptl100contentType" value="1">
  <html:hidden property="ptl100contentHtml" />
  <html:hidden property="ptl100content" />
</logic:equal>

<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.ptl100.3" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%">&nbsp;</td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ptl100add')">

          <logic:equal name="ptl100Form" property="ptlCmdMode" value="<%= String.valueOf(tCmdEdit) %>">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
          </logic:equal>

          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ptl100back')">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>

  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/></td>
    </tr>
    </table>
  </logic:messagesPresent>


  </td>
  </tr>

  <tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="ptl.17" /></span><span class="text_r2">※</span></td>
        <td align="left" class="td_wt" width="750">
          <html:text property="ptl100name" maxlength="100" size="40" style="width:271px;" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span></td>
        <td align="left" class="td_wt">
        <html:select property="ptl100category">
          <html:optionsCollection property="ptl100CategoryList" value="value" label="label" />
        </html:select>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="ptl.16" /></span><span class="text_r2">※</span></td>
        <td align="left" class="td_wt">
          <html:radio property="ptl100border" value="0" styleId="borderKbn0" /><label for="borderKbn0" class="text_base"><gsmsg:write key="address.adr010.contact.5" /></label>
          &nbsp;<html:radio property="ptl100border" value="1" styleId="borderKbn1" /><label for="borderKbn1" class="text_base"><gsmsg:write key="cmn.no" /></label>
        </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span><span class="text_r2">※</span></td>
      <td align="left" class="td_wt" width="80%">
        <html:radio property="ptl100contentType" value="0" styleId="contentType0" onclick="return buttonPush('init');" /><label for="contentType0" class="text_base"><gsmsg:write key="ptl.10" /></label>
        &nbsp;<html:radio property="ptl100contentType" value="2" styleId="contentType2" onclick="return buttonPush('init');" /><label for="contentType2" class="text_base"><gsmsg:write key="ptl.11" /></label>
        &nbsp;<html:radio property="ptl100contentType" value="1" styleId="contentType1" onclick="return buttonPush('init');" /><label for="contentType1" class="text_base"><gsmsg:write key="ptl.12" /></label>
        <br>
      <span id="contentUrl">
      <logic:equal name="ptl100Form" property="ptl100contentType" value="0">
        <html:textarea styleClass="portletContentClass" styleId="ptletContentArea" property="ptl100content" cols="50" rows="40"/>
      </logic:equal>
      <logic:equal name="ptl100Form" property="ptl100contentType" value="1">
        URL: <html:text property="ptl100contentUrl" size="40" style="width:271px;" />
      </logic:equal>
      <logic:equal name="ptl100Form" property="ptl100contentType" value="2">
        <html:textarea property="ptl100contentHtml" style="width:628px;" rows="20" />
      </logic:equal>
      </td>
      </tr>

      <!-- 編集かつ「URLを指定」でない場合のみ表示 -->
      <logic:equal name="ptl100Form" property="ptlCmdMode" value="1">
        <logic:notEqual name="ptl100Form" property="ptl100contentType" value="1">
          <tr>
          <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.image" /></span></td>
          <td align="left" class="td_wt" width="80%">
          <input type="button" name="pltImageBtn" class="btn_add_n1" onClick="openPortletImagePopup(0, 0);" value="<gsmsg:write key="ptl.ptl100.1" />">


    <logic:notEmpty name="ptl100Form" property="ptl100ImageList">
    <br>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th align="center" class="td_type24" width="100%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.image" /></span></th>
    <th align="center" class="td_type24" width="0%" nowrap><span class="text_tlw">&nbsp;</span></th>
    <th align="center" class="td_type24" width="0%" nowrap><span class="text_tlw">&nbsp;</span></th>
    </tr>

    <bean:define id="tdColor" value="" />

    <logic:iterate id="imageModel" name="ptl100Form" property="ptl100ImageList" indexId="idx">

    <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr>
    <td id="pltImg0_1" align="left" class="<%= tdColor %>" width="100%" nowrap>
       <a href="javascript:void(0);" onClick="return selectPortletImage('<bean:write name="imageModel" property="pliSid" />');"><span class="text_link"><bean:write name="imageModel" property="pliName" /></span></a>
    </td>
    <td id="pltImg1_1" align="center" class="<%= tdColor %>" width="0%" nowrap>
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.edit" />" name="btn_edit" onClick="openPortletImagePopup(1, <bean:write name="imageModel" property="pliSid" />);">
    </td>
    <td id="pltImg2_1" align="center" class="<%= tdColor %>" width="0%" nowrap>
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.delete" />" name="btn_delete" onClick="deleteImage('<bean:write name="imageModel" property="pliSid" />');">
    </td>
    </tr>

    </logic:iterate>

    </table>

    </logic:notEmpty>

          </td>
          </tr>

        </logic:notEqual>
      </logic:equal>


      <!-- 登録かつ「URLを指定」でない場合のみ表示 -->
      <logic:equal name="ptl100Form" property="ptlCmdMode" value="0">
        <logic:notEqual name="ptl100Form" property="ptl100contentType" value="1">
          <tr>
          <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.image" /></span></td>
          <td align="left" class="td_wt" width="80%">
          <span class="text_r1">※<gsmsg:write key="ptl.ptl100.4" /></span>
          </td>
          </tr>

        </logic:notEqual>
      </logic:equal>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="ptl.8" /></span></td>
      <td align="left" class="td_wt" width="80%">
      <html:textarea property="ptl100description" style="width:455px;" rows="3" styleClass="text_gray" onkeyup="showLengthStr(value, 1000, 'inputlength');" styleId="inputstr" />
      <br>
      <span class="font_string_count"><gsmsg:write key="wml.js.15" /></span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;1000&nbsp;<gsmsg:write key="cmn.character" /></span>
      </td>
      </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ptl100add')">
          <logic:equal name="ptl100Form" property="ptlCmdMode" value="<%= String.valueOf(tCmdEdit) %>">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
          </logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ptl100back')">
        </td>
      </tr>
    </table>
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<!-- Footer -->

<script type="text/javascript">
    tinyMCE.init({
        // General options
        mode : "exact",
        elements : "ptletContentArea",
        theme : "advanced",
        plugins : "safari,style,layer,table,save,advhr,advimage,advlink,inlinepopups,insertdatetime,preview,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras",
        language : "ja",
        width : "455px",

        // Theme options
        theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons2 : "cut,copy,paste,|,bullist,numlist,|,outdent,indent,|,undo,redo,|,link,unlink,image,code,|,preview,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : true
    });
</script>

</html:form>

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>


</body>

</html:html>