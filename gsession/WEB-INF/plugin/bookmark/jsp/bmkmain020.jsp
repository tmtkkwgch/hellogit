<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bmk.24" />(<gsmsg:write key="cmn.main" />)</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../bookmark/js/bmkmain020.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>

</head>

<body class="body_03">
<html:form action="/bookmark/bmkmain020">

<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<logic:equal name="bmkmain020Form" property="bmkmain020dspFlg" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_YES) %>">
<logic:notEmpty name="bmkmain020Form" property="bmkmain020NewList">

      <table width="100%" class="tl0" cellspacing="0" cellpadding="0">

      <tr>
      <td class="double_header_7D91BD_left">
      <img src="../bookmark/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="bmk.43" />"><a href="<bean:write name="bmkmain020Form" property="bmkTopUrl" />"><gsmsg:write key="bmk.24" /></a></td>
      </td>
      <td align="right" class="double_header_7D91BD_right">
      <input type="button" onClick="return bmkmain020buttonPush('bmkmain020settei');" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
      </td>
      </tr>

      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>

      <logic:iterate id="newMdl" name="bmkmain020Form" property="bmkmain020NewList" indexId="idx">
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>
      <tr>
      <!-- 追加ボタンありのときのtd -->
      <logic:equal name="newMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" style="border-right:0px;">
      </logic:equal>
      <!-- 追加ボタンなしのときのtd -->
      <logic:notEqual name="newMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" colspan="2">
      </logic:notEqual>

      <!-- タイトル -->
      <a style="color:blue;" href="<bean:write name="newMdl" property="bmuUrl" />" target="_blank">
      <span class="text_link"><font size="-1">
      <bean:write name="newMdl" property="viewBmuTitle" filter="false" />
<%--
      <logic:notEmpty name="newMdl" property="bmkTitleDspList">
      <logic:iterate id="titleDsp" name="newMdl" property="bmkTitleDspList" indexId="idx2">
      <% if (idx2 > 0) { %> <br> <% } %>
      <bean:write name="titleDsp" />
      </logic:iterate>
      </logic:notEmpty>
--%>
      </font></span>
      </a>
      <!-- 人数 -->
      <bean:define id="bmkMain020UsrCnt" name="newMdl" property="bmkPerCount" type="java.lang.Integer" />
      <a href="javascript:void(0);" onclick="return bmkmain020selPerCount('<bean:write name="newMdl" property="bmuSid" />');" style="font-size:70%;color:#ff0000;background-color:#ffcccc;">
      <gsmsg:write key="bmk.23" arg0="<%= String.valueOf(bmkMain020UsrCnt.intValue()) %>" />
      </a>
      </td>

      <!-- 追加ボタン -->
      <logic:equal name="newMdl" property="bmkMyKbn" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.TOROKU_NO) %>'>
      <td class="<%= tdColor %>" align="right" style="border-left:0px; padding:0px 2px 0px 0px;">
      <input type="button" name="add" style="border:0px;font-size:10px;color:#4e4e4e;width:26px;height:20px;padding-right:0px;padding-left:0px;padding-top:6px;font-weight:bold;" class="bmk_add_btn2" value="<gsmsg:write key="cmn.add" />" onclick="return bmkmain020buttonPushAdd('<bean:write name="newMdl" property="bmuSid" />');">
      </td>
      </logic:equal>

      </tr>
      </logic:iterate>
      <tr class="td_type1_10pt">
        <td class="main_link_td_padding" colspan="2" align="right" width="100%">
        <span class="main_link"><a href="javascript:void(0);" onclick="return bmkmain020buttonPush('bmkmain020newbookmark');"><gsmsg:write key="cmn.list" /></a></span>
        </td>
      </tr>
      </table>

</logic:notEmpty>
</logic:equal>

</html:form>
</body>
</html:html>