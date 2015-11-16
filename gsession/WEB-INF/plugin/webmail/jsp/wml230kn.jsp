<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.wml.wml230.Wml230Form" %>
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
  <script language="JavaScript" src="../webmail/js/wml230.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml230kn">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml030_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="wmlAccountSid" />
<html:hidden property="wmlFilterCmdMode" />
<html:hidden property="wmlEditFilterId" />
<html:hidden property="wmlViewAccount" />
<html:hidden property="dspCount" />
<html:hidden property="wml220SortRadio" />

<html:hidden property="wml230initFlg" />
<html:hidden property="wml230FilterName" />
<html:hidden property="wml230filterType" />
<html:hidden property="wml230condition1" />
<html:hidden property="wml230conditionType1" />
<html:hidden property="wml230conditionExs1" />
<html:hidden property="wml230conditionText1" />
<html:hidden property="wml230condition2" />
<html:hidden property="wml230conditionType2" />
<html:hidden property="wml230conditionExs2" />
<html:hidden property="wml230conditionText2" />
<html:hidden property="wml230condition3" />
<html:hidden property="wml230conditionType3" />
<html:hidden property="wml230conditionExs3" />
<html:hidden property="wml230conditionText3" />
<html:hidden property="wml230condition4" />
<html:hidden property="wml230conditionType4" />
<html:hidden property="wml230conditionExs4" />
<html:hidden property="wml230conditionText4" />
<html:hidden property="wml230condition5" />
<html:hidden property="wml230conditionType5" />
<html:hidden property="wml230conditionExs5" />
<html:hidden property="wml230conditionText5" />

<html:hidden property="wml230actionLabel" />
<html:hidden property="wml230actionLabelValue" />
<html:hidden property="wml230actionRead" />
<html:hidden property="wml230actionDust" />
<html:hidden property="wml230actionSend" />

<logic:notEmpty name="wml230knForm" property="wml230actionSendValue">
<logic:iterate id="fwAddress" name="wml230knForm" property="wml230actionSendValue">
  <input type="hidden" name="wml230actionSendValue" value="<bean:write name="fwAddress" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="wml230tempFile" />
<html:hidden property="wml230doFilter" />

