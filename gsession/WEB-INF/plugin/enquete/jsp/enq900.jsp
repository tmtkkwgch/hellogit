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
<html:form action="/enquete/enq900" >
<!-- BODY -->
<input type="hidden" name="CMD">
<html:hidden property="cmd" />
<html:hidden property="backScreen" />

<!-- 検索条件hidden -->
<%@ include file="/WEB-INF/plugin/enquete/jsp/enq010_hiddenParams.jsp" %>

<logic:notEmpty name="enq900Form" property="enq010priority">
<logic:iterate id="svPriority" name="enq900Form" property="enq010priority">
  <input type="hidden" name="enq010priority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq900Form" property="enq010status">
<logic:iterate id="svStatus" name="enq900Form" property="enq010status">
  <input type="hidden" name="enq010status" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq900Form" property="enq010svPriority">
<logic:iterate id="svPriority" name="enq900Form" property="enq010svPriority">
  <input type="hidden" name="enq010svPriority" value="<bean:write name="svPriority" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq900Form" property="enq010svStatus">
<logic:iterate id="svStatus" name="enq900Form" property="enq010svStatus">
  <input type="hidden" name="enq010svStatus" value="<bean:write name="svStatus" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq900Form" property="enq010statusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq900Form" property="enq010statusAnsOver">
  <input type="hidden" name="enq010statusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>
<logic:notEmpty name="enq900Form" property="enq010svStatusAnsOver">
<logic:iterate id="svStatusAnsOver" name="enq900Form" property="enq010svStatusAnsOver">
  <input type="hidden" name="enq010svStatusAnsOver" value="<bean:write name="svStatusAnsOver" />">
</logic:iterate>
</logic:notEmpty>


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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="enq.plugin" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('enq900back');">
        </td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
        <td align="left">
          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900makeUser');"><span class="text_link"><gsmsg:write key="enq.enq900.01" /></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.02"/></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900syurui');"><span class="text_link"><gsmsg:write key="enq.enq900.03"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.04"/></li></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900dsp');"><span class="text_link"><gsmsg:write key="enq.enq900.05"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.06"/></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900dspMain');"><span class="text_link"><gsmsg:write key="enq.enq900.07"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.08"/></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900autoDel');"><span class="text_link"><gsmsg:write key="enq.enq900.11"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.12"/></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900del');"><span class="text_link"><gsmsg:write key="enq.enq900.09"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.10"/></div></dd>
          </dl>

          <dl class="decorate_textbox1">
          <dt><a href="javascript:void(0)" onClick="buttonPush('enq900mngEnq');"><span class="text_link"><gsmsg:write key="enq.enq900.13"/></span></a></dt>
          <dd><div class="text"><li><gsmsg:write key="enq.enq900.14"/></div></dd>
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