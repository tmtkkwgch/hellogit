<%@ page import="java.util.Calendar" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="main.man002.24" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/user/usr031kn">

<input type="hidden" name="CMD" value="">
<html:hidden property="processMode" />
<html:hidden property="usr030cmdMode" />
<html:hidden property="usr030SearchFlg" />

<html:hidden property="selectgsid" />
<html:hidden property="usr030userId" />
<html:hidden property="usr030shainno" />
<html:hidden property="usr030sei" />
<html:hidden property="usr030mei" />
<html:hidden property="usr030seikn" />
<html:hidden property="usr030meikn" />
<html:hidden property="usr030agefrom" />
<html:hidden property="usr030ageto" />
<html:hidden property="usr030yakushoku" />
<html:hidden property="usr030mail" />
<html:hidden property="usr030tdfkCd" />
<html:hidden property="usr030seibetu" />
<html:hidden property="usr030entranceYearFr" />
<html:hidden property="usr030entranceMonthFr" />
<html:hidden property="usr030entranceDayFr" />
<html:hidden property="usr030entranceYearTo" />
<html:hidden property="usr030entranceMonthTo" />
<html:hidden property="usr030entranceDayTo" />
<html:hidden property="selectgsidSave" />
<html:hidden property="usr030userIdSave" />
<html:hidden property="usr030shainnoSave" />
<html:hidden property="usr030seiSave" />
<html:hidden property="usr030meiSave" />
<html:hidden property="usr030seiknSave" />
<html:hidden property="usr030meiknSave" />
<html:hidden property="usr030agefromSave" />
<html:hidden property="usr030agetoSave" />
<html:hidden property="usr030yakushokuSave" />
<html:hidden property="usr030mailSave" />
<html:hidden property="usr030tdfkCdSave" />
<html:hidden property="usr030seibetuSave" />
<html:hidden property="usr030entranceYearFrSave" />
<html:hidden property="usr030entranceMonthFrSave" />
<html:hidden property="usr030entranceDayFrSave" />
<html:hidden property="usr030entranceYearToSave" />
<html:hidden property="usr030entranceMonthToSave" />
<html:hidden property="usr030entranceDayToSave" />

<html:hidden property="usr031userid" />
<html:hidden property="usr031password" />
<html:hidden property="usr031passwordkn" />
<html:hidden property="usr031sei" />
<html:hidden property="usr031mei" />
<html:hidden property="usr031seikn" />
<html:hidden property="usr031meikn" />
<html:hidden property="usr031shainno" />
<html:hidden property="usr031yakushoku" />
<html:hidden property="usr031seibetu" />
<html:hidden property="usr031sortkey1" />
<html:hidden property="usr031sortkey2" />
<html:hidden property="usr031bikou" />
<html:hidden property="usr031bikouHtml" />
<html:hidden property="usr031defgroup" />
<html:hidden property="usr031birthYear" />
<html:hidden property="usr031birthMonth" />
<html:hidden property="usr031birthDay" />
<html:hidden property="usr031entranceYear" />
<html:hidden property="usr031entranceMonth" />
<html:hidden property="usr031entranceDay" />
<html:hidden property="usr031mail1" />
<html:hidden property="usr031mailCmt1" />
<html:hidden property="usr031mail2" />
<html:hidden property="usr031mailCmt2" />
<html:hidden property="usr031mail3" />
<html:hidden property="usr031mailCmt3" />
<html:hidden property="usr031tel1" />
<html:hidden property="usr031telNai1" />
<html:hidden property="usr031telCmt1" />
<html:hidden property="usr031tel2" />
<html:hidden property="usr031telNai2" />
<html:hidden property="usr031telCmt2" />
<html:hidden property="usr031tel3" />
<html:hidden property="usr031telNai3" />
<html:hidden property="usr031telCmt3" />
<html:hidden property="usr031fax1" />
<html:hidden property="usr031faxCmt1" />
<html:hidden property="usr031fax2" />
<html:hidden property="usr031faxCmt2" />
<html:hidden property="usr031fax3" />
<html:hidden property="usr031faxCmt3" />
<html:hidden property="usr031post1" />
<html:hidden property="usr031post2" />
<html:hidden property="usr031tdfkCd" />
<html:hidden property="usr031address1" />
<html:hidden property="usr031address2" />
<html:hidden property="usr031syozoku" />
<html:hidden property="usr031BinSid" />
<html:hidden property="usr031ImageName" />
<html:hidden property="usr031ImageSaveName" />
<html:hidden property="usr031UsiPicgKf" />
<html:hidden property="usr031UsiBdateKf" />
<html:hidden property="usr031UsiMail1Kf" />
<html:hidden property="usr031UsiMail2Kf" />
<html:hidden property="usr031UsiMail3Kf" />
<html:hidden property="usr031UsiZipKf" />
<html:hidden property="usr031UsiTdfKf" />
<html:hidden property="usr031UsiAddr1Kf" />
<html:hidden property="usr031UsiAddr2Kf" />
<html:hidden property="usr031UsiTel1Kf" />
<html:hidden property="usr031UsiTel2Kf" />
<html:hidden property="usr031UsiTel3Kf" />
<html:hidden property="usr031UsiFax1Kf" />
<html:hidden property="usr031UsiFax2Kf" />
<html:hidden property="usr031UsiFax3Kf" />
<html:hidden property="usr030SearchKana" />
<html:hidden property="usr030selectuser" />
<html:hidden property="selectgroup" />
<html:hidden property="adminFlg" />
<html:hidden property="usr031UsiMblUseKbn" />

