<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- 定数 --%>
<%
    String tabNaibuClick  = jp.groupsession.v2.prj.prj150.Prj150Action.CMD_TAB_NAIBU;
    String tabGaibuClick  = jp.groupsession.v2.prj.prj150.Prj150Action.CMD_TAB_GAIBU;

    String okClick    = jp.groupsession.v2.prj.prj150.Prj150Action.CMD_OK_CLICK;
    String okTmpClick = jp.groupsession.v2.prj.prj150.Prj150Action.CMD_OK_TMP_CLICK;
    String backClick  = jp.groupsession.v2.prj.prj150.Prj150Action.CMD_BACK_CLICK;
    String back020    = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ020;
    String back030    = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ030;
    String back140    = jp.groupsession.v2.prj.GSConstProject.SCR_ID_PRJ140;
%>

<%-- 処理(タブ)モード --%>
<%
    String modeNaibu  = jp.groupsession.v2.prj.GSConstProject.MODE_NAIBU;
    String modeGaibu  = jp.groupsession.v2.prj.GSConstProject.MODE_GAIBU;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/userpopup.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj150.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/check.js?<%= GSConst.VERSION_PARAM %>"></script>

<logic:equal name="prj150Form" property="addressPluginKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.PLUGIN_USE) %>">
<script language="JavaScript" src="../address/js/adr240.js?<%= GSConst.VERSION_PARAM %>"></script>
</logic:equal>
<theme:css filename="theme.css"/>

</head>

<body class="body_03" onunload="windowClose();">

<html:form action="/project/prj150">
<input type="hidden" name="helpPrm" value="<bean:write name="prj150Form" property="prj150cmdMode" />">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
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

<logic:notEmpty name="prj150Form" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj040svScMemberSid" scope="request">
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

<logic:notEmpty name="prj150Form" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj030sendMember" scope="request">
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

<html:hidden property="prj150cmdMode" />
<html:hidden property="dspAddFlg" />

<input type="hidden" name="prj150delCompanyId" value="">
<input type="hidden" name="prj150delCompanyBaseId" value="">
<input type="hidden" name="prj150UsrDelFlg" value="">

<logic:notEmpty name="prj150Form" property="prj020syozokuMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj020syozokuMember" scope="request">
    <input type="hidden" name="prj020syozokuMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj020user" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj020user" scope="request">
    <input type="hidden" name="prj020user" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<div id="hiddenList">

  <logic:notEmpty name="prj150Form" property="prj020hdnMember" scope="request">
    <logic:iterate id="item" name="prj150Form" property="prj020hdnMember" scope="request">
      <input type="hidden" name="prj020hdnMember" value="<bean:write name="item"/>">
    </logic:iterate>
  </logic:notEmpty>
  <logic:notEmpty name="prj150Form" property="prj140hdnMember" scope="request">
    <logic:iterate id="item" name="prj150Form" property="prj140hdnMember" scope="request">
      <input type="hidden" name="prj140hdnMember" value="<bean:write name="item"/>">
    </logic:iterate>
  </logic:notEmpty>

  <logic:notEmpty name="prj150Form" property="prj020hdnMemberSv" scope="request">
    <logic:iterate id="item" name="prj150Form" property="prj020hdnMemberSv" scope="request">
      <input type="hidden" name="prj020hdnMemberSv" value="<bean:write name="item"/>">
    </logic:iterate>
  </logic:notEmpty>
  <logic:notEmpty name="prj150Form" property="prj140hdnMemberSv" scope="request">
    <logic:iterate id="item" name="prj150Form" property="prj140hdnMemberSv" scope="request">
      <input type="hidden" name="prj140hdnMemberSv" value="<bean:write name="item"/>">
    </logic:iterate>
  </logic:notEmpty>

</div>

<div id="prj150CompanyIdArea">
<logic:notEmpty name="prj150Form" property="prj150CompanySid">
  <logic:iterate id="coId" name="prj150Form" property="prj150CompanySid">
    <input type="hidden" name="prj150CompanySid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj150CompanySidSv">
  <logic:iterate id="coId" name="prj150Form" property="prj150CompanySidSv">
    <input type="hidden" name="prj150CompanySidSv" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

</div>

