<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String maxLengthSyosai = String.valueOf(1000);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery.bgiframe.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../schedule/jquery_ui/js/jquery-ui-1.8.14.custom.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp260.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/glayer.js"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css" type="text/css">
<link rel=stylesheet href='../common/css/glayer.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../address/css/address.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/jquery/jquery-theme.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/jquery_ui/css/jquery.ui.dialog.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/nippou/ntp260">

<input type="hidden" name="CMD" value="">

<!--@BODY -->

<table width="530px" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="ntp.1" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="ntp.147" /> ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    </tr>
    </table>


  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <logic:messagesPresent message="false">
  <tr><td align="left"><span class="TXT02"><html:errors/></span></td></tr>
  <tr><td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td></tr>
  </logic:messagesPresent>

  <tr>
  <td align="left">

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td align="left" class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.92" /></span></td>
    <td align="left" class="td_type20" width="85%"><bean:write name="ntp260Form" property="ntp260TemplateName" /></td>
    </tr>

    <tr>
    <td align="left" class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.148" /></span></td>
    <td align="left" class="td_type20" width="90%">
      <logic:notEmpty name="ntp260Form" property="ntp260ItemList">
        <logic:iterate id="itmMdl" name="ntp260Form" property="ntp260ItemList">
          <bean:write name="itmMdl" /></br>
        </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td align="left" class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.12" /></span></td>
    <td align="left" class="td_type20" width="90%">
      <logic:notEmpty name="ntp260Form" property="ntp260TargetList">
        <logic:iterate id="trgMdl" name="ntp260Form" property="ntp260TargetList">
          <bean:write name="trgMdl" property="ntgName" /></br>
        </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td align="left" class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="ntp.149" /></span></td>
    <td align="left" class="td_type20" width="90%">
      <logic:notEmpty name="ntp260Form" property="ntp260GpUsrList">
        <logic:iterate id="memMdl" name="ntp260Form" property="ntp260GpUsrList">
          <bean:write name="memMdl" property="label" /></br>
        </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td align="left" class="table_bg_A5B4E1" width="10%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content" /></span></td>
    <td align="left" class="td_type20" width="90%">
      <bean:write name="ntp260Form" property="ntp260TemplateDetail" filter="false" />
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="center" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
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


</body>
</html:html>