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
<title>[Group Session] <bean:write name="cmn131knForm" property="cmn131dspTitle" /><gsmsg:write key="cmn.cmn131kn.1" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/submit.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/common/cmn131kn" onsubmit="return onControlSubmit();">

<input type="hidden" name="CMD" value="touroku">
<html:hidden property="cmn130cmdMode" />
<html:hidden property="cmn130selectGroupSid" />
<html:hidden property="cmn131name" />
<html:hidden property="cmn131memo" />
<html:hidden property="cmn131initFlg" />
<input type="hidden" name="helpPrm" value="<bean:write name="cmn131Form" property="cmn130cmdMode" />">

<logic:notEmpty name="cmn131knForm" property="cmn131userSid" scope="request">
<logic:iterate id="users" name="cmn131knForm" property="cmn131userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cmn131userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="cmn131Form" property="cmn131refUserSid" scope="request">
<logic:iterate id="refUsers" name="cmn131Form" property="cmn131refUserSid" indexId="idx" scope="request">
  <bean:define id="refUserSid" name="refUsers" type="java.lang.String" />
  <html:hidden property="cmn131refUserSid" value="<%= refUserSid %>" />
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
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <bean:write name="cmn131knForm" property="cmn131dspTitle" /><gsmsg:write key="cmn.check" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="40%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="60%">
      <input type="submit" value="<gsmsg:write key="cmn.final" />" class="btn_base1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
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
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.cmn130.1" /></span></td>
    <td align="left" class="td_wt" width="100%"><span class="text_base"><bean:write name="cmn131knForm" property="cmn131name" /></span></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
    <td align="left" class="td_wt">
      <span class="text_base">
        <logic:notEmpty name="cmn131knForm" property="cmn131knMemberList" scope="request">
          <logic:iterate id="memMdl" name="cmn131knForm" property="cmn131knMemberList" scope="request">
            <bean:write name="memMdl" property="usiSei" />　<bean:write name="memMdl" property="usiMei" /><br>
          </logic:iterate>
        </logic:notEmpty>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cir.11" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base"><bean:write name="cmn131knForm" property="cmn131knMemo" filter="false"/></span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.share" /></span></td>
    <td align="left" class="td_wt">
      <span class="text_base">
        <logic:notEmpty name="cmn131knForm" property="cmn131refMbLabelList" scope="request">
          <logic:iterate id="refMemMdl" name="cmn131knForm" property="cmn131refMbLabelList" scope="request">
            <bean:write name="refMemMdl" property="label" /><br>
          </logic:iterate>
        </logic:notEmpty>
      </span>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="submit" value="<gsmsg:write key="cmn.final" />" class="btn_base1">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
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