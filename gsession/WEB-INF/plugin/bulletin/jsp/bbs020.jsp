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
<title>[GroupSession] <gsmsg:write key="bbs.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../bulletin/js/bbs020.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../bulletin/css/bulletin.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
</head>

<body class="body_03">

<html:form action="/bulletin/bbs020">
<input type="hidden" name="CMD" value="">
<html:hidden name="bbs020Form" property="backScreen" />
<html:hidden name="bbs020Form" property="s_key" />
<html:hidden name="bbs020Form" property="bbs010page1" />
<html:hidden name="bbs020Form" property="bbs020forumSid" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!-- BODY -->
<table align="center"  cellpadding="5" cellspacing="0" border="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
  <td>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
        <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
        <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
        <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="cmn.bulletin" />-<gsmsg:write key="bbs.14" /> ]</td>
        <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      </tr>
    </table>

    <table cellpadding="5" cellspacing="0" width="100%">
      <tr>
        <td align="left">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.up" />" onClick="buttonPush('up');">
        <input type="button" class="btn_base0" value="<gsmsg:write key="cmn.down" />" onClick="buttonPush('down');">
        </td>
        <td align="right">
        <input type="button" class="btn_add_n1" name="new" value="<gsmsg:write key="cmn.add" />" onClick="buttonPush('addForum');">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('confMenu');"></span>
        </td>
      </tr>
    </table>

    <logic:notEmpty name="bbs020Form" property="forumList">
    <bean:size id="count1" name="bbs020Form" property="bbsPageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bbs020page1" onchange="changePage(0);" styleClass="text_i">
          <html:optionsCollection name="bbs020Form" property="bbsPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
      </td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tl0">
      <tr>
      <td width="0%" align="center" class="table_bg_7D91BD" nowrap>&nbsp;</td>
      <td width="16%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="bbs.4" /></span></td>
      <td width="50%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="cmn.comment" /></span></td>
      <td width="10%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="bbs.5" /></span></td>
      <td width="5%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="bbs.6" /></span></td>
      <td width="5%" align="center" class="table_bg_7D91BD" nowrap><span class="text_tlw"><gsmsg:write key="cmn.size" /></span></td>
      <td colspan="2" width="14%" align="center" class="table_bg_7D91BD" nowrap>&nbsp;</td>
      </tr>

      <logic:notEmpty name="bbs020Form" property="forumList">
      <logic:iterate id="forumMdl" name="bbs020Form" property="forumList" indexId="idx">
      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>
      <bean:define id="bfiSort" name="forumMdl" property="bfiSort" />

      <tr>
      <td class="<%= tdColor %>" align="center" nowrap><html:radio property="bbs020indexRadio" value="<%= String.valueOf(bfiSort) %>" styleId="<%= String.valueOf(bfiSort) %>" /></td>
      <td class="<%= tdColor %>"><label for="<%= String.valueOf(bfiSort) %>"><bean:write name="forumMdl" property="bfiName" /></label></td>
      <td class="<%= tdColor %>"><bean:write name="forumMdl" property="bfiCmtView" filter="false" /></td>
      <td class="<%= tdColor %>" align="right"><bean:write name="forumMdl" property="writeCnt" /></td>
      <td class="<%= tdColor %>" align="center">
      <logic:equal name="forumMdl" property="bfiReply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_YES) %>"><gsmsg:write key="cmn.accepted" /></logic:equal>
      <logic:equal name="forumMdl" property="bfiReply" value="<%= String.valueOf(jp.groupsession.v2.bbs.GSConstBulletin.BBS_THRE_REPLY_NO) %>"><gsmsg:write key="cmn.not" /></logic:equal>
      </td>
      <td class="<%= tdColor %>" align="right"><bean:write name="forumMdl" property="viewBfsSize" /></td>
      <td class="<%= tdColor %>" align="center"><input type="button" value="<gsmsg:write key="cmn.delete" />" class="btn_dell_n3" onClick="buttonPushWithId('delForum', '<bean:write name="forumMdl" property="bfiSid" />');"></td>
      <td class="<%= tdColor %>" align="center"><input type="button" value="<gsmsg:write key="cmn.edit" />" class="btn_edit_n3" onClick="buttonPushWithId('editForum', '<bean:write name="forumMdl" property="bfiSid" />');"></td>
      </tr>

      </logic:iterate>
      </logic:notEmpty>

    </table>

    <logic:notEmpty name="bbs020Form" property="forumList">
    <bean:size id="count1" name="bbs020Form" property="bbsPageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">
    <table width="100%" cellpadding="5" cellspacing="0">
      <tr>
      <td align="right">
        <img src="../common/images/arrow2_l.gif" alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('prevPage');">
        <html:select property="bbs020page2" onchange="changePage(1);" styleClass="text_i">
          <html:optionsCollection name="bbs020Form" property="bbsPageLabel" value="value" label="label" />
        </html:select>
        <img src="../common/images/arrow2_r.gif" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" height="20" width="20" onClick="buttonPush('nextPage');">
      </td>
      </tr>
    </table>
    </logic:greaterThan>
    </logic:notEmpty>
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