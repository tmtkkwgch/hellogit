<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String version = jp.groupsession.v2.cmn.GSConst.VERSION; %>
<% String close = String.valueOf(jp.groupsession.v2.usr.GSConstUser.INDIVIDUAL_INFO_CLOSE); %>
<html:html>
<head>
<title>[<gsmsg:write key="mobile.5" />] <gsmsg:write key="cmn.shain.info" /></title>
<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_header.jsp" %>
<% pluginName = "usr"; %>
<% thisForm = "mbhUsr020Form"; %>
</head>

<body>

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_002_header.jsp" %>

<div data-role="page" data-theme="<%= usrTheme %>">

<html:form action="/mobile/sp_usr020">
<input type="hidden" name="CMD" value="">
<html:hidden property="mobileType" value="1"/>
<html:hidden property="usr010cmdMode" />
<html:hidden property="usr010pageNum1" />
<html:hidden property="selectgsid" />
<html:hidden property="usr010selectedLabel" />
<html:hidden property="usr010SelectInd" />
<html:hidden property="usr010SearchInd" />
<html:hidden property="usr010SearchTopFlg" />
<html:hidden property="usr010SearchKanaFlg" />


<div data-role="header" data-nobackbtn="true" data-theme="<%= usrTheme %>">
<a href="#" onClick="buttonPush('usr020back');" data-role="button" data-icon="back" data-iconpos="notext" class="header_back_button">Back</a>
<img src="../mobile/sp/imgages/usr_menu_icon_single.gif" class="tl img_border"/>
  <h1><gsmsg:write key="cmn.shain.info" /></h1>
  <a href="../mobile/sp_man001.do?mobileType=1" data-role="button" data-icon="home" data-iconpos="notext" class="header_home_button">Home</a>
</div><!-- /header -->

<div data-role="content">

