<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[Group Session] <gsmsg:write key="wml.wml160kn.03" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
  <theme:css filename="theme.css"/>
  <link rel="stylesheet" href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
  <script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
</head>

<body class="body_03">

<html:form action="/webmail/wml160kn">

<%@ include file="/WEB-INF/plugin/webmail/jsp/wml010_hiddenParams.jsp" %>

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="wml030keyword" />
<html:hidden property="wml030group" />
<html:hidden property="wml030user" />
<html:hidden property="wml030pageTop" />
<html:hidden property="wml030pageBottom" />
<html:hidden property="wml030svKeyword" />
<html:hidden property="wml030svGroup" />
<html:hidden property="wml030svUser" />
<html:hidden property="wml030sortKey" />
<html:hidden property="wml030order" />
<html:hidden property="wml030searchFlg" />
<html:hidden property="wml160initFlg" />
<html:hidden property="wml160updateFlg" />

<html:hidden property="wmlViewAccount" />
<html:hidden property="wmlAccountMode" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt=""></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="wml.wml160kn.03" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <br>
    <span class="text_r1"><gsmsg:write key="main.man028kn.3" /></span>
    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="cmn.capture.file.name" /></span></td>
    <td class="td_type1"><span class="smail_td1"><bean:write name="wml160knForm" property="wml160knFileName" /></span></td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="cmn.overwrite" /></span><span class="text_r2"></td>
    <td class="td_type1" nowrap>
    <logic:equal name="wml160knForm" property="wml160updateFlg" value="1"><gsmsg:write key="wml.wml160.06" /></logic:equal>
    </td>
    </tr>
    </table>
  </td>
  </tr>

  <tr>
    <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>
    <table class="tl0 table_td_border" cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <th align="center" class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="wml.96" /></span></th>
    <th align="center" class="table_bg_A5B4E1" width="25%"><span class="text_bb1"><gsmsg:write key="cmn.employer" /></span></th>
    </tr>

    <logic:iterate id="useUserData" name="wml160knForm" property="wml160knUseUserList">
    <tr>
    <td align="left" class="smail_td1">
      <span class="text_base"><bean:write name="useUserData" property="accountName" /></span>
    </td>
    <td align="left" class="smail_td1">
    <span class="text_base">
      <bean:define id="useUserKbn" name="useUserData" property="userKbn" type="java.lang.Integer" />
      <% boolean grpFlg = (useUserKbn == 1); %>
      <% if (grpFlg) { %><gsmsg:write key="cmn.group" />:
      <% } else { %><gsmsg:write key="cmn.user" />:
      <% } %>
      <div style="margin-top: 10px; padding-left: 15px;">
        <logic:iterate id="useUserName" name="useUserData" property="userNameList">
        <% if (grpFlg) { %><bean:write name="useUserName" property="grpName" />
        <% } else { %><bean:write name="useUserName" property="usiSei" />  <bean:write name="useUserName" property="usiMei" />
        <% } %><br>
        </logic:iterate>
      </div>

      <logic:notEmpty name="useUserData" property="proxyUserNameList">
      <br><br>
      <gsmsg:write key="cmn.proxyuser" />:
      <div style="margin-top: 10px; padding-left: 15px;">
        <logic:iterate id="proxyUserName" name="useUserData" property="proxyUserNameList">
        <bean:write name="proxyUserName" property="usiSei" />  <bean:write name="proxyUserName" property="usiMei" /><br>
        </logic:iterate>
      </div>
      </logic:notEmpty>

    </span></td>
    </tr>
    </logic:iterate>

    </table>
  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="<gsmsg:write key="cmn.space" />">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" name="btn_add" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('decision');">
          <input type="button" name="btn_facilities_mnt" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('backInput');">
        </td>
      </tr>
    </table>
  </td>
  </tr>
  </table>

</td>
</tr>
</table>

</html:form>

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</body>
</html:html>