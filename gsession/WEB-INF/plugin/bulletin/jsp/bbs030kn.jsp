<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<%@ page import="jp.groupsession.v2.bbs.GSConstBulletin" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="bbs.bbs030kn.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs030kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs030kn">

<input type="hidden" name="helpPrm" value="<bean:write name="bbs030knForm" property="bbs030cmdMode" />">

<input type="hidden" name="CMD" value="">
<html:hidden name="bbs030knForm" property="backScreen" />
<html:hidden name="bbs030Form" property="bbs020indexRadio" />
<html:hidden name="bbs030knForm" property="s_key" />
<html:hidden name="bbs030knForm" property="bbs010page1" />
<html:hidden name="bbs030knForm" property="bbs020page1" />
<html:hidden name="bbs030knForm" property="bbs020forumSid" />
<html:hidden name="bbs030knForm" property="bbs030cmdMode" />
<html:hidden name="bbs030knForm" property="bbs030forumName" />
<html:hidden name="bbs030knForm" property="bbs030comment" />
<html:hidden name="bbs030knForm" property="bbs030groupSid" />
<html:hidden name="bbs030knForm" property="bbs030reply" />
<html:hidden name="bbs030knForm" property="bbs030read" />
<html:hidden name="bbs030knForm" property="bbs030ImageName" />
<html:hidden name="bbs030knForm" property="bbs030ImageSaveName" />
<html:hidden name="bbs030knForm" property="bbs030mread" />
<html:hidden name="bbs030knForm" property="bbs030templateKbn" />
<html:hidden name="bbs030knForm" property="bbs030template" />
<html:hidden name="bbs030knForm" property="bbs030templateWriteKbn" />
<html:hidden name="bbs030knForm" property="bbs030diskSize" />
<html:hidden name="bbs030knForm" property="bbs030diskSizeLimit" />
<html:hidden name="bbs030knForm" property="bbs030warnDisk" />
<html:hidden name="bbs030knForm" property="bbs030warnDiskThreshold" />

<html:hidden name="bbs030knForm" property="bbs030LimitDisable" />
<html:hidden name="bbs030knForm" property="bbs030Limit" />
<html:hidden name="bbs030knForm" property="bbs030LimitDate" />
<html:hidden name="bbs030knForm" property="bbs030Keep" />
<html:hidden name="bbs030knForm" property="bbs030KeepDateY" />
<html:hidden name="bbs030knForm" property="bbs030KeepDateM" />

<logic:notEmpty name="bbs030knForm" property="bbs030memberSid">
<logic:iterate id="usid" name="bbs030knForm" property="bbs030memberSid">
  <input type="hidden" name="bbs030memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bbs030knForm" property="bbs030memberSidRead">
<logic:iterate id="usid" name="bbs030knForm" property="bbs030memberSidRead">
  <input type="hidden" name="bbs030memberSidRead" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="bbs030knForm" property="bbs030memberSidAdm">
