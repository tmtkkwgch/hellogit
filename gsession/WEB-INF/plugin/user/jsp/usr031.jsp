<%@ page import="java.util.Calendar" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.usr.GSConstUser" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String infoOpen = String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_OPEN); %>
<% String infoClose = String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE); %>

<% String reservUser = String.valueOf(jp.groupsession.v2.usr.GSConstUser.USER_RESERV_SID); %>

<% String maxLengthBiko = String.valueOf(jp.groupsession.v2.usr.GSConstUser.MAX_LENGTH_USERCOMMENT);
   String labelSetOk = String.valueOf(jp.groupsession.v2.usr.GSConstUser.LABEL_SET_OK);
   %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="main.man002.24" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../common/js/yui/yahoo/yahoo.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/event/event.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/dom/dom.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/dragdrop/dragdrop.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../common/js/yui/container/container.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>

<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/group.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr031.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/uidHisPopUp.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<logic:equal name="usr031Form" property="labelSetPow" value="<%= labelSetOk %>">
<script language="JavaScript" src="../user/js/usrLabel.js?<%= GSConst.VERSION_PARAM %>"></script>
</logic:equal>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/container.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>



</head>

<logic:equal name="usr031Form" property="adminFlg" value="true">
  <logic:equal name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
    <body class="body_03" onload="defaultGroup();changeUidElementStatus();showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="windowClose();">
  </logic:equal>
  <logic:notEqual name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
    <body class="body_03" onload="defaultGroup();showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="windowClose();">
  </logic:notEqual>
</logic:equal>
<logic:notEqual name="usr031Form" property="adminFlg" value="true">
  <body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthBiko %>, 'inputlength');" onunload="windowClose();">
</logic:notEqual>

<logic:equal name="usr031Form" property="labelSetPow" value="<%= labelSetOk %>">
<div id="labelPanel">
<div class="hd"><gsmsg:write key="cmn.select.a.label" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="lab" style="margin:0; padding:0; width:100%; height: 400px" frameborder="no"></iframe></div>
</div>
</logic:equal>

<div id="subPanel">
<div class="hd">　<gsmsg:write key="cmn.plz.register.post" /></div>
<div class="bd"><iframe src="../common/html/damy.html" name="pos" style="margin:0; padding:0; width:100%; height: 100%" frameborder="no"></iframe></div>
</div>

<div id="FreezePane">



<html:form action="/user/usr031">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="winname" value="posbox">
<input type="hidden" name="childframe" value="grpFrame">

<html:hidden property="processMode" />
<html:hidden property="usr030SearchKana" />
<html:hidden property="usr030selectuser" />
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

<html:hidden property="selectgroup" />
<html:hidden property="usr031BinSid" />
<html:hidden property="usr031ImageName" />
<html:hidden property="usr031ImageSaveName" />
<html:hidden property="labelSetPow" />
<html:hidden property="adminFlg" />
<html:hidden property="usr031CoeKbn" />
<html:hidden property="usr031Digit" />
<html:hidden property="usr031UidPswdKbn" />

<span id="usr031labelArea">
<logic:notEmpty name="usr031Form" property="usrLabel">
<logic:iterate id="label" name="usr031Form" property="usrLabel">
  <input type="hidden" name="usrLabel" value="<bean:write name="label" />">
</logic:iterate>
</logic:notEmpty>
</span>

<logic:notEmpty name="usr031Form" property="groupList">
<logic:iterate id="grpData" name="usr031Form" property="groupList">
  <input type="hidden" name="defGrpId" value="<bean:write name="grpData" property="groupSid" />">
  <input type="hidden" name="defGrpNm" value="<bean:write name="grpData" property="groupName" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr031Form" property="usr030selectusers" scope="request">
<logic:iterate id="users" name="usr031Form" property="usr030selectusers" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="usr030selectusers" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:equal name="usr031Form" property="processMode" value="kojn_edit">
<input type="hidden" name="helpPrm" value="<bean:write name="usr031Form" property="processMode" />">
</logic:equal>
<logic:notEqual name="usr031Form" property="processMode" value="kojn_edit">
<input type="hidden" name="helpPrm" value="add">
</logic:notEqual>

