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
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/schedule/sch080">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/schedule/jsp/sch080_hiddenParams.jsp" %>

<logic:notEmpty name="sch080Form" property="sch100SvSearchTarget" scope="request">
  <logic:iterate id="svTarget" name="sch080Form" property="sch100SvSearchTarget" scope="request">
    <input type="hidden" name="sch100SvSearchTarget" value="<bean:write name="svTarget"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch080Form" property="sch100SearchTarget" scope="request">
  <logic:iterate id="target" name="sch080Form" property="sch100SearchTarget" scope="request">
    <input type="hidden" name="sch100SearchTarget" value="<bean:write name="target"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch080Form" property="sch100SvBgcolor" scope="request">
  <logic:iterate id="svBgcolor" name="sch080Form" property="sch100SvBgcolor" scope="request">
    <input type="hidden" name="sch100SvBgcolor" value="<bean:write name="svBgcolor"/>">
  </logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="sch080Form" property="sch100Bgcolor" scope="request">
  <logic:iterate id="bgcolor" name="sch080Form" property="sch100Bgcolor" scope="request">
    <input type="hidden" name="sch100Bgcolor" value="<bean:write name="bgcolor"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="sch080Form" property="sch100CsvOutField" scope="request">
  <logic:iterate id="csvOutField" name="sch080Form" property="sch100CsvOutField" scope="request">
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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="schedule.108" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('sch080back');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('crange');"><span class="text_link"><gsmsg:write key="sch.preferences" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch080.2" /></li></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('adel');"><span class="text_link"><gsmsg:write key="cmn.automatic.delete.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch080.4" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('sdel');"><span class="text_link"><gsmsg:write key="cmn.manual.delete" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch080.9" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('simp');"><span class="text_link"><gsmsg:write key="schedule.31" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch080.1" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('gmdspset');"><span class="text_link"><gsmsg:write key="schedule.sch080.5" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch080.6" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('iniset');"><span class="text_link"><gsmsg:write key="cmn.default.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.5" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('repeatset');"><span class="text_link"><gsmsg:write key="schedule.147" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.8" /></div></dd>
      </dl>

      <logic:equal name="sch080Form" property="smailUseOk" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.PLUGIN_USE) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailAdmset');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.2" /></div></dd>
      </dl>
      </logic:equal>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('spAccess');"><span class="text_link"><gsmsg:write key="schedule.sch080.10" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch080.11" /></div></dd>
      </dl>

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