<logic:iterate id="usid" name="bbs030knForm" property="bbs030memberSidAdm">
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
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.bulletin" />-<gsmsg:write key="bbs.bbs030kn.1" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="50%"></td>
        <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
        <td class="header_glay_bg" width="50%">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_ok1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
        </td>
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
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.4" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="bbs030knForm" property="bbs030forumName" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.comment" /></span></td>
    <td align="left" class="td_type20" width="100%"><bean:write name="bbs030knForm" property="bbs030viewcomment" filter="false" /></td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.member" /></span></td>
    <td align="left" class="td_type20">


      <table width="60%" cellpadding="0" cellspacing="5" border="0" class="tl_u2">
      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.add.edit.delete" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_type1">
        <logic:notEmpty name="bbs030knForm" property="bbs030knMemNameList">
        <logic:iterate id="memName" name="bbs030knForm" property="bbs030knMemNameList">
          <span class="text_base"><bean:write name="memName" property="label" /></span><br>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="bbs030knForm" property="bbs030knMemNameList">&nbsp;</logic:empty>

      </td>
      </tr>

      <tr>
      <td width="40%" class="table_bg_A5B4E1" align="center"><span class="text_bb1"><gsmsg:write key="cmn.reading" /></span></td>
      </tr>
      <tr>
      <td align="left" class="td_type1">
        <logic:notEmpty name="bbs030knForm" property="bbs030knMemNameListRead">
        <logic:iterate id="memName" name="bbs030knForm" property="bbs030knMemNameListRead">
          <span class="text_base"><bean:write name="memName" property="label" /></span><br>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="bbs030knForm" property="bbs030knMemNameListRead">&nbsp;</logic:empty>


      </td>
      </tr>
      </table>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="bbs.35" /></span></td>
    <td align="left" class="td_type20">
      <logic:notEmpty name="bbs030knForm" property="bbs030knMemNameListAdm">
      <logic:iterate id="memName" name="bbs030knForm" property="bbs030knMemNameListAdm">
          <bean:write name="memName" property="label" /><br>
      </logic:iterate>
      </logic:notEmpty>
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
      <logic:equal name="bbs030knForm" property="bbs030ImageName" value="">
        <img src="../bulletin/images/cate_icon01.gif" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1">
      </logic:equal>
      <logic:notEqual name="bbs030knForm" property="bbs030ImageName" value="">
          <img src="../bulletin/bbs030kn.do?CMD=getImageFile&" name="pitctImage" width="30" height="30" alt="<gsmsg:write key="cmn.icon" />" border="1" onload="initImageView('pitctImage');">
      </logic:notEqual>
      </td>
      </tr>
      </table>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.6" /></span></td>
    <td align="left" class="td_type20" width="100%">
    <logic:equal name="bbs030knForm" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>"><gsmsg:write key="cmn.permit" /></logic:equal>
    <logic:equal name="bbs030knForm" property="bbs030reply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>"><gsmsg:write key="cmn.not.permit" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.7" /></span></td>
    <td align="left" class="td_type20" width="100%">
    <logic:equal name="bbs030knForm" property="bbs030read" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.NEWUSER_THRE_VIEW_NO) %>"><gsmsg:write key="cmn.read.yet" /></logic:equal>
    <logic:equal name="bbs030knForm" property="bbs030read" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.NEWUSER_THRE_VIEW_YES) %>"><gsmsg:write key="cmn.read.already" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="cmn.all.read" /></span></td>
    <td align="left" class="td_type20" width="100%">
    <logic:equal name="bbs030knForm" property="bbs030mread" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_FORUM_MREAD_YES) %>"><gsmsg:write key="cmn.permit" /></logic:equal>
    <logic:equal name="bbs030knForm" property="bbs030mread" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_FORUM_MREAD_NO) %>"><gsmsg:write key="cmn.not.permit" /></logic:equal>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="wml.87" /></span></td>
    <td align="left" class="td_type20" width="100%">
      <logic:equal name="bbs030knForm" property="bbs030diskSize" value="<%= String.valueOf(GSConstBulletin.BFI_DISK_NOLIMIT) %>">
      <gsmsg:write key="wml.31" />
      </logic:equal>
      <logic:equal name="bbs030knForm" property="bbs030diskSize" value="<%= String.valueOf(GSConstBulletin.BFI_DISK_LIMITED) %>">
      <gsmsg:write key="wml.32" />&nbsp;&nbsp;<bean:write name="bbs030knForm" property="bbs030diskSizeLimit" />MB
      </logic:equal>
    </td>
    </tr>

    <logic:equal name="bbs030knForm" property="bbs030diskSize" value="<%= String.valueOf(GSConstBulletin.BFI_DISK_LIMITED) %>">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="wml.wml150.15" /></span></td>
    <td align="left" class="td_type20" width="100%">
      <logic:equal name="bbs030knForm" property="bbs030warnDisk" value="<%= String.valueOf(GSConstBulletin.BFI_WARN_DISK_NO) %>">
      <gsmsg:write key="cmn.notset" />
      </logic:equal>
      <logic:equal name="bbs030knForm" property="bbs030warnDisk" value="<%= String.valueOf(GSConstBulletin.BFI_WARN_DISK_YES) %>">
      <gsmsg:write key="cmn.warning2" />&nbsp;&nbsp;
      <gsmsg:write key="cmn.threshold" />&nbsp;<bean:write name="bbs030knForm" property="bbs030warnDiskThreshold" />%
      </logic:equal>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs030.6" /></span></td>
    <td align="left" class="td_type20" width="100%">

      <logic:equal name="bbs030knForm" property="bbs030templateKbn" value="0">
        <gsmsg:write key="cmn.noset" />
      </logic:equal>

      <logic:equal name="bbs030knForm" property="bbs030templateKbn" value="1">
        <gsmsg:write key="cmn.setting.do" />

        <logic:equal name="bbs030knForm" property="bbs030templateWriteKbn" value="1">
          &nbsp;&nbsp;<span class="text_r1"><gsmsg:write key="bbs.bbs030.8" /></span>
        </logic:equal>

        <table width="99%" cellpadding="0" cellspacing="5" border="0" class="tl_u2" style="margin-top: 10px;">
          <tr>
          <td class="table_bg_A5B4E1" align="center">
          <span class="text_bb1"><gsmsg:write key="cmn.template" /></span>
          </td>
          </tr>
          <tr>
          <td align="left" class="td_type1">
            <bean:write name="bbs030knForm" property="bbs030viewTemplate" filter="false" />
          </td>
          </tr>
        </table>

      </logic:equal>

    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.12" /></span></td>
    <td align="left" class="td_type20" width="100%">
      <logic:equal name="bbs030knForm" property="bbs030LimitDisable" value="<%= String.valueOf(GSConstBulletin.THREAD_DISABLE) %>">
      <gsmsg:write key="fil.107" />
      </logic:equal>
      <logic:equal name="bbs030knForm" property="bbs030LimitDisable" value="<%= String.valueOf(GSConstBulletin.THREAD_ENABLE) %>">
      <gsmsg:write key="fil.108" />
      </logic:equal>
    </td>
    </tr>

    <logic:equal name="bbs030knForm" property="bbs030LimitDisable" value="<%= String.valueOf(GSConstBulletin.THREAD_ENABLE) %>">
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs030.10" /></span></td>
    <td align="left" class="td_type20" width="100%">
      <logic:equal name="bbs030knForm" property="bbs030Limit" value="<%= String.valueOf(GSConstBulletin.THREAD_LIMIT_NO) %>">
      <gsmsg:write key="cmn.unlimited" />
      </logic:equal>
      <logic:equal name="bbs030knForm" property="bbs030Limit" value="<%= String.valueOf(GSConstBulletin.THREAD_LIMIT_YES) %>">
      <gsmsg:write key="bbs.bbs070.4" />&nbsp;&nbsp;<bean:write name="bbs030knForm" property="bbs030LimitDate" /><gsmsg:write key="cmn.days.after2" />
      </logic:equal>
    </td>
    </tr>
    <tr>
    <td class="table_bg_A5B4E1" width="0%" nowrap><span class="text_bb1"><gsmsg:write key="bbs.bbs030.11" /></span></td>
    <td align="left" class="td_type20" width="100%">
      <logic:equal name="bbs030knForm" property="bbs030Keep" value="<%= String.valueOf(GSConstBulletin.THREAD_KEEP_NO) %>">
      <gsmsg:write key="cmn.noset" />
      </logic:equal>
      <logic:equal name="bbs030knForm" property="bbs030Keep" value="<%= String.valueOf(GSConstBulletin.THREAD_KEEP_YES) %>">
      <bean:define id="keepMonth" name="bbs030knForm" property="bbs030KeepDateM" />
      <% String strKeepMonth = String.valueOf(keepMonth); %>
      <gsmsg:write key="cmn.setting.do" /><br>
      <gsmsg:write key="bbs.bbs030.14" />&nbsp;<bean:write name="bbs030knForm" property="bbs030KeepDateY" />年<gsmsg:write key="cmn.months" arg0="<%= strKeepMonth %>"/>
      </logic:equal>
    </td>
    </tr>
    </logic:equal>
    </table>

    <table cellpadding="0" cellspacing="0" width="100%" style="padding-top: 10px;">
      <tr>
        <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_ok1" onClick="buttonPush('decision');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('backToInput');">
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