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
<title>[GroupSession] <gsmsg:write key="tcd.49" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../timecard/js/tcd040.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/timecard/tcd040">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />

<logic:notEmpty name="tcd040Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd040Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="tcd040SearchFlg" />
<html:hidden property="tcd040SltYearSv" />
<html:hidden property="tcd040SltMonthSv" />
<html:hidden property="tcd040SltYearToSv" />
<html:hidden property="tcd040SltMonthToSv" />
<html:hidden property="tcd040SltGroupSv" />
<html:hidden property="tcd040ZangyoCkSv" />
<html:hidden property="tcd040SinyaCkSv" />
<html:hidden property="tcd040KyujituCkSv" />
<html:hidden property="tcd040ChikokuCkSv" />
<html:hidden property="tcd040SoutaiCkSv" />
<html:hidden property="tcd040KekkinCkSv" />
<html:hidden property="tcd040KeityoCkSv" />
<html:hidden property="tcd040YuukyuCkSv" />
<html:hidden property="tcd040DaikyuCkSv" />
<html:hidden property="tcd040SonotaCkSv" />
<html:hidden property="tcd040SortKey" />
<html:hidden property="tcd040OrderKey" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">

<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.49" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('tcd040_back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <table width="100%">
      <tr><td><span class="TXT02"><html:errors/></span></td></tr>
      </table>
    </logic:messagesPresent>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="5" width="100%">
    <tr>
    <td width="60%" valign="top">
      <table class="tl0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td class="table_bg_7D91BD">
      <span class="text_left"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.04" /></span></span>
      <span class="text_right"><input type="button" name="btn_usrimp" class="btn_search_n1" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush('tcd040_search');"></span>
      </td>
      </tr>
      <tr>
      <td class="td_type1">
        <table width="100%" border="0" cellspacing="0" cellpadding="5">
        <tr>
        <td width="100%" colspan="3">
        <html:select name="tcd040Form" property="tcd040SltYear" >
          <html:optionsCollection name="tcd040Form" property="tcd040YearLabelList" />            </html:select>
        <html:select name="tcd040Form" property="tcd040SltMonth" >
          <html:optionsCollection name="tcd040Form" property="tcd040MonthLabelList" />
        </html:select>
        &nbsp;<gsmsg:write key="tcd.153" />&nbsp;
        <html:select name="tcd040Form" property="tcd040SltYearTo" >
          <html:optionsCollection name="tcd040Form" property="tcd040YearLabelList" />            </html:select>
        <html:select name="tcd040Form" property="tcd040SltMonthTo" >
          <html:optionsCollection name="tcd040Form" property="tcd040MonthLabelList" />
        </html:select>
        </td>
        </tr>

        <tr>
        <td width="100%" colspan="3">
        <span class="text_tlw_black"><gsmsg:write key="cmn.show.group" /></span>
        <html:select name="tcd040Form" property="tcd040SltGroup" styleClass="select01">
          <html:optionsCollection name="tcd040Form" property="tcd040GpLabelList" />
        </html:select>
        <input type="button" onclick="openGroupWindow(this.form.tcd040SltGroup, 'tcd040SltGroup', '0', '', 1)" class="group_btn" value="&nbsp;&nbsp;" id="tcd040GroupBtn">
        </td>
        </tr>

        <tr>
        <td width="100%" colspan="3">
        <html:checkbox name="tcd040Form" property="tcd040avgInZero" value="1" styleId="avgInZero" /><label for="avgInZero"><gsmsg:write key="tcd.tcd040.16" /></label>
        <hr style="border-color:#cccccc;">
        </td>
        </tr>

        <tr>
        <td valign="top">
        <html:multibox styleId="tcd040ZangyoCk" property="tcd040ZangyoCk" value="1"/><label for="tcd040ZangyoCk"><gsmsg:write key="tcd.tcd040.10" /></label><br>
        <html:multibox styleId="tcd040SinyaCk" property="tcd040SinyaCk" value="1"/><label for="tcd040SinyaCk"><gsmsg:write key="tcd.tcd040.08" /></label><br>
        <html:multibox styleId="tcd040KyujituCk" property="tcd040KyujituCk" value="1"/><label for="tcd040KyujituCk"><gsmsg:write key="tcd.tcd040.15" /></label>
        </td>
        <td valign="top">
        <html:multibox styleId="tcd040ChikokuCk" property="tcd040ChikokuCk" value="1"/><label for="tcd040ChikokuCk"><gsmsg:write key="tcd.tcd040.05" /></label><br>
        <html:multibox styleId="tcd040SoutaiCk" property="tcd040SoutaiCk" value="1"/><label for="tcd040SoutaiCk"><gsmsg:write key="tcd.tcd040.07" /></label><br>
        <html:multibox styleId="tcd040KekkinCk" property="tcd040KekkinCk" value="1"/><label for="tcd040KekkinCk"><gsmsg:write key="tcd.tcd040.13" /></label>
        </td>
        <td valign="top">
        <html:multibox styleId="tcd040KeityoCk" property="tcd040KeityoCk" value="1"/><label for="tcd040KeityoCk"><gsmsg:write key="tcd.tcd040.14" /></label><br>
        <html:multibox styleId="tcd040YuukyuCk" property="tcd040YuukyuCk" value="1"/><label for="tcd040YuukyuCk"><gsmsg:write key="tcd.tcd040.01" /></label><br>
        <html:multibox styleId="tcd040DaikyuCk" property="tcd040DaikyuCk" value="1"/><label for="tcd040DaikyuCk"><gsmsg:write key="tcd.tcd040.06" /></label><br>
        <html:multibox styleId="tcd040SonotaCk" property="tcd040SonotaCk" value="1"/><label for="tcd040SonotaCk"><gsmsg:write key="tcd.tcd040.17" /></label>
        </td>
        </tr>
        </table>
      </td>
      </tr>
      </table>
    </td>
    <td width="40%" valign="top">
      <table class="tl0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td class="table_bg_7D91BD">
      <span class="text_left"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.09" /></span></span>
      <span class="text_right"><input type="button" name="btn_usrimp" class="btn_csv_n2" value="<gsmsg:write key="cmn.export" />" onClick="buttonPush('tcd040_export');"></span>
      </td>
      </tr>
      <tr>
      <td class="td_type1">
      <ul>
      <logic:notEmpty name="tcd040Form" property="tcd040SearchList" scope="request">
      <logic:iterate id="searchList" name="tcd040Form" property="tcd040SearchList" scope="request">
      <li><bean:write name="searchList" /></li>
      </logic:iterate>
      </logic:notEmpty>
      </ul>
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
    <tr class="table_bg_7D91BD">

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SIMEI) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="25%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SIMEI) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.name" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="25%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SIMEI) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.name" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SIMEI) %>">
    <th width="35%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SIMEI) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.name" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SYAINNO) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SYAINNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.employee.staff.number" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SYAINNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.employee.staff.number" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SYAINNO) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SYAINNO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.employee.staff.number" /></span></a></th>
    </logic:notEqual>

    <th width="10%" colspan="2"><span class="text_tlwn"><gsmsg:write key="tcd.tcd010.16" /></span></th>
    <th width="10%" colspan="2"><span class="text_tlwn"><gsmsg:write key="tcd.tcd010.09" /></span></th>
    <th width="10%" colspan="2"><span class="text_tlwn"><gsmsg:write key="tcd.tcd010.06" /></span></th>
    <th width="10%" colspan="2"><span class="text_tlwn"><gsmsg:write key="tcd.tcd010.14" /></span></th>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_CHIKOKU) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_CHIKOKU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="tcd.18" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_CHIKOKU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.18" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_CHIKOKU) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_CHIKOKU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.18" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SOUTAI) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SOUTAI) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="tcd.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SOUTAI) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.22" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SOUTAI) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SOUTAI) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.22" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEKKIN) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEKKIN) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="tcd.34" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEKKIN) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.34" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEKKIN) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEKKIN) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.34" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEITYO) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEITYO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="tcd.35" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEITYO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.35" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEITYO) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KEITYO) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.35" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_YUUKYU) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_YUUKYU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="tcd.03" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_YUUKYU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.03" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_YUUKYU) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_YUUKYU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.03" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_DAIKYU) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_DAIKYU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="tcd.19" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_DAIKYU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.19" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_DAIKYU) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_DAIKYU) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.19" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SONOTA) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SONOTA) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="tcd.tcd040.22" /><br><gsmsg:write key="cmn.other" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SONOTA) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.other" /><br><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SONOTA) %>">
    <th width="5%" rowspan="2"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SONOTA) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.other" /></span></a></th>
    </logic:notEqual>

    </tr>

    <tr class="table_bg_7D91BD">

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADODAYS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADODAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADODAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADODAYS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADODAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADOHOURS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADOHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADOHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADOHOURS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KADOHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANDAYS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANDAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANDAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANDAYS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANDAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANHOURS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANHOURS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_ZANHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYADAYS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYADAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYADAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYADAYS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYADAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYAHOURS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYAHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYAHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYAHOURS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_SINYAHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /></span></a></th>
    </logic:notEqual>
