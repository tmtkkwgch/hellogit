<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="man.mbl.use.mass.configuration.confirmation" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/main/man210kn" onsubmit="return onControlSubmit();">

<input type="hidden" name="CMD" value="touroku">
<html:hidden property="man210ObjKbn" />
<html:hidden property="man210UseKbn" />
<html:hidden property="man210NumCont" />
<html:hidden property="man210NumAutAdd" />

<logic:notEmpty name="man210knForm" property="man210userSid" scope="request">
<logic:iterate id="users" name="man210knForm" property="man210userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="man210userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man210kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="40%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="60%">
      <input type="submit" value="<gsmsg:write key="cmn.final" />" class="btn_base1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_mblUseConf');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td valign="middle">
      <span class="text_r1"><gsmsg:write key="main.man210kn.2" /></span>
    </td>
    </tr>
    </table>

    <table width="100%" class="tl_u2" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.target" /></span></td>
    <td align="left" class="td_wt" width="100%">

      <logic:equal name="man210knForm" property="man210ObjKbn" value="0">
        <span class="text_base"><gsmsg:write key="cmn.alluser" /></span>
      </logic:equal>

      <logic:notEqual name="man210knForm" property="man210ObjKbn" value="0">
        <span class="text_base">
          <logic:notEmpty name="man210knForm" property="man210knMemberList" scope="request">
            <logic:iterate id="memMdl" name="man210knForm" property="man210knMemberList" scope="request">
              <bean:write name="memMdl" property="usiSei" />　<bean:write name="memMdl" property="usiMei" /><br>
            </logic:iterate>
          </logic:notEmpty>
        </span>
      </logic:notEqual>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="cmn.mobile.use" /></span></td>
    <td align="left" class="td_wt" width="100%">

    <logic:equal name="man210knForm" property="man210UseKbn" value="0">
      <span class="text_base"><gsmsg:write key="main.man210kn.3" /></span>

      <logic:equal name="man210knForm" property="man210NumCont" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_CONTROL) %>">
        <br><span class="text_base"><gsmsg:write key="cmn.login.control.identification.number" /></span>
      </logic:equal>

      <logic:equal name="man210knForm" property="man210NumAutAdd" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_AUTO_REG_OK) %>">
        <br><span class="text_base"><gsmsg:write key="main.man210.3" /></span>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="man210knForm" property="man210UseKbn" value="0">
      <span class="text_base"><gsmsg:write key="main.man210kn.4" /></span>
    </logic:notEqual>

    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="submit" value="<gsmsg:write key="cmn.final" />" class="btn_base1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_mblUseConf');">
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