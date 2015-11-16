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
<title>[Group Session] <gsmsg:write key="address.adr070kn.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03">

<html:form action="/address/adr070kn">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="adr070knForm" property="adr070cmdMode" />">

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/address/jsp/adr010_hiddenParams.jsp" %>

<logic:notEmpty name="adr070knForm" property="adr010SearchTargetContact" scope="request">
  <logic:iterate id="target" name="adr070knForm" property="adr010SearchTargetContact" scope="request">
    <input type="hidden" name="adr010SearchTargetContact" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr070knForm" property="adr010svSearchTargetContact" scope="request">
  <logic:iterate id="svTarget" name="adr070knForm" property="adr010svSearchTargetContact" scope="request">
    <input type="hidden" name="adr010svSearchTargetContact" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070knForm" property="adr010searchLabel">
<logic:iterate id="searchLabel" name="adr070knForm" property="adr010searchLabel">
  <input type="hidden" name="adr010searchLabel" value="<bean:write name="searchLabel" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="adr070knForm" property="adr010svSearchLabel">
<logic:iterate id="svSearchLabel" name="adr070knForm" property="adr010svSearchLabel">
  <input type="hidden" name="adr010svSearchLabel" value="<bean:write name="svSearchLabel" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr070selectCompany" />
<html:hidden property="adr070selectCompanyBase" />
<html:hidden property="adr070permitView" />
<html:hidden property="adr070tantoGroup" />

<logic:notEmpty name="adr070knForm" property="adr070permitViewGroup">
<logic:iterate id="permitViewGroup" name="adr070knForm" property="adr070permitViewGroup">
  <input type="hidden" name="adr070permitViewGroup" value="<bean:write name="permitViewGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070knForm" property="adr070permitViewUser">
<logic:iterate id="permitViewUser" name="adr070knForm" property="adr070permitViewUser">
  <input type="hidden" name="adr070permitViewUser" value="<bean:write name="permitViewUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr070permitViewUserGroup" />
<html:hidden property="adr070permitEdit" />


<logic:notEmpty name="adr070knForm" property="adr070permitEditGroup">
<logic:iterate id="permitEditGroup" name="adr070knForm" property="adr070permitEditGroup">
  <input type="hidden" name="adr070permitEditGroup" value="<bean:write name="permitEditGroup" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070knForm" property="adr070permitEditUser">
<logic:iterate id="permitEditUser" name="adr070knForm" property="adr070permitEditUser">
  <input type="hidden" name="adr070permitEditUser" value="<bean:write name="permitEditUser" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070knForm" property="adr070tantoList">
<logic:iterate id="tanto" name="adr070knForm" property="adr070tantoList">
  <input type="hidden" name="adr070tantoList" value="<bean:write name="tanto" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070knForm" property="projectCmbList">
<logic:iterate id="project" name="adr070knForm" property="projectCmbList">
  <input type="hidden" name="projectCmbList" value="<bean:write name="project" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="adr070knForm" property="adr010selectSid">
<logic:iterate id="adrSid" name="adr070knForm" property="adr010selectSid">
  <input type="hidden" name="adr010selectSid" value="<bean:write name="adrSid" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="adr070permitEditUserGroup" />
<html:hidden property="adr070cmdMode" />
<html:hidden property="adr070updateFlg" />

