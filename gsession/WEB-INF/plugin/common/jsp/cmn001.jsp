<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<% String gsurl = jp.groupsession.v2.cmn.GSConst.GS_HOMEPAGE_URL; %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="mobile.17" /></title>
<LINK REL="SHORTCUT ICON" href="../common/images/favicon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href='../common/css/login.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn001.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<logic:equal name="cmn001Form" property="cmn001LoginCtrl" value="0">
<body class="body_09" onLoad="document.getElementsByName('cmn001Userid')[0].focus()">
</logic:equal>
<logic:notEqual name="cmn001Form" property="cmn001LoginCtrl" value="0">
<body class="body_09">
</logic:notEqual>

<div id="login_lt">

  <div align="left">
    <a href="<%= gsurl %>" target="_blank"><img src="../common/images/login/login_gslogo.gif" alt="<gsmsg:write key="cmn.cmn001.2" />" border="0"></a>
  </div>

</div>

<div id="login_box">
    <div align="left">
      <a href="<bean:write name="cmn001Form" property="cmn001Url" />" target="_blank">
      <logic:equal name="cmn001Form" property="cmn001BinSid" value="0">
        <img src="../common/images/login/login_gslogo2.gif" alt="<gsmsg:write key="cmn.cmn001.2" />" border="0">
      </logic:equal>
      <logic:notEqual name="cmn001Form" property="cmn001BinSid" value="0">
        <img src="../common/cmn001.do?CMD=getImageFile&cmn001BinSid=<bean:write name="cmn001Form" property="cmn001BinSid" />" alt="<gsmsg:write key="cmn.cmn001.2" />" border="0">
      </logic:notEqual>
      </a>
    </div>

    <logic:equal name="cmn001Form" property="cmn001LoginCtrl" value="0">
    <html:form action="/common/cmn001">
    <input type="hidden" name="CMD" value="login">
    <input type="hidden" name="cmn001loginType" value="<%= String.valueOf(jp.groupsession.v2.cmn.cmn001.Cmn001Form.LOGIN_TYPE_SCREEN) %>">
    <html:hidden property="url" />
    <html:hidden property="cmn001initAccess" />

      <div class="login_msg">
      <logic:messagesPresent message="false">
      <html:errors /><br>
      </logic:messagesPresent>
      <gsmsg:write key="cmn.cmn001.3" />
      </div>

      <div style="border: 0; vertical-align:bottom; margin-top: 10px;">
      <table width="0">

      <tr>
      <td><span class="login_text"><gsmsg:write key="cmn.user.id" /></span></td>
      <td><span class="login_text"><gsmsg:write key="user.117" /></span></td>
      <td></td>
      </tr>

      <tr>
      <td valign="middle">
       <html:text property="cmn001Userid" maxlength="256" size="20" styleClass="login_uid" tabindex="1" onfocus="changeBackColor('cmn001Userid', 'login_uid_focus');" onblur="changeBackColor('cmn001Userid', 'login_uid');" />
      </td>
      <td valign="middle">
        <html:password property="cmn001Passwd" maxlength="256" size="20" styleClass="login_pw" tabindex="2" onfocus="changeBackColor('cmn001Passwd', 'login_pw_focus');" onblur="changeBackColor('cmn001Passwd', 'login_pw');" />
      </td>
      <td valign="middle">
        <input type="submit" value="<gsmsg:write key="mobile.17" />" class="login_btn" tabindex="3">
      </td>
      </tr>
      </table>
      </div>

    </html:form>
    </logic:equal>

    <logic:notEqual name="cmn001Form" property="cmn001LoginCtrl" value="0">
    <div class="login_msg"><span class="text_error"><gsmsg:write key="cmn.cmn001.4" /><br><gsmsg:write key="cmn.cmn001.5" /></span></div>
    </logic:notEqual>

    <div id="tutorial">
      <div style="background-image:url(../common/images/login/opt_icon_tutorial.gif); background-repeat: no-repeat; padding-left: 30px; height:20px;">
        <a href="http://groupsession.jp/v4/tutorial/" target="_blank" class="link_style"><gsmsg:write key="cmn.cmn001.6" /></a>
      </div>
    </div>

    <div id="login_options">
      <div style="background-image:url(../common/images/login/opt_icon_mobile.gif); background-repeat: no-repeat; padding-left: 30px;">
        <a href="http://groupsession.jp/v4/seihin/mobile.html" target="_blank" class="link_style"><gsmsg:write key="cmn.cmn001.7" /></a>
      </div>
      <div style="margin-top:10px; padding-bottom:10px; background-image:url(../common/images/login/opt_icon_crossride.gif); background-repeat: no-repeat; padding-left: 30px;">
        <a href="../crossride_dnf4/index.html" class="link_style"><gsmsg:write key="cmn.cmn001.8" /></a>
      </div>
    </div>

</div>



<div align="center">
<div id="footer_login" >
  <div id="footer_links">
    <a href="../mobile/mh_cmn001.do"><img src="../common/images/login/link_mobile_mh.png" border="0" alt="<gsmsg:write key="cmn.cmn001.11" />"></a>
    <a href="../mobile/sp_cmn001.do"><img src="../common/images/login/link_mobile_sp.png" border="0" alt="<gsmsg:write key="cmn.cmn001.12" />"></a>
    <a href="http://groupsession.jp/v4/seihin/app.html" target="_blank"><img src="../common/images/login/link_appli.png" border="0" alt="<gsmsg:write key="cmn.appli" />"></a>
  </div>
  <div>
    <a href="http://www.sjts.co.jp/" target="_blank">&copy; Copyright <gsmsg:write key="cmn.cmn001.10" /> All rights reserved.</a>
  </div>
</div>
</div>

</body>
</html:html>