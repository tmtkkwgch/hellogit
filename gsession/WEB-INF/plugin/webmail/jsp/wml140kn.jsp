<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.wml.wml140.Wml140Form" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml140kn.06" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml070.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml140.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml140kn">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="wmlFilterCmdMode" />
<html:hidden property="wmlEditFilterId" />
<html:hidden property="wml130accountSid" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="dspCount" />
<html:hidden property="wml130SortRadio" />

<html:hidden property="wml140initFlg" />
<html:hidden property="wml140FilterName" />
<html:hidden property="wml140filterType" />
<html:hidden property="wml140condition1" />
<html:hidden property="wml140conditionType1" />
<html:hidden property="wml140conditionExs1" />
<html:hidden property="wml140conditionText1" />
<html:hidden property="wml140condition2" />
<html:hidden property="wml140conditionType2" />
<html:hidden property="wml140conditionExs2" />
<html:hidden property="wml140conditionText2" />
<html:hidden property="wml140condition3" />
<html:hidden property="wml140conditionType3" />
<html:hidden property="wml140conditionExs3" />
<html:hidden property="wml140conditionText3" />
<html:hidden property="wml140condition4" />
<html:hidden property="wml140conditionType4" />
<html:hidden property="wml140conditionExs4" />
<html:hidden property="wml140conditionText4" />
<html:hidden property="wml140condition5" />
<html:hidden property="wml140conditionType5" />
<html:hidden property="wml140conditionExs5" />
<html:hidden property="wml140conditionText5" />

<html:hidden property="wml140actionLabel" />
<html:hidden property="wml140actionLabelValue" />
<html:hidden property="wml140actionRead" />
<html:hidden property="wml140actionDust" />
<html:hidden property="wml140actionSend" />

<logic:notEmpty name="wml140knForm" property="wml140actionSendValue">
<logic:iterate id="fwAddress" name="wml140knForm" property="wml140actionSendValue">
  <input type="hidden" name="wml140actionSendValue" value="<bean:write name="fwAddress" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="wml140tempFile" />
<html:hidden property="wml140doFilter" />

