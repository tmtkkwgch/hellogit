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
<title>[<gsmsg:write key="ntp.1" />] <gsmsg:write key="addressbook" /><gsmsg:write key="addressbook" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
<% thisForm = "mbhNtp070Form"; %>
</head>

<body>

<html:form action="/mobile/mh_ntp070">
<html:hidden property="ntp070cmdMode" />
<html:hidden property="ntp070SelectInd" />
<html:hidden property="ntp070SearchInd" />
<html:hidden property="ntp070SearchTopFlg" />
<html:hidden property="ntp070SearchKanaFlg" />
<html:hidden property="ntp070page" />
<html:hidden property="cmd" />
<html:hidden property="dspMod" />
<html:hidden property="listMod" />
<html:hidden property="ntp040InitFlg" />
<html:hidden property="ntp040CopyFlg" />
<html:hidden property="ntp040ScrollFlg" />
<html:hidden property="ntp040BgcolorInit" />
<html:hidden property="ntp040DspMoveFlg" />

<html:hidden property="ntp040AnkenUse" />
<html:hidden property="ntp040CompanyUse" />
<html:hidden property="ntp040AnkenCompanyUse" />
<html:hidden property="ntp040KtBriHhuUse" />
<html:hidden property="ntp040MikomidoUse" />
<html:hidden property="ntp040TmpFileUse" />
<html:hidden property="ntp040NextActionUse" />
<html:hidden property="ntp040AdrHistoryPageNum" />
<html:hidden property="ntp040DefaultValue" />
<html:hidden property="ntp040DefaultValue2" />

<html:hidden property="ntp040InitYear" />
<html:hidden property="ntp040InitMonth" />
<html:hidden property="ntp040InitDay" />
<html:hidden property="ntp040ActYear" />
<html:hidden property="ntp040ActMonth" />
<html:hidden property="ntp040ActDay" />
<html:hidden property="ntp040HoukokuYear" />
<html:hidden property="ntp040HoukokuMonth" />
<html:hidden property="ntp040HoukokuDay" />

<html:hidden property="ntp040schUrl" />

<html:hidden property="ntp010DspDate" />
<html:hidden property="ntp010SelectUsrSid" />
<html:hidden property="ntp010SelectUsrAreaSid" />
<html:hidden property="ntp010SelectUsrKbn" />
<html:hidden property="ntp010SelectDate" />
<html:hidden property="ntp010NipSid" />
<html:hidden property="ntp010DspGpSid" />
<html:hidden property="ntp010searchWord" />
<html:hidden property="ntp020SelectUsrSid" />



<logic:notEmpty name="mbhNtp070Form"  property="ntp040FileList">
<logic:iterate id="tempmdl" name="mbhNtp070Form"  property="ntp040FileList">
<html:hidden name="tempmdl" property="binFileName" />
</logic:iterate>
</logic:notEmpty>

<input type="hidden" name="ntp040delCompanyId" value="">
<input type="hidden" name="ntp040delCompanyBaseId" value="">

<html:hidden property="addressPluginKbn" />
<html:hidden property="searchPluginKbn" />


<html:hidden property="ntp040FrYear" />
<html:hidden property="ntp040FrMonth" />
<html:hidden property="ntp040FrDay" />
<html:hidden property="ntp040FrHour" />
<html:hidden property="ntp040FrMin" />
<html:hidden property="ntp040ToHour" />
<html:hidden property="ntp040ToMin" />
<html:hidden property="ntp040Ktbunrui" />
<html:hidden property="ntp040Kthouhou" />
<html:hidden property="ntp040UsrName" />
<html:hidden property="ntp040AnkenSid" />
<html:hidden property="ntp040CompanySid" />
<html:hidden property="ntp040CompanyBaseSid" />
<html:hidden property="ntp040Mikomido" />
<html:hidden property="ntp040Bgcolor" />
<html:hidden property="ntp040Title" />
<html:hidden property="ntp040Value" />
<html:hidden property="ntp040ActDateKbn" />
<html:hidden property="ntp040NxtActYear" />
<html:hidden property="ntp040NxtActMonth" />
<html:hidden property="ntp040NxtActDay" />
<html:hidden property="ntp040NextAction" />

<logic:notEmpty name="mbhNtp070Form"  property="ntp040targetPrmList">
<logic:iterate id="targetPrmStr" name="mbhNtp070Form"  property="ntp040targetPrmList">
<input type="hidden" name="ntp040targetPrmList" value="<bean:write name="targetPrmStr"/>">
</logic:iterate>
</logic:notEmpty>


<input type="hidden" name="mbhNtp070Form" property="cmdMode" />
<b><%= emojiBook.toString() %><gsmsg:write key="addressbook" /> - </b>

