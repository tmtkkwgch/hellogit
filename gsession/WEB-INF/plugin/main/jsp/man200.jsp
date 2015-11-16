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
<script language="JavaScript" src="../main/js/man200.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man002.39" /></title>
</head>

<body class="body_03" onload="initEnableDisable();">

<!--　BODY -->
<html:form action="/main/man200">
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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.39" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('rule_settei_kakunin');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="buttonPush('backKtool');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">


    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.1" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man200.2" /></span><br>
      <br>

      <html:select property="man200Digit" style="width: 100px;">
        <html:optionsCollection name="man200Form" property="man200DigitLabelList" value="value" label="label" />
      </html:select>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.3" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man200.4" /></span><br>
      <br>
      <html:radio name="man200Form" property="man200CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_OFF) %>" styleId="man200CoeKbn_0" onclick="" /><label for="man200CoeKbn_0"><gsmsg:write key="main.man200.5" /></label>&nbsp;
      <html:radio name="man200Form" property="man200CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_EN) %>" styleId="man200CoeKbn_1" onclick="" /><label for="man200CoeKbn_1"><gsmsg:write key="main.man200.17" /></label>&nbsp;
      <html:radio name="man200Form" property="man200CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_ENS) %>" styleId="man200CoeKbn_2" onclick="" /><label for="man200CoeKbn_2"><gsmsg:write key="main.man200.18" /></label>&nbsp;
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="man.expiration.date" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man200.7" /></span><br>
      <span class="text_r1"><gsmsg:write key="main.man200.8" /></span><br>
      <br>
      <logic:equal name="man200Form" property="manPasswordKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_ADM) %>">
      <html:hidden property="manPasswordKbn" />
      <html:radio name="man200Form" property="man200LimitKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_OFF) %>" disabled="true" styleId="man200LimitKbn_0" onclick="changeLimitKbn(0);" /><label for="man200LimitKbn_0"><gsmsg:write key="main.man200.9" /></label>&nbsp;
      <html:radio name="man200Form" property="man200LimitKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_ON) %>" disabled="true" styleId="man200LimitKbn_1" onclick="changeLimitKbn(1);" /><label for="man200LimitKbn_1"><gsmsg:write key="main.man200.10" /></label>&nbsp;<br>
      <span class="text_r1"><gsmsg:write key="man.passkbn.admin"/></span>
      </logic:equal>
      <logic:equal name="man200Form" property="manPasswordKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PASSWORD_EDIT_USER) %>">
      <html:hidden property="manPasswordKbn" />
      <html:radio name="man200Form" property="man200LimitKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_OFF) %>" styleId="man200LimitKbn_0" onclick="changeLimitKbn(0);" /><label for="man200LimitKbn_0"><gsmsg:write key="main.man200.9" /></label>&nbsp;
      <html:radio name="man200Form" property="man200LimitKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_ON) %>" styleId="man200LimitKbn_1" onclick="changeLimitKbn(1);" /><label for="man200LimitKbn_1"><gsmsg:write key="main.man200.10" /></label>&nbsp;<br>
      </logic:equal>
      <div  id="passLimitDay">
      <br>
      <span class="text_base"><gsmsg:write key="main.man200.11" /></span>&nbsp;<html:text name="man200Form" property="man200LimitDay" maxlength="3" styleClass="text_base" style="width:51px; text-align:right" />&nbsp;<span class="text_base"><gsmsg:write key="main.man200.12" /></span>
      </div>

      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.13" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man200.14" /></span><br><br>
      <html:radio name="man200Form" property="man200UidPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_UIDPSWDKBN_ON) %>" styleId="man200UidPswdKbn_1" onclick="" /><label for="man200UidPswdKbn_1"><gsmsg:write key="cmn.not.permit" /></label>&nbsp;
      <html:radio name="man200Form" property="man200UidPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_UIDPSWDKBN_OFF) %>" styleId="man200UidPswdKbn_0" onclick="" /><label for="man200UidPswdKbn_0"><gsmsg:write key="cmn.permit" /></label>&nbsp;
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.15" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
      <td class="td_type1">
      <span class="text_base"><gsmsg:write key="main.man200.16" /></span><br><br>
      <html:radio name="man200Form" property="man200OldPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_OLDPSWDKBN_ON) %>" styleId="man200OldPswdKbn_1" onclick="" /><label for="man200OldPswdKbn_1"><gsmsg:write key="cmn.not.permit" /></label>&nbsp;
      <html:radio name="man200Form" property="man200OldPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_OLDPSWDKBN_OFF) %>" styleId="man200OldPswdKbn_0" onclick="" /><label for="man200OldPswdKbn_0"><gsmsg:write key="cmn.permit" /></label>&nbsp;
      </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">


    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="OK" class="btn_ok1" onClick="buttonPush('rule_settei_kakunin');">
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