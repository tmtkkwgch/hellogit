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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.category.setting" /> </title>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../user/js/usr043.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/user/usr043">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="usr043ProcMode" value="">
<input type="hidden" name="usr043EditSid" value="">

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

<logic:notEmpty name="usr043Form" property="usr040labSid">
  <logic:iterate id="labSidArray" name="usr043Form" property="usr040labSid" indexId="idx">
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

<logic:notEmpty name="usr043Form" property="usr040labSidSave">
  <logic:iterate id="labSidArraySave" name="usr043Form" property="usr040labSidSave" indexId="idx">
    <input type="hidden" name="usr040labSidSave" value="<bean:write name="labSidArraySave" />">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040SearchFlg" />
<html:hidden property="usr040DspFlg" />

<html:hidden property="usr040CategorySetInitFlg" />
<logic:notEmpty name="usr043Form" property="usr040CategoryOpenFlg">
<logic:iterate id="openFlg" name="usr043Form" property="usr040CategoryOpenFlg">
  <bean:define id="flg" name="openFlg" type="java.lang.String" />
  <html:hidden property="usr040CategoryOpenFlg" value="<%= flg %>" />
</logic:iterate>
</logic:notEmpty>

<html:hidden property="usr040GrpSearchGId"/>
<html:hidden property="usr040GrpSearchGName"/>
<html:hidden property="usr040GrpSearchGIdSave"/>
<html:hidden property="usr040GrpSearchGNameSave"/>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<logic:notEmpty name="usr043Form" property="catList" scope="request">
  <logic:iterate id="sort" name="usr043Form" property="catList" scope="request">
    <input type="hidden" name="usr043KeyList" value="<bean:write name="sort" property="ulcValue" />">
  </logic:iterate>
</logic:notEmpty>


<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../user/images/header_user_01.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shain.info" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="cmn.category.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonSubmit('addCategory', 'add' , '-1');">&nbsp;
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('usr041back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%" style="margin-top: 10px;">
    <tr>
    <td>
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('usr043up');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('usr043down');">&nbsp;&nbsp;
    </td>
    </tr>
    </table>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
      <th align="center" class="table_bg_7D91BD" width="5%"><span class="text_tlw">&nbsp;</span></th>
      <th align="center" class="table_bg_7D91BD" width="28%"><span class="text_tlw"><gsmsg:write key="cmn.category.name" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="51%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="16%" colspan="2"><span class="text_tlw"><gsmsg:write key="cmn.edit" /></span></th>
    </tr>

    <logic:notEmpty name="usr043Form" property="catList">
    <bean:define id="tdColor" value=""/>
    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>
    <logic:iterate id="catMdl" name="usr043Form" property="catList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <bean:define id="ulcValue" name="catMdl" property="ulcValue" />

    <tr align="center" class="<%= tdColor %>">
    <!-- ラジオ -->
    <td align="center" nowrap>
    <html:radio property="usr043SortRadio" value="<%= String.valueOf(ulcValue) %>" />
    </td>
    <!-- カテゴリ名 -->
    <td align="left" nowrap><bean:write name="catMdl" property="lucName" /></td>
    <!-- 備考 -->
    <td align="left">
    <bean:write name="catMdl" property="lucBiko" filter="false" />
    </td>
    <td align="center">
    <logic:notEqual name="catMdl" property="lucSid" value="0">
      <input type="button" value="<gsmsg:write key="cmn.category" />" class="btn_base0" onclick="return buttonSubmit('categoryEdit', 'edit', '<bean:write name="catMdl" property="lucSid" />')">
    </logic:notEqual>
    </td>
    <td align="center">
    <input type="button" value="<gsmsg:write key="cmn.label" />" class="btn_base0" onclick="return buttonSubmit('usr043edit','', '<bean:write name="catMdl" property="lucSid" />');"></td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>


    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellpadding="0" border="0" width="100%">
    <tr>
    <td align="right" valign="middle">
      <input type="button" value="<gsmsg:write key="user.59" />" class="btn_add_n1" onClick="buttonSubmit('addCategory', 'add' , '-1');">&nbsp;
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