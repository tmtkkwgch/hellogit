<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="cmn.list" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "wml"; %>
<% String svKeyWord = ""; %>
<% boolean openFlg = true; %>
<% thisForm = "mbhWml020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml020">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden name="mbhWml020Form" property="wmlAccount" />
<html:hidden name="mbhWml020Form" property="wmlDirectory" />
<html:hidden name="mbhWml020Form" property="wmlAccount" />
<html:hidden name="mbhWml020Form" property="wmlDirectory" />
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
<html:hidden property="wml020svSearchKeyword" />
<html:hidden property="wml020svSearchKeywordKbn" />
<html:hidden property="wml020svSearchReadKbn" />
<html:hidden property="wml020svSearchTempFile" />
<html:hidden property="wml020DetaileSrhFlg" />
<html:hidden property="wml020DirKbn" />
<html:hidden property="wml020FrDate" />
<html:hidden property="wml020ToDate" />


<bean:define id="wml020Form" name="mbhWml020Form" type="jp.groupsession.v3.mbh.wml020.MbhWml020Form" />


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="./sp_wml010.do" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="wml.wml010.25" /><br><gsmsg:write key="cmn.mail" /><gsmsg:write key="cmn.list" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhWml020Form" property="wml020svKeyword">
  <bean:define id="svKeyStr" name="mbhWml020Form" property="wml020svKeyword" type="java.lang.String" />
  <% svKeyWord = svKeyStr; %>
</logic:notEmpty>

<logic:equal name="mbhWml020Form" property="wml020SearchFlg" value="1">
  <% openFlg = false; %>
</logic:equal>

<logic:notEqual name="mbhWml020Form" property="wml020DetaileSrhFlg" value="1">
<div data-role="collapsible" data-collapsed="<%= openFlg %>">
  <h2>
    <div align="center"><div class="font_xxsmall"><gsmsg:write key="wml.search.mail" /></div></div>
  </h2>
  <table>
  <tr>
  <td>
    <html:text name="mbhWml020Form" size="27" property="wml020svKeyword" />
  </td>
  <td>
    <div class="font_xxsmall">
      <input name="mbhWml020Search" value="&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.search" />&nbsp;&nbsp;&nbsp;" type="submit" data-inline="true" data-role="button" data-icon="search">
      <input name="mbhWml020advancedSearch" value="<gsmsg:write key="cmn.advanced.search" />" type="submit" data-inline="true" data-role="button" data-icon="search">
    </div>
  </td>
  </tr>
  </table>
</div>
</logic:notEqual>
<logic:equal name="mbhWml020Form" property="wml020DetaileSrhFlg" value="1">
  <div align="center">
    <input name="mbhWml020advancedSearch" value="<gsmsg:write key="cmn.advanced.search" />" type="submit" data-inline="true" data-role="button" data-icon="search">
  </div>
</logic:equal>
<hr>

<logic:equal name="mbhWml020Form" property="wml020SearchFlg" value="1">
<div class="title_1" align="center">
<b><gsmsg:write key="wml.js.74" /></b>
</div>
</logic:equal>

<logic:greaterThan name="mbhWml020Form" property="wml020MaxPage" value="1">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <div class="font_xsmall">
    <input name="mbhWml020previous" value="<gsmsg:write key="cmn.previous" />" type="submit" data-inline="true" data-icon="arrow-l" />
    <input name="mbhWml020next" value="<gsmsg:write key="cmn.next" />" type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right" />
  </div>
</div>
</logic:greaterThan>

