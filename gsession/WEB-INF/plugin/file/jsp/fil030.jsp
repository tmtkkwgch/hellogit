<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.fil.GSConstFile" %>

<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.fil.GSConstFile.MAX_LENGTH_BIKO); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.51" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>


<logic:equal name="fil030Form" property="admVerKbn" value="1">
<body class="body_03" onload="showOrHide();setVersionComboStatus();showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="windowClose();">
</logic:equal>
<logic:notEqual name="fil030Form" property="admVerKbn" value="1">
<body class="body_03" onload="showOrHide();showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="windowClose();">
</logic:notEqual>


<html:form action="/file/fil030">
<input type="hidden" name="CMD" value="">
<html:hidden property="cmnMode" />
<html:hidden property="backDsp" />
<html:hidden property="backScreen" />
<html:hidden property="filSearchWd" />
<html:hidden property="admVerKbn" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil030SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="fil030binSid" />
<html:hidden property="fil030ImageName" />
<html:hidden property="fil030ImageSaveName" />
<html:hidden property="fil030InitFlg" />
<logic:equal name="fil030Form" property="cmnMode" value="2">
<html:hidden name="fil030Form" property="fil030CabinetName" />
</logic:equal>

<logic:notEmpty name="fil030Form" property="fil220sltCheck" scope="request">
<logic:iterate id="select" name="fil030Form" property="fil220sltCheck" scope="request">
  <input type="hidden" name="fil220sltCheck" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030Form" property="fil030SvAcFull">
<logic:iterate id="afid" name="fil030Form" property="fil030SvAcFull">
  <input type="hidden" name="fil030SvAcFull" value="<bean:write name="afid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030Form" property="fil030SvAcRead">
<logic:iterate id="arid" name="fil030Form" property="fil030SvAcRead">
  <input type="hidden" name="fil030SvAcRead" value="<bean:write name="arid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030Form" property="fil030SvAdm">
<logic:iterate id="admid" name="fil030Form" property="fil030SvAdm">
  <input type="hidden" name="fil030SvAdm" value="<bean:write name="admid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030Form" property="fil010SelectDelLink" scope="request">