<input type="hidden" name="delLabel" value="">


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <logic:notEqual name="usr031Form" property="processMode" value="kojn_edit">
      <img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    </logic:notEqual>
    <logic:equal name="usr031Form" property="processMode" value="kojn_edit">
      <img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    </logic:equal>
    <logic:equal name="usr031Form" property="processMode"  scope="request" value="add">
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="user.usr031.3" /> ]</td>
    </logic:equal>
    <logic:equal name="usr031Form" property="processMode"  scope="request" value="edit">
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="user.usr031.4" /> ]</td>
    </logic:equal>
    <logic:equal name="usr031Form" property="processMode"  scope="request" value="kojn_edit">
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.modify.personalinfo" /> ]</td>
    </logic:equal>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="usr031Form" property="adminFlg" value="true">
        <logic:equal name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
          <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="submitStyleChange();setShowGroup();getSelectGroup();return buttonPushUsr('usr031_kakunin');">
        </logic:equal>
        <logic:notEqual name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
          <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="setShowGroup();getSelectGroup();return buttonPushUsr('usr031_kakunin');">
        </logic:notEqual>
      </logic:equal>

      <logic:notEqual name="usr031Form" property="adminFlg" value="true">
        <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="return buttonPushUsr('usr031_kakunin');">
      </logic:notEqual>

      <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPushUsr('Usr031_Back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">

    <logic:equal name="usr031Form" property="adminFlg" value="true">

    <!-- ユーザID-->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.user.id" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
      <html:text property="usr031userid" style="width:333px;" maxlength="256" />
      <br>
      <% String userMinIdCnt = "2"; %>
      <span class="text_base">*<gsmsg:write key="user.usr031.10" arg0="<%= userMinIdCnt %>" /></span><br>
      <span class="text_base">*<gsmsg:write key="user.usr031.7" /></span>
    </td>
    </tr>

    <logic:equal name="usr031Form" property="changePassword" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.CHANGEPASSWORD_PARMIT) %>">
    <!-- パスワード -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.117" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%" colspan="2">
      <html:password property="usr031password" style="width:333px;" maxlength="256" /><br>
      <html:password property="usr031passwordkn" style="width:333px;" maxlength="256" /><span class="text_base">&nbsp;<gsmsg:write key="user.19" /></span><br>
      <span class="text_base"><html:checkbox styleId="pswd_kbn" name="usr031Form" property="usr031PswdKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.PSWD_UPDATE_ON) %>" /><label for="pswd_kbn"><gsmsg:write key="user.usr031.9" /></label></span><br>
      <bean:define id="digitStr" name="usr031Form" property="usr031Digit" type="java.lang.Integer" />
      <span class="text_base">*<gsmsg:write key="user.usr031.10" arg0="<%= String.valueOf(digitStr.intValue()) %>" /></span><br>
      <logic:equal name="usr031Form" property="usr031CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_EN)  %>"><span class="text_base">*<gsmsg:write key="user.usr031.12" /></span><br></logic:equal>
      <logic:equal name="usr031Form" property="usr031CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_ENS)  %>"><span class="text_base">*<gsmsg:write key="user.usr031.19" /></span><br></logic:equal>
      <span class="text_base">*<gsmsg:write key="user.usr031.11" /></span><br>
      <logic:equal name="usr031Form" property="usr031UidPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_UIDPSWDKBN_ON)  %>"><span class="text_base">*<gsmsg:write key="user.usr031.13" /></span></logic:equal>

    </td>
    </tr>
    </logic:equal>

    </logic:equal>

    <!-- 職員番号 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.employee.staff.number" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <html:text property="usr031shainno" style="width:273px;" maxlength="20" />
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
        <logic:equal name="usr031Form" property="usr031ImageName" value="">
          <img src="../user/images/photo.gif" name="pitctImage" width="130" height="150" alt="<gsmsg:write key="cmn.photo" />" border="1">
        </logic:equal>
        <logic:notEqual name="usr031Form" property="usr031ImageName" value="">

          <img src="../user/usr031.do?CMD=getImageFile" name="pitctImage" alt="<gsmsg:write key="cmn.photo" />" border="1" onload="initImageView130('pitctImage');">

        </logic:notEqual>
      </td>
      <td width="100%" nowrap><span class="text_base"><html:radio styleId="usr031UsiPicgKf_0" name="usr031Form" property="usr031UsiPicgKf" value="<%= infoOpen %>" /><label for="usr031UsiPicgKf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiPicgKf_1" name="usr031Form" property="usr031UsiPicgKf" value="<%= infoClose %>" /><label for="usr031UsiPicgKf_1"><gsmsg:write key="cmn.not.publish" /></label></span></td>
      </tr>
      <tr>
      <td nowrap>
      <span class="text_base">(<gsmsg:write key="user.usr031.14" />)</span><br>
      <span class="text_r1"><gsmsg:write key="user.usr031.20" /></span><br>
      <logic:notEqual name="usr031Form" property="adminFlg" value="true">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPushUsr('pictDelete');">&nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('usr031PictFileList', '<bean:write name="usr031Form" property="usr031pluginId" />', '0', '4');">
      </logic:notEqual>
      <logic:equal name="usr031Form" property="adminFlg" value="true">
      <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="setShowGroup();getSelectGroup();return buttonPushUsr('pictDelete');">&nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('usr031PictFileList', '<bean:write name="usr031Form" property="usr031pluginId" />', '0', '4');">
      </logic:equal>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- 氏名 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.name" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <span class="text_base2"><gsmsg:write key="cmn.lastname" /></span>&nbsp;<html:text property="usr031sei" style="width:153px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_SEI) %>" />
      <span class="text_base2"><gsmsg:write key="cmn.name3" /></span>&nbsp;<html:text property="usr031mei" style="width:153px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_MEI) %>" />
    </td>
    </tr>

    <!-- 氏名かな -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.119" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <span class="text_base2"><gsmsg:write key="cmn.lastname" /></span>&nbsp;<html:text property="usr031seikn" style="width:153px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_SEI_KN) %>" />
      <span class="text_base2"><gsmsg:write key="cmn.name3" /></span>&nbsp;<html:text property="usr031meikn" style="width:153px;" maxlength="<%= String.valueOf(GSConstUser.MAX_LENGTH_USER_NAME_MEI_KN) %>" /><br>
      <span class="text_base">*<gsmsg:write key="cmn.enter.kana.zenkaku" /></span><br>
    </td>
    </tr>

    <!-- 所属 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <font class="text_bb1"><gsmsg:write key="cmn.affiliation" /></font>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <html:text property="usr031syozoku" style="width:513px;" maxlength="60" />
    </td>
    </tr>

    <!-- 役職 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.post" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
        <table cellpadding="0" cellspacing="0">
          <tr>
          <td>
            <html:select property="usr031yakushoku">
              <html:optionsCollection name="usr031Form" property="posLabelList" value="value" label="label" />
            </html:select>
            &nbsp;
          </td>

          <logic:equal name="usr031Form" property="adminFlg" value="true">
          <td><a href="#" onClick="getSelectGroup();return openpos();"><img src="../common/images/plus.gif" alt="<gsmsg:write key="cmn.add" />" border="0"></a></td>
          </logic:equal>

          </tr>
        </table>
    </td>
    </tr>


    <!-- 性別 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="user.123" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">

      <logic:notEmpty name="usr031Form" property="seibetuLabelList">
        <bean:define id="seibetuVal" name="usr031Form" property="usr031seibetu" type="java.lang.String" />
        <logic:iterate id="seibetuMdl" name="usr031Form" property="seibetuLabelList" indexId="idxSeibetu">
          <logic:equal name="seibetuMdl" property="value" value="<%= seibetuVal %>">
            <input type="radio" name="usr031seibetu" id="usr031seibetu_<%= idxSeibetu %>" value="<bean:write name="seibetuMdl" property="value" />" checked /><label class="text_base" for="usr031seibetu_<%= idxSeibetu %>"><bean:write name="seibetuMdl" property="label" /></label>
          </logic:equal>
          <logic:notEqual name="seibetuMdl" property="value" value="<%= seibetuVal %>">
            <input type="radio" name="usr031seibetu" id="usr031seibetu_<%= idxSeibetu %>" value="<bean:write name="seibetuMdl" property="value" />" /><label class="text_base" for="usr031seibetu_<%= idxSeibetu %>"><bean:write name="seibetuMdl" property="label" /></label>
          </logic:notEqual>
        </logic:iterate>
      </logic:notEmpty>

    </td>
    </tr>

    <!-- 入社年月日 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="user.122" /></font></td>
    <td valign="middle" align="left" class="td_wt" colspan="2"><html:text property="usr031entranceYear" style="width:57px;" maxlength="4" />
      <span class="text_base"><gsmsg:write key="cmn.year2" />&nbsp;</span><html:text property="usr031entranceMonth" style="width:45px;" maxlength="2" />
      <span class="text_base"><gsmsg:write key="cmn.month" />&nbsp;</span><html:text property="usr031entranceDay" style="width:45px;" maxlength="2" />
      <span class="text_base"><gsmsg:write key="cmn.day" /></span>
    </td>
    </tr>
    <!-- ヘッダ 全公開・全非公開ボタン -->
    <tr class="td_type5" >
    <td valign="middle" align="left"  nowrap colspan="3">
    </td>
    <td valign="middle" align="left"  nowrap>
        <span class="text_base">
        <a href="javascript:allPublish();" ><gsmsg:write key="cmn.all" /><gsmsg:write key="cmn.publish" /></a>
        &nbsp;
        <a href="javascript:allDisPublish();" ><gsmsg:write key="cmn.all" /><gsmsg:write key="cmn.not.publish" /></a>
        </span>
    </td>
    </tr>
    <!-- 生年月日 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="user.120" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031birthYear" style="width:57px;" maxlength="4" />
      <span class="text_base"><gsmsg:write key="cmn.year2" />&nbsp;</span><html:text property="usr031birthMonth" style="width:45px;" maxlength="2" />
      <span class="text_base"><gsmsg:write key="cmn.month" />&nbsp;</span><html:text property="usr031birthDay" style="width:45px;" maxlength="2" />
      <span class="text_base"><gsmsg:write key="cmn.day" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt7" nowrap>
      <span class="text_base"><html:radio styleId="usr031UsiBdateKf_0" name="usr031Form" property="usr031UsiBdateKf" value="<%= infoOpen %>" /><label for="usr031UsiBdateKf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiBdateKf_1" name="usr031Form" property="usr031UsiBdateKf" value="<%= infoClose %>" /><label for="usr031UsiBdateKf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span>
    </td>
    </tr>

    <!-- メールアドレス -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mailaddress1" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031mail1" style="width:352px;" maxlength="256" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031mailCmt1" maxlength="10" style="width:123px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiMail1Kf_0" name="usr031Form" property="usr031UsiMail1Kf" value="<%= infoOpen %>" /><label for="usr031UsiMail1Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiMail1Kf_1" name="usr031Form" property="usr031UsiMail1Kf" value="<%= infoClose %>" /><label for="usr031UsiMail1Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mailaddress2" /></font> </td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031mail2" style="width:352px;" maxlength="256" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031mailCmt2" maxlength="10" style="width:123px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiMail2Kf_0" name="usr031Form" property="usr031UsiMail2Kf" value="<%= infoOpen %>" /><label for="usr031UsiMail2Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiMail2Kf_1" name="usr031Form" property="usr031UsiMail2Kf" value="<%= infoClose %>" /><label for="usr031UsiMail2Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mailaddress3" /></font> </td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031mail3" style="width:352px;" maxlength="256" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031mailCmt3" maxlength="10" style="width:123px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiMail3Kf_0" name="usr031Form" property="usr031UsiMail3Kf" value="<%= infoOpen %>" /><label for="usr031UsiMail3Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiMail3Kf_1" name="usr031Form" property="usr031UsiMail3Kf" value="<%= infoClose %>" /><label for="usr031UsiMail3Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>

    <!-- 郵便番号 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" rowspan="4" nowrap><font class="text_bb1"><gsmsg:write key="cmn.address" /></font></td>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.postalcode" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031post1" style="width:51px;" maxlength="3" />
      <font class="text_base">-</font>
      <html:text property="usr031post2" style="width:51px;" maxlength="4" />
    </td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiZipKf_0" name="usr031Form" property="usr031UsiZipKf" value="<%= infoOpen %>" /><label for="usr031UsiZipKf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiZipKf_1" name="usr031Form" property="usr031UsiZipKf" value="<%= infoClose %>" /><label for="usr031UsiZipKf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>

    <!-- 都道府県 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.prefectures" /></font></td>
    <td valign="middle" align="left" class="td_wt6">
      <html:select property="usr031tdfkCd">
        <html:optionsCollection name="usr031Form" property="tdfkLabelList" value="value" label="label" />
      </html:select>
    </td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiTdfKf_0" name="usr031Form" property="usr031UsiTdfKf" value="<%= infoOpen %>" /><label for="usr031UsiTdfKf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiTdfKf_1" name="usr031Form" property="usr031UsiTdfKf" value="<%= infoClose %>" /><label for="usr031UsiTdfKf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>

    <!-- 住所1 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap><font class="text_bb1"><gsmsg:write key="cmn.address" />１</font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031address1" style="width:564px;" maxlength="100" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiAddr1Kf_0" name="usr031Form" property="usr031UsiAddr1Kf" value="<%= infoOpen %>" /><label for="usr031UsiAddr1Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiAddr1Kf_1" name="usr031Form" property="usr031UsiAddr1Kf" value="<%= infoClose %>" /><label for="usr031UsiAddr1Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </td>
    </tr>

    <!-- 住所2 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap> <font class="text_bb1"><gsmsg:write key="cmn.address" />２</font> </td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031address2" style="width:564px;" maxlength="100" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiAddr2Kf_0" name="usr031Form" property="usr031UsiAddr2Kf" value="<%= infoOpen %>" /><label for="usr031UsiAddr2Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiAddr2Kf_1" name="usr031Form" property="usr031UsiAddr2Kf" value="<%= infoClose %>" /><label for="usr031UsiAddr2Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>

    <!-- 電話番号 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel1" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031tel1" style="width:171px;" maxlength="20" />&nbsp;
      <span class="text_base2"><gsmsg:write key="user.136" />：&nbsp;</span><html:text property="usr031telNai1" maxlength="15" style="width:93px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031telCmt1" maxlength="10" style="width:153px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiTel1Kf_0" name="usr031Form" property="usr031UsiTel1Kf" value="<%= infoOpen %>" /><label for="usr031UsiTel1Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiTel1Kf_1" name="usr031Form" property="usr031UsiTel1Kf" value="<%= infoClose %>" /><label for="usr031UsiTel1Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel2" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031tel2" style="width:171px;" maxlength="20" />&nbsp;
      <span class="text_base2"><gsmsg:write key="user.136" />：&nbsp;</span><html:text property="usr031telNai2" maxlength="15" style="width:93px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031telCmt2" maxlength="10" style="width:153px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiTel2Kf_0" name="usr031Form" property="usr031UsiTel2Kf" value="<%= infoOpen %>" /><label for="usr031UsiTel2Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiTel2Kf_1" name="usr031Form" property="usr031UsiTel2Kf" value="<%= infoClose %>" /><label for="usr031UsiTel2Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.tel3" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031tel3" style="width:171px;" maxlength="20" />&nbsp;
      <span class="text_base2"><gsmsg:write key="user.136" />：&nbsp;</span><html:text property="usr031telNai3" maxlength="15" style="width:93px;" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031telCmt3" maxlength="10" style="width:153px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiTel3Kf_0" name="usr031Form" property="usr031UsiTel3Kf" value="<%= infoOpen %>" /><label for="usr031UsiTel3Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiTel3Kf_1" name="usr031Form" property="usr031UsiTel3Kf" value="<%= infoClose %>" /><label for="usr031UsiTel3Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>

    <!-- FAX -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="user.143" /></font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031fax1" style="width:171px;" maxlength="20" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031faxCmt1" maxlength="10" style="width:153px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiFax1Kf_0" name="usr031Form" property="usr031UsiFax1Kf" value="<%= infoOpen %>" /><label for="usr031UsiFax1Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiFax1Kf_1" name="usr031Form" property="usr031UsiFax1Kf" value="<%= infoClose %>" /><label for="usr031UsiFax1Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ２</font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031fax2" style="width:171px;" maxlength="20" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031faxCmt2" maxlength="10" style="width:153px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiFax2Kf_0" name="usr031Form" property="usr031UsiFax2Kf" value="<%= infoOpen %>" /><label for="usr031UsiFax2Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiFax2Kf_1" name="usr031Form" property="usr031UsiFax2Kf" value="<%= infoClose %>" /><label for="usr031UsiFax2Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1">ＦＡＸ３</font></td>
    <td valign="middle" align="left" class="td_wt6"><html:text property="usr031fax3" style="width:171px;" maxlength="20" />&nbsp;
      <span class="text_base2"><gsmsg:write key="cmn.comment" />：&nbsp;</span><html:text property="usr031faxCmt3" maxlength="10" style="width:153px;" /></td>
    <td valign="middle" align="left" class="td_wt7" nowrap><span class="text_base"><html:radio styleId="usr031UsiFax3Kf_0" name="usr031Form" property="usr031UsiFax3Kf" value="<%= infoOpen %>" /><label for="usr031UsiFax3Kf_0"><gsmsg:write key="cmn.publish" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiFax3Kf_1" name="usr031Form" property="usr031UsiFax3Kf" value="<%= infoClose %>" /><label for="usr031UsiFax3Kf_1"><gsmsg:write key="cmn.not.publish" /></label>&nbsp;</span></td>
    </tr>

    <logic:equal name="usr031Form" property="adminFlg" value="true">
      <logic:equal name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">

      <tr>
      <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap><font class="text_bb1"><gsmsg:write key="cmn.mobile.use" /></font></td>
      <td valign="middle" align="left" class="td_wt7" colspan="2">
        <table width="100%" border="0">
        <tr>
        <td>
          <span class="text_base"><html:radio styleId="usr031UsiMblUse_0" name="usr031Form" property="usr031UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_OK) %>" onclick="return changeUidElementStatus();" /><label for="usr031UsiMblUse_0"><gsmsg:write key="cmn.accepted" /></label>&nbsp;&nbsp;<html:radio styleId="usr031UsiMblUse_1" name="usr031Form" property="usr031UsiMblUseKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MBL_USE_NG) %>" onclick="return changeUidElementStatus();" /><label for="usr031UsiMblUse_1"><gsmsg:write key="cmn.not" /></label></span>
        </td>
        </tr>
        <tr>
        <td>
          <span class="sc_ttl_sun"><gsmsg:write key="main.man210.6" /></span>
          <br><span class="text_base"><html:checkbox name="usr031Form" property="usr031NumCont" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_CONTROL) %>" styleId="num_seigyo" onclick="return changeUidElementStatus();" /><label for="num_seigyo"><gsmsg:write key="cmn.login.control.identification.number" /></label></span>
        </td>
        </tr>
        <tr>
        <td style="padding-left: 15px;">
          <span class="text_base"><html:checkbox name="usr031Form" property="usr031NumAutAdd" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.UID_AUTO_REG_OK) %>" styleId="autoreg" onclick="return changeUidElementStatus();" /><label for="autoreg"><gsmsg:write key="user.99" /></label></span>
          <br><span class="sc_ttl_sun">&nbsp;&nbsp;&nbsp;&nbsp;<span class="sc_ttl_sun">*<gsmsg:write key="user.100" /></span>
          <br><span class="sc_ttl_sun">&nbsp;&nbsp;&nbsp;&nbsp;<span class="sc_ttl_sun">*<gsmsg:write key="user.102" /></span>
        </td>
        </tr>
        <tr>
        <td style="padding-left: 35px; padding-top: 5px;">
          <span class="text_base"><gsmsg:write key="user.105" />：&nbsp;<html:text name="usr031Form" property="usr031CmuUid1" style="width:333px;" maxlength="50" />&nbsp;<input type="button" class="btn_base1s" name="hisBtn1" id="hisBtn1" value="<gsmsg:write key="user.usr031.18" />" onclick="return openUidHisWindow(<bean:write name="usr031Form" property="usr030selectuser" />, 1);"></span>
        </td>
        </tr>
        <tr>
        <td style="padding-left: 35px;">
          <span class="text_base"><gsmsg:write key="user.106" />：&nbsp;<html:text name="usr031Form" property="usr031CmuUid2" style="width:333px;" maxlength="50" />&nbsp;<input type="button" class="btn_base1s" name="hisBtn2" id="hisBtn2" value="<gsmsg:write key="user.usr031.18" />" onclick="return openUidHisWindow(<bean:write name="usr031Form" property="usr030selectuser" />, 2);"></span>
        </td>
        </tr>
        <tr>
        <td style="padding-left: 35px;">
          <span class="text_base"><gsmsg:write key="user.107" />：&nbsp;<html:text name="usr031Form" property="usr031CmuUid3" style="width:333px;" maxlength="50" />&nbsp;<input type="button" class="btn_base1s" name="hisBtn3" id="hisBtn3" value="<gsmsg:write key="user.usr031.18" />" onclick="return openUidHisWindow(<bean:write name="usr031Form" property="usr030selectuser" />, 3);"></span>
        </td>
        </tr>
        </table>
      </td>
      </tr>

      </logic:equal>
    </logic:equal>

    <!-- ラベル -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1"  colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.label" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt"  colspan="2" nowrap>
    <logic:equal name="usr031Form" property="labelSetPow" value="<%= labelSetOk %>">
      <table cellpadding="0" cellspacing="0">
      <tr><td><input type="button" value="<gsmsg:write key="cmn.select.label" />" class="btn_base1"  onClick="return openlabel();"></td></tr>
      </table>
    </logic:equal>

      <logic:notEmpty name="usr031Form" property="selectLabelList">

      <table cellpadding="0" cellspacing="0" class="tl0 spacer" width="40%">

      <% String[] labelClass = {"td_label1", "td_label2"}; %>
      <logic:iterate id="labelData" name="usr031Form" property="selectLabelList" indexId="idx">

      <tr class="<%= labelClass[idx.intValue() % 2] %>">
      <td width="100%">
    <logic:equal name="usr031Form" property="labelSetPow" value="<%= labelSetOk %>">
      <img src="../common/images/delete.gif" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="deleteLabel('<bean:write name="labelData" property="labSid" />');">&nbsp;
    </logic:equal>
      <bean:write name="labelData" property="labName" />
      </td>
      </tr>

      </logic:iterate>

      </table>
      </logic:notEmpty>


    </td>
    </tr>

    <!-- ソートキー1,2 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <font class="text_bb1"><gsmsg:write key="cmn.sortkey" /></font>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <span class="text_base">1：</span><html:text property="usr031sortkey1" style="width:93px;" maxlength="10" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="text_base">2：</span><html:text property="usr031sortkey2" style="width:93px;" maxlength="10" />
    </td>
    </tr>



    <!-- 備考 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.memo" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <textarea style="width:541px;" name="usr031bikou"  rows="5" wrap="hard" onkeyup="showLengthStr(value, <%= maxLengthBiko %>, 'inputlength');" id="inputstr"><bean:write name="usr031Form" property="usr031bikou" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;<span class="font_string_count_max">/&nbsp;<%= maxLengthBiko %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <logic:equal name="usr031Form" property="adminFlg" value="true">
    <!-- グループ設定ヘッダ -->
    <tr>
    <td valign="middle" align="left" class="td_type5" nowrap colspan="4">
      <span class="text_tl2"><gsmsg:write key="user.86" /></span><span class="text_r2"></span>
    </td>
    </tr>

    <!--  グループ設定 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.affiliation.group" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">

      <tr>
      <td align="center">
        <table width="100%">
        <tr>
        <td width="100%" colspan="2"><img alt="<gsmsg:write key="user.116" />" src="../common/images/damy.gif" height="4" width="1"></td>
        </tr>
        <tr>
        <td align="left" width="80%">
        <iframe name="grpFrame" src="../user/usr021.do?parentName=usr031" class="iframe_01" height="300" width="100%">
        <gsmsg:write key="user.32" />
        </iframe>
        </td>
        <td align="center" valign="top" width="20%">
          <br><input type="button" name="all_off" class="btn_base1w" onclick="javascript:onAllUnCheck();defaultGroup();" value="<gsmsg:write key="user.31" />">
          <br><br><input type="button" name="all_off" class="btn_base1w" onclick="javascript:onParentChecked();defaultGroup();" value="<gsmsg:write key="user.33" />">
          <br><br><input type="button" name="all_off" class="btn_base1w" onclick="javascript:onChildChecked();;defaultGroup();" value="<gsmsg:write key="user.34" />">
        </td>
        </tr>
        <tr>
        <td width="100%" colspan="2"><img alt="<gsmsg:write key="user.116" />" src="../common/images/damy.gif" height="4" width="1"></td>
        </tr>
        </table>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <!-- デフォルトグループ -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" colspan="2" nowrap>

    <logic:lessEqual name="usr031Form" property="usr030selectuser" value="<%= reservUser %>">
      <span class="text_bb1"><gsmsg:write key="user.35" /></span><span class="text_r2">※</span>
    </logic:lessEqual>
    <logic:greaterThan name="usr031Form" property="usr030selectuser" value="<%= reservUser %>">
      <span class="text_bb1"><gsmsg:write key="user.35" /></span><span class="text_r2">※</span>
    </logic:greaterThan>

    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <logic:empty name="usr031Form" property="groupList">
        <html:select property="usr031defgroup" style="width:550px;">
          <option value="-1"><gsmsg:write key="cmn.select.plz" /></option>
        </html:select>
      </logic:empty>
      <logic:notEmpty name="usr031Form" property="groupList">
        <html:select property="usr031defgroup" style="width:550px;">
          <option value="-1"><gsmsg:write key="cmn.select.plz" /></option>
          <html:optionsCollection name="usr031Form" property="groupList" value="groupSid" label="groupName" />
        </html:select>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" >
      <logic:equal name="usr031Form" property="adminFlg" value="true">
        <logic:equal name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
          <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="submitStyleChange();setShowGroup();getSelectGroup();return buttonPushUsr('usr031_kakunin');">
        </logic:equal>
        <logic:notEqual name="usr031Form" property="usr031UsiMblUse" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
          <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="setShowGroup();getSelectGroup();return buttonPushUsr('usr031_kakunin');">
        </logic:notEqual>
      </logic:equal>

      <logic:notEqual name="usr031Form" property="adminFlg" value="true">
        <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="return buttonPushUsr('usr031_kakunin');">
      </logic:notEqual>
      <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPushUsr('Usr031_Back');">
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
</div>

</body>
</html:html>