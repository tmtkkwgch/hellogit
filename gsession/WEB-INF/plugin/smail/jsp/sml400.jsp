<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="sml.sml400.01" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<script language="JavaScript" src="../common/js/cmd.js?<%=GSConst.VERSION_PARAM%>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%=GSConst.VERSION_PARAM%>'></script>
<script language="JavaScript" src="../smail/js/sml400.js?<%=GSConst.VERSION_PARAM%>"></script>
<link rel=stylesheet href='../common/css/default.css?<%=GSConst.VERSION_PARAM%>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%=GSConst.VERSION_PARAM%>' type='text/css'>
</head>

<body class="body_03" onload="setShowHide();">
<html:form action="/smail/sml400">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="smlAccountSid" />
<html:hidden property="smlViewAccount" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />

<logic:notEmpty name="sml400Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml400Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center" cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.display.settings" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backToMenu');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
    </tr>
    </table>
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5"><br>

    <!-- 表示件数 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="smlMaxDspArea1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.number.display" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <!-- 表示件数 設定種別 -->
    <td valign="middle" align="left" class="td_wt">
      <div align="left" width="100%" style="float:left;">
        <html:radio name="sml400Form" styleId="sml400MaxDspStype_01" property="sml400MaxDspStype" value="1" onclick="changeStypeMaxDsp();" />
        <label for="sml400MaxDspStype_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
        <html:radio name="sml400Form" styleId="sml400MaxDspStype_02" property="sml400MaxDspStype" value="0" onclick="changeStypeMaxDsp();" />
        <label for="sml400MaxDspStype_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      </div>
      <div align="right" width="0%" style="margin:5px;float:right;" nowrap><span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.02" /></span></div>
    </td>
    </tr>
    <tr id="smlMaxDspArea2">
    <!-- 表示件数 選択 -->
    <td valign="middle" align="left" class="td_wt" nowrap>
      <html:select name="sml400Form" property="sml400MaxDsp">
      <html:optionsCollection name="sml400Form" property="sml400MaxDspList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <!-- 自動リロード時間 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="smlReloadTimeArea1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.auto.reload.time" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <!-- 自動リロード時間 設定種別 -->
    <td valign="middle" align="left" class="td_wt">
      <div align="left" width="100%" style="float:left;">
        <html:radio name="sml400Form" styleId="sml400ReloadTimeStype_01" property="sml400ReloadTimeStype" value="1" onclick="changeStypeReloadTime();" />
        <label for="sml400ReloadTimeStype_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
        <html:radio name="sml400Form" styleId="sml400ReloadTimeStype_02" property="sml400ReloadTimeStype" value="0" onclick="changeStypeReloadTime();" />
        <label for="sml400ReloadTimeStype_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      </div>
      <div align="right" width="0%" style="margin:5px;float:right;" nowrap><span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.01" /></span></div>
    </td>
    </tr>
    <tr>
    <!-- 自動リロード時間 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlReloadTimeArea2" nowrap>
        <html:select name="sml400Form" property="sml400ReloadTime">
        <html:optionsCollection name="sml400Form" property="sml400ReloadTimeList" value="value" label="label" />
        </html:select>
    </td>
    </tr>

    <!-- 写真表示設定 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="smlPhotoDspArea1" nowrap>
      <span class="text_bb1"><gsmsg:write key="sml.sml040.05" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <!-- 写真表示設定 設定種別 -->
    <td valign="middle" align="left" class="td_wt">
      <div align="left" width="100%" style="float:left;">
        <html:radio name="sml400Form" styleId="sml400PhotoDspStype_01" property="sml400PhotoDspStype" value="1" onclick="changeStypePhotoDsp();" />
        <label for="sml400PhotoDspStype_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
        <html:radio name="sml400Form" styleId="sml400PhotoDspStype_02" property="sml400PhotoDspStype" value="0" onclick="changeStypePhotoDsp();" />
        <label for="sml400PhotoDspStype_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      </div>
      <div align="right" width="0%" style="margin:5px;float:right;" nowrap><span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.06" /></span></div>
    </td>
    </tr>
    <tr>
    <!-- 写真表示設定 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlPhotoDspArea2" nowrap>
        <html:radio name="sml400Form" property="sml400PhotoDsp" value="0" styleId="sml400PhotoDsp_01"><label for="sml400PhotoDsp_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label></html:radio>
        <html:radio name="sml400Form" property="sml400PhotoDsp" value="1" styleId="sml400PhotoDsp_02"><label for="sml400PhotoDsp_02"><span class="text_base"><gsmsg:write key="cmn.hide" /></span></label></html:radio>
    </td>
    </tr>

    <!-- 添付画像表示設定 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="smlAttachImgDspArea1" nowrap>
      <span class="text_bb1"><gsmsg:write key="sml.sml040.07" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <!-- 添付画像表示設定 設定種別 -->
    <td valign="middle" align="left" class="td_wt">
      <div align="left" width="100%" style="float:left;">
        <html:radio name="sml400Form" styleId="sml400AttachImgDspStype_01" property="sml400AttachImgDspStype" value="1" onclick="changeStypeAttachImgDsp();" />
        <label for="sml400AttachImgDspStype_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
        <html:radio name="sml400Form" styleId="sml400AttachImgDspStype_02" property="sml400AttachImgDspStype" value="0" onclick="changeStypeAttachImgDsp();" />
        <label for="sml400AttachImgDspStype_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      </div>
      <div align="right" width="0%" style="margin:5px;float:right;" nowrap><span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.08" /></span></div>
    </td>
    </tr>
    <tr>
    <!-- 添付画像表示設定 選択 -->
    <td valign="middle" align="left" class="td_wt" id="smlAttachImgDspArea2" nowrap>
        <html:radio name="sml400Form" property="sml400AttachImgDsp" value="0" styleId="sml400AttachImgDsp_01"><label for="sml400AttachImgDsp_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label></html:radio>
        <html:radio name="sml400Form" property="sml400AttachImgDsp" value="1" styleId="sml400AttachImgDsp_02"><label for="sml400AttachImgDsp_02"><span class="text_base"><gsmsg:write key="cmn.hide" /></span></label></html:radio>
    </td>
    </tr>

    </table>

    <table cellpadding="0" cellpadding="5" border="0" width="100%"><br>
    <tr>
    <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('backToMenu');">
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
</body>
</html:html>