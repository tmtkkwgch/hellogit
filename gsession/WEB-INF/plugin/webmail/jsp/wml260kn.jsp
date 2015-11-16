<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="jp.groupsession.v2.cmn.GSConstWebmail" %>
<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml260kn.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <gsjsmsg:js filename="gsjsmsg.js"/>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>" language="JavaScript"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../webmail/js/wml260.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="setSearchDateView(<bean:write name="wml260knForm" property="wml260SendDateCondition" />);" onunload="windowClose();">

<html:form action="/webmail/wml260kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden property="wml260searchFlg" />
<html:hidden property="wml260sortKey" />
<html:hidden property="wml260order" />
<html:hidden property="wmlViewAccount" />

<html:hidden property="wml260AccountName" />
<html:hidden property="wml260Title" />
<html:hidden property="wml260Address" />
<html:hidden property="wml260AddressFrom" />
<html:hidden property="wml260AddressTo" />
<html:hidden property="wml260SendDateYear" />
<html:hidden property="wml260SendDateMonth" />
<html:hidden property="wml260SendDateDay" />
<html:hidden property="wml260SendDateYearTo" />
<html:hidden property="wml260SendDateMonthTo" />
<html:hidden property="wml260SendDateDayTo" />
<html:hidden property="wml260SendDateCondition" />

<html:hidden property="wml260svAccountName" />
<html:hidden property="wml260svTitle" />
<html:hidden property="wml260svAddress" />
<html:hidden property="wml260svAddressFrom" />
<html:hidden property="wml260svAddressTo" />
<html:hidden property="wml260svSendDateYear" />
<html:hidden property="wml260svSendDateMonth" />
<html:hidden property="wml260svSendDateDay" />
<html:hidden property="wml260svSendDateYearTo" />
<html:hidden property="wml260svSendDateMonthTo" />
<html:hidden property="wml260svSendDateDayTo" />
<html:hidden property="wml260svSendDateCondition" />

<logic:iterate id="selectMailNum" name="wml260knForm" property="wml260selectMailNum">
  <input type="hidden" name="wml260selectMailNum" value="<bean:write name="selectMailNum" />">
