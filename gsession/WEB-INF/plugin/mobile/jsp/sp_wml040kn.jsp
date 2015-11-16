<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" />(<gsmsg:write key="cmn.check" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "wml"; %>
<% thisForm = "mbhWml040knForm"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml040kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wmlLabel" />
<html:hidden property="wmlMailNum" />
<html:hidden property="wmlSendMode" />
<html:hidden property="wml020SearchFlg" />
<html:hidden property="wml020selectPage" />
<html:hidden property="wml020MaxPage" />
<html:hidden property="wml020svSearchFrom" />
<html:hidden property="wml020svSearchTo" />
<html:hidden property="wml020svSearchDateType" />
<html:hidden property="wml020svSearchDateYearFr" />
<html:hidden property="wml020svSearchDateMonthFr" />
<html:hidden property="wml020svSearchDateDayFr" />
<html:hidden property="wml020svSearchDateYearTo" />
<html:hidden property="wml020svSearchDateMonthTo" />
<html:hidden property="wml020svSearchDateDayTo" />
<html:hidden property="wml020svKeyword" />
<html:hidden property="wml020svSearchKeyword" />
<html:hidden property="wml020svSearchKeywordKbn" />
<html:hidden property="wml020svSearchReadKbn" />
<html:hidden property="wml020svSearchTempFile" />
<html:hidden property="wml020DetaileSrhFlg" />
<html:hidden property="wml020FrDate" />
<html:hidden property="wml020ToDate" />
<html:hidden property="wml040initFlg" />
<html:hidden property="wml040Account" />
<html:hidden property="wml040svAccount" />
<html:hidden property="wml040Subject" />
<html:hidden property="wml040To" />
<html:hidden property="wml040Cc" />
<html:hidden property="wml040Bcc" />
<html:hidden property="wml040Body" />
<html:hidden property="wml040mode" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('wml040knBack');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="cmn.check" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.from" /></div>
    <bean:write name="mbhWml040knForm" property="wml040To" />
  </li>
  <logic:notEmpty name="mbhWml040knForm" property="wml040Cc">
    <li>
      <div class="font_small">■CC</div>
      <bean:write name="mbhWml040knForm" property="wml040Cc" />
    </li>
  </logic:notEmpty>
  <logic:notEmpty name="mbhWml040knForm" property="wml040Bcc">
    <li>
      <div class="font_small">■BCC</div>
      <bean:write name="mbhWml040knForm" property="wml040Bcc" />
    </li>
  </logic:notEmpty>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.subject" /></div>
    <bean:write name="mbhWml040knForm" property="wml040Subject" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.body" /></div>
    <bean:write name="mbhWml040knForm" property="wml040knViewBody" filter="false" />
  </li>
</ul>

  <!-- 添付ファイル -->
  <logic:notEmpty name="mbhWml040knForm" property="wml040FileLabelList">
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li><span class="font_small">■<gsmsg:write key="cmn.attach.file" /></span></li>
    <logic:iterate id="file" name="mbhWml040knForm" property="wml040FileLabelList" indexId="idx" scope="request">
      <li><bean:write name="file" property="label" /></li>
    </logic:iterate>
    </ul>
  </logic:notEmpty>

<div align="center">
<div class="font_small">
  <logic:equal name="mbhWml040knForm" property="wml040mode" value="1">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <br><input name="wml040knDraft" value="OK" type="submit" data-inline="true" data-role="button" data-icon="check" /><input name="wml040knBack" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-iconpos="right" />
    </div>
  </logic:equal>
  <logic:notEqual name="mbhWml040knForm" property="wml040mode" value="1">
    <div data-role="controlgroup" data-type="horizontal" align="center">
      <br><input name="wml040knSend" value="<gsmsg:write key="cmn.sent" />" type="submit" data-inline="true" data-role="button" data-icon="forward" /><input name="wml040knBack" value="<gsmsg:write key="cmn.back" />" type="submit" data-icon="back" data-iconpos="right" />
    </div>
  </logic:notEqual>
</div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>