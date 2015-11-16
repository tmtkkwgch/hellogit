<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String smailSendOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.SMAIL_SEND_OFF);
   String smailSendOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.SMAIL_SEND_ON);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" /> <gsmsg:write key="cmn.sml.notification.setting.kn" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03">

<html:form action="/file/fil260kn">
<input type="hidden" name="CMD" value="">

<html:hidden property="backDsp" />
<html:hidden property="backScreen" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />
<html:hidden property="filSearchWd" />
<html:hidden property="fil260initFlg" />
<html:hidden property="fil260smlSendKbn" />
<html:hidden property="fil260smlSend" />

<logic:notEmpty name="fil260knForm" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil260knForm" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil260knForm" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil260knForm" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.sml.notification.setting.kn" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onclick="buttonPush('fil260knok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil260knback');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top: 10px;">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <IMG SRC="./images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td width="20%" valign="middle" align="left" class="td_sub_title3" nowrap rowspan="2">
    <span class="text_bb1"><gsmsg:write key="shortmail.notification" /></span>
    </td>
    <td width="80%" valign="middle" align="left" class="td_wt">
      <span class="text_base">
      <logic:equal name="fil260knForm" property="fil260smlSendKbn" value="<%= String.valueOf(jp.groupsession.v2.fil.GSConstFile.FAC_SMAIL_SEND_KBN_ADMIN) %>"><gsmsg:write key="cmn.set.the.admin" /></logic:equal>
      <logic:equal name="fil260knForm" property="fil260smlSendKbn" value="<%= String.valueOf(jp.groupsession.v2.fil.GSConstFile.FAC_SMAIL_SEND_KBN_USER) %>"><gsmsg:write key="cmn.set.eachuser" /></logic:equal>
      </span>
    </td>
    </tr>

<logic:equal name="fil260knForm" property="fil260smlSendKbn" value="<%= String.valueOf(jp.groupsession.v2.fil.GSConstFile.FAC_SMAIL_SEND_KBN_ADMIN) %>">
    <tr>
    <td valign="middle" align="left" class="td_wt" style="border-collapse: collapse;" id="smlNoticeKbnArea">
      <span class="text_base">
      <logic:equal name="fil260knForm" property="fil260smlSend" value="<%= String.valueOf(jp.groupsession.v2.fil.GSConstFile.FAC_SMAIL_SEND_NO) %>"><gsmsg:write key="cmn.dont.notify" /></logic:equal>
      <logic:equal name="fil260knForm" property="fil260smlSend" value="<%= String.valueOf(jp.groupsession.v2.fil.GSConstFile.FAC_SMAIL_SEND_YES) %>"><gsmsg:write key="cmn.notify" /></logic:equal>
      </span>
    </td>
    </tr>
</logic:equal>
    </table>

    <table cellpadding="0" cellspacing="0" width="100%" style="margin-top: 10px;">
    <tr>
    <td align="right">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onclick="buttonPush('fil260knok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil260knback');">
    </td>
    </tr>
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