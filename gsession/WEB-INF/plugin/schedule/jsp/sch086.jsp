<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConstSchedule" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="schedule.108" /> [<gsmsg:write key="schedule.sch091.1" />]</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../schedule/js/sch086.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="changeEnableDisable(); changeEnableDisablePublic(); changeEnableDisableSame();">
<html:form action="/schedule/sch086">

<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="sch010DspDate" />
<html:hidden property="changeDateFlg" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />
<html:hidden property="sch010SelectDate" />
<html:hidden property="sch010SchSid" />
<html:hidden property="sch010DspGpSid" />
<html:hidden property="sch010searchWord" />
<html:hidden property="sch020SelectUsrSid" />
<html:hidden property="sch030FromHour" />

<html:hidden property="sch100PageNum" />
<html:hidden property="sch100Slt_page1" />
<html:hidden property="sch100Slt_page2" />
<html:hidden property="sch100OrderKey1" />
<html:hidden property="sch100SortKey1" />
<html:hidden property="sch100OrderKey2" />
<html:hidden property="sch100SortKey2" />

<html:hidden property="sch100SvSltGroup" />
<html:hidden property="sch100SvSltUser" />
<html:hidden property="sch100SvSltStartYearFr" />
<html:hidden property="sch100SvSltStartMonthFr" />
<html:hidden property="sch100SvSltStartDayFr" />
<html:hidden property="sch100SvSltStartYearTo" />
<html:hidden property="sch100SvSltStartMonthTo" />
<html:hidden property="sch100SvSltStartDayTo" />
<html:hidden property="sch100SvSltEndYearFr" />
<html:hidden property="sch100SvSltEndMonthFr" />
<html:hidden property="sch100SvSltEndDayFr" />
<html:hidden property="sch100SvSltEndYearTo" />
<html:hidden property="sch100SvSltEndMonthTo" />
<html:hidden property="sch100SvSltEndDayTo" />
<html:hidden property="sch100SvKeyWordkbn" />
<html:hidden property="sch100SvKeyValue" />
<html:hidden property="sch100SvOrderKey1" />
<html:hidden property="sch100SvSortKey1" />
<html:hidden property="sch100SvOrderKey2" />
<html:hidden property="sch100SvSortKey2" />

