<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%

    jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
    String sts_sonota      = String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_ETC);
    String sts_zaiseki     = String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_IN);
    String sts_huzai       = String.valueOf(jp.groupsession.v2.cmn.GSConst.UIOSTS_LEAVE);
    String sts_sonota_str  = gsMsg.getMessage(request, "cmn.other");
    String sts_zaiseki_str = gsMsg.getMessage(request, "cmn.zaiseki");
    String sts_huzai_str   = gsMsg.getMessage(request, "cmn.absence");

%>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="zsk.zsk011.03" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../common/css/freeze.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../zaiseki/css/zaiseki.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../zaiseki/js/zsk011.js?<%= GSConst.VERSION_PARAM %>"></script>

<title>[Group Session] <gsmsg:write key="cmn.zaiseki.management" /></title>
</head>

<body class="body_03">
<html:form action="/zaiseki/zsk011">

<input type="hidden" name="CMD" value="ok">
<html:hidden property="closeFlg" />
<html:hidden property="uioUpdateUsrSid" />
<html:hidden property="uioUpdateStatus" />


<table cellpadding="4" cellspacing="0" border="0" width="100%">
<tr>
<td align="left">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td width="0%"><img src="./images/header_zaiseki_01.gif" border="0" alt="<gsmsg:write key="cmn.zaiseki.management" />"></td>
  <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.zaiseki.management" /></span></td>
  <td width="0%" class="header_white_bg_text">&nbsp;</td>
  <td width="100%" class="header_white_bg">
    <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
  </tr>
  </table>
</td>
</tr>

<tr>
<td>
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.spacer" />">
  <logic:messagesPresent message="false">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
  <tr>
  <td align="left"><html:errors/></td>
  </tr>
  </table>
  </logic:messagesPresent>

  <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
  <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.name2" /></span></td>
  <td align="left" class="td_wt" width="80%"><span class="text_base"><bean:write name="zsk011Form" property="zsk011UpdateUserName" /></span></td>
  </tr>
  <tr>
  <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="zsk.20" /></span></td>
  <td align="left" class="td_wt" width="80%">
    <span class="block_color_in"><html:radio property="zsk011Status" styleId="sts_zaiseki" value="<%= sts_zaiseki %>" /><span class="text_base2"><label for="sts_zaiseki" class="text_base2"><%= sts_zaiseki_str %></label></span></span>&nbsp;
    <span class="block_color_leave"><html:radio property="zsk011Status" styleId="sts_huzai" value="<%= sts_huzai %>" /><span class="text_base2"><label for="sts_huzai" class="text_base2"><%= sts_huzai_str %></label></span></span>&nbsp;
    <span class="block_color_etc"><html:radio property="zsk011Status" styleId="sts_sonota" value="<%= sts_sonota %>"/><span class="text_base2"><label for="sts_sonota" class="text_base2"><%= sts_sonota_str %></label></span></span>
  </td>
  </tr>
  <tr>
  <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="zsk.23" /></span></td>
  <td align="left" class="td_wt" width="80%"><html:text style="width:245px;" maxlength="30" property="zsk011Comment" styleClass="text_base" /></td>
  </tr>
  </table>

</td>
</tr>

<tr>
<td>

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="100%" align="center">
      <input type="submit" value="<gsmsg:write key="cmn.entry" />" class="btn_base1" onclick="checkStatus();">
    </td>
    </tr>
  </table>
</td>
</tr>

</table>

</html:form>

</body>
</html:html>