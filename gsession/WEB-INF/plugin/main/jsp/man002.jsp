<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">
<html:form action="/main/man002">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <bean:define id="kusr" name="<%= key %>" scope="session" />
      <logic:notEqual name="kusr" property="usrsid" value="0">
        <span id="rt">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('man002back');">
        </span>
      </logic:notEqual>
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td valign="top" width="49%">

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.1" />"><gsmsg:write key="cmn.license.info" /></dt>
      <dd>
        <li class="text">
        <table border="0" style="padding-left:5px;padding-top:15px;padding-right:25px;padding-bottom:15px;">

        <tr>
        <td width="25%" align="right" nowrap>
          <span class="text_bb1"><gsmsg:write key="main.man002.2" /></span>
        </td>
        <td width="75%" style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <logic:empty name="man002Form" property="gsUid">&nbsp;</logic:empty>
          <logic:notEmpty name="man002Form" property="gsUid">
            <span class="text_gray"><bean:write name="man002Form" property="gsUid" /></span>
          </logic:notEmpty>
        </td>
        </tr>

        <tr>
        <td width="25%" align="right" nowrap>
          <span class="text_bb1"><gsmsg:write key="main.man002.3" /></span>
        </td>
        <td width="75%" style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <logic:empty name="man002Form" property="licenseId">&nbsp;</logic:empty>
          <logic:notEmpty name="man002Form" property="licenseId">
            <span class="text_gray"><bean:write name="man002Form" property="licenseId" /></span>
          </logic:notEmpty>
        </td>
        </tr>

        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        <tr>
        <td align="right">
          <span class="text_bb1"><gsmsg:write key="cmn.company.name" />：</span>
        </td>
        <td style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <logic:empty name="man002Form" property="licenseCom">&nbsp;</logic:empty>
          <logic:notEmpty name="man002Form" property="licenseCom">
            <span class="text_gray"><bean:write name="man002Form" property="licenseCom" /></span>
          </logic:notEmpty>
        </td>
        </tr>

        <logic:notEmpty name="man002Form" property="pluginList">
        <logic:iterate id="plugin" name="man002Form" property="pluginList" scope="request" indexId="cnt">
        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        <tr>
        <td align="right">
          <span class="text_bb1"><bean:write name="plugin" property="pluginName" />：</span>
        </td>
        <td style="border:1px;border-bottom-style:solid;border-color:#cccccc;"><span class="text_gray"><gsmsg:write key="cmn.period2" />&nbsp;<bean:write name="plugin" property="licenseLimit" /></span></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>

























        <logic:equal name="man002Form" property="sysKbn" value="1">
        <bean:define id="usrCnt" name="man002Form" property="userCount" type="java.lang.String" />
        <bean:define id="maxCnt" name="man002Form" property="licenseCount" type="java.lang.String" />
        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        <tr>
        <td width="25%" align="right" nowrap>
          <span class="text_bb1">ByCloud：</span>
        </td>
        <td style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <logic:empty name="man002Form" property="licenseId">&nbsp;</logic:empty>
          <logic:notEmpty name="man002Form" property="licenseId">
            <span class="text_gray"><%= usrCnt %>/<gsmsg:write key="bmk.23" arg0="<%= maxCnt %>" /></span>
          </logic:notEmpty>
        </td>
        </tr>

        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>
        <tr>
        <td width="25%" align="right" nowrap>
          <span class="text_bb1"><gsmsg:write key="wml.88" />：</span>
        </td>
        <td style="border:1px;border-bottom-style:solid;border-color:#cccccc;">
          <span class="text_gray"><bean:write name="man002Form" property="dbUse" />/<bean:write name="man002Form" property="dbCanUse" />MB</span>
        </td>
        </tr>

        </logic:equal>































        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>

        <tr>
        <td colspan="2" align="center">
          <logic:notEqual name="man002Form" property="sysKbn" value="1">
            <input type="button" value="<gsmsg:write key="main.man002.7" />" class="btn_base1_4" onClick="return buttonPush('licenseReg');">
          </logic:notEqual>
        </td>
        </tr>
        <tr>
        <td colspan="2"><img src="../common/images/spacer.gif" alt="" width="1" height="5"></td>
        </tr>

        <tr>
        <td colspan="2" align="right">
        <bean:define id="manCnt" name="man002Form" property="userCount" type="java.lang.String" />
        <span class="text_gray"><gsmsg:write key="main.man002.8" />&nbsp;</span><span class="text_gray"><gsmsg:write key="bmk.23" arg0="<%= manCnt %>" /></span>
        </td>
        </tr>

        <logic:notEqual name="man002Form" property="limitUserCount" value="0">
        <tr>
        <td colspan="2" align="right">
        <bean:define id="manLimCnt" name="man002Form" property="limitUserCount" type="java.lang.String" />
        <span class="text_gray"><gsmsg:write key="main.man002.9" />&nbsp;</span><span class="text_gray"><gsmsg:write key="bmk.23" arg0="<%= manLimCnt %>" /></span>
        </td>
        </tr>
        </logic:notEqual>

        <tr>
        <td colspan="2" align="right">
          <logic:notEmpty name="man002Form" property="licensePageUrl">
            <logic:notEqual name="man002Form" property="sysKbn" value="1">
            <logic:notEmpty name="man002Form" property="gsUid">
              <a href="<bean:write name="man002Form" property="licensePageUrl" />?ksSN=<bean:write name="man002Form" property="gsUid" />" target="_blank"><span class="sc_link_b"><gsmsg:write key="main.man002.10" /></span></a>
            </logic:notEmpty>
            <logic:empty name="man002Form" property="gsUid">
              <a href="<bean:write name="man002Form" property="licensePageUrl" />" target="_blank"><span class="sc_link_b"><gsmsg:write key="main.man002.10" /></span></a>
            </logic:empty>
            </logic:notEqual>

            <logic:equal name="man002Form" property="sysKbn" value="1">
                <a href="<bean:write name="man002Form" property="licensePageUrl" />&byCloudId=<bean:write name="man002Form" property="domain" />" target="_blank"><span class="sc_link_b">ライセンスの更新・ユーザ数追加はこちらから</span></a>
            </logic:equal>
          </logic:notEmpty>
        </td>
        </tr>
        </table>

      </li>
      </dd>
      </dl>

      <br>

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.1" />"><gsmsg:write key="main.man002.1" /></dt>
      <dd>
        <li class="text">
          <img src="../mobile/images/menu_icon_single.gif" class="img_border img_menu_icon_single" alt="<gsmsg:write key="cmn.mobile.use.massconfig" />">
          <a href="#" onClick="return buttonPush('mblUse');"><span class="text_link"><gsmsg:write key="cmn.mobile.use.massconfig" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.14" /></span>
        </li>
      </dd>
      </dl>

      <logic:notEmpty name="man002Form" property="pluginMenuList">
      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="cmn.plugin" />"><gsmsg:write key="cmn.plugin" /></dt>
      <dd>
      <!-- <gsmsg:write key="main.4" /> -->
      <li class="text">
        <img src="../main/images/menu_icon_single_info.gif" class="img_border img_menu_icon_single" alt="">
        <a href="../main/man300.do?CMD=admconf&backScreen=1"><span class="text_link"><gsmsg:write key="cmn.information" /></span></a>
        <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.71" /></span>
      </li>
      <logic:equal name="man002Form" property="portalUseOk" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PLUGIN_USE) %>">
      <li class="text">
        <img src="../portal/images/menu_icon_single.gif" class="img_border img_menu_icon_single" alt="">
        <a href="../portal/ptl020.do"><span class="text_link"><gsmsg:write key="portal.portal" /></span></a>
        <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.72" /></span>
      </li>
      </logic:equal>
      <logic:iterate id="plugin" name="man002Form" property="pluginMenuList">
        <bean:define id="plname" name="plugin" property="name" type="java.lang.String" />
        <li class="text">
          <img src="<bean:write name='plugin' property='icon' />" class="img_border" alt="">
          <a href="<bean:write name='plugin' property='url' />"><span class="text_link"><bean:write name='plugin' property='name' /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.17" arg0="<%= plname %>" /></span>
        </li>

      </logic:iterate>

      </dd>
      </dl>
      </logic:notEmpty>

    </td>

    <td width="2%">&nbsp;</td>

    <td width="49%" valign="top">

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="cmn.preferences" />"><gsmsg:write key="cmn.preferences" /></dt>
      <dd>
        <li class="text">
          <img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.19" />">
          <a href="#" onClick="return buttonPush('pluginlist');"><span class="text_link"><gsmsg:write key="main.man002.19" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.20" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_enterprise.gif" class="img_border" alt="<gsmsg:write key="main.man002.21" />">
          <a href="#" onClick="return buttonPush('enterpriseiinfo');"><span class="text_link"><gsmsg:write key="main.man002.21" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.22" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_syain.gif" class="img_border" alt="<gsmsg:write key="user.44" />">
          <a href="#" onClick="return buttonPush('grouplist');"><span class="text_link"><gsmsg:write key="user.44" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.23" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_syain.gif" class="img_border" alt="<gsmsg:write key="main.man002.24" />">
          <a href="#" onClick="return buttonPush('userlist');"><span class="text_link"><gsmsg:write key="main.man002.24" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.25" /></span>
        </li>


        <li class="text">
          <img src="../main/images/icon_syain.gif" class="img_border" alt="<gsmsg:write key="main.memberships.conf" />">
          <a href="#" onClick="return buttonPush('memshipconf');"><span class="text_link"><gsmsg:write key="main.memberships.conf" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.expimp.memberships" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_syain.gif" class="img_border" alt="<gsmsg:write key="main.man002.26" />">
          <a href="#" onClick="return buttonPush('poslist');"><span class="text_link"><gsmsg:write key="main.man002.26" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.27" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_holiday.gif" class="img_border" alt="<gsmsg:write key="main.holiday.setting" />">
          <a href="#" onClick="return buttonPush('holidaylist');"><span class="text_link"><gsmsg:write key="main.holiday.setting" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.29" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_proxy.gif" class="img_border" alt="<gsmsg:write key="main.man002.30" />">
          <a href="#" onClick="return buttonPush('pxyconf');"><span class="text_link"><gsmsg:write key="main.man002.30" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.31" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_temp.gif" class="img_border" alt="<gsmsg:write key="main.man002.32" />">
          <a href="#" onClick="return buttonPush('fileConf');"><span class="text_link"><gsmsg:write key="main.man002.32" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.33" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_batch_01.gif" class="img_border" alt="<gsmsg:write key="main.man002.34" />">
          <a href="#" onClick="return buttonPush('jobschedule');"><span class="text_link"><gsmsg:write key="main.man002.34" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.35" /></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_syain.gif" class="img_border" alt="<gsmsg:write key="main.man500.2" />">
          <a href="#" onClick="return buttonPush('pconfedit');"><span class="text_link"><gsmsg:write key="main.man500.2"/></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.76"/></span>
        </li>

        <li class="text">
          <img src="../main/images/icon_syain.gif" class="img_border" alt="<gsmsg:write key="main.grp.usr.sort.setting" />">
          <a href="#" onClick="return buttonPush('sortConfig');"><span class="text_link"><gsmsg:write key="main.grp.usr.sort.setting" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.37" /></span>
        </li>

        <li class="text">
          <img src="../portal/images/menu_icon_single.gif" class="img_border" alt="<gsmsg:write key="main.layout.setting" />">
          <a href="#" onClick="return buttonPush('layoutConfig');"><span class="text_link"><gsmsg:write key="main.layout.setting" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.73" /></span>
        </li>

      </dd>
      </dl>

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.38" />"><gsmsg:write key="main.man002.38" /></dt>
      <dd>
        <logic:equal name="man002Form" property="changePassword" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.CHANGEPASSWORD_PARMIT) %>">
        <li class="text">
          <img src="../main/images/icon_passward.gif" class="img_border" alt="<gsmsg:write key="main.man002.39" />">
          <a href="#" onClick="return buttonPush('pswdConf');"><span class="text_link"><gsmsg:write key="main.man002.39" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.40" /></span>
        </li>
        </logic:equal>
        <li class="text">
          <img src="../main/images/icon_session.gif" class="img_border" alt="<gsmsg:write key="main.man002.41" />">
          <a href="#" onClick="return buttonPush('sessiontime');"><span class="text_link"><gsmsg:write key="main.man002.41" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.42" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_loginconf.gif" class="img_border" alt="<gsmsg:write key="main.login.setting" />">
          <a href="#" onClick="return buttonPush('loginConf');"><span class="text_link"><gsmsg:write key="main.login.setting" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.44" /></span>
        </li>

      </dd>
      </dl>


