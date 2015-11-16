<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.wml.wml010.Wml010Const" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhWml070Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="cmn.list" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>
<% String svFromWord = ""; %>
<% String svToWord = ""; %>
<% String svKeyWord = ""; %>
<html:form action="/mobile/mh_wml070">
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wml020svSearchDateType" />

<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /><br><bean:write name="mbhWml070Form" property="wml020Title" /><gsmsg:write key="cmn.advanced.search" /></b>
<hr>
<logic:notEmpty name="mbhWml070Form" property="wml070message">
  <logic:iterate id="data" name="mbhWml070Form" property="wml070message">
    <span style="color:#ff0000;"><bean:write name="data" /></span><br/>
  </logic:iterate>
</logic:notEmpty>
○From<br/>
<html:text name="mbhWml070Form" size="27" maxlength="21" property="wml020svSearchFrom" /><br/>
○To<br/>
<html:text name="mbhWml070Form" size="27" maxlength="21" property="wml020svSearchTo" /><br/>
○日付<br/>
<logic:equal name="mbhWml070Form" property="wml070searchDateType" value="1">
&nbsp;From:
    <html:select property="wml020svSearchDateYearFr" styleId="wml020svSearchDateYearFr" style="vertical-align:middle;">
    <html:optionsCollection name="mbhWml070Form" property="yearCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.year" />&nbsp;
    <html:select property="wml020svSearchDateMonthFr" styleId="wml020svSearchDateMonthFr" style="vertical-align:middle;">
    <html:optionsCollection name="mbhWml070Form" property="monthCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.month" />&nbsp;
    <html:select property="wml020svSearchDateDayFr" styleId="wml020svSearchDateDayFr" style="vertical-align:middle;">
    <html:optionsCollection name="mbhWml070Form" property="dayCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.day" />


<br/>
&nbsp;To&nbsp;&nbsp;&nbsp;:&nbsp;
    <html:select name="mbhWml070Form" property="wml020svSearchDateYearTo" styleId="wml020svSearchDateYearTo" style="vertical-align:middle;">
    <html:optionsCollection name="mbhWml070Form" property="yearCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.year" />&nbsp;
    <html:select name="mbhWml070Form" property="wml020svSearchDateMonthTo" styleId="wml020svSearchDateMonthTo" style="vertical-align:middle;">
    <html:optionsCollection name="mbhWml070Form" property="monthCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.month" />&nbsp;
    <html:select name="mbhWml070Form" property="wml020svSearchDateDayTo" styleId="wml020svSearchDateDayTo" style="vertical-align:middle;">
    <html:optionsCollection name="mbhWml070Form" property="dayCombo" value="value" label="label" />
    </html:select><gsmsg:write key="cmn.day" /></br>
    <input name="mbhWml070NoPeriod" value="<gsmsg:write key="cmn.not.specified" />" type="submit">
</logic:equal>
<logic:notEqual name="mbhWml070Form" property="wml070searchDateType" value="1">
  <input name="mbhWml070Period" value="<gsmsg:write key="cmn.period" />" type="submit">
</logic:notEqual>
<br/>
○<gsmsg:write key="cmn.keyword" /><br/>
  <html:select name="mbhWml070Form" property="wml020svSearchKeywordKbn" style="width:180px">
  <html:optionsCollection name="mbhWml070Form" property="keywordCombo" value="value" label="label" />
  </html:select><br/>
  <html:text name="mbhWml070Form" size="27" maxlength="21" property="wml020svSearchKeyword" styleClass="text_base" styleId="selectionSearchArea" /><br/>
○<gsmsg:write key="wml.wml010.01" /><br/>
    <html:radio name="mbhWml070Form" property="wml020svSearchReadKbn" styleId="searchReadKbn0" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_NOSET) %>" /><label for="searchReadKbn0"><gsmsg:write key="cmn.not.specified" /></label>
    <html:radio name="mbhWml070Form" property="wml020svSearchReadKbn" styleId="searchReadKbn1" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_NOREAD) %>" /><label for="searchReadKbn1"><gsmsg:write key="cmn.read.yet" /></label>
    <html:radio name="mbhWml070Form" property="wml020svSearchReadKbn" styleId="searchReadKbn2" value="<%= String.valueOf(Wml010Const.SEARCH_READKBN_READED) %>" /><label for="searchReadKbn2"><gsmsg:write key="cmn.read.already" /></label><br/>
○<gsmsg:write key="cmn.attach.file" /><br/>
<html:checkbox name="mbhWml070Form" property="wml020svSearchTempFile" styleId="searchTempFile" value="1" /><label for="searchTempFile"><gsmsg:write key="wml.wml010.06" /></label>
<br/>
<br/>

<div align="center">
  <input name="mbhWml070Search" value="<gsmsg:write key="cmn.search" />" type="submit">
  <input name="mbhWml070Cancel" value="<gsmsg:write key="cmn.cancel" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>