<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% String maxLengthComment = String.valueOf(jp.groupsession.v2.ip.IpkConst.MAX_LENGTH_MSG); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=shift_jis">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.ipk020.1" /></title>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthComment %>, 'inputlength');scroll();">
<html:form action="/ipkanri/ipk020">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name='ipk020Form' property='ipk020helpMode' />">
<html:hidden property="netSid" />
<html:hidden property="cmd" />
<html:hidden property="binSid" />
<html:hidden property="ipk020AdminFlg" />
<logic:notEmpty name="ipk020Form" property="adminSidList">
<logic:iterate id="param" name="ipk020Form" property="adminSidList">
<input type="hidden" name="adminSidList" value="<bean:write name='param' />">
</logic:iterate>
</logic:notEmpty>
<html:hidden property="ipk020ScrollFlg" />
<table align="center" width="820" table_kotei3>
<tr>
<td>
  <table class="table_kotei2" cellpadding="0">

  <tr>
  <td width="0%">
  <img src="../ipkanri/images/header_ipkanri_icon_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.ipkanri" /></span></td>
  <td width="100%" class="header_white_bg_text">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <logic:empty name="ipk020Form" property="netSid">[ <gsmsg:write key="ipk.ipk020.2" /> ]</logic:empty>
  <logic:notEmpty name="ipk020Form" property="netSid">[ <gsmsg:write key="ipk.ipk020.1" /> ]</logic:notEmpty>
  </logic:equal>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">[ <gsmsg:write key="ipk.ipk020.4" /> ]
  </logic:equal>
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />" /></td>
  </tr>
  </table>
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="40%"></td>
  <td width="0%"><img src="../ipkanri/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
  <td class="header_glay_bg" width="60%">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <logic:notEmpty name="ipk020Form" property="netSid">
  <input type="button" name="edit" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush2('netDelete');" class="btn_dell_n1">
  </logic:notEmpty>
  <logic:empty name="ipk020Form" property="netSid">
  <input type="button" name="edit" value="<gsmsg:write key="cmn.entry" />" onClick="buttonPush2('netAdd');" class="btn_base1">
  </logic:empty>
  <logic:notEmpty name="ipk020Form" property="netSid">
  <input type="button" name="edit" value="<gsmsg:write key="cmn.edit" />" onClick="buttonPush2('netEdit');" class="btn_edit_n1">
  </logic:notEmpty>
  </logic:equal>
  <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('return', '');" class="btn_back_n1"></td>
  <td width="0%"><img src="../ipkanri/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
  </tr>
  </table>
