<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.3" /></title>
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
<script language="JavaScript" src="../rss/js/rss010.js?<%= GSConst.VERSION_PARAM %>"></script>
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

<logic:empty name="rss010Form" property="rss010SidUpdate">
<body class="body_03">
</logic:empty>
<logic:notEmpty name="rss010Form" property="rss010SidUpdate">
<body class="body_03" onload="rssUpdate();">
</logic:notEmpty>

<html:form action="/rss/rss010">
<input type="hidden" name="CMD" value="search">
<input type="hidden" name="rssSid" value="">
<input type="hidden" name="rssTitle" value="">
<input type="hidden" name="rssCmdMode" value="0">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<!-- タイトル -->
<table align="center" cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">

  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../rss/images/header_rssl03_01.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="rss.3" /></span></td>
    <td width="100%" class="header_white_bg">
    <logic:equal name="rss010Form" property="rss010viewAdminBtn" value="1">
      <input type="button" name="btn_ktool" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('aconf');">
    </logic:equal>
      <input type="button" name="btn_user_tool" class="btn_setting_n1" value="<gsmsg:write key="cmn.preferences2" />" onClick="buttonPush('pconf');">
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="rss.3" />"></td>
    </tr>
    </table>

  </td>
  </tr>
  </table>

</td>
</tr>

<tr>
<td valign="top" align="right">
  <!-- input type="button" name="btn_koudokurank" class="btn_base1" value="<gsmsg:write key="rss.rss010.1" />" -->
  <input type="button" name="btn_feedadd" class="btn_rank_n1" value="<gsmsg:write key="cmn.ranking" />" onClick="buttonPush('ranking');">
  <input type="button" name="btn_feedadd" class="btn_add_n1" value="<gsmsg:write key="rss.6" />" onClick="buttonPush('rssInput');">
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

<!-- 購読RSSの一覧 -->
<div align="center">

<table width="100%">
<tr>
<td width="75%" valign="top">

  <table width="100%">
  <tr>
  <td width="50%" valign="top" id="rssListLeft" class="column">

  <!-- 左コンテンツを表示 -->
  <logic:iterate id="flist" name="rss010Form" property="rss010Flist" indexId="idx1">

  <logic:equal name="flist" property="feedPosition" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_POSITIONFLG_LEFT) %>">

  <!-- RSS取得結果  -->
  <div class="portlet" id="<bean:write name="flist" property="feedSid" />">
  <table cellpadding="0" cellspacing="0" width="100%" border="0" class="rsswin">
  <tr class="rss_sorthandle">
    <td width="0%" align="left" class="rsswin_title"><img src="../rss/images/menu_icon_2.gif"></td>
    <td width="100%" align="left" class="rsswin_title2"><span><a style="font-size: 90%; font-weight: bold; text-decoration: underline;" href="<bean:write name='flist' property='furl' />" target="_blank"><bean:write name='flist' property='ftitle' /></a></span></td>
    <td width="0%" align="right" class="rsswin_title2"><input type="button" value="<gsmsg:write key="cmn.edit" />" onClick="rssEdit(<bean:write name='flist' property='feedSid' />);" class="btn_edit_n3"></td>
  </tr>
  <tr>
  <td colspan="3" class="rsswin_body">

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
      <div id="<bean:write name='idx1' />_<bean:write name='idx2' />">
        <div class="rss_link_text">
          <!-- 要約表示切替 -->
          <a style="color: #000000" href="javaScript:void(0);" onClick="dispDescription('ds<bean:write name="idx1" />_<bean:write name="idx2" />');">▽</a>
          <!-- 記事 -->
          <a href="<bean:write name='feed1' property='link' />" target="_blank" style="text-decoration: underline;"><bean:write name="feed1" property="title" /></a>
          <!-- RSS記事タイトル検索 -->
          &nbsp;&nbsp;<a href="#" onClick="rssSearch('<bean:write name="feed1" property="title" />');"><img src="../common/images/search_web.gif" border="0" class="img_bottom" alt="<gsmsg:write key="cmn.search" />"></a>
        </div>
      </div>

      <div class="rss_author_text"><bean:write name="feed1" property="author" /></div>

      <!-- Description -->
      <div id="ds<bean:write name='idx1' />_<bean:write name='idx2' />" class="rss_description_text_notdsp">
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
  <logic:iterate id="flist" name="rss010Form" property="rss010Flist" indexId="idx1">

  <logic:equal name="flist" property="feedPosition" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstRss.RSS_POSITIONFLG_RIGHT) %>">
  <!-- RSS取得結果  -->
  <div class="portlet" id="<bean:write name='flist' property='feedSid' />">
  <table cellpadding="0" cellspacing="0" width="100%" border="0" class="rsswin">
  <tr class="rss_sorthandle">
  <td width="0%" align="left" class="rsswin_title"><img src="../rss/images/menu_icon_2.gif"></td>
  <td width="100%" align="left" class="rsswin_title2"><span><a style="font-size: 90%; font-weight: bold; text-decoration: underline;" href="<bean:write name='flist' property='furl' />" target="_blank"><bean:write name="flist" property="ftitle" /></a></span></td>
  <td width="0%" align="right" class="rsswin_title2"><input type="button" value="<gsmsg:write key="cmn.edit" />" onClick="rssEdit(<bean:write name='flist' property='feedSid' />);" class="btn_edit_n3"></td>
  </tr>
  <tr>
  <td colspan="3" class="rsswin_body">
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
      <div id="<bean:write name='idx1' />_<bean:write name='idx2' />">
        <div class="rss_link_text">
          <!-- 要約表示切替 -->
          <a style="color: #000000" href="javaScript:void(0);" onClick="dispDescription('ds<bean:write name="idx1" />_<bean:write name="idx2" />');">▽</a>
          <!-- 記事 -->
          <a href="<bean:write name='feed1' property='link' />" target="_blank" style="text-decoration: underline;"><bean:write name="feed1" property="title" /></a>
          <!-- RSS記事タイトル検索 -->
          &nbsp;&nbsp;<a href="javaScript:void(0);" onClick="rssSearch('<bean:write name="feed1" property="title" />');"><img src="../common/images/search_web.gif" border="0" class="img_bottom" alt="<gsmsg:write key="cmn.search" />"></a>
        </div>
      </div>

      <div class="rss_author_text"><bean:write name="feed1" property="author" /></div>

      <!-- Description -->
      <div id="ds<bean:write name='idx1' />_<bean:write name='idx2' />" class="rss_description_text_notdsp">
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

