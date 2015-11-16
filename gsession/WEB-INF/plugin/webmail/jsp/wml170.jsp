<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- 自動削除区分 --%>
<%
  String walKbnNoset     = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAL_KBN_NOSET);
  String walKbnAuto      = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.WAL_KBN_AUTODELETE);
%>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.22" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
  <script language="JavaScript" src="../webmail/js/wml170.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="changeEnableDisable();">

<html:form action="/webmail/wml170">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden name="wml170Form" property="wmlCmdMode" />
<html:hidden name="wml170Form" property="wmlViewAccount" />
<html:hidden name="wml170Form" property="wmlAccountMode" />
<html:hidden name="wml170Form" property="wmlAccountSid" />
<html:hidden name="wml170Form" property="wml170initFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table summary="" align="center" cellpadding="0" cellspacing="5" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table summary="" cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.22" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr><td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <logic:equal name="wml170Form" property="wmlEntryMailLogFlg" value="false">
    <tr><td><span class="text_r2"><gsmsg:write key="wml.114" /></span></td></tr>
  </logic:equal>

  <tr>
  <td>

    <table summary="" id="wml_settings">

      <tr>
      <th><gsmsg:write key="wml.59" /></th>
      <td>
        <html:radio name="wml170Form" property="wml170delKbn" value="<%= walKbnNoset %>" styleId="walDelKbnNo" onclick="setDispState(this.form.wml170delKbn, this.form.wml170delYear, this.form.wml170delMonth, this.form.wml170delDay)" /><label for="walDelKbnNo"><gsmsg:write key="cmn.noset" /></label><br>
        <html:radio name="wml170Form" property="wml170delKbn" value="<%= walKbnAuto %>" styleId="walDelKbnAuto" onclick="setDispState(this.form.wml170delKbn, this.form.wml170delYear, this.form.wml170delMonth, this.form.wml170delDay)" /><label for="walDelKbnAuto"><gsmsg:write key="cmn.autodelete" /></label>

        <div>
        <logic:notEmpty name="wml170Form" property="yearLabelList">
          <html:select property="wml170delYear">
            <html:optionsCollection name="wml170Form" property="yearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="wml170Form" property="monthLabelList">
          <html:select property="wml170delMonth">
            <html:optionsCollection name="wml170Form" property="monthLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="wml170Form" property="dayLabelList">
          <html:select property="wml170delDay">
            <html:optionsCollection name="wml170Form" property="dayLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1" height="10" border="0" alt="">
    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="<gsmsg:write key="cmn.setting" />" class="btn_setting_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');">
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