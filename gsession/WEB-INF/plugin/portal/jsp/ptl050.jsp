<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
    String maxLengthDescription = String.valueOf(jp.groupsession.v2.man.GSConstPortal.MAX_LENGTH_PTL_DESCRIPTION);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../portal/js/ptl050.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../portal/css/portal.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js"></script>

<title>[Group Session] <gsmsg:write key="ptl.ptl050.1" /></title>
</head>

<!-- body -->
<body class="body_03" onload="showOrHide();showLengthId($('#inputstr')[0], 1000, 'inputlength');">
<html:form action="/portal/ptl050">
<input type="hidden" name="CMD" value="init">

<html:hidden property="ptlPortalSid" />
<html:hidden property="ptl030sortPortal" />
<html:hidden property="ptl050init" />
<html:hidden property="ptlCmdMode" />

<logic:notEmpty name="ptl050Form" property="ptl050memberSid">
<logic:iterate id="usid" name="ptl050Form" property="ptl050memberSid">
  <input type="hidden" name="ptl050memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<bean:define id="ptlSid" name="ptl050Form" property="ptlPortalSid" type="java.lang.Integer"/>
<% if (ptlSid > -1) {%>
<input type="hidden" name="helpPrm" value="1">
<% } else { %>
<input type="hidden" name="helpPrm" value="0">
<% } %>


<%@ include file="/WEB-INF/plugin/portal/jsp/ptl_hiddenParams.jsp" %>
<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">

<tr>

<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="80%">
  <tr>
  <td>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text">[ <gsmsg:write key="ptl.ptl050.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%">&nbsp;</td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush2('ptl050ok');">
          <logic:greaterThan name="ptl050Form" property="ptlPortalSid" value="0">
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush2('ptl050delete');">
          </logic:greaterThan>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('ptl050back');">
        </td>
        <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="10" height="10" border="0" alt="">
  </td>
  </tr>


  <logic:messagesPresent message="false">
  <tr>
    <td align="left"><span class="TXT02"><html:errors/></span></td>
  </tr>
  </logic:messagesPresent>

  <tr>
  <td>

    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
      <tr>
        <td class="table_bg_A5B4E1" width="250" nowrap><span class="text_bb1"><gsmsg:write key="ptl.4" /></span><span class="text_r2">※</span></td>
        <td align="left" class="td_wt" width="750">
          <html:text name="ptl050Form" property="ptl050name" size="40" maxlength="100" style="width:271px;" />
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.public.kbn" /></span><span class="text_r2">※</span></td>
        <td align="left" class="td_wt">
          <html:radio name="ptl050Form" styleId="ptl050open_0" property="ptl050openKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_OPENKBN_OK) %>" /><label for="ptl050open_0"><span class="text_base6"><gsmsg:write key="cmn.show" /></span></label>&nbsp;
          <html:radio name="ptl050Form" styleId="ptl050open_1" property="ptl050openKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_OPENKBN_NG) %>" /><label for="ptl050open_1"><span class="text_base6"><gsmsg:write key="cmn.hide" /></span></label>
        </td>
      </tr>

      <tr>
        <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.memo" /></span></td>
        <td align="left" class="td_wt">
          <textarea name="ptl050description" style="width:411px;" rows="10" onkeyup="showLengthStr(value, <%= maxLengthDescription %>, 'inputlength');" id="inputstr"><bean:write name="ptl050Form" property="ptl050description" /></textarea>
          <br>
          <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthDescription %>&nbsp;<gsmsg:write key="cmn.character" /></span>
        </td>
      </tr>

      <tr>
      <td valign="middle" align="left" class="td_sub_title" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.access.auth" /></span><span class="text_r2">※</span>
      </td>
      <td valign="middle" align="left" class="td_wt" width="100%">
        <html:radio name="ptl050Form" onclick="showOrHide();" styleId="ptl050access_0" property="ptl050accessKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_ACCESS_OFF) %>" /><label for="ptl050access_0"><span class="text_base6"><gsmsg:write key="cmn.not.limit" /></span></label>&nbsp;
        <html:radio name="ptl050Form" onclick="showOrHide();" styleId="ptl050access_1" property="ptl050accessKbn" value="<%= String.valueOf(jp.groupsession.v2.man.GSConstPortal.PTL_ACCESS_ON) %>" /><label for="ptl050access_1"><span class="text_base6"><gsmsg:write key="cmn.do.limit" /></span></label>

        <div id="hide0">

        </div>

        <div id="show0">

        <table width="0%" border="0">

        <tr>
        <td width="35%" align="center"></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="35%" align="left">
          <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup(this.form.ptl050accessKbnGroup, 'ptl050accessKbnGroup', '<bean:write name="ptl050Form" property="ptl050accessKbnGroup" />', '0', 'backRedraw', 'ptl050memberSid', '-1', '1', '0', '0')" type="button">
        </td>
        <td width="10%" align="left"></td>
        </tr>

        <tr>
        <td width="35%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
        <td width="20%" align="center">&nbsp;</td>
        <td width="35%" align="left">
          <html:select property="ptl050accessKbnGroup" style="width: 150px;" styleClass="select01" onchange="return buttonPush('changeGrp');">
            <logic:notEmpty name="ptl050Form" property="ptl050GroupList">
            <html:optionsCollection name="ptl050Form" property="ptl050GroupList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>

          <span class="text_base">
          <input type="checkbox" name="acAllSlt" value="1" id="select_user" onclick="return selectAccessList();" />
          <label for="select_user"><gsmsg:write key="cmn.select" /></label></span>
        </td>
        <td width="10%" align="left">
          <input type="button" onclick="openGroupWindow(this.form.ptl050accessKbnGroup, 'ptl050accessKbnGroup', '0', 'backRedraw')" class="group_btn2" value="&nbsp;&nbsp;" id="ptl050accessKbnGroupBtn">
        </td>
        </tr>

        <tr>
        <td>
          <html:select property="ptl050accessKbnRead" size="13" style="width:220px;" styleClass="select01" multiple="true">
            <logic:notEmpty name="ptl050Form" property="ptl050LeftUserList">
            <html:optionsCollection name="ptl050Form" property="ptl050LeftUserList" value="value" label="label" />
            </logic:notEmpty>
          </html:select>
        </td>
        <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMember');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMember');">
        </td>

        <td valign="top">
        <html:select property="ptl050SelectRightUser" size="13" style="width:220px;" styleClass="select01" multiple="true">
          <logic:notEmpty name="ptl050Form" property="ptl050RightUserList">
          <html:optionsCollection name="ptl050Form" property="ptl050RightUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
        </td>
        <td></td>
        </tr>

        </table>

        </div>

      </td>
      </tr>

    </table>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt="">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="100%" align="right">
          <input type="button" value="OK" class="btn_ok1" onClick="buttonPush2('ptl050ok');">
          <logic:greaterThan name="ptl050Form" property="ptlPortalSid" value="0">
          <input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n1" onClick="buttonPush2('ptl050delete');">
          </logic:greaterThan>
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush2('ptl050back');">
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

<!-- Footer -->
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>


</body>

</html:html>