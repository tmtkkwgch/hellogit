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
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<title>[Group Session] <gsmsg:write key="cmn.zaiseki.management" /> <gsmsg:write key="cmn.preferences2" /></title>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk070">
<input type="hidden" name="CMD">
<html:hidden property="backScreen" />
<html:hidden name="zsk070Form" property="selectZifSid" />
<html:hidden name="zsk070Form" property="uioStatus" />
<html:hidden name="zsk070Form" property="uioStatusBiko" />
<html:hidden name="zsk070Form" property="sortKey" />
<html:hidden name="zsk070Form" property="orderKey" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- body -->
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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.zaiseki.management" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
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
          <dt><a href="#"  onClick="buttonPush('zsk080');"><span class="text_link"><gsmsg:write key="zsk.zsk070.01" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk070.05" /></li></div></dd>

          </dl>

        </td>
      </tr>

      <logic:equal name="zsk070Form" property="zsk070canMemEdit" value="true">
      <tr>
        <td align="left">

          <dl class="decorate_textbox1">
          <dt><a href="#"  onClick="buttonPush('zsk130');"><span class="text_link"><gsmsg:write key="zsk.28" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk070.03" /></li></div></dd>

          </dl>

        </td>
      </tr>
      </logic:equal>

      <tr>
        <td align="left">

          <dl class="decorate_textbox1">
          <dt><a href="#"  onClick="buttonPush('zsk090');"><span class="text_link"><gsmsg:write key="zsk.17" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk070.02" /></li></div></dd>

          </dl>

        </td>
      </tr>

      <tr>
        <td align="left">

          <dl class="decorate_textbox1">
          <dt><a href="#"  onClick="buttonPush('zsk100');"><span class="text_link"><gsmsg:write key="zsk.36" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="zsk.zsk070.04" /></li></div></dd>

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
