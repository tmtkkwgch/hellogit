<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-jqText.tld" prefix="jquery" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.wml.wml010.Wml010Const" %>
<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="cmn.list" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.css?<%= GSConst.VERSION_PARAM %>" />
<link rel="stylesheet" href="../mobile/sp/css/jquery.ui.datepicker.mobile.css?<%= GSConst.VERSION_PARAM %>" />
<script src="../mobile/sp/js/jQuery.ui.datepicker.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
<script src="../mobile/sp/js/jquery.ui.datepicker.mobile.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>

<logic:equal name="mbhWml070Form" property="mobileLang" value="0">
<script src="../mobile/sp/js/jquery.ui.datepicker-ja.js?<%= GSConst.VERSION_PARAM %>" type="text/javascript"></script>
</logic:equal>

<script type="text/javascript">
$(document).ready(function(){
  $("#date").datepicker();
});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $("#date2").datepicker();
});
</script>
<% pluginName = "wml"; %>
<% thisForm = "mbhWml070Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml070">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wml020svSearchDateType" />
<input type="hidden" name="wml020selectPage" value="1">

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('wml030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="cmn.advanced.search" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhWml070Form" property="wml070message">
  <logic:iterate id="data" name="mbhWml070Form" property="wml070message">
    <span style="color:#ff0000;"><bean:write name="data" /></span><br/>
  </logic:iterate>
</logic:notEmpty>
<div class="font_xsmall">■From</div>
<html:text name="mbhWml070Form" size="27" maxlength="21" property="wml020svSearchFrom" /><br/>
<div class="font_xsmall">■To</div>
<html:text name="mbhWml070Form" size="27" maxlength="21" property="wml020svSearchTo" /><br/>
<div class="font_xsmall">■<gsmsg:write key="cmn.date2" /></div>
<logic:equal name="mbhWml070Form" property="wml070searchDateType" value="1">
&nbsp;From:
<jquery:jqtext id="date" name="mbhWml070Form" property="wml020FrDate" readonly="true"/>

<br/>
&nbsp;To&nbsp;&nbsp;&nbsp;:&nbsp;
<jquery:jqtext id="date2" name="mbhWml070Form" property="wml020ToDate" readonly="true"/>
    <span class="font_small">
      <input name="mbhWml070NoPeriod" value="<gsmsg:write key="cmn.not.specified" />" type="submit">
    </span>
</logic:equal>
<logic:notEqual name="mbhWml070Form" property="wml070searchDateType" value="1">
  <span class="font_small">
    <input name="mbhWml070Period" value="<gsmsg:write key="cmn.period" />" type="submit">
  </span>
</logic:notEqual>
<br/>
<div class="font_xsmall">■<gsmsg:write key="cmn.keyword" /></div>
  <span class="font_small">
    <html:select name="mbhWml070Form" property="wml020svSearchKeywordKbn">
    <html:optionsCollection name="mbhWml070Form" property="keywordCombo" value="value" label="label" />
    </html:select><br/>
    <html:text name="mbhWml070Form" size="27" maxlength="21" property="wml020svSearchKeyword" styleClass="text_base" styleId="selectionSearchArea" /><br/>
  </span>
<div class="font_xsmall">■<gsmsg:write key="wml.wml010.01" /></div>
<fieldset data-role="controlgroup" data-type="horizontal" align="center">
    <html:radio name="mbhWml070Form" property="wml020svSearchReadKbn" styleId="searchReadKbn0" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_NOSET) %>" /><label for="searchReadKbn0"><span class="font_small"><gsmsg:write key="cmn.not.specified" /></span></label>
    <html:radio name="mbhWml070Form" property="wml020svSearchReadKbn" styleId="searchReadKbn1" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_NOREAD) %>" /><label for="searchReadKbn1"><span class="font_small"><gsmsg:write key="cmn.read.yet" /></span></label>
    <html:radio name="mbhWml070Form" property="wml020svSearchReadKbn" styleId="searchReadKbn2" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_READED) %>" /><label for="searchReadKbn2"><span class="font_small"><gsmsg:write key="cmn.read.already" /></span></label>
</fieldset>
<div class="font_xsmall">■<gsmsg:write key="cmn.attach.file" /></div>
  <span class="font_small">
    <html:checkbox name="mbhWml070Form" property="wml020svSearchTempFile" styleId="searchTempFile" value="1" /><label for="searchTempFile"><gsmsg:write key="wml.wml010.06" /></label>
  </span>
<br/>
<br/>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <div class="font_middle">
    <input name="mbhWml070Search" value="&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.search" />&nbsp;&nbsp;&nbsp;"  type="submit" data-inline="true" data-icon="search" />
    <input name="mbhWml070Cancel" value="<gsmsg:write key="cmn.cancel" />" type="submit" data-inline="true" data-icon="refresh" data-iconpos="right"/>
  </div>
</div>




</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>