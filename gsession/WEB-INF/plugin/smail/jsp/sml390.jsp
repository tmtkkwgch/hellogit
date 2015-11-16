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
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession]
<logic:equal name="sml390Form" property="sml380EditBan" value="0"><gsmsg:write key="sml.sml390.01" /></logic:equal>
<logic:notEqual name="sml390Form" property="sml380EditBan" value="0"><gsmsg:write key="sml.sml390.02" /></logic:notEqual>
</title>
</head>

<body class="body_03">
<html:form action="/smail/sml390">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="sml380EditBan" />
<logic:notEmpty name="sml390Form" property="sml390sbpTarget" scope="request">
  <logic:iterate id="sid" name="sml390Form" property="sml390sbpTarget" scope="request">
    <input type="hidden" name="sml390sbpTarget" value="<bean:write name="sid"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sml390Form" property="sml390sbdTarget" scope="request">
  <logic:iterate id="sid" name="sml390Form" property="sml390sbdTarget" scope="request">
    <input type="hidden" name="sml390sbdTarget" value="<bean:write name="sid"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sml390Form" property="sml390sbdTargetAcc" scope="request">
  <logic:iterate id="sid" name="sml390Form" property="sml390sbdTargetAcc" scope="request">
    <input type="hidden" name="sml390sbdTargetAcc" value="<bean:write name="sid"/>">
  </logic:iterate>
</logic:notEmpty>
<html:hidden property="sml390initFlg" />

<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />


<logic:notEmpty name="sml390Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml390Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml380keyword" />
<html:hidden property="sml380svKeyword" />
<html:hidden property="sml380sortKey" />
<html:hidden property="sml380order" />
<html:hidden property="sml380searchFlg" />
<html:hidden property="sml380pageTop" />

<logic:equal name="sml390Form" property="sml380EditBan" value="0">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>
<logic:notEqual name="sml390Form" property="sml380EditBan" value="0">
  <input type="hidden" name="helpPrm" value="1">
