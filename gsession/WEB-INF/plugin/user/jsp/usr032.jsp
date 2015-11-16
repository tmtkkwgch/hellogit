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
<title>[GroupSession] <gsmsg:write key="user.usr032.3" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../user/js/group.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr032.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<html:form action="/user/usr032">

<logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
  <body class="body_03" onload="defaultGroup();lmtEnableDisable();" onunload="windowClose();">
</logic:equal>

<logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
  <body class="body_03" onload="lmtEnableDisable();" onunload="windowClose();">
</logic:equal>


<input type="hidden" name="CMD" value="">
<html:hidden property="processMode" />
<html:hidden property="csvOut" />
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
<html:hidden property="usr032cmdMode" />


<html:hidden property="usr030SearchKana" />
<html:hidden property="selectgroup" />

<logic:notEmpty name="usr032Form" property="groupList">
  <logic:iterate id="grpData" name="usr032Form" property="groupList">
    <input type="hidden" name="defGrpId" value="<bean:write name="grpData" property="groupSid" />">
    <input type="hidden" name="defGrpNm" value="<bean:write name="grpData" property="groupName" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr032Form" property="usr030selectusers">
<logic:iterate id="usrSid" name="usr032Form" property="usr030selectusers">
  <input type="hidden" name="usr030selectusers" value="<bean:write name="usrSid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
  <input type="hidden" name="helpPrm" value="0">
</logic:equal>

<logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
  <input type="hidden" name="helpPrm" value="1">
</logic:equal>

<!-- BODY -->

<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="user.usr032.3" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
      <input type="button" name="btn_add" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="getSelectGroup();buttonSubmit('Usr032_userImp');">
      </logic:equal>
      <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
      <input type="button" name="btn_add" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonSubmit('Usr032_userImp');">
      </logic:equal>
      <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonSubmit('Usr032_Back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <html:errors />
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>

        <td width="100%" nowrap>
        <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_2_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('tujou');"><gsmsg:write key="group.designation" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_3_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('groupikatu');"><gsmsg:write key="user.usr032.5" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
        </logic:equal>

        <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_2_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('tujou');"><gsmsg:write key="group.designation" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_3_on"  nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('groupikatu');"><gsmsg:write key="user.usr032.5" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
        </logic:equal>
        </td>

        <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
    </table>

    <!-- 取込みファイル -->
    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl_u2">
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt6" width="0%" colspan="1">

      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('usr032selectFiles', '<bean:write name="usr032Form" property="usr032pluginId" />', '1', '0');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');"><br>
      <html:select property="usr032selectFiles" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="usr032Form" property="usr032FileLabelList" value="value" label="label" />
      </html:select>
    </td>
    <td valign="middle" align="left" class="td_wt7" width="100%" colspan="1">
    <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
    <% String csvFileMsg = ""; %>
    <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
      <% csvFileMsg = "<a href=\"../user/usr032.do?CMD=Usr032_sample01&sample=1\">" + gsMsg.getMessage(request, "user.usr032.8") + "</a>"; %>
      &nbsp;<span class="text_base">*<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" /></span>
      <br>&nbsp;<span class="text_base">*<gsmsg:write key="user.91" />⇒<a href="../user/usr032.do?CMD=Usr032_sample01&sample=1">【<gsmsg:write key="cmn.download" />】</a></span>
    </logic:equal>
    <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
      <% csvFileMsg = "<a href=\"../user/usr032.do?CMD=Usr032_sample02&sample=1\">" + gsMsg.getMessage(request, "user.usr032.8") + "</a>"; %>
      &nbsp;<span class="text_base">*<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" /></span>
      <br>&nbsp;<span class="text_base">*<gsmsg:write key="user.91" />⇒<a href="../user/usr032.do?CMD=Usr032_sample02&sample=1">【<gsmsg:write key="cmn.download" />】</a></span>
    </logic:equal>
    </td>
    </tr>

    <!-- 所属グループ,デフォルトグループ　-->
    <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
      <%@ include file="/WEB-INF/plugin/user/jsp/usr032_sub01.jsp" %>
    </logic:equal>

    <logic:equal name="usr032Form" property="changePassword" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.CHANGEPASSWORD_PARMIT) %>">
    <!--  パスワード変更 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.change.password" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
    <html:checkbox name="usr032Form" property="usr032PswdKbn" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.PSWD_UPDATE_ON) %>" styleId="pswd_kbn" /><label for="pswd_kbn" class="text_base"><gsmsg:write key="user.usr031.9" /></label>
    </td>
    </tr>
    </logic:equal>

    <!-- 上書き -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt" colspan="2">
      <html:checkbox name="usr032Form" property="usr032updateFlg" value="1" styleId="updateFlg" onclick="lmtEnableDisable();" /><label for="updateFlg" class="text_base"><gsmsg:write key="user.1" /></label>
      <div id="lmtinput">
        <html:multibox name="usr032Form" property="usr032updatePassFlg" value="1" styleId="passUp" /><label for="passUp" class="text_base"><gsmsg:write key="user.no.pass.override" /></label>
      </div>
    </td>
    </tr>

    <!-- グループ作成 -->
    <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
      <%@ include file="/WEB-INF/plugin/user/jsp/usr032_sub02.jsp" %>
    </logic:equal>

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NORMAL) %>">
      <input type="button" name="btn_add" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="getSelectGroup();buttonSubmit('Usr032_userImp');">
      </logic:equal>
      <logic:equal name="usr032Form" property="usr032cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_GROUP_ALL) %>">
      <input type="button" name="btn_add" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonSubmit('Usr032_userImp');">
      </logic:equal>
      <input type="button" name="btn_back" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonSubmit('Usr032_Back');">
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