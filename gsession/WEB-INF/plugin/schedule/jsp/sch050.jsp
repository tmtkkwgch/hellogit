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
<title>[GroupSession] <gsmsg:write key="schedule.sch050.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../schedule/js/sch050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<!--　BODY -->
<body class="body_03">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/schedule/sch050">
<input type="hidden" name="CMD" value="">

<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center">
<logic:messagesPresent message="false">
    <table width="70%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
  </table>
</logic:messagesPresent>
  <table width="70%">
  <tr>
  <td align="center">
    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td align="left" class="td_type5" colspan="2">

      <table cellpadding="0" cellpadding="0" border="0" width="100%">
        <tr>
          <td align="left" valign="middle" width="100%">
            <font class="text_tl1"><gsmsg:write key="schedule.sch050.1" /></font>
          </td>
          <td align="right" valign="middle">
            <input type="button" value="<gsmsg:write key="cmn.setting2" />" class="btn_setting_n1" onClick="buttonPush('050_ok');">
          </td>
          <td align="right" valign="middle">
            <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('050_back');">
          </td>
        </tr>
      </table>

    </td>
    </tr>
    <tr>
    <td class="td_gray" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.starttime" /></span></td>
    <td align="left" class="td_wt2" width="100%">
    <html:select property="sch050FrHour">
      <html:optionsCollection name="sch050Form" property="sch050HourLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch050FrMin">
      <html:optionsCollection name="sch050Form" property="sch050MinuteLabel" value="value" label="label" />
    </html:select>
    </td>
    </tr>

    <tr>
    <td class="td_gray" nowrap><span class="text_bb1"><gsmsg:write key="cmn.endtime" /></span></td>
    <td class="td_wt2">
    <html:select property="sch050ToHour">
      <html:optionsCollection name="sch050Form" property="sch050HourLabel" value="value" label="label" />
    </html:select>
    <html:select property="sch050ToMin">
      <html:optionsCollection name="sch050Form" property="sch050MinuteLabel" value="value" label="label" />
    </html:select>
    </td>
    </tr>
    <tr>
    <td align="left" class="td_type5" colspan="2">

      <table cellpadding="0" cellpadding="0" border="0" width="100%">
        <tr>
          <td align="left" valign="middle" width="100%">
            <font class="text_tl1">&nbsp;</font>
          </td>
          <td align="right" valign="middle">
            <input type="button" value="<gsmsg:write key="cmn.setting2" />" class="btn_setting_n1" onClick="buttonPush('050_ok');">
          </td>
          <td align="right" valign="middle">
            <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('050_back');">
          </td>
        </tr>
      </table>

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