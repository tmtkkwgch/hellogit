<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.bulletin" /><gsmsg:write key="bbs.32" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "bbs"; %>
<% thisForm = "mbhBbs030Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_bbs030">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="bbs010forumSid" />
<html:hidden property="threadSid" />
<input type="hidden" name="CMD" value="">

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
  <a href="#" onClick="buttonPush('bbs030back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
  <img src="../mobile/sp/imgages/bbs_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.bulletin" /><br><gsmsg:write key="bbs.32" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:equal name="mbhBbs030Form" property="bbs030ShowThreBtn" value="1">
    <logic:equal name="mbhBbs030Form" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
      <input name="bbs030reply" value="<gsmsg:write key="cmn.reply" />" type="submit" type="submit" data-inline="true" data-icon="refresh" />
    </logic:equal>
  </logic:equal>
  <input name="bbs030back" value="<gsmsg:write key="cmn.back" />" type="submit" type="submit" data-inline="true" data-icon="back" data-iconpos="right" />
</div>

<div align="center">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:notEmpty name="mbhBbs030Form" property="bbsPageLabel">
  <bean:define id="maxPage" name="mbhBbs030Form" property="bbs030maxPage" />
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="1">
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/sp_bbs030.do?mobileType=1&CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a><a href="../mobile/sp_bbs030.do?mobileType=1&CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="1">
    <a href="../mobile/sp_bbs030.do?mobileType=1&CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:equal>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/sp_bbs030.do?mobileType=1&CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a>
  </logic:equal>
  </logic:notEmpty>
</div>
</div>

<logic:notEmpty name="mbhBbs030Form" property="writeList">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li data-role="list-divider">
    <div align="center">
      <div class="font_xsmall"><span class="text_bbs1"><bean:write name="mbhBbs030Form" property="bbs030forumName" /></span></div>
      <hr>
      <b><bean:write name="mbhBbs030Form" property="bbs030threadTitle" /></b><logic:notEmpty name="mbhBbs030Form" property="bbsPageLabel"><div class="font_small">(<bean:write name="mbhBbs030Form" property="bbs030page1" />/<bean:write name="mbhBbs030Form" property="bbs030maxPage" />)</div></logic:notEmpty>
    </div>
  </li>
</ul>

  <bean:define id="mod" value="0" />
  <logic:iterate id="writeMdl" name="mbhBbs030Form" property="writeList" indexId="idx">
  <bean:define id="bbs080BwiSid" name="writeMdl" property="bwiSid"  type="java.lang.Integer" />

  <div class="text_box">
      <div class="font_xsmall">
        <b>■<gsmsg:write key="cmn.contributor" />：
        <logic:equal name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
          <del><bean:write name="writeMdl" property="userName" /></del>
        </logic:equal>
        <logic:notEqual name="writeMdl" property="userJkbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.JTKBN_DELETE) %>">
          <bean:write name="writeMdl" property="userName" />
        </logic:notEqual>
        </b>
        <br>
        <b>■<gsmsg:write key="cmn.posted" />:</b>
        <b>
        <logic:notEqual name="writeMdl" property="writeUpdateFlg" value="1">
          <bean:write name="writeMdl" property="strBwiAdate" />
        </logic:notEqual>
        <logic:equal name="writeMdl" property="writeUpdateFlg" value="1">
          <bean:write name="writeMdl" property="strBwiEdate" />
        </logic:equal>
        </b>
        <br>
        <b>■<gsmsg:write key="cmn.content" /></b>
        <logic:equal name="writeMdl" property="newFlg" value="1">
          <blink><span style="color:red">new</span></blink>
        </logic:equal>
        <br>
      </div>
      <bean:write name="writeMdl" property="bwiValueView" filter="false" />
      <br>
      <div class="font_xsmall">
        <logic:notEmpty name="writeMdl" property="tmpFileList">
        <b>■<gsmsg:write key="cmn.attach.file" /></b>
        <br>
        <logic:iterate id="fileMdl" name="writeMdl" property="tmpFileList">
          <hr>
          <div class="font_small"><a href="../mobile/sp_bbs030.do?CMD=fileDownload&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />&bbs030binSid=<bean:write name="fileMdl" property="binSid" />&bbs030writeSid=<bean:write name="bbs080BwiSid" />"><div class="font_middle3"><bean:write name="fileMdl" property="binFileName" /><bean:write name="fileMdl" property="binFileSizeDsp" /></div></a></div>
        </logic:iterate>
        </logic:notEmpty>
      </div>
  </div>
  <br>
  </logic:iterate>

</logic:notEmpty>

<div align="center">
<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:notEmpty name="mbhBbs030Form" property="bbsPageLabel">
  <bean:define id="maxPage" name="mbhBbs030Form" property="bbs030maxPage" />
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="1">
  <logic:notEqual name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString() %>">
    <a href="../mobile/sp_bbs030.do?mobileType=1&CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a><a href="../mobile/sp_bbs030.do?mobileType=1&CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:notEqual>
  </logic:notEqual>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="1">
    <a href="../mobile/sp_bbs030.do?mobileType=1&CMD=nextPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-r" data-iconpos="right"><div class="font_xsmall"><gsmsg:write key="cmn.next" /></div></a>
  </logic:equal>
  <logic:equal name="mbhBbs030Form" property="bbs030page1" value="<%= maxPage.toString()%>">
    <a href="../mobile/sp_bbs030.do?mobileType=1&CMD=prevPage&bbs030page1=<bean:write name="mbhBbs030Form" property="bbs030page1" />&bbs010forumSid=<bean:write name="mbhBbs030Form" property="bbs010forumSid" />&threadSid=<bean:write name="mbhBbs030Form" property="threadSid" />" data-inline="true" data-role="button" data-icon="arrow-l"><div class="font_xsmall"><gsmsg:write key="cmn.previous" /></div></a>
  </logic:equal>
  </logic:notEmpty>
</div>
</div>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <logic:equal name="mbhBbs030Form" property="bbs030ShowThreBtn" value="1">
    <logic:equal name="mbhBbs030Form" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>">
      <input name="bbs030reply" value="<gsmsg:write key="cmn.reply" />" type="submit" type="submit" data-inline="true" data-icon="refresh" />
    </logic:equal>
  </logic:equal>
  <input name="bbs030back" value="<gsmsg:write key="cmn.back" />" type="submit" type="submit" data-inline="true" data-icon="back" data-iconpos="right" />
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>

</div><!-- /page -->

</body>
</html:html>