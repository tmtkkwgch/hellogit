<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir070Form"; %>
<%@page import="jp.groupsession.v2.cir.GSConstCircular"%>
<%@page import="jp.groupsession.v2.cmn.GSConst"%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="mobile.9" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>

</head>

<!--　BODY -->
<body>

<html:form method="post" action="/mobile/mh_cir070">

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


<logic:notEmpty name="mbhCir070Form" property="cir050userSid" scope="request">
<logic:iterate id="users" name="mbhCir070Form" property="cir050userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cir050userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir070Form" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir070Form" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /><br><gsmsg:write key="mobile.9" /></b>


<hr>

■<span class="text_base"><gsmsg:write key="cir.54" /></span><br>
<span class="text_r1"><gsmsg:write key="cir.cir040.4" /></span><br><br>


<!-- 日付指定の場合 -->
<html:select property="cir050memoPeriodYear" styleId="selYear">
<html:optionsCollection name="mbhCir070Form" property="cir050memoSelectYear" value="value" label="label" />
</html:select>
<html:select property="cir050memoPeriodMonth" styleId="selMonth">
<html:optionsCollection name="mbhCir070Form" property="cir050memoSelectMonth" value="value" label="label" />
</html:select>
<html:select property="cir050memoPeriodDay" styleId="selDay">
<html:optionsCollection name="mbhCir070Form" property="cir050memoSelectDay" value="value" label="label" />
</html:select>



<div align="center">

  <input name="cir070Add" value="<gsmsg:write key="cmn.setting" />" type="submit">
<logic:equal name="mbhCir070Form" property="changeKbn" value="0">
<input name="cir070Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</logic:equal>
</div>

</html:form>

</body>
</html:html>