<logic:equal name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<b><gsmsg:write key="address.102" /></b>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<b><gsmsg:write key="cmn.name" /><gsmsg:write key="cmn.list" /></b>
</logic:notEqual>

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

<bean:define id="existComKanaList" name="mbhNtp070Form" property="ntp070comNameKanaList" type="java.util.List" />
<bean:define id="existKanaList" name="mbhNtp070Form" property="ntp070unameKanaList" type="java.util.List" />
<bean:define id="selectInd" name="mbhNtp070Form" property="ntp070SelectInd" type="java.lang.String"/>
<%--
<% String[] topKanaArray = (String[]) rowList.get(0); %>
<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     String topKana = topKanaArray[index]; %>
<%     int kanaInd = index; %>
<a href="../mobile/mh_ntp070.do<%= jsessionId.toString() %>?CMD=search50tab&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070SelectInd=<%= kanaInd %>&ntp070SearchInd=-1&ntp070SearchTopFlg=1&ntp070selectLabel=<bean:write name="mbhNtp070Form" property="ntp070selectLabel" />"><%= topKana %></a>
<% } %>
<br>

<logic:notEqual name="mbhNtp070Form" property="ntp070SelectInd" value="-1">
<logic:equal name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existComKanaList.contains(kana)) { %>
<a href="../mobile/mh_ntp070.do<%= jsessionId.toString() %>?CMD=search50tab&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070SelectInd=<bean:write name="mbhNtp070Form" property="ntp070SelectInd" />&ntp070SearchInd=<%= rowIndex %>"><span class="user_sakuin_text"><%= kana %></span></a>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existKanaList.contains(kana)) { %>
<a href="../mobile/mh_ntp070.do<%= jsessionId.toString() %>?CMD=search50tab&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070SelectInd=<bean:write name="mbhNtp070Form" property="ntp070SelectInd" />&ntp070SearchInd=<%= rowIndex %>&ntp070SearchKanaFlg=1&ntp070selectLabel=<bean:write name="mbhNtp070Form" property="ntp070selectLabel" />"><span class="user_sakuin_text"><%= kana %></span></a>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
--%>



<% String[] topKanaArray = (String[]) rowList.get(0); %>
<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     String topKana = topKanaArray[index]; %>
<%     int kanaInd = index; %>
<%--
<a href="../mobile/mh_ntp070.do<%= jsessionId.toString() %>?CMD=search50tab&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070SelectInd=<%= kanaInd %>&ntp070SearchInd=-1&ntp070SearchTopFlg=1&ntp070selectLabel=<bean:write name="mbhNtp070Form" property="ntp070selectLabel" />"><%= topKana %></a>
--%>
<logic:equal name="mbhNtp070Form" property="ntp070SelectInd" value="<%= String.valueOf(kanaInd) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJTOP %><%= String.valueOf(kanaInd) %>" value="<%= topKana %>" class="color_red"/>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070SelectInd" value="<%= String.valueOf(kanaInd) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJTOP %><%= String.valueOf(kanaInd) %>" value="<%= topKana %>" />
</logic:notEqual>
<%   if (index == 4) { %>
<br>
<%   } %>
<% } %>
<br>
<logic:notEqual name="mbhNtp070Form" property="ntp070SelectInd" value="-1">
<logic:equal name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existComKanaList.contains(kana)) { %>
<%--
<a href="../mobile/mh_ntp070.do<%= jsessionId.toString() %>?CMD=search50tab&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070SelectInd=<bean:write name="mbhNtp070Form" property="ntp070SelectInd" />&ntp070SearchInd=<%= rowIndex %>"><span class="user_sakuin_text"><%= kana %></span></a>
--%>
<logic:equal name="mbhNtp070Form" property="ntp070SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJ %><%= rowIndex %>" value="<%= kana %>" class="color_red"/>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJ %><%= rowIndex %>" value="<%= kana %>"/>
</logic:notEqual>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existKanaList.contains(kana)) { %>
<%--
<a href="../mobile/mh_ntp070.do<%= jsessionId.toString() %>?CMD=search50tab&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070SelectInd=<bean:write name="mbhNtp070Form" property="ntp070SelectInd" />&ntp070SearchInd=<%= rowIndex %>&ntp070SearchKanaFlg=1&ntp070selectLabel=<bean:write name="mbhNtp070Form" property="ntp070selectLabel" />"><span class="user_sakuin_text"><%= kana %></span></a>
--%>
<logic:equal name="mbhNtp070Form" property="ntp070SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJ_NAME %><%= rowIndex %>" value="<%= kana %>" class="color_red"/>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070SearchInd" value="<%= String.valueOf(rowIndex) %>">
<input type="submit" name="<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJ_NAME %><%= rowIndex %>" value="<%= kana %>"/>
</logic:notEqual>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>




<br>
</logic:notEqual>
</logic:notEqual>

<logic:notEqual name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<hr>
<gsmsg:write key="cmn.label" /><gsmsg:write key="cmn.search" />：
<html:select property="ntp070selectLabel">
<html:optionsCollection name="mbhNtp070Form" property="ntp070Label" value="value" label="label" />
</html:select>
<gsmsg:define id="cmnSearch" msgkey="cmn.search" />
<br><html:submit property="doSearchBtn" value="<%= cmnSearch %>" />
<br>
</logic:notEqual>

<logic:equal name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<logic:notEmpty name="mbhNtp070Form" property="companyList">

<logic:notEmpty  name="mbhNtp070Form" property="pageCmbList">
<br>
<div align="center">
<bean:define id="maxPage" name="mbhNtp070Form" property="ntp070MaxPage" />
<logic:equal name="mbhNtp070Form" property="ntp070page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<input type="submit" name="ntp070next" value="次へ" />
</logic:equal>
<logic:equal name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" value="<gsmsg:write key="cmn.previous" />"/>(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="1">
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" value="<gsmsg:write key="cmn.previous" />"/>(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<input type="submit" name="ntp070next" value="次へ" />
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEmpty>

<br>

<logic:iterate id="companyData" name="mbhNtp070Form" property="companyList" indexId="idx">
<hr>

<logic:empty name="companyData" property="abaName">
<input name="<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_SELECTCMP_AD %><bean:write name="companyData" property="acoSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="companyData" property="acoName" />&nbsp;&nbsp;
</logic:empty>

<logic:notEmpty name="companyData" property="abaName">
<input name="<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_SELECTCMPBA_AD %><bean:write name="companyData" property="acoSid" />_<bean:write name="companyData" property="abaSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="companyData" property="acoName" />&nbsp;&nbsp;<bean:write name="companyData" property="abaName" />
</logic:notEmpty>

<br>
</logic:iterate>
<logic:notEmpty  name="mbhNtp070Form" property="pageCmbList">
<br>
<div align="center">
<bean:define id="maxPage" name="mbhNtp070Form" property="ntp070MaxPage" />
<logic:equal name="mbhNtp070Form" property="ntp070page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<input type="submit" name="ntp070next" value="次へ" />
</logic:equal>
<logic:equal name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="1">
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<input type="submit" name="ntp070next" value="次へ" />
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEmpty>
</logic:notEmpty>

<logic:empty name="mbhNtp070Form" property="companyList">
<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
</logic:empty>
</logic:equal>

<logic:notEqual name="mbhNtp070Form" property="ntp070cmdMode" value="0">
<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
<logic:iterate id="detailMdl" name="mbhNtp070Form" property="detailList" indexId="idx">
<hr>
<a href="../mobile/mh_adr020.do<%= jsessionId.toString() %>?ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />&ntp070EditAdrSid=<bean:write name="detailMdl" property="adrSid" />&ntp070SelectInd=<bean:write name="mbhNtp070Form" property="ntp070SelectInd" />&ntp070SearchInd=<bean:write name="mbhNtp070Form" property="ntp070SearchInd" />&ntp070page=<bean:write name="mbhNtp070Form" property="ntp070page" />&ntp070SearchTopFlg=<bean:write name="mbhNtp070Form" property="ntp070SearchTopFlg" />&ntp070SearchKanaFlg=<bean:write name="mbhNtp070Form" property="ntp070SearchKanaFlg" />&ntp070selectLabel=<bean:write name="mbhNtp070Form" property="ntp070selectLabel" />"><span class="text_link"><bean:write name="detailMdl" property="userName" /></span></a>
<br>
<a href="../mobile/mh_adr030.do<%= jsessionId.toString() %>?adr030editAcoSid=<bean:write name="detailMdl" property="acoSid" />&ntp070cmdMode=<bean:write name="mbhNtp070Form" property="ntp070cmdMode" />"><span class="text_link"><bean:write name="detailMdl" property="companyName" /></span></a>
</logic:iterate>
<logic:notEmpty  name="mbhNtp070Form" property="pageCmbList">
<br>
<br>
<div align="center">
<bean:define id="maxPage" name="mbhNtp070Form" property="ntp070MaxPage" />
<logic:equal name="mbhNtp070Form" property="ntp070page" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<input type="submit" name="ntp070next" value="次へ" />
</logic:equal>
<logic:equal name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<gsmsg:write key="cmn.next" />
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="1">
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" />(<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />)<input type="submit" name="ntp070next" value="次へ" />
</logic:notEqual>
</logic:notEqual>
</div>
</logic:notEmpty>
</logic:notEqual>

<div align="center">
<input name="ntp070back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>