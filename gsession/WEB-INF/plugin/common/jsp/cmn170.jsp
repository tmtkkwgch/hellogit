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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[GroupSession] <gsmsg:write key="main.man030.10" /></title>
</head>

<body class="body_03">
<html:form action="/common/cmn170">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">
  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man030.10" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_ok1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('cmn170kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('cmn170back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:empty name="cmn170Form" property="themeList">
　　        <p align="center"><gsmsg:write key="cmn.cmn170.1" /></p>
    </logic:empty>

    <logic:notEmpty name="cmn170Form" property="themeList">

     <div align="center">
     <table cellpadding="5" cellspacing="0" border="0" width="85%" class="tl_u2">
     <!-- テーマ -->

        <%! boolean flg = false; %>

        <logic:iterate id="theme" name="cmn170Form" property="themeList" indexId="idx1">

        <% if (idx1%2 == 0) { %>
        <tr>
          <% } %>

        <% String StyleId = String.valueOf(idx1); %>
        <bean:define id="ctmid" name="theme" property="ctmSid" type="java.lang.Integer" />

          <td valign="middle" align="left" width="0%" nowrap">
          <table>
            <tr>
            <td colspan="2" valign="bottom" height="35px">
              <label for="<%= StyleId %>" onclick="return clickFormLabel(this);"><span class="theme_image"><img src="../<bean:write name="theme" property="ctmPathImg" />" border="1" alt="<bean:write name="theme" property="ctmName" />"></span></label>&nbsp;
            </td>
            </tr>
            <tr>
            <td width="100%">
              <bean:define id="perCnt" name="theme" property="ctmPerCount" type="java.lang.Integer" />
              <html:radio name="cmn170Form" styleId="<%= StyleId %>" property="cmn170Dsp1" value="<%= String.valueOf(ctmid.intValue()) %>" />
              <label for="<%= StyleId %>" onclick="return clickFormLabel(this);"><span class="text_theme"><bean:write name="theme" property="ctmName" /></span></label>
              <span class="theme-count"><gsmsg:write key="bmk.22" arg0="<%= String.valueOf(perCnt.intValue()) %>" /></span>
            </td>
            </tr>
          </table>
        </td>

        <% if (!(idx1%2 == 0)) {
           flg = false;
        %>
        </tr>
        <%} else {
            flg = true;
            }
          %>

          </logic:iterate>

        <% if (flg == true) { %>
        <td valign="middle" align="left" width="0%" nowrap></td>
        <td valign="middle" align="left"  width="50%" colspan="5"></td>
        </tr>
          <% } %>

        </table>

      </logic:notEmpty>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" class="btn_ok1" value="<gsmsg:write key="cmn.entry" />" onClick="return buttonPush('cmn170kakunin');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('cmn170back');">
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