<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- 定数 --%>
<%
    String downLoad  = jp.groupsession.v2.prj.prj160.Prj160Action.CMD_CSV_DOWNLOAD;
    String importCsv = jp.groupsession.v2.prj.prj160.Prj160Action.CMD_IMP_CLICK;
    String back      = jp.groupsession.v2.prj.prj160.Prj160Action.CMD_BACK_CLICK;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmn110.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/project/prj160">
<input type="hidden" name="CMD" value="">
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010Init" />
<html:hidden property="selectingProject" />
<html:hidden property="selectingTodoDay" />
<html:hidden property="selectingTodoPrj" />
<html:hidden property="selectingTodoSts" />
<html:hidden property="prj040searchFlg" />
<html:hidden property="prj040scPrjId" />
<html:hidden property="prj040scStatusFrom" />
<html:hidden property="prj040scStatusTo" />
<html:hidden property="prj040scPrjName" />
<html:hidden property="prj040scStartYearFrom" />
<html:hidden property="prj040scStartMonthFrom" />
<html:hidden property="prj040scStartDayFrom" />
<html:hidden property="prj040scStartYearTo" />
<html:hidden property="prj040scStartMonthTo" />
<html:hidden property="prj040scStartDayTo" />
<html:hidden property="prj040scEndYearFrom" />
<html:hidden property="prj040scEndMonthFrom" />
<html:hidden property="prj040scEndDayFrom" />
<html:hidden property="prj040scEndYearTo" />
<html:hidden property="prj040scEndMonthTo" />
<html:hidden property="prj040scEndDayTo" />
<html:hidden property="prj040svScPrjId" />
<html:hidden property="prj040svScStatusFrom" />
<html:hidden property="prj040svScStatusTo" />
<html:hidden property="prj040svScPrjName" />
<html:hidden property="prj040svScStartYearFrom" />
<html:hidden property="prj040svScStartMonthFrom" />
<html:hidden property="prj040svScStartDayFrom" />
<html:hidden property="prj040svScStartYearTo" />
<html:hidden property="prj040svScStartMonthTo" />
<html:hidden property="prj040svScStartDayTo" />
<html:hidden property="prj040svScEndYearFrom" />
<html:hidden property="prj040svScEndMonthFrom" />
<html:hidden property="prj040svScEndDayFrom" />
<html:hidden property="prj040svScEndYearTo" />
<html:hidden property="prj040svScEndMonthTo" />
<html:hidden property="prj040svScEndDayTo" />
<html:hidden property="prj040page1" />
<html:hidden property="prj040page2" />
<html:hidden property="prj040sort" />
<html:hidden property="prj040order" />
<html:hidden property="prj040scYosanFr" />
<html:hidden property="prj040scYosanTo" />
<html:hidden property="prj040svScYosanFr" />
<html:hidden property="prj040svScYosanTo" />

<html:hidden property="prj160PrjMyKbn" />

