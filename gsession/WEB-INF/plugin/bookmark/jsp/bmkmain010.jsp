<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bmk.43" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bookmark/js/bmkmain010.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">
<html:form action="/bookmark/bmkmain010">
<input type="hidden" name="CMD" value="">
<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>
<div id="tooltips_bmk">
  <logic:equal name="bmkmain010Form" property="dspFlg" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_NO) %>">
  <!--非表示-->
  </logic:equal>

  <!-- 個人ブックマーク -->
  <logic:notEmpty name="bmkmain010Form" property="bmkMain010List">
    <logic:notEqual name="bmkmain010Form" property="dspFlg" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_NO) %>">
    <table width="100%" class="tl0" cellspacing="0" cellpadding="0">
    <tr>
    <td class="double_header_7D91BD_left" style="border-top: solid 1px #333333;border-left: solid 1px #333333;">
    <img src="../bookmark/images/menu_icon_single.gif" class="img_border img_bottom img_menu_icon_single" alt="<gsmsg:write key="bmk.43" />"><a href="<bean:write name="bmkmain010Form" property="bmkTopUrl" />"><gsmsg:write key="bmk.43" /></a></td>
    </td>
    <td align="right" class="double_header_7D91BD_right" style="border-top: solid 1px #333333;border-right: solid 1px #333333;">
    <input type="button" onClick="return buttonPushSetting();" style="border:0px;color:#40a06b;font-size:10px;font-weight:bold;width:60px;height:17px;" class="btn_small_setting" value="<gsmsg:write key="cmn.setting" />">
    </td>
    </tr>

    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_type1", "td_type25_2"}; %>

    <logic:iterate id="bmkMdl" name="bmkmain010Form" property="bmkMain010List" indexId="idx">
    <% tdColor = tdColors[(idx.intValue() % 2)];
      %>
    <tr>
    <td colspan="2" class="<%= tdColor %>">

    <!-- タイトル・人数 -->

    <a style="color:blue;" title="" href="<bean:write name="bmkMdl" property="bmkUrl" />" target="_blank">
    <span class="text_link"><font size="-1">
    <bean:write name="bmkMdl" property="bmkTitle" />
    </font></span>
    <span class="tooltips"><bean:write name="bmkMdl" property="labelName"/><br><bean:write name="bmkMdl" property="bmkCmt"/></span>
    </a>

    <bean:define id="bmkMain010UsrCnt" name="bmkMdl" property="userCount" type="java.lang.Integer" />
    <a href="javascript:void(0);" onclick="return selPerCount('<bean:write name="bmkMdl" property="bmuSid" />');" style="font-size:70%;color:#ff0000;background-color:#ffcccc;">
    <gsmsg:write key="bmk.23" arg0="<%= String.valueOf(bmkMain010UsrCnt.intValue()) %>" />
    </a>

    </td>
    </tr>

    </logic:iterate>
    </table>
  </logic:notEqual>
  </logic:notEmpty>
</div>
</html:form>
</body>
</html:html>