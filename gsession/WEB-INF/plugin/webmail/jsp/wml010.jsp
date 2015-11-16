<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.wml.wml010.Wml010Const" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
  <title>[GroupSession]<gsmsg:write key="wml.wml010.25" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <style type="text/css">
    body {
        visibility: hidden;
    }
  </style>

  <theme:css filename="theme.css"/>
  <link rel="stylesheet" type="text/css" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" />
  <link rel="stylesheet" type="text/css" href="../webmail/css/search.css?<%= GSConst.VERSION_PARAM %>" />
  <link rel="stylesheet" type="text/css" href="../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>" />
  <link rel="stylesheet" type="text/css" href="../webmail/css/reset-fonts_webmail.css?<%= GSConst.VERSION_PARAM %>">

  <logic:notEmpty name="wml010Form" property="wml010theme">
    <bean:define id="themeName" name="wml010Form" property="wml010theme" type="java.lang.String" />
    <link rel="stylesheet" type="text/css" href="../webmail/css/theme/<%= themeName %>.css?<%= GSConst.VERSION_PARAM %>" />
  </logic:notEmpty>

  <link rel="stylesheet" type="text/css" href="../common/js/yui/fonts/fonts-min.css?<%= GSConst.VERSION_PARAM %>" />
  <link rel="stylesheet" type="text/css" href="../common/js/yui/tabview/assets/skins/sam/tabview.css?<%= GSConst.VERSION_PARAM %>" />
  <link rel="stylesheet" type="text/css" href="../common/js/yui-accordion/accordionview/assets/skins/sam/reset-fonts.css?<%= GSConst.VERSION_PARAM %>">
  <link rel="stylesheet" type="text/css" href="../common/js/yui-accordion/accordionview/assets/skins/sam/accordionview.css?<%= GSConst.VERSION_PARAM %>">

  <gsjsmsg:js filename="gsjsmsg.js"/>
  <script type="text/javascript" src="../common/js/yui/yahoo/yahoo-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/yui/yahoo-dom-event/yahoo-dom-event.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/yui/container/container-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/yui/element/element-beta-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/yui/tabview/tabview-min.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/yui-accordion/accordionview/utilities.js?<%= GSConst.VERSION_PARAM %>"></script>

  <script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml010.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/assets/editorSubWindow.js?<%= GSConst.VERSION_PARAM %>"></script>

  <script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

  <script type="text/javascript" src="../portal/js/tiny_mce/tiny_mce.js?ae<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="webmail_body yui-skin-sam" onunload="wmlEditorWindowClose();">
<span id="id_wml010Body">

<html:form action="/webmail/wml010" styleId="webmailForm" onsubmit="javascript:return false;">

<input type="hidden" name="CMD" value="">

<!-- ヘルプパラメータの設定 -->
<input type="hidden" name="helpPrm" value="0">

<input type="hidden" name="wmlAccountMode" value="0">
<input type="hidden" name="detailSearchFlg" value="1">
<input type="hidden" name="wml010downloadFileId" value="">
<input type="hidden" name="wml010svSendContent" value="">
<input type="hidden" name="wml010sendMessageNum" value="">
<input type="hidden" name="wml010sendMailType" value="">

<html:hidden property="wml010pluginAddressUse" />
<html:hidden property="wml010pluginUserUse" />
<html:hidden property="wml010pluginCircularUse" />
<html:hidden property="wml010pluginSmailUse" />
<html:hidden property="wml010pluginFilekanriUse" />

<html:hidden property="wml010viewDirectory" />
<html:hidden property="wml010viewDirectoryType" />
<html:hidden property="wml010viewLabel" />
<input type="hidden" name="wml010viewDelMail" value="0">
<html:hidden property="wml010viewAccountAddress" />
<html:hidden property="wml010selectPage" />
<html:hidden property="wml010sortKey" />
<html:hidden property="wml010order" />
<html:hidden property="wml010searchType" />
<html:hidden property="wml010searchSortKey" />
<html:hidden property="wml010searchOrder" />
<html:hidden property="wml010checkAddress" />
<html:hidden property="wml010checkFile" />

