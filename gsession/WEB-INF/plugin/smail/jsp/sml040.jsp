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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%=GSConst.VERSION_PARAM%>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%=GSConst.VERSION_PARAM%>'></script>
<script language="JavaScript" src="../smail/js/sml040.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="cmn.display.settings" /></title>
</head>

<body class="body_03" onload="setShowHide();">
<html:form action="/smail/sml040">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />
<html:hidden property="sml040MaxDspStype" />
<html:hidden property="sml040ReloadTimeStype" />
<html:hidden property="sml040PhotoDspStype" />
<html:hidden property="sml040AttachImgDspStype" />

<logic:notEmpty name="sml040Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml040Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.display.settings" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('edit');">
      <input type="button" value="<gsmsg:write key="cmn.back.preferences" />" class="btn_back_n3" onClick="buttonPush('backToList');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <!-- 表示件数 -->
    <tr id="smlMaxDspArea">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.number.display" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
        <html:select name="sml040Form" property="sml040ViewCnt">
          <html:optionsCollection name="sml040Form" property="sml040DspCntList" value="value" label="label" />
        </html:select>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.02" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- 自動リロード時間 -->
    <tr id="smlReloadTimeArea">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.auto.reload.time" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
        <html:select name="sml040Form" property="sml040ReloadTime">
        <html:optionsCollection name="sml040Form" property="sml040TimeLabelList" value="value" label="label" />
        </html:select>
      </td>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.01" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- 写真画像表示設定 -->
    <tr id="smlPhotoDspArea">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml040.05" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
        <html:radio name="sml040Form" property="sml040PhotoDsp" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_IMAGE_TEMP_DSP) %>" styleId="sml040PhotoDsp_01"><label for="sml040PhotoDsp_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label></html:radio>
        <html:radio name="sml040Form" property="sml040PhotoDsp" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_IMAGE_TEMP_NOT_DSP) %>" styleId="sml040PhotoDsp_02"><label for="sml040PhotoDsp_02"><span class="text_base"><gsmsg:write key="cmn.hide" /></span></label></html:radio>
      </td>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.06" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- 添付画像表示設定 -->
    <tr id="smlAttachImgDspArea">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml040.07" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
        <html:radio name="sml040Form" property="sml040ImageTempDsp" value="0" styleId="sml040ImageTempDsp_01"><label for="sml040ImageTempDsp_01"><span class="text_base"><gsmsg:write key="cmn.show" /></span></label></html:radio>
        <html:radio name="sml040Form" property="sml040ImageTempDsp" value="1" styleId="sml040ImageTempDsp_02"><label for="sml040ImageTempDsp_02"><span class="text_base"><gsmsg:write key="cmn.hide" /></span></label></html:radio>
      </td>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1">&nbsp;<gsmsg:write key="sml.sml040.08" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('edit');">
      <input type="button" value="<gsmsg:write key="cmn.back.preferences" />" class="btn_back_n3" onClick="buttonPush('backToList');">
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