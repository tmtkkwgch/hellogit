<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>
<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% String maxLengthContent = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_CONTENT); %>
<% thisForm = "mbhRng040Form"; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="rng.rng010.02" />)</title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
</head>

<body>

<html:form action="/mobile/mh_rng040">
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<html:hidden property="rng020pageTop" />

<logic:notEmpty name="mbhRng040Form" property="rng040apprUser">
<logic:iterate id="apprUser" name="mbhRng040Form" property="rng040apprUser">
  <input type="hidden" name="rng040apprUser" value="<bean:write name='apprUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040Form" property="rng040confirmUser">
<logic:iterate id="confirmUser" name="mbhRng040Form" property="rng040confirmUser">
  <input type="hidden" name="rng040confirmUser" value="<bean:write name='confirmUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040Form" property="rng040files">
<logic:iterate id="files" name="mbhRng040Form" property="rng040files">
  <input type="hidden" name="rng040files" value="<bean:write name='files' />">
</logic:iterate>
</logic:notEmpty>

<b><%= emojiMail.toString() %><gsmsg:write key="rng.62" /><br><gsmsg:write key="rng.rng010.02" /></b>

<hr>

<logic:messagesPresent message="false">
<span style="color:red;"><html:errors/></span>
</logic:messagesPresent>

■<gsmsg:write key="cmn.title" />
<br>
<html:text name="mbhRng040Form" property="rng040Title" size="27" />
<br>
■<gsmsg:write key="rng.47" />：<bean:write name="mbhRng040Form" property="rng040requestUser" />

<br>
■<gsmsg:write key="cmn.content" />
<br><html:textarea name="mbhRng040Form" property="rng040Content" cols="16" rows="3" ></html:textarea>

<br>
■<gsmsg:write key="rng.24" /><input name="rng040Select" value="<gsmsg:write key="cmn.select" />" type="submit">
<br>
<logic:notEmpty name="mbhRng040Form" property="rng040apprUserList">
<logic:iterate id="urBean" name="mbhRng040Form" property="rng040apprUserList">
<input name="<bean:write name="urBean" property="value" />" value="<gsmsg:write key="cmn.delete" />" type="submit"><bean:write name="urBean" property="label" />
<br>
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040Form" property="rng040confirmUserList">
■<gsmsg:write key="rng.35" />
<br>
<logic:iterate id="urBean" name="mbhRng040Form" property="rng040confirmUserList">
<input name="<bean:write name="urBean" property="value" />" value="<gsmsg:write key="cmn.delete" />" type="submit"><bean:write name="urBean" property="label" />
<br>
</logic:iterate>
</logic:notEmpty>

<div align="center">
  <br><input name="rng040Ok" value="<gsmsg:write key="rng.46" />" type="submit"><input name="rng040Hozon" value="<gsmsg:write key="cmn.draft" /><gsmsg:write key="mobile.20" />" type="submit"><input name="rng040Back" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>

<input type="hidden" name="CMD" value="">

</html:form>

<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_footer.jsp" %>
</body>
</html:html>