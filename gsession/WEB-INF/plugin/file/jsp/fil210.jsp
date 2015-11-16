<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>

<%
   String crtKbnAdmin = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_ADMIN);
   String crtKbnGroup = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_GROUP);
   String crtKbnUser = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_USER);
   String crtKbnNo = String.valueOf(jp.groupsession.v2.fil.GSConstFile.CREATE_CABINET_PERMIT_NO);
   String lockKbnOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.LOCK_KBN_OFF);
   String lockKbnOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.LOCK_KBN_ON);
   String verKbnOff = String.valueOf(jp.groupsession.v2.fil.GSConstFile.VERSION_KBN_OFF);
   String verKbnOn = String.valueOf(jp.groupsession.v2.fil.GSConstFile.VERSION_KBN_ON);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.preferences" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../file/js/fil210.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/file.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../file/css/dtree.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
</head>

<body class="body_03" onload="viewchange();">

<html:form action="/file/fil210">
<input type="hidden" name="CMD" value="">
<html:hidden property="filSearchWd" />
<html:hidden property="backDsp" />
<html:hidden property="fil010SelectCabinet" />
<html:hidden property="fil010SelectDirSid" />

<logic:notEmpty name="fil210Form" property="fil040SelectDel" scope="request">
  <logic:iterate id="del" name="fil210Form" property="fil040SelectDel" scope="request">
    <input type="hidden" name="fil040SelectDel" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil210Form" property="fil210SvUsers">
<logic:iterate id="param" name="fil210Form" property="fil210SvUsers">
  <input type="hidden" name="fil210SvUsers" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil210Form" property="fil210SvGroups">
<logic:iterate id="param" name="fil210Form" property="fil210SvGroups">
  <input type="hidden" name="fil210SvGroups" value="<bean:write name="param" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="fil210Form" property="fil010SelectDelLink" scope="request">
  <logic:iterate id="del" name="fil210Form" property="fil010SelectDelLink" scope="request">
    <input type="hidden" name="fil010SelectDelLink" value="<bean:write name="del"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="backScreen" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>