<html:hidden property="wml010svSearchKeywordNml" />
<html:hidden property="wml010svSearchFrom" />
<html:hidden property="wml010svSearchTo" />
<html:hidden property="wml010svSearchToKbnTo" />
<html:hidden property="wml010svSearchToKbnCc" />
<html:hidden property="wml010svSearchToKbnBcc" />
<html:hidden property="wml010svSearchDateType" />
<html:hidden property="wml010svSearchDateYearFr" />
<html:hidden property="wml010svSearchDateMonthFr" />
<html:hidden property="wml010svSearchDateDayFr" />
<html:hidden property="wml010svSearchDateYearTo" />
<html:hidden property="wml010svSearchDateMonthTo" />
<html:hidden property="wml010svSearchDateDayTo" />
<html:hidden property="wml010svSearchTempFile" />
<html:hidden property="wml010svSearchKeywordKbn" />
<html:hidden property="wml010svSearchKeyword" />
<html:hidden property="wml010svSearchReadKbn" />

<input type="hidden" name="wml010sendSignOld" value="0">
<input type="hidden" name="wml010shareMailNum" value="">
<html:hidden property="wml010maxBodySize" styleId="wmlBodyLimit" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" border="0" width="99%" align="center">
  <tr style="font-size:120%;">
    <td width="0%">
    <img src="../webmail/images/header_webmail.gif" border="0" alt="<gsmsg:write key="wml.wml010.25" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="wml.wml010.25" /></span></td>
    <td width="0%" class="header_white_bg_text">&nbsp;</td>
    <td width="100%" class="header_white_bg mailSearch">
      <img alt="<gsmsg:write key="cmn.search" />" src="../common/images/search.gif"/>
      <html:text name="wml010Form" property="wml010searchKeywordNml" maxlength="100" />&nbsp;<input type="button" class="btn_base1" onClick="return searchResultLoadInit(<%= String.valueOf(Wml010Const.SEARCHTYPE_KEYWORD) %>);" value="<gsmsg:write key="wml.wml010.18" />">
      &nbsp;<button class="btn_base0" onClick="return viewSearchDetail();"><gsmsg:write key="cmn.detail" /></button>
    </td>
    <td width="0%" class="header_white_bg">
      <input type="button" name="btn_ktools" class="btn_base1_7" value="<gsmsg:write key="wml.100" />" onClick="moveAccountConf();">
      <logic:equal name="wml010Form" property="wml010adminUser" value="true">
        <input type="button" name="btn_ktools" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('admTool');">
      </logic:equal>
      <input type="button" name="btn_ptools" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('psnTool');">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="wml.wml010.25" />"></td>
  </tr>

</table>

<div id="warnDiskArea" style="display: none; text-align:center; border: solid 2px; border-color: #CC3333!important; width: 98%; padding: 10px 0px; margin: 10px 0px 5px 5px;">
<table align="center">
<tr>
<td>
<img src="../common/images/warn2.gif" border="0" alt="<gsmsg:write key="cmn.warning" />">
</td>
<td class="text_error">
<gsmsg:write key="wml.251" /><span id="warnDiskRatio"></span><gsmsg:write key="wml.252" />
</td>
</tr>
</table>
</div>

