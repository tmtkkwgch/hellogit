<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../nippou/js/ntp180.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css?<%= GSConst.VERSION_PARAM %>" type="text/css">

</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp180">

<input type="hidden" name="CMD" value="">
<html:hidden property="ntp180NkhSid" />
<html:hidden property="ntp180ProcMode" />

<logic:notEmpty name="ntp180Form" property="ntp180KthouhouList" scope="request">
  <logic:iterate id="sort" name="ntp180Form" property="ntp180KthouhouList" scope="request">
    <input type="hidden" name="ntp180sortLabel" value="<bean:write name="sort" property="kthValue" />">
  </logic:iterate>
</logic:notEmpty>
<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../nippou/images/header_nippou_02.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.121" /><gsmsg:write key="cmn.list" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('add','-1');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp180');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="left">

    <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
      <td>
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="buttonPush2('upHouhouData');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="buttonPush2('downHouhouData');">
      </td>
    </tr>
    </table>
    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th align="center" class="table_bg_7D91BD" width="5%" nowrap><span class="text_tlw"></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.118" /></span></th>
    <th align="left" class="table_bg_7D91BD" width="90%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.121" /><gsmsg:write key="cmn.name3" /></span></th>
    </tr>
    <logic:notEmpty name="ntp180Form" property="ntp180KthouhouList">
    <bean:define id="tdColor" value="" />

    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="houhouMdl" name="ntp180Form" property="ntp180KthouhouList" indexId="idx">
    <bean:define id="radiovalue" name="houhouMdl" property="kthValue" />
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr align="center" class="<%= tdColor %>">
    <!-- ラジオボタン -->
    <td align="center">
    <html:radio property="ntp180sortHouhou" value="<%= String.valueOf(radiovalue) %>"/>
    </td>
    <!-- 活動方法コード -->
    <td align="left">
    <a href="#" onclick="return buttonSubmit('edit','<bean:write name="houhouMdl" property="kthSid" />') ">
    <span class="text_link"><bean:write name="houhouMdl" property="kthCode" /></span>
    </a>
    </td>

    <!-- 活動方法名 -->
    <td align="left">
    <a href="#" onclick="return buttonSubmit('edit','<bean:write name="houhouMdl" property="kthSid" />') ">
    <span class="text_link"><bean:write name="houhouMdl" property="kthName" /></span>
    </a>
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="left">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.add" />" onClick="return buttonSubmit('add','-1');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush2('backNtp180');">
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