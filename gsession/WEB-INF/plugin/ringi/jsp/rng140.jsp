<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%

String maxLengthBiko = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_COMMENT);

int tCmdAdd = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_ADD;
int tCmdEdit = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT;
int tmodeShare = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_SHARE;
int tmodePrivate = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_PRIVATE;

%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.category.entry" /></title>
<link rel=stylesheet href='../address/css/address.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<bean:define id="rngTemplateMode" name="rng140Form" property="rngTemplateMode" />
<% int rtMode = ((Integer) rngTemplateMode).intValue(); %>
<html:form action="/ringi/rng140">

<input type="hidden" name="CMD" value="ok">
<html:hidden property="rng140ProcMode" />
<html:hidden property="rng140SeniFlg" />
<html:hidden property="rng140CatSid" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rngProcMode" />
<html:hidden property="backScreen" />
<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rng020Title" />
<html:hidden property="rng020Content" />

<html:hidden property="rng020copyApply" />

<html:hidden property="rng060SelectCat" />

<logic:notEmpty name="rng140Form" property="rng020apprUser">
<logic:iterate id="apprUser" name="rng140Form" property="rng020apprUser">
  <input type="hidden" name="rng020apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng140Form" property="rng020confirmUser">
<logic:iterate id="confirmUser" name="rng140Form" property="rng020confirmUser">
  <input type="hidden" name="rng020confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<bean:write name="rng140Form" property="rngTemplateMode" />">
<input type="hidden" name="helpPrm" value="<bean:write name="rng140Form" property="rng140ProcMode" />">

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
<% if (rtMode == tmodeShare) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.23" /> ]</td>
<% } else if (rtMode == tmodePrivate) { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.33" /> ]</td>
<% } %>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="rng.04" />"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('ok');">
    <logic:equal name="rng140Form" property="rng140ProcMode" value="<%= String.valueOf(tCmdEdit) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng140back');">
    </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="rng.04" />"></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
  <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>
  <tr>
  <td>
  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/></td>
    </tr>
    </table>
  </logic:messagesPresent>

  </td>
  </tr>


  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category.name" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt" width="80%">
    <html:text style="width:275px;" maxlength="20" property="rng140CatName" styleClass="text_base" />
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
    <logic:equal name="rng140Form" property="rng140ProcMode" value="<%= String.valueOf(tCmdEdit) %>">
      <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('delete');">
    </logic:equal>
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng140back');">
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