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
<script language="JavaScript" src="../main/js/man027kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/main/man027kn">
<html:hidden property="man020DspYear" />
<html:hidden property="editHltSid" />
<html:hidden property="man023CheckAll" />

<input type="hidden" name="CMD" value="">

<logic:notEmpty name="man027knForm" property="man023hltSid" scope="request">
<logic:iterate id="hltBean" name="man027knForm" property="man023hltSid" scope="request">
  <input type="hidden" name="man023hltSid" value="<bean:write name="hltBean" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man027kn.1" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.delete2" />" class="btn_dell_n1" onClick="buttonPush('delTempCommit')">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('backTemp')">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td colspan="2"><span class="text_r1"><gsmsg:write key="main.man027kn.2" /></span></td>

    </tr>
    </table>

    <table class="tl0 table_td_border"cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr class="td_type24">
    <th width="25%"><gsmsg:write key="cmn.date3" /></th>
    <th width="75%"><gsmsg:write key="cmn.holiday.name" /></th>
    </tr>

    <logic:notEmpty name="man027knForm" property="man023TemplateList" >
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"smail_td1", "smail_td2"}; %>
    <logic:iterate id="pageTemplateRecBean" name="man027knForm" property="man023TemplateList" indexId="idx">
    <% tdColor = tdColors[(idx % 2)]; %>
    <tr>
    <th class="<%= tdColor %>" align="center" nowrap><bean:write name="pageTemplateRecBean" property="viewDate" scope="page"/></td>
    <td class="<%= tdColor %>"><bean:write name="pageTemplateRecBean" property="hltName" scope="page"/></td>
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