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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="ptl.ptl050kn.1" /></title>
</head>

<!-- body -->
<body class="body_03">

<html:form action="/portal/ptl050kn">
<input type="hidden" name="CMD" value="init">
<html:hidden property="ptlPortalSid" />
<html:hidden property="ptl030sortPortal" />

<html:hidden property="ptl050init" />
<html:hidden property="ptl050name" />
<html:hidden property="ptl050openKbn" />
<html:hidden property="ptl050description" />
<html:hidden property="ptl050accessKbn" />
<html:hidden property="ptl050accessKbnGroup" />

<logic:notEmpty name="ptl050knForm" property="ptl050memberSid">
<logic:iterate id="usid" name="ptl050knForm" property="ptl050memberSid">
  <input type="hidden" name="ptl050memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>
<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.ptl050kn.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%">&nbsp;</td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush2('ptl050knOk');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('ptl050knBack');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
    <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td>

    <table id="wml_settings" class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="ptl.4" /></span></td>
        <td align="left" class="td_wt" width="750">
          <span class="text_base"><bean:write name="ptl050knForm" property="ptl050name" /></span>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.public.kbn" /></span></td>
        <td align="left" class="td_wt">
          <span class="text_base">
            <logic:equal name="ptl050knForm" property="ptl050openKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_OPENKBN_OK) %>"><gsmsg:write key="cmn.show" /></logic:equal>
            <logic:equal name="ptl050knForm" property="ptl050openKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_OPENKBN_NG) %>"><gsmsg:write key="cmn.hide" /></logic:equal>
          </span>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="td_wt">
          <span class="text_base">
            <bean:write name="ptl050knForm" property="ptl050knviewDescription" filter="false" />
          </span>
        </td>
      </tr>

      <tr>
      <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.access.auth" /></span>
      </td>

      <td valign="middle" align="left" class="td_wt" width="100%">

        <span class="text_base">
          <logic:equal name="ptl050knForm" property="ptl050accessKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_ACCESS_OFF) %>"><gsmsg:write key="cmn.not.limit" /></logic:equal>
          <logic:equal name="ptl050knForm" property="ptl050accessKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_ACCESS_ON) %>"><gsmsg:write key="cmn.do.limit" /></logic:equal>
        </span>

        <logic:equal name="ptl050knForm" property="ptl050accessKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_ACCESS_ON) %>">
        <br>
        <br>

        <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
        <tr><td class="td_sub_title3" style="text-align:center!important;"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td></tr>
        <tr>
        <td align="left" class="td_wt">
        <span class="text_base">

          <logic:notEmpty name="ptl050knForm" property="ptl050knMemNameList">
          <logic:iterate id="memName" name="ptl050knForm" property="ptl050knMemNameList">
            <bean:write name="memName" property="label" /><br>
          </logic:iterate>
          </logic:notEmpty>

        </span>
        </td>
        </tr>
        </table>
        </logic:equal>

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
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush2('ptl050knOk');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('ptl050knBack');">
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

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>

</html:html>