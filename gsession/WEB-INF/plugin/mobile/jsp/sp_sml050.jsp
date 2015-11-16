<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />]<gsmsg:write key="cmn.shortmail" /><gsmsg:write key="sml.sml020.05" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "sml"; %>
<% thisForm = "mbhSml050Form"; %>
</head>
<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form method="post" action="/mobile/sp_sml050">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<input type="hidden" name="id" value="">
<html:hidden property="smlViewAccount" />
<html:hidden property="smlViewAccountName" />
<html:hidden property="sml010ProcMode" />
<html:hidden property="sml020PageNum" />
<html:hidden property="sml020SelectedSid" />
<html:hidden property="sml040Title" />
<html:hidden property="sml040Mark" />
<html:hidden property="sml040Body" />
<html:hidden property="sml040ProcMode" />
<html:hidden property="sml040InitFlg" />
<html:hidden property="sml050cmdMode" />

<logic:notEmpty name="mbhSml050Form" property="sml040userSid">
<logic:iterate id="usid" name="mbhSml050Form" property="sml040userSid">
  <input type="hidden" name="sml040userSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml050Form" property="sml040userSidCc">
<logic:iterate id="usidcc" name="mbhSml050Form" property="sml040userSidCc">
  <input type="hidden" name="sml040userSidCc" value="<bean:write name="usidcc" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="mbhSml050Form" property="sml040userSidBcc">
<logic:iterate id="usidbcc" name="mbhSml050Form" property="sml040userSidBcc">
  <input type="hidden" name="sml040userSidBcc" value="<bean:write name="usidbcc" />">
</logic:iterate>
</logic:notEmpty>

<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('sml050back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/sml_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shortmail" /><br>
    <logic:equal name="mbhSml050Form" property="sml050cmdMode" value="0"><gsmsg:write key="sml.sml020.05" /></logic:equal>
    <logic:equal name="mbhSml050Form" property="sml050cmdMode" value="1"><gsmsg:write key="sml.sml020.05" /></logic:equal>
    <logic:equal name="mbhSml050Form" property="sml050cmdMode" value="2"><gsmsg:write key="sml.sml020.05" /></logic:equal>
  </h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<logic:notEmpty name="mbhSml050Form" property="sml050GroupList">
<html:select name="mbhSml050Form" property="sml050GroupSid" onchange="changeCombo();">
  <html:optionsCollection name="mbhSml050Form" property="sml050GroupList" value="value" label="label" />
</html:select>
</logic:notEmpty>

<logic:notEqual name="mbhSml050Form" property="sml050GroupSid" value="sac">
  <logic:notEmpty name="mbhSml050Form" property="sml050UserList">
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <logic:iterate id="userList" name="mbhSml050Form" property="sml050UserList" indexId="idx">
      <li>
        <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.sml050.MbhSml050Form.PARAM_SELECTADR %><bean:write name="userList" property="usrSid" />');"><bean:write name="userList" property="usiSei" />&nbsp;&nbsp;<bean:write name="userList" property="usiMei" /></a>
      </li>
      </logic:iterate>
    </ul>
  </logic:notEmpty>
</logic:notEqual>

<logic:equal name="mbhSml050Form" property="sml050GroupSid" value="sac">
  <logic:notEmpty name="mbhSml050Form" property="sml050UserList">
    <ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
      <logic:iterate id="userList" name="mbhSml050Form" property="sml050UserList" indexId="idx">
      <li>
        <a href="#" onClick="addUser('<%= jp.groupsession.v3.mbh.sml050.MbhSml050Form.PARAM_SELECTADR %>sac<bean:write name="userList" property="sacSid" />');"><bean:write name="userList" property="sacName" /></a>
      </li>
      </logic:iterate>
    </ul>
  </logic:notEmpty>
</logic:equal>

<div align="center">
  <br><input name="sml050Back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back"/>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>