<logic:notEmpty name="mbhWml020Form" property="mailList">
<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

  <li data-role="list-divider">
    <div align="center">
      <b><div class="font_middle">
        <bean:write name="mbhWml020Form" property="wml020Title" />
        <logic:greaterThan name="mbhWml020Form" property="wml020MaxPage" value="1">
          (<bean:write name="mbhWml020Form" property="wml020selectPage" />/<bean:write name="mbhWml020Form" property="wml020MaxPage" />)
        </logic:greaterThan>
      </div></b>
      <logic:greaterThan name="maxPage" value="1"><div class="font_small">(<bean:write name="mbhSml020Form" property="sml020PageNum" />/<bean:write name="mbhSml020Form" property="sml020MaxPageNum" />)</div></logic:greaterThan>
    </div>
  </li>

  <bean:define id="mod" value="0" />
  <logic:iterate id="mailData" name="mbhWml020Form" property="mailList" indexId="labelIdx">

    <logic:equal name="mod" value="<%= String.valueOf(labelIdx.intValue() % 2) %>">
      <bean:define id="liColor" value="c" />
    </logic:equal>
    <logic:notEqual name="mod" value="<%= String.valueOf(labelIdx.intValue() % 2) %>">
      <bean:define id="liColor" value="d" />
    </logic:notEqual>

  <% String style=""; %><logic:equal name="mailData" property="readed" value="true"><% style=" style=\"color:#990099\""; %></logic:equal>
  <li data-theme=<bean:write name="liColor" />>
    <logic:notEqual name="mbhWml020Form" property="wml020DirKbn" value="4">
      <a href="#" onClick="buttonPush3('wml020look', <bean:write name="mailData" property="mailNum" />);" <%= style %>>
    </logic:notEqual>
    <logic:equal name="mbhWml020Form" property="wml020DirKbn" value="4">
        <a href="#" onClick="buttonPush3('wml020edit', <bean:write name="mailData" property="mailNum" />);" <%= style %>>
      </logic:equal>



      <div class="font_small">

      <logic:equal name="mailData" property="forward" value="true">
        <img alt="Fw" src="../mobile/images/img_forward.gif">
      </logic:equal>

      <logic:equal name="mailData" property="reply" value="true">
        <img alt="Re" src="../mobile/images/img_henshin.gif">
      </logic:equal>

      <bean:write name="mailData" property="dateString" /></div>
    <div class="font_small">
      <logic:equal name="mbhWml020Form" property="wmlSendDirectory" value="false">
      ■<gsmsg:write key="wml.from" /><br><bean:write name="mailData" property="from" filter="false"/>
      </logic:equal>
      <logic:equal name="mbhWml020Form" property="wmlSendDirectory" value="true">
      ■<gsmsg:write key="cmn.from" /><br><bean:write name="mailData" property="listTo" filter="false"/>
      </logic:equal>
      <br>
      <bean:define id="mailSubject" name="mailData" property="subject" type="java.lang.String" />
  <%
    jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage();
        String nothing = gsMsg.getMessage(request, "cmn.no");
  if (mailSubject == null || mailSubject.length() == 0) { mailSubject = nothing; }

  %>

      ■<gsmsg:write key="cmn.subject" />
    </div>


    <logic:notEqual name="mbhWml020Form" property="wml020DirKbn" value="4">
      <%= mailSubject %></a>
    </logic:notEqual>
    <logic:equal name="mbhWml020Form" property="wml020DirKbn" value="4">
        <%= mailSubject %></a>
      </logic:equal>

  </li>
  </logic:iterate>
</ul>
</logic:notEmpty>
<logic:empty name="mbhWml020Form" property="mailList">
<br/>
<div align="center">
<gsmsg:write key="wml.js.41" />
</div>
<br/>
</logic:empty>
<html:hidden name="mbhWml020Form" property="wmlAccount" />
<html:hidden name="mbhWml020Form" property="wmlDirectory" />

<logic:greaterThan name="mbhWml020Form" property="wml020MaxPage" value="1">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <div class="font_xsmall">
    <input name="mbhWml020previous" value="<gsmsg:write key="cmn.previous" />" type="submit" data-inline="true" data-icon="arrow-l" />
    <input name="mbhWml020next" value="<gsmsg:write key="cmn.next" />" type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right" />
  </div>
</div>
</logic:greaterThan>
<gsmsg:define id="cmnLabel" msgkey="cmn.label" />
<gsmsg:define id="cmnMove" msgkey="cmn.move" />
<div class="font_small">■<gsmsg:write key="cmn.move" />・<gsmsg:write key="cmn.label" /><gsmsg:write key="cmn.select" /></div>
<div class="font_small">
<html:select name="mbhWml020Form" property="wmlLabel" onchange="changeCombo2('wml020change');">
　　<optgroup label="■&nbsp;<%= cmnLabel %>">
　　　　<html:option value="-1"><gsmsg:write key="cmn.specified.no" /></html:option>
　　　　<html:optionsCollection name="mbhWml020Form" property="labelList" value="id" label="name" />
  </optgroup>
  <optgroup label="■&nbsp;<%= cmnMove %>">
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_JUSHIN) %>"><gsmsg:write key="cmn.receive" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_SOSHIN) %>"><gsmsg:write key="wml.19" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_SOKOU) %>"><gsmsg:write key="cmn.draft" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_GOMI) %>"><gsmsg:write key="cmn.trash" /></html:option>
　　　　<html:option value="<%= String.valueOf(jp.groupsession.v3.mbh.wml020.MbhWml020Form.WML_HOKAN) %>"><gsmsg:write key="cmn.strage" /></html:option>
  </optgroup>
</html:select>
</div>

<hr>

<div align="center">
  <a href="./sp_wml010.do" data-inline="true" data-role="button" data-icon="back"><div class="font_xsmall"><gsmsg:write key="cmn.back" /></div></a>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>