<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.sml.sml340.Sml340Form" %>
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
  <link rel="stylesheet" href="../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

  <gsjsmsg:js filename="gsjsmsg.js"/>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../smail/js/sml340.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="changeFilterInput();">

<html:form action="/smail/sml340">


<input type="hidden" id="CMD" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="smlAccountSid" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlFilterCmdMode" />
<html:hidden property="smlEditFilterId" />
<html:hidden property="sml340initFlg" />
<html:hidden property="dspCount" />
<html:hidden property="sml330SortRadio" />

<html:hidden property="sml340viewMailList" />
<html:hidden property="sml340mailListSortKey" />
<html:hidden property="sml340mailListOrder" />
<html:hidden property="sml340svFilterType" />
<html:hidden property="sml340svCondition1" />
<html:hidden property="sml340svConditionType1" />
<html:hidden property="sml340svConditionExs1" />
<html:hidden property="sml340svConditionText1" />
<html:hidden property="sml340svCondition2" />
<html:hidden property="sml340svConditionType2" />
<html:hidden property="sml340svConditionExs2" />
<html:hidden property="sml340svConditionText2" />
<html:hidden property="sml340svCondition3" />
<html:hidden property="sml340svConditionType3" />
<html:hidden property="sml340svConditionExs3" />
<html:hidden property="sml340svConditionText3" />
<html:hidden property="sml340svCondition4" />
<html:hidden property="sml340svConditionType4" />
<html:hidden property="sml340svConditionExs4" />
<html:hidden property="sml340svConditionText4" />
<html:hidden property="sml340svCondition5" />
<html:hidden property="sml340svConditionType5" />
<html:hidden property="sml340svConditionExs5" />
<html:hidden property="sml340svConditionText5" />
<html:hidden property="sml340svTempFile" />

<html:hidden property="sml240keyword" />
<html:hidden property="sml240group" />
<html:hidden property="sml240user" />
<html:hidden property="sml240svKeyword" />
<html:hidden property="sml240svGroup" />
<html:hidden property="sml240svUser" />
<html:hidden property="sml240sortKey" />
<html:hidden property="sml240order" />
<html:hidden property="sml240searchFlg" />

<input type="hidden" name="sml340actionSendValueDelIdx" value="">

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
      <bean:write name="sml340Form" property="sml330accountName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
    <td align="left" class="td_wt" width="70%">
      <html:text name="sml340Form" property="sml340FilterName" maxlength="100" style="width:100%;" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.40" />&nbsp;<html:radio name="sml340Form" property="sml340filterType" value="0" styleId="filtype1" /><label for="filtype1"><gsmsg:write key="wml.wml140.01" /></label>
                                &nbsp;<html:radio name="sml340Form" property="sml340filterType" value="1" styleId="filtype2" /><label for="filtype2"><gsmsg:write key="wml.containing.either" /></label></p>
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
      conditionCheck = "sml340condition" + condition;
      conditionType = "sml340conditionType" + condition;
      conditionExs = "sml340conditionExs" + condition;
      conditionText = "sml340conditionText" + condition;
      number = String.valueOf(i);
