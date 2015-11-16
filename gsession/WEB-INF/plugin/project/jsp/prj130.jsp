<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%
    String mode_kojin  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_KOJIN);
    String mode_kyoyu  = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_KYOYU);
    String mode_select = String.valueOf(jp.groupsession.v2.prj.GSConstProject.MODE_TMP_SELECT);
    String back        = jp.groupsession.v2.prj.prj130.Prj130Action.CMD_BACK;
    String addTmp      = jp.groupsession.v2.prj.prj130.Prj130Action.CMD_ADD;
    String selectTmp   = jp.groupsession.v2.prj.prj130.Prj130Action.CMD_TMP_SELECT;
    String checkTmp    = jp.groupsession.v2.prj.prj130.Prj130Action.CMD_TMP_CHECK;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj130.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/imageView.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">
<html:form action="/project/prj130">
<input type="hidden" name="helpPrm" value="<bean:write name="prj130Form" property="prj130cmdMode" />">

<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="prj010cmdMode" />
<html:hidden property="prj010sort" />
<html:hidden property="prj010order" />
<html:hidden property="prj010page1" />
<html:hidden property="prj010page2" />
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

<logic:notEmpty name="prj130Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj030scrId" />
<html:hidden property="prj030prjSid" />
<html:hidden property="prj030sort" />
<html:hidden property="prj030order" />
<html:hidden property="prj030page1" />
<html:hidden property="prj030page2" />
<html:hidden property="prj030Init" />
<html:hidden property="selectingDate" />
<html:hidden property="selectingStatus" />
<html:hidden property="selectingCategory" />
<html:hidden property="selectingMember" />

<logic:notEmpty name="prj130Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj020scrId" />
<html:hidden property="prj020cmdMode" />
<html:hidden property="prj020prjSid" />
<html:hidden property="prj020prjId" />
<html:hidden property="prj020prjName" />
<html:hidden property="prj020prjNameS" />
<html:hidden property="prj020yosan" />
<html:hidden property="prj020koukai" />
<html:hidden property="prj020startYear" />
<html:hidden property="prj020startMonth" />
<html:hidden property="prj020startDay" />
<html:hidden property="prj020endYear" />
<html:hidden property="prj020endMonth" />
<html:hidden property="prj020endDay" />
<html:hidden property="prj020status" />
<html:hidden property="prj020mokuhyou" />
<html:hidden property="prj020naiyou" />
<html:hidden property="prj020group" />
<html:hidden property="prj020kengen" />
<html:hidden property="prj020prjMyKbn" />
<html:hidden property="prj020smailKbn" />
<html:hidden property="prj020IcoName" />
<html:hidden property="prj020IcoSaveName" />
<html:hidden property="prj020initFlg" />

<logic:notEmpty name="prj130Form" property="prj020syozokuMember" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj020syozokuMember" scope="request">
    <input type="hidden" name="prj020syozokuMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj020user" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj020user" scope="request">
    <input type="hidden" name="prj020user" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj020hdnMember" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj020hdnMember" scope="request">
    <input type="hidden" name="prj020hdnMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj020adminMember" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj020adminMember" scope="request">
    <input type="hidden" name="prj020adminMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj020prjMember" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj020prjMember" scope="request">
    <input type="hidden" name="prj020prjMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj020hdnAdmin" scope="request">
  <logic:iterate id="item" name="prj130Form" property="prj020hdnAdmin" scope="request">
    <input type="hidden" name="prj020hdnAdmin" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj150AddressIdSv" scope="request">
  <logic:iterate id="addressId" name="prj130Form" property="prj150AddressIdSv" scope="request">
    <input type="hidden" name="prj150AddressIdSv" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj150CompanySid" scope="request">
  <logic:iterate id="coId" name="prj130Form" property="prj150CompanySid" scope="request">
    <input type="hidden" name="prj150CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj130Form" property="prj150CompanyBaseSid" scope="request">
  <logic:iterate id="coId" name="prj130Form" property="prj150CompanyBaseSid" scope="request">
    <input type="hidden" name="prj150CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prjTmpMode" />
