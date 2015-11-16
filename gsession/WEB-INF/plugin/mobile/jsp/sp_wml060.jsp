<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<%@ page import="jp.groupsession.v2.adr.adr010.Adr010Const" %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="addressbook" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "wml"; %>
<% thisForm = "mbhWml060Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_wml060">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<input type="hidden" name="adId" value="">
<html:hidden property="wmlAccount" />
<html:hidden property="wmlDirectory" />
<html:hidden property="wmlLabel" />
<html:hidden property="wmlMailNum" />
<html:hidden property="wmlSendMode" />
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
<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010SelectInd" />
<html:hidden property="adr010SearchInd" />
<html:hidden property="adr010SearchTopFlg" />
<html:hidden property="adr010SearchKanaFlg" />
<html:hidden property="adr010page" />
<input type="hidden" name="mbhWml060Form" property="cmdMode" />
<bean:define id="maxPage" name="mbhWml060Form" property="adr010MaxPage" />
<bean:define id="mod" value="0" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('wml060back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/wml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="addressbook" /><br>
    <logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
      <b><gsmsg:write key="address.102" /></b>
    </logic:equal>
    <logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
      <b><gsmsg:write key="cmn.name" /><gsmsg:write key="cmn.list" /></b>
    </logic:notEqual>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<% String cmpColor = radioFont; %>
<% String nameColor = radioFont; %>

<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
  <% cmpColor = radioFontActive; %>
</logic:equal>
<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="1">
  <% nameColor = radioFontActive; %>
</logic:equal>

<fieldset data-role="controlgroup" data-type="horizontal" align="center">
  <html:radio name="mbhWml060Form" property="adr010cmdMode" value="0" styleId="company" onchange="buttonPush('company')"/>
  <label for="company" class="<%= radioClass %>"><span class="font_middle" style="color:<%= cmpColor %>"><gsmsg:write key="address.139" /></span></label>
  <html:radio name="mbhWml060Form" property="adr010cmdMode" value="1" styleId="name" onchange="buttonPush('name')"/>
  <label for="name" class="<%= radioClass %>"><span class="font_middle" style="color:<%= nameColor %>"><gsmsg:write key="cmn.name" /></span></label>
</fieldset>

<% java.util.List rowList = new java.util.ArrayList(); %>
<% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
<% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.a"), gsMsg.getMessage(request, "cmn.kana.ka"), gsMsg.getMessage(request, "cmn.kana.sa"), gsMsg.getMessage(request, "cmn.kana.ta"), gsMsg.getMessage(request, "cmn.kana.na"), gsMsg.getMessage(request, "cmn.kana.ha"), gsMsg.getMessage(request, "cmn.kana.ma"), gsMsg.getMessage(request, "cmn.kana.ya"), gsMsg.getMessage(request, "cmn.kana.ra"), gsMsg.getMessage(request, "cmn.kana.wa")}); %>
<% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.i"), gsMsg.getMessage(request, "cmn.kana.ki"), gsMsg.getMessage(request, "cmn.kana.shi"), gsMsg.getMessage(request, "cmn.kana.chi"), gsMsg.getMessage(request, "cmn.kana.ni"), gsMsg.getMessage(request, "cmn.kana.hi"), gsMsg.getMessage(request, "cmn.kana.mi"), "", gsMsg.getMessage(request, "cmn.kana.ri"), gsMsg.getMessage(request, "cmn.kana.wo")}); %>
<% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.u"), gsMsg.getMessage(request, "cmn.kana.ku"), gsMsg.getMessage(request, "cmn.kana.su"), gsMsg.getMessage(request, "cmn.kana.tsu"), gsMsg.getMessage(request, "cmn.kana.nu"), gsMsg.getMessage(request, "cmn.kana.fu"), gsMsg.getMessage(request, "cmn.kana.mu"), gsMsg.getMessage(request, "cmn.kana.yu"), gsMsg.getMessage(request, "cmn.kana.ru"), gsMsg.getMessage(request, "cmn.kana.n")}); %>
<% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.e"), gsMsg.getMessage(request, "cmn.kana.ke"), gsMsg.getMessage(request, "cmn.kana.se"), gsMsg.getMessage(request, "cmn.kana.te"), gsMsg.getMessage(request, "cmn.kana.ne"), gsMsg.getMessage(request, "cmn.kana.he"), gsMsg.getMessage(request, "cmn.kana.me"), "", gsMsg.getMessage(request, "cmn.kana.re"), ""}); %>
<% rowList.add(new String[]{gsMsg.getMessage(request, "cmn.kana.o"), gsMsg.getMessage(request, "cmn.kana.ko"), gsMsg.getMessage(request, "cmn.kana.so"), gsMsg.getMessage(request, "cmn.kana.to"), gsMsg.getMessage(request, "cmn.kana.no"), gsMsg.getMessage(request, "cmn.kana.ho"), gsMsg.getMessage(request, "cmn.kana.mo"), gsMsg.getMessage(request, "cmn.kana.yo"), gsMsg.getMessage(request, "cmn.kana.ro"), ""}); %>

<bean:define id="existComKanaList" name="mbhWml060Form" property="adr010comNameKanaList" type="java.util.List" />
<bean:define id="existKanaList" name="mbhWml060Form" property="adr010unameKanaList" type="java.util.List" />
<bean:define id="selectInd" name="mbhWml060Form" property="adr010SelectInd" type="java.lang.String"/>

<div align="center" class="font_xxsmall">
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<div data-role="navbar">
<% String[] topKanaArray = (String[]) rowList.get(0); %>
<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     if (index == 0 || index == 5) { %>
<ul>
<%     } %>
<%     String topKana = topKanaArray[index]; %>
<%     String kanaInd = String.valueOf(index); %>
<%     String color = null; %>
<logic:equal name="mbhWml060Form" property="adr010SelectInd" value="<%= kanaInd %>" >
<%     color = "#ff9f9f"; %>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010SelectInd" value="<%= kanaInd %>" >
<%     color = radioFontActive; %>
</logic:notEqual>
<li>
<a href="javascript:kanaTopBtn('search50tab', '<%= kanaInd %>', '-1', '1');" class="<%= buttonClass %>"><span style="color:<%= color %>"><%= topKana %></span></a>
</li>
<%     if (index == 4 || index == 9) { %>
</ul>
<%     } %>
<% } %>
</div>
</div>
</div>
<br>
<logic:notEqual name="mbhWml060Form" property="adr010SelectInd" value="-1">
<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
<div data-role="header" data-nobackbtn="true" data-theme="c">
<div data-role="navbar">
<ul>
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     String color = null; %>
<logic:equal name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#ff0000"; %>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#000000"; %>
</logic:notEqual>
<%     if (!kana.equals("")) { %>
<li>
<%       if (existComKanaList.contains(kana)) { %>
<a style="border-right: 1px solid #dbdbdb;" href="javascript:kanaBtn('search50tab', '<%= rowIndex %>');"><span class="txt_shadow1" style="color:<%= color %>"><%= kana %></span></a>
<%       } else { %>
<%         if (selInd == 7 || selInd == 9) { %>
<a style="visibility:hidden;">&nbsp;</a><div class="font_small"><div class="tl4"><span style="color:#a6a6a6;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= kana %></span></div></div>
<%         } else { %>
<a style="visibility:hidden;">&nbsp;</a><div class="font_small"><div class="tl4"><span style="color:#a6a6a6;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= kana %></span></div></div>
<%         } %>
<%       } %>
</li>
<%    } %>
<% } %>
</ul>
</div>
</div>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
<div data-role="header" data-nobackbtn="true" data-theme="c">
<div data-role="navbar">
<ul>
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     String color = null; %>
<logic:equal name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#ff0000"; %>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#000000"; %>
</logic:notEqual>
<%     if (!kana.equals("")) { %>
<li>
<%       if (existKanaList.contains(kana)) { %>
<a style="border-right: 1px solid #dbdbdb;" href="javascript:kanaBtn2('search50tab', '<%= rowIndex %>', 1);"><span class="txt_shadow1" style="color:<%= color %>"><%= kana %></span></a>
<%       } else { %>
<%         if (selInd == 7 || selInd == 9) { %>
<a style="visibility:hidden;">&nbsp;</a><div class="font_small"><div class="tl4"><span style="color:#a6a6a6;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= kana %></span></div></div>
<%         } else { %>
<a style="visibility:hidden;">&nbsp;</a><div class="font_small"><div class="tl4"><span style="color:#a6a6a6;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= kana %></span></div></div>
<%         } %>
<%       } %>
</li>
<%    } %>
<% } %>
</ul>
</div>
</div>
</logic:notEqual>
</logic:notEqual>

<logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
  <hr>
  <div class="font_xsmall">■<gsmsg:write key="cmn.label" /></div>
  <div class="font_xsmall" align="center">
    <html:select property="adr010selectLabel" onchange="changeCombo2('searchBtn');">
      <html:optionsCollection name="mbhWml060Form" property="adr010Label" value="value" label="label" />
    </html:select>
  </div>
</logic:notEqual>

<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
<logic:notEmpty name="mbhWml060Form" property="detailList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:greaterThan name="maxPage" value="1">
      <li data-role="list-divider">
        <div align="center">(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)</div>
      </li>
    </logic:greaterThan>
    <logic:iterate id="detailMdl" name="mbhWml060Form" property="detailList" indexId="idx">

      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:notEqual>

      <li data-theme=<bean:write name="liColor" />>
      <logic:notEmpty name="detailMdl" property="mail1">
        <a href="javascript:addUser2('<%= jp.groupsession.v3.mbh.wml050.MbhWml050Form.PARAM_SELECTADR_AD %><bean:write name="detailMdl" property="adrSid" />');">
      </logic:notEmpty>
      <div class="font_small">
        <logic:notEmpty name="detailMdl" property="mail1">
          <bean:write name="detailMdl" property="userName" />
        </logic:notEmpty>
        <logic:empty name="detailMdl" property="mail1">
          <bean:write name="detailMdl" property="userName" />
        </logic:empty>
        <br>
        <span class="text_link"><bean:write name="detailMdl" property="companyName" /></span>
      </div>
      <logic:notEmpty name="detailMdl" property="mail1">
        </a>
      </logic:notEmpty>
      </li>
    </logic:iterate>
  </ul>
