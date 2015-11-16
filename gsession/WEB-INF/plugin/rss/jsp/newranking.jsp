<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="rss.newranking.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body class="body_03">

<html:form action="/rss/rssnewranking">
<input type="hidden" name="CMD" value="">
<html:hidden name="rssNewRankingForm" property="rssSid" />
<html:hidden name="rssNewRankingForm" property="rssTitle" />
  <logic:notEmpty name="rssNewRankingForm" property="newRankingList">
  <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
  <tr>
  <td align="left" class="header_7D91BD_right">
    <img src="../rss/images/menu_icon_single.gif" class="img_bottom img_border img_menu_icon_single" alt="<gsmsg:write key="rss.9" />"><a href="<bean:write name="rssNewRankingForm" property="rssTopUrl" />"><gsmsg:write key="rss.9" /></a>
  </td>
  </tr>

  <% String[] tdClassList = {"td_type1", "td_type25_2"}; %>
  <logic:iterate id="rankingMdl" name="rssNewRankingForm" property="newRankingList" indexId="index">

  <% String tdClass = tdClassList[index.intValue() % 2]; %>
  <% String titleClass = "text_link"; %>

  <tr class="text_base2">
  <td class="<%= tdClass %>">

    <table width="100%" cellspacing="0" cellpadding="1" border="0">
    <tr valign="top">
    <td width="84%" align="left"><a href="<bean:write name="rankingMdl" property="url" />" target="_blank"><span class="<%= titleClass %>"><font size="-1"><bean:write name="rankingMdl" property="title" /></font></span></a>
    </td>
    <td width="16%">
      <logic:equal name="rankingMdl" property="koudokuCount" value="0">
      <input type="button" value="&nbsp;"  onClick="return rssAdd('<bean:write name="rankingMdl" property="rssSid" />', '<bean:write name="rankingMdl" property="title" />');" class="btn_kodoku">
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
  </logic:notEmpty>
</html:form>

</body>
</html:html>