<ul data-role="listview" data-inset="true" data-theme="d" data-dividertheme="c">
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiSyainNo">
    <li>
      <div class="font_small">■<gsmsg:write key="cmn.employee.staff.number" /></div>
      <bean:write name="mbhUsr020Form" property="usr020usiSyainNo" />
    </li>
  </logic:notEmpty>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.name" /></div>
    <bean:write name="mbhUsr020Form" property="usr020usiSei" />&nbsp;&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMei" />
  </li>
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.name" />（<gsmsg:write key="cmn.kana" />）</div>
    <bean:write name="mbhUsr020Form" property="usr020usiSeiKn" />&nbsp;&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMeiKn" />
  </li>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiSyozoku">
    <li>
      <div class="font_small">■<gsmsg:write key="cmn.affiliation" /></div>
      <bean:write name="mbhUsr020Form" property="usr020usiSyozoku" />
    </li>
  </logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiYakusyoku">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.post" /></div>
    <bean:write name="mbhUsr020Form" property="usr020usiYakusyoku" />
  </li>
  </logic:notEmpty>
  <logic:notEmpty name="mbhUsr020Form" property="usr020usiBdate">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.birthday" /></div>
    <logic:equal name="mbhUsr020Form" property="usr020usiBdateKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiBdateKf" value="<%= close %>"><bean:write name="mbhUsr020Form" property="usr020usiBdate" />
    <logic:notEqual name="mbhUsr020Form" property="usr020usiAge" value="-1">
    (<bean:write name="mbhUsr020Form" property="usr020usiAge" /><gsmsg:write key="user.98" />)
    </logic:notEqual>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail1">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiMail1Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />1</div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiMail1Kf" value="<%= close %>">
    <logic:notEmpty name="mbhUsr020Form" property="usr020usiMail1">
    <logic:equal name="mbhUsr020Form" property="usr020webMailUse" value="0">
      <a href="../mobile/sp_usr030.do?mobileType=1&usr020usid=<bean:write name="mbhUsr020Form" property="usr020usid" />&usr010cmdMode=<bean:write name="mbhUsr020Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr020Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr020Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr020Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr020Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr020Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr020Form" property="usr010SearchKanaFlg" />&usr030SelectValue=mail&usr020usiMail1=<bean:write name="mbhUsr020Form" property="usr020usiMail1" />">
        <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />1</div>
        <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiMail1" /></span>
      </a>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020webMailUse" value="0">
      <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />1</div>
      <bean:write name="mbhUsr020Form" property="usr020usiMail1" />
    </logic:notEqual>
    </logic:notEmpty>
    <logic:notEmpty name="mbhUsr020Form" property="usr020usiMailCmt1"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMailCmt1" /></logic:notEmpty>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail2">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiMail2Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />2</div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiMail2Kf" value="<%= close %>">
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiMail2">
      <logic:equal name="mbhUsr020Form" property="usr020webMailUse" value="0">
        <a href="../mobile/sp_usr030.do?mobileType=1&usr020usid=<bean:write name="mbhUsr020Form" property="usr020usid" />&usr010cmdMode=<bean:write name="mbhUsr020Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr020Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr020Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr020Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr020Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr020Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr020Form" property="usr010SearchKanaFlg" />&usr030SelectValue=mail&usr020usiMail1=<bean:write name="mbhUsr020Form" property="usr020usiMail2" />">
          <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />2</div>
          <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiMail2" /></span>
        </a>
      </logic:equal>
        <logic:notEqual name="mbhUsr020Form" property="usr020webMailUse" value="0">
          <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />2</div>
        <bean:write name="mbhUsr020Form" property="usr020usiMail2" />
      </logic:notEqual>
      </logic:notEmpty>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiMailCmt2"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMailCmt2" /></logic:notEmpty>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiMail3">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiMail3Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />3</div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiMail3Kf" value="<%= close %>">
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiMail3">
      <logic:equal name="mbhUsr020Form" property="usr020webMailUse" value="0">
        <a href="../mobile/sp_usr030.do?mobileType=1&usr020usid=<bean:write name="mbhUsr020Form" property="usr020usid" />&usr010cmdMode=<bean:write name="mbhUsr020Form" property="usr010cmdMode" />&usr010pageNum1=<bean:write name="mbhUsr020Form" property="usr010pageNum1" />&usr010selectedLabel=<bean:write name="mbhUsr020Form" property="usr010selectedLabel" />&usr010SelectInd=<bean:write name="mbhUsr020Form" property="usr010SelectInd" />&usr010SearchInd=<bean:write name="mbhUsr020Form" property="usr010SearchInd" />&usr010SearchTopFlg=<bean:write name="mbhUsr020Form" property="usr010SearchTopFlg" />&usr010SearchKanaFlg=<bean:write name="mbhUsr020Form" property="usr010SearchKanaFlg" />&usr030SelectValue=mail&usr020usiMail1=<bean:write name="mbhUsr020Form" property="usr020usiMail3" />">
          <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />3</div>
          <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiMail3" /></span>
        </a>
      </logic:equal>
      <logic:notEqual name="mbhUsr020Form" property="usr020webMailUse" value="0">
        <div class="font_small">■<gsmsg:write key="cmn.mailaddress" />3</div>
        <bean:write name="mbhUsr020Form" property="usr020usiMail3" />
      </logic:notEqual>
      </logic:notEmpty>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiMailCmt3"><br><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiMailCmt3" /></logic:notEmpty>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiZip">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.postalcode" /></div>
    <logic:equal name="mbhUsr020Form" property="usr020usiZipKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiZipKf" value="<%= close %>"><bean:write name="mbhUsr020Form" property="usr020usiZip" /></logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020TdfkName">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.prefectures" /></div>
    <logic:equal name="mbhUsr020Form" property="usr020usiTdfkKf" value="<%= close %>"><span class="text_r1"><gsmsg:write key="cmn.private" /></span></logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiTdfkKf" value="<%= close %>"><bean:write name="mbhUsr020Form" property="usr020TdfkName" /></logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiAddr1">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiAddr1Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.address" /></div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiAddr1Kf" value="<%= close %>">
    <a href="http://maps.google.co.jp/maps?q=<bean:write name="mbhUsr020Form" property="usr020usiAddr1Url" />" />
      <div class="font_small">■<gsmsg:write key="cmn.address" /></div>
      <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiAddr1" /></span>
    </a><br>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiTel1">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiTel1Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.tel" />1</div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiTel1Kf" value="<%= close %>">
      <a href=tel:<bean:write name="mbhUsr020Form" property="usr020usiTel1" />>
        <div class="font_small">■<gsmsg:write key="cmn.tel" />1</div>
        <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiTel1" /></span>
      </a><br>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelNai1"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelNai1" />&nbsp;&nbsp;<br></logic:notEmpty>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelCmt1"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelCmt1" /></logic:notEmpty>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiTel2">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiTel2Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.tel" />2</div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiTel2Kf" value="<%= close %>">
      <a href=tel:<bean:write name="mbhUsr020Form" property="usr020usiTel2" />>
        <div class="font_small">■<gsmsg:write key="cmn.tel" />2</div>
        <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiTel2" /></span>
      </a><br>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelNai2"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelNai2" />&nbsp;&nbsp;<br></logic:notEmpty>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelCmt2"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelCmt2" /></logic:notEmpty>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020usiTel3">
  <li>
    <logic:equal name="mbhUsr020Form" property="usr020usiTel3Kf" value="<%= close %>">
      <div class="font_small">■<gsmsg:write key="cmn.tel" />3</div>
      <span class="text_r1"><gsmsg:write key="cmn.private" /></span>
    </logic:equal>
    <logic:notEqual name="mbhUsr020Form" property="usr020usiTel3Kf" value="<%= close %>">
      <a href=tel:<bean:write name="mbhUsr020Form" property="usr020usiTel3" />>
        <div class="font_small">■<gsmsg:write key="cmn.tel" />3</div>
        <span style="color:blue"><bean:write name="mbhUsr020Form" property="usr020usiTel3" /></span>
      </a><br>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelNai3"><span class="attent1"><gsmsg:write key="user.136" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelNai3" />&nbsp;&nbsp;<br></logic:notEmpty>
      <logic:notEmpty name="mbhUsr020Form" property="usr020usiTelCmt3"><span class="attent1"><gsmsg:write key="cmn.comment" />：</span>&nbsp;<bean:write name="mbhUsr020Form" property="usr020usiTelCmt3" /></logic:notEmpty>
    </logic:notEqual>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="labelList">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.label" /></div>
    <logic:iterate id="labelData" name="mbhUsr020Form" property="labelList" indexId="idx">
    <bean:write name="labelData" property="labName" />
    <br>
    </logic:iterate>
  </li>
</logic:notEmpty>
<logic:notEmpty name="mbhUsr020Form" property="usr020grpNmlist">
  <li>
    <div class="font_small">■<gsmsg:write key="cmn.affiliation" /></div>
    <logic:iterate id="group" name="mbhUsr020Form" property="usr020grpNmlist" indexId="idx" scope="request">
      <bean:write name="group" property="groupName" />
      <logic:equal name="group" property="grpKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>"><gsmsg:write key="cmn.admin" /></logic:equal>
      <logic:notEqual name="group" property="grpKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConst.USER_ADMIN) %>">&nbsp;</logic:notEqual>
      <br>
    </logic:iterate>
  </li>
</logic:notEmpty>
</ul>

<logic:messagesPresent message="false">
<br>
<html:errors />
</logic:messagesPresent>

<div align="center" class="font_small">
  <input name="usr020back" value="<gsmsg:write key="cmn.back" />" type="submit" data-inline="true" data-role="button" data-icon="back"/>
</div>

</div><!-- /content -->

<%@ include file="/WEB-INF/plugin/mobile/jsp/sp_001_footer.jsp" %>

</html:form>
</div><!-- /page -->

</body>
</html:html>