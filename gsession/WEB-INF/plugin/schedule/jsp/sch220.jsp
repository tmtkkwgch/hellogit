<%@ page pageEncoding="Windows-31J" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/ctag-css.tld" prefix="theme" %>
<%@ taglib uri="/WEB-INF/ctag-message.tld" prefix="gsmsg" %>
<%@ taglib uri="/WEB-INF/ctag-jsmsg.tld" prefix="gsjsmsg" %>
<%@ page import="jp.groupsession.v2.cmn.GSConst" %>



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[Group Session] スケジュール 出欠確認</title>
<script type="text/javascript" src="../common/js/cmd.js?<%= GSConst.VERSION_PARAM %>"></script>
<link rel=stylesheet href="../common/css/default.css?<%= GSConst.VERSION_PARAM %>" type="text/css">
<link rel=stylesheet href='../schedule/css/schedule.css?<%= GSConst.VERSION_PARAM %>' type='text/css'>
<theme:css filename="theme.css"/>

</head>

<body class="body_03" onload="" onunload="">

<html:form action="/schedule/sch220">

<input type="hidden" name="CMD" value="">

<!--　BODY -->

<table width="100%" cellpadding="5" cellspacing="0">

<tr>
<td width="100%" align="center">

  <table width="70%" cellpadding="0" cellspacing="0">
  <tr>
  <td align="center">

    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
    <td width="0%"><img src="../schedule/images/header_schedule_01.gif" border="0" alt="<gsmsg:write key="schedule.108" />"></td>
    <td width="0%" class="header_white_bg_text" align="right" valign="middle" nowrap><span class="plugin_ttl"><gsmsg:write key="schedule.108" /></span></td>
    <td width="100%" class="header_white_bg_text">[ スケジュール 出欠確認 ]</td>
    <td width="0%"><img src="../common/images/header_white_end.gif" border="0" alt="<gsmsg:write key="schedule.108"/>"></td>
    </tr>
    </table>


  </td>
  </tr>

  <tr>
  <td><img src="../common/images/spacer.gif" style="width:100px; height:10px;" border="0" alt=""></td>
  </tr>

  <tr>
  <td align="center">

    <table class="tl0 table_td_border" width="100%" cellpadding="0" cellspacing="0">
      <tr class="table_bg_7D91BD">
      <th width="40%" class="table_bg_7D91BD" nowrap>回答日時</th>
      <th width="30%" class="table_bg_7D91BD" nowrap>氏名</th>
      <th width="30%" class="table_bg_7D91BD" nowrap>回答内容</th>
      </tr>
      <logic:notEmpty name="sch220Form" property="sch040AttendAnsList">
      <logic:iterate id="attendMdl" name="sch220Form" property="sch040AttendAnsList" indexId="idx">
        <% String[] typeClass = new String[] {"td_type1", "td_type_usr"}; %>
        <% String tdType = typeClass[(idx.intValue() % 2)]; %>
        <tr class="<%= tdType %>">
        <td align="center" nowrap>
        <logic:equal name="attendMdl" property="attendAnsKbn" value="0">
        －
        </logic:equal>
        <logic:notEqual name="attendMdl" property="attendAnsKbn" value="0">
          <bean:write name="attendMdl" property="attendAnsDate" />
        </logic:notEqual>
        </td>
        <td align="left" nowrap>
          <bean:write name="attendMdl" property="attendAnsUsrName" />
        </td>
        <td align="center" nowrap>
          <logic:equal name="attendMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NONE) %>">未回答</logic:equal>
          <logic:equal name="attendMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_YES) %>"><span class="attend_text_yes">出席</span></logic:equal>
          <logic:equal name="attendMdl" property="attendAnsKbn" value="<%= String.valueOf(jp.groupsession.v2.cmn.GSConstSchedule.ATTEND_ANS_NO) %>"><span class="attend_text_no">欠席</span></logic:equal>
        </td>
        </tr>
      </logic:iterate>
      </logic:notEmpty>
    </table>

  </td>
  </tr>

  <tr>
  <td>
    <IMG SRC="../common/images/spacer.gif" width="1px" height="10px" border="0">

    <table width="100%">
    <tr>
    <td width="100%" align="center" cellpadding="5" cellspacing="0">
      <input type="button" value="<gsmsg:write key="cmn.close" />" class="btn_close_n1" onClick="window.close();">
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


</body>
</html:html>