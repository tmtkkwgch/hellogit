<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />[ <gsmsg:write key="reserve.rsv210kn.1" /> ]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv210kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv210InitFlg" />
<html:hidden property="rsv210Mokuteki" />
<html:hidden property="rsv210SelectedHourFr" />
<html:hidden property="rsv210SelectedMinuteFr" />
<html:hidden property="rsv210SelectedHourTo" />
<html:hidden property="rsv210SelectedMinuteTo" />
<html:hidden property="rsv210Naiyo" />
<html:hidden property="rsv210RsyEdit" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv210knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv210knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv210knForm" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv210knForm" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv210SchKbn" />
<html:hidden property="rsv210SchGroupSid" />

<html:hidden property="rsv210GroupSid" />
<logic:notEmpty name="rsv210knForm" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="rsv210knForm" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv210kn.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('ikkatu_toroku_kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_ikkatu_input');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:1px; height:2px;" border="0" alt="">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td></tr>
  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="reserve.122" /></span></td>
    <td align="left" class="td_type1" width="80%">

      <table width="100%">

      <logic:iterate id="day" name="rsv210knForm" property="rsvIkkatuTorokuHiddenList" scope="request">

        <tr>
        <td class="text_hid_day"><bean:write name="day" property="hidDayStr" /></td>
        </tr>

        <logic:iterate id="grp" name="day" property="grpList">
        <tr>
        <td class="text_hid_grp_lpad">■<bean:write name="grp" property="rsgName" /></td>
        </tr>

        <logic:iterate id="sisetu" name="grp" property="sisetuList">
        <tr>
        <td width="100%" class="td_sisetu_lpad"><span class="text_base"><bean:write name="sisetu" property="rsdName" /></span></td>
        </tr>
        </logic:iterate>

        <tr>
        <td><img src="../common/images/spacer.gif" width="1" height="3"></td>
        </tr>

        </logic:iterate>

      </logic:iterate>

      </table>

    </td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td><br></td>
  </tr>
  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="td_type1" width="80%"><span class="text_base_rsv"><bean:write name="rsv210knForm" property="rsv210Torokusya" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.72" /></span></td>
    <td align="left" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv210knForm" property="rsv210Mokuteki" /></span></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.time" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <gsmsg:write key="cmn.start" />：&nbsp;<bean:write name="rsv210knForm" property="rsv210knTimeFr" /><br>
        <gsmsg:write key="cmn.end" />：&nbsp;<bean:write name="rsv210knForm" property="rsv210knTimeTo" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv"><bean:write name="rsv210knForm" property="rsv210knNaiyo" filter="false" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span></td>
    <td align="left" class="td_type1">
      <span class="text_base_rsv">
        <logic:equal name="rsv210knForm" property="rsv210RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_NONE) %>"><gsmsg:write key="cmn.nolimit" /></logic:equal>
        <logic:equal name="rsv210knForm" property="rsv210RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_PER_AND_ADU) %>"><gsmsg:write key="cmn.only.principal.or.registant" /></logic:equal>
        <logic:equal name="rsv210knForm" property="rsv210RsyEdit" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.EDIT_AUTH_GRP_AND_ADU) %>"><gsmsg:write key="cmn.only.affiliation.group.membership" /></logic:equal>
      </span>
    </td>
    </tr>

    <logic:notEmpty name="rsv210knForm" property="userNameArray" scope="request">
      <tr>
      <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="schedule.3" /></span></td>
      <td align="left" class="td_type1">
        <logic:iterate id="usr" name="rsv210knForm" property="userNameArray" scope="request">
          <span class="text_base_rsv">
            <bean:write name="usr" property="usisei" />&nbsp;&nbsp;<bean:write name="usr" property="usimei" /><br>
          </span>
        </logic:iterate>
      </td>
      </tr>
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
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('ikkatu_toroku_kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_ikkatu_input');">
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