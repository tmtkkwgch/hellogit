<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shain.info" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "usrTop"; %>
<% thisForm = "mbhUsr010Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_usr010">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="CMD" value="">
<html:hidden property="usr010cmdMode" />
<html:hidden property="usr010SelectInd" />
<html:hidden property="usr010SearchInd" />
<html:hidden property="usr010SearchTopFlg" />
<html:hidden property="usr010SearchKanaFlg" />
<bean:define id="maxCnt" name="mbhUsr010Form" property="usr010maxPageNum" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/usr_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shain.info" /> <br>
    <logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <b><gsmsg:write key="cmn.group" /></b>
    </logic:equal>
    <logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <b><gsmsg:write key="cmn.name" /></b>
    </logic:notEqual>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<%--
<div data-role="controlgroup" data-type="horizontal" align="center" class="font_small">
  <a href="../mobile/sp_usr010.do?mobileType=1&usr010cmdMode=2" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.group" /></a>
  <a href="../mobile/sp_usr010.do?mobileType=1&usr010cmdMode=1" data-role="button" data-icon="arrow-r" data-inline="true" data-iconpos="right"><gsmsg:write key="cmn.name" /></a>
</div>
--%>
<% String grpColor = radioFont; %>
<% String nameColor = radioFont; %>

<logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
  <% grpColor = radioFontActive; %>
</logic:equal>
<logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="1">
  <% nameColor = radioFontActive; %>
</logic:equal>

<fieldset data-role="controlgroup" data-type="horizontal" align="center">
  <html:radio name="mbhUsr010Form" property="usr010cmdMode" value="2" styleId="company" onchange="buttonPush('group')"/>
  <label for="company" class="<%= radioClass %>"><span class="font_middle" style="color:<%= grpColor %>"><gsmsg:write key="cmn.group" /></span></label>
  <html:radio name="mbhUsr010Form" property="usr010cmdMode" value="1" styleId="name" onchange="buttonPush('name')"/>
  <label for="name" class="<%= radioClass %>"><span class="font_middle" style="color:<%= nameColor %>">&nbsp;&nbsp;&nbsp;&nbsp;<gsmsg:write key="cmn.name" />&nbsp;&nbsp;&nbsp;&nbsp;</span></label>
</fieldset>

<hr>
<logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
<% java.util.List rowList = new java.util.ArrayList(); %>
<% rowList.add(new String[]{"ア", "カ", "サ", "タ", "ナ", "ハ", "マ", "ヤ", "ラ", "ワ"}); %>
<% rowList.add(new String[]{"イ", "キ", "シ", "チ", "ニ", "ヒ", "ミ", "", "リ", "ヲ"}); %>
<% rowList.add(new String[]{"ウ", "ク", "ス", "ツ", "ヌ", "フ", "ム", "ユ", "ル", "ン"}); %>
<% rowList.add(new String[]{"エ", "ケ", "セ", "テ", "ネ", "ヘ", "メ", "", "レ", ""}); %>
<% rowList.add(new String[]{"オ", "コ", "ソ", "ト", "ノ", "ホ", "モ", "ヨ", "ロ", ""}); %>

<bean:define id="existKanaList" name="mbhUsr010Form" property="usr010kanas" type="java.util.List" />
<bean:define id="selectInd" name="mbhUsr010Form" property="usr010SelectInd" type="java.lang.String"/>

<div align="center" class="font_xxsmall">
<% String[] topKanaArray = (String[]) rowList.get(0); %>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<div data-role="navbar">

<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     if (index == 0 || index == 5) { %>
<ul>
<%     } %>
<%     String topKana = topKanaArray[index]; %>
<%     String kanaInd = String.valueOf(index); %>
<%     String color = null; %>
<logic:equal name="mbhUsr010Form" property="usr010SelectInd" value="<%= kanaInd %>" >
<%     color = "#ff9f9f"; %>
</logic:equal>
<logic:notEqual name="mbhUsr010Form" property="usr010SelectInd" value="<%= kanaInd %>" >
<%     color = radioFontActive; %>
</logic:notEqual>
<li>
  <a href="../mobile/sp_usr010.do?CMD=searchKn&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010SelectInd=<%= kanaInd %>&usr010SearchInd=-1&usr010SearchTopFlg=1&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />" data-inline="true" data-role="button" class="<%= buttonClass %>"><span style="color:<%= color %>"><%= topKana %></span></a>
</li>
<%     if (index == 4 || index == 9) { %>
</ul>
<%     } %>
<% } %>

</div>
</div>
</div>

<%--

<br>


<div class="font_small" align="center">
  <html:select property="usr010SelectInd" onchange="changeCombo2('searchKnTop');">
    <html:optionsCollection name="mbhUsr010Form" property="usr010KanaTopLabel" value="value" label="label" />
  </html:select>
</div>

--%>

<br>

<logic:notEqual name="mbhUsr010Form" property="usr010SelectInd" value="-1">