</logic:iterate>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="85%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml260kn.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" class="btn_dell_n1" value="<gsmsg:write key="reserve.22" />" onclick="buttonPush('wml260knSendCancel');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backList');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr><td>&nbsp;</td></tr>

  <logic:messagesPresent message="false">
    <tr><td align="left"><html:errors/></td></tr>
    <tr><td>&nbsp;</td></tr>
  </logic:messagesPresent>

  <tr><td><span class="text_r2"><gsmsg:write key="wml.wml260kn.02" /></span></td></tr>

  </td>
  </tr>

  <tr><td>&nbsp;</td></tr>

  <tr>
  <td id="searchResult">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td><span class="text_base"><gsmsg:write key="wml.wml070.08" /></span></td>
      <logic:equal name="wml260knForm" property="wml260pageDspFlg" value="true">
      <td align="right" nowrap>
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="wml260pageTop" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="wml260knForm" property="pageList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
      </td>
      </logic:equal>
    </tr>
    </table>

    <bean:define id="orderValue" name="wml260knForm" property="wml260order" type="java.lang.Integer" />
    <%  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String accountName = gsMsg.getMessage(request, "wml.96");
        String sender = gsMsg.getMessage(request, "cmn.sender");
        String from = gsMsg.getMessage(request, "cmn.from");
        String subject = gsMsg.getMessage(request, "cmn.subject");
        String date = gsMsg.getMessage(request, "cmn.date");
        String up = gsMsg.getMessage(request, "tcd.tcd040.22");
        String down = gsMsg.getMessage(request, "tcd.tcd040.23");
    %>
    <% String orderLeft = up; %>
    <% String orderRight = ""; %>
    <% String nextOrder = String.valueOf(GSConstWebmail.ORDER_DESC); %>
    <% if (orderValue.intValue() == GSConstWebmail.ORDER_DESC) { %>
    <%    orderLeft = ""; %>
    <%    orderRight = down; %>
    <%    nextOrder = String.valueOf(GSConstWebmail.ORDER_ASC); %>
    <% } %>

    <bean:define id="sortValue" name="wml260knForm" property="wml260sortKey" type="java.lang.Integer" />
    <% String[] orderList = {String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC), String.valueOf(GSConstWebmail.ORDER_ASC)}; %>
    <% String[] titleList = {accountName, subject, sender, from + "(To Cc Bcc)", date}; %>
    <% int titleIndex = 0; %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_TITLE) { titleIndex = 1; } %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_FROM) { titleIndex = 2; } %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_TO) { titleIndex = 3; } %>
    <% if (sortValue.intValue() == GSConstWebmail.SKEY_DATE) { titleIndex = 4; } %>
    <% if (sortValue.intValue() == 4) { titleIndex = 0; } %>
    <% titleList[titleIndex] = orderLeft + titleList[titleIndex] + orderRight; %>
    <% orderList[titleIndex] = nextOrder; %>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="0%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="20%">
    <a href="#" onClick="return sort(<%= "4" %>, <%= orderList[0] %>);"><span class="text_tlw"><%= titleList[0] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="35%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_TITLE) %>, <%= orderList[1] %>);"><span class="text_tlw"><%= titleList[1] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="20%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_FROM) %>, <%= orderList[2] %>);"><span class="text_tlw"><%= titleList[2] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="20%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_TO) %>, <%= orderList[3] %>);"><span class="text_tlw"><%= titleList[3] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="10%">
    <a href="#" onClick="return sort(<%= String.valueOf(GSConstWebmail.SKEY_DATE) %>, <%= orderList[4] %>);"><span class="text_tlw"><%= titleList[4] %></span></a>
    </th>
    </tr>

    <logic:notEmpty name="wml260knForm" property="wml260SendResvList">
      <logic:iterate id="sendToData" name="wml260knForm" property="wml260SendResvList">
      <tr>
        <logic:equal name="sendToData" property="wlgTempFlg" value="1" >
          <td align="center" class="smail_td1" nowrap><img src="../webmail/images/assets/attach.gif"></td>
        </logic:equal>
        <logic:notEqual name="sendToData" property="wlgTempFlg" value="1">
          <td align="center" class="smail_td1" nowrap></td>
        </logic:notEqual>
        <td align="left" class="smail_td1"><span class="text_base"><bean:write name="sendToData" property="accountName" /></span></td>
        <td align="left" class="smail_td1">
          <a href="#" onClick="openDetail('<bean:write name="sendToData" property="wmdMailnum" />');"><span class="text_link"><bean:write name="sendToData" property="wlgTitle" /></span></a>
        </td>
        <td align="left" class="smail_td1"><span class="text_base"><bean:write name="sendToData" property="wlgFrom" /></span></td>
        <td align="left" class="smail_td1"><span class="text_base"><bean:write name="sendToData" property="wlsAddress" /></td>
        <td align="left" class="smail_td1"><span class="text_base"><bean:write name="sendToData" property="wlgDate" /></span><br>
                                           <span class="text_base"><bean:write name="sendToData" property="wlgTime" /></span>
        </td>
      </tr>
      </logic:iterate>
    </logic:notEmpty>

    </table>
  </td>
  </tr>

  <tr><td>&nbsp;</td></tr>

  <logic:equal name="wml260knForm" property="wml260pageDspFlg" value="true">
  <tr>
  <td align="right" nowrap>
    <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
    <html:select property="wml260pageBottom" onchange="changePage(2);" styleClass="text_i">
      <html:optionsCollection name="wml260knForm" property="pageList" value="value" label="label" />
    </html:select>
    <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('next');">
  </td>
  </tr>
  </logic:equal>

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