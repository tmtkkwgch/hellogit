<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<%@ page import="jp.groupsession.v2.adr.adr010.Adr010Const" %>
<html:html>
<head>
<title>[<gsmsg:write key="ntp.1" />] <gsmsg:write key="addressbook" /><gsmsg:write key="addressbook" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "ntp"; %>
<% thisForm = "mbhNtp070Form"; %>; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_ntp070">
<input type="hidden" name="gjId"/>
<input type="hidden" name="companyId"/>
<input type="hidden" name="mobileType" value="1">
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

<div data-role="header" data-nobackbtn="true" align="center" data-theme="<%= usrTheme %>">
  <a href="#" onClick="buttonPush('sch050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
    <h1><gsmsg:write key="addressbook" /><br><gsmsg:write key="cmn.search" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">


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
<logic:equal name="mbhNtp070Form" property="ntp070SelectInd" value="<%= kanaInd %>" >
<%     color = "#ff9f9f"; %>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070SelectInd" value="<%= kanaInd %>" >
<%     color = radioFontActive; %>
</logic:notEqual>
<li>
  <a href="#" onclick="clickGj('<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJTOP %><%= String.valueOf(kanaInd) %>');" class="<%= buttonClass %>"><span style="color:<%= color %>"><%= topKana %></span></a>
</li>
<%     if (index == 4 || index == 9) { %>
</ul>
<%     } %>
<% } %>
</div>
</div>
</div>

<br>

<logic:notEqual name="mbhNtp070Form" property="ntp070SelectInd" value="-1">
<logic:equal name="mbhNtp070Form" property="ntp070cmdMode" value="0">

<div data-role="header" data-nobackbtn="true" data-theme="c">
<div data-role="navbar">
<ul>
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     String color = null; %>
<logic:equal name="mbhNtp070Form" property="ntp070SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#ff0000"; %>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#000000"; %>
</logic:notEqual>
<%     if (!kana.equals("")) { %>
<li>
<%       if (existComKanaList.contains(kana)) { %>
<a style="border-right: 1px solid #dbdbdb;" href="#" onclick="clickGj('<%= jp.groupsession.v3.mbh.ntp070.MbhNtp070Form.PARAM_SELECTADR_GJ %><%= rowIndex %>');"><span class="txt_shadow1" style="color:<%= color %>"><%= kana %></span></a>
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
<div align="center" class="font_xsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<bean:define id="maxPage" name="mbhNtp070Form" property="ntp070MaxPage" />
<logic:equal name="mbhNtp070Form" property="ntp070page" value="1">
<input type="submit" name="ntp070next" value="次へ" data-inline="true" data-role="button" data-icon="arrow-r"  data-iconpos="right"/>
</logic:equal>
<logic:equal name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" value="<gsmsg:write key="cmn.previous" />" data-inline="true" data-role="button" data-icon="arrow-l"/>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="1">
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" value="<gsmsg:write key="cmn.previous" />" data-inline="true" data-role="button" data-icon="arrow-l" /><input type="submit" name="ntp070next" value="次へ" data-inline="true" data-role="button" data-icon="arrow-r"  data-iconpos="right"/>
</logic:notEqual>
</logic:notEqual>
</div>
</div>
</logic:notEmpty>

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

<logic:notEmpty  name="mbhNtp070Form" property="pageCmbList">
<li data-role="list-divider" style="background:#ffffff;">
<div align="center">
<bean:write name="mbhNtp070Form" property="ntp070page" />/<bean:write name="mbhNtp070Form" property="ntp070MaxPage" />
</div>
</li>
</logic:notEmpty>

<logic:iterate id="companyData" name="mbhNtp070Form" property="companyList" indexId="idx">
<li>

<logic:empty name="companyData" property="abaName">
<a href="#" onclick="clickComp('<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_SELECTCMP_AD %><bean:write name="companyData" property="acoSid" />')">
<bean:write name="companyData" property="acoName" />
</a>
</logic:empty>

<logic:notEmpty name="companyData" property="abaName">
<a href="#" onclick="clickComp('<%= jp.groupsession.v3.mbh.ntp040.MbhNtp040Form.PARAM_SELECTCMPBA_AD %><bean:write name="companyData" property="acoSid" />_<bean:write name="companyData" property="abaSid" />')">
<bean:write name="companyData" property="acoName" />&nbsp;&nbsp;<bean:write name="companyData" property="abaName" />
</a>
</logic:notEmpty>

</li>
</logic:iterate>
</ul>
<logic:notEmpty  name="mbhNtp070Form" property="pageCmbList">
<div align="center" class="font_xsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<bean:define id="maxPage" name="mbhNtp070Form" property="ntp070MaxPage" />
<logic:equal name="mbhNtp070Form" property="ntp070page" value="1">
<input type="submit" name="ntp070next" value="次へ" data-inline="true" data-role="button" data-icon="arrow-r"  data-iconpos="right"/>
</logic:equal>
<logic:equal name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" value="<gsmsg:write key="cmn.previous" />" data-inline="true" data-role="button" data-icon="arrow-l"/>
</logic:equal>
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="1">
<logic:notEqual name="mbhNtp070Form" property="ntp070page" value="<%= String.valueOf(maxPage) %>" >
<input type="submit" name="ntp070prev" value="<gsmsg:write key="cmn.previous" />" data-inline="true" data-role="button" data-icon="arrow-l" /><input type="submit" name="ntp070next" value="次へ" data-inline="true" data-role="button" data-icon="arrow-r"  data-iconpos="right"/>
</logic:notEqual>
</logic:notEqual>
</div>
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


<div align="center">
<input name="ntp070back" value="<gsmsg:write key="cmn.back" />" type="submit"  data-inline="true" data-role="button" data-icon="back">
</div>

</div><!-- /content -->


</html:form>

</div><!-- /page -->

</body>
</html:html>