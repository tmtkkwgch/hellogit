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
<title>[GroupSession] <gsmsg:write key="main.man310.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../main/js/man310.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">

<html:form action="/main/man310">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="imssid" value="0">
<html:hidden name="man310Form" property="man310binSid" />

<table width="100%">
<tr>
<td width="100%" align="center">

  <table width="95%" cellpadding="0" cellspacing="0">

  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../main/images/header_info_01.gif" border="0" alt="<gsmsg:write key="main.man310.1" />"></td>
    <td width="0%" class="header_white_bg_text" align="left" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.information" /></span></td>
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
  <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
  <bean:define id="tdColor" value="" />
  <% String[] tdColors = new String[] {"td_wt", "td_sch_type2"}; %>
  <% tdColor = tdColors[0]; %>

    <table width="100%" class="tl0" border="0" cellpadding="2">
    <tr>
    <td colspan="3" nowrap width="100%">
      <table width="100%">
      <tr>
      <td width="0%" valign="middle"><img src="../common/images/info_30.gif"></td>
      <td width="100%" valign="middle">
        <span class="text_bb4">
        <bean:write name="man310Form" property="man310Msg" filter="false" />
        </span>

      </td>
      </tr>
      </table>
    </td>
    </tr>
    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap width="15%"><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="<%= tdColor %>"  width="85%">
    <span class="text_base">
    <bean:write name="man310Form" property="man310Value" filter="false" />
    </span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td align="left" class="<%= tdColor %>" width="100%">
    <logic:empty name="man310Form" property="tmpFileList">&nbsp;</logic:empty>
    <logic:notEmpty name="man310Form" property="tmpFileList">
      <table cellpadding="0" cellpadding="0" border="0">
      <logic:iterate id="fileMdl" name="man310Form" property="tmpFileList">
      <logic:notEmpty name="fileMdl" property="binFileExtension">
        <bean:define id="fext" name="fileMdl" property="binFileExtension"  type="java.lang.String" />
        <%
        String dext = ((String)pageContext.getAttribute("fext",PageContext.PAGE_SCOPE));
        if (dext != null) {
            dext = dext.toLowerCase();
            if (jp.groupsession.v2.cmn.biz.CommonBiz.isViewFile(dext)) {
        %>
        <tr>
        <td colspan="2">
        <img src="../main/man310.do?CMD=tempview&imssid=<%= request.getParameter("imssid") %>&man310binSid=<bean:write name="fileMdl" property="binSid" />" name="pictImage<bean:write name="fileMdl" property="binSid" />" onload="initImageView('pictImage<bean:write name="fileMdl" property="binSid" />');">
        </td>
        </tr>
        <%
            }
        }
        %>
      </logic:notEmpty>
      <tr>
      <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
      <td class="menu_bun"><a href="javascript:fileLinkClick(<%= request.getParameter("imssid") %>, <bean:write name="fileMdl" property="binSid" />);"><span class="text_link"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></span></a></td>
      </tr>
      </logic:iterate>
      </table>
    </logic:notEmpty>
    </td>
    </tr>






    </table>

    <img src="../common/images/spacer.gif" width="1px" height="30px" border="0">

    <% tdColor = tdColors[1]; %>
    <table width="100%" class="tl0" border="0" cellpadding="2">

   <tr>
    <td colspan="2" class="td_type3" nowrap width="15%"><span class="text_bb1"><gsmsg:write key="main.exposed" /></span></td>
    <td align="left" class="<%= tdColor %>" width="85%">
    <span class="text_base">
    <logic:notEmpty name="man310Form" property="man310KoukaiList">
    <logic:iterate id="memName" name="man310Form" property="man310KoukaiList">
        <bean:write name="memName" property="label" /><br>
    </logic:iterate>
    </logic:notEmpty>
    </span>
    </td>
    </tr>
    <tr>
    <td colspan="2" class="td_type3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <logic:equal name="man310Form" property="man310UsrJkbn" value="9">
    <del>
    <bean:write name="man310Form" property="man310NameSei" />&nbsp;<bean:write name="man310Form" property="man310NameMei" />
    </del>
    </logic:equal>
    <logic:notEqual name="man310Form" property="man310UsrJkbn" value="9">
    <bean:write name="man310Form" property="man310NameSei" />&nbsp;<bean:write name="man310Form" property="man310NameMei" />
    </logic:notEqual>
    </span>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td align="right" class="td_btn">
  <img src="../common/images/spacer.gif" width="1px" height="25px" border="0">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
  </td>
  </tr>

  </table>

</td>
</tr>
</table>

<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>
</html:form>
</body>
</html:html>