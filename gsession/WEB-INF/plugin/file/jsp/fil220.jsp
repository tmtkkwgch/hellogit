<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String accessOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.ACCESS_KBN_OFF);
   String accessOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.ACCESS_KBN_ON);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="fil.62" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/file.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../file/js/fil220.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/file/fil220">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="cmnMode" value="">
<html:hidden property="backScreen" />
<html:hidden property="backDsp" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil030SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="filSearchWd" />

<logic:notEmpty name="fil220Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil220Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil220Form" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil220Form" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
  <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
  <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="fil.62" /> ]</td>
  <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
  </tr>
  </table>

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td width="50%">
  <logic:notEmpty name="fil220Form" property="fil220cabinetList">
  <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" onclick="buttonPush('fil220up');">
  <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" onclick="buttonPush('fil220down');">
  </logic:notEmpty>
  </td>
  <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>

  <td class="header_glay_bg" width="50%">
  <logic:notEmpty name="fil220Form" property="fil220cabinetList">
  <input type="button" class="btn_base1" value="<gsmsg:write key="fil.fil220.1" />" onclick="CabinetDetailMulti();">
  </logic:notEmpty>
  <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil220back');">
  </td>
  <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
  </tr>
  </table>

  <logic:messagesPresent message="false">
  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr><td width="100%"><html:errors /></td></tr>
  </table>
  </logic:messagesPresent>

  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <logic:notEmpty name="fil220Form" property="fil220cabinetList">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td align="left"><span class="text_base">※<gsmsg:write key="fil.fil220.2" /></span></td>
  </tr>
  </table>

  <!-- ページコンテンツ start -->
  <table cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td width="100%" valign="top">

    <!-- 一覧 -->
    <table class="tl0 prj_tbl_base3" width="100%" cellpadding="0" cellspacing="0">

    <tr>
    <th width="5%" class="td_type_file"></th>
    <th width="5%" class="td_type_file"><html:checkbox property="fil220allCheck" onclick="changeChk();" /></th>
    <th width="75%" class="td_type_file"><span class="text_prjtodo_list_head"><gsmsg:write key="cmn.name4" /></span></th>
    <th width="15%" class="td_type_file"><span class="text_prjtodo_list_head"><gsmsg:write key="fil.103" /></span></th>
    <th width="5%" class="td_type_file"></th>
    </tr>

    <% String[] td_colors = new String[] {"td_type_file1", "td_type_file2"}; %>

    <logic:iterate id="cabinetModel" name="fil220Form" property="fil220cabinetList" indexId="idx">

    <bean:define id="cabinetSid" name="cabinetModel" property="fcbSid" />

    <tr class="<%= td_colors[idx.intValue() % 2] %>">
    <td class="prj_td" align="center">
      <html:radio property="fil220sltRadio" value="<%= String.valueOf(cabinetSid) %>" />
    </td>
    <td class="prj_td" align="center">
      <html:multibox name="fil220Form" property="fil220sltCheck" value="<%= String.valueOf(cabinetSid) %>" />
    </td>
    <td class="prj_td" align="left">
      <img src="../file/images/cabinet.gif" border="0" alt="" style="vertical-align:bottom;">&nbsp;
      <span class="text_base"><bean:write name="cabinetModel" property="fcbName" /></span>
    </td>
    <td class="prj_td" align="center">
      <logic:equal name="cabinetModel" property="fcbAccessKbn" value="<%= accessOn %>">
      <img src="../file/images/file_lock.gif" width="20" height="20" border="0">
      </logic:equal>
    </td>
    <td class="prj_td" align="center">
      <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="CabinetDetail('<bean:write name="cabinetModel" property="fcbSid" />');">
    </td>
    </tr>

    </logic:iterate>

    </table>

    <img src="../file/images/file_lock.gif" width="20" height="20" border="0" alt="" style="vertical-align:bottom;">&nbsp;<span class="text_base">：<gsmsg:write key="fil.fil220.3" /></span>
  </td>
  </tr>
  </table>

  <table cellpadding="0" cellspacing="0" width="70%">
  <tr>
  <td align="right">
    <input type="button" class="btn_base1" value="<gsmsg:write key="fil.fil220.1" />" onclick="CabinetDetailMulti();">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil220back');">
  </td>
  </tr>
  </table>
  </logic:notEmpty>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>
