<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% int tmodeShare = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_SHARE; %>
<% int tmodePrivate = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_PRIVATE; %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../ringi/css/ringi.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../ringi/js/pageutil.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="rng.62" /> <gsmsg:write key="cmn.preferences2" /></title>
</head>

<body class="body_03">

<html:form action="ringi/rng080">
<input type="hidden" name="CMD" value="">

<html:hidden property="backScreen" />
<html:hidden property="rng010orderKey" />
<html:hidden property="rng010sortKey" />
<html:hidden property="rng010pageTop" />
<html:hidden property="rng010pageBottom" />

<html:hidden property="rngProcMode" />
<html:hidden property="rngTemplateMode" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="rng.62" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rng010')">
        </td>
      </tr>
    </table>


    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="template(<%= tmodePrivate %>, 'rng060')"><span class="text_link"><gsmsg:write key="rng.rng080.02" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="rng.rng080.04" /></li></div></dd>
          </dl>

        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="template(<%= tmodePrivate %>, 'rng100')"><span class="text_link"><gsmsg:write key="rng.rng080.01" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="rng.16" /></li></div></dd>
          </dl>

        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('rng120');"><span class="text_link"><gsmsg:write key="cmn.preferences2" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="rng.rng080.03" /></li></div></dd>
          </dl>

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