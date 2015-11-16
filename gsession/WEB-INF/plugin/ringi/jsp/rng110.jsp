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
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../ringi/js/rng110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.62" /> <gsmsg:write key="rng.rng110.01" /></title>
</head>

<body class="body_03">

<html:form action="ringi/rng110">

<input type="hidden" name="helpPrm" value="<bean:write name="rng110Form" property="rngRctCmdMode" />">

<input type="hidden" name="CMD" value="entry">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />
<html:hidden property="rctSid" />
<html:hidden property="rngRctCmdMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />
<html:hidden property="rng110UserSid" />

<logic:notEmpty name="rng110Form" property="rng110apprUser">
<logic:iterate id="apprUser" name="rng110Form" property="rng110apprUser">
  <input type="hidden" name="rng110apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng110Form" property="rng110confirmUser">
<logic:iterate id="confirmUser" name="rng110Form" property="rng110confirmUser">
  <input type="hidden" name="rng110confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

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
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.rng110.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <logic:equal name="rng110Form" property="rngRctCmdMode" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT) %>">
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
          </logic:equal>
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('entry');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backList');"></td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
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
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rng.rng110.02" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <html:text name="rng110Form" property="rng110name" style="width:273px;" maxlength="20" />
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
              <html:select property="rng110selectApprUser" size="10" styleClass="select02" multiple="true">
                <html:optionsCollection name="rng110Form" property="rng110apprUserList" value="value" label="label" />
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
              <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.rng110group, 'rng110group', '<bean:write name="rng110Form" property="rng110group" />', '0', 'init', 'rng110apprUser', 'rng110confirmUser', 'ringi', '<bean:write name="rng110Form" property="rng110UserSid" />', '0')" type="button"><br>
              <html:select property="rng110group" styleClass="select01" onchange="changeGroupCombo();">
                <html:optionsCollection name="rng110Form" property="rng110groupList" value="value" label="label" />
              </html:select>
            </td>
            <td valign="bottom">
              <input type="button" onclick="openGroupWindow(this.form.rng110group, 'rng110group', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="rng110GroupBtn">
            </td>
          </tr>

          <tr>
            <td>
              <html:select property="rng110users" size="17" styleClass="select01" multiple="true">
                <html:optionsCollection name="rng110Form" property="rng110userList" value="value" label="label" />
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
          <html:select property="rng110selectConfirmUser" size="5" styleClass="select01" multiple="true">
            <html:optionsCollection name="rng110Form" property="rng110confirmUserList" value="value" label="label" />
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
      <logic:equal name="rng110Form" property="rngRctCmdMode" value="<%= String.valueOf(jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
      </logic:equal>
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('entry');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backList');"></td>
    </tr>
    </table>

  </td>
  </tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>