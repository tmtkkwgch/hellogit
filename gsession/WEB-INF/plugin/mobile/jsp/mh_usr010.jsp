<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhUsr010Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.shain.info" /><gsmsg:write key="mobile.15" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_usr010">

<html:hidden property="usr010cmdMode" />
<html:hidden property="usr010SelectInd" />
<html:hidden property="usr010SearchInd" />
<html:hidden property="usr010SearchTopFlg" />
<html:hidden property="usr010SearchKanaFlg" />

<b><%= emojiBook.toString() %><gsmsg:write key="cmn.shain.info" /> - </b>

<logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
<b><gsmsg:write key="cmn.group" /></b>
</logic:equal>
<logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
<b><gsmsg:write key="cmn.name" /></b>
</logic:notEqual>
<hr>
<gsmsg:write key="mobile.12" />：
<a href="../mobile/mh_usr010.do<%= jsessionId.toString() %>?usr010cmdMode=2"><gsmsg:write key="cmn.group" /></a>
<a href="../mobile/mh_usr010.do<%= jsessionId.toString() %>?usr010cmdMode=1"><gsmsg:write key="cmn.name" /></a>
<hr>
<logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
■五十音検索
<% java.util.List rowList = new java.util.ArrayList(); %>
<% rowList.add(new String[]{"ア", "カ", "サ", "タ", "ナ", "ハ", "マ", "ヤ", "ラ", "ワ"}); %>
<% rowList.add(new String[]{"イ", "キ", "シ", "チ", "ニ", "ヒ", "ミ", "", "リ", "ヲ"}); %>
<% rowList.add(new String[]{"ウ", "ク", "ス", "ツ", "ヌ", "フ", "ム", "ユ", "ル", "ン"}); %>
<% rowList.add(new String[]{"エ", "ケ", "セ", "テ", "ネ", "ヘ", "メ", "", "レ", ""}); %>
<% rowList.add(new String[]{"オ", "コ", "ソ", "ト", "ノ", "ホ", "モ", "ヨ", "ロ", ""}); %>

<br>
<bean:define id="existKanaList" name="mbhUsr010Form" property="usr010kanas" type="java.util.List" />
<bean:define id="selectInd" name="mbhUsr010Form" property="usr010SelectInd" type="java.lang.String"/>

<% String[] topKanaArray = (String[]) rowList.get(0); %>
<% for (int index = 0; index < topKanaArray.length; index++) { %>
<%     String topKana = topKanaArray[index]; %>
<%     int kanaInd = index; %>
<a href="../mobile/mh_usr010.do<%= jsessionId.toString() %>?CMD=searchKn&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010SelectInd=<%= kanaInd %>&usr010SearchInd=-1&usr010SearchTopFlg=1&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />"><%= topKana %></a>
<% } %>
<br>

<logic:notEqual name="mbhUsr010Form" property="usr010SelectInd" value="-1">
<hr style="border-style:dotted">
<% for (int rowIndex = 0; rowIndex < rowList.size(); rowIndex++) { %>
<%     String[] kanaArray = (String[]) rowList.get(rowIndex); %>
<%     int selInd = Integer.parseInt(selectInd); %>
<%     String kana = kanaArray[selInd]; %>
<%     if (existKanaList.contains(kana)) { %>
<a href="../mobile/mh_usr010.do<%= jsessionId.toString() %>?CMD=searchKn&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<%= rowIndex %>&usr010SearchKanaFlg=1&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />"><span class="user_sakuin_text"><%= kana %></span></a>
<%     } else { %>
<span class="user_base_text2"><%= kana %></span>
<%     } %>
<% } %>
<br>
</logic:notEqual>


<hr>
<gsmsg:write key="cmn.label" /><gsmsg:write key="cmn.search" />：
<html:select property="usr010selectedLabel">
<html:optionsCollection name="mbhUsr010Form" property="usr010Label" value="value" label="label" />
</html:select>
<gsmsg:define id="cmnSearch" msgkey="cmn.search" />
<br><html:submit property="usr010SearchBtn" value="<%= cmnSearch %>" />
</logic:notEqual>

<logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">

<logic:notEmpty name="mbhUsr010Form" property="usr010GroupList">
<html:select name="mbhUsr010Form" property="selectgsid" styleClass="select01">
  <html:optionsCollection name="mbhUsr010Form" property="usr010GroupList" value="value" label="label" />
</html:select>
</logic:notEmpty>
  <input name="usr010search" value="<gsmsg:write key="cmn.search" />" type="submit">
</logic:equal>

<logic:notEmpty name="mbhUsr010Form" property="usr010users">
<logic:iterate id="user" name="mbhUsr010Form" property="usr010users" scope="request" indexId="idx">
  <hr>
  <logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
  <a href="../mobile/mh_usr020.do<%= jsessionId.toString() %>?userSid=<bean:write name="user" property="usrSid" />&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />"><bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" /></a>
  </logic:equal>
  <logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <a href="../mobile/mh_usr020.do<%= jsessionId.toString() %>?userSid=<bean:write name="user" property="usrSid" />&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />"><bean:write name="user" property="usiSei" />&nbsp;<bean:write name="user" property="usiMei" /></a>
  </logic:notEqual>
</logic:iterate>
</logic:notEmpty>

<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>
<br>
<br>

<div align="center">
<logic:notEmpty name="mbhUsr010Form" property="usr010PageLabel">
  <bean:define id="maxCnt" name="mbhUsr010Form" property="usr010maxPageNum" />
  <logic:greaterThan name="maxCnt" value="1">
  <logic:equal name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)<a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />"><gsmsg:write key="cmn.next" /></a>
    </logic:equal>
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="1">
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)<a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />"><gsmsg:write key="cmn.next" /></a>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&selectgsid=<bean:write name="mbhUsr010Form" property="selectgsid" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)<gsmsg:write key="cmn.next" />
    </logic:equal>
  </logic:equal>
  <logic:notEqual name="mbhUsr010Form" property="usr010cmdMode" value="2">
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="1">
<gsmsg:write key="cmn.previous" />(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)<a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />"><gsmsg:write key="cmn.next" /></a>
    </logic:equal>
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="1">
    <logic:notEqual name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)<a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_right&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />"><gsmsg:write key="cmn.next" /></a>
    </logic:notEqual>
    </logic:notEqual>
    <logic:equal name="mbhUsr010Form" property="usr010pageNum1" value="<%= maxCnt.toString() %>">
      <a href="./mh_usr010.do<%= jsessionId.toString() %>?CMD=arrorw_left&usr010cmdMode=<bean:write name="mbhUsr010Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr010Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr010Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr010Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr010Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr010Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr010Form" property="usr010SearchKanaFlg" />"><gsmsg:write key="cmn.previous" /></a>(<bean:write name="mbhUsr010Form" property="usr010pageNum1" />/<bean:write name="mbhUsr010Form" property="usr010maxPageNum" />)<gsmsg:write key="cmn.next" />
    </logic:equal>
  </logic:notEqual>
  </logic:greaterThan>
</logic:notEmpty>
</div>

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>