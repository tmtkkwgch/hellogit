<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />]<gsmsg:write key="wml.wml010.25" /><gsmsg:write key="cmn.from" /><gsmsg:write key="cmn.search" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "wml"; %>
<% thisForm = "mbhWml050Form"; %>
</head>
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml050">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="id" value="">
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
<html:hidden property="wml040adrMode" />

<bean:define id="mw050Form" name="mbhWml050Form" type="jp.groupsession.v3.mbh.wml050.MbhWml050Form" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('wml050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="sml.sml020.05" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div class="font_small" align="center">
  <html:select property="wml050Group" onchange="changeCombo();">
    <html:optionsCollection name="mbhWml050Form" property="grpCombo" value="value" label="label" />
  </html:select>
</div>

<logic:notEmpty name="mbhWml050Form" property="userList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider">
      <div align="center">
        <bean:write name="mbhWml050Form" property="wml050GroupName" />
      </div>
    </li>
    <logic:iterate id="userData" name="mbhWml050Form" property="userList">
      <li>
        <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.wml050.MbhWml050Form.PARAM_SELECTADR %><bean:write name="userData" property="userSid" />');"><bean:write name="userData" property="userName" /></a>
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div align="center">
  <div class="font_small">
    <input name="wml050Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back"/>
  </div>
</div>
</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>