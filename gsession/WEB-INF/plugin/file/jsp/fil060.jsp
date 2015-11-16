<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.fil.GSConstFile" %>

<%
    String maxLengthBiko        = String.valueOf(jp.groupsession.v2.fil.GSConstFile.MAX_LENGTH_FOLDER_BIKO);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="cmn.edit.folder" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil060.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onunload="windowClose();" onload="showOrHide();parentAccessShowOrHide(<bean:write name="fil060Form" property="fil060ParentAccessAll" />);showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');">

<html:form action="/file/fil060">
<input type="hidden" name="CMD" value="">
<html:hidden property="backDsp" />
<html:hidden property="backDspLow" />
<html:hidden property="filSearchWd" />

<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />

<html:hidden property="fil050DirSid" />
<html:hidden property="fil050ParentDirSid" />
<html:hidden property="fil060CmdMode" />
<html:hidden property="fil060PluginId" />
<html:hidden property="fil060ParentAccessAll" />
<html:hidden property="fil060ParentZeroUser" />

<html:hidden name="fil060Form" property="fil100SltCabinetSid" />
<html:hidden name="fil060Form" property="fil100ChkTrgFolder" />
<html:hidden name="fil060Form" property="fil100ChkTrgFile" />
<html:hidden name="fil060Form" property="fil100SearchMode" />
<html:hidden name="fil060Form" property="fil100ChkWdTrgName" />
<html:hidden name="fil060Form" property="fil100ChkWdTrgBiko" />
<html:hidden name="fil060Form" property="fil100ChkWdTrgText" />
<html:hidden name="fil060Form" property="fileSearchfromYear" />
<html:hidden name="fil060Form" property="fileSearchfromMonth" />
<html:hidden name="fil060Form" property="fileSearchfromDay" />
<html:hidden name="fil060Form" property="fileSearchtoYear" />
<html:hidden name="fil060Form" property="fileSearchtoMonth" />
<html:hidden name="fil060Form" property="fileSearchtoDay" />
<html:hidden name="fil060Form" property="fil100ChkOnOff" />

<html:hidden name="fil060Form" property="fil100SvSltCabinetSid" />
<html:hidden name="fil060Form" property="fil100SvChkTrgFolder" />
<html:hidden name="fil060Form" property="fil100SvChkTrgFile" />
<html:hidden name="fil060Form" property="fil100SvSearchMode" />
<html:hidden name="fil060Form" property="fil100SvChkWdTrgName" />
<html:hidden name="fil060Form" property="fil100SvChkWdTrgBiko" />
<html:hidden name="fil060Form" property="fil100SvChkWdTrgText" />
<html:hidden name="fil060Form" property="fil100SvChkWdKeyWord" />
<html:hidden name="fil060Form" property="fileSvSearchfromYear" />
<html:hidden name="fil060Form" property="fileSvSearchfromMonth" />
<html:hidden name="fil060Form" property="fileSvSearchfromDay" />
<html:hidden name="fil060Form" property="fileSvSearchtoYear" />
<html:hidden name="fil060Form" property="fileSvSearchtoMonth" />
<html:hidden name="fil060Form" property="fileSvSearchtoDay" />
<html:hidden name="fil060Form" property="fil100SvChkOnOff" />
<html:hidden name="fil060Form" property="fil100sortKey" />
<html:hidden name="fil060Form" property="fil100orderKey" />
<html:hidden name="fil060Form" property="fil100pageNum1" />
<html:hidden name="fil060Form" property="fil100pageNum2" />

<logic:notEmpty name="fil060Form" property="fil060SvAcFull">
<logic:iterate id="afid" name="fil060Form" property="fil060SvAcFull">
  <input type="hidden" name="fil060SvAcFull" value="<bean:write name="afid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil060Form" property="fil060SvAcRead">
<logic:iterate id="arid" name="fil060Form" property="fil060SvAcRead">
  <input type="hidden" name="fil060SvAcRead" value="<bean:write name="arid" />">
