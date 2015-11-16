<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.rss020.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../rss/js/rss020.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<!-- body class="body_03" onload="initDispDescription(<bean:write name='rss020Form' property='rssAuth' />);" -->
<body class="body_03">

<html:form action="/rss/rss020">

<input type="hidden" name="CMD" value="">
<html:hidden name="rss020Form" property="rssCmdMode" />
<html:hidden name="rss020Form" property="rssSid" />

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
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rss.3" />-<gsmsg:write key="rss.rss020.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="50%"></td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
      <td class="header_glay_bg" width="50%">
      <input type="button" value="ＯＫ" class="btn_ok1" onClick="buttonPush('moveRssData');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rssList');"></td>
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
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rss.feedurl" /></span><span class="text_r2">※</span></td>
      <td align="left" class="td_type20" width="100%">
        <html:text name="rss020Form" property="rssFeedUrl" style="width:633px;" maxlength="2000" />
      </td>
      </tr>

<!----------------
      <tr>
      <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="rss.28" /></span><span class="text_r2">※</span></td>
      <td align="left" class="td_type20" width="100%">
        <span class="text_gray">
        <html:radio styleId="AuthUse" name="rss020Form" property="rssAuth" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_BASIC_AUTH_USE) %>" onclick="dispDescription()" /><label for="AuthUse"><gsmsg:write key="rss.29" /></label>
        <html:radio styleId="AuthNotUse" name="rss020Form" property="rssAuth" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_BASIC_AUTH_NOT_USE) %>" onclick="notDispDescription()" /><label for="AuthNotUse"><gsmsg:write key="rss.30" /></label>
        </span>

        <div id="basicAuthDsp" class="description_text_dsp2">
        <table class="tl0">
        <tr>
        <th align="left" width="0%" nowrap><span class="text_base2">ID</span></th>
        <th align="center" width="0%" nowrap><span class="text_base2">：</span></th>
        <td align="left" width="100%"><span class="text_base2"><html:text name="rss020Form" property="rssAuthId" size="50" maxlength="50" /></span></td>
        </tr>

        <tr>
        <th align="left" width="0%" nowrap><span class="text_base2"><gsmsg:write key="user.117" /></span></th>
        <th align="center" width="0%" nowrap><span class="text_base2">：</span></th>
        <td align="left" width="100%"><span class="text_base2"><html:text name="rss020Form" property="rssAuthPswd" size="50" maxlength="50" /></span></td>
        </tr>
        </table>
        </div>
      </td>
      </tr>
----------------->
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
      <td align="right">
      <input type="button" value="ＯＫ" class="btn_ok1" onClick="buttonPush('moveRssData');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('rssList');"></td>
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