<logic:notEmpty name="prj160Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj160Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj160Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj160Form" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj160Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj160Form" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj030scrId" />
<html:hidden property="prj030prjSid" />
<html:hidden property="prj030sort" />
<html:hidden property="prj030order" />
<html:hidden property="prj030page1" />
<html:hidden property="prj030page2" />
<html:hidden property="prj030Init" />
<html:hidden property="prj060todoSid" />
<html:hidden property="selectingDate" />
<html:hidden property="selectingStatus" />
<html:hidden property="selectingCategory" />
<html:hidden property="selectingMember" />
<html:hidden property="selectDir" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="left">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.prj160.1" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n2" onclick="buttonPush('<%= importCsv %>');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= back %>');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td align="left">
    <logic:messagesPresent message="false"><html:errors /><img src="../common/images/spacer.gif" width="1px" height="10px" border="0" alt=""></logic:messagesPresent>
  </td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
    <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span><span class="text_r2">※</span></td>
    <td width="80%" class="td_type1" nowrap>

      <input type="button" class="btn_attach" value="<gsmsg:write key="cmn.attached" />" name="attacheBtn" onClick="opnTemp('prj160SelectFiles', '<%= jp.groupsession.v2.prj.GSConstProject.PLUGIN_ID_PROJECT %>', '1');">
      &nbsp;<input type="button" class="btn_delete" value="<gsmsg:write key="cmn.delete" />" name="dellBtn" onClick="buttonPush('delete');"><br>

      <html:select property="prj160SelectFiles" styleClass="select01" multiple="false" size="1">
        <html:optionsCollection name="prj160Form" property="prj160FileLabelList" value="value" label="label" />
      </html:select>

      &nbsp;

      <span class="text_base_prj">
      <% jp.groupsession.v2.struts.msg.GsMessage gsMsg = new jp.groupsession.v2.struts.msg.GsMessage(); %>
      <% String csvFileMsg = "<a href=\"../project/prj160.do?CMD=" + downLoad + "&sample=1\">" + gsMsg.getMessage(request, "cmn.capture.csvfile") + "</a>"; %>
        *<gsmsg:write key="cmn.plz.specify2" arg0="<%= csvFileMsg %>" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="project.4" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
      <span class="text_base_prj">
        <html:radio styleId="lvl1" name="prj160Form" property="prj160ImportMeans" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.PRJ_IMP_MEANS_ADD) %>" /><label for="lvl1"><gsmsg:write key="project.prj160.5" /></label>&nbsp;
        <html:radio styleId="lvl2" name="prj160Form" property="prj160ImportMeans" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.PRJ_IMP_MEANS_RESET) %>" /><label for="lvl2"><gsmsg:write key="project.prj160.6" /></label>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.project" /></span><span class="text_r2">※</span></td>
    <td class="td_type1">
      <html:select property="prj160PrjSid" styleClass="select01" onchange="buttonPush('projectChange');">
        <html:optionsCollection name="prj160Form" property="prj160ProjectLabel" value="value" label="label" />
      </html:select>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.label" /></span></td>
    <td class="td_type1">
      <logic:notEmpty name="prj160Form" property="prj160CategoryList">
        <span class="text_base_prj">
        <logic:iterate id="category" name="prj160Form" property="prj160CategoryList" scope="request" indexId="idx">
          <br><bean:write name="category" property="ptcName" />
        </logic:iterate>
        <br>&nbsp;
        </span>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.status" /></span></td>
    <td class="td_type1">
      <logic:notEmpty name="prj160Form" property="prj160StatusList">
        <span class="text_base_prj">
        <logic:iterate id="status" name="prj160Form" property="prj160StatusList" scope="request" indexId="idx">
          <br><bean:write name="status" property="ptsRate" />%（<bean:write name="status" property="ptsName" />）
        </logic:iterate>
        <br>&nbsp;
        </span>
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="project.src.29" /></span></td>
    <td class="td_type1" align="left">

      <table class="tl0" width="95%" cellpadding="5" cellspacing="0" border="0">
      <tr>
      <td class="table_bg_7D91BD" align="right" width="5%" nowrap><span class="text_tlw">Ｎｏ</span></td>
      <td class="table_bg_7D91BD" align="center" width="40%"><span class="text_tlw"><gsmsg:write key="cmn.name" /></span></td>
      <td class="table_bg_7D91BD" align="center" width="55%" nowrap><span class="text_tlw"><gsmsg:write key="project.3" /></span></td>
      </tr>

      <logic:notEmpty name="prj160Form" property="prj160MemberList">
        <bean:define id="mod" value="0" />
        <logic:iterate name="prj160Form" property="prj160MemberList" scope="request" id="member" indexId="index">

          <logic:equal name="mod" value="<%= String.valueOf(index.intValue() % 2) %>">
            <bean:define id="tblColor" value="td_type1" />
          </logic:equal>
          <logic:notEqual name="mod" value="<%= String.valueOf(index.intValue() % 2) %>">
            <bean:define id="tblColor" value="td_type_blue" />
          </logic:notEqual>

          <tr>
          <td class="<bean:write name="tblColor" />" align="right">
            <span class="text_base_prj"><%= String.valueOf(index.intValue() + 1) %></span>
          </td>
          <td class="<bean:write name="tblColor" />" align="left">
            <span class="text_base_prj"><a href="javaScript:void(0);" onClick="return openUserInfoWindow('<bean:write name="member" property="userSid" />');"><bean:write name="member" property="sei" />&nbsp;&nbsp;<bean:write name="member" property="mei" /></a></span>
          </td>
          <td class="<bean:write name="tblColor" />" align="left">
            <span class="text_base_prj"><bean:write name="member" property="memberKey" /></span>
          </td>
          </tr>

        </logic:iterate>
      </logic:notEmpty>

      </table>

    </td>
    </tr>

    <tr>
    <td><img src="../common/images/spacer.gif" width="1px" height="3px" border="0"></td>
    </tr>

    <tr>
    <td colspan="2" align="right">
      <input type="button" value="<gsmsg:write key="cmn.import" />" class="btn_csv_n2" onclick="buttonPush('<%= importCsv %>');">
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= back %>');">
    </td>
    </tr>
    </table>

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