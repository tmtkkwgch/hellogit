<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.bbs090kn.1" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs080Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs080">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<html:hidden name="mbhBbs080Form" property="bbs010page1" />
<html:hidden name="mbhBbs080Form" property="bbs010forumSid" />
<html:hidden name="mbhBbs080Form" property="threadSid" />
<html:hidden name="mbhBbs080Form" property="bbs030page1" />
<html:hidden name="mbhBbs080Form" property="bbs030writeSid" />
<html:hidden name="mbhBbs080Form" property="bbs030orderKey" />
<html:hidden name="mbhBbs080Form" property="bbs070cmdMode" />
<html:hidden name="mbhBbs080Form" property="bbs070value" />
<html:hidden name="mbhBbs080Form" property="bbs070threTitle" />
<html:hidden name="mbhBbs070Form" property="bbs070contributor" />
<html:hidden name="mbhBbs070Form" property="bbs070InitFlg" />


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('bbs080back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="bbs.bbs090kn.1" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <b><div class="font_small"><bean:write name="mbhBbs080Form" property="bbs070forumName" /></div></b>
      <hr>
      <b><bean:write name="mbhBbs080Form" property="bbs070threTitle" /></b>
    </div>
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.contributor" /></div><br>
    <b><bean:write name="mbhBbs080Form" property="bbs080knviewContributor" /></b>
  </li>
  <li>
    <div class="font_xsmall">■<gsmsg:write key="cmn.content" /></div><br>
    <b><bean:write name="mbhBbs080Form" property="bbs080viewvalue" filter="false" /></b>
  </li>
<!--   添付ファイル -->
  <logic:notEmpty name="mbhBbs080Form" property="bbs070FileLabelList">
    <li><span class="font_xsmall">■<gsmsg:write key="cmn.attach.file" /></span></li>
    <logic:iterate id="file" name="mbhBbs080Form" property="bbs070FileLabelList" indexId="idx" scope="request">
      <li><bean:write name="file" property="label" /></li>
    </logic:iterate>
  </logic:notEmpty>
</ul>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="bbs080ok" value="<gsmsg:write key="cmn.final" />" type="submit" data-inline="true" data-icon="check" />
  <input name="bbs080back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->
</body>
</html:html>