%>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140.02" /><%=condition%></span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <html:checkbox name="sml340Form" property="<%=conditionCheck%>" value="<%= number %>" onclick="changeFilterInput();" />&nbsp;&nbsp;

      <logic:notEmpty name="sml340Form" property="conditionList1">
        <html:select property="<%=conditionType%>" size="1">
          <html:optionsCollection name="sml340Form" property="conditionList1" value="value" label="label" />
        </html:select>
      </logic:notEmpty>

      <logic:notEmpty name="sml340Form" property="conditionList2">
        <html:select property="<%=conditionExs%>" size="1">
          <html:optionsCollection name="sml340Form" property="conditionList2" value="value" label="label" />
        </html:select>
      </logic:notEmpty>

      <html:text property="<%=conditionText%>" maxlength="256" style="width:586px;" />
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
       <html:checkbox name="sml340Form" property="sml340tempFile" value="1" /><gsmsg:write key="wml.14" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.56" /></p>


    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="sml340Form" property="sml340actionLabel" value="1" /></td>
    <td align="left" class="td_wt" width="100%">
    <gsmsg:write key="wml.75" />
    <logic:notEmpty name="sml340Form" property="conditionList2">
      <html:select property="sml340actionLabelValue" size="1">
        <html:optionsCollection name="sml340Form" property="lbList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1" valign="middle"><html:checkbox name="sml340Form" property="sml340actionRead" value="1" /></td>
    <td align="left" class="td_wt" width="100%"><gsmsg:write key="cmn.mark.read" /></td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="sml340Form" property="sml340actionDust" value="1" /></td>
    <td align="left" class="td_wt" width="100%"><gsmsg:write key="wml.91" /></td>
    </tr>
    </table>

  </td>
  </tr>

  <logic:notEmpty name="sml340Form" property="sml340mailList">
  <tr><td>&nbsp;</td></tr>

  <tr>
  <td>
    <table width="100% class="mailList">
    <tr>
    <td width="70%" align="left" nowrap>
    <html:checkbox styleId="sml340doFilter" name="sml340Form" property="sml340doFilter" value="<%= String.valueOf(Sml340Form.SML340_DOFILTER_YES) %>" /><label for="sml340doFilter"><gsmsg:write key="wml.85" /></label>
    </td>
    <td width="30%" align="right" nowrap>
      <logic:notEmpty name="sml340Form" property="sml340mailListPageCombo">
      <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select styleId="sml340mailListPageTop" property="sml340mailListPageTop" onchange="selectPage(0);" styleClass="text_i">
        <html:optionsCollection name="sml340Form" property="sml340mailListPageCombo" value="value" label="label" />
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

    <bean:define id="sml340SortKey" name="sml340Form" property="sml340mailListSortKey" type="java.lang.Integer" />
    <bean:define id="sml340Order" name="sml340Form" property="sml340mailListOrder" type="java.lang.Integer" />
    <% String[] widthList = new String[] {"28", "50", "20"};
      jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String from = gsMsg.getMessage(request, "wml.from");
        String subject = gsMsg.getMessage(request, "cmn.subject");
        String date = gsMsg.getMessage(request, "cmn.date");
        String down = gsMsg.getMessage(request, "tcd.tcd040.22");
        String up = gsMsg.getMessage(request, "tcd.tcd040.23");
    %>
    <% String[] titleList = new String[] {from, subject, date}; %>
    <% int[] sortKeyList = new int[] {Sml340Form.SML340_SKEY_FROM, Sml340Form.SML340_SKEY_SUBJECT, Sml340Form.SML340_SKEY_DATE}; %>

    <% for (int i = 0; i < widthList.length; i++) { %>
    <%   String title = titleList[i]; %>
    <%   int order = Sml340Form.SML340_ORDER_ASC; %>
    <%   if (sortKeyList[i] == sml340SortKey.intValue()) { %>
    <%     if (sml340Order.intValue() == Sml340Form.SML340_ORDER_DESC) { %>
    <%       title = title + " " + down; %>
    <%     } else { %>
    <%       title = up + " " + title; %>
    <%       order = Sml340Form.SML340_ORDER_DESC; %>
    <%     } %>
    <%   } %>
    <td width="<%= widthList[i] %>%"><a href="#" onClick="return sml340Sort(<%= String.valueOf(sortKeyList[i]) %>, <%= String.valueOf(order) %>)"><%= title %></a></td>
    <% } %>

    </tr>

    <logic:iterate id="mailData" name="sml340Form" property="sml340mailList" indexId="idx">
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
    <td>
      <logic:equal name="mailData" property="accountJkbn" value="0">
        <bean:write name="mailData" property="accountName" />
      </logic:equal>
      <logic:notEqual name="mailData" property="accountJkbn" value="0">
        <del><bean:write name="mailData" property="accountName" /></del>
      </logic:notEqual>
    </td>
    <td><a href="javascript:void(0);" onClick="openDetail('<bean:write name="mailData" property="smlSid" />');"><span class="text_link"><bean:write name="mailData" property="smsTitle" /></span></a></td>
    <td><bean:write name="mailData" property="strDate" /></td>
    </tr>
    </logic:iterate>

    </table>
  </td>
  </tr>
  <tr><td>&nbsp;</td></tr>

  <logic:notEmpty name="sml340Form" property="sml340mailListPageCombo">
  <tr>
  <td width="100%" align="right" colspan="2">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select styleId="sml340mailListPageTop" property="sml340mailListPageTop" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="sml340Form" property="sml340mailListPageCombo" value="value" label="label" />
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

<div id="mail_detail_pop" title="" style="display:none">

  <table width="100%" height="100%">
    <tr id="mail_kakunin_body_tr"></tr>
    <tr><td style="padding-left:20px;"><div class="clear_div mail_check_body_txt" style="width:550px;height:300px;overflow-y:auto;overflow-x:auto;"></div></td></tr>
  </table>

</div>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>