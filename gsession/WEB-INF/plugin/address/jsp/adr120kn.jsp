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
<title>[Group Session] <gsmsg:write key="address.adr120kn.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03">

<html:form action="/address/adr120kn">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr120knForm" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr120knForm" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr120knForm" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr120knForm" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr120knForm" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr120knForm" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr120knForm" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr120knForm" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr120knForm" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr120knForm" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="adr100searchFlg" />
<html:hidden property="adr100page" />
<html:hidden property="adr100pageTop" />
<html:hidden property="adr100pageBottom" />
<html:hidden property="adr100code" />
<html:hidden property="adr100coName" />
<html:hidden property="adr100coNameKn" />
<html:hidden property="adr100coBaseName" />
<html:hidden property="adr100atiSid" />
<html:hidden property="adr100tdfk" />
<html:hidden property="adr100biko" />
<html:hidden property="adr100svCode" />
<html:hidden property="adr100svCoName" />
<html:hidden property="adr100svCoNameKn" />
<html:hidden property="adr100svCoBaseName" />
<html:hidden property="adr100svAtiSid" />
<html:hidden property="adr100svTdfk" />
<html:hidden property="adr100svBiko" />
<html:hidden property="adr100SortKey" />
<html:hidden property="adr100OrderKey" />
<html:hidden property="adr100SearchKana" />
<html:hidden property="adr100mode" />

<logic:notEmpty name="adr120knForm" property="adr120atiSid">
<logic:iterate id="atiSid" name="adr120knForm" property="adr120atiSid">
  <input type="hidden" name="adr120atiSid" value="<bean:write name="atiSid" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr120updateFlg" />
<html:hidden property="adr120knCompanyCnt" />

<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../address/images/header_address_01.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.addressbook" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr120kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('importCompanyDesicion');">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backImportCompany');">
          <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl0" align="center">

  <tr>
  <td colspan="2"><span class="text_r1"><gsmsg:write key="cmn.capture.file.sure" /></span></td>
  </tr>

    <!-- 取込みファイル -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
    <a href="../schedule/sch110kn.do?CMD=downLoad">
      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span>
    </td>
    <td valign="middle" align="left" class="td_type1" width="100%">
    <a href="../address/adr120kn.do?CMD=downLoad">
      <span class="text_link_min"><bean:write name="adr120knForm" property="adr120knFileName" /></span>
    </a>
    </td>
    </tr>


    <!--  取り込み件数 -->
    <tr>
    <td class="table_bg_A5B4E1" width="0%"><span class="text_bb1"><gsmsg:write key="cmn.capture.item.count" /></span></td>
    <td class="td_type1" width="100%"><bean:write name="adr120knForm" property="adr120knCompanyCnt" /><gsmsg:write key="cmn.number" /></td>
    </tr>


    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.11" /></span>
    </td>
    <td valign="middle" align="left" class="td_type1" width="100%">
      <logic:notEmpty name="adr120knForm" property="adr120selectAtiSidCombo">
      <span class="text_base">
      <logic:iterate id="atiLabel" name="adr120knForm" property="adr120selectAtiSidCombo">
      <bean:write name="atiLabel" property="label" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_type1">
    <logic:equal name="adr120knForm" property="adr120updateFlg" value="1">
    <span class="text_base"><gsmsg:write key="address.109" /></span>
    </logic:equal>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('importCompanyDesicion');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backImportCompany');">
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