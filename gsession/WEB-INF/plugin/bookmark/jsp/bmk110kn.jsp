<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<body class="body_03">
<html:form action="/bookmark/bmk110kn">
<input type="hidden" name="CMD" value="">

<html:hidden name="bmk110knForm" property="backScreen" />
<html:hidden name="bmk110knForm" property="bmk110PubEditKbn" />
<html:hidden name="bmk110knForm" property="bmk110GrpEditKbn" />
<html:hidden name="bmk110knForm" property="bmk110GroupSid" />

<logic:notEmpty name="bmk110knForm" property="bmk110UserSid">
<logic:iterate id="usid" name="bmk110knForm" property="bmk110UserSid">
  <input type="hidden" name="bmk110UserSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk110knForm" property="bmk110GrpSid">
<logic:iterate id="gsid" name="bmk110knForm" property="bmk110GrpSid">
  <input type="hidden" name="bmk110GrpSid" value="<bean:write name="gsid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk110knForm" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk110knForm" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions.kn" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.permissions.kn" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions.kn" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('bmk110knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk110knback');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>
  <logic:messagesPresent message="false">
  <tr><td width="100%"><html:errors /></td></tr>
  <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
  </logic:messagesPresent>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.34" /></span></td>
    <td class="td_type1" width="80%" nowrap>
    <logic:equal name="bmk110knForm" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ADMIN) %>"><gsmsg:write key="cmn.only.admin.editable" /></logic:equal>
    <logic:equal name="bmk110knForm" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ALL) %>"><gsmsg:write key="bmk.33" /></logic:equal>

    <!-- 共有ブックマーク編集権限：グループ指定 -->
    <logic:equal name="bmk110knForm" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_GROUP) %>">
        <gsmsg:write key="group.designation" /><gsmsg:write key="wml.215" /><br>
        <logic:notEmpty name="bmk110knForm" property="bmk110knGroupList">
          <logic:iterate id="groupName" name="bmk110knForm" property="bmk110knGroupList">
          <span style="padding-left:10px"><bean:write name="groupName" /></span><br>
          </logic:iterate>
        </logic:notEmpty>
    </logic:equal>
    <!-- 共有ブックマーク編集権限：ユーザ指定 -->
    <logic:equal name="bmk110knForm" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_USER) %>">
        <gsmsg:write key="cmn.user.specified" /><gsmsg:write key="wml.215" /><br>
        <logic:notEmpty name="bmk110knForm" property="bmk110knUserList">
          <logic:iterate id="userName" name="bmk110knForm" property="bmk110knUserList">
          <span style="padding-left:10px"><bean:write name="userName" /></span><br>
          </logic:iterate>
        </logic:notEmpty>
    </logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="18%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.51" /></span></td>
    <td class="td_type1" width="82%">
    <logic:equal name="bmk110knForm" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_ADMIN) %>"><gsmsg:write key="cmn.only.admin.editable" /></logic:equal>
    <logic:equal name="bmk110knForm" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_GROUP) %>"><gsmsg:write key="bmk.48" /></logic:equal>
    <logic:equal name="bmk110knForm" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_ALL) %>"><gsmsg:write key="bmk.33" /></logic:equal>
    </td>
    </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>

  <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('bmk110knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk110knback');">
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