<html:hidden property="wml230viewMailList" />
<html:hidden property="wml230mailListSortKey" />
<html:hidden property="wml230mailListOrder" />
<html:hidden property="wml230svFilterType" />
<html:hidden property="wml230svCondition1" />
<html:hidden property="wml230svConditionType1" />
<html:hidden property="wml230svConditionExs1" />
<html:hidden property="wml230svConditionText1" />
<html:hidden property="wml230svCondition2" />
<html:hidden property="wml230svConditionType2" />
<html:hidden property="wml230svConditionExs2" />
<html:hidden property="wml230svConditionText2" />
<html:hidden property="wml230svCondition3" />
<html:hidden property="wml230svConditionType3" />
<html:hidden property="wml230svConditionExs3" />
<html:hidden property="wml230svConditionText3" />
<html:hidden property="wml230svCondition4" />
<html:hidden property="wml230svConditionType4" />
<html:hidden property="wml230svConditionExs4" />
<html:hidden property="wml230svConditionText4" />
<html:hidden property="wml230svCondition5" />
<html:hidden property="wml230svConditionType5" />
<html:hidden property="wml230svConditionExs5" />
<html:hidden property="wml230svConditionText5" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
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
      <bean:write name="wml230knForm" property="wml220accountName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
    <td align="left" class="td_wt" width="75%">
      <bean:write name="wml230knForm" property="wml230FilterName" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.40" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230filterTypeView" /></span>&nbsp;</p>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.05" /></span></td>
    <td align="left" class="td_wt" width="75%"><bean:write name="wml230knForm" property="wml230conditionType1View" />&nbsp;<bean:write name="wml230knForm" property="wml230conditionExs1View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230conditionText1" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.04" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml230knForm" property="wml230conditionType2View" />&nbsp;<bean:write name="wml230knForm" property="wml230conditionExs2View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230conditionText2" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.03" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml230knForm" property="wml230conditionType3View" />&nbsp;<bean:write name="wml230knForm" property="wml230conditionExs3View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230conditionText3" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.02" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml230knForm" property="wml230conditionType4View" />&nbsp;<bean:write name="wml230knForm" property="wml230conditionExs4View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230conditionText4" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.01" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="wml230knForm" property="wml230conditionType5View" />&nbsp;<bean:write name="wml230knForm" property="wml230conditionExs5View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230conditionText5" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td align="left" class="td_wt">
    <logic:equal name="wml230knForm" property="wml230tempFile" value="1">
      <gsmsg:write key="wml.14" />
    </logic:equal>
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.56" /></p>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <logic:equal name="wml230knForm" property="wml230actionLabel" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%">
        <gsmsg:write key="wml.75" />&nbsp;<span class="text_base5"><bean:write name="wml230knForm" property="wml230LabelView" /></span>
      </td>
      </tr>
    </logic:equal>

    <logic:equal name="wml230knForm" property="wml230actionRead" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%"><gsmsg:write key="cmn.mark.read" /></td>
      </tr>
    </logic:equal>

    <logic:equal name="wml230knForm" property="wml230actionDust" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%"><gsmsg:write key="wml.91" /></td>
      </tr>
    </logic:equal>

    <logic:equal name="wml230knForm" property="wml230actionSend" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%">
        <table>
        <tr>
        <td>
        <gsmsg:write key="wml.57" />&nbsp;
        </td>
        <td>
          <logic:notEmpty name="wml230knForm" property="wml230actionSendValue">
          <logic:iterate id="fwAddress" name="wml230knForm" property="wml230actionSendValue" indexId="fwIdx">
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

  <logic:notEmpty name="wml230knForm" property="wml230mailList">
  <tr><td>&nbsp;</td></tr>

  <tr>
  <td>
    <table width="100% class="mailList">
      <tr>
      <td width="70%" align="left" nowrap>
      <gsmsg:write key="wml.85" />&nbsp;<gsmsg:write key="wml.116" />
      </td>
      <td width="30%" align="right" nowrap>
        <logic:notEmpty name="wml230knForm" property="wml230mailListPageCombo">
          <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
          <html:select styleId="wml230mailListPageTop" property="wml230mailListPageTop" onchange="selectPage(0);" styleClass="text_i">
          <html:optionsCollection name="wml230knForm" property="wml230mailListPageCombo" value="value" label="label" />
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

    <bean:define id="wml230SortKey" name="wml230knForm" property="wml230mailListSortKey" type="java.lang.Integer" />
    <bean:define id="wml230Order" name="wml230knForm" property="wml230mailListOrder" type="java.lang.Integer" />
    <% String[] widthList = new String[] {"28", "50", "20"};
      jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String from = gsMsg.getMessage(request, "wml.from");
        String subject = gsMsg.getMessage(request, "cmn.subject");
        String date = gsMsg.getMessage(request, "cmn.date");
        String down = gsMsg.getMessage(request, "tcd.tcd040.22");
        String up = gsMsg.getMessage(request, "tcd.tcd040.23");
    %>
    <% String[] titleList = new String[] {from, subject, date}; %>
    <% int[] sortKeyList = new int[] {Wml230Form.WML230_SKEY_FROM, Wml230Form.WML230_SKEY_SUBJECT, Wml230Form.WML230_SKEY_DATE}; %>

    <% for (int i = 0; i < widthList.length; i++) { %>
    <%   String title = titleList[i]; %>
    <%   int order = Wml230Form.WML230_ORDER_ASC; %>
    <%   if (sortKeyList[i] == wml230SortKey.intValue()) { %>
    <%     if (wml230Order.intValue() == Wml230Form.WML230_ORDER_DESC) { %>
    <%       title = title + " " + down; %>
    <%     } else { %>
    <%       title = up + " " + title; %>
    <%       order = Wml230Form.WML230_ORDER_DESC; %>
    <%     } %>
    <%   } %>
    <td width="<%= widthList[i] %>%"><a href="#" onClick="return wml230Sort(<%= String.valueOf(sortKeyList[i]) %>, <%= String.valueOf(order) %>)"><%= title %></a></td>
    <% } %>

    </tr>

    <logic:iterate id="mailData" name="wml230knForm" property="wml230mailList" indexId="idx">
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

  <logic:notEmpty name="wml230knForm" property="wml230mailListPageCombo">
  <tr>
  <td width="100%" align="right">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select styleId="wml230mailListPageTop" property="wml230mailListPageTop" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="wml230knForm" property="wml230mailListPageCombo" value="value" label="label" />
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