<div id="prj150CompanyBaseIdArea">
<logic:notEmpty name="prj150Form" property="prj150CompanyBaseSid">
  <logic:iterate id="coId" name="prj150Form" property="prj150CompanyBaseSid">
    <input type="hidden" name="prj150CompanyBaseSid" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj150CompanyBaseSidSv">
  <logic:iterate id="coId" name="prj150Form" property="prj150CompanyBaseSidSv">
    <input type="hidden" name="prj150CompanyBaseSidSv" value="<bean:write name="coId"/>">
  </logic:iterate>
</logic:notEmpty>

</div>

<div id="prj150AddressIdArea">
<logic:notEmpty name="prj150Form" property="prj150AddressId">
  <logic:iterate id="addressId" name="prj150Form" property="prj150AddressId">
    <input type="hidden" name="prj150AddressId" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<div id="prj150AddressIdSvArea">
<logic:notEmpty name="prj150Form" property="prj150AddressIdSv">
  <logic:iterate id="addressId" name="prj150Form" property="prj150AddressIdSv">
    <input type="hidden" name="prj150AddressIdSv" value="<bean:write name="addressId"/>">
  </logic:iterate>
</logic:notEmpty>
</div>

<logic:notEmpty name="prj150Form" property="prj020adminMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj020adminMember" scope="request">
    <input type="hidden" name="prj020adminMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj020prjMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj020prjMember" scope="request">
    <input type="hidden" name="prj020prjMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj020hdnAdmin" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj020hdnAdmin" scope="request">
    <input type="hidden" name="prj020hdnAdmin" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prjTmpMode" />
<html:hidden property="prtSid" />
<html:hidden property="prj140prtTmpName" />
<html:hidden property="prj140prtId" />
<html:hidden property="prj140prtName" />
<html:hidden property="prj140prtNameS" />
<html:hidden property="prj140yosan" />
<html:hidden property="prj140koukai" />
<html:hidden property="prj140startYear" />
<html:hidden property="prj140startMonth" />
<html:hidden property="prj140startDay" />
<html:hidden property="prj140endYear" />
<html:hidden property="prj140endMonth" />
<html:hidden property="prj140endDay" />
<html:hidden property="prj140status" />
<html:hidden property="prj140mokuhyou" />
<html:hidden property="prj140naiyou" />
<html:hidden property="prj140group" />
<html:hidden property="prj140kengen" />
<html:hidden property="prj140smailKbn" />

<logic:notEmpty name="prj150Form" property="prj140syozokuMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj140syozokuMember" scope="request">
    <input type="hidden" name="prj140syozokuMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj140user" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj140user" scope="request">
    <input type="hidden" name="prj140user" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj140adminMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj140adminMember" scope="request">
    <input type="hidden" name="prj140adminMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj140prjMember" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj140prjMember" scope="request">
    <input type="hidden" name="prj140prjMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj150Form" property="prj140hdnAdmin" scope="request">
  <logic:iterate id="item" name="prj150Form" property="prj140hdnAdmin" scope="request">
    <input type="hidden" name="prj140hdnAdmin" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="movedDspId" />
