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
<meta http-equiv="Content-Type" content="text/html; charset=shift_jis">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="sml.19" /></title>
<script language="JavaScript" src="../smail/js/sml070.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/smail/sml070">

<logic:notEmpty name="sml070Form" property="sml070SmlList" scope="request">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">

  <logic:iterate id="msg" name="sml070Form" property="sml070SmlList" scope="request" indexId="idx">
  <tr>
  <td width="30" class="td_type16" align="center" nowrap>
    <logic:equal name="msg" property="smsMark" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL) %>">
      <img src="../smail/images/call.gif" alt="<gsmsg:write key="cmn.phone" />" border="0">
    </logic:equal>
    <logic:equal name="msg" property="smsMark" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP) %>">
      <img src="../smail/images/zyuu.gif" alt="<gsmsg:write key="sml.61" />" border="0">
    </logic:equal>
    <logic:notEqual name="msg" property="smsMark" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_TEL) %>">
      <logic:notEqual name="msg" property="smsMark" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.MARK_KBN_INP) %>">
        &nbsp;
      </logic:notEqual>
    </logic:notEqual>
  </td>
  <td width="100%" class="td_type16" align="left">
    <a href="#" onClick="return smlLinkClick('smlCheck', '<bean:write name="msg" property="smlSid" />');"><span class="sc_link"><bean:write name="msg" property="smsTitle" /></span></a>
  </td>

  <td width="95" class="td_type16" align="left" nowrap>
    <span class="text_base">
    <logic:equal name="msg" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_TOROKU) %>">
      <bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" />
    </logic:equal>
    <logic:equal name="msg" property="usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
      <del><bean:write name="msg" property="usiSei" />&nbsp;&nbsp;<bean:write name="msg" property="usiMei" /></del>
    </logic:equal>
    </span>
  </td>

  <td width="135" class="td_type16" align="center" nowrap>
    <span class="text_small"><bean:write name="msg" property="strSdate" /></span>
  </td>
  </tr>

  </logic:iterate>
  </table>
</logic:notEmpty>

</html:form>
</body>
</html:html>