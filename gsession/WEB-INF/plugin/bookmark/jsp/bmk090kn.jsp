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
<html:form action="/bookmark/bmk090kn">
<input type="hidden" name="CMD" value="">

<html:hidden name="bmk090knForm" property="bmk090GrpName" />
<html:hidden name="bmk090knForm" property="bmk090GrpEditKbn" />
<html:hidden name="bmk090knForm" property="bmk090GroupSid" />

<logic:notEmpty name="bmk090knForm" property="bmk090UserSid">
<logic:iterate id="usid" name="bmk090knForm" property="bmk090UserSid">
  <input type="hidden" name="bmk090UserSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk090knForm" property="bmk090GrpSid">
<logic:iterate id="gsid" name="bmk090knForm" property="bmk090GrpSid">
  <input type="hidden" name="bmk090GrpSid" value="<bean:write name="gsid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk090knForm" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk090knForm" property="bmk010delInfSid" scope="request">
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
    <td width="0%"><img src="../bookmark/images/header_link01.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="bmk.43" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.setting.permissions.kn" />(<gsmsg:write key="bmk.51" />) ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="bmk.43" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('bmk090knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk090knback');">
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
  <bean:define id="grpNameStr" name="bmk090knForm" property="bmk090GrpName" type="java.lang.String" />
  <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
  <% String grpMsg = "<span class=\"attent1\">" + gsMsg.getMessage(request, "cmn.group") + gsMsg.getMessage(request, "wml.215") + grpNameStr + "</span>"; %>
  <gsmsg:write key="bmk.44" arg0="<%= grpMsg %>" /></span>
  </td>
  </tr>
  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>
  <tr>
  <td>

    <table class="tl0" width="100%" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="15%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.edit.permissions" /></span></a></td>
    <td class="td_type1" width="85%"  nowrap>
    <logic:equal name="bmk090knForm" property="bmk090GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ADMIN) %>"><gsmsg:write key="bmk.50" /></logic:equal>
    <logic:equal name="bmk090knForm" property="bmk090GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ALL) %>"><gsmsg:write key="bmk.33" /></logic:equal>

    <!-- 共有ブックマーク編集権限：グループ指定 -->
    <logic:equal name="bmk090knForm" property="bmk090GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_GROUP) %>">
        <gsmsg:write key="group.designation" /><gsmsg:write key="wml.215" /><br>
        <logic:notEmpty name="bmk090knForm" property="bmk090knGroupList">
          <logic:iterate id="groupName" name="bmk090knForm" property="bmk090knGroupList">
          <span style="padding-left:10px"><bean:write name="groupName" /></span><br>
          </logic:iterate>
        </logic:notEmpty>
    </logic:equal>
    <!-- 共有ブックマーク編集権限：ユーザ指定 -->
    <logic:equal name="bmk090knForm" property="bmk090GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_USER) %>">
        <gsmsg:write key="cmn.user.specified" /><gsmsg:write key="wml.215" /><br>
        <logic:notEmpty name="bmk090knForm" property="bmk090knUserList">
          <logic:iterate id="userName" name="bmk090knForm" property="bmk090knUserList">
          <span style="padding-left:10px"><bean:write name="userName" /></span><br>
          </logic:iterate>
        </logic:notEmpty>
    </logic:equal>
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
    <input type="button" class="btn_base1" value="<gsmsg:write key="cmn.final" />" onClick="return buttonPush('bmk090knkakutei');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk090knback');">
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