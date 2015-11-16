<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int tCmdAdd = jp.groupsession.v2.man.GSConstPortal.CMD_MODE_ADD; %>
<% int tCmdEdit = jp.groupsession.v2.man.GSConstPortal.CMD_MODE_EDIT; %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="ptl.ptl110.1" /></title>
</head>

<!-- body -->
<body class="body_03">
<html:form action="/portal/ptl110">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortletSid" />
<html:hidden property="ptlCmdMode" />

<html:hidden property="ptl090category" />
<html:hidden property="ptl090svCategory" />

<input type="hidden" name="ptlPlcBack" value="">
<input type="hidden" name="ptlPtlCategorytSid" value="">

<logic:notEmpty name="ptl110Form" property="ptl110categoryList" scope="request">
  <logic:iterate id="sort" name="ptl110Form" property="ptl110categoryList" scope="request">
    <input type="hidden" name="ptl110categorySort" value="<bean:write name="sort" property="strPlcSort" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>

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

        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="cmn.categorylist" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="addEditCategoryWithFlg('0', '<%= tCmdAdd %>', 'ptl110add');">
        <input type="button" name="btn_back_ktool" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ptl110back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="<gsmsg:write key="cmn.spacer" />">
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush2('sortUp');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush2('sortDown');">
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
      <logic:iterate id="categoryMdl" name="ptl110Form" property="ptl110categoryList" indexId="idx">
        <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <bean:define id="plcSid" name="categoryMdl" property="plcSid" type="java.lang.Integer" />
        <bean:define id="plcSort" name="categoryMdl" property="plcSort" type="java.lang.Integer" />
        <% String strPlcSid = String.valueOf(plcSid); %>
        <% String strPlcSort = String.valueOf(plcSort); %>
        <% String radioValue = strPlcSid + ":" + strPlcSort; %>
        <tr>
          <td align="center" width="5%" class="<bean:write name="tblColor" />">
            <html:radio property="ptl110sortPltCategory" value="<%= radioValue %>" />
          </td>
          <td align="left" width="30%" class="<bean:write name="tblColor" />">
          <a href="#"  onClick="addEditCategoryWithFlg('<%= strPlcSid %>', '<%= tCmdEdit %>', 'ptl110edit')">
          <bean:write name="categoryMdl" property="plcName" /><a></td>
        </tr>
      </logic:iterate>
    </table>
  </td>
  </tr>

  <tr>
    <td>
      <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="<gsmsg:write key="cmn.spacer" />">
    </td>
  </tr>
  <tr>
  <td>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="<gsmsg:write key="cmn.spacer" />">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
      <td width="100%" align="right">
        <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush2('ptl110add');">
        <input type="button" name="btn_back_ktool" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush2('ptl110back');">
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

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>