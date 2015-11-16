<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.wml.wml180.Wml180Form" %>

<%-- 手動削除区分 --%>
<%
  String manuDelNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MANU_DEL_NO);
  String manuDelOk        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MANU_DEL_OK);
%>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.21" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../webmail/js/wml180.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="wml180ChangeDelKbn();">

<html:form action="/webmail/wml180">

<html:hidden property="backScreen" />
<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden name="wml180Form" property="wmlViewAccount" />
<input type="hidden" name="CMD" value="">

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.21" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('admTool');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr><td><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td></tr>

  <logic:messagesPresent message="false">
    <tr><td><html:errors/></td></tr>
    <tr><td><br></td></tr>
  </logic:messagesPresent>


  <logic:equal name="wml180Form" property="wmlEntryMailLogFlg" value="false">
    <tr><td><span class="text_r2"><gsmsg:write key="wml.114" /></span></td></tr>
  </logic:equal>
  <tr>
  <td>

    <table summary="" id="wml_settings">

      <tr>
      <th><gsmsg:write key="wml.59" /></th>
      <td>
        <html:radio styleId="wml180_delKbn0" name="wml180Form" property="wml180delKbn" value="<%= String.valueOf(Wml180Form.DEL_KBN_DATE) %>" onclick="wml180ChangeDelKbn();" /><label for="wml180_delKbn0"><gsmsg:write key="wml.wml180.01" /></label>
        &nbsp;<html:radio styleId="wml180_delKbn1" name="wml180Form" property="wml180delKbn" value="<%= String.valueOf(Wml180Form.DEL_KBN_DATEAREA) %>" onclick="wml180ChangeDelKbn();" /><label for="wml180_delKbn1"><gsmsg:write key="wml.05" /></label>
        &nbsp;<html:radio styleId="wml180_delKbn2" name="wml180Form" property="wml180delKbn" value="<%= String.valueOf(Wml180Form.DEL_KBN_ALL) %>" onclick="wml180ChangeDelKbn();" /><label for="wml180_delKbn2"><gsmsg:write key="cmn.delete.all" /></label>

        <span id="dateElement">
        <br><br>
        <logic:notEmpty name="wml180Form" property="yearLabelList">
          <html:select property="wml180delYear" styleId="delYear">
            <html:optionsCollection name="wml180Form" property="yearLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="wml180Form" property="monthLabelList">
          <html:select property="wml180delMonth" styleId="delMonth">
            <html:optionsCollection name="wml180Form" property="monthLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>

        <logic:notEmpty name="wml180Form" property="dayLabelList">
          <html:select property="wml180delDay" styleId="delDay">
            <html:optionsCollection name="wml180Form" property="dayLabelList" value="value" label="label" />
          </html:select>
        </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </span>

        <span id="dateAreaElement">
        <br><br>
        <html:select property="wml180delYearFr">
          <html:optionsCollection name="wml180Form" property="dateAreaYearLabelList" value="value" label="label" />
        </html:select><gsmsg:write key="cmn.year2" />
        &nbsp;
        <html:select property="wml180delMonthFr">
          <html:optionsCollection name="wml180Form" property="dateAreaMonthLabelList" value="value" label="label" />
        </html:select><gsmsg:write key="cmn.month" />
        &nbsp;
        <html:select property="wml180delDayFr">
          <html:optionsCollection name="wml180Form" property="dateAreaDayLabelList" value="value" label="label" />
        </html:select><gsmsg:write key="cmn.day" />

        &nbsp;<gsmsg:write key="tcd.153" />&nbsp;

        <html:select property="wml180delYearTo">
          <html:optionsCollection name="wml180Form" property="dateAreaYearLabelList" value="value" label="label" />
        </html:select><gsmsg:write key="cmn.year2" />
        &nbsp;
        <html:select property="wml180delMonthTo">
          <html:optionsCollection name="wml180Form" property="dateAreaMonthLabelList" value="value" label="label" />
        </html:select><gsmsg:write key="cmn.month" />
        &nbsp;
        <html:select property="wml180delDayTo">
          <html:optionsCollection name="wml180Form" property="dateAreaDayLabelList" value="value" label="label" />
        </html:select><gsmsg:write key="cmn.day" />
        </span>
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
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('confirm');">
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