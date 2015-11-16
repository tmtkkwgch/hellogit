<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>[GroupSession] <gsmsg:write key="cmn.help" /><gsmsg:write key="main.src.man250.16" /><gsmsg:write key="cmn.list" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../help/css/help.css" type="text/css">
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js'></script>
<script type="text/javascript" src="../common/js/cmd.js"></script>
<script type="text/javascript" src="../help/js/help.js"></script>
<script type="text/javascript">
<!--
  function myHeaderTextChange(value) {
      document.getElementById('header_text').innerHTML = value;
  }
//-->
</script>
</head>
<body>

<html:form action="/help/hlp002" target="body" method="post">
<input type="hidden" name="CMD" value="search">

<div id="container"><a name="top"></a>
    <table cellpadding="0" summary="<gsmsg:write key="main.src.man250.16" /><gsmsg:write key="cmn.list" />" cellspacing="0" width="100%">
        <tr>
        <td width="0%"><a href="../help/hlp010.do" target="body" onclick="myHeaderTextChange('[ <gsmsg:write key="main.src.man250.16" /><gsmsg:write key="cmn.list" /> ]');"><img src="../help/images/header_plugin_list_01.gif" border="0" alt="<gsmsg:write key="cmn.help" />"></a></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.help" /></span></td>
        <td width="0%" class="header_white_bg_text"><span id="header_text">[ <gsmsg:write key="cmn.help.function" /> ]</span></td>
        <td width="100%" class="header_white_bg" nowrap>
          <html:text name="hlp002Form" property="hlp002SearchText" maxlength="100" style="width:155px;" />
          <input type="submit" value="<gsmsg:write key="cmn.search" />" class="btn_base1s" onclick="myHeaderTextChange('[ <gsmsg:write key="cmn.search" /> ]'); buttonPush('search');">
        </td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.help" />"></td>
        </tr>
    </table>
    <img src="../help/images/spacer.gif" width="100" height="10" alt="">
</div>

</html:form>
</body>
</html:html>