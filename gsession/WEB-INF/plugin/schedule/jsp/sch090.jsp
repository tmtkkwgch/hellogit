<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch060.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="parent.menu.location='../common/cmn003.do';">
<html:form action="/schedule/sch090">
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

<logic:notEmpty name="sch090Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch090Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch090Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch090Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch090Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch090Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch090Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch090Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch090Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch090Form" property="sch100CsvOutField" scope="request">
    <input type="hidden" name="sch100CsvOutField" value="<bean:write name="csvOutField"/>">
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

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.108" />  ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('sch090back');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('iniset');"><span class="text_link"><gsmsg:write key="cmn.default.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.5" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('daydspset');"><span class="text_link"><gsmsg:write key="cmn.show.timezone.days.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.6" /></div></dd>
      </dl>

      <logic:equal name="sch090Form" property="sch090dspEditGroupUser" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.SCC_USER_SHOW) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('gmdspset');"><span class="text_link"><gsmsg:write key="sch.groupmember.view.set" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.1" /></div></dd>
      </dl>
      </logic:equal>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('gsdspset');"><span class="text_link"><gsmsg:write key="schedule.src.83" /><gsmsg:write key="cmn.display.settings" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.9" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('listdspset');"><span class="text_link"><gsmsg:write key="schedule.sch090.4" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.3" /></div></dd>
      </dl>

      <logic:equal name="sch090Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailset');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.2" /></div></dd>
      </dl>
      </logic:equal>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('defaultdspset');"><span class="text_link"><gsmsg:write key="schedule.145" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.146" /></div></dd>
      </dl>

      <logic:equal name="sch090Form" property="sch090canEditRepertKbn" value="true">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('repeatset');"><span class="text_link"><gsmsg:write key="schedule.147" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.8" /></div></dd>
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


<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</html:form>
</body>
</html:html>