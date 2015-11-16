<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=shift_jis">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="zsk.zsk020.03" /></title>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk020">
<input type="hidden" name="CMD">
<html:hidden name="zsk020Form" property="backScreen" />
<html:hidden name="zsk020Form" property="selectZifSid" />
<html:hidden name="zsk020Form" property="uioStatus" />
<html:hidden name="zsk020Form" property="uioStatusBiko" />
<html:hidden name="zsk020Form" property="sortKey" />
<html:hidden name="zsk020Form" property="orderKey" />

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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.zaiseki.management" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('zsk010');">
        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('zsk030');"><span class="text_link"><gsmsg:write key="zsk.26" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk020.02" /></li></div></dd>
          </dl>
        </td>
      </tr>

      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('zsk110');"><span class="text_link"><gsmsg:write key="zsk.11" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk020.01" /></li></div></dd>
          </dl>
        </td>
      </tr>

      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="buttonPush('zsk140');"><span class="text_link"><gsmsg:write key="zsk.74" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk020.04" /></li></div></dd>
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