<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="main.man260.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man260.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03" onload="changeEnableDisable();">
<html:form action="/main/man260">

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man260.1" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('man260_opLogKn');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.autodelete" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man260.2" /></span>

      <br>
      <bean:define id="man260BachTime" name="man260Form" property="man260BatchFrHour" type="java.lang.String" />
      <span class="text_r1"><gsmsg:write key="cmn.automatic.performed.time" arg0="<%= man260BachTime %>" /></span>


      <br><br>
      <html:radio name="man260Form" property="man260BatchKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.OPE_LOG_NOTCONF) %>" styleId="man260JdelKbn_0" onclick="setDispState(this.form.man260BatchKbn, this.form.man260Year, this.form.man260Month)" /><label for="man260JdelKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
      <html:radio name="man260Form" property="man260BatchKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.OPE_LOG_CONF) %>" styleId="man260JdelKbn_1" onclick="setDispState(this.form.man260BatchKbn, this.form.man260Year, this.form.man260Month)" /><label for="man260JdelKbn_1"><gsmsg:write key="cmn.automatically.delete" />&nbsp;</label>&nbsp;

    <gsmsg:write key="cmn.after.data.head" />

    <logic:notEmpty name="man260Form" property="yearLabel">
      <html:select property="man260Year" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="man260Form" property="yearLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>

    <logic:notEmpty name="man260Form" property="monthLabel">
      <html:select property="man260Month" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="man260Form" property="monthLabel" value="value" label="label" />
      </html:select>
       <gsmsg:write key="cmn.after.data" />
    </logic:notEmpty>

    </td>
    </tr>
    </table>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('man260_opLogKn');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');"></td>
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