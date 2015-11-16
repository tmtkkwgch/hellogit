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
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="cmn.zaiseki.management" /></title>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk080">
<input type="hidden" name="CMD">
<html:hidden name="zsk080Form" property="backScreen" />
<html:hidden name="zsk080Form" property="selectZifSid" />
<html:hidden name="zsk080Form" property="uioStatus" />
<html:hidden name="zsk080Form" property="uioStatusBiko" />
<html:hidden name="zsk080Form" property="sortKey" />
<html:hidden name="zsk080Form" property="orderKey" />

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
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="zsk.zsk080.03" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
        <input type="button" name="btn_shinsei" class="btn_ok1" value="OK" onClick="buttonPush('zsk080kn');">
        <input type="button" name="btn_shinsei" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('zsk070');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>
  </td>
  </tr>

  <tr>
    <td>
    <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr><td align="left"><html:errors/><br></td></tr>
    </table>
    </logic:messagesPresent>
    </td>
  </tr>

  <tr>
  <td>
  <table class="tl0" width="100%" cellpadding="5">

  <logic:empty name="zsk080Form" property="zasekiList" scope="request">
  <tr>
    <td><span class="text_base"><gsmsg:write key="zsk.zsk080.01" /></span></td>
  </tr>
  </logic:empty>
  <logic:notEmpty name="zsk080Form" property="zasekiList" scope="request">
  <tr>
    <td colspan="3"><span class="text_base"><gsmsg:write key="zsk.zsk080.02" /></span></td>
  </tr>
  <tr>
    <th width="60%" align="center" class="table_bg_7D91BD" colspan="2"><span class="text_tlw"><gsmsg:write key="zsk.29" /></span></th>
    <th width="20%" align="center" class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="zsk.25" /></span></th>
  </tr>
  <logic:iterate id="zsk030Mdl" name="zsk080Form" property="zasekiList" scope="request" indexId="idx">
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

  <tr>
    <bean:define id="sid" name="zsk030Mdl" property="zifSid" />

    <td class="<%= tdColor %>" width="1%" align="center">
    <html:radio idName="zsk030Mdl" property="dfZifSid" value="zifSid" /></td>

    <td class="<%= tdColor %>">
    <bean:write name="zsk030Mdl" property="zifName" />
    </td>
    <td align="center" class="<%= tdColor %>"><bean:write name="zsk030Mdl" property="lastUpdateDate" /></td>
  </tr>

  </logic:iterate>

  </logic:notEmpty>

  </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">

    <table width="100%" cellspacing="0" cellpadding="0" border="0">
    <tr>
      <td align="right">
        <input type="button" name="btn_shinsei" class="btn_ok1" value="OK" onClick="buttonPush('zsk080kn');">
        <input type="button" name="btn_shinsei" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('zsk070');">
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