<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="address.adr070.1" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr070.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="viewchange();editchange();">

<html:form action="/address/adr070">

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr070Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr070Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr070Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr070Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr070Form" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr070Form" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr070Form" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr070permitViewGroup">
<logic:iterate id="permitViewGroup" name="adr070Form" property="adr070permitViewGroup">
  <input type="hidden" name="adr070permitViewGroup" value="<bean:write name="permitViewGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr070permitViewUser">
<logic:iterate id="permitViewUser" name="adr070Form" property="adr070permitViewUser">
  <input type="hidden" name="adr070permitViewUser" value="<bean:write name="permitViewUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr070permitEditGroup">
<logic:iterate id="permitEditGroup" name="adr070Form" property="adr070permitEditGroup">
  <input type="hidden" name="adr070permitEditGroup" value="<bean:write name="permitEditGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr070permitEditUser">
<logic:iterate id="permitEditUser" name="adr070Form" property="adr070permitEditUser">
  <input type="hidden" name="adr070permitEditUser" value="<bean:write name="permitEditUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr070tantoList">
<logic:iterate id="tanto" name="adr070Form" property="adr070tantoList">
  <input type="hidden" name="adr070tantoList" value="<bean:write name="tanto" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="projectCmbList">
<logic:iterate id="project" name="adr070Form" property="projectCmbList">
  <input type="hidden" name="projectCmbList" value="<bean:write name="project" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr070Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="adr070Form" property="adr070cmdMode" />">

<html:hidden property="adr070cmdMode" />

