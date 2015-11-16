<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [<gsmsg:write key="reserve.rsv260kn.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv260kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv260kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv260knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv260knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv260knForm" property="saveUser" scope="request">
  <logic:iterate id="usrSid" name="rsv260knForm" property="saveUser" scope="request">
    <input type="hidden" name="saveUser" value="<bean:write name="usrSid" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv260Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv260Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv260SelectedSisetuKbn" />
<html:hidden property="rsv260SelectedGrpComboSid" />
<html:hidden property="rsv260GrpAdmKbn" />
<html:hidden property="rsv260updateFlg" />
<html:hidden property="rsv260createGrpFlg" />

<input type="hidden" name="helpPrm" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv260kn.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('doImport');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_group_input');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td><span class="text_r1"><gsmsg:write key="cmn.capture.file.sure" /></span></td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1" width="100%"><bean:write name="rsv260knForm" property="rsv260knSelectedSisetuName" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.110" /></span></td>
    <td align="left" class="td_type1">
    <a href="../reserve/rsv260kn.do?CMD=downLoad">
      <bean:write name="rsv260knForm" property="rsv260knFileName" />
    </a>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.admin" /></span>
    <td align="left" class="td_type1">
      <logic:equal name="rsv260knForm" property="rsv260GrpAdmKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_OK) %>"><gsmsg:write key="reserve.50" /></logic:equal>
      <logic:equal name="rsv260knForm" property="rsv260GrpAdmKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_NO) %>"><gsmsg:write key="reserve.51" /></logic:equal>
    <logic:notEmpty name="rsv260knForm" property="rsv260knKanriUser" scope="request">
      <br>
      <br>
      <logic:iterate id="usr" name="rsv260knForm" property="rsv260knKanriUser" scope="request" indexId="idx">
        <bean:write name="usr" property="label" /><br>
      </logic:iterate>
    </logic:notEmpty>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span></td>
    <td align="left" class="td_type1">
      <logic:equal name="rsv260knForm" property="rsv260updateFlg" value="1"><gsmsg:write key="reserve.125" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.126" /></span></td>
    <td align="left" class="td_type1">
      <logic:equal name="rsv260knForm" property="rsv260createGrpFlg" value="1"><gsmsg:write key="reserve.127" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.capture.item.count" /></span></td>
    <td align="left" class="td_type1"><bean:write name="rsv260knForm" property="impDataCnt" /></td>
    </tr>

    </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('doImport');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_group_input');">
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