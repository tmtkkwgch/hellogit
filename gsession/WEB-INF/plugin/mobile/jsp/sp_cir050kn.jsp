<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.4" />] <gsmsg:write key="cir.5" /><gsmsg:write key="cmn.create.new" /><gsmsg:write key="cmn.check" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sml"; %>
<% thisForm = "mbhCir050knForm"; %>
</head>

<!--　BODY -->
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_cir050kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
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
<html:hidden property="cir050memoPeriodYear" />
<html:hidden property="cir050memoPeriodMonth" />
<html:hidden property="cir050memoPeriodDay" />
<html:hidden property="cir050limitDay" />
<html:hidden property="cir050InitFlg" />

<html:hidden property="cir060dspId" />
<html:hidden property="cir020selectInfSid" />

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

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('cir040back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/cir_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cir.5" /><br><gsmsg:write key="cmn.create.new" /><gsmsg:write key="cmn.check" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:messagesPresent message="false">
  <html:errors/><br>
</logic:messagesPresent>


<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">


  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.2" /></span><br>
    <span class="text_base"><bean:write name="mbhCir050knForm" property="cirViewAccountName" /></span>
  </li>

  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.20" /></span>

    <logic:notEmpty name="mbhCir050knForm" property="cir050MemberList" scope="request">
    <br>
    <logic:iterate id="memMdl" name="mbhCir050knForm" property="cir050MemberList" scope="request">
      <span class="text_base">
      <bean:write name="memMdl" property="cacName" /><br>
      </span>
    </logic:iterate>

    </logic:notEmpty>
  </li>

  <li>
    <!-- タイトル -->
    <span class="font_xsmall">■<gsmsg:write key="cmn.title" /></span><br>
    <bean:write name="mbhCir050knForm" property="cir050title" />
  </li>

  <li>
    <!-- 内容 -->
    <span class="font_xsmall">■<gsmsg:write key="cmn.content" /></span>
    <br>
     <bean:write name="mbhCir050knForm" property="cir050knBody" filter="false"/>
  </li>

<!-- 修正期限 -->
  <li>
    <span class="font_xsmall">■<gsmsg:write key="cir.cir040.2" /></span><br>
    <logic:equal name="mbhCir050knForm" property="cir050memoKbn" value="0">
    <span class="text_base"><gsmsg:write key="cmn.not" /></span>
    </logic:equal>
    <logic:equal name="mbhCir050knForm" property="cir050memoKbn" value="1">
    <bean:write name="mbhCir050knForm" property="cir050memoPeriodYear" /><gsmsg:write key="cmn.year2" />
    <bean:write name="mbhCir050knForm" property="cir050memoPeriodMonth" /><gsmsg:write key="cmn.month" />
    <bean:write name="mbhCir050knForm" property="cir050memoPeriodDay" /><gsmsg:write key="cmn.day" />
    <gsmsg:write key="cir.56" arg0="" />
    </logic:equal>
  </li>

  <li>
    <!-- 公開／非公開   -->
    <span class="font_xsmall">■<gsmsg:write key="cir.cir030.3" /></span><br>
    <logic:equal name="mbhCir050knForm" property="cir050show" value="0">
      <gsmsg:write key="cmn.public" />
    </logic:equal>
    <logic:equal name="mbhCir050knForm" property="cir050show" value="1">
      <gsmsg:write key="cmn.private" />
    </logic:equal>
  </li>
  </ul>

  <!-- 添付ファイル -->
  <logic:notEmpty name="mbhCir050knForm" property="cir050FileLabelList">
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
    <li><span class="font_xsmall">■<gsmsg:write key="cmn.attach.file" /></span></li>
    <logic:iterate id="file" name="mbhCir050knForm" property="cir050FileLabelList" indexId="idx" scope="request">
      <li><bean:write name="file" property="label" /></li>
    </logic:iterate>
    </ul>
  </logic:notEmpty>

<div data-role="controlgroup" data-type="horizontal" align="center">
  <input type="submit" name="sendbtn" value="ＯＫ" class="btn_ok1" data-inline="true" data-role="button" data-icon="forward">
  <input type="submit" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" name="cir050knBack" data-icon="back" data-iconpos="right">
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>