<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table  cellpadding="0" cellspacing="0"width="70%">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.filekanri" />　<gsmsg:write key="cmn.preferences" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="buttonPush('fil210ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil210back');">
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <logic:messagesPresent message="false">
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr><td width="100%"><html:errors /></td></tr>
    </table>
    </logic:messagesPresent>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="10" cellspacing="0" border="0" width="100%" class="tl_u2">

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="15%" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.28" /></span><span class="text_r2">※</span>
    </td>

    <td valign="middle" align="left" class="td_wt" width="100%" width="85%">

      <div id="editselect" class="spacer">
      <html:radio name="fil210Form" styleId="view0" onclick="viewchange();" property="fil210CrtKbn" value="<%= crtKbnAdmin %>" /><label for="view0"><span class="text_base"><gsmsg:write key="fil.fil210.1" /></span></label>
      <html:radio name="fil210Form" styleId="view1" onclick="viewchange();" property="fil210CrtKbn" value="<%= crtKbnGroup %>" /><label for="view1"><span class="text_base"><gsmsg:write key="group.designation" /></span></label>
      <html:radio name="fil210Form" styleId="view2" onclick="viewchange();" property="fil210CrtKbn" value="<%= crtKbnUser %>" /><label for="view2"><span class="text_base"><gsmsg:write key="cmn.user.specified" /></span></label>
      </div>

    <!-- 管理者のみ -->
      <div id="viewadmin" class="spacer">
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td class="text_base">
      <gsmsg:write key="fil.29" />
      </td>
      </tr>
      </table>
      </div>

    <!-- グループ指定 -->
      <div id="viewgroup" class="spacer">
      <table width="0%" border="0">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="fil.fil210.2" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="left">
      </td>
      </tr>

      <tr>
      <td align="center">

        <html:select property="fil210LeftGroups" multiple="true" size="13" styleClass="select01">
        <logic:notEmpty name="fil210Form" property="fil210LeftGpList">
          <html:optionsCollection name="fil210Form" property="fil210LeftGpList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>

      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil210AddGroup');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil210DelGroup');">
      </td>

      <td valign="top">

        <html:select property="fil210RightGroups" multiple="true" size="13" styleClass="select01">
        <logic:notEmpty name="fil210Form" property="fil210RightGpList">
          <html:optionsCollection name="fil210Form" property="fil210RightGpList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>

      </td>
      </tr>

      </table>

      </div>

    <!-- ユーザ指定 -->
      <div id="viewuser" class="spacer">
      <table width="0%" border="0">
      <tr>
      <td width="40%" class="td_sub_title3" align="center"><span class="text_bb1"><gsmsg:write key="fil.fil210.2" /></span></td>
      <td width="20%" align="center">&nbsp;</td>

      <td width="40%" align="left">
        <input class="btn_group_n1" onclick="return openAllGroup(this.form.fil210SltGroup, 'fil210SltGroup', '-1', '0', 'changeGrp', 'fil210SvUsers', '-1', '0')" value="<gsmsg:write key="cmn.sel.all.group" />"  type="button"><br>
        <logic:notEmpty name="fil210Form" property="fil210DspGpList">
        <html:select property="fil210SltGroup" onchange="buttonPush('changeGrp');">
          <html:optionsCollection name="fil210Form" property="fil210DspGpList" value="value" label="label" />
        </html:select>
        </logic:notEmpty>

        <span class="text_base">
        <input type="button" onclick="openGroupWindow(this.form.fil210SltGroup, 'fil210SltGroup', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="fil210GroupBtn">
        <html:checkbox name="fil210Form" property="fil210AllSelect" value="1" styleId="all_select" onclick="allSelect();" />
        <label for="all_select"><gsmsg:write key="cmn.select" /></label></span>

      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="fil210LeftUsers" multiple="true" size="13" styleClass="select01">
        <logic:notEmpty name="fil210Form" property="fil210LeftList">
          <html:optionsCollection name="fil210Form" property="fil210LeftList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>

      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('fil210AddUser');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('fil210DelUser');">
      </td>

      <td valign="top">
        <html:select property="fil210RightUsers" multiple="true" size="13" styleClass="select01">
        <logic:notEmpty name="fil210Form" property="fil210RightList">
          <html:optionsCollection name="fil210Form" property="fil210RightList" value="value" label="label" />
        </logic:notEmpty>
        </html:select>

      </td>
      </tr>

      </table>
      </div>

    <!-- 権限なし -->

      <div id="viewall" class="spacer">
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
      <tr>
      <td class="text_base">
      <gsmsg:write key="fil.30" />
      </td>
      </tr>
      </table>
      </div>

    </td>
    </tr>


    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.31" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">

      <logic:notEmpty name="fil210Form" property="fil210FileSizeList">
      <html:select property="fil210FileSize">
        <html:optionsCollection name="fil210Form" property="fil210FileSizeList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>

      <br>
      <span class="text_base">※<gsmsg:write key="fil.32" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" width="0%" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.fil210.3" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" width="100%">

      <logic:notEmpty name="fil210Form" property="fil210SaveDaysList">
      <html:select property="fil210SaveDays">
        <html:optionsCollection name="fil210Form" property="fil210SaveDaysList" value="value" label="label" />
      </html:select>
      </logic:notEmpty>

      <br>
      <span class="text_base">※<gsmsg:write key="fil.33" /></span><br>
      <span class="text_base">※<gsmsg:write key="fil.fil210.5" /></span>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.34" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <html:radio name="fil210Form" styleId="lockKbn_01" property="fil210LockKbn" value="<%= lockKbnOff %>" /><label for="lockKbn_01"><span class="text_base7"><gsmsg:write key="fil.107" /></span></label>&nbsp;
      <html:radio name="fil210Form" styleId="lockKbn_02" property="fil210LockKbn" value="<%= lockKbnOn %>" /><label for="lockKbn_02"><span class="text_base7"><gsmsg:write key="fil.108" /></span></label>
    </td>
    </tr>

    <tr>
    <td valign="middle" align="left" class="td_sub_title3" nowrap>
      <span class="text_bb1"><gsmsg:write key="fil.69" /></span><span class="text_r2">※</span>
    </td>
    <td valign="middle" align="left" class="td_wt" >
      <html:radio name="fil210Form" styleId="verKbn_01" property="fil210VerKbn" value="<%= verKbnOff %>" /><label for="verKbn_01"><span class="text_base7"><gsmsg:write key="fil.107" /></span></label>&nbsp;
      <html:radio name="fil210Form" styleId="verKbn_02" property="fil210VerKbn" value="<%= verKbnOn %>" /><label for="verKbn_02"><span class="text_base7"><gsmsg:write key="fil.108" /></span></label>
    </td>
    </tr>

  </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" cellspacing="0" width="100%">
    <tr>
    <td align="right">
      <input type="button" name="btn_add" class="btn_ok1" value="OK" onClick="buttonPush('fil210ok');">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onclick="buttonPush('fil210back');">
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