<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.3" /> <gsmsg:write key="cmn.setting.main.view2" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../rss/css/rss.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-1.6.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src='../common/js/jquery-ui-1.8.16/jquery-ui-1.8.16.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.core.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.mouse.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/jquery-ui-1.8.16/ui/jquery.ui.sortable.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../rss/js/rss060.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript">
  /* Discription disp and hide */
  function dispDescription(fdid) {
    var ctext = $('#' + fdid)[0];
    if (ctext.className == 'rss_description_text_dsp') {
      changeStyle(ctext, 'rss_description_text_notdsp');
    } else {
      changeStyle(ctext, 'rss_description_text_dsp');
    }
  }
</script>

</head>

<logic:empty name="rss060Form" property="rss060SidUpdate">
<body class="body_03">
</logic:empty>
<logic:notEmpty name="rss060Form" property="rss060SidUpdate">
<body class="body_03" onload="rssUpdate();">
</logic:notEmpty>

<html:form action="/rss/rss060">
<input type="hidden" name="CMD" value="search">
<html:hidden property="backScreen" />
<input type="hidden" name="rssSid" value="">
<input type="hidden" name="rssTitle" value="">
<input type="hidden" name="rssCmdMode" value="0">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="70%" align="center">
<tr>
<td width="100%" align="center" valign="top">

  <!-- タイトル -->
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
    <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.main.view2" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
  </tr>
  </table>

  <table cellpadding="5" cellspacing="0" border="0" width="100%">
  <tr>
  <td valign="bottom"><span class="text_r1"><gsmsg:write key="rss.rss060.1" /></span></td>
  <td valign="top" align="right">
    <input type="button" name="btn_user_tool" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backpconf');">
  </td>
  </tr>
  </table>

  <!-- 購読RSSの一覧 -->

  <div align="center">
    <table width="100%">
    <tr>
    <td width="50%" valign="top" id="rssListLeft" class="column">

    <!-- 左コンテンツを表示 -->
    <logic:iterate id="flist" name="rss060Form" property="rss060Flist" indexId="idx1">

    <logic:equal name="flist" property="feedPosition" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_POSITIONFLG_LEFT) %>">

    <!-- RSS取得結果  -->
    <div class="portlet" id="<bean:write name="flist" property="feedSid" />">
    <table cellpadding="0" cellspacing="0" width="100%" border="0" class="rsswin">
    <tr class="rss_sorthandle">
      <td width="0%" align="left" class="rsswin_title"><img src="../rss/images/menu_icon_2.gif"></td>
      <td width="100%" align="left" class="rsswin_title2"><span><a style="font-size: 90%; font-weight: bold; text-decoration: underline;" href="<bean:write name="flist" property="furl" />" target="_blank"><bean:write name="flist" property="ftitle" /></a></span></td>
    </tr>
    <tr>
    <td colspan="2" class="rsswin_body">
      <logic:empty name="flist" property="feedList">
        <div class="rss_link_text">
        <a href="<bean:write name="flist" property="feedUrl" />" target="_blank" style="text-decoration: underline;"><bean:write name="flist" property="feedUrl" /></a>
        </div>
      </logic:empty>
      <logic:notEmpty name="flist" property="feedList">
    <bean:define id="feedList" name="flist" property="feedList" type="java.util.List" />
    <% if (feedList.size() == 0) { %>
      <div class="rss_link_text">
      <a href="<bean:write name="flist" property="feedUrl" />" target="_blank" style="text-decoration: underline;"><bean:write name="flist" property="feedUrl" /></a>
      </div>

    <% } else { %>

      <logic:iterate id="feed1" name="flist" property="feedList" indexId="idx2">
        <div id="<bean:write name="idx1" />_<bean:write name="idx2" />">
          <div class="rss_link_text" style="vertical-align:bottom;">
            <!-- 要約表示切替 -->
            <a style="color: #000000" href="javaScript:void(0);" onClick="dispDescription('ds<bean:write name="idx1" />_<bean:write name="idx2" />');">▽</a>
            <!-- 記事 -->
            <a href="<bean:write name="feed1" property="link" />" target="_blank" style="text-decoration: underline;"><bean:write name="feed1" property="title" /></a>
            <!-- RSS記事タイトル検索 -->
            &nbsp;&nbsp;<a href="#" onClick="rssSearch('<bean:write name="feed1" property="title" />');"><img src="../common/images/search_web.gif" border="0" class="img_bottom" alt="<gsmsg:write key="cmn.search" />"></a>
          </div>
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

      </logic:notEmpty>
    </td>
    </tr>
    </table>
    </div>
    </logic:equal>

    </logic:iterate>
    </td>

    <td width="50%" valign="top" id="rssListRight" class="column">
    <!-- 右コンテンツを表示 -->
    <logic:iterate id="flist" name="rss060Form" property="rss060Flist" indexId="idx1">

    <logic:equal name="flist" property="feedPosition" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_POSITIONFLG_RIGHT) %>">
    <!-- RSS取得結果  -->
    <div class="portlet" id="<bean:write name="flist" property="feedSid" />">
    <table cellpadding="0" cellspacing="0" width="100%" border="0" class="rsswin" id="rssListRight_<bean:write name="flist" property="feedSid" />">
    <tr class="rss_sorthandle">
    <td width="0%" align="left" class="rsswin_title"><img src="../rss/images/menu_icon_2.gif"></td>
    <td width="100%" align="left" class="rsswin_title2"><span><a style="font-size: 90%; font-weight: bold; text-decoration: underline;" href="<bean:write name="flist" property="furl" />" target="_blank"><bean:write name="flist" property="ftitle" /></a></span></td>
    </tr>
    <tr>
    <td colspan="2" class="rsswin_body">
      <logic:empty name="flist" property="feedList">
        <div class="rss_link_text">
        <a href="<bean:write name="flist" property="feedUrl" />" target="_blank" style="text-decoration: underline;"><bean:write name="flist" property="feedUrl" /></a>
        </div>
      </logic:empty>
      <logic:notEmpty name="flist" property="feedList">
      <bean:define id="feedList" name="flist" property="feedList" type="java.util.List" />
      <% if (feedList.size() == 0) { %>
        <div class="rss_link_text">
        <a href="<bean:write name="flist" property="feedUrl" />" target="_blank" style="text-decoration: underline;"><bean:write name="flist" property="feedUrl" /></a>
        </div>

      <% } else { %>

      <logic:iterate id="feed1" name="flist" property="feedList" indexId="idx2">
        <div id="<bean:write name="idx1" />_<bean:write name="idx2" />">
          <div class="rss_link_text">
            <!-- 要約表示切替 -->
            <a style="color: #000000" href="javaScript:void(0);" onClick="dispDescription('ds<bean:write name="idx1" />_<bean:write name="idx2" />');">▽</a>
            <!-- 記事 -->
            <a href="<bean:write name="feed1" property="link" />" target="_blank" style="text-decoration: underline;"><bean:write name="feed1" property="title" /></a>
            <!-- RSS記事タイトル検索 -->
            &nbsp;&nbsp;<a href="javaScript:void(0);" onClick="rssSearch('<bean:write name="feed1" property="title" />');"><img src="../common/images/search_web.gif" border="0" class="img_bottom" alt="<gsmsg:write key="cmn.search" />"></a>
          </div>
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
      </logic:notEmpty>
    </td>
    </tr>
    </table>
    </div>
    </logic:equal>

    </logic:iterate>
    </td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</div>

</html:form>

<br class="clear">
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>