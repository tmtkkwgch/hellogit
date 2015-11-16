<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.wml.wml140.Wml140Form" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml140.04" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

  <gsjsmsg:js filename="gsjsmsg.js"/>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml070.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml140.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="changeFilterInput();">

<html:form action="/webmail/wml140">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlFilterCmdMode" />
<html:hidden property="wmlEditFilterId" />
<html:hidden property="wml130accountSid" />
<html:hidden property="wml140initFlg" />
<html:hidden property="dspCount" />
<html:hidden property="wml130SortRadio" />

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
<html:hidden property="wml140svTempFile" />

<input type="hidden" name="wml140actionSendValueDelIdx" value="">

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml140.04" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_ok1" value="<gsmsg:write key="cmn.ok" />" onClick="buttonPush('confirm');">
          <input type="button" name="btn_filterSearch" class="btn_filterSearch" value="<gsmsg:write key="wml.wml140.03" />" onClick="buttonPush('filterSearch');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('filterList');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
  <td>
    <table width="100%">
    <tr><td align="left"><html:errors/></td></tr>
    </table>
  </td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <br>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.102" /></span></td>
    <td align="left" class="td_wt" width="75%">
      <bean:write name="wml140Form" property="wml130account" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
    <td align="left" class="td_wt" width="70%">
      <html:text name="wml140Form" property="wml140FilterName" maxlength="100" style="width:100%;" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.40" />&nbsp;<html:radio name="wml140Form" property="wml140filterType" value="0" styleId="filtype1" /><label for="filtype1"><gsmsg:write key="wml.wml140.01" /></label>
                                &nbsp;<html:radio name="wml140Form" property="wml140filterType" value="1" styleId="filtype2" /><label for="filtype2"><gsmsg:write key="wml.containing.either" /></label></p>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
<%
  int condition = 1;
  String conditionCheck = null;
  String conditionType = null;
  String conditionExs = null;
  String conditionText = null;
  String number = null;
  try {
    for (int i = 1; i <= 5; i++) {
      conditionCheck = "wml140condition" + condition;
      conditionType = "wml140conditionType" + condition;
      conditionExs = "wml140conditionExs" + condition;
      conditionText = "wml140conditionText" + condition;
      number = String.valueOf(i);
%>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140.02" /><%=condition%></span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <html:checkbox name="wml140Form" property="<%=conditionCheck%>" value="<%= number %>" onclick="changeFilterInput();" />&nbsp;&nbsp;

      <logic:notEmpty name="wml140Form" property="conditionList1">
        <html:select property="<%=conditionType%>" size="1">
          <html:optionsCollection name="wml140Form" property="conditionList1" value="value" label="label" />
        </html:select>
      </logic:notEmpty>

      <logic:notEmpty name="wml140Form" property="conditionList2">
        <html:select property="<%=conditionExs%>" size="1">
          <html:optionsCollection name="wml140Form" property="conditionList2" value="value" label="label" />
        </html:select>
      </logic:notEmpty>

      <html:text property="<%=conditionText%>" maxlength="256" style="width:393px;"/>
    </td>
    </tr>
<%
      condition++;
    }
  } catch (Exception e) {

  }
%>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td align="left" class="td_wt text_base2">
       <html:checkbox name="wml140Form" property="wml140tempFile" value="1" /><gsmsg:write key="wml.14" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.56" /></p>


    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="wml140Form" property="wml140actionLabel" value="1" /></td>
    <td align="left" class="td_wt" width="100%">
    <gsmsg:write key="wml.75" />
    <logic:notEmpty name="wml140Form" property="conditionList2">
      <html:select property="wml140actionLabelValue" size="1">
        <html:optionsCollection name="wml140Form" property="lbList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1" valign="middle"><html:checkbox name="wml140Form" property="wml140actionRead" value="1" /></td>
    <td align="left" class="td_wt" width="100%"><gsmsg:write key="cmn.mark.read" /></td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="wml140Form" property="wml140actionDust" value="1" /></td>
    <td align="left" class="td_wt" width="100%"><gsmsg:write key="wml.91" /></td>
    </tr>

