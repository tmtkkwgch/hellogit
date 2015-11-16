<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- 手動削除区分 --%>
<%
  String manuDelNo        = String.valueOf(jp.groupsession.v2.rng.RngConst.MANU_DEL_NO);
  String manuDelOk        = String.valueOf(jp.groupsession.v2.rng.RngConst.MANU_DEL_OK);
%>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="cmn.manual.delete" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../ringi/js/rng170.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="wmlInitDelKbn();">

<html:form action="/ringi/rng170">

<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.manual.delete" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table summary="" cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%"> </td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush('confirm');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backMenu');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td></tr>
  <tr><td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td>

    <table width="100%" class="tl0" border="0" cellpadding="5" style="margin-top: 10px">
      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="rng.48" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
        <html:radio name="rng170Form" property="rng170pendingKbn" value="<%= manuDelNo %>" styleId="manuDelNo1" onclick="wmlChangeDelKbn(1)" /><label for="manuDelNo1"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="rng170Form" property="rng170pendingKbn" value="<%= manuDelOk %>" styleId="manuDelOk1" onclick="wmlChangeDelKbn(1)" /><label for="manuDelOk1"><gsmsg:write key="wml.60" /></label>
          <logic:notEmpty name="rng170Form" property="yearLabelList">
            <html:select property="rng170pendingYear" styleId="delYear1">
              <html:optionsCollection name="rng170Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="rng170Form" property="monthLabelList">
            <html:select property="rng170pendingMonth" styleId="delMonth1">
              <html:optionsCollection name="rng170Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="rng170Form" property="dayLabelList">
            <html:select property="rng170pendingDay" styleId="delDay1">
              <html:optionsCollection name="rng170Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.complete" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
        <html:radio name="rng170Form" property="rng170completeKbn" value="<%= manuDelNo %>" styleId="manuDelNo2" onclick="wmlChangeDelKbn(2)" /><label for="manuDelNo2"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="rng170Form" property="rng170completeKbn" value="<%= manuDelOk %>" styleId="manuDelOk2" onclick="wmlChangeDelKbn(2)" /><label for="manuDelOk2"><gsmsg:write key="wml.60" /></label>
          <logic:notEmpty name="rng170Form" property="yearLabelList">
            <html:select property="rng170completeYear" styleId="delYear2">
              <html:optionsCollection name="rng170Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="rng170Form" property="monthLabelList">
            <html:select property="rng170completeMonth" styleId="delMonth2">
              <html:optionsCollection name="rng170Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="rng170Form" property="dayLabelList">
            <html:select property="rng170completeDay" styleId="delDay2">
              <html:optionsCollection name="rng170Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </span>
      </td>
      </tr>

      <tr>
      <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.draft" /></span></td>
      <td align="left" class="td_wt" width="80%">
        <span class="text_base">
        <html:radio name="rng170Form" property="rng170draftKbn" value="<%= manuDelNo %>" styleId="manuDelNo3" onclick="wmlChangeDelKbn(3)" /><label for="manuDelNo3"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="rng170Form" property="rng170draftKbn" value="<%= manuDelOk %>" styleId="manuDelOk3" onclick="wmlChangeDelKbn(3)" /><label for="manuDelOk3"><gsmsg:write key="wml.60" /></label>
          <logic:notEmpty name="rng170Form" property="yearLabelList">
            <html:select property="rng170draftYear" styleId="delYear3">
              <html:optionsCollection name="rng170Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="rng170Form" property="monthLabelList">
            <html:select property="rng170draftMonth" styleId="delMonth3">
              <html:optionsCollection name="rng170Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="rng170Form" property="dayLabelList">
            <html:select property="rng170draftDay" styleId="delDay3">
              <html:optionsCollection name="rng170Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
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
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backMenu');">
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