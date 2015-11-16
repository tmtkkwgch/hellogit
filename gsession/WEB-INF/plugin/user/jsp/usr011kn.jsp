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
<title>[GroupSession] <gsmsg:write key="user.44" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../user/js/usr011kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../user/css/user.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/user/usr011kn">
<input type="hidden" name="CMD" value="">
<html:hidden property="usr011gpId" />
<html:hidden property="usr011gpname" />
<html:hidden property="usr011gpnameKana" />
<html:hidden property="usr011com" />
<html:hidden property="usr011grpsid" />
<html:hidden property="usr011DelKbn" />
<html:hidden property="usr011DelButton" />
<html:hidden property="usr010grpmode" />
<html:hidden property="usr010grpSid" />
<html:hidden property="slt_group" />
<html:hidden property="selectgroup" />
<html:hidden property="disabledGroups"/>

<input type="hidden" name="helpPrm" value="<bean:write name="usr011knForm" property="usr011hrpPrm" />">

<logic:notEmpty name="usr011knForm" property="sv_users" scope="request">
  <logic:iterate id="ulBean" name="usr011knForm" property="sv_users" scope="request">
    <input type="hidden" name="sv_users" value="<bean:write name="ulBean" />">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="usr011knForm" property="sv_usersKr" scope="request">
  <logic:iterate id="ulkrBean" name="usr011knForm" property="sv_usersKr" scope="request">
    <input type="hidden" name="sv_usersKr" value="<bean:write name="ulkrBean" />">
  </logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!-- BODY -->
<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">
      <logic:equal name="usr011knForm" property="usr010grpmode"  scope="request" value="add">[ <gsmsg:write key="user.usr011kn.1" /> ]</logic:equal>
      <logic:equal name="usr011knForm" property="usr010grpmode"  scope="request" value="edit">
        <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">[ <gsmsg:write key="user.usr011kn.2" /> ]</logic:notEqual>
      </logic:equal>
      <logic:equal name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">[ <gsmsg:write key="user.usr011kn.3" /> ]</logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('commit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="pushBack(),buttonPush('usr011_back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <logic:messagesPresent message="false"><html:errors /></logic:messagesPresent>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="3" cellspacing="0" border="0" width="100%" align="center">
    <tr>
    <td>
      <span class="text_r1">
        <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del"><gsmsg:write key="user.usr011kn.4" /></logic:notEqual>
        <logic:equal name="usr011knForm" property="usr011DelKbn"  scope="request" value="del"><gsmsg:write key="user.usr011kn.5" /></logic:equal>
      </span>
    </td>
    </tr>
    </table>

    <table cellpadding="5" cellspacing="0" border="0" width="100%" class="tl2" align="center">

    <!-- グループ名 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.id" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <span class="text_base">
    <logic:equal name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011gpIdDel" />
    </logic:equal>
    <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011gpId" />
    </logic:notEqual>
    </span></td>
    </tr>

    <!-- グループ名 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.name" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <span class="text_base">
    <logic:equal name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011gpnameDel" />
    </logic:equal>
    <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011gpname" />
    </logic:notEqual>
    </span></td>
    </tr>

    <!-- グループ名カナ -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="user.14" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <span class="text_base">
    <logic:equal name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011gpnameKanaDel" />
    </logic:equal>
    <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011gpnameKana" />
    </logic:notEqual>
    </span></td>
    </tr>

    <!-- コメント -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.comment" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
    <span class="text_base">
    <logic:equal name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011comDel" filter="false" />
    </logic:equal>
    <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
      <bean:write name="usr011knForm" property="usr011comHtml" filter="false" />
    </logic:notEqual>
    </span></td>
    </tr>

    <!-- 所属者 -->
    <logic:notEqual name="usr011knForm" property="usr011DelKbn"  scope="request" value="del">
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="user.75" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <span class="text_base">
      <logic:notEmpty name="usr011knForm" property="usr011tarBelongingUser" scope="request">
        <logic:iterate id="ulBean" name="usr011knForm" property="usr011tarBelongingUser" scope="request">
          <bean:write name="ulBean" property="fullName" filter="false" />
          <br>
        </logic:iterate>
      </logic:notEmpty>
      </span>
    </td>
    </tr>

    <!-- グループ管理者 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.group.admin" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%">
      <span class="text_base">
      <logic:notEmpty name="usr011knForm" property="usr011BelongingUserKr" scope="request">
        <logic:iterate id="ulkrBean" name="usr011knForm" property="usr011BelongingUserKr" scope="request">
          <bean:write name="ulkrBean" property="fullName" filter="false" />
          <br>
        </logic:iterate>
      </logic:notEmpty>
      </span>
    </td>
    </tr>
    </logic:notEqual>

    <!-- グループ階層設定 -->
    <tr>
    <th valign="middle" align="left" class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="user.21" /></span></th>
    <td valign="middle" align="left" class="td_nrlb" width="100%"><span class="text_base"><bean:write name="usr011knForm" property="usr011knGroupClassName" /></span></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="buttonPush('commit');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="pushBack(),buttonPush('usr011_back');">
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