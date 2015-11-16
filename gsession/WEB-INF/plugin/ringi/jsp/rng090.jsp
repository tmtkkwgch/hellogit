<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% int tmodeShare = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_SHARE; %>
<% int tmodePrivate = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_PRIVATE; %>

<% int tCmdAdd = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_ADD; %>
<% int tCmdEdit = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT; %>

<% String maxLengthFormat = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_FORMAT); %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.57" /></title>
</head>

<body class="body_03" onunload="windowClose();" onload="showLengthId($('#inputstr')[0], <%= maxLengthFormat %>, 'inputlength');">

<html:form action="ringi/rng090">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rngTplCmdMode" />
<html:hidden property="rngSelectTplSid" />

<html:hidden property="rng060SelectCat" />
<html:hidden property="rng060SelectCatUsr" />

<html:hidden property="rng060SelectCat" />
<html:hidden property="rng060SelectCatUsr" />
<html:hidden property="rng090UserSid" />

<logic:notEmpty name="rng090Form" property="rng090apprUser">
<logic:iterate id="apprUser" name="rng090Form" property="rng090apprUser">
  <input type="hidden" name="rng090apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng090Form" property="rng090confirmUser">
<logic:iterate id="confirmUser" name="rng090Form" property="rng090confirmUser">
  <input type="hidden" name="rng090confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<bean:write name="rng090Form" property="rngTemplateMode" />">
<input type="hidden" name="helpPrm" value="<bean:write name="rng090Form" property="rngTplCmdMode" />">

<!-- body -->
<bean:define id="rngTemplateMode" name="rng090Form" property="rngTemplateMode" />
<% int rtMode = ((Integer) rngTemplateMode).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" class="tl0" align="center">
<tr>
<td>

  <table width="70%" class="tl0" align="center">
  <tr>
  <td width="100%" align="left">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
<% if (rtMode == tmodeShare) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.entry.shared.template" /> ]</td>
<% } else if (rtMode == tmodePrivate) { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.entry.personal.template" /> ]</td>
<% } else { %>
        <td width="0%"><img src="../ringi/images/header_ringi_02.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="rng.57" /> ]</td>
<% } %>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%" nowrap>
          <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('ok090')">
<logic:equal name="rng090Form" property="rngTplCmdMode" value="<%= String.valueOf(tCmdEdit) %>">
          <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('cmn999del')">
</logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng060')">
        </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

<logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="left"><html:errors/></td>
      </tr>
    </table>
</logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td><img src="../common/images/damy.gif" width="1" height="5" alt=" "></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td colspan="4" align="left" class="td_wt" width="100%">
      <html:select name="rng090Form" property="rng090CatSid">
        <html:optionsCollection name="rng090Form" property="rng090CategoryList" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="rng.10" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td colspan="4" align="left" class="td_wt" width="100%"><html:text name="rng090Form" property="rng090title" style="width:393px;" maxlength="100" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td colspan="4" align="left" class="td_wt" width="100%"><html:text name="rng090Form" property="rng090rngTitle" style="width:393px;" maxlength="100" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="rng.12" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td colspan="4" align="left" class="td_wt">
      <textarea name="rng090content" style="width:541px;" rows="20" onkeyup="showLengthStr(value, <%= maxLengthFormat %>, 'inputlength');" id="inputstr"><bean:write name="rng090Form" property="rng090content" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthFormat %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

<!-- 添付 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td valign="middle" align="left" class="td_wt">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delTempfile');">
        &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('rng090files', 'ringitemplate', '0', '0');">
        <br>
        <html:select property="rng090files" styleClass="select01" multiple="true">
          <html:optionsCollection name="rng090Form" property="rng090FileLabelList" value="value" label="label" />
        </html:select>
    </td>
    </tr>

<!-- 経路 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.24" /></span></td>
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

            <td valign="top" class="table_bg_A5B4E1" height="100%">
              <table height="100%">
              <tr>
                <td height="50%" valign="top"><a href="javascript:void(0)" onClick="return buttonPush('upAppr');"><img src="../common/images/arrow2_t.gif" alt="<gsmsg:write key="cmn.up" />" border="0"></a></td>
              </tr>
              <tr>
                <td height="50%" valign="bottom"><a href="javascript:void(0)" onClick="return buttonPush('downAppr');"><img src="../common/images/arrow2_b.gif" alt="<gsmsg:write key="cmn.down" />" border="0"></a></td>
              </tr>
              </table>
            </td>

            <td width="100%" rowspan="2" align="left">
              <html:select property="rng090selectApprUser" size="10" styleClass="select02" multiple="true">
                <logic:notEmpty name="rng090Form" property="rng090apprUserList">
                <html:optionsCollection name="rng090Form" property="rng090apprUserList" value="value" label="label" />
                </logic:notEmpty>
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
              <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.rng090group, 'rng090group', '<bean:write name="rng090Form" property="rng090group" />', '0', 'init', 'rng090apprUser', 'rng090confirmUser', 'ringi', '<bean:write name="rng090Form" property="rng090UserSid" />', '0')" type="button"><br>
              <html:select property="rng090group" styleClass="select01" onchange="buttonPush('init');">
                <logic:notEmpty name="rng090Form" property="rng090groupList">
                <html:optionsCollection name="rng090Form" property="rng090groupList" value="value" label="label" />
                </logic:notEmpty>
              </html:select>
            </td>
            <td valign="bottom">
              <input type="button" onclick="openGroupWindow(this.form.rng090group, 'rng090group', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="rng090GroupBtn">
            </td>
          </tr>

          <tr>
            <td>
              <html:select property="rng090users" size="17" styleClass="select01" multiple="true">
                <logic:notEmpty name="rng090Form" property="rng090userList">
                <html:optionsCollection name="rng090Form" property="rng090userList" value="value" label="label" />
                </logic:notEmpty>
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
          <html:select property="rng090selectConfirmUser" size="5" styleClass="select01" multiple="true">
            <logic:notEmpty name="rng090Form" property="rng090confirmUserList">
            <html:optionsCollection name="rng090Form" property="rng090confirmUserList" value="value" label="label" />
            </logic:notEmpty>
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

    <tr>
      <td colspan="2" width="100%" align="left"><img src="../common/images/damy.gif" width="1" height="5" alt=" "></td>
    </tr>

    </table>

    <table width="100%" class="tl0" cellspacing="3">
    <tr>
    <td width="100%" align="right" nowrap>
          <input type="button" class="btn_ok1" value="OK" onClick="buttonPush('ok090')">
<logic:equal name="rng090Form" property="rngTplCmdMode" value="<%= String.valueOf(tCmdEdit) %>">
          <input type="button" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('cmn999del')">
</logic:equal>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng060')">
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