<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% int tmodeShare = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_SHARE; %>
<% int tmodePrivate = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_PRIVATE; %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng090kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.58" /></title>
</head>
<body class="body_03">

<html:form action="ringi/rng090kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="backScreen" />
<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rngTplCmdMode" />
<html:hidden property="rngSelectTplSid" />

<html:hidden property="rng090title" />
<html:hidden property="rng090rngTitle" />
<html:hidden property="rng090content" />
<html:hidden property="rng090group" />
<html:hidden name="rng090knForm" property="rng090knTmpFileId" />

<html:hidden property="rng090CatSid" />

<html:hidden property="rng060SelectCat" />
<html:hidden property="rng060SelectCatUsr" />

<input type="hidden" name="helpPrm" value="<bean:write name="rng090knForm" property="rngTemplateMode" />">
<input type="hidden" name="helpPrm" value="<bean:write name="rng090knForm" property="rngTplCmdMode" />">

<logic:notEmpty name="rng090knForm" property="rng090apprUser">
<logic:iterate id="apprUser" name="rng090knForm" property="rng090apprUser">
  <input type="hidden" name="rng090apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng090knForm" property="rng090confirmUser">
<logic:iterate id="confirmUser" name="rng090knForm" property="rng090confirmUser">
  <input type="hidden" name="rng090confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<!-- BODY -->
<bean:define id="rngTemplateMode" name="rng090knForm" property="rngTemplateMode" />
<% int rtMode = ((Integer) rngTemplateMode).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" class="tl0" align="center">
<tr>
<td>

  <table width="70%" class="tl0" align="center">
  <tr>
  <td width="100%" align="left">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
<% if (rtMode == tmodeShare) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.entry.shared.template.kn" /> ]</td>
<% } else if (rtMode == tmodePrivate) { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.entry.personal.template.kn" /> ]</td>
<% } else { %>
        <td width="0%"><img src="../ringi/images/header_ringi_02.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="rng.58" /> ]</td>
<% } %>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%" nowrap>
          <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('cmn999kakutei')">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng090back')"></td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

<logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td align="left"><html:errors/></td>
      </tr>
    </table>
</logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td><img src="../common/images/damy.gif" width="1" height="5" alt=""></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.category" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <span class="text_base"><bean:write name="rng090knForm" property="rng090knCatName" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="rng.10" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <span class="text_base"><bean:write name="rng090knForm" property="rng090title" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <span class="text_base"><bean:write name="rng090knForm" property="rng090rngTitle" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="rng.12" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <span class="text_base">
    <bean:write name="rng090knForm" property="rng090knViewContent" filter="false" />
    </span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td valign="middle" align="left" class="td_wt">
    <span class="text_base">
<logic:empty name="rng090knForm" property="rng090FileLabelList" scope="request">&nbsp;</logic:empty>
<logic:notEmpty name="rng090knForm" property="rng090FileLabelList" scope="request">
        <table cellpadding="0" cellspacing="0" border="0">
        <logic:iterate id="fileMdl" name="rng090knForm" property="rng090FileLabelList" scope="request">
          <tr>
          <td width="0"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun"><a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a></td>
          </tr>
        </logic:iterate>
        </table>
</logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="rng.42" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <span class="text_base">
      <logic:notEmpty name="rng090knForm" property="rng090knApprUserList">
      <logic:iterate id="apprUser" name="rng090knForm" property="rng090knApprUserList">
        <bean:write name="apprUser" property="usiSei" />&nbsp;<bean:write name="apprUser" property="usiMei" /><br>
      </logic:iterate>
      </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="rng.35" /></span></td>
    <td align="left" class="td_wt" width="100%">
    <span class="text_base">
      <logic:notEmpty name="rng090knForm" property="rng090knConfirmUserList">
      <logic:iterate id="confirmUser" name="rng090knForm" property="rng090knConfirmUserList">
        <bean:write name="confirmUser" property="usiSei" />&nbsp;<bean:write name="confirmUser" property="usiMei" /><br>
      </logic:iterate>
      </logic:notEmpty>
    </span>
    </td>
    </tr>

    <tr>
      <td colspan="2" width="100%" align="left"><img src="../common/images/damy.gif" width="1" height="5" alt=""></td>
    </tr>

    </table>

      <table width="100%" class="tl0" cellspacing="3">
      <tr>
      <td width="100%" align="right" nowrap>
          <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('cmn999kakutei')">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng090back')"></td>
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