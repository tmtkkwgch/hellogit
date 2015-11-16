<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.preferences2" />[<gsmsg:write key="cmn.display.settings.kn" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../reserve/js/rsv150kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../reserve/css/reserve.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/reserve/rsv150kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="rsvBackPgId" />
<html:hidden property="rsvDspFrom" />
<html:hidden property="rsvSelectedGrpSid" />
<html:hidden property="rsvSelectedSisetuSid" />
<html:hidden property="rsv150SelectedGrpSid" />
<html:hidden property="rsv150initDspFlg" />
<html:hidden property="rsv150DispItem1" />
<html:hidden property="rsv150DispItem2" />
<html:hidden property="rsv150ReloadTime" />
<html:hidden property="rsv150ImgDspKbn" />
<html:hidden property="rsv150DefDsp" />

<%@ include file="/WEB-INF/plugin/reserve/jsp/rsvHidden.jsp" %>

<logic:notEmpty name="rsv150knForm" property="rsv100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="rsv150knForm" property="rsv100CsvOutField" scope="request">
    <input type="hidden" name="rsv100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="rsv150knForm" property="rsvIkkatuTorokuKey" scope="request">
  <logic:iterate id="key" name="rsv150knForm" property="rsvIkkatuTorokuKey" scope="request">
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.display.settings.kn" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="./images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('hyozi_settei_kakutei');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_hyozi_settei_input');">
    </td>
    <td width="0%"><img src="./images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
<logic:messagesPresent message="false">
      <tr>
         <td align="left"><html:errors/></td>
      </tr>
</logic:messagesPresent>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.initial.display" /></span></a></td>
    <td class="td_type1"><bean:write name="rsv150knForm" property="rsv150knDefDsp"/></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.99" /></span></a></td>
    <td class="td_type1"><bean:write name="rsv150knForm" property="rsv150knDispGroup"/></td>
    </tr>
    <tr>
    <td width="20%" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.100" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="reserve.rsv150kn.2" /></span><br><br>
      <logic:notEmpty name="rsv150knForm" property="rsv150knDispItem1"><bean:write name="rsv150knForm" property="rsv150knDispItem1"/><br></logic:notEmpty>
      <logic:notEmpty name="rsv150knForm" property="rsv150knDispItem2"><bean:write name="rsv150knForm" property="rsv150knDispItem2"/><br></logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.auto.reload.time" /></span></a></td>
    <td class="td_type1"><bean:write name="rsv150knForm" property="rsv150knReloadTime"/></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="reserve.102" /></span></a></td>
    <td class="td_type1">
      <logic:equal name="rsv150knForm" property="rsv150ImgDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_ON) %>" ><gsmsg:write key="cmn.display.ok" /></logic:equal>
      <logic:equal name="rsv150knForm" property="rsv150ImgDspKbn" value="<%= String.valueOf(jp.groupsession.v2.rsv.GSConstReserve.SISETU_IMG_OFF) %>" ><gsmsg:write key="cmn.dont.show" /></logic:equal>
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
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('hyozi_settei_kakutei');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('back_to_hyozi_settei_input');">
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