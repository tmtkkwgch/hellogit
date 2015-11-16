<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.rssmain.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<logic:empty name="rssmainForm" property="sidUpdate">
<body class="body_03">
</logic:empty>
<logic:notEmpty name="rssmainForm" property="sidUpdate">
<body class="body_03" onload="rssUpdate();">
</logic:notEmpty>

<html:form action="/rss/rssmain">



<table width="100%" cellpadding="0" cellspacing="0" style="z-index:20000;" id="rss_area">
<tr>
<td width="50%" valign="top" align="left">

<!-- 左コンテンツを表示 -->
<logic:iterate id="flist" name="rssmainForm" property="flist" indexId="idx1">
<logic:equal name="flist" property="feedPosition" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_POSITIONFLG_LEFT) %>">

  <!-- RSS取得結果  -->
  <table cellpadding="0" cellspacing="0" class="rsswin">
  <tr>
  <td width="0%" align="left" class="header_7D91BD_left"><img src="../rss/images/menu_icon_single.gif" class="img_border img_menu_icon_single" width="20" height="20"><span><a style="font-size: 90%; font-weight: bold; text-decoration: underline;" href="<bean:write name="flist" property="furl" />" target="_blank"><bean:write name="flist" property="ftitle" /></a></span></td>
  </tr>
  <tr>
  <td colspan="2" class="rsswin_body">
  <bean:define id="feedList" name="flist" property="feedList" type="java.util.List" />
  <% if (feedList == null || feedList.size() == 0) { %>
    <div class="rss_link_text">
    <a href="<bean:write name="flist" property="feedUrl" />" target="_blank" style="text-decoration: underline;"><bean:write name="flist" property="feedUrl" /></a>
    </div>

  <% } else { %>

  <logic:iterate id="feed1" name="flist" property="feedList" indexId="idx2">
    <div class="rss_link_text">
    <!-- 要約表示切替 -->
    <a style="color: #000000!important" href="javaScript:void(0);" onClick="dispDescription('ds<bean:write name="idx1" />_<bean:write name="idx2" />');">▽</a>
    <!-- 記事 -->
    <a href="<bean:write name="feed1" property="link" />" target="_blank" style="text-decoration: underline;"><bean:write name="feed1" property="title" /></a>
    <!-- RSS記事タイトル検索 -->
    &nbsp;&nbsp;<a href="javaScript:void(0);" onClick="rssSearch('<bean:write name="feed1" property="title" />');"><img src="../common/images/search_web.gif" border="0" class="img_bottom" alt="<gsmsg:write key="cmn.search" />"></a>
    </div>

    <div class="rss_author_text"><bean:write name="feed1" property="author" /></div>

    <!-- Description -->
    <div id="ds<bean:write name="idx1" />_<bean:write name="idx2" />" class="rss_description_text_notdsp">
      <div class="rss_date_text"><bean:write name="feed1" property="publishedDate" /></div>
      <logic:empty name="feed1" property="description">&nbsp;</logic:empty>
      <logic:notEmpty name="feed1" property="description">
      <bean:define id="description" name="feed1" property="description" />
      <bean:write name="description" property="value" filter="false" />
      </logic:notEmpty>
    </div>

  </logic:iterate>
  <% } %>
  </td>
  </tr>
  </table>

</logic:equal>
</logic:iterate>
</td>

<td width="50%" valign="top" align="right">
<!-- 右コンテンツを表示 -->
<logic:iterate id="flist" name="rssmainForm" property="flist" indexId="idx1">
<logic:equal name="flist" property="feedPosition" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_POSITIONFLG_RIGHT) %>">

  <!-- RSS取得結果  -->
  <table cellpadding="0" cellspacing="0" class="rsswin">
  <tr>
  <td width="0%" align="left" class="header_7D91BD_left"><img src="../rss/images/menu_icon_single.gif" class="img_border img_menu_icon_single" width="20" height="20"><span><a style="font-size: 90%; font-weight: bold; text-decoration: underline;" href="<bean:write name="flist" property="furl" />" target="_blank"><bean:write name="flist" property="ftitle" /></a></span></td>
  </tr>
  <tr>
  <td colspan="2" class="rsswin_body">
  <bean:define id="feedList" name="flist" property="feedList" type="java.util.List" />
  <% if (feedList == null || feedList.size() == 0) { %>
    <div class="rss_link_text">
    <a href="<bean:write name="flist" property="feedUrl" />" target="_blank" style="text-decoration: underline;"><bean:write name="flist" property="feedUrl" /></a>
    </div>

  <% } else { %>

  <logic:iterate id="feed1" name="flist" property="feedList" indexId="idx2">
    <div class="rss_link_text" style="vertical-align:bottom;">
    <!-- 要約表示切替 -->
    <a style="color: #000000!important" href="javaScript:void(0);" onClick="dispDescription('ds<bean:write name="idx1" />_<bean:write name="idx2" />');">▽</a>
    <!-- 記事 -->
    <a href="<bean:write name="feed1" property="link" />" target="_blank" style="text-decoration: underline;"><bean:write name="feed1" property="title" /></a>
    <!-- RSS記事タイトル検索 -->
    &nbsp;&nbsp;<a href="javaScript:void(0);" onClick="rssSearch('<bean:write name="feed1" property="title" />');"><img src="../common/images/search_web.gif" border="0" class="img_bottom" alt="<gsmsg:write key="cmn.search" />"></a>
    </div>

    <div class="rss_author_text"><bean:write name="feed1" property="author" /></div>
    <!-- Description -->
    <div id="ds<bean:write name="idx1" />_<bean:write name="idx2" />" class="rss_description_text_notdsp">
      <div class="rss_date_text"><bean:write name="feed1" property="publishedDate" /></div>
      <logic:empty name="feed1" property="description">&nbsp;</logic:empty>
      <logic:notEmpty name="feed1" property="description">
      <bean:define id="description" name="feed1" property="description" />
      <bean:write name="description" property="value" filter="false" />
      </logic:notEmpty>
    </div>
  </logic:iterate>
  <% } %>
  </td>
  </tr>
  </table>
</logic:equal>

</logic:iterate>

</td>
</tr>
</table>

</html:form>

</body>
</html:html>