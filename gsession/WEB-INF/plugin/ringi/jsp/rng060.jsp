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

<% int tCmdAdd = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_ADD; %>
<% int tCmdEdit = jp.groupsession.v2.rng.RngConst.RNG_CMDMODE_EDIT; %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../ringi/js/rng060.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.56" /></title>
</head>

<body class="body_03">

<html:form action="ringi/rng060">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />
<html:hidden property="rngTplCmdMode" />
<input type="hidden" name="rngSelectTplSid" value="-1">

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rng020Title" />
<html:hidden property="rng020Content" />
<logic:notEmpty name="rng060Form" property="rng020apprUser">
<logic:iterate id="apprUser" name="rng060Form" property="rng020apprUser">
  <input type="hidden" name="rng020apprUser" value="<bean:write name="apprUser" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="rng060Form" property="rng020confirmUser">
<logic:iterate id="confirmUser" name="rng060Form" property="rng020confirmUser">
  <input type="hidden" name="rng020confirmUser" value="<bean:write name="confirmUser" />">
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="rng060TemplateMode" value="">
<html:hidden property="rng020copyApply" />

<input type="hidden" name="rng090CatSid" value="">
<input type="hidden" name="rng140ProcMode" value="">
<input type="hidden" name="rng140CatSid" value="">

<input type="hidden" name="helpPrm" value="<bean:write name="rng060Form" property="rngTemplateMode" />">


<bean:define id="rngTemplateMode" name="rng060Form" property="rngTemplateMode" />
<% int rtMode = ((Integer) rngTemplateMode).intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- body -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
<% if (rtMode == tmodeAll) { %>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../ringi/images/header_ringi_01.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rng.62" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rng.56" /> ]</td>
        <td width="100%" class="header_white_bg" nowrap>&nbsp;</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rng.62" />"></td>
      </tr>
    </table>
<% } else { %>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
<% if (rtMode == tmodeShare) { %>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.rng060.07" /> ]</td>
<% } else if (rtMode == tmodePrivate) { %>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.personal.template" /> ]</td>
<% } %>
        <td width="0%">
        <img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>
<% } %>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="select.template" />"></td>
        <td class="header_glay_bg" width="50%">
<% if (rtMode == tmodeShare || rtMode == tmodePrivate) { %>
  <% if (rtMode == tmodeShare) { %>
          <input type="button" name="btn_facilities_mnt" class="btn_template" value="<gsmsg:write key="cmn.add.template" />" onClick="selectTemplate('-1', <%= tCmdAdd %>, 'rng090', '<bean:write name="rng060Form" property="rng060SelectCat" />')">
  <% } else { %>
          <input type="button" name="btn_facilities_mnt" class="btn_template" value="<gsmsg:write key="cmn.add.template" />" onClick="selectTemplate('-1', <%= tCmdAdd %>, 'rng090', '<bean:write name="rng060Form" property="rng060SelectCatUsr" />')">
  <% } %>
<% } %>

<% if (rtMode == tmodeShare) { %>
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('060back')">
<% } else { %>
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('060back')">
<% } %>
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.back.admin.setting" />"></td>
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
  <td><span class="text_base"><gsmsg:write key="rng.rng060.03" /></span></td>
  </tr>
