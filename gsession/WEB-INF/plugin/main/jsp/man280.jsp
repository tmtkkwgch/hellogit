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
<title>[GroupSession] <gsmsg:write key="man.restricteduse.plugin" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../main/js/man280.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="selectUseKbn(); selectLimitType();">

<html:form action="/main/man280">

<input type="hidden" name="CMD" value="">
<html:hidden name="man280Form" property="menuEditOk" />
<html:hidden name="man280Form" property="man120pluginId" />

<html:hidden name="man280Form" property="man280initFlg" />
<html:hidden name="man280Form" property="man280backId" />

<logic:notEmpty name="man280Form" property="man280memberSid">
<logic:iterate id="usid" name="man280Form" property="man280memberSid">
  <input type="hidden" name="man280memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="man280Form" property="man280AdminSid">
<logic:iterate id="usid" name="man280Form" property="man280AdminSid">
  <input type="hidden" name="man280AdminSid" value="<bean:write name="usid" />">
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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.plugin.usage.restrictions" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="50%"></td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
      <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backPluginList');"></td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    </logic:messagesPresent>

    <h1>
    <logic:equal name="man280Form" property="man280pluginKbn" value="0">
      <img width="25px" src="../<bean:write name="man280Form" property="man120pluginId" />/images/menu_icon_single.gif" id="img<bean:write name="man280Form" property="man120pluginId" />" alt="" class="img_bottom img_border" onerror="defaultImg('img<bean:write name="man280Form" property="man120pluginId" />')">
    </logic:equal>
    <logic:notEqual name="man280Form" property="man280pluginKbn" value="0">
      <logic:equal name="man280Form" property="man280BinSid" value="0">
        <img width="25" height="25" src="../common/images/plugin_default.gif" name="pitctImage" alt="<gsmsg:write key="cmn.icon" />" class="img_bottom img_border">
      </logic:equal>
      <logic:notEqual name="man280Form" property="man280BinSid" value="0">
        <img width="25" height="25" src="../main/man280.do?CMD=getImageFile&man120imgPluginId=<bean:write name="man280Form" property="man120pluginId" />" alt="<gsmsg:write key="cmn.icon" />" class="img_bottom img_border">
      </logic:notEqual>
    </logic:notEqual>
    &nbsp;<span valign="middle"><bean:write name="man280Form" property="man280pluginName" /></span>
    </h1>

    <logic:notEqual name="man280Form" property="man120pluginId" value="main">
    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2" width="100%" nowrap><span class="text_bb1"><gsmsg:write key="main.man280.2" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
    <td align="left" class="td_type20" width="80%">

      <gsmsg:write key="main.man280.3" />
      <br><br>
      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <logic:notEqual name="man280Form" property="man280groupSidAdmin" value="-9">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.man280groupSidAdmin, 'man280groupSidAdmin', '<bean:write name="man280Form" property="man280groupSidAdmin" />', '0', '', 'man280AdminSid', '-1', '1')" type="button"></br>
        </logic:notEqual>
        <html:select property="man280groupSidAdmin" styleClass="select01" onchange="selectGroup();">
          <logic:notEmpty name="man280Form" property="man280GroupListAdmin">
          <html:optionsCollection name="man280Form" property="man280GroupListAdmin" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="10%" align="left" valign="bottom">
      <input type="button" onclick="openGroupWindow(this.form.man280groupSidAdmin, 'man280groupSidAdmin', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="man280GroupBtn">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="man280SelectLeftAdmin" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man280Form" property="man280LeftAdminList">
          <html:optionsCollection name="man280Form" property="man280LeftAdminList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addAdmin');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeAdmin');">
      </td>
      <td>
        <html:select property="man280SelectRightAdmin" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man280Form" property="man280RightAdminList">
          <html:optionsCollection name="man280Form" property="man280RightAdminList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>
    </table>

    <br>
    </logic:notEqual>

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td class="table_bg_A5B4E1" colspan="2" width="100%" nowrap><span class="text_bb1"><gsmsg:write key="main.plugin.usage.restrictions" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="main.plugin.usage.restrictions" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type20" width="80%">
      <html:radio name="man280Form" property="man280useKbn" styleId="useKbn0" value="0" onclick="selectUseKbn();" /><label for="useKbn0"><gsmsg:write key="main.man280.4" /></label>
      <br>
      <html:radio name="man280Form" property="man280useKbn" styleId="useKbn1" value="1" onclick="selectUseKbn();" /><label for="useKbn1"><gsmsg:write key="main.man280.5" /></label>
    </td>
    </tr>

    <tr>
    <td id="pluginUseMember" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.howto.limit" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td id="pluginUseMember2" align="left" class="td_type20">

      <table width="0%" border="0">

      <tr>
      <td width="100%" align="left" colspan="3">
      <span class="text_base2">
      <html:radio name="man280Form" property="man280limitType" styleId="limitType0" value="0" onclick="selectLimitType();" /><label for="limitType0"><gsmsg:write key="main.man280.7" /></label>
      <br>
      <html:radio name="man280Form" property="man280limitType" styleId="limitType1" value="1" onclick="selectLimitType();" /><label for="limitType1"><gsmsg:write key="main.man280.8" /></label>
      </span>
      <br><br>
      <div id="limit">
      <span class="text_base"><gsmsg:write key="main.man280.9" /></span>
      </div>
      <div id="permit">
      <span class="text_base"><gsmsg:write key="main.man280.10" /></span>
      </div>
      <br>

      </td>
      </tr>

      <tr>
      <td id="permit2" width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="man.restricted.use.user" /></span></td>
      <td id="limit2" width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="main.can.use.user" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <logic:notEqual name="man280Form" property="man280groupSid" value="-9">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.man280groupSid, 'man280groupSid', '<bean:write name="man280Form" property="man280groupSid" />', '0', '', 'man280memberSid', '-1', '1')" type="button"></br>
        </logic:notEqual>
        <html:select property="man280groupSid" styleClass="select01" onchange="selectGroup();">
          <logic:notEmpty name="man280Form" property="man280GroupList">
          <html:optionsCollection name="man280Form" property="man280GroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="10%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.man280groupSid, 'man280groupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="man280GroupBtn2">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="man280SelectLeftUser" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man280Form" property="man280LeftUserList">
          <html:optionsCollection name="man280Form" property="man280LeftUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMember');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMember');">
      </td>
      <td>
        <html:select property="man280SelectRightUser" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man280Form" property="man280RightUserList">
          <html:optionsCollection name="man280Form" property="man280RightUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backPluginList');">
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
</body>
</html:html>