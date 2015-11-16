<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ page import="jp.groupsession.v2.sml.sml340.Sml340Form" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml140kn.06" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../smail/css/smail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../smail/js/sml340.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/smail/sml340kn">


<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

<html:hidden property="smlAccountSid" />
<html:hidden property="smlFilterCmdMode" />
<html:hidden property="smlEditFilterId" />
<html:hidden property="smlViewAccount" />
<html:hidden property="dspCount" />
<html:hidden property="sml330SortRadio" />

<html:hidden property="sml340initFlg" />
<html:hidden property="sml340FilterName" />
<html:hidden property="sml340filterType" />
<html:hidden property="sml340condition1" />
<html:hidden property="sml340conditionType1" />
<html:hidden property="sml340conditionExs1" />
<html:hidden property="sml340conditionText1" />
<html:hidden property="sml340condition2" />
<html:hidden property="sml340conditionType2" />
<html:hidden property="sml340conditionExs2" />
<html:hidden property="sml340conditionText2" />
<html:hidden property="sml340condition3" />
<html:hidden property="sml340conditionType3" />
<html:hidden property="sml340conditionExs3" />
<html:hidden property="sml340conditionText3" />
<html:hidden property="sml340condition4" />
<html:hidden property="sml340conditionType4" />
<html:hidden property="sml340conditionExs4" />
<html:hidden property="sml340conditionText4" />
<html:hidden property="sml340condition5" />
<html:hidden property="sml340conditionType5" />
<html:hidden property="sml340conditionExs5" />
<html:hidden property="sml340conditionText5" />

<html:hidden property="sml340actionLabel" />
<html:hidden property="sml340actionLabelValue" />
<html:hidden property="sml340actionRead" />
<html:hidden property="sml340actionDust" />

<html:hidden property="sml240keyword" />
<html:hidden property="sml240group" />
<html:hidden property="sml240user" />
<html:hidden property="sml240svKeyword" />
<html:hidden property="sml240svGroup" />
<html:hidden property="sml240svUser" />
<html:hidden property="sml240sortKey" />
<html:hidden property="sml240order" />
<html:hidden property="sml240searchFlg" />



<html:hidden property="sml340tempFile" />
<html:hidden property="sml340doFilter" />

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
      <bean:write name="sml340knForm" property="sml330accountName" />
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.84" /></span></td>
    <td align="left" class="td_wt" width="75%">
      <bean:write name="sml340knForm" property="sml340FilterName" />
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.40" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340filterTypeView" /></span>&nbsp;</p>
    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td class="table_bg_A5B4E1" width="25%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.05" /></span></td>
    <td align="left" class="td_wt" width="75%"><bean:write name="sml340knForm" property="sml340conditionType1View" />&nbsp;<bean:write name="sml340knForm" property="sml340conditionExs1View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340conditionText1" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.04" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="sml340knForm" property="sml340conditionType2View" />&nbsp;<bean:write name="sml340knForm" property="sml340conditionExs2View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340conditionText2" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.03" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="sml340knForm" property="sml340conditionType3View" />&nbsp;<bean:write name="sml340knForm" property="sml340conditionExs3View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340conditionText3" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.02" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="sml340knForm" property="sml340conditionType4View" />&nbsp;<bean:write name="sml340knForm" property="sml340conditionExs4View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340conditionText4" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml140kn.01" /></span></td>
    <td align="left" class="td_wt">
    <bean:write name="sml340knForm" property="sml340conditionType5View" />&nbsp;<bean:write name="sml340knForm" property="sml340conditionExs5View" />&nbsp;&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340conditionText5" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td align="left" class="td_wt">
    <logic:equal name="sml340knForm" property="sml340tempFile" value="1">
      <gsmsg:write key="wml.14" />
    </logic:equal>
    </td>
    </tr>
    </table>

    <p><gsmsg:write key="wml.56" /></p>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <logic:equal name="sml340knForm" property="sml340actionLabel" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%">
        <gsmsg:write key="wml.75" />&nbsp;<span class="text_base5"><bean:write name="sml340knForm" property="sml340LabelView" /></span>
      </td>
      </tr>
    </logic:equal>

    <logic:equal name="sml340knForm" property="sml340actionRead" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%"><gsmsg:write key="cmn.mark.read" /></td>
      </tr>
    </logic:equal>

    <logic:equal name="sml340knForm" property="sml340actionDust" value="1">
      <tr>
      <td align="left" class="td_wt" width="100%"><gsmsg:write key="wml.91" /></td>
      </tr>
    </logic:equal>


    </table>

  </td>
  </tr>

  <logic:notEmpty name="sml340knForm" property="sml340mailList">
  <tr><td>&nbsp;</td></tr>

  <tr>
  <td>
    <table width="100% class="mailList">
      <tr>
      <td width="70%" align="left" nowrap>
      <gsmsg:write key="wml.85" />
      </td>
      <td width="30%" align="right" nowrap>
        <logic:notEmpty name="sml340knForm" property="sml340mailListPageCombo">
          <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
          <html:select styleId="sml340mailListPageTop" property="sml340mailListPageTop" onchange="selectPage(0);" styleClass="text_i">
          <html:optionsCollection name="sml340knForm" property="sml340mailListPageCombo" value="value" label="label" />
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

    <bean:define id="sml340SortKey" name="sml340knForm" property="sml340mailListSortKey" type="java.lang.Integer" />
    <bean:define id="sml340Order" name="sml340knForm" property="sml340mailListOrder" type="java.lang.Integer" />
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

    <logic:iterate id="mailData" name="sml340knForm" property="sml340mailList" indexId="idx">
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

  <logic:notEmpty name="sml340knForm" property="sml340mailListPageCombo">
  <tr>
  <td width="100%" align="right">
    <a href="javascript:void(0);" onClick="return buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" src="../common/images/arrow2_l.gif" border="0"></a>
    <html:select styleId="sml340mailListPageTop" property="sml340mailListPageTop" onchange="selectPage(1);" styleClass="text_i">
      <html:optionsCollection name="sml340knForm" property="sml340mailListPageCombo" value="value" label="label" />
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