<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man170.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man170.1" /></title>
</head>

<body class="body_03" onload="tarminalChange(0);">
<html:form action="/main/man170">
<input type="hidden" name="CMD" value="">
<html:hidden property="man170SortKey" />
<html:hidden property="man170OrderKey" />
<html:hidden property="man050SelectedUsrSid" />
<html:hidden property="man050Backurl" />
<html:hidden property="man050grpSid" />
<html:hidden property="man050SortKey" />
<html:hidden property="man050OrderKey" />

<html:hidden property="man050FrYear" />
<html:hidden property="man050FrMonth" />
<html:hidden property="man050FrDay" />
<html:hidden property="man050ToYear" />
<html:hidden property="man050ToMonth" />
<html:hidden property="man050ToDay" />

<html:hidden property="man050cmdMode" />
<html:hidden property="man050usrSid" />
<html:hidden property="man050Terminal" />
<html:hidden property="man050Car" />
<html:hidden property="man050PageTop" />
<html:hidden property="man050PageBottom" />


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center" cellpadding="0" cellspacing="5" width="100%">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td>
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../main/images/header_main_01.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
      <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="main.man170.1" /> ]</td>
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
      </tr>
      </table>

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="70%">&nbsp;</td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
      <td class="header_glay_bg" width="50%">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('backToLoginList');">
      </td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
      </table>
    </td>
    </tr>
    <tr>
    <td>
      <img src="../common/images/spacer.gif" width="1px" height="10px" alt="">
    </td>
    </tr>
    <tr>
    <td width="100%">

      <table summary="" width="0%" cellpadding="0" cellspacing="0" border="0" style="padding-bottom:5px;">
      <tr>
      <td colspan="6" align="left" style="padding-bottom:15px;" nowrap>
        <table summary="" class="tl0 table_td_border2" cellpadding="0" cellspacing="0">
        <tr>
        <td class="table_bg_7D91BD" align="center" width="80" nowrap><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></td>
        <td align="left" width="200" nowrap>
          <span class="text_base">
          <logic:equal name="man170Form" property="man170UserJtkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
            <s><bean:write name="man170Form" property="man170UserName" /></s>
          </logic:equal>
          <logic:notEqual name="man170Form" property="man170UserJtkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
            <bean:write name="man170Form" property="man170UserName" />
          </logic:notEqual>
          </span>
        </td>
        </tr>
        </table>
      </td>
      </tr>

      <tr>
      <td align="left" width="0%" nowrap>
        <span class="text_bb1"><gsmsg:write key="main.man050.6" />：</span>
        <span class="text_base">
          <html:radio property="man170Terminal" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_ALL) %>" styleId="terminal0" onclick="return tarminalChange(1)" /><label for="terminal0"><gsmsg:write key="cmn.all" /></label>
          <html:radio property="man170Terminal" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_PC) %>" styleId="terminal1" onclick="return tarminalChange(1)" /><label for="terminal1"><%= jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_PC_TEXT %></label>
          <html:radio property="man170Terminal" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.TERMINAL_KBN_MOBILE) %>" styleId="terminal2" onclick="return tarminalChange(1)" /><label for="terminal2"><gsmsg:write key="main.man120.4" /></label>
        </span>
      </td>

      <td align="left" width="0%" nowrap>
        <img src="../common/images/spacer.gif" width="20px" height="1px" alt="">
      </td>

      <td align="left" width="0%" nowrap>
        <span class="text_bb1"><gsmsg:write key="cmn.careers" />：</span>
        <span class="text_base">
          <html:radio property="man170Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_PC_ALL) %>" styleId="car0" onclick="return buttonPush('research');" /><label for="car0"><gsmsg:write key="cmn.all" /></label>
          <html:radio property="man170Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_DOCOMO) %>" styleId="car2" onclick="return buttonPush('research');" /><label for="car2"><%= jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_DOCOMO_TEXT %></label>
          <html:radio property="man170Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_KDDI) %>" styleId="car3" onclick="return buttonPush('research');" /><label for="car3"><%= jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_KDDI_TEXT %></label>
          <html:radio property="man170Car" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_SOFTBANK) %>" styleId="car4" onclick="return buttonPush('research');" /><label for="car4"><%= jp.groupsession.v2.cmn.GSConstCommon.CAR_KBN_SOFTBANK_TEXT %></label>
        </span>
      </td>

      <td align="right" width="100%"><img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('pageleft');" style="cursor:pointer;">&nbsp;</td>
      <td align="right" width="0%">
        <logic:notEmpty name="man170Form" property="man170PageList">
          <html:select property="man170PageTop" onchange="return changePage('0');">
            <html:optionsCollection name="man170Form" property="man170PageList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
        <logic:empty name="man170Form" property="man170PageList">
          <html:select property="man170PageTop">
            <option value="1" class="text_i">1 / 1</option>
          </html:select>
        </logic:empty>
      </td>
      <td align="right" width="0%">&nbsp;<img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');" style="cursor:pointer;"></td>
      </tr>
      </table>

    </td>
    </tr>
    </table>

    <table summary="" class="tl0 table_td_border2" width="100%" cellpadding="0" cellspacing="0">
    <tr class="table_bg_7D91BD">

    <logic:equal name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_DATE) %>">

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_DATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.5" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_DATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.5" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_DATE) %>">
      <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_DATE) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.5" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_TERMINAL) %>">

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="10%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_TERMINAL) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.6" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="10%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_TERMINAL) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.6" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_TERMINAL) %>">
      <th width="10%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_TERMINAL) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="main.man050.6" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_IP) %>">

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_IP) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.ipaddress" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_IP) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.ipaddress" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_IP) %>">
      <th width="20%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_IP) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.ipaddress" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_CAR) %>">

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_CAR) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.careers" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_CAR) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.careers" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_CAR) %>">
      <th width="25%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_CAR) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.careers" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_UID) %>">

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>">
        <th width="35%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_UID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.identification.number" />▲</span></a></th>
      </logic:equal>

      <logic:equal name="man170Form" property="man170OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>">
        <th width="35%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_UID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.identification.number" />▼</span></a></th>
      </logic:equal>

    </logic:equal>

    <logic:notEqual name="man170Form" property="man170SortKey" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_UID) %>">
      <th width="35%" nowrap><a href="#" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.SORT_UID) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>')"><span class="text_tlw"><gsmsg:write key="cmn.identification.number" /></span></a></th>
    </logic:notEqual>

    <bean:define id="mod" value="0" />
    <logic:notEmpty name="man170Form" property="man170LoginHistoryList">
      <logic:iterate id="history" name="man170Form" property="man170LoginHistoryList" scope="request" indexId="idx">

      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type_usr" />
      </logic:notEqual>

      <tr class="<bean:write name="tblColor" />">
      <td align="center"><span class="text_base"><bean:write name="history" property="loginTime" /></span></td>
      <td align="left"><span class="text_base"><bean:write name="history" property="terminalName" /></span></td>
      <td align="left"><span class="text_base"><bean:write name="history" property="clhIp" /></span></td>
      <td align="left"><span class="text_base"><bean:write name="history" property="carName" /></span></td>
      <td align="left"><span class="text_base"><bean:write name="history" property="clhUid" /></span></td>
      </tr>

      </logic:iterate>
    </logic:notEmpty>

    </tr>

    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">

      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="100%">
        <img src="../common/images/spacer.gif" width="1px" height="10px" alt="">
      </td>
      </tr>
      <tr>
      <td>
        <table summary="" width="0%" cellpadding="0" cellspacing="0" border="0" style="padding-bottom:5px;">
        <tr>
        <td align="left" style="padding-bottom:15px;" nowrap>

        <td align="right" width="100%"><img alt="<gsmsg:write key="cmn.previous.page" />" height="20" src="../common/images/arrow2_l.gif" class="img_bottom" width="20" border="0" onClick="buttonPush('pageleft');" style="cursor:pointer;">&nbsp;</td>
        <td align="right" width="0%">
          <logic:notEmpty name="man170Form" property="man170PageList">
            <html:select property="man170PageBottom" onchange="return changePage('1');">
              <html:optionsCollection name="man170Form" property="man170PageList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
          <logic:empty name="man170Form" property="man170PageList">
            <html:select property="man170PageBottom">
              <option value="1" class="text_i">1 / 1</option>
            </html:select>
          </logic:empty>
        </td>
        <td align="right" width="0%">&nbsp;<img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('pageright');" style="cursor:pointer;"></td>
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

</td>
</tr>
</table>


</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>