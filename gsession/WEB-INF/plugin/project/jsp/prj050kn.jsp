<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>


<%-- CMD定数 --%>
<%  String kakuteiClick    = jp.groupsession.v2.prj.prj050kn.Prj050knAction.CMD_KAKUTEI_CLICK;
    String backClick       = jp.groupsession.v2.prj.prj050kn.Prj050knAction.CMD_BACK_CLICK;
    String fileDl          = jp.groupsession.v2.prj.prj050kn.Prj050knAction.CMD_FILE_DL;

    String entryAdd        = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_ADD;
    String entryEdit       = jp.groupsession.v2.prj.GSConstProject.CMD_MODE_EDIT;
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href="../project/css/project.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<title>[Group Session] <gsmsg:write key="project.107" /></title>
<gsjsmsg:js filename="gsjsmsg.js"/>
<script language="JavaScript" src='../common/js/jquery-1.5.2.min.js?<%= GSConst.VERSION_PARAM %>'></script>
<script type="text/javascript" src="../common/js/calendar.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/project.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../project/js/prj050kn.js?<%= GSConst.VERSION_PARAM %>"></script>
<theme:css filename="theme.css"/>
</head>

<body class="body_03">

<html:form action="/project/prj050kn">
<input type="hidden" name="helpPrm" value="<bean:write name="prj050knForm" property="prj050cmdMode" />">

<input type="hidden" name="CMD" id="CMD" value="">
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

<logic:notEmpty name="prj050knForm" property="prj040scMemberSid" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj040scMemberSid" scope="request">
    <input type="hidden" name="prj040scMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj040svScMemberSid" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj040svScMemberSid" scope="request">
    <input type="hidden" name="prj040svScMemberSid" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj050elementKbn" />

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

<logic:notEmpty name="prj050knForm" property="prj030sendMember" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj030sendMember" scope="request">
    <input type="hidden" name="prj030sendMember" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj070scrId" />
<html:hidden property="prj070scPrjSid" />
<html:hidden property="prj070scCategorySid" />
<html:hidden property="prj070scStatusFrom" />
<html:hidden property="prj070scStatusTo" />
<html:hidden property="prj070scKaisiYoteiYear" />
<html:hidden property="prj070scKaisiYoteiMonth" />
<html:hidden property="prj070scKaisiYoteiDay" />
<html:hidden property="prj070scKigenYear" />
<html:hidden property="prj070scKigenMonth" />
<html:hidden property="prj070scKigenDay" />
<html:hidden property="prj070scKaisiJissekiYear" />
<html:hidden property="prj070scKaisiJissekiMonth" />
<html:hidden property="prj070scKaisiJissekiDay" />
<html:hidden property="prj070scSyuryoJissekiYear" />
<html:hidden property="prj070scSyuryoJissekiMonth" />
<html:hidden property="prj070scSyuryoJissekiDay" />
<html:hidden property="prj070scTitle" />
<html:hidden property="prj070KeyWordkbn" />
<html:hidden property="prj070svScPrjSid" />
<html:hidden property="prj070svScCategorySid" />
<html:hidden property="prj070svScStatusFrom" />
<html:hidden property="prj070svScStatusTo" />
<html:hidden property="prj070svScKaisiYoteiYear" />
<html:hidden property="prj070svScKaisiYoteiMonth" />
<html:hidden property="prj070svScKaisiYoteiDay" />
<html:hidden property="prj070svScKigenYear" />
<html:hidden property="prj070svScKigenMonth" />
<html:hidden property="prj070svScKigenDay" />
<html:hidden property="prj070svScKaisiJissekiYear" />
<html:hidden property="prj070svScKaisiJissekiMonth" />
<html:hidden property="prj070svScKaisiJissekiDay" />
<html:hidden property="prj070svScSyuryoJissekiYear" />
<html:hidden property="prj070svScSyuryoJissekiMonth" />
<html:hidden property="prj070svScSyuryoJissekiDay" />
<html:hidden property="prj070svScTitle" />
<html:hidden property="prj070SvKeyWordkbn" />
<html:hidden property="prj070page1" />
<html:hidden property="prj070page2" />
<html:hidden property="prj070sort" />
<html:hidden property="prj070order" />
<html:hidden property="prj070searchFlg" />
<html:hidden property="prj070InitFlg" />