<html:hidden property="sepKey" />
<html:hidden property="selectDir" />
<html:hidden property="prj020AddMemAllDelFlg" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="70%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
    <td width="0%" class="header_white_bg_text">[ <gsmsg:write key="cmn.project" /><gsmsg:write key="project.29" /> ]</td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">

    <!-- 内部タブ -->
    <logic:equal name="prj150Form" property="prj150cmdMode" value="0">
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      </logic:equal>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back020 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="prj150ButtonPush('<%= okClick %>', 1);">
      </logic:equal>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back140 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="prj150ButtonPush('<%= okTmpClick %>', 1);">
      </logic:equal>

      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
      </logic:equal>

      <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="prj150ButtonPush('<%= backClick %>', 2);">
      </logic:notEqual>
    </logic:equal>

    <!-- 外部タブ -->
    <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back140 %>">
    <logic:notEqual name="prj150Form" property="prj150cmdMode" value="0">
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      </logic:equal>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back020 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="prj150GaiButtonPush('<%= okClick %>', 1);">
      </logic:equal>

      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
      </logic:equal>

      <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="prj150GaiButtonPush('<%= backClick %>', 2);">
      </logic:notEqual>
    </logic:notEqual>
    </logic:notEqual>
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    <tr>
    <td colspan="3"><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
    </tr>
    <tr>
    <td colspan="3" align="left" nowrap>
      <logic:messagesPresent message="false">
        <html:errors/>
      </logic:messagesPresent>
    </td>
    </tr>
    <tr>
    <td colspan="3"><img src="../common/images/spacer.gif" width="1px" height="5px" border="0"></td>
    </tr>
    </table>

    <table width="100%" cellpadding="0" cellspacing="0">
      <tr>
      <td width="100%">

      <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back140 %>">
      <logic:equal name="prj150Form" property="prj150cmdMode" value="<%= modeNaibu %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('<%= tabNaibuClick %>');"><gsmsg:write key="project.prj150.9" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('<%= tabGaibuClick %>');"><gsmsg:write key="project.prj150.10" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
      </logic:equal>

      <logic:equal name="prj150Form" property="prj150cmdMode" value="<%= modeGaibu %>">
        <table class="tab_table" width="100%" height="34px" border="0" cellpadding="0" cellspacing="0">
        <tr>

        <td class="tab_bg_image_1_off" nowrap>
            <div class="tab_text_area_right">
                <a href="javascript:changeTab('<%= tabNaibuClick %>');"><gsmsg:write key="project.prj150.9" /></a>
            </div>
        </td>
        <td class="tab_space" nowrap>&nbsp;</td>
        <td class="tab_bg_image_1_on" nowrap>
            <div class="tab_text_area">
                <a href="javascript:changeTab('<%= tabGaibuClick %>');"><gsmsg:write key="project.prj150.10" /></a>
            </div>
        </td>

        <td class="tab_bg_underbar" width="100%" align="right" valign="top" nowrap>&nbsp;</td>
        </tr>
        </table>
      </logic:equal>
      </logic:notEqual>

      </td>
      <td width="0%" class="tab_head_end"><img src="../common/images/damy.gif" width="10" height="10" border="0" alt=""></td>
      </tr>
    </table>

    <table class="tl0 prj_tbl_base_2" width="100%" cellpadding="0" cellspacing="0">
      <logic:equal name="prj150Form" property="prj150cmdMode" value="<%= modeNaibu %>">
        <%@ include file="/WEB-INF/plugin/project/jsp/prj150_sub01.jsp" %>
      </logic:equal>
      <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back140 %>">
      <logic:equal name="prj150Form" property="prj150cmdMode" value="<%= modeGaibu %>">
        <%@ include file="/WEB-INF/plugin/project/jsp/prj150_sub02.jsp" %>
      </logic:equal>
      </logic:notEqual>
    </table>

    <tr>
      <td><img src="../common/images/spacer.gif" width="1px" height="10px" border="0"></td>
    </tr>

    <tr>
    <td>
    <table align="right">
    <tr>

    <!-- 内部タグ -->
    <logic:equal name="prj150Form" property="prj150cmdMode" value="0">
    <td>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      </logic:equal>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back020 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="prj150ButtonPush('<%= okClick %>', 1);">
      </logic:equal>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back140 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="prj150ButtonPush('<%= okTmpClick %>', 1);">
      </logic:equal>
    </td>
    <td>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
      </logic:equal>
      <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="prj150ButtonPush('<%= backClick %>', 2);">
      </logic:notEqual>
    </td>
    </logic:equal>

    <!-- 外部タブ -->
    <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back140 %>">
    <logic:notEqual name="prj150Form" property="prj150cmdMode" value="0">
    <td>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="buttonPush('<%= okClick %>');">
      </logic:equal>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back020 %>">
        <input type="button" value="OK" class="btn_ok1" onclick="prj150GaiButtonPush('<%= okClick %>', 1);">
      </logic:equal>
    </td>
    <td>
      <logic:equal name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
      </logic:equal>

      <logic:notEqual name="prj150Form" property="movedDspId" value="<%= back030 %>">
        <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="prj150GaiButtonPush('<%= backClick %>', 2);">
      </logic:notEqual>
    </td>
    </logic:notEqual>
    </logic:notEqual>
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