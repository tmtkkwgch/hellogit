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
<html:form action="/address/adr060">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<logic:notEmpty name="adr060Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr060Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr060Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr060Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr060Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr060Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="60%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.number.display.settings" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.setting" />" onClick="return buttonPush('adr060commit');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr060back');">
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

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="address.adr060.2" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
        <html:select name="adr060Form" property="adr060DspCount">
          <html:optionsCollection name="adr060Form" property="adr060DspCountList" value="value" label="label" />
        </html:select>
      </td>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="address.adr060.3" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="address.adr060.4" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td align="left" width="100%">
        <html:select name="adr060Form" property="adr060ComCount">
          <html:optionsCollection name="adr060Form" property="adr060DspCountList" value="value" label="label" />
        </html:select>
      </td>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="address.adr060.5" /></span>
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
    <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.setting" />" onClick="return buttonPush('adr060commit');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('adr060back');">
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