<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr070.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('importAddressConfirm');">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAddressList');">
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

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>

        <td width="100%" nowrap>
        <logic:equal name="adr070Form" property="adr070cmdMode" value="0">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_3_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:buttonPush('douji');"><gsmsg:write key="address.adr070.5" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_2_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:buttonPush('tujou');"><gsmsg:write key="address.adr070.4" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
        </logic:equal>

        <logic:equal name="adr070Form" property="adr070cmdMode" value="1">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_3_on"  nowrap>
            <div class="tab_text_area">
                <a href="javascript:buttonPush('douji');"><gsmsg:write key="address.adr070.5" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_2_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:buttonPush('tujou');"><gsmsg:write key="address.adr070.4" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
        </logic:equal>
        </td>

        <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
    </table>




    <!-- <gsmsg:write key="cmn.capture.file" /> -->
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('adr070file', '<%= jp.groupsession.v2.adr.GSConstAddress.PLUGIN_ID_ADDRESS %>', '1', '0');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('deleteFile');"><br>
      <html:select property="adr070file" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="adr070Form" property="adr070fileCombo" value="value" label="label" />
      </html:select>
      <br>
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% String csvFileMsg = ""; %>
      <logic:equal name="adr070Form" property="adr070cmdMode" value="0">
      <% csvFileMsg = gsMsg.getMessage(request, "address.adr070.2"); %>
      </logic:equal>
      <logic:equal name="adr070Form" property="adr070cmdMode" value="1">
      <% csvFileMsg = gsMsg.getMessage(request, "address.adr070.2.1"); %>
      </logic:equal>
      <span class="text_base">*<gsmsg:write key="cmn.plz.specify" arg0="<%= csvFileMsg %>" /></span>
    </td>
    </tr>


    <logic:notEqual name="adr070Form" property="adr070cmdMode" value="1">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.select.company" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table border="0">
      <tr>
      <td class="text_base"><gsmsg:write key="address.139" />&nbsp;:&nbsp;</td>
      <td>
      <html:select name="adr070Form" property="adr070selectCompany" styleClass="select01" onchange="buttonPush('init');">
        <html:optionsCollection name="adr070Form" property="adr070CompanyCombo" value="value" label="label" />
      </html:select>
      </td>
      </tr>
      <tr>
      <td class="text_base"><gsmsg:write key="address.10" />&nbsp;:&nbsp;</td>
      <td>
      <html:select name="adr070Form" property="adr070selectCompanyBase" styleClass="select01" onchange="buttonPush('init');">
        <html:optionsCollection name="adr070Form" property="adr070CompanyBaseCombo" value="value" label="label" />
      </html:select>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:notEqual>

    <!-- 上書き -->
    <logic:equal name="adr070Form" property="adr070cmdMode" value="1">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <html:checkbox name="adr070Form" property="adr070updateFlg" value="1" styleId="updateFlg" /><label for="updateFlg" class="text_base"><gsmsg:write key="address.109" /></label>
    </td>
    </tr>
    </logic:equal>


    <!-- <gsmsg:write key="cmn.staff" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.46" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">

      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.adr070.3" /></span></td>
      <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
      <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.user" /></span></td>
      </tr>

      <tr>
      <td align="center" class="td_body01" valign="top">
        <html:select name="adr070Form" property="adr070selectTantoList" size="10" styleClass="select01" multiple="true">
        <logic:notEmpty name="adr070Form" property="selectTantoCombo">
          <html:optionsCollection name="adr070Form" property="selectTantoCombo" value="value" label="label" />
        </logic:notEmpty>
        <option>&nbsp;</option>
        </html:select>
      </td>
      <td valign="middle" align="center">
        <a href="#" onClick="buttonPush('addTanto');"><img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif"></a>
        <br><br><br>
        <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteTanto');" tabindex="44">
      </td>
      <td align="center" class="td_body01" valign="top">
        <table>
        <tr>
        <td>
        <input class="btn_group_n1" onclick="return openAllGroup(this.form.adr070tantoGroup, 'adr070tantoGroup', '-1', '0', 'init', 'adr070tantoList', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
        <html:select name="adr070Form" property="adr070tantoGroup" styleClass="select01" onchange="buttonPush('init');">
          <html:optionsCollection name="adr070Form" property="groupCmbList" value="value" label="label" />
        </html:select>
        </td>
        <td valign="bottom">
          <input type="button" onclick="openGroupWindow(this.form.adr070tantoGroup, 'adr070tantoGroup', '0')" class="group_btn2" value="&nbsp;&nbsp;" id="adr070GroupBtn">
        </td>
        </tr>
        <tr>
        <td>
        <html:select name="adr070Form" property="adr070NoSelectTantoList" size="10" styleClass="select01" multiple="true">
        <logic:notEmpty name="adr070Form" property="noSelectTantoCombo">
          <html:optionsCollection name="adr070Form" property="noSelectTantoCombo" value="value" label="label" />
        </logic:notEmpty>
        <option value="-1">&nbsp;</option>
        </html:select>
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <!-- <gsmsg:write key="address.61" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.61" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio property="adr070permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_OWN) %>" styleId="view0" onclick="viewchange();" /><label for="view0"><span class="text_base"><gsmsg:write key="address.62" /></span></label>
      <html:radio property="adr070permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_GROUP) %>" styleId="view1" onclick="viewchange();" /><label for="view1"><span class="text_base"><gsmsg:write key="group.designation" /></span></label>
      <html:radio property="adr070permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_USER) %>" styleId="view2" onclick="viewchange();" /><label for="view2"><span class="text_base"><gsmsg:write key="cmn.user.specified" /></span></label>
      <html:radio property="adr070permitView" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_NORESTRICTION) %>" styleId="view3" onclick="viewchange();" /><label for="view3"><span class="text_base"><gsmsg:write key="cmn.no.setting.permission" /></span></label>

      <div id="viewgroup" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.66" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.group" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr070Form" property="adr070selectPermitViewGroup" size="10" styleClass="select01" multiple="true">
          <logic:notEmpty name="adr070Form" property="selectPermitViewGroup">
            <html:optionsCollection name="adr070Form" property="selectPermitViewGroup" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addViewGroup');">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteViewGroup');">
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr070Form" property="adr070NoSelectPermitViewGroup" size="10" styleClass="select01" multiple="true">
          <logic:notEmpty name="adr070Form" property="noSelectPermitViewGroup">
            <html:optionsCollection name="adr070Form" property="noSelectPermitViewGroup" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        </tr>
        </table>
      </div>

      <div id="viewuser" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="address.68" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.user" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <!-- <gsmsg:write key="cmn.affiliation" /> -->
          <html:select name="adr070Form" property="adr070selectPermitViewUser" size="10" styleClass="select01" multiple="true">
          <logic:notEmpty name="adr070Form" property="selectPermitViewUser">
            <html:optionsCollection name="adr070Form" property="selectPermitViewUser" value="value" label="label" />
          </logic:notEmpty>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addViewUser');">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteViewUser');">
        </td>
        <td align="center" class="td_body01" valign="top">
          <!-- <gsmsg:write key="address.src.34" /> -->
          <table>
          <tr>
          <td>
            <input class="btn_group_n1" onclick="return openAllGroup(this.form.adr070permitViewUserGroup, 'adr070permitViewUserGroup', '-1', '0', 'init', 'adr070permitViewUser', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
            <html:select name="adr070Form" property="adr070permitViewUserGroup" styleClass="select01" onchange="buttonPush('init');">
              <html:optionsCollection name="adr070Form" property="groupCmbList" value="value" label="label" />
            </html:select>
          </td>
          <td valign="bottom">
            <input type="button" onclick="openGroupWindow(this.form.adr070permitViewUserGroup, 'adr070permitViewUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="adr070GroupBtn2">
          </td>
          </tr>
          <tr>
          <td>
            <html:select name="adr070Form" property="adr070NoSelectPermitViewUser" size="9" styleClass="select01" multiple="true">
            <logic:notEmpty name="adr070Form" property="noSelectPermitViewUser">
              <html:optionsCollection name="adr070Form" property="noSelectPermitViewUser" value="value" label="label" />
            </logic:notEmpty>
            </html:select>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </table>
      </div>

    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.edit.permissions" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt">

      <div id="editselect">
      <html:radio property="adr070permitEdit" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_OWN) %>" styleId="edit0" onclick="editchange();" /><label for="edit0"><span class="text_base"><gsmsg:write key="address.62" /></span></label>
      <html:radio property="adr070permitEdit" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_GROUP) %>" styleId="edit1" onclick="editchange();" /><label for="edit1"><span class="text_base"><gsmsg:write key="group.designation" /></span></label>
      <html:radio property="adr070permitEdit" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_USER) %>" styleId="edit2" onclick="editchange();" /><label for="edit2"><span class="text_base"><gsmsg:write key="cmn.user.specified" /></span></label>
      <html:radio property="adr070permitEdit" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_NORESTRICTION) %>" styleId="edit3" onclick="editchange();" /><label for="edit3"><span class="text_base"><gsmsg:write key="cmn.no.setting.permission" /></span></label>
      </div>

      <div id="editselectstr">
      </div>

      <div id="editgroup" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.editgroup" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.group" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr070Form" property="adr070selectPermitEditGroup" size="10" styleClass="select01" multiple="true">
          <logic:notEmpty name="adr070Form" property="selectPermitEditGroup">
            <html:optionsCollection name="adr070Form" property="selectPermitEditGroup" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addEditGroup');">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteEditGroup');">
        </td>
        <td align="center" class="td_body01" valign="top">
          <html:select name="adr070Form" property="adr070NoSelectPermitEditGroup" size="10" styleClass="select01" multiple="true">
          <logic:notEmpty name="adr070Form" property="noSelectPermitEditGroup">
            <html:optionsCollection name="adr070Form" property="noSelectPermitEditGroup" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        </tr>
        </table>
      </div>

      <div id="edituser" class="spacer">
        <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.edituser" /></span></td>
        <td width="0%"><img alt="<gsmsg:write key="cmn.space" />" height="1" src="../common/images/damy.gif" width="25"></td>
        <td align="center" class="td_type3" width="50%"><span class="text_base3"><gsmsg:write key="cmn.user" /></span></td>
        </tr>

        <tr>
        <td align="center" class="td_body01" valign="top">
          <!-- <gsmsg:write key="cmn.affiliation" /> -->
          <html:select name="adr070Form" property="adr070selectPermitEditUser" size="10" styleClass="select01" multiple="true">
          <logic:notEmpty name="adr070Form" property="selectPermitEditUser">
            <html:optionsCollection name="adr070Form" property="selectPermitEditUser" value="value" label="label" />
          </logic:notEmpty>
          <option>&nbsp;</option>
          </html:select>
        </td>
        <td valign="middle" align="center">
          <img alt="<gsmsg:write key="cmn.add" />" border="0" src="../common/images/arrow2_l.gif" onClick="buttonPush('addEditUser');">
          <br><br><br>
          <img alt="<gsmsg:write key="cmn.delete" />" border="0" src="../common/images/arrow2_r.gif" onClick="buttonPush('deleteEditUser');">
        </td>
        <td align="center" class="td_body01" valign="top">
          <!-- <gsmsg:write key="address.src.34" /> -->
          <table>
          <tr>
          <td>
            <input class="btn_group_n1" onclick="return openAllGroup(this.form.adr070permitEditUserGroup, 'adr070permitEditUserGroup', '-1', '0', 'init', 'adr070permitEditUser', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
            <html:select name="adr070Form" property="adr070permitEditUserGroup" styleClass="select01" onchange="buttonPush('init');">
              <html:optionsCollection name="adr070Form" property="groupCmbList" value="value" label="label" />
            </html:select>
          </td>
          <td valign="bottom">
            <input type="button" onclick="openGroupWindow(this.form.adr070permitEditUserGroup, 'adr070permitEditUserGroup', '0', 'init')" class="group_btn2" value="&nbsp;&nbsp;" id="adr070GroupBtn3">
          </td>
          </tr>
          <tr>
          <td>
            <html:select name="adr070Form" property="adr070NoSelectPermitEditUser" size="9" styleClass="select01" multiple="true">
            <logic:notEmpty name="adr070Form" property="noSelectPermitEditUser">
              <html:optionsCollection name="adr070Form" property="noSelectPermitEditUser" value="value" label="label" />
            </logic:notEmpty>
            <option>&nbsp;</option>
            </html:select>
          </td>
          </tr>
          </table>
        </td>
        </tr>
        </table>
      </div>

    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('importAddressConfirm');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAddressList');">
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</div>
</body>
</html:html>