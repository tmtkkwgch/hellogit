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
<script language="JavaScript" src="../common/js/cmn120.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <bean:write name="cmn120Form" property="cmn120FunctionName" /><gsmsg:write key="cmn.select" /></title>
</head>

<body class="body_03" onload="cmn120Load();">

<html:form action="common/cmn120">

<input type="hidden" name="CMD">
<html:hidden property="cmn120BackUrl" />
<html:hidden property="cmn120FunctionName" />
<html:hidden property="cmn120FormKey" />
<html:hidden property="cmn120paramName" />
<html:hidden property="cmn120MovePage" />

<logic:notEmpty name="cmn120Form" property="cmn120userSid">
<logic:iterate id="usid" name="cmn120Form" property="cmn120userSid">
  <input type="hidden" name="cmn120userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn120Form" property="cmn120userSidOld">
<logic:iterate id="usid" name="cmn120Form" property="cmn120userSidOld">
  <input type="hidden" name="cmn120userSidOld" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn120Form" property="cmn120paramName">
<logic:notEmpty name="cmn120Form" property="cmn120userSid">
<logic:iterate id="usid" name="cmn120Form" property="cmn120userSid">
  <input type="hidden" name="<bean:write name="cmn120Form" property="cmn120paramName" />" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>
</logic:notEmpty>

<logic:match name="cmn120Form" property="cmn120BackUrl" value="smail">
  <input type="hidden" name="helpPrm" value="0">
</logic:match>
<logic:match name="cmn120Form" property="cmn120BackUrl" value="circular">
  <input type="hidden" name="helpPrm" value="1">
</logic:match>
<logic:match name="cmn120Form" property="cmn120BackUrl" value="project">
  <input type="hidden" name="helpPrm" value="2">
</logic:match>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="600px">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <logic:match name="cmn120Form" property="cmn120BackUrl" value="smail">
        <img src="../smail/images/header_smail03_7_01.gif" border="0" alt="<gsmsg:write key="cmn.shortmail" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shortmail" /></span></td>
      </logic:match>
      <logic:match name="cmn120Form" property="cmn120BackUrl" value="circular">
        <img src="../circular/images/header_circular.gif" border="0" alt="<gsmsg:write key="cir.5" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cir.5" /></span></td>
      </logic:match>
      <logic:match name="cmn120Form" property="cmn120BackUrl" value="project">
        <img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
      </logic:match>
    <td width="100%" class="header_white_bg_text">[ <bean:write name="cmn120Form" property="cmn120FunctionName" /><gsmsg:write key="cmn.select" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
      <td class="header_glay_bg" width="50%">
        <input type="button" value="<gsmsg:write key="cmn.select" />" class="btn_base1" onClick="cmn120ButtonPush(1);">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="cmn120ButtonPush(2);"></td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <html:errors />
    <img src="../common/images/spacer.gif" alt="" height="10px" width="10px">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="schedule.src.25" /></span></td>
    <td class="td_wt">
      <table width="100%" border="0" cellpadding="0">
      <tr>
      <td align="left" width="50%">
        <html:select name="cmn120Form" property="cmn120MyGroupSid" styleClass="select01">
          <logic:notEmpty name="cmn120Form" property="cmn120MyGroupList">
            <html:optionsCollection name="cmn120Form" property="cmn120MyGroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="50%" align="center">
        <bean:define id="functionName" name="cmn120Form" property="cmn120FunctionName" type="java.lang.String" />
        <input type="button" value="<gsmsg:write key="cmn.cmn120.1" arg0="<%= functionName %>" />" class="btn_base1_3" onClick="cmn120ButtonPush(3);">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><bean:write name="cmn120Form" property="cmn120FunctionName" /></span><span class="text_r2">※</span></td>
    <td align="left" class="td_wt">
      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><bean:write name="cmn120Form" property="cmn120FunctionName" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
      <input class="btn_group_n1" onclick="return openAllGroup(this.form.cmn120groupSid, 'cmn120groupSid', '-1', '0', 'changeGrp', 'cmn120userSid', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
        <html:select name="cmn120Form" property="cmn120groupSid" styleClass="select01" onchange="cmn120ChangeGrp();">
          <logic:notEmpty name="cmn120Form" property="cmn120GroupList">
            <html:optionsCollection name="cmn120Form" property="cmn120GroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="10%" align="center" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.cmn120groupSid, 'cmn120groupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="cmn120GroupBtn">
      </td>
      </tr>
      <tr>
      <td>
        <html:select name="cmn120Form" property="cmn120SelectRightUser" size="15" multiple="true" styleClass="select01">
          <logic:notEmpty name="cmn120Form" property="cmn120RightUserList">
            <html:optionsCollection name="cmn120Form" property="cmn120RightUserList" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="cmn120ButtonPush(5);">
        <br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="cmn120ButtonPush(4);">
        <br>
      </td>
      <td align="center">
        <html:select name="cmn120Form" property="cmn120SelectLeftUser" size="15" multiple="true" styleClass="select01">
          <logic:notEmpty name="cmn120Form" property="cmn120LeftUserList">
            <html:optionsCollection name="cmn120Form" property="cmn120LeftUserList" value="value" label="label" />
          </logic:notEmpty>
          <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
        </html:select>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" alt="" height="10px" width="10px">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.select" />" class="btn_base1" onClick="cmn120ButtonPush(1);">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="cmn120ButtonPush(2);">
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