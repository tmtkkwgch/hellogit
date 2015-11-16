<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int tCmdAdd = jp.groupsession.v2.man.GSConstPortal.CMD_MODE_ADD; %>
<% int tCmdEdit = jp.groupsession.v2.man.GSConstPortal.CMD_MODE_EDIT; %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../portal/js/ptl090.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="ptl.9" /></title>
</head>

<body class="body_03">

<html:form action="portal/ptl090">
<input type="hidden" name="CMD" value="">
<html:hidden property="ptl090svCategory" />
<html:hidden property="ptlPortletSid" />
<html:hidden property="ptlCmdMode" />

<logic:notEmpty name="ptl090Form" property="ptl090portletlist" scope="request">
  <logic:iterate id="sort" name="ptl090Form" property="ptl090portletlist" scope="request">
    <input type="hidden" name="arrayPtl090sortPortlet" value="<bean:write name="sort" property="strPltSort" />">
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
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.9" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_to_portal" class="btn_portal_n1" value="<gsmsg:write key="ptl.2" />" onClick="buttonPush('portalManager')">
          <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="editPortlet('ptl090addPortlet', -1, '<%= tCmdAdd %>')">
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('confMenu')">
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
  <td><span class="text_base"><gsmsg:write key="ptl.ptl090.1" /></span></td>
  </tr>
  <tr>
  <td>
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="15%" class="td_type25_2" align="center" nowrap	><span class="text_todo_head"><gsmsg:write key="cmn.category.select" /></span></td>
    <td class="td_type1">
      <html:select property="ptl090category" onchange="buttonPush('init');">
        <html:optionsCollection property="ptl090CategoryList" value="value" label="label" />
      </html:select>
    <td width="30px" class="td_type1">
      <input type="button" name="btn_cat_list" class="btn_cate_list" value="<gsmsg:write key="cmn.categorylist" />" onClick="buttonPush('ptl090categoryList');">
    </td>
    <td width="110px" class="td_type1">
      <input type="button" name="btn_add_cat" class="btn_folder_n1" value="<gsmsg:write key="rng.rng060.02" />" onClick="addCategory('ptl090addCategory', '0');">
    </td>
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
  <td align="left">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush('sortUp');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />"" name="btn_downer" onClick="buttonPush('sortDown');">
  </td>
  </tr>

  <tr><td><img src="../common/images/spacer.gif" style="width:0px; height:5px;" border="0" alt="<gsmsg:write key="cmn.spacer" />"></td></tr>

  <tr>
  <td>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.category.belong" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="ptl.3" /></span></th>
    </tr>
      <bean:define id="mod1" value="0" />
      <logic:iterate id="portletMdl" name="ptl090Form" property="ptl090portletlist" indexId="idx">
        <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <bean:define id="pltSid" name="portletMdl" property="pltSid" type="java.lang.Integer" />
        <bean:define id="pltSort" name="portletMdl" property="pltSort" type="java.lang.Integer" />
        <% String strPltSid = String.valueOf(pltSid); %>
        <% String strPltSort = String.valueOf(pltSort); %>
        <% String radioValue = strPltSid + ":" + strPltSort; %>
        <tr>
          <td align="center" width="5%" class="<bean:write name="tblColor" />">
            <html:radio property="ptl090sortPortlet" value="<%= radioValue %>" />
          </td>

          <td align="left" width="20%" class="<bean:write name="tblColor" />" >
            <bean:write name="portletMdl" property="plcName" />
          </td>

          <td align="left" width="75%" class="<bean:write name="tblColor" />" nowrap>
            <a href="#"  onClick="editPortlet('ptl090addPortlet', '<bean:write name="portletMdl" property="pltSid" />', '<%= tCmdEdit %>')"><bean:write name="portletMdl" property="pltName" /><a>
          </td>
        </tr>
      </logic:iterate>
    </table>

  </td>
  </tr>

   <tr>
     <td>
      <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="<gsmsg:write key="cmn.spacer" />">
      <table width="100%" cellpadding="5" cellspacing="0">
      <tr>
        <td width="100%" align="right">
          <input type="button" name="btn_to_portal" class="btn_portal_n1" value="<gsmsg:write key="ptl.2" />" onClick="buttonPush('portalManager')">
          <input type="button" name="btn_facilities_mnt" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="editPortlet('ptl090addPortlet', -1, '<%= tCmdAdd %>')">
          <input type="button" name="btn_back_ktool" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('confMenu')">
        </td>
      </tr>
      </table>
     </td>
   </tr>
  </table>
</td></tr>

</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>