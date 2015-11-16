<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% String maxLengthContent = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_CONTENT); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../ringi/js/rng020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.rng020.04" /></title>
</head>

<body class="body_03" onunload="windowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthContent %>, 'inputlength');scroll();">

<html:form action="ringi/rng020">
<input type="hidden" name="CMD" value="">

<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rngKeyword" />
<html:hidden property="rng130Type" />
<html:hidden property="sltGroupSid" />
<html:hidden property="sltUserSid" />
<html:hidden property="rng130keyKbn" />
<html:hidden property="rng130searchSubject1" />
<html:hidden property="rng130searchSubject2" />
<html:hidden property="sltSortKey1" />
<html:hidden property="rng130orderKey1" />
<html:hidden property="sltSortKey2" />
<html:hidden property="rng130orderKey2" />
<html:hidden property="sltApplYearFr" />
<html:hidden property="sltApplMonthFr" />
<html:hidden property="sltApplDayFr" />
<html:hidden property="sltApplYearTo" />
<html:hidden property="sltApplMonthTo" />
<html:hidden property="sltApplDayTo" />
<html:hidden property="sltLastManageYearFr" />
<html:hidden property="sltLastManageMonthFr" />
<html:hidden property="sltLastManageDayFr" />
<html:hidden property="sltLastManageYearTo" />
<html:hidden property="sltLastManageMonthTo" />
<html:hidden property="sltLastManageDayTo" />
<html:hidden property="rng130pageTop" />
<html:hidden property="rng130pageBottom" />

<html:hidden property="svRngKeyword" />
<html:hidden property="svRng130Type" />
<html:hidden property="svGroupSid" />
<html:hidden property="svUserSid" />
<html:hidden property="svRng130keyKbn" />
<html:hidden property="svRng130searchSubject1" />
<html:hidden property="svRng130searchSubject2" />
<html:hidden property="svSortKey1" />
<html:hidden property="svRng130orderKey1" />
<html:hidden property="svSortKey2" />
<html:hidden property="svRng130orderKey2" />
<html:hidden property="svApplYearFr" />
<html:hidden property="svApplMonthFr" />
<html:hidden property="svApplDayFr" />
<html:hidden property="svApplYearTo" />
<html:hidden property="svApplMonthTo" />
<html:hidden property="svApplDayTo" />
<html:hidden property="svLastManageYearFr" />
<html:hidden property="svLastManageMonthFr" />
<html:hidden property="svLastManageDayFr" />
<html:hidden property="svLastManageYearTo" />
<html:hidden property="svLastManageMonthTo" />
<html:hidden property="svLastManageDayTo" />

<html:hidden property="rng130searchFlg" />

<logic:notEmpty name="rng020Form" property="rng020apprUser">
<logic:iterate id="apprUser" name="rng020Form" property="rng020apprUser">
  <input type="hidden" name="rng020apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng020Form" property="rng020confirmUser">
<logic:iterate id="confirmUser" name="rng020Form" property="rng020confirmUser">
  <input type="hidden" name="rng020confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="rng020copyApply" />
