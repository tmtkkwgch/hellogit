<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<theme:css filename="theme.css"/>
</head>

<html:form action="/project/prjptl010">
<body class="body_03">
<html:hidden name="prjptl010Form" property="prjPtl010PrjSid" />

      <bean:define id="kanryo" name="prjptl010Form" property="todoKanryoCnt" />
      <bean:define id="mikanryo" name="prjptl010Form" property="todoMikanryoCnt" />
      <bean:define id="sinkotyu" name="prjptl010Form" property="todoSinkotyuCnt" />

      <%
        int kanryoCnt = ((Integer) kanryo).intValue();
        int mikanryoCnt = ((Integer) mikanryo).intValue();
        int sinkotyuCnt = ((Integer) sinkotyu).intValue();
        int allCnt = kanryoCnt + mikanryoCnt + sinkotyuCnt;

        if (allCnt > 0) {
      %>

<div style="width: 100%; margin: 0px; padding: 0px; text-align: center;">
  <iframe border="0" frameborder="0" scrolling="no" width="100%" height="330px" src="../project/prjptl011.do?prjPtl010PrjSid=<bean:write name="prjptl010Form" property="prjPtl010PrjSid" />"></iframe>
</div>
      <% } %>

</body>
</html:form>
</html:html>