<html:hidden property="sch100SltGroup" />
<html:hidden property="sch100SltUser" />
<html:hidden property="sch100SltStartYearFr" />
<html:hidden property="sch100SltStartMonthFr" />
<html:hidden property="sch100SltStartDayFr" />
<html:hidden property="sch100SltStartYearTo" />
<html:hidden property="sch100SltStartMonthTo" />
<html:hidden property="sch100SltStartDayTo" />
<html:hidden property="sch100SltEndYearFr" />
<html:hidden property="sch100SltEndMonthFr" />
<html:hidden property="sch100SltEndDayFr" />
<html:hidden property="sch100SltEndYearTo" />
<html:hidden property="sch100SltEndMonthTo" />
<html:hidden property="sch100SltEndDayTo" />
<html:hidden property="sch100KeyWordkbn" />
<logic:notEmpty name="sch086Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch086Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch086Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch086Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch086Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch086Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch086Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch086Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch086Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch086Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="sch086init" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="70%" class="tl0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.sch091.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('sch086Ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <!-- 編集権限 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="schEditArea1" nowrap rowspan="2">
      <span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="sch086Form" styleId="sch086EditType_01" property="sch086EditType" value="<%= String.valueOf(GSConstSchedule.SAD_INIEDIT_STYPE_ADM) %>" onclick="changeEnableDisable();" /><label for="sch086EditType_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="sch086Form" styleId="sch086EditType_02" property="sch086EditType" value="<%= String.valueOf(GSConstSchedule.SAD_INIEDIT_STYPE_USER) %>" onclick="changeEnableDisable();" /><label for="sch086EditType_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
    </td>
    </tr>

    <tr>
      <td valign="middle" align="left" class="td_wt" id="schEditArea2">
      <html:radio name="sch086Form" property="sch086Edit" styleId="sch086Edit0" value="<%= String.valueOf(GSConstSchedule.EDIT_CONF_NONE) %>" /><span class="text_base"><label for="sch086Edit0"><gsmsg:write key="cmn.nolimit" /></label></span>&nbsp;
      <html:radio name="sch086Form" property="sch086Edit" styleId="sch086Edit1" value="<%= String.valueOf(GSConstSchedule.EDIT_CONF_OWN) %>" /><span class="text_base"><label for="sch086Edit1"><gsmsg:write key="cmn.only.principal.or.registant" /></label>&nbsp;</span>
      <html:radio name="sch086Form" property="sch086Edit" styleId="sch086Edit2" value="<%= String.valueOf(GSConstSchedule.EDIT_CONF_GROUP) %>" /><span class="text_base"><label for="sch086Edit2"><gsmsg:write key="cmn.only.affiliation.group.membership" /></label>&nbsp;</span>
    </td>
    </tr>


    <!-- 公開区分 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="schPublicArea1" nowrap rowspan="2">
      <span class="text_bb1"><gsmsg:write key="cmn.public" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="sch086Form" styleId="sch086PublicType_01" property="sch086PublicType" value="<%= String.valueOf(GSConstSchedule.SAD_INIPUBLIC_STYPE_ADM) %>" onclick="changeEnableDisablePublic();" /><label for="sch086PublicType_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="sch086Form" styleId="sch086PublicType_02" property="sch086PublicType" value="<%= String.valueOf(GSConstSchedule.SAD_INIPUBLIC_STYPE_USER) %>" onclick="changeEnableDisablePublic();" /><label for="sch086PublicType_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" id="schPublicArea2">
     <html:radio name="sch086Form" property="sch086Public" styleId="sch086public0" value="<%= String.valueOf(GSConstSchedule.DSP_PUBLIC) %>" /><span class="text_base"><label for="sch086public0"><gsmsg:write key="cmn.public" /></label></span>&nbsp;
     <html:radio name="sch086Form" property="sch086Public" styleId="sch086public1" value="<%= String.valueOf(GSConstSchedule.DSP_NOT_PUBLIC) %>" /><span class="text_base"><label for="sch086public1"><gsmsg:write key="cmn.private" /></label>&nbsp;</span>
     <html:radio name="sch086Form" property="sch086Public" styleId="sch086public2" value="<%= String.valueOf(GSConstSchedule.DSP_YOTEIARI) %>" /><span class="text_base"><label for="sch086public2"><gsmsg:write key="schedule.5" /></label>&nbsp;</span>
     <html:radio name="sch086Form" property="sch086Public" styleId="sch086public3" value="<%= String.valueOf(GSConstSchedule.DSP_BELONG_GROUP) %>" /><span class="text_base"><label for="sch086public3"><gsmsg:write key="schedule.28" /></label>&nbsp;</span>
    </td>
    </tr>


    <!-- 同時修正 -->
    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" width="20%" id="schSameArea1" nowrap rowspan="2">
      <span class="text_bb1"><gsmsg:write key="schedule.32" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <html:radio name="sch086Form" styleId="sch086SameType_01" property="sch086SameType" value="<%= String.valueOf(GSConstSchedule.SAD_INISAME_STYPE_ADM) %>" onclick="changeEnableDisableSame();" /><label for="sch086SameType_01"><span class="text_base"><gsmsg:write key="cmn.set.the.admin" /></span></label>&nbsp;
      <html:radio name="sch086Form" styleId="sch086SameType_02" property="sch086SameType" value="<%= String.valueOf(GSConstSchedule.SAD_INISAME_STYPE_USER) %>" onclick="changeEnableDisableSame();" /><label for="sch086SameType_02"><span class="text_base"><gsmsg:write key="cmn.set.eachuser" /></span></label>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_wt" id="schSameArea2">
     <html:radio name="sch086Form" property="sch086Same" styleId="sch086Same0" value="<%= String.valueOf(GSConstSchedule.SAME_EDIT_ON) %>" /><span class="text_base"><label for="sch086Same0"><gsmsg:write key="schedule.34" /></label></span>&nbsp;
     <html:radio name="sch086Form" property="sch086Same" styleId="sch086Same1" value="<%= String.valueOf(GSConstSchedule.SAME_EDIT_OFF) %>" /><span class="text_base"><label for="sch086Same1"><gsmsg:write key="schedule.33" /></label></span>
    </td>
    </tr>


    </table>

  </td>
  </tr>

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td>
      <table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top:10px;">
      <tr>
      <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('sch086Ok');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('back');">
      </td>
      </tr>
      </table>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  </table>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>