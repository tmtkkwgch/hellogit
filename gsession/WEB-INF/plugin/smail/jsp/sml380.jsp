<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.sml.GSConstSmail" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/sml380.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../smail/js/smlaccountsel.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="sml.sml380.01" />
</title>
</head>

<body class="body_03">
<html:form action="/smail/sml380">
<input type="hidden" name="CMD" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountSid" />
<html:hidden property="sml380EditBan" />
<html:hidden property="backScreen" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml010Sort_key" />
<html:hidden property="sml010Order_key" />
<html:hidden property="sml010PageNum" />
<html:hidden property="sml010SelectedSid" />


<logic:notEmpty name="sml380Form" property="sml010DelSid" scope="request">
  <logic:iterate id="del" name="sml380Form" property="sml010DelSid" scope="request">
    <input type="hidden" name="sml010DelSid" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sml380svKeyword" />
<html:hidden property="sml380sortKey" />
<html:hidden property="sml380order" />
<html:hidden property="sml380searchFlg" />


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
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="sml.sml380.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="addBan();">
          <input type="button" name="btn_del"class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('banDelete');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');"></td>
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key='cmn.function.btn' />"></td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr><td style="padding-top: 15px;"><html:errors/></td></tr>
      </table>
    </logic:messagesPresent>


    <br>

    <table width="100%" border="0" cellpadding="0" cellspacing="3">
    <tr>
    <td colspan="2" align="left">
    <p class="type_p">
    <html:text name="sml380Form" property="sml380keyword" maxlength="50" style="width:183px;" />
    <input type="button" onclick="buttonPush('search');" class="btn_base0" value="<gsmsg:write key="cmn.search" />">
    </p>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left" width="100%">
        <logic:notEmpty name="sml380Form" property="banList">
          <span class="text_base"><gsmsg:write key="sml.sml380.02" /></span>
        </logic:notEmpty>
      </td>

      <logic:notEmpty name="sml380Form" property="pageCombo">
      <td align="right" nowrap>
          <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
          <html:select name="sml380Form" property="sml380pageTop" styleClass="text_i" onchange="changePage(0);">
          <html:optionsCollection name="sml380Form" property="pageCombo" />
          </html:select>
          <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
      </td>
      </logic:notEmpty>
    </tr>
    </table>
    <logic:notEmpty name="sml380Form" property="banList">

    <bean:define id="orderValue" name="sml380Form" property="sml380order" type="java.lang.Integer" />
    <%  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String destList = gsMsg.getMessage(request, "sml.sml380.03");
        String user = gsMsg.getMessage(request, "cmn.employer");
        String down = gsMsg.getMessage(request, "tcd.tcd040.23");
        String up = gsMsg.getMessage(request, "tcd.tcd040.22");
    %>
    <% String orderLeft = ""; %>
    <% String orderRight = up; %>
    <% String nextOrder = String.valueOf(GSConstSmail.ORDER_KEY_DESC); %>
    <% if (orderValue.intValue() == GSConstSmail.ORDER_KEY_DESC) { %>
    <%    orderLeft = ""; %>
    <%    orderRight = down; %>
    <%    nextOrder = String.valueOf(GSConstSmail.ORDER_KEY_ASC); %>
    <% } %>

    <bean:define id="sortValue" name="sml380Form" property="sml380sortKey" type="java.lang.Integer" />
    <% String[] orderList = {String.valueOf(GSConstSmail.ORDER_KEY_ASC)}; %>
    <% String[] titleList = {destList}; %>
    <% int titleIndex = 0; %>
    <% titleList[titleIndex] = orderLeft + titleList[titleIndex] + orderRight; %>
    <% orderList[titleIndex] = nextOrder; %>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="1%"><input type="checkbox" name="sml380AllCheck" value="1" onClick="chgCheckAll('sml380AllCheck', 'sml380selectBanList');"></th>
    <th align="center" class="table_bg_7D91BD" width="45%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstSmail.MSG_SORT_KEY_TITLE) %>, <%= orderList[0] %>);"><span class="text_tlw"><%= titleList[0] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="54%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <logic:iterate id="banData" name="sml380Form" property="banList" indexId="idx">

    <bean:define id="backclass" value="td_line_color" />
    <bean:define id="backclass_no_edit" value="td_line_no_edit_color" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="back_no_edit" value="<%= String.valueOf(backclass_no_edit) + String.valueOf(backpat) %>" />

    <tr class="<%= String.valueOf(back) %>" id="<bean:write name="banData" property="sbcSid" />">

    <td class="prj_td" align="center">

      <% if (Integer.valueOf(backpat) == 1) { %>
        <html:multibox name="sml380Form" property="sml380selectBanList" onclick="backGroundSetting(this, '1');">
          <bean:write name="banData" property="sbcSid" />
        </html:multibox>
      <% } else { %>
        <html:multibox name="sml380Form" property="sml380selectBanList" onclick="backGroundSetting(this, '2');">
          <bean:write name="banData" property="sbcSid" />
        </html:multibox>
      <% } %>

    </td>

    <td align="left" class="prj_td">
      <a href="#" onClick="return editBan(<bean:write name="banData" property="sbcSid" />)"; class="text_link"><bean:write name="banData" property="dspSbcName" /></a>
    </td>
    <td align="left" class="prj_td"><span class="text_base"><bean:write name="banData" property="dspSbcBiko" filter="false" /></span></td>
    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>

    </td>
    </tr>
    <tr><td>&nbsp;</td></tr>

    <logic:notEmpty name="sml380Form" property="pageCombo">
      <tr>
      <td align="right" nowrap>
          <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
          <html:select name="sml380Form" property="sml380pageBottom" styleClass="text_i" onchange="changePage(1);">
          <html:optionsCollection name="sml380Form" property="pageCombo" />
          </html:select>
          <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
      </td>
      </tr>
    </logic:notEmpty>

    <tr>
    <td>
      <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />" >
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

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>


</body>
</html:html>