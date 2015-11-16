<%@ page pageEncoding="Windows-31J"
    contentType="text/html; charset=UTF-8"%>
  <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
  <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
  <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
  <%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>


  <html:html>
  <head>
  <title>[GroupSession] HelloWorld</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel=stylesheet href='../common/css/default.css' type='text/css'>
  </head>

  <body class="body_03">
  <html:form action="/newplugin/helloworld">

  <%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

  <table width="100%">
  <tr>
  <td width="100%" align="center">
    <p>Hello WorldÅI</p>
  </td>
  </tr>
  </table>

  <%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

  </html:form>
  </body>
  </html:html>