<td width="25%" valign="top">
  <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>

  <logic:notEmpty name="rss010Form" property="rankingList">

  <!-- 登録ランキング -->
  <table width="100%" class="tl0">

  <tr>
  <th width="100%" align="center" class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="cmn.entry.rankings" /></span></th>
  </tr>

  <logic:iterate id="rankingMdl" name="rss010Form" property="rankingList" indexId="index">

  <bean:define id="rankNom" value="<%= String.valueOf(index.intValue() + 1) %>" />
  <% String tdClass = tdClassList[index.intValue() % 2]; %>
  <% String titleClass = "text_link"; %>

  <tr class="text_base2">
  <td class="<%= tdClass %>">
    <table width="100%" cellspacing="0" cellpadding="1" border="0">
    <tr valign="top">
    <td width="15%">
      <bean:define id="rankNum" name="rankingMdl" property="ranking" type="java.lang.String" />
      <span class="swd_rank_best3"><gsmsg:write key="rss.rss010.3" arg0="<%= rankNum %>" /></span>
    </td>
    <td width="69%" align="left"><a href="<bean:write name='rankingMdl' property='rsdUrl' />" target="_blank"><span class="<%= titleClass %>"><font size="-1"><bean:write name="rankingMdl" property="rsdTitle" /></font></span></a><br>
    <span class="swd_rank_nom">(<bean:write name="rankingMdl" property="userCount" />&nbsp;)</span>
    </td>

    <td width="16%">
    <logic:equal name="rankingMdl" property="koudokuCount" value="0">
    <input type="button" value="&nbsp;" onClick="return rssAdd('<bean:write name="rankingMdl" property="rssSid" />', '<bean:write name="rankingMdl" property="rsdTitle" />');" class="btn_kodoku">
    </logic:equal>
    <logic:greaterThan name="rankingMdl" property="koudokuCount" value="0">
    <span class="text_koudokuzumi"><gsmsg:write key="rss.1" /><br><gsmsg:write key="cmn.pre" /></span>
    </logic:greaterThan>
    </td>

    </tr>
    </table>
  </td>
  </tr>

  </logic:iterate>

  </table>
  <br>
  </logic:notEmpty>


  <logic:notEmpty name="rss010Form" property="newRankingList">

  <!-- 新着RSS -->
  <table width="100%" class="tl0">

  <tr>
  <th width="100%" align="center" class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="rss.9" /></span></th>
  </tr>

  <logic:iterate id="newRankingMdl" name="rss010Form" property="newRankingList" indexId="index">

  <% String tdClass = tdClassList[index.intValue() % 2]; %>
  <% String titleClass = "text_link"; %>

  <tr class="text_base2">
  <td class="<%= tdClass %>">

    <table width="100%" cellspacing="0" cellpadding="1" border="0">
    <tr valign="top">
    <td width="84%" align="left"><a href="<bean:write name='newRankingMdl' property='url' />" target="_blank"><span class="<%= titleClass %>"><font size="-1"><bean:write name="newRankingMdl" property="title" /></font></span></a>
    </td>
    <td width="16%">
    <logic:equal name="newRankingMdl" property="koudokuCount" value="0">
    <input type="button" value="&nbsp;"  onClick="return rssAdd('<bean:write name="newRankingMdl" property="rssSid" />', '<bean:write name="newRankingMdl" property="title" />');" class="btn_kodoku">
    </logic:equal>
    <logic:greaterThan name="newRankingMdl" property="koudokuCount" value="0">
    <span class="text_koudokuzumi"><gsmsg:write key="rss.1" /><br><gsmsg:write key="cmn.pre" /></span>
    </logic:greaterThan>
    </td>
    </tr>
    </table>

  </td>
  </tr>

  </logic:iterate>

  </table>
  </logic:notEmpty>

</td>
</tr>
</table>

</div>

<!-- SUB MENU -->
<div id="rss_menu">

  <div id="kiji" class="text_test1"><gsmsg:write key="rss.10" /></div>

</div><!-- rss_menu -->

</html:form>

<br class="clear">
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>