</td>
</tr>
<tr>
<td>
<span class="text_error"><html:errors/></span>
</td>
</tr>
<tr>
<td>
<br>
  <table class="table_padding">
  <tr>
  <td valign="top" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.name4" /></span>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <span class="font_red2">※</span></logic:equal></td>
  <td class="td_type1">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <input type="text" name="netName" style="width:213px;" maxlength="50" value="<bean:write name="ipk020Form" property="netName" />"></td>
  </logic:equal>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">
  <bean:write name="ipk020Form" property="netName" />
  </logic:equal>
  </tr>

  <tr>
  <td width="160" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="ipk.2" /></span>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true"><span class="font_red2">※</span></logic:equal></td>
  <td class="td_type1">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <input type="text" name="netNetad1" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netNetad1" />" class="text_number">&nbsp; .
  <input type="text" name="netNetad2" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netNetad2" />" class="text_number">&nbsp; .
  <input type="text" name="netNetad3" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netNetad3" />" class="text_number">&nbsp; .
  <input type="text" name="netNetad4" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netNetad4" />" class="text_number">
  </logic:equal>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">
  <bean:write name="ipk020Form" property="netNetad1" />.
  <bean:write name="ipk020Form" property="netNetad2" />.
  <bean:write name="ipk020Form" property="netNetad3" />.
  <bean:write name="ipk020Form" property="netNetad4" />
  </logic:equal>
  </td>

  <tr>
  <td width="160" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="ipk.3" /></span>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true"><span class="font_red2">※</span></logic:equal></td>
  <td class="td_type1">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <input type="text" name="netSabnet1" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netSabnet1" />" class="text_number">&nbsp; .
  <input type="text" name="netSabnet2" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netSabnet2" />" class="text_number">&nbsp; .
  <input type="text" name="netSabnet3" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netSabnet3" />" class="text_number">&nbsp; .
  <input type="text" name="netSabnet4" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netSabnet4" />" class="text_number">
  </logic:equal>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">
  <bean:write name="ipk020Form" property="netSabnet1" />.
  <bean:write name="ipk020Form" property="netSabnet2" />.
  <bean:write name="ipk020Form" property="netSabnet3" />.
  <bean:write name="ipk020Form" property="netSabnet4" />
  </logic:equal>
  </td>
  </tr>

  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <tr>
  <td width="160" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.public" />/<gsmsg:write key="cmn.private" /></span>
  </td>
  <td class="td_type1">
  <html:radio property="netDsp" value="0" styleId="network_dsp"/><label for="network_dsp" class="font_black4"/><gsmsg:write key="cmn.public" /></label>
  <html:radio property="netDsp" value="9" styleId="network_not_dsp"/><label for="network_not_dsp" class="font_black4"/><gsmsg:write key="cmn.private" /></label>
  </td>
  </tr>
  </logic:equal>

  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <tr>
  <td width="160" align="left" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.sort" /></span><span class="font_red2">※</span></td>
  <td class="td_type1">
  <input type="text" name="netSort" style="width:57px;" maxlength="3" value="<bean:write name="ipk020Form" property="netSort" />" class="text_number">
  </td>
  </tr>
  </logic:equal>

  <tr>
  <td align="left" valign="top" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.comment" /></span></td>
  <td class="td_type1">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <textarea name="netMsg" style="width:572px;" rows="7"  class="textarea" onkeyup="showLengthStr(value, <%= maxLengthComment %>, 'inputlength');" id="inputstr"><bean:write name="ipk020Form" property="netMsg" /></textarea>
  <br>
  <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthComment %>&nbsp;<gsmsg:write key="cmn.character" /></span>
  </logic:equal>
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">
  <bean:write name="ipk020Form" property="netMsgHtml" filter="false"/>
  </logic:equal>
  </td>
  </tr>
  <tr>
  <td class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.attached" />(<gsmsg:write key="cmn.public" />)</span></td>
  <td align="left" class="td_type1">
    <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
    <table class="table_kotei2">
    <tr>
    <td width="0%" class="td_button2"><input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush2('delTempFile');"></td>
    <td width="0%" class="td_button2">
    <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('ipk020KoukaiFiles', 'ipkanri', '0', '0', 'koukai');">
    </td>
    <td width="100%" class="td_button2">
    <input type="button" name="download" value="<gsmsg:write key="cmn.download" />" onClick="buttonPush2('koukaiTempDownload');" class="btn_base1_3" />
    <a id="add_user" name="add_user"></a>
    </td>
    </tr>
    <tr>
    <td colspan="3">
    <html:select property="ipk020KoukaiFiles" styleClass="select01" multiple="true">
    <logic:notEmpty name="ipk020Form" property="ipk020KoukaiFileLabelList">
    <html:optionsCollection name="ipk020Form" property="ipk020KoukaiFileLabelList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>
    </table>
    </logic:equal>

    <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">
    <logic:notEmpty name="ipk020Form" property="ipk020KoukaiFileLabelList">
    <logic:iterate id="koukaiFileMdl" name="ipk020Form" property="koukaiBinFileInfList">
    <img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />">
    <a href="javascript:fileLinkClick(<bean:write name='koukaiFileMdl' property='binSid' />);">
    <span class="text_link"><bean:write name="koukaiFileMdl" property="binFileName" /><bean:write name="koukaiFileMdl" property="binFileSizeDsp" /></a><br>
    </logic:iterate>
    </logic:notEmpty>
    </logic:equal>
  </td>
  </tr>

  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <tr>
  <td class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="cmn.attached" />(<gsmsg:write key="cmn.private" />)</span></td>
  <td align="left" class="td_type1">
    <table class="table_kotei2">
    <tr>
    <td width="0%" class="td_button2"><input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush2('delAdmTempFile');"></td>
    <td width="0%" class="td_button2">
    <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('ipk020HikoukaiFiles', 'ipkanri', '0', '0', 'hikoukai');">
    </td>
    <td width="100%" class="td_button2"><input type="button" name="download" value="<gsmsg:write key="cmn.download" />" onClick="buttonPush2('hikoukaiTempDownload');" class="btn_base1_3"></td>
    </tr>
    <tr>
    <td colspan="3">
    <html:select property="ipk020HikoukaiFiles" styleClass="select01" multiple="true">
    <logic:notEmpty name="ipk020Form" property="ipk020HikoukaiFileLabelList">
    <html:optionsCollection name="ipk020Form" property="ipk020HikoukaiFileLabelList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>
    </table>
  </td>
  </tr>
  </logic:equal>

  <tr class="td_border">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <td align="left" rowspan="1" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="ipk.ipk020.5" /></span></td>
  <td class="td_type1" cellspacing="0" cellpadding="0">
    <table width="100%" border="0" cellpadding="0" algin="center">
    <tr>
    <td align="left">
      <table width="100%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="font_black2"><gsmsg:write key="ipk.ipk020.6" /></span></td>
      <td width="20%" align="center"> &nbsp; </td>
      <td width="35%" align="left">
      <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.groupSelect, 'groupSelect', '<bean:write name="ipk020Form" property="groupSelect" />', '0', 'changeGrp', 'adminSidList', '-1', '0', '0', '0', '1')" type="button">
      <html:select name="ipk020Form" property="groupSelect" styleClass="select01" onchange="changeGrp();">
      <logic:notEmpty name="ipk020Form" property="groupList">
      <html:optionsCollection name="ipk020Form" property="groupList" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      </td>
      <td width="10%" align="left" valign="bottom">
      <input type="button" onclick="openGroupWindowForIpkanri(this.form.groupSelect, 'groupSelect', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="ipk020GroupBtn">
      </td>
      </tr>
      <tr>
      <td>
      <html:select name="ipk020Form" property="selectLeftUser" size="5" multiple="true" styleClass="select01">
      <logic:notEmpty name="ipk020Form" property="leftUserList">
      <html:optionsCollection name="ipk020Form" property="leftUserList" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="ipk020ButtonPush(1);"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="ipk020ButtonPush(2);">

      </td>
      <td align="center">
      <html:select name="ipk020Form" property="selectRightUser" size="5" multiple="true" styleClass="select01">
      <logic:notEmpty name="ipk020Form" property="rightUserList">
      <html:optionsCollection name="ipk020Form" property="rightUserList" value="value" label="label" filter="false"/>
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>
  </td>
  </logic:equal>

  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="false">
  <td align="left" rowspan="1" class="table_bg_A5B4E1" nowrap><span class="font_black2"><gsmsg:write key="ipk.ipk020.5" /></span></td>
  <td class="td_type1" cellspacing="0" cellpadding="0">
  <logic:notEmpty name="ipk020Form" property="leftUserList">
  <logic:iterate id="param" name="ipk020Form" property="leftUserList">
  <bean:write name="param" property="label" /><br />
  </logic:iterate>
  </logic:notEmpty>
  </td>
  </logic:equal>
  </tr>
  </table>
</td>
</tr>
<tr>
<td>
  <table width="100%" cellspacing="0" cellpadding="0" align="center">
  <tr>
  <td align="right">
  <logic:equal name="ipk020Form" property="ipk020AdminFlg" value="true">
  <logic:notEmpty name="ipk020Form" property="netSid">
  <input type="button" name="edit" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush2('netDelete');" class="btn_dell_n1">
  </logic:notEmpty>
  <logic:empty name="ipk020Form" property="netSid">
  <input type="button" name="edit" value="<gsmsg:write key="cmn.entry" />" onClick="buttonPush2('netAdd');" class="btn_base1">
  </logic:empty>
  <logic:notEmpty name="ipk020Form" property="netSid">
  <input type="button" name="edit" value="<gsmsg:write key="cmn.edit" />" onClick="buttonPush2('netEdit');" class="btn_edit_n1">
  </logic:notEmpty>
  </logic:equal>
  <input type="button" name="cancel" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('return', '');" class="btn_back_n1">
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