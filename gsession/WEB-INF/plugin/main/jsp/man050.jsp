<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String list = String.valueOf(jp.groupsession.v2.man.GSConstMain.MODE_LIST); %>
<% String search = String.valueOf(jp.groupsession.v2.man.GSConstMain.MODE_SEARCH); %>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man050.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<title>[Group Session] <gsmsg:write key="user.usr090.2" /></title>
</head>
<body class="body_03" onunload="windowClose();" onload="tarminalChangeInit();">
<!--　BODY -->

<html:form action="/main/man050">

<input type="hidden" name="CMD" value="">
<html:hidden property="man050SortKey" />
<html:hidden property="man050OrderKey" />
<html:hidden property="man050Backurl" />
<html:hidden property="man050SelectedUsrSid" />
<html:hidden property="man050cmdMode" />
<html:hidden property="man050SearchFlg" />

<html:hidden property="cmd" />
<html:hidden property="sch010SelectUsrSid" />
<html:hidden property="sch010SelectUsrKbn" />

<logic:equal name="man050Form" property="man050adminFlg" value="true">
<input type="hidden" name="helpPrm" value="<bean:write name="man050Form" property="man050cmdMode" />">
</logic:equal>

<logic:equal name="man050Form" property="man050adminFlg" value="false">
<input type="hidden" name="helpPrm" value="2">
</logic:equal>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<!-- <gsmsg:write key="cmn.title" /> -->
<table align="center" cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
      <img src="../main/images/header_main_01.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    <td width="0%" class="header_white_bg_text"></td>
    <td width="0%" class="header_white_bg_text3" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.main" /></span></td>
    <td width="100%" class="header_white_bg_text">
        <logic:equal name="man050Form" property="man050cmdMode" value="<%= list %>">
          [<gsmsg:write key="cmn.lastlogin.list" />]
        </logic:equal>
        <logic:notEqual name="man050Form" property="man050cmdMode" value="<%= list %>">
          [<gsmsg:write key="cmn.lastlogin.advanced" />]
        </logic:notEqual>
    </td>
      <td width="100%" class="header_white_bg">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('main');">
      </td>
    <td width="0%">
      <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="cmn.main" />"></td>
    </tr>
    <tr>
    <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
    </tr>
    </table>

    <logic:equal name="man050Form" property="man050adminFlg" value="true">

          <logic:messagesPresent message="false">
          <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
          <table cellpadding="0" cellspacing="0" border="0" width="100%">
          <tr><td width="100%"><html:errors /></td></tr>
          </table>
          </logic:messagesPresent>

          <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
        <td width="100%" nowrap>

        <logic:equal name="man050Form" property="man050cmdMode" value="<%= list %>">
           <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
            <tr>

            <td class="tab_bg_image_1_on" nowrap>
                <div class="tab_text_area">
                    <a href="javascript:changeTab('list');"><gsmsg:write key="main.man050.9" /></a>
                </div>
            </td>
            <td class="tab_space" nowrap>&nbsp;</td>
            <td class="tab_bg_image_1_off" nowrap>
                <div class="tab_text_area_right">
                    <a href="javascript:changeTab('initsearch');"><gsmsg:write key="cmn.advanced.search" /></a>
                </div>
            </td>

            <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
            </tr>
        </table>
        </logic:equal>

        <logic:equal name="man050Form" property="man050cmdMode" value="<%= search %>">
           <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
            <tr>

            <td class="tab_bg_image_1_off" nowrap>
                <div class="tab_text_area_right">
                    <a href="javascript:changeTab('list');"><gsmsg:write key="main.man050.9" /></a>
                </div>
            </td>
            <td class="tab_space" nowrap>&nbsp;</td>
            <td class="tab_bg_image_1_on" nowrap>
                <div class="tab_text_area">
                    <a href="javascript:changeTab('initsearch');"><gsmsg:write key="cmn.advanced.search" /></a>
                </div>
            </td>

            <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
            </tr>
        </table>
        </logic:equal>
        </td>

        <td class="smail_tab_top_bg" width="100%" align="right" valign="top" nowrap>
          </td>
        <td width="0%" class="tab_head_end"><img src="../common/images/spacer.gif" width="10" height="10" border="0" alt=""></td>
        </tr>
        </table>
    </logic:equal>

    <logic:equal name="man050Form" property="man050cmdMode" value="<%= list %>">
      <%@ include file="/WEB-INF/plugin/main/jsp/man050_sub01.jsp" %>
    </logic:equal>
    <logic:equal name="man050Form" property="man050cmdMode" value="<%= search %>">
      <%@ include file="/WEB-INF/plugin/main/jsp/man050_sub02.jsp" %>
    </logic:equal>
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