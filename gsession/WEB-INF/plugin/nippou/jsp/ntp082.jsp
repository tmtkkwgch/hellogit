<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /><gsmsg:write key="anp.anp080.01" /></title>
<script language="JavaScript" src="../common/js/cmd.js"></script>
<script language="JavaScript" src="../common/js/prototype.js"></script>
<script language="JavaScript" src="../nippou/js/ntp082.js"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css" type="text/css">
</head>

<body class="body_03" onload="initSetting();">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp082">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp010DspDate"/>
<html:hidden property="ntp010SelectUsrSid"/>
<html:hidden property="ntp010SelectUsrKbn"/>
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid"/>
<html:hidden property="ntp020SelectUsrSid"/>
<html:hidden property="ntp030SelectUsrSid"/>
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="backScreen" />

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
<!--　BODY -->
<table align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img alt="<gsmsg:write key="cmn.admin.setting" />" src="../common/images/header_ktool_01.gif" border="0"></td>
    <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ntp.1" /> <gsmsg:write key="cmn.automatic.delete.setting" /> ]</td>
    <td width="0%"><img alt="<gsmsg:write key="cmn.admin.setting" />" src="../common/images/header_ktool_05.gif" border="0"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img alt="" src="../common/images/header_glay_1.gif" border="0"></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('ntp082kakunin');">
    <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="return buttonPush('ntp082back');"></td>
    <td width="0%"><img alt="" src="../common/images/header_glay_3.gif" border="0"></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td><img style="WIDTH: 100px; HEIGHT: 10px" alt="" src="../common/images/spacer.gif" border="0" ></td>
  </tr>

  <tr>
  <td>
    <!-- エラーメッセージ -->
    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span><br>
    </logic:messagesPresent>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.autodelete" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
    <span class="text_base"><gsmsg:write key="ntp.86" /></span><br><br>
    <span class="text_r1"><gsmsg:write key="cmn.automatic.performed.time" arg0="5"/></span><br><br>

    <html:radio name="ntp082Form" property="ntp082AtdelFlg" styleId="ntp082AtdelFlg0" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTO_DELETE_OFF) %>" onclick="changeDisable();" /><span class="text_base7"><label for="ntp082AtdelFlg0"><gsmsg:write key="cmn.noset" /></label></span>&nbsp;
    <html:radio name="ntp082Form" property="ntp082AtdelFlg" styleId="ntp082AtdelFlg1" value="<%= String.valueOf(jp.groupsession.v2.ntp.GSConstNippou.AUTO_DELETE_ON) %>" onclick="changeEnable();" /><span class="text_base7"><label for="ntp082AtdelFlg1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;</span>

    <!-- 年 -->
    <html:select property="ntp082AtdelYear">
    <html:optionsCollection name="ntp082Form" property="ntp082AtdelYearLabel" value="value" label="label" />
    </html:select>

    <!-- 月 -->
    <html:select property="ntp082AtdelMonth">
    <html:optionsCollection name="ntp082Form" property="ntp082AtdelMonthLabel" value="value" label="label" />
    </html:select>
    <span class="text_base7"><gsmsg:write key="cmn.after.data" /></span>

    </td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
  <img height="10" src="../common/images/spacer.gif" width="1" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('ntp082kakunin');">
    <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="return buttonPush('ntp082back');">
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