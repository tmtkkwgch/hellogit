<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.reserve" />
  <logic:equal name="rsv060knForm" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>"> [<gsmsg:write key="reserve.rsv060kn.1" />]</logic:equal>
  <logic:equal name="rsv060knForm" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>"> [<gsmsg:write key="reserve.rsv060kn.2" />]</logic:equal>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../reserve/js/rsv060kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv060kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv050SortRadio" />
<html:hidden property="rsv060InitFlg" />
<html:hidden property="rsv060ProcMode" />
<html:hidden property="rsv060EditGrpSid" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv060knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv060knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="rsv060GrpId" />
<html:hidden property="rsv060GrpName" />
<html:hidden property="rsv060SelectedSisetuKbn" />
<html:hidden property="rsv060SelectedGrpComboSid" />
<html:hidden property="rsv060GrpAdmKbn" />
<html:hidden property="rsv060apprKbn" />

<html:hidden property="rsv060AccessKbn" />
<html:hidden property="rsv060groupSid" />
<html:hidden property="rsv060AccessDspFlg" />

<logic:notEmpty name="rsv060knForm" property="saveUser" scope="request">
  <logic:iterate id="usrSid" name="rsv060knForm" property="saveUser" scope="request">
    <input type="hidden" name="saveUser" value="<bean:write name="usrSid" />">
  </logic:iterate>
</logic:notEmpty>

<logic:equal name="rsv060knForm" property="rsv060AccessDspFlg" value="true">
<logic:notEmpty name="rsv060Form" property="rsv060memberSid">
<logic:iterate id="usid" name="rsv060Form" property="rsv060memberSid">
  <input type="hidden" name="rsv060memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv060Form" property="rsv060memberSidRead">
<logic:iterate id="usid" name="rsv060Form" property="rsv060memberSidRead">
  <input type="hidden" name="rsv060memberSidRead" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>
</logic:equal>

<logic:notEmpty name="rsv060Form" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv060Form" property="rsvIkkatuTorokuKey" scope="request">
    <input type="hidden" name="rsvIkkatuTorokuKey" value="<bean:write name="key"/>">
  </logic:iterate>
</logic:notEmpty>



<input type="hidden" name="helpPrm" value="<bean:write name="rsv060Form" property="rsv060ProcMode" />">

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
    <td width="100%" class="header_white_bg_text">
      <logic:equal name="rsv060knForm" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_SINKI) %>">[ <gsmsg:write key="reserve.rsv060kn.1" /> ]</logic:equal>
      <logic:equal name="rsv060knForm" property="rsv060ProcMode" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.PROC_MODE_EDIT) %>">[ <gsmsg:write key="reserve.rsv060kn.2" /> ]</logic:equal>
    </td>
    <td width="0%" class="header_white_bg">
      <img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../reserve/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('kakutei');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_sisetu_group_input');">
    </td>
    <td width="0%"><img src="../reserve/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr><td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td></tr>

  <tr>
  <td>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.name" /></span></td>
    <td align="left" class="td_type1" width="80%"><bean:write name="rsv060knForm" property="rsv060GrpName" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.id" /></span></td>
    <td align="left" class="td_type1"><bean:write name="rsv060knForm" property="rsv060GrpId" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.47" /></span></td>
    <td align="left" class="td_type1"><bean:write name="rsv060knForm" property="rsv060knSelectedSisetuName" /></td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.162" /></span></td>
    <td align="left" class="td_type1">
      <logic:equal name="rsv060knForm" property="rsv060GrpAdmKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_OK) %>"><gsmsg:write key="reserve.163" /></logic:equal>
      <logic:equal name="rsv060knForm" property="rsv060GrpAdmKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_ADM_KBN_NO) %>"><gsmsg:write key="reserve.164" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.admin" /></span>
    <td align="left" class="td_type1">
      <logic:notEmpty name="rsv060knForm" property="rsv060knKanriUser" scope="request">
      <logic:iterate id="usr" name="rsv060knForm" property="rsv060knKanriUser" scope="request" indexId="idx">
        <bean:write name="usr" property="label" /><br>
      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="reserve.appr.set.title" /></span></td>
    <td align="left" class="td_type1">
      <logic:equal name="rsv060knForm" property="rsv060apprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_APPR_KBN_APPR) %>"><gsmsg:write key="reserve.appr.set.kbn1" /></logic:equal>
      <logic:equal name="rsv060knForm" property="rsv060apprKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSG_APPR_KBN_SISETSU) %>"><gsmsg:write key="reserve.appr.set.kbn3" /></logic:equal>
    </td>
    </tr>

    <logic:equal name="rsv060knForm" property="rsv060AccessDspFlg" value="true">
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.access.auth" /></span>
    <td align="left" class="td_type1">



        <span class="text_bb1"><gsmsg:write key="cmn.howto.limit" /></span><br>
        <span class="text_base">&nbsp;
          <logic:equal name="rsv060knForm" property="rsv060AccessKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_PERMIT) %>"><gsmsg:write key="cmn.access.permit" /></logic:equal>
          <logic:equal name="rsv060knForm" property="rsv060AccessKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_LIMIT) %>"><gsmsg:write key="cmn.access.limit" /></logic:equal>
        </span>
        <br><br>

      <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center">
      <span class="text_bb1">
      <logic:equal name="rsv060knForm" property="rsv060AccessKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_PERMIT) %>">
        <gsmsg:write key="reserve.165" />
      </logic:equal>
      <logic:equal name="rsv060knForm" property="rsv060AccessKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_LIMIT) %>">
        <gsmsg:write key="reserve.161" />
      </logic:equal>
      </span>
      </td>
      </tr>
      <tr>
      <td align="left" class="td_type1">
        <logic:notEmpty name="rsv060knForm" property="rsv060knEditUser">
        <logic:iterate id="memNameEdit" name="rsv060knForm" property="rsv060knEditUser">
          <span class="text_base"><bean:write name="memNameEdit" property="label" /></span><br>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="rsv060knForm" property="rsv060knEditUser">&nbsp;</logic:empty>
      </td>
      </tr>

      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center">
      <span class="text_bb1">
      <logic:equal name="rsv060knForm" property="rsv060AccessKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_PERMIT) %>">
        <gsmsg:write key="cmn.reading.ng" />
      </logic:equal>
      <logic:equal name="rsv060knForm" property="rsv060AccessKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.RSV_ACCESS_MODE_LIMIT) %>">
        <gsmsg:write key="cmn.reading.ok" />
      </logic:equal>
      </span>
      </td>
      </tr>
      <tr>
      <td align="left" class="td_type1">
        <logic:notEmpty name="rsv060knForm" property="rsv060knReadUser">
        <logic:iterate id="memNameRead" name="rsv060knForm" property="rsv060knReadUser">
          <span class="text_base"><bean:write name="memNameRead" property="label" /></span><br>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="rsv060knForm" property="rsv060knReadUser">&nbsp;</logic:empty>


      </td>
      </tr>
      </table>

    </td>
    </tr>
    </logic:equal>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('kakutei');">
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