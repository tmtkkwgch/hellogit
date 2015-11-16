<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<% int dspOn = jp.groupsession.v2.rsv.GSConstReserve.RSV_OVERTIME_DSP_ON; %>
<% int dspOff = jp.groupsession.v2.rsv.GSConstReserve.RSV_OVERTIME_DSP_OFF; %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.setting.main.view" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../reserve/js/rsv240.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="lmtEnableDisable();">
<html:form action="/reserve/rsv240">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv240Form" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv240Form" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv240InitFlg" />

<logic:notEmpty name="rsv240Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv240Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.main.view" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('rsv240ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.preferences" />" onclick="buttonPush('rsv240back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

  </td>
  </tr>

  <tr>
  <td><span class="text_base"><gsmsg:write key="reserve.rsv240.2" /></span></td>
  </tr>

  <tr>
  <td>
  <div id="lmtinput">
    <span class="text_r1"><gsmsg:write key="reserve.show.time.passed" /></span>
    <html:radio name="rsv240Form" property="rsv240overTimeDspKbn" value="<%= String.valueOf(dspOn) %>" styleId="rsv240overTimeDspKbn0" /><label for="rsv240overTimeDspKbn0"><span class="text_base"><gsmsg:write key="reserve.show.ok" /></span></label>&nbsp;
    <html:radio name="rsv240Form" property="rsv240overTimeDspKbn" value="<%= String.valueOf(dspOff) %>" styleId="rsv240overTimeDspKbn1" /><label for="rsv240overTimeDspKbn1"><span class="text_base"><gsmsg:write key="reserve.show.no" /></span></label>
  </div>
  </td>
  </tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="0%" nowrap><html:checkbox name="rsv240Form" property="rsv240AllCheck" value="1" onclick="changeChk();lmtEnableDisable();" /></th>
    <th align="center" class="table_bg_7D91BD" width="100%"><span class="text_tlw"><gsmsg:write key="cmn.group.name" /></span></th>
    </tr>

    <bean:define id="mod" value="0" />
    <logic:notEmpty name="rsv240Form" property="rsv240DspList" scope="request">
    <logic:iterate id="grpMdl" name="rsv240Form" property="rsv240DspList" scope="request" indexId="idx">

      <tr>

        <bean:define id="index" value="<%= String.valueOf(((Integer) idx).intValue()) %>" />
        <% String label_id = "label_" + idx.toString();  %>

        <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>
        <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>

      <td class="<bean:write name="tblColor" />" nowrap>
      <html:multibox name="rsv240Form" property="rsv240RsgSids" styleId="<%= label_id.toString() %>" onclick="lmtEnableDisable();">
        <bean:write name="grpMdl" property="rsgSid" />
      </html:multibox>
      </td>

      <td align="left" class="<bean:write name="tblColor" />">
        <label for="<%= label_id.toString() %>"><bean:write name="grpMdl" property="rsgName" /></label>
      </td>
      </tr>

    </logic:iterate>
    </logic:notEmpty>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('rsv240ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.preferences" />" onclick="buttonPush('rsv240back');">
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