<html:hidden property="wml140viewMailList" />
<html:hidden property="wml140mailListSortKey" />
<html:hidden property="wml140mailListOrder" />
<html:hidden property="wml140svFilterType" />
<html:hidden property="wml140svCondition1" />
<html:hidden property="wml140svConditionType1" />
<html:hidden property="wml140svConditionExs1" />
<html:hidden property="wml140svConditionText1" />
<html:hidden property="wml140svCondition2" />
<html:hidden property="wml140svConditionType2" />
<html:hidden property="wml140svConditionExs2" />
<html:hidden property="wml140svConditionText2" />
<html:hidden property="wml140svCondition3" />
<html:hidden property="wml140svConditionType3" />
<html:hidden property="wml140svConditionExs3" />
<html:hidden property="wml140svConditionText3" />
<html:hidden property="wml140svCondition4" />
<html:hidden property="wml140svConditionType4" />
<html:hidden property="wml140svConditionExs4" />
<html:hidden property="wml140svConditionText4" />
<html:hidden property="wml140svCondition5" />
<html:hidden property="wml140svConditionType5" />
<html:hidden property="wml140svConditionExs5" />
<html:hidden property="wml140svConditionText5" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml140kn.06" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr><td>
    <br>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.102" /></span></td>
    <td align="left" class="td_wt" width="75%">
      <bean:write name="wml140knForm" property="wml130account" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
    <td align="left" class="td_wt" width="75%">
      <bean:write name="wml140knForm" property="wml140FilterName" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.40" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140filterTypeView" /></span>&nbsp;</p>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.05" /></span></td>
    <td align="left" class="td_wt" width="75%"><bean:write name="wml140knForm" property="wml140conditionType1View" />&nbsp;<bean:write name="wml140knForm" property="wml140conditionExs1View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140conditionText1" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.04" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml140knForm" property="wml140conditionType2View" />&nbsp;<bean:write name="wml140knForm" property="wml140conditionExs2View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140conditionText2" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.03" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml140knForm" property="wml140conditionType3View" />&nbsp;<bean:write name="wml140knForm" property="wml140conditionExs3View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140conditionText3" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.02" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml140knForm" property="wml140conditionType4View" />&nbsp;<bean:write name="wml140knForm" property="wml140conditionExs4View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140conditionText4" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.01" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml140knForm" property="wml140conditionType5View" />&nbsp;<bean:write name="wml140knForm" property="wml140conditionExs5View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140conditionText5" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td align="left" class="td_wt">
    <logic:equal name="wml140knForm" property="wml140tempFile" value="1">
      <gsmsg:write key="wml.14" />
    </logic:equal>
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.56" /></p>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <logic:equal name="wml140knForm" property="wml140actionLabel" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%">
        <gsmsg:write key="wml.75" />&nbsp;<span class="text_base5"><bean:write name="wml140knForm" property="wml140LabelView" /></span>
      </td>
      </tr>
    </logic:equal>

    <logic:equal name="wml140knForm" property="wml140actionRead" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%"><gsmsg:write key="cmn.mark.read" /></td>
      </tr>
    </logic:equal>

    <logic:equal name="wml140knForm" property="wml140actionDust" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%"><gsmsg:write key="wml.91" /></td>
      </tr>
    </logic:equal>

    <logic:equal name="wml140knForm" property="wml140actionSend" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%">
        <table>
        <tr>
        <td>
        <gsmsg:write key="wml.57" />&nbsp;
        </td>
        <td>
          <logic:notEmpty name="wml140knForm" property="wml140actionSendValue">
          <logic:iterate id="fwAddress" name="wml140knForm" property="wml140actionSendValue" indexId="fwIdx">
            <div class="text_base5"<% if (fwIdx.intValue() > 0) { %> style="margin-top: 5px;"<% } %>>&nbsp;&nbsp;<bean:write name="fwAddress" /></div>
          </logic:iterate>
          </logic:notEmpty>
        </td>
        </tr>
        </table>
      </td>
      </tr>
    </logic:equal>

    </table>

  </td>
  </tr>

  <logic:notEmpty name="wml140knForm" property="wml140mailList">
  <tr><td>&nbsp;</td></tr>

  <tr>
  <td>
    <table width="100% class="mailList">
      <tr>
      <td width="70%" align="left" nowrap>
      <gsmsg:write key="wml.85" />&nbsp;<gsmsg:write key="wml.116" />
      </td>
      <td width="30%" align="right" nowrap>
        <logic:notEmpty name="wml140knForm" property="wml140mailListPageCombo">
          <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
          <html:select styleId="wml140mailListPageTop" property="wml140mailListPageTop" onchange="selectPage(0);" styleClass="text_i">
          <html:optionsCollection name="wml140knForm" property="wml140mailListPageCombo" value="value" label="label" />
          </html:select>
          <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
        </logic:notEmpty>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" class="mailList" border="0" cellspaging="0" cellpadding="0">
    <tr class="mailList_header">
    <td width="2%">&nbsp;</td>

    <bean:define id="wml140SortKey" name="wml140knForm" property="wml140mailListSortKey" type="java.lang.Integer" />
    <bean:define id="wml140Order" name="wml140knForm" property="wml140mailListOrder" type="java.lang.Integer" />
    <% String[] widthList = new String[] {"28", "50", "20"};
      jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String from = gsMsg.getMessage(request, "wml.from");
        String subject = gsMsg.getMessage(request, "cmn.subject");
        String date = gsMsg.getMessage(request, "cmn.date");
        String down = gsMsg.getMessage(request, "tcd.tcd040.22");
        String up = gsMsg.getMessage(request, "tcd.tcd040.23");
    %>
    <% String[] titleList = new String[] {from, subject, date}; %>
    <% int[] sortKeyList = new int[] {Wml140Form.WML140_SKEY_FROM, Wml140Form.WML140_SKEY_SUBJECT, Wml140Form.WML140_SKEY_DATE}; %>

    <% for (int i = 0; i < widthList.length; i++) { %>
    <%   String title = titleList[i]; %>
    <%   int order = Wml140Form.WML140_ORDER_ASC; %>
    <%   if (sortKeyList[i] == wml140SortKey.intValue()) { %>
    <%     if (wml140Order.intValue() == Wml140Form.WML140_ORDER_DESC) { %>
    <%       title = title + " " + down; %>
    <%     } else { %>
    <%       title = up + " " + title; %>
    <%       order = Wml140Form.WML140_ORDER_DESC; %>
    <%     } %>
    <%   } %>
    <td width="<%= widthList[i] %>%"><a href="#" onClick="return wml140Sort(<%= String.valueOf(sortKeyList[i]) %>, <%= String.valueOf(order) %>)"><%= title %></a></td>
    <% } %>

    </tr>

    <logic:iterate id="mailData" name="wml140knForm" property="wml140mailList" indexId="idx">
    <% if (idx.intValue() % 2 == 0) { %>
    <tr class="mailList_data1">
    <% } else { %>
    <tr class="mailList_data2">
    <% } %>

    <td align="center">
      <logic:equal name="mailData" property="attach" value="true">
      <img src="../webmail/images/assets/attach2.gif">
      </logic:equal>
    </td>
    <td><bean:write name="mailData" property="from" /></td>
    <td><a href="#" onClick="openDetail('<bean:write name="mailData" property="mailNum" />');"><span class="text_link"><bean:write name="mailData" property="subject" /></span></a></td>
    <td><bean:write name="mailData" property="strDate" /></td>
    </tr>
    </logic:iterate>

    </table>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>

  <logic:notEmpty name="wml140knForm" property="wml140mailListPageCombo">
  <tr>
  <td width="100%" align="right">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select styleId="wml140mailListPageTop" property="wml140mailListPageTop" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="wml140knForm" property="wml140mailListPageCombo" value="value" label="label" />
    </html:select>
    <a href="javascript:void(0);" onClick="return buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_r.gif" border="0"></a></td>
  </td>
  </tr>
  </logic:notEmpty>
  </logic:notEmpty>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.space" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backInput');">
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>