<div id="top_detailSearch">

  <br>

  <table id="detailSearchCondition" class="searchCondition" cellpadding="0" cellspacing="0" width="99%">

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1">From</span></td>
    <td align="left" class="webmail_td2" width="40%">&nbsp;<html:text name="wml010Form" property="wml010searchFrom" style="width:80%" /></td>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml010.01" /></span></td>
    <td align="left" class="webmail_td2" width="40%" nowrap>
    &nbsp;<html:radio name="wml010Form" property="wml010searchReadKbn" styleId="searchReadKbn0" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_NOSET) %>" />&nbsp;<label for="searchReadKbn0"><gsmsg:write key="cmn.not.specified" /></label>
    &nbsp;&nbsp;<html:radio name="wml010Form" property="wml010searchReadKbn" styleId="searchReadKbn1" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_NOREAD) %>" />&nbsp;<label for="searchReadKbn1"><gsmsg:write key="cmn.read.yet" /></label>
    &nbsp;&nbsp;<html:radio name="wml010Form" property="wml010searchReadKbn" styleId="searchReadKbn2" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_READED) %>" />&nbsp;<label for="searchReadKbn2"><gsmsg:write key="cmn.read.already" /></label>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="wml.send.dest" /></span></td>
    <td align="left" class="webmail_td2" width="40%" style="padding: 2px;">
    &nbsp;<html:text name="wml010Form" property="wml010searchTo" style="width:80%" />
    <div style="padding-top: 3px;">
    &nbsp;<html:checkbox property="wml010searchToKbnTo" styleId="wml010destTo" value="1" />&nbsp;<label for="wml010destTo"><gsmsg:write key="cmn.from" /></label>
    &nbsp;&nbsp;<html:checkbox property="wml010searchToKbnCc" styleId="wml010destCc" value="1" />&nbsp;<label for="wml010destCc">CC</label>
    &nbsp;&nbsp;<html:checkbox property="wml010searchToKbnBcc" styleId="wml010destBcc" value="1" />&nbsp;<label for="wml010destBcc">BCC</label>
    </div>
    </td>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attach.file" /></span></td>
    <td align="left" class="webmail_td2" width="40%" style="padding:5px;!important"><html:checkbox name="wml010Form" property="wml010searchTempFile" styleId="searchTempFile" value="1" /><label for="searchTempFile"><gsmsg:write key="wml.wml010.06" /></label></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.date2" /></span></td>
    <td align="left" class="webmail_td2" width="90%" colspan="3" nowrap style="padding:5px;!important">
    <html:radio name="wml010Form" property="wml010searchDateType" styleId="searchDate0" value="<%= String.valueOf(Wml010Const.SEARCH_DATE_NOSET) %>" onclick="changeSearchDateType();" />&nbsp;<label for="searchDate0"><gsmsg:write key="cmn.not.specified" /></label>
    &nbsp;&nbsp;<html:radio name="wml010Form" property="wml010searchDateType" styleId="searchDate1" value="<%= String.valueOf(Wml010Const.SEARCH_DATE_SET) %>" onclick="changeSearchDateType();" />&nbsp;<label for="searchDate1"><gsmsg:write key="wml.wml010.12" /></label>

    <span id="searchDateArea">
    &nbsp;&nbsp;&nbsp;From:&nbsp;&nbsp;
    <html:select property="wml010searchDateYearFr" styleId="wml010srhYearFr" style="vertical-align:middle;">
    <html:optionsCollection name="wml010Form" property="yearCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.year2" />&nbsp;
    <html:select property="wml010searchDateMonthFr" styleId="wml010srhMonthFr" style="vertical-align:middle;">
    <html:optionsCollection name="wml010Form" property="monthCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.month" />&nbsp;
    <html:select property="wml010searchDateDayFr" styleId="wml010srhDayFr" style="vertical-align:middle;">
    <html:optionsCollection name="wml010Form" property="dayCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.day" />&nbsp;
    <input type="button" name="wml010srhDateFrBtn" value="Cal" onClick="wrtCalendarByBtn($('#wml010srhDayFr')[0], $('#wml010srhMonthFr')[0], $('#wml010srhYearFr')[0], 'wml010_1');" class="calendar_btn" style="vertical-align:middle;", id="wml010wrtCalendarByBtn(_1">

    &nbsp;&nbsp;&nbsp;To　:&nbsp;&nbsp;
    <html:select name="wml010Form" property="wml010searchDateYearTo" styleId="wml010srhYearTo" style="vertical-align:middle;">
    <html:optionsCollection name="wml010Form" property="yearCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.year2" />&nbsp;
    <html:select name="wml010Form" property="wml010searchDateMonthTo" styleId="wml010srhMonthTo" style="vertical-align:middle;">
    <html:optionsCollection name="wml010Form" property="monthCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.month" />&nbsp;
    <html:select name="wml010Form" property="wml010searchDateDayTo" styleId="wml010srhDayTo" style="vertical-align:middle;">
    <html:optionsCollection name="wml010Form" property="dayCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.day" />&nbsp;
    <input type="button" name="wml010srhDateToBtn" value="Cal" onClick="wrtCalendarByBtn($('#wml010srhDayTo')[0], $('#wml010srhMonthTo')[0], $('#wml010srhYearTo')[0], 'wml010_2');" class="calendar_btn" style="vertical-align:middle;", id="wml010_2">
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.keyword" /></span></td>
    <td align="left" class="webmail_td2" width="90%" colspan="3">&nbsp;
      <html:select name="wml010Form" property="wml010searchKeywordKbn" style="width:180px" styleId="wmlSearchKeywordKbn">
      <html:optionsCollection name="wml010Form" property="keywordCombo" value="value" label="label" />
      </html:select>
    &nbsp;<html:text name="wml010Form" property="wml010searchKeyword" maxlength="100" style="width:60%" />
    </td>
    </tr>

    <tr>
    <td align="center" colspan="4">
    <br>
    <input type="button" name="detailSearchBtn" value="<gsmsg:write key="cmn.search" />" class="btn_search_n1" onClick="return searchResultLoadInit(<%= String.valueOf(Wml010Const.SEARCHTYPE_DETAIL) %>);">
    <input type="button" name="detailSearchBtn" value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onClick="return viewSearchDetail();">
    <br>&nbsp;
    </td>
    </tr>

  </table>

