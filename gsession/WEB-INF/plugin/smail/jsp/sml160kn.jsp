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
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.10" /></title>
</head>

<body class="body_03">
<html:form action="/smail/sml160kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="smlAccountSid" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />

<logic:notEmpty name="sml160knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml160knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml160JdelKbn" />
<html:hidden property="sml160JYear" />
<html:hidden property="sml160JMonth" />
<html:hidden property="sml160SdelKbn" />
<html:hidden property="sml160SYear" />
<html:hidden property="sml160SMonth" />
<html:hidden property="sml160WdelKbn" />
<html:hidden property="sml160WYear" />
<html:hidden property="sml160WMonth" />
<html:hidden property="sml160DdelKbn" />
<html:hidden property="sml160DYear" />
<html:hidden property="sml160DMonth" />
<html:hidden property="sml160SelKbn" />
<html:hidden property="sml160AccountName" />
<html:hidden property="sml160AccountSid" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.10" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('delete');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_syudo_input');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <th class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.target" /><gsmsg:write key="wml.102" /></span></th>
    <td align="left" class="td_wt" width="100%">
      <logic:iterate id="accountMdl" name="sml160knForm" property="sml160knAccountList">
        <div class="text_base1"><bean:write name="accountMdl" property="accountName"/></div>
      </logic:iterate>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.57" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml160knForm" property="sml160JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.dont.delete" />
      </logic:equal>

      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>

      <logic:equal name="sml160knForm" property="sml160JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="sml160Year1" name="sml160knForm" property="sml160JYear" type="java.lang.String" />
        <bean:define id="sml160Month1" name="sml160knForm" property="sml160JMonth" type="java.lang.String" />
        <% String kikanStr1 = "<strong>" + gsMsg.getMessage(request, "cmn.year", sml160Year1) + " " + gsMsg.getMessage(request, "cmn.months", sml160Month1) + "</strong>"; %>
        <gsmsg:write key="cmn.del.data.older.than" arg0="<%= kikanStr1 %>" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.59" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml160knForm" property="sml160SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.dont.delete" />
      </logic:equal>
      <logic:equal name="sml160knForm" property="sml160SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="sml160Year2" name="sml160knForm" property="sml160SYear" type="java.lang.String" />
        <bean:define id="sml160Month2" name="sml160knForm" property="sml160SMonth" type="java.lang.String" />
        <% String kikanStr2 = "<strong>" + gsMsg.getMessage(request, "cmn.year", sml160Year2) + " " + gsMsg.getMessage(request, "cmn.months", sml160Month2) + "</strong>"; %>
        <gsmsg:write key="cmn.del.data.older.than" arg0="<%= kikanStr2 %>" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.58" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml160knForm" property="sml160WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.dont.delete" />
      </logic:equal>
      <logic:equal name="sml160knForm" property="sml160WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="sml160Year3" name="sml160knForm" property="sml160WYear" type="java.lang.String" />
        <bean:define id="sml160Month3" name="sml160knForm" property="sml160WMonth" type="java.lang.String" />
        <% String kikanStr3 = "<strong>" + gsMsg.getMessage(request, "cmn.year", sml160Year3) + " " + gsMsg.getMessage(request, "cmn.months", sml160Month3) + "</strong>"; %>
        <gsmsg:write key="cmn.del.data.older.than" arg0="<%= kikanStr3 %>" />
      </logic:equal>
      </span>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.56" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml160knForm" property="sml160DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.dont.delete" />
      </logic:equal>
      <logic:equal name="sml160knForm" property="sml160DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="sml160Year4" name="sml160knForm" property="sml160DYear" type="java.lang.String" />
        <bean:define id="sml160Month4" name="sml160knForm" property="sml160DMonth" type="java.lang.String" />
        <% String kikanStr4 = "<strong>" + gsMsg.getMessage(request, "cmn.year", sml160Year4) + " " + gsMsg.getMessage(request, "cmn.months", sml160Month4) + "</strong>"; %>
        <gsmsg:write key="cmn.del.data.older.than" arg0="<%= kikanStr4 %>" />
      </logic:equal>
      </span>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('delete');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('back_syudo_input');">
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