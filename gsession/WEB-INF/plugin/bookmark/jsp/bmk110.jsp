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
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bookmark/css/bookmark.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<title>[Group Session] <gsmsg:write key="bmk.43" /></title>
</head>

<body class="body_03">
<html:form action="/bookmark/bmk110">

<input type="hidden" name="CMD" value="">
<html:hidden name="bmk110Form" property="backScreen" />
<%@ include file="/WEB-INF/plugin/bookmark/jsp/bmk010_hiddenParams.jsp" %>

<logic:notEmpty name="bmk110Form" property="bmk110UserSid">
<logic:iterate id="usid" name="bmk110Form" property="bmk110UserSid">
  <input type="hidden" name="bmk110UserSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk110Form" property="bmk110GrpSid">
<logic:iterate id="gsid" name="bmk110Form" property="bmk110GrpSid">
  <input type="hidden" name="bmk110GrpSid" value="<bean:write name="gsid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bmk110Form" property="bmk010delInfSid" scope="request">
<logic:iterate id="item" name="bmk110Form" property="bmk010delInfSid" scope="request">
  <input type="hidden" name="bmk010delInfSid" value="<bean:write name="item"/>">
</logic:iterate>
</logic:notEmpty>

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
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.setting.permissions" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.setting.permissions" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('bmk110kakunin');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk110back');">
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

    <!-- 共有ブックマーク編集権限 -->
    <tr>
    <td class="table_bg_A5B4E1" width="18%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.34" /></span></td>
    <td align="left" class="td_type20" width="82%"  nowrap>

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="bmk110Form" styleId="bmk110PubEditKbn_01" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ADMIN) %>" onclick="buttonPush('chgPubEditKbn');" /><label for="bmk110PubEditKbn_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="bmk110Form" styleId="bmk110PubEditKbn_02" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_GROUP) %>" onclick="buttonPush('chgPubEditKbn');" /><label for="bmk110PubEditKbn_02"><span class="text_base6"><gsmsg:write key="group.designation" /></span></label>&nbsp;
      <html:radio name="bmk110Form" styleId="bmk110PubEditKbn_03" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_USER) %>" onclick="buttonPush('chgPubEditKbn');" /><label for="bmk110PubEditKbn_03"><span class="text_base6"><gsmsg:write key="cmn.user.specified" /></span></label>&nbsp;
      <html:radio name="bmk110Form" styleId="bmk110PubEditKbn_04" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ALL) %>" onclick="buttonPush('chgPubEditKbn');" /><label for="bmk110PubEditKbn_04"><span class="text_base6"><gsmsg:write key="bmk.33" /></span></label>
      </td>
      </tr>
      <tr>
      <td align="left" width="100%" style="padding-top:4px; padding-left:4px">
      <logic:equal name="bmk110Form" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ADMIN) %>"><div class="text_base7"><gsmsg:write key="bmk.bmk110.02" /></div></logic:equal>
      <logic:equal name="bmk110Form" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_ALL) %>"><div class="text_base7"><gsmsg:write key="bmk.bmk110.01" /></div></logic:equal>

      <!-- 共有ブックマーク編集権限：グループ指定 -->
      <logic:equal name="bmk110Form" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_GROUP) %>">
          <table width="0%" border="0">
          <tr>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.editgroup" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.group" /></span></td>
          </tr>
          <tr>
          <td>
            <html:select name="bmk110Form" property="bmk110SelectRightGroup" size="6" multiple="true" styleClass="select01" style="width: 220px;">
              <logic:notEmpty name="bmk110Form" property="bmk110RightGroupList">
                <html:optionsCollection name="bmk110Form" property="bmk110RightGroupList" value="value" label="label" />
              </logic:notEmpty>
              <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </html:select>
          </td>
          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addGroup');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeGroup');">
          </td>
          <td align="center">
            <html:select name="bmk110Form" property="bmk110SelectLeftGroup" size="6" multiple="true" styleClass="select01" style="width: 220px;">
              <logic:notEmpty name="bmk110Form" property="bmk110LeftGroupList">
                <html:optionsCollection name="bmk110Form" property="bmk110LeftGroupList" value="value" label="label" />
              </logic:notEmpty>
              <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </html:select>
          </td>
          </tr>
          </table>
      </logic:equal>

      <!-- 共有ブックマーク編集権限：ユーザ指定 -->
      <logic:equal name="bmk110Form" property="bmk110PubEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.EDIT_POW_USER) %>">
          <table width="0%" border="0">
          <tr>
          <td width="30%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.edituser" /></span></td>
          <td width="20%" align="center">&nbsp;</td>
          <td width="30%" align="left">
            <input class="btn_group_n1" onclick="return openAllGroup(this.form.bmk110GroupSid, 'bmk110GroupSid', '-1', '0', 'changeGrp', 'bmk110UserSid', '-1')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
             <html:select name="bmk110Form" property="bmk110GroupSid" styleClass="select01" onchange="return buttonPush('changeGrp');" style="width: 220px;">
              <logic:notEmpty name="bmk110Form" property="bmk110GroupList">
                <html:optionsCollection name="bmk110Form" property="bmk110GroupList" value="value" label="label" />
              </logic:notEmpty>
            </html:select>
          </td>
          <td width="20%" align="left" valign="bottom">
          <input type="button" onclick="openGroupWindow(this.form.bmk110GroupSid, 'bmk110GroupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="bmk110GroupBtn">
          </td>
          </tr>

          <tr>
          <td>
            <html:select name="bmk110Form" property="bmk110SelectRightUser" size="6" multiple="true" styleClass="select01" style="width: 220px;">
              <logic:notEmpty name="bmk110Form" property="bmk110RightUserList">
                <html:optionsCollection name="bmk110Form" property="bmk110RightUserList" value="value" label="label" />
              </logic:notEmpty>
              <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </html:select>
          </td>

          <td align="center">
            <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addUser');"><br><br>
            <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeUser');">
          </td>

          <td align="center">
            <html:select name="bmk110Form" property="bmk110SelectLeftUser" size="6" multiple="true" styleClass="select01" style="width: 220px;">
              <logic:notEmpty name="bmk110Form" property="bmk110LeftUserList">
                <html:optionsCollection name="bmk110Form" property="bmk110LeftUserList" value="value" label="label" />
              </logic:notEmpty>
              <option value="-1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
            </html:select>
          </td>
          </tr>

          </table>
      </logic:equal>

      </td>
      </tr>
      </table>

    </td>
    </tr>

    <!-- グループブックマーク編集権限 -->
    <tr>
    <td class="table_bg_A5B4E1" width="18%" nowrap><span class="text_bb1"><gsmsg:write key="bmk.51" /></span></td>
    <td align="left" class="td_type20" width="82%">

      <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
      <td align="left" width="100%">
      <html:radio name="bmk110Form" styleId="bmk110GrpEditKbn_01" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_ADMIN) %>" onclick="buttonPush('chgGrpEditKbn');" /><label for="bmk110GrpEditKbn_01"><span class="text_base6"><gsmsg:write key="cmn.only.admin.editable" /></span></label>&nbsp;
      <html:radio name="bmk110Form" styleId="bmk110GrpEditKbn_02" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_GROUP) %>" onclick="buttonPush('chgGrpEditKbn');" /><label for="bmk110GrpEditKbn_02"><span class="text_base6"><gsmsg:write key="bmk.48" /></span></label>&nbsp;
      <html:radio name="bmk110Form" styleId="bmk110GrpEditKbn_03" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_ALL) %>" onclick="buttonPush('chgGrpEditKbn');" /><label for="bmk110GrpEditKbn_03"><span class="text_base6"><gsmsg:write key="bmk.33" /></span></label>
      </td>
      </tr>
      <tr>
      <td align="left" width="100%" style="padding-top:4px; padding-left:4px">
      <div class="text_base7">
      <logic:equal name="bmk110Form" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_ADMIN) %>"><gsmsg:write key="bmk.bmk110.03" /></logic:equal>
      <logic:equal name="bmk110Form" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_GROUP) %>"><gsmsg:write key="bmk.bmk110.04" /></logic:equal>
      <logic:equal name="bmk110Form" property="bmk110GrpEditKbn" value="<%= String.valueOf(jp.groupsession.v2.bmk.GSConstBookmark.GROUP_EDIT_ALL) %>"><gsmsg:write key="bmk.19" /></logic:equal>
      </div>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    </table>

    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
    <input type="button" class="btn_ok1" value="OK" onClick="return buttonPush('bmk110kakunin');">
    <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="return buttonPush('bmk110back');">
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