<logic:notEqual name="wml140Form" property="wml140fwLimitFlg" value="1">
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="wml140Form" property="wml140actionSend" value="1" onclick="changeFilterInput();" />&nbsp;&nbsp;</td>
    <td align="left" class="td_wt" width="100%">
      <table>
      <tr>
      <td width="10%" nowrap>
        <gsmsg:write key="wml.57" />
      </td>
      <td width="90%" style="padding-left: 30px; vertical-align: top;">
        <table id="wml140fwAddressArea" class="tl0" cellpadding="3" cellspacing="0" border="0" width="99%">
        <logic:notEmpty name="wml140Form" property="wml140actionSendValue">
        <logic:iterate id="fwAddress" name="wml140Form" property="wml140actionSendValue" indexId="fwAdrIdx" type="java.lang.String">
          <tr>
          <td width="5%" align="right"><% if (fwAdrIdx.intValue() > 0) { %><img src="../common/images/delete.gif" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="deleteFwAddress(<%= String.valueOf(fwAdrIdx.intValue()) %>);"><% } else { %>&nbsp;<% } %></td>
          <td width="95%">
         <% if (fwAddress == null) { fwAddress = ""; }%>
          <input type="text" name="wml140actionSendValue" value="<%= fwAddress %>"  maxlength="256" style="width:60%;">
          </td>
          </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="wml140Form" property="wml140actionSendValue">
          <tr>
          <td width="5%">&nbsp;</td>
          <td class="td_wt" width="95%"><input type="text" name="wml140actionSendValue" value=""  maxlength="256" style="width:60%;"></td>
          </tr>
        </logic:empty>
        <tr><td>&nbsp;</td><td><a href="#" onClick="return buttonPush('addFwAddress');" class="text_link" style="font-size: 70%"><gsmsg:write key="wml.wml140.05" /></td></tr>
        </table>
      </td>
      </tr>
      </table>
    </td>
    </tr>
</logic:notEqual>
    </table>

  </td>
  </tr>

  <logic:notEmpty name="wml140Form" property="wml140mailList">
  <tr><td>&nbsp;</td></tr>

  <tr>
  <td>
    <table width="100% class="mailList">
    <tr>
    <td width="70%" align="left" nowrap>
    <html:checkbox styleId="wml140doFilter" name="wml140Form" property="wml140doFilter" value="<%= String.valueOf(jp.groupsession.v2.wml.wml140.Wml140Form.WML140_DOFILTER_YES) %>" /><label for="wml140doFilter"><gsmsg:write key="wml.85" />&nbsp;<gsmsg:write key="wml.116" /></label>
    </td>
    <td width="30%" align="right" nowrap>
      <logic:notEmpty name="wml140Form" property="wml140mailListPageCombo">
      <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select styleId="wml140mailListPageTop" property="wml140mailListPageTop" onchange="selectPage(0);" styleClass="text_i">
        <html:optionsCollection name="wml140Form" property="wml140mailListPageCombo" value="value" label="label" />
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

    <bean:define id="wml140SortKey" name="wml140Form" property="wml140mailListSortKey" type="java.lang.Integer" />
    <bean:define id="wml140Order" name="wml140Form" property="wml140mailListOrder" type="java.lang.Integer" />
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
    <%       title = title + " " + up; %>
    <%       order = Wml140Form.WML140_ORDER_DESC; %>
    <%     } %>
    <%   } %>
    <td width="<%= widthList[i] %>%"><a href="#" onClick="return wml140Sort(<%= String.valueOf(sortKeyList[i]) %>, <%= String.valueOf(order) %>)"><%= title %></a></td>
    <% } %>

    </tr>

    <logic:iterate id="mailData" name="wml140Form" property="wml140mailList" indexId="idx">
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

  <logic:notEmpty name="wml140Form" property="wml140mailListPageCombo">
  <tr>
  <td width="100%" align="right" colspan="2">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select styleId="wml140mailListPageTop" property="wml140mailListPageTop" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="wml140Form" property="wml140mailListPageCombo" value="value" label="label" />
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
          <input type="button" name="btn_add" class="btn_ok1" value="<gsmsg:write key="cmn.ok" />" onClick="buttonPush('confirm');">
          <input type="button" name="btn_filterSearch" class="btn_filterSearch" value="<gsmsg:write key="wml.wml140.03" />" onClick="buttonPush('filterSearch');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('filterList');">
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