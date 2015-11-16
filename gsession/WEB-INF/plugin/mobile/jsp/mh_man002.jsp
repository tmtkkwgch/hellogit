<%@ page pageEncoding="UTF-8" contentType="text/html; charset=Shift_JIS"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%  String key = jp.groupsession.v2.cmn.GSConst.SESSION_KEY; %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_000_header.jsp" %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cmn.main" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<META HTTP-EQUIV="Pragma" Content="no-cache">
<META HTTP-EQUIV="Cache-Control" Content="no-cache">
<META HTTP-EQUIV="Expires" Content="-1">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_header.jsp" %>
<% thisForm = "mbhMan002Form"; %>
</head>

<body>

<html:form action="/mobile/mh_man002">

<input type="hidden" name="CMD" value="">
<html:hidden name="mbhMan002Form" property="man002binSid" />
<html:hidden name="mbhMan002Form" property="man002SelectedSid" />

<b><%= emojiLook.toString() %><gsmsg:write key="cmn.information" /></b>
<hr>

■<gsmsg:write key="cmn.message" />
<br>
<bean:write name="mbhMan002Form" property="man002Msg" />
<br>
■<gsmsg:write key="cmn.content" />
<br>
<bean:write name="mbhMan002Form" property="man002Value" filter="false" />
<logic:notEmpty name="mbhMan002Form" property="tmpFileList">
  <br>
  ■<gsmsg:write key="cmn.attach.file" />
  <logic:iterate id="fileMdl" name="mbhMan002Form" property="tmpFileList">
<%--
  <logic:notEmpty name="fileMdl" property="binFileExtension">
    <bean:define id="fext" name="fileMdl" property="binFileExtension"  type="java.lang.String" />
    <%
    String dext = ((String)pageContext.getAttribute("fext",PageContext.PAGE_SCOPE));
    if (dext != null) {
        dext = dext.toLowerCase();
        if (jp.groupsession.v2.cmn.biz.CommonBiz.isViewFile(dext)) {
    %>

    <img src="../main/man002.do?CMD=tempview&man002binSid=<bean:write name="fileMdl" property="binSid" />" name="pictImage<bean:write name="fileMdl" property="binSid" />" onload="initImageView('pictImage<bean:write name="fileMdl" property="binSid" />');">

    <%
        }
    }
    %>
  </logic:notEmpty>
 --%>
<%--
  <a href="javascript:fileLinkClick(<bean:write name="fileMdl" property="binSid" />);"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></a>
--%>
  <br>
  <bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" />
  </logic:iterate>

</logic:notEmpty>

<logic:notEmpty name="mbhMan002Form" property="man002KoukaiList">
<br>
■<gsmsg:write key="main.exposed" />
<br>
<logic:iterate id="memName" name="mbhMan002Form" property="man002KoukaiList">
  <bean:write name="memName" property="label" /><br>
</logic:iterate>
</logic:notEmpty>

■<gsmsg:write key="cmn.registant" />
<br>
<logic:equal name="mbhMan002Form" property="man002UsrJkbn" value="9">
<del>
<bean:write name="mbhMan002Form" property="man002NameSei" />&nbsp;<bean:write name="mbhMan002Form" property="man002NameMei" />
</del>
</logic:equal>
<logic:notEqual name="mbhMan002Form" property="man002UsrJkbn" value="9">
<bean:write name="mbhMan002Form" property="man002NameSei" />&nbsp;<bean:write name="mbhMan002Form" property="man002NameMei" />
</logic:notEqual>

<hr>
<div align="center">
  <input name="man002backButton" value="<gsmsg:write key="cmn.back" />" type="submit">
</div>
<div id="mh_footer">
<%@ include file="/WEB-INF/plugin/mobile/jsp/mh_001_footer.jsp" %>
</div>

</html:form>
</body>
</html:html>