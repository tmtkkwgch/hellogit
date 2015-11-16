<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String zskOn = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ZAISEKI_ON); %>
<% String zskOff = String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.ZAISEKI_OFF); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="tcd.50" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript" src="../timecard/js/tcdmain.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">
<html:form action="/timecard/tcdmain">
<input type="hidden" name="CMD" value="">


    <logic:equal name="tcdmainForm" property="dspFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>">
    <!--非表示-->
    </logic:equal>

    <logic:notEqual name="tcdmainForm" property="dspFlg" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstCommon.MAIN_NOT_DSP) %>">

    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <tr>
    <td align="left" class="header_7D91BD_right" colspan="2">
    <img src="../timecard/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="tcd.50" />"><a href="<bean:write name="tcdmainForm" property="tcdTopUrl" />"><gsmsg:write key="tcd.50" /></a>
    </td>
    </tr>

    <logic:equal name="tcdmainForm" property="tcdStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.STATUS_FREE) %>">
    <tr>
    <td width="30%" class="td_type30" align="center"><span class="text_tcd"><gsmsg:write key="tcd.tcdmain.03" /></span></td>
    <td width="70%" class="td_type1" align="center">
    <table cellspacing="0" cellpadding="0">
    <tr><td><input type="button" value="　　　　<gsmsg:write key="tcd.tcdmain.06" />　　　　" class="btn_base0" onClick="editTimecard('tcdEdit');"></td></tr>
    <tr><td><div class="text_base"><html:checkbox styleId="zsk_kbn" name="tcdmainForm" property="zaisekiSts" value="<%= zskOn %>" /><label for="zsk_kbn"><gsmsg:write key="tcd.tcdmain.04" /></label></div></td></tr>
    </table>


    </td>
    </tr>
    <tr>
    <td class="td_type30" align="center"><span class="text_tcd"><gsmsg:write key="tcd.tcdmain.02" /></span></td>
    <td class="td_type1" align="center">&nbsp;</td>
    </tr>

    </logic:equal>

    <logic:equal name="tcdmainForm" property="tcdStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.STATUS_FIN) %>">
    <tr>
    <td width="30%" class="td_type30" align="center"><span class="text_tcd"><gsmsg:write key="tcd.tcdmain.03" /></span></td>
    <td width="70%" class="td_type1" align="center"><span class="text_tcd"><bean:write name="tcdmainForm" property="tcdStartTime" /></span></td>
    </tr>
    <tr>
    <td class="td_type30" align="center"><span class="text_tcd"><gsmsg:write key="tcd.tcdmain.02" /></span></td>
    <td class="td_type1" align="center"><span class="text_tcd"><bean:write name="tcdmainForm" property="tcdStopTime" /></span></td>
    </tr>
    </logic:equal>

    <logic:equal name="tcdmainForm" property="tcdStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.STATUS_HAFE) %>">
    <tr>
    <td width="30%" class="td_type30" align="center"><span class="text_tcd"><gsmsg:write key="tcd.tcdmain.03" /></span></td>
    <td width="70%" class="td_type1" align="center"><span class="text_tcd"><bean:write name="tcdmainForm" property="tcdStartTime" /></span></td>
    </tr>
    <tr>
    <td class="td_type30" align="center"><span class="text_tcd"><gsmsg:write key="tcd.tcdmain.02" /></span></td>
    <td class="td_type1" align="center">
    <table cellspacing="0" cellpadding="0">
    <tr><td><input type="button" value="　　　　<gsmsg:write key="tcd.tcdmain.05" />　　　　" class="btn_base0" onClick="editTimecard('tcdEdit');"></td></tr>
    <tr><td><div class="text_base"><html:checkbox styleId="zsk_kbn" name="tcdmainForm" property="zaisekiSts" value="<%= zskOn %>" /><label for="zsk_kbn"><gsmsg:write key="tcd.tcdmain.01" /></label></div></td></tr>
    </td>
    </tr>

    </logic:equal>

    <logic:equal name="tcdmainForm" property="tcdStatus" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstTimecard.STATUS_FAIL) %>">
    <tr>
    <td class="td_type1" align="left" colspan="2">
    <span class="text_tcd"><gsmsg:write key="tcd.tcdmain.07" /></span>
    </td>
    </tr>
    </logic:equal>

    </table>
    </logic:notEqual>
</html:form>
</body>
</html:html>