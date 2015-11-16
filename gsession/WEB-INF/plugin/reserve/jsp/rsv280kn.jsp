<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="reserve.rsv280kn.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/reserve/rsv280kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsv280EditKbn" />
<html:hidden property="rsv280Edit" />
<html:hidden property="rsv280initFlg" />

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv280knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv280knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv280knForm" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv280knForm" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="reserve.rsv280kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('rsv280knkakutei');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('rsv280knback');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <% String editRow = ""; %>
    <logic:equal name="rsv280knForm" property="rsv280EditKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.ADM_SORTKBN_ADM) %>">
    <% editRow = " rowspan=\"2\""; %>
    </logic:equal>

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" nowrap id="rsvEditArea1"<%= editRow %>>
      <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <logic:equal name="rsv280knForm" property="rsv280EditKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RAC_INIEDITKBN_ADM) %>"><span class="text_base6_2"><gsmsg:write key="cmn.set.the.admin" /></span></logic:equal>
      <logic:equal name="rsv280knForm" property="rsv280EditKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RAC_INIEDITKBN_USER) %>"><span class="text_base6_2"><gsmsg:write key="cmn.set.eachuser" /></span></logic:equal>
    </td>
    </tr>

    <logic:equal name="rsv280knForm" property="rsv280EditKbn" value="<%= String.valueOf(jp.groupsession.v2.zsk.GSConstZaiseki.ADM_SORTKBN_ADM) %>">
    <tr>
    <td valign="middle" align="left" class="td_wt" id="rsvEditArea2">
      <span class="text_base2">
      <logic:equal name="rsv280knForm" property="rsv280Edit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>" ><gsmsg:write key="cmn.nolimit" /></logic:equal>
      <logic:equal name="rsv280knForm" property="rsv280Edit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>" ><gsmsg:write key="cmn.only.principal.or.registant" /></logic:equal>
      <logic:equal name="rsv280knForm" property="rsv280Edit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>" ><gsmsg:write key="cmn.only.affiliation.group.membership" /></logic:equal>
    </span>
    </td>
    </tr>
    </logic:equal>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('rsv280knkakutei');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('rsv280knback');">
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