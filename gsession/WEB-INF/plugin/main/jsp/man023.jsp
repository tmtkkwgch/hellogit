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
<title>[Group Session] <gsmsg:write key="main.man023.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../main/js/man023.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/main/man023">
<html:hidden property="man020DspYear" />
<html:hidden property="editHltSid" />
<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man023.1" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n1" onClick="buttonPush('import')">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('backHoliday')">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">

      <bean:define id="dspYear" name="man023Form" property="man020DspYear" type="java.lang.String" />
      <input type="button" value="<gsmsg:write key="cmn.reflected.holiday.year" arg0="<%= dspYear %>" />" class="btn_base1w" onClick="buttonPush('reflect')">
      <input type="button" value="<gsmsg:write key="cmn.add.template" />" class="btn_add_n3" onClick="buttonPush('addTemp')">
      <input type="button" value="<gsmsg:write key="main.man023.3" />" class="btn_dell_n2" onClick="buttonPush('delTemp')">
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
      <span class="TXT02"><html:errors/></span>
    </logic:messagesPresent>

    <table class="tl0 table_td_border" cellpadding="0" cellspacing="0" border="0" width="100%">

    <tr class="td_type24">
    <th width="0%"><html:checkbox name="man023Form" property="man023CheckAll" value="1" onclick="changeChk();" /></th>
    <th width="30%"><gsmsg:write key="cmn.date3" /></th>
    <th width="70%"><gsmsg:write key="cmn.holiday.name" /></th>
    <th width="0%">&nbsp;</th>
    </tr>

    <% int length = 0; %>
    <logic:notEmpty name="man023Form" property="man023TemplateList" >
    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type3"}; %>
      <logic:iterate id="pageTemplateRecBean" name="man023Form" property="man023TemplateList" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)]; %>

    <tr>
    <td class="<%= tdColor %>" align="center">
    <html:multibox name="man023Form" property="man023hltSid">
      <bean:write  name="pageTemplateRecBean" property="hltSid" />
    </html:multibox>

    </td>

    <th class="<%= tdColor %>" align="center" nowrap><bean:write name="pageTemplateRecBean" property="viewDate" scope="page"/></td>
    <td class="<%= tdColor %>"><bean:write name="pageTemplateRecBean" property="hltName" scope="page"/></td>
    <td class="<%= tdColor %>">
    <input type="button" class="btn_change" name="btnChange" value="<gsmsg:write key="cmn.change" />" onClick="setTemplateNo('<bean:write name="pageTemplateRecBean" property="hltSid" scope="page"/>'),buttonPush('editTemp');">
    </td>
    </tr>

    <% length++; %>
    </logic:iterate>
    </logic:notEmpty>


<input type="hidden" name="length" value="<%= length %>">

    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" value="<gsmsg:write key="cmn.reflected.holiday.year" arg0="<%= dspYear %>" />" class="btn_base1w" onClick="buttonPush('reflect')">
      <input type="button" value="<gsmsg:write key="cmn.add.template" />" class="btn_add_n3" onClick="buttonPush('addTemp')">
      <input type="button" value="<gsmsg:write key="main.man023.3" />" class="btn_dell_n2" onClick="buttonPush('delTemp')">
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