<logic:notEmpty name="prj050knForm" property="prj070scTantou" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070scTantou" scope="request">
    <input type="hidden" name="prj070scTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070scTourokusya" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070scTourokusya" scope="request">
    <input type="hidden" name="prj070scTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070svScTantou" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070svScTantou" scope="request">
    <input type="hidden" name="prj070svScTantou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070svScTourokusya" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070svScTourokusya" scope="request">
    <input type="hidden" name="prj070svScTourokusya" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070svScJuuyou" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070svScJuuyou" scope="request">
    <input type="hidden" name="prj070svScJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070scJuuyou" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070scJuuyou" scope="request">
    <input type="hidden" name="prj070scJuuyou" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070SearchTarget" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070SearchTarget" scope="request">
    <input type="hidden" name="prj070SearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj070SvSearchTarget" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj070SvSearchTarget" scope="request">
    <input type="hidden" name="prj070SvSearchTarget" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj060scrId" />
<html:hidden property="prj060prjSid" />
<html:hidden property="prj060todoSid" />
<html:hidden property="prj060schUrl" />
<html:hidden property="prj060comment" />
<html:hidden property="prj060status" />
<html:hidden property="prj060riyu" />

<html:hidden property="prj060SelectYearFr" />
<html:hidden property="prj060SelectMonthFr" />
<html:hidden property="prj060SelectDayFr" />
<html:hidden property="prj060SelectYearTo" />
<html:hidden property="prj060SelectMonthTo" />
<html:hidden property="prj060SelectDayTo" />
<html:hidden property="prj060ResultKosu" />

<html:hidden property="prj050scrId" />
<html:hidden property="prj050cmdMode" />
<html:hidden property="prj050prjSid" />
<html:hidden property="prj050todoSid" />

<html:hidden property="prj050prjMyKbn" />
<html:hidden property="prj050cate" />
<html:hidden property="prj050title" />
<html:hidden property="prj050kaisiYoteiYear" />
<html:hidden property="prj050kaisiYoteiMonth" />
<html:hidden property="prj050kaisiYoteiDay" />
<html:hidden property="prj050kigenYear" />
<html:hidden property="prj050kigenMonth" />
<html:hidden property="prj050kigenDay" />
<html:hidden property="prj050yoteiKosu" />
<html:hidden property="prj050kaisiJissekiYear" />
<html:hidden property="prj050kaisiJissekiMonth" />
<html:hidden property="prj050kaisiJissekiDay" />
<html:hidden property="prj050syuryoJissekiYear" />
<html:hidden property="prj050syuryoJissekiMonth" />
<html:hidden property="prj050syuryoJissekiDay" />
<html:hidden property="prj050jissekiKosu" />
<html:hidden property="prj050keikoku" />
<html:hidden property="prj050juyou" />
<html:hidden property="prj050status" />
<html:hidden property="prj050statusCmt" />
<html:hidden property="prj050naiyo" />

<html:hidden property="prj050titleEasy" />
<html:hidden property="prj050kigenYearEasy" />
<html:hidden property="prj050kigenMonthEasy" />
<html:hidden property="prj050kigenDayEasy" />
<html:hidden property="prj050juyouEasy" />
<html:hidden property="prj050statusEasy" />
<html:hidden property="prj050statusCmtEasy" />
<html:hidden property="prj050naiyoEasy" />

<logic:notEmpty name="prj050knForm" property="prj050tanto" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj050tanto" scope="request">
    <input type="hidden" name="prj050tanto" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj050member" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj050member" scope="request">
    <input type="hidden" name="prj050member" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj050hdnTanto" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj050hdnTanto" scope="request">
    <input type="hidden" name="prj050hdnTanto" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<logic:notEmpty name="prj050knForm" property="prj050tenpu" scope="request">
  <logic:iterate id="item" name="prj050knForm" property="prj050tenpu" scope="request">
    <input type="hidden" name="prj050tenpu" value="<bean:write name="item"/>">
  </logic:iterate>
</logic:notEmpty>

