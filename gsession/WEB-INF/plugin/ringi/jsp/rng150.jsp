<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int tmodeShare = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_SHARE; %>
<% int tmodePrivate = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_PRIVATE; %>

<% int tCmdAdd = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_ADD; %>
<% int tCmdEdit = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT; %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng150.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.rng150.01" /></title>
</head>

<body class="body_03">

<html:form action="ringi/rng150">
<input type="hidden" name="CMD" value="">
<html:hidden property="rngTemplateMode" />
<html:hidden property="rng060SelectCat" />
<html:hidden property="rng060SelectCatUsr" />

<input type="hidden" name="rng140ProcMode" value="">
<input type="hidden" name="rng140CatSid" value="">
<input type="hidden" name="rng140SeniFlg" value="">

<html:hidden property="rngProcMode" />
<html:hidden property="backScreen" />
<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rng020Title" />
<html:hidden property="rng020Content" />

<html:hidden property="rng020copyApply" />

<logic:notEmpty name="rng150Form" property="rng020apprUser">
<logic:iterate id="apprUser" name="rng150Form" property="rng020apprUser">
  <input type="hidden" name="rng020apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng150Form" property="rng020confirmUser">
<logic:iterate id="confirmUser" name="rng150Form" property="rng020confirmUser">
  <input type="hidden" name="rng020confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="helpPrm" value="<bean:write name="rng150Form" property="rngTemplateMode" />">
<input type="hidden" name="helpPrm" value="0">

<bean:define id="rngTemplateMode" name="rng150Form" property="rngTemplateMode" />
<% int rtcMode = ((Integer) rngTemplateMode).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
<% if (rtcMode == tmodeShare) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.rng150.03" /> ]</td>
<% } else { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.rng150.04" /> ]</td>
<% } %>
        <td width="0%">
        <img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.category.select" />"></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addEditCategoryWithFlg('-1', <%= tCmdAdd %>, 'addeditcategory')">
        <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="rng.rng150.02" />" onClick="buttonPush('rng150back')">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.category.select" />"></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return sortUp(<%= String.valueOf(rtcMode) %>);">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return sortDown(<%= String.valueOf(rtcMode) %>);">
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <th align="center" class="table_bg_7D91BD" width="5%"><span class="text_tlw">&nbsp;</span></th>
      <th align="center" class="table_bg_7D91BD" width="95%"><span class="text_tlw"><gsmsg:write key="cmn.category.name" /></span></th>
    </tr>
      <bean:define id="mod1" value="0" />
      <logic:iterate id="tempCatMdl" name="rng150Form" property="rng150CatList" indexId="idx">
        <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <bean:define id="rtcSid" name="tempCatMdl" property="rtcSid" type="java.lang.Integer" />
        <% String strRtpSid = String.valueOf(rtcSid); %>
        <tr>
          <td align="center" width="5%" class="<bean:write name="tblColor" />">
            <html:radio property="rng150SortRadio" value="<%= strRtpSid %>" />
          </td>
          <td align="left" width="30%" class="<bean:write name="tblColor" />">
          <a href="#"  onClick="addEditCategoryWithFlg('<%= strRtpSid %>', <%= tCmdEdit %>, 'addeditcategory')">
          <bean:write name="tempCatMdl" property="rtcName" /><a></td>
        </tr>
      </logic:iterate>
    </table>

  </td>
  </tr>

  <tr>
    <td>
      <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">
    </td>
  </tr>
  <tr>
  <td>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td width="100%" align="right">
        <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addEditCategoryWithFlg('-1', <%= tCmdAdd %>, 'addeditcategory')">
        <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="rng.rng150.02" />" onClick="buttonPush('rng150back')">
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