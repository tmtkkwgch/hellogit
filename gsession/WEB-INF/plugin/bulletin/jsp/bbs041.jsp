<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bbs.bbs041.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/webSearch.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/search.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs041.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs041">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs041Form" property="s_key" />
<html:hidden name="bbs041Form" property="bbs010page1" />
<html:hidden name="bbs041Form" property="bbs010forumSid" />
<html:hidden name="bbs041Form" property="bbs060page1" />
<html:hidden name="bbs041Form" property="searchDspID" />
<html:hidden name="bbs041Form" property="threadSid" />
<html:hidden name="bbs041Form" property="bbs040forumSid" />
<html:hidden name="bbs041Form" property="bbs040keyKbn" />
<html:hidden name="bbs041Form" property="bbs040taisyouThread" />
<html:hidden name="bbs041Form" property="bbs040taisyouNaiyou" />
<html:hidden name="bbs041Form" property="bbs040userName" />
<html:hidden name="bbs041Form" property="bbs040readKbn" />
<html:hidden name="bbs041Form" property="bbs040dateNoKbn" />
<html:hidden name="bbs041Form" property="bbs040fromYear" />
<html:hidden name="bbs041Form" property="bbs040fromMonth" />
<html:hidden name="bbs041Form" property="bbs040fromDay" />
<html:hidden name="bbs041Form" property="bbs040toYear" />
<html:hidden name="bbs041Form" property="bbs040toMonth" />
<html:hidden name="bbs041Form" property="bbs040toDay" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%">
        <img src="../bulletin/images/header_bulletin_01.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
        <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.bulletin" /></span></td>
        <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bbs.bbs041.1" /> ]</td>
        <td width="0%">
        <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.bulletin" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="right">
          <input type="button" name="btn_prjadd" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backPage');"></span>
        </td>
      </tr>
    </table>

    <logic:notEmpty name="bbs041Form" property="resultList">
    <bean:size id="count1" name="bbs041Form" property="bbsPageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bbs041page1" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="bbs041Form" property="bbsPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table width="100%" cellpadding="5" cellspacing="5" align="center">
      <tr>
      <td align="center" class="table_bg_A5B4E1">
        <span class="text_bb1"><gsmsg:write key="cmn.search.result" /></span>
      </td>
      </tr>

      <logic:notEmpty name="bbs041Form" property="resultList">
      <logic:iterate id="resultMdl" name="bbs041Form" property="resultList" indexId="idx">
      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>

      <tr>
      <td valign="middle" class="<%= tdColor %>">

        <span class="text_left"><img src="../bulletin/images/cate_icon02.gif" alt="<gsmsg:write key="bbs.2" />"></span>

        <div class="item_text">
        <a href="javascript:clickResult(<bean:write name="resultMdl" property="bfiSid" />, <bean:write name="resultMdl" property="btiSid" />);"><span class="text_link"><bean:write name="resultMdl" property="btiTitle" /></span></a>
        <logic:equal name="resultMdl" property="btiLimit" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_LIMIT_YES) %>">
            <logic:equal name="resultMdl" property="threadFinFlg" value="1">
            <span class="text_right" style="color:#ff0000;"><gsmsg:write key="bbs.12" />：<bean:write name="resultMdl" property="strBtiLimitDate" />迄</span>
            </logic:equal>
            <logic:equal name="resultMdl" property="threadFinFlg" value="0">
            <span class="text_right"><gsmsg:write key="bbs.12" />：<bean:write name="resultMdl" property="strBtiLimitDate" />迄</span>
            </logic:equal>
        </logic:equal>
        <div class="text_base2">
        <table width="80%">
        <tr>
        <td>
        <bean:write name="resultMdl" property="bwiValueView" />
        </td>
        </tr>
        </table>
        <div>

        </div>
        <span class="text_left"><gsmsg:write key="bbs.bbs041.3" />：<bean:write name="resultMdl" property="userName" /></span>
        <span class="text_right"><gsmsg:write key="bbs.bbs041.4" />：<bean:write name="resultMdl" property="strWriteDate" /></span></div>
        </div>
      </td>
      </tr>

      </logic:iterate>
      </logic:notEmpty>
    </table>

    <logic:notEmpty name="bbs041Form" property="resultList">
    <bean:size id="count2" name="bbs041Form" property="bbsPageLabel" scope="request" />
    <table width="100%" cellpadding="5" cellspacing="0">
      <logic:notEqual name="bbs041Form" property="bbs041searchUse" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.PLUGIN_NOT_USE) %>">
      <logic:notEmpty name="bbs041Form" property="s_key">
      <tr>
      <td align="left">
      <bean:define id="searchKeyword" name="bbs041Form" property="s_key" type="java.lang.String" />
      <a href="javascript:void(0);" class="" onmouseover="overSearch();" onmouseout="outSearch();" onClick="webSearch('<bean:write name="bbs041Form" property="bbs041WebSearchWord" />');">
        <span id="webSearchArea" class="text_normal">
        <gsmsg:write key="cmn.websearch" arg0="<%= searchKeyword %>" />
        </span>
      </a>
      </td>
      </tr>
      </logic:notEmpty>
      </logic:notEqual>
    <logic:greaterThan name="count2" value="1">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bbs041page2" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="bbs041Form" property="bbsPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');"></td>
      </tr>
    </logic:greaterThan>
    </table>
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