<%@ page import="jp.co.sjts.util.http.BrowserUtil" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%-- METAタグ用のヘッダーファイルです --%>

<%--
    if (BrowserUtil.isAndroid(request)
            || BrowserUtil.isIPhone(request)
            || BrowserUtil.isIPod(request)
            ) {
        //android,iPhone,iPodはviewportを指定する
    }
--%>
<%
    String refUrl = "";
    if (request != null) {
        request.getHeader("Referer");
    }
%>

<% String pluginName = null; %>
<% String thisForm = null; %>
<% String usrTheme = "b"; %>
<% String radioClass = null; %>
<% String radioFont = null; %>
<% String radioFontActive = null; %>
<% String buttonClass = null; %>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<link REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="../common/images/favicon.png">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" charset="utf-8" />

<meta name="format-detection" content="telephone=no">

<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">

<link rel="stylesheet" href="../mobile/sp/css/default.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jquery-1.6.1.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<script src="../mobile/sp/js/jquery.mobile_config.js?<%= GSConst.VERSION_PARAM %>"></script>
<script src="../mobile/sp/js/sp_mobile.js?<%= GSConst.VERSION_PARAM %>"></script>
<script src="../mobile/sp/jquery.mobile-1.0.1/jquery.mobile-1.0.1.min.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel="stylesheet" href="../mobile/sp/jquery.mobile-1.0.1/jquery.mobile-1.0.1.css?<%= GSConst.VERSION_PARAM %>" />