<logic:iterate id="item" name="fil030Form" property="fil010SelectDelLink" scope="request">
  <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil030Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil030Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil030Form" property="cmnMode" />">

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../file/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.filekanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.filekanri" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="fil.51" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

    <logic:notEqual name="fil030Form" property="cmnMode" value="1">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.entry" />" onclick="buttonPush('fil030edit');">
    </logic:notEqual>

    <logic:equal name="fil030Form" property="cmnMode" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttonPush('fil030edit');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil030delete');">
    </logic:equal>
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil030back');">
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

    <img src="../project/images/spacer.gif" width="1px" height="10px" border="0">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.13" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
    <logic:notEqual name="fil030Form" property="cmnMode" value="2">
    <html:text name="fil030Form" maxlength="70" property="fil030CabinetName" styleClass="text_base" style="width:283px;" />
    </logic:notEqual>
    <logic:equal name="fil030Form" property="cmnMode" value="2">
    <bean:write name="fil030Form" property="fil030CabinetName" filter="false" />
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.102" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <html:radio name="fil030Form" property="fil030AccessKbn" styleId="okini0" value="0" onclick="showOrHide();"/>
      <span class="text_base7"><label for="okini0"><gsmsg:write key="cmn.not.limit" /></label></span>&nbsp;
      <html:radio name="fil030Form" property="fil030AccessKbn" styleId="okini1" value="1" onclick="showOrHide();"/>
      <span class="text_base7"><label for="okini1"><gsmsg:write key="cmn.do.limit" /></label>&nbsp;</span>
      <logic:notEqual name="fil030Form" property="cmnMode" value="<%= GSConstFile.CMN_MODE_ADD %>">
        <span class="text_base">
        <html:checkbox name="fil030Form" property="file030AdaptIncFile" value="1" styleId="adaptIncFile"/>
        <label for="adaptIncFile"><gsmsg:write key="fil.127" /></label></span>
      </logic:notEqual>
    <div id="hide0">
    </div>
    <div id="show0">
      <table width="100%" border="0">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span><span class="text_r2">※</span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" align="left">
      <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.fil030AcSltGroup, 'fil030AcSltGroup', '<bean:write name="fil030Form" property="fil030AcSltGroup" />', '0', 'changeGrp', 'fil030SvAcFull', 'fil030SvAcRead', 'file', '-1', '1')" type="button"><br>
      <html:select name="fil030Form" property="fil030AcSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil030Form" property="fil030AcGroupLavel">
      <html:optionsCollection name="fil030Form" property="fil030AcGroupLavel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      <input type="button" onclick="openGroupWindow(this.form.fil030AcSltGroup, 'fil030AcSltGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="fil030GroupBtn">
      <span class="text_base">
      <input type="checkbox" name="fil030AcAllSlt" value="1" id="select_user" onclick="return selectAccessList();" />
      <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center">
      <html:select name="fil030Form" property="fil030AcFull" size="5" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil030Form" property="fil030AcFullLavel">
      <html:optionsCollection name="fil030Form" property="fil030AcFullLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil030fulladd');">
      <br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil030fulldel');">
      </td>

      <td valign="top" rowspan="3">
      <html:select name="fil030Form" property="fil030AcRight" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil030Form" property="fil030AcRightLavel">
      <html:optionsCollection name="fil030Form" property="fil030AcRightLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      </tr>

      <tr>
      <td align="center" valign="top">
      <html:select name="fil030Form" property="fil030AcRead" size="5" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil030Form" property="fil030AcReadLavel">
      <html:optionsCollection name="fil030Form" property="fil030AcReadLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil030readadd');"><br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil030readdel');">
      </td>
      </tr>

      </table>


    </div>


    </td>
    </tr>


    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.14" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">

      <table width="0%" border="0">

      <tr>
      <td align="left" colspan="3" class="text_base"><gsmsg:write key="fil.fil030.1" /></td>
      </tr>

      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" align="left">
      <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.fil030AdmSltGroup, 'fil030AdmSltGroup', '<bean:write name="fil030Form" property="fil030AdmSltGroup" />', '0', 'changeGrp', 'fil030SvAdm', '-1', '0')" type="button">
      <html:select name="fil030Form" property="fil030AdmSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil030Form" property="fil030AdmGroupLavel">
      <html:optionsCollection name="fil030Form" property="fil030AdmGroupLavel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>

      <span class="text_base">
      <input type="button" onclick="openGroupWindow(this.form.fil030AdmSltGroup, 'fil030AdmSltGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="fil030GroupBtn2">
      <input type="checkbox" name="fil030AdmAllSlt" value="1" id="select_admin" onclick="return selectAdminList();" />
      <label for="select_admin"><gsmsg:write key="cmn.select" /></label></span>

      </td>
      </tr>

      <tr>
      <td align="center">
      <html:select name="fil030Form" property="fil030Adm" size="13" multiple="true" styleClass="select01" style="width: 220px;">
      <logic:notEmpty name="fil030Form" property="fil030AdmLavel">
      <html:optionsCollection name="fil030Form" property="fil030AdmLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil030admadd');"><br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil030admdel');">
      </td>

      <td valign="top">
      <html:select name="fil030Form" property="fil030AdmRight" size="13" multiple="true" styleClass="select01" style="width: 220px;">
      <logic:notEmpty name="fil030Form" property="fil030AdmRightLavel">
      <html:optionsCollection name="fil030Form" property="fil030AdmRightLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.3" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <html:radio name="fil030Form" property="fil030CapaKbn" styleId="disksize0" value="0" onclick="showOrHide();"/><span class="text_base7"><label for="disksize0"><gsmsg:write key="cmn.noset" /></label></span>&nbsp;
      <html:radio name="fil030Form" property="fil030CapaKbn" styleId="disksize1" value="1" onclick="showOrHide();"/><span class="text_base7"><label for="disksize1"><gsmsg:write key="cmn.setting.do" /></label>&nbsp;</span>
    <div id="hide1">
    </div>

    <div id="show1">
<br>&nbsp;&nbsp;
<span class="text_base"><gsmsg:write key="fil.4" />：</span>
    <html:text name="fil030Form" maxlength="7" property="fil030CapaSize" styleClass="text_base" style="width:73px;" />
    <span class="text_base">MB</span>
