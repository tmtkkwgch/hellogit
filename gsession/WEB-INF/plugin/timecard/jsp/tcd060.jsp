<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ctag-timeZoneChart.tld" prefix="timeZoneChart" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>

<%@ page import="jp.groupsession.v2.cmn.GSConst" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<title>[GroupSession] <gsmsg:write key="tcd.47" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<script language="JavaScript" src="../timecard/js/tcd060.js?<%= GSConst.VERSION_PARAM %>"></script>

<theme:css filename="theme.css"/>
<link rel=stylesheet href='../common/css/default.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<link rel=stylesheet href='../timecard/css/timecard.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>

</head>

<body class="body_03">
<html:form action="/timecard/tcd060">
<input type="hidden" name="CMD" value="">
<html:hidden property="backScreen" />
<html:hidden property="year" />
<html:hidden property="month" />
<html:hidden property="tcdDspFrom" />

<html:hidden property="usrSid" />
<html:hidden property="sltGroupSid" />
<input type="hidden" name="addTimezoneKbn" value="">
<input type="hidden" name="editTimezoneSid" value="">


<logic:notEmpty name="tcd060Form" property="selectDay" scope="request">
<logic:iterate id="select" name="tcd060Form" property="selectDay" scope="request">
  <input type="hidden" name="selectDay" value="<bean:write name="select" />">
</logic:iterate>
</logic:notEmpty>

<%@ include file="/WEB-INF/plugin/common/jsp/header001.jsp" %>

<!--　BODY -->
<table width="100%" cellpadding="5" cellspacing="0">
<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="left">

    <!-- タイトル -->
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../common/images/header_ktool_01.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    <td width="0%" class="header_ktool_bg_text2" align="left" valign="middle" nowrap><span class="settei_ttl"><gsmsg:write key="cmn.admin.setting" /></span></td>
    <td width="100%" class="header_ktool_bg_text2">[ <gsmsg:write key="tcd.47" /> ]</td>
    <td width="0%"><img src="../common/images/header_ktool_05.gif" border="0" alt="<gsmsg:write key="cmn.admin.setting" />"></td>
    </tr>
    </table>


    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="50%"></td>
    <td width="0%"><img src="../common/images/header_glay_1.gif" border="0" alt=""></td>
    <td class="header_glay_bg" width="50%">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd060_back');"></td>
    <td width="0%"><img src="../common/images/header_glay_3.gif" border="0" alt=""></td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">
    <table cellpadding="0" cellspacing="5" width="100%">
    <tr>
    <logic:equal name="tcd060Form" property="dspChartKbn" value="0">
    <!--タイムチャートタグstart-->
    <timeZoneChart:timezonewrite name="tcd060Form" property="tcd060Timechart"/>
    <!--タイムチャートタグend-->
    </logic:equal>

    <td width="60%" align="center" valign="top">
      <table class="tl0" width="80%">

      <tr>
      <td class="td_type2">
        <table width="100%">
        <tr>
        <th class="tl0" align="left"><span class="text_base2"><gsmsg:write key="tcd.tcd060.01" /></span></th>
        <td class="tl0" align="right"><input type="button" class="btn_add_n4" onClick="addButton('1');"  value="<gsmsg:write key="cmn.add" />"></td>
        </tr>
        </table>
      </td>
      </tr>
      <logic:notEmpty name="tcd060Form" property="tcd060TimezoneMeiList" scope="request">
      <logic:iterate id="tzMdl" name="tcd060Form" property="tcd060TimezoneMeiList" scope="request" indexId="cnt1">
      <logic:equal name="tzMdl" property="timeZoneKbn" value="1">
      <tr>
      <td class="td_type1" align="center"><a href="#" onClick="editButton(<bean:write name="tzMdl" property="timeZoneSID" />);">
      <span class="normal_link_m"><bean:write name="tzMdl" property="timeZoneStr" /></span>
      </a></td>
      </tr>
      </logic:equal>
      </logic:iterate>
      </logic:notEmpty>

      <tr>
      <td class="td_type23">
        <table width="100%">
        <tr>
        <th class="tl0" align="left"><span class="text_tlw"><gsmsg:write key="tcd.tcd060.04" /></span></th>
        <td class="tl0" align="right"><input type="button" class="btn_add_n4" onClick="addButton('2');"  value="<gsmsg:write key="cmn.add" />"></td>
        </tr>
        </table>
      </td>
      </tr>
      <logic:notEmpty name="tcd060Form" property="tcd060TimezoneMeiList" scope="request">
      <logic:iterate id="tzMdl" name="tcd060Form" property="tcd060TimezoneMeiList" scope="request" indexId="cnt2">
      <logic:equal name="tzMdl" property="timeZoneKbn" value="2">
      <tr>
      <td class="td_type1" align="center"><a href="#" onClick="editButton(<bean:write name="tzMdl" property="timeZoneSID" />);">
      <span class="normal_link_m"><bean:write name="tzMdl" property="timeZoneStr" /></span>
      </a></td>
      </tr>
      </logic:equal>
      </logic:iterate>
      </logic:notEmpty>
      <!--深夜残業-->
      <tr>
      <td class="td_type22">
        <table width="100%">
        <tr>
        <th class="tl0" align="left"><span class="text_tlw"><gsmsg:write key="tcd.tcd060.03" /></span></th>
        <td class="tl0" align="right"><input type="button" class="btn_add_n4" onClick="addButton('3');"  value="<gsmsg:write key="cmn.add" />"></td>
        </tr>
        </table>
      </td>
      </tr>
      <logic:notEmpty name="tcd060Form" property="tcd060TimezoneMeiList" scope="request">
      <logic:iterate id="tzMdl" name="tcd060Form" property="tcd060TimezoneMeiList" scope="request" indexId="cnt3">
      <logic:equal name="tzMdl" property="timeZoneKbn" value="3">
      <tr>
      <td class="td_type1" align="center"><a href="#" onClick="editButton(<bean:write name="tzMdl" property="timeZoneSID" />);">
      <span class="normal_link_m"><bean:write name="tzMdl" property="timeZoneStr" /></span>
      </a></td>
      </tr>
      </logic:equal>
      </logic:iterate>
      </logic:notEmpty>

      <tr>
      <td class="td_type9">
        <table width="100%">
        <tr>
        <th class="tl0" align="left"><span class="text_base2"><gsmsg:write key="tcd.tcd060.05" /></span></th>
        <td class="tl0" align="right"><input type="button" class="btn_add_n4" onClick="addButton('4');" value="<gsmsg:write key="cmn.add" />"></td>
        </tr>
        </table>
      </td>
      </tr>
      <logic:notEmpty name="tcd060Form" property="tcd060TimezoneMeiList" scope="request">
      <logic:iterate id="tzMdl" name="tcd060Form" property="tcd060TimezoneMeiList" scope="request" indexId="cnt4">
      <logic:equal name="tzMdl" property="timeZoneKbn" value="4">
      <tr>
      <td class="td_type1" align="center"><a href="#" onClick="editButton(<bean:write name="tzMdl" property="timeZoneSID" />);">
      <span class="normal_link_m"><bean:write name="tzMdl" property="timeZoneStr" /></span>
      </a></td>
      </tr>
      </logic:equal>
      </logic:iterate>
      </logic:notEmpty>

      </table>
    </td>
    </tr>
    </table>

    <img src="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="right" cellpadding="5" cellspacing="0">
      <input type="button" class="btn_back_n1" value="<gsmsg:write key="cmn.back" />" onClick="buttonPush('tcd060_back');">
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