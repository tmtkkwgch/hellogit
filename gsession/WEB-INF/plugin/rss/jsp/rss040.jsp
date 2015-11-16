<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.rss040.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../rss/js/rss040.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/rss/rss040">
<input type="hidden" name="CMD" value="">
<input type="hidden" name="rssSid" value="">
<input type="hidden" name="rss040feedTitle" value="">

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
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="rss.3" />-<gsmsg:write key="cmn.entry.rankings" /> ]</td>
        <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backPage');"></span>
        </td>
      </tr>
    </table>

    <logic:messagesPresent message="false">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    </logic:messagesPresent>

    <logic:notEmpty name="rss040Form" property="resultList">
    <bean:size id="count1" name="rss040Form" property="pageLabelList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="rss040page1" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="rss040Form" property="pageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table width="100%" cellpadding="3" cellspacing="0" border="0" align="center">
      <logic:notEmpty name="rss040Form" property="resultList">
      <tr>
      <th width="100%" class="table_bg_7D91BD" colspan="3"><span class="text_tlw"><gsmsg:write key="cmn.entry.rankings" /></span></th>

      <logic:iterate id="resultMdl" name="rss040Form" property="resultList" indexId="idx">
      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"rss_td1", "rss_td2"}; %>
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>

      <tr>
      <td width="5%" align="right" class="<%= tdColor %>"><span class="rss_ranking_text"><bean:write name="resultMdl" property="ranking" /></span></td>
      <td width="90%" valign="middle" class="<%= tdColor %>">
        <table width="100%" cellpadding="0" cellspacing="3" border="0">
        <tr>
        <td align="left">
        <a href="<bean:write name="resultMdl" property="rsdUrl" />" target="_blank"><span class="text_link"><bean:write name="resultMdl" property="rsdTitle" /></span></a>
        </td>
        <td align="right" valign="bottom"><b><bean:write name="resultMdl" property="userCount" /></b>&nbsp;&nbsp;<font size="-1">users</span></td>
        </tr>
        <tr><td colspan="2">&nbsp;</td></tr>
        <tr>
        <td class="text_base2" colspan="2">
        <span class="rss_description"><bean:write name="resultMdl" property="description" /></span>
        </td>
        </tr>
        </table>
      </td>
      <td class="<%= tdColor %>" width="5%" align="center">
      <logic:equal name="resultMdl" property="koudokuCount" value="0">
      <input type="button" value="<gsmsg:write key="rss.6" />" onClick="rssAdd(<bean:write name="resultMdl" property="rssSid" />, '<bean:write name="resultMdl" property="rsdTitle" />');" class="btn_add_n1">
      </logic:equal>
      <logic:greaterThan name="resultMdl" property="koudokuCount" value="0">
      <span class="text_koudokuzumi"><gsmsg:write key="rss.1" /><br><gsmsg:write key="cmn.pre" /></span>
      </logic:greaterThan>

      </td>
      </tr>

      </logic:iterate>
      </logic:notEmpty>
    </table>

    <logic:notEmpty name="rss040Form" property="resultList">
    <bean:size id="count2" name="rss040Form" property="pageLabelList" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="rss040page2" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="rss040Form" property="pageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backPage');"></span>
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