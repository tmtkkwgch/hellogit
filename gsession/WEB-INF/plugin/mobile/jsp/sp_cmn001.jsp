<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.regex.*" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% String gsMobileUrl = jp.groupsession.v3.mbh.GSConstMobileHtml.GS_MOBILE_HOMEPAGE_URL; %>

<!-- ブラウザ識別（IEのみ） -->
<%
  String sUserAgent = request.getHeader("user-agent");
  Pattern pattern = Pattern.compile(".*((MSIE)+ [0-9]\\.[0-9]).*");
  Matcher matcher = pattern.matcher(sUserAgent);
  boolean ieFlg = matcher.matches();
%>

<!DOCTYPE html>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="mobile.17" /></title>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>

</head>

<body>

<% if (ieFlg) { %>

<!-- IE -->
<html:form action="/mobile/sp_cmn001">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="login">

<span style="color: red"><html:errors/></span>
<logic:equal name="mbhCmn001Form" property="licenseFlg" value="0">
<gsmsg:write key="mobile.6" /><br>
<gsmsg:write key="mobile.6.2" />
</logic:equal>

<logic:notEqual name="mbhCmn001Form" property="licenseFlg" value="0">
  <div align="center">
    <b><gsmsg:write key="mobile.30" /><br><gsmsg:write key="mobile.31" /></b>
    <br>
    <a rel="external" href="../common/cmn001.do" target="_blank"><gsmsg:write key="mobile.7" /></a>
    <a rel="external" href="<%= gsMobileUrl %>" target="_blank"><gsmsg:write key="mobile.4" /></a>
  </div>

  <div class="font_small" align="center">
    <hr>
    GroupSession Ver <%= version %>
    <br>Copyright (C) 日本トータルシステム株式会社</h1>
    <hr>
  </div>
</logic:notEqual>
</html:form>

<% } else { %>

<!-- IE以外 -->
<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_cmn001">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <h1><gsmsg:write key="mobile.5" /><br><gsmsg:write key="mobile.17" /></h1>
</div><!-- /header -->

<div data-role="content">

    <input type="hidden" name="CMD" value="login">

    <logic:equal name="mbhCmn001Form" property="licenseFlg" value="0">

      <html:errors />

      <br><gsmsg:write key="cmn.user.id" />
      <br><input type="text" name="cmn001Userid" maxlength="20" size="20" tabindex="1">
    　　　　
      <br><gsmsg:write key="user.117" />
      <br><input type="password" name="cmn001Passwd" maxlength="20" size="20" tabindex="2">
      <br><input type="submit" value="<gsmsg:write key="mobile.17" />" class="login_btn" tabindex="3">

      <div data-role="content">
　　          <div data-role="controlgroup" data-type="horizontal" align="center">
          <a rel="external" href="../common/cmn001.do?mobileType=1" data-inline="true" data-role="button" data-icon="arrow-r" target="_blank"><gsmsg:write key="mobile.7" /></a>
        </div>
      </div><!-- /content -->
    </logic:equal>

    <logic:notEqual name="mbhCmn001Form" property="licenseFlg" value="0">
      <div data-role="content" align="center">
        <b><gsmsg:write key="mobile.30" /><br><gsmsg:write key="mobile.31" /></b>
        <br>
        <a rel="external" href="../common/cmn001.do?mobileType=1" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right" target="_blank"><gsmsg:write key="mobile.7" /></a>
        <a rel="external" href="<%= gsMobileUrl %>" data-inline="true" data-role="button" data-icon="arrow-r" target="_blank" data-iconpos="right"><gsmsg:write key="mobile.4" /></a>
      </div><!-- /content -->
    </logic:notEqual>

</div><!-- /content -->

<div data-role="footer" data-theme="<%= usrTheme %>">
  <br>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_footer.jsp" %>
</div><!-- /footer -->

</html:form>

</div><!-- /page -->

<% } %>

</body>

</html:html>