</div>

<span id="webmailErrMsg" style="width:100%;"></span>
<img src="../common/images/spacer.gif" height="5" border="0" alt="">

<div id="mailElement" style="background-color:#ffffff!important">

  <table cellpadding="5" cellspacing="0" align="center">
  <tr>
  <td width="205" align="left" valign="top">

    <div align="left" style="margin-left:10px;text-align:left!important;">
    <a href="../webmail/wml010.do" target="_blank" style="color:#3d67ce;text-decoration:underline;font-size:90%;"><b><gsmsg:write key="wml.wml010.24" /></b></a><br>
    <img src="../common/images/spacer.gif" height="5" border="0" alt="">
    </div>

    <ul id="leftmenu">
      <li>
        <h3><gsmsg:write key="wml.102" /></h3>

        <div>
          <div align="center" id="accountList">
            <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
            <html:select name="wml010Form" property="wmlViewAccount" style="width:200px" onchange="changeAccount();">
            <html:optionsCollection name="wml010Form" property="accountCombo" value="value" label="label" />
            </html:select>
            <br>
            <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">

            <logic:notEmpty name="wml010Form" property="accountLinkList">
            <table width="100%" cellspacing="5" cellpadding="5">
            <logic:iterate id="accountData" name="wml010Form" property="accountLinkList">
            <tr><td align="left" class="text_bold">
            &nbsp;<img src="../webmail/images/switch_ac_icon.png"></img><a href="#" onClick="return clickNotSelectAccount(<bean:write name="accountData" property="wacSid" />);"><bean:write name="accountData" property="wacName" /></a>
            <logic:notEqual name="accountData" property="notReadCount" value="0">&nbsp;<span style="font-size: 90%">(<bean:write name="accountData" property="notReadCount" />)</span></logic:notEqual>
            </td>
            </tr>
            </logic:iterate>
            </table>
            </logic:notEmpty>
            <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
          </div>

          <div id="folder_top">
            <div class="wrap">
              <div id="check_buttons"></div>
            </div>
          </div>
          <div id="folder_list" style="width: 205px;">
            <div class="wrap">
              <div id="treewrapper">
                <div id="treediv"></div>
              </div>
            </div>
          </div>
        </div>
      </li>

      <li>
        <h3><gsmsg:write key="cmn.shain.info" /></h3>
        <div>
          <div id="shain_list" style="width: 205px; background-color: #ffffff!important">
            <div class="wrap">
              <span class="address_search opening_hidden" style="display:none;">
              <input type="button" class="display_hide" name="fakeButton" onClick="return redrawShainList();">
              <html:select property="wml010shainGroup" style="width:160px;" onchange="redrawShainList();">
              <html:optionsCollection name="wml010Form" property="shainGroupCombo" value="value" label="label" />
              </html:select>
              <input type="button" onclick="openGroupWindow(this.form.wml010shainGroup, 'wml010shainGroup', '0', 'changeGrp', '1', 'fakeButton')" class="group_btn2" value="&nbsp;&nbsp;" id="wml010GroupBtn">
              </span>
              <div id="popular_shain" valign="top"></div>
                <ul class="opening_hidden" style="display:none;" id="wml010ShainList"></ul>
              </div>
            </div>
          </div>
        </div>
      </li>

      <li>
        <h3><gsmsg:write key="addressbook" /></h3>
        <div>
          <div id="address_list" style="width: 205px; background-color: #ffffff!important">
            <div class="wrap">
              <span class="address_search opening_hidden" style="display:none;">
              <div style="width:185px;" align="center">
              <html:radio name="wml010Form" property="wml010addressType" value="1" styleId="adrType0" onclick="redrawAddressList()" /><label for="adrType0"><gsmsg:write key="cmn.all" /></label>&nbsp;&nbsp;
              <html:radio name="wml010Form" property="wml010addressType" value="0" styleId="adrType1" onclick="redrawAddressList()" /><label for="adrType1"><gsmsg:write key="wml.wml010.07" /></label>
              </div>
              <input type="text" style="width:100px;" name="wml010searchTextAddressList">&nbsp;<input class="btn_base0" type="button" value="<gsmsg:write key="cmn.search" />" onClick="searchAddressExe();">
              </span>
              <div id="popular_address" valign="top"></div>
                <ul class="opening_hidden" style="display:none;" id="wml010AddressList"></ul>
              </div>
            </div>
          </div>
        </div>
      </li>

      <li>
        <h3><gsmsg:write key="wml.262" /></h3>
        <div>
          <div id="destlist_list" style="width: 205px; background-color: #ffffff!important">
            <div class="wrap">
              <div id="popular_destlist" valign="top"></div>
                <ul class="opening_hidden" style="display:none;" id="wml010DestList"></ul>
              </div>
            </div>
          </div>
        </div>
      </li>

    </ul>

  </td>

  <td width="10">&nbsp</td>

  <td valign="top">
    <div id="composeViewSearch"></div>

    <div id="mailBox" class="yui-navset">
      <ul class="yui-nav">
        <li class="selected" id="inBox"><a href="#mailBox1"><em><gsmsg:write key="cmn.receive" /></em></a></li>
      </ul>
      <div class="yui-content" id="tabContent">
        <div id="mailBox1"></div>
      </div>
    </div>
    <div style="margin-top: 10px; text-align: right;">
      <a class="page-top" href="#"><gsmsg:write key="cmn.top.back" /></a>
    </div>


  </td>

  </tr>

