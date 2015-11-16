<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs070kn.1" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs060Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs060">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<input type="hidden" name="mobileType" value="1">
<html:hidden name="mbhBbs060Form" property="s_key" />
<html:hidden name="mbhBbs060Form" property="bbs010page1" />
<html:hidden name="mbhBbs060Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs060Form" property="searchDspID" />
<html:hidden name="mbhBbs060Form" property="bbs080page1" />
<html:hidden name="mbhBbs060Form" property="bbs080writeSid" />
<html:hidden name="mbhBbs060Form" property="bbs080orderKey" />

<html:hidden name="mbhBbs060Form" property="threadSid" />
<html:hidden name="mbhBbs060Form" property="bbs040cmdMode" />
<html:hidden name="mbhBbs060Form" property="bbs040forumName" />
<html:hidden name="mbhBbs060Form" property="bbs040title" />
<html:hidden name="mbhBbs060Form" property="bbs040value" />
<html:hidden name="mbhBbs060Form" property="bbs040limit" />
<html:hidden name="mbhBbs060Form" property="bbs040limitYear" />
<html:hidden name="mbhBbs060Form" property="bbs040limitMonth" />
<html:hidden name="mbhBbs060Form" property="bbs040limitDay" />
<html:hidden name="mbhBbs060Form" property="bbs040contributor" />

<html:hidden name="mbhBbs060Form" property="bbs060TmpFileId" />

<html:hidden property="bbs040InitFlg" />


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('bbs060back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="cmn.thread.create" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <b><bean:write name="mbhBbs060Form" property="bbs040forumName" /></b>
    </div>
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.contributor" /></div><br>
    <b><bean:write name="mbhBbs060Form" property="bbs060knviewContributor" /></b>
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.title" /></div><br>
    <b><bean:write name="mbhBbs060Form" property="bbs040title" /></b>
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.content" /></div><br>
    <b><bean:write name="mbhBbs060Form" property="bbs060viewvalue" filter="false" /></b>
  </li>
    <!-- 添付ファイル -->
  <logic:notEmpty name="mbhBbs060Form" property="bbs040FileLabelList">
    <li><span class="font_xsmall">■<gsmsg:write key="cmn.attach.file" /></span></li>
    <logic:iterate id="file" name="mbhBbs060Form" property="bbs040FileLabelList" indexId="idx" scope="request">
      <li><bean:write name="file" property="label" /></li>
    </logic:iterate>
  </logic:notEmpty>
  <li><div class="font_xsmall">■<gsmsg:write key="bbs.12" /></div>
    <div align="center">
      <b><logic:equal name="mbhBbs060Form" property="bbs040limit" value="0"><gsmsg:write key="cmn.unlimited" /></logic:equal>
      <logic:notEqual name="mbhBbs060Form" property="bbs040limit" value="0">
      <bean:define id="yr" name="mbhBbs060Form" property="bbs040limitYear" type="java.lang.Integer" />
      <gsmsg:write key="cmn.year" arg0="<%= String.valueOf(yr) %>" /><bean:write name="mbhBbs060Form" property="bbs040limitMonth"/><gsmsg:write key="cmn.month" /><bean:write name="mbhBbs060Form" property="bbs040limitDay"/><gsmsg:write key="cmn.day" />
      </logic:notEqual>
      </b>
    </div>
  </li>
</ul>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="bbs060ok" value="<gsmsg:write key="cmn.final" />" type="submit" data-inline="true" data-icon="check" />
  <input name="bbs060back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->
</body>
</html:html>