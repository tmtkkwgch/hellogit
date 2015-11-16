<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%-- 手動削除区分 --%>
<%
  String manuDelNo        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MANU_DEL_NO);
  String manuDelOk        = String.valueOf(jp.groupsession.v2.cmn.GSConstWebmail.MANU_DEL_OK);
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
<script type="text/javascript" src="../webmail/js/wml060.js?<%= GSConst.VERSION_PARAM %>"></script>

</head>

<body class="body_03" onload="wmlInitDelKbn();">

<html:form action="/webmail/wml060">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>
<html:hidden name="wml060Form" property="wmlViewAccount" />
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />

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

  <tr>
  <td>

    <table summary="" id="wml_settings">

      <tr>
      <th><gsmsg:write key="cmn.trash" /></th>
      <td>
        <html:radio name="wml060Form" property="wml060delKbn1" value="<%= manuDelNo %>" styleId="manuDelNo1" onclick="wmlChangeDelKbn(1)" /><label for="manuDelNo1"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="wml060Form" property="wml060delKbn1" value="<%= manuDelOk %>" styleId="manuDelOk1" onclick="wmlChangeDelKbn(1)" /><label for="manuDelOk1"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="wml060Form" property="yearLabelList">
            <html:select property="wml060delYear1" styleId="delYear1">
              <html:optionsCollection name="wml060Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="monthLabelList">
            <html:select property="wml060delMonth1" styleId="delMonth1">
              <html:optionsCollection name="wml060Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="dayLabelList">
            <html:select property="wml060delDay1" styleId="delDay1">
              <html:optionsCollection name="wml060Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>

      <tr>
      <th><gsmsg:write key="wml.19" /></th>
      <td>
        <html:radio name="wml060Form" property="wml060delKbn2" value="<%= manuDelNo %>" styleId="manuDelNo2" onclick="wmlChangeDelKbn(2)" /><label for="manuDelNo2"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="wml060Form" property="wml060delKbn2" value="<%= manuDelOk %>" styleId="manuDelOk2" onclick="wmlChangeDelKbn(2)" /><label for="manuDelOk2"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="wml060Form" property="yearLabelList">
            <html:select property="wml060delYear2" styleId="delYear2">
              <html:optionsCollection name="wml060Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="monthLabelList">
            <html:select property="wml060delMonth2" styleId="delMonth2">
              <html:optionsCollection name="wml060Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="dayLabelList">
            <html:select property="wml060delDay2" styleId="delDay2">
              <html:optionsCollection name="wml060Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>

      <tr>
      <th><gsmsg:write key="cmn.draft" /></th>
      <td>
        <html:radio name="wml060Form" property="wml060delKbn3" value="<%= manuDelNo %>" styleId="manuDelNo3" onclick="wmlChangeDelKbn(3)" /><label for="manuDelNo3"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="wml060Form" property="wml060delKbn3" value="<%= manuDelOk %>" styleId="manuDelOk3" onclick="wmlChangeDelKbn(3)" /><label for="manuDelOk3"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="wml060Form" property="yearLabelList">
            <html:select property="wml060delYear3" styleId="delYear3">
              <html:optionsCollection name="wml060Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="monthLabelList">
            <html:select property="wml060delMonth3" styleId="delMonth3">
              <html:optionsCollection name="wml060Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="dayLabelList">
            <html:select property="wml060delDay3" styleId="delDay3">
              <html:optionsCollection name="wml060Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>

      <tr>
      <th><gsmsg:write key="wml.37" /></th>
      <td>
        <html:radio name="wml060Form" property="wml060delKbn4" value="<%= manuDelNo %>" styleId="manuDelNo4" onclick="wmlChangeDelKbn(4)" /><label for="manuDelNo4"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="wml060Form" property="wml060delKbn4" value="<%= manuDelOk %>" styleId="manuDelOk4" onclick="wmlChangeDelKbn(4)" /><label for="manuDelOk4"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="wml060Form" property="yearLabelList">
            <html:select property="wml060delYear4" styleId="delYear4">
              <html:optionsCollection name="wml060Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="monthLabelList">
            <html:select property="wml060delMonth4" styleId="delMonth4">
              <html:optionsCollection name="wml060Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="dayLabelList">
            <html:select property="wml060delDay4" styleId="delDay4">
              <html:optionsCollection name="wml060Form" property="dayLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>
        <gsmsg:write key="wml.73" />
        </div>
      </td>
      </tr>

      <tr>
      <th><gsmsg:write key="cmn.strage" /></th>
      <td>
        <html:radio name="wml060Form" property="wml060delKbn5" value="<%= manuDelNo %>" styleId="manuDelNo5" onclick="wmlChangeDelKbn(5)" /><label for="manuDelNo5"><gsmsg:write key="cmn.dont.delete" /></label><br>
        <html:radio name="wml060Form" property="wml060delKbn5" value="<%= manuDelOk %>" styleId="manuDelOk5" onclick="wmlChangeDelKbn(5)" /><label for="manuDelOk5"><gsmsg:write key="wml.60" /></label>
        <div>
          <logic:notEmpty name="wml060Form" property="yearLabelList">
            <html:select property="wml060delYear5" styleId="delYear5">
              <html:optionsCollection name="wml060Form" property="yearLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="monthLabelList">
            <html:select property="wml060delMonth5" styleId="delMonth5">
              <html:optionsCollection name="wml060Form" property="monthLabelList" value="value" label="label" />
            </html:select>
          </logic:notEmpty>

          <logic:notEmpty name="wml060Form" property="dayLabelList">
            <html:select property="wml060delDay5" styleId="delDay5">
              <html:optionsCollection name="wml060Form" property="dayLabelList" value="value" label="label" />
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