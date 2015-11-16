<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="jp.groupsession.v2.cmn.GSConstWebmail" %>
<%@ page import="jp.groupsession.v2.wml.wml030.Wml030AccountModel" %>

<html:html>
<head>
  <title>[GroupSession]<gsmsg:write key="wml.wml020.08" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml030.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml030">
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<input type="hidden" name="wmlCmdMode" value="0">
<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />
<html:hidden property="wmlAccountSid" />

<html:hidden property="wml030svKeyword" />
<html:hidden property="wml030svGroup" />
<html:hidden property="wml030svUser" />
<html:hidden property="wml030sortKey" />
<html:hidden property="wml030order" />
<html:hidden property="wml030searchFlg" />

<input type="hidden" name="wmlMailTemplateKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MAILTEMPLATE_NORMAL) %>">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="95%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml020.08" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_import" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('acntImport');">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('accountDetail', 0);">
          <input type="button" name="btn_del"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('accountDelete');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('admTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>

    <p class="type_p">
    <html:text name="wml030Form" property="wml030keyword" maxlength="50" style="width:183px;" />
    <input type="button" onclick="buttonPush('search');" class="btn_base0" value="<gsmsg:write key="cmn.search" />">
    &nbsp;
    <small><gsmsg:write key="cmn.group" /></small>
    <html:select name="wml030Form" property="wml030group" onchange="buttonPush('init');">
    <html:optionsCollection name="wml030Form" property="groupCombo" value="value" label="label" />
    </html:select>
    <input type="button" onclick="openGroupWindow(this.form.wml030group, 'wml030group', '0', 'init')" class="group_btn" value="&nbsp;&nbsp;" id="wml030GroupBtn">

    <small><gsmsg:write key="cmn.user" /></small>
    <html:select name="wml030Form" property="wml030user">
    <html:optionsCollection name="wml030Form" property="userCombo" value="value" label="label" />
    </html:select>
    </p>

    <logic:messagesPresent message="false">
      <html:errors/>
    </logic:messagesPresent>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <bean:define id="pageDspFlg" name="wml030Form" property="wml030pageDspFlg" type="java.lang.Boolean" />
    <tr>
      <td align="left" valign="bottom" width="99%"<% if (pageDspFlg.booleanValue()) {%> rowspan="2"<% } %>>
        <logic:notEmpty name="wml030Form" property="accountList">
          <span class="text_base"><gsmsg:write key="wml.wml030.03" /></span>
        </logic:notEmpty>
      </td>
      <td align="right" width="1%">
        <logic:notEmpty name="wml030Form" property="accountList">
          <input type="button" name="btn_export" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('acntExport');">
        </logic:notEmpty>
      </td>
      </tr>

      <% if (pageDspFlg.booleanValue()) { %>
      <tr>
      <td align="right" nowrap>
          <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
          <html:select name="wml030Form" property="wml030pageTop" styleClass="text_i" onchange="changePage(0);">
          <html:optionsCollection name="wml030Form" property="pageCombo" />
          </html:select>
          <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
      </td>
      </tr>
      <% } %>
    </tr>
    </table>

    <logic:notEmpty name="wml030Form" property="accountList">

    <bean:define id="orderValue" name="wml030Form" property="wml030order" type="java.lang.Integer" />
    <%  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String account = gsMsg.getMessage(request, "wml.96");
        String mail = gsMsg.getMessage(request, "cmn.mailaddress");
        String user = gsMsg.getMessage(request, "cmn.employer");
        String disk = gsMsg.getMessage(request, "wml.88");
        String date = gsMsg.getMessage(request, "cmn.received.date");
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

    <bean:define id="sortValue" name="wml030Form" property="wml030sortKey" type="java.lang.Integer" />
    <% String[] orderList = {String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC)}; %>
    <% String[] titleList = {account, mail, user, disk, date}; %>
    <% int titleIndex = 0; %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_MAIL) { titleIndex = 1; } %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_USER) { titleIndex = 2; } %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_DISKSIZE) { titleIndex = 3; } %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_RECEIVEDATE) { titleIndex = 4; } %>
    <% titleList[titleIndex] = orderLeft + titleList[titleIndex] + orderRight; %>
    <% orderList[titleIndex] = nextOrder; %>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="4%"><input type="checkbox" name="wml030AllCheck" value="1" onClick="chgCheckAll('wml030AllCheck', 'wml030selectAcount');chgCheckAllChange('wml030AllCheck', 'wml030selectAcount');"></th>
    <th align="center" class="table_bg_7D91BD" width="20%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_ACCOUNTNAME) %>, <%= orderList[0] %>);"><span class="text_tlw"><%= titleList[0] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="25%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_MAIL) %>, <%= orderList[1] %>);"><span class="text_tlw"><%= titleList[1] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="12%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_USER) %>, <%= orderList[2] %>);"><span class="text_tlw"><%= titleList[2] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="10%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_DISKSIZE) %>, <%= orderList[3] %>);"><span class="text_tlw"><%= titleList[3] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="10%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_RECEIVEDATE) %>, <%= orderList[4] %>);"><span class="text_tlw"><%= titleList[4] %></span></a>
    </th>

    <th align="center" class="table_bg_7D91BD" width="16%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="1%">&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="1%">&nbsp;</th>
    </tr>

    <logic:iterate id="accountData" name="wml030Form" property="accountList" indexId="idx">

    <bean:define id="backclass" value="td_line_color" />
    <bean:define id="backclass_no_edit" value="td_line_no_edit_color" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="back_no_edit" value="<%= String.valueOf(backclass_no_edit) + String.valueOf(backpat) %>" />

    <tr class="<%= String.valueOf(back) %>" id="<bean:write name="accountData" property="accountSid" />">

    <td class="prj_td" align="center">

      <% if (Integer.valueOf(backpat) == 1) { %>
        <html:multibox name="wml030Form" property="wml030selectAcount" onclick="backGroundSetting(this, '1');">
          <bean:write name="accountData" property="accountSid" />
        </html:multibox>
      <% } else { %>
        <html:multibox name="wml030Form" property="wml030selectAcount" onclick="backGroundSetting(this, '2');">
          <bean:write name="accountData" property="accountSid" />
        </html:multibox>
      <% } %>

    </td>

    <td align="left" class="prj_td">
      <span class="text_base"><bean:write name="accountData" property="accountName" /></span></a>
    </td>
    <td align="left" class="prj_td"><span class="text_base"><bean:write name="accountData" property="mailAddress" /></span></td>
    <td align="left" class="prj_td">
    <span class="text_base"><bean:write name="accountData" property="accountUserCount" />&nbsp;<bean:write name="accountData" property="accountUserKbnString" /></span>
    </td>
    <td align="right" class="prj_td"><span class="text_base"><bean:write name="accountData" property="diskSizeUse" />MB</span></td>
    <td align="left" class="prj_td"><span class="text_base"><bean:write name="accountData" property="viewReceiveDate" /></span></td>
    <td align="left" class="prj_td"><span class="text_base"><bean:write name="accountData" property="viewBiko" filter="false" /></span></td>

    <% String strWacSid = Integer.toString(((Wml030AccountModel) accountData).getAccountSid()); %>
    <td align="left" class="prj_td">
     <input type="button" onclick="editAccount(<%= String.valueOf(GSConstWebmail.CMDMODE_EDIT) %>, <%= strWacSid %>);" class="btn_base0" value="<gsmsg:write key="cmn.edit" />">
    </td>
    <td align="left" class="prj_td">
     <input type="button" onclick="confLabel(<%= strWacSid %>);" class="btn_account_subconf" value="<gsmsg:write key="cmn.label" />">
     <br><input type="button" onclick="confFilter(<%= strWacSid %>);" class="btn_account_subconf" value="<gsmsg:write key="wml.248" />">
     <br><input type="button" onclick="confTemplate(<%= strWacSid %>);" class="btn_account_subconf" value="<gsmsg:write key="cmn.template" />">
    </td>
    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>
  </td>
  </tr>

  <tr><td>&nbsp;</td></tr>

  <% if (pageDspFlg.booleanValue()) { %>
    <tr>
    <td align="right" nowrap>
        <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
        <html:select name="wml030Form" property="wml030pageBottom" styleClass="text_i" onchange="changePage(1);">
        <html:optionsCollection name="wml030Form" property="pageCombo" />
        </html:select>
        <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
    </td>
    </tr>
  <% } %>

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