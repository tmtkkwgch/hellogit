<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../ipkanri/js/ipkanri.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../ipkanri/css/ip.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[GroupSession] <gsmsg:write key="ipk.12" /></title>
</head>
<body class="body_03">
<html:form action="/ipkanri/ipk010">
<input type="hidden" name="cmd" value="search">
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<table align="center" cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">
  <table align="center" class="table_kotei2">  <tr>
  <td width="100%" align="center">
    <table cellpadding="0" class="table_kotei2">
    <tr>
    <td width="0%"><img src="../ipkanri/images/header_ipkanri_icon_01.gif" border="0" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.ipkanri" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="ipk.12" /> ]</td>
    <td width="100%" class="header_white_bg_text"></td>
    <logic:equal value="true" name="ipk010Form" property="allAdm">
    <td width="0%" class="header_white_bg"><input type="button" class="btn_setting_admin_n1" name="new" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush2('admin');"></td>
    </logic:equal>
    <td width="0%"><img src="../common/images/header_white_end.gif" alt="<gsmsg:write key="cmn.ipkanri" />"></td>
    </tr>
    </table>
  </td>
  </tr>
  <tr>
  <td>
  <html:hidden property="netSid" />
    <table class="table_ip" cellspacing="0px" cellpadding="0px" width="100%">

    <tr>
    <td colspan="5" class="td_button">
    <img src="../common/images/search.gif" alt="<gsmsg:write key="cmn.search" />" class="img_bottom">
    <html:text name="ipk010Form" property="ipk070KeyWord" style="width:183px;" maxlength="50" />
    <input type="submit" name="btn_search" class="btn_base1s" value="<gsmsg:write key="cmn.search" />" onClick="buttonPush2('search');">
    <logic:equal value="true" name="ipk010Form" property="allAdm">
    <input type="button" class="btn_add_n1" name="new" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('networkAdd', '');">
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td width="16%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="ipk.1" /></span></td>
    <td width="16%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="ipk.2" /></span></td>
    <td width="16%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="ipk.3" /></span></td>
    <td width="16%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="cmn.comment" /></span></td>
    <td width="8%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="cmn.detail" /></span></td>
    </tr>

    <logic:notEmpty name="ipk010Form" property="ipkNetList">
    <logic:iterate id="param" name="ipk010Form" property="ipkNetList" indexId="idx">
    <bean:define id="backclass" value="td_type20_" />
    <bean:define id="backlinkclass" value="text_link3_" />
    <bean:define id="backpat" value="<%= String.valueOf((idx.intValue() % 2) + 1) %>" />
    <bean:define id="back" value="<%= String.valueOf(backclass) + String.valueOf(backpat) %>" />
    <bean:define id="backlink" value="<%= String.valueOf(backlinkclass) + String.valueOf(backpat) %>" />

    <tr>
    <td class="<%= String.valueOf(backlink) %>" align="left" nowrap><a href="#" onClick="buttonPush('ipList', '<bean:write name="param" property="netSid" />');">
    <img src="../ipkanri/images/ipk_icon.gif" alt="<gsmsg:write key="cmn.ipkanri" />" class="ipkanri_icon">&nbsp;<bean:write name="param" property="netName" /></a></td>
    <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="param" property="netNetad" /></td>
    <td class="<%= String.valueOf(back) %>" nowrap><bean:write name="param" property="netSabnet" /></td>
    <td class="<%= String.valueOf(back) %>" valign="top"><bean:write name="param" property="netMsg" filter="false"/></td>
    <td class="<%= String.valueOf(back) %>" align="center"><br>
    <input type="button" class=btn_base1 name="edit" value="<gsmsg:write key="cmn.detail" />" onClick="buttonPush('networkEdit', '<bean:write name="param" property="netSid" />');"><br><br>
    <logic:equal name="param" property="netAdm" value="true" >
    <input type="button" class="btn_dell_n1" name="delete" value="<gsmsg:write key="cmn.delete" />" onClick="buttonPush('networkDelete', '<bean:write name="param" property="netSid" />');"><br><br>
    </logic:equal>
    </td>
    </tr>

    </logic:iterate>
    </logic:notEmpty>

    <tr>
    <td colspan="5" height="45px" valign="bottom" align="right">
    <logic:equal value="true" name="ipk010Form" property="allAdm">
    <input type="button" class="btn_add_n1" name="new" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('networkAdd', '');">
    </logic:equal>
    </td>
    </tr>

    </table>
  </td>
  </tr>

  </table>
</td>
</tr>

<tr>
<td>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</td>
</tr>

</table>
</html:form>
</body>
</html:html>