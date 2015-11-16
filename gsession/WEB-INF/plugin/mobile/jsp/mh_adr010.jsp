<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<%@ page import="jp.groupsession.v2.adr.adr010.Adr010Const" %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="addressbook" /><gsmsg:write key="addressbook" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
<% thisForm = "mbhAdr010Form"; %>
</head>

<body>

<html:form action="/mobile/mh_adr010">
<html:hidden property="adr010cmdMode" />
<html:hidden property="adr010SelectInd" />
<html:hidden property="adr010SearchInd" />
<html:hidden property="adr010SearchTopFlg" />
<html:hidden property="adr010SearchKanaFlg" />
<input type="hidden" name="mbhAdr010Form" property="cmdMode" />
<b><%= emojiBook.toString() %><gsmsg:write key="addressbook" /> - </b>

<logic:equal name="mbhAdr010Form" property="adr010cmdMode" value="0">
<b><gsmsg:write key="address.102" /></b>
</logic:equal>
<logic:notEqual name="mbhAdr010Form" property="adr010cmdMode" value="0">
<b><gsmsg:write key="cmn.name" /><gsmsg:write key="cmn.list" /></b>
</logic:notEqual>
<hr>
<gsmsg:write key="mobile.12" />：
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?adr010cmdMode=0"><gsmsg:write key="address.139" /><a>
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?adr010cmdMode=1"><gsmsg:write key="cmn.name" /><a>
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

<bean:define id="existComKanaList" name="mbhAdr010Form" property="adr010comNameKanaList" type="java.util.List" />
<bean:define id="existKanaList" name="mbhAdr010Form" property="adr010unameKanaList" type="java.util.List" />
<bean:define id="selectInd" name="mbhAdr010Form" property="adr010SelectInd" type="java.lang.String"/>

<% String[] topKanaArray = (String[]) rowList.get(0); %>
<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     String topKana = topKanaArray[index]; %>
<%     int kanaInd = index; %>
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=search50tab&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SelectInd=<%= kanaInd %>&adr010SearchInd=-1&adr010SearchTopFlg=1&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><%= topKana %></a>
<% } %>
<br>

<logic:notEqual name="mbhAdr010Form" property="adr010SelectInd" value="-1">
<logic:equal name="mbhAdr010Form" property="adr010cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existComKanaList.contains(kana)) { %>
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=search50tab&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<%= rowIndex %>"><span class="user_sakuin_text"><%= kana %></span></a>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:equal>
<logic:notEqual name="mbhAdr010Form" property="adr010cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existKanaList.contains(kana)) { %>
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=search50tab&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<%= rowIndex %>&adr010SearchKanaFlg=1&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><span class="user_sakuin_text"><%= kana %></span></a>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:notEqual>
</logic:notEqual>

<logic:notEqual name="mbhAdr010Form" property="adr010cmdMode" value="0">
<hr>
<gsmsg:write key="cmn.label" /><gsmsg:write key="cmn.search" />：
<html:select property="adr010selectLabel">
<html:optionsCollection name="mbhAdr010Form" property="adr010Label" value="value" label="label" />
</html:select>
<gsmsg:define id="cmnSearch" msgkey="cmn.search" />
<br><html:submit property="doSearchBtn" value="<%= cmnSearch %>" />
<br>
</logic:notEqual>

<logic:equal name="mbhAdr010Form" property="adr010cmdMode" value="0">
<logic:notEmpty name="mbhAdr010Form" property="companyList">
<logic:iterate id="companyData" name="mbhAdr010Form" property="companyList" indexId="idx">
<hr>
<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?adr030editAcoSid=<bean:write name="companyData" property="acoSid" />"><bean:write name="companyData" property="companyName" /></span></a>
<br>
</logic:iterate>
<logic:notEmpty  name="mbhAdr010Form" property="pageCmbList">
<br>
<div align="center">
<bean:define id="maxPage" name="mbhAdr010Form" property="adr010MaxPage" />
<logic:equal name="mbhAdr010Form" property="adr010page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhAdr010Form" property="adr010page" />/<bean:write name="mbhAdr010Form" property="adr010MaxPage" />)<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=nextPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />"><gsmsg:write key="cmn.next" /></a>
</logic:equal>
<logic:equal name="mbhAdr010Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=prevPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhAdr010Form" property="adr010page" />/<bean:write name="mbhAdr010Form" property="adr010MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhAdr010Form" property="adr010page" value="1">
<logic:notEqual name="mbhAdr010Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
  <a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=prevPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhAdr010Form" property="adr010page" />/<bean:write name="mbhAdr010Form" property="adr010MaxPage" />)<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=nextPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />"><gsmsg:write key="cmn.next" /></a>
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEmpty>
</logic:notEmpty>

<logic:empty name="mbhAdr010Form" property="companyList">
<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
</logic:empty>
</logic:equal>

<logic:notEqual name="mbhAdr010Form" property="adr010cmdMode" value="0">
<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
<logic:iterate id="detailMdl" name="mbhAdr010Form" property="detailList" indexId="idx">
<hr>
<a href="../mobile/mh_adr020.do<%= jsessionId.toString() %>?adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010EditAdrSid=<bean:write name="detailMdl" property="adrSid" />&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />&adr010SearchKanaFlg=<bean:write name="mbhAdr010Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><span class="text_link"><bean:write name="detailMdl" property="userName" /></span></a>
<br>
<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?adr030editAcoSid=<bean:write name="detailMdl" property="acoSid" />&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />"><span class="text_link"><bean:write name="detailMdl" property="companyName" /></span></a>
</logic:iterate>
<logic:notEmpty  name="mbhAdr010Form" property="pageCmbList">
<br>
<br>
<div align="center">
<bean:define id="maxPage" name="mbhAdr010Form" property="adr010MaxPage" />
<logic:equal name="mbhAdr010Form" property="adr010page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhAdr010Form" property="adr010page" />/<bean:write name="mbhAdr010Form" property="adr010MaxPage" />)<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=nextPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SearchKanaFlg=<bean:write name="mbhAdr010Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><gsmsg:write key="cmn.next" /></a>
</logic:equal>
<logic:equal name="mbhAdr010Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=prevPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SearchKanaFlg=<bean:write name="mbhAdr010Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhAdr010Form" property="adr010page" />/<bean:write name="mbhAdr010Form" property="adr010MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhAdr010Form" property="adr010page" value="1">
<logic:notEqual name="mbhAdr010Form" property="adr010page" value="<%= String.valueOf(maxPage) %>" >
  <a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=prevPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SearchKanaFlg=<bean:write name="mbhAdr010Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhAdr010Form" property="adr010page" />/<bean:write name="mbhAdr010Form" property="adr010MaxPage" />)<a href="../mobile/mh_adr010.do<%= jsessionId.toString() %>?CMD=nextPage&adr010SelectInd=<bean:write name="mbhAdr010Form" property="adr010SelectInd" />&adr010SearchInd=<bean:write name="mbhAdr010Form" property="adr010SearchInd" />&adr010page=<bean:write name="mbhAdr010Form" property="adr010page" />&adr010SearchTopFlg=<bean:write name="mbhAdr010Form" property="adr010SearchTopFlg" />&adr010cmdMode=<bean:write name="mbhAdr010Form" property="adr010cmdMode" />&adr010SearchKanaFlg=<bean:write name="mbhAdr010Form" property="adr010SearchKanaFlg" />&adr010selectLabel=<bean:write name="mbhAdr010Form" property="adr010selectLabel" />"><gsmsg:write key="cmn.next" /></a>
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEmpty>
</logic:notEqual>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>