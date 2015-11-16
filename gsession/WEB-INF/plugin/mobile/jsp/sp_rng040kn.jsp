<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<% int tmodeAll = jp.groupsession.v2.rng.RngConst.RNG_TEMPLATE_ALL; %>
<% String maxLengthContent = String.valueOf(jp.groupsession.v2.rng.RngConst.MAX_LENGTH_CONTENT); %>

<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="rng.62" />(<gsmsg:write key="rng.rng020kn.01" />)</title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "rng"; %>
<% thisForm = "mbhRng040knForm"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_rng040kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="rng020sortKey" />
<html:hidden property="rng020orderKey" />
<html:hidden property="rng020pageTop" />
<html:hidden property="rngSid" />
<html:hidden property="rngProcMode" />
<html:hidden property="rngCmdMode" />
<html:hidden property="rng040Title" />
<html:hidden property="rng040requestUser" />
<html:hidden property="rng040Content" />
<html:hidden property="rng040InitFlg" />

<logic:notEmpty name="mbhRng040knForm" property="rng040apprUser">
<logic:iterate id="apprUser" name="mbhRng040knForm" property="rng040apprUser">
  <input type="hidden" name="rng040apprUser" value="<bean:write name='apprUser' />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhRng040knForm" property="rng040confirmUser">
<logic:iterate id="confirmUser" name="mbhRng040knForm" property="rng040confirmUser">
  <input type="hidden" name="rng040confirmUser" value="<bean:write name='confirmUser' />">
</logic:iterate>
</logic:notEmpty>

<html:hidden property="rng040BinSid" />

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('rng040knBack');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/rng_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="rng.62" /><br><gsmsg:write key="rng.rng020kn.01" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.title" /></div>
    <bean:write name="mbhRng040knForm" property="rng040Title" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="rng.47" /></div>
    <bean:write name="mbhRng040knForm" property="rng040requestUser" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.content" /></div>
    <bean:write name="mbhRng040knForm" property="rng040knContent" filter="false" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="rng.24" /></div>
    <logic:notEmpty name="mbhRng040knForm" property="channelList">
    <logic:iterate id="channelModel" name="mbhRng040knForm" property="channelList" indexId="idx">
    <bean:write name="channelModel" property="userName" />
    <logic:notEmpty name="channelModel" property="yakusyoku">
    (<bean:write name="channelModel" property="yakusyoku" />)
    </logic:notEmpty>
    <br>
    </logic:iterate>
    </logic:notEmpty>
    <br>
    <logic:notEmpty name="mbhRng040knForm" property="confirmChannelList">
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.check" /></div>
    <logic:iterate id="confirmChannelModel" name="mbhRng040knForm" property="confirmChannelList" indexId="indx">
    <bean:write name="confirmChannelModel" property="userName" />
    <logic:notEmpty name="confirmChannelModel" property="yakusyoku">
    (<bean:write name="confirmChannelModel" property="yakusyoku" />)
    </logic:notEmpty>
    <br>
    </logic:iterate>
    </logic:notEmpty>
  </li>
</ul>

<!-- 添付ファイル -->
<logic:notEmpty name="mbhRng040knForm" property="rng040fileList">
  <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li data-role="list-divider"><div align="center"><gsmsg:write key="cmn.attached" /></div></li>
    <logic:iterate id="tmp" name="mbhRng040knForm" property="rng040fileList">
      <li>
      <bean:write name="tmp" property="label" />
      </li>
    </logic:iterate>
  </ul>
</logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input name="rng040knSinsei" value="<gsmsg:write key="rng.46" />" type="submit" data-inline="true" data-icon="forward" />
  <input name="rng040knBack" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-icon="back" data-iconpos="right"/>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>