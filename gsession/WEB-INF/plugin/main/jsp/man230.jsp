<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="main.man002.66" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/main/man230">

<input type="hidden" name="CMD" value="">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="50%" class="header_ktool_bg_text2">[ <gsmsg:write key="main.man002.66" /> ]</td>
    <td width="50%" class="header_ktool_bg" align="right"></td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.reload" />" class="btn_reload_n1" onClick="buttonPush('reload');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backKtool');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%" class="tl0" border="0" cellpadding="5">
      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="main.man002.66" /></span></td>
      <td align="left" class="td_wt2">
        <table border="0" cellpadding="2">
        <tr>
        <td width="0%" nowrap><span class="text_gray">・Version</span></td>
        <td width="100%"><span class="txt_status"><%= jp.groupsession.v2.cmn.GSConst.VERSION %>&nbsp;(<bean:write name="man230Form" property="man230GSVersionDB" />)</span></td>
        </tr>

        <tr>
        <td width="0%" nowrap><span class="text_gray">・OS</span></td>
        <td width="100%"><span class="txt_status"><bean:write name="man230Form" property="man230Os" /></span></td>
        </tr>

        <tr>
        <td nowrap><span class="text_gray">・<gsmsg:write key="main.man230.2" /></span></td>
        <td><span class="txt_status"><bean:write name="man230Form" property="man230J2ee" /></span></td>
        </tr>

        <tr>
        <td nowrap><span class="text_gray">・Java</span></td>
        <td><span class="txt_status"><bean:write name="man230Form" property="man230Java" /></span></td>
        </tr>

        <tr>
        <td nowrap><span class="text_gray">・<gsmsg:write key="main.man230.4" /></span></td>
        <td><span class="txt_status"><bean:write name="man230Form" property="man230MemoryUse" />&nbsp;(<bean:write name="man230Form" property="man230MemoryUsePer" />%)</span></td>
        </tr>

        <tr>
        <td nowrap><span class="text_gray">・<gsmsg:write key="main.man230.5" /></span></td>
        <td><span class="txt_status"><bean:write name="man230Form" property="man230MemoryMax" /></span></td>
        </tr>

        <logic:equal name="man230Form" property="man230DbKbn" value="0">
        <tr>
        <td nowrap><span class="text_gray">・<gsmsg:write key="main.man230.6" /></span></td>
        <td><span class="txt_status"><bean:write name="man230Form" property="man230FreeDSpace" /></span></td>
        </tr>
        </logic:equal>

        <!-- space  -->
        <tr>
        <td width="0%" nowrap><span class="text_gray"><img src="../common/images/spacer.gif" width="1px" height="30px" border="0"></span></td>
        <td width="100%"><span class="txt_status"></span></td>
        </tr>

        <tr>
        <td width="0%" nowrap><span class="text_gray">・<gsmsg:write key="main.man230.7" /></span></td>
        <td width="100%"><span class="txt_status"><bean:write name="man230Form" property="man230ConnectionCount" /></span></td>
        </tr>


        </table>
      </td>
      </tr>

    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">




    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backKtool');"></td>
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>