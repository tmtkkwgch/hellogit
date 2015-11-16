<%@ page import="java.util.Calendar" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String close = String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE); %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn100.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script type="text/javascript">
function initPictureCmn() {
      jQuery(".img_hoge").each(function(){
        if ($(".img_hoge").width() > 130) {
          $(".img_hoge").width(130);
        }
      });
}
</script>

<title>[Group Session] <gsmsg:write key="cmn.shain.info" /></title>
</head>

<body class="body_03" onload="initPictureCmn()">
<html:form action="/common/cmn100">
<input type="hidden" name="CMD" value="">

<html:hidden property="formUnload" />
<html:hidden property="cmn100usid" />
<html:hidden property="cmn100usiSyainNo" />

<html:hidden property="cmn100usiPictKf" />

<html:hidden property="cmn100usiSei" />
<html:hidden property="cmn100usiMei" />
<html:hidden property="cmn100usiSeiKn" />
<html:hidden property="cmn100usiMeiKn" />
<html:hidden property="cmn100usiSyozoku" />
<html:hidden property="cmn100usiYakusyoku" />

<html:hidden property="cmn100usiBdateKf" />
<logic:notEqual name="cmn100Form" property="cmn100usiBdateKf" value="<%= close %>">
  <html:hidden property="cmn100usiBdate" />
</logic:notEqual>

<html:hidden property="cmn100usiMail1Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiMail1Kf" value="<%= close %>">
  <html:hidden property="cmn100usiMail1" />
</logic:notEqual>

<html:hidden property="cmn100usiMail2Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiMail2Kf" value="<%= close %>">
  <html:hidden property="cmn100usiMail2" />
</logic:notEqual>

<html:hidden property="cmn100usiMail3Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiMail3Kf" value="<%= close %>">
  <html:hidden property="cmn100usiMail3" />
</logic:notEqual>

<html:hidden property="cmn100usiZipKf" />
<logic:notEqual name="cmn100Form" property="cmn100usiZipKf" value="<%= close %>">
  <html:hidden property="cmn100usiZip" />
</logic:notEqual>

<html:hidden property="cmn100usiTdfkKf" />
<logic:notEqual name="cmn100Form" property="cmn100usiTdfkKf" value="<%= close %>">
  <html:hidden property="cmn100TdfkName" />
</logic:notEqual>

<html:hidden property="cmn100usiAddr1Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiAddr1Kf" value="<%= close %>">
  <html:hidden property="cmn100usiAddr1" />
</logic:notEqual>

<html:hidden property="cmn100usiAddr2Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiAddr2Kf" value="<%= close %>">
  <html:hidden property="cmn100usiAddr2" />
</logic:notEqual>

<html:hidden property="cmn100usiTel1Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiTel1Kf" value="<%= close %>">
  <html:hidden property="cmn100usiTel1" />
</logic:notEqual>

<html:hidden property="cmn100usiTel2Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiTel2Kf" value="<%= close %>">
  <html:hidden property="cmn100usiTel2" />
</logic:notEqual>

<html:hidden property="cmn100usiTel3Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiTel3Kf" value="<%= close %>">
  <html:hidden property="cmn100usiTel3" />
</logic:notEqual>

<html:hidden property="cmn100usiFax1Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiFax1Kf" value="<%= close %>">
  <html:hidden property="cmn100usiFax1" />
</logic:notEqual>

<html:hidden property="cmn100usiFax2Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiFax2Kf" value="<%= close %>">
  <html:hidden property="cmn100usiFax2" />
</logic:notEqual>

<html:hidden property="cmn100usiFax3Kf" />
<logic:notEqual name="cmn100Form" property="cmn100usiFax3Kf" value="<%= close %>">
  <html:hidden property="cmn100usiFax3" />
</logic:notEqual>

