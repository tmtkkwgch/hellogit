<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% thisForm = "mbhCir050knForm"; %>
<%@page import="jp.groupsession.v2.cir.GSConstCircular"%>
<%@page import="jp.groupsession.v2.cmn.GSConst"%>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.create.new" /><gsmsg:write key="cmn.check" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>

</head>

<!--　BODY -->
<body>

<html:form method="post" action="/mobile/mh_cir050kn">

<html:hidden property="cirViewAccount" />
<html:hidden property="cirViewAccountName" />
<html:hidden property="cir020cmdMode" />
<html:hidden property="cir020orderKey" />
<html:hidden property="cir020sortKey" />
<html:hidden property="cir020pageNum1" />
<html:hidden property="cir020pageNum2" />
<html:hidden property="cir050pluginId" />
<html:hidden property="cir050memoKbn" />
<html:hidden property="cir050memoPeriod" />
<html:hidden property="cir050title" />
<html:hidden property="cir050value" />
<html:hidden property="cir050show" />
<html:hidden property="cir050memoPeriodYear" />
<html:hidden property="cir050memoPeriodMonth" />
<html:hidden property="cir050memoPeriodDay" />



<logic:notEmpty name="mbhCir050knForm" property="cir050userSid" scope="request">
<logic:iterate id="users" name="mbhCir050knForm" property="cir050userSid" indexId="idx" scope="request">
  <bean:define id="userSid" name="users" type="java.lang.String" />
  <html:hidden property="cir050userSid" value="<%= userSid %>" />
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhCir050knForm" property="cir020delInfSid" scope="request">
<logic:iterate id="item" name="mbhCir050knForm" property="cir020delInfSid" scope="request">
  <input type="hidden" name="cir020delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMemo.toString() %><gsmsg:write key="cir.5" /><br><gsmsg:write key="cmn.create.new" /></b>

<hr>

<logic:messagesPresent message="false">
  <html:errors/><br>
</logic:messagesPresent>

■<gsmsg:write key="cir.2" />:<bean:write name="mbhCir050knForm" property="cirViewAccountName" /><br>

■<gsmsg:write key="cir.20" />

<logic:notEmpty name="mbhCir050knForm" property="cir050MemberList" scope="request">
<br>
<logic:iterate id="memMdl" name="mbhCir050knForm" property="cir050MemberList" scope="request">
  <span class="text_base">
  <bean:write name="memMdl" property="cacName" /><br>
  </span>
</logic:iterate>

</logic:notEmpty>

<logic:empty name="mbhCir050knForm" property="cir050MemberList" scope="request">

</logic:empty>

<br>

<!-- タイトル -->
■<gsmsg:write key="cmn.title" /><br>
<bean:write name="mbhCir050knForm" property="cir050title" />

<br>

<!-- 内容 -->
■<gsmsg:write key="cmn.content" />
<br>
<bean:write name="mbhCir050knForm" property="cir050value" />

<br>
<br>

<!-- 修正期限 -->
<span class="text_bb1">■<gsmsg:write key="cir.cir040.2" /></span><br>
<logic:equal name="mbhCir050knForm" property="cir050memoKbn" value="0">
<span class="text_base"><gsmsg:write key="cmn.not" /></span>
</logic:equal>
<logic:equal name="mbhCir050knForm" property="cir050memoKbn" value="1">
<bean:write name="mbhCir050knForm" property="cir050memoPeriodYear" /><gsmsg:write key="cmn.year2" />
<bean:write name="mbhCir050knForm" property="cir050memoPeriodMonth" /><gsmsg:write key="cmn.month" />
<bean:write name="mbhCir050knForm" property="cir050memoPeriodDay" /><gsmsg:write key="cmn.day" />
<gsmsg:write key="cir.56" arg0="" />

</logic:equal>

<br>
<br>

<!-- 公開／非公開   -->
■<gsmsg:write key="cir.cir030.3" /><br>
<logic:equal name="mbhCir050knForm" property="cir050show" value="0">
  <gsmsg:write key="cmn.public" />
</logic:equal>
<logic:equal name="mbhCir050knForm" property="cir050show" value="1">
  <gsmsg:write key="cmn.private" />
</logic:equal>

<br>
<br>

<input type="submit" name="sendbtn" value="OK" class="btn_ok1">
<input type="submit" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" name="cir050knBack">

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>