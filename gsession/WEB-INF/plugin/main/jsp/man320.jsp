<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
String orderAsc  = String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_ASC);
String orderDesc = String.valueOf(jp.groupsession.v2.cmn.GSConst.ORDER_KEY_DESC);
%>

<%
String sortMsg  = String.valueOf(jp.groupsession.v2.man.man320.Man320Biz.SORT_KEY_MSG);
String sortFrDate  = String.valueOf(jp.groupsession.v2.man.man320.Man320Biz.SORT_KEY_FRDATE);
String sortToDate  = String.valueOf(jp.groupsession.v2.man.man320.Man320Biz.SORT_KEY_TODATE);
String sortUsrName  = String.valueOf(jp.groupsession.v2.man.man320.Man320Biz.SORT_KEY_USR_NAME);
%>

<html:html>
<head>

<title>[GroupSession] <gsmsg:write key="main.man320.1" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man320.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../main/css/main.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/main/man320">

<input type="hidden" name="CMD" value="">
<html:hidden property="cmd" />
<html:hidden property="man320SortKey" />
<html:hidden property="man320OrderKey" />
<html:hidden property="man320PageNum" />
<html:hidden property="man320SelectedSid" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<!--　BODY -->
<table width="80%" cellpadding="5" cellspacing="0" align="center">
<tr>
<td width="100%" align="center">

  <table width="100%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- <gsmsg:write key="cmn.title" /> -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../main/images/header_info_01.gif" border="0" alt="<gsmsg:write key="man.information.management" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.information" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="main.man320.1" /> ]</td>
    <td width="100%" class="header_white_bg">
    <logic:equal name="man320Form" property="man320FormAdminConfBtn" value="1">
    <input type="button" class="btn_setting_admin_n1" value="<gsmsg:write key="cmn.admin.setting" />" onClick="buttonPush('admconf');">
    </logic:equal>
    </td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <logic:notEmpty name="man320Form" property="man320DspList">
      <input type="button" name="btn_del" class="btn_dell_n1" value="<gsmsg:write key="man.purge" />" onClick="buttonPush('man320delete');">
      </logic:notEmpty>
      <input type="button" class="btn_add_n1" value="<gsmsg:write key="cmn.create.new" />" onClick="addMsg();">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('320_back');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:empty name="man320Form" property="man320DspList">
    <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>
    <span class="text_base"><gsmsg:write key="man.no.info.registered" /></span>
    </logic:empty>
    <logic:notEmpty name="man320Form" property="man320DspList">

    <logic:messagesPresent message="false">
      <table width="100%">
      <tr>
        <td align="left"><span class="TXT02"><html:errors/></span></td>
      </tr>
      </table>
    </logic:messagesPresent>

    <div><img src="../common/images/spacer.gif" width="1" height="10" class="img_bottom"></div>

    <bean:size id="count1" name="man320Form" property="man320PageLabel" scope="request" />
    <logic:greaterThan name="count1" value="1">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right" valign="top" nowrap>
    <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
    <logic:notEmpty name="man320Form" property="man320PageLabel">
      <html:select property="man320SltPage1" onchange="changePage1();" styleClass="text_i">
        <html:optionsCollection name="man320Form" property="man320PageLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
    </td>
    </tr>
    </table>
    </logic:greaterThan>

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">

      <tr class="td_type3">
      <th width="0%" class="table_bg_7D91BD" nowrap><input type="checkbox" name="allChk" onClick="changeChk();"></th>
      <logic:equal name="man320Form" property="man320SortKey" value="<%= sortMsg %>">
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderAsc %>">
        <th width="100%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortMsg %>', '<%= orderDesc %>')"><span class="text_tlw"><gsmsg:write key="cmn.message" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderDesc %>">
        <th width="100%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortMsg %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="cmn.message" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man320Form" property="man320SortKey" value="<%= sortMsg %>">
        <th width="100%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortMsg %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="cmn.message" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="man320Form" property="man320SortKey" value="<%= sortFrDate %>">
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderAsc %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortFrDate %>', '<%= orderDesc %>')"><span class="text_tlw"><gsmsg:write key="main.man321.1" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderDesc %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortFrDate %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="main.man321.1" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man320Form" property="man320SortKey" value="<%= sortFrDate %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortFrDate %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="main.man321.1" /></span></a></th>
      </logic:notEqual>

      <logic:equal name="man320Form" property="man320SortKey" value="<%= sortToDate %>">
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderAsc %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortToDate %>', '<%= orderDesc %>')"><span class="text_tlw"><gsmsg:write key="main.man322.1" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderDesc %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortToDate %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="main.man322.1" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man320Form" property="man320SortKey" value="<%= sortToDate %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortToDate %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="main.man322.1" /></span></a></th>
      </logic:notEqual>

      <th width="0%" class="table_bg_7D91BD"><span class="text_tlw"><gsmsg:write key="cmn.advanced.settings" /></span></th>

      <logic:equal name="man320Form" property="man320SortKey" value="<%= sortUsrName %>">
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderAsc %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortUsrName %>', '<%= orderDesc %>')"><span class="text_tlw"><gsmsg:write key="main.man323.1" />▲</span></a></th>
      </logic:equal>
      <logic:equal name="man320Form" property="man320OrderKey" value="<%= orderDesc %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortUsrName %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="main.man323.1" />▼</span></a></th>
      </logic:equal>
      </logic:equal>
      <logic:notEqual name="man320Form" property="man320SortKey" value="<%= sortUsrName %>">
        <th width="0%" class="table_bg_7D91BD" nowrap><a href="javascript:void(0)" onclick="return onTitleLinkSubmit('<%= sortUsrName %>', '<%= orderAsc %>')"><span class="text_tlw"><gsmsg:write key="main.man323.1" /></span></a></th>
      </logic:notEqual>

      </tr>

      <logic:iterate id="dspMdl" name="man320Form" property="man320DspList" indexId="idx">
      <bean:define id="tdColor" value="" />
      <% String[] tdColors = new String[] {"td_type20", "td_type25_2"}; %>
      <% tdColor = tdColors[(idx.intValue() % 2)]; %>

      <tr>
        <td class="<%= tdColor %>" nowrap align="center">
        <bean:define id="sid" name="dspMdl" property="imsSid" type="java.lang.Integer" />
        <html:multibox property="selectMsg" value="<%= Integer.toString(sid.intValue()) %>"/>
        </td>

        <td class="<%= tdColor %>" nowrap align="left">
        <logic:equal name="dspMdl" property="kigenChFlg" value="1">
          <img src="../common/images/keikoku.gif" alt="<gsmsg:write key="cmn.warning" />" class="img_bottom">
        </logic:equal>
        <a href="#" onClick="return editMsg(<bean:write name="dspMdl" property="imsSid" />)">
        <span class="text_link">
        <bean:write name="dspMdl" property="imsMsg" />
        </span></a>
        </td>
        <td class="<%= tdColor %>" nowrap align="left"><bean:write name="dspMdl" property="frDate" /></td>
        <td class="<%= tdColor %>" nowrap align="left">
        <logic:equal name="dspMdl" property="kigenChFlg" value="1">
          <span class="text_r1"><bean:write name="dspMdl" property="toDate" /></span>
        </logic:equal>
        <logic:notEqual name="dspMdl" property="kigenChFlg" value="1">
          <bean:write name="dspMdl" property="toDate" />
        </logic:notEqual>
        </td>
        <td class="<%= tdColor %>" nowrap align="center"><bean:write name="dspMdl" property="exString"  /></td>
        <td class="<%= tdColor %>" nowrap align="left">
        <logic:equal name="dspMdl" property="usrJkbn" value="9">
        <del>
        <bean:write name="dspMdl" property="usrNameSei" />&nbsp;<bean:write name="dspMdl" property="usrNameMei" />
        </del>
        </logic:equal>
        <logic:notEqual name="dspMdl" property="usrJkbn" value="9">
        <bean:write name="dspMdl" property="usrNameSei" />&nbsp;<bean:write name="dspMdl" property="usrNameMei" />
        </logic:notEqual>
        </td>
      </tr>

      </logic:iterate>

    </table>


    <bean:size id="count2" name="man320Form" property="man320PageLabel" scope="request" />
    <logic:greaterThan name="count2" value="1">

    <table width="100%" cellpadding="5" cellspacing="0">
    <tr>
    <td width="100%" align="right" valign="top" nowrap>
    <img alt="<gsmsg:write key="cmn.previous.page" />" class="img_bottom" height="20" src="../common/images/arrow2_l.gif" width="20" border="0" onClick="buttonPush('arrorw_left');">
    <logic:notEmpty name="man320Form" property="man320PageLabel">
      <html:select property="man320SltPage2" onchange="changePage2();" styleClass="text_i">
      <html:optionsCollection name="man320Form" property="man320PageLabel" value="value" label="label" />
      </html:select>
    </logic:notEmpty>
    <img src="../common/images/arrow2_r.gif" name ="btn_next" alt="<gsmsg:write key="cmn.next.page" />" class="img_bottom" width="20" height="20" border="0" onClick="buttonPush('arrorw_right');">
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

<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>

</html:form>
</body>
</html:html>