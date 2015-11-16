<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.usr.GSConstUser" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="main.man002.24" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../user/js/usr030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="exportExecute();">

<html:form action="/user/usr030">
<input type="hidden" name="CMD" value="">
<html:hidden property="processMode" />
<html:hidden property="csvOut" />
<html:hidden property="usr030cmdMode" />
<html:hidden property="usr030SearchFlg" />
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

<logic:equal name="usr030Form" property="usr030cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NAME) %>">
    <input type="hidden" name="helpPrm" value="0">
</logic:equal>

<logic:equal name="usr030Form" property="usr030cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_SHOUSAI) %>">
    <input type="hidden" name="helpPrm" value="1">
</logic:equal>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.24" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right" nowrap>
      <input type="button" name="btn_prjadd" class="btn_group_n1" value="<gsmsg:write key="user.44" />" onClick="buttonPush('groupEdit');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('back');">
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <html:errors />
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>

    <td width="100%" nowrap>

    <logic:equal name="usr030Form" property="usr030cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NAME) %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('name');"><gsmsg:write key="cmn.name2" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('shousai');"><gsmsg:write key="cmn.advanced.search" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
    </logic:equal>

    <logic:equal name="usr030Form" property="usr030cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_SHOUSAI) %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('name');"><gsmsg:write key="cmn.name2" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('shousai');"><gsmsg:write key="cmn.advanced.search" /></a>
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
    <!-- ユーザ管理-->
    <table cellpadding="5" cellspacing="0" border="0" class="tl0 td_gray2" width="100%">
    <tr>
    <td width="100%" valign="top" class="td_usr_header">
    <!-- 氏名 　　-->
    <logic:equal name="usr030Form" property="usr030cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_NAME) %>">
      <%@ include file="/WEB-INF/plugin/user/jsp/usr030_sub01.jsp" %>
    </logic:equal>

    <!-- 詳細検索 -->
    <logic:equal name="usr030Form" property="usr030cmdMode" value="<%= String.valueOf(jp.groupsession.v2.usr.GSConstUser.MODE_SHOUSAI) %>">
      <%@ include file="/WEB-INF/plugin/user/jsp/usr030_sub02.jsp" %>
    </logic:equal>
    <tr>
    <td width="100%">
      <table  width="100%" cellpadding="0" cellspacing="0">
      <tr align="left">
      <td width="0%">
        <logic:empty name="usr030Form" property="usr030users">
          <html:select property="usr030selectusers" style="width:550px;" size="20">
          </html:select>
        </logic:empty>
        <logic:notEmpty name="usr030Form" property="usr030users">
          <html:select property="usr030selectusers" style="width:550px;" size="20"  multiple="multiple">
            <html:optionsCollection name="usr030Form" property="usr030users" value="usrsid" label="usiseimei" />
          </html:select>
        </logic:notEmpty>
      </td>
      <td><img height="1" src="../common/images/damy.gif" width="10"></td>
      <td width="100%" align="center" valign="top">
        <table width="0%">
        <tr>
        <td>
          <br>
          <input type="button" name="btn_prjadd" class="btn_add_n3" value="<gsmsg:write key="user.142" />"  onClick="buttonPush('add');">
          <br><br>
          <input type="button" name="btn_prjadd" class="btn_edit_n2" value="<gsmsg:write key="cmn.fixed2" />"  onClick="buttonPush('edit');">
          <br><br>
          <input type="button" name="btn_prjadd" class="btn_dell_n2" value="<gsmsg:write key="cmn.delete2" />"  onClick="buttonPush('del');">
          <br>
          <span class="text_base"><a href="javascript:buttonPush('csvDel');">※<gsmsg:write key="user.usr030.1" /></a></span>
          <br><br>
          <input type="button" class="btn_csv_n3" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('userImp');">
          <br><br>
          <input type="button" class="btn_csv_n3" value="<gsmsg:write key="cmn.export" />" onClick="exportPush('userExp');">
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
</td>
</tr>
</table>

</html:form>
<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
      <map name="imagemap">
        <area shape="rect" coords="0,3,97,26" href="javascript:changeTab('name');" alt="<gsmsg:write key="cmn.name" />">
        <area shape="rect" coords="104,3,197,26" href="javascript:changeTab('shousai');" alt="<gsmsg:write key="cmn.advanced.search" />">
      </map>
</body>
</html:html>