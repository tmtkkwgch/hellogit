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
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng061.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.56" /></title>
</head>
<body class="body_03">

<html:form action="ringi/rng061">
<input type="hidden" name="CMD" value="">

<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />
<html:hidden property="rngSelectTplSid" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rng061title" />
<html:hidden property="rng061rngTitle" />
<html:hidden property="rng061content" />
<html:hidden name="rng061Form" property="rng061TmpFileId" />
<html:hidden property="rng061templateType" />

<html:hidden property="rng020Title" />
<html:hidden property="rng020Content" />
<html:hidden property="rng060SelectCat" />
<html:hidden property="rng060SelectCatUsr" />
<span id="users">
<logic:notEmpty name="rng061Form" property="rng020apprUser">
<logic:iterate id="apprUser" name="rng061Form" property="rng020apprUser">
  <input type="hidden" name="rng020apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng061Form" property="rng020confirmUser">
<logic:iterate id="confirmUser" name="rng061Form" property="rng020confirmUser">
  <input type="hidden" name="rng020confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>
</span>

<logic:notEmpty name="rng061Form" property="rng061apprUser">
<logic:iterate id="apprUser" name="rng061Form" property="rng061apprUser">
  <input type="hidden" name="rng061apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng061Form" property="rng061confirmUser">
<logic:iterate id="confirmUser" name="rng061Form" property="rng061confirmUser">
  <input type="hidden" name="rng061confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="rng020copyApply" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%" class="tl0" align="center">
<tr>
<td>

  <table width="70%" class="tl0" align="center">
  <tr>
  <td width="100%" align="left">

    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td width="0%"><img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rng.56" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%" nowrap>
          <input type="button" name="template_henshu" class="btn_base1" value="<gsmsg:write key="cmn.select" />" onClick="selectTpl()">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng060')"></td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" align="center" cellpadding="5">
    <tr>
    <td width="100%" align="left" nowrap>

      <table class="tl1" width="100%" align="center" border="0" cellpadding="5">

      <tr>
      <logic:equal name="rng061Form" property="rng061templateType" value="2">
      <td align="left" class="table_bg_7D91BD" width="100%">
      </logic:equal>
      <logic:notEqual name="rng061Form" property="rng061templateType" value="2">
      <td align="left" class="td_type24" width="100%">
      </logic:notEqual>
      <span class="text_left text_tlw"><bean:write name="rng061Form" property="rng061title" /></span>
      </td>
      </tr>

      <tr>
        <th width="100%" align="left" class="td_type3"><gsmsg:write key="cmn.title" /></th>
      </tr>

      <tr>
      <td align="left" class="td_wt" width="100%">
<bean:write name="rng061Form" property="rng061rngTitle"/>
      </td>
      </tr>

      <tr>
        <th width="100%" align="left" class="td_type3"><gsmsg:write key="cmn.content" /></th>
      </tr>

      <tr>
      <td align="left" class="td_wt" width="100%">
<pre>
<bean:write name="rng061Form" property="rng061viewContent" filter="false" />
</pre>
      </td>
      </tr>

      <tr>
        <th width="100%" align="left" class="td_type3"><gsmsg:write key="cmn.attach.file" /></th>
      </tr>
      <tr>
        <td width="100%" align="left" class="td_wt">
<logic:empty name="rng061Form" property="rng061FileLabelList" scope="request">&nbsp;</logic:empty>
<logic:notEmpty name="rng061Form" property="rng061FileLabelList" scope="request">
        <table cellpadding="0" cellpadding="0" border="0">
      <logic:iterate id="fileMdl" name="rng061Form" property="tmpFileList" scope="request">
          <tr>
          <td width="0"><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
          <td class="menu_bun"><a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="binSid" />');"><span class="text_link"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a></td>
          </tr>
       </logic:iterate>
        </table>
</logic:notEmpty>
      </td>
      </tr>

      <tr>
        <th width="100%" align="left" class="td_type3"><gsmsg:write key="rng.42" /></th>
      </tr>

      <tr>
      <td align="left" class="td_wt" width="100%">
      <logic:notEmpty name="rng061Form" property="rng061ApprUserList">
      <logic:iterate id="apprUser" name="rng061Form" property="rng061ApprUserList">
        <bean:write name="apprUser" property="usiSei" />&nbsp;<bean:write name="apprUser" property="usiMei" /><br>
      </logic:iterate>
      </logic:notEmpty>
      </td>
      </tr>

      <tr>
        <th width="100%" align="left" class="td_type3"><gsmsg:write key="rng.35" /></th>
      </tr>

      <tr>
      <td align="left" class="td_wt" width="100%">
      <logic:notEmpty name="rng061Form" property="rng061ConfirmUserList">
      <logic:iterate id="confirmUser" name="rng061Form" property="rng061ConfirmUserList">
        <bean:write name="confirmUser" property="usiSei" />&nbsp;<bean:write name="confirmUser" property="usiMei" /><br>
      </logic:iterate>
      </logic:notEmpty>
      </td>
      </tr>

      <tr>
      <td width="100%" align="left"><img src="../common/images/damy.gif" width="1" height="5" alt=""></td>
      </tr>


      <tr>
      <td width="100%" align="left"><img src="../common/images/damy.gif" width="1" height="5" alt=""></td>
      </tr>
      </table>

      <table width="100%" class="tl0" cellspacing="3">
      <tr>
      <td width="100%" align="right" nowrap>
          <input type="button" name="template_henshu" class="btn_base1" value="<gsmsg:write key="cmn.select" />" onClick="selectTpl()">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng060')"></td>
      </td>
      </tr>
      </table>

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