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
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr300.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.addressbook" /></title>
</head>

<body class="body_03" onload="lmtEnableDisable();lmtEnableDisable2(<bean:write name="adr300Form" property="adr300PermitKbn" />);">
<html:form action="/address/adr300">
<input type="hidden" name="CMD" value="">
<html:hidden name="adr300Form" property="backScreen" />

<logic:notEmpty name="adr300Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr300Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr300Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr300Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr300Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr300Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.default.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.default.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.default.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('adr300kakunin');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr300back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="address.120" /></span></td>
    <td align="left" class="td_type20">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
        <html:radio name="adr300Form" styleId="adr300MemDspKbn_01" property="adr300MemDspKbn" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.MEM_DSP_ADM) %>" onclick="lmtEnableDisable();" /><label for="adr300MemDspKbn_01"><span class="text_base6"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
        <html:radio name="adr300Form" styleId="adr300MemDspKbn_02" property="adr300MemDspKbn" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.MEM_DSP_USR) %>" onclick="lmtEnableDisable();" /><label for="adr300MemDspKbn_02"><span class="text_base6"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      </td>
      </tr>
      </table>

      <span id="lmtinput">※<gsmsg:write key="cmn.view.user.defaultset" /><br></span>
      <span class="text_bb1"><gsmsg:write key="cmn.reading" />：</span>
      <html:select property="adr300PermitKbn" onchange="lmtEnableDisable2(this[this.selectedIndex].value);">
        <html:optionsCollection name="adr300Form" property="adr300PermitKbnLabel" value="value" label="label" />
      </html:select>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.edit" />：</span>
      <span id="lmtinput1">
        <gsmsg:write key="address.62" />
      </span>
      <span id="lmtinput2">
        <gsmsg:write key="group.designation" />
      </span>
      <span id="lmtinput3">
        <gsmsg:write key="cmn.user.specified" />
      </span>
      <span id="lmtinput4">
        <html:select property="adr300EditKbn">
          <html:optionsCollection name="adr300Form" property="adr300EditKbnLabel" value="value" label="label" />
        </html:select>
      </span>

    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('adr300kakunin');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr300back');">
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