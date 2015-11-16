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
<title>[GroupSession] <gsmsg:write key="user.usr041.1" /> </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../user/js/usr041.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/user/usr041">
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

<logic:notEmpty name="usr041Form" property="usr040labSid">
  <logic:iterate id="labSidArray" name="usr041Form" property="usr040labSid" indexId="idx">
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

<logic:notEmpty name="usr041Form" property="usr040labSidSave">
  <logic:iterate id="labSidArraySave" name="usr041Form" property="usr040labSidSave" indexId="idx">
    <input type="hidden" name="usr040labSidSave" value="<bean:write name="labSidArraySave" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040SearchFlg" />
<html:hidden property="usr040DspFlg" />

<html:hidden property="usr040CategorySetInitFlg" />

<logic:notEmpty name="usr041Form" property="usr040CategoryOpenFlg">
<logic:iterate id="openFlg" name="usr041Form" property="usr040CategoryOpenFlg">
  <bean:define id="flg" name="openFlg" type="java.lang.String" />
  <html:hidden property="usr040CategoryOpenFlg" value="<%= flg %>" />
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040GrpSearchGId"/>
<html:hidden property="usr040GrpSearchGName"/>
<html:hidden property="usr040GrpSearchGIdSave"/>
<html:hidden property="usr040GrpSearchGNameSave"/>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">


    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.display.settings" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.setting2" />" class="btn_setting_n1" onClick="buttonPush('usr041commit');">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('usr041back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <logic:equal name="usr041Form" property="usr041DefoDspKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.DEFO_DSP_USR) %>">
    <tr>
    <td width="15%" valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.sortby.members" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <span class="text_r1">※<gsmsg:write key="cmn.plg.set.order.grpmember" /></span><br>
      <span class="text_bb1"><gsmsg:write key="cmn.first.key" />：</span>

      <!-- キー1 -->
      <html:select property="usr041SortKey1">
        <html:optionsCollection name="usr041Form" property="usr041SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="usr041Form" property="usr041SortOrder1" styleId="usr041SortOrder10" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="usr041SortOrder10"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="usr041Form" property="usr041SortOrder1" styleId="usr041SortOrder11" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="usr041SortOrder11"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>
      <br>

      <span class="text_bb1"><gsmsg:write key="cmn.second.key" />：</span>
      <!-- キー2 -->
      <html:select property="usr041SortKey2">
        <html:optionsCollection name="usr041Form" property="usr041SortKeyLabel" value="value" label="label" />
      </html:select>
      <html:radio name="usr041Form" property="usr041SortOrder2" styleId="usr041SortOrder20" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC) %>" /><span class="text_base"><label for="usr041SortOrder20"><gsmsg:write key="cmn.order.asc" /></label></span>&nbsp;
      <html:radio name="usr041Form" property="usr041SortOrder2" styleId="usr041SortOrder21" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC) %>" /><span class="text_base"><label for="usr041SortOrder21"><gsmsg:write key="cmn.order.desc" /></label>&nbsp;</span>

    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.number.display" /></span></td>
    <td align="left" class="td_wt">
      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
        <html:select name="usr041Form" property="usr041DspCnt">
          <html:optionsCollection name="usr041Form" property="usr041DspCntList" value="value" label="label" />
        </html:select>
      </td>
      <td align="right" width="0%" nowrap>
        <span class="text_r1"><gsmsg:write key="user.usr041.7" /></span>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="button" value="<gsmsg:write key="cmn.setting2" />" class="btn_setting_n1" onClick="buttonPush('usr041commit');">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('usr041back');">
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