</logic:notEqual>


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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">
        <logic:equal name="sml390Form" property="sml380EditBan" value="0">
            [ <gsmsg:write key="sml.sml390.01" /> ]
        </logic:equal>
        <logic:notEqual name="sml390Form" property="sml380EditBan" value="0">
            [ <gsmsg:write key="sml.sml390.02" /> ]
        </logic:notEqual>

        </td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="submit" value="OK" class="btn_ok1" onClick="buttonPush('ok');">
          <logic:notEqual name="sml390Form" property="sml380EditBan" value="0">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
          </logic:notEqual>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>


    <img src="../common/images/spacer.gif" alt="<gsmsg:write key="cmn.spacer" />" height="10px" width="10px">
    <html:errors />
    <img src="../common/images/spacer.gif" alt="<gsmsg:write key="cmn.spacer" />" height="10px" width="10px">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml380.03" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" width="90%" class="td_wt">
      <html:text styleClass="text_base" style="width:335px;" maxlength="50" name="sml390Form" property="sml390sbcName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml380.04" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">
         <table width="100%" border="0">
         <tr>
         <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="sml.sml390.05" /></span></td>
         <td width="20%" align="center" style="border: 0px;">&nbsp;</td>
         <td width="40%" align="left" style="border: 0px;">
         <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.sml390banGroup, 'sml390banGroup', '<bean:write name="sml390Form" property="sml390banGroup" />', '0', 'changeGrp', 'sml390sbdTarget', '-1', '1')" type="button"><br>
         <html:select name="sml390Form" property="sml390banGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
         <logic:notEmpty name="sml390Form" property="groupCombo">
         <html:optionsCollection name="sml390Form" property="groupCombo" value="value" label="label" />
         </logic:notEmpty>
         </html:select>
         <input type="button" onclick="openGroupWindow(this.form.sml390banGroup, 'sml390banGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="sml390banGroupBtn">
         </td>
         </tr>
         <tr>
         <td align="center">
         <html:select name="sml390Form" property="sml390sbdTargetSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
         <logic:notEmpty name="sml390Form" property="sml390sbdTargetSelectCombo">
         <html:optionsCollection name="sml390Form" property="sml390sbdTargetSelectCombo" value="value" label="label" />
         </logic:notEmpty>
         <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
         </html:select>
         </td>
         <td align="center" style="border: 0px;">
         <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addBanUser');">
         <br><br>
         <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteBanUser');">
         </td>
         <td valign="top"  style="border: 0px;">
         <html:select name="sml390Form" property="sml390sbdTargetNoSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
         <logic:notEmpty name="sml390Form" property="sml390sbdTargetNoSelectCombo">
         <html:optionsCollection name="sml390Form" property="sml390sbdTargetNoSelectCombo" value="value" label="label" />
         </logic:notEmpty>
         <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
         </html:select>
         </td>
         </tr>

         <tr>
         <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="sml.189" /></span></td>
         <td width="20%" align="center" style="border: 0px;">&nbsp;</td>

         <td width="40%" align="left" style="border: 0px;">
         </td>
         </tr>
         <tr>
         <td align="center">
         <html:select name="sml390Form" property="sml390sbdTargetAccSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
         <logic:notEmpty name="sml390Form" property="sml390sbdTargetAccSelectCombo">
         <html:optionsCollection name="sml390Form" property="sml390sbdTargetAccSelectCombo" value="value" label="label" />
         </logic:notEmpty>
         <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
         </html:select>
         </td>
         <td align="center" style="border: 0px;">
         <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addBanAcc');">
         <br><br>
         <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteBanAcc');">
         </td>
         <td valign="top"  style="border: 0px;">
         <html:select name="sml390Form" property="sml390sbdTargetAccNoSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
         <logic:notEmpty name="sml390Form" property="sml390sbdTargetAccNoSelectCombo">
         <html:optionsCollection name="sml390Form" property="sml390sbdTargetAccNoSelectCombo" value="value" label="label" />
         </logic:notEmpty>
         <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
         </html:select>
         </td>
         </tr>

         </table>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="sml.sml390.03" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt">
         <gsmsg:write key="cmn.post" />:
         <html:select name="sml390Form" property="sml390post" styleClass="select01"  style="width: 150px;">
         <logic:notEmpty name="sml390Form" property="postCombo">
         <html:optionsCollection name="sml390Form" property="postCombo" value="value" label="label" />
         </logic:notEmpty>
         </html:select>
         <p>
         <table width="100%" border="0">
         <tr>
         <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="sml.sml390.05" /></span></td>
         <td width="20%" align="center" style="border: 0px;">&nbsp;</td>

         <td width="40%" align="left" style="border: 0px;">
         <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.sml390ableGroup, 'sml390ableGroup', '<bean:write name="sml390Form" property="sml390ableGroup" />', '0', 'changeGrp', 'sml390sbpTarget', '-1', '1')" type="button"><br>
         <html:select name="sml390Form" property="sml390ableGroup" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 150px;">
         <logic:notEmpty name="sml390Form" property="ableUserGroupCombo">
         <html:optionsCollection name="sml390Form" property="ableUserGroupCombo" value="value" label="label" />
         </logic:notEmpty>
         </html:select>
         <input type="button" onclick="openGroupWindow(this.form.sml390ableGroup, 'sml390ableGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="sml390GroupBtn">
         </td>
         </tr>

         <tr>
         <td align="center">
         <html:select name="sml390Form" property="sml390sbpTargetSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
         <logic:notEmpty name="sml390Form" property="sml390sbpTargetSelectCombo">
         <html:optionsCollection name="sml390Form" property="sml390sbpTargetSelectCombo" value="value" label="label" />
         </logic:notEmpty>
         <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
         </html:select>
         </td>

         <td align="center" style="border: 0px;">
         <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addUser');">
         <br><br>
         <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('deleteUser');">
         </td>

         <td valign="top"  style="border: 0px;">
         <html:select name="sml390Form" property="sml390sbpTargetNoSelect" size="10" multiple="true" styleClass="select01" style="width:220px;">
         <logic:notEmpty name="sml390Form" property="sml390sbpTargetNoSelectCombo">
         <html:optionsCollection name="sml390Form" property="sml390sbpTargetNoSelectCombo" value="value" label="label" />
         </logic:notEmpty>
         <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
         </html:select>
         </td>
         </tr>

         </table>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
    <td align="left" class="td_wt">
        <html:textarea name="sml390Form" property="sml390biko"  rows="10" style="width:572px;" />
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
          <input type="submit" value="OK" class="btn_ok1" onClick="buttonPush('ok');">
          <logic:notEqual name="sml390Form" property="sml380EditBan" value="0">
            <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
          </logic:notEqual>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back');">
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