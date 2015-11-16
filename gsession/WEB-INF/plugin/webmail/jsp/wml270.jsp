<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="jp.groupsession.v2.cmn.GSConstWebmail" %>
<%@ page import="jp.groupsession.v2.wml.wml270.Wml270DestListModel" %>

<html:html>
<head>
  <title>[GroupSession]<gsmsg:write key="wml.wml020.14" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml270.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml270">
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<input type="hidden" name="wmlCmdMode" value="0">
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />

<input type="hidden" name="wmlEditDestList" value="">
<html:hidden property="wml270svKeyword" />
<html:hidden property="wml270sortKey" />
<html:hidden property="wml270order" />
<html:hidden property="wml270searchFlg" />

<input type="hidden" name="wmlMailTemplateKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_NORMAL) %>">

<bean:define id="acctMode" name="wml270Form" property="wmlAccountMode" type="java.lang.Integer" />
<% int accountMode = acctMode.intValue(); %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="60%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <% if (accountMode == 1) { %>
          <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <% } else { %>
          <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
          <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <% } %>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml020.14" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('addDestList', 0);">
          <input type="button" name="btn_del"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('destListDelete');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('admTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:messagesPresent message="false">
    <tr><td style="padding-top: 15px;"><html:errors/></td></tr>
  </logic:messagesPresent>

  <tr>
  <td>

    <p class="type_p">
    <html:text name="wml270Form" property="wml270keyword" maxlength="50" style="width:183px;" />
    <input type="button" onclick="buttonPush('search');" class="btn_base0" value="<gsmsg:write key="cmn.search" />">
    </p>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left" width="100%">
        <logic:notEmpty name="wml270Form" property="destListList">
          <% if (accountMode == 1) { %>
          <span class="text_base"><gsmsg:write key="wml.wml270.02" /></span>
          <% } else { %>
          <span class="text_base"><gsmsg:write key="wml.wml270.01" /></span>
         <% } %>
        </logic:notEmpty>
      </td>

      <logic:notEmpty name="wml270Form" property="pageCombo">
      <td align="right" nowrap>
          <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
          <html:select name="wml270Form" property="wml270pageTop" styleClass="text_i" onchange="changePage(0);">
          <html:optionsCollection name="wml270Form" property="pageCombo" />
          </html:select>
          <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
      </td>
      </logic:notEmpty>
    </tr>
    </table>

    <logic:notEmpty name="wml270Form" property="destListList">

    <bean:define id="orderValue" name="wml270Form" property="wml270order" type="java.lang.Integer" />
    <%  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String destList = gsMsg.getMessage(request, "wml.263");
        String user = gsMsg.getMessage(request, "cmn.employer");
        String down = gsMsg.getMessage(request, "tcd.tcd040.23");
        String up = gsMsg.getMessage(request, "tcd.tcd040.22");
    %>
    <% String orderLeft = ""; %>
    <% String orderRight = up; %>
    <% String nextOrder = String.valueOf(GSConstWebmail.ORDER_DESC); %>
    <% if (orderValue.intValue() == GSConstWebmail.ORDER_DESC) { %>
    <%    orderLeft = ""; %>
    <%    orderRight = down; %>
    <%    nextOrder = String.valueOf(GSConstWebmail.ORDER_ASC); %>
    <% } %>

    <bean:define id="sortValue" name="wml270Form" property="wml270sortKey" type="java.lang.Integer" />
    <% String[] orderList = {String.valueOf(GSConstWebmail.ORDER_ASC)}; %>
    <% String[] titleList = {destList}; %>
    <% int titleIndex = 0; %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_USER) { titleIndex = 1; } %>
    <% titleList[titleIndex] = orderLeft + titleList[titleIndex] + orderRight; %>
    <% orderList[titleIndex] = nextOrder; %>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="1%"><input type="checkbox" name="wml030AllCheck" value="1" onClick="chgCheckAll('wml030AllCheck', 'wml270selectDestList');chgCheckAllChange('wml030AllCheck', 'wml030selectAcount');"></th>
    <th align="center" class="table_bg_7D91BD" width="45%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_ACCOUNTNAME) %>, <%= orderList[0] %>);"><span class="text_tlw"><%= titleList[0] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="54%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <logic:iterate id="destData" name="wml270Form" property="destListList" indexId="idx" type="Wml270DestListModel">

    <bean:define id="backclass" value="td_line_color" />
    <bean:define id="backclass_no_edit" value="td_line_no_edit_color" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="back_no_edit" value="<%= String.valueOf(backclass_no_edit) + String.valueOf(backpat) %>" />

    <tr class="<%= String.valueOf(back) %>" id="<bean:write name="destData" property="destListSid" />">

    <td class="prj_td" align="center">

      <logic:equal name="destData" property="editFlg" value="true">
      <% if (Integer.valueOf(backpat) == 1) { %>
        <html:multibox name="wml270Form" property="wml270selectDestList" onclick="backGroundSetting(this, '1');">
          <bean:write name="destData" property="destListSid" />
        </html:multibox>
      <% } else { %>
        <html:multibox name="wml270Form" property="wml270selectDestList" onclick="backGroundSetting(this, '2');">
          <bean:write name="destData" property="destListSid" />
        </html:multibox>
      <% } %>
      </logic:equal>

    </td>

    <td align="left" class="prj_td">
      <a href="#" onClick="return editDestlist(<bean:write name="destData" property="destListSid" />)"; class="text_link"><bean:write name="destData" property="destListName" /></a>
    </td>
    <td align="left" class="prj_td"><span class="text_base"><bean:write name="destData" property="viewBiko" filter="false" /></span></td>
    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>
  </td>
  </tr>

  <tr><td>&nbsp;</td></tr>

  <logic:notEmpty name="wml270Form" property="pageCombo">
    <tr>
    <td align="right" nowrap>
        <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
        <html:select name="wml270Form" property="wml270pageBottom" styleClass="text_i" onchange="changePage(1);">
        <html:optionsCollection name="wml270Form" property="pageCombo" />
        </html:select>
        <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
    </td>
    </tr>
  </logic:notEmpty>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">&nbsp;</td>
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