<tr>
<td colspan="2">&nbsp;</td>
<td>
<div class="diskSizeArea">
<gsmsg:write key="wml.88" />&nbsp;:&nbsp;<span id="useDiskSize"></span>MB<span id="limitDiskArea">&nbsp;&nbsp;/&nbsp;&nbsp;<span id="limitDiskSize"></span>MB&nbsp(<span id="useDiskRatio"></span>%)</span>
</div>


<table>
<tr>
<td class="mailAttendArea">

  <logic:notEqual name="wml010Form" property="wml010maxBodySize" value="0">
  <br>
  <br>
  <bean:define id="mailMaxBodySize" name="wml010Form" property="wml010maxBodySize" type="java.lang.Integer" />
  <gsmsg:write key="wml.wml010.04" arg0="<%= String.valueOf(mailMaxBodySize.intValue()) %>"  arg1="<%= String.valueOf(mailMaxBodySize.intValue()) %>" />
  </logic:notEqual>


  <logic:equal name="wml010Form" property="wml010maxBodySize" value="0">
  <br>
  </logic:equal>

  <span id="autoDeleteArea">
  <br>

  <% String delDirNameArea = "<span id=\"autoDelDirName\"></span>"; %>
  <% String delDirNameArea2 = "<span id=\"autoDelDirName2\"></span>"; %>
  <% String delDateArea = "<span id=\"autoDelDateValue\"></span>"; %>

  <span id="autoDelDate"><gsmsg:write key="cmn.comments" /> <gsmsg:write key="wml.wml010.28" arg0="<%= delDirNameArea %>" arg1="<%= delDateArea %>" /></span>
  <span id="autoDelLogout"><gsmsg:write key="cmn.comments" /> <gsmsg:write key="wml.wml010.19" arg0="<%= delDirNameArea2 %>" /></span>
  </span>

</td>
</tr>
</table>
</td>
</tr>

  </table>

