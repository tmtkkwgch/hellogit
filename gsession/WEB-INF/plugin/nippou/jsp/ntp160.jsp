<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="ntp.1" /></title>
<script language="JavaScript" src="../common/js/cmd.js"></script>
<script language="JavaScript" src="../common/js/prototype.js"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css" type="text/css">
<link rel=stylesheet href="../nippou/css/nippou.css" type="text/css">

</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/nippou/ntp160">

<input type="hidden" name="CMD" value="">

<logic:notEmpty name="ntp160Form" property="ntp160SortKey" scope="request">
<logic:iterate id="ngpSid" name="ntp160Form" property="ntp160SortKey" scope="request">
  <input type="hidden" name="ntp160SortKey" value="<bean:write name="ngpSid" />">
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
    <td width="0%" class="header_white_bg_text" nowrap>[ <gsmsg:write key="ntp.62" /><gsmsg:write key="ntp.165" /><gsmsg:write key="cmn.list" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="ntp.1" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('backNtp160');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="left" style="padding-bottom: 5px;">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr width="50%">
    <td align="left">
    <span class="text_bb1"><gsmsg:write key="ntp.61" />：</span>
    <html:select name="ntp160Form" property="ntp160NgySid" styleClass="select01" onchange="return buttonPush('changeGyomu');" style="width: 220px;">
    <logic:notEmpty name="ntp160Form" property="ntp160GyomuList">
    <html:optionsCollection name="ntp160Form" property="ntp160GyomuList" value="value" label="label" />
    </logic:notEmpty>
    </html:select>
    </td>
    <td align="right">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" name="btn_upper" onClick="return buttonPush('up');">
    <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" name="btn_downer" onClick="return buttonPush('down');">
    </td>
    </table>
  </td>
  </tr>

  <tr>
  <td align="left">

    <table class="tl0 table_td_border2" cellpadding="5" cellspacing="0" border="0" width="100%">

    <tr>
    <th align="center" class="table_bg_7D91BD" width="5%" nowrap><span class="text_tlw"></span></th>
    <th align="center" class="table_bg_7D91BD" width="0%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.126" /></span></th>
    <th align="left" class="table_bg_7D91BD" width="95%" nowrap><span class="text_tlw"><gsmsg:write key="ntp.127" /></span></th>
    </tr>
    <logic:notEmpty name="ntp160Form" property="ntp160ProcessList">
    <bean:define id="tdColor" value="" />

    <% String[] tdColors = new String[] {"td_type1", "td_type_usr"}; %>

    <logic:iterate id="processMdl" name="ntp160Form" property="ntp160ProcessList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <bean:define id="ngpSort" name="processMdl" property="ngpSid" />

    <tr align="center" class="<%= tdColor %>">
    <td align="center">
    <html:radio property="ntp160SortRadio" value="<%= String.valueOf(ngpSort) %>" />
    </td>
    <!-- プロセスコード -->
    <td align="left"><span class="text_base7"><bean:write name="processMdl" property="ngpCode" /></span></td>
    <!-- <gsmsg:write key="ntp.127" /> -->
    <td align="left"><span class="text_base7"><bean:write name="processMdl" property="ngpName" /></span></td>
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
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('backNtp160');">
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