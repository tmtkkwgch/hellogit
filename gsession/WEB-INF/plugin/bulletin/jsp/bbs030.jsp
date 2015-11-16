<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
    String maxLengthComment        = String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.MAX_LENGTH_FORUMCOMMENT);
    String maxLengthTemplate        = String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.MAX_LENGTH_THREVALUE);
%>

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bbs.bbs030.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script language="JavaScript" src="../bulletin/js/bbs030.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script type="text/javascript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/grouppopup.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03" onload="showLengthId($('#inputstr')[0], <%= maxLengthComment %>, 'inputlength');showLengthId($('#inputstrTemplate')[0], <%= maxLengthTemplate %>, 'inputlengthTemplate');changeInitArea();changeInputDiskSize(<bean:write name="bbs030Form" property="bbs030diskSize" />);changeWarnDisk(<bean:write name="bbs030Form" property="bbs030warnDisk" />);">

<html:form action="/bulletin/bbs030">

<input type="hidden" name="helpPrm" value="<bean:write name="bbs030Form" property="bbs030cmdMode" />">

<input type="hidden" name="CMD" value="">
<html:hidden name="bbs030Form" property="backScreen" />
<html:hidden name="bbs030Form" property="s_key" />
<html:hidden name="bbs030Form" property="bbs010page1" />
<html:hidden name="bbs030Form" property="bbs020page1" />
<html:hidden name="bbs030Form" property="bbs020forumSid" />
<html:hidden name="bbs030Form" property="bbs020indexRadio" />
<html:hidden name="bbs030Form" property="bbs030cmdMode" />
<html:hidden name="bbs030Form" property="bbs030ImageName" />
<html:hidden name="bbs030Form" property="bbs030ImageSaveName" />

<logic:notEmpty name="bbs030Form" property="bbs030memberSid">
<logic:iterate id="usid" name="bbs030Form" property="bbs030memberSid">
  <input type="hidden" name="bbs030memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bbs030Form" property="bbs030memberSidRead">
<logic:iterate id="usid" name="bbs030Form" property="bbs030memberSidRead">
  <input type="hidden" name="bbs030memberSidRead" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bbs030Form" property="bbs030memberSidAdm">