</logic:iterate>
</logic:notEmpty>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="fil060Form" property="fil060CmdMode" />">
<!-- BODY -->

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
    <logic:equal name="fil060Form" property="fil060CmdMode" value="0">
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="fil.39" /> ]</td>
    </logic:equal>

    <logic:equal name="fil060Form" property="fil060CmdMode" value="1">
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.edit.folder" /> ]</td>
    </logic:equal>

    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

    <logic:equal name="fil060Form" property="fil060CmdMode" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttonPush('fil060add');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil060delete');">
    </logic:equal>

    <logic:equal name="fil060Form" property="fil060CmdMode" value="0">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('fil060add');">
    </logic:equal>

    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil060back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.update.user" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
      <logic:notEmpty name="fil060Form" property="fil060groupList">
        <html:select property="fil060EditId" styleClass="select01">
          <html:optionsCollection name="fil060Form" property="fil060groupList" value="value" label="label" />
        </html:select>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.21" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
    <html:text property="fil060DirName" maxlength="70" style="width:599px;" />
    </td>
    </tr>

    <!-- ユーザ制限 -->
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.126" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table width="100%" border="0">
      <logic:equal name="fil060Form" property="fil060ParentAccessAllDspFlg" value="1">
      <tr>
      <td align="left">
      <span class="hide0"><input type="button" value="<gsmsg:write key="cmn.all" /><gsmsg:write key="api.cmn.view" />" class="btn_base1s_2" onClick="return parentAccessShowOrHide(1);"></span>
      <span class="show0"><input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_base1s_2" onClick="return parentAccessShowOrHide(0);"></span>
      </td>
      <td align="center">&nbsp;</td>
      <td align="center">&nbsp;</td>
      </tr>
      </logic:equal>
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      </tr>
      <tr>
      <td width="40%" align="left" valign="top" class="td_sub_detail">
      <logic:notEmpty name="fil060Form" property="fil060ParentEditList">
      <logic:iterate id="editMdl" name="fil060Form" property="fil060ParentEditList" indexId="idx" length="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="editMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="editMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      <div class="hide0">
      <logic:equal name="fil060Form" property="fil060ParentAccessAllDspFlg" value="1">
      <bean:size name="fil060Form" property="fil060ParentEditList" id="editSize" />
      <logic:greaterThan name="editSize" value="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <span class="text_base">…</span>
      </logic:greaterThan>
      </logic:equal>
      </div>
      <div class="show0">
      <logic:notEmpty name="fil060Form" property="fil060ParentEditList">
      <logic:iterate id="editMdl" name="fil060Form" property="fil060ParentEditList" indexId="idx" offset="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="editMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="editMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      </div>
      </td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left" valign="top" class="td_sub_detail">
      <logic:notEmpty name="fil060Form" property="fil060ParentReadList">
      <logic:iterate id="readMdl" name="fil060Form" property="fil060ParentReadList" indexId="idx" length="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="readMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="readMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      <div class="hide0">
      <logic:equal name="fil060Form" property="fil060ParentAccessAllDspFlg" value="1">
      <bean:size name="fil060Form" property="fil060ParentReadList" id="readSize" />
      <logic:greaterThan name="readSize" value="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <span class="text_base">…</span>
      </logic:greaterThan>
      </logic:equal>
      </div>
      <div class="show0">
      <logic:notEmpty name="fil060Form" property="fil060ParentReadList">
      <logic:iterate id="readMdl" name="fil060Form" property="fil060ParentReadList" indexId="idx" offset="<%= GSConstFile.MAXCOUNT_PARENT_ACCESS %>">
        <div class="text_base">
        <logic:equal name="readMdl" property="grpFlg" value="1">
          <span class="sc_link_g">G</span> </logic:equal>
          <bean:write name="readMdl" property="userName" />
        </div>
      </logic:iterate>
      </logic:notEmpty>
      </div>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <tr>
    <td class="td_sub_title" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.130" /><span class="text_r2">※</span></span>
    </td>
    <td align="left" class="td_sub_detail">
    <html:radio name="fil060Form" property="fil060AccessKbn" styleId="okini0" value="0" onclick="showOrHide();"/>
    <span class="text_base7"><label for="okini0"><gsmsg:write key="cmn.not.limit" /></label></span>&nbsp;
    <html:radio name="fil060Form" property="fil060AccessKbn" styleId="okini1" value="1" onclick="showOrHide();"/>
    <span class="text_base7"><label for="okini1"><gsmsg:write key="cmn.do.limit" /></label>&nbsp;</span>
    <logic:notEqual name="fil060Form" property="fil060CmdMode" value="<%= GSConstFile.CMN_MODE_ADD %>">
      <span class="text_base">
      <html:checkbox name="fil060Form" property="file060AdaptIncFile" value="1" styleId="adaptIncFile"/>
      <label for="adaptIncFile"><gsmsg:write key="fil.127" /></label></span>
    </logic:notEqual>
    </td>
    </tr>
    <tr id="hide0"></tr>
    <tr id="show0">
    <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
    <span class="text_bb1"><gsmsg:write key="fil.102" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table width="100%" border="0">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" align="left">
      <html:select name="fil060Form" property="fil060AcEditSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil060Form" property="fil060AcEditGroupLavel">
      <html:optionsCollection name="fil060Form" property="fil060AcEditGroupLavel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      <span class="text_base">
      <input type="checkbox" name="fil060AcEditAllSlt" value="1" id="select_edit_user" onclick="return selectAccessEditList();" />
      <label for="select_edit_user"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center" valign="top">
      <html:select name="fil060Form" property="fil060AcFull" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil060Form" property="fil060AcFullLavel">
      <html:optionsCollection name="fil060Form" property="fil060AcFullLavel" value="value" label="label" />
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </logic:notEmpty>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil060fulladd');">
      <br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil060fulldel');">
      </td>

      <td valign="top">
      <html:select name="fil060Form" property="fil060AcEditRight" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil060Form" property="fil060AcEditRightLavel">
      <html:optionsCollection name="fil060Form" property="fil060AcEditRightLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left">
      <html:select name="fil060Form" property="fil060AcReadSltGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
      <logic:notEmpty name="fil060Form" property="fil060AcReadGroupLavel">
      <html:optionsCollection name="fil060Form" property="fil060AcReadGroupLavel" value="value" label="label" />
      </logic:notEmpty>
      </html:select>
      <span class="text_base">
      <input type="checkbox" name="fil060AcReadAllSlt" value="1" id="select_read_user" onclick="return selectAccessReadList();" />
      <label for="select_read_user"><gsmsg:write key="cmn.select" /></label></span>
      </td>
      </tr>

      <tr>
      <td align="center" valign="top">
      <html:select name="fil060Form" property="fil060AcRead" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil060Form" property="fil060AcReadLavel">
      <html:optionsCollection name="fil060Form" property="fil060AcReadLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>

      <td align="center">
      <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil060readadd');"><br><br>
      <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil060readdel');">
      </td>
      <td valign="top">
      <html:select name="fil060Form" property="fil060AcReadRight" size="13" multiple="true" styleClass="select01" style="width:220px;">
      <logic:notEmpty name="fil060Form" property="fil060AcReadRightLavel">
      <html:optionsCollection name="fil060Form" property="fil060AcReadRightLavel" value="value" label="label" />
      </logic:notEmpty>
      <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
      </html:select>
      </td>
      </tr>

      </table>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <textarea name="fil060Biko" style="width:599px;" rows="5" styleClass="text_gray" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="fil060Form" property="fil060Biko" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>
<%--
    <tr>
    <td class="td_sub_title" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td class="td_sub_detail" align="left" width="80%">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      <td width="0%" align="left" valign="top" nowrap>
        <html:select property="fil060TempFiles" size="10" styleClass="select01" multiple="true">
        <html:optionsCollection name="fil060Form" property="fil060FileLabelList" value="value" label="label" />
        </html:select>
      </td>
      <td width="0%" align="left" valign="top" nowrap><img src="../common/images/spacer.gif" width="10px" height="1px" border="0"></td>
      <td width="100%" align="left" valign="middle">
        <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('fil060TempFiles', '<bean:write name="fil060Form" property="fil060PluginId" />', '0', '3');">
        <br><br><input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('deleteTemp');">
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
    <logic:equal name="fil060Form" property="fil060CmdMode" value="1">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.edit" />" onclick="buttonPush('fil060add');">
    <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onclick="buttonPush('fil060delete');">
    </logic:equal>

    <logic:equal name="fil060Form" property="fil060CmdMode" value="0">
    <input type="button" class="btn_edit_n1" value="<gsmsg:write key="cmn.add" />" onclick="buttonPush('fil060add');">
    </logic:equal>

    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil060back');">
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