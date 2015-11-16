<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.shain.info" /> <gsmsg:write key="cmn.default.display.order.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr082.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="lmtEnableDisable();">
<html:form action="/user/usr082">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="usr040cmdMode" />
<html:hidden property="usr040orderKey" />
<html:hidden property="usr040sortKey" />
<html:hidden property="usr040orderKey2" />
<html:hidden property="usr040sortKey2" />
<html:hidden property="usr040pageNum1" />
<html:hidden property="usr040pageNum2" />

<html:hidden property="usr040SearchKana" />
<html:hidden property="selectgsid" />

<html:hidden property="usr040Keyword" />
<html:hidden property="usr040KeyKbnShainno" />
<html:hidden property="usr040KeyKbnName" />
<html:hidden property="usr040KeyKbnNameKn" />
<html:hidden property="usr040KeyKbnMail" />
<html:hidden property="usr040agefrom" />
<html:hidden property="usr040ageto" />
<html:hidden property="usr040yakushoku" />
<html:hidden property="usr040tdfkCd" />
<html:hidden property="usr040entranceYearFr" />
<html:hidden property="usr040entranceMonthFr" />
<html:hidden property="usr040entranceDayFr" />
<html:hidden property="usr040entranceYearTo" />
<html:hidden property="usr040entranceMonthTo" />
<html:hidden property="usr040entranceDayTo" />
<html:hidden property="usr040seibetu" />

<logic:notEmpty name="usr082Form" property="usr040labSid">
  <logic:iterate id="labSidArray" name="usr082Form" property="usr040labSid" indexId="idx">
    <input type="hidden" name="usr040labSid" value="<bean:write name="labSidArray" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="selectgsidSave" />
<html:hidden property="usr040SearchKanaSave" />

<html:hidden property="usr040KeywordSave" />
<html:hidden property="usr040KeyKbnShainnoSave" />
<html:hidden property="usr040KeyKbnNameSave" />
<html:hidden property="usr040KeyKbnNameKnSave" />
<html:hidden property="usr040KeyKbnMailSave" />
<html:hidden property="usr040agefromSave" />
<html:hidden property="usr040agetoSave" />
<html:hidden property="usr040yakushokuSave" />
<html:hidden property="usr040tdfkCdSave" />
<html:hidden property="usr040entranceYearFrSave" />
<html:hidden property="usr040entranceMonthFrSave" />
<html:hidden property="usr040entranceDayFrSave" />
<html:hidden property="usr040entranceYearToSave" />
<html:hidden property="usr040entranceMonthToSave" />
<html:hidden property="usr040entranceDayToSave" />
<html:hidden property="usr040seibetuSave" />

<logic:notEmpty name="usr082Form" property="usr040labSidSave">
  <logic:iterate id="labSidArraySave" name="usr082Form" property="usr040labSidSave" indexId="idx">
    <input type="hidden" name="usr040labSidSave" value="<bean:write name="labSidArraySave" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040SearchFlg" />
<html:hidden property="usr040DspFlg" />

<html:hidden property="usr082initKbn" />

<html:hidden property="usr040CategorySetInitFlg" />

<logic:notEmpty name="usr082Form" property="usr040CategoryOpenFlg">
<logic:iterate id="openFlg" name="usr082Form" property="usr040CategoryOpenFlg">
  <bean:define id="flg" name="openFlg" type="java.lang.String" />
  <html:hidden property="usr040CategoryOpenFlg" value="<%= flg %>" />
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040GrpSearchGId"/>
<html:hidden property="usr040GrpSearchGName"/>
<html:hidden property="usr040GrpSearchGIdSave"/>
<html:hidden property="usr040GrpSearchGNameSave"/>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.shain.info" /> <gsmsg:write key="cmn.default.display.order.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('usr082kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('usr082back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.usr082.2" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt" >

      <html:radio name="usr082Form" styleId="usr082DefoDspKbn_01" property="usr082DefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.DEFO_DSP_ADM) %>" onclick="lmtEnableDisable();" /><label for="usr082DefoDspKbn_01"><span class="text_base_user1"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="usr082Form" styleId="usr082DefoDspKbn_02" property="usr082DefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.DEFO_DSP_USR) %>" onclick="lmtEnableDisable();" /><label for="usr082DefoDspKbn_02"><span class="text_base_user1"><gsmsg:write key="cmn.set.eachuser" /></span></label>
      <br>
      <span id="lmtinput">※<gsmsg:write key="cmn.view.user.defaultset" /><br></span>
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" />：</span>

      <!-- キー1 -->
      <html:select property="usr082AdSortKey1">
        <html:optionsCollection name="usr082Form" property="usr082SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="usr082Form" property="usr082AdSortOrder1" styleId="usr082AdSortOrder10" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="usr082AdSortOrder10"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="usr082Form" property="usr082AdSortOrder1" styleId="usr082AdSortOrder11" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="usr082AdSortOrder11"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" />：</span>
      <!-- キー2 -->
      <html:select property="usr082AdSortKey2">
        <html:optionsCollection name="usr082Form" property="usr082SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="usr082Form" property="usr082AdSortOrder2" styleId="usr082AdSortOrder20" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="usr082AdSortOrder20"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="usr082Form" property="usr082AdSortOrder2" styleId="usr082AdSortOrder21" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="usr082AdSortOrder21"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('usr082kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('usr082back');">
    </td>
    </tr>
    </table>


  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>