<html:hidden property="prj050MailSend" />
<html:hidden property="prj050PrjListKbn" />
<html:hidden property="prj050dspKbn" />
<html:hidden property="fileId" value="" />
<html:hidden property="selectDir" />

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<td width="100%" align="center">

  <table cellpadding="0" width="82%" class="tl0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../project/images/header_project_01.gif" border="0" alt="<gsmsg:write key="cmn.project" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="cmn.project" /></span></td>
    <td width="0%" class="header_white_bg_text">
      <logic:equal name="prj050knForm" property="prj050cmdMode" value="<%= entryAdd %>">[ <gsmsg:write key="project.prj050kn.1" /> ]</logic:equal>
      <logic:equal name="prj050knForm" property="prj050cmdMode" value="<%= entryEdit %>">[ <gsmsg:write key="project.prj050kn.2" /> ]</logic:equal>
    </td>
    <td width="100%" class="header_white_bg">
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt=""></td>
    </tr>
    </table>

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('<%= kakuteiClick %>');">
      <input type="button" value="<gsmsg:write key="cmn.back"/>" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
    </td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <logic:messagesPresent message="false">
    <table width="100%">
    <tr><td colspan="4" align="left"><html:errors/></td></tr>
    </table>
    </logic:messagesPresent>

    <logic:equal name="prj050knForm" property="prj050elementKbn" value="0">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="projectName" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap width="20%"><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="prj050titleEasy" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.111" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="kigen" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="naiyo" filter="false" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.4" /></span></td>
    <%-- <td align="left" class="td_type1"><bean:write name="prj050knForm" property="juyoName" /></td> --%>
    <td align="left" class="td_type1">
      <bean:write name="prj050knForm" property="juyoName" />
      <logic:equal name="prj050knForm" property="prj050juyouEasy" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_LOW) %>">
        <img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:middle;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:middle;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:middle;">
      </logic:equal>
      <logic:equal name="prj050knForm" property="prj050juyouEasy" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_MIDDLE) %>">
        <img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:middle;"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:middle;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:middle;">
      </logic:equal>
      <logic:equal name="prj050knForm" property="prj050juyouEasy" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_HIGH) %>">
        <img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:middle;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:middle;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:middle;">
      </logic:equal>
    </td>
    </tr>
    </table>
    </logic:equal>

    <logic:equal name="prj050knForm" property="prj050elementKbn" value="1">
    <table width="100%" class="tl0 prj_tbl_base" border="0" cellpadding="5">
    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.project" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="projectName" /></td>
    </tr>


    <logic:equal name="prj050Form" property="prj050cmdMode" value="<%= entryEdit %>">
    <tr>
    <td class="td_sub_title" colspan="2" width="20%" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.5" /></span></td>
    <td class="td_type1" align="left" width="80%">
      <logic:notEmpty name="prj050knForm" property="projectItem">
      <bean:define id="prjMdl" name="prj050knForm" property="projectItem" />
      <bean:write name="prjMdl" property="strKanriNo" />
      </logic:notEmpty>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.registant" /></span></td>
    <td class="td_type1" align="left">
      <logic:notEmpty name="prj050knForm" property="projectItem">
      <bean:define id="prjMdl" name="prj050knForm" property="projectItem" />
      <bean:write name="prjMdl" property="addUserSei" />&nbsp;<bean:write name="prjMdl" property="addUserMei" />
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap width="20%"><span class="text_bb1"><gsmsg:write key="cmn.title" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="prj050title" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.label" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="categoryName" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" rowspan="3" nowrap><span class="text_bb1"><gsmsg:write key="project.prj010.8" /></span></td>
    <td class="td_sub_title" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="kaisiYotei" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="kigen" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap><span class="text_bb1"><gsmsg:write key="project.33" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="prj050yoteiKosu" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" rowspan="3" nowrap><span class="text_bb1"><gsmsg:write key="cmn.performance" /></span></td>
    <td class="td_sub_title" nowrap><span class="text_bb1"><gsmsg:write key="cmn.start" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="kaisiJisseki" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap><span class="text_bb1"><gsmsg:write key="cmn.end" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="syuryoJisseki" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" nowrap><span class="text_bb1"><gsmsg:write key="project.33" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="prj050jissekiKosu" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.content2" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="naiyo" filter="false" /></td>
    </tr>

    <logic:equal name="prj050knForm" property="prj050prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.113" /></span></td>
    <td class="td_type1" align="left">
      <logic:notEmpty name="prj050knForm" property="adminMemberLabel">
      <logic:iterate id="member" name="prj050knForm" property="adminMemberLabel">
      <bean:write name="member" property="label" /><br>
      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>
    </logic:equal>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.prj050.4" /></span></td>
    <%-- <td align="left" class="td_type1"><bean:write name="prj050knForm" property="juyoName" /></td> --%>
    <td align="left" class="td_type1">
      <bean:write name="prj050knForm" property="juyoName" />
      <logic:equal name="prj050knForm" property="prj050juyou" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_LOW) %>">
        <img src="../project/images/star_b.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:middle;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:middle;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.58" />" style="vertical-align:middle;">
      </logic:equal>
      <logic:equal name="prj050knForm" property="prj050juyou" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_MIDDLE) %>">
        <img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:middle;"><img src="../project/images/star_y.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:middle;"><img src="../project/images/star_g.gif" border="0" alt="<gsmsg:write key="project.59" />" style="vertical-align:middle;">
      </logic:equal>
      <logic:equal name="prj050knForm" property="prj050juyou" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.WEIGHT_KBN_HIGH) %>">
        <img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:middle;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:middle;"><img src="../project/images/star_o.gif" border="0" alt="<gsmsg:write key="project.60" />" style="vertical-align:middle;">
      </logic:equal>
    </td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.35" /></span></td>
    <td align="left" class="td_type1"><bean:write name="prj050knForm" property="keikokuName" /></td>
    </tr>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="cmn.status" /></span></td>
    <td align="left" class="td_type1">
      <bean:write name="prj050knForm" property="statusRate" />%(<bean:write name="prj050knForm" property="statusName" />)

      <br>
      <br><gsmsg:write key="project.36" />：<br>
      <bean:write name="prj050knForm" property="statusCmt" filter="false" />
    </td>
    </tr>
