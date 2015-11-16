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
<title>[Group Session] <gsmsg:write key="main.holiday.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../main/js/man020.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/main/man020">
<input type="hidden" name="CMD" value="">
<html:hidden name="man020Form" property="man020DspYear" />
<input type="hidden" name="editHolDate">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.holiday.setting" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"><input type="button" value="<gsmsg:write key="cmn.back.admin.setting" />" class="btn_back_n3" onClick="buttonPush('2');"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('7');">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonPush('3');">
      <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('4');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <table width="100%" class="tl0" cellpadding="5">
  <tr>
  <td width="100%" class="" align="left">
    <input type="button" value="<gsmsg:write key="main.holiday.template" />" class="btn_base1w" onClick="buttonPush('1');">
  </td>
  <td align="right" nowrap>
     <p align="right">
     <table width="0%" border="0">
     <tr>
     <td><input type="image" name="zday" src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.year" />" width="18" height="18" border="0" onClick="buttonPush('5');"></td>
     <td nowrap>
       <bean:define id="yr" name="man020Form" property="man020DspYear" type="java.lang.String" />
       <strong><gsmsg:write key="cmn.year" arg0="<%= yr %>" /></strong>
     </td>
     <td><input type="image" name="yday" src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.year" />" width="18" height="18" border="0" onClick="buttonPush('6');"></td>
     </tr>
     </table>
     </p>
  </td>
  </tr>
  </table>

  <!-- <gsmsg:write key="cmn.error.message" /> -->
  <logic:messagesPresent message="false">
    <span class="TXT02"><html:errors/></span>
  </logic:messagesPresent>

  <table width="100%" class="tl0 table_td_border" cellpadding="5">
  <tr class="td_type24">
  <th align="center" width="0%">&nbsp;</th>
  <th align="center" width="30%"><gsmsg:write key="cmn.date2" /></th>
  <th align="center" width="70%"><gsmsg:write key="cmn.holiday.name" /></th>
  <th align="center" width="0%">&nbsp;</th>
  </tr>

  <logic:notEmpty name="man020Form" property="man020HolList">
  <bean:define id="tdColor" value="" />
  <% String[] tdColors = new String[] {"td_type1", "td_type3"}; %>
  <logic:iterate id="holMdl" name="man020Form" property="man020HolList" indexId="idx">
  <% tdColor = tdColors[(idx.intValue() % 2)]; %>

  <tr>
  <td align="center" class="<%= tdColor %>" width="5%">
  <html:multibox name="man020Form" property="holDate">
    <bean:write name="holMdl" property="strDate" />
  </html:multibox>
  </td>
  <th align="center" class="<%= tdColor %>" width="20%"><bean:write name="holMdl" property="viewDate" /></th>
  <td class="<%= tdColor %>" width="70%"><bean:write name="holMdl" property="holName" /></td>
  <td align="center" class="<%= tdColor %>" width="5%">
    <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="editHol(<bean:write name="holMdl" property="strDate" />);">
  </td>
  </tr>

  </logic:iterate>
  </logic:notEmpty>
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