<logic:notEmpty  name="mbhWml060Form" property="pageCmbList">

<div class="font_xsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:equal name="mbhWml060Form" property="adr010page" value="1">
<input type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:equal>
<logic:equal name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" data-inline="true" data-icon="arrow-l" name="prevPageBtn" value="<gsmsg:write key="cmn.previous" />"/>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010page" value="1">
<logic:notEqual name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" data-inline="true" data-icon="arrow-l" name="prevPageBtn" value="<gsmsg:write key="cmn.previous" />"/><input type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:notEqual>
</logic:notEqual>
</div>
</div>
</logic:notEmpty>
</logic:notEmpty>

<logic:empty name="mbhWml060Form" property="companyList">
<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
</logic:empty>
</logic:equal>

<logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:greaterThan name="maxPage" value="1">
      <li data-role="list-divider">
        <div align="center">(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)</div>
      </li>
    </logic:greaterThan>
    <logic:iterate id="detailMdl" name="mbhWml060Form" property="detailList" indexId="idx">

      <logic:equal name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(idx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:notEqual>

      <li data-theme=<bean:write name="liColor" />>
      <logic:notEmpty name="detailMdl" property="mail1">
        <a href="javascript:addUser2('<%= jp.groupsession.v3.mbh.wml050.MbhWml050Form.PARAM_SELECTADR_AD %><bean:write name="detailMdl" property="adrSid" />');">
      </logic:notEmpty>
      <div class="font_small">
        <logic:notEmpty name="detailMdl" property="mail1">
          <bean:write name="detailMdl" property="userName" />
        </logic:notEmpty>
        <logic:empty name="detailMdl" property="mail1">
          <bean:write name="detailMdl" property="userName" />
        </logic:empty>
        <br>
        <span class="text_link"><bean:write name="detailMdl" property="companyName" /></span>
      </div>
      <logic:notEmpty name="detailMdl" property="mail1">
        </a>
      </logic:notEmpty>
      </li>
    </logic:iterate>
  </ul>
<logic:notEmpty  name="mbhWml060Form" property="pageCmbList">
<div class="font_xsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<bean:define id="maxPage" name="mbhWml060Form" property="adr010MaxPage" />
<logic:equal name="mbhWml060Form" property="adr010page" value="1">
<input type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:equal>
<logic:equal name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="prevPageBtn" data-inline="true" data-icon="arrow-l" value="<gsmsg:write key="cmn.previous" />"/>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010page" value="1">
<logic:notEqual name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="prevPageBtn" data-inline="true" data-icon="arrow-l" value="<gsmsg:write key="cmn.previous" />"/><input type="submit" data-inline="true" data-icon="arrow-r" data-iconpos="right" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:notEqual>
</logic:notEqual>
</div>
</div>
</logic:notEmpty>
</logic:notEqual>

<div class="font_xsmall" align="center">
  <br><input name="wml060Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>