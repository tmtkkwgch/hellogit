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
<title>[GroupSession] <gsmsg:write key="main.man290kn.1" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/count.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/changestyle.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../main/js/man290kn.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">


<html:form action="/main/man290kn">

<html:hidden property="cmd" />
<html:hidden property="man320OrderKey" />
<html:hidden property="man320SortKey" />
<html:hidden property="man320FormAdminConfBtn" />
<html:hidden property="man320SltPage1" />
<html:hidden property="man320SltPage2" />
<html:hidden property="man320PageNum" />
<html:hidden property="man320SelectedSid" />

<html:hidden property="man290ExtKbn" />
<html:hidden property="man290Week" />
<html:hidden property="man290Day" />
<html:hidden property="man290FrYear" />
<html:hidden property="man290FrMonth" />
<html:hidden property="man290FrDay" />
<html:hidden property="man290FrHour" />
<html:hidden property="man290FrMin" />
<html:hidden property="man290ToYear" />
<html:hidden property="man290ToMonth" />
<html:hidden property="man290ToDay" />
<html:hidden property="man290ToHour" />
<html:hidden property="man290ToMin" />
<html:hidden property="man290Msg" />
<html:hidden property="man290Value" />
<html:hidden property="man290Jtkbn" />
<html:hidden property="man290groupSid" />
<html:hidden property="man290elementKbn" />
<html:hidden property="man290HolKbn" />
<html:hidden name="man290knForm" property="man290knTmpFileId" />

<logic:notEmpty name="man290knForm" property="man290memberSid">
<logic:iterate id="usid" name="man290knForm" property="man290memberSid">
  <input type="hidden" name="man290memberSid" value="<bean:write name="usid" />">
</logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="man290knForm" property="man290Dweek" scope="request">
<logic:iterate id="selectWeek" name="man290knForm" property="man290Dweek" scope="request">
  <input type="hidden" name="man290Dweek" value="<bean:write name="selectWeek" />">
</logic:iterate>
</logic:notEmpty>


<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>
<input type="hidden" name="helpPrm" value="<bean:write name="man290knForm" property="man290helpMode" />">
<!--　BODY -->
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../main/images/header_info_01.gif" border="0" alt="<gsmsg:write key="man.information.management" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.information" /></span></td>
    <logic:equal name="man290knForm" property="cmd" value="add">
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="main.man290kn.1" /> ]</td>
    </logic:equal>
    <logic:equal name="man290knForm" property="cmd" value="edit">
    <td width="100%" class="header_white_bg_text">[ <gsmsg:write key="man.information.edit.kn" /> ]</td>
    </logic:equal>

    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%">
    </td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

        <logic:equal name="man290knForm" property="cmd" value="add">
        <input type="hidden" name="CMD" value="290kn_ok">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush2('290kn_commit', '<bean:write name="man290knForm" property="cmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush2('290kn_back', '<bean:write name="man290knForm" property="cmd" />');">
        </logic:equal>

        <logic:equal name="man290knForm" property="cmd" value="edit">
        <input type="hidden" name="CMD" value="290kn_ok">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush2('290kn_commit', '<bean:write name="man290knForm" property="cmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush2('290kn_back', '<bean:write name="man290knForm" property="cmd" />');">
        </logic:equal>

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
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="main.man290.2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean:write name="man290knForm" property="man290knKoukaiString" /><br><br>
    <logic:notEmpty name="man290knForm" property="man290knDateList" scope="request">
    <logic:iterate id="aftDate" name="man290knForm" property="man290knDateList" scope="request">
      <bean:write name="aftDate" scope="page"/><br>
    </logic:iterate>
    <br>
    </logic:notEmpty>
    <span class="text_r1"><bean:write name="man290knForm" property="man290knSyukuString" /></span>
    </span>
    </td>
    </tr>

    <tr>
    <td rowspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.period" /></span></td>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
    <span class="text_base">
    <bean:write name="man290knForm" property="man290knFrDate" />
    </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="<%= tdColor %>" nowrap>
    <span class="text_base">
    <bean:write name="man290knForm" property="man290knToDate" />
    </span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.message" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean:write name="man290knForm" property="man290knMsg" />
    </span>
    </td>
    </tr>

    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="<%= tdColor %>">
    <span class="text_base">
    <bean:write name="man290knForm" property="man290knValue" filter="false" />
    </span>
    </td>
    </tr>


    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="main.exposed" /></span></td>
    <td align="left" class="td_type20">
    <span class="text_base">

    <logic:notEmpty name="man290knForm" property="man290knKoukaiList">
    <logic:iterate id="memName" name="man290knForm" property="man290knKoukaiList">
        <bean:write name="memName" property="label" /><br>
    </logic:iterate>
    </logic:notEmpty>

    </span>
    </td>
    </tr>


    <tr>
    <td colspan="2" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.attached" /></span></td>
    <td align="left" class="td_type20" valign="middle">
      <logic:empty name="man290knForm" property="man290FileLabelList" scope="request">&nbsp;</logic:empty>
      <logic:notEmpty name="man290knForm" property="man290FileLabelList" scope="request">
      <table cellpadding="0" cellpadding="0" border="0">
      <logic:iterate id="fileMdl" name="man290knForm" property="man290FileLabelList" scope="request">
      <tr>
      <td><img src="../common/images/file_icon.gif" alt="<gsmsg:write key="cmn.file" />"></td>
      <td class="menu_bun"><a href="javascript:void(0);" onClick="return fileLinkClick('<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a></td>
      </tr>
      </logic:iterate>
      </table>
      </logic:notEmpty>
    </td>
    </tr>

    </table>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
    <tr>
    <td align="left">
    </td>
    <td align="right">

        <logic:equal name="man290knForm" property="cmd" value="add">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush2('290kn_commit', '<bean:write name="man290knForm" property="cmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush2('290kn_back', '<bean:write name="man290knForm" property="cmd" />');">
        </logic:equal>

        <logic:equal name="man290knForm" property="cmd" value="edit">
          <input type="button" value="<gsmsg:write key="man.final" />" class="btn_base1" onClick="buttonPush2('290kn_commit', '<bean:write name="man290knForm" property="cmd" />');">
          <input type="button" value="<gsmsg:write key="cmn.back2" />" class="btn_back_n1" onClick="buttonPush2('290kn_back', '<bean:write name="man290knForm" property="cmd" />');">
        </logic:equal>


    </td>
    </tr>
    </table>

  </td>
  </tr>

  </table>

</td>
</tr>
</table>

<IFRAME type="hidden" src="../common/html/damy.html" style="display: none" name="navframe"></IFRAME>
</html:form>

<span id="damy"></span>
<%@ include file="/WEB-INF/plugin/common/jsp/footer001.jsp" %>
</body>
</html:html>