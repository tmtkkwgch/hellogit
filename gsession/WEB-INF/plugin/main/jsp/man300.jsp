<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<% String maxLengthContent = String.valueOf(jp.groupsession.v2.man.GSConstMain.MAX_LENGTH_VALUE); %>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="man.info.admin.settings" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/popup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man300.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<% String closeScript = "calWindowClose();windowClose();"; %>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onunload="<%= closeScript %>">


<html:form action="/main/man300">

<html:hidden property="cmd" />
<html:hidden property="man320OrderKey" />
<html:hidden property="man320SortKey" />
<html:hidden property="man320FormAdminConfBtn" />
<html:hidden property="man320SltPage1" />
<html:hidden property="man320SltPage2" />
<html:hidden property="man320PageNum" />
<html:hidden property="man320SelectedSid" />

<logic:notEmpty name="man300Form" property="man300memberSid">
<logic:iterate id="usid" name="man300Form" property="man300memberSid">
  <input type="hidden" name="man300memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>
<html:hidden property="backScreen" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.information" /> <gsmsg:write key="cmn.admin.setting" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

      <input type="hidden" name="CMD" value="300_ok">
      <input type="submit" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('300_back');">

    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <bean:define id="tdColor" value="" />
    <% String[] tdColors = new String[] {"td_wt", "td_sch_type2"}; %>
    <% tdColor = tdColors[0]; %>

    <table width="100%" class="tl0" border="0" cellpadding="5">

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
    <td align="left" class="td_type20">

      <table width="0%" border="0">
      <tr>
      <td width="25%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="25%" align="left">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.man300groupSid, 'man300groupSid', '<bean:write name="man300Form" property="man300groupSid" />', '0', '300_group', 'man300memberSid', '-1', '1')" type="button">
        <html:select property="man300groupSid" styleClass="select01" onchange="selectGroup('300_group');">
          <logic:notEmpty name="man300Form" property="man300GroupList">
          <html:optionsCollection name="man300Form" property="man300GroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="30%" align="left" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.man300groupSid, 'man300groupSid', '0', '300_group')" class="group_btn2" value="&nbsp;&nbsp;" id="man300GroupBtn">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="man300SelectLeftUser" size="8" styleClass="select01" multiple="true">
          <logic:notEmpty name="man300Form" property="man300LeftUserList">
          <html:optionsCollection name="man300Form" property="man300LeftUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('300_leftarrow');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('300_rightarrow');">
      </td>
      <td>
        <html:select property="man300SelectRightUser" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="man300Form" property="man300RightUserList">
          <html:optionsCollection name="man300Form" property="man300RightUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>

    </table>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
  <tr>
  <td align="left">
    </td>
    <td align="right">

      <input type="submit" value="<gsmsg:write key="schedule.43" />" class="btn_edit_n1">
      <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush('300_back');">

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