<!--　BODY -->
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
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="address.adr070kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.addressbook" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backRegist');">
          <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl2" align="center">
    <!-- <gsmsg:write key="cmn.capture.file" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <span class="text_base"><bean:write name="adr070knForm" property="adr070knFileName" /></span>
    </td>
    </tr>

    <!-- <gsmsg:write key="adr.address.capture" /><gsmsg:write key="cmn.name" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.adr070kn.2" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <logic:notEmpty name="adr070knForm" property="adr070knNameList">
      <span class="text_base">
      <logic:iterate id="userName" name="adr070knForm" property="adr070knNameList">
      <bean:write name="userName" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.number.display.settings" /> -->
    <logic:notEqual name="adr070knForm" property="adr070cmdMode" value="1">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.139" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <table border="0">
      <tr>
      <td class="text_base"><gsmsg:write key="address.139" />&nbsp;:&nbsp;</td>
      <td class="text_base"><bean:write name="adr070knForm" property="adr070knCompanyName" /></td>
      </tr>
      <tr>
      <td class="text_base"><gsmsg:write key="address.10" />&nbsp;:&nbsp;</td>
      <td class="text_base"><bean:write name="adr070knForm" property="adr070knCompanyBaseName" /></td>
      </tr>
      </table>
    </td>
    </tr>
    </logic:notEqual>

    <logic:equal name="adr070knForm" property="adr070cmdMode" value="1">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.adr120kn.2" /></span>
    </td>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <logic:notEmpty name="adr070knForm" property="adr070knComList">
      <span class="text_base">
      <logic:iterate id="companyName" name="adr070knForm" property="adr070knComList">
      <bean:write name="companyName" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    <!-- 役職 -->
    <logic:notEmpty name="adr070knForm" property="adr070knPositionList">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.post" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <span class="text_r1">
        <gsmsg:write key="address.adr070kn.3" /><br>
        <gsmsg:write key="address.adr070kn.4" />
      </span><br>
      <span class="text_base">
        <logic:iterate id="posName" name="adr070knForm" property="adr070knPositionList">
          <bean:write name="posName"/><br>
        </logic:iterate>
      </span>
    </td>
    </tr>
    </logic:notEmpty>

    <!-- <gsmsg:write key="cmn.number.display.settings" /> -->
    <logic:equal name="adr070knForm" property="adr070cmdMode" value="1">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <logic:equal name="adr070knForm" property="adr070updateFlg" value="1" >
      <span class="text_base"><gsmsg:write key="address.109" /></span>
      </logic:equal>
    </td>
    </tr>
    </logic:equal>

    <!-- <gsmsg:write key="cmn.staff" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.staff" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">
      <logic:notEmpty name="adr070knForm" property="adr070knTantoUserName">
        <span class="text_base">
        <logic:iterate id="tanto" name="adr070knForm" property="adr070knTantoUserName" indexId="idx">
          <bean:write name="tanto" property="usisei" />&nbsp;<bean:write name="tanto" property="usimei" /><br>
        </logic:iterate>
        </span>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- <gsmsg:write key="address.61" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="address.61" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <bean:define id="permitView" name="adr070knForm" property="adr070permitView" type="java.lang.Integer"/>
      <% if (permitView.intValue() == jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_NORESTRICTION) { %>
      <span class="attent1"><gsmsg:write key="cmn.no.setting.permission" /></span>
      <% } else if (permitView.intValue() == jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_GROUP) { %>
      <span class="attent1"><gsmsg:write key="group.designation" />：</span>
      <br>
      <% } else if (permitView.intValue() == jp.groupsession.v2.cmn.GSConst.ADR_VIEWPERMIT_USER) { %>
      <span class="attent1"><gsmsg:write key="cmn.user.specified" />：</span>
      <br>
      <% } else { %>
      <span class="attent1"><gsmsg:write key="address.62" /></span>
      <% } %>

      <logic:notEmpty name="adr070knForm" property="adr070knPermitViewList">
      <span class="text_base">
      <logic:iterate id="permitViewName" name="adr070knForm" property="adr070knPermitViewList">
      <bean:write name="permitViewName" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>

    <!-- <gsmsg:write key="cmn.edit.permissions" /> -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <bean:define id="permitEdit" name="adr070knForm" property="adr070permitEdit" type="java.lang.Integer" />
      <% if (permitEdit.intValue() == jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_NORESTRICTION) { %>
      <span class="attent1"><gsmsg:write key="cmn.no.setting.permission" /></span>
      <% } else if (permitEdit.intValue() == jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_GROUP) { %>
      <span class="attent1"><gsmsg:write key="group.designation" />：</span>
      <br>
      <% } else if (permitEdit.intValue() == jp.groupsession.v2.adr.GSConstAddress.EDITPERMIT_USER) { %>
      <span class="attent1"><gsmsg:write key="cmn.user.specified" />：</span>
      <br>
      <% } else { %>
      <span class="attent1"><gsmsg:write key="address.62" /></span>
      <% } %>

      <logic:notEmpty name="adr070knForm" property="adr070knPermitEditList">
      <span class="text_base">
      <logic:iterate id="permitEditName" name="adr070knForm" property="adr070knPermitEditList">
      <bean:write name="permitEditName" /><br>
      </logic:iterate>
      </span>
      </logic:notEmpty>
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backRegist');">
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