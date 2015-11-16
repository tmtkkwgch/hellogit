<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<body class="body_03">
<html:form action="/bookmark/bmk140">
<input type="hidden" name="CMD" value="">
<html:hidden name="bmk140Form" property="backScreen" />

<logic:notEmpty name="bmk140Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk140Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<logic:notEmpty name="bmk140Form" property="bmk140ViewBmkLabel">
<logic:iterate id="ViewBmk" name="bmk140Form" property="bmk140ViewBmkLabel">
<input type="hidden" name="bmk140ViewBmkList" value="<bean:write name="ViewBmk" property="value" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.main.view" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.setting" />" onClick="return buttonPush('bmk140commit');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk140back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  <logic:messagesPresent message="false">
  <tr><td width="100%"><html:errors /></td></tr>
  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </logic:messagesPresent>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.30" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <html:radio name="bmk140Form" property="bmk140MyKbn" styleId="bmk140MyKbn1" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_YES) %>" onclick="buttonPush('redraw');" /><span class="text_base"><label for="bmk140MyKbn1"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <html:radio name="bmk140Form" property="bmk140MyKbn" styleId="bmk140MyKbn0" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_NO) %>" onclick="buttonPush('redraw');" /><span class="text_base"><label for="bmk140MyKbn0"><gsmsg:write key="cmn.dont.show" /></label></span>&nbsp;

      <logic:equal name="bmk140Form" property="bmk140MyKbn" value="1">
          <br>
          <table width="0%" border="0">

          <tr>
          <td width="40%" valign="top">
            <table border="0" width="0%">
            <tr>
            <td class="td_type14" align="center"><gsmsg:write key="bmk.bmk140.06" /></td>
            </tr>
            <tr>
            <td align="center">
              <html:select name="bmk140Form" property="bmk140SelectNotViewBmk" size="12" style="width:220px;">
              <html:optionsCollection name="bmk140Form" property="bmk140NotViewBmkLabel" value="value" label="label" />
                 <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
              </html:select>
            </td>
            </tr>
            </table>
          </td>

          <td width="10%" align="center" valign="middle">
            <img src="../common/images/arrow2_r.gif" width="25" height="25" alt="<gsmsg:write key="cmn.add" />" border="0" onClick="buttonPush('add');"><br><br>
            <img src="../common/images/arrow2_l.gif" width="25" height="25" alt="<gsmsg:write key="cmn.delete" />" border="0" onClick="buttonPush('delete');">
          </td>

          <td width="40%" valign="top">
            <table border="0" width="0%">
            <tr>
            <td class="td_type14" align="center"><gsmsg:write key="bmk.bmk140.05" /></td>
            </tr>
            <tr>
            <td align="center">
              <html:select name="bmk140Form" property="bmk140SelectViewBmk" size="12" style="width:220px;">
              <html:optionsCollection name="bmk140Form" property="bmk140ViewBmkLabel" value="value" label="label" />
                 <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
              </html:select>
            </td>
            </tr>
            </table>
          </td>

          <td width="10%" align="center">
            <img src="../common/images/arrow2_u.gif" width="25" height="25" alt="<gsmsg:write key="cmn.up" />" border="0" onClick="buttonPush('up');"><br><br>
            <img src="../common/images/arrow2_d.gif" width="25" height="25" alt="<gsmsg:write key="cmn.down" />" border="0" onClick="buttonPush('down');">
          </td>
          </tr>

          </table>
      </logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.24" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <html:radio name="bmk140Form" property="bmk140NewKbn" styleId="bmk140NewKbn1" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_YES) %>" onclick="" /><span class="text_base"><label for="bmk140NewKbn1"><gsmsg:write key="cmn.display.ok" /></label></span>&nbsp;
      <html:radio name="bmk140Form" property="bmk140NewKbn" styleId="bmk140NewKbn0" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_NO) %>" onclick="" /><span class="text_base"><label for="bmk140NewKbn0"><gsmsg:write key="cmn.dont.show" /></label></span>&nbsp;
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.bmk140.04" /></span></td>
    <td align="left" class="td_wt" width="100%">
      <html:select property="bmk140newCnt">
        <html:optionsCollection name="bmk140Form" property="bmk140newCntLabel" value="value" label="label" />
      </html:select>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_setting_n1" value="<gsmsg:write key="cmn.setting" />" onClick="return buttonPush('bmk140commit');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk140back');">
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