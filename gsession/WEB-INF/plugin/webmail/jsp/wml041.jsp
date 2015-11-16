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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../webmail/js/wml041.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../webmail/css/webmail.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="wml.wml041.01" /></title>
</head>

<body class="body_03" onload="wml041Init();" onunload="wml041Close();">

<html:form action="webmail/wml041">

<input type="hidden" name="wml041CMD">
<html:hidden property="wml041mode" />
<html:hidden property="wml041No" />
<html:hidden property="wml041initFlg" />
<html:hidden property="wml041endFlg" />

<table width="545px" align="center">
  <tr>
  <td class="table_bg_A5B4E1">
      <span class="text_base3"><gsmsg:write key="wml.wml041.01" /></span>
  </td>
  </tr>

  <logic:messagesPresent message="false">
  <tr>
  <td align="left"><span class="TXT02" id="cmn110ErrArea"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td>
    <table style="width:99%; margin-top:15px;">
    <tr>
    <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_base3"><gsmsg:write key="cmn.title" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td width="80%"  class="td_type20">
      <html:text name="wml041Form" property="wml041title" maxlength="100" style="width:393px;" />
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="wml.34" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span></td>
    <td align="left" class="td_type20">
        <html:textarea name="wml041Form" property="wml041sign" rows="10" style="width:393px;" />
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td align="right" style="padding-top: 8px; padding-right: 5px;">
    <logic:equal name="wml041Form" property="wml041mode" value="1">
      <input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_base1" onClick="wml041Entry();">
    </logic:equal>
    <logic:notEqual name="wml041Form" property="wml041mode" value="1">
      <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_base1" onClick="wml041Entry();">
    </logic:notEqual>
    <input type="button" value="<gsmsg:write key="cmn.cancel" />" class="btn_base1" onClick="wml041thisClose();">
  </td>
  </tr>
</table>


</html:form>
</div>
</body>
</html:html>