<logic:iterate id="usid" name="bbs030Form" property="bbs030memberSidAdm">
  <input type="hidden" name="bbs030memberSidAdm" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="70%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.bulletin" />-<gsmsg:write key="bbs.bbs030.2" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="50%"></td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
      <td class="header_glay_bg" width="50%">
      <input type="button" value="ＯＫ" class="btn_ok1" onClick="buttonPush('forumConfirm');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backForumList');"></td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td align="left"><span class="TXT02"><html:errors/></span></td>
    </tr>
    </table>

    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    </logic:messagesPresent>

    <table width="100%" class="tl0" border="0" cellpadding="5">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.4" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
    <html:text name="bbs030Form" property="bbs030forumName" maxlength="70" style="width:624px;"/>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.comment" /></span></td>
    <td align="left" class="td_type20" width="100%">
    <textarea name="bbs030comment" style="width:644px;" rows="10"  onkeyup="showLengthStr(value, <%= maxLengthComment %>, 'inputlength');" id="inputstr"><bean:write name="bbs030Form" property="bbs030comment" /></textarea>
      <br>
      <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlength" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthComment %>&nbsp;<gsmsg:write key="cmn.character" /></span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
    <td align="left" class="td_type20">

      <table width="0%" border="0">
      <tr>
      <td width="35%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="35%" align="left">
        <input class="btn_group_n1" value="<gsmsg:write key="cmn.sel.all.group" />" onclick="return openAllGroup2(this.form.bbs030groupSid, 'bbs030groupSid', '<bean:write name="bbs030Form" property="bbs030groupSid" />', '0', 'changeGrp', 'bbs030memberSid', 'bbs030memberSidRead', 'bulletin', '-1', '1')" type="button"><br>
        <html:select property="bbs030groupSid" styleClass="select01" onchange="selectGroup();">
          <logic:notEmpty name="bbs030Form" property="bbs030GroupList">
          <html:optionsCollection name="bbs030Form" property="bbs030GroupList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td width="10%" align="center" valign="bottom">
        <input type="button" onclick="openGroupWindow(this.form.bbs030groupSid, 'bbs030groupSid', '0', 'changeGrp')" class="group_btn2" value="&nbsp;&nbsp;" id="bbs030GroupBtn">
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="bbs030SelectLeftUser" size="5" styleClass="select01" multiple="true">
          <logic:notEmpty name="bbs030Form" property="bbs030LeftUserList">
          <html:optionsCollection name="bbs030Form" property="bbs030LeftUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMember');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMember');">
      </td>
      <td rowspan="3">
        <html:select property="bbs030SelectRightUser" size="13" styleClass="select01" multiple="true">
          <logic:notEmpty name="bbs030Form" property="bbs030RightUserList">
          <html:optionsCollection name="bbs030Form" property="bbs030RightUserList" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>


      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="bbs030SelectLeftUserRead" size="5" styleClass="select01" multiple="true">
          <logic:notEmpty name="bbs030Form" property="bbs030LeftUserListRead">
          <html:optionsCollection name="bbs030Form" property="bbs030LeftUserListRead" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>

      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMemberRead');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMemberRead');">
      </td>
      </tr>


      </table>

    </td>
    </tr>


    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="bbs.35" /></span></td>
    <td align="left" class="td_type20">

      <table width="0%" border="0">
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.admin" /></span></td>
      <td width="20%" align="center">&nbsp;</td>
      <td width="40%" align="center">
        <html:select property="bbs030groupSidAdm" styleClass="select01" onchange="selectGroup();">
          <logic:notEmpty name="bbs030Form" property="bbs030GroupListAdm">
          <html:optionsCollection name="bbs030Form" property="bbs030GroupListAdm" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>

      <tr>
      <td align="center">
        <html:select property="bbs030SelectLeftUserAdm" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="bbs030Form" property="bbs030LeftUserListAdm">
          <html:optionsCollection name="bbs030Form" property="bbs030LeftUserListAdm" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      <td align="center">
        <input type="button" class="btn_base1add" value="<gsmsg:write key="cmn.add"/>" name="adduserBtn" onClick="buttonPush('addMemberAdm');"><br><br>
        <input type="button" class="btn_base1del" value="<gsmsg:write key="cmn.delete" />" name="deluserBtn" onClick="buttonPush('removeMemberAdm');">
      </td>
      <td>
        <html:select property="bbs030SelectRightUserAdm" size="7" styleClass="select01" multiple="true">
          <logic:notEmpty name="bbs030Form" property="bbs030RightUserListAdm">
          <html:optionsCollection name="bbs030Form" property="bbs030RightUserListAdm" value="value" label="label" />
          </logic:notEmpty>
        </html:select>
      </td>
      </tr>
      </table>

    </td>
    </tr>



    <tr>
    <td valign="middle" align="left" class="table_bg_A5B4E1" nowrap>
      <span class="text_bb1"><gsmsg:write key="cmn.icon" /></span>
    </td>
    <td valign="middle" align="left" class="td_wt">
      <table width="100%" border="0">
      <tr>
      <td width="0%">

        <logic:equal name="bbs030Form" property="bbs030ImageName" value="">
          <img src="../bulletin/images/cate_icon01.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1"><br>
        </logic:equal>
        <logic:notEqual name="bbs030Form" property="bbs030ImageName" value="">
          <img src="../bulletin/bbs030.do?CMD=getImageFile" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');">
        </logic:notEqual>
      </td>
      </tr>
      <tr>
      <td><span class="text_base"><gsmsg:write key="cmn.icon.size" /></span><br>
        <input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('bbs030tempdeleteMark');">
        &nbsp;<input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTempPlus('bbs030SelectTempFilesMark', '<bean:write name="bbs030Form" property="bbs030pluginId" />', '1', '1', 'bbs');">
      </td>
      <td>&nbsp;</td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.6" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
      <html:radio name="bbs030Form" styleId="bbs030reply_1" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>" /><label for="bbs030reply_1"><span class="text_base6"><gsmsg:write key="cmn.not.permit" /></span></label>&nbsp;
      <html:radio name="bbs030Form" styleId="bbs030reply_0" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>" /><label for="bbs030reply_0"><span class="text_base6"><gsmsg:write key="cmn.permit" /></span></label>
      <div class="text_base7">
            <gsmsg:write key="bbs.bbs030.3" />
      </div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.7" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
      <html:radio name="bbs030Form" styleId="bbs030read_0" property="bbs030read" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.NEWUSER_THRE_VIEW_NO) %>" /><label for="bbs030read_0"><span class="text_base6"><gsmsg:write key="cmn.read.yet" /></span></label>&nbsp;
      <html:radio name="bbs030Form" styleId="bbs030read_1" property="bbs030read" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.NEWUSER_THRE_VIEW_YES) %>" /><label for="bbs030read_1"><span class="text_base6"><gsmsg:write key="cmn.read.already" /></span></label>
      <div class="text_base7">
            <gsmsg:write key="bbs.bbs030.4" />
      </div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.all.read" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
      <html:radio name="bbs030Form" styleId="bbs030mread_1" property="bbs030mread" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>" /><label for="bbs030mread_1"><span class="text_base6"><gsmsg:write key="cmn.not.permit" /></span></label>&nbsp;
      <html:radio name="bbs030Form" styleId="bbs030mread_0" property="bbs030mread" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>" /><label for="bbs030mread_0"><span class="text_base6"><gsmsg:write key="cmn.permit" /></span></label>
      <div class="text_base7">
        <gsmsg:write key="bbs.bbs030.5" />
      </div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="wml.87" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
      <html:radio name="bbs030Form" property="bbs030diskSize" styleId="disk1" value="0" onclick="changeInputDiskSize(0);" /><label for="disk1" class="text_base6"><gsmsg:write key="wml.31" /></label>
      &nbsp;<html:radio name="bbs030Form" property="bbs030diskSize" styleId="disk2" value="1" onclick="changeInputDiskSize(1);" /><label for="disk2" class="text_base6"><gsmsg:write key="wml.32" /></label>
      <span id="inputDiskSize" class="text_base6">&nbsp;<html:text name="bbs030Form" property="bbs030diskSizeLimit" styleClass="width:60%;" maxlength="6" style="width:77px;"/>MB&nbsp;</span>
    </td>
    </tr>

    <tr id="warnDiskArea">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.15" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
        <html:radio name="bbs030Form" property="bbs030warnDisk" styleId="warnDisk1" value="0" onclick="changeWarnDisk(0);" /><label for="warnDisk1" class="text_base6"><gsmsg:write key="cmn.notset" /></label>
        &nbsp;<html:radio name="bbs030Form" property="bbs030warnDisk" styleId="warnDisk2" value="1" onclick="changeWarnDisk(1);" /><label for="warnDisk2" class="text_base6"><gsmsg:write key="cmn.warning2" /></label>
        <span id="warnDiskThresholdArea">
          <span class="text_base6">
          &nbsp;&nbsp;<gsmsg:write key="cmn.threshold" />:&nbsp;
          <html:select name="bbs030Form" property="bbs030warnDiskThreshold">
            <html:optionsCollection  name="bbs030Form" property="warnDiskThresholdList" value="value" label="label" />
          </html:select>
          %
        </span>
        <div class="text_base7">
          <gsmsg:write key="bbs.bbs030.9" />
        </div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs030.6" /></span></td>
    <td align="left" class="td_type20" width="100%">

      <html:radio name="bbs030Form" onclick="changeInitArea();" styleId="bbs030templateKbn_1" property="bbs030templateKbn" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_TEMPLATE_NO) %>" /><label for="bbs030templateKbn_1"><span class="text_base6"><gsmsg:write key="cmn.noset" /></span></label>
      <html:radio name="bbs030Form" onclick="changeInitArea();" styleId="bbs030templateKbn_0" property="bbs030templateKbn" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_TEMPLATE_YES) %>" /><label for="bbs030templateKbn_0"><span class="text_base6"><gsmsg:write key="cmn.setting.do" /></span></label>&nbsp;

      <div class="text_base7">
        <gsmsg:write key="bbs.bbs030.7" />
      </div>

      <div id ="templateArea">

      <br>

        <div class="text_base">
        <html:checkbox name="bbs030Form" property="bbs030templateWriteKbn"  styleId="toukouCheck" value="1" />
        <label for="toukouCheck"><gsmsg:write key="bbs.bbs030.8" /></label>
        </div>

        <textarea name="bbs030template" cols="50" rows="10" onkeyup="showLengthStr(value, <%= maxLengthTemplate %>, 'inputlengthTemplate');" id="inputstrTemplate"><bean:write name="bbs030Form" property="bbs030template" /></textarea>
        <br>
        <span class="font_string_count"><gsmsg:write key="cmn.current.characters" />:</span><span id="inputlengthTemplate" class="font_string_count">0</span>&nbsp;/&nbsp;<span class="font_string_count_max"><%= maxLengthTemplate %>&nbsp;<gsmsg:write key="cmn.character" /></span>
      </div>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.12" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
      <html:radio name="bbs030Form" property="bbs030LimitDisable" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_DISABLE) %>" styleId="limiton1" onclick="doInit();" /><label for="limiton1" class="text_base6"><gsmsg:write key="fil.107" /></label>
      &nbsp;<html:radio name="bbs030Form" property="bbs030LimitDisable" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_ENABLE) %>" styleId="limiton2" onclick="doInit();" /><label for="limiton2" class="text_base6"><gsmsg:write key="fil.108" /></label>

    </td>
    </tr>

    <tr id="inputLimitEnableLimit">
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs030.10" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
    <div >
      <html:radio name="bbs030Form" property="bbs030Limit" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_LIMIT_NO) %>" styleId="limit1" /><label for="limit1" class="text_base6"><gsmsg:write key="cmn.unlimited" /></label>
      &nbsp;<html:radio name="bbs030Form" property="bbs030Limit" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_LIMIT_YES) %>" styleId="limit2" /><label for="limit2" class="text_base6"><gsmsg:write key="bbs.bbs070.4" /></label>
      <span id="inputLimitDate" class="text_base6">&nbsp;<html:text name="bbs030Form" property="bbs030LimitDate" styleClass="width:60%;" maxlength="3" style="width:59px;"/><gsmsg:write key="cmn.days.after2" />&nbsp;</span>
    </div>
    </td>
    </tr>

    <tr id="inputLimitEnableDate">
    <td class="table_bg_A5B4E1" width="0%" nowrap ><span class="text_bb1"><gsmsg:write key="bbs.bbs030.11" /></span><span class="text_r3">※</span></td>
    <td align="left" class="td_type20" width="100%">
      <html:radio name="bbs030Form" property="bbs030Keep" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_KEEP_NO) %>" styleId="keep1" /><label for="keep1" class="text_base6"><gsmsg:write key="cmn.noset" /></label>
      &nbsp;<html:radio name="bbs030Form" property="bbs030Keep" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.THREAD_KEEP_YES) %>" styleId="keep2" /><label for="keep2" class="text_base6"><gsmsg:write key="cmn.setting.do" /></label>
      <br>
      <span><gsmsg:write key="bbs.bbs030.12" /></span>
      <logic:equal name="bbs030Form" property="bbs030DspAtdelFlg" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.AUTO_DELETE_ON) %>">
          <span><br><gsmsg:write key="bbs.bbs030.13" /></span>
          <bean:define id="dspAtdelYear" name="bbs030Form" property="bbs030DspAtdelYear" type="java.lang.Integer" />
          <bean:define id="dspAtdelMonth" name="bbs030Form" property="bbs030DspAtdelMonth" type="java.lang.Integer" />
          <span><br>　<gsmsg:write key="cmn.autodelete.setting" />&nbsp;:&nbsp;<gsmsg:write key="bbs.bbs030.15" arg0="<%= String.valueOf(dspAtdelYear) %>" arg1="<%= String.valueOf(dspAtdelMonth) %>" /></span>
      </logic:equal>
      <div id="inputKeepDate">
      <br>
      <span class="text_base6"><gsmsg:write key="bbs.bbs030.14" /></span>&nbsp;
      <html:select name="bbs030Form" property="bbs030KeepDateY">
        <html:optionsCollection  name="bbs030Form" property="bbs030KeepDateYLabel" value="value" label="label" />
      </html:select>
      <html:select name="bbs030Form" property="bbs030KeepDateM">
        <html:optionsCollection  name="bbs030Form" property="bbs030KeepDateMLabel" value="value" label="label" />
      </html:select>
      </div>
    </td>
    </tr>

    </table>

    <table cellpadding="0" cellspacing="0" width="100%" style="margin-top: 10px;">
      <tr>
      <td align="right">
      <input type="button" value="ＯＫ" class="btn_ok1" onClick="buttonPush('forumConfirm');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backForumList');">
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