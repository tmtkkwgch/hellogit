<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="ntp.1" /> [<gsmsg:write key="ntp.1" /><gsmsg:write key="cmn.import" /><gsmsg:write key="cmn.check" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../nippou/js/ntp110.js"></script>
<script language="JavaScript" src="../common/js/cmn110.js"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>
</head>

<body class="body_03">
<html:form action="/nippou/ntp062kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp062InitFlg" />

<logic:notEmpty name="ntp062knForm" property="ntp061ChkShohinSidList" scope="request">
<logic:iterate id="item" name="ntp062knForm" property="ntp061ChkShohinSidList" scope="request">
  <input type="hidden" name="ntp061ChkShohinSidList" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="ntp062knForm" property="sv_users" scope="request">
<logic:iterate id="ulBean" name="ntp062knForm" property="sv_users" scope="request">
<input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">

<tr>
<td width="100%" align="center">
  <table width="70%" class="tl0">

  <tr>
  <td align="left">

<!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_anken_01.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.11" /><gsmsg:write key="cmn.import" /></span></td>
    <td width="100%" class="header_white_bg"></td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('doImport');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_import_input');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
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
  <td><span class="text_r1"><gsmsg:write key="main.man028kn.3" /></span></td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.src.34" />名</span></td>
    <td class="td_type1" width="80%">
    <a href="../nippou/ntp062kn.do?CMD=downLoad">
    <span class="text_link_min"><bean:write name="ntp062knForm" property="ntp062knFileName" /></span>
    </a>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.capture.item.count" /></span></td>
    <td class="td_type1"><bean:write name="ntp062knForm" property="impDataCnt" /><gsmsg:write key="cmn.number" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.staff" /></span></td>
    <td class="td_type1">
      <logic:notEmpty name="ntp062knForm" property="ntp061SelectUsrLavel" scope="request">
        <logic:iterate id="urBean" name="ntp062knForm" property="ntp061SelectUsrLavel" scope="request">
           <bean:write name="urBean" property="usiSei" scope="page"/>　<bean:write name="urBean" property="usiMei" scope="page"/><br>
          </logic:iterate>
       </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="ntp.58" /></span></td>
    <td class="td_type1">
      <logic:notEmpty name="ntp062knForm" property="ntp061ShohinList">
        <logic:iterate id="shohinMdl" name="ntp062knForm" property="ntp061ShohinList" scope="request">
           <bean:write name="shohinMdl" property="label" /><br>
          </logic:iterate>
       </logic:notEmpty>
    </td>
    </tr>
    </table>
  </td>
  </tr>


  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('doImport');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('back_to_import_input');">
      </td>
      </tr>
      </table>
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