</html:form>

  <!-- START Modal Dialog-->
  <div id='dialog1'>
    <div class='hd'><gsmsg:write key="wml.wml010.16" /></div>
    <div class='bd'>
      <form method='POST' action='../webmail/wml010.do' name="addLabelForm">
        <table summary="">
          <tr>
          <td><input type="radio" name="wml010addLabelType" value="0" id="addLabelType0" checked><label for="addLabelType0"><gsmsg:write key="wml.76" /></label></td>
          <td>
            <select id="dialog_sel" name="wml010addLabel">
              <logic:notEmpty name="wml010Form" property="labelCombo">
              <logic:iterate id="labelData" name="wml010Form" property="labelCombo">
              <option value="<bean:write name="labelData" property="value" />"><bean:write name="labelData" property="label" /></option>
              </logic:iterate>
              </logic:notEmpty>
            </select>
          </td>
          </tr>
          <tr>
          <td><input type="radio" name="wml010addLabelType" id="addLabelType1" value="1"><label for="addLabelType1"><gsmsg:write key="wml.wml010.09" /></label></td>
          <td>
          <input type="text" name="wml010addLabelName" id="dialog_new" maxlength="100" style="width:153px;">
          <span id="addLabelParam"></span>
          </td>
          </tr>
        </table>
      </form>
    </div>
  </div>

  <div id='dialog2'>
    <div class='hd'><gsmsg:write key="wml.wml010.17" /></div>
    <div class='bd'>
      <form method='POST' action='../webmail/wml010.do' name="delLabelForm">
        <table summary="">
          <tr>
          <td><gsmsg:write key="wml.76" /></td>
          <td>
            <select id="dialog_del" name="wml010delLabel">
              <logic:notEmpty name="wml010Form" property="labelCombo">
              <logic:iterate id="labelData" name="wml010Form" property="labelCombo">
              <option value="<bean:write name="labelData" property="value" />"><bean:write name="labelData" property="label" /></option>
              </logic:iterate>
              </logic:notEmpty>
            </select>
          </td>
          </tr>
        </table>
      </form>
    </div>
  </div>

  <div id='dialog3'>
    <div class='hd'><gsmsg:write key="wml.wml010.05" /></div>
    <div class='bd'>
      <form method='POST' action='../webmail/wml010.do' name="readMailForm">
        <table summary="" width="100%">
          <tr>
          <td width="100%" style="text-align:center!important;text-valign:middle!important;"><img src="../webmail/images/ajax-loader.gif"></td>
          </tr>
        </table>
      </form>
    </div>
  </div>

  <div id='dialog4'>
    <div class='hd'><gsmsg:write key="cmn.attach.file" /></div>
    <div class='bd'>
      <form method='POST' action='../webmail/wml010.do' enctype="multipart/form-data" id="sendMailTempForm" name="sendMailTempForm">
        <input type="hidden" id="sendMailTempFormCMD" name="CMD" value="sendFileUpload">
        <table summary="" width="100%">
          <tr>
          <td width="100%">
          <input type="file" id="wml010sendMailFile" name="wml010sendMailFile[0]" size="30" maxlength="50" class="text_base2">
          </td>
          </tr>
          <tr id="wml010DragArea">
          <td>
            <br>
            <div id="uploadArea" draggable="true" class="fileDropArea" style="height: 100px;">
            <br>
            <br>
            <br>
            <gsmsg:write key="cmn.file.droparea.message" />
            </div>
          </td>
          </tr>
        </table>
      </form>
    </div>
  </div>

  <div id='dialog5'>
    <div class='hd'><gsmsg:write key="wml.wml010.20" /></div>
    <div class='bd' style="text-align: left; background-color: #ffffff;">
      <form method='POST' action='../webmail/wml010.do' name="moveMailForm">
            <logic:notEmpty name="wml010Form" property="folderCombo">
            <logic:iterate id="folderData" name="wml010Form" property="folderCombo" indexId="idx">
              <% String folderId = "moveFolder_" + String.valueOf(idx.intValue()); %>
              <div style="margin-top: 5px;">
              <input type="radio" name="wml010moveFolder" value="<bean:write name="folderData" property="value" />" id="<%= folderId %>">&nbsp;&nbsp;&nbsp;<label for="<%= folderId %>" style="font-size:100%"><bean:write name="folderData" property="label" /></label><br>
              </div>
            </logic:iterate>
            </logic:notEmpty>
      </form>
    </div>
  </div>

  <div id='dialog6'>
    <div class='hd'><gsmsg:write key="cmn.check" /></div>
    <div class='bd' style="text-align: left; background-color: #ffffff;">
        <iframe id="wml010SendConfirmArea" hspace="0" vspace="0" style="margin:0px; padding:0px; width:100%; height:520px; display:inline!important;" frameborder="no" src=""></iframe>
    </div>
  </div>

  <div id='dialog7'>
    <div class='hd'><gsmsg:write key="anp.anp090.03" /></div>
    <div class='bd' style="text-align: center; background-color: #ffffff;">
        <table summary="" id="sendTemplateList" width="565" class="mailTemplateList">
        <tbody></tbody>
        </table>
        <div id="tooltip_area" align="left"></div>
    </div>
  </div>

  <div id='dialog8'>
    <div class='hd'><gsmsg:write key="cmn.share" /></div>
    <div class='bd' style="text-align: center; background-color: #ffffff;">
        <table summary="" id="wmlShereList" width="280px">
        <tbody>
        <tr id="entrySmailArea">
          <td class="mailList_data1"><img src="../smail/images/menu_icon_single.gif"></td>
          <td class="mailList_data1"><a href="#" onClick="openEntrySmail();"><gsmsg:write key="wml.wml010.31" /></a></td>
        </tr>
        <tr id="entryCircularArea">
          <td class="mailList_data1"><img src="../circular/images/menu_icon_single.gif"></td>
          <td class="mailList_data1"><a href="#" onClick="openEntryCircular();"><gsmsg:write key="wml.wml010.30" /></a></td>
        </tr>
        <tr id="entryFilekanriArea">
          <td class="mailList_data1"><img src="../file/images/menu_icon_single.gif"></td>
          <td class="mailList_data1"><a href="#" onClick="openEntryFilekanri();"><gsmsg:write key="wml.wml010.32" /></a></td>
        </tr>
        </tbody>
        </table>
    </div>
  </div>

  <div id='dialog9'>
    <div class='hd'><gsmsg:write key="cmn.share" /></div>
    <div class='bd' style="text-align: left; background-color: #ffffff;">
        <iframe id="wml010EntryMailArea" hspace="0" vspace="0" style="margin:0px; padding:0px; width:100%; height:700px; display:inline!important;" frameborder="no" src=""></iframe>
    </div>
  </div>

  <div id='dialog10'>
    <div class='hd'><gsmsg:write key="cmn.warning" /></div>
    <div class='bd'>
      <div class="width: 300px; margin-left: 20px">
