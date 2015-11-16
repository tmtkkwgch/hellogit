<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.sml.sml360.Sml360Form" %>
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
  <script language="JavaScript" src="../smail/js/sml360.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03" onload="changeFilterInput();">

<html:form action="/smail/sml360">


<input type="hidden" id="CMD" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="smlCmdMode" />
<html:hidden property="smlAccountSid" />
<html:hidden property="smlViewAccount" />
<html:hidden property="smlAccountMode" />
<html:hidden property="smlFilterCmdMode" />
<html:hidden property="smlEditFilterId" />
<html:hidden property="sml360initFlg" />
<html:hidden property="dspCount" />
<html:hidden property="sml350SortRadio" />

<html:hidden property="sml360viewMailList" />
<html:hidden property="sml360mailListSortKey" />
<html:hidden property="sml360mailListOrder" />
<html:hidden property="sml360svFilterType" />
<html:hidden property="sml360svCondition1" />
<html:hidden property="sml360svConditionType1" />
<html:hidden property="sml360svConditionExs1" />
<html:hidden property="sml360svConditionText1" />
<html:hidden property="sml360svCondition2" />
<html:hidden property="sml360svConditionType2" />
<html:hidden property="sml360svConditionExs2" />
<html:hidden property="sml360svConditionText2" />
<html:hidden property="sml360svCondition3" />
<html:hidden property="sml360svConditionType3" />
<html:hidden property="sml360svConditionExs3" />
<html:hidden property="sml360svConditionText3" />
<html:hidden property="sml360svCondition4" />
<html:hidden property="sml360svConditionType4" />
<html:hidden property="sml360svConditionExs4" />
<html:hidden property="sml360svConditionText4" />
<html:hidden property="sml360svCondition5" />
<html:hidden property="sml360svConditionType5" />
<html:hidden property="sml360svConditionExs5" />
<html:hidden property="sml360svConditionText5" />
<html:hidden property="sml360svTempFile" />


<input type="hidden" name="sml360actionSendValueDelIdx" value="">

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
      <bean:write name="sml360Form" property="sml350accountName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
    <td align="left" class="td_wt" width="70%">
      <html:text name="sml360Form" property="sml360FilterName" maxlength="100" style="width:100%;" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.40" />&nbsp;<html:radio name="sml360Form" property="sml360filterType" value="0" styleId="filtype1" /><label for="filtype1"><gsmsg:write key="wml.wml140.01" /></label>
                                &nbsp;<html:radio name="sml360Form" property="sml360filterType" value="1" styleId="filtype2" /><label for="filtype2"><gsmsg:write key="wml.containing.either" /></label></p>
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
      conditionCheck = "sml360condition" + condition;
      conditionType = "sml360conditionType" + condition;
      conditionExs = "sml360conditionExs" + condition;
      conditionText = "sml360conditionText" + condition;
      number = String.valueOf(i);