<html:hidden property="usr031Digit" />
<html:hidden property="usr031CoeKbn" />
<html:hidden property="usr031PswdKbn" />
<html:hidden property="usr031UidPswdKbn" />

<logic:equal name="usr031knForm" property="processMode" value="edit">
<input type="hidden" name="helpPrm" value="add">
</logic:equal>
<logic:notEqual name="usr031knForm" property="processMode" value="edit">
<input type="hidden" name="helpPrm" value="<bean:write name="usr031knForm" property="processMode" />">
</logic:notEqual>

<html:hidden property="usr031NumCont" />
<html:hidden property="usr031NumAutAdd" />
<html:hidden property="usr031CmuUid1" />
<html:hidden property="usr031CmuUid2" />
<html:hidden property="usr031CmuUid3" />
<html:hidden property="usr031delPluralKbn" />

<logic:notEmpty name="usr031knForm" property="usr030selectusers" scope="request">
<logic:iterate id="users" name="usr031knForm" property="usr030selectusers" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="usr030selectusers" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr031knForm" property="usrLabel">
<logic:iterate id="label" name="usr031knForm" property="usrLabel">
  <input type="hidden" name="usrLabel" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

<table width="70%" cellpadding="0" cellspacing="0">
<tr>
<td align="left">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%">
  <logic:notEqual name="usr031knForm" property="processMode" value="kojn_edit">
    <img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
  </logic:notEqual>
  <logic:equal name="usr031knForm" property="processMode" value="kojn_edit">
    <img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
  </logic:equal>

  <td width="100%" class="header_ktool_bg_text2">
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="add">[ <gsmsg:write key="user.usr031kn.1" /> ]</logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="edit">[ <gsmsg:write key="user.usr031kn.2" /> ]</logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="kojn_edit">[ <gsmsg:write key="user.usr031kn.3" /> ]</logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="del">[ <gsmsg:write key="user.usr031kn.4" /> ]</logic:equal>
  </td>
  <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
  </tr>
  </table>


  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="50%"></td>
  <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
  <td class="header_glay_bg" width="50%">
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="add">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('add');">
    </logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="edit">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('edit');">
    </logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="kojn_edit">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('edit');">
    </logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="del">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush('usr031kn_del');">
    </logic:equal>
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('Usr031kn_Back');">
  </td>
  <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
  </tr>
  </table>


  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">


  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>
    <span class="text_r1">
    <logic:equal name="usr031knForm" property="processMode" scope="request" value="add"><gsmsg:write key="user.usr011kn.4" /></logic:equal>
    <logic:equal name="usr031knForm" property="processMode" scope="request" value="edit"><gsmsg:write key="user.usr031kn.6" /></logic:equal>
    <logic:equal name="usr031knForm" property="processMode" scope="request" value="kojn_edit"><gsmsg:write key="user.usr031kn.6" /></logic:equal>
    <logic:equal name="usr031knForm" property="processMode" scope="request" value="del"><gsmsg:write key="user.usr031kn.7" /></logic:equal>
    </span>
  </td>
  </tr>
  </table>


  <logic:equal name="usr031knForm" property="usr031delPluralKbn" value="0">
  <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">


  <logic:equal name="usr031knForm" property="adminFlg" value="true">

  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.user.id" /></span>
  </td>

  <!-- ユーザID -->
  <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031userid" /></span>
  </td>
  </tr>

  <logic:equal name="usr031knForm" property="changePassword" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.CHANGEPASSWORD_PARMIT) %>">
  <!--  パスワード -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="user.117" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="passworddamy" /></span>
  </td>
  </tr>
  </logic:equal>

  </logic:equal>

  <!-- 職員番号 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.employee.staff.number" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031shainno" /></span>
  </td>
  </tr>

  <!-- 写真 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.photo" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <table width="100%" border="0">
    <tr>
    <td width="0%">
      <logic:equal name="usr031knForm" property="usr031ImageName" value="">
        <img src="../user/images/photo.gif" name="pitctImage" width="130" height="150" alt="<gsmsg:write key="cmn.photo" />" border="1">
      </logic:equal>
      <logic:notEqual name="usr031knForm" property="usr031ImageName" value="">
        <img src="../user/usr031kn.do?CMD=getImageFile&" name="pitctImage" alt="<gsmsg:write key="cmn.photo" />" border="1" onload="initImageView130('pitctImage');">
      </logic:notEqual>
    </td>
    <td width="100%" nowrap>
      <logic:equal name="usr031knForm" property="usr031UsiPicgKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
      <logic:equal name="usr031knForm" property="usr031UsiPicgKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
      </span>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <!--  氏名 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.name" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031sei" />&nbsp;&nbsp;<bean:write name="usr031knForm" property="usr031mei" /></span>
  </td>
  </tr>

  <!--  氏名かな -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="user.119" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031seikn" />&nbsp;&nbsp;<bean:write name="usr031knForm" property="usr031meikn" /></span>
  </td>
  </tr>

  <!-- 所属 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.affiliation" /></font> </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031syozoku" /></span>
  </td>
  </tr>

  <!-- 役職 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.post" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031knposName" /></span>
  </td>
  </tr>

  <!-- 性別 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="user.123" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031seibetuName" /></span>
  </td>
  </tr>

  <!-- 入社年月日 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="user.122" /></font></td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base">
      <logic:notEmpty name="usr031knForm" property="usr031entranceYear" scope="request">
      <bean:define id="yr" name="usr031knForm" property="usr031entranceYear" type="java.lang.String" />
      <gsmsg:write key="cmn.year" arg0="<%= yr %>" />&nbsp;
      <bean:write name="usr031knForm" property="usr031entranceMonth" /><gsmsg:write key="cmn.month" />&nbsp;
      <bean:write name="usr031knForm" property="usr031entranceDay" /><gsmsg:write key="cmn.day" />
      </logic:notEmpty>
    </span>
  </td>
  </tr>

  <!-- 生年月日 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="user.120" /></font></td>
  <td valign="middle" align="left" class="td_wt6">
    <span class="text_base">
      <logic:notEmpty name="usr031knForm" property="usr031birthYear" scope="request">
      <bean:define id="yr" name="usr031knForm" property="usr031birthYear" type="java.lang.String" />
      <gsmsg:write key="cmn.year" arg0="<%= yr %>" />&nbsp;
      <bean:write name="usr031knForm" property="usr031birthMonth" /><gsmsg:write key="cmn.month" />&nbsp;
      <bean:write name="usr031knForm" property="usr031birthDay" /><gsmsg:write key="cmn.day" />
      </logic:notEmpty>
    </span>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiBdateKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiBdateKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- メールアドレス -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mailaddress1" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031knMail1" filter="false" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031mailCmt1">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031mailCmt1" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiMail1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiMail1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mailaddress2" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031knMail2" filter="false" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031mailCmt2">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031mailCmt2" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiMail2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiMail2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mailaddress3" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031knMail3" filter="false" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031mailCmt3">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031mailCmt3" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiMail3Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiMail3Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- 郵便番号 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="4" nowrap><font class="text_bb1"><gsmsg:write key="cmn.address" /></font></td>
  <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.postalcode" /></font></td>
  <td valign="middle" align="left" class="td_wt6">
    <logic:notEmpty name="usr031knForm" property="usr031post1" scope="request">
      <span class="text_base"><bean:write name="usr031knForm" property="usr031post1" />-<bean:write name="usr031knForm" property="usr031post2" /></span>
    </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiZipKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiZipKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- 都道府県 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.prefectures" /></font></td>
  <td valign="middle" align="left" class="td_wt6">
    <span class="text_base">
      <logic:notEmpty name="usr031knForm" property="usr031kntdfkName" scope="request">
        <bean:write name="usr031knForm" property="usr031kntdfkName" />
      </logic:notEmpty>
    </span>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiTdfKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiTdfKf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- 住所1 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address" />１</font> </td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031address1" /></span>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiAddr1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiAddr1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- 住所2 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address" />２</font> </td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031address2" /></span></td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiAddr2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiAddr2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- 電話番号 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel1" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031tel1" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031telNai1">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="user.136" />：&nbsp;<bean:write name="usr031knForm" property="usr031telNai1" /></span>
  </logic:notEmpty>
  <logic:notEmpty name="usr031knForm" property="usr031telCmt1">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031telCmt1" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiTel1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiTel1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel2" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031tel2" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031telNai2">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="user.136" />：&nbsp;<bean:write name="usr031knForm" property="usr031telNai2" /></span>
  </logic:notEmpty>
  <logic:notEmpty name="usr031knForm" property="usr031telCmt2">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031telCmt2" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiTel2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiTel2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel3" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031tel3" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031telNai3">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="user.136" />：&nbsp;<bean:write name="usr031knForm" property="usr031telNai3" /></span>
  </logic:notEmpty>
  <logic:notEmpty name="usr031knForm" property="usr031telCmt3">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031telCmt3" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiTel3Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiTel3Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <!-- FAX -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="user.143" /></font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031fax1" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031faxCmt1">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031faxCmt1" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiFax1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiFax1Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ２</font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031fax2" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031faxCmt2">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031faxCmt2" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiFax2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiFax2Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ３</font></td>
  <td valign="middle" align="left" class="td_wt6"><span class="text_base"><bean:write name="usr031knForm" property="usr031fax3" /></span>
  <logic:notEmpty name="usr031knForm" property="usr031faxCmt3">
  &nbsp;&nbsp;<span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;<bean:write name="usr031knForm" property="usr031faxCmt3" /></span>
  </logic:notEmpty>
  </td>
  <td valign="middle" align="left" class="td_wt7" nowrap>
    <logic:equal name="usr031knForm" property="usr031UsiFax3Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN) %>"><span class="text_r1"><gsmsg:write key="cmn.publish" /></span></logic:equal>
    <logic:equal name="usr031knForm" property="usr031UsiFax3Kf" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE) %>"><span class="text_base"><gsmsg:write key="cmn.not.publish" /></span></logic:equal>
  </td>
  </tr>

  <logic:equal name="usr031knForm" property="adminFlg" value="true">
    <logic:equal name="usr031knForm" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">

    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mobile.use" /></font></td>
    <td valign="middle" align="left" class="td_wt7" colspan="2">
      <table width="100%" border="0">
      <tr>
      <td>
        <!-- モバイル使用可否 -->
        <span class="text_base">
          <logic:equal name="usr031knForm" property="usr031UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_OK) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
          <logic:equal name="usr031knForm" property="usr031UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_NG) %>"><gsmsg:write key="cmn.not" /></logic:equal>
        </span>
      </td>
      </tr>

      <logic:equal name="usr031knForm" property="usr031UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_OK) %>">
        <logic:equal name="usr031knForm" property="usr031NumCont" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_CONTROL) %>">
        <tr>
        <td>
          <span class="text_base"><gsmsg:write key="cmn.login.control.identification.number" /></span>
        </td>
        </tr>

          <logic:equal name="usr031knForm" property="usr031NumAutAdd" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_AUTO_REG_OK) %>">
          <tr>
          <td style="padding-left: 15px;">
            <span class="text_base"><gsmsg:write key="user.99" /></span>
          </td>
          </tr>
          </logic:equal>

        <tr>
        <td style="padding-left: 35px; padding-top: 5px;">
          <span class="text_base"><gsmsg:write key="user.105" />：&nbsp;<bean:write name="usr031knForm" property="usr031CmuUid1" /></span>
        </td>
        </tr>

        <tr>
        <td style="padding-left: 35px;">
          <span class="text_base"><gsmsg:write key="user.106" />：&nbsp;<bean:write name="usr031knForm" property="usr031CmuUid2" /></span>
        </td>
        </tr>

        <tr>
        <td style="padding-left: 35px;">
          <span class="text_base"><gsmsg:write key="user.107" />：&nbsp;<bean:write name="usr031knForm" property="usr031CmuUid3" /></span>
        </td>
        </tr>

        </logic:equal>

      </logic:equal>

      </table>
    </td>
    </tr>

    </logic:equal>
  </logic:equal>

    <tr>
    <!-- ラベル -->
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.label" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">

      <logic:notEmpty name="usr031knForm" property="selectLabelList">
      <% String[] labelClass = {"td_label1", "td_label2"}; %>
      <table cellpadding="0" cellspacing="0" class="tl0 spacer" width="40%">

      <logic:iterate id="labelData" name="usr031knForm" property="selectLabelList" indexId="idx">
      <tr class="<%= labelClass[idx.intValue() % 2] %>">
      <td><bean:write name="labelData" property="labName" /></td>
      </tr>
      </logic:iterate>

      </table>
      </logic:notEmpty>
      </td>
      </tr>


  <!-- ソートキー1,2-->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.sortkey" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <logic:notEqual name="usr031knForm" property="usr031sortkey1" value="">
    <span class="text_base">1：<bean:write name="usr031knForm" property="usr031sortkey1" /></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </logic:notEqual>
    <logic:notEqual name="usr031knForm" property="usr031sortkey2" value="">
    <span class="text_base">2：<bean:write name="usr031knForm" property="usr031sortkey2" /></span>
    </logic:notEqual>
  </td>
  </tr>


  <!-- 備考 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <span class="text_base"><bean:write name="usr031knForm" property="usr031bikouHtml" filter="false" /></span>
  </td>
  </tr>

  <logic:equal name="usr031knForm" property="adminFlg" value="true">
  <tr>
  <td valign="middle" align="left" class="td_type5" nowrap colspan="4">
    <span class="text_tl2"><gsmsg:write key="user.86" /></span><span class="text_r2"></span>
  </td>
  </tr>

  <!--  グループ設定 -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="cmn.affiliation.group" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <logic:notEmpty name="usr031knForm" property="usr031knSltgps" scope="request">
    <logic:iterate id="sgrp" name="usr031knForm" property="usr031knSltgps" scope="request">
      <span class="text_base"><bean:write name="sgrp" property="grpName" /></span><br>
    </logic:iterate>
    </logic:notEmpty>
  </td>
  </tr>

  <!-- デフォルトグループ -->
  <tr>
  <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
    <span class="text_bb1"><gsmsg:write key="user.35" /></span>
  </td>
  <td valign="middle" align="left" class="td_wt" colspan="2">
    <logic:notEmpty name="usr031knForm" property="usr031knDefgp">
      <bean:define id="defgrp" name="usr031knForm" property="usr031knDefgp" />
      <span class="text_base"><bean:write name="defgrp" property="grpName" /></span>
    </logic:notEmpty>
  </td>
  </tr>
  </logic:equal>
  </table>
  </logic:equal>


  <logic:equal name="usr031knForm" property="usr031delPluralKbn" value="1">
  <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

  <logic:notEmpty name="usr031knForm" property="usr031delUsrList" >

    <tr>
    <td valign="middle" align="center" class="table_bg_7D91BD" width="20%" nowrap>
      <span class="text_tlw"><gsmsg:write key="cmn.employee.staff.number" /></span>
    </td>
    <td valign="middle" align="center" class="table_bg_7D91BD" width="80%" nowrap>
      <span class="text_tlw"><gsmsg:write key="cmn.name" /></span>
    </td>
    </tr>

  <logic:iterate id="uinfMdl" name="usr031knForm" property="usr031delUsrList" >

    <tr>
    <!-- ユーザID -->
    <td valign="middle" align="left" class="td_wt" nowrap>
      <span class="text_base"><bean:write name="uinfMdl" property="usiSyainNo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" nowrap>
      <span class="text_base"><bean:write name="uinfMdl" property="usiSei" />&nbsp;<bean:write name="uinfMdl" property="usiMei" /></span>
    </td>
    </tr>
  </logic:iterate>
  </table>
  </logic:notEmpty>
  </logic:equal>



  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">


  <table width="100%">
  <tr>
  <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="add">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('add');">
    </logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="edit">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('edit');">
    </logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="kojn_edit">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('edit');">
    </logic:equal>
    <logic:equal name="usr031knForm" property="processMode"  scope="request" value="del">
      <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.delete" />" onClick="return buttonPush('usr031kn_del');">
    </logic:equal>
    <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('Usr031kn_Back');">
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