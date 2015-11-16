<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="wml.wml010.25" /><gsmsg:write key="addressbook" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<link rel="stylesheet" href="../mobile/sp/css/default.css?<%= GSConst.VERSION_PARAM %>" />
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
<% thisForm = "mbhWml060Form"; %>
</head>

<body>

<html:form action="/mobile/mh_wml060">
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

<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010SelectInd" />
<html:hidden property="adr010SearchInd" />
<html:hidden property="adr010SearchTopFlg" />
<html:hidden property="adr010SearchKanaFlg" />
<html:hidden property="adr010page" />
<input type="hidden" name="mbhWml060Form" property="cmdMode" />
<b><%= emojiBook.toString() %><gsmsg:write key="wml.wml010.25" /></b>

<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
<br><b><gsmsg:write key="addressbook" /> - <gsmsg:write key="address.102" /></b>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
<br><b><gsmsg:write key="addressbook" /> - <gsmsg:write key="cmn.name" /><gsmsg:write key="cmn.list" /></b>
</logic:notEqual>
<hr>
<gsmsg:write key="mobile.12" />：
<input type="submit" name="comSearchBtn" value="<gsmsg:write key="address.139" />"/>
<input type="submit" name="nameSearchBtn" value="<gsmsg:write key="cmn.name" />"/>

<hr>
■<gsmsg:write key="address.adr100.1" />
<br>
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

