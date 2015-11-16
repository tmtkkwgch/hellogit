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
<title>[GroupSession] <gsmsg:write key="sml.08" /></title>
</head>

<body class="body_03">
<html:form action="/smail/sml150kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />

<logic:notEmpty name="sml150knForm" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml150knForm" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="backScreen" />
<html:hidden property="sml150DelKbn" />
<html:hidden property="sml150JdelKbn" />
<html:hidden property="sml150JYear" />
<html:hidden property="sml150JMonth" />
<html:hidden property="sml150SdelKbn" />
<html:hidden property="sml150SYear" />
<html:hidden property="sml150SMonth" />
<html:hidden property="sml150WdelKbn" />
<html:hidden property="sml150WYear" />
<html:hidden property="sml150WMonth" />
<html:hidden property="sml150DdelKbn" />
<html:hidden property="sml150DYear" />
<html:hidden property="sml150DMonth" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.08" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('update');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
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

    <%--
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.53" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
        <logic:equal name="sml150knForm" property="sml150DelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_DEL_KBN_ADM_SETTING) %>"><gsmsg:write key="cmn.set.the.admin" /></logic:equal>
        <logic:equal name="sml150knForm" property="sml150DelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_DEL_KBN_USER_SETTING) %>"><gsmsg:write key="cmn.set.eachuser" /></logic:equal>
      </span>
    </td>
    </tr>
    --%>



    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.50" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml150knForm" property="sml150JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml150knForm" property="sml150JdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="jyear" name="sml150knForm" property="sml150JYear" type="java.lang.String" />
        <bean:define id="jmonth" name="sml150knForm" property="sml150JMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= jyear %>" /> <gsmsg:write key="cmn.months" arg0="<%= jmonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.52" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml150knForm" property="sml150SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml150knForm" property="sml150SdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="syear" name="sml150knForm" property="sml150SYear" type="java.lang.String" />
        <bean:define id="smonth" name="sml150knForm" property="sml150SMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= syear %>" /> <gsmsg:write key="cmn.months" arg0="<%= smonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.51" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml150knForm" property="sml150WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml150knForm" property="sml150WdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="wyear" name="sml150knForm" property="sml150WYear" type="java.lang.String" />
        <bean:define id="wmonth" name="sml150knForm" property="sml150WMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= wyear %>" /> <gsmsg:write key="cmn.months" arg0="<%= wmonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="sml.49" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <span class="text_base">
      <logic:equal name="sml150knForm" property="sml150DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_NO) %>">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="sml150knForm" property="sml150DdelKbn" value="<%= String.valueOf(jp.groupsession.v2.sml.GSConstSmail.SML_AUTO_DEL_LIMIT) %>">
        <bean:define id="dyear" name="sml150knForm" property="sml150DYear" type="java.lang.String" />
        <bean:define id="dmonth" name="sml150knForm" property="sml150DMonth" type="java.lang.String" />
        <strong><gsmsg:write key="cmn.year" arg0="<%= dyear %>" /> <gsmsg:write key="cmn.months" arg0="<%= dmonth %>" /></strong> <gsmsg:write key="cmn.auto.del.data.older.than" />
      </logic:equal>
      </span>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="5" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('update');">
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