<html:hidden property="cmn100usiBiko" />
<html:hidden property="cmn100binSid" />

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="45%">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../user/images/header_user_01.gif" border="0" alt="<gsmsg:write key="cmn.shain.info" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.shain.info" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!--削除ユーザ-->
    <logic:notEqual name="cmn100Form" property="cmn100usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_JTKBN_ACTIVE) %>">
    <table width="500" class="tl0" border="0" cellpadding="0">
    <span class="text_kakunin"><gsmsg:write key="cmn.cmn100.1" /></span>
    </table>
    </logic:notEqual>

    <logic:equal name="cmn100Form" property="cmn100usrJkbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_JTKBN_ACTIVE) %>">
    <table width="500" class="tl0" border="0" cellpadding="0">
    <tr>
    <td width="20%"><img src="../common/images/spacer.gif" width="20px" height="1px" border="0"></td>
    <td width="20%"><img src="../common/images/spacer.gif" width="20px" height="1px" border="0"></td>
    <td width="40%" colspan="2"><img src="../common/images/spacer.gif" width="20px" height="1px" border="0"></td>
    <td width="20%" colspan="2"><img src="../common/images/spacer.gif" width="20px" height="1px" border="0"></td>
    </tr>
    <!--付加情報-->
    <logic:iterate id="app" name="cmn100Form" property="appendInfoList" scope="request" indexId="idx">
    <logic:iterate id="msg" name="app" property="message" indexId="idy">

    <logic:equal name="idy" value="0">
    <tr>
    <td class="table_bg_A5B4E1" rowspan="<bean:write name="app" property="titleRow" />" colspan="2">
    <span class="text_bb1"><bean:write name="app" property="title" /></span>
    <logic:equal name="app" property="linkType" value="1">
    <input type="button" class="btn_gekkan" value="&nbsp;" name="deluserBtn" onClick="moveMonthSchedule('month', <bean:write name="cmn100Form" property="cmn100usid" />, 0);">
    </logic:equal>
    </td>

    <logic:equal name="app" property="filter" value="1">
    <bean:write name="msg" filter="false"/>
    </logic:equal>

    <logic:notEqual name="app" property="filter" value="1">
    <td colspan="4" align="left" class="td_type1">
    <bean:write name="msg" filter="true"/>
    </td>
    </logic:notEqual>
    </tr>
    </logic:equal>

    <logic:notEqual name="idy" value="0">
    <tr>
    <logic:equal name="app" property="filter" value="1">
    <bean:write name="msg" filter="false"/>
    </logic:equal>
    <logic:notEqual name="app" property="filter" value="1">
    <td colspan="4" align="left" class="td_type1">
    <bean:write name="msg" filter="true"/>
    </td>
    </logic:notEqual>
    </tr>
    </logic:notEqual>
    </logic:iterate>
    <tr>
    <td width="20%"><img src="../common/images/spacer.gif" width="20px" height="5px" border="0"></td>
    <td width="20%"><img src="../common/images/spacer.gif" width="20px" height="5px" border="0"></td>
    <td width="40%" colspan="2"><img src="../common/images/spacer.gif" width="20px" height="5px" border="0"></td>
    <td width="20%" colspan="2"><img src="../common/images/spacer.gif" width="20px" height="5px" border="0"></td>
    </tr>
    </logic:iterate>

    <td class="table_bg_A5B4E1" nowrap colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.employee.staff.number" /></span></td>
    <td colspan="2" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiSyainNo" /></td>
    <td rowspan="4" colspan="2" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiPictKf" value="<%= close %>"><div align="center" class="photo_hikokai"><span style="color:#fc2929;"><gsmsg:write key="cmn.private" /></span></div></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiPictKf" value="<%= close %>">
        <logic:lessThan name="cmn100Form" property="cmn100binSid" value="1">
          <img src="../user/images/photo.gif" name="pitctImage" width="130" height="150" alt="<gsmsg:write key="cmn.photo" />" border="1">
        </logic:lessThan>
        <logic:greaterThan name="cmn100Form" property="cmn100binSid" value="0">
          <logic:equal name="cmn100Form" property="formUnload" value="false">
          <img src="../common/cmn100.do?CMD=getImageFile&cmn100binSid=<bean:write name='cmn100Form' property='cmn100binSid' />" name="pictImage" alt="<gsmsg:write key="cmn.photo" />" border="1" class="img_hoge">
          </logic:equal>
        </logic:greaterThan>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.name" /></td>
    <td colspan="2" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiSei" />&nbsp;&nbsp;<bean:write name="cmn100Form" property="cmn100usiMei" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="user.119" /></td>
    <td colspan="2" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiSeiKn" />&nbsp;&nbsp;<bean:write name="cmn100Form" property="cmn100usiMeiKn" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.affiliation" /></td>
    <td colspan="2" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiSyozoku" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.post" /></td>
    <td colspan="4" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiYakusyoku" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="user.123" /></td>
    <td colspan="4" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiSeibetu" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="user.156" /></td>
    <td colspan="4" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100entranceDate" /></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.birthday" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiBdateKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiBdateKf" value="<%= close %>"><bean:write name="cmn100Form" property="cmn100usiBdate" />
      <logic:notEqual name="cmn100Form" property="cmn100usiAge" value="-1">
      (<bean:write name="cmn100Form" property="cmn100usiAge" /><gsmsg:write key="user.98" />)
      </logic:notEqual>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.mailaddress1" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiMail1Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiMail1Kf" value="<%= close %>">
      <logic:notEmpty name="cmn100Form" property="cmn100usiMail1"><a href="mailto:<bean:write name="cmn100Form" property="cmn100usiMail1" />"><bean:write name="cmn100Form" property="cmn100usiMail1" /></a></logic:notEmpty>
      <logic:notEmpty name="cmn100Form" property="cmn100usiMailCmt1"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiMailCmt1" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.mailaddress2" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiMail2Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiMail2Kf" value="<%= close %>">
        <logic:notEmpty name="cmn100Form" property="cmn100usiMail2"><a href="mailto:<bean:write name="cmn100Form" property="cmn100usiMail2" />"><bean:write name="cmn100Form" property="cmn100usiMail2" /></a></logic:notEmpty>
        <logic:notEmpty name="cmn100Form" property="cmn100usiMailCmt2"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiMailCmt2" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.mailaddress3" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiMail3Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiMail3Kf" value="<%= close %>">
        <logic:notEmpty name="cmn100Form" property="cmn100usiMail3"><a href="mailto:<bean:write name="cmn100Form" property="cmn100usiMail3" />"><bean:write name="cmn100Form" property="cmn100usiMail3" /></a></logic:notEmpty>
        <logic:notEmpty name="cmn100Form" property="cmn100usiMailCmt3"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiMailCmt3" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>

    <% String addressRow = "4"; %>

    <tr>
    <td rowspan="<%= addressRow %>" class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.address" /></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.postalcode" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiZipKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiZipKf" value="<%= close %>"><bean:write name="cmn100Form" property="cmn100usiZip" /></logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.prefectures" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiTdfkKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiTdfkKf" value="<%= close %>"><bean:write name="cmn100Form" property="cmn100TdfkName" /></logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.address1" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiAddr1Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiAddr1Kf" value="<%= close %>"><bean:write name="cmn100Form" property="cmn100usiAddr1" /></logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.address2" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiAddr2Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiAddr2Kf" value="<%= close %>"><bean:write name="cmn100Form" property="cmn100usiAddr2" /></logic:notEqual>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.tel1" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiTel1Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiTel1Kf" value="<%= close %>">
        <bean:write name="cmn100Form" property="cmn100usiTel1" /><br>
        <logic:notEmpty name="cmn100Form" property="cmn100usiTelNai1"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiTelNai1" />&nbsp;&nbsp;</logic:notEmpty>
        <logic:notEmpty name="cmn100Form" property="cmn100usiTelCmt1"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiTelCmt1" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.tel2" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiTel2Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiTel2Kf" value="<%= close %>">
        <bean:write name="cmn100Form" property="cmn100usiTel2" /><br>
        <logic:notEmpty name="cmn100Form" property="cmn100usiTelNai2"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiTelNai2" />&nbsp;&nbsp;</logic:notEmpty>
        <logic:notEmpty name="cmn100Form" property="cmn100usiTelCmt2"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiTelCmt2" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.tel3" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiTel3Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiTel3Kf" value="<%= close %>">
        <bean:write name="cmn100Form" property="cmn100usiTel3" /><br>
        <logic:notEmpty name="cmn100Form" property="cmn100usiTelNai3"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiTelNai3" />&nbsp;&nbsp;</logic:notEmpty>
        <logic:notEmpty name="cmn100Form" property="cmn100usiTelCmt3"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiTelCmt3" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="user.143" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiFax1Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiFax1Kf" value="<%= close %>">
        <bean:write name="cmn100Form" property="cmn100usiFax1" />
        <logic:notEmpty name="cmn100Form" property="cmn100usiFaxCmt1"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiFaxCmt1" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.cmn100.2" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiFax2Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiFax2Kf" value="<%= close %>">
        <bean:write name="cmn100Form" property="cmn100usiFax2" />
        <logic:notEmpty name="cmn100Form" property="cmn100usiFaxCmt2"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiFaxCmt2" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.cmn100.3" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100usiFax3Kf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
      <logic:notEqual name="cmn100Form" property="cmn100usiFax3Kf" value="<%= close %>">
        <bean:write name="cmn100Form" property="cmn100usiFax3" />
        <logic:notEmpty name="cmn100Form" property="cmn100usiFaxCmt3"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="cmn100Form" property="cmn100usiFaxCmt3" /></logic:notEmpty>
      </logic:notEqual>
    </td>
    </tr>

    <logic:equal name="cmn100Form" property="cmn100UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.mobile.use" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:equal name="cmn100Form" property="cmn100UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_OK) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
      <logic:equal name="cmn100Form" property="cmn100UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_NG) %>"><gsmsg:write key="cmn.not" /></logic:equal>
    </td>
    </tr>
    </logic:equal>


    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.memo" /></td>
    <td colspan="4" align="left" class="td_type1"><bean:write name="cmn100Form" property="cmn100usiBiko" filter="false" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" colspan="2"><span class="text_bb1"><gsmsg:write key="cmn.label" /></td>
    <td colspan="4" align="left" class="td_type1">
      <logic:notEmpty name="cmn100Form" property="labelList">
      <% String[] labelClass = {"td_label1", "td_label2"}; %>
      <table cellpadding="0" cellspacing="0" class="tl0 spacer" width="100%">

      <logic:iterate id="labelData" name="cmn100Form" property="labelList" indexId="idx">
      <tr class="<%= labelClass[idx.intValue() % 2] %>">
      <td><bean:write name="labelData" property="labName" /></td>
      </tr>
      </logic:iterate>

      </table>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td align="left" class="td_type5" colspan="6">
      <div id="title"><span id="lt" class="text_tl2"><gsmsg:write key="cmn.affiliation.group" /></span></div>
    </td>
    </tr>

    <bean:define id="mod" value="0" />

    <logic:iterate id="group" name="cmn100Form" property="cmn100grpNmlist" indexId="idx" scope="request">
      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type1" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="tblColor" value="td_type_usr" />
      </logic:notEqual>
      <tr>
      <td colspan="4" align="left" class="<bean:write name="tblColor" />"><bean:write name="group" property="groupName" /></td>
      <td colspan="2" align="center" class="<bean:write name="tblColor" />">
        <logic:equal name="group" property="grpKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>"><gsmsg:write key="cmn.admin" /></logic:equal>
        <logic:notEqual name="group" property="grpKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">&nbsp;</logic:notEqual>
      </td>
      </tr>
    </logic:iterate>

    </logic:equal>
    </td>
    </tr>

    <tr>
    <td align="left" class="td_type5" colspan="6">
        <div id="title"><span id="lt" class="text_tl2"><gsmsg:write key="cmn.cmn100.4" /></span></div>
    </td>
    </tr>

    <tr>
    <td colspan="6" align="center" class="td_type1">

      <table width="0%">
      <tr>
      <th align="left">docomo</th><td><img src="../common/cmn100.do?CMD=getQrDocomo&userSid=<bean:write name="cmn100Form" property="cmn100usid" />"></td>
      </tr>
      <tr>
      <th align="left">au</th><td><img src="../common/cmn100.do?CMD=getQrAu&userSid=<bean:write name="cmn100Form" property="cmn100usid" />"></td>
      </tr>
      <tr>
      <th align="left">SoftBank</th><td><img src="../common/cmn100.do?CMD=getQrSoftBank&userSid=<bean:write name="cmn100Form" property="cmn100usid" />"></td>
      </tr>
      </table>

      <div width="99%" align="left" style="padding-top: 10px;"class="text_base_mini">
      <gsmsg:write key="cmn.cmn100.5" />
      </div>
    </td>
    </tr>
    </table>

  <tr>
  <td align="right" class="td_btn">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  </tr>

  </table>

</td>
</tr>
</table>

</html:form>
</body>
</html:html>