<span  class="text_bb1">
現在編集中のメールがあるため、<span id="msg_dialog10"></span>できません。<br>
「メール作成」タブを閉じてから再度実行してください。<br>
</span>
      </div>
    </div>
  </div>

</div>


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

<script type="text/javascript" src="../webmail/js/json2.js?<%= GSConst.VERSION_PARAM %>" charset="utf-8"></script>
<script type="text/javascript" src="../common/js/yui/yuiloader/yuiloader-beta-min.js?<%= GSConst.VERSION_PARAM %>" charset="utf-8"></script>
<script type="text/javascript" src="../webmail/js/assets/mailUtil.js?<%= GSConst.VERSION_PARAM %>" charset="utf-8"></script>
<script type="text/javascript" src="../webmail/js/assets/main.js?<%= GSConst.VERSION_PARAM %>" charset="utf-8"></script>
<script type="text/javascript" src="../common/js/yui-accordion/accordionview/accordionview.js?<%= GSConst.VERSION_PARAM %>"></script>

<script type="text/javascript">
  (function() {
    var tabView = new YAHOO.widget.TabView('mailBox');
    var menu1 = new YAHOO.widget.AccordionView('leftmenu', {collapsible: true, expandable: true, expandItem: 0, width: '205px', animate: true, animationSpeed: '0.5'});
  })();

  (function() {

    //関数実行
    $(function(){
      smoothscroll();
    });

    //クリックイベンント
    function smoothscroll (){
      function scroll(sp){
        $((jQuery.support.checkOn && jQuery.support.htmlSerialize && !window.globalStorage != -1) ? document.compatMode == "BackCompat" ? "body" : "html" : "html,body").animate({scrollTop:sp},300);
      }

      //ページのトップへ戻る
      $("a.page-top").click(function(){
        scroll(0);
        return false;
      });
    }
  })();
</script>

</span>

<div id="mailListArea"></div>
</body>
</html:html>