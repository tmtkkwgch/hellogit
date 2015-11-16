<%@ page import="jp.co.sjts.util.http.BrowserUtil" %>

<%-- METAタグ用のヘッダーファイルです --%>

<%
if (request != null) {
    if (BrowserUtil.isAndroid(request)
            || BrowserUtil.isIPhone(request)
            || BrowserUtil.isIPod(request)
            ) {
        //android,iPhone,iPodはviewportを指定する
%>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />

<!--
<link rel="stylesheet" href="../mobile/sp/css/jquery.mobile-1.0a2.min.css" />
<link rel="stylesheet" href="../mobile/sp/css/default.css" />
<script src="../mobile/sp/js/jquery-1.4.4.min.js"></script>
<script src="../mobile/sp/js/jquery.mobile-1.0a2.min.js"></script>
 -->

<%

    }
}

%>