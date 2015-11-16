<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% String svKeyWord = ""; %>
<% thisForm = "mbhWml020Form"; %>
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

<html:form action="/mobile/mh_wml020">
<html:hidden name="mbhWml020Form" property="wmlAccount" />
<html:hidden name="mbhWml020Form" property="wmlDirectory" />
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
<html:hidden property="wml020svSearchKeyword" />
<html:hidden property="wml020svSearchKeywordKbn" />
<html:hidden property="wml020svSearchReadKbn" />
<html:hidden property="wml020svSearchTempFile" />
<html:hidden property="wml020DetaileSrhFlg" />
<html:hidden property="wml020DirKbn" />
<b><%= emojiMail.toString() %><gsmsg:write key="wml.wml010.25" /><br><bean:write name="mbhWml020Form" property="wml020Title" />
<logic:notEqual name="mbhWml020Form" property="wml020DetaileSrhFlg" value="1">
<gsmsg:write key="cmn.mail" /><gsmsg:write key="cmn.list" />
</logic:notEqual>
</b>

<hr>

<logic:notEqual name="mbhWml020Form" property="wml020DetaileSrhFlg" value="1">
<html:text name="mbhWml020Form" size="27" property="wml020svKeyword" />
<input name="mbhWml020Search" value="<gsmsg:write key="cmn.search" />" type="submit">
</logic:notEqual>
<input name="mbhWml020advancedSearch" value="<gsmsg:write key="cmn.advanced.search" />" type="submit">

<logic:equal name="mbhWml020Form" property="wml020SearchFlg" value="1">
<hr>
<div align = center>
<gsmsg:write key="wml.js.74" />
</div>
</logic:equal>

<hr>
<bean:define id="wml020Form" name="mbhWml020Form" type="jp.groupsession.v3.mbh.wml020.MbhWml020Form" />

<logic:greaterThan name="mbhWml020Form" property="wml020MaxPage" value="1">
<div align="center">
  <input name="mbhWml020previous" value="<gsmsg:write key="cmn.previous" />" type="submit">
  (<bean:write name="mbhWml020Form" property="wml020selectPage" />/<bean:write name="mbhWml020Form" property="wml020MaxPage" />)
  <input name="mbhWml020next" value="<gsmsg:write key="cmn.next" />" type="submit">
</div>
</logic:greaterThan>

<logic:notEmpty name="mbhWml020Form" property="mailList">

<logic:iterate id="mailData" name="mbhWml020Form" property="mailList">
  <% String style=""; %><logic:equal name="mailData" property="readed" value="true"><% style=" style=\"color:#990099\""; %></logic:equal>
  <br>

  <logic:equal name="mailData" property="forward" value="true">
    <img alt="Fw" src="../mobile/images/img_forward.gif">
  </logic:equal>

  <logic:equal name="mailData" property="reply" value="true">
    <img alt="Re" src="../mobile/images/img_henshin.gif">
  </logic:equal>

  <b><bean:write name="mailData" property="dateString" />
  <br>
  <logic:equal name="mbhWml020Form" property="wmlSendDirectory" value="false">
  ■<gsmsg:write key="wml.from" />：<bean:write name="mailData" property="from" filter="false"/>
  </logic:equal>
  <logic:equal name="mbhWml020Form" property="wmlSendDirectory" value="true">
  ■<gsmsg:write key="cmn.from" />：<bean:write name="mailData" property="listTo" />
  </logic:equal>
  <br>
  <bean:define id="mailSubject" name="mailData" property="subject" type="java.lang.String" />
  <%
    jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String nothing = gsMsg.getMessage(request, "cmn.no");
  if (mailSubject == null || mailSubject.length() == 0) { mailSubject = nothing; }

  %>
  <logic:notEqual name="mbhWml020Form" property="wml020DirKbn" value="4">
  ■<gsmsg:write key="cmn.subject" />：<%= mailSubject %><br/>
  <input name="<%= jp.groupsession.v3.mbh.wml020.MbhWml020Form.PARAM_SELECTADR %><bean:write name="mailData" property="mailNum" />" value="<gsmsg:write key="cmn.show" />" type="submit">
  </logic:notEqual>
  <logic:equal name="mbhWml020Form" property="wml020DirKbn" value="4">
  ■<gsmsg:write key="cmn.subject" />：<%= mailSubject %><br/>
  <input name="<%= jp.groupsession.v3.mbh.wml020.MbhWml020Form.PARAM_SELECTADR %><bean:write name="mailData" property="mailNum" />" value="<gsmsg:write key="cmn.show" />" type="submit">
  </logic:equal>
  </b>
  <hr>
</logic:iterate>
</logic:notEmpty>
<logic:empty name="mbhWml020Form" property="mailList">
<br/>
<gsmsg:write key="wml.js.41" />
<br/>
</logic:empty>
<html:hidden name="mbhWml020Form" property="wmlAccount" />
<html:hidden name="mbhWml020Form" property="wmlDirectory" />

<logic:greaterThan name="mbhWml020Form" property="wml020MaxPage" value="1">
<div align="center">
  <input name="mbhWml020previous" value="<gsmsg:write key="cmn.previous" />" type="submit">
  (<bean:write name="mbhWml020Form" property="wml020selectPage" />/<bean:write name="mbhWml020Form" property="wml020MaxPage" />)
  <input name="mbhWml020next" value="<gsmsg:write key="cmn.next" />" type="submit">
</div>
<hr>
</logic:greaterThan>

<br>

■<gsmsg:write key="cmn.move" />・<gsmsg:write key="cmn.label" />
<br>
<gsmsg:define id="cmnLabel" msgkey="cmn.label" />
<gsmsg:define id="cmnMove" msgkey="cmn.move" />
<html:select name="mbhWml020Form" property="wmlLabel">
　　<optgroup label="<%= cmnLabel %>">
    <html:option value="-1"><gsmsg:write key="cmn.specified.no" /></html:option>
    <html:optionsCollection name="mbhWml020Form" property="labelList" value="id" label="name" />
  </optgroup>
  <optgroup label="<%= cmnMove %>">
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_JUSHIN) %>"><gsmsg:write key="cmn.receive" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_SOSHIN) %>"><gsmsg:write key="wml.19" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_SOKOU) %>"><gsmsg:write key="cmn.draft" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_GOMI) %>"><gsmsg:write key="cmn.trash" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_HOKAN) %>"><gsmsg:write key="cmn.strage" /></html:option>
  </optgroup>
</html:select>
<gsmsg:define id="cmnSearch" msgkey="cmn.select" />
<input name="mbhWml020Select" value="<%= cmnSearch %>" type="submit">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<input name="mbhWml020Back" value="<gsmsg:write key="cmn.back" />" type="submit">

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>