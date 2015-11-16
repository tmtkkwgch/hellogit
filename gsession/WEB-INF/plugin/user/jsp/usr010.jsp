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
<title>[GroupSession] <gsmsg:write key="user.44" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr010.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="exportExecute();">
<html:form action="/user/usr010">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="usr010grpmode" value="">
<input type="hidden" name="usr010grpSid" value="-1">
<html:hidden property="grpCsvOut" />

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="user.44" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right" nowrap>
      <input type="button" class="btn_user_n1" value="<gsmsg:write key="user.23" />" onClick="return buttonPush('userEdit');">
      <input type="button" class="btn_back_n3" value="<gsmsg:write key="cmn.back.admin.setting" />" onClick="return buttonPush('back');">
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <html:errors />
    <table cellpadding="5" cellspacing="0" border="0" class="td_gray" width="100%">
    <tr>
    <td width="100%" colspan="2">
    <div class="td_type24"><gsmsg:write key="cmn.grouplist" /></div>
    </td>
    </tr>
    <tr>
    <td width="100%" valign="top">
      <iframe src="../user/usr020.do" class="iframe_01" name="ctgFrame" height="300" width="100%">
      <gsmsg:write key="user.32" />
      </iframe>
    </td>
    <td width="0%" valign="top">
      <table cellpadding="2" cellspacing="0">
      <tr><td><input type="button" class="btn_add_n3" value="<gsmsg:write key="user.37" />" onClick="return usr010ChahgeProcess(this.form, 'add'), getChgctg();"></td></tr>
      <tr><td><input type="button" class="btn_edit_n2" value="<gsmsg:write key="user.133" />" onClick="return  usr010ChahgeProcess(this.form, 'edit'), getChgctg();"></td></tr>
      <tr><td><input type="button" class="btn_base1w2" value="<gsmsg:write key="user.43" />" onClick="return usr010ChahgeProcess(this.form, 'uview'), getChgctg();"></td></tr>
      <tr><td><input type="button" class="btn_csv_n3" value="<gsmsg:write key="cmn.import" />" onClick="return usr010ChahgeProcess(this.form, 'groupImp'), getChgctg();"></td></tr>
      <tr><td><input type="button" class="btn_csv_n3" value="<gsmsg:write key="cmn.export" />" onClick="return usr010ChahgeProcess(this.form, 'groupExp'), getChgctg();"></td></tr>
      </table>
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
<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>