<html:hidden property="prtSid" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table width="100%">
<tr>
<td width="100%" align="center" cellpadding="5" cellspacing="0">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <logic:equal name="prj130Form" property="prjTmpMode" value="<%= mode_kyoyu %>">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="project.prj130.1" /> ]</td>
      <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <logic:equal name="prj130Form" property="prjTmpMode" value="<%= mode_kojin %>">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../common/images/header_pset_01.gif" border="0" alt="<gsmsg:write key="cmn.preferences2" />"></td>
      <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.preferences2" /></span></td>
      <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="project.prj130.1" /> ]</td>
      <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <logic:equal name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
      <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
      <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="select.template" /> ]</td>
      <td width="100%" class="header_white_bg">
      <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

    <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
      <table cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <td align="right">
        <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="selectTemplate('<%= addTmp %>');">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('<%= back %>');">
      </td>
      </tr>
      </table>
    </logic:notEqual>

    <logic:equal name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
      <tr>
      <td width="50%"></td>
      <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
      <td class="header_glay_bg" width="50%">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('<%= back %>');">
      </td>
      <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
      </tr>
      </table>
    </logic:equal>

  </td>
  </tr>

  <tr>
  <td>
    <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">
  </td>
  </tr>

  <tr>
  <td><span class="text_base"><gsmsg:write key="project.prj130.2" /></span></td>
  </tr>

  <tr>
  <td>

    <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_kojin %>">
      <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <th align="center" class="table_bg_7D91BD" width="100%"><span class="text_tlw"><gsmsg:write key="cmn.shared.template" /></span></th>
      </tr>
    </logic:notEqual>

    <logic:notEmpty name="prj130Form" property="prj130TmpKyoyuList">

      <bean:define id="mod1" value="0" />

      <logic:iterate id="kyoyuTmp" name="prj130Form" property="prj130TmpKyoyuList" indexId="idx">
        <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <tr>
        <td align="left" class="<bean:write name="tblColor" />" width="100%">

          <logic:equal name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
            <a href="javaScript:void(0);" onClick="return selectTemplate('<%= checkTmp %>', '<bean:write name="kyoyuTmp" property="prtSid" />')"><span class="text_link"><bean:write name="kyoyuTmp" property="prtTmpName" /></span></a>
          </logic:equal>

          <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
            <a href="javaScript:void(0);" onClick="return selectTemplate('<%= selectTmp %>', '<bean:write name="kyoyuTmp" property="prtSid" />')"><span class="text_link"><bean:write name="kyoyuTmp" property="prtTmpName" /></span></a>
          </logic:notEqual>

        </td>
        </tr>
      </logic:iterate>
    </logic:notEmpty>

    <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_kojin %>">
      <tr>
      <td>
        <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">
      </td>
      </tr>
      </table>
    </logic:notEqual>

    <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_kyoyu %>">
      <table class="tl0" cellpadding="5" cellspacing="0" border="0" width="100%">
      <tr>
      <th align="center" class="table_bg_7D91BD" width="100%"><span class="text_tlw"><gsmsg:write key="cmn.personal.template" /></span></th>
      </tr>
    </logic:notEqual>

    <logic:notEmpty name="prj130Form" property="prj130TmpKojinList">

      <bean:define id="mod1" value="0" />

      <logic:iterate id="kojinTmp" name="prj130Form" property="prj130TmpKojinList" indexId="idx">
        <logic:equal name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type1" />
        </logic:equal>
        <logic:notEqual name="mod1" value="<%= String.valueOf(idx.intValue() % 2) %>">
          <bean:define id="tblColor" value="td_type25_2" />
        </logic:notEqual>

        <tr>
        <td align="left" class="<bean:write name="tblColor" />" width="100%">

          <logic:equal name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
            <a href="javaScript:void(0);" onClick="return selectTemplate('<%= checkTmp %>', '<bean:write name="kojinTmp" property="prtSid" />')"><span class="text_link"><bean:write name="kojinTmp" property="prtTmpName" /></span></a>
          </logic:equal>

          <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
            <a href="javaScript:void(0);" onClick="return selectTemplate('<%= selectTmp %>', '<bean:write name="kojinTmp" property="prtSid" />')"><span class="text_link"><bean:write name="kojinTmp" property="prtTmpName" /></span></a>
          </logic:notEqual>

        </td>
        </tr>
      </logic:iterate>
    </logic:notEmpty>

    <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_kyoyu %>">
      <tr>
      <td>
        <img src="../common/images/spacer.gif" style="width:1px; height:10px;" border="0" alt="">
      </td>
      </tr>
      </table>
    </logic:notEqual>

  </td>
  </tr>

  <tr>
  <td>

    <table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
    <td align="right">
      <logic:notEqual name="prj130Form" property="prjTmpMode" value="<%= mode_select %>">
        <input type="button" value="<gsmsg:write key="cmn.add" />" class="btn_add_n1" onClick="selectTemplate('<%= addTmp %>');">
      </logic:notEqual>
      <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onClick="buttonPush('<%= back %>');">
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