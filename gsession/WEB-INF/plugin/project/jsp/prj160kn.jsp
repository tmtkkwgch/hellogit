<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- 定数 --%>
<%
    String importCsv = jp.groupsession.v2.prj.prj160kn.Prj160knAction.CMD_CSV_IMPORT;
    String back      = jp.groupsession.v2.prj.prj160kn.Prj160knAction.CMD_BACK_CLICK;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/project/prj160kn">
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

<logic:notEmpty name="prj160knForm" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj160knForm" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj160knForm" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj160knForm" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj160knForm" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj160knForm" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj160ImportMeans" />
<html:hidden property="prj030scrId" />
<html:hidden property="prj030prjSid" />
<html:hidden property="prj030sort" />
<html:hidden property="prj030order" />
<html:hidden property="prj030page1" />
<html:hidden property="prj030page2" />
<html:hidden property="prj030Init" />
<html:hidden property="prj060todoSid" />
<html:hidden property="prj160PrjSid" />
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
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="project.prj160.7" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('<%= importCsv %>');">
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
  <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
  </tr>

  <tr>
  <td>
    <table width="100%" cellpadding="5" cellspacing="0" class="tl0">
    <tr>
    <td width="20%" class="table_bg_A5B4E1" nowrap><span class="text_bb1"><gsmsg:write key="cmn.capture.file" /></span></td>
    <td width="80%" class="td_type1" nowrap>
      <span class="text_base_prj">
        <bean:write name="prj160knForm" property="prj160knImportFileName" />
      </span>
    </td>
    </tr>
<gsmsg:define id="textAddImport" msgkey="project.prj160.5" />
<gsmsg:define id="textResetImport" msgkey="project.prj160.6" />
    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="project.4" /></span></td>
    <td class="td_type1">
      <span class="text_base_prj">
        <logic:equal name="prj160knForm" property="prj160ImportMeans" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.PRJ_IMP_MEANS_ADD) %>">
          <%= textAddImport %>
        </logic:equal>
        <logic:equal name="prj160knForm" property="prj160ImportMeans" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.PRJ_IMP_MEANS_RESET) %>">
          <%= textResetImport %>
        </logic:equal>
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.project" /></span></td>
    <td class="td_type1">
      <span class="text_base_prj">
        <bean:write name="prj160knForm" property="prj160knTargetProjectName" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="project.prj160kn.2" /></span></td>
    <td class="td_type1">
      <span class="text_base_prj">
        <bean:write name="prj160knForm" property="prj160knImportUserName" />
      </span>
    </td>
    </tr>

    <tr>
    <td class="table_bg_A5B4E1"><span class="text_bb1"><gsmsg:write key="cmn.capture.item.count" /></span></td>
    <td class="td_type1">
      <span class="text_base_prj">
        <bean:write name="prj160knForm" property="prj160ImportCnt" /><gsmsg:write key="cmn.number" />
      </span>
    </td>
    </tr>

    <tr>
    <td><img src="../common/images/spacer.gif" width="1px" height="3px" border="0"></td>
    </tr>

    <tr>
    <td colspan="2" align="right">
      <input type="button" value="<gsmsg:write key="cmn.run" />" class="btn_base1" onclick="buttonPush('<%= importCsv %>');">
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