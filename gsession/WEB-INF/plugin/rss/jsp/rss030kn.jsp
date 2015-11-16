<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.rss030kn.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/rss/rss030kn">

<input type="hidden" name="CMD" value="">
<html:hidden name="rss030Form" property="rssCmdMode" />
<html:hidden name="rss030Form" property="rssSid" />
<html:hidden name="rss030knForm" property="rssTitle" />
<html:hidden name="rss030knForm" property="rssBeforeFeedUrl" />
<html:hidden name="rss030knForm" property="rssFeedUrl" />
<html:hidden name="rss030knForm" property="rssUrl" />
<html:hidden name="rss030knForm" property="rss030ViewCnt" />
<html:hidden name="rss030knForm" property="rss030mainView" />
<html:hidden name="rss030knForm" property="rssAuth" />
<html:hidden name="rss030knForm" property="rssAuthId" />
<html:hidden name="rss030knForm" property="rssAuthPswd" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../rss/images/header_rssl03_01.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rss.3" /></span></td>
      <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rss.3" />-<gsmsg:write key="rss.rss030kn.3" /> ]</td>
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rss.14" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="rss030knForm" property="rssTitle" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rss.feedurl" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="rss030knForm" property="rssFeedUrl" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rss.16" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="rss030knForm" property="rssUrl" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.number.display" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="rss030knForm" property="rss030ViewCnt" /><gsmsg:write key="cmn.number" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.main.view" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="rss030knForm" property="strMainView" /></td>
    </tr>

<!----------------------------
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rss.28" /></span></td>
    <td align="left" class="td_type20" width="100%">
      <logic:equal name="rss030knForm" property="rssAuth" value="1">
      <table class="tl0">
      <tr>
      <td align="left" colspan="3"><span class="text_base2"><gsmsg:write key="rss.29" /></span></td>
      </tr>

      <tr>
      <th align="left"><span class="text_base2">ID</span>&nbsp;</th>
      <th align="center"><span class="text_base2">:</span></th>
      <td><span class="text_base2"><bean:write name="rss030knForm" property="rssAuthId" /></span></td>
      </tr>

      <tr>
      <th align="left"><span class="text_base2"><gsmsg:write key="user.117" /></span>&nbsp;</th>
      <th align="center"><span class="text_base2">:</span></th>
      <td><span class="text_base2"><bean:write name="rss030knForm" property="rssAuthPswd" /></span></td>
      </tr>
      </table>
      </logic:equal>
      <logic:notEqual name="rss030knForm" property="rssAuth" value="1">
        <span class="text_base2"><gsmsg:write key="rss.30" /></span>
      </logic:notEqual>
    </td>
    </tr>
------------------------------->
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
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