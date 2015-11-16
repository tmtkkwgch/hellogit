<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" />(<gsmsg:write key="sml.sml030.08" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "wml"; %>
<% thisForm = "mbhWml030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml030">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wmlLabel" />
<html:hidden property="wmlMailNum" />
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
<html:hidden property="wml020FrDate" />
<html:hidden property="wml020ToDate" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('wml030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="sml.sml030.08" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div>
      <logic:equal name="mbhWml030Form" property="forward" value="true">
        <img alt="Fw" src="../mobile/images/img_forward.gif">
      </logic:equal>

      <logic:equal name="mbhWml030Form" property="reply" value="true">
        <img alt="Re" src="../mobile/images/img_henshin.gif">
      </logic:equal>

      <bean:write name="mbhWml030Form" property="wml030Date" />
    </div>
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="wml.from" /></div>
    <bean:write name="mbhWml030Form" property="wml030From" />
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.subject" /></div>
    <bean:write name="mbhWml030Form" property="wml030Subject" />
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.from" /></div>
    <bean:write name="mbhWml030Form" property="wml030To" />
  </li>
  <logic:notEmpty name="mbhWml030Form" property="wml030Cc">
    <li>
      <div class="font_xsmall">■CC</div>
      <bean:write name="mbhWml030Form" property="wml030Cc" />
    </li>
  </logic:notEmpty>
  <logic:notEmpty name="mbhWml030Form" property="wml030Bcc">
    <li>
      <div class="font_xsmall">■BCC</div>
      <bean:write name="mbhWml030Form" property="wml030Bcc" />
    </li>
  </logic:notEmpty>
  <logic:notEmpty name="mbhWml030Form" property="wml030TempFileList">
    <li>
      <div class="font_xsmall" align="center"><gsmsg:write key="cmn.attached" /></div>
    </li>
      <logic:iterate id="tempMdl" name="mbhWml030Form" property="wml030TempFileList" indexId="idx">
        <li>
<%--           <a href="../webmail/wml010.do?mobileType=1&CMD=downloadFile&wml010downloadFileId=<bean:write name="tempMdl" property="binSid" />"><span class="color_blue"><bean:write name="tempMdl" property="fileName" />&nbsp;<bean:write name="tempMdl" property="fileSizeDsp" /></span></a> --%>
          <a href="../webmail/wml010.do?CMD=downloadFile&wml010downloadMessageNum=<bean:write name="mbhWml030Form" property="wmlMailNum"/>&wmlViewAccount=<bean:write name="mbhWml030Form" property="wmlAccount"/>&wml010downloadFileId=<bean:write name="tempMdl" property="binSid" />"><span class="color_blue"><bean:write name="tempMdl" property="fileName" />&nbsp;<bean:write name="tempMdl" property="fileSizeDsp" /></span></a>
        </li>
      </logic:iterate>
  </logic:notEmpty>
</ul>

<div class="text_box">
  <div class="font_xsmall" align="center"><gsmsg:write key="cmn.content" /></div>
  <hr>
  <b><bean:write name="mbhWml030Form" property="wml030Body" filter="false"/></b>
</div>
<gsmsg:define id="cmnStrage" msgkey="cmn.strage" />
<div class="font_small">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="wml030Reply" value="<gsmsg:write key="cmn.reply" />" type="submit" data-inline="true" data-icon="forward"/>
  <input name="wml030ReplyAll" value="<gsmsg:write key="sml.67" />" type="submit" data-inline="true" data-icon="forward"/>
  <input name="wml030Forwarding" value="<gsmsg:write key="cmn.forward" />" type="submit" data-inline="true" data-icon="forward"/>
</div>
<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="wml030DustMail" value="<gsmsg:write key="cmn.delete" />" type="submit" data-inline="true" data-icon="delete"/>
  <logic:notEqual name="mbhWml030Form" property="wml020Title" value="<%= cmnStrage %>">
    <input name="wml030KeepMail" value="<gsmsg:write key="cmn.strage" />" type="submit" data-inline="true" data-icon="check" data-iconpos="right"/>
  </logic:notEqual>
</div>
<div align="center">
  <input name="wml030Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>