<% String[] topKanaArray = (String[]) rowList.get(0); %>
<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     String topKana = topKanaArray[index]; %>
<%     int kanaInd = index; %>
<%--
<a href="../mobile/mh_wml060.do<%= jsessionId.toString() %>?CMD=search50tab&adr010cmdMode=<bean:write name="mbhWml060Form" property="adr010cmdMode" />&adr010SelectInd=<%= kanaInd %>&adr010SearchInd=-1&adr010SearchTopFlg=1&adr010selectLabel=<bean:write name="mbhWml060Form" property="adr010selectLabel" />"><%= topKana %></a>
--%>
<logic:equal name="mbhWml060Form" property="adr010SelectInd" value="<%= String.valueOf(kanaInd) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.wml060.MbhWml060Form.PARAM_SELECTADR_GJTOP %><%= String.valueOf(kanaInd) %>" value="<%= topKana %>" class="color_red"/>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010SelectInd" value="<%= String.valueOf(kanaInd) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.wml060.MbhWml060Form.PARAM_SELECTADR_GJTOP %><%= String.valueOf(kanaInd) %>" value="<%= topKana %>" />
</logic:notEqual>
<%   if (index == 4) { %>
<br>
<%   } %>
<% } %>
<br>
<logic:notEqual name="mbhWml060Form" property="adr010SelectInd" value="-1">
<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existComKanaList.contains(kana)) { %>
<%--
<a href="../mobile/mh_wml060.do<%= jsessionId.toString() %>?CMD=search50tab&adr010cmdMode=<bean:write name="mbhWml060Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhWml060Form" property="adr010SelectInd" />&adr010SearchInd=<%= rowIndex %>"><span class="user_sakuin_text"><%= kana %></span></a>
--%>
<logic:equal name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.wml060.MbhWml060Form.PARAM_SELECTADR_GJ %><%= rowIndex %>" value="<%= kana %>" class="color_red"/>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.wml060.MbhWml060Form.PARAM_SELECTADR_GJ %><%= rowIndex %>" value="<%= kana %>"/>
</logic:notEqual>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existKanaList.contains(kana)) { %>
<%--
<a href="../mobile/mh_wml060.do<%= jsessionId.toString() %>?CMD=search50tab&adr010cmdMode=<bean:write name="mbhWml060Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhWml060Form" property="adr010SelectInd" />&adr010SearchInd=<%= rowIndex %>&adr010SearchKanaFlg=1&adr010selectLabel=<bean:write name="mbhWml060Form" property="adr010selectLabel" />"><span class="user_sakuin_text"><%= kana %></span></a>
--%>
<logic:equal name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.wml060.MbhWml060Form.PARAM_SELECTADR_GJ_NAME %><%= rowIndex %>" value="<%= kana %>" class="color_red"/>
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.wml060.MbhWml060Form.PARAM_SELECTADR_GJ_NAME %><%= rowIndex %>" value="<%= kana %>"/>
</logic:notEqual>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:notEqual>
</logic:notEqual>

<logic:notEqual name="mbhWml060Form" property="adr010cmdMode" value="0">
<hr>
<gsmsg:write key="cmn.label" /><gsmsg:write key="cmn.search" />：
<html:select property="adr010selectLabel">
<html:optionsCollection name="mbhWml060Form" property="adr010Label" value="value" label="label" />
</html:select>
<gsmsg:define id="cmnSearch" msgkey="cmn.search" />
<br><html:submit property="doSearchBtn" value="<%= cmnSearch %>" />
<br>
</logic:notEqual>

<logic:equal name="mbhWml060Form" property="adr010cmdMode" value="0">
<logic:notEmpty name="mbhWml060Form" property="detailList">
<logic:iterate id="detailMdl" name="mbhWml060Form" property="detailList" indexId="idx">
<hr>
<logic:notEmpty name="detailMdl" property="mail1">
  <input name="<%= jp.groupsession.v3.mbh.wml050.MbhWml050Form.PARAM_SELECTADR_AD %><bean:write name="detailMdl" property="adrSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><%= emojiRsv.toString() %>
</logic:notEmpty>
<bean:write name="detailMdl" property="userName" />
<br>
<span class="text_link"><bean:write name="detailMdl" property="companyName" /></span>
</logic:iterate>
<logic:notEmpty  name="mbhWml060Form" property="pageCmbList">
<br>
<br>
<div align="center">
<bean:define id="maxPage" name="mbhWml060Form" property="adr010MaxPage" />
<logic:equal name="mbhWml060Form" property="adr010page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)<input type="submit" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:equal>
<logic:equal name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="prevPageBtn" value="<gsmsg:write key="cmn.previous" />"/>(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010page" value="1">
<logic:notEqual name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="prevPageBtn" value="<gsmsg:write key="cmn.previous" />"/>(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)<input type="submit" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:notEqual>
</logic:notEqual>
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
<logic:iterate id="detailMdl" name="mbhWml060Form" property="detailList" indexId="idx">
<hr>
<logic:notEmpty name="detailMdl" property="mail1">
  <input name="<%= jp.groupsession.v3.mbh.wml050.MbhWml050Form.PARAM_SELECTADR_AD %><bean:write name="detailMdl" property="adrSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><%= emojiRsv.toString() %>
</logic:notEmpty>
<bean:write name="detailMdl" property="userName" />
<br>
<span class="text_link"><bean:write name="detailMdl" property="companyName" /></span>
</logic:iterate>
<logic:notEmpty  name="mbhWml060Form" property="pageCmbList">
<br>
<br>
<div align="center">
<bean:define id="maxPage" name="mbhWml060Form" property="adr010MaxPage" />
<logic:equal name="mbhWml060Form" property="adr010page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)<input type="submit" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:equal>
<logic:equal name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="prevPageBtn" value="<gsmsg:write key="cmn.previous" />"/>(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhWml060Form" property="adr010page" value="1">
<logic:notEqual name="mbhWml060Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="prevPageBtn" value="<gsmsg:write key="cmn.previous" />"/>(<bean:write name="mbhWml060Form" property="adr010page" />/<bean:write name="mbhWml060Form" property="adr010MaxPage" />)<input type="submit" name="nextPageBtn" value="<gsmsg:write key="cmn.next" />"/>
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEmpty>
</logic:notEqual>

<div align="center">
  <br><input name="wml060Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>