<div data-role="header" data-nobackbtn="true" data-theme="c">
<div data-role="navbar">
<ul>
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     String color = null; %>
<logic:equal name="mbhUsr010Form" property="usr010SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#ff0000"; %>
</logic:equal>
<logic:notEqual name="mbhUsr010Form" property="usr010SearchInd" value="<%= String.valueOf(rowIndex) %>" >
<%     color = "#000000"; %>
</logic:notEqual>
<%     if (!kana.equals("")) { %>
<li>
<%       if (existKanaList.contains(kana)) { %>
<a style="border-right: 1px solid #dbdbdb;" href="../mobile/sp_usr010.do?mobileType=1&CMD=searchKn&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<%= rowIndex %>&usr010SearchKanaFlg=1&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />"><span class="txt_shadow1" style="color:<%= color %>"><%= kana %></span></a>
<%       } else { %>
<%         if (selInd == 7 || selInd == 9) { %>
<a style="visibility:hidden;"></a><div class="font_small"><div class="tl4"><span style="color:#a6a6a6;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= kana %></span></div></div>
<%         } else { %>
<a style="visibility:hidden;"></a><div class="font_small"><div class="tl4"><span style="color:#a6a6a6;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= kana %></span></div></div>
<%         } %>
<%       } %>
</li>
<%    } %>
<% } %>
</ul>
</div>
</div>

</logic:notEqual>



<hr>
<div class="font_xsmall">■<gsmsg:write key="cmn.label" /></div>
<div class="font_xsmall" align="center">
  <html:select property="usr010selectedLabel" onchange="changeCombo2('searchKn');">
    <html:optionsCollection name="mbhUsr010Form" property="usr010Label" value="value" label="label" />
  </html:select>
</div>
</logic:notEqual>

<logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">

<logic:notEmpty name="mbhUsr010Form" property="usr010GroupList">
<div class="font_small" align="center">
  <html:select name="mbhUsr010Form" property="selectgsid" styleClass="select01" onchange="changeCombo();">
    <html:optionsCollection name="mbhUsr010Form" property="usr010GroupList" value="value" label="label" />
  </html:select>
</div>
</logic:notEmpty>
</logic:equal>

<logic:notEmpty name="mbhUsr010Form" property="usr010users">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
      <li data-role="list-divider">
        <div align="center">
          <bean:write name="mbhUsr010Form" property="selectgpname" /><logic:greaterThan name="maxCnt" value="1">(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)</logic:greaterThan>
        </div>
      </li>
    </logic:equal>
    <logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
      <logic:greaterThan name="maxCnt" value="1">
        <li data-role="list-divider">
          <div align="center">(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)</div>
        </li>
      </logic:greaterThan>
    </logic:notEqual>
    <bean:define id="mod" value="0" />
    <logic:iterate id="user" name="mbhUsr010Form" property="usr010users" scope="request" indexId="labelIdx">

      <logic:equal name="mod" value="<%= String.valueOf(labelIdx.intValue() % 2) %>">
        <bean:define id="liColor" value="c" />
      </logic:equal>
      <logic:notEqual name="mod" value="<%= String.valueOf(labelIdx.intValue() % 2) %>">
        <bean:define id="liColor" value="d" />
      </logic:notEqual>

      <logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
        <li data-theme=<bean:write name="liColor" />>
          <a href="../mobile/sp_usr020.do?mobileType=1&userSid=<bean:write name="user" property="usrSid" />&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />"><bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" /></a>
        </li>
      </logic:equal>
      <logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
        <li>
          <a href="../mobile/sp_usr020.do?mobileType=1&userSid=<bean:write name="user" property="usrSid" />&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />"><bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" /></a>
        </li>
      </logic:notEqual>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>

<div align="center" class="font_xsmall">
<div data-role="controlgroup" data-type="horizontal" align="center">
<logic:notEmpty name="mbhUsr010Form" property="usr010PageLabel">

  <logic:greaterThan name="maxCnt" value="1">
  <logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="1">
      <a href="./sp_usr010.do?mobileType=1&CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><gsmsg:write key="cmn.next" /></a>
    </logic:equal>
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="1">
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./sp_usr010.do?mobileType=1&CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.previous" /></a><a href="./sp_usr010.do?mobileType=1&CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><gsmsg:write key="cmn.next" /></a>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./sp_usr010.do?mobileType=1&CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.previous" /></a>
    </logic:equal>
  </logic:equal>
  <logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="1">
      <a href="./sp_usr010.do?mobileType=1&CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><gsmsg:write key="cmn.next" /></a>
    </logic:equal>
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="1">
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./sp_usr010.do?mobileType=1&CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.previous" /></a><a href="./sp_usr010.do?mobileType=1&CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><gsmsg:write key="cmn.next" /></a>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./sp_usr010.do?mobileType=1&CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />" data-inline="true" data-role="button" data-icon="arrow-l"><gsmsg:write key="cmn.previous" /></a>
    </logic:equal>
  </logic:notEqual>
  </logic:greaterThan>
</logic:notEmpty>
</div>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>