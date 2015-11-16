<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
  int add = jp.groupsession.v2.man.man100.Man100Biz.MODE_ADD;
  int edit = jp.groupsession.v2.man.man100.Man100Biz.MODE_EDIT;
%>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="main.man002.26" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man100.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/main/man100">

<input type="hidden" name="CMD" value="">
<input type="hidden" name="man100ProcMode" value="">
<input type="hidden" name="man100EditPosSid" value="">

<logic:notEmpty name="man100Form" property="posList" scope="request">
  <logic:iterate id="sort" name="man100Form" property="posList" scope="request">
    <input type="hidden" name="man100KeyList" value="<bean:write name="sort" property="posValue" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.26" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_inport" class="btn_csv_n1" value="<gsmsg:write key="cmn.import" />" onClick="buttonPush('posImport');">
          <input type="button" name="btn_add" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="buttonSubmit('add', '<%= add %>', '-1');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('admin_back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>
  <tr>
  <td>

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td>
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />"  onClick="buttonPush('up');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />"  onClick="buttonPush('down');">
&nbsp;&nbsp;<span class="text_base"><gsmsg:write key="main.man100.1" /></span>
      </td>
    </tr>
    </table>

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <th class="table_bg_7D91BD" width="6%" nowrap>&nbsp;</th>
    <th align="center" class="table_bg_7D91BD" width="10%"><span class="text_tlw"><gsmsg:write key="user.src.50" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="25%"><span class="text_tlw"><gsmsg:write key="cmn.job.title" /></span></th>
    <th align="center" class="table_bg_7D91BD" width="59%"><span class="text_tlw"><gsmsg:write key="cmn.memo" /></span></th>
    </tr>

    <logic:notEmpty name="man100Form" property="posList" scope="request">
    <logic:iterate id="posMdl" name="man100Form" property="posList" scope="request" indexId="idx">

    <bean:define id="mod" value="0" />
    <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type1" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
      <bean:define id="tblColor" value="td_type_usr" />
    </logic:notEqual>

    <bean:define id="posValue" name="posMdl" property="posValue" />

      <tr class="<bean:write name="tblColor" />">
      <td align="center" nowrap><html:radio property="man100SortRadio" value="<%= String.valueOf(posValue) %>" /></td>
      <td align="left"><bean:write name="posMdl" property="posCode"/></td>
      <td align="left">
        <a href="javascript:void(0);" onclick="return buttonSubmit('posname', '<%= edit %>', '<bean:write name="posMdl" property="posSid" />');"><span class="text_link"><bean:write name="posMdl" property="posName" /></span></a>
      </td>
      <td align="left"><bean:write name="posMdl" property="posBiko" filter="false" /></td>
      </tr>

    </logic:iterate>
    </logic:notEmpty>

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