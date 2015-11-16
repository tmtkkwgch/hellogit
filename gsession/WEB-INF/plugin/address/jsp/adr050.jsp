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
<title>[Group Session] Database Administrator</title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/address/adr050">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<logic:notEmpty name="adr050Form" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr050Form" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr050Form" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr050Form" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr050Form" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr050Form" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>
<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.addressbook" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('adr050back');">
    </td>
    </tr>
    </table>


    <table cellpadding="5" cellspacing="0" border="0" width="100%">

    <logic:equal name="adr050Form" property="adr050initFlg" value="<%= String.valueOf(jp.groupsession.v2.adr.GSConstAddress.MEM_DSP_USR) %>">
      <tr>
      <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('iniset');"><span class="text_link"><gsmsg:write key="cmn.default.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="address.83" /></li></div></dd>
      </dl>
      </td>
      </tr>
    </logic:equal>

    <tr>
    <td align="left">
    <dl class="decorate_textbox1">
    <dt><a href="#" onClick="return buttonPush('countedit');"><span class="text_link"><gsmsg:write key="cmn.number.display.settings" /></span></a></dt>
    <dd><div class="text"><li><gsmsg:write key="address.adr050.1" /></li></div></dd>
    </dl>
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