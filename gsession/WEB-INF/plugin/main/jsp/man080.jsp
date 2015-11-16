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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man080.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="cmn.autobackup.setting" /></title>
</head>
<body class="body_03" onload="setDisabled();">
<!--　BODY -->

<html:form action="/main/man080">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="man080backupFile" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.autobackup.setting" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt="<gsmsg:write key="cmn.autobackup.setting" />"></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
          <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backadmconf');">
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt="<gsmsg:write key="cmn.autobackup.setting" />"></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">


    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man080.1" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <span class="text_r1"><gsmsg:write key="main.man080.2" /></span>
      <br>
      <bean:define id="man080batTime" name="man080Form" property="man080batchHour" type="java.lang.String" />
      <span class="text_base"><gsmsg:write key="main.man080.3" arg0="<%= man080batTime %>" /></span>
      <br>
      <br><html:radio styleId="noset" name="man080Form" property="man080Interval" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_NOSET) %>" onclick="setDisabled();" /><label for="noset"><gsmsg:write key="cmn.noset" /></label>
      <br><html:radio styleId="day" name="man080Form" property="man080Interval" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_DAILY) %>" onclick="setDisabled();" /><label for="day"><gsmsg:write key="cmn.everyday" /></label>

      <br><html:radio styleId="week" name="man080Form" property="man080Interval" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_WEEKLY) %>" onclick="setDisabled();" /><label for="week"><gsmsg:write key="cmn.weekly2" /></label>&nbsp;&nbsp;
      <html:select name="man080Form" property="man080dow">
      <html:optionsCollection name="man080Form" property="dowList" />
      </html:select>

      <br><html:radio styleId="month" name="man080Form" property="man080Interval" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.BUCCONF_INTERVAL_MONTHLY) %>" onclick="setDisabled();" /><label for="month"><gsmsg:write key="cmn.monthly.2" /></label>&nbsp;&nbsp;
      <html:select name="man080Form" property="man080weekmonth">
      <html:optionsCollection name="man080Form" property="weekmonthList" />
      </html:select>
      <html:select name="man080Form" property="man080monthdow">
      <html:optionsCollection name="man080Form" property="dowList" />
      </html:select>

    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="man.number.generations" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <span class="text_r1"><gsmsg:write key="main.man080.5" /></span>
      <br><br>
      <html:select name="man080Form" property="man080generation">
      <html:optionsCollection name="man080Form" property="generationList" />
      </html:select>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.output" /></span></td>
    <td align="left" class="td_wt2" width="100%">
      <html:radio name="man080Form" property="man080zipOutputKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.ZIP_BACKUP_FLG_OFF) %>" styleId="man080notCompress" /><label for="man080notCompress"><gsmsg:write key="main.not.compress" /></label>
      <html:radio name="man080Form" property="man080zipOutputKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.ZIP_BACKUP_FLG_ON) %>" styleId="man080zipOutput" /><label for="man080zipOutput"><gsmsg:write key="main.zip.format.output" /></label>
    </td>
    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <th align="center" class="table_bg_7D91BD" width="60%" nowrap><span class="text_tlw"><gsmsg:write key="cmn.backupfile" /></span></th>
      <th align="center" class="table_bg_7D91BD" width="25%" nowrap><span class="text_tlw"><gsmsg:write key="man.creation.date" /></th>
      <th align="center" class="table_bg_7D91BD" width="15%" nowrap><span class="text_tlw"><gsmsg:write key="main.man080.7" /></span></th>
    </tr>

    <logic:notEmpty name="man080Form" property="fileDataList">
    <% String[] tdClass = {"td_type1", "td_type29"}; %>

    <logic:iterate id="fileData" name="man080Form" property="fileDataList" indexId="idx">

    <tr>
    <td align="left" class="<%= tdClass[idx.intValue() % 2] %>">
    <logic:equal name="fileData" property="zipOutput" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.ZIP_BACKUP_FLG_ON) %>" >
      <a href="javascript:void(0)" onclick="return clickFile('<bean:write name="fileData" property="hashFileName" />')"><span class="text_link"><bean:write name="fileData" property="fileName" /></span></a>
    </logic:equal>
    <logic:equal name="fileData" property="zipOutput" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.ZIP_BACKUP_FLG_OFF) %>" >
      <bean:write name="fileData" property="fileName" />
    </logic:equal>
    </td>
    <td align="center" class="<%= tdClass[idx.intValue() % 2] %>"><bean:write name="fileData" property="strMakeDate" /></td>
    <td align="right" class="<%= tdClass[idx.intValue() % 2] %>"><bean:write name="fileData" property="fileSize" /></td>
    </tr>

    </logic:iterate>

    </logic:notEmpty>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('confirm');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backadmconf');">
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