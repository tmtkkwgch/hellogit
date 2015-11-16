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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man180.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man180.1" /></title>
</head>

<body class="body_03" onload="setDispState()">

<!--　BODY -->
<html:form action="/main/man180">
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man180.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('batch_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%"><span class="text_bb1"><gsmsg:write key="cmn.autodelete" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man180.2" /></span>
      <logic:notEmpty name="man180Form" property="man180BatchTime">
      <br>
      <bean:define id="man180BachTime" name="man180Form" property="man180BatchTime" type="java.lang.String" />
      <span class="text_r1"><gsmsg:write key="cmn.automatic.performed.time" arg0="<%= man180BachTime %>" /></span>
      </logic:notEmpty>
      <br><br>
      <html:radio name="man180Form" property="man180BatchKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.LHIS_DELKBN_OFF) %>" styleId="man180BatchKbn_0" onclick="setDispState()" /><label for="man180BatchKbn_0"><gsmsg:write key="cmn.noset" /></label>&nbsp;
      <html:radio name="man180Form" property="man180BatchKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.LHIS_DELKBN_ON) %>" styleId="man180BatchKbn_1" onclick="setDispState()" /><label for="man180BatchKbn_1"><gsmsg:write key="cmn.automatically.delete" /></label>&nbsp;

      <gsmsg:write key="cmn.after.data.head" />
      <html:select property="man180Year" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="man180Form" property="man180YearLabelList" value="value" label="label" />
      </html:select>
      <html:select property="man180Month" styleClass="select01" style="width: 100px;">
        <html:optionsCollection name="man180Form" property="man180MonthLabelList" value="value" label="label" />
      </html:select>
      <gsmsg:write key="cmn.after.data" />
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">


    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('batch_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
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