<logic:equal name="man002Form" property="tempFileHozonKbn" value="0">
      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.45" />"><gsmsg:write key="main.man002.45" /></dt>
      <dd>
        <li class="text">
          <img src="../main/images/icon_backup01.gif" class="img_border" alt="<gsmsg:write key="cmn.autobackup.setting" />">
          <a href="#" onClick="return buttonPush('backupconf');"><span class="text_link"><gsmsg:write key="cmn.autobackup.setting" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.47" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_backup02.gif" class="img_border" alt="<gsmsg:write key="cmn.manual.backup" />">
          <a href="#" onClick="return buttonPush('manualbackup');"><span class="text_link"><gsmsg:write key="cmn.manual.backup" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.49" /></span>
        </li>

      </dd>
      </dl>
</logic:equal>

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.45" />"><gsmsg:write key="main.man002.50" /></dt>
      <dd>
        <li class="text">
          <img src="../main/images/icon_log.gif" class="img_border" alt="<gsmsg:write key="main.man002.51" />">
          <a href="#" onClick="return buttonPush('oplogConf');"><span class="text_link"><gsmsg:write key="main.man002.51" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.52" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_log.gif" class="img_border" class="img_border" alt="<gsmsg:write key="main.man002.53" />">
          <a href="#" onClick="return buttonPush('oplogSearch');"><span class="text_link"><gsmsg:write key="main.man002.53" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.54" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_login01.gif" class="img_border" alt="<gsmsg:write key="man.autodelete.opelog" />">
          <a href="#" onClick="return buttonPush('oplogAutoDel');"><span class="text_link"><gsmsg:write key="man.autodelete.opelog" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.56" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_login02.gif" class="img_border" alt="<gsmsg:write key="main.manualdelete.operation.log" />">
          <a href="#" onClick="return buttonPush('oplogSyudoDel');"><span class="text_link"><gsmsg:write key="main.manualdelete.operation.log" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.74" /></span>
        </li>
        <logic:equal name="man002Form" property="dbKbn" value="0">
        <li class="text">
          <img src="../main/images/icon_log.gif" class="img_border" alt="<gsmsg:write key="main.man002.57" />">
          <a href="#" onClick="return buttonPush('loglist');"><span class="text_link"><gsmsg:write key="main.man002.57" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.58" /></span>
        </li>
        </logic:equal>
        <li class="text">
          <img src="../main/images/icon_login02.gif" class="img_border" alt="<gsmsg:write key="user.usr090.2" />">
          <a href="#" onClick="return buttonPush('beLogTime');"><span class="text_link"><gsmsg:write key="user.usr090.2" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.dsp.last.login.time" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_login01.gif" class="img_border" alt="<gsmsg:write key="main.autodelete.login.history" />">
          <a href="#" onClick="return buttonPush('lhisAutoDel');"><span class="text_link"><gsmsg:write key="main.autodelete.login.history" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.60" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_login02.gif" class="img_border" alt="<gsmsg:write key="main.manualdelete.login.history" />">
          <a href="#" onClick="return buttonPush('lhisSyudoDel');"><span class="text_link"><gsmsg:write key="main.manualdelete.login.history" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.62" /></span>
        </li>
        <li class="text">
          <img src="../main/images/icon_login_history.gif" class="img_border" alt="<gsmsg:write key="main.login.history.statistical.info" />">
          <a href="#" onClick="return buttonPush('manLogCount');"><span class="text_link"><gsmsg:write key="main.login.history.statistical.info" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.75" /></span>
        </li>

      </dd>
      </dl>

      <logic:equal name="man002Form" property="dbKbn" value="0">
      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.63" />"><gsmsg:write key="main.man002.63" /></dt>
      <dd>
        <li class="text">
          <img src="../main/images/icon_disc.gif" class="img_border" alt="<gsmsg:write key="main.man002.64" />">
          <a href="#" onClick="return buttonPush('diskconf');"><span class="text_link"><gsmsg:write key="main.man002.64" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.65" /></span>
        </li>
      </dd>
      </dl>
      </logic:equal>

      <dl class="decorate_textbox2">
      <dt><img src="../main/images/gs_icon.gif" border="0" alt="<gsmsg:write key="main.man002.66" />"><gsmsg:write key="main.man002.66" /></dt>
      <dd>
        <li class="text">
          <img src="../main/images/icon_proxy.gif" class="img_border" alt="<gsmsg:write key="main.man002.67" />">
          <a href="#" onClick="return buttonPush('system');"><span class="text_link"><gsmsg:write key="main.man002.67" /></span></a>
          <br><span class="text_indent_22">・・・<gsmsg:write key="main.man002.68" /></span>
        </li>

      </dd>
      </dl>

      <logic:equal name="kusr" property="usrsid" value="0">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('password');"><img src="../main/images/icon_batch.gif" class="img_border" alt="<gsmsg:write key="cmn.change.password" />"><span class="text_link"><gsmsg:write key="cmn.change.password" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="main.man002.70" /></li></div></dd>
      </dl>
      </logic:equal>


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