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
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.addressbook" /></title>
</head>

<body class="body_03">
<html:form action="/address/adr040">
<input type="hidden" name="CMD" value="">
<html:hidden name="adr040Form" property="backScreen" />

<logic:notEmpty name="adr040Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr040Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr040Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr040Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr040Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr040Form" property="adr010selectSid">
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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.permissions" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('adr040kakunin');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr040back');">
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
    <td class="table_bg_A5B4E1" width="18%" nowrap><span class="text_bb1"><gsmsg:write key="address.85" /></span></td>
    <td align="left" class="td_type20" width="82%">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="adr040Form" styleId="adr040Pow5_01" property="adr040Pow5" value="1" /><label for="adr040Pow5_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="adr040Form" styleId="adr040Pow5_02" property="adr040Pow5" value="0" /><label for="adr040Pow5_02"><span class="text_base6"><gsmsg:write key="address.87" /></span></label>
      <div class="text_base7"><gsmsg:write key="address.adr040.1" /></div>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="18%" nowrap><span class="text_bb1"><gsmsg:write key="address.88" /></span></td>
    <td align="left" class="td_type20" width="82%">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="adr040Form" styleId="adr040Pow1_01" property="adr040Pow1" value="1" /><label for="adr040Pow1_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="adr040Form" styleId="adr040Pow1_02" property="adr040Pow1" value="0" /><label for="adr040Pow1_02"><span class="text_base6"><gsmsg:write key="address.89" /></span></label>
      <div class="text_base7"><gsmsg:write key="address.adr040.2" /></div>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="address.90" /></span></td>
    <td align="left" class="td_type20">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="adr040Form" styleId="adr040Pow2_01" property="adr040Pow2" value="1" /><label for="adr040Pow2_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="adr040Form" styleId="adr040Pow2_02" property="adr040Pow2" value="0" /><label for="adr040Pow2_02"><span class="text_base6"><gsmsg:write key="address.91" /></span></label>
      <div class="text_base7"><gsmsg:write key="address.adr040.3" /></div>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions.label" /></span></td>
    <td align="left" class="td_type20">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="adr040Form" styleId="adr040Pow3_01" property="adr040Pow3" value="1" /><label for="adr040Pow3_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="adr040Form" styleId="adr040Pow3_02" property="adr040Pow3" value="0" /><label for="adr040Pow3_02"><span class="text_base6"><gsmsg:write key="cmn.noset.edit.permissions.label" /></span></label>
      <div class="text_base7"><gsmsg:write key="cmn.always.edit.noset.edit.permissions" /></div>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="address.94" /></span></td>
    <td align="left" class="td_type20">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="adr040Form" styleId="adr040Pow4_01" property="adr040Pow4" value="1" /><label for="adr040Pow4_01"><span class="text_base6"><gsmsg:write key="address.140" /></span></label>&nbsp;
      <html:radio name="adr040Form" styleId="adr040Pow4_02" property="adr040Pow4" value="0" /><label for="adr040Pow4_02"><span class="text_base6"><gsmsg:write key="address.95" /></span></label>
      <div class="text_base7"><gsmsg:write key="address.adr040.5" /></div>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('adr040kakunin');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr040back');">
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