<% if (rtMode == tmodeAll) { %>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:15px;" border="0" alt="">
  </td>
  </tr>

<% } %>
<% if (rtMode == tmodeAll || rtMode == tmodeShare) { %>

  <% if (rtMode == tmodeShare) { %>

  <logic:notEqual name="rng060Form" property="rng060SelectCat" value="-1">
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return sortUp(<%= String.valueOf(tmodeShare) %>);">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return sortDown(<%= String.valueOf(tmodeShare) %>);">
    </td>
    </tr>
    </table>
  </td>
  </tr>
  </logic:notEqual>
  <% } %>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="15%" class="td_type25_2" align="center"><span class="text_todo_head"><gsmsg:write key="cmn.category.select" /></span></td>
    <td class="td_type1">
      <html:select property="rng060SelectCat" onchange="buttonPush('init');">
        <html:optionsCollection property="rng060CategoryList" value="value" label="label" />
      </html:select>
    </td>
<% if (rtMode == tmodeShare || rtMode == tmodePrivate) { %>
    <logic:notEqual name="rng060Form" property="rng060SelectCat" value="0">
    <logic:notEqual name="rng060Form" property="rng060SelectCat" value="-1">
    <td width="30px" class="td_type1">
      <input type="button" name="btn_facilities_mnt" class="btn_cate_edit" value="<gsmsg:write key="rng.04" />" onClick="addEditCategory('<bean:write name="rng060Form" property="rng060SelectCat" />', <%= tCmdEdit %>, 'addeditcategory')">
    </td>
    </logic:notEqual>
    </logic:notEqual>
    <td width="30px" class="td_type1">
      <input type="button" name="btn_cat_list" class="btn_cate_list" value="<gsmsg:write key="cmn.categorylist" />" onClick="buttonPush('rng150')">
    </td>
    <td width="110px" class="td_type1">
      <input type="button" name="btn_add_cat" class="btn_folder_n1" value="<gsmsg:write key="rng.rng060.02" />" onClick="addEditCategory('-1', <%= tCmdAdd %>, 'addeditcategory')">
    </td>
<% } %>
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

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
<% if (rtMode != tmodeAll) { %>
    <th align="center" class="table_bg_7D91BD sort_radio_are" width="0%" nowrap>&nbsp;</th>
<% } %>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.category.belong" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.shared.template" /></span></th>
    </tr>
<bean:define id="mod1" value="0" />
<logic:iterate id="template1" name="rng060Form" property="rng060tplListShare" indexId="idx">
  <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
    <bean:define id="tblColor" value="td_type1" />
  </logic:equal>
  <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
    <bean:define id="tblColor" value="td_type25_2" />
  </logic:notEqual>

  <bean:define id="rtpSid" name="template1" property="rtpSid" type="java.lang.Integer" />
  <% String strRtpSid = String.valueOf(rtpSid.intValue()); %>
    <tr>
    <% if (rtMode == tmodeShare) { %>
    <td align="center" class="<bean:write name="tblColor" /> sort_radio_are" width="0%">
      <html:radio property="rng060SortRadio" value="<%= strRtpSid %>" />
    </td>
    <% } %>
    <td align="left" class="<bean:write name="tblColor" />" width="0%" nowrap>
      <bean:write name="template1" property="rtcName" />
    </td>
    <td align="left" class="<bean:write name="tblColor" />" width="100%" nowrap>
       <a href="#" onClick="selectTemplate('<%= strRtpSid %>', <%= tCmdEdit %>, '060title')"><span class="text_link"><bean:write name="template1" property="rtpTitle" /></span></a>
    </td>
    </tr>
</logic:iterate>
    </table>

  </td>
  </tr>

<% } %>
<% if (rtMode == tmodeAll) { %>
  <tr>
    <td>
      <img src="../common/images/spacer.gif" width="1" height="25" border="0" alt="">
    </td>
  </tr>
<% } %>
<% if (rtMode == tmodeAll || rtMode == tmodePrivate) { %>

  <% if (rtMode == tmodePrivate) { %>
  <logic:notEqual name="rng060Form" property="rng060SelectCatUsr" value="-1">
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return sortUp(<%= String.valueOf(tmodePrivate) %>);">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return sortDown(<%= String.valueOf(tmodePrivate) %>);">
    </td>
    </tr>
    </table>
  </td>
  </tr>
  </logic:notEqual>
  <% } %>

<% if (rtMode == tmodeAll) { %>
  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1" height="15" border="0" alt="">
  </td>
  </tr>
<% } %>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="15%" class="td_type25_2" align="center"><span class="text_todo_head"><gsmsg:write key="cmn.category.select" /></span></td>
    <td class="td_type1">
      <html:select property="rng060SelectCatUsr" onchange="buttonPush('init');">
        <html:optionsCollection property="rng060CategoryListPrivate" value="value" label="label" />
      </html:select>
    </td>
<% if (rtMode == tmodeShare || rtMode == tmodePrivate) { %>
    <logic:notEqual name="rng060Form" property="rng060SelectCatUsr" value="0">
    <logic:notEqual name="rng060Form" property="rng060SelectCatUsr" value="-1">
    <td width="30px" class="td_type1">
      <input type="button" name="btn_facilities_mnt" class="btn_edit_n1" value="<gsmsg:write key="rng.04" />" onClick="addEditCategory('<bean:write name="rng060Form" property="rng060SelectCatUsr" />', <%= tCmdEdit %>, 'addeditcategory')">
    </td>
    </logic:notEqual>
    </logic:notEqual>
    <td width="30px" class="td_type1">
      <input type="button" name="btn_cat_list" class="btn_cate_list" value="<gsmsg:write key="cmn.categorylist" />" onClick="buttonPush('rng150')">
    </td>
    <td width="30px" class="td_type1">
      <input type="button" name="btn_add_cat" class="btn_folder_n1" value="<gsmsg:write key="rng.rng060.02" />" onClick="addEditCategory('-1', <%= tCmdAdd %>, 'addeditcategory')">
    </td>
<% } %>
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

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
<% if (rtMode != tmodeAll) { %>
    <th align="center" class="table_bg_7D91BD sort_radio_are_usr" width="0%" nowrap>&nbsp;</th>
<% } %>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.category.belong" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.personal.template" /></span></th>
    </tr>
<bean:define id="mod2" value="0" />
<logic:iterate id="template2" name="rng060Form" property="rng060tplListPrivate" indexId="idx">
  <logic:equal name="mod2" value="<%= String.valueOf(idx.intValue() % 2) %>">
    <bean:define id="tblColor" value="td_type1" />
  </logic:equal>
  <logic:notEqual name="mod2" value="<%= String.valueOf(idx.intValue() % 2) %>">
    <bean:define id="tblColor" value="td_type25_2" />
  </logic:notEqual>
    <bean:define id="rtpSidPrivate" name="template2" property="rtpSid" type="java.lang.Integer" />
    <% String strRtpSidPrivate = String.valueOf(rtpSidPrivate.intValue()); %>

    <tr>
    <% if (rtMode == tmodePrivate) { %>
    <td align="left" class="<bean:write name="tblColor" /> sort_radio_are_usr" width="0%" nowrap>
      <html:radio property="rng060SortRadioPrivate" value="<%= strRtpSidPrivate %>" />
    </td>
    <% } %>
    <td align="left" class="<bean:write name="tblColor" />" width="0%" nowrap>
      <bean:write name="template2" property="rtcName" />
    </td>
    <td align="left" class="<bean:write name="tblColor" />" width="100%" nowrap>
       <a href="#" onClick="selectTemplate('<%= strRtpSidPrivate %>', <%= tCmdEdit %>, '060title')"><span class="text_link"><bean:write name="template2" property="rtpTitle" /></span></a>
    </td>
    </tr>
</logic:iterate>
    </table>

  </td>
  </tr>
<% } %>
  <tr>
  <td>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td width="100%" align="right">
<% if (rtMode == tmodeShare || rtMode == tmodePrivate) { %>
  <% if (rtMode == tmodeShare) { %>
          <input type="button" name="btn_facilities_mnt" class="btn_template" value="<gsmsg:write key="cmn.add.template" />" onClick="selectTemplate('-1', <%= tCmdAdd %>, 'rng090', '<bean:write name="rng060Form" property="rng060SelectCat" />')">
  <% } else { %>
          <input type="button" name="btn_facilities_mnt" class="btn_template" value="<gsmsg:write key="cmn.add.template" />" onClick="selectTemplate('-1', <%= tCmdAdd %>, 'rng090', '<bean:write name="rng060Form" property="rng060SelectCatUsr" />')">
  <% } %>
<% } %>
<% if (rtMode == tmodeShare) { %>
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('060back')">
<% } else { %>
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('060back')">
<% } %>
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