<br>&nbsp;&nbsp;

    <span class="text_base"><gsmsg:write key="fil.fil030kn.1" />：</span>
    <html:select name="fil030Form" property="fil030CapaWarn" styleClass="select01" style="width: 80px;">
    <logic:notEmpty name="fil030Form" property="fil030CapaWarnLavel">
    <html:optionsCollection name="fil030Form" property="fil030CapaWarnLavel" value="value" label="label" />
    </logic:notEmpty>
    </html:select>

    <br>
    <span class="text_r2">※<gsmsg:write key="cmn.not.specified.nowarn" /></span>
    </div>

    </td>
    </tr>

    <logic:equal name="fil030Form" property="admVerKbn" value="1" >
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="fil.5" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <span class="text_base"><html:checkbox name="fil030Form" property="fil030VerAllKbn" value="1" styleId="select_version" onclick="return setVersionComboStatus();"/>
    <label for="select_version"><gsmsg:write key="fil.15" /></label>
    </span>
    <span class="text_base">&nbsp;&nbsp;<gsmsg:write key="fil.fil030.3" />：</span>
    <html:select name="fil030Form" property="fil030VerKbn" styleClass="select01" style="width: 80px;">
    <logic:notEmpty name="fil030Form" property="fil030VerKbnLavel">
    <html:optionsCollection name="fil030Form" property="fil030VerKbnLavel" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    </tr>

    </logic:equal>


    <logic:notEqual name="fil030Form" property="cmnMode" value="2">
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.icon" /></span><span class="text_r2">※</span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <table width="100%" border="0">
      <tr>
      <td width="0%">
        <logic:empty name="fil030Form" property="fil030ImageName">
        <img src="../file/images/cabinet.gif" alt="">
        </logic:empty>
        <logic:notEmpty name="fil030Form" property="fil030ImageName">
        <logic:equal name="fil030Form" property="cmnMode" value="0">
          <img width="30" src="../file/fil030.do?CMD=getImageFile" name="pitctImage" alt="<gsmsg:write key="cmn.photo" />" border="1" onload="">
        </logic:equal>
        <logic:equal name="fil030Form" property="cmnMode" value="1">
          <img width="30" src="../file/fil030.do?CMD=getImageFile&cmnMode=<bean:write name="fil030Form" property="cmnMode" />&fil030SelectCabinet=<bean:write name="fil030Form" property="fil030SelectCabinet" />" name="pitctImage" alt="<gsmsg:write key="cmn.photo" />" border="1" onload="">
        </logic:equal>
        </logic:notEmpty>
      </td>
      </tr>
      <tr>
      <td>
        <span class="text_base"><gsmsg:write key="cmn.icon.size" /></span><br>
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('fil030tempdeleteMark');">
        &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('fil030binSid', '<bean:write name="fil030Form" property="fil030pluginId" />', '0', '5', 'mark');">
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </logic:notEqual>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
    <textarea name="fil030Biko" style="width:541px;" rows="5" styleClass="text_base" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="fil030Form" property="fil030Biko" /></textarea>
    <br>
    <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
<%--
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>
        <html:select property="fil030SelectTempFiles" styleClass="select01" multiple="true" size="5">
        <html:optionsCollection name="fil030Form" property="fil030FileLabelList" value="value" label="label" />
        </html:select>
      </td>
      <td width="0%" align="left" valign="top" nowrap><img src="../file/images/spacer.gif" width="10px" height="1px" border="0"></td>
      <td width="100%" align="left" valign="middle">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('fil030tempdelete');">
      <br><br>
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('fil030SelectTempFiles', '<bean:write name="fil030Form" property="fil030pluginId" />', '0', '3', 'temp');">
      </td>
      </tr>
      </table>


    </td>
    </tr>
--%>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="right">
    <logic:notEqual name="fil030Form" property="cmnMode" value="1">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.entry" />" onclick="buttonPush('fil030edit');">
    </logic:notEqual>

    <logic:equal name="fil030Form" property="cmnMode" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttonPush('fil030edit');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil030delete');">
    </logic:equal>

    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil030back');">
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>