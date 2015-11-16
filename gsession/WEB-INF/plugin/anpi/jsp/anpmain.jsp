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
<title>[Group Session] <gsmsg:write key="anp.anp010.01"/></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anpmain">
<!-- BODY -->
<input type="hidden" name="CMD">

<logic:notEmpty name="anpMainForm" property="anpMainAnpiSid">

    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left" class="header_7D91BD_right" >
        <img src="../anpi/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="anp.plugin"/>">
            <a href="../anpi/anp010.do"><gsmsg:write key="anp.plugin"/>&nbsp;&nbsp;<gsmsg:write key="anp.anp010.06"/></a>
    </td>
    </tr>
    <tr>
    <td class="td_type1">
    <logic:notEmpty name="anpMainForm" property="anpMainState" >
       <table class="tl0 table_td_border" width="100%" cellpadding="5" cellspacing="0">
          <tr class="td_type1">
              <th class="td_type3" nowrap><gsmsg:write key="anp.date.send"/></th>
              <td class="td_type1" colspan="2" nowrap><bean:write name="anpMainForm" property="anpMainState.haisinDate" /></td>
          </tr>
          <tr class="td_type1">
              <th class="td_type3" nowrap><gsmsg:write key="anp.date.resend"/></th>
              <td class="td_type1" colspan="2" nowrap><bean:write name="anpMainForm" property="anpMainState.resendDate" /></td>
          </tr>
          <tr class="td_type1">
              <th class="td_type3" nowrap><gsmsg:write key="anp.date.end"/></th>
              <td colspan="2" nowrap><bean:write name="anpMainForm" property="anpMainState.lastDate" /></td>
          </tr>
          <tr class="td_type1">
              <th class="td_type3" nowrap><gsmsg:write key="anp.ans.state"/></th>
              <td colspan="2" nowrap><bean:write name="anpMainForm" property="anpMainState.replyState" /></td>
          </tr>
          <tr class="td_type1">
              <th width="0%" class="td_type3" rowspan="3" nowrap><gsmsg:write key="anp.state"/></th>
              <td width="0%" class="td_type3" nowrap><gsmsg:write key="anp.jokyo.good"/></td>
              <td width="20%" align="right" nowrap><bean:write name="anpMainForm" property="anpMainState.jokyoGood" /></td>
          </tr>
          <tr class="td_type1">
              <td class="td_type3" nowrap><gsmsg:write key="anp.jokyo.keisyo"/></td>
              <td align="right" nowrap><bean:write name="anpMainForm" property="anpMainState.jokyoKeisyo" /></td>
          </tr>
          <tr class="td_type1">
              <td class="td_type3" nowrap><gsmsg:write key="anp.jokyo.jusyo"/></td>
              <td align="right" nowrap><bean:write name="anpMainForm" property="anpMainState.jokyoJusyo" /></td>
          </tr>
          <tr class="td_type1">
              <th class="td_type3" rowspan="2" nowrap><gsmsg:write key="anp.syusya"/></th>
              <td class="td_type3" nowrap><gsmsg:write key="anp.syusya.ok2"/></td>
              <td align="right" nowrap><bean:write name="anpMainForm" property="anpMainState.syusyaOk" /></td>
          </tr>
          <tr class="td_type1">
              <td class="td_type3" nowrap><gsmsg:write key="anp.syusya.no"/></td>
              <td align="right" nowrap><bean:write name="anpMainForm" property="anpMainState.syusyaNo" /></td>
          </tr>
        </table>
        </logic:notEmpty>
    </td>
    </tr>
    </table>

</logic:notEmpty>

</html:form>
</body>
</html:html>