<html:hidden property="rng020ScrollFlg" />
<input type="hidden" name="rng020TemplateFileId" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0" border="0">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rng.46" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="rng.63" />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.save.draft" />" class="btn_base1" onClick="buttonPush('draft')">
          <input type="button" value="<gsmsg:write key="rng.46" />" class="btn_base1" onClick="buttonPush('approval')">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng010')"></td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="rng.63" />"></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr><td align="left"><html:errors/><br></td></tr>
    </table>
    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <html:text name="rng020Form" property="rng020Title" style="width:633px;" maxlength="100" />
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.47" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><bean:write name="rng020Form" property="rng020requestUser" /></span>
    </td>
    </tr>

  <logic:equal name="rng020Form" property="rngCmdMode" value="1">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.37" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_base"><bean:write name="rng020Form" property="rng020createDate" /></span>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <input type="button" class="btn_base1w" value="<gsmsg:write key="cmn.use.template" />" onClick="template(<%= tmodeAll %>, 'rng060')"><br>
      <% String onKeyUp = "showLengthStr(value, " + String.valueOf(maxLengthContent) + ", 'inputlength');"; %>
      <html:textarea name="rng020Form" property="rng020Content" styleClass="text_base" style="width:512px;" rows="10" onkeyup="<%= onKeyUp %>" styleId="inputstr" />
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthContent %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td valign="middle" align="left" class="td_wt">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delTemp');">
      &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('rng020files', 'ringi', '0', '0');">
      <br>
      <html:select property="rng020files" styleClass="select01" size="5" multiple="true">
        <html:optionsCollection name="rng020Form" property="rng020fileList" value="value" label="label" />
      </html:select>

      <logic:notEmpty name="rng020Form" property="rng020templateFileList">
        <table cellpadding="0" cellpadding="0" border="0">
        <logic:iterate id="templateFileData" name="rng020Form" property="rng020templateFileList">
          <tr>
          <td width="0"><img src="../common/images/file_icon.gif" alt="ファイル"></td>
          <td class="menu_bun">
          <a href="javascript:void(0);" onClick="return templateFileLinkClick('<bean:write name="templateFileData" property="value" />');">
          <span class="text_link"><bean:write name="templateFileData" property="label" /></span>
          </a>
          </td>
          </tr>
        </logic:iterate>
        </table>
      </logic:notEmpty>
    <a id="add_user" name="add_user"></a>
    </td>
    </tr>

    <tr>
    <td width="0%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="rng.25" /></span></td>
    <td valign="middle" align="left" class="td_wt">
      <table width="100%" border="0" cellpadding="0">
      <tr>
      <td align="left" width="50%">
      <html:select property="rng020rctSid" styleClass="select01">
        <html:optionsCollection name="rng020Form" property="rng020rctList" value="value" label="label" />
      </html:select>
      </td>
      <td align="left" width="50%">
      <input type="button" value="<gsmsg:write key="rng.rng020.02" />" class="btn_base1" onClick="buttonPush('setChannel');">
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.24" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td valign="middle" align="left" class="td_wt">


      <table width="0%" border="0">
      <tr>
        <td width="30%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="rng.42" /></span></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="30%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.userlist" /></span></td>
        <td width="20%" align="left">&nbsp;</td>
      </tr>


      <tr>
        <td align="center" valign="top">

          <table cellspacing="0" class="tl0" height="100%">
          <tr>

            <td width="1%"  valign="top" class="table_bg_A5B4E1" height="99%">
              <table height="99%">
              <tr>
                <td height="5%" valign="top"><a href="javascript:void(0)" onClick="return buttonPush('upAppr');"><img src="../common/images/arrow2_t.gif" alt="<gsmsg:write key="cmn.up" />" border="0"></a></td>
              </tr>
              <tr><td height="90%">&nbsp;</td></tr>
              <tr>
                <td height="5%" valign="bottom"><a href="javascript:void(0)" onClick="return buttonPush('downAppr');"><img src="../common/images/arrow2_b.gif" alt="<gsmsg:write key="cmn.down" />" border="0" style="vertical-align: bottom;"></a></td>
              </tr>
              </table>
            </td>

            <td width="99%" rowspan="2" align="left">
              <html:select property="rng020selectApprUser" size="10" styleClass="select02" multiple="true">
                <html:optionsCollection name="rng020Form" property="rng020apprUserList" value="value" label="label" />
              </html:select>
            </td>

          </tr>
          </table>
        </td>
        <td align="center">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addAppr');"><br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('delAppr');"><br>
        </td>
        <td valign="top"  rowspan="3">
          <table width="100%" cellpadding="0" cellspacing="0">
          <tr>
            <td width="40%">
              <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.rng020group, 'rng020group', '<bean:write name="rng020Form" property="rng020group" />', '0', 'changeGroup', 'rng020apprUser', 'rng020confirmUser', 'ringi', '<bean:write name="rng020Form" property="rng020requestUserId" />', '0')" type="button"><br>
              <html:select property="rng020group" styleClass="select01" onchange="changeGroupCombo2();">
                <html:optionsCollection name="rng020Form" property="rng020groupList" value="value" label="label" />
              </html:select>
            </td>
            <td valign="bottom">
              <input type="button" onclick="openGroupWindow(this.form.rng020group, 'rng020group', '0', 'changeGroup')" class="group_btn2" value="&nbsp;&nbsp;" id="rng020GroupBtn">
            </td>
          </tr>

          <tr>
            <td>
              <html:select property="rng020users" size="17" styleClass="select01" multiple="true">
                <html:optionsCollection name="rng020Form" property="rng020userList" value="value" label="label" />
              </html:select>
            </td>
          </tr>
          </table>

        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="rng.35" /></span></td>
        <td rowspan="1">&nbsp;</td>
      </tr>

      <tr>
        <td align="left">
          <html:select property="rng020selectConfirmUser" size="5" styleClass="select01" multiple="true">
            <html:optionsCollection name="rng020Form" property="rng020confirmUserList" value="value" label="label" />
          </html:select>
        </td>
        <td align="center">
          <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addConfirm');"><br><br>
          <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('delConfirm');"><br>
        </td>
      </tr>

      </table>

    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.save.draft" />" class="btn_base1" onClick="buttonPush('draft')">
      <input type="button" value="<gsmsg:write key="rng.46" />" class="btn_base1" onClick="buttonPush('approval')">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng010')"></td>
    </tr>
    </table>

  </td>
  </tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>