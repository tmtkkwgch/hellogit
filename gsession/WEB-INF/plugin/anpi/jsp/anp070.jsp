<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp070">
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp070back');">
        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('anp070base');"><span class="text_link"><gsmsg:write key="cmn.preferences" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="anp.anp070.01"/></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('anp070mailtemp');"><span class="text_link"><gsmsg:write key="anp.anp070.02"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="anp.anp070.03"/></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('anp070contact');"><span class="text_link"><gsmsg:write key="anp.anp070.04"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="anp.anp070.05"/></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('anp070allset');"><span class="text_link"><gsmsg:write key="anp.anp070.06"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="anp.anp070.07"/></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="#" onClick="return buttonPush('anp070history');"><span class="text_link"><gsmsg:write key="anp.anp070.08"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="anp.anp070.09"/></div></dd>
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


</html:form>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>