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
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="ptl.2" /></title>
</head>

<!-- body -->
<body class="body_03">
<html:form action="/portal/ptl140">
<input type="hidden" name="CMD" value="init">
<logic:notEmpty name="ptl140Form" property="ptl140portalList" scope="request">
  <logic:iterate id="sort" name="ptl140Form" property="ptl140portalList" scope="request">
    <input type="hidden" name="arrayPtl140sortPortal" value="<bean:write name="sort" property="strPtsSort" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.2" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('menuBack');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>


  <logic:equal name="ptl140Form" property="ptlDefPow" value="1" >
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="25%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="ptl.18" /></span></td>
    <td class="td_type1">
      <html:radio name="ptl140Form" property="ptlType" value="0" styleId="ptlInitType0" onclick="buttonPush('changeKbn');"><label for="ptlInitType0"><gsmsg:write key="ptl.19" /></label></html:radio>
      &nbsp;&nbsp;<html:radio name="ptl140Form" property="ptlType" value="1" styleId="ptlInitType1" onclick="buttonPush('changeKbn');"><label for="ptlInitType1"><gsmsg:write key="ptl.20" /></label></html:radio>
    </tr>
    </table>
    <img src="../common/images/spacer.gif" style="width:100px; height:20px;" border="0" alt="">
  </td>
  </tr>
  </logic:equal>
  <logic:equal name="ptl140Form" property="ptlSortPow" value="1" >

  <tr>
  <td align="left">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" onclick="buttonPush('sortUp');">
      <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" onclick="buttonPush('sortDown');"><br>
  </td>
  </tr>

  <tr><td><img src="../common/images/spacer.gif" style="width:0px; height:5px;" border="0" alt="space"></td></tr>
  <tr>
  <td>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th class="table_bg_7D91BD" width="6%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="35%"><span class="text_tlw"><gsmsg:write key="ptl.4" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="59%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>
      <bean:define id="mod1" value="0" />

      <logic:iterate id="portalMdl" name="ptl140Form" property="ptl140portalList" indexId="idx">
        <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <bean:define id="ptlSid" name="portalMdl" property="ptlSid" type="java.lang.Integer" />
        <bean:define id="ptsSort" name="portalMdl" property="ptsSort" type="java.lang.Integer" />
        <% String strPtlSid = String.valueOf(ptlSid); %>
        <% String strPtsSort = String.valueOf(ptsSort); %>
        <% String radioValue = strPtlSid + ":" + strPtsSort; %>
        <tr>
          <td align="center" width="5%" class="<bean:write name="tblColor" />">
            <html:radio property="ptl140sortPortal" value="<%= radioValue %>" />
          </td>

          <td align="left" width="20%" class="<bean:write name="tblColor" />" >
            <bean:write name="portalMdl" property="ptlName" />
          </td>

          <td align="left" width="75%" class="<bean:write name="tblColor" />" nowrap>
            <bean:write name="portalMdl" property="ptlDescription" filter="false" />
          </td>
        </tr>
      </logic:iterate>

    </table>

  </td>
  </tr>
  </logic:equal>

  <tr>
  <td>

    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('menuBack');">
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