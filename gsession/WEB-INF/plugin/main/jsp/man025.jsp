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
<meta http-equiv="Content-Type" content="text/html; charset=shift_jis">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.holiday.setting" /></title>
<script language="JavaScript" src="../main/js/man025.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/main/man025">
    <logic:equal name="man025Form" property="processMode" scope="request" value="addTemp">
<input type="hidden" name="CMD" value="add">
    </logic:equal>
    <logic:equal name="man025Form" property="processMode" scope="request" value="editTemp">
<input type="hidden" name="CMD" value="edit">
    </logic:equal>

<html:hidden property="processMode" />
<html:hidden property="man020DspYear" />
<html:hidden property="editHltSid" />
<html:hidden property="man023CheckAll" />

<logic:notEmpty name="man025Form" property="man023hltSid" scope="request">
<logic:iterate id="hltBean" name="man025Form" property="man023hltSid" scope="request">
  <input type="hidden" name="man023hltSid" value="<bean:write name="hltBean" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%">

<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <logic:equal name="man025Form" property="processMode" scope="request" value="addTemp">
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man025.1" /> ]</td>
    </logic:equal>
    <logic:equal name="man025Form" property="processMode" scope="request" value="editTemp">
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man025.2" /> ]</td>
    </logic:equal>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

      <logic:equal name="man025Form" property="processMode" scope="request" value="addTemp">
        <input type="submit" value="<gsmsg:write key="user.59" />" class="btn_add_n1">
      </logic:equal>
      <logic:equal name="man025Form" property="processMode" scope="request" value="editTemp">
        <input type="button" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1" onclick="buttonPush('edit')">
      </logic:equal>
        <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onclick="buttonPush('backTemp')">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>


    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td>

    <logic:equal name="man025Form" property="processMode" scope="request" value="addTemp">
      <input type="button" value="<gsmsg:write key="main.man025.3" />" class="btn_base1" onclick="buttonPush('kakucho')">
    </logic:equal>

    </td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table width="100%" class="tl0 table_td_border" border="0" cellpadding="5">
    <tr class="table_bg_A5B4E1">
    <th width="30%"><gsmsg:write key="cmn.date2" /></th>
    <th width="70%"><gsmsg:write key="cmn.holiday.name" /></th>
    </tr>

    <tr>
    <th align="center" width="20%">

    <html:select name="man025Form" property="man025HltMonth">
      <html:optionsCollection name="man025Form" property="man025MonthLabel" value="value" label="label" />
    </html:select>
    <html:select name="man025Form" property="man025HltDay">
      <html:optionsCollection name="man025Form" property="man025DayLabel" value="value" label="label" />
    </html:select>

    </th>
    <td width="70%">
      <html:text property="man025HltName" size="60" maxlength="20" style="width:100%;" />
    </td>
    </tr>

    <tr>
    <td width="100%" colspan="2" class="td_type11" align="left">
      <html:multibox name="man025Form" property="man025FuriFlg" value="0" />
      <span class="text_r1"><gsmsg:write key="main.man025.4" /></span><br>
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