<%-- 休出 --%>
    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEDAYS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEDAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEDAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEDAYS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEDAYS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.number.days" /></span></a></th>
    </logic:notEqual>

    <logic:equal name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEHOURS) %>">
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.22" /></span></a></th>
    </logic:equal>
    <logic:equal name="tcd040Form" property="tcd040OrderKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_DESC) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /><gsmsg:write key="tcd.tcd040.23" /></span></a></th>
    </logic:equal>
    </logic:equal>
    <logic:notEqual name="tcd040Form" property="tcd040SortKey" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEHOURS) %>">
    <th width="10%"><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.SORT_KYUDEHOURS) %>', '<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ORDER_KEY_ASC) %>')"><span class="text_tlwn"><gsmsg:write key="cmn.time" /></span></a></th>
    </logic:notEqual>
    </tr>

    <logic:notEmpty name="tcd040Form" property="tcd040ResultList" scope="request">
    <logic:iterate id="mngMdl" name="tcd040Form" property="tcd040ResultList" scope="request" indexId="idx">
    <!--背景-->
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"smail_td1", "smail_td2"}; %>
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>
    <!--基準-->
    <logic:equal name="idx" value="0">
    <tr class="td_type3">
    <td align="left" valign="middle" nowrap><span class="text_bb1"><gsmsg:write key="tcd.tcd040.03" /></span></td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="mngMdl" property="kadoBaseDaysStr" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="mngMdl" property="kadoBaseHoursStr" /></span></td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    <td align="right" valign="middle">&nbsp;</td>
    </tr>
    </logic:equal>



    <tr class="<%= tdColor %>">
    <td align="left" valign="middle" nowrap>
    <span class="normal_link"><bean:write name="mngMdl" property="userName" /></span>
    </td>
    <td align="left" valign="middle" nowrap>
    <span class="normal_link"><bean:write name="mngMdl" property="userSyainNo" /></span>
    </td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="kadoDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="kadoHoursStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="zangyoDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="zangyoHoursStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="sinyaDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="sinyaHoursStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="kyusyutuDays" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="kyusyutuHours" /></td>

    <td align="right" valign="middle"><bean:write name="mngMdl" property="chikokuTimesStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="soutaiTimesStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="kekkinDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="keityoDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="yuukyuDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="daikyuDaysStr" /></td>
    <td align="right" valign="middle"><bean:write name="mngMdl" property="sonotaDaysStr" /></td>
    </tr>
    </logic:iterate>


    <tr class="td_type3">
    <td align="left" valign="middle" nowrap><span class="text_bb1"><gsmsg:write key="tcd.tcd040.02" /></span></td>
    <td align="right" valign="middle">&nbsp;</td>
    <bean:define id="averageData" name="tcd040Form" property="averageData" />
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="kadoDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="kadoHours" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="zangyoDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="zangyoHours" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="sinyaDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="sinyaHours" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="kyusyutuDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="kyusyutuHours" /></span></td>

    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="chikokuTimes" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="soutaiTimes" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="kekkinDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="keityoDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="yuukyuDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="daikyuDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="averageData" property="sonotaDays" /></span></td>
    </tr>

    <tr class="td_type3">
    <td align="left" valign="middle" nowrap><span class="text_bb1"><gsmsg:write key="tcd.tcd040.11" /></span></td>
    <td align="right" valign="middle">&nbsp;</td>
    <bean:define id="totalData" name="tcd040Form" property="totalData" />
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="kadoDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="kadoHours" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="zangyoDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="zangyoHours" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="sinyaDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="sinyaHours" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="kyusyutuDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="kyusyutuHours" /></span></td>

    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="chikokuTimes" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="soutaiTimes" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="kekkinDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="keityoDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="yuukyuDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="daikyuDays" /></span></td>
    <td align="right" valign="middle"><span class="text_bb1"><bean:write name="totalData" property="sonotaDays" /></span></td>
    </tr>

    </logic:notEmpty>
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