<gsmsg:define id="textNo" msgkey="cmn.no3" />
<gsmsg:define id="textAllMem" msgkey="project130" />
<gsmsg:define id="textCmnStaff" msgkey="cmn.staff" />
<gsmsg:define id="textProjectAdm" msgkey="project.src.32" />
<gsmsg:define id="textProjectLeaderAndTanto" msgkey="project.src.64" />

    <logic:equal name="prj050knForm" property="useSmail" value="true">
    <logic:equal name="prj050knForm" property="prj050prjMyKbn" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.KBN_MY_PRJ_DEF) %>">
    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="shortmail.notification" /></span></td>
    <td align="left" class="td_type1">
      <logic:equal name="prj050knForm" property="prj050MailSend" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.NOT_SEND) %>"><%= textNo %></logic:equal>
      <logic:equal name="prj050knForm" property="prj050MailSend" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_TANTO) %>"><%= textCmnStaff %></logic:equal>
      <logic:equal name="prj050knForm" property="prj050MailSend" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER) %>"><%= textProjectAdm %></logic:equal>
      <logic:equal name="prj050knForm" property="prj050MailSend" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_LEADER_AND_TANTO) %>"><%= textProjectLeaderAndTanto %></logic:equal>
      <logic:equal name="prj050knForm" property="prj050MailSend" value="<%= String.valueOf(jp.groupsession.v2.prj.GSConstProject.SEND_ALL_MEMBER) %>"><%= textAllMem %></logic:equal>
    </td>
    </tr>
    </logic:equal>
    </logic:equal>

    <tr>
    <td class="td_sub_title" colspan="2" nowrap><span class="text_bb1"><gsmsg:write key="project.110" /></span></td>
    <td align="left" class="td_type1">
      <logic:notEmpty name="prj050knForm" property="fileLabel">
      <logic:iterate id="fileMdl" name="prj050knForm" property="fileLabel">
      <a href="javascript:void(0);" onClick="return fileDl('<%= fileDl %>', '<bean:write name="fileMdl" property="value" />');"><span class="text_link"><bean:write name="fileMdl" property="label" /></span></a><br>
      </logic:iterate>
      </logic:notEmpty>
    </td>
    </tr>
    </table>
    </logic:equal>

    <img src="../schedule/images/spacer.gif" width="1px" height="10px" border="0">

    <table cellpadding="0" width="100%">
    <tr>
    <td align="right">
          <input type="button" value="<gsmsg:write key="cmn.final" />" class="btn_base1" onclick="buttonPush('<%= kakuteiClick %>');">
          <input type="button" value="<gsmsg:write key="cmn.back" />" class="btn_back_n1" onclick="buttonPush('<%= backClick %>');">
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