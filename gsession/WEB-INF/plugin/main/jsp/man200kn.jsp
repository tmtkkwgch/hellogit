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
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="main.man200kn.1" /></title>
</head>

<body class="body_03">

<!--　BODY -->
<html:form action="/main/man200kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="man200CoeKbn" />
<html:hidden property="man200LimitKbn" />
<html:hidden property="man200UidPswdKbn" />
<html:hidden property="man200OldPswdKbn" />
<html:hidden property="man200LimitDay" />
<html:hidden property="man200Digit" />

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
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man200kn.1" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('setting');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back_pswdConf');">
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
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.1" /></span></td>
      <td class="td_type1">
        <bean:define id="digitMsg" name="man200knForm" property="man200Digit" type="java.lang.Integer" />
      <gsmsg:write key="cmn.digit.more" arg0="<%= String.valueOf(digitMsg.intValue()) %>" />
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.3" /></span></td>
      <td class="td_type1">
      <logic:equal name="man200knForm" property="man200CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_OFF) %>"><gsmsg:write key="main.man200.5" /></logic:equal>
      <logic:equal name="man200knForm" property="man200CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_EN) %>"><gsmsg:write key="main.man200.17" /></logic:equal>
      <logic:equal name="man200knForm" property="man200CoeKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_COEKBN_ON_ENS) %>"><gsmsg:write key="main.man200.18" /></logic:equal>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="man.expiration.date" /></span></td>
      <td class="td_type1">
      <logic:equal name="man200knForm" property="man200LimitKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_OFF) %>"><gsmsg:write key="cmn.unlimited" /></logic:equal>
      <logic:equal name="man200knForm" property="man200LimitKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_LIMITKBN_ON) %>"><gsmsg:write key="main.man200.11" /><bean:write name="man200knForm" property="man200LimitDay"/><gsmsg:write key="main.man200.12" /></logic:equal>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.13" /></span></td>
      <td class="td_type1">
      <logic:equal name="man200knForm" property="man200UidPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_UIDPSWDKBN_OFF) %>"><gsmsg:write key="cmn.permit" /></logic:equal>
      <logic:equal name="man200knForm" property="man200UidPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_UIDPSWDKBN_ON) %>"><gsmsg:write key="cmn.not.permit" /></logic:equal>
      </td>
    </tr>

    <tr>
      <td class="table_bg_A5B4E1" width="20%"><span class="text_bb1"><gsmsg:write key="main.man200.15" /></span></td>
      <td class="td_type1">
      <logic:equal name="man200knForm" property="man200OldPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_OLDPSWDKBN_OFF) %>"><gsmsg:write key="cmn.permit" /></logic:equal>
      <logic:equal name="man200knForm" property="man200OldPswdKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstMain.PWC_OLDPSWDKBN_ON) %>"><gsmsg:write key="cmn.not.permit" /></logic:equal>
      </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">


    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('setting');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('back_pswdConf');">
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