%>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140.02" /><%=condition%></span></td>
    <td align="left" class="td_wt" width="75%" nowrap>
      <html:checkbox name="sml360Form" property="<%=conditionCheck%>" value="<%= number %>" onclick="changeFilterInput();" />&nbsp;&nbsp;

      <logic:notEmpty name="sml360Form" property="conditionList1">
        <html:select property="<%=conditionType%>" size="1">
          <html:optionsCollection name="sml360Form" property="conditionList1" value="value" label="label" />
        </html:select>
      </logic:notEmpty>

      <logic:notEmpty name="sml360Form" property="conditionList2">
        <html:select property="<%=conditionExs%>" size="1">
          <html:optionsCollection name="sml360Form" property="conditionList2" value="value" label="label" />
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
       <html:checkbox name="sml360Form" property="sml360tempFile" value="1" /><gsmsg:write key="wml.14" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.56" /></p>


    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="sml360Form" property="sml360actionLabel" value="1" /></td>
    <td align="left" class="td_wt" width="100%">
    <gsmsg:write key="wml.75" />
    <logic:notEmpty name="sml360Form" property="conditionList2">
      <html:select property="sml360actionLabelValue" size="1">
        <html:optionsCollection name="sml360Form" property="lbList" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    </td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1" valign="middle"><html:checkbox name="sml360Form" property="sml360actionRead" value="1" /></td>
    <td align="left" class="td_wt" width="100%"><gsmsg:write key="cmn.mark.read" /></td>
    </tr>
    <tr>
    <td align="left" class="table_bg_A5B4E1"><html:checkbox name="sml360Form" property="sml360actionDust" value="1" /></td>
    <td align="left" class="td_wt" width="100%"><gsmsg:write key="wml.91" /></td>
    </tr>
    </table>

  </td>
  </tr>

  <logic:notEmpty name="sml360Form" property="sml360mailList">
  <tr><td>&nbsp;</td></tr>

  <tr>
  <td>
    <table width="100% class="mailList">
    <tr>
    <td width="70%" align="left" nowrap>
    <html:checkbox styleId="sml360doFilter" name="sml360Form" property="sml360doFilter" value="<%= String.valueOf(Sml360Form.SML360_DOFILTER_YES) %>" /><label for="sml360doFilter"><gsmsg:write key="wml.85" /></label>
    </td>
    <td width="30%" align="right" nowrap>
      <logic:notEmpty name="sml360Form" property="sml360mailListPageCombo">
      <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
        <html:select styleId="sml360mailListPageTop" property="sml360mailListPageTop" onchange="selectPage(0);" styleClass="text_i">
        <html:optionsCollection name="sml360Form" property="sml360mailListPageCombo" value="value" label="label" />
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

    <bean:define id="sml360SortKey" name="sml360Form" property="sml360mailListSortKey" type="java.lang.Integer" />
    <bean:define id="sml360Order" name="sml360Form" property="sml360mailListOrder" type="java.lang.Integer" />
    <% String[] widthList = new String[] {"28", "50", "20"};
      jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String from = gsMsg.getMessage(request, "wml.from");
        String subject = gsMsg.getMessage(request, "cmn.subject");
        String date = gsMsg.getMessage(request, "cmn.date");
        String down = gsMsg.getMessage(request, "tcd.tcd040.22");
        String up = gsMsg.getMessage(request, "tcd.tcd040.23");
    %>
    <% String[] titleList = new String[] {from, subject, date}; %>
    <% int[] sortKeyList = new int[] {Sml360Form.SML360_SKEY_FROM, Sml360Form.SML360_SKEY_SUBJECT, Sml360Form.SML360_SKEY_DATE}; %>

    <% for (int i = 0; i < widthList.length; i++) { %>
    <%   String title = titleList[i]; %>
    <%   int order = Sml360Form.SML360_ORDER_ASC; %>
    <%   if (sortKeyList[i] == sml360SortKey.intValue()) { %>
    <%     if (sml360Order.intValue() == Sml360Form.SML360_ORDER_DESC) { %>
    <%       title = title + " " + down; %>
    <%     } else { %>
    <%       title = title + " " + up; %>
    <%       order = Sml360Form.SML360_ORDER_DESC; %>
    <%     } %>
    <%   } %>
    <td width="<%= widthList[i] %>%"><a href="#" onClick="return sml360Sort(<%= String.valueOf(sortKeyList[i]) %>, <%= String.valueOf(order) %>)"><%= title %></a></td>
    <% } %>

    </tr>

    <logic:iterate id="mailData" name="sml360Form" property="sml360mailList" indexId="idx">
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

  <logic:notEmpty name="sml360Form" property="sml360mailListPageCombo">
  <tr>
  <td width="100%" align="right" colspan="2">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select styleId="sml360mailListPageTop" property="sml360mailListPageTop" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="sml360Form" property="sml360mailListPageCombo" value="value" label="label" />
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