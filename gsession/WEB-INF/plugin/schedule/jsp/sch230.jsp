<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.sch.sch230.Sch230Const" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>
<head>
  <title>[GroupSession]<gsmsg:write key="schedule.sch230.01" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <link rel="stylesheet" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../schedule/js/sch230.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
</head>

<body class="body_03">

<html:form action="/schedule/sch230">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="sch230editMode" value="">
<input type="hidden" name="sch230editData" value="">

<%@ include file="/WEB-INF/plugin/schedule/jsp/sch080_hiddenParams.jsp" %>

<logic:notEmpty name="sch230Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch230Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch230Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch230Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch230Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch230Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch230Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch230Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch230Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch230Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sch230svKeyword" />
<html:hidden property="sch230sortKey" />
<html:hidden property="sch230order" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="60%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch230.01" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('addSpAccess', 0);">
          <input type="button" name="btn_del" class="btn_dell_n1" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('spAccessDelete');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('sch230back');">
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
    <html:text name="sch230Form" property="sch230keyword" maxlength="50" style="width:183px;" />
    <input type="button" onclick="buttonPush('search');" class="btn_base0" value="<gsmsg:write key="cmn.search" />">
    </p>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left" width="100%">&nbsp;</td>

      <logic:notEmpty name="sch230Form" property="pageCombo">
      <td align="right" nowrap>
          <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
          <html:select name="sch230Form" property="sch230pageTop" styleClass="text_i" onchange="changePage(0);">
          <html:optionsCollection name="sch230Form" property="pageCombo" />
          </html:select>
          <a href="#" onClick="buttonPush('nextPage');"><img alt="<gsmsg:write key="cmn.next.page" />" src="../common/images/arrow2_r.gif" border="0" height="20" width="20"></a>
      </td>
      </logic:notEmpty>
    </tr>
    </table>

    <logic:notEmpty name="sch230Form" property="spAccessList">

    <bean:define id="orderValue" name="sch230Form" property="sch230order" type="java.lang.Integer" />
    <%  jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String destList = gsMsg.getMessage(request, "schedule.sch240.04");
        String user = gsMsg.getMessage(request, "cmn.employer");
        String down = gsMsg.getMessage(request, "tcd.tcd040.23");
        String up = gsMsg.getMessage(request, "tcd.tcd040.22");
    %>
    <% String orderLeft = ""; %>
    <% String orderRight = up; %>
    <%
        String nextOrder = String.valueOf(Sch230Const.ORDER_DESC);
    %>
    <%
        if (orderValue.intValue() == Sch230Const.ORDER_DESC) {
    %>
    <%
        orderLeft = "";
    %>
    <%
        orderRight = down;
    %>
    <%
        nextOrder = String.valueOf(Sch230Const.ORDER_ASC);
    %>
    <%
        }
    %>

    <bean:define id="sortValue" name="sch230Form" property="sch230sortKey" type="java.lang.Integer" />
    <%
        String[] orderList = {String.valueOf(Sch230Const.ORDER_ASC)};
    %>
    <%
        String[] titleList = {destList};
    %>
    <%
        int titleIndex = 0;
    %>
    <%
        if (sortValue.intValue() == Sch230Const.SKEY_USER) { titleIndex = 1; }
    %>
    <%
        titleList[titleIndex] = orderLeft + titleList[titleIndex] + orderRight;
    %>
    <%
        orderList[titleIndex] = nextOrder;
    %>

    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="1%">
    <input type="checkbox" name="sch240AllCheck" value="1" onClick="chgCheckAll('sch240AllCheck', 'sch230selectSpAccessList');chgCheckAllChange('sch240AllCheck', 'sch230selectSpAccessList');">
    </th>
    <th align="center" class="table_bg_7D91BD" width="45%">
    <a href="#" onClick="return sort(<%=String.valueOf(Sch230Const.SKEY_ACCOUNTNAME)%>, <%= orderList[0] %>);"><span class="text_tlw"><%= titleList[0] %></span></a>
    </th>
    <th align="center" class="table_bg_7D91BD" width="54%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <logic:iterate id="accessData" name="sch230Form" property="spAccessList" indexId="idx" type="jp.groupsession.v2.sch.sch230.Sch230SpAccessModel">

    <bean:define id="backclass" value="td_line_color" />
    <bean:define id="backclass_no_edit" value="td_line_no_edit_color" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="back_no_edit" value="<%= String.valueOf(backclass_no_edit) + String.valueOf(backpat) %>" />

    <tr class="<%= String.valueOf(back) %>" id="<bean:write name="accessData" property="ssaSid" />">

    <td class="prj_td" align="center">

      <% if (Integer.valueOf(backpat) == 1) { %>
        <html:multibox name="sch230Form" property="sch230selectSpAccessList" onclick="backGroundSetting(this, '1');">
          <bean:write name="accessData" property="ssaSid" />
        </html:multibox>
      <% } else { %>
        <html:multibox name="sch230Form" property="sch230selectSpAccessList" onclick="backGroundSetting(this, '2');">
          <bean:write name="accessData" property="ssaSid" />
        </html:multibox>
      <% } %>

    </td>

    <td align="left" class="prj_td">
      <a href="#" onClick="return editAccess(<bean:write name="accessData" property="ssaSid" />)"; class="text_link"><bean:write name="accessData" property="name" /></a>
    </td>
    <td align="left" class="prj_td"><span class="text_base"><bean:write name="accessData" property="viewBiko" filter="false" /></span></td>
    </tr>
    </logic:iterate>

    </table>
    </logic:notEmpty>
  </td>
  </tr>

  <tr><td>&nbsp;</td></tr>

  <logic:notEmpty name="sch230Form" property="pageCombo">
    <tr>
    <td align="right" nowrap>
        <a href="#" onClick="buttonPush('prevPage');"><img alt="<gsmsg:write key="cmn.previous.page" />" src="../common/images/arrow2_l.gif" border="0" height="20" width="20"></a>
        <html:select name="sch230Form" property="sch230pageBottom" styleClass="text_i" onchange="changePage(1);">
        <html:optionsCollection name="sch230Form" property="pageCombo" />
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