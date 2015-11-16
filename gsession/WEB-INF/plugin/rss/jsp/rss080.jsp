<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConstRss" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%-- ソートキー --%>
<%
  String sortName          =  String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_SORT_NAME);
  String sortUserCount     =  String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_SORT_USER_COUNT);
  String sortLastUpdate    =  String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_SORT_LAST_UPDATE);
%>

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="rss.3" /><gsmsg:write key="rss.rss080.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../rss/js/rss080.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../rss/js/rssMemPopUp.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/rss/rss080">
<input type="hidden" name="CMD">
<html:hidden property="rss080orderKey" />
<html:hidden property="rss080sortKey" />
<html:hidden property="rssSid" />
<html:hidden property="rssTitle" />
<html:hidden property="backScreen" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<!-- タイトル -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../rss/images/header_rssl03_01.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rss.3" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="rss.rss080.1" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td align="right">
       <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAconf');">
    </td>
    </tr>
    </table>

    <logic:notEmpty name="rss080Form" property="resultList">
    <bean:size id="count1" name="rss080Form" property="pageLabelList" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="rss080page1" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="rss080Form" property="pageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

  <bean:define id="sortKey" name="rss080Form" property="rss080sortKey" />
  <bean:define id="orderKey" name="rss080Form" property="rss080orderKey" />
  <% int iSortKey = ((Integer) sortKey).intValue();                                       %>
  <% int iOrderKey = ((Integer) orderKey).intValue();                                     %>
  <% int[] sortKeyList = GSConstRss.LIST_SORT_KEY_ALL; %>
  <% String[] title_width = new String[] { "60%", "15%", "20%"};                   %>
  <gsmsg:define id="rssName" msgkey="rss.14" />
  <gsmsg:define id="users" msgkey="rss.25" />
  <gsmsg:define id="lastUpdate" msgkey="cmn.last.modified" />
  <% String[] titleList = new String[] {rssName, users, lastUpdate};  %>

  <table class="tl0" width="100%" cellpadding="0" cellspacing="0">
  <tr>

  <% int order_asc = GSConstRss.ORDER_KEY_ASC; %>
  <% int order_desc = GSConstRss.ORDER_KEY_DESC; %>
  <% for (int i = 0; i < sortKeyList.length; i++) {   %>
  <%   String title = titleList[i];                   %>
  <%   String skey = String.valueOf(sortKeyList[i]);  %>
  <%   String order = String.valueOf(order_asc);      %>
  <%   if (iSortKey == sortKeyList[i]) {              %>
  <%     if (iOrderKey == order_desc) {               %>
  <%       title = title + "▼";                      %>
  <%     } else {                                     %>
  <%       title = title + "▲";                      %>
  <%       order = String.valueOf(order_desc);        %>
  <%     }                                            %>
  <%   }                                              %>
  <th width="<%= title_width[i] %>" class="table_bg_7D91BD">
  <%   if (iSortKey > 0) { %>
       <a href="#" onClick="return sort(<%= skey %>, <%= order %>);">
  <%   } %>
  <span class="text_tlw">
    <%= title %>
  </span>
  <%   if (iSortKey > 0) { %>
       </a>
  <%   } %>
  </th>
  <% } %>
  <th width="5%" class="table_bg_7D91BD"></th>

    <logic:notEmpty name="rss080Form" property="resultList">
      <bean:define id="mod" value="0" />
      <logic:iterate name="rss080Form" property="resultList" scope="request" id="rssData" indexId="index">

        <logic:equal name="mod" value="<%= String.valueOf(index.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod" value="<%= String.valueOf(index.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <tr>
        <td class="<bean:write name="tblColor" />" align="left">
          <span class="text_link"><a href="<bean:write name="rssData" property="rsdUrl" />" target="_blank"><bean:write name="rssData" property="rsdTitle" /></a></span><br>
          <span class="rss_description"><bean:write name="rssData" property="rsdUrlFeed" /></span>
        </td>
        <td class="<bean:write name="tblColor" />" align="left">
            <span class="text_tantou">
            <a href="javascript:openUsrWindow(<bean:write name="rssData" property="rssSid" />);">
            <bean:write name="rssData" property="userCount" /> users
            </a>
            </span>
        </td>

        <td class="<bean:write name="tblColor" />" align="left">
          <bean:write name="rssData" property="dspFeedUpdateTime" />
        </td>

        <td class="<bean:write name="tblColor" />" align="left">
          <input type="button" value="<gsmsg:write key="cmn.delete" />"  onClick="return rssDel('<bean:write name="rssData" property="rssSid" />', '<bean:write name="rssData" property="rsdTitle" />');" class="btn_dell_n3">
        </td>
        </tr>

      </logic:iterate>
    </logic:notEmpty>

    </table>

    <logic:notEmpty name="rss080Form" property="resultList">
    <bean:size id="count2" name="rss080Form" property="pageLabelList" scope="request" />
    <logic:greaterThan name="count2" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="rss080page2" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="rss080Form" property="pageLabelList" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table cellpadding="5" cellspacing="0" width="100%">
    <tr>
    <td align="right">
       <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backAconf');">
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

<br class="clear">
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>