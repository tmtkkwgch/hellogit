<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="Shift_JIS">
<title>[Group Session] <gsmsg:write key="cmn.admin.setting.menu" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>

</head>
<body class="body_03">
<html:form action="/anpi/anp121kn">
<!-- BODY -->
<input type="hidden" name="CMD">

<html:hidden property="backScreen" />
<html:hidden property="anp110SelectUserSid" />
<html:hidden property="anp110SortKeyIndex" />
<html:hidden property="anp110OrderKey" />
<html:hidden property="anp110NowPage" />
<html:hidden property="anp110DspPage1" />
<html:hidden property="anp110DspPage2" />
<html:hidden property="anp121knFileName" />

<html:hidden property="anp110SelectGroupSid" />
<html:hidden property="anp110SelectMailFilter" />
<html:hidden property="anp110SelectTellFilter" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="anp.plugin"/><gsmsg:write key="anp.anp121kn.01"/> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
            <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_csv_n1" onClick="buttonPush('anp121knimport');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp121knback');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
        </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <!-- エラーメッセージ -->
    <div style="text-align:left">
    <html:errors/>
    </div>

    <table cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
    <tr>
    <td><span class="text_r1"><gsmsg:write key="cmn.capture.file.sure" /></span></td>
    </tr>
    </table>

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

        <tr>
	    <td valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap>
	      <span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2"><gsmsg:write key="cmn.comments" /></span>
	    </td>
	    <td valign="middle" align="left" class="td_wt" width="100%">
	      <!-- 取込みファイル -->
          <span class="text_base"><bean:write name="anp121knForm" property="anp121knFileName"/></span>
	    </td>
	    </tr>

    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
        <td align="right">
            <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_csv_n1" onClick="buttonPush('anp121knimport');">
            <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('anp121knback');">
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