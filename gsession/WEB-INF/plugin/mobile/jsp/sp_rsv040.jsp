<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.reserve" /><gsmsg:write key="mobile.15" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rsv"; %>
<% thisForm = "mbhRsv040Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_rsv040">
<html:hidden property="mobileType" value="1"/>
<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<img src="../mobile/sp/imgages/rsv_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.reserve" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">


<select name="sch050GroupSid" onchange="changeCombo();" class="select01">
<option value="0">会議室</option>
<option value="1">車</option>
<option value="11">PC</option>
</select>

<br>

  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">

      <li data-role="list-divider">
        <div align="center">
         会議室
       </div>

      </li>








      <li data-theme=c>
        <a href="./sp_rsv010.do?mobileType=1">会議室Ａ</a>
      </li>






      <li data-theme=d>
        <a href="./sp_rsv010.do?mobileType=1">会議室Ｂ</a>
      </li>






      <li data-theme=c>

        <a href="./sp_rsv010.do?mobileType=1">会議室Ｃ</a>
      </li>


  </ul>


<div align="center">
  <input name="sch050back" value="戻る" type="submit" data-inline="true" data-role="button" data-icon="back"/>
</div>



</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>