<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String maxLengthCmt = String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.MAX_LENGTH_CMT);
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="bmk.bmk030kn.04" /></title>
<theme:css filename="theme.css"/>
<link rel=stylesheet href="../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<html:form action="/bookmark/bmk030kn">

<input type="hidden" name="CMD" value="">

<html:hidden property="bmk020url" />

<html:hidden property="bmk030mode" />
<html:hidden property="bmk030modeName" />
<html:hidden property="bmk030groupSid" />
<html:hidden property="bmk030title" />
<html:hidden property="bmk030label" />
<html:hidden property="bmk030cmt" />
<html:hidden property="bmk030score" />
<html:hidden property="bmk030public" />
<html:hidden property="bmk030main" />
<html:hidden property="bmk030InitFlg" />

<html:hidden property="bmk070ReturnPage" />
<html:hidden property="bmk070Page" />
<html:hidden property="bmk070PageTop" />
<html:hidden property="bmk070PageBottom" />
<html:hidden property="bmk070OrderKey" />
<html:hidden property="bmk070SortKey" />

<html:hidden property="bmk080Page" />
<html:hidden property="bmk080PageTop" />
<html:hidden property="bmk080PageBottom" />
<input type="hidden" name="helpPrm" value="<bean:write name="bmk030knForm" property="procMode" />">

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<bean:define id="bmkMode" name="bmk030knForm" property="bmk030mode" type="java.lang.Integer" />
<% if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) { %>
<html:hidden property="bmk030public" />
<html:hidden property="bmk030main" />
<% } else if (bmkMode.intValue() == jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) { %>
<html:hidden property="bmk030main" />
<% } %>

<logic:notEmpty name="bmk030knForm" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk030knForm" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="bmk150PageNum" />
<html:hidden property="bmk070ToBmk150DspFlg" />
<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td>

  <table cellpadding="0" cellspacing="0" width="70%" align="center">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%">
    <img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="bmk.bmk030kn.05" /> ]</td>
    <td width="0%">
    <img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="return buttonPush('bmk030knpushOk');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('bmk030knpushBack');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

  <!-- メッセージ -->
  <logic:messagesPresent message="false">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
    <tr>
    <td align="left"><html:errors/><br></td>
    </tr>
    </table>
  </logic:messagesPresent>

    <table class="tl0 table_td_border" width="100%"  border="0" cellspacing="0" cellpadding="5">

    <!-- 登録先 -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left" width="17%"><gsmsg:write key="bmk.16" /></th>
    <td width="83%"><bean:write name="bmk030knForm" property="bmk030modeName" /></td>
    </tr>

    <!-- ＵＲＬ -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left">URL</th>
    <td>
<%-- <bean:write name="bmk030knForm" property="bmk020url" /> --%>
    <logic:notEmpty name="bmk030knForm" property="bmk030knUrlDsp">
    <logic:iterate id="urlDsp" name="bmk030knForm" property="bmk030knUrlDsp" indexId="idx2">
    <% if (idx2 > 0) { %> <br> <% } %>
    <bean:write name="urlDsp" />
    </logic:iterate>
    </logic:notEmpty>
    </td>
    </tr>

    <!-- タイトル -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.title" /></th>
    <td>
    <bean:write name="bmk030knForm" property="bmk030title" />
<%--
    <logic:notEmpty name="bmk030knForm" property="bmk030knTitleDsp">
    <logic:iterate id="titleDsp" name="bmk030knForm" property="bmk030knTitleDsp" indexId="idx3">
    <% if (idx3 > 0) { %> <br> <% } %>
    <bean:write name="titleDsp" />
    </logic:iterate>
    </logic:notEmpty>
--%>
    </td>
    </tr>

    <!-- ラベル -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.label" /></th>
    <td><bean:write name="bmk030knForm" property="bmk030label" /></td>
    </tr>

    <!-- コメント -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.comment" /></th>
    <td><bean:write name="bmk030knForm" property="bmk030knCmtDsp" filter="false" /></td>
    </tr>

    <!-- 評価 -->
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="bmk.bmk030kn.01" /></th>
    <td>
    <logic:equal name="bmk030knForm" property="bmk030score" value='1'>
    <gsmsg:write key="tcd.148" /> (1)
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030score" value='2'>
    <gsmsg:write key="tcd.149" /> (2)
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030score" value='3'>
    <gsmsg:write key="tcd.150" /> (3)
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030score" value='4'>
    <gsmsg:write key="tcd.151" /> (4)
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030score" value='5'>
    <gsmsg:write key="tcd.152" /> (5)
    </logic:equal>
    </td>
    </tr>

    <!-- 公開区分 -->
    <logic:notEqual name="bmk030knForm" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KYOYU) %>'>
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.public" /></th>
    <td>
    <logic:equal name="bmk030knForm" property="bmk030public" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_YES) %>'>
    <gsmsg:write key="cmn.public" />
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030public" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.KOKAI_NO) %>'>
    <logic:equal name="bmk030knForm" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
    <gsmsg:write key="cmn.private" />
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_GROUP) %>'>
    <gsmsg:write key="bmk.25" />
    </logic:equal>
    </logic:equal>
    </td>
    </tr>
    </logic:notEqual>

    <!-- メイン表示区分 -->
    <logic:equal name="bmk030knForm" property="bmk030mode" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.BMK_KBN_KOJIN) %>'>
    <tr>
    <th class="table_bg_A5B4E1" scope="row" align="left"><gsmsg:write key="cmn.main.view" /></th>
    <td>
    <logic:equal name="bmk030knForm" property="bmk030main" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_YES) %>'>
    <gsmsg:write key="cmn.show" />
    </logic:equal>
    <logic:equal name="bmk030knForm" property="bmk030main" value='<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.DSP_NO) %>'>
    <gsmsg:write key="cmn.hide" />
    </logic:equal>
    </td>
    </tr>
    </logic:equal>

    </table>

  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
    <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onClick="return buttonPush('bmk030knpushOk');">
    <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="return buttonPush('bmk030knpushBack');">
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