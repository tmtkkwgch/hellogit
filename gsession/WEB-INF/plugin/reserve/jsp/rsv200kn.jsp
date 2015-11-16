<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" /> [ <gsmsg:write key="reserve.rsv200kn.1" /> ]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv200kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv200kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv080EditGrpSid" />
<html:hidden property="rsv080SortRadio" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv200knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv200knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv200Prop1Check" />
<html:hidden property="rsv200Prop2Check" />
<html:hidden property="rsv200Prop3Check" />
<html:hidden property="rsv200Prop4Check" />
<html:hidden property="rsv200Prop5Check" />
<html:hidden property="rsv200Prop6Check" />
<html:hidden property="rsv200Prop7Check" />
<html:hidden property="rsv200BikoCheck" />
<html:hidden property="rsv200Prop1Value" />
<html:hidden property="rsv200Prop2Value" />
<html:hidden property="rsv200Prop3Value" />
<html:hidden property="rsv200Prop4Value" />
<html:hidden property="rsv200Prop5Value" />
<html:hidden property="rsv200Prop6Value" />
<html:hidden property="rsv200Prop7Value" />
<html:hidden property="rsv200Biko" />

<logic:notEmpty name="rsv200knForm" property="rsv200TargetSisetu" scope="request">
  <logic:iterate id="targetSisetu" name="rsv200knForm" property="rsv200TargetSisetu" scope="request">
    <input type="hidden" name="rsv200TargetSisetu" value="<bean:write name="targetSisetu"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv200knForm" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv200knForm" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="0%">
      <img src="../reserve/images/header_reserve_01.gif" border="0" alt="<gsmsg:write key="cmn.reserve" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.reserve" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="reserve.rsv200kn.1" /> ]</td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('ikkatu_settei_kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_ikkatu_settei');">
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
  </td></tr>
  <tr>
  <td>
    <span class="text_base"><gsmsg:write key="reserve.rsv200kn.2" /></span>
  </td>
  </tr>
  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName4">
      <logic:equal name="rsv200knForm" property="rsv200Prop4Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName4" /></span></td>
      <td align="left" width="80%" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv200knForm" property="rsv200Prop4Value" /></span></td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName5">
      <logic:equal name="rsv200knForm" property="rsv200Prop5Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName5" /></span></td>
      <td align="left" width="80%" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv200knForm" property="rsv200Prop5Value" /></span></td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName1">
      <logic:equal name="rsv200knForm" property="rsv200Prop1Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName1" /></span></td>
      <td align="left" width="80%" class="td_type1"><span class="text_base_rsv"><bean:write name="rsv200knForm" property="rsv200Prop1Value" /></span></td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName2">
      <logic:equal name="rsv200knForm" property="rsv200Prop2Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName2" /></span></td>
      <td align="left" width="80%" class="td_type1">
        <span class="text_base_rsv">
          <logic:equal name="rsv200knForm" property="rsv200Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
          <logic:equal name="rsv200knForm" property="rsv200Prop2Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span>
      </td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName3">
      <logic:equal name="rsv200knForm" property="rsv200Prop3Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName3" /></span></td>
      <td align="left" width="80%" class="td_type1">
        <span class="text_base_rsv">
          <logic:equal name="rsv200knForm" property="rsv200Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
          <logic:equal name="rsv200knForm" property="rsv200Prop3Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span>
      </td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName7">
      <logic:equal name="rsv200knForm" property="rsv200Prop7Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName7" /></span></td>
      <td align="left" width="80%" class="td_type1">
        <span class="text_base_rsv">
          <logic:equal name="rsv200knForm" property="rsv200Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_KA) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
          <logic:equal name="rsv200knForm" property="rsv200Prop7Value" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_KBN_HUKA) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span>
      </td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:notEmpty name="rsv200knForm" property="rsv200PropHeaderName6">
      <logic:equal name="rsv200knForm" property="rsv200Prop6Check" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><bean:write name="rsv200knForm" property="rsv200PropHeaderName6" /></span></td>
      <td align="left" width="80%" class="td_type1">
        <span class="text_base_rsv">
        <logic:notEmpty name="rsv200knForm" property="rsv200Prop6Value">
          <bean:write name="rsv200knForm" property="rsv200Prop6Value" /><gsmsg:write key="cmn.days.after" />
        </logic:notEmpty>
        </span>
      </td>
      </tr>
      </logic:equal>
    </logic:notEmpty>

    <logic:equal name="rsv200knForm" property="rsv200BikoCheck" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROP_CHECK_YES) %>">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    <td align="left" width="80%" class="td_type1">
      <span class="text_base_rsv"><bean:write name="rsv200knForm" property="rsv200knBiko" filter="false" /></span>
    </td>
    </tr>
    </logic:equal>
    </table>

  </td>
  </tr>
  </table>

  <br>

  <table width="70%" class="tl0">
  <tr>
  <td align="left"><span class="text_base"><gsmsg:write key="reserve.rsv200kn.3" /></span></td>
  </tr>
  <tr>
  <th align="center" class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="reserve.121" /></span></th>
  </tr>

  <bean:define id="mod" value="0" />

  <logic:notEmpty name="rsv200knForm" property="rsv200SisetuList" scope="request">
    <logic:iterate id="sisetu" name="rsv200knForm" property="rsv200SisetuList" scope="request" indexId="idx">

    <tr>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type_rsv" />
    </logic:notEqual>
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type1" />
    </logic:equal>
    <td align="left" class="<bean:write name="tblColor" />">
      <bean:write name="sisetu" property="rsdName" /></td>
    </tr>

    </logic:iterate>
  </logic:notEmpty>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('ikkatu_settei_kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_ikkatu_settei');">
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