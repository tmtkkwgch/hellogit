<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-dailyScheduleRow.tld" prefix="dailyScheduleRow" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.ntp.GSConstNippou" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css' type='text/css'>
<link rel=stylesheet href='../nippou/css/nippou.css' type='text/css'>

</head>

<body class="body_03">
<html:form action="/nippou/ntp090">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="ntp010DspDate" />

<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp010searchWord" />


<html:hidden property="ntp100PageNum" />
<html:hidden property="ntp100Slt_page1" />
<html:hidden property="ntp100Slt_page2" />
<html:hidden property="ntp100OrderKey1" />
<html:hidden property="ntp100SortKey1" />
<html:hidden property="ntp100OrderKey2" />
<html:hidden property="ntp100SortKey2" />

<html:hidden property="ntp100SvSltGroup" />
<html:hidden property="ntp100SvSltUser" />
<html:hidden property="ntp100SvSltYearFr" />
<html:hidden property="ntp100SvSltMonthFr" />
<html:hidden property="ntp100SvSltDayFr" />
<html:hidden property="ntp100SvSltYearTo" />
<html:hidden property="ntp100SvSltMonthTo" />
<html:hidden property="ntp100SvSltDayTo" />
<html:hidden property="ntp100SvKeyWordkbn" />
<html:hidden property="ntp100SvKeyValue" />
<html:hidden property="ntp100SvOrderKey1" />
<html:hidden property="ntp100SvSortKey1" />
<html:hidden property="ntp100SvOrderKey2" />
<html:hidden property="ntp100SvSortKey2" />

<html:hidden property="ntp100SltGroup" />
<html:hidden property="ntp100SltUser" />
<html:hidden property="ntp100SltYearFr" />
<html:hidden property="ntp100SltMonthFr" />
<html:hidden property="ntp100SltDayFr" />
<html:hidden property="ntp100SltYearTo" />
<html:hidden property="ntp100SltMonthTo" />
<html:hidden property="ntp100SltDayTo" />
<html:hidden property="ntp100KeyWordkbn" />

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
    <td width="100%" class="header_ktool_bg_text"><gsmsg:write key="cmn.preferences2" /> [ <gsmsg:write key="ntp.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('ntp090back');">
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="left">

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('iniset');"><span class="text_link"><gsmsg:write key="ntp.104" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.105" /></div></dd>
      </dl>
<%--
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('daydspset');"><span class="text_link">日間表示時間帯設定</span></a></dt>
      <dd><div class="text"><li>日報日間で基本表示を行う時間帯の設定を行います。</div></dd>
      </dl>
--%>
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('gmdspset');"><span class="text_link"><gsmsg:write key="sch.groupmember.view.set" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="schedule.sch090.1" /></div></dd>
      </dl>

      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('listdspset');"><span class="text_link"><gsmsg:write key="ntp.106" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.107" /></div></dd>
      </dl>
      <logic:equal name="ntp090Form" property="smailUseOk" value="<%=String.valueOf(GSConstNippou.PLUGIN_USE) %>">
      <logic:equal name="ntp090Form" property="ntp090SmlNoticeKbn" value="<%= String.valueOf(GSConstNippou.SML_NOTICE_USR) %>">
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('smailset');"><span class="text_link"><gsmsg:write key="cmn.sml.notification.setting" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.159" /></div></dd>
      </dl>
      </logic:equal>
      </logic:equal>
      
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('schkbnset');"><span class="text_link"><gsmsg:write key="ntp.108" /></span></a></dt>
      <dd><div class="text"><li><gsmsg:write key="ntp.109" /></div></dd>
      </dl>

<%--
      <dl class="decorate_textbox1">
      <dt><a href="#" onClick="return buttonPush('targetset');"><span class="text_link">目標値設定</span></a></dt>
      <dd><div class="text"><li>目標値の設定を行います。</div></dd>
      </dl>
--%>
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