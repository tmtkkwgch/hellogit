<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir060Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.add" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
</head>

<body>

<html:form action="/mobile/mh_cir060">
<input type="hidden" name="CMD" value="">
<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<html:hidden property="cir020pageNum2" />
<html:hidden property="cir050pluginId" />
<html:hidden property="cir050memoPeriod" />
<html:hidden property="cir050title" />
<html:hidden property="cir050value" />
<html:hidden property="cir050show" />
<html:hidden property="cir050memoKbn" />

<logic:notEmpty name="mbhCir060Form" property="cir050userSid" scope="request">
<logic:iterate id="users" name="mbhCir060Form" property="cir050userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cir050userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir060Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir060Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /><br><gsmsg:write key="cmn.user" /><gsmsg:write key="cmn.add" /></b>

<hr>

<%= emojiPen.toString() %><span class="text_bb1"><gsmsg:write key="cmn.group" /></span>
<br>


<logic:notEmpty name="mbhCir060Form" property="cir060GroupList">
<html:select name="mbhCir060Form" property="cir060GroupSid" styleClass="select01">
  <html:optionsCollection name="mbhCir060Form" property="cir060GroupList" value="value" label="label" />
</html:select>
</logic:notEmpty>

<input name="cir060Search" value="<gsmsg:write key="cmn.search" />" type="submit">
<br>
<logic:notEmpty name="mbhCir060Form" property="cir060UserList">

<logic:notEqual name="mbhCir060Form" property="cir060GroupSid" value="cac">
  <logic:notEmpty name="mbhCir060Form" property="cir060UserList">
    <logic:iterate id="userList" name="mbhCir060Form" property="cir060UserList" indexId="idx">
     <input name="<%= jp.groupsession.v3.mbh.cir060.MbhCir060Form.PARAM_SELECTADR %><bean:write name="userList" property="usrSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="userList" property="usiSei" />&nbsp;&nbsp;<bean:write name="userList" property="usiMei" /><br>
    </logic:iterate>
  </logic:notEmpty>
</logic:notEqual>

<logic:equal name="mbhCir060Form" property="cir060GroupSid" value="cac">
  <logic:notEmpty name="mbhCir060Form" property="cir060UserList">
    <logic:iterate id="userList" name="mbhCir060Form" property="cir060UserList" indexId="idx">
     <input name="<%= jp.groupsession.v3.mbh.cir060.MbhCir060Form.PARAM_SELECTADR %>cac<bean:write name="userList" property="cacSid" />" value="<gsmsg:write key="cmn.select" />" type="submit"><bean:write name="userList" property="cacName" /><br>
    </logic:iterate>
  </logic:notEmpty>
</logic:equal>

</logic:notEmpty>



<br>